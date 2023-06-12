package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
//import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
/* import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World; */

public class IceSlabBlock extends SlabBlock {

    public IceSlabBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    /* @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        //entity.addVelocity(new Vec3d(entity.getVelocity().getX() * 0.0989f, 0, entity.getVelocity().getY() * 0.0989f));
    } */

    /* @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) {
            return true;
        }
        return super.isSideInvisible(state, stateFrom, direction);
    } */
}
