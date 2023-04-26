package com.feroov.rotm.events;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.EntitiesROTM;
import com.feroov.rotm.entity.hostile.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ROTM.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventsROTM
{
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {
        event.put(EntitiesROTM.GUNSWINE.get(), Gunswine.setAttributes());
        event.put(EntitiesROTM.COWPG.get(), Cowpg.setAttributes());
        event.put(EntitiesROTM.MONSTERTANK.get(), MonsterTank.setAttributes());
        event.put(EntitiesROTM.MECHAMOO.get(), Mechamoo.setAttributes());
        event.put(EntitiesROTM.STABBIT.get(), Stabbit.setAttributes());
        event.put(EntitiesROTM.HORSIPER.get(), Horsiper.setAttributes());
        event.put(EntitiesROTM.NINJORSE.get(), Ninjorse.setAttributes());
        event.put(EntitiesROTM.CLUCKNORRIS.get(), CluckNorris.setAttributes());
    }
}
