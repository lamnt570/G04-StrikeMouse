package SE.G04.StrikeMouse.game;

import java.util.Random;

import SE.G04.StrikeMouse.util.Constants;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Controller {
	private static final String TAG =  Controller.class.getName();
	
	public Sprite backGround;
	public Sprite[][] holes;
	
	private float sumTime = 0;
	private int currentImage = 0;
	private float changeTime = 0.07f;
	private int Xcurrent,Ycurrent;
	private static int delay=0;
	private Sprite[][][] holesImages;
	private static Random random;
	
	
	public Controller () {
		random = new Random();
		init();
	}
	
	public void init () {
		initBackGround();
		initHoles();
		initMouse();		
	}
	

	public static void setImageInfo(Sprite spr, float sizeX, float sizeY, float x, float y){
		spr.setSize(Constants.VIEWPORT_WIDTH*sizeX, Constants.VIEWPORT_HEIGHT*sizeY);
		spr.setPosition(Constants.VIEWPORT_WIDTH*(x-sizeX/2), Constants.VIEWPORT_HEIGHT*(y-sizeY/2));
	}
	
	public void initBackGround(){
		backGround = new Sprite(Assets.instance.backGround.backGround);
		setImageInfo(backGround, 1, 1, 0, 0);
	}
	
	private static final float sizeWidthMax = 0.25f;	// width of the biggest hole
	private static final float sizeHeightMax = 0.25f;	// height of the biggest hole
	private static final float middleX = 0f;			// coordinate of middle hole in Ox axis
	private static final float middleY = -5/16f;		// coordinate of middle hole in Oy axis
	private static final float deltaX = 5/16f;			// distance between two holes in Ox axis
	private static final float deltaY = 0.25f;			// distance between two holes in Oy axis
	private static final float[] sizeRatio = {4/6f, 4/5f, 1f}; // ratio between size of holes in row i and size max
	private static final float[] deltaXRatio = {4/6f, 4/5f, 1f};
	private static final float[] deltaYRatio = {1.8f, 1f, 0f};
	
	public void initHolesImages(){
		holesImages = new Sprite[3][3][7];
		
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				for (int k=0; k<6; k++){
					holesImages[i][j][k] = new Sprite(Assets.instance.holes.holes[k]);
					setImageInfo(holesImages[i][j][k],
							sizeRatio[i]*sizeWidthMax, sizeRatio[i]*sizeHeightMax,
							middleX + deltaXRatio[i]*(j-1)*deltaX, middleY + deltaYRatio[i]*deltaY);
				}
	}

	public void initHoles(){
		initHolesImages();
		holes = new Sprite[3][3];
		
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				holes[i][j] = holesImages[i][j][0];
	}
	
	public void initMouse(){
		Xcurrent = random.nextInt(3);
		Ycurrent = random.nextInt(3);
	}
	
	public void update(float deltaTime) {
		sumTime +=deltaTime;
		if( sumTime > changeTime){
			if (currentImage<6) {
				if(currentImage==5 && delay<1){
					delay++;
				}
				else{
					currentImage++;
					delay=0;
				}
			}
			
			if(currentImage==6 ){
				 holes[Xcurrent][Ycurrent]=holesImages[Xcurrent][Ycurrent][0];
				 Xcurrent= random.nextInt(3);
				 Ycurrent= random.nextInt(3);
				 currentImage=0;
			}
			
			holes[Xcurrent][Ycurrent]=holesImages[Xcurrent][Ycurrent][currentImage];
			sumTime = 0;
		}		
	}
}
