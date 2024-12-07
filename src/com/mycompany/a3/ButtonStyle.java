package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.plaf.Border;

public class ButtonStyle extends Button {
	
	public ButtonStyle () {
			// this class allows a custom buttom to be create and set
			this.getAllStyles().setPadding(TOP, 5);
			this.getAllStyles().setPadding(BOTTOM, 5);
				
				// Add a 2 px border around the button
			this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(0, 0, 0)));
				
				
			this.getAllStyles().setBgTransparency(255);
				
				
			this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
				
				
			this.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
	}
}
