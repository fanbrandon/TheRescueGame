package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Label;

public class LabelStyle extends Label {
	
	// this creates a custom label to set as
	public LabelStyle (String name) {
		super(name);
		getAllStyles().setFgColor(ColorUtil.rgb(0,0,225));
	}
	
	public LabelStyle () {
		getAllStyles().setFgColor(ColorUtil.rgb(0,0,225));
	}
}
