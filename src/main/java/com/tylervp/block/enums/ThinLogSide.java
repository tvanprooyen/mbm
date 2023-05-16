package com.tylervp.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum ThinLogSide implements StringIdentifiable
{
    NONE("none"),
    LOG("log"),
    LEAVES("leaves"),
    LOGLEAVES("logleaves"),
    CHAIN("chain"),
    ROPE("rope");

    private final String name;

    private ThinLogSide(String name) {
        this.name = name;
    }

    public String toString() {
		return this.name;
	}

    @Override
    public String asString() {
        return this.name;
    }
}