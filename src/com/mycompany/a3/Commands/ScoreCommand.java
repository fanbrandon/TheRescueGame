package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ScoreCommand extends Command {
	private GameWorld gw;
	
	public ScoreCommand(GameWorld gw) {
		super("Score");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.score();
	}
}
