package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.layout.AlignmentLineKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.tokens.PlainTooltipTokens;
import androidx.compose.material3.tokens.RichTooltipTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Tooltip.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001ax\u0010\u001a\u001a\u00020\u001b2\u0011\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u001b0\u001d¢\u0006\u0002\b\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020&2\u001c\u0010(\u001a\u0018\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001b0)¢\u0006\u0002\b\u001e¢\u0006\u0002\b+H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001a0\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u00020&2\u0011\u0010(\u001a\r\u0012\u0004\u0012\u00020\u001b0\u001d¢\u0006\u0002\b\u001eH\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\u0094\u0001\u00102\u001a\u00020\u001b2\u0011\u00103\u001a\r\u0012\u0004\u0012\u00020\u001b0\u001d¢\u0006\u0002\b\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u0002042\u0015\b\u0002\u00105\u001a\u000f\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001d¢\u0006\u0002\b\u001e2\u0015\b\u0002\u00106\u001a\u000f\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001d¢\u0006\u0002\b\u001e2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u00107\u001a\u0002082\u001c\u0010(\u001a\u0018\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001b0)¢\u0006\u0002\b\u001e¢\u0006\u0002\b+H\u0007¢\u0006\u0002\u00109\u001aR\u0010:\u001a\u00020\u001b2\u0006\u00107\u001a\u0002082\u0011\u00103\u001a\r\u0012\u0004\u0012\u00020\u001b0\u001d¢\u0006\u0002\b\u001e2\u0013\u00105\u001a\u000f\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001d¢\u0006\u0002\b\u001e2\u0013\u00106\u001a\u000f\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001d¢\u0006\u0002\b\u001eH\u0003¢\u0006\u0002\u0010;\u001a~\u0010<\u001a\u00020\u001b2\u0011\u0010=\u001a\r\u0012\u0004\u0012\u00020\u001b0\u001d¢\u0006\u0002\b\u001e2\u0006\u0010>\u001a\u00020?2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\u0006\u0010!\u001a\u00020@2\u0006\u0010%\u001a\u00020&2\u0006\u0010A\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u00012\u001c\u0010(\u001a\u0018\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001b0)¢\u0006\u0002\b\u001e¢\u0006\u0002\b+H\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bC\u0010D\u001a\u001a\u0010E\u001a\u00020 *\u00020 2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020H0GH\u0002\u001a\u001c\u0010I\u001a\u00020 *\u00020 2\u0006\u0010J\u001a\u00020H2\u0006\u0010K\u001a\u00020HH\u0002\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0004\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0005\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\b\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\t\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\n\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0019\u0010\u000b\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\f\u0010\r\"\u0013\u0010\u000e\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u000f\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0010\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0014X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u0019\u0010\u0016\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\u0017\u0010\r\"\u0019\u0010\u0018\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\u0019\u0010\r\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006L"}, d2 = {"ActionLabelBottomPadding", "Landroidx/compose/ui/unit/Dp;", "F", "ActionLabelMinHeight", "HeightFromSubheadToTextFirstLine", "HeightToSubheadFirstLine", "PlainTooltipContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "PlainTooltipHorizontalPadding", "PlainTooltipMaxWidth", "PlainTooltipVerticalPadding", "RichTooltipHorizontalPadding", "getRichTooltipHorizontalPadding", "()F", "RichTooltipMaxWidth", "TextBottomPadding", "TooltipAnchorPadding", "TooltipDuration", "", "TooltipFadeInDuration", "", "TooltipFadeOutDuration", "TooltipMinHeight", "getTooltipMinHeight", "TooltipMinWidth", "getTooltipMinWidth", "PlainTooltipBox", "", "tooltip", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "tooltipState", "Landroidx/compose/material3/PlainTooltipState;", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "content", "Lkotlin/Function1;", "Landroidx/compose/material3/TooltipBoxScope;", "Lkotlin/ExtensionFunctionType;", "PlainTooltipBox-nBX6wN0", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/PlainTooltipState;Landroidx/compose/ui/graphics/Shape;JJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "PlainTooltipImpl", "textColor", "PlainTooltipImpl-Iv8Zu3U", "(JLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "RichTooltipBox", "text", "Landroidx/compose/material3/RichTooltipState;", "title", "action", "colors", "Landroidx/compose/material3/RichTooltipColors;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/RichTooltipState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/RichTooltipColors;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "RichTooltipImpl", "(Landroidx/compose/material3/RichTooltipColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TooltipBox", "tooltipContent", "tooltipPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "Landroidx/compose/material3/TooltipState;", "elevation", "maxWidth", "TooltipBox-XDn_Kpo", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/window/PopupPositionProvider;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TooltipState;JFFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "animateTooltip", "transition", "Landroidx/compose/animation/core/Transition;", "", "textVerticalPadding", "subheadExists", "actionExists", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TooltipKt {
    private static final float ActionLabelBottomPadding;
    private static final float ActionLabelMinHeight;
    private static final float HeightFromSubheadToTextFirstLine;
    private static final float HeightToSubheadFirstLine;
    private static final PaddingValues PlainTooltipContentPadding;
    private static final float PlainTooltipHorizontalPadding;
    private static final float PlainTooltipVerticalPadding;
    private static final float RichTooltipHorizontalPadding;
    private static final float RichTooltipMaxWidth;
    private static final float TextBottomPadding;
    public static final long TooltipDuration = 1500;
    public static final int TooltipFadeInDuration = 150;
    private static final int TooltipFadeOutDuration = 75;
    private static final float TooltipAnchorPadding = Dp.m5274constructorimpl(4);
    private static final float TooltipMinHeight = Dp.m5274constructorimpl(24);
    private static final float TooltipMinWidth = Dp.m5274constructorimpl(40);
    private static final float PlainTooltipMaxWidth = Dp.m5274constructorimpl(ComposerKt.invocationKey);

    /* JADX WARN: Code duplicated, block: B:100:0x0150  */
    /* JADX WARN: Code duplicated, block: B:102:0x0154  */
    /* JADX WARN: Code duplicated, block: B:104:0x0172  */
    /* JADX WARN: Code duplicated, block: B:105:0x0183  */
    /* JADX WARN: Code duplicated, block: B:107:0x0191  */
    /* JADX WARN: Code duplicated, block: B:110:0x0198  */
    /* JADX WARN: Code duplicated, block: B:113:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:116:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:117:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:120:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:123:0x021a  */
    /* JADX WARN: Code duplicated, block: B:124:0x0228  */
    /* JADX WARN: Code duplicated, block: B:127:0x0295  */
    /* JADX WARN: Code duplicated, block: B:131:0x029f  */
    /* JADX WARN: Code duplicated, block: B:133:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x0106  */
    /* JADX WARN: Code duplicated, block: B:85:0x0115  */
    /* JADX WARN: Code duplicated, block: B:98:0x0149 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:99:0x014b  */
    /* JADX INFO: renamed from: PlainTooltipBox-nBX6wN0, reason: not valid java name */
    public static final void m1995PlainTooltipBoxnBX6wN0(final Function2<? super Composer, ? super Integer, Unit> tooltip, Modifier modifier, PlainTooltipState tooltipState, Shape shape, long containerColor, long contentColor, final Function3<? super TooltipBoxScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        PlainTooltipState tooltipState2;
        Shape shape2;
        long containerColor2;
        final long contentColor2;
        int i2;
        Modifier.Companion modifier3;
        PlainTooltipState tooltipState3;
        Shape shape3;
        long containerColor3;
        final int $dirty;
        Object it$iv$iv;
        Object value$iv$iv;
        int tooltipAnchorPadding;
        Object it$iv$iv2;
        Object value$iv$iv2;
        long contentColor3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(tooltip, "tooltip");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-531325137);
        ComposerKt.sourceInformation($composer2, "C(PlainTooltipBox)P(5,3,6,4,0:c#ui.graphics.Color,2:c#ui.graphics.Color)97@4379L32,98@4448L26,99@4520L26,100@4590L24,*103@4721L7,104@4794L63,106@4863L450:Tooltip.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changedInstance(tooltip) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
            tooltipState2 = tooltipState;
        } else if (($changed & 896) == 0) {
            tooltipState2 = tooltipState;
            $dirty2 |= $composer2.changed(tooltipState2) ? 256 : 128;
        } else {
            tooltipState2 = tooltipState;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i5 = $composer2.changed(shape2) ? 2048 : 1024;
                $dirty2 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                containerColor2 = containerColor;
                int i6 = $composer2.changed(containerColor2) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                containerColor2 = containerColor;
            }
            $dirty2 |= i6;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                contentColor2 = contentColor;
                int i7 = $composer2.changed(contentColor2) ? 131072 : 65536;
                $dirty2 |= i7;
            } else {
                contentColor2 = contentColor;
            }
            $dirty2 |= i7;
        } else {
            contentColor2 = contentColor;
        }
        if ((i & 64) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(content) ? 1048576 : 524288;
            }
            if (($dirty2 & 2995931) == 599186 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i4 != 0) {
                        $composer2.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                        it$iv$iv = $composer2.rememberedValue();
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = new PlainTooltipState();
                            $composer2.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer2.endReplaceableGroup();
                        tooltipState2 = (PlainTooltipState) value$iv$iv;
                    }
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                        shape2 = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape($composer2, 6);
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                        containerColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor($composer2, 6);
                    }
                    if ((i & 32) != 0) {
                        contentColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContentColor($composer2, 6);
                        tooltipState3 = tooltipState2;
                        shape3 = shape2;
                        containerColor3 = containerColor2;
                        $dirty = (-458753) & $dirty2;
                    } else {
                        tooltipState3 = tooltipState2;
                        shape3 = shape2;
                        containerColor3 = containerColor2;
                        $dirty = $dirty2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        modifier3 = modifier;
                        tooltipState3 = tooltipState2;
                        shape3 = shape2;
                        containerColor3 = containerColor2;
                        $dirty = (-458753) & $dirty2;
                    } else {
                        modifier3 = modifier;
                        tooltipState3 = tooltipState2;
                        shape3 = shape2;
                        containerColor3 = containerColor2;
                        $dirty = $dirty2;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-531325137, $dirty, -1, "androidx.compose.material3.PlainTooltipBox (Tooltip.kt:94)");
                }
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density $this$PlainTooltipBox_nBX6wN0_u24lambda_u241 = (Density) objConsume;
                tooltipAnchorPadding = $this$PlainTooltipBox_nBX6wN0_u24lambda_u241.mo321roundToPx0680j_4(TooltipAnchorPadding);
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv2 = $composer2.rememberedValue();
                if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = new PlainTooltipPositionProvider(tooltipAnchorPadding);
                    $composer2.updateRememberedValue(value$iv$iv2);
                } else {
                    value$iv$iv2 = it$iv$iv2;
                }
                $composer2.endReplaceableGroup();
                PlainTooltipPositionProvider positionProvider = (PlainTooltipPositionProvider) value$iv$iv2;
                contentColor3 = contentColor2;
                m1997TooltipBoxXDn_Kpo(ComposableLambdaKt.composableLambda($composer2, -785135750, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt$PlainTooltipBox$2
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

                    public final void invoke(Composer $composer3, int $changed2) {
                        ComposerKt.sourceInformation($composer3, "C108@4914L107:Tooltip.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-785135750, $changed2, -1, "androidx.compose.material3.PlainTooltipBox.<anonymous> (Tooltip.kt:107)");
                            }
                            long j = contentColor2;
                            Function2<Composer, Integer, Unit> function2 = tooltip;
                            int i8 = $dirty;
                            TooltipKt.m1996PlainTooltipImplIv8Zu3U(j, function2, $composer3, ((i8 << 3) & 112) | ((i8 >> 15) & 14));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), positionProvider, modifier3, shape3, tooltipState3, containerColor3, Dp.m5274constructorimpl(0), PlainTooltipMaxWidth, content, $composer2, (($dirty << 3) & 896) | 14155830 | ($dirty & 7168) | (($dirty << 6) & 57344) | (($dirty << 3) & 458752) | (($dirty << 6) & 234881024));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                tooltipState3 = tooltipState2;
                shape3 = shape2;
                containerColor3 = containerColor2;
                contentColor3 = contentColor2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final PlainTooltipState plainTooltipState = tooltipState3;
            final Shape shape4 = shape3;
            final long j = containerColor3;
            final long j2 = contentColor3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt$PlainTooltipBox$3
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

                public final void invoke(Composer composer, int i8) {
                    TooltipKt.m1995PlainTooltipBoxnBX6wN0(tooltip, modifier4, plainTooltipState, shape4, j, j2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty2 |= i2;
        if (($dirty2 & 2995931) == 599186) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if (i4 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new PlainTooltipState();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    tooltipState2 = (PlainTooltipState) value$iv$iv;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape($composer2, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    containerColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor($composer2, 6);
                }
                if ((i & 32) != 0) {
                    contentColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContentColor($composer2, 6);
                    tooltipState3 = tooltipState2;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    $dirty = (-458753) & $dirty2;
                } else {
                    tooltipState3 = tooltipState2;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if (i4 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new PlainTooltipState();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    tooltipState2 = (PlainTooltipState) value$iv$iv;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape($composer2, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    containerColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor($composer2, 6);
                }
                if ((i & 32) != 0) {
                    contentColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContentColor($composer2, 6);
                    tooltipState3 = tooltipState2;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    $dirty = (-458753) & $dirty2;
                } else {
                    tooltipState3 = tooltipState2;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-531325137, $dirty, -1, "androidx.compose.material3.PlainTooltipBox (Tooltip.kt:94)");
            }
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$PlainTooltipBox_nBX6wN0_u24lambda_u242 = (Density) objConsume2;
            tooltipAnchorPadding = $this$PlainTooltipBox_nBX6wN0_u24lambda_u242.mo321roundToPx0680j_4(TooltipAnchorPadding);
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv2 = $composer2.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new PlainTooltipPositionProvider(tooltipAnchorPadding);
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            PlainTooltipPositionProvider positionProvider2 = (PlainTooltipPositionProvider) value$iv$iv2;
            contentColor3 = contentColor2;
            m1997TooltipBoxXDn_Kpo(ComposableLambdaKt.composableLambda($composer2, -785135750, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt$PlainTooltipBox$2
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

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C108@4914L107:Tooltip.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-785135750, $changed2, -1, "androidx.compose.material3.PlainTooltipBox.<anonymous> (Tooltip.kt:107)");
                        }
                        long j3 = contentColor2;
                        Function2<Composer, Integer, Unit> function2 = tooltip;
                        int i8 = $dirty;
                        TooltipKt.m1996PlainTooltipImplIv8Zu3U(j3, function2, $composer3, ((i8 << 3) & 112) | ((i8 >> 15) & 14));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), positionProvider2, modifier3, shape3, tooltipState3, containerColor3, Dp.m5274constructorimpl(0), PlainTooltipMaxWidth, content, $composer2, (($dirty << 3) & 896) | 14155830 | ($dirty & 7168) | (($dirty << 6) & 57344) | (($dirty << 3) & 458752) | (($dirty << 6) & 234881024));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if (i4 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new PlainTooltipState();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    tooltipState2 = (PlainTooltipState) value$iv$iv;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape($composer2, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    containerColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor($composer2, 6);
                }
                if ((i & 32) != 0) {
                    contentColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContentColor($composer2, 6);
                    tooltipState3 = tooltipState2;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    $dirty = (-458753) & $dirty2;
                } else {
                    tooltipState3 = tooltipState2;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if (i4 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new PlainTooltipState();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    tooltipState2 = (PlainTooltipState) value$iv$iv;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape($composer2, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    containerColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor($composer2, 6);
                }
                if ((i & 32) != 0) {
                    contentColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContentColor($composer2, 6);
                    tooltipState3 = tooltipState2;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    $dirty = (-458753) & $dirty2;
                } else {
                    tooltipState3 = tooltipState2;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-531325137, $dirty, -1, "androidx.compose.material3.PlainTooltipBox (Tooltip.kt:94)");
            }
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$PlainTooltipBox_nBX6wN0_u24lambda_u243 = (Density) objConsume3;
            tooltipAnchorPadding = $this$PlainTooltipBox_nBX6wN0_u24lambda_u243.mo321roundToPx0680j_4(TooltipAnchorPadding);
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv2 = $composer2.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new PlainTooltipPositionProvider(tooltipAnchorPadding);
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            PlainTooltipPositionProvider positionProvider3 = (PlainTooltipPositionProvider) value$iv$iv2;
            contentColor3 = contentColor2;
            m1997TooltipBoxXDn_Kpo(ComposableLambdaKt.composableLambda($composer2, -785135750, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt$PlainTooltipBox$2
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

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C108@4914L107:Tooltip.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-785135750, $changed2, -1, "androidx.compose.material3.PlainTooltipBox.<anonymous> (Tooltip.kt:107)");
                        }
                        long j3 = contentColor2;
                        Function2<Composer, Integer, Unit> function2 = tooltip;
                        int i8 = $dirty;
                        TooltipKt.m1996PlainTooltipImplIv8Zu3U(j3, function2, $composer3, ((i8 << 3) & 112) | ((i8 >> 15) & 14));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), positionProvider3, modifier3, shape3, tooltipState3, containerColor3, Dp.m5274constructorimpl(0), PlainTooltipMaxWidth, content, $composer2, (($dirty << 3) & 896) | 14155830 | ($dirty & 7168) | (($dirty << 6) & 57344) | (($dirty << 3) & 458752) | (($dirty << 6) & 234881024));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final PlainTooltipState plainTooltipState2 = tooltipState3;
        final Shape shape5 = shape3;
        final long j3 = containerColor3;
        final long j4 = contentColor3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt$PlainTooltipBox$3
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

            public final void invoke(Composer composer, int i8) {
                TooltipKt.m1995PlainTooltipBoxnBX6wN0(tooltip, modifier5, plainTooltipState2, shape5, j3, j4, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:106:0x016c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:107:0x016e  */
    /* JADX WARN: Code duplicated, block: B:108:0x0173  */
    /* JADX WARN: Code duplicated, block: B:110:0x0177  */
    /* JADX WARN: Code duplicated, block: B:112:0x0193  */
    /* JADX WARN: Code duplicated, block: B:113:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:115:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:117:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:118:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:120:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:121:0x01be  */
    /* JADX WARN: Code duplicated, block: B:124:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:125:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:128:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:129:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:132:0x0209  */
    /* JADX WARN: Code duplicated, block: B:135:0x0252  */
    /* JADX WARN: Code duplicated, block: B:136:0x0260  */
    /* JADX WARN: Code duplicated, block: B:139:0x0294  */
    /* JADX WARN: Code duplicated, block: B:143:0x02a2 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:147:0x031c  */
    /* JADX WARN: Code duplicated, block: B:151:0x032d  */
    /* JADX WARN: Code duplicated, block: B:152:0x0330  */
    /* JADX WARN: Code duplicated, block: B:94:0x0124  */
    /* JADX WARN: Code duplicated, block: B:96:0x0136  */
    public static final void RichTooltipBox(final Function2<? super Composer, ? super Integer, Unit> text, Modifier modifier, RichTooltipState tooltipState, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Shape shape, RichTooltipColors colors, final Function3<? super TooltipBoxScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        final RichTooltipState tooltipState2;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Shape shape2;
        int i2;
        Modifier.Companion modifier2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        Shape shape3;
        int $dirty;
        RichTooltipColors colors2;
        Object it$iv$iv;
        Object value$iv$iv;
        int tooltipAnchorPadding;
        Object it$iv$iv2;
        Object value$iv$iv2;
        boolean invalid$iv$iv;
        Object value$iv$iv3;
        Function2<? super Composer, ? super Integer, Unit> function8;
        RichTooltipColors colors3;
        RichTooltipState tooltipState3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(180959998);
        ComposerKt.sourceInformation($composer2, "C(RichTooltipBox)P(5,3,7,6!1,4)151@6531L31,154@6690L25,155@6765L19,*158@6891L7,159@6964L62,161@7043L130,161@7032L141,166@7179L536:Tooltip.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changedInstance(text) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
            tooltipState2 = tooltipState;
        } else if (($changed & 896) == 0) {
            tooltipState2 = tooltipState;
            $dirty2 |= $composer2.changed(tooltipState2) ? 256 : 128;
        } else {
            tooltipState2 = tooltipState;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            function4 = function2;
        } else if (($changed & 7168) == 0) {
            function4 = function2;
            $dirty2 |= $composer2.changedInstance(function4) ? 2048 : 1024;
        } else {
            function4 = function2;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty2 |= 24576;
            function5 = function3;
        } else if (($changed & 57344) == 0) {
            function5 = function3;
            $dirty2 |= $composer2.changedInstance(function5) ? 16384 : 8192;
        } else {
            function5 = function3;
        }
        if ((458752 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i7 = $composer2.changed(shape2) ? 131072 : 65536;
                $dirty2 |= i7;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i7;
        } else {
            shape2 = shape;
        }
        if (($changed & 3670016) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer2.changed(colors)) ? 1048576 : 524288;
        }
        if ((i & 128) == 0) {
            if ((29360128 & $changed) == 0) {
                i2 = $composer2.changedInstance(content) ? 8388608 : 4194304;
            }
            if ((23967451 & $dirty2) == 4793490 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        $composer2.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                        it$iv$iv = $composer2.rememberedValue();
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = new RichTooltipState();
                            $composer2.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer2.endReplaceableGroup();
                        tooltipState2 = (RichTooltipState) value$iv$iv;
                    } else {
                        modifier2 = modifier2;
                    }
                    if (i5 != 0) {
                        function6 = null;
                    } else {
                        function6 = function4;
                    }
                    if (i6 != 0) {
                        function7 = null;
                    } else {
                        function7 = function3;
                    }
                    if ((i & 32) != 0) {
                        shape3 = TooltipDefaults.INSTANCE.getRichTooltipContainerShape($composer2, 6);
                        $dirty2 &= -458753;
                    } else {
                        shape3 = shape;
                    }
                    if ((i & 64) != 0) {
                        $dirty = $dirty2 & (-3670017);
                        colors2 = TooltipDefaults.INSTANCE.m1994richTooltipColorsro_MJ88(0L, 0L, 0L, 0L, $composer2, 24576, 15);
                    } else {
                        $dirty = $dirty2;
                        colors2 = colors;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                    }
                    if ((i & 64) != 0) {
                        int i8 = $dirty2 & (-3670017);
                        modifier2 = modifier;
                        function7 = function3;
                        shape3 = shape;
                        colors2 = colors;
                        $dirty = i8;
                        function6 = function4;
                    } else {
                        modifier2 = modifier;
                        function7 = function3;
                        shape3 = shape;
                        function6 = function4;
                        $dirty = $dirty2;
                        colors2 = colors;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(180959998, $dirty, -1, "androidx.compose.material3.RichTooltipBox (Tooltip.kt:148)");
                }
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density $this$RichTooltipBox_u24lambda_u244 = (Density) objConsume;
                tooltipAnchorPadding = $this$RichTooltipBox_u24lambda_u244.mo321roundToPx0680j_4(TooltipAnchorPadding);
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv2 = $composer2.rememberedValue();
                if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = new RichTooltipPositionProvider(tooltipAnchorPadding);
                    $composer2.updateRememberedValue(value$iv$iv2);
                } else {
                    value$iv$iv2 = it$iv$iv2;
                }
                $composer2.endReplaceableGroup();
                RichTooltipPositionProvider positionProvider = (RichTooltipPositionProvider) value$iv$iv2;
                int i9 = (($dirty >> 6) & 14) | (($dirty >> 9) & 112);
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(tooltipState2) | $composer2.changed(function7);
                value$iv$iv3 = $composer2.rememberedValue();
                if (!invalid$iv$iv || value$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.TooltipKt$RichTooltipBox$2$1
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
                            tooltipState2.setPersistent$material3_release(function7 != null);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv3);
                }
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv3, $composer2, 0);
                final RichTooltipColors richTooltipColors = colors2;
                final Function2<? super Composer, ? super Integer, Unit> function9 = function6;
                final Function2<? super Composer, ? super Integer, Unit> function10 = function7;
                final int i10 = $dirty;
                m1997TooltipBoxXDn_Kpo(ComposableLambdaKt.composableLambda($composer2, 1423372873, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt.RichTooltipBox.3
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

                    public final void invoke(Composer $composer3, int $changed2) {
                        ComposerKt.sourceInformation($composer3, "C168@7230L155:Tooltip.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1423372873, $changed2, -1, "androidx.compose.material3.RichTooltipBox.<anonymous> (Tooltip.kt:167)");
                            }
                            RichTooltipColors richTooltipColors2 = richTooltipColors;
                            Function2<Composer, Integer, Unit> function11 = text;
                            Function2<Composer, Integer, Unit> function12 = function9;
                            Function2<Composer, Integer, Unit> function13 = function10;
                            int i11 = i10;
                            TooltipKt.RichTooltipImpl(richTooltipColors2, function11, function12, function13, $composer3, ((i11 >> 18) & 14) | ((i11 << 3) & 112) | ((i11 >> 3) & 896) | ((i11 >> 3) & 7168));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), positionProvider, modifier2, shape3, tooltipState2, colors2.getContainerColor(), RichTooltipTokens.INSTANCE.m2432getContainerElevationD9Ej5fM(), RichTooltipMaxWidth, content, $composer2, (($dirty << 3) & 896) | 14155830 | (($dirty >> 6) & 7168) | (($dirty << 6) & 57344) | (234881024 & ($dirty << 3)));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function6;
                colors3 = colors2;
                function5 = function7;
                shape2 = shape3;
                tooltipState3 = tooltipState2;
            } else {
                $composer2.skipToGroupEnd();
                modifier2 = modifier;
                colors3 = colors;
                function8 = function4;
                tooltipState3 = tooltipState2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier3 = modifier2;
            final RichTooltipState richTooltipState = tooltipState3;
            final Function2<? super Composer, ? super Integer, Unit> function11 = function8;
            final Function2<? super Composer, ? super Integer, Unit> function12 = function5;
            final Shape shape4 = shape2;
            final RichTooltipColors richTooltipColors2 = colors3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt.RichTooltipBox.4
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
                    TooltipKt.RichTooltipBox(text, modifier3, richTooltipState, function11, function12, shape4, richTooltipColors2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 12582912;
        $dirty2 |= i2;
        if ((23967451 & $dirty2) == 4793490) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new RichTooltipState();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    tooltipState2 = (RichTooltipState) value$iv$iv;
                } else {
                    modifier2 = modifier2;
                }
                if (i5 != 0) {
                    function6 = null;
                } else {
                    function6 = function4;
                }
                if (i6 != 0) {
                    function7 = null;
                } else {
                    function7 = function3;
                }
                if ((i & 32) != 0) {
                    shape3 = TooltipDefaults.INSTANCE.getRichTooltipContainerShape($composer2, 6);
                    $dirty2 &= -458753;
                } else {
                    shape3 = shape;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    colors2 = TooltipDefaults.INSTANCE.m1994richTooltipColorsro_MJ88(0L, 0L, 0L, 0L, $composer2, 24576, 15);
                } else {
                    $dirty = $dirty2;
                    colors2 = colors;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new RichTooltipState();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    tooltipState2 = (RichTooltipState) value$iv$iv;
                } else {
                    modifier2 = modifier2;
                }
                if (i5 != 0) {
                    function6 = null;
                } else {
                    function6 = function4;
                }
                if (i6 != 0) {
                    function7 = null;
                } else {
                    function7 = function3;
                }
                if ((i & 32) != 0) {
                    shape3 = TooltipDefaults.INSTANCE.getRichTooltipContainerShape($composer2, 6);
                    $dirty2 &= -458753;
                } else {
                    shape3 = shape;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    colors2 = TooltipDefaults.INSTANCE.m1994richTooltipColorsro_MJ88(0L, 0L, 0L, 0L, $composer2, 24576, 15);
                } else {
                    $dirty = $dirty2;
                    colors2 = colors;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(180959998, $dirty, -1, "androidx.compose.material3.RichTooltipBox (Tooltip.kt:148)");
            }
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$RichTooltipBox_u24lambda_u245 = (Density) objConsume2;
            tooltipAnchorPadding = $this$RichTooltipBox_u24lambda_u245.mo321roundToPx0680j_4(TooltipAnchorPadding);
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv2 = $composer2.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new RichTooltipPositionProvider(tooltipAnchorPadding);
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            RichTooltipPositionProvider positionProvider2 = (RichTooltipPositionProvider) value$iv$iv2;
            int i11 = (($dirty >> 6) & 14) | (($dirty >> 9) & 112);
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(tooltipState2) | $composer2.changed(function7);
            value$iv$iv3 = $composer2.rememberedValue();
            if (!invalid$iv$iv) {
            }
            value$iv$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.TooltipKt$RichTooltipBox$2$1
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
                    tooltipState2.setPersistent$material3_release(function7 != null);
                }
            };
            $composer2.updateRememberedValue(value$iv$iv3);
            $composer2.endReplaceableGroup();
            EffectsKt.SideEffect((Function0) value$iv$iv3, $composer2, 0);
            final RichTooltipColors richTooltipColors3 = colors2;
            final Function2<? super Composer, ? super Integer, Unit> function13 = function6;
            final Function2<? super Composer, ? super Integer, Unit> function14 = function7;
            final int i12 = $dirty;
            m1997TooltipBoxXDn_Kpo(ComposableLambdaKt.composableLambda($composer2, 1423372873, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt.RichTooltipBox.3
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

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C168@7230L155:Tooltip.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1423372873, $changed2, -1, "androidx.compose.material3.RichTooltipBox.<anonymous> (Tooltip.kt:167)");
                        }
                        RichTooltipColors richTooltipColors4 = richTooltipColors3;
                        Function2<Composer, Integer, Unit> function15 = text;
                        Function2<Composer, Integer, Unit> function16 = function13;
                        Function2<Composer, Integer, Unit> function17 = function14;
                        int i13 = i12;
                        TooltipKt.RichTooltipImpl(richTooltipColors4, function15, function16, function17, $composer3, ((i13 >> 18) & 14) | ((i13 << 3) & 112) | ((i13 >> 3) & 896) | ((i13 >> 3) & 7168));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), positionProvider2, modifier2, shape3, tooltipState2, colors2.getContainerColor(), RichTooltipTokens.INSTANCE.m2432getContainerElevationD9Ej5fM(), RichTooltipMaxWidth, content, $composer2, (($dirty << 3) & 896) | 14155830 | (($dirty >> 6) & 7168) | (($dirty << 6) & 57344) | (234881024 & ($dirty << 3)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function8 = function6;
            colors3 = colors2;
            function5 = function7;
            shape2 = shape3;
            tooltipState3 = tooltipState2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new RichTooltipState();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    tooltipState2 = (RichTooltipState) value$iv$iv;
                } else {
                    modifier2 = modifier2;
                }
                if (i5 != 0) {
                    function6 = null;
                } else {
                    function6 = function4;
                }
                if (i6 != 0) {
                    function7 = null;
                } else {
                    function7 = function3;
                }
                if ((i & 32) != 0) {
                    shape3 = TooltipDefaults.INSTANCE.getRichTooltipContainerShape($composer2, 6);
                    $dirty2 &= -458753;
                } else {
                    shape3 = shape;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    colors2 = TooltipDefaults.INSTANCE.m1994richTooltipColorsro_MJ88(0L, 0L, 0L, 0L, $composer2, 24576, 15);
                } else {
                    $dirty = $dirty2;
                    colors2 = colors;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new RichTooltipState();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    tooltipState2 = (RichTooltipState) value$iv$iv;
                } else {
                    modifier2 = modifier2;
                }
                if (i5 != 0) {
                    function6 = null;
                } else {
                    function6 = function4;
                }
                if (i6 != 0) {
                    function7 = null;
                } else {
                    function7 = function3;
                }
                if ((i & 32) != 0) {
                    shape3 = TooltipDefaults.INSTANCE.getRichTooltipContainerShape($composer2, 6);
                    $dirty2 &= -458753;
                } else {
                    shape3 = shape;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    colors2 = TooltipDefaults.INSTANCE.m1994richTooltipColorsro_MJ88(0L, 0L, 0L, 0L, $composer2, 24576, 15);
                } else {
                    $dirty = $dirty2;
                    colors2 = colors;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(180959998, $dirty, -1, "androidx.compose.material3.RichTooltipBox (Tooltip.kt:148)");
            }
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$RichTooltipBox_u24lambda_u246 = (Density) objConsume3;
            tooltipAnchorPadding = $this$RichTooltipBox_u24lambda_u246.mo321roundToPx0680j_4(TooltipAnchorPadding);
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv2 = $composer2.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new RichTooltipPositionProvider(tooltipAnchorPadding);
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            RichTooltipPositionProvider positionProvider3 = (RichTooltipPositionProvider) value$iv$iv2;
            int i13 = (($dirty >> 6) & 14) | (($dirty >> 9) & 112);
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(tooltipState2) | $composer2.changed(function7);
            value$iv$iv3 = $composer2.rememberedValue();
            if (!invalid$iv$iv) {
            }
            value$iv$iv3 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.TooltipKt$RichTooltipBox$2$1
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
                    tooltipState2.setPersistent$material3_release(function7 != null);
                }
            };
            $composer2.updateRememberedValue(value$iv$iv3);
            $composer2.endReplaceableGroup();
            EffectsKt.SideEffect((Function0) value$iv$iv3, $composer2, 0);
            final RichTooltipColors richTooltipColors4 = colors2;
            final Function2<? super Composer, ? super Integer, Unit> function15 = function6;
            final Function2<? super Composer, ? super Integer, Unit> function16 = function7;
            final int i14 = $dirty;
            m1997TooltipBoxXDn_Kpo(ComposableLambdaKt.composableLambda($composer2, 1423372873, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt.RichTooltipBox.3
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

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C168@7230L155:Tooltip.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1423372873, $changed2, -1, "androidx.compose.material3.RichTooltipBox.<anonymous> (Tooltip.kt:167)");
                        }
                        RichTooltipColors richTooltipColors5 = richTooltipColors4;
                        Function2<Composer, Integer, Unit> function17 = text;
                        Function2<Composer, Integer, Unit> function18 = function15;
                        Function2<Composer, Integer, Unit> function19 = function16;
                        int i15 = i14;
                        TooltipKt.RichTooltipImpl(richTooltipColors5, function17, function18, function19, $composer3, ((i15 >> 18) & 14) | ((i15 << 3) & 112) | ((i15 >> 3) & 896) | ((i15 >> 3) & 7168));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), positionProvider3, modifier2, shape3, tooltipState2, colors2.getContainerColor(), RichTooltipTokens.INSTANCE.m2432getContainerElevationD9Ej5fM(), RichTooltipMaxWidth, content, $composer2, (($dirty << 3) & 896) | 14155830 | (($dirty >> 6) & 7168) | (($dirty << 6) & 57344) | (234881024 & ($dirty << 3)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function8 = function6;
            colors3 = colors2;
            function5 = function7;
            shape2 = shape3;
            tooltipState3 = tooltipState2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier2;
        final RichTooltipState richTooltipState2 = tooltipState3;
        final Function2<? super Composer, ? super Integer, Unit> function17 = function8;
        final Function2<? super Composer, ? super Integer, Unit> function18 = function5;
        final Shape shape5 = shape2;
        final RichTooltipColors richTooltipColors5 = colors3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt.RichTooltipBox.4
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

            public final void invoke(Composer composer, int i15) {
                TooltipKt.RichTooltipBox(text, modifier4, richTooltipState2, function17, function18, shape5, richTooltipColors5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: TooltipBox-XDn_Kpo, reason: not valid java name */
    public static final void m1997TooltipBoxXDn_Kpo(final Function2<? super Composer, ? super Integer, Unit> function2, final PopupPositionProvider tooltipPositionProvider, final Modifier modifier, final Shape shape, final TooltipState tooltipState, final long containerColor, final float elevation, final float maxWidth, final Function3<? super TooltipBoxScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Object value$iv$iv$iv;
        Composer $composer2 = $composer.startRestartGroup(-1415647894);
        ComposerKt.sourceInformation($composer2, "C(TooltipBox)P(6,7,4,5,8,0:c#ui.graphics.Color,2:c#ui.unit.Dp,3:c#ui.unit.Dp)199@8111L24,200@8161L49,202@8228L1804,245@10038L1263:Tooltip.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(tooltipPositionProvider) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(modifier) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(shape) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty |= $composer2.changed(tooltipState) ? 16384 : 8192;
        }
        if ((458752 & $changed) == 0) {
            $dirty |= $composer2.changed(containerColor) ? 131072 : 65536;
        }
        if ((3670016 & $changed) == 0) {
            $dirty |= $composer2.changed(elevation) ? 1048576 : 524288;
        }
        if ((29360128 & $changed) == 0) {
            $dirty |= $composer2.changed(maxWidth) ? 8388608 : 4194304;
        }
        if ((234881024 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        final int $dirty2 = $dirty;
        if ((191739611 & $dirty2) != 38347922 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1415647894, $dirty2, -1, "androidx.compose.material3.TooltipBox (Tooltip.kt:188)");
            }
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
            final CoroutineScope coroutineScope = wrapper$iv.getCoroutineScope();
            $composer2.endReplaceableGroup();
            final String longPressLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1795getTooltipLongPressLabeladMyvUU(), $composer2, 6);
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object value$iv$iv = $composer2.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new TooltipBoxScope() { // from class: androidx.compose.material3.TooltipKt$TooltipBox$scope$1$1
                    @Override // androidx.compose.material3.TooltipBoxScope
                    public Modifier tooltipAnchor(Modifier $this$tooltipAnchor) {
                        Intrinsics.checkNotNullParameter($this$tooltipAnchor, "<this>");
                        final CoroutineScope coroutineScope2 = coroutineScope;
                        final TooltipState tooltipState2 = tooltipState;
                        final Function0<Job> function0 = new Function0<Job>() { // from class: androidx.compose.material3.TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$onLongPress$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Job invoke() {
                                return BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(tooltipState2, null), 3, null);
                            }

                            /* JADX INFO: renamed from: androidx.compose.material3.TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$onLongPress$1$1, reason: invalid class name */
                            /* JADX INFO: compiled from: Tooltip.kt */
                            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material3.TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$onLongPress$1$1", f = "Tooltip.kt", i = {}, l = {208}, m = "invokeSuspend", n = {}, s = {})
                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ TooltipState $tooltipState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass1(TooltipState tooltipState, Continuation<? super AnonymousClass1> continuation) {
                                    super(2, continuation);
                                    this.$tooltipState = tooltipState;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new AnonymousClass1(this.$tooltipState, continuation);
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
                                            if (this.$tooltipState.show(this) == coroutine_suspended) {
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
                        Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput($this$tooltipAnchor, tooltipState, new TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1(function0, null));
                        final String str = longPressLabel;
                        return SemanticsModifierKt.semantics(modifierPointerInput, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$2
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
                                String str2 = str;
                                final Function0<Job> function1 = function0;
                                SemanticsPropertiesKt.onLongClick(semantics, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$2.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        function1.invoke();
                                        return true;
                                    }
                                });
                            }
                        });
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            }
            $composer2.endReplaceableGroup();
            TooltipKt$TooltipBox$scope$1$1 scope = (TooltipKt$TooltipBox$scope$1$1) value$iv$iv;
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
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
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1995827622, "C246@10069L70,275@11286L9:Tooltip.kt#uh7d8r");
            final Transition transition = TransitionKt.updateTransition(Boolean.valueOf(tooltipState.isVisible()), "Tooltip transition", $composer2, 48, 0);
            $composer2.startReplaceableGroup(-1995827526);
            ComposerKt.sourceInformation($composer2, "248@10246L41,249@10300L960");
            if (((Boolean) transition.getCurrentState()).booleanValue() || ((Boolean) transition.getTargetState()).booleanValue()) {
                final String tooltipPaneDescription = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1796getTooltipPaneDescriptionadMyvUU(), $composer2, 6);
                TooltipPopup_androidKt.TooltipPopup(tooltipPositionProvider, new Function0<Unit>() { // from class: androidx.compose.material3.TooltipKt$TooltipBox$1$1
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
                        if (tooltipState.isVisible()) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(tooltipState, null), 3, null);
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.TooltipKt$TooltipBox$1$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: Tooltip.kt */
                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.TooltipKt$TooltipBox$1$1$1", f = "Tooltip.kt", i = {}, l = {254}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ TooltipState $tooltipState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(TooltipState tooltipState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$tooltipState = tooltipState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$tooltipState, continuation);
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
                                    if (this.$tooltipState.dismiss(this) == coroutine_suspended) {
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
                }, ComposableLambdaKt.composableLambda($composer2, -442150991, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt$TooltipBox$1$2
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

                    public final void invoke(Composer $composer3, int $changed2) {
                        Object value$iv$iv2;
                        ComposerKt.sourceInformation($composer3, "C265@10968L38,257@10611L635:Tooltip.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-442150991, $changed2, -1, "androidx.compose.material3.TooltipBox.<anonymous>.<anonymous> (Tooltip.kt:256)");
                            }
                            Modifier modifierAnimateTooltip = TooltipKt.animateTooltip(SizeKt.m538sizeInqDBjuR0$default(modifier, TooltipKt.getTooltipMinWidth(), TooltipKt.getTooltipMinHeight(), maxWidth, 0.0f, 8, null), transition);
                            Object key1$iv = tooltipPaneDescription;
                            final String str = tooltipPaneDescription;
                            $composer3.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv$iv = $composer3.changed(key1$iv);
                            Object it$iv$iv = $composer3.rememberedValue();
                            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv2 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TooltipKt$TooltipBox$1$2$1$1
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
                                        SemanticsPropertiesKt.setPaneTitle(semantics, str);
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv$iv2);
                            } else {
                                value$iv$iv2 = it$iv$iv;
                            }
                            $composer3.endReplaceableGroup();
                            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierAnimateTooltip, false, (Function1) value$iv$iv2, 1, null);
                            Shape shape2 = shape;
                            long j = containerColor;
                            float f = elevation;
                            Function2<Composer, Integer, Unit> function4 = function2;
                            int i3 = $dirty2;
                            SurfaceKt.m1806SurfaceT9BRK9s(modifierSemantics$default, shape2, j, 0L, f, f, null, function4, $composer3, ((i3 >> 6) & 112) | ((i3 >> 9) & 896) | (57344 & (i3 >> 6)) | ((i3 >> 3) & 458752) | ((i3 << 21) & 29360128), 72);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, (($dirty2 >> 3) & 14) | 384);
            }
            $composer2.endReplaceableGroup();
            function3.invoke(scope, $composer2, Integer.valueOf((($dirty2 >> 21) & 112) | 6));
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt$TooltipBox$2
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
                TooltipKt.m1997TooltipBoxXDn_Kpo(function2, tooltipPositionProvider, modifier, shape, tooltipState, containerColor, elevation, maxWidth, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: PlainTooltipImpl-Iv8Zu3U, reason: not valid java name */
    public static final void m1996PlainTooltipImplIv8Zu3U(final long textColor, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(893340370);
        ComposerKt.sourceInformation($composer2, "C(PlainTooltipImpl)P(1:c#ui.graphics.Color)284@11413L337:Tooltip.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(textColor) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($dirty & 91) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(893340370, $dirty, -1, "androidx.compose.material3.PlainTooltipImpl (Tooltip.kt:280)");
            }
            Modifier modifier$iv = PaddingKt.padding(Modifier.INSTANCE, PlainTooltipContentPadding);
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            int $changed$iv$iv = (6 << 3) & 112;
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
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1000468479, "C285@11514L10,286@11582L162:Tooltip.kt#uh7d8r");
            TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer2, 6), PlainTooltipTokens.INSTANCE.getSupportingTextFont());
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(textColor)), TextKt.getLocalTextStyle().provides(textStyle)}, function2, $composer2, ($dirty & 112) | 8);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt$PlainTooltipImpl$2
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
                TooltipKt.m1996PlainTooltipImplIv8Zu3U(textColor, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RichTooltipImpl(final RichTooltipColors colors, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-878950288);
        ComposerKt.sourceInformation($composer2, "C(RichTooltipImpl)P(1,2,3)303@12033L10,305@12142L10,307@12246L10,308@12309L1298:Tooltip.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(colors) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 5851) != 1170 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-878950288, $dirty2, -1, "androidx.compose.material3.RichTooltipImpl (Tooltip.kt:296)");
            }
            TextStyle actionLabelTextStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer2, 6), RichTooltipTokens.INSTANCE.getActionLabelTextFont());
            TextStyle subheadTextStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer2, 6), RichTooltipTokens.INSTANCE.getSubheadFont());
            TextStyle supportingTextStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer2, 6), RichTooltipTokens.INSTANCE.getSupportingTextFont());
            Modifier modifier$iv = PaddingKt.m489paddingVpY3zN4$default(Modifier.INSTANCE, RichTooltipHorizontalPadding, 0.0f, 2, null);
            $composer2.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation($composer2, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            int $changed$iv$iv = (6 << 3) & 112;
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
            int i2 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 51873561, "C322@12806L317:Tooltip.kt#uh7d8r");
            $composer2.startReplaceableGroup(51873568);
            ComposerKt.sourceInformation($composer2, "*312@12437L350");
            if (function3 != null) {
                Modifier modifier$iv2 = AlignmentLineKt.m369paddingFromBaselineVpY3zN4$default(Modifier.INSTANCE, HeightToSubheadFirstLine, 0.0f, 2, null);
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                int $i$f$Box = ((6 >> 3) & 14) | ((6 >> 3) & 112);
                MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, $i$f$Box);
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
                ComposerKt.sourceInformationMarkerStart($composer2, -1242326159, "C315@12562L211:Tooltip.kt#uh7d8r");
                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(colors.getTitleContentColor())), TextKt.getLocalTextStyle().provides(subheadTextStyle)}, function3, $composer2, 8);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            }
            $composer2.endReplaceableGroup();
            Modifier modifier$iv3 = textVerticalPadding(Modifier.INSTANCE, function3 != null, function4 != null);
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv3 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv3 = (0 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume7 = $composer2.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv3 = (Density) objConsume7;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume8 = $composer2.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv3 = (LayoutDirection) objConsume8;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration3 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume9 = $composer2.consume(localViewConfiguration3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv3 = (ViewConfiguration) objConsume9;
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf3 = LayoutKt.materializerOf(modifier$iv3);
            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor3);
            } else {
                $composer2.useNode();
            }
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv3 = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, density$iv$iv3, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, layoutDirection$iv$iv3, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, viewConfiguration$iv$iv3, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i5 = ($changed$iv$iv$iv3 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 471369652, "C325@12918L195:Tooltip.kt#uh7d8r");
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(colors.getContentColor())), TextKt.getLocalTextStyle().provides(supportingTextStyle)}, function2, $composer2, ($dirty2 & 112) | 8);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(75391440);
            ComposerKt.sourceInformation($composer2, "*332@13158L433");
            if (function4 != null) {
                Modifier modifier$iv4 = PaddingKt.m491paddingqDBjuR0$default(SizeKt.m525requiredHeightInVpY3zN4$default(Modifier.INSTANCE, ActionLabelMinHeight, 0.0f, 2, null), 0.0f, 0.0f, 0.0f, ActionLabelBottomPadding, 7, null);
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv4 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv3, false, $composer2, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                int $changed$iv$iv4 = (6 << 3) & 112;
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume10 = $composer2.consume(localDensity4);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density$iv$iv4 = (Density) objConsume10;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume11 = $composer2.consume(localLayoutDirection4);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LayoutDirection layoutDirection$iv$iv4 = (LayoutDirection) objConsume11;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration4 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume12 = $composer2.consume(localViewConfiguration4);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ViewConfiguration viewConfiguration$iv$iv4 = (ViewConfiguration) objConsume12;
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf4 = LayoutKt.materializerOf(modifier$iv4);
                int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor4);
                } else {
                    $composer2.useNode();
                }
                $composer2.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv4 = Updater.m2603constructorimpl($composer2);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv4, density$iv$iv4, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv4, layoutDirection$iv$iv4, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv4, viewConfiguration$iv$iv4, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer2.enableReusing();
                function3MaterializerOf4.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv4 >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i7 = ($changed$iv$iv$iv4 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i8 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1242325360, "C337@13361L216:Tooltip.kt#uh7d8r");
                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(colors.getActionContentColor())), TextKt.getLocalTextStyle().provides(actionLabelTextStyle)}, function4, $composer2, 8);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                Unit unit3 = Unit.INSTANCE;
                Unit unit4 = Unit.INSTANCE;
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
            $composer2 = $composer2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt.RichTooltipImpl.2
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
                TooltipKt.RichTooltipImpl(colors, function2, function3, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    private static final Modifier textVerticalPadding(Modifier $this$textVerticalPadding, boolean subheadExists, boolean actionExists) {
        if (!subheadExists && !actionExists) {
            return PaddingKt.m489paddingVpY3zN4$default($this$textVerticalPadding, 0.0f, PlainTooltipVerticalPadding, 1, null);
        }
        return PaddingKt.m491paddingqDBjuR0$default(AlignmentLineKt.m369paddingFromBaselineVpY3zN4$default($this$textVerticalPadding, HeightFromSubheadToTextFirstLine, 0.0f, 2, null), 0.0f, 0.0f, 0.0f, TextBottomPadding, 7, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier animateTooltip(Modifier $this$animateTooltip, final Transition<Boolean> transition) {
        return ComposedModifierKt.composed($this$animateTooltip, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TooltipKt$animateTooltip$$inlined$debugInspectorInfo$1
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
                $this$null.setName("animateTooltip");
                $this$null.getProperties().set("transition", transition);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material3.TooltipKt.animateTooltip.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            private static final float invoke$lambda$1(State<Float> state) {
                Object thisObj$iv = state.getValue();
                return ((Number) thisObj$iv).floatValue();
            }

            public final Modifier invoke(Modifier composed, Composer $composer, int $changed) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                $composer.startReplaceableGroup(-1498516085);
                ComposerKt.sourceInformation($composer, "C700@25010L583,719@25623L561:Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1498516085, $changed, -1, "androidx.compose.material3.animateTooltip.<anonymous> (Tooltip.kt:699)");
                }
                Transition<Boolean> transition2 = transition;
                Function3 transitionSpec$iv = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.TooltipKt$animateTooltip$2$scale$2
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer composer, Integer num) {
                        return invoke(segment, composer, num.intValue());
                    }

                    public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> animateFloat, Composer $composer2, int $changed2) {
                        TweenSpec tweenSpecTween$default;
                        Intrinsics.checkNotNullParameter(animateFloat, "$this$animateFloat");
                        $composer2.startReplaceableGroup(386845748);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(386845748, $changed2, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:701)");
                        }
                        if (animateFloat.isTransitioningTo(false, true)) {
                            tweenSpecTween$default = AnimationSpecKt.tween$default(150, 0, EasingKt.getLinearOutSlowInEasing(), 2, null);
                        } else {
                            tweenSpecTween$default = AnimationSpecKt.tween$default(75, 0, EasingKt.getLinearOutSlowInEasing(), 2, null);
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        $composer2.endReplaceableGroup();
                        return tweenSpecTween$default;
                    }
                };
                $composer.startReplaceableGroup(-1338768149);
                ComposerKt.sourceInformation($composer, "CC(animateFloat)P(2)938@37489L78:Transition.kt#pdpnli");
                TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                int $changed$iv$iv = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
                $composer.startReplaceableGroup(-142660079);
                ComposerKt.sourceInformation($composer, "CC(animateValue)P(3,2)856@34079L32,857@34134L31,858@34190L23,860@34226L89:Transition.kt#pdpnli");
                int $changed2 = ($changed$iv$iv >> 9) & 112;
                boolean it = transition2.getCurrentState().booleanValue();
                $composer.startReplaceableGroup(-1553362193);
                ComposerKt.sourceInformation($composer, "C:Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1553362193, $changed2, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:717)");
                }
                float f = it ? 1.0f : 0.8f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceableGroup();
                Object initialValue$iv$iv = Float.valueOf(f);
                int $changed3 = ($changed$iv$iv >> 9) & 112;
                boolean it2 = transition2.getTargetState().booleanValue();
                $composer.startReplaceableGroup(-1553362193);
                ComposerKt.sourceInformation($composer, "C:Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1553362193, $changed3, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:717)");
                }
                float f2 = it2 ? 1.0f : 0.8f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceableGroup();
                Object targetValue$iv$iv = Float.valueOf(f2);
                State scale$delegate = TransitionKt.createTransitionAnimation(transition2, initialValue$iv$iv, targetValue$iv$iv, transitionSpec$iv.invoke(transition2.getSegment(), $composer, Integer.valueOf(($changed$iv$iv >> 3) & 112)), vectorConverter, "tooltip transition: scaling", $composer, ($changed$iv$iv & 14) | (($changed$iv$iv << 9) & 57344) | (($changed$iv$iv << 6) & 458752));
                $composer.endReplaceableGroup();
                $composer.endReplaceableGroup();
                Transition<Boolean> transition3 = transition;
                Function3 transitionSpec$iv2 = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.TooltipKt$animateTooltip$2$alpha$2
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer composer, Integer num) {
                        return invoke(segment, composer, num.intValue());
                    }

                    public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> animateFloat, Composer $composer2, int $changed4) {
                        TweenSpec tweenSpecTween$default;
                        Intrinsics.checkNotNullParameter(animateFloat, "$this$animateFloat");
                        $composer2.startReplaceableGroup(-281714272);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-281714272, $changed4, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:720)");
                        }
                        if (animateFloat.isTransitioningTo(false, true)) {
                            tweenSpecTween$default = AnimationSpecKt.tween$default(150, 0, EasingKt.getLinearEasing(), 2, null);
                        } else {
                            tweenSpecTween$default = AnimationSpecKt.tween$default(75, 0, EasingKt.getLinearEasing(), 2, null);
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        $composer2.endReplaceableGroup();
                        return tweenSpecTween$default;
                    }
                };
                $composer.startReplaceableGroup(-1338768149);
                ComposerKt.sourceInformation($composer, "CC(animateFloat)P(2)938@37489L78:Transition.kt#pdpnli");
                TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                int $changed$iv$iv2 = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
                $composer.startReplaceableGroup(-142660079);
                ComposerKt.sourceInformation($composer, "CC(animateValue)P(3,2)856@34079L32,857@34134L31,858@34190L23,860@34226L89:Transition.kt#pdpnli");
                int $changed4 = ($changed$iv$iv2 >> 9) & 112;
                boolean it3 = transition3.getCurrentState().booleanValue();
                $composer.startReplaceableGroup(2073045083);
                ComposerKt.sourceInformation($composer, "C:Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2073045083, $changed4, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:736)");
                }
                float f3 = it3 ? 1.0f : 0.0f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceableGroup();
                Object initialValue$iv$iv2 = Float.valueOf(f3);
                int $changed5 = ($changed$iv$iv2 >> 9) & 112;
                boolean it4 = transition3.getTargetState().booleanValue();
                $composer.startReplaceableGroup(2073045083);
                ComposerKt.sourceInformation($composer, "C:Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2073045083, $changed5, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:736)");
                }
                float f4 = it4 ? 1.0f : 0.0f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceableGroup();
                Object targetValue$iv$iv2 = Float.valueOf(f4);
                State alpha$delegate = TransitionKt.createTransitionAnimation(transition3, initialValue$iv$iv2, targetValue$iv$iv2, transitionSpec$iv2.invoke(transition3.getSegment(), $composer, Integer.valueOf(($changed$iv$iv2 >> 3) & 112)), vectorConverter2, "tooltip transition: alpha", $composer, ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344) | (($changed$iv$iv2 << 6) & 458752));
                $composer.endReplaceableGroup();
                $composer.endReplaceableGroup();
                Modifier modifierM3127graphicsLayerAp8cVGQ$default = GraphicsLayerModifierKt.m3127graphicsLayerAp8cVGQ$default(composed, invoke$lambda$1(scale$delegate), invoke$lambda$1(scale$delegate), invoke$lambda$3(alpha$delegate), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131064, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceableGroup();
                return modifierM3127graphicsLayerAp8cVGQ$default;
            }

            private static final float invoke$lambda$3(State<Float> state) {
                Object thisObj$iv = state.getValue();
                return ((Number) thisObj$iv).floatValue();
            }
        });
    }

    static {
        float fM5274constructorimpl = Dp.m5274constructorimpl(4);
        PlainTooltipVerticalPadding = fM5274constructorimpl;
        float fM5274constructorimpl2 = Dp.m5274constructorimpl(8);
        PlainTooltipHorizontalPadding = fM5274constructorimpl2;
        PlainTooltipContentPadding = PaddingKt.m481PaddingValuesYgX7TsA(fM5274constructorimpl2, fM5274constructorimpl);
        RichTooltipMaxWidth = Dp.m5274constructorimpl(320);
        RichTooltipHorizontalPadding = Dp.m5274constructorimpl(16);
        HeightToSubheadFirstLine = Dp.m5274constructorimpl(28);
        HeightFromSubheadToTextFirstLine = Dp.m5274constructorimpl(24);
        TextBottomPadding = Dp.m5274constructorimpl(16);
        ActionLabelMinHeight = Dp.m5274constructorimpl(36);
        ActionLabelBottomPadding = Dp.m5274constructorimpl(8);
    }

    public static final float getTooltipMinHeight() {
        return TooltipMinHeight;
    }

    public static final float getTooltipMinWidth() {
        return TooltipMinWidth;
    }

    public static final float getRichTooltipHorizontalPadding() {
        return RichTooltipHorizontalPadding;
    }
}
