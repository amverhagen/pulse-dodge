package com.amverhagen.pulsedodge.lines;

import com.badlogic.gdx.math.Vector2;

public class IndexedLine {
	private Vector2[] positions;
	private Vector2 origin;
	protected int numberOfIndices;
	private int lineLength;

	public IndexedLine(float xPos, float yPos, int numberOfIndices, int length) throws IndexOutOfBoundsException {
		this.origin = new Vector2(xPos, yPos);
		if (numberOfIndices < 1)
			throw new IndexOutOfBoundsException("Index must be greater than or equal to 1.");
		this.numberOfIndices = numberOfIndices;
		this.lineLength = length;
		this.initPositions();
		this.setPositionsBasedOnOrigin();
	}

	private void initPositions() {
		positions = new Vector2[numberOfIndices];
		for (int i = 0; i < positions.length; i++) {
			positions[i] = new Vector2();
		}
	}

	private void setPositionsBasedOnOrigin() {
		if (numberOfIndices <= 0) {
			return;
		} else if (numberOfIndices == 1) {
			positions[0].x = origin.x;
			positions[0].y = origin.y;
		} else {
			float positionDifference = (float) lineLength / (numberOfIndices - 1);
			float xPos = origin.x;
			float yPos = origin.y;
			for (int i = 0; i < positions.length; i++) {
				xPos = origin.x + (i * positionDifference);
				positions[i].x = xPos;
				positions[i].y = yPos;
			}
		}
	}

	public void setOriginToVector(Vector2 vect) {
		origin.x = vect.x;
		origin.y = vect.y;
		setPositionsBasedOnOrigin();
	}

	public void moveLineHorizontally(float xDifference) {
		origin.x += xDifference;
		for (int i = 0; i < positions.length; i++) {
			positions[i].x += xDifference;
		}
	}

	public void moveLineVertically(float yDifference) {
		origin.y += yDifference;
		for (int i = 0; i < positions.length; i++) {
			positions[i].y += yDifference;
		}
	}

	public void rotateLine(double radians) {
		for (int i = 0; i < positions.length; i++) {
			double newX = Math.cos(radians) * (positions[i].x - origin.x)
					- (Math.sin(radians) * (positions[i].y - origin.y)) + origin.x;
			double newY = Math.sin(radians) * (positions[i].x - origin.x)
					+ Math.cos(radians) * (positions[i].y - origin.y) + origin.y;
			positions[i].set((float) newX, (float) newY);
		}
	}

	public Vector2 getVectorAtIndex(int location) {
		if (location <= 0 || location > positions.length - 1) {
			return origin;
		} else {
			return positions[location];
		}
	}
}
