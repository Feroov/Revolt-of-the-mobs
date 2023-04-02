package com.feroov.rotm.entity;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Gunswine;
import com.feroov.rotm.entity.projectiles.RifleAmmo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntitiesROTM
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ROTM.MOD_ID);

    public static final RegistryObject<EntityType<Gunswine>> GUNSWINE =
            ENTITY_TYPES.register("gunswine",
                    () -> EntityType.Builder.of(Gunswine::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.8f)
                            .build(new ResourceLocation(ROTM.MOD_ID, "gunswine").toString()));

    public static final RegistryObject<EntityType<RifleAmmo>> RIFLE_AMMO = ENTITY_TYPES.register("rifle_ammo",
            () -> EntityType.Builder.<RifleAmmo>of(RifleAmmo::new, MobCategory.MISC).sized(0.2F, 0.2F)
                    .clientTrackingRange(9).build(new ResourceLocation(ROTM.MOD_ID, "rifle_ammo").toString()));


    public static void register(IEventBus eventBus) { ENTITY_TYPES.register(eventBus); }
}
