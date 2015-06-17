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
		float circleWidth = height / (indices * 3);
		activeIndex = indices / 2;
		indexPositions = new float[indices];
		float sectionWidth = height / indices;
		for (int i = 0; i < indices; i++) {
			indexPositions[i] = (sectionWidth / 2) - circleWidth
					+ (sectionWidth * i);
		}

		circle = new Sprite(new Texture(Gdx.files.internal("green_circle.png")));
		circle.setBounds(x_pos, indexPositions[activeIndex], circleWidth,
				circleWidth);
	}

	public void moveUp() {
		if (activeIndex < 4) {
			activeIndex++;
		}
		circle.setY(indexPositions[activeIndex]);
	}

	public void moveDown() {
		if (activeIndex > 0) {
			activeIndex--;
		}
		circle.setY(indexPositions[activeIndex]);
	}

	public Sprite getCircle() {
		return circle;
	}
}
