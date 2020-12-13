package com.hermeticlock.mccourse.item;

import com.hermeticlock.mccourse.McCourseMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class CopperedApple extends Item {

    public CopperedApple() {
        super(new Properties().group(McCourseMod.COURSE_TAB)
                .food(new Food.Builder()
                        .hunger(5)
                        .saturation(1.5f)
                        .effect(() -> new EffectInstance(
                                Effects.GLOWING, 300, 1
                        ), 0.5f).build()
                )
        );
    }



}
