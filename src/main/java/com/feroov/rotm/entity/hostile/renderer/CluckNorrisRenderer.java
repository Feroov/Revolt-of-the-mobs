package com.feroov.rotm.entity.hostile.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.CluckNorris;
import com.feroov.rotm.entity.hostile.model.CluckNorrisModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CluckNorrisRenderer extends GeoEntityRenderer<CluckNorris>
{
    public CluckNorrisRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new CluckNorrisModel());
    }

    @Override
    public ResourceLocation getTextureLocation(CluckNorris animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/clucknorris.png");
    }

    @Override
    public void render(CluckNorris entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight)
    {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    protected float getDeathMaxRotation(CluckNorris entityLivingBaseIn)
    {
        return 0;
    }
}
