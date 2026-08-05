package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayoutKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PagerMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aH\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u000bH\u0002\u001a@\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u000bH\u0002\u001a\u0017\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0082\b\u001a\u008c\u0001\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0014*\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00012\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u0006H\u0002\u001am\u0010\n\u001a\u00020\u0004*\u00020\u00152\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u0002012\u0006\u0010 \u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b2\u00103\u001aä\u0001\u00104\u001a\u000205*\u00020\u00152\u0006\u00106\u001a\u00020\u00062\u0006\u0010(\u001a\u00020)2\u0006\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u00062\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020'2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010 \u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\u0006\u0010$\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032/\u0010?\u001a+\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020\u000f0\u000b¢\u0006\u0002\bB\u0012\u0004\u0012\u00020C0@H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bD\u0010E\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006F"}, d2 = {"DEBUG", "", "createPagesAfterList", "", "Landroidx/compose/foundation/pager/MeasuredPage;", "currentLastPage", "", "pagesCount", "beyondBoundsPageCount", "pinnedPages", "getAndMeasure", "Lkotlin/Function1;", "createPagesBeforeList", "currentFirstPage", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "calculatePagesOffsets", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "pages", "extraPagesBefore", "extraPagesAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "pagesScrollOffset", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "spaceBetweenPages", "pageAvailableSize", "index", "childConstraints", "Landroidx/compose/ui/unit/Constraints;", "pagerItemProvider", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "visualPageOffset", "Landroidx/compose/ui/unit/IntOffset;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getAndMeasure-SGf7dI0", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;IJLandroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;JLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/unit/LayoutDirection;ZI)Landroidx/compose/foundation/pager/MeasuredPage;", "measurePager", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "pageCount", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "firstVisiblePage", "firstVisiblePageOffset", "scrollToBeConsumed", "", "constraints", "layout", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measurePager-ntgEbfI", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;ILandroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;IIIIIIFJLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/Alignment$Horizontal;ZJIILjava/util/List;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/pager/PagerMeasureResult;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PagerMeasureKt {
    private static final boolean DEBUG = false;

    /* JADX INFO: renamed from: measurePager-ntgEbfI, reason: not valid java name */
    public static final PagerMeasureResult m712measurePagerntgEbfI(final LazyLayoutMeasureScope measurePager, int pageCount, final PagerLazyLayoutItemProvider pagerItemProvider, int mainAxisAvailableSize, int beforeContentPadding, int afterContentPadding, int spaceBetweenPages, int firstVisiblePage, int firstVisiblePageOffset, float scrollToBeConsumed, long constraints, final Orientation orientation, final Alignment.Vertical verticalAlignment, final Alignment.Horizontal horizontalAlignment, final boolean reverseLayout, final long visualPageOffset, final int pageAvailableSize, int beyondBoundsPageCount, List<Integer> pinnedPages, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> layout) {
        int scrollDelta;
        int minOffset;
        int maxCrossAxis;
        int scrollDelta2;
        int currentFirstPageScrollOffset;
        int currentFirstPage;
        int currentFirstPageScrollOffset2;
        MeasuredPage firstPage;
        List<MeasuredPage> list;
        Object maxElem$iv;
        int index;
        ArrayDeque visiblePages;
        Intrinsics.checkNotNullParameter(measurePager, "$this$measurePager");
        Intrinsics.checkNotNullParameter(pagerItemProvider, "pagerItemProvider");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        Intrinsics.checkNotNullParameter(pinnedPages, "pinnedPages");
        Intrinsics.checkNotNullParameter(layout, "layout");
        if (!(beforeContentPadding >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(afterContentPadding >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int pageSizeWithSpacing = RangesKt.coerceAtLeast(pageAvailableSize + spaceBetweenPages, 0);
        if (pageCount <= 0) {
            return new PagerMeasureResult(CollectionsKt.emptyList(), 0, pageAvailableSize, spaceBetweenPages, afterContentPadding, orientation, -beforeContentPadding, mainAxisAvailableSize + afterContentPadding, false, 0.0f, null, null, 0, false, layout.invoke(Integer.valueOf(Constraints.m5220getMinWidthimpl(constraints)), Integer.valueOf(Constraints.m5219getMinHeightimpl(constraints)), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$measurePager$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope invoke) {
                    Intrinsics.checkNotNullParameter(invoke, "$this$invoke");
                }
            }));
        }
        final long childConstraints = ConstraintsKt.Constraints$default(0, orientation == Orientation.Vertical ? Constraints.m5218getMaxWidthimpl(constraints) : pageAvailableSize, 0, orientation != Orientation.Vertical ? Constraints.m5217getMaxHeightimpl(constraints) : pageAvailableSize, 5, null);
        int currentFirstPage2 = firstVisiblePage;
        int currentFirstPageScrollOffset3 = firstVisiblePageOffset;
        if (currentFirstPage2 >= pageCount) {
            currentFirstPage2 = pageCount - 1;
            currentFirstPageScrollOffset3 = 0;
        }
        int scrollDelta3 = MathKt.roundToInt(scrollToBeConsumed);
        int currentFirstPageScrollOffset4 = currentFirstPageScrollOffset3 - scrollDelta3;
        if (currentFirstPage2 != 0 || currentFirstPageScrollOffset4 >= 0) {
            scrollDelta = scrollDelta3;
        } else {
            int scrollDelta4 = scrollDelta3 + currentFirstPageScrollOffset4;
            currentFirstPageScrollOffset4 = 0;
            scrollDelta = scrollDelta4;
        }
        ArrayDeque visiblePages2 = new ArrayDeque();
        int currentFirstPageScrollOffset5 = (-beforeContentPadding) + (spaceBetweenPages < 0 ? spaceBetweenPages : 0);
        int maxOffset = mainAxisAvailableSize;
        int maxCrossAxis2 = currentFirstPageScrollOffset4 + currentFirstPageScrollOffset5;
        int currentFirstPage3 = currentFirstPage2;
        int currentFirstPage4 = 0;
        while (maxCrossAxis2 < 0 && currentFirstPage3 > 0) {
            int previous = currentFirstPage3 - 1;
            int maxCrossAxis3 = currentFirstPage4;
            int currentFirstPageScrollOffset6 = maxCrossAxis2;
            ArrayDeque visiblePages3 = visiblePages2;
            MeasuredPage measuredPage = m711getAndMeasureSGf7dI0(measurePager, previous, childConstraints, pagerItemProvider, visualPageOffset, orientation, horizontalAlignment, verticalAlignment, measurePager.getLayoutDirection(), reverseLayout, pageAvailableSize);
            visiblePages3.add(0, measuredPage);
            currentFirstPage3 = previous;
            currentFirstPage4 = Math.max(maxCrossAxis3, measuredPage.getCrossAxisSize());
            maxCrossAxis2 = currentFirstPageScrollOffset6 + pageSizeWithSpacing;
            visiblePages2 = visiblePages3;
            currentFirstPageScrollOffset5 = currentFirstPageScrollOffset5;
            maxOffset = maxOffset;
        }
        int maxCrossAxis4 = currentFirstPage4;
        int currentFirstPageScrollOffset7 = maxCrossAxis2;
        int maxOffset2 = maxOffset;
        ArrayDeque visiblePages4 = visiblePages2;
        int minOffset2 = currentFirstPageScrollOffset5;
        int currentFirstPageScrollOffset8 = currentFirstPageScrollOffset7;
        if (currentFirstPageScrollOffset8 < minOffset2) {
            scrollDelta += currentFirstPageScrollOffset8;
            currentFirstPageScrollOffset8 = minOffset2;
        }
        int currentFirstPageScrollOffset9 = currentFirstPageScrollOffset8 - minOffset2;
        int index2 = currentFirstPage3;
        int index3 = maxOffset2;
        int maxMainAxis = RangesKt.coerceAtLeast(index3 + afterContentPadding, 0);
        int currentMainAxisOffset = -currentFirstPageScrollOffset9;
        ArrayDeque $this$fastForEach$iv = visiblePages4;
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            index2++;
            currentMainAxisOffset += pageSizeWithSpacing;
        }
        int currentMainAxisOffset2 = currentMainAxisOffset;
        int maxCrossAxis5 = maxCrossAxis4;
        int currentFirstPage5 = currentFirstPage3;
        int maxCrossAxis6 = index2;
        int currentFirstPageScrollOffset10 = currentFirstPageScrollOffset9;
        while (true) {
            minOffset = minOffset2;
            if (maxCrossAxis6 >= pageCount || (currentMainAxisOffset2 >= maxMainAxis && currentMainAxisOffset2 > 0 && !visiblePages4.isEmpty())) {
                break;
                break;
            }
            int i = maxCrossAxis6;
            ArrayDeque visiblePages5 = visiblePages4;
            int index4 = maxCrossAxis6;
            int index5 = currentMainAxisOffset2;
            int maxCrossAxis7 = maxCrossAxis5;
            int maxMainAxis2 = maxMainAxis;
            int maxOffset3 = index3;
            MeasuredPage measuredPage2 = m711getAndMeasureSGf7dI0(measurePager, i, childConstraints, pagerItemProvider, visualPageOffset, orientation, horizontalAlignment, verticalAlignment, measurePager.getLayoutDirection(), reverseLayout, pageAvailableSize);
            currentMainAxisOffset2 = index5 + pageSizeWithSpacing;
            if (currentMainAxisOffset2 <= minOffset) {
                index = index4;
                if (index != pageCount - 1) {
                    currentFirstPageScrollOffset10 -= pageSizeWithSpacing;
                    currentFirstPage5 = index + 1;
                    visiblePages = visiblePages5;
                    maxCrossAxis5 = maxCrossAxis7;
                }
                maxCrossAxis6 = index + 1;
                visiblePages4 = visiblePages;
                minOffset2 = minOffset;
                maxMainAxis = maxMainAxis2;
                index3 = maxOffset3;
            } else {
                index = index4;
            }
            int maxCrossAxis8 = Math.max(maxCrossAxis7, measuredPage2.getCrossAxisSize());
            visiblePages = visiblePages5;
            visiblePages.add(measuredPage2);
            maxCrossAxis5 = maxCrossAxis8;
            maxCrossAxis6 = index + 1;
            visiblePages4 = visiblePages;
            minOffset2 = minOffset;
            maxMainAxis = maxMainAxis2;
            index3 = maxOffset3;
        }
        int maxOffset4 = index3;
        if (currentMainAxisOffset2 < maxOffset4) {
            int toScrollBack = maxOffset4 - currentMainAxisOffset2;
            currentMainAxisOffset2 += toScrollBack;
            maxCrossAxis = maxCrossAxis5;
            int currentFirstPageScrollOffset11 = currentFirstPageScrollOffset10 - toScrollBack;
            while (currentFirstPageScrollOffset11 < beforeContentPadding && currentFirstPage5 > 0) {
                int previousIndex = currentFirstPage5 - 1;
                ArrayDeque visiblePages6 = visiblePages4;
                int index6 = maxCrossAxis6;
                MeasuredPage measuredPage3 = m711getAndMeasureSGf7dI0(measurePager, previousIndex, childConstraints, pagerItemProvider, visualPageOffset, orientation, horizontalAlignment, verticalAlignment, measurePager.getLayoutDirection(), reverseLayout, pageAvailableSize);
                visiblePages6.add(0, measuredPage3);
                maxCrossAxis = Math.max(maxCrossAxis, measuredPage3.getCrossAxisSize());
                currentFirstPageScrollOffset11 += pageSizeWithSpacing;
                currentFirstPage5 = previousIndex;
                visiblePages4 = visiblePages6;
                minOffset = minOffset;
                maxOffset4 = maxOffset4;
                maxCrossAxis6 = index6;
            }
            int scrollDelta5 = scrollDelta + toScrollBack;
            if (currentFirstPageScrollOffset11 < 0) {
                currentMainAxisOffset2 += currentFirstPageScrollOffset11;
                scrollDelta2 = scrollDelta5 + currentFirstPageScrollOffset11;
                currentFirstPageScrollOffset = 0;
                currentFirstPage = currentFirstPage5;
            } else {
                scrollDelta2 = scrollDelta5;
                currentFirstPage = currentFirstPage5;
                currentFirstPageScrollOffset = currentFirstPageScrollOffset11;
            }
        } else {
            maxOffset4 = maxOffset4;
            visiblePages4 = visiblePages4;
            maxCrossAxis6 = maxCrossAxis6;
            minOffset = minOffset;
            maxCrossAxis = maxCrossAxis5;
            scrollDelta2 = scrollDelta;
            currentFirstPageScrollOffset = currentFirstPageScrollOffset10;
            currentFirstPage = currentFirstPage5;
        }
        float consumedScroll = (MathKt.getSign(MathKt.roundToInt(scrollToBeConsumed)) != MathKt.getSign(scrollDelta2) || Math.abs(MathKt.roundToInt(scrollToBeConsumed)) < Math.abs(scrollDelta2)) ? scrollToBeConsumed : scrollDelta2;
        if (!(currentFirstPageScrollOffset >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int visiblePagesScrollOffset = -currentFirstPageScrollOffset;
        MeasuredPage firstPage2 = (MeasuredPage) visiblePages4.first();
        if (beforeContentPadding > 0 || spaceBetweenPages < 0) {
            int size2 = visiblePages4.size();
            for (int i2 = 0; i2 < size2 && currentFirstPageScrollOffset != 0 && pageSizeWithSpacing <= currentFirstPageScrollOffset && i2 != CollectionsKt.getLastIndex(visiblePages4); i2++) {
                currentFirstPageScrollOffset -= pageSizeWithSpacing;
                firstPage2 = (MeasuredPage) visiblePages4.get(i2 + 1);
            }
            currentFirstPageScrollOffset2 = currentFirstPageScrollOffset;
            firstPage = firstPage2;
        } else {
            currentFirstPageScrollOffset2 = currentFirstPageScrollOffset;
            firstPage = firstPage2;
        }
        int maxCrossAxis9 = maxCrossAxis;
        MeasuredPage firstPage3 = firstPage;
        List<MeasuredPage> listCreatePagesBeforeList = createPagesBeforeList(currentFirstPage, beyondBoundsPageCount, pinnedPages, new Function1<Integer, MeasuredPage>() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$measurePager$extraPagesBefore$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ MeasuredPage invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final MeasuredPage invoke(int it) {
                LazyLayoutMeasureScope lazyLayoutMeasureScope = measurePager;
                return PagerMeasureKt.m711getAndMeasureSGf7dI0(lazyLayoutMeasureScope, it, childConstraints, pagerItemProvider, visualPageOffset, orientation, horizontalAlignment, verticalAlignment, lazyLayoutMeasureScope.getLayoutDirection(), reverseLayout, pageAvailableSize);
            }
        });
        int size3 = listCreatePagesBeforeList.size();
        int maxCrossAxis10 = maxCrossAxis9;
        for (int index$iv2 = 0; index$iv2 < size3; index$iv2++) {
            Object item$iv2 = listCreatePagesBeforeList.get(index$iv2);
            maxCrossAxis10 = Math.max(maxCrossAxis10, ((MeasuredPage) item$iv2).getCrossAxisSize());
        }
        ArrayDeque visiblePages7 = visiblePages4;
        int currentMainAxisOffset3 = currentMainAxisOffset2;
        List<MeasuredPage> listCreatePagesAfterList = createPagesAfterList(((MeasuredPage) visiblePages4.last()).getIndex(), pageCount, beyondBoundsPageCount, pinnedPages, new Function1<Integer, MeasuredPage>() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$measurePager$extraPagesAfter$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ MeasuredPage invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final MeasuredPage invoke(int it) {
                LazyLayoutMeasureScope lazyLayoutMeasureScope = measurePager;
                return PagerMeasureKt.m711getAndMeasureSGf7dI0(lazyLayoutMeasureScope, it, childConstraints, pagerItemProvider, visualPageOffset, orientation, horizontalAlignment, verticalAlignment, lazyLayoutMeasureScope.getLayoutDirection(), reverseLayout, pageAvailableSize);
            }
        });
        int size4 = listCreatePagesAfterList.size();
        int maxCrossAxis11 = maxCrossAxis10;
        for (int index$iv3 = 0; index$iv3 < size4; index$iv3++) {
            Object item$iv3 = listCreatePagesAfterList.get(index$iv3);
            maxCrossAxis11 = Math.max(maxCrossAxis11, ((MeasuredPage) item$iv3).getCrossAxisSize());
        }
        boolean noExtraPages = Intrinsics.areEqual(firstPage3, visiblePages7.first()) && listCreatePagesBeforeList.isEmpty() && listCreatePagesAfterList.isEmpty();
        int layoutWidth = ConstraintsKt.m5232constrainWidthK40F9xA(constraints, orientation == Orientation.Vertical ? maxCrossAxis11 : currentMainAxisOffset3);
        int layoutHeight = ConstraintsKt.m5231constrainHeightK40F9xA(constraints, orientation == Orientation.Vertical ? currentMainAxisOffset3 : maxCrossAxis11);
        final List<MeasuredPage> listCalculatePagesOffsets = calculatePagesOffsets(measurePager, visiblePages7, listCreatePagesBeforeList, listCreatePagesAfterList, layoutWidth, layoutHeight, currentMainAxisOffset3, maxOffset4, visiblePagesScrollOffset, orientation, reverseLayout, measurePager, spaceBetweenPages, pageAvailableSize);
        if (noExtraPages) {
            list = listCalculatePagesOffsets;
        } else {
            List<MeasuredPage> list2 = listCalculatePagesOffsets;
            ArrayList target$iv = new ArrayList(list2.size());
            int index$iv$iv = 0;
            int size5 = list2.size();
            while (index$iv$iv < size5) {
                MeasuredPage measuredPage4 = list2.get(index$iv$iv);
                MeasuredPage it = measuredPage4;
                List<MeasuredPage> list3 = list2;
                if (it.getIndex() >= ((MeasuredPage) visiblePages7.first()).getIndex() && it.getIndex() <= ((MeasuredPage) visiblePages7.last()).getIndex()) {
                    target$iv.add(measuredPage4);
                }
                index$iv$iv++;
                list2 = list3;
            }
            list = target$iv;
        }
        int viewPortSize = orientation == Orientation.Vertical ? layoutHeight : layoutWidth;
        List<MeasuredPage> list4 = list;
        if (!list4.isEmpty()) {
            maxElem$iv = list4.get(0);
            MeasuredPage it2 = (MeasuredPage) maxElem$iv;
            float maxValue$iv = -Math.abs(SnapPositionInLayoutKt.calculateDistanceToDesiredSnapPosition(measurePager, viewPortSize, beforeContentPadding, afterContentPadding, pageAvailableSize, it2.getOffset(), it2.getIndex(), PagerStateKt.getSnapAlignmentStartToStart()));
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(list4);
            if (1 <= lastIndex) {
                while (true) {
                    Object e$iv = list4.get(i$iv);
                    MeasuredPage it3 = (MeasuredPage) e$iv;
                    float v$iv = -Math.abs(SnapPositionInLayoutKt.calculateDistanceToDesiredSnapPosition(measurePager, viewPortSize, beforeContentPadding, afterContentPadding, pageAvailableSize, it3.getOffset(), it3.getIndex(), PagerStateKt.getSnapAlignmentStartToStart()));
                    if (Float.compare(maxValue$iv, v$iv) < 0) {
                        maxValue$iv = v$iv;
                        maxElem$iv = e$iv;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        } else {
            maxElem$iv = null;
        }
        MeasuredPage closestPageToSnapPosition = (MeasuredPage) maxElem$iv;
        int maxOffset5 = maxOffset4;
        int index7 = maxCrossAxis6;
        return new PagerMeasureResult(list, pageCount, pageAvailableSize, spaceBetweenPages, afterContentPadding, orientation, -beforeContentPadding, maxOffset5 + afterContentPadding, reverseLayout, consumedScroll, firstPage3, closestPageToSnapPosition, currentFirstPageScrollOffset2, index7 < pageCount || currentMainAxisOffset3 > maxOffset5, layout.invoke(Integer.valueOf(layoutWidth), Integer.valueOf(layoutHeight), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$measurePager$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope invoke) {
                Intrinsics.checkNotNullParameter(invoke, "$this$invoke");
                List<MeasuredPage> list5 = listCalculatePagesOffsets;
                int size6 = list5.size();
                for (int index$iv4 = 0; index$iv4 < size6; index$iv4++) {
                    Object item$iv4 = list5.get(index$iv4);
                    MeasuredPage it4 = (MeasuredPage) item$iv4;
                    it4.place(invoke);
                }
            }
        }));
    }

    private static final List<MeasuredPage> createPagesAfterList(int currentLastPage, int pagesCount, int beyondBoundsPageCount, List<Integer> list, Function1<? super Integer, MeasuredPage> function1) {
        ArrayList arrayList = null;
        int end = Math.min(currentLastPage + beyondBoundsPageCount, pagesCount - 1);
        int i = currentLastPage + 1;
        if (i <= end) {
            while (true) {
                if (arrayList == null) {
                    Object list2 = new ArrayList();
                    arrayList = (List) list2;
                }
                arrayList.add(function1.invoke(Integer.valueOf(i)));
                if (i == end) {
                    break;
                }
                i++;
            }
        }
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            int pageIndex = ((Number) item$iv).intValue();
            boolean z = false;
            if (end + 1 <= pageIndex && pageIndex < pagesCount) {
                z = true;
            }
            if (z) {
                if (arrayList == null) {
                    Object list3 = new ArrayList();
                    arrayList = (List) list3;
                }
                arrayList.add(function1.invoke(Integer.valueOf(pageIndex)));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<MeasuredPage> createPagesBeforeList(int currentFirstPage, int beyondBoundsPageCount, List<Integer> list, Function1<? super Integer, MeasuredPage> function1) {
        ArrayList arrayList = null;
        int start = Math.max(0, currentFirstPage - beyondBoundsPageCount);
        int i = currentFirstPage - 1;
        if (start <= i) {
            while (true) {
                if (arrayList == null) {
                    Object list2 = new ArrayList();
                    arrayList = (List) list2;
                }
                arrayList.add(function1.invoke(Integer.valueOf(i)));
                if (i == start) {
                    break;
                }
                i--;
            }
        }
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            int pageIndex = ((Number) item$iv).intValue();
            if (pageIndex < start) {
                if (arrayList == null) {
                    Object list3 = new ArrayList();
                    arrayList = (List) list3;
                }
                arrayList.add(function1.invoke(Integer.valueOf(pageIndex)));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getAndMeasure-SGf7dI0, reason: not valid java name */
    public static final MeasuredPage m711getAndMeasureSGf7dI0(LazyLayoutMeasureScope $this$getAndMeasure_u2dSGf7dI0, int index, long childConstraints, PagerLazyLayoutItemProvider pagerItemProvider, long visualPageOffset, Orientation orientation, Alignment.Horizontal horizontalAlignment, Alignment.Vertical verticalAlignment, LayoutDirection layoutDirection, boolean reverseLayout, int pageAvailableSize) {
        Object key = pagerItemProvider.getKey(index);
        return new MeasuredPage(index, pageAvailableSize, $this$getAndMeasure_u2dSGf7dI0.mo645measure0kLqBqw(index, childConstraints), visualPageOffset, key, orientation, horizontalAlignment, verticalAlignment, layoutDirection, reverseLayout, null);
    }

    private static final List<MeasuredPage> calculatePagesOffsets(LazyLayoutMeasureScope $this$calculatePagesOffsets, List<MeasuredPage> list, List<MeasuredPage> list2, List<MeasuredPage> list3, int layoutWidth, int layoutHeight, int finalMainAxisOffset, int maxOffset, int pagesScrollOffset, Orientation orientation, boolean reverseLayout, Density density, int spaceBetweenPages, int pageAvailableSize) {
        int[] offsets;
        int pagesCount;
        int size;
        int pageSizeWithSpacing = pageAvailableSize + spaceBetweenPages;
        int mainAxisLayoutSize = orientation == Orientation.Vertical ? layoutHeight : layoutWidth;
        boolean hasSpareSpace = finalMainAxisOffset < Math.min(mainAxisLayoutSize, maxOffset);
        if (hasSpareSpace) {
            if (!(pagesScrollOffset == 0)) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        ArrayList positionedPages = new ArrayList(list.size() + list2.size() + list3.size());
        if (hasSpareSpace) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            int pagesCount2 = list.size();
            int[] sizes = new int[pagesCount2];
            for (int i = 0; i < pagesCount2; i++) {
                sizes[i] = pageAvailableSize;
            }
            int[] offsets2 = new int[pagesCount2];
            for (int i2 = 0; i2 < pagesCount2; i2++) {
                offsets2[i2] = 0;
            }
            Arrangement.HorizontalOrVertical arrangement = Arrangement.Absolute.INSTANCE.m397spacedBy0680j_4($this$calculatePagesOffsets.mo324toDpu2uoSUM(pageAvailableSize));
            if (orientation == Orientation.Vertical) {
                arrangement.arrange(density, mainAxisLayoutSize, sizes, offsets2);
                offsets = offsets2;
            } else {
                offsets = offsets2;
                arrangement.arrange(density, mainAxisLayoutSize, sizes, LayoutDirection.Ltr, offsets);
            }
            IntRange reverseAwareOffsetIndices = ArraysKt.getIndices(offsets);
            if (reverseLayout) {
                reverseAwareOffsetIndices = RangesKt.reversed(reverseAwareOffsetIndices);
            }
            int index = reverseAwareOffsetIndices.getFirst();
            int last = reverseAwareOffsetIndices.getLast();
            int step = reverseAwareOffsetIndices.getStep();
            if ((step > 0 && index <= last) || (step < 0 && last <= index)) {
                while (true) {
                    int absoluteOffset = offsets[index];
                    int pagesCount3 = pagesCount;
                    MeasuredPage page = list.get(calculatePagesOffsets$reverseAware(index, reverseLayout, pagesCount));
                    if (reverseLayout) {
                        size = (mainAxisLayoutSize - absoluteOffset) - page.getSize();
                    } else {
                        size = absoluteOffset;
                    }
                    int relativeOffset = size;
                    page.position(relativeOffset, layoutWidth, layoutHeight);
                    positionedPages.add(page);
                    if (index == last) {
                        break;
                    }
                    index += step;
                    pagesCount = pagesCount3;
                }
            }
        } else {
            positionedPages = positionedPages;
            int currentMainAxis = pagesScrollOffset;
            int size2 = list2.size();
            for (int index$iv = 0; index$iv < size2; index$iv++) {
                Object item$iv = list2.get(index$iv);
                MeasuredPage it = (MeasuredPage) item$iv;
                currentMainAxis -= pageSizeWithSpacing;
                it.position(currentMainAxis, layoutWidth, layoutHeight);
                positionedPages.add(it);
            }
            int currentMainAxis2 = pagesScrollOffset;
            int size3 = list.size();
            for (int index$iv2 = 0; index$iv2 < size3; index$iv2++) {
                Object item$iv2 = list.get(index$iv2);
                MeasuredPage it2 = (MeasuredPage) item$iv2;
                it2.position(currentMainAxis2, layoutWidth, layoutHeight);
                positionedPages.add(it2);
                currentMainAxis2 += pageSizeWithSpacing;
            }
            int size4 = list3.size();
            for (int index$iv3 = 0; index$iv3 < size4; index$iv3++) {
                Object item$iv3 = list3.get(index$iv3);
                MeasuredPage it3 = (MeasuredPage) item$iv3;
                it3.position(currentMainAxis2, layoutWidth, layoutHeight);
                positionedPages.add(it3);
                currentMainAxis2 += pageSizeWithSpacing;
            }
        }
        return positionedPages;
    }

    private static final int calculatePagesOffsets$reverseAware(int $this$calculatePagesOffsets_u24reverseAware, boolean $reverseLayout, int pagesCount) {
        return !$reverseLayout ? $this$calculatePagesOffsets_u24reverseAware : (pagesCount - $this$calculatePagesOffsets_u24reverseAware) - 1;
    }

    private static final void debugLog(Function0<String> function0) {
    }
}
