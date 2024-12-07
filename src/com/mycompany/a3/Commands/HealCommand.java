package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;



public class HealCommand extends Command{
	
	private GameWorld gw;
	
	public HealCommand(GameWorld gw) {
		super("Heal");
		
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (gw.getPaused()) {
			gw.heal();
		}
	}
}
