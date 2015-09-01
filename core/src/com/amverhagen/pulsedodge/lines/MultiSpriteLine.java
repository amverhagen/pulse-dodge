package com.amverhagen.pulsedodge.lines;

import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class MultiSpriteLine extends IndexedLine {
	private HashMap<Integer, Sprite> map;
	private int numberOfSprites;
	private Sprite sprite;
	private boolean wrappable;

	public MultiSpriteLine(float xPos, float yPos, int numberOfIndices, int length, int numberOfSprites,
			Sprite sprite) {
		super(xPos, yPos, numberOfIndices, length);
		this.numberOfSprites = numberOfSprites;
		if (this.numberOfSprites > super.numberOfIndices) {
			this.numberOfSprites = super.numberOfIndices;
		}
		this.wrappable = false;
		this.sprite = sprite;
		populateMap();
		bindSpritesToLine();
	}

	private void populateMap() {
		map = new HashMap<Integer, Sprite>();
		for (int i = 0; i < numberOfSprites; i++) {
			map.put(i, new Sprite(sprite));
		}
	}

	private void bindSpritesToLine() {
		for (int i = 0; i < super.numberOfIndices; i++) {
			Sprite temp = map.get(i);
			if (temp != null) {
				Vector2 vector = super.getVectorAtIndex(i);
				temp.setPosition(vector.x, vector.y);
			}
		}
	}

	public void setWrappable(boolean value) {
		wrappable = value;
	}

	public void moveSpritesForward() {
		if (wrappable) {
		} else {

		}
	}

	public void moveSpritesBackward() {
		if (wrappable) {
		} else {
		}
	}

	public void randomlyPlaceSprites() {
	}

	public void moveSpritesToCenter() {

	}

	@Override
	public void moveLineHorizontally(float xDifference) {
		super.moveLineHorizontally(xDifference);
		bindSpritesToLine();
	}

	@Override
	public void moveLineVertically(float yDifference) {
		super.moveLineVertically(yDifference);
		bindSpritesToLine();
	}

	public Collection<Sprite> getSprites() {
		return map.values();
	}
}
