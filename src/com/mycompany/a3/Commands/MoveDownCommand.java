package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class MoveDownCommand extends Command {
	private GameWorld gw;
	
	public MoveDownCommand(GameWorld gw) {
		super("Move Down");
		
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.moveDown();
	}
}
