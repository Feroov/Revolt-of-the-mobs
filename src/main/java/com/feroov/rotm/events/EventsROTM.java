package com.feroov.rotm.events;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.EntitiesROTM;
import com.feroov.rotm.entity.hostile.Gunswine;
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
    }
}
