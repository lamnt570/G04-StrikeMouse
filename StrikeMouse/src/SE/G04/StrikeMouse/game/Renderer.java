package SE.G04.StrikeMouse.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import SE.G04.StrikeMouse.util.Constants;

public class Renderer implements Disposable {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Controller controller;
	
	public Renderer (Controller controller) {}
	
	private void init () {}
	
	public void render () {}
	
	public void resize (int width, int height) {}
	
	@Override 
	public void dispose () {}
}