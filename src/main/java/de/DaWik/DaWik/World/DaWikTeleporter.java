package de.DaWik.DaWik.World;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class DaWikTeleporter extends Teleporter {

	private WorldServer world;

	public DaWikTeleporter(WorldServer p_i1963_1_,int id) {
		super(p_i1963_1_);
		//TODO
		this.world = p_i1963_1_;

	}

	@Override
	public boolean makePortal(Entity p_85188_1_) {
		return true;
	}

	@Override
	public boolean placeInExistingPortal(Entity player, double x, double y, double z, float raw) {
		player.setPosition(world.getSpawnPoint().posX, world.getSpawnPoint().posY, world.getSpawnPoint().posZ);
		return true;
	}

	@Override
	public void placeInPortal(Entity p_77185_1_, double p_77185_2_, double p_77185_4_, double p_77185_6_, float p_77185_8_) {
	
	}

	@Override
	public void removeStalePortalLocations(long p_85189_1_) {
	
	}

}
