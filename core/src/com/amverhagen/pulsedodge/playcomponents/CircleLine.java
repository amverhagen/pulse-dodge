package com.amverhagen.pulsedodge.playcomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CircleLine {
	private Sprite[] circles;
	private int circleAmount;
	private int activeIndex;
	private Texture activeCircle;
	private Texture emptyCircle;

	public CircleLine(int circleAmount, int x_pos, int y_pos, int width,
			int height) {
		System.out.println("xpos: " + x_pos + ", y_pos: " + y_pos + ", width: "
				+ width + ", height: " + height);
		circles = new Sprite[circleAmount];
		this.circleAmount = circleAmount;
		int sectionWidth = width / circleAmount;
		System.out.println("Section width: " + sectionWidth);
		int circleWidth = sectionWidth / 3;
		System.out.println("Circle width: " + circleWidth);
		activeIndex = circleAmount / 2;
		System.out.println("Active index: " + activeIndex);
		activeCircle = new Texture(Gdx.files.internal("white_sphere.png"));
		emptyCircle = new Texture(Gdx.files.internal("empty_black.png"));
		int circleX = sectionWidth / 2 - circleWidth / 2;
		for (int i = 0; i < circleAmount; i++) {
			System.out.println("Index: " + i + ", circleX " + circleX);
			circles[i] = new Sprite(emptyCircle);
			circles[i].setBounds(circleX, y_pos, circleWidth, circleWidth);
			circleX += sectionWidth;
			System.out.println("Index: " + i + ", xpos: " + circles[i].getX());
		}
		circles[activeIndex].setTexture(activeCircle);
	}

	public void moveRight() {

	}

	public void moveLeft() {

	}

	public Sprite getActiveCircle() {
		return null;
	}

	public void draw(SpriteBatch batch) {
		for (int i = 0; i < circleAmount; i++) {
			batch.draw(circles[i].getTexture(), circles[i].getX(),
					circles[i].getY(), circles[i].getWidth(),
					circles[i].getHeight());
		}
	}
}
