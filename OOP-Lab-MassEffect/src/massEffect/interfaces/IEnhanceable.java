package massEffect.interfaces;

import massEffect.gameObjects.enhancements.Enhancement;

public interface IEnhanceable {

	Iterable<Enhancement> getEnhancements();

    void addEnhancement(Enhancement enhancement);
    
    String getName();
}
