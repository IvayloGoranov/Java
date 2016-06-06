package massEffect.interfaces;

import massEffect.exceptions.ShipException;

public interface ICommandManager {

	IGameEngine getEngine();
	
	void setEngine(IGameEngine engine);
	
    void processCommand(String command) throws ShipException;

    void seedCommands();
}
