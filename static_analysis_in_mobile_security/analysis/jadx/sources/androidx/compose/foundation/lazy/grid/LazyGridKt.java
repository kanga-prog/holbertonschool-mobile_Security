package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.ClipScrollableContainerKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.OverscrollKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyGrid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0098\u0001\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u001d\u0010\u0006\u001a\u0019\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\u0019¢\u0006\u0002\b\u000bH\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a#\u0010\u001c\u001a\u00020\u00012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010 \u001a\u008c\u0001\u0010!\u001a\u0019\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020#0\u0007¢\u0006\u0002\b\u000b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010\u0004\u001a\u00020\u00052\u001d\u0010\u0006\u001a\u0019\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\u0002\b\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0003ø\u0001\u0000¢\u0006\u0002\u0010$\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"LazyGrid", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "slots", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/foundation/lazy/grid/LazyGridSlots;", "Lkotlin/ExtensionFunctionType;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "isVertical", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/LazyGridScope;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/grid/LazyGridState;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "ScrollPositionUpdater", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/runtime/Composer;I)V", "rememberLazyGridMeasurePolicy", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/layout/MeasureResult;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/grid/LazyGridState;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/runtime/Composer;II)Lkotlin/jvm/functions/Function2;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyGridKt {
    /* JADX WARN: Code duplicated, block: B:101:0x014a  */
    /* JADX WARN: Code duplicated, block: B:103:0x014f  */
    /* JADX WARN: Code duplicated, block: B:105:0x0155  */
    /* JADX WARN: Code duplicated, block: B:106:0x0158  */
    /* JADX WARN: Code duplicated, block: B:109:0x015f  */
    /* JADX WARN: Code duplicated, block: B:110:0x0162  */
    /* JADX WARN: Code duplicated, block: B:112:0x0166  */
    /* JADX WARN: Code duplicated, block: B:114:0x016c  */
    /* JADX WARN: Code duplicated, block: B:115:0x016e  */
    /* JADX WARN: Code duplicated, block: B:119:0x0179  */
    /* JADX WARN: Code duplicated, block: B:125:0x0197  */
    /* JADX WARN: Code duplicated, block: B:127:0x019f  */
    /* JADX WARN: Code duplicated, block: B:134:0x01c1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:135:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:137:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:138:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:140:0x01da  */
    /* JADX WARN: Code duplicated, block: B:141:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:144:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:145:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:148:0x0203  */
    /* JADX WARN: Code duplicated, block: B:151:0x0284  */
    /* JADX WARN: Code duplicated, block: B:152:0x0287  */
    /* JADX WARN: Code duplicated, block: B:155:0x033b  */
    /* JADX WARN: Code duplicated, block: B:158:0x0344  */
    /* JADX WARN: Code duplicated, block: B:159:0x0347  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:75:0x0105  */
    /* JADX WARN: Code duplicated, block: B:78:0x010d  */
    /* JADX WARN: Code duplicated, block: B:79:0x0113  */
    /* JADX WARN: Code duplicated, block: B:81:0x0117  */
    /* JADX WARN: Code duplicated, block: B:83:0x011f  */
    /* JADX WARN: Code duplicated, block: B:84:0x0122  */
    /* JADX WARN: Code duplicated, block: B:86:0x0127  */
    /* JADX WARN: Code duplicated, block: B:89:0x012d  */
    /* JADX WARN: Code duplicated, block: B:91:0x0131  */
    /* JADX WARN: Code duplicated, block: B:93:0x0136  */
    /* JADX WARN: Code duplicated, block: B:95:0x013c  */
    /* JADX WARN: Code duplicated, block: B:96:0x013f  */
    /* JADX WARN: Code duplicated, block: B:99:0x0146  */
    public static final void LazyGrid(Modifier modifier, final LazyGridState state, final Function2<? super Density, ? super Constraints, LazyGridSlots> slots, PaddingValues contentPadding, boolean reverseLayout, final boolean isVertical, FlingBehavior flingBehavior, final boolean userScrollEnabled, final Arrangement.Vertical verticalArrangement, final Arrangement.Horizontal horizontalArrangement, final Function1<? super LazyGridScope, Unit> content, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        PaddingValues contentPadding2;
        boolean reverseLayout2;
        FlingBehavior flingBehavior2;
        PaddingValues contentPadding3;
        boolean reverseLayout3;
        int $dirty;
        Composer $composer2;
        Orientation orientation;
        Modifier modifier3;
        boolean reverseLayout4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(slots, "slots");
        Intrinsics.checkNotNullParameter(verticalArrangement, "verticalArrangement");
        Intrinsics.checkNotNullParameter(horizontalArrangement, "horizontalArrangement");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(152645664);
        ComposerKt.sourceInformation($composer3, "C(LazyGrid)P(5,8,7,1,6,4,2,9,10,3)66@3113L15,76@3554L18,78@3603L50,80@3679L51,82@3756L221,95@4018L48,102@4304L277,110@4645L125,119@4994L7,98@4159L1320:LazyGrid.kt#7791vq");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        int i7 = i & 1;
        if (i7 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 14) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changed(state) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changedInstance(slots) ? 256 : 128;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer3.changed(contentPadding) ? 2048 : 1024;
        }
        int i9 = i & 16;
        if (i9 != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty2 |= $composer3.changed(reverseLayout) ? 16384 : 8192;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
                i2 = $composer3.changed(isVertical) ? 131072 : 65536;
            }
            if (($changed & 3670016) != 0) {
                $dirty2 |= ((i & 64) == 0 || !$composer3.changed(flingBehavior)) ? 524288 : 1048576;
            }
            if ((i & 128) != 0) {
                $dirty2 |= 12582912;
            } else if (($changed & 29360128) != 0) {
                if ($composer3.changed(userScrollEnabled)) {
                    i3 = 8388608;
                } else {
                    i3 = 4194304;
                }
                $dirty2 |= i3;
            }
            if ((i & 256) != 0) {
                if ((234881024 & $changed) == 0) {
                    if ($composer3.changed(verticalArrangement)) {
                        i4 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i4 = 33554432;
                    }
                }
                if ((i & 512) != 0) {
                    if ((1879048192 & $changed) == 0) {
                        if ($composer3.changed(horizontalArrangement)) {
                            i5 = 536870912;
                        } else {
                            i5 = 268435456;
                        }
                    }
                    if ((i & 1024) != 0) {
                        $dirty1 |= 6;
                    } else if (($changed1 & 14) == 0) {
                        if ($composer3.changedInstance(content)) {
                            i6 = 4;
                        } else {
                            i6 = 2;
                        }
                        $dirty1 |= i6;
                    }
                    if ((1533916891 & $dirty2) != 306783378 && ($dirty1 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        contentPadding3 = contentPadding;
                        reverseLayout4 = reverseLayout;
                        flingBehavior2 = flingBehavior;
                        modifier3 = modifier2;
                        $composer2 = $composer3;
                    } else {
                        $composer3.startDefaults();
                        if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i8 != 0) {
                                contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                            } else {
                                contentPadding2 = contentPadding;
                            }
                            if (i9 != 0) {
                                reverseLayout2 = false;
                            } else {
                                reverseLayout2 = reverseLayout;
                            }
                            if ((i & 64) != 0) {
                                contentPadding3 = contentPadding2;
                                flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                                reverseLayout3 = reverseLayout2;
                                $dirty = $dirty2 & (-3670017);
                            } else {
                                flingBehavior2 = flingBehavior;
                                contentPadding3 = contentPadding2;
                                reverseLayout3 = reverseLayout2;
                                $dirty = $dirty2;
                            }
                        } else {
                            $composer3.skipToGroupEnd();
                            if ((i & 64) != 0) {
                                contentPadding3 = contentPadding;
                                reverseLayout3 = reverseLayout;
                                flingBehavior2 = flingBehavior;
                                $dirty = $dirty2 & (-3670017);
                            } else {
                                contentPadding3 = contentPadding;
                                reverseLayout3 = reverseLayout;
                                flingBehavior2 = flingBehavior;
                                $dirty = $dirty2;
                            }
                        }
                        $composer3.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                        }
                        OverscrollEffect overscrollEffect = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                        Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                        LazyLayoutSemanticState semanticState = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                        int $dirty3 = $dirty;
                        boolean reverseLayout5 = reverseLayout3;
                        Modifier modifier4 = modifier2;
                        Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                        state.setVertical$foundation_release(isVertical);
                        $composer2 = $composer3;
                        ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda, state, $composer2, $dirty3 & 112);
                        if (isVertical) {
                            orientation = Orientation.Vertical;
                        } else {
                            orientation = Orientation.Horizontal;
                        }
                        modifier3 = modifier4;
                        Orientation orientation2 = orientation;
                        Modifier modifierOverscroll = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier4.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda, semanticState, orientation, userScrollEnabled, reverseLayout5, $composer2, (($dirty3 >> 9) & 57344) | (($dirty3 << 3) & 458752)), orientation), state, reverseLayout5, orientation2, $composer2, ($dirty3 & 112) | (($dirty3 >> 6) & 896)), overscrollEffect);
                        ScrollableDefaults scrollableDefaults = ScrollableDefaults.INSTANCE;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer2.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        reverseLayout4 = reverseLayout5;
                        LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda, ScrollableKt.scrollable(modifierOverscroll, state, orientation2, overscrollEffect, userScrollEnabled, scrollableDefaults.reverseDirection((LayoutDirection) objConsume, orientation2, reverseLayout5), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy, $composer2, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup == null) {
                        return;
                    }
                    final Modifier modifier5 = modifier3;
                    final PaddingValues paddingValues = contentPadding3;
                    final boolean z = reverseLayout4;
                    final FlingBehavior flingBehavior3 = flingBehavior2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt.LazyGrid.1
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
                            LazyGridKt.LazyGrid(modifier5, state, slots, paddingValues, z, isVertical, flingBehavior3, userScrollEnabled, verticalArrangement, horizontalArrangement, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                        }
                    });
                }
                i5 = 805306368;
                $dirty2 |= i5;
                if ((i & 1024) != 0) {
                    $dirty1 |= 6;
                } else if (($changed1 & 14) == 0) {
                    if ($composer3.changedInstance(content)) {
                        i6 = 4;
                    } else {
                        i6 = 2;
                    }
                    $dirty1 |= i6;
                }
                if ((1533916891 & $dirty2) != 306783378) {
                    $composer3.startDefaults();
                    if (($changed & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i8 != 0) {
                            contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        } else {
                            contentPadding2 = contentPadding;
                        }
                        if (i9 != 0) {
                            reverseLayout2 = false;
                        } else {
                            reverseLayout2 = reverseLayout;
                        }
                        if ((i & 64) != 0) {
                            contentPadding3 = contentPadding2;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            contentPadding3 = contentPadding2;
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i8 != 0) {
                            contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        } else {
                            contentPadding2 = contentPadding;
                        }
                        if (i9 != 0) {
                            reverseLayout2 = false;
                        } else {
                            reverseLayout2 = reverseLayout;
                        }
                        if ((i & 64) != 0) {
                            contentPadding3 = contentPadding2;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            contentPadding3 = contentPadding2;
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2;
                        }
                    }
                    $composer3.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                    }
                    OverscrollEffect overscrollEffect2 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                    Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda2 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                    LazyLayoutSemanticState semanticState2 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                    int $dirty4 = $dirty;
                    boolean reverseLayout6 = reverseLayout3;
                    Modifier modifier6 = modifier2;
                    Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy2 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda2, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                    state.setVertical$foundation_release(isVertical);
                    $composer2 = $composer3;
                    ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda2, state, $composer2, $dirty4 & 112);
                    if (isVertical) {
                        orientation = Orientation.Vertical;
                    } else {
                        orientation = Orientation.Horizontal;
                    }
                    modifier3 = modifier6;
                    Orientation orientation3 = orientation;
                    Modifier modifierOverscroll2 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier6.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda2, semanticState2, orientation, userScrollEnabled, reverseLayout6, $composer2, (($dirty4 >> 9) & 57344) | (($dirty4 << 3) & 458752)), orientation), state, reverseLayout6, orientation3, $composer2, ($dirty4 & 112) | (($dirty4 >> 6) & 896)), overscrollEffect2);
                    ScrollableDefaults scrollableDefaults2 = ScrollableDefaults.INSTANCE;
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume2 = $composer2.consume(localLayoutDirection2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    reverseLayout4 = reverseLayout6;
                    LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda2, ScrollableKt.scrollable(modifierOverscroll2, state, orientation3, overscrollEffect2, userScrollEnabled, scrollableDefaults2.reverseDirection((LayoutDirection) objConsume2, orientation3, reverseLayout6), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy2, $composer2, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer3.startDefaults();
                    if (($changed & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i8 != 0) {
                            contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        } else {
                            contentPadding2 = contentPadding;
                        }
                        if (i9 != 0) {
                            reverseLayout2 = false;
                        } else {
                            reverseLayout2 = reverseLayout;
                        }
                        if ((i & 64) != 0) {
                            contentPadding3 = contentPadding2;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            contentPadding3 = contentPadding2;
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i8 != 0) {
                            contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        } else {
                            contentPadding2 = contentPadding;
                        }
                        if (i9 != 0) {
                            reverseLayout2 = false;
                        } else {
                            reverseLayout2 = reverseLayout;
                        }
                        if ((i & 64) != 0) {
                            contentPadding3 = contentPadding2;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            contentPadding3 = contentPadding2;
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2;
                        }
                    }
                    $composer3.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                    }
                    OverscrollEffect overscrollEffect3 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                    Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda3 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                    LazyLayoutSemanticState semanticState3 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                    int $dirty5 = $dirty;
                    boolean reverseLayout7 = reverseLayout3;
                    Modifier modifier7 = modifier2;
                    Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy3 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda3, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                    state.setVertical$foundation_release(isVertical);
                    $composer2 = $composer3;
                    ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda3, state, $composer2, $dirty5 & 112);
                    if (isVertical) {
                        orientation = Orientation.Vertical;
                    } else {
                        orientation = Orientation.Horizontal;
                    }
                    modifier3 = modifier7;
                    Orientation orientation4 = orientation;
                    Modifier modifierOverscroll3 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier7.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda3, semanticState3, orientation, userScrollEnabled, reverseLayout7, $composer2, (($dirty5 >> 9) & 57344) | (($dirty5 << 3) & 458752)), orientation), state, reverseLayout7, orientation4, $composer2, ($dirty5 & 112) | (($dirty5 >> 6) & 896)), overscrollEffect3);
                    ScrollableDefaults scrollableDefaults3 = ScrollableDefaults.INSTANCE;
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume3 = $composer2.consume(localLayoutDirection3);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    reverseLayout4 = reverseLayout7;
                    LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda3, ScrollableKt.scrollable(modifierOverscroll3, state, orientation4, overscrollEffect3, userScrollEnabled, scrollableDefaults3.reverseDirection((LayoutDirection) objConsume3, orientation4, reverseLayout7), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy3, $composer2, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup == null) {
                    return;
                }
                final Modifier modifier8 = modifier3;
                final PaddingValues paddingValues2 = contentPadding3;
                final boolean z2 = reverseLayout4;
                final FlingBehavior flingBehavior4 = flingBehavior2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt.LazyGrid.1
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
                        LazyGridKt.LazyGrid(modifier8, state, slots, paddingValues2, z2, isVertical, flingBehavior4, userScrollEnabled, verticalArrangement, horizontalArrangement, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                    }
                });
            }
            i4 = 100663296;
            $dirty2 |= i4;
            if ((i & 512) != 0) {
                if ((1879048192 & $changed) == 0) {
                    if ($composer3.changed(horizontalArrangement)) {
                        i5 = 536870912;
                    } else {
                        i5 = 268435456;
                    }
                }
                if ((i & 1024) != 0) {
                    $dirty1 |= 6;
                } else if (($changed1 & 14) == 0) {
                    if ($composer3.changedInstance(content)) {
                        i6 = 4;
                    } else {
                        i6 = 2;
                    }
                    $dirty1 |= i6;
                }
                if ((1533916891 & $dirty2) != 306783378) {
                    $composer3.startDefaults();
                    if (($changed & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i8 != 0) {
                            contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        } else {
                            contentPadding2 = contentPadding;
                        }
                        if (i9 != 0) {
                            reverseLayout2 = false;
                        } else {
                            reverseLayout2 = reverseLayout;
                        }
                        if ((i & 64) != 0) {
                            contentPadding3 = contentPadding2;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            contentPadding3 = contentPadding2;
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i8 != 0) {
                            contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        } else {
                            contentPadding2 = contentPadding;
                        }
                        if (i9 != 0) {
                            reverseLayout2 = false;
                        } else {
                            reverseLayout2 = reverseLayout;
                        }
                        if ((i & 64) != 0) {
                            contentPadding3 = contentPadding2;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            contentPadding3 = contentPadding2;
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2;
                        }
                    }
                    $composer3.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                    }
                    OverscrollEffect overscrollEffect4 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                    Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda4 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                    LazyLayoutSemanticState semanticState4 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                    int $dirty6 = $dirty;
                    boolean reverseLayout8 = reverseLayout3;
                    Modifier modifier9 = modifier2;
                    Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy4 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda4, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                    state.setVertical$foundation_release(isVertical);
                    $composer2 = $composer3;
                    ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda4, state, $composer2, $dirty6 & 112);
                    if (isVertical) {
                        orientation = Orientation.Vertical;
                    } else {
                        orientation = Orientation.Horizontal;
                    }
                    modifier3 = modifier9;
                    Orientation orientation5 = orientation;
                    Modifier modifierOverscroll4 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier9.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda4, semanticState4, orientation, userScrollEnabled, reverseLayout8, $composer2, (($dirty6 >> 9) & 57344) | (($dirty6 << 3) & 458752)), orientation), state, reverseLayout8, orientation5, $composer2, ($dirty6 & 112) | (($dirty6 >> 6) & 896)), overscrollEffect4);
                    ScrollableDefaults scrollableDefaults4 = ScrollableDefaults.INSTANCE;
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume4 = $composer2.consume(localLayoutDirection4);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    reverseLayout4 = reverseLayout8;
                    LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda4, ScrollableKt.scrollable(modifierOverscroll4, state, orientation5, overscrollEffect4, userScrollEnabled, scrollableDefaults4.reverseDirection((LayoutDirection) objConsume4, orientation5, reverseLayout8), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy4, $composer2, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer3.startDefaults();
                    if (($changed & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i8 != 0) {
                            contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        } else {
                            contentPadding2 = contentPadding;
                        }
                        if (i9 != 0) {
                            reverseLayout2 = false;
                        } else {
                            reverseLayout2 = reverseLayout;
                        }
                        if ((i & 64) != 0) {
                            contentPadding3 = contentPadding2;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            contentPadding3 = contentPadding2;
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i8 != 0) {
                            contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        } else {
                            contentPadding2 = contentPadding;
                        }
                        if (i9 != 0) {
                            reverseLayout2 = false;
                        } else {
                            reverseLayout2 = reverseLayout;
                        }
                        if ((i & 64) != 0) {
                            contentPadding3 = contentPadding2;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            contentPadding3 = contentPadding2;
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2;
                        }
                    }
                    $composer3.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                    }
                    OverscrollEffect overscrollEffect5 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                    Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda5 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                    LazyLayoutSemanticState semanticState5 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                    int $dirty7 = $dirty;
                    boolean reverseLayout9 = reverseLayout3;
                    Modifier modifier10 = modifier2;
                    Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy5 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda5, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                    state.setVertical$foundation_release(isVertical);
                    $composer2 = $composer3;
                    ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda5, state, $composer2, $dirty7 & 112);
                    if (isVertical) {
                        orientation = Orientation.Vertical;
                    } else {
                        orientation = Orientation.Horizontal;
                    }
                    modifier3 = modifier10;
                    Orientation orientation6 = orientation;
                    Modifier modifierOverscroll5 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier10.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda5, semanticState5, orientation, userScrollEnabled, reverseLayout9, $composer2, (($dirty7 >> 9) & 57344) | (($dirty7 << 3) & 458752)), orientation), state, reverseLayout9, orientation6, $composer2, ($dirty7 & 112) | (($dirty7 >> 6) & 896)), overscrollEffect5);
                    ScrollableDefaults scrollableDefaults5 = ScrollableDefaults.INSTANCE;
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection5 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume5 = $composer2.consume(localLayoutDirection5);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    reverseLayout4 = reverseLayout9;
                    LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda5, ScrollableKt.scrollable(modifierOverscroll5, state, orientation6, overscrollEffect5, userScrollEnabled, scrollableDefaults5.reverseDirection((LayoutDirection) objConsume5, orientation6, reverseLayout9), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy5, $composer2, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup == null) {
                    return;
                }
                final Modifier modifier11 = modifier3;
                final PaddingValues paddingValues3 = contentPadding3;
                final boolean z3 = reverseLayout4;
                final FlingBehavior flingBehavior5 = flingBehavior2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt.LazyGrid.1
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
                        LazyGridKt.LazyGrid(modifier11, state, slots, paddingValues3, z3, isVertical, flingBehavior5, userScrollEnabled, verticalArrangement, horizontalArrangement, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                    }
                });
            }
            i5 = 805306368;
            $dirty2 |= i5;
            if ((i & 1024) != 0) {
                $dirty1 |= 6;
            } else if (($changed1 & 14) == 0) {
                if ($composer3.changedInstance(content)) {
                    i6 = 4;
                } else {
                    i6 = 2;
                }
                $dirty1 |= i6;
            }
            if ((1533916891 & $dirty2) != 306783378) {
                $composer3.startDefaults();
                if (($changed & 1) != 0) {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        contentPadding3 = contentPadding2;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2;
                    }
                } else {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        contentPadding3 = contentPadding2;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                }
                OverscrollEffect overscrollEffect6 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda6 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                LazyLayoutSemanticState semanticState6 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                int $dirty8 = $dirty;
                boolean reverseLayout10 = reverseLayout3;
                Modifier modifier12 = modifier2;
                Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy6 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda6, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                state.setVertical$foundation_release(isVertical);
                $composer2 = $composer3;
                ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda6, state, $composer2, $dirty8 & 112);
                if (isVertical) {
                    orientation = Orientation.Vertical;
                } else {
                    orientation = Orientation.Horizontal;
                }
                modifier3 = modifier12;
                Orientation orientation7 = orientation;
                Modifier modifierOverscroll6 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier12.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda6, semanticState6, orientation, userScrollEnabled, reverseLayout10, $composer2, (($dirty8 >> 9) & 57344) | (($dirty8 << 3) & 458752)), orientation), state, reverseLayout10, orientation7, $composer2, ($dirty8 & 112) | (($dirty8 >> 6) & 896)), overscrollEffect6);
                ScrollableDefaults scrollableDefaults6 = ScrollableDefaults.INSTANCE;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection6 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume6 = $composer2.consume(localLayoutDirection6);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                reverseLayout4 = reverseLayout10;
                LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda6, ScrollableKt.scrollable(modifierOverscroll6, state, orientation7, overscrollEffect6, userScrollEnabled, scrollableDefaults6.reverseDirection((LayoutDirection) objConsume6, orientation7, reverseLayout10), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy6, $composer2, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0) {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        contentPadding3 = contentPadding2;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2;
                    }
                } else {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        contentPadding3 = contentPadding2;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                }
                OverscrollEffect overscrollEffect7 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda7 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                LazyLayoutSemanticState semanticState7 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                int $dirty9 = $dirty;
                boolean reverseLayout11 = reverseLayout3;
                Modifier modifier13 = modifier2;
                Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy7 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda7, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                state.setVertical$foundation_release(isVertical);
                $composer2 = $composer3;
                ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda7, state, $composer2, $dirty9 & 112);
                if (isVertical) {
                    orientation = Orientation.Vertical;
                } else {
                    orientation = Orientation.Horizontal;
                }
                modifier3 = modifier13;
                Orientation orientation8 = orientation;
                Modifier modifierOverscroll7 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier13.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda7, semanticState7, orientation, userScrollEnabled, reverseLayout11, $composer2, (($dirty9 >> 9) & 57344) | (($dirty9 << 3) & 458752)), orientation), state, reverseLayout11, orientation8, $composer2, ($dirty9 & 112) | (($dirty9 >> 6) & 896)), overscrollEffect7);
                ScrollableDefaults scrollableDefaults7 = ScrollableDefaults.INSTANCE;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection7 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume7 = $composer2.consume(localLayoutDirection7);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                reverseLayout4 = reverseLayout11;
                LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda7, ScrollableKt.scrollable(modifierOverscroll7, state, orientation8, overscrollEffect7, userScrollEnabled, scrollableDefaults7.reverseDirection((LayoutDirection) objConsume7, orientation8, reverseLayout11), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy7, $composer2, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier14 = modifier3;
            final PaddingValues paddingValues4 = contentPadding3;
            final boolean z4 = reverseLayout4;
            final FlingBehavior flingBehavior6 = flingBehavior2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt.LazyGrid.1
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
                    LazyGridKt.LazyGrid(modifier14, state, slots, paddingValues4, z4, isVertical, flingBehavior6, userScrollEnabled, verticalArrangement, horizontalArrangement, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty2 |= i2;
        if (($changed & 3670016) != 0) {
            $dirty2 |= ((i & 64) == 0 || !$composer3.changed(flingBehavior)) ? 524288 : 1048576;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 29360128) != 0) {
            if ($composer3.changed(userScrollEnabled)) {
                i3 = 8388608;
            } else {
                i3 = 4194304;
            }
            $dirty2 |= i3;
        }
        if ((i & 256) != 0) {
            if ((234881024 & $changed) == 0) {
                if ($composer3.changed(verticalArrangement)) {
                    i4 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i4 = 33554432;
                }
            }
            if ((i & 512) != 0) {
                if ((1879048192 & $changed) == 0) {
                    if ($composer3.changed(horizontalArrangement)) {
                        i5 = 536870912;
                    } else {
                        i5 = 268435456;
                    }
                }
                if ((i & 1024) != 0) {
                    $dirty1 |= 6;
                } else if (($changed1 & 14) == 0) {
                    if ($composer3.changedInstance(content)) {
                        i6 = 4;
                    } else {
                        i6 = 2;
                    }
                    $dirty1 |= i6;
                }
                if ((1533916891 & $dirty2) != 306783378) {
                    $composer3.startDefaults();
                    if (($changed & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i8 != 0) {
                            contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        } else {
                            contentPadding2 = contentPadding;
                        }
                        if (i9 != 0) {
                            reverseLayout2 = false;
                        } else {
                            reverseLayout2 = reverseLayout;
                        }
                        if ((i & 64) != 0) {
                            contentPadding3 = contentPadding2;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            contentPadding3 = contentPadding2;
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i8 != 0) {
                            contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        } else {
                            contentPadding2 = contentPadding;
                        }
                        if (i9 != 0) {
                            reverseLayout2 = false;
                        } else {
                            reverseLayout2 = reverseLayout;
                        }
                        if ((i & 64) != 0) {
                            contentPadding3 = contentPadding2;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            contentPadding3 = contentPadding2;
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2;
                        }
                    }
                    $composer3.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                    }
                    OverscrollEffect overscrollEffect8 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                    Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda8 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                    LazyLayoutSemanticState semanticState8 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                    int $dirty10 = $dirty;
                    boolean reverseLayout12 = reverseLayout3;
                    Modifier modifier15 = modifier2;
                    Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy8 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda8, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                    state.setVertical$foundation_release(isVertical);
                    $composer2 = $composer3;
                    ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda8, state, $composer2, $dirty10 & 112);
                    if (isVertical) {
                        orientation = Orientation.Vertical;
                    } else {
                        orientation = Orientation.Horizontal;
                    }
                    modifier3 = modifier15;
                    Orientation orientation9 = orientation;
                    Modifier modifierOverscroll8 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier15.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda8, semanticState8, orientation, userScrollEnabled, reverseLayout12, $composer2, (($dirty10 >> 9) & 57344) | (($dirty10 << 3) & 458752)), orientation), state, reverseLayout12, orientation9, $composer2, ($dirty10 & 112) | (($dirty10 >> 6) & 896)), overscrollEffect8);
                    ScrollableDefaults scrollableDefaults8 = ScrollableDefaults.INSTANCE;
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection8 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume8 = $composer2.consume(localLayoutDirection8);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    reverseLayout4 = reverseLayout12;
                    LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda8, ScrollableKt.scrollable(modifierOverscroll8, state, orientation9, overscrollEffect8, userScrollEnabled, scrollableDefaults8.reverseDirection((LayoutDirection) objConsume8, orientation9, reverseLayout12), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy8, $composer2, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer3.startDefaults();
                    if (($changed & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i8 != 0) {
                            contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        } else {
                            contentPadding2 = contentPadding;
                        }
                        if (i9 != 0) {
                            reverseLayout2 = false;
                        } else {
                            reverseLayout2 = reverseLayout;
                        }
                        if ((i & 64) != 0) {
                            contentPadding3 = contentPadding2;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            contentPadding3 = contentPadding2;
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i8 != 0) {
                            contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                        } else {
                            contentPadding2 = contentPadding;
                        }
                        if (i9 != 0) {
                            reverseLayout2 = false;
                        } else {
                            reverseLayout2 = reverseLayout;
                        }
                        if ((i & 64) != 0) {
                            contentPadding3 = contentPadding2;
                            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2 & (-3670017);
                        } else {
                            flingBehavior2 = flingBehavior;
                            contentPadding3 = contentPadding2;
                            reverseLayout3 = reverseLayout2;
                            $dirty = $dirty2;
                        }
                    }
                    $composer3.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                    }
                    OverscrollEffect overscrollEffect9 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                    Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda9 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                    LazyLayoutSemanticState semanticState9 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                    int $dirty11 = $dirty;
                    boolean reverseLayout13 = reverseLayout3;
                    Modifier modifier16 = modifier2;
                    Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy9 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda9, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                    state.setVertical$foundation_release(isVertical);
                    $composer2 = $composer3;
                    ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda9, state, $composer2, $dirty11 & 112);
                    if (isVertical) {
                        orientation = Orientation.Vertical;
                    } else {
                        orientation = Orientation.Horizontal;
                    }
                    modifier3 = modifier16;
                    Orientation orientation10 = orientation;
                    Modifier modifierOverscroll9 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier16.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda9, semanticState9, orientation, userScrollEnabled, reverseLayout13, $composer2, (($dirty11 >> 9) & 57344) | (($dirty11 << 3) & 458752)), orientation), state, reverseLayout13, orientation10, $composer2, ($dirty11 & 112) | (($dirty11 >> 6) & 896)), overscrollEffect9);
                    ScrollableDefaults scrollableDefaults9 = ScrollableDefaults.INSTANCE;
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection9 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume9 = $composer2.consume(localLayoutDirection9);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    reverseLayout4 = reverseLayout13;
                    LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda9, ScrollableKt.scrollable(modifierOverscroll9, state, orientation10, overscrollEffect9, userScrollEnabled, scrollableDefaults9.reverseDirection((LayoutDirection) objConsume9, orientation10, reverseLayout13), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy9, $composer2, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup == null) {
                    return;
                }
                final Modifier modifier17 = modifier3;
                final PaddingValues paddingValues5 = contentPadding3;
                final boolean z5 = reverseLayout4;
                final FlingBehavior flingBehavior7 = flingBehavior2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt.LazyGrid.1
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
                        LazyGridKt.LazyGrid(modifier17, state, slots, paddingValues5, z5, isVertical, flingBehavior7, userScrollEnabled, verticalArrangement, horizontalArrangement, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                    }
                });
            }
            i5 = 805306368;
            $dirty2 |= i5;
            if ((i & 1024) != 0) {
                $dirty1 |= 6;
            } else if (($changed1 & 14) == 0) {
                if ($composer3.changedInstance(content)) {
                    i6 = 4;
                } else {
                    i6 = 2;
                }
                $dirty1 |= i6;
            }
            if ((1533916891 & $dirty2) != 306783378) {
                $composer3.startDefaults();
                if (($changed & 1) != 0) {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        contentPadding3 = contentPadding2;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2;
                    }
                } else {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        contentPadding3 = contentPadding2;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                }
                OverscrollEffect overscrollEffect10 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda10 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                LazyLayoutSemanticState semanticState10 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                int $dirty12 = $dirty;
                boolean reverseLayout14 = reverseLayout3;
                Modifier modifier18 = modifier2;
                Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy10 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda10, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                state.setVertical$foundation_release(isVertical);
                $composer2 = $composer3;
                ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda10, state, $composer2, $dirty12 & 112);
                if (isVertical) {
                    orientation = Orientation.Vertical;
                } else {
                    orientation = Orientation.Horizontal;
                }
                modifier3 = modifier18;
                Orientation orientation11 = orientation;
                Modifier modifierOverscroll10 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier18.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda10, semanticState10, orientation, userScrollEnabled, reverseLayout14, $composer2, (($dirty12 >> 9) & 57344) | (($dirty12 << 3) & 458752)), orientation), state, reverseLayout14, orientation11, $composer2, ($dirty12 & 112) | (($dirty12 >> 6) & 896)), overscrollEffect10);
                ScrollableDefaults scrollableDefaults10 = ScrollableDefaults.INSTANCE;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection10 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume10 = $composer2.consume(localLayoutDirection10);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                reverseLayout4 = reverseLayout14;
                LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda10, ScrollableKt.scrollable(modifierOverscroll10, state, orientation11, overscrollEffect10, userScrollEnabled, scrollableDefaults10.reverseDirection((LayoutDirection) objConsume10, orientation11, reverseLayout14), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy10, $composer2, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0) {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        contentPadding3 = contentPadding2;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2;
                    }
                } else {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        contentPadding3 = contentPadding2;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                }
                OverscrollEffect overscrollEffect11 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda11 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                LazyLayoutSemanticState semanticState11 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                int $dirty13 = $dirty;
                boolean reverseLayout15 = reverseLayout3;
                Modifier modifier19 = modifier2;
                Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy11 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda11, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                state.setVertical$foundation_release(isVertical);
                $composer2 = $composer3;
                ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda11, state, $composer2, $dirty13 & 112);
                if (isVertical) {
                    orientation = Orientation.Vertical;
                } else {
                    orientation = Orientation.Horizontal;
                }
                modifier3 = modifier19;
                Orientation orientation12 = orientation;
                Modifier modifierOverscroll11 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier19.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda11, semanticState11, orientation, userScrollEnabled, reverseLayout15, $composer2, (($dirty13 >> 9) & 57344) | (($dirty13 << 3) & 458752)), orientation), state, reverseLayout15, orientation12, $composer2, ($dirty13 & 112) | (($dirty13 >> 6) & 896)), overscrollEffect11);
                ScrollableDefaults scrollableDefaults11 = ScrollableDefaults.INSTANCE;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection11 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume11 = $composer2.consume(localLayoutDirection11);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                reverseLayout4 = reverseLayout15;
                LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda11, ScrollableKt.scrollable(modifierOverscroll11, state, orientation12, overscrollEffect11, userScrollEnabled, scrollableDefaults11.reverseDirection((LayoutDirection) objConsume11, orientation12, reverseLayout15), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy11, $composer2, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier110 = modifier3;
            final PaddingValues paddingValues6 = contentPadding3;
            final boolean z6 = reverseLayout4;
            final FlingBehavior flingBehavior8 = flingBehavior2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt.LazyGrid.1
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
                    LazyGridKt.LazyGrid(modifier110, state, slots, paddingValues6, z6, isVertical, flingBehavior8, userScrollEnabled, verticalArrangement, horizontalArrangement, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i4 = 100663296;
        $dirty2 |= i4;
        if ((i & 512) != 0) {
            if ((1879048192 & $changed) == 0) {
                if ($composer3.changed(horizontalArrangement)) {
                    i5 = 536870912;
                } else {
                    i5 = 268435456;
                }
            }
            if ((i & 1024) != 0) {
                $dirty1 |= 6;
            } else if (($changed1 & 14) == 0) {
                if ($composer3.changedInstance(content)) {
                    i6 = 4;
                } else {
                    i6 = 2;
                }
                $dirty1 |= i6;
            }
            if ((1533916891 & $dirty2) != 306783378) {
                $composer3.startDefaults();
                if (($changed & 1) != 0) {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        contentPadding3 = contentPadding2;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2;
                    }
                } else {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        contentPadding3 = contentPadding2;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                }
                OverscrollEffect overscrollEffect12 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda12 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                LazyLayoutSemanticState semanticState12 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                int $dirty14 = $dirty;
                boolean reverseLayout16 = reverseLayout3;
                Modifier modifier111 = modifier2;
                Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy12 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda12, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                state.setVertical$foundation_release(isVertical);
                $composer2 = $composer3;
                ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda12, state, $composer2, $dirty14 & 112);
                if (isVertical) {
                    orientation = Orientation.Vertical;
                } else {
                    orientation = Orientation.Horizontal;
                }
                modifier3 = modifier111;
                Orientation orientation13 = orientation;
                Modifier modifierOverscroll12 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier111.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda12, semanticState12, orientation, userScrollEnabled, reverseLayout16, $composer2, (($dirty14 >> 9) & 57344) | (($dirty14 << 3) & 458752)), orientation), state, reverseLayout16, orientation13, $composer2, ($dirty14 & 112) | (($dirty14 >> 6) & 896)), overscrollEffect12);
                ScrollableDefaults scrollableDefaults12 = ScrollableDefaults.INSTANCE;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection12 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume12 = $composer2.consume(localLayoutDirection12);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                reverseLayout4 = reverseLayout16;
                LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda12, ScrollableKt.scrollable(modifierOverscroll12, state, orientation13, overscrollEffect12, userScrollEnabled, scrollableDefaults12.reverseDirection((LayoutDirection) objConsume12, orientation13, reverseLayout16), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy12, $composer2, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0) {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        contentPadding3 = contentPadding2;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2;
                    }
                } else {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i8 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = reverseLayout;
                    }
                    if ((i & 64) != 0) {
                        contentPadding3 = contentPadding2;
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2 & (-3670017);
                    } else {
                        flingBehavior2 = flingBehavior;
                        contentPadding3 = contentPadding2;
                        reverseLayout3 = reverseLayout2;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
                }
                OverscrollEffect overscrollEffect13 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
                Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda13 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
                LazyLayoutSemanticState semanticState13 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                int $dirty15 = $dirty;
                boolean reverseLayout17 = reverseLayout3;
                Modifier modifier112 = modifier2;
                Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy13 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda13, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
                state.setVertical$foundation_release(isVertical);
                $composer2 = $composer3;
                ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda13, state, $composer2, $dirty15 & 112);
                if (isVertical) {
                    orientation = Orientation.Vertical;
                } else {
                    orientation = Orientation.Horizontal;
                }
                modifier3 = modifier112;
                Orientation orientation14 = orientation;
                Modifier modifierOverscroll13 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier112.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda13, semanticState13, orientation, userScrollEnabled, reverseLayout17, $composer2, (($dirty15 >> 9) & 57344) | (($dirty15 << 3) & 458752)), orientation), state, reverseLayout17, orientation14, $composer2, ($dirty15 & 112) | (($dirty15 >> 6) & 896)), overscrollEffect13);
                ScrollableDefaults scrollableDefaults13 = ScrollableDefaults.INSTANCE;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection13 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume13 = $composer2.consume(localLayoutDirection13);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                reverseLayout4 = reverseLayout17;
                LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda13, ScrollableKt.scrollable(modifierOverscroll13, state, orientation14, overscrollEffect13, userScrollEnabled, scrollableDefaults13.reverseDirection((LayoutDirection) objConsume13, orientation14, reverseLayout17), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy13, $composer2, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier113 = modifier3;
            final PaddingValues paddingValues7 = contentPadding3;
            final boolean z7 = reverseLayout4;
            final FlingBehavior flingBehavior9 = flingBehavior2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt.LazyGrid.1
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
                    LazyGridKt.LazyGrid(modifier113, state, slots, paddingValues7, z7, isVertical, flingBehavior9, userScrollEnabled, verticalArrangement, horizontalArrangement, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i5 = 805306368;
        $dirty2 |= i5;
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 14) == 0) {
            if ($composer3.changedInstance(content)) {
                i6 = 4;
            } else {
                i6 = 2;
            }
            $dirty1 |= i6;
        }
        if ((1533916891 & $dirty2) != 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = reverseLayout;
                }
                if ((i & 64) != 0) {
                    contentPadding3 = contentPadding2;
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    reverseLayout3 = reverseLayout2;
                    $dirty = $dirty2 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                    $dirty = $dirty2;
                }
            } else {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = reverseLayout;
                }
                if ((i & 64) != 0) {
                    contentPadding3 = contentPadding2;
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    reverseLayout3 = reverseLayout2;
                    $dirty = $dirty2 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
            }
            OverscrollEffect overscrollEffect14 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
            Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda14 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
            LazyLayoutSemanticState semanticState14 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
            int $dirty16 = $dirty;
            boolean reverseLayout18 = reverseLayout3;
            Modifier modifier114 = modifier2;
            Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy14 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda14, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
            state.setVertical$foundation_release(isVertical);
            $composer2 = $composer3;
            ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda14, state, $composer2, $dirty16 & 112);
            if (isVertical) {
                orientation = Orientation.Vertical;
            } else {
                orientation = Orientation.Horizontal;
            }
            modifier3 = modifier114;
            Orientation orientation15 = orientation;
            Modifier modifierOverscroll14 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier114.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda14, semanticState14, orientation, userScrollEnabled, reverseLayout18, $composer2, (($dirty16 >> 9) & 57344) | (($dirty16 << 3) & 458752)), orientation), state, reverseLayout18, orientation15, $composer2, ($dirty16 & 112) | (($dirty16 >> 6) & 896)), overscrollEffect14);
            ScrollableDefaults scrollableDefaults14 = ScrollableDefaults.INSTANCE;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection14 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume14 = $composer2.consume(localLayoutDirection14);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            reverseLayout4 = reverseLayout18;
            LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda14, ScrollableKt.scrollable(modifierOverscroll14, state, orientation15, overscrollEffect14, userScrollEnabled, scrollableDefaults14.reverseDirection((LayoutDirection) objConsume14, orientation15, reverseLayout18), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy14, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = reverseLayout;
                }
                if ((i & 64) != 0) {
                    contentPadding3 = contentPadding2;
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    reverseLayout3 = reverseLayout2;
                    $dirty = $dirty2 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                    $dirty = $dirty2;
                }
            } else {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i8 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = reverseLayout;
                }
                if ((i & 64) != 0) {
                    contentPadding3 = contentPadding2;
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    reverseLayout3 = reverseLayout2;
                    $dirty = $dirty2 & (-3670017);
                } else {
                    flingBehavior2 = flingBehavior;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(152645664, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:52)");
            }
            OverscrollEffect overscrollEffect15 = ScrollableDefaults.INSTANCE.overscrollEffect($composer3, 6);
            Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda15 = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 << 3) & 112));
            LazyLayoutSemanticState semanticState15 = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
            int $dirty17 = $dirty;
            boolean reverseLayout19 = reverseLayout3;
            Modifier modifier115 = modifier2;
            Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyGridMeasurePolicy15 = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda15, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty >> 9) & 3670016) | (($dirty >> 3) & 29360128), 0);
            state.setVertical$foundation_release(isVertical);
            $composer2 = $composer3;
            ScrollPositionUpdater(function0RememberLazyGridItemProviderLambda15, state, $composer2, $dirty17 & 112);
            if (isVertical) {
                orientation = Orientation.Vertical;
            } else {
                orientation = Orientation.Horizontal;
            }
            modifier3 = modifier115;
            Orientation orientation16 = orientation;
            Modifier modifierOverscroll15 = OverscrollKt.overscroll(LazyGridBeyondBoundsModifierKt.lazyGridBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier115.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda15, semanticState15, orientation, userScrollEnabled, reverseLayout19, $composer2, (($dirty17 >> 9) & 57344) | (($dirty17 << 3) & 458752)), orientation), state, reverseLayout19, orientation16, $composer2, ($dirty17 & 112) | (($dirty17 >> 6) & 896)), overscrollEffect15);
            ScrollableDefaults scrollableDefaults15 = ScrollableDefaults.INSTANCE;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection15 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume15 = $composer2.consume(localLayoutDirection15);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            reverseLayout4 = reverseLayout19;
            LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda15, ScrollableKt.scrollable(modifierOverscroll15, state, orientation16, overscrollEffect15, userScrollEnabled, scrollableDefaults15.reverseDirection((LayoutDirection) objConsume15, orientation16, reverseLayout19), flingBehavior2, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyGridMeasurePolicy15, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier116 = modifier3;
        final PaddingValues paddingValues8 = contentPadding3;
        final boolean z8 = reverseLayout4;
        final FlingBehavior flingBehavior10 = flingBehavior2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt.LazyGrid.1
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
                LazyGridKt.LazyGrid(modifier116, state, slots, paddingValues8, z8, isVertical, flingBehavior10, userScrollEnabled, verticalArrangement, horizontalArrangement, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ScrollPositionUpdater(final Function0<? extends LazyGridItemProvider> function0, final LazyGridState state, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-649335720);
        ComposerKt.sourceInformation($composer2, "C(ScrollPositionUpdater):LazyGrid.kt#7791vq");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(state) ? 32 : 16;
        }
        if (($dirty & 91) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-649335720, $changed, -1, "androidx.compose.foundation.lazy.grid.ScrollPositionUpdater (LazyGrid.kt:138)");
            }
            LazyGridItemProvider itemProvider = function0.invoke();
            if (itemProvider.getItemCount() > 0) {
                LazyGridState.updateScrollPositionIfTheFirstItemWasMoved$foundation_release$default(state, itemProvider, 0, 2, null);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt.ScrollPositionUpdater.1
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
                LazyGridKt.ScrollPositionUpdater(function0, state, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    private static final Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> rememberLazyGridMeasurePolicy(final Function0<? extends LazyGridItemProvider> function0, final LazyGridState state, final Function2<? super Density, ? super Constraints, LazyGridSlots> function2, final PaddingValues contentPadding, final boolean reverseLayout, final boolean isVertical, Arrangement.Horizontal horizontalArrangement, Arrangement.Vertical verticalArrangement, Composer $composer, int $changed, int i) {
        $composer.startReplaceableGroup(1292704639);
        ComposerKt.sourceInformation($composer, "C(rememberLazyGridMeasurePolicy)P(3,6,5!1,4,2)173@6918L8458:LazyGrid.kt#7791vq");
        Arrangement.Horizontal horizontalArrangement2 = (i & 64) != 0 ? null : horizontalArrangement;
        Arrangement.Vertical verticalArrangement2 = (i & 128) != 0 ? null : verticalArrangement;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1292704639, $changed, -1, "androidx.compose.foundation.lazy.grid.rememberLazyGridMeasurePolicy (LazyGrid.kt:156)");
        }
        Object[] keys$iv = {state, function2, contentPadding, Boolean.valueOf(reverseLayout), Boolean.valueOf(isVertical), horizontalArrangement2, verticalArrangement2};
        $composer.startReplaceableGroup(-568225417);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean invalid$iv = false;
        for (Object key$iv : keys$iv) {
            invalid$iv |= $composer.changed(key$iv);
        }
        Object value$iv$iv = $composer.rememberedValue();
        if (invalid$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
            final Arrangement.Vertical vertical = verticalArrangement2;
            final Arrangement.Horizontal horizontal = horizontalArrangement2;
            value$iv$iv = new Function2<LazyLayoutMeasureScope, Constraints, LazyGridMeasureResult>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyGridMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                    return m621invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                }

                /* JADX WARN: Type inference failed for: r0v42, types: [androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1] */
                /* JADX WARN: Type inference failed for: r0v43, types: [androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1] */
                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyGridMeasureResult m621invoke0kLqBqw(final LazyLayoutMeasureScope $this$null, final long containerConstraints) throws Throwable {
                    int i2;
                    int i3;
                    int i4;
                    float spacing;
                    int iM5218getMaxWidthimpl;
                    final long visualItemOffset;
                    Snapshot previous$iv$iv;
                    int firstVisibleLineIndex;
                    int firstVisibleLineScrollOffset;
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                    CheckScrollableContainerConstraintsKt.m188checkScrollableContainerConstraintsK40F9xA(containerConstraints, isVertical ? Orientation.Vertical : Orientation.Horizontal);
                    if (isVertical) {
                        i2 = $this$null.mo321roundToPx0680j_4(contentPadding.mo437calculateLeftPaddingu2uoSUM($this$null.getLayoutDirection()));
                    } else {
                        i2 = $this$null.mo321roundToPx0680j_4(PaddingKt.calculateStartPadding(contentPadding, $this$null.getLayoutDirection()));
                    }
                    int startPadding = i2;
                    if (isVertical) {
                        i3 = $this$null.mo321roundToPx0680j_4(contentPadding.mo438calculateRightPaddingu2uoSUM($this$null.getLayoutDirection()));
                    } else {
                        i3 = $this$null.mo321roundToPx0680j_4(PaddingKt.calculateEndPadding(contentPadding, $this$null.getLayoutDirection()));
                    }
                    int endPadding = i3;
                    int topPadding = $this$null.mo321roundToPx0680j_4(contentPadding.getTop());
                    int bottomPadding = $this$null.mo321roundToPx0680j_4(contentPadding.getBottom());
                    final int totalVerticalPadding = topPadding + bottomPadding;
                    final int totalHorizontalPadding = startPadding + endPadding;
                    boolean z = isVertical;
                    int totalMainAxisPadding = z ? totalVerticalPadding : totalHorizontalPadding;
                    if (z && !reverseLayout) {
                        i4 = topPadding;
                    } else if (z && reverseLayout) {
                        i4 = bottomPadding;
                    } else {
                        i4 = (z || reverseLayout) ? endPadding : startPadding;
                    }
                    final int beforeContentPadding = i4;
                    final int afterContentPadding = totalMainAxisPadding - beforeContentPadding;
                    long contentConstraints = ConstraintsKt.m5234offsetNN6EwU(containerConstraints, -totalHorizontalPadding, -totalVerticalPadding);
                    final LazyGridItemProvider itemProvider = function0.invoke();
                    final LazyGridSpanLayoutProvider spanLayoutProvider = itemProvider.getSpanLayoutProvider();
                    final LazyGridSlots resolvedSlots = function2.invoke($this$null, Constraints.m5206boximpl(containerConstraints));
                    int slotsPerLine = resolvedSlots.getSizes().length;
                    spanLayoutProvider.setSlotsPerLine(slotsPerLine);
                    state.setDensity$foundation_release($this$null);
                    state.setSlotsPerLine$foundation_release(slotsPerLine);
                    if (isVertical) {
                        Arrangement.Vertical vertical2 = vertical;
                        if (vertical2 == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        spacing = vertical2.getSpacing();
                    } else {
                        Arrangement.Horizontal horizontal2 = horizontal;
                        if (horizontal2 == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        spacing = horizontal2.getSpacing();
                    }
                    float spaceBetweenLinesDp = spacing;
                    final int spaceBetweenLines = $this$null.mo321roundToPx0680j_4(spaceBetweenLinesDp);
                    final int itemsCount = itemProvider.getItemCount();
                    if (isVertical) {
                        iM5218getMaxWidthimpl = Constraints.m5217getMaxHeightimpl(containerConstraints) - totalVerticalPadding;
                    } else {
                        iM5218getMaxWidthimpl = Constraints.m5218getMaxWidthimpl(containerConstraints) - totalHorizontalPadding;
                    }
                    int mainAxisAvailableSize = iM5218getMaxWidthimpl;
                    if (!reverseLayout || mainAxisAvailableSize > 0) {
                        visualItemOffset = IntOffsetKt.IntOffset(startPadding, topPadding);
                    } else {
                        boolean z2 = isVertical;
                        visualItemOffset = IntOffsetKt.IntOffset(z2 ? startPadding : startPadding + mainAxisAvailableSize, z2 ? topPadding + mainAxisAvailableSize : topPadding);
                    }
                    final boolean z3 = isVertical;
                    final boolean z4 = reverseLayout;
                    final ?? r0 = new LazyGridMeasuredItemProvider(itemProvider, $this$null, spaceBetweenLines, z3, z4, beforeContentPadding, afterContentPadding, visualItemOffset) { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1
                        final /* synthetic */ int $afterContentPadding;
                        final /* synthetic */ int $beforeContentPadding;
                        final /* synthetic */ boolean $isVertical;
                        final /* synthetic */ boolean $reverseLayout;
                        final /* synthetic */ LazyLayoutMeasureScope $this_null;
                        final /* synthetic */ long $visualItemOffset;

                        {
                            this.$this_null = $this$null;
                            this.$isVertical = z3;
                            this.$reverseLayout = z4;
                            this.$beforeContentPadding = beforeContentPadding;
                            this.$afterContentPadding = afterContentPadding;
                            this.$visualItemOffset = visualItemOffset;
                        }

                        @Override // androidx.compose.foundation.lazy.grid.LazyGridMeasuredItemProvider
                        public LazyGridMeasuredItem createItem(int index, Object key, Object contentType, int crossAxisSize, int mainAxisSpacing, List<? extends Placeable> placeables) {
                            Intrinsics.checkNotNullParameter(key, "key");
                            Intrinsics.checkNotNullParameter(placeables, "placeables");
                            return new LazyGridMeasuredItem(index, key, this.$isVertical, crossAxisSize, mainAxisSpacing, this.$reverseLayout, this.$this_null.getLayoutDirection(), this.$beforeContentPadding, this.$afterContentPadding, placeables, this.$visualItemOffset, contentType, null);
                        }
                    };
                    final boolean z5 = isVertical;
                    final ?? r1 = new LazyGridMeasuredLineProvider(z5, resolvedSlots, itemsCount, spaceBetweenLines, r0, spanLayoutProvider) { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1
                        final /* synthetic */ boolean $isVertical;
                        final /* synthetic */ LazyGridSlots $resolvedSlots;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(z5, resolvedSlots, itemsCount, spaceBetweenLines, r0, spanLayoutProvider);
                            this.$isVertical = z5;
                            this.$resolvedSlots = resolvedSlots;
                        }

                        @Override // androidx.compose.foundation.lazy.grid.LazyGridMeasuredLineProvider
                        public LazyGridMeasuredLine createLine(int index, LazyGridMeasuredItem[] items, List<GridItemSpan> spans, int mainAxisSpacing) {
                            Intrinsics.checkNotNullParameter(items, "items");
                            Intrinsics.checkNotNullParameter(spans, "spans");
                            return new LazyGridMeasuredLine(index, items, this.$resolvedSlots, spans, this.$isVertical, mainAxisSpacing);
                        }
                    };
                    state.setPrefetchInfoRetriever$foundation_release(new Function1<Integer, ArrayList<Pair<? extends Integer, ? extends Constraints>>>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ ArrayList<Pair<? extends Integer, ? extends Constraints>> invoke(Integer num) {
                            return invoke(num.intValue());
                        }

                        public final ArrayList<Pair<Integer, Constraints>> invoke(int line) {
                            LazyGridSpanLayoutProvider.LineConfiguration lineConfiguration = spanLayoutProvider.getLineConfiguration(line);
                            int index = lineConfiguration.getFirstItemIndex();
                            int slot = 0;
                            ArrayList<Pair<Integer, Constraints>> arrayList = new ArrayList<>(lineConfiguration.getSpans().size());
                            List<GridItemSpan> spans = lineConfiguration.getSpans();
                            LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 = r1;
                            int index$iv = 0;
                            int size = spans.size();
                            while (index$iv < size) {
                                Object item$iv = spans.get(index$iv);
                                long it = ((GridItemSpan) item$iv).getPackedValue();
                                int span = GridItemSpan.m606getCurrentLineSpanimpl(it);
                                arrayList.add(TuplesKt.to(Integer.valueOf(index), Constraints.m5206boximpl(lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1.m627childConstraintsJhjzzOo$foundation_release(slot, span))));
                                index++;
                                slot += span;
                                index$iv++;
                                lineConfiguration = lineConfiguration;
                            }
                            return arrayList;
                        }
                    });
                    Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                    LazyGridState lazyGridState = state;
                    Snapshot snapshot$iv = this_$iv.createNonObservableSnapshot();
                    try {
                        Snapshot previous$iv$iv2 = snapshot$iv.makeCurrent();
                        int firstVisibleLineIndex2 = 0;
                        try {
                            int index = lazyGridState.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(itemProvider, lazyGridState.getFirstVisibleItemIndex());
                            if (index < itemsCount || itemsCount <= 0) {
                                try {
                                    firstVisibleLineIndex = spanLayoutProvider.getLineIndexOfItem(index);
                                    try {
                                        firstVisibleLineScrollOffset = lazyGridState.getFirstVisibleItemScrollOffset();
                                        try {
                                            Unit unit = Unit.INSTANCE;
                                            try {
                                                snapshot$iv.restoreCurrent(previous$iv$iv2);
                                                snapshot$iv.dispose();
                                                LazyGridMeasureResult it = LazyGridMeasureKt.m622measureLazyGridZRKPzZ8(itemsCount, (LazyGridMeasuredLineProvider) r1, (LazyGridMeasuredItemProvider) r0, mainAxisAvailableSize, beforeContentPadding, afterContentPadding, spaceBetweenLines, firstVisibleLineIndex, firstVisibleLineScrollOffset, state.getScrollToBeConsumed(), contentConstraints, isVertical, vertical, horizontal, reverseLayout, $this$null, state.getPlacementAnimator(), spanLayoutProvider, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, state.getPinnedItems(), state.getBeyondBoundsInfo()), new Function3<Integer, Integer, Function1<? super Placeable.PlacementScope, ? extends Unit>, MeasureResult>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1.3
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(3);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function3
                                                    public /* bridge */ /* synthetic */ MeasureResult invoke(Integer num, Integer num2, Function1<? super Placeable.PlacementScope, ? extends Unit> function1) {
                                                        return invoke(num.intValue(), num2.intValue(), (Function1<? super Placeable.PlacementScope, Unit>) function1);
                                                    }

                                                    public final MeasureResult invoke(int width, int height, Function1<? super Placeable.PlacementScope, Unit> placement) {
                                                        Intrinsics.checkNotNullParameter(placement, "placement");
                                                        return $this$null.layout(ConstraintsKt.m5232constrainWidthK40F9xA(containerConstraints, totalHorizontalPadding + width), ConstraintsKt.m5231constrainHeightK40F9xA(containerConstraints, totalVerticalPadding + height), MapsKt.emptyMap(), placement);
                                                    }
                                                });
                                                state.applyMeasureResult$foundation_release(it);
                                                return it;
                                            } catch (Throwable th) {
                                                th = th;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            previous$iv$iv = previous$iv$iv2;
                                            firstVisibleLineIndex2 = firstVisibleLineIndex;
                                            try {
                                                snapshot$iv.restoreCurrent(previous$iv$iv);
                                                throw th;
                                            } catch (Throwable th3) {
                                                th = th3;
                                            }
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        previous$iv$iv = previous$iv$iv2;
                                        firstVisibleLineIndex2 = firstVisibleLineIndex;
                                        snapshot$iv.restoreCurrent(previous$iv$iv);
                                        throw th;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    previous$iv$iv = previous$iv$iv2;
                                }
                            } else {
                                try {
                                    firstVisibleLineIndex = spanLayoutProvider.getLineIndexOfItem(itemsCount - 1);
                                    firstVisibleLineScrollOffset = 0;
                                    Unit unit2 = Unit.INSTANCE;
                                    snapshot$iv.restoreCurrent(previous$iv$iv2);
                                    snapshot$iv.dispose();
                                    LazyGridMeasureResult it2 = LazyGridMeasureKt.m622measureLazyGridZRKPzZ8(itemsCount, (LazyGridMeasuredLineProvider) r1, (LazyGridMeasuredItemProvider) r0, mainAxisAvailableSize, beforeContentPadding, afterContentPadding, spaceBetweenLines, firstVisibleLineIndex, firstVisibleLineScrollOffset, state.getScrollToBeConsumed(), contentConstraints, isVertical, vertical, horizontal, reverseLayout, $this$null, state.getPlacementAnimator(), spanLayoutProvider, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, state.getPinnedItems(), state.getBeyondBoundsInfo()), new Function3<Integer, Integer, Function1<? super Placeable.PlacementScope, ? extends Unit>, MeasureResult>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1.3
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ MeasureResult invoke(Integer num, Integer num2, Function1<? super Placeable.PlacementScope, ? extends Unit> function1) {
                                            return invoke(num.intValue(), num2.intValue(), (Function1<? super Placeable.PlacementScope, Unit>) function1);
                                        }

                                        public final MeasureResult invoke(int width, int height, Function1<? super Placeable.PlacementScope, Unit> placement) {
                                            Intrinsics.checkNotNullParameter(placement, "placement");
                                            return $this$null.layout(ConstraintsKt.m5232constrainWidthK40F9xA(containerConstraints, totalHorizontalPadding + width), ConstraintsKt.m5231constrainHeightK40F9xA(containerConstraints, totalVerticalPadding + height), MapsKt.emptyMap(), placement);
                                        }
                                    });
                                    state.applyMeasureResult$foundation_release(it2);
                                    return it2;
                                } catch (Throwable th6) {
                                    th = th6;
                                    previous$iv$iv = previous$iv$iv2;
                                    snapshot$iv.restoreCurrent(previous$iv$iv);
                                    throw th;
                                }
                            }
                        } catch (Throwable th7) {
                            th = th7;
                            previous$iv$iv = previous$iv$iv2;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                    }
                    snapshot$iv.dispose();
                    throw th;
                }
            };
            $composer.updateRememberedValue(value$iv$iv);
        }
        $composer.endReplaceableGroup();
        Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function3 = (Function2) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return function3;
    }
}
