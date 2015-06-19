package com.amverhagen.pulsedodge.playcomponents;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Line {

	private Dot firstDot;
	private Dot lastDot;
	private int size;

	public Line() {
		size = 0;
	}

	public void AddDot(float x, float y) {
		if (size == 0) {
			Dot dot = new Dot(x, y);
			firstDot = dot;
			lastDot = dot;
			size++;
		} else {
			Dot dot = new Dot(x, y);
			dot.setPrevious(lastDot);
			lastDot.setNext(dot);
			lastDot = dot;
			size++;
			if (firstDot.getX() < -10) {
				Dot newFirst = firstDot.getNext();
				newFirst.setPrevious(null);
				firstDot = null;
				firstDot = newFirst;
				size--;
			}
		}
	}

	public void update(float speed) {
		Dot current = firstDot;
		while (current.hasNext()) {
			current.setX(current.getX() - speed);
			current = current.getNext();
		}
	}

	public void draw(ShapeRenderer renderer) {
		Dot current = firstDot;
		while (current.hasNext()) {
			// renderer.point(current.getX(), current.getY(), 0);
			renderer.rectLine(current.getX(), current.getY(), current.getNext()
					.getX(), current.getNext().getY(), 3);
			current = current.getNext();
		}
	}
}
