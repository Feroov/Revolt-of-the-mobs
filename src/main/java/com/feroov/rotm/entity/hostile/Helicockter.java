package com.feroov.rotm.entity.hostile;

import com.feroov.rotm.entity.projectiles.DeagleAmmo;
import com.feroov.rotm.sound.SoundEventsROTM;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumSet;

public class Helicockter extends Monster implements GeoEntity
{
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public static final EntityDataAccessor<Integer> ATTACK = SynchedEntityData.defineId(Helicockter.class, EntityDataSerializers.INT);

    public Helicockter(EntityType<? extends Monster> entityType, Level level) { super(entityType, level); }

    public static AttributeSupplier setAttributes()
    {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.FOLLOW_RANGE, 19.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6f).build();
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new OpenDoorGoal(this,true));
        this.targetSelector.addGoal(2, new CluckNorrisAttackGoal(this, 0.5D, true, 3));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> {
            return p_28879_ instanceof Enemy && !(p_28879_ instanceof Helicockter);
        }));
        this.goalSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Animal.class, true));
        this.goalSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.goalSelector.addGoal(4, new CluckNorrisRangedAttackGoal(this, 0.4D, 22.0D, 19.0F, 0));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(6, new MoveTowardsRestrictionGoal(this, 0.4D));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        this.playSound(SoundEvents.CHICKEN_AMBIENT, 1.0F, 0.2F);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        this.playSound(SoundEvents.CHICKEN_HURT, 1.0F, 0.2F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(SoundEvents.CHICKEN_DEATH, 1.0F, 0.2F);
        return null;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) { return 1.15F; }

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
        controllerRegistrar.add(new AnimationController<>(this, "attackController", 0, this::attack));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState)
    {
        if(tAnimationState.isMoving())
        {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        tAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    private <E extends GeoEntity> PlayState attack(AnimationState<E> event)
    {
        if ((this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()))
        {
            event.getController().setAnimation(RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        }
        if (isAggressive())
        {
            event.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() { return cache; }

    @Override
    protected void defineSynchedData() { super.defineSynchedData(); this.entityData.define(ATTACK, 1); }


    public void setAttackingState(int time) { this.entityData.set(ATTACK, time); }

    public static class CluckNorrisRangedAttackGoal extends Goal
    {
        private final Helicockter mob;
        private final Helicockter rangedAttackMob;
        @Nullable
        private LivingEntity target;
        private int attackTime = -1;
        private int seeTime, statecheck;
        private final double attackIntervalMin, attackIntervalMax, speedModifier;
        private final float attackRadius, attackRadiusSqr;
        private boolean strafingClockwise, strafingBackwards;
        private int strafingTime = -1;

        public CluckNorrisRangedAttackGoal(Helicockter cluckNorris, double speedIn, double dpsIn, float rangeIn, int state)
        {
            this(cluckNorris, speedIn, dpsIn, dpsIn, rangeIn, state);
        }

        public CluckNorrisRangedAttackGoal(Helicockter cluckNorris, double speedIn, double atckIntervalMin, double atckIntervalMax, float atckRadius, int state)
        {
            if (cluckNorris == null)
            {
                throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
            }
            else
            {
                this.rangedAttackMob =  cluckNorris;
                this.mob =  cluckNorris;
                this.speedModifier = speedIn;
                this.attackIntervalMin = atckIntervalMin;
                this.attackIntervalMax = atckIntervalMax;
                this.attackRadius = atckRadius;
                this.attackRadiusSqr = atckRadius * atckRadius;
                this.statecheck = state;

                this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
            }
        }


        public boolean canUse()
        {
            LivingEntity livingentity = this.mob.getTarget();
            if (livingentity != null && livingentity.isAlive())
            {
                this.target = livingentity;
                return true;
            }
            else
            {
                return false;
            }
        }

        public boolean canContinueToUse() { return this.canUse() || !this.mob.getNavigation().isDone(); }

        public void stop() {
            this.target = null;
            this.seeTime = 0;
            this.attackTime = -1;
        }

        public boolean requiresUpdateEveryTick() { return true; }

        public void tick()
        {
            LivingEntity livingentity = this.mob.getTarget();
            double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
            boolean flag = this.mob.getSensing().hasLineOfSight(this.target);
            if (flag) { ++this.seeTime; } else { this.seeTime = 0; }

            if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 5) {
                this.mob.getNavigation().stop();
            } else { this.mob.getNavigation().moveTo(this.target, this.speedModifier); }

            if (livingentity != null) {
                boolean flag1 = this.seeTime > 0;
                if (flag != flag1) { this.seeTime = 0;}
                if (flag) { ++this.seeTime;} else {--this.seeTime;}

                // strafing going backwards type stuff
                if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 20) {
                    this.mob.getNavigation().stop();
                    ++this.strafingTime;
                } else { this.mob.getNavigation().moveTo(livingentity, this.speedModifier); this.strafingTime = -1; }

                if (this.strafingTime >= 20)
                {
                    if ((double)this.mob.getRandom().nextFloat() < 0.3D) { this.strafingClockwise = !this.strafingClockwise; }
                    if ((double)this.mob.getRandom().nextFloat() < 0.3D) { this.strafingBackwards = !this.strafingBackwards; }
                    this.strafingTime = 0;
                }

                if (this.strafingTime > -1) {
                    if (d0 > (double)(this.attackRadiusSqr * 0.75F)) { this.strafingBackwards = false; }
                    else if (d0 < (double)(this.attackRadiusSqr * 0.25F)) { this.strafingBackwards = true; }
                    //speed shit
                    this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.0F : 0.0F, this.strafingClockwise ? 0.3F : -0.3F);
                    this.mob.lookAt(livingentity, 30.0F, 30.0F);
                }
                this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
                if (--this.attackTime == 0) {
                    if (!flag) { return; }
                    if (this.mob.isUsingItem()) {
                        if (!flag && this.seeTime < -60) { this.mob.stopUsingItem(); }
                        else if (flag) { int i = this.mob.getTicksUsingItem();
                            if (i >= 19) { this.mob.setAttackingState(statecheck); }
                            if (i >= 20) { this.mob.stopUsingItem(); }
                        }
                    }
                    float f = (float)Math.sqrt(d0) / this.attackRadius;
                    float f1 = Mth.clamp(f, 0.1F, 1.0F);
                    this.rangedAttackMob.performRangedAttack(this.target, f1);
                    this.attackTime = Mth.floor(f * (float)(this.attackIntervalMax - this.attackIntervalMin) + (float)this.attackIntervalMin);
                } else if (this.attackTime < 0) {
                    this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0)
                            / (double)this.attackRadius, (double)this.attackIntervalMin, (double)this.attackIntervalMax));
                }

            }
        }
    }


    public void performRangedAttack(LivingEntity livingEntity, float p_32142_)
    {
        DeagleAmmo arrow = new DeagleAmmo(this.level, this);
        double d0 = livingEntity.getEyeY() - (double)1.5F;
        double d1 = livingEntity.getX() - this.getX();
        double d2 = d0 - arrow.getY();
        double d3 = livingEntity.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
        arrow.shoot(d1, d2 + d4, d3, 1.8F, 0.1F);
        this.playSound(SoundEventsROTM.DEAGLE.get(), 6.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(arrow);
    }

    public boolean canFireProjectileWeapon(ProjectileWeaponItem projectileWeaponItem) { return projectileWeaponItem == Items.BOW; }



    static class CluckNorrisAttackGoal extends MeleeAttackGoal
    {
        private final Helicockter entity;
        private final double speedModifier;
        private int statecheck, ticksUntilNextPathRecalculation, ticksUntilNextAttack;
        private double pathedTargetX, pathedTargetY, pathedTargetZ;

        public CluckNorrisAttackGoal(Helicockter horsiper, double speedIn, boolean longMemoryIn, int state)
        {
            super(horsiper, speedIn, longMemoryIn);
            this.entity = horsiper;
            this.statecheck = state;
            this.speedModifier = speedIn;
        }

        public void start() { super.start();}
        public boolean canUse() { return super.canUse();}

        public void stop() { super.stop(); this.entity.setAggressive(false); this.entity.setAttackingState(0); }

        public void tick()
        {
            LivingEntity livingentity = this.entity.getTarget();
            if (livingentity != null)
            {
                this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
                double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
                if ((this.mob.getSensing().hasLineOfSight(livingentity))
                        && this.ticksUntilNextPathRecalculation <= 0
                        && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D
                        || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY,
                        this.pathedTargetZ) >= 1.0D
                        || this.mob.getRandom().nextFloat() < 0.05F))
                {
                    this.pathedTargetX = livingentity.getX();
                    this.pathedTargetY = livingentity.getY();
                    this.pathedTargetZ = livingentity.getZ();
                    this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                    if (d0 > 1024.0D) { this.ticksUntilNextPathRecalculation += 10; }
                    else if (d0 > 256.0D) { this.ticksUntilNextPathRecalculation += 5;}
                    if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier))
                    {
                        this.ticksUntilNextPathRecalculation += 15;
                    }
                }
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 0, 0);
                this.checkAndPerformAttack(livingentity, d0);
            }
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity livingentity, double squaredDistance)
        {
            double d0 = this.getAttackReachSqr(livingentity);
            if (squaredDistance <= d0 && this.getTicksUntilNextAttack() <= 0)
            {
                this.resetAttackCooldown();
                this.entity.setAttackingState(statecheck);
                this.mob.doHurtTarget(livingentity);
            }
        }

        @Override
        protected int getAttackInterval() { return 50;}

        @Override
        protected double getAttackReachSqr(LivingEntity attackTarget)
        {
            return this.mob.getBbWidth() * 1.0F * this.mob.getBbWidth() * 1.0F + attackTarget.getBbWidth();
        }
    }
    @Override
    public boolean isBaby() { return false;}
}
