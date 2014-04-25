package SE.G04.StrikeMouse.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import SE.G04.StrikeMouse.util.Constants;

public class ControllerTest {
	private Controller controller;

	@Before
	public void setUp() {
		controller = new Controller();
		controller.createHolesImagesArray();
		controller.setHolesImagesInfo();
		controller.initHoles();
	}

	@Test
	public void testHoleWidth() {
		float expectedWidth[][] = { { 1 / 6f, 1 / 6f, 1 / 6f },
				{ 1 / 5f, 1 / 5f, 1 / 5f }, { 1 / 4f, 1 / 4f, 1 / 4f } };
		for (int i = 0; i < Constants.HOLE_ROWS; i++)
			for (int j = 0; j < Constants.HOLE_COLUMNS; j++)
				assertEquals(expectedWidth[i][j],
						controller.holes[i][j].getWidth(), 0);
	}

	@Test
	public void testHoleHeight() {
		float expectedHeight[][] = { { 1 / 6f, 1 / 6f, 1 / 6f },
				{ 1 / 5f, 1 / 5f, 1 / 5f }, { 1 / 4f, 1 / 4f, 1 / 4f } };
		for (int i = 0; i < Constants.HOLE_ROWS; i++)
			for (int j = 0; j < Constants.HOLE_COLUMNS; j++)
				assertEquals(expectedHeight[i][j],
						controller.holes[i][j].getHeight(), 0);
	}

	@Test
	public void testHoleX() {
		float expectedX[][] = { { -7 / 24f, -1 / 12f, 1 / 8f },
				{ -7 / 20f, -1 / 10f, 3 / 20f }, { -7 / 16f, -1 / 8f, 3 / 16f } };

		for (int i = 0; i < Constants.HOLE_ROWS; i++)
			for (int j = 0; j < Constants.HOLE_COLUMNS; j++)
				assertEquals(expectedX[i][j], controller.holes[i][j].getX(),
						0.0000001);
	}

	@Test
	public void testHoleY() {
		float expectedY[][] = { { 13 / 240f, 13 / 240f, 13 / 240f },
				{ -13 / 80f, -13 / 80f, -13 / 80f },
				{ -7 / 16f, -7 / 16f, -7 / 16f } };

		for (int i = 0; i < Constants.HOLE_ROWS; i++)
			for (int j = 0; j < Constants.HOLE_COLUMNS; j++)
				assertEquals(expectedY[i][j], controller.holes[i][j].getY(),
						0.0000001);
	}

	@Test
	public void testTotalDeltaTime() {
		assertEquals(0f, controller.getTotalDeltaTime(), 0);
		controller.update(Constants.UPDATE_INTERVAL);
		assertEquals(0f, controller.getTotalDeltaTime(), 0);

		assertEquals(0f, controller.getTotalDeltaTime(), 0);
		controller.update(Constants.UPDATE_INTERVAL / 2);
		assertEquals(Constants.UPDATE_INTERVAL / 2,
				controller.getTotalDeltaTime(), 0);
		controller.update(Constants.UPDATE_INTERVAL / 2);
		assertEquals(0, controller.getTotalDeltaTime(), 0);
	}
	
	@Test
	public void testCurrentImage(){
		for (int i=0; i<Constants.MAX_MOUSE_IMAGE; i++){
			assertEquals(i, controller.getCurrentImage());
			controller.update(Constants.UPDATE_INTERVAL);			
		}
	}
}
