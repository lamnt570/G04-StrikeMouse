package SE.G04.StrikeMouse.game;

import java.util.Random;

import SE.G04.StrikeMouse.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Controller {
	private static final String TAG = Controller.class.getName();

	public Sprite backGround;
	public Sprite[][] holes;
	public int score;

	private float totalDeltaTime = 0;
	private int currentImage = 0;
	private int currentX, currentY;
	private static int delay = 0;
	private Sprite[][][] holesImages;
	private static Random random;

	private float hammerX;
	private float hammerY;
	private boolean mouseIsAlive;

	public Controller() {
		random = new Random();
		init();
	}

	public void init() {
		initBackGround();
		initHolesImages();
		initHoles();
		initMouse();
	}

	public static void setImageInfo(Sprite spr, float sizeX, float sizeY,
			float x, float y) {
		spr.setSize(Constants.VIEWPORT_WIDTH * sizeX, Constants.VIEWPORT_HEIGHT
				* sizeY);
		spr.setPosition(Constants.VIEWPORT_WIDTH * (x - sizeX / 2),
				Constants.VIEWPORT_HEIGHT * (y - sizeY / 2));
	}

	public void initBackGround() {
		backGround = new Sprite(Assets.instance.backGround.backGround);
		setImageInfo(backGround, 1, 1, 0, 0);
	}

	// width of the biggest hole
	private static final float sizeWidthMax = 0.25f;
	// height of the biggest hole
	private static final float sizeHeightMax = 0.25f;
	// horizontal coordinate of middle hole
	private static final float middleX = 0f;
	// vertical coordinate of middle hole
	private static final float middleY = -5 / 16f;
	// max horizontal distance between two holes
	private static final float maxDeltaX = 5 / 16f;
	// max vertical distance between two holes
	private static final float maxDeltaY = 0.25f;
	// ratio between size of holes in row i and size max
	private static final float[] sizeRatio = { 4 / 6f, 4 / 5f, 1f };
	// ratio between distance of 2 holes in row i and max horizontal distance
	private static final float[] deltaXRatio = { 4 / 6f, 4 / 5f, 1f };
	// ratio between distance of 2 holes in column j and max vertical distance
	private static final float[] deltaYRatio = { 1.8f, 1f, 0f };

	public void createHolesImagesArray() {
		holesImages = new Sprite[Constants.HOLE_ROWS][Constants.HOLE_COLUMNS][Constants.MAX_MOUSE_IMAGE];

		for (int i = 0; i < Constants.HOLE_ROWS; i++)
			for (int j = 0; j < Constants.HOLE_COLUMNS; j++)
				for (int k = 0; k < Constants.MAX_MOUSE_IMAGE; k++)
					holesImages[i][j][k] = new Sprite();
	}

	public void loadHolesImages() {
		for (int i = 0; i < Constants.HOLE_ROWS; i++)
			for (int j = 0; j < Constants.HOLE_COLUMNS; j++)
				for (int k = 0; k < Constants.MAX_MOUSE_IMAGE; k++)
					holesImages[i][j][k]
							.setRegion(Assets.instance.holes.holes[k]);
	}

	public void setHolesImagesInfo() {
		for (int i = 0; i < Constants.HOLE_ROWS; i++)
			for (int j = 0; j < Constants.HOLE_COLUMNS; j++)
				for (int k = 0; k < Constants.MAX_MOUSE_IMAGE; k++)
					setImageInfo(holesImages[i][j][k], sizeRatio[i]
							* sizeWidthMax, sizeRatio[i] * sizeHeightMax,
							middleX + deltaXRatio[i] * (j - 1) * maxDeltaX,
							middleY + deltaYRatio[i] * maxDeltaY);
	}

	public void initHolesImages() {
		createHolesImagesArray();
		loadHolesImages();
		setHolesImagesInfo();
	}

	public void initHoles() {
		holes = new Sprite[Constants.HOLE_ROWS][Constants.HOLE_COLUMNS];

		for (int i = 0; i < Constants.HOLE_ROWS; i++)
			for (int j = 0; j < Constants.HOLE_COLUMNS; j++)
				holes[i][j] = holesImages[i][j][0];
	}

	public void initMouse() {
		currentX = random.nextInt(Constants.HOLE_ROWS);
		currentY = random.nextInt(Constants.HOLE_COLUMNS);
		mouseIsAlive = true;
	}

	public float getTotalDeltaTime() {
		return totalDeltaTime;
	}

	public int getCurrentImage() {
		return currentImage;
	}

	public void updateCurrentImage() {
		if (currentImage == (Constants.MAX_MOUSE_IMAGE - 1))
			if (delay < Constants.LAST_IMAGE_DELAY)
				delay++;
			else {
				currentImage++;
				delay = 0;
			}
		else
			currentImage++;
	}

	public void checkCurrentImage() {
		if (currentImage == Constants.MAX_MOUSE_IMAGE) {
			holes[currentX][currentY] = holesImages[currentX][currentY][0];
			currentX = random.nextInt(Constants.HOLE_ROWS);
			currentY = random.nextInt(Constants.HOLE_COLUMNS);
			mouseIsAlive = true;
			currentImage = 0;
		}
	}

	public void updateHoleImage() {
		holes[currentX][currentY] = holesImages[currentX][currentY][currentImage];
	}

	public float getInputX() {
		float x = Gdx.input.getX();
		x /= Gdx.graphics.getWidth();
		x -= 0.5f;
		return x;
	}

	public float getInputY() {
		float y = Gdx.input.getY();
		y /= Gdx.graphics.getHeight();
		y -= 0.5f;
		y *= -1;
		return y;
	}

	public boolean checkHammerHit() {
		if (!mouseIsAlive)
			return false;
		
		float width = holes[currentX][currentY].getWidth();
		float height = holes[currentX][currentY].getHeight();
		
		float minX = holes[currentX][currentY].getX();
		float minY = holes[currentX][currentY].getY();
		float maxX = minX + width;
		float maxY = minY + +height;
		
		minX += width/6f;
		maxX -= width/6f;
		if ((minX <= hammerX) && (hammerX <= maxX))
			if ((minY <= hammerY) && (hammerY <= maxY))
				return true;
		
		return false;
	}

	public void handleUserInput() {
		if (Gdx.app.getType() == ApplicationType.Desktop) {
			if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
				hammerX = getInputX();
				hammerY = getInputY();
				if (checkHammerHit()) {
					score++;
					mouseIsAlive = false;
				}
			}
		}
	}

	public void update(float deltaTime) {
		totalDeltaTime += deltaTime;

		if (totalDeltaTime >= Constants.UPDATE_INTERVAL) {
			updateCurrentImage();
			checkCurrentImage();
			updateHoleImage();

			totalDeltaTime = 0;
		}

		handleUserInput();
	}
}
