package massEffect.core.factories;

import massEffect.gameObjects.enhancements.Enhancement;
import massEffect.gameObjects.enhancements.EnhancementType;

public class EnhancementFactory {

	public Enhancement create(EnhancementType enhancementType) {
		
        switch (enhancementType)
        {
            case ThanixCannon:
                return new Enhancement("ThanixCannon", 0, 50, 0);
            case KineticBarrier:
                return new Enhancement("KineticBarrier", 100, 0, 0);
            case ExtendedFuelCells:
                return new Enhancement("ExtendedFuelCells", 0, 0, 200);
            default:
                throw new UnsupportedOperationException();
        }
    }
}
