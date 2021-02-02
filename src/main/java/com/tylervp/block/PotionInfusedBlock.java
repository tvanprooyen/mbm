package com.tylervp.block;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class PotionInfusedBlock extends Block {
    private final StatusEffect effect;
    public static final BooleanProperty LIT;
    public static final IntProperty INFUSEDSTATE;
    

	public PotionInfusedBlock(StatusEffect effect,Settings settings) {
		super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(PotionInfusedBlock.LIT, false).with(INFUSEDSTATE, 10));
        this.effect = effect;
    }
    
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack item = player.getMainHandStack();

        if(item.getItem() == Items.POTION){
            List<StatusEffectInstance> list5 = PotionUtil.getPotionEffects(item);
            int infusedState = state.<Integer>get((Property<Integer>)PotionInfusedBlock.INFUSEDSTATE);

            if(((StatusEffectInstance)list5.get(0)).getEffectType() == this.effect && infusedState < 10){
                infusedState += 2 * (((StatusEffectInstance)list5.get(0)).getAmplifier() + 1);
                if(infusedState >= 10){
                    infusedState = 10;
                }

                world.setBlockState(pos, (BlockState)state.with(PotionInfusedBlock.INFUSEDSTATE, infusedState), 2);
                return ActionResult.success(world.isClient);
            }
        }
        
        
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        super.onSteppedOn(world, pos, entity);

        BlockState state = world.getBlockState(pos);
        int infusedState = state.<Integer>get((Property<Integer>)PotionInfusedBlock.INFUSEDSTATE);

        List<Entity> EntityList = new ArrayList<Entity>();

        EntityList.add(entity);

        if(entity.getType() == EntityType.ITEM || entity.getType() == EntityType.TNT_MINECART || entity.getType() == EntityType.HOPPER_MINECART || entity.getType() == EntityType.EXPERIENCE_ORB || entity.getType() == EntityType.POTION || entity.getType() == EntityType.LIGHTNING_BOLT || entity.getType() == EntityType.EXPERIENCE_BOTTLE) {
            return;
        } else if(entity.getType() == EntityType.BOAT || entity.getType() == EntityType.MINECART){
            EntityList = entity.getPassengerList();
        }

        for (Entity entities : EntityList) {
            if(infusedState > 0){
                int amp = 0;
                int time = 10;
                int breakAmount = 1;
                
                if(((LivingEntity)entities).hasStatusEffect(this.effect) && state.<Boolean>get((Property<Boolean>)PotionInfusedBlock.LIT)) {
                    return;
                }

                if(world.getBlockState(pos.down()).isOf(Blocks.GOLD_BLOCK)){
                    amp = 1;
                    breakAmount += 1;
                } else if(world.getBlockState(pos.down()).isOf(Blocks.DIAMOND_BLOCK)){
                    amp = 3;
                    breakAmount += 2;
                } else if(world.getBlockState(pos.down()).isOf(Blocks.NETHERITE_BLOCK)){
                    amp = 5;
                    breakAmount += 3;
                }

                if(world.getBlockState(pos.down(2)).isOf(Blocks.GOLD_BLOCK)){
                    time *= 2;
                    breakAmount += 1;
                } else if(world.getBlockState(pos.down(2)).isOf(Blocks.DIAMOND_BLOCK)){
                    time *= 4;
                    breakAmount += 2;
                } else if(world.getBlockState(pos.down(2)).isOf(Blocks.NETHERITE_BLOCK)){
                    time *= 6;
                    breakAmount += 3;
                }

                if((world.getBlockState(pos.down(2)).isOf(Blocks.GOLD_BLOCK) || world.getBlockState(pos.down(2)).isOf(Blocks.DIAMOND_BLOCK) || world.getBlockState(pos.down(2)).isOf(Blocks.NETHERITE_BLOCK)) && world.getBlockState(pos.down(3)).isOf(Blocks.GOLD_BLOCK)){
                    time *= 2;
                    breakAmount += 2;
                } else if((world.getBlockState(pos.down(2)).isOf(Blocks.GOLD_BLOCK) || world.getBlockState(pos.down(2)).isOf(Blocks.DIAMOND_BLOCK) || world.getBlockState(pos.down(2)).isOf(Blocks.NETHERITE_BLOCK)) && world.getBlockState(pos.down(3)).isOf(Blocks.DIAMOND_BLOCK)){
                    time *= 4;
                    breakAmount += 4;
                } else if((world.getBlockState(pos.down(2)).isOf(Blocks.GOLD_BLOCK) || world.getBlockState(pos.down(2)).isOf(Blocks.DIAMOND_BLOCK) || world.getBlockState(pos.down(2)).isOf(Blocks.NETHERITE_BLOCK)) && world.getBlockState(pos.down(3)).isOf(Blocks.NETHERITE_BLOCK)){
                    time *= 6;
                    breakAmount += 6;
                }

                if(!world.isClient){
                    System.out.println("OnStep: " + breakAmount);
                }

                StatusEffectInstance CurrEffectInstance = new StatusEffectInstance(this.effect, time * 20, amp);
                
                if(((LivingEntity)entities).hasStatusEffect(this.effect) && !state.<Boolean>get((Property<Boolean>)PotionInfusedBlock.LIT)){
                    Collection<StatusEffectInstance> StatusEffectCollection = ((LivingEntity)entities).getStatusEffects();
                    for (StatusEffectInstance StatusEffect : StatusEffectCollection) {
                        if(StatusEffect.getEffectType() == this.effect){
                            amp = StatusEffect.getAmplifier();
                            time = (time * 20) + StatusEffect.getDuration();
                            ((LivingEntity)entities).removeStatusEffect(this.effect);
                            CurrEffectInstance = new StatusEffectInstance(this.effect, time, amp);
                        }
                    }
                }
                
                
                breakBlock(world, pos, state, breakAmount);
                ((LivingEntity)entities).addStatusEffect(CurrEffectInstance);

                light(state, world, pos, this.effect);
            } else {
                delight(state, world, pos, this.effect);
            }
        }
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        super.onProjectileHit(world, state, hit, projectile);
        if(projectile instanceof PotionEntity){
            if (world.isClient) {
                return;
            }

            ItemStack itemStack3 = ((PotionEntity)projectile).getStack();
            Potion potion4 = PotionUtil.getPotion(itemStack3);
            List<StatusEffectInstance> list5 = PotionUtil.getPotionEffects(itemStack3);
            boolean boolean6 = potion4 == Potions.WATER && list5.isEmpty();
            BlockPos blockPos8 = hit.getBlockPos();

            if (boolean6) {
                world.setBlockState(blockPos8, (BlockState)state.with(PotionInfusedBlock.INFUSEDSTATE, 0), 2);
            }
        }

        
    }
    
    private void breakBlock(World world, BlockPos pos, BlockState state, int BreakAmount) {
        world.playSound(null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 0.7f, 0.9f + world.random.nextFloat() * 0.2f);
        int integer5 = state.<Integer>get((Property<Integer>)PotionInfusedBlock.INFUSEDSTATE);
        integer5 = integer5 - BreakAmount;
        
        if(integer5 <= 0){
            integer5 = 0;
        }

        if(!world.isClient){
            System.out.println("BreakBlock Infused State: " + integer5);
        }
        
        world.setBlockState(pos, (BlockState)state.with(PotionInfusedBlock.INFUSEDSTATE, integer5), 3);
        world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
    }

    private static void light(BlockState state, World world, BlockPos pos, StatusEffect effect) {
        if (!state.<Boolean>get((Property<Boolean>)PotionInfusedBlock.LIT) && state.<Integer>get((Property<Integer>)PotionInfusedBlock.INFUSEDSTATE) > 0) {
            world.setBlockState(pos, ((BlockState)state).with(PotionInfusedBlock.LIT, true), 3);
        }
    }

    private static void delight(BlockState state, World world, BlockPos pos, StatusEffect effect) {
        if (state.<Boolean>get((Property<Boolean>)PotionInfusedBlock.LIT) && state.<Integer>get((Property<Integer>)PotionInfusedBlock.INFUSEDSTATE) == 0) {
            world.setBlockState(pos, ((BlockState)state).with(PotionInfusedBlock.LIT, false), 3);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.<Boolean>get((Property<Boolean>)PotionInfusedBlock.LIT)) {
            world.setBlockState(pos, ((BlockState)state).with(PotionInfusedBlock.LIT, false), 3);
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.<Boolean>get((Property<Boolean>)PotionInfusedBlock.LIT)) {
            spawnParticles(world, pos, this.effect);
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.<Boolean>get((Property<Boolean>)PotionInfusedBlock.LIT);
    }
    
    private static void spawnParticles(World world, BlockPos pos, StatusEffect effect) {
        //double double3 = 0.5625;
        Random random5 = world.random;
        ParticleEffect partical;

        ParticleEffect RED = DustParticleEffect.RED;
        ParticleEffect GREEN = new DustParticleEffect(0.01f, 1f, 0f, 1.0F);
        ParticleEffect BLUE = new DustParticleEffect(0.01f, 0f, 1f, 1.0F);
        //ParticleEffect CYAN = new DustParticleEffect(0.01f, 1f, 1f, 1.0F);
        ParticleEffect YELLOW = new DustParticleEffect(1f, 1f, 0f, 1.0F);
        //ParticleEffect MAGENTA = new DustParticleEffect(1f, 0f, 1f, 1.0F);
        ParticleEffect BLACK = new DustParticleEffect(0.01f, 0f, 0f, 1.0F);
        ParticleEffect WHITE = new DustParticleEffect(1f, 1f, 1f, 1.0F);
        ParticleEffect AZURE = new DustParticleEffect(0.145f, 0.322f, 0.647f, 1.0F);
        ParticleEffect KHAKI = new DustParticleEffect(0.75f,0.64f,0.3f, 1.0F);
        //ParticleEffect DARK_GRAY = new DustParticleEffect(0.12f,0.12f,0.14f, 1.0F);
        ParticleEffect AMBER = new DustParticleEffect(0.89f,0.6f,0.23f, 1.0F);
        ParticleEffect OLIVE = new DustParticleEffect(0.58f,0.63f,0.38f, 1.0F);
        ParticleEffect ORANGE = new DustParticleEffect(0.97f,0.49f,0.14f, 1.0F);
        ParticleEffect DULL_GREEN = new DustParticleEffect(0.35f,0.46f,0.33f, 1.0F);
        ParticleEffect LIGHT_GRAY = new DustParticleEffect(0.5f,0.51f,0.57f, 1.0F);
        ParticleEffect SKY_BLUE = new DustParticleEffect(0.81f,1f,1f, 1.0F);
        ParticleEffect AVOCADO_GREEN = new DustParticleEffect(0.2f,0.6f,0.01f, 1.0F);
        ParticleEffect DARK_KHAKI = new DustParticleEffect(0.29f,0.26f,0.09f, 1.0F);
        ParticleEffect PURPLE = new DustParticleEffect(0.33f,0.11f,0.29f, 1.0F);
        ParticleEffect MEDIUM_BLUE = new DustParticleEffect(0.12f,0.12f,0.63f, 1.0F);
        ParticleEffect PINK = new DustParticleEffect(0.8f,0.36f,0.67f, 1.0F);
        ParticleEffect MAHOGANY = new DustParticleEffect(0.6f,0.27f,0.23f, 1.0F);
        ParticleEffect GRAY_BLUE = new DustParticleEffect(0.35f,0.42f,0.51f, 1.0F);
        ParticleEffect ELECTRIC_BLUE = new DustParticleEffect(0.49f,0.69f,0.78f, 1.0F);
        ParticleEffect DARK_RED = new DustParticleEffect(0.58f,0.14f,0.14f, 1.0F);
        ParticleEffect GRAY = new DustParticleEffect(0.28f,0.3f,0.28f, 1.0F);
        ParticleEffect BISTRE = new DustParticleEffect(0.21f,0.16f,0.15f, 1.0F);
        ParticleEffect MAROON = new DustParticleEffect(0.26f,0.04f,0.04f, 1.0F);




        for (final Direction lv : Direction.values()) {
            BlockPos blockPos10 = pos.offset(lv);
            if (!world.getBlockState(blockPos10).isOpaqueFullCube(world, blockPos10)) {
                Direction.Axis axis11 = lv.getAxis();
                double double12 = (axis11 == Direction.Axis.X) ? (0.5 + 0.5625 * lv.getOffsetX()) : random5.nextFloat();
                double double14 = (axis11 == Direction.Axis.Y) ? (0.5 + 0.5625 * lv.getOffsetY()) : random5.nextFloat();
                double double16 = (axis11 == Direction.Axis.Z) ? (0.5 + 0.5625 * lv.getOffsetZ()) : random5.nextFloat();

                if(effect == StatusEffects.ABSORPTION){
                    partical = AZURE;
                } else if(effect == StatusEffects.UNLUCK){
                    partical = KHAKI;
                } else if(effect == StatusEffects.BLINDNESS){
                    partical = BLACK;
                } else if(effect == StatusEffects.FIRE_RESISTANCE){
                    partical = AMBER;
                } else if(effect == StatusEffects.GLOWING){
                    partical = OLIVE;
                } else if(effect == StatusEffects.HASTE){
                    partical = YELLOW;
                } else if(effect == StatusEffects.HEALTH_BOOST){
                    partical = ORANGE;
                } else if(effect == StatusEffects.HUNGER){
                    partical = DULL_GREEN;
                } else if(effect == StatusEffects.INSTANT_DAMAGE){
                    partical = MAROON;
                } else if(effect == StatusEffects.INSTANT_HEALTH){
                    partical = RED;
                } else if(effect == StatusEffects.INVISIBILITY){
                    partical = LIGHT_GRAY;
                } else if(effect == StatusEffects.JUMP_BOOST){
                    partical = GREEN; //!!!!
                } else if(effect == StatusEffects.LEVITATION){
                    partical = SKY_BLUE;
                } else if(effect == StatusEffects.LUCK){
                    partical = AVOCADO_GREEN;
                } else if(effect == StatusEffects.MINING_FATIGUE){
                    partical = DARK_KHAKI;
                } else if(effect == StatusEffects.NAUSEA){
                    partical = PURPLE;
                } else if(effect == StatusEffects.NIGHT_VISION){
                    partical = MEDIUM_BLUE;
                } else if(effect == StatusEffects.POISON){
                    partical = GREEN;
                } else if(effect == StatusEffects.REGENERATION){
                    partical = PINK;
                } else if(effect == StatusEffects.RESISTANCE){
                    partical = MAHOGANY;
                } else if(effect == StatusEffects.SATURATION){
                    partical = RED;
                } else if(effect == StatusEffects.SLOWNESS){
                    partical = GRAY_BLUE;
                } else if(effect == StatusEffects.SPEED){
                    partical = ELECTRIC_BLUE;
                } else if(effect == StatusEffects.STRENGTH){
                    partical = DARK_RED;
                } else if(effect == StatusEffects.WATER_BREATHING){
                    partical = BLUE;
                } else if(effect == StatusEffects.WEAKNESS){
                    partical = GRAY;
                } else if(effect == StatusEffects.WITHER){
                    partical = BISTRE;
                } else {
                    partical = WHITE;
                }

                world.addParticle(partical, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.0, 0.0);
            }
        }
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PotionInfusedBlock.LIT, PotionInfusedBlock.INFUSEDSTATE);
    }
    
    static {
        LIT = RedstoneTorchBlock.LIT;
        INFUSEDSTATE = IntProperty.of("infusedstate", 0, 10);
    }
}
