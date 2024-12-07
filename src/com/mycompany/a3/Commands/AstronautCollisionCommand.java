package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AstronautCollisionCommand extends Command {
	
	private GameWorld gw;
	
	public AstronautCollisionCommand(GameWorld gw) {
		
		super("Fight");
		
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.astronautCollision();
	}
}
