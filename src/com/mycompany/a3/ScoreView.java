package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	
	private GameWorld gw;
	
	private Label clock;
	private Label score;
	private Label astronautsRescued;
	private Label alienSneaked;
	private Label astronautsLeft;
	private Label alienLeft;
	private Label sound;
	
	public ScoreView(GameWorld gw) {
		this.gw = gw;
		
		this.setLayout(new FlowLayout(Component.CENTER));
		
		// Create labels for the score
		Label clockLabel = new LabelStyle("Time: ");
		clock = new Label();
		
		this.add(clockLabel);
		this.add(clock);
		
		Label scoreLabel = new LabelStyle("Score: ");
		score = new Label();
		
		this.add(scoreLabel);
		this.add(score);
		
		Label astronautsRescuedLabel = new LabelStyle("Astronauts Rescued: ");
		astronautsRescued = new Label();
		
		this.add(astronautsRescuedLabel);
		this.add(astronautsRescued);
		
		
		Label alienSneakedLabel = new LabelStyle("Aliens Sneaked in: ");
		alienSneaked = new Label();
		
		this.add(alienSneakedLabel);
		this.add(alienSneaked);
		
		Label astronautsLeftLabel = new LabelStyle("Astronauts Remaining: ");
		astronautsLeft = new Label();
		
		this.add(astronautsLeftLabel);
		this.add(astronautsLeft);
		
		Label alienLeftLabel = new LabelStyle("Aliens Remaining: ");
		alienLeft = new Label();
		
		this.add(alienLeftLabel);
		this.add(alienLeft);
		
		Label soundLabel = new LabelStyle("Sound: ");
		sound = new Label();
		this.add(soundLabel);
		this.add(sound);
	}

	@Override
	public void update(Observable observable, Object data) { // use getters in gameWorld to get correct values to display
		clock.setText(" " + gw.getClock()/50);
		clock.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 255));
		
		score.setText(" " + gw.getScore());
		score.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 255));
		
		astronautsLeft.setText(" " + gw.getAstronautCount());
		astronautsLeft.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 255));
		
		alienLeft.setText(" " + gw.getAlienCount());
		alienLeft.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 255));
		
		alienSneaked.setText(" " + gw.getAlienSneak());
		alienSneaked.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 255));
		
		astronautsRescued.setText(" " + gw.getAstroRescued());
		astronautsRescued.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 255));
		
		if (gw.getSound()) {
			sound.setText("ON");
			sound.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 255));
		}
		else {
			sound.setText("OFF");
			sound.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 255));
		}
		this.revalidate();
		
	}
}
