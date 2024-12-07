package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ContractCommand extends Command {
	
	private GameWorld gw;
	
	public ContractCommand(GameWorld gw) {
		super("Contract Door");
		
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.contract();
	}
}
