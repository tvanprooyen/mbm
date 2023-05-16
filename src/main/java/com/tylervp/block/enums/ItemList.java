package com.tylervp.block.enums;

import com.tylervp.item.MBMItems;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.util.StringIdentifiable;

public enum ItemList implements StringIdentifiable
{
    NONE(Items.APPLE),
    RAW_COPPER_BLOCK(Items.RAW_COPPER_BLOCK),
    RAW_IRON_BLOCK(Items.RAW_IRON_BLOCK),
    RAW_GOLD_BLOCK(Items.RAW_GOLD_BLOCK),
    REDSTONE_BLOCK(Items.REDSTONE_BLOCK),
    IRON_BLOCK(Items.IRON_BLOCK),
    GOLD_BLOCK(Items.GOLD_BLOCK),
    COPPER_BLOCK(Items.COPPER_BLOCK),
    EMERALD_BLOCK(Items.EMERALD_BLOCK),
    DIAMOND_BLOCK(Items.DIAMOND_BLOCK),
    NETHERITE_BLOCK(Items.NETHERITE_BLOCK),
    COAL_BLOCK(Items.COAL_BLOCK),
    QUARTZ_BLOCK(Items.QUARTZ_BLOCK),
    IRON_INGOT(Items.IRON_INGOT),
    GOLD_INGOT(Items.GOLD_INGOT),
    COPPER_INGOT(Items.COPPER_INGOT),
    LEATHER_BLOCK(MBMItems.LEATHER_BLOCK),
    SLIME_BLOCK(Items.SLIME_BLOCK),
    WHITE_WOOL(Items.WHITE_WOOL),
    BONE_BLOCK(Items.BONE_BLOCK),
    STICK(Items.STICK),
    STICK_BUNDLE(MBMItems.STICK_BUNDLE)/* ,
    STICK_BUNDLE(MBMItems.STICK_BUNDLE) */;

    private final String name;
    private final ItemConvertible item;

    private ItemList(ItemConvertible item) {
        this.name = item.toString();
        this.item = item;
    }

    public String toString() {
		return this.name;
	}

    public ItemConvertible toItem() {
        return this.item;
    }

    public int toID() {
        return this.ordinal();
    }

    @Override
    public String asString() {
        return this.name;
    }
}