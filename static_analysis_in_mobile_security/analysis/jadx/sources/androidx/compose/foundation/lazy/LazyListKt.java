package androidx.compose.foundation.lazy;

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
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyList.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0098\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\u0019¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0002\u0010\u001c\u001a#\u0010\u001d\u001a\u00020\u00012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010!\u001a\u008d\u0001\u0010\"\u001a\u0019\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0#¢\u0006\u0002\b\u001b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0003ø\u0001\u0000¢\u0006\u0002\u0010'\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006("}, d2 = {"LazyList", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/LazyListState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "isVertical", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "beyondBoundsItemCount", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyListScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/gestures/FlingBehavior;ZILandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "ScrollPositionUpdater", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/LazyListItemProvider;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/runtime/Composer;I)V", "rememberLazyListMeasurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZZILandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/runtime/Composer;II)Lkotlin/jvm/functions/Function2;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyListKt {
    /* JADX WARN: Code duplicated, block: B:100:0x0152  */
    /* JADX WARN: Code duplicated, block: B:102:0x015a  */
    /* JADX WARN: Code duplicated, block: B:103:0x015d  */
    /* JADX WARN: Code duplicated, block: B:105:0x0162  */
    /* JADX WARN: Code duplicated, block: B:108:0x0168  */
    /* JADX WARN: Code duplicated, block: B:109:0x016d  */
    /* JADX WARN: Code duplicated, block: B:111:0x0171  */
    /* JADX WARN: Code duplicated, block: B:113:0x0179  */
    /* JADX WARN: Code duplicated, block: B:115:0x017e  */
    /* JADX WARN: Code duplicated, block: B:118:0x0184  */
    /* JADX WARN: Code duplicated, block: B:119:0x0189  */
    /* JADX WARN: Code duplicated, block: B:121:0x018d  */
    /* JADX WARN: Code duplicated, block: B:124:0x0196  */
    /* JADX WARN: Code duplicated, block: B:126:0x019b  */
    /* JADX WARN: Code duplicated, block: B:129:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:130:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:132:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:135:0x01af  */
    /* JADX WARN: Code duplicated, block: B:145:0x01df A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:146:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:147:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:149:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:150:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:152:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:153:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:155:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:156:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:158:0x0201  */
    /* JADX WARN: Code duplicated, block: B:159:0x0205  */
    /* JADX WARN: Code duplicated, block: B:162:0x020d  */
    /* JADX WARN: Code duplicated, block: B:165:0x029b  */
    /* JADX WARN: Code duplicated, block: B:166:0x029e  */
    /* JADX WARN: Code duplicated, block: B:169:0x0344  */
    /* JADX WARN: Code duplicated, block: B:173:0x0357  */
    /* JADX WARN: Code duplicated, block: B:174:0x035c  */
    /* JADX WARN: Code duplicated, block: B:64:0x00df  */
    /* JADX WARN: Code duplicated, block: B:65:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:67:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:70:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:72:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:75:0x0101  */
    /* JADX WARN: Code duplicated, block: B:76:0x0108  */
    /* JADX WARN: Code duplicated, block: B:78:0x010c  */
    /* JADX WARN: Code duplicated, block: B:80:0x0114  */
    /* JADX WARN: Code duplicated, block: B:81:0x0117  */
    /* JADX WARN: Code duplicated, block: B:83:0x011c  */
    /* JADX WARN: Code duplicated, block: B:86:0x0124  */
    /* JADX WARN: Code duplicated, block: B:87:0x012b  */
    /* JADX WARN: Code duplicated, block: B:89:0x012f  */
    /* JADX WARN: Code duplicated, block: B:91:0x0137  */
    /* JADX WARN: Code duplicated, block: B:92:0x013a  */
    /* JADX WARN: Code duplicated, block: B:94:0x013f  */
    /* JADX WARN: Code duplicated, block: B:97:0x0145  */
    /* JADX WARN: Code duplicated, block: B:98:0x014c  */
    public static final void LazyList(final Modifier modifier, final LazyListState state, final PaddingValues contentPadding, final boolean reverseLayout, final boolean isVertical, final FlingBehavior flingBehavior, final boolean userScrollEnabled, int beyondBoundsItemCount, Alignment.Horizontal horizontalAlignment, Arrangement.Vertical verticalArrangement, Alignment.Vertical verticalAlignment, Arrangement.Horizontal horizontalArrangement, final Function1<? super LazyListScope, Unit> content, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int $dirty1;
        int beyondBoundsItemCount2;
        Alignment.Horizontal horizontalAlignment2;
        Arrangement.Vertical verticalArrangement2;
        Alignment.Vertical verticalAlignment2;
        Arrangement.Horizontal horizontalArrangement2;
        int $dirty2;
        Composer $composer2;
        Orientation orientation;
        Arrangement.Horizontal horizontalArrangement3;
        Alignment.Vertical verticalAlignment3;
        Arrangement.Vertical verticalArrangement3;
        Alignment.Horizontal horizontalAlignment3;
        int beyondBoundsItemCount3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(contentPadding, "contentPadding");
        Intrinsics.checkNotNullParameter(flingBehavior, "flingBehavior");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(620764179);
        ComposerKt.sourceInformation($composer3, "C(LazyList)P(7,9,2,8,6,3,10!1,4,12,11,5)78@3680L50,80@3756L48,82@3830L292,95@4128L48,97@4224L18,103@4479L277,111@4820L164,121@5208L7,99@4334L1359:LazyList.kt#428nma");
        int $dirty = $changed;
        int $dirty3 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer3.changed(state) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer3.changed(contentPadding) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(reverseLayout) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty |= $composer3.changed(isVertical) ? 16384 : 8192;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
                i2 = $composer3.changed(flingBehavior) ? 131072 : 65536;
            }
            if ((i & 64) != 0) {
                $dirty |= 1572864;
            } else if (($changed & 3670016) != 0) {
                if ($composer3.changed(userScrollEnabled)) {
                    i3 = 1048576;
                } else {
                    i3 = 524288;
                }
                $dirty |= i3;
            }
            i4 = i & 128;
            if (i4 != 0) {
                $dirty |= 12582912;
            } else if (($changed & 29360128) != 0) {
                if ($composer3.changed(beyondBoundsItemCount)) {
                    i5 = 8388608;
                } else {
                    i5 = 4194304;
                }
                $dirty |= i5;
            }
            i6 = i & 256;
            if (i6 != 0) {
                $dirty |= 100663296;
            } else if (($changed & 234881024) != 0) {
                if ($composer3.changed(horizontalAlignment)) {
                    i7 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i7 = 33554432;
                }
                $dirty |= i7;
            }
            i8 = i & 512;
            if (i8 != 0) {
                $dirty |= 805306368;
            } else if (($changed & 1879048192) != 0) {
                if ($composer3.changed(verticalArrangement)) {
                    i9 = 536870912;
                } else {
                    i9 = 268435456;
                }
                $dirty |= i9;
            }
            i10 = i & 1024;
            if (i10 != 0) {
                $dirty3 |= 6;
            } else if (($changed1 & 14) != 0) {
                $dirty3 |= $composer3.changed(verticalAlignment) ? 4 : 2;
            }
            i11 = i & 2048;
            if (i11 != 0) {
                $dirty3 |= 48;
            } else if (($changed1 & 112) != 0) {
                $dirty3 |= $composer3.changed(horizontalArrangement) ? 32 : 16;
            }
            if ((i & 4096) != 0) {
                $dirty3 |= 384;
            } else if (($changed1 & 896) == 0) {
                $dirty3 |= $composer3.changedInstance(content) ? 256 : 128;
            }
            $dirty1 = $dirty3;
            if ((1533916891 & $dirty) != 306783378 && ($dirty1 & 731) == 146 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                beyondBoundsItemCount3 = beyondBoundsItemCount;
                horizontalAlignment3 = horizontalAlignment;
                verticalArrangement3 = verticalArrangement;
                verticalAlignment3 = verticalAlignment;
                horizontalArrangement3 = horizontalArrangement;
                $composer2 = $composer3;
                $dirty2 = $dirty;
            } else {
                if (i4 != 0) {
                    beyondBoundsItemCount2 = 0;
                } else {
                    beyondBoundsItemCount2 = beyondBoundsItemCount;
                }
                if (i6 != 0) {
                    horizontalAlignment2 = null;
                } else {
                    horizontalAlignment2 = horizontalAlignment;
                }
                if (i8 != 0) {
                    verticalArrangement2 = null;
                } else {
                    verticalArrangement2 = verticalArrangement;
                }
                if (i10 != 0) {
                    verticalAlignment2 = null;
                } else {
                    verticalAlignment2 = verticalAlignment;
                }
                if (i11 != 0) {
                    horizontalArrangement2 = null;
                } else {
                    horizontalArrangement2 = horizontalArrangement;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(620764179, $dirty, $dirty1, "androidx.compose.foundation.lazy.LazyList (LazyList.kt:50)");
                }
                Function0<LazyListItemProvider> function0RememberLazyListItemProviderLambda = LazyListItemProviderKt.rememberLazyListItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 >> 3) & 112));
                LazyLayoutSemanticState semanticState = LazyListSemanticsKt.rememberLazyListSemanticState(state, isVertical, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
                Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyListMeasurePolicy = rememberLazyListMeasurePolicy(function0RememberLazyListItemProviderLambda, state, contentPadding, reverseLayout, isVertical, beyondBoundsItemCount2, horizontalAlignment2, verticalAlignment2, horizontalArrangement2, verticalArrangement2, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | (($dirty >> 6) & 458752) | (($dirty >> 6) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024) | (1879048192 & $dirty), 0);
                $dirty2 = $dirty;
                $composer2 = $composer3;
                ScrollPositionUpdater(function0RememberLazyListItemProviderLambda, state, $composer2, $dirty2 & 112);
                OverscrollEffect overscrollEffect = ScrollableDefaults.INSTANCE.overscrollEffect($composer2, 6);
                if (isVertical) {
                    orientation = Orientation.Vertical;
                } else {
                    orientation = Orientation.Horizontal;
                }
                Orientation orientation2 = orientation;
                Modifier modifierOverscroll = OverscrollKt.overscroll(LazyListBeyondBoundsModifierKt.lazyListBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyListItemProviderLambda, semanticState, orientation2, userScrollEnabled, reverseLayout, $composer2, (($dirty2 >> 6) & 57344) | (($dirty2 << 6) & 458752)), orientation2), state, beyondBoundsItemCount2, reverseLayout, orientation2, $composer2, ($dirty2 & 112) | (($dirty2 >> 15) & 896) | ($dirty2 & 7168)), overscrollEffect);
                ScrollableDefaults scrollableDefaults = ScrollableDefaults.INSTANCE;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LazyLayoutKt.LazyLayout(function0RememberLazyListItemProviderLambda, ScrollableKt.scrollable(modifierOverscroll, state, orientation2, overscrollEffect, userScrollEnabled, scrollableDefaults.reverseDirection((LayoutDirection) objConsume, orientation2, reverseLayout), flingBehavior, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyListMeasurePolicy, $composer2, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                horizontalArrangement3 = horizontalArrangement2;
                verticalAlignment3 = verticalAlignment2;
                verticalArrangement3 = verticalArrangement2;
                horizontalAlignment3 = horizontalAlignment2;
                beyondBoundsItemCount3 = beyondBoundsItemCount2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final int i12 = beyondBoundsItemCount3;
            final Alignment.Horizontal horizontal = horizontalAlignment3;
            final Arrangement.Vertical vertical = verticalArrangement3;
            final Alignment.Vertical vertical2 = verticalAlignment3;
            final Arrangement.Horizontal horizontal2 = horizontalArrangement3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListKt.LazyList.1
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

                public final void invoke(Composer composer, int i13) {
                    LazyListKt.LazyList(modifier, state, contentPadding, reverseLayout, isVertical, flingBehavior, userScrollEnabled, i12, horizontal, vertical, vertical2, horizontal2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty |= i2;
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) != 0) {
            if ($composer3.changed(userScrollEnabled)) {
                i3 = 1048576;
            } else {
                i3 = 524288;
            }
            $dirty |= i3;
        }
        i4 = i & 128;
        if (i4 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 29360128) != 0) {
            if ($composer3.changed(beyondBoundsItemCount)) {
                i5 = 8388608;
            } else {
                i5 = 4194304;
            }
            $dirty |= i5;
        }
        i6 = i & 256;
        if (i6 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) != 0) {
            if ($composer3.changed(horizontalAlignment)) {
                i7 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i7 = 33554432;
            }
            $dirty |= i7;
        }
        i8 = i & 512;
        if (i8 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) != 0) {
            if ($composer3.changed(verticalArrangement)) {
                i9 = 536870912;
            } else {
                i9 = 268435456;
            }
            $dirty |= i9;
        }
        i10 = i & 1024;
        if (i10 != 0) {
            $dirty3 |= 6;
        } else if (($changed1 & 14) != 0) {
            $dirty3 |= $composer3.changed(verticalAlignment) ? 4 : 2;
        }
        i11 = i & 2048;
        if (i11 != 0) {
            $dirty3 |= 48;
        } else if (($changed1 & 112) != 0) {
            $dirty3 |= $composer3.changed(horizontalArrangement) ? 32 : 16;
        }
        if ((i & 4096) != 0) {
            $dirty3 |= 384;
        } else if (($changed1 & 896) == 0) {
            $dirty3 |= $composer3.changedInstance(content) ? 256 : 128;
        }
        $dirty1 = $dirty3;
        if ((1533916891 & $dirty) != 306783378) {
            if (i4 != 0) {
                beyondBoundsItemCount2 = 0;
            } else {
                beyondBoundsItemCount2 = beyondBoundsItemCount;
            }
            if (i6 != 0) {
                horizontalAlignment2 = null;
            } else {
                horizontalAlignment2 = horizontalAlignment;
            }
            if (i8 != 0) {
                verticalArrangement2 = null;
            } else {
                verticalArrangement2 = verticalArrangement;
            }
            if (i10 != 0) {
                verticalAlignment2 = null;
            } else {
                verticalAlignment2 = verticalAlignment;
            }
            if (i11 != 0) {
                horizontalArrangement2 = null;
            } else {
                horizontalArrangement2 = horizontalArrangement;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(620764179, $dirty, $dirty1, "androidx.compose.foundation.lazy.LazyList (LazyList.kt:50)");
            }
            Function0<LazyListItemProvider> function0RememberLazyListItemProviderLambda2 = LazyListItemProviderKt.rememberLazyListItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 >> 3) & 112));
            LazyLayoutSemanticState semanticState2 = LazyListSemanticsKt.rememberLazyListSemanticState(state, isVertical, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
            Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyListMeasurePolicy2 = rememberLazyListMeasurePolicy(function0RememberLazyListItemProviderLambda2, state, contentPadding, reverseLayout, isVertical, beyondBoundsItemCount2, horizontalAlignment2, verticalAlignment2, horizontalArrangement2, verticalArrangement2, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | (($dirty >> 6) & 458752) | (($dirty >> 6) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024) | (1879048192 & $dirty), 0);
            $dirty2 = $dirty;
            $composer2 = $composer3;
            ScrollPositionUpdater(function0RememberLazyListItemProviderLambda2, state, $composer2, $dirty2 & 112);
            OverscrollEffect overscrollEffect2 = ScrollableDefaults.INSTANCE.overscrollEffect($composer2, 6);
            if (isVertical) {
                orientation = Orientation.Vertical;
            } else {
                orientation = Orientation.Horizontal;
            }
            Orientation orientation3 = orientation;
            Modifier modifierOverscroll2 = OverscrollKt.overscroll(LazyListBeyondBoundsModifierKt.lazyListBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyListItemProviderLambda2, semanticState2, orientation3, userScrollEnabled, reverseLayout, $composer2, (($dirty2 >> 6) & 57344) | (($dirty2 << 6) & 458752)), orientation3), state, beyondBoundsItemCount2, reverseLayout, orientation3, $composer2, ($dirty2 & 112) | (($dirty2 >> 15) & 896) | ($dirty2 & 7168)), overscrollEffect2);
            ScrollableDefaults scrollableDefaults2 = ScrollableDefaults.INSTANCE;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyLayoutKt.LazyLayout(function0RememberLazyListItemProviderLambda2, ScrollableKt.scrollable(modifierOverscroll2, state, orientation3, overscrollEffect2, userScrollEnabled, scrollableDefaults2.reverseDirection((LayoutDirection) objConsume2, orientation3, reverseLayout), flingBehavior, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyListMeasurePolicy2, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            horizontalArrangement3 = horizontalArrangement2;
            verticalAlignment3 = verticalAlignment2;
            verticalArrangement3 = verticalArrangement2;
            horizontalAlignment3 = horizontalAlignment2;
            beyondBoundsItemCount3 = beyondBoundsItemCount2;
        } else {
            if (i4 != 0) {
                beyondBoundsItemCount2 = 0;
            } else {
                beyondBoundsItemCount2 = beyondBoundsItemCount;
            }
            if (i6 != 0) {
                horizontalAlignment2 = null;
            } else {
                horizontalAlignment2 = horizontalAlignment;
            }
            if (i8 != 0) {
                verticalArrangement2 = null;
            } else {
                verticalArrangement2 = verticalArrangement;
            }
            if (i10 != 0) {
                verticalAlignment2 = null;
            } else {
                verticalAlignment2 = verticalAlignment;
            }
            if (i11 != 0) {
                horizontalArrangement2 = null;
            } else {
                horizontalArrangement2 = horizontalArrangement;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(620764179, $dirty, $dirty1, "androidx.compose.foundation.lazy.LazyList (LazyList.kt:50)");
            }
            Function0<LazyListItemProvider> function0RememberLazyListItemProviderLambda3 = LazyListItemProviderKt.rememberLazyListItemProviderLambda(state, content, $composer3, (($dirty >> 3) & 14) | (($dirty1 >> 3) & 112));
            LazyLayoutSemanticState semanticState3 = LazyListSemanticsKt.rememberLazyListSemanticState(state, isVertical, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
            Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2RememberLazyListMeasurePolicy3 = rememberLazyListMeasurePolicy(function0RememberLazyListItemProviderLambda3, state, contentPadding, reverseLayout, isVertical, beyondBoundsItemCount2, horizontalAlignment2, verticalAlignment2, horizontalArrangement2, verticalArrangement2, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | (($dirty >> 6) & 458752) | (($dirty >> 6) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024) | (1879048192 & $dirty), 0);
            $dirty2 = $dirty;
            $composer2 = $composer3;
            ScrollPositionUpdater(function0RememberLazyListItemProviderLambda3, state, $composer2, $dirty2 & 112);
            OverscrollEffect overscrollEffect3 = ScrollableDefaults.INSTANCE.overscrollEffect($composer2, 6);
            if (isVertical) {
                orientation = Orientation.Vertical;
            } else {
                orientation = Orientation.Horizontal;
            }
            Orientation orientation4 = orientation;
            Modifier modifierOverscroll3 = OverscrollKt.overscroll(LazyListBeyondBoundsModifierKt.lazyListBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberLazyListItemProviderLambda3, semanticState3, orientation4, userScrollEnabled, reverseLayout, $composer2, (($dirty2 >> 6) & 57344) | (($dirty2 << 6) & 458752)), orientation4), state, beyondBoundsItemCount2, reverseLayout, orientation4, $composer2, ($dirty2 & 112) | (($dirty2 >> 15) & 896) | ($dirty2 & 7168)), overscrollEffect3);
            ScrollableDefaults scrollableDefaults3 = ScrollableDefaults.INSTANCE;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyLayoutKt.LazyLayout(function0RememberLazyListItemProviderLambda3, ScrollableKt.scrollable(modifierOverscroll3, state, orientation4, overscrollEffect3, userScrollEnabled, scrollableDefaults3.reverseDirection((LayoutDirection) objConsume3, orientation4, reverseLayout), flingBehavior, state.getInternalInteractionSource()), state.getPrefetchState(), function2RememberLazyListMeasurePolicy3, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            horizontalArrangement3 = horizontalArrangement2;
            verticalAlignment3 = verticalAlignment2;
            verticalArrangement3 = verticalArrangement2;
            horizontalAlignment3 = horizontalAlignment2;
            beyondBoundsItemCount3 = beyondBoundsItemCount2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final int i13 = beyondBoundsItemCount3;
        final Alignment.Horizontal horizontal3 = horizontalAlignment3;
        final Arrangement.Vertical vertical3 = verticalArrangement3;
        final Alignment.Vertical vertical4 = verticalAlignment3;
        final Arrangement.Horizontal horizontal4 = horizontalArrangement3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListKt.LazyList.1
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

            public final void invoke(Composer composer, int i14) {
                LazyListKt.LazyList(modifier, state, contentPadding, reverseLayout, isVertical, flingBehavior, userScrollEnabled, i13, horizontal3, vertical3, vertical4, horizontal4, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ScrollPositionUpdater(final Function0<? extends LazyListItemProvider> function0, final LazyListState state, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-331135862);
        ComposerKt.sourceInformation($composer2, "C(ScrollPositionUpdater):LazyList.kt#428nma");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(state) ? 32 : 16;
        }
        if (($dirty & 91) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-331135862, $changed, -1, "androidx.compose.foundation.lazy.ScrollPositionUpdater (LazyList.kt:140)");
            }
            LazyListItemProvider itemProvider = function0.invoke();
            if (itemProvider.getItemCount() > 0) {
                LazyListState.updateScrollPositionIfTheFirstItemWasMoved$foundation_release$default(state, itemProvider, 0, 2, null);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListKt.ScrollPositionUpdater.1
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
                LazyListKt.ScrollPositionUpdater(function0, state, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    private static final Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> rememberLazyListMeasurePolicy(final Function0<? extends LazyListItemProvider> function0, final LazyListState state, final PaddingValues contentPadding, final boolean reverseLayout, final boolean isVertical, final int beyondBoundsItemCount, Alignment.Horizontal horizontalAlignment, Alignment.Vertical verticalAlignment, Arrangement.Horizontal horizontalArrangement, Arrangement.Vertical verticalArrangement, Composer $composer, int $changed, int i) {
        $composer.startReplaceableGroup(183156450);
        ComposerKt.sourceInformation($composer, "C(rememberLazyListMeasurePolicy)P(5,7,1,6,4!2,8)173@7248L7052:LazyList.kt#428nma");
        Alignment.Horizontal horizontalAlignment2 = (i & 64) != 0 ? null : horizontalAlignment;
        Alignment.Vertical verticalAlignment2 = (i & 128) != 0 ? null : verticalAlignment;
        Arrangement.Horizontal horizontalArrangement2 = (i & 256) != 0 ? null : horizontalArrangement;
        Arrangement.Vertical verticalArrangement2 = (i & 512) != 0 ? null : verticalArrangement;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(183156450, $changed, -1, "androidx.compose.foundation.lazy.rememberLazyListMeasurePolicy (LazyList.kt:152)");
        }
        Object[] keys$iv = {state, contentPadding, Boolean.valueOf(reverseLayout), Boolean.valueOf(isVertical), horizontalAlignment2, verticalAlignment2, horizontalArrangement2, verticalArrangement2};
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
            final Alignment.Horizontal horizontal2 = horizontalAlignment2;
            final Alignment.Vertical vertical2 = verticalAlignment2;
            value$iv$iv = new Function2<LazyLayoutMeasureScope, Constraints, LazyListMeasureResult>() { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyListMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                    return m590invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyListMeasureResult m590invoke0kLqBqw(final LazyLayoutMeasureScope $this$null, final long containerConstraints) throws Throwable {
                    int i2;
                    int i3;
                    int i4;
                    float spacing;
                    int iM5218getMaxWidthimpl;
                    final long visualItemOffset;
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
                    final long contentConstraints = ConstraintsKt.m5234offsetNN6EwU(containerConstraints, -totalHorizontalPadding, -totalVerticalPadding);
                    state.setDensity$foundation_release($this$null);
                    final LazyListItemProvider itemProvider = function0.invoke();
                    itemProvider.getItemScope().setMaxSize(Constraints.m5218getMaxWidthimpl(contentConstraints), Constraints.m5217getMaxHeightimpl(contentConstraints));
                    if (isVertical) {
                        Arrangement.Vertical vertical3 = vertical;
                        if (vertical3 == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        spacing = vertical3.getSpacing();
                    } else {
                        Arrangement.Horizontal horizontal3 = horizontal;
                        if (horizontal3 == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        spacing = horizontal3.getSpacing();
                    }
                    float spaceBetweenItemsDp = spacing;
                    final int spaceBetweenItems = $this$null.mo321roundToPx0680j_4(spaceBetweenItemsDp);
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
                    final Alignment.Horizontal horizontal4 = horizontal2;
                    final Alignment.Vertical vertical4 = vertical2;
                    final boolean z4 = reverseLayout;
                    LazyListMeasuredItemProvider lazyListMeasuredItemProvider = new LazyListMeasuredItemProvider(contentConstraints, z3, itemProvider, $this$null, itemsCount, spaceBetweenItems, horizontal4, vertical4, z4, beforeContentPadding, afterContentPadding, visualItemOffset) { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1
                        final /* synthetic */ int $afterContentPadding;
                        final /* synthetic */ int $beforeContentPadding;
                        final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
                        final /* synthetic */ boolean $isVertical;
                        final /* synthetic */ int $itemsCount;
                        final /* synthetic */ boolean $reverseLayout;
                        final /* synthetic */ int $spaceBetweenItems;
                        final /* synthetic */ LazyLayoutMeasureScope $this_null;
                        final /* synthetic */ Alignment.Vertical $verticalAlignment;
                        final /* synthetic */ long $visualItemOffset;

                        {
                            this.$isVertical = z3;
                            this.$this_null = $this$null;
                            this.$itemsCount = itemsCount;
                            this.$spaceBetweenItems = spaceBetweenItems;
                            this.$horizontalAlignment = horizontal4;
                            this.$verticalAlignment = vertical4;
                            this.$reverseLayout = z4;
                            this.$beforeContentPadding = beforeContentPadding;
                            this.$afterContentPadding = afterContentPadding;
                            this.$visualItemOffset = visualItemOffset;
                        }

                        @Override // androidx.compose.foundation.lazy.LazyListMeasuredItemProvider
                        public LazyListMeasuredItem createItem(int index, Object key, Object contentType, List<? extends Placeable> placeables) {
                            Intrinsics.checkNotNullParameter(key, "key");
                            Intrinsics.checkNotNullParameter(placeables, "placeables");
                            int spacing2 = index == this.$itemsCount + (-1) ? 0 : this.$spaceBetweenItems;
                            return new LazyListMeasuredItem(index, placeables, this.$isVertical, this.$horizontalAlignment, this.$verticalAlignment, this.$this_null.getLayoutDirection(), this.$reverseLayout, this.$beforeContentPadding, this.$afterContentPadding, spacing2, this.$visualItemOffset, key, contentType, null);
                        }
                    };
                    state.m600setPremeasureConstraintsBRTryo0$foundation_release(lazyListMeasuredItemProvider.getChildConstraints());
                    Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                    LazyListState lazyListState = state;
                    Snapshot snapshot$iv = this_$iv.createNonObservableSnapshot();
                    try {
                        Snapshot previous$iv$iv = snapshot$iv.makeCurrent();
                        try {
                            try {
                                int firstVisibleItemIndex = lazyListState.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(itemProvider, lazyListState.getFirstVisibleItemIndex());
                                try {
                                    int firstVisibleScrollOffset = lazyListState.getFirstVisibleItemScrollOffset();
                                    try {
                                        Unit unit = Unit.INSTANCE;
                                        try {
                                            snapshot$iv.restoreCurrent(previous$iv$iv);
                                            snapshot$iv.dispose();
                                            LazyListMeasureResult it = LazyListMeasureKt.m594measureLazyListCD5nmq0(itemsCount, lazyListMeasuredItemProvider, mainAxisAvailableSize, beforeContentPadding, afterContentPadding, spaceBetweenItems, firstVisibleItemIndex, firstVisibleScrollOffset, state.getScrollToBeConsumed(), contentConstraints, isVertical, itemProvider.getHeaderIndexes(), vertical, horizontal, reverseLayout, $this$null, state.getPlacementAnimator(), beyondBoundsItemCount, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, state.getPinnedItems(), state.getBeyondBoundsInfo()), new Function3<Integer, Integer, Function1<? super Placeable.PlacementScope, ? extends Unit>, MeasureResult>() { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1.2
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
                                            snapshot$iv.dispose();
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        try {
                                            snapshot$iv.restoreCurrent(previous$iv$iv);
                                            throw th;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            snapshot$iv.dispose();
                                            throw th;
                                        }
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                    }
                }
            };
            $composer.updateRememberedValue(value$iv$iv);
        }
        $composer.endReplaceableGroup();
        Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2 = (Function2) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return function2;
    }
}
