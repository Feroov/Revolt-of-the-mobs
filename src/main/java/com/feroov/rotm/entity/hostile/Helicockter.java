package com.feroov.rotm.entity.hostile;



import com.feroov.rotm.entity.projectiles.FiftyCal;
import com.feroov.rotm.entity.projectiles.TankShell;
import com.feroov.rotm.sound.SoundEventsROTM;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
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

public class Helicockter extends FlyingMob implements GeoEntity
{
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public static final EntityDataAccessor<Integer> ATTACK = SynchedEntityData.defineId(Helicockter.class, EntityDataSerializers.INT);

    public Helicockter(EntityType<? extends FlyingMob> entityType, Level level)
    {
        super(entityType, level);
        this.moveControl = new Helicockter.HelicockterMoveControl(this);
    }

    public static AttributeSupplier setAttributes()
    {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 130.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.FOLLOW_RANGE, 50)
                .add(Attributes.MOVEMENT_SPEED, 1.6f).build();
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new Helicockter.HelicockterRangedAttackGoal(this, 2.50D, 4.3D, 50.0F, 0));
        this.goalSelector.addGoal(1, new Helicockter.RandomFloatAroundGoal(this));
        this.goalSelector.addGoal(2, new Helicockter.HelicockterLookGoal(this));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> {
            return p_28879_ instanceof Enemy && !(p_28879_ instanceof Helicockter) && !(p_28879_ instanceof CluckNorris);
        }));
        this.goalSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Animal.class, true));
        this.goalSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, FlyingMob.class, true));
        this.goalSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
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
        this.playSound(SoundEvents.ANVIL_HIT, 1.0F, 0.2F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(SoundEvents.GENERIC_EXPLODE, 1.0F, 0.2F);
        return null;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) { return 1.15F; }

    @Override
    protected void tickDeath()
    {
        ++this.deathTime;
        if (this.deathTime == 80 && !this.level.isClientSide())
        {
            this.level.broadcastEntityEvent(this, (byte)80);
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }


    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> event)
    {
        if (isAggressive())
        {
            event.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        if ((this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()))
        {
            event.getController().setAnimation(RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        }

        if(event.isMoving())
        {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() { return cache; }

    @Override
    protected void defineSynchedData() { super.defineSynchedData(); this.entityData.define(ATTACK, 1); }


    public void setAttackingState(int time) { this.entityData.set(ATTACK, time); }


    static class HelicockterMoveControl extends MoveControl {
        private final Helicockter helicockter;
        private int floatDuration;

        public HelicockterMoveControl(Helicockter helicockter) {
            super(helicockter);
            this.helicockter = helicockter;
        }

        public void tick()
        {
            if (this.operation == MoveControl.Operation.MOVE_TO)
            {
                if (this.floatDuration-- <= 0)
                {
                    this.floatDuration += this.helicockter.getRandom().nextInt(5) + 2;
                    Vec3 vec3 = new Vec3(this.wantedX - this.helicockter.getX(), this.wantedY -
                            this.helicockter.getY(), this.wantedZ - this.helicockter.getZ());
                    double d0 = vec3.length();
                    vec3 = vec3.normalize();
                    if (this.canReach(vec3, Mth.ceil(d0)))
                    {
                        this.helicockter.setDeltaMovement(this.helicockter.getDeltaMovement().add(vec3.scale(0.1D)));
                    }
                    else
                    {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }
            }
        }

        private boolean canReach(Vec3 p_32771_, int p_32772_)
        {
            AABB aabb = this.helicockter.getBoundingBox();

            for(int i = 1; i < p_32772_; ++i)
            {
                aabb = aabb.move(p_32771_);
                if (!this.helicockter.level.noCollision(this.helicockter, aabb))
                {
                    return false;
                }
            }
            return true;
        }
    }

    static class RandomFloatAroundGoal extends Goal
    {
        private final Helicockter helicockter;
        public RandomFloatAroundGoal(Helicockter helicockter)
        {
            this.helicockter = helicockter;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse()
        {
            MoveControl movecontrol = this.helicockter.getMoveControl();
            if (!movecontrol.hasWanted())
            {
                return true;
            }
            else
            {
                double d0 = movecontrol.getWantedX() - this.helicockter.getX();
                double d1 = movecontrol.getWantedY() - this.helicockter.getY();
                double d2 = movecontrol.getWantedZ() - this.helicockter.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        public boolean canContinueToUse()
        {
            return false;
        }

        public void start()
        {
            RandomSource randomsource = this.helicockter.getRandom();
            double d0 = this.helicockter.getX() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.helicockter.getY() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.helicockter.getZ() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.helicockter.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }
    }

    static class HelicockterLookGoal extends Goal
    {
        private final Helicockter helicockter;

        public HelicockterLookGoal(Helicockter helicockter)
        {
            this.helicockter = helicockter;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() { return true; }
        public boolean requiresUpdateEveryTick() { return true; }

        public void tick()
        {
            if (this.helicockter.getTarget() == null)
            {
                Vec3 vec3 = this.helicockter.getDeltaMovement();
                this.helicockter.setYRot(-((float)Mth.atan2(vec3.x, vec3.z)) * (180F / (float)Math.PI));
                this.helicockter.yBodyRot = this.helicockter.getYRot();
            }
            else
            {
                LivingEntity livingentity = this.helicockter.getTarget();
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.helicockter) < 4096.0D) {
                    double d1 = livingentity.getX() - this.helicockter.getX();
                    double d2 = livingentity.getZ() - this.helicockter.getZ();
                    this.helicockter.setYRot(-((float)Mth.atan2(d1, d2)) * (180F / (float)Math.PI));
                    this.helicockter.yBodyRot = this.helicockter.getYRot();
                }
            }
        }
    }

    public static class HelicockterRangedAttackGoal extends Goal
    {
        private final Helicockter mob;
        private final Helicockter rangedAttackMob;
        private int statecheck;
        @Nullable
        private LivingEntity target;
        private int attackTime = -1;
        private final double speedModifier;
        private int seeTime;
        private final double attackIntervalMin;
        private final double attackIntervalMax;
        private final float attackRadius;
        private final float attackRadiusSqr;
        private boolean strafingClockwise;
        private boolean strafingBackwards;
        private int strafingTime = -1;

        public HelicockterRangedAttackGoal(Helicockter helicockter, double speedIn, double dpsIn, float rangeIn, int state) {
            this(helicockter, speedIn, dpsIn, dpsIn, rangeIn, state);
        }

        public HelicockterRangedAttackGoal(Helicockter femaleHunter, double speedIn, double atckIntervalMin, double atckIntervalMax, float atckRadius, int state) {
            if (femaleHunter == null) {
                throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
            } else {
                this.rangedAttackMob =  femaleHunter;
                this.mob =  femaleHunter;
                this.speedModifier = speedIn;
                this.attackIntervalMin = atckIntervalMin;
                this.attackIntervalMax = atckIntervalMax;
                this.attackRadius = atckRadius;
                this.attackRadiusSqr = atckRadius * atckRadius;
                this.statecheck = state;

                this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
            }
        }


        public boolean canUse() {
            LivingEntity livingentity = this.mob.getTarget();
            if (livingentity != null && livingentity.isAlive()) {
                this.target = livingentity;
                return true;
            } else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            return this.canUse() || !this.mob.getNavigation().isDone();
        }

        public void stop() {
            this.target = null;
            this.seeTime = 0;
            this.attackTime = -1;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = this.mob.getTarget();
            double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
            boolean flag = this.mob.getSensing().hasLineOfSight(this.target);
            if (flag) {
                ++this.seeTime;
            } else {
                this.seeTime = 0;
            }

            if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 5) {
                this.mob.getNavigation().stop();
            } else {
                this.mob.getNavigation().moveTo(this.target, this.speedModifier);
            }

            if (livingentity != null) {
                boolean flag1 = this.seeTime > 0;
                if (flag != flag1) {
                    this.seeTime = 0;
                }

                if (flag) {
                    ++this.seeTime;
                } else {
                    --this.seeTime;
                }

                if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 20) {
                    this.mob.getNavigation().stop();
                    ++this.strafingTime;
                } else {
                    this.mob.getNavigation().moveTo(livingentity, this.speedModifier);
                    this.strafingTime = -1;
                }

                if (this.strafingTime >= 20) {
                    if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
                        this.strafingClockwise = !this.strafingClockwise;
                    }

                    if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
                        this.strafingBackwards = !this.strafingBackwards;
                    }

                    this.strafingTime = 0;
                }

                if (this.strafingTime > -1) {
                    if (d0 > (double)(this.attackRadiusSqr * 0.75F)) {
                        this.strafingBackwards = false;
                    } else if (d0 < (double)(this.attackRadiusSqr * 0.25F)) {
                        this.strafingBackwards = true;
                    }                                                           //speed shit
                    this.mob.getMoveControl().strafe(this.strafingBackwards ? 2.45F : 2.45F, this.strafingClockwise ? 2.45F : 2.45F);
                    this.mob.lookAt(livingentity, 30.0F, 30.0F);
                }
                this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
                if (--this.attackTime == 0) {
                    if (!flag) {
                        return;
                    }
                    if (this.mob.isUsingItem()) {
                        if (!flag && this.seeTime < -60) {
                            this.mob.stopUsingItem();
                        } else if (flag) {
                            int i = this.mob.getTicksUsingItem();
                            if (i >= 19) {
                                this.mob.setAttackingState(statecheck);
                            }
                            if (i >= 20) {
                                this.mob.stopUsingItem();
                            }
                        }
                    }
                    float f = (float)Math.sqrt(d0) / this.attackRadius;
                    float f1 = Mth.clamp(f, 0.1F, 1.0F);
                    this.rangedAttackMob.performRangedAttack(this.target, f1);
                    this.attackTime = Mth.floor(f * (float)(this.attackIntervalMax - this.attackIntervalMin) + (float)this.attackIntervalMin);
                } else if (this.attackTime < 0)
                {
                    this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0)
                            / (double)this.attackRadius, (double)this.attackIntervalMin, (double)this.attackIntervalMax));
                }

            }
        }
    }



    public void performRangedAttack(LivingEntity livingEntity, float p_32142_)
    {
        FiftyCal arrow = new FiftyCal(this.level, this);
        double d0 = livingEntity.getX() - this.getX();
        double d1 = livingEntity.getY() - arrow.getY();
        double d2 = livingEntity.getZ() - this.getZ();
        double d3 = (double) Mth.sqrt((float) (d0 * d0 + d2 * d2));
        arrow.shoot(d0, d1 + d3 * (double) 0, d2, 4.6F, 7.0F);
        this.playSound(SoundEventsROTM.FIFTY_CAL.get(), 2.0F, 10.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(arrow);
    }




    public boolean canFireProjectileWeapon(ProjectileWeaponItem projectileWeaponItem) { return projectileWeaponItem == Items.BOW; }


    @Override
    public boolean isBaby() { return false;}
}
