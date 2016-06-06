package massEffect.interfaces;

import java.util.Collection;

import massEffect.core.factories.EnhancementFactory;
import massEffect.core.factories.ShipFactory;
import massEffect.gameObjects.Galaxy;

public interface IGameEngine {

	ShipFactory getShipFactory();

    EnhancementFactory getEnhancementFactory();

    Collection<IStarship> getStarships();

    Galaxy getGalaxy();

    ICommandManager getCommandManager();

    boolean isRunning();
    
    void setIsRunning(boolean isRunning);

    void run();
}
