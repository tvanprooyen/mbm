package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class SpikeBlock extends Block {
    protected static final VoxelShape UP_DOWN_SHAPE;
    protected static final VoxelShape UP_TOP_SHAPE;
    protected static final VoxelShape DOWN_TOP_SHAPE;
    protected static final VoxelShape NORTH_SOUTH_SHAPE;
    protected static final VoxelShape NORTH_TOP_SHAPE;
    protected static final VoxelShape EAST_WEST_SHAPE;
    protected static final VoxelShape EAST_TOP_SHAPE;
    protected static final VoxelShape SOUTH_TOP_SHAPE;
    protected static final VoxelShape WEST_TOP_SHAPE;




    public static final BooleanProperty TOP;
    public static final BooleanProperty INTI_HIT;
    public static final DirectionProperty DIRECTION;


    public SpikeBlock(AbstractBlock.Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState().with(SpikeBlock.TOP, true).with(SpikeBlock.INTI_HIT, false));
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
       if(!entity.isSneaking() && world.getBlockState(pos).get(SpikeBlock.DIRECTION) == Direction.UP) {
           float landingMutiplyer = (fallDistance * 2);
            entity.damage(world.getDamageSources().fall(), landingMutiplyer);
       } else {
           super.onLandedUpon(world, state, pos, entity, fallDistance);
       }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(SpikeBlock.DIRECTION)) {
            case UP: return state.get(SpikeBlock.TOP) ? SpikeBlock.UP_TOP_SHAPE : SpikeBlock.UP_DOWN_SHAPE;
            case DOWN: return state.get(SpikeBlock.TOP) ? SpikeBlock.DOWN_TOP_SHAPE : SpikeBlock.UP_DOWN_SHAPE;
            case NORTH: return state.get(SpikeBlock.TOP) ? SpikeBlock.NORTH_TOP_SHAPE : SpikeBlock.NORTH_SOUTH_SHAPE;
            case EAST: return state.get(SpikeBlock.TOP) ? SpikeBlock.EAST_TOP_SHAPE : SpikeBlock.EAST_WEST_SHAPE;
            case SOUTH: return state.get(SpikeBlock.TOP) ? SpikeBlock.SOUTH_TOP_SHAPE : SpikeBlock.NORTH_SOUTH_SHAPE;
            case WEST: return state.get(SpikeBlock.TOP) ? SpikeBlock.WEST_TOP_SHAPE : SpikeBlock.EAST_WEST_SHAPE;
            default: return SpikeBlock.UP_DOWN_SHAPE;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getOutlineShape(state, world, pos, context);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.NORMAL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();

        BlockPos.Mutable mutable3 = pos.mutableCopy();
        BlockState forwardBlockState = world.getBlockState(mutable3.set(pos, ctx.getSide()));
        if(forwardBlockState.isOf(this)) {
            if(forwardBlockState.get(SpikeBlock.DIRECTION) == ctx.getSide()) {
                return (BlockState)this.getDefaultState().with(SpikeBlock.TOP, false).with(SpikeBlock.DIRECTION, ctx.getSide());
            }
        }

        return (BlockState)this.getDefaultState().with(SpikeBlock.DIRECTION, ctx.getSide());
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {

        if(state.get(SpikeBlock.DIRECTION) != Direction.UP && entity.getVelocity().getY() > -0.1){
            world.setBlockState(pos, this.getDefaultState().with(SpikeBlock.TOP, state.get(SpikeBlock.TOP)).with(SpikeBlock.DIRECTION, state.get(SpikeBlock.DIRECTION)).with(SpikeBlock.INTI_HIT, false));
        }

        /* if((entity.getVelocity().getX() > 0.05 && state.get(SpikeBlock.DIRECTION).getAxis() == Axis.X)){ // || (entity.getVelocity().getZ() > 0.1 && state.get(SpikeBlock.DIRECTION).getAxis() == Axis.Z)
            world.setBlockState(pos, this.getDefaultState().with(SpikeBlock.TOP, state.get(SpikeBlock.TOP)).with(SpikeBlock.DIRECTION, state.get(SpikeBlock.DIRECTION)).with(SpikeBlock.INTI_HIT, true));
        } */
        
        if(state.get(SpikeBlock.INTI_HIT) && state.get(SpikeBlock.DIRECTION) != Direction.UP){
            world.setBlockState(pos, this.getDefaultState().with(SpikeBlock.TOP, state.get(SpikeBlock.TOP)).with(SpikeBlock.DIRECTION, state.get(SpikeBlock.DIRECTION)).with(SpikeBlock.INTI_HIT, false));
            entity.damage(world.getDamageSources().generic(), 4f);
        }


        //System.out.println((entity.getVelocity().getX() > 0.05 && state.get(SpikeBlock.DIRECTION).getAxis() == Axis.X));
    }

    private Boolean checkForwardBlockPos(BlockPos posFrom, BlockPos pos){
        return ((posFrom.getX() == pos.getX()) && (posFrom.getY() == pos.getY()) && (posFrom.getZ() == pos.getZ()));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {

        if(state.isOf(this) && newState.isOf(Blocks.MOVING_PISTON)){
            BlockPos.Mutable mutable = pos.mutableCopy();
            BlockState projectedState = world.getBlockState(mutable.set(mutable.set(pos, state.get(SpikeBlock.DIRECTION).getOpposite()), state.get(SpikeBlock.DIRECTION).getOpposite()));
            if(!projectedState.isOf(Blocks.MOVING_PISTON)){
                return this.getDefaultState().with(SpikeBlock.TOP, state.get(SpikeBlock.TOP)).with(SpikeBlock.DIRECTION, state.get(SpikeBlock.DIRECTION)).with(SpikeBlock.INTI_HIT, true);
            }
        }

        
        
        BlockPos.Mutable mutable3 = pos.mutableCopy();
        if(newState.isOf(this)){
            if(state.get(SpikeBlock.DIRECTION) == newState.get(SpikeBlock.DIRECTION)) {
                if(checkForwardBlockPos(posFrom, mutable3.set(pos, state.get(SpikeBlock.DIRECTION)))){   //if(((posFrom.getX() == mutable3.set(pos, state.get(SpikeBlock.DIRECTION)).getX()) && (posFrom.getY() == mutable3.set(pos, state.get(SpikeBlock.DIRECTION)).getY()) && (posFrom.getZ() == mutable3.set(pos, state.get(SpikeBlock.DIRECTION)).getZ()))){
                    return this.getDefaultState().with(SpikeBlock.TOP, false).with(SpikeBlock.DIRECTION, state.get(SpikeBlock.DIRECTION));
                }
            }
        } else if(!newState.isOf(this)){
            if(checkForwardBlockPos(posFrom, mutable3.set(pos, state.get(SpikeBlock.DIRECTION)))){
                return this.getDefaultState().with(SpikeBlock.TOP, true).with(SpikeBlock.DIRECTION, state.get(SpikeBlock.DIRECTION));
            }
        }

        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    /* private void debug(WorldAccess world, Object object){
        if(world.isClient()){
            System.out.println(object);
        }
    } */

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SpikeBlock.TOP, SpikeBlock.INTI_HIT, SpikeBlock.DIRECTION);
    }


    static {

        UP_DOWN_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0); //GOOD
        UP_TOP_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 11.0, 10.0); // GOOD
        DOWN_TOP_SHAPE = Block.createCuboidShape(6.0, 5.0, 6.0, 10.0, 16.0, 10.0); // GOOD
        NORTH_TOP_SHAPE = Block.createCuboidShape(6.0, 6.0, 5.0, 10.0, 10.0, 16.0);// GOOD
        EAST_TOP_SHAPE = Block.createCuboidShape(0.0, 6.0, 6.0, 11.0, 10.0, 10.0);// GOOD
        SOUTH_TOP_SHAPE = Block.createCuboidShape(6.0, 6.0, 0.0, 10.0, 10.0, 11.0);// GOOD
        WEST_TOP_SHAPE = Block.createCuboidShape(5.0, 6.0, 6.0, 16.0, 10.0, 10.0);// GOOD

        NORTH_SOUTH_SHAPE = Block.createCuboidShape(6.0, 6.0, 0.0, 10.0, 10.0, 16.0);//DONE
        EAST_WEST_SHAPE = Block.createCuboidShape(0.0, 6.0, 6.0, 16.0, 10.0, 10.0);//DONE
        
        TOP = BooleanProperty.of("top");
        INTI_HIT = BooleanProperty.of("init_hit");
        DIRECTION = DirectionProperty.of("direction", Direction.values());
    }
}
