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

    public static final RegistryObject<SoundEvent> GUNSWINE_DEATH = SOUND_EVENTS.register("gunswine_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ROTM.MOD_ID, "gunswine_death")));
    public static final RegistryObject<SoundEvent> AK47 = SOUND_EVENTS.register("ak47", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ROTM.MOD_ID, "ak47")));
    public static final RegistryObject<SoundEvent> ROCKET = SOUND_EVENTS.register("rocket", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ROTM.MOD_ID, "rocket")));
    public static final RegistryObject<SoundEvent> FIFTY_CAL = SOUND_EVENTS.register("50cal", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ROTM.MOD_ID, "50cal")));
    public static final RegistryObject<SoundEvent> DEAGLE = SOUND_EVENTS.register("deagle", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ROTM.MOD_ID, "deagle")));
    public static final RegistryObject<SoundEvent> TANK_SHOT = SOUND_EVENTS.register("tank", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ROTM.MOD_ID, "tank")));
    public static final RegistryObject<SoundEvent> TANK_IDLE = SOUND_EVENTS.register("tank_idle", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ROTM.MOD_ID, "tank_idle")));
    public static final RegistryObject<SoundEvent> MECHAMOO_SHOT = SOUND_EVENTS.register("mechamoo_shot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ROTM.MOD_ID, "mechamoo_shot")));

    public static void register(IEventBus eventBus) { SOUND_EVENTS.register(eventBus); }
}
