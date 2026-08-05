package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
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
import androidx.compose.foundation.layout.PaddingKt;
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
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.Velocity;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
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

/* JADX INFO: compiled from: Drawer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0096\u0001\u0010\t\u001a\u00020\n2\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u001a2\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\n0\u001e¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020&H\u0002\u001a3\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u001a2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\u0006\u0010*\u001a\u00020\u0015H\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a.\u0010-\u001a\u00020\u00132\u0006\u0010.\u001a\u00020#2\u0006\u0010/\u001a\u0002002\u0014\b\u0002\u00101\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00150\fH\u0007\u001a\u0014\u00102\u001a\u0002032\n\u0010$\u001a\u0006\u0012\u0002\b\u000304H\u0002\u001a\u0096\u0001\u00105\u001a\u00020\n2\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u0002062\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u001a2\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\n0\u001e¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b7\u00108\u001aA\u00109\u001a\u00020\n2\u0006\u0010:\u001a\u00020\u00152\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00020\u001e2\u0006\u0010(\u001a\u00020\u001aH\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001a.\u0010?\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0006\u0012\u0004\u0018\u00010\u00020@2\u0006\u0010A\u001a\u00020\u00022\u0006\u0010B\u001a\u00020\u00022\u0006\u0010C\u001a\u00020\u0015H\u0002\u001a \u0010D\u001a\u00020\u00022\u0006\u0010E\u001a\u00020\u00022\u0006\u0010F\u001a\u00020\u00022\u0006\u0010G\u001a\u00020\u0002H\u0002\u001a+\u0010H\u001a\u00020\u00132\u0006\u0010.\u001a\u00020#2\u0014\b\u0002\u00101\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00150\fH\u0007¢\u0006\u0002\u0010I\u001a+\u0010J\u001a\u0002062\u0006\u0010.\u001a\u00020K2\u0014\b\u0002\u00101\u001a\u000e\u0012\u0004\u0012\u00020K\u0012\u0004\u0012\u00020\u00150\fH\u0007¢\u0006\u0002\u0010L\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0013\u0010\u0004\u001a\u00020\u0005X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0006\"\u0013\u0010\u0007\u001a\u00020\u0005X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0006\"\u0013\u0010\b\u001a\u00020\u0005X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0006\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006M²\u0006\n\u0010N\u001a\u00020\u0002X\u008a\u0084\u0002"}, d2 = {"AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "BottomDrawerOpenFraction", "DrawerPositionalThreshold", "Landroidx/compose/ui/unit/Dp;", "F", "DrawerVelocityThreshold", "EndDrawerPadding", "BottomDrawer", "", "drawerContent", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "drawerState", "Landroidx/compose/material/BottomDrawerState;", "gesturesEnabled", "", "drawerShape", "Landroidx/compose/ui/graphics/Shape;", "drawerElevation", "drawerBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "drawerContentColor", "scrimColor", "content", "Lkotlin/Function0;", "BottomDrawer-Gs3lGvM", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/BottomDrawerState;ZLandroidx/compose/ui/graphics/Shape;FJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "BottomDrawerAnchorChangeCallback", "Landroidx/compose/material/AnchoredDraggableState$AnchorChangedCallback;", "Landroidx/compose/material/BottomDrawerValue;", "state", "scope", "Lkotlinx/coroutines/CoroutineScope;", "BottomDrawerScrim", "color", "onDismiss", "visible", "BottomDrawerScrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "BottomDrawerState", "initialValue", "density", "Landroidx/compose/ui/unit/Density;", "confirmStateChange", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroidx/compose/material/AnchoredDraggableState;", "ModalDrawer", "Landroidx/compose/material/DrawerState;", "ModalDrawer-Gs3lGvM", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/DrawerState;ZLandroidx/compose/ui/graphics/Shape;FJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Scrim", "open", "onClose", "fraction", "Scrim-Bx497Mc", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;JLandroidx/compose/runtime/Composer;I)V", "calculateAnchors", "", "fullHeight", "drawerHeight", "isLandscape", "calculateFraction", "a", "b", "pos", "rememberBottomDrawerState", "(Landroidx/compose/material/BottomDrawerValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomDrawerState;", "rememberDrawerState", "Landroidx/compose/material/DrawerValue;", "(Landroidx/compose/material/DrawerValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/DrawerState;", "material_release", "alpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DrawerKt {
    private static final float BottomDrawerOpenFraction = 0.5f;
    private static final float EndDrawerPadding = Dp.m5274constructorimpl(56);
    private static final float DrawerPositionalThreshold = Dp.m5274constructorimpl(56);
    private static final float DrawerVelocityThreshold = Dp.m5274constructorimpl(400);
    private static final TweenSpec<Float> AnimationSpec = new TweenSpec<>(256, 0, null, 6, null);

    public static /* synthetic */ BottomDrawerState BottomDrawerState$default(BottomDrawerValue bottomDrawerValue, Density density, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = new Function1<BottomDrawerValue, Boolean>() { // from class: androidx.compose.material.DrawerKt.BottomDrawerState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(BottomDrawerValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
        }
        return BottomDrawerState(bottomDrawerValue, density, function1);
    }

    public static final BottomDrawerState BottomDrawerState(BottomDrawerValue initialValue, Density density, Function1<? super BottomDrawerValue, Boolean> confirmStateChange) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(confirmStateChange, "confirmStateChange");
        BottomDrawerState it = new BottomDrawerState(initialValue, confirmStateChange);
        it.setDensity$material_release(density);
        return it;
    }

    public static final DrawerState rememberDrawerState(final DrawerValue initialValue, final Function1<? super DrawerValue, Boolean> function1, Composer $composer, int $changed, int i) {
        Object value$iv$iv;
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        $composer.startReplaceableGroup(-1435874229);
        ComposerKt.sourceInformation($composer, "C(rememberDrawerState)P(1)455@15934L61,455@15870L125:Drawer.kt#jmzs0o");
        if ((i & 2) != 0) {
            Function1 confirmStateChange = new Function1<DrawerValue, Boolean>() { // from class: androidx.compose.material.DrawerKt.rememberDrawerState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(DrawerValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
            function1 = confirmStateChange;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1435874229, $changed, -1, "androidx.compose.material.rememberDrawerState (Drawer.kt:451)");
        }
        Object[] objArr = new Object[0];
        Saver<DrawerState, DrawerValue> Saver = DrawerState.INSTANCE.Saver(function1);
        int i2 = ($changed & 14) | ($changed & 112);
        $composer.startReplaceableGroup(511388516);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(initialValue) | $composer.changed(function1);
        Object it$iv$iv = $composer.rememberedValue();
        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new Function0<DrawerState>() { // from class: androidx.compose.material.DrawerKt$rememberDrawerState$2$1
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

    public static final BottomDrawerState rememberBottomDrawerState(final BottomDrawerValue initialValue, Function1<? super BottomDrawerValue, Boolean> function1, Composer $composer, int $changed, int i) {
        Object value$iv$iv;
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        $composer.startReplaceableGroup(-598115156);
        ComposerKt.sourceInformation($composer, "C(rememberBottomDrawerState)P(1)472@16436L7,473@16543L76,473@16455L164:Drawer.kt#jmzs0o");
        final Function1<? super BottomDrawerValue, Boolean> function2 = (i & 2) != 0 ? new Function1<BottomDrawerValue, Boolean>() { // from class: androidx.compose.material.DrawerKt.rememberBottomDrawerState.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BottomDrawerValue it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return true;
            }
        } : function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-598115156, $changed, -1, "androidx.compose.material.rememberBottomDrawerState (Drawer.kt:468)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) objConsume;
        Object[] objArr = {density};
        Saver<BottomDrawerState, BottomDrawerValue> Saver = BottomDrawerState.INSTANCE.Saver(density, function2);
        int i2 = ($changed & 14) | (($changed << 3) & 896);
        $composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(initialValue) | $composer.changed(density) | $composer.changed(function2);
        Object it$iv$iv = $composer.rememberedValue();
        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = (Function0) new Function0<BottomDrawerState>() { // from class: androidx.compose.material.DrawerKt$rememberBottomDrawerState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final BottomDrawerState invoke() {
                    return DrawerKt.BottomDrawerState(initialValue, density, function2);
                }
            };
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        $composer.endReplaceableGroup();
        BottomDrawerState bottomDrawerState = (BottomDrawerState) RememberSaveableKt.m2617rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv$iv, $composer, 72, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return bottomDrawerState;
    }

    /* JADX WARN: Code duplicated, block: B:116:0x0164  */
    /* JADX WARN: Code duplicated, block: B:118:0x0175  */
    /* JADX WARN: Code duplicated, block: B:137:0x01c4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:138:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:139:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:142:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:143:0x01de  */
    /* JADX WARN: Code duplicated, block: B:145:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:146:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:149:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:150:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:152:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:153:0x0203  */
    /* JADX WARN: Code duplicated, block: B:156:0x0208  */
    /* JADX WARN: Code duplicated, block: B:157:0x0215  */
    /* JADX WARN: Code duplicated, block: B:160:0x021b  */
    /* JADX WARN: Code duplicated, block: B:161:0x0228  */
    /* JADX WARN: Code duplicated, block: B:164:0x022e  */
    /* JADX WARN: Code duplicated, block: B:165:0x024a  */
    /* JADX WARN: Code duplicated, block: B:168:0x0264  */
    /* JADX WARN: Code duplicated, block: B:171:0x029e  */
    /* JADX WARN: Code duplicated, block: B:172:0x02bb  */
    /* JADX WARN: Code duplicated, block: B:175:0x031e  */
    /* JADX WARN: Code duplicated, block: B:178:0x0327  */
    /* JADX WARN: Code duplicated, block: B:179:0x032a  */
    /* JADX INFO: renamed from: ModalDrawer-Gs3lGvM, reason: not valid java name */
    public static final void m1091ModalDrawerGs3lGvM(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> drawerContent, Modifier modifier, DrawerState drawerState, boolean gesturesEnabled, Shape drawerShape, float drawerElevation, long drawerBackgroundColor, long drawerContentColor, long scrimColor, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        DrawerState drawerState2;
        boolean z;
        Shape shape;
        float f;
        int i2;
        Modifier.Companion modifier2;
        DrawerState drawerState3;
        boolean gesturesEnabled2;
        CornerBasedShape drawerShape2;
        float drawerElevation2;
        long drawerBackgroundColor2;
        long drawerContentColor2;
        Modifier modifier3;
        long scrimColor2;
        Shape drawerShape3;
        float drawerElevation3;
        long drawerBackgroundColor3;
        long drawerContentColor3;
        DrawerState drawerState4;
        int $dirty;
        boolean gesturesEnabled3;
        Object it$iv$iv$iv;
        Object value$iv$iv$iv;
        Modifier modifier4;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(drawerContent, "drawerContent");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(1305806945);
        ComposerKt.sourceInformation($composer3, "C(ModalDrawer)P(2,8,6,7,5,4:c#ui.unit.Dp,1:c#ui.graphics.Color,3:c#ui.graphics.Color,9:c#ui.graphics.Color)512@18429L39,514@18546L6,516@18661L6,517@18709L38,518@18788L10,521@18856L24,522@18885L3435:Drawer.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(drawerContent) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                drawerState2 = drawerState;
                int i4 = $composer3.changed(drawerState2) ? 256 : 128;
                $dirty2 |= i4;
            } else {
                drawerState2 = drawerState;
            }
            $dirty2 |= i4;
        } else {
            drawerState2 = drawerState;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            z = gesturesEnabled;
        } else if (($changed & 7168) == 0) {
            z = gesturesEnabled;
            $dirty2 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = gesturesEnabled;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                shape = drawerShape;
                int i6 = $composer3.changed(shape) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                shape = drawerShape;
            }
            $dirty2 |= i6;
        } else {
            shape = drawerShape;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = drawerElevation;
        } else if ((458752 & $changed) == 0) {
            f = drawerElevation;
            $dirty2 |= $composer3.changed(f) ? 131072 : 65536;
        } else {
            f = drawerElevation;
        }
        if (($changed & 3670016) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer3.changed(drawerBackgroundColor)) ? 1048576 : 524288;
        }
        if (($changed & 29360128) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer3.changed(drawerContentColor)) ? 8388608 : 4194304;
        }
        if ((234881024 & $changed) == 0) {
            $dirty2 |= ((i & 256) == 0 && $composer3.changed(scrimColor)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) == 0) {
            if ((1879048192 & $changed) == 0) {
                i2 = $composer3.changedInstance(content) ? 536870912 : 268435456;
            }
            if ((1533916891 & $dirty2) == 306783378 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                        $dirty2 &= -897;
                    } else {
                        drawerState3 = drawerState2;
                    }
                    if (i5 != 0) {
                        gesturesEnabled2 = true;
                    } else {
                        gesturesEnabled2 = z;
                    }
                    if ((i & 16) != 0) {
                        drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                        $dirty2 &= -57345;
                    } else {
                        drawerShape2 = shape;
                    }
                    if (i7 != 0) {
                        drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                    } else {
                        drawerElevation2 = f;
                    }
                    if ((i & 64) != 0) {
                        drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                        $dirty2 &= -3670017;
                    } else {
                        drawerBackgroundColor2 = drawerBackgroundColor;
                    }
                    if ((i & 128) != 0) {
                        drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer3, ($dirty2 >> 18) & 14);
                        $dirty2 &= -29360129;
                    } else {
                        drawerContentColor2 = drawerContentColor;
                    }
                    if ((i & 256) != 0) {
                        modifier3 = modifier2;
                        drawerShape3 = drawerShape2;
                        drawerContentColor3 = drawerContentColor2;
                        scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                        drawerState4 = drawerState3;
                        gesturesEnabled3 = gesturesEnabled2;
                        drawerElevation3 = drawerElevation2;
                        drawerBackgroundColor3 = drawerBackgroundColor2;
                        $dirty = $dirty2 & (-234881025);
                    } else {
                        modifier3 = modifier2;
                        scrimColor2 = scrimColor;
                        drawerShape3 = drawerShape2;
                        drawerElevation3 = drawerElevation2;
                        drawerBackgroundColor3 = drawerBackgroundColor2;
                        drawerContentColor3 = drawerContentColor2;
                        drawerState4 = drawerState3;
                        $dirty = $dirty2;
                        gesturesEnabled3 = gesturesEnabled2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 64) != 0) {
                        $dirty2 &= -3670017;
                    }
                    if ((i & 128) != 0) {
                        $dirty2 &= -29360129;
                    }
                    if ((i & 256) != 0) {
                        modifier3 = modifier;
                        drawerBackgroundColor3 = drawerBackgroundColor;
                        drawerContentColor3 = drawerContentColor;
                        scrimColor2 = scrimColor;
                        drawerState4 = drawerState2;
                        gesturesEnabled3 = z;
                        drawerShape3 = shape;
                        drawerElevation3 = f;
                        $dirty = (-234881025) & $dirty2;
                    } else {
                        modifier3 = modifier;
                        drawerBackgroundColor3 = drawerBackgroundColor;
                        drawerContentColor3 = drawerContentColor;
                        scrimColor2 = scrimColor;
                        drawerState4 = drawerState2;
                        gesturesEnabled3 = z;
                        drawerShape3 = shape;
                        drawerElevation3 = f;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1305806945, $dirty, -1, "androidx.compose.material.ModalDrawer (Drawer.kt:509)");
                }
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
                final CoroutineScope scope = wrapper$iv.getCoroutineScope();
                $composer3.endReplaceableGroup();
                final DrawerState drawerState5 = drawerState4;
                final boolean z2 = gesturesEnabled3;
                final int i8 = $dirty;
                final long j = scrimColor2;
                final Shape shape2 = drawerShape3;
                final long j2 = drawerBackgroundColor3;
                modifier4 = modifier3;
                final long j3 = drawerContentColor3;
                final float f2 = drawerElevation3;
                $composer2 = $composer3;
                BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), null, false, ComposableLambdaKt.composableLambda($composer2, 816674999, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1
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
                        Object value$iv$iv;
                        Function0<ComposeUiNode> function0;
                        Function0<ComposeUiNode> function1;
                        Object key1$iv;
                        Object value$iv$iv2;
                        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
                        ComposerKt.sourceInformation($composer4, "C531@19307L7,532@19334L217,532@19323L228,538@19594L7,539@19633L2681:Drawer.kt#jmzs0o");
                        int $dirty3 = $changed2;
                        if (($changed2 & 14) == 0) {
                            $dirty3 |= $composer4.changed(BoxWithConstraints) ? 4 : 2;
                        }
                        if (($dirty3 & 91) != 18 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(816674999, $changed2, -1, "androidx.compose.material.ModalDrawer.<anonymous> (Drawer.kt:522)");
                            }
                            long modalDrawerConstraints = BoxWithConstraints.mo424getConstraintsmsEJaDk();
                            if (!Constraints.m5214getHasBoundedWidthimpl(modalDrawerConstraints)) {
                                throw new IllegalStateException("Drawer shouldn't have infinite width");
                            }
                            final float minValue = -Constraints.m5218getMaxWidthimpl(modalDrawerConstraints);
                            final float maxValue = 0.0f;
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume = $composer4.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            final Density density = (Density) objConsume;
                            Object[] keys$iv = {drawerState5, density, Float.valueOf(minValue), Float.valueOf(0.0f)};
                            final DrawerState drawerState6 = drawerState5;
                            $composer4.startReplaceableGroup(-568225417);
                            ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv = false;
                            for (Object key$iv : keys$iv) {
                                invalid$iv |= $composer4.changed(key$iv);
                            }
                            Object it$iv$iv = $composer4.rememberedValue();
                            if (invalid$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$1$1
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
                                        drawerState6.setDensity$material_release(density);
                                        Map anchors = MapsKt.mapOf(TuplesKt.to(DrawerValue.Closed, Float.valueOf(minValue)), TuplesKt.to(DrawerValue.Open, Float.valueOf(maxValue)));
                                        AnchoredDraggableState.updateAnchors$material_release$default(drawerState6.getAnchoredDraggableState$material_release(), anchors, null, 2, null);
                                    }
                                };
                                $composer4.updateRememberedValue(value$iv$iv);
                            } else {
                                value$iv$iv = it$iv$iv;
                            }
                            $composer4.endReplaceableGroup();
                            EffectsKt.SideEffect((Function0) value$iv$iv, $composer4, 0);
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer4.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            boolean isRtl = objConsume2 == LayoutDirection.Rtl;
                            Modifier modifier$iv = AnchoredDraggableKt.anchoredDraggable(Modifier.INSTANCE, drawerState5.getAnchoredDraggableState$material_release(), Orientation.Horizontal, (24 & 4) != 0 ? true : z2, (24 & 8) != 0 ? false : isRtl, (24 & 16) != 0 ? null : null);
                            final DrawerState drawerState7 = drawerState5;
                            final int i9 = i8;
                            long j4 = j;
                            Shape shape3 = shape2;
                            long j5 = j2;
                            long j6 = j3;
                            float f3 = f2;
                            Function2<Composer, Integer, Unit> function2 = content;
                            final boolean z3 = z2;
                            final CoroutineScope coroutineScope = scope;
                            final Function3<ColumnScope, Composer, Integer, Unit> function3 = drawerContent;
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
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
                            int i10 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i11 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, 413829465, "C548@19951L45,561@20406L106,551@20009L553,566@20596L33,*568@20696L7,577@21160L222,567@20642L1662:Drawer.kt#jmzs0o");
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                            Modifier modifier$iv2 = Modifier.INSTANCE;
                            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv2 = (0 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                function1 = constructor2;
                                $composer4.createNode(function1);
                            } else {
                                function1 = constructor2;
                                $composer4.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2603constructorimpl($composer4);
                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                            }
                            function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i12 = ($changed$iv$iv$iv2 >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                            int i13 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, 392281232, "C549@19973L9:Drawer.kt#jmzs0o");
                            function2.invoke($composer4, Integer.valueOf((i9 >> 27) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            boolean zIsOpen = drawerState7.isOpen();
                            Function0<Unit> function4 = new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$2
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
                                    if (z3 && drawerState7.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(DrawerValue.Closed).booleanValue()) {
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(drawerState7, null), 3, null);
                                    }
                                }

                                /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$ModalDrawer$1$2$2$1, reason: invalid class name */
                                /* JADX INFO: compiled from: Drawer.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                @DebugMetadata(c = "androidx.compose.material.DrawerKt$ModalDrawer$1$2$2$1", f = "Drawer.kt", i = {}, l = {559}, m = "invokeSuspend", n = {}, s = {})
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
                            Object key1$iv2 = Float.valueOf(minValue);
                            Object key2$iv = Float.valueOf(0.0f);
                            int i14 = (i9 & 896) | 48;
                            $composer4.startReplaceableGroup(1618982084);
                            ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                            boolean invalid$iv$iv = $composer4.changed(key1$iv2) | $composer4.changed(key2$iv) | $composer4.changed(drawerState7);
                            Object it$iv$iv2 = $composer4.rememberedValue();
                            if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                                key1$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$3$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Float invoke() {
                                        return Float.valueOf(DrawerKt.calculateFraction(minValue, maxValue, drawerState7.requireOffset$material_release()));
                                    }
                                };
                                $composer4.updateRememberedValue(key1$iv);
                            } else {
                                key1$iv = it$iv$iv2;
                            }
                            $composer4.endReplaceableGroup();
                            DrawerKt.m1092ScrimBx497Mc(zIsOpen, function4, (Function0) key1$iv, j4, $composer4, (i9 >> 15) & 7168);
                            final String navigationMenu = Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1205getNavigationMenuUdPEhr4(), $composer4, 6);
                            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer4.consume(localDensity2);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density $this$invoke_u24lambda_u245_u24lambda_u243 = (Density) objConsume3;
                            Modifier modifierM537sizeInqDBjuR0 = SizeKt.m537sizeInqDBjuR0(Modifier.INSTANCE, $this$invoke_u24lambda_u245_u24lambda_u243.mo324toDpu2uoSUM(Constraints.m5220getMinWidthimpl(modalDrawerConstraints)), $this$invoke_u24lambda_u245_u24lambda_u243.mo324toDpu2uoSUM(Constraints.m5219getMinHeightimpl(modalDrawerConstraints)), $this$invoke_u24lambda_u245_u24lambda_u243.mo324toDpu2uoSUM(Constraints.m5218getMaxWidthimpl(modalDrawerConstraints)), $this$invoke_u24lambda_u245_u24lambda_u243.mo324toDpu2uoSUM(Constraints.m5217getMaxHeightimpl(modalDrawerConstraints)));
                            int i15 = (i9 >> 6) & 14;
                            $composer4.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv$iv2 = $composer4.changed(drawerState7);
                            Object it$iv$iv3 = $composer4.rememberedValue();
                            if (invalid$iv$iv2 || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv2 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$5$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ IntOffset invoke(Density density2) {
                                        return IntOffset.m5383boximpl(m1098invokeBjo55l4(density2));
                                    }

                                    /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                                    public final long m1098invokeBjo55l4(Density offset) {
                                        Intrinsics.checkNotNullParameter(offset, "$this$offset");
                                        return IntOffsetKt.IntOffset(MathKt.roundToInt(drawerState7.requireOffset$material_release()), 0);
                                    }
                                };
                                $composer4.updateRememberedValue(value$iv$iv2);
                            } else {
                                value$iv$iv2 = it$iv$iv3;
                            }
                            $composer4.endReplaceableGroup();
                            int i16 = i9 >> 12;
                            SurfaceKt.m1210SurfaceFjzlyU(SemanticsModifierKt.semantics$default(PaddingKt.m491paddingqDBjuR0$default(OffsetKt.offset(modifierM537sizeInqDBjuR0, (Function1) value$iv$iv2), 0.0f, 0.0f, DrawerKt.EndDrawerPadding, 0.0f, 11, null), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$6
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
                                    if (drawerState7.isOpen()) {
                                        final DrawerState drawerState8 = drawerState7;
                                        final CoroutineScope coroutineScope2 = coroutineScope;
                                        SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$6.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Boolean invoke() {
                                                if (drawerState8.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(DrawerValue.Closed).booleanValue()) {
                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new C00471(drawerState8, null), 3, null);
                                                }
                                                return true;
                                            }

                                            /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$ModalDrawer$1$2$6$1$1, reason: invalid class name and collision with other inner class name */
                                            /* JADX INFO: compiled from: Drawer.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                            @DebugMetadata(c = "androidx.compose.material.DrawerKt$ModalDrawer$1$2$6$1$1", f = "Drawer.kt", i = {}, l = {594}, m = "invokeSuspend", n = {}, s = {})
                                            static final class C00471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ DrawerState $drawerState;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                C00471(DrawerState drawerState, Continuation<? super C00471> continuation) {
                                                    super(2, continuation);
                                                    this.$drawerState = drawerState;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new C00471(this.$drawerState, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((C00471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                            }, 1, null), shape3, j5, j6, null, f3, ComposableLambdaKt.composableLambda($composer4, -1941234439, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$7
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
                                    ComposerKt.sourceInformation($composer5, "C603@22235L55:Drawer.kt#jmzs0o");
                                    if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                        $composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1941234439, $changed3, -1, "androidx.compose.material.ModalDrawer.<anonymous>.<anonymous>.<anonymous> (Drawer.kt:602)");
                                    }
                                    Modifier modifier$iv3 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                                    Function3<ColumnScope, Composer, Integer, Unit> function5 = function3;
                                    int $changed$iv = ((i9 << 9) & 7168) | 6;
                                    $composer5.startReplaceableGroup(-483455358);
                                    ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                    MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                    int $changed$iv$iv3 = ($changed$iv << 3) & 112;
                                    $composer5.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer5, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                    int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                    CompositionLocalMap localMap$iv$iv3 = $composer5.getCurrentCompositionLocalMap();
                                    Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifier$iv3);
                                    int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
                                    if (!($composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    $composer5.startReusableNode();
                                    if ($composer5.getInserting()) {
                                        $composer5.createNode(constructor3);
                                    } else {
                                        $composer5.useNode();
                                    }
                                    Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2603constructorimpl($composer5);
                                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                                    }
                                    function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                                    $composer5.startReplaceableGroup(2058660585);
                                    int i17 = ($changed$iv$iv$iv3 >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer5, 276693570, "C77@3893L9:Column.kt#2w3rfo");
                                    function5.invoke(ColumnScopeInstance.INSTANCE, $composer5, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    $composer5.endReplaceableGroup();
                                    $composer5.endNode();
                                    $composer5.endReplaceableGroup();
                                    $composer5.endReplaceableGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }), $composer4, ((i9 >> 9) & 112) | 1572864 | (i16 & 896) | (i16 & 7168) | (458752 & i9), 16);
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
                }), $composer2, 3072, 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier4 = modifier;
                drawerBackgroundColor3 = drawerBackgroundColor;
                drawerContentColor3 = drawerContentColor;
                scrimColor2 = scrimColor;
                drawerState4 = drawerState2;
                gesturesEnabled3 = z;
                drawerShape3 = shape;
                $composer2 = $composer3;
                drawerElevation3 = f;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final DrawerState drawerState6 = drawerState4;
            final boolean z3 = gesturesEnabled3;
            final Shape shape3 = drawerShape3;
            final float f3 = drawerElevation3;
            final long j4 = drawerBackgroundColor3;
            final long j5 = drawerContentColor3;
            final long j6 = scrimColor2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$2
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
                    DrawerKt.m1091ModalDrawerGs3lGvM(drawerContent, modifier5, drawerState6, z3, shape3, f3, j4, j5, j6, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty2 |= i2;
        if ((1533916891 & $dirty2) == 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty2 &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                } else {
                    gesturesEnabled2 = z;
                }
                if ((i & 16) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty2 &= -57345;
                } else {
                    drawerShape2 = shape;
                }
                if (i7 != 0) {
                    drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                } else {
                    drawerElevation2 = f;
                }
                if ((i & 64) != 0) {
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -3670017;
                } else {
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                if ((i & 128) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer3, ($dirty2 >> 18) & 14);
                    $dirty2 &= -29360129;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 256) != 0) {
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContentColor3 = drawerContentColor2;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    drawerState4 = drawerState3;
                    gesturesEnabled3 = gesturesEnabled2;
                    drawerElevation3 = drawerElevation2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    $dirty = $dirty2 & (-234881025);
                } else {
                    modifier3 = modifier2;
                    scrimColor2 = scrimColor;
                    drawerShape3 = drawerShape2;
                    drawerElevation3 = drawerElevation2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerState4 = drawerState3;
                    $dirty = $dirty2;
                    gesturesEnabled3 = gesturesEnabled2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty2 &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                } else {
                    gesturesEnabled2 = z;
                }
                if ((i & 16) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty2 &= -57345;
                } else {
                    drawerShape2 = shape;
                }
                if (i7 != 0) {
                    drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                } else {
                    drawerElevation2 = f;
                }
                if ((i & 64) != 0) {
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -3670017;
                } else {
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                if ((i & 128) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer3, ($dirty2 >> 18) & 14);
                    $dirty2 &= -29360129;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 256) != 0) {
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContentColor3 = drawerContentColor2;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    drawerState4 = drawerState3;
                    gesturesEnabled3 = gesturesEnabled2;
                    drawerElevation3 = drawerElevation2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    $dirty = $dirty2 & (-234881025);
                } else {
                    modifier3 = modifier2;
                    scrimColor2 = scrimColor;
                    drawerShape3 = drawerShape2;
                    drawerElevation3 = drawerElevation2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerState4 = drawerState3;
                    $dirty = $dirty2;
                    gesturesEnabled3 = gesturesEnabled2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1305806945, $dirty, -1, "androidx.compose.material.ModalDrawer (Drawer.kt:509)");
            }
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
            final CoroutineScope scope2 = wrapper$iv2.getCoroutineScope();
            $composer3.endReplaceableGroup();
            final DrawerState drawerState7 = drawerState4;
            final boolean z4 = gesturesEnabled3;
            final int i9 = $dirty;
            final long j7 = scrimColor2;
            final Shape shape4 = drawerShape3;
            final long j8 = drawerBackgroundColor3;
            modifier4 = modifier3;
            final long j9 = drawerContentColor3;
            final float f4 = drawerElevation3;
            $composer2 = $composer3;
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), null, false, ComposableLambdaKt.composableLambda($composer2, 816674999, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1
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
                    Object value$iv$iv;
                    Function0<ComposeUiNode> function0;
                    Function0<ComposeUiNode> function1;
                    Object key1$iv;
                    Object value$iv$iv2;
                    Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
                    ComposerKt.sourceInformation($composer4, "C531@19307L7,532@19334L217,532@19323L228,538@19594L7,539@19633L2681:Drawer.kt#jmzs0o");
                    int $dirty3 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty3 |= $composer4.changed(BoxWithConstraints) ? 4 : 2;
                    }
                    if (($dirty3 & 91) != 18 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(816674999, $changed2, -1, "androidx.compose.material.ModalDrawer.<anonymous> (Drawer.kt:522)");
                        }
                        long modalDrawerConstraints = BoxWithConstraints.mo424getConstraintsmsEJaDk();
                        if (!Constraints.m5214getHasBoundedWidthimpl(modalDrawerConstraints)) {
                            throw new IllegalStateException("Drawer shouldn't have infinite width");
                        }
                        final float minValue = -Constraints.m5218getMaxWidthimpl(modalDrawerConstraints);
                        final float maxValue = 0.0f;
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        final Density density = (Density) objConsume;
                        Object[] keys$iv = {drawerState7, density, Float.valueOf(minValue), Float.valueOf(0.0f)};
                        final DrawerState drawerState8 = drawerState7;
                        $composer4.startReplaceableGroup(-568225417);
                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                        boolean invalid$iv = false;
                        for (Object key$iv : keys$iv) {
                            invalid$iv |= $composer4.changed(key$iv);
                        }
                        Object it$iv$iv = $composer4.rememberedValue();
                        if (invalid$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$1$1
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
                                    drawerState8.setDensity$material_release(density);
                                    Map anchors = MapsKt.mapOf(TuplesKt.to(DrawerValue.Closed, Float.valueOf(minValue)), TuplesKt.to(DrawerValue.Open, Float.valueOf(maxValue)));
                                    AnchoredDraggableState.updateAnchors$material_release$default(drawerState8.getAnchoredDraggableState$material_release(), anchors, null, 2, null);
                                }
                            };
                            $composer4.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer4.endReplaceableGroup();
                        EffectsKt.SideEffect((Function0) value$iv$iv, $composer4, 0);
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        boolean isRtl = objConsume2 == LayoutDirection.Rtl;
                        Modifier modifier$iv = AnchoredDraggableKt.anchoredDraggable(Modifier.INSTANCE, drawerState7.getAnchoredDraggableState$material_release(), Orientation.Horizontal, (24 & 4) != 0 ? true : z4, (24 & 8) != 0 ? false : isRtl, (24 & 16) != 0 ? null : null);
                        final DrawerState drawerState9 = drawerState7;
                        final int i10 = i9;
                        long j10 = j7;
                        Shape shape5 = shape4;
                        long j11 = j8;
                        long j12 = j9;
                        float f5 = f4;
                        Function2<Composer, Integer, Unit> function2 = content;
                        final boolean z5 = z4;
                        final CoroutineScope coroutineScope = scope2;
                        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3 = drawerContent;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
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
                        int i11 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i12 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 413829465, "C548@19951L45,561@20406L106,551@20009L553,566@20596L33,*568@20696L7,577@21160L222,567@20642L1662:Drawer.kt#jmzs0o");
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Modifier modifier$iv2 = Modifier.INSTANCE;
                        Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv2 = (0 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            function1 = constructor2;
                            $composer4.createNode(function1);
                        } else {
                            function1 = constructor2;
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                        }
                        function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i13 = ($changed$iv$iv$iv2 >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                        int i14 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 392281232, "C549@19973L9:Drawer.kt#jmzs0o");
                        function2.invoke($composer4, Integer.valueOf((i10 >> 27) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        boolean zIsOpen = drawerState9.isOpen();
                        Function0<Unit> function4 = new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$2
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
                                if (z5 && drawerState9.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(DrawerValue.Closed).booleanValue()) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(drawerState9, null), 3, null);
                                }
                            }

                            /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$ModalDrawer$1$2$2$1, reason: invalid class name */
                            /* JADX INFO: compiled from: Drawer.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                            @DebugMetadata(c = "androidx.compose.material.DrawerKt$ModalDrawer$1$2$2$1", f = "Drawer.kt", i = {}, l = {559}, m = "invokeSuspend", n = {}, s = {})
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
                        Object key1$iv2 = Float.valueOf(minValue);
                        Object key2$iv = Float.valueOf(0.0f);
                        int i15 = (i10 & 896) | 48;
                        $composer4.startReplaceableGroup(1618982084);
                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                        boolean invalid$iv$iv = $composer4.changed(key1$iv2) | $composer4.changed(key2$iv) | $composer4.changed(drawerState9);
                        Object it$iv$iv2 = $composer4.rememberedValue();
                        if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                            key1$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$3$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Float invoke() {
                                    return Float.valueOf(DrawerKt.calculateFraction(minValue, maxValue, drawerState9.requireOffset$material_release()));
                                }
                            };
                            $composer4.updateRememberedValue(key1$iv);
                        } else {
                            key1$iv = it$iv$iv2;
                        }
                        $composer4.endReplaceableGroup();
                        DrawerKt.m1092ScrimBx497Mc(zIsOpen, function4, (Function0) key1$iv, j10, $composer4, (i10 >> 15) & 7168);
                        final String navigationMenu = Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1205getNavigationMenuUdPEhr4(), $composer4, 6);
                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localDensity2);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density $this$invoke_u24lambda_u245_u24lambda_u243 = (Density) objConsume3;
                        Modifier modifierM537sizeInqDBjuR0 = SizeKt.m537sizeInqDBjuR0(Modifier.INSTANCE, $this$invoke_u24lambda_u245_u24lambda_u243.mo324toDpu2uoSUM(Constraints.m5220getMinWidthimpl(modalDrawerConstraints)), $this$invoke_u24lambda_u245_u24lambda_u243.mo324toDpu2uoSUM(Constraints.m5219getMinHeightimpl(modalDrawerConstraints)), $this$invoke_u24lambda_u245_u24lambda_u243.mo324toDpu2uoSUM(Constraints.m5218getMaxWidthimpl(modalDrawerConstraints)), $this$invoke_u24lambda_u245_u24lambda_u243.mo324toDpu2uoSUM(Constraints.m5217getMaxHeightimpl(modalDrawerConstraints)));
                        int i16 = (i10 >> 6) & 14;
                        $composer4.startReplaceableGroup(1157296644);
                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                        boolean invalid$iv$iv2 = $composer4.changed(drawerState9);
                        Object it$iv$iv3 = $composer4.rememberedValue();
                        if (invalid$iv$iv2 || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv2 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$5$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ IntOffset invoke(Density density2) {
                                    return IntOffset.m5383boximpl(m1098invokeBjo55l4(density2));
                                }

                                /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                                public final long m1098invokeBjo55l4(Density offset) {
                                    Intrinsics.checkNotNullParameter(offset, "$this$offset");
                                    return IntOffsetKt.IntOffset(MathKt.roundToInt(drawerState9.requireOffset$material_release()), 0);
                                }
                            };
                            $composer4.updateRememberedValue(value$iv$iv2);
                        } else {
                            value$iv$iv2 = it$iv$iv3;
                        }
                        $composer4.endReplaceableGroup();
                        int i17 = i10 >> 12;
                        SurfaceKt.m1210SurfaceFjzlyU(SemanticsModifierKt.semantics$default(PaddingKt.m491paddingqDBjuR0$default(OffsetKt.offset(modifierM537sizeInqDBjuR0, (Function1) value$iv$iv2), 0.0f, 0.0f, DrawerKt.EndDrawerPadding, 0.0f, 11, null), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$6
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
                                if (drawerState9.isOpen()) {
                                    final DrawerState drawerState10 = drawerState9;
                                    final CoroutineScope coroutineScope2 = coroutineScope;
                                    SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$6.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Boolean invoke() {
                                            if (drawerState10.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(DrawerValue.Closed).booleanValue()) {
                                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new C00471(drawerState10, null), 3, null);
                                            }
                                            return true;
                                        }

                                        /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$ModalDrawer$1$2$6$1$1, reason: invalid class name and collision with other inner class name */
                                        /* JADX INFO: compiled from: Drawer.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                        @DebugMetadata(c = "androidx.compose.material.DrawerKt$ModalDrawer$1$2$6$1$1", f = "Drawer.kt", i = {}, l = {594}, m = "invokeSuspend", n = {}, s = {})
                                        static final class C00471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ DrawerState $drawerState;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            C00471(DrawerState drawerState, Continuation<? super C00471> continuation) {
                                                super(2, continuation);
                                                this.$drawerState = drawerState;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new C00471(this.$drawerState, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((C00471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                        }, 1, null), shape5, j11, j12, null, f5, ComposableLambdaKt.composableLambda($composer4, -1941234439, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$7
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
                                ComposerKt.sourceInformation($composer5, "C603@22235L55:Drawer.kt#jmzs0o");
                                if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1941234439, $changed3, -1, "androidx.compose.material.ModalDrawer.<anonymous>.<anonymous>.<anonymous> (Drawer.kt:602)");
                                }
                                Modifier modifier$iv3 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                                Function3<ColumnScope, Composer, Integer, Unit> function5 = function3;
                                int $changed$iv = ((i10 << 9) & 7168) | 6;
                                $composer5.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv3 = ($changed$iv << 3) & 112;
                                $composer5.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer5, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                CompositionLocalMap localMap$iv$iv3 = $composer5.getCurrentCompositionLocalMap();
                                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifier$iv3);
                                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
                                if (!($composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer5.startReusableNode();
                                if ($composer5.getInserting()) {
                                    $composer5.createNode(constructor3);
                                } else {
                                    $composer5.useNode();
                                }
                                Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2603constructorimpl($composer5);
                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                                }
                                function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                                $composer5.startReplaceableGroup(2058660585);
                                int i18 = ($changed$iv$iv$iv3 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, 276693570, "C77@3893L9:Column.kt#2w3rfo");
                                function5.invoke(ColumnScopeInstance.INSTANCE, $composer5, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endReplaceableGroup();
                                $composer5.endNode();
                                $composer5.endReplaceableGroup();
                                $composer5.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), $composer4, ((i10 >> 9) & 112) | 1572864 | (i17 & 896) | (i17 & 7168) | (458752 & i10), 16);
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
            }), $composer2, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty2 &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                } else {
                    gesturesEnabled2 = z;
                }
                if ((i & 16) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty2 &= -57345;
                } else {
                    drawerShape2 = shape;
                }
                if (i7 != 0) {
                    drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                } else {
                    drawerElevation2 = f;
                }
                if ((i & 64) != 0) {
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -3670017;
                } else {
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                if ((i & 128) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer3, ($dirty2 >> 18) & 14);
                    $dirty2 &= -29360129;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 256) != 0) {
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContentColor3 = drawerContentColor2;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    drawerState4 = drawerState3;
                    gesturesEnabled3 = gesturesEnabled2;
                    drawerElevation3 = drawerElevation2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    $dirty = $dirty2 & (-234881025);
                } else {
                    modifier3 = modifier2;
                    scrimColor2 = scrimColor;
                    drawerShape3 = drawerShape2;
                    drawerElevation3 = drawerElevation2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerState4 = drawerState3;
                    $dirty = $dirty2;
                    gesturesEnabled3 = gesturesEnabled2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    drawerState3 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty2 &= -897;
                } else {
                    drawerState3 = drawerState2;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                } else {
                    gesturesEnabled2 = z;
                }
                if ((i & 16) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty2 &= -57345;
                } else {
                    drawerShape2 = shape;
                }
                if (i7 != 0) {
                    drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                } else {
                    drawerElevation2 = f;
                }
                if ((i & 64) != 0) {
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -3670017;
                } else {
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                if ((i & 128) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer3, ($dirty2 >> 18) & 14);
                    $dirty2 &= -29360129;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 256) != 0) {
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContentColor3 = drawerContentColor2;
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    drawerState4 = drawerState3;
                    gesturesEnabled3 = gesturesEnabled2;
                    drawerElevation3 = drawerElevation2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    $dirty = $dirty2 & (-234881025);
                } else {
                    modifier3 = modifier2;
                    scrimColor2 = scrimColor;
                    drawerShape3 = drawerShape2;
                    drawerElevation3 = drawerElevation2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerState4 = drawerState3;
                    $dirty = $dirty2;
                    gesturesEnabled3 = gesturesEnabled2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1305806945, $dirty, -1, "androidx.compose.material.ModalDrawer (Drawer.kt:509)");
            }
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
            final CoroutineScope scope3 = wrapper$iv3.getCoroutineScope();
            $composer3.endReplaceableGroup();
            final DrawerState drawerState8 = drawerState4;
            final boolean z5 = gesturesEnabled3;
            final int i10 = $dirty;
            final long j10 = scrimColor2;
            final Shape shape5 = drawerShape3;
            final long j11 = drawerBackgroundColor3;
            modifier4 = modifier3;
            final long j12 = drawerContentColor3;
            final float f5 = drawerElevation3;
            $composer2 = $composer3;
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), null, false, ComposableLambdaKt.composableLambda($composer2, 816674999, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1
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
                    Object value$iv$iv;
                    Function0<ComposeUiNode> function0;
                    Function0<ComposeUiNode> function1;
                    Object key1$iv;
                    Object value$iv$iv2;
                    Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
                    ComposerKt.sourceInformation($composer4, "C531@19307L7,532@19334L217,532@19323L228,538@19594L7,539@19633L2681:Drawer.kt#jmzs0o");
                    int $dirty3 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty3 |= $composer4.changed(BoxWithConstraints) ? 4 : 2;
                    }
                    if (($dirty3 & 91) != 18 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(816674999, $changed2, -1, "androidx.compose.material.ModalDrawer.<anonymous> (Drawer.kt:522)");
                        }
                        long modalDrawerConstraints = BoxWithConstraints.mo424getConstraintsmsEJaDk();
                        if (!Constraints.m5214getHasBoundedWidthimpl(modalDrawerConstraints)) {
                            throw new IllegalStateException("Drawer shouldn't have infinite width");
                        }
                        final float minValue = -Constraints.m5218getMaxWidthimpl(modalDrawerConstraints);
                        final float maxValue = 0.0f;
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        final Density density = (Density) objConsume;
                        Object[] keys$iv = {drawerState8, density, Float.valueOf(minValue), Float.valueOf(0.0f)};
                        final DrawerState drawerState9 = drawerState8;
                        $composer4.startReplaceableGroup(-568225417);
                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                        boolean invalid$iv = false;
                        for (Object key$iv : keys$iv) {
                            invalid$iv |= $composer4.changed(key$iv);
                        }
                        Object it$iv$iv = $composer4.rememberedValue();
                        if (invalid$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$1$1
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
                                    drawerState9.setDensity$material_release(density);
                                    Map anchors = MapsKt.mapOf(TuplesKt.to(DrawerValue.Closed, Float.valueOf(minValue)), TuplesKt.to(DrawerValue.Open, Float.valueOf(maxValue)));
                                    AnchoredDraggableState.updateAnchors$material_release$default(drawerState9.getAnchoredDraggableState$material_release(), anchors, null, 2, null);
                                }
                            };
                            $composer4.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer4.endReplaceableGroup();
                        EffectsKt.SideEffect((Function0) value$iv$iv, $composer4, 0);
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        boolean isRtl = objConsume2 == LayoutDirection.Rtl;
                        Modifier modifier$iv = AnchoredDraggableKt.anchoredDraggable(Modifier.INSTANCE, drawerState8.getAnchoredDraggableState$material_release(), Orientation.Horizontal, (24 & 4) != 0 ? true : z5, (24 & 8) != 0 ? false : isRtl, (24 & 16) != 0 ? null : null);
                        final DrawerState drawerState10 = drawerState8;
                        final int i11 = i10;
                        long j13 = j10;
                        Shape shape6 = shape5;
                        long j14 = j11;
                        long j15 = j12;
                        float f6 = f5;
                        Function2<Composer, Integer, Unit> function2 = content;
                        final boolean z6 = z5;
                        final CoroutineScope coroutineScope = scope3;
                        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3 = drawerContent;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
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
                        int i13 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 413829465, "C548@19951L45,561@20406L106,551@20009L553,566@20596L33,*568@20696L7,577@21160L222,567@20642L1662:Drawer.kt#jmzs0o");
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Modifier modifier$iv2 = Modifier.INSTANCE;
                        Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv2 = (0 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            function1 = constructor2;
                            $composer4.createNode(function1);
                        } else {
                            function1 = constructor2;
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                        }
                        function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i14 = ($changed$iv$iv$iv2 >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                        int i15 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 392281232, "C549@19973L9:Drawer.kt#jmzs0o");
                        function2.invoke($composer4, Integer.valueOf((i11 >> 27) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        boolean zIsOpen = drawerState10.isOpen();
                        Function0<Unit> function4 = new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$2
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
                                if (z6 && drawerState10.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(DrawerValue.Closed).booleanValue()) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(drawerState10, null), 3, null);
                                }
                            }

                            /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$ModalDrawer$1$2$2$1, reason: invalid class name */
                            /* JADX INFO: compiled from: Drawer.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                            @DebugMetadata(c = "androidx.compose.material.DrawerKt$ModalDrawer$1$2$2$1", f = "Drawer.kt", i = {}, l = {559}, m = "invokeSuspend", n = {}, s = {})
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
                        Object key1$iv2 = Float.valueOf(minValue);
                        Object key2$iv = Float.valueOf(0.0f);
                        int i16 = (i11 & 896) | 48;
                        $composer4.startReplaceableGroup(1618982084);
                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                        boolean invalid$iv$iv = $composer4.changed(key1$iv2) | $composer4.changed(key2$iv) | $composer4.changed(drawerState10);
                        Object it$iv$iv2 = $composer4.rememberedValue();
                        if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                            key1$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$3$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Float invoke() {
                                    return Float.valueOf(DrawerKt.calculateFraction(minValue, maxValue, drawerState10.requireOffset$material_release()));
                                }
                            };
                            $composer4.updateRememberedValue(key1$iv);
                        } else {
                            key1$iv = it$iv$iv2;
                        }
                        $composer4.endReplaceableGroup();
                        DrawerKt.m1092ScrimBx497Mc(zIsOpen, function4, (Function0) key1$iv, j13, $composer4, (i11 >> 15) & 7168);
                        final String navigationMenu = Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1205getNavigationMenuUdPEhr4(), $composer4, 6);
                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localDensity2);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density $this$invoke_u24lambda_u245_u24lambda_u243 = (Density) objConsume3;
                        Modifier modifierM537sizeInqDBjuR0 = SizeKt.m537sizeInqDBjuR0(Modifier.INSTANCE, $this$invoke_u24lambda_u245_u24lambda_u243.mo324toDpu2uoSUM(Constraints.m5220getMinWidthimpl(modalDrawerConstraints)), $this$invoke_u24lambda_u245_u24lambda_u243.mo324toDpu2uoSUM(Constraints.m5219getMinHeightimpl(modalDrawerConstraints)), $this$invoke_u24lambda_u245_u24lambda_u243.mo324toDpu2uoSUM(Constraints.m5218getMaxWidthimpl(modalDrawerConstraints)), $this$invoke_u24lambda_u245_u24lambda_u243.mo324toDpu2uoSUM(Constraints.m5217getMaxHeightimpl(modalDrawerConstraints)));
                        int i17 = (i11 >> 6) & 14;
                        $composer4.startReplaceableGroup(1157296644);
                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                        boolean invalid$iv$iv2 = $composer4.changed(drawerState10);
                        Object it$iv$iv3 = $composer4.rememberedValue();
                        if (invalid$iv$iv2 || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv2 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$5$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ IntOffset invoke(Density density2) {
                                    return IntOffset.m5383boximpl(m1098invokeBjo55l4(density2));
                                }

                                /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                                public final long m1098invokeBjo55l4(Density offset) {
                                    Intrinsics.checkNotNullParameter(offset, "$this$offset");
                                    return IntOffsetKt.IntOffset(MathKt.roundToInt(drawerState10.requireOffset$material_release()), 0);
                                }
                            };
                            $composer4.updateRememberedValue(value$iv$iv2);
                        } else {
                            value$iv$iv2 = it$iv$iv3;
                        }
                        $composer4.endReplaceableGroup();
                        int i18 = i11 >> 12;
                        SurfaceKt.m1210SurfaceFjzlyU(SemanticsModifierKt.semantics$default(PaddingKt.m491paddingqDBjuR0$default(OffsetKt.offset(modifierM537sizeInqDBjuR0, (Function1) value$iv$iv2), 0.0f, 0.0f, DrawerKt.EndDrawerPadding, 0.0f, 11, null), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$6
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
                                if (drawerState10.isOpen()) {
                                    final DrawerState drawerState11 = drawerState10;
                                    final CoroutineScope coroutineScope2 = coroutineScope;
                                    SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$6.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Boolean invoke() {
                                            if (drawerState11.getAnchoredDraggableState$material_release().getConfirmValueChange$material_release().invoke(DrawerValue.Closed).booleanValue()) {
                                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new C00471(drawerState11, null), 3, null);
                                            }
                                            return true;
                                        }

                                        /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$ModalDrawer$1$2$6$1$1, reason: invalid class name and collision with other inner class name */
                                        /* JADX INFO: compiled from: Drawer.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                        @DebugMetadata(c = "androidx.compose.material.DrawerKt$ModalDrawer$1$2$6$1$1", f = "Drawer.kt", i = {}, l = {594}, m = "invokeSuspend", n = {}, s = {})
                                        static final class C00471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ DrawerState $drawerState;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            C00471(DrawerState drawerState, Continuation<? super C00471> continuation) {
                                                super(2, continuation);
                                                this.$drawerState = drawerState;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new C00471(this.$drawerState, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((C00471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                        }, 1, null), shape6, j14, j15, null, f6, ComposableLambdaKt.composableLambda($composer4, -1941234439, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$1$2$7
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
                                ComposerKt.sourceInformation($composer5, "C603@22235L55:Drawer.kt#jmzs0o");
                                if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1941234439, $changed3, -1, "androidx.compose.material.ModalDrawer.<anonymous>.<anonymous>.<anonymous> (Drawer.kt:602)");
                                }
                                Modifier modifier$iv3 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                                Function3<ColumnScope, Composer, Integer, Unit> function5 = function3;
                                int $changed$iv = ((i11 << 9) & 7168) | 6;
                                $composer5.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv3 = ($changed$iv << 3) & 112;
                                $composer5.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer5, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                CompositionLocalMap localMap$iv$iv3 = $composer5.getCurrentCompositionLocalMap();
                                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifier$iv3);
                                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
                                if (!($composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer5.startReusableNode();
                                if ($composer5.getInserting()) {
                                    $composer5.createNode(constructor3);
                                } else {
                                    $composer5.useNode();
                                }
                                Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2603constructorimpl($composer5);
                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                                }
                                function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                                $composer5.startReplaceableGroup(2058660585);
                                int i19 = ($changed$iv$iv$iv3 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, 276693570, "C77@3893L9:Column.kt#2w3rfo");
                                function5.invoke(ColumnScopeInstance.INSTANCE, $composer5, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endReplaceableGroup();
                                $composer5.endNode();
                                $composer5.endReplaceableGroup();
                                $composer5.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), $composer4, ((i11 >> 9) & 112) | 1572864 | (i18 & 896) | (i18 & 7168) | (458752 & i11), 16);
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
            }), $composer2, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final DrawerState drawerState9 = drawerState4;
        final boolean z6 = gesturesEnabled3;
        final Shape shape6 = drawerShape3;
        final float f6 = drawerElevation3;
        final long j13 = drawerBackgroundColor3;
        final long j14 = drawerContentColor3;
        final long j15 = scrimColor2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$ModalDrawer$2
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
                DrawerKt.m1091ModalDrawerGs3lGvM(drawerContent, modifier6, drawerState9, z6, shape6, f6, j13, j14, j15, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:110:0x0154  */
    /* JADX WARN: Code duplicated, block: B:112:0x0165  */
    /* JADX WARN: Code duplicated, block: B:131:0x01b3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:132:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:133:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:136:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:137:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:139:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:140:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:143:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:144:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:146:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:147:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:150:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:151:0x0204  */
    /* JADX WARN: Code duplicated, block: B:154:0x020a  */
    /* JADX WARN: Code duplicated, block: B:155:0x0215  */
    /* JADX WARN: Code duplicated, block: B:158:0x021b  */
    /* JADX WARN: Code duplicated, block: B:159:0x0236  */
    /* JADX WARN: Code duplicated, block: B:162:0x024f  */
    /* JADX WARN: Code duplicated, block: B:165:0x026a  */
    /* JADX WARN: Code duplicated, block: B:168:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:169:0x02df  */
    /* JADX WARN: Code duplicated, block: B:172:0x0342  */
    /* JADX WARN: Code duplicated, block: B:175:0x034b  */
    /* JADX WARN: Code duplicated, block: B:176:0x034e  */
    /* JADX INFO: renamed from: BottomDrawer-Gs3lGvM, reason: not valid java name */
    public static final void m1089BottomDrawerGs3lGvM(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> drawerContent, Modifier modifier, BottomDrawerState drawerState, boolean gesturesEnabled, Shape drawerShape, float drawerElevation, long drawerBackgroundColor, long drawerContentColor, long scrimColor, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Shape shape;
        long j;
        int i2;
        Modifier.Companion modifier2;
        BottomDrawerState drawerState2;
        boolean gesturesEnabled2;
        CornerBasedShape drawerShape2;
        float drawerElevation2;
        long drawerBackgroundColor2;
        long drawerContentColor2;
        long scrimColor2;
        boolean gesturesEnabled3;
        long drawerBackgroundColor3;
        long drawerContentColor3;
        Modifier modifier3;
        final BottomDrawerState drawerState3;
        Shape drawerShape3;
        float drawerElevation3;
        int $dirty;
        Object value$iv$iv$iv;
        Modifier modifier4;
        BottomDrawerState drawerState4;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(drawerContent, "drawerContent");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(625649286);
        ComposerKt.sourceInformation($composer3, "C(BottomDrawer)P(2,8,6,7,5,4:c#ui.unit.Dp,1:c#ui.graphics.Color,3:c#ui.graphics.Color,9:c#ui.graphics.Color)643@24199L33,645@24310L6,647@24425L6,648@24473L38,649@24552L10,659@24864L24,661@24894L3701:Drawer.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(drawerContent) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            z = gesturesEnabled;
        } else if (($changed & 7168) == 0) {
            z = gesturesEnabled;
            $dirty2 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = gesturesEnabled;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                shape = drawerShape;
                int i6 = $composer3.changed(shape) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                shape = drawerShape;
            }
            $dirty2 |= i6;
        } else {
            shape = drawerShape;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty2 |= $composer3.changed(drawerElevation) ? 131072 : 65536;
        }
        if (($changed & 3670016) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer3.changed(drawerBackgroundColor)) ? 1048576 : 524288;
        }
        if (($changed & 29360128) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer3.changed(drawerContentColor)) ? 8388608 : 4194304;
        }
        if ((234881024 & $changed) == 0) {
            if ((i & 256) == 0) {
                j = scrimColor;
                int i8 = $composer3.changed(j) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty2 |= i8;
            } else {
                j = scrimColor;
            }
            $dirty2 |= i8;
        } else {
            j = scrimColor;
        }
        if ((i & 512) == 0) {
            if ((1879048192 & $changed) == 0) {
                i2 = $composer3.changedInstance(content) ? 536870912 : 268435456;
            }
            if (i4 != 4 && (1533916891 & $dirty2) == 306783378 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                modifier4 = modifier;
                drawerState4 = drawerState;
                drawerElevation3 = drawerElevation;
                drawerBackgroundColor3 = drawerBackgroundColor;
                drawerContentColor3 = drawerContentColor;
                scrimColor2 = j;
                gesturesEnabled3 = z;
                drawerShape3 = shape;
                $composer2 = $composer3;
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        drawerState2 = rememberBottomDrawerState(BottomDrawerValue.Closed, null, $composer3, 6, 2);
                        $dirty2 &= -897;
                    } else {
                        drawerState2 = drawerState;
                    }
                    if (i5 != 0) {
                        gesturesEnabled2 = true;
                    } else {
                        gesturesEnabled2 = z;
                    }
                    if ((i & 16) != 0) {
                        drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                        $dirty2 &= -57345;
                    } else {
                        drawerShape2 = shape;
                    }
                    if (i7 != 0) {
                        drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                    } else {
                        drawerElevation2 = drawerElevation;
                    }
                    if ((i & 64) != 0) {
                        drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                        $dirty2 &= -3670017;
                    } else {
                        drawerBackgroundColor2 = drawerBackgroundColor;
                    }
                    if ((i & 128) != 0) {
                        drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer3, ($dirty2 >> 18) & 14);
                        $dirty2 &= -29360129;
                    } else {
                        drawerContentColor2 = drawerContentColor;
                    }
                    if ((i & 256) != 0) {
                        scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                        gesturesEnabled3 = gesturesEnabled2;
                        drawerBackgroundColor3 = drawerBackgroundColor2;
                        drawerContentColor3 = drawerContentColor2;
                        modifier3 = modifier2;
                        drawerState3 = drawerState2;
                        drawerShape3 = drawerShape2;
                        drawerElevation3 = drawerElevation2;
                        $dirty = $dirty2 & (-234881025);
                    } else {
                        scrimColor2 = j;
                        gesturesEnabled3 = gesturesEnabled2;
                        drawerBackgroundColor3 = drawerBackgroundColor2;
                        drawerContentColor3 = drawerContentColor2;
                        modifier3 = modifier2;
                        drawerState3 = drawerState2;
                        drawerShape3 = drawerShape2;
                        drawerElevation3 = drawerElevation2;
                        $dirty = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if (i4 != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 64) != 0) {
                        $dirty2 &= -3670017;
                    }
                    if ((i & 128) != 0) {
                        $dirty2 &= -29360129;
                    }
                    if ((i & 256) != 0) {
                        drawerState3 = drawerState;
                        drawerElevation3 = drawerElevation;
                        drawerBackgroundColor3 = drawerBackgroundColor;
                        drawerContentColor3 = drawerContentColor;
                        scrimColor2 = j;
                        gesturesEnabled3 = z;
                        drawerShape3 = shape;
                        modifier3 = modifier;
                        $dirty = (-234881025) & $dirty2;
                    } else {
                        drawerState3 = drawerState;
                        drawerElevation3 = drawerElevation;
                        drawerBackgroundColor3 = drawerBackgroundColor;
                        drawerContentColor3 = drawerContentColor;
                        scrimColor2 = j;
                        gesturesEnabled3 = z;
                        drawerShape3 = shape;
                        modifier3 = modifier;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(625649286, $dirty, -1, "androidx.compose.material.BottomDrawer (Drawer.kt:640)");
                }
                $composer3.startReplaceableGroup(-1561647407);
                ComposerKt.sourceInformation($composer3, "654@24761L7,655@24777L64");
                if (drawerState3.getDensity() == null) {
                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localDensity);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    final Density density = (Density) objConsume;
                    EffectsKt.SideEffect(new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$1
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
                            drawerState3.setDensity$material_release(density);
                        }
                    }, $composer3, 0);
                }
                $composer3.endReplaceableGroup();
                $composer3.startReplaceableGroup(773894976);
                ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
                $composer3.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                value$iv$iv$iv = $composer3.rememberedValue();
                if (value$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                    $composer3.updateRememberedValue(value$iv$iv$iv);
                }
                $composer3.endReplaceableGroup();
                CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
                final CoroutineScope scope = wrapper$iv.getCoroutineScope();
                $composer3.endReplaceableGroup();
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null);
                final boolean z2 = gesturesEnabled3;
                final BottomDrawerState bottomDrawerState = drawerState3;
                final int i9 = $dirty;
                final long j2 = scrimColor2;
                final Shape shape2 = drawerShape3;
                modifier4 = modifier3;
                drawerState4 = drawerState3;
                final long j3 = drawerBackgroundColor3;
                final long j4 = drawerContentColor3;
                final float f = drawerElevation3;
                $composer2 = $composer3;
                BoxWithConstraintsKt.BoxWithConstraints(modifierFillMaxSize$default, null, false, ComposableLambdaKt.composableLambda($composer2, 1220102512, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2
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
                        Modifier.Companion nestedScroll;
                        Function0<ComposeUiNode> function0;
                        Object value$iv$iv;
                        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
                        ComposerKt.sourceInformation($composer4, "C*665@25171L7,677@25586L7,688@25934L2655:Drawer.kt#jmzs0o");
                        int $dirty3 = $changed2;
                        if (($changed2 & 14) == 0) {
                            $dirty3 |= $composer4.changed(BoxWithConstraints) ? 4 : 2;
                        }
                        if (($dirty3 & 91) != 18 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1220102512, $changed2, -1, "androidx.compose.material.BottomDrawer.<anonymous> (Drawer.kt:661)");
                            }
                            final float fullHeight = Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk());
                            final boolean isLandscape = Constraints.m5218getMaxWidthimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk()) > Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk());
                            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer4.consume(localDensity2);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density $this$invoke_u24lambda_u240 = (Density) objConsume2;
                            Modifier drawerConstraints = SizeKt.m538sizeInqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, $this$invoke_u24lambda_u240.mo324toDpu2uoSUM(Constraints.m5218getMaxWidthimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk())), $this$invoke_u24lambda_u240.mo324toDpu2uoSUM(Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk())), 3, null);
                            if (z2) {
                                nestedScroll = NestedScrollModifierKt.nestedScroll$default(Modifier.INSTANCE, bottomDrawerState.getNestedScrollConnection(), null, 2, null);
                            } else {
                                nestedScroll = Modifier.INSTANCE;
                            }
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer4.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            boolean isRtl = objConsume3 == LayoutDirection.Rtl;
                            Modifier swipeable = AnchoredDraggableKt.anchoredDraggable(Modifier.INSTANCE.then(nestedScroll), bottomDrawerState.getAnchoredDraggableState$material_release(), Orientation.Vertical, (24 & 4) != 0 ? true : z2, (24 & 8) != 0 ? false : isRtl, (24 & 16) != 0 ? null : null);
                            Function2<Composer, Integer, Unit> function2 = content;
                            final int i10 = i9;
                            long j5 = j2;
                            final BottomDrawerState bottomDrawerState2 = bottomDrawerState;
                            final CoroutineScope coroutineScope = scope;
                            Shape shape3 = shape2;
                            long j6 = j3;
                            long j7 = j4;
                            float f2 = f;
                            final boolean z3 = z2;
                            final Function3<ColumnScope, Composer, Integer, Unit> function3 = drawerContent;
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(swipeable);
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
                            int i11 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i12 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, 1720994955, "C689@25963L9,690@25985L391,701@26410L33,702@26483L113,705@26609L1970:Drawer.kt#jmzs0o");
                            function2.invoke($composer4, Integer.valueOf((i10 >> 27) & 14));
                            DrawerKt.m1090BottomDrawerScrim3JVO9M(j5, new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$1
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
                                    if (z3 && bottomDrawerState2.confirmStateChange$material_release(BottomDrawerValue.Closed)) {
                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(bottomDrawerState2, null), 3, null);
                                    }
                                }

                                /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$BottomDrawer$2$1$1$1, reason: invalid class name */
                                /* JADX INFO: compiled from: Drawer.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                @DebugMetadata(c = "androidx.compose.material.DrawerKt$BottomDrawer$2$1$1$1", f = "Drawer.kt", i = {}, l = {697}, m = "invokeSuspend", n = {}, s = {})
                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ BottomDrawerState $drawerState;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass1(BottomDrawerState bottomDrawerState, Continuation<? super AnonymousClass1> continuation) {
                                        super(2, continuation);
                                        this.$drawerState = bottomDrawerState;
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
                            }, bottomDrawerState2.getTargetValue() != BottomDrawerValue.Closed, $composer4, (i10 >> 24) & 14);
                            final String navigationMenu = Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1205getNavigationMenuUdPEhr4(), $composer4, 6);
                            $composer4.startReplaceableGroup(511388516);
                            ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2):Composables.kt#9igjgp");
                            boolean invalid$iv$iv = $composer4.changed(bottomDrawerState2) | $composer4.changed(coroutineScope);
                            Object it$iv$iv = $composer4.rememberedValue();
                            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv = DrawerKt.BottomDrawerAnchorChangeCallback(bottomDrawerState2, coroutineScope);
                                $composer4.updateRememberedValue(value$iv$iv);
                            } else {
                                value$iv$iv = it$iv$iv;
                            }
                            $composer4.endReplaceableGroup();
                            final AnchoredDraggableState.AnchorChangedCallback anchorChangeCallback = (AnchoredDraggableState.AnchorChangedCallback) value$iv$iv;
                            int i13 = i10 >> 12;
                            SurfaceKt.m1210SurfaceFjzlyU(SemanticsModifierKt.semantics$default(OffsetKt.offset(OnRemeasuredModifierKt.onSizeChanged(drawerConstraints, new Function1<IntSize, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                                    m1095invokeozmzZPI(intSize.getPackedValue());
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                                public final void m1095invokeozmzZPI(long drawerSize) {
                                    float drawerHeight = IntSize.m5433getHeightimpl(drawerSize);
                                    float f3 = fullHeight;
                                    boolean z4 = isLandscape;
                                    Map $this$invoke_ozmzZPI_u24lambda_u240 = MapsKt.createMapBuilder();
                                    $this$invoke_ozmzZPI_u24lambda_u240.put(BottomDrawerValue.Closed, Float.valueOf(f3));
                                    float peekHeight = 0.5f * f3;
                                    if (drawerHeight > peekHeight || z4) {
                                        $this$invoke_ozmzZPI_u24lambda_u240.put(BottomDrawerValue.Open, Float.valueOf(peekHeight));
                                    }
                                    if (drawerHeight > 0.0f) {
                                        $this$invoke_ozmzZPI_u24lambda_u240.put(BottomDrawerValue.Expanded, Float.valueOf(Math.max(0.0f, f3 - drawerHeight)));
                                    }
                                    bottomDrawerState2.getAnchoredDraggableState$material_release().updateAnchors$material_release(MapsKt.build($this$invoke_ozmzZPI_u24lambda_u240), anchorChangeCallback);
                                }
                            }), new Function1<Density, IntOffset>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$3
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ IntOffset invoke(Density density2) {
                                    return IntOffset.m5383boximpl(m1096invokeBjo55l4(density2));
                                }

                                /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                                public final long m1096invokeBjo55l4(Density offset) {
                                    Intrinsics.checkNotNullParameter(offset, "$this$offset");
                                    return IntOffsetKt.IntOffset(0, MathKt.roundToInt(bottomDrawerState2.requireOffset$material_release()));
                                }
                            }), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$4
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
                                    if (bottomDrawerState2.isOpen()) {
                                        final BottomDrawerState bottomDrawerState3 = bottomDrawerState2;
                                        final CoroutineScope coroutineScope2 = coroutineScope;
                                        SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$4.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            /* JADX WARN: Can't rename method to resolve collision */
                                            @Override // kotlin.jvm.functions.Function0
                                            public final Boolean invoke() {
                                                if (bottomDrawerState3.confirmStateChange$material_release(BottomDrawerValue.Closed)) {
                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new C00461(bottomDrawerState3, null), 3, null);
                                                }
                                                return true;
                                            }

                                            /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$BottomDrawer$2$1$4$1$1, reason: invalid class name and collision with other inner class name */
                                            /* JADX INFO: compiled from: Drawer.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                            @DebugMetadata(c = "androidx.compose.material.DrawerKt$BottomDrawer$2$1$4$1$1", f = "Drawer.kt", i = {}, l = {738}, m = "invokeSuspend", n = {}, s = {})
                                            static final class C00461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ BottomDrawerState $drawerState;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                C00461(BottomDrawerState bottomDrawerState, Continuation<? super C00461> continuation) {
                                                    super(2, continuation);
                                                    this.$drawerState = bottomDrawerState;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new C00461(this.$drawerState, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((C00461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                            }, 1, null), shape3, j6, j7, null, f2, ComposableLambdaKt.composableLambda($composer4, 457750254, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$5
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
                                    ComposerKt.sourceInformation($composer5, "C747@28534L31:Drawer.kt#jmzs0o");
                                    if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                        $composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(457750254, $changed3, -1, "androidx.compose.material.BottomDrawer.<anonymous>.<anonymous>.<anonymous> (Drawer.kt:746)");
                                    }
                                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                    int $changed$iv = (i10 << 9) & 7168;
                                    $composer5.startReplaceableGroup(-483455358);
                                    ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                                    Modifier modifier$iv = Modifier.INSTANCE;
                                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                    MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                    int $changed$iv$iv2 = ($changed$iv << 3) & 112;
                                    $composer5.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer5, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                    int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                    CompositionLocalMap localMap$iv$iv2 = $composer5.getCurrentCompositionLocalMap();
                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv);
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
                                    int i14 = ($changed$iv$iv$iv2 >> 9) & 14;
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
                            }), $composer4, ((i10 >> 9) & 112) | 1572864 | (i13 & 896) | (i13 & 7168) | (458752 & i10), 16);
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
                }), $composer2, 3072, 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final BottomDrawerState bottomDrawerState2 = drawerState4;
            final boolean z3 = gesturesEnabled3;
            final Shape shape3 = drawerShape3;
            final float f2 = drawerElevation3;
            final long j5 = drawerBackgroundColor3;
            final long j6 = drawerContentColor3;
            final long j7 = scrimColor2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$3
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
                    DrawerKt.m1089BottomDrawerGs3lGvM(drawerContent, modifier5, bottomDrawerState2, z3, shape3, f2, j5, j6, j7, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty2 |= i2;
        if (i4 != 4) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerState2 = rememberBottomDrawerState(BottomDrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty2 &= -897;
                } else {
                    drawerState2 = drawerState;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                } else {
                    gesturesEnabled2 = z;
                }
                if ((i & 16) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty2 &= -57345;
                } else {
                    drawerShape2 = shape;
                }
                if (i7 != 0) {
                    drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                } else {
                    drawerElevation2 = drawerElevation;
                }
                if ((i & 64) != 0) {
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -3670017;
                } else {
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                if ((i & 128) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer3, ($dirty2 >> 18) & 14);
                    $dirty2 &= -29360129;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 256) != 0) {
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    gesturesEnabled3 = gesturesEnabled2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    modifier3 = modifier2;
                    drawerState3 = drawerState2;
                    drawerShape3 = drawerShape2;
                    drawerElevation3 = drawerElevation2;
                    $dirty = $dirty2 & (-234881025);
                } else {
                    scrimColor2 = j;
                    gesturesEnabled3 = gesturesEnabled2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    modifier3 = modifier2;
                    drawerState3 = drawerState2;
                    drawerShape3 = drawerShape2;
                    drawerElevation3 = drawerElevation2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerState2 = rememberBottomDrawerState(BottomDrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty2 &= -897;
                } else {
                    drawerState2 = drawerState;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                } else {
                    gesturesEnabled2 = z;
                }
                if ((i & 16) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty2 &= -57345;
                } else {
                    drawerShape2 = shape;
                }
                if (i7 != 0) {
                    drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                } else {
                    drawerElevation2 = drawerElevation;
                }
                if ((i & 64) != 0) {
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -3670017;
                } else {
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                if ((i & 128) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer3, ($dirty2 >> 18) & 14);
                    $dirty2 &= -29360129;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 256) != 0) {
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    gesturesEnabled3 = gesturesEnabled2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    modifier3 = modifier2;
                    drawerState3 = drawerState2;
                    drawerShape3 = drawerShape2;
                    drawerElevation3 = drawerElevation2;
                    $dirty = $dirty2 & (-234881025);
                } else {
                    scrimColor2 = j;
                    gesturesEnabled3 = gesturesEnabled2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    modifier3 = modifier2;
                    drawerState3 = drawerState2;
                    drawerShape3 = drawerShape2;
                    drawerElevation3 = drawerElevation2;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(625649286, $dirty, -1, "androidx.compose.material.BottomDrawer (Drawer.kt:640)");
            }
            $composer3.startReplaceableGroup(-1561647407);
            ComposerKt.sourceInformation($composer3, "654@24761L7,655@24777L64");
            if (drawerState3.getDensity() == null) {
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer3.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final Density density2 = (Density) objConsume2;
                EffectsKt.SideEffect(new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$1
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
                        drawerState3.setDensity$material_release(density2);
                    }
                }, $composer3, 0);
            }
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            value$iv$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv$iv);
            }
            $composer3.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv2 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope2 = wrapper$iv2.getCoroutineScope();
            $composer3.endReplaceableGroup();
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null);
            final boolean z4 = gesturesEnabled3;
            final BottomDrawerState bottomDrawerState3 = drawerState3;
            final int i10 = $dirty;
            final long j8 = scrimColor2;
            final Shape shape4 = drawerShape3;
            modifier4 = modifier3;
            drawerState4 = drawerState3;
            final long j9 = drawerBackgroundColor3;
            final long j10 = drawerContentColor3;
            final float f3 = drawerElevation3;
            $composer2 = $composer3;
            BoxWithConstraintsKt.BoxWithConstraints(modifierFillMaxSize$default2, null, false, ComposableLambdaKt.composableLambda($composer2, 1220102512, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2
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
                    Modifier.Companion nestedScroll;
                    Function0<ComposeUiNode> function0;
                    Object value$iv$iv;
                    Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
                    ComposerKt.sourceInformation($composer4, "C*665@25171L7,677@25586L7,688@25934L2655:Drawer.kt#jmzs0o");
                    int $dirty3 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty3 |= $composer4.changed(BoxWithConstraints) ? 4 : 2;
                    }
                    if (($dirty3 & 91) != 18 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1220102512, $changed2, -1, "androidx.compose.material.BottomDrawer.<anonymous> (Drawer.kt:661)");
                        }
                        final float fullHeight = Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk());
                        final boolean isLandscape = Constraints.m5218getMaxWidthimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk()) > Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk());
                        ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localDensity3);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density $this$invoke_u24lambda_u240 = (Density) objConsume3;
                        Modifier drawerConstraints = SizeKt.m538sizeInqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, $this$invoke_u24lambda_u240.mo324toDpu2uoSUM(Constraints.m5218getMaxWidthimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk())), $this$invoke_u24lambda_u240.mo324toDpu2uoSUM(Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk())), 3, null);
                        if (z4) {
                            nestedScroll = NestedScrollModifierKt.nestedScroll$default(Modifier.INSTANCE, bottomDrawerState3.getNestedScrollConnection(), null, 2, null);
                        } else {
                            nestedScroll = Modifier.INSTANCE;
                        }
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume4 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        boolean isRtl = objConsume4 == LayoutDirection.Rtl;
                        Modifier swipeable = AnchoredDraggableKt.anchoredDraggable(Modifier.INSTANCE.then(nestedScroll), bottomDrawerState3.getAnchoredDraggableState$material_release(), Orientation.Vertical, (24 & 4) != 0 ? true : z4, (24 & 8) != 0 ? false : isRtl, (24 & 16) != 0 ? null : null);
                        Function2<Composer, Integer, Unit> function2 = content;
                        final int i11 = i10;
                        long j11 = j8;
                        final BottomDrawerState bottomDrawerState4 = bottomDrawerState3;
                        final CoroutineScope coroutineScope = scope2;
                        Shape shape5 = shape4;
                        long j12 = j9;
                        long j13 = j10;
                        float f4 = f3;
                        final boolean z5 = z4;
                        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3 = drawerContent;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(swipeable);
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
                        int i13 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 1720994955, "C689@25963L9,690@25985L391,701@26410L33,702@26483L113,705@26609L1970:Drawer.kt#jmzs0o");
                        function2.invoke($composer4, Integer.valueOf((i11 >> 27) & 14));
                        DrawerKt.m1090BottomDrawerScrim3JVO9M(j11, new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$1
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
                                if (z5 && bottomDrawerState4.confirmStateChange$material_release(BottomDrawerValue.Closed)) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(bottomDrawerState4, null), 3, null);
                                }
                            }

                            /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$BottomDrawer$2$1$1$1, reason: invalid class name */
                            /* JADX INFO: compiled from: Drawer.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                            @DebugMetadata(c = "androidx.compose.material.DrawerKt$BottomDrawer$2$1$1$1", f = "Drawer.kt", i = {}, l = {697}, m = "invokeSuspend", n = {}, s = {})
                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ BottomDrawerState $drawerState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass1(BottomDrawerState bottomDrawerState, Continuation<? super AnonymousClass1> continuation) {
                                    super(2, continuation);
                                    this.$drawerState = bottomDrawerState;
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
                        }, bottomDrawerState4.getTargetValue() != BottomDrawerValue.Closed, $composer4, (i11 >> 24) & 14);
                        final String navigationMenu = Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1205getNavigationMenuUdPEhr4(), $composer4, 6);
                        $composer4.startReplaceableGroup(511388516);
                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2):Composables.kt#9igjgp");
                        boolean invalid$iv$iv = $composer4.changed(bottomDrawerState4) | $composer4.changed(coroutineScope);
                        Object it$iv$iv = $composer4.rememberedValue();
                        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = DrawerKt.BottomDrawerAnchorChangeCallback(bottomDrawerState4, coroutineScope);
                            $composer4.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer4.endReplaceableGroup();
                        final AnchoredDraggableState.AnchorChangedCallback<BottomDrawerValue> anchorChangeCallback = (AnchoredDraggableState.AnchorChangedCallback) value$iv$iv;
                        int i14 = i11 >> 12;
                        SurfaceKt.m1210SurfaceFjzlyU(SemanticsModifierKt.semantics$default(OffsetKt.offset(OnRemeasuredModifierKt.onSizeChanged(drawerConstraints, new Function1<IntSize, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                                m1095invokeozmzZPI(intSize.getPackedValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                            public final void m1095invokeozmzZPI(long drawerSize) {
                                float drawerHeight = IntSize.m5433getHeightimpl(drawerSize);
                                float f5 = fullHeight;
                                boolean z6 = isLandscape;
                                Map $this$invoke_ozmzZPI_u24lambda_u240 = MapsKt.createMapBuilder();
                                $this$invoke_ozmzZPI_u24lambda_u240.put(BottomDrawerValue.Closed, Float.valueOf(f5));
                                float peekHeight = 0.5f * f5;
                                if (drawerHeight > peekHeight || z6) {
                                    $this$invoke_ozmzZPI_u24lambda_u240.put(BottomDrawerValue.Open, Float.valueOf(peekHeight));
                                }
                                if (drawerHeight > 0.0f) {
                                    $this$invoke_ozmzZPI_u24lambda_u240.put(BottomDrawerValue.Expanded, Float.valueOf(Math.max(0.0f, f5 - drawerHeight)));
                                }
                                bottomDrawerState4.getAnchoredDraggableState$material_release().updateAnchors$material_release(MapsKt.build($this$invoke_ozmzZPI_u24lambda_u240), anchorChangeCallback);
                            }
                        }), new Function1<Density, IntOffset>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$3
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ IntOffset invoke(Density density3) {
                                return IntOffset.m5383boximpl(m1096invokeBjo55l4(density3));
                            }

                            /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                            public final long m1096invokeBjo55l4(Density offset) {
                                Intrinsics.checkNotNullParameter(offset, "$this$offset");
                                return IntOffsetKt.IntOffset(0, MathKt.roundToInt(bottomDrawerState4.requireOffset$material_release()));
                            }
                        }), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$4
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
                                if (bottomDrawerState4.isOpen()) {
                                    final BottomDrawerState bottomDrawerState5 = bottomDrawerState4;
                                    final CoroutineScope coroutineScope2 = coroutineScope;
                                    SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$4.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Boolean invoke() {
                                            if (bottomDrawerState5.confirmStateChange$material_release(BottomDrawerValue.Closed)) {
                                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new C00461(bottomDrawerState5, null), 3, null);
                                            }
                                            return true;
                                        }

                                        /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$BottomDrawer$2$1$4$1$1, reason: invalid class name and collision with other inner class name */
                                        /* JADX INFO: compiled from: Drawer.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                        @DebugMetadata(c = "androidx.compose.material.DrawerKt$BottomDrawer$2$1$4$1$1", f = "Drawer.kt", i = {}, l = {738}, m = "invokeSuspend", n = {}, s = {})
                                        static final class C00461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ BottomDrawerState $drawerState;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            C00461(BottomDrawerState bottomDrawerState, Continuation<? super C00461> continuation) {
                                                super(2, continuation);
                                                this.$drawerState = bottomDrawerState;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new C00461(this.$drawerState, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((C00461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                        }, 1, null), shape5, j12, j13, null, f4, ComposableLambdaKt.composableLambda($composer4, 457750254, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$5
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
                                ComposerKt.sourceInformation($composer5, "C747@28534L31:Drawer.kt#jmzs0o");
                                if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(457750254, $changed3, -1, "androidx.compose.material.BottomDrawer.<anonymous>.<anonymous>.<anonymous> (Drawer.kt:746)");
                                }
                                Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                int $changed$iv = (i11 << 9) & 7168;
                                $composer5.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv2 = ($changed$iv << 3) & 112;
                                $composer5.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer5, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                CompositionLocalMap localMap$iv$iv2 = $composer5.getCurrentCompositionLocalMap();
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv);
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
                        }), $composer4, ((i11 >> 9) & 112) | 1572864 | (i14 & 896) | (i14 & 7168) | (458752 & i11), 16);
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
            }), $composer2, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerState2 = rememberBottomDrawerState(BottomDrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty2 &= -897;
                } else {
                    drawerState2 = drawerState;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                } else {
                    gesturesEnabled2 = z;
                }
                if ((i & 16) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty2 &= -57345;
                } else {
                    drawerShape2 = shape;
                }
                if (i7 != 0) {
                    drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                } else {
                    drawerElevation2 = drawerElevation;
                }
                if ((i & 64) != 0) {
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -3670017;
                } else {
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                if ((i & 128) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer3, ($dirty2 >> 18) & 14);
                    $dirty2 &= -29360129;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 256) != 0) {
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    gesturesEnabled3 = gesturesEnabled2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    modifier3 = modifier2;
                    drawerState3 = drawerState2;
                    drawerShape3 = drawerShape2;
                    drawerElevation3 = drawerElevation2;
                    $dirty = $dirty2 & (-234881025);
                } else {
                    scrimColor2 = j;
                    gesturesEnabled3 = gesturesEnabled2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    modifier3 = modifier2;
                    drawerState3 = drawerState2;
                    drawerShape3 = drawerShape2;
                    drawerElevation3 = drawerElevation2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    drawerState2 = rememberBottomDrawerState(BottomDrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty2 &= -897;
                } else {
                    drawerState2 = drawerState;
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                } else {
                    gesturesEnabled2 = z;
                }
                if ((i & 16) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty2 &= -57345;
                } else {
                    drawerShape2 = shape;
                }
                if (i7 != 0) {
                    drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                } else {
                    drawerElevation2 = drawerElevation;
                }
                if ((i & 64) != 0) {
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -3670017;
                } else {
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                if ((i & 128) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer3, ($dirty2 >> 18) & 14);
                    $dirty2 &= -29360129;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 256) != 0) {
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    gesturesEnabled3 = gesturesEnabled2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    modifier3 = modifier2;
                    drawerState3 = drawerState2;
                    drawerShape3 = drawerShape2;
                    drawerElevation3 = drawerElevation2;
                    $dirty = $dirty2 & (-234881025);
                } else {
                    scrimColor2 = j;
                    gesturesEnabled3 = gesturesEnabled2;
                    drawerBackgroundColor3 = drawerBackgroundColor2;
                    drawerContentColor3 = drawerContentColor2;
                    modifier3 = modifier2;
                    drawerState3 = drawerState2;
                    drawerShape3 = drawerShape2;
                    drawerElevation3 = drawerElevation2;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(625649286, $dirty, -1, "androidx.compose.material.BottomDrawer (Drawer.kt:640)");
            }
            $composer3.startReplaceableGroup(-1561647407);
            ComposerKt.sourceInformation($composer3, "654@24761L7,655@24777L64");
            if (drawerState3.getDensity() == null) {
                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer3.consume(localDensity3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final Density density3 = (Density) objConsume3;
                EffectsKt.SideEffect(new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$1
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
                        drawerState3.setDensity$material_release(density3);
                    }
                }, $composer3, 0);
            }
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            value$iv$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv$iv);
            }
            $composer3.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv3 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope3 = wrapper$iv3.getCoroutineScope();
            $composer3.endReplaceableGroup();
            Modifier modifierFillMaxSize$default3 = SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null);
            final boolean z5 = gesturesEnabled3;
            final BottomDrawerState bottomDrawerState4 = drawerState3;
            final int i11 = $dirty;
            final long j11 = scrimColor2;
            final Shape shape5 = drawerShape3;
            modifier4 = modifier3;
            drawerState4 = drawerState3;
            final long j12 = drawerBackgroundColor3;
            final long j13 = drawerContentColor3;
            final float f4 = drawerElevation3;
            $composer2 = $composer3;
            BoxWithConstraintsKt.BoxWithConstraints(modifierFillMaxSize$default3, null, false, ComposableLambdaKt.composableLambda($composer2, 1220102512, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2
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
                    Modifier.Companion nestedScroll;
                    Function0<ComposeUiNode> function0;
                    Object value$iv$iv;
                    Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
                    ComposerKt.sourceInformation($composer4, "C*665@25171L7,677@25586L7,688@25934L2655:Drawer.kt#jmzs0o");
                    int $dirty3 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty3 |= $composer4.changed(BoxWithConstraints) ? 4 : 2;
                    }
                    if (($dirty3 & 91) != 18 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1220102512, $changed2, -1, "androidx.compose.material.BottomDrawer.<anonymous> (Drawer.kt:661)");
                        }
                        final float fullHeight = Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk());
                        final boolean isLandscape = Constraints.m5218getMaxWidthimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk()) > Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk());
                        ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume4 = $composer4.consume(localDensity4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density $this$invoke_u24lambda_u240 = (Density) objConsume4;
                        Modifier drawerConstraints = SizeKt.m538sizeInqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, $this$invoke_u24lambda_u240.mo324toDpu2uoSUM(Constraints.m5218getMaxWidthimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk())), $this$invoke_u24lambda_u240.mo324toDpu2uoSUM(Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk())), 3, null);
                        if (z5) {
                            nestedScroll = NestedScrollModifierKt.nestedScroll$default(Modifier.INSTANCE, bottomDrawerState4.getNestedScrollConnection(), null, 2, null);
                        } else {
                            nestedScroll = Modifier.INSTANCE;
                        }
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume5 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        boolean isRtl = objConsume5 == LayoutDirection.Rtl;
                        Modifier swipeable = AnchoredDraggableKt.anchoredDraggable(Modifier.INSTANCE.then(nestedScroll), bottomDrawerState4.getAnchoredDraggableState$material_release(), Orientation.Vertical, (24 & 4) != 0 ? true : z5, (24 & 8) != 0 ? false : isRtl, (24 & 16) != 0 ? null : null);
                        Function2<Composer, Integer, Unit> function2 = content;
                        final int i12 = i11;
                        long j14 = j11;
                        final BottomDrawerState bottomDrawerState5 = bottomDrawerState4;
                        final CoroutineScope coroutineScope = scope3;
                        Shape shape6 = shape5;
                        long j15 = j12;
                        long j16 = j13;
                        float f5 = f4;
                        final boolean z6 = z5;
                        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3 = drawerContent;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(swipeable);
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
                        int i13 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i14 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 1720994955, "C689@25963L9,690@25985L391,701@26410L33,702@26483L113,705@26609L1970:Drawer.kt#jmzs0o");
                        function2.invoke($composer4, Integer.valueOf((i12 >> 27) & 14));
                        DrawerKt.m1090BottomDrawerScrim3JVO9M(j14, new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$1
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
                                if (z6 && bottomDrawerState5.confirmStateChange$material_release(BottomDrawerValue.Closed)) {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(bottomDrawerState5, null), 3, null);
                                }
                            }

                            /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$BottomDrawer$2$1$1$1, reason: invalid class name */
                            /* JADX INFO: compiled from: Drawer.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                            @DebugMetadata(c = "androidx.compose.material.DrawerKt$BottomDrawer$2$1$1$1", f = "Drawer.kt", i = {}, l = {697}, m = "invokeSuspend", n = {}, s = {})
                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ BottomDrawerState $drawerState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass1(BottomDrawerState bottomDrawerState, Continuation<? super AnonymousClass1> continuation) {
                                    super(2, continuation);
                                    this.$drawerState = bottomDrawerState;
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
                        }, bottomDrawerState5.getTargetValue() != BottomDrawerValue.Closed, $composer4, (i12 >> 24) & 14);
                        final String navigationMenu = Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1205getNavigationMenuUdPEhr4(), $composer4, 6);
                        $composer4.startReplaceableGroup(511388516);
                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2):Composables.kt#9igjgp");
                        boolean invalid$iv$iv = $composer4.changed(bottomDrawerState5) | $composer4.changed(coroutineScope);
                        Object it$iv$iv = $composer4.rememberedValue();
                        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = DrawerKt.BottomDrawerAnchorChangeCallback(bottomDrawerState5, coroutineScope);
                            $composer4.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer4.endReplaceableGroup();
                        final AnchoredDraggableState.AnchorChangedCallback<BottomDrawerValue> anchorChangeCallback = (AnchoredDraggableState.AnchorChangedCallback) value$iv$iv;
                        int i15 = i12 >> 12;
                        SurfaceKt.m1210SurfaceFjzlyU(SemanticsModifierKt.semantics$default(OffsetKt.offset(OnRemeasuredModifierKt.onSizeChanged(drawerConstraints, new Function1<IntSize, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                                m1095invokeozmzZPI(intSize.getPackedValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                            public final void m1095invokeozmzZPI(long drawerSize) {
                                float drawerHeight = IntSize.m5433getHeightimpl(drawerSize);
                                float f6 = fullHeight;
                                boolean z7 = isLandscape;
                                Map $this$invoke_ozmzZPI_u24lambda_u240 = MapsKt.createMapBuilder();
                                $this$invoke_ozmzZPI_u24lambda_u240.put(BottomDrawerValue.Closed, Float.valueOf(f6));
                                float peekHeight = 0.5f * f6;
                                if (drawerHeight > peekHeight || z7) {
                                    $this$invoke_ozmzZPI_u24lambda_u240.put(BottomDrawerValue.Open, Float.valueOf(peekHeight));
                                }
                                if (drawerHeight > 0.0f) {
                                    $this$invoke_ozmzZPI_u24lambda_u240.put(BottomDrawerValue.Expanded, Float.valueOf(Math.max(0.0f, f6 - drawerHeight)));
                                }
                                bottomDrawerState5.getAnchoredDraggableState$material_release().updateAnchors$material_release(MapsKt.build($this$invoke_ozmzZPI_u24lambda_u240), anchorChangeCallback);
                            }
                        }), new Function1<Density, IntOffset>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$3
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ IntOffset invoke(Density density4) {
                                return IntOffset.m5383boximpl(m1096invokeBjo55l4(density4));
                            }

                            /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                            public final long m1096invokeBjo55l4(Density offset) {
                                Intrinsics.checkNotNullParameter(offset, "$this$offset");
                                return IntOffsetKt.IntOffset(0, MathKt.roundToInt(bottomDrawerState5.requireOffset$material_release()));
                            }
                        }), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$4
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
                                if (bottomDrawerState5.isOpen()) {
                                    final BottomDrawerState bottomDrawerState6 = bottomDrawerState5;
                                    final CoroutineScope coroutineScope2 = coroutineScope;
                                    SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$4.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Boolean invoke() {
                                            if (bottomDrawerState6.confirmStateChange$material_release(BottomDrawerValue.Closed)) {
                                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new C00461(bottomDrawerState6, null), 3, null);
                                            }
                                            return true;
                                        }

                                        /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$BottomDrawer$2$1$4$1$1, reason: invalid class name and collision with other inner class name */
                                        /* JADX INFO: compiled from: Drawer.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                                        @DebugMetadata(c = "androidx.compose.material.DrawerKt$BottomDrawer$2$1$4$1$1", f = "Drawer.kt", i = {}, l = {738}, m = "invokeSuspend", n = {}, s = {})
                                        static final class C00461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ BottomDrawerState $drawerState;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            C00461(BottomDrawerState bottomDrawerState, Continuation<? super C00461> continuation) {
                                                super(2, continuation);
                                                this.$drawerState = bottomDrawerState;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new C00461(this.$drawerState, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((C00461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                        }, 1, null), shape6, j15, j16, null, f5, ComposableLambdaKt.composableLambda($composer4, 457750254, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$2$1$5
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
                                ComposerKt.sourceInformation($composer5, "C747@28534L31:Drawer.kt#jmzs0o");
                                if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(457750254, $changed3, -1, "androidx.compose.material.BottomDrawer.<anonymous>.<anonymous>.<anonymous> (Drawer.kt:746)");
                                }
                                Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                int $changed$iv = (i12 << 9) & 7168;
                                $composer5.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv2 = ($changed$iv << 3) & 112;
                                $composer5.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer5, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                CompositionLocalMap localMap$iv$iv2 = $composer5.getCurrentCompositionLocalMap();
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv);
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
                                int i16 = ($changed$iv$iv$iv2 >> 9) & 14;
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
                        }), $composer4, ((i12 >> 9) & 112) | 1572864 | (i15 & 896) | (i15 & 7168) | (458752 & i12), 16);
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
            }), $composer2, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final BottomDrawerState bottomDrawerState5 = drawerState4;
        final boolean z6 = gesturesEnabled3;
        final Shape shape6 = drawerShape3;
        final float f5 = drawerElevation3;
        final long j14 = drawerBackgroundColor3;
        final long j15 = drawerContentColor3;
        final long j16 = scrimColor2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$3
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
                DrawerKt.m1089BottomDrawerGs3lGvM(drawerContent, modifier6, bottomDrawerState5, z6, shape6, f5, j14, j15, j16, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    private static final Map<BottomDrawerValue, Float> calculateAnchors(float fullHeight, float drawerHeight, boolean isLandscape) {
        float peekHeight = 0.5f * fullHeight;
        float expandedHeight = Math.max(0.0f, fullHeight - drawerHeight);
        if (drawerHeight < peekHeight || isLandscape) {
            Pair[] pairArr = new Pair[2];
            pairArr[0] = TuplesKt.to(BottomDrawerValue.Closed, Float.valueOf(fullHeight));
            pairArr[1] = TuplesKt.to(BottomDrawerValue.Expanded, drawerHeight == 0.0f ? null : Float.valueOf(expandedHeight));
            return MapsKt.mapOf(pairArr);
        }
        Pair[] pairArr2 = new Pair[3];
        pairArr2[0] = TuplesKt.to(BottomDrawerValue.Closed, Float.valueOf(fullHeight));
        pairArr2[1] = TuplesKt.to(BottomDrawerValue.Open, Float.valueOf(peekHeight));
        pairArr2[2] = TuplesKt.to(BottomDrawerValue.Expanded, drawerHeight == 0.0f ? null : Float.valueOf(expandedHeight));
        return MapsKt.mapOf(pairArr2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculateFraction(float a, float b, float pos) {
        return RangesKt.coerceIn((pos - a) / (b - a), 0.0f, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: BottomDrawerScrim-3J-VO9M, reason: not valid java name */
    public static final void m1090BottomDrawerScrim3JVO9M(final long color, final Function0<Unit> function0, final boolean visible, Composer $composer, final int $changed) {
        Modifier.Companion dismissModifier;
        Object value$iv$iv;
        Object value$iv$iv2;
        Composer $composer2 = $composer.startRestartGroup(-513067266);
        ComposerKt.sourceInformation($composer2, "C(BottomDrawerScrim)P(0:c#ui.graphics.Color)805@29975L121,809@30123L30,827@30676L62,823@30567L171:Drawer.kt#jmzs0o");
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
                ComposerKt.traceEventStart(-513067266, $dirty2, -1, "androidx.compose.material.BottomDrawerScrim (Drawer.kt:799)");
            }
            if (color != Color.INSTANCE.m3007getUnspecified0d7_KjU()) {
                final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(visible ? 1.0f : 0.0f, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer2, 48, 28);
                final String closeDrawer = Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1201getCloseDrawerUdPEhr4(), $composer2, 6);
                $composer2.startReplaceableGroup(-1298942364);
                ComposerKt.sourceInformation($composer2, "812@30261L73,815@30387L122");
                if (visible) {
                    Modifier.Companion companion = Modifier.INSTANCE;
                    int i = ($dirty2 >> 3) & 14;
                    $composer2.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                    boolean invalid$iv$iv = $composer2.changed(function0);
                    Object it$iv$iv = $composer2.rememberedValue();
                    if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new DrawerKt$BottomDrawerScrim$dismissModifier$1$1(function0, null);
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, function0, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv);
                    int i2 = $dirty2 & 112;
                    $composer2.startReplaceableGroup(511388516);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                    boolean invalid$iv$iv2 = $composer2.changed(closeDrawer) | $composer2.changed(function0);
                    Object it$iv$iv2 = $composer2.rememberedValue();
                    if (invalid$iv$iv2 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawerScrim$dismissModifier$2$1
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
                                final Function0<Unit> function1 = function0;
                                SemanticsPropertiesKt.onClick$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.DrawerKt$BottomDrawerScrim$dismissModifier$2$1.1
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
                value$iv$iv3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawerScrim$1$1
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
                        DrawScope.CC.m3522drawRectnJ9OG0$default(Canvas, color, 0L, 0L, DrawerKt.BottomDrawerScrim_3J_VO9M$lambda$3(stateAnimateFloatAsState), null, null, 0, 118, null);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawerScrim$2
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
                DrawerKt.m1090BottomDrawerScrim3JVO9M(color, function0, visible, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float BottomDrawerScrim_3J_VO9M$lambda$3(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Scrim-Bx497Mc, reason: not valid java name */
    public static final void m1092ScrimBx497Mc(final boolean open, final Function0<Unit> function0, final Function0<Float> function1, final long color, Composer $composer, final int $changed) {
        Modifier.Companion dismissDrawer;
        Composer $composer2 = $composer.startRestartGroup(1983403750);
        ComposerKt.sourceInformation($composer2, "C(Scrim)P(3,2,1,0:c#ui.graphics.Color)840@30893L30,856@31337L51,852@31246L142:Drawer.kt#jmzs0o");
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
                ComposerKt.traceEventStart(1983403750, $dirty2, -1, "androidx.compose.material.Scrim (Drawer.kt:834)");
            }
            final String closeDrawer = Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1201getCloseDrawerUdPEhr4(), $composer2, 6);
            $composer2.startReplaceableGroup(1010561092);
            ComposerKt.sourceInformation($composer2, "843@31012L35,844@31096L108");
            if (open) {
                Modifier.Companion companion = Modifier.INSTANCE;
                int i = ($dirty2 >> 3) & 14;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean invalid$iv$iv = $composer2.changed(function0);
                DrawerKt$Scrim$dismissDrawer$1$1 value$iv$iv = $composer2.rememberedValue();
                if (invalid$iv$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = new DrawerKt$Scrim$dismissDrawer$1$1(function0, null);
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
                    value$iv$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.DrawerKt$Scrim$dismissDrawer$2$1
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
                            SemanticsPropertiesKt.onClick$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.DrawerKt$Scrim$dismissDrawer$2$1.1
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
                value$iv$iv3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.DrawerKt$Scrim$1$1
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$Scrim$2
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
                DrawerKt.m1092ScrimBx497Mc(open, function0, function1, color, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Drawer.kt */
    @Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J)\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b\n\u0010\u000bJ-\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0012\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014J%\u0010\u0015\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u0019*\u00020\rH\u0003ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0016\u0010\u0018\u001a\u00020\u0019*\u00020\u0007H\u0003ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001bJ\u001a\u0010\u001d\u001a\u00020\r*\u00020\u0019H\u0002ø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u000f\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001f"}, d2 = {"androidx/compose/material/DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "toFloat", "", "offsetToFloat", "(J)F", "velocityToFloat", "toOffset", "(F)J", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class C02831 implements NestedScrollConnection {
        final /* synthetic */ AnchoredDraggableState<?> $state;
        private final Orientation orientation = Orientation.Vertical;

        C02831(AnchoredDraggableState<?> anchoredDraggableState) {
            this.$state = anchoredDraggableState;
        }

        public final Orientation getOrientation() {
            return this.orientation;
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
            DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1;
            if (continuation instanceof DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) {
                drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = (DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) continuation;
                if ((drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label & Integer.MIN_VALUE) != 0) {
                    drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label -= Integer.MIN_VALUE;
                } else {
                    drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = new DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1(this, continuation);
                }
            } else {
                drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = new DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1(this, continuation);
            }
            DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2 = drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1;
            Object $result = drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    float toFling = velocityToFloat(available);
                    float currentOffset = this.$state.requireOffset();
                    if (toFling < 0.0f && currentOffset > this.$state.getMinOffset()) {
                        AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                        drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.J$0 = available;
                        drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.label = 1;
                        if (anchoredDraggableState.settle(toFling, drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        available = Velocity.INSTANCE.m5510getZero9UxMQ8M();
                    }
                    break;
                case 1:
                    available = drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$2.J$0;
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
            DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1;
            if (continuation instanceof DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) {
                drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = (DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) continuation;
                if ((drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label & Integer.MIN_VALUE) != 0) {
                    drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label -= Integer.MIN_VALUE;
                } else {
                    drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = new DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1(this, continuation);
                }
            } else {
                drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = new DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1(this, continuation);
            }
            Object $result = drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                    float fVelocityToFloat = velocityToFloat(available);
                    drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.J$0 = available;
                    drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label = 1;
                    if (anchoredDraggableState.settle(fVelocityToFloat, drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    available = drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.J$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Velocity.m5490boximpl(available);
        }

        private final long toOffset(float $this$toOffset) {
            return androidx.compose.ui.geometry.OffsetKt.Offset(this.orientation == Orientation.Horizontal ? $this$toOffset : 0.0f, this.orientation == Orientation.Vertical ? $this$toOffset : 0.0f);
        }

        private final float velocityToFloat(long $this$toFloat) {
            return this.orientation == Orientation.Horizontal ? Velocity.m5499getXimpl($this$toFloat) : Velocity.m5500getYimpl($this$toFloat);
        }

        private final float offsetToFloat(long $this$toFloat) {
            return this.orientation == Orientation.Horizontal ? Offset.m2731getXimpl($this$toFloat) : Offset.m2732getYimpl($this$toFloat);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NestedScrollConnection ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(AnchoredDraggableState<?> anchoredDraggableState) {
        return new C02831(anchoredDraggableState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnchoredDraggableState.AnchorChangedCallback<BottomDrawerValue> BottomDrawerAnchorChangeCallback(final BottomDrawerState state, final CoroutineScope scope) {
        return new AnchoredDraggableState.AnchorChangedCallback<BottomDrawerValue>() { // from class: androidx.compose.material.DrawerKt.BottomDrawerAnchorChangeCallback.1

            /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$BottomDrawerAnchorChangeCallback$1$WhenMappings */
            /* JADX INFO: compiled from: Drawer.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[BottomDrawerValue.values().length];
                    try {
                        iArr[BottomDrawerValue.Closed.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[BottomDrawerValue.Open.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[BottomDrawerValue.Expanded.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // androidx.compose.material.AnchoredDraggableState.AnchorChangedCallback
            public final void onAnchorsChanged(BottomDrawerValue previousTarget, Map<BottomDrawerValue, Float> previousAnchors, Map<BottomDrawerValue, Float> newAnchors) {
                BottomDrawerValue bottomDrawerValue;
                Intrinsics.checkNotNullParameter(previousTarget, "previousTarget");
                Intrinsics.checkNotNullParameter(previousAnchors, "previousAnchors");
                Intrinsics.checkNotNullParameter(newAnchors, "newAnchors");
                Float previousTargetOffset = previousAnchors.get(previousTarget);
                switch (WhenMappings.$EnumSwitchMapping$0[previousTarget.ordinal()]) {
                    case 1:
                        bottomDrawerValue = BottomDrawerValue.Closed;
                        break;
                    case 2:
                    case 3:
                        boolean hasHalfExpandedState = newAnchors.containsKey(BottomDrawerValue.Open);
                        if (hasHalfExpandedState) {
                            bottomDrawerValue = BottomDrawerValue.Open;
                        } else if (!newAnchors.containsKey(BottomDrawerValue.Expanded)) {
                            bottomDrawerValue = BottomDrawerValue.Closed;
                        } else {
                            bottomDrawerValue = BottomDrawerValue.Expanded;
                        }
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                BottomDrawerValue newTarget = bottomDrawerValue;
                float newTargetOffset = ((Number) MapsKt.getValue(newAnchors, newTarget)).floatValue();
                if (!Intrinsics.areEqual(newTargetOffset, previousTargetOffset)) {
                    if (state.isAnimationRunning$material_release()) {
                        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new DrawerKt$BottomDrawerAnchorChangeCallback$1$onAnchorsChanged$1(state, newTarget, null), 3, null);
                        return;
                    }
                    boolean didSnapSynchronously = state.trySnapTo$material_release(newTarget);
                    if (!didSnapSynchronously) {
                        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new DrawerKt$BottomDrawerAnchorChangeCallback$1$onAnchorsChanged$2(state, newTarget, null), 3, null);
                    }
                }
            }
        };
    }
}
