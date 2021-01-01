package com.tylervp.fluid;

import java.util.Random;

import com.tylervp.block.AbstractMudFluid;
import com.tylervp.block.MBMBlocks;
import com.tylervp.item.MBMItems;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.state.StateManager;

public abstract class MudFluid extends AbstractMudFluid {
	
	@Override
	public Fluid getStill() {
		return MBMBlocks.STILL_MUD;
	}
 
	@Override
	public Fluid getFlowing() {
		return MBMBlocks.FLOWING_MUD;
	}
 
	@Override
	public Item getBucketItem() {
		return MBMItems.MUD_BUCKET;
	}
 
	@Override
	protected BlockState toBlockState(FluidState fluidState) {
		// method_15741 converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
		return MBMBlocks.MUD.getDefaultState().with(Properties.LEVEL_15, method_15741(fluidState));
	}

	//@Environment(EnvType.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, FluidState state, Random random) {
        if (!state.isStill() && !state.<Boolean>get((Property<Boolean>)MudFluid.FALLING)) {
            if (random.nextInt(64) == 0) {
                world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, random.nextFloat() * 0.25f + 0.75f, random.nextFloat() + 0.5f, false);
            }
        }
        else if (random.nextInt(10) == 0) {
			DustParticleEffect dirtPartical = new DustParticleEffect(0.93f, 0.63f, 0.45f, 1.0f);
            world.addParticle(dirtPartical, pos.getX() + random.nextDouble(), pos.getY() + random.nextDouble(), pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
		}

    }
 
	public static class Flowing extends MudFluid {
		@Override
		protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
			super.appendProperties(builder);
			builder.add(LEVEL);
		}
 
		@Override
		public int getLevel(FluidState fluidState) {
			return fluidState.get(LEVEL);
		}
 
		@Override
		public boolean isStill(FluidState fluidState) {
			return false;
		}
	}
 
	public static class Still extends MudFluid {
		@Override
		public int getLevel(FluidState fluidState) {
			return 8;
		}
 
		@Override
		public boolean isStill(FluidState fluidState) {
			return true;
		}
	}
}