package com.feroov.rotm.entity.hostile.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Gunswine;
import com.feroov.rotm.entity.hostile.model.GunswineModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GunswineRenderer extends GeoEntityRenderer<Gunswine>
{
    public GunswineRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new GunswineModel());
    }

    @Override
    public ResourceLocation getTextureLocation(Gunswine animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/gunswine.png");
    }

    @Override
    public void render(Gunswine entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight)
    {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    protected float getDeathMaxRotation(Gunswine entityLivingBaseIn)
    {
        return 0;
    }
}
