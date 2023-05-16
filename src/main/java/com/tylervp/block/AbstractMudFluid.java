package com.tylervp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
//import net.minecraft.tag.FluidTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.util.math.Direction;

public abstract class AbstractMudFluid extends FlowableFluid {
	/**
	 * @return is the given fluid an instance of this fluid?
	 */
	@Override
	public boolean matchesType(Fluid fluid) {
		return fluid == getStill() || fluid == getFlowing();
	}
 
	/**
	 * @return is the fluid infinite like water?
	 */
	@Override
	protected boolean isInfinite(World world) {
		return true;
	}
	
	@Override
    protected void flow(WorldAccess world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState) {
        if (direction == Direction.DOWN) {
            FluidState fluidState7 = world.getFluidState(pos);
            if (fluidState7.isIn(FluidTags.LAVA) && fluidState7.getFluid() == MBMBlocks.FLOWING_MUD || fluidState7.getFluid() == MBMBlocks.STILL_MUD) {
                if (state.getBlock() instanceof FluidBlock) {
                    world.setBlockState(pos, Blocks.DIRT.getDefaultState(), 3);
                }
                this.playExtinguishEvent(world, pos);
                return;
            }
		}
        super.flow(world, pos, state, direction, fluidState);
    }
    
    private void playExtinguishEvent(WorldAccess world, BlockPos pos) {
        world.syncWorldEvent(1501, pos, 0);
    }
 
	/**
	 * Perform actions when fluid flows into a replaceable block. Water drops
	 * the block's loot table. Lava plays the "block.lava.extinguish" sound.
	 */
    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
		Block.dropStacks(state, world, pos, blockEntity);
    }

	/**
	 * Lava returns true if its FluidState is above a certain height and the
	 * Fluid is Water.
	 * 
	 * @return if the given Fluid can flow into this FluidState?
	 */
	@Override
	protected boolean canBeReplacedWith(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction) {
		return false;
	}
 
	/**
	 * Possibly related to the distance checks for flowing into nearby holes?
	 * Water returns 4. Lava returns 2 in the Overworld and 4 in the Nether.
	 */
	@Override
	protected int getFlowSpeed(WorldView worldView) {
		return 3;
	}
	
	/*@Override
    protected void flow(WorldAccess world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState) {
        if (direction == Direction.DOWN) {
            FluidState fluidState7 = world.getFluidState(pos);
            if (fluidState7.isIn(FluidTags.LAVA)) {
                if (state.getBlock() instanceof FluidBlock) {
                    world.setBlockState(pos, Blocks.DIRT.getDefaultState(), 3);
                }
                this.playExtinguishEvent(world, pos);
                return;
            }
        }
        super.flow(world, pos, state, direction, fluidState);
	}
	
	private void playExtinguishEvent(WorldAccess world, BlockPos pos) {
        world.syncWorldEvent(1501, pos, 0);
    }*/
 
	/**
	 * Water returns 1. Lava returns 2 in the Overworld and 1 in the Nether.
	 */
	@Override
	protected int getLevelDecreasePerBlock(WorldView worldView) {
		return 1;
	}
 
	/**
	 * Water returns 5. Lava returns 30 in the Overworld and 10 in the Nether.
	 */
	@Override
	public int getTickRate(WorldView worldView) {
		return 10;
	}
 
	/**
	 * Water and Lava both return 100.0F.
	 */
	@Override
	protected float getBlastResistance() {
		return 100.0F;
	}
}