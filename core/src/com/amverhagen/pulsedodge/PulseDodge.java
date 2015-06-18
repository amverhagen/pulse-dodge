package com.amverhagen.pulsedodge;

import com.amverhagen.pulsedodge.playcomponents.CircleLine;
import com.amverhagen.pulsedodge.playcomponents.Line;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PulseDodge extends ApplicationAdapter implements InputProcessor {
	private final int GAME_WORLD_WIDTH = 1600;
	private final int GAME_WORLD_HEIGHT = 900;
	private SpriteBatch batch;
	private CircleLine circleLine;
	private Viewport viewport;
	private Camera camera;
	private Line dotLine;
	private Sprite background;
	private ShapeRenderer shapeRenderer;
	private float speed;

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.position.set(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2, 0);
		viewport = new FitViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, camera);
		viewport.apply();
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		background = new Sprite(new Texture(
				Gdx.files.internal("background.png")));
		background.setPosition(0, 0);
		background.setSize(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);
		circleLine = new CircleLine(5, GAME_WORLD_WIDTH / 3f, 0f,
				GAME_WORLD_WIDTH / 12, GAME_WORLD_HEIGHT);
		dotLine = new Line();

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2, 0);
	}

	@Override
	public void render() {
		speed = 1000 * Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 1, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		circleLine.update();

		dotLine.AddDot(circleLine.getCircle().getX(),
				circleLine.getCircleCenter());
		dotLine.update(speed);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		background.draw(batch);
		circleLine.getCircle().draw(batch);
		batch.end();

		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		Gdx.gl.glLineWidth(6);
		shapeRenderer.setColor(Color.GREEN);
		dotLine.draw(shapeRenderer);
		shapeRenderer.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.W) {
			circleLine.moveUp();
		}
		if (keycode == Keys.S) {
			circleLine.moveDown();
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (screenX > Gdx.graphics.getWidth() / 2) {
			circleLine.moveUp();
		} else {
			circleLine.moveDown();
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
