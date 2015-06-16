package com.amverhagen.pulsedodge.playcomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class CircleLine {
	private float[] indexPositions;
	private int activeIndex;
	private Sprite circle;

	public CircleLine(int indices, float x_pos, float y_pos, int width,
			int height) {
		float circleWidth = (height/width) * 100f;
		activeIndex = indices / 2;
		indexPositions = new float[indices];
		float sectionWidth = width / indices;
		for (int i = 0; i < indices; i++) {
			indexPositions[i] = (sectionWidth / 2) - 75 / 2
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
