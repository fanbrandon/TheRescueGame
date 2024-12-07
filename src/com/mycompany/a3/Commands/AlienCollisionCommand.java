package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AlienCollisionCommand extends Command {
	
	private GameWorld gw;
	
	public AlienCollisionCommand(GameWorld gw) {
		super("New Alien");
		
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.alienCollision();
	}
}
