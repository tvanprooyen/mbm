package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class FramingBlock extends Block {
    protected static final VoxelShape OUTLINE_SHAPE_Y, RAY_TRACE_SHAPE_Y, OUTLINE_SHAPE_Y_FULL;
    protected static final IntProperty CENTERBLOCK;

    public FramingBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(CENTERBLOCK, 0));
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int CurrentCenterBlock = state.get(CENTERBLOCK);

        if(CurrentCenterBlock != 0){
            return FramingBlock.OUTLINE_SHAPE_Y_FULL;
        } else {
            return FramingBlock.OUTLINE_SHAPE_Y;
        }

        
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int CurrentCenterBlock = state.get(CENTERBLOCK);

        if(CurrentCenterBlock != 0){
            return FramingBlock.OUTLINE_SHAPE_Y_FULL;
        } else {
            return FramingBlock.OUTLINE_SHAPE_Y;
        }
    }
    
    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return FramingBlock.RAY_TRACE_SHAPE_Y;
    }

   /* @Override
   public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState state = world.getBlockState(pos);
        

        if(state.isOf(this)){
            Item PlayerItem = ctx.getStack().getItem();

            if(PlayerItem == Items.COBBLESTONE){
                return (BlockState)this.getDefaultState().with(FramingBlock.CENTERBLOCK, 1);
            }

        }
    
        return super.getPlacementState(ctx);
   }
 */
   /* @Override
   public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
            Boolean NorthBlock = !world.getBlockState(pos.north()).isAir();
            Boolean EastBlock = !world.getBlockState(pos.east()).isAir();
            Boolean SouthBlock = !world.getBlockState(pos.south()).isAir();
            Boolean WestBlock = !world.getBlockState(pos.west()).isAir();
        
            return (BlockState)this.getDefaultState().with(NORTH, NorthBlock).with(EAST, EastBlock).with(SOUTH, SouthBlock).with(WEST, WestBlock);
   } */

   
   /* @Override
   public boolean canReplace(BlockState state, ItemPlacementContext context) {
    return super.canReplace(state, context);
   } */

   /* @Override
   public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        if(state.isOf(this)){
            int CurrentCenterBlock = state.get(CENTERBLOCK);
            if(CurrentCenterBlock == 1){
                if(stack.getItem() == Items.WOODEN_PICKAXE || stack.getItem() == Items.IRON_PICKAXE || stack.getItem() == Items.DIAMOND_PICKAXE || stack.getItem() == Items.NETHERITE_PICKAXE){
                    Block.dropStack(world, pos,new ItemStack(Blocks.COBBLESTONE.asItem(), 1));
                    return;
                }
            } else {
                if(stack.getItem() == Items.WOODEN_AXE || stack.getItem() == Items.IRON_AXE || stack.getItem() == Items.DIAMOND_AXE || stack.getItem() == Items.NETHERITE_AXE){
                    Block.dropStack(world, pos,new ItemStack(this.asItem(), 1));
                    return;
                }
            }
        }

       super.onStacksDropped(state, world, pos, stack);
   } */

   /* @Override
   public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        if(state.isOf(this)){
            int CurrentCenterBlock = state.get(CENTERBLOCK);

            if(CurrentCenterBlock == 1){
                world.setBlockState(pos, (BlockState)Blocks.COBBLESTONE.getDefaultState(), 32);
                Player player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 1, false);
                ItemStack stack= player.getStackInHand(hand);
                if(requiredTools(insideBlock(state).getDefaultState(), stack)) {
                    return BreakDrop(state, world, pos, stack);
                }
            }
            return;
        }

       super.onBroken(world, pos, state);
   } */

    public Block insideBlock(BlockState state){   
        switch (insideBlockInt(state)) {
            case 1: 
                return Blocks.COBBLESTONE;
            default:
                return this;
        }
    }

    public boolean requiredTools(BlockState state, ItemStack stack){
        switch (insideBlockInt(state)) {
            case 0:
                return stack.getItem() == Items.WOODEN_AXE || stack.getItem() == Items.IRON_AXE || stack.getItem() == Items.DIAMOND_AXE || stack.getItem() == Items.NETHERITE_AXE;
            case 1: 
                return stack.getItem() == Items.WOODEN_PICKAXE || stack.getItem() == Items.IRON_PICKAXE || stack.getItem() == Items.DIAMOND_PICKAXE || stack.getItem() == Items.NETHERITE_PICKAXE;
            default:
                return false;
        }
    }


    public int insideBlockInt(BlockState state){
        return state.get(CENTERBLOCK);
    }


    public ActionResult BreakDrop(BlockState state, World world, BlockPos pos, ItemStack stack){
        if(requiredTools(state, stack)){
            Block CurrentBlock = insideBlock(state);
            Block.dropStack(world, pos,new ItemStack(CurrentBlock.asItem(), 1));
            world.setBlockState(pos, (BlockState)CurrentBlock.getDefaultState());
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

   @Override
   public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(state.isOf(this) && state.get(CENTERBLOCK) == 0){
            Item PlayerItem = player.getStackInHand(hand).getItem();
            //ItemStack stack= player.getStackInHand(hand);

            if(PlayerItem == Items.COBBLESTONE){
                world.setBlockState(pos, (BlockState)this.getDefaultState().with(CENTERBLOCK, 1));
                return ActionResult.SUCCESS;
            }/*  else if(requiredTools(insideBlock(state).getDefaultState(), stack)) {
                return BreakDrop(state, world, pos, stack);
            } */
        }


       return super.onUse(state, world, pos, player, hand, hit);
   }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(FramingBlock.CENTERBLOCK);
    }

    static {
        RAY_TRACE_SHAPE_Y = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);

        VoxelShape Side1 = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), Block.createCuboidShape(0.0, 0.0, 5.0, 1.0, 16.0, 11.0), BooleanBiFunction.ONLY_FIRST);
        VoxelShape Side2 = VoxelShapes.combineAndSimplify(Side1, Block.createCuboidShape(5.0, 0.0, 0.0, 11.0, 16.0, 1.0), BooleanBiFunction.ONLY_FIRST);
        VoxelShape Side3 = VoxelShapes.combineAndSimplify(Side2, Block.createCuboidShape(5.0, 0.0, 15.0, 11.0, 16.0, 16.0), BooleanBiFunction.ONLY_FIRST);
        VoxelShape Side4 = VoxelShapes.combineAndSimplify(Side3, Block.createCuboidShape(15.0, 0.0, 5.0, 16.0, 16.0, 11.0), BooleanBiFunction.ONLY_FIRST);

        OUTLINE_SHAPE_Y = VoxelShapes.combineAndSimplify(Side4, FramingBlock.RAY_TRACE_SHAPE_Y, BooleanBiFunction.ONLY_FIRST);

        OUTLINE_SHAPE_Y_FULL = Side4;

        CENTERBLOCK = IntProperty.of("centerblock", 0, 1);
    }
    
}
