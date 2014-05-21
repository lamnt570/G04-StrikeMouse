package SE.G04.StrikeMouse;

import com.badlogic.gdx.ApplicationListener;

import SE.G04.StrikeMouse.game.Assets;
import SE.G04.StrikeMouse.game.Controller;
import SE.G04.StrikeMouse.game.Renderer;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;

public class StrikeMouseMain implements ApplicationListener {
	private Controller controller;
	private Renderer renderer;
	private boolean paused;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Assets.instance.init(new AssetManager());

		controller = new Controller();
		controller.init();
		renderer = new Renderer(controller);

		paused = false;
	}

	@Override
	public void render() {
		if (!paused) {
			controller.update(Gdx.graphics.getDeltaTime());
		}
		Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f,
				0xff / 255.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		Assets.instance.init(new AssetManager());
		paused = false;
	}

	@Override
	public void dispose() {
		renderer.dispose();
		Assets.instance.dispose();
	}
}
