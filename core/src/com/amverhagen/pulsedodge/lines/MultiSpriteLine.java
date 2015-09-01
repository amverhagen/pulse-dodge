package com.amverhagen.pulsedodge.lines;

import java.util.ArrayList;

import com.amverhagen.pulsedodge.collections.BoundedIndexMap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class MultiSpriteLine extends IndexedLine {
	private BoundedIndexMap<Sprite> spriteMap;
	private int numberOfSprites;
	private Sprite sprite;
	private boolean wrappable;

	public MultiSpriteLine(float xPos, float yPos, int numberOfIndices, int length, int numberOfSprites,
			Sprite sprite) {
		super(xPos, yPos, numberOfIndices, length);
		this.numberOfSprites = numberOfSprites;
		spriteMap = new BoundedIndexMap<Sprite>(super.numberOfIndices);
		if (this.numberOfSprites > super.numberOfIndices)
			this.numberOfSprites = super.numberOfIndices;
		this.wrappable = false;
		this.sprite = sprite;
		populateSprites();
		bindSpritesToLine();
	}

	private void populateSprites() {
		for (int i = 0; i < numberOfSprites; i++) {
			spriteMap.put(i, new Sprite(sprite));
		}
	}

	private void bindSpritesToLine() {
		for (int i = 0; i < spriteMap.getSize(); i++) {
			if (spriteMap.hasValue(i)) {
				Vector2 vector = super.getVectorAtIndex(i);
				spriteMap.get(i).setPosition(vector.x, vector.y);
			}
		}
	}

	public void setWrappable(boolean value) {
		wrappable = value;
	}

	public void moveSpritesForward() {
		Sprite end = spriteMap.get(spriteMap.getSize() - 1);
		if (!wrappable && end != null) {
			return;
		}
		for (int i = spriteMap.getSize() - 2; i >= 0; i--) {
			Sprite temp = spriteMap.remove(i);
			spriteMap.put((i + 1), temp);
		}
		spriteMap.put(0, end);
		bindSpritesToLine();
	}

	public void moveSpritesBackward() {
		Sprite first = spriteMap.get(0);
		if (!wrappable && first != null) {
			return;
		}
		for (int i = 1; i < spriteMap.getSize(); i++) {
			Sprite temp = spriteMap.remove(i);
			spriteMap.put((i - 1), temp);
		}
		spriteMap.put(spriteMap.getSize() - 1, first);
		bindSpritesToLine();
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

	public ArrayList<Sprite> getSpriteList() {
		return spriteMap.getEntries();
	}
}
