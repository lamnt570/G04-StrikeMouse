package SE.G04.StrikeMouse.game;

import SE.G04.StrikeMouse.util.Constants;
import com.badlogic.gdx.math.MathUtils;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Controller {
	private static final String TAG =  Controller.class.getName();
	public Sprite backGround;
	public Sprite hole[];
	//public Sprite mouse;
	
	public Controller () {
		init();
	}
	
	private void init () {
		
		backGround = new Sprite(Assets.instance.backGround.backGround);
		backGround.setSize(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		backGround.translate(-Constants.VIEWPORT_WIDTH/2, -Constants.VIEWPORT_HEIGHT/2);
		
		hole = new Sprite[11];
		
		for(int i=0; i<11; i++){
			hole[i]= new Sprite(Assets.instance.hole.hole);
			hole[i].setSize(2,(float) 1.5);
		}
		
		for(int i=3; i<7; i++){
			hole[i].setSize((float) 2,(float) 1.75);
		}
		
		for(int i=7; i<11; i++){
			hole[i].setSize((float) 2.5,(float) 2);
		}
		
		
		hole[0].setPosition(Constants.hole1width, Constants.hole1height);
		hole[1].setPosition(Constants.hole2width, Constants.hole2height);
		hole[2].setPosition(Constants.hole3width, Constants.hole3height);
		hole[3].setPosition(Constants.hole4width, Constants.hole4height);
		hole[4].setPosition(Constants.hole5width, Constants.hole5height);
		hole[5].setPosition(Constants.hole6width, Constants.hole6height);
		hole[6].setPosition(Constants.hole7width, Constants.hole7height);
		hole[7].setPosition(Constants.hole8width, Constants.hole8height);
		hole[8].setPosition(Constants.hole9width, Constants.hole9height);
		hole[9].setPosition(Constants.hole10width, Constants.hole10height);
		hole[10].setPosition(Constants.hole11width, Constants.hole11height);
	
//		mouse = new Sprite(Assets.instance.mouse.mouse);
//		mouse.setSize((float)2,(float) 1.5);
//		mouse.setPosition(Constants.hole1width, Constants.hole1height);
//		
	}
	
	public void update (float deltaTime) {
		
	}
}
