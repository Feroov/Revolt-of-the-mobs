package com.feroov.rotm.entity.hostile.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Stabbit;
import com.feroov.rotm.entity.hostile.model.StabbitModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class StabbitRenderer extends GeoEntityRenderer<Stabbit>
{
    public StabbitRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new StabbitModel());
        this.shadowRadius = 0.44F;
    }

    @Override
    public ResourceLocation getTextureLocation(Stabbit animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/stabbit.png");
    }

    @Override
    public void render(Stabbit entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight)
    {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    protected float getDeathMaxRotation(Stabbit entityLivingBaseIn)
    {
        return 0;
    }
}
