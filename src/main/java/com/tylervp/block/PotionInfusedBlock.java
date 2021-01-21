package com.tylervp.block;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PotionInfusedBlock extends Block {

	public PotionInfusedBlock(Settings settings) {
		super(settings);
		setDefaultState(this.stateManager.getDefaultState());
	}

    @Override
    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        
/*         Box box6 = new Box(this.pos).expand(double2).stretch(0.0, this.world.getHeight(), 0.0);
        List<PlayerEntity> list7 = world.getPl;
        for (final PlayerEntity lv2 : list7) {
            lv2.addStatusEffect(new StatusEffectInstance(StatusEffect.byRawId(0), 1, 1, true, true));
        }
 */

        world.getClosestPlayer((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), 10.0, true).addStatusEffect(new StatusEffectInstance(StatusEffect.byRawId(0), 1, 1, true, true));


        super.onSteppedOn(world, pos, entity);
    }
	
}
