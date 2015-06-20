package com.amverhagen.pulsedodge.playcomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class CircleLine {
	private float[] indexPositions;
	private byte activeIndex;
	private Sprite circle;
	private byte indices;

	public CircleLine(byte indices, float x_pos, float y_pos, int width,
			int height) {
		this.indices = indices;
		float circleHeight = height / (indices * 3);
		activeIndex = (byte) (indices / 2);
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

//	public void update() {
//		// if (movingUp) {
//		// circle.translateY(Gdx.graphics.getDeltaTime() * 2000);
//		// if (circle.getY() >= indexPositions[activeIndex]) {
//		// circle.setY(indexPositions[activeIndex]);
//		// movingUp = false;
//		// }
//		// } else if (movingDown) {
//		// circle.translateY(-(Gdx.graphics.getDeltaTime() * 2000));
//		// if (circle.getY() <= indexPositions[activeIndex]) {
//		// circle.setY(indexPositions[activeIndex]);
//		// movingDown = false;
//		// }
//		// }
//	}

	public void moveUp() {
		if (activeIndex < indices - 1) {
			activeIndex++;
			circle.setY(indexPositions[activeIndex]);
		}
		// if (!movingDown && !movingUp) {
		// if (activeIndex < indices - 1) {
		// activeIndex++;
		// movingUp = true;
		// }
		// }

	}

	public void moveDown() {
		if (activeIndex > 0) {
			activeIndex--;
			circle.setY(indexPositions[activeIndex]);
		}
		// if (!movingUp && !movingDown) {
		// if (activeIndex > 0) {
		// activeIndex--;
		// movingDown = true;
		// }
		// }
	}

	public float getCircleCenter() {
		return circle.getY() + circle.getHeight() / 2;
	}

	public Sprite getCircle() {
		return circle;
	}
}
