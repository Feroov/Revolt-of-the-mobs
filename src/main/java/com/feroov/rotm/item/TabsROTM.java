package com.feroov.rotm.item;

import com.feroov.rotm.ROTM;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ROTM.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TabsROTM
{
    public static CreativeModeTab ROTM_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        ROTM_TAB = event.registerCreativeModeTab(new ResourceLocation(ROTM.MOD_ID, "rotm_tab"),
                builder -> builder.icon(() -> new ItemStack(ItemsROTM.GUNSWINE_SPAWN_EGG.get()))
                        .title(Component.translatable("creativemodetab.rotm_tab")));
    }
}
