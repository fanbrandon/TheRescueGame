package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TickCommand extends Command {
	private GameWorld gw;
	
	public TickCommand(GameWorld gw) {
		super("Tick");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.tick();
	}
}
