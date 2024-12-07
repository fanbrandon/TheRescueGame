package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;


public class PauseCommand extends Command{
	private Game game;
	
	public PauseCommand(Game game) {
		super("Pause");
		
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		game.pauseGame();
	}
}
