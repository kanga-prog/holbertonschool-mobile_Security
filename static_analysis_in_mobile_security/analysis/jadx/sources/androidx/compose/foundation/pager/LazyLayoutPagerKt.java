package androidx.compose.foundation.pager;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ClipScrollableContainerKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.OverscrollKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.reflect.KProperty0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: LazyLayoutPager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÙ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162#\u0010\u0017\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 21\u0010!\u001a-\u0012\u0004\u0012\u00020#\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010\"¢\u0006\u0002\b%¢\u0006\u0002\b&H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010(\u001a\u0081\u0001\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010\u0004\u001a\u00020\u000521\u0010!\u001a-\u0012\u0004\u0012\u00020#\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010\"¢\u0006\u0002\b%¢\u0006\u0002\b&2#\u0010\u0017\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00182\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00100*H\u0003¢\u0006\u0002\u0010-\u001a\u0014\u0010.\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006/"}, d2 = {"Pager", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/pager/PagerState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "flingBehavior", "Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "userScrollEnabled", "beyondBoundsPageCount", "", "pageSpacing", "Landroidx/compose/ui/unit/Dp;", "pageSize", "Landroidx/compose/foundation/pager/PageSize;", "pageNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "key", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "index", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "pageContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/pager/PagerScope;", "page", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "Pager-fs30GE4", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;ZIFLandroidx/compose/foundation/pager/PageSize;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "rememberPagerItemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "pageCount", "(Landroidx/compose/foundation/pager/PagerState;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function0;", "dragDirectionDetector", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutPagerKt {
    /* JADX WARN: Code duplicated, block: B:40:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:43:0x0210  */
    /* JADX WARN: Code duplicated, block: B:45:0x021a  */
    /* JADX WARN: Code duplicated, block: B:46:0x021c  */
    /* JADX WARN: Code duplicated, block: B:48:0x0226  */
    /* JADX WARN: Code duplicated, block: B:51:0x0236  */
    /* JADX WARN: Code duplicated, block: B:52:0x0238  */
    /* JADX WARN: Code duplicated, block: B:55:0x0311  */
    /* JADX WARN: Code duplicated, block: B:58:0x031a  */
    /* JADX WARN: Code duplicated, block: B:59:0x0321  */
    /* JADX INFO: renamed from: Pager-fs30GE4, reason: not valid java name */
    public static final void m700Pagerfs30GE4(final Modifier modifier, final PagerState state, final PaddingValues contentPadding, final boolean reverseLayout, final Orientation orientation, final SnapFlingBehavior flingBehavior, final boolean userScrollEnabled, int beyondBoundsPageCount, float pageSpacing, final PageSize pageSize, final NestedScrollConnection pageNestedScrollConnection, final Function1<? super Integer, ? extends Object> function1, final Alignment.Horizontal horizontalAlignment, final Alignment.Vertical verticalAlignment, final Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> pageContent, Composer $composer, final int $changed, final int $changed1, final int i) {
        int beyondBoundsPageCount2;
        float pageSpacing2;
        int beyondBoundsPageCount3;
        Object value$iv$iv;
        final int beyondBoundsPageCount4;
        boolean invalid$iv$iv;
        Object value$iv$iv2;
        boolean invalid$iv$iv2;
        Object value$iv$iv3;
        Orientation orientation2;
        Modifier.Companion companionPagerSemantics;
        boolean z;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        boolean z2;
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(contentPadding, "contentPadding");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        Intrinsics.checkNotNullParameter(flingBehavior, "flingBehavior");
        Intrinsics.checkNotNullParameter(pageSize, "pageSize");
        Intrinsics.checkNotNullParameter(pageNestedScrollConnection, "pageNestedScrollConnection");
        Intrinsics.checkNotNullParameter(horizontalAlignment, "horizontalAlignment");
        Intrinsics.checkNotNullParameter(verticalAlignment, "verticalAlignment");
        Intrinsics.checkNotNullParameter(pageContent, "pageContent");
        Composer $composer2 = $composer.startRestartGroup(-301644943);
        ComposerKt.sourceInformation($composer2, "C(Pager)P(5,12,1,11,6,2,13!1,10:c#ui.unit.Dp,9,8,4!1,14)95@4489L18,101@4652L19,97@4537L134,114@5148L19,103@4697L477,117@5205L94,127@5488L115,138@5788L276,146@6128L161,156@6513L7,133@5609L1489:LazyLayoutPager.kt#g6yjnt");
        if ((i & 128) != 0) {
            beyondBoundsPageCount2 = 0;
        } else {
            beyondBoundsPageCount2 = beyondBoundsPageCount;
        }
        if ((i & 256) == 0) {
            pageSpacing2 = pageSpacing;
        } else {
            pageSpacing2 = Dp.m5274constructorimpl(0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-301644943, $changed, $changed1, "androidx.compose.foundation.pager.Pager (LazyLayoutPager.kt:58)");
        }
        if (!(beyondBoundsPageCount2 >= 0)) {
            throw new IllegalArgumentException(("beyondBoundsPageCount should be greater than or equal to 0, you selected " + beyondBoundsPageCount2).toString());
        }
        OverscrollEffect overscrollEffect = ScrollableDefaults.INSTANCE.overscrollEffect($composer2, 6);
        int i2 = ($changed >> 3) & 14;
        $composer2.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean invalid$iv$iv3 = $composer2.changed(state);
        Object it$iv$iv = $composer2.rememberedValue();
        if (!invalid$iv$iv3) {
            beyondBoundsPageCount3 = beyondBoundsPageCount2;
            if (it$iv$iv != Composer.INSTANCE.getEmpty()) {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            beyondBoundsPageCount4 = beyondBoundsPageCount3;
            Function0<PagerLazyLayoutItemProvider> function0RememberPagerItemProviderLambda = rememberPagerItemProviderLambda(state, pageContent, function1, (Function0) value$iv$iv, $composer2, (($changed1 << 3) & 896) | (($changed >> 3) & 14) | (($changed1 >> 9) & 112));
            int i3 = ($changed >> 3) & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(state);
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (!invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = (Function0) new Function0<Integer>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$measurePolicy$1$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Integer invoke() {
                        return Integer.valueOf(state.getPageCount());
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2M713rememberPagerMeasurePolicyBxUkNYg = PagerMeasurePolicyKt.m713rememberPagerMeasurePolicyBxUkNYg(function0RememberPagerItemProviderLambda, state, contentPadding, reverseLayout, orientation, beyondBoundsPageCount4, pageSpacing2, pageSize, horizontalAlignment, verticalAlignment, (Function0) value$iv$iv2, $composer2, ($changed & 112) | ($changed & 896) | ($changed & 7168) | ($changed & 57344) | (($changed >> 6) & 458752) | (($changed >> 6) & 3670016) | (($changed >> 6) & 29360128) | (($changed1 << 18) & 234881024) | (1879048192 & ($changed1 << 18)), 0);
            int i4 = (($changed >> 15) & 14) | ($changed & 112);
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer2.changed(flingBehavior) | $composer2.changed(state);
            Object it$iv$iv3 = $composer2.rememberedValue();
            if (!invalid$iv$iv2 || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv3 = new PagerWrapperFlingBehavior(flingBehavior, state);
                $composer2.updateRememberedValue(value$iv$iv3);
            } else {
                value$iv$iv3 = it$iv$iv3;
            }
            $composer2.endReplaceableGroup();
            PagerWrapperFlingBehavior pagerFlingBehavior = (PagerWrapperFlingBehavior) value$iv$iv3;
            $composer2.startReplaceableGroup(1445586192);
            ComposerKt.sourceInformation($composer2, "122@5368L58");
            if (userScrollEnabled) {
                Modifier.Companion companion = Modifier.INSTANCE;
                orientation2 = orientation;
                if (orientation2 == Orientation.Vertical) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                companionPagerSemantics = PagerKt.pagerSemantics(companion, state, z2, $composer2, ($changed & 112) | 6);
            } else {
                orientation2 = orientation;
                companionPagerSemantics = Modifier.INSTANCE;
            }
            $composer2.endReplaceableGroup();
            Modifier pagerSemantics = companionPagerSemantics;
            if (orientation2 == Orientation.Vertical) {
                z = true;
            } else {
                z = false;
            }
            LazyLayoutSemanticState semanticState = PagerSemanticsKt.rememberPagerSemanticState(state, reverseLayout, z, $composer2, (($changed >> 3) & 14) | (($changed >> 6) & 112));
            Orientation orientation3 = orientation2;
            Modifier modifierOverscroll = OverscrollKt.overscroll(PagerBeyondBoundsModifierKt.pagerBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()).then(pagerSemantics), function0RememberPagerItemProviderLambda, semanticState, orientation, userScrollEnabled, reverseLayout, $composer2, (($changed >> 3) & 7168) | (($changed >> 6) & 57344) | (($changed << 6) & 458752)), orientation3), state, beyondBoundsPageCount4, reverseLayout, orientation, $composer2, ($changed & 112) | (($changed >> 15) & 896) | ($changed & 7168) | ($changed & 57344)), overscrollEffect);
            ScrollableDefaults scrollableDefaults = ScrollableDefaults.INSTANCE;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyLayoutKt.LazyLayout(function0RememberPagerItemProviderLambda, NestedScrollModifierKt.nestedScroll$default(dragDirectionDetector(ScrollableKt.scrollable(modifierOverscroll, state, orientation, overscrollEffect, userScrollEnabled, scrollableDefaults.reverseDirection((LayoutDirection) objConsume, orientation3, reverseLayout), pagerFlingBehavior, state.getInternalInteractionSource()), state), pageNestedScrollConnection, null, 2, null), state.getPrefetchState(), function2M713rememberPagerMeasurePolicyBxUkNYg, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final float f = pageSpacing2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$2
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
                    LazyLayoutPagerKt.m700Pagerfs30GE4(modifier, state, contentPadding, reverseLayout, orientation, flingBehavior, userScrollEnabled, beyondBoundsPageCount4, f, pageSize, pageNestedScrollConnection, function1, horizontalAlignment, verticalAlignment, pageContent, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        beyondBoundsPageCount3 = beyondBoundsPageCount2;
        value$iv$iv = (Function0) new Function0<Integer>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$pagerItemProvider$1$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(state.getPageCount());
            }
        };
        $composer2.updateRememberedValue(value$iv$iv);
        $composer2.endReplaceableGroup();
        beyondBoundsPageCount4 = beyondBoundsPageCount3;
        Function0<PagerLazyLayoutItemProvider> function0RememberPagerItemProviderLambda2 = rememberPagerItemProviderLambda(state, pageContent, function1, (Function0) value$iv$iv, $composer2, (($changed1 << 3) & 896) | (($changed >> 3) & 14) | (($changed1 >> 9) & 112));
        int i5 = ($changed >> 3) & 14;
        $composer2.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
        invalid$iv$iv = $composer2.changed(state);
        Object it$iv$iv4 = $composer2.rememberedValue();
        if (!invalid$iv$iv) {
        }
        value$iv$iv2 = (Function0) new Function0<Integer>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$measurePolicy$1$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(state.getPageCount());
            }
        };
        $composer2.updateRememberedValue(value$iv$iv2);
        $composer2.endReplaceableGroup();
        Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2M713rememberPagerMeasurePolicyBxUkNYg2 = PagerMeasurePolicyKt.m713rememberPagerMeasurePolicyBxUkNYg(function0RememberPagerItemProviderLambda2, state, contentPadding, reverseLayout, orientation, beyondBoundsPageCount4, pageSpacing2, pageSize, horizontalAlignment, verticalAlignment, (Function0) value$iv$iv2, $composer2, ($changed & 112) | ($changed & 896) | ($changed & 7168) | ($changed & 57344) | (($changed >> 6) & 458752) | (($changed >> 6) & 3670016) | (($changed >> 6) & 29360128) | (($changed1 << 18) & 234881024) | (1879048192 & ($changed1 << 18)), 0);
        int i6 = (($changed >> 15) & 14) | ($changed & 112);
        $composer2.startReplaceableGroup(511388516);
        ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
        invalid$iv$iv2 = $composer2.changed(flingBehavior) | $composer2.changed(state);
        Object it$iv$iv5 = $composer2.rememberedValue();
        if (!invalid$iv$iv2) {
            value$iv$iv3 = new PagerWrapperFlingBehavior(flingBehavior, state);
            $composer2.updateRememberedValue(value$iv$iv3);
        } else {
            value$iv$iv3 = new PagerWrapperFlingBehavior(flingBehavior, state);
            $composer2.updateRememberedValue(value$iv$iv3);
        }
        $composer2.endReplaceableGroup();
        PagerWrapperFlingBehavior pagerFlingBehavior2 = (PagerWrapperFlingBehavior) value$iv$iv3;
        $composer2.startReplaceableGroup(1445586192);
        ComposerKt.sourceInformation($composer2, "122@5368L58");
        if (userScrollEnabled) {
            Modifier.Companion companion2 = Modifier.INSTANCE;
            orientation2 = orientation;
            if (orientation2 == Orientation.Vertical) {
                z2 = true;
            } else {
                z2 = false;
            }
            companionPagerSemantics = PagerKt.pagerSemantics(companion2, state, z2, $composer2, ($changed & 112) | 6);
        } else {
            orientation2 = orientation;
            companionPagerSemantics = Modifier.INSTANCE;
        }
        $composer2.endReplaceableGroup();
        Modifier pagerSemantics2 = companionPagerSemantics;
        if (orientation2 == Orientation.Vertical) {
            z = true;
        } else {
            z = false;
        }
        LazyLayoutSemanticState semanticState2 = PagerSemanticsKt.rememberPagerSemanticState(state, reverseLayout, z, $composer2, (($changed >> 3) & 14) | (($changed >> 6) & 112));
        Orientation orientation4 = orientation2;
        Modifier modifierOverscroll2 = OverscrollKt.overscroll(PagerBeyondBoundsModifierKt.pagerBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()).then(pagerSemantics2), function0RememberPagerItemProviderLambda2, semanticState2, orientation, userScrollEnabled, reverseLayout, $composer2, (($changed >> 3) & 7168) | (($changed >> 6) & 57344) | (($changed << 6) & 458752)), orientation4), state, beyondBoundsPageCount4, reverseLayout, orientation, $composer2, ($changed & 112) | (($changed >> 15) & 896) | ($changed & 7168) | ($changed & 57344)), overscrollEffect);
        ScrollableDefaults scrollableDefaults2 = ScrollableDefaults.INSTANCE;
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume2 = $composer2.consume(localLayoutDirection2);
        ComposerKt.sourceInformationMarkerEnd($composer2);
        LazyLayoutKt.LazyLayout(function0RememberPagerItemProviderLambda2, NestedScrollModifierKt.nestedScroll$default(dragDirectionDetector(ScrollableKt.scrollable(modifierOverscroll2, state, orientation, overscrollEffect, userScrollEnabled, scrollableDefaults2.reverseDirection((LayoutDirection) objConsume2, orientation4, reverseLayout), pagerFlingBehavior2, state.getInternalInteractionSource()), state), pageNestedScrollConnection, null, 2, null), state.getPrefetchState(), function2M713rememberPagerMeasurePolicyBxUkNYg2, $composer2, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final float f2 = pageSpacing2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$2
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
                LazyLayoutPagerKt.m700Pagerfs30GE4(modifier, state, contentPadding, reverseLayout, orientation, flingBehavior, userScrollEnabled, beyondBoundsPageCount4, f2, pageSize, pageNestedScrollConnection, function1, horizontalAlignment, verticalAlignment, pageContent, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    private static final Function0<PagerLazyLayoutItemProvider> rememberPagerItemProviderLambda(final PagerState state, Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, final Function1<? super Integer, ? extends Object> function1, final Function0<Integer> function0, Composer $composer, int $changed) {
        Object value$iv$iv;
        $composer.startReplaceableGroup(-1372505274);
        ComposerKt.sourceInformation($composer, "C(rememberPagerItemProviderLambda)P(3,1)240@9459L33,241@9504L659:LazyLayoutPager.kt#g6yjnt");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1372505274, $changed, -1, "androidx.compose.foundation.pager.rememberPagerItemProviderLambda (LazyLayoutPager.kt:234)");
        }
        final State latestContent = SnapshotStateKt.rememberUpdatedState(function4, $composer, ($changed >> 3) & 14);
        Object[] keys$iv = {state, latestContent, function1, function0};
        $composer.startReplaceableGroup(-568225417);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean invalid$iv = false;
        for (Object key$iv : keys$iv) {
            invalid$iv |= $composer.changed(key$iv);
        }
        Object it$iv$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
            final State intervalContentState = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0<PagerLayoutIntervalContent>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$intervalContentState$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final PagerLayoutIntervalContent invoke() {
                    return new PagerLayoutIntervalContent(latestContent.getValue(), function1, function0.invoke().intValue());
                }
            });
            final State itemProviderState = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0<PagerLazyLayoutItemProvider>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$itemProviderState$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final PagerLazyLayoutItemProvider invoke() {
                    PagerLayoutIntervalContent intervalContent = intervalContentState.getValue();
                    NearestRangeKeyIndexMap map = new NearestRangeKeyIndexMap(state.getNearestRange$foundation_release(), intervalContent);
                    return new PagerLazyLayoutItemProvider(state, intervalContent, map);
                }
            });
            value$iv$iv = new PropertyReference0Impl(itemProviderState) { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$1
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            };
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        $composer.endReplaceableGroup();
        KProperty0 kProperty0 = (KProperty0) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return kProperty0;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1, reason: invalid class name */
    /* JADX INFO: compiled from: LazyLayoutPager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1", f = "LazyLayoutPager.kt", i = {}, l = {265}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PagerState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PagerState pagerState, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$state = pagerState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$state, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: LazyLayoutPager.kt */
        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1", f = "LazyLayoutPager.kt", i = {}, l = {266}, m = "invokeSuspend", n = {}, s = {})
        static final class C00191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ PagerState $state;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00191(PointerInputScope pointerInputScope, PagerState pagerState, Continuation<? super C00191> continuation) {
                super(2, continuation);
                this.$$this$pointerInput = pointerInputScope;
                this.$state = pagerState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00191(this.$$this$pointerInput, this.$state, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: LazyLayoutPager.kt */
            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1$1", f = "LazyLayoutPager.kt", i = {0, 1, 1, 1}, l = {268, 271}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "downEvent", "upEventOrCancellation"}, s = {"L$0", "L$0", "L$1", "L$2"})
            static final class C00201 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PagerState $state;
                private /* synthetic */ Object L$0;
                Object L$1;
                Object L$2;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00201(PagerState pagerState, Continuation<? super C00201> continuation) {
                    super(2, continuation);
                    this.$state = pagerState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00201 c00201 = new C00201(this.$state, continuation);
                    c00201.L$0 = obj;
                    return c00201;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((C00201) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:20:0x0099  */
                /* JADX WARN: Code duplicated, block: B:23:0x00af A[LOOP:0: B:19:0x0097->B:23:0x00af, LOOP_END] */
                /* JADX WARN: Code duplicated, block: B:31:0x00ad A[SYNTHETIC] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x007f -> B:18:0x0087). Please report as a decompilation issue!!! */
                /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                    	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                    	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                    */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final java.lang.Object invokeSuspend(java.lang.Object r22) {
                    /*
                        Method dump skipped, instruction units count: 238
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.LazyLayoutPagerKt.AnonymousClass1.C00191.C00201.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (ForEachGestureKt.awaitEachGesture(this.$$this$pointerInput, new C00201(this.$state, null), this) == coroutine_suspended) {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                    this.label = 1;
                    if (CoroutineScopeKt.coroutineScope(new C00191($this$pointerInput, this.$state, null), this) == coroutine_suspended) {
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

    private static final Modifier dragDirectionDetector(Modifier $this$dragDirectionDetector, PagerState state) {
        return $this$dragDirectionDetector.then(SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, state, new AnonymousClass1(state, null)));
    }
}
