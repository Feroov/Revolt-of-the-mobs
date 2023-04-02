package com.feroov.rotm.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class TiersROTM
{
    public static final ForgeTier ADMIN =
            new ForgeTier(999,999999,999f,9999f,10,
            Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(Blocks.BEDROCK));
}
