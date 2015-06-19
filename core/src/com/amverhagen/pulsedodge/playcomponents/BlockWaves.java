package com.amverhagen.pulsedodge.playcomponents;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlockWaves {

	private ArrayList<Sprite> blocks;
	private ArrayList<Sprite> blurbs;
	private Texture blockTexture;
	private Texture blurbTexture;
	private float x;
	private float width;
	private int indices;
	private float blurbX;
	private float blurbWidth;
	private float blockWidth;
	private float blockHeight;
	private float sectionHeight;

	public BlockWaves(float x, float y, float width, float height, int indices) {
		this.x = x;
		this.width = width;
		this.indices = indices;
		sectionHeight = height / indices;
		blurbWidth = sectionHeight * .30f;
		blockWidth = width / 15;
		blockHeight = sectionHeight * .75f;
		blurbX = (x + width) + (blockWidth / 2) - (blurbWidth / 2);
		blockTexture = new Texture("green_block.png");
		blurbTexture = new Texture("green_blurb.png");
		blurbs = new ArrayList<Sprite>();
		blocks = new ArrayList<Sprite>();
	}

	public void createWave() {
		if (indices < 1) {
			return;
		}
		int blurbPosition = (int) Math.floor(Math.random() * indices);
		int blankPosition = (int) Math.floor(Math.random() * indices);
		while (blurbPosition == blankPosition) {
			blankPosition = (int) Math.floor(Math.random() * indices);
		}
		for (int i = 0; i < indices; i++) {
			if (i == blurbPosition) {
				Sprite blurb = null;
				for (Sprite s : blurbs) {
					if (s.getX() < -10) {
						blurb = s;
						break;
					}
				}
				if (blurb == null) {
					blurb = new Sprite(blurbTexture);
					blurb.setSize(blurbWidth, blurbWidth);
					blurbs.add(blurb);
				}
				blurb.setPosition(blurbX, (sectionHeight * i)
						+ ((sectionHeight / 2) - (blurbWidth / 2)));

			} else if (i != blankPosition) {

				Sprite block = null;
				for (Sprite s : blocks) {
					if (s.getX() < -10) {
						block = s;
						break;
					}
				}
				if (block == null) {
					block = new Sprite(blockTexture);
					block.setSize(blockWidth, blockHeight);
					blocks.add(block);
				}
				block.setPosition(x + width, (sectionHeight * i)
						+ (sectionHeight * .15f));
			}
		}
	}

	public void updateLines(float speed) {
		for (Sprite s : blurbs) {
			s.setX(s.getX() - speed);
		}
		for (Sprite s : blocks) {
			s.setX(s.getX() - speed);
		}
	}

	public void draw(SpriteBatch batch) {
		for (Sprite s : blurbs) {
			s.draw(batch);
		}
		for (Sprite s : blocks) {
			s.draw(batch);
		}
	}

}
