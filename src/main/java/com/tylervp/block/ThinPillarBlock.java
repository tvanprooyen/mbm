package com.tylervp.block;

import java.util.Random;

import com.tylervp.item.MBMItems;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;

public class ThinPillarBlock extends PillarBlock implements Waterloggable {
    protected static final VoxelShape SHAPEX,SHAPEY,SHAPEZ;
    public static final BooleanProperty LEAVES,PRESISTANT;
    public static final EnumProperty<Direction.Axis> AXIS;
    public static final BooleanProperty WATERLOGGED;
    public static final BooleanProperty CHAIN,ROPE;

    protected ThinPillarBlock (AbstractBlock.Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState().with(LEAVES, false).with(PRESISTANT, false).with(Properties.WATERLOGGED, false).with(CHAIN, false).with(ROPE, false));
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        switch (type) {
            case LAND: {
                return false;
            }
            case WATER: {
                return world.getFluidState(pos).isIn(FluidTags.WATER);
            }
            case AIR: {
                return false;
            }
            default: {
                return false;
            }
        }
    }

    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        //super.getOutlineShape(state, world, pos, context);
        Axis axis = state.get(AXIS);

        if(state.get(LEAVES)){
            return VoxelShapes.fullCube();
        } else {
            if(axis == Axis.X){
                return ThinPillarBlock.SHAPEX;

            } else if(axis == Axis.Y){
                return ThinPillarBlock.SHAPEY;

            } else if(axis == Axis.Z){
                return ThinPillarBlock.SHAPEZ;

            } else {
                return ThinPillarBlock.SHAPEZ;
            }
        }
    }


    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.<Boolean>get((Property<Boolean>)ThinPillarBlock.WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        
        if(state.get(ThinPillarBlock.AXIS) != Axis.Y && (world.getBlockState(pos.down()).isOf(Blocks.CHAIN) || world.getBlockState(pos.down()).isOf(Blocks.LANTERN))){
            Boolean lHanging = true;
            
            if(world.getBlockState(pos.down()).isOf(Blocks.LANTERN)){
                lHanging = world.getBlockState(pos.down()).get(LanternBlock.HANGING);
            }

            return (BlockState)this.getDefaultState().with(LEAVES, state.get(LEAVES)).with(ThinPillarBlock.PRESISTANT, state.get(ThinPillarBlock.PRESISTANT)).with(AXIS, state.get(AXIS)).with(Properties.WATERLOGGED, state.get(WATERLOGGED)).with(CHAIN, lHanging).with(ROPE, false);
        } else {

            if(state.get(ThinPillarBlock.AXIS) != Axis.Y && (world.getBlockState(pos.down()).isOf(MBMBlocks.ROPE) || world.getBlockState(pos.down()).isOf(MBMBlocks.ROPEMID))){
                return (BlockState)this.getDefaultState().with(ThinPillarBlock.LEAVES, state.get(ThinPillarBlock.LEAVES)).with(ThinPillarBlock.PRESISTANT, state.get(ThinPillarBlock.PRESISTANT)).with(ThinPillarBlock.AXIS, state.get(ThinPillarBlock.AXIS)).with(Properties.WATERLOGGED, state.get(ThinPillarBlock.WATERLOGGED)).with(ThinPillarBlock.CHAIN, false).with(ThinPillarBlock.ROPE, true);
            }
            else{
                return (BlockState)this.getDefaultState().with(ThinPillarBlock.LEAVES, state.get(ThinPillarBlock.LEAVES)).with(ThinPillarBlock.PRESISTANT, state.get(ThinPillarBlock.PRESISTANT)).with(ThinPillarBlock.AXIS, state.get(ThinPillarBlock.AXIS)).with(Properties.WATERLOGGED, state.get(ThinPillarBlock.WATERLOGGED)).with(ThinPillarBlock.CHAIN, false).with(ThinPillarBlock.ROPE, false);
            }
            
        }
        
        //return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

/*
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(LEAVES)){
            return VoxelShapes.fullCube();
        } else {
            if(state.get(AXIS) == Axis.X){
                return ThinPillarBlock.SHAPEX;

            } else if(state.get(AXIS) == Axis.Y){
                return ThinPillarBlock.SHAPEY;

            } else if(state.get(AXIS) == Axis.Z){
                return ThinPillarBlock.SHAPEZ;

            } else {
                return ThinPillarBlock.SHAPEZ;
            }
        }
    }*/

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerItem = player.getStackInHand(hand);
        Boolean finished = false;
        
        //Tool Actions That Change Blockstates
        if(state.isOf(this)){
            if (playerItem.getItem() == Items.SHEARS && state.get(ThinPillarBlock.LEAVES)) {
                //Take Leaves Off
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                
                world.setBlockState(pos, this.getDefaultState().with(ThinPillarBlock.AXIS, state.get(ThinPillarBlock.AXIS)).with(ThinPillarBlock.LEAVES, false).with(ThinPillarBlock.PRESISTANT, false).with(ThinPillarBlock.CHAIN, state.get(ThinPillarBlock.CHAIN)).with(ThinPillarBlock.ROPE, state.get(ThinPillarBlock.ROPE)).with(ThinPillarBlock.WATERLOGGED, state.get(ThinPillarBlock.WATERLOGGED)));
                if(state.isOf(MBMBlocks.THIN_OAK_LOG)){
                    Block.dropStack(world, pos, new ItemStack(Blocks.OAK_LEAVES.asItem(), 1));
                } else if(state.isOf(MBMBlocks.THIN_SPRUCE_LOG)){
                    Block.dropStack(world, pos, new ItemStack(Blocks.SPRUCE_LEAVES.asItem(), 1));
                } else if(state.isOf(MBMBlocks.THIN_ACACIA_LOG)){
                    Block.dropStack(world, pos, new ItemStack(Blocks.ACACIA_LEAVES.asItem(), 1));
                } else if(state.isOf(MBMBlocks.THIN_BIRCH_LOG)){
                    Block.dropStack(world, pos, new ItemStack(Blocks.BIRCH_LEAVES.asItem(), 1));
                } else if(state.isOf(MBMBlocks.THIN_DARK_OAK_LOG)){
                    Block.dropStack(world, pos, new ItemStack(Blocks.DARK_OAK_LEAVES.asItem(), 1));
                } else if(state.isOf(MBMBlocks.THIN_JUNGLE_LOG)){
                    Block.dropStack(world, pos, new ItemStack(Blocks.JUNGLE_LEAVES.asItem(), 1));
                }
                
                //Damage shears as it was used
                playerItem.<PlayerEntity>damage(1, player, (p) -> p.sendToolBreakStatus(hand));
                finished = true;
            } else if ((playerItem.getItem() == Items.WOODEN_AXE || playerItem.getItem() == Items.STONE_AXE || playerItem.getItem() == Items.GOLDEN_AXE || playerItem.getItem() == Items.IRON_AXE || playerItem.getItem() == Items.DIAMOND_AXE || playerItem.getItem() == Items.NETHERITE_AXE) && !state.get(LEAVES)) {
                //Strip Log
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_AXE_STRIP, SoundCategory.NEUTRAL, 1.0f, 1.0f);

                if(state.isOf(MBMBlocks.THIN_OAK_LOG)){
                    world.setBlockState(pos, MBMBlocks.THIN_STRIPPED_OAK_LOG.getDefaultState().with(ThinPillarBlock.AXIS, state.get(ThinPillarBlock.AXIS)).with(ThinPillarBlock.LEAVES, false).with(ThinPillarBlock.PRESISTANT, false).with(ThinPillarBlock.CHAIN, state.get(ThinPillarBlock.CHAIN)).with(ThinPillarBlock.ROPE, state.get(ThinPillarBlock.ROPE)).with(ThinPillarBlock.WATERLOGGED, state.get(ThinPillarBlock.WATERLOGGED)));
                    Block.dropStack(world, pos, new ItemStack(MBMItems.OAK_BARK_FRAGMENT.asItem(), 2));
                } else if(state.isOf(MBMBlocks.THIN_SPRUCE_LOG)){
                    world.setBlockState(pos, MBMBlocks.THIN_STRIPPED_SPRUCE_LOG.getDefaultState().with(ThinPillarBlock.AXIS, state.get(ThinPillarBlock.AXIS)).with(ThinPillarBlock.LEAVES, false).with(ThinPillarBlock.PRESISTANT, false).with(ThinPillarBlock.CHAIN, state.get(ThinPillarBlock.CHAIN)).with(ThinPillarBlock.ROPE, state.get(ThinPillarBlock.ROPE)).with(ThinPillarBlock.WATERLOGGED, state.get(ThinPillarBlock.WATERLOGGED)));
                    Block.dropStack(world, pos, new ItemStack(MBMItems.SPRUCE_BARK_FRAGMENT.asItem(), 2));
                } else if(state.isOf(MBMBlocks.THIN_ACACIA_LOG)){
                    world.setBlockState(pos, MBMBlocks.THIN_STRIPPED_ACACIA_LOG.getDefaultState().with(ThinPillarBlock.AXIS, state.get(ThinPillarBlock.AXIS)).with(ThinPillarBlock.LEAVES, false).with(ThinPillarBlock.PRESISTANT, false).with(ThinPillarBlock.CHAIN, state.get(ThinPillarBlock.CHAIN)).with(ThinPillarBlock.ROPE, state.get(ThinPillarBlock.ROPE)).with(ThinPillarBlock.WATERLOGGED, state.get(ThinPillarBlock.WATERLOGGED)));
                    Block.dropStack(world, pos, new ItemStack(MBMItems.ACACIA_BARK_FRAGMENT.asItem(), 2));
                } else if(state.isOf(MBMBlocks.THIN_BIRCH_LOG)){
                    world.setBlockState(pos, MBMBlocks.THIN_STRIPPED_BIRCH_LOG.getDefaultState().with(ThinPillarBlock.AXIS, state.get(ThinPillarBlock.AXIS)).with(ThinPillarBlock.LEAVES, false).with(ThinPillarBlock.PRESISTANT, false).with(ThinPillarBlock.CHAIN, state.get(ThinPillarBlock.CHAIN)).with(ThinPillarBlock.ROPE, state.get(ThinPillarBlock.ROPE)).with(ThinPillarBlock.WATERLOGGED, state.get(ThinPillarBlock.WATERLOGGED)));
                    Block.dropStack(world, pos, new ItemStack(MBMItems.BIRCH_BARK_FRAGMENT.asItem(), 2));
                } else if(state.isOf(MBMBlocks.THIN_DARK_OAK_LOG)){
                    world.setBlockState(pos, MBMBlocks.THIN_STRIPPED_DARK_OAK_LOG.getDefaultState().with(ThinPillarBlock.AXIS, state.get(ThinPillarBlock.AXIS)).with(ThinPillarBlock.LEAVES, false).with(ThinPillarBlock.PRESISTANT, false).with(ThinPillarBlock.CHAIN, state.get(ThinPillarBlock.CHAIN)).with(ThinPillarBlock.ROPE, state.get(ThinPillarBlock.ROPE)).with(ThinPillarBlock.WATERLOGGED, state.get(ThinPillarBlock.WATERLOGGED)));
                    Block.dropStack(world, pos, new ItemStack(MBMItems.DARK_OAK_BARK_FRAGMENT.asItem(), 2));
                } else if(state.isOf(MBMBlocks.THIN_JUNGLE_LOG)){
                    world.setBlockState(pos, MBMBlocks.THIN_STRIPPED_JUNGLE_LOG.getDefaultState().with(ThinPillarBlock.AXIS, state.get(ThinPillarBlock.AXIS)).with(ThinPillarBlock.LEAVES, false).with(ThinPillarBlock.PRESISTANT, false).with(ThinPillarBlock.CHAIN, state.get(ThinPillarBlock.CHAIN)).with(ThinPillarBlock.ROPE, state.get(ThinPillarBlock.ROPE)).with(ThinPillarBlock.WATERLOGGED, state.get(ThinPillarBlock.WATERLOGGED)));
                    Block.dropStack(world, pos, new ItemStack(MBMItems.JUNGLE_BARK_FRAGMENT.asItem(), 2));
                }
                
                //Damage Tool as it was used
                if(
                    !state.isOf(MBMBlocks.THIN_STRIPPED_OAK_LOG) || 
                    !state.isOf(MBMBlocks.THIN_STRIPPED_SPRUCE_LOG) || 
                    !state.isOf(MBMBlocks.THIN_STRIPPED_ACACIA_LOG) || 
                    !state.isOf(MBMBlocks.THIN_STRIPPED_BIRCH_LOG) || 
                    !state.isOf(MBMBlocks.THIN_STRIPPED_DARK_OAK_LOG) || 
                    !state.isOf(MBMBlocks.THIN_STRIPPED_JUNGLE_LOG)
                ){
                    playerItem.<PlayerEntity>damage(1, player, (p) -> p.sendToolBreakStatus(hand));
                }
                finished = true;
            }
        }

        //This means that the player should swing there arm.
        if(finished){
            return ActionResult.success(true);
        }
        
        return super.onUse(state, world, pos, player, hand, hit);
    }
    

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        BlockState state = ctx.getWorld().getBlockState(pos);
        World world = ctx.getWorld();
        PlayerEntity player = ctx.getPlayer();
        FluidState fuildstate = ctx.getWorld().getFluidState(pos);

        if(state.isOf(this)){
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            return (BlockState)this.getDefaultState().with(LEAVES, true).with(AXIS, state.get(AXIS)).with(Properties.WATERLOGGED, fuildstate.getFluid() == Fluids.WATER).with(CHAIN, false).with(ROPE, false);
        }

        Boolean lHanging = true;
        
        if(world.getBlockState(pos.down()).isOf(Blocks.CHAIN) || world.getBlockState(pos.down()).isOf(Blocks.LANTERN)) {
            if(world.getBlockState(pos.down()).isOf(Blocks.LANTERN)){
                lHanging = world.getBlockState(pos.down()).get(LanternBlock.HANGING);
            }
        } else {
            lHanging = false;
        }

        world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.NEUTRAL, 1.0f, 1.0f);
        return (BlockState)this.getDefaultState().with(LEAVES, false).with(AXIS, ctx.getSide().getAxis()).with(Properties.WATERLOGGED, fuildstate.getFluid() == Fluids.WATER).with(CHAIN, (ctx.getPlayerFacing().getAxis() != Axis.Y && lHanging)).with(ROPE, ctx.getPlayerFacing().getAxis() != Axis.Y && (world.getBlockState(pos.down()).isOf(MBMBlocks.ROPE) || world.getBlockState(pos.down()).isOf(MBMBlocks.ROPEMID)));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.NEUTRAL, 1.0f, 1.0f);
        super.onBreak(world, pos, state, player);
    }
    

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        if(state.get(ThinPillarBlock.LEAVES) == false){
            return 
            (context.getStack().getItem() == Items.OAK_LEAVES && state.isOf(MBMBlocks.THIN_OAK_LOG)) || 
            (context.getStack().getItem() == Items.SPRUCE_LEAVES && state.isOf(MBMBlocks.THIN_SPRUCE_LOG)) || 
            (context.getStack().getItem() == Items.ACACIA_LEAVES && state.isOf(MBMBlocks.THIN_ACACIA_LOG)) || 
            (context.getStack().getItem() == Items.BIRCH_LEAVES && state.isOf(MBMBlocks.THIN_BIRCH_LOG)) || 
            (context.getStack().getItem() == Items.DARK_OAK_LEAVES && state.isOf(MBMBlocks.THIN_DARK_OAK_LOG)) || 
            (context.getStack().getItem() == Items.JUNGLE_LEAVES && state.isOf(MBMBlocks.THIN_JUNGLE_LOG));
        }
        return false;
    }
    

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

        //BlockState BSup = world.getBlockState(pos.up());
        //BlockState BSdown = world.getBlockState(pos.down());
        BlockState BSnorth = world.getBlockState(pos.north());
        BlockState BSeast = world.getBlockState(pos.east());
        BlockState BSsouth = world.getBlockState(pos.south());
        BlockState BSwest = world.getBlockState(pos.west());

        //Boolean hasLeaves = state.<Boolean>get((Property<Boolean>)ThinPillarBlock.LEAVES);
        Boolean isPresistant = state.<Boolean>get((Property<Boolean>)ThinPillarBlock.PRESISTANT);
        //if(!hasLeaves){
            /*if(
                ((state.isOf(moreblocksmod.THIN_OAK_LOG)) && (BSnorth.isOf(Blocks.OAK_LEAVES) || BSeast.isOf(Blocks.OAK_LEAVES) || BSsouth.isOf(Blocks.OAK_LEAVES) || BSwest.isOf(Blocks.OAK_LEAVES))) || 
                ((state.isOf(moreblocksmod.THIN_SPRUCE_LOG)) && (BSnorth.isOf(Blocks.SPRUCE_LEAVES) || BSeast.isOf(Blocks.SPRUCE_LEAVES) || BSsouth.isOf(Blocks.SPRUCE_LEAVES) || BSwest.isOf(Blocks.SPRUCE_LEAVES))) || 
                ((state.isOf(moreblocksmod.THIN_ACACIA_LOG)) && (BSnorth.isOf(Blocks.ACACIA_LEAVES) || BSeast.isOf(Blocks.ACACIA_LEAVES) || BSsouth.isOf(Blocks.ACACIA_LEAVES) || BSwest.isOf(Blocks.ACACIA_LEAVES))) || 
                ((state.isOf(moreblocksmod.THIN_BIRCH_LOG)) && (BSnorth.isOf(Blocks.BIRCH_LEAVES) || BSeast.isOf(Blocks.BIRCH_LEAVES) || BSsouth.isOf(Blocks.BIRCH_LEAVES) || BSwest.isOf(Blocks.BIRCH_LEAVES))) || 
                ((state.isOf(moreblocksmod.THIN_DARK_OAK_LOG)) && (BSnorth.isOf(Blocks.DARK_OAK_LEAVES) || BSeast.isOf(Blocks.DARK_OAK_LEAVES) || BSsouth.isOf(Blocks.DARK_OAK_LEAVES) || BSwest.isOf(Blocks.DARK_OAK_LEAVES))) || 
                ((state.isOf(moreblocksmod.THIN_JUNGLE_LOG)) && (BSnorth.isOf(Blocks.JUNGLE_LEAVES) || BSeast.isOf(Blocks.JUNGLE_LEAVES) || BSsouth.isOf(Blocks.JUNGLE_LEAVES) || BSwest.isOf(Blocks.JUNGLE_LEAVES))) || 
                (BSnorth.isOf(this) || BSeast.isOf(this) || BSsouth.isOf(this) || BSwest.isOf(this))
            ){*/

                Boolean hasLeavesNORTH = false, hasLeavesEAST = false, hasLeavesSOUTH = false, hasLeavesWEST = false;

                if(
                    (BSnorth.isOf(Blocks.OAK_LEAVES) && state.isOf(MBMBlocks.THIN_OAK_LOG)) || 
                    (BSnorth.isOf(Blocks.SPRUCE_LEAVES) && state.isOf(MBMBlocks.THIN_SPRUCE_LOG)) || 
                    (BSnorth.isOf(Blocks.ACACIA_LEAVES) && state.isOf(MBMBlocks.THIN_ACACIA_LOG)) || 
                    (BSnorth.isOf(Blocks.BIRCH_LEAVES) && state.isOf(MBMBlocks.THIN_BIRCH_LOG)) || 
                    (BSnorth.isOf(Blocks.DARK_OAK_LEAVES) && state.isOf(MBMBlocks.THIN_DARK_OAK_LOG)) || 
                    (BSnorth.isOf(Blocks.JUNGLE_LEAVES) && state.isOf(MBMBlocks.THIN_JUNGLE_LOG))
                ) { hasLeavesNORTH = true; } 
                else if(BSnorth.isOf(this)){hasLeavesNORTH = BSnorth.<Boolean>get((Property<Boolean>)ThinPillarBlock.LEAVES);}

                
                if(
                    (BSeast.isOf(Blocks.OAK_LEAVES) && state.isOf(MBMBlocks.THIN_OAK_LOG)) || 
                    (BSeast.isOf(Blocks.SPRUCE_LEAVES) && state.isOf(MBMBlocks.THIN_SPRUCE_LOG)) || 
                    (BSeast.isOf(Blocks.ACACIA_LEAVES) && state.isOf(MBMBlocks.THIN_ACACIA_LOG)) || 
                    (BSeast.isOf(Blocks.BIRCH_LEAVES) && state.isOf(MBMBlocks.THIN_BIRCH_LOG)) || 
                    (BSeast.isOf(Blocks.DARK_OAK_LEAVES) && state.isOf(MBMBlocks.THIN_DARK_OAK_LOG)) || 
                    (BSeast.isOf(Blocks.JUNGLE_LEAVES) && state.isOf(MBMBlocks.THIN_JUNGLE_LOG))
                ) { hasLeavesEAST = true; }
                else if(BSeast.isOf(this)){hasLeavesEAST = BSeast.<Boolean>get((Property<Boolean>)ThinPillarBlock.LEAVES);}

                
                if(
                    (BSsouth.isOf(Blocks.OAK_LEAVES) && state.isOf(MBMBlocks.THIN_OAK_LOG)) || 
                    (BSsouth.isOf(Blocks.SPRUCE_LEAVES) && state.isOf(MBMBlocks.THIN_SPRUCE_LOG)) || 
                    (BSsouth.isOf(Blocks.ACACIA_LEAVES) && state.isOf(MBMBlocks.THIN_ACACIA_LOG)) || 
                    (BSsouth.isOf(Blocks.BIRCH_LEAVES) && state.isOf(MBMBlocks.THIN_BIRCH_LOG)) || 
                    (BSsouth.isOf(Blocks.DARK_OAK_LEAVES) && state.isOf(MBMBlocks.THIN_DARK_OAK_LOG)) || 
                    (BSsouth.isOf(Blocks.JUNGLE_LEAVES) && state.isOf(MBMBlocks.THIN_JUNGLE_LOG))
                ) { hasLeavesSOUTH = true; }
                else if(BSsouth.isOf(this)){hasLeavesSOUTH = BSsouth.<Boolean>get((Property<Boolean>)ThinPillarBlock.LEAVES);}

                
                if(
                    (BSwest.isOf(Blocks.OAK_LEAVES) && state.isOf(MBMBlocks.THIN_OAK_LOG)) || 
                    (BSwest.isOf(Blocks.SPRUCE_LEAVES) && state.isOf(MBMBlocks.THIN_SPRUCE_LOG)) || 
                    (BSwest.isOf(Blocks.ACACIA_LEAVES) && state.isOf(MBMBlocks.THIN_ACACIA_LOG)) || 
                    (BSwest.isOf(Blocks.BIRCH_LEAVES) && state.isOf(MBMBlocks.THIN_BIRCH_LOG)) || 
                    (BSwest.isOf(Blocks.DARK_OAK_LEAVES) && state.isOf(MBMBlocks.THIN_DARK_OAK_LOG)) || 
                    (BSwest.isOf(Blocks.JUNGLE_LEAVES) && state.isOf(MBMBlocks.THIN_JUNGLE_LOG))
                ) { hasLeavesWEST = true; }
                else if(BSwest.isOf(this)){hasLeavesWEST = BSwest.<Boolean>get((Property<Boolean>)ThinPillarBlock.LEAVES);}

                //System.out.println(this.toString() + " | North: " + hasLeavesNORTH.toString() + " East: " + hasLeavesEAST.toString() + " South: " + hasLeavesSOUTH.toString() + " West: " + hasLeavesWEST.toString());
                
                //hasLeavesUP || hasLeavesDOWN || 
                if((hasLeavesNORTH || hasLeavesEAST || hasLeavesSOUTH || hasLeavesWEST)){
                    world.setBlockState(pos, this.getDefaultState().with(ThinPillarBlock.LEAVES, true).with(ThinPillarBlock.AXIS, state.get(ThinPillarBlock.AXIS)).with(ThinPillarBlock.PRESISTANT, isPresistant).with(ThinPillarBlock.WATERLOGGED, state.get(ThinPillarBlock.WATERLOGGED)).with(ThinPillarBlock.CHAIN, state.get(ThinPillarBlock.CHAIN)).with(ThinPillarBlock.ROPE, state.get(ThinPillarBlock.ROPE)));
                } else if (!(hasLeavesNORTH && hasLeavesEAST && hasLeavesSOUTH && hasLeavesWEST) && !isPresistant) {
                    world.setBlockState(pos, this.getDefaultState().with(ThinPillarBlock.LEAVES, false).with(ThinPillarBlock.AXIS, state.get(ThinPillarBlock.AXIS)).with(ThinPillarBlock.PRESISTANT, isPresistant).with(ThinPillarBlock.WATERLOGGED, state.get(ThinPillarBlock.WATERLOGGED)).with(ThinPillarBlock.CHAIN, state.get(ThinPillarBlock.CHAIN)).with(ThinPillarBlock.ROPE, state.get(ThinPillarBlock.ROPE)));
                } 
            //}
        //}
        super.randomTick(state, world, pos, random);
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        Boolean hasLeaves = state.<Boolean>get((Property<Boolean>)ThinPillarBlock.LEAVES);
        if(hasLeaves){
            if(state.isOf(MBMBlocks.THIN_OAK_LOG)){
                Block.dropStack(world, pos, new ItemStack(Blocks.OAK_LEAVES.asItem(), 1));
            } else if(state.isOf(MBMBlocks.THIN_SPRUCE_LOG)){
                Block.dropStack(world, pos, new ItemStack(Blocks.SPRUCE_LEAVES.asItem(), 1));
            } else if(state.isOf(MBMBlocks.THIN_ACACIA_LOG)){
                Block.dropStack(world, pos, new ItemStack(Blocks.ACACIA_LEAVES.asItem(), 1));
            } else if(state.isOf(MBMBlocks.THIN_BIRCH_LOG)){
                Block.dropStack(world, pos, new ItemStack(Blocks.BIRCH_LEAVES.asItem(), 1));
            } else if(state.isOf(MBMBlocks.THIN_DARK_OAK_LOG)){
                Block.dropStack(world, pos, new ItemStack(Blocks.DARK_OAK_LEAVES.asItem(), 1));
            } else if(state.isOf(MBMBlocks.THIN_JUNGLE_LOG)){
                Block.dropStack(world, pos, new ItemStack(Blocks.JUNGLE_LEAVES.asItem(), 1));
            }
        }
        super.onStacksDropped(state, world, pos, stack);
    }

    
    @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.<Boolean>get((Property<Boolean>)WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ThinPillarBlock.AXIS,LEAVES,PRESISTANT,WATERLOGGED,CHAIN, ROPE);
    }

    static {
        SHAPEZ = Block.createCuboidShape(4.0, 4.0, 0.0, 12.0, 12.0, 16.0);
        SHAPEX = Block.createCuboidShape(0.0, 4.0, 4.0, 16.0, 12.0, 12.0);
        SHAPEY = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
        LEAVES = BooleanProperty.of("leaves");
        PRESISTANT = BooleanProperty.of("presistant");
        CHAIN = BooleanProperty.of("chain");
        ROPE = BooleanProperty.of("rope");
        AXIS = Properties.AXIS;
        WATERLOGGED = Properties.WATERLOGGED;
    }
}