package com.amverhagen.pulsedodge.playcomponents;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Line {

	private Dot firstDot;
	private Dot lastDot;
	private short size;

	public Line() {
		size = 0;
	}

	public void AddDot(float x, float y) {
		if (size == 0) {
			Dot dot = new Dot(x, y);
			firstDot = dot;
			lastDot = dot;
			size++;
		} else if (firstDot.getX() < -10) {
			if (firstDot == lastDot) {
				firstDot.setXY(x, y);
			} else {
				Dot dot = new Dot(x, y);
				dot.setNext(lastDot);
				lastDot.setPrevious(dot);
				lastDot = dot;
				firstDot = firstDot.getPrevious();
				firstDot.setNext(null);
			}
		} else {
			Dot dot = new Dot(x, y);
			dot.setNext(lastDot);
			lastDot.setPrevious(dot);
			lastDot = dot;
			size++;
		}
	}

	public void update(float speed) {
		Dot current = firstDot;
		while (current.hasPrevious()) {
			current.setX(current.getX() - speed);
			current = current.getPrevious();
		}
	}

	public void draw(ShapeRenderer renderer) {
		Dot current = firstDot;
		while (current.hasPrevious()) {
			renderer.rectLine(current.getX(), current.getY(), current
					.getPrevious().getX(), current.getPrevious().getY(), 3);
			current = current.getPrevious();
		}
	}
}
