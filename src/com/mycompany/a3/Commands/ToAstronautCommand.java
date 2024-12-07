package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ToAstronautCommand extends Command {
	private GameWorld gw;
	
	public ToAstronautCommand(GameWorld gw) {
		super("Move To Astronaut");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.toAstronaut();
	}
}
