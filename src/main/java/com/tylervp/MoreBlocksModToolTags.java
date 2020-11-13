package com.tylervp;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class MoreBlocksModToolTags {
	public static final Tag<Item> HAMMERS = register("netherite_hammer");

	private MoreBlocksModToolTags() { }

	private static Tag<Item> register(String id) {
		return TagRegistry.item(new Identifier("moreblocksmod", id));
	}
}