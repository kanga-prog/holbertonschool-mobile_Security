package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.unit.Density;
import androidx.core.app.NotificationCompat;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: SwipeableV2.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b5\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u0000 s*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001sB\u007f\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012#\b\u0002\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\b\u0012.\b\u0002\u0010\r\u001a(\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00060\u000e¢\u0006\u0002\b\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013ø\u0001\u0000¢\u0006\u0002\u0010\u0014J#\u0010V\u001a\u00020W2\u0006\u0010Q\u001a\u00028\u00002\b\b\u0002\u0010X\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010YJ%\u0010Z\u001a\u00028\u00002\u0006\u0010@\u001a\u00020\u00062\u0006\u0010(\u001a\u00028\u00002\u0006\u0010X\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010[J\u000e\u0010\\\u001a\u00020\u00062\u0006\u0010]\u001a\u00020\u0006J\u0013\u0010^\u001a\u00020\f2\u0006\u0010_\u001a\u00028\u0000¢\u0006\u0002\u0010`J\b\u0010a\u001a\u00020\u000fH\u0002J\u0006\u0010b\u001a\u00020\u0006J\u0019\u0010c\u001a\u00020W2\u0006\u0010X\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010dJ\u0015\u0010e\u001a\u00020W2\u0006\u0010Q\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010$J\u0019\u0010f\u001a\u00020W2\u0006\u0010Q\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010gJ9\u0010h\u001a\u00020W2\b\b\u0002\u0010i\u001a\u00020j2\u001c\u0010k\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020W0l\u0012\u0006\u0012\u0004\u0018\u00010\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010mJ\u0017\u0010n\u001a\u00020\f2\u0006\u0010Q\u001a\u00028\u0000H\u0000¢\u0006\u0004\bo\u0010`J!\u0010p\u001a\u00020\f2\u0012\u0010q\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0016H\u0000¢\u0006\u0002\brRC\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u00162\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u00168@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR/\u0010 \u001a\u0004\u0018\u00018\u00002\b\u0010\u0015\u001a\u0004\u0018\u00018\u00008B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010\u001d\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R/\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R+\u0010(\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u00008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010\u001d\u001a\u0004\b)\u0010\"\"\u0004\b*\u0010$R\u001c\u0010,\u001a\u0004\u0018\u00010\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0011\u00101\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b1\u00102R+\u00103\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00068F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b8\u0010\u001d\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001b\u00109\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b:\u00105R\u001b\u0010=\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b?\u0010<\u001a\u0004\b>\u00105R/\u0010@\u001a\u0004\u0018\u00010\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u00068F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010\u001d\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR:\u0010\r\u001a(\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00060\u000e¢\u0006\u0002\b\u0011X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u001b\u0010H\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010<\u001a\u0004\bI\u00105R\u0014\u0010K\u001a\u00020LX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u0010NR\u000e\u0010O\u001a\u00020PX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010Q\u001a\u00028\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bS\u0010<\u001a\u0004\bR\u0010\"R\u001f\u0010\u0012\u001a\u00020\u0013X\u0080\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010U\u001a\u0004\bT\u00105\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006t"}, d2 = {"Landroidx/compose/material3/SwipeableV2State;", "T", "", "initialValue", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmValueChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "newValue", "", "positionalThreshold", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "totalDistance", "Lkotlin/ExtensionFunctionType;", "velocityThreshold", "Landroidx/compose/ui/unit/Dp;", "(Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "<set-?>", "", "anchors", "getAnchors$material3_release", "()Ljava/util/Map;", "setAnchors$material3_release", "(Ljava/util/Map;)V", "anchors$delegate", "Landroidx/compose/runtime/MutableState;", "getAnimationSpec$material3_release", "()Landroidx/compose/animation/core/AnimationSpec;", "animationTarget", "getAnimationTarget", "()Ljava/lang/Object;", "setAnimationTarget", "(Ljava/lang/Object;)V", "animationTarget$delegate", "getConfirmValueChange$material3_release", "()Lkotlin/jvm/functions/Function1;", "currentValue", "getCurrentValue", "setCurrentValue", "currentValue$delegate", "density", "getDensity$material3_release", "()Landroidx/compose/ui/unit/Density;", "setDensity$material3_release", "(Landroidx/compose/ui/unit/Density;)V", "isAnimationRunning", "()Z", "lastVelocity", "getLastVelocity", "()F", "setLastVelocity", "(F)V", "lastVelocity$delegate", "maxOffset", "getMaxOffset", "maxOffset$delegate", "Landroidx/compose/runtime/State;", "minOffset", "getMinOffset", "minOffset$delegate", "offset", "getOffset", "()Ljava/lang/Float;", "setOffset", "(Ljava/lang/Float;)V", "offset$delegate", "getPositionalThreshold$material3_release", "()Lkotlin/jvm/functions/Function2;", NotificationCompat.CATEGORY_PROGRESS, "getProgress", "progress$delegate", "swipeDraggableState", "Landroidx/compose/foundation/gestures/DraggableState;", "getSwipeDraggableState$material3_release", "()Landroidx/compose/foundation/gestures/DraggableState;", "swipeMutex", "Landroidx/compose/material3/InternalMutatorMutex;", "targetValue", "getTargetValue", "targetValue$delegate", "getVelocityThreshold-D9Ej5fM$material3_release", "F", "animateTo", "", "velocity", "(Ljava/lang/Object;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "computeTarget", "(FLjava/lang/Object;F)Ljava/lang/Object;", "dispatchRawDelta", "delta", "hasAnchorForValue", "value", "(Ljava/lang/Object;)Z", "requireDensity", "requireOffset", "settle", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "snap", "snapTo", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "swipe", "swipePriority", "Landroidx/compose/foundation/MutatePriority;", "action", "Lkotlin/coroutines/Continuation;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySnapTo", "trySnapTo$material3_release", "updateAnchors", "newAnchors", "updateAnchors$material3_release", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SwipeableV2State<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: anchors$delegate, reason: from kotlin metadata */
    private final MutableState anchors;
    private final AnimationSpec<Float> animationSpec;

    /* JADX INFO: renamed from: animationTarget$delegate, reason: from kotlin metadata */
    private final MutableState animationTarget;
    private final Function1<T, Boolean> confirmValueChange;

    /* JADX INFO: renamed from: currentValue$delegate, reason: from kotlin metadata */
    private final MutableState currentValue;
    private Density density;

    /* JADX INFO: renamed from: lastVelocity$delegate, reason: from kotlin metadata */
    private final MutableState lastVelocity;

    /* JADX INFO: renamed from: maxOffset$delegate, reason: from kotlin metadata */
    private final State maxOffset;

    /* JADX INFO: renamed from: minOffset$delegate, reason: from kotlin metadata */
    private final State minOffset;

    /* JADX INFO: renamed from: offset$delegate, reason: from kotlin metadata */
    private final MutableState offset;
    private final Function2<Density, Float, Float> positionalThreshold;

    /* JADX INFO: renamed from: progress$delegate, reason: from kotlin metadata */
    private final State progress;
    private final DraggableState swipeDraggableState;
    private final InternalMutatorMutex swipeMutex;

    /* JADX INFO: renamed from: targetValue$delegate, reason: from kotlin metadata */
    private final State targetValue;
    private final float velocityThreshold;

    /* JADX INFO: renamed from: androidx.compose.material3.SwipeableV2State$animateTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SwipeableV2.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.SwipeableV2State", f = "SwipeableV2.kt", i = {0}, l = {350}, m = "animateTo", n = {"this"}, s = {"L$0"})
    static final class C04371 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SwipeableV2State<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04371(SwipeableV2State<T> swipeableV2State, Continuation<? super C04371> continuation) {
            super(continuation);
            this.this$0 = swipeableV2State;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.animateTo(null, 0.0f, this);
        }
    }

    public /* synthetic */ SwipeableV2State(Object obj, AnimationSpec animationSpec, Function1 function1, Function2 function2, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, animationSpec, function1, function2, f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private SwipeableV2State(T t, AnimationSpec<Float> animationSpec, Function1<? super T, Boolean> confirmValueChange, Function2<? super Density, ? super Float, Float> positionalThreshold, float velocityThreshold) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(confirmValueChange, "confirmValueChange");
        Intrinsics.checkNotNullParameter(positionalThreshold, "positionalThreshold");
        this.animationSpec = animationSpec;
        this.confirmValueChange = confirmValueChange;
        this.positionalThreshold = positionalThreshold;
        this.velocityThreshold = velocityThreshold;
        this.swipeMutex = new InternalMutatorMutex();
        this.swipeDraggableState = new SwipeableV2State$swipeDraggableState$1(this);
        this.currentValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
        this.targetValue = SnapshotStateKt.derivedStateOf(new Function0<T>(this) { // from class: androidx.compose.material3.SwipeableV2State$targetValue$2
            final /* synthetic */ SwipeableV2State<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                T currentValue;
                T t2 = (T) this.this$0.getAnimationTarget();
                if (t2 != null) {
                    return t2;
                }
                SwipeableV2State<T> swipeableV2State = this.this$0;
                Float offset = swipeableV2State.getOffset();
                if (offset != null) {
                    currentValue = (T) swipeableV2State.computeTarget(offset.floatValue(), swipeableV2State.getCurrentValue(), 0.0f);
                } else {
                    currentValue = swipeableV2State.getCurrentValue();
                }
                return currentValue;
            }
        });
        this.offset = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.progress = SnapshotStateKt.derivedStateOf(new Function0<Float>(this) { // from class: androidx.compose.material3.SwipeableV2State$progress$2
            final /* synthetic */ SwipeableV2State<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                Float f = (Float) this.this$0.getAnchors$material3_release().get(this.this$0.getCurrentValue());
                float f2 = 0.0f;
                float a = f != null ? f.floatValue() : 0.0f;
                Float f3 = (Float) this.this$0.getAnchors$material3_release().get(this.this$0.getTargetValue());
                float b = f3 != null ? f3.floatValue() : 0.0f;
                float distance = Math.abs(b - a);
                if (distance > 1.0E-6f) {
                    float progress = (this.this$0.requireOffset() - a) / (b - a);
                    if (progress >= 1.0E-6f) {
                        f2 = progress > 0.999999f ? 1.0f : progress;
                    }
                } else {
                    f2 = 1.0f;
                }
                return Float.valueOf(f2);
            }
        });
        this.lastVelocity = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(0.0f), null, 2, null);
        this.minOffset = SnapshotStateKt.derivedStateOf(new Function0<Float>(this) { // from class: androidx.compose.material3.SwipeableV2State$minOffset$2
            final /* synthetic */ SwipeableV2State<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                Float fMinOrNull = SwipeableV2Kt.minOrNull(this.this$0.getAnchors$material3_release());
                return Float.valueOf(fMinOrNull != null ? fMinOrNull.floatValue() : Float.NEGATIVE_INFINITY);
            }
        });
        this.maxOffset = SnapshotStateKt.derivedStateOf(new Function0<Float>(this) { // from class: androidx.compose.material3.SwipeableV2State$maxOffset$2
            final /* synthetic */ SwipeableV2State<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                Float fMaxOrNull = SwipeableV2Kt.maxOrNull(this.this$0.getAnchors$material3_release());
                return Float.valueOf(fMaxOrNull != null ? fMaxOrNull.floatValue() : Float.POSITIVE_INFINITY);
            }
        });
        this.animationTarget = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.anchors = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MapsKt.emptyMap(), null, 2, null);
    }

    public /* synthetic */ SwipeableV2State(Object obj, AnimationSpec animationSpec, Function1 function1, Function2 function2, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? SwipeableV2Defaults.INSTANCE.getAnimationSpec() : animationSpec, (i & 4) != 0 ? new Function1<T, Boolean>() { // from class: androidx.compose.material3.SwipeableV2State.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(T t) {
                return true;
            }
        } : function1, (i & 8) != 0 ? SwipeableV2Defaults.INSTANCE.getPositionalThreshold() : function2, (i & 16) != 0 ? SwipeableV2Defaults.INSTANCE.m1821getVelocityThresholdD9Ej5fM() : f, null);
    }

    public final AnimationSpec<Float> getAnimationSpec$material3_release() {
        return this.animationSpec;
    }

    public final Function1<T, Boolean> getConfirmValueChange$material3_release() {
        return this.confirmValueChange;
    }

    public final Function2<Density, Float, Float> getPositionalThreshold$material3_release() {
        return this.positionalThreshold;
    }

    /* JADX INFO: renamed from: getVelocityThreshold-D9Ej5fM$material3_release, reason: not valid java name and from getter */
    public final float getVelocityThreshold() {
        return this.velocityThreshold;
    }

    /* JADX INFO: renamed from: getSwipeDraggableState$material3_release, reason: from getter */
    public final DraggableState getSwipeDraggableState() {
        return this.swipeDraggableState;
    }

    private final void setCurrentValue(T t) {
        MutableState $this$setValue$iv = this.currentValue;
        $this$setValue$iv.setValue(t);
    }

    public final T getCurrentValue() {
        State $this$getValue$iv = this.currentValue;
        return $this$getValue$iv.getValue();
    }

    public final T getTargetValue() {
        return (T) this.targetValue.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setOffset(Float f) {
        MutableState $this$setValue$iv = this.offset;
        $this$setValue$iv.setValue(f);
    }

    public final Float getOffset() {
        State $this$getValue$iv = this.offset;
        return (Float) $this$getValue$iv.getValue();
    }

    public final float requireOffset() {
        Float offset = getOffset();
        if (offset != null) {
            return offset.floatValue();
        }
        throw new IllegalStateException("The offset was read before being initialized. Did you access the offset in a phase before layout, like effects or composition?".toString());
    }

    public final boolean isAnimationRunning() {
        return getAnimationTarget() != null;
    }

    public final float getProgress() {
        State $this$getValue$iv = this.progress;
        return ((Number) $this$getValue$iv.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLastVelocity(float f) {
        MutableState $this$setValue$iv = this.lastVelocity;
        $this$setValue$iv.setValue(Float.valueOf(f));
    }

    public final float getLastVelocity() {
        State $this$getValue$iv = this.lastVelocity;
        return ((Number) $this$getValue$iv.getValue()).floatValue();
    }

    public final float getMinOffset() {
        State $this$getValue$iv = this.minOffset;
        return ((Number) $this$getValue$iv.getValue()).floatValue();
    }

    public final float getMaxOffset() {
        State $this$getValue$iv = this.maxOffset;
        return ((Number) $this$getValue$iv.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final T getAnimationTarget() {
        State $this$getValue$iv = this.animationTarget;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAnimationTarget(T t) {
        MutableState $this$setValue$iv = this.animationTarget;
        $this$setValue$iv.setValue(t);
    }

    public final Map<T, Float> getAnchors$material3_release() {
        State $this$getValue$iv = this.anchors;
        return (Map) $this$getValue$iv.getValue();
    }

    public final void setAnchors$material3_release(Map<T, Float> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        MutableState $this$setValue$iv = this.anchors;
        $this$setValue$iv.setValue(map);
    }

    /* JADX INFO: renamed from: getDensity$material3_release, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    public final void setDensity$material3_release(Density density) {
        this.density = density;
    }

    public final boolean updateAnchors$material3_release(Map<T, Float> newAnchors) {
        boolean initialValueHasAnchor;
        Intrinsics.checkNotNullParameter(newAnchors, "newAnchors");
        boolean previousAnchorsEmpty = getAnchors$material3_release().isEmpty();
        setAnchors$material3_release(newAnchors);
        if (previousAnchorsEmpty) {
            T currentValue = getCurrentValue();
            Float initialValueAnchor = getAnchors$material3_release().get(currentValue);
            initialValueHasAnchor = initialValueAnchor != null;
            if (initialValueHasAnchor) {
                trySnapTo$material3_release(currentValue);
            }
        } else {
            initialValueHasAnchor = true;
        }
        return (initialValueHasAnchor && previousAnchorsEmpty) ? false : true;
    }

    public final boolean hasAnchorForValue(T value) {
        return getAnchors$material3_release().containsKey(value);
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SwipeableV2State$snapTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SwipeableV2.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000H\u008a@"}, d2 = {"T", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.material3.SwipeableV2State$snapTo$2", f = "SwipeableV2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04382 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ T $targetValue;
        int label;
        final /* synthetic */ SwipeableV2State<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04382(SwipeableV2State<T> swipeableV2State, T t, Continuation<? super C04382> continuation) {
            super(1, continuation);
            this.this$0 = swipeableV2State;
            this.$targetValue = t;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C04382(this.this$0, this.$targetValue, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((C04382) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.this$0.snap(this.$targetValue);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object snapTo(T t, Continuation<? super Unit> continuation) {
        Object objSwipe$default = swipe$default(this, null, new C04382(this, t, null), continuation, 1, null);
        return objSwipe$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSwipe$default : Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateTo$default(SwipeableV2State swipeableV2State, Object obj, float f, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            f = swipeableV2State.getLastVelocity();
        }
        return swipeableV2State.animateTo(obj, f, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x009e  */
    /* JADX WARN: Code duplicated, block: B:31:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:32:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:38:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:41:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:50:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:52:0x0114  */
    /* JADX WARN: Code duplicated, block: B:53:0x0116  */
    /* JADX WARN: Code duplicated, block: B:59:0x0120  */
    /* JADX WARN: Code duplicated, block: B:62:0x0127  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x011a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Multi-variable type inference failed */
    public final Object animateTo(T t, float f, Continuation<? super Unit> continuation) throws Throwable {
        C04371 c04371;
        SwipeableV2State swipeableV2State;
        SwipeableV2State swipeableV2State2;
        float fRequireOffset;
        Iterator<T> it;
        T next;
        Object key;
        boolean z;
        float fRequireOffset2;
        Iterator<T> it2;
        T next2;
        Object key2;
        boolean z2;
        if (continuation instanceof C04371) {
            c04371 = (C04371) continuation;
            if ((c04371.label & Integer.MIN_VALUE) != 0) {
                c04371.label -= Integer.MIN_VALUE;
            } else {
                c04371 = new C04371(this, continuation);
            }
        } else {
            c04371 = new C04371(this, continuation);
        }
        Object obj = c04371.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c04371.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Float f2 = getAnchors$material3_release().get(t);
                if (f2 != null) {
                    try {
                        AnonymousClass2 anonymousClass2 = new AnonymousClass2(this, t, f2, f, null);
                        c04371.L$0 = this;
                        c04371.label = 1;
                        try {
                            if (swipe$default(this, null, anonymousClass2, c04371, 1, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            swipeableV2State2 = this;
                            swipeableV2State2.setAnimationTarget(null);
                            fRequireOffset2 = swipeableV2State2.requireOffset();
                            it2 = swipeableV2State2.getAnchors$material3_release().entrySet().iterator();
                            do {
                                if (it2.hasNext()) {
                                    next2 = it2.next();
                                    if (Math.abs(((Number) ((Map.Entry) next2).getValue()).floatValue() - fRequireOffset2) < 0.5f) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                } else {
                                    next2 = null;
                                }
                                Map.Entry entry = (Map.Entry) next2;
                                key2 = entry != null ? entry.getKey() : null;
                                if (key2 == null) {
                                    key2 = swipeableV2State2.getCurrentValue();
                                }
                                swipeableV2State2.setCurrentValue(key2);
                            } while (!z2);
                            Map.Entry entry2 = (Map.Entry) next2;
                            key2 = entry2 != null ? entry2.getKey() : null;
                            if (key2 == null) {
                                key2 = swipeableV2State2.getCurrentValue();
                            }
                            swipeableV2State2.setCurrentValue(key2);
                        } catch (Throwable th) {
                            th = th;
                            swipeableV2State = this;
                            swipeableV2State.setAnimationTarget(null);
                            fRequireOffset = swipeableV2State.requireOffset();
                            it = swipeableV2State.getAnchors$material3_release().entrySet().iterator();
                            do {
                                if (it.hasNext()) {
                                    next = it.next();
                                    if (Math.abs(((Number) ((Map.Entry) next).getValue()).floatValue() - fRequireOffset) < 0.5f) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                } else {
                                    next = null;
                                }
                                Map.Entry entry3 = (Map.Entry) next;
                                key = entry3 != null ? entry3.getKey() : null;
                                if (key == null) {
                                    key = swipeableV2State.getCurrentValue();
                                }
                                swipeableV2State.setCurrentValue(key);
                                throw th;
                            } while (!z);
                            Map.Entry entry4 = (Map.Entry) next;
                            key = entry4 != null ? entry4.getKey() : null;
                            if (key == null) {
                                key = swipeableV2State.getCurrentValue();
                            }
                            swipeableV2State.setCurrentValue(key);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        swipeableV2State = this;
                    }
                } else {
                    setCurrentValue(t);
                }
                return Unit.INSTANCE;
            case 1:
                swipeableV2State = (SwipeableV2State) c04371.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    swipeableV2State2 = swipeableV2State;
                    swipeableV2State2.setAnimationTarget(null);
                    fRequireOffset2 = swipeableV2State2.requireOffset();
                    it2 = swipeableV2State2.getAnchors$material3_release().entrySet().iterator();
                    do {
                        if (it2.hasNext()) {
                            next2 = it2.next();
                            if (Math.abs(((Number) ((Map.Entry) next2).getValue()).floatValue() - fRequireOffset2) < 0.5f) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                        } else {
                            next2 = null;
                        }
                        Map.Entry entry5 = (Map.Entry) next2;
                        key2 = entry5 != null ? entry5.getKey() : null;
                        if (key2 == null) {
                            key2 = swipeableV2State2.getCurrentValue();
                        }
                        swipeableV2State2.setCurrentValue(key2);
                        return Unit.INSTANCE;
                    } while (!z2);
                    Map.Entry entry6 = (Map.Entry) next2;
                    key2 = entry6 != null ? entry6.getKey() : null;
                    if (key2 == null) {
                        key2 = swipeableV2State2.getCurrentValue();
                    }
                    swipeableV2State2.setCurrentValue(key2);
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    swipeableV2State.setAnimationTarget(null);
                    fRequireOffset = swipeableV2State.requireOffset();
                    it = swipeableV2State.getAnchors$material3_release().entrySet().iterator();
                    do {
                        if (it.hasNext()) {
                            next = it.next();
                            if (Math.abs(((Number) ((Map.Entry) next).getValue()).floatValue() - fRequireOffset) < 0.5f) {
                                z = true;
                            } else {
                                z = false;
                            }
                        } else {
                            next = null;
                        }
                        Map.Entry entry7 = (Map.Entry) next;
                        key = entry7 != null ? entry7.getKey() : null;
                        if (key == null) {
                            key = swipeableV2State.getCurrentValue();
                        }
                        swipeableV2State.setCurrentValue(key);
                        throw th;
                    } while (!z);
                    Map.Entry entry8 = (Map.Entry) next;
                    key = entry8 != null ? entry8.getKey() : null;
                    if (key == null) {
                        key = swipeableV2State.getCurrentValue();
                    }
                    swipeableV2State.setCurrentValue(key);
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SwipeableV2State$animateTo$2, reason: invalid class name */
    /* JADX INFO: compiled from: SwipeableV2.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000H\u008a@"}, d2 = {"T", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.material3.SwipeableV2State$animateTo$2", f = "SwipeableV2.kt", i = {}, l = {353}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Float $targetOffset;
        final /* synthetic */ T $targetValue;
        final /* synthetic */ float $velocity;
        int label;
        final /* synthetic */ SwipeableV2State<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(SwipeableV2State<T> swipeableV2State, T t, Float f, float f2, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.this$0 = swipeableV2State;
            this.$targetValue = t;
            this.$targetOffset = f;
            this.$velocity = f2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$targetValue, this.$targetOffset, this.$velocity, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AnonymousClass2 anonymousClass2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.this$0.setAnimationTarget(this.$targetValue);
                    final Ref.FloatRef prev = new Ref.FloatRef();
                    Float offset = this.this$0.getOffset();
                    prev.element = offset != null ? offset.floatValue() : 0.0f;
                    float f = prev.element;
                    float fFloatValue = this.$targetOffset.floatValue();
                    float f2 = this.$velocity;
                    AnimationSpec<Float> animationSpec$material3_release = this.this$0.getAnimationSpec$material3_release();
                    final SwipeableV2State<T> swipeableV2State = this.this$0;
                    this.label = 1;
                    if (SuspendAnimationKt.animate(f, fFloatValue, f2, animationSpec$material3_release, new Function2<Float, Float, Unit>() { // from class: androidx.compose.material3.SwipeableV2State.animateTo.2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Float f3, Float f4) {
                            invoke(f3.floatValue(), f4.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(float value, float velocity) {
                            swipeableV2State.setOffset(Float.valueOf(value));
                            prev.element = value;
                            swipeableV2State.setLastVelocity(velocity);
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    anonymousClass2 = this;
                    break;
                    break;
                case 1:
                    anonymousClass2 = this;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            anonymousClass2.this$0.setLastVelocity(0.0f);
            return Unit.INSTANCE;
        }
    }

    public final Object settle(float velocity, Continuation<? super Unit> continuation) throws Throwable {
        T currentValue = getCurrentValue();
        T tComputeTarget = computeTarget(requireOffset(), currentValue, velocity);
        if (this.confirmValueChange.invoke(tComputeTarget).booleanValue()) {
            Object objAnimateTo = animateTo(tComputeTarget, velocity, continuation);
            return objAnimateTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo : Unit.INSTANCE;
        }
        Object objAnimateTo2 = animateTo(currentValue, velocity, continuation);
        return objAnimateTo2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo2 : Unit.INSTANCE;
    }

    public final float dispatchRawDelta(float delta) {
        Float offset = getOffset();
        float currentDragPosition = offset != null ? offset.floatValue() : 0.0f;
        float potentiallyConsumed = currentDragPosition + delta;
        float clamped = RangesKt.coerceIn(potentiallyConsumed, getMinOffset(), getMaxOffset());
        float deltaToConsume = clamped - currentDragPosition;
        if (Math.abs(deltaToConsume) >= 0.0f) {
            Float offset2 = getOffset();
            setOffset(Float.valueOf(RangesKt.coerceIn((offset2 != null ? offset2.floatValue() : 0.0f) + deltaToConsume, getMinOffset(), getMaxOffset())));
        }
        return deltaToConsume;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final T computeTarget(float offset, T currentValue, float velocity) {
        Map<T, Float> anchors$material3_release = getAnchors$material3_release();
        Float f = anchors$material3_release.get(currentValue);
        Density densityRequireDensity = requireDensity();
        float fMo327toPx0680j_4 = densityRequireDensity.mo327toPx0680j_4(this.velocityThreshold);
        if (!Intrinsics.areEqual(f, offset) && f != null) {
            if (f.floatValue() < offset) {
                if (velocity >= fMo327toPx0680j_4) {
                    return (T) SwipeableV2Kt.closestAnchor(anchors$material3_release, offset, true);
                }
                T t = (T) SwipeableV2Kt.closestAnchor(anchors$material3_release, offset, true);
                if (offset >= Math.abs(f.floatValue() + Math.abs(this.positionalThreshold.invoke(densityRequireDensity, Float.valueOf(Math.abs(((Number) MapsKt.getValue(anchors$material3_release, t)).floatValue() - f.floatValue()))).floatValue()))) {
                    return t;
                }
            } else {
                if (velocity <= (-fMo327toPx0680j_4)) {
                    return (T) SwipeableV2Kt.closestAnchor(anchors$material3_release, offset, false);
                }
                T t2 = (T) SwipeableV2Kt.closestAnchor(anchors$material3_release, offset, false);
                float fAbs = Math.abs(f.floatValue() - Math.abs(this.positionalThreshold.invoke(densityRequireDensity, Float.valueOf(Math.abs(f.floatValue() - ((Number) MapsKt.getValue(anchors$material3_release, t2)).floatValue()))).floatValue()));
                if (offset < 0.0f) {
                    if (Math.abs(offset) >= fAbs) {
                        return t2;
                    }
                } else if (offset <= fAbs) {
                    return t2;
                }
            }
        }
        return currentValue;
    }

    private final Density requireDensity() {
        Density density = this.density;
        if (density == null) {
            throw new IllegalArgumentException(("SwipeableState did not have a density attached. Are you using Modifier.swipeable with this=" + this + " SwipeableState?").toString());
        }
        return density;
    }

    static /* synthetic */ Object swipe$default(SwipeableV2State swipeableV2State, MutatePriority mutatePriority, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return swipeableV2State.swipe(mutatePriority, function1, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SwipeableV2State$swipe$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SwipeableV2.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.material3.SwipeableV2State$swipe$2", f = "SwipeableV2.kt", i = {}, l = {462}, m = "invokeSuspend", n = {}, s = {})
    static final class C04392 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Continuation<? super Unit>, Object> $action;
        final /* synthetic */ MutatePriority $swipePriority;
        int label;
        final /* synthetic */ SwipeableV2State<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04392(SwipeableV2State<T> swipeableV2State, MutatePriority mutatePriority, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super C04392> continuation) {
            super(2, continuation);
            this.this$0 = swipeableV2State;
            this.$swipePriority = mutatePriority;
            this.$action = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04392(this.this$0, this.$swipePriority, this.$action, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04392) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (((SwipeableV2State) this.this$0).swipeMutex.mutate(this.$swipePriority, this.$action, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object swipe(MutatePriority swipePriority, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C04392(this, swipePriority, function1, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    public final boolean trySnapTo$material3_release(final T targetValue) {
        return this.swipeMutex.tryMutate(new Function0<Unit>(this) { // from class: androidx.compose.material3.SwipeableV2State$trySnapTo$1
            final /* synthetic */ SwipeableV2State<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.snap(targetValue);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void snap(T targetValue) {
        Float targetOffset = getAnchors$material3_release().get(targetValue);
        if (targetOffset != null) {
            float fFloatValue = targetOffset.floatValue();
            Float offset = getOffset();
            dispatchRawDelta(fFloatValue - (offset != null ? offset.floatValue() : 0.0f));
            setCurrentValue(targetValue);
            setAnimationTarget(null);
            return;
        }
        setCurrentValue(targetValue);
    }

    /* JADX INFO: compiled from: SwipeableV2.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0089\u0001\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0005\u0012\u0004\u0012\u0002H\u00060\u0004\"\b\b\u0001\u0010\u0006*\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\f0\u000b2,\u0010\r\u001a(\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\t0\u000e¢\u0006\u0002\b\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Landroidx/compose/material3/SwipeableV2State$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/SwipeableV2State;", "T", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmValueChange", "Lkotlin/Function1;", "", "positionalThreshold", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "distance", "Lkotlin/ExtensionFunctionType;", "velocityThreshold", "Landroidx/compose/ui/unit/Dp;", "Saver-eqLRuRQ", "(Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;F)Landroidx/compose/runtime/saveable/Saver;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: Saver-eqLRuRQ, reason: not valid java name */
        public final <T> Saver<SwipeableV2State<T>, T> m1825SavereqLRuRQ(final AnimationSpec<Float> animationSpec, final Function1<? super T, Boolean> confirmValueChange, final Function2<? super Density, ? super Float, Float> positionalThreshold, final float velocityThreshold) {
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            Intrinsics.checkNotNullParameter(confirmValueChange, "confirmValueChange");
            Intrinsics.checkNotNullParameter(positionalThreshold, "positionalThreshold");
            return SaverKt.Saver(new Function2<SaverScope, SwipeableV2State<T>, T>() { // from class: androidx.compose.material3.SwipeableV2State$Companion$Saver$1
                @Override // kotlin.jvm.functions.Function2
                public final T invoke(SaverScope Saver, SwipeableV2State<T> it) {
                    Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.getCurrentValue();
                }
            }, new Function1<T, SwipeableV2State<T>>() { // from class: androidx.compose.material3.SwipeableV2State$Companion$Saver$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final SwipeableV2State<T> invoke(T it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return new SwipeableV2State<>(it, animationSpec, confirmValueChange, positionalThreshold, velocityThreshold, null);
                }
            });
        }
    }
}
