package com.feroov.rotm.entity.hostile.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Horsiper;
import com.feroov.rotm.entity.hostile.model.HorsiperModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HorsiperRenderer extends GeoEntityRenderer<Horsiper>
{
    public HorsiperRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new HorsiperModel());
        this.shadowRadius = 0.64F;
    }

    @Override
    public ResourceLocation getTextureLocation(Horsiper animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/horsiper.png");
    }

    @Override
    public void render(Horsiper entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight)
    {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    protected float getDeathMaxRotation(Horsiper entityLivingBaseIn)
    {
        return 0;
    }
}
