package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TabRow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u009d\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00012.\b\u0002\u0010\u0011\u001a(\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u00182\u0013\b\u0002\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\b0\u001a¢\u0006\u0002\b\u00182\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\b0\u001a¢\u0006\u0002\b\u0018H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0093\u0001\u0010\u001e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2.\b\u0002\u0010\u0011\u001a(\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\b0\u0012¢\u0006\u0002\b\u00182\u0013\b\u0002\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\b0\u001a¢\u0006\u0002\b\u00182\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\b0\u001a¢\u0006\u0002\b\u0018H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"ScrollableTabRowMinimumTabWidth", "Landroidx/compose/ui/unit/Dp;", "F", "ScrollableTabRowPadding", "ScrollableTabRowScrollSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "ScrollableTabRow", "", "selectedTabIndex", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "edgePadding", "indicator", "Lkotlin/Function1;", "", "Landroidx/compose/material3/TabPosition;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "tabPositions", "Landroidx/compose/runtime/Composable;", "divider", "Lkotlin/Function0;", "tabs", "ScrollableTabRow-sKfQg0A", "(ILandroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "TabRow", "TabRow-pAZo6Ak", "(ILandroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TabRowKt {
    private static final float ScrollableTabRowMinimumTabWidth = Dp.m5274constructorimpl(90);
    private static final float ScrollableTabRowPadding = Dp.m5274constructorimpl(52);
    private static final AnimationSpec<Float> ScrollableTabRowScrollSpec = AnimationSpecKt.tween$default(250, 0, EasingKt.getFastOutSlowInEasing(), 2, null);

    /* JADX WARN: Code duplicated, block: B:100:0x0137  */
    /* JADX WARN: Code duplicated, block: B:103:0x0143  */
    /* JADX WARN: Code duplicated, block: B:105:0x014e  */
    /* JADX WARN: Code duplicated, block: B:107:0x0160  */
    /* JADX WARN: Code duplicated, block: B:108:0x0167  */
    /* JADX WARN: Code duplicated, block: B:111:0x0171  */
    /* JADX WARN: Code duplicated, block: B:114:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:118:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:119:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:83:0x0103  */
    /* JADX WARN: Code duplicated, block: B:85:0x010a  */
    /* JADX WARN: Code duplicated, block: B:95:0x0129 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:96:0x012b  */
    /* JADX WARN: Code duplicated, block: B:97:0x0130  */
    /* JADX INFO: renamed from: TabRow-pAZo6Ak, reason: not valid java name */
    public static final void m1841TabRowpAZo6Ak(final int selectedTabIndex, Modifier modifier, long containerColor, long contentColor, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> tabs, Composer $composer, final int $changed, final int i) {
        long containerColor2;
        long contentColor2;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3ComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function4;
        int i2;
        Modifier.Companion modifier2;
        final Function2<? super Composer, ? super Integer, Unit> function2M1459getLambda1$material3_release;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function5;
        long containerColor3;
        long contentColor3;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(tabs, "tabs");
        Composer $composer2 = $composer.startRestartGroup(-1199178586);
        ComposerKt.sourceInformation($composer2, "C(TabRow)P(5,4,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3)128@6357L14,129@6414L12,142@6844L1858:TabRow.kt#uh7d8r");
        final int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(selectedTabIndex) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                containerColor2 = containerColor;
                int i4 = $composer2.changed(containerColor2) ? 256 : 128;
                $dirty |= i4;
            } else {
                containerColor2 = containerColor;
            }
            $dirty |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                contentColor2 = contentColor;
                int i5 = $composer2.changed(contentColor2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                contentColor2 = contentColor;
            }
            $dirty |= i5;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            function3ComposableLambda = function3;
        } else if ((57344 & $changed) == 0) {
            function3ComposableLambda = function3;
            $dirty |= $composer2.changedInstance(function3ComposableLambda) ? 16384 : 8192;
        } else {
            function3ComposableLambda = function3;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function4 = function2;
        } else if ((458752 & $changed) == 0) {
            function4 = function2;
            $dirty |= $composer2.changedInstance(function4) ? 131072 : 65536;
        } else {
            function4 = function2;
        }
        if ((i & 64) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(tabs) ? 1048576 : 524288;
            }
            if (($dirty & 2995931) == 599186 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        containerColor2 = TabRowDefaults.INSTANCE.getContainerColor($composer2, 6);
                        $dirty &= -897;
                    }
                    if ((i & 8) != 0) {
                        $dirty &= -7169;
                        contentColor2 = TabRowDefaults.INSTANCE.getContentColor($composer2, 6);
                    }
                    if (i6 != 0) {
                        function3ComposableLambda = ComposableLambdaKt.composableLambda($composer2, -2052073983, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(List<? extends TabPosition> list, Composer composer, Integer num) {
                                invoke((List<TabPosition>) list, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(List<TabPosition> tabPositions, Composer $composer3, int $changed2) {
                                Intrinsics.checkNotNullParameter(tabPositions, "tabPositions");
                                ComposerKt.sourceInformation($composer3, "C132@6608L100:TabRow.kt#uh7d8r");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2052073983, $changed2, -1, "androidx.compose.material3.TabRow.<anonymous> (TabRow.kt:130)");
                                }
                                if (selectedTabIndex < tabPositions.size()) {
                                    TabRowDefaults.INSTANCE.m1839Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, tabPositions.get(selectedTabIndex)), 0.0f, 0L, $composer3, 3072, 6);
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        });
                    }
                    if (i7 != 0) {
                        function2M1459getLambda1$material3_release = ComposableSingletons$TabRowKt.INSTANCE.m1459getLambda1$material3_release();
                    } else {
                        function2M1459getLambda1$material3_release = function4;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty &= -897;
                    }
                    if ((i & 8) != 0) {
                        $dirty &= -7169;
                        function2M1459getLambda1$material3_release = function4;
                        modifier2 = modifier;
                    } else {
                        modifier2 = modifier;
                        function2M1459getLambda1$material3_release = function4;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1199178586, $dirty, -1, "androidx.compose.material3.TabRow (TabRow.kt:125)");
                }
                SurfaceKt.m1806SurfaceT9BRK9s(SelectableGroupKt.selectableGroup(modifier2), null, containerColor2, contentColor2, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1273256619, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$2
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
                        ComposerKt.sourceInformation($composer3, "C147@7026L1670,147@6984L1712:TabRow.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1273256619, $changed2, -1, "androidx.compose.material3.TabRow.<anonymous> (TabRow.kt:146)");
                        }
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Object key1$iv = tabs;
                        Object key2$iv = function2M1459getLambda1$material3_release;
                        Object key3$iv = function3ComposableLambda;
                        final Function2<Composer, Integer, Unit> function7 = tabs;
                        final Function2<Composer, Integer, Unit> function8 = function2M1459getLambda1$material3_release;
                        final Function3<List<TabPosition>, Composer, Integer, Unit> function9 = function3ComposableLambda;
                        final int i8 = $dirty;
                        int i9 = ((i8 >> 18) & 14) | ((i8 >> 12) & 112) | ((i8 >> 6) & 896);
                        $composer3.startReplaceableGroup(1618982084);
                        ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                        boolean invalid$iv$iv = $composer3.changed(key1$iv) | $composer3.changed(key2$iv) | $composer3.changed(key3$iv);
                        Object value$iv$iv = $composer3.rememberedValue();
                        if (invalid$iv$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.TabRowKt$TabRow$2$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                    return m1843invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                                }

                                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                                public final MeasureResult m1843invoke0kLqBqw(final SubcomposeMeasureScope SubcomposeLayout, final long constraints) {
                                    Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                                    final int tabRowWidth = Constraints.m5218getMaxWidthimpl(constraints);
                                    List<Measurable> listSubcompose = SubcomposeLayout.subcompose(TabSlots.Tabs, function7);
                                    int tabCount = listSubcompose.size();
                                    final Ref.IntRef tabWidth = new Ref.IntRef();
                                    if (tabCount > 0) {
                                        tabWidth.element = tabRowWidth / tabCount;
                                    }
                                    List<Measurable> $this$fold$iv = listSubcompose;
                                    int accumulator$iv = 0;
                                    for (Object element$iv : $this$fold$iv) {
                                        Measurable curr = (Measurable) element$iv;
                                        int max = accumulator$iv;
                                        accumulator$iv = Math.max(curr.maxIntrinsicHeight(tabWidth.element), max);
                                    }
                                    List<Measurable> $this$map$iv = listSubcompose;
                                    int $i$f$map = 0;
                                    Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                    for (Object item$iv$iv : $this$map$iv) {
                                        Measurable it = (Measurable) item$iv$iv;
                                        int $i$f$map2 = tabWidth.element;
                                        destination$iv$iv.add(it.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(constraints, $i$f$map2, tabWidth.element, accumulator$iv, accumulator$iv)));
                                        $i$f$map = $i$f$map;
                                        $this$map$iv = $this$map$iv;
                                    }
                                    final List tabPlaceables = (List) destination$iv$iv;
                                    ArrayList arrayList = new ArrayList(tabCount);
                                    for (int i10 = 0; i10 < tabCount; i10++) {
                                        int index = i10;
                                        float arg0$iv = SubcomposeLayout.mo324toDpu2uoSUM(tabWidth.element);
                                        arrayList.add(new TabPosition(Dp.m5274constructorimpl(index * arg0$iv), SubcomposeLayout.mo324toDpu2uoSUM(tabWidth.element), null));
                                    }
                                    final ArrayList tabPositions = arrayList;
                                    final Function2<Composer, Integer, Unit> function10 = function8;
                                    final Function3<List<TabPosition>, Composer, Integer, Unit> function11 = function9;
                                    final int i11 = i8;
                                    final int i12 = accumulator$iv;
                                    return MeasureScope.CC.layout$default(SubcomposeLayout, tabRowWidth, accumulator$iv, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$2$1$1.1
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
                                            Iterable $this$forEachIndexed$iv = tabPlaceables;
                                            Ref.IntRef intRef = tabWidth;
                                            int index$iv = 0;
                                            for (Object item$iv : $this$forEachIndexed$iv) {
                                                int index$iv2 = index$iv + 1;
                                                if (index$iv < 0) {
                                                    CollectionsKt.throwIndexOverflow();
                                                }
                                                int index2 = index$iv;
                                                Placeable.PlacementScope.placeRelative$default(layout, (Placeable) item$iv, index2 * intRef.element, 0, 0.0f, 4, null);
                                                index$iv = index$iv2;
                                            }
                                            Iterable $this$forEach$iv = SubcomposeLayout.subcompose(TabSlots.Divider, function10);
                                            long j = constraints;
                                            int i13 = i12;
                                            for (Object element$iv2 : $this$forEach$iv) {
                                                Measurable it2 = (Measurable) element$iv2;
                                                long j2 = j;
                                                Placeable placeable = it2.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(j2, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(j2) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(j2) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(j2) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(j2) : 0));
                                                Placeable.PlacementScope.placeRelative$default(layout, placeable, 0, i13 - placeable.getHeight(), 0.0f, 4, null);
                                                i13 = i13;
                                                j = j;
                                            }
                                            SubcomposeMeasureScope subcomposeMeasureScope = SubcomposeLayout;
                                            TabSlots tabSlots = TabSlots.Indicator;
                                            final Function3<List<TabPosition>, Composer, Integer, Unit> function12 = function11;
                                            final List<TabPosition> list = tabPositions;
                                            final int i14 = i11;
                                            Iterable $this$forEach$iv2 = subcomposeMeasureScope.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(-976887453, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt.TabRow.2.1.1.1.3
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

                                                public final void invoke(Composer $composer4, int $changed3) {
                                                    ComposerKt.sourceInformation($composer4, "C185@8506L23:TabRow.kt#uh7d8r");
                                                    if (($changed3 & 11) == 2 && $composer4.getSkipping()) {
                                                        $composer4.skipToGroupEnd();
                                                        return;
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-976887453, $changed3, -1, "androidx.compose.material3.TabRow.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:184)");
                                                    }
                                                    function12.invoke(list, $composer4, Integer.valueOf(((i14 >> 9) & 112) | 8));
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                    }
                                                }
                                            }));
                                            int i15 = tabRowWidth;
                                            int i16 = i12;
                                            for (Object element$iv3 : $this$forEach$iv2) {
                                                Measurable it3 = (Measurable) element$iv3;
                                                Placeable.PlacementScope.placeRelative$default(layout, it3.mo4225measureBRTryo0(Constraints.INSTANCE.m5226fixedJhjzzOo(i15, i16)), 0, 0, 0.0f, 4, null);
                                            }
                                        }
                                    }, 4, null);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv$iv);
                        }
                        $composer3.endReplaceableGroup();
                        SubcomposeLayoutKt.SubcomposeLayout(modifierFillMaxWidth$default, (Function2) value$iv$iv, $composer3, 6, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer2, 12582912 | ($dirty & 896) | ($dirty & 7168), 114);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                function5 = function2M1459getLambda1$material3_release;
                containerColor3 = containerColor2;
                contentColor3 = contentColor2;
                function6 = function3ComposableLambda;
            } else {
                $composer2.skipToGroupEnd();
                containerColor3 = containerColor2;
                contentColor3 = contentColor2;
                function6 = function3ComposableLambda;
                function5 = function4;
                modifier3 = modifier;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final long j = containerColor3;
            final long j2 = contentColor3;
            final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function7 = function6;
            final Function2<? super Composer, ? super Integer, Unit> function8 = function5;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$3
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
                    TabRowKt.m1841TabRowpAZo6Ak(selectedTabIndex, modifier4, j, j2, function7, function8, tabs, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
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
                if ((i & 4) != 0) {
                    containerColor2 = TabRowDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    contentColor2 = TabRowDefaults.INSTANCE.getContentColor($composer2, 6);
                }
                if (i6 != 0) {
                    function3ComposableLambda = ComposableLambdaKt.composableLambda($composer2, -2052073983, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(List<? extends TabPosition> list, Composer composer, Integer num) {
                            invoke((List<TabPosition>) list, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(List<TabPosition> tabPositions, Composer $composer3, int $changed2) {
                            Intrinsics.checkNotNullParameter(tabPositions, "tabPositions");
                            ComposerKt.sourceInformation($composer3, "C132@6608L100:TabRow.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2052073983, $changed2, -1, "androidx.compose.material3.TabRow.<anonymous> (TabRow.kt:130)");
                            }
                            if (selectedTabIndex < tabPositions.size()) {
                                TabRowDefaults.INSTANCE.m1839Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, tabPositions.get(selectedTabIndex)), 0.0f, 0L, $composer3, 3072, 6);
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                }
                if (i7 != 0) {
                    function2M1459getLambda1$material3_release = ComposableSingletons$TabRowKt.INSTANCE.m1459getLambda1$material3_release();
                } else {
                    function2M1459getLambda1$material3_release = function4;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    containerColor2 = TabRowDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    contentColor2 = TabRowDefaults.INSTANCE.getContentColor($composer2, 6);
                }
                if (i6 != 0) {
                    function3ComposableLambda = ComposableLambdaKt.composableLambda($composer2, -2052073983, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(List<? extends TabPosition> list, Composer composer, Integer num) {
                            invoke((List<TabPosition>) list, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(List<TabPosition> tabPositions, Composer $composer3, int $changed2) {
                            Intrinsics.checkNotNullParameter(tabPositions, "tabPositions");
                            ComposerKt.sourceInformation($composer3, "C132@6608L100:TabRow.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2052073983, $changed2, -1, "androidx.compose.material3.TabRow.<anonymous> (TabRow.kt:130)");
                            }
                            if (selectedTabIndex < tabPositions.size()) {
                                TabRowDefaults.INSTANCE.m1839Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, tabPositions.get(selectedTabIndex)), 0.0f, 0L, $composer3, 3072, 6);
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                }
                if (i7 != 0) {
                    function2M1459getLambda1$material3_release = ComposableSingletons$TabRowKt.INSTANCE.m1459getLambda1$material3_release();
                } else {
                    function2M1459getLambda1$material3_release = function4;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1199178586, $dirty, -1, "androidx.compose.material3.TabRow (TabRow.kt:125)");
            }
            SurfaceKt.m1806SurfaceT9BRK9s(SelectableGroupKt.selectableGroup(modifier2), null, containerColor2, contentColor2, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1273256619, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$2
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
                    ComposerKt.sourceInformation($composer3, "C147@7026L1670,147@6984L1712:TabRow.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1273256619, $changed2, -1, "androidx.compose.material3.TabRow.<anonymous> (TabRow.kt:146)");
                    }
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Object key1$iv = tabs;
                    Object key2$iv = function2M1459getLambda1$material3_release;
                    Object key3$iv = function3ComposableLambda;
                    final Function2<? super Composer, ? super Integer, Unit> function9 = tabs;
                    final Function2<? super Composer, ? super Integer, Unit> function10 = function2M1459getLambda1$material3_release;
                    final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function11 = function3ComposableLambda;
                    final int i8 = $dirty;
                    int i9 = ((i8 >> 18) & 14) | ((i8 >> 12) & 112) | ((i8 >> 6) & 896);
                    $composer3.startReplaceableGroup(1618982084);
                    ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                    boolean invalid$iv$iv = $composer3.changed(key1$iv) | $composer3.changed(key2$iv) | $composer3.changed(key3$iv);
                    Object value$iv$iv = $composer3.rememberedValue();
                    if (invalid$iv$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.TabRowKt$TabRow$2$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                return m1843invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                            }

                            /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                            public final MeasureResult m1843invoke0kLqBqw(final SubcomposeMeasureScope SubcomposeLayout, final long constraints) {
                                Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                                final int tabRowWidth = Constraints.m5218getMaxWidthimpl(constraints);
                                List<Measurable> listSubcompose = SubcomposeLayout.subcompose(TabSlots.Tabs, function9);
                                int tabCount = listSubcompose.size();
                                final Ref.IntRef tabWidth = new Ref.IntRef();
                                if (tabCount > 0) {
                                    tabWidth.element = tabRowWidth / tabCount;
                                }
                                List<Measurable> $this$fold$iv = listSubcompose;
                                int accumulator$iv = 0;
                                for (Object element$iv : $this$fold$iv) {
                                    Measurable curr = (Measurable) element$iv;
                                    int max = accumulator$iv;
                                    accumulator$iv = Math.max(curr.maxIntrinsicHeight(tabWidth.element), max);
                                }
                                List<Measurable> $this$map$iv = listSubcompose;
                                int $i$f$map = 0;
                                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                for (Object item$iv$iv : $this$map$iv) {
                                    Measurable it = (Measurable) item$iv$iv;
                                    int $i$f$map2 = tabWidth.element;
                                    destination$iv$iv.add(it.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(constraints, $i$f$map2, tabWidth.element, accumulator$iv, accumulator$iv)));
                                    $i$f$map = $i$f$map;
                                    $this$map$iv = $this$map$iv;
                                }
                                final List<? extends Placeable> tabPlaceables = (List) destination$iv$iv;
                                ArrayList arrayList = new ArrayList(tabCount);
                                for (int i10 = 0; i10 < tabCount; i10++) {
                                    int index = i10;
                                    float arg0$iv = SubcomposeLayout.mo324toDpu2uoSUM(tabWidth.element);
                                    arrayList.add(new TabPosition(Dp.m5274constructorimpl(index * arg0$iv), SubcomposeLayout.mo324toDpu2uoSUM(tabWidth.element), null));
                                }
                                final List<TabPosition> tabPositions = arrayList;
                                final Function2<? super Composer, ? super Integer, Unit> function12 = function10;
                                final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function13 = function11;
                                final int i11 = i8;
                                final int i12 = accumulator$iv;
                                return MeasureScope.CC.layout$default(SubcomposeLayout, tabRowWidth, accumulator$iv, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$2$1$1.1
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
                                        Iterable $this$forEachIndexed$iv = tabPlaceables;
                                        Ref.IntRef intRef = tabWidth;
                                        int index$iv = 0;
                                        for (Object item$iv : $this$forEachIndexed$iv) {
                                            int index$iv2 = index$iv + 1;
                                            if (index$iv < 0) {
                                                CollectionsKt.throwIndexOverflow();
                                            }
                                            int index2 = index$iv;
                                            Placeable.PlacementScope.placeRelative$default(layout, (Placeable) item$iv, index2 * intRef.element, 0, 0.0f, 4, null);
                                            index$iv = index$iv2;
                                        }
                                        Iterable $this$forEach$iv = SubcomposeLayout.subcompose(TabSlots.Divider, function12);
                                        long j3 = constraints;
                                        int i13 = i12;
                                        for (Object element$iv2 : $this$forEach$iv) {
                                            Measurable it2 = (Measurable) element$iv2;
                                            long j4 = j3;
                                            Placeable placeable = it2.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(j4, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(j4) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(j4) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(j4) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(j4) : 0));
                                            Placeable.PlacementScope.placeRelative$default(layout, placeable, 0, i13 - placeable.getHeight(), 0.0f, 4, null);
                                            i13 = i13;
                                            j3 = j3;
                                        }
                                        SubcomposeMeasureScope subcomposeMeasureScope = SubcomposeLayout;
                                        TabSlots tabSlots = TabSlots.Indicator;
                                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function14 = function13;
                                        final List<TabPosition> list = tabPositions;
                                        final int i14 = i11;
                                        Iterable $this$forEach$iv2 = subcomposeMeasureScope.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(-976887453, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt.TabRow.2.1.1.1.3
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

                                            public final void invoke(Composer $composer4, int $changed3) {
                                                ComposerKt.sourceInformation($composer4, "C185@8506L23:TabRow.kt#uh7d8r");
                                                if (($changed3 & 11) == 2 && $composer4.getSkipping()) {
                                                    $composer4.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-976887453, $changed3, -1, "androidx.compose.material3.TabRow.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:184)");
                                                }
                                                function14.invoke(list, $composer4, Integer.valueOf(((i14 >> 9) & 112) | 8));
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }));
                                        int i15 = tabRowWidth;
                                        int i16 = i12;
                                        for (Object element$iv3 : $this$forEach$iv2) {
                                            Measurable it3 = (Measurable) element$iv3;
                                            Placeable.PlacementScope.placeRelative$default(layout, it3.mo4225measureBRTryo0(Constraints.INSTANCE.m5226fixedJhjzzOo(i15, i16)), 0, 0, 0.0f, 4, null);
                                        }
                                    }
                                }, 4, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv$iv);
                    }
                    $composer3.endReplaceableGroup();
                    SubcomposeLayoutKt.SubcomposeLayout(modifierFillMaxWidth$default, (Function2) value$iv$iv, $composer3, 6, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, 12582912 | ($dirty & 896) | ($dirty & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            function5 = function2M1459getLambda1$material3_release;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            function6 = function3ComposableLambda;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    containerColor2 = TabRowDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    contentColor2 = TabRowDefaults.INSTANCE.getContentColor($composer2, 6);
                }
                if (i6 != 0) {
                    function3ComposableLambda = ComposableLambdaKt.composableLambda($composer2, -2052073983, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(List<? extends TabPosition> list, Composer composer, Integer num) {
                            invoke((List<TabPosition>) list, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(List<TabPosition> tabPositions, Composer $composer3, int $changed2) {
                            Intrinsics.checkNotNullParameter(tabPositions, "tabPositions");
                            ComposerKt.sourceInformation($composer3, "C132@6608L100:TabRow.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2052073983, $changed2, -1, "androidx.compose.material3.TabRow.<anonymous> (TabRow.kt:130)");
                            }
                            if (selectedTabIndex < tabPositions.size()) {
                                TabRowDefaults.INSTANCE.m1839Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, tabPositions.get(selectedTabIndex)), 0.0f, 0L, $composer3, 3072, 6);
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                }
                if (i7 != 0) {
                    function2M1459getLambda1$material3_release = ComposableSingletons$TabRowKt.INSTANCE.m1459getLambda1$material3_release();
                } else {
                    function2M1459getLambda1$material3_release = function4;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    containerColor2 = TabRowDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    contentColor2 = TabRowDefaults.INSTANCE.getContentColor($composer2, 6);
                }
                if (i6 != 0) {
                    function3ComposableLambda = ComposableLambdaKt.composableLambda($composer2, -2052073983, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(List<? extends TabPosition> list, Composer composer, Integer num) {
                            invoke((List<TabPosition>) list, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(List<TabPosition> tabPositions, Composer $composer3, int $changed2) {
                            Intrinsics.checkNotNullParameter(tabPositions, "tabPositions");
                            ComposerKt.sourceInformation($composer3, "C132@6608L100:TabRow.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2052073983, $changed2, -1, "androidx.compose.material3.TabRow.<anonymous> (TabRow.kt:130)");
                            }
                            if (selectedTabIndex < tabPositions.size()) {
                                TabRowDefaults.INSTANCE.m1839Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, tabPositions.get(selectedTabIndex)), 0.0f, 0L, $composer3, 3072, 6);
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                }
                if (i7 != 0) {
                    function2M1459getLambda1$material3_release = ComposableSingletons$TabRowKt.INSTANCE.m1459getLambda1$material3_release();
                } else {
                    function2M1459getLambda1$material3_release = function4;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1199178586, $dirty, -1, "androidx.compose.material3.TabRow (TabRow.kt:125)");
            }
            SurfaceKt.m1806SurfaceT9BRK9s(SelectableGroupKt.selectableGroup(modifier2), null, containerColor2, contentColor2, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1273256619, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$2
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
                    ComposerKt.sourceInformation($composer3, "C147@7026L1670,147@6984L1712:TabRow.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1273256619, $changed2, -1, "androidx.compose.material3.TabRow.<anonymous> (TabRow.kt:146)");
                    }
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Object key1$iv = tabs;
                    Object key2$iv = function2M1459getLambda1$material3_release;
                    Object key3$iv = function3ComposableLambda;
                    final Function2<? super Composer, ? super Integer, Unit> function9 = tabs;
                    final Function2<? super Composer, ? super Integer, Unit> function10 = function2M1459getLambda1$material3_release;
                    final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function11 = function3ComposableLambda;
                    final int i8 = $dirty;
                    int i9 = ((i8 >> 18) & 14) | ((i8 >> 12) & 112) | ((i8 >> 6) & 896);
                    $composer3.startReplaceableGroup(1618982084);
                    ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                    boolean invalid$iv$iv = $composer3.changed(key1$iv) | $composer3.changed(key2$iv) | $composer3.changed(key3$iv);
                    Object value$iv$iv = $composer3.rememberedValue();
                    if (invalid$iv$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.TabRowKt$TabRow$2$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                return m1843invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                            }

                            /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                            public final MeasureResult m1843invoke0kLqBqw(final SubcomposeMeasureScope SubcomposeLayout, final long constraints) {
                                Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                                final int tabRowWidth = Constraints.m5218getMaxWidthimpl(constraints);
                                List<Measurable> listSubcompose = SubcomposeLayout.subcompose(TabSlots.Tabs, function9);
                                int tabCount = listSubcompose.size();
                                final Ref.IntRef tabWidth = new Ref.IntRef();
                                if (tabCount > 0) {
                                    tabWidth.element = tabRowWidth / tabCount;
                                }
                                List<Measurable> $this$fold$iv = listSubcompose;
                                int accumulator$iv = 0;
                                for (Object element$iv : $this$fold$iv) {
                                    Measurable curr = (Measurable) element$iv;
                                    int max = accumulator$iv;
                                    accumulator$iv = Math.max(curr.maxIntrinsicHeight(tabWidth.element), max);
                                }
                                List<Measurable> $this$map$iv = listSubcompose;
                                int $i$f$map = 0;
                                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                for (Object item$iv$iv : $this$map$iv) {
                                    Measurable it = (Measurable) item$iv$iv;
                                    int $i$f$map2 = tabWidth.element;
                                    destination$iv$iv.add(it.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(constraints, $i$f$map2, tabWidth.element, accumulator$iv, accumulator$iv)));
                                    $i$f$map = $i$f$map;
                                    $this$map$iv = $this$map$iv;
                                }
                                final List<? extends Placeable> tabPlaceables = (List) destination$iv$iv;
                                ArrayList arrayList = new ArrayList(tabCount);
                                for (int i10 = 0; i10 < tabCount; i10++) {
                                    int index = i10;
                                    float arg0$iv = SubcomposeLayout.mo324toDpu2uoSUM(tabWidth.element);
                                    arrayList.add(new TabPosition(Dp.m5274constructorimpl(index * arg0$iv), SubcomposeLayout.mo324toDpu2uoSUM(tabWidth.element), null));
                                }
                                final List<TabPosition> tabPositions = arrayList;
                                final Function2<? super Composer, ? super Integer, Unit> function12 = function10;
                                final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function13 = function11;
                                final int i11 = i8;
                                final int i12 = accumulator$iv;
                                return MeasureScope.CC.layout$default(SubcomposeLayout, tabRowWidth, accumulator$iv, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$2$1$1.1
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
                                        Iterable $this$forEachIndexed$iv = tabPlaceables;
                                        Ref.IntRef intRef = tabWidth;
                                        int index$iv = 0;
                                        for (Object item$iv : $this$forEachIndexed$iv) {
                                            int index$iv2 = index$iv + 1;
                                            if (index$iv < 0) {
                                                CollectionsKt.throwIndexOverflow();
                                            }
                                            int index2 = index$iv;
                                            Placeable.PlacementScope.placeRelative$default(layout, (Placeable) item$iv, index2 * intRef.element, 0, 0.0f, 4, null);
                                            index$iv = index$iv2;
                                        }
                                        Iterable $this$forEach$iv = SubcomposeLayout.subcompose(TabSlots.Divider, function12);
                                        long j3 = constraints;
                                        int i13 = i12;
                                        for (Object element$iv2 : $this$forEach$iv) {
                                            Measurable it2 = (Measurable) element$iv2;
                                            long j4 = j3;
                                            Placeable placeable = it2.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(j4, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(j4) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(j4) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(j4) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(j4) : 0));
                                            Placeable.PlacementScope.placeRelative$default(layout, placeable, 0, i13 - placeable.getHeight(), 0.0f, 4, null);
                                            i13 = i13;
                                            j3 = j3;
                                        }
                                        SubcomposeMeasureScope subcomposeMeasureScope = SubcomposeLayout;
                                        TabSlots tabSlots = TabSlots.Indicator;
                                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function14 = function13;
                                        final List<TabPosition> list = tabPositions;
                                        final int i14 = i11;
                                        Iterable $this$forEach$iv2 = subcomposeMeasureScope.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(-976887453, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt.TabRow.2.1.1.1.3
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

                                            public final void invoke(Composer $composer4, int $changed3) {
                                                ComposerKt.sourceInformation($composer4, "C185@8506L23:TabRow.kt#uh7d8r");
                                                if (($changed3 & 11) == 2 && $composer4.getSkipping()) {
                                                    $composer4.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-976887453, $changed3, -1, "androidx.compose.material3.TabRow.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:184)");
                                                }
                                                function14.invoke(list, $composer4, Integer.valueOf(((i14 >> 9) & 112) | 8));
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }));
                                        int i15 = tabRowWidth;
                                        int i16 = i12;
                                        for (Object element$iv3 : $this$forEach$iv2) {
                                            Measurable it3 = (Measurable) element$iv3;
                                            Placeable.PlacementScope.placeRelative$default(layout, it3.mo4225measureBRTryo0(Constraints.INSTANCE.m5226fixedJhjzzOo(i15, i16)), 0, 0, 0.0f, 4, null);
                                        }
                                    }
                                }, 4, null);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv$iv);
                    }
                    $composer3.endReplaceableGroup();
                    SubcomposeLayoutKt.SubcomposeLayout(modifierFillMaxWidth$default, (Function2) value$iv$iv, $composer3, 6, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, 12582912 | ($dirty & 896) | ($dirty & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            function5 = function2M1459getLambda1$material3_release;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            function6 = function3ComposableLambda;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final long j3 = containerColor3;
        final long j4 = contentColor3;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function9 = function6;
        final Function2<? super Composer, ? super Integer, Unit> function10 = function5;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$3
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
                TabRowKt.m1841TabRowpAZo6Ak(selectedTabIndex, modifier5, j3, j4, function9, function10, tabs, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: renamed from: ScrollableTabRow-sKfQg0A, reason: not valid java name */
    public static final void m1840ScrollableTabRowsKfQg0A(final int selectedTabIndex, Modifier modifier, long containerColor, long contentColor, float edgePadding, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> tabs, Composer $composer, final int $changed, final int i) {
        long containerColor2;
        long contentColor2;
        float edgePadding2;
        Modifier.Companion modifier2;
        ComposableLambda composableLambda;
        Function2<? super Composer, ? super Integer, Unit> function2M1460getLambda2$material3_release;
        Modifier modifier3;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function4;
        Function2<? super Composer, ? super Integer, Unit> function5;
        long containerColor3;
        long contentColor3;
        float edgePadding3;
        Intrinsics.checkNotNullParameter(tabs, "tabs");
        Composer $composer2 = $composer.startRestartGroup(-497821003);
        ComposerKt.sourceInformation($composer2, "C(ScrollableTabRow)P(6,5,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3:c#ui.unit.Dp,4)230@11025L14,231@11082L12,243@11485L3289:TabRow.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(selectedTabIndex) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                containerColor2 = containerColor;
                int i3 = $composer2.changed(containerColor2) ? 256 : 128;
                $dirty |= i3;
            } else {
                containerColor2 = containerColor;
            }
            $dirty |= i3;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                contentColor2 = contentColor;
                int i4 = $composer2.changed(contentColor2) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                contentColor2 = contentColor;
            }
            $dirty |= i4;
        } else {
            contentColor2 = contentColor;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            edgePadding2 = edgePadding;
        } else if ((57344 & $changed) == 0) {
            edgePadding2 = edgePadding;
            $dirty |= $composer2.changed(edgePadding2) ? 16384 : 8192;
        } else {
            edgePadding2 = edgePadding;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 131072 : 65536;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 1048576 : 524288;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if ((29360128 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(tabs) ? 8388608 : 4194304;
        }
        if ((23967451 & $dirty) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            function4 = function3;
            function5 = function2;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            edgePadding3 = edgePadding2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if ((i & 4) != 0) {
                    containerColor2 = TabRowDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    contentColor2 = TabRowDefaults.INSTANCE.getContentColor($composer2, 6);
                }
                if (i5 != 0) {
                    edgePadding2 = ScrollableTabRowPadding;
                }
                composableLambda = i6 != 0 ? ComposableLambdaKt.composableLambda($composer2, -913748678, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRow$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(List<? extends TabPosition> list, Composer composer, Integer num) {
                        invoke((List<TabPosition>) list, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(List<TabPosition> tabPositions, Composer $composer3, int $changed2) {
                        Intrinsics.checkNotNullParameter(tabPositions, "tabPositions");
                        ComposerKt.sourceInformation($composer3, "C234@11267L92:TabRow.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-913748678, $changed2, -1, "androidx.compose.material3.ScrollableTabRow.<anonymous> (TabRow.kt:233)");
                        }
                        TabRowDefaults.INSTANCE.m1839Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, tabPositions.get(selectedTabIndex)), 0.0f, 0L, $composer3, 3072, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }) : function3;
                function2M1460getLambda2$material3_release = i7 != 0 ? ComposableSingletons$TabRowKt.INSTANCE.m1460getLambda2$material3_release() : function2;
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    composableLambda = function3;
                    function2M1460getLambda2$material3_release = function2;
                    $dirty &= -7169;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                    composableLambda = function3;
                    function2M1460getLambda2$material3_release = function2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-497821003, $dirty, -1, "androidx.compose.material3.ScrollableTabRow (TabRow.kt:227)");
            }
            final float f = edgePadding2;
            final Function2<? super Composer, ? super Integer, Unit> function6 = function2M1460getLambda2$material3_release;
            final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function7 = composableLambda;
            final int i8 = $dirty;
            SurfaceKt.m1806SurfaceT9BRK9s(modifier2, null, containerColor2, contentColor2, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 286469328, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRow$2
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
                    Object value$iv$iv;
                    ComposerKt.sourceInformation($composer3, "C248@11625L21,249@11676L24,250@11733L185,256@11927L2841:TabRow.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(286469328, $changed2, -1, "androidx.compose.material3.ScrollableTabRow.<anonymous> (TabRow.kt:247)");
                        }
                        ScrollState scrollState = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
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
                        CoroutineScope coroutineScope = wrapper$iv.getCoroutineScope();
                        $composer3.endReplaceableGroup();
                        $composer3.startReplaceableGroup(511388516);
                        ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
                        boolean invalid$iv$iv = $composer3.changed(scrollState) | $composer3.changed(coroutineScope);
                        Object it$iv$iv = $composer3.rememberedValue();
                        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = new ScrollableTabData(scrollState, coroutineScope);
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        final ScrollableTabData scrollableTabData = (ScrollableTabData) value$iv$iv;
                        Modifier modifierClipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), scrollState, false, null, false, 14, null)));
                        final float f2 = f;
                        final Function2<Composer, Integer, Unit> function8 = tabs;
                        final Function2<Composer, Integer, Unit> function9 = function6;
                        final int i9 = selectedTabIndex;
                        final Function3<List<TabPosition>, Composer, Integer, Unit> function10 = function7;
                        final int i10 = i8;
                        SubcomposeLayoutKt.SubcomposeLayout(modifierClipToBounds, new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRow$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                return m1842invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                            }

                            /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                            public final MeasureResult m1842invoke0kLqBqw(final SubcomposeMeasureScope SubcomposeLayout, final long constraints) {
                                Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                                int minTabWidth = SubcomposeLayout.mo321roundToPx0680j_4(TabRowKt.ScrollableTabRowMinimumTabWidth);
                                final int padding = SubcomposeLayout.mo321roundToPx0680j_4(f2);
                                Iterable iterableSubcompose = SubcomposeLayout.subcompose(TabSlots.Tabs, function8);
                                Iterable $this$fold$iv = iterableSubcompose;
                                int accumulator$iv = 0;
                                for (Object element$iv : $this$fold$iv) {
                                    Measurable measurable = (Measurable) element$iv;
                                    int curr = accumulator$iv;
                                    accumulator$iv = Math.max(curr, measurable.maxIntrinsicHeight(Integer.MAX_VALUE));
                                }
                                long tabConstraints = Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : minTabWidth, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : accumulator$iv, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : accumulator$iv);
                                Iterable $this$map$iv = iterableSubcompose;
                                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                for (Object item$iv$iv : $this$map$iv) {
                                    Measurable it = (Measurable) item$iv$iv;
                                    destination$iv$iv.add(it.mo4225measureBRTryo0(tabConstraints));
                                }
                                final List tabPlaceables = (List) destination$iv$iv;
                                List $this$fold$iv2 = tabPlaceables;
                                int initial$iv = padding * 2;
                                final int accumulator$iv2 = initial$iv;
                                for (Object element$iv2 : $this$fold$iv2) {
                                    Placeable measurable2 = (Placeable) element$iv2;
                                    int curr2 = accumulator$iv2;
                                    accumulator$iv2 = curr2 + measurable2.getWidth();
                                }
                                final Function2<Composer, Integer, Unit> function11 = function9;
                                final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                final int i11 = i9;
                                final Function3<List<TabPosition>, Composer, Integer, Unit> function12 = function10;
                                final int i12 = i10;
                                final int i13 = accumulator$iv;
                                return MeasureScope.CC.layout$default(SubcomposeLayout, accumulator$iv2, accumulator$iv, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TabRowKt.ScrollableTabRow.2.1.1
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
                                        final List tabPositions = new ArrayList();
                                        int left = padding;
                                        Iterable $this$forEach$iv = tabPlaceables;
                                        SubcomposeMeasureScope subcomposeMeasureScope = SubcomposeLayout;
                                        int left2 = left;
                                        for (Object element$iv3 : $this$forEach$iv) {
                                            Placeable it2 = (Placeable) element$iv3;
                                            Placeable.PlacementScope.placeRelative$default(layout, it2, left2, 0, 0.0f, 4, null);
                                            tabPositions.add(new TabPosition(subcomposeMeasureScope.mo324toDpu2uoSUM(left2), subcomposeMeasureScope.mo324toDpu2uoSUM(it2.getWidth()), null));
                                            left2 += it2.getWidth();
                                        }
                                        Iterable $this$forEach$iv2 = SubcomposeLayout.subcompose(TabSlots.Divider, function11);
                                        long j = constraints;
                                        int i14 = accumulator$iv2;
                                        int i15 = i13;
                                        for (Object element$iv4 : $this$forEach$iv2) {
                                            Measurable it3 = (Measurable) element$iv4;
                                            Placeable placeable = it3.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(j) : i14, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(j) : i14, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(j) : 0));
                                            Placeable.PlacementScope.placeRelative$default(layout, placeable, 0, i15 - placeable.getHeight(), 0.0f, 4, null);
                                            i15 = i15;
                                        }
                                        SubcomposeMeasureScope subcomposeMeasureScope2 = SubcomposeLayout;
                                        TabSlots tabSlots = TabSlots.Indicator;
                                        final Function3<List<TabPosition>, Composer, Integer, Unit> function13 = function12;
                                        final int i16 = i12;
                                        Iterable $this$forEach$iv3 = subcomposeMeasureScope2.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(963343607, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt.ScrollableTabRow.2.1.1.3
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

                                            public final void invoke(Composer $composer4, int $changed3) {
                                                ComposerKt.sourceInformation($composer4, "C312@14319L23:TabRow.kt#uh7d8r");
                                                if (($changed3 & 11) == 2 && $composer4.getSkipping()) {
                                                    $composer4.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(963343607, $changed3, -1, "androidx.compose.material3.ScrollableTabRow.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:311)");
                                                }
                                                function13.invoke(tabPositions, $composer4, Integer.valueOf(((i16 >> 12) & 112) | 8));
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }));
                                        int i17 = accumulator$iv2;
                                        int i18 = i13;
                                        for (Object element$iv5 : $this$forEach$iv3) {
                                            Measurable it4 = (Measurable) element$iv5;
                                            Placeable.PlacementScope.placeRelative$default(layout, it4.mo4225measureBRTryo0(Constraints.INSTANCE.m5226fixedJhjzzOo(i17, i18)), 0, 0, 0.0f, 4, null);
                                        }
                                        scrollableTabData2.onLaidOut(SubcomposeLayout, padding, tabPositions, i11);
                                    }
                                }, 4, null);
                            }
                        }, $composer3, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, (($dirty >> 3) & 14) | 12582912 | ($dirty & 896) | ($dirty & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            function4 = composableLambda;
            function5 = function2M1460getLambda2$material3_release;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            edgePadding3 = edgePadding2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier3;
        final long j = containerColor3;
        final long j2 = contentColor3;
        final float f2 = edgePadding3;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function8 = function4;
        final Function2<? super Composer, ? super Integer, Unit> function9 = function5;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRow$3
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
                TabRowKt.m1840ScrollableTabRowsKfQg0A(selectedTabIndex, modifier4, j, j2, f2, function8, function9, tabs, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }
}
