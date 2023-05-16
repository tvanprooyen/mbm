package com.tylervp.util;

public class Config {

    public boolean vslabsEnabled;
    public boolean hoppertopcollision;
    public int hopperSpeed;

    public Config() {
    }

    public Config(boolean vslabsEnabled, int hopperSpeed, boolean hoppertopcollision) {
        this.vslabsEnabled = vslabsEnabled;
        this.hopperSpeed = hopperSpeed;
        this.hoppertopcollision = hoppertopcollision;
    }
}