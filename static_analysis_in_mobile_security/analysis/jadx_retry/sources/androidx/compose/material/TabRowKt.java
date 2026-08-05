package androidx.compose.material;

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
import java.util.Iterator;
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
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a¬\u0001\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u000123\b\u0002\u0010\u0010\u001a-\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\u0018\b\u0002\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u001a¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\u0016\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u001a¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a¢\u0001\u0010\u001e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r23\b\u0002\u0010\u0010\u001a-\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\u0018\b\u0002\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u001a¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\u0016\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u001a¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"ScrollableTabRowMinimumTabWidth", "Landroidx/compose/ui/unit/Dp;", "F", "ScrollableTabRowScrollSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "ScrollableTabRow", "", "selectedTabIndex", "", "modifier", "Landroidx/compose/ui/Modifier;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "edgePadding", "indicator", "Lkotlin/Function1;", "", "Landroidx/compose/material/TabPosition;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "tabPositions", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/UiComposable;", "divider", "Lkotlin/Function0;", "tabs", "ScrollableTabRow-sKfQg0A", "(ILandroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "TabRow", "TabRow-pAZo6Ak", "(ILandroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TabRowKt {
    private static final float ScrollableTabRowMinimumTabWidth = Dp.m5274constructorimpl(90);
    private static final AnimationSpec<Float> ScrollableTabRowScrollSpec = AnimationSpecKt.tween$default(250, 0, EasingKt.getFastOutSlowInEasing(), 2, null);

    /* JADX INFO: renamed from: TabRow-pAZo6Ak, reason: not valid java name */
    public static final void m1240TabRowpAZo6Ak(final int selectedTabIndex, Modifier modifier, long backgroundColor, long contentColor, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> tabs, Composer $composer, final int $changed, final int i) {
        long backgroundColor2;
        long contentColor2;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3ComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Modifier.Companion modifier2;
        final Function2<? super Composer, ? super Integer, Unit> function2M1079getLambda1$material_release;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function5;
        long backgroundColor3;
        long contentColor3;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function6;
        Intrinsics.checkNotNullParameter(tabs, "tabs");
        Composer $composer2 = $composer.startRestartGroup(-249175289);
        ComposerKt.sourceInformation($composer2, "C(TabRow)P(5,4,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3)131@6500L6,132@6549L32,145@7006L1504:TabRow.kt#jmzs0o");
        final int $dirty = $changed;
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
                backgroundColor2 = backgroundColor;
                int i3 = $composer2.changed(backgroundColor2) ? 256 : 128;
                $dirty |= i3;
            } else {
                backgroundColor2 = backgroundColor;
            }
            $dirty |= i3;
        } else {
            backgroundColor2 = backgroundColor;
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
            function3ComposableLambda = function3;
        } else if ((57344 & $changed) == 0) {
            function3ComposableLambda = function3;
            $dirty |= $composer2.changedInstance(function3ComposableLambda) ? 16384 : 8192;
        } else {
            function3ComposableLambda = function3;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function4 = function2;
        } else if ((458752 & $changed) == 0) {
            function4 = function2;
            $dirty |= $composer2.changedInstance(function4) ? 131072 : 65536;
        } else {
            function4 = function2;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty |= $composer2.changedInstance(tabs) ? 1048576 : 524288;
        }
        if (($dirty & 2995931) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            backgroundColor3 = backgroundColor2;
            contentColor3 = contentColor2;
            function6 = function3ComposableLambda;
            function5 = function4;
            modifier3 = modifier;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                }
                if ((i & 8) != 0) {
                    long contentColor4 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                    contentColor2 = contentColor4;
                }
                if (i5 != 0) {
                    function3ComposableLambda = ComposableLambdaKt.composableLambda($composer2, -553782708, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$TabRow$1
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
                            ComposerKt.sourceInformation($composer3, "C135@6729L92:TabRow.kt#jmzs0o");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-553782708, $changed2, -1, "androidx.compose.material.TabRow.<anonymous> (TabRow.kt:134)");
                            }
                            TabRowDefaults.INSTANCE.m1235Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, tabPositions.get(selectedTabIndex)), 0.0f, 0L, $composer3, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                }
                function2M1079getLambda1$material_release = i6 != 0 ? ComposableSingletons$TabRowKt.INSTANCE.m1079getLambda1$material_release() : function4;
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    function2M1079getLambda1$material_release = function4;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                    function2M1079getLambda1$material_release = function4;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-249175289, $dirty, -1, "androidx.compose.material.TabRow (TabRow.kt:128)");
            }
            SurfaceKt.m1210SurfaceFjzlyU(SelectableGroupKt.selectableGroup(modifier2), null, backgroundColor2, contentColor2, null, 0.0f, ComposableLambdaKt.composableLambda($composer2, -1961746365, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$TabRow$2
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
                    ComposerKt.sourceInformation($composer3, "C150@7189L1315,150@7147L1357:TabRow.kt#jmzs0o");
                    if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1961746365, $changed2, -1, "androidx.compose.material.TabRow.<anonymous> (TabRow.kt:149)");
                    }
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Object key1$iv = tabs;
                    Object key2$iv = function2M1079getLambda1$material_release;
                    Object key3$iv = function3ComposableLambda;
                    final Function2<Composer, Integer, Unit> function7 = tabs;
                    final Function2<Composer, Integer, Unit> function8 = function2M1079getLambda1$material_release;
                    final Function3<List<TabPosition>, Composer, Integer, Unit> function9 = function3ComposableLambda;
                    final int i7 = $dirty;
                    int i8 = ((i7 >> 18) & 14) | ((i7 >> 12) & 112) | ((i7 >> 6) & 896);
                    $composer3.startReplaceableGroup(1618982084);
                    ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                    boolean invalid$iv$iv = $composer3.changed(key1$iv) | $composer3.changed(key2$iv) | $composer3.changed(key3$iv);
                    Object value$iv$iv = $composer3.rememberedValue();
                    if (invalid$iv$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.TabRowKt$TabRow$2$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                return m1242invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                            }

                            /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                            public final MeasureResult m1242invoke0kLqBqw(final SubcomposeMeasureScope SubcomposeLayout, final long constraints) {
                                Object maxElem$iv;
                                Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                                final int tabRowWidth = Constraints.m5218getMaxWidthimpl(constraints);
                                List<Measurable> listSubcompose = SubcomposeLayout.subcompose(TabSlots.Tabs, function7);
                                int tabCount = listSubcompose.size();
                                final int tabWidth = tabRowWidth / tabCount;
                                List<Measurable> $this$map$iv = listSubcompose;
                                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                for (Object item$iv$iv : $this$map$iv) {
                                    Measurable it = (Measurable) item$iv$iv;
                                    destination$iv$iv.add(it.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : tabWidth, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : tabWidth, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0)));
                                    $this$map$iv = $this$map$iv;
                                }
                                final List tabPlaceables = (List) destination$iv$iv;
                                List $this$maxByOrNull$iv = tabPlaceables;
                                Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
                                if (iterator$iv.hasNext()) {
                                    maxElem$iv = iterator$iv.next();
                                    if (iterator$iv.hasNext()) {
                                        Placeable it2 = (Placeable) maxElem$iv;
                                        int maxValue$iv = it2.getHeight();
                                        do {
                                            Object e$iv = iterator$iv.next();
                                            Placeable it3 = (Placeable) e$iv;
                                            int v$iv = it3.getHeight();
                                            if (maxValue$iv < v$iv) {
                                                maxElem$iv = e$iv;
                                                maxValue$iv = v$iv;
                                            }
                                        } while (iterator$iv.hasNext());
                                    }
                                } else {
                                    maxElem$iv = null;
                                }
                                Placeable placeable = (Placeable) maxElem$iv;
                                final int tabRowHeight = placeable != null ? placeable.getHeight() : 0;
                                ArrayList arrayList = new ArrayList(tabCount);
                                for (int i9 = 0; i9 < tabCount; i9++) {
                                    int index = i9;
                                    float arg0$iv = SubcomposeLayout.mo324toDpu2uoSUM(tabWidth);
                                    arrayList.add(new TabPosition(Dp.m5274constructorimpl(index * arg0$iv), SubcomposeLayout.mo324toDpu2uoSUM(tabWidth), null));
                                }
                                final ArrayList tabPositions = arrayList;
                                final Function2<Composer, Integer, Unit> function10 = function8;
                                final Function3<List<TabPosition>, Composer, Integer, Unit> function11 = function9;
                                final int i10 = i7;
                                return MeasureScope.CC.layout$default(SubcomposeLayout, tabRowWidth, tabRowHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.TabRowKt$TabRow$2$1$1.1
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
                                        int i11 = tabWidth;
                                        int index$iv = 0;
                                        for (Object item$iv : $this$forEachIndexed$iv) {
                                            int index$iv2 = index$iv + 1;
                                            if (index$iv < 0) {
                                                CollectionsKt.throwIndexOverflow();
                                            }
                                            int index2 = index$iv;
                                            Placeable.PlacementScope.placeRelative$default(layout, (Placeable) item$iv, index2 * i11, 0, 0.0f, 4, null);
                                            index$iv = index$iv2;
                                        }
                                        Iterable $this$forEach$iv = SubcomposeLayout.subcompose(TabSlots.Divider, function10);
                                        long j = constraints;
                                        int i12 = tabRowHeight;
                                        for (Object element$iv : $this$forEach$iv) {
                                            Measurable it4 = (Measurable) element$iv;
                                            long j2 = j;
                                            Placeable placeable2 = it4.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(j2, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(j2) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(j2) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(j2) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(j2) : 0));
                                            Placeable.PlacementScope.placeRelative$default(layout, placeable2, 0, i12 - placeable2.getHeight(), 0.0f, 4, null);
                                            i12 = i12;
                                            j = j;
                                        }
                                        SubcomposeMeasureScope subcomposeMeasureScope = SubcomposeLayout;
                                        TabSlots tabSlots = TabSlots.Indicator;
                                        final Function3<List<TabPosition>, Composer, Integer, Unit> function12 = function11;
                                        final List<TabPosition> list = tabPositions;
                                        final int i13 = i10;
                                        Iterable $this$forEach$iv2 = subcomposeMeasureScope.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(-1341594997, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt.TabRow.2.1.1.1.3
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
                                                ComposerKt.sourceInformation($composer4, "C176@8314L23:TabRow.kt#jmzs0o");
                                                if (($changed3 & 11) == 2 && $composer4.getSkipping()) {
                                                    $composer4.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1341594997, $changed3, -1, "androidx.compose.material.TabRow.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:175)");
                                                }
                                                function12.invoke(list, $composer4, Integer.valueOf(((i13 >> 9) & 112) | 8));
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }));
                                        int i14 = tabRowWidth;
                                        int i15 = tabRowHeight;
                                        for (Object element$iv2 : $this$forEach$iv2) {
                                            Measurable it5 = (Measurable) element$iv2;
                                            Placeable.PlacementScope.placeRelative$default(layout, it5.mo4225measureBRTryo0(Constraints.INSTANCE.m5226fixedJhjzzOo(i14, i15)), 0, 0, 0.0f, 4, null);
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
            }), $composer2, ($dirty & 896) | 1572864 | ($dirty & 7168), 50);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            function5 = function2M1079getLambda1$material_release;
            backgroundColor3 = backgroundColor2;
            contentColor3 = contentColor2;
            function6 = function3ComposableLambda;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier3;
        final long j = backgroundColor3;
        final long j2 = contentColor3;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function7 = function6;
        final Function2<? super Composer, ? super Integer, Unit> function8 = function5;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$TabRow$3
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
                TabRowKt.m1240TabRowpAZo6Ak(selectedTabIndex, modifier4, j, j2, function7, function8, tabs, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:106:0x0155 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:107:0x0157  */
    /* JADX WARN: Code duplicated, block: B:108:0x015c  */
    /* JADX WARN: Code duplicated, block: B:111:0x0162  */
    /* JADX WARN: Code duplicated, block: B:114:0x0174  */
    /* JADX WARN: Code duplicated, block: B:116:0x0181  */
    /* JADX WARN: Code duplicated, block: B:118:0x018a  */
    /* JADX WARN: Code duplicated, block: B:119:0x019a  */
    /* JADX WARN: Code duplicated, block: B:121:0x019e  */
    /* JADX WARN: Code duplicated, block: B:122:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:125:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:128:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:132:0x0216  */
    /* JADX WARN: Code duplicated, block: B:133:0x0219  */
    /* JADX WARN: Code duplicated, block: B:94:0x0129  */
    /* JADX WARN: Code duplicated, block: B:96:0x0130  */
    /* JADX INFO: renamed from: ScrollableTabRow-sKfQg0A, reason: not valid java name */
    public static final void m1239ScrollableTabRowsKfQg0A(final int selectedTabIndex, Modifier modifier, long backgroundColor, long contentColor, float edgePadding, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> tabs, Composer $composer, final int $changed, final int i) {
        long backgroundColor2;
        long contentColor2;
        float edgePadding2;
        int i2;
        Modifier.Companion modifier2;
        ComposableLambda composableLambda;
        Function2<? super Composer, ? super Integer, Unit> function2M1080getLambda2$material_release;
        Modifier modifier3;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function4;
        Function2<? super Composer, ? super Integer, Unit> function5;
        long backgroundColor3;
        long contentColor3;
        float edgePadding3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(tabs, "tabs");
        Composer $composer2 = $composer.startRestartGroup(-1473476840);
        ComposerKt.sourceInformation($composer2, "C(ScrollableTabRow)P(6,5,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3:c#ui.unit.Dp,4)225@11118L6,226@11167L32,240@11686L3006:TabRow.kt#jmzs0o");
        int $dirty = $changed;
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
                backgroundColor2 = backgroundColor;
                int i4 = $composer2.changed(backgroundColor2) ? 256 : 128;
                $dirty |= i4;
            } else {
                backgroundColor2 = backgroundColor;
            }
            $dirty |= i4;
        } else {
            backgroundColor2 = backgroundColor;
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
            edgePadding2 = edgePadding;
        } else if ((57344 & $changed) == 0) {
            edgePadding2 = edgePadding;
            $dirty |= $composer2.changed(edgePadding2) ? 16384 : 8192;
        } else {
            edgePadding2 = edgePadding;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 131072 : 65536;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 1048576 : 524288;
        }
        if ((i & 128) == 0) {
            if ((29360128 & $changed) == 0) {
                i2 = $composer2.changedInstance(tabs) ? 8388608 : 4194304;
            }
            if ((23967451 & $dirty) == 4793490 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        $dirty &= -897;
                        backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                    }
                    if ((i & 8) != 0) {
                        long contentColor4 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty >> 6) & 14);
                        $dirty &= -7169;
                        contentColor2 = contentColor4;
                    }
                    if (i6 != 0) {
                        edgePadding2 = TabRowDefaults.INSTANCE.m1238getScrollableTabRowPaddingD9Ej5fM();
                    }
                    if (i7 != 0) {
                        composableLambda = ComposableLambdaKt.composableLambda($composer2, -655609869, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$1
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
                                ComposerKt.sourceInformation($composer3, "C230@11409L92:TabRow.kt#jmzs0o");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-655609869, $changed2, -1, "androidx.compose.material.ScrollableTabRow.<anonymous> (TabRow.kt:229)");
                                }
                                TabRowDefaults.INSTANCE.m1235Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, tabPositions.get(selectedTabIndex)), 0.0f, 0L, $composer3, 3072, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        });
                    } else {
                        composableLambda = function3;
                    }
                    if (i8 != 0) {
                        function2M1080getLambda2$material_release = ComposableSingletons$TabRowKt.INSTANCE.m1080getLambda2$material_release();
                    } else {
                        function2M1080getLambda2$material_release = function2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty &= -897;
                    }
                    if ((i & 8) != 0) {
                        composableLambda = function3;
                        function2M1080getLambda2$material_release = function2;
                        $dirty &= -7169;
                        modifier2 = modifier;
                    } else {
                        modifier2 = modifier;
                        composableLambda = function3;
                        function2M1080getLambda2$material_release = function2;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1473476840, $dirty, -1, "androidx.compose.material.ScrollableTabRow (TabRow.kt:222)");
                }
                final float f = edgePadding2;
                final Function2<? super Composer, ? super Integer, Unit> function6 = function2M1080getLambda2$material_release;
                final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function7 = composableLambda;
                final int i9 = $dirty;
                SurfaceKt.m1210SurfaceFjzlyU(modifier2, null, backgroundColor2, contentColor2, null, 0.0f, ComposableLambdaKt.composableLambda($composer2, 1455860572, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$2
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
                        ComposerKt.sourceInformation($composer3, "C245@11827L21,246@11878L24,247@11935L185,253@12129L2557:TabRow.kt#jmzs0o");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1455860572, $changed2, -1, "androidx.compose.material.ScrollableTabRow.<anonymous> (TabRow.kt:244)");
                            }
                            ScrollState scrollState = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
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
                            final int i10 = selectedTabIndex;
                            final Function3<List<TabPosition>, Composer, Integer, Unit> function10 = function7;
                            final int i11 = i9;
                            SubcomposeLayoutKt.SubcomposeLayout(modifierClipToBounds, new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$2.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                    return m1241invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                                }

                                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                                public final MeasureResult m1241invoke0kLqBqw(final SubcomposeMeasureScope SubcomposeLayout, final long constraints) {
                                    Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                                    int minTabWidth = SubcomposeLayout.mo321roundToPx0680j_4(TabRowKt.ScrollableTabRowMinimumTabWidth);
                                    final int padding = SubcomposeLayout.mo321roundToPx0680j_4(f2);
                                    long tabConstraints = Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : minTabWidth, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0);
                                    Iterable $this$map$iv = SubcomposeLayout.subcompose(TabSlots.Tabs, function8);
                                    Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                    for (Object item$iv$iv : $this$map$iv) {
                                        destination$iv$iv.add(((Measurable) item$iv$iv).mo4225measureBRTryo0(tabConstraints));
                                    }
                                    final List tabPlaceables = (List) destination$iv$iv;
                                    final Ref.IntRef layoutWidth = new Ref.IntRef();
                                    layoutWidth.element = padding * 2;
                                    final Ref.IntRef layoutHeight = new Ref.IntRef();
                                    List $this$forEach$iv = tabPlaceables;
                                    for (Object element$iv : $this$forEach$iv) {
                                        Placeable it = (Placeable) element$iv;
                                        layoutWidth.element += it.getWidth();
                                        layoutHeight.element = Math.max(layoutHeight.element, it.getHeight());
                                    }
                                    int i12 = layoutWidth.element;
                                    int i13 = layoutHeight.element;
                                    final Function2<Composer, Integer, Unit> function11 = function9;
                                    final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                    final int i14 = i10;
                                    final Function3<List<TabPosition>, Composer, Integer, Unit> function12 = function10;
                                    final int i15 = i11;
                                    return MeasureScope.CC.layout$default(SubcomposeLayout, i12, i13, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.TabRowKt.ScrollableTabRow.2.1.2
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
                                            Iterable $this$forEach$iv2 = tabPlaceables;
                                            SubcomposeMeasureScope subcomposeMeasureScope = SubcomposeLayout;
                                            int left2 = left;
                                            for (Object element$iv2 : $this$forEach$iv2) {
                                                Placeable it2 = (Placeable) element$iv2;
                                                Placeable.PlacementScope.placeRelative$default(layout, it2, left2, 0, 0.0f, 4, null);
                                                tabPositions.add(new TabPosition(subcomposeMeasureScope.mo324toDpu2uoSUM(left2), subcomposeMeasureScope.mo324toDpu2uoSUM(it2.getWidth()), null));
                                                left2 += it2.getWidth();
                                            }
                                            Iterable $this$forEach$iv3 = SubcomposeLayout.subcompose(TabSlots.Divider, function11);
                                            long j = constraints;
                                            Ref.IntRef intRef = layoutWidth;
                                            Ref.IntRef intRef2 = layoutHeight;
                                            for (Object element$iv3 : $this$forEach$iv3) {
                                                Measurable it3 = (Measurable) element$iv3;
                                                Placeable placeable = it3.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(j) : intRef.element, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(j) : intRef.element, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(j) : 0));
                                                Placeable.PlacementScope.placeRelative$default(layout, placeable, 0, intRef2.element - placeable.getHeight(), 0.0f, 4, null);
                                                intRef2 = intRef2;
                                            }
                                            SubcomposeMeasureScope subcomposeMeasureScope2 = SubcomposeLayout;
                                            TabSlots tabSlots = TabSlots.Indicator;
                                            final Function3<List<TabPosition>, Composer, Integer, Unit> function13 = function12;
                                            final int i16 = i15;
                                            Iterable $this$forEach$iv4 = subcomposeMeasureScope2.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(230769237, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt.ScrollableTabRow.2.1.2.3
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
                                                    ComposerKt.sourceInformation($composer4, "C301@14237L23:TabRow.kt#jmzs0o");
                                                    if (($changed3 & 11) == 2 && $composer4.getSkipping()) {
                                                        $composer4.skipToGroupEnd();
                                                        return;
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(230769237, $changed3, -1, "androidx.compose.material.ScrollableTabRow.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:300)");
                                                    }
                                                    function13.invoke(tabPositions, $composer4, Integer.valueOf(((i16 >> 12) & 112) | 8));
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                    }
                                                }
                                            }));
                                            Ref.IntRef intRef3 = layoutWidth;
                                            Ref.IntRef intRef4 = layoutHeight;
                                            for (Object element$iv4 : $this$forEach$iv4) {
                                                Measurable it4 = (Measurable) element$iv4;
                                                Placeable.PlacementScope.placeRelative$default(layout, it4.mo4225measureBRTryo0(Constraints.INSTANCE.m5226fixedJhjzzOo(intRef3.element, intRef4.element)), 0, 0, 0.0f, 4, null);
                                            }
                                            scrollableTabData2.onLaidOut(SubcomposeLayout, padding, tabPositions, i14);
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
                }), $composer2, (($dirty >> 3) & 14) | 1572864 | ($dirty & 896) | ($dirty & 7168), 50);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                function4 = composableLambda;
                function5 = function2M1080getLambda2$material_release;
                backgroundColor3 = backgroundColor2;
                contentColor3 = contentColor2;
                edgePadding3 = edgePadding2;
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier;
                function4 = function3;
                function5 = function2;
                backgroundColor3 = backgroundColor2;
                contentColor3 = contentColor2;
                edgePadding3 = edgePadding2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final long j = backgroundColor3;
            final long j2 = contentColor3;
            final float f2 = edgePadding3;
            final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function8 = function4;
            final Function2<? super Composer, ? super Integer, Unit> function9 = function5;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$3
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
                    TabRowKt.m1239ScrollableTabRowsKfQg0A(selectedTabIndex, modifier4, j, j2, f2, function8, function9, tabs, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 12582912;
        $dirty |= i2;
        if ((23967451 & $dirty) == 4793490) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                }
                if ((i & 8) != 0) {
                    long contentColor5 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                    contentColor2 = contentColor5;
                }
                if (i6 != 0) {
                    edgePadding2 = TabRowDefaults.INSTANCE.m1238getScrollableTabRowPaddingD9Ej5fM();
                }
                if (i7 != 0) {
                    composableLambda = ComposableLambdaKt.composableLambda($composer2, -655609869, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$1
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
                            ComposerKt.sourceInformation($composer3, "C230@11409L92:TabRow.kt#jmzs0o");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-655609869, $changed2, -1, "androidx.compose.material.ScrollableTabRow.<anonymous> (TabRow.kt:229)");
                            }
                            TabRowDefaults.INSTANCE.m1235Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, tabPositions.get(selectedTabIndex)), 0.0f, 0L, $composer3, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                } else {
                    composableLambda = function3;
                }
                if (i8 != 0) {
                    function2M1080getLambda2$material_release = ComposableSingletons$TabRowKt.INSTANCE.m1080getLambda2$material_release();
                } else {
                    function2M1080getLambda2$material_release = function2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                }
                if ((i & 8) != 0) {
                    long contentColor6 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                    contentColor2 = contentColor6;
                }
                if (i6 != 0) {
                    edgePadding2 = TabRowDefaults.INSTANCE.m1238getScrollableTabRowPaddingD9Ej5fM();
                }
                if (i7 != 0) {
                    composableLambda = ComposableLambdaKt.composableLambda($composer2, -655609869, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$1
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
                            ComposerKt.sourceInformation($composer3, "C230@11409L92:TabRow.kt#jmzs0o");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-655609869, $changed2, -1, "androidx.compose.material.ScrollableTabRow.<anonymous> (TabRow.kt:229)");
                            }
                            TabRowDefaults.INSTANCE.m1235Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, tabPositions.get(selectedTabIndex)), 0.0f, 0L, $composer3, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                } else {
                    composableLambda = function3;
                }
                if (i8 != 0) {
                    function2M1080getLambda2$material_release = ComposableSingletons$TabRowKt.INSTANCE.m1080getLambda2$material_release();
                } else {
                    function2M1080getLambda2$material_release = function2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1473476840, $dirty, -1, "androidx.compose.material.ScrollableTabRow (TabRow.kt:222)");
            }
            final float f3 = edgePadding2;
            final Function2<? super Composer, ? super Integer, Unit> function10 = function2M1080getLambda2$material_release;
            final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function11 = composableLambda;
            final int i10 = $dirty;
            SurfaceKt.m1210SurfaceFjzlyU(modifier2, null, backgroundColor2, contentColor2, null, 0.0f, ComposableLambdaKt.composableLambda($composer2, 1455860572, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$2
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
                    ComposerKt.sourceInformation($composer3, "C245@11827L21,246@11878L24,247@11935L185,253@12129L2557:TabRow.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1455860572, $changed2, -1, "androidx.compose.material.ScrollableTabRow.<anonymous> (TabRow.kt:244)");
                        }
                        ScrollState scrollState = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
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
                        final float f4 = f3;
                        final Function2<? super Composer, ? super Integer, Unit> function12 = tabs;
                        final Function2<? super Composer, ? super Integer, Unit> function13 = function10;
                        final int i11 = selectedTabIndex;
                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function14 = function11;
                        final int i12 = i10;
                        SubcomposeLayoutKt.SubcomposeLayout(modifierClipToBounds, new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                return m1241invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                            }

                            /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                            public final MeasureResult m1241invoke0kLqBqw(final SubcomposeMeasureScope SubcomposeLayout, final long constraints) {
                                Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                                int minTabWidth = SubcomposeLayout.mo321roundToPx0680j_4(TabRowKt.ScrollableTabRowMinimumTabWidth);
                                final int padding = SubcomposeLayout.mo321roundToPx0680j_4(f4);
                                long tabConstraints = Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : minTabWidth, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0);
                                Iterable $this$map$iv = SubcomposeLayout.subcompose(TabSlots.Tabs, function12);
                                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                for (Object item$iv$iv : $this$map$iv) {
                                    destination$iv$iv.add(((Measurable) item$iv$iv).mo4225measureBRTryo0(tabConstraints));
                                }
                                final List<? extends Placeable> tabPlaceables = (List) destination$iv$iv;
                                final Ref.IntRef layoutWidth = new Ref.IntRef();
                                layoutWidth.element = padding * 2;
                                final Ref.IntRef layoutHeight = new Ref.IntRef();
                                List $this$forEach$iv = tabPlaceables;
                                for (Object element$iv : $this$forEach$iv) {
                                    Placeable it = (Placeable) element$iv;
                                    layoutWidth.element += it.getWidth();
                                    layoutHeight.element = Math.max(layoutHeight.element, it.getHeight());
                                }
                                int i13 = layoutWidth.element;
                                int i14 = layoutHeight.element;
                                final Function2<? super Composer, ? super Integer, Unit> function15 = function13;
                                final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                final int i15 = i11;
                                final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function16 = function14;
                                final int i16 = i12;
                                return MeasureScope.CC.layout$default(SubcomposeLayout, i13, i14, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.TabRowKt.ScrollableTabRow.2.1.2
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
                                        final List<TabPosition> tabPositions = new ArrayList();
                                        int left = padding;
                                        Iterable $this$forEach$iv2 = tabPlaceables;
                                        SubcomposeMeasureScope subcomposeMeasureScope = SubcomposeLayout;
                                        int left2 = left;
                                        for (Object element$iv2 : $this$forEach$iv2) {
                                            Placeable it2 = (Placeable) element$iv2;
                                            Placeable.PlacementScope.placeRelative$default(layout, it2, left2, 0, 0.0f, 4, null);
                                            tabPositions.add(new TabPosition(subcomposeMeasureScope.mo324toDpu2uoSUM(left2), subcomposeMeasureScope.mo324toDpu2uoSUM(it2.getWidth()), null));
                                            left2 += it2.getWidth();
                                        }
                                        Iterable $this$forEach$iv3 = SubcomposeLayout.subcompose(TabSlots.Divider, function15);
                                        long j3 = constraints;
                                        Ref.IntRef intRef = layoutWidth;
                                        Ref.IntRef intRef2 = layoutHeight;
                                        for (Object element$iv3 : $this$forEach$iv3) {
                                            Measurable it3 = (Measurable) element$iv3;
                                            Placeable placeable = it3.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(j3, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(j3) : intRef.element, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(j3) : intRef.element, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(j3) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(j3) : 0));
                                            Placeable.PlacementScope.placeRelative$default(layout, placeable, 0, intRef2.element - placeable.getHeight(), 0.0f, 4, null);
                                            intRef2 = intRef2;
                                        }
                                        SubcomposeMeasureScope subcomposeMeasureScope2 = SubcomposeLayout;
                                        TabSlots tabSlots = TabSlots.Indicator;
                                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function17 = function16;
                                        final int i17 = i16;
                                        Iterable $this$forEach$iv4 = subcomposeMeasureScope2.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(230769237, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt.ScrollableTabRow.2.1.2.3
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
                                                ComposerKt.sourceInformation($composer4, "C301@14237L23:TabRow.kt#jmzs0o");
                                                if (($changed3 & 11) == 2 && $composer4.getSkipping()) {
                                                    $composer4.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(230769237, $changed3, -1, "androidx.compose.material.ScrollableTabRow.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:300)");
                                                }
                                                function17.invoke(tabPositions, $composer4, Integer.valueOf(((i17 >> 12) & 112) | 8));
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }));
                                        Ref.IntRef intRef3 = layoutWidth;
                                        Ref.IntRef intRef4 = layoutHeight;
                                        for (Object element$iv4 : $this$forEach$iv4) {
                                            Measurable it4 = (Measurable) element$iv4;
                                            Placeable.PlacementScope.placeRelative$default(layout, it4.mo4225measureBRTryo0(Constraints.INSTANCE.m5226fixedJhjzzOo(intRef3.element, intRef4.element)), 0, 0, 0.0f, 4, null);
                                        }
                                        scrollableTabData2.onLaidOut(SubcomposeLayout, padding, tabPositions, i15);
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
            }), $composer2, (($dirty >> 3) & 14) | 1572864 | ($dirty & 896) | ($dirty & 7168), 50);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            function4 = composableLambda;
            function5 = function2M1080getLambda2$material_release;
            backgroundColor3 = backgroundColor2;
            contentColor3 = contentColor2;
            edgePadding3 = edgePadding2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                }
                if ((i & 8) != 0) {
                    long contentColor7 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                    contentColor2 = contentColor7;
                }
                if (i6 != 0) {
                    edgePadding2 = TabRowDefaults.INSTANCE.m1238getScrollableTabRowPaddingD9Ej5fM();
                }
                if (i7 != 0) {
                    composableLambda = ComposableLambdaKt.composableLambda($composer2, -655609869, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$1
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
                            ComposerKt.sourceInformation($composer3, "C230@11409L92:TabRow.kt#jmzs0o");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-655609869, $changed2, -1, "androidx.compose.material.ScrollableTabRow.<anonymous> (TabRow.kt:229)");
                            }
                            TabRowDefaults.INSTANCE.m1235Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, tabPositions.get(selectedTabIndex)), 0.0f, 0L, $composer3, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                } else {
                    composableLambda = function3;
                }
                if (i8 != 0) {
                    function2M1080getLambda2$material_release = ComposableSingletons$TabRowKt.INSTANCE.m1080getLambda2$material_release();
                } else {
                    function2M1080getLambda2$material_release = function2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                }
                if ((i & 8) != 0) {
                    long contentColor8 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                    contentColor2 = contentColor8;
                }
                if (i6 != 0) {
                    edgePadding2 = TabRowDefaults.INSTANCE.m1238getScrollableTabRowPaddingD9Ej5fM();
                }
                if (i7 != 0) {
                    composableLambda = ComposableLambdaKt.composableLambda($composer2, -655609869, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$1
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
                            ComposerKt.sourceInformation($composer3, "C230@11409L92:TabRow.kt#jmzs0o");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-655609869, $changed2, -1, "androidx.compose.material.ScrollableTabRow.<anonymous> (TabRow.kt:229)");
                            }
                            TabRowDefaults.INSTANCE.m1235Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, tabPositions.get(selectedTabIndex)), 0.0f, 0L, $composer3, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                } else {
                    composableLambda = function3;
                }
                if (i8 != 0) {
                    function2M1080getLambda2$material_release = ComposableSingletons$TabRowKt.INSTANCE.m1080getLambda2$material_release();
                } else {
                    function2M1080getLambda2$material_release = function2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1473476840, $dirty, -1, "androidx.compose.material.ScrollableTabRow (TabRow.kt:222)");
            }
            final float f4 = edgePadding2;
            final Function2<? super Composer, ? super Integer, Unit> function12 = function2M1080getLambda2$material_release;
            final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function13 = composableLambda;
            final int i11 = $dirty;
            SurfaceKt.m1210SurfaceFjzlyU(modifier2, null, backgroundColor2, contentColor2, null, 0.0f, ComposableLambdaKt.composableLambda($composer2, 1455860572, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$2
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
                    ComposerKt.sourceInformation($composer3, "C245@11827L21,246@11878L24,247@11935L185,253@12129L2557:TabRow.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1455860572, $changed2, -1, "androidx.compose.material.ScrollableTabRow.<anonymous> (TabRow.kt:244)");
                        }
                        ScrollState scrollState = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
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
                        final float f5 = f4;
                        final Function2<? super Composer, ? super Integer, Unit> function14 = tabs;
                        final Function2<? super Composer, ? super Integer, Unit> function15 = function12;
                        final int i12 = selectedTabIndex;
                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function16 = function13;
                        final int i13 = i11;
                        SubcomposeLayoutKt.SubcomposeLayout(modifierClipToBounds, new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                return m1241invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                            }

                            /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                            public final MeasureResult m1241invoke0kLqBqw(final SubcomposeMeasureScope SubcomposeLayout, final long constraints) {
                                Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                                int minTabWidth = SubcomposeLayout.mo321roundToPx0680j_4(TabRowKt.ScrollableTabRowMinimumTabWidth);
                                final int padding = SubcomposeLayout.mo321roundToPx0680j_4(f5);
                                long tabConstraints = Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : minTabWidth, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0);
                                Iterable $this$map$iv = SubcomposeLayout.subcompose(TabSlots.Tabs, function14);
                                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                for (Object item$iv$iv : $this$map$iv) {
                                    destination$iv$iv.add(((Measurable) item$iv$iv).mo4225measureBRTryo0(tabConstraints));
                                }
                                final List<? extends Placeable> tabPlaceables = (List) destination$iv$iv;
                                final Ref.IntRef layoutWidth = new Ref.IntRef();
                                layoutWidth.element = padding * 2;
                                final Ref.IntRef layoutHeight = new Ref.IntRef();
                                List $this$forEach$iv = tabPlaceables;
                                for (Object element$iv : $this$forEach$iv) {
                                    Placeable it = (Placeable) element$iv;
                                    layoutWidth.element += it.getWidth();
                                    layoutHeight.element = Math.max(layoutHeight.element, it.getHeight());
                                }
                                int i14 = layoutWidth.element;
                                int i15 = layoutHeight.element;
                                final Function2<? super Composer, ? super Integer, Unit> function17 = function15;
                                final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                final int i16 = i12;
                                final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function18 = function16;
                                final int i17 = i13;
                                return MeasureScope.CC.layout$default(SubcomposeLayout, i14, i15, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.TabRowKt.ScrollableTabRow.2.1.2
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
                                        final List<TabPosition> tabPositions = new ArrayList();
                                        int left = padding;
                                        Iterable $this$forEach$iv2 = tabPlaceables;
                                        SubcomposeMeasureScope subcomposeMeasureScope = SubcomposeLayout;
                                        int left2 = left;
                                        for (Object element$iv2 : $this$forEach$iv2) {
                                            Placeable it2 = (Placeable) element$iv2;
                                            Placeable.PlacementScope.placeRelative$default(layout, it2, left2, 0, 0.0f, 4, null);
                                            tabPositions.add(new TabPosition(subcomposeMeasureScope.mo324toDpu2uoSUM(left2), subcomposeMeasureScope.mo324toDpu2uoSUM(it2.getWidth()), null));
                                            left2 += it2.getWidth();
                                        }
                                        Iterable $this$forEach$iv3 = SubcomposeLayout.subcompose(TabSlots.Divider, function17);
                                        long j3 = constraints;
                                        Ref.IntRef intRef = layoutWidth;
                                        Ref.IntRef intRef2 = layoutHeight;
                                        for (Object element$iv3 : $this$forEach$iv3) {
                                            Measurable it3 = (Measurable) element$iv3;
                                            Placeable placeable = it3.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(j3, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(j3) : intRef.element, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(j3) : intRef.element, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(j3) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(j3) : 0));
                                            Placeable.PlacementScope.placeRelative$default(layout, placeable, 0, intRef2.element - placeable.getHeight(), 0.0f, 4, null);
                                            intRef2 = intRef2;
                                        }
                                        SubcomposeMeasureScope subcomposeMeasureScope2 = SubcomposeLayout;
                                        TabSlots tabSlots = TabSlots.Indicator;
                                        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function19 = function18;
                                        final int i18 = i17;
                                        Iterable $this$forEach$iv4 = subcomposeMeasureScope2.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(230769237, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt.ScrollableTabRow.2.1.2.3
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
                                                ComposerKt.sourceInformation($composer4, "C301@14237L23:TabRow.kt#jmzs0o");
                                                if (($changed3 & 11) == 2 && $composer4.getSkipping()) {
                                                    $composer4.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(230769237, $changed3, -1, "androidx.compose.material.ScrollableTabRow.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:300)");
                                                }
                                                function19.invoke(tabPositions, $composer4, Integer.valueOf(((i18 >> 12) & 112) | 8));
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }));
                                        Ref.IntRef intRef3 = layoutWidth;
                                        Ref.IntRef intRef4 = layoutHeight;
                                        for (Object element$iv4 : $this$forEach$iv4) {
                                            Measurable it4 = (Measurable) element$iv4;
                                            Placeable.PlacementScope.placeRelative$default(layout, it4.mo4225measureBRTryo0(Constraints.INSTANCE.m5226fixedJhjzzOo(intRef3.element, intRef4.element)), 0, 0, 0.0f, 4, null);
                                        }
                                        scrollableTabData2.onLaidOut(SubcomposeLayout, padding, tabPositions, i16);
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
            }), $composer2, (($dirty >> 3) & 14) | 1572864 | ($dirty & 896) | ($dirty & 7168), 50);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            function4 = composableLambda;
            function5 = function2M1080getLambda2$material_release;
            backgroundColor3 = backgroundColor2;
            contentColor3 = contentColor2;
            edgePadding3 = edgePadding2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final long j3 = backgroundColor3;
        final long j4 = contentColor3;
        final float f5 = edgePadding3;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function14 = function4;
        final Function2<? super Composer, ? super Integer, Unit> function15 = function5;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$3
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
                TabRowKt.m1239ScrollableTabRowsKfQg0A(selectedTabIndex, modifier5, j3, j4, f5, function14, function15, tabs, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }
}
