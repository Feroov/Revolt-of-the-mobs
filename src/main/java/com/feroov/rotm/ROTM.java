package com.feroov.rotm;

import com.feroov.rotm.entity.item.ItemsROTM;
import com.feroov.rotm.entity.item.TabsROTM;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(ROTM.MOD_ID)
public class ROTM
{
    public static final String MOD_ID = "rotm";
    private static final Logger LOGGER = LogUtils.getLogger();
    public ROTM()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemsROTM.register(eventBus);

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        if(event.getTab() == TabsROTM.ROTM_TAB)
        {
            event.accept(ItemsROTM.ADMIN_SWORD);
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
