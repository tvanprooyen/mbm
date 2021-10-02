package com.tylervp.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum WireSides implements StringIdentifiable {
	TOP("top"),
	MIDDLE("middle"),
    BOTTOM("bottom");

	private final String name;

	private WireSides(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public String asString() {
		return this.name;
	}
}
