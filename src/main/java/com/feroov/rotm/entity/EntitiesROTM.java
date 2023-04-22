package com.feroov.rotm.entity;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.*;
import com.feroov.rotm.entity.projectiles.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntitiesROTM
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ROTM.MOD_ID);

    // Mobs
    public static final RegistryObject<EntityType<Gunswine>> GUNSWINE =
            ENTITY_TYPES.register("gunswine",
                    () -> EntityType.Builder.of(Gunswine::new, MobCategory.CREATURE)
                            .sized(0.8f, 1.8f)
                            .build(new ResourceLocation(ROTM.MOD_ID, "gunswine").toString()));

    public static final RegistryObject<EntityType<Cowpg>> COWPG =
            ENTITY_TYPES.register("cowpg",
                    () -> EntityType.Builder.of(Cowpg::new, MobCategory.CREATURE)
                            .sized(0.9f, 2.2f)
                            .build(new ResourceLocation(ROTM.MOD_ID, "cowpg").toString()));

    public static final RegistryObject<EntityType<MonsterTank>> MONSTERTANK =
            ENTITY_TYPES.register("monstertank",
                    () -> EntityType.Builder.of(MonsterTank::new, MobCategory.CREATURE)
                            .sized(3.2f, 3.0f).fireImmune().immuneTo(Blocks.LAVA)
                            .build(new ResourceLocation(ROTM.MOD_ID, "monstertank").toString()));

    public static final RegistryObject<EntityType<Mechamoo>> MECHAMOO =
            ENTITY_TYPES.register("mechamoo",
                    () -> EntityType.Builder.of(Mechamoo::new, MobCategory.CREATURE)
                            .sized(3.2f, 5.0f).fireImmune().immuneTo(Blocks.LAVA)
                            .build(new ResourceLocation(ROTM.MOD_ID, "mechamoo").toString()));

    public static final RegistryObject<EntityType<Stabbit>> STABBIT =
            ENTITY_TYPES.register("stabbit",
                    () -> EntityType.Builder.of(Stabbit::new, MobCategory.CREATURE)
                            .sized(0.7f, 0.9f)
                            .build(new ResourceLocation(ROTM.MOD_ID, "stabbit").toString()));

    public static final RegistryObject<EntityType<Horsiper>> HORSIPER =
            ENTITY_TYPES.register("horsiper",
                    () -> EntityType.Builder.of(Horsiper::new, MobCategory.CREATURE)
                            .sized(0.9f, 2.2f)
                            .build(new ResourceLocation(ROTM.MOD_ID, "horsiper").toString()));

    public static final RegistryObject<EntityType<CluckNorris>> CLUCKNORRIS =
            ENTITY_TYPES.register("clucknorris",
                    () -> EntityType.Builder.of(CluckNorris::new, MobCategory.CREATURE)
                            .sized(0.7f, 1.2f)
                            .build(new ResourceLocation(ROTM.MOD_ID, "clucknorris").toString()));



    // Projectiles
    public static final RegistryObject<EntityType<RifleAmmo>> RIFLE_AMMO = ENTITY_TYPES.register("rifle_ammo",
            () -> EntityType.Builder.<RifleAmmo>of(RifleAmmo::new, MobCategory.MISC).sized(0.2F, 0.2F)
                    .clientTrackingRange(9).build(new ResourceLocation(ROTM.MOD_ID, "rifle_ammo").toString()));

    public static final RegistryObject<EntityType<DeagleAmmo>> DEAGLE = ENTITY_TYPES.register("deagle",
            () -> EntityType.Builder.<DeagleAmmo>of(DeagleAmmo::new, MobCategory.MISC).sized(0.55F, 0.55F)
                    .clientTrackingRange(9).build(new ResourceLocation(ROTM.MOD_ID, "deagle").toString()));

    public static final RegistryObject<EntityType<FiftyCal>> FIFTY_CAL = ENTITY_TYPES.register("fifty_cal",
            () -> EntityType.Builder.<FiftyCal>of(FiftyCal::new, MobCategory.MISC).sized(0.7F, 0.7F)
                    .clientTrackingRange(9).build(new ResourceLocation(ROTM.MOD_ID, "fifty_cal").toString()));

    public static final RegistryObject<EntityType<Rocket>> ROCKET = ENTITY_TYPES.register("rocket",
            () -> EntityType.Builder.<Rocket>of(Rocket::new, MobCategory.MISC).sized(0.7F, 0.7F)
                    .clientTrackingRange(9).build(new ResourceLocation(ROTM.MOD_ID, "rocket").toString()));

    public static final RegistryObject<EntityType<TankShell>> TANKSHELL = ENTITY_TYPES.register("tankshell",
            () -> EntityType.Builder.<TankShell>of(TankShell::new, MobCategory.MISC).sized(1.0F, 1.0F)
                    .clientTrackingRange(9).build(new ResourceLocation(ROTM.MOD_ID, "tankshell").toString()));


    public static void register(IEventBus eventBus) { ENTITY_TYPES.register(eventBus); }
}
