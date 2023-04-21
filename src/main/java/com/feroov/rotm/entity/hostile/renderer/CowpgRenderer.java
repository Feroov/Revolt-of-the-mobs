package com.feroov.rotm.entity.hostile.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Cowpg;
import com.feroov.rotm.entity.hostile.model.CowpgModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CowpgRenderer extends GeoEntityRenderer<Cowpg>
{
    public CowpgRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new CowpgModel());
        this.shadowRadius = 0.64F;
    }

    @Override
    public ResourceLocation getTextureLocation(Cowpg animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/cowpg.png");
    }

    @Override
    public void render(Cowpg entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight)
    {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    protected float getDeathMaxRotation(Cowpg entityLivingBaseIn)
    {
        return 0;
    }
}
