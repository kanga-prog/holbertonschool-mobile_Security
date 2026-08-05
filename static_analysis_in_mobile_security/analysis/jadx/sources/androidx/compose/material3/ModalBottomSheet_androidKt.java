package androidx.compose.material3;

import android.view.View;
import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.DraggableKt;
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
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import java.util.Map;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: ModalBottomSheet.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\u001a\u009e\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a¨\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0015H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u001b\u001aq\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020\u000726\u0010 \u001a2\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u00110%¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00010!2!\u0010'\u001a\u001d\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010\u0013H\u0003\u001a6\u0010(\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0018\u001a\u00020\u00192\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0011H\u0001¢\u0006\u0002\u0010)\u001a3\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u000b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010,\u001a\u00020-H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010/\u001a-\u00100\u001a\u00020\u00072\b\b\u0002\u00101\u001a\u00020-2\u0014\b\u0002\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020-0\u0013H\u0007¢\u0006\u0002\u00103\u001aX\u00104\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u00106\u001a\u00020%2,\u00107\u001a(\u0012\u0004\u0012\u000208\u0012\u0013\u0012\u00110%¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00010!¢\u0006\u0002\b\u0015H\u0003\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00069"}, d2 = {"ModalBottomSheet", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "sheetState", "Landroidx/compose/material3/SheetState;", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "scrimColor", "dragHandle", "Landroidx/compose/runtime/Composable;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "ModalBottomSheet-xOkiWaM", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;Landroidx/compose/ui/graphics/Shape;JJFJLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "ModalBottomSheet-EP0qOeE", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;Landroidx/compose/ui/graphics/Shape;JJFJLkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "ModalBottomSheetAnchorChangeHandler", "Landroidx/compose/material3/AnchorChangeHandler;", "Landroidx/compose/material3/SheetValue;", "state", "animateTo", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "target", "", "velocity", "snapTo", "ModalBottomSheetPopup", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Scrim", "color", "visible", "", "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "rememberModalBottomSheetState", "skipPartiallyExpanded", "confirmValueChange", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "modalBottomSheetSwipeable", "anchorChangeHandler", "screenHeight", "onDragStopped", "Lkotlinx/coroutines/CoroutineScope;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModalBottomSheet_androidKt {
    /* JADX INFO: renamed from: ModalBottomSheet-EP0qOeE, reason: not valid java name */
    public static final void m1612ModalBottomSheetEP0qOeE(final Function0<Unit> onDismissRequest, Modifier modifier, SheetState sheetState, Shape shape, long containerColor, long contentColor, float tonalElevation, long scrimColor, Function2<? super Composer, ? super Integer, Unit> function2, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int $changed1, final int i) {
        SheetState sheetState2;
        Shape shape2;
        float tonalElevation2;
        int $dirty;
        Modifier.Companion modifier2;
        final SheetState sheetState3;
        Shape shape3;
        long containerColor2;
        long contentColor2;
        long scrimColor2;
        WindowInsets windowInsets2;
        int $dirty2;
        Function2<? super Composer, ? super Integer, Unit> function3;
        long scrimColor3;
        long contentColor3;
        Object value$iv$iv$iv;
        final int $dirty1;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Modifier modifier3;
        long containerColor3;
        SheetState sheetState4;
        WindowInsets windowInsets3;
        long contentColor4;
        Shape shape4;
        float tonalElevation3;
        int $dirty3;
        Intrinsics.checkNotNullParameter(onDismissRequest, "onDismissRequest");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-879758728);
        ComposerKt.sourceInformation($composer2, "C(ModalBottomSheet)P(5,4,8,7,0:c#ui.graphics.Color,2:c#ui.graphics.Color,9:c#ui.unit.Dp,6:c#ui.graphics.Color,3,10)125@5985L31,126@6057L13,127@6120L14,128@6162L31,130@6295L10,132@6442L12,135@6525L24,152@7150L507,167@7663L4907,269@12645L41,269@12618L68:ModalBottomSheet.android.kt#uh7d8r");
        int $dirty4 = $changed;
        int $dirty5 = $changed1;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty4 |= $composer2.changedInstance(onDismissRequest) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty4 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty4 |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                sheetState2 = sheetState;
                int i3 = $composer2.changed(sheetState2) ? 256 : 128;
                $dirty4 |= i3;
            } else {
                sheetState2 = sheetState;
            }
            $dirty4 |= i3;
        } else {
            sheetState2 = sheetState;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i4 = $composer2.changed(shape2) ? 2048 : 1024;
                $dirty4 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i4;
        } else {
            shape2 = shape;
        }
        if ((57344 & $changed) == 0) {
            $dirty4 |= ((i & 16) == 0 && $composer2.changed(containerColor)) ? 16384 : 8192;
        }
        if ((458752 & $changed) == 0) {
            $dirty4 |= ((i & 32) == 0 && $composer2.changed(contentColor)) ? 131072 : 65536;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty4 |= 1572864;
            tonalElevation2 = tonalElevation;
        } else if ((3670016 & $changed) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty4 |= $composer2.changed(tonalElevation2) ? 1048576 : 524288;
        } else {
            tonalElevation2 = tonalElevation;
        }
        if (($changed & 29360128) == 0) {
            if ((i & 128) == 0) {
                $dirty3 = $dirty4;
                int i6 = $composer2.changed(scrimColor) ? 8388608 : 4194304;
                $dirty = $dirty3 | i6;
            } else {
                $dirty3 = $dirty4;
            }
            $dirty = $dirty3 | i6;
        } else {
            $dirty = $dirty4;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((1879048192 & $changed) == 0) {
            $dirty |= ((i & 512) == 0 && $composer2.changed(windowInsets)) ? 536870912 : 268435456;
        }
        int $dirty6 = $dirty;
        if ((i & 1024) != 0) {
            $dirty5 |= 6;
        } else if (($changed1 & 14) == 0) {
            $dirty5 |= $composer2.changedInstance(content) ? 4 : 2;
        }
        if ((1533916891 & $dirty6) == 306783378 && ($dirty5 & 11) == 2 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            containerColor3 = containerColor;
            contentColor4 = contentColor;
            scrimColor3 = scrimColor;
            function4 = function2;
            windowInsets3 = windowInsets;
            $dirty1 = $dirty5;
            sheetState4 = sheetState2;
            shape4 = shape2;
            tonalElevation3 = tonalElevation2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if ((i & 4) != 0) {
                    sheetState3 = rememberModalBottomSheetState(false, null, $composer2, 0, 3);
                    $dirty6 &= -897;
                } else {
                    sheetState3 = sheetState2;
                }
                if ((i & 8) != 0) {
                    shape3 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                    $dirty6 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty6 &= -57345;
                    containerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 32) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 12) & 14);
                    $dirty6 &= -458753;
                } else {
                    contentColor2 = contentColor;
                }
                float tonalElevation4 = i5 != 0 ? BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM() : tonalElevation2;
                if ((i & 128) != 0) {
                    scrimColor2 = BottomSheetDefaults.INSTANCE.getScrimColor($composer2, 6);
                    $dirty6 &= -29360129;
                } else {
                    scrimColor2 = scrimColor;
                }
                Function2<? super Composer, ? super Integer, Unit> function2M1449getLambda1$material3_release = i7 != 0 ? ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1449getLambda1$material3_release() : function2;
                if ((i & 512) != 0) {
                    $dirty2 = $dirty6 & (-1879048193);
                    tonalElevation2 = tonalElevation4;
                    windowInsets2 = BottomSheetDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    function3 = function2M1449getLambda1$material3_release;
                    scrimColor3 = scrimColor2;
                    contentColor3 = contentColor2;
                    shape2 = shape3;
                } else {
                    windowInsets2 = windowInsets;
                    $dirty2 = $dirty6;
                    tonalElevation2 = tonalElevation4;
                    function3 = function2M1449getLambda1$material3_release;
                    scrimColor3 = scrimColor2;
                    contentColor3 = contentColor2;
                    shape2 = shape3;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty6 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty6 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty6 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty6 &= -458753;
                }
                if ((i & 128) != 0) {
                    $dirty6 &= -29360129;
                }
                if ((i & 512) != 0) {
                    $dirty6 &= -1879048193;
                }
                modifier2 = modifier;
                containerColor2 = containerColor;
                scrimColor3 = scrimColor;
                windowInsets2 = windowInsets;
                $dirty2 = $dirty6;
                sheetState3 = sheetState2;
                contentColor3 = contentColor;
                function3 = function2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-879758728, $dirty2, $dirty5, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:122)");
            }
            $composer2.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer2, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv$iv = $composer2.rememberedValue();
            WindowInsets windowInsets4 = windowInsets2;
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
            final Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$animateToDismiss$1
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
                    if (sheetState3.getSwipeableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Hidden).booleanValue()) {
                        Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(sheetState3, null), 3, null);
                        final SheetState sheetState5 = sheetState3;
                        final Function0<Unit> function1 = onDismissRequest;
                        jobLaunch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$animateToDismiss$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable it) {
                                if (!sheetState5.isVisible()) {
                                    function1.invoke();
                                }
                            }
                        });
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$animateToDismiss$1$1, reason: invalid class name */
                /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$animateToDismiss$1$1", f = "ModalBottomSheet.android.kt", i = {}, l = {139}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ SheetState $sheetState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$sheetState = sheetState;
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
            };
            final Function1<Float, Unit> function1 = new Function1<Float, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$settleToDismiss$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                    invoke(f.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(float it) {
                    Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(sheetState3, it, null), 3, null);
                    final SheetState sheetState5 = sheetState3;
                    final Function0<Unit> function5 = onDismissRequest;
                    jobLaunch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$settleToDismiss$1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                            invoke2(th);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Throwable it2) {
                            if (!sheetState5.isVisible()) {
                                function5.invoke();
                            }
                        }
                    });
                }

                /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$settleToDismiss$1$1, reason: invalid class name */
                /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$settleToDismiss$1$1", f = "ModalBottomSheet.android.kt", i = {}, l = {147}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ float $it;
                    final /* synthetic */ SheetState $sheetState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(SheetState sheetState, float f, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$sheetState = sheetState;
                        this.$it = f;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$sheetState, this.$it, continuation);
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
                                if (this.$sheetState.settle$material3_release(this.$it, this) == coroutine_suspended) {
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
            int i8 = (($dirty2 >> 6) & 14) | 64;
            $dirty1 = $dirty5;
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(sheetState3) | $composer2.changed(scope);
            final Function2<? super Composer, ? super Integer, Unit> function5 = function3;
            Object value$iv$iv = $composer2.rememberedValue();
            if (invalid$iv$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = ModalBottomSheetAnchorChangeHandler(sheetState3, new Function2<SheetValue, Float, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$anchorChangeHandler$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(SheetValue sheetValue, Float f) {
                        invoke(sheetValue, f.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(SheetValue target, float velocity) {
                        Intrinsics.checkNotNullParameter(target, "target");
                        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(sheetState3, target, velocity, null), 3, null);
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$anchorChangeHandler$1$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$anchorChangeHandler$1$1$1", f = "ModalBottomSheet.android.kt", i = {}, l = {157}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $sheetState;
                        final /* synthetic */ SheetValue $target;
                        final /* synthetic */ float $velocity;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SheetState sheetState, SheetValue sheetValue, float f, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                            this.$target = sheetValue;
                            this.$velocity = f;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$sheetState, this.$target, this.$velocity, continuation);
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
                                    if (this.$sheetState.animateTo$material3_release(this.$target, this.$velocity, this) == coroutine_suspended) {
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
                }, new Function1<SheetValue, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$anchorChangeHandler$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SheetValue sheetValue) {
                        invoke2(sheetValue);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SheetValue target) {
                        Intrinsics.checkNotNullParameter(target, "target");
                        boolean didSnapImmediately = sheetState3.trySnapTo$material3_release(target);
                        if (!didSnapImmediately) {
                            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(sheetState3, target, null), 3, null);
                        }
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$anchorChangeHandler$1$2$1, reason: invalid class name */
                    /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$anchorChangeHandler$1$2$1", f = "ModalBottomSheet.android.kt", i = {}, l = {162}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $sheetState;
                        final /* synthetic */ SheetValue $target;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SheetState sheetState, SheetValue sheetValue, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$sheetState = sheetState;
                            this.$target = sheetValue;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$sheetState, this.$target, continuation);
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
                                    if (this.$sheetState.snapTo$material3_release(this.$target, this) == coroutine_suspended) {
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
                $composer2.updateRememberedValue(value$iv$iv);
            }
            $composer2.endReplaceableGroup();
            final AnchorChangeHandler anchorChangeHandler = (AnchorChangeHandler) value$iv$iv;
            final long j = scrimColor3;
            final SheetState sheetState5 = sheetState3;
            final int i9 = $dirty2;
            final Modifier modifier4 = modifier2;
            final Shape shape5 = shape2;
            final long j2 = containerColor2;
            final long j3 = contentColor3;
            final float f = tonalElevation2;
            Modifier modifier5 = modifier2;
            long containerColor4 = containerColor2;
            ModalBottomSheetPopup(new Function0<Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
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
                    if (sheetState3.getCurrentValue() != SheetValue.Expanded || !sheetState3.getHasPartiallyExpandedState()) {
                        Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass2(sheetState3, null), 3, null);
                        final Function0<Unit> function6 = onDismissRequest;
                        jobLaunch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                invoke2(th);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable it) {
                                function6.invoke();
                            }
                        });
                        return;
                    }
                    BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(sheetState3, null), 3, null);
                }

                /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1$1, reason: invalid class name */
                /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1$1", f = "ModalBottomSheet.android.kt", i = {}, l = {171}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ SheetState $sheetState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$sheetState = sheetState;
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
                                if (this.$sheetState.partialExpand(this) == coroutine_suspended) {
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

                /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1$2, reason: invalid class name */
                /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1$2", f = "ModalBottomSheet.android.kt", i = {}, l = {173}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ SheetState $sheetState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass2(SheetState sheetState, Continuation<? super AnonymousClass2> continuation) {
                        super(2, continuation);
                        this.$sheetState = sheetState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass2(this.$sheetState, continuation);
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
            }, windowInsets4, ComposableLambdaKt.composableLambda($composer2, 1424497392, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2
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
                    ComposerKt.sourceInformation($composer3, "C177@8118L4446:ModalBottomSheet.android.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1424497392, $changed2, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:176)");
                        }
                        Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                        final long j4 = j;
                        final Function0<Unit> function6 = function0;
                        final SheetState sheetState6 = sheetState5;
                        final int i10 = i9;
                        final Modifier modifier6 = modifier4;
                        final AnchorChangeHandler<SheetValue> anchorChangeHandler2 = anchorChangeHandler;
                        final Function1<Float, Unit> function7 = function1;
                        final Shape shape6 = shape5;
                        final long j5 = j2;
                        final long j6 = j3;
                        final float f2 = f;
                        final Function2<Composer, Integer, Unit> function8 = function5;
                        final Function3<ColumnScope, Composer, Integer, Unit> function9 = content;
                        final int i11 = $dirty1;
                        final CoroutineScope coroutineScope = scope;
                        BoxWithConstraintsKt.BoxWithConstraints(modifierFillMaxSize$default, null, false, ComposableLambdaKt.composableLambda($composer3, 574030426, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2.1
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

                            /* JADX WARN: Code duplicated, block: B:54:0x022a  */
                            /* JADX WARN: Code duplicated, block: B:57:? A[RETURN, SYNTHETIC] */
                            public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer $composer4, int $changed3) {
                                Object value$iv$iv2;
                                Object value$iv$iv3;
                                Object value$iv$iv4;
                                boolean invalid$iv$iv2;
                                Object value$iv$iv5;
                                Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
                                ComposerKt.sourceInformation($composer4, "C179@8226L168,184@8434L48,190@8711L36,191@8776L244,200@9080L348,212@9716L75,185@8495L4059:ModalBottomSheet.android.kt#uh7d8r");
                                int $dirty7 = $changed3;
                                if (($changed3 & 14) == 0) {
                                    $dirty7 |= $composer4.changed(BoxWithConstraints) ? 4 : 2;
                                }
                                if (($dirty7 & 91) != 18 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(574030426, $changed3, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous>.<anonymous> (ModalBottomSheet.android.kt:177)");
                                    }
                                    int fullHeight = Constraints.m5217getMaxHeightimpl(BoxWithConstraints.mo424getConstraintsmsEJaDk());
                                    ModalBottomSheet_androidKt.m1614Scrim3JVO9M(j4, function6, sheetState6.getTargetValue() != SheetValue.Hidden, $composer4, (i10 >> 21) & 14);
                                    final String bottomSheetPaneTitle = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1737getBottomSheetPaneTitleadMyvUU(), $composer4, 6);
                                    Modifier modifierAlign = BoxWithConstraints.align(SizeKt.fillMaxWidth$default(SizeKt.m541widthInVpY3zN4$default(modifier6, 0.0f, SheetDefaultsKt.getBottomSheetMaxWidth(), 1, null), 0.0f, 1, null), Alignment.INSTANCE.getTopCenter());
                                    $composer4.startReplaceableGroup(1157296644);
                                    ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                    boolean invalid$iv$iv3 = $composer4.changed(bottomSheetPaneTitle);
                                    Object it$iv$iv = $composer4.rememberedValue();
                                    if (invalid$iv$iv3 || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                                        value$iv$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$1$1
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
                                                SemanticsPropertiesKt.setPaneTitle(semantics, bottomSheetPaneTitle);
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv$iv2);
                                    } else {
                                        value$iv$iv2 = it$iv$iv;
                                    }
                                    $composer4.endReplaceableGroup();
                                    Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierAlign, false, (Function1) value$iv$iv2, 1, null);
                                    Object key1$iv = sheetState6;
                                    final SheetState sheetState7 = sheetState6;
                                    int i12 = (i10 >> 6) & 14;
                                    $composer4.startReplaceableGroup(1157296644);
                                    ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                    boolean invalid$iv$iv4 = $composer4.changed(key1$iv);
                                    Object it$iv$iv2 = $composer4.rememberedValue();
                                    if (invalid$iv$iv4 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                                        value$iv$iv3 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$2$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ IntOffset invoke(Density density) {
                                                return IntOffset.m5383boximpl(m1616invokeBjo55l4(density));
                                            }

                                            /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                                            public final long m1616invokeBjo55l4(Density offset) {
                                                Intrinsics.checkNotNullParameter(offset, "$this$offset");
                                                return IntOffsetKt.IntOffset(0, (int) sheetState7.requireOffset());
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv$iv3);
                                    } else {
                                        value$iv$iv3 = it$iv$iv2;
                                    }
                                    $composer4.endReplaceableGroup();
                                    Modifier modifierOffset = OffsetKt.offset(modifierSemantics$default, (Function1) value$iv$iv3);
                                    Object key1$iv2 = sheetState6;
                                    SheetState sheetState8 = sheetState6;
                                    Function1<Float, Unit> function10 = function7;
                                    int i13 = (i10 >> 6) & 14;
                                    $composer4.startReplaceableGroup(1157296644);
                                    ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                    boolean invalid$iv$iv5 = $composer4.changed(key1$iv2);
                                    Object it$iv$iv3 = $composer4.rememberedValue();
                                    if (!invalid$iv$iv5) {
                                        Object key1$iv3 = Composer.INSTANCE.getEmpty();
                                        if (it$iv$iv3 != key1$iv3) {
                                            value$iv$iv4 = it$iv$iv3;
                                        }
                                        $composer4.endReplaceableGroup();
                                        Modifier modifierNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(modifierOffset, (NestedScrollConnection) value$iv$iv4, null, 2, null);
                                        SheetState sheetState9 = sheetState6;
                                        AnchorChangeHandler<SheetValue> anchorChangeHandler3 = anchorChangeHandler2;
                                        float f3 = fullHeight;
                                        Object key1$iv4 = function7;
                                        final Function1<Float, Unit> function11 = function7;
                                        $composer4.startReplaceableGroup(1157296644);
                                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                        invalid$iv$iv2 = $composer4.changed(key1$iv4);
                                        value$iv$iv5 = $composer4.rememberedValue();
                                        if (!invalid$iv$iv2 || value$iv$iv5 == Composer.INSTANCE.getEmpty()) {
                                            value$iv$iv5 = (Function2) new Function2<CoroutineScope, Float, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$4$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                /* JADX WARN: Multi-variable type inference failed */
                                                {
                                                    super(2);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope2, Float f4) {
                                                    invoke(coroutineScope2, f4.floatValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(CoroutineScope modalBottomSheetSwipeable, float it) {
                                                    Intrinsics.checkNotNullParameter(modalBottomSheetSwipeable, "$this$modalBottomSheetSwipeable");
                                                    function11.invoke(Float.valueOf(it));
                                                }
                                            };
                                            $composer4.updateRememberedValue(value$iv$iv5);
                                        }
                                        $composer4.endReplaceableGroup();
                                        Modifier modifierModalBottomSheetSwipeable = ModalBottomSheet_androidKt.modalBottomSheetSwipeable(modifierNestedScroll$default, sheetState9, anchorChangeHandler3, f3, (Function2) value$iv$iv5);
                                        Shape shape7 = shape6;
                                        long j7 = j5;
                                        long j8 = j6;
                                        float f4 = f2;
                                        final Function2<Composer, Integer, Unit> function12 = function8;
                                        final Function3<ColumnScope, Composer, Integer, Unit> function13 = function9;
                                        final int i14 = i11;
                                        final SheetState sheetState10 = sheetState6;
                                        final Function0<Unit> function14 = function6;
                                        final CoroutineScope coroutineScope2 = coroutineScope;
                                        final int i15 = i10;
                                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, 1371274015, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt.ModalBottomSheet.2.1.5
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
                                                ComposerKt.sourceInformation($composer5, "C221@10013L2527:ModalBottomSheet.android.kt#uh7d8r");
                                                if (($changed4 & 11) != 2 || !$composer5.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(1371274015, $changed4, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous>.<anonymous>.<anonymous> (ModalBottomSheet.android.kt:220)");
                                                    }
                                                    Modifier modifier$iv = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                                    Function2<Composer, Integer, Unit> function15 = function12;
                                                    Function3<ColumnScope, Composer, Integer, Unit> function16 = function13;
                                                    int i16 = i14;
                                                    final SheetState sheetState11 = sheetState10;
                                                    final Function0<Unit> function17 = function14;
                                                    final CoroutineScope coroutineScope3 = coroutineScope2;
                                                    int i17 = i15;
                                                    $composer5.startReplaceableGroup(-483455358);
                                                    ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                                                    int $changed$iv$iv = (6 << 3) & 112;
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
                                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
                                                    int i18 = ($changed$iv$iv$iv >> 9) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer5, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                                    int $changed5 = ((6 >> 6) & 112) | 6;
                                                    ColumnScope $this$invoke_u24lambda_u241 = ColumnScopeInstance.INSTANCE;
                                                    ComposerKt.sourceInformationMarkerStart($composer5, -11289811, "C263@12513L9:ModalBottomSheet.android.kt#uh7d8r");
                                                    $composer5.startReplaceableGroup(-11289811);
                                                    ComposerKt.sourceInformation($composer5, "224@10171L54,225@10275L48,226@10372L47,227@10444L2026");
                                                    if (function15 != null) {
                                                        final String collapseActionLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1738getBottomSheetPartialExpandDescriptionadMyvUU(), $composer5, 6);
                                                        final String dismissActionLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1734getBottomSheetDismissDescriptionadMyvUU(), $composer5, 6);
                                                        final String expandActionLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1736getBottomSheetExpandDescriptionadMyvUU(), $composer5, 6);
                                                        Modifier modifier$iv2 = SemanticsModifierKt.semantics($this$invoke_u24lambda_u241.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally()), true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1
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
                                                                final SheetState $this$invoke_u24lambda_u240 = sheetState11;
                                                                String str = dismissActionLabel;
                                                                String str2 = expandActionLabel;
                                                                String str3 = collapseActionLabel;
                                                                final Function0<Unit> function18 = function17;
                                                                final CoroutineScope coroutineScope4 = coroutineScope3;
                                                                final SheetState sheetState12 = sheetState11;
                                                                SemanticsPropertiesKt.dismiss(semantics, str, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$1
                                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                    {
                                                                        super(0);
                                                                    }

                                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                                    @Override // kotlin.jvm.functions.Function0
                                                                    public final Boolean invoke() {
                                                                        function18.invoke();
                                                                        return true;
                                                                    }
                                                                });
                                                                if ($this$invoke_u24lambda_u240.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                                                    SemanticsPropertiesKt.expand(semantics, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$2
                                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                        {
                                                                            super(0);
                                                                        }

                                                                        /* JADX WARN: Can't rename method to resolve collision */
                                                                        @Override // kotlin.jvm.functions.Function0
                                                                        public final Boolean invoke() {
                                                                            if ($this$invoke_u24lambda_u240.getSwipeableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                                                BuildersKt__Builders_commonKt.launch$default(coroutineScope4, null, null, new AnonymousClass1(sheetState12, null), 3, null);
                                                                            }
                                                                            return true;
                                                                        }

                                                                        /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$2$1, reason: invalid class name */
                                                                        /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
                                                                        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                                                        @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$2$1", f = "ModalBottomSheet.android.kt", i = {}, l = {242}, m = "invokeSuspend", n = {}, s = {})
                                                                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                                            final /* synthetic */ SheetState $sheetState;
                                                                            int label;

                                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                            AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                                super(2, continuation);
                                                                                this.$sheetState = sheetState;
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
                                                                                        if (this.$sheetState.expand(this) == coroutine_suspended) {
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
                                                                } else if ($this$invoke_u24lambda_u240.getHasPartiallyExpandedState()) {
                                                                    SemanticsPropertiesKt.collapse(semantics, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$3
                                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                        {
                                                                            super(0);
                                                                        }

                                                                        /* JADX WARN: Can't rename method to resolve collision */
                                                                        @Override // kotlin.jvm.functions.Function0
                                                                        public final Boolean invoke() {
                                                                            if ($this$invoke_u24lambda_u240.getSwipeableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                                                BuildersKt__Builders_commonKt.launch$default(coroutineScope4, null, null, new AnonymousClass1($this$invoke_u24lambda_u240, null), 3, null);
                                                                            }
                                                                            return true;
                                                                        }

                                                                        /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$3$1, reason: invalid class name */
                                                                        /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
                                                                        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                                                        @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$3$1", f = "ModalBottomSheet.android.kt", i = {}, l = {253}, m = "invokeSuspend", n = {}, s = {})
                                                                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                                            final /* synthetic */ SheetState $this_with;
                                                                            int label;

                                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                            AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                                super(2, continuation);
                                                                                this.$this_with = sheetState;
                                                                            }

                                                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                                return new AnonymousClass1(this.$this_with, continuation);
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
                                                                                        if (this.$this_with.partialExpand(this) == coroutine_suspended) {
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
                                                            }
                                                        });
                                                        $composer5.startReplaceableGroup(733328855);
                                                        ComposerKt.sourceInformation($composer5, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                                        MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                                        int $changed$iv$iv2 = (0 << 3) & 112;
                                                        $composer5.startReplaceableGroup(-1323940314);
                                                        ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                        Object objConsume4 = $composer5.consume(localDensity2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                                        Density density$iv$iv2 = (Density) objConsume4;
                                                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                        Object objConsume5 = $composer5.consume(localLayoutDirection2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                                        LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                                                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                        Object objConsume6 = $composer5.consume(localViewConfiguration2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                                        ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
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
                                                        $composer5.disableReusing();
                                                        Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer5);
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                                        $composer5.enableReusing();
                                                        function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                                        $composer5.startReplaceableGroup(2058660585);
                                                        int i19 = ($changed$iv$iv$iv2 >> 9) & 14;
                                                        ComposerKt.sourceInformationMarkerStart($composer5, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                        int i20 = ((0 >> 6) & 112) | 6;
                                                        ComposerKt.sourceInformationMarkerStart($composer5, -1407594613, "C260@12432L12:ModalBottomSheet.android.kt#uh7d8r");
                                                        function15.invoke($composer5, Integer.valueOf((i17 >> 24) & 14));
                                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                                        $composer5.endReplaceableGroup();
                                                        $composer5.endNode();
                                                        $composer5.endReplaceableGroup();
                                                        $composer5.endReplaceableGroup();
                                                    }
                                                    $composer5.endReplaceableGroup();
                                                    function16.invoke($this$invoke_u24lambda_u241, $composer5, Integer.valueOf(($changed5 & 14) | ((i16 << 3) & 112)));
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
                                        int i16 = i10;
                                        SurfaceKt.m1806SurfaceT9BRK9s(modifierModalBottomSheetSwipeable, shape7, j7, j8, f4, 0.0f, null, composableLambda, $composer4, ((i16 >> 6) & 112) | 12582912 | ((i16 >> 6) & 896) | ((i16 >> 6) & 7168) | ((i16 >> 6) & 57344), 96);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    value$iv$iv4 = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState8, Orientation.Vertical, function10);
                                    $composer4.updateRememberedValue(value$iv$iv4);
                                    $composer4.endReplaceableGroup();
                                    Modifier modifierNestedScroll$default2 = NestedScrollModifierKt.nestedScroll$default(modifierOffset, (NestedScrollConnection) value$iv$iv4, null, 2, null);
                                    SheetState sheetState11 = sheetState6;
                                    AnchorChangeHandler<SheetValue> anchorChangeHandler4 = anchorChangeHandler2;
                                    float f5 = fullHeight;
                                    Object key1$iv5 = function7;
                                    final Function1<? super Float, Unit> function15 = function7;
                                    $composer4.startReplaceableGroup(1157296644);
                                    ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                    invalid$iv$iv2 = $composer4.changed(key1$iv5);
                                    value$iv$iv5 = $composer4.rememberedValue();
                                    if (!invalid$iv$iv2) {
                                    }
                                    value$iv$iv5 = (Function2) new Function2<CoroutineScope, Float, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$4$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3, Float f6) {
                                            invoke(coroutineScope3, f6.floatValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(CoroutineScope modalBottomSheetSwipeable, float it) {
                                            Intrinsics.checkNotNullParameter(modalBottomSheetSwipeable, "$this$modalBottomSheetSwipeable");
                                            function15.invoke(Float.valueOf(it));
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv$iv5);
                                    $composer4.endReplaceableGroup();
                                    Modifier modifierModalBottomSheetSwipeable2 = ModalBottomSheet_androidKt.modalBottomSheetSwipeable(modifierNestedScroll$default2, sheetState11, anchorChangeHandler4, f5, (Function2) value$iv$iv5);
                                    Shape shape8 = shape6;
                                    long j9 = j5;
                                    long j10 = j6;
                                    float f6 = f2;
                                    final Function2<? super Composer, ? super Integer, Unit> function16 = function8;
                                    final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function17 = function9;
                                    final int i17 = i11;
                                    final SheetState sheetState12 = sheetState6;
                                    final Function0<Unit> function18 = function6;
                                    final CoroutineScope coroutineScope3 = coroutineScope;
                                    final int i18 = i10;
                                    ComposableLambda composableLambda2 = ComposableLambdaKt.composableLambda($composer4, 1371274015, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt.ModalBottomSheet.2.1.5
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
                                            ComposerKt.sourceInformation($composer5, "C221@10013L2527:ModalBottomSheet.android.kt#uh7d8r");
                                            if (($changed4 & 11) != 2 || !$composer5.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1371274015, $changed4, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous>.<anonymous>.<anonymous> (ModalBottomSheet.android.kt:220)");
                                                }
                                                Modifier modifier$iv = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                                Function2<Composer, Integer, Unit> function19 = function16;
                                                Function3<ColumnScope, Composer, Integer, Unit> function110 = function17;
                                                int i19 = i17;
                                                final SheetState sheetState13 = sheetState12;
                                                final Function0<Unit> function111 = function18;
                                                final CoroutineScope coroutineScope4 = coroutineScope3;
                                                int i110 = i18;
                                                $composer5.startReplaceableGroup(-483455358);
                                                ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                                MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                                                int $changed$iv$iv = (6 << 3) & 112;
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
                                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
                                                int i111 = ($changed$iv$iv$iv >> 9) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer5, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                                int $changed5 = ((6 >> 6) & 112) | 6;
                                                ColumnScope $this$invoke_u24lambda_u241 = ColumnScopeInstance.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart($composer5, -11289811, "C263@12513L9:ModalBottomSheet.android.kt#uh7d8r");
                                                $composer5.startReplaceableGroup(-11289811);
                                                ComposerKt.sourceInformation($composer5, "224@10171L54,225@10275L48,226@10372L47,227@10444L2026");
                                                if (function19 != null) {
                                                    final String collapseActionLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1738getBottomSheetPartialExpandDescriptionadMyvUU(), $composer5, 6);
                                                    final String dismissActionLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1734getBottomSheetDismissDescriptionadMyvUU(), $composer5, 6);
                                                    final String expandActionLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1736getBottomSheetExpandDescriptionadMyvUU(), $composer5, 6);
                                                    Modifier modifier$iv2 = SemanticsModifierKt.semantics($this$invoke_u24lambda_u241.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally()), true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1
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
                                                            final SheetState $this$invoke_u24lambda_u240 = sheetState13;
                                                            String str = dismissActionLabel;
                                                            String str2 = expandActionLabel;
                                                            String str3 = collapseActionLabel;
                                                            final Function0<Unit> function112 = function111;
                                                            final CoroutineScope coroutineScope5 = coroutineScope4;
                                                            final SheetState sheetState14 = sheetState13;
                                                            SemanticsPropertiesKt.dismiss(semantics, str, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$1
                                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                {
                                                                    super(0);
                                                                }

                                                                /* JADX WARN: Can't rename method to resolve collision */
                                                                @Override // kotlin.jvm.functions.Function0
                                                                public final Boolean invoke() {
                                                                    function112.invoke();
                                                                    return true;
                                                                }
                                                            });
                                                            if ($this$invoke_u24lambda_u240.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                                                SemanticsPropertiesKt.expand(semantics, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$2
                                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                    {
                                                                        super(0);
                                                                    }

                                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                                    @Override // kotlin.jvm.functions.Function0
                                                                    public final Boolean invoke() {
                                                                        if ($this$invoke_u24lambda_u240.getSwipeableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope5, null, null, new AnonymousClass1(sheetState14, null), 3, null);
                                                                        }
                                                                        return true;
                                                                    }

                                                                    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$2$1, reason: invalid class name */
                                                                    /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
                                                                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                                                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$2$1", f = "ModalBottomSheet.android.kt", i = {}, l = {242}, m = "invokeSuspend", n = {}, s = {})
                                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                                        final /* synthetic */ SheetState $sheetState;
                                                                        int label;

                                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                            super(2, continuation);
                                                                            this.$sheetState = sheetState;
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
                                                                                    if (this.$sheetState.expand(this) == coroutine_suspended) {
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
                                                            } else if ($this$invoke_u24lambda_u240.getHasPartiallyExpandedState()) {
                                                                SemanticsPropertiesKt.collapse(semantics, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$3
                                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                    {
                                                                        super(0);
                                                                    }

                                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                                    @Override // kotlin.jvm.functions.Function0
                                                                    public final Boolean invoke() {
                                                                        if ($this$invoke_u24lambda_u240.getSwipeableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope5, null, null, new AnonymousClass1($this$invoke_u24lambda_u240, null), 3, null);
                                                                        }
                                                                        return true;
                                                                    }

                                                                    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$3$1, reason: invalid class name */
                                                                    /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
                                                                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                                                    @DebugMetadata(c = "androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$2$1$5$1$1$1$3$1", f = "ModalBottomSheet.android.kt", i = {}, l = {253}, m = "invokeSuspend", n = {}, s = {})
                                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                                        final /* synthetic */ SheetState $this_with;
                                                                        int label;

                                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                            super(2, continuation);
                                                                            this.$this_with = sheetState;
                                                                        }

                                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                            return new AnonymousClass1(this.$this_with, continuation);
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
                                                                                    if (this.$this_with.partialExpand(this) == coroutine_suspended) {
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
                                                        }
                                                    });
                                                    $composer5.startReplaceableGroup(733328855);
                                                    ComposerKt.sourceInformation($composer5, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                                    MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                                    int $changed$iv$iv2 = (0 << 3) & 112;
                                                    $composer5.startReplaceableGroup(-1323940314);
                                                    ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                                    ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume4 = $composer5.consume(localDensity2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                                    Density density$iv$iv2 = (Density) objConsume4;
                                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                                    ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume5 = $composer5.consume(localLayoutDirection2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                                    LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                                                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                                    ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume6 = $composer5.consume(localViewConfiguration2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                                    ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
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
                                                    $composer5.disableReusing();
                                                    Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer5);
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                                    $composer5.enableReusing();
                                                    function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                                    $composer5.startReplaceableGroup(2058660585);
                                                    int i112 = ($changed$iv$iv$iv2 >> 9) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer5, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                    int i20 = ((0 >> 6) & 112) | 6;
                                                    ComposerKt.sourceInformationMarkerStart($composer5, -1407594613, "C260@12432L12:ModalBottomSheet.android.kt#uh7d8r");
                                                    function19.invoke($composer5, Integer.valueOf((i110 >> 24) & 14));
                                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                                    $composer5.endReplaceableGroup();
                                                    $composer5.endNode();
                                                    $composer5.endReplaceableGroup();
                                                    $composer5.endReplaceableGroup();
                                                }
                                                $composer5.endReplaceableGroup();
                                                function110.invoke($this$invoke_u24lambda_u241, $composer5, Integer.valueOf(($changed5 & 14) | ((i19 << 3) & 112)));
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
                                    int i19 = i10;
                                    SurfaceKt.m1806SurfaceT9BRK9s(modifierModalBottomSheetSwipeable2, shape8, j9, j10, f6, 0.0f, null, composableLambda2, $composer4, ((i19 >> 6) & 112) | 12582912 | ((i19 >> 6) & 896) | ((i19 >> 6) & 7168) | ((i19 >> 6) & 57344), 96);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        }), $composer3, 3078, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, (($dirty2 >> 24) & 112) | 384);
            if (sheetState3.getHasExpandedState()) {
                int i10 = ($dirty2 >> 6) & 14;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean invalid$iv$iv2 = $composer2.changed(sheetState3);
                ModalBottomSheet_androidKt$ModalBottomSheet$3$1 value$iv$iv2 = $composer2.rememberedValue();
                if (invalid$iv$iv2 || value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = new ModalBottomSheet_androidKt$ModalBottomSheet$3$1(sheetState3, null);
                    $composer2.updateRememberedValue(value$iv$iv2);
                }
                $composer2.endReplaceableGroup();
                EffectsKt.LaunchedEffect(sheetState3, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv2, $composer2, (($dirty2 >> 6) & 14) | 64);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function4 = function5;
            modifier3 = modifier5;
            containerColor3 = containerColor4;
            sheetState4 = sheetState3;
            windowInsets3 = windowInsets4;
            contentColor4 = contentColor3;
            shape4 = shape2;
            tonalElevation3 = tonalElevation2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier3;
        final SheetState sheetState6 = sheetState4;
        final Shape shape6 = shape4;
        final long j4 = containerColor3;
        final long j5 = contentColor4;
        final float f2 = tonalElevation3;
        final long j6 = scrimColor3;
        final Function2<? super Composer, ? super Integer, Unit> function6 = function4;
        final WindowInsets windowInsets5 = windowInsets3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$4
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
                ModalBottomSheet_androidKt.m1612ModalBottomSheetEP0qOeE(onDismissRequest, modifier6, sheetState6, shape6, j4, j5, f2, j6, function6, windowInsets5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:116:0x0176  */
    /* JADX WARN: Code duplicated, block: B:118:0x017d  */
    /* JADX WARN: Code duplicated, block: B:137:0x01d1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:138:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:139:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:142:0x01de  */
    /* JADX WARN: Code duplicated, block: B:143:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:146:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:147:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:150:0x0200  */
    /* JADX WARN: Code duplicated, block: B:151:0x020c  */
    /* JADX WARN: Code duplicated, block: B:154:0x0212  */
    /* JADX WARN: Code duplicated, block: B:156:0x0220  */
    /* JADX WARN: Code duplicated, block: B:157:0x0227  */
    /* JADX WARN: Code duplicated, block: B:160:0x022c  */
    /* JADX WARN: Code duplicated, block: B:161:0x0238  */
    /* JADX WARN: Code duplicated, block: B:163:0x023c  */
    /* JADX WARN: Code duplicated, block: B:164:0x0254  */
    /* JADX WARN: Code duplicated, block: B:167:0x026e  */
    /* JADX WARN: Code duplicated, block: B:170:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:174:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:176:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use ModalBottomSheet overload with windowInset parameter.")
    /* JADX INFO: renamed from: ModalBottomSheet-xOkiWaM, reason: not valid java name */
    public static final /* synthetic */ void m1613ModalBottomSheetxOkiWaM(final Function0 onDismissRequest, Modifier modifier, SheetState sheetState, Shape shape, long containerColor, long contentColor, float tonalElevation, long scrimColor, Function2 dragHandle, final Function3 content, Composer $composer, final int $changed, final int i) {
        SheetState sheetState2;
        Shape shape2;
        long contentColor2;
        float f;
        int $dirty;
        int i2;
        int $dirty2;
        Modifier.Companion modifier2;
        SheetState sheetState3;
        Shape shape3;
        long containerColor2;
        float tonalElevation2;
        long scrimColor2;
        Modifier modifier3;
        Function2 dragHandle2;
        int $dirty3;
        float tonalElevation3;
        long containerColor3;
        long contentColor3;
        long scrimColor3;
        SheetState sheetState4;
        Shape shape4;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int $dirty4;
        Intrinsics.checkNotNullParameter(onDismissRequest, "onDismissRequest");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-1311611302);
        ComposerKt.sourceInformation($composer3, "C(ModalBottomSheet)P(5,4,8,7,0:c#ui.graphics.Color,2:c#ui.graphics.Color,9:c#ui.unit.Dp,6:c#ui.graphics.Color,3)284@12980L31,285@13052L13,286@13115L14,287@13157L31,289@13290L10,292@13437L321:ModalBottomSheet.android.kt#uh7d8r");
        int $dirty5 = $changed;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty5 |= $composer3.changedInstance(onDismissRequest) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty5 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty5 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                sheetState2 = sheetState;
                int i4 = $composer3.changed(sheetState2) ? 256 : 128;
                $dirty5 |= i4;
            } else {
                sheetState2 = sheetState;
            }
            $dirty5 |= i4;
        } else {
            sheetState2 = sheetState;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i5 = $composer3.changed(shape2) ? 2048 : 1024;
                $dirty5 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty5 |= i5;
        } else {
            shape2 = shape;
        }
        if (($changed & 57344) == 0) {
            $dirty5 |= ((i & 16) == 0 && $composer3.changed(containerColor)) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                contentColor2 = contentColor;
                int i6 = $composer3.changed(contentColor2) ? 131072 : 65536;
                $dirty5 |= i6;
            } else {
                contentColor2 = contentColor;
            }
            $dirty5 |= i6;
        } else {
            contentColor2 = contentColor;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty5 |= 1572864;
            f = tonalElevation;
        } else if (($changed & 3670016) == 0) {
            f = tonalElevation;
            $dirty5 |= $composer3.changed(f) ? 1048576 : 524288;
        } else {
            f = tonalElevation;
        }
        if (($changed & 29360128) == 0) {
            if ((i & 128) == 0) {
                $dirty4 = $dirty5;
                int i8 = $composer3.changed(scrimColor) ? 8388608 : 4194304;
                $dirty = $dirty4 | i8;
            } else {
                $dirty4 = $dirty5;
            }
            $dirty = $dirty4 | i8;
        } else {
            $dirty = $dirty5;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty |= $composer3.changedInstance(dragHandle) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) == 0) {
            if ((1879048192 & $changed) == 0) {
                i2 = $composer3.changedInstance(content) ? 536870912 : 268435456;
            }
            $dirty2 = $dirty;
            if (($dirty2 & 1533916891) == 306783378 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        sheetState3 = rememberModalBottomSheetState(false, null, $composer3, 0, 3);
                        $dirty2 &= -897;
                    } else {
                        sheetState3 = sheetState2;
                    }
                    if ((i & 8) != 0) {
                        shape3 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer3, 6);
                        $dirty2 &= -7169;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 16) != 0) {
                        containerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer3, 6);
                        $dirty2 &= -57345;
                    } else {
                        containerColor2 = containerColor;
                    }
                    if ((i & 32) != 0) {
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty2 >> 12) & 14);
                        $dirty2 &= -458753;
                    }
                    if (i7 != 0) {
                        tonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        tonalElevation2 = f;
                    }
                    if ((i & 128) != 0) {
                        scrimColor2 = BottomSheetDefaults.INSTANCE.getScrimColor($composer3, 6);
                        $dirty2 &= -29360129;
                    } else {
                        scrimColor2 = scrimColor;
                    }
                    if (i9 != 0) {
                        modifier3 = modifier2;
                        tonalElevation3 = tonalElevation2;
                        containerColor3 = containerColor2;
                        contentColor3 = contentColor2;
                        dragHandle2 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1450getLambda2$material3_release();
                        scrimColor3 = scrimColor2;
                        sheetState4 = sheetState3;
                        $dirty3 = $dirty2;
                        shape4 = shape3;
                    } else {
                        modifier3 = modifier2;
                        dragHandle2 = dragHandle;
                        $dirty3 = $dirty2;
                        tonalElevation3 = tonalElevation2;
                        containerColor3 = containerColor2;
                        contentColor3 = contentColor2;
                        scrimColor3 = scrimColor2;
                        sheetState4 = sheetState3;
                        shape4 = shape3;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                    }
                    if ((i & 128) != 0) {
                        modifier3 = modifier;
                        containerColor3 = containerColor;
                        scrimColor3 = scrimColor;
                        dragHandle2 = dragHandle;
                        $dirty3 = (-29360129) & $dirty2;
                        sheetState4 = sheetState2;
                        shape4 = shape2;
                        contentColor3 = contentColor2;
                        tonalElevation3 = f;
                    } else {
                        modifier3 = modifier;
                        containerColor3 = containerColor;
                        scrimColor3 = scrimColor;
                        dragHandle2 = dragHandle;
                        $dirty3 = $dirty2;
                        sheetState4 = sheetState2;
                        shape4 = shape2;
                        contentColor3 = contentColor2;
                        tonalElevation3 = f;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1311611302, $dirty3, -1, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:281)");
                }
                $composer2 = $composer3;
                m1612ModalBottomSheetEP0qOeE(onDismissRequest, modifier3, sheetState4, shape4, containerColor3, contentColor3, tonalElevation3, scrimColor3, dragHandle2, null, content, $composer2, ($dirty3 & 14) | ($dirty3 & 112) | ($dirty3 & 896) | ($dirty3 & 7168) | (57344 & $dirty3) | (458752 & $dirty3) | ($dirty3 & 3670016) | ($dirty3 & 29360128) | ($dirty3 & 234881024), ($dirty3 >> 27) & 14, 512);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier3 = modifier;
                containerColor3 = containerColor;
                scrimColor3 = scrimColor;
                dragHandle2 = dragHandle;
                sheetState4 = sheetState2;
                shape4 = shape2;
                contentColor3 = contentColor2;
                $composer2 = $composer3;
                tonalElevation3 = f;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final SheetState sheetState5 = sheetState4;
            final Shape shape5 = shape4;
            final long j = containerColor3;
            final long j2 = contentColor3;
            final float f2 = tonalElevation3;
            final long j3 = scrimColor3;
            final Function2 function2 = dragHandle2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$5
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
                    ModalBottomSheet_androidKt.m1613ModalBottomSheetxOkiWaM(onDismissRequest, modifier4, sheetState5, shape5, j, j2, f2, j3, function2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty |= i2;
        $dirty2 = $dirty;
        if (($dirty2 & 1533916891) == 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    sheetState3 = rememberModalBottomSheetState(false, null, $composer3, 0, 3);
                    $dirty2 &= -897;
                } else {
                    sheetState3 = sheetState2;
                }
                if ((i & 8) != 0) {
                    shape3 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    containerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 32) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty2 >> 12) & 14);
                    $dirty2 &= -458753;
                }
                if (i7 != 0) {
                    tonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                } else {
                    tonalElevation2 = f;
                }
                if ((i & 128) != 0) {
                    scrimColor2 = BottomSheetDefaults.INSTANCE.getScrimColor($composer3, 6);
                    $dirty2 &= -29360129;
                } else {
                    scrimColor2 = scrimColor;
                }
                if (i9 != 0) {
                    modifier3 = modifier2;
                    tonalElevation3 = tonalElevation2;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    dragHandle2 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1450getLambda2$material3_release();
                    scrimColor3 = scrimColor2;
                    sheetState4 = sheetState3;
                    $dirty3 = $dirty2;
                    shape4 = shape3;
                } else {
                    modifier3 = modifier2;
                    dragHandle2 = dragHandle;
                    $dirty3 = $dirty2;
                    tonalElevation3 = tonalElevation2;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    scrimColor3 = scrimColor2;
                    sheetState4 = sheetState3;
                    shape4 = shape3;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    sheetState3 = rememberModalBottomSheetState(false, null, $composer3, 0, 3);
                    $dirty2 &= -897;
                } else {
                    sheetState3 = sheetState2;
                }
                if ((i & 8) != 0) {
                    shape3 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    containerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 32) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty2 >> 12) & 14);
                    $dirty2 &= -458753;
                }
                if (i7 != 0) {
                    tonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                } else {
                    tonalElevation2 = f;
                }
                if ((i & 128) != 0) {
                    scrimColor2 = BottomSheetDefaults.INSTANCE.getScrimColor($composer3, 6);
                    $dirty2 &= -29360129;
                } else {
                    scrimColor2 = scrimColor;
                }
                if (i9 != 0) {
                    modifier3 = modifier2;
                    tonalElevation3 = tonalElevation2;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    dragHandle2 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1450getLambda2$material3_release();
                    scrimColor3 = scrimColor2;
                    sheetState4 = sheetState3;
                    $dirty3 = $dirty2;
                    shape4 = shape3;
                } else {
                    modifier3 = modifier2;
                    dragHandle2 = dragHandle;
                    $dirty3 = $dirty2;
                    tonalElevation3 = tonalElevation2;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    scrimColor3 = scrimColor2;
                    sheetState4 = sheetState3;
                    shape4 = shape3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1311611302, $dirty3, -1, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:281)");
            }
            $composer2 = $composer3;
            m1612ModalBottomSheetEP0qOeE(onDismissRequest, modifier3, sheetState4, shape4, containerColor3, contentColor3, tonalElevation3, scrimColor3, dragHandle2, null, content, $composer2, ($dirty3 & 14) | ($dirty3 & 112) | ($dirty3 & 896) | ($dirty3 & 7168) | (57344 & $dirty3) | (458752 & $dirty3) | ($dirty3 & 3670016) | ($dirty3 & 29360128) | ($dirty3 & 234881024), ($dirty3 >> 27) & 14, 512);
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
                    sheetState3 = rememberModalBottomSheetState(false, null, $composer3, 0, 3);
                    $dirty2 &= -897;
                } else {
                    sheetState3 = sheetState2;
                }
                if ((i & 8) != 0) {
                    shape3 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    containerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 32) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty2 >> 12) & 14);
                    $dirty2 &= -458753;
                }
                if (i7 != 0) {
                    tonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                } else {
                    tonalElevation2 = f;
                }
                if ((i & 128) != 0) {
                    scrimColor2 = BottomSheetDefaults.INSTANCE.getScrimColor($composer3, 6);
                    $dirty2 &= -29360129;
                } else {
                    scrimColor2 = scrimColor;
                }
                if (i9 != 0) {
                    modifier3 = modifier2;
                    tonalElevation3 = tonalElevation2;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    dragHandle2 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1450getLambda2$material3_release();
                    scrimColor3 = scrimColor2;
                    sheetState4 = sheetState3;
                    $dirty3 = $dirty2;
                    shape4 = shape3;
                } else {
                    modifier3 = modifier2;
                    dragHandle2 = dragHandle;
                    $dirty3 = $dirty2;
                    tonalElevation3 = tonalElevation2;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    scrimColor3 = scrimColor2;
                    sheetState4 = sheetState3;
                    shape4 = shape3;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    sheetState3 = rememberModalBottomSheetState(false, null, $composer3, 0, 3);
                    $dirty2 &= -897;
                } else {
                    sheetState3 = sheetState2;
                }
                if ((i & 8) != 0) {
                    shape3 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    containerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 32) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty2 >> 12) & 14);
                    $dirty2 &= -458753;
                }
                if (i7 != 0) {
                    tonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                } else {
                    tonalElevation2 = f;
                }
                if ((i & 128) != 0) {
                    scrimColor2 = BottomSheetDefaults.INSTANCE.getScrimColor($composer3, 6);
                    $dirty2 &= -29360129;
                } else {
                    scrimColor2 = scrimColor;
                }
                if (i9 != 0) {
                    modifier3 = modifier2;
                    tonalElevation3 = tonalElevation2;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    dragHandle2 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1450getLambda2$material3_release();
                    scrimColor3 = scrimColor2;
                    sheetState4 = sheetState3;
                    $dirty3 = $dirty2;
                    shape4 = shape3;
                } else {
                    modifier3 = modifier2;
                    dragHandle2 = dragHandle;
                    $dirty3 = $dirty2;
                    tonalElevation3 = tonalElevation2;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    scrimColor3 = scrimColor2;
                    sheetState4 = sheetState3;
                    shape4 = shape3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1311611302, $dirty3, -1, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:281)");
            }
            $composer2 = $composer3;
            m1612ModalBottomSheetEP0qOeE(onDismissRequest, modifier3, sheetState4, shape4, containerColor3, contentColor3, tonalElevation3, scrimColor3, dragHandle2, null, content, $composer2, ($dirty3 & 14) | ($dirty3 & 112) | ($dirty3 & 896) | ($dirty3 & 7168) | (57344 & $dirty3) | (458752 & $dirty3) | ($dirty3 & 3670016) | ($dirty3 & 29360128) | ($dirty3 & 234881024), ($dirty3 >> 27) & 14, 512);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final SheetState sheetState6 = sheetState4;
        final Shape shape6 = shape4;
        final long j4 = containerColor3;
        final long j5 = contentColor3;
        final float f3 = tonalElevation3;
        final long j6 = scrimColor3;
        final Function2<? super Composer, ? super Integer, Unit> function3 = dragHandle2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$5
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
                ModalBottomSheet_androidKt.m1613ModalBottomSheetxOkiWaM(onDismissRequest, modifier5, sheetState6, shape6, j4, j5, f3, j6, function3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static final SheetState rememberModalBottomSheetState(boolean skipPartiallyExpanded, Function1<? super SheetValue, Boolean> function1, Composer $composer, int $changed, int i) {
        $composer.startReplaceableGroup(-1261794383);
        ComposerKt.sourceInformation($composer, "C(rememberModalBottomSheetState)P(1)318@14402L69:ModalBottomSheet.android.kt#uh7d8r");
        if ((i & 1) != 0) {
            skipPartiallyExpanded = false;
        }
        if ((i & 2) != 0) {
            Function1 confirmValueChange = new Function1<SheetValue, Boolean>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt.rememberModalBottomSheetState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(SheetValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
            function1 = confirmValueChange;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1261794383, $changed, -1, "androidx.compose.material3.rememberModalBottomSheetState (ModalBottomSheet.android.kt:315)");
        }
        SheetState sheetStateRememberSheetState = SheetDefaultsKt.rememberSheetState(skipPartiallyExpanded, function1, SheetValue.Hidden, false, $composer, ($changed & 14) | 384 | ($changed & 112), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return sheetStateRememberSheetState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Scrim-3J-VO9M, reason: not valid java name */
    public static final void m1614Scrim3JVO9M(final long color, final Function0<Unit> function0, final boolean visible, Composer $composer, final int $changed) {
        Modifier.Companion dismissSheet;
        Object value$iv$iv;
        Composer $composer2 = $composer.startRestartGroup(1053897700);
        ComposerKt.sourceInformation($composer2, "C(Scrim)P(0:c#ui.graphics.Color)327@14631L121,346@15192L62,342@15086L168:ModalBottomSheet.android.kt#uh7d8r");
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
                ComposerKt.traceEventStart(1053897700, $dirty2, -1, "androidx.compose.material3.Scrim (ModalBottomSheet.android.kt:321)");
            }
            if (color != Color.INSTANCE.m3007getUnspecified0d7_KjU()) {
                final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(visible ? 1.0f : 0.0f, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer2, 48, 28);
                $composer2.startReplaceableGroup(-1858721447);
                ComposerKt.sourceInformation($composer2, "333@14864L124");
                if (visible) {
                    Modifier.Companion companion = Modifier.INSTANCE;
                    int i = ($dirty2 >> 3) & 14;
                    $composer2.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                    boolean invalid$iv$iv = $composer2.changed(function0);
                    Object it$iv$iv = $composer2.rememberedValue();
                    if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = new ModalBottomSheet_androidKt$Scrim$dismissSheet$1$1(function0, null);
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    dismissSheet = SemanticsModifierKt.clearAndSetSemantics(SuspendingPointerInputFilterKt.pointerInput(companion, function0, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv), new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$Scrim$dismissSheet$2
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
                } else {
                    dismissSheet = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissSheet);
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
                value$iv$iv2 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$Scrim$1$1
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
                        DrawScope.CC.m3522drawRectnJ9OG0$default(Canvas, color, 0L, 0L, ModalBottomSheet_androidKt.Scrim_3J_VO9M$lambda$2(stateAnimateFloatAsState), null, null, 0, 118, null);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$Scrim$2
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
                ModalBottomSheet_androidKt.m1614Scrim3JVO9M(color, function0, visible, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Scrim_3J_VO9M$lambda$2(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier modalBottomSheetSwipeable(Modifier $this$modalBottomSheetSwipeable, final SheetState sheetState, AnchorChangeHandler<SheetValue> anchorChangeHandler, final float screenHeight, Function2<? super CoroutineScope, ? super Float, Unit> function2) {
        return SwipeableV2Kt.swipeAnchors(DraggableKt.draggable($this$modalBottomSheetSwipeable, sheetState.getSwipeableState$material3_release().getSwipeDraggableState(), Orientation.Vertical, (188 & 4) != 0 ? true : sheetState.isVisible(), (188 & 8) != 0 ? null : null, (188 & 16) != 0 ? false : sheetState.getSwipeableState$material3_release().isAnimationRunning(), (188 & 32) != 0 ? new DraggableKt.C01841(null) : null, (188 & 64) != 0 ? new DraggableKt.AnonymousClass2(null) : new C03961(function2), (188 & 128) != 0 ? false : false), sheetState.getSwipeableState$material3_release(), SetsKt.setOf((Object[]) new SheetValue[]{SheetValue.Hidden, SheetValue.PartiallyExpanded, SheetValue.Expanded}), anchorChangeHandler, new Function2<SheetValue, IntSize, Float>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt.modalBottomSheetSwipeable.2

            /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$modalBottomSheetSwipeable$2$WhenMappings */
            /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[SheetValue.values().length];
                    try {
                        iArr[SheetValue.Hidden.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[SheetValue.Expanded.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Float invoke(SheetValue sheetValue, IntSize intSize) {
                return m1618invokeO0kMr_c(sheetValue, intSize.getPackedValue());
            }

            /* JADX INFO: renamed from: invoke-O0kMr_c, reason: not valid java name */
            public final Float m1618invokeO0kMr_c(SheetValue value, long sheetSize) {
                Intrinsics.checkNotNullParameter(value, "value");
                switch (WhenMappings.$EnumSwitchMapping$0[value.ordinal()]) {
                    case 1:
                        return Float.valueOf(screenHeight);
                    case 2:
                        if (IntSize.m5433getHeightimpl(sheetSize) >= screenHeight / 2 && !sheetState.getSkipPartiallyExpanded()) {
                            return Float.valueOf(screenHeight / 2.0f);
                        }
                        return null;
                    case 3:
                        if (IntSize.m5433getHeightimpl(sheetSize) != 0) {
                            return Float.valueOf(Math.max(0.0f, screenHeight - IntSize.m5433getHeightimpl(sheetSize)));
                        }
                        return null;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
        });
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$modalBottomSheetSwipeable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* synthetic */ class C03961 extends FunctionReferenceImpl implements Function3<CoroutineScope, Float, Continuation<? super Unit>, Object>, SuspendFunction {
        C03961(Object obj) {
            super(3, obj, Intrinsics.Kotlin.class, "suspendConversion0", "modalBottomSheetSwipeable$suspendConversion0(Lkotlin/jvm/functions/Function2;Lkotlinx/coroutines/CoroutineScope;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Float f, Continuation<? super Unit> continuation) {
            return invoke(coroutineScope, f.floatValue(), continuation);
        }

        public final Object invoke(CoroutineScope p0, float p1, Continuation<? super Unit> continuation) {
            return ModalBottomSheet_androidKt.modalBottomSheetSwipeable$suspendConversion0((Function2) this.receiver, p0, p1, continuation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object modalBottomSheetSwipeable$suspendConversion0(Function2 $this$modalBottomSheetSwipeable_u24suspendConversion0, CoroutineScope p0, float p1, Continuation $completion) {
        $this$modalBottomSheetSwipeable_u24suspendConversion0.invoke(p0, Boxing.boxFloat(p1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnchorChangeHandler<SheetValue> ModalBottomSheetAnchorChangeHandler(final SheetState state, final Function2<? super SheetValue, ? super Float, Unit> function2, final Function1<? super SheetValue, Unit> function1) {
        return new AnchorChangeHandler<SheetValue>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt.ModalBottomSheetAnchorChangeHandler.1

            /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetAnchorChangeHandler$1$WhenMappings */
            /* JADX INFO: compiled from: ModalBottomSheet.android.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[SheetValue.values().length];
                    try {
                        iArr[SheetValue.Hidden.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[SheetValue.Expanded.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // androidx.compose.material3.AnchorChangeHandler
            public final void onAnchorsChanged(SheetValue previousTarget, Map<SheetValue, Float> previousAnchors, Map<SheetValue, Float> newAnchors) {
                SheetValue sheetValue;
                Intrinsics.checkNotNullParameter(previousTarget, "previousTarget");
                Intrinsics.checkNotNullParameter(previousAnchors, "previousAnchors");
                Intrinsics.checkNotNullParameter(newAnchors, "newAnchors");
                Float previousTargetOffset = previousAnchors.get(previousTarget);
                switch (WhenMappings.$EnumSwitchMapping$0[previousTarget.ordinal()]) {
                    case 1:
                        sheetValue = SheetValue.Hidden;
                        break;
                    case 2:
                    case 3:
                        boolean hasPartiallyExpandedState = newAnchors.containsKey(SheetValue.PartiallyExpanded);
                        if (!hasPartiallyExpandedState) {
                            sheetValue = !newAnchors.containsKey(SheetValue.Expanded) ? SheetValue.Hidden : SheetValue.Expanded;
                        } else {
                            sheetValue = SheetValue.PartiallyExpanded;
                        }
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                SheetValue newTarget = sheetValue;
                float newTargetOffset = ((Number) MapsKt.getValue(newAnchors, newTarget)).floatValue();
                if (!Intrinsics.areEqual(newTargetOffset, previousTargetOffset)) {
                    if (state.getSwipeableState$material3_release().isAnimationRunning() || previousAnchors.isEmpty()) {
                        function2.invoke(newTarget, Float.valueOf(state.getSwipeableState$material3_release().getLastVelocity()));
                    } else {
                        function1.invoke(newTarget);
                    }
                }
            }
        };
    }

    public static final void ModalBottomSheetPopup(final Function0<Unit> onDismissRequest, final WindowInsets windowInsets, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed) {
        Object value$iv$iv;
        Intrinsics.checkNotNullParameter(onDismissRequest, "onDismissRequest");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-627217336);
        ComposerKt.sourceInformation($composer2, "C(ModalBottomSheetPopup)P(1,2)420@17875L7,421@17896L38,422@17963L28,423@18018L29,424@18081L621,446@18708L217:ModalBottomSheet.android.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changedInstance(onDismissRequest) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(windowInsets) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 731) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-627217336, $dirty2, -1, "androidx.compose.material3.ModalBottomSheetPopup (ModalBottomSheet.android.kt:415)");
            }
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            View view = (View) objConsume;
            UUID id = (UUID) RememberSaveableKt.m2617rememberSaveable(new Object[0], (Saver) null, (String) null, (Function0) new Function0<UUID>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetPopup$id$1
                @Override // kotlin.jvm.functions.Function0
                public final UUID invoke() {
                    return UUID.randomUUID();
                }
            }, $composer2, 3080, 6);
            CompositionContext parentComposition = ComposablesKt.rememberCompositionContext($composer2, 0);
            final State currentContent$delegate = SnapshotStateKt.rememberUpdatedState(content, $composer2, ($dirty2 >> 6) & 14);
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Intrinsics.checkNotNullExpressionValue(id, "id");
                ModalBottomSheetWindow $this$ModalBottomSheetPopup_u24lambda_u247_u24lambda_u246 = new ModalBottomSheetWindow(onDismissRequest, view, id);
                $this$ModalBottomSheetPopup_u24lambda_u247_u24lambda_u246.setCustomContent(parentComposition, ComposableLambdaKt.composableLambdaInstance(861223805, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetPopup$modalBottomSheetWindow$1$1$1
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
                        ComposerKt.sourceInformation($composer3, "C433@18369L285:ModalBottomSheet.android.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(861223805, $changed2, -1, "androidx.compose.material3.ModalBottomSheetPopup.<anonymous>.<anonymous>.<anonymous> (ModalBottomSheet.android.kt:432)");
                            }
                            Modifier modifier$iv = WindowInsetsPadding_androidKt.imePadding(WindowInsetsPaddingKt.windowInsetsPadding(SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetPopup$modalBottomSheetWindow$1$1$1.1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semantics) {
                                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                                    SemanticsPropertiesKt.popup(semantics);
                                }
                            }, 1, null), windowInsets));
                            State<Function2<Composer, Integer, Unit>> state = currentContent$delegate;
                            $composer3.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer3.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume2;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume3;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume4 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume4;
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
                            int i = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i2 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 321876634, "C439@18616L16:ModalBottomSheet.android.kt#uh7d8r");
                            ModalBottomSheet_androidKt.ModalBottomSheetPopup$lambda$5(state).invoke($composer3, 0);
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
                }));
                value$iv$iv = $this$ModalBottomSheetPopup_u24lambda_u247_u24lambda_u246;
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            final ModalBottomSheetWindow modalBottomSheetWindow = (ModalBottomSheetWindow) value$iv$iv;
            EffectsKt.DisposableEffect(modalBottomSheetWindow, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt.ModalBottomSheetPopup.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                    Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                    modalBottomSheetWindow.show();
                    final ModalBottomSheetWindow modalBottomSheetWindow2 = modalBottomSheetWindow;
                    return new DisposableEffectResult() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetPopup$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            modalBottomSheetWindow2.disposeComposition();
                            modalBottomSheetWindow2.dismiss();
                        }
                    };
                }
            }, $composer2, 8);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt.ModalBottomSheetPopup.2
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
                ModalBottomSheet_androidKt.ModalBottomSheetPopup(onDismissRequest, windowInsets, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2<Composer, Integer, Unit> ModalBottomSheetPopup$lambda$5(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }
}
