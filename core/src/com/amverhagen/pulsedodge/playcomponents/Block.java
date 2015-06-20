package com.amverhagen.pulsedodge.playcomponents;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Block implements Poolable {
	private boolean active;
	private boolean breaking;
	private Vector2 position;
	private float width;
	private float height;
	private TextureAtlas atlas;
	private TextureRegion region;
	private short currentFrame;
	private final short MAX_FRAMES = 4;

	public Block() {
		active = false;
		breaking = false;
		position = new Vector2();
		width = 0;
		height = 0;
		currentFrame = 0;
		region = null;
	}

	public void init(float posX, float poxY, float width, float height,
			TextureAtlas atlas) {
		position.set(posX, poxY);
		this.width = width;
		this.height = height;
		this.atlas = atlas;
		region = atlas.findRegion("bk0");
		active = true;
	}

	public void update(float speed) {
		position.add(-(speed), 0);
		if (breaking) {
			if (currentFrame > MAX_FRAMES) {
				breaking = false;
				active = false;
			} else {
				region = atlas.findRegion("bk" + currentFrame);
				currentFrame++;
			}
		}
		if (position.x < -100) {
			active = false;
		}
	}

	public TextureRegion getRegion() {
		return region;
	}

	public void setBreaking() {
		breaking = true;
	}

	public boolean isBreaking() {
		return breaking;
	}

	public void setUnactive() {
		active = false;
	}

	public boolean isActive() {
		return active;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		active = false;
		breaking = false;
		position.set(0, 0);
		width = 0;
		height = 0;
		region = null;
		currentFrame = 0;

	}

}
