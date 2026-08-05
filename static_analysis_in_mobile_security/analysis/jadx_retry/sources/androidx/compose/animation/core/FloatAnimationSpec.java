package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FloatAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H&J \u0010\b\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J(\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H&J(\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H&J,\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\r\"\b\b\u0000\u0010\u000e*\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u000e0\u0011H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0003"}, d2 = {"Landroidx/compose/animation/core/FloatAnimationSpec;", "Landroidx/compose/animation/core/AnimationSpec;", "", "getDurationNanos", "", "initialValue", "targetValue", "initialVelocity", "getEndVelocity", "getValueFromNanos", "playTimeNanos", "getVelocityFromNanos", "vectorize", "Landroidx/compose/animation/core/VectorizedFloatAnimationSpec;", "V", "Landroidx/compose/animation/core/AnimationVector;", "converter", "Landroidx/compose/animation/core/TwoWayConverter;", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface FloatAnimationSpec extends AnimationSpec<Float> {
    long getDurationNanos(float initialValue, float targetValue, float initialVelocity);

    float getEndVelocity(float initialValue, float targetValue, float initialVelocity);

    float getValueFromNanos(long playTimeNanos, float initialValue, float targetValue, float initialVelocity);

    float getVelocityFromNanos(long playTimeNanos, float initialValue, float targetValue, float initialVelocity);

    @Override // androidx.compose.animation.core.AnimationSpec
    <V extends AnimationVector> VectorizedFloatAnimationSpec<V> vectorize(TwoWayConverter<Float, V> converter);

    /* JADX INFO: renamed from: androidx.compose.animation.core.FloatAnimationSpec$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: FloatAnimationSpec.kt */
    public final /* synthetic */ class CC {
        public static float $default$getEndVelocity(FloatAnimationSpec _this, float initialValue, float targetValue, float initialVelocity) {
            return _this.getVelocityFromNanos(_this.getDurationNanos(initialValue, targetValue, initialVelocity), initialValue, targetValue, initialVelocity);
        }

        /* JADX INFO: renamed from: $default$vectorize, reason: collision with other method in class */
        public static VectorizedFloatAnimationSpec m94$default$vectorize(FloatAnimationSpec _this, TwoWayConverter converter) {
            Intrinsics.checkNotNullParameter(converter, "converter");
            return new VectorizedFloatAnimationSpec(_this);
        }
    }

    /* JADX INFO: compiled from: FloatAnimationSpec.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static float getEndVelocity(FloatAnimationSpec $this, float initialValue, float targetValue, float initialVelocity) {
            return CC.$default$getEndVelocity($this, initialValue, targetValue, initialVelocity);
        }

        @Deprecated
        public static <V extends AnimationVector> VectorizedFloatAnimationSpec<V> vectorize(FloatAnimationSpec $this, TwoWayConverter<Float, V> converter) {
            Intrinsics.checkNotNullParameter(converter, "converter");
            return CC.m94$default$vectorize($this, (TwoWayConverter) converter);
        }
    }
}
