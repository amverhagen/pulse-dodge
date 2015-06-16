package com.amverhagen.pulsedodge;

import com.amverhagen.pulsedodge.playcomponents.CircleLine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PulseDodge extends ApplicationAdapter implements InputProcessor {
	private SpriteBatch batch;
	private CircleLine circleLine;

	@Override
	public void create() {
		batch = new SpriteBatch();
		circleLine = new CircleLine(5, 0, Gdx.graphics.getHeight() / 3,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 12);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		circleLine.getCircle().draw(batch);
		batch.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.D) {
			circleLine.moveRight();
		}
		if (keycode == Keys.A) {
			circleLine.moveLeft();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
