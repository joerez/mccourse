package com.hermeticlock.mccourse.events;

import com.hermeticlock.mccourse.item.ModItems;
import com.hermeticlock.mccourse.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Collection;
import java.util.logging.LogManager;

public class ModEvents {

    @SubscribeEvent
    public void onCopperedSheep(AttackEntityEvent event) {
        if (event.getPlayer().getHeldItemMainhand().getItem() == ModItems.COPPERED_APPLE.get()) {
            if (event.getTarget().isAlive()) {
                LivingEntity target = (LivingEntity)event.getTarget();

                if (target instanceof SheepEntity) {
                    PlayerEntity player = event.getPlayer();

                    // delete one of the held items
                    player.getHeldItemMainhand().shrink(1);

                    target.addPotionEffect(new EffectInstance(Effects.GLOWING, Config.COPPERED_GLOW_DURATION.get()));

                    if (!player.world.isRemote()) {
                        String msg = TextFormatting.YELLOW + "Sheep is now glowing!";

                        player.sendMessage(
                                new StringTextComponent(msg), player.getUniqueID()
                        );

                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onCopperedSheepDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();

        if (entity instanceof SheepEntity) {
            World world = entity.getEntityWorld();
            Collection<ItemEntity> drops = event.getDrops();

            if (entity.isPotionActive(Effects.GLOWING)) {

                drops.add(new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(),
                        new ItemStack(ModItems.COPPER_INGOT.get())
                ));


            }

        }
    }

    @SubscribeEvent
    public void onPowerWord(ServerChatEvent event) {
        if (event.getMessage().contains("shazam")) {
            PlayerEntity player = event.getPlayer();

            player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 400, 10));

            Minecraft.getInstance().player.jump();
            player.world.createExplosion(player, player.getPosX(), player.getPosY(), player.getPosZ(), 10, true, Explosion.Mode.BREAK);
        }

    }



}
