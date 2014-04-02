package SE.G04.StrikeMouse.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
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
	
	private Assets() {}
	
	public void init(AssetManager assetManager){
		this.assetManager = assetManager;
		assetManager.setErrorListener(this);
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		assetManager.finishLoading();
		
//		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
//		for (String str: assetManager.getAssetNames())
//			Gdx.app.debug(TAG, "asset: " + str);
		
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		for (Texture t: atlas.getTextures())
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		backGround = new AssetBackGround(atlas);
		holes = new AssetHoles(atlas);
	}
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.debug(TAG, "Couldn't load asset '" + asset.fileName + "'", 
				(Exception) throwable);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}
	
	
	public class AssetBackGround{
		public final AtlasRegion backGround;
		public AssetBackGround(TextureAtlas atlas){
			backGround = atlas.findRegion("back_ground");
		}
	}
	
	public class AssetHoles{
		public final AtlasRegion holes[];
		public AssetHoles(TextureAtlas atlas){
			holes = new AtlasRegion[6];
			for (char i='0'; i<'6'; i++)
				holes[i-'0'] = atlas.findRegion(i+"");
		}
	}
}
