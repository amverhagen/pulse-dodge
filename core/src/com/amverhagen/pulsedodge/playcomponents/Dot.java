package com.amverhagen.pulsedodge.playcomponents;

public class Dot {

	private float x_pos;
	private float y_pos;

	public Dot(float x, float y) {
		x_pos = x;
		y_pos = y;
	}

	public float getX() {
		return x_pos;
	}

	public float getY() {
		return y_pos;
	}

	public void setX(float x) {
		x_pos = x;
	}

	public void setXY(float x, float y) {
		x_pos = x;
		y_pos = y;
	}
}
