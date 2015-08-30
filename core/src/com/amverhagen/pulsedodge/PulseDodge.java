package com.amverhagen.pulsedodge;

import com.amverhagen.pulsedodge.playcomponents.BlockWaves;
import com.amverhagen.pulsedodge.playcomponents.SpriteLine;
import com.amverhagen.pulsedodge.playcomponents.Line;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PulseDodge extends ApplicationAdapter implements InputProcessor {
	private final int GAME_WORLD_WIDTH = 900;
	private final int GAME_WORLD_HEIGHT = 1600;
	private SpriteBatch batch;
	// private CircleLine circleLine;
	private Viewport viewport;
	private Camera camera;
	private Line dotLine;
	private ShapeRenderer shapeRenderer;
	private BlockWaves waves;
	private float time;
	private boolean reset;
	private float speed;

	@Override
	public void create() {
		time = 0;
		reset = true;
		camera = new OrthographicCamera();
		camera.position.set(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2, 0);
		viewport = new FitViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, camera);
		viewport.apply();
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		// circleLine = new CircleLine(5, GAME_WORLD_WIDTH / 5f, 0f,
		// GAME_WORLD_WIDTH / 12, GAME_WORLD_HEIGHT);
		dotLine = new Line();
		waves = new BlockWaves(0, 0, GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, 5);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2, 0);
	}

	@Override
	public void render() {
		time = time + Gdx.graphics.getDeltaTime();
		if (time > .25 && reset) {
			reset = false;
			waves.createBar();
		}
		if (time > .5) {
			reset = true;
			time = 0;
			waves.createWave();
			waves.createBar();
		}
		speed = 1 * Gdx.graphics.getDeltaTime() * 1000;

		// waves.updateLines(speed, circleLine.getCircle());

		// dotLine.AddDot(circleLine.getCircle().getX(),
		// circleLine.getCircleCenter());
		// dotLine.update(speed);

		camera.update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		// circleLine.getCircle().draw(batch);
		waves.draw(batch);
		batch.end();

		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.GREEN);
		// dotLine.draw(shapeRenderer);
		shapeRenderer.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
		// circleLine.getCircle().getTexture().dispose();
		waves.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.W) {
			// circleLine.moveUp();
		}
		if (keycode == Keys.S) {
			// circleLine.moveDown();
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (screenX > Gdx.graphics.getWidth() / 2) {
			// circleLine.moveUp();
		} else {
			// circleLine.moveDown();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
