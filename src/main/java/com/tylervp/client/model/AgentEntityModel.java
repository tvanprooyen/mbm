package com.tylervp.client.model;

import com.tylervp.entity.AgentEntity;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;

public class AgentEntityModel extends EntityModel<AgentEntity> {
	private final ModelPart head;
	private final ModelPart root;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private float handSwingProgress;

	private boolean runOnce;

	private float RightArmPivX, RightArmPivZ, LeftArmPivX, LeftArmPivZ;

	public AgentEntityModel(ModelPart root) {

        this.head = root.getChild("head");
		this.root = root.getChild("root");
		this.rightArm = root.getChild("rightArm");
		this.leftArm = root.getChild("leftArm");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
        
        this.handSwingProgress = 0;

		this.runOnce = true;
	}

    public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -7.0F, -2.5F, 6.0F, 7.0F, 5.0F, new Dilation(0.0F))
		.uv(24, 23).cuboid(-1.0F, -3.0F, -3.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 15.0F, 1.0F));

		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create().uv(0, 14).cuboid(-4.0F, -3.5F, -2.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(2, 24).cuboid(-3.0F, 0.5F, -2.0F, 6.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 18.5F, 1.0F));

		ModelPartData rightArm = modelPartData.addChild("rightArm", ModelPartBuilder.create().uv(23, 0).cuboid(-2.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 16.0F, 1.0F));

		ModelPartData leftArm = modelPartData.addChild("leftArm", ModelPartBuilder.create().uv(23, 0).mirrored().cuboid(0.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, 16.0F, 1.0F));

		ModelPartData rightLeg = modelPartData.addChild("rightLeg", ModelPartBuilder.create().uv(23, 12).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 21.0F, 1.0F));

		ModelPartData leftLeg = modelPartData.addChild("leftLeg", ModelPartBuilder.create().uv(23, 12).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.0F, 21.0F, 1.0F));

		return TexturedModelData.of(modelData, 32, 32);
	}

	public void savePivots() {
		if(this.runOnce) {
			this.RightArmPivX = this.rightArm.pivotX;
			this.RightArmPivZ = this.rightArm.pivotZ;
			this.LeftArmPivX = this.leftArm.pivotX;
			this.LeftArmPivZ = this.leftArm.pivotZ;
			this.runOnce = false;

			System.out.println("[ More Blocks Mod ] Right Arm Pivot X" + this.rightArm.pivotX);
			System.out.println("[ More Blocks Mod ] Right Arm Pivot Z" + this.rightArm.pivotZ);
			System.out.println("[ More Blocks Mod ] Left Arm Pivot X" + this.leftArm.pivotX);
			System.out.println("[ More Blocks Mod ] Left Arm Pivot Z" + this.leftArm.pivotZ);
		}
	}

    int number = 0;
	int prevNumber = 0;
	int LowNumber = 0;
	int prevLowNumber = 0;
	@Override
	public void setAngles(AgentEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		savePivots();

		if(entity.getOnStatus()){

			/* IronGolemEntityModel
			this.head.yaw = netHeadYaw * 0.017453292f;
			this.head.pitch = headPitch * 0.017453292f;
			this.rightArm.pitch = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f / float10;
			this.leftArm.pitch = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f / float10;

			this.rightArm.pitch = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount / float10;
			this.leftArm.pitch = MathHelper.sin(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount / float10;
			this.rightArm.roll = 0.0f;
			this.leftArm.roll = 0.0f; */

			this.head.yaw = netHeadYaw * ((float)Math.PI / 180);
			this.head.pitch = headPitch * ((float)Math.PI / 180);
			this.rightLeg.pitch = -1.5f * MathHelper.wrap(limbSwing, 5.0f) * limbSwingAmount;
			this.leftLeg.pitch = 1.5f * MathHelper.wrap(limbSwing, 5.0f) * limbSwingAmount;
			this.rightLeg.yaw = 0.0f;
			this.leftLeg.yaw = 0.0f;

			if(entity.isMining() || entity.isMiningUp()) {
				/*
				Posistion					Time   	          Speed   Swing Area
				-2.35f + (MathHelper.cos(animationProgress * 1.0662f) * 0.45f)
				 */

				float RightArmPitch = -MathHelper.abs((entity.isMiningUp() ? -2 : 0 + -1.35f) + (MathHelper.cos(ageInTicks * 1f) * 0.45f));
				this.rightArm.pitch = RightArmPitch;
				float RightArmRoll = -MathHelper.abs(-0.5f + (MathHelper.cos(ageInTicks * 1f) * 0.8f));
				this.rightArm.roll = RightArmRoll;

				this.root.yaw = RightArmRoll/4;

				this.rightArm.pivotZ = MathHelper.sin(this.root.yaw);
        		this.rightArm.pivotX = -MathHelper.cos(this.root.yaw) * 4.0f;
				this.leftArm.pivotZ = -MathHelper.sin(this.root.yaw) * 5.0f;
        		this.leftArm.pivotX = MathHelper.cos(this.root.yaw) * 4.0f;

				this.rightArm.yaw = this.root.yaw;
				this.leftArm.yaw = this.root.yaw;

				//this.leftArm.pitch = 1.0f;

				//this.leftArm.roll = -this.rightArm.roll;
				
			} else {
				this.leftArm.pitch = -1f * MathHelper.wrap(limbSwing, 5.0f) * limbSwingAmount;
				this.rightArm.pitch = 1f * MathHelper.wrap(limbSwing, 5.0f) * limbSwingAmount;
				this.leftArm.yaw = 0.0f;
				this.rightArm.yaw = 0.0f;
				this.root.yaw = 0.0f;
			}

			

			

		} else {
			if(entity.getWave()){

				/*
				Posistion					Time   	          Speed   Swing Area
				-2.35f + (MathHelper.cos(animationProgress * 1.0662f) * 0.45f) */

				/* Wave
				this.RightArm.pitch = -2.95f;
				this.RightArm.roll = MathHelper.cos(animationProgress * 0.9662f) * 0.10f;
				this.RightArm.yaw = 0.0f;
				this.LeftArm.yaw = 0.0f; */

				/* Yes
				this.Head.pitch = MathHelper.cos(animationProgress * 0.9662f) * 0.10f; */

				/* No
				this.Head.yaw = MathHelper.cos(animationProgress * 0.5662f) * 0.25f; */

				/* Mine */
				float RightArmPitch = -MathHelper.abs(-1.35f + (MathHelper.cos(ageInTicks * 0.1662f) * 0.45f));
				this.rightArm.pitch = RightArmPitch;
				this.rightArm.roll = 0.0f;

				float RightArmYaw = -0.25f;
                
				int AnimationProgressRepeat = (((int)(ageInTicks * 0.01) * 100));


				if(RightArmPitch <= -1.79){
					//number++;
				} 
				if(RightArmPitch >= -0.89){
					number--;
				}
				if(RightArmPitch >= -0.8){
					RightArmYaw = -0.95f + MathHelper.abs(MathHelper.cos(ageInTicks * 0.1662f) * 0.7f);
				}
				this.rightArm.yaw = RightArmYaw;


			} else {
				this.rightArm.pitch = 0.0f;
				this.leftArm.pitch = 0.0f;
				this.rightLeg.pitch = 0.0f;
				this.leftLeg.pitch = 0.0f;
				this.rightArm.roll = 0.0f;
				this.leftArm.roll = 0.0f;
				this.head.yaw = 0.0f;
				this.head.pitch = 0.0f;
				this.rightArm.yaw = 0.0f;
				this.root.yaw = 0.0f;
				this.leftArm.yaw = 0.0f;

				this.rightArm.pivotZ = this.RightArmPivZ;
        		this.rightArm.pivotX = this.RightArmPivX;
				this.leftArm.pivotZ = this.LeftArmPivZ;
        		this.leftArm.pivotX = this.LeftArmPivX;
			}
		}

        this.rightLeg.yaw = 0.0f;
        this.leftLeg.yaw = 0.0f;
        this.rightLeg.roll = 0.0f;
		this.leftLeg.roll = 0.0f;
    }

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void animateModel(AgentEntity entity, float limbAngle, float limbDistance, float tickDelta) {
		super.animateModel(entity, limbAngle, limbDistance, tickDelta);
		this.handSwingProgress = tickDelta;
	}

	protected ModelPart getArm(Arm arm) {
        if (arm == Arm.LEFT) {
            return this.leftArm;
        }
        return this.rightArm;
    }

	protected void animateArms(AgentEntity entity, float animationProgress) {
		/*
		Posistion					Time   	          Speed   Swing Area
		-2.35f + (MathHelper.cos(animationProgress * 1.0662f) * 0.45f) */ 
        if (this.handSwingProgress <= 0.0f) {
            return;
        }
        float f = this.handSwingProgress * 0.001f;
        //this.root.yaw = MathHelper.sin(MathHelper.sqrt(f) * ((float)Math.PI * 2)) * 0.2f;

        /* this.rightArm.pivotZ = MathHelper.sin(this.root.yaw) * 5.0f;
        this.rightArm.pivotX = -MathHelper.cos(this.root.yaw) * 5.0f;
        this.leftArm.pivotZ = -MathHelper.sin(this.root.yaw) * 5.0f;
        this.leftArm.pivotX = MathHelper.cos(this.root.yaw) * 5.0f;
        this.rightArm.yaw += this.root.yaw;
        this.leftArm.yaw += this.root.yaw;
        this.leftArm.pitch += this.root.yaw; */
        f = 1.0f - this.handSwingProgress;
        f *= f;
        f *= f;
        f = 1.0f - f;
        float g = MathHelper.sin(f * (float)Math.PI);
        float h = MathHelper.sin(this.handSwingProgress * (float)Math.PI) * -(this.head.pitch - 0.1f) * 0.1f;
        //rightArm.pitch -= g * 0.001f + h;
        //rightArm.yaw += this.root.yaw * 2.0f;
		float ArmRoll = MathHelper.abs(MathHelper.sin(this.handSwingProgress * (float)Math.PI) * -0.01f);

		System.out.println((this.handSwingProgress * (float)Math.PI));

		if((this.handSwingProgress * (float)Math.PI) < 180) {
			rightArm.roll += ArmRoll;
		} else {
			rightArm.roll -= ArmRoll;
		}

        //rightArm.roll += MathHelper.sin(this.handSwingProgress * (float)Math.PI) * -0.01f;
    }
}