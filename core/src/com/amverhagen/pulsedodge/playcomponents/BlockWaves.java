package com.amverhagen.pulsedodge.playcomponents;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Pool;

public class BlockWaves {
	private final ArrayList<Block> activeBlocks = new ArrayList<Block>();
	private final Pool<Block> blockPool = new Pool<Block>() {
		@Override
		protected Block newObject() {
			return new Block();
		}
	};
	private final ArrayList<WaveComponent> activeWaveComponents = new ArrayList<WaveComponent>();
	private final Pool<WaveComponent> componentsPool = new Pool<WaveComponent>() {
		@Override
		protected WaveComponent newObject() {
			return new WaveComponent();
		}
	};

	private Texture blockTexture;
	private Texture blurbTexture;
	private Texture barTexture;
	private float x;
	private float width;
	private int indices;
	private float height;
	private float initBlurbX;
	private float initBlurbY;
	private float blurbWidth;
	private float blockWidth;
	private float blockHeight;
	private float sectionHeight;
	private float barWidth;
	private TextureAtlas atlas;

	public BlockWaves(float x, float y, float width, float height, int indices) {
		this.x = x;
		this.width = width;
		this.indices = indices;
		this.height = height;
		sectionHeight = height / indices;
		blurbWidth = sectionHeight * .30f;
		blockWidth = width / 25;
		blockHeight = sectionHeight * .75f;
		barWidth = width / 100;
		initBlurbX = (x + width) + (blockWidth / 2) - (blurbWidth / 2);
		initBlurbY = ((sectionHeight / 2) - blurbWidth / 2);
		blockTexture = new Texture("small_green_block.png");
		blurbTexture = new Texture("green_blurb.png");
		barTexture = new Texture("tall_green_bar.png");
		atlas = new TextureAtlas(
				Gdx.files.internal("breaksprites.atlas"));
	}

	public void createBar() {
		WaveComponent bar = componentsPool.obtain();
		bar.init(x + width + (blockWidth / 2) - barWidth / 2, 0, barWidth,
				height, ComponentType.BAR);
		activeWaveComponents.add(bar);
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
				WaveComponent blurb = componentsPool.obtain();
				blurb.init(initBlurbX, (sectionHeight * i) + initBlurbY,
						blurbWidth, blurbWidth, ComponentType.BLURB);
				activeWaveComponents.add(blurb);

			} else if (i != blankPosition) {

				Block block = blockPool.obtain();
				block.init(x + width, (sectionHeight * i)
						+ (sectionHeight * .15f), blockWidth, blockHeight,
						atlas);
				activeBlocks.add(block);

				// WaveComponent b = componentsPool.obtain();
				// b.init(x + width, (sectionHeight * i) + (sectionHeight *
				// .15f),
				// blockWidth, blockHeight, ComponentType.BLOCK);
				// activeWaveComponents.add(b);

			}
		}
	}

	public void updateLines(float speed, Sprite circle) {

		for (Block block : activeBlocks) {
			if (circle.getX() < block.getX() + block.getWidth()
					&& circle.getX() + circle.getWidth() > block.getX()
					&& circle.getY() < block.getY() + block.getHeight()
					&& circle.getY() + circle.getHeight() > block.getY()) {
				block.setBreaking();
			}
			block.update(speed);
		}

		for (WaveComponent b : activeWaveComponents) {
			b.update(speed);
		}

		WaveComponent b;
		int len = activeWaveComponents.size();
		for (int i = len; --i >= 0;) {
			b = activeWaveComponents.get(i);
			if (b.isActive() == false) {
				activeWaveComponents.remove(i);
				componentsPool.free(b);
			}
		}

		Block block;
		len = activeBlocks.size();
		for (int i = len; --i >= 0;) {
			block = activeBlocks.get(i);
			if (block.isActive() == false) {
				activeBlocks.remove(i);
				blockPool.free(block);
			}
		}
	}

	public void draw(SpriteBatch batch) {

		for (Block block : activeBlocks) {
			batch.draw(block.getRegion(), block.getX(), block.getY(),
					block.getWidth(), block.getHeight());
		}
		for (WaveComponent b : activeWaveComponents) {
			if (b.getType() == ComponentType.BLOCK) {
				batch.draw(blockTexture, b.getX(), b.getY(), b.getWidth(),
						b.getHeight());
			} else if (b.getType() == ComponentType.BLURB) {
				batch.draw(blurbTexture, b.getX(), b.getY(), b.getWidth(),
						b.getHeight());
			} else if (b.getType() == ComponentType.BAR) {
				batch.draw(barTexture, b.getX(), b.getY(), b.getWidth(),
						b.getHeight());
			}
		}
	}

	public void dispose() {
		blurbTexture.dispose();
		blockTexture.dispose();
		barTexture.dispose();
		atlas.dispose();
	}
}
