package SE.G04.StrikeMouse.game;

import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

import SE.G04.StrikeMouse.util.Constants;

public class Assets implements Disposable, AssetErrorListener {
	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	private AssetManager assetManager;
	public AssetBackGround backGround;
	public AssetHoles holes;
	public AssetFonts fonts;
	public AssetHammers hammers;

	private Assets() {
	}

	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		assetManager.setErrorListener(this);
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		assetManager.finishLoading();
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		for (Texture t : atlas.getTextures())
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		backGround = new AssetBackGround(atlas);
		holes = new AssetHoles(atlas);
		fonts = new AssetFonts();
		hammers = new AssetHammers(atlas);
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.debug(TAG, "Couldn't load asset '" + asset.fileName + "'",
				(Exception) throwable);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
		fonts.defaultBigFonts.dispose();
	}

	public class AssetBackGround {
		public final AtlasRegion backGround;

		public AssetBackGround(TextureAtlas atlas) {
			backGround = atlas.findRegion("back_ground");
		}
	}

	public class AssetHoles {
		public final AtlasRegion holes[];

		public AssetHoles(TextureAtlas atlas) {
			holes = new AtlasRegion[Constants.MAX_ALIVE_MOUSE_IMAGE
					+ Constants.MAX_HIT_MOUSE_IMAGE];
			for (int i = 0; i < holes.length; i++)
				if (i < Constants.MAX_ALIVE_MOUSE_IMAGE)
					holes[i] = atlas.findRegion("alive_mouse" + i);
				else
					holes[i] = atlas.findRegion("hit_mouse"
							+ (i - Constants.MAX_ALIVE_MOUSE_IMAGE));
		}
	}

	public class AssetFonts {
		public final BitmapFont defaultBigFonts;

		public AssetFonts() {
			defaultBigFonts = new BitmapFont(
					Gdx.files.internal("images/arial-15.fnt"), true);
			defaultBigFonts.setScale(2.0f);
			defaultBigFonts.getRegion().getTexture()
					.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		}
	}

	public class AssetHammers {
		public final AtlasRegion hammers[];

		public AssetHammers(TextureAtlas atlas) {
			hammers = new AtlasRegion[Constants.MAX_HAMMER_IMAGE];
			for (int i = 0; i < Constants.MAX_HAMMER_IMAGE; i++) {
				hammers[i] = atlas.findRegion("hammer" + i);
			}
		}
	}

}
