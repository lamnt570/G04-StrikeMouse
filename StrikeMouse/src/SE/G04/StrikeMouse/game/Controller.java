package SE.G04.StrikeMouse.game;

import SE.G04.StrikeMouse.util.Constants;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Controller {
	private static final String TAG =  Controller.class.getName();
	public Sprite backGround;
	
	public Controller () {
		init();
	}
	
	private void init () {
		backGround = new Sprite(Assets.instance.backGround.backGround);
		backGround.setSize(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		backGround.translate(-Constants.VIEWPORT_WIDTH/2, -Constants.VIEWPORT_HEIGHT/2);
	}
	
	public void update (float deltaTime) {
		
	}
}
