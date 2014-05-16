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
		for (int i = 0; i < Constants.HOLE_ROWS; i++)
			for (int j = 0; j < Constants.HOLE_COLUMNS; j++)
				controller.holes[i][j].draw(batch);
		if (controller.hammerIsOn)
			controller.hammer.draw(batch);
		batch.end();
		renderGUI(batch);
	}

	private void renderGUI(SpriteBatch batch) {
		batch.setProjectionMatrix(cameraGUI.combined);
		batch.begin();
		renderGui(batch);
		batch.end();
	}

	public void resize(int width, int height) {
		camera.update();
		cameraGUI.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	private void renderGui(SpriteBatch batch) {
		BitmapFont scoreFont = Assets.instance.fonts.defaultBigFonts;
		Assets.instance.fonts.defaultBigFonts.draw(batch,
				"" + controller.score, Constants.SCORE_POSITION_WIDTH+150,
				Constants.SCORE_POSITION_HEIGHT);
		Assets.instance.fonts.defaultBigFonts.draw(batch, "" + "SCORE",
				Constants.STRING_SCORE_POSITON_WIDTH+170,
				Constants.SCORE_POSITION_HEIGHT);

		scoreFont.setColor(1, 0, 0, 1);
		renderGuiExtraLive(batch);
		renderGuiGameOverMessage(batch);

	}

	private void renderGuiExtraLive(SpriteBatch batch) {
		for (int i = 0; i < Constants.LIVES_START; i++) {
			Assets.instance.fonts.defaultBigFonts.draw(batch, ""
					+ controller.lives, Constants.SCORE_POSITION_WIDTH - 200,
					Constants.SCORE_POSITION_HEIGHT);
			Assets.instance.fonts.defaultBigFonts.draw(batch, "" + "LIVES",
					Constants.STRING_SCORE_POSITON_WIDTH - 150,
					Constants.SCORE_POSITION_HEIGHT);
		}
	}

	private void renderGuiGameOverMessage(SpriteBatch batch) {
		float x = cameraGUI.viewportWidth / 2;
		float y = cameraGUI.viewportHeight / 2;
		if (controller.isGameOver()==true) {
			BitmapFont fontGameOver = Assets.instance.fonts.defaultBigFonts;
			fontGameOver.drawMultiLine(batch, "GAME OVER", x, y, 0,
					BitmapFont.HAlignment.CENTER);

		}
	}

}
