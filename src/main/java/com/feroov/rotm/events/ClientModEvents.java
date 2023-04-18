package com.feroov.rotm.events;


import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.EntitiesROTM;
import com.feroov.rotm.entity.hostile.renderer.CowpgRenderer;
import com.feroov.rotm.entity.hostile.renderer.GunswineRenderer;
import com.feroov.rotm.entity.hostile.renderer.HorsiperRenderer;
import com.feroov.rotm.entity.hostile.renderer.StabbitRenderer;
import com.feroov.rotm.entity.projectiles.renderer.FiftyCalRenderer;
import com.feroov.rotm.entity.projectiles.renderer.RifleAmmoRenderer;
import com.feroov.rotm.entity.projectiles.renderer.RocketRenderer;
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
        event.registerEntityRenderer(EntitiesROTM.STABBIT.get(), StabbitRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.HORSIPER.get(), HorsiperRenderer::new);

        // Misc
        event.registerEntityRenderer(EntitiesROTM.RIFLE_AMMO.get(), RifleAmmoRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.ROCKET.get(), RocketRenderer::new);
        event.registerEntityRenderer(EntitiesROTM.FIFTY_CAL.get(), FiftyCalRenderer::new);
    }
}
