package androidx.compose.material;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.core.app.NotificationCompat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: AnchoredDraggable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 u*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002tuBs\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\r\u0012#\b\u0002\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\u0002\u0010\u0011Jm\u0010S\u001a\u00020T2\u0006\u0010N\u001a\u00028\u00002\b\b\u0002\u0010U\u001a\u00020V2H\u0010W\u001aD\b\u0001\u0012\u0004\u0012\u00020\u0013\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0015¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020T0Y\u0012\u0006\u0012\u0004\u0018\u00010\u00020X¢\u0006\u0002\bZH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010[Je\u0010S\u001a\u00020T2\b\b\u0002\u0010U\u001a\u00020V2H\u0010W\u001aD\b\u0001\u0012\u0004\u0012\u00020\u0013\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0015¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020T0Y\u0012\u0006\u0012\u0004\u0018\u00010\u00020X¢\u0006\u0002\bZH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\\J%\u0010]\u001a\u00028\u00002\u0006\u0010D\u001a\u00020\u00062\u0006\u0010+\u001a\u00028\u00002\u0006\u0010^\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010_J\u001d\u0010`\u001a\u00028\u00002\u0006\u0010D\u001a\u00020\u00062\u0006\u0010+\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010aJ\u000e\u0010b\u001a\u00020\u00062\u0006\u0010c\u001a\u00020\u0006Jm\u0010d\u001a\u00020T2\b\u0010N\u001a\u0004\u0018\u00018\u00002\u0006\u0010U\u001a\u00020V2H\u0010W\u001aD\b\u0001\u0012\u0004\u0012\u00020\u0013\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0015¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020T0Y\u0012\u0006\u0012\u0004\u0018\u00010\u00020X¢\u0006\u0002\bZH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010[J\u0013\u0010e\u001a\u00020\u00102\u0006\u0010f\u001a\u00028\u0000¢\u0006\u0002\u0010gJ\u0015\u0010h\u001a\u00020\u00062\u0006\u0010c\u001a\u00020\u0006H\u0000¢\u0006\u0002\biJ\u0006\u0010j\u001a\u00020\u0006J\u0019\u0010k\u001a\u00020T2\u0006\u0010^\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010lJ\u0017\u0010m\u001a\u00020\u00102\u0006\u0010N\u001a\u00028\u0000H\u0000¢\u0006\u0004\bn\u0010gJ3\u0010o\u001a\u00020T2\u0012\u0010p\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u00152\u0010\b\u0002\u0010q\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010rH\u0000¢\u0006\u0002\bsR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000RC\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u00152\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u00158@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR/\u0010\u001f\u001a\u0004\u0018\u00018\u00002\b\u0010\u0014\u001a\u0004\u0018\u00018\u00008B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010\u001c\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001b\u0010%\u001a\u00028\u00008@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b&\u0010!R/\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R+\u0010+\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010\u001c\u001a\u0004\b,\u0010!\"\u0004\b-\u0010#R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\u000202X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0011\u00105\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b5\u00106R+\u00107\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00068F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001b\u0010>\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b@\u0010(\u001a\u0004\b?\u00109R\u001b\u0010A\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bC\u0010(\u001a\u0004\bB\u00109R1\u0010D\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00068F@BX\u0086\u008e\u0002¢\u0006\u0018\n\u0004\bI\u0010\u001c\u0012\u0004\bE\u0010F\u001a\u0004\bG\u00109\"\u0004\bH\u0010;R/\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010*R\u001b\u0010K\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bM\u0010(\u001a\u0004\bL\u00109R\u001b\u0010N\u001a\u00028\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bP\u0010(\u001a\u0004\bO\u0010!R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010R\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006v"}, d2 = {"Landroidx/compose/material/AnchoredDraggableState;", "T", "", "initialValue", "positionalThreshold", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "totalDistance", "velocityThreshold", "Lkotlin/Function0;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "confirmValueChange", "newValue", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;)V", "anchoredDragScope", "Landroidx/compose/material/AnchoredDragScope;", "<set-?>", "", "anchors", "getAnchors$material_release", "()Ljava/util/Map;", "setAnchors$material_release", "(Ljava/util/Map;)V", "anchors$delegate", "Landroidx/compose/runtime/MutableState;", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "animationTarget", "getAnimationTarget", "()Ljava/lang/Object;", "setAnimationTarget", "(Ljava/lang/Object;)V", "animationTarget$delegate", "closestValue", "getClosestValue$material_release", "closestValue$delegate", "Landroidx/compose/runtime/State;", "getConfirmValueChange$material_release", "()Lkotlin/jvm/functions/Function1;", "currentValue", "getCurrentValue", "setCurrentValue", "currentValue$delegate", "dragMutex", "Landroidx/compose/material/InternalMutatorMutex;", "draggableState", "Landroidx/compose/foundation/gestures/DraggableState;", "getDraggableState$material_release", "()Landroidx/compose/foundation/gestures/DraggableState;", "isAnimationRunning", "()Z", "lastVelocity", "getLastVelocity", "()F", "setLastVelocity", "(F)V", "lastVelocity$delegate", "Landroidx/compose/runtime/MutableFloatState;", "maxOffset", "getMaxOffset", "maxOffset$delegate", "minOffset", "getMinOffset", "minOffset$delegate", "offset", "getOffset$annotations", "()V", "getOffset", "setOffset", "offset$delegate", "getPositionalThreshold$material_release", NotificationCompat.CATEGORY_PROGRESS, "getProgress", "progress$delegate", "targetValue", "getTargetValue", "targetValue$delegate", "getVelocityThreshold$material_release", "()Lkotlin/jvm/functions/Function0;", "anchoredDrag", "", "dragPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "computeTarget", "velocity", "(FLjava/lang/Object;F)Ljava/lang/Object;", "computeTargetWithoutThresholds", "(FLjava/lang/Object;)Ljava/lang/Object;", "dispatchRawDelta", "delta", "doAnchoredDrag", "hasAnchorForValue", "value", "(Ljava/lang/Object;)Z", "newOffsetForDelta", "newOffsetForDelta$material_release", "requireOffset", "settle", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySnapTo", "trySnapTo$material_release", "updateAnchors", "newAnchors", "onAnchorsChanged", "Landroidx/compose/material/AnchoredDraggableState$AnchorChangedCallback;", "updateAnchors$material_release", "AnchorChangedCallback", "Companion", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AnchoredDraggableState<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AnchoredDragScope anchoredDragScope;

    /* JADX INFO: renamed from: anchors$delegate, reason: from kotlin metadata */
    private final MutableState anchors;
    private final AnimationSpec<Float> animationSpec;

    /* JADX INFO: renamed from: animationTarget$delegate, reason: from kotlin metadata */
    private final MutableState animationTarget;

    /* JADX INFO: renamed from: closestValue$delegate, reason: from kotlin metadata */
    private final State closestValue;
    private final Function1<T, Boolean> confirmValueChange;

    /* JADX INFO: renamed from: currentValue$delegate, reason: from kotlin metadata */
    private final MutableState currentValue;
    private final InternalMutatorMutex dragMutex;
    private final DraggableState draggableState;

    /* JADX INFO: renamed from: lastVelocity$delegate, reason: from kotlin metadata */
    private final MutableFloatState lastVelocity;

    /* JADX INFO: renamed from: maxOffset$delegate, reason: from kotlin metadata */
    private final State maxOffset;

    /* JADX INFO: renamed from: minOffset$delegate, reason: from kotlin metadata */
    private final State minOffset;

    /* JADX INFO: renamed from: offset$delegate, reason: from kotlin metadata */
    private final MutableState offset;
    private final Function1<Float, Float> positionalThreshold;

    /* JADX INFO: renamed from: progress$delegate, reason: from kotlin metadata */
    private final State progress;

    /* JADX INFO: renamed from: targetValue$delegate, reason: from kotlin metadata */
    private final State targetValue;
    private final Function0<Float> velocityThreshold;

    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0007\n\u0002\b\u0003\bç\u0080\u0001\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J=\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\b0\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\b0\u0007H&¢\u0006\u0002\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/material/AnchoredDraggableState$AnchorChangedCallback;", "T", "", "onAnchorsChanged", "", "previousTargetValue", "previousAnchors", "", "", "newAnchors", "(Ljava/lang/Object;Ljava/util/Map;Ljava/util/Map;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface AnchorChangedCallback<T> {
        void onAnchorsChanged(T previousTargetValue, Map<T, Float> previousAnchors, Map<T, Float> newAnchors);
    }

    public static /* synthetic */ void getOffset$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AnchoredDraggableState(T t, Function1<? super Float, Float> positionalThreshold, Function0<Float> velocityThreshold, AnimationSpec<Float> animationSpec, Function1<? super T, Boolean> confirmValueChange) {
        Intrinsics.checkNotNullParameter(positionalThreshold, "positionalThreshold");
        Intrinsics.checkNotNullParameter(velocityThreshold, "velocityThreshold");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(confirmValueChange, "confirmValueChange");
        this.positionalThreshold = positionalThreshold;
        this.velocityThreshold = velocityThreshold;
        this.animationSpec = animationSpec;
        this.confirmValueChange = confirmValueChange;
        this.dragMutex = new InternalMutatorMutex();
        this.draggableState = new AnchoredDraggableState$draggableState$1(this);
        this.currentValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
        this.targetValue = SnapshotStateKt.derivedStateOf(new Function0<T>(this) { // from class: androidx.compose.material.AnchoredDraggableState$targetValue$2
            final /* synthetic */ AnchoredDraggableState<T> this$0;

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
                AnchoredDraggableState<T> anchoredDraggableState = this.this$0;
                float offset = anchoredDraggableState.getOffset();
                if (!Float.isNaN(offset)) {
                    currentValue = (T) anchoredDraggableState.computeTarget(offset, anchoredDraggableState.getCurrentValue(), 0.0f);
                } else {
                    currentValue = anchoredDraggableState.getCurrentValue();
                }
                return currentValue;
            }
        });
        this.closestValue = SnapshotStateKt.derivedStateOf(new Function0<T>(this) { // from class: androidx.compose.material.AnchoredDraggableState$closestValue$2
            final /* synthetic */ AnchoredDraggableState<T> this$0;

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
                AnchoredDraggableState<T> anchoredDraggableState = this.this$0;
                float offset = anchoredDraggableState.getOffset();
                if (!Float.isNaN(offset)) {
                    currentValue = (T) anchoredDraggableState.computeTargetWithoutThresholds(offset, anchoredDraggableState.getCurrentValue());
                } else {
                    currentValue = anchoredDraggableState.getCurrentValue();
                }
                return currentValue;
            }
        });
        this.offset = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(Float.NaN), null, 2, null);
        this.progress = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Float>(this) { // from class: androidx.compose.material.AnchoredDraggableState$progress$2
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                Float f = (Float) this.this$0.getAnchors$material_release().get(this.this$0.getCurrentValue());
                float f2 = 0.0f;
                float a = f != null ? f.floatValue() : 0.0f;
                Float f3 = (Float) this.this$0.getAnchors$material_release().get(this.this$0.getClosestValue$material_release());
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
        this.lastVelocity = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.minOffset = SnapshotStateKt.derivedStateOf(new Function0<Float>(this) { // from class: androidx.compose.material.AnchoredDraggableState$minOffset$2
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                Float fMinOrNull = AnchoredDraggableKt.minOrNull(this.this$0.getAnchors$material_release());
                return Float.valueOf(fMinOrNull != null ? fMinOrNull.floatValue() : Float.NEGATIVE_INFINITY);
            }
        });
        this.maxOffset = SnapshotStateKt.derivedStateOf(new Function0<Float>(this) { // from class: androidx.compose.material.AnchoredDraggableState$maxOffset$2
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                Float fMaxOrNull = AnchoredDraggableKt.maxOrNull(this.this$0.getAnchors$material_release());
                return Float.valueOf(fMaxOrNull != null ? fMaxOrNull.floatValue() : Float.POSITIVE_INFINITY);
            }
        });
        this.animationTarget = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.anchors = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MapsKt.emptyMap(), null, 2, null);
        this.anchoredDragScope = new AnchoredDragScope(this) { // from class: androidx.compose.material.AnchoredDraggableState$anchoredDragScope$1
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // androidx.compose.material.AnchoredDragScope
            public void dragTo(float newOffset, float lastKnownVelocity) {
                this.this$0.setOffset(newOffset);
                this.this$0.setLastVelocity(lastKnownVelocity);
            }
        };
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ AnchoredDraggableState(Object obj, Function1 function1, Function0 function0, AnimationSpec animationSpec, Function1 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        AnimationSpec animationSpec2;
        Function1 function3;
        if ((i & 8) == 0) {
            animationSpec2 = animationSpec;
        } else {
            animationSpec2 = AnchoredDraggableDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i & 16) == 0) {
            function3 = function2;
        } else {
            function3 = new Function1<T, Boolean>() { // from class: androidx.compose.material.AnchoredDraggableState.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(T t) {
                    return true;
                }
            };
        }
        this(obj, function1, function0, animationSpec2, function3);
    }

    public final Function1<Float, Float> getPositionalThreshold$material_release() {
        return this.positionalThreshold;
    }

    public final Function0<Float> getVelocityThreshold$material_release() {
        return this.velocityThreshold;
    }

    public final AnimationSpec<Float> getAnimationSpec() {
        return this.animationSpec;
    }

    public final Function1<T, Boolean> getConfirmValueChange$material_release() {
        return this.confirmValueChange;
    }

    /* JADX INFO: renamed from: getDraggableState$material_release, reason: from getter */
    public final DraggableState getDraggableState() {
        return this.draggableState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentValue(T t) {
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

    public final T getClosestValue$material_release() {
        return (T) this.closestValue.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setOffset(float f) {
        MutableState $this$setValue$iv = this.offset;
        $this$setValue$iv.setValue(Float.valueOf(f));
    }

    public final float getOffset() {
        State $this$getValue$iv = this.offset;
        return ((Number) $this$getValue$iv.getValue()).floatValue();
    }

    public final float requireOffset() {
        if (!(!Float.isNaN(getOffset()))) {
            throw new IllegalStateException("The offset was read before being initialized. Did you access the offset in a phase before layout, like effects or composition?".toString());
        }
        return getOffset();
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
        MutableFloatState $this$setValue$iv = this.lastVelocity;
        $this$setValue$iv.setFloatValue(f);
    }

    public final float getLastVelocity() {
        FloatState $this$getValue$iv = this.lastVelocity;
        return $this$getValue$iv.getFloatValue();
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

    public final Map<T, Float> getAnchors$material_release() {
        State $this$getValue$iv = this.anchors;
        return (Map) $this$getValue$iv.getValue();
    }

    public final void setAnchors$material_release(Map<T, Float> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        MutableState $this$setValue$iv = this.anchors;
        $this$setValue$iv.setValue(map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateAnchors$material_release$default(AnchoredDraggableState anchoredDraggableState, Map map, AnchorChangedCallback anchorChangedCallback, int i, Object obj) {
        if ((i & 2) != 0) {
            anchorChangedCallback = null;
        }
        anchoredDraggableState.updateAnchors$material_release(map, anchorChangedCallback);
    }

    public final void updateAnchors$material_release(Map<T, Float> newAnchors, AnchorChangedCallback<T> onAnchorsChanged) {
        Intrinsics.checkNotNullParameter(newAnchors, "newAnchors");
        if (!Intrinsics.areEqual(getAnchors$material_release(), newAnchors)) {
            Map<T, Float> anchors$material_release = getAnchors$material_release();
            T targetValue = getTargetValue();
            boolean previousAnchorsEmpty = getAnchors$material_release().isEmpty();
            setAnchors$material_release(newAnchors);
            boolean currentValueHasAnchor = getAnchors$material_release().get(getCurrentValue()) != null;
            if (previousAnchorsEmpty && currentValueHasAnchor) {
                trySnapTo$material_release(getCurrentValue());
            } else if (onAnchorsChanged != null) {
                onAnchorsChanged.onAnchorsChanged(targetValue, anchors$material_release, newAnchors);
            }
        }
    }

    public final boolean hasAnchorForValue(T value) {
        return getAnchors$material_release().containsKey(value);
    }

    public final Object settle(float velocity, Continuation<? super Unit> continuation) {
        T currentValue = getCurrentValue();
        T tComputeTarget = computeTarget(requireOffset(), currentValue, velocity);
        if (this.confirmValueChange.invoke(tComputeTarget).booleanValue()) {
            Object objAnimateTo = AnchoredDraggableKt.animateTo(this, tComputeTarget, velocity, continuation);
            return objAnimateTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo : Unit.INSTANCE;
        }
        Object objAnimateTo2 = AnchoredDraggableKt.animateTo(this, currentValue, velocity, continuation);
        return objAnimateTo2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo2 : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final T computeTarget(float offset, T currentValue, float velocity) {
        Map<T, Float> anchors$material_release = getAnchors$material_release();
        Float f = anchors$material_release.get(currentValue);
        float fFloatValue = this.velocityThreshold.invoke().floatValue();
        if (!Intrinsics.areEqual(f, offset) && f != null) {
            if (f.floatValue() < offset) {
                if (velocity >= fFloatValue) {
                    return (T) AnchoredDraggableKt.closestAnchor(anchors$material_release, offset, true);
                }
                T t = (T) AnchoredDraggableKt.closestAnchor(anchors$material_release, offset, true);
                if (offset >= Math.abs(f.floatValue() + Math.abs(this.positionalThreshold.invoke(Float.valueOf(Math.abs(((Number) MapsKt.getValue(anchors$material_release, t)).floatValue() - f.floatValue()))).floatValue()))) {
                    return t;
                }
            } else {
                if (velocity <= (-fFloatValue)) {
                    return (T) AnchoredDraggableKt.closestAnchor(anchors$material_release, offset, false);
                }
                T t2 = (T) AnchoredDraggableKt.closestAnchor(anchors$material_release, offset, false);
                float fAbs = Math.abs(f.floatValue() - Math.abs(this.positionalThreshold.invoke(Float.valueOf(Math.abs(f.floatValue() - ((Number) MapsKt.getValue(anchors$material_release, t2)).floatValue()))).floatValue()));
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

    /* JADX INFO: Access modifiers changed from: private */
    public final T computeTargetWithoutThresholds(float offset, T currentValue) {
        Map<T, Float> anchors$material_release = getAnchors$material_release();
        Float f = anchors$material_release.get(currentValue);
        if (Intrinsics.areEqual(f, offset) || f == null) {
            return currentValue;
        }
        return f.floatValue() < offset ? (T) AnchoredDraggableKt.closestAnchor(anchors$material_release, offset, true) : (T) AnchoredDraggableKt.closestAnchor(anchors$material_release, offset, false);
    }

    public static /* synthetic */ Object anchoredDrag$default(AnchoredDraggableState anchoredDraggableState, MutatePriority mutatePriority, Function3 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return anchoredDraggableState.anchoredDrag(mutatePriority, function3, continuation);
    }

    public final Object anchoredDrag(MutatePriority dragPriority, Function3<? super AnchoredDragScope, ? super Map<T, Float>, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        Object objDoAnchoredDrag = doAnchoredDrag(null, dragPriority, function3, continuation);
        return objDoAnchoredDrag == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDoAnchoredDrag : Unit.INSTANCE;
    }

    public static /* synthetic */ Object anchoredDrag$default(AnchoredDraggableState anchoredDraggableState, Object obj, MutatePriority mutatePriority, Function3 function3, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return anchoredDraggableState.anchoredDrag(obj, mutatePriority, function3, continuation);
    }

    public final Object anchoredDrag(T t, MutatePriority dragPriority, Function3<? super AnchoredDragScope, ? super Map<T, Float>, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        Object objDoAnchoredDrag = doAnchoredDrag(t, dragPriority, function3, continuation);
        return objDoAnchoredDrag == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDoAnchoredDrag : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material.AnchoredDraggableState$doAnchoredDrag$2, reason: invalid class name */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.material.AnchoredDraggableState$doAnchoredDrag$2", f = "AnchoredDraggable.kt", i = {}, l = {441}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<AnchoredDragScope, Map<T, Float>, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ MutatePriority $dragPriority;
        final /* synthetic */ T $targetValue;
        int label;
        final /* synthetic */ AnchoredDraggableState<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(T t, AnchoredDraggableState<T> anchoredDraggableState, MutatePriority mutatePriority, Function3<? super AnchoredDragScope, ? super Map<T, Float>, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$targetValue = t;
            this.this$0 = anchoredDraggableState;
            this.$dragPriority = mutatePriority;
            this.$block = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$targetValue, this.this$0, this.$dragPriority, this.$block, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:26:0x0079  */
        /* JADX WARN: Code duplicated, block: B:30:0x0098  */
        /* JADX WARN: Code duplicated, block: B:32:0x00b8  */
        /* JADX WARN: Code duplicated, block: B:33:0x00ba  */
        /* JADX WARN: Code duplicated, block: B:39:0x00c4  */
        /* JADX WARN: Code duplicated, block: B:42:0x00cb  */
        /* JADX WARN: Code duplicated, block: B:52:0x00ee  */
        /* JADX WARN: Code duplicated, block: B:56:0x010d  */
        /* JADX WARN: Code duplicated, block: B:58:0x012d  */
        /* JADX WARN: Code duplicated, block: B:59:0x012f  */
        /* JADX WARN: Code duplicated, block: B:65:0x0139  */
        /* JADX WARN: Code duplicated, block: B:68:0x0140  */
        /* JADX WARN: Code duplicated, block: B:77:0x00be A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:81:0x0133 A[SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Throwable th;
            AnonymousClass2 anonymousClass2;
            AnchoredDraggableState<T> anchoredDraggableState;
            Iterator<T> it;
            T next;
            Object key;
            boolean z;
            AnchoredDraggableState<T> anchoredDraggableState2;
            Iterator<T> it2;
            T next2;
            Object key2;
            boolean z2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    if (this.$targetValue == null || this.this$0.getAnchors$material_release().containsKey(this.$targetValue)) {
                        try {
                            this.label = 1;
                            if (((AnchoredDraggableState) this.this$0).dragMutex.mutate(this.$dragPriority, new AnonymousClass1(this.$targetValue, this.this$0, this.$block, null), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass2 = this;
                            if (anonymousClass2.$targetValue != null) {
                                anonymousClass2.this$0.setAnimationTarget(null);
                            }
                            Set<Map.Entry<T, Float>> setEntrySet = anonymousClass2.this$0.getAnchors$material_release().entrySet();
                            anchoredDraggableState2 = anonymousClass2.this$0;
                            it2 = setEntrySet.iterator();
                            do {
                                if (it2.hasNext()) {
                                    next2 = it2.next();
                                    if (Math.abs(((Number) ((Map.Entry) next2).getValue()).floatValue() - anchoredDraggableState2.getOffset()) < 0.5f) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                } else {
                                    next2 = null;
                                }
                                Map.Entry entry = (Map.Entry) next2;
                                key2 = entry != null ? entry.getKey() : null;
                                if (key2 != null && ((Boolean) anonymousClass2.this$0.getConfirmValueChange$material_release().invoke(key2)).booleanValue()) {
                                    anonymousClass2.this$0.setCurrentValue(key2);
                                }
                            } while (!z2);
                            Map.Entry entry2 = (Map.Entry) next2;
                            key2 = entry2 != null ? entry2.getKey() : null;
                            if (key2 != null) {
                                anonymousClass2.this$0.setCurrentValue(key2);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            anonymousClass2 = this;
                            if (anonymousClass2.$targetValue != null) {
                                anonymousClass2.this$0.setAnimationTarget(null);
                            }
                            Set<Map.Entry<T, Float>> setEntrySet2 = anonymousClass2.this$0.getAnchors$material_release().entrySet();
                            anchoredDraggableState = anonymousClass2.this$0;
                            it = setEntrySet2.iterator();
                            do {
                                if (it.hasNext()) {
                                    next = it.next();
                                    if (Math.abs(((Number) ((Map.Entry) next).getValue()).floatValue() - anchoredDraggableState.getOffset()) < 0.5f) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                } else {
                                    next = null;
                                }
                                Map.Entry entry3 = (Map.Entry) next;
                                key = entry3 != null ? entry3.getKey() : null;
                                if (key != null && ((Boolean) anonymousClass2.this$0.getConfirmValueChange$material_release().invoke(key)).booleanValue()) {
                                    anonymousClass2.this$0.setCurrentValue(key);
                                }
                                throw th;
                            } while (!z);
                            Map.Entry entry4 = (Map.Entry) next;
                            key = entry4 != null ? entry4.getKey() : null;
                            if (key != null) {
                                anonymousClass2.this$0.setCurrentValue(key);
                            }
                            throw th;
                        }
                    } else if (this.this$0.getConfirmValueChange$material_release().invoke(this.$targetValue).booleanValue()) {
                        this.this$0.setCurrentValue(this.$targetValue);
                    }
                    return Unit.INSTANCE;
                case 1:
                    anonymousClass2 = this;
                    try {
                        ResultKt.throwOnFailure(obj);
                        if (anonymousClass2.$targetValue != null) {
                            anonymousClass2.this$0.setAnimationTarget(null);
                        }
                        Set<Map.Entry<T, Float>> setEntrySet3 = anonymousClass2.this$0.getAnchors$material_release().entrySet();
                        anchoredDraggableState2 = anonymousClass2.this$0;
                        it2 = setEntrySet3.iterator();
                        do {
                            if (it2.hasNext()) {
                                next2 = it2.next();
                                if (Math.abs(((Number) ((Map.Entry) next2).getValue()).floatValue() - anchoredDraggableState2.getOffset()) < 0.5f) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                            } else {
                                next2 = null;
                            }
                            Map.Entry entry5 = (Map.Entry) next2;
                            key2 = entry5 != null ? entry5.getKey() : null;
                            if (key2 != null) {
                                anonymousClass2.this$0.setCurrentValue(key2);
                            }
                            return Unit.INSTANCE;
                        } while (!z2);
                        Map.Entry entry6 = (Map.Entry) next2;
                        key2 = entry6 != null ? entry6.getKey() : null;
                        if (key2 != null) {
                            anonymousClass2.this$0.setCurrentValue(key2);
                        }
                        return Unit.INSTANCE;
                    } catch (Throwable th3) {
                        th = th3;
                        if (anonymousClass2.$targetValue != null) {
                            anonymousClass2.this$0.setAnimationTarget(null);
                        }
                        Set<Map.Entry<T, Float>> setEntrySet4 = anonymousClass2.this$0.getAnchors$material_release().entrySet();
                        anchoredDraggableState = anonymousClass2.this$0;
                        it = setEntrySet4.iterator();
                        do {
                            if (it.hasNext()) {
                                next = it.next();
                                if (Math.abs(((Number) ((Map.Entry) next).getValue()).floatValue() - anchoredDraggableState.getOffset()) < 0.5f) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                            } else {
                                next = null;
                            }
                            Map.Entry entry7 = (Map.Entry) next;
                            key = entry7 != null ? entry7.getKey() : null;
                            if (key != null) {
                                anonymousClass2.this$0.setCurrentValue(key);
                            }
                            throw th;
                        } while (!z);
                        Map.Entry entry8 = (Map.Entry) next;
                        key = entry8 != null ? entry8.getKey() : null;
                        if (key != null) {
                            anonymousClass2.this$0.setCurrentValue(key);
                        }
                        throw th;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material.AnchoredDraggableState$doAnchoredDrag$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: AnchoredDraggable.kt */
        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000H\u008a@"}, d2 = {"T", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        @DebugMetadata(c = "androidx.compose.material.AnchoredDraggableState$doAnchoredDrag$2$1", f = "AnchoredDraggable.kt", i = {}, l = {443}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
            final /* synthetic */ Function3<AnchoredDragScope, Map<T, Float>, Continuation<? super Unit>, Object> $block;
            final /* synthetic */ T $targetValue;
            int label;
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(T t, AnchoredDraggableState<T> anchoredDraggableState, Function3<? super AnchoredDragScope, ? super Map<T, Float>, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super AnonymousClass1> continuation) {
                super(1, continuation);
                this.$targetValue = t;
                this.this$0 = anchoredDraggableState;
                this.$block = function3;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Continuation<?> continuation) {
                return new AnonymousClass1(this.$targetValue, this.this$0, this.$block, continuation);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        T t = this.$targetValue;
                        if (t != null) {
                            this.this$0.setAnimationTarget(t);
                        }
                        Function3<AnchoredDragScope, Map<T, Float>, Continuation<? super Unit>, Object> function3 = this.$block;
                        AnchoredDragScope anchoredDragScope = ((AnchoredDraggableState) this.this$0).anchoredDragScope;
                        Map<T, Float> anchors$material_release = this.this$0.getAnchors$material_release();
                        this.label = 1;
                        if (function3.invoke(anchoredDragScope, anchors$material_release, this) == coroutine_suspended) {
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object doAnchoredDrag(T t, MutatePriority dragPriority, Function3<? super AnchoredDragScope, ? super Map<T, Float>, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(t, this, dragPriority, function3, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    public final float newOffsetForDelta$material_release(float delta) {
        return RangesKt.coerceIn((Float.isNaN(getOffset()) ? 0.0f : getOffset()) + delta, getMinOffset(), getMaxOffset());
    }

    public final float dispatchRawDelta(float delta) {
        float newOffset = newOffsetForDelta$material_release(delta);
        float oldOffset = Float.isNaN(getOffset()) ? 0.0f : getOffset();
        setOffset(newOffset);
        return newOffset - oldOffset;
    }

    public final boolean trySnapTo$material_release(final T targetValue) {
        return this.dragMutex.tryMutate(new Function0<Unit>(this) { // from class: androidx.compose.material.AnchoredDraggableState$trySnapTo$1
            final /* synthetic */ AnchoredDraggableState<T> this$0;

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
                AnchoredDragScope $this$invoke_u24lambda_u240 = ((AnchoredDraggableState) this.this$0).anchoredDragScope;
                AnchoredDraggableState<T> anchoredDraggableState = this.this$0;
                Object obj = targetValue;
                Float targetOffset = (Float) anchoredDraggableState.getAnchors$material_release().get(obj);
                if (targetOffset != null) {
                    AnchoredDragScope.CC.dragTo$default($this$invoke_u24lambda_u240, targetOffset.floatValue(), 0.0f, 2, null);
                    anchoredDraggableState.setAnimationTarget(null);
                }
                anchoredDraggableState.setCurrentValue(obj);
            }
        });
    }

    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jw\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0005\u0012\u0004\u0012\u0002H\u00060\u0004\"\b\b\u0001\u0010\u0006*\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\f0\u000b2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0012H\u0007¨\u0006\u0013"}, d2 = {"Landroidx/compose/material/AnchoredDraggableState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material/AnchoredDraggableState;", "T", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmValueChange", "Lkotlin/Function1;", "", "positionalThreshold", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "distance", "velocityThreshold", "Lkotlin/Function0;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> Saver<AnchoredDraggableState<T>, T> Saver(final AnimationSpec<Float> animationSpec, final Function1<? super T, Boolean> confirmValueChange, final Function1<? super Float, Float> positionalThreshold, final Function0<Float> velocityThreshold) {
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            Intrinsics.checkNotNullParameter(confirmValueChange, "confirmValueChange");
            Intrinsics.checkNotNullParameter(positionalThreshold, "positionalThreshold");
            Intrinsics.checkNotNullParameter(velocityThreshold, "velocityThreshold");
            return SaverKt.Saver(new Function2<SaverScope, AnchoredDraggableState<T>, T>() { // from class: androidx.compose.material.AnchoredDraggableState$Companion$Saver$1
                @Override // kotlin.jvm.functions.Function2
                public final T invoke(SaverScope Saver, AnchoredDraggableState<T> it) {
                    Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.getCurrentValue();
                }
            }, new Function1<T, AnchoredDraggableState<T>>() { // from class: androidx.compose.material.AnchoredDraggableState$Companion$Saver$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final AnchoredDraggableState<T> invoke(T it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return new AnchoredDraggableState<>(it, positionalThreshold, velocityThreshold, animationSpec, confirmValueChange);
                }
            });
        }
    }
}
