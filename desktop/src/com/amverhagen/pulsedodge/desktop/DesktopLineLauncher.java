package com.amverhagen.pulsedodge.desktop;

import com.amverhagen.pulsedodge.LineTester;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLineLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Line Tester";
		cfg.width = 900;
		cfg.height = 800;

		new LwjglApplication(new LineTester(), cfg);
	}
}
