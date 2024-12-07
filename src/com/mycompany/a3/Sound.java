package com.mycompany.a3;

import com.codename1.ui.Display;
import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;


public class Sound {
	private Media m;
	
	public Sound (String filename) {
		
		if (Display.getInstance().getCurrent() == null) {
			System.out.println("Error: Create sound objects");
			System.exit(0);
		}
		
		while (m == null) {
			try {
				InputStream in = Display.getInstance().getResourceAsStream(getClass(), "/" + filename);
				
				m = MediaManager.createMedia(in, "audio/wav");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void pause() {
		m.pause();
	}
	
	public void play() {
		m.setTime(0);
		m.play();
	}
}
