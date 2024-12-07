package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class MoveLeftCommand extends Command {
	private GameWorld gw;
	
	public MoveLeftCommand(GameWorld gw) {
		super("Move Left");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.moveLeft();
	}
}
