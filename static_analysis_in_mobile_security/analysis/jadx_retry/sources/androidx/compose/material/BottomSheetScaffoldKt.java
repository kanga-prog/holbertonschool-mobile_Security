package androidx.compose.material;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.CornerBasedShape;
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
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.Velocity;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BottomSheetScaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ä\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u009c\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2-\u0010\u000b\u001a)\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00110\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\u001c\u0010\u001c\u001a\u0018\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00060\f¢\u0006\u0002\b\u001e¢\u0006\u0002\b\u001fH\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001aá\u0002\u0010\"\u001a\u00020\u00062\u001c\u0010#\u001a\u0018\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00060\f¢\u0006\u0002\b\u001e¢\u0006\u0002\b\u001f2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010$\u001a\u00020%2\u0015\b\u0002\u0010&\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010'¢\u0006\u0002\b\u001e2\u0019\b\u0002\u0010(\u001a\u0013\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00060\f¢\u0006\u0002\b\u001e2\u0015\b\u0002\u0010*\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010'¢\u0006\u0002\b\u001e2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00012\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010-\u001a\u00020\u00012 \b\u0002\u0010.\u001a\u001a\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u0006\u0018\u00010\f¢\u0006\u0002\b\u001e¢\u0006\u0002\b\u001f2\b\b\u0002\u0010/\u001a\u00020\n2\b\b\u0002\u00100\u001a\u00020\u00152\b\b\u0002\u00101\u001a\u00020\u00012\b\b\u0002\u00102\u001a\u00020\u00182\b\b\u0002\u00103\u001a\u00020\u00182\b\b\u0002\u00104\u001a\u00020\u00182\b\b\u0002\u00105\u001a\u00020\u00182\b\b\u0002\u00106\u001a\u00020\u00182\u0017\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u00060\f¢\u0006\u0002\b\u001eH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b8\u00109\u001a\u001e\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00120;2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010<\u001a\u00020=H\u0002\u001aÈ\u0001\u0010>\u001a\u00020\u00062\u0013\u0010&\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010'¢\u0006\u0002\b\u001e2&\u0010?\u001a\"\u0012\u0013\u0012\u001107¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(@\u0012\u0004\u0012\u00020\u00060\f¢\u0006\u0002\b\u001e2&\u0010A\u001a\"\u0012\u0013\u0012\u00110B¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(C\u0012\u0004\u0012\u00020\u00060\f¢\u0006\u0002\b\u001e2\u0013\u0010*\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0018\u00010'¢\u0006\u0002\b\u001e2\u0011\u0010(\u001a\r\u0012\u0004\u0012\u00020\u00060'¢\u0006\u0002\b\u001e2\u0006\u0010-\u001a\u00020\u00012\u0006\u0010+\u001a\u00020,2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00130'2\u0006\u0010E\u001a\u00020\bH\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bF\u0010G\u001a4\u0010H\u001a\u00020\b2\u0006\u0010I\u001a\u00020\u00122\u000e\b\u0002\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00130K2\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\n0\fH\u0007\u001a>\u0010M\u001a\u00020\b2\u0006\u0010I\u001a\u00020\u00122\u0006\u0010N\u001a\u00020O2\u000e\b\u0002\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00130K2\u0014\b\u0002\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\n0\fH\u0007\u001a\u001c\u0010Q\u001a\u00020R2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030S2\u0006\u0010T\u001a\u00020UH\u0002\u001a+\u0010V\u001a\u00020%2\b\b\u0002\u0010W\u001a\u00020X2\b\b\u0002\u0010Y\u001a\u00020\b2\b\b\u0002\u0010Z\u001a\u00020)H\u0007¢\u0006\u0002\u0010[\u001a;\u0010\\\u001a\u00020\b2\u0006\u0010I\u001a\u00020\u00122\u000e\b\u0002\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00130K2\u0014\b\u0002\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\n0\fH\u0007¢\u0006\u0002\u0010]\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0004\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006^"}, d2 = {"BottomSheetScaffoldPositionalThreshold", "Landroidx/compose/ui/unit/Dp;", "F", "BottomSheetScaffoldVelocityThreshold", "FabSpacing", "BottomSheet", "", "state", "Landroidx/compose/material/BottomSheetState;", "sheetGesturesEnabled", "", "calculateAnchors", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/IntSize;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "sheetSize", "", "Landroidx/compose/material/BottomSheetValue;", "", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetElevation", "sheetBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "BottomSheet-0cLKjW4", "(Landroidx/compose/material/BottomSheetState;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/Shape;FJJLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomSheetScaffold", "sheetContent", "scaffoldState", "Landroidx/compose/material/BottomSheetScaffoldState;", "topBar", "Lkotlin/Function0;", "snackbarHost", "Landroidx/compose/material/SnackbarHostState;", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material/FabPosition;", "sheetPeekHeight", "drawerContent", "drawerGesturesEnabled", "drawerShape", "drawerElevation", "drawerBackgroundColor", "drawerContentColor", "drawerScrimColor", "backgroundColor", "contentColor", "Landroidx/compose/foundation/layout/PaddingValues;", "BottomSheetScaffold-bGncdBI", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/BottomSheetScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLandroidx/compose/ui/graphics/Shape;FJJFLkotlin/jvm/functions/Function3;ZLandroidx/compose/ui/graphics/Shape;FJJJJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;IIII)V", "BottomSheetScaffoldAnchorChangeCallback", "Landroidx/compose/material/AnchoredDraggableState$AnchorChangedCallback;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "BottomSheetScaffoldLayout", "body", "innerPadding", "bottomSheet", "", "layoutHeight", "sheetOffset", "sheetState", "BottomSheetScaffoldLayout-KCBPh4w", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;FILkotlin/jvm/functions/Function0;Landroidx/compose/material/BottomSheetState;Landroidx/compose/runtime/Composer;I)V", "BottomSheetScaffoldState", "initialValue", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "confirmStateChange", "BottomSheetState", "density", "Landroidx/compose/ui/unit/Density;", "confirmValueChange", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroidx/compose/material/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "rememberBottomSheetScaffoldState", "drawerState", "Landroidx/compose/material/DrawerState;", "bottomSheetState", "snackbarHostState", "(Landroidx/compose/material/DrawerState;Landroidx/compose/material/BottomSheetState;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomSheetScaffoldState;", "rememberBottomSheetState", "(Landroidx/compose/material/BottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomSheetState;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BottomSheetScaffoldKt {
    private static final float FabSpacing = Dp.m5274constructorimpl(16);
    private static final float BottomSheetScaffoldPositionalThreshold = Dp.m5274constructorimpl(56);
    private static final float BottomSheetScaffoldVelocityThreshold = Dp.m5274constructorimpl(125);

    public static /* synthetic */ BottomSheetState BottomSheetScaffoldState$default(BottomSheetValue bottomSheetValue, AnimationSpec animationSpec, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = SwipeableDefaults.INSTANCE.getAnimationSpec();
        }
        return BottomSheetScaffoldState(bottomSheetValue, animationSpec, function1);
    }

    @Deprecated(message = "This constructor is deprecated. confirmStateChange has been renamed to confirmValueChange.", replaceWith = @ReplaceWith(expression = "BottomSheetScaffoldState(initialValue, animationSpec, confirmStateChange)", imports = {}))
    public static final BottomSheetState BottomSheetScaffoldState(BottomSheetValue initialValue, AnimationSpec<Float> animationSpec, Function1<? super BottomSheetValue, Boolean> confirmStateChange) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(confirmStateChange, "confirmStateChange");
        return new BottomSheetState(initialValue, animationSpec, confirmStateChange);
    }

    public static /* synthetic */ BottomSheetState BottomSheetState$default(BottomSheetValue bottomSheetValue, Density density, AnimationSpec animationSpec, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            animationSpec = SwipeableDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i & 8) != 0) {
            function1 = new Function1<BottomSheetValue, Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt.BottomSheetState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(BottomSheetValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
        }
        return BottomSheetState(bottomSheetValue, density, animationSpec, function1);
    }

    public static final BottomSheetState BottomSheetState(BottomSheetValue initialValue, Density density, AnimationSpec<Float> animationSpec, Function1<? super BottomSheetValue, Boolean> confirmValueChange) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(confirmValueChange, "confirmValueChange");
        BottomSheetState it = new BottomSheetState(initialValue, animationSpec, confirmValueChange);
        it.setDensity$material_release(density);
        return it;
    }

    public static final BottomSheetState rememberBottomSheetState(final BottomSheetValue initialValue, final AnimationSpec<Float> animationSpec, final Function1<? super BottomSheetValue, Boolean> function1, Composer $composer, int $changed, int i) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        $composer.startReplaceableGroup(1808153344);
        ComposerKt.sourceInformation($composer, "C(rememberBottomSheetState)P(2)308@11241L7,309@11260L433:BottomSheetScaffold.kt#jmzs0o");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec2 = SwipeableDefaults.INSTANCE.getAnimationSpec();
            animationSpec = animationSpec2;
        }
        if ((i & 4) != 0) {
            Function1 confirmStateChange = new Function1<BottomSheetValue, Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt.rememberBottomSheetState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(BottomSheetValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
            function1 = confirmStateChange;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1808153344, $changed, -1, "androidx.compose.material.rememberBottomSheetState (BottomSheetScaffold.kt:303)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) objConsume;
        BottomSheetState bottomSheetState = (BottomSheetState) RememberSaveableKt.m2617rememberSaveable(new Object[]{animationSpec}, (Saver) BottomSheetState.INSTANCE.Saver(animationSpec, function1, density), (String) null, (Function0) new Function0<BottomSheetState>() { // from class: androidx.compose.material.BottomSheetScaffoldKt.rememberBottomSheetState.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BottomSheetState invoke() {
                return BottomSheetScaffoldKt.BottomSheetState(initialValue, density, animationSpec, function1);
            }
        }, $composer, 72, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return bottomSheetState;
    }

    public static final BottomSheetScaffoldState rememberBottomSheetScaffoldState(DrawerState drawerState, BottomSheetState bottomSheetState, SnackbarHostState snackbarHostState, Composer $composer, int $changed, int i) {
        Object value$iv$iv;
        Object value$iv$iv2;
        $composer.startReplaceableGroup(-1353009744);
        ComposerKt.sourceInformation($composer, "C(rememberBottomSheetScaffoldState)P(1)351@12567L39,352@12649L35,353@12729L32,355@12803L248:BottomSheetScaffold.kt#jmzs0o");
        if ((i & 1) != 0) {
            drawerState = DrawerKt.rememberDrawerState(DrawerValue.Closed, null, $composer, 6, 2);
        }
        if ((i & 2) != 0) {
            bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed, null, null, $composer, 6, 6);
        }
        if ((i & 4) != 0) {
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new SnackbarHostState();
                $composer.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv;
            }
            $composer.endReplaceableGroup();
            snackbarHostState = (SnackbarHostState) value$iv$iv2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1353009744, $changed, -1, "androidx.compose.material.rememberBottomSheetScaffoldState (BottomSheetScaffold.kt:350)");
        }
        int i2 = ($changed & 14) | ($changed & 112) | ($changed & 896);
        $composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(drawerState) | $composer.changed(bottomSheetState) | $composer.changed(snackbarHostState);
        Object it$iv$iv2 = $composer.rememberedValue();
        if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new BottomSheetScaffoldState(drawerState, bottomSheetState, snackbarHostState);
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv2;
        }
        $composer.endReplaceableGroup();
        BottomSheetScaffoldState bottomSheetScaffoldState = (BottomSheetScaffoldState) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return bottomSheetScaffoldState;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: BottomSheetScaffold-bGncdBI, reason: not valid java name */
    public static final void m1006BottomSheetScaffoldbGncdBI(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> sheetContent, Modifier modifier, BottomSheetScaffoldState scaffoldState, Function2<? super Composer, ? super Integer, Unit> function2, Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, int floatingActionButtonPosition, boolean sheetGesturesEnabled, Shape sheetShape, float sheetElevation, long sheetBackgroundColor, long sheetContentColor, float sheetPeekHeight, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function5, boolean drawerGesturesEnabled, Shape drawerShape, float drawerElevation, long drawerBackgroundColor, long drawerContentColor, long drawerScrimColor, long backgroundColor, long contentColor, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        int i2;
        long j;
        BottomSheetScaffoldState scaffoldState2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        boolean sheetGesturesEnabled2;
        CornerBasedShape sheetShape2;
        float sheetElevation2;
        long sheetBackgroundColor2;
        long sheetContentColor2;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function8;
        boolean drawerGesturesEnabled2;
        CornerBasedShape drawerShape2;
        long drawerBackgroundColor2;
        long drawerContentColor2;
        int i3;
        long drawerScrimColor2;
        long backgroundColor2;
        int $dirty2;
        int $dirty;
        Shape drawerShape3;
        float drawerElevation2;
        float sheetPeekHeight2;
        long contentColor2;
        long backgroundColor3;
        int $dirty3;
        int $dirty4;
        int $dirty1;
        final BottomSheetScaffoldState scaffoldState3;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function9;
        long drawerScrimColor3;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function10;
        int floatingActionButtonPosition2;
        float sheetPeekHeight3;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        boolean sheetGesturesEnabled3;
        Shape sheetShape3;
        Shape drawerShape4;
        float sheetElevation3;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function13;
        boolean drawerGesturesEnabled3;
        float drawerElevation3;
        long sheetContentColor3;
        long drawerScrimColor4;
        long drawerContentColor3;
        long sheetBackgroundColor3;
        long drawerBackgroundColor3;
        long backgroundColor4;
        long contentColor3;
        BottomSheetScaffoldState scaffoldState4;
        Modifier modifier3;
        Object value$iv$iv;
        Intrinsics.checkNotNullParameter(sheetContent, "sheetContent");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(46422755);
        ComposerKt.sourceInformation($composer2, "C(BottomSheetScaffold)P(15,12,13,22,21,10,11:c#material.FabPosition,18,20,17:c#ui.unit.Dp,14:c#ui.graphics.Color,16:c#ui.graphics.Color,19:c#ui.unit.Dp,4,7,9,6:c#ui.unit.Dp,3:c#ui.graphics.Color,5:c#ui.graphics.Color,8:c#ui.graphics.Color,0:c#ui.graphics.Color,2:c#ui.graphics.Color)417@16492L34,423@16861L6,425@16992L6,426@17039L37,430@17296L6,432@17411L6,433@17459L38,434@17544L10,435@17599L6,436@17644L32,*447@18055L7,499@20525L713:BottomSheetScaffold.kt#jmzs0o");
        int $dirty5 = $changed;
        int $dirty6 = $changed1;
        int $dirty7 = $changed2;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty5 |= $composer2.changedInstance(sheetContent) ? 4 : 2;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty5 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 896) == 0) {
            $dirty5 |= ((i & 4) == 0 && $composer2.changed(scaffoldState)) ? 256 : 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty5 |= 3072;
            function6 = function2;
        } else if (($changed & 7168) == 0) {
            function6 = function2;
            $dirty5 |= $composer2.changedInstance(function6) ? 2048 : 1024;
        } else {
            function6 = function2;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty5 |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty5 |= $composer2.changedInstance(function3) ? 16384 : 8192;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty5 |= $composer2.changedInstance(function4) ? 131072 : 65536;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty5 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty5 |= $composer2.changed(floatingActionButtonPosition) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty5 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty5 |= $composer2.changed(sheetGesturesEnabled) ? 8388608 : 4194304;
        }
        if (($changed & 234881024) == 0) {
            $dirty5 |= ((i & 256) == 0 && $composer2.changed(sheetShape)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty5 |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty5 |= $composer2.changed(sheetElevation) ? 536870912 : 268435456;
        }
        if (($changed1 & 14) == 0) {
            $dirty6 |= ((i & 1024) == 0 && $composer2.changed(sheetBackgroundColor)) ? 4 : 2;
        }
        if (($changed1 & 112) == 0) {
            $dirty6 |= ((i & 2048) == 0 && $composer2.changed(sheetContentColor)) ? 32 : 16;
        }
        int i11 = i & 4096;
        if (i11 != 0) {
            $dirty6 |= 384;
            i2 = i11;
        } else {
            i2 = i11;
            if (($changed1 & 896) == 0) {
                $dirty6 |= $composer2.changed(sheetPeekHeight) ? 256 : 128;
            }
        }
        int i12 = i & 8192;
        if (i12 != 0) {
            $dirty6 |= 3072;
        } else if (($changed1 & 7168) == 0) {
            $dirty6 |= $composer2.changedInstance(function5) ? 2048 : 1024;
        }
        int i13 = i & 16384;
        if (i13 != 0) {
            $dirty6 |= 24576;
        } else if (($changed1 & 57344) == 0) {
            $dirty6 |= $composer2.changed(drawerGesturesEnabled) ? 16384 : 8192;
        }
        if (($changed1 & 458752) == 0) {
            $dirty6 |= ((i & 32768) == 0 && $composer2.changed(drawerShape)) ? 131072 : 65536;
        }
        int i14 = i & 65536;
        if (i14 != 0) {
            $dirty6 |= 1572864;
        } else if (($changed1 & 3670016) == 0) {
            $dirty6 |= $composer2.changed(drawerElevation) ? 1048576 : 524288;
        }
        if (($changed1 & 29360128) == 0) {
            $dirty6 |= ((i & 131072) == 0 && $composer2.changed(drawerBackgroundColor)) ? 8388608 : 4194304;
        }
        if (($changed1 & 234881024) == 0) {
            $dirty6 |= ((i & 262144) == 0 && $composer2.changed(drawerContentColor)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed1 & 1879048192) == 0) {
            $dirty6 |= ((i & 524288) == 0 && $composer2.changed(drawerScrimColor)) ? 536870912 : 268435456;
        }
        int $dirty8 = $dirty6;
        if (($changed2 & 14) == 0) {
            $dirty7 |= ((i & 1048576) == 0 && $composer2.changed(backgroundColor)) ? 4 : 2;
        }
        if (($changed2 & 112) == 0) {
            if ((2097152 & i) == 0) {
                j = contentColor;
                int i15 = $composer2.changed(j) ? 32 : 16;
                $dirty7 |= i15;
            } else {
                j = contentColor;
            }
            $dirty7 |= i15;
        } else {
            j = contentColor;
        }
        if ((4194304 & i) != 0) {
            $dirty7 |= 384;
        } else if (($changed2 & 896) == 0) {
            $dirty7 |= $composer2.changedInstance(content) ? 256 : 128;
        }
        if ((1533916891 & $dirty5) == 306783378 && ($dirty8 & 1533916891) == 306783378 && ($dirty7 & 731) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            scaffoldState4 = scaffoldState;
            function10 = function3;
            function11 = function4;
            floatingActionButtonPosition2 = floatingActionButtonPosition;
            sheetGesturesEnabled3 = sheetGesturesEnabled;
            sheetShape3 = sheetShape;
            sheetElevation3 = sheetElevation;
            sheetBackgroundColor3 = sheetBackgroundColor;
            sheetContentColor3 = sheetContentColor;
            sheetPeekHeight3 = sheetPeekHeight;
            function13 = function5;
            drawerGesturesEnabled3 = drawerGesturesEnabled;
            drawerShape4 = drawerShape;
            drawerElevation3 = drawerElevation;
            drawerBackgroundColor3 = drawerBackgroundColor;
            drawerContentColor3 = drawerContentColor;
            drawerScrimColor4 = drawerScrimColor;
            backgroundColor4 = backgroundColor;
            function12 = function6;
            contentColor3 = j;
            modifier3 = modifier2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    scaffoldState2 = rememberBottomSheetScaffoldState(null, null, null, $composer2, 0, 7);
                    $dirty5 &= -897;
                } else {
                    scaffoldState2 = scaffoldState;
                }
                Function2<? super Composer, ? super Integer, Unit> function14 = i5 != 0 ? null : function2;
                Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function3M1073getLambda1$material_release = i6 != 0 ? ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1073getLambda1$material_release() : function3;
                function7 = i7 != 0 ? null : function4;
                int floatingActionButtonPosition3 = i8 != 0 ? FabPosition.INSTANCE.m1119getEnd5ygKITE() : floatingActionButtonPosition;
                sheetGesturesEnabled2 = i9 != 0 ? true : sheetGesturesEnabled;
                BottomSheetScaffoldState scaffoldState5 = scaffoldState2;
                if ((i & 256) != 0) {
                    sheetShape2 = MaterialTheme.INSTANCE.getShapes($composer2, 6).getLarge();
                    $dirty5 &= -234881025;
                } else {
                    sheetShape2 = sheetShape;
                }
                sheetElevation2 = i10 != 0 ? BottomSheetScaffoldDefaults.INSTANCE.m1003getSheetElevationD9Ej5fM() : sheetElevation;
                if ((i & 1024) != 0) {
                    sheetBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                    $dirty8 &= -15;
                } else {
                    sheetBackgroundColor2 = sheetBackgroundColor;
                }
                Function2<? super Composer, ? super Integer, Unit> function15 = function14;
                if ((i & 2048) != 0) {
                    sheetContentColor2 = ColorsKt.m1066contentColorForek8zF_U(sheetBackgroundColor2, $composer2, $dirty8 & 14);
                    $dirty8 &= -113;
                } else {
                    sheetContentColor2 = sheetContentColor;
                }
                float sheetPeekHeight4 = i2 != 0 ? BottomSheetScaffoldDefaults.INSTANCE.m1004getSheetPeekHeightD9Ej5fM() : sheetPeekHeight;
                function8 = i12 != 0 ? null : function5;
                drawerGesturesEnabled2 = i13 != 0 ? true : drawerGesturesEnabled;
                if ((i & 32768) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer2, 6).getLarge();
                    $dirty8 &= -458753;
                } else {
                    drawerShape2 = drawerShape;
                }
                float drawerElevation4 = i14 != 0 ? DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM() : drawerElevation;
                if ((i & 131072) != 0) {
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                    $dirty8 &= -29360129;
                } else {
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                if ((i & 262144) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer2, ($dirty8 >> 21) & 14);
                    $dirty8 &= -234881025;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 524288) != 0) {
                    i3 = 6;
                    drawerScrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                    $dirty8 &= -1879048193;
                } else {
                    i3 = 6;
                    drawerScrimColor2 = drawerScrimColor;
                }
                if ((i & 1048576) != 0) {
                    backgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, i3).m1041getBackground0d7_KjU();
                    $dirty2 = $dirty7 & (-15);
                } else {
                    backgroundColor2 = backgroundColor;
                    $dirty2 = $dirty7;
                }
                if ((i & 2097152) != 0) {
                    Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function16 = function3M1073getLambda1$material_release;
                    long contentColor4 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, $dirty2 & 14);
                    $dirty = $dirty5;
                    drawerShape3 = drawerShape2;
                    drawerElevation2 = drawerElevation4;
                    sheetPeekHeight2 = sheetPeekHeight4;
                    $dirty3 = $dirty2 & (-113);
                    $dirty4 = floatingActionButtonPosition3;
                    $dirty1 = $dirty8;
                    contentColor2 = contentColor4;
                    drawerScrimColor3 = drawerScrimColor2;
                    function6 = function15;
                    backgroundColor3 = backgroundColor2;
                    scaffoldState3 = scaffoldState5;
                    function9 = function16;
                } else {
                    Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function17 = function3M1073getLambda1$material_release;
                    $dirty = $dirty5;
                    drawerShape3 = drawerShape2;
                    drawerElevation2 = drawerElevation4;
                    sheetPeekHeight2 = sheetPeekHeight4;
                    contentColor2 = contentColor;
                    backgroundColor3 = backgroundColor2;
                    $dirty3 = $dirty2;
                    $dirty4 = floatingActionButtonPosition3;
                    $dirty1 = $dirty8;
                    scaffoldState3 = scaffoldState5;
                    function9 = function17;
                    drawerScrimColor3 = drawerScrimColor2;
                    function6 = function15;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty5 &= -897;
                }
                if ((i & 256) != 0) {
                    $dirty5 &= -234881025;
                }
                if ((i & 1024) != 0) {
                    $dirty8 &= -15;
                }
                if ((i & 2048) != 0) {
                    $dirty8 &= -113;
                }
                if ((32768 & i) != 0) {
                    $dirty8 &= -458753;
                }
                if ((i & 131072) != 0) {
                    $dirty8 &= -29360129;
                }
                if ((262144 & i) != 0) {
                    $dirty8 &= -234881025;
                }
                if ((i & 524288) != 0) {
                    $dirty8 &= -1879048193;
                }
                if ((i & 1048576) != 0) {
                    $dirty7 &= -15;
                }
                if ((2097152 & i) != 0) {
                    int i16 = $dirty7 & (-113);
                    function7 = function4;
                    $dirty4 = floatingActionButtonPosition;
                    sheetElevation2 = sheetElevation;
                    sheetBackgroundColor2 = sheetBackgroundColor;
                    sheetContentColor2 = sheetContentColor;
                    sheetPeekHeight2 = sheetPeekHeight;
                    function8 = function5;
                    drawerGesturesEnabled2 = drawerGesturesEnabled;
                    drawerShape3 = drawerShape;
                    drawerElevation2 = drawerElevation;
                    drawerBackgroundColor2 = drawerBackgroundColor;
                    drawerContentColor2 = drawerContentColor;
                    backgroundColor3 = backgroundColor;
                    $dirty = $dirty5;
                    $dirty3 = i16;
                    contentColor2 = j;
                    $dirty1 = $dirty8;
                    scaffoldState3 = scaffoldState;
                    function9 = function3;
                    sheetGesturesEnabled2 = sheetGesturesEnabled;
                    sheetShape2 = sheetShape;
                    drawerScrimColor3 = drawerScrimColor;
                } else {
                    function9 = function3;
                    function7 = function4;
                    sheetElevation2 = sheetElevation;
                    sheetBackgroundColor2 = sheetBackgroundColor;
                    sheetContentColor2 = sheetContentColor;
                    sheetPeekHeight2 = sheetPeekHeight;
                    function8 = function5;
                    drawerGesturesEnabled2 = drawerGesturesEnabled;
                    drawerShape3 = drawerShape;
                    drawerElevation2 = drawerElevation;
                    drawerBackgroundColor2 = drawerBackgroundColor;
                    drawerContentColor2 = drawerContentColor;
                    backgroundColor3 = backgroundColor;
                    $dirty = $dirty5;
                    $dirty3 = $dirty7;
                    contentColor2 = j;
                    $dirty1 = $dirty8;
                    scaffoldState3 = scaffoldState;
                    $dirty4 = floatingActionButtonPosition;
                    sheetGesturesEnabled2 = sheetGesturesEnabled;
                    sheetShape2 = sheetShape;
                    drawerScrimColor3 = drawerScrimColor;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(46422755, $dirty, $dirty1, "androidx.compose.material.BottomSheetScaffold (BottomSheetScaffold.kt:414)");
            }
            $composer2.startReplaceableGroup(1207995830);
            ComposerKt.sourceInformation($composer2, "441@17907L7,442@17934L72,442@17923L83");
            if (scaffoldState3.getBottomSheetState().getDensity() == null) {
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final Density density = (Density) objConsume;
                int i17 = ($dirty >> 6) & 14;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                boolean invalid$iv$iv = $composer2.changed(scaffoldState3) | $composer2.changed(density);
                Object it$iv$iv = $composer2.rememberedValue();
                if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$1
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
                            scaffoldState3.getBottomSheetState().setDensity$material_release(density);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
            }
            $composer2.endReplaceableGroup();
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$BottomSheetScaffold_bGncdBI_u24lambda_u244 = (Density) objConsume2;
            final float peekHeightPx = $this$BottomSheetScaffold_bGncdBI_u24lambda_u244.mo327toPx0680j_4(sheetPeekHeight2);
            final BottomSheetScaffoldState bottomSheetScaffoldState = scaffoldState3;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function6;
            final Function2<? super Composer, ? super Integer, Unit> function19 = function7;
            final float f = sheetPeekHeight2;
            final int i18 = $dirty4;
            final int i19 = $dirty;
            final int i20 = $dirty3;
            final int i21 = $dirty1;
            final boolean z = sheetGesturesEnabled2;
            final Shape shape = sheetShape2;
            final float f2 = sheetElevation2;
            final long j2 = sheetBackgroundColor2;
            final long j3 = sheetContentColor2;
            final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function20 = function9;
            Function2<Composer, Integer, Unit> function21 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$child$1
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

                /* JADX WARN: Code duplicated, block: B:21:0x010d  */
                /* JADX WARN: Code duplicated, block: B:24:? A[RETURN, SYNTHETIC] */
                public final void invoke(Composer $composer3, int $changed3) {
                    Object value$iv$iv2;
                    ComposerKt.sourceInformation($composer3, "C493@20277L50,449@18129L2385:BottomSheetScaffold.kt#jmzs0o");
                    if (($changed3 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(893101063, $changed3, -1, "androidx.compose.material.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:448)");
                        }
                        BottomSheetState bottomSheetState = bottomSheetScaffoldState.getBottomSheetState();
                        Function2<Composer, Integer, Unit> function22 = function18;
                        Function3<PaddingValues, Composer, Integer, Unit> function23 = content;
                        final boolean z2 = z;
                        final BottomSheetScaffoldState bottomSheetScaffoldState2 = bottomSheetScaffoldState;
                        final float f3 = f;
                        final float f4 = peekHeightPx;
                        final Shape shape2 = shape;
                        final float f5 = f2;
                        final long j4 = j2;
                        final long j5 = j3;
                        final Function3<ColumnScope, Composer, Integer, Unit> function24 = sheetContent;
                        final int i22 = i19;
                        final int i23 = i21;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer3, -1378534681, true, new Function3<Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$child$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Composer composer, Integer num2) {
                                invoke(num.intValue(), composer, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Code duplicated, block: B:39:0x0166  */
                            /* JADX WARN: Code duplicated, block: B:42:? A[RETURN, SYNTHETIC] */
                            public final void invoke(final int layoutHeight, Composer $composer4, int $changed4) {
                                Modifier.Companion companionNestedScroll$default;
                                Object value$iv$iv3;
                                ComposerKt.sourceInformation($composer4, "C469@19143L580,464@18881L1191:BottomSheetScaffold.kt#jmzs0o");
                                int $dirty9 = $changed4;
                                if (($changed4 & 14) == 0) {
                                    $dirty9 |= $composer4.changed(layoutHeight) ? 4 : 2;
                                }
                                int $dirty10 = $dirty9;
                                if (($dirty10 & 91) != 18 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1378534681, $dirty10, -1, "androidx.compose.material.BottomSheetScaffold.<anonymous>.<anonymous> (BottomSheetScaffold.kt:452)");
                                    }
                                    $composer4.startReplaceableGroup(-816851374);
                                    ComposerKt.sourceInformation($composer4, "456@18416L390");
                                    if (z2) {
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        Object key1$iv = bottomSheetScaffoldState2.getBottomSheetState().getAnchoredDraggableState$material_release();
                                        BottomSheetScaffoldState bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                                        $composer4.startReplaceableGroup(1157296644);
                                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                        boolean invalid$iv$iv2 = $composer4.changed(key1$iv);
                                        Object it$iv$iv2 = $composer4.rememberedValue();
                                        if (invalid$iv$iv2 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                                            value$iv$iv3 = BottomSheetScaffoldKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(bottomSheetScaffoldState3.getBottomSheetState().getAnchoredDraggableState$material_release(), Orientation.Vertical);
                                            $composer4.updateRememberedValue(value$iv$iv3);
                                        } else {
                                            value$iv$iv3 = it$iv$iv2;
                                        }
                                        $composer4.endReplaceableGroup();
                                        companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(companion, (NestedScrollConnection) value$iv$iv3, null, 2, null);
                                    } else {
                                        companionNestedScroll$default = Modifier.INSTANCE;
                                    }
                                    $composer4.endReplaceableGroup();
                                    Modifier nestedScroll = companionNestedScroll$default;
                                    BottomSheetState bottomSheetState2 = bottomSheetScaffoldState2.getBottomSheetState();
                                    Modifier modifierM525requiredHeightInVpY3zN4$default = SizeKt.m525requiredHeightInVpY3zN4$default(SizeKt.fillMaxWidth$default(nestedScroll, 0.0f, 1, null), f3, 0.0f, 2, null);
                                    boolean z3 = z2;
                                    Object key1$iv2 = Integer.valueOf(layoutHeight);
                                    Object key2$iv = Float.valueOf(f4);
                                    final float f6 = f4;
                                    int i24 = $dirty10 & 14;
                                    $composer4.startReplaceableGroup(511388516);
                                    ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2):Composables.kt#9igjgp");
                                    boolean invalid$iv$iv3 = $composer4.changed(key1$iv2) | $composer4.changed(key2$iv);
                                    Object value$iv$iv4 = $composer4.rememberedValue();
                                    if (!invalid$iv$iv3) {
                                        Object key2$iv2 = Composer.INSTANCE.getEmpty();
                                        if (value$iv$iv4 == key2$iv2) {
                                        }
                                        $composer4.endReplaceableGroup();
                                        Shape shape3 = shape2;
                                        float f7 = f5;
                                        long j6 = j4;
                                        long j7 = j5;
                                        Function3<ColumnScope, Composer, Integer, Unit> function25 = function24;
                                        int i25 = i22;
                                        int $dirty11 = i23;
                                        BottomSheetScaffoldKt.m1005BottomSheet0cLKjW4(bottomSheetState2, z3, (Function1) value$iv$iv4, shape3, f7, j6, j7, modifierM525requiredHeightInVpY3zN4$default, function25, $composer4, ((i25 >> 18) & 112) | ((i25 >> 15) & 7168) | ((i25 >> 15) & 57344) | (($dirty11 << 15) & 458752) | (($dirty11 << 15) & 3670016) | ((i25 << 24) & 234881024), 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    value$iv$iv4 = (Function1) new Function1<IntSize, Map<BottomSheetValue, ? extends Float>>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$child$1$1$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Map<BottomSheetValue, ? extends Float> invoke(IntSize intSize) {
                                            return m1011invokeozmzZPI(intSize.getPackedValue());
                                        }

                                        /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                                        public final Map<BottomSheetValue, Float> m1011invokeozmzZPI(long sheetSize) {
                                            float sheetHeight = IntSize.m5433getHeightimpl(sheetSize);
                                            float f8 = layoutHeight;
                                            float f9 = f6;
                                            float collapsedHeight = f8 - f9;
                                            if (!(sheetHeight == 0.0f)) {
                                                if (!(sheetHeight == f9)) {
                                                    return MapsKt.mapOf(TuplesKt.to(BottomSheetValue.Collapsed, Float.valueOf(collapsedHeight)), TuplesKt.to(BottomSheetValue.Expanded, Float.valueOf(layoutHeight - sheetHeight)));
                                                }
                                            }
                                            return MapsKt.mapOf(TuplesKt.to(BottomSheetValue.Collapsed, Float.valueOf(collapsedHeight)));
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv$iv4);
                                    $composer4.endReplaceableGroup();
                                    Shape shape4 = shape2;
                                    float f8 = f5;
                                    long j8 = j4;
                                    long j9 = j5;
                                    Function3<ColumnScope, Composer, Integer, Unit> function26 = function24;
                                    int i26 = i22;
                                    int $dirty12 = i23;
                                    BottomSheetScaffoldKt.m1005BottomSheet0cLKjW4(bottomSheetState2, z3, (Function1) value$iv$iv4, shape4, f8, j8, j9, modifierM525requiredHeightInVpY3zN4$default, function26, $composer4, ((i26 >> 18) & 112) | ((i26 >> 15) & 7168) | ((i26 >> 15) & 57344) | (($dirty12 << 15) & 458752) | (($dirty12 << 15) & 3670016) | ((i26 << 24) & 234881024), 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        });
                        Function2<Composer, Integer, Unit> function25 = function19;
                        final Function3<SnackbarHostState, Composer, Integer, Unit> function26 = function20;
                        final BottomSheetScaffoldState bottomSheetScaffoldState3 = bottomSheetScaffoldState;
                        final int i24 = i19;
                        ComposableLambda composableLambda2 = ComposableLambdaKt.composableLambda($composer3, -486138068, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$child$1.2
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

                            public final void invoke(Composer $composer4, int $changed4) {
                                ComposerKt.sourceInformation($composer4, "C491@20190L45:BottomSheetScaffold.kt#jmzs0o");
                                if (($changed4 & 11) == 2 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-486138068, $changed4, -1, "androidx.compose.material.BottomSheetScaffold.<anonymous>.<anonymous> (BottomSheetScaffold.kt:490)");
                                }
                                function26.invoke(bottomSheetScaffoldState3.getSnackbarHostState(), $composer4, Integer.valueOf((i24 >> 9) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        });
                        float f6 = f;
                        int i25 = i18;
                        Object key1$iv = bottomSheetScaffoldState;
                        final BottomSheetScaffoldState bottomSheetScaffoldState4 = bottomSheetScaffoldState;
                        int i26 = (i19 >> 6) & 14;
                        $composer3.startReplaceableGroup(1157296644);
                        ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                        boolean invalid$iv$iv2 = $composer3.changed(key1$iv);
                        Object it$iv$iv2 = $composer3.rememberedValue();
                        if (!invalid$iv$iv2) {
                            Object key1$iv2 = Composer.INSTANCE.getEmpty();
                            if (it$iv$iv2 != key1$iv2) {
                                value$iv$iv2 = it$iv$iv2;
                            }
                            $composer3.endReplaceableGroup();
                            Function0 function0 = (Function0) value$iv$iv2;
                            int i27 = i19;
                            BottomSheetScaffoldKt.m1007BottomSheetScaffoldLayoutKCBPh4w(function22, function23, composableLambda, function25, composableLambda2, f6, i25, function0, bottomSheetState, $composer3, ((i27 >> 9) & 14) | 24960 | ((i20 >> 3) & 112) | ((i27 >> 6) & 7168) | ((i21 << 9) & 458752) | (i27 & 3670016));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        value$iv$iv2 = (Function0) new Function0<Float>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$child$1$3$1
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(bottomSheetScaffoldState4.getBottomSheetState().requireOffset());
                            }
                        };
                        $composer3.updateRememberedValue(value$iv$iv2);
                        $composer3.endReplaceableGroup();
                        Function0 function1 = (Function0) value$iv$iv2;
                        int i28 = i19;
                        BottomSheetScaffoldKt.m1007BottomSheetScaffoldLayoutKCBPh4w(function22, function23, composableLambda, function25, composableLambda2, f6, i25, function1, bottomSheetState, $composer3, ((i28 >> 9) & 14) | 24960 | ((i20 >> 3) & 112) | ((i28 >> 6) & 7168) | ((i21 << 9) & 458752) | (i28 & 3670016));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            };
            Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function22 = function9;
            final Function2 child = ComposableLambdaKt.composableLambda($composer2, 893101063, true, function21);
            int floatingActionButtonPosition4 = $dirty4;
            final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function23 = function8;
            final BottomSheetScaffoldState bottomSheetScaffoldState2 = scaffoldState3;
            final boolean z2 = drawerGesturesEnabled2;
            final Shape shape2 = drawerShape3;
            final float f3 = drawerElevation2;
            final long j4 = drawerBackgroundColor2;
            final long j5 = drawerContentColor2;
            final long j6 = drawerScrimColor3;
            final int i22 = $dirty1;
            BottomSheetScaffoldState scaffoldState6 = scaffoldState3;
            SurfaceKt.m1210SurfaceFjzlyU(SizeKt.fillMaxSize$default(modifier2, 0.0f, 1, null), null, backgroundColor3, contentColor2, null, 0.0f, ComposableLambdaKt.composableLambda($composer2, 1273816607, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$2
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

                public final void invoke(Composer $composer3, int $changed3) {
                    ComposerKt.sourceInformation($composer3, "C:BottomSheetScaffold.kt#jmzs0o");
                    if (($changed3 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1273816607, $changed3, -1, "androidx.compose.material.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:504)");
                        }
                        if (function23 == null) {
                            $composer3.startReplaceableGroup(-249540336);
                            ComposerKt.sourceInformation($composer3, "506@20705L7");
                            child.invoke($composer3, 6);
                            $composer3.endReplaceableGroup();
                        } else {
                            $composer3.startReplaceableGroup(-249540299);
                            ComposerKt.sourceInformation($composer3, "508@20742L480");
                            Function3<ColumnScope, Composer, Integer, Unit> function24 = function23;
                            DrawerState drawerState = bottomSheetScaffoldState2.getDrawerState();
                            boolean z3 = z2;
                            Shape shape3 = shape2;
                            float f4 = f3;
                            long j7 = j4;
                            long j8 = j5;
                            long j9 = j6;
                            Function2<Composer, Integer, Unit> function25 = child;
                            int i23 = i22;
                            DrawerKt.m1091ModalDrawerGs3lGvM(function24, null, drawerState, z3, shape3, f4, j7, j8, j9, function25, $composer3, ((i23 >> 9) & 14) | 805306368 | ((i23 >> 3) & 7168) | ((i23 >> 3) & 57344) | ((i23 >> 3) & 458752) | ((i23 >> 3) & 3670016) | ((i23 >> 3) & 29360128) | ((i23 >> 3) & 234881024), 2);
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
            }), $composer2, (($dirty3 << 6) & 896) | 1572864 | (($dirty3 << 6) & 7168), 50);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function10 = function22;
            floatingActionButtonPosition2 = floatingActionButtonPosition4;
            sheetPeekHeight3 = sheetPeekHeight2;
            function11 = function7;
            function12 = function6;
            sheetGesturesEnabled3 = sheetGesturesEnabled2;
            sheetShape3 = sheetShape2;
            drawerShape4 = drawerShape3;
            sheetElevation3 = sheetElevation2;
            function13 = function8;
            drawerGesturesEnabled3 = drawerGesturesEnabled2;
            drawerElevation3 = drawerElevation2;
            sheetContentColor3 = sheetContentColor2;
            drawerScrimColor4 = drawerScrimColor3;
            drawerContentColor3 = drawerContentColor2;
            sheetBackgroundColor3 = sheetBackgroundColor2;
            drawerBackgroundColor3 = drawerBackgroundColor2;
            backgroundColor4 = backgroundColor3;
            contentColor3 = contentColor2;
            scaffoldState4 = scaffoldState6;
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier3;
        final BottomSheetScaffoldState bottomSheetScaffoldState3 = scaffoldState4;
        final Function2<? super Composer, ? super Integer, Unit> function24 = function12;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function25 = function10;
        final Function2<? super Composer, ? super Integer, Unit> function26 = function11;
        final int i23 = floatingActionButtonPosition2;
        final boolean z3 = sheetGesturesEnabled3;
        final Shape shape3 = sheetShape3;
        final float f4 = sheetElevation3;
        final long j7 = sheetBackgroundColor3;
        final long j8 = sheetContentColor3;
        final float f5 = sheetPeekHeight3;
        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function27 = function13;
        final boolean z4 = drawerGesturesEnabled3;
        final Shape shape4 = drawerShape4;
        final float f6 = drawerElevation3;
        final long j9 = drawerBackgroundColor3;
        final long j10 = drawerContentColor3;
        final long j11 = drawerScrimColor4;
        final long j12 = backgroundColor4;
        final long j13 = contentColor3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$3
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

            public final void invoke(Composer composer, int i24) {
                BottomSheetScaffoldKt.m1006BottomSheetScaffoldbGncdBI(sheetContent, modifier4, bottomSheetScaffoldState3, function24, function25, function26, i23, z3, shape3, f4, j7, j8, f5, function27, z4, shape4, f6, j9, j10, j11, j12, j13, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), RecomposeScopeImplKt.updateChangedFlags($changed2), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:104:0x0135 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:105:0x0137  */
    /* JADX WARN: Code duplicated, block: B:108:0x0142  */
    /* JADX WARN: Code duplicated, block: B:111:0x0186  */
    /* JADX WARN: Code duplicated, block: B:112:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:115:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:119:0x01f8 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:123:0x0286  */
    /* JADX WARN: Code duplicated, block: B:127:0x0291  */
    /* JADX WARN: Code duplicated, block: B:128:0x0296  */
    /* JADX INFO: renamed from: BottomSheet-0cLKjW4, reason: not valid java name */
    public static final void m1005BottomSheet0cLKjW4(final BottomSheetState state, final boolean sheetGesturesEnabled, final Function1<? super IntSize, ? extends Map<BottomSheetValue, Float>> function1, final Shape sheetShape, final float sheetElevation, final long sheetBackgroundColor, final long sheetContentColor, Modifier modifier, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        int i2;
        final int $dirty;
        Object it$iv$iv$iv;
        Object value$iv$iv$iv;
        boolean invalid$iv$iv;
        Object value$iv$iv;
        Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer $composer2 = $composer.startRestartGroup(1407045933);
        ComposerKt.sourceInformation($composer2, "C(BottomSheet)P(8,6!1,7,5:c#ui.unit.Dp,3:c#ui.graphics.Color,4:c#ui.graphics.Color,2)536@21671L24,537@21727L92,540@21824L1599:BottomSheetScaffold.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changed(sheetGesturesEnabled) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer2.changed(sheetShape) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if ((57344 & $changed) == 0) {
            $dirty2 |= $composer2.changed(sheetElevation) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty2 |= $composer2.changed(sheetBackgroundColor) ? 131072 : 65536;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if ((3670016 & $changed) == 0) {
            $dirty2 |= $composer2.changed(sheetContentColor) ? 1048576 : 524288;
        }
        int i3 = i & 128;
        if (i3 != 0) {
            $dirty2 |= 12582912;
            modifier2 = modifier;
        } else if ((29360128 & $changed) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 8388608 : 4194304;
        } else {
            modifier2 = modifier;
        }
        if ((i & 256) == 0) {
            if ((234881024 & $changed) == 0) {
                i2 = $composer2.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
            }
            $dirty = $dirty2;
            if ((191739611 & $dirty) == 38347922 || !$composer2.getSkipping()) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1407045933, $dirty, -1, "androidx.compose.material.BottomSheet (BottomSheetScaffold.kt:525)");
                }
                $composer2.startReplaceableGroup(773894976);
                ComposerKt.sourceInformation($composer2, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv$iv = $composer2.rememberedValue();
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
                int i4 = ($dirty & 14) | 64;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(state) | $composer2.changed(scope);
                Object it$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = BottomSheetScaffoldAnchorChangeCallback(state, scope);
                    $composer2.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                $composer2.endReplaceableGroup();
                final AnchoredDraggableState.AnchorChangedCallback anchorChangeCallback = (AnchoredDraggableState.AnchorChangedCallback) value$iv$iv;
                SurfaceKt.m1210SurfaceFjzlyU(SemanticsModifierKt.semantics$default(OnRemeasuredModifierKt.onSizeChanged(AnchoredDraggableKt.anchoredDraggable(modifier2, state.getAnchoredDraggableState$material_release(), Orientation.Vertical, (24 & 4) != 0 ? true : sheetGesturesEnabled, (24 & 8) != 0 ? false : false, (24 & 16) != 0 ? null : null), new Function1<IntSize, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                        m1010invokeozmzZPI(intSize.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                    public final void m1010invokeozmzZPI(long layoutSize) {
                        state.getAnchoredDraggableState$material_release().updateAnchors$material_release(function1.invoke(IntSize.m5426boximpl(layoutSize)), anchorChangeCallback);
                    }
                }), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2
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
                        if (state.getAnchoredDraggableState$material_release().getAnchors$material_release().size() > 1) {
                            if (state.isCollapsed()) {
                                final BottomSheetState bottomSheetState = state;
                                final CoroutineScope coroutineScope = scope;
                                SemanticsPropertiesKt.expand$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        if (bottomSheetState.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(BottomSheetValue.Expanded).booleanValue()) {
                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00421(bottomSheetState, null), 3, null);
                                        }
                                        return true;
                                    }

                                    /* JADX INFO: renamed from: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1$1, reason: invalid class name and collision with other inner class name */
                                    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                    @DebugMetadata(c = "androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1$1", f = "BottomSheetScaffold.kt", i = {}, l = {561}, m = "invokeSuspend", n = {}, s = {})
                                    static final class C00421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ BottomSheetState $state;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        C00421(BottomSheetState bottomSheetState, Continuation<? super C00421> continuation) {
                                            super(2, continuation);
                                            this.$state = bottomSheetState;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            return new C00421(this.$state, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((C00421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object $result) {
                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            switch (this.label) {
                                                case 0:
                                                    ResultKt.throwOnFailure($result);
                                                    this.label = 1;
                                                    if (this.$state.expand(this) == coroutine_suspended) {
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
                                final BottomSheetState bottomSheetState2 = state;
                                final CoroutineScope coroutineScope2 = scope;
                                SemanticsPropertiesKt.collapse$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        if (bottomSheetState2.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(BottomSheetValue.Collapsed).booleanValue()) {
                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(bottomSheetState2, null), 3, null);
                                        }
                                        return true;
                                    }

                                    /* JADX INFO: renamed from: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$2$1, reason: invalid class name */
                                    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                    @DebugMetadata(c = "androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$2$1", f = "BottomSheetScaffold.kt", i = {}, l = {568}, m = "invokeSuspend", n = {}, s = {})
                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ BottomSheetState $state;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        AnonymousClass1(BottomSheetState bottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                            super(2, continuation);
                                            this.$state = bottomSheetState;
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
                                                    if (this.$state.collapse(this) == coroutine_suspended) {
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
                    }
                }, 1, null), sheetShape, sheetBackgroundColor, sheetContentColor, null, sheetElevation, ComposableLambdaKt.composableLambda($composer2, 1944994153, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$3
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
                        ComposerKt.sourceInformation($composer3, "C578@23390L25:BottomSheetScaffold.kt#jmzs0o");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1944994153, $changed2, -1, "androidx.compose.material.BottomSheet.<anonymous> (BottomSheetScaffold.kt:578)");
                        }
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        int $changed$iv = ($dirty >> 15) & 7168;
                        $composer3.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                        int $changed$iv$iv = ($changed$iv << 3) & 112;
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
                            $composer3.createNode(constructor);
                        } else {
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
                        int i5 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, 276693570, "C77@3893L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer2, (($dirty >> 6) & 112) | 1572864 | (($dirty >> 9) & 896) | (($dirty >> 9) & 7168) | (($dirty << 3) & 458752), 16);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$4
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

                public final void invoke(Composer composer, int i5) {
                    BottomSheetScaffoldKt.m1005BottomSheet0cLKjW4(state, sheetGesturesEnabled, function1, sheetShape, sheetElevation, sheetBackgroundColor, sheetContentColor, modifier4, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 100663296;
        $dirty2 |= i2;
        $dirty = $dirty2;
        if ((191739611 & $dirty) == 38347922) {
            if (i3 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1407045933, $dirty, -1, "androidx.compose.material.BottomSheet (BottomSheetScaffold.kt:525)");
            }
            $composer2.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer2, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2));
                $composer2.updateRememberedValue(value$iv$iv$iv);
            } else {
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer2.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv2 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope2 = wrapper$iv2.getCoroutineScope();
            $composer2.endReplaceableGroup();
            int i5 = ($dirty & 14) | 64;
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(state) | $composer2.changed(scope2);
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (!invalid$iv$iv) {
            }
            value$iv$iv = BottomSheetScaffoldAnchorChangeCallback(state, scope2);
            $composer2.updateRememberedValue(value$iv$iv);
            $composer2.endReplaceableGroup();
            final AnchoredDraggableState.AnchorChangedCallback<BottomSheetValue> anchorChangeCallback2 = (AnchoredDraggableState.AnchorChangedCallback) value$iv$iv;
            SurfaceKt.m1210SurfaceFjzlyU(SemanticsModifierKt.semantics$default(OnRemeasuredModifierKt.onSizeChanged(AnchoredDraggableKt.anchoredDraggable(modifier2, state.getAnchoredDraggableState$material_release(), Orientation.Vertical, (24 & 4) != 0 ? true : sheetGesturesEnabled, (24 & 8) != 0 ? false : false, (24 & 16) != 0 ? null : null), new Function1<IntSize, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                    m1010invokeozmzZPI(intSize.getPackedValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                public final void m1010invokeozmzZPI(long layoutSize) {
                    state.getAnchoredDraggableState$material_release().updateAnchors$material_release(function1.invoke(IntSize.m5426boximpl(layoutSize)), anchorChangeCallback2);
                }
            }), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2
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
                    if (state.getAnchoredDraggableState$material_release().getAnchors$material_release().size() > 1) {
                        if (state.isCollapsed()) {
                            final BottomSheetState bottomSheetState = state;
                            final CoroutineScope coroutineScope = scope2;
                            SemanticsPropertiesKt.expand$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    if (bottomSheetState.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(BottomSheetValue.Expanded).booleanValue()) {
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00421(bottomSheetState, null), 3, null);
                                    }
                                    return true;
                                }

                                /* JADX INFO: renamed from: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1$1, reason: invalid class name and collision with other inner class name */
                                /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                @DebugMetadata(c = "androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1$1", f = "BottomSheetScaffold.kt", i = {}, l = {561}, m = "invokeSuspend", n = {}, s = {})
                                static final class C00421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ BottomSheetState $state;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C00421(BottomSheetState bottomSheetState, Continuation<? super C00421> continuation) {
                                        super(2, continuation);
                                        this.$state = bottomSheetState;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new C00421(this.$state, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((C00421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object $result) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch (this.label) {
                                            case 0:
                                                ResultKt.throwOnFailure($result);
                                                this.label = 1;
                                                if (this.$state.expand(this) == coroutine_suspended) {
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
                            final BottomSheetState bottomSheetState2 = state;
                            final CoroutineScope coroutineScope2 = scope2;
                            SemanticsPropertiesKt.collapse$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    if (bottomSheetState2.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(BottomSheetValue.Collapsed).booleanValue()) {
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(bottomSheetState2, null), 3, null);
                                    }
                                    return true;
                                }

                                /* JADX INFO: renamed from: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$2$1, reason: invalid class name */
                                /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                @DebugMetadata(c = "androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$2$1", f = "BottomSheetScaffold.kt", i = {}, l = {568}, m = "invokeSuspend", n = {}, s = {})
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ BottomSheetState $state;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(BottomSheetState bottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.$state = bottomSheetState;
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
                                                if (this.$state.collapse(this) == coroutine_suspended) {
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
                }
            }, 1, null), sheetShape, sheetBackgroundColor, sheetContentColor, null, sheetElevation, ComposableLambdaKt.composableLambda($composer2, 1944994153, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$3
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
                    ComposerKt.sourceInformation($composer3, "C578@23390L25:BottomSheetScaffold.kt#jmzs0o");
                    if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1944994153, $changed2, -1, "androidx.compose.material.BottomSheet.<anonymous> (BottomSheetScaffold.kt:578)");
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                    int $changed$iv = ($dirty >> 15) & 7168;
                    $composer3.startReplaceableGroup(-483455358);
                    ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                    int $changed$iv$iv = ($changed$iv << 3) & 112;
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
                        $composer3.createNode(constructor);
                    } else {
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
                    int i6 = ($changed$iv$iv$iv >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, 276693570, "C77@3893L9:Column.kt#2w3rfo");
                    function4.invoke(ColumnScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, (($dirty >> 6) & 112) | 1572864 | (($dirty >> 9) & 896) | (($dirty >> 9) & 7168) | (($dirty << 3) & 458752), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
        } else {
            if (i3 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1407045933, $dirty, -1, "androidx.compose.material.BottomSheet (BottomSheetScaffold.kt:525)");
            }
            $composer2.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer2, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2));
                $composer2.updateRememberedValue(value$iv$iv$iv);
            } else {
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer2.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv3 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope3 = wrapper$iv3.getCoroutineScope();
            $composer2.endReplaceableGroup();
            int i6 = ($dirty & 14) | 64;
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(state) | $composer2.changed(scope3);
            Object it$iv$iv3 = $composer2.rememberedValue();
            if (!invalid$iv$iv) {
            }
            value$iv$iv = BottomSheetScaffoldAnchorChangeCallback(state, scope3);
            $composer2.updateRememberedValue(value$iv$iv);
            $composer2.endReplaceableGroup();
            final AnchoredDraggableState.AnchorChangedCallback<BottomSheetValue> anchorChangeCallback3 = (AnchoredDraggableState.AnchorChangedCallback) value$iv$iv;
            SurfaceKt.m1210SurfaceFjzlyU(SemanticsModifierKt.semantics$default(OnRemeasuredModifierKt.onSizeChanged(AnchoredDraggableKt.anchoredDraggable(modifier2, state.getAnchoredDraggableState$material_release(), Orientation.Vertical, (24 & 4) != 0 ? true : sheetGesturesEnabled, (24 & 8) != 0 ? false : false, (24 & 16) != 0 ? null : null), new Function1<IntSize, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                    m1010invokeozmzZPI(intSize.getPackedValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                public final void m1010invokeozmzZPI(long layoutSize) {
                    state.getAnchoredDraggableState$material_release().updateAnchors$material_release(function1.invoke(IntSize.m5426boximpl(layoutSize)), anchorChangeCallback3);
                }
            }), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2
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
                    if (state.getAnchoredDraggableState$material_release().getAnchors$material_release().size() > 1) {
                        if (state.isCollapsed()) {
                            final BottomSheetState bottomSheetState = state;
                            final CoroutineScope coroutineScope = scope3;
                            SemanticsPropertiesKt.expand$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    if (bottomSheetState.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(BottomSheetValue.Expanded).booleanValue()) {
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00421(bottomSheetState, null), 3, null);
                                    }
                                    return true;
                                }

                                /* JADX INFO: renamed from: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1$1, reason: invalid class name and collision with other inner class name */
                                /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                @DebugMetadata(c = "androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$1$1", f = "BottomSheetScaffold.kt", i = {}, l = {561}, m = "invokeSuspend", n = {}, s = {})
                                static final class C00421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ BottomSheetState $state;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C00421(BottomSheetState bottomSheetState, Continuation<? super C00421> continuation) {
                                        super(2, continuation);
                                        this.$state = bottomSheetState;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new C00421(this.$state, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((C00421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object $result) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        switch (this.label) {
                                            case 0:
                                                ResultKt.throwOnFailure($result);
                                                this.label = 1;
                                                if (this.$state.expand(this) == coroutine_suspended) {
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
                            final BottomSheetState bottomSheetState2 = state;
                            final CoroutineScope coroutineScope2 = scope3;
                            SemanticsPropertiesKt.collapse$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    if (bottomSheetState2.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(BottomSheetValue.Collapsed).booleanValue()) {
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(bottomSheetState2, null), 3, null);
                                    }
                                    return true;
                                }

                                /* JADX INFO: renamed from: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$2$1, reason: invalid class name */
                                /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                @DebugMetadata(c = "androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$2$2$1", f = "BottomSheetScaffold.kt", i = {}, l = {568}, m = "invokeSuspend", n = {}, s = {})
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ BottomSheetState $state;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(BottomSheetState bottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.$state = bottomSheetState;
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
                                                if (this.$state.collapse(this) == coroutine_suspended) {
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
                }
            }, 1, null), sheetShape, sheetBackgroundColor, sheetContentColor, null, sheetElevation, ComposableLambdaKt.composableLambda($composer2, 1944994153, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$3
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
                    ComposerKt.sourceInformation($composer3, "C578@23390L25:BottomSheetScaffold.kt#jmzs0o");
                    if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1944994153, $changed2, -1, "androidx.compose.material.BottomSheet.<anonymous> (BottomSheetScaffold.kt:578)");
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                    int $changed$iv = ($dirty >> 15) & 7168;
                    $composer3.startReplaceableGroup(-483455358);
                    ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                    int $changed$iv$iv = ($changed$iv << 3) & 112;
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
                        $composer3.createNode(constructor);
                    } else {
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
                    int i7 = ($changed$iv$iv$iv >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, 276693570, "C77@3893L9:Column.kt#2w3rfo");
                    function4.invoke(ColumnScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, (($dirty >> 6) & 112) | 1572864 | (($dirty >> 9) & 896) | (($dirty >> 9) & 7168) | (($dirty << 3) & 458752), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheet$4
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
                BottomSheetScaffoldKt.m1005BottomSheet0cLKjW4(state, sheetGesturesEnabled, function1, sheetShape, sheetElevation, sheetBackgroundColor, sheetContentColor, modifier5, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: BottomSheetScaffoldLayout-KCBPh4w, reason: not valid java name */
    public static final void m1007BottomSheetScaffoldLayoutKCBPh4w(final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function3<? super Integer, ? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function6, final float sheetPeekHeight, final int floatingActionButtonPosition, final Function0<Float> function0, final BottomSheetState sheetState, Composer $composer, final int $changed) {
        int i;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1621720523);
        ComposerKt.sourceInformation($composer3, "C(BottomSheetScaffoldLayout)P(8!3,7,5:c#ui.unit.Dp,3:c#material.FabPosition)612@24340L2821,612@24323L2838:BottomSheetScaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer3.changedInstance(function5) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function6) ? 16384 : 8192;
        }
        if ((458752 & $changed) == 0) {
            $dirty |= $composer3.changed(sheetPeekHeight) ? 131072 : 65536;
        }
        if ((3670016 & $changed) == 0) {
            $dirty |= $composer3.changed(floatingActionButtonPosition) ? 1048576 : 524288;
        }
        if ((29360128 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 8388608 : 4194304;
        }
        if ((234881024 & $changed) == 0) {
            $dirty |= $composer3.changed(sheetState) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        final int $dirty2 = $dirty;
        if ((191739611 & $dirty2) != 38347922 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1621720523, $dirty2, -1, "androidx.compose.material.BottomSheetScaffoldLayout (BottomSheetScaffold.kt:601)");
            }
            Object[] keys$iv = {function4, function0, function2, function3, Dp.m5272boximpl(sheetPeekHeight), function5, FabPosition.m1111boximpl(floatingActionButtonPosition), function6, sheetState};
            $composer3.startReplaceableGroup(-568225417);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv = false;
            for (Object key$iv : keys$iv) {
                invalid$iv |= $composer3.changed(key$iv);
            }
            Object value$iv$iv = $composer3.rememberedValue();
            if (invalid$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                i = 0;
                value$iv$iv = new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1

                    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[BottomSheetValue.values().length];
                            try {
                                iArr[BottomSheetValue.Collapsed.ordinal()] = 1;
                            } catch (NoSuchFieldError e) {
                            }
                            try {
                                iArr[BottomSheetValue.Expanded.ordinal()] = 2;
                            } catch (NoSuchFieldError e2) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                        return m1012invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                    }

                    /* JADX WARN: Code duplicated, block: B:30:0x010c  */
                    /* JADX WARN: Code duplicated, block: B:60:0x0214  */
                    /* JADX WARN: Code duplicated, block: B:78:0x0268  */
                    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m1012invoke0kLqBqw(SubcomposeMeasureScope SubcomposeLayout, long constraints) {
                        List list;
                        int height;
                        List list2;
                        int width;
                        int height2;
                        Object maxElem$iv;
                        Object maxElem$iv2;
                        final int snackbarOffsetY;
                        Object maxElem$iv3;
                        Object maxElem$iv4;
                        Object maxElem$iv5;
                        Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                        int layoutWidth = Constraints.m5218getMaxWidthimpl(constraints);
                        final int layoutHeight = Constraints.m5217getMaxHeightimpl(constraints);
                        long looseConstraints = Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0);
                        BottomSheetScaffoldLayoutSlot bottomSheetScaffoldLayoutSlot = BottomSheetScaffoldLayoutSlot.Sheet;
                        final Function3<Integer, Composer, Integer, Unit> function7 = function4;
                        final int i2 = $dirty2;
                        Iterable $this$map$iv = SubcomposeLayout.subcompose(bottomSheetScaffoldLayoutSlot, ComposableLambdaKt.composableLambdaInstance(835355605, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1$sheetPlaceables$1
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

                            public final void invoke(Composer $composer4, int $changed2) {
                                ComposerKt.sourceInformation($composer4, "C618@24623L25:BottomSheetScaffold.kt#jmzs0o");
                                if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(835355605, $changed2, -1, "androidx.compose.material.BottomSheetScaffoldLayout.<anonymous>.<anonymous>.<anonymous> (BottomSheetScaffold.kt:617)");
                                }
                                function7.invoke(Integer.valueOf(layoutHeight), $composer4, Integer.valueOf((i2 >> 3) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                        for (Object item$iv$iv : $this$map$iv) {
                            Measurable it = (Measurable) item$iv$iv;
                            destination$iv$iv.add(it.mo4225measureBRTryo0(looseConstraints));
                        }
                        final List sheetPlaceables = (List) destination$iv$iv;
                        final int sheetOffsetY = MathKt.roundToInt(function0.invoke().floatValue());
                        Function2<Composer, Integer, Unit> function8 = function2;
                        if (function8 != null) {
                            Iterable $this$map$iv2 = SubcomposeLayout.subcompose(BottomSheetScaffoldLayoutSlot.TopBar, function8);
                            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                            for (Object item$iv$iv2 : $this$map$iv2) {
                                Measurable it2 = (Measurable) item$iv$iv2;
                                destination$iv$iv2.add(it2.mo4225measureBRTryo0(looseConstraints));
                            }
                            list = (List) destination$iv$iv2;
                        } else {
                            list = null;
                        }
                        final List topBarPlaceables = list;
                        if (topBarPlaceables == null) {
                            height = 0;
                        } else {
                            if (!topBarPlaceables.isEmpty()) {
                                maxElem$iv5 = topBarPlaceables.get(0);
                                Placeable it3 = (Placeable) maxElem$iv5;
                                int maxValue$iv = it3.getHeight();
                                int i$iv = 1;
                                int lastIndex = CollectionsKt.getLastIndex(topBarPlaceables);
                                if (1 <= lastIndex) {
                                    while (true) {
                                        Object e$iv = topBarPlaceables.get(i$iv);
                                        Placeable it4 = (Placeable) e$iv;
                                        int height3 = it4.getHeight();
                                        if (maxValue$iv < height3) {
                                            maxElem$iv5 = e$iv;
                                            maxValue$iv = height3;
                                        }
                                        if (i$iv == lastIndex) {
                                            break;
                                        }
                                        i$iv++;
                                    }
                                }
                            } else {
                                maxElem$iv5 = null;
                            }
                            Placeable placeable = (Placeable) maxElem$iv5;
                            if (placeable != null) {
                                height = placeable.getHeight();
                            } else {
                                height = 0;
                            }
                        }
                        final int topBarHeight = height;
                        long bodyConstraints = Constraints.m5208copyZbe2FdA(looseConstraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(looseConstraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(looseConstraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(looseConstraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(looseConstraints) : layoutHeight - topBarHeight);
                        BottomSheetScaffoldLayoutSlot bottomSheetScaffoldLayoutSlot2 = BottomSheetScaffoldLayoutSlot.Body;
                        final Function3<PaddingValues, Composer, Integer, Unit> function9 = function3;
                        final float f = sheetPeekHeight;
                        final int i3 = $dirty2;
                        Iterable $this$map$iv3 = SubcomposeLayout.subcompose(bottomSheetScaffoldLayoutSlot2, ComposableLambdaKt.composableLambdaInstance(-2019457358, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1$bodyPlaceables$1
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

                            public final void invoke(Composer $composer4, int $changed2) {
                                ComposerKt.sourceInformation($composer4, "C630@25195L45:BottomSheetScaffold.kt#jmzs0o");
                                if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2019457358, $changed2, -1, "androidx.compose.material.BottomSheetScaffoldLayout.<anonymous>.<anonymous>.<anonymous> (BottomSheetScaffold.kt:629)");
                                }
                                function9.invoke(PaddingKt.m484PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, f, 7, null), $composer4, Integer.valueOf(i3 & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
                        for (Object item$iv$iv3 : $this$map$iv3) {
                            Iterable $this$map$iv4 = $this$map$iv3;
                            Measurable it5 = (Measurable) item$iv$iv3;
                            destination$iv$iv3.add(it5.mo4225measureBRTryo0(bodyConstraints));
                            $this$map$iv3 = $this$map$iv4;
                        }
                        final List bodyPlaceables = (List) destination$iv$iv3;
                        Function2<Composer, Integer, Unit> function10 = function5;
                        if (function10 != null) {
                            Iterable $this$map$iv5 = SubcomposeLayout.subcompose(BottomSheetScaffoldLayoutSlot.Fab, function10);
                            Collection destination$iv$iv4 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv5, 10));
                            Iterable $this$mapTo$iv$iv = $this$map$iv5;
                            for (Object item$iv$iv4 : $this$mapTo$iv$iv) {
                                Iterable $this$mapTo$iv$iv2 = $this$mapTo$iv$iv;
                                Measurable it6 = (Measurable) item$iv$iv4;
                                destination$iv$iv4.add(it6.mo4225measureBRTryo0(looseConstraints));
                                $this$mapTo$iv$iv = $this$mapTo$iv$iv2;
                            }
                            list2 = (List) destination$iv$iv4;
                        } else {
                            list2 = null;
                        }
                        final List fabPlaceable = list2;
                        if (fabPlaceable != null) {
                            List $this$fastMaxBy$iv = fabPlaceable;
                            if ($this$fastMaxBy$iv.isEmpty()) {
                                maxElem$iv4 = null;
                            } else {
                                maxElem$iv4 = $this$fastMaxBy$iv.get(0);
                                Placeable it7 = (Placeable) maxElem$iv4;
                                int maxValue$iv2 = it7.getWidth();
                                int i$iv2 = 1;
                                int $i$f$fastMaxBy = CollectionsKt.getLastIndex($this$fastMaxBy$iv);
                                if (1 <= $i$f$fastMaxBy) {
                                    while (true) {
                                        Object e$iv2 = $this$fastMaxBy$iv.get(i$iv2);
                                        Placeable it8 = (Placeable) e$iv2;
                                        int width2 = it8.getWidth();
                                        List $this$fastMaxBy$iv2 = $this$fastMaxBy$iv;
                                        if (maxValue$iv2 < width2) {
                                            maxElem$iv4 = e$iv2;
                                            maxValue$iv2 = width2;
                                        }
                                        if (i$iv2 == $i$f$fastMaxBy) {
                                            break;
                                        }
                                        i$iv2++;
                                        $this$fastMaxBy$iv = $this$fastMaxBy$iv2;
                                    }
                                }
                            }
                            Placeable placeable2 = (Placeable) maxElem$iv4;
                            if (placeable2 != null) {
                                width = placeable2.getWidth();
                            } else {
                                width = 0;
                            }
                        } else {
                            width = 0;
                        }
                        int fabWidth = width;
                        if (fabPlaceable != null) {
                            List $this$fastMaxBy$iv3 = fabPlaceable;
                            if ($this$fastMaxBy$iv3.isEmpty()) {
                                maxElem$iv3 = null;
                            } else {
                                maxElem$iv3 = $this$fastMaxBy$iv3.get(0);
                                Placeable it9 = (Placeable) maxElem$iv3;
                                int maxValue$iv3 = it9.getHeight();
                                int i$iv3 = 1;
                                int $i$f$fastMaxBy2 = CollectionsKt.getLastIndex($this$fastMaxBy$iv3);
                                if (1 <= $i$f$fastMaxBy2) {
                                    while (true) {
                                        Object e$iv3 = $this$fastMaxBy$iv3.get(i$iv3);
                                        Placeable it10 = (Placeable) e$iv3;
                                        int height4 = it10.getHeight();
                                        List $this$fastMaxBy$iv4 = $this$fastMaxBy$iv3;
                                        if (maxValue$iv3 < height4) {
                                            maxElem$iv3 = e$iv3;
                                            maxValue$iv3 = height4;
                                        }
                                        if (i$iv3 == $i$f$fastMaxBy2) {
                                            break;
                                        }
                                        i$iv3++;
                                        $this$fastMaxBy$iv3 = $this$fastMaxBy$iv4;
                                    }
                                }
                            }
                            Placeable placeable3 = (Placeable) maxElem$iv3;
                            if (placeable3 != null) {
                                height2 = placeable3.getHeight();
                            } else {
                                height2 = 0;
                            }
                        } else {
                            height2 = 0;
                        }
                        int fabHeight = height2;
                        final int fabOffsetX = FabPosition.m1114equalsimpl0(floatingActionButtonPosition, FabPosition.INSTANCE.m1118getCenter5ygKITE()) ? (layoutWidth - fabWidth) / 2 : (layoutWidth - fabWidth) - SubcomposeLayout.mo321roundToPx0680j_4(BottomSheetScaffoldKt.FabSpacing);
                        final int fabOffsetY = SubcomposeLayout.mo327toPx0680j_4(sheetPeekHeight) < ((float) (fabHeight / 2)) ? (sheetOffsetY - fabHeight) - SubcomposeLayout.mo321roundToPx0680j_4(BottomSheetScaffoldKt.FabSpacing) : sheetOffsetY - (fabHeight / 2);
                        Iterable $this$map$iv6 = SubcomposeLayout.subcompose(BottomSheetScaffoldLayoutSlot.Snackbar, function6);
                        Collection destination$iv$iv5 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv6, 10));
                        for (Object item$iv$iv5 : $this$map$iv6) {
                            Iterable $this$map$iv7 = $this$map$iv6;
                            Measurable it11 = (Measurable) item$iv$iv5;
                            destination$iv$iv5.add(it11.mo4225measureBRTryo0(looseConstraints));
                            $this$map$iv6 = $this$map$iv7;
                        }
                        final List snackbarPlaceables = (List) destination$iv$iv5;
                        List $this$fastMaxBy$iv5 = snackbarPlaceables;
                        if ($this$fastMaxBy$iv5.isEmpty()) {
                            maxElem$iv = null;
                        } else {
                            maxElem$iv = $this$fastMaxBy$iv5.get(0);
                            Placeable it12 = (Placeable) maxElem$iv;
                            int maxValue$iv4 = it12.getWidth();
                            int i$iv4 = 1;
                            int $i$f$fastMaxBy3 = CollectionsKt.getLastIndex($this$fastMaxBy$iv5);
                            if (1 <= $i$f$fastMaxBy3) {
                                while (true) {
                                    Object e$iv4 = $this$fastMaxBy$iv5.get(i$iv4);
                                    Placeable it13 = (Placeable) e$iv4;
                                    int width3 = it13.getWidth();
                                    List $this$fastMaxBy$iv6 = $this$fastMaxBy$iv5;
                                    if (maxValue$iv4 < width3) {
                                        maxElem$iv = e$iv4;
                                        maxValue$iv4 = width3;
                                    }
                                    if (i$iv4 == $i$f$fastMaxBy3) {
                                        break;
                                    }
                                    i$iv4++;
                                    $this$fastMaxBy$iv5 = $this$fastMaxBy$iv6;
                                }
                            }
                        }
                        Placeable placeable4 = (Placeable) maxElem$iv;
                        int snackbarWidth = placeable4 != null ? placeable4.getWidth() : 0;
                        List $this$fastMaxBy$iv7 = snackbarPlaceables;
                        if ($this$fastMaxBy$iv7.isEmpty()) {
                            maxElem$iv2 = null;
                        } else {
                            Object maxElem$iv6 = $this$fastMaxBy$iv7.get(0);
                            Placeable it14 = (Placeable) maxElem$iv6;
                            int maxValue$iv5 = it14.getHeight();
                            int i$iv5 = 1;
                            int $i$f$fastMaxBy4 = CollectionsKt.getLastIndex($this$fastMaxBy$iv7);
                            if (1 <= $i$f$fastMaxBy4) {
                                while (true) {
                                    Object e$iv5 = $this$fastMaxBy$iv7.get(i$iv5);
                                    Placeable it15 = (Placeable) e$iv5;
                                    int height5 = it15.getHeight();
                                    List $this$fastMaxBy$iv8 = $this$fastMaxBy$iv7;
                                    if (maxValue$iv5 < height5) {
                                        maxElem$iv6 = e$iv5;
                                        maxValue$iv5 = height5;
                                    }
                                    if (i$iv5 == $i$f$fastMaxBy4) {
                                        break;
                                    }
                                    i$iv5++;
                                    $this$fastMaxBy$iv7 = $this$fastMaxBy$iv8;
                                }
                            }
                            maxElem$iv2 = maxElem$iv6;
                        }
                        Placeable placeable5 = (Placeable) maxElem$iv2;
                        int snackbarHeight = placeable5 != null ? placeable5.getHeight() : 0;
                        final int snackbarOffsetX = (layoutWidth - snackbarWidth) / 2;
                        switch (WhenMappings.$EnumSwitchMapping$0[sheetState.getCurrentValue().ordinal()]) {
                            case 1:
                                snackbarOffsetY = fabOffsetY - snackbarHeight;
                                break;
                            case 2:
                                snackbarOffsetY = layoutHeight - snackbarHeight;
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                        return MeasureScope.CC.layout$default(SubcomposeLayout, layoutWidth, layoutHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1.1
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
                                List<Placeable> list3 = bodyPlaceables;
                                int i4 = topBarHeight;
                                int size = list3.size();
                                for (int index$iv = 0; index$iv < size; index$iv++) {
                                    Object item$iv = list3.get(index$iv);
                                    Placeable it16 = (Placeable) item$iv;
                                    Placeable.PlacementScope.placeRelative$default(layout, it16, 0, i4, 0.0f, 4, null);
                                }
                                List<Placeable> list4 = topBarPlaceables;
                                if (list4 != null) {
                                    int size2 = list4.size();
                                    for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                                        Object item$iv2 = list4.get(index$iv2);
                                        Placeable it17 = (Placeable) item$iv2;
                                        Placeable.PlacementScope.placeRelative$default(layout, it17, 0, 0, 0.0f, 4, null);
                                    }
                                }
                                List<Placeable> list5 = sheetPlaceables;
                                int i5 = sheetOffsetY;
                                int size3 = list5.size();
                                for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
                                    Object item$iv3 = list5.get(index$iv3);
                                    Placeable it18 = (Placeable) item$iv3;
                                    Placeable.PlacementScope.placeRelative$default(layout, it18, 0, i5, 0.0f, 4, null);
                                }
                                List<Placeable> list6 = fabPlaceable;
                                if (list6 != null) {
                                    int i6 = fabOffsetX;
                                    int i7 = fabOffsetY;
                                    int size4 = list6.size();
                                    for (int index$iv4 = 0; index$iv4 < size4; index$iv4++) {
                                        Object item$iv4 = list6.get(index$iv4);
                                        Placeable it19 = (Placeable) item$iv4;
                                        Placeable.PlacementScope.placeRelative$default(layout, it19, i6, i7, 0.0f, 4, null);
                                    }
                                }
                                List<Placeable> list7 = snackbarPlaceables;
                                int i8 = snackbarOffsetX;
                                int i9 = snackbarOffsetY;
                                int size5 = list7.size();
                                for (int index$iv5 = 0; index$iv5 < size5; index$iv5++) {
                                    Object item$iv5 = list7.get(index$iv5);
                                    Placeable it20 = (Placeable) item$iv5;
                                    Placeable.PlacementScope.placeRelative$default(layout, it20, i8, i9, 0.0f, 4, null);
                                }
                            }
                        }, 4, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                i = 0;
            }
            $composer3.endReplaceableGroup();
            $composer2 = $composer3;
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) value$iv$iv, $composer2, i, 1);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2
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
                BottomSheetScaffoldKt.m1007BottomSheetScaffoldLayoutKCBPh4w(function2, function3, function4, function5, function6, sheetPeekHeight, floatingActionButtonPosition, function0, sheetState, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: renamed from: androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
    @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rJ!\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\tH\u0003ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\u0003H\u0003ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0017J\u001a\u0010\u0019\u001a\u00020\t*\u00020\u0015H\u0002ø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001a\u0082\u0002\u000f\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001b"}, d2 = {"androidx/compose/material/BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "toFloat", "", "offsetToFloat", "(J)F", "velocityToFloat", "toOffset", "(F)J", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class C02761 implements NestedScrollConnection {
        final /* synthetic */ Orientation $orientation;
        final /* synthetic */ AnchoredDraggableState<?> $state;

        C02761(AnchoredDraggableState<?> anchoredDraggableState, Orientation $orientation) {
            this.$state = anchoredDraggableState;
            this.$orientation = $orientation;
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
        public long mo337onPreScrollOzD1aCk(long available, int source) {
            float delta = offsetToFloat(available);
            if (delta < 0.0f && NestedScrollSource.m3997equalsimpl0(source, NestedScrollSource.INSTANCE.m4002getDragWNlRxjI())) {
                return toOffset(this.$state.dispatchRawDelta(delta));
            }
            return Offset.INSTANCE.m2747getZeroF1C5BW0();
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
        public long mo335onPostScrollDzOQY0M(long consumed, long available, int source) {
            if (NestedScrollSource.m3997equalsimpl0(source, NestedScrollSource.INSTANCE.m4002getDragWNlRxjI())) {
                return toOffset(this.$state.dispatchRawDelta(offsetToFloat(available)));
            }
            return Offset.INSTANCE.m2747getZeroF1C5BW0();
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPreFling-QWom1Mo */
        public Object mo336onPreFlingQWom1Mo(long available, Continuation<? super Velocity> continuation) {
            BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1;
            if (continuation instanceof BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) {
                bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = (BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) continuation;
                if ((bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label & Integer.MIN_VALUE) != 0) {
                    bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label -= Integer.MIN_VALUE;
                } else {
                    bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = new BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1(this, continuation);
                }
            } else {
                bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = new BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1(this, continuation);
            }
            BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2 = bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1;
            Object $result = bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    float toFling = velocityToFloat(available);
                    float currentOffset = this.$state.requireOffset();
                    if (toFling < 0.0f && currentOffset > this.$state.getMinOffset()) {
                        AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                        bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.J$0 = available;
                        bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.label = 1;
                        if (anchoredDraggableState.settle(toFling, bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        available = Velocity.INSTANCE.m5510getZero9UxMQ8M();
                    }
                    break;
                case 1:
                    available = bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.J$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Velocity.m5490boximpl(available);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
        public Object mo334onPostFlingRZ2iAVY(long j, long available, Continuation<? super Velocity> continuation) {
            BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1;
            if (continuation instanceof BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) {
                bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = (BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) continuation;
                if ((bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label & Integer.MIN_VALUE) != 0) {
                    bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label -= Integer.MIN_VALUE;
                } else {
                    bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = new BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1(this, continuation);
                }
            } else {
                bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = new BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1(this, continuation);
            }
            Object $result = bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                    float fVelocityToFloat = velocityToFloat(available);
                    bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.J$0 = available;
                    bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label = 1;
                    if (anchoredDraggableState.settle(fVelocityToFloat, bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    available = bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.J$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Velocity.m5490boximpl(available);
        }

        private final long toOffset(float $this$toOffset) {
            return OffsetKt.Offset(this.$orientation == Orientation.Horizontal ? $this$toOffset : 0.0f, this.$orientation == Orientation.Vertical ? $this$toOffset : 0.0f);
        }

        private final float velocityToFloat(long $this$toFloat) {
            return this.$orientation == Orientation.Horizontal ? Velocity.m5499getXimpl($this$toFloat) : Velocity.m5500getYimpl($this$toFloat);
        }

        private final float offsetToFloat(long $this$toFloat) {
            return this.$orientation == Orientation.Horizontal ? Offset.m2731getXimpl($this$toFloat) : Offset.m2732getYimpl($this$toFloat);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NestedScrollConnection ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(AnchoredDraggableState<?> anchoredDraggableState, Orientation orientation) {
        return new C02761(anchoredDraggableState, orientation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnchoredDraggableState.AnchorChangedCallback<BottomSheetValue> BottomSheetScaffoldAnchorChangeCallback(final BottomSheetState state, final CoroutineScope scope) {
        return new AnchoredDraggableState.AnchorChangedCallback<BottomSheetValue>() { // from class: androidx.compose.material.BottomSheetScaffoldKt.BottomSheetScaffoldAnchorChangeCallback.1

            /* JADX INFO: renamed from: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldAnchorChangeCallback$1$WhenMappings */
            /* JADX INFO: compiled from: BottomSheetScaffold.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[BottomSheetValue.values().length];
                    try {
                        iArr[BottomSheetValue.Collapsed.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[BottomSheetValue.Expanded.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // androidx.compose.material.AnchoredDraggableState.AnchorChangedCallback
            public final void onAnchorsChanged(BottomSheetValue prevTarget, Map<BottomSheetValue, Float> prevAnchors, Map<BottomSheetValue, Float> newAnchors) {
                BottomSheetValue newTarget;
                Intrinsics.checkNotNullParameter(prevTarget, "prevTarget");
                Intrinsics.checkNotNullParameter(prevAnchors, "prevAnchors");
                Intrinsics.checkNotNullParameter(newAnchors, "newAnchors");
                Float previousTargetOffset = prevAnchors.get(prevTarget);
                switch (WhenMappings.$EnumSwitchMapping$0[prevTarget.ordinal()]) {
                    case 1:
                        newTarget = BottomSheetValue.Collapsed;
                        break;
                    case 2:
                        newTarget = !newAnchors.containsKey(BottomSheetValue.Expanded) ? BottomSheetValue.Collapsed : BottomSheetValue.Expanded;
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                float newTargetOffset = ((Number) MapsKt.getValue(newAnchors, newTarget)).floatValue();
                if (!Intrinsics.areEqual(newTargetOffset, previousTargetOffset)) {
                    if (state.isAnimationRunning$material_release()) {
                        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new BottomSheetScaffoldKt$BottomSheetScaffoldAnchorChangeCallback$1$onAnchorsChanged$1(state, newTarget, null), 3, null);
                        return;
                    }
                    boolean didSnapSynchronously = state.trySnapTo$material_release(newTarget);
                    if (!didSnapSynchronously) {
                        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new BottomSheetScaffoldKt$BottomSheetScaffoldAnchorChangeCallback$1$onAnchorsChanged$2(state, newTarget, null), 3, null);
                    }
                }
            }
        };
    }
}
