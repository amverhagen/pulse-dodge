package com.amverhagen.pulsedodge.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.amverhagen.pulsedodge.PulseDodge;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 504;
		config.height = 896;
		new LwjglApplication(new PulseDodge(), config);
	}
}
