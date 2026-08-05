package androidx.compose.foundation.gestures.snapping;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.ui.MotionDurationScale;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;

/* JADX INFO: compiled from: SnapFlingBehavior.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001BL\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\rø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0002J=\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\"0!*\u00020#2\u0006\u0010$\u001a\u00020\u00062\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020'0&H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(JL\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\"0!*\u00020#2\u0006\u0010$\u001a\u00020\u00062!\u0010*\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020'0&H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(J\u001d\u0010.\u001a\u00020\u0006*\u00020#2\u0006\u0010$\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010/J1\u0010.\u001a\u00020\u0006*\u00020#2\u0006\u0010$\u001a\u00020\u00062\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020'0&H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010(JT\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\"0!*\u00020#2\u0006\u00102\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062!\u0010*\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020'0&H\u0082@ø\u0001\u0000¢\u0006\u0002\u00104J=\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\"0!*\u00020#2\u0006\u0010\u001f\u001a\u00020\u00062\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020'0&H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0019\u0010\f\u001a\u00020\rX\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0015R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00066"}, d2 = {"Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "Landroidx/compose/foundation/gestures/FlingBehavior;", "snapLayoutInfoProvider", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "lowVelocityAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "highVelocityAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "snapAnimationSpec", "density", "Landroidx/compose/ui/unit/Density;", "shortSnapVelocityThreshold", "Landroidx/compose/ui/unit/Dp;", "(Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/ui/unit/Density;FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "motionScaleDuration", "Landroidx/compose/ui/MotionDurationScale;", "getMotionScaleDuration$foundation_release", "()Landroidx/compose/ui/MotionDurationScale;", "setMotionScaleDuration$foundation_release", "(Landroidx/compose/ui/MotionDurationScale;)V", "F", "velocityThreshold", "equals", "", "other", "", "hashCode", "", "isDecayApproachPossible", "offset", "velocity", "fling", "Landroidx/compose/foundation/gestures/snapping/AnimationResult;", "Landroidx/compose/animation/core/AnimationVector1D;", "Landroidx/compose/foundation/gestures/ScrollScope;", "initialVelocity", "onRemainingScrollOffsetUpdate", "Lkotlin/Function1;", "", "(Landroidx/compose/foundation/gestures/ScrollScope;FLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "longSnap", "onAnimationStep", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "remainingScrollOffset", "performFling", "(Landroidx/compose/foundation/gestures/ScrollScope;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onSettlingDistanceUpdated", "runApproach", "initialTargetOffset", "delta", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shortSnap", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SnapFlingBehavior implements FlingBehavior {
    public static final int $stable = 0;
    private final Density density;
    private final DecayAnimationSpec<Float> highVelocityAnimationSpec;
    private final AnimationSpec<Float> lowVelocityAnimationSpec;
    private MotionDurationScale motionScaleDuration;
    private final float shortSnapVelocityThreshold;
    private final AnimationSpec<Float> snapAnimationSpec;
    private final SnapLayoutInfoProvider snapLayoutInfoProvider;
    private final float velocityThreshold;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$1, reason: invalid class name */
    /* JADX INFO: compiled from: SnapFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehavior", f = "SnapFlingBehavior.kt", i = {0}, l = {131}, m = "fling", n = {"onRemainingScrollOffsetUpdate"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.fling(null, 0.0f, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$longSnap$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SnapFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehavior", f = "SnapFlingBehavior.kt", i = {0, 0, 0, 0}, l = {179, 191}, m = "longSnap", n = {"this", "$this$longSnap", "onAnimationStep", "remainingScrollOffset"}, s = {"L$0", "L$1", "L$2", "L$3"})
    static final class C02031 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C02031(Continuation<? super C02031> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.longSnap(null, 0.0f, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$performFling$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SnapFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehavior", f = "SnapFlingBehavior.kt", i = {}, l = {117}, m = "performFling", n = {}, s = {})
    static final class C02043 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02043(Continuation<? super C02043> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.performFling(null, 0.0f, null, this);
        }
    }

    public /* synthetic */ SnapFlingBehavior(SnapLayoutInfoProvider snapLayoutInfoProvider, AnimationSpec animationSpec, DecayAnimationSpec decayAnimationSpec, AnimationSpec animationSpec2, Density density, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(snapLayoutInfoProvider, animationSpec, decayAnimationSpec, animationSpec2, density, f);
    }

    private SnapFlingBehavior(SnapLayoutInfoProvider snapLayoutInfoProvider, AnimationSpec<Float> lowVelocityAnimationSpec, DecayAnimationSpec<Float> highVelocityAnimationSpec, AnimationSpec<Float> snapAnimationSpec, Density density, float shortSnapVelocityThreshold) {
        Intrinsics.checkNotNullParameter(snapLayoutInfoProvider, "snapLayoutInfoProvider");
        Intrinsics.checkNotNullParameter(lowVelocityAnimationSpec, "lowVelocityAnimationSpec");
        Intrinsics.checkNotNullParameter(highVelocityAnimationSpec, "highVelocityAnimationSpec");
        Intrinsics.checkNotNullParameter(snapAnimationSpec, "snapAnimationSpec");
        Intrinsics.checkNotNullParameter(density, "density");
        this.snapLayoutInfoProvider = snapLayoutInfoProvider;
        this.lowVelocityAnimationSpec = lowVelocityAnimationSpec;
        this.highVelocityAnimationSpec = highVelocityAnimationSpec;
        this.snapAnimationSpec = snapAnimationSpec;
        this.density = density;
        this.shortSnapVelocityThreshold = shortSnapVelocityThreshold;
        this.velocityThreshold = density.mo327toPx0680j_4(shortSnapVelocityThreshold);
        this.motionScaleDuration = ScrollableKt.getDefaultScrollMotionDurationScale();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ SnapFlingBehavior(SnapLayoutInfoProvider snapLayoutInfoProvider, AnimationSpec animationSpec, DecayAnimationSpec decayAnimationSpec, AnimationSpec animationSpec2, Density density, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        float minFlingVelocityDp;
        if ((i & 32) == 0) {
            minFlingVelocityDp = f;
        } else {
            minFlingVelocityDp = SnapFlingBehaviorKt.getMinFlingVelocityDp();
        }
        this(snapLayoutInfoProvider, animationSpec, decayAnimationSpec, animationSpec2, density, minFlingVelocityDp, null);
    }

    /* JADX INFO: renamed from: getMotionScaleDuration$foundation_release, reason: from getter */
    public final MotionDurationScale getMotionScaleDuration() {
        return this.motionScaleDuration;
    }

    public final void setMotionScaleDuration$foundation_release(MotionDurationScale motionDurationScale) {
        Intrinsics.checkNotNullParameter(motionDurationScale, "<set-?>");
        this.motionScaleDuration = motionDurationScale;
    }

    @Override // androidx.compose.foundation.gestures.FlingBehavior
    public Object performFling(ScrollScope $this$performFling, float initialVelocity, Continuation<? super Float> continuation) {
        return performFling($this$performFling, initialVelocity, new Function1<Float, Unit>() { // from class: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.performFling.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                invoke(f.floatValue());
                return Unit.INSTANCE;
            }

            public final void invoke(float it) {
            }
        }, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object performFling(ScrollScope $this$performFling, float initialVelocity, Function1<? super Float, Unit> function1, Continuation<? super Float> continuation) throws Throwable {
        C02043 c02043;
        Object objFling;
        if (continuation instanceof C02043) {
            c02043 = (C02043) continuation;
            if ((c02043.label & Integer.MIN_VALUE) != 0) {
                c02043.label -= Integer.MIN_VALUE;
            } else {
                c02043 = new C02043(continuation);
            }
        } else {
            c02043 = new C02043(continuation);
        }
        C02043 c02044 = c02043;
        Object $result = c02044.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02044.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                c02044.label = 1;
                objFling = fling($this$performFling, initialVelocity, function1, c02044);
                if (objFling == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objFling = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AnimationResult animationResult = (AnimationResult) objFling;
        float remainingOffset = ((Number) animationResult.component1()).floatValue();
        AnimationState remainingState = animationResult.component2();
        return Boxing.boxFloat(remainingOffset == 0.0f ? 0.0f : ((Number) remainingState.getVelocity()).floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object fling(ScrollScope $this$fling, float initialVelocity, Function1<? super Float, Unit> function1, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Function1<? super Float, Unit> function2;
        Object objWithContext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object $result = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                function2 = function1;
                MotionDurationScale motionDurationScale = this.motionScaleDuration;
                SnapFlingBehavior$fling$result$1 snapFlingBehavior$fling$result$1 = new SnapFlingBehavior$fling$result$1(initialVelocity, this, $this$fling, function2, null);
                anonymousClass2.L$0 = function2;
                anonymousClass2.label = 1;
                objWithContext = BuildersKt.withContext(motionDurationScale, snapFlingBehavior$fling$result$1, anonymousClass2);
                if (objWithContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                function2 = (Function1) anonymousClass2.L$0;
                ResultKt.throwOnFailure($result);
                objWithContext = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AnimationResult result = (AnimationResult) objWithContext;
        function2.invoke(Boxing.boxFloat(0.0f));
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object shortSnap(ScrollScope $this$shortSnap, float velocity, final Function1<? super Float, Unit> function1, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
        SnapLayoutInfoProvider $this$shortSnap_u24lambda_u243 = this.snapLayoutInfoProvider;
        float closestOffset = $this$shortSnap_u24lambda_u243.calculateSnappingOffset(this.density, 0.0f);
        final Ref.FloatRef remainingScrollOffset = new Ref.FloatRef();
        remainingScrollOffset.element = closestOffset;
        AnimationState animationState = AnimationStateKt.AnimationState$default(0.0f, velocity, 0L, 0L, false, 28, null);
        return SnapFlingBehaviorKt.animateSnap($this$shortSnap, closestOffset, closestOffset, animationState, this.snapAnimationSpec, new Function1<Float, Unit>() { // from class: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.shortSnap.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                invoke(f.floatValue());
                return Unit.INSTANCE;
            }

            public final void invoke(float delta) {
                remainingScrollOffset.element -= delta;
                function1.invoke(Float.valueOf(remainingScrollOffset.element));
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    public final Object longSnap(ScrollScope scrollScope, float initialVelocity, Function1<? super Float, Unit> function1, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
        C02031 c02031;
        ScrollScope $this$longSnap;
        final Function1<? super Float, Unit> function2;
        final Ref.FloatRef remainingScrollOffset;
        Object objRunApproach;
        SnapFlingBehavior snapFlingBehavior;
        if (continuation instanceof C02031) {
            C02031 c02032 = (C02031) continuation;
            if ((c02032.label & Integer.MIN_VALUE) != 0) {
                c02032.label -= Integer.MIN_VALUE;
                c02031 = c02032;
            } else {
                c02031 = new C02031(continuation);
            }
        } else {
            c02031 = new C02031(continuation);
        }
        Object $result = c02031.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02031.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                $this$longSnap = scrollScope;
                function2 = function1;
                SnapLayoutInfoProvider $this$longSnap_u24lambda_u245 = this.snapLayoutInfoProvider;
                float it = $this$longSnap_u24lambda_u245.calculateApproachOffset(this.density, initialVelocity);
                float initialOffset = Math.abs(it) * Math.signum(initialVelocity);
                remainingScrollOffset = new Ref.FloatRef();
                remainingScrollOffset.element = initialOffset;
                function2.invoke(Boxing.boxFloat(remainingScrollOffset.element));
                Function1<Float, Unit> function3 = new Function1<Float, Unit>() { // from class: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.longSnap.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                        invoke(f.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(float delta) {
                        remainingScrollOffset.element -= delta;
                        function2.invoke(Float.valueOf(remainingScrollOffset.element));
                    }
                };
                c02031.L$0 = this;
                c02031.L$1 = $this$longSnap;
                c02031.L$2 = function2;
                c02031.L$3 = remainingScrollOffset;
                c02031.label = 1;
                objRunApproach = runApproach($this$longSnap, initialOffset, initialVelocity, function3, c02031);
                if (objRunApproach == coroutine_suspended) {
                    return coroutine_suspended;
                }
                snapFlingBehavior = this;
                break;
                break;
            case 1:
                Ref.FloatRef remainingScrollOffset2 = (Ref.FloatRef) c02031.L$3;
                Function1<? super Float, Unit> function4 = (Function1) c02031.L$2;
                ScrollScope $this$longSnap2 = (ScrollScope) c02031.L$1;
                snapFlingBehavior = (SnapFlingBehavior) c02031.L$0;
                ResultKt.throwOnFailure($result);
                remainingScrollOffset = remainingScrollOffset2;
                function2 = function4;
                $this$longSnap = $this$longSnap2;
                objRunApproach = $result;
                break;
            case 2:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AnimationResult animationResult = (AnimationResult) objRunApproach;
        float remainingOffset = ((Number) animationResult.component1()).floatValue();
        AnimationState animationState = animationResult.component2();
        remainingScrollOffset.element = remainingOffset;
        AnimationState animationStateCopy$default = AnimationStateKt.copy$default(animationState, 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
        AnimationSpec<Float> animationSpec = snapFlingBehavior.snapAnimationSpec;
        Function1<Float, Unit> function5 = new Function1<Float, Unit>() { // from class: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior.longSnap.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                invoke(f.floatValue());
                return Unit.INSTANCE;
            }

            public final void invoke(float delta) {
                remainingScrollOffset.element -= delta;
                function2.invoke(Float.valueOf(remainingScrollOffset.element));
            }
        };
        c02031.L$0 = null;
        c02031.L$1 = null;
        c02031.L$2 = null;
        c02031.L$3 = null;
        c02031.label = 2;
        Object objAnimateSnap = SnapFlingBehaviorKt.animateSnap($this$longSnap, remainingOffset, remainingOffset, animationStateCopy$default, animationSpec, function5, c02031);
        if (objAnimateSnap == coroutine_suspended) {
            return coroutine_suspended;
        }
        return objAnimateSnap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object runApproach(ScrollScope $this$runApproach, float initialTargetOffset, float initialVelocity, Function1<? super Float, Unit> function1, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
        ApproachAnimation animation;
        if (isDecayApproachPossible(initialTargetOffset, initialVelocity)) {
            animation = new HighVelocityApproachAnimation(this.highVelocityAnimationSpec);
        } else {
            animation = new LowVelocityApproachAnimation(this.lowVelocityAnimationSpec, this.snapLayoutInfoProvider, this.density);
        }
        return SnapFlingBehaviorKt.approach($this$runApproach, initialTargetOffset, initialVelocity, animation, this.snapLayoutInfoProvider, this.density, function1, continuation);
    }

    private final boolean isDecayApproachPossible(float offset, float velocity) {
        float decayOffset = DecayAnimationSpecKt.calculateTargetValue(this.highVelocityAnimationSpec, 0.0f, velocity);
        SnapLayoutInfoProvider $this$isDecayApproachPossible_u24lambda_u2410 = this.snapLayoutInfoProvider;
        float snapStepSize = $this$isDecayApproachPossible_u24lambda_u2410.calculateSnapStepSize(this.density);
        return Math.abs(decayOffset) >= Math.abs(offset) + snapStepSize;
    }

    public boolean equals(Object other) {
        return (other instanceof SnapFlingBehavior) && Intrinsics.areEqual(((SnapFlingBehavior) other).snapAnimationSpec, this.snapAnimationSpec) && Intrinsics.areEqual(((SnapFlingBehavior) other).highVelocityAnimationSpec, this.highVelocityAnimationSpec) && Intrinsics.areEqual(((SnapFlingBehavior) other).lowVelocityAnimationSpec, this.lowVelocityAnimationSpec) && Intrinsics.areEqual(((SnapFlingBehavior) other).snapLayoutInfoProvider, this.snapLayoutInfoProvider) && Intrinsics.areEqual(((SnapFlingBehavior) other).density, this.density) && Dp.m5279equalsimpl0(((SnapFlingBehavior) other).shortSnapVelocityThreshold, this.shortSnapVelocityThreshold);
    }

    public int hashCode() {
        int it = (0 * 31) + this.snapAnimationSpec.hashCode();
        return (((((((((it * 31) + this.highVelocityAnimationSpec.hashCode()) * 31) + this.lowVelocityAnimationSpec.hashCode()) * 31) + this.snapLayoutInfoProvider.hashCode()) * 31) + this.density.hashCode()) * 31) + Dp.m5280hashCodeimpl(this.shortSnapVelocityThreshold);
    }
}
