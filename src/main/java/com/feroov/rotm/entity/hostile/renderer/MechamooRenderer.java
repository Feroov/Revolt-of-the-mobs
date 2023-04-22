package com.feroov.rotm.entity.hostile.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Mechamoo;
import com.feroov.rotm.entity.hostile.model.MechamooModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MechamooRenderer extends GeoEntityRenderer<Mechamoo>
{
    public MechamooRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new MechamooModel());
        this.shadowRadius = 1.74F;
    }

    @Override
    public ResourceLocation getTextureLocation(Mechamoo animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/mechamoo.png");
    }

    @Override
    public void render(Mechamoo entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight)
    {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    protected float getDeathMaxRotation(Mechamoo entityLivingBaseIn)
    {
        return 0;
    }
}
