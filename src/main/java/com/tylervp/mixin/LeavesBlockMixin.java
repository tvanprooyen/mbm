package com.tylervp.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import com.tylervp.block.Apple;
import com.tylervp.block.MBMBlocks;
import com.tylervp.block.ThinLogBlock;
import com.tylervp.block.TreeFruitBlock;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin extends Block {

    private static final BooleanProperty ALLOW_FRUIT;

    public LeavesBlockMixin(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LeavesBlock.DISTANCE, 7).with(LeavesBlock.PERSISTENT, false).with(LeavesBlock.WATERLOGGED, false).with(LeavesBlockMixin.ALLOW_FRUIT, true));
    }

    @Inject(method = "getPlacementState(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/block/BlockState;", at = @At("HEAD"), cancellable = true)
    private void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> ci) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        BlockState state = world.getBlockState(pos);

        if(state.isOf(MBMBlocks.THIN_OAK_LOG)){
            ci.setReturnValue((BlockState)MBMBlocks.THIN_OAK_LOG.getDefaultState().with(ThinLogBlock.LEAVES, true).with(ThinLogBlock.PRESISTANT, true).with(ThinLogBlock.AXIS, state.get(ThinLogBlock.AXIS)).with(ThinLogBlock.CHAIN, state.get(ThinLogBlock.CHAIN)).with(ThinLogBlock.ROPE, state.get(ThinLogBlock.ROPE)));
        }

        if(state.isOf(MBMBlocks.THIN_SPRUCE_LOG)){
            ci.setReturnValue((BlockState)MBMBlocks.THIN_SPRUCE_LOG.getDefaultState().with(ThinLogBlock.LEAVES, true).with(ThinLogBlock.PRESISTANT, true).with(ThinLogBlock.AXIS, state.get(ThinLogBlock.AXIS)).with(ThinLogBlock.CHAIN, state.get(ThinLogBlock.CHAIN)).with(ThinLogBlock.ROPE, state.get(ThinLogBlock.ROPE)));
        }

        if(state.isOf(MBMBlocks.THIN_ACACIA_LOG)){
            ci.setReturnValue((BlockState)MBMBlocks.THIN_ACACIA_LOG.getDefaultState().with(ThinLogBlock.LEAVES, true).with(ThinLogBlock.PRESISTANT, true).with(ThinLogBlock.AXIS, state.get(ThinLogBlock.AXIS)).with(ThinLogBlock.CHAIN, state.get(ThinLogBlock.CHAIN)).with(ThinLogBlock.ROPE, state.get(ThinLogBlock.ROPE)));
        }

        if(state.isOf(MBMBlocks.THIN_BIRCH_LOG)){
            ci.setReturnValue((BlockState)MBMBlocks.THIN_BIRCH_LOG.getDefaultState().with(ThinLogBlock.LEAVES, true).with(ThinLogBlock.PRESISTANT, true).with(ThinLogBlock.AXIS, state.get(ThinLogBlock.AXIS)).with(ThinLogBlock.CHAIN, state.get(ThinLogBlock.CHAIN)).with(ThinLogBlock.ROPE, state.get(ThinLogBlock.ROPE)));
        }

        if(state.isOf(MBMBlocks.THIN_DARK_OAK_LOG)){
            ci.setReturnValue((BlockState)MBMBlocks.THIN_DARK_OAK_LOG.getDefaultState().with(ThinLogBlock.LEAVES, true).with(ThinLogBlock.PRESISTANT, true).with(ThinLogBlock.AXIS, state.get(ThinLogBlock.AXIS)).with(ThinLogBlock.CHAIN, state.get(ThinLogBlock.CHAIN)).with(ThinLogBlock.ROPE, state.get(ThinLogBlock.ROPE)));
        }

        if(state.isOf(MBMBlocks.THIN_JUNGLE_LOG)){
            ci.setReturnValue((BlockState)MBMBlocks.THIN_JUNGLE_LOG.getDefaultState().with(ThinLogBlock.LEAVES, true).with(ThinLogBlock.PRESISTANT, true).with(ThinLogBlock.AXIS, state.get(ThinLogBlock.AXIS)).with(ThinLogBlock.CHAIN, state.get(ThinLogBlock.CHAIN)).with(ThinLogBlock.ROPE, state.get(ThinLogBlock.ROPE)));
        }

        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockState blockState = this.getDefaultState().with(LeavesBlockMixin.ALLOW_FRUIT, false).with(LeavesBlock.PERSISTENT, true).with(LeavesBlock.WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        ci.setReturnValue(updateDistanceFromLogs(blockState, ctx.getWorld(), ctx.getBlockPos()));

    }

    private static BlockState updateDistanceFromLogs(BlockState state, WorldAccess world, BlockPos pos) {
        int i = 7;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (Direction direction : Direction.values()) {
            mutable.set((Vec3i)pos, direction);
            i = Math.min(i, getDistanceFromLog(world.getBlockState(mutable)) + 1);
            if (i == 1) break;
        }
        return (BlockState)state.with(LeavesBlock.DISTANCE, i);
    }

    private static int getDistanceFromLog(BlockState state) {
        if (state.isIn(BlockTags.LOGS)) {
            return 0;
        }
        if (state.getBlock() instanceof LeavesBlock) {
            return state.get(LeavesBlock.DISTANCE);
        }
        return 7;
    }

    @Inject(method = "hasRandomTicks(Lnet/minecraft/block/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private void hasRandomTicks(BlockState state, CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue((state.get(LeavesBlock.DISTANCE) == 7 && state.get(LeavesBlock.PERSISTENT) == false) || state.get(ALLOW_FRUIT));
    }

    private boolean testArea(ServerWorld world , BlockPos.Mutable startPosMutable, Block block) {
        boolean pass = false;

        for (int y = -1; y <= 1; y++) {
            BlockState northwest = world.getBlockState(startPosMutable.add(-1, y, -1));
            BlockState southeast = world.getBlockState(startPosMutable.add(1, y, 1));
            BlockState southwest = world.getBlockState(startPosMutable.add(-1, y, 1));
            BlockState northeast = world.getBlockState(startPosMutable.add(1, y, -1));
            BlockState north = world.getBlockState(startPosMutable.add(0, y, -1));
            BlockState east = world.getBlockState(startPosMutable.add(1, y, 0));
            BlockState south = world.getBlockState(startPosMutable.add(0, y, 1));
            BlockState west = world.getBlockState(startPosMutable.add(-1, y, 0));

            if(northwest.isOf(block) || southeast.isOf(block) || southwest.isOf(block) || northeast.isOf(block) || north.isOf(block) || east.isOf(block) || south.isOf(block) || west.isOf(block)) {
                pass = true;
                break;
            }
        }

        return pass;
    }

    @Inject(method = "randomTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;)V", at = @At("HEAD"), cancellable = true)
    private void randomTicks(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if(!state.get(LeavesBlock.PERSISTENT) && state.get(ALLOW_FRUIT)) {

            Block fruitBlock;

            if(state.isOf(Blocks.OAK_LEAVES)) {
                fruitBlock = MBMBlocks.OAK_ACORN;
            } else if(state.isOf(Blocks.SPRUCE_LEAVES)) {
                fruitBlock = MBMBlocks.PINECONE;
            } else if(state.isOf(Blocks.DARK_OAK_LEAVES)) {
                fruitBlock = MBMBlocks.DARK_OAK_ACORN;
            } else if(state.isOf(Blocks.BIRCH_LEAVES)) {
                fruitBlock = MBMBlocks.BIRCH_CATKIN;
            } else {
                fruitBlock = Blocks.AIR;
            }

            if(random.nextInt(3) == 0 || fruitBlock == Blocks.AIR) {
                world.setBlockState(pos, state.with(ALLOW_FRUIT, false));
            }

            if(fruitBlock != Blocks.AIR) {
                BlockPos.Mutable startPosMutable = pos.mutableCopy();

                for (Direction direction : Direction.values()) {
                    if(direction == Direction.DOWN && (fruitBlock == MBMBlocks.PINECONE || fruitBlock == MBMBlocks.DARK_OAK_ACORN || fruitBlock == MBMBlocks.OAK_ACORN || fruitBlock == MBMBlocks.BIRCH_CATKIN)) {

                        if(testArea(world, startPosMutable, fruitBlock)) {
                            world.setBlockState(pos, state.with(ALLOW_FRUIT, false));
                            break;
                        }

                        if(random.nextInt(4) == 0) {
                            break;
                        }

                        if(random.nextInt(6) == 0) {
                            BlockPos offsetPos = startPosMutable.offset(direction);
                            BlockState offsetState = world.getBlockState(offsetPos);

                            if(offsetState.isAir()) {
                                world.setBlockState(offsetPos, fruitBlock.getDefaultState().with(TreeFruitBlock.UP, true));
                                world.setBlockState(pos, state.with(ALLOW_FRUIT, false));
                                break;
                            }
                        }

                    } else if(direction != Direction.UP && !(fruitBlock == MBMBlocks.PINECONE || fruitBlock == MBMBlocks.DARK_OAK_ACORN || fruitBlock == MBMBlocks.OAK_ACORN || fruitBlock == MBMBlocks.BIRCH_CATKIN)) {

                        if(testArea(world, startPosMutable, fruitBlock)) {
                            world.setBlockState(pos, state.with(ALLOW_FRUIT, false));
                            break;
                        }

                        if(random.nextInt(4) == 0) {
                            break;
                        }

                        if(random.nextInt(6) == 0) {
                            BlockPos offsetPos = startPosMutable.offset(direction);
                            BlockState offsetState = world.getBlockState(offsetPos);

                            if(offsetState.isAir()) {
                                world.setBlockState(offsetPos, fruitBlock.getDefaultState().with(Apple.FACE, direction.getOpposite()));
                                world.setBlockState(pos, state.with(ALLOW_FRUIT, false));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "appendProperties(Lnet/minecraft/state/StateManager$Builder;)V", at = @At("HEAD"), cancellable = true)
    private void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(LeavesBlock.DISTANCE, LeavesBlock.PERSISTENT, LeavesBlock.WATERLOGGED, LeavesBlockMixin.ALLOW_FRUIT);
        ci.cancel();
    }

    static {
        ALLOW_FRUIT = BooleanProperty.of("allowfruit");
    }

    /* @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerItem = player.getStackInHand(hand);
        Random random = world.getRandom();

        if(state.isOf(Blocks.OAK_LEAVES)) {
            if (playerItem.getItem() == Items.BONE_MEAL) {
                for (Direction direction : Direction.values()) {
                    if(random.nextInt(10) == 0){
                        BlockPos offsetPos = pos.offset(direction);
                        BlockState offsetState = world.getBlockState(pos.offset(direction));

                        if(offsetState.isAir()) {
                            if(!world.isClient()) {
                                world.setBlockState(offsetPos, MBMBlocks.APPLE.getDefaultState().with(Apple.FACE, direction.getOpposite()));
                            }
                            return ActionResult.success(world.isClient());
                        }
                    }
                }
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    } */

    /* @Override
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> ci) {
        Random random = world.getRandom();
        ItemStack playerItem = player.getStackInHand(hand);

        if(state.isOf(Blocks.OAK_LEAVES)) {
            if (playerItem.getItem() == Items.BONE_MEAL) {
                System.out.println("OL");
                if(random.nextInt(30) == 0) {
                    System.out.println("Hit");
                    for (Direction direction : Direction.values()) {
                        if(random.nextInt(30) == 0) {
                            System.out.println("Hit2");
                            BlockPos offsetPos = pos.offset(direction);
                            BlockState offsetState = world.getBlockState(pos.offset(direction));

                            if(offsetState.isAir()) {
                                System.out.println("Hit3");
                                world.setBlockState(offsetPos, MBMBlocks.APPLE.getDefaultState().with(Apple.FACE, direction.getOpposite()));
                                ci.setReturnValue(ActionResult.success(world.isClient));
                            }
                        }
                    }
                }
            }
            ci.setReturnValue(ActionResult.FAIL);
        }
    } */
    

    /* @Inject(method = "randomTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V", at = @At("HEAD"), cancellable = true)
    private void randomTicks(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        System.out.println("Random Tick");
        if(state.isOf(Blocks.OAK_LEAVES)) {
            System.out.println("OL");
            if(random.nextInt(30) == 0) {
                System.out.println("Hit");
                for (Direction direction : Direction.values()) {
                    if(random.nextInt(30) == 0) {
                        System.out.println("Hit2");
                        BlockPos offsetPos = pos.offset(direction);
                        BlockState offsetState = world.getBlockState(pos.offset(direction));

                        if(offsetState.isAir()) {
                            System.out.println("Hit3");
                            world.setBlockState(offsetPos, MBMBlocks.APPLE.getDefaultState().with(Apple.FACE, direction.getOpposite()));
                        }
                    }
                }
            }
        }
    } */
}