package com.tylervp.block;

import net.minecraft.util.math.random.Random;

import com.tylervp.item.MBMItems;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class GrapeSpur extends Block {
    public static final IntProperty STAGES;

    public GrapeSpur(AbstractBlock.Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.getDefaultState().with(STAGES, 1));
    }

    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
		return VoxelShapes.empty();
	}

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int stage = state.get(STAGES);

        if(stage < 5) {
            return VoxelShapes.empty();
        }

        switch (stage) {
            case 5:
                return Block.createCuboidShape(6, 0, 6, 10, 4, 10);
            case 6:
                return Block.createCuboidShape(6, 0, 6, 10, 9, 10);
        }

        return getOutlineShape(state, world, pos, context);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int stage = state.get(STAGES);

        switch (stage) {
            case 1:
                return Block.createCuboidShape(4, 0, 4, 12, 2, 12);
            case 2:
                return Block.createCuboidShape(4, 0, 4, 12, 5, 12);
            case 3:
                return Block.createCuboidShape(4, 0, 4, 12, 11, 12);
            case 4:
                return Block.createCuboidShape(4, 0, 4, 12, 16, 12);
            case 5:
                return Block.createCuboidShape(6, 0, 6, 10, 16, 10);
            case 6:
                return Block.createCuboidShape(6, 0, 6, 10, 16, 10);
        }

        return super.getOutlineShape(state, world, pos, context);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return /* !world.getBlockState(pos.down()).isAir() &&  */(world.getBlockState(pos.down()).isOf(Blocks.ROOTED_DIRT) || world.getBlockState(pos.down()).isOf(Blocks.GRASS_BLOCK) || world.getBlockState(pos.down()).isOf(Blocks.DIRT) || world.getBlockState(pos.down()).isOf(MBMBlocks.GRAPE_LOG) || world.getBlockState(pos.down()).isOf(Blocks.COARSE_DIRT) || world.getBlockState(pos.down()).isOf(MBMBlocks.PACKED_DIRT));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if(!canPlaceAt(state, world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(STAGES, 1);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerItem = player.getStackInHand(hand);

        if(playerItem.getItem() == Items.BONE_MEAL) {
            Random random = world.getRandom();
            if(!world.isClient){
                generate(state, (ServerWorld)world, pos, random);
            } else {

                int stage = state.get(STAGES);

                if(stage < 6){
                    if(stage == 4 && world.getBlockState(pos.down()).isOf(MBMBlocks.GRAPE_LOG)) {

                        BlockPos pos2 = pos.mutableCopy();
                        BlockPos.Mutable mutable2 = pos2.mutableCopy();
                        for (final Direction lv3 : Direction.values()) {
                            if(Block.isFaceFullSquare(world.getBlockState(mutable2.set(pos2, lv3)).getSidesShape(world, pos2), lv3.getOpposite())){
                                spawnParticlesGrow(world, pos);
                                return ActionResult.success(world.isClient);
                            }
                        }
                        return super.onUse(state, world, pos, player, hand, hit);
                    }
                    
                    spawnParticlesGrow(world, pos);
                    return ActionResult.success(world.isClient);
                } else {
                    spawnParticlesGrow(world, pos);
                    return ActionResult.success(world.isClient);
                }
            }
        }

            

        return super.onUse(state, world, pos, player, hand, hit);
    }

    private static void spawnParticlesGrow(World world, BlockPos pos) {
        //double double3 = 0.5625;
        for (int i = 0; i < 4; i++) {
            Random random5 = world.random;
            for (final Direction lv : Direction.values()) {
                BlockPos blockPos10 = pos.offset(lv);
                if (!world.getBlockState(blockPos10).isOpaqueFullCube(world, blockPos10)) {
                    Direction.Axis axis11 = lv.getAxis();
                    double double12 = (axis11 == Direction.Axis.X) ? (0.5 + 0.5625 * lv.getOffsetX()) : random5.nextFloat();
                    double double14 = (axis11 == Direction.Axis.Y) ? (0.5 + 0.5625 * lv.getOffsetY()) : random5.nextFloat();
                    double double16 = (axis11 == Direction.Axis.Z) ? (0.5 + 0.5625 * lv.getOffsetZ()) : random5.nextFloat();
                    //DustParticleEffect dirtPartical = new DustParticleEffect(0.93f, 0.63f, 0.45f, 1.0f);
                    
                    world.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.0, 0.0);
                }
            }   
        }
        
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        generate(state, world, pos, random);
    }

    private void generate(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int stage = state.get(STAGES);

        if(stage < 6){
            if(stage == 4 && world.getBlockState(pos.down()).isOf(MBMBlocks.GRAPE_LOG)) {

                BlockPos pos2 = pos.mutableCopy();
                BlockPos.Mutable mutable2 = pos2.mutableCopy();
                for (final Direction lv3 : Direction.values()) {
                    if(Block.isFaceFullSquare(world.getBlockState(mutable2.set(pos2, lv3)).getSidesShape(world, pos2), lv3.getOpposite())){
                        world.setBlockState(pos, MBMBlocks.GRAPE_LEAVES.getDefaultState().with(GrapeLeaves.MAXGROW, GrapeLeaves.maxGrowRandomizer(random)).with(GrapeLeaves.SHOWBOTTOM, true).with(GrapeLeaves.GROWNDIRECTION, Direction.DOWN).with(GrapeLeaves.BEARFRUIT, GrapeLeaves.shouldBearFruit(random)).with(GrapeLeaves.GROWVINES, GrapeLeaves.shouldGrowVines(random)));
                        return;
                    }
                }
                return;
            }
            
            world.setBlockState(pos, this.getDefaultState().with(STAGES, stage + 1));
        } else {
            BlockPos.Mutable mutable2 = pos.mutableCopy();
            for (final Direction lv3 : Direction.values()) {
                if(lv3 != Direction.DOWN && Block.isFaceFullSquare(world.getBlockState(mutable2.set(pos, lv3)).getSidesShape(world, pos), lv3.getOpposite())){
                    world.setBlockState(pos, MBMBlocks.GRAPE_LOG.getDefaultState());

                    if(world.getBlockState(pos.down()).isOf(Blocks.DIRT))   {
                        world.setBlockState(pos.down(), Blocks.ROOTED_DIRT.getDefaultState());
                    }
                    if(world.getBlockState(pos.down(2)).isAir()) {
                        world.setBlockState(pos.down(2), Blocks.HANGING_ROOTS.getDefaultState());
                    } else if (world.getBlockState(pos.down(2)).isOf(Blocks.WATER)) {
                        world.setBlockState(pos.down(2), Blocks.HANGING_ROOTS.getDefaultState().with(Properties.WATERLOGGED, true));
                    }
                    return;
                }
            }
            world.setBlockState(pos, MBMBlocks.GRAPE_LEAVES.getDefaultState().with(GrapeLeaves.MAXGROW, GrapeLeaves.maxGrowRandomizer(random)).with(GrapeLeaves.SHOWBOTTOM, false).with(GrapeLeaves.GROWNDIRECTION, Direction.DOWN).with(GrapeLeaves.BEARFRUIT, GrapeLeaves.shouldBearFruit(random)).with(GrapeLeaves.GROWVINES, false).with(GrapeLeaves.DISTANCE, 7));
            if(world.getBlockState(pos.down()).isOf(Blocks.DIRT))   {
                world.setBlockState(pos.down(), Blocks.ROOTED_DIRT.getDefaultState());
            }
            if(world.getBlockState(pos.down(2)).isAir()) {
                world.setBlockState(pos.down(2), Blocks.HANGING_ROOTS.getDefaultState());
            } else if (world.getBlockState(pos.down(2)).isOf(Blocks.WATER)) {
                world.setBlockState(pos.down(2), Blocks.HANGING_ROOTS.getDefaultState().with(Properties.WATERLOGGED, true));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(this.getSeedsItem());
    }

    @Environment(EnvType.CLIENT)
    protected ItemConvertible getSeedsItem() {
        return MBMItems.GRAPE_SEEDS;
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(STAGES);
    }

    static {
        STAGES = IntProperty.of("stage", 1, 6);
    }
}
