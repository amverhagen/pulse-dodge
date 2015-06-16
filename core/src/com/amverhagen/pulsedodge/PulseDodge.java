package com.amverhagen.pulsedodge;

import com.amverhagen.pulsedodge.playcomponents.CircleLine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PulseDodge extends ApplicationAdapter implements InputProcessor {
	private final int GAME_WORLD_WIDTH = 900;
	private final int GAME_WORLD_HEIGHT = 1600;
	private SpriteBatch batch;
	private CircleLine circleLine;
	private Viewport viewport;
	private Camera camera;
	private Sprite background;

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.position.set(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2, 0);
		viewport = new FitViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, camera);
		viewport.apply();
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		background = new Sprite(new Texture(Gdx.files.internal("background.png")));
		background.setPosition(0, 0);
		background.setSize(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);
		circleLine = new CircleLine(5, 0f, GAME_WORLD_HEIGHT / 3f,
				GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2,0);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 1, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		background.draw(batch);
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
