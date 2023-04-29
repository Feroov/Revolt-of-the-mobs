package com.feroov.rotm.entity.hostile.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Gigahorse;
import com.feroov.rotm.entity.hostile.model.GigahorseModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GigahorseRenderer extends GeoEntityRenderer<Gigahorse>
{
    public GigahorseRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new GigahorseModel());
        this.shadowRadius = 0.74F;
    }

    @Override
    public ResourceLocation getTextureLocation(Gigahorse animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/gigahorse.png");
    }

    @Override
    public void render(Gigahorse entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight)
    {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    protected float getDeathMaxRotation(Gigahorse entityLivingBaseIn)
    {
        return 0;
    }

    @Override
    public void preRender(PoseStack poseStack, Gigahorse animatable, BakedGeoModel model,
                          MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick,
                          int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay,
                red, green, blue, alpha);
        poseStack.scale(1.1F, 1.1F, 1.1F);
    }
}
