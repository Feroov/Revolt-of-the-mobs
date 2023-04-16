package com.feroov.rotm.entity.projectiles.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.projectiles.RifleAmmo;
import com.feroov.rotm.entity.projectiles.client.RifleAmmoModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class RifleAmmoRenderer extends GeoEntityRenderer<RifleAmmo>
{
    public RifleAmmoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new RifleAmmoModel());
    }

    @Override
    public ResourceLocation getTextureLocation(RifleAmmo animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/projectile/rifleammo.png");
    }

    @Override
    public void preRender(PoseStack poseStack, RifleAmmo animatable, BakedGeoModel model,
                          MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick,
                          int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay,
                red, green, blue, alpha);
        poseStack.scale(0.1F, 0.1F, 0.1F);
    }
}
