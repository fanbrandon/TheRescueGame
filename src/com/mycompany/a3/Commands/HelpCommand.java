package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command {
	
	public HelpCommand() {
		super("Help");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Dialog.show("Help", "E: Expand Door\n C: Contract Door\n L: Move Left\n R: Move Right\n U: Move Up\n"
				+ "D: Move Down\n S: Score\n A: Go to Alien\n O: Go to Astronaut\n W: New Alien\n F: Fight\n T: Tick Game\n", "OK", null);
	}
}
