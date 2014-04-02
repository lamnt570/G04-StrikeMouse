package SE.G04.StrikeMouse.game;

import java.util.Random;

import SE.G04.StrikeMouse.util.Constants;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Controller {
	private static final String TAG =  Controller.class.getName();
	public Sprite backGround;
	public Sprite[][] holes;
	public float sum=0;
	public int currentImage=0;
	public float changeTime=0.1f;
	public int Xcurrent,Ycurrent;
	
	private Sprite[][][] holesImages;
	public Controller () {
		init();
	}
	
	private void init () {
		initBackGround();
		initHoles();
		Random rd = new Random();
		Xcurrent=rd.nextInt(3);
		Ycurrent=rd.nextInt(3);
	}
	
	public static void setImageInfo(Sprite spr, float sizeX, float sizeY, float x, float y){
		spr.setSize(Constants.VIEWPORT_WIDTH*sizeX, Constants.VIEWPORT_HEIGHT*sizeY);
		spr.setPosition(Constants.VIEWPORT_WIDTH*(x-sizeX/2), Constants.VIEWPORT_HEIGHT*(y-sizeY/2));
	}
	
	private void initBackGround(){
		backGround = new Sprite(Assets.instance.backGround.backGround);
		setImageInfo(backGround, 1, 1, 0, 0);
	}
	
	private static final float sizeWidthMax = 0.25f;	// width of the biggest hole
	private static final float sizeHeightMax = 0.25f;	// height of the biggest hole
	private static final float middleX = 0f;			// coordinate of middle hole in Ox
	private static final float middleY = -5/16f;			// coordinate of middle hole in Oy
	private static final float deltaX = 5/16f;			// distance between two holes in Ox
	private static final float deltaY = 0.25f;			// distance between two holes in Oy
	private static int delay=0;
	
	private void initHolesImages(){
		holesImages = new Sprite[3][3][8];
		
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				for (int k=0; k<6; k++)
					holesImages[i][j][k] = new Sprite(Assets.instance.holes.holes[k]);
		
		for (int j=0; j<3; j++)
			for (int k=0; k<6; k++)
				setImageInfo(holesImages[2][j][k],						
						sizeWidthMax, sizeHeightMax,
						middleX + (j-1)*deltaX, middleY);
			
		for (int j=0; j<3; j++)
			for (int k=0; k<6; k++)
				setImageInfo(holesImages[1][j][k], 
						4/5f*sizeWidthMax, 4/5f*sizeHeightMax,
						4/5f*(middleX + (j-1)*deltaX), middleY + deltaY);
		
		for (int j=0; j<3; j++)
			for (int k=0; k<6; k++)
				setImageInfo(holesImages[0][j][k], 
						4/6f*sizeWidthMax, 4/6f*sizeHeightMax,
						4/6f*(middleX + (j-1)*deltaX), middleY + 1.8f*deltaY);
	}

	public void initHoles(){
		initHolesImages();
		holes = new Sprite[3][3];
		
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				holes[i][j] = holesImages[i][j][0];
	}
	
	public void update(float deltaTime) {
		sum+=deltaTime;
		Random rd =new Random();
		if( sum > changeTime){
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
				 Xcurrent= rd.nextInt(3);
				 Ycurrent= rd.nextInt(3);
				 currentImage=0;
			}
			
			holes[Xcurrent][Ycurrent]=holesImages[Xcurrent][Ycurrent][currentImage];
			sum=0;
		}
		
	}

}


