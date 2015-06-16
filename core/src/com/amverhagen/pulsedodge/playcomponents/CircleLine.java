package com.amverhagen.pulsedodge.playcomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class CircleLine {
	private int[] indexPositions;
	private int activeIndex;
	private Sprite circle;

	public CircleLine(int indices, int x_pos, int y_pos, int width, int height) {
		int circleWidth = width / 12;
		activeIndex = indices / 2;
		indexPositions = new int[indices];
		int sectionWidth = width / indices;
		for (int i = 0; i < indices; i++) {
			indexPositions[i] = (sectionWidth / 2) - circleWidth / 2
					+ (sectionWidth * i);
		}

		circle = new Sprite(new Texture(Gdx.files.internal("green_circle.png")));
		circle.setBounds(indexPositions[activeIndex], y_pos, circleWidth,
				circleWidth);
	}

	public void moveRight() {
		if (activeIndex < 4) {
			activeIndex++;
		}
		circle.setPosition(indexPositions[activeIndex], circle.getY());
	}

	public void moveLeft() {
		if (activeIndex > 0) {
			activeIndex--;
		}
		circle.setPosition(indexPositions[activeIndex], circle.getY());

	}

	public Sprite getCircle() {
		return circle;
	}
}
