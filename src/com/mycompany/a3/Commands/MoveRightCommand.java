package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class MoveRightCommand extends Command {
	private GameWorld gw;
	
	public MoveRightCommand(GameWorld gw) {
		super("Move Right");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.moveRight();
	}
}
