package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ExpandCommand extends Command {

	private GameWorld gw;
	
	public ExpandCommand(GameWorld gw) {
		super("Expand Door");
		this.gw = gw;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.expand();
	}
}
