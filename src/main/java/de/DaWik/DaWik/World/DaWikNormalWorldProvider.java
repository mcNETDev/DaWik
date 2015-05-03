package de.DaWik.DaWik.World;

import de.DaWik.DaWik.DaWikDimensionManager;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldProviderSurface;

public class DaWikNormalWorldProvider extends WorldProvider {

	@Override
	public String getDimensionName() {
		return DaWikDimensionManager.getName(dimensionId);
	}

}
