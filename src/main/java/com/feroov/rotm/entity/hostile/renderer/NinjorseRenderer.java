package com.feroov.rotm.entity.hostile.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Ninjorse;
import com.feroov.rotm.entity.hostile.model.NinjorseModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NinjorseRenderer extends GeoEntityRenderer<Ninjorse>
{
    public NinjorseRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new NinjorseModel());
        this.shadowRadius = 0.54F;
    }

    @Override
    public ResourceLocation getTextureLocation(Ninjorse animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/ninjorse.png");
    }

    @Override
    public void render(Ninjorse entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight)
    {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    protected float getDeathMaxRotation(Ninjorse entityLivingBaseIn)
    {
        return 0;
    }

    @Override
    public void preRender(PoseStack poseStack, Ninjorse animatable, BakedGeoModel model,
                          MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick,
                          int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay,
                red, green, blue, alpha);
        poseStack.scale(1.2F, 1.2F, 1.2F);
    }

    @Override
    protected int getBlockLightLevel(Ninjorse entity, BlockPos blockPos) {
        return 15;
    }
}
