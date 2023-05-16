package com.tylervp.util;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class ConfigFileReader {
    private boolean vslabsEnabled, hoppertopcollision;
    private int hopperSpeed;
    private Path CONFIGFILE;

    public ConfigFileReader() {
        this.vslabsEnabled = true;
        this.hopperSpeed = 8;
        this.hoppertopcollision = true;
        this.CONFIGFILE = Paths.get(System.getProperty("user.dir") + "/mods/mbmconfig.json");
        this.ReadFromFile();
    }

    public boolean isHopperTopCollisionDisabled() {
        return this.hoppertopcollision;
    }

    public boolean isSlabsEnabled() {
        return this.vslabsEnabled;
    }

    public int getHopperSpeed() {
        return this.hopperSpeed;
    }

    public void setHopperTopCollision(boolean hoppertopcollision) {
        this.hoppertopcollision = hoppertopcollision;

        WriteFromFile();
    }

    public void setHopperSpeed(int hopperSpeed) {
        if(hopperSpeed < 0) {
            hopperSpeed = 0;
        }

        this.hopperSpeed = hopperSpeed;

        WriteFromFile();
    }

    public void setVerticalSlabsEnabled(boolean vslabsEnabled) {
        this.vslabsEnabled = vslabsEnabled;

        WriteFromFile();
    }

    private void CreateJsonFile() {
        try {
            File file = new File(this.CONFIGFILE.toString());
            if(file.createNewFile()) {
                System.out.println("[More Blocks Mod] MBM Config File Created");
                WriteFromFile();
            } else {
                System.out.println("[More Blocks Mod] File Exists. Error Adding Data.");
            }
        } catch (Exception ex) {
            System.out.println("[More Blocks Mod] Error Making File");
        }
    }

    private void WriteFromFile() {
        try {
            Gson gson = new Gson();
            Config user = new Config(this.vslabsEnabled, this.hopperSpeed, this.hoppertopcollision);

            Writer writer = Files.newBufferedWriter(this.CONFIGFILE);

            gson.toJson(user, writer);

            System.out.println("[More Blocks Mod] Vertical Slabs Enabled: " + this.vslabsEnabled);
            System.out.println("[More Blocks Mod] Hopper Top Collision Disabled: " + this.hoppertopcollision);
            System.out.println("[More Blocks Mod] Hopper Speed Set At: " + this.hopperSpeed);

            writer.close();

        } catch (Exception ex) {
            System.out.println("[More Blocks Mod] MBM File Not Found");
            CreateJsonFile();
        }
    }

    public void ReadFromFile() {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(this.CONFIGFILE);

            // convert a JSON string to a User object
            Config user = gson.fromJson(reader,Config.class);

            System.out.println("[More Blocks Mod] MBM Config File Loaded");

            this.vslabsEnabled = user.vslabsEnabled;
            this.hopperSpeed = user.hopperSpeed;
            this.hoppertopcollision = user.hoppertopcollision;

            // close reader
            reader.close();

            System.out.println("[More Blocks Mod] Vertical Slabs Enabled: " + this.vslabsEnabled);
            System.out.println("[More Blocks Mod] Hopper Top Collision Disabled: " + this.hoppertopcollision);

            if(this.hopperSpeed < 0) {
                setHopperSpeed(this.hopperSpeed);
                System.out.println("[More Blocks Mod] Hopper Speed Can't Be Lower Than 0.");
            }

            System.out.println("[More Blocks Mod] Hopper Speed Set At: " + this.hopperSpeed);


        } catch (Exception ex) {
            System.out.println("[More Blocks Mod] No MBM Config File Found. Nothing Changed");
        }
    }
}
