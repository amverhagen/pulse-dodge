package com.amverhagen.pulsedodge.playcomponents;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class SpriteLine {
	private int numberOfPositions;
	private Vector2[] positions;
	private int lineLength;
	private Sprite sprite;
	private boolean wrappable;
	private int currentPosition;

	public SpriteLine(int numberOfPositions, float xPos, float yPos, int length, Sprite sprite) {
		this.numberOfPositions = numberOfPositions;
		this.positions = new Vector2[numberOfPositions];
		this.lineLength = length;
		this.sprite = sprite;
		this.wrappable = false;
		this.currentPosition = numberOfPositions / 2;
		this.populatePositionsFromVector(new Vector2(xPos, yPos));
	}

	public void populatePositionsFromVector(Vector2 origin) {
		float positionDifference = lineLength / numberOfPositions;
		for (int i = 0; i < positions.length; i++) {
			float xPos = origin.x + (i * positionDifference) - (sprite.getWidth() / 2);
			float yPos = origin.y - (sprite.getHeight() / 2);
			positions[i] = new Vector2(xPos, yPos);
		}
	}

	public void moveForward() {
		if (wrappable) {
			if (currentPosition <= positions.length - 1) {
				currentPosition = 0;
			}
		} else {
			if (currentPosition < positions.length - 1) {
				currentPosition++;
			}
		}
		this.setSpritePosition();
	}

	public void moveBack() {
		if (wrappable) {
			if (currentPosition >= 0) {
				currentPosition = positions.length - 1;
			}
		} else {
			if (currentPosition > 0) {
				currentPosition--;
			}
		}
		this.setSpritePosition();
	}

	private void setSpritePosition() {
		sprite.setX(positions[currentPosition].x);
	}

	// public float getCircleCenter() {
	// return circle.getY() + circle.getHeight() / 2;
	// }
	//
	// public Sprite getCircle() {
	// return circle;
	// }
}
