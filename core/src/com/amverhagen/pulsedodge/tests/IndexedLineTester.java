package com.amverhagen.pulsedodge.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.amverhagen.pulsedodge.lines.IndexedLine;

public class IndexedLineTester {

	@Test
	public void testMoveHorizontally() {
		float initX = 0;
		float xDifference = 30;
		float xAfter = initX + xDifference;
		for (int i = 1; i < 400; i++) {
			IndexedLine iL = new IndexedLine(initX, 0f, i, 400);
			iL.moveLineHorizontally(xDifference);
			assertEquals((double) xAfter, (double) iL.getVectorAtIndex(i).x, .001d);
		}
	}

	@Test
	public void testMoveVertically() {
		float initY = 0;
		float yDifference = 30;
		float yAfter = initY + yDifference;
		for (int i = 1; i < 400; i++) {
			IndexedLine iL = new IndexedLine(0f, 0f, i, 400);
			iL.moveLineVertically(yDifference);
			assertEquals((double) yAfter, (double) iL.getVectorAtIndex(i).y, .001d);
		}
	}

	@Test
	public void testDistancesBetweenPoints() {
		float initX = 0;
		float initY = 0;
		int indices = 4;

		for (int i = 0; i < 10000; i++) {
			float distance = (float) i / (indices - 1);
			IndexedLine iL = new IndexedLine(initX, initY, indices, i);
			for (int j = 0; j < indices - 1; j++) {
				float indiceDifference = iL.getVectorAtIndex(j + 1).x - iL.getVectorAtIndex(j).x;
				assertEquals(distance, indiceDifference, .001);
			}
		}
	}
}
