package com.amverhagen.pulsedodge.playcomponents;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class WaveComponent implements Poolable {

	private Vector2 position;
	private float width;
	private float height;
	private boolean active;
	private ComponentType type;

	public WaveComponent() {
		position = new Vector2();
		active = false;
		width = 0;
		height = 0;
	}

	public void init(float posX, float poxY, float width, float height,
			ComponentType type) {
		position.set(posX, poxY);
		this.width = width;
		this.height = height;
		this.type = type;
		active = true;
	}

	@Override
	public void reset() {
		position.set(0, 0);
		width = 0;
		height = 0;
		active = false;
	}

	public void update(float speed) {
		position.add(-(speed), 0);

		if (position.x < -100) {
			active = false;
		}
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

	public ComponentType getType() {
		return type;
	}

}
