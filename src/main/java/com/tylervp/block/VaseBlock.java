package com.tylervp.block;

import java.util.Random;

import com.tylervp.block.entity.VaseBlockEntity;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class VaseBlock extends BlockWithEntity {
    protected static final VoxelShape VASE_SHAPE/* , RAYTRACE_SHAPE */;

    public VaseBlock(AbstractBlock.Settings settings) {
        super(settings);
        
    }
//ShulkerBoxBlock  BarrelBlock

    @Override
   public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
      return new VaseBlockEntity(pos, state);
   }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
     }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VASE_SHAPE;
    }

   @Override
   public PistonBehavior getPistonBehavior(BlockState state) {
      return PistonBehavior.DESTROY;
   }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
       if(!player.isSneaking()){
          if (world.isClient) {
            return ActionResult.SUCCESS;
         } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof VaseBlockEntity) {
               player.openHandledScreen((VaseBlockEntity)blockEntity);
               //player.incrementStat(Stats.OPEN_BARREL);
            }
   
            return ActionResult.CONSUME;
         }
       } else {
          return super.onUse(state, world, pos, player, hand, hit);
       }
        
     }
  
     public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
           BlockEntity blockEntity = world.getBlockEntity(pos);
           if (blockEntity instanceof Inventory) {
              ItemScatterer.spawn(world, pos, (Inventory)blockEntity);
              world.updateComparators(pos, this);
           }
  
           super.onStateReplaced(state, world, pos, newState, moved);
        }
     }
  
     public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof VaseBlockEntity) {
           ((VaseBlockEntity)blockEntity).tick();
        }
  
     }
  
     public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasCustomName()) {
           BlockEntity blockEntity = world.getBlockEntity(pos);
           if (blockEntity instanceof VaseBlockEntity) {
              ((VaseBlockEntity)blockEntity).setCustomName(itemStack.getName());
           }
        }
  
     }
  
     public boolean hasComparatorOutput(BlockState state) {
        return true;
     }
  
     public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
     }
    

    static {
        
        VoxelShape NECK_SHAPE = VoxelShapes.combineAndSimplify(Block.createCuboidShape(2.0, 12.0, 2.0, 14.0, 14.0, 14.0), Block.createCuboidShape(5.0, 12.0, 5.0, 11.0, 14.0, 11.0), BooleanBiFunction.ONLY_FIRST);
        VoxelShape TOP_SHAPE = VoxelShapes.combineAndSimplify(Block.createCuboidShape(2.0, 14.0, 2.0, 14.0, 16.0, 14.0), Block.createCuboidShape(4.0, 14.0, 4.0, 12.0, 16.0, 12.0), BooleanBiFunction.ONLY_FIRST);
        VoxelShape BOTTLESHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);

        VoxelShape SHAPE_1 = VoxelShapes.combineAndSimplify(BOTTLESHAPE, TOP_SHAPE, BooleanBiFunction.ONLY_FIRST);
        VoxelShape SHAPE_2 = VoxelShapes.combineAndSimplify(SHAPE_1, NECK_SHAPE, BooleanBiFunction.ONLY_FIRST);
        VASE_SHAPE = VoxelShapes.combineAndSimplify(SHAPE_2, Block.createCuboidShape(6.0, 14.0, 6.0, 10.0, 16.0, 10.0), BooleanBiFunction.ONLY_FIRST);
    }
}