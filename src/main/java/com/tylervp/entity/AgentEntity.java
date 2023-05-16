package com.tylervp.entity;

import java.util.EnumSet;

import com.tylervp.entity.goal.FollowOwnerGoal;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlazedTerracottaBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.EntityView;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

/*
 * Our Cube Entity extends MobEntityWithAi, which extends MobEntity, which extends LivingEntity.
 *
 * LivingEntity has health and can deal damage.
 * MobEntity has movement controls and AI capabilities.
 * MobEntityWithAi has pathfinding favor and slightly tweaked leash behavior.    implements IAnimatedEntity
 */
public class AgentEntity extends FriendEntity implements InventoryOwner {
    protected static final TrackedData<Byte> CubeEntityFlags;
    //protected static final TrackedData<Integer> CubeEntityIntFlags;
    private static double GoalSpeed = 0.6;
    public final SimpleInventory inventory;

    public int TaskNumber = 0;
    public int TaskNumberSave = -1;
    public int BlockBreakProgress = 0;

    public boolean OnStatus = false;
    public boolean StartFollowArrowPath = false;
    public boolean StartMovetoEntity = false;
    public boolean StartMine = false;

    public boolean isMining;

    public boolean Wave = false;

    public NbtCompound compoundTag = new NbtCompound();
    public NbtCompound taskData = new NbtCompound();

    private int Adder = 1;

    private LivingEntity TargetEntity;

    private BlockPos CurrentLookPos;

    
    private final double MaxBlockTimeOut;
    private double BlockTimeOut;

    //EntityAnimationManager manager = new EntityAnimationManager();
    //EntityAnimationController controller = new EntityAnimationController<>(this, "walkController", 20, this::animationPredicate);
    
    /* private <E extends Entity> boolean animationPredicate(AnimationTestEvent<E> event) {
        controller.setAnimation(new AnimationBuilder().addAnimation("walk"));
        return true;
    } */



    public AgentEntity(EntityType<? extends AgentEntity> entityType, World world) {
        super(entityType, world);
        this.inventory = new SimpleInventory(1);

        this.isMining = false;
        this.TargetEntity = null;

        this.CurrentLookPos = BlockPos.ORIGIN;

        this.MaxBlockTimeOut = 20;
        this.BlockTimeOut = this.MaxBlockTimeOut;

        this.setCanPickUpLoot(true);

        this.setPersistent();


       // manager.addAnimationController(controller);
    }

    /* @Override
    public EntityAnimationManager getAnimationManager() {
        return manager;
    } */

    @Override
    public SimpleInventory getInventory() {
        return this.inventory;
    }

    @Override
    public boolean canPickupItem(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false;
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        setBlockBreakProgress(-1);
        this.drop(damageSource);
    }

