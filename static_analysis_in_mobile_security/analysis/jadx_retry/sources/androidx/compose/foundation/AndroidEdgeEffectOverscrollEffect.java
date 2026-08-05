package androidx.compose.foundation;

import android.content.Context;
import android.widget.EdgeEffect;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: AndroidOverscroll.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010-\u001a\u00020!H\u0002JE\u0010.\u001a\u00020!2\u0006\u0010/\u001a\u0002002\"\u00101\u001a\u001e\b\u0001\u0012\u0004\u0012\u000200\u0012\n\u0012\b\u0012\u0004\u0012\u00020003\u0012\u0006\u0012\u0004\u0018\u00010402H\u0096@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b5\u00106J9\u00107\u001a\u00020%2\u0006\u00108\u001a\u00020%2\u0006\u00109\u001a\u00020:2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%0\u001fH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b<\u0010=J\b\u0010>\u001a\u00020!H\u0002J%\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020%2\u0006\u0010B\u001a\u00020%H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bC\u0010DJ%\u0010E\u001a\u00020@2\u0006\u0010A\u001a\u00020%2\u0006\u0010B\u001a\u00020%H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bF\u0010DJ%\u0010G\u001a\u00020@2\u0006\u0010A\u001a\u00020%2\u0006\u0010B\u001a\u00020%H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bH\u0010DJ%\u0010I\u001a\u00020@2\u0006\u0010A\u001a\u00020%2\u0006\u0010B\u001a\u00020%H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bJ\u0010DJ\u001d\u0010K\u001a\u00020\u00142\u0006\u00108\u001a\u00020%H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bL\u0010MJ\b\u0010N\u001a\u00020\u0014H\u0002J \u0010O\u001a\u00020\u0014*\u00020P2\u0006\u0010Q\u001a\u00020\t2\n\u0010R\u001a\u00060Sj\u0002`TH\u0002J \u0010U\u001a\u00020\u0014*\u00020P2\u0006\u0010V\u001a\u00020\t2\n\u0010R\u001a\u00060Sj\u0002`TH\u0002J\n\u0010W\u001a\u00020!*\u00020PJ \u0010X\u001a\u00020\u0014*\u00020P2\u0006\u0010Y\u001a\u00020\t2\n\u0010R\u001a\u00060Sj\u0002`TH\u0002J \u0010Z\u001a\u00020\u0014*\u00020P2\u0006\u0010[\u001a\u00020\t2\n\u0010R\u001a\u00060Sj\u0002`TH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\u00020\rX\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u00020\u00148\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0018R\u000e\u0010\u001c\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001fX\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0002\n\u0000R\u0019\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020!0'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\\"}, d2 = {"Landroidx/compose/foundation/AndroidEdgeEffectOverscrollEffect;", "Landroidx/compose/foundation/OverscrollEffect;", "context", "Landroid/content/Context;", "overscrollConfig", "Landroidx/compose/foundation/OverscrollConfiguration;", "(Landroid/content/Context;Landroidx/compose/foundation/OverscrollConfiguration;)V", "allEffects", "", "Landroid/widget/EdgeEffect;", "bottomEffect", "bottomEffectNegation", "containerSize", "Landroidx/compose/ui/geometry/Size;", "J", "effectModifier", "Landroidx/compose/ui/Modifier;", "getEffectModifier", "()Landroidx/compose/ui/Modifier;", "invalidationEnabled", "", "getInvalidationEnabled$foundation_release$annotations", "()V", "getInvalidationEnabled$foundation_release", "()Z", "setInvalidationEnabled$foundation_release", "(Z)V", "isInProgress", "leftEffect", "leftEffectNegation", "onNewSize", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/IntSize;", "", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "redrawSignal", "Landroidx/compose/runtime/MutableState;", "rightEffect", "rightEffectNegation", "scrollCycleInProgress", "topEffect", "topEffectNegation", "animateToRelease", "applyToFling", "velocity", "Landroidx/compose/ui/unit/Velocity;", "performFling", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "applyToFling-BMRW4eQ", "(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "applyToScroll", "delta", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "performScroll", "applyToScroll-Rhakbz0", "(JILkotlin/jvm/functions/Function1;)J", "invalidateOverscroll", "pullBottom", "", "scroll", "displacement", "pullBottom-0a9Yr6o", "(JJ)F", "pullLeft", "pullLeft-0a9Yr6o", "pullRight", "pullRight-0a9Yr6o", "pullTop", "pullTop-0a9Yr6o", "releaseOppositeOverscroll", "releaseOppositeOverscroll-k-4lQ0M", "(J)Z", "stopOverscrollAnimation", "drawBottom", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "bottom", "canvas", "Landroid/graphics/Canvas;", "Landroidx/compose/ui/graphics/NativeCanvas;", "drawLeft", "left", "drawOverscroll", "drawRight", "right", "drawTop", "top", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidEdgeEffectOverscrollEffect implements OverscrollEffect {
    private final List<EdgeEffect> allEffects;
    private final EdgeEffect bottomEffect;
    private final EdgeEffect bottomEffectNegation;
    private long containerSize;
    private final Modifier effectModifier;
    private boolean invalidationEnabled;
    private final EdgeEffect leftEffect;
    private final EdgeEffect leftEffectNegation;
    private final Function1<IntSize, Unit> onNewSize;
    private final OverscrollConfiguration overscrollConfig;
    private PointerId pointerId;
    private Offset pointerPosition;
    private final MutableState<Unit> redrawSignal;
    private final EdgeEffect rightEffect;
    private final EdgeEffect rightEffectNegation;
    private boolean scrollCycleInProgress;
    private final EdgeEffect topEffect;
    private final EdgeEffect topEffectNegation;

    public static /* synthetic */ void getInvalidationEnabled$foundation_release$annotations() {
    }

    public AndroidEdgeEffectOverscrollEffect(Context context, OverscrollConfiguration overscrollConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(overscrollConfig, "overscrollConfig");
        this.overscrollConfig = overscrollConfig;
        EdgeEffect edgeEffectCreate = EdgeEffectCompat.INSTANCE.create(context, null);
        this.topEffect = edgeEffectCreate;
        EdgeEffect edgeEffectCreate2 = EdgeEffectCompat.INSTANCE.create(context, null);
        this.bottomEffect = edgeEffectCreate2;
        EdgeEffect edgeEffectCreate3 = EdgeEffectCompat.INSTANCE.create(context, null);
        this.leftEffect = edgeEffectCreate3;
        EdgeEffect edgeEffectCreate4 = EdgeEffectCompat.INSTANCE.create(context, null);
        this.rightEffect = edgeEffectCreate4;
        this.allEffects = CollectionsKt.listOf((Object[]) new EdgeEffect[]{edgeEffectCreate3, edgeEffectCreate, edgeEffectCreate4, edgeEffectCreate2});
        this.topEffectNegation = EdgeEffectCompat.INSTANCE.create(context, null);
        this.bottomEffectNegation = EdgeEffectCompat.INSTANCE.create(context, null);
        this.leftEffectNegation = EdgeEffectCompat.INSTANCE.create(context, null);
        this.rightEffectNegation = EdgeEffectCompat.INSTANCE.create(context, null);
        List<EdgeEffect> list = this.allEffects;
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            EdgeEffect it = (EdgeEffect) item$iv;
            it.setColor(ColorKt.m3025toArgb8_81llA(this.overscrollConfig.getGlowColor()));
        }
        this.redrawSignal = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
        this.invalidationEnabled = true;
        this.containerSize = Size.INSTANCE.m2809getZeroNHjbRc();
        Function1<IntSize, Unit> function1 = new Function1<IntSize, Unit>() { // from class: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$onNewSize$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                m156invokeozmzZPI(intSize.getPackedValue());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
            public final void m156invokeozmzZPI(long size2) {
                boolean differentSize = !Size.m2796equalsimpl0(IntSizeKt.m5444toSizeozmzZPI(size2), this.this$0.containerSize);
                this.this$0.containerSize = IntSizeKt.m5444toSizeozmzZPI(size2);
                if (differentSize) {
                    this.this$0.topEffect.setSize(IntSize.m5434getWidthimpl(size2), IntSize.m5433getHeightimpl(size2));
                    this.this$0.bottomEffect.setSize(IntSize.m5434getWidthimpl(size2), IntSize.m5433getHeightimpl(size2));
                    this.this$0.leftEffect.setSize(IntSize.m5433getHeightimpl(size2), IntSize.m5434getWidthimpl(size2));
                    this.this$0.rightEffect.setSize(IntSize.m5433getHeightimpl(size2), IntSize.m5434getWidthimpl(size2));
                    this.this$0.topEffectNegation.setSize(IntSize.m5434getWidthimpl(size2), IntSize.m5433getHeightimpl(size2));
                    this.this$0.bottomEffectNegation.setSize(IntSize.m5434getWidthimpl(size2), IntSize.m5433getHeightimpl(size2));
                    this.this$0.leftEffectNegation.setSize(IntSize.m5433getHeightimpl(size2), IntSize.m5434getWidthimpl(size2));
                    this.this$0.rightEffectNegation.setSize(IntSize.m5433getHeightimpl(size2), IntSize.m5434getWidthimpl(size2));
                }
                if (differentSize) {
                    this.this$0.invalidateOverscroll();
                    this.this$0.animateToRelease();
                }
            }
        };
        this.onNewSize = function1;
        this.effectModifier = OnRemeasuredModifierKt.onSizeChanged(SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE.then(AndroidOverscrollKt.StretchOverscrollNonClippingLayer), Unit.INSTANCE, new AndroidEdgeEffectOverscrollEffect$effectModifier$1(this, null)), function1).then(new DrawOverscrollModifier(this, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$special$$inlined$debugInspectorInfo$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                Intrinsics.checkNotNullParameter($this$null, "$this$null");
                $this$null.setName("overscroll");
                $this$null.setValue(this.this$0);
            }
        } : InspectableValueKt.getNoInspectorInfo()));
    }

    /* JADX INFO: renamed from: getInvalidationEnabled$foundation_release, reason: from getter */
    public final boolean getInvalidationEnabled() {
        return this.invalidationEnabled;
    }

    public final void setInvalidationEnabled$foundation_release(boolean z) {
        this.invalidationEnabled = z;
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToScroll-Rhakbz0, reason: not valid java name */
    public long mo155applyToScrollRhakbz0(long delta, int source, Function1<? super Offset, Offset> performScroll) {
        float consumedPixelsY;
        boolean appliedHorizontalOverscroll;
        boolean appliedVerticalOverscroll;
        Intrinsics.checkNotNullParameter(performScroll, "performScroll");
        if (Size.m2802isEmptyimpl(this.containerSize)) {
            return performScroll.invoke(Offset.m2720boximpl(delta)).getPackedValue();
        }
        if (!this.scrollCycleInProgress) {
            stopOverscrollAnimation();
            this.scrollCycleInProgress = true;
        }
        Offset offset = this.pointerPosition;
        long pointer = offset != null ? offset.getPackedValue() : SizeKt.m2810getCenteruvyYCjk(this.containerSize);
        float consumedPixelsX = 0.0f;
        if (Offset.m2732getYimpl(delta) == 0.0f) {
            consumedPixelsY = 0.0f;
        } else {
            if (EdgeEffectCompat.INSTANCE.getDistanceCompat(this.topEffect) == 0.0f) {
                if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.bottomEffect) == 0.0f)) {
                    consumedPixelsY = m149pullBottom0a9Yr6o(delta, pointer);
                    if (EdgeEffectCompat.INSTANCE.getDistanceCompat(this.bottomEffect) == 0.0f) {
                        this.bottomEffect.onRelease();
                    }
                } else {
                    consumedPixelsY = 0.0f;
                }
            } else {
                consumedPixelsY = m152pullTop0a9Yr6o(delta, pointer);
                if (EdgeEffectCompat.INSTANCE.getDistanceCompat(this.topEffect) == 0.0f) {
                    this.topEffect.onRelease();
                }
            }
        }
        if (!(Offset.m2731getXimpl(delta) == 0.0f)) {
            if (EdgeEffectCompat.INSTANCE.getDistanceCompat(this.leftEffect) == 0.0f) {
                if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.rightEffect) == 0.0f)) {
                    float fM151pullRight0a9Yr6o = m151pullRight0a9Yr6o(delta, pointer);
                    if (EdgeEffectCompat.INSTANCE.getDistanceCompat(this.rightEffect) == 0.0f) {
                        this.rightEffect.onRelease();
                    }
                    consumedPixelsX = fM151pullRight0a9Yr6o;
                }
            } else {
                float fM150pullLeft0a9Yr6o = m150pullLeft0a9Yr6o(delta, pointer);
                if (EdgeEffectCompat.INSTANCE.getDistanceCompat(this.leftEffect) == 0.0f) {
                    this.leftEffect.onRelease();
                }
                consumedPixelsX = fM150pullLeft0a9Yr6o;
            }
        }
        long consumedOffset = OffsetKt.Offset(consumedPixelsX, consumedPixelsY);
        if (!Offset.m2728equalsimpl0(consumedOffset, Offset.INSTANCE.m2747getZeroF1C5BW0())) {
            invalidateOverscroll();
        }
        long leftForDelta = Offset.m2735minusMKHz9U(delta, consumedOffset);
        long consumedByDelta = performScroll.invoke(Offset.m2720boximpl(leftForDelta)).getPackedValue();
        long leftForOverscroll = Offset.m2735minusMKHz9U(leftForDelta, consumedByDelta);
        boolean needsInvalidation = false;
        if (NestedScrollSource.m3997equalsimpl0(source, NestedScrollSource.INSTANCE.m4002getDragWNlRxjI())) {
            if (Offset.m2731getXimpl(leftForOverscroll) > 0.5f) {
                m150pullLeft0a9Yr6o(leftForOverscroll, pointer);
                appliedHorizontalOverscroll = true;
            } else if (Offset.m2731getXimpl(leftForOverscroll) < -0.5f) {
                m151pullRight0a9Yr6o(leftForOverscroll, pointer);
                appliedHorizontalOverscroll = true;
            } else {
                appliedHorizontalOverscroll = false;
            }
            if (Offset.m2732getYimpl(leftForOverscroll) > 0.5f) {
                m152pullTop0a9Yr6o(leftForOverscroll, pointer);
                appliedVerticalOverscroll = true;
            } else if (Offset.m2732getYimpl(leftForOverscroll) < -0.5f) {
                m149pullBottom0a9Yr6o(leftForOverscroll, pointer);
                appliedVerticalOverscroll = true;
            } else {
                appliedVerticalOverscroll = false;
            }
            needsInvalidation = appliedHorizontalOverscroll || appliedVerticalOverscroll;
        }
        boolean appliedHorizontalOverscroll2 = m153releaseOppositeOverscrollk4lQ0M(delta);
        boolean needsInvalidation2 = appliedHorizontalOverscroll2 || needsInvalidation;
        if (needsInvalidation2) {
            invalidateOverscroll();
        }
        return Offset.m2736plusMKHz9U(consumedOffset, consumedByDelta);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x008a  */
    /* JADX WARN: Code duplicated, block: B:31:0x0092  */
    /* JADX WARN: Code duplicated, block: B:33:0x009e  */
    /* JADX WARN: Code duplicated, block: B:34:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:36:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:37:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:46:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:48:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:51:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:53:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:54:0x0114  */
    /* JADX WARN: Code duplicated, block: B:63:0x0153  */
    /* JADX WARN: Code duplicated, block: B:64:0x0163  */
    /* JADX WARN: Code duplicated, block: B:66:0x016b  */
    /* JADX WARN: Code duplicated, block: B:69:0x0183  */
    /* JADX WARN: Code duplicated, block: B:70:0x0193  */
    /* JADX WARN: Code duplicated, block: B:72:0x019b  */
    /* JADX WARN: Code duplicated, block: B:75:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToFling-BMRW4eQ, reason: not valid java name */
    public Object mo154applyToFlingBMRW4eQ(long velocity, Function2<? super Velocity, ? super Continuation<? super Velocity>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        AndroidEdgeEffectOverscrollEffect$applyToFling$1 androidEdgeEffectOverscrollEffect$applyToFling$1;
        AndroidEdgeEffectOverscrollEffect androidEdgeEffectOverscrollEffect;
        float consumedX;
        boolean z;
        float consumedY;
        long remainingVelocity;
        Object objInvoke;
        long remainingVelocity2;
        if (continuation instanceof AndroidEdgeEffectOverscrollEffect$applyToFling$1) {
            androidEdgeEffectOverscrollEffect$applyToFling$1 = (AndroidEdgeEffectOverscrollEffect$applyToFling$1) continuation;
            if ((androidEdgeEffectOverscrollEffect$applyToFling$1.label & Integer.MIN_VALUE) != 0) {
                androidEdgeEffectOverscrollEffect$applyToFling$1.label -= Integer.MIN_VALUE;
            } else {
                androidEdgeEffectOverscrollEffect$applyToFling$1 = new AndroidEdgeEffectOverscrollEffect$applyToFling$1(this, continuation);
            }
        } else {
            androidEdgeEffectOverscrollEffect$applyToFling$1 = new AndroidEdgeEffectOverscrollEffect$applyToFling$1(this, continuation);
        }
        AndroidEdgeEffectOverscrollEffect$applyToFling$1 androidEdgeEffectOverscrollEffect$applyToFling$2 = androidEdgeEffectOverscrollEffect$applyToFling$1;
        Object $result = androidEdgeEffectOverscrollEffect$applyToFling$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (androidEdgeEffectOverscrollEffect$applyToFling$2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                androidEdgeEffectOverscrollEffect = this;
                if (Size.m2802isEmptyimpl(androidEdgeEffectOverscrollEffect.containerSize)) {
                    Velocity velocityM5490boximpl = Velocity.m5490boximpl(velocity);
                    androidEdgeEffectOverscrollEffect$applyToFling$2.label = 1;
                    if (function2.invoke(velocityM5490boximpl, androidEdgeEffectOverscrollEffect$applyToFling$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                if (Velocity.m5499getXimpl(velocity) > 0.0f) {
                    if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(androidEdgeEffectOverscrollEffect.leftEffect) == 0.0f)) {
                        EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.leftEffect, MathKt.roundToInt(Velocity.m5499getXimpl(velocity)));
                        consumedX = Velocity.m5499getXimpl(velocity);
                    } else if (Velocity.m5499getXimpl(velocity) >= 0.0f) {
                        consumedX = 0.0f;
                    } else {
                        if (EdgeEffectCompat.INSTANCE.getDistanceCompat(androidEdgeEffectOverscrollEffect.rightEffect) == 0.0f) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z) {
                            EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.rightEffect, -MathKt.roundToInt(Velocity.m5499getXimpl(velocity)));
                            consumedX = Velocity.m5499getXimpl(velocity);
                        } else {
                            consumedX = 0.0f;
                        }
                    }
                } else if (Velocity.m5499getXimpl(velocity) >= 0.0f) {
                    consumedX = 0.0f;
                } else {
                    if (EdgeEffectCompat.INSTANCE.getDistanceCompat(androidEdgeEffectOverscrollEffect.rightEffect) == 0.0f) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.rightEffect, -MathKt.roundToInt(Velocity.m5499getXimpl(velocity)));
                        consumedX = Velocity.m5499getXimpl(velocity);
                    } else {
                        consumedX = 0.0f;
                    }
                }
                if (Velocity.m5500getYimpl(velocity) > 0.0f) {
                    if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(androidEdgeEffectOverscrollEffect.topEffect) == 0.0f)) {
                        EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.topEffect, MathKt.roundToInt(Velocity.m5500getYimpl(velocity)));
                        consumedY = Velocity.m5500getYimpl(velocity);
                    } else if (Velocity.m5500getYimpl(velocity) >= 0.0f) {
                        consumedY = 0.0f;
                    } else {
                        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(androidEdgeEffectOverscrollEffect.bottomEffect) == 0.0f)) {
                            EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.bottomEffect, -MathKt.roundToInt(Velocity.m5500getYimpl(velocity)));
                            consumedY = Velocity.m5500getYimpl(velocity);
                        } else {
                            consumedY = 0.0f;
                        }
                    }
                } else if (Velocity.m5500getYimpl(velocity) >= 0.0f) {
                    consumedY = 0.0f;
                } else {
                    if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(androidEdgeEffectOverscrollEffect.bottomEffect) == 0.0f)) {
                        EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.bottomEffect, -MathKt.roundToInt(Velocity.m5500getYimpl(velocity)));
                        consumedY = Velocity.m5500getYimpl(velocity);
                    } else {
                        consumedY = 0.0f;
                    }
                }
                long consumed = VelocityKt.Velocity(consumedX, consumedY);
                if (!Velocity.m5498equalsimpl0(consumed, Velocity.INSTANCE.m5510getZero9UxMQ8M())) {
                    androidEdgeEffectOverscrollEffect.invalidateOverscroll();
                }
                remainingVelocity = Velocity.m5502minusAH228Gc(velocity, consumed);
                Velocity velocityM5490boximpl2 = Velocity.m5490boximpl(remainingVelocity);
                androidEdgeEffectOverscrollEffect$applyToFling$2.L$0 = androidEdgeEffectOverscrollEffect;
                androidEdgeEffectOverscrollEffect$applyToFling$2.J$0 = remainingVelocity;
                androidEdgeEffectOverscrollEffect$applyToFling$2.label = 2;
                objInvoke = function2.invoke(velocityM5490boximpl2, androidEdgeEffectOverscrollEffect$applyToFling$2);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                long consumedByVelocity = ((Velocity) objInvoke).getPackedValue();
                remainingVelocity2 = Velocity.m5502minusAH228Gc(remainingVelocity, consumedByVelocity);
                androidEdgeEffectOverscrollEffect.scrollCycleInProgress = false;
                if (Velocity.m5499getXimpl(remainingVelocity2) > 0.0f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.leftEffect, MathKt.roundToInt(Velocity.m5499getXimpl(remainingVelocity2)));
                } else if (Velocity.m5499getXimpl(remainingVelocity2) < 0.0f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.rightEffect, -MathKt.roundToInt(Velocity.m5499getXimpl(remainingVelocity2)));
                }
                if (Velocity.m5500getYimpl(remainingVelocity2) > 0.0f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.topEffect, MathKt.roundToInt(Velocity.m5500getYimpl(remainingVelocity2)));
                } else if (Velocity.m5500getYimpl(remainingVelocity2) < 0.0f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.bottomEffect, -MathKt.roundToInt(Velocity.m5500getYimpl(remainingVelocity2)));
                }
                if (!Velocity.m5498equalsimpl0(remainingVelocity2, Velocity.INSTANCE.m5510getZero9UxMQ8M())) {
                    androidEdgeEffectOverscrollEffect.invalidateOverscroll();
                }
                androidEdgeEffectOverscrollEffect.animateToRelease();
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            case 2:
                remainingVelocity = androidEdgeEffectOverscrollEffect$applyToFling$2.J$0;
                AndroidEdgeEffectOverscrollEffect androidEdgeEffectOverscrollEffect2 = (AndroidEdgeEffectOverscrollEffect) androidEdgeEffectOverscrollEffect$applyToFling$2.L$0;
                ResultKt.throwOnFailure($result);
                androidEdgeEffectOverscrollEffect = androidEdgeEffectOverscrollEffect2;
                objInvoke = $result;
                long consumedByVelocity2 = ((Velocity) objInvoke).getPackedValue();
                remainingVelocity2 = Velocity.m5502minusAH228Gc(remainingVelocity, consumedByVelocity2);
                androidEdgeEffectOverscrollEffect.scrollCycleInProgress = false;
                if (Velocity.m5499getXimpl(remainingVelocity2) > 0.0f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.leftEffect, MathKt.roundToInt(Velocity.m5499getXimpl(remainingVelocity2)));
                } else if (Velocity.m5499getXimpl(remainingVelocity2) < 0.0f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.rightEffect, -MathKt.roundToInt(Velocity.m5499getXimpl(remainingVelocity2)));
                }
                if (Velocity.m5500getYimpl(remainingVelocity2) > 0.0f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.topEffect, MathKt.roundToInt(Velocity.m5500getYimpl(remainingVelocity2)));
                } else if (Velocity.m5500getYimpl(remainingVelocity2) < 0.0f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.bottomEffect, -MathKt.roundToInt(Velocity.m5500getYimpl(remainingVelocity2)));
                }
                if (!Velocity.m5498equalsimpl0(remainingVelocity2, Velocity.INSTANCE.m5510getZero9UxMQ8M())) {
                    androidEdgeEffectOverscrollEffect.invalidateOverscroll();
                }
                androidEdgeEffectOverscrollEffect.animateToRelease();
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public boolean isInProgress() {
        List<EdgeEffect> list = this.allEffects;
        int index$iv$iv = 0;
        int size = list.size();
        while (true) {
            if (index$iv$iv >= size) {
                return false;
            }
            Object item$iv$iv = list.get(index$iv$iv);
            EdgeEffect it = (EdgeEffect) item$iv$iv;
            if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(it) == 0.0f)) {
                return true;
            }
            index$iv$iv++;
        }
    }

    private final boolean stopOverscrollAnimation() {
        boolean stopped = false;
        long fakeDisplacement = SizeKt.m2810getCenteruvyYCjk(this.containerSize);
        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.leftEffect) == 0.0f)) {
            m150pullLeft0a9Yr6o(Offset.INSTANCE.m2747getZeroF1C5BW0(), fakeDisplacement);
            stopped = true;
        }
        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.rightEffect) == 0.0f)) {
            m151pullRight0a9Yr6o(Offset.INSTANCE.m2747getZeroF1C5BW0(), fakeDisplacement);
            stopped = true;
        }
        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.topEffect) == 0.0f)) {
            m152pullTop0a9Yr6o(Offset.INSTANCE.m2747getZeroF1C5BW0(), fakeDisplacement);
            stopped = true;
        }
        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.bottomEffect) == 0.0f)) {
            m149pullBottom0a9Yr6o(Offset.INSTANCE.m2747getZeroF1C5BW0(), fakeDisplacement);
            return true;
        }
        return stopped;
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public Modifier getEffectModifier() {
        return this.effectModifier;
    }

    public final void drawOverscroll(DrawScope $this$drawOverscroll) {
        Intrinsics.checkNotNullParameter($this$drawOverscroll, "<this>");
        if (Size.m2802isEmptyimpl(this.containerSize)) {
            return;
        }
        Canvas it = $this$drawOverscroll.getDrawContext().getCanvas();
        this.redrawSignal.getValue();
        android.graphics.Canvas canvas = AndroidCanvas_androidKt.getNativeCanvas(it);
        boolean needsInvalidate = false;
        boolean z = true;
        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.leftEffectNegation) == 0.0f)) {
            drawRight($this$drawOverscroll, this.leftEffectNegation, canvas);
            this.leftEffectNegation.finish();
        }
        if (!this.leftEffect.isFinished()) {
            needsInvalidate = drawLeft($this$drawOverscroll, this.leftEffect, canvas);
            EdgeEffectCompat.INSTANCE.onPullDistanceCompat(this.leftEffectNegation, EdgeEffectCompat.INSTANCE.getDistanceCompat(this.leftEffect), 0.0f);
        }
        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.topEffectNegation) == 0.0f)) {
            drawBottom($this$drawOverscroll, this.topEffectNegation, canvas);
            this.topEffectNegation.finish();
        }
        if (!this.topEffect.isFinished()) {
            needsInvalidate = drawTop($this$drawOverscroll, this.topEffect, canvas) || needsInvalidate;
            EdgeEffectCompat.INSTANCE.onPullDistanceCompat(this.topEffectNegation, EdgeEffectCompat.INSTANCE.getDistanceCompat(this.topEffect), 0.0f);
        }
        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.rightEffectNegation) == 0.0f)) {
            drawLeft($this$drawOverscroll, this.rightEffectNegation, canvas);
            this.rightEffectNegation.finish();
        }
        if (!this.rightEffect.isFinished()) {
            needsInvalidate = drawRight($this$drawOverscroll, this.rightEffect, canvas) || needsInvalidate;
            EdgeEffectCompat.INSTANCE.onPullDistanceCompat(this.rightEffectNegation, EdgeEffectCompat.INSTANCE.getDistanceCompat(this.rightEffect), 0.0f);
        }
        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.bottomEffectNegation) == 0.0f)) {
            drawTop($this$drawOverscroll, this.bottomEffectNegation, canvas);
            this.bottomEffectNegation.finish();
        }
        if (!this.bottomEffect.isFinished()) {
            if (!drawBottom($this$drawOverscroll, this.bottomEffect, canvas) && !needsInvalidate) {
                z = false;
            }
            needsInvalidate = z;
            EdgeEffectCompat.INSTANCE.onPullDistanceCompat(this.bottomEffectNegation, EdgeEffectCompat.INSTANCE.getDistanceCompat(this.bottomEffect), 0.0f);
        }
        if (needsInvalidate) {
            invalidateOverscroll();
        }
    }

    private final boolean drawLeft(DrawScope $this$drawLeft, EdgeEffect left, android.graphics.Canvas canvas) {
        int restore = canvas.save();
        canvas.rotate(270.0f);
        canvas.translate(-Size.m2797getHeightimpl(this.containerSize), $this$drawLeft.mo327toPx0680j_4(this.overscrollConfig.getDrawPadding().mo437calculateLeftPaddingu2uoSUM($this$drawLeft.getLayoutDirection())));
        boolean needsInvalidate = left.draw(canvas);
        canvas.restoreToCount(restore);
        return needsInvalidate;
    }

    private final boolean drawTop(DrawScope $this$drawTop, EdgeEffect top, android.graphics.Canvas canvas) {
        int restore = canvas.save();
        canvas.translate(0.0f, $this$drawTop.mo327toPx0680j_4(this.overscrollConfig.getDrawPadding().getTop()));
        boolean needsInvalidate = top.draw(canvas);
        canvas.restoreToCount(restore);
        return needsInvalidate;
    }

    private final boolean drawRight(DrawScope $this$drawRight, EdgeEffect right, android.graphics.Canvas canvas) {
        int restore = canvas.save();
        int width = MathKt.roundToInt(Size.m2800getWidthimpl(this.containerSize));
        float rightPadding = this.overscrollConfig.getDrawPadding().mo438calculateRightPaddingu2uoSUM($this$drawRight.getLayoutDirection());
        canvas.rotate(90.0f);
        canvas.translate(0.0f, (-width) + $this$drawRight.mo327toPx0680j_4(rightPadding));
        boolean needsInvalidate = right.draw(canvas);
        canvas.restoreToCount(restore);
        return needsInvalidate;
    }

    private final boolean drawBottom(DrawScope $this$drawBottom, EdgeEffect bottom, android.graphics.Canvas canvas) {
        int restore = canvas.save();
        canvas.rotate(180.0f);
        float bottomPadding = $this$drawBottom.mo327toPx0680j_4(this.overscrollConfig.getDrawPadding().getBottom());
        canvas.translate(-Size.m2800getWidthimpl(this.containerSize), (-Size.m2797getHeightimpl(this.containerSize)) + bottomPadding);
        boolean needsInvalidate = bottom.draw(canvas);
        canvas.restoreToCount(restore);
        return needsInvalidate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invalidateOverscroll() {
        if (this.invalidationEnabled) {
            this.redrawSignal.setValue(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void animateToRelease() {
        boolean needsInvalidation = false;
        List<EdgeEffect> list = this.allEffects;
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            EdgeEffect it = (EdgeEffect) item$iv;
            it.onRelease();
            needsInvalidation = it.isFinished() || needsInvalidation;
        }
        if (needsInvalidation) {
            invalidateOverscroll();
        }
    }

    /* JADX INFO: renamed from: releaseOppositeOverscroll-k-4lQ0M, reason: not valid java name */
    private final boolean m153releaseOppositeOverscrollk4lQ0M(long delta) {
        boolean needsInvalidation = false;
        if (!this.leftEffect.isFinished() && Offset.m2731getXimpl(delta) < 0.0f) {
            EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.leftEffect, Offset.m2731getXimpl(delta));
            needsInvalidation = this.leftEffect.isFinished();
        }
        if (!this.rightEffect.isFinished() && Offset.m2731getXimpl(delta) > 0.0f) {
            EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.rightEffect, Offset.m2731getXimpl(delta));
            needsInvalidation = needsInvalidation || this.rightEffect.isFinished();
        }
        if (!this.topEffect.isFinished() && Offset.m2732getYimpl(delta) < 0.0f) {
            EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.topEffect, Offset.m2732getYimpl(delta));
            needsInvalidation = needsInvalidation || this.topEffect.isFinished();
        }
        if (!this.bottomEffect.isFinished() && Offset.m2732getYimpl(delta) > 0.0f) {
            EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.bottomEffect, Offset.m2732getYimpl(delta));
            return needsInvalidation || this.bottomEffect.isFinished();
        }
        return needsInvalidation;
    }

    /* JADX INFO: renamed from: pullTop-0a9Yr6o, reason: not valid java name */
    private final float m152pullTop0a9Yr6o(long scroll, long displacement) {
        float displacementX = Offset.m2731getXimpl(displacement) / Size.m2800getWidthimpl(this.containerSize);
        float pullY = Offset.m2732getYimpl(scroll) / Size.m2797getHeightimpl(this.containerSize);
        float consumed = EdgeEffectCompat.INSTANCE.onPullDistanceCompat(this.topEffect, pullY, displacementX) * Size.m2797getHeightimpl(this.containerSize);
        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.topEffect) == 0.0f)) {
            return Offset.m2732getYimpl(scroll);
        }
        return consumed;
    }

    /* JADX INFO: renamed from: pullBottom-0a9Yr6o, reason: not valid java name */
    private final float m149pullBottom0a9Yr6o(long scroll, long displacement) {
        float displacementX = Offset.m2731getXimpl(displacement) / Size.m2800getWidthimpl(this.containerSize);
        float pullY = Offset.m2732getYimpl(scroll) / Size.m2797getHeightimpl(this.containerSize);
        float consumed = (-EdgeEffectCompat.INSTANCE.onPullDistanceCompat(this.bottomEffect, -pullY, 1 - displacementX)) * Size.m2797getHeightimpl(this.containerSize);
        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.bottomEffect) == 0.0f)) {
            return Offset.m2732getYimpl(scroll);
        }
        return consumed;
    }

    /* JADX INFO: renamed from: pullLeft-0a9Yr6o, reason: not valid java name */
    private final float m150pullLeft0a9Yr6o(long scroll, long displacement) {
        float displacementY = Offset.m2732getYimpl(displacement) / Size.m2797getHeightimpl(this.containerSize);
        float pullX = Offset.m2731getXimpl(scroll) / Size.m2800getWidthimpl(this.containerSize);
        float consumed = EdgeEffectCompat.INSTANCE.onPullDistanceCompat(this.leftEffect, pullX, 1 - displacementY) * Size.m2800getWidthimpl(this.containerSize);
        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.leftEffect) == 0.0f)) {
            return Offset.m2731getXimpl(scroll);
        }
        return consumed;
    }

    /* JADX INFO: renamed from: pullRight-0a9Yr6o, reason: not valid java name */
    private final float m151pullRight0a9Yr6o(long scroll, long displacement) {
        float displacementY = Offset.m2732getYimpl(displacement) / Size.m2797getHeightimpl(this.containerSize);
        float pullX = Offset.m2731getXimpl(scroll) / Size.m2800getWidthimpl(this.containerSize);
        float consumed = (-EdgeEffectCompat.INSTANCE.onPullDistanceCompat(this.rightEffect, -pullX, displacementY)) * Size.m2800getWidthimpl(this.containerSize);
        if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(this.rightEffect) == 0.0f)) {
            return Offset.m2731getXimpl(scroll);
        }
        return consumed;
    }
}
