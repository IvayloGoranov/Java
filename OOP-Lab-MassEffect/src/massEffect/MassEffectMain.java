package massEffect;

import massEffect.gameObjects.Galaxy;
import massEffect.gameObjects.locations.StarSystem;
import massEffect.interfaces.ICommandManager;
import massEffect.interfaces.IGameEngine;

public class MassEffectMain {

	public static void main(String[] args) {
		
		 Galaxy galaxy = new Galaxy();
         seedStarSystems(galaxy);

         ICommandManager commandManager = new CommandManager();
         IGameEngine engine = new GameEngine(commandManager, galaxy);
         engine.run();
	}
	
	public static void seedStarSystems(Galaxy galaxy) {
		
        StarSystem artemisTau = new StarSystem("Artemis-Tau");
        StarSystem serpentNebula = new StarSystem("Serpent-Nebula");
        StarSystem hadesGamma = new StarSystem("Hades-Gamma");
        StarSystem keplerVerge = new StarSystem("Kepler-Verge");

        galaxy.getStarSystems().add(artemisTau);
        galaxy.getStarSystems().add(serpentNebula);
        galaxy.getStarSystems().add(hadesGamma);
        galaxy.getStarSystems().add(keplerVerge);

        artemisTau.getNeighbourStarSystems().put(serpentNebula, Double.valueOf(50));
        artemisTau.getNeighbourStarSystems().put(keplerVerge, Double.valueOf(120));

        serpentNebula.getNeighbourStarSystems().put(artemisTau, Double.valueOf(50));
        serpentNebula.getNeighbourStarSystems().put(hadesGamma, Double.valueOf(360));

        hadesGamma.getNeighbourStarSystems().put(serpentNebula, Double.valueOf(360));
        hadesGamma.getNeighbourStarSystems().put(keplerVerge, Double.valueOf(145));

        keplerVerge.getNeighbourStarSystems().put(hadesGamma, Double.valueOf(145));
        keplerVerge.getNeighbourStarSystems().put(artemisTau, Double.valueOf(120));
    }
}
