package com.amverhagen.pulsedodge;

import java.util.ArrayList;

import com.amverhagen.pulsedodge.lines.MultiSpriteLine;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LineTester implements ApplicationListener {
	Sprite sprite;
	Sprite blockSprite;
	SpriteBatch batch;
	Texture blockTexture;
	Texture circleTexture;
	MultiSpriteLine blockLine;
	ArrayList<MultiSpriteLine> blockLines;

	@Override
	public void create() {
		circleTexture = new Texture(Gdx.files.internal("green_circle.png"));
		blockTexture = new Texture(Gdx.files.internal("small_green_block.png"));
		blockSprite = new Sprite(blockTexture);

		batch = new SpriteBatch();
		sprite = new Sprite(circleTexture);
		sprite.setSize(50, 100);
		blockSprite.setSize(50, 100);
		blockLines = new ArrayList<MultiSpriteLine>();
		blockLine = new MultiSpriteLine(100, 100, 7, 400, 5, sprite);
		blockLine.setWrappable(true);
		blockLines.add(blockLine);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (MultiSpriteLine msl : blockLines) {
			for (Sprite s : msl.getSpriteList()) {
				s.draw(batch);
			}
		}
		batch.end();
		if (Gdx.input.isKeyPressed(Keys.A)) {
			blockLine.moveLineHorizontally(-20);
		} else if (Gdx.input.isKeyPressed(Keys.D)) {
			blockLine.moveLineHorizontally(20);
		} else if (Gdx.input.isKeyPressed(Keys.W)) {
			blockLine.moveLineVertically(20);
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			blockLine.moveLineVertically(-20);
		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			blockLine.moveSpritesForward();
		}
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			blockLine.moveSpritesBackward();
		}
		if (Gdx.input.isTouched()) {
			MultiSpriteLine newLine = new MultiSpriteLine(Gdx.input.getX(), Gdx.input.getY(), 7, 400, 6, blockSprite);
			blockLines.add(newLine);
		}
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			for (MultiSpriteLine msl : blockLines) {
				msl.rotateLine(.1d);
			}
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