    @Override
    protected void dropInventory() {
        for (int i = 0; i < 8; i++) {
            if(!this.inventory.getStack(i).isEmpty()) {
                //Block.dropStack(world, this.getBlockPos(), this.inventory.getStack(i));
                this.dropStack(this.inventory.getStack(i));
                this.inventory.removeStack(i);
            }
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new FollowArrowPath(this, GoalSpeed));
        /* this.targetSelector.add(2, new ActiveTargetGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, true)); //new FollowMobGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, false));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f)); */
        this.goalSelector.add(2, new FollowOwnerGoal(this, GoalSpeed, 2, 30, false));
        this.goalSelector.add(3, new SwimGoal(this));
        this.goalSelector.add(4, new Mine(this, GoalSpeed));
    }

    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
        super.initEquipment(random, localDifficulty);
    }

    @Override
    public void setCurrentHand(Hand hand) {
        System.out.println(hand);
        super.setCurrentHand(hand);
    }

    /* @Override
    protected void onBlockCollision(BlockState state) {
        super.onBlockCollision(state);
        if(state.isOf(Blocks.SOUL_SAND) || state.isOf(Blocks.SOUL_SOIL)){
            this.setForwardSpeed(this.forwardSpeed + 0.5f);
        }
    } */
    
    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ActionResult status = ActionResult.FAIL;

        setTargetEntity((LivingEntity)player);

        //this.getInventory()

        //MinecraftClient.getInstance().openScreen(new ExampleScreen(new ExampleGui(this)));

        //setWave(true);
        

        if(player.getMainHandStack().getItem() == Items.STICK || player.getMainHandStack().isEmpty()){
            if(this.Adder >= 1){
                if(player.getMainHandStack().getItem() == Items.STICK && !getOnStatus()){

                    switch (this.TaskNumber) {
                        case 0:
                            setStartMine(false);
                            setStartFollowArrowPath(true);
                            setStartMovetoEntity(false);
                            player.sendMessage(Text.translatable("Follow Arrow Path"), true);
            
                            break;
                        case 1:
                            setStartMine(false);
                            setStartFollowArrowPath(false);
                            setStartMovetoEntity(true);
                            player.sendMessage(Text.translatable("Follow Player"), true);
            
                            break;
                        case 2:
                            setStartMine(true);
                            setStartFollowArrowPath(false);
                            setStartMovetoEntity(false);
                            player.sendMessage(Text.translatable("Mine"), true);
                            
                            break;
                        default:
                        TaskNumber = -1;
                            setStartMine(false);
                            setStartFollowArrowPath(false);
                            setStartMovetoEntity(false);
                            player.sendMessage(Text.translatable("Do Nothing"), true);
            
                            break;
                    }
                    this.TaskNumberSave = this.TaskNumber;
                    this.TaskNumber++;
                    status = ActionResult.SUCCESS;

                } else if(player.getMainHandStack().isEmpty()){
                    if(getOnStatus()){
                        setOnStatus(false);
                    } else {
                        setOnStatus(true);
                    }
                    status = ActionResult.SUCCESS;
                }
                this.Adder = 0;
            }
            this.Adder++;
        } else {
            this.getInventory().addStack(player.getMainHandStack().getItem().getDefaultStack());
            return ActionResult.success(world.isClient);
        }

        return status;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        if (nbt.contains("taskData", 10)) {
            this.taskData = this.compoundTag.getCompound("taskData");

            this.taskData.putBoolean("onstatus", getOnStatus());
            this.taskData.putBoolean("setSFAP", getStartFollowArrowPath());
            this.taskData.putBoolean("setSM", getStartMine());
            this.taskData.putBoolean("setSME", getStartMovetoEntity());
            this.taskData.putBoolean("isMining", isMining());
            this.taskData.putBoolean("isMining", isMiningUp());
            this.taskData.putInt("taskNumber", this.TaskNumberSave);

            nbt.getCompound("taskData").putBoolean("onstatus", getOnStatus());
            nbt.getCompound("taskData").putBoolean("setSFAP", getStartFollowArrowPath());
            nbt.getCompound("taskData").putBoolean("setSM", getStartMine());
            nbt.getCompound("taskData").putBoolean("setSME", getStartMovetoEntity());
            nbt.getCompound("taskData").putBoolean("isMining", isMining());
            nbt.getCompound("taskData").putBoolean("isMiningUp", isMiningUp());
            nbt.getCompound("taskData").putInt("taskNumber", this.TaskNumberSave);

        } else {
            nbt.put("taskData", this.taskData);

            this.taskData.putBoolean("onstatus", getOnStatus());
            this.taskData.putBoolean("setSFAP", getStartFollowArrowPath());
            this.taskData.putBoolean("setSM", getStartMine());
            this.taskData.putBoolean("setSME", getStartMovetoEntity());
            this.taskData.putBoolean("isMining", isMining());
            this.taskData.putBoolean("isMining", isMiningUp());
            this.taskData.putInt("taskNumber", this.TaskNumberSave);

            nbt.getCompound("taskData").putBoolean("onstatus", getOnStatus());
            nbt.getCompound("taskData").putBoolean("setSFAP", getStartFollowArrowPath());
            nbt.getCompound("taskData").putBoolean("setSM", getStartMine());
            nbt.getCompound("taskData").putBoolean("setSME", getStartMovetoEntity());
            nbt.getCompound("taskData").putBoolean("isMining", isMining());
            nbt.getCompound("taskData").putBoolean("isMiningUp", isMiningUp());
            nbt.getCompound("taskData").putInt("taskNumber", this.TaskNumberSave);
        }

        this.writeInventory(nbt);
        //nbt.put("Inventory", this.inventory.toNbtList());
        System.out.println("Write: " + this.taskData);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        this.compoundTag.copyFrom(nbt);

        if (nbt.contains("taskData", 10)) {
            this.taskData = this.compoundTag.getCompound("taskData");
            setOnStatus(this.taskData.getBoolean("onstatus"));
            setStartFollowArrowPath(this.taskData.getBoolean("setSFAP"));
            setStartMine(this.taskData.getBoolean("setSM"));
            setStartMovetoEntity(this.taskData.getBoolean("setSME"));
            setIsMining(this.taskData.getBoolean("isMining"));
            setIsMiningUp(this.taskData.getBoolean("isMiningUp"));
            this.TaskNumber = this.taskData.getInt("taskNumber");
            this.TaskNumberSave = this.TaskNumber;
        }
        this.readInventory(nbt);
        //this.inventory.readNbtList(nbt.getList("Inventory", 10));
        System.out.println("Read: " + this.taskData);
    }

    @Override
    public StackReference getStackReference(int mappedIndex) {
        int i = mappedIndex - 300;
        if (i >= 0 && i < this.inventory.size()) {
            return StackReference.of(this.inventory, i);
        }
        return super.getStackReference(mappedIndex);
    }


    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return PathAwareEntity.createLivingAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, GoalSpeed).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.0);
    }

    /* 
    # Location Numbers for Data Tracker
    # 2 - OnStatus
    # 4 - StartFollowArrowPath
    # 8 - StartMine
    # 16 - StartMovetoEntity
    */


    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.<Byte>startTracking(AgentEntity.CubeEntityFlags, (byte)0);
        //this.dataTracker.<Integer>startTracking(AgentEntity.CubeEntityIntFlags, 0);
    }


    public boolean getOnStatus() {
        return this.getCubeFlag(1);
    }
    
    public void setOnStatus(boolean OnStatus) {
        this.setCubeFlag(1, OnStatus);
    }


    public boolean getStartFollowArrowPath() {
        return this.getCubeFlag(2);
    }

    public void setStartFollowArrowPath(boolean StartFollowArrowPath) {
        this.setCubeFlag(2, StartFollowArrowPath);
    }

    public boolean getStartMine() {
        return this.getCubeFlag(3);
    }

    public void setStartMine(boolean StartMine) {
        this.setCubeFlag(3, StartMine);
    }

    public boolean getStartMovetoEntity() {
        return this.getCubeFlag(4);
    }

    public void setStartMovetoEntity(boolean StartMovetoEntity) {
        this.setCubeFlag(4, StartMovetoEntity);
    }

    public boolean getTargetPlayer() {
        return this.getCubeFlag(5);
    }

    public void setTargetPlayer(boolean setTargetPlayer) {
        this.setCubeFlag(5, setTargetPlayer);
    }

    public boolean isMining() {
        return this.getCubeFlag(6);
    }

    public void setIsMining(boolean isMining) {
        this.setCubeFlag(6, isMining);
    }

    public boolean isMiningUp() {
        return this.getCubeFlag(7);
    }

    public void setIsMiningUp(boolean isMiningUp) {
        this.setCubeFlag(7, isMiningUp);
    }

    public void setIsMining(boolean isMining, boolean up) {

        setIsMining(false);
        setIsMiningUp(false);

        if(!isMining) return;

        if(up) {
            setIsMining(isMining);
        } else {
            setIsMiningUp(isMining);
        }
    }


    private void setCubeFlag(int location, boolean value) {

        location = (int)Math.floor(Math.pow(2, location));

        if (value) {
            this.dataTracker.<Byte>set(AgentEntity.CubeEntityFlags, (byte)(this.dataTracker.<Byte>get(AgentEntity.CubeEntityFlags) | location));
        }
        else {
            this.dataTracker.<Byte>set(AgentEntity.CubeEntityFlags, (byte)(this.dataTracker.<Byte>get(AgentEntity.CubeEntityFlags) & ~location));
        }
    }
    
    private boolean getCubeFlag(int location) {
        location = (int)Math.floor(Math.pow(2, location));

        return (this.dataTracker.<Byte>get(AgentEntity.CubeEntityFlags) & location) != 0x0;
    }

    /* private int getCubeFlag1(int location) {
        return (this.dataTracker.<Integer>get(AgentEntity.CubeEntityFlags) & location);
    } */


    public int getBlockBreakingStatus(){
        return this.BlockBreakProgress;
    }

    public void setBlockBreakProgress(int BlockBreakProgress) {
        this.BlockBreakProgress = BlockBreakProgress;
    }


    public boolean getWave(){
        return this.Wave;
    }

    public void setWave(boolean Wave) {
        this.Wave = Wave;
    }

    public void setTargetEntity(LivingEntity targetEntity) {
        this.setOwner((PlayerEntity)targetEntity);
        this.TargetEntity = targetEntity;
    }

    public LivingEntity getTargetEntity() {
        return this.TargetEntity;
    }

    private void resetTimeOut() {
        this.BlockTimeOut = this.MaxBlockTimeOut;
    }

    private void runTimeOut() {
        this.BlockTimeOut -= 1;

        if(this.BlockTimeOut == 0) {
            setBlockBreakProgress(-1);
        }

        if(!getOnStatus()) {
            setBlockBreakProgress(-1);
        }

        //if(getCurrentLookPos() != BlockPos.ORIGIN) {
        this.world.setBlockBreakingInfo(this.getUuid().hashCode(), getCurrentLookPos(), getBlockBreakingStatus());
        //}
    }

    public BlockPos getCurrentLookPos() {
        return this.CurrentLookPos;
    }

    public void setCurrentLookPos(BlockPos CurrentLookPos) {
        this.CurrentLookPos = CurrentLookPos;
    }


    /* public float getBlockBreakingSpeed(BlockState block) {
        float float3 = this.inventory.getBlockBreakingSpeed(block);

        if (float3 > 1.0f) {
            int integer4 = EnchantmentHelper.getEfficiency(this);
            ItemStack itemStack5 = this.getMainHandStack();
            if (integer4 > 0 && !itemStack5.isEmpty()) {
                float3 += integer4 * integer4 + 1;
            }
        }
        if (StatusEffectUtil.hasHaste(this)) {
            float3 *= 1.0f + (StatusEffectUtil.getHasteAmplifier(this) + 1) * 0.2f;
        }
        if (this.hasStatusEffect(StatusEffects.MINING_FATIGUE)) {
            float float4;
            switch (this.getStatusEffect(StatusEffects.MINING_FATIGUE).getAmplifier()) {
                case 0: {
                    float4 = 0.3f;
                    break;
                }
                case 1: {
                    float4 = 0.09f;
                    break;
                }
                case 2: {
                    float4 = 0.0027f;
                    break;
                }
                default: {
                    float4 = 8.1E-4f;
                    break;
                }
            }
            float3 *= float4;
        }
        if (this.isSubmergedIn(FluidTags.WATER) && !EnchantmentHelper.hasAquaAffinity(this)) {
            float3 /= 5.0f;
        }
        if (!this.onGround) {
            float3 /= 5.0f;
        }
        return float3;
    }


    public boolean isUsingEffectiveTool(BlockState block) {
        return !block.isToolRequired() || this.inventory.getMainHandStack().isEffectiveOn(block);
    } */


    @Override
    protected void loot(ItemEntity item) {
        ItemStack itemStack3 = item.getStack();
        if (this.canGather(itemStack3)) {
            SimpleInventory simpleInventory4 = this.getInventory();
            boolean boolean5 = simpleInventory4.canInsert(itemStack3);
            if (!boolean5) {
                return;
            }
            this.triggerItemPickedUpByEntityCriteria(item);
            this.sendPickup(item, itemStack3.getCount());
            ItemStack itemStack6 = simpleInventory4.addStack(itemStack3);
            if (itemStack6.isEmpty()) {
                item.discard();
            }
            else {
                itemStack3.setCount(itemStack6.getCount());
            }
        }
    }
    
    @Override
    public boolean canGather(ItemStack stack) {
        Item item3 = stack.getItem();
        return this.getInventory().canInsert(stack);
    }

    @Override
    protected void mobTick() {
        super.mobTick();

        runTimeOut();

        //this.pick

        /* for (int i = 0; i < this.getInventory().size(); ++i) {
            int j;
            Integer integer;
            ItemStack itemStack = this.getInventory().getStack(i);
            if (itemStack.isEmpty()) continue;
            for (int k = j = itemStack.getCount(); k > 0; --k) {
                this.getInventory().removeStack(i, 1);
            }
        } */

        /* if(getOnStatus() && getStartMine()){
            if(getBlockBreakingStatus() == 10) {
                setBlockBreakProgress(0);
            }

            setBlockBreakProgress(getBlockBreakingStatus() + 1);
        } */
        
    }


    static {
        CubeEntityFlags = DataTracker.<Byte>registerData(AgentEntity.class, TrackedDataHandlerRegistry.BYTE);
    }


    public class FollowArrowPath extends Goal {
        private final PathAwareEntity mob;
        private final double speed;
        
        public FollowArrowPath(PathAwareEntity mob, double speed) {
            this.mob = mob;
            this.speed = speed;
        }
        
        @Override
        public boolean canStart() {
            return this.mob.world.getBlockState(mob.getBlockPos().down()).isOf(Blocks.MAGENTA_GLAZED_TERRACOTTA) && getOnStatus() && getStartFollowArrowPath();
        }

        @Override
        public boolean shouldContinue() {
            return this.mob.world.getBlockState(mob.getBlockPos().down()).isOf(Blocks.MAGENTA_GLAZED_TERRACOTTA) && getOnStatus() && getStartFollowArrowPath();
        }

        @Override
        public void stop() {
            this.mob.getNavigation().stop();
        }
        
        @Override
        public void tick() {
            double moveX,moveY,moveZ;
            moveX = mob.getX();
            moveY = mob.getY();
            moveZ = mob.getZ();
            //System.out.println(mob.getMovementDirection());


                if (this.mob.world.getBlockState(mob.getBlockPos().down()).isOf(Blocks.MAGENTA_GLAZED_TERRACOTTA)) {
                    if(this.mob.world.getBlockState(mob.getBlockPos().down()).get(GlazedTerracottaBlock.FACING) == Direction.NORTH){
                        moveZ = mob.getBlockPos().getZ()+2;
                    } else if(this.mob.world.getBlockState(mob.getBlockPos().down()).get(GlazedTerracottaBlock.FACING) == Direction.EAST){
                        moveX = mob.getBlockPos().getX()-2;
                    } else if(this.mob.world.getBlockState(mob.getBlockPos().down()).get(GlazedTerracottaBlock.FACING) == Direction.SOUTH){
                        moveZ = mob.getBlockPos().getZ()-2;
                    }  else if(this.mob.world.getBlockState(mob.getBlockPos().down()).get(GlazedTerracottaBlock.FACING) == Direction.WEST){
                        moveX = mob.getBlockPos().getX()+2;
                    }
                } else {
                    moveX = mob.getX();
                    moveY = mob.getY();
                    moveZ = mob.getZ();
                    this.mob.getNavigation().stop();
                }

                this.mob.getLookControl().lookAt(moveX, mob.getEyeY(), moveZ, (float)(this.mob.getMaxLookYawChange() + 20), (float)this.mob.getMaxLookPitchChange());
                this.mob.getNavigation().startMovingTo(moveX, moveY, moveZ, this.speed);
        }
    }
 
    


    public class Mine extends Goal {
        private final AgentEntity mob;
        private final double speed;

        private BlockPos SaveMove;

        private Direction SaveDirection;

        private double messageTicks;

        private boolean ArrowLocked;
        
        public Mine(AgentEntity mob, double speed) {
            this.mob = mob;
            this.speed = speed;

            SaveMove = BlockPos.ORIGIN;

            SaveDirection = null;

            this.messageTicks = 10;

            this.ArrowLocked = false;
        }

        @Override
        public boolean canStart() {
            return getOnStatus() && getStartMine();
        }

        @Override
        public boolean shouldContinue() {
            return getOnStatus() && getStartMine();
        }

        @Override
        public void stop() {
            this.mob.getNavigation().stop();
        }

        private boolean InvalidBlocks(BlockPos pos) {
            return !(
                this.mob.world.getBlockState(pos).isOf(Blocks.BEDROCK) ||
                this.mob.world.getBlockState(pos).isOf(Blocks.COMMAND_BLOCK) ||
                this.mob.world.getBlockState(pos).isOf(Blocks.REPEATING_COMMAND_BLOCK) ||
                this.mob.world.getBlockState(pos).isOf(Blocks.CHAIN_COMMAND_BLOCK) ||
                this.mob.world.getBlockState(pos).isOf(Blocks.STRUCTURE_BLOCK) ||
                this.mob.world.getBlockState(pos).isOf(Blocks.JIGSAW) ||
                this.mob.world.getBlockState(pos).isOf(Blocks.BARRIER)
            );
        }

        private boolean IgnoreBlocks(BlockPos pos) {
            return !(
                this.mob.world.getBlockState(pos).isOf(Blocks.STONE_PRESSURE_PLATE) ||
                this.mob.world.getBlockState(pos).isOf(Blocks.OAK_PRESSURE_PLATE)
            );
        }

        private ItemEntity throwItems(BlockPos toBlock, ItemStack stack) {

            if (stack.isEmpty()) {
                return null;
            }
            if (this.mob.world.isClient) {
                return null;
            }

            ItemEntity itemEntity = new ItemEntity(this.mob.world, this.mob.getX(), this.mob.getY() + (double)0.0f, this.mob.getZ(), stack); //this.mob.inventory.getStack(0)

            double xVel = (toBlock.toCenterPos().getX() - this.mob.getBlockPos().toCenterPos().getX()) / 5;
            double yVel = (toBlock.toCenterPos().getY() - this.mob.getBlockPos().toCenterPos().getY()) / 5;
            double zVel = (toBlock.toCenterPos().getZ() - this.mob.getBlockPos().toCenterPos().getZ()) / 5;

            itemEntity.addVelocity(xVel, yVel, zVel);
            itemEntity.setPickupDelay(20);
            this.mob.world.spawnEntity(itemEntity);
            return itemEntity;
        }

        private BlockPos findBlock(BlockPos StartPos, Block block, int distance) {
            return findBlock(StartPos, block, distance, 0);
        }

        private BlockPos findBlock(BlockPos StartPos, Block block, int distance, int yDistance) {

            BlockPos.Mutable startPosMutable = StartPos.mutableCopy();

            int yDistanceUp = yDistance;
            int yDistanceDown = -yDistance;

            if(yDistance < 0) {
                yDistanceUp = 0;
            } else if(yDistance == 0) {
                yDistanceDown = -1;
            }

            for (int y = yDistanceDown; y < yDistanceUp; y++) {
                for (int x = -distance; x < distance; x++) {
                    for (int z = -distance; z < distance; z++) {

                        BlockPos DifPos = startPosMutable.add(x, y, z);

                        if(this.mob.world.getBlockState(DifPos).isOf(block)) {
                            return DifPos;
                        }
                    }
                }
            }

            return BlockPos.ORIGIN;
        }

        @Override
        public void tick() {

            boolean localIsMining = false;
            boolean isUp = false;

            this.mob.setTarget(getTargetEntity());

                double moveX,moveY,moveZ;
                BlockPos pos = this.mob.getBlockPos();
                BlockPos pos2 = pos;
                BlockPos posDown = pos;

                double xHalf, zHalf;

                xHalf = 0;
                zHalf = 0;

                moveX = mob.getX();
                moveY = mob.getY();
                moveZ = mob.getZ();

                //BlockPos CurrentLookPos = BlockPos.ORIGIN;

                /* if(!this.ArrowLocked) {
                    this.mob.getLookControl().lookAt(moveX, mob.getEyeY(), moveZ, (float)(this.mob.getMaxLookYawChange() + 20), (float)this.mob.getMaxLookPitchChange());
                    this.mob.getMoveControl().moveTo((int)moveX, this.mob.getY(), (int)moveZ, this.speed);
                } */


                if(!this.mob.getInventory().getStack(0).isEmpty() && (this.mob.getInventory().getStack(0).getCount() == this.mob.getInventory().getStack(0).getMaxCount())) {

                    if(this.SaveMove == BlockPos.ORIGIN) {

                        BlockPos.Mutable TempSaveMove = this.mob.getBlockPos().mutableCopy();

                        if(this.SaveDirection == Direction.SOUTH){
                            //moveZ = mob.getBlockPos().getZ()+1;
                            this.SaveMove = TempSaveMove.add(0, 0, 1);
                        } else if(this.SaveDirection == Direction.WEST){
                            //moveX = mob.getBlockPos().getX()-1;
                            this.SaveMove = TempSaveMove.add(-1, 0, 0);
                        } else if(this.SaveDirection == Direction.NORTH){
                            //moveZ = mob.getBlockPos().getZ()-1;
                            this.SaveMove = TempSaveMove.add(0, 0, -1);
                        }  else if(this.SaveDirection == Direction.EAST){
                            //moveX = mob.getBlockPos().getX()+1;
                            this.SaveMove = TempSaveMove.add(1, 0, 0);
                        }
                    }

                    /* Block.dropStack(this.mob.world, this.mob.getBlockPos(), this.mob.getInventory().getStack(0));
                    this.mob.getInventory().removeStack(0); */

                    BlockPos FindPos = findBlock(this.mob.getBlockPos(), Blocks.HOPPER, 16, 4);

                    if(FindPos == BlockPos.ORIGIN) {
                        this.mob.getNavigation().stop();
                    } else {

                        this.mob.getNavigation().startMovingTo(FindPos.toCenterPos().getX(), this.mob.getY(), FindPos.toCenterPos().getZ(), this.speed);

                        //this.mob.getMoveControl().moveTo(FindPos.toCenterPos().getX(), this.mob.getY(), FindPos.toCenterPos().getZ(), this.speed);

                        if(this.mob.getBlockPos().isWithinDistance(FindPos.toCenterPos(), 5) || this.mob.getNavigation().getCurrentPath() == null) {
                            this.mob.getLookControl().lookAt(FindPos.toCenterPos().getX(), FindPos.getY(), FindPos.toCenterPos().getZ(), (float)(this.mob.getMaxLookYawChange() + 20), (float)this.mob.getMaxLookPitchChange());
                        } else {
                            this.mob.getLookControl().lookAt(
                                this.mob.getNavigation().getCurrentPath().getNodePos(this.mob.getNavigation().getCurrentPath().getCurrentNodeIndex()).getX(),
                                this.mob.getEyeY(),
                                this.mob.getNavigation().getCurrentPath().getNodePos(this.mob.getNavigation().getCurrentPath().getCurrentNodeIndex()).getZ(),
                                (float)(this.mob.getMaxLookYawChange() + 20),
                                (float)this.mob.getMaxLookPitchChange()
                            );
                        }

                        if(this.mob.getBlockPos().isWithinDistance(FindPos.toCenterPos(), 2)) {

                            /* this.mob.dropStack(this.mob.inventory.getStack(0)); */

                            throwItems(FindPos.up(), this.mob.inventory.getStack(0));

                            this.mob.inventory.removeStack(0);
                            this.mob.getNavigation().stop();
                        }
                    }

                    setIsMining(localIsMining, isUp);
                   return;
                }

                if(this.SaveMove != BlockPos.ORIGIN) {

                    if(this.mob.getBlockPos().isWithinDistance(SaveMove.toCenterPos(), 2)) {
                        this.mob.getLookControl().lookAt(getCurrentLookPos().getX(), getCurrentLookPos().getY(), getCurrentLookPos().getZ());
                    }

                    /* this.mob.getMoveControl().moveTo(SaveMove.toCenterPos().getX(), SaveMove.toCenterPos().getY(), SaveMove.toCenterPos().getZ(), this.speed); */

                    /* this.mob.getBlockPos().isWithinDistance(SaveMove.toCenterPos(), 1) */
                    if(/*this.mob.getNavigation().isIdle() &&*/  this.mob.getBlockPos().isWithinDistance(SaveMove.toCenterPos(), 2) ) {
                        this.mob.getMoveControl().moveTo(SaveMove.toCenterPos().getX(), SaveMove.toCenterPos().getY(), SaveMove.toCenterPos().getZ(), this.speed);

                        if(this.mob.getBlockPos().isWithinDistance(SaveMove.toCenterPos(), 0.1)) {
                            this.SaveMove = BlockPos.ORIGIN;
                        }
                    } else {
                        this.mob.getNavigation().startMovingTo(SaveMove.toCenterPos().getX(), SaveMove.toCenterPos().getY(), SaveMove.toCenterPos().getZ(), this.speed);
                    }

                    setIsMining(localIsMining, isUp);
                    return;
                }


                if(this.mob.world.getBlockState(mob.getBlockPos().down()).isOf(Blocks.RED_CONCRETE)){

                    this.mob.getNavigation().stop();

                    setOnStatus(false);

                } else if(this.mob.world.getBlockState(mob.getBlockPos().down()).isOf(Blocks.MAGENTA_GLAZED_TERRACOTTA)){

                        if(this.mob.world.getBlockState(mob.getBlockPos().down()).get(GlazedTerracottaBlock.FACING) == Direction.NORTH){
                            moveZ = mob.getBlockPos().getZ()+2;
                            xHalf = -0.5;
                            zHalf = 0;
                            SaveDirection = Direction.NORTH;
                        } else if(this.mob.world.getBlockState(mob.getBlockPos().down()).get(GlazedTerracottaBlock.FACING) == Direction.EAST){
                            moveX = mob.getBlockPos().getX()-2;
                            xHalf = 0;
                            zHalf = 0.5;
                            SaveDirection = Direction.EAST;
                        } else if(this.mob.world.getBlockState(mob.getBlockPos().down()).get(GlazedTerracottaBlock.FACING) == Direction.SOUTH){
                            moveZ = mob.getBlockPos().getZ()-2;
                            xHalf = -0.5;
                            zHalf = 0;
                            SaveDirection = Direction.SOUTH;
                        }  else if(this.mob.world.getBlockState(mob.getBlockPos().down()).get(GlazedTerracottaBlock.FACING) == Direction.WEST){
                            moveX = mob.getBlockPos().getX()+2;
                            xHalf = 0;
                            zHalf = 0.5;
                            SaveDirection = Direction.WEST;
                        }

                    this.mob.getLookControl().lookAt(moveX, mob.getEyeY(), moveZ, (float)(this.mob.getMaxLookYawChange() + 20), (float)this.mob.getMaxLookPitchChange());
                    //this.mob.getNavigation().startMovingTo(moveX, moveY, moveZ, this.speed);
                    this.mob.getMoveControl().moveTo((int)moveX + xHalf, this.mob.getY(), (int)moveZ + zHalf, this.speed);

                    } else if(this.mob.getHorizontalFacing().getOpposite() != SaveDirection){

                        if(SaveDirection == Direction.NORTH){
                            moveZ = mob.getBlockPos().getZ()+1;
                            xHalf = -0.5;
                            zHalf = 0;
                            SaveDirection = Direction.NORTH;
                        } else if(SaveDirection == Direction.EAST){
                            moveX = mob.getBlockPos().getX()-1;
                            xHalf = 0;
                            zHalf = 0.5;
                            SaveDirection = Direction.EAST;
                        } else if(SaveDirection == Direction.SOUTH){
                            moveZ = mob.getBlockPos().getZ()-1;
                            xHalf = -0.5;
                            zHalf = 0;
                            SaveDirection = Direction.SOUTH;
                        }  else if(SaveDirection == Direction.WEST){
                            moveX = mob.getBlockPos().getX()+1;
                            xHalf = 0;
                            zHalf = 0.5;
                            SaveDirection = Direction.WEST;
                        }

                    this.mob.getLookControl().lookAt(moveX, mob.getEyeY(), moveZ, (float)(this.mob.getMaxLookYawChange() + 20), (float)this.mob.getMaxLookPitchChange());
                    //this.mob.getNavigation().startMovingTo(moveX, moveY, moveZ, this.speed);
                    this.mob.getMoveControl().moveTo((int)moveX + xHalf, this.mob.getY(), (int)moveZ + zHalf, this.speed);
                } else {

                    if (this.mob.getHorizontalFacing() == Direction.NORTH){
                        pos = this.mob.getBlockPos().north();
                        pos2 = this.mob.getBlockPos().north(1).up();
                        posDown = this.mob.getBlockPos().north(1).down();
                        if(!ArrowLocked) {
                            moveZ = pos.getZ() - 1;
                        } else {

                        }

                        xHalf = -0.5;
                        zHalf = 0;
                    } else if(this.mob.getHorizontalFacing() == Direction.EAST){
                        pos = this.mob.getBlockPos().east();
                        pos2 = this.mob.getBlockPos().east(1).up();
                        posDown = this.mob.getBlockPos().east(1).down();
                        if(!ArrowLocked) {
                            moveX = pos.getX() + 1;
                        } else {

                        }

                        xHalf = 0;
                        zHalf = 0.5;
                    }  else if(this.mob.getHorizontalFacing() == Direction.SOUTH){
                        pos = this.mob.getBlockPos().south();
                        pos2 = this.mob.getBlockPos().south(1).up();
                        posDown = this.mob.getBlockPos().south(1).down();
                        if(!ArrowLocked) {
                            moveZ = pos.getZ() + 1;
                        } else {

                        }

                        xHalf = -0.5;
                        zHalf = 0;
                    }  else if(this.mob.getHorizontalFacing() == Direction.WEST){
                        pos = this.mob.getBlockPos().west();
                        pos2 = this.mob.getBlockPos().west(1).up();
                        posDown = this.mob.getBlockPos().west(1).down();
                        moveX = pos.getX() - 1;
                        xHalf = 0;
                        zHalf = 0.5;
                    }

                    boolean wait = false;

                    if(!this.mob.world.getBlockState(this.mob.getBlockPos().up()).isAir() && InvalidBlocks(this.mob.getBlockPos().up())){
                        setCurrentLookPos(this.mob.getBlockPos().up());
                        wait = true;
                        isUp = true;
                    } else if(!this.mob.world.getBlockState(pos2).isAir() && InvalidBlocks(pos2) && InvalidBlocks(this.mob.getBlockPos().up())){
                        setCurrentLookPos(pos2);
                        wait = true;
                        isUp = false;
                    } else if(InvalidBlocks(pos)){
                        setCurrentLookPos(pos);
                        wait = false;
                    }

                    if(this.mob.world.getBlockState(posDown).isAir() || this.mob.world.getBlockState(posDown).isOf(Blocks.LAVA) || this.mob.world.getBlockState(posDown).isOf(Blocks.WATER)) {
                        wait = true;
                    }


                    /* if(this.mob.getOwner() != null) {
                        //this.mob.world.isPlayerInRange(this.mob.getX(), this.mob.getY(), this.mob.getZ(), 1)
                        //this.mob.isInRange(this.mob.getOwner(), 1, 1)

                        if(this.mob.world.isPlayerInRange(this.mob.getX(), this.mob.getY(), this.mob.getZ(), 1)) {
                            wait = true;
                        }
                    } */

                    //this.mob.world.getClosestPlayer(this.mob, 1);

                    if(this.mob.world.isPlayerInRange(this.mob.getX(), this.mob.getY(), this.mob.getZ(), 1)/*  || this.mob.world */) {
                        wait = true;
                    }

                    double CurrentEye;
                    if(getCurrentLookPos() == BlockPos.ORIGIN){
                        CurrentEye = this.mob.getEyeY();
                    } else {
                        CurrentEye = getCurrentLookPos().up().getY();

                        this.mob.world.getBlockState(getCurrentLookPos()).getHardness(this.mob.world, getCurrentLookPos());

                        if(this.mob.world.getClosestPlayer(this.mob, 2) != null && this.messageTicks == 0) {
                            this.mob.world.getClosestPlayer(this.mob, 2).sendMessage(Text.translatable("X:" + getCurrentLookPos().getX() + " Y:" + getCurrentLookPos().getY() + " Z:" + getCurrentLookPos().getZ() + " Invalid Blocks?: " + InvalidBlocks(getCurrentLookPos())), true);
                            this.messageTicks = 10;
                        } else if(this.mob.world.getClosestPlayer(this.mob, 2) != null) {
                            this.messageTicks -= 1;
                        }

                        /* if(this.messageTicks == 0) {
                            this.messageTicks = 20;
                        } */


                        if(InvalidBlocks(getCurrentLookPos()) && IgnoreBlocks(getCurrentLookPos())) {
                            if(getBlockBreakingStatus() == 9){
                                setBlockBreakProgress(-1);
                                this.mob.world.breakBlock(getCurrentLookPos(), true, this.mob);
                                localIsMining = false;
                            } else {
                                resetTimeOut();
                                setBlockBreakProgress(getBlockBreakingStatus() + 1);
                                localIsMining = true;
                            }
                        }
                    }

                    this.mob.getLookControl().lookAt(moveX, CurrentEye, moveZ, (float)(this.mob.getMaxLookYawChange() + 20), (float)this.mob.getMaxLookPitchChange());

                    if(!wait){
                        this.mob.getMoveControl().moveTo((int)moveX + xHalf, this.mob.getY(), (int)moveZ + zHalf, this.speed);
                    } else {
                        //localIsMining = false;
                    }


                    //this.mob.getJumpControl().setActive();
                    //this.mob.getNavigation().startMovingTo(moveX, this.mob.getY(), moveZ, this.speed);


                   // this.mob.getNavigation().findPathTo(pos, 1);
            }

            setIsMining(localIsMining, isUp);
        }
    }


























    /* public class MoveOutOfWaterGoal extends Goal {
        private final PathAwareEntity mob;
        
        public MoveOutOfWaterGoal(PathAwareEntity mob) {
            this.mob = mob;
        }
        
        @Override
        public boolean canStart() {
            return true;
        }
        
        @Override
        public void tick() {
            BlockPos blockPos2 = null;
            Iterable<BlockPos> iterable3 = BlockPos.iterate(MathHelper.floor(this.mob.getX() - 2.0), MathHelper.floor(this.mob.getY() - 2.0), MathHelper.floor(this.mob.getZ() - 2.0), MathHelper.floor(this.mob.getX() + 2.0), MathHelper.floor(this.mob.getY()), MathHelper.floor(this.mob.getZ() + 2.0));

            for (final BlockPos lv2 : iterable3) {
                System.out.println(this.mob.world.getBlockState(lv2));
                
                if (this.mob.world.getBlockState(lv2).isOf(Blocks.DIAMOND_BLOCK)) {
                    blockPos2 = lv2;
                    break;
                }
            }
            if (blockPos2 != null) {
                System.out.println("MOOW Goal: Move to Hit");
                //this.mob.getMoveControl().moveTo(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ(), 1.0);
                this.mob.getNavigation().startMovingTo(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ(), 0.6);
            }

            System.out.println("MOOW Goal: Run");
        }
    } */
}
