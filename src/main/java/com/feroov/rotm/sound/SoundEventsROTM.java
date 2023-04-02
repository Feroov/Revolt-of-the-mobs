package com.feroov.rotm.sound;

import com.feroov.rotm.ROTM;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundEventsROTM
{
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ROTM.MOD_ID);

    // Gunswine
    public static final RegistryObject<SoundEvent> GUNSWINE_DEATH = SOUND_EVENTS.register("gunswine_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ROTM.MOD_ID, "gunswine_death")));


    public static final RegistryObject<SoundEvent> AK47 = SOUND_EVENTS.register("ak47", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ROTM.MOD_ID, "ak47")));


    public static void register(IEventBus eventBus) { SOUND_EVENTS.register(eventBus); }
}
