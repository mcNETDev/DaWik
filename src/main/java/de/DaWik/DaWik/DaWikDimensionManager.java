package de.DaWik.DaWik;

import java.util.ArrayList;
import cpw.mods.fml.common.event.FMLInterModComms;
import de.DaWik.DaWik.World.Dimension;
import de.DaWik.DaWik.World.Admin.DaWikMiningWorldProvider;
import de.DaWik.DaWik.World.Admin.DaWikNormalWorldProvider;
import de.DaWik.DaWik.World.Admin.DaWikVoidWorldProvider;
import net.minecraftforge.common.DimensionManager;

public class DaWikDimensionManager {
	public static ArrayList<Dimension> dims = new ArrayList<Dimension>();

	public void init() {
		for (int i = 0; i < DaWik.DIMLIST.length; i++) {
			String dim = DaWik.DIMLIST[i];
			String[] prop = dim.split(";");
			String name = prop[0];
			int dimid = Integer.valueOf(prop[1]);
			DimType type = DimType.valueOf(prop[2]);
			dims.add(new Dimension(name, dimid, type));
			switch (type) {
			case MINING:
				DimensionManager.registerProviderType(dimid, DaWikMiningWorldProvider.class, true);
				FMLInterModComms.sendMessage("BuildCraft|Energy", "oil-gen-exclude", dimid + "");
				FMLInterModComms.sendMessage("Thaumcraft", "dimensionBlacklist", dimid + ":1");
				break;
			case NORMAL:
				DimensionManager.registerProviderType(dimid, DaWikNormalWorldProvider.class, true);
				break;
			case VOID:
				DimensionManager.registerProviderType(dimid, DaWikVoidWorldProvider.class, true);
				FMLInterModComms.sendMessage("BuildCraft|Energy", "oil-gen-exclude", dimid + "");
				break;
			default:
				break;
			}
			DimensionManager.registerDimension(dimid, dimid);
			
		}
	}

	public static String getName(int dimensionId) {
		for (Dimension dim : dims) {
			if (dim.getId() == dimensionId) {
				return dim.getName();
			}
		}
		return "";
	}
	public static boolean isMiningDim(int id){
		for (Dimension dim : dims) {
			if (dim.getId() == id && dim.getType().equals(DimType.MINING)) {
				return true;
			}
		}
		return false;
	}
	
}
