package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.tokens.TimeInputTokens;
import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.profileinstaller.ProfileVerifier;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;
import kotlin.text.CharsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TimePicker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u001a:\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00012\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u00190\u001e¢\u0006\u0002\b\u001fH\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u001d\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0003¢\u0006\u0002\u0010'\u001a%\u0010(\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0002\u0010+\u001a%\u0010,\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010)\u001a\u00020*H\u0003¢\u0006\u0002\u0010.\u001a\u0015\u0010/\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0003¢\u0006\u0002\u00100\u001a\u001d\u00101\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0003¢\u0006\u0002\u0010'\u001a%\u00102\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0003¢\u0006\u0002\u00103\u001a1\u00104\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010%\u001a\u00020&2\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0002\u00105\u001a=\u00106\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020:H\u0003¢\u0006\u0002\u0010<\u001a)\u0010=\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010%\u001a\u00020&H\u0007¢\u0006\u0002\u0010>\u001a%\u0010?\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020&2\u0006\u0010#\u001a\u00020$H\u0003¢\u0006\u0002\u0010@\u001a;\u0010A\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010B\u001a\u00020CH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bD\u0010E\u001ae\u0010F\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020G2\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\u00190I2\u0006\u0010#\u001a\u00020$2\u0006\u0010J\u001a\u00020K2\b\b\u0002\u0010L\u001a\u00020M2\b\b\u0002\u0010N\u001a\u00020O2\u0006\u0010%\u001a\u00020&H\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bP\u0010Q\u001a=\u0010R\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010#\u001a\u00020$2\u0006\u0010J\u001a\u00020K2\u0006\u0010%\u001a\u00020&H\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bS\u0010T\u001aQ\u0010U\u001a\u00020\u00192\u0006\u0010V\u001a\u00020*2\u0006\u0010W\u001a\u00020:2\f\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00190\u001e2\u0006\u0010%\u001a\u00020&2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020Y\u0012\u0004\u0012\u00020\u00190I¢\u0006\u0002\b\u001f¢\u0006\u0002\bZH\u0003¢\u0006\u0002\u0010[\u001a\u001d\u0010\\\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0003¢\u0006\u0002\u0010'\u001a%\u0010]\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0003¢\u0006\u0002\u00103\u001a1\u0010^\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010%\u001a\u00020&2\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0002\u00105\u001a\u0018\u0010_\u001a\u00020\t2\u0006\u0010`\u001a\u00020\t2\u0006\u0010a\u001a\u00020\tH\u0002\u001a(\u0010b\u001a\u00020\t2\u0006\u0010c\u001a\u00020\t2\u0006\u0010d\u001a\u00020\t2\u0006\u0010e\u001a\u00020\u00072\u0006\u0010f\u001a\u00020\u0007H\u0002\u001a-\u0010g\u001a\u00020h2\u0006\u0010J\u001a\u00020K2\u0006\u0010i\u001a\u00020*2\u0006\u0010j\u001a\u00020\u0007H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bk\u0010l\u001a+\u0010m\u001a\u00020$2\b\b\u0002\u0010n\u001a\u00020\u00072\b\b\u0002\u0010o\u001a\u00020\u00072\b\b\u0002\u0010i\u001a\u00020*H\u0007¢\u0006\u0002\u0010p\u001a`\u0010q\u001a\u00020\u00192\u0006\u0010J\u001a\u00020K2\u0006\u0010#\u001a\u00020$2\u0006\u0010-\u001a\u00020G2\u0006\u0010r\u001a\u00020G2\u0006\u0010s\u001a\u00020\u00072!\u0010t\u001a\u001d\u0012\u0013\u0012\u00110G¢\u0006\f\bu\u0012\b\bv\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u00190IH\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bw\u0010x\u001a$\u0010y\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0z2\u0006\u0010{\u001a\u00020\t2\u0006\u0010|\u001a\u00020\tH\u0002\u001a\u001c\u0010}\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010#\u001a\u00020$2\u0006\u0010)\u001a\u00020*H\u0002\u001a\u001c\u0010~\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002\u001a\u0015\u0010\u007f\u001a\u00020h*\u00020\u00072\u0007\u0010\u0080\u0001\u001a\u00020\u0007H\u0002\u001a\u0016\u0010\u0081\u0001\u001a\u00020\u001b*\u00020\u001b2\u0007\u0010\u0081\u0001\u001a\u00020*H\u0003\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0004\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u000b\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\f\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\r\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u000f\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0010\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u0013\u0010\u0016\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0017\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0082\u0001"}, d2 = {"ClockDisplayBottomMargin", "Landroidx/compose/ui/unit/Dp;", "F", "ClockFaceBottomMargin", "DisplaySeparatorWidth", "ExtraHours", "", "", "FullCircle", "", "Hours", "InnerCircleRadius", "MaxDistance", "MinimumInteractiveSize", "Minutes", "OuterCircleSizeRadius", "PeriodToggleMargin", "QuarterCircle", "", "RadiansPerHour", "RadiansPerMinute", "SeparatorZIndex", "SupportLabelTop", "TimeInputBottomPadding", "CircularLayout", "", "modifier", "Landroidx/compose/ui/Modifier;", "radius", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "CircularLayout-uFdPcIQ", "(Landroidx/compose/ui/Modifier;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ClockDisplayNumbers", "state", "Landroidx/compose/material3/TimePickerState;", "colors", "Landroidx/compose/material3/TimePickerColors;", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "ClockFace", "autoSwitchToMinute", "", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;I)V", "ClockText", "value", "(Landroidx/compose/material3/TimePickerState;IZLandroidx/compose/runtime/Composer;I)V", "DisplaySeparator", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "HorizontalClockDisplay", "HorizontalPeriodToggle", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "HorizontalTimePicker", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;II)V", "PeriodToggleImpl", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "startShape", "Landroidx/compose/ui/graphics/Shape;", "endShape", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "TimeInput", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "TimeInputImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/material3/TimePickerState;Landroidx/compose/runtime/Composer;I)V", "TimePicker", "layoutType", "Landroidx/compose/material3/TimePickerLayoutType;", "TimePicker-mT9BvqQ", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ILandroidx/compose/runtime/Composer;II)V", "TimePickerTextField", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "selection", "Landroidx/compose/material3/Selection;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "TimePickerTextField-lxUZ_iY", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/TimePickerState;ILandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "TimeSelector", "TimeSelector-uXwN82Y", "(Landroidx/compose/ui/Modifier;ILandroidx/compose/material3/TimePickerState;ILandroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "ToggleItem", "checked", "shape", "onClick", "Landroidx/compose/foundation/layout/RowScope;", "Lkotlin/ExtensionFunctionType;", "(ZLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/TimePickerColors;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "VerticalClockDisplay", "VerticalPeriodToggle", "VerticalTimePicker", "atan", "y", "x", "dist", "x1", "y1", "x2", "y2", "numberContentDescription", "", "is24Hour", "number", "numberContentDescription-YKJpE6Y", "(IZILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "rememberTimePickerState", "initialHour", "initialMinute", "(IIZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TimePickerState;", "timeInputOnChange", "prevValue", "max", "onNewValue", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "timeInputOnChange-gIWD5Rc", "(ILandroidx/compose/material3/TimePickerState;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/TextFieldValue;ILkotlin/jvm/functions/Function1;)V", "valuesForAnimation", "Lkotlin/Pair;", "current", "new", "clockDial", "drawSelector", "toLocalString", "minDigits", "visible", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TimePickerKt {
    private static final List<Integer> ExtraHours;
    private static final float FullCircle = 6.2831855f;
    private static final List<Integer> Hours;
    private static final float PeriodToggleMargin;
    private static final double QuarterCircle = 1.5707963267948966d;
    private static final float RadiansPerHour = 0.5235988f;
    private static final float RadiansPerMinute = 0.10471976f;
    private static final float SeparatorZIndex = 2.0f;
    private static final float OuterCircleSizeRadius = Dp.m5274constructorimpl(101);
    private static final float InnerCircleRadius = Dp.m5274constructorimpl(69);
    private static final float ClockDisplayBottomMargin = Dp.m5274constructorimpl(36);
    private static final float ClockFaceBottomMargin = Dp.m5274constructorimpl(24);
    private static final float DisplaySeparatorWidth = Dp.m5274constructorimpl(24);
    private static final float SupportLabelTop = Dp.m5274constructorimpl(7);
    private static final float TimeInputBottomPadding = Dp.m5274constructorimpl(24);
    private static final float MaxDistance = Dp.m5274constructorimpl(74);
    private static final float MinimumInteractiveSize = Dp.m5274constructorimpl(48);
    private static final List<Integer> Minutes = CollectionsKt.listOf((Object[]) new Integer[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55});

    /* JADX INFO: renamed from: TimePicker-mT9BvqQ, reason: not valid java name */
    public static final void m1899TimePickermT9BvqQ(final TimePickerState state, Modifier modifier, TimePickerColors colors, int layoutType, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors timePickerColors;
        int i2;
        TimePickerColors colors2;
        int $dirty;
        Modifier modifier3;
        TimePickerColors colors3;
        int layoutType2;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(state, "state");
        Composer $composer3 = $composer.startRestartGroup(-619286452);
        ComposerKt.sourceInformation($composer3, "C(TimePicker)P(3,2!,1:c#material3.TimePickerLayoutType)191@10184L8,192@10252L12,194@10313L23:TimePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changed(state) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                timePickerColors = colors;
                int i4 = $composer3.changed(timePickerColors) ? 256 : 128;
                $dirty2 |= i4;
            } else {
                timePickerColors = colors;
            }
            $dirty2 |= i4;
        } else {
            timePickerColors = colors;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                i2 = layoutType;
                int i5 = $composer3.changed(i2) ? 2048 : 1024;
                $dirty2 |= i5;
            } else {
                i2 = layoutType;
            }
            $dirty2 |= i5;
        } else {
            i2 = layoutType;
        }
        if (($dirty2 & 5851) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            colors3 = timePickerColors;
            layoutType2 = i2;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    colors2 = TimePickerDefaults.INSTANCE.m1896colorsu3YEpmA(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 24576, 16383);
                    $dirty2 &= -897;
                } else {
                    colors2 = timePickerColors;
                }
                if ((i & 8) != 0) {
                    $dirty = $dirty2 & (-7169);
                    modifier3 = modifier4;
                    colors3 = colors2;
                    layoutType2 = TimePickerDefaults.INSTANCE.m1897layoutTypesDNSZnc($composer3, 6);
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    colors3 = colors2;
                    layoutType2 = i2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                $dirty = $dirty2;
                modifier3 = modifier2;
                colors3 = timePickerColors;
                layoutType2 = i2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-619286452, $dirty, -1, "androidx.compose.material3.TimePicker (TimePicker.kt:188)");
            }
            State<Boolean> state2 = TouchExplorationStateProvider_androidKt.touchExplorationState($composer3, 0);
            if (TimePickerLayoutType.m1917equalsimpl0(layoutType2, TimePickerLayoutType.INSTANCE.m1922getVerticalQJTpgSE())) {
                $composer3.startReplaceableGroup(1318081657);
                ComposerKt.sourceInformation($composer3, "197@10401L184");
                $composer2 = $composer3;
                VerticalTimePicker(state, modifier3, colors3, !TimePicker_mT9BvqQ$lambda$0(state2), $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896), 0);
                $composer2.endReplaceableGroup();
            } else {
                $composer2 = $composer3;
                $composer2.startReplaceableGroup(1318081863);
                ComposerKt.sourceInformation($composer2, "204@10607L186");
                HorizontalTimePicker(state, modifier3, colors3, !TimePicker_mT9BvqQ$lambda$0(state2), $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896), 0);
                $composer2.endReplaceableGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final TimePickerColors timePickerColors2 = colors3;
        final int i6 = layoutType2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePicker$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i7) {
                TimePickerKt.m1899TimePickermT9BvqQ(state, modifier5, timePickerColors2, i6, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    private static final boolean TimePicker_mT9BvqQ$lambda$0(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    public static final void TimeInput(final TimePickerState state, Modifier modifier, TimePickerColors colors, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors colors2;
        Modifier.Companion modifier3;
        Modifier modifier4;
        TimePickerColors colors3;
        Intrinsics.checkNotNullParameter(state, "state");
        Composer $composer2 = $composer.startRestartGroup(-760850373);
        ComposerKt.sourceInformation($composer2, "C(TimeInput)P(2,1)233@11683L8,235@11701L38:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                colors2 = colors;
                int i3 = $composer2.changed(colors2) ? 256 : 128;
                $dirty |= i3;
            } else {
                colors2 = colors;
            }
            $dirty |= i3;
        } else {
            colors2 = colors;
        }
        if (($dirty & 731) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            colors3 = colors2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    colors2 = TimePickerDefaults.INSTANCE.m1896colorsu3YEpmA(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 24576, 16383);
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-760850373, $dirty, -1, "androidx.compose.material3.TimeInput (TimePicker.kt:230)");
            }
            TimeInputImpl(modifier3, colors2, state, $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty << 6) & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            colors3 = colors2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier4;
        final TimePickerColors timePickerColors = colors3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.TimeInput.1
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
                TimePickerKt.TimeInput(state, modifier5, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:27:0x00b5  */
    public static final TimePickerState rememberTimePickerState(int initialHour, int initialMinute, boolean is24Hour, Composer $composer, int $changed, int i) {
        $composer.startReplaceableGroup(1237715277);
        ComposerKt.sourceInformation($composer, "C(rememberTimePickerState)441@21331L14,444@21424L133,442@21368L189:TimePicker.kt#uh7d8r");
        final int initialHour2 = (i & 1) != 0 ? 0 : initialHour;
        final int initialMinute2 = (i & 2) != 0 ? 0 : initialMinute;
        final boolean is24Hour2 = (i & 4) != 0 ? TimeFormat_androidKt.is24HourFormat($composer, 0) : is24Hour;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1237715277, $changed, -1, "androidx.compose.material3.rememberTimePickerState (TimePicker.kt:438)");
        }
        Object[] objArr = new Object[0];
        Saver<TimePickerState, ?> Saver = TimePickerState.INSTANCE.Saver();
        Object key1$iv = Integer.valueOf(initialHour2);
        Object key2$iv = Integer.valueOf(initialMinute2);
        Object key3$iv = Boolean.valueOf(is24Hour2);
        int i2 = ($changed & 14) | ($changed & 112) | ($changed & 896);
        $composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(key1$iv) | $composer.changed(key2$iv) | $composer.changed(key3$iv);
        Object value$iv$iv = $composer.rememberedValue();
        if (!invalid$iv$iv) {
            Object key1$iv2 = Composer.INSTANCE.getEmpty();
            if (value$iv$iv == key1$iv2) {
            }
            $composer.endReplaceableGroup();
            TimePickerState timePickerState = (TimePickerState) RememberSaveableKt.m2617rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv$iv, $composer, 72, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceableGroup();
            return timePickerState;
        }
        value$iv$iv = (Function0) new Function0<TimePickerState>() { // from class: androidx.compose.material3.TimePickerKt$rememberTimePickerState$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TimePickerState invoke() {
                return new TimePickerState(initialHour2, initialMinute2, is24Hour2);
            }
        };
        $composer.updateRememberedValue(value$iv$iv);
        $composer.endReplaceableGroup();
        TimePickerState timePickerState2 = (TimePickerState) RememberSaveableKt.m2617rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv$iv, $composer, 72, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return timePickerState2;
    }

    public static final void VerticalTimePicker(final TimePickerState state, Modifier modifier, TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors colors2;
        Modifier.Companion modifier3;
        Modifier modifier4;
        TimePickerColors colors3;
        Intrinsics.checkNotNullParameter(state, "state");
        Composer $composer2 = $composer.startRestartGroup(310683247);
        ComposerKt.sourceInformation($composer2, "C(VerticalTimePicker)P(3,2,1)664@28871L8,667@28921L319:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                colors2 = colors;
                int i3 = $composer2.changed(colors2) ? 256 : 128;
                $dirty |= i3;
            } else {
                colors2 = colors;
            }
            $dirty |= i3;
        } else {
            colors2 = colors;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 2048 : 1024;
        }
        if (($dirty & 5851) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            colors3 = colors2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    colors2 = TimePickerDefaults.INSTANCE.m1896colorsu3YEpmA(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 24576, 16383);
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(310683247, $dirty, -1, "androidx.compose.material3.VerticalTimePicker (TimePicker.kt:661)");
            }
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getCenterHorizontally();
            int $changed$iv = (($dirty >> 3) & 14) | 384;
            $composer2.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation($composer2, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
            int $changed$iv$iv = ($changed$iv << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier3);
            Modifier modifier5 = modifier3;
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
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i4 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 276693704, "C79@4027L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = (($changed$iv >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 37436160, "C668@29011L35,669@29055L60,670@29124L44,671@29177L57:TimePicker.kt#uh7d8r");
            VerticalClockDisplay(state, colors2, $composer2, ($dirty & 14) | (($dirty >> 3) & 112));
            SpacerKt.Spacer(SizeKt.m520height3ABfNKs(Modifier.INSTANCE, ClockDisplayBottomMargin), $composer2, 6);
            ClockFace(state, colors2, autoSwitchToMinute, $composer2, ($dirty & 14) | (($dirty >> 3) & 112) | (($dirty >> 3) & 896));
            SpacerKt.Spacer(SizeKt.m520height3ABfNKs(Modifier.INSTANCE, ClockFaceBottomMargin), $composer2, 6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier5;
            colors3 = colors2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final TimePickerColors timePickerColors = colors3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.VerticalTimePicker.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i6) {
                TimePickerKt.VerticalTimePicker(state, modifier6, timePickerColors, autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static final void HorizontalTimePicker(final TimePickerState state, Modifier modifier, TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors colors2;
        Modifier.Companion modifier3;
        Modifier modifier4;
        TimePickerColors colors3;
        Intrinsics.checkNotNullParameter(state, "state");
        Composer $composer2 = $composer.startRestartGroup(5079681);
        ComposerKt.sourceInformation($composer2, "C(HorizontalTimePicker)P(3,2,1)679@29404L8,682@29454L309:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                colors2 = colors;
                int i3 = $composer2.changed(colors2) ? 256 : 128;
                $dirty |= i3;
            } else {
                colors2 = colors;
            }
            $dirty |= i3;
        } else {
            colors2 = colors;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 2048 : 1024;
        }
        if (($dirty & 5851) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            colors3 = colors2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    colors2 = TimePickerDefaults.INSTANCE.m1896colorsu3YEpmA(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 24576, 16383);
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(5079681, $dirty, -1, "androidx.compose.material3.HorizontalTimePicker (TimePicker.kt:676)");
            }
            Modifier modifier$iv = PaddingKt.m491paddingqDBjuR0$default(modifier3, 0.0f, 0.0f, 0.0f, ClockFaceBottomMargin, 7, null);
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            $composer2.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation($composer2, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            Modifier modifier5 = modifier3;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i4 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -326682283, "C80@4021L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i5 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -321635938, "C686@29599L37,687@29645L59,688@29713L44:TimePicker.kt#uh7d8r");
            HorizontalClockDisplay(state, colors2, $composer2, ($dirty & 14) | (($dirty >> 3) & 112));
            SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, ClockDisplayBottomMargin), $composer2, 6);
            ClockFace(state, colors2, autoSwitchToMinute, $composer2, ($dirty & 14) | (($dirty >> 3) & 112) | (($dirty >> 3) & 896));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier5;
            colors3 = colors2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final TimePickerColors timePickerColors = colors3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.HorizontalTimePicker.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i6) {
                TimePickerKt.HorizontalTimePicker(state, modifier6, timePickerColors, autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TimeInputImpl(final Modifier modifier, final TimePickerColors colors, final TimePickerState state, Composer $composer, final int $changed) {
        Object value$iv$iv;
        Object value$iv$iv2;
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Composer $composer3;
        Composer $composer4 = $composer.startRestartGroup(-475657989);
        ComposerKt.sourceInformation($composer4, "C(TimeInputImpl)P(1)698@29965L92,698@29913L144,701@30133L84,701@30081L136,705@30223L3592:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer4.changed(modifier) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer4.changed(colors) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer4.changed(state) ? 256 : 128;
        }
        final int $dirty2 = $dirty;
        if (($dirty2 & 731) == 146 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            $composer2 = $composer4;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-475657989, $dirty2, -1, "androidx.compose.material3.TimeInputImpl (TimePicker.kt:693)");
            }
            Object[] objArr = new Object[0];
            Saver<TextFieldValue, Object> saver = TextFieldValue.INSTANCE.getSaver();
            int i = ($dirty2 >> 6) & 14;
            $composer4.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer4.changed(state);
            Object it$iv$iv = $composer4.rememberedValue();
            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new Function0<MutableState<TextFieldValue>>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$hourValue$2$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final MutableState<TextFieldValue> invoke() {
                        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue(TimePickerKt.toLocalString(state.getHourForDisplay$material3_release(), 2), 0L, (TextRange) null, 6, (DefaultConstructorMarker) null), null, 2, null);
                    }
                };
                $composer4.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer4.endReplaceableGroup();
            final MutableState hourValue$delegate = RememberSaveableKt.rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv$iv, $composer4, 72, 4);
            Object[] objArr2 = new Object[0];
            Saver<TextFieldValue, Object> saver2 = TextFieldValue.INSTANCE.getSaver();
            int i2 = ($dirty2 >> 6) & 14;
            $composer4.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv2 = $composer4.changed(state);
            Object it$iv$iv2 = $composer4.rememberedValue();
            if (invalid$iv$iv2 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new Function0<MutableState<TextFieldValue>>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$minuteValue$2$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final MutableState<TextFieldValue> invoke() {
                        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue(TimePickerKt.toLocalString(state.getMinute(), 2), 0L, (TextRange) null, 6, (DefaultConstructorMarker) null), null, 2, null);
                    }
                };
                $composer4.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer4.endReplaceableGroup();
            final MutableState minuteValue$delegate = RememberSaveableKt.rememberSaveable(objArr2, (Saver) saver2, (String) null, (Function0) value$iv$iv2, $composer4, 72, 4);
            $composer2 = $composer4;
            Modifier modifier$iv = PaddingKt.m491paddingqDBjuR0$default(modifier, 0.0f, 0.0f, 0.0f, TimeInputBottomPadding, 7, null);
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            $composer2.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation($composer2, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i3 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -326682283, "C80@4021L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i4 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1230444741, "C709@30386L10,715@30597L2787:TimePicker.kt#uh7d8r");
            TextStyle textStyleFromToken = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer2, 6), TimeInputTokens.INSTANCE.getTimeFieldLabelTextFont());
            TextStyle textStyle = textStyleFromToken.m4782copyCXVQc50((3653631 & 1) != 0 ? textStyleFromToken.spanStyle.m4727getColor0d7_KjU() : colors.m1895timeSelectorContentColorvNxB06k$material3_release(true), (3653631 & 2) != 0 ? textStyleFromToken.spanStyle.getFontSize() : 0L, (3653631 & 4) != 0 ? textStyleFromToken.spanStyle.getFontWeight() : null, (3653631 & 8) != 0 ? textStyleFromToken.spanStyle.getFontStyle() : null, (3653631 & 16) != 0 ? textStyleFromToken.spanStyle.getFontSynthesis() : null, (3653631 & 32) != 0 ? textStyleFromToken.spanStyle.getFontFamily() : null, (3653631 & 64) != 0 ? textStyleFromToken.spanStyle.getFontFeatureSettings() : null, (3653631 & 128) != 0 ? textStyleFromToken.spanStyle.getLetterSpacing() : 0L, (3653631 & 256) != 0 ? textStyleFromToken.spanStyle.getBaselineShift() : null, (3653631 & 512) != 0 ? textStyleFromToken.spanStyle.getTextGeometricTransform() : null, (3653631 & 1024) != 0 ? textStyleFromToken.spanStyle.getLocaleList() : null, (3653631 & 2048) != 0 ? textStyleFromToken.spanStyle.getBackground() : 0L, (3653631 & 4096) != 0 ? textStyleFromToken.spanStyle.getTextDecoration() : null, (3653631 & 8192) != 0 ? textStyleFromToken.spanStyle.getShadow() : null, (3653631 & 16384) != 0 ? textStyleFromToken.paragraphStyle.getTextAlign() : TextAlign.m5127boximpl(TextAlign.INSTANCE.m5134getCentere0LSkKk()), (3653631 & 32768) != 0 ? textStyleFromToken.paragraphStyle.getTextDirection() : null, (3653631 & 65536) != 0 ? textStyleFromToken.paragraphStyle.getLineHeight() : 0L, (3653631 & 131072) != 0 ? textStyleFromToken.paragraphStyle.getTextIndent() : null, (3653631 & 262144) != 0 ? textStyleFromToken.platformStyle : null, (3653631 & 524288) != 0 ? textStyleFromToken.paragraphStyle.getLineHeightStyle() : null, (3653631 & 1048576) != 0 ? textStyleFromToken.paragraphStyle.getLineBreak() : null, (3653631 & 2097152) != 0 ? textStyleFromToken.paragraphStyle.getHyphens() : null);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{TextKt.getLocalTextStyle().provides(textStyle)}, ComposableLambdaKt.composableLambda($composer2, 1306700887, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code duplicated, block: B:28:0x012a  */
                /* JADX WARN: Code duplicated, block: B:31:0x0135  */
                /* JADX WARN: Code duplicated, block: B:32:0x0139  */
                /* JADX WARN: Code duplicated, block: B:36:0x01c6  */
                /* JADX WARN: Code duplicated, block: B:39:0x01d1  */
                /* JADX WARN: Code duplicated, block: B:40:0x01d3  */
                /* JADX WARN: Code duplicated, block: B:44:0x0221  */
                /* JADX WARN: Code duplicated, block: B:47:0x022c  */
                /* JADX WARN: Code duplicated, block: B:48:0x022e  */
                /* JADX WARN: Code duplicated, block: B:52:0x0294  */
                /* JADX WARN: Code duplicated, block: B:56:0x02a2 A[ADDED_TO_REGION] */
                /* JADX WARN: Code duplicated, block: B:60:0x02e9  */
                /* JADX WARN: Code duplicated, block: B:63:? A[RETURN, SYNTHETIC] */
                public final void invoke(Composer $composer5, int $changed2) {
                    Object key1$iv;
                    Object value$iv$iv3;
                    boolean invalid$iv$iv3;
                    Object it$iv$iv3;
                    Object value$iv$iv4;
                    boolean invalid$iv$iv4;
                    Object it$iv$iv4;
                    Object value$iv$iv5;
                    boolean invalid$iv$iv5;
                    Object it$iv$iv5;
                    Object value$iv$iv6;
                    boolean invalid$iv$iv6;
                    Object value$iv$iv7;
                    Object key1$iv2;
                    Object key1$iv3;
                    Object key1$iv4;
                    ComposerKt.sourceInformation($composer5, "C719@30761L411,731@31241L353,746@31903L38,717@30672L1318,749@32003L85,752@32197L376,765@32645L331,780@33287L38,750@32101L1273:TimePicker.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer5.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1306700887, $changed2, -1, "androidx.compose.material3.TimeInputImpl.<anonymous>.<anonymous> (TimePicker.kt:715)");
                        }
                        Modifier.Companion companion = Modifier.INSTANCE;
                        Object key1$iv5 = hourValue$delegate;
                        Object key2$iv = state;
                        final TimePickerState timePickerState = state;
                        final MutableState<TextFieldValue> mutableState = hourValue$delegate;
                        int i5 = ($dirty2 >> 3) & 112;
                        $composer5.startReplaceableGroup(511388516);
                        ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                        boolean invalid$iv$iv7 = $composer5.changed(key1$iv5) | $composer5.changed(key2$iv);
                        Object it$iv$iv6 = $composer5.rememberedValue();
                        if (invalid$iv$iv7 || it$iv$iv6 == Composer.INSTANCE.getEmpty()) {
                            key1$iv = (Function1) new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                                    return m1908invokeZmokQxo(keyEvent.m3957unboximpl());
                                }

                                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                                public final Boolean m1908invokeZmokQxo(android.view.KeyEvent event) {
                                    Intrinsics.checkNotNullParameter(event, "event");
                                    int iM3970getUtf16CodePointZmokQxo = KeyEvent_androidKt.m3970getUtf16CodePointZmokQxo(event);
                                    boolean switchFocus = (48 <= iM3970getUtf16CodePointZmokQxo && iM3970getUtf16CodePointZmokQxo < 58) && TextRange.m4768getStartimpl(TimePickerKt.TimeInputImpl$lambda$5(mutableState).getSelection()) == 2 && TimePickerKt.TimeInputImpl$lambda$5(mutableState).getText().length() == 2;
                                    if (switchFocus) {
                                        timePickerState.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                    }
                                    return false;
                                }
                            };
                            $composer5.updateRememberedValue(key1$iv);
                        } else {
                            key1$iv = it$iv$iv6;
                        }
                        $composer5.endReplaceableGroup();
                        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(companion, (Function1) key1$iv);
                        TextFieldValue textFieldValueTimeInputImpl$lambda$5 = TimePickerKt.TimeInputImpl$lambda$5(hourValue$delegate);
                        Object key1$iv6 = state;
                        Object key2$iv2 = hourValue$delegate;
                        final TimePickerState timePickerState2 = state;
                        final MutableState<TextFieldValue> mutableState2 = hourValue$delegate;
                        int i6 = ($dirty2 >> 6) & 14;
                        $composer5.startReplaceableGroup(511388516);
                        ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                        boolean invalid$iv$iv8 = $composer5.changed(key1$iv6) | $composer5.changed(key2$iv2);
                        Object it$iv$iv7 = $composer5.rememberedValue();
                        if (!invalid$iv$iv8) {
                            Object key1$iv7 = Composer.INSTANCE.getEmpty();
                            if (it$iv$iv7 != key1$iv7) {
                                value$iv$iv3 = it$iv$iv7;
                            }
                            $composer5.endReplaceableGroup();
                            Function1 function1 = (Function1) value$iv$iv3;
                            TimePickerState timePickerState3 = state;
                            int iM1711getHourJiIwxys = Selection.INSTANCE.m1711getHourJiIwxys();
                            KeyboardOptions keyboardOptions = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4933getNexteUduSuo(), 3, null);
                            Object key1$iv8 = state;
                            final TimePickerState timePickerState4 = state;
                            int i7 = ($dirty2 >> 6) & 14;
                            $composer5.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                            invalid$iv$iv3 = $composer5.changed(key1$iv8);
                            it$iv$iv3 = $composer5.rememberedValue();
                            if (!invalid$iv$iv3) {
                                key1$iv4 = Composer.INSTANCE.getEmpty();
                                if (it$iv$iv3 == key1$iv4) {
                                    value$iv$iv4 = it$iv$iv3;
                                }
                                $composer5.endReplaceableGroup();
                                KeyboardActions keyboardActions = new KeyboardActions(null, null, (Function1) value$iv$iv4, null, null, null, 59, null);
                                TimePickerColors timePickerColors = colors;
                                int i8 = $dirty2;
                                TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnKeyEvent, textFieldValueTimeInputImpl$lambda$5, function1, timePickerState3, iM1711getHourJiIwxys, keyboardOptions, keyboardActions, timePickerColors, $composer5, ((i8 << 3) & 7168) | 24576 | ((i8 << 18) & 29360128), 0);
                                TimePickerKt.DisplaySeparator(SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimePickerKt.DisplaySeparatorWidth, TimeInputTokens.INSTANCE.m2486getPeriodSelectorContainerHeightD9Ej5fM()), $composer5, 6);
                                Modifier.Companion companion2 = Modifier.INSTANCE;
                                Object key1$iv9 = minuteValue$delegate;
                                Object key2$iv3 = state;
                                final TimePickerState timePickerState5 = state;
                                final MutableState<TextFieldValue> mutableState3 = minuteValue$delegate;
                                int i9 = ($dirty2 >> 3) & 112;
                                $composer5.startReplaceableGroup(511388516);
                                ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                                invalid$iv$iv4 = $composer5.changed(key1$iv9) | $composer5.changed(key2$iv3);
                                it$iv$iv4 = $composer5.rememberedValue();
                                if (!invalid$iv$iv4) {
                                    key1$iv3 = Composer.INSTANCE.getEmpty();
                                    if (it$iv$iv4 == key1$iv3) {
                                        value$iv$iv5 = it$iv$iv4;
                                    }
                                    $composer5.endReplaceableGroup();
                                    Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(companion2, (Function1) value$iv$iv5);
                                    TextFieldValue textFieldValueTimeInputImpl$lambda$8 = TimePickerKt.TimeInputImpl$lambda$8(minuteValue$delegate);
                                    Object key1$iv10 = state;
                                    Object key2$iv4 = minuteValue$delegate;
                                    final TimePickerState timePickerState6 = state;
                                    final MutableState<TextFieldValue> mutableState4 = minuteValue$delegate;
                                    int i10 = ($dirty2 >> 6) & 14;
                                    $composer5.startReplaceableGroup(511388516);
                                    ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                                    invalid$iv$iv5 = $composer5.changed(key1$iv10) | $composer5.changed(key2$iv4);
                                    it$iv$iv5 = $composer5.rememberedValue();
                                    if (!invalid$iv$iv5) {
                                        key1$iv2 = Composer.INSTANCE.getEmpty();
                                        if (it$iv$iv5 == key1$iv2) {
                                            value$iv$iv6 = it$iv$iv5;
                                        }
                                        $composer5.endReplaceableGroup();
                                        Function1 function2 = (Function1) value$iv$iv6;
                                        TimePickerState timePickerState7 = state;
                                        int iM1712getMinuteJiIwxys = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                        KeyboardOptions keyboardOptions2 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                                        Object key1$iv11 = state;
                                        final TimePickerState timePickerState8 = state;
                                        int i11 = ($dirty2 >> 6) & 14;
                                        $composer5.startReplaceableGroup(1157296644);
                                        ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                        invalid$iv$iv6 = $composer5.changed(key1$iv11);
                                        value$iv$iv7 = $composer5.rememberedValue();
                                        if (!invalid$iv$iv6 || value$iv$iv7 == Composer.INSTANCE.getEmpty()) {
                                            value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                                    invoke2(keyboardActionScope);
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(KeyboardActionScope $receiver) {
                                                    Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                                    timePickerState8.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                                }
                                            };
                                            $composer5.updateRememberedValue(value$iv$iv7);
                                        }
                                        $composer5.endReplaceableGroup();
                                        KeyboardActions keyboardActions2 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                                        TimePickerColors timePickerColors2 = colors;
                                        int i12 = $dirty2;
                                        TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent, textFieldValueTimeInputImpl$lambda$8, function2, timePickerState7, iM1712getMinuteJiIwxys, keyboardOptions2, keyboardActions2, timePickerColors2, $composer5, ((i12 << 3) & 7168) | 24576 | ((i12 << 18) & 29360128), 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    value$iv$iv6 = (Function1) new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                            invoke2(textFieldValue);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(TextFieldValue newValue) {
                                            Intrinsics.checkNotNullParameter(newValue, "newValue");
                                            int iM1712getMinuteJiIwxys2 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                            TimePickerState timePickerState9 = timePickerState6;
                                            TextFieldValue textFieldValueTimeInputImpl$lambda$9 = TimePickerKt.TimeInputImpl$lambda$8(mutableState4);
                                            final MutableState<TextFieldValue> mutableState5 = mutableState4;
                                            TimePickerKt.m1907timeInputOnChangegIWD5Rc(iM1712getMinuteJiIwxys2, timePickerState9, newValue, textFieldValueTimeInputImpl$lambda$9, 59, new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1.1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                                    invoke2(textFieldValue);
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(TextFieldValue it) {
                                                    Intrinsics.checkNotNullParameter(it, "it");
                                                    mutableState5.setValue(it);
                                                }
                                            });
                                        }
                                    };
                                    $composer5.updateRememberedValue(value$iv$iv6);
                                    $composer5.endReplaceableGroup();
                                    Function1 function3 = (Function1) value$iv$iv6;
                                    TimePickerState timePickerState9 = state;
                                    int iM1712getMinuteJiIwxys2 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                    KeyboardOptions keyboardOptions3 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                                    Object key1$iv12 = state;
                                    final TimePickerState timePickerState10 = state;
                                    int i13 = ($dirty2 >> 6) & 14;
                                    $composer5.startReplaceableGroup(1157296644);
                                    ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                    invalid$iv$iv6 = $composer5.changed(key1$iv12);
                                    value$iv$iv7 = $composer5.rememberedValue();
                                    if (!invalid$iv$iv6) {
                                    }
                                    value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                            invoke2(keyboardActionScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(KeyboardActionScope $receiver) {
                                            Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                            timePickerState10.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                        }
                                    };
                                    $composer5.updateRememberedValue(value$iv$iv7);
                                    $composer5.endReplaceableGroup();
                                    KeyboardActions keyboardActions3 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                                    TimePickerColors timePickerColors3 = colors;
                                    int i14 = $dirty2;
                                    TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent, textFieldValueTimeInputImpl$lambda$8, function3, timePickerState9, iM1712getMinuteJiIwxys2, keyboardOptions3, keyboardActions3, timePickerColors3, $composer5, ((i14 << 3) & 7168) | 24576 | ((i14 << 18) & 29360128), 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                value$iv$iv5 = (Function1) new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                                        return m1909invokeZmokQxo(keyEvent.m3957unboximpl());
                                    }

                                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                                    public final Boolean m1909invokeZmokQxo(android.view.KeyEvent event) {
                                        Intrinsics.checkNotNullParameter(event, "event");
                                        boolean switchFocus = KeyEvent_androidKt.m3970getUtf16CodePointZmokQxo(event) == 0 && TextRange.m4768getStartimpl(TimePickerKt.TimeInputImpl$lambda$8(mutableState3).getSelection()) == 0;
                                        if (switchFocus) {
                                            timePickerState5.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1711getHourJiIwxys());
                                        }
                                        return Boolean.valueOf(switchFocus);
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv$iv5);
                                $composer5.endReplaceableGroup();
                                Modifier modifierOnPreviewKeyEvent2 = KeyInputModifierKt.onPreviewKeyEvent(companion2, (Function1) value$iv$iv5);
                                TextFieldValue textFieldValueTimeInputImpl$lambda$9 = TimePickerKt.TimeInputImpl$lambda$8(minuteValue$delegate);
                                Object key1$iv13 = state;
                                Object key2$iv5 = minuteValue$delegate;
                                final TimePickerState timePickerState11 = state;
                                final MutableState<TextFieldValue> mutableState5 = minuteValue$delegate;
                                int i15 = ($dirty2 >> 6) & 14;
                                $composer5.startReplaceableGroup(511388516);
                                ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                                invalid$iv$iv5 = $composer5.changed(key1$iv13) | $composer5.changed(key2$iv5);
                                it$iv$iv5 = $composer5.rememberedValue();
                                if (!invalid$iv$iv5) {
                                    key1$iv2 = Composer.INSTANCE.getEmpty();
                                    if (it$iv$iv5 == key1$iv2) {
                                        value$iv$iv6 = it$iv$iv5;
                                    }
                                    $composer5.endReplaceableGroup();
                                    Function1 function4 = (Function1) value$iv$iv6;
                                    TimePickerState timePickerState12 = state;
                                    int iM1712getMinuteJiIwxys3 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                    KeyboardOptions keyboardOptions4 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                                    Object key1$iv14 = state;
                                    final TimePickerState timePickerState13 = state;
                                    int i16 = ($dirty2 >> 6) & 14;
                                    $composer5.startReplaceableGroup(1157296644);
                                    ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                    invalid$iv$iv6 = $composer5.changed(key1$iv14);
                                    value$iv$iv7 = $composer5.rememberedValue();
                                    if (!invalid$iv$iv6) {
                                    }
                                    value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                            invoke2(keyboardActionScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(KeyboardActionScope $receiver) {
                                            Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                            timePickerState13.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                        }
                                    };
                                    $composer5.updateRememberedValue(value$iv$iv7);
                                    $composer5.endReplaceableGroup();
                                    KeyboardActions keyboardActions4 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                                    TimePickerColors timePickerColors4 = colors;
                                    int i17 = $dirty2;
                                    TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent2, textFieldValueTimeInputImpl$lambda$9, function4, timePickerState12, iM1712getMinuteJiIwxys3, keyboardOptions4, keyboardActions4, timePickerColors4, $composer5, ((i17 << 3) & 7168) | 24576 | ((i17 << 18) & 29360128), 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                value$iv$iv6 = (Function1) new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                        invoke2(textFieldValue);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(TextFieldValue newValue) {
                                        Intrinsics.checkNotNullParameter(newValue, "newValue");
                                        int iM1712getMinuteJiIwxys4 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                        TimePickerState timePickerState14 = timePickerState11;
                                        TextFieldValue textFieldValueTimeInputImpl$lambda$10 = TimePickerKt.TimeInputImpl$lambda$8(mutableState5);
                                        final MutableState<TextFieldValue> mutableState6 = mutableState5;
                                        TimePickerKt.m1907timeInputOnChangegIWD5Rc(iM1712getMinuteJiIwxys4, timePickerState14, newValue, textFieldValueTimeInputImpl$lambda$10, 59, new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                                invoke2(textFieldValue);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(TextFieldValue it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                mutableState6.setValue(it);
                                            }
                                        });
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv$iv6);
                                $composer5.endReplaceableGroup();
                                Function1 function5 = (Function1) value$iv$iv6;
                                TimePickerState timePickerState14 = state;
                                int iM1712getMinuteJiIwxys4 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                KeyboardOptions keyboardOptions5 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                                Object key1$iv15 = state;
                                final TimePickerState timePickerState15 = state;
                                int i18 = ($dirty2 >> 6) & 14;
                                $composer5.startReplaceableGroup(1157296644);
                                ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                invalid$iv$iv6 = $composer5.changed(key1$iv15);
                                value$iv$iv7 = $composer5.rememberedValue();
                                if (!invalid$iv$iv6) {
                                }
                                value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                        invoke2(keyboardActionScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(KeyboardActionScope $receiver) {
                                        Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                        timePickerState15.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv$iv7);
                                $composer5.endReplaceableGroup();
                                KeyboardActions keyboardActions5 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                                TimePickerColors timePickerColors5 = colors;
                                int i19 = $dirty2;
                                TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent2, textFieldValueTimeInputImpl$lambda$9, function5, timePickerState14, iM1712getMinuteJiIwxys4, keyboardOptions5, keyboardActions5, timePickerColors5, $composer5, ((i19 << 3) & 7168) | 24576 | ((i19 << 18) & 29360128), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            value$iv$iv4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$3$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                    invoke2(keyboardActionScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyboardActionScope $receiver) {
                                    Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                    timePickerState4.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                }
                            };
                            $composer5.updateRememberedValue(value$iv$iv4);
                            $composer5.endReplaceableGroup();
                            KeyboardActions keyboardActions6 = new KeyboardActions(null, null, (Function1) value$iv$iv4, null, null, null, 59, null);
                            TimePickerColors timePickerColors6 = colors;
                            int i20 = $dirty2;
                            TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnKeyEvent, textFieldValueTimeInputImpl$lambda$5, function1, timePickerState3, iM1711getHourJiIwxys, keyboardOptions, keyboardActions6, timePickerColors6, $composer5, ((i20 << 3) & 7168) | 24576 | ((i20 << 18) & 29360128), 0);
                            TimePickerKt.DisplaySeparator(SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimePickerKt.DisplaySeparatorWidth, TimeInputTokens.INSTANCE.m2486getPeriodSelectorContainerHeightD9Ej5fM()), $composer5, 6);
                            Modifier.Companion companion3 = Modifier.INSTANCE;
                            Object key1$iv16 = minuteValue$delegate;
                            Object key2$iv6 = state;
                            final TimePickerState timePickerState16 = state;
                            final MutableState<TextFieldValue> mutableState6 = minuteValue$delegate;
                            int i21 = ($dirty2 >> 3) & 112;
                            $composer5.startReplaceableGroup(511388516);
                            ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                            invalid$iv$iv4 = $composer5.changed(key1$iv16) | $composer5.changed(key2$iv6);
                            it$iv$iv4 = $composer5.rememberedValue();
                            if (!invalid$iv$iv4) {
                                key1$iv3 = Composer.INSTANCE.getEmpty();
                                if (it$iv$iv4 == key1$iv3) {
                                    value$iv$iv5 = it$iv$iv4;
                                }
                                $composer5.endReplaceableGroup();
                                Modifier modifierOnPreviewKeyEvent3 = KeyInputModifierKt.onPreviewKeyEvent(companion3, (Function1) value$iv$iv5);
                                TextFieldValue textFieldValueTimeInputImpl$lambda$10 = TimePickerKt.TimeInputImpl$lambda$8(minuteValue$delegate);
                                Object key1$iv17 = state;
                                Object key2$iv7 = minuteValue$delegate;
                                final TimePickerState timePickerState17 = state;
                                final MutableState<TextFieldValue> mutableState7 = minuteValue$delegate;
                                int i110 = ($dirty2 >> 6) & 14;
                                $composer5.startReplaceableGroup(511388516);
                                ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                                invalid$iv$iv5 = $composer5.changed(key1$iv17) | $composer5.changed(key2$iv7);
                                it$iv$iv5 = $composer5.rememberedValue();
                                if (!invalid$iv$iv5) {
                                    key1$iv2 = Composer.INSTANCE.getEmpty();
                                    if (it$iv$iv5 == key1$iv2) {
                                        value$iv$iv6 = it$iv$iv5;
                                    }
                                    $composer5.endReplaceableGroup();
                                    Function1 function6 = (Function1) value$iv$iv6;
                                    TimePickerState timePickerState18 = state;
                                    int iM1712getMinuteJiIwxys5 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                    KeyboardOptions keyboardOptions6 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                                    Object key1$iv18 = state;
                                    final TimePickerState timePickerState19 = state;
                                    int i111 = ($dirty2 >> 6) & 14;
                                    $composer5.startReplaceableGroup(1157296644);
                                    ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                    invalid$iv$iv6 = $composer5.changed(key1$iv18);
                                    value$iv$iv7 = $composer5.rememberedValue();
                                    if (!invalid$iv$iv6) {
                                    }
                                    value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                            invoke2(keyboardActionScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(KeyboardActionScope $receiver) {
                                            Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                            timePickerState19.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                        }
                                    };
                                    $composer5.updateRememberedValue(value$iv$iv7);
                                    $composer5.endReplaceableGroup();
                                    KeyboardActions keyboardActions7 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                                    TimePickerColors timePickerColors7 = colors;
                                    int i112 = $dirty2;
                                    TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent3, textFieldValueTimeInputImpl$lambda$10, function6, timePickerState18, iM1712getMinuteJiIwxys5, keyboardOptions6, keyboardActions7, timePickerColors7, $composer5, ((i112 << 3) & 7168) | 24576 | ((i112 << 18) & 29360128), 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                value$iv$iv6 = (Function1) new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                        invoke2(textFieldValue);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(TextFieldValue newValue) {
                                        Intrinsics.checkNotNullParameter(newValue, "newValue");
                                        int iM1712getMinuteJiIwxys6 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                        TimePickerState timePickerState110 = timePickerState17;
                                        TextFieldValue textFieldValueTimeInputImpl$lambda$11 = TimePickerKt.TimeInputImpl$lambda$8(mutableState7);
                                        final MutableState<TextFieldValue> mutableState8 = mutableState7;
                                        TimePickerKt.m1907timeInputOnChangegIWD5Rc(iM1712getMinuteJiIwxys6, timePickerState110, newValue, textFieldValueTimeInputImpl$lambda$11, 59, new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                                invoke2(textFieldValue);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(TextFieldValue it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                mutableState8.setValue(it);
                                            }
                                        });
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv$iv6);
                                $composer5.endReplaceableGroup();
                                Function1 function7 = (Function1) value$iv$iv6;
                                TimePickerState timePickerState110 = state;
                                int iM1712getMinuteJiIwxys6 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                KeyboardOptions keyboardOptions7 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                                Object key1$iv19 = state;
                                final TimePickerState timePickerState111 = state;
                                int i113 = ($dirty2 >> 6) & 14;
                                $composer5.startReplaceableGroup(1157296644);
                                ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                invalid$iv$iv6 = $composer5.changed(key1$iv19);
                                value$iv$iv7 = $composer5.rememberedValue();
                                if (!invalid$iv$iv6) {
                                }
                                value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                        invoke2(keyboardActionScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(KeyboardActionScope $receiver) {
                                        Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                        timePickerState111.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv$iv7);
                                $composer5.endReplaceableGroup();
                                KeyboardActions keyboardActions8 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                                TimePickerColors timePickerColors8 = colors;
                                int i114 = $dirty2;
                                TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent3, textFieldValueTimeInputImpl$lambda$10, function7, timePickerState110, iM1712getMinuteJiIwxys6, keyboardOptions7, keyboardActions8, timePickerColors8, $composer5, ((i114 << 3) & 7168) | 24576 | ((i114 << 18) & 29360128), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            value$iv$iv5 = (Function1) new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                                    return m1909invokeZmokQxo(keyEvent.m3957unboximpl());
                                }

                                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                                public final Boolean m1909invokeZmokQxo(android.view.KeyEvent event) {
                                    Intrinsics.checkNotNullParameter(event, "event");
                                    boolean switchFocus = KeyEvent_androidKt.m3970getUtf16CodePointZmokQxo(event) == 0 && TextRange.m4768getStartimpl(TimePickerKt.TimeInputImpl$lambda$8(mutableState6).getSelection()) == 0;
                                    if (switchFocus) {
                                        timePickerState16.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1711getHourJiIwxys());
                                    }
                                    return Boolean.valueOf(switchFocus);
                                }
                            };
                            $composer5.updateRememberedValue(value$iv$iv5);
                            $composer5.endReplaceableGroup();
                            Modifier modifierOnPreviewKeyEvent4 = KeyInputModifierKt.onPreviewKeyEvent(companion3, (Function1) value$iv$iv5);
                            TextFieldValue textFieldValueTimeInputImpl$lambda$11 = TimePickerKt.TimeInputImpl$lambda$8(minuteValue$delegate);
                            Object key1$iv110 = state;
                            Object key2$iv8 = minuteValue$delegate;
                            final TimePickerState timePickerState112 = state;
                            final MutableState<TextFieldValue> mutableState8 = minuteValue$delegate;
                            int i115 = ($dirty2 >> 6) & 14;
                            $composer5.startReplaceableGroup(511388516);
                            ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                            invalid$iv$iv5 = $composer5.changed(key1$iv110) | $composer5.changed(key2$iv8);
                            it$iv$iv5 = $composer5.rememberedValue();
                            if (!invalid$iv$iv5) {
                                key1$iv2 = Composer.INSTANCE.getEmpty();
                                if (it$iv$iv5 == key1$iv2) {
                                    value$iv$iv6 = it$iv$iv5;
                                }
                                $composer5.endReplaceableGroup();
                                Function1 function8 = (Function1) value$iv$iv6;
                                TimePickerState timePickerState113 = state;
                                int iM1712getMinuteJiIwxys7 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                KeyboardOptions keyboardOptions8 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                                Object key1$iv111 = state;
                                final TimePickerState timePickerState114 = state;
                                int i116 = ($dirty2 >> 6) & 14;
                                $composer5.startReplaceableGroup(1157296644);
                                ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                invalid$iv$iv6 = $composer5.changed(key1$iv111);
                                value$iv$iv7 = $composer5.rememberedValue();
                                if (!invalid$iv$iv6) {
                                }
                                value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                        invoke2(keyboardActionScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(KeyboardActionScope $receiver) {
                                        Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                        timePickerState114.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv$iv7);
                                $composer5.endReplaceableGroup();
                                KeyboardActions keyboardActions9 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                                TimePickerColors timePickerColors9 = colors;
                                int i117 = $dirty2;
                                TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent4, textFieldValueTimeInputImpl$lambda$11, function8, timePickerState113, iM1712getMinuteJiIwxys7, keyboardOptions8, keyboardActions9, timePickerColors9, $composer5, ((i117 << 3) & 7168) | 24576 | ((i117 << 18) & 29360128), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            value$iv$iv6 = (Function1) new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                    invoke2(textFieldValue);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextFieldValue newValue) {
                                    Intrinsics.checkNotNullParameter(newValue, "newValue");
                                    int iM1712getMinuteJiIwxys8 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                    TimePickerState timePickerState115 = timePickerState112;
                                    TextFieldValue textFieldValueTimeInputImpl$lambda$12 = TimePickerKt.TimeInputImpl$lambda$8(mutableState8);
                                    final MutableState<TextFieldValue> mutableState9 = mutableState8;
                                    TimePickerKt.m1907timeInputOnChangegIWD5Rc(iM1712getMinuteJiIwxys8, timePickerState115, newValue, textFieldValueTimeInputImpl$lambda$12, 59, new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                            invoke2(textFieldValue);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(TextFieldValue it) {
                                            Intrinsics.checkNotNullParameter(it, "it");
                                            mutableState9.setValue(it);
                                        }
                                    });
                                }
                            };
                            $composer5.updateRememberedValue(value$iv$iv6);
                            $composer5.endReplaceableGroup();
                            Function1 function9 = (Function1) value$iv$iv6;
                            TimePickerState timePickerState115 = state;
                            int iM1712getMinuteJiIwxys8 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                            KeyboardOptions keyboardOptions9 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                            Object key1$iv112 = state;
                            final TimePickerState timePickerState116 = state;
                            int i118 = ($dirty2 >> 6) & 14;
                            $composer5.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                            invalid$iv$iv6 = $composer5.changed(key1$iv112);
                            value$iv$iv7 = $composer5.rememberedValue();
                            if (!invalid$iv$iv6) {
                            }
                            value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                    invoke2(keyboardActionScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyboardActionScope $receiver) {
                                    Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                    timePickerState116.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                }
                            };
                            $composer5.updateRememberedValue(value$iv$iv7);
                            $composer5.endReplaceableGroup();
                            KeyboardActions keyboardActions10 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                            TimePickerColors timePickerColors10 = colors;
                            int i119 = $dirty2;
                            TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent4, textFieldValueTimeInputImpl$lambda$11, function9, timePickerState115, iM1712getMinuteJiIwxys8, keyboardOptions9, keyboardActions10, timePickerColors10, $composer5, ((i119 << 3) & 7168) | 24576 | ((i119 << 18) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        value$iv$iv3 = (Function1) new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                invoke2(textFieldValue);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextFieldValue newValue) {
                                Intrinsics.checkNotNullParameter(newValue, "newValue");
                                int iM1711getHourJiIwxys2 = Selection.INSTANCE.m1711getHourJiIwxys();
                                TimePickerState timePickerState20 = timePickerState2;
                                TextFieldValue textFieldValueTimeInputImpl$lambda$6 = TimePickerKt.TimeInputImpl$lambda$5(mutableState2);
                                int i22 = timePickerState2.getIs24hour() ? 23 : 12;
                                final MutableState<TextFieldValue> mutableState9 = mutableState2;
                                TimePickerKt.m1907timeInputOnChangegIWD5Rc(iM1711getHourJiIwxys2, timePickerState20, newValue, textFieldValueTimeInputImpl$lambda$6, i22, new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$2$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                        invoke2(textFieldValue);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(TextFieldValue it) {
                                        Intrinsics.checkNotNullParameter(it, "it");
                                        mutableState9.setValue(it);
                                    }
                                });
                            }
                        };
                        $composer5.updateRememberedValue(value$iv$iv3);
                        $composer5.endReplaceableGroup();
                        Function1 function10 = (Function1) value$iv$iv3;
                        TimePickerState timePickerState20 = state;
                        int iM1711getHourJiIwxys2 = Selection.INSTANCE.m1711getHourJiIwxys();
                        KeyboardOptions keyboardOptions10 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4933getNexteUduSuo(), 3, null);
                        Object key1$iv20 = state;
                        final TimePickerState timePickerState21 = state;
                        int i22 = ($dirty2 >> 6) & 14;
                        $composer5.startReplaceableGroup(1157296644);
                        ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                        invalid$iv$iv3 = $composer5.changed(key1$iv20);
                        it$iv$iv3 = $composer5.rememberedValue();
                        if (!invalid$iv$iv3) {
                            key1$iv4 = Composer.INSTANCE.getEmpty();
                            if (it$iv$iv3 == key1$iv4) {
                                value$iv$iv4 = it$iv$iv3;
                            }
                            $composer5.endReplaceableGroup();
                            KeyboardActions keyboardActions11 = new KeyboardActions(null, null, (Function1) value$iv$iv4, null, null, null, 59, null);
                            TimePickerColors timePickerColors11 = colors;
                            int i23 = $dirty2;
                            TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnKeyEvent, textFieldValueTimeInputImpl$lambda$5, function10, timePickerState20, iM1711getHourJiIwxys2, keyboardOptions10, keyboardActions11, timePickerColors11, $composer5, ((i23 << 3) & 7168) | 24576 | ((i23 << 18) & 29360128), 0);
                            TimePickerKt.DisplaySeparator(SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimePickerKt.DisplaySeparatorWidth, TimeInputTokens.INSTANCE.m2486getPeriodSelectorContainerHeightD9Ej5fM()), $composer5, 6);
                            Modifier.Companion companion4 = Modifier.INSTANCE;
                            Object key1$iv113 = minuteValue$delegate;
                            Object key2$iv9 = state;
                            final TimePickerState timePickerState117 = state;
                            final MutableState<TextFieldValue> mutableState9 = minuteValue$delegate;
                            int i24 = ($dirty2 >> 3) & 112;
                            $composer5.startReplaceableGroup(511388516);
                            ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                            invalid$iv$iv4 = $composer5.changed(key1$iv113) | $composer5.changed(key2$iv9);
                            it$iv$iv4 = $composer5.rememberedValue();
                            if (!invalid$iv$iv4) {
                                key1$iv3 = Composer.INSTANCE.getEmpty();
                                if (it$iv$iv4 == key1$iv3) {
                                    value$iv$iv5 = it$iv$iv4;
                                }
                                $composer5.endReplaceableGroup();
                                Modifier modifierOnPreviewKeyEvent5 = KeyInputModifierKt.onPreviewKeyEvent(companion4, (Function1) value$iv$iv5);
                                TextFieldValue textFieldValueTimeInputImpl$lambda$12 = TimePickerKt.TimeInputImpl$lambda$8(minuteValue$delegate);
                                Object key1$iv114 = state;
                                Object key2$iv10 = minuteValue$delegate;
                                final TimePickerState timePickerState118 = state;
                                final MutableState<TextFieldValue> mutableState10 = minuteValue$delegate;
                                int i1110 = ($dirty2 >> 6) & 14;
                                $composer5.startReplaceableGroup(511388516);
                                ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                                invalid$iv$iv5 = $composer5.changed(key1$iv114) | $composer5.changed(key2$iv10);
                                it$iv$iv5 = $composer5.rememberedValue();
                                if (!invalid$iv$iv5) {
                                    key1$iv2 = Composer.INSTANCE.getEmpty();
                                    if (it$iv$iv5 == key1$iv2) {
                                        value$iv$iv6 = it$iv$iv5;
                                    }
                                    $composer5.endReplaceableGroup();
                                    Function1 function11 = (Function1) value$iv$iv6;
                                    TimePickerState timePickerState119 = state;
                                    int iM1712getMinuteJiIwxys9 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                    KeyboardOptions keyboardOptions11 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                                    Object key1$iv115 = state;
                                    final TimePickerState timePickerState1110 = state;
                                    int i1111 = ($dirty2 >> 6) & 14;
                                    $composer5.startReplaceableGroup(1157296644);
                                    ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                    invalid$iv$iv6 = $composer5.changed(key1$iv115);
                                    value$iv$iv7 = $composer5.rememberedValue();
                                    if (!invalid$iv$iv6) {
                                    }
                                    value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                            invoke2(keyboardActionScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(KeyboardActionScope $receiver) {
                                            Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                            timePickerState1110.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                        }
                                    };
                                    $composer5.updateRememberedValue(value$iv$iv7);
                                    $composer5.endReplaceableGroup();
                                    KeyboardActions keyboardActions12 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                                    TimePickerColors timePickerColors12 = colors;
                                    int i1112 = $dirty2;
                                    TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent5, textFieldValueTimeInputImpl$lambda$12, function11, timePickerState119, iM1712getMinuteJiIwxys9, keyboardOptions11, keyboardActions12, timePickerColors12, $composer5, ((i1112 << 3) & 7168) | 24576 | ((i1112 << 18) & 29360128), 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                value$iv$iv6 = (Function1) new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                        invoke2(textFieldValue);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(TextFieldValue newValue) {
                                        Intrinsics.checkNotNullParameter(newValue, "newValue");
                                        int iM1712getMinuteJiIwxys10 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                        TimePickerState timePickerState1111 = timePickerState118;
                                        TextFieldValue textFieldValueTimeInputImpl$lambda$13 = TimePickerKt.TimeInputImpl$lambda$8(mutableState10);
                                        final MutableState<TextFieldValue> mutableState11 = mutableState10;
                                        TimePickerKt.m1907timeInputOnChangegIWD5Rc(iM1712getMinuteJiIwxys10, timePickerState1111, newValue, textFieldValueTimeInputImpl$lambda$13, 59, new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                                invoke2(textFieldValue);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(TextFieldValue it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                mutableState11.setValue(it);
                                            }
                                        });
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv$iv6);
                                $composer5.endReplaceableGroup();
                                Function1 function12 = (Function1) value$iv$iv6;
                                TimePickerState timePickerState1111 = state;
                                int iM1712getMinuteJiIwxys10 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                KeyboardOptions keyboardOptions12 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                                Object key1$iv116 = state;
                                final TimePickerState timePickerState1112 = state;
                                int i1113 = ($dirty2 >> 6) & 14;
                                $composer5.startReplaceableGroup(1157296644);
                                ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                invalid$iv$iv6 = $composer5.changed(key1$iv116);
                                value$iv$iv7 = $composer5.rememberedValue();
                                if (!invalid$iv$iv6) {
                                }
                                value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                        invoke2(keyboardActionScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(KeyboardActionScope $receiver) {
                                        Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                        timePickerState1112.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv$iv7);
                                $composer5.endReplaceableGroup();
                                KeyboardActions keyboardActions13 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                                TimePickerColors timePickerColors13 = colors;
                                int i1114 = $dirty2;
                                TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent5, textFieldValueTimeInputImpl$lambda$12, function12, timePickerState1111, iM1712getMinuteJiIwxys10, keyboardOptions12, keyboardActions13, timePickerColors13, $composer5, ((i1114 << 3) & 7168) | 24576 | ((i1114 << 18) & 29360128), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            value$iv$iv5 = (Function1) new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                                    return m1909invokeZmokQxo(keyEvent.m3957unboximpl());
                                }

                                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                                public final Boolean m1909invokeZmokQxo(android.view.KeyEvent event) {
                                    Intrinsics.checkNotNullParameter(event, "event");
                                    boolean switchFocus = KeyEvent_androidKt.m3970getUtf16CodePointZmokQxo(event) == 0 && TextRange.m4768getStartimpl(TimePickerKt.TimeInputImpl$lambda$8(mutableState9).getSelection()) == 0;
                                    if (switchFocus) {
                                        timePickerState117.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1711getHourJiIwxys());
                                    }
                                    return Boolean.valueOf(switchFocus);
                                }
                            };
                            $composer5.updateRememberedValue(value$iv$iv5);
                            $composer5.endReplaceableGroup();
                            Modifier modifierOnPreviewKeyEvent6 = KeyInputModifierKt.onPreviewKeyEvent(companion4, (Function1) value$iv$iv5);
                            TextFieldValue textFieldValueTimeInputImpl$lambda$13 = TimePickerKt.TimeInputImpl$lambda$8(minuteValue$delegate);
                            Object key1$iv117 = state;
                            Object key2$iv11 = minuteValue$delegate;
                            final TimePickerState timePickerState1113 = state;
                            final MutableState<TextFieldValue> mutableState11 = minuteValue$delegate;
                            int i1115 = ($dirty2 >> 6) & 14;
                            $composer5.startReplaceableGroup(511388516);
                            ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                            invalid$iv$iv5 = $composer5.changed(key1$iv117) | $composer5.changed(key2$iv11);
                            it$iv$iv5 = $composer5.rememberedValue();
                            if (!invalid$iv$iv5) {
                                key1$iv2 = Composer.INSTANCE.getEmpty();
                                if (it$iv$iv5 == key1$iv2) {
                                    value$iv$iv6 = it$iv$iv5;
                                }
                                $composer5.endReplaceableGroup();
                                Function1 function13 = (Function1) value$iv$iv6;
                                TimePickerState timePickerState1114 = state;
                                int iM1712getMinuteJiIwxys11 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                KeyboardOptions keyboardOptions13 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                                Object key1$iv118 = state;
                                final TimePickerState timePickerState1115 = state;
                                int i1116 = ($dirty2 >> 6) & 14;
                                $composer5.startReplaceableGroup(1157296644);
                                ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                invalid$iv$iv6 = $composer5.changed(key1$iv118);
                                value$iv$iv7 = $composer5.rememberedValue();
                                if (!invalid$iv$iv6) {
                                }
                                value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                        invoke2(keyboardActionScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(KeyboardActionScope $receiver) {
                                        Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                        timePickerState1115.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv$iv7);
                                $composer5.endReplaceableGroup();
                                KeyboardActions keyboardActions14 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                                TimePickerColors timePickerColors14 = colors;
                                int i1117 = $dirty2;
                                TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent6, textFieldValueTimeInputImpl$lambda$13, function13, timePickerState1114, iM1712getMinuteJiIwxys11, keyboardOptions13, keyboardActions14, timePickerColors14, $composer5, ((i1117 << 3) & 7168) | 24576 | ((i1117 << 18) & 29360128), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            value$iv$iv6 = (Function1) new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                    invoke2(textFieldValue);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextFieldValue newValue) {
                                    Intrinsics.checkNotNullParameter(newValue, "newValue");
                                    int iM1712getMinuteJiIwxys12 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                    TimePickerState timePickerState1116 = timePickerState1113;
                                    TextFieldValue textFieldValueTimeInputImpl$lambda$14 = TimePickerKt.TimeInputImpl$lambda$8(mutableState11);
                                    final MutableState<TextFieldValue> mutableState12 = mutableState11;
                                    TimePickerKt.m1907timeInputOnChangegIWD5Rc(iM1712getMinuteJiIwxys12, timePickerState1116, newValue, textFieldValueTimeInputImpl$lambda$14, 59, new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                            invoke2(textFieldValue);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(TextFieldValue it) {
                                            Intrinsics.checkNotNullParameter(it, "it");
                                            mutableState12.setValue(it);
                                        }
                                    });
                                }
                            };
                            $composer5.updateRememberedValue(value$iv$iv6);
                            $composer5.endReplaceableGroup();
                            Function1 function14 = (Function1) value$iv$iv6;
                            TimePickerState timePickerState1116 = state;
                            int iM1712getMinuteJiIwxys12 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                            KeyboardOptions keyboardOptions14 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                            Object key1$iv119 = state;
                            final TimePickerState timePickerState1117 = state;
                            int i1118 = ($dirty2 >> 6) & 14;
                            $composer5.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                            invalid$iv$iv6 = $composer5.changed(key1$iv119);
                            value$iv$iv7 = $composer5.rememberedValue();
                            if (!invalid$iv$iv6) {
                            }
                            value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                    invoke2(keyboardActionScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyboardActionScope $receiver) {
                                    Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                    timePickerState1117.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                }
                            };
                            $composer5.updateRememberedValue(value$iv$iv7);
                            $composer5.endReplaceableGroup();
                            KeyboardActions keyboardActions15 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                            TimePickerColors timePickerColors15 = colors;
                            int i1119 = $dirty2;
                            TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent6, textFieldValueTimeInputImpl$lambda$13, function14, timePickerState1116, iM1712getMinuteJiIwxys12, keyboardOptions14, keyboardActions15, timePickerColors15, $composer5, ((i1119 << 3) & 7168) | 24576 | ((i1119 << 18) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        value$iv$iv4 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$3$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope $receiver) {
                                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                timePickerState21.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                            }
                        };
                        $composer5.updateRememberedValue(value$iv$iv4);
                        $composer5.endReplaceableGroup();
                        KeyboardActions keyboardActions16 = new KeyboardActions(null, null, (Function1) value$iv$iv4, null, null, null, 59, null);
                        TimePickerColors timePickerColors16 = colors;
                        int i25 = $dirty2;
                        TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnKeyEvent, textFieldValueTimeInputImpl$lambda$5, function10, timePickerState20, iM1711getHourJiIwxys2, keyboardOptions10, keyboardActions16, timePickerColors16, $composer5, ((i25 << 3) & 7168) | 24576 | ((i25 << 18) & 29360128), 0);
                        TimePickerKt.DisplaySeparator(SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimePickerKt.DisplaySeparatorWidth, TimeInputTokens.INSTANCE.m2486getPeriodSelectorContainerHeightD9Ej5fM()), $composer5, 6);
                        Modifier.Companion companion5 = Modifier.INSTANCE;
                        Object key1$iv1110 = minuteValue$delegate;
                        Object key2$iv12 = state;
                        final TimePickerState timePickerState1118 = state;
                        final MutableState<TextFieldValue> mutableState12 = minuteValue$delegate;
                        int i26 = ($dirty2 >> 3) & 112;
                        $composer5.startReplaceableGroup(511388516);
                        ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                        invalid$iv$iv4 = $composer5.changed(key1$iv1110) | $composer5.changed(key2$iv12);
                        it$iv$iv4 = $composer5.rememberedValue();
                        if (!invalid$iv$iv4) {
                            key1$iv3 = Composer.INSTANCE.getEmpty();
                            if (it$iv$iv4 == key1$iv3) {
                                value$iv$iv5 = it$iv$iv4;
                            }
                            $composer5.endReplaceableGroup();
                            Modifier modifierOnPreviewKeyEvent7 = KeyInputModifierKt.onPreviewKeyEvent(companion5, (Function1) value$iv$iv5);
                            TextFieldValue textFieldValueTimeInputImpl$lambda$14 = TimePickerKt.TimeInputImpl$lambda$8(minuteValue$delegate);
                            Object key1$iv1111 = state;
                            Object key2$iv13 = minuteValue$delegate;
                            final TimePickerState timePickerState1119 = state;
                            final MutableState<TextFieldValue> mutableState13 = minuteValue$delegate;
                            int i11110 = ($dirty2 >> 6) & 14;
                            $composer5.startReplaceableGroup(511388516);
                            ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                            invalid$iv$iv5 = $composer5.changed(key1$iv1111) | $composer5.changed(key2$iv13);
                            it$iv$iv5 = $composer5.rememberedValue();
                            if (!invalid$iv$iv5) {
                                key1$iv2 = Composer.INSTANCE.getEmpty();
                                if (it$iv$iv5 == key1$iv2) {
                                    value$iv$iv6 = it$iv$iv5;
                                }
                                $composer5.endReplaceableGroup();
                                Function1 function15 = (Function1) value$iv$iv6;
                                TimePickerState timePickerState11110 = state;
                                int iM1712getMinuteJiIwxys13 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                KeyboardOptions keyboardOptions15 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                                Object key1$iv1112 = state;
                                final TimePickerState timePickerState11111 = state;
                                int i11111 = ($dirty2 >> 6) & 14;
                                $composer5.startReplaceableGroup(1157296644);
                                ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                invalid$iv$iv6 = $composer5.changed(key1$iv1112);
                                value$iv$iv7 = $composer5.rememberedValue();
                                if (!invalid$iv$iv6) {
                                }
                                value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                        invoke2(keyboardActionScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(KeyboardActionScope $receiver) {
                                        Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                        timePickerState11111.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                    }
                                };
                                $composer5.updateRememberedValue(value$iv$iv7);
                                $composer5.endReplaceableGroup();
                                KeyboardActions keyboardActions17 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                                TimePickerColors timePickerColors17 = colors;
                                int i11112 = $dirty2;
                                TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent7, textFieldValueTimeInputImpl$lambda$14, function15, timePickerState11110, iM1712getMinuteJiIwxys13, keyboardOptions15, keyboardActions17, timePickerColors17, $composer5, ((i11112 << 3) & 7168) | 24576 | ((i11112 << 18) & 29360128), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            value$iv$iv6 = (Function1) new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                    invoke2(textFieldValue);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextFieldValue newValue) {
                                    Intrinsics.checkNotNullParameter(newValue, "newValue");
                                    int iM1712getMinuteJiIwxys14 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                    TimePickerState timePickerState11112 = timePickerState1119;
                                    TextFieldValue textFieldValueTimeInputImpl$lambda$15 = TimePickerKt.TimeInputImpl$lambda$8(mutableState13);
                                    final MutableState<TextFieldValue> mutableState14 = mutableState13;
                                    TimePickerKt.m1907timeInputOnChangegIWD5Rc(iM1712getMinuteJiIwxys14, timePickerState11112, newValue, textFieldValueTimeInputImpl$lambda$15, 59, new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                            invoke2(textFieldValue);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(TextFieldValue it) {
                                            Intrinsics.checkNotNullParameter(it, "it");
                                            mutableState14.setValue(it);
                                        }
                                    });
                                }
                            };
                            $composer5.updateRememberedValue(value$iv$iv6);
                            $composer5.endReplaceableGroup();
                            Function1 function16 = (Function1) value$iv$iv6;
                            TimePickerState timePickerState11112 = state;
                            int iM1712getMinuteJiIwxys14 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                            KeyboardOptions keyboardOptions16 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                            Object key1$iv1113 = state;
                            final TimePickerState timePickerState11113 = state;
                            int i11113 = ($dirty2 >> 6) & 14;
                            $composer5.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                            invalid$iv$iv6 = $composer5.changed(key1$iv1113);
                            value$iv$iv7 = $composer5.rememberedValue();
                            if (!invalid$iv$iv6) {
                            }
                            value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                    invoke2(keyboardActionScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyboardActionScope $receiver) {
                                    Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                    timePickerState11113.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                }
                            };
                            $composer5.updateRememberedValue(value$iv$iv7);
                            $composer5.endReplaceableGroup();
                            KeyboardActions keyboardActions18 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                            TimePickerColors timePickerColors18 = colors;
                            int i11114 = $dirty2;
                            TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent7, textFieldValueTimeInputImpl$lambda$14, function16, timePickerState11112, iM1712getMinuteJiIwxys14, keyboardOptions16, keyboardActions18, timePickerColors18, $composer5, ((i11114 << 3) & 7168) | 24576 | ((i11114 << 18) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        value$iv$iv5 = (Function1) new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                                return m1909invokeZmokQxo(keyEvent.m3957unboximpl());
                            }

                            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                            public final Boolean m1909invokeZmokQxo(android.view.KeyEvent event) {
                                Intrinsics.checkNotNullParameter(event, "event");
                                boolean switchFocus = KeyEvent_androidKt.m3970getUtf16CodePointZmokQxo(event) == 0 && TextRange.m4768getStartimpl(TimePickerKt.TimeInputImpl$lambda$8(mutableState12).getSelection()) == 0;
                                if (switchFocus) {
                                    timePickerState1118.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1711getHourJiIwxys());
                                }
                                return Boolean.valueOf(switchFocus);
                            }
                        };
                        $composer5.updateRememberedValue(value$iv$iv5);
                        $composer5.endReplaceableGroup();
                        Modifier modifierOnPreviewKeyEvent8 = KeyInputModifierKt.onPreviewKeyEvent(companion5, (Function1) value$iv$iv5);
                        TextFieldValue textFieldValueTimeInputImpl$lambda$15 = TimePickerKt.TimeInputImpl$lambda$8(minuteValue$delegate);
                        Object key1$iv1114 = state;
                        Object key2$iv14 = minuteValue$delegate;
                        final TimePickerState timePickerState11114 = state;
                        final MutableState<TextFieldValue> mutableState14 = minuteValue$delegate;
                        int i11115 = ($dirty2 >> 6) & 14;
                        $composer5.startReplaceableGroup(511388516);
                        ComposerKt.sourceInformation($composer5, "CC(remember)P(1,2):Composables.kt#9igjgp");
                        invalid$iv$iv5 = $composer5.changed(key1$iv1114) | $composer5.changed(key2$iv14);
                        it$iv$iv5 = $composer5.rememberedValue();
                        if (!invalid$iv$iv5) {
                            key1$iv2 = Composer.INSTANCE.getEmpty();
                            if (it$iv$iv5 == key1$iv2) {
                                value$iv$iv6 = it$iv$iv5;
                            }
                            $composer5.endReplaceableGroup();
                            Function1 function17 = (Function1) value$iv$iv6;
                            TimePickerState timePickerState11115 = state;
                            int iM1712getMinuteJiIwxys15 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                            KeyboardOptions keyboardOptions17 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                            Object key1$iv1115 = state;
                            final TimePickerState timePickerState11116 = state;
                            int i11116 = ($dirty2 >> 6) & 14;
                            $composer5.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                            invalid$iv$iv6 = $composer5.changed(key1$iv1115);
                            value$iv$iv7 = $composer5.rememberedValue();
                            if (!invalid$iv$iv6) {
                            }
                            value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                    invoke2(keyboardActionScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(KeyboardActionScope $receiver) {
                                    Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                    timePickerState11116.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                }
                            };
                            $composer5.updateRememberedValue(value$iv$iv7);
                            $composer5.endReplaceableGroup();
                            KeyboardActions keyboardActions19 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                            TimePickerColors timePickerColors19 = colors;
                            int i11117 = $dirty2;
                            TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent8, textFieldValueTimeInputImpl$lambda$15, function17, timePickerState11115, iM1712getMinuteJiIwxys15, keyboardOptions17, keyboardActions19, timePickerColors19, $composer5, ((i11117 << 3) & 7168) | 24576 | ((i11117 << 18) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        value$iv$iv6 = (Function1) new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                invoke2(textFieldValue);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextFieldValue newValue) {
                                Intrinsics.checkNotNullParameter(newValue, "newValue");
                                int iM1712getMinuteJiIwxys16 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                                TimePickerState timePickerState11117 = timePickerState11114;
                                TextFieldValue textFieldValueTimeInputImpl$lambda$16 = TimePickerKt.TimeInputImpl$lambda$8(mutableState14);
                                final MutableState<TextFieldValue> mutableState15 = mutableState14;
                                TimePickerKt.m1907timeInputOnChangegIWD5Rc(iM1712getMinuteJiIwxys16, timePickerState11117, newValue, textFieldValueTimeInputImpl$lambda$16, 59, new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$5$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue) {
                                        invoke2(textFieldValue);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(TextFieldValue it) {
                                        Intrinsics.checkNotNullParameter(it, "it");
                                        mutableState15.setValue(it);
                                    }
                                });
                            }
                        };
                        $composer5.updateRememberedValue(value$iv$iv6);
                        $composer5.endReplaceableGroup();
                        Function1 function18 = (Function1) value$iv$iv6;
                        TimePickerState timePickerState11117 = state;
                        int iM1712getMinuteJiIwxys16 = Selection.INSTANCE.m1712getMinuteJiIwxys();
                        KeyboardOptions keyboardOptions18 = new KeyboardOptions(0, false, KeyboardType.INSTANCE.m4979getNumberPjHm6EE(), ImeAction.INSTANCE.m4931getDoneeUduSuo(), 3, null);
                        Object key1$iv1116 = state;
                        final TimePickerState timePickerState11118 = state;
                        int i11118 = ($dirty2 >> 6) & 14;
                        $composer5.startReplaceableGroup(1157296644);
                        ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                        invalid$iv$iv6 = $composer5.changed(key1$iv1116);
                        value$iv$iv7 = $composer5.rememberedValue();
                        if (!invalid$iv$iv6) {
                        }
                        value$iv$iv7 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1$6$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                                invoke2(keyboardActionScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(KeyboardActionScope $receiver) {
                                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                timePickerState11118.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                            }
                        };
                        $composer5.updateRememberedValue(value$iv$iv7);
                        $composer5.endReplaceableGroup();
                        KeyboardActions keyboardActions110 = new KeyboardActions(null, null, (Function1) value$iv$iv7, null, null, null, 59, null);
                        TimePickerColors timePickerColors110 = colors;
                        int i11119 = $dirty2;
                        TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifierOnPreviewKeyEvent8, textFieldValueTimeInputImpl$lambda$15, function18, timePickerState11117, iM1712getMinuteJiIwxys16, keyboardOptions18, keyboardActions110, timePickerColors110, $composer5, ((i11119 << 3) & 7168) | 24576 | ((i11119 << 18) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer5.skipToGroupEnd();
                }
            }), $composer2, 56);
            $composer2.startReplaceableGroup(565119480);
            ComposerKt.sourceInformation($composer2, "786@33429L370");
            if (state.getIs24hour()) {
                $composer3 = $composer2;
            } else {
                $composer3 = $composer2;
                Modifier modifier$iv2 = PaddingKt.m491paddingqDBjuR0$default(modifier, PeriodToggleMargin, 0.0f, 0.0f, 0.0f, 14, null);
                $composer3.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv2 = (0 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume4 = $composer3.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density density$iv$iv2 = (Density) objConsume4;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume5 = $composer3.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume6 = $composer3.consume(localViewConfiguration2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor2);
                } else {
                    $composer3.useNode();
                }
                $composer3.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer3);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer3.enableReusing();
                function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i5 = ($changed$iv$iv$iv2 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i6 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1645137119, "C787@33497L288:TimePicker.kt#uh7d8r");
                VerticalPeriodToggle(SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m2487getPeriodSelectorContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m2486getPeriodSelectorContainerHeightD9Ej5fM()), state, colors, $composer3, (($dirty2 >> 3) & 112) | 6 | (($dirty2 << 3) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
            }
            $composer3.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.TimeInputImpl.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i7) {
                TimePickerKt.TimeInputImpl(modifier, colors, state, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$lambda$5(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$lambda$8(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HorizontalClockDisplay(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(755539561);
        ComposerKt.sourceInformation($composer2, "C(HorizontalClockDisplay)P(1)802@33922L554:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(colors) ? 32 : 16;
        }
        if (($dirty & 91) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(755539561, $dirty, -1, "androidx.compose.material3.HorizontalClockDisplay (TimePicker.kt:801)");
            }
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getCenter();
            $composer2.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation($composer2, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 276693704, "C79@4027L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -385990141, "C803@33981L34:TimePicker.kt#uh7d8r");
            ClockDisplayNumbers(state, colors, $composer2, ($dirty & 14) | ($dirty & 112));
            $composer2.startReplaceableGroup(-552398963);
            ComposerKt.sourceInformation($composer2, "805@34059L401");
            if (!state.getIs24hour()) {
                Modifier modifier$iv2 = PaddingKt.m491paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, PeriodToggleMargin, 0.0f, 0.0f, 13, null);
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                int $changed$iv = ((6 >> 3) & 14) | ((6 >> 3) & 112);
                MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, $changed$iv);
                int $changed$iv$iv2 = (6 << 3) & 112;
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume4 = $composer2.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density$iv$iv2 = (Density) objConsume4;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume5 = $composer2.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume6 = $composer2.consume(localViewConfiguration2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor2);
                } else {
                    $composer2.useNode();
                }
                $composer2.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer2);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer2.enableReusing();
                function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i3 = ($changed$iv$iv$iv2 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 34966463, "C806@34136L310:TimePicker.kt#uh7d8r");
                HorizontalPeriodToggle(SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m2498getPeriodSelectorHorizontalContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m2497getPeriodSelectorHorizontalContainerHeightD9Ej5fM()), state, colors, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
            }
            $composer2.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.HorizontalClockDisplay.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i5) {
                TimePickerKt.HorizontalClockDisplay(state, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerticalClockDisplay(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(2054675515);
        ComposerKt.sourceInformation($composer2, "C(VerticalClockDisplay)P(1)821@34581L549:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(colors) ? 32 : 16;
        }
        if (($dirty & 91) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2054675515, $dirty, -1, "androidx.compose.material3.VerticalClockDisplay (TimePicker.kt:820)");
            }
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getCenter();
            $composer2.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation($composer2, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -326682283, "C80@4021L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1085478083, "C822@34639L34:TimePicker.kt#uh7d8r");
            ClockDisplayNumbers(state, colors, $composer2, ($dirty & 14) | ($dirty & 112));
            $composer2.startReplaceableGroup(952907597);
            ComposerKt.sourceInformation($composer2, "824@34717L397");
            if (!state.getIs24hour()) {
                Modifier modifier$iv2 = PaddingKt.m491paddingqDBjuR0$default(Modifier.INSTANCE, PeriodToggleMargin, 0.0f, 0.0f, 0.0f, 14, null);
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                int $changed$iv = ((6 >> 3) & 14) | ((6 >> 3) & 112);
                MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, $changed$iv);
                int $changed$iv$iv2 = (6 << 3) & 112;
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume4 = $composer2.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density$iv$iv2 = (Density) objConsume4;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume5 = $composer2.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume6 = $composer2.consume(localViewConfiguration2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor2);
                } else {
                    $composer2.useNode();
                }
                $composer2.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer2);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer2.enableReusing();
                function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i3 = ($changed$iv$iv$iv2 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1130526335, "C825@34796L304:TimePicker.kt#uh7d8r");
                VerticalPeriodToggle(SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m2501getPeriodSelectorVerticalContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m2500getPeriodSelectorVerticalContainerHeightD9Ej5fM()), state, colors, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
            }
            $composer2.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.VerticalClockDisplay.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i5) {
                TimePickerKt.VerticalClockDisplay(state, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockDisplayNumbers(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-934561141);
        ComposerKt.sourceInformation($composer2, "C(ClockDisplayNumbers)P(1)844@35316L10,843@35244L1047:TimePicker.kt#uh7d8r");
        final int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(colors) ? 32 : 16;
        }
        if (($dirty & 91) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-934561141, $dirty, -1, "androidx.compose.material3.ClockDisplayNumbers (TimePicker.kt:839)");
            }
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{TextKt.getLocalTextStyle().provides(TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer2, 6), TimePickerTokens.INSTANCE.getTimeSelectorLabelTextFont()))}, ComposableLambdaKt.composableLambda($composer2, -477913269, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockDisplayNumbers.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C846@35380L905:TimePicker.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-477913269, $changed2, -1, "androidx.compose.material3.ClockDisplayNumbers.<anonymous> (TimePicker.kt:845)");
                        }
                        TimePickerState timePickerState = state;
                        TimePickerColors timePickerColors = colors;
                        int i = $dirty;
                        $composer3.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer3.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer3.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer3.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            $composer3.createNode(constructor);
                        } else {
                            $composer3.useNode();
                        }
                        $composer3.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer3);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer3.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer3.startReplaceableGroup(2058660585);
                        int i2 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        int i3 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 94464085, "C847@35398L338,857@35749L181,863@35943L332:TimePicker.kt#uh7d8r");
                        int i4 = ((i << 9) & 57344) | ((i << 6) & 896) | 3078;
                        TimePickerKt.m1901TimeSelectoruXwN82Y(SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m2504getTimeSelectorContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m2503getTimeSelectorContainerHeightD9Ej5fM()), timePickerState.getHourForDisplay$material3_release(), timePickerState, Selection.INSTANCE.m1711getHourJiIwxys(), timePickerColors, $composer3, i4);
                        TimePickerKt.DisplaySeparator(SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimePickerKt.DisplaySeparatorWidth, TimePickerTokens.INSTANCE.m2500getPeriodSelectorVerticalContainerHeightD9Ej5fM()), $composer3, 6);
                        TimePickerKt.m1901TimeSelectoruXwN82Y(SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m2504getTimeSelectorContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m2503getTimeSelectorContainerHeightD9Ej5fM()), timePickerState.getMinute(), timePickerState, Selection.INSTANCE.m1712getMinuteJiIwxys(), timePickerColors, $composer3, i4);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, 56);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockDisplayNumbers.2
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
                TimePickerKt.ClockDisplayNumbers(state, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HorizontalPeriodToggle(final Modifier modifier, final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Object value$iv$iv;
        Composer $composer2 = $composer.startRestartGroup(1261215927);
        ComposerKt.sourceInformation($composer2, "C(HorizontalPeriodToggle)P(1,2)883@36453L904,908@37404L9,910@37439L206:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(state) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 731) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1261215927, $dirty2, -1, "androidx.compose.material3.HorizontalPeriodToggle (TimePicker.kt:878)");
            }
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new MeasurePolicy() { // from class: androidx.compose.material3.TimePickerKt$HorizontalPeriodToggle$measurePolicy$1$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
                        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
                        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
                        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
                        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo11measure3p2s80s(MeasureScope MeasurePolicy, List<? extends Measurable> measurables, long constraints) {
                        Intrinsics.checkNotNullParameter(MeasurePolicy, "$this$MeasurePolicy");
                        Intrinsics.checkNotNullParameter(measurables, "measurables");
                        List<? extends Measurable> $this$first$iv = measurables;
                        for (Object element$iv : $this$first$iv) {
                            Measurable it = (Measurable) element$iv;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "Spacer")) {
                                Measurable spacer = (Measurable) element$iv;
                                final Placeable spacerPlaceable = spacer.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : MathKt.roundToInt(MeasurePolicy.mo327toPx0680j_4(TimePickerTokens.INSTANCE.m2499getPeriodSelectorOutlineWidthD9Ej5fM())), (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0));
                                List<? extends Measurable> $this$filter$iv = measurables;
                                Collection destination$iv$iv = new ArrayList();
                                for (Object element$iv$iv : $this$filter$iv) {
                                    Measurable it2 = (Measurable) element$iv$iv;
                                    if (!Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Spacer")) {
                                        destination$iv$iv.add(element$iv$iv);
                                    }
                                }
                                Iterable $this$map$iv = (List) destination$iv$iv;
                                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                for (Object item$iv$iv : $this$map$iv) {
                                    Measurable item = (Measurable) item$iv$iv;
                                    destination$iv$iv2.add(item.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : Constraints.m5218getMaxWidthimpl(constraints) / 2, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0)));
                                }
                                final List items = (List) destination$iv$iv2;
                                return MeasureScope.CC.layout$default(MeasurePolicy, Constraints.m5218getMaxWidthimpl(constraints), Constraints.m5217getMaxHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$HorizontalPeriodToggle$measurePolicy$1$1$measure$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                        invoke2(placementScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Placeable.PlacementScope layout) {
                                        Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                        Placeable.PlacementScope.place$default(layout, items.get(0), 0, 0, 0.0f, 4, null);
                                        Placeable.PlacementScope.place$default(layout, items.get(1), items.get(0).getWidth(), 0, 0.0f, 4, null);
                                        Placeable.PlacementScope.place$default(layout, spacerPlaceable, items.get(0).getWidth() - (spacerPlaceable.getWidth() / 2), 0, 0.0f, 4, null);
                                    }
                                }, 4, null);
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            MeasurePolicy measurePolicy = (MeasurePolicy) value$iv$iv;
            Shape shape = ShapesKt.toShape(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(shape, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape2 = (CornerBasedShape) shape;
            PeriodToggleImpl(modifier, state, colors, measurePolicy, ShapesKt.start(shape2), ShapesKt.end(shape2), $composer2, ($dirty2 & 14) | 3072 | ($dirty2 & 112) | ($dirty2 & 896));
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.HorizontalPeriodToggle.1
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
                TimePickerKt.HorizontalPeriodToggle(modifier, state, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerticalPeriodToggle(final Modifier modifier, final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Object value$iv$iv;
        Composer $composer2 = $composer.startRestartGroup(-1898918107);
        ComposerKt.sourceInformation($composer2, "C(VerticalPeriodToggle)P(1,2)926@37805L911,951@38763L9,953@38798L207:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(state) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 731) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1898918107, $dirty2, -1, "androidx.compose.material3.VerticalPeriodToggle (TimePicker.kt:921)");
            }
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new MeasurePolicy() { // from class: androidx.compose.material3.TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
                        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
                        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
                        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
                        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo11measure3p2s80s(MeasureScope MeasurePolicy, List<? extends Measurable> measurables, long constraints) {
                        Intrinsics.checkNotNullParameter(MeasurePolicy, "$this$MeasurePolicy");
                        Intrinsics.checkNotNullParameter(measurables, "measurables");
                        List<? extends Measurable> $this$first$iv = measurables;
                        for (Object element$iv : $this$first$iv) {
                            Measurable it = (Measurable) element$iv;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "Spacer")) {
                                Measurable spacer = (Measurable) element$iv;
                                final Placeable spacerPlaceable = spacer.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : MathKt.roundToInt(MeasurePolicy.mo327toPx0680j_4(TimePickerTokens.INSTANCE.m2499getPeriodSelectorOutlineWidthD9Ej5fM()))));
                                List<? extends Measurable> $this$filter$iv = measurables;
                                Collection destination$iv$iv = new ArrayList();
                                for (Object element$iv$iv : $this$filter$iv) {
                                    Measurable it2 = (Measurable) element$iv$iv;
                                    if (!Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Spacer")) {
                                        destination$iv$iv.add(element$iv$iv);
                                    }
                                }
                                Iterable $this$map$iv = (List) destination$iv$iv;
                                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                for (Object item$iv$iv : $this$map$iv) {
                                    Measurable item = (Measurable) item$iv$iv;
                                    destination$iv$iv2.add(item.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : Constraints.m5217getMaxHeightimpl(constraints) / 2)));
                                }
                                final List items = (List) destination$iv$iv2;
                                return MeasureScope.CC.layout$default(MeasurePolicy, Constraints.m5218getMaxWidthimpl(constraints), Constraints.m5217getMaxHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1$measure$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                        invoke2(placementScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Placeable.PlacementScope layout) {
                                        Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                        Placeable.PlacementScope.place$default(layout, items.get(0), 0, 0, 0.0f, 4, null);
                                        Placeable.PlacementScope.place$default(layout, items.get(1), 0, items.get(0).getHeight(), 0.0f, 4, null);
                                        Placeable.PlacementScope.place$default(layout, spacerPlaceable, 0, items.get(0).getHeight() - (spacerPlaceable.getHeight() / 2), 0.0f, 4, null);
                                    }
                                }, 4, null);
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            MeasurePolicy measurePolicy = (MeasurePolicy) value$iv$iv;
            Shape shape = ShapesKt.toShape(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(shape, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape2 = (CornerBasedShape) shape;
            PeriodToggleImpl(modifier, state, colors, measurePolicy, ShapesKt.top(shape2), ShapesKt.bottom(shape2), $composer2, ($dirty2 & 14) | 3072 | ($dirty2 & 112) | ($dirty2 & 896));
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.VerticalPeriodToggle.1
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
                TimePickerKt.VerticalPeriodToggle(modifier, state, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PeriodToggleImpl(final Modifier modifier, final TimePickerState state, final TimePickerColors colors, final MeasurePolicy measurePolicy, final Shape startShape, final Shape endShape, Composer $composer, final int $changed) {
        Object value$iv$iv;
        Composer $composer2 = $composer.startRestartGroup(1374241901);
        ComposerKt.sourceInformation($composer2, "C(PeriodToggleImpl)P(3,5!1,2,4)977@39398L9,978@39457L41,981@39562L111,979@39503L1257:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(state) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(measurePolicy) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty |= $composer2.changed(startShape) ? 16384 : 8192;
        }
        if ((458752 & $changed) == 0) {
            $dirty |= $composer2.changed(endShape) ? 131072 : 65536;
        }
        int $dirty2 = $dirty;
        if ((374491 & $dirty2) != 74898 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1374241901, $dirty2, -1, "androidx.compose.material3.PeriodToggleImpl (TimePicker.kt:964)");
            }
            BorderStroke borderStroke = BorderStrokeKt.m187BorderStrokecXLIe8U(TimePickerTokens.INSTANCE.m2499getPeriodSelectorOutlineWidthD9Ej5fM(), colors.getPeriodSelectorBorderColor());
            Shape shape = ShapesKt.toShape(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(shape, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape2 = (CornerBasedShape) shape;
            final String contentDescription = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1794getTimePickerPeriodToggleadMyvUU(), $composer2, 6);
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(contentDescription);
            Object value$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv || value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$PeriodToggleImpl$1$1
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
                        SemanticsPropertiesKt.setContainer(semantics, true);
                        SemanticsPropertiesKt.setContentDescription(semantics, contentDescription);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv2);
            }
            $composer2.endReplaceableGroup();
            Modifier modifierBorder = BorderKt.border(SelectableGroupKt.selectableGroup(SemanticsModifierKt.semantics$default(modifier, false, (Function1) value$iv$iv2, 1, null)).then(modifier), borderStroke, shape2);
            int $changed$iv = ($dirty2 >> 3) & 896;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)73@2855L7,74@2910L7,75@2969L7,76@2981L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv = (LayoutDirection) objConsume2;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv = (ViewConfiguration) objConsume3;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifierBorder);
            int $changed$iv$iv = (($changed$iv << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, density$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, layoutDirection$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, viewConfiguration$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i = ($changed$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1654470848, "C993@39989L71,990@39863L303,1003@40399L9,998@40179L244,1009@40575L70,1005@40436L308:TimePicker.kt#uh7d8r");
            boolean z = !state.isAfternoonToggle$material3_release();
            int i2 = ($dirty2 >> 3) & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv2 = $composer2.changed(state);
            Object value$iv$iv3 = $composer2.rememberedValue();
            if (invalid$iv$iv2 || value$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.TimePickerKt$PeriodToggleImpl$2$1$1
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
                        state.setAfternoonToggle$material3_release(false);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv3);
            }
            $composer2.endReplaceableGroup();
            ToggleItem(z, startShape, (Function0) value$iv$iv3, colors, ComposableSingletons$TimePickerKt.INSTANCE.m1461getLambda1$material3_release(), $composer2, (($dirty2 >> 9) & 112) | 24576 | (($dirty2 << 3) & 7168));
            SpacerKt.Spacer(BackgroundKt.m160backgroundbw27NRU$default(SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(LayoutIdKt.layoutId(Modifier.INSTANCE, "Spacer"), SeparatorZIndex), 0.0f, 1, null), ColorSchemeKt.toColor(TimePickerTokens.INSTANCE.getPeriodSelectorOutlineColor(), $composer2, 6), null, 2, null), $composer2, 0);
            boolean zIsAfternoonToggle$material3_release = state.isAfternoonToggle$material3_release();
            int i3 = ($dirty2 >> 3) & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv3 = $composer2.changed(state);
            Object it$iv$iv = $composer2.rememberedValue();
            if (invalid$iv$iv3 || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.TimePickerKt$PeriodToggleImpl$2$2$1
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
                        state.setAfternoonToggle$material3_release(true);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            ToggleItem(zIsAfternoonToggle$material3_release, endShape, (Function0) value$iv$iv, colors, ComposableSingletons$TimePickerKt.INSTANCE.m1462getLambda2$material3_release(), $composer2, (($dirty2 >> 12) & 112) | 24576 | (($dirty2 << 3) & 7168));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.PeriodToggleImpl.3
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
                TimePickerKt.PeriodToggleImpl(modifier, state, colors, measurePolicy, startShape, endShape, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ToggleItem(final boolean checked, final Shape shape, final Function0<Unit> function0, final TimePickerColors colors, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Object value$iv$iv;
        Composer $composer2 = $composer.startRestartGroup(-1937408098);
        ComposerKt.sourceInformation($composer2, "C(ToggleItem)P(!1,4,3)1033@41221L22,1038@41400L112,1029@41086L432:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(checked) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(shape) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if ((46811 & $dirty2) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1937408098, $dirty2, -1, "androidx.compose.material3.ToggleItem (TimePicker.kt:1019)");
            }
            long contentColor = colors.m1893periodSelectorContentColorvNxB06k$material3_release(checked);
            long containerColor = colors.m1892periodSelectorContainerColorvNxB06k$material3_release(checked);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(Modifier.INSTANCE, checked ? 0.0f : 1.0f), 0.0f, 1, null);
            Object key1$iv = Boolean.valueOf(checked);
            int i = $dirty2 & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(key1$iv);
            Object it$iv$iv = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ToggleItem$1$1
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
                        SemanticsPropertiesKt.setSelected(semantics, checked);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            ButtonKt.TextButton(function0, SemanticsModifierKt.semantics$default(modifierFillMaxSize$default, false, (Function1) value$iv$iv, 1, null), false, shape, ButtonDefaults.INSTANCE.m1345textButtonColorsro_MJ88(containerColor, contentColor, 0L, 0L, $composer2, 24576, 12), null, null, PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0)), null, function3, $composer2, (($dirty2 >> 6) & 14) | 12582912 | (($dirty2 << 6) & 7168) | (($dirty2 << 15) & 1879048192), 356);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ToggleItem.2
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

            public final void invoke(Composer composer, int i2) {
                TimePickerKt.ToggleItem(checked, shape, function0, colors, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DisplaySeparator(final Modifier modifier, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(2100674302);
        ComposerKt.sourceInformation($composer2, "C(DisplaySeparator)1048@41655L7,1056@41916L245:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($dirty & 11) == 2 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2100674302, $changed, -1, "androidx.compose.material3.DisplaySeparator (TimePicker.kt:1046)");
            }
            ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localTextStyle);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            TextStyle textStyle = (TextStyle) objConsume;
            TextStyle style = IncludeFontPaddingHelper_androidKt.copyAndSetFontPadding(textStyle.m4782copyCXVQc50((3653631 & 1) != 0 ? textStyle.spanStyle.m4727getColor0d7_KjU() : 0L, (3653631 & 2) != 0 ? textStyle.spanStyle.getFontSize() : 0L, (3653631 & 4) != 0 ? textStyle.spanStyle.getFontWeight() : null, (3653631 & 8) != 0 ? textStyle.spanStyle.getFontStyle() : null, (3653631 & 16) != 0 ? textStyle.spanStyle.getFontSynthesis() : null, (3653631 & 32) != 0 ? textStyle.spanStyle.getFontFamily() : null, (3653631 & 64) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : null, (3653631 & 128) != 0 ? textStyle.spanStyle.getLetterSpacing() : 0L, (3653631 & 256) != 0 ? textStyle.spanStyle.getBaselineShift() : null, (3653631 & 512) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : null, (3653631 & 1024) != 0 ? textStyle.spanStyle.getLocaleList() : null, (3653631 & 2048) != 0 ? textStyle.spanStyle.getBackground() : 0L, (3653631 & 4096) != 0 ? textStyle.spanStyle.getTextDecoration() : null, (3653631 & 8192) != 0 ? textStyle.spanStyle.getShadow() : null, (3653631 & 16384) != 0 ? textStyle.paragraphStyle.getTextAlign() : TextAlign.m5127boximpl(TextAlign.INSTANCE.m5134getCentere0LSkKk()), (3653631 & 32768) != 0 ? textStyle.paragraphStyle.getTextDirection() : null, (3653631 & 65536) != 0 ? textStyle.paragraphStyle.getLineHeight() : 0L, (3653631 & 131072) != 0 ? textStyle.paragraphStyle.getTextIndent() : null, (3653631 & 262144) != 0 ? textStyle.platformStyle : null, (3653631 & 524288) != 0 ? textStyle.paragraphStyle.getLineHeightStyle() : new LineHeightStyle(LineHeightStyle.Alignment.INSTANCE.m5111getCenterPIaL0Z0(), LineHeightStyle.Trim.INSTANCE.m5123getBothEVpEnUU(), null), (3653631 & 1048576) != 0 ? textStyle.paragraphStyle.getLineBreak() : null, (3653631 & 2097152) != 0 ? textStyle.paragraphStyle.getHyphens() : null), false);
            Modifier modifier$iv = SemanticsModifierKt.clearAndSetSemantics(modifier, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt.DisplaySeparator.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver clearAndSetSemantics) {
                    Intrinsics.checkNotNullParameter(clearAndSetSemantics, "$this$clearAndSetSemantics");
                }
            });
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv = (Density) objConsume2;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume3;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer2.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume4;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -617594898, "C1062@42109L9,1060@42035L120:TimePicker.kt#uh7d8r");
            TextKt.m1884Text4IGK_g(":", (Modifier) null, ColorSchemeKt.toColor(TimeInputTokens.INSTANCE.getTimeFieldSeparatorColor(), $composer2, 6), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, style, $composer2, 6, 0, 65530);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.DisplaySeparator.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i3) {
                TimePickerKt.DisplaySeparator(modifier, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: TimeSelector-uXwN82Y, reason: not valid java name */
    public static final void m1901TimeSelectoruXwN82Y(final Modifier modifier, final int value, final TimePickerState state, final int selection, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(21099367);
        ComposerKt.sourceInformation($composer3, "C(TimeSelector)P(1,4,3,2:c#material3.Selection)1078@42456L176,1088@42784L24,1091@42898L124,1104@43327L9,1089@42813L1060:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer3.changed(value) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer3.changed(state) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(selection) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty |= $composer3.changed(colors) ? 16384 : 8192;
        }
        final int $dirty2 = $dirty;
        if ((46811 & $dirty2) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(21099367, $dirty2, -1, "androidx.compose.material3.TimeSelector (TimePicker.kt:1070)");
            }
            boolean selected = Selection.m1707equalsimpl0(state.m1924getSelectionJiIwxys$material3_release(), selection);
            final String selectorContentDescription = Strings_androidKt.m1797getStringNWtq28(Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1711getHourJiIwxys()) ? Strings.INSTANCE.m1786getTimePickerHourSelectionadMyvUU() : Strings.INSTANCE.m1790getTimePickerMinuteSelectionadMyvUU(), $composer3, 0);
            long containerColor = colors.m1894timeSelectorContainerColorvNxB06k$material3_release(selected);
            final long contentColor = colors.m1895timeSelectorContentColorvNxB06k$material3_release(selected);
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object value$iv$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv$iv);
            }
            $composer3.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer3.changed(selectorContentDescription);
            Object value$iv$iv = $composer3.rememberedValue();
            if (invalid$iv$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$1$1
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
                        SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4614getRadioButtono7Vup1c());
                        SemanticsPropertiesKt.setContentDescription(semantics, selectorContentDescription);
                    }
                };
                $composer3.updateRememberedValue(value$iv$iv);
            }
            $composer3.endReplaceableGroup();
            $composer2 = $composer3;
            SurfaceKt.m1807Surfaced85dljk(selected, new Function0<Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    if (!Selection.m1707equalsimpl0(selection, state.m1924getSelectionJiIwxys$material3_release())) {
                        state.m1927setSelectioniHAOin8$material3_release(selection);
                        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(state, null), 3, null);
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$TimeSelector$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: TimePicker.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.TimePickerKt$TimeSelector$2$1", f = "TimePicker.kt", i = {}, l = {1100}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ TimePickerState $state;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(TimePickerState timePickerState, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$state = timePickerState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$state, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                this.label = 1;
                                if (this.$state.animateToCurrent$material3_release(this) == coroutine_suspended) {
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
            }, SemanticsModifierKt.semantics(modifier, true, (Function1) value$iv$iv), false, ShapesKt.toShape(TimePickerTokens.INSTANCE.getTimeSelectorContainerShape(), $composer3, 6), containerColor, 0L, 0.0f, 0.0f, (BorderStroke) null, (MutableInteractionSource) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.composableLambda($composer3, -1338709103, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    Object value$iv$iv2;
                    ComposerKt.sourceInformation($composer4, "C1108@43428L152,1114@43590L277:TimePicker.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1338709103, $changed2, -1, "androidx.compose.material3.TimeSelector.<anonymous> (TimePicker.kt:1106)");
                    }
                    int i = selection;
                    boolean is24hour = state.getIs24hour();
                    int i2 = value;
                    int i3 = $dirty2;
                    final String valueContentDescription = TimePickerKt.m1906numberContentDescriptionYKJpE6Y(i, is24hour, i2, $composer4, ((i3 << 3) & 896) | ((i3 >> 9) & 14));
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                    int i4 = value;
                    long j = contentColor;
                    $composer4.startReplaceableGroup(733328855);
                    ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                    int $changed$iv$iv = (48 << 3) & 112;
                    $composer4.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer4.consume(localDensity);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    Density density$iv$iv = (Density) objConsume;
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume2 = $composer4.consume(localLayoutDirection);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                    ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume3 = $composer4.consume(localViewConfiguration);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                    int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        $composer4.createNode(constructor);
                    } else {
                        $composer4.useNode();
                    }
                    $composer4.disableReusing();
                    Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                    $composer4.enableReusing();
                    function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                    $composer4.startReplaceableGroup(2058660585);
                    int i5 = ($changed$iv$iv$iv >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i6 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, 992575728, "C1116@43697L48,1115@43645L212:TimePicker.kt#uh7d8r");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    $composer4.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                    boolean invalid$iv$iv2 = $composer4.changed(valueContentDescription);
                    Object it$iv$iv = $composer4.rememberedValue();
                    if (invalid$iv$iv2 || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$3$1$1$1
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
                                SemanticsPropertiesKt.setContentDescription(semantics, valueContentDescription);
                            }
                        };
                        $composer4.updateRememberedValue(value$iv$iv2);
                    } else {
                        value$iv$iv2 = it$iv$iv;
                    }
                    $composer4.endReplaceableGroup();
                    TextKt.m1884Text4IGK_g(TimePickerKt.toLocalString(i4, 2), SemanticsModifierKt.semantics$default(companion, false, (Function1) value$iv$iv2, 1, null), j, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131064);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endReplaceableGroup();
                    $composer4.endNode();
                    $composer4.endReplaceableGroup();
                    $composer4.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, 0, 48, 1992);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$4
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
                TimePickerKt.m1901TimeSelectoruXwN82Y(modifier, value, state, selection, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    public static final void ClockFace(final TimePickerState state, final TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(colors, "colors");
        Composer $composer2 = $composer.startRestartGroup(-1525091100);
        ComposerKt.sourceInformation($composer2, "C(ClockFace)P(2,1)1130@44011L1899:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(colors) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 256 : 128;
        }
        final int $dirty2 = $dirty;
        if (($dirty2 & 731) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1525091100, $dirty2, -1, "androidx.compose.material3.ClockFace (TimePicker.kt:1125)");
            }
            CrossfadeKt.Crossfade(state.getValues$material3_release(), SemanticsModifierKt.semantics$default(SizeKt.m534size3ABfNKs(BackgroundKt.m159backgroundbw27NRU(Modifier.INSTANCE, colors.getClockDialColor(), RoundedCornerShapeKt.getCircleShape()), TimePickerTokens.INSTANCE.m2492getClockDialContainerSizeD9Ej5fM()), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.setContainer(semantics, false);
                    SemanticsPropertiesKt.selectableGroup(semantics);
                }
            }, 1, null), AnimationSpecKt.tween$default(350, 0, null, 6, null), (String) null, ComposableLambdaKt.composableLambda($composer2, 1628166511, true, new Function3<List<? extends Integer>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(List<? extends Integer> list, Composer composer, Integer num) {
                    invoke((List<Integer>) list, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(final List<Integer> screen, Composer $composer3, int $changed2) {
                    Intrinsics.checkNotNullParameter(screen, "screen");
                    ComposerKt.sourceInformation($composer3, "C1141@44425L1479:TimePicker.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1628166511, $changed2, -1, "androidx.compose.material3.ClockFace.<anonymous> (TimePicker.kt:1140)");
                    }
                    Modifier modifierDrawSelector = TimePickerKt.drawSelector(SizeKt.m534size3ABfNKs(TimePickerKt.clockDial(Modifier.INSTANCE, state, autoSwitchToMinute), TimePickerTokens.INSTANCE.m2492getClockDialContainerSizeD9Ej5fM()), state, colors);
                    float f = TimePickerKt.OuterCircleSizeRadius;
                    final TimePickerColors timePickerColors = colors;
                    final TimePickerState timePickerState = state;
                    final boolean z = autoSwitchToMinute;
                    final int i = $dirty2;
                    TimePickerKt.m1898CircularLayoutuFdPcIQ(modifierDrawSelector, f, ComposableLambdaKt.composableLambda($composer3, -1385633737, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed3) {
                            ComposerKt.sourceInformation($composer4, "C1148@44687L1207:TimePicker.kt#uh7d8r");
                            if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1385633737, $changed3, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous> (TimePicker.kt:1147)");
                                }
                                ProvidedValue[] providedValueArr = {ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(timePickerColors.m1887clockDialContentColorvNxB06k$material3_release(false)))};
                                final List<Integer> list = screen;
                                final TimePickerState timePickerState2 = timePickerState;
                                final boolean z2 = z;
                                final int i2 = i;
                                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer4, -2018362505, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.2.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer5, int $changed4) {
                                        int outerValue;
                                        ComposerKt.sourceInformation($composer5, "C1161@45270L592:TimePicker.kt#uh7d8r");
                                        if (($changed4 & 11) != 2 || !$composer5.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-2018362505, $changed4, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1150)");
                                            }
                                            $composer5.startReplaceableGroup(-504302349);
                                            ComposerKt.sourceInformation($composer5, "*1157@45091L64");
                                            int size = list.size();
                                            TimePickerState timePickerState3 = timePickerState2;
                                            List<Integer> list2 = list;
                                            boolean z3 = z2;
                                            int i3 = i2;
                                            for (int i4 = 0; i4 < size; i4++) {
                                                int it = i4;
                                                if (!timePickerState3.getIs24hour() || Selection.m1707equalsimpl0(timePickerState3.m1924getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1712getMinuteJiIwxys())) {
                                                    outerValue = list2.get(it).intValue();
                                                } else {
                                                    outerValue = list2.get(it).intValue() % 12;
                                                }
                                                TimePickerKt.ClockText(timePickerState3, outerValue, z3, $composer5, (i3 & 14) | (i3 & 896));
                                            }
                                            $composer5.endReplaceableGroup();
                                            if (Selection.m1707equalsimpl0(timePickerState2.m1924getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1711getHourJiIwxys()) && timePickerState2.getIs24hour()) {
                                                Modifier modifierM159backgroundbw27NRU = BackgroundKt.m159backgroundbw27NRU(SizeKt.m534size3ABfNKs(LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutId.InnerCircle), TimePickerTokens.INSTANCE.m2492getClockDialContainerSizeD9Ej5fM()), Color.INSTANCE.m3006getTransparent0d7_KjU(), RoundedCornerShapeKt.getCircleShape());
                                                float f2 = TimePickerKt.InnerCircleRadius;
                                                final TimePickerState timePickerState4 = timePickerState2;
                                                final boolean z4 = z2;
                                                final int i5 = i2;
                                                TimePickerKt.m1898CircularLayoutuFdPcIQ(modifierM159backgroundbw27NRU, f2, ComposableLambdaKt.composableLambda($composer5, -448649404, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.2.1.1.2
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                        invoke(composer, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(Composer $composer6, int $changed5) {
                                                        ComposerKt.sourceInformation($composer6, "C*1170@45750L64:TimePicker.kt#uh7d8r");
                                                        if (($changed5 & 11) != 2 || !$composer6.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(-448649404, $changed5, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1167)");
                                                            }
                                                            int size2 = TimePickerKt.ExtraHours.size();
                                                            TimePickerState timePickerState5 = timePickerState4;
                                                            boolean z5 = z4;
                                                            int i6 = i5;
                                                            for (int i7 = 0; i7 < size2; i7++) {
                                                                int it2 = i7;
                                                                int innerValue = ((Number) TimePickerKt.ExtraHours.get(it2)).intValue();
                                                                TimePickerKt.ClockText(timePickerState5, innerValue, z5, $composer6, (i6 & 14) | (i6 & 896));
                                                            }
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventEnd();
                                                                return;
                                                            }
                                                            return;
                                                        }
                                                        $composer6.skipToGroupEnd();
                                                    }
                                                }), $composer5, 432, 0);
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer5.skipToGroupEnd();
                                    }
                                }), $composer4, 56);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    }), $composer3, 432, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, 24584, 8);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.3
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
                TimePickerKt.ClockFace(state, colors, autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier drawSelector(Modifier $this$drawSelector, final TimePickerState state, final TimePickerColors colors) {
        return DrawModifierKt.drawWithContent($this$drawSelector, new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt.drawSelector.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope drawWithContent) {
                Intrinsics.checkNotNullParameter(drawWithContent, "$this$drawWithContent");
                long selectorOffsetPx = OffsetKt.Offset(drawWithContent.mo327toPx0680j_4(DpOffset.m5335getXD9Ej5fM(state.m1925getSelectorPosRKDOV3M$material3_release())), drawWithContent.mo327toPx0680j_4(DpOffset.m5337getYD9Ej5fM(state.m1925getSelectorPosRKDOV3M$material3_release())));
                float f = 2;
                float selectorRadius = drawWithContent.mo327toPx0680j_4(TimePickerTokens.INSTANCE.m2494getClockDialSelectorHandleContainerSizeD9Ej5fM()) / f;
                long selectorColor = colors.getSelectorColor();
                DrawScope.CC.m3509drawCircleVaOC9Bg$default(drawWithContent, Color.INSTANCE.m2997getBlack0d7_KjU(), selectorRadius, selectorOffsetPx, 0.0f, null, null, BlendMode.INSTANCE.m2888getClear0nO6VwU(), 56, null);
                drawWithContent.drawContent();
                DrawScope.CC.m3509drawCircleVaOC9Bg$default(drawWithContent, selectorColor, selectorRadius, selectorOffsetPx, 0.0f, null, null, BlendMode.INSTANCE.m2916getXor0nO6VwU(), 56, null);
                float strokeWidth = drawWithContent.mo327toPx0680j_4(TimePickerTokens.INSTANCE.m2495getClockDialSelectorTrackContainerWidthD9Ej5fM());
                long lineLength = Offset.m2735minusMKHz9U(selectorOffsetPx, OffsetKt.Offset(((float) Math.cos(state.getCurrentAngle$material3_release().getValue().floatValue())) * selectorRadius, ((float) Math.sin(state.getCurrentAngle$material3_release().getValue().floatValue())) * selectorRadius));
                DrawScope.CC.m3514drawLineNGM6Ib0$default(drawWithContent, selectorColor, androidx.compose.ui.geometry.SizeKt.m2810getCenteruvyYCjk(drawWithContent.mo3442getSizeNHjbRc()), lineLength, strokeWidth, 0, null, 0.0f, null, BlendMode.INSTANCE.m2915getSrcOver0nO6VwU(), 240, null);
                DrawScope.CC.m3509drawCircleVaOC9Bg$default(drawWithContent, selectorColor, drawWithContent.mo327toPx0680j_4(TimePickerTokens.INSTANCE.m2493getClockDialSelectorCenterContainerSizeD9Ej5fM()) / f, androidx.compose.ui.geometry.SizeKt.m2810getCenteruvyYCjk(drawWithContent.mo3442getSizeNHjbRc()), 0.0f, null, null, 0, 120, null);
                DrawScope.CC.m3509drawCircleVaOC9Bg$default(drawWithContent, colors.m1887clockDialContentColorvNxB06k$material3_release(true), selectorRadius, selectorOffsetPx, 0.0f, null, null, BlendMode.INSTANCE.m2898getDstOver0nO6VwU(), 56, null);
            }
        });
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$clockDial$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TimePicker.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    static final class C04672 extends Lambda implements Function3<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ boolean $autoSwitchToMinute;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04672(TimePickerState timePickerState, boolean z) {
            super(3);
            this.$state = timePickerState;
            this.$autoSwitchToMinute = z;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
            return invoke(modifier, composer, num.intValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$lambda$1(MutableState<Float> mutableState) {
            MutableState<Float> $this$getValue$iv = mutableState;
            return $this$getValue$iv.getValue().floatValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$2(MutableState<Float> mutableState, float value) {
            mutableState.setValue(Float.valueOf(value));
        }

        public final Modifier invoke(Modifier composed, Composer $composer, int $changed) {
            Object value$iv$iv;
            Object value$iv$iv2;
            Object value$iv$iv3;
            Object value$iv$iv$iv;
            Object value$iv$iv4;
            Intrinsics.checkNotNullParameter(composed, "$this$composed");
            $composer.startReplaceableGroup(-1645090088);
            ComposerKt.sourceInformation($composer, "C1245@47844L31,1246@47895L31,1247@47945L43,1248@48005L24,*1249@48066L7,1252@48143L28:TimePicker.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1645090088, $changed, -1, "androidx.compose.material3.clockDial.<anonymous> (TimePicker.kt:1244)");
            }
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(0.0f), null, 2, null);
                $composer.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer.endReplaceableGroup();
            MutableState offsetX$delegate = (MutableState) value$iv$iv;
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv2 = $composer.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(0.0f), null, 2, null);
                $composer.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer.endReplaceableGroup();
            MutableState offsetY$delegate = (MutableState) value$iv$iv2;
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv3 = $composer.rememberedValue();
            if (it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntOffset.m5383boximpl(IntOffset.INSTANCE.m5402getZeronOccac()), null, 2, null);
                $composer.updateRememberedValue(value$iv$iv3);
            } else {
                value$iv$iv3 = it$iv$iv3;
            }
            $composer.endReplaceableGroup();
            MutableState center$delegate = (MutableState) value$iv$iv3;
            $composer.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
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
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Density $this$invoke_u24lambda_u248 = (Density) objConsume;
            float maxDist = $this$invoke_u24lambda_u248.mo327toPx0680j_4(TimePickerKt.MaxDistance);
            Modifier.Companion companion = Modifier.INSTANCE;
            Object key1$iv = this.$state;
            final TimePickerState timePickerState = this.$state;
            $composer.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer.changed(key1$iv);
            Object it$iv$iv4 = $composer.rememberedValue();
            if (invalid$iv$iv || it$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv4 = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.material3.TimePickerKt$clockDial$2$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                        m1910invokeozmzZPI(intSize.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                    public final void m1910invokeozmzZPI(long it) {
                        timePickerState.m1926setCentergyyYBs$material3_release(IntSizeKt.m5440getCenterozmzZPI(it));
                    }
                };
                $composer.updateRememberedValue(value$iv$iv4);
            } else {
                value$iv$iv4 = it$iv$iv4;
            }
            $composer.endReplaceableGroup();
            Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(SuspendingPointerInputFilterKt.pointerInput(OnRemeasuredModifierKt.onSizeChanged(companion, (Function1) value$iv$iv4), new Object[]{this.$state, IntOffset.m5383boximpl(invoke$lambda$7(center$delegate)), Float.valueOf(maxDist)}, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) new C01012(offsetX$delegate, offsetY$delegate, scope, this.$state, maxDist, this.$autoSwitchToMinute, null)), new Object[]{this.$state, IntOffset.m5383boximpl(invoke$lambda$7(center$delegate)), Float.valueOf(maxDist)}, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) new AnonymousClass3(scope, this.$state, this.$autoSwitchToMinute, maxDist, offsetX$delegate, offsetY$delegate, null));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceableGroup();
            return modifierPointerInput;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$lambda$4(MutableState<Float> mutableState) {
            MutableState<Float> $this$getValue$iv = mutableState;
            return $this$getValue$iv.getValue().floatValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$5(MutableState<Float> mutableState, float value) {
            mutableState.setValue(Float.valueOf(value));
        }

        private static final long invoke$lambda$7(MutableState<IntOffset> mutableState) {
            MutableState<IntOffset> $this$getValue$iv = mutableState;
            return $this$getValue$iv.getValue().getPackedValue();
        }

        /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$clockDial$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: TimePicker.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.material3.TimePickerKt$clockDial$2$2", f = "TimePicker.kt", i = {}, l = {1255}, m = "invokeSuspend", n = {}, s = {})
        static final class C01012 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $autoSwitchToMinute;
            final /* synthetic */ float $maxDist;
            final /* synthetic */ MutableState<Float> $offsetX$delegate;
            final /* synthetic */ MutableState<Float> $offsetY$delegate;
            final /* synthetic */ CoroutineScope $scope;
            final /* synthetic */ TimePickerState $state;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01012(MutableState<Float> mutableState, MutableState<Float> mutableState2, CoroutineScope coroutineScope, TimePickerState timePickerState, float f, boolean z, Continuation<? super C01012> continuation) {
                super(2, continuation);
                this.$offsetX$delegate = mutableState;
                this.$offsetY$delegate = mutableState2;
                this.$scope = coroutineScope;
                this.$state = timePickerState;
                this.$maxDist = f;
                this.$autoSwitchToMinute = z;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C01012 c01012 = new C01012(this.$offsetX$delegate, this.$offsetY$delegate, this.$scope, this.$state, this.$maxDist, this.$autoSwitchToMinute, continuation);
                c01012.L$0 = obj;
                return c01012;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                return ((C01012) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$clockDial$2$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: TimePicker.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.TimePickerKt$clockDial$2$2$1", f = "TimePicker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            static final class AnonymousClass1 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
                final /* synthetic */ MutableState<Float> $offsetX$delegate;
                final /* synthetic */ MutableState<Float> $offsetY$delegate;
                /* synthetic */ long J$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(MutableState<Float> mutableState, MutableState<Float> mutableState2, Continuation<? super AnonymousClass1> continuation) {
                    super(3, continuation);
                    this.$offsetX$delegate = mutableState;
                    this.$offsetY$delegate = mutableState2;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
                    return m1911invoked4ec7I(pressGestureScope, offset.getPackedValue(), continuation);
                }

                /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
                public final Object m1911invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$offsetX$delegate, this.$offsetY$delegate, continuation);
                    anonymousClass1.J$0 = j;
                    return anonymousClass1.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            long it = this.J$0;
                            C04672.invoke$lambda$2(this.$offsetX$delegate, Offset.m2731getXimpl(it));
                            C04672.invoke$lambda$5(this.$offsetY$delegate, Offset.m2732getYimpl(it));
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
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$offsetX$delegate, this.$offsetY$delegate, null);
                        final CoroutineScope coroutineScope = this.$scope;
                        final TimePickerState timePickerState = this.$state;
                        final float f = this.$maxDist;
                        final boolean z = this.$autoSwitchToMinute;
                        Function1<Offset, Unit> function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.material3.TimePickerKt.clockDial.2.2.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                                m1912invokek4lQ0M(offset.getPackedValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                            public final void m1912invokek4lQ0M(long it) {
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(timePickerState, it, f, z, null), 3, null);
                            }

                            /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$clockDial$2$2$2$1, reason: invalid class name */
                            /* JADX INFO: compiled from: TimePicker.kt */
                            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material3.TimePickerKt$clockDial$2$2$2$1", f = "TimePicker.kt", i = {}, l = {1261}, m = "invokeSuspend", n = {}, s = {})
                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ boolean $autoSwitchToMinute;
                                final /* synthetic */ long $it;
                                final /* synthetic */ float $maxDist;
                                final /* synthetic */ TimePickerState $state;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass1(TimePickerState timePickerState, long j, float f, boolean z, Continuation<? super AnonymousClass1> continuation) {
                                    super(2, continuation);
                                    this.$state = timePickerState;
                                    this.$it = j;
                                    this.$maxDist = f;
                                    this.$autoSwitchToMinute = z;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new AnonymousClass1(this.$state, this.$it, this.$maxDist, this.$autoSwitchToMinute, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            this.label = 1;
                                            if (this.$state.onTap$material3_release(Offset.m2731getXimpl(this.$it), Offset.m2732getYimpl(this.$it), this.$maxDist, this.$autoSwitchToMinute, this) == coroutine_suspended) {
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
                        };
                        this.label = 1;
                        if (TapGestureDetectorKt.detectTapGestures($this$pointerInput, (3 & 1) != 0 ? null : null, (3 & 2) != 0 ? null : null, (3 & 4) != 0 ? TapGestureDetectorKt.NoPressGesture : anonymousClass1, (3 & 8) != 0 ? null : function1, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$clockDial$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: TimePicker.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.material3.TimePickerKt$clockDial$2$3", f = "TimePicker.kt", i = {}, l = {1266}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass3 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $autoSwitchToMinute;
            final /* synthetic */ float $maxDist;
            final /* synthetic */ MutableState<Float> $offsetX$delegate;
            final /* synthetic */ MutableState<Float> $offsetY$delegate;
            final /* synthetic */ CoroutineScope $scope;
            final /* synthetic */ TimePickerState $state;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(CoroutineScope coroutineScope, TimePickerState timePickerState, boolean z, float f, MutableState<Float> mutableState, MutableState<Float> mutableState2, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.$scope = coroutineScope;
                this.$state = timePickerState;
                this.$autoSwitchToMinute = z;
                this.$maxDist = f;
                this.$offsetX$delegate = mutableState;
                this.$offsetY$delegate = mutableState2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$scope, this.$state, this.$autoSwitchToMinute, this.$maxDist, this.$offsetX$delegate, this.$offsetY$delegate, continuation);
                anonymousClass3.L$0 = obj;
                return anonymousClass3;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                        final CoroutineScope coroutineScope = this.$scope;
                        final TimePickerState timePickerState = this.$state;
                        final boolean z = this.$autoSwitchToMinute;
                        Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.compose.material3.TimePickerKt.clockDial.2.3.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C01031(timePickerState, z, null), 3, null);
                            }

                            /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$clockDial$2$3$1$1, reason: invalid class name and collision with other inner class name */
                            /* JADX INFO: compiled from: TimePicker.kt */
                            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material3.TimePickerKt$clockDial$2$3$1$1", f = "TimePicker.kt", i = {}, l = {1270, 1272}, m = "invokeSuspend", n = {}, s = {})
                            static final class C01031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ boolean $autoSwitchToMinute;
                                final /* synthetic */ TimePickerState $state;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                C01031(TimePickerState timePickerState, boolean z, Continuation<? super C01031> continuation) {
                                    super(2, continuation);
                                    this.$state = timePickerState;
                                    this.$autoSwitchToMinute = z;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new C01031(this.$state, this.$autoSwitchToMinute, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((C01031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    C01031 c01031;
                                    C01031 c01032;
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            if (Selection.m1707equalsimpl0(this.$state.m1924getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1711getHourJiIwxys()) && this.$autoSwitchToMinute) {
                                                this.$state.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                                                this.label = 1;
                                                if (this.$state.animateToCurrent$material3_release(this) != coroutine_suspended) {
                                                    c01032 = this;
                                                } else {
                                                    return coroutine_suspended;
                                                }
                                            } else if (Selection.m1707equalsimpl0(this.$state.m1924getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1712getMinuteJiIwxys())) {
                                                this.label = 2;
                                                if (this.$state.settle(this) != coroutine_suspended) {
                                                    c01031 = this;
                                                } else {
                                                    return coroutine_suspended;
                                                }
                                            }
                                            return Unit.INSTANCE;
                                        case 1:
                                            c01032 = this;
                                            ResultKt.throwOnFailure($result);
                                            return Unit.INSTANCE;
                                        case 2:
                                            c01031 = this;
                                            ResultKt.throwOnFailure($result);
                                            return Unit.INSTANCE;
                                        default:
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                }
                            }
                        };
                        final CoroutineScope coroutineScope2 = this.$scope;
                        final TimePickerState timePickerState2 = this.$state;
                        final float f = this.$maxDist;
                        final MutableState<Float> mutableState = this.$offsetX$delegate;
                        final MutableState<Float> mutableState2 = this.$offsetY$delegate;
                        this.label = 1;
                        if (DragGestureDetectorKt.detectDragGestures($this$pointerInput, (5 & 1) != 0 ? DragGestureDetectorKt.AnonymousClass2.INSTANCE : null, (5 & 2) != 0 ? DragGestureDetectorKt.AnonymousClass3.INSTANCE : function0, (5 & 4) != 0 ? DragGestureDetectorKt.AnonymousClass4.INSTANCE : null, new Function2<PointerInputChange, Offset, Unit>() { // from class: androidx.compose.material3.TimePickerKt.clockDial.2.3.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Offset offset) {
                                m1913invokeUv8p0NA(pointerInputChange, offset.getPackedValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                            public final void m1913invokeUv8p0NA(PointerInputChange pointerInputChange, long dragAmount) {
                                Intrinsics.checkNotNullParameter(pointerInputChange, "<anonymous parameter 0>");
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(dragAmount, timePickerState2, mutableState, mutableState2, null), 3, null);
                                timePickerState2.moveSelector$material3_release(C04672.invoke$lambda$1(mutableState), C04672.invoke$lambda$4(mutableState2), f);
                            }

                            /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$clockDial$2$3$2$1, reason: invalid class name */
                            /* JADX INFO: compiled from: TimePicker.kt */
                            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material3.TimePickerKt$clockDial$2$3$2$1", f = "TimePicker.kt", i = {}, l = {1279}, m = "invokeSuspend", n = {}, s = {})
                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ long $dragAmount;
                                final /* synthetic */ MutableState<Float> $offsetX$delegate;
                                final /* synthetic */ MutableState<Float> $offsetY$delegate;
                                final /* synthetic */ TimePickerState $state;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass1(long j, TimePickerState timePickerState, MutableState<Float> mutableState, MutableState<Float> mutableState2, Continuation<? super AnonymousClass1> continuation) {
                                    super(2, continuation);
                                    this.$dragAmount = j;
                                    this.$state = timePickerState;
                                    this.$offsetX$delegate = mutableState;
                                    this.$offsetY$delegate = mutableState2;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new AnonymousClass1(this.$dragAmount, this.$state, this.$offsetX$delegate, this.$offsetY$delegate, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            MutableState<Float> mutableState = this.$offsetX$delegate;
                                            C04672.invoke$lambda$2(mutableState, C04672.invoke$lambda$1(mutableState) + Offset.m2731getXimpl(this.$dragAmount));
                                            MutableState<Float> mutableState2 = this.$offsetY$delegate;
                                            C04672.invoke$lambda$5(mutableState2, C04672.invoke$lambda$4(mutableState2) + Offset.m2732getYimpl(this.$dragAmount));
                                            this.label = 1;
                                            if (TimePickerState.update$material3_release$default(this.$state, TimePickerKt.atan(C04672.invoke$lambda$4(this.$offsetY$delegate) - IntOffset.m5393getYimpl(this.$state.m1923getCenternOccac$material3_release()), C04672.invoke$lambda$1(this.$offsetX$delegate) - IntOffset.m5392getXimpl(this.$state.m1923getCenternOccac$material3_release())), false, this, 2, null) == coroutine_suspended) {
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier clockDial(Modifier $this$clockDial, final TimePickerState state, boolean autoSwitchToMinute) {
        return ComposedModifierKt.composed($this$clockDial, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TimePickerKt$clockDial$$inlined$debugInspectorInfo$1
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
                $this$null.setName("clockDial");
                $this$null.getProperties().set("state", state);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new C04672(state, autoSwitchToMinute));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockText(final TimePickerState state, final int value, final boolean autoSwitchToMinute, Composer $composer, final int $changed) {
        Object value$iv$iv;
        Object value$iv$iv$iv;
        Object value$iv$iv2;
        Composer $composer2 = $composer.startRestartGroup(-1317232110);
        ComposerKt.sourceInformation($composer2, "C(ClockText)P(1,2)*1287@49593L10,1291@49736L7,1292@49786L40,1293@49843L24,1295@49905L142,1313@50500L39,1308@50299L758:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(value) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 731) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1317232110, $dirty2, -1, "androidx.compose.material3.ClockText (TimePicker.kt:1286)");
            }
            TextStyle it = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer2, 6), TimePickerTokens.INSTANCE.getClockDialLabelTextFont());
            TextStyle style = IncludeFontPaddingHelper_androidKt.copyAndSetFontPadding(it, false);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$ClockText_u24lambda_u2427 = (Density) objConsume;
            final float maxDist = $this$ClockText_u24lambda_u2427.mo327toPx0680j_4(MaxDistance);
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m2720boximpl(Offset.INSTANCE.m2747getZeroF1C5BW0()), null, 2, null);
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            final MutableState center$delegate = (MutableState) value$iv$iv;
            $composer2.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer2, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2));
                $composer2.updateRememberedValue(value$iv$iv$iv);
            } else {
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer2.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            $composer2.endReplaceableGroup();
            final String contentDescription = m1906numberContentDescriptionYKJpE6Y(state.m1924getSelectionJiIwxys$material3_release(), state.getIs24hour(), value, $composer2, ($dirty2 << 3) & 896);
            String text = toLocalString(value, 1);
            final boolean selected = Selection.m1707equalsimpl0(state.m1924getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1712getMinuteJiIwxys()) ? Intrinsics.areEqual(toLocalString(state.getMinute(), 1), text) : Intrinsics.areEqual(toLocalString(state.getHour(), 1), text);
            Alignment center = Alignment.INSTANCE.getCenter();
            Modifier modifierM534size3ABfNKs = SizeKt.m534size3ABfNKs(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), MinimumInteractiveSize);
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(center$delegate);
            Object value$iv$iv3 = $composer2.rememberedValue();
            if (invalid$iv$iv || value$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv3 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockText$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates it2) {
                        Intrinsics.checkNotNullParameter(it2, "it");
                        TimePickerKt.ClockText$lambda$30(center$delegate, LayoutCoordinatesKt.boundsInParent(it2).m2761getCenterF1C5BW0());
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv3);
            }
            $composer2.endReplaceableGroup();
            Modifier modifierSemantics = SemanticsModifierKt.semantics(FocusableKt.focusable$default(OnGloballyPositionedModifierKt.onGloballyPositioned(modifierM534size3ABfNKs, (Function1) value$iv$iv3), false, null, 3, null), true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockText.2
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
                    final CoroutineScope coroutineScope = scope;
                    final TimePickerState timePickerState = state;
                    final float f = maxDist;
                    final boolean z = autoSwitchToMinute;
                    final MutableState<Offset> mutableState = center$delegate;
                    SemanticsPropertiesKt.onClick$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.TimePickerKt.ClockText.2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C01001(timePickerState, f, z, mutableState, null), 3, null);
                            return true;
                        }

                        /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockText$2$1$1, reason: invalid class name and collision with other inner class name */
                        /* JADX INFO: compiled from: TimePicker.kt */
                        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                        @DebugMetadata(c = "androidx.compose.material3.TimePickerKt$ClockText$2$1$1", f = "TimePicker.kt", i = {}, l = {1318}, m = "invokeSuspend", n = {}, s = {})
                        static final class C01001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ boolean $autoSwitchToMinute;
                            final /* synthetic */ MutableState<Offset> $center$delegate;
                            final /* synthetic */ float $maxDist;
                            final /* synthetic */ TimePickerState $state;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            C01001(TimePickerState timePickerState, float f, boolean z, MutableState<Offset> mutableState, Continuation<? super C01001> continuation) {
                                super(2, continuation);
                                this.$state = timePickerState;
                                this.$maxDist = f;
                                this.$autoSwitchToMinute = z;
                                this.$center$delegate = mutableState;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new C01001(this.$state, this.$maxDist, this.$autoSwitchToMinute, this.$center$delegate, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((C01001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        this.label = 1;
                                        if (this.$state.onTap$material3_release(Offset.m2731getXimpl(TimePickerKt.ClockText$lambda$29(this.$center$delegate)), Offset.m2732getYimpl(TimePickerKt.ClockText$lambda$29(this.$center$delegate)), this.$maxDist, this.$autoSwitchToMinute, this) == coroutine_suspended) {
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
                    }, 1, null);
                    SemanticsPropertiesKt.setSelected(semantics, selected);
                }
            });
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(center, false, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv = (Density) objConsume2;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume3;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer2.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume4;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifierSemantics);
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
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 859623406, "C1324@50912L76,1323@50853L198:TimePicker.kt#uh7d8r");
            Modifier.Companion companion = Modifier.INSTANCE;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv2 = $composer2.changed(contentDescription);
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv2 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockText$3$1$1
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
                    public final void invoke2(SemanticsPropertyReceiver clearAndSetSemantics) {
                        Intrinsics.checkNotNullParameter(clearAndSetSemantics, "$this$clearAndSetSemantics");
                        SemanticsPropertiesKt.setContentDescription(clearAndSetSemantics, contentDescription);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            TextKt.m1884Text4IGK_g(text, SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) value$iv$iv2), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, style, $composer2, 0, 0, 65532);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockText.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i3) {
                TimePickerKt.ClockText(state, value, autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long ClockText$lambda$29(MutableState<Offset> mutableState) {
        MutableState<Offset> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().getPackedValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockText$lambda$30(MutableState<Offset> mutableState, long value) {
        mutableState.setValue(Offset.m2720boximpl(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: timeInputOnChange-gIWD5Rc, reason: not valid java name */
    public static final void m1907timeInputOnChangegIWD5Rc(int selection, TimePickerState state, TextFieldValue value, TextFieldValue prevValue, int max, Function1<? super TextFieldValue, Unit> function1) {
        int newValue;
        TextFieldValue textFieldValueM4986copy3r_uNRQ$default;
        if (Intrinsics.areEqual(value.getText(), prevValue.getText())) {
            function1.invoke(value);
            return;
        }
        if (value.getText().length() == 0) {
            if (Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1711getHourJiIwxys())) {
                state.setHour$material3_release(0);
            } else {
                state.setMinute$material3_release(0);
            }
            function1.invoke(TextFieldValue.m4986copy3r_uNRQ$default(value, "", 0L, (TextRange) null, 6, (Object) null));
            return;
        }
        try {
            if (value.getText().length() == 3 && TextRange.m4768getStartimpl(value.getSelection()) == 1) {
                newValue = CharsKt.digitToInt(value.getText().charAt(0));
            } else {
                newValue = Integer.parseInt(value.getText());
            }
            if (newValue <= max) {
                if (Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1711getHourJiIwxys())) {
                    state.setHour$material3_release(newValue);
                    if (newValue > 1 && !state.getIs24hour()) {
                        state.m1927setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1712getMinuteJiIwxys());
                    }
                } else {
                    state.setMinute$material3_release(newValue);
                }
                if (value.getText().length() <= 2) {
                    textFieldValueM4986copy3r_uNRQ$default = value;
                } else {
                    textFieldValueM4986copy3r_uNRQ$default = TextFieldValue.m4986copy3r_uNRQ$default(value, String.valueOf(value.getText().charAt(0)), 0L, (TextRange) null, 6, (Object) null);
                }
                function1.invoke(textFieldValueM4986copy3r_uNRQ$default);
            }
        } catch (NumberFormatException e) {
        } catch (IllegalArgumentException e2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:100:0x013a  */
    /* JADX WARN: Code duplicated, block: B:103:0x0162  */
    /* JADX WARN: Code duplicated, block: B:104:0x016d  */
    /* JADX WARN: Code duplicated, block: B:107:0x0199  */
    /* JADX WARN: Code duplicated, block: B:108:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:111:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:114:0x02f2  */
    /* JADX WARN: Code duplicated, block: B:115:0x02f6  */
    /* JADX WARN: Code duplicated, block: B:118:0x037b  */
    /* JADX WARN: Code duplicated, block: B:120:0x039d  */
    /* JADX WARN: Code duplicated, block: B:121:0x03a2  */
    /* JADX WARN: Code duplicated, block: B:123:0x03e6  */
    /* JADX WARN: Code duplicated, block: B:126:0x040b  */
    /* JADX WARN: Code duplicated, block: B:127:0x0412  */
    /* JADX WARN: Code duplicated, block: B:130:0x04cb  */
    /* JADX WARN: Code duplicated, block: B:133:0x04d7  */
    /* JADX WARN: Code duplicated, block: B:134:0x04db  */
    /* JADX WARN: Code duplicated, block: B:137:0x0591  */
    /* JADX WARN: Code duplicated, block: B:141:0x05a2 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:145:0x0753  */
    /* JADX WARN: Code duplicated, block: B:146:0x075a  */
    /* JADX WARN: Code duplicated, block: B:149:0x0802  */
    /* JADX WARN: Code duplicated, block: B:153:0x080d  */
    /* JADX WARN: Code duplicated, block: B:156:0x082c  */
    /* JADX WARN: Code duplicated, block: B:160:0x0836  */
    /* JADX WARN: Code duplicated, block: B:162:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x011a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:93:0x011c  */
    /* JADX WARN: Code duplicated, block: B:94:0x0125  */
    /* JADX WARN: Code duplicated, block: B:96:0x0129  */
    /* JADX WARN: Code duplicated, block: B:97:0x0132  */
    /* JADX INFO: renamed from: TimePickerTextField-lxUZ_iY, reason: not valid java name */
    public static final void m1900TimePickerTextFieldlxUZ_iY(final Modifier modifier, final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, final TimePickerState state, final int selection, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, final TimePickerColors colors, Composer $composer, final int $changed, final int i) {
        KeyboardOptions keyboardOptions2;
        KeyboardActions keyboardActions2;
        int i2;
        int $dirty;
        KeyboardOptions keyboardOptions3;
        KeyboardActions keyboardActions3;
        Object it$iv$iv;
        Object value$iv$iv;
        Object it$iv$iv2;
        Object value$iv$iv2;
        FocusRequester focusRequester;
        boolean selected;
        Function0<ComposeUiNode> constructor;
        Composer $composer2;
        boolean selected2;
        int iM1788getTimePickerHourTextFieldadMyvUU;
        Composer $composer3;
        Function0<ComposeUiNode> constructor2;
        boolean invalid$iv$iv;
        Object value$iv$iv3;
        int iM1789getTimePickerMinuteadMyvUU;
        Composer $composer4;
        boolean invalid$iv$iv2;
        Object value$iv$iv4;
        int minute;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer $composer5 = $composer.startRestartGroup(-367523658);
        ComposerKt.sourceInformation($composer5, "C(TimePickerTextField)P(3,7,4,6,5:c#material3.Selection,2,1)1396@52868L39,1397@52933L29,1398@53015L227,1404@53295L3274,1488@56607L103,1488@56575L135:TimePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer5.changed(modifier) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer5.changed(value) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer5.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer5.changed(state) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty2 |= $composer5.changed(selection) ? 16384 : 8192;
        }
        int i3 = i & 32;
        if (i3 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            keyboardOptions2 = keyboardOptions;
        } else if ((458752 & $changed) == 0) {
            keyboardOptions2 = keyboardOptions;
            $dirty2 |= $composer5.changed(keyboardOptions2) ? 131072 : 65536;
        } else {
            keyboardOptions2 = keyboardOptions;
        }
        int i4 = i & 64;
        if (i4 != 0) {
            $dirty2 |= 1572864;
            keyboardActions2 = keyboardActions;
        } else if (($changed & 3670016) == 0) {
            keyboardActions2 = keyboardActions;
            $dirty2 |= $composer5.changed(keyboardActions2) ? 1048576 : 524288;
        } else {
            keyboardActions2 = keyboardActions;
        }
        if ((i & 128) == 0) {
            if ((29360128 & $changed) == 0) {
                i2 = $composer5.changed(colors) ? 8388608 : 4194304;
            }
            $dirty = $dirty2;
            if ((23967451 & $dirty) == 4793490 || !$composer5.getSkipping()) {
                if (i3 != 0) {
                    keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
                } else {
                    keyboardOptions3 = keyboardOptions2;
                }
                if (i4 != 0) {
                    keyboardActions3 = KeyboardActions.INSTANCE.getDefault();
                } else {
                    keyboardActions3 = keyboardActions2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-367523658, $dirty, -1, "androidx.compose.material3.TimePickerTextField (TimePicker.kt:1386)");
                }
                $composer5.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer5, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv = $composer5.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer5.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                $composer5.endReplaceableGroup();
                final MutableInteractionSource interactionSource = (MutableInteractionSource) value$iv$iv;
                $composer5.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer5, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv2 = $composer5.rememberedValue();
                if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = new FocusRequester();
                    $composer5.updateRememberedValue(value$iv$iv2);
                } else {
                    value$iv$iv2 = it$iv$iv2;
                }
                $composer5.endReplaceableGroup();
                focusRequester = (FocusRequester) value$iv$iv2;
                final TextFieldColors textFieldColors = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(colors.m1895timeSelectorContentColorvNxB06k$material3_release(true), 0L, 0L, 0L, colors.m1894timeSelectorContainerColorvNxB06k$material3_release(true), colors.m1894timeSelectorContainerColorvNxB06k$material3_release(true), 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer5, 0, 0, 0, 0, 3072, 2147483598, 4095);
                selected = Selection.m1707equalsimpl0(selection, state.m1924getSelectionJiIwxys$material3_release());
                int $changed$iv = $dirty & 14;
                $composer5.startReplaceableGroup(-483455358);
                ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                int $changed$iv$iv = ($changed$iv << 3) & 112;
                $composer5.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer5.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                Density density$iv$iv = (Density) objConsume;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer5.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer5.consume(localViewConfiguration);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier);
                int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                if (!($composer5.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer5.startReusableNode();
                if ($composer5.getInserting()) {
                    $composer5.createNode(constructor);
                } else {
                    $composer5.useNode();
                }
                $composer5.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer5);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer5.enableReusing();
                function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                $composer5.startReplaceableGroup(2058660585);
                int i5 = ($changed$iv$iv$iv >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer5, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i6 = (($changed$iv >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer5, 896149831, "C1415@53727L202,1423@53939L2043,1474@56136L206,1481@56409L9,1483@56471L10,1470@55992L571:TimePicker.kt#uh7d8r");
                $composer5.startReplaceableGroup(896149831);
                ComposerKt.sourceInformation($composer5, "1406@53362L320");
                if (selected) {
                    $composer2 = $composer5;
                    selected2 = selected;
                } else {
                    Modifier modifierM536sizeVpY3zN4 = SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m2490getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m2489getTimeFieldContainerHeightD9Ej5fM());
                    if (Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1711getHourJiIwxys())) {
                        minute = state.getHourForDisplay$material3_release();
                    } else {
                        minute = state.getMinute();
                    }
                    $composer2 = $composer5;
                    selected2 = selected;
                    m1901TimeSelectoruXwN82Y(modifierM536sizeVpY3zN4, minute, state, selection, colors, $composer2, (($dirty >> 3) & 7168) | (($dirty >> 3) & 896) | 6 | (($dirty >> 9) & 57344));
                }
                $composer2.endReplaceableGroup();
                if (Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1712getMinuteJiIwxys())) {
                    iM1788getTimePickerHourTextFieldadMyvUU = Strings.INSTANCE.m1792getTimePickerMinuteTextFieldadMyvUU();
                } else {
                    iM1788getTimePickerHourTextFieldadMyvUU = Strings.INSTANCE.m1788getTimePickerHourTextFieldadMyvUU();
                }
                $composer3 = $composer2;
                final String contentDescription = Strings_androidKt.m1797getStringNWtq28(iM1788getTimePickerHourTextFieldadMyvUU, $composer3, 0);
                Modifier modifier$iv = visible(Modifier.INSTANCE, selected2);
                $composer3.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv2 = (0 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume4 = $composer3.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density density$iv$iv2 = (Density) objConsume4;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume5 = $composer3.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume6 = $composer3.consume(localViewConfiguration2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv);
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor2);
                } else {
                    $composer3.useNode();
                }
                $composer3.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer3);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer3.enableReusing();
                function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i7 = ($changed$iv$iv$iv2 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i8 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1694936332, "C1430@54275L92,1436@54569L7,1442@54838L11,1443@54902L11,1424@53985L1987:TimePicker.kt#uh7d8r");
                Modifier modifierM536sizeVpY3zN5 = SizeKt.m536sizeVpY3zN4(FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, r26), TimeInputTokens.INSTANCE.m2490getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m2489getTimeFieldContainerHeightD9Ej5fM());
                $composer3.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer3.changed(contentDescription);
                Object it$iv$iv3 = $composer3.rememberedValue();
                if (!invalid$iv$iv || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$1$1
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
                            SemanticsPropertiesKt.setContentDescription(semantics, contentDescription);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv$iv3);
                } else {
                    value$iv$iv3 = it$iv$iv3;
                }
                $composer3.endReplaceableGroup();
                Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierM536sizeVpY3zN5, false, (Function1) value$iv$iv3, 1, null);
                ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume7 = $composer3.consume(localTextStyle);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                BasicTextFieldKt.BasicTextField(value, function1, modifierSemantics$default, true, false, (TextStyle) objConsume7, keyboardOptions3, keyboardActions3, true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource, Brush.Companion.m2929verticalGradient8A3gB4$default(Brush.INSTANCE, new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m2961boximpl(Color.INSTANCE.m3006getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m2961boximpl(Color.INSTANCE.m3006getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m2961boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).m1385getPrimary0d7_KjU())), TuplesKt.to(Float.valueOf(0.9f), Color.m2961boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).m1385getPrimary0d7_KjU())), TuplesKt.to(Float.valueOf(0.9f), Color.m2961boximpl(Color.INSTANCE.m3006getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), Color.m2961boximpl(Color.INSTANCE.m3006getTransparent0d7_KjU()))}, 0.0f, 0.0f, 0, 14, (Object) null), (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.composableLambda($composer3, 2133555260, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function2, Composer composer, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function2, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> it, Composer $composer6, int $changed2) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        ComposerKt.sourceInformation($composer6, "C1448@55094L864:TimePicker.kt#uh7d8r");
                        int $dirty3 = $changed2;
                        if (($changed2 & 14) == 0) {
                            $dirty3 |= $composer6.changedInstance(it) ? 4 : 2;
                        }
                        int $dirty4 = $dirty3;
                        if (($dirty4 & 91) == 18 && $composer6.getSkipping()) {
                            $composer6.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2133555260, $dirty4, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1447)");
                        }
                        OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                        String text = value.getText();
                        VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                        PaddingValues paddingValuesM480PaddingValues0680j_4 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        MutableInteractionSource mutableInteractionSource = interactionSource;
                        TextFieldColors textFieldColors2 = textFieldColors;
                        final MutableInteractionSource mutableInteractionSource2 = interactionSource;
                        final TextFieldColors textFieldColors3 = textFieldColors;
                        outlinedTextFieldDefaults.DecorationBox(text, it, true, true, none, mutableInteractionSource, false, null, null, null, null, null, null, null, textFieldColors2, paddingValuesM480PaddingValues0680j_4, ComposableLambdaKt.composableLambda($composer6, -968963953, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer7, int $changed3) {
                                ComposerKt.sourceInformation($composer7, "C1462@55828L9,1458@55582L336:TimePicker.kt#uh7d8r");
                                if (($changed3 & 11) == 2 && $composer7.getSkipping()) {
                                    $composer7.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-968963953, $changed3, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1457)");
                                }
                                OutlinedTextFieldDefaults.INSTANCE.m1648ContainerBoxnbWgWpA(true, false, mutableInteractionSource2, textFieldColors3, ShapesKt.toShape(TimeInputTokens.INSTANCE.getTimeFieldContainerShape(), $composer7, 6), 0.0f, 0.0f, $composer7, 12583350, 96);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), $composer6, (($dirty4 << 3) & 112) | 224640, 14352384, 16320);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer3, (($dirty >> 3) & 14) | 100666368 | (($dirty >> 3) & 112) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128), 199680, 7696);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(androidx.compose.foundation.layout.OffsetKt.m448offsetVpY3zN4$default(Modifier.INSTANCE, 0.0f, SupportLabelTop, 1, null), new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver clearAndSetSemantics) {
                        Intrinsics.checkNotNullParameter(clearAndSetSemantics, "$this$clearAndSetSemantics");
                    }
                });
                if (Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1711getHourJiIwxys())) {
                    iM1789getTimePickerMinuteadMyvUU = Strings.INSTANCE.m1785getTimePickerHouradMyvUU();
                } else {
                    iM1789getTimePickerMinuteadMyvUU = Strings.INSTANCE.m1789getTimePickerMinuteadMyvUU();
                }
                TextKt.m1884Text4IGK_g(Strings_androidKt.m1797getStringNWtq28(iM1789getTimePickerMinuteadMyvUU, $composer3, 0), modifierClearAndSetSemantics, ColorSchemeKt.toColor(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextColor(), $composer3, 6), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer3, 6), TimeInputTokens.INSTANCE.getTimeFieldSupportingTextFont()), $composer3, 0, 0, 65528);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                $composer5.endReplaceableGroup();
                $composer5.endNode();
                $composer5.endReplaceableGroup();
                $composer5.endReplaceableGroup();
                Selection selectionM1704boximpl = Selection.m1704boximpl(state.m1924getSelectionJiIwxys$material3_release());
                Object key2$iv = Selection.m1704boximpl(selection);
                int i9 = (($dirty >> 9) & 14) | 384 | (($dirty >> 9) & 112);
                $composer4 = $composer5;
                $composer4.startReplaceableGroup(1618982084);
                ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                invalid$iv$iv2 = $composer4.changed(state) | $composer4.changed(key2$iv) | $composer4.changed(focusRequester);
                Object it$iv$iv4 = $composer4.rememberedValue();
                if (!invalid$iv$iv2 || it$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester, null);
                    $composer4.updateRememberedValue(value$iv$iv4);
                } else {
                    value$iv$iv4 = it$iv$iv4;
                }
                $composer4.endReplaceableGroup();
                EffectsKt.LaunchedEffect(selectionM1704boximpl, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv4, $composer4, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer5.skipToGroupEnd();
                keyboardOptions3 = keyboardOptions2;
                keyboardActions3 = keyboardActions2;
                $composer4 = $composer5;
            }
            scopeUpdateScopeEndRestartGroup = $composer4.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final KeyboardOptions keyboardOptions4 = keyboardOptions3;
            final KeyboardActions keyboardActions4 = keyboardActions3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$3
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

                public final void invoke(Composer composer, int i10) {
                    TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifier, value, function1, state, selection, keyboardOptions4, keyboardActions4, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 12582912;
        $dirty2 |= i2;
        $dirty = $dirty2;
        if ((23967451 & $dirty) == 4793490) {
            if (i3 != 0) {
                keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
            } else {
                keyboardOptions3 = keyboardOptions2;
            }
            if (i4 != 0) {
                keyboardActions3 = KeyboardActions.INSTANCE.getDefault();
            } else {
                keyboardActions3 = keyboardActions2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-367523658, $dirty, -1, "androidx.compose.material3.TimePickerTextField (TimePicker.kt:1386)");
            }
            $composer5.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer5, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv = $composer5.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                $composer5.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer5.endReplaceableGroup();
            final MutableInteractionSource interactionSource2 = (MutableInteractionSource) value$iv$iv;
            $composer5.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer5, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv2 = $composer5.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new FocusRequester();
                $composer5.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer5.endReplaceableGroup();
            focusRequester = (FocusRequester) value$iv$iv2;
            final TextFieldColors textFieldColors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(colors.m1895timeSelectorContentColorvNxB06k$material3_release(true), 0L, 0L, 0L, colors.m1894timeSelectorContainerColorvNxB06k$material3_release(true), colors.m1894timeSelectorContainerColorvNxB06k$material3_release(true), 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer5, 0, 0, 0, 0, 3072, 2147483598, 4095);
            selected = Selection.m1707equalsimpl0(selection, state.m1924getSelectionJiIwxys$material3_release());
            int $changed$iv2 = $dirty & 14;
            $composer5.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer5, (($changed$iv2 >> 3) & 14) | (($changed$iv2 >> 3) & 112));
            int $changed$iv$iv3 = ($changed$iv2 << 3) & 112;
            $composer5.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume8 = $composer5.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            Density density$iv$iv3 = (Density) objConsume8;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume9 = $composer5.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            LayoutDirection layoutDirection$iv$iv3 = (LayoutDirection) objConsume9;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration3 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume10 = $composer5.consume(localViewConfiguration3);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ViewConfiguration viewConfiguration$iv$iv3 = (ViewConfiguration) objConsume10;
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf3 = LayoutKt.materializerOf(modifier);
            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
            if (!($composer5.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer5.startReusableNode();
            if ($composer5.getInserting()) {
                $composer5.createNode(constructor);
            } else {
                $composer5.useNode();
            }
            $composer5.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv3 = Updater.m2603constructorimpl($composer5);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, density$iv$iv3, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, layoutDirection$iv$iv3, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, viewConfiguration$iv$iv3, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer5.enableReusing();
            function3MaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
            $composer5.startReplaceableGroup(2058660585);
            int i10 = ($changed$iv$iv$iv3 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer5, 276693704, "C79@4027L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i11 = (($changed$iv2 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer5, 896149831, "C1415@53727L202,1423@53939L2043,1474@56136L206,1481@56409L9,1483@56471L10,1470@55992L571:TimePicker.kt#uh7d8r");
            $composer5.startReplaceableGroup(896149831);
            ComposerKt.sourceInformation($composer5, "1406@53362L320");
            if (selected) {
                Modifier modifierM536sizeVpY3zN6 = SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m2490getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m2489getTimeFieldContainerHeightD9Ej5fM());
                if (Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1711getHourJiIwxys())) {
                    minute = state.getHourForDisplay$material3_release();
                } else {
                    minute = state.getMinute();
                }
                $composer2 = $composer5;
                selected2 = selected;
                m1901TimeSelectoruXwN82Y(modifierM536sizeVpY3zN6, minute, state, selection, colors, $composer2, (($dirty >> 3) & 7168) | (($dirty >> 3) & 896) | 6 | (($dirty >> 9) & 57344));
            } else {
                $composer2 = $composer5;
                selected2 = selected;
            }
            $composer2.endReplaceableGroup();
            if (Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1712getMinuteJiIwxys())) {
                iM1788getTimePickerHourTextFieldadMyvUU = Strings.INSTANCE.m1792getTimePickerMinuteTextFieldadMyvUU();
            } else {
                iM1788getTimePickerHourTextFieldadMyvUU = Strings.INSTANCE.m1788getTimePickerHourTextFieldadMyvUU();
            }
            $composer3 = $composer2;
            final String contentDescription2 = Strings_androidKt.m1797getStringNWtq28(iM1788getTimePickerHourTextFieldadMyvUU, $composer3, 0);
            Modifier modifier$iv2 = visible(Modifier.INSTANCE, selected2);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv4 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv4 = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume11 = $composer3.consume(localDensity4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv4 = (Density) objConsume11;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume12 = $composer3.consume(localLayoutDirection4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv4 = (LayoutDirection) objConsume12;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration4 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume13 = $composer3.consume(localViewConfiguration4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv4 = (ViewConfiguration) objConsume13;
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf4 = LayoutKt.materializerOf(modifier$iv2);
            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor2);
            } else {
                $composer3.useNode();
            }
            $composer3.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv4 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv4, density$iv$iv4, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv4, layoutDirection$iv$iv4, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv4, viewConfiguration$iv$iv4, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf4.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv4 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i12 = ($changed$iv$iv$iv4 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i13 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1694936332, "C1430@54275L92,1436@54569L7,1442@54838L11,1443@54902L11,1424@53985L1987:TimePicker.kt#uh7d8r");
            Modifier modifierM536sizeVpY3zN7 = SizeKt.m536sizeVpY3zN4(FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, r26), TimeInputTokens.INSTANCE.m2490getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m2489getTimeFieldContainerHeightD9Ej5fM());
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(contentDescription2);
            Object it$iv$iv5 = $composer3.rememberedValue();
            if (invalid$iv$iv) {
            }
            value$iv$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$1$1
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
                    SemanticsPropertiesKt.setContentDescription(semantics, contentDescription2);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv3);
            $composer3.endReplaceableGroup();
            Modifier modifierSemantics$default2 = SemanticsModifierKt.semantics$default(modifierM536sizeVpY3zN7, false, (Function1) value$iv$iv3, 1, null);
            ProvidableCompositionLocal<TextStyle> localTextStyle2 = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume14 = $composer3.consume(localTextStyle2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BasicTextFieldKt.BasicTextField(value, function1, modifierSemantics$default2, true, false, (TextStyle) objConsume14, keyboardOptions3, keyboardActions3, true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource2, Brush.Companion.m2929verticalGradient8A3gB4$default(Brush.INSTANCE, new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m2961boximpl(Color.INSTANCE.m3006getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m2961boximpl(Color.INSTANCE.m3006getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m2961boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).m1385getPrimary0d7_KjU())), TuplesKt.to(Float.valueOf(0.9f), Color.m2961boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).m1385getPrimary0d7_KjU())), TuplesKt.to(Float.valueOf(0.9f), Color.m2961boximpl(Color.INSTANCE.m3006getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), Color.m2961boximpl(Color.INSTANCE.m3006getTransparent0d7_KjU()))}, 0.0f, 0.0f, 0, 14, (Object) null), (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.composableLambda($composer3, 2133555260, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function2, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function2, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> it, Composer $composer6, int $changed2) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    ComposerKt.sourceInformation($composer6, "C1448@55094L864:TimePicker.kt#uh7d8r");
                    int $dirty3 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty3 |= $composer6.changedInstance(it) ? 4 : 2;
                    }
                    int $dirty4 = $dirty3;
                    if (($dirty4 & 91) == 18 && $composer6.getSkipping()) {
                        $composer6.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(2133555260, $dirty4, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1447)");
                    }
                    OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                    String text = value.getText();
                    VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                    PaddingValues paddingValuesM480PaddingValues0680j_4 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    MutableInteractionSource mutableInteractionSource = interactionSource2;
                    TextFieldColors textFieldColors3 = textFieldColors2;
                    final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
                    final TextFieldColors textFieldColors4 = textFieldColors2;
                    outlinedTextFieldDefaults.DecorationBox(text, it, true, true, none, mutableInteractionSource, false, null, null, null, null, null, null, null, textFieldColors3, paddingValuesM480PaddingValues0680j_4, ComposableLambdaKt.composableLambda($composer6, -968963953, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer7, int $changed3) {
                            ComposerKt.sourceInformation($composer7, "C1462@55828L9,1458@55582L336:TimePicker.kt#uh7d8r");
                            if (($changed3 & 11) == 2 && $composer7.getSkipping()) {
                                $composer7.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-968963953, $changed3, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1457)");
                            }
                            OutlinedTextFieldDefaults.INSTANCE.m1648ContainerBoxnbWgWpA(true, false, mutableInteractionSource2, textFieldColors4, ShapesKt.toShape(TimeInputTokens.INSTANCE.getTimeFieldContainerShape(), $composer7, 6), 0.0f, 0.0f, $composer7, 12583350, 96);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }), $composer6, (($dirty4 << 3) & 112) | 224640, 14352384, 16320);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer3, (($dirty >> 3) & 14) | 100666368 | (($dirty >> 3) & 112) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128), 199680, 7696);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            Modifier modifierClearAndSetSemantics2 = SemanticsModifierKt.clearAndSetSemantics(androidx.compose.foundation.layout.OffsetKt.m448offsetVpY3zN4$default(Modifier.INSTANCE, 0.0f, SupportLabelTop, 1, null), new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver clearAndSetSemantics) {
                    Intrinsics.checkNotNullParameter(clearAndSetSemantics, "$this$clearAndSetSemantics");
                }
            });
            if (Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1711getHourJiIwxys())) {
                iM1789getTimePickerMinuteadMyvUU = Strings.INSTANCE.m1785getTimePickerHouradMyvUU();
            } else {
                iM1789getTimePickerMinuteadMyvUU = Strings.INSTANCE.m1789getTimePickerMinuteadMyvUU();
            }
            TextKt.m1884Text4IGK_g(Strings_androidKt.m1797getStringNWtq28(iM1789getTimePickerMinuteadMyvUU, $composer3, 0), modifierClearAndSetSemantics2, ColorSchemeKt.toColor(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextColor(), $composer3, 6), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer3, 6), TimeInputTokens.INSTANCE.getTimeFieldSupportingTextFont()), $composer3, 0, 0, 65528);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            $composer5.endReplaceableGroup();
            $composer5.endNode();
            $composer5.endReplaceableGroup();
            $composer5.endReplaceableGroup();
            Selection selectionM1704boximpl2 = Selection.m1704boximpl(state.m1924getSelectionJiIwxys$material3_release());
            Object key2$iv2 = Selection.m1704boximpl(selection);
            int i14 = (($dirty >> 9) & 14) | 384 | (($dirty >> 9) & 112);
            $composer4 = $composer5;
            $composer4.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer4.changed(state) | $composer4.changed(key2$iv2) | $composer4.changed(focusRequester);
            Object it$iv$iv6 = $composer4.rememberedValue();
            if (invalid$iv$iv2) {
                value$iv$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester, null);
                $composer4.updateRememberedValue(value$iv$iv4);
            } else {
                value$iv$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester, null);
                $composer4.updateRememberedValue(value$iv$iv4);
            }
            $composer4.endReplaceableGroup();
            EffectsKt.LaunchedEffect(selectionM1704boximpl2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv4, $composer4, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            if (i3 != 0) {
                keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
            } else {
                keyboardOptions3 = keyboardOptions2;
            }
            if (i4 != 0) {
                keyboardActions3 = KeyboardActions.INSTANCE.getDefault();
            } else {
                keyboardActions3 = keyboardActions2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-367523658, $dirty, -1, "androidx.compose.material3.TimePickerTextField (TimePicker.kt:1386)");
            }
            $composer5.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer5, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv = $composer5.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                $composer5.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer5.endReplaceableGroup();
            final MutableInteractionSource interactionSource3 = (MutableInteractionSource) value$iv$iv;
            $composer5.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer5, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv2 = $composer5.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new FocusRequester();
                $composer5.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer5.endReplaceableGroup();
            focusRequester = (FocusRequester) value$iv$iv2;
            final TextFieldColors textFieldColors3 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(colors.m1895timeSelectorContentColorvNxB06k$material3_release(true), 0L, 0L, 0L, colors.m1894timeSelectorContainerColorvNxB06k$material3_release(true), colors.m1894timeSelectorContainerColorvNxB06k$material3_release(true), 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer5, 0, 0, 0, 0, 3072, 2147483598, 4095);
            selected = Selection.m1707equalsimpl0(selection, state.m1924getSelectionJiIwxys$material3_release());
            int $changed$iv3 = $dirty & 14;
            $composer5.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv3 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv3 = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv5 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv3, horizontalAlignment$iv3, $composer5, (($changed$iv3 >> 3) & 14) | (($changed$iv3 >> 3) & 112));
            int $changed$iv$iv5 = ($changed$iv3 << 3) & 112;
            $composer5.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume15 = $composer5.consume(localDensity5);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            Density density$iv$iv5 = (Density) objConsume15;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection5 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume16 = $composer5.consume(localLayoutDirection5);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            LayoutDirection layoutDirection$iv$iv5 = (LayoutDirection) objConsume16;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration5 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume17 = $composer5.consume(localViewConfiguration5);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ViewConfiguration viewConfiguration$iv$iv5 = (ViewConfiguration) objConsume17;
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf5 = LayoutKt.materializerOf(modifier);
            int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 9) & 7168) | 6;
            if (!($composer5.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer5.startReusableNode();
            if ($composer5.getInserting()) {
                $composer5.createNode(constructor);
            } else {
                $composer5.useNode();
            }
            $composer5.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv5 = Updater.m2603constructorimpl($composer5);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv5, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv5, density$iv$iv5, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv5, layoutDirection$iv$iv5, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv5, viewConfiguration$iv$iv5, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer5.enableReusing();
            function3MaterializerOf5.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv5 >> 3) & 112));
            $composer5.startReplaceableGroup(2058660585);
            int i15 = ($changed$iv$iv$iv5 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer5, 276693704, "C79@4027L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
            int i16 = (($changed$iv3 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer5, 896149831, "C1415@53727L202,1423@53939L2043,1474@56136L206,1481@56409L9,1483@56471L10,1470@55992L571:TimePicker.kt#uh7d8r");
            $composer5.startReplaceableGroup(896149831);
            ComposerKt.sourceInformation($composer5, "1406@53362L320");
            if (selected) {
                Modifier modifierM536sizeVpY3zN8 = SizeKt.m536sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m2490getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m2489getTimeFieldContainerHeightD9Ej5fM());
                if (Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1711getHourJiIwxys())) {
                    minute = state.getHourForDisplay$material3_release();
                } else {
                    minute = state.getMinute();
                }
                $composer2 = $composer5;
                selected2 = selected;
                m1901TimeSelectoruXwN82Y(modifierM536sizeVpY3zN8, minute, state, selection, colors, $composer2, (($dirty >> 3) & 7168) | (($dirty >> 3) & 896) | 6 | (($dirty >> 9) & 57344));
            } else {
                $composer2 = $composer5;
                selected2 = selected;
            }
            $composer2.endReplaceableGroup();
            if (Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1712getMinuteJiIwxys())) {
                iM1788getTimePickerHourTextFieldadMyvUU = Strings.INSTANCE.m1792getTimePickerMinuteTextFieldadMyvUU();
            } else {
                iM1788getTimePickerHourTextFieldadMyvUU = Strings.INSTANCE.m1788getTimePickerHourTextFieldadMyvUU();
            }
            $composer3 = $composer2;
            final String contentDescription3 = Strings_androidKt.m1797getStringNWtq28(iM1788getTimePickerHourTextFieldadMyvUU, $composer3, 0);
            Modifier modifier$iv3 = visible(Modifier.INSTANCE, selected2);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv6 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv3, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv6 = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume18 = $composer3.consume(localDensity6);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv6 = (Density) objConsume18;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection6 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume19 = $composer3.consume(localLayoutDirection6);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv6 = (LayoutDirection) objConsume19;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration6 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume110 = $composer3.consume(localViewConfiguration6);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv6 = (ViewConfiguration) objConsume110;
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf6 = LayoutKt.materializerOf(modifier$iv3);
            int $changed$iv$iv$iv6 = (($changed$iv$iv6 << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor2);
            } else {
                $composer3.useNode();
            }
            $composer3.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv6 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv6, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv6, density$iv$iv6, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv6, layoutDirection$iv$iv6, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv6, viewConfiguration$iv$iv6, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf6.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv6 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i17 = ($changed$iv$iv$iv6 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i18 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1694936332, "C1430@54275L92,1436@54569L7,1442@54838L11,1443@54902L11,1424@53985L1987:TimePicker.kt#uh7d8r");
            Modifier modifierM536sizeVpY3zN9 = SizeKt.m536sizeVpY3zN4(FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, r26), TimeInputTokens.INSTANCE.m2490getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m2489getTimeFieldContainerHeightD9Ej5fM());
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(contentDescription3);
            Object it$iv$iv7 = $composer3.rememberedValue();
            if (invalid$iv$iv) {
            }
            value$iv$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$1$1
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
                    SemanticsPropertiesKt.setContentDescription(semantics, contentDescription3);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv3);
            $composer3.endReplaceableGroup();
            Modifier modifierSemantics$default3 = SemanticsModifierKt.semantics$default(modifierM536sizeVpY3zN9, false, (Function1) value$iv$iv3, 1, null);
            ProvidableCompositionLocal<TextStyle> localTextStyle3 = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume111 = $composer3.consume(localTextStyle3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BasicTextFieldKt.BasicTextField(value, function1, modifierSemantics$default3, true, false, (TextStyle) objConsume111, keyboardOptions3, keyboardActions3, true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource3, Brush.Companion.m2929verticalGradient8A3gB4$default(Brush.INSTANCE, new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m2961boximpl(Color.INSTANCE.m3006getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m2961boximpl(Color.INSTANCE.m3006getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(0.1f), Color.m2961boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).m1385getPrimary0d7_KjU())), TuplesKt.to(Float.valueOf(0.9f), Color.m2961boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, 6).m1385getPrimary0d7_KjU())), TuplesKt.to(Float.valueOf(0.9f), Color.m2961boximpl(Color.INSTANCE.m3006getTransparent0d7_KjU())), TuplesKt.to(Float.valueOf(1.0f), Color.m2961boximpl(Color.INSTANCE.m3006getTransparent0d7_KjU()))}, 0.0f, 0.0f, 0, 14, (Object) null), (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.composableLambda($composer3, 2133555260, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function2, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function2, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> it, Composer $composer6, int $changed2) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    ComposerKt.sourceInformation($composer6, "C1448@55094L864:TimePicker.kt#uh7d8r");
                    int $dirty3 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty3 |= $composer6.changedInstance(it) ? 4 : 2;
                    }
                    int $dirty4 = $dirty3;
                    if (($dirty4 & 91) == 18 && $composer6.getSkipping()) {
                        $composer6.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(2133555260, $dirty4, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1447)");
                    }
                    OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                    String text = value.getText();
                    VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                    PaddingValues paddingValuesM480PaddingValues0680j_4 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    MutableInteractionSource mutableInteractionSource = interactionSource3;
                    TextFieldColors textFieldColors4 = textFieldColors3;
                    final MutableInteractionSource mutableInteractionSource2 = interactionSource3;
                    final TextFieldColors textFieldColors5 = textFieldColors3;
                    outlinedTextFieldDefaults.DecorationBox(text, it, true, true, none, mutableInteractionSource, false, null, null, null, null, null, null, null, textFieldColors4, paddingValuesM480PaddingValues0680j_4, ComposableLambdaKt.composableLambda($composer6, -968963953, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer7, int $changed3) {
                            ComposerKt.sourceInformation($composer7, "C1462@55828L9,1458@55582L336:TimePicker.kt#uh7d8r");
                            if (($changed3 & 11) == 2 && $composer7.getSkipping()) {
                                $composer7.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-968963953, $changed3, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1457)");
                            }
                            OutlinedTextFieldDefaults.INSTANCE.m1648ContainerBoxnbWgWpA(true, false, mutableInteractionSource2, textFieldColors5, ShapesKt.toShape(TimeInputTokens.INSTANCE.getTimeFieldContainerShape(), $composer7, 6), 0.0f, 0.0f, $composer7, 12583350, 96);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }), $composer6, (($dirty4 << 3) & 112) | 224640, 14352384, 16320);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer3, (($dirty >> 3) & 14) | 100666368 | (($dirty >> 3) & 112) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128), 199680, 7696);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            Modifier modifierClearAndSetSemantics3 = SemanticsModifierKt.clearAndSetSemantics(androidx.compose.foundation.layout.OffsetKt.m448offsetVpY3zN4$default(Modifier.INSTANCE, 0.0f, SupportLabelTop, 1, null), new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver clearAndSetSemantics) {
                    Intrinsics.checkNotNullParameter(clearAndSetSemantics, "$this$clearAndSetSemantics");
                }
            });
            if (Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1711getHourJiIwxys())) {
                iM1789getTimePickerMinuteadMyvUU = Strings.INSTANCE.m1785getTimePickerHouradMyvUU();
            } else {
                iM1789getTimePickerMinuteadMyvUU = Strings.INSTANCE.m1789getTimePickerMinuteadMyvUU();
            }
            TextKt.m1884Text4IGK_g(Strings_androidKt.m1797getStringNWtq28(iM1789getTimePickerMinuteadMyvUU, $composer3, 0), modifierClearAndSetSemantics3, ColorSchemeKt.toColor(TimeInputTokens.INSTANCE.getTimeFieldSupportingTextColor(), $composer3, 6), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer3, 6), TimeInputTokens.INSTANCE.getTimeFieldSupportingTextFont()), $composer3, 0, 0, 65528);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            $composer5.endReplaceableGroup();
            $composer5.endNode();
            $composer5.endReplaceableGroup();
            $composer5.endReplaceableGroup();
            Selection selectionM1704boximpl3 = Selection.m1704boximpl(state.m1924getSelectionJiIwxys$material3_release());
            Object key2$iv3 = Selection.m1704boximpl(selection);
            int i19 = (($dirty >> 9) & 14) | 384 | (($dirty >> 9) & 112);
            $composer4 = $composer5;
            $composer4.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer4.changed(state) | $composer4.changed(key2$iv3) | $composer4.changed(focusRequester);
            Object it$iv$iv8 = $composer4.rememberedValue();
            if (invalid$iv$iv2) {
                value$iv$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester, null);
                $composer4.updateRememberedValue(value$iv$iv4);
            } else {
                value$iv$iv4 = new TimePickerKt$TimePickerTextField$2$1(state, selection, focusRequester, null);
                $composer4.updateRememberedValue(value$iv$iv4);
            }
            $composer4.endReplaceableGroup();
            EffectsKt.LaunchedEffect(selectionM1704boximpl3, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv4, $composer4, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer4.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final KeyboardOptions keyboardOptions5 = keyboardOptions3;
        final KeyboardActions keyboardActions5 = keyboardActions3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePickerTextField$3
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

            public final void invoke(Composer composer, int i110) {
                TimePickerKt.m1900TimePickerTextFieldlxUZ_iY(modifier, value, function1, state, selection, keyboardOptions5, keyboardActions5, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: CircularLayout-uFdPcIQ, reason: not valid java name */
    public static final void m1898CircularLayoutuFdPcIQ(Modifier modifier, final float radius, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(1548175696);
        ComposerKt.sourceInformation($composer2, "C(CircularLayout)P(1,2:c#ui.unit.Dp)1502@56908L1669:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 14) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(radius) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 731) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1548175696, $dirty2, -1, "androidx.compose.material3.CircularLayout (TimePicker.kt:1497)");
            }
            MeasurePolicy measurePolicy = new MeasurePolicy() { // from class: androidx.compose.material3.TimePickerKt$CircularLayout$1
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                    return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                    return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                    return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                    return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo11measure3p2s80s(MeasureScope Layout, List<? extends Measurable> measurables, final long constraints) {
                    Object next;
                    Object next2;
                    Measurable it;
                    Measurable it2;
                    Intrinsics.checkNotNullParameter(Layout, "$this$Layout");
                    Intrinsics.checkNotNullParameter(measurables, "measurables");
                    final float radiusPx = Layout.mo327toPx0680j_4(radius);
                    long itemConstraints = Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0);
                    List<? extends Measurable> $this$filter$iv = measurables;
                    Collection destination$iv$iv = new ArrayList();
                    for (Object element$iv$iv : $this$filter$iv) {
                        Measurable it3 = (Measurable) element$iv$iv;
                        if ((LayoutIdKt.getLayoutId(it3) == LayoutId.Selector || LayoutIdKt.getLayoutId(it3) == LayoutId.InnerCircle) ? false : true) {
                            destination$iv$iv.add(element$iv$iv);
                        }
                    }
                    Iterable $this$map$iv = (List) destination$iv$iv;
                    Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    for (Object item$iv$iv : $this$map$iv) {
                        Measurable measurable = (Measurable) item$iv$iv;
                        destination$iv$iv2.add(measurable.mo4225measureBRTryo0(itemConstraints));
                    }
                    final List placeables = (List) destination$iv$iv2;
                    Iterator<T> it4 = measurables.iterator();
                    do {
                        if (!it4.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it4.next();
                        it2 = (Measurable) next;
                    } while (!(LayoutIdKt.getLayoutId(it2) == LayoutId.Selector));
                    Measurable selectorMeasurable = (Measurable) next;
                    Iterator<T> it5 = measurables.iterator();
                    do {
                        if (!it5.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it5.next();
                        it = (Measurable) next2;
                    } while (!(LayoutIdKt.getLayoutId(it) == LayoutId.InnerCircle));
                    Measurable innerMeasurable = (Measurable) next2;
                    final float theta = 6.2831855f / placeables.size();
                    final Placeable selectorPlaceable = selectorMeasurable != null ? selectorMeasurable.mo4225measureBRTryo0(itemConstraints) : null;
                    final Placeable innerCirclePlaceable = innerMeasurable != null ? innerMeasurable.mo4225measureBRTryo0(itemConstraints) : null;
                    return MeasureScope.CC.layout$default(Layout, Constraints.m5220getMinWidthimpl(constraints), Constraints.m5219getMinHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$CircularLayout$1$measure$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope layout) {
                            Intrinsics.checkNotNullParameter(layout, "$this$layout");
                            Placeable placeable = selectorPlaceable;
                            if (placeable != null) {
                                Placeable.PlacementScope.place$default(layout, placeable, 0, 0, 0.0f, 4, null);
                            }
                            Iterable $this$forEachIndexed$iv = placeables;
                            long j = constraints;
                            float f = radiusPx;
                            float f2 = theta;
                            int index$iv = 0;
                            for (Object item$iv : $this$forEachIndexed$iv) {
                                int index$iv2 = index$iv + 1;
                                if (index$iv < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                Placeable it6 = (Placeable) item$iv;
                                int i3 = index$iv;
                                int centerOffsetX = (Constraints.m5218getMaxWidthimpl(j) / 2) - (it6.getWidth() / 2);
                                int centerOffsetY = (Constraints.m5217getMaxHeightimpl(j) / 2) - (it6.getHeight() / 2);
                                double d = f;
                                double offsetX = ((double) centerOffsetX) + (Math.cos(((double) (i3 * f2)) - 1.5707963267948966d) * d);
                                double offsetY = (d * Math.sin(((double) (i3 * f2)) - 1.5707963267948966d)) + ((double) centerOffsetY);
                                Placeable.PlacementScope.place$default(layout, it6, MathKt.roundToInt(offsetX), MathKt.roundToInt(offsetY), 0.0f, 4, null);
                                index$iv = index$iv2;
                                j = j;
                            }
                            Placeable placeable2 = innerCirclePlaceable;
                            if (placeable2 != null) {
                                Placeable.PlacementScope.place$default(layout, placeable2, (Constraints.m5220getMinWidthimpl(constraints) - innerCirclePlaceable.getWidth()) / 2, (Constraints.m5219getMinHeightimpl(constraints) - innerCirclePlaceable.getHeight()) / 2, 0.0f, 4, null);
                            }
                        }
                    }, 4, null);
                }
            };
            int $changed$iv = (($dirty2 >> 6) & 14) | (($dirty2 << 3) & 112);
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)73@2855L7,74@2910L7,75@2969L7,76@2981L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv = (LayoutDirection) objConsume2;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv = (ViewConfiguration) objConsume3;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier4);
            int $changed$iv$iv = (($changed$iv << 9) & 7168) | 6;
            Modifier modifier5 = modifier4;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, density$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, layoutDirection$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, viewConfiguration$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            function2.invoke($composer2, Integer.valueOf(($changed$iv$iv >> 9) & 14));
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$CircularLayout$2
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

            public final void invoke(Composer composer, int i3) {
                TimePickerKt.m1898CircularLayoutuFdPcIQ(modifier6, radius, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: renamed from: numberContentDescription-YKJpE6Y, reason: not valid java name */
    public static final String m1906numberContentDescriptionYKJpE6Y(int selection, boolean is24Hour, int number, Composer $composer, int $changed) {
        int id;
        ComposerKt.sourceInformationMarkerStart($composer, 1826155772, "C(numberContentDescription)P(2:c#material3.Selection)1555@58952L21:TimePicker.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1826155772, $changed, -1, "androidx.compose.material3.numberContentDescription (TimePicker.kt:1542)");
        }
        if (Selection.m1707equalsimpl0(selection, Selection.INSTANCE.m1712getMinuteJiIwxys())) {
            id = Strings.INSTANCE.m1791getTimePickerMinuteSuffixadMyvUU();
        } else if (is24Hour) {
            id = Strings.INSTANCE.m1783getTimePicker24HourSuffixadMyvUU();
        } else {
            id = Strings.INSTANCE.m1787getTimePickerHourSuffixadMyvUU();
        }
        String strM1798getStringiSCLEhQ = Strings_androidKt.m1798getStringiSCLEhQ(id, new Object[]{Integer.valueOf(number)}, $composer, 64);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return strM1798getStringiSCLEhQ;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair<Float, Float> valuesForAnimation(float current, float f) {
        float start = current;
        float end = f;
        if (Math.abs(start - end) <= 3.141592653589793d) {
            return new Pair<>(Float.valueOf(start), Float.valueOf(end));
        }
        if (start > 3.141592653589793d && end < 3.141592653589793d) {
            end += FullCircle;
        } else if (start < 3.141592653589793d && end > 3.141592653589793d) {
            start += FullCircle;
        }
        return new Pair<>(Float.valueOf(start), Float.valueOf(end));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float dist(float x1, float y1, int x2, int y2) {
        float x = x2 - x1;
        float y = y2 - y1;
        return (float) Math.hypot(x, y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float atan(float y, float x) {
        float ret = ((float) Math.atan2(y, x)) - 1.5707964f;
        return ret < 0.0f ? FullCircle + ret : ret;
    }

    static {
        List<Integer> listListOf = CollectionsKt.listOf((Object[]) new Integer[]{12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        Hours = listListOf;
        List<Integer> $this$map$iv = listListOf;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            int it = ((Number) item$iv$iv).intValue();
            destination$iv$iv.add(Integer.valueOf((it % 12) + 12));
        }
        ExtraHours = (List) destination$iv$iv;
        PeriodToggleMargin = Dp.m5274constructorimpl(12);
    }

    private static final Modifier visible(Modifier $this$visible, final boolean visible) {
        return $this$visible.then(new VisibleModifier(visible, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TimePickerKt$visible$$inlined$debugInspectorInfo$1
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
                $this$null.setName("visible");
                $this$null.getProperties().set("visible", Boolean.valueOf(visible));
            }
        } : InspectableValueKt.getNoInspectorInfo()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toLocalString(int $this$toLocalString, int minDigits) {
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        formatter.setGroupingUsed(false);
        formatter.setMinimumIntegerDigits(minDigits);
        String str = formatter.format(Integer.valueOf($this$toLocalString));
        Intrinsics.checkNotNullExpressionValue(str, "formatter.format(this)");
        return str;
    }
}
