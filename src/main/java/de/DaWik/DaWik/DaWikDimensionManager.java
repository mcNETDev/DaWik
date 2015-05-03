package de.DaWik.DaWik;

import java.util.ArrayList;

import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.event.FMLInterModComms;
import de.DaWik.DaWik.Config.ConfigManager;
import de.DaWik.DaWik.World.DaWikMiningWorldProvider;
import de.DaWik.DaWik.World.DaWikNormalWorldProvider;
import de.DaWik.DaWik.World.Dimension;

public class DaWikDimensionManager {
	public static ArrayList<Dimension> dims = new ArrayList<Dimension>();

	public static void init() {
		for (String dim : ConfigManager.dimList) {
			String[] prop = dim.split(";");
			String name = prop[0];
			int dimid = Integer.valueOf(prop[1]);
			DimType type = DimType.valueOf(prop[2]);
			DaWikDimensionManager.dims.add(new Dimension(name, dimid, type));
			switch (type) {
			case MINING:
				DimensionManager.registerProviderType(dimid, DaWikMiningWorldProvider.class, true);
				FMLInterModComms.sendMessage("BuildCraft|Energy", "oil-gen-exclude", dimid + "");
				break;
			case NORMAL:
				DimensionManager.registerProviderType(dimid, DaWikNormalWorldProvider.class, true);
				break;
			default:
				break;
			}
			DimensionManager.registerDimension(dimid, dimid);

		}
	}

	public static String getName(int dimensionId) {
		for (Dimension dim : DaWikDimensionManager.dims) {
			if (dim.getId() == dimensionId) {
				return dim.getName();
			}
		}
		return "";
	}

	public static boolean isMiningDim(int id) {
		for (Dimension dim : DaWikDimensionManager.dims) {
			if ((dim.getId() == id) && dim.getType().equals(DimType.MINING)) {
				return true;
			}
		}
		return false;
	}

}
