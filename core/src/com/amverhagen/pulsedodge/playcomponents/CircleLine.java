package com.amverhagen.pulsedodge.playcomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class CircleLine {
	private float[] indexPositions;
	private int activeIndex;
	private Sprite circle;
	private int indices;

	public CircleLine(int indices, float x_pos, float y_pos, int width,
			int height) {
		this.indices = indices;
		float circleHeight = height / (indices * 3);
		activeIndex = indices / 2;
		indexPositions = new float[indices];
		float sectionHeight = height / indices;
		for (int i = 0; i < indices; i++) {
			indexPositions[i] = (sectionHeight / 2) - circleHeight / 2
					+ (sectionHeight * i);
		}

		circle = new Sprite(new Texture(Gdx.files.internal("green_circle.png")));
		circle.setBounds(x_pos, indexPositions[activeIndex], circleHeight,
				circleHeight);
	}

	public void moveUp() {
		if (activeIndex < indices-1) {
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

	public float getCircleCenter() {
		return circle.getY() + circle.getHeight() / 2;
	}

	public Sprite getCircle() {
		return circle;
	}
}
