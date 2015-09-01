package com.amverhagen.pulsedodge.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.amverhagen.pulsedodge.lines.MultiSpriteLine;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MultiSpriteLineTester {
	float initX = 100;
	float initY = 100;
	int numberOfIndices = 100;
	int length = 400;
	int numberOfSprites = 10;
	Sprite sprite = new Sprite();

	@Test
	public void testMoveHorizontally() {
		float initX = 0;
		float xDifference = 30;
		float xAfter = initX + xDifference;
		for (int i = 1; i < 400; i++) {
			MultiSpriteLine multiSpriteLine = new MultiSpriteLine(initX, 0f, i, 400, 10, sprite);
			multiSpriteLine.moveLineHorizontally(xDifference);
			assertEquals((double) xAfter, (double) multiSpriteLine.getVectorAtIndex(0).x, .001d);
		}
	}

	@Test
	public void testMoveVertically() {
		float initY = 0;
		float yDifference = 30;
		float yAfter = initY + yDifference;
		for (int i = 1; i < 400; i++) {
			MultiSpriteLine multiSpriteLine = new MultiSpriteLine(0f, initY, i, 400, 10, sprite);
			multiSpriteLine.moveLineVertically(yDifference);
			assertEquals((double) yAfter, (double) multiSpriteLine.getVectorAtIndex(0).y, .001d);
		}
	}

	@Test
	public void testIfProperAmountOfSpritesAreMade() {
		MultiSpriteLine msl = new MultiSpriteLine(initX, initY, numberOfIndices, length, numberOfSprites, sprite);
		int countArray = 0;
		for (@SuppressWarnings("unused")
		Sprite s : msl.getSpriteList()) {
			countArray++;
		}
		assertEquals(numberOfSprites, countArray);
	}

	@Test
	public void testIfSpritesMatchLineAfterMoveVertical() {
		float yDiff = 100;
		float yAfter = initY + yDiff;
		for (int i = 0; i < 100; i++) {
			MultiSpriteLine msl = new MultiSpriteLine(initX, initY, numberOfIndices, length, i, sprite);
			msl.moveLineVertically(yDiff);
			for (Sprite s : msl.getSpriteList()) {
				assertEquals((double) yAfter, (double) s.getY(), .001d);
			}
		}
	}

	@Test
	public void testIfSpritesMatchLineAfterMoveHorizontal() {
		float xDiff = 100;
		for (int i = 0; i < 100; i++) {
			MultiSpriteLine msl = new MultiSpriteLine(initX, initY, numberOfIndices, length, i, sprite);
			float[] positions = new float[msl.getSpriteList().size()];
			int loc = 0;
			for (Sprite s : msl.getSpriteList()) {
				positions[loc] = s.getX() + xDiff;
				loc++;
			}
			msl.moveLineHorizontally(xDiff);
			loc = 0;
			for (Sprite s : msl.getSpriteList()) {
				assertEquals((double) positions[loc], (double) s.getX(), .001d);
				loc++;
			}
		}
	}

}
