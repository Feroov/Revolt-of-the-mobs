package com.feroov.rotm.entity.projectiles;

import com.feroov.rotm.entity.EntitiesROTM;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;


public class DeagleAmmo extends AbstractArrow implements GeoEntity
{

    public static final EntityDataAccessor<Integer> PARTICLE = SynchedEntityData.defineId(DeagleAmmo.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(ThrowableItemProjectile.class, EntityDataSerializers.ITEM_STACK);
    protected int timeInAir;
    protected boolean inAir;
    private int ticksInAir;

    private float projectiledamage = 8.2F;
    public SoundEvent hitSound = this.getDefaultHitGroundSoundEvent();

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public DeagleAmmo(EntityType<? extends DeagleAmmo> entityType, Level world)
    {
        super(entityType, world);
        this.pickup = Pickup.DISALLOWED;
    }

    public DeagleAmmo(Level world, LivingEntity owner, float damage)
    {
        super(EntitiesROTM.RIFLE_AMMO.get(), owner, world);
        this.projectiledamage = damage;
    }

    public DeagleAmmo(Level world, LivingEntity owner)
    {
        super(EntitiesROTM.RIFLE_AMMO.get(), owner, world);
    }

    protected DeagleAmmo(EntityType<? extends DeagleAmmo> type, double x, double y, double z, Level world)
    {
        this(type, world);
    }


    /******************************************** Methods of Interest ************************************************************/
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult)
    {
        Entity entity = entityHitResult.getEntity();
        if (entityHitResult.getType() != HitResult.Type.ENTITY || !((EntityHitResult) entityHitResult).getEntity().is(entity))
        {
            if (!this.level.isClientSide) { this.remove(RemovalReason.KILLED); }
        }

        Entity entity1 = this.getOwner();
        DamageSource damagesource;
        if (entity1 == null) { damagesource = this.damageSources().arrow(this, this); }
        else
        {
            damagesource = this.damageSources().arrow(this, entity1);
            if (entity1 instanceof LivingEntity) {
                ((LivingEntity)entity1).setLastHurtMob(entity);
            }
        }
        if (entity.hurt(damagesource, projectiledamage))
        {
            if (entity instanceof LivingEntity)
            {
                LivingEntity livingentity = (LivingEntity) entity;
                if (!this.level.isClientSide && entity1 instanceof LivingEntity)
                {
                    EnchantmentHelper.doPostHurtEffects(livingentity, entity1);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity) entity1, livingentity);
                    this.remove(RemovalReason.KILLED);
                }
                this.doPostHurtEffects(livingentity);
                if (entity1 != null && livingentity != entity1 && livingentity instanceof Player
                        && entity1 instanceof ServerPlayer && !this.isSilent())
                {
                    ((ServerPlayer) entity1).connection
                            .send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 14.0F));
                }
            }
        } else { if (!this.level.isClientSide) { this.remove(RemovalReason.KILLED); } }
    }


    @Override
    protected void onHitBlock(BlockHitResult blockHitResult)
    {
        super.onHitBlock(blockHitResult);
        if (!this.level.isClientSide())
            this.remove(RemovalReason.DISCARDED);
        this.setSoundEvent(SoundEvents.ARMOR_EQUIP_IRON);
    }

    @Override
    public void tick()
    {
        super.tick();
        ++this.ticksInAir;
        if (this.ticksInAir >= 80) { this.remove(RemovalReason.DISCARDED); }
    }
    /***************************************************************************************************************************/


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
        controllers.add(new AnimationController<>(this, event ->
        {
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() { return cache; }

    @Override
    protected void defineSynchedData() { super.defineSynchedData(); this.entityData.define(PARTICLE, 0); }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() { return NetworkHooks.getEntitySpawningPacket(this);}

    @Override
    protected void tickDespawn() { ++this.ticksInAir; if (this.tickCount >= 40) { this.remove(RemovalReason.KILLED); }}

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy)
    {
        super.shoot(x, y, z, velocity, inaccuracy);
        this.ticksInAir = 0;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putShort("life", (short) this.ticksInAir);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        this.ticksInAir = compound.getShort("life");
    }

    @Override
    protected ItemStack getPickupItem() { return null; }


    @Override
    public void setSoundEvent(SoundEvent soundIn) { this.hitSound = soundIn; }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent()  { return SoundEvents.ARMOR_EQUIP_IRON; }

    @Override
    public boolean displayFireAnimation() { return false; }
}
