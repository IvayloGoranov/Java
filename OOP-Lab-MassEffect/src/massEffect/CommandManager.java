package massEffect;

import java.util.HashMap;

import massEffect.core.commands.AttackCommand;
import massEffect.core.commands.Command;
import massEffect.core.commands.CreateCommand;
import massEffect.core.commands.OverCommand;
import massEffect.core.commands.PlotJumpCommand;
import massEffect.core.commands.StatusReportCommand;
import massEffect.exceptions.ShipException;
import massEffect.interfaces.ICommandManager;
import massEffect.interfaces.IGameEngine;

public class CommandManager implements ICommandManager {

	private IGameEngine gameEngine;
	
	protected final HashMap<String, Command> commandsByName;
	
    public CommandManager() {
    	
        this.commandsByName = new HashMap<String, Command>();
    }
	
	@Override
	public IGameEngine getEngine() {

		return this.gameEngine;
	}

	@Override
	public void setEngine(IGameEngine engine) {
		
		this.gameEngine = engine;
	}

	@Override
	public void processCommand(String commandString) throws ShipException {
		
		String[] commandArgs = commandString.split("\\s");
        String commandName = commandArgs[0];

        if (!this.commandsByName.containsKey(commandName)) {
        	
            throw new UnsupportedOperationException(String.format(
                "Command %s does not exist.", commandName));
        }

        Command command = this.commandsByName.get(commandName);
        command.execute(commandArgs);
	}

	@Override
	public void seedCommands() {
		
		this.commandsByName.put("create", new CreateCommand(this.getEngine()));
        this.commandsByName.put("attack", new AttackCommand(this.getEngine()));
        this.commandsByName.put("status-report", new StatusReportCommand(this.getEngine()));
        this.commandsByName.put("plot-jump", new PlotJumpCommand(this.getEngine()));
        this.commandsByName.put("over", new OverCommand(this.getEngine()));
	}
}
