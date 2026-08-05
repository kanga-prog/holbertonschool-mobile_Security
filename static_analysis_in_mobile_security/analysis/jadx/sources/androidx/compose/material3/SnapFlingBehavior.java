package androidx.compose.material3;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.compose.foundation.lazy.LazyListLayoutInfo;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.ui.MotionDurationScale;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;

/* JADX INFO: compiled from: SnapFlingBehavior.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0081\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\f*\u0001\u0014\b\u0001\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0018\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u0019H\u0002J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0096\u0002J\u0018\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010*\u001a\u00020\u001dH\u0016JK\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020-0,*\u00020.2\u0006\u0010/\u001a\u00020\u00062\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020-012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@ø\u0001\u0000¢\u0006\u0002\u00102JS\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020-0,*\u00020.2\u0006\u0010/\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u00062\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020-012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u00105J\u0014\u00106\u001a\u00020\u0006*\u00020\u00062\u0006\u00107\u001a\u00020\u0006H\u0002J(\u00108\u001a\u0002H9\"\u000e\b\u0000\u00109*\b\u0012\u0004\u0012\u0002H90:*\b\u0012\u0004\u0012\u0002H90;H\u0082\u0002¢\u0006\u0002\u0010<J(\u0010=\u001a\u0002H9\"\u000e\b\u0000\u00109*\b\u0012\u0004\u0012\u0002H90:*\b\u0012\u0004\u0012\u0002H90;H\u0082\u0002¢\u0006\u0002\u0010<J)\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020-0,*\u00020.2\u0006\u0010?\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010@J)\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020-0,*\u00020.2\u0006\u0010?\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010@J\u001d\u0010B\u001a\u00020\u0006*\u00020.2\u0006\u0010?\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010@J1\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020-0,*\u00020.2\u0006\u0010D\u001a\u00020\u00062\u0006\u0010?\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010EJ)\u0010F\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020-0,*\u00020.2\u0006\u0010)\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010@R\u000e\u0010\f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\u00020\u000eX\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u000fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001c\u001a\u00020\u001d*\u00020\u001e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006G"}, d2 = {"Landroidx/compose/material3/SnapFlingBehavior;", "Landroidx/compose/foundation/gestures/FlingBehavior;", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "density", "Landroidx/compose/ui/unit/Density;", "(Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/ui/unit/Density;)V", "DefaultScrollMotionDurationScaleFactor", "MinFlingVelocityDp", "Landroidx/compose/ui/unit/Dp;", "F", "itemSize", "getItemSize", "()F", "motionScaleDuration", "androidx/compose/material3/SnapFlingBehavior$motionScaleDuration$1", "Landroidx/compose/material3/SnapFlingBehavior$motionScaleDuration$1;", "velocityThreshold", "visibleItemsInfo", "", "Landroidx/compose/foundation/lazy/LazyListItemInfo;", "getVisibleItemsInfo", "()Ljava/util/List;", "singleAxisViewportSize", "", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "getSingleAxisViewportSize", "(Landroidx/compose/foundation/lazy/LazyListLayoutInfo;)I", "calculateDistanceToDesiredSnapPosition", "layoutInfo", "item", "equals", "", "other", "", "findClosestOffset", "velocity", "hashCode", "animateDecay", "Landroidx/compose/material3/AnimationResult;", "Landroidx/compose/animation/core/AnimationVector1D;", "Landroidx/compose/foundation/gestures/ScrollScope;", "targetOffset", "animationState", "Landroidx/compose/animation/core/AnimationState;", "(Landroidx/compose/foundation/gestures/ScrollScope;FLandroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/DecayAnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateSnap", "cancelOffset", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLandroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coerceToTarget", "target", "component1", "T", "", "Lkotlin/ranges/ClosedFloatingPointRange;", "(Lkotlin/ranges/ClosedFloatingPointRange;)Ljava/lang/Comparable;", "component2", "fling", "initialVelocity", "(Landroidx/compose/foundation/gestures/ScrollScope;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "longSnap", "performFling", "runApproach", "initialTargetOffset", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shortSnap", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SnapFlingBehavior implements FlingBehavior {
    private final float DefaultScrollMotionDurationScaleFactor;
    private final float MinFlingVelocityDp;
    private final DecayAnimationSpec<Float> decayAnimationSpec;
    private final Density density;
    private final LazyListState lazyListState;
    private SnapFlingBehavior$motionScaleDuration$1 motionScaleDuration;
    private final AnimationSpec<Float> snapAnimationSpec;
    private final float velocityThreshold;

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$animateDecay$1, reason: invalid class name */
    /* JADX INFO: compiled from: SnapFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.SnapFlingBehavior", f = "SnapFlingBehavior.kt", i = {0, 0, 0}, l = {261}, m = "animateDecay", n = {"animationState", "previousValue", "targetOffset"}, s = {"L$0", "L$1", "F$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.animateDecay(null, 0.0f, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$animateSnap$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SnapFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.SnapFlingBehavior", f = "SnapFlingBehavior.kt", i = {0, 0, 0, 0, 0}, l = {299}, m = "animateSnap", n = {"this", "animationState", "consumedUpToNow", "targetOffset", "initialVelocity"}, s = {"L$0", "L$1", "L$2", "F$0", "F$1"})
    static final class C04231 extends ContinuationImpl {
        float F$0;
        float F$1;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C04231(Continuation<? super C04231> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.animateSnap(null, 0.0f, 0.0f, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$fling$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SnapFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.SnapFlingBehavior", f = "SnapFlingBehavior.kt", i = {}, l = {95}, m = "fling", n = {}, s = {})
    static final class C04251 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C04251(Continuation<? super C04251> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.fling(null, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$longSnap$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SnapFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.SnapFlingBehavior", f = "SnapFlingBehavior.kt", i = {0, 0}, l = {135, 140}, m = "longSnap", n = {"this", "$this$longSnap"}, s = {"L$0", "L$1"})
    static final class C04261 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C04261(Continuation<? super C04261> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.longSnap(null, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$performFling$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SnapFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.SnapFlingBehavior", f = "SnapFlingBehavior.kt", i = {}, l = {83}, m = "performFling", n = {}, s = {})
    static final class C04271 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C04271(Continuation<? super C04271> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.performFling(null, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$runApproach$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SnapFlingBehavior.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.SnapFlingBehavior", f = "SnapFlingBehavior.kt", i = {0}, l = {154}, m = "runApproach", n = {"this"}, s = {"L$0"})
    static final class C04281 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C04281(Continuation<? super C04281> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.runApproach(null, 0.0f, 0.0f, this);
        }
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.compose.material3.SnapFlingBehavior$motionScaleDuration$1] */
    public SnapFlingBehavior(LazyListState lazyListState, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> snapAnimationSpec, Density density) {
        Intrinsics.checkNotNullParameter(lazyListState, "lazyListState");
        Intrinsics.checkNotNullParameter(decayAnimationSpec, "decayAnimationSpec");
        Intrinsics.checkNotNullParameter(snapAnimationSpec, "snapAnimationSpec");
        Intrinsics.checkNotNullParameter(density, "density");
        this.lazyListState = lazyListState;
        this.decayAnimationSpec = decayAnimationSpec;
        this.snapAnimationSpec = snapAnimationSpec;
        this.density = density;
        this.velocityThreshold = density.mo327toPx0680j_4(this.MinFlingVelocityDp);
        this.motionScaleDuration = new MotionDurationScale() { // from class: androidx.compose.material3.SnapFlingBehavior$motionScaleDuration$1
            @Override // androidx.compose.ui.MotionDurationScale, kotlin.coroutines.CoroutineContext.Element
            public /* synthetic */ CoroutineContext.Key getKey() {
                return MotionDurationScale.INSTANCE;
            }

            @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
            public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
                return (R) MotionDurationScale.DefaultImpls.fold(this, r, function2);
            }

            @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
            public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
                return (E) MotionDurationScale.DefaultImpls.get(this, key);
            }

            @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
            public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
                return MotionDurationScale.DefaultImpls.minusKey(this, key);
            }

            @Override // kotlin.coroutines.CoroutineContext
            public CoroutineContext plus(CoroutineContext context) {
                return MotionDurationScale.DefaultImpls.plus(this, context);
            }

            @Override // androidx.compose.ui.MotionDurationScale
            public float getScaleFactor() {
                return this.this$0.DefaultScrollMotionDurationScaleFactor;
            }
        };
        this.DefaultScrollMotionDurationScaleFactor = 1.0f;
        this.MinFlingVelocityDp = Dp.m5274constructorimpl(400);
    }

    private final List<LazyListItemInfo> getVisibleItemsInfo() {
        return this.lazyListState.getLayoutInfo().getVisibleItemsInfo();
    }

    private final float getItemSize() {
        if (!getVisibleItemsInfo().isEmpty()) {
            List<LazyListItemInfo> visibleItemsInfo = getVisibleItemsInfo();
            int sum$iv = 0;
            int size = visibleItemsInfo.size();
            for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                Object item$iv$iv = visibleItemsInfo.get(index$iv$iv);
                LazyListItemInfo it = (LazyListItemInfo) item$iv$iv;
                sum$iv += it.getSize();
            }
            return sum$iv / getVisibleItemsInfo().size();
        }
        return 0.0f;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.compose.foundation.gestures.FlingBehavior
    public Object performFling(ScrollScope $this$performFling, float initialVelocity, Continuation<? super Float> continuation) throws Throwable {
        C04271 c04271;
        Object objFling;
        if (continuation instanceof C04271) {
            c04271 = (C04271) continuation;
            if ((c04271.label & Integer.MIN_VALUE) != 0) {
                c04271.label -= Integer.MIN_VALUE;
            } else {
                c04271 = new C04271(continuation);
            }
        } else {
            c04271 = new C04271(continuation);
        }
        C04271 c04272 = c04271;
        Object $result = c04272.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c04272.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                c04272.label = 1;
                objFling = fling($this$performFling, initialVelocity, c04272);
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
    public final Object fling(ScrollScope $this$fling, float initialVelocity, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) throws Throwable {
        C04251 c04251;
        Object objWithContext;
        if (continuation instanceof C04251) {
            c04251 = (C04251) continuation;
            if ((c04251.label & Integer.MIN_VALUE) != 0) {
                c04251.label -= Integer.MIN_VALUE;
            } else {
                c04251 = new C04251(continuation);
            }
        } else {
            c04251 = new C04251(continuation);
        }
        C04251 c04252 = c04251;
        Object $result = c04252.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c04252.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                SnapFlingBehavior$motionScaleDuration$1 snapFlingBehavior$motionScaleDuration$1 = this.motionScaleDuration;
                SnapFlingBehavior$fling$result$1 snapFlingBehavior$fling$result$1 = new SnapFlingBehavior$fling$result$1(initialVelocity, this, $this$fling, null);
                c04252.label = 1;
                objWithContext = BuildersKt.withContext(snapFlingBehavior$motionScaleDuration$1, snapFlingBehavior$fling$result$1, c04252);
                if (objWithContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objWithContext = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AnimationResult result = (AnimationResult) objWithContext;
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object shortSnap(ScrollScope $this$shortSnap, float velocity, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
        float closestOffset = findClosestOffset(0.0f, this.lazyListState);
        return animateSnap($this$shortSnap, closestOffset, closestOffset, AnimationStateKt.AnimationState$default(0.0f, velocity, 0L, 0L, false, 28, null), this.snapAnimationSpec, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    public final Object longSnap(ScrollScope scrollScope, float initialVelocity, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
        C04261 c04261;
        SnapFlingBehavior snapFlingBehavior;
        ScrollScope $this$longSnap;
        Object objRunApproach;
        if (continuation instanceof C04261) {
            C04261 c04262 = (C04261) continuation;
            if ((c04262.label & Integer.MIN_VALUE) != 0) {
                c04262.label -= Integer.MIN_VALUE;
                c04261 = c04262;
            } else {
                c04261 = new C04261(continuation);
            }
        } else {
            c04261 = new C04261(continuation);
        }
        Object $result = c04261.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c04261.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                snapFlingBehavior = this;
                $this$longSnap = scrollScope;
                float offset = RangesKt.coerceAtLeast(Math.abs(DecayAnimationSpecKt.calculateTargetValue(snapFlingBehavior.decayAnimationSpec, 0.0f, initialVelocity)) - snapFlingBehavior.getItemSize(), 0.0f);
                if (!(offset == 0.0f)) {
                    offset *= Math.signum(initialVelocity);
                }
                c04261.L$0 = snapFlingBehavior;
                c04261.L$1 = $this$longSnap;
                c04261.label = 1;
                objRunApproach = snapFlingBehavior.runApproach($this$longSnap, offset, initialVelocity, c04261);
                if (objRunApproach == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ScrollScope $this$longSnap2 = (ScrollScope) c04261.L$1;
                SnapFlingBehavior snapFlingBehavior2 = (SnapFlingBehavior) c04261.L$0;
                ResultKt.throwOnFailure($result);
                $this$longSnap = $this$longSnap2;
                snapFlingBehavior = snapFlingBehavior2;
                objRunApproach = $result;
                break;
            case 2:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AnimationResult animationResult = (AnimationResult) objRunApproach;
        float fFloatValue = ((Number) animationResult.component1()).floatValue();
        AnimationState animationState = animationResult.component2();
        AnimationState<Float, AnimationVector1D> animationStateCopy$default = AnimationStateKt.copy$default(animationState, 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
        AnimationSpec<Float> animationSpec = snapFlingBehavior.snapAnimationSpec;
        c04261.L$0 = null;
        c04261.L$1 = null;
        c04261.label = 2;
        Object objAnimateSnap = snapFlingBehavior.animateSnap($this$longSnap, fFloatValue, fFloatValue, animationStateCopy$default, animationSpec, c04261);
        if (objAnimateSnap == coroutine_suspended) {
            return coroutine_suspended;
        }
        return objAnimateSnap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    public final Object runApproach(ScrollScope $this$runApproach_u24lambda_u242, float initialTargetOffset, float initialVelocity, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
        C04281 c04281;
        SnapFlingBehavior snapFlingBehavior;
        Object obj;
        if (continuation instanceof C04281) {
            C04281 c04282 = (C04281) continuation;
            if ((c04282.label & Integer.MIN_VALUE) != 0) {
                c04282.label -= Integer.MIN_VALUE;
                c04281 = c04282;
            } else {
                c04281 = new C04281(continuation);
            }
        } else {
            c04281 = new C04281(continuation);
        }
        Object $result = c04281.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c04281.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                snapFlingBehavior = this;
                AnimationState<Float, AnimationVector1D> animationStateAnimationState$default = AnimationStateKt.AnimationState$default(0.0f, initialVelocity, 0L, 0L, false, 28, null);
                DecayAnimationSpec<Float> decayAnimationSpec = snapFlingBehavior.decayAnimationSpec;
                c04281.L$0 = snapFlingBehavior;
                c04281.label = 1;
                Object objAnimateDecay = snapFlingBehavior.animateDecay($this$runApproach_u24lambda_u242, initialTargetOffset, animationStateAnimationState$default, decayAnimationSpec, c04281);
                if (objAnimateDecay == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = objAnimateDecay;
                break;
                break;
            case 1:
                SnapFlingBehavior snapFlingBehavior2 = (SnapFlingBehavior) c04281.L$0;
                ResultKt.throwOnFailure($result);
                snapFlingBehavior = snapFlingBehavior2;
                obj = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AnimationState currentAnimationState = ((AnimationResult) obj).component2();
        float remainingOffset = snapFlingBehavior.findClosestOffset(((Number) currentAnimationState.getVelocity()).floatValue(), snapFlingBehavior.lazyListState);
        return new AnimationResult(Boxing.boxFloat(remainingOffset), currentAnimationState);
    }

    public boolean equals(Object other) {
        return (other instanceof SnapFlingBehavior) && Intrinsics.areEqual(((SnapFlingBehavior) other).snapAnimationSpec, this.snapAnimationSpec) && Intrinsics.areEqual(((SnapFlingBehavior) other).decayAnimationSpec, this.decayAnimationSpec) && Intrinsics.areEqual(((SnapFlingBehavior) other).lazyListState, this.lazyListState) && Intrinsics.areEqual(((SnapFlingBehavior) other).density, this.density);
    }

    public int hashCode() {
        int it = (0 * 31) + this.snapAnimationSpec.hashCode();
        return (((((it * 31) + this.decayAnimationSpec.hashCode()) * 31) + this.lazyListState.hashCode()) * 31) + this.density.hashCode();
    }

    private final <T extends Comparable<? super T>> T component1(ClosedFloatingPointRange<T> closedFloatingPointRange) {
        Intrinsics.checkNotNullParameter(closedFloatingPointRange, "<this>");
        return closedFloatingPointRange.getStart();
    }

    private final <T extends Comparable<? super T>> T component2(ClosedFloatingPointRange<T> closedFloatingPointRange) {
        Intrinsics.checkNotNullParameter(closedFloatingPointRange, "<this>");
        return closedFloatingPointRange.getEndInclusive();
    }

    private static final boolean findClosestOffset$isValidDistance(float $this$findClosestOffset_u24isValidDistance) {
        if (!($this$findClosestOffset_u24isValidDistance == Float.POSITIVE_INFINITY)) {
            if (!($this$findClosestOffset_u24isValidDistance == Float.NEGATIVE_INFINITY)) {
                return true;
            }
        }
        return false;
    }

    private static final ClosedFloatingPointRange<Float> findClosestOffset$calculateSnappingOffsetBounds(LazyListState $lazyListState, SnapFlingBehavior this$0) {
        float lowerBoundOffset = Float.NEGATIVE_INFINITY;
        float upperBoundOffset = Float.POSITIVE_INFINITY;
        LazyListLayoutInfo $this$findClosestOffset_u24calculateSnappingOffsetBounds_u24lambda_u248 = $lazyListState.getLayoutInfo();
        List<LazyListItemInfo> visibleItemsInfo = $this$findClosestOffset_u24calculateSnappingOffsetBounds_u24lambda_u248.getVisibleItemsInfo();
        int size = visibleItemsInfo.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = visibleItemsInfo.get(index$iv);
            LazyListItemInfo item = (LazyListItemInfo) item$iv;
            float offset = this$0.calculateDistanceToDesiredSnapPosition($this$findClosestOffset_u24calculateSnappingOffsetBounds_u24lambda_u248, item);
            if (offset <= 0.0f && offset > lowerBoundOffset) {
                lowerBoundOffset = offset;
            }
            if (offset >= 0.0f && offset < upperBoundOffset) {
                upperBoundOffset = offset;
            }
        }
        return RangesKt.rangeTo(lowerBoundOffset, upperBoundOffset);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0041  */
    /* JADX WARN: Code duplicated, block: B:22:0x004d  */
    private final float findClosestOffset(float velocity, LazyListState lazyListState) {
        float finalDistance;
        ClosedFloatingPointRange<Float> closedFloatingPointRangeFindClosestOffset$calculateSnappingOffsetBounds = findClosestOffset$calculateSnappingOffsetBounds(lazyListState, this);
        float lowerBound = ((Number) component1(closedFloatingPointRangeFindClosestOffset$calculateSnappingOffsetBounds)).floatValue();
        float upperBound = ((Number) component2(closedFloatingPointRangeFindClosestOffset$calculateSnappingOffsetBounds)).floatValue();
        float fSignum = Math.signum(velocity);
        if (!(fSignum == 0.0f)) {
            if (fSignum == 1.0f) {
                finalDistance = upperBound;
            } else {
                if (fSignum == -1.0f) {
                    finalDistance = lowerBound;
                } else {
                    finalDistance = 0.0f;
                }
            }
        } else if (Math.abs(upperBound) <= Math.abs(lowerBound)) {
            finalDistance = upperBound;
        } else {
            finalDistance = lowerBound;
        }
        if (findClosestOffset$isValidDistance(finalDistance)) {
            return finalDistance;
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object animateDecay(final ScrollScope $this$animateDecay, final float targetOffset, AnimationState<Float, AnimationVector1D> animationState, DecayAnimationSpec<Float> decayAnimationSpec, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
        AnonymousClass1 anonymousClass1;
        float targetOffset2;
        Ref.FloatRef previousValue;
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
                final Ref.FloatRef previousValue2 = new Ref.FloatRef();
                boolean z = animationState.getVelocity().floatValue() == 0.0f;
                Function1<AnimationScope<Float, AnimationVector1D>, Unit> function1 = new Function1<AnimationScope<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material3.SnapFlingBehavior.animateDecay.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
                        invoke2(animationScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AnimationScope<Float, AnimationVector1D> animateDecay) {
                        Intrinsics.checkNotNullParameter(animateDecay, "$this$animateDecay");
                        if (Math.abs(animateDecay.getValue().floatValue()) >= Math.abs(targetOffset)) {
                            float finalValue = this.coerceToTarget(animateDecay.getValue().floatValue(), targetOffset);
                            float finalDelta = finalValue - previousValue2.element;
                            SnapFlingBehavior.animateDecay$consumeDelta(animateDecay, $this$animateDecay, finalDelta);
                            animateDecay.cancelAnimation();
                            return;
                        }
                        float delta = animateDecay.getValue().floatValue() - previousValue2.element;
                        SnapFlingBehavior.animateDecay$consumeDelta(animateDecay, $this$animateDecay, delta);
                        previousValue2.element = animateDecay.getValue().floatValue();
                    }
                };
                anonymousClass2.L$0 = animationState;
                anonymousClass2.L$1 = previousValue2;
                anonymousClass2.F$0 = targetOffset;
                anonymousClass2.label = 1;
                if (SuspendAnimationKt.animateDecay(animationState, decayAnimationSpec, !z, function1, anonymousClass2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                targetOffset2 = targetOffset;
                previousValue = previousValue2;
                break;
                break;
            case 1:
                targetOffset2 = anonymousClass2.F$0;
                previousValue = (Ref.FloatRef) anonymousClass2.L$1;
                animationState = (AnimationState) anonymousClass2.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return new AnimationResult(Boxing.boxFloat(targetOffset2 - previousValue.element), animationState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void animateDecay$consumeDelta(AnimationScope<Float, AnimationVector1D> animationScope, ScrollScope $this_animateDecay, float delta) {
        float consumed = $this_animateDecay.scrollBy(delta);
        if (Math.abs(delta - consumed) > 0.5f) {
            animationScope.cancelAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    public final Object animateSnap(final ScrollScope $this$animateSnap, float f, final float cancelOffset, AnimationState<Float, AnimationVector1D> animationState, AnimationSpec<Float> animationSpec, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
        C04231 c04231;
        float targetOffset;
        AnimationState<Float, AnimationVector1D> animationState2;
        SnapFlingBehavior snapFlingBehavior;
        Ref.FloatRef consumedUpToNow;
        float initialVelocity;
        if (continuation instanceof C04231) {
            C04231 c04232 = (C04231) continuation;
            if ((c04232.label & Integer.MIN_VALUE) != 0) {
                c04232.label -= Integer.MIN_VALUE;
                c04231 = c04232;
            } else {
                c04231 = new C04231(continuation);
            }
        } else {
            c04231 = new C04231(continuation);
        }
        Object $result = c04231.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c04231.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                targetOffset = f;
                final Ref.FloatRef consumedUpToNow2 = new Ref.FloatRef();
                float initialVelocity2 = animationState.getVelocity().floatValue();
                Float fBoxFloat = Boxing.boxFloat(targetOffset);
                boolean z = animationState.getVelocity().floatValue() == 0.0f;
                Function1<AnimationScope<Float, AnimationVector1D>, Unit> function1 = new Function1<AnimationScope<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material3.SnapFlingBehavior.animateSnap.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
                        invoke2(animationScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Code duplicated, block: B:9:0x0042  */
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AnimationScope<Float, AnimationVector1D> animateTo) {
                        Intrinsics.checkNotNullParameter(animateTo, "$this$animateTo");
                        float realValue = SnapFlingBehavior.this.coerceToTarget(animateTo.getValue().floatValue(), cancelOffset);
                        float delta = realValue - consumedUpToNow2.element;
                        float consumed = $this$animateSnap.scrollBy(delta);
                        if (Math.abs(delta - consumed) > 0.5f) {
                            animateTo.cancelAnimation();
                        } else {
                            if (!(realValue == animateTo.getValue().floatValue())) {
                                animateTo.cancelAnimation();
                            }
                        }
                        consumedUpToNow2.element += consumed;
                    }
                };
                c04231.L$0 = this;
                c04231.L$1 = animationState;
                c04231.L$2 = consumedUpToNow2;
                c04231.F$0 = targetOffset;
                c04231.F$1 = initialVelocity2;
                c04231.label = 1;
                if (SuspendAnimationKt.animateTo(animationState, fBoxFloat, animationSpec, !z, function1, c04231) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                animationState2 = animationState;
                snapFlingBehavior = this;
                consumedUpToNow = consumedUpToNow2;
                initialVelocity = initialVelocity2;
                break;
                break;
            case 1:
                initialVelocity = c04231.F$1;
                targetOffset = c04231.F$0;
                consumedUpToNow = (Ref.FloatRef) c04231.L$2;
                AnimationState<Float, AnimationVector1D> animationState3 = (AnimationState) c04231.L$1;
                SnapFlingBehavior snapFlingBehavior2 = (SnapFlingBehavior) c04231.L$0;
                ResultKt.throwOnFailure($result);
                animationState2 = animationState3;
                snapFlingBehavior = snapFlingBehavior2;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        float finalVelocity = snapFlingBehavior.coerceToTarget(animationState2.getVelocity().floatValue(), initialVelocity);
        return new AnimationResult(Boxing.boxFloat(targetOffset - consumedUpToNow.element), AnimationStateKt.copy$default((AnimationState) animationState2, 0.0f, finalVelocity, 0L, 0L, false, 29, (Object) null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float coerceToTarget(float $this$coerceToTarget, float target) {
        if (target == 0.0f) {
            return 0.0f;
        }
        return target > 0.0f ? RangesKt.coerceAtMost($this$coerceToTarget, target) : RangesKt.coerceAtLeast($this$coerceToTarget, target);
    }

    private final float calculateDistanceToDesiredSnapPosition(LazyListLayoutInfo layoutInfo, LazyListItemInfo item) {
        int containerSize = (getSingleAxisViewportSize(layoutInfo) - layoutInfo.getBeforeContentPadding()) - layoutInfo.getAfterContentPadding();
        float f = containerSize;
        float f2 = 2;
        float desiredDistance = (f / f2) - (item.getSize() / f2);
        int itemCurrentPosition = item.getOffset();
        return itemCurrentPosition - desiredDistance;
    }

    private final int getSingleAxisViewportSize(LazyListLayoutInfo $this$singleAxisViewportSize) {
        return $this$singleAxisViewportSize.getOrientation() == Orientation.Vertical ? IntSize.m5433getHeightimpl($this$singleAxisViewportSize.mo589getViewportSizeYbymL2g()) : IntSize.m5434getWidthimpl($this$singleAxisViewportSize.mo589getViewportSizeYbymL2g());
    }
}
