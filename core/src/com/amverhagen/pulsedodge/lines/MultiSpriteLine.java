package com.amverhagen.pulsedodge.lines;

import java.util.ArrayList;

import com.amverhagen.pulsedodge.collections.BoundedIndexMap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class MultiSpriteLine extends IndexedLine {
	private BoundedIndexMap<Sprite> spriteMap;
	private int numberOfSprites;
	private Sprite sprite;

	public MultiSpriteLine(float xPos, float yPos, int numberOfIndices, int length, int numberOfSprites,
			Sprite sprite) {
		super(xPos, yPos, numberOfIndices, length);
		this.numberOfSprites = numberOfSprites;
		if (this.numberOfSprites > super.numberOfIndices)
			this.numberOfSprites = super.numberOfIndices;
		this.sprite = sprite;
		spriteMap = new BoundedIndexMap<Sprite>(super.numberOfIndices);
		populateSprites();
		bindSpritesToLine();
	}

	private void populateSprites() {
		for (int i = 0; i < numberOfSprites; i++) {
			spriteMap.putEntryAtIndex(i, new Sprite(sprite));
		}
	}

	private void bindSpritesToLine() {
		for (int i = 0; i < spriteMap.getMapSize(); i++) {
			if (spriteMap.hasValueAtIndex(i)) {
				Vector2 vector = super.getVectorAtIndex(i);
				spriteMap.getValueAtIndex(i).setPosition(vector.x, vector.y);
			}
		}
	}

	public void setWrappable(boolean value) {
		spriteMap.wrappable = value;
	}

	public void moveSpritesForward() {
		spriteMap.shiftAllEntriesUpOneIndex();
		bindSpritesToLine();
	}

	public void moveSpritesBackward() {
		spriteMap.shiftAllEntriesDownOneIndex();
		bindSpritesToLine();
	}

	public void randomlyPlaceSprites() {
	}

	public void moveSpritesToCenter() {

	}

	public ArrayList<Sprite> getSpriteList() {
		return spriteMap.getAllEntriesAsList();
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

	@Override
	public void rotateLine(double radians) {
		super.rotateLine(radians);
		bindSpritesToLine();
	}
}
