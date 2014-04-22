package SE.G04.StrikeMouse.game;

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
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import SE.G04.StrikeMouse.util.Constants;

public class Assets implements Disposable, AssetErrorListener {
	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	private AssetManager assetManager;

	public AssetBackGround backGround;
	public AssetHoles holes;
	public AssetFonts fonts;

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

	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.debug(TAG, "Couldn't load asset '" + asset.fileName + "'",
				(Exception) throwable);
	}

	@Override
	public void dispose() {
		assetManager.dispose();

		fonts.defaultBig.dispose();
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
			holes = new AtlasRegion[6];
			for (char i = '0'; i < '6'; i++)
				holes[i - '0'] = atlas.findRegion(i + "");
		}
	}
	

	public class AssetFonts {
		public final BitmapFont defaultBig;

		public AssetFonts() {

			defaultBig = new BitmapFont(
					Gdx.files.internal("images/arial-15.fnt"), true);

			defaultBig.setScale(2.0f);


			defaultBig.getRegion().getTexture()
					.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		}
	}
	
	
}
