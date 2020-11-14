package com.tylervp;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ChoppedLog extends Block {
    public static final DirectionProperty FACING;
    public static final BooleanProperty UP_FACING, DOWN_FACING;
    //public static final EnumProperty<Direction.Axis> AXIS;
    public static final IntProperty CHOPPEDSTATE;
    protected static final VoxelShape OUTLINE_SHAPE_1_NORTH, OUTLINE_SHAPE_2_NORTH, OUTLINE_SHAPE_3_NORTH;
    protected static final VoxelShape OUTLINE_SHAPE_1_EAST, OUTLINE_SHAPE_2_EAST, OUTLINE_SHAPE_3_EAST;
    protected static final VoxelShape OUTLINE_SHAPE_1_SOUTH, OUTLINE_SHAPE_2_SOUTH, OUTLINE_SHAPE_3_SOUTH;
    protected static final VoxelShape OUTLINE_SHAPE_1_WEST, OUTLINE_SHAPE_2_WEST, OUTLINE_SHAPE_3_WEST;

    protected static final VoxelShape OUTLINE_SHAPE_1_NORTH_BOTTOM, OUTLINE_SHAPE_3_NORTH_BOTTOM;
    protected static final VoxelShape OUTLINE_SHAPE_1_EAST_BOTTOM, OUTLINE_SHAPE_3_EAST_BOTTOM;
    protected static final VoxelShape OUTLINE_SHAPE_1_SOUTH_BOTTOM, OUTLINE_SHAPE_3_SOUTH_BOTTOM;
    protected static final VoxelShape OUTLINE_SHAPE_1_WEST_BOTTOM, OUTLINE_SHAPE_3_WEST_BOTTOM;

    protected static final VoxelShape OUTLINE_SHAPE_1_NORTH_TOP, OUTLINE_SHAPE_3_NORTH_TOP;
    protected static final VoxelShape OUTLINE_SHAPE_1_EAST_TOP, OUTLINE_SHAPE_3_EAST_TOP;
    protected static final VoxelShape OUTLINE_SHAPE_1_SOUTH_TOP, OUTLINE_SHAPE_3_SOUTH_TOP;
    protected static final VoxelShape OUTLINE_SHAPE_1_WEST_TOP, OUTLINE_SHAPE_3_WEST_TOP;

    protected static final VoxelShape OUTLINE_SHAPE_2_BOTTOM, OUTLINE_SHAPE_2_TOP;
    

    public ChoppedLog(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(ChoppedLog.CHOPPEDSTATE, 1).with(ChoppedLog.DOWN_FACING, false).with(ChoppedLog.UP_FACING, false));
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(state.isOf(this)) {
            ItemStack playerItem = player.getStackInHand(hand);

            if(!(state.isOf(moreblocksmod.CHOPPED_STRIPPED_ACACIA_LOG) || state.isOf(moreblocksmod.CHOPPED_STRIPPED_BIRCH_LOG) || state.isOf(moreblocksmod.CHOPPED_STRIPPED_DARK_OAK_LOG)  || state.isOf(moreblocksmod.CHOPPED_STRIPPED_JUNGLE_LOG)  || state.isOf(moreblocksmod.CHOPPED_STRIPPED_OAK_LOG)  || state.isOf(moreblocksmod.CHOPPED_STRIPPED_SPRUCE_LOG))) {
                if ((playerItem.getItem() == Items.WOODEN_AXE || playerItem.getItem() == Items.STONE_AXE || playerItem.getItem() == Items.GOLDEN_AXE || playerItem.getItem() == Items.IRON_AXE || playerItem.getItem() == Items.DIAMOND_AXE || playerItem.getItem() == Items.NETHERITE_AXE)){
                    
                    BlockState ChangeState = state;
                    ItemConvertible bark_item = this.asItem();

                    if(state.isOf(moreblocksmod.CHOPPED_ACACIA_LOG)) {
                        ChangeState = moreblocksmod.CHOPPED_STRIPPED_ACACIA_LOG.getDefaultState();
                        bark_item = moreblocksmod.ACACIA_BARK_FRAGMENT.asItem();
                    } else if(state.isOf(moreblocksmod.CHOPPED_BIRCH_LOG)) {
                        ChangeState = moreblocksmod.CHOPPED_STRIPPED_BIRCH_LOG.getDefaultState();
                        bark_item = moreblocksmod.BIRCH_BARK_FRAGMENT.asItem();
                    } else if(state.isOf(moreblocksmod.CHOPPED_DARK_OAK_LOG)) {
                        ChangeState = moreblocksmod.CHOPPED_STRIPPED_DARK_OAK_LOG.getDefaultState();
                        bark_item = moreblocksmod.DARK_OAK_BARK_FRAGMENT.asItem();
                    } else if(state.isOf(moreblocksmod.CHOPPED_JUNGLE_LOG)) {
                        ChangeState = moreblocksmod.CHOPPED_STRIPPED_JUNGLE_LOG.getDefaultState();
                        bark_item = moreblocksmod.JUNGLE_BARK_FRAGMENT.asItem();
                    } else if(state.isOf(moreblocksmod.CHOPPED_OAK_LOG)) {
                        ChangeState = moreblocksmod.CHOPPED_STRIPPED_OAK_LOG.getDefaultState();
                        bark_item = moreblocksmod.OAK_BARK_FRAGMENT.asItem();
                    } else if(state.isOf(moreblocksmod.CHOPPED_SPRUCE_LOG)) {
                        ChangeState = moreblocksmod.CHOPPED_STRIPPED_SPRUCE_LOG.getDefaultState();
                        bark_item = moreblocksmod.SPRUCE_BARK_FRAGMENT.asItem();
                    }

                    Block.dropStack(world, pos, new ItemStack(bark_item, state.get(ChoppedLog.CHOPPEDSTATE)));
                    world.setBlockState(pos, ChangeState.with(ChoppedLog.CHOPPEDSTATE, state.get(ChoppedLog.CHOPPEDSTATE)).with(ChoppedLog.DOWN_FACING, state.get(ChoppedLog.DOWN_FACING)).with(ChoppedLog.UP_FACING, state.get(ChoppedLog.UP_FACING)).with(ChoppedLog.FACING, state.get(ChoppedLog.FACING)));
                    
                    world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_AXE_STRIP, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                    playerItem.<PlayerEntity>damage(1, player, (p) -> p.sendToolBreakStatus(hand));
                    return ActionResult.success(world.isClient);
                }
            }
            
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
    
    @Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        int choppedstate = state.<Integer>get((Property<Integer>)ChoppedLog.CHOPPEDSTATE);
        Direction facing = state.get(ChoppedLog.FACING);

        if(choppedstate == 2 && state.get(ChoppedLog.DOWN_FACING)) {
            return OUTLINE_SHAPE_2_BOTTOM;
        }

        if(choppedstate == 2 && state.get(ChoppedLog.UP_FACING)) {
            return OUTLINE_SHAPE_2_TOP;
        }

        if(state.get(ChoppedLog.DOWN_FACING) && state.get(ChoppedLog.UP_FACING)){
            return VoxelShapes.fullCube();
        }

        switch (facing) {
            case NORTH:
                switch(choppedstate) {
                    case 1:
                        if(state.get(ChoppedLog.DOWN_FACING)) {
                            return OUTLINE_SHAPE_1_NORTH_BOTTOM;
                        } else if(state.get(ChoppedLog.UP_FACING)) {
                            return OUTLINE_SHAPE_1_NORTH_TOP;
                        } else {
                            return OUTLINE_SHAPE_1_NORTH;
                        }
                        
                    case 2:
                        return OUTLINE_SHAPE_2_NORTH;
                    case 3:
                    if(state.get(ChoppedLog.DOWN_FACING)) {
                        return OUTLINE_SHAPE_3_NORTH_BOTTOM;
                    } else if(state.get(ChoppedLog.UP_FACING)) {
                        return OUTLINE_SHAPE_3_NORTH_TOP;
                    } else {
                        return OUTLINE_SHAPE_3_NORTH;
                    }
                    default:
                        return VoxelShapes.fullCube();
                }

            case EAST:
                switch(choppedstate) {
                    case 1:
                        if(state.get(ChoppedLog.DOWN_FACING)) {
                            return OUTLINE_SHAPE_1_EAST_BOTTOM;
                        } else if(state.get(ChoppedLog.UP_FACING)) {
                            return OUTLINE_SHAPE_1_EAST_TOP;
                        } else {
                            return OUTLINE_SHAPE_1_EAST;
                        }
                    case 2:
                        return OUTLINE_SHAPE_2_EAST;
                    case 3:
                        if(state.get(ChoppedLog.DOWN_FACING)) {
                            return OUTLINE_SHAPE_3_EAST_BOTTOM;
                        } else if(state.get(ChoppedLog.UP_FACING)) {
                            return OUTLINE_SHAPE_3_EAST_TOP;
                        } else {
                            return OUTLINE_SHAPE_3_EAST;
                        }
                        
                    default:
                        return VoxelShapes.fullCube();
                }

            case SOUTH:
                switch(choppedstate) {
                    case 1:
                        if(state.get(ChoppedLog.DOWN_FACING)) {
                            return OUTLINE_SHAPE_1_SOUTH_BOTTOM;
                        } else if(state.get(ChoppedLog.UP_FACING)) {
                            return OUTLINE_SHAPE_1_SOUTH_TOP;
                        } else {
                            return OUTLINE_SHAPE_1_SOUTH;
                        }
                    case 2:
                        return OUTLINE_SHAPE_2_SOUTH;
                    case 3:
                        if(state.get(ChoppedLog.DOWN_FACING)) {
                            return OUTLINE_SHAPE_3_SOUTH_BOTTOM;
                        } else if(state.get(ChoppedLog.UP_FACING)) {
                            return OUTLINE_SHAPE_3_SOUTH_TOP;
                        } else {
                            return OUTLINE_SHAPE_3_SOUTH;
                        }
                        
                    default:
                        return VoxelShapes.fullCube();
                }

            case WEST:
                switch(choppedstate) {
                    case 1:
                        if(state.get(ChoppedLog.DOWN_FACING)) {
                            return OUTLINE_SHAPE_1_WEST_BOTTOM;
                        } else if(state.get(ChoppedLog.UP_FACING)) {
                            return OUTLINE_SHAPE_1_WEST_TOP;
                        } else {
                            return OUTLINE_SHAPE_1_WEST;
                        }
                    case 2:
                        return OUTLINE_SHAPE_2_WEST;
                    case 3:
                        if(state.get(ChoppedLog.DOWN_FACING)) {
                            return OUTLINE_SHAPE_3_WEST_BOTTOM;
                        } else if(state.get(ChoppedLog.UP_FACING)) {
                            return OUTLINE_SHAPE_3_WEST_TOP;
                        } else {
                            return OUTLINE_SHAPE_3_WEST;
                        }
                        
                    default:
                        return VoxelShapes.fullCube();
                }
        
            default:
                return VoxelShapes.fullCube();
        }
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        int choppedstate = state.<Integer>get((Property<Integer>)ChoppedLog.CHOPPEDSTATE);
        BlockPos pos = context.getBlockPos();

        if (context.getStack().getItem() == this.asItem() && choppedstate < 4 && ((((context.getHitPos().x - pos.getX()) > 0.1) && ((context.getHitPos().x - pos.getX()) < 0.9)) && ((((context.getHitPos().z - pos.getZ()) > 0.1) && (context.getHitPos().z - pos.getZ()) < 0.9)))) {
            return context.canReplaceExisting() && (((context.getHitPos().y - pos.getY()) < 1.0) && ((context.getHitPos().y - pos.getY()) > 0.0));
        }
        return choppedstate > 4;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
		BlockState state = ctx.getWorld().getBlockState(pos);
        FluidState fluidState5 = ctx.getWorld().getFluidState(pos);
        Direction facing;
        
        if (state.isOf(this)) {
            int choppedstate = state.<Integer>get((Property<Integer>)ChoppedLog.CHOPPEDSTATE);
            Direction currentFacing = state.get(ChoppedLog.FACING);
            facing = currentFacing;

            if(!state.get(ChoppedLog.UP_FACING) && !state.get(ChoppedLog.DOWN_FACING)){
                if(((ctx.getHitPos().x - pos.getX()) == 0.5) && ((ctx.getHitPos().z - pos.getZ()) < 0.5)){
                    if(currentFacing == Direction.EAST || choppedstate == 1){
                        facing = Direction.SOUTH;
                    } else {
                        facing = currentFacing;
                    }
                    
                } else if(((ctx.getHitPos().x - pos.getX()) == 0.5) && ((ctx.getHitPos().z - pos.getZ()) > 0.5)){
                    if(currentFacing == Direction.WEST || choppedstate == 1){
                        facing = Direction.NORTH;
                    } else {
                        facing = currentFacing;
                    }

                } else if(((ctx.getHitPos().x - pos.getX()) < 0.5) && ((ctx.getHitPos().z - pos.getZ()) == 0.5)){
                    if(currentFacing == Direction.NORTH || choppedstate == 1){
                        facing = Direction.EAST;
                    } else {
                        facing = currentFacing;
                    }

                } else if(((ctx.getHitPos().x - pos.getX()) > 0.5) && ((ctx.getHitPos().z - pos.getZ()) == 0.5)){
                    if(currentFacing == Direction.SOUTH || choppedstate == 1){
                        facing = Direction.WEST;
                    } else {
                        
                        facing = currentFacing;
                    }
                    
                }
            } else {
                if(choppedstate == 2){
                    if(facing == Direction.NORTH || facing == Direction.SOUTH){
                        if(((ctx.getHitPos().x - pos.getX()) < 0.5)){
                            facing = Direction.NORTH;
                        } else {
                            facing = Direction.SOUTH;
                        }
                    } else {
                        if(((ctx.getHitPos().z - pos.getZ()) < 0.5)){
                            facing = Direction.EAST;
                        } else {
                            facing = Direction.WEST;
                        }
                    }

                    facing = facing.getOpposite();
                }
            }

            return state.with(ChoppedLog.CHOPPEDSTATE, Math.min(4, choppedstate + 1)).with(ChoppedLog.FACING, facing);
        }

        facing = ctx.getPlayerFacing();
        Boolean upFacing = false;
        Boolean downFacing = false;

        if(!(ctx.getPlayerLookDirection() == Direction.DOWN || ctx.getPlayerLookDirection() == Direction.UP )){
            if(((ctx.getHitPos().x - pos.getX()) < 0.5)){
                if(((ctx.getHitPos().z - pos.getZ()) < 0.5)){
                    facing = Direction.NORTH;
                } else {
                    facing = Direction.WEST;
                }
            } else {
                if(((ctx.getHitPos().z - pos.getZ()) < 0.5)){
                    facing = Direction.EAST;
                } else {
                    facing = Direction.SOUTH;
                }
            }
        } else {
            facing = ctx.getPlayerFacing();
            
            if(ctx.getPlayerLookDirection() == Direction.DOWN){
                downFacing = true;
            } else if(ctx.getPlayerLookDirection() == Direction.UP) {
                upFacing = true;
            }

            /* switch (facing) {
                case NORTH:
                    if(((ctx.getHitPos().x - pos.getX()) < 0.5)){
                        facing = Direction.SOUTH;
                    } else {
                        facing = Direction.NORTH;
                    }
                    break;
                case EAST:
                    if(((ctx.getHitPos().z - pos.getZ()) < 0.5)){
                        facing = Direction.SOUTH;
                    } else {
                        facing = Direction.EAST;
                    }
                    break;
                case SOUTH:
                    if(((ctx.getHitPos().x - pos.getX()) < 0.5)){
                        facing = Direction.SOUTH;
                    } else {
                        facing = Direction.NORTH;
                    }
                    break;
                case WEST:
                    if(((ctx.getHitPos().z - pos.getZ()) < 0.5)){
                        facing = Direction.SOUTH;
                    } else {
                        facing = Direction.NORTH;
                    }
                    break;
                default:
                    break;
            } */


            if(facing == Direction.NORTH || facing == Direction.SOUTH){
                if(((ctx.getHitPos().x - pos.getX()) < 0.5)){
                    facing = Direction.NORTH;
                } else {
                    facing = Direction.SOUTH;
                }
            } else {
                if(((ctx.getHitPos().z - pos.getZ()) < 0.5)){
                    facing = Direction.EAST;
                } else {
                    facing = Direction.WEST;
                }
            }


        }
        
		return (BlockState)this.getDefaultState().with(ChoppedLog.CHOPPEDSTATE, 1).with(ChoppedLog.FACING, facing.getOpposite()).with(ChoppedLog.DOWN_FACING, downFacing).with(ChoppedLog.UP_FACING, upFacing);

        //return super.getPlacementState(ctx);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(ChoppedLog.CHOPPEDSTATE, ChoppedLog.FACING, ChoppedLog.DOWN_FACING, ChoppedLog.UP_FACING);//ChoppedLog.FACING, //ChoppedLog.AXIS, 
    }
    
    static {
        FACING = Properties.HORIZONTAL_FACING;
        UP_FACING = BooleanProperty.of("up");
        DOWN_FACING = BooleanProperty.of("down");
        CHOPPEDSTATE = IntProperty.of("chopstate", 1, 4);

        VoxelShape tempShapes;
        //Shapes
        //Top
        OUTLINE_SHAPE_2_TOP = Block.createCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

        //Bottom
        OUTLINE_SHAPE_2_BOTTOM = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

        //North - Original
        OUTLINE_SHAPE_1_NORTH = Block.createCuboidShape(8.0, 0.0, 8.0, 16.0, 16.0, 16.0);
        OUTLINE_SHAPE_2_NORTH = Block.createCuboidShape(0.0, 0.0, 8.0, 16.0, 16.0, 16.0);
        tempShapes = VoxelShapes.combineAndSimplify(Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, 16.0, 8.0), ChoppedLog.OUTLINE_SHAPE_2_NORTH, BooleanBiFunction.ONLY_FIRST);
        OUTLINE_SHAPE_3_NORTH =  VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), tempShapes, BooleanBiFunction.ONLY_FIRST);

        OUTLINE_SHAPE_1_NORTH_BOTTOM = Block.createCuboidShape(8.0, 0.0, 0.0, 16.0, 8.0, 16.0);
        OUTLINE_SHAPE_1_NORTH_TOP = Block.createCuboidShape(8.0, 8.0, 0.0, 16.0, 16.0, 16.0);
        
        //East
        OUTLINE_SHAPE_1_EAST = Block.createCuboidShape(0.0, 0.0, 8.0, 8.0, 16.0, 16.0);
        OUTLINE_SHAPE_2_EAST = Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, 16.0, 16.0);
        tempShapes = VoxelShapes.combineAndSimplify(Block.createCuboidShape(8.0, 0.0, 0.0, 16.0, 16.0, 8.0), ChoppedLog.OUTLINE_SHAPE_2_EAST, BooleanBiFunction.ONLY_FIRST);
        OUTLINE_SHAPE_3_EAST =  VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), tempShapes, BooleanBiFunction.ONLY_FIRST);

        OUTLINE_SHAPE_1_EAST_BOTTOM = Block.createCuboidShape(0.0, 0.0, 8.0, 16.0, 8.0, 16.0);
        OUTLINE_SHAPE_1_EAST_TOP = Block.createCuboidShape(0.0, 8.0, 8.0, 16.0, 16.0, 16.0);
        
        //South
        OUTLINE_SHAPE_1_SOUTH = Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, 16.0, 8.0);
        OUTLINE_SHAPE_2_SOUTH = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 8.0);
        tempShapes = VoxelShapes.combineAndSimplify(Block.createCuboidShape(8.0, 0.0, 8.0, 16.0, 16.0, 16.0), ChoppedLog.OUTLINE_SHAPE_2_SOUTH, BooleanBiFunction.ONLY_FIRST);
        OUTLINE_SHAPE_3_SOUTH =  VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), tempShapes, BooleanBiFunction.ONLY_FIRST);

        OUTLINE_SHAPE_1_SOUTH_BOTTOM = Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, 8.0, 16.0);
        OUTLINE_SHAPE_1_SOUTH_TOP = Block.createCuboidShape(0.0, 8.0, 0.0, 8.0, 16.0, 16.0);
        
        //West
        OUTLINE_SHAPE_1_WEST = Block.createCuboidShape(8.0, 0.0, 0.0, 16.0, 16.0, 8.0); 
        OUTLINE_SHAPE_2_WEST = Block.createCuboidShape(8.0, 0.0, 0.0, 16.0, 16.0, 16.0);
        tempShapes = VoxelShapes.combineAndSimplify(Block.createCuboidShape(0.0, 0.0, 8.0, 8.0, 16.0, 16.0), ChoppedLog.OUTLINE_SHAPE_2_WEST, BooleanBiFunction.ONLY_FIRST);
        OUTLINE_SHAPE_3_WEST =  VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), tempShapes, BooleanBiFunction.ONLY_FIRST);

        OUTLINE_SHAPE_1_WEST_BOTTOM = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 8.0);
        OUTLINE_SHAPE_1_WEST_TOP = Block.createCuboidShape(0.0, 8.0, 0.0, 16.0, 16.0, 8.0);

        //Build Shapes
        OUTLINE_SHAPE_3_NORTH_BOTTOM = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), OUTLINE_SHAPE_1_SOUTH_TOP, BooleanBiFunction.ONLY_FIRST);
        OUTLINE_SHAPE_3_EAST_BOTTOM = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), OUTLINE_SHAPE_1_WEST_TOP, BooleanBiFunction.ONLY_FIRST);
        OUTLINE_SHAPE_3_SOUTH_BOTTOM = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), OUTLINE_SHAPE_1_NORTH_TOP, BooleanBiFunction.ONLY_FIRST);
        OUTLINE_SHAPE_3_WEST_BOTTOM = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), OUTLINE_SHAPE_1_EAST_TOP, BooleanBiFunction.ONLY_FIRST);

        OUTLINE_SHAPE_3_NORTH_TOP = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), OUTLINE_SHAPE_1_SOUTH_BOTTOM, BooleanBiFunction.ONLY_FIRST);
        OUTLINE_SHAPE_3_EAST_TOP = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), OUTLINE_SHAPE_1_WEST_BOTTOM, BooleanBiFunction.ONLY_FIRST);
        OUTLINE_SHAPE_3_SOUTH_TOP = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), OUTLINE_SHAPE_1_NORTH_BOTTOM, BooleanBiFunction.ONLY_FIRST);
        OUTLINE_SHAPE_3_WEST_TOP = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), OUTLINE_SHAPE_1_EAST_BOTTOM, BooleanBiFunction.ONLY_FIRST);
    

    }
}
