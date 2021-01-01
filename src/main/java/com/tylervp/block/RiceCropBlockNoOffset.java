package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

public class RiceCropBlockNoOffset extends RiceCropBlock {
    public static final IntProperty AGE;
    public static final BooleanProperty FLIP;
    
    protected RiceCropBlockNoOffset(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(((BlockState)this.stateManager.getDefaultState()).with(this.getAgeProperty(), 0));
    }

    @Override
    public AbstractBlock.OffsetType getOffsetType() {
        return AbstractBlock.OffsetType.NONE;
    }

    static {
        AGE = Properties.AGE_7;
        FLIP = RiceCropBlock.FLIP;
    }
}
