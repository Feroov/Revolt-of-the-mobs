package com.feroov.rotm.entity.hostile;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nonnull;


public class Gigahorse extends Monster implements GeoEntity
{
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    protected static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(Gigahorse.class, EntityDataSerializers.BOOLEAN);


    public Gigahorse(EntityType<? extends Monster> entityType, Level level) { super(entityType, level); }

    public static AttributeSupplier setAttributes()
    {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 400.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.42D)
                .add(Attributes.FOLLOW_RANGE, 60.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 2.0D)
                .add(Attributes.ATTACK_DAMAGE, 19.5D).build();
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new NinjorseMeleeAttack(this, 1.0D, true));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Mob.class, 15.0F));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (livingEntity) -> {
            return livingEntity instanceof Enemy && !(livingEntity instanceof Gigahorse) && !(livingEntity instanceof Horsiper) && !(livingEntity instanceof Ninjorse);
        }));
        this.goalSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Animal.class, true));
        this.goalSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.73D));
        this.goalSelector.addGoal(7, new MoveTowardsRestrictionGoal(this, 0.73D));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        this.playSound(SoundEvents.HORSE_ANGRY, 1.0F, 0.2F);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        this.playSound(SoundEvents.ZOMBIE_HORSE_HURT, 1.0F, 0.2F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(SoundEvents.IRON_GOLEM_DEATH, 1.0F, 0.2F);
        return null;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) { return 3.01F; }

    @Override
    protected void tickDeath()
    {
        ++this.deathTime;
        if (this.deathTime == 60 && !this.level.isClientSide())
        {
            this.level.broadcastEntityEvent(this, (byte)60);
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }


    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState)
    {
        if ((this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()))
        {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        }
        if (isAttacking())
        {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        }

        if (!(walkAnimation.speed() > -0.10F && walkAnimation.speed() < 0.10F) && !this.isAggressive())
        {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        if(isAggressive())
        {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public boolean causeFallDamage(float p_146828_, float p_146829_, DamageSource p_146830_) {
        return false;
    }

    @Override
    public boolean doHurtTarget(Entity entityIn)
    {
        if (!super.doHurtTarget(entityIn))
        {
            return false;
        }
        else
        {
            if (entityIn instanceof LivingEntity)
            {
                ((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 6));
            }
            return true;
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() { return cache; }

    @Override
    protected void defineSynchedData() { super.defineSynchedData(); this.entityData.define(ATTACKING, false); }

    public void setAttacking(boolean attack) {
        this.entityData.set(ATTACKING, attack);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }


    public static class NinjorseMeleeAttack extends MeleeAttackGoal
    {
        private Gigahorse entity;
        private int animCounter = 0;
        private int animTickLength = 20;

        public NinjorseMeleeAttack(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen)
        {
            super(mob, speedModifier, followingTargetEvenIfNotSeen);
            if(mob instanceof Gigahorse c)
            {
                entity = c;
            }
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity p_25557_, double p_25558_)
        {
            if (p_25558_ <= this.getAttackReachSqr(p_25557_) && this.getTicksUntilNextAttack() <= 0)
            {
                if(entity != null)
                {
                    entity.setAttacking(true);
                    animCounter = 0;
                }
            }

            super.checkAndPerformAttack(p_25557_, p_25558_);
        }

        @Override
        public void tick()
        {
            super.tick();
            if(entity.isAttacking())
            {
                animCounter++;

                if(animCounter >= animTickLength)
                {
                    animCounter = 0;
                    entity.setAttacking(false);
                }
            }
        }

        @Override
        public void stop()
        {
            animCounter = 0;
            entity.setAttacking(false);
            super.stop();
        }
    }

    @Override
    public boolean isBaby() { return false;}
}
