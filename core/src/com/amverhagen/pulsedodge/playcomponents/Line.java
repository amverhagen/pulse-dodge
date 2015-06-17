package com.amverhagen.pulsedodge.playcomponents;

import java.util.ArrayList;

public class Line {

	private ArrayList<Dot> dots;
	private boolean dotCreated;

	public Line() {
		dots = new ArrayList<Dot>();
		dotCreated = false;
	}

	public void AddDot(float x, float y) {
		dotCreated = false;
		for (Dot d : dots) {
			if (d.getX() < 0) {
				d.setXY(x, y);
				dotCreated = true;
			}
		}
		if (!dotCreated) {
			Dot dot = new Dot(x, y);
			dots.add(dot);
		}
	}

	public void update(float speed) {
		for (Dot d : dots) {
			d.setX(d.getX() - speed);
		}
	}

	public ArrayList<Dot> getDots() {
		return dots;
	}
}
