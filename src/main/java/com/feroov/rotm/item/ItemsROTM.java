package com.feroov.rotm.item;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.EntitiesROTM;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ItemsROTM
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ROTM.MOD_ID);

    public static final RegistryObject<Item> ADMIN_SWORD = ITEMS.register("admin_sword",
            () -> new SwordItem(TiersROTM.ADMIN, 0, 9996f,
                    new Item.Properties()) {
        @Override
        public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
        {
            super.appendHoverText(stack, worldIn, tooltip, flagIn);
            tooltip.add(Component.translatable("Banish every being off the screen")
                    .withStyle(ChatFormatting.RED));
        }
    });

    public static final RegistryObject<Item> GUNSWINE_SPAWN_EGG = ITEMS.register("gunswine_spawn_egg",
            () -> new ForgeSpawnEggItem(EntitiesROTM.GUNSWINE, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final RegistryObject<Item> COWPG_SPAWN_EGG = ITEMS.register("cowpg_spawn_egg",
            () -> new ForgeSpawnEggItem(EntitiesROTM.COWPG, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));


    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
