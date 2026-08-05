package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BackdropScaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a;\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\tH\u0003¢\u0006\u0002\u0010\u000b\u001aõ\u0001\u0010\f\u001a\u00020\u00042\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010\u0016\u001a\u00020\u00012\b\b\u0002\u0010\u0017\u001a\u00020\u00142\b\b\u0002\u0010\u0018\u001a\u00020\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u001a2\b\b\u0002\u0010 \u001a\u00020\u001a2\b\b\u0002\u0010!\u001a\u00020\u001a2\u0019\b\u0002\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00040#¢\u0006\u0002\b\tH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001ah\u0010'\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0016\u0010(\u001a\u0012\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\t¢\u0006\u0002\b)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020+0#2\"\u0010,\u001a\u001e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u00040-¢\u0006\u0002\b\t¢\u0006\u0002\b)H\u0003ø\u0001\u0000¢\u0006\u0002\u0010/\u001a3\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u001a2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u00103\u001a\u00020\u0014H\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b4\u00105\u001aE\u00106\u001a\u00020\u00122\u0006\u00107\u001a\u00020\u00062\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020.092\u0014\b\u0002\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00140#2\b\b\u0002\u0010;\u001a\u00020$H\u0007¢\u0006\u0002\u0010<\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006=²\u0006\n\u0010>\u001a\u00020.X\u008a\u0084\u0002²\u0006\n\u0010?\u001a\u00020.X\u008a\u0084\u0002"}, d2 = {"AnimationSlideOffset", "Landroidx/compose/ui/unit/Dp;", "F", "BackLayerTransition", "", "target", "Landroidx/compose/material/BackdropValue;", "appBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "content", "(Landroidx/compose/material/BackdropValue;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "BackdropScaffold", "backLayerContent", "frontLayerContent", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material/BackdropScaffoldState;", "gesturesEnabled", "", "peekHeight", "headerHeight", "persistentAppBar", "stickyFrontLayer", "backLayerBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "backLayerContentColor", "frontLayerShape", "Landroidx/compose/ui/graphics/Shape;", "frontLayerElevation", "frontLayerBackgroundColor", "frontLayerContentColor", "frontLayerScrimColor", "snackbarHost", "Lkotlin/Function1;", "Landroidx/compose/material/SnackbarHostState;", "BackdropScaffold-BZszfkY", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material/BackdropScaffoldState;ZFFZZJJLandroidx/compose/ui/graphics/Shape;FJJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "BackdropStack", "backLayer", "Landroidx/compose/ui/UiComposable;", "calculateBackLayerConstraints", "Landroidx/compose/ui/unit/Constraints;", "frontLayer", "Lkotlin/Function2;", "", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)V", "Scrim", "color", "onDismiss", "visible", "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "rememberBackdropScaffoldState", "initialValue", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "confirmStateChange", "snackbarHostState", "(Landroidx/compose/material/BackdropValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BackdropScaffoldState;", "material_release", "alpha", "animationProgress"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BackdropScaffoldKt {
    private static final float AnimationSlideOffset = Dp.m5274constructorimpl(20);

    public static final BackdropScaffoldState rememberBackdropScaffoldState(final BackdropValue initialValue, final AnimationSpec<Float> animationSpec, final Function1<? super BackdropValue, Boolean> function1, final SnackbarHostState snackbarHostState, Composer $composer, int $changed, int i) {
        Object value$iv$iv;
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        $composer.startReplaceableGroup(-862178912);
        ComposerKt.sourceInformation($composer, "C(rememberBackdropScaffoldState)P(2)171@6447L32,173@6518L538:BackdropScaffold.kt#jmzs0o");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec2 = SwipeableDefaults.INSTANCE.getAnimationSpec();
            animationSpec = animationSpec2;
        }
        if ((i & 4) != 0) {
            Function1 confirmStateChange = new Function1<BackdropValue, Boolean>() { // from class: androidx.compose.material.BackdropScaffoldKt.rememberBackdropScaffoldState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(BackdropValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
            function1 = confirmStateChange;
        }
        if ((i & 8) != 0) {
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new SnackbarHostState();
                $composer.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer.endReplaceableGroup();
            snackbarHostState = (SnackbarHostState) value$iv$iv;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-862178912, $changed, -1, "androidx.compose.material.rememberBackdropScaffoldState (BackdropScaffold.kt:167)");
        }
        BackdropScaffoldState backdropScaffoldState = (BackdropScaffoldState) RememberSaveableKt.m2617rememberSaveable(new Object[]{animationSpec, function1, snackbarHostState}, (Saver) BackdropScaffoldState.INSTANCE.Saver(animationSpec, function1, snackbarHostState), (String) null, (Function0) new Function0<BackdropScaffoldState>() { // from class: androidx.compose.material.BackdropScaffoldKt.rememberBackdropScaffoldState.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BackdropScaffoldState invoke() {
                return new BackdropScaffoldState(initialValue, animationSpec, function1, snackbarHostState);
            }
        }, $composer, 72, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return backdropScaffoldState;
    }

    /* JADX INFO: renamed from: BackdropScaffold-BZszfkY, reason: not valid java name */
    public static final void m985BackdropScaffoldBZszfkY(final Function2<? super Composer, ? super Integer, Unit> appBar, final Function2<? super Composer, ? super Integer, Unit> backLayerContent, final Function2<? super Composer, ? super Integer, Unit> frontLayerContent, Modifier modifier, BackdropScaffoldState scaffoldState, boolean gesturesEnabled, float peekHeight, float headerHeight, boolean persistentAppBar, boolean stickyFrontLayer, long backLayerBackgroundColor, long backLayerContentColor, Shape frontLayerShape, float frontLayerElevation, long frontLayerBackgroundColor, long frontLayerContentColor, long frontLayerScrimColor, Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        int $dirty;
        Shape shape;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function4;
        BackdropScaffoldState scaffoldState2;
        boolean persistentAppBar2;
        boolean stickyFrontLayer2;
        int $dirty1;
        long backLayerBackgroundColor2;
        long backLayerContentColor2;
        int $dirty2;
        Shape frontLayerShape2;
        long frontLayerBackgroundColor2;
        long frontLayerContentColor2;
        long frontLayerBackgroundColor3;
        BackdropScaffoldState scaffoldState3;
        Shape frontLayerShape3;
        float frontLayerElevation2;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function3M1072getLambda1$material_release;
        int $dirty3;
        long frontLayerScrimColor2;
        long frontLayerContentColor3;
        long frontLayerBackgroundColor4;
        long backLayerContentColor3;
        int $dirty4;
        boolean gesturesEnabled2;
        float peekHeight2;
        float headerHeight2;
        Object value$iv$iv;
        boolean persistentAppBar3;
        BackdropScaffoldState scaffoldState4;
        boolean gesturesEnabled3;
        float peekHeight3;
        float headerHeight3;
        Modifier modifier3;
        boolean stickyFrontLayer3;
        long backLayerBackgroundColor3;
        Intrinsics.checkNotNullParameter(appBar, "appBar");
        Intrinsics.checkNotNullParameter(backLayerContent, "backLayerContent");
        Intrinsics.checkNotNullParameter(frontLayerContent, "frontLayerContent");
        Composer $composer2 = $composer.startRestartGroup(1397420093);
        ComposerKt.sourceInformation($composer2, "C(BackdropScaffold)P(!1,2,5,12,15,10,13:c#ui.unit.Dp,11:c#ui.unit.Dp,14,17,1:c#ui.graphics.Color,3:c#ui.graphics.Color,9,7:c#ui.unit.Dp,4:c#ui.graphics.Color,6:c#ui.graphics.Color,8:c#ui.graphics.Color)260@11766L40,266@12093L6,267@12144L41,268@12241L15,270@12387L6,271@12439L42,272@12542L20,*275@12690L7,276@12764L7,288@13147L100,293@13271L3282:BackdropScaffold.kt#jmzs0o");
        int $dirty5 = $changed;
        int $dirty6 = $changed1;
        int i2 = 2;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty5 |= $composer2.changedInstance(appBar) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty5 |= $composer2.changedInstance(backLayerContent) ? 32 : 16;
        }
        int i3 = 128;
        if ((i & 4) != 0) {
            $dirty5 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty5 |= $composer2.changedInstance(frontLayerContent) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty5 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 7168) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i5 = 8192;
        if (($changed & 57344) == 0) {
            $dirty5 |= ((i & 16) == 0 && $composer2.changed(scaffoldState)) ? 16384 : 8192;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty5 |= $composer2.changed(gesturesEnabled) ? 131072 : 65536;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty5 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty5 |= $composer2.changed(peekHeight) ? 1048576 : 524288;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty5 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty5 |= $composer2.changed(headerHeight) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty5 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty5 |= $composer2.changed(persistentAppBar) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty = $dirty5 | 805306368;
            i10 = i10;
        } else {
            if (($changed & 1879048192) == 0) {
                $dirty5 |= $composer2.changed(stickyFrontLayer) ? 536870912 : 268435456;
            }
            $dirty = $dirty5;
        }
        if (($changed1 & 14) == 0) {
            if ((i & 1024) == 0 && $composer2.changed(backLayerBackgroundColor)) {
                i2 = 4;
            }
            $dirty6 |= i2;
        }
        if (($changed1 & 112) == 0) {
            $dirty6 |= ((i & 2048) == 0 && $composer2.changed(backLayerContentColor)) ? 32 : 16;
        }
        if (($changed1 & 896) == 0) {
            if ((i & 4096) == 0) {
                shape = frontLayerShape;
                if ($composer2.changed(shape)) {
                    i3 = 256;
                }
            } else {
                shape = frontLayerShape;
            }
            $dirty6 |= i3;
        } else {
            shape = frontLayerShape;
        }
        int i11 = i & 8192;
        if (i11 != 0) {
            $dirty6 |= 3072;
        } else if (($changed1 & 7168) == 0) {
            $dirty6 |= $composer2.changed(frontLayerElevation) ? 2048 : 1024;
        }
        if (($changed1 & 57344) == 0) {
            if ((i & 16384) == 0 && $composer2.changed(frontLayerBackgroundColor)) {
                i5 = 16384;
            }
            $dirty6 |= i5;
        }
        if ((458752 & $changed1) == 0) {
            $dirty6 |= ((i & 32768) == 0 && $composer2.changed(frontLayerContentColor)) ? 131072 : 65536;
        }
        if ((3670016 & $changed1) == 0) {
            $dirty6 |= ((i & 65536) == 0 && $composer2.changed(frontLayerScrimColor)) ? 1048576 : 524288;
        }
        int i12 = i & 131072;
        if (i12 != 0) {
            $dirty6 |= 12582912;
            function4 = function3;
        } else if (($changed1 & 29360128) == 0) {
            function4 = function3;
            $dirty6 |= $composer2.changedInstance(function4) ? 8388608 : 4194304;
        } else {
            function4 = function3;
        }
        int $dirty7 = $dirty6;
        if (($dirty & 1533916891) == 306783378 && (23967451 & $dirty7) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            scaffoldState4 = scaffoldState;
            gesturesEnabled3 = gesturesEnabled;
            peekHeight3 = peekHeight;
            persistentAppBar3 = persistentAppBar;
            stickyFrontLayer3 = stickyFrontLayer;
            backLayerBackgroundColor3 = backLayerBackgroundColor;
            backLayerContentColor3 = backLayerContentColor;
            frontLayerElevation2 = frontLayerElevation;
            frontLayerBackgroundColor4 = frontLayerBackgroundColor;
            frontLayerContentColor3 = frontLayerContentColor;
            frontLayerScrimColor2 = frontLayerScrimColor;
            modifier3 = modifier2;
            frontLayerShape3 = shape;
            function3M1072getLambda1$material_release = function4;
            headerHeight3 = headerHeight;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier modifier4 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 16) != 0) {
                    scaffoldState2 = rememberBackdropScaffoldState(BackdropValue.Concealed, null, null, null, $composer2, 6, 14);
                    $dirty &= -57345;
                } else {
                    scaffoldState2 = scaffoldState;
                }
                boolean gesturesEnabled4 = i6 != 0 ? true : gesturesEnabled;
                float peekHeight4 = i7 != 0 ? BackdropScaffoldDefaults.INSTANCE.m984getPeekHeightD9Ej5fM() : peekHeight;
                float headerHeight4 = i8 != 0 ? BackdropScaffoldDefaults.INSTANCE.m983getHeaderHeightD9Ej5fM() : headerHeight;
                persistentAppBar2 = i9 != 0 ? true : persistentAppBar;
                stickyFrontLayer2 = i10 != 0 ? true : stickyFrontLayer;
                BackdropScaffoldState scaffoldState5 = scaffoldState2;
                if ((i & 1024) != 0) {
                    $dirty1 = $dirty7 & (-15);
                    backLayerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1048getPrimary0d7_KjU();
                } else {
                    $dirty1 = $dirty7;
                    backLayerBackgroundColor2 = backLayerBackgroundColor;
                }
                if ((i & 2048) != 0) {
                    backLayerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(backLayerBackgroundColor2, $composer2, $dirty1 & 14);
                    $dirty2 = $dirty1 & (-113);
                } else {
                    backLayerContentColor2 = backLayerContentColor;
                    $dirty2 = $dirty1;
                }
                boolean gesturesEnabled5 = gesturesEnabled4;
                if ((i & 4096) != 0) {
                    frontLayerShape2 = BackdropScaffoldDefaults.INSTANCE.getFrontLayerShape($composer2, 6);
                    $dirty2 &= -897;
                } else {
                    frontLayerShape2 = frontLayerShape;
                }
                float frontLayerElevation3 = i11 != 0 ? BackdropScaffoldDefaults.INSTANCE.m982getFrontLayerElevationD9Ej5fM() : frontLayerElevation;
                Shape frontLayerShape4 = frontLayerShape2;
                if ((i & 16384) != 0) {
                    frontLayerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -57345;
                } else {
                    frontLayerBackgroundColor2 = frontLayerBackgroundColor;
                }
                if ((i & 32768) != 0) {
                    frontLayerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(frontLayerBackgroundColor2, $composer2, ($dirty2 >> 12) & 14);
                    $dirty2 &= -458753;
                } else {
                    frontLayerContentColor2 = frontLayerContentColor;
                }
                if ((i & 65536) != 0) {
                    frontLayerBackgroundColor3 = BackdropScaffoldDefaults.INSTANCE.getFrontLayerScrimColor($composer2, 6);
                    $dirty2 &= -3670017;
                } else {
                    frontLayerBackgroundColor3 = frontLayerScrimColor;
                }
                if (i12 != 0) {
                    frontLayerShape3 = frontLayerShape4;
                    frontLayerElevation2 = frontLayerElevation3;
                    function3M1072getLambda1$material_release = ComposableSingletons$BackdropScaffoldKt.INSTANCE.m1072getLambda1$material_release();
                    $dirty3 = $dirty2;
                    frontLayerScrimColor2 = frontLayerBackgroundColor3;
                    frontLayerContentColor3 = frontLayerContentColor2;
                    modifier2 = modifier4;
                    frontLayerBackgroundColor4 = frontLayerBackgroundColor2;
                    backLayerContentColor3 = backLayerContentColor2;
                    $dirty4 = $dirty;
                    scaffoldState3 = scaffoldState5;
                    gesturesEnabled2 = gesturesEnabled5;
                    peekHeight2 = peekHeight4;
                    headerHeight2 = headerHeight4;
                } else {
                    scaffoldState3 = scaffoldState5;
                    frontLayerShape3 = frontLayerShape4;
                    frontLayerElevation2 = frontLayerElevation3;
                    function3M1072getLambda1$material_release = function3;
                    $dirty3 = $dirty2;
                    frontLayerScrimColor2 = frontLayerBackgroundColor3;
                    frontLayerContentColor3 = frontLayerContentColor2;
                    modifier2 = modifier4;
                    frontLayerBackgroundColor4 = frontLayerBackgroundColor2;
                    backLayerContentColor3 = backLayerContentColor2;
                    $dirty4 = $dirty;
                    gesturesEnabled2 = gesturesEnabled5;
                    peekHeight2 = peekHeight4;
                    headerHeight2 = headerHeight4;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 1024) != 0) {
                    $dirty7 &= -15;
                }
                if ((i & 2048) != 0) {
                    $dirty7 &= -113;
                }
                if ((i & 4096) != 0) {
                    $dirty7 &= -897;
                }
                if ((i & 16384) != 0) {
                    $dirty7 &= -57345;
                }
                if ((i & 32768) != 0) {
                    $dirty7 &= -458753;
                }
                if ((i & 65536) != 0) {
                    gesturesEnabled2 = gesturesEnabled;
                    peekHeight2 = peekHeight;
                    headerHeight2 = headerHeight;
                    persistentAppBar2 = persistentAppBar;
                    backLayerContentColor3 = backLayerContentColor;
                    frontLayerElevation2 = frontLayerElevation;
                    frontLayerBackgroundColor4 = frontLayerBackgroundColor;
                    frontLayerContentColor3 = frontLayerContentColor;
                    frontLayerScrimColor2 = frontLayerScrimColor;
                    $dirty3 = (-3670017) & $dirty7;
                    frontLayerShape3 = shape;
                    function3M1072getLambda1$material_release = function4;
                    $dirty4 = $dirty;
                    scaffoldState3 = scaffoldState;
                    stickyFrontLayer2 = stickyFrontLayer;
                    backLayerBackgroundColor2 = backLayerBackgroundColor;
                } else {
                    scaffoldState3 = scaffoldState;
                    gesturesEnabled2 = gesturesEnabled;
                    peekHeight2 = peekHeight;
                    headerHeight2 = headerHeight;
                    persistentAppBar2 = persistentAppBar;
                    backLayerContentColor3 = backLayerContentColor;
                    frontLayerElevation2 = frontLayerElevation;
                    frontLayerBackgroundColor4 = frontLayerBackgroundColor;
                    frontLayerContentColor3 = frontLayerContentColor;
                    frontLayerScrimColor2 = frontLayerScrimColor;
                    frontLayerShape3 = shape;
                    function3M1072getLambda1$material_release = function4;
                    $dirty3 = $dirty7;
                    $dirty4 = $dirty;
                    stickyFrontLayer2 = stickyFrontLayer;
                    backLayerBackgroundColor2 = backLayerBackgroundColor;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1397420093, $dirty4, $dirty3, "androidx.compose.material.BackdropScaffold (BackdropScaffold.kt:255)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$BackdropScaffold_BZszfkY_u24lambda_u241 = (Density) objConsume;
            final float peekHeightPx = $this$BackdropScaffold_BZszfkY_u24lambda_u241.mo327toPx0680j_4(peekHeight2);
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$BackdropScaffold_BZszfkY_u24lambda_u242 = (Density) objConsume2;
            final float headerHeightPx = $this$BackdropScaffold_BZszfkY_u24lambda_u242.mo327toPx0680j_4(headerHeight2);
            final boolean z = persistentAppBar2;
            final BackdropScaffoldState backdropScaffoldState = scaffoldState3;
            final int i13 = $dirty4;
            boolean persistentAppBar4 = persistentAppBar2;
            final Function2 backLayer = ComposableLambdaKt.composableLambda($composer2, 1744778315, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$backLayer$1
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
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer3, "C:BackdropScaffold.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1744778315, $changed2, -1, "androidx.compose.material.BackdropScaffold.<anonymous> (BackdropScaffold.kt:278)");
                        }
                        if (z) {
                            $composer3.startReplaceableGroup(-1017265331);
                            ComposerKt.sourceInformation($composer3, "280@12876L82");
                            Function2<Composer, Integer, Unit> function2 = appBar;
                            int i14 = i13;
                            Function2<Composer, Integer, Unit> function5 = backLayerContent;
                            $composer3.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                            if (!($composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                                function0 = constructor;
                                $composer3.createNode(function0);
                            } else {
                                function0 = constructor;
                                $composer3.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer3);
                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                            }
                            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                            $composer3.startReplaceableGroup(2058660585);
                            int i15 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 276693570, "C77@3893L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                            int i16 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1993300203, "C281@12901L8,282@12926L18:BackdropScaffold.kt#jmzs0o");
                            function2.invoke($composer3, Integer.valueOf(i14 & 14));
                            function5.invoke($composer3, Integer.valueOf((i14 >> 3) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                        } else {
                            $composer3.startReplaceableGroup(-1017265219);
                            ComposerKt.sourceInformation($composer3, "285@12988L72");
                            BackdropValue targetValue = backdropScaffoldState.getTargetValue();
                            Function2<Composer, Integer, Unit> function6 = appBar;
                            Function2<Composer, Integer, Unit> function7 = backLayerContent;
                            int i17 = i13;
                            BackdropScaffoldKt.BackLayerTransition(targetValue, function6, function7, $composer3, ((i17 << 3) & 896) | ((i17 << 3) & 112));
                            $composer3.endReplaceableGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            });
            Object key1$iv = Float.valueOf(headerHeightPx);
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(key1$iv);
            Object it$iv$iv = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = (Function1) new Function1<Constraints, Constraints>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$calculateBackLayerConstraints$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Constraints invoke(Constraints constraints) {
                        return Constraints.m5206boximpl(m990invokeZezNO4M(constraints.getValue()));
                    }

                    /* JADX INFO: renamed from: invoke-ZezNO4M, reason: not valid java name */
                    public final long m990invokeZezNO4M(long it) {
                        return ConstraintsKt.m5235offsetNN6EwU$default(Constraints.m5208copyZbe2FdA(it, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(it) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(it) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(it) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(it) : 0), 0, -MathKt.roundToInt(headerHeightPx), 1, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            final Function1 calculateBackLayerConstraints = (Function1) value$iv$iv;
            final Modifier modifier5 = modifier2;
            final boolean z2 = stickyFrontLayer2;
            final boolean z3 = gesturesEnabled2;
            final BackdropScaffoldState backdropScaffoldState2 = scaffoldState3;
            final int i14 = $dirty4;
            final Shape shape2 = frontLayerShape3;
            final long j = frontLayerBackgroundColor4;
            final long j2 = frontLayerContentColor3;
            final float f = frontLayerElevation2;
            final int i15 = $dirty3;
            final float f2 = headerHeight2;
            final float f3 = peekHeight2;
            final long j3 = frontLayerScrimColor2;
            final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function5 = function3M1072getLambda1$material_release;
            BackdropScaffoldState scaffoldState6 = scaffoldState3;
            boolean gesturesEnabled6 = gesturesEnabled2;
            SurfaceKt.m1210SurfaceFjzlyU(null, null, backLayerBackgroundColor2, backLayerContentColor3, null, 0.0f, ComposableLambdaKt.composableLambda($composer2, -1049909631, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1
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
                    Object value$iv$iv$iv;
                    ComposerKt.sourceInformation($composer3, "C297@13395L24,298@13428L3119:BackdropScaffold.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1049909631, $changed2, -1, "androidx.compose.material.BackdropScaffold.<anonymous> (BackdropScaffold.kt:296)");
                        }
                        $composer3.startReplaceableGroup(773894976);
                        ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
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
                        Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(modifier5, 0.0f, 1, null);
                        Function2<Composer, Integer, Unit> function2 = backLayer;
                        Function1<Constraints, Constraints> function1 = calculateBackLayerConstraints;
                        final float f4 = headerHeightPx;
                        final boolean z4 = z2;
                        final boolean z5 = z3;
                        final BackdropScaffoldState backdropScaffoldState3 = backdropScaffoldState2;
                        final float f5 = peekHeightPx;
                        final int i16 = i14;
                        final Shape shape3 = shape2;
                        final long j4 = j;
                        final long j5 = j2;
                        final float f6 = f;
                        final int i17 = i15;
                        final float f7 = f2;
                        final float f8 = f3;
                        final Function2<Composer, Integer, Unit> function6 = frontLayerContent;
                        final long j6 = j3;
                        final Function3<SnackbarHostState, Composer, Integer, Unit> function7 = function5;
                        BackdropScaffoldKt.BackdropStack(modifierFillMaxSize$default, function2, function1, ComposableLambdaKt.composableLambda($composer3, 1800047509, true, new Function4<Constraints, Float, Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(Constraints constraints, Float f9, Composer composer, Integer num) {
                                m988invokejYbf7pk(constraints.getValue(), f9.floatValue(), composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Code duplicated, block: B:48:0x01cc  */
                            /* JADX INFO: renamed from: invoke-jYbf7pk, reason: not valid java name */
                            public final void m988invokejYbf7pk(long constraints, float backLayerHeight, Composer $composer4, int $changed3) {
                                float revealedHeight;
                                Modifier.Companion companionNestedScroll$default;
                                Object value$iv$iv2;
                                float fM5274constructorimpl;
                                Function0<ComposeUiNode> function0;
                                ComposerKt.sourceInformation($composer4, "CP(1:c#ui.unit.Constraints)344@15194L57,342@15132L942,366@16117L420:BackdropScaffold.kt#jmzs0o");
                                int $dirty8 = $changed3;
                                if (($changed3 & 14) == 0) {
                                    $dirty8 |= $composer4.changed(constraints) ? 4 : 2;
                                }
                                if (($changed3 & 112) == 0) {
                                    $dirty8 |= $composer4.changed(backLayerHeight) ? 32 : 16;
                                }
                                if (($dirty8 & 731) != 146 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1800047509, $changed3, -1, "androidx.compose.material.BackdropScaffold.<anonymous>.<anonymous> (BackdropScaffold.kt:302)");
                                    }
                                    float fullHeight = Constraints.m5217getMaxHeightimpl(constraints);
                                    float revealedHeight2 = fullHeight - f4;
                                    if (!z4) {
                                        revealedHeight = revealedHeight2;
                                    } else {
                                        revealedHeight = Math.min(revealedHeight2, backLayerHeight);
                                    }
                                    if (z5) {
                                        companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(Modifier.INSTANCE, backdropScaffoldState3.getNestedScrollConnection(), null, 2, null);
                                    } else {
                                        companionNestedScroll$default = Modifier.INSTANCE;
                                    }
                                    Modifier nestedScroll = companionNestedScroll$default;
                                    Modifier modifierThen = Modifier.INSTANCE.then(nestedScroll);
                                    BackdropScaffoldState backdropScaffoldState4 = backdropScaffoldState3;
                                    Map mapMapOf = MapsKt.mapOf(TuplesKt.to(Float.valueOf(f5), BackdropValue.Concealed), TuplesKt.to(Float.valueOf(revealedHeight), BackdropValue.Revealed));
                                    Modifier modifierM1220swipeablepPrIpRY = SwipeableKt.m1220swipeablepPrIpRY(modifierThen, backdropScaffoldState4, mapMapOf, Orientation.Vertical, (288 & 8) != 0 ? true : z5, (288 & 16) != 0 ? false : false, (288 & 32) != 0 ? null : null, (288 & 64) != 0 ? new Function2<T, T, FixedThreshold>() { // from class: androidx.compose.material.SwipeableKt$swipeable$1
                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function2
                                        public final FixedThreshold invoke(T t, T t2) {
                                            return new FixedThreshold(Dp.m5274constructorimpl(56), null);
                                        }
                                    } : null, (288 & 128) != 0 ? SwipeableDefaults.resistanceConfig$default(SwipeableDefaults.INSTANCE, mapMapOf.keySet(), 0.0f, 0.0f, 6, null) : null, (288 & 256) != 0 ? SwipeableDefaults.INSTANCE.m1219getVelocityThresholdD9Ej5fM() : 0.0f);
                                    final BackdropScaffoldState backdropScaffoldState5 = backdropScaffoldState3;
                                    final CoroutineScope coroutineScope = scope;
                                    Modifier swipeable = SemanticsModifierKt.semantics$default(modifierM1220swipeablepPrIpRY, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1$1$swipeable$1
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
                                            if (backdropScaffoldState5.isConcealed()) {
                                                final BackdropScaffoldState backdropScaffoldState6 = backdropScaffoldState5;
                                                final CoroutineScope coroutineScope2 = coroutineScope;
                                                SemanticsPropertiesKt.collapse$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1$1$swipeable$1.1
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        if (backdropScaffoldState6.getConfirmStateChange$material_release().invoke(BackdropValue.Revealed).booleanValue()) {
                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new C00411(backdropScaffoldState6, null), 3, null);
                                                        }
                                                        return true;
                                                    }

                                                    /* JADX INFO: renamed from: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1$1$swipeable$1$1$1, reason: invalid class name and collision with other inner class name */
                                                    /* JADX INFO: compiled from: BackdropScaffold.kt */
                                                    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                                    @DebugMetadata(c = "androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1$1$swipeable$1$1$1", f = "BackdropScaffold.kt", i = {}, l = {330}, m = "invokeSuspend", n = {}, s = {})
                                                    static final class C00411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ BackdropScaffoldState $scaffoldState;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        C00411(BackdropScaffoldState backdropScaffoldState, Continuation<? super C00411> continuation) {
                                                            super(2, continuation);
                                                            this.$scaffoldState = backdropScaffoldState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new C00411(this.$scaffoldState, continuation);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                            return ((C00411) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Object invokeSuspend(Object $result) {
                                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                            switch (this.label) {
                                                                case 0:
                                                                    ResultKt.throwOnFailure($result);
                                                                    this.label = 1;
                                                                    if (this.$scaffoldState.reveal(this) == coroutine_suspended) {
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
                                            } else {
                                                final BackdropScaffoldState backdropScaffoldState7 = backdropScaffoldState5;
                                                final CoroutineScope coroutineScope3 = coroutineScope;
                                                SemanticsPropertiesKt.expand$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1$1$swipeable$1.2
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        if (backdropScaffoldState7.getConfirmStateChange$material_release().invoke(BackdropValue.Concealed).booleanValue()) {
                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(backdropScaffoldState7, null), 3, null);
                                                        }
                                                        return true;
                                                    }

                                                    /* JADX INFO: renamed from: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1$1$swipeable$1$2$1, reason: invalid class name */
                                                    /* JADX INFO: compiled from: BackdropScaffold.kt */
                                                    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                                    @DebugMetadata(c = "androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1$1$swipeable$1$2$1", f = "BackdropScaffold.kt", i = {}, l = {336}, m = "invokeSuspend", n = {}, s = {})
                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ BackdropScaffoldState $scaffoldState;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        AnonymousClass1(BackdropScaffoldState backdropScaffoldState, Continuation<? super AnonymousClass1> continuation) {
                                                            super(2, continuation);
                                                            this.$scaffoldState = backdropScaffoldState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new AnonymousClass1(this.$scaffoldState, continuation);
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
                                                                    if (this.$scaffoldState.conceal(this) == coroutine_suspended) {
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
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    Object key1$iv2 = backdropScaffoldState3;
                                    final BackdropScaffoldState backdropScaffoldState6 = backdropScaffoldState3;
                                    int i18 = (i16 >> 12) & 14;
                                    $composer4.startReplaceableGroup(1157296644);
                                    ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                    boolean invalid$iv$iv2 = $composer4.changed(key1$iv2);
                                    Object it$iv$iv2 = $composer4.rememberedValue();
                                    if (invalid$iv$iv2 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                                        value$iv$iv2 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1$1$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ IntOffset invoke(Density density) {
                                                return IntOffset.m5383boximpl(m989invokeBjo55l4(density));
                                            }

                                            /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                                            public final long m989invokeBjo55l4(Density offset) {
                                                Intrinsics.checkNotNullParameter(offset, "$this$offset");
                                                return IntOffsetKt.IntOffset(0, MathKt.roundToInt(backdropScaffoldState6.getOffset().getValue().floatValue()));
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv$iv2);
                                    } else {
                                        value$iv$iv2 = it$iv$iv2;
                                    }
                                    $composer4.endReplaceableGroup();
                                    Modifier modifierThen2 = OffsetKt.offset(companion, (Function1) value$iv$iv2).then(swipeable);
                                    Shape shape4 = shape3;
                                    long j7 = j4;
                                    long j8 = j5;
                                    float f9 = f6;
                                    final float f10 = f8;
                                    final Function2<Composer, Integer, Unit> function8 = function6;
                                    final int i19 = i16;
                                    final long j9 = j6;
                                    final BackdropScaffoldState backdropScaffoldState7 = backdropScaffoldState3;
                                    final int i20 = i17;
                                    final boolean z6 = z5;
                                    final CoroutineScope coroutineScope2 = scope;
                                    ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, -1065299503, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt.BackdropScaffold.1.1.2
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

                                        public final void invoke(Composer $composer5, int $changed4) {
                                            Function0<ComposeUiNode> function9;
                                            ComposerKt.sourceInformation($composer5, "C351@15517L543:BackdropScaffold.kt#jmzs0o");
                                            if (($changed4 & 11) != 2 || !$composer5.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1065299503, $changed4, -1, "androidx.compose.material.BackdropScaffold.<anonymous>.<anonymous>.<anonymous> (BackdropScaffold.kt:350)");
                                                }
                                                Modifier modifier$iv = PaddingKt.m491paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, f10, 7, null);
                                                Function2<Composer, Integer, Unit> function10 = function8;
                                                int i21 = i19;
                                                long j10 = j9;
                                                final BackdropScaffoldState backdropScaffoldState8 = backdropScaffoldState7;
                                                int i22 = i20;
                                                final boolean z7 = z6;
                                                final CoroutineScope coroutineScope3 = coroutineScope2;
                                                $composer5.startReplaceableGroup(733328855);
                                                ComposerKt.sourceInformation($composer5, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                                                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                                MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                                int $changed$iv$iv = (0 << 3) & 112;
                                                $composer5.startReplaceableGroup(-1323940314);
                                                ComposerKt.sourceInformation($composer5, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                                CompositionLocalMap localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
                                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
                                                int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                                                if (!($composer5.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer5.startReusableNode();
                                                if ($composer5.getInserting()) {
                                                    function9 = constructor;
                                                    $composer5.createNode(function9);
                                                } else {
                                                    function9 = constructor;
                                                    $composer5.useNode();
                                                }
                                                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer5);
                                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                                }
                                                function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                                $composer5.startReplaceableGroup(2058660585);
                                                int i23 = ($changed$iv$iv$iv >> 9) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer5, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                int i24 = ((0 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer5, 967343184, "C352@15582L19,353@15622L420:BackdropScaffold.kt#jmzs0o");
                                                function10.invoke($composer5, Integer.valueOf((i21 >> 6) & 14));
                                                BackdropScaffoldKt.m986Scrim3JVO9M(j10, new Function0<Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1$1$2$1$1
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
                                                        if (z7 && backdropScaffoldState8.getConfirmStateChange$material_release().invoke(BackdropValue.Concealed).booleanValue()) {
                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(backdropScaffoldState8, null), 3, null);
                                                        }
                                                    }

                                                    /* JADX INFO: renamed from: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1$1$2$1$1$1, reason: invalid class name */
                                                    /* JADX INFO: compiled from: BackdropScaffold.kt */
                                                    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                                    @DebugMetadata(c = "androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$1$1$2$1$1$1", f = "BackdropScaffold.kt", i = {}, l = {358}, m = "invokeSuspend", n = {}, s = {})
                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ BackdropScaffoldState $scaffoldState;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        AnonymousClass1(BackdropScaffoldState backdropScaffoldState, Continuation<? super AnonymousClass1> continuation) {
                                                            super(2, continuation);
                                                            this.$scaffoldState = backdropScaffoldState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new AnonymousClass1(this.$scaffoldState, continuation);
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
                                                                    if (this.$scaffoldState.conceal(this) == coroutine_suspended) {
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
                                                }, backdropScaffoldState8.getTargetValue() == BackdropValue.Revealed, $composer5, (i22 >> 18) & 14);
                                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                                $composer5.endReplaceableGroup();
                                                $composer5.endNode();
                                                $composer5.endReplaceableGroup();
                                                $composer5.endReplaceableGroup();
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer5.skipToGroupEnd();
                                        }
                                    });
                                    int i21 = i17;
                                    SurfaceKt.m1210SurfaceFjzlyU(modifierThen2, shape4, j7, j8, null, f9, composableLambda, $composer4, ((i21 >> 3) & 112) | 1572864 | ((i21 >> 6) & 896) | ((i21 >> 6) & 7168) | ((i21 << 6) & 458752), 16);
                                    Modifier.Companion companion2 = Modifier.INSTANCE;
                                    if (backdropScaffoldState3.isRevealed()) {
                                        if (revealedHeight == fullHeight - f4) {
                                            fM5274constructorimpl = f7;
                                        } else {
                                            fM5274constructorimpl = Dp.m5274constructorimpl(0);
                                        }
                                    } else {
                                        fM5274constructorimpl = Dp.m5274constructorimpl(0);
                                    }
                                    Modifier modifier$iv = PaddingKt.m491paddingqDBjuR0$default(companion2, 0.0f, 0.0f, 0.0f, fM5274constructorimpl, 7, null);
                                    Alignment contentAlignment$iv = Alignment.INSTANCE.getBottomCenter();
                                    Function3<SnackbarHostState, Composer, Integer, Unit> function9 = function7;
                                    BackdropScaffoldState backdropScaffoldState8 = backdropScaffoldState3;
                                    int i22 = i17;
                                    $composer4.startReplaceableGroup(733328855);
                                    ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                    int $changed$iv$iv = (48 << 3) & 112;
                                    $composer4.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                                    CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
                                    int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                                    if (!($composer4.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    $composer4.startReusableNode();
                                    if ($composer4.getInserting()) {
                                        function0 = constructor;
                                        $composer4.createNode(function0);
                                    } else {
                                        function0 = constructor;
                                        $composer4.useNode();
                                    }
                                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer4);
                                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                    }
                                    function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                    $composer4.startReplaceableGroup(2058660585);
                                    int i23 = ($changed$iv$iv$iv >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    int i24 = ((48 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer4, 1395535663, "C375@16478L45:BackdropScaffold.kt#jmzs0o");
                                    function9.invoke(backdropScaffoldState8.getSnackbarHostState(), $composer4, Integer.valueOf((i22 >> 18) & 112));
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    ComposerKt.sourceInformationMarkerEnd($composer4);
                                    $composer4.endReplaceableGroup();
                                    $composer4.endNode();
                                    $composer4.endReplaceableGroup();
                                    $composer4.endReplaceableGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        }), $composer3, 3120);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, (($dirty3 << 6) & 896) | 1572864 | (($dirty3 << 6) & 7168), 51);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            persistentAppBar3 = persistentAppBar4;
            scaffoldState4 = scaffoldState6;
            gesturesEnabled3 = gesturesEnabled6;
            peekHeight3 = peekHeight2;
            headerHeight3 = headerHeight2;
            modifier3 = modifier2;
            stickyFrontLayer3 = stickyFrontLayer2;
            backLayerBackgroundColor3 = backLayerBackgroundColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier3;
        final BackdropScaffoldState backdropScaffoldState3 = scaffoldState4;
        final boolean z4 = gesturesEnabled3;
        final float f4 = peekHeight3;
        final float f5 = headerHeight3;
        final boolean z5 = persistentAppBar3;
        final boolean z6 = stickyFrontLayer3;
        final long j4 = backLayerBackgroundColor3;
        final long j5 = backLayerContentColor3;
        final Shape shape3 = frontLayerShape3;
        final float f6 = frontLayerElevation2;
        final long j6 = frontLayerBackgroundColor4;
        final long j7 = frontLayerContentColor3;
        final long j8 = frontLayerScrimColor2;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function6 = function3M1072getLambda1$material_release;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropScaffold$2
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

            public final void invoke(Composer composer, int i16) {
                BackdropScaffoldKt.m985BackdropScaffoldBZszfkY(appBar, backLayerContent, frontLayerContent, modifier6, backdropScaffoldState3, z4, f4, f5, z5, z6, j4, j5, shape3, f6, j6, j7, j8, function6, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Scrim-3J-VO9M, reason: not valid java name */
    public static final void m986Scrim3JVO9M(final long color, final Function0<Unit> function0, final boolean visible, Composer $composer, final int $changed) {
        Modifier.Companion dismissModifier;
        Composer $composer2 = $composer.startRestartGroup(-92141505);
        ComposerKt.sourceInformation($composer2, "C(Scrim)P(0:c#ui.graphics.Color)388@16708L121,401@17118L62,397@17009L171:BackdropScaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(color) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(visible) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 731) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-92141505, $dirty2, -1, "androidx.compose.material.Scrim (BackdropScaffold.kt:382)");
            }
            if (color != Color.INSTANCE.m3007getUnspecified0d7_KjU()) {
                final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(visible ? 1.0f : 0.0f, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer2, 48, 28);
                $composer2.startReplaceableGroup(1010547004);
                ComposerKt.sourceInformation($composer2, "393@16915L37");
                if (visible) {
                    Modifier.Companion companion = Modifier.INSTANCE;
                    Unit unit = Unit.INSTANCE;
                    int i = ($dirty2 >> 3) & 14;
                    $composer2.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                    boolean invalid$iv$iv = $composer2.changed(function0);
                    BackdropScaffoldKt$Scrim$dismissModifier$1$1 value$iv$iv = $composer2.rememberedValue();
                    if (invalid$iv$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new BackdropScaffoldKt$Scrim$dismissModifier$1$1(function0, null);
                        $composer2.updateRememberedValue(value$iv$iv);
                    }
                    $composer2.endReplaceableGroup();
                    dismissModifier = SuspendingPointerInputFilterKt.pointerInput(companion, unit, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv);
                } else {
                    dismissModifier = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissModifier);
                Object key1$iv = Color.m2961boximpl(color);
                int i2 = $dirty2 & 14;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                boolean invalid$iv$iv2 = $composer2.changed(key1$iv) | $composer2.changed(stateAnimateFloatAsState);
                Object value$iv$iv2 = $composer2.rememberedValue();
                if (!invalid$iv$iv2) {
                    Object key1$iv2 = Composer.INSTANCE.getEmpty();
                    if (value$iv$iv2 == key1$iv2) {
                    }
                    $composer2.endReplaceableGroup();
                    CanvasKt.Canvas(modifierThen, (Function1) value$iv$iv2, $composer2, 0);
                }
                value$iv$iv2 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$Scrim$1$1
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
                        DrawScope.CC.m3522drawRectnJ9OG0$default(Canvas, color, 0L, 0L, BackdropScaffoldKt.Scrim_3J_VO9M$lambda$4(stateAnimateFloatAsState), null, null, 0, 118, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv2);
                $composer2.endReplaceableGroup();
                CanvasKt.Canvas(modifierThen, (Function1) value$iv$iv2, $composer2, 0);
            }
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$Scrim$2
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
                BackdropScaffoldKt.m986Scrim3JVO9M(color, function0, visible, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Scrim_3J_VO9M$lambda$4(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BackLayerTransition(final BackdropValue target, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Composer $composer2;
        Function0<ComposeUiNode> function4;
        Composer $composer3 = $composer.startRestartGroup(-950970976);
        ComposerKt.sourceInformation($composer3, "C(BackLayerTransition)P(2)421@17840L112,*424@18002L7,429@18176L486:BackdropScaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(target) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 731) != 146 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-950970976, $dirty2, -1, "androidx.compose.material.BackLayerTransition (BackdropScaffold.kt:414)");
            }
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(target == BackdropValue.Revealed ? 0.0f : 2.0f, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer3, 48, 28);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$BackLayerTransition_u24lambda_u248 = (Density) objConsume;
            float animationSlideOffset = $this$BackLayerTransition_u24lambda_u248.mo327toPx0680j_4(AnimationSlideOffset);
            float f = 1;
            float appBarFloat = RangesKt.coerceIn(BackLayerTransition$lambda$7(stateAnimateFloatAsState) - f, 0.0f, 1.0f);
            float contentFloat = RangesKt.coerceIn(f - BackLayerTransition$lambda$7(stateAnimateFloatAsState), 0.0f, 1.0f);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1538629162, "C430@18190L226,438@18425L231:BackdropScaffold.kt#jmzs0o");
            Modifier modifier$iv2 = GraphicsLayerModifierKt.m3127graphicsLayerAp8cVGQ$default(ZIndexModifierKt.zIndex(Modifier.INSTANCE, appBarFloat), 0.0f, 0.0f, appBarFloat, 0.0f, (f - appBarFloat) * animationSlideOffset, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131051, null);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv2 = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor2;
                $composer3.createNode(function1);
            } else {
                function1 = constructor2;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2603constructorimpl($composer3);
            $composer2 = $composer3;
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
            }
            function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i3 = ($changed$iv$iv$iv2 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i4 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -481855289, "C436@18398L8:BackdropScaffold.kt#jmzs0o");
            function2.invoke($composer3, Integer.valueOf(($dirty2 >> 3) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            Modifier modifier$iv3 = GraphicsLayerModifierKt.m3127graphicsLayerAp8cVGQ$default(ZIndexModifierKt.zIndex(Modifier.INSTANCE, contentFloat), 0.0f, 0.0f, contentFloat, 0.0f, (f - contentFloat) * (-animationSlideOffset), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131051, null);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv3 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv3, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv3 = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifier$iv3);
            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function4 = constructor3;
                $composer3.createNode(function4);
            } else {
                function4 = constructor3;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
            }
            function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i5 = ($changed$iv$iv$iv3 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -481855050, "C444@18637L9:BackdropScaffold.kt#jmzs0o");
            function3.invoke($composer3, Integer.valueOf(($dirty2 >> 6) & 14));
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
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt.BackLayerTransition.2
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
                BackdropScaffoldKt.BackLayerTransition(target, function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    private static final float BackLayerTransition$lambda$7(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BackdropStack(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function1<? super Constraints, Constraints> function1, final Function4<? super Constraints, ? super Float, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed) {
        Object value$iv$iv;
        Composer $composer2 = $composer.startRestartGroup(-1248995194);
        ComposerKt.sourceInformation($composer2, "C(BackdropStack)P(3)457@18967L890,457@18940L917:BackdropScaffold.kt#jmzs0o");
        final int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? 2048 : 1024;
        }
        if (($dirty & 5851) != 1170 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1248995194, $dirty, -1, "androidx.compose.material.BackdropStack (BackdropScaffold.kt:451)");
            }
            int i = (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty >> 3) & 896);
            $composer2.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(function2) | $composer2.changed(function1) | $composer2.changed(function4);
            Object it$iv$iv = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropStack$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                        return m991invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                    }

                    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m991invoke0kLqBqw(SubcomposeMeasureScope SubcomposeLayout, final long constraints) {
                        Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                        final Placeable backLayerPlaceable = ((Measurable) CollectionsKt.first((List) SubcomposeLayout.subcompose(BackdropLayers.Back, function2))).mo4225measureBRTryo0(function1.invoke(Constraints.m5206boximpl(constraints)).getValue());
                        final float backLayerHeight = backLayerPlaceable.getHeight();
                        BackdropLayers backdropLayers = BackdropLayers.Front;
                        final Function4<Constraints, Float, Composer, Integer, Unit> function5 = function4;
                        final int i2 = $dirty;
                        List<Measurable> listSubcompose = SubcomposeLayout.subcompose(backdropLayers, ComposableLambdaKt.composableLambdaInstance(-1222642649, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropStack$1$1$placeables$1
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
                                ComposerKt.sourceInformation($composer3, "C466@19305L40:BackdropScaffold.kt#jmzs0o");
                                if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                    $composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1222642649, $changed2, -1, "androidx.compose.material.BackdropStack.<anonymous>.<anonymous>.<anonymous> (BackdropScaffold.kt:465)");
                                }
                                function5.invoke(Constraints.m5206boximpl(constraints), Float.valueOf(backLayerHeight), $composer3, Integer.valueOf((i2 >> 3) & 896));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        List target$iv = new ArrayList(listSubcompose.size());
                        int index$iv$iv = 0;
                        int size = listSubcompose.size();
                        while (index$iv$iv < size) {
                            Object item$iv$iv = listSubcompose.get(index$iv$iv);
                            target$iv.add(((Measurable) item$iv$iv).mo4225measureBRTryo0(constraints));
                            index$iv$iv++;
                            backLayerHeight = backLayerHeight;
                            listSubcompose = listSubcompose;
                        }
                        final List placeables = target$iv;
                        int maxWidth = Math.max(Constraints.m5220getMinWidthimpl(constraints), backLayerPlaceable.getWidth());
                        int maxHeight = Math.max(Constraints.m5219getMinHeightimpl(constraints), backLayerPlaceable.getHeight());
                        int size2 = placeables.size();
                        for (int index$iv = 0; index$iv < size2; index$iv++) {
                            Object item$iv = placeables.get(index$iv);
                            Placeable it = (Placeable) item$iv;
                            maxWidth = Math.max(maxWidth, it.getWidth());
                            maxHeight = Math.max(maxHeight, it.getHeight());
                        }
                        return MeasureScope.CC.layout$default(SubcomposeLayout, maxWidth, maxHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt$BackdropStack$1$1.2
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
                                Placeable.PlacementScope.placeRelative$default(layout, backLayerPlaceable, 0, 0, 0.0f, 4, null);
                                List<Placeable> list = placeables;
                                int size3 = list.size();
                                for (int index$iv2 = 0; index$iv2 < size3; index$iv2++) {
                                    Object item$iv2 = list.get(index$iv2);
                                    Placeable it2 = (Placeable) item$iv2;
                                    Placeable.PlacementScope.placeRelative$default(layout, it2, 0, 0, 0.0f, 4, null);
                                }
                            }
                        }, 4, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            SubcomposeLayoutKt.SubcomposeLayout(modifier, (Function2) value$iv$iv, $composer2, $dirty & 14, 0);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BackdropScaffoldKt.BackdropStack.2
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
                BackdropScaffoldKt.BackdropStack(modifier, function2, function1, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }
}
