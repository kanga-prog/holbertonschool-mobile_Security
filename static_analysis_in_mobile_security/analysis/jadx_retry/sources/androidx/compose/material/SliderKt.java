package androidx.compose.material;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.GestureCancellationException;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PointMode;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.app.NotificationCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: Slider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aS\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0003¢\u0006\u0002\u0010\u001b\u001a\u007f\u0010\u001c\u001a\u00020\u00122\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0016\u0012\u0004\u0012\u00020\u00120\u00142\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\b\b\u0002\u0010!\u001a\u00020\"2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010$2\b\b\u0002\u0010%\u001a\u00020&H\u0007¢\u0006\u0002\u0010'\u001ak\u0010(\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010%\u001a\u00020&2\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u0001H\u0003¢\u0006\u0002\u00103\u001a}\u00104\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00142\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\b\b\u0002\u0010!\u001a\u00020\"2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010$2\b\b\u0002\u00105\u001a\u00020/2\b\b\u0002\u0010%\u001a\u00020&H\u0007¢\u0006\u0002\u00106\u001aK\u00107\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u00108\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010%\u001a\u00020&2\u0006\u0010-\u001a\u00020\b2\u0006\u00105\u001a\u00020/2\u0006\u0010\u001e\u001a\u00020\u0001H\u0003¢\u0006\u0002\u00109\u001aS\u0010:\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010;\u001a\u00020\b2\u0006\u0010<\u001a\u00020\bH\u0003¢\u0006\u0002\u0010=\u001a1\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\b2\u0006\u0010B\u001a\u00020\b2\u0006\u0010C\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010D\u001a \u0010E\u001a\u00020\b2\u0006\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020\b2\u0006\u0010H\u001a\u00020\bH\u0002\u001a0\u0010I\u001a\u00020\b2\u0006\u0010J\u001a\u00020\b2\u0006\u0010K\u001a\u00020\b2\u0006\u0010L\u001a\u00020\b2\u0006\u0010M\u001a\u00020\b2\u0006\u0010N\u001a\u00020\bH\u0002\u001a<\u0010I\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010J\u001a\u00020\b2\u0006\u0010K\u001a\u00020\b2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010M\u001a\u00020\b2\u0006\u0010N\u001a\u00020\bH\u0002\u001a.\u0010P\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010Q\u001a\u00020\b2\u0006\u0010R\u001a\u00020\bH\u0002\u001a\u0016\u0010S\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010!\u001a\u00020\"H\u0002\u001aI\u0010T\u001a\u00020\u0012*\u00020U2\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010V\u001a\u00020\u00032\u0006\u00105\u001a\u00020/2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010W\u001a\u00020\u0003H\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bX\u0010Y\u001a;\u0010Z\u001a\u0010\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020\b\u0018\u00010[*\u00020]2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020aH\u0082@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\bb\u0010c\u001a\u0098\u0001\u0010d\u001a\u00020\u0001*\u00020\u00012\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\b0f2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\b0f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010h\u001a\u00020 2\u0006\u0010R\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0018\u0010i\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00120\u00140f2\u001e\u0010j\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120k0fH\u0002\u001a\\\u0010l\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020 2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00142\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010$2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\b\b\u0002\u0010!\u001a\u00020\"H\u0002\u001aj\u0010m\u001a\u00020\u0001*\u00020\u00012\u0006\u0010?\u001a\u00020@2\u0006\u00105\u001a\u00020/2\u0006\u0010R\u001a\u00020\b2\u0006\u0010h\u001a\u00020 2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020\b0f2\u0018\u0010i\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00140f2\f\u0010o\u001a\b\u0012\u0004\u0012\u00020\b0\u00192\u0006\u0010\u001f\u001a\u00020 H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\u0005\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\t\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\n\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0019\u0010\u000b\u001a\u00020\u0003X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\f\u0010\r\"\u0013\u0010\u000e\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0019\u0010\u000f\u001a\u00020\u0003X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0010\u0010\r\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006p"}, d2 = {"DefaultSliderConstraints", "Landroidx/compose/ui/Modifier;", "SliderHeight", "Landroidx/compose/ui/unit/Dp;", "F", "SliderMinWidth", "SliderToTickAnimation", "Landroidx/compose/animation/core/TweenSpec;", "", "ThumbDefaultElevation", "ThumbPressedElevation", "ThumbRadius", "getThumbRadius", "()F", "ThumbRippleRadius", "TrackHeight", "getTrackHeight", "CorrectValueSideEffect", "", "scaleToOffset", "Lkotlin/Function1;", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "trackRange", "valueState", "Landroidx/compose/runtime/MutableState;", "value", "(Lkotlin/jvm/functions/Function1;Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/ranges/ClosedFloatingPointRange;Landroidx/compose/runtime/MutableState;FLandroidx/compose/runtime/Composer;I)V", "RangeSlider", "onValueChange", "modifier", "enabled", "", "steps", "", "onValueChangeFinished", "Lkotlin/Function0;", "colors", "Landroidx/compose/material/SliderColors;", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material/SliderColors;Landroidx/compose/runtime/Composer;II)V", "RangeSliderImpl", "positionFractionStart", "positionFractionEnd", "tickFractions", "", "width", "startInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "endInteractionSource", "startThumbSemantics", "endThumbSemantics", "(ZFFLjava/util/List;Landroidx/compose/material/SliderColors;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Slider", "interactionSource", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SliderColors;Landroidx/compose/runtime/Composer;II)V", "SliderImpl", "positionFraction", "(ZFLjava/util/List;Landroidx/compose/material/SliderColors;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "Track", "thumbPx", "trackStrokeWidth", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material/SliderColors;ZFFLjava/util/List;FFLandroidx/compose/runtime/Composer;I)V", "animateToTarget", "draggableState", "Landroidx/compose/foundation/gestures/DraggableState;", "current", "target", "velocity", "(Landroidx/compose/foundation/gestures/DraggableState;FFFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calcFraction", "a", "b", "pos", "scale", "a1", "b1", "x1", "a2", "b2", "x", "snapValueToTick", "minPx", "maxPx", "stepsToTickFractions", "SliderThumb", "Landroidx/compose/foundation/layout/BoxScope;", "offset", "thumbSize", "SliderThumb-PcYyNuk", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/ui/Modifier;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SliderColors;ZFLandroidx/compose/runtime/Composer;I)V", "awaitSlop", "Lkotlin/Pair;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "id", "Landroidx/compose/ui/input/pointer/PointerId;", "type", "Landroidx/compose/ui/input/pointer/PointerType;", "awaitSlop-8vUncbI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rangeSliderPressDragModifier", "rawOffsetStart", "Landroidx/compose/runtime/State;", "rawOffsetEnd", "isRtl", "gestureEndAction", "onDrag", "Lkotlin/Function2;", "sliderSemantics", "sliderTapModifier", "rawOffset", "pressOffset", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SliderKt {
    private static final Modifier DefaultSliderConstraints;
    private static final float SliderHeight;
    private static final float SliderMinWidth;
    private static final TweenSpec<Float> SliderToTickAnimation;
    private static final float ThumbRadius = Dp.m5274constructorimpl(10);
    private static final float ThumbRippleRadius = Dp.m5274constructorimpl(24);
    private static final float ThumbDefaultElevation = Dp.m5274constructorimpl(1);
    private static final float ThumbPressedElevation = Dp.m5274constructorimpl(6);
    private static final float TrackHeight = Dp.m5274constructorimpl(4);

    public static final void Slider(final float value, final Function1<? super Float, Unit> onValueChange, Modifier modifier, boolean enabled, ClosedFloatingPointRange<Float> closedFloatingPointRange, int steps, Function0<Unit> function0, MutableInteractionSource interactionSource, SliderColors colors, Composer $composer, final int $changed, final int i) {
        ClosedFloatingPointRange<Float> closedFloatingPointRange2;
        int i2;
        Function0<Unit> function1;
        Modifier.Companion modifier2;
        boolean enabled2;
        ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo;
        int steps2;
        Function0<Unit> function2;
        MutableInteractionSource interactionSource2;
        SliderColors colors2;
        Object value$iv$iv;
        Object value$iv$iv2;
        MutableInteractionSource interactionSource3;
        SliderColors colors3;
        boolean enabled3;
        ClosedFloatingPointRange<Float> closedFloatingPointRange3;
        int steps3;
        Function0<Unit> function3;
        Intrinsics.checkNotNullParameter(onValueChange, "onValueChange");
        Composer $composer2 = $composer.startRestartGroup(-1962335196);
        ComposerKt.sourceInformation($composer2, "C(Slider)P(7,4,3,1,8,6,5,2)154@7436L39,155@7519L8,158@7612L35,159@7672L59,162@7736L3444:Slider.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changedInstance(onValueChange) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(modifier) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(enabled) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                closedFloatingPointRange2 = closedFloatingPointRange;
                int i5 = $composer2.changed(closedFloatingPointRange2) ? 16384 : 8192;
                $dirty |= i5;
            } else {
                closedFloatingPointRange2 = closedFloatingPointRange;
            }
            $dirty |= i5;
        } else {
            closedFloatingPointRange2 = closedFloatingPointRange;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i2 = steps;
        } else if ((458752 & $changed) == 0) {
            i2 = steps;
            $dirty |= $composer2.changed(i2) ? 131072 : 65536;
        } else {
            i2 = steps;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty |= 1572864;
            function1 = function0;
        } else if ((3670016 & $changed) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 1048576 : 524288;
        } else {
            function1 = function0;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 8388608 : 4194304;
        }
        if (($changed & 234881024) == 0) {
            $dirty |= ((i & 256) == 0 && $composer2.changed(colors)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($dirty & 191739611) == 38347922 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            enabled3 = enabled;
            interactionSource3 = interactionSource;
            colors3 = colors;
            closedFloatingPointRange3 = closedFloatingPointRange2;
            function3 = function1;
            steps3 = i2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i3 != 0 ? Modifier.INSTANCE : modifier;
                enabled2 = i4 != 0 ? true : enabled;
                if ((i & 16) != 0) {
                    closedFloatingPointRangeRangeTo = RangesKt.rangeTo(0.0f, 1.0f);
                    $dirty &= -57345;
                } else {
                    closedFloatingPointRangeRangeTo = closedFloatingPointRange2;
                }
                steps2 = i6 != 0 ? 0 : i2;
                function2 = i7 != 0 ? null : function1;
                if (i8 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    Object it$iv$iv = $composer2.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    interactionSource2 = interactionSource;
                }
                if ((i & 256) != 0) {
                    colors2 = SliderDefaults.INSTANCE.m1185colorsq0g_0yA(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 6, 1023);
                    $dirty &= -234881025;
                } else {
                    colors2 = colors;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 256) != 0) {
                    modifier2 = modifier;
                    enabled2 = enabled;
                    colors2 = colors;
                    $dirty &= -234881025;
                    closedFloatingPointRangeRangeTo = closedFloatingPointRange2;
                    steps2 = i2;
                    function2 = function1;
                    interactionSource2 = interactionSource;
                } else {
                    modifier2 = modifier;
                    enabled2 = enabled;
                    interactionSource2 = interactionSource;
                    colors2 = colors;
                    closedFloatingPointRangeRangeTo = closedFloatingPointRange2;
                    steps2 = i2;
                    function2 = function1;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1962335196, $dirty, -1, "androidx.compose.material.Slider (Slider.kt:145)");
            }
            if (!(steps2 >= 0)) {
                throw new IllegalArgumentException("steps should be >= 0".toString());
            }
            State onValueChangeState = SnapshotStateKt.rememberUpdatedState(onValueChange, $composer2, ($dirty >> 3) & 14);
            Object key1$iv = Integer.valueOf(steps2);
            int i9 = ($dirty >> 15) & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(key1$iv);
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = stepsToTickFractions(steps2);
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            List tickFractions = (List) value$iv$iv2;
            Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier2);
            float arg0$iv = ThumbRadius;
            float fM5274constructorimpl = Dp.m5274constructorimpl(2 * arg0$iv);
            float arg0$iv2 = ThumbRadius;
            BoxWithConstraintsKt.BoxWithConstraints(FocusableKt.focusable(sliderSemantics(SizeKt.m530requiredSizeInqDBjuR0$default(modifierMinimumInteractiveComponentSize, fM5274constructorimpl, Dp.m5274constructorimpl(2 * arg0$iv2), 0.0f, 0.0f, 12, null), value, enabled2, onValueChange, function2, closedFloatingPointRangeRangeTo, steps2), enabled2, interactionSource2), null, false, ComposableLambdaKt.composableLambda($composer2, 2085116814, true, new C03013(closedFloatingPointRangeRangeTo, $dirty, value, interactionSource2, enabled2, tickFractions, colors2, onValueChangeState, function2)), $composer2, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource3 = interactionSource2;
            colors3 = colors2;
            enabled3 = enabled2;
            closedFloatingPointRange3 = closedFloatingPointRangeRangeTo;
            steps3 = steps2;
            function3 = function2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier3 = modifier2;
        final boolean z = enabled3;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange4 = closedFloatingPointRange3;
        final int i10 = steps3;
        final Function0<Unit> function4 = function3;
        final MutableInteractionSource mutableInteractionSource = interactionSource3;
        final SliderColors sliderColors = colors3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.Slider.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i11) {
                SliderKt.Slider(value, onValueChange, modifier3, z, closedFloatingPointRange4, i10, function4, mutableInteractionSource, sliderColors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: renamed from: androidx.compose.material.SliderKt$Slider$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/layout/BoxWithConstraintsScope;", "invoke", "(Landroidx/compose/foundation/layout/BoxWithConstraintsScope;Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 48)
    static final class C03013 extends Lambda implements Function3<BoxWithConstraintsScope, Composer, Integer, Unit> {
        final /* synthetic */ int $$dirty;
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ Function0<Unit> $onValueChangeFinished;
        final /* synthetic */ State<Function1<Float, Unit>> $onValueChangeState;
        final /* synthetic */ List<Float> $tickFractions;
        final /* synthetic */ float $value;
        final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03013(ClosedFloatingPointRange<Float> closedFloatingPointRange, int i, float f, MutableInteractionSource mutableInteractionSource, boolean z, List<Float> list, SliderColors sliderColors, State<? extends Function1<? super Float, Unit>> state, Function0<Unit> function0) {
            super(3);
            this.$valueRange = closedFloatingPointRange;
            this.$$dirty = i;
            this.$value = f;
            this.$interactionSource = mutableInteractionSource;
            this.$enabled = z;
            this.$tickFractions = list;
            this.$colors = sliderColors;
            this.$onValueChangeState = state;
            this.$onValueChangeFinished = function0;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
            invoke(boxWithConstraintsScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:52:0x0366  */
        /* JADX WARN: Code duplicated, block: B:55:? A[RETURN, SYNTHETIC] */
        public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer $composer, int $changed) {
            Object value$iv$iv$iv;
            Object value$iv$iv;
            Object value$iv$iv2;
            boolean invalid$iv$iv;
            SliderKt$Slider$3$drag$1$1 value$iv$iv3;
            Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
            ComposerKt.sourceInformation($composer, "C176@8217L7,*181@8378L7,192@8792L24,193@8841L54,194@8922L36,196@8989L392,205@9391L83,207@9507L623,236@10624L55,243@10965L209:Slider.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 14) == 0) {
                $dirty |= $composer.changed(BoxWithConstraints) ? 4 : 2;
            }
            if (($dirty & 91) == 18 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2085116814, $changed, -1, "androidx.compose.material.Slider.<anonymous> (Slider.kt:175)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer);
            boolean isRtl = objConsume == LayoutDirection.Rtl;
            float widthPx = Constraints.m5218getMaxWidthimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk());
            final Ref.FloatRef maxPx = new Ref.FloatRef();
            final Ref.FloatRef minPx = new Ref.FloatRef();
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Density $this$invoke_u24lambda_u240 = (Density) objConsume2;
            maxPx.element = Math.max(widthPx - $this$invoke_u24lambda_u240.mo327toPx0680j_4(SliderKt.getThumbRadius()), 0.0f);
            minPx.element = Math.min($this$invoke_u24lambda_u240.mo327toPx0680j_4(SliderKt.getThumbRadius()), maxPx.element);
            $composer.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv$iv = $composer.rememberedValue();
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer));
                $composer.updateRememberedValue(value$iv$iv$iv);
            } else {
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            $composer.endReplaceableGroup();
            float f = this.$value;
            ClosedFloatingPointRange<Float> closedFloatingPointRange = this.$valueRange;
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = PrimitiveSnapshotStateKt.mutableFloatStateOf(invoke$scaleToOffset(closedFloatingPointRange, minPx, maxPx, f));
                $composer.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer.endReplaceableGroup();
            final MutableFloatState rawOffset = (MutableFloatState) value$iv$iv;
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv2 = $composer.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
                $composer.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer.endReplaceableGroup();
            final MutableFloatState pressOffset = (MutableFloatState) value$iv$iv2;
            Object key1$iv = Float.valueOf(minPx.element);
            Object key2$iv = Float.valueOf(maxPx.element);
            Object key3$iv = this.$valueRange;
            final State<Function1<Float, Unit>> state = this.$onValueChangeState;
            final ClosedFloatingPointRange<Float> closedFloatingPointRange2 = this.$valueRange;
            int i = (this.$$dirty >> 6) & 896;
            $composer.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            boolean invalid$iv$iv2 = $composer.changed(key1$iv) | $composer.changed(key2$iv) | $composer.changed(key3$iv);
            Object value$iv$iv4 = $composer.rememberedValue();
            if (!invalid$iv$iv2) {
                Object key2$iv2 = Composer.INSTANCE.getEmpty();
                if (value$iv$iv4 == key2$iv2) {
                }
                $composer.endReplaceableGroup();
                final SliderDraggableState draggableState = (SliderDraggableState) value$iv$iv4;
                float f2 = this.$value;
                int i2 = this.$$dirty;
                SliderKt.CorrectValueSideEffect(new AnonymousClass2(this.$valueRange, minPx, maxPx), this.$valueRange, RangesKt.rangeTo(minPx.element, maxPx.element), rawOffset, f2, $composer, ((i2 >> 9) & 112) | 3072 | ((i2 << 12) & 57344));
                final List<Float> list = this.$tickFractions;
                final Function0<Unit> function0 = this.$onValueChangeFinished;
                State gestureEndAction = SnapshotStateKt.rememberUpdatedState(new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$Slider$3$gestureEndAction$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Float f3) {
                        invoke(f3.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(float velocity) {
                        Function0<Unit> function1;
                        float current = rawOffset.getFloatValue();
                        float target = SliderKt.snapValueToTick(current, list, minPx.element, maxPx.element);
                        if (!(current == target)) {
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(draggableState, current, target, velocity, function0, null), 3, null);
                        } else {
                            if (draggableState.isDragging() || (function1 = function0) == null) {
                                return;
                            }
                            function1.invoke();
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material.SliderKt$Slider$3$gestureEndAction$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: Slider.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                    @DebugMetadata(c = "androidx.compose.material.SliderKt$Slider$3$gestureEndAction$1$1", f = "Slider.kt", i = {}, l = {213}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ float $current;
                        final /* synthetic */ SliderDraggableState $draggableState;
                        final /* synthetic */ Function0<Unit> $onValueChangeFinished;
                        final /* synthetic */ float $target;
                        final /* synthetic */ float $velocity;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SliderDraggableState sliderDraggableState, float f, float f2, float f3, Function0<Unit> function0, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$draggableState = sliderDraggableState;
                            this.$current = f;
                            this.$target = f2;
                            this.$velocity = f3;
                            this.$onValueChangeFinished = function0;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$draggableState, this.$current, this.$target, this.$velocity, this.$onValueChangeFinished, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            AnonymousClass1 anonymousClass1;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (SliderKt.animateToTarget(this.$draggableState, this.$current, this.$target, this.$velocity, this) != coroutine_suspended) {
                                        anonymousClass1 = this;
                                    } else {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    anonymousClass1 = this;
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            Function0<Unit> function0 = anonymousClass1.$onValueChangeFinished;
                            if (function0 != null) {
                                function0.invoke();
                            }
                            return Unit.INSTANCE;
                        }
                    }
                }, $composer, 0);
                Modifier press = SliderKt.sliderTapModifier(Modifier.INSTANCE, draggableState, this.$interactionSource, widthPx, isRtl, rawOffset, gestureEndAction, pressOffset, this.$enabled);
                Modifier.Companion companion = Modifier.INSTANCE;
                Orientation orientation = Orientation.Horizontal;
                boolean zIsDragging = draggableState.isDragging();
                Modifier.Companion companion2 = companion;
                SliderDraggableState sliderDraggableState = draggableState;
                boolean z = this.$enabled;
                MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                $composer.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer.changed(gestureEndAction);
                value$iv$iv3 = $composer.rememberedValue();
                if (!invalid$iv$iv || value$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv3 = new SliderKt$Slider$3$drag$1$1(gestureEndAction, null);
                    $composer.updateRememberedValue(value$iv$iv3);
                }
                $composer.endReplaceableGroup();
                Modifier drag = DraggableKt.draggable(companion2, sliderDraggableState, orientation, (188 & 4) != 0 ? true : z, (188 & 8) != 0 ? null : mutableInteractionSource, (188 & 16) != 0 ? false : zIsDragging, (188 & 32) != 0 ? new DraggableKt.C01841(null) : null, (188 & 64) != 0 ? new DraggableKt.AnonymousClass2(null) : (Function3) value$iv$iv3, (188 & 128) != 0 ? false : isRtl);
                float coerced = RangesKt.coerceIn(this.$value, this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue());
                float fraction = SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), coerced);
                boolean z2 = this.$enabled;
                List<Float> list2 = this.$tickFractions;
                SliderColors sliderColors = this.$colors;
                float f3 = maxPx.element - minPx.element;
                MutableInteractionSource mutableInteractionSource2 = this.$interactionSource;
                Modifier modifierThen = press.then(drag);
                int i3 = this.$$dirty;
                SliderKt.SliderImpl(z2, fraction, list2, sliderColors, f3, mutableInteractionSource2, modifierThen, $composer, ((i3 >> 9) & 14) | 512 | ((i3 >> 15) & 7168) | ((i3 >> 6) & 458752));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            value$iv$iv4 = new SliderDraggableState(new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$Slider$3$draggableState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Float f4) {
                    invoke(f4.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(float it) {
                    MutableFloatState mutableFloatState = rawOffset;
                    mutableFloatState.setFloatValue(mutableFloatState.getFloatValue() + it + pressOffset.getFloatValue());
                    pressOffset.setFloatValue(0.0f);
                    float offsetInTrack = RangesKt.coerceIn(rawOffset.getFloatValue(), minPx.element, maxPx.element);
                    state.getValue().invoke(Float.valueOf(SliderKt.C03013.invoke$scaleToUserValue(minPx, maxPx, closedFloatingPointRange2, offsetInTrack)));
                }
            });
            $composer.updateRememberedValue(value$iv$iv4);
            $composer.endReplaceableGroup();
            final SliderDraggableState draggableState2 = (SliderDraggableState) value$iv$iv4;
            float f4 = this.$value;
            int i4 = this.$$dirty;
            SliderKt.CorrectValueSideEffect(new AnonymousClass2(this.$valueRange, minPx, maxPx), this.$valueRange, RangesKt.rangeTo(minPx.element, maxPx.element), rawOffset, f4, $composer, ((i4 >> 9) & 112) | 3072 | ((i4 << 12) & 57344));
            final List<Float> list3 = this.$tickFractions;
            final Function0<Unit> function1 = this.$onValueChangeFinished;
            State gestureEndAction2 = SnapshotStateKt.rememberUpdatedState(new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$Slider$3$gestureEndAction$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Float f5) {
                    invoke(f5.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(float velocity) {
                    Function0<Unit> function2;
                    float current = rawOffset.getFloatValue();
                    float target = SliderKt.snapValueToTick(current, list3, minPx.element, maxPx.element);
                    if (!(current == target)) {
                        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(draggableState2, current, target, velocity, function1, null), 3, null);
                    } else {
                        if (draggableState2.isDragging() || (function2 = function1) == null) {
                            return;
                        }
                        function2.invoke();
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.material.SliderKt$Slider$3$gestureEndAction$1$1, reason: invalid class name */
                /* JADX INFO: compiled from: Slider.kt */
                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                @DebugMetadata(c = "androidx.compose.material.SliderKt$Slider$3$gestureEndAction$1$1", f = "Slider.kt", i = {}, l = {213}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ float $current;
                    final /* synthetic */ SliderDraggableState $draggableState;
                    final /* synthetic */ Function0<Unit> $onValueChangeFinished;
                    final /* synthetic */ float $target;
                    final /* synthetic */ float $velocity;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(SliderDraggableState sliderDraggableState, float f, float f2, float f3, Function0<Unit> function0, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$draggableState = sliderDraggableState;
                        this.$current = f;
                        this.$target = f2;
                        this.$velocity = f3;
                        this.$onValueChangeFinished = function0;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$draggableState, this.$current, this.$target, this.$velocity, this.$onValueChangeFinished, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        AnonymousClass1 anonymousClass1;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                this.label = 1;
                                if (SliderKt.animateToTarget(this.$draggableState, this.$current, this.$target, this.$velocity, this) != coroutine_suspended) {
                                    anonymousClass1 = this;
                                } else {
                                    return coroutine_suspended;
                                }
                                break;
                            case 1:
                                anonymousClass1 = this;
                                ResultKt.throwOnFailure($result);
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        Function0<Unit> function0 = anonymousClass1.$onValueChangeFinished;
                        if (function0 != null) {
                            function0.invoke();
                        }
                        return Unit.INSTANCE;
                    }
                }
            }, $composer, 0);
            Modifier press2 = SliderKt.sliderTapModifier(Modifier.INSTANCE, draggableState2, this.$interactionSource, widthPx, isRtl, rawOffset, gestureEndAction2, pressOffset, this.$enabled);
            Modifier.Companion companion3 = Modifier.INSTANCE;
            Orientation orientation2 = Orientation.Horizontal;
            boolean zIsDragging2 = draggableState2.isDragging();
            Modifier.Companion companion4 = companion3;
            SliderDraggableState sliderDraggableState2 = draggableState2;
            boolean z3 = this.$enabled;
            MutableInteractionSource mutableInteractionSource3 = this.$interactionSource;
            $composer.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv = $composer.changed(gestureEndAction2);
            value$iv$iv3 = $composer.rememberedValue();
            if (!invalid$iv$iv) {
            }
            value$iv$iv3 = new SliderKt$Slider$3$drag$1$1(gestureEndAction2, null);
            $composer.updateRememberedValue(value$iv$iv3);
            $composer.endReplaceableGroup();
            Modifier drag2 = DraggableKt.draggable(companion4, sliderDraggableState2, orientation2, (188 & 4) != 0 ? true : z3, (188 & 8) != 0 ? null : mutableInteractionSource3, (188 & 16) != 0 ? false : zIsDragging2, (188 & 32) != 0 ? new DraggableKt.C01841(null) : null, (188 & 64) != 0 ? new DraggableKt.AnonymousClass2(null) : (Function3) value$iv$iv3, (188 & 128) != 0 ? false : isRtl);
            float coerced2 = RangesKt.coerceIn(this.$value, this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue());
            float fraction2 = SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), coerced2);
            boolean z4 = this.$enabled;
            List<Float> list4 = this.$tickFractions;
            SliderColors sliderColors2 = this.$colors;
            float f5 = maxPx.element - minPx.element;
            MutableInteractionSource mutableInteractionSource4 = this.$interactionSource;
            Modifier modifierThen2 = press2.then(drag2);
            int i5 = this.$$dirty;
            SliderKt.SliderImpl(z4, fraction2, list4, sliderColors2, f5, mutableInteractionSource4, modifierThen2, $composer, ((i5 >> 9) & 14) | 512 | ((i5 >> 15) & 7168) | ((i5 >> 6) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$scaleToUserValue(Ref.FloatRef minPx, Ref.FloatRef maxPx, ClosedFloatingPointRange<Float> closedFloatingPointRange, float offset) {
            return SliderKt.scale(minPx.element, maxPx.element, offset, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$scaleToOffset(ClosedFloatingPointRange<Float> closedFloatingPointRange, Ref.FloatRef minPx, Ref.FloatRef maxPx, float userValue) {
            return SliderKt.scale(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), userValue, minPx.element, maxPx.element);
        }

        /* JADX INFO: renamed from: androidx.compose.material.SliderKt$Slider$3$2, reason: invalid class name */
        /* JADX INFO: compiled from: Slider.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<Float, Float> {
            final /* synthetic */ Ref.FloatRef $maxPx;
            final /* synthetic */ Ref.FloatRef $minPx;
            final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(ClosedFloatingPointRange<Float> closedFloatingPointRange, Ref.FloatRef floatRef, Ref.FloatRef floatRef2) {
                super(1, Intrinsics.Kotlin.class, "scaleToOffset", "invoke$scaleToOffset(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/internal/Ref$FloatRef;Lkotlin/jvm/internal/Ref$FloatRef;F)F", 0);
                this.$valueRange = closedFloatingPointRange;
                this.$minPx = floatRef;
                this.$maxPx = floatRef2;
            }

            public final Float invoke(float p0) {
                return Float.valueOf(C03013.invoke$scaleToOffset(this.$valueRange, this.$minPx, this.$maxPx, p0));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Float invoke(Float f) {
                return invoke(f.floatValue());
            }
        }
    }

    public static final void RangeSlider(final ClosedFloatingPointRange<Float> value, final Function1<? super ClosedFloatingPointRange<Float>, Unit> onValueChange, Modifier modifier, boolean enabled, ClosedFloatingPointRange<Float> closedFloatingPointRange, int steps, Function0<Unit> function0, SliderColors colors, Composer $composer, final int $changed, final int i) {
        boolean enabled2;
        ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo;
        int steps2;
        Function0<Unit> function1;
        SliderColors colors2;
        Modifier modifier2;
        boolean enabled3;
        ClosedFloatingPointRange<Float> closedFloatingPointRange2;
        int steps3;
        Function0<Unit> function2;
        int $dirty;
        Object value$iv$iv;
        Object value$iv$iv2;
        Object value$iv$iv3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(onValueChange, "onValueChange");
        Composer $composer3 = $composer.startRestartGroup(-1556183027);
        ComposerKt.sourceInformation($composer3, "C(RangeSlider)P(6,3,2,1,7,5,4)299@13521L8,301@13593L39,302@13690L39,305@13811L35,306@13871L59,310@13936L5142:Slider.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changedInstance(onValueChange) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 7168) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                closedFloatingPointRangeRangeTo = closedFloatingPointRange;
                int i4 = $composer3.changed(closedFloatingPointRangeRangeTo) ? 16384 : 8192;
                $dirty2 |= i4;
            } else {
                closedFloatingPointRangeRangeTo = closedFloatingPointRange;
            }
            $dirty2 |= i4;
        } else {
            closedFloatingPointRangeRangeTo = closedFloatingPointRange;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            steps2 = steps;
        } else if ((458752 & $changed) == 0) {
            steps2 = steps;
            $dirty2 |= $composer3.changed(steps2) ? 131072 : 65536;
        } else {
            steps2 = steps;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            function1 = function0;
        } else if ((3670016 & $changed) == 0) {
            function1 = function0;
            $dirty2 |= $composer3.changedInstance(function1) ? 1048576 : 524288;
        } else {
            function1 = function0;
        }
        if (($changed & 29360128) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer3.changed(colors)) ? 8388608 : 4194304;
        }
        if (($dirty2 & 23967451) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier2 = modifier;
            colors2 = colors;
            enabled3 = enabled2;
            closedFloatingPointRange2 = closedFloatingPointRangeRangeTo;
            steps3 = steps2;
            function2 = function1;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if (i3 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    closedFloatingPointRangeRangeTo = RangesKt.rangeTo(0.0f, 1.0f);
                }
                if (i5 != 0) {
                    steps2 = 0;
                }
                if (i6 != 0) {
                    function1 = null;
                }
                if ((i & 128) != 0) {
                    modifier2 = modifier3;
                    colors2 = SliderDefaults.INSTANCE.m1185colorsq0g_0yA(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 6, 1023);
                    enabled3 = enabled2;
                    closedFloatingPointRange2 = closedFloatingPointRangeRangeTo;
                    steps3 = steps2;
                    function2 = function1;
                    $dirty = $dirty2 & (-29360129);
                } else {
                    colors2 = colors;
                    modifier2 = modifier3;
                    enabled3 = enabled2;
                    closedFloatingPointRange2 = closedFloatingPointRangeRangeTo;
                    steps3 = steps2;
                    function2 = function1;
                    $dirty = $dirty2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 128) != 0) {
                    modifier2 = modifier;
                    colors2 = colors;
                    enabled3 = enabled2;
                    closedFloatingPointRange2 = closedFloatingPointRangeRangeTo;
                    steps3 = steps2;
                    function2 = function1;
                    $dirty = $dirty2 & (-29360129);
                } else {
                    modifier2 = modifier;
                    colors2 = colors;
                    enabled3 = enabled2;
                    closedFloatingPointRange2 = closedFloatingPointRangeRangeTo;
                    steps3 = steps2;
                    function2 = function1;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1556183027, $dirty, -1, "androidx.compose.material.RangeSlider (Slider.kt:290)");
            }
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer3.endReplaceableGroup();
            MutableInteractionSource startInteractionSource = (MutableInteractionSource) value$iv$iv;
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv2 = $composer3.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer3.endReplaceableGroup();
            MutableInteractionSource endInteractionSource = (MutableInteractionSource) value$iv$iv2;
            if (!(steps3 >= 0)) {
                throw new IllegalArgumentException("steps should be >= 0".toString());
            }
            State onValueChangeState = SnapshotStateKt.rememberUpdatedState(onValueChange, $composer3, ($dirty >> 3) & 14);
            Object key1$iv = Integer.valueOf(steps3);
            int i7 = ($dirty >> 15) & 14;
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer3.changed(key1$iv);
            Object it$iv$iv3 = $composer3.rememberedValue();
            if (invalid$iv$iv || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv3 = stepsToTickFractions(steps3);
                $composer3.updateRememberedValue(value$iv$iv3);
            } else {
                value$iv$iv3 = it$iv$iv3;
            }
            $composer3.endReplaceableGroup();
            List tickFractions = (List) value$iv$iv3;
            Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier2);
            float arg0$iv = ThumbRadius;
            float arg0$iv2 = Dp.m5274constructorimpl(4 * arg0$iv);
            float arg0$iv3 = ThumbRadius;
            $composer2 = $composer3;
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.m530requiredSizeInqDBjuR0$default(modifierMinimumInteractiveComponentSize, arg0$iv2, Dp.m5274constructorimpl(2 * arg0$iv3), 0.0f, 0.0f, 12, null), null, false, ComposableLambdaKt.composableLambda($composer2, 652589923, true, new C02992(closedFloatingPointRange2, value, $dirty, onValueChangeState, startInteractionSource, endInteractionSource, enabled3, steps3, function2, tickFractions, colors2)), $composer2, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier2;
        final boolean z = enabled3;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange3 = closedFloatingPointRange2;
        final int i8 = steps3;
        final Function0<Unit> function3 = function2;
        final SliderColors sliderColors = colors2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.RangeSlider.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i9) {
                SliderKt.RangeSlider(value, onValueChange, modifier4, z, closedFloatingPointRange3, i8, function3, sliderColors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: renamed from: androidx.compose.material.SliderKt$RangeSlider$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/layout/BoxWithConstraintsScope;", "invoke", "(Landroidx/compose/foundation/layout/BoxWithConstraintsScope;Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 48)
    static final class C02992 extends Lambda implements Function3<BoxWithConstraintsScope, Composer, Integer, Unit> {
        final /* synthetic */ int $$dirty;
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $endInteractionSource;
        final /* synthetic */ Function0<Unit> $onValueChangeFinished;
        final /* synthetic */ State<Function1<ClosedFloatingPointRange<Float>, Unit>> $onValueChangeState;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;
        final /* synthetic */ int $steps;
        final /* synthetic */ List<Float> $tickFractions;
        final /* synthetic */ ClosedFloatingPointRange<Float> $value;
        final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C02992(ClosedFloatingPointRange<Float> closedFloatingPointRange, ClosedFloatingPointRange<Float> closedFloatingPointRange2, int i, State<? extends Function1<? super ClosedFloatingPointRange<Float>, Unit>> state, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, boolean z, int i2, Function0<Unit> function0, List<Float> list, SliderColors sliderColors) {
            super(3);
            this.$valueRange = closedFloatingPointRange;
            this.$value = closedFloatingPointRange2;
            this.$$dirty = i;
            this.$onValueChangeState = state;
            this.$startInteractionSource = mutableInteractionSource;
            this.$endInteractionSource = mutableInteractionSource2;
            this.$enabled = z;
            this.$steps = i2;
            this.$onValueChangeFinished = function0;
            this.$tickFractions = list;
            this.$colors = sliderColors;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
            invoke(boxWithConstraintsScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:63:0x04db  */
        /* JADX WARN: Code duplicated, block: B:67:? A[RETURN, SYNTHETIC] */
        public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer $composer, int $changed) {
            Object value$iv$iv;
            Object value$iv$iv2;
            Object value$iv$iv$iv;
            Object value$iv$iv3;
            boolean invalid$iv$iv;
            Object value$iv$iv4;
            Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
            ComposerKt.sourceInformation($composer, "C315@14165L7,*320@14326L7,331@14754L60,332@14842L67,334@14919L164,341@15092L169,349@15283L24,350@15339L964,374@16373L857,374@16326L904,416@18249L63,424@18539L65,430@18732L340:Slider.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 14) == 0) {
                $dirty |= $composer.changed(BoxWithConstraints) ? 4 : 2;
            }
            if (($dirty & 91) != 18 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(652589923, $changed, -1, "androidx.compose.material.RangeSlider.<anonymous> (Slider.kt:314)");
                }
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer);
                boolean isRtl = objConsume == LayoutDirection.Rtl;
                float widthPx = Constraints.m5218getMaxWidthimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk());
                final Ref.FloatRef maxPx = new Ref.FloatRef();
                final Ref.FloatRef minPx = new Ref.FloatRef();
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Density $this$invoke_u24lambda_u240 = (Density) objConsume2;
                maxPx.element = widthPx - $this$invoke_u24lambda_u240.mo327toPx0680j_4(SliderKt.getThumbRadius());
                minPx.element = $this$invoke_u24lambda_u240.mo327toPx0680j_4(SliderKt.getThumbRadius());
                ClosedFloatingPointRange<Float> closedFloatingPointRange = this.$value;
                ClosedFloatingPointRange<Float> closedFloatingPointRange2 = this.$valueRange;
                $composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
                Object it$iv$iv = $composer.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = PrimitiveSnapshotStateKt.mutableFloatStateOf(invoke$scaleToOffset(closedFloatingPointRange2, minPx, maxPx, closedFloatingPointRange.getStart().floatValue()));
                    $composer.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                $composer.endReplaceableGroup();
                final MutableFloatState rawOffsetStart = (MutableFloatState) value$iv$iv;
                ClosedFloatingPointRange<Float> closedFloatingPointRange3 = this.$value;
                ClosedFloatingPointRange<Float> closedFloatingPointRange4 = this.$valueRange;
                $composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
                Object it$iv$iv2 = $composer.rememberedValue();
                if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = PrimitiveSnapshotStateKt.mutableFloatStateOf(invoke$scaleToOffset(closedFloatingPointRange4, minPx, maxPx, closedFloatingPointRange3.getEndInclusive().floatValue()));
                    $composer.updateRememberedValue(value$iv$iv2);
                } else {
                    value$iv$iv2 = it$iv$iv2;
                }
                $composer.endReplaceableGroup();
                final MutableFloatState rawOffsetEnd = (MutableFloatState) value$iv$iv2;
                SliderKt.CorrectValueSideEffect(new C00552(this.$valueRange, minPx, maxPx), this.$valueRange, RangesKt.rangeTo(minPx.element, maxPx.element), rawOffsetStart, this.$value.getStart().floatValue(), $composer, ((this.$$dirty >> 9) & 112) | 3072);
                SliderKt.CorrectValueSideEffect(new AnonymousClass3(this.$valueRange, minPx, maxPx), this.$valueRange, RangesKt.rangeTo(minPx.element, maxPx.element), rawOffsetEnd, this.$value.getEndInclusive().floatValue(), $composer, ((this.$$dirty >> 9) & 112) | 3072);
                $composer.startReplaceableGroup(773894976);
                ComposerKt.sourceInformation($composer, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
                $composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
                Object it$iv$iv$iv = $composer.rememberedValue();
                if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer));
                    $composer.updateRememberedValue(value$iv$iv$iv);
                } else {
                    value$iv$iv$iv = it$iv$iv$iv;
                }
                $composer.endReplaceableGroup();
                CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
                final CoroutineScope scope = wrapper$iv.getCoroutineScope();
                $composer.endReplaceableGroup();
                final List<Float> list = this.$tickFractions;
                final Function0<Unit> function0 = this.$onValueChangeFinished;
                final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state = this.$onValueChangeState;
                final ClosedFloatingPointRange<Float> closedFloatingPointRange5 = this.$valueRange;
                State gestureEndAction = SnapshotStateKt.rememberUpdatedState(new Function1<Boolean, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$gestureEndAction$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean isStart) {
                        float current = (isStart ? rawOffsetStart : rawOffsetEnd).getFloatValue();
                        float target = SliderKt.snapValueToTick(current, list, minPx.element, maxPx.element);
                        if (!(current == target)) {
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(current, target, function0, isStart, rawOffsetStart, rawOffsetEnd, state, minPx, maxPx, closedFloatingPointRange5, null), 3, null);
                            return;
                        }
                        Function0<Unit> function1 = function0;
                        if (function1 != null) {
                            function1.invoke();
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material.SliderKt$RangeSlider$2$gestureEndAction$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: Slider.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                    @DebugMetadata(c = "androidx.compose.material.SliderKt$RangeSlider$2$gestureEndAction$1$1", f = "Slider.kt", i = {}, l = {361}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ float $current;
                        final /* synthetic */ boolean $isStart;
                        final /* synthetic */ Ref.FloatRef $maxPx;
                        final /* synthetic */ Ref.FloatRef $minPx;
                        final /* synthetic */ Function0<Unit> $onValueChangeFinished;
                        final /* synthetic */ State<Function1<ClosedFloatingPointRange<Float>, Unit>> $onValueChangeState;
                        final /* synthetic */ MutableFloatState $rawOffsetEnd;
                        final /* synthetic */ MutableFloatState $rawOffsetStart;
                        final /* synthetic */ float $target;
                        final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        AnonymousClass1(float f, float f2, Function0<Unit> function0, boolean z, MutableFloatState mutableFloatState, MutableFloatState mutableFloatState2, State<? extends Function1<? super ClosedFloatingPointRange<Float>, Unit>> state, Ref.FloatRef floatRef, Ref.FloatRef floatRef2, ClosedFloatingPointRange<Float> closedFloatingPointRange, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$current = f;
                            this.$target = f2;
                            this.$onValueChangeFinished = function0;
                            this.$isStart = z;
                            this.$rawOffsetStart = mutableFloatState;
                            this.$rawOffsetEnd = mutableFloatState2;
                            this.$onValueChangeState = state;
                            this.$minPx = floatRef;
                            this.$maxPx = floatRef2;
                            this.$valueRange = closedFloatingPointRange;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$current, this.$target, this.$onValueChangeFinished, this.$isStart, this.$rawOffsetStart, this.$rawOffsetEnd, this.$onValueChangeState, this.$minPx, this.$maxPx, this.$valueRange, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            AnonymousClass1 anonymousClass1;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    Animatable animatableAnimatable$default = AnimatableKt.Animatable$default(this.$current, 0.0f, 2, null);
                                    Float fBoxFloat = Boxing.boxFloat(this.$target);
                                    TweenSpec tweenSpec = SliderKt.SliderToTickAnimation;
                                    Float fBoxFloat2 = Boxing.boxFloat(0.0f);
                                    final boolean z = this.$isStart;
                                    final MutableFloatState mutableFloatState = this.$rawOffsetStart;
                                    final MutableFloatState mutableFloatState2 = this.$rawOffsetEnd;
                                    final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state = this.$onValueChangeState;
                                    final Ref.FloatRef floatRef = this.$minPx;
                                    final Ref.FloatRef floatRef2 = this.$maxPx;
                                    final ClosedFloatingPointRange<Float> closedFloatingPointRange = this.$valueRange;
                                    this.label = 1;
                                    if (animatableAnimatable$default.animateTo(fBoxFloat, tweenSpec, fBoxFloat2, new Function1<Animatable<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material.SliderKt.RangeSlider.2.gestureEndAction.1.1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Animatable<Float, AnimationVector1D> animatable) {
                                            invoke2(animatable);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Animatable<Float, AnimationVector1D> animateTo) {
                                            Intrinsics.checkNotNullParameter(animateTo, "$this$animateTo");
                                            (z ? mutableFloatState : mutableFloatState2).setFloatValue(animateTo.getValue().floatValue());
                                            state.getValue().invoke(SliderKt.C02992.invoke$scaleToUserValue(floatRef, floatRef2, closedFloatingPointRange, RangesKt.rangeTo(mutableFloatState.getFloatValue(), mutableFloatState2.getFloatValue())));
                                        }
                                    }, this) != coroutine_suspended) {
                                        anonymousClass1 = this;
                                    } else {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    anonymousClass1 = this;
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            Function0<Unit> function0 = anonymousClass1.$onValueChangeFinished;
                            if (function0 != null) {
                                function0.invoke();
                            }
                            return Unit.INSTANCE;
                        }
                    }
                }, $composer, 0);
                final ClosedFloatingPointRange<Float> closedFloatingPointRange6 = this.$value;
                final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state2 = this.$onValueChangeState;
                Object[] keys$iv = {rawOffsetStart, rawOffsetEnd, this.$valueRange, Float.valueOf(minPx.element), Float.valueOf(maxPx.element), closedFloatingPointRange6, state2};
                final ClosedFloatingPointRange<Float> closedFloatingPointRange7 = this.$valueRange;
                int $changed$iv = 8;
                $composer.startReplaceableGroup(-568225417);
                ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
                int length = keys$iv.length;
                boolean invalid$iv = false;
                int i = 0;
                while (i < length) {
                    int $changed$iv2 = $changed$iv;
                    Object key$iv = keys$iv[i];
                    invalid$iv |= $composer.changed(key$iv);
                    i++;
                    $changed$iv = $changed$iv2;
                }
                Object it$iv$iv3 = $composer.rememberedValue();
                if (invalid$iv || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv3 = new Function2<Boolean, Float, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$onDrag$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Float f) {
                            invoke(bool.booleanValue(), f.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean isStart, float offset) {
                            ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo;
                            if (isStart) {
                                MutableFloatState mutableFloatState = rawOffsetStart;
                                mutableFloatState.setFloatValue(mutableFloatState.getFloatValue() + offset);
                                rawOffsetEnd.setFloatValue(SliderKt.C02992.invoke$scaleToOffset(closedFloatingPointRange7, minPx, maxPx, closedFloatingPointRange6.getEndInclusive().floatValue()));
                                float offsetEnd = rawOffsetEnd.getFloatValue();
                                closedFloatingPointRangeRangeTo = RangesKt.rangeTo(RangesKt.coerceIn(rawOffsetStart.getFloatValue(), minPx.element, offsetEnd), offsetEnd);
                            } else {
                                MutableFloatState mutableFloatState2 = rawOffsetEnd;
                                mutableFloatState2.setFloatValue(mutableFloatState2.getFloatValue() + offset);
                                rawOffsetStart.setFloatValue(SliderKt.C02992.invoke$scaleToOffset(closedFloatingPointRange7, minPx, maxPx, closedFloatingPointRange6.getStart().floatValue()));
                                float offsetStart = rawOffsetStart.getFloatValue();
                                closedFloatingPointRangeRangeTo = RangesKt.rangeTo(offsetStart, RangesKt.coerceIn(rawOffsetEnd.getFloatValue(), offsetStart, maxPx.element));
                            }
                            state2.getValue().invoke(SliderKt.C02992.invoke$scaleToUserValue(minPx, maxPx, closedFloatingPointRange7, closedFloatingPointRangeRangeTo));
                        }
                    };
                    $composer.updateRememberedValue(value$iv$iv3);
                } else {
                    value$iv$iv3 = it$iv$iv3;
                }
                $composer.endReplaceableGroup();
                State onDrag = SnapshotStateKt.rememberUpdatedState(value$iv$iv3, $composer, 0);
                Modifier pressDrag = SliderKt.rangeSliderPressDragModifier(Modifier.INSTANCE, this.$startInteractionSource, this.$endInteractionSource, rawOffsetStart, rawOffsetEnd, this.$enabled, isRtl, widthPx, this.$valueRange, gestureEndAction, onDrag);
                final float coercedStart = RangesKt.coerceIn(this.$value.getStart().floatValue(), this.$valueRange.getStart().floatValue(), this.$value.getEndInclusive().floatValue());
                final float coercedEnd = RangesKt.coerceIn(this.$value.getEndInclusive().floatValue(), this.$value.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue());
                float fractionStart = SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), coercedStart);
                float fractionEnd = SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), coercedEnd);
                int startSteps = (int) Math.floor(this.$steps * fractionEnd);
                int endSteps = (int) Math.floor(this.$steps * (1.0f - fractionStart));
                Modifier.Companion companion = Modifier.INSTANCE;
                boolean z = this.$enabled;
                Object key1$iv = this.$onValueChangeState;
                Object key2$iv = Float.valueOf(coercedEnd);
                final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state3 = this.$onValueChangeState;
                $composer.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
                boolean invalid$iv$iv2 = $composer.changed(key1$iv) | $composer.changed(key2$iv);
                Object value$iv$iv5 = $composer.rememberedValue();
                if (!invalid$iv$iv2) {
                    Object key2$iv2 = Composer.INSTANCE.getEmpty();
                    if (value$iv$iv5 == key2$iv2) {
                    }
                    $composer.endReplaceableGroup();
                    Modifier startThumbSemantics = SliderKt.sliderSemantics(companion, coercedStart, z, (Function1) value$iv$iv5, this.$onValueChangeFinished, RangesKt.rangeTo(this.$valueRange.getStart().floatValue(), coercedEnd), startSteps);
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    boolean z2 = this.$enabled;
                    Object key1$iv2 = this.$onValueChangeState;
                    Object key2$iv3 = Float.valueOf(coercedStart);
                    final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state4 = this.$onValueChangeState;
                    $composer.startReplaceableGroup(511388516);
                    ComposerKt.sourceInformation($composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer.changed(key1$iv2) | $composer.changed(key2$iv3);
                    Object it$iv$iv4 = $composer.rememberedValue();
                    if (!invalid$iv$iv || it$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv4 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$endThumbSemantics$1$1
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

                            public final void invoke(float value) {
                                state4.getValue().invoke(RangesKt.rangeTo(coercedStart, value));
                            }
                        };
                        $composer.updateRememberedValue(value$iv$iv4);
                    } else {
                        value$iv$iv4 = it$iv$iv4;
                    }
                    $composer.endReplaceableGroup();
                    Modifier endThumbSemantics = SliderKt.sliderSemantics(companion2, coercedEnd, z2, (Function1) value$iv$iv4, this.$onValueChangeFinished, RangesKt.rangeTo(coercedStart, this.$valueRange.getEndInclusive().floatValue()), endSteps);
                    boolean z3 = this.$enabled;
                    List<Float> list2 = this.$tickFractions;
                    SliderColors sliderColors = this.$colors;
                    float f = maxPx.element - minPx.element;
                    MutableInteractionSource mutableInteractionSource = this.$startInteractionSource;
                    MutableInteractionSource mutableInteractionSource2 = this.$endInteractionSource;
                    int i2 = this.$$dirty;
                    SliderKt.RangeSliderImpl(z3, fractionStart, fractionEnd, list2, sliderColors, f, mutableInteractionSource, mutableInteractionSource2, pressDrag, startThumbSemantics, endThumbSemantics, $composer, ((i2 >> 9) & 14) | 14159872 | ((i2 >> 9) & 57344), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                value$iv$iv5 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$startThumbSemantics$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Float f2) {
                        invoke(f2.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(float value) {
                        state3.getValue().invoke(RangesKt.rangeTo(value, coercedEnd));
                    }
                };
                $composer.updateRememberedValue(value$iv$iv5);
                $composer.endReplaceableGroup();
                Modifier startThumbSemantics2 = SliderKt.sliderSemantics(companion, coercedStart, z, (Function1) value$iv$iv5, this.$onValueChangeFinished, RangesKt.rangeTo(this.$valueRange.getStart().floatValue(), coercedEnd), startSteps);
                Modifier.Companion companion3 = Modifier.INSTANCE;
                boolean z4 = this.$enabled;
                Object key1$iv3 = this.$onValueChangeState;
                Object key2$iv4 = Float.valueOf(coercedStart);
                final State<? extends Function1<? super ClosedFloatingPointRange<Float>, Unit>> state5 = this.$onValueChangeState;
                $composer.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer.changed(key1$iv3) | $composer.changed(key2$iv4);
                Object it$iv$iv5 = $composer.rememberedValue();
                if (!invalid$iv$iv) {
                }
                value$iv$iv4 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$endThumbSemantics$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Float f2) {
                        invoke(f2.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(float value) {
                        state5.getValue().invoke(RangesKt.rangeTo(coercedStart, value));
                    }
                };
                $composer.updateRememberedValue(value$iv$iv4);
                $composer.endReplaceableGroup();
                Modifier endThumbSemantics2 = SliderKt.sliderSemantics(companion3, coercedEnd, z4, (Function1) value$iv$iv4, this.$onValueChangeFinished, RangesKt.rangeTo(coercedStart, this.$valueRange.getEndInclusive().floatValue()), endSteps);
                boolean z5 = this.$enabled;
                List<Float> list3 = this.$tickFractions;
                SliderColors sliderColors2 = this.$colors;
                float f2 = maxPx.element - minPx.element;
                MutableInteractionSource mutableInteractionSource3 = this.$startInteractionSource;
                MutableInteractionSource mutableInteractionSource4 = this.$endInteractionSource;
                int i3 = this.$$dirty;
                SliderKt.RangeSliderImpl(z5, fractionStart, fractionEnd, list3, sliderColors2, f2, mutableInteractionSource3, mutableInteractionSource4, pressDrag, startThumbSemantics2, endThumbSemantics2, $composer, ((i3 >> 9) & 14) | 14159872 | ((i3 >> 9) & 57344), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ClosedFloatingPointRange<Float> invoke$scaleToUserValue(Ref.FloatRef minPx, Ref.FloatRef maxPx, ClosedFloatingPointRange<Float> closedFloatingPointRange, ClosedFloatingPointRange<Float> closedFloatingPointRange2) {
            return SliderKt.scale(minPx.element, maxPx.element, closedFloatingPointRange2, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$scaleToOffset(ClosedFloatingPointRange<Float> closedFloatingPointRange, Ref.FloatRef minPx, Ref.FloatRef maxPx, float userValue) {
            return SliderKt.scale(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), userValue, minPx.element, maxPx.element);
        }

        /* JADX INFO: renamed from: androidx.compose.material.SliderKt$RangeSlider$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Slider.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* synthetic */ class C00552 extends FunctionReferenceImpl implements Function1<Float, Float> {
            final /* synthetic */ Ref.FloatRef $maxPx;
            final /* synthetic */ Ref.FloatRef $minPx;
            final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00552(ClosedFloatingPointRange<Float> closedFloatingPointRange, Ref.FloatRef floatRef, Ref.FloatRef floatRef2) {
                super(1, Intrinsics.Kotlin.class, "scaleToOffset", "invoke$scaleToOffset(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/internal/Ref$FloatRef;Lkotlin/jvm/internal/Ref$FloatRef;F)F", 0);
                this.$valueRange = closedFloatingPointRange;
                this.$minPx = floatRef;
                this.$maxPx = floatRef2;
            }

            public final Float invoke(float p0) {
                return Float.valueOf(C02992.invoke$scaleToOffset(this.$valueRange, this.$minPx, this.$maxPx, p0));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Float invoke(Float f) {
                return invoke(f.floatValue());
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material.SliderKt$RangeSlider$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: Slider.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function1<Float, Float> {
            final /* synthetic */ Ref.FloatRef $maxPx;
            final /* synthetic */ Ref.FloatRef $minPx;
            final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(ClosedFloatingPointRange<Float> closedFloatingPointRange, Ref.FloatRef floatRef, Ref.FloatRef floatRef2) {
                super(1, Intrinsics.Kotlin.class, "scaleToOffset", "invoke$scaleToOffset(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/internal/Ref$FloatRef;Lkotlin/jvm/internal/Ref$FloatRef;F)F", 0);
                this.$valueRange = closedFloatingPointRange;
                this.$minPx = floatRef;
                this.$maxPx = floatRef2;
            }

            public final Float invoke(float p0) {
                return Float.valueOf(C02992.invoke$scaleToOffset(this.$valueRange, this.$minPx, this.$maxPx, p0));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Float invoke(Float f) {
                return invoke(f.floatValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SliderImpl(final boolean enabled, final float positionFraction, final List<Float> list, final SliderColors colors, final float width, final MutableInteractionSource interactionSource, final Modifier modifier, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(1679682785);
        ComposerKt.sourceInformation($composer2, "C(SliderImpl)P(1,4,5!1,6)588@24897L712:Slider.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1679682785, $changed, -1, "androidx.compose.material.SliderImpl (Slider.kt:579)");
        }
        Modifier modifier$iv = modifier.then(DefaultSliderConstraints);
        $composer2.startReplaceableGroup(733328855);
        ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
        int $changed$iv$iv = (0 << 3) & 112;
        $composer2.startReplaceableGroup(-1323940314);
        ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
        CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
        if (!($composer2.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer2.startReusableNode();
        if ($composer2.getInserting()) {
            $composer2.createNode(constructor);
        } else {
            $composer2.useNode();
        }
        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer2);
        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
        }
        function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
        $composer2.startReplaceableGroup(2058660585);
        int i = ($changed$iv$iv$iv >> 9) & 14;
        ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
        BoxScope $this$SliderImpl_u24lambda_u248 = BoxScopeInstance.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer2, 618023922, "C*592@25057L7,601@25302L216,611@25527L76:Slider.kt#jmzs0o");
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer2.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer2);
        Density $this$SliderImpl_u24lambda_u248_u24lambda_u247 = (Density) objConsume;
        float trackStrokeWidth = $this$SliderImpl_u24lambda_u248_u24lambda_u247.mo327toPx0680j_4(TrackHeight);
        float thumbPx = $this$SliderImpl_u24lambda_u248_u24lambda_u247.mo327toPx0680j_4(ThumbRadius);
        float widthDp = $this$SliderImpl_u24lambda_u248_u24lambda_u247.mo323toDpu2uoSUM(width);
        float arg0$iv = ThumbRadius;
        float thumbSize = Dp.m5274constructorimpl(2 * arg0$iv);
        float offset = Dp.m5274constructorimpl(widthDp * positionFraction);
        Track(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), colors, enabled, 0.0f, positionFraction, list, thumbPx, trackStrokeWidth, $composer2, (($changed >> 6) & 112) | 265222 | (($changed << 6) & 896) | (($changed << 9) & 57344));
        m1186SliderThumbPcYyNuk($this$SliderImpl_u24lambda_u248, Modifier.INSTANCE, offset, interactionSource, colors, enabled, thumbSize, $composer2, ((((0 >> 6) & 112) | 6) & 14) | 1572912 | (($changed >> 6) & 7168) | (($changed << 3) & 57344) | (($changed << 15) & 458752));
        ComposerKt.sourceInformationMarkerEnd($composer2);
        ComposerKt.sourceInformationMarkerEnd($composer2);
        $composer2.endReplaceableGroup();
        $composer2.endNode();
        $composer2.endReplaceableGroup();
        $composer2.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.SliderImpl.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i2) {
                SliderKt.SliderImpl(enabled, positionFraction, list, colors, width, interactionSource, modifier, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RangeSliderImpl(final boolean enabled, final float positionFractionStart, final float positionFractionEnd, final List<Float> list, final SliderColors colors, final float width, final MutableInteractionSource startInteractionSource, final MutableInteractionSource endInteractionSource, final Modifier modifier, final Modifier startThumbSemantics, final Modifier endThumbSemantics, Composer $composer, final int $changed, final int $changed1) {
        Function0<ComposeUiNode> function0;
        Object value$iv$iv;
        Composer $composer2 = $composer.startRestartGroup(-278895713);
        ComposerKt.sourceInformation($composer2, "C(RangeSliderImpl)P(1,6,5,9!1,10,7!1,4,8)630@26054L35,631@26122L33,632@26160L1522:Slider.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-278895713, $changed, $changed1, "androidx.compose.material.RangeSliderImpl (Slider.kt:616)");
        }
        final String startContentDescription = Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1207getSliderRangeStartUdPEhr4(), $composer2, 6);
        final String endContentDescription = Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1206getSliderRangeEndUdPEhr4(), $composer2, 6);
        Modifier modifier$iv = modifier.then(DefaultSliderConstraints);
        $composer2.startReplaceableGroup(733328855);
        ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
        int $changed$iv$iv = (0 << 3) & 112;
        $composer2.startReplaceableGroup(-1323940314);
        ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
        CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
        int $dirty1 = $changed$iv$iv << 9;
        int $changed$iv$iv$iv = ($dirty1 & 7168) | 6;
        if (!($composer2.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer2.startReusableNode();
        if ($composer2.getInserting()) {
            function0 = constructor;
            $composer2.createNode(function0);
        } else {
            function0 = constructor;
            $composer2.useNode();
        }
        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer2);
        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
        }
        function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
        $composer2.startReplaceableGroup(2058660585);
        int i = ($changed$iv$iv$iv >> 9) & 14;
        ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
        int $changed2 = ((0 >> 6) & 112) | 6;
        BoxScope $this$RangeSliderImpl_u24lambda_u2412 = BoxScopeInstance.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer2, -1690173328, "C*636@26320L7,645@26628L301,660@27025L48,658@26939L369,671@27403L46,669@27317L359:Slider.kt#jmzs0o");
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer2.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer2);
        Density $this$RangeSliderImpl_u24lambda_u2412_u24lambda_u249 = (Density) objConsume;
        float trackStrokeWidth = $this$RangeSliderImpl_u24lambda_u2412_u24lambda_u249.mo327toPx0680j_4(TrackHeight);
        float thumbPx = $this$RangeSliderImpl_u24lambda_u2412_u24lambda_u249.mo327toPx0680j_4(ThumbRadius);
        float widthDp = $this$RangeSliderImpl_u24lambda_u2412_u24lambda_u249.mo323toDpu2uoSUM(width);
        float arg0$iv = ThumbRadius;
        float thumbSize = Dp.m5274constructorimpl(2 * arg0$iv);
        float offsetStart = Dp.m5274constructorimpl(widthDp * positionFractionStart);
        float offsetEnd = Dp.m5274constructorimpl(widthDp * positionFractionEnd);
        Track(SizeKt.fillMaxSize$default($this$RangeSliderImpl_u24lambda_u2412.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart()), 0.0f, 1, null), colors, enabled, positionFractionStart, positionFractionEnd, list, thumbPx, trackStrokeWidth, $composer2, (($changed >> 9) & 112) | 262144 | (($changed << 6) & 896) | (($changed << 6) & 7168) | (($changed << 6) & 57344));
        Modifier.Companion companion = Modifier.INSTANCE;
        $composer2.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer2.changed(startContentDescription);
        Object it$iv$iv = $composer2.rememberedValue();
        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSliderImpl$1$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.setContentDescription(semantics, startContentDescription);
                }
            };
            $composer2.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        $composer2.endReplaceableGroup();
        m1186SliderThumbPcYyNuk($this$RangeSliderImpl_u24lambda_u2412, FocusableKt.focusable(SemanticsModifierKt.semantics(companion, true, (Function1) value$iv$iv), true, startInteractionSource).then(startThumbSemantics), offsetStart, startInteractionSource, colors, enabled, thumbSize, $composer2, ($changed2 & 14) | 1572864 | (($changed >> 9) & 7168) | ($changed & 57344) | (($changed << 15) & 458752));
        Modifier.Companion companion2 = Modifier.INSTANCE;
        $composer2.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean invalid$iv$iv2 = $composer2.changed(endContentDescription);
        Object value$iv$iv2 = $composer2.rememberedValue();
        if (invalid$iv$iv2 || value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSliderImpl$1$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.setContentDescription(semantics, endContentDescription);
                }
            };
            $composer2.updateRememberedValue(value$iv$iv2);
        }
        $composer2.endReplaceableGroup();
        m1186SliderThumbPcYyNuk($this$RangeSliderImpl_u24lambda_u2412, FocusableKt.focusable(SemanticsModifierKt.semantics(companion2, true, (Function1) value$iv$iv2), true, endInteractionSource).then(endThumbSemantics), offsetEnd, endInteractionSource, colors, enabled, thumbSize, $composer2, ($changed2 & 14) | 1572864 | (($changed >> 12) & 7168) | (57344 & $changed) | (($changed << 15) & 458752));
        ComposerKt.sourceInformationMarkerEnd($composer2);
        ComposerKt.sourceInformationMarkerEnd($composer2);
        $composer2.endReplaceableGroup();
        $composer2.endNode();
        $composer2.endReplaceableGroup();
        $composer2.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.RangeSliderImpl.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i2) {
                SliderKt.RangeSliderImpl(enabled, positionFractionStart, positionFractionEnd, list, colors, width, startInteractionSource, endInteractionSource, modifier, startThumbSemantics, endThumbSemantics, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SliderThumb-PcYyNuk, reason: not valid java name */
    public static final void m1186SliderThumbPcYyNuk(final BoxScope $this$SliderThumb_u2dPcYyNuk, final Modifier modifier, final float offset, final MutableInteractionSource interactionSource, final SliderColors colors, final boolean enabled, final float thumbSize, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        Object value$iv$iv;
        SliderKt$SliderThumb$1$1$1 value$iv$iv2;
        Composer $composer2 = $composer.startRestartGroup(428907178);
        ComposerKt.sourceInformation($composer2, "C(SliderThumb)P(3,4:c#ui.unit.Dp,2!,5:c#ui.unit.Dp)692@27895L1553:Slider.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed($this$SliderThumb_u2dPcYyNuk) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(offset) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty |= $composer2.changed(colors) ? 16384 : 8192;
        }
        if ((458752 & $changed) == 0) {
            $dirty |= $composer2.changed(enabled) ? 131072 : 65536;
        }
        if ((3670016 & $changed) == 0) {
            $dirty |= $composer2.changed(thumbSize) ? 1048576 : 524288;
        }
        int $dirty2 = $dirty;
        if ((2995931 & $dirty2) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(428907178, $dirty2, -1, "androidx.compose.material.SliderThumb (Slider.kt:684)");
            }
            Modifier modifier$iv = $this$SliderThumb_u2dPcYyNuk.align(PaddingKt.m491paddingqDBjuR0$default(Modifier.INSTANCE, offset, 0.0f, 0.0f, 0.0f, 14, null), Alignment.INSTANCE.getCenterStart());
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3 skippableUpdate$iv$iv$iv = LayoutKt.modifierMaterializerOf(modifier$iv);
            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -528162279, "C696@28026L46,697@28115L658,697@28081L692,720@29129L59,724@29393L19,715@28935L507:Slider.kt#jmzs0o");
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotStateKt.mutableStateListOf();
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            SnapshotStateList interactions = (SnapshotStateList) value$iv$iv;
            int i3 = (($dirty2 >> 9) & 14) | 48;
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(interactionSource) | $composer2.changed(interactions);
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new SliderKt$SliderThumb$1$1$1(interactionSource, interactions, null);
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv2, $composer2, (($dirty2 >> 9) & 14) | 64);
            float elevation = interactions.isEmpty() ^ true ? ThumbPressedElevation : ThumbDefaultElevation;
            SpacerKt.Spacer(BackgroundKt.m159backgroundbw27NRU(ShadowKt.m2643shadows4CzXII$default(HoverableKt.hoverable$default(IndicationKt.indication(SizeKt.m536sizeVpY3zN4(modifier, thumbSize, thumbSize), interactionSource, RippleKt.m1298rememberRipple9IZ8Weo(false, ThumbRippleRadius, 0L, $composer2, 54, 4)), interactionSource, false, 2, null), enabled ? elevation : Dp.m5274constructorimpl(0), RoundedCornerShapeKt.getCircleShape(), false, 0L, 0L, 24, null), colors.thumbColor(enabled, $composer2, (($dirty2 >> 15) & 14) | (($dirty2 >> 9) & 112)).getValue().m2981unboximpl(), RoundedCornerShapeKt.getCircleShape()), $composer2, 0);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt$SliderThumb$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i4) {
                SliderKt.m1186SliderThumbPcYyNuk($this$SliderThumb_u2dPcYyNuk, modifier, offset, interactionSource, colors, enabled, thumbSize, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Track(final Modifier modifier, final SliderColors colors, final boolean enabled, final float positionFractionStart, final float positionFractionEnd, final List<Float> list, final float thumbPx, final float trackStrokeWidth, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(1833126050);
        ComposerKt.sourceInformation($composer2, "C(Track)P(2!2,4!1,6)740@29741L35,741@29811L34,742@29881L34,743@29949L33,744@29987L1514:Slider.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1833126050, $changed, -1, "androidx.compose.material.Track (Slider.kt:730)");
        }
        final State<Color> stateTrackColor = colors.trackColor(enabled, false, $composer2, (($changed >> 6) & 14) | 48 | (($changed << 3) & 896));
        final State<Color> stateTrackColor2 = colors.trackColor(enabled, true, $composer2, (($changed >> 6) & 14) | 48 | (($changed << 3) & 896));
        final State<Color> stateTickColor = colors.tickColor(enabled, false, $composer2, (($changed >> 6) & 14) | 48 | (($changed << 3) & 896));
        final State<Color> stateTickColor2 = colors.tickColor(enabled, true, $composer2, (($changed >> 6) & 14) | 48 | (($changed << 3) & 896));
        CanvasKt.Canvas(modifier, new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.SliderKt.Track.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope Canvas) {
                Object answer$iv$iv$iv;
                Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
                boolean isRtl = Canvas.getLayoutDirection() == LayoutDirection.Rtl;
                long sliderLeft = OffsetKt.Offset(thumbPx, Offset.m2732getYimpl(Canvas.mo3441getCenterF1C5BW0()));
                long sliderRight = OffsetKt.Offset(Size.m2800getWidthimpl(Canvas.mo3442getSizeNHjbRc()) - thumbPx, Offset.m2732getYimpl(Canvas.mo3441getCenterF1C5BW0()));
                long sliderStart = isRtl ? sliderRight : sliderLeft;
                long sliderEnd = isRtl ? sliderLeft : sliderRight;
                long sliderEnd2 = sliderEnd;
                long sliderStart2 = sliderStart;
                DrawScope.CC.m3514drawLineNGM6Ib0$default(Canvas, stateTrackColor.getValue().m2981unboximpl(), sliderStart, sliderEnd, trackStrokeWidth, StrokeCap.INSTANCE.m3325getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                long sliderValueEnd = OffsetKt.Offset(Offset.m2731getXimpl(sliderStart2) + ((Offset.m2731getXimpl(sliderEnd2) - Offset.m2731getXimpl(sliderStart2)) * positionFractionEnd), Offset.m2732getYimpl(Canvas.mo3441getCenterF1C5BW0()));
                long sliderValueStart = OffsetKt.Offset(Offset.m2731getXimpl(sliderStart2) + ((Offset.m2731getXimpl(sliderEnd2) - Offset.m2731getXimpl(sliderStart2)) * positionFractionStart), Offset.m2732getYimpl(Canvas.mo3441getCenterF1C5BW0()));
                DrawScope.CC.m3514drawLineNGM6Ib0$default(Canvas, stateTrackColor2.getValue().m2981unboximpl(), sliderValueStart, sliderValueEnd, trackStrokeWidth, StrokeCap.INSTANCE.m3325getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                Iterable $this$groupBy$iv = list;
                float f = positionFractionEnd;
                float f2 = positionFractionStart;
                Map destination$iv$iv = new LinkedHashMap();
                for (Object element$iv$iv : $this$groupBy$iv) {
                    float it = ((Number) element$iv$iv).floatValue();
                    Boolean boolValueOf = Boolean.valueOf(it > f || it < f2);
                    Object value$iv$iv$iv = destination$iv$iv.get(boolValueOf);
                    if (value$iv$iv$iv == null) {
                        answer$iv$iv$iv = new ArrayList();
                        destination$iv$iv.put(boolValueOf, answer$iv$iv$iv);
                    } else {
                        answer$iv$iv$iv = value$iv$iv$iv;
                    }
                    List list$iv$iv = (List) answer$iv$iv$iv;
                    list$iv$iv.add(element$iv$iv);
                }
                State<Color> state = stateTickColor;
                State<Color> state2 = stateTickColor2;
                float f3 = trackStrokeWidth;
                for (Map.Entry element$iv : destination$iv$iv.entrySet()) {
                    boolean outsideFraction = ((Boolean) element$iv.getKey()).booleanValue();
                    Iterable list2 = (List) element$iv.getValue();
                    Iterable $this$map$iv = list2;
                    Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    for (Object item$iv$iv : $this$map$iv) {
                        long sliderStart3 = sliderStart2;
                        long sliderEnd3 = sliderEnd2;
                        destination$iv$iv2.add(Offset.m2720boximpl(OffsetKt.Offset(Offset.m2731getXimpl(OffsetKt.m2754lerpWko1d7g(sliderStart3, sliderEnd3, ((Number) item$iv$iv).floatValue())), Offset.m2732getYimpl(Canvas.mo3441getCenterF1C5BW0()))));
                        sliderStart2 = sliderStart3;
                        isRtl = isRtl;
                        state = state;
                        state2 = state2;
                        sliderEnd2 = sliderEnd3;
                    }
                    boolean isRtl2 = isRtl;
                    long sliderStart4 = sliderStart2;
                    long sliderEnd4 = sliderEnd2;
                    State<Color> state3 = state2;
                    State<Color> state4 = state;
                    DrawScope.CC.m3519drawPointsF8ZwMP8$default(Canvas, (List) destination$iv$iv2, PointMode.INSTANCE.m3277getPointsr_lszbg(), (outsideFraction ? state4 : state3).getValue().m2981unboximpl(), f3, StrokeCap.INSTANCE.m3325getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                    f3 = f3;
                    state2 = state3;
                    state = state4;
                    sliderEnd2 = sliderEnd4;
                    isRtl = isRtl2;
                    sliderStart2 = sliderStart4;
                }
            }
        }, $composer2, $changed & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.Track.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i) {
                SliderKt.Track(modifier, colors, enabled, positionFractionStart, positionFractionEnd, list, thumbPx, trackStrokeWidth, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float snapValueToTick(float current, List<Float> list, float minPx, float maxPx) {
        Object minElem$iv;
        List<Float> $this$minByOrNull$iv = list;
        Iterator iterator$iv = $this$minByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            minElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                float it = ((Number) minElem$iv).floatValue();
                float minValue$iv = Math.abs(MathHelpersKt.lerp(minPx, maxPx, it) - current);
                do {
                    Object e$iv = iterator$iv.next();
                    float it2 = ((Number) e$iv).floatValue();
                    float v$iv = Math.abs(MathHelpersKt.lerp(minPx, maxPx, it2) - current);
                    if (Float.compare(minValue$iv, v$iv) > 0) {
                        minElem$iv = e$iv;
                        minValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            minElem$iv = null;
        }
        Object minElem$iv2 = (Float) minElem$iv;
        if (minElem$iv2 == null) {
            return current;
        }
        float $this$snapValueToTick_u24lambda_u2417 = ((Number) minElem$iv2).floatValue();
        return MathHelpersKt.lerp(minPx, maxPx, $this$snapValueToTick_u24lambda_u2417);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: awaitSlop-8vUncbI, reason: not valid java name */
    public static final Object m1189awaitSlop8vUncbI(AwaitPointerEventScope $this$awaitSlop_u2d8vUncbI, long id, int type, Continuation<? super Pair<PointerInputChange, Float>> continuation) {
        SliderKt$awaitSlop$1 sliderKt$awaitSlop$1;
        final Ref.FloatRef initialDelta;
        Object objM1084awaitHorizontalPointerSlopOrCancellationgDDlDlE;
        if (continuation instanceof SliderKt$awaitSlop$1) {
            sliderKt$awaitSlop$1 = (SliderKt$awaitSlop$1) continuation;
            if ((sliderKt$awaitSlop$1.label & Integer.MIN_VALUE) != 0) {
                sliderKt$awaitSlop$1.label -= Integer.MIN_VALUE;
            } else {
                sliderKt$awaitSlop$1 = new SliderKt$awaitSlop$1(continuation);
            }
        } else {
            sliderKt$awaitSlop$1 = new SliderKt$awaitSlop$1(continuation);
        }
        SliderKt$awaitSlop$1 sliderKt$awaitSlop$2 = sliderKt$awaitSlop$1;
        Object $result = sliderKt$awaitSlop$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (sliderKt$awaitSlop$2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                initialDelta = new Ref.FloatRef();
                Function2<PointerInputChange, Float, Unit> function2 = new Function2<PointerInputChange, Float, Unit>() { // from class: androidx.compose.material.SliderKt$awaitSlop$postPointerSlop$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Float f) {
                        invoke(pointerInputChange, f.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(PointerInputChange pointerInput, float offset) {
                        Intrinsics.checkNotNullParameter(pointerInput, "pointerInput");
                        pointerInput.consume();
                        initialDelta.element = offset;
                    }
                };
                sliderKt$awaitSlop$2.L$0 = initialDelta;
                sliderKt$awaitSlop$2.label = 1;
                objM1084awaitHorizontalPointerSlopOrCancellationgDDlDlE = DragGestureDetectorCopyKt.m1084awaitHorizontalPointerSlopOrCancellationgDDlDlE($this$awaitSlop_u2d8vUncbI, id, type, function2, sliderKt$awaitSlop$2);
                if (objM1084awaitHorizontalPointerSlopOrCancellationgDDlDlE == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                initialDelta = (Ref.FloatRef) sliderKt$awaitSlop$2.L$0;
                ResultKt.throwOnFailure($result);
                objM1084awaitHorizontalPointerSlopOrCancellationgDDlDlE = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        PointerInputChange afterSlopResult = (PointerInputChange) objM1084awaitHorizontalPointerSlopOrCancellationgDDlDlE;
        if (afterSlopResult != null) {
            return TuplesKt.to(afterSlopResult, Boxing.boxFloat(initialDelta.element));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Float> stepsToTickFractions(int steps) {
        if (steps == 0) {
            return CollectionsKt.emptyList();
        }
        int i = steps + 2;
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            int it = i2;
            arrayList.add(Float.valueOf(it / (steps + 1)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float scale(float a1, float b1, float x1, float a2, float b2) {
        return MathHelpersKt.lerp(a2, b2, calcFraction(a1, b1, x1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClosedFloatingPointRange<Float> scale(float a1, float b1, ClosedFloatingPointRange<Float> closedFloatingPointRange, float a2, float b2) {
        return RangesKt.rangeTo(scale(a1, b1, closedFloatingPointRange.getStart().floatValue(), a2, b2), scale(a1, b1, closedFloatingPointRange.getEndInclusive().floatValue(), a2, b2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calcFraction(float a, float b, float pos) {
        return RangesKt.coerceIn(((b - a) > 0.0f ? 1 : ((b - a) == 0.0f ? 0 : -1)) == 0 ? 0.0f : (pos - a) / (b - a), 0.0f, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CorrectValueSideEffect(final Function1<? super Float, Float> function1, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final ClosedFloatingPointRange<Float> closedFloatingPointRange2, final MutableState<Float> mutableState, final float value, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-743965752);
        ComposerKt.sourceInformation($composer2, "C(CorrectValueSideEffect)P(!1,3!1,4)839@33355L300,839@33344L311:Slider.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(closedFloatingPointRange) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(closedFloatingPointRange2) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(mutableState) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty |= $composer2.changed(value) ? 16384 : 8192;
        }
        if ((46811 & $dirty) != 9362 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-743965752, $changed, -1, "androidx.compose.material.CorrectValueSideEffect (Slider.kt:832)");
            }
            Object[] keys$iv = {closedFloatingPointRange, function1, Float.valueOf(value), mutableState, closedFloatingPointRange2};
            $composer2.startReplaceableGroup(-568225417);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv = false;
            for (Object key$iv : keys$iv) {
                invalid$iv |= $composer2.changed(key$iv);
            }
            Object value$iv$iv = $composer2.rememberedValue();
            if (invalid$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new Function0<Unit>() { // from class: androidx.compose.material.SliderKt$CorrectValueSideEffect$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        float error = (closedFloatingPointRange.getEndInclusive().floatValue() - closedFloatingPointRange.getStart().floatValue()) / 1000;
                        float newOffset = function1.invoke(Float.valueOf(value)).floatValue();
                        if (Math.abs(newOffset - mutableState.getValue().floatValue()) > error && closedFloatingPointRange2.contains(mutableState.getValue())) {
                            mutableState.setValue(Float.valueOf(newOffset));
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            }
            $composer2.endReplaceableGroup();
            EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.CorrectValueSideEffect.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i) {
                SliderKt.CorrectValueSideEffect(function1, closedFloatingPointRange, closedFloatingPointRange2, mutableState, value, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier sliderSemantics(Modifier $this$sliderSemantics, float value, final boolean enabled, final Function1<? super Float, Unit> function1, final Function0<Unit> function0, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final int steps) {
        final float coerced = RangesKt.coerceIn(value, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default($this$sliderSemantics, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.SliderKt.sliderSemantics.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semantics) {
                Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                if (!enabled) {
                    SemanticsPropertiesKt.disabled(semantics);
                }
                final ClosedFloatingPointRange<Float> closedFloatingPointRange2 = closedFloatingPointRange;
                final int i = steps;
                final float f = coerced;
                final Function1<Float, Unit> function2 = function1;
                final Function0<Unit> function3 = function0;
                SemanticsPropertiesKt.setProgress$default(semantics, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material.SliderKt.sliderSemantics.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f2) {
                        return invoke(f2.floatValue());
                    }

                    public final Boolean invoke(float targetValue) {
                        float newValue = RangesKt.coerceIn(targetValue, closedFloatingPointRange2.getStart().floatValue(), closedFloatingPointRange2.getEndInclusive().floatValue());
                        int i2 = i;
                        boolean z = true;
                        if (i2 > 0) {
                            float distance = newValue;
                            int i3 = 0;
                            int i4 = i2 + 1;
                            if (0 <= i4) {
                                while (true) {
                                    float stepValue = MathHelpersKt.lerp(closedFloatingPointRange2.getStart().floatValue(), closedFloatingPointRange2.getEndInclusive().floatValue(), i3 / (i + 1));
                                    if (Math.abs(stepValue - newValue) <= distance) {
                                        distance = Math.abs(stepValue - newValue);
                                        newValue = stepValue;
                                    }
                                    if (i3 == i4) {
                                        break;
                                    }
                                    i3++;
                                }
                            }
                        }
                        float resolvedValue = newValue;
                        if (resolvedValue == f) {
                            z = false;
                        } else {
                            function2.invoke(Float.valueOf(resolvedValue));
                            Function0<Unit> function4 = function3;
                            if (function4 != null) {
                                function4.invoke();
                            }
                        }
                        return Boolean.valueOf(z);
                    }
                }, 1, null);
            }
        }, 1, null), value, closedFloatingPointRange, steps);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier sliderTapModifier(Modifier $this$sliderTapModifier, final DraggableState draggableState, final MutableInteractionSource interactionSource, final float maxPx, final boolean isRtl, final State<Float> state, final State<? extends Function1<? super Float, Unit>> state2, final MutableState<Float> mutableState, final boolean enabled) {
        return ComposedModifierKt.composed($this$sliderTapModifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.SliderKt$sliderTapModifier$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                $this$null.setName("sliderTapModifier");
                $this$null.getProperties().set("draggableState", draggableState);
                $this$null.getProperties().set("interactionSource", interactionSource);
                $this$null.getProperties().set("maxPx", Float.valueOf(maxPx));
                $this$null.getProperties().set("isRtl", Boolean.valueOf(isRtl));
                $this$null.getProperties().set("rawOffset", state);
                $this$null.getProperties().set("gestureEndAction", state2);
                $this$null.getProperties().set("pressOffset", mutableState);
                $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material.SliderKt.sliderTapModifier.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer $composer, int $changed) {
                Modifier modifierPointerInput;
                Object value$iv$iv$iv;
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                $composer.startReplaceableGroup(1945228890);
                ComposerKt.sourceInformation($composer, "C907@35754L24:Slider.kt#jmzs0o");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1945228890, $changed, -1, "androidx.compose.material.sliderTapModifier.<anonymous> (Slider.kt:905)");
                }
                if (enabled) {
                    $composer.startReplaceableGroup(773894976);
                    ComposerKt.sourceInformation($composer, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
                    $composer.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
                    Object it$iv$iv$iv = $composer.rememberedValue();
                    if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer));
                        $composer.updateRememberedValue(value$iv$iv$iv);
                    } else {
                        value$iv$iv$iv = it$iv$iv$iv;
                    }
                    $composer.endReplaceableGroup();
                    CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
                    CoroutineScope scope = wrapper$iv.getCoroutineScope();
                    $composer.endReplaceableGroup();
                    modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(composed, new Object[]{draggableState, interactionSource, Float.valueOf(maxPx), Boolean.valueOf(isRtl)}, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) new AnonymousClass1(isRtl, maxPx, mutableState, state, scope, draggableState, state2, null));
                } else {
                    modifierPointerInput = composed;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceableGroup();
                return modifierPointerInput;
            }

            /* JADX INFO: renamed from: androidx.compose.material.SliderKt$sliderTapModifier$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: Slider.kt */
            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            @DebugMetadata(c = "androidx.compose.material.SliderKt$sliderTapModifier$2$1", f = "Slider.kt", i = {}, l = {910}, m = "invokeSuspend", n = {}, s = {})
            static final class AnonymousClass1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ DraggableState $draggableState;
                final /* synthetic */ State<Function1<Float, Unit>> $gestureEndAction;
                final /* synthetic */ boolean $isRtl;
                final /* synthetic */ float $maxPx;
                final /* synthetic */ MutableState<Float> $pressOffset;
                final /* synthetic */ State<Float> $rawOffset;
                final /* synthetic */ CoroutineScope $scope;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass1(boolean z, float f, MutableState<Float> mutableState, State<Float> state, CoroutineScope coroutineScope, DraggableState draggableState, State<? extends Function1<? super Float, Unit>> state2, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$isRtl = z;
                    this.$maxPx = f;
                    this.$pressOffset = mutableState;
                    this.$rawOffset = state;
                    this.$scope = coroutineScope;
                    this.$draggableState = draggableState;
                    this.$gestureEndAction = state2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$isRtl, this.$maxPx, this.$pressOffset, this.$rawOffset, this.$scope, this.$draggableState, this.$gestureEndAction, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX INFO: renamed from: androidx.compose.material.SliderKt$sliderTapModifier$2$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: Slider.kt */
                @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@"}, d2 = {"Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/geometry/Offset;", "pos", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                @DebugMetadata(c = "androidx.compose.material.SliderKt$sliderTapModifier$2$1$1", f = "Slider.kt", i = {}, l = {915}, m = "invokeSuspend", n = {}, s = {})
                static final class C00601 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
                    final /* synthetic */ boolean $isRtl;
                    final /* synthetic */ float $maxPx;
                    final /* synthetic */ MutableState<Float> $pressOffset;
                    final /* synthetic */ State<Float> $rawOffset;
                    /* synthetic */ long J$0;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00601(boolean z, float f, MutableState<Float> mutableState, State<Float> state, Continuation<? super C00601> continuation) {
                        super(3, continuation);
                        this.$isRtl = z;
                        this.$maxPx = f;
                        this.$pressOffset = mutableState;
                        this.$rawOffset = state;
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
                        return m1190invoked4ec7I(pressGestureScope, offset.getPackedValue(), continuation);
                    }

                    /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
                    public final Object m1190invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
                        C00601 c00601 = new C00601(this.$isRtl, this.$maxPx, this.$pressOffset, this.$rawOffset, continuation);
                        c00601.L$0 = pressGestureScope;
                        c00601.J$0 = j;
                        return c00601.invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        C00601 c00601;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                PressGestureScope $this$detectTapGestures = (PressGestureScope) this.L$0;
                                long pos = this.J$0;
                                float to = this.$isRtl ? this.$maxPx - Offset.m2731getXimpl(pos) : Offset.m2731getXimpl(pos);
                                this.$pressOffset.setValue(Boxing.boxFloat(to - this.$rawOffset.getValue().floatValue()));
                                try {
                                    this.label = 1;
                                    if ($this$detectTapGestures.awaitRelease(this) != coroutine_suspended) {
                                        return Unit.INSTANCE;
                                    }
                                    return coroutine_suspended;
                                } catch (GestureCancellationException e) {
                                    c00601 = this;
                                    c00601.$pressOffset.setValue(Boxing.boxFloat(0.0f));
                                }
                                break;
                            case 1:
                                c00601 = this;
                                try {
                                    ResultKt.throwOnFailure($result);
                                    break;
                                } catch (GestureCancellationException e2) {
                                    c00601.$pressOffset.setValue(Boxing.boxFloat(0.0f));
                                }
                                return Unit.INSTANCE;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                            C00601 c00601 = new C00601(this.$isRtl, this.$maxPx, this.$pressOffset, this.$rawOffset, null);
                            final CoroutineScope coroutineScope = this.$scope;
                            final DraggableState draggableState = this.$draggableState;
                            final State<Function1<Float, Unit>> state = this.$gestureEndAction;
                            Function1<Offset, Unit> function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.material.SliderKt.sliderTapModifier.2.1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                                    m1191invokek4lQ0M(offset.getPackedValue());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                                public final void m1191invokek4lQ0M(long it) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00621(draggableState, state, null), 3, null);
                                }

                                /* JADX INFO: renamed from: androidx.compose.material.SliderKt$sliderTapModifier$2$1$2$1, reason: invalid class name and collision with other inner class name */
                                /* JADX INFO: compiled from: Slider.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                @DebugMetadata(c = "androidx.compose.material.SliderKt$sliderTapModifier$2$1$2$1", f = "Slider.kt", i = {}, l = {922}, m = "invokeSuspend", n = {}, s = {})
                                static final class C00621 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ DraggableState $draggableState;
                                    final /* synthetic */ State<Function1<Float, Unit>> $gestureEndAction;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    C00621(DraggableState draggableState, State<? extends Function1<? super Float, Unit>> state, Continuation<? super C00621> continuation) {
                                        super(2, continuation);
                                        this.$draggableState = draggableState;
                                        this.$gestureEndAction = state;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new C00621(this.$draggableState, this.$gestureEndAction, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((C00621) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    /* JADX INFO: renamed from: androidx.compose.material.SliderKt$sliderTapModifier$2$1$2$1$1, reason: invalid class name and collision with other inner class name */
                                    /* JADX INFO: compiled from: Slider.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/foundation/gestures/DragScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                    @DebugMetadata(c = "androidx.compose.material.SliderKt$sliderTapModifier$2$1$2$1$1", f = "Slider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                                    static final class C00631 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
                                        private /* synthetic */ Object L$0;
                                        int label;

                                        C00631(Continuation<? super C00631> continuation) {
                                            super(2, continuation);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            C00631 c00631 = new C00631(continuation);
                                            c00631.L$0 = obj;
                                            return c00631;
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
                                            return ((C00631) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object obj) {
                                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            switch (this.label) {
                                                case 0:
                                                    ResultKt.throwOnFailure(obj);
                                                    DragScope $this$drag = (DragScope) this.L$0;
                                                    $this$drag.dragBy(0.0f);
                                                    return Unit.INSTANCE;
                                                default:
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                        }
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object $result) {
                                        C00621 c00621;
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch (this.label) {
                                            case 0:
                                                ResultKt.throwOnFailure($result);
                                                this.label = 1;
                                                if (this.$draggableState.drag(MutatePriority.UserInput, new C00631(null), this) != coroutine_suspended) {
                                                    c00621 = this;
                                                } else {
                                                    return coroutine_suspended;
                                                }
                                                break;
                                            case 1:
                                                c00621 = this;
                                                ResultKt.throwOnFailure($result);
                                                break;
                                            default:
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                        }
                                        c00621.$gestureEndAction.getValue().invoke(Boxing.boxFloat(0.0f));
                                        return Unit.INSTANCE;
                                    }
                                }
                            };
                            this.label = 1;
                            if (TapGestureDetectorKt.detectTapGestures($this$pointerInput, (3 & 1) != 0 ? null : null, (3 & 2) != 0 ? null : null, (3 & 4) != 0 ? TapGestureDetectorKt.NoPressGesture : c00601, (3 & 8) != 0 ? null : function1, this) == coroutine_suspended) {
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
        });
    }

    /* JADX INFO: renamed from: androidx.compose.material.SliderKt$animateToTarget$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/foundation/gestures/DragScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.material.SliderKt$animateToTarget$2", f = "Slider.kt", i = {}, l = {955}, m = "invokeSuspend", n = {}, s = {})
    static final class C03042 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ float $current;
        final /* synthetic */ float $target;
        final /* synthetic */ float $velocity;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03042(float f, float f2, float f3, Continuation<? super C03042> continuation) {
            super(2, continuation);
            this.$current = f;
            this.$target = f2;
            this.$velocity = f3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03042 c03042 = new C03042(this.$current, this.$target, this.$velocity, continuation);
            c03042.L$0 = obj;
            return c03042;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
            return ((C03042) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final DragScope $this$drag = (DragScope) this.L$0;
                    final Ref.FloatRef latestValue = new Ref.FloatRef();
                    latestValue.element = this.$current;
                    this.label = 1;
                    if (AnimatableKt.Animatable$default(this.$current, 0.0f, 2, null).animateTo(Boxing.boxFloat(this.$target), SliderKt.SliderToTickAnimation, Boxing.boxFloat(this.$velocity), new Function1<Animatable<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material.SliderKt.animateToTarget.2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Animatable<Float, AnimationVector1D> animatable) {
                            invoke2(animatable);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Animatable<Float, AnimationVector1D> animateTo) {
                            Intrinsics.checkNotNullParameter(animateTo, "$this$animateTo");
                            $this$drag.dragBy(animateTo.getValue().floatValue() - latestValue.element);
                            latestValue.element = animateTo.getValue().floatValue();
                        }
                    }, this) == coroutine_suspended) {
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
    public static final Object animateToTarget(DraggableState draggableState, float current, float target, float velocity, Continuation<? super Unit> continuation) {
        Object objDrag$default = DraggableState.CC.drag$default(draggableState, null, new C03042(current, target, velocity, null), continuation, 1, null);
        return objDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDrag$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1", f = "Slider.kt", i = {}, l = {983}, m = "invokeSuspend", n = {}, s = {})
    static final class C03051 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MutableInteractionSource $endInteractionSource;
        final /* synthetic */ State<Function1<Boolean, Unit>> $gestureEndAction;
        final /* synthetic */ boolean $isRtl;
        final /* synthetic */ float $maxPx;
        final /* synthetic */ State<Function2<Boolean, Float, Unit>> $onDrag;
        final /* synthetic */ State<Float> $rawOffsetEnd;
        final /* synthetic */ State<Float> $rawOffsetStart;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03051(MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, State<Float> state, State<Float> state2, State<? extends Function2<? super Boolean, ? super Float, Unit>> state3, boolean z, float f, State<? extends Function1<? super Boolean, Unit>> state4, Continuation<? super C03051> continuation) {
            super(2, continuation);
            this.$startInteractionSource = mutableInteractionSource;
            this.$endInteractionSource = mutableInteractionSource2;
            this.$rawOffsetStart = state;
            this.$rawOffsetEnd = state2;
            this.$onDrag = state3;
            this.$isRtl = z;
            this.$maxPx = f;
            this.$gestureEndAction = state4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03051 c03051 = new C03051(this.$startInteractionSource, this.$endInteractionSource, this.$rawOffsetStart, this.$rawOffsetEnd, this.$onDrag, this.$isRtl, this.$maxPx, this.$gestureEndAction, continuation);
            c03051.L$0 = obj;
            return c03051;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C03051) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                    RangeSliderLogic rangeSliderLogic = new RangeSliderLogic(this.$startInteractionSource, this.$endInteractionSource, this.$rawOffsetStart, this.$rawOffsetEnd, this.$onDrag);
                    this.label = 1;
                    if (CoroutineScopeKt.coroutineScope(new C00571($this$pointerInput, this.$isRtl, this.$maxPx, rangeSliderLogic, this.$rawOffsetStart, this.$gestureEndAction, this.$rawOffsetEnd, this.$onDrag, null), this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Slider.kt */
        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1", f = "Slider.kt", i = {}, l = {984}, m = "invokeSuspend", n = {}, s = {})
        static final class C00571 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ State<Function1<Boolean, Unit>> $gestureEndAction;
            final /* synthetic */ boolean $isRtl;
            final /* synthetic */ float $maxPx;
            final /* synthetic */ State<Function2<Boolean, Float, Unit>> $onDrag;
            final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
            final /* synthetic */ State<Float> $rawOffsetEnd;
            final /* synthetic */ State<Float> $rawOffsetStart;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00571(PointerInputScope pointerInputScope, boolean z, float f, RangeSliderLogic rangeSliderLogic, State<Float> state, State<? extends Function1<? super Boolean, Unit>> state2, State<Float> state3, State<? extends Function2<? super Boolean, ? super Float, Unit>> state4, Continuation<? super C00571> continuation) {
                super(2, continuation);
                this.$$this$pointerInput = pointerInputScope;
                this.$isRtl = z;
                this.$maxPx = f;
                this.$rangeSliderLogic = rangeSliderLogic;
                this.$rawOffsetStart = state;
                this.$gestureEndAction = state2;
                this.$rawOffsetEnd = state3;
                this.$onDrag = state4;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00571 c00571 = new C00571(this.$$this$pointerInput, this.$isRtl, this.$maxPx, this.$rangeSliderLogic, this.$rawOffsetStart, this.$gestureEndAction, this.$rawOffsetEnd, this.$onDrag, continuation);
                c00571.L$0 = obj;
                return c00571;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00571) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: Slider.kt */
            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1", f = "Slider.kt", i = {0, 1, 1, 1, 1, 1, 2, 2}, l = {985, 995, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", NotificationCompat.CATEGORY_EVENT, "interaction", "posX", "draggingStart", "interaction", "draggingStart"}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1"})
            static final class C00581 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ CoroutineScope $$this$coroutineScope;
                final /* synthetic */ State<Function1<Boolean, Unit>> $gestureEndAction;
                final /* synthetic */ boolean $isRtl;
                final /* synthetic */ float $maxPx;
                final /* synthetic */ State<Function2<Boolean, Float, Unit>> $onDrag;
                final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                final /* synthetic */ State<Float> $rawOffsetEnd;
                final /* synthetic */ State<Float> $rawOffsetStart;
                private /* synthetic */ Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                Object L$4;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C00581(boolean z, float f, RangeSliderLogic rangeSliderLogic, State<Float> state, CoroutineScope coroutineScope, State<? extends Function1<? super Boolean, Unit>> state2, State<Float> state3, State<? extends Function2<? super Boolean, ? super Float, Unit>> state4, Continuation<? super C00581> continuation) {
                    super(2, continuation);
                    this.$isRtl = z;
                    this.$maxPx = f;
                    this.$rangeSliderLogic = rangeSliderLogic;
                    this.$rawOffsetStart = state;
                    this.$$this$coroutineScope = coroutineScope;
                    this.$gestureEndAction = state2;
                    this.$rawOffsetEnd = state3;
                    this.$onDrag = state4;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00581 c00581 = new C00581(this.$isRtl, this.$maxPx, this.$rangeSliderLogic, this.$rawOffsetStart, this.$$this$coroutineScope, this.$gestureEndAction, this.$rawOffsetEnd, this.$onDrag, continuation);
                    c00581.L$0 = obj;
                    return c00581;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((C00581) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:19:0x0089  */
                /* JADX WARN: Code duplicated, block: B:20:0x0095  */
                /* JADX WARN: Code duplicated, block: B:23:0x00ae A[DONT_INVERT] */
                /* JADX WARN: Code duplicated, block: B:24:0x00b0  */
                /* JADX WARN: Code duplicated, block: B:25:0x00b2  */
                /* JADX WARN: Code duplicated, block: B:26:0x00b4  */
                /* JADX WARN: Code duplicated, block: B:28:0x00c6  */
                /* JADX WARN: Code duplicated, block: B:29:0x00c8  */
                /* JADX WARN: Code duplicated, block: B:32:0x00e9 A[RETURN] */
                /* JADX WARN: Code duplicated, block: B:33:0x00ea  */
                /* JADX WARN: Code duplicated, block: B:36:0x00f2  */
                /* JADX WARN: Code duplicated, block: B:38:0x011a  */
                /* JADX WARN: Code duplicated, block: B:41:0x0131  */
                /* JADX WARN: Code duplicated, block: B:44:0x0135  */
                /* JADX WARN: Code duplicated, block: B:46:0x0142  */
                /* JADX WARN: Code duplicated, block: B:49:0x0147  */
                /* JADX WARN: Code duplicated, block: B:58:0x019a A[RETURN] */
                /* JADX WARN: Code duplicated, block: B:59:0x019b  */
                /* JADX WARN: Code duplicated, block: B:62:0x01ab A[Catch: CancellationException -> 0x01bc, TryCatch #1 {CancellationException -> 0x01bc, blocks: (B:60:0x01a2, B:62:0x01ab, B:63:0x01b3), top: B:75:0x01a2 }] */
                /* JADX WARN: Code duplicated, block: B:63:0x01b3 A[Catch: CancellationException -> 0x01bc, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x01bc, blocks: (B:60:0x01a2, B:62:0x01ab, B:63:0x01b3), top: B:75:0x01a2 }] */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    C00581 c00581;
                    AwaitPointerEventScope $this$awaitEachGesture;
                    Object $result2;
                    Object $result3;
                    PointerInputChange event;
                    DragInteraction.Start interaction;
                    Ref.FloatRef posX;
                    float fM2731getXimpl;
                    int compare;
                    Ref.BooleanRef draggingStart;
                    boolean z;
                    Object objM1189awaitSlop8vUncbI;
                    DragInteraction.Start interaction2;
                    Object $result4;
                    Object $result5;
                    final Ref.BooleanRef draggingStart2;
                    Pair it;
                    Ref.BooleanRef draggingStart3;
                    DragInteraction.Start interaction3;
                    Object objM274horizontalDragjO51t88;
                    Object $result6;
                    State<Float> state;
                    boolean z2;
                    float slop;
                    boolean shouldUpdateCapturedThumb;
                    DragInteraction.Cancel finishInteraction;
                    boolean success;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            c00581 = this;
                            AwaitPointerEventScope $this$awaitEachGesture2 = (AwaitPointerEventScope) c00581.L$0;
                            c00581.L$0 = $this$awaitEachGesture2;
                            c00581.label = 1;
                            Object objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture2, false, null, c00581, 2, null);
                            if (objAwaitFirstDown$default == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$awaitEachGesture = $this$awaitEachGesture2;
                            $result2 = $result;
                            $result3 = objAwaitFirstDown$default;
                            event = (PointerInputChange) $result3;
                            interaction = new DragInteraction.Start();
                            posX = new Ref.FloatRef();
                            if (c00581.$isRtl) {
                                fM2731getXimpl = c00581.$maxPx - Offset.m2731getXimpl(event.getPosition());
                            } else {
                                fM2731getXimpl = Offset.m2731getXimpl(event.getPosition());
                            }
                            posX.element = fM2731getXimpl;
                            compare = c00581.$rangeSliderLogic.compareOffsets(posX.element);
                            draggingStart = new Ref.BooleanRef();
                            if (compare != 0) {
                                if (compare < 0) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                            } else if (c00581.$rawOffsetStart.getValue().floatValue() > posX.element) {
                                z = true;
                            } else {
                                z = false;
                            }
                            draggingStart.element = z;
                            c00581.L$0 = $this$awaitEachGesture;
                            c00581.L$1 = event;
                            c00581.L$2 = interaction;
                            c00581.L$3 = posX;
                            c00581.L$4 = draggingStart;
                            c00581.label = 2;
                            objM1189awaitSlop8vUncbI = SliderKt.m1189awaitSlop8vUncbI($this$awaitEachGesture, event.getId(), event.getType(), c00581);
                            if (objM1189awaitSlop8vUncbI == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            interaction2 = interaction;
                            $result4 = objM1189awaitSlop8vUncbI;
                            $result5 = $result2;
                            draggingStart2 = draggingStart;
                            it = (Pair) $result4;
                            if (it != null) {
                                state = c00581.$rawOffsetEnd;
                                State<Float> state2 = c00581.$rawOffsetStart;
                                z2 = c00581.$isRtl;
                                slop = DragGestureDetectorCopyKt.m1087pointerSlopE8SPZFQ($this$awaitEachGesture.getViewConfiguration(), event.getType());
                                if (Math.abs(state.getValue().floatValue() - posX.element) < slop || Math.abs(state2.getValue().floatValue() - posX.element) >= slop) {
                                    shouldUpdateCapturedThumb = false;
                                } else {
                                    shouldUpdateCapturedThumb = true;
                                }
                                if (shouldUpdateCapturedThumb) {
                                    float dir = ((Number) it.getSecond()).floatValue();
                                    draggingStart2.element = z2 ? dir < 0.0f : dir >= 0.0f;
                                    posX.element += Offset.m2731getXimpl(PointerEventKt.positionChange((PointerInputChange) it.getFirst()));
                                }
                            }
                            c00581.$rangeSliderLogic.captureThumb(draggingStart2.element, posX.element, interaction2, c00581.$$this$coroutineScope);
                            try {
                                long id = event.getId();
                                final State<Function2<Boolean, Float, Unit>> state3 = c00581.$onDrag;
                                final boolean z3 = c00581.$isRtl;
                                c00581.L$0 = interaction2;
                                c00581.L$1 = draggingStart2;
                                c00581.L$2 = null;
                                c00581.L$3 = null;
                                c00581.L$4 = null;
                                c00581.label = 3;
                                objM274horizontalDragjO51t88 = DragGestureDetectorKt.m274horizontalDragjO51t88($this$awaitEachGesture, id, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$finishInteraction$success$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                        invoke2(pointerInputChange);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(PointerInputChange it2) {
                                        Intrinsics.checkNotNullParameter(it2, "it");
                                        float deltaX = Offset.m2731getXimpl(PointerEventKt.positionChange(it2));
                                        state3.getValue().invoke(Boolean.valueOf(draggingStart2.element), Float.valueOf(z3 ? -deltaX : deltaX));
                                    }
                                }, c00581);
                                if (objM274horizontalDragjO51t88 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                draggingStart3 = draggingStart2;
                                interaction3 = interaction2;
                                Object obj = $result5;
                                $result5 = objM274horizontalDragjO51t88;
                                $result6 = obj;
                                try {
                                    success = ((Boolean) $result5).booleanValue();
                                    if (success) {
                                        finishInteraction = new DragInteraction.Stop(interaction3);
                                    } else {
                                        finishInteraction = new DragInteraction.Cancel(interaction3);
                                    }
                                    break;
                                } catch (CancellationException e) {
                                    $result5 = $result6;
                                    finishInteraction = new DragInteraction.Cancel(interaction3);
                                }
                                c00581.$gestureEndAction.getValue().invoke(Boxing.boxBoolean(draggingStart3.element));
                                BuildersKt__Builders_commonKt.launch$default(c00581.$$this$coroutineScope, null, null, new AnonymousClass2(c00581.$rangeSliderLogic, draggingStart3, finishInteraction, null), 3, null);
                                return Unit.INSTANCE;
                            } catch (CancellationException e2) {
                                draggingStart3 = draggingStart2;
                                interaction3 = interaction2;
                                finishInteraction = new DragInteraction.Cancel(interaction3);
                                c00581.$gestureEndAction.getValue().invoke(Boxing.boxBoolean(draggingStart3.element));
                                BuildersKt__Builders_commonKt.launch$default(c00581.$$this$coroutineScope, null, null, new AnonymousClass2(c00581.$rangeSliderLogic, draggingStart3, finishInteraction, null), 3, null);
                                return Unit.INSTANCE;
                            }
                        case 1:
                            c00581 = this;
                            $result3 = $result;
                            AwaitPointerEventScope $this$awaitEachGesture3 = (AwaitPointerEventScope) c00581.L$0;
                            ResultKt.throwOnFailure($result3);
                            $this$awaitEachGesture = $this$awaitEachGesture3;
                            $result2 = $result3;
                            event = (PointerInputChange) $result3;
                            interaction = new DragInteraction.Start();
                            posX = new Ref.FloatRef();
                            if (c00581.$isRtl) {
                                fM2731getXimpl = c00581.$maxPx - Offset.m2731getXimpl(event.getPosition());
                            } else {
                                fM2731getXimpl = Offset.m2731getXimpl(event.getPosition());
                            }
                            posX.element = fM2731getXimpl;
                            compare = c00581.$rangeSliderLogic.compareOffsets(posX.element);
                            draggingStart = new Ref.BooleanRef();
                            if (compare != 0) {
                                if (compare < 0) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                            } else if (c00581.$rawOffsetStart.getValue().floatValue() > posX.element) {
                                z = true;
                            } else {
                                z = false;
                            }
                            draggingStart.element = z;
                            c00581.L$0 = $this$awaitEachGesture;
                            c00581.L$1 = event;
                            c00581.L$2 = interaction;
                            c00581.L$3 = posX;
                            c00581.L$4 = draggingStart;
                            c00581.label = 2;
                            objM1189awaitSlop8vUncbI = SliderKt.m1189awaitSlop8vUncbI($this$awaitEachGesture, event.getId(), event.getType(), c00581);
                            if (objM1189awaitSlop8vUncbI == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            interaction2 = interaction;
                            $result4 = objM1189awaitSlop8vUncbI;
                            $result5 = $result2;
                            draggingStart2 = draggingStart;
                            it = (Pair) $result4;
                            if (it != null) {
                                state = c00581.$rawOffsetEnd;
                                State<Float> state4 = c00581.$rawOffsetStart;
                                z2 = c00581.$isRtl;
                                slop = DragGestureDetectorCopyKt.m1087pointerSlopE8SPZFQ($this$awaitEachGesture.getViewConfiguration(), event.getType());
                                if (Math.abs(state.getValue().floatValue() - posX.element) < slop) {
                                    shouldUpdateCapturedThumb = false;
                                } else {
                                    shouldUpdateCapturedThumb = false;
                                }
                                if (shouldUpdateCapturedThumb) {
                                    float dir2 = ((Number) it.getSecond()).floatValue();
                                    draggingStart2.element = z2 ? dir2 < 0.0f : dir2 >= 0.0f;
                                    posX.element += Offset.m2731getXimpl(PointerEventKt.positionChange((PointerInputChange) it.getFirst()));
                                }
                            }
                            c00581.$rangeSliderLogic.captureThumb(draggingStart2.element, posX.element, interaction2, c00581.$$this$coroutineScope);
                            long id2 = event.getId();
                            final State<? extends Function2<? super Boolean, ? super Float, Unit>> state5 = c00581.$onDrag;
                            final boolean z4 = c00581.$isRtl;
                            c00581.L$0 = interaction2;
                            c00581.L$1 = draggingStart2;
                            c00581.L$2 = null;
                            c00581.L$3 = null;
                            c00581.L$4 = null;
                            c00581.label = 3;
                            objM274horizontalDragjO51t88 = DragGestureDetectorKt.m274horizontalDragjO51t88($this$awaitEachGesture, id2, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$finishInteraction$success$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                    invoke2(pointerInputChange);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(PointerInputChange it2) {
                                    Intrinsics.checkNotNullParameter(it2, "it");
                                    float deltaX = Offset.m2731getXimpl(PointerEventKt.positionChange(it2));
                                    state5.getValue().invoke(Boolean.valueOf(draggingStart2.element), Float.valueOf(z4 ? -deltaX : deltaX));
                                }
                            }, c00581);
                            if (objM274horizontalDragjO51t88 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            draggingStart3 = draggingStart2;
                            interaction3 = interaction2;
                            Object obj2 = $result5;
                            $result5 = objM274horizontalDragjO51t88;
                            $result6 = obj2;
                            success = ((Boolean) $result5).booleanValue();
                            if (success) {
                                finishInteraction = new DragInteraction.Stop(interaction3);
                            } else {
                                finishInteraction = new DragInteraction.Cancel(interaction3);
                            }
                            c00581.$gestureEndAction.getValue().invoke(Boxing.boxBoolean(draggingStart3.element));
                            BuildersKt__Builders_commonKt.launch$default(c00581.$$this$coroutineScope, null, null, new AnonymousClass2(c00581.$rangeSliderLogic, draggingStart3, finishInteraction, null), 3, null);
                            return Unit.INSTANCE;
                        case 2:
                            c00581 = this;
                            $result4 = $result;
                            draggingStart2 = (Ref.BooleanRef) c00581.L$4;
                            posX = (Ref.FloatRef) c00581.L$3;
                            interaction2 = (DragInteraction.Start) c00581.L$2;
                            event = (PointerInputChange) c00581.L$1;
                            $this$awaitEachGesture = (AwaitPointerEventScope) c00581.L$0;
                            ResultKt.throwOnFailure($result4);
                            $result5 = $result4;
                            it = (Pair) $result4;
                            if (it != null) {
                                state = c00581.$rawOffsetEnd;
                                State<Float> state6 = c00581.$rawOffsetStart;
                                z2 = c00581.$isRtl;
                                slop = DragGestureDetectorCopyKt.m1087pointerSlopE8SPZFQ($this$awaitEachGesture.getViewConfiguration(), event.getType());
                                if (Math.abs(state.getValue().floatValue() - posX.element) < slop) {
                                    shouldUpdateCapturedThumb = false;
                                } else {
                                    shouldUpdateCapturedThumb = false;
                                }
                                if (shouldUpdateCapturedThumb) {
                                    float dir3 = ((Number) it.getSecond()).floatValue();
                                    draggingStart2.element = z2 ? dir3 < 0.0f : dir3 >= 0.0f;
                                    posX.element += Offset.m2731getXimpl(PointerEventKt.positionChange((PointerInputChange) it.getFirst()));
                                }
                            }
                            c00581.$rangeSliderLogic.captureThumb(draggingStart2.element, posX.element, interaction2, c00581.$$this$coroutineScope);
                            long id3 = event.getId();
                            final State<? extends Function2<? super Boolean, ? super Float, Unit>> state7 = c00581.$onDrag;
                            final boolean z5 = c00581.$isRtl;
                            c00581.L$0 = interaction2;
                            c00581.L$1 = draggingStart2;
                            c00581.L$2 = null;
                            c00581.L$3 = null;
                            c00581.L$4 = null;
                            c00581.label = 3;
                            objM274horizontalDragjO51t88 = DragGestureDetectorKt.m274horizontalDragjO51t88($this$awaitEachGesture, id3, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$finishInteraction$success$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                    invoke2(pointerInputChange);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(PointerInputChange it2) {
                                    Intrinsics.checkNotNullParameter(it2, "it");
                                    float deltaX = Offset.m2731getXimpl(PointerEventKt.positionChange(it2));
                                    state7.getValue().invoke(Boolean.valueOf(draggingStart2.element), Float.valueOf(z5 ? -deltaX : deltaX));
                                }
                            }, c00581);
                            if (objM274horizontalDragjO51t88 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            draggingStart3 = draggingStart2;
                            interaction3 = interaction2;
                            Object obj3 = $result5;
                            $result5 = objM274horizontalDragjO51t88;
                            $result6 = obj3;
                            success = ((Boolean) $result5).booleanValue();
                            if (success) {
                                finishInteraction = new DragInteraction.Stop(interaction3);
                            } else {
                                finishInteraction = new DragInteraction.Cancel(interaction3);
                            }
                            c00581.$gestureEndAction.getValue().invoke(Boxing.boxBoolean(draggingStart3.element));
                            BuildersKt__Builders_commonKt.launch$default(c00581.$$this$coroutineScope, null, null, new AnonymousClass2(c00581.$rangeSliderLogic, draggingStart3, finishInteraction, null), 3, null);
                            return Unit.INSTANCE;
                        case 3:
                            c00581 = this;
                            $result5 = $result;
                            draggingStart3 = (Ref.BooleanRef) c00581.L$1;
                            interaction3 = (DragInteraction.Start) c00581.L$0;
                            try {
                                ResultKt.throwOnFailure($result5);
                                $result6 = $result5;
                                success = ((Boolean) $result5).booleanValue();
                                if (success) {
                                    finishInteraction = new DragInteraction.Stop(interaction3);
                                } else {
                                    finishInteraction = new DragInteraction.Cancel(interaction3);
                                }
                                break;
                            } catch (CancellationException e3) {
                                finishInteraction = new DragInteraction.Cancel(interaction3);
                                c00581.$gestureEndAction.getValue().invoke(Boxing.boxBoolean(draggingStart3.element));
                                BuildersKt__Builders_commonKt.launch$default(c00581.$$this$coroutineScope, null, null, new AnonymousClass2(c00581.$rangeSliderLogic, draggingStart3, finishInteraction, null), 3, null);
                                return Unit.INSTANCE;
                            }
                            c00581.$gestureEndAction.getValue().invoke(Boxing.boxBoolean(draggingStart3.element));
                            BuildersKt__Builders_commonKt.launch$default(c00581.$$this$coroutineScope, null, null, new AnonymousClass2(c00581.$rangeSliderLogic, draggingStart3, finishInteraction, null), 3, null);
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$2, reason: invalid class name */
                /* JADX INFO: compiled from: Slider.kt */
                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$2", f = "Slider.kt", i = {}, l = {1031}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Ref.BooleanRef $draggingStart;
                    final /* synthetic */ DragInteraction $finishInteraction;
                    final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass2(RangeSliderLogic rangeSliderLogic, Ref.BooleanRef booleanRef, DragInteraction dragInteraction, Continuation<? super AnonymousClass2> continuation) {
                        super(2, continuation);
                        this.$rangeSliderLogic = rangeSliderLogic;
                        this.$draggingStart = booleanRef;
                        this.$finishInteraction = dragInteraction;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass2(this.$rangeSliderLogic, this.$draggingStart, this.$finishInteraction, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                this.label = 1;
                                if (this.$rangeSliderLogic.activeInteraction(this.$draggingStart.element).emit(this.$finishInteraction, this) == coroutine_suspended) {
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

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                        this.label = 1;
                        if (ForEachGestureKt.awaitEachGesture(this.$$this$pointerInput, new C00581(this.$isRtl, this.$maxPx, this.$rangeSliderLogic, this.$rawOffsetStart, $this$coroutineScope, this.$gestureEndAction, this.$rawOffsetEnd, this.$onDrag, null), this) == coroutine_suspended) {
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
    public static final Modifier rangeSliderPressDragModifier(Modifier $this$rangeSliderPressDragModifier, MutableInteractionSource startInteractionSource, MutableInteractionSource endInteractionSource, State<Float> state, State<Float> state2, boolean enabled, boolean isRtl, float maxPx, ClosedFloatingPointRange<Float> closedFloatingPointRange, State<? extends Function1<? super Boolean, Unit>> state3, State<? extends Function2<? super Boolean, ? super Float, Unit>> state4) {
        return enabled ? SuspendingPointerInputFilterKt.pointerInput($this$rangeSliderPressDragModifier, new Object[]{startInteractionSource, endInteractionSource, Float.valueOf(maxPx), Boolean.valueOf(isRtl), closedFloatingPointRange}, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) new C03051(startInteractionSource, endInteractionSource, state, state2, state4, isRtl, maxPx, state3, null)) : $this$rangeSliderPressDragModifier;
    }

    static {
        float fM5274constructorimpl = Dp.m5274constructorimpl(48);
        SliderHeight = fM5274constructorimpl;
        float fM5274constructorimpl2 = Dp.m5274constructorimpl(144);
        SliderMinWidth = fM5274constructorimpl2;
        DefaultSliderConstraints = SizeKt.m522heightInVpY3zN4$default(SizeKt.m541widthInVpY3zN4$default(Modifier.INSTANCE, fM5274constructorimpl2, 0.0f, 2, null), 0.0f, fM5274constructorimpl, 1, null);
        SliderToTickAnimation = new TweenSpec<>(100, 0, null, 6, null);
    }

    public static final float getThumbRadius() {
        return ThumbRadius;
    }

    public static final float getTrackHeight() {
        return TrackHeight;
    }
}
