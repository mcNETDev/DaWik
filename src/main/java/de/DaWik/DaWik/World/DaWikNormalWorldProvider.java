package de.DaWik.DaWik.World;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.ForgeHooksClient;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.DaWik.DaWik.DaWikDimensionManager;
import de.DaWik.DaWik.enums.SkyColors;

public class DaWikNormalWorldProvider extends WorldProvider {

	private SkyColors skyColor = SkyColors.DEFAULT;
	private boolean init = false;

	public DaWikNormalWorldProvider() {
		System.out.println(dimensionId);
		// skyColor = DaWikDimensionManager.getDim(dimensionId).getSky();
	}

	@Override
	public String getDimensionName() {
		return DaWikDimensionManager.getName(dimensionId);
	}

	@Override
	public boolean isSkyColored() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getSkyColor(Entity player, float parmFloat) {
		if (!init && (dimensionId > 0)) {
			if (DaWikDimensionManager.getDim(dimensionId).getSky() != null) {
				skyColor = DaWikDimensionManager.getDim(dimensionId).getSky();
				init = true;
			} else {
				// System.out.println("FEHLER");
			}
			// System.out.println(dimensionId);
		}
		float f1 = worldObj.getCelestialAngle(parmFloat);
		float f2 = (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F) + 0.5F;

		if (f2 < 0.0F) {
			f2 = 0.0F;
		}

		if (f2 > 1.0F) {
			f2 = 1.0F;
		}

		int i = MathHelper.floor_double(player.posX);
		int j = MathHelper.floor_double(player.posY);
		int k = MathHelper.floor_double(player.posZ);
		int l = ForgeHooksClient.getSkyBlendColour(worldObj, i, j, k);
		l = skyColor.getColor();
		float f4 = ((l >> 16) & 255) / 255.0F;
		float f5 = ((l >> 8) & 255) / 255.0F;
		float f6 = (l & 255) / 255.0F;
		f4 *= f2;
		f5 *= f2;
		f6 *= f2;
		float f7 = worldObj.getRainStrength(parmFloat);
		float f8;
		float f9;

		if (f7 > 0.0F) {
			f8 = ((f4 * 0.3F) + (f5 * 0.59F) + (f6 * 0.11F)) * 0.6F;
			f9 = 1.0F - (f7 * 0.75F);
			f4 = (f4 * f9) + (f8 * (1.0F - f9));
			f5 = (f5 * f9) + (f8 * (1.0F - f9));
			f6 = (f6 * f9) + (f8 * (1.0F - f9));
		}

		f8 = worldObj.getWeightedThunderStrength(parmFloat);

		if (f8 > 0.0F) {
			f9 = ((f4 * 0.3F) + (f5 * 0.59F) + (f6 * 0.11F)) * 0.2F;
			float f10 = 1.0F - (f8 * 0.75F);
			f4 = (f4 * f10) + (f9 * (1.0F - f10));
			f5 = (f5 * f10) + (f9 * (1.0F - f10));
			f6 = (f6 * f10) + (f9 * (1.0F - f10));
		}

		if (worldObj.lastLightningBolt > 0) {
			f9 = worldObj.lastLightningBolt - parmFloat;

			if (f9 > 1.0F) {
				f9 = 1.0F;
			}

			f9 *= 0.45F;
			f4 = (f4 * (1.0F - f9)) + (0.8F * f9);
			f5 = (f5 * (1.0F - f9)) + (0.8F * f9);
			f6 = (f6 * (1.0F - f9)) + (1.0F * f9);
		}

		return Vec3.createVectorHelper(f4, f5, f6);
	}
}
