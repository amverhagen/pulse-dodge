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
	SpriteBatch batch;
	Texture blockTexture;
	MultiSpriteLine blockLine;
	ArrayList<MultiSpriteLine> blockLines;

	@Override
	public void create() {
		blockTexture = new Texture(Gdx.files.internal("small_green_block.png"));
		batch = new SpriteBatch();
		sprite = new Sprite(blockTexture);
		sprite.setSize(50, 100);
		blockLine = new MultiSpriteLine(100, 100, 7, 400, 6, sprite);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Sprite s : blockLine.getSpriteList()) {
			s.draw(batch);
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
