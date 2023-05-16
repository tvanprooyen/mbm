package com.tylervp.command;

import static net.minecraft.server.command.CommandManager.*;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.tylervp.block.MBMBlocks;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

public class MBMCommands {

    public static void register() {

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("mbmgamerules")
        .then(literal("hopperspeed").executes(ctx -> { ctx.getSource().sendMessage(Text.literal("Hopper Speed set to:" + MBMBlocks.CFR.getHopperSpeed())); return 1;})
            .then(argument("hopperspeed", IntegerArgumentType.integer(0, 16))
                .executes(ctx -> {
                    MBMBlocks.CFR.setHopperSpeed(IntegerArgumentType.getInteger(ctx, "hopperspeed"));
                    Text.literal("Hopper Speed set to:" + MBMBlocks.CFR.getHopperSpeed());
                    return 1;
                })
            )
        ).then(literal("verticalslabsenabled").executes(ctx -> { ctx.getSource().sendMessage(Text.literal("Vertical Slabs Enabled set to:" + MBMBlocks.CFR.isSlabsEnabled())); return 1;})
                .then(argument("vsenabled", BoolArgumentType.bool())
                .executes(ctx -> {
                    MBMBlocks.CFR.setVerticalSlabsEnabled(BoolArgumentType.getBool(ctx, "vsenabled"));
                    Text.literal("Vertical Slabs Enabled set to:" + MBMBlocks.CFR.getHopperSpeed());
                    ctx.getSource().sendMessage(Text.literal("[MBM] Restart Minecraft for Changes"));
                    return 1;
                })
            )
        ).then(literal("hoppertopcollisiondisabled").executes(ctx -> { ctx.getSource().sendMessage(Text.literal("Hopper Top Collision set to:" + MBMBlocks.CFR.isHopperTopCollisionDisabled())); return 1;})
            .then(argument("hoppertopcollisiondisabled", BoolArgumentType.bool())
                .executes(ctx -> {
                    MBMBlocks.CFR.setHopperTopCollision(BoolArgumentType.getBool(ctx, "hoppertopcollisiondisabled"));
                    Text.literal("Hopper Top Collision set to:" + MBMBlocks.CFR.getHopperSpeed());
                    return 1;
                })
            )
        )
        ));
    }
}
