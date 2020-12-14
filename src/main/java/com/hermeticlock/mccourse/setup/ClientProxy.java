package com.hermeticlock.mccourse.setup;

import com.hermeticlock.mccourse.McCourseMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = McCourseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy implements IProxy {
    @Override
    public void init() {
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }
}
