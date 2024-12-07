package com.mycompany.a3;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.Commands.AboutCommand;
import com.mycompany.a3.Commands.AlienCollisionCommand;
import com.mycompany.a3.Commands.AstronautCollisionCommand;
import com.mycompany.a3.Commands.ContractCommand;
import com.mycompany.a3.Commands.ExitCommand;
import com.mycompany.a3.Commands.ExpandCommand;
import com.mycompany.a3.Commands.HealCommand;
import com.mycompany.a3.Commands.HelpCommand;
import com.mycompany.a3.Commands.MoveDownCommand;
import com.mycompany.a3.Commands.MoveLeftCommand;
import com.mycompany.a3.Commands.MoveRightCommand;
import com.mycompany.a3.Commands.MoveUpCommand;
import com.mycompany.a3.Commands.PauseCommand;
import com.mycompany.a3.Commands.ScoreCommand;
import com.mycompany.a3.Commands.SoundCommand;
import com.mycompany.a3.Commands.TickCommand;
import com.mycompany.a3.Commands.ToAlienCommand;
import com.mycompany.a3.Commands.ToAstronautCommand;
import com.codename1.ui.Toolbar;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	private Button pauseButton = new ButtonStyle();
	private Button healButton = new ButtonStyle();
	private Button expandButton = new  ButtonStyle();
	private Button upButton = new  ButtonStyle();
	private Button leftButton = new  ButtonStyle();
	private Button moveToAstroButton = new  ButtonStyle();
	private CheckBox soundCheckBox = new CheckBox(); // create sound checkbox
	private Button contractButton = new  ButtonStyle();
	private Button downButton = new  ButtonStyle();
	private Button rightButton = new  ButtonStyle();
	private Button moveToAlienButton = new  ButtonStyle();
	private Button scoreButton = new  ButtonStyle();
	private Toolbar toolBar = new Toolbar();
	
	private AboutCommand aboutCmd = new AboutCommand(); // create all commands for the previous key inputs
	private ExitCommand exitCmd = new ExitCommand();
	private HelpCommand helpCmd = new HelpCommand();
	private PauseCommand pauseCmd;
	private MoveDownCommand downCmd;
	private MoveUpCommand upCmd; 
	private MoveRightCommand rightCmd;
	private MoveLeftCommand leftCmd; 
	private ContractCommand contractCmd; 
	private ExpandCommand expandCmd; 
	private AlienCollisionCommand alienCollisionCmd;
	private AstronautCollisionCommand astronautCollisionCmd;
	private ScoreCommand scoreCmd;
	private SoundCommand soundCmd;
	private TickCommand tickCmd;
	private ToAlienCommand toAlienCmd;
	private ToAstronautCommand toAstronautCmd;
	private HealCommand healCmd;
	
	/**
	 * Construct instance of game, create game world
	 * 
	 */
	public Game() {
		this.setLayout(new BorderLayout());
		gw = new GameWorld();
		mv = new MapView(gw);
		sv = new ScoreView(gw);
		
		gw.addObserver(mv); // add map view as observer
		gw.addObserver(sv); // add score view as observer
		
		Toolbar toolBar = new Toolbar(); // add toolbar
		this.setToolbar(toolBar);
		
		toolBar.setTitle("The Rescue Game"); // title of game
		
		Container southContainer = new Container(new FlowLayout(Component.CENTER)); // set up containers in south, west, east
		Container eastContainer = new Container((new BoxLayout(BoxLayout.Y_AXIS)));
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		
		southContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK)); // create a border between containers
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		westContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		
		pauseCmd = new PauseCommand(this);
        downCmd = new MoveDownCommand(gw);
        upCmd = new MoveUpCommand(gw);
        rightCmd = new MoveRightCommand(gw);
        leftCmd = new MoveLeftCommand(gw);
        contractCmd = new ContractCommand(gw);
        expandCmd = new ExpandCommand(gw);
        scoreCmd = new ScoreCommand(gw);
        soundCmd = new SoundCommand(gw, toolBar);
        tickCmd = new TickCommand(gw);
        toAlienCmd = new ToAlienCommand(gw);
        toAstronautCmd = new ToAstronautCommand(gw);
		
		
		soundCheckBox.setCommand(soundCmd);
		
		soundCheckBox.getAllStyles().setBgTransparency(255); // style for checkbox
		soundCheckBox.getAllStyles().setPadding(TOP, 2);
		soundCheckBox.getAllStyles().setPadding(BOTTOM, 2);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.rgb(118, 139, 168));
		soundCheckBox.getAllStyles().setFgColor(ColorUtil.WHITE);
		
		
		toolBar.addCommandToRightBar(helpCmd); // add help to toolbar
		toolBar.addCommandToSideMenu(scoreCmd); // add the side menu to toolbar
		toolBar.addCommandToSideMenu(aboutCmd);
		toolBar.addCommandToSideMenu(exitCmd);
		toolBar.addComponentToSideMenu(soundCheckBox);
		
		// create buttons and set its command and place it in the correct container
		//Button newAlienButton = new ButtonStyle();
		//newAlienButton.setCommand(alienCollisionCmd);
		
		//Button fightButton = new  ButtonStyle();
		//fightButton.setCommand(astronautCollisionCmd);
		
		//Button tickButton = new  ButtonStyle();
		//tickButton.setCommand(tickCmd);
		
		healCmd = new HealCommand(gw);
		healButton.setCommand(healCmd);
		healButton.setEnabled(false);
		pauseButton.setCommand(pauseCmd);
		
		//southContainer.add(newAlienButton); buttons no longer needed
		//southContainer.add(fightButton);
		//southContainer.add(tickButton);
		southContainer.add(healButton);
		southContainer.add(pauseButton);
		
		
		expandButton.getAllStyles().setMarginTop(100);
		expandButton.setCommand(expandCmd);
		
		
		upButton.setCommand(upCmd);
		
		
		leftButton.setCommand(leftCmd);
		
		
		moveToAstroButton.setCommand(toAstronautCmd);
		
		westContainer.add(expandButton);
		westContainer.add(upButton);
		westContainer.add(leftButton);
		westContainer.add(moveToAstroButton);
		
		
		contractButton.getAllStyles().setMarginTop(100);
		contractButton.setCommand(contractCmd);
		
		
		downButton.setCommand(downCmd);
		
		
		rightButton.setCommand(rightCmd);
		
		
		moveToAlienButton.setCommand(toAlienCmd);
		
		
		scoreButton.setCommand(scoreCmd);
		
		eastContainer.add(contractButton);
		eastContainer.add(downButton);
		eastContainer.add(rightButton);
		eastContainer.add(moveToAlienButton);
		eastContainer.add(scoreButton);
		
		// set keys to correct commands
		this.addKeyListener('e', expandCmd);
		this.addKeyListener('c', contractCmd);
		this.addKeyListener('s', scoreCmd);
		this.addKeyListener('r', rightCmd);
		this.addKeyListener('l', leftCmd);
		this.addKeyListener('u', upCmd);
		this.addKeyListener('d', downCmd);
		this.addKeyListener('o', toAstronautCmd);
		this.addKeyListener('a', toAlienCmd);
		this.addKeyListener('w', alienCollisionCmd);
		this.addKeyListener('f', astronautCollisionCmd);
		this.addKeyListener('t', tickCmd);
		
		//add the borderlayout
		
		this.add(BorderLayout.SOUTH,southContainer);
		
		this.add(BorderLayout.WEST,westContainer);
		
		this.add(BorderLayout.EAST,eastContainer);
		
		this.add(BorderLayout.CENTER, mv);
		
		this.add(BorderLayout.NORTH, sv);
		
		gw.init();
		
		this.show();
		
		gw.setWidth(mv.getWidth()); // get width of game world
		gw.setHeight(mv.getHeight()); // get height of game world
		
		gw.createSounds();
		
		this.revalidate();
		
		timer = new UITimer(this);
		
		timer.schedule(20, true, this);
		
		
	}

	@Override
	public void run() {
		gw.tick();
		
	}
	
	public void pauseGame() {
		
		this.setToolbar(toolBar);
		
		if(!gw.getPaused()) {
			pauseButton.setText("Play");
			
			gw.pauseSound();
			
			healButton.setEnabled(true);
			
			gw.setPaused(true);
			
			timer.cancel();
			
			upButton.setEnabled(false);
            downButton.setEnabled(false);
            rightButton.setEnabled(false);
            leftButton.setEnabled(false);
            expandButton.setEnabled(false);
            contractButton.setEnabled(false);
            scoreButton.setEnabled(false);
            moveToAlienButton.setEnabled(false);
            moveToAstroButton.setEnabled(false);
            
            this.removeKeyListener('e', expandCmd);
            this.removeKeyListener('c', contractCmd);
            this.removeKeyListener('s', scoreCmd);
            this.removeKeyListener('r', rightCmd);
            this.removeKeyListener('l', leftCmd);
            this.removeKeyListener('u', upCmd);
            this.removeKeyListener('d', downCmd);
            this.removeKeyListener('o', toAstronautCmd);
            this.removeKeyListener('a', toAlienCmd);
            
            toolBar.setTitle("The Rescue Game"); // title of game
            
            toolBar.removeCommand(soundCmd);
            toolBar.removeCommand(aboutCmd);
            toolBar.removeCommand(scoreCmd);
            toolBar.removeCommand(exitCmd);
            toolBar.removeCommand(helpCmd);
            
    		aboutCmd.setEnabled(false);
    		scoreCmd.setEnabled(false);
    		exitCmd.setEnabled(false);
    		soundCmd.setEnabled(false);
    		helpCmd.setEnabled(false);
    		
    		toolBar.addCommandToSideMenu(aboutCmd);
            toolBar.addCommandToSideMenu(scoreCmd);
            toolBar.addCommandToSideMenu(exitCmd);
            toolBar.addCommandToRightBar(helpCmd); // add help to toolbar
            
    		
            
   
		}
		else {
			pauseButton.setText("Pause");
			
			gw.playSound();
			
			gw.setPaused(false);
			
			timer.schedule(20, true, this);
			
			healButton.setEnabled(false);
			
			upButton.setEnabled(true);
            downButton.setEnabled(true);
            rightButton.setEnabled(true);
            leftButton.setEnabled(true);
            expandButton.setEnabled(true);
            contractButton.setEnabled(true);
            scoreButton.setEnabled(true);
            moveToAlienButton.setEnabled(true);
            moveToAstroButton.setEnabled(true);
            
            this.addKeyListener('e', expandCmd);
    		this.addKeyListener('c', contractCmd);
    		this.addKeyListener('s', scoreCmd);
    		this.addKeyListener('r', rightCmd);
    		this.addKeyListener('l', leftCmd);
    		this.addKeyListener('u', upCmd);
    		this.addKeyListener('d', downCmd);
    		this.addKeyListener('o', toAstronautCmd);
    		this.addKeyListener('a', toAlienCmd);
    		
    		toolBar.setTitle("The Rescue Game"); // title of game
    		
    		toolBar.removeCommand(aboutCmd);
            toolBar.removeCommand(scoreCmd);
            toolBar.removeCommand(exitCmd);
            toolBar.removeCommand(helpCmd);
            toolBar.removeComponent(soundCheckBox);
    		
            
    		aboutCmd.setEnabled(true);
    		scoreCmd.setEnabled(true);
    		exitCmd.setEnabled(true);
    		soundCmd.setEnabled(true);
    		helpCmd.setEnabled(true);
    		
    		toolBar.addCommandToSideMenu(scoreCmd); // add the side menu to toolbar
            toolBar.addCommandToSideMenu(aboutCmd);
            toolBar.addCommandToSideMenu(exitCmd);
            toolBar.addComponentToSideMenu(soundCheckBox);
            toolBar.addCommandToRightBar(helpCmd); // add help to toolbar
    		
            
		}
		revalidate();
	}
	
	
}
