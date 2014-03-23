package SE.G04.StrikeMouse.game;

import sun.print.BackgroundLookupListener;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import SE.G04.StrikeMouse.util.Constants;

public class Renderer implements Disposable {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Controller controller;
	
	public Renderer (Controller controller) {
		this.controller = controller;
		init();
	}
	
	private void init () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
	}
	
	public void render () {
		batch.setProjectionMatrix(camera.combined);	
		batch.begin();
		controller.backGround.draw(batch);
		for(int i=0; i<=10; i++){
		controller.hole[i].draw(batch);
		}
		//controller.mouse.draw(batch);
		batch.end();
	}
	
	public void resize (int width, int height) {
		camera.update();
	}
	
	@Override 
	public void dispose () {
		batch.dispose();
	}
}