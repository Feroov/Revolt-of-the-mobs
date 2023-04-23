package com.feroov.rotm;

import com.feroov.rotm.entity.EntitiesROTM;
import com.feroov.rotm.item.ItemsROTM;
import com.feroov.rotm.item.TabsROTM;
import com.feroov.rotm.sound.SoundEventsROTM;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(ROTM.MOD_ID)
public class ROTM
{
    public static final String MOD_ID = "rotm";

    public ROTM()

    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        SoundEventsROTM.register(eventBus);
        ItemsROTM.register(eventBus);
        EntitiesROTM.register(eventBus);

        eventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        if(event.getTab() == TabsROTM.ROTM_TAB)
        {
            event.accept(ItemsROTM.ADMIN_SWORD);
            event.accept(ItemsROTM.GUNSWINE_SPAWN_EGG);
            event.accept(ItemsROTM.COWPG_SPAWN_EGG);
            event.accept(ItemsROTM.MONSTERTANK_SPAWN_EGG);
            event.accept(ItemsROTM.MECHAMOO_SPAWN_EGG);
            event.accept(ItemsROTM.STABBIT_SPAWN_EGG);
            event.accept(ItemsROTM.HORSIPER_SPAWN_EGG);
            event.accept(ItemsROTM.CLUCKNORRIS_SPAWN_EGG);
        }
    }
}
