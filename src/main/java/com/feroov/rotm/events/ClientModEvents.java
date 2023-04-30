package com.feroov.rotm.events;


import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.EntitiesROTM;
import com.feroov.rotm.entity.hostile.renderer.*;
import com.feroov.rotm.entity.projectiles.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ROTM.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{
    public ClientModEvents(){}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        // Hostile
        event.registerEntityRenderer(EntitiesROTM.GUNSWINE.get(), GunswineRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.COWPG.get(), CowpgRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.MONSTERTANK.get(), MonsterTankRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.MECHAMOO.get(), MechamooRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.STABBIT.get(), StabbitRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.HORSIPER.get(), HorsiperRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.NINJORSE.get(), NinjorseRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.GIGAHORSE.get(), GigahorseRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.CLUCKNORRIS.get(), CluckNorrisRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.HELICOCKTER.get(), HelicockterRenderer::new);

        // Misc
        event.registerEntityRenderer(EntitiesROTM.RIFLE_AMMO.get(), RifleAmmoRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.TANKSHELL.get(), TankShellRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.ROCKET.get(), RocketRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.FIFTY_CAL.get(), FiftyCalRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.DEAGLE.get(), DeagleRenderer::new);
    }
}
