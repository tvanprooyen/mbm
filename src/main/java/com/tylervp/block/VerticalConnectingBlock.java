/* package com.tylervp;

import java.util.Map;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConnectingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class VerticalConnectingBlock extends Block implements Waterloggable {
    public static final BooleanProperty UP;
    public static final BooleanProperty DOWN;
    public static final BooleanProperty WATERLOGGED;
    protected static final Map<Direction, BooleanProperty> FACING_PROPERTIES;
    protected final VoxelShape[] collisionShapes;
    protected final VoxelShape[] boundingShapes;
    private final Object2IntMap<BlockState> SHAPE_INDEX_CACHE;
    
    protected VerticalConnectingBlock(float radius1, float radius2, float boundingHeight1, float boundingHeight2, float collisionHeight, AbstractBlock.Settings settings) {
        super(settings);
        this.SHAPE_INDEX_CACHE = new Object2IntOpenHashMap<BlockState>();
        this.collisionShapes = this.createShapes(radius1, radius2, collisionHeight, 0.0f, collisionHeight);
        this.boundingShapes = this.createShapes(radius1, radius2, boundingHeight1, 0.0f, boundingHeight2);
        for (final BlockState lv : this.stateManager.getStates()) {
            this.getShapeIndex(lv);
        }
    }
    
    protected VoxelShape[] createShapes(float radius1, float radius2, float height1, float offset2, float height2) {
        float float7 = 8.0f - radius1;
        float float8 = 8.0f + radius1;
        float float9 = 8.0f - radius2;
        float float10 = 8.0f + radius2;
        VoxelShape voxelShape11 = Block.createCuboidShape(float7, 0.0, float7, float8, height1, float8);
        VoxelShape voxelShape12 = Block.createCuboidShape(float9, offset2, 0.0, float10, height2, float10);
        VoxelShape voxelShape13 = Block.createCuboidShape(float9, offset2, float9, float10, height2, 16.0);
        VoxelShape voxelShape14 = Block.createCuboidShape(0.0, offset2, float9, float10, height2, float10);
        VoxelShape voxelShape15 = Block.createCuboidShape(float9, offset2, float9, 16.0, height2, float10);
        VoxelShape voxelShape16 = VoxelShapes.union(voxelShape12, voxelShape15);
        VoxelShape voxelShape17 = VoxelShapes.union(voxelShape13, voxelShape14);
        VoxelShape[] lvs = { VoxelShapes.empty(), voxelShape13, voxelShape14, voxelShape17, voxelShape12, VoxelShapes.union(voxelShape13, voxelShape12), VoxelShapes.union(voxelShape14, voxelShape12), VoxelShapes.union(voxelShape17, voxelShape12), voxelShape15, VoxelShapes.union(voxelShape13, voxelShape15), VoxelShapes.union(voxelShape14, voxelShape15), VoxelShapes.union(voxelShape17, voxelShape15), voxelShape16, VoxelShapes.union(voxelShape13, voxelShape16), VoxelShapes.union(voxelShape14, voxelShape16), VoxelShapes.union(voxelShape17, voxelShape16) };
        for (int o = 0; o < 16; ++o) {
            lvs[o] = VoxelShapes.union(voxelShape11, lvs[o]);
        }
        return lvs;
    }
    
    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return !state.<Boolean>get((Property<Boolean>)VerticalConnectingBlock.WATERLOGGED);
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.boundingShapes[this.getShapeIndex(state)];
    }
    
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.collisionShapes[this.getShapeIndex(state)];
    }
    
    private static int getDirectionMask(Direction dir) {
        return 1 << dir.getHorizontal();
    }
    
    protected int getShapeIndex(BlockState state) {
        return this.SHAPE_INDEX_CACHE.computeIntIfAbsent(state, blockState -> {
            int integer2 = 0;
            if (blockState.<Boolean>get((Property<Boolean>)VerticalConnectingBlock.UP)) {
                integer2 |= getDirectionMask(Direction.UP);
            }
            if (blockState.<Boolean>get((Property<Boolean>)VerticalConnectingBlock.DOWN)) {
                integer2 |= getDirectionMask(Direction.DOWN);
            }
            return integer2;
        });
    }
    
    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.<Boolean>get((Property<Boolean>)VerticalConnectingBlock.WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }
    
    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
    
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        switch (mirror) {
            case LEFT_RIGHT: {
                return (((State<O, BlockState>)state).with((Property<Comparable>)HorizontalConnectingBlock.NORTH, (Comparable)state.<V>get((Property<V>)HorizontalConnectingBlock.SOUTH))).<Comparable, Comparable>with((Property<Comparable>)HorizontalConnectingBlock.SOUTH, (Comparable)state.<V>get((Property<V>)HorizontalConnectingBlock.NORTH));
            }
            case FRONT_BACK: {
                return (((State<O, BlockState>)state).with((Property<Comparable>)HorizontalConnectingBlock.EAST, (Comparable)state.<V>get((Property<V>)HorizontalConnectingBlock.WEST))).<Comparable, Comparable>with((Property<Comparable>)HorizontalConnectingBlock.WEST, (Comparable)state.<V>get((Property<V>)HorizontalConnectingBlock.EAST));
            }
            default: {
                return super.mirror(state, mirror);
            }
        }
    }
    
    static {
        UP = ConnectingBlock.UP;
        DOWN = ConnectingBlock.DOWN;
        WATERLOGGED = Properties.WATERLOGGED;
        FACING_PROPERTIES = ConnectingBlock.FACING_PROPERTIES.entrySet().stream().filter(entry -> entry.getKey().getAxis().isHorizontal()).collect(Util.<Direction, BooleanProperty>toMap());
    }
}
 */