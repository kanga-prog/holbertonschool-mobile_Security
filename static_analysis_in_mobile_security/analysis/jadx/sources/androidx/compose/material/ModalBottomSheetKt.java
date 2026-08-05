package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
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
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.Velocity;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ModalBottomSheet.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u001c\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0007\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a\u0096\u0001\u0010\u0011\u001a\u00020\u00122\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00120\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00012\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020!2\b\b\u0002\u0010#\u001a\u00020!2\u0011\u0010$\u001a\r\u0012\u0004\u0012\u00020\u00120%¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a@\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\r2\u000e\b\u0002\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\u0014\b\u0002\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001c0\u00142\b\b\u0002\u0010.\u001a\u00020\u001cH\u0007\u001aH\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\r2\u0006\u0010/\u001a\u0002002\u000e\b\u0002\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\u0014\b\u0002\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001c0\u00142\b\b\u0002\u0010.\u001a\u00020\u001cH\u0007\u001a3\u00101\u001a\u00020\u00122\u0006\u00102\u001a\u00020!2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00120%2\u0006\u00104\u001a\u00020\u001cH\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a9\u00107\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\r2\u000e\b\u0002\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001c0\u0014H\u0007¢\u0006\u0002\u00109\u001aE\u00107\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\r2\u000e\b\u0002\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\u0014\b\u0002\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001c0\u00142\b\b\u0002\u0010:\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010;\u001aA\u00107\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\r2\u000e\b\u0002\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010:\u001a\u00020\u001c2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001c0\u0014H\u0007¢\u0006\u0002\u0010<\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0004\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006=²\u0006\n\u0010>\u001a\u00020,X\u008a\u0084\u0002"}, d2 = {"MaxModalBottomSheetWidth", "Landroidx/compose/ui/unit/Dp;", "F", "ModalBottomSheetPositionalThreshold", "ModalBottomSheetVelocityThreshold", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "state", "Landroidx/compose/material/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "ModalBottomSheetAnchorChangeCallback", "Landroidx/compose/material/AnchoredDraggableState$AnchorChangedCallback;", "Landroidx/compose/material/ModalBottomSheetValue;", "Landroidx/compose/material/ModalBottomSheetState;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "ModalBottomSheetLayout", "", "sheetContent", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "sheetState", "sheetGesturesEnabled", "", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetElevation", "sheetBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "scrimColor", "content", "Lkotlin/Function0;", "ModalBottomSheetLayout-Gs3lGvM", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/ModalBottomSheetState;ZLandroidx/compose/ui/graphics/Shape;FJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ModalBottomSheetState", "initialValue", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmValueChange", "isSkipHalfExpanded", "density", "Landroidx/compose/ui/unit/Density;", "Scrim", "color", "onDismiss", "visible", "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "rememberModalBottomSheetState", "confirmStateChange", "(Landroidx/compose/material/ModalBottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/ModalBottomSheetState;", "skipHalfExpanded", "(Landroidx/compose/material/ModalBottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ModalBottomSheetState;", "(Landroidx/compose/material/ModalBottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/ModalBottomSheetState;", "material_release", "alpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModalBottomSheetKt {
    private static final float ModalBottomSheetPositionalThreshold = Dp.m5274constructorimpl(56);
    private static final float ModalBottomSheetVelocityThreshold = Dp.m5274constructorimpl(125);
    private static final float MaxModalBottomSheetWidth = Dp.m5274constructorimpl(640);

    public static /* synthetic */ ModalBottomSheetState ModalBottomSheetState$default(ModalBottomSheetValue modalBottomSheetValue, Density density, AnimationSpec animationSpec, Function1 function1, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            animationSpec = SwipeableDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i & 8) != 0) {
            function1 = new Function1<ModalBottomSheetValue, Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.ModalBottomSheetState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(ModalBottomSheetValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
        }
        if ((i & 16) != 0) {
            z = false;
        }
        return ModalBottomSheetState(modalBottomSheetValue, density, animationSpec, function1, z);
    }

    public static final ModalBottomSheetState ModalBottomSheetState(ModalBottomSheetValue initialValue, Density density, AnimationSpec<Float> animationSpec, Function1<? super ModalBottomSheetValue, Boolean> confirmValueChange, boolean isSkipHalfExpanded) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(confirmValueChange, "confirmValueChange");
        ModalBottomSheetState it = new ModalBottomSheetState(initialValue, animationSpec, isSkipHalfExpanded, confirmValueChange);
        it.setDensity$material_release(density);
        return it;
    }

    public static /* synthetic */ ModalBottomSheetState ModalBottomSheetState$default(ModalBottomSheetValue modalBottomSheetValue, AnimationSpec animationSpec, Function1 function1, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = SwipeableDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i & 4) != 0) {
            function1 = new Function1<ModalBottomSheetValue, Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.ModalBottomSheetState.3
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(ModalBottomSheetValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
        }
        if ((i & 8) != 0) {
            z = false;
        }
        return ModalBottomSheetState(modalBottomSheetValue, animationSpec, function1, z);
    }

    @Deprecated(message = "This constructor is deprecated. Density must be provided by the component. Please use the constructor that provides a [Density].", replaceWith = @ReplaceWith(expression = "\n            ModalBottomSheetState(\n                initialValue = initialValue,\n                density =,\n                animationSpec = animationSpec,\n                isSkipHalfExpanded = isSkipHalfExpanded,\n                confirmStateChange = confirmValueChange\n            )\n            ", imports = {}))
    public static final ModalBottomSheetState ModalBottomSheetState(ModalBottomSheetValue initialValue, AnimationSpec<Float> animationSpec, Function1<? super ModalBottomSheetValue, Boolean> confirmValueChange, boolean isSkipHalfExpanded) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(confirmValueChange, "confirmValueChange");
        return new ModalBottomSheetState(initialValue, animationSpec, isSkipHalfExpanded, confirmValueChange);
    }

    public static final ModalBottomSheetState rememberModalBottomSheetState(final ModalBottomSheetValue initialValue, AnimationSpec<Float> animationSpec, Function1<? super ModalBottomSheetValue, Boolean> function1, boolean skipHalfExpanded, Composer $composer, int $changed, int i) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        $composer.startReplaceableGroup(-126412120);
        ComposerKt.sourceInformation($composer, "C(rememberModalBottomSheetState)P(2)442@17797L7:ModalBottomSheet.kt#jmzs0o");
        AnimationSpec<Float> animationSpec2 = (i & 2) != 0 ? SwipeableDefaults.INSTANCE.getAnimationSpec() : animationSpec;
        Function1<? super ModalBottomSheetValue, Boolean> function2 = (i & 4) != 0 ? new Function1<ModalBottomSheetValue, Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.rememberModalBottomSheetState.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(ModalBottomSheetValue it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return true;
            }
        } : function1;
        boolean skipHalfExpanded2 = (i & 8) != 0 ? false : skipHalfExpanded;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-126412120, $changed, -1, "androidx.compose.material.rememberModalBottomSheetState (ModalBottomSheet.kt:436)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) objConsume;
        $composer.startMovableGroup(170051607, initialValue);
        ComposerKt.sourceInformation($composer, "447@18058L646");
        final AnimationSpec<Float> animationSpec3 = animationSpec2;
        final Function1<? super ModalBottomSheetValue, Boolean> function3 = function2;
        final boolean z = skipHalfExpanded2;
        ModalBottomSheetState modalBottomSheetState = (ModalBottomSheetState) RememberSaveableKt.m2617rememberSaveable(new Object[]{initialValue, animationSpec2, Boolean.valueOf(skipHalfExpanded2), function2, density}, (Saver) ModalBottomSheetState.INSTANCE.Saver(animationSpec2, function2, skipHalfExpanded2, density), (String) null, (Function0) new Function0<ModalBottomSheetState>() { // from class: androidx.compose.material.ModalBottomSheetKt.rememberModalBottomSheetState.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ModalBottomSheetState invoke() {
                return ModalBottomSheetKt.ModalBottomSheetState(initialValue, density, animationSpec3, function3, z);
            }
        }, $composer, 72, 4);
        $composer.endMovableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return modalBottomSheetState;
    }

    @Deprecated(message = "This function is deprecated. confirmStateChange has been renamed to confirmValueChange.", replaceWith = @ReplaceWith(expression = "rememberModalBottomSheetState(initialValue, animationSpec, confirmStateChange, false)", imports = {}))
    public static final ModalBottomSheetState rememberModalBottomSheetState(ModalBottomSheetValue initialValue, AnimationSpec<Float> animationSpec, boolean skipHalfExpanded, Function1<? super ModalBottomSheetValue, Boolean> confirmStateChange, Composer $composer, int $changed, int i) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(confirmStateChange, "confirmStateChange");
        $composer.startReplaceableGroup(-409288536);
        ComposerKt.sourceInformation($composer, "C(rememberModalBottomSheetState)P(2!1,3)495@20118L185:ModalBottomSheet.kt#jmzs0o");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec2 = SwipeableDefaults.INSTANCE.getAnimationSpec();
            animationSpec = animationSpec2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-409288536, $changed, -1, "androidx.compose.material.rememberModalBottomSheetState (ModalBottomSheet.kt:490)");
        }
        ModalBottomSheetState modalBottomSheetStateRememberModalBottomSheetState = rememberModalBottomSheetState(initialValue, animationSpec, confirmStateChange, skipHalfExpanded, $composer, ($changed & 14) | 64 | (($changed >> 3) & 896) | (($changed << 3) & 7168), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return modalBottomSheetStateRememberModalBottomSheetState;
    }

    @Deprecated(message = "This function is deprecated. confirmStateChange has been renamed to confirmValueChange.", replaceWith = @ReplaceWith(expression = "rememberModalBottomSheetState(initialValue, animationSpec, confirmValueChange = confirmStateChange)", imports = {}))
    public static final ModalBottomSheetState rememberModalBottomSheetState(ModalBottomSheetValue initialValue, AnimationSpec<Float> animationSpec, Function1<? super ModalBottomSheetValue, Boolean> confirmStateChange, Composer $composer, int $changed, int i) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(confirmStateChange, "confirmStateChange");
        $composer.startReplaceableGroup(-1928569212);
        ComposerKt.sourceInformation($composer, "C(rememberModalBottomSheetState)P(2)523@21187L174:ModalBottomSheet.kt#jmzs0o");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec2 = SwipeableDefaults.INSTANCE.getAnimationSpec();
            animationSpec = animationSpec2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1928569212, $changed, -1, "androidx.compose.material.rememberModalBottomSheetState (ModalBottomSheet.kt:519)");
        }
        ModalBottomSheetState modalBottomSheetStateRememberModalBottomSheetState = rememberModalBottomSheetState(initialValue, animationSpec, confirmStateChange, false, $composer, ($changed & 14) | 3136 | ($changed & 896), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return modalBottomSheetStateRememberModalBottomSheetState;
    }

    /* JADX WARN: Code duplicated, block: B:110:0x0156  */
    /* JADX WARN: Code duplicated, block: B:112:0x0169  */
    /* JADX WARN: Code duplicated, block: B:131:0x01b5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:132:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:133:0x01be  */
    /* JADX WARN: Code duplicated, block: B:135:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:138:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:139:0x01df  */
    /* JADX WARN: Code duplicated, block: B:142:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:143:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:145:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:146:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:149:0x0204  */
    /* JADX WARN: Code duplicated, block: B:150:0x0211  */
    /* JADX WARN: Code duplicated, block: B:153:0x0217  */
    /* JADX WARN: Code duplicated, block: B:154:0x0222  */
    /* JADX WARN: Code duplicated, block: B:157:0x0228  */
    /* JADX WARN: Code duplicated, block: B:158:0x023f  */
    /* JADX WARN: Code duplicated, block: B:161:0x0256  */
    /* JADX WARN: Code duplicated, block: B:164:0x0271  */
    /* JADX WARN: Code duplicated, block: B:167:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:168:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:171:0x0319  */
    /* JADX WARN: Code duplicated, block: B:175:0x0324  */
    /* JADX WARN: Code duplicated, block: B:178:0x0390  */
    /* JADX WARN: Code duplicated, block: B:181:0x0399  */
    /* JADX WARN: Code duplicated, block: B:182:0x039c  */
    /* JADX INFO: renamed from: ModalBottomSheetLayout-Gs3lGvM, reason: not valid java name */
    public static final void m1140ModalBottomSheetLayoutGs3lGvM(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> sheetContent, Modifier modifier, ModalBottomSheetState sheetState, boolean sheetGesturesEnabled, Shape sheetShape, float sheetElevation, long sheetBackgroundColor, long sheetContentColor, long scrimColor, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        Shape shape;
        int i2;
        long j;
        int i3;
        int $dirty;
        Modifier modifier3;
        ModalBottomSheetState sheetState2;
        boolean sheetGesturesEnabled2;
        CornerBasedShape sheetShape2;
        float sheetElevation2;
        long sheetBackgroundColor2;
        long sheetContentColor2;
        long scrimColor2;
        boolean sheetGesturesEnabled3;
        final ModalBottomSheetState sheetState3;
        Shape sheetShape3;
        float sheetElevation3;
        long sheetBackgroundColor3;
        long sheetContentColor3;
        int $dirty2;
        Object it$iv$iv$iv;
        Object value$iv$iv$iv;
        final CoroutineScope scope;
        boolean invalid$iv$iv;
        Object value$iv$iv;
        ModalBottomSheetState sheetState4;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(sheetContent, "sheetContent");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-92970288);
        ComposerKt.sourceInformation($composer3, "C(ModalBottomSheetLayout)P(4,1,9,7,8,6:c#ui.unit.Dp,3:c#ui.graphics.Color,5:c#ui.graphics.Color,2:c#ui.graphics.Color)565@23318L37,567@23437L6,569@23560L6,570@23607L37,571@23695L10,581@24005L24,583@24104L99,586@24208L4785:ModalBottomSheet.kt#jmzs0o");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty3 |= $composer3.changedInstance(sheetContent) ? 4 : 2;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty3 |= 128;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty3 |= 3072;
            z = sheetGesturesEnabled;
        } else if (($changed & 7168) == 0) {
            z = sheetGesturesEnabled;
            $dirty3 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = sheetGesturesEnabled;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                shape = sheetShape;
                int i7 = $composer3.changed(shape) ? 16384 : 8192;
                $dirty3 |= i7;
            } else {
                shape = sheetShape;
            }
            $dirty3 |= i7;
        } else {
            shape = sheetShape;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((458752 & $changed) == 0) {
            $dirty3 |= $composer3.changed(sheetElevation) ? 131072 : 65536;
        }
        if ((3670016 & $changed) == 0) {
            if ((i & 64) == 0) {
                i2 = i6;
                int i9 = $composer3.changed(sheetBackgroundColor) ? 1048576 : 524288;
                $dirty3 |= i9;
            } else {
                i2 = i6;
            }
            $dirty3 |= i9;
        } else {
            i2 = i6;
        }
        if ((29360128 & $changed) == 0) {
            $dirty3 |= ((i & 128) == 0 && $composer3.changed(sheetContentColor)) ? 8388608 : 4194304;
        }
        if ((234881024 & $changed) == 0) {
            if ((i & 256) == 0) {
                j = scrimColor;
                int i10 = $composer3.changed(j) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty3 |= i10;
            } else {
                j = scrimColor;
            }
            $dirty3 |= i10;
        } else {
            j = scrimColor;
        }
        if ((i & 512) == 0) {
            if ((1879048192 & $changed) == 0) {
                i3 = $composer3.changedInstance(content) ? 536870912 : 268435456;
            }
            $dirty = $dirty3;
            if (i5 != 4 && (1533916891 & $dirty) == 306783378 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                sheetState4 = sheetState;
                sheetElevation3 = sheetElevation;
                sheetBackgroundColor3 = sheetBackgroundColor;
                sheetContentColor3 = sheetContentColor;
                modifier3 = modifier2;
                scrimColor2 = j;
                sheetGesturesEnabled3 = z;
                sheetShape3 = shape;
                $composer2 = $composer3;
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i4 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i5 != 0) {
                        sheetState2 = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, (AnimationSpec<Float>) null, (Function1<? super ModalBottomSheetValue, Boolean>) null, false, $composer3, 6, 14);
                        $dirty &= -897;
                    } else {
                        sheetState2 = sheetState;
                    }
                    if (i2 != 0) {
                        sheetGesturesEnabled2 = true;
                    } else {
                        sheetGesturesEnabled2 = z;
                    }
                    if ((i & 16) != 0) {
                        sheetShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                        $dirty &= -57345;
                    } else {
                        sheetShape2 = shape;
                    }
                    if (i8 != 0) {
                        sheetElevation2 = ModalBottomSheetDefaults.INSTANCE.m1139getElevationD9Ej5fM();
                    } else {
                        sheetElevation2 = sheetElevation;
                    }
                    if ((i & 64) != 0) {
                        sheetBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                        $dirty &= -3670017;
                    } else {
                        sheetBackgroundColor2 = sheetBackgroundColor;
                    }
                    if ((i & 128) != 0) {
                        sheetContentColor2 = ColorsKt.m1066contentColorForek8zF_U(sheetBackgroundColor2, $composer3, ($dirty >> 18) & 14);
                        $dirty &= -29360129;
                    } else {
                        sheetContentColor2 = sheetContentColor;
                    }
                    if ((i & 256) != 0) {
                        sheetGesturesEnabled3 = sheetGesturesEnabled2;
                        sheetState3 = sheetState2;
                        sheetBackgroundColor3 = sheetBackgroundColor2;
                        sheetContentColor3 = sheetContentColor2;
                        scrimColor2 = ModalBottomSheetDefaults.INSTANCE.getScrimColor($composer3, 6);
                        sheetShape3 = sheetShape2;
                        $dirty2 = $dirty & (-234881025);
                        sheetElevation3 = sheetElevation2;
                    } else {
                        scrimColor2 = scrimColor;
                        sheetGesturesEnabled3 = sheetGesturesEnabled2;
                        sheetState3 = sheetState2;
                        sheetShape3 = sheetShape2;
                        sheetElevation3 = sheetElevation2;
                        sheetBackgroundColor3 = sheetBackgroundColor2;
                        sheetContentColor3 = sheetContentColor2;
                        $dirty2 = $dirty;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if (i5 != 0) {
                        $dirty &= -897;
                    }
                    if ((i & 16) != 0) {
                        $dirty &= -57345;
                    }
                    if ((i & 64) != 0) {
                        $dirty &= -3670017;
                    }
                    if ((i & 128) != 0) {
                        $dirty &= -29360129;
                    }
                    if ((i & 256) != 0) {
                        sheetElevation3 = sheetElevation;
                        sheetBackgroundColor3 = sheetBackgroundColor;
                        sheetContentColor3 = sheetContentColor;
                        modifier3 = modifier2;
                        scrimColor2 = j;
                        sheetGesturesEnabled3 = z;
                        sheetShape3 = shape;
                        sheetState3 = sheetState;
                        $dirty2 = $dirty & (-234881025);
                    } else {
                        sheetElevation3 = sheetElevation;
                        sheetBackgroundColor3 = sheetBackgroundColor;
                        sheetContentColor3 = sheetContentColor;
                        modifier3 = modifier2;
                        scrimColor2 = j;
                        sheetGesturesEnabled3 = z;
                        sheetShape3 = shape;
                        sheetState3 = sheetState;
                        $dirty2 = $dirty;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-92970288, $dirty2, -1, "androidx.compose.material.ModalBottomSheetLayout (ModalBottomSheet.kt:561)");
                }
                $composer3.startReplaceableGroup(502769027);
                ComposerKt.sourceInformation($composer3, "576@23903L7,577@23919L63");
                if (sheetState3.getDensity() == null) {
                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localDensity);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    final Density density = (Density) objConsume;
                    EffectsKt.SideEffect(new Function0<Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$1
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
                            sheetState3.setDensity$material_release(density);
                        }
                    }, $composer3, 0);
                }
                $composer3.endReplaceableGroup();
                $composer3.startReplaceableGroup(773894976);
                ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
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
                scope = wrapper$iv.getCoroutineScope();
                $composer3.endReplaceableGroup();
                final Orientation orientation = Orientation.Vertical;
                $composer3.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer3.changed(sheetState3) | $composer3.changed(scope);
                Object it$iv$iv = $composer3.rememberedValue();
                if (!invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = ModalBottomSheetAnchorChangeCallback(sheetState3, scope);
                    $composer3.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                $composer3.endReplaceableGroup();
                final AnchoredDraggableState.AnchorChangedCallback anchorChangeCallback = (AnchoredDraggableState.AnchorChangedCallback) value$iv$iv;
                final boolean z2 = sheetGesturesEnabled3;
                final ModalBottomSheetState modalBottomSheetState = sheetState3;
                final Shape shape2 = sheetShape3;
                final long j2 = sheetBackgroundColor3;
                final int $dirty4 = $dirty2;
                final long j3 = sheetContentColor3;
                sheetState4 = sheetState3;
                final float f = sheetElevation3;
                final long j4 = scrimColor2;
                $composer2 = $composer3;
                BoxWithConstraintsKt.BoxWithConstraints(modifier3, null, false, ComposableLambdaKt.composableLambda($composer2, -1731958854, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
                        invoke(boxWithConstraintsScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer $composer4, int $changed2) {
                        Function0<ComposeUiNode> function0;
                        Modifier.Companion companionNestedScroll$default;
                        Modifier.Companion companionSemantics$default;
                        Object value$iv$iv2;
                        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
                        ComposerKt.sourceInformation($composer4, "C588@24304L430,600@24743L4244:ModalBottomSheet.kt#jmzs0o");
                        int $dirty5 = $changed2;
                        if (($changed2 & 14) == 0) {
                            $dirty5 |= $composer4.changed(BoxWithConstraints) ? 4 : 2;
                        }
                        if (($dirty5 & 91) != 18 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1731958854, $changed2, -1, "androidx.compose.material.ModalBottomSheetLayout.<anonymous> (ModalBottomSheet.kt:586)");
                            }
                            final float fullHeight = Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk());
                            Modifier modifier$iv = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Function2<Composer, Integer, Unit> function2 = content;
                            int i11 = $dirty4;
                            long j5 = j4;
                            final ModalBottomSheetState modalBottomSheetState2 = modalBottomSheetState;
                            final CoroutineScope coroutineScope = scope;
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                            int $changed$iv$iv = (6 << 3) & 112;
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
                            int i12 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i13 = ((6 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1295116475, "C589@24346L9,590@24368L356:ModalBottomSheet.kt#jmzs0o");
                            function2.invoke($composer4, Integer.valueOf((i11 >> 27) & 14));
                            ModalBottomSheetKt.m1141Scrim3JVO9M(j5, new Function0<Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$1$1
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
                                    if (modalBottomSheetState2.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(ModalBottomSheetValue.Hidden).booleanValue()) {
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(modalBottomSheetState2, null), 3, null);
                                    }
                                }

                                /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$1$1$1, reason: invalid class name */
                                /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                @DebugMetadata(c = "androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {595}, m = "invokeSuspend", n = {}, s = {})
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ ModalBottomSheetState $sheetState;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(ModalBottomSheetState modalBottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.$sheetState = modalBottomSheetState;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass1(this.$sheetState, continuation);
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
                                                if (this.$sheetState.hide(this) == coroutine_suspended) {
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
                            }, modalBottomSheetState2.getAnchoredDraggableState$material_release().getTargetValue() != ModalBottomSheetValue.Hidden, $composer4, (i11 >> 24) & 14);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SizeKt.m541widthInVpY3zN4$default(BoxWithConstraints.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0.0f, ModalBottomSheetKt.MaxModalBottomSheetWidth, 1, null), 0.0f, 1, null);
                            $composer4.startReplaceableGroup(1241536180);
                            ComposerKt.sourceInformation($composer4, "608@25105L354");
                            if (z2) {
                                Modifier.Companion companion = Modifier.INSTANCE;
                                Object key1$iv = modalBottomSheetState.getAnchoredDraggableState$material_release();
                                Object key2$iv = orientation;
                                ModalBottomSheetState modalBottomSheetState3 = modalBottomSheetState;
                                Orientation orientation2 = orientation;
                                $composer4.startReplaceableGroup(511388516);
                                ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2):Composables.kt#9igjgp");
                                boolean invalid$iv$iv2 = $composer4.changed(key1$iv) | $composer4.changed(key2$iv);
                                Object it$iv$iv2 = $composer4.rememberedValue();
                                if (!invalid$iv$iv2) {
                                    Object key1$iv2 = Composer.INSTANCE.getEmpty();
                                    if (it$iv$iv2 != key1$iv2) {
                                        value$iv$iv2 = it$iv$iv2;
                                    }
                                    $composer4.endReplaceableGroup();
                                    companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(companion, (NestedScrollConnection) value$iv$iv2, null, 2, null);
                                }
                                value$iv$iv2 = ModalBottomSheetKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(modalBottomSheetState3.getAnchoredDraggableState$material_release(), orientation2);
                                $composer4.updateRememberedValue(value$iv$iv2);
                                $composer4.endReplaceableGroup();
                                companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(companion, (NestedScrollConnection) value$iv$iv2, null, 2, null);
                            } else {
                                companionNestedScroll$default = Modifier.INSTANCE;
                            }
                            $composer4.endReplaceableGroup();
                            Modifier modifierThen = modifierFillMaxWidth$default.then(companionNestedScroll$default);
                            final ModalBottomSheetState modalBottomSheetState4 = modalBottomSheetState;
                            Modifier modifierAnchoredDraggable = AnchoredDraggableKt.anchoredDraggable(OffsetKt.offset(modifierThen, new Function1<Density, IntOffset>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2.3
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ IntOffset invoke(Density density2) {
                                    return IntOffset.m5383boximpl(m1143invokeBjo55l4(density2));
                                }

                                /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                                public final long m1143invokeBjo55l4(Density offset) {
                                    Intrinsics.checkNotNullParameter(offset, "$this$offset");
                                    return IntOffsetKt.IntOffset(0, MathKt.roundToInt(modalBottomSheetState4.getAnchoredDraggableState$material_release().requireOffset()));
                                }
                            }), modalBottomSheetState.getAnchoredDraggableState$material_release(), orientation, (24 & 4) != 0 ? true : z2 && modalBottomSheetState.getAnchoredDraggableState$material_release().getCurrentValue() != ModalBottomSheetValue.Hidden, (24 & 8) != 0 ? false : false, (24 & 16) != 0 ? null : null);
                            final ModalBottomSheetState modalBottomSheetState5 = modalBottomSheetState;
                            final AnchoredDraggableState.AnchorChangedCallback<ModalBottomSheetValue> anchorChangedCallback = anchorChangeCallback;
                            Modifier modifierOnSizeChanged = OnRemeasuredModifierKt.onSizeChanged(modifierAnchoredDraggable, new Function1<IntSize, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2.4
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                                    m1144invokeozmzZPI(intSize.getPackedValue());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                                public final void m1144invokeozmzZPI(long sheetSize) {
                                    float f2 = fullHeight;
                                    ModalBottomSheetState modalBottomSheetState6 = modalBottomSheetState5;
                                    Map $this$invoke_ozmzZPI_u24lambda_u240 = MapsKt.createMapBuilder();
                                    $this$invoke_ozmzZPI_u24lambda_u240.put(ModalBottomSheetValue.Hidden, Float.valueOf(f2));
                                    float halfHeight = f2 / 2.0f;
                                    if (!modalBottomSheetState6.getIsSkipHalfExpanded() && IntSize.m5433getHeightimpl(sheetSize) > halfHeight) {
                                        $this$invoke_ozmzZPI_u24lambda_u240.put(ModalBottomSheetValue.HalfExpanded, Float.valueOf(halfHeight));
                                    }
                                    if (IntSize.m5433getHeightimpl(sheetSize) != 0) {
                                        $this$invoke_ozmzZPI_u24lambda_u240.put(ModalBottomSheetValue.Expanded, Float.valueOf(Math.max(0.0f, f2 - IntSize.m5433getHeightimpl(sheetSize))));
                                    }
                                    modalBottomSheetState5.getAnchoredDraggableState$material_release().updateAnchors$material_release(MapsKt.build($this$invoke_ozmzZPI_u24lambda_u240), anchorChangedCallback);
                                }
                            });
                            if (z2) {
                                Modifier.Companion companion2 = Modifier.INSTANCE;
                                final ModalBottomSheetState modalBottomSheetState6 = modalBottomSheetState;
                                final CoroutineScope coroutineScope2 = scope;
                                companionSemantics$default = SemanticsModifierKt.semantics$default(companion2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2.5
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
                                        if (modalBottomSheetState6.isVisible()) {
                                            final ModalBottomSheetState modalBottomSheetState7 = modalBottomSheetState6;
                                            final CoroutineScope coroutineScope3 = coroutineScope2;
                                            SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.ModalBottomSheetLayout.2.5.1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    if (modalBottomSheetState7.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(ModalBottomSheetValue.Hidden).booleanValue()) {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new C00531(modalBottomSheetState7, null), 3, null);
                                                    }
                                                    return true;
                                                }

                                                /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$1$1, reason: invalid class name and collision with other inner class name */
                                                /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                                @DebugMetadata(c = "androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$1$1", f = "ModalBottomSheet.kt", i = {}, l = {653}, m = "invokeSuspend", n = {}, s = {})
                                                static final class C00531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ ModalBottomSheetState $sheetState;
                                                    int label;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    C00531(ModalBottomSheetState modalBottomSheetState, Continuation<? super C00531> continuation) {
                                                        super(2, continuation);
                                                        this.$sheetState = modalBottomSheetState;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new C00531(this.$sheetState, continuation);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                        return ((C00531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Object invokeSuspend(Object $result) {
                                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                        switch (this.label) {
                                                            case 0:
                                                                ResultKt.throwOnFailure($result);
                                                                this.label = 1;
                                                                if (this.$sheetState.hide(this) == coroutine_suspended) {
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
                                            if (modalBottomSheetState6.getAnchoredDraggableState$material_release().getCurrentValue() == ModalBottomSheetValue.HalfExpanded) {
                                                final ModalBottomSheetState modalBottomSheetState8 = modalBottomSheetState6;
                                                final CoroutineScope coroutineScope4 = coroutineScope2;
                                                SemanticsPropertiesKt.expand$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.ModalBottomSheetLayout.2.5.2
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        if (modalBottomSheetState8.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(ModalBottomSheetValue.Expanded).booleanValue()) {
                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope4, null, null, new AnonymousClass1(modalBottomSheetState8, null), 3, null);
                                                        }
                                                        return true;
                                                    }

                                                    /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$2$1, reason: invalid class name */
                                                    /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                                    @DebugMetadata(c = "androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$2$1", f = "ModalBottomSheet.kt", i = {}, l = {665}, m = "invokeSuspend", n = {}, s = {})
                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ ModalBottomSheetState $sheetState;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        AnonymousClass1(ModalBottomSheetState modalBottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                                            super(2, continuation);
                                                            this.$sheetState = modalBottomSheetState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new AnonymousClass1(this.$sheetState, continuation);
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
                                                                    if (this.$sheetState.expand$material_release(this) == coroutine_suspended) {
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
                                            } else if (modalBottomSheetState6.getHasHalfExpandedState$material_release()) {
                                                final ModalBottomSheetState modalBottomSheetState9 = modalBottomSheetState6;
                                                final CoroutineScope coroutineScope5 = coroutineScope2;
                                                SemanticsPropertiesKt.collapse$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.ModalBottomSheetLayout.2.5.3
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        if (modalBottomSheetState9.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(ModalBottomSheetValue.HalfExpanded).booleanValue()) {
                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope5, null, null, new AnonymousClass1(modalBottomSheetState9, null), 3, null);
                                                        }
                                                        return true;
                                                    }

                                                    /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$3$1, reason: invalid class name */
                                                    /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                                    @DebugMetadata(c = "androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$3$1", f = "ModalBottomSheet.kt", i = {}, l = {675}, m = "invokeSuspend", n = {}, s = {})
                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ ModalBottomSheetState $sheetState;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        AnonymousClass1(ModalBottomSheetState modalBottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                                            super(2, continuation);
                                                            this.$sheetState = modalBottomSheetState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new AnonymousClass1(this.$sheetState, continuation);
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
                                                                    if (this.$sheetState.halfExpand$material_release(this) == coroutine_suspended) {
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
                                }, 1, null);
                            } else {
                                companionSemantics$default = Modifier.INSTANCE;
                            }
                            Modifier modifierThen2 = modifierOnSizeChanged.then(companionSemantics$default);
                            Shape shape3 = shape2;
                            long j6 = j2;
                            long j7 = j3;
                            float f2 = f;
                            final Function3<ColumnScope, Composer, Integer, Unit> function3 = sheetContent;
                            final int i14 = $dirty4;
                            ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, 1552994302, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2.6
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

                                public final void invoke(Composer $composer5, int $changed3) {
                                    ComposerKt.sourceInformation($composer5, "C688@28947L30:ModalBottomSheet.kt#jmzs0o");
                                    if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                        $composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1552994302, $changed3, -1, "androidx.compose.material.ModalBottomSheetLayout.<anonymous>.<anonymous> (ModalBottomSheet.kt:687)");
                                    }
                                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                    int $changed$iv = (i14 << 9) & 7168;
                                    $composer5.startReplaceableGroup(-483455358);
                                    ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                                    Modifier modifier$iv2 = Modifier.INSTANCE;
                                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                    MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                    int $changed$iv$iv2 = ($changed$iv << 3) & 112;
                                    $composer5.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer5, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                    int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                    CompositionLocalMap localMap$iv$iv2 = $composer5.getCurrentCompositionLocalMap();
                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                    if (!($composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    $composer5.startReusableNode();
                                    if ($composer5.getInserting()) {
                                        $composer5.createNode(constructor2);
                                    } else {
                                        $composer5.useNode();
                                    }
                                    Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2603constructorimpl($composer5);
                                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                                    }
                                    function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                    $composer5.startReplaceableGroup(2058660585);
                                    int i15 = ($changed$iv$iv$iv2 >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer5, 276693570, "C77@3893L9:Column.kt#2w3rfo");
                                    function4.invoke(ColumnScopeInstance.INSTANCE, $composer5, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    $composer5.endReplaceableGroup();
                                    $composer5.endNode();
                                    $composer5.endReplaceableGroup();
                                    $composer5.endReplaceableGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            });
                            int i15 = $dirty4;
                            SurfaceKt.m1210SurfaceFjzlyU(modifierThen2, shape3, j6, j7, null, f2, composableLambda, $composer4, ((i15 >> 9) & 112) | 1572864 | ((i15 >> 12) & 896) | ((i15 >> 12) & 7168) | (i15 & 458752), 16);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }), $composer2, (($dirty4 >> 3) & 14) | 3072, 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final ModalBottomSheetState modalBottomSheetState2 = sheetState4;
            final boolean z3 = sheetGesturesEnabled3;
            final Shape shape3 = sheetShape3;
            final float f2 = sheetElevation3;
            final long j5 = sheetBackgroundColor3;
            final long j6 = sheetContentColor3;
            final long j7 = scrimColor2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$3
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
                    ModalBottomSheetKt.m1140ModalBottomSheetLayoutGs3lGvM(sheetContent, modifier4, modalBottomSheetState2, z3, shape3, f2, j5, j6, j7, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i3 = 805306368;
        $dirty3 |= i3;
        $dirty = $dirty3;
        if (i5 != 4) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    sheetState2 = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, (AnimationSpec<Float>) null, (Function1<? super ModalBottomSheetValue, Boolean>) null, false, $composer3, 6, 14);
                    $dirty &= -897;
                } else {
                    sheetState2 = sheetState;
                }
                if (i2 != 0) {
                    sheetGesturesEnabled2 = true;
                } else {
                    sheetGesturesEnabled2 = z;
                }
                if ((i & 16) != 0) {
                    sheetShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty &= -57345;
                } else {
                    sheetShape2 = shape;
                }
                if (i8 != 0) {
                    sheetElevation2 = ModalBottomSheetDefaults.INSTANCE.m1139getElevationD9Ej5fM();
                } else {
                    sheetElevation2 = sheetElevation;
                }
                if ((i & 64) != 0) {
                    sheetBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty &= -3670017;
                } else {
                    sheetBackgroundColor2 = sheetBackgroundColor;
                }
                if ((i & 128) != 0) {
                    sheetContentColor2 = ColorsKt.m1066contentColorForek8zF_U(sheetBackgroundColor2, $composer3, ($dirty >> 18) & 14);
                    $dirty &= -29360129;
                } else {
                    sheetContentColor2 = sheetContentColor;
                }
                if ((i & 256) != 0) {
                    sheetGesturesEnabled3 = sheetGesturesEnabled2;
                    sheetState3 = sheetState2;
                    sheetBackgroundColor3 = sheetBackgroundColor2;
                    sheetContentColor3 = sheetContentColor2;
                    scrimColor2 = ModalBottomSheetDefaults.INSTANCE.getScrimColor($composer3, 6);
                    sheetShape3 = sheetShape2;
                    $dirty2 = $dirty & (-234881025);
                    sheetElevation3 = sheetElevation2;
                } else {
                    scrimColor2 = scrimColor;
                    sheetGesturesEnabled3 = sheetGesturesEnabled2;
                    sheetState3 = sheetState2;
                    sheetShape3 = sheetShape2;
                    sheetElevation3 = sheetElevation2;
                    sheetBackgroundColor3 = sheetBackgroundColor2;
                    sheetContentColor3 = sheetContentColor2;
                    $dirty2 = $dirty;
                }
            } else {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    sheetState2 = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, (AnimationSpec<Float>) null, (Function1<? super ModalBottomSheetValue, Boolean>) null, false, $composer3, 6, 14);
                    $dirty &= -897;
                } else {
                    sheetState2 = sheetState;
                }
                if (i2 != 0) {
                    sheetGesturesEnabled2 = true;
                } else {
                    sheetGesturesEnabled2 = z;
                }
                if ((i & 16) != 0) {
                    sheetShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty &= -57345;
                } else {
                    sheetShape2 = shape;
                }
                if (i8 != 0) {
                    sheetElevation2 = ModalBottomSheetDefaults.INSTANCE.m1139getElevationD9Ej5fM();
                } else {
                    sheetElevation2 = sheetElevation;
                }
                if ((i & 64) != 0) {
                    sheetBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty &= -3670017;
                } else {
                    sheetBackgroundColor2 = sheetBackgroundColor;
                }
                if ((i & 128) != 0) {
                    sheetContentColor2 = ColorsKt.m1066contentColorForek8zF_U(sheetBackgroundColor2, $composer3, ($dirty >> 18) & 14);
                    $dirty &= -29360129;
                } else {
                    sheetContentColor2 = sheetContentColor;
                }
                if ((i & 256) != 0) {
                    sheetGesturesEnabled3 = sheetGesturesEnabled2;
                    sheetState3 = sheetState2;
                    sheetBackgroundColor3 = sheetBackgroundColor2;
                    sheetContentColor3 = sheetContentColor2;
                    scrimColor2 = ModalBottomSheetDefaults.INSTANCE.getScrimColor($composer3, 6);
                    sheetShape3 = sheetShape2;
                    $dirty2 = $dirty & (-234881025);
                    sheetElevation3 = sheetElevation2;
                } else {
                    scrimColor2 = scrimColor;
                    sheetGesturesEnabled3 = sheetGesturesEnabled2;
                    sheetState3 = sheetState2;
                    sheetShape3 = sheetShape2;
                    sheetElevation3 = sheetElevation2;
                    sheetBackgroundColor3 = sheetBackgroundColor2;
                    sheetContentColor3 = sheetContentColor2;
                    $dirty2 = $dirty;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-92970288, $dirty2, -1, "androidx.compose.material.ModalBottomSheetLayout (ModalBottomSheet.kt:561)");
            }
            $composer3.startReplaceableGroup(502769027);
            ComposerKt.sourceInformation($composer3, "576@23903L7,577@23919L63");
            if (sheetState3.getDensity() == null) {
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer3.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final Density density2 = (Density) objConsume2;
                EffectsKt.SideEffect(new Function0<Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$1
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
                        sheetState3.setDensity$material_release(density2);
                    }
                }, $composer3, 0);
            }
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
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
            scope = wrapper$iv2.getCoroutineScope();
            $composer3.endReplaceableGroup();
            final Orientation orientation2 = Orientation.Vertical;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(sheetState3) | $composer3.changed(scope);
            Object it$iv$iv2 = $composer3.rememberedValue();
            if (invalid$iv$iv) {
                value$iv$iv = ModalBottomSheetAnchorChangeCallback(sheetState3, scope);
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = ModalBottomSheetAnchorChangeCallback(sheetState3, scope);
                $composer3.updateRememberedValue(value$iv$iv);
            }
            $composer3.endReplaceableGroup();
            final AnchoredDraggableState.AnchorChangedCallback<ModalBottomSheetValue> anchorChangeCallback2 = (AnchoredDraggableState.AnchorChangedCallback) value$iv$iv;
            final boolean z4 = sheetGesturesEnabled3;
            final ModalBottomSheetState modalBottomSheetState3 = sheetState3;
            final Shape shape4 = sheetShape3;
            final long j8 = sheetBackgroundColor3;
            final int $dirty5 = $dirty2;
            final long j9 = sheetContentColor3;
            sheetState4 = sheetState3;
            final float f3 = sheetElevation3;
            final long j10 = scrimColor2;
            $composer2 = $composer3;
            BoxWithConstraintsKt.BoxWithConstraints(modifier3, null, false, ComposableLambdaKt.composableLambda($composer2, -1731958854, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
                    invoke(boxWithConstraintsScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer $composer4, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    Modifier.Companion companionNestedScroll$default;
                    Modifier.Companion companionSemantics$default;
                    Object value$iv$iv2;
                    Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
                    ComposerKt.sourceInformation($composer4, "C588@24304L430,600@24743L4244:ModalBottomSheet.kt#jmzs0o");
                    int $dirty6 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty6 |= $composer4.changed(BoxWithConstraints) ? 4 : 2;
                    }
                    if (($dirty6 & 91) != 18 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1731958854, $changed2, -1, "androidx.compose.material.ModalBottomSheetLayout.<anonymous> (ModalBottomSheet.kt:586)");
                        }
                        final float fullHeight = Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk());
                        Modifier modifier$iv = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i11 = $dirty5;
                        long j11 = j10;
                        final ModalBottomSheetState modalBottomSheetState4 = modalBottomSheetState3;
                        final CoroutineScope coroutineScope = scope;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                        int $changed$iv$iv = (6 << 3) & 112;
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
                        int i12 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i13 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1295116475, "C589@24346L9,590@24368L356:ModalBottomSheet.kt#jmzs0o");
                        function2.invoke($composer4, Integer.valueOf((i11 >> 27) & 14));
                        ModalBottomSheetKt.m1141Scrim3JVO9M(j11, new Function0<Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$1$1
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
                                if (modalBottomSheetState4.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(ModalBottomSheetValue.Hidden).booleanValue()) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(modalBottomSheetState4, null), 3, null);
                                }
                            }

                            /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$1$1$1, reason: invalid class name */
                            /* JADX INFO: compiled from: ModalBottomSheet.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                            @DebugMetadata(c = "androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {595}, m = "invokeSuspend", n = {}, s = {})
                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ ModalBottomSheetState $sheetState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass1(ModalBottomSheetState modalBottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                    super(2, continuation);
                                    this.$sheetState = modalBottomSheetState;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new AnonymousClass1(this.$sheetState, continuation);
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
                                            if (this.$sheetState.hide(this) == coroutine_suspended) {
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
                        }, modalBottomSheetState4.getAnchoredDraggableState$material_release().getTargetValue() != ModalBottomSheetValue.Hidden, $composer4, (i11 >> 24) & 14);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SizeKt.m541widthInVpY3zN4$default(BoxWithConstraints.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0.0f, ModalBottomSheetKt.MaxModalBottomSheetWidth, 1, null), 0.0f, 1, null);
                        $composer4.startReplaceableGroup(1241536180);
                        ComposerKt.sourceInformation($composer4, "608@25105L354");
                        if (z4) {
                            Modifier.Companion companion = Modifier.INSTANCE;
                            Object key1$iv = modalBottomSheetState3.getAnchoredDraggableState$material_release();
                            Object key2$iv = orientation2;
                            ModalBottomSheetState modalBottomSheetState5 = modalBottomSheetState3;
                            Orientation orientation3 = orientation2;
                            $composer4.startReplaceableGroup(511388516);
                            ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2):Composables.kt#9igjgp");
                            boolean invalid$iv$iv2 = $composer4.changed(key1$iv) | $composer4.changed(key2$iv);
                            Object it$iv$iv3 = $composer4.rememberedValue();
                            if (!invalid$iv$iv2) {
                                Object key1$iv2 = Composer.INSTANCE.getEmpty();
                                if (it$iv$iv3 != key1$iv2) {
                                    value$iv$iv2 = it$iv$iv3;
                                }
                                $composer4.endReplaceableGroup();
                                companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(companion, (NestedScrollConnection) value$iv$iv2, null, 2, null);
                            }
                            value$iv$iv2 = ModalBottomSheetKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(modalBottomSheetState5.getAnchoredDraggableState$material_release(), orientation3);
                            $composer4.updateRememberedValue(value$iv$iv2);
                            $composer4.endReplaceableGroup();
                            companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(companion, (NestedScrollConnection) value$iv$iv2, null, 2, null);
                        } else {
                            companionNestedScroll$default = Modifier.INSTANCE;
                        }
                        $composer4.endReplaceableGroup();
                        Modifier modifierThen = modifierFillMaxWidth$default.then(companionNestedScroll$default);
                        final ModalBottomSheetState modalBottomSheetState6 = modalBottomSheetState3;
                        Modifier modifierAnchoredDraggable = AnchoredDraggableKt.anchoredDraggable(OffsetKt.offset(modifierThen, new Function1<Density, IntOffset>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2.3
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ IntOffset invoke(Density density3) {
                                return IntOffset.m5383boximpl(m1143invokeBjo55l4(density3));
                            }

                            /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                            public final long m1143invokeBjo55l4(Density offset) {
                                Intrinsics.checkNotNullParameter(offset, "$this$offset");
                                return IntOffsetKt.IntOffset(0, MathKt.roundToInt(modalBottomSheetState6.getAnchoredDraggableState$material_release().requireOffset()));
                            }
                        }), modalBottomSheetState3.getAnchoredDraggableState$material_release(), orientation2, (24 & 4) != 0 ? true : z4 && modalBottomSheetState3.getAnchoredDraggableState$material_release().getCurrentValue() != ModalBottomSheetValue.Hidden, (24 & 8) != 0 ? false : false, (24 & 16) != 0 ? null : null);
                        final ModalBottomSheetState modalBottomSheetState7 = modalBottomSheetState3;
                        final AnchoredDraggableState.AnchorChangedCallback<ModalBottomSheetValue> anchorChangedCallback = anchorChangeCallback2;
                        Modifier modifierOnSizeChanged = OnRemeasuredModifierKt.onSizeChanged(modifierAnchoredDraggable, new Function1<IntSize, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                                m1144invokeozmzZPI(intSize.getPackedValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                            public final void m1144invokeozmzZPI(long sheetSize) {
                                float f4 = fullHeight;
                                ModalBottomSheetState modalBottomSheetState8 = modalBottomSheetState7;
                                Map $this$invoke_ozmzZPI_u24lambda_u240 = MapsKt.createMapBuilder();
                                $this$invoke_ozmzZPI_u24lambda_u240.put(ModalBottomSheetValue.Hidden, Float.valueOf(f4));
                                float halfHeight = f4 / 2.0f;
                                if (!modalBottomSheetState8.getIsSkipHalfExpanded() && IntSize.m5433getHeightimpl(sheetSize) > halfHeight) {
                                    $this$invoke_ozmzZPI_u24lambda_u240.put(ModalBottomSheetValue.HalfExpanded, Float.valueOf(halfHeight));
                                }
                                if (IntSize.m5433getHeightimpl(sheetSize) != 0) {
                                    $this$invoke_ozmzZPI_u24lambda_u240.put(ModalBottomSheetValue.Expanded, Float.valueOf(Math.max(0.0f, f4 - IntSize.m5433getHeightimpl(sheetSize))));
                                }
                                modalBottomSheetState7.getAnchoredDraggableState$material_release().updateAnchors$material_release(MapsKt.build($this$invoke_ozmzZPI_u24lambda_u240), anchorChangedCallback);
                            }
                        });
                        if (z4) {
                            Modifier.Companion companion2 = Modifier.INSTANCE;
                            final ModalBottomSheetState modalBottomSheetState8 = modalBottomSheetState3;
                            final CoroutineScope coroutineScope2 = scope;
                            companionSemantics$default = SemanticsModifierKt.semantics$default(companion2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2.5
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
                                    if (modalBottomSheetState8.isVisible()) {
                                        final ModalBottomSheetState modalBottomSheetState9 = modalBottomSheetState8;
                                        final CoroutineScope coroutineScope3 = coroutineScope2;
                                        SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.ModalBottomSheetLayout.2.5.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Boolean invoke() {
                                                if (modalBottomSheetState9.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(ModalBottomSheetValue.Hidden).booleanValue()) {
                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new C00531(modalBottomSheetState9, null), 3, null);
                                                }
                                                return true;
                                            }

                                            /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$1$1, reason: invalid class name and collision with other inner class name */
                                            /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                            @DebugMetadata(c = "androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$1$1", f = "ModalBottomSheet.kt", i = {}, l = {653}, m = "invokeSuspend", n = {}, s = {})
                                            static final class C00531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ ModalBottomSheetState $sheetState;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                C00531(ModalBottomSheetState modalBottomSheetState, Continuation<? super C00531> continuation) {
                                                    super(2, continuation);
                                                    this.$sheetState = modalBottomSheetState;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new C00531(this.$sheetState, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((C00531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object $result) {
                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    switch (this.label) {
                                                        case 0:
                                                            ResultKt.throwOnFailure($result);
                                                            this.label = 1;
                                                            if (this.$sheetState.hide(this) == coroutine_suspended) {
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
                                        if (modalBottomSheetState8.getAnchoredDraggableState$material_release().getCurrentValue() == ModalBottomSheetValue.HalfExpanded) {
                                            final ModalBottomSheetState modalBottomSheetState10 = modalBottomSheetState8;
                                            final CoroutineScope coroutineScope4 = coroutineScope2;
                                            SemanticsPropertiesKt.expand$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.ModalBottomSheetLayout.2.5.2
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    if (modalBottomSheetState10.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(ModalBottomSheetValue.Expanded).booleanValue()) {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope4, null, null, new AnonymousClass1(modalBottomSheetState10, null), 3, null);
                                                    }
                                                    return true;
                                                }

                                                /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$2$1, reason: invalid class name */
                                                /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                                @DebugMetadata(c = "androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$2$1", f = "ModalBottomSheet.kt", i = {}, l = {665}, m = "invokeSuspend", n = {}, s = {})
                                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ ModalBottomSheetState $sheetState;
                                                    int label;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass1(ModalBottomSheetState modalBottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                                        super(2, continuation);
                                                        this.$sheetState = modalBottomSheetState;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new AnonymousClass1(this.$sheetState, continuation);
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
                                                                if (this.$sheetState.expand$material_release(this) == coroutine_suspended) {
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
                                        } else if (modalBottomSheetState8.getHasHalfExpandedState$material_release()) {
                                            final ModalBottomSheetState modalBottomSheetState11 = modalBottomSheetState8;
                                            final CoroutineScope coroutineScope5 = coroutineScope2;
                                            SemanticsPropertiesKt.collapse$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.ModalBottomSheetLayout.2.5.3
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    if (modalBottomSheetState11.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(ModalBottomSheetValue.HalfExpanded).booleanValue()) {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope5, null, null, new AnonymousClass1(modalBottomSheetState11, null), 3, null);
                                                    }
                                                    return true;
                                                }

                                                /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$3$1, reason: invalid class name */
                                                /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                                @DebugMetadata(c = "androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$3$1", f = "ModalBottomSheet.kt", i = {}, l = {675}, m = "invokeSuspend", n = {}, s = {})
                                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ ModalBottomSheetState $sheetState;
                                                    int label;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass1(ModalBottomSheetState modalBottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                                        super(2, continuation);
                                                        this.$sheetState = modalBottomSheetState;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new AnonymousClass1(this.$sheetState, continuation);
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
                                                                if (this.$sheetState.halfExpand$material_release(this) == coroutine_suspended) {
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
                            }, 1, null);
                        } else {
                            companionSemantics$default = Modifier.INSTANCE;
                        }
                        Modifier modifierThen2 = modifierOnSizeChanged.then(companionSemantics$default);
                        Shape shape5 = shape4;
                        long j12 = j8;
                        long j13 = j9;
                        float f4 = f3;
                        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3 = sheetContent;
                        final int i14 = $dirty5;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, 1552994302, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2.6
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

                            public final void invoke(Composer $composer5, int $changed3) {
                                ComposerKt.sourceInformation($composer5, "C688@28947L30:ModalBottomSheet.kt#jmzs0o");
                                if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1552994302, $changed3, -1, "androidx.compose.material.ModalBottomSheetLayout.<anonymous>.<anonymous> (ModalBottomSheet.kt:687)");
                                }
                                Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                int $changed$iv = (i14 << 9) & 7168;
                                $composer5.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                                Modifier modifier$iv2 = Modifier.INSTANCE;
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv2 = ($changed$iv << 3) & 112;
                                $composer5.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer5, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                CompositionLocalMap localMap$iv$iv2 = $composer5.getCurrentCompositionLocalMap();
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                if (!($composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer5.startReusableNode();
                                if ($composer5.getInserting()) {
                                    $composer5.createNode(constructor2);
                                } else {
                                    $composer5.useNode();
                                }
                                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2603constructorimpl($composer5);
                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                                }
                                function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                $composer5.startReplaceableGroup(2058660585);
                                int i15 = ($changed$iv$iv$iv2 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, 276693570, "C77@3893L9:Column.kt#2w3rfo");
                                function4.invoke(ColumnScopeInstance.INSTANCE, $composer5, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endReplaceableGroup();
                                $composer5.endNode();
                                $composer5.endReplaceableGroup();
                                $composer5.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        });
                        int i15 = $dirty5;
                        SurfaceKt.m1210SurfaceFjzlyU(modifierThen2, shape5, j12, j13, null, f4, composableLambda, $composer4, ((i15 >> 9) & 112) | 1572864 | ((i15 >> 12) & 896) | ((i15 >> 12) & 7168) | (i15 & 458752), 16);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, (($dirty5 >> 3) & 14) | 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    sheetState2 = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, (AnimationSpec<Float>) null, (Function1<? super ModalBottomSheetValue, Boolean>) null, false, $composer3, 6, 14);
                    $dirty &= -897;
                } else {
                    sheetState2 = sheetState;
                }
                if (i2 != 0) {
                    sheetGesturesEnabled2 = true;
                } else {
                    sheetGesturesEnabled2 = z;
                }
                if ((i & 16) != 0) {
                    sheetShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty &= -57345;
                } else {
                    sheetShape2 = shape;
                }
                if (i8 != 0) {
                    sheetElevation2 = ModalBottomSheetDefaults.INSTANCE.m1139getElevationD9Ej5fM();
                } else {
                    sheetElevation2 = sheetElevation;
                }
                if ((i & 64) != 0) {
                    sheetBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty &= -3670017;
                } else {
                    sheetBackgroundColor2 = sheetBackgroundColor;
                }
                if ((i & 128) != 0) {
                    sheetContentColor2 = ColorsKt.m1066contentColorForek8zF_U(sheetBackgroundColor2, $composer3, ($dirty >> 18) & 14);
                    $dirty &= -29360129;
                } else {
                    sheetContentColor2 = sheetContentColor;
                }
                if ((i & 256) != 0) {
                    sheetGesturesEnabled3 = sheetGesturesEnabled2;
                    sheetState3 = sheetState2;
                    sheetBackgroundColor3 = sheetBackgroundColor2;
                    sheetContentColor3 = sheetContentColor2;
                    scrimColor2 = ModalBottomSheetDefaults.INSTANCE.getScrimColor($composer3, 6);
                    sheetShape3 = sheetShape2;
                    $dirty2 = $dirty & (-234881025);
                    sheetElevation3 = sheetElevation2;
                } else {
                    scrimColor2 = scrimColor;
                    sheetGesturesEnabled3 = sheetGesturesEnabled2;
                    sheetState3 = sheetState2;
                    sheetShape3 = sheetShape2;
                    sheetElevation3 = sheetElevation2;
                    sheetBackgroundColor3 = sheetBackgroundColor2;
                    sheetContentColor3 = sheetContentColor2;
                    $dirty2 = $dirty;
                }
            } else {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    sheetState2 = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, (AnimationSpec<Float>) null, (Function1<? super ModalBottomSheetValue, Boolean>) null, false, $composer3, 6, 14);
                    $dirty &= -897;
                } else {
                    sheetState2 = sheetState;
                }
                if (i2 != 0) {
                    sheetGesturesEnabled2 = true;
                } else {
                    sheetGesturesEnabled2 = z;
                }
                if ((i & 16) != 0) {
                    sheetShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty &= -57345;
                } else {
                    sheetShape2 = shape;
                }
                if (i8 != 0) {
                    sheetElevation2 = ModalBottomSheetDefaults.INSTANCE.m1139getElevationD9Ej5fM();
                } else {
                    sheetElevation2 = sheetElevation;
                }
                if ((i & 64) != 0) {
                    sheetBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty &= -3670017;
                } else {
                    sheetBackgroundColor2 = sheetBackgroundColor;
                }
                if ((i & 128) != 0) {
                    sheetContentColor2 = ColorsKt.m1066contentColorForek8zF_U(sheetBackgroundColor2, $composer3, ($dirty >> 18) & 14);
                    $dirty &= -29360129;
                } else {
                    sheetContentColor2 = sheetContentColor;
                }
                if ((i & 256) != 0) {
                    sheetGesturesEnabled3 = sheetGesturesEnabled2;
                    sheetState3 = sheetState2;
                    sheetBackgroundColor3 = sheetBackgroundColor2;
                    sheetContentColor3 = sheetContentColor2;
                    scrimColor2 = ModalBottomSheetDefaults.INSTANCE.getScrimColor($composer3, 6);
                    sheetShape3 = sheetShape2;
                    $dirty2 = $dirty & (-234881025);
                    sheetElevation3 = sheetElevation2;
                } else {
                    scrimColor2 = scrimColor;
                    sheetGesturesEnabled3 = sheetGesturesEnabled2;
                    sheetState3 = sheetState2;
                    sheetShape3 = sheetShape2;
                    sheetElevation3 = sheetElevation2;
                    sheetBackgroundColor3 = sheetBackgroundColor2;
                    sheetContentColor3 = sheetContentColor2;
                    $dirty2 = $dirty;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-92970288, $dirty2, -1, "androidx.compose.material.ModalBottomSheetLayout (ModalBottomSheet.kt:561)");
            }
            $composer3.startReplaceableGroup(502769027);
            ComposerKt.sourceInformation($composer3, "576@23903L7,577@23919L63");
            if (sheetState3.getDensity() == null) {
                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer3.consume(localDensity3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final Density density3 = (Density) objConsume3;
                EffectsKt.SideEffect(new Function0<Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$1
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
                        sheetState3.setDensity$material_release(density3);
                    }
                }, $composer3, 0);
            }
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
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
            scope = wrapper$iv3.getCoroutineScope();
            $composer3.endReplaceableGroup();
            final Orientation orientation3 = Orientation.Vertical;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(sheetState3) | $composer3.changed(scope);
            Object it$iv$iv3 = $composer3.rememberedValue();
            if (invalid$iv$iv) {
                value$iv$iv = ModalBottomSheetAnchorChangeCallback(sheetState3, scope);
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = ModalBottomSheetAnchorChangeCallback(sheetState3, scope);
                $composer3.updateRememberedValue(value$iv$iv);
            }
            $composer3.endReplaceableGroup();
            final AnchoredDraggableState.AnchorChangedCallback<ModalBottomSheetValue> anchorChangeCallback3 = (AnchoredDraggableState.AnchorChangedCallback) value$iv$iv;
            final boolean z5 = sheetGesturesEnabled3;
            final ModalBottomSheetState modalBottomSheetState4 = sheetState3;
            final Shape shape5 = sheetShape3;
            final long j11 = sheetBackgroundColor3;
            final int $dirty6 = $dirty2;
            final long j12 = sheetContentColor3;
            sheetState4 = sheetState3;
            final float f4 = sheetElevation3;
            final long j13 = scrimColor2;
            $composer2 = $composer3;
            BoxWithConstraintsKt.BoxWithConstraints(modifier3, null, false, ComposableLambdaKt.composableLambda($composer2, -1731958854, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
                    invoke(boxWithConstraintsScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer $composer4, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    Modifier.Companion companionNestedScroll$default;
                    Modifier.Companion companionSemantics$default;
                    Object value$iv$iv2;
                    Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
                    ComposerKt.sourceInformation($composer4, "C588@24304L430,600@24743L4244:ModalBottomSheet.kt#jmzs0o");
                    int $dirty7 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty7 |= $composer4.changed(BoxWithConstraints) ? 4 : 2;
                    }
                    if (($dirty7 & 91) != 18 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1731958854, $changed2, -1, "androidx.compose.material.ModalBottomSheetLayout.<anonymous> (ModalBottomSheet.kt:586)");
                        }
                        final float fullHeight = Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk());
                        Modifier modifier$iv = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i11 = $dirty6;
                        long j14 = j13;
                        final ModalBottomSheetState modalBottomSheetState5 = modalBottomSheetState4;
                        final CoroutineScope coroutineScope = scope;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                        int $changed$iv$iv = (6 << 3) & 112;
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
                        int i12 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i13 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1295116475, "C589@24346L9,590@24368L356:ModalBottomSheet.kt#jmzs0o");
                        function2.invoke($composer4, Integer.valueOf((i11 >> 27) & 14));
                        ModalBottomSheetKt.m1141Scrim3JVO9M(j14, new Function0<Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$1$1
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
                                if (modalBottomSheetState5.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(ModalBottomSheetValue.Hidden).booleanValue()) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(modalBottomSheetState5, null), 3, null);
                                }
                            }

                            /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$1$1$1, reason: invalid class name */
                            /* JADX INFO: compiled from: ModalBottomSheet.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                            @DebugMetadata(c = "androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$1$1$1", f = "ModalBottomSheet.kt", i = {}, l = {595}, m = "invokeSuspend", n = {}, s = {})
                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ ModalBottomSheetState $sheetState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass1(ModalBottomSheetState modalBottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                    super(2, continuation);
                                    this.$sheetState = modalBottomSheetState;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new AnonymousClass1(this.$sheetState, continuation);
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
                                            if (this.$sheetState.hide(this) == coroutine_suspended) {
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
                        }, modalBottomSheetState5.getAnchoredDraggableState$material_release().getTargetValue() != ModalBottomSheetValue.Hidden, $composer4, (i11 >> 24) & 14);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SizeKt.m541widthInVpY3zN4$default(BoxWithConstraints.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0.0f, ModalBottomSheetKt.MaxModalBottomSheetWidth, 1, null), 0.0f, 1, null);
                        $composer4.startReplaceableGroup(1241536180);
                        ComposerKt.sourceInformation($composer4, "608@25105L354");
                        if (z5) {
                            Modifier.Companion companion = Modifier.INSTANCE;
                            Object key1$iv = modalBottomSheetState4.getAnchoredDraggableState$material_release();
                            Object key2$iv = orientation3;
                            ModalBottomSheetState modalBottomSheetState6 = modalBottomSheetState4;
                            Orientation orientation4 = orientation3;
                            $composer4.startReplaceableGroup(511388516);
                            ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2):Composables.kt#9igjgp");
                            boolean invalid$iv$iv2 = $composer4.changed(key1$iv) | $composer4.changed(key2$iv);
                            Object it$iv$iv4 = $composer4.rememberedValue();
                            if (!invalid$iv$iv2) {
                                Object key1$iv2 = Composer.INSTANCE.getEmpty();
                                if (it$iv$iv4 != key1$iv2) {
                                    value$iv$iv2 = it$iv$iv4;
                                }
                                $composer4.endReplaceableGroup();
                                companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(companion, (NestedScrollConnection) value$iv$iv2, null, 2, null);
                            }
                            value$iv$iv2 = ModalBottomSheetKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(modalBottomSheetState6.getAnchoredDraggableState$material_release(), orientation4);
                            $composer4.updateRememberedValue(value$iv$iv2);
                            $composer4.endReplaceableGroup();
                            companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(companion, (NestedScrollConnection) value$iv$iv2, null, 2, null);
                        } else {
                            companionNestedScroll$default = Modifier.INSTANCE;
                        }
                        $composer4.endReplaceableGroup();
                        Modifier modifierThen = modifierFillMaxWidth$default.then(companionNestedScroll$default);
                        final ModalBottomSheetState modalBottomSheetState7 = modalBottomSheetState4;
                        Modifier modifierAnchoredDraggable = AnchoredDraggableKt.anchoredDraggable(OffsetKt.offset(modifierThen, new Function1<Density, IntOffset>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2.3
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ IntOffset invoke(Density density4) {
                                return IntOffset.m5383boximpl(m1143invokeBjo55l4(density4));
                            }

                            /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                            public final long m1143invokeBjo55l4(Density offset) {
                                Intrinsics.checkNotNullParameter(offset, "$this$offset");
                                return IntOffsetKt.IntOffset(0, MathKt.roundToInt(modalBottomSheetState7.getAnchoredDraggableState$material_release().requireOffset()));
                            }
                        }), modalBottomSheetState4.getAnchoredDraggableState$material_release(), orientation3, (24 & 4) != 0 ? true : z5 && modalBottomSheetState4.getAnchoredDraggableState$material_release().getCurrentValue() != ModalBottomSheetValue.Hidden, (24 & 8) != 0 ? false : false, (24 & 16) != 0 ? null : null);
                        final ModalBottomSheetState modalBottomSheetState8 = modalBottomSheetState4;
                        final AnchoredDraggableState.AnchorChangedCallback<ModalBottomSheetValue> anchorChangedCallback = anchorChangeCallback3;
                        Modifier modifierOnSizeChanged = OnRemeasuredModifierKt.onSizeChanged(modifierAnchoredDraggable, new Function1<IntSize, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                                m1144invokeozmzZPI(intSize.getPackedValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                            public final void m1144invokeozmzZPI(long sheetSize) {
                                float f5 = fullHeight;
                                ModalBottomSheetState modalBottomSheetState9 = modalBottomSheetState8;
                                Map $this$invoke_ozmzZPI_u24lambda_u240 = MapsKt.createMapBuilder();
                                $this$invoke_ozmzZPI_u24lambda_u240.put(ModalBottomSheetValue.Hidden, Float.valueOf(f5));
                                float halfHeight = f5 / 2.0f;
                                if (!modalBottomSheetState9.getIsSkipHalfExpanded() && IntSize.m5433getHeightimpl(sheetSize) > halfHeight) {
                                    $this$invoke_ozmzZPI_u24lambda_u240.put(ModalBottomSheetValue.HalfExpanded, Float.valueOf(halfHeight));
                                }
                                if (IntSize.m5433getHeightimpl(sheetSize) != 0) {
                                    $this$invoke_ozmzZPI_u24lambda_u240.put(ModalBottomSheetValue.Expanded, Float.valueOf(Math.max(0.0f, f5 - IntSize.m5433getHeightimpl(sheetSize))));
                                }
                                modalBottomSheetState8.getAnchoredDraggableState$material_release().updateAnchors$material_release(MapsKt.build($this$invoke_ozmzZPI_u24lambda_u240), anchorChangedCallback);
                            }
                        });
                        if (z5) {
                            Modifier.Companion companion2 = Modifier.INSTANCE;
                            final ModalBottomSheetState modalBottomSheetState9 = modalBottomSheetState4;
                            final CoroutineScope coroutineScope2 = scope;
                            companionSemantics$default = SemanticsModifierKt.semantics$default(companion2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2.5
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
                                    if (modalBottomSheetState9.isVisible()) {
                                        final ModalBottomSheetState modalBottomSheetState10 = modalBottomSheetState9;
                                        final CoroutineScope coroutineScope3 = coroutineScope2;
                                        SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.ModalBottomSheetLayout.2.5.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Boolean invoke() {
                                                if (modalBottomSheetState10.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(ModalBottomSheetValue.Hidden).booleanValue()) {
                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new C00531(modalBottomSheetState10, null), 3, null);
                                                }
                                                return true;
                                            }

                                            /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$1$1, reason: invalid class name and collision with other inner class name */
                                            /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                            @DebugMetadata(c = "androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$1$1", f = "ModalBottomSheet.kt", i = {}, l = {653}, m = "invokeSuspend", n = {}, s = {})
                                            static final class C00531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ ModalBottomSheetState $sheetState;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                C00531(ModalBottomSheetState modalBottomSheetState, Continuation<? super C00531> continuation) {
                                                    super(2, continuation);
                                                    this.$sheetState = modalBottomSheetState;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new C00531(this.$sheetState, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((C00531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object $result) {
                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    switch (this.label) {
                                                        case 0:
                                                            ResultKt.throwOnFailure($result);
                                                            this.label = 1;
                                                            if (this.$sheetState.hide(this) == coroutine_suspended) {
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
                                        if (modalBottomSheetState9.getAnchoredDraggableState$material_release().getCurrentValue() == ModalBottomSheetValue.HalfExpanded) {
                                            final ModalBottomSheetState modalBottomSheetState11 = modalBottomSheetState9;
                                            final CoroutineScope coroutineScope4 = coroutineScope2;
                                            SemanticsPropertiesKt.expand$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.ModalBottomSheetLayout.2.5.2
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    if (modalBottomSheetState11.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(ModalBottomSheetValue.Expanded).booleanValue()) {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope4, null, null, new AnonymousClass1(modalBottomSheetState11, null), 3, null);
                                                    }
                                                    return true;
                                                }

                                                /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$2$1, reason: invalid class name */
                                                /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                                @DebugMetadata(c = "androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$2$1", f = "ModalBottomSheet.kt", i = {}, l = {665}, m = "invokeSuspend", n = {}, s = {})
                                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ ModalBottomSheetState $sheetState;
                                                    int label;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass1(ModalBottomSheetState modalBottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                                        super(2, continuation);
                                                        this.$sheetState = modalBottomSheetState;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new AnonymousClass1(this.$sheetState, continuation);
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
                                                                if (this.$sheetState.expand$material_release(this) == coroutine_suspended) {
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
                                        } else if (modalBottomSheetState9.getHasHalfExpandedState$material_release()) {
                                            final ModalBottomSheetState modalBottomSheetState12 = modalBottomSheetState9;
                                            final CoroutineScope coroutineScope5 = coroutineScope2;
                                            SemanticsPropertiesKt.collapse$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt.ModalBottomSheetLayout.2.5.3
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    if (modalBottomSheetState12.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(ModalBottomSheetValue.HalfExpanded).booleanValue()) {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope5, null, null, new AnonymousClass1(modalBottomSheetState12, null), 3, null);
                                                    }
                                                    return true;
                                                }

                                                /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$3$1, reason: invalid class name */
                                                /* JADX INFO: compiled from: ModalBottomSheet.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                                @DebugMetadata(c = "androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2$5$3$1", f = "ModalBottomSheet.kt", i = {}, l = {675}, m = "invokeSuspend", n = {}, s = {})
                                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ ModalBottomSheetState $sheetState;
                                                    int label;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass1(ModalBottomSheetState modalBottomSheetState, Continuation<? super AnonymousClass1> continuation) {
                                                        super(2, continuation);
                                                        this.$sheetState = modalBottomSheetState;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new AnonymousClass1(this.$sheetState, continuation);
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
                                                                if (this.$sheetState.halfExpand$material_release(this) == coroutine_suspended) {
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
                            }, 1, null);
                        } else {
                            companionSemantics$default = Modifier.INSTANCE;
                        }
                        Modifier modifierThen2 = modifierOnSizeChanged.then(companionSemantics$default);
                        Shape shape6 = shape5;
                        long j15 = j11;
                        long j16 = j12;
                        float f5 = f4;
                        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3 = sheetContent;
                        final int i14 = $dirty6;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, 1552994302, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$2.6
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

                            public final void invoke(Composer $composer5, int $changed3) {
                                ComposerKt.sourceInformation($composer5, "C688@28947L30:ModalBottomSheet.kt#jmzs0o");
                                if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1552994302, $changed3, -1, "androidx.compose.material.ModalBottomSheetLayout.<anonymous>.<anonymous> (ModalBottomSheet.kt:687)");
                                }
                                Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                int $changed$iv = (i14 << 9) & 7168;
                                $composer5.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                                Modifier modifier$iv2 = Modifier.INSTANCE;
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv2 = ($changed$iv << 3) & 112;
                                $composer5.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer5, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                CompositionLocalMap localMap$iv$iv2 = $composer5.getCurrentCompositionLocalMap();
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                if (!($composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer5.startReusableNode();
                                if ($composer5.getInserting()) {
                                    $composer5.createNode(constructor2);
                                } else {
                                    $composer5.useNode();
                                }
                                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2603constructorimpl($composer5);
                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                                }
                                function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                $composer5.startReplaceableGroup(2058660585);
                                int i15 = ($changed$iv$iv$iv2 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, 276693570, "C77@3893L9:Column.kt#2w3rfo");
                                function4.invoke(ColumnScopeInstance.INSTANCE, $composer5, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endReplaceableGroup();
                                $composer5.endNode();
                                $composer5.endReplaceableGroup();
                                $composer5.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        });
                        int i15 = $dirty6;
                        SurfaceKt.m1210SurfaceFjzlyU(modifierThen2, shape6, j15, j16, null, f5, composableLambda, $composer4, ((i15 >> 9) & 112) | 1572864 | ((i15 >> 12) & 896) | ((i15 >> 12) & 7168) | (i15 & 458752), 16);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, (($dirty6 >> 3) & 14) | 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final ModalBottomSheetState modalBottomSheetState5 = sheetState4;
        final boolean z6 = sheetGesturesEnabled3;
        final Shape shape6 = sheetShape3;
        final float f5 = sheetElevation3;
        final long j14 = sheetBackgroundColor3;
        final long j15 = sheetContentColor3;
        final long j16 = scrimColor2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetLayout$3
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
                ModalBottomSheetKt.m1140ModalBottomSheetLayoutGs3lGvM(sheetContent, modifier5, modalBottomSheetState5, z6, shape6, f5, j14, j15, j16, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Scrim-3J-VO9M, reason: not valid java name */
    public static final void m1141Scrim3JVO9M(final long color, final Function0<Unit> function0, final boolean visible, Composer $composer, final int $changed) {
        Modifier.Companion dismissModifier;
        Object value$iv$iv;
        Object value$iv$iv2;
        Composer $composer2 = $composer.startRestartGroup(-526532668);
        ComposerKt.sourceInformation($composer2, "C(Scrim)P(0:c#ui.graphics.Color)700@29148L121,704@29295L29,720@29810L62,716@29701L171:ModalBottomSheet.kt#jmzs0o");
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
                ComposerKt.traceEventStart(-526532668, $dirty2, -1, "androidx.compose.material.Scrim (ModalBottomSheet.kt:694)");
            }
            if (color != Color.INSTANCE.m3007getUnspecified0d7_KjU()) {
                final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(visible ? 1.0f : 0.0f, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer2, 48, 28);
                final String closeSheet = Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1202getCloseSheetUdPEhr4(), $composer2, 6);
                $composer2.startReplaceableGroup(1010559499);
                ComposerKt.sourceInformation($composer2, "707@29432L37,708@29522L121");
                if (visible) {
                    Modifier.Companion companion = Modifier.INSTANCE;
                    int i = ($dirty2 >> 3) & 14;
                    $composer2.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                    boolean invalid$iv$iv = $composer2.changed(function0);
                    Object it$iv$iv = $composer2.rememberedValue();
                    if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new ModalBottomSheetKt$Scrim$dismissModifier$1$1(function0, null);
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, function0, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv);
                    int i2 = $dirty2 & 112;
                    $composer2.startReplaceableGroup(511388516);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                    boolean invalid$iv$iv2 = $composer2.changed(closeSheet) | $composer2.changed(function0);
                    Object it$iv$iv2 = $composer2.rememberedValue();
                    if (invalid$iv$iv2 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$Scrim$dismissModifier$2$1
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
                                SemanticsPropertiesKt.setContentDescription(semantics, closeSheet);
                                final Function0<Unit> function1 = function0;
                                SemanticsPropertiesKt.onClick$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt$Scrim$dismissModifier$2$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        function1.invoke();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv$iv2);
                    } else {
                        value$iv$iv2 = it$iv$iv2;
                    }
                    $composer2.endReplaceableGroup();
                    dismissModifier = SemanticsModifierKt.semantics(modifierPointerInput, true, (Function1) value$iv$iv2);
                } else {
                    dismissModifier = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissModifier);
                Object key1$iv = Color.m2961boximpl(color);
                int i3 = $dirty2 & 14;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                boolean invalid$iv$iv3 = $composer2.changed(key1$iv) | $composer2.changed(stateAnimateFloatAsState);
                Object value$iv$iv3 = $composer2.rememberedValue();
                if (!invalid$iv$iv3) {
                    Object key1$iv2 = Composer.INSTANCE.getEmpty();
                    if (value$iv$iv3 == key1$iv2) {
                    }
                    $composer2.endReplaceableGroup();
                    CanvasKt.Canvas(modifierThen, (Function1) value$iv$iv3, $composer2, 0);
                }
                value$iv$iv3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$Scrim$1$1
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
                        DrawScope.CC.m3522drawRectnJ9OG0$default(Canvas, color, 0L, 0L, ModalBottomSheetKt.Scrim_3J_VO9M$lambda$2(stateAnimateFloatAsState), null, null, 0, 118, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv3);
                $composer2.endReplaceableGroup();
                CanvasKt.Canvas(modifierThen, (Function1) value$iv$iv3, $composer2, 0);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$Scrim$2
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
                ModalBottomSheetKt.m1141Scrim3JVO9M(color, function0, visible, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Scrim_3J_VO9M$lambda$2(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1, reason: invalid class name */
    /* JADX INFO: compiled from: ModalBottomSheet.kt */
    @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rJ!\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\tH\u0003ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\u0003H\u0003ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0017J\u001a\u0010\u0019\u001a\u00020\t*\u00020\u0015H\u0002ø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001a\u0082\u0002\u000f\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001b"}, d2 = {"androidx/compose/material/ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "toFloat", "", "offsetToFloat", "(J)F", "velocityToFloat", "toOffset", "(F)J", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 implements NestedScrollConnection {
        final /* synthetic */ Orientation $orientation;
        final /* synthetic */ AnchoredDraggableState<?> $state;

        AnonymousClass1(AnchoredDraggableState<?> anchoredDraggableState, Orientation $orientation) {
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
            ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1;
            if (continuation instanceof ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) {
                modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = (ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) continuation;
                if ((modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label & Integer.MIN_VALUE) != 0) {
                    modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label -= Integer.MIN_VALUE;
                } else {
                    modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = new ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1(this, continuation);
                }
            } else {
                modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = new ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1(this, continuation);
            }
            ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2 = modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1;
            Object $result = modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    float toFling = velocityToFloat(available);
                    float currentOffset = this.$state.requireOffset();
                    if (toFling < 0.0f && currentOffset > this.$state.getMinOffset()) {
                        AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                        modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.J$0 = available;
                        modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.label = 1;
                        if (anchoredDraggableState.settle(toFling, modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        available = Velocity.INSTANCE.m5510getZero9UxMQ8M();
                    }
                    break;
                case 1:
                    available = modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.J$0;
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
            ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1;
            if (continuation instanceof ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) {
                modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = (ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) continuation;
                if ((modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label & Integer.MIN_VALUE) != 0) {
                    modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label -= Integer.MIN_VALUE;
                } else {
                    modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = new ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1(this, continuation);
                }
            } else {
                modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = new ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1(this, continuation);
            }
            Object $result = modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                    float fVelocityToFloat = velocityToFloat(available);
                    modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.J$0 = available;
                    modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label = 1;
                    if (anchoredDraggableState.settle(fVelocityToFloat, modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    available = modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.J$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Velocity.m5490boximpl(available);
        }

        private final long toOffset(float $this$toOffset) {
            return androidx.compose.ui.geometry.OffsetKt.Offset(this.$orientation == Orientation.Horizontal ? $this$toOffset : 0.0f, this.$orientation == Orientation.Vertical ? $this$toOffset : 0.0f);
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
        return new AnonymousClass1(anchoredDraggableState, orientation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnchoredDraggableState.AnchorChangedCallback<ModalBottomSheetValue> ModalBottomSheetAnchorChangeCallback(final ModalBottomSheetState state, final CoroutineScope scope) {
        return new AnchoredDraggableState.AnchorChangedCallback<ModalBottomSheetValue>() { // from class: androidx.compose.material.ModalBottomSheetKt.ModalBottomSheetAnchorChangeCallback.1

            /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ModalBottomSheetAnchorChangeCallback$1$WhenMappings */
            /* JADX INFO: compiled from: ModalBottomSheet.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[ModalBottomSheetValue.values().length];
                    try {
                        iArr[ModalBottomSheetValue.Hidden.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[ModalBottomSheetValue.HalfExpanded.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[ModalBottomSheetValue.Expanded.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // androidx.compose.material.AnchoredDraggableState.AnchorChangedCallback
            public final void onAnchorsChanged(ModalBottomSheetValue prevTarget, Map<ModalBottomSheetValue, Float> prevAnchors, Map<ModalBottomSheetValue, Float> newAnchors) {
                ModalBottomSheetValue modalBottomSheetValue;
                Intrinsics.checkNotNullParameter(prevTarget, "prevTarget");
                Intrinsics.checkNotNullParameter(prevAnchors, "prevAnchors");
                Intrinsics.checkNotNullParameter(newAnchors, "newAnchors");
                Float previousTargetOffset = prevAnchors.get(prevTarget);
                switch (WhenMappings.$EnumSwitchMapping$0[prevTarget.ordinal()]) {
                    case 1:
                        modalBottomSheetValue = ModalBottomSheetValue.Hidden;
                        break;
                    case 2:
                    case 3:
                        boolean hasHalfExpandedState = newAnchors.containsKey(ModalBottomSheetValue.HalfExpanded);
                        if (!hasHalfExpandedState) {
                            modalBottomSheetValue = !newAnchors.containsKey(ModalBottomSheetValue.Expanded) ? ModalBottomSheetValue.Hidden : ModalBottomSheetValue.Expanded;
                        } else {
                            modalBottomSheetValue = ModalBottomSheetValue.HalfExpanded;
                        }
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                ModalBottomSheetValue newTarget = modalBottomSheetValue;
                float newTargetOffset = ((Number) MapsKt.getValue(newAnchors, newTarget)).floatValue();
                if (!Intrinsics.areEqual(newTargetOffset, previousTargetOffset)) {
                    if (state.isAnimationRunning$material_release()) {
                        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new ModalBottomSheetKt$ModalBottomSheetAnchorChangeCallback$1$onAnchorsChanged$1(state, newTarget, null), 3, null);
                        return;
                    }
                    boolean didSnapSynchronously = state.trySnapTo$material_release(newTarget);
                    if (!didSnapSynchronously) {
                        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new ModalBottomSheetKt$ModalBottomSheetAnchorChangeCallback$1$onAnchorsChanged$2(state, newTarget, null), 3, null);
                    }
                }
            }
        };
    }
}
