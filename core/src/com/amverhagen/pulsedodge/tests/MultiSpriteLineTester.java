package com.amverhagen.pulsedodge.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.amverhagen.pulsedodge.lines.MultiSpriteLine;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MultiSpriteLineTester {
	float initX = 100;
	float initY = 100;
	int numberOfIndices = 10;
	int length = 400;
	int numberOfSprites = 10;
	Sprite sprite = new Sprite();

	@Test
	public void testMoveUp() {
		float initX = 0;
		float xDifference = 30;
		float xAfter = initX + xDifference;
		for (int i = -400; i < 400; i++) {
			MultiSpriteLine multiSpriteLine = new MultiSpriteLine(initX, 0f, i, 400, 10, sprite);
			multiSpriteLine.moveLineHorizontally(xDifference);
			assertEquals((double) xAfter, (double) multiSpriteLine.getVectorAtIndex(0).x, .001d);
		}
	}

	@Test
	public void testMoveDown() {
		float initY = 0;
		float yDifference = 30;
		float yAfter = initY + yDifference;
		for (int i = -400; i < 400; i++) {
			MultiSpriteLine multiSpriteLine = new MultiSpriteLine(0f, initY, i, 400, 10, sprite);
			multiSpriteLine.moveLineVertically(yDifference);
			assertEquals((double) yAfter, (double) multiSpriteLine.getVectorAtIndex(0).y, .001d);
		}
	}

	@Test
	public void testIfProperAmountOfSpritesAreMade() {
		MultiSpriteLine msl = new MultiSpriteLine(initX, initY, numberOfIndices, length, numberOfSprites, sprite);
		int count = 0;
		for (@SuppressWarnings("unused")
		Sprite s : msl.getSprites()) {
			count++;
		}
		assertEquals(numberOfSprites, count);
	}

	@Test
	public void testMultiSpriteMoveUp() {
		float yDiff = 100;
		float yAfter = initY + yDiff;
		MultiSpriteLine msl = new MultiSpriteLine(initX, initY, numberOfIndices, length, numberOfSprites, sprite);
		msl.moveLineVertically(yDiff);
		for (Sprite s : msl.getSprites()) {
			assertEquals((double) yAfter, (double) s.getY(), .001d);
		}
	}
}
