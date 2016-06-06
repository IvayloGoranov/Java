package massEffect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import massEffect.core.factories.EnhancementFactory;
import massEffect.core.factories.ShipFactory;
import massEffect.exceptions.ShipException;
import massEffect.gameObjects.Galaxy;
import massEffect.interfaces.ICommandManager;
import massEffect.interfaces.IGameEngine;
import massEffect.interfaces.IStarship;

public class GameEngine implements IGameEngine {

	private ICommandManager commandManager;
	private Galaxy galaxy;
	private ShipFactory shipFactory;
	private EnhancementFactory enhancementFactory;
	private Collection<IStarship> starships;
	private boolean isRunning;
	
	public GameEngine(ICommandManager commandManager, Galaxy galaxy) {
		
        this.setCommandManager(commandManager);
        this.galaxy = galaxy;
        this.shipFactory = new ShipFactory();
        this.enhancementFactory = new EnhancementFactory();
        this.starships = new ArrayList<IStarship>();
    }
	
	
	@Override
	public ShipFactory getShipFactory() {

		return this.shipFactory;
	}

	@Override
	public EnhancementFactory getEnhancementFactory() {

		return this.enhancementFactory;
	}

	@Override
	public Collection<IStarship> getStarships() {

		return this.starships;
	}

	@Override
	public Galaxy getGalaxy() {

		return this.galaxy;
	}

	@Override
	public ICommandManager getCommandManager() {

		return this.commandManager;
	}

	public void setCommandManager(ICommandManager commandManager) {

		this.commandManager = commandManager;
	}
	
	@Override
	public boolean isRunning() {

		return this.isRunning;
	}

	@Override
	public void setIsRunning(boolean isRunning) {
		
		this.isRunning = isRunning;
	}

	@Override
	public void run() {

		this.isRunning = true;
        this.getCommandManager().setEngine(this);
        this.commandManager.seedCommands();

        do {
        	
            @SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
        	String command = scanner.nextLine();

            try {
            	
                this.commandManager.processCommand(command);
            }
            catch (ShipException ex) {
            	
                System.out.println(ex.getMessage());
            }
        } 
        while (this.isRunning());
	}

}
