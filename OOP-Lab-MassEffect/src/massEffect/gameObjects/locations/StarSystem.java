package massEffect.gameObjects.locations;

import java.util.LinkedHashMap;
import java.util.Map;

public class StarSystem {

	private String name;
	private Map<StarSystem, Double> neighbourStarSystems;
	
	public StarSystem(String name) {
		
        this.setName(name);
        this.neighbourStarSystems = new LinkedHashMap<StarSystem, Double>();
    }

	public String getName() {
		
		return this.name;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public Map<StarSystem, Double> getNeighbourStarSystems() {
		
		return this.neighbourStarSystems;
	}
}
