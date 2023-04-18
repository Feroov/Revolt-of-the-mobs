package com.feroov.rotm.entity.projectiles.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.projectiles.FiftyCal;
import com.feroov.rotm.entity.projectiles.client.FiftyCalModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class FiftyCalRenderer extends GeoEntityRenderer<FiftyCal>
{
    public FiftyCalRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new FiftyCalModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FiftyCal animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/projectile/50cal.png");
    }
}
