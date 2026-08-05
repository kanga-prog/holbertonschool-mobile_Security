package androidx.compose.animation.core;

import kotlin.Metadata;

/* JADX INFO: compiled from: SpringSimulation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0002J0\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001eH\u0000ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u001f\u0010 R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\n\"\u0004\b\r\u0010\u0004R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\n\"\u0004\b\u0015\u0010\u0004\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006!"}, d2 = {"Landroidx/compose/animation/core/SpringSimulation;", "", "finalPosition", "", "(F)V", "dampedFreq", "", "value", "dampingRatio", "getDampingRatio", "()F", "setDampingRatio", "getFinalPosition", "setFinalPosition", "gammaMinus", "gammaPlus", "initialized", "", "naturalFreq", "stiffness", "getStiffness", "setStiffness", "getAcceleration", "lastDisplacement", "lastVelocity", "init", "", "updateValues", "Landroidx/compose/animation/core/Motion;", "timeElapsed", "", "updateValues-IJZedt4$animation_core_release", "(FFJ)J", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SpringSimulation {
    private double dampedFreq;
    private float finalPosition;
    private double gammaMinus;
    private double gammaPlus;
    private boolean initialized;
    private double naturalFreq = Math.sqrt(50.0d);
    private float dampingRatio = 1.0f;

    public SpringSimulation(float finalPosition) {
        this.finalPosition = finalPosition;
    }

    public final float getFinalPosition() {
        return this.finalPosition;
    }

    public final void setFinalPosition(float f) {
        this.finalPosition = f;
    }

    public final void setStiffness(float value) {
        if (getStiffness() <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.naturalFreq = Math.sqrt(value);
        this.initialized = false;
    }

    public final float getStiffness() {
        double d = this.naturalFreq;
        return (float) (d * d);
    }

    public final float getDampingRatio() {
        return this.dampingRatio;
    }

    public final void setDampingRatio(float value) {
        if (value < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        this.dampingRatio = value;
        this.initialized = false;
    }

    public final float getAcceleration(float lastDisplacement, float lastVelocity) {
        float adjustedDisplacement = lastDisplacement - this.finalPosition;
        double d = this.naturalFreq;
        double k = d * d;
        double c = d * 2.0d * ((double) this.dampingRatio);
        return (float) (((-k) * ((double) adjustedDisplacement)) - (((double) lastVelocity) * c));
    }

    private final void init() {
        if (this.initialized) {
            return;
        }
        if (this.finalPosition == SpringSimulationKt.getUNSET()) {
            throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
        }
        float f = this.dampingRatio;
        double dampingRatioSquared = ((double) f) * ((double) f);
        if (f > 1.0f) {
            double d = this.naturalFreq;
            double d2 = 1;
            this.gammaPlus = (((double) (-f)) * d) + (d * Math.sqrt(dampingRatioSquared - d2));
            double d3 = -this.dampingRatio;
            double d4 = this.naturalFreq;
            this.gammaMinus = (d3 * d4) - (d4 * Math.sqrt(dampingRatioSquared - d2));
        } else if (f >= 0.0f && f < 1.0f) {
            this.dampedFreq = this.naturalFreq * Math.sqrt(((double) 1) - dampingRatioSquared);
        }
        this.initialized = true;
    }

    /* JADX INFO: renamed from: updateValues-IJZedt4$animation_core_release, reason: not valid java name */
    public final long m108updateValuesIJZedt4$animation_core_release(float lastDisplacement, float lastVelocity, long timeElapsed) {
        double coeffA;
        double displacement;
        init();
        float adjustedDisplacement = lastDisplacement - this.finalPosition;
        double deltaT = timeElapsed / 1000.0d;
        float f = this.dampingRatio;
        if (f > 1.0f) {
            double d = this.gammaMinus;
            double d2 = this.gammaPlus;
            double coeffA2 = ((double) adjustedDisplacement) - (((((double) adjustedDisplacement) * d) - ((double) lastVelocity)) / (d - d2));
            double currentVelocity = lastVelocity;
            double coeffB = ((((double) adjustedDisplacement) * d) - currentVelocity) / (d - d2);
            displacement = (Math.exp(d * deltaT) * coeffA2) + (Math.exp(this.gammaPlus * deltaT) * coeffB);
            double d3 = this.gammaMinus;
            double dExp = coeffA2 * d3 * Math.exp(d3 * deltaT);
            double d4 = this.gammaPlus;
            coeffA = dExp + (coeffB * d4 * Math.exp(d4 * deltaT));
        } else {
            if (f == 1.0f) {
                double d5 = this.naturalFreq;
                double coeffB2 = ((double) lastVelocity) + (((double) adjustedDisplacement) * d5);
                displacement = Math.exp((-d5) * deltaT) * (((double) adjustedDisplacement) + (coeffB2 * deltaT));
                double dExp2 = (((double) adjustedDisplacement) + (coeffB2 * deltaT)) * Math.exp((-this.naturalFreq) * deltaT);
                double d6 = this.naturalFreq;
                coeffA = (dExp2 * (-d6)) + (Math.exp((-d6) * deltaT) * coeffB2);
            } else {
                double d7 = ((double) 1) / this.dampedFreq;
                double d8 = this.naturalFreq;
                double sinCoeff = d7 * ((((double) f) * d8 * ((double) adjustedDisplacement)) + ((double) lastVelocity));
                double displacement2 = Math.exp(((double) (-f)) * d8 * deltaT) * ((((double) adjustedDisplacement) * Math.cos(this.dampedFreq * deltaT)) + (Math.sin(this.dampedFreq * deltaT) * sinCoeff));
                double d9 = this.naturalFreq;
                float f2 = this.dampingRatio;
                double d10 = (-d9) * displacement2 * ((double) f2);
                double dExp3 = Math.exp(((double) (-f2)) * d9 * deltaT);
                double d11 = this.dampedFreq;
                double dSin = (-d11) * ((double) adjustedDisplacement) * Math.sin(d11 * deltaT);
                double d12 = this.dampedFreq;
                coeffA = d10 + (dExp3 * (dSin + (d12 * sinCoeff * Math.cos(d12 * deltaT))));
                displacement = displacement2;
            }
        }
        float newValue = (float) (((double) this.finalPosition) + displacement);
        float newVelocity = (float) coeffA;
        return SpringSimulationKt.Motion(newValue, newVelocity);
    }
}
