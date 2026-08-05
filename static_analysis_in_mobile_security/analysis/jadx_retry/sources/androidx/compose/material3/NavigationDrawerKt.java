package androidx.compose.material3;

import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.material3.tokens.NavigationDrawerTokens;
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
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NavigationDrawer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\u001ao\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001aQ\u0010\u001a\u001a\u00020\b2\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\b0\u001c¢\u0006\u0002\b\u00162\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\b0\u001c¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010!\u001am\u0010\"\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00042\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001ao\u0010%\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010\u0019\u001ac\u0010'\u001a\u00020\b2\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\b0\u001c¢\u0006\u0002\b\u00162\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010(\u001a\u00020\u000e2\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\b0\u001c¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a\u008c\u0001\u0010+\u001a\u00020\b2\u0011\u0010,\u001a\r\u0012\u0004\u0012\u00020\b0\u001c¢\u0006\u0002\b\u00162\u0006\u0010-\u001a\u00020 2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\b0\u001c2\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010/\u001a\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010\u001c¢\u0006\u0002\b\u00162\u0015\b\u0002\u00100\u001a\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010\u001c¢\u0006\u0002\b\u00162\b\b\u0002\u00101\u001a\u00020\f2\b\b\u0002\u00102\u001a\u0002032\b\b\u0002\u00104\u001a\u000205H\u0007¢\u0006\u0002\u00106\u001ao\u00107\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b8\u0010\u0019\u001a=\u00109\u001a\u00020\b2\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\b0\u001c¢\u0006\u0002\b\u00162\b\b\u0002\u0010\t\u001a\u00020\n2\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\b0\u001c¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010:\u001aA\u0010;\u001a\u00020\b2\u0006\u0010<\u001a\u00020 2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0\u001c2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00020\u001c2\u0006\u0010?\u001a\u00020\u000eH\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a \u0010B\u001a\u00020\u00022\u0006\u0010C\u001a\u00020\u00022\u0006\u0010D\u001a\u00020\u00022\u0006\u0010E\u001a\u00020\u0002H\u0002\u001a+\u0010F\u001a\u00020\u001e2\u0006\u0010G\u001a\u00020H2\u0014\b\u0002\u0010I\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020 0\u0014H\u0007¢\u0006\u0002\u0010J\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0013\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006K"}, d2 = {"AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "DrawerVelocityThreshold", "Landroidx/compose/ui/unit/Dp;", "F", "MinimumDrawerWidth", "DismissibleDrawerSheet", "", "modifier", "Landroidx/compose/ui/Modifier;", "drawerShape", "Landroidx/compose/ui/graphics/Shape;", "drawerContainerColor", "Landroidx/compose/ui/graphics/Color;", "drawerContentColor", "drawerTonalElevation", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "DismissibleDrawerSheet-afqeVBk", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DismissibleNavigationDrawer", "drawerContent", "Lkotlin/Function0;", "drawerState", "Landroidx/compose/material3/DrawerState;", "gesturesEnabled", "", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DrawerState;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "DrawerSheet", "DrawerSheet-vywBR7E", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ModalDrawerSheet", "ModalDrawerSheet-afqeVBk", "ModalNavigationDrawer", "scrimColor", "ModalNavigationDrawer-FHprtrg", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DrawerState;ZJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "NavigationDrawerItem", "label", "selected", "onClick", "icon", "badge", "shape", "colors", "Landroidx/compose/material3/NavigationDrawerItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/NavigationDrawerItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "PermanentDrawerSheet", "PermanentDrawerSheet-afqeVBk", "PermanentNavigationDrawer", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Scrim", "open", "onClose", "fraction", "color", "Scrim-Bx497Mc", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;JLandroidx/compose/runtime/Composer;I)V", "calculateFraction", "a", "b", "pos", "rememberDrawerState", "initialValue", "Landroidx/compose/material3/DrawerValue;", "confirmStateChange", "(Landroidx/compose/material3/DrawerValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DrawerState;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NavigationDrawerKt {
    private static final float DrawerVelocityThreshold = Dp.m5274constructorimpl(400);
    private static final float MinimumDrawerWidth = Dp.m5274constructorimpl(240);
    private static final TweenSpec<Float> AnimationSpec = new TweenSpec<>(256, 0, null, 6, null);

    public static final DrawerState rememberDrawerState(final DrawerValue initialValue, final Function1<? super DrawerValue, Boolean> function1, Composer $composer, int $changed, int i) {
        Object value$iv$iv;
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        $composer.startReplaceableGroup(2098699222);
        ComposerKt.sourceInformation($composer, "C(rememberDrawerState)P(1)227@8145L61,227@8081L125:NavigationDrawer.kt#uh7d8r");
        if ((i & 2) != 0) {
            Function1 confirmStateChange = new Function1<DrawerValue, Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt.rememberDrawerState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(DrawerValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
            function1 = confirmStateChange;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2098699222, $changed, -1, "androidx.compose.material3.rememberDrawerState (NavigationDrawer.kt:223)");
        }
        Object[] objArr = new Object[0];
        Saver<DrawerState, DrawerValue> Saver = DrawerState.Companion.Saver(function1);
        int i2 = ($changed & 14) | ($changed & 112);
        $composer.startReplaceableGroup(511388516);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(initialValue) | $composer.changed(function1);
        Object it$iv$iv = $composer.rememberedValue();
        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new Function0<DrawerState>() { // from class: androidx.compose.material3.NavigationDrawerKt$rememberDrawerState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final DrawerState invoke() {
                    return new DrawerState(initialValue, function1);
                }
            };
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        $composer.endReplaceableGroup();
        DrawerState drawerState = (DrawerState) RememberSaveableKt.m2617rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv$iv, $composer, 72, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return drawerState;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0175  */
    /* JADX WARN: Code duplicated, block: B:102:0x0194  */
    /* JADX WARN: Code duplicated, block: B:105:0x021b  */
    /* JADX WARN: Code duplicated, block: B:106:0x021e  */
    /* JADX WARN: Code duplicated, block: B:109:0x0306  */
    /* JADX WARN: Code duplicated, block: B:112:0x0312  */
    /* JADX WARN: Code duplicated, block: B:113:0x0316  */
    /* JADX WARN: Code duplicated, block: B:116:0x0452  */
    /* JADX WARN: Code duplicated, block: B:119:0x045e  */
    /* JADX WARN: Code duplicated, block: B:120:0x0462  */
    /* JADX WARN: Code duplicated, block: B:123:0x0549  */
    /* JADX WARN: Code duplicated, block: B:126:0x0554  */
    /* JADX WARN: Code duplicated, block: B:127:0x055f  */
    /* JADX WARN: Code duplicated, block: B:131:0x05b5  */
    /* JADX WARN: Code duplicated, block: B:135:0x05c3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:139:0x0692  */
    /* JADX WARN: Code duplicated, block: B:142:0x069e  */
    /* JADX WARN: Code duplicated, block: B:143:0x06a2  */
    /* JADX WARN: Code duplicated, block: B:146:0x075f  */
    /* JADX WARN: Code duplicated, block: B:151:0x076c  */
    /* JADX WARN: Code duplicated, block: B:153:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:84:0x0107 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:85:0x0109  */
    /* JADX WARN: Code duplicated, block: B:86:0x010e  */
    /* JADX WARN: Code duplicated, block: B:89:0x0113  */
    /* JADX WARN: Code duplicated, block: B:90:0x011c  */
    /* JADX WARN: Code duplicated, block: B:92:0x011f  */
    /* JADX WARN: Code duplicated, block: B:95:0x0125  */
    /* JADX WARN: Code duplicated, block: B:98:0x0139  */
    /* JADX INFO: renamed from: ModalNavigationDrawer-FHprtrg, reason: not valid java name */
    public static final void m1633ModalNavigationDrawerFHprtrg(final Function2<? super Composer, ? super Integer, Unit> drawerContent, Modifier modifier, DrawerState drawerState, boolean gesturesEnabled, long scrimColor, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        DrawerState drawerState2;
        final boolean gesturesEnabled2;
        long scrimColor2;
        int i2;
        Modifier.Companion modifier3;
        final DrawerState drawerState3;
        Object it$iv$iv$iv;
        Object value$iv$iv$iv;
        float minValue;
        Object objConsume;
        boolean isRtl;
        Modifier modifier4;
        long scrimColor3;
        Function0<ComposeUiNode> constructor;
        Composer $composer2;
        Function0<ComposeUiNode> constructor2;
        boolean invalid$iv$iv;
        Object value$iv$iv;
        final float minValue2;
        final float maxValue;
        boolean invalid$iv$iv2;
        Object value$iv$iv2;
        Function0<ComposeUiNode> constructor3;
        DrawerState drawerState4;
        Object key2$iv;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(drawerContent, "drawerContent");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-1169303680);
        ComposerKt.sourceInformation($composer3, "C(ModalNavigationDrawer)P(1,4,2,3,5:c#ui.graphics.Color)256@9399L39,258@9516L10,261@9584L24,262@9634L33,*263@9706L7,267@9910L7,268@9945L1722:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changedInstance(drawerContent) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                drawerState2 = drawerState;
                int i4 = $composer3.changed(drawerState2) ? 256 : 128;
                $dirty |= i4;
            } else {
                drawerState2 = drawerState;
            }
            $dirty |= i4;
        } else {
            drawerState2 = drawerState;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            gesturesEnabled2 = gesturesEnabled;
        } else if (($changed & 7168) == 0) {
            gesturesEnabled2 = gesturesEnabled;
            $dirty |= $composer3.changed(gesturesEnabled2) ? 2048 : 1024;
        } else {
            gesturesEnabled2 = gesturesEnabled;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                scrimColor2 = scrimColor;
                int i6 = $composer3.changed(scrimColor2) ? 16384 : 8192;
                $dirty |= i6;
            } else {
                scrimColor2 = scrimColor;
            }
            $dirty |= i6;
        } else {
            scrimColor2 = scrimColor;
        }
        if ((i & 32) == 0) {
            if ((458752 & $changed) == 0) {
                i2 = $composer3.changedInstance(content) ? 131072 : 65536;
            }
            if ((374491 & $dirty) == 74898 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if ((i & 4) != 0) {
                        drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                        $dirty &= -897;
                    } else {
                        drawerState3 = drawerState2;
                    }
                    if (i5 != 0) {
                        gesturesEnabled2 = true;
                    }
                    if ((i & 16) != 0) {
                        $dirty &= -57345;
                        scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty &= -897;
                    }
                    if ((i & 16) != 0) {
                        $dirty &= -57345;
                    }
                    modifier3 = modifier2;
                    drawerState3 = drawerState2;
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1169303680, $dirty, -1, "androidx.compose.material3.ModalNavigationDrawer (NavigationDrawer.kt:253)");
                }
                $composer3.startReplaceableGroup(773894976);
                ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
                $composer3.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv$iv = $composer3.rememberedValue();
                if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                    $composer3.updateRememberedValue(value$iv$iv$iv);
                } else {
                    value$iv$iv$iv = it$iv$iv$iv;
                }
                $composer3.endReplaceableGroup();
                CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
                final CoroutineScope scope = wrapper$iv.getCoroutineScope();
                $composer3.endReplaceableGroup();
                final String navigationMenu = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1777getNavigationMenuadMyvUU(), $composer3, 6);
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer3.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density $this$ModalNavigationDrawer_FHprtrg_u24lambda_u241 = (Density) objConsume2;
                minValue = -$this$ModalNavigationDrawer_FHprtrg_u24lambda_u241.mo327toPx0680j_4(NavigationDrawerTokens.INSTANCE.m2302getContainerWidthD9Ej5fM());
                Map anchors = MapsKt.mapOf(TuplesKt.to(Float.valueOf(minValue), DrawerValue.Closed), TuplesKt.to(Float.valueOf(0.0f), DrawerValue.Open));
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                objConsume = $composer3.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (objConsume == LayoutDirection.Rtl) {
                    isRtl = true;
                } else {
                    isRtl = false;
                }
                Modifier modifier$iv = SwipeableKt.m1818swipeablepPrIpRY(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), drawerState3.getSwipeableState$material3_release(), anchors, Orientation.Horizontal, (32 & 8) != 0 ? true : gesturesEnabled2, (32 & 16) != 0 ? false : isRtl, (32 & 32) != 0 ? null : null, (32 & 64) != 0 ? SwipeableKt$swipeable$1.INSTANCE : new Function2<DrawerValue, DrawerValue, ThresholdConfig>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$1
                    @Override // kotlin.jvm.functions.Function2
                    public final ThresholdConfig invoke(DrawerValue drawerValue, DrawerValue drawerValue2) {
                        Intrinsics.checkNotNullParameter(drawerValue, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(drawerValue2, "<anonymous parameter 1>");
                        return new FractionalThreshold(0.5f);
                    }
                }, (32 & 128) != 0 ? SwipeableDefaults.resistanceConfig$material3_release$default(SwipeableDefaults.INSTANCE, anchors.keySet(), 0.0f, 0.0f, 6, null) : null, (32 & 256) != 0 ? SwipeableDefaults.INSTANCE.m1817getVelocityThresholdD9Ej5fM$material3_release() : DrawerVelocityThreshold);
                modifier4 = modifier3;
                $composer3.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv = (0 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                scrimColor3 = scrimColor2;
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer3.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density density$iv$iv = (Density) objConsume3;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume4 = $composer3.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume5 = $composer3.consume(localViewConfiguration);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                int i7 = ($changed$iv$iv$iv >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i8 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 2009205847, "C282@10437L37,295@10832L95,285@10483L486,302@11028L55,300@10978L683:NavigationDrawer.kt#uh7d8r");
                $composer3.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Modifier modifier$iv2 = Modifier.INSTANCE;
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                int $changed$iv = ((0 >> 3) & 14) | ((0 >> 3) & 112);
                MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer3, $changed$iv);
                int $changed$iv$iv2 = (0 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume6 = $composer3.consume(localDensity3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density density$iv$iv2 = (Density) objConsume6;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                $composer2 = $composer3;
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume7 = $composer3.consume(localLayoutDirection3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume7;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume8 = $composer3.consume(localViewConfiguration2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume8;
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
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
                int i9 = ($changed$iv$iv$iv2 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i10 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -860470390, "C283@10455L9:NavigationDrawer.kt#uh7d8r");
                content.invoke($composer3, Integer.valueOf(($dirty >> 15) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                boolean zIsOpen = drawerState3.isOpen();
                Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2
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
                        if (gesturesEnabled2 && drawerState3.getSwipeableState$material3_release().getConfirmStateChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(drawerState3, null), 3, null);
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1, reason: invalid class name */
                    /* JADX INFO: compiled from: NavigationDrawer.kt */
                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1", f = "NavigationDrawer.kt", i = {}, l = {293}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ DrawerState $drawerState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(DrawerState drawerState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$drawerState = drawerState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$drawerState, continuation);
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
                                    if (this.$drawerState.close(this) == coroutine_suspended) {
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
                Object key1$iv = Float.valueOf(minValue);
                Object key2$iv2 = Float.valueOf(0.0f);
                int i11 = ($dirty & 896) | 48;
                $composer3.startReplaceableGroup(1618982084);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                invalid$iv$iv = $composer3.changed(key1$iv) | $composer3.changed(key2$iv2) | $composer3.changed(drawerState3);
                value$iv$iv = $composer3.rememberedValue();
                if (!invalid$iv$iv) {
                    key2$iv = Composer.INSTANCE.getEmpty();
                    if (value$iv$iv != key2$iv) {
                        minValue2 = minValue;
                        maxValue = 0.0f;
                    }
                    $composer3.endReplaceableGroup();
                    Object key1$iv2 = value$iv$iv;
                    m1635ScrimBx497Mc(zIsOpen, function0, (Function0) key1$iv2, scrimColor3, $composer3, ($dirty >> 3) & 7168);
                    Modifier.Companion companion = Modifier.INSTANCE;
                    int i12 = ($dirty >> 6) & 14;
                    $composer3.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                    invalid$iv$iv2 = $composer3.changed(drawerState3);
                    value$iv$iv2 = $composer3.rememberedValue();
                    if (!invalid$iv$iv2 || value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv2 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$4$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ IntOffset invoke(Density density) {
                                return IntOffset.m5383boximpl(m1638invokeBjo55l4(density));
                            }

                            /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                            public final long m1638invokeBjo55l4(Density offset) {
                                Intrinsics.checkNotNullParameter(offset, "$this$offset");
                                return IntOffsetKt.IntOffset(MathKt.roundToInt(drawerState3.getOffset().getValue().floatValue()), 0);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv$iv2);
                    }
                    $composer3.endReplaceableGroup();
                    Modifier modifier$iv3 = SemanticsModifierKt.semantics$default(OffsetKt.offset(companion, (Function1) value$iv$iv2), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5
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
                            SemanticsPropertiesKt.setPaneTitle(semantics, navigationMenu);
                            if (drawerState3.isOpen()) {
                                final DrawerState drawerState5 = drawerState3;
                                final CoroutineScope coroutineScope = scope;
                                SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        if (drawerState5.getSwipeableState$material3_release().getConfirmStateChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00871(drawerState5, null), 3, null);
                                        }
                                        return true;
                                    }

                                    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1, reason: invalid class name and collision with other inner class name */
                                    /* JADX INFO: compiled from: NavigationDrawer.kt */
                                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                    @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1", f = "NavigationDrawer.kt", i = {}, l = {312}, m = "invokeSuspend", n = {}, s = {})
                                    static final class C00871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ DrawerState $drawerState;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        C00871(DrawerState drawerState, Continuation<? super C00871> continuation) {
                                            super(2, continuation);
                                            this.$drawerState = drawerState;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new C00871(this.$drawerState, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((C00871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object $result) {
                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            switch (this.label) {
                                                case 0:
                                                    ResultKt.throwOnFailure($result);
                                                    this.label = 1;
                                                    if (this.$drawerState.close(this) == coroutine_suspended) {
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
                            }
                        }
                    }, 1, null);
                    $composer3.startReplaceableGroup(733328855);
                    ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv3 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv3, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv3 = (0 << 3) & 112;
                    $composer3.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                    ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                    DrawerState drawerState5 = drawerState3;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume9 = $composer3.consume(localDensity4);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Density density$iv$iv3 = (Density) objConsume9;
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume10 = $composer3.consume(localLayoutDirection4);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    LayoutDirection layoutDirection$iv$iv3 = (LayoutDirection) objConsume10;
                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration3 = CompositionLocalsKt.getLocalViewConfiguration();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume11 = $composer3.consume(localViewConfiguration3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ViewConfiguration viewConfiguration$iv$iv3 = (ViewConfiguration) objConsume11;
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf3 = LayoutKt.materializerOf(modifier$iv3);
                    int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        $composer3.createNode(constructor3);
                    } else {
                        $composer3.useNode();
                    }
                    $composer3.disableReusing();
                    Composer $this$Layout_u24lambda_u2d0$iv$iv3 = Updater.m2603constructorimpl($composer3);
                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, density$iv$iv3, ComposeUiNode.INSTANCE.getSetDensity());
                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, layoutDirection$iv$iv3, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, viewConfiguration$iv$iv3, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                    $composer3.enableReusing();
                    function3MaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                    $composer3.startReplaceableGroup(2058660585);
                    int i13 = ($changed$iv$iv$iv3 >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    int i14 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -860469209, "C317@11636L15:NavigationDrawer.kt#uh7d8r");
                    drawerContent.invoke($composer3, Integer.valueOf($dirty & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endReplaceableGroup();
                    $composer2.endNode();
                    $composer2.endReplaceableGroup();
                    $composer2.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    drawerState4 = drawerState5;
                }
                minValue2 = minValue;
                maxValue = 0.0f;
                value$iv$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(NavigationDrawerKt.calculateFraction(minValue2, maxValue, drawerState3.getOffset().getValue().floatValue()));
                    }
                };
                $composer3.updateRememberedValue(value$iv$iv);
                $composer3.endReplaceableGroup();
                Object key1$iv3 = value$iv$iv;
                m1635ScrimBx497Mc(zIsOpen, function0, (Function0) key1$iv3, scrimColor3, $composer3, ($dirty >> 3) & 7168);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                int i15 = ($dirty >> 6) & 14;
                $composer3.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv2 = $composer3.changed(drawerState3);
                value$iv$iv2 = $composer3.rememberedValue();
                if (!invalid$iv$iv2) {
                }
                value$iv$iv2 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$4$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ IntOffset invoke(Density density) {
                        return IntOffset.m5383boximpl(m1638invokeBjo55l4(density));
                    }

                    /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                    public final long m1638invokeBjo55l4(Density offset) {
                        Intrinsics.checkNotNullParameter(offset, "$this$offset");
                        return IntOffsetKt.IntOffset(MathKt.roundToInt(drawerState3.getOffset().getValue().floatValue()), 0);
                    }
                };
                $composer3.updateRememberedValue(value$iv$iv2);
                $composer3.endReplaceableGroup();
                Modifier modifier$iv4 = SemanticsModifierKt.semantics$default(OffsetKt.offset(companion2, (Function1) value$iv$iv2), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5
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
                        SemanticsPropertiesKt.setPaneTitle(semantics, navigationMenu);
                        if (drawerState3.isOpen()) {
                            final DrawerState drawerState6 = drawerState3;
                            final CoroutineScope coroutineScope = scope;
                            SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    if (drawerState6.getSwipeableState$material3_release().getConfirmStateChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00871(drawerState6, null), 3, null);
                                    }
                                    return true;
                                }

                                /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1, reason: invalid class name and collision with other inner class name */
                                /* JADX INFO: compiled from: NavigationDrawer.kt */
                                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1", f = "NavigationDrawer.kt", i = {}, l = {312}, m = "invokeSuspend", n = {}, s = {})
                                static final class C00871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ DrawerState $drawerState;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C00871(DrawerState drawerState, Continuation<? super C00871> continuation) {
                                        super(2, continuation);
                                        this.$drawerState = drawerState;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new C00871(this.$drawerState, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((C00871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object $result) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch (this.label) {
                                            case 0:
                                                ResultKt.throwOnFailure($result);
                                                this.label = 1;
                                                if (this.$drawerState.close(this) == coroutine_suspended) {
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
                        }
                    }
                }, 1, null);
                $composer3.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv4 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv4, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv4 = (0 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                DrawerState drawerState6 = drawerState3;
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume12 = $composer3.consume(localDensity5);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density density$iv$iv4 = (Density) objConsume12;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection5 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume13 = $composer3.consume(localLayoutDirection5);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                LayoutDirection layoutDirection$iv$iv4 = (LayoutDirection) objConsume13;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration4 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume14 = $composer3.consume(localViewConfiguration4);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ViewConfiguration viewConfiguration$iv$iv4 = (ViewConfiguration) objConsume14;
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf4 = LayoutKt.materializerOf(modifier$iv4);
                int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 9) & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor3);
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
                int i16 = ($changed$iv$iv$iv4 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                int i17 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -860469209, "C317@11636L15:NavigationDrawer.kt#uh7d8r");
                drawerContent.invoke($composer3, Integer.valueOf($dirty & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                drawerState4 = drawerState6;
            } else {
                $composer3.skipToGroupEnd();
                modifier4 = modifier2;
                gesturesEnabled2 = gesturesEnabled2;
                scrimColor3 = scrimColor2;
                $composer2 = $composer3;
                drawerState4 = drawerState2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final DrawerState drawerState7 = drawerState4;
            final boolean z = gesturesEnabled2;
            final long j = scrimColor3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$3
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

                public final void invoke(Composer composer, int i18) {
                    NavigationDrawerKt.m1633ModalNavigationDrawerFHprtrg(drawerContent, modifier5, drawerState7, z, j, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty |= i2;
        if ((374491 & $dirty) == 74898) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1169303680, $dirty, -1, "androidx.compose.material3.ModalNavigationDrawer (NavigationDrawer.kt:253)");
            }
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv$iv);
            } else {
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer3.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv2 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope2 = wrapper$iv2.getCoroutineScope();
            $composer3.endReplaceableGroup();
            final String navigationMenu2 = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1777getNavigationMenuadMyvUU(), $composer3, 6);
            ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume15 = $composer3.consume(localDensity6);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$ModalNavigationDrawer_FHprtrg_u24lambda_u242 = (Density) objConsume15;
            minValue = -$this$ModalNavigationDrawer_FHprtrg_u24lambda_u242.mo327toPx0680j_4(NavigationDrawerTokens.INSTANCE.m2302getContainerWidthD9Ej5fM());
            Map anchors2 = MapsKt.mapOf(TuplesKt.to(Float.valueOf(minValue), DrawerValue.Closed), TuplesKt.to(Float.valueOf(0.0f), DrawerValue.Open));
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection6 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            objConsume = $composer3.consume(localLayoutDirection6);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (objConsume == LayoutDirection.Rtl) {
                isRtl = true;
            } else {
                isRtl = false;
            }
            Modifier modifier$iv5 = SwipeableKt.m1818swipeablepPrIpRY(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), drawerState3.getSwipeableState$material3_release(), anchors2, Orientation.Horizontal, (32 & 8) != 0 ? true : gesturesEnabled2, (32 & 16) != 0 ? false : isRtl, (32 & 32) != 0 ? null : null, (32 & 64) != 0 ? SwipeableKt$swipeable$1.INSTANCE : new Function2<DrawerValue, DrawerValue, ThresholdConfig>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$1
                @Override // kotlin.jvm.functions.Function2
                public final ThresholdConfig invoke(DrawerValue drawerValue, DrawerValue drawerValue2) {
                    Intrinsics.checkNotNullParameter(drawerValue, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(drawerValue2, "<anonymous parameter 1>");
                    return new FractionalThreshold(0.5f);
                }
            }, (32 & 128) != 0 ? SwipeableDefaults.resistanceConfig$material3_release$default(SwipeableDefaults.INSTANCE, anchors2.keySet(), 0.0f, 0.0f, 6, null) : null, (32 & 256) != 0 ? SwipeableDefaults.INSTANCE.m1817getVelocityThresholdD9Ej5fM$material3_release() : DrawerVelocityThreshold);
            modifier4 = modifier3;
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv5 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv5 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv5, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv5 = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
            scrimColor3 = scrimColor2;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume16 = $composer3.consume(localDensity7);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv5 = (Density) objConsume16;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection7 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume17 = $composer3.consume(localLayoutDirection7);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv5 = (LayoutDirection) objConsume17;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration5 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume18 = $composer3.consume(localViewConfiguration5);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv5 = (ViewConfiguration) objConsume18;
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf5 = LayoutKt.materializerOf(modifier$iv5);
            int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 9) & 7168) | 6;
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
            Composer $this$Layout_u24lambda_u2d0$iv$iv5 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv5, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv5, density$iv$iv5, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv5, layoutDirection$iv$iv5, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv5, viewConfiguration$iv$iv5, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf5.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv5 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i18 = ($changed$iv$iv$iv5 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
            int i19 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 2009205847, "C282@10437L37,295@10832L95,285@10483L486,302@11028L55,300@10978L683:NavigationDrawer.kt#uh7d8r");
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Modifier modifier$iv6 = Modifier.INSTANCE;
            Alignment contentAlignment$iv6 = Alignment.INSTANCE.getTopStart();
            int $changed$iv2 = ((0 >> 3) & 14) | ((0 >> 3) & 112);
            MeasurePolicy measurePolicy$iv6 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv6, false, $composer3, $changed$iv2);
            int $changed$iv$iv6 = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume19 = $composer3.consume(localDensity8);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv6 = (Density) objConsume19;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection8 = CompositionLocalsKt.getLocalLayoutDirection();
            $composer2 = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume20 = $composer3.consume(localLayoutDirection8);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv6 = (LayoutDirection) objConsume20;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration6 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume21 = $composer3.consume(localViewConfiguration6);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv6 = (ViewConfiguration) objConsume21;
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf6 = LayoutKt.materializerOf(modifier$iv6);
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
            int i20 = ($changed$iv$iv$iv6 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
            int i110 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -860470390, "C283@10455L9:NavigationDrawer.kt#uh7d8r");
            content.invoke($composer3, Integer.valueOf(($dirty >> 15) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            boolean zIsOpen2 = drawerState3.isOpen();
            Function0<Unit> function1 = new Function0<Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2
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
                    if (gesturesEnabled2 && drawerState3.getSwipeableState$material3_release().getConfirmStateChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                        BuildersKt__Builders_commonKt.launch$default(scope2, null, null, new AnonymousClass1(drawerState3, null), 3, null);
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: NavigationDrawer.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1", f = "NavigationDrawer.kt", i = {}, l = {293}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ DrawerState $drawerState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(DrawerState drawerState, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$drawerState = drawerState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$drawerState, continuation);
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
                                if (this.$drawerState.close(this) == coroutine_suspended) {
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
            Object key1$iv4 = Float.valueOf(minValue);
            Object key2$iv3 = Float.valueOf(0.0f);
            int i111 = ($dirty & 896) | 48;
            $composer3.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(key1$iv4) | $composer3.changed(key2$iv3) | $composer3.changed(drawerState3);
            value$iv$iv = $composer3.rememberedValue();
            if (!invalid$iv$iv) {
                key2$iv = Composer.INSTANCE.getEmpty();
                if (value$iv$iv != key2$iv) {
                    minValue2 = minValue;
                    maxValue = 0.0f;
                }
                $composer3.endReplaceableGroup();
                Object key1$iv5 = value$iv$iv;
                m1635ScrimBx497Mc(zIsOpen2, function1, (Function0) key1$iv5, scrimColor3, $composer3, ($dirty >> 3) & 7168);
                Modifier.Companion companion3 = Modifier.INSTANCE;
                int i112 = ($dirty >> 6) & 14;
                $composer3.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv2 = $composer3.changed(drawerState3);
                value$iv$iv2 = $composer3.rememberedValue();
                if (!invalid$iv$iv2) {
                }
                value$iv$iv2 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$4$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ IntOffset invoke(Density density) {
                        return IntOffset.m5383boximpl(m1638invokeBjo55l4(density));
                    }

                    /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                    public final long m1638invokeBjo55l4(Density offset) {
                        Intrinsics.checkNotNullParameter(offset, "$this$offset");
                        return IntOffsetKt.IntOffset(MathKt.roundToInt(drawerState3.getOffset().getValue().floatValue()), 0);
                    }
                };
                $composer3.updateRememberedValue(value$iv$iv2);
                $composer3.endReplaceableGroup();
                Modifier modifier$iv7 = SemanticsModifierKt.semantics$default(OffsetKt.offset(companion3, (Function1) value$iv$iv2), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5
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
                        SemanticsPropertiesKt.setPaneTitle(semantics, navigationMenu2);
                        if (drawerState3.isOpen()) {
                            final DrawerState drawerState8 = drawerState3;
                            final CoroutineScope coroutineScope = scope2;
                            SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    if (drawerState8.getSwipeableState$material3_release().getConfirmStateChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00871(drawerState8, null), 3, null);
                                    }
                                    return true;
                                }

                                /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1, reason: invalid class name and collision with other inner class name */
                                /* JADX INFO: compiled from: NavigationDrawer.kt */
                                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1", f = "NavigationDrawer.kt", i = {}, l = {312}, m = "invokeSuspend", n = {}, s = {})
                                static final class C00871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ DrawerState $drawerState;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C00871(DrawerState drawerState, Continuation<? super C00871> continuation) {
                                        super(2, continuation);
                                        this.$drawerState = drawerState;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new C00871(this.$drawerState, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((C00871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object $result) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch (this.label) {
                                            case 0:
                                                ResultKt.throwOnFailure($result);
                                                this.label = 1;
                                                if (this.$drawerState.close(this) == coroutine_suspended) {
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
                        }
                    }
                }, 1, null);
                $composer3.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv7 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv7 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv7, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv7 = (0 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity9 = CompositionLocalsKt.getLocalDensity();
                DrawerState drawerState8 = drawerState3;
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume110 = $composer3.consume(localDensity9);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density density$iv$iv7 = (Density) objConsume110;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection9 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume111 = $composer3.consume(localLayoutDirection9);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                LayoutDirection layoutDirection$iv$iv7 = (LayoutDirection) objConsume111;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration7 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume112 = $composer3.consume(localViewConfiguration7);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ViewConfiguration viewConfiguration$iv$iv7 = (ViewConfiguration) objConsume112;
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf7 = LayoutKt.materializerOf(modifier$iv7);
                int $changed$iv$iv$iv7 = (($changed$iv$iv7 << 9) & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor3);
                } else {
                    $composer3.useNode();
                }
                $composer3.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv7 = Updater.m2603constructorimpl($composer3);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv7, measurePolicy$iv7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv7, density$iv$iv7, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv7, layoutDirection$iv$iv7, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv7, viewConfiguration$iv$iv7, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer3.enableReusing();
                function3MaterializerOf7.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv7 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i113 = ($changed$iv$iv$iv7 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                int i114 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -860469209, "C317@11636L15:NavigationDrawer.kt#uh7d8r");
                drawerContent.invoke($composer3, Integer.valueOf($dirty & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                drawerState4 = drawerState8;
            }
            minValue2 = minValue;
            maxValue = 0.0f;
            value$iv$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return Float.valueOf(NavigationDrawerKt.calculateFraction(minValue2, maxValue, drawerState3.getOffset().getValue().floatValue()));
                }
            };
            $composer3.updateRememberedValue(value$iv$iv);
            $composer3.endReplaceableGroup();
            Object key1$iv6 = value$iv$iv;
            m1635ScrimBx497Mc(zIsOpen2, function1, (Function0) key1$iv6, scrimColor3, $composer3, ($dirty >> 3) & 7168);
            Modifier.Companion companion4 = Modifier.INSTANCE;
            int i115 = ($dirty >> 6) & 14;
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer3.changed(drawerState3);
            value$iv$iv2 = $composer3.rememberedValue();
            if (!invalid$iv$iv2) {
            }
            value$iv$iv2 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$4$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntOffset invoke(Density density) {
                    return IntOffset.m5383boximpl(m1638invokeBjo55l4(density));
                }

                /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                public final long m1638invokeBjo55l4(Density offset) {
                    Intrinsics.checkNotNullParameter(offset, "$this$offset");
                    return IntOffsetKt.IntOffset(MathKt.roundToInt(drawerState3.getOffset().getValue().floatValue()), 0);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv2);
            $composer3.endReplaceableGroup();
            Modifier modifier$iv8 = SemanticsModifierKt.semantics$default(OffsetKt.offset(companion4, (Function1) value$iv$iv2), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5
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
                    SemanticsPropertiesKt.setPaneTitle(semantics, navigationMenu2);
                    if (drawerState3.isOpen()) {
                        final DrawerState drawerState9 = drawerState3;
                        final CoroutineScope coroutineScope = scope2;
                        SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                if (drawerState9.getSwipeableState$material3_release().getConfirmStateChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00871(drawerState9, null), 3, null);
                                }
                                return true;
                            }

                            /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1, reason: invalid class name and collision with other inner class name */
                            /* JADX INFO: compiled from: NavigationDrawer.kt */
                            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1", f = "NavigationDrawer.kt", i = {}, l = {312}, m = "invokeSuspend", n = {}, s = {})
                            static final class C00871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ DrawerState $drawerState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                C00871(DrawerState drawerState, Continuation<? super C00871> continuation) {
                                    super(2, continuation);
                                    this.$drawerState = drawerState;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new C00871(this.$drawerState, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((C00871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            this.label = 1;
                                            if (this.$drawerState.close(this) == coroutine_suspended) {
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
                    }
                }
            }, 1, null);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv8 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv8 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv8, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv8 = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity10 = CompositionLocalsKt.getLocalDensity();
            DrawerState drawerState9 = drawerState3;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume113 = $composer3.consume(localDensity10);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv8 = (Density) objConsume113;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection10 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume114 = $composer3.consume(localLayoutDirection10);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv8 = (LayoutDirection) objConsume114;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration8 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume115 = $composer3.consume(localViewConfiguration8);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv8 = (ViewConfiguration) objConsume115;
            constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf8 = LayoutKt.materializerOf(modifier$iv8);
            int $changed$iv$iv$iv8 = (($changed$iv$iv8 << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor3);
            } else {
                $composer3.useNode();
            }
            $composer3.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv8 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv8, measurePolicy$iv8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv8, density$iv$iv8, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv8, layoutDirection$iv$iv8, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv8, viewConfiguration$iv$iv8, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf8.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv8 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i116 = ($changed$iv$iv$iv8 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
            int i117 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -860469209, "C317@11636L15:NavigationDrawer.kt#uh7d8r");
            drawerContent.invoke($composer3, Integer.valueOf($dirty & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            drawerState4 = drawerState9;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1169303680, $dirty, -1, "androidx.compose.material3.ModalNavigationDrawer (NavigationDrawer.kt:253)");
            }
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv$iv);
            } else {
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer3.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv3 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope3 = wrapper$iv3.getCoroutineScope();
            $composer3.endReplaceableGroup();
            final String navigationMenu3 = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1777getNavigationMenuadMyvUU(), $composer3, 6);
            ProvidableCompositionLocal<Density> localDensity11 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume116 = $composer3.consume(localDensity11);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$ModalNavigationDrawer_FHprtrg_u24lambda_u243 = (Density) objConsume116;
            minValue = -$this$ModalNavigationDrawer_FHprtrg_u24lambda_u243.mo327toPx0680j_4(NavigationDrawerTokens.INSTANCE.m2302getContainerWidthD9Ej5fM());
            Map anchors3 = MapsKt.mapOf(TuplesKt.to(Float.valueOf(minValue), DrawerValue.Closed), TuplesKt.to(Float.valueOf(0.0f), DrawerValue.Open));
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            objConsume = $composer3.consume(localLayoutDirection11);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (objConsume == LayoutDirection.Rtl) {
                isRtl = true;
            } else {
                isRtl = false;
            }
            Modifier modifier$iv9 = SwipeableKt.m1818swipeablepPrIpRY(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), drawerState3.getSwipeableState$material3_release(), anchors3, Orientation.Horizontal, (32 & 8) != 0 ? true : gesturesEnabled2, (32 & 16) != 0 ? false : isRtl, (32 & 32) != 0 ? null : null, (32 & 64) != 0 ? SwipeableKt$swipeable$1.INSTANCE : new Function2<DrawerValue, DrawerValue, ThresholdConfig>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$1
                @Override // kotlin.jvm.functions.Function2
                public final ThresholdConfig invoke(DrawerValue drawerValue, DrawerValue drawerValue2) {
                    Intrinsics.checkNotNullParameter(drawerValue, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(drawerValue2, "<anonymous parameter 1>");
                    return new FractionalThreshold(0.5f);
                }
            }, (32 & 128) != 0 ? SwipeableDefaults.resistanceConfig$material3_release$default(SwipeableDefaults.INSTANCE, anchors3.keySet(), 0.0f, 0.0f, 6, null) : null, (32 & 256) != 0 ? SwipeableDefaults.INSTANCE.m1817getVelocityThresholdD9Ej5fM$material3_release() : DrawerVelocityThreshold);
            modifier4 = modifier3;
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv9 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv9 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv9, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv9 = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity12 = CompositionLocalsKt.getLocalDensity();
            scrimColor3 = scrimColor2;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume117 = $composer3.consume(localDensity12);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv9 = (Density) objConsume117;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection12 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume118 = $composer3.consume(localLayoutDirection12);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv9 = (LayoutDirection) objConsume118;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration9 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume119 = $composer3.consume(localViewConfiguration9);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv9 = (ViewConfiguration) objConsume119;
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf9 = LayoutKt.materializerOf(modifier$iv9);
            int $changed$iv$iv$iv9 = (($changed$iv$iv9 << 9) & 7168) | 6;
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
            Composer $this$Layout_u24lambda_u2d0$iv$iv9 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv9, measurePolicy$iv9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv9, density$iv$iv9, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv9, layoutDirection$iv$iv9, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv9, viewConfiguration$iv$iv9, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf9.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv9 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i118 = ($changed$iv$iv$iv9 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance9 = BoxScopeInstance.INSTANCE;
            int i119 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 2009205847, "C282@10437L37,295@10832L95,285@10483L486,302@11028L55,300@10978L683:NavigationDrawer.kt#uh7d8r");
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Modifier modifier$iv10 = Modifier.INSTANCE;
            Alignment contentAlignment$iv10 = Alignment.INSTANCE.getTopStart();
            int $changed$iv3 = ((0 >> 3) & 14) | ((0 >> 3) & 112);
            MeasurePolicy measurePolicy$iv10 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv10, false, $composer3, $changed$iv3);
            int $changed$iv$iv10 = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity13 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume120 = $composer3.consume(localDensity13);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv10 = (Density) objConsume120;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection13 = CompositionLocalsKt.getLocalLayoutDirection();
            $composer2 = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume22 = $composer3.consume(localLayoutDirection13);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv10 = (LayoutDirection) objConsume22;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration10 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume23 = $composer3.consume(localViewConfiguration10);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv10 = (ViewConfiguration) objConsume23;
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf10 = LayoutKt.materializerOf(modifier$iv10);
            int $changed$iv$iv$iv10 = (($changed$iv$iv10 << 9) & 7168) | 6;
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
            Composer $this$Layout_u24lambda_u2d0$iv$iv10 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv10, measurePolicy$iv10, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv10, density$iv$iv10, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv10, layoutDirection$iv$iv10, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv10, viewConfiguration$iv$iv10, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf10.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv10 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i21 = ($changed$iv$iv$iv10 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance10 = BoxScopeInstance.INSTANCE;
            int i1110 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -860470390, "C283@10455L9:NavigationDrawer.kt#uh7d8r");
            content.invoke($composer3, Integer.valueOf(($dirty >> 15) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            boolean zIsOpen3 = drawerState3.isOpen();
            Function0<Unit> function2 = new Function0<Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2
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
                    if (gesturesEnabled2 && drawerState3.getSwipeableState$material3_release().getConfirmStateChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                        BuildersKt__Builders_commonKt.launch$default(scope3, null, null, new AnonymousClass1(drawerState3, null), 3, null);
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: NavigationDrawer.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$2$1", f = "NavigationDrawer.kt", i = {}, l = {293}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ DrawerState $drawerState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(DrawerState drawerState, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$drawerState = drawerState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$drawerState, continuation);
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
                                if (this.$drawerState.close(this) == coroutine_suspended) {
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
            Object key1$iv7 = Float.valueOf(minValue);
            Object key2$iv4 = Float.valueOf(0.0f);
            int i1111 = ($dirty & 896) | 48;
            $composer3.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(key1$iv7) | $composer3.changed(key2$iv4) | $composer3.changed(drawerState3);
            value$iv$iv = $composer3.rememberedValue();
            if (!invalid$iv$iv) {
                key2$iv = Composer.INSTANCE.getEmpty();
                if (value$iv$iv != key2$iv) {
                    minValue2 = minValue;
                    maxValue = 0.0f;
                }
                $composer3.endReplaceableGroup();
                Object key1$iv8 = value$iv$iv;
                m1635ScrimBx497Mc(zIsOpen3, function2, (Function0) key1$iv8, scrimColor3, $composer3, ($dirty >> 3) & 7168);
                Modifier.Companion companion5 = Modifier.INSTANCE;
                int i1112 = ($dirty >> 6) & 14;
                $composer3.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv2 = $composer3.changed(drawerState3);
                value$iv$iv2 = $composer3.rememberedValue();
                if (!invalid$iv$iv2) {
                }
                value$iv$iv2 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$4$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ IntOffset invoke(Density density) {
                        return IntOffset.m5383boximpl(m1638invokeBjo55l4(density));
                    }

                    /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                    public final long m1638invokeBjo55l4(Density offset) {
                        Intrinsics.checkNotNullParameter(offset, "$this$offset");
                        return IntOffsetKt.IntOffset(MathKt.roundToInt(drawerState3.getOffset().getValue().floatValue()), 0);
                    }
                };
                $composer3.updateRememberedValue(value$iv$iv2);
                $composer3.endReplaceableGroup();
                Modifier modifier$iv11 = SemanticsModifierKt.semantics$default(OffsetKt.offset(companion5, (Function1) value$iv$iv2), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5
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
                        SemanticsPropertiesKt.setPaneTitle(semantics, navigationMenu3);
                        if (drawerState3.isOpen()) {
                            final DrawerState drawerState10 = drawerState3;
                            final CoroutineScope coroutineScope = scope3;
                            SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    if (drawerState10.getSwipeableState$material3_release().getConfirmStateChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00871(drawerState10, null), 3, null);
                                    }
                                    return true;
                                }

                                /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1, reason: invalid class name and collision with other inner class name */
                                /* JADX INFO: compiled from: NavigationDrawer.kt */
                                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1", f = "NavigationDrawer.kt", i = {}, l = {312}, m = "invokeSuspend", n = {}, s = {})
                                static final class C00871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ DrawerState $drawerState;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C00871(DrawerState drawerState, Continuation<? super C00871> continuation) {
                                        super(2, continuation);
                                        this.$drawerState = drawerState;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new C00871(this.$drawerState, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((C00871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object $result) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch (this.label) {
                                            case 0:
                                                ResultKt.throwOnFailure($result);
                                                this.label = 1;
                                                if (this.$drawerState.close(this) == coroutine_suspended) {
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
                        }
                    }
                }, 1, null);
                $composer3.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv11 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv11 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv11, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv11 = (0 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity14 = CompositionLocalsKt.getLocalDensity();
                DrawerState drawerState10 = drawerState3;
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume1110 = $composer3.consume(localDensity14);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density density$iv$iv11 = (Density) objConsume1110;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection14 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume1111 = $composer3.consume(localLayoutDirection14);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                LayoutDirection layoutDirection$iv$iv11 = (LayoutDirection) objConsume1111;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration11 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume1112 = $composer3.consume(localViewConfiguration11);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ViewConfiguration viewConfiguration$iv$iv11 = (ViewConfiguration) objConsume1112;
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf11 = LayoutKt.materializerOf(modifier$iv11);
                int $changed$iv$iv$iv11 = (($changed$iv$iv11 << 9) & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor3);
                } else {
                    $composer3.useNode();
                }
                $composer3.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv11 = Updater.m2603constructorimpl($composer3);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv11, measurePolicy$iv11, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv11, density$iv$iv11, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv11, layoutDirection$iv$iv11, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv11, viewConfiguration$iv$iv11, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer3.enableReusing();
                function3MaterializerOf11.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv11 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i1113 = ($changed$iv$iv$iv11 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance11 = BoxScopeInstance.INSTANCE;
                int i1114 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -860469209, "C317@11636L15:NavigationDrawer.kt#uh7d8r");
                drawerContent.invoke($composer3, Integer.valueOf($dirty & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                drawerState4 = drawerState10;
            }
            minValue2 = minValue;
            maxValue = 0.0f;
            value$iv$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return Float.valueOf(NavigationDrawerKt.calculateFraction(minValue2, maxValue, drawerState3.getOffset().getValue().floatValue()));
                }
            };
            $composer3.updateRememberedValue(value$iv$iv);
            $composer3.endReplaceableGroup();
            Object key1$iv9 = value$iv$iv;
            m1635ScrimBx497Mc(zIsOpen3, function2, (Function0) key1$iv9, scrimColor3, $composer3, ($dirty >> 3) & 7168);
            Modifier.Companion companion6 = Modifier.INSTANCE;
            int i1115 = ($dirty >> 6) & 14;
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer3.changed(drawerState3);
            value$iv$iv2 = $composer3.rememberedValue();
            if (!invalid$iv$iv2) {
            }
            value$iv$iv2 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$4$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntOffset invoke(Density density) {
                    return IntOffset.m5383boximpl(m1638invokeBjo55l4(density));
                }

                /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                public final long m1638invokeBjo55l4(Density offset) {
                    Intrinsics.checkNotNullParameter(offset, "$this$offset");
                    return IntOffsetKt.IntOffset(MathKt.roundToInt(drawerState3.getOffset().getValue().floatValue()), 0);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv2);
            $composer3.endReplaceableGroup();
            Modifier modifier$iv12 = SemanticsModifierKt.semantics$default(OffsetKt.offset(companion6, (Function1) value$iv$iv2), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5
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
                    SemanticsPropertiesKt.setPaneTitle(semantics, navigationMenu3);
                    if (drawerState3.isOpen()) {
                        final DrawerState drawerState11 = drawerState3;
                        final CoroutineScope coroutineScope = scope3;
                        SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                if (drawerState11.getSwipeableState$material3_release().getConfirmStateChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00871(drawerState11, null), 3, null);
                                }
                                return true;
                            }

                            /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1, reason: invalid class name and collision with other inner class name */
                            /* JADX INFO: compiled from: NavigationDrawer.kt */
                            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1", f = "NavigationDrawer.kt", i = {}, l = {312}, m = "invokeSuspend", n = {}, s = {})
                            static final class C00871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ DrawerState $drawerState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                C00871(DrawerState drawerState, Continuation<? super C00871> continuation) {
                                    super(2, continuation);
                                    this.$drawerState = drawerState;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new C00871(this.$drawerState, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((C00871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            this.label = 1;
                                            if (this.$drawerState.close(this) == coroutine_suspended) {
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
                    }
                }
            }, 1, null);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv12 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv12 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv12, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv12 = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity15 = CompositionLocalsKt.getLocalDensity();
            DrawerState drawerState11 = drawerState3;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume1113 = $composer3.consume(localDensity15);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv12 = (Density) objConsume1113;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection15 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume1114 = $composer3.consume(localLayoutDirection15);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv12 = (LayoutDirection) objConsume1114;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration12 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume1115 = $composer3.consume(localViewConfiguration12);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv12 = (ViewConfiguration) objConsume1115;
            constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf12 = LayoutKt.materializerOf(modifier$iv12);
            int $changed$iv$iv$iv12 = (($changed$iv$iv12 << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor3);
            } else {
                $composer3.useNode();
            }
            $composer3.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv12 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv12, measurePolicy$iv12, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv12, density$iv$iv12, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv12, layoutDirection$iv$iv12, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv12, viewConfiguration$iv$iv12, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf12.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv12 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i1116 = ($changed$iv$iv$iv12 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance12 = BoxScopeInstance.INSTANCE;
            int i1117 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -860469209, "C317@11636L15:NavigationDrawer.kt#uh7d8r");
            drawerContent.invoke($composer3, Integer.valueOf($dirty & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            drawerState4 = drawerState11;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final DrawerState drawerState12 = drawerState4;
        final boolean z2 = gesturesEnabled2;
        final long j2 = scrimColor3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$3
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

            public final void invoke(Composer composer, int i120) {
                NavigationDrawerKt.m1633ModalNavigationDrawerFHprtrg(drawerContent, modifier6, drawerState12, z2, j2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static final void DismissibleNavigationDrawer(final Function2<? super Composer, ? super Integer, Unit> drawerContent, Modifier modifier, DrawerState drawerState, boolean gesturesEnabled, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        DrawerState drawerState2;
        boolean gesturesEnabled2;
        Modifier.Companion modifier3;
        final DrawerState drawerState3;
        Object value$iv$iv$iv;
        boolean gesturesEnabled3;
        Composer $composer2;
        DrawerState drawerState4;
        Modifier modifier4;
        Intrinsics.checkNotNullParameter(drawerContent, "drawerContent");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(398812198);
        ComposerKt.sourceInformation($composer3, "C(DismissibleNavigationDrawer)P(1,4,2,3)347@12931L39,*352@13151L7,356@13256L24,357@13306L33,360@13464L7,361@13499L1566:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changedInstance(drawerContent) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                drawerState2 = drawerState;
                int i3 = $composer3.changed(drawerState2) ? 256 : 128;
                $dirty |= i3;
            } else {
                drawerState2 = drawerState;
            }
            $dirty |= i3;
        } else {
            drawerState2 = drawerState;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            gesturesEnabled2 = gesturesEnabled;
        } else if (($changed & 7168) == 0) {
            gesturesEnabled2 = gesturesEnabled;
            $dirty |= $composer3.changed(gesturesEnabled2) ? 2048 : 1024;
        } else {
            gesturesEnabled2 = gesturesEnabled;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if ((57344 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(content) ? 16384 : 8192;
        }
        if ((46811 & $dirty) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier4 = modifier2;
            drawerState4 = drawerState2;
            gesturesEnabled3 = gesturesEnabled2;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i4 != 0) {
                    gesturesEnabled2 = true;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
                drawerState3 = drawerState2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(398812198, $dirty, -1, "androidx.compose.material3.DismissibleNavigationDrawer (NavigationDrawer.kt:344)");
            }
            float drawerWidth = NavigationDrawerTokens.INSTANCE.m2302getContainerWidthD9Ej5fM();
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$DismissibleNavigationDrawer_u24lambda_u247 = (Density) objConsume;
            float drawerWidthPx = $this$DismissibleNavigationDrawer_u24lambda_u247.mo327toPx0680j_4(drawerWidth);
            float minValue = -drawerWidthPx;
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv$iv);
            } else {
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer3.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            $composer3.endReplaceableGroup();
            final String navigationMenu = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1777getNavigationMenuadMyvUU(), $composer3, 6);
            Map anchors = MapsKt.mapOf(TuplesKt.to(Float.valueOf(minValue), DrawerValue.Closed), TuplesKt.to(Float.valueOf(0.0f), DrawerValue.Open));
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            boolean isRtl = objConsume2 == LayoutDirection.Rtl;
            Modifier modifier5 = modifier3;
            Modifier modifier$iv = SwipeableKt.m1818swipeablepPrIpRY(modifier3, drawerState3.getSwipeableState$material3_release(), anchors, Orientation.Horizontal, (32 & 8) != 0 ? true : gesturesEnabled2, (32 & 16) != 0 ? false : isRtl, (32 & 32) != 0 ? null : null, (32 & 64) != 0 ? SwipeableKt$swipeable$1.INSTANCE : new Function2<DrawerValue, DrawerValue, ThresholdConfig>() { // from class: androidx.compose.material3.NavigationDrawerKt.DismissibleNavigationDrawer.1
                @Override // kotlin.jvm.functions.Function2
                public final ThresholdConfig invoke(DrawerValue drawerValue, DrawerValue drawerValue2) {
                    Intrinsics.checkNotNullParameter(drawerValue, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(drawerValue2, "<anonymous parameter 1>");
                    return new FractionalThreshold(0.5f);
                }
            }, (32 & 128) != 0 ? SwipeableDefaults.resistanceConfig$material3_release$default(SwipeableDefaults.INSTANCE, anchors.keySet(), 0.0f, 0.0f, 6, null) : null, (32 & 256) != 0 ? SwipeableDefaults.INSTANCE.m1817getVelocityThresholdD9Ej5fM$material3_release() : DrawerVelocityThreshold);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            gesturesEnabled3 = gesturesEnabled2;
            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv = (Density) objConsume3;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer3.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume5 = $composer3.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
            int $changed$iv$iv$iv = ((((0 << 3) & 112) << 9) & 7168) | 6;
            int $dirty2 = $dirty;
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
            int i5 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 973028062, "C373@13915L1144:NavigationDrawer.kt#uh7d8r");
            MeasurePolicy measurePolicy$iv2 = new MeasurePolicy() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$2
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i7) {
                    return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i7);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i7) {
                    return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i7);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i7) {
                    return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i7);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i7) {
                    return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i7);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo11measure3p2s80s(MeasureScope Layout, List<? extends Measurable> measurables, long constraints) {
                    Intrinsics.checkNotNullParameter(Layout, "$this$Layout");
                    Intrinsics.checkNotNullParameter(measurables, "measurables");
                    final Placeable sheetPlaceable = measurables.get(0).mo4225measureBRTryo0(constraints);
                    final Placeable contentPlaceable = measurables.get(1).mo4225measureBRTryo0(constraints);
                    int width = contentPlaceable.getWidth();
                    int height = contentPlaceable.getHeight();
                    final DrawerState drawerState5 = drawerState3;
                    return MeasureScope.CC.layout$default(Layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$2$measure$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            Placeable.PlacementScope.placeRelative$default(layout, contentPlaceable, sheetPlaceable.getWidth() + MathKt.roundToInt(drawerState5.getOffset().getValue().floatValue()), 0, 0.0f, 4, null);
                            Placeable.PlacementScope.placeRelative$default(layout, sheetPlaceable, MathKt.roundToInt(drawerState5.getOffset().getValue().floatValue()), 0, 0.0f, 4, null);
                        }
                    }, 4, null);
                }
            };
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)73@2855L7,74@2910L7,75@2969L7,76@2981L460:Layout.kt#80mrfh");
            Modifier modifier$iv2 = Modifier.INSTANCE;
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer3.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv = (Density) objConsume6;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            $composer2 = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume7 = $composer3.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv = (LayoutDirection) objConsume7;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume8 = $composer3.consume(localViewConfiguration2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv = (ViewConfiguration) objConsume8;
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
            int $changed$iv$iv = ((0 << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor2);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, density$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, layoutDirection$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, viewConfiguration$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i7 = ($changed$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 94146718, "C374@13946L523,389@14482L45:NavigationDrawer.kt#uh7d8r");
            drawerState4 = drawerState3;
            Modifier modifier$iv3 = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$1$1
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
                    SemanticsPropertiesKt.setPaneTitle(semantics, navigationMenu);
                    if (drawerState3.isOpen()) {
                        final DrawerState drawerState5 = drawerState3;
                        final CoroutineScope coroutineScope = scope;
                        SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                if (drawerState5.getSwipeableState$material3_release().getConfirmStateChange$material3_release().invoke(DrawerValue.Closed).booleanValue()) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00861(drawerState5, null), 3, null);
                                }
                                return true;
                            }

                            /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$1$1$1$1, reason: invalid class name and collision with other inner class name */
                            /* JADX INFO: compiled from: NavigationDrawer.kt */
                            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$1$1$1$1", f = "NavigationDrawer.kt", i = {}, l = {383}, m = "invokeSuspend", n = {}, s = {})
                            static final class C00861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ DrawerState $drawerState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                C00861(DrawerState drawerState, Continuation<? super C00861> continuation) {
                                    super(2, continuation);
                                    this.$drawerState = drawerState;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new C00861(this.$drawerState, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((C00861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object $result) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch (this.label) {
                                        case 0:
                                            ResultKt.throwOnFailure($result);
                                            this.label = 1;
                                            if (this.$drawerState.close(this) == coroutine_suspended) {
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
                    }
                }
            }, 1, null);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            int $i$f$Box = ((0 >> 3) & 14) | ((0 >> 3) & 112);
            MeasurePolicy measurePolicy$iv3 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer3, $i$f$Box);
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume9 = $composer3.consume(localDensity4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv2 = (Density) objConsume9;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume10 = $composer3.consume(localLayoutDirection4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume10;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration3 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume11 = $composer3.consume(localViewConfiguration3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume11;
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf3 = LayoutKt.materializerOf(modifier$iv3);
            int $changed$iv$iv$iv2 = ((((0 << 3) & 112) << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor3);
            } else {
                $composer3.useNode();
            }
            $composer3.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i8 = ($changed$iv$iv$iv2 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i9 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -817267027, "C387@14440L15:NavigationDrawer.kt#uh7d8r");
            drawerContent.invoke($composer3, Integer.valueOf($dirty2 & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Modifier modifier$iv4 = Modifier.INSTANCE;
            Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv4 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv3, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume12 = $composer3.consume(localDensity5);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv3 = (Density) objConsume12;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection5 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume13 = $composer3.consume(localLayoutDirection5);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv3 = (LayoutDirection) objConsume13;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration4 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume14 = $composer3.consume(localViewConfiguration4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv3 = (ViewConfiguration) objConsume14;
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf4 = LayoutKt.materializerOf(modifier$iv4);
            int $changed$iv$iv$iv3 = ((((0 << 3) & 112) << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor4);
            } else {
                $composer3.useNode();
            }
            $composer3.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv3 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, density$iv$iv3, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, layoutDirection$iv$iv3, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, viewConfiguration$iv$iv3, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf4.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i10 = ($changed$iv$iv$iv3 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i11 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -817266963, "C390@14504L9:NavigationDrawer.kt#uh7d8r");
            content.invoke($composer3, Integer.valueOf(($dirty2 >> 12) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final DrawerState drawerState5 = drawerState4;
        final boolean z = gesturesEnabled3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.DismissibleNavigationDrawer.3
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

            public final void invoke(Composer composer, int i12) {
                NavigationDrawerKt.DismissibleNavigationDrawer(drawerContent, modifier6, drawerState5, z, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static final void PermanentNavigationDrawer(final Function2<? super Composer, ? super Integer, Unit> drawerContent, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Intrinsics.checkNotNullParameter(drawerContent, "drawerContent");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-276843608);
        ComposerKt.sourceInformation($composer2, "C(PermanentNavigationDrawer)P(1,2)429@16096L105:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changedInstance(drawerContent) ? 4 : 2;
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
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 731) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-276843608, $dirty2, -1, "androidx.compose.material3.PermanentNavigationDrawer (NavigationDrawer.kt:424)");
            }
            Modifier modifier$iv = SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, null);
            $composer2.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation($composer2, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            Modifier modifier5 = modifier4;
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
            int i3 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -326682283, "C80@4021L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i4 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1153996105, "C430@16134L15,431@16158L37:NavigationDrawer.kt#uh7d8r");
            drawerContent.invoke($composer2, Integer.valueOf($dirty2 & 14));
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Modifier modifier$iv2 = Modifier.INSTANCE;
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            int $changed$iv = ((0 >> 3) & 14) | ((0 >> 3) & 112);
            MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, $changed$iv);
            int $changed$iv$iv2 = (0 << 3) & 112;
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
            int i5 = ($changed$iv$iv$iv2 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1146973058, "C432@16176L9:NavigationDrawer.kt#uh7d8r");
            content.invoke($composer2, Integer.valueOf(($dirty2 >> 6) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
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
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.PermanentNavigationDrawer.2
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

            public final void invoke(Composer composer, int i7) {
                NavigationDrawerKt.PermanentNavigationDrawer(drawerContent, modifier6, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:102:0x014b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:103:0x014d  */
    /* JADX WARN: Code duplicated, block: B:104:0x0152  */
    /* JADX WARN: Code duplicated, block: B:107:0x0158  */
    /* JADX WARN: Code duplicated, block: B:110:0x0165  */
    /* JADX WARN: Code duplicated, block: B:113:0x0175  */
    /* JADX WARN: Code duplicated, block: B:115:0x0181  */
    /* JADX WARN: Code duplicated, block: B:118:0x018c  */
    /* JADX WARN: Code duplicated, block: B:119:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:122:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:125:0x0205  */
    /* JADX WARN: Code duplicated, block: B:129:0x020f  */
    /* JADX WARN: Code duplicated, block: B:131:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x0103  */
    /* JADX WARN: Code duplicated, block: B:86:0x010d  */
    /* JADX INFO: renamed from: ModalDrawerSheet-afqeVBk, reason: not valid java name */
    public static final void m1632ModalDrawerSheetafqeVBk(Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        int i2;
        Modifier.Companion modifier3;
        Shape drawerShape3;
        long drawerContainerColor3;
        long drawerContentColor3;
        float drawerTonalElevation3;
        WindowInsets windowInsets3;
        Modifier modifier4;
        int $dirty;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1001163336);
        ComposerKt.sourceInformation($composer2, "C(ModalDrawerSheet)P(5,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp,6)456@17304L5,457@17359L11,458@17412L37,460@17567L12,463@17637L183:NavigationDrawer.kt#uh7d8r");
        int $dirty2 = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 14) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 112) == 0) {
            if ((i & 2) == 0) {
                drawerShape2 = drawerShape;
                int i4 = $composer2.changed(drawerShape2) ? 32 : 16;
                $dirty2 |= i4;
            } else {
                drawerShape2 = drawerShape;
            }
            $dirty2 |= i4;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i5 = $composer2.changed(drawerContainerColor2) ? 256 : 128;
                $dirty2 |= i5;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty2 |= i5;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i6 = $composer2.changed(drawerContentColor2) ? 2048 : 1024;
                $dirty2 |= i6;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty2 |= i6;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty2 |= 24576;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 57344) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty2 |= $composer2.changed(drawerTonalElevation2) ? 16384 : 8192;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i8 = $composer2.changed(windowInsets2) ? 131072 : 65536;
                $dirty2 |= i8;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i8;
        } else {
            windowInsets2 = windowInsets;
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
                        modifier3 = modifier2;
                    }
                    if ((i & 2) != 0) {
                        $dirty2 &= -113;
                        drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer2, 6);
                    }
                    if ((i & 4) != 0) {
                        drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                        $dirty2 &= -897;
                    }
                    if ((i & 8) != 0) {
                        drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                        $dirty2 &= -7169;
                    }
                    if (i7 != 0) {
                        drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1515getModalDrawerElevationD9Ej5fM();
                    }
                    if ((i & 32) != 0) {
                        modifier4 = modifier3;
                        windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                        drawerShape3 = drawerShape2;
                        drawerContainerColor3 = drawerContainerColor2;
                        drawerContentColor3 = drawerContentColor2;
                        drawerTonalElevation3 = drawerTonalElevation2;
                        $dirty = $dirty2 & (-458753);
                    } else {
                        drawerShape3 = drawerShape2;
                        drawerContainerColor3 = drawerContainerColor2;
                        drawerContentColor3 = drawerContentColor2;
                        drawerTonalElevation3 = drawerTonalElevation2;
                        windowInsets3 = windowInsets2;
                        modifier4 = modifier3;
                        $dirty = $dirty2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 2) != 0) {
                        $dirty2 &= -113;
                    }
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 32) != 0) {
                        drawerShape3 = drawerShape2;
                        drawerContainerColor3 = drawerContainerColor2;
                        drawerContentColor3 = drawerContentColor2;
                        drawerTonalElevation3 = drawerTonalElevation2;
                        windowInsets3 = windowInsets2;
                        $dirty = $dirty2 & (-458753);
                        modifier4 = modifier2;
                    } else {
                        drawerShape3 = drawerShape2;
                        drawerContainerColor3 = drawerContainerColor2;
                        drawerContentColor3 = drawerContentColor2;
                        drawerTonalElevation3 = drawerTonalElevation2;
                        windowInsets3 = windowInsets2;
                        $dirty = $dirty2;
                        modifier4 = modifier2;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1001163336, $dirty, -1, "androidx.compose.material3.ModalDrawerSheet (NavigationDrawer.kt:454)");
                }
                m1631DrawerSheetvywBR7E(windowInsets3, modifier4, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, content, $composer2, (($dirty >> 15) & 14) | (($dirty << 3) & 112) | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | ($dirty & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
                drawerShape3 = drawerShape2;
                drawerContainerColor3 = drawerContainerColor2;
                drawerContentColor3 = drawerContentColor2;
                drawerTonalElevation3 = drawerTonalElevation2;
                windowInsets3 = windowInsets2;
                modifier4 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final Shape shape = drawerShape3;
            final long j = drawerContainerColor3;
            final long j2 = drawerContentColor3;
            final float f = drawerTonalElevation3;
            final WindowInsets windowInsets4 = windowInsets3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalDrawerSheet$1
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
                    NavigationDrawerKt.m1632ModalDrawerSheetafqeVBk(modifier5, shape, j, j2, f, windowInsets4, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
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
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                    drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer2, 6);
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1515getModalDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    modifier4 = modifier3;
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    $dirty = $dirty2 & (-458753);
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                    drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer2, 6);
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1515getModalDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    modifier4 = modifier3;
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    $dirty = $dirty2 & (-458753);
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1001163336, $dirty, -1, "androidx.compose.material3.ModalDrawerSheet (NavigationDrawer.kt:454)");
            }
            m1631DrawerSheetvywBR7E(windowInsets3, modifier4, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, content, $composer2, (($dirty >> 15) & 14) | (($dirty << 3) & 112) | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | ($dirty & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                    drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer2, 6);
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1515getModalDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    modifier4 = modifier3;
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    $dirty = $dirty2 & (-458753);
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                    drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer2, 6);
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1515getModalDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    modifier4 = modifier3;
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    $dirty = $dirty2 & (-458753);
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1001163336, $dirty, -1, "androidx.compose.material3.ModalDrawerSheet (NavigationDrawer.kt:454)");
            }
            m1631DrawerSheetvywBR7E(windowInsets3, modifier4, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, content, $composer2, (($dirty >> 15) & 14) | (($dirty << 3) & 112) | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | ($dirty & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final Shape shape2 = drawerShape3;
        final long j3 = drawerContainerColor3;
        final long j4 = drawerContentColor3;
        final float f2 = drawerTonalElevation3;
        final WindowInsets windowInsets5 = windowInsets3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalDrawerSheet$1
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
                NavigationDrawerKt.m1632ModalDrawerSheetafqeVBk(modifier6, shape2, j3, j4, f2, windowInsets5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:100:0x014e  */
    /* JADX WARN: Code duplicated, block: B:101:0x0153  */
    /* JADX WARN: Code duplicated, block: B:103:0x0157  */
    /* JADX WARN: Code duplicated, block: B:106:0x0161  */
    /* JADX WARN: Code duplicated, block: B:109:0x0171  */
    /* JADX WARN: Code duplicated, block: B:111:0x017d  */
    /* JADX WARN: Code duplicated, block: B:114:0x0188  */
    /* JADX WARN: Code duplicated, block: B:115:0x019c  */
    /* JADX WARN: Code duplicated, block: B:118:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:121:0x0200  */
    /* JADX WARN: Code duplicated, block: B:125:0x020a  */
    /* JADX WARN: Code duplicated, block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x0108  */
    /* JADX WARN: Code duplicated, block: B:86:0x0112  */
    /* JADX WARN: Code duplicated, block: B:99:0x014c A[DONT_INVERT] */
    /* JADX INFO: renamed from: DismissibleDrawerSheet-afqeVBk, reason: not valid java name */
    public static final void m1630DismissibleDrawerSheetafqeVBk(Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        int i2;
        Modifier.Companion modifier2;
        Shape drawerShape3;
        long drawerContainerColor3;
        long drawerContentColor3;
        WindowInsets windowInsets3;
        float drawerTonalElevation3;
        Modifier modifier3;
        int $dirty;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-588600583);
        ComposerKt.sourceInformation($composer2, "C(DismissibleDrawerSheet)P(5,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp,6)494@18990L11,495@19043L37,497@19204L12,500@19274L183:NavigationDrawer.kt#uh7d8r");
        int $dirty2 = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 4 : 2;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty2 |= 48;
            drawerShape2 = drawerShape;
        } else if (($changed & 112) == 0) {
            drawerShape2 = drawerShape;
            $dirty2 |= $composer2.changed(drawerShape2) ? 32 : 16;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i5 = $composer2.changed(drawerContainerColor2) ? 256 : 128;
                $dirty2 |= i5;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty2 |= i5;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i6 = $composer2.changed(drawerContentColor2) ? 2048 : 1024;
                $dirty2 |= i6;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty2 |= i6;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty2 |= 24576;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 57344) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty2 |= $composer2.changed(drawerTonalElevation2) ? 16384 : 8192;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i8 = $composer2.changed(windowInsets2) ? 131072 : 65536;
                $dirty2 |= i8;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i8;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(content) ? 1048576 : 524288;
            }
            if (($dirty2 & 2995931) == 599186 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        drawerShape2 = RectangleShapeKt.getRectangleShape();
                    }
                    if ((i & 4) != 0) {
                        drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                        $dirty2 &= -897;
                    }
                    if ((i & 8) != 0) {
                        drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                        $dirty2 &= -7169;
                    }
                    if (i7 != 0) {
                        drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1513getDismissibleDrawerElevationD9Ej5fM();
                    }
                    if ((i & 32) != 0) {
                        $dirty = $dirty2 & (-458753);
                        windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                        drawerShape3 = drawerShape2;
                        drawerContainerColor3 = drawerContainerColor2;
                        drawerContentColor3 = drawerContentColor2;
                        drawerTonalElevation3 = drawerTonalElevation2;
                        modifier3 = modifier2;
                    } else {
                        drawerShape3 = drawerShape2;
                        drawerContainerColor3 = drawerContainerColor2;
                        drawerContentColor3 = drawerContentColor2;
                        windowInsets3 = windowInsets2;
                        drawerTonalElevation3 = drawerTonalElevation2;
                        modifier3 = modifier2;
                        $dirty = $dirty2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 32) != 0) {
                        drawerShape3 = drawerShape2;
                        drawerContainerColor3 = drawerContainerColor2;
                        drawerContentColor3 = drawerContentColor2;
                        windowInsets3 = windowInsets2;
                        drawerTonalElevation3 = drawerTonalElevation2;
                        modifier3 = modifier;
                        $dirty = $dirty2 & (-458753);
                    } else {
                        drawerShape3 = drawerShape2;
                        drawerContainerColor3 = drawerContainerColor2;
                        drawerContentColor3 = drawerContentColor2;
                        windowInsets3 = windowInsets2;
                        drawerTonalElevation3 = drawerTonalElevation2;
                        modifier3 = modifier;
                        $dirty = $dirty2;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-588600583, $dirty, -1, "androidx.compose.material3.DismissibleDrawerSheet (NavigationDrawer.kt:491)");
                }
                m1631DrawerSheetvywBR7E(windowInsets3, modifier3, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, content, $composer2, (($dirty >> 15) & 14) | (($dirty << 3) & 112) | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | ($dirty & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
                drawerShape3 = drawerShape2;
                drawerContainerColor3 = drawerContainerColor2;
                drawerContentColor3 = drawerContentColor2;
                windowInsets3 = windowInsets2;
                drawerTonalElevation3 = drawerTonalElevation2;
                modifier3 = modifier;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final Shape shape = drawerShape3;
            final long j = drawerContainerColor3;
            final long j2 = drawerContentColor3;
            final float f = drawerTonalElevation3;
            final WindowInsets windowInsets4 = windowInsets3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleDrawerSheet$1
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
                    NavigationDrawerKt.m1630DismissibleDrawerSheetafqeVBk(modifier4, shape, j, j2, f, windowInsets4, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty2 |= i2;
        if (($dirty2 & 2995931) == 599186) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1513getDismissibleDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-458753);
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    windowInsets3 = windowInsets2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1513getDismissibleDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-458753);
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    windowInsets3 = windowInsets2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-588600583, $dirty, -1, "androidx.compose.material3.DismissibleDrawerSheet (NavigationDrawer.kt:491)");
            }
            m1631DrawerSheetvywBR7E(windowInsets3, modifier3, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, content, $composer2, (($dirty >> 15) & 14) | (($dirty << 3) & 112) | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | ($dirty & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1513getDismissibleDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-458753);
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    windowInsets3 = windowInsets2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1513getDismissibleDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-458753);
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    windowInsets3 = windowInsets2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-588600583, $dirty, -1, "androidx.compose.material3.DismissibleDrawerSheet (NavigationDrawer.kt:491)");
            }
            m1631DrawerSheetvywBR7E(windowInsets3, modifier3, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, content, $composer2, (($dirty >> 15) & 14) | (($dirty << 3) & 112) | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | ($dirty & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Shape shape2 = drawerShape3;
        final long j3 = drawerContainerColor3;
        final long j4 = drawerContentColor3;
        final float f2 = drawerTonalElevation3;
        final WindowInsets windowInsets5 = windowInsets3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleDrawerSheet$1
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
                NavigationDrawerKt.m1630DismissibleDrawerSheetafqeVBk(modifier5, shape2, j3, j4, f2, windowInsets5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:100:0x014e  */
    /* JADX WARN: Code duplicated, block: B:101:0x0153  */
    /* JADX WARN: Code duplicated, block: B:103:0x0157  */
    /* JADX WARN: Code duplicated, block: B:106:0x0160  */
    /* JADX WARN: Code duplicated, block: B:109:0x0170  */
    /* JADX WARN: Code duplicated, block: B:111:0x017c  */
    /* JADX WARN: Code duplicated, block: B:114:0x0187  */
    /* JADX WARN: Code duplicated, block: B:115:0x019e  */
    /* JADX WARN: Code duplicated, block: B:118:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:121:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:125:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:128:0x0249  */
    /* JADX WARN: Code duplicated, block: B:132:0x0253  */
    /* JADX WARN: Code duplicated, block: B:134:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x0108  */
    /* JADX WARN: Code duplicated, block: B:86:0x0110  */
    /* JADX WARN: Code duplicated, block: B:99:0x014c A[DONT_INVERT] */
    /* JADX INFO: renamed from: PermanentDrawerSheet-afqeVBk, reason: not valid java name */
    public static final void m1634PermanentDrawerSheetafqeVBk(Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        int i2;
        Modifier.Companion modifier2;
        Shape drawerShape3;
        long drawerContainerColor3;
        long drawerContentColor3;
        WindowInsets windowInsets3;
        float drawerTonalElevation3;
        Modifier modifier3;
        int $dirty;
        final String navigationMenu;
        boolean invalid$iv$iv;
        Object value$iv$iv;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-1733353241);
        ComposerKt.sourceInformation($composer2, "C(PermanentDrawerSheet)P(5,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp,6)531@20618L11,532@20671L37,534@20830L12,537@20921L33,540@21021L50,538@20959L244:NavigationDrawer.kt#uh7d8r");
        int $dirty2 = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 4 : 2;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty2 |= 48;
            drawerShape2 = drawerShape;
        } else if (($changed & 112) == 0) {
            drawerShape2 = drawerShape;
            $dirty2 |= $composer2.changed(drawerShape2) ? 32 : 16;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i5 = $composer2.changed(drawerContainerColor2) ? 256 : 128;
                $dirty2 |= i5;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty2 |= i5;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i6 = $composer2.changed(drawerContentColor2) ? 2048 : 1024;
                $dirty2 |= i6;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty2 |= i6;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty2 |= 24576;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 57344) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty2 |= $composer2.changed(drawerTonalElevation2) ? 16384 : 8192;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i8 = $composer2.changed(windowInsets2) ? 131072 : 65536;
                $dirty2 |= i8;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i8;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(content) ? 1048576 : 524288;
            }
            if (($dirty2 & 2995931) == 599186 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        drawerShape2 = RectangleShapeKt.getRectangleShape();
                    }
                    if ((i & 4) != 0) {
                        drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                        $dirty2 &= -897;
                    }
                    if ((i & 8) != 0) {
                        drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                        $dirty2 &= -7169;
                    }
                    if (i7 != 0) {
                        drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1516getPermanentDrawerElevationD9Ej5fM();
                    }
                    if ((i & 32) != 0) {
                        $dirty = $dirty2 & (-458753);
                        windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                        drawerShape3 = drawerShape2;
                        drawerContainerColor3 = drawerContainerColor2;
                        drawerContentColor3 = drawerContentColor2;
                        drawerTonalElevation3 = drawerTonalElevation2;
                        modifier3 = modifier2;
                    } else {
                        drawerShape3 = drawerShape2;
                        drawerContainerColor3 = drawerContainerColor2;
                        drawerContentColor3 = drawerContentColor2;
                        windowInsets3 = windowInsets2;
                        drawerTonalElevation3 = drawerTonalElevation2;
                        modifier3 = modifier2;
                        $dirty = $dirty2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 32) != 0) {
                        drawerShape3 = drawerShape2;
                        drawerContainerColor3 = drawerContainerColor2;
                        drawerContentColor3 = drawerContentColor2;
                        windowInsets3 = windowInsets2;
                        drawerTonalElevation3 = drawerTonalElevation2;
                        modifier3 = modifier;
                        $dirty = (-458753) & $dirty2;
                    } else {
                        drawerShape3 = drawerShape2;
                        drawerContainerColor3 = drawerContainerColor2;
                        drawerContentColor3 = drawerContentColor2;
                        windowInsets3 = windowInsets2;
                        drawerTonalElevation3 = drawerTonalElevation2;
                        modifier3 = modifier;
                        $dirty = $dirty2;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1733353241, $dirty, -1, "androidx.compose.material3.PermanentDrawerSheet (NavigationDrawer.kt:528)");
                }
                navigationMenu = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1777getNavigationMenuadMyvUU(), $composer2, 6);
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(navigationMenu);
                Object it$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$PermanentDrawerSheet$1$1
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
                            SemanticsPropertiesKt.setPaneTitle(semantics, navigationMenu);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                $composer2.endReplaceableGroup();
                m1631DrawerSheetvywBR7E(windowInsets3, SemanticsModifierKt.semantics$default(modifier3, false, (Function1) value$iv$iv, 1, null), drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, content, $composer2, (($dirty >> 15) & 14) | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | ($dirty & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
                drawerShape3 = drawerShape2;
                drawerContainerColor3 = drawerContainerColor2;
                drawerContentColor3 = drawerContentColor2;
                windowInsets3 = windowInsets2;
                drawerTonalElevation3 = drawerTonalElevation2;
                modifier3 = modifier;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final Shape shape = drawerShape3;
            final long j = drawerContainerColor3;
            final long j2 = drawerContentColor3;
            final float f = drawerTonalElevation3;
            final WindowInsets windowInsets4 = windowInsets3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$PermanentDrawerSheet$2
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
                    NavigationDrawerKt.m1634PermanentDrawerSheetafqeVBk(modifier4, shape, j, j2, f, windowInsets4, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty2 |= i2;
        if (($dirty2 & 2995931) == 599186) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1516getPermanentDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-458753);
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    windowInsets3 = windowInsets2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1516getPermanentDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-458753);
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    windowInsets3 = windowInsets2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1733353241, $dirty, -1, "androidx.compose.material3.PermanentDrawerSheet (NavigationDrawer.kt:528)");
            }
            navigationMenu = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1777getNavigationMenuadMyvUU(), $composer2, 6);
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(navigationMenu);
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv) {
                value$iv$iv = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$PermanentDrawerSheet$1$1
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
                        SemanticsPropertiesKt.setPaneTitle(semantics, navigationMenu);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$PermanentDrawerSheet$1$1
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
                        SemanticsPropertiesKt.setPaneTitle(semantics, navigationMenu);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            }
            $composer2.endReplaceableGroup();
            m1631DrawerSheetvywBR7E(windowInsets3, SemanticsModifierKt.semantics$default(modifier3, false, (Function1) value$iv$iv, 1, null), drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, content, $composer2, (($dirty >> 15) & 14) | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | ($dirty & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1516getPermanentDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-458753);
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    windowInsets3 = windowInsets2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1516getPermanentDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-458753);
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    windowInsets3 = windowInsets2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1733353241, $dirty, -1, "androidx.compose.material3.PermanentDrawerSheet (NavigationDrawer.kt:528)");
            }
            navigationMenu = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1777getNavigationMenuadMyvUU(), $composer2, 6);
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(navigationMenu);
            Object it$iv$iv3 = $composer2.rememberedValue();
            if (invalid$iv$iv) {
                value$iv$iv = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$PermanentDrawerSheet$1$1
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
                        SemanticsPropertiesKt.setPaneTitle(semantics, navigationMenu);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$PermanentDrawerSheet$1$1
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
                        SemanticsPropertiesKt.setPaneTitle(semantics, navigationMenu);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            }
            $composer2.endReplaceableGroup();
            m1631DrawerSheetvywBR7E(windowInsets3, SemanticsModifierKt.semantics$default(modifier3, false, (Function1) value$iv$iv, 1, null), drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, content, $composer2, (($dirty >> 15) & 14) | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | ($dirty & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Shape shape2 = drawerShape3;
        final long j3 = drawerContainerColor3;
        final long j4 = drawerContentColor3;
        final float f2 = drawerTonalElevation3;
        final WindowInsets windowInsets5 = windowInsets3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$PermanentDrawerSheet$2
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
                NavigationDrawerKt.m1634PermanentDrawerSheetafqeVBk(modifier5, shape2, j3, j4, f2, windowInsets5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:102:0x013b  */
    /* JADX WARN: Code duplicated, block: B:105:0x014c  */
    /* JADX WARN: Code duplicated, block: B:107:0x0157  */
    /* JADX WARN: Code duplicated, block: B:110:0x0167  */
    /* JADX WARN: Code duplicated, block: B:113:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:117:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:118:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:83:0x0100  */
    /* JADX WARN: Code duplicated, block: B:85:0x010a  */
    /* JADX WARN: Code duplicated, block: B:95:0x0127 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:96:0x0129  */
    /* JADX WARN: Code duplicated, block: B:97:0x012e  */
    /* JADX WARN: Code duplicated, block: B:99:0x0132  */
    /* JADX INFO: renamed from: DrawerSheet-vywBR7E, reason: not valid java name */
    public static final void m1631DrawerSheetvywBR7E(final WindowInsets windowInsets, Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        int i2;
        Modifier.Companion modifier2;
        Modifier modifier3;
        Shape drawerShape3;
        long drawerContainerColor3;
        float drawerTonalElevation3;
        long drawerContentColor3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer $composer2 = $composer.startRestartGroup(175072821);
        ComposerKt.sourceInformation($composer2, "C(DrawerSheet)P(6,5,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp)556@21400L11,557@21453L37,561@21620L667:NavigationDrawer.kt#uh7d8r");
        final int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(windowInsets) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            drawerShape2 = drawerShape;
        } else if (($changed & 896) == 0) {
            drawerShape2 = drawerShape;
            $dirty |= $composer2.changed(drawerShape2) ? 256 : 128;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i5 = $composer2.changed(drawerContainerColor2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty |= i5;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i6 = $composer2.changed(drawerContentColor2) ? 16384 : 8192;
                $dirty |= i6;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty |= i6;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 458752) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty |= $composer2.changed(drawerTonalElevation2) ? 131072 : 65536;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((i & 64) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(function3) ? 1048576 : 524288;
            }
            if (($dirty & 2995931) == 599186 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        drawerShape2 = RectangleShapeKt.getRectangleShape();
                    }
                    if ((i & 8) != 0) {
                        drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                        $dirty &= -7169;
                    }
                    if ((i & 16) != 0) {
                        drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty >> 9) & 14);
                        $dirty &= -57345;
                    }
                    if (i7 != 0) {
                        drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1516getPermanentDrawerElevationD9Ej5fM();
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty &= -57345;
                        modifier2 = modifier;
                    } else {
                        modifier2 = modifier;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(175072821, $dirty, -1, "androidx.compose.material3.DrawerSheet (NavigationDrawer.kt:552)");
                }
                SurfaceKt.m1806SurfaceT9BRK9s(SizeKt.fillMaxHeight$default(SizeKt.m538sizeInqDBjuR0$default(modifier2, MinimumDrawerWidth, 0.0f, DrawerDefaults.INSTANCE.m1514getMaximumDrawerWidthD9Ej5fM(), 0.0f, 10, null), 0.0f, 1, null), drawerShape2, drawerContainerColor2, drawerContentColor2, drawerTonalElevation2, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 959363152, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DrawerSheet$1
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
                        ComposerKt.sourceInformation($composer3, "C573@22002L279:NavigationDrawer.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(959363152, $changed2, -1, "androidx.compose.material3.DrawerSheet.<anonymous> (NavigationDrawer.kt:572)");
                            }
                            Modifier modifier$iv = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.m538sizeInqDBjuR0$default(Modifier.INSTANCE, NavigationDrawerKt.MinimumDrawerWidth, 0.0f, DrawerDefaults.INSTANCE.m1514getMaximumDrawerWidthD9Ej5fM(), 0.0f, 10, null), windowInsets);
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            int $changed$iv = ($dirty >> 9) & 7168;
                            $composer3.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
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
                            int i8 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                            function4.invoke(ColumnScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
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
                }), $composer2, (($dirty >> 3) & 112) | 12582912 | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344), 96);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                drawerShape3 = drawerShape2;
                drawerContainerColor3 = drawerContainerColor2;
                drawerTonalElevation3 = drawerTonalElevation2;
                drawerContentColor3 = drawerContentColor2;
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier;
                drawerShape3 = drawerShape2;
                drawerContainerColor3 = drawerContainerColor2;
                drawerTonalElevation3 = drawerTonalElevation2;
                drawerContentColor3 = drawerContentColor2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final Shape shape = drawerShape3;
            final long j = drawerContainerColor3;
            final long j2 = drawerContentColor3;
            final float f = drawerTonalElevation3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DrawerSheet$2
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
                    NavigationDrawerKt.m1631DrawerSheetvywBR7E(windowInsets, modifier4, shape, j, j2, f, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty |= i2;
        if (($dirty & 2995931) == 599186) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 8) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1516getPermanentDrawerElevationD9Ej5fM();
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 8) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1516getPermanentDrawerElevationD9Ej5fM();
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(175072821, $dirty, -1, "androidx.compose.material3.DrawerSheet (NavigationDrawer.kt:552)");
            }
            SurfaceKt.m1806SurfaceT9BRK9s(SizeKt.fillMaxHeight$default(SizeKt.m538sizeInqDBjuR0$default(modifier2, MinimumDrawerWidth, 0.0f, DrawerDefaults.INSTANCE.m1514getMaximumDrawerWidthD9Ej5fM(), 0.0f, 10, null), 0.0f, 1, null), drawerShape2, drawerContainerColor2, drawerContentColor2, drawerTonalElevation2, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 959363152, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DrawerSheet$1
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
                    ComposerKt.sourceInformation($composer3, "C573@22002L279:NavigationDrawer.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(959363152, $changed2, -1, "androidx.compose.material3.DrawerSheet.<anonymous> (NavigationDrawer.kt:572)");
                        }
                        Modifier modifier$iv = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.m538sizeInqDBjuR0$default(Modifier.INSTANCE, NavigationDrawerKt.MinimumDrawerWidth, 0.0f, DrawerDefaults.INSTANCE.m1514getMaximumDrawerWidthD9Ej5fM(), 0.0f, 10, null), windowInsets);
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        int $changed$iv = ($dirty >> 9) & 7168;
                        $composer3.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                        int $changed$iv$iv = ($changed$iv << 3) & 112;
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
                        int i8 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
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
            }), $composer2, (($dirty >> 3) & 112) | 12582912 | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344), 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerTonalElevation3 = drawerTonalElevation2;
            drawerContentColor3 = drawerContentColor2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 8) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1516getPermanentDrawerElevationD9Ej5fM();
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 8) != 0) {
                    drawerContainerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m1516getPermanentDrawerElevationD9Ej5fM();
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(175072821, $dirty, -1, "androidx.compose.material3.DrawerSheet (NavigationDrawer.kt:552)");
            }
            SurfaceKt.m1806SurfaceT9BRK9s(SizeKt.fillMaxHeight$default(SizeKt.m538sizeInqDBjuR0$default(modifier2, MinimumDrawerWidth, 0.0f, DrawerDefaults.INSTANCE.m1514getMaximumDrawerWidthD9Ej5fM(), 0.0f, 10, null), 0.0f, 1, null), drawerShape2, drawerContainerColor2, drawerContentColor2, drawerTonalElevation2, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 959363152, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DrawerSheet$1
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
                    ComposerKt.sourceInformation($composer3, "C573@22002L279:NavigationDrawer.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(959363152, $changed2, -1, "androidx.compose.material3.DrawerSheet.<anonymous> (NavigationDrawer.kt:572)");
                        }
                        Modifier modifier$iv = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.m538sizeInqDBjuR0$default(Modifier.INSTANCE, NavigationDrawerKt.MinimumDrawerWidth, 0.0f, DrawerDefaults.INSTANCE.m1514getMaximumDrawerWidthD9Ej5fM(), 0.0f, 10, null), windowInsets);
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        int $changed$iv = ($dirty >> 9) & 7168;
                        $composer3.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                        int $changed$iv$iv = ($changed$iv << 3) & 112;
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
                        int i8 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
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
            }), $composer2, (($dirty >> 3) & 112) | 12582912 | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344), 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerTonalElevation3 = drawerTonalElevation2;
            drawerContentColor3 = drawerContentColor2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Shape shape2 = drawerShape3;
        final long j3 = drawerContainerColor3;
        final long j4 = drawerContentColor3;
        final float f2 = drawerTonalElevation3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DrawerSheet$2
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
                NavigationDrawerKt.m1631DrawerSheetvywBR7E(windowInsets, modifier5, shape2, j3, j4, f2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static final void NavigationDrawerItem(final Function2<? super Composer, ? super Integer, Unit> label, final boolean selected, final Function0<Unit> onClick, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Shape shape, NavigationDrawerItemColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function4;
        Shape shape2;
        MutableInteractionSource mutableInteractionSource;
        Modifier.Companion modifier2;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Shape shape3;
        NavigationDrawerItemColors colors2;
        int $dirty;
        MutableInteractionSource interactionSource2;
        NavigationDrawerItemColors colors3;
        Object value$iv$iv;
        NavigationDrawerItemColors colors4;
        MutableInteractionSource interactionSource3;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Shape shape4;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer2 = $composer.startRestartGroup(-1304626543);
        ComposerKt.sourceInformation($composer2, "C(NavigationDrawerItem)P(4,7,6,5,2!1,8)657@25365L9,658@25446L8,659@25506L39,668@25818L24,661@25554L1233:NavigationDrawer.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changedInstance(label) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changed(selected) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer2.changedInstance(onClick) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 2048 : 1024;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty2 |= 24576;
            function4 = function2;
        } else if (($changed & 57344) == 0) {
            function4 = function2;
            $dirty2 |= $composer2.changedInstance(function4) ? 16384 : 8192;
        } else {
            function4 = function2;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((458752 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 131072 : 65536;
        }
        if ((3670016 & $changed) == 0) {
            if ((i & 64) == 0) {
                shape2 = shape;
                int i5 = $composer2.changed(shape2) ? 1048576 : 524288;
                $dirty2 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        if (($changed & 29360128) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer2.changed(colors)) ? 8388608 : 4194304;
        }
        int i6 = i & 256;
        if (i6 != 0) {
            $dirty2 |= 100663296;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 234881024) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer2.changed(mutableInteractionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if (($dirty2 & 191739611) == 38347922 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            colors4 = colors;
            interactionSource3 = mutableInteractionSource;
            shape4 = shape2;
            function6 = function3;
            function7 = function4;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if (i3 != 0) {
                    function4 = null;
                }
                function5 = i4 != 0 ? null : function3;
                if ((i & 64) != 0) {
                    shape3 = ShapesKt.toShape(NavigationDrawerTokens.INSTANCE.getActiveIndicatorShape(), $composer2, 6);
                    $dirty2 &= -3670017;
                } else {
                    shape3 = shape2;
                }
                if ((i & 128) != 0) {
                    colors2 = NavigationDrawerItemDefaults.INSTANCE.m1629colorsoq7We08(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 100663296, 255);
                    $dirty2 &= -29360129;
                } else {
                    colors2 = colors;
                }
                if (i6 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    Object it$iv$iv = $composer2.rememberedValue();
                    int $dirty3 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    $dirty = $dirty3;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    colors3 = colors2;
                } else {
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    colors3 = colors2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    modifier2 = modifier;
                    function5 = function3;
                    $dirty = $dirty2 & (-29360129);
                    interactionSource2 = mutableInteractionSource;
                    shape3 = shape2;
                    colors3 = colors;
                } else {
                    modifier2 = modifier;
                    function5 = function3;
                    colors3 = colors;
                    $dirty = $dirty2;
                    interactionSource2 = mutableInteractionSource;
                    shape3 = shape2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1304626543, $dirty, -1, "androidx.compose.material3.NavigationDrawerItem (NavigationDrawer.kt:650)");
            }
            final Function2<? super Composer, ? super Integer, Unit> function8 = function4;
            final NavigationDrawerItemColors navigationDrawerItemColors = colors3;
            final int i7 = $dirty;
            final Function2<? super Composer, ? super Integer, Unit> function9 = function5;
            SurfaceKt.m1807Surfaced85dljk(selected, onClick, SizeKt.fillMaxWidth$default(SizeKt.m520height3ABfNKs(SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.NavigationDrawerItem.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4616getTabo7Vup1c());
                }
            }, 1, null), NavigationDrawerTokens.INSTANCE.m2300getActiveIndicatorHeightD9Ej5fM()), 0.0f, 1, null), false, shape3, colors3.containerColor(selected, $composer2, (($dirty >> 3) & 14) | (($dirty >> 18) & 112)).getValue().m2981unboximpl(), 0L, 0.0f, 0.0f, (BorderStroke) null, interactionSource2, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.composableLambda($composer2, 191488423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.NavigationDrawerItem.3
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
                    ComposerKt.sourceInformation($composer3, "C671@25913L868:NavigationDrawer.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(191488423, $changed2, -1, "androidx.compose.material3.NavigationDrawerItem.<anonymous> (NavigationDrawer.kt:670)");
                        }
                        Modifier modifier$iv = PaddingKt.m491paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m5274constructorimpl(16), 0.0f, Dp.m5274constructorimpl(24), 0.0f, 10, null);
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        Function2<Composer, Integer, Unit> function10 = function8;
                        NavigationDrawerItemColors navigationDrawerItemColors2 = navigationDrawerItemColors;
                        boolean z = selected;
                        int i8 = i7;
                        Function2<Composer, Integer, Unit> function11 = function9;
                        Function2<Composer, Integer, Unit> function12 = label;
                        $composer3.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                        int $changed$iv$iv = (390 << 3) & 112;
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
                        int i9 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                        int i10 = ((390 >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u241 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1538531918, "C680@26311L203:NavigationDrawer.kt#uh7d8r");
                        $composer3.startReplaceableGroup(-1538531918);
                        ComposerKt.sourceInformation($composer3, "676@26118L19,677@26160L78,678@26255L29");
                        if (function10 != null) {
                            long iconColor = navigationDrawerItemColors2.iconColor(z, $composer3, ((i8 >> 3) & 14) | ((i8 >> 18) & 112)).getValue().m2981unboximpl();
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(iconColor))}, function10, $composer3, ((i8 >> 9) & 112) | 8);
                            SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, Dp.m5274constructorimpl(12)), $composer3, 6);
                        }
                        $composer3.endReplaceableGroup();
                        Modifier modifier$iv2 = RowScope.CC.weight$default($this$invoke_u24lambda_u241, Modifier.INSTANCE, 1.0f, false, 2, null);
                        $composer3.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        int $changed$iv = ((0 >> 3) & 14) | ((0 >> 3) & 112);
                        MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, $changed$iv);
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
                        int i11 = ($changed$iv$iv$iv2 >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i12 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1694711418, "C681@26378L19,682@26420L80:NavigationDrawer.kt#uh7d8r");
                        int i13 = ((i8 >> 3) & 14) | ((i8 >> 18) & 112);
                        long labelColor = navigationDrawerItemColors2.textColor(z, $composer3, i13).getValue().m2981unboximpl();
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(labelColor))}, function12, $composer3, ((i8 << 3) & 112) | 8);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        $composer3.startReplaceableGroup(-533539227);
                        ComposerKt.sourceInformation($composer3, "685@26564L29,686@26634L20,687@26677L80");
                        if (function11 != null) {
                            SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, Dp.m5274constructorimpl(12)), $composer3, 6);
                            long badgeColor = navigationDrawerItemColors2.badgeColor(z, $composer3, i13).getValue().m2981unboximpl();
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(badgeColor))}, function11, $composer3, ((i8 >> 12) & 112) | 8);
                        }
                        $composer3.endReplaceableGroup();
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
            }), $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty >> 6) & 57344), (($dirty >> 24) & 14) | 48, 968);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors4 = colors3;
            interactionSource3 = interactionSource2;
            modifier3 = modifier2;
            function6 = function5;
            shape4 = shape3;
            function7 = function4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function10 = function7;
        final Function2<? super Composer, ? super Integer, Unit> function11 = function6;
        final Shape shape5 = shape4;
        final NavigationDrawerItemColors navigationDrawerItemColors2 = colors4;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.NavigationDrawerItem.4
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
                NavigationDrawerKt.NavigationDrawerItem(label, selected, onClick, modifier4, function10, function11, shape5, navigationDrawerItemColors2, mutableInteractionSource2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculateFraction(float a, float b, float pos) {
        return RangesKt.coerceIn((pos - a) / (b - a), 0.0f, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Scrim-Bx497Mc, reason: not valid java name */
    public static final void m1635ScrimBx497Mc(final boolean open, final Function0<Unit> function0, final Function0<Float> function1, final long color, Composer $composer, final int $changed) {
        Modifier.Companion dismissDrawer;
        Composer $composer2 = $composer.startRestartGroup(2106487387);
        ComposerKt.sourceInformation($composer2, "C(Scrim)P(3,2,1,0:c#ui.graphics.Color)848@32851L30,864@33295L51,860@33204L142:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(open) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(color) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 5851) != 1170 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2106487387, $dirty2, -1, "androidx.compose.material3.Scrim (NavigationDrawer.kt:842)");
            }
            final String closeDrawer = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1739getCloseDraweradMyvUU(), $composer2, 6);
            $composer2.startReplaceableGroup(-1858703321);
            ComposerKt.sourceInformation($composer2, "851@32970L35,852@33054L108");
            if (open) {
                Modifier.Companion companion = Modifier.INSTANCE;
                int i = ($dirty2 >> 3) & 14;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean invalid$iv$iv = $composer2.changed(function0);
                NavigationDrawerKt$Scrim$dismissDrawer$1$1 value$iv$iv = $composer2.rememberedValue();
                if (invalid$iv$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = new NavigationDrawerKt$Scrim$dismissDrawer$1$1(function0, null);
                    $composer2.updateRememberedValue(value$iv$iv);
                }
                $composer2.endReplaceableGroup();
                Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, function0, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv);
                int i2 = $dirty2 & 112;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                boolean invalid$iv$iv2 = $composer2.changed(closeDrawer) | $composer2.changed(function0);
                Object value$iv$iv2 = $composer2.rememberedValue();
                if (invalid$iv$iv2 || value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$Scrim$dismissDrawer$2$1
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
                            SemanticsPropertiesKt.setContentDescription(semantics, closeDrawer);
                            final Function0<Unit> function2 = function0;
                            SemanticsPropertiesKt.onClick$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$Scrim$dismissDrawer$2$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    function2.invoke();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv2);
                }
                $composer2.endReplaceableGroup();
                dismissDrawer = SemanticsModifierKt.semantics(modifierPointerInput, true, (Function1) value$iv$iv2);
            } else {
                dismissDrawer = Modifier.INSTANCE;
            }
            $composer2.endReplaceableGroup();
            Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissDrawer);
            Object key1$iv = Color.m2961boximpl(color);
            int i3 = (($dirty2 >> 9) & 14) | (($dirty2 >> 3) & 112);
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean invalid$iv$iv3 = $composer2.changed(key1$iv) | $composer2.changed(function1);
            Object value$iv$iv3 = $composer2.rememberedValue();
            if (invalid$iv$iv3 || value$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$Scrim$1$1
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
                        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
                        DrawScope.CC.m3522drawRectnJ9OG0$default(Canvas, color, 0L, 0L, function1.invoke().floatValue(), null, null, 0, 118, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv3);
            }
            $composer2.endReplaceableGroup();
            CanvasKt.Canvas(modifierThen, (Function1) value$iv$iv3, $composer2, 0);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$Scrim$2
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
                NavigationDrawerKt.m1635ScrimBx497Mc(open, function0, function1, color, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }
}
