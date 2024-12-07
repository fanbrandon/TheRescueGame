package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {
	
	public AboutCommand() {
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Dialog.show("About", "Welcome to The Rescue Game\n Professor Muyan\n Author: Brandon Fan\n Assignment 3", "OK", null);
	}
}
