package SE.G04.StrikeMouse.game;
import sun.print.BackgroundLookupListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import SE.G04.StrikeMouse.util.Constants;

public class Renderer implements Disposable {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Controller controller;
	private OrthographicCamera cameraGUI;

	public Renderer(Controller controller) {
		this.controller = controller;
		init();
	}

	private void init() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,
				Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
		cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH,
				Constants.VIEWPORT_GUI_HEIGHT);
		cameraGUI.position.set(0, 0, 0);
		cameraGUI.setToOrtho(true);
		cameraGUI.update();
	}

	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		controller.backGround.draw(batch);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				controller.holes[i][j].draw(batch);
			}
		}

		batch.end();
		renderGUI(batch);

	}

	private void renderGUI(SpriteBatch batch) {
		batch.setProjectionMatrix(cameraGUI.combined);
		batch.begin();
		renderGuiScore(batch);
		batch.end();
	}

	public void resize(int width, int height) {
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / (float) height)
				* (float) width;
		camera.update();
		cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
		cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT / (float) height)
				* (float) width;
		cameraGUI.position.set(cameraGUI.viewportWidth / 2,
				cameraGUI.viewportHeight / 2, 0);
		cameraGUI.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	private void renderGuiScore(SpriteBatch batch) {
		float x = -15;
		float y = -15;
		BitmapFont scoreFont = Assets.instance.fonts.defaultBig;
		Assets.instance.fonts.defaultBig.draw(batch, "" + controller.score,
				x + 500, y + 48);
		Assets.instance.fonts.defaultBig.draw(batch, "" + controller.score1,
				x + 350, y + 48);
		scoreFont.setColor(1, 0, 0, 1);
	}

}
