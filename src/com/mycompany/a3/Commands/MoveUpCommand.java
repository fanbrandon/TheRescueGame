package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class MoveUpCommand extends Command{
	private GameWorld gw;
	
	public MoveUpCommand(GameWorld gw) {
		super("Move Up");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.moveUp();
	}
}
