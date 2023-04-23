    // Shows name on top of entity
    @Override
    public boolean shouldShowName(MonsterTank entity) {
        return true;
    }

    // Spider eye type render in dark
    @Override
    protected int getBlockLightLevel(MonsterTank entity, BlockPos blockPos) {
        return 15;
    }

    // Rendering
    @Override
    public RenderType getRenderType(MonsterTank animatable, ResourceLocation texture,
                                    @Nullable MultiBufferSource bufferSource,
                                    float partialTick) {
        return RenderType.dragonExplosionAlpha(texture);
    }

    // Removes gravity from projectiles
    @Override
    public boolean isNoGravity() {
        if (this.isInWater())
            return false;
        return true;
    }