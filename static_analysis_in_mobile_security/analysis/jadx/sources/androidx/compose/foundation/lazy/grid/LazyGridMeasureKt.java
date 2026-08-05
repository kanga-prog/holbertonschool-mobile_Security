package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LazyGridMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aP\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\bH\u0083\bø\u0001\u0000\u001a\u008c\u0001\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002\u001aè\u0001\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012/\u00101\u001a+\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u0002040\b¢\u0006\u0002\b5\u0012\u0004\u0012\u00020602H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b7\u00108\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00069"}, d2 = {"calculateExtraItems", "", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "pinnedItems", "", "measuredItemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;", "itemConstraints", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/Constraints;", "filter", "", "calculateItemsOffsets", "", "lines", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLine;", "itemsBefore", "itemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "firstLineScrollOffset", "isVertical", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "measureLazyGrid", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "itemsCount", "measuredLineProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenLines", "firstVisibleLineIndex", "firstVisibleLineScrollOffset", "scrollToBeConsumed", "", "constraints", "placementAnimator", "Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;", "spanLayoutProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;", "layout", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyGrid-ZRKPzZ8", "(ILandroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;IIIIIIFJZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;Ljava/util/List;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyGridMeasureKt {
    /* JADX INFO: renamed from: measureLazyGrid-ZRKPzZ8, reason: not valid java name */
    public static final LazyGridMeasureResult m622measureLazyGridZRKPzZ8(int itemsCount, LazyGridMeasuredLineProvider measuredLineProvider, LazyGridMeasuredItemProvider measuredItemProvider, int mainAxisAvailableSize, int beforeContentPadding, int afterContentPadding, int spaceBetweenLines, int firstVisibleLineIndex, int firstVisibleLineScrollOffset, float scrollToBeConsumed, long constraints, boolean isVertical, Arrangement.Vertical verticalArrangement, Arrangement.Horizontal horizontalArrangement, boolean reverseLayout, Density density, LazyGridItemPlacementAnimator placementAnimator, LazyGridSpanLayoutProvider spanLayoutProvider, List<Integer> pinnedItems, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> layout) {
        int currentFirstLineIndex;
        int currentMainAxisOffset;
        int currentMainAxisOffset2;
        int scrollDelta;
        ArrayDeque visibleLines;
        LazyGridMeasuredLine firstLine;
        int layoutWidth;
        int layoutHeight;
        List<LazyGridMeasuredItem> list;
        Orientation orientation;
        ArrayList arrayList;
        float consumedScroll;
        ArrayList arrayList2;
        LazyGridMeasuredItem[] items;
        LazyGridMeasuredItem lazyGridMeasuredItem;
        int minOffset;
        int currentMainAxisOffset3;
        Intrinsics.checkNotNullParameter(measuredLineProvider, "measuredLineProvider");
        Intrinsics.checkNotNullParameter(measuredItemProvider, "measuredItemProvider");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(placementAnimator, "placementAnimator");
        Intrinsics.checkNotNullParameter(spanLayoutProvider, "spanLayoutProvider");
        Intrinsics.checkNotNullParameter(pinnedItems, "pinnedItems");
        Intrinsics.checkNotNullParameter(layout, "layout");
        if (!(beforeContentPadding >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(afterContentPadding >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (itemsCount <= 0) {
            return new LazyGridMeasureResult(null, 0, false, 0.0f, layout.invoke(Integer.valueOf(Constraints.m5220getMinWidthimpl(constraints)), Integer.valueOf(Constraints.m5219getMinHeightimpl(constraints)), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope invoke) {
                    Intrinsics.checkNotNullParameter(invoke, "$this$invoke");
                }
            }), CollectionsKt.emptyList(), -beforeContentPadding, mainAxisAvailableSize + afterContentPadding, 0, reverseLayout, isVertical ? Orientation.Vertical : Orientation.Horizontal, afterContentPadding, spaceBetweenLines);
        }
        int currentFirstLineIndex2 = firstVisibleLineIndex;
        int scrollDelta2 = MathKt.roundToInt(scrollToBeConsumed);
        int currentFirstLineScrollOffset = firstVisibleLineScrollOffset - scrollDelta2;
        if (currentFirstLineIndex2 == 0 && currentFirstLineScrollOffset < 0) {
            scrollDelta2 += currentFirstLineScrollOffset;
            currentFirstLineScrollOffset = 0;
        }
        ArrayDeque visibleLines2 = new ArrayDeque();
        int minOffset2 = (-beforeContentPadding) + (spaceBetweenLines < 0 ? spaceBetweenLines : 0);
        int currentFirstLineScrollOffset2 = currentFirstLineScrollOffset + minOffset2;
        while (currentFirstLineScrollOffset2 < 0 && currentFirstLineIndex2 > 0) {
            int previous = currentFirstLineIndex2 - 1;
            LazyGridMeasuredLine measuredLine = measuredLineProvider.getAndMeasure(previous);
            visibleLines2.add(0, measuredLine);
            currentFirstLineScrollOffset2 += measuredLine.getMainAxisSizeWithSpacings();
            currentFirstLineIndex2 = previous;
        }
        if (currentFirstLineScrollOffset2 < minOffset2) {
            scrollDelta2 += currentFirstLineScrollOffset2;
            currentFirstLineScrollOffset2 = minOffset2;
        }
        int currentFirstLineScrollOffset3 = currentFirstLineScrollOffset2 - minOffset2;
        int index = currentFirstLineIndex2;
        int currentFirstLineIndex3 = currentFirstLineIndex2;
        int maxMainAxis = RangesKt.coerceAtLeast(mainAxisAvailableSize + afterContentPadding, 0);
        int currentMainAxisOffset4 = -currentFirstLineScrollOffset3;
        int currentMainAxisOffset5 = currentMainAxisOffset4;
        ArrayDeque $this$fastForEach$iv = visibleLines2;
        int index2 = index;
        int index3 = $this$fastForEach$iv.size();
        for (int currentFirstLineScrollOffset4 = 0; currentFirstLineScrollOffset4 < index3; currentFirstLineScrollOffset4++) {
            Object item$iv = $this$fastForEach$iv.get(currentFirstLineScrollOffset4);
            LazyGridMeasuredLine it = (LazyGridMeasuredLine) item$iv;
            index2++;
            currentMainAxisOffset5 += it.getMainAxisSizeWithSpacings();
        }
        int currentFirstLineIndex4 = currentFirstLineIndex3;
        int currentMainAxisOffset6 = currentMainAxisOffset5;
        int currentFirstLineScrollOffset5 = currentFirstLineScrollOffset3;
        int index4 = index2;
        while (true) {
            if (index4 >= itemsCount) {
                currentFirstLineIndex = currentFirstLineIndex4;
                break;
            }
            if (currentMainAxisOffset6 >= maxMainAxis && currentMainAxisOffset6 > 0 && !visibleLines2.isEmpty()) {
                currentFirstLineIndex = currentFirstLineIndex4;
                break;
            }
            currentFirstLineIndex = currentFirstLineIndex4;
            LazyGridMeasuredLine measuredLine2 = measuredLineProvider.getAndMeasure(index4);
            if (measuredLine2.isEmpty()) {
                break;
            }
            int currentMainAxisOffset7 = currentMainAxisOffset6 + measuredLine2.getMainAxisSizeWithSpacings();
            if (currentMainAxisOffset7 > minOffset2) {
                minOffset = minOffset2;
                currentMainAxisOffset3 = currentMainAxisOffset7;
            } else {
                minOffset = minOffset2;
                currentMainAxisOffset3 = currentMainAxisOffset7;
                int currentMainAxisOffset8 = itemsCount - 1;
                if (((LazyGridMeasuredItem) ArraysKt.last(measuredLine2.getItems())).getIndex() != currentMainAxisOffset8) {
                    currentFirstLineScrollOffset5 -= measuredLine2.getMainAxisSizeWithSpacings();
                    currentFirstLineIndex = index4 + 1;
                }
                index4++;
                currentFirstLineIndex4 = currentFirstLineIndex;
                currentMainAxisOffset6 = currentMainAxisOffset3;
                minOffset2 = minOffset;
            }
            visibleLines2.add(measuredLine2);
            index4++;
            currentFirstLineIndex4 = currentFirstLineIndex;
            currentMainAxisOffset6 = currentMainAxisOffset3;
            minOffset2 = minOffset;
        }
        if (currentMainAxisOffset6 >= mainAxisAvailableSize) {
            maxMainAxis = maxMainAxis;
            currentMainAxisOffset = currentMainAxisOffset6;
            currentMainAxisOffset2 = currentFirstLineScrollOffset5;
            scrollDelta = scrollDelta2;
        } else {
            int toScrollBack = mainAxisAvailableSize - currentMainAxisOffset6;
            int currentFirstLineScrollOffset6 = currentFirstLineScrollOffset5 - toScrollBack;
            int currentMainAxisOffset9 = currentMainAxisOffset6 + toScrollBack;
            int currentFirstLineIndex5 = currentFirstLineIndex;
            while (currentFirstLineScrollOffset6 < beforeContentPadding && currentFirstLineIndex5 > 0) {
                int previousIndex = currentFirstLineIndex5 - 1;
                LazyGridMeasuredLine measuredLine3 = measuredLineProvider.getAndMeasure(previousIndex);
                visibleLines2.add(0, measuredLine3);
                currentFirstLineScrollOffset6 += measuredLine3.getMainAxisSizeWithSpacings();
                currentFirstLineIndex5 = previousIndex;
                maxMainAxis = maxMainAxis;
            }
            int scrollDelta3 = scrollDelta2 + toScrollBack;
            if (currentFirstLineScrollOffset6 >= 0) {
                currentMainAxisOffset = currentMainAxisOffset9;
                currentMainAxisOffset2 = currentFirstLineScrollOffset6;
                scrollDelta = scrollDelta3;
            } else {
                currentMainAxisOffset = currentMainAxisOffset9 + currentFirstLineScrollOffset6;
                currentMainAxisOffset2 = 0;
                scrollDelta = scrollDelta3 + currentFirstLineScrollOffset6;
            }
        }
        float consumedScroll2 = (MathKt.getSign(MathKt.roundToInt(scrollToBeConsumed)) == MathKt.getSign(scrollDelta) && Math.abs(MathKt.roundToInt(scrollToBeConsumed)) >= Math.abs(scrollDelta)) ? scrollDelta : scrollToBeConsumed;
        if (!(currentMainAxisOffset2 >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int visibleLinesScrollOffset = -currentMainAxisOffset2;
        LazyGridMeasuredLine firstLine2 = (LazyGridMeasuredLine) visibleLines2.first();
        LazyGridMeasuredItem lazyGridMeasuredItem2 = (LazyGridMeasuredItem) ArraysKt.firstOrNull(firstLine2.getItems());
        int firstItemIndex = lazyGridMeasuredItem2 != null ? lazyGridMeasuredItem2.getIndex() : 0;
        LazyGridMeasuredLine lazyGridMeasuredLine = (LazyGridMeasuredLine) visibleLines2.lastOrNull();
        int lastItemIndex = (lazyGridMeasuredLine == null || (items = lazyGridMeasuredLine.getItems()) == null || (lazyGridMeasuredItem = (LazyGridMeasuredItem) ArraysKt.lastOrNull(items)) == null) ? 0 : lazyGridMeasuredItem.getIndex();
        ArrayList arrayList3 = null;
        List<Integer> list2 = pinnedItems;
        ArrayDeque visibleLines3 = visibleLines2;
        int size = list2.size();
        int index$iv$iv = 0;
        while (index$iv$iv < size) {
            int i = size;
            List<Integer> list3 = list2;
            Object item$iv$iv = list3.get(index$iv$iv);
            int index$iv = ((Number) item$iv$iv).intValue();
            int scrollDelta4 = scrollDelta;
            int it2 = (index$iv < 0 || index$iv >= firstItemIndex) ? 0 : 1;
            if (it2 != 0) {
                long constraints$iv = measuredLineProvider.m628itemConstraintsOenEA2s(index$iv);
                consumedScroll = consumedScroll2;
                LazyGridMeasuredItem measuredItem$iv = LazyGridMeasuredItemProvider.m625getAndMeasure3p2s80s$default(measuredItemProvider, index$iv, 0, constraints$iv, 2, null);
                if (arrayList3 != null) {
                    arrayList2 = arrayList3;
                } else {
                    Object items$iv = new ArrayList();
                    arrayList2 = (List) items$iv;
                }
                arrayList2.add(measuredItem$iv);
                arrayList3 = arrayList2;
            } else {
                consumedScroll = consumedScroll2;
            }
            index$iv$iv++;
            list2 = list3;
            size = i;
            scrollDelta = scrollDelta4;
            firstItemIndex = firstItemIndex;
            consumedScroll2 = consumedScroll;
            visibleLinesScrollOffset = visibleLinesScrollOffset;
            currentMainAxisOffset2 = currentMainAxisOffset2;
            maxMainAxis = maxMainAxis;
            visibleLines3 = visibleLines3;
        }
        int visibleLinesScrollOffset2 = visibleLinesScrollOffset;
        int currentFirstLineScrollOffset7 = currentMainAxisOffset2;
        float consumedScroll3 = consumedScroll2;
        int firstItemIndex2 = firstItemIndex;
        ArrayDeque visibleLines4 = visibleLines3;
        List extraItemsBefore = arrayList3 == null ? CollectionsKt.emptyList() : arrayList3;
        List<Integer> list4 = pinnedItems;
        int size2 = list4.size();
        ArrayList arrayList4 = null;
        int index$iv$iv2 = 0;
        while (index$iv$iv2 < size2) {
            Object item$iv$iv2 = list4.get(index$iv$iv2);
            int index$iv2 = ((Number) item$iv$iv2).intValue();
            int lastItemIndex2 = lastItemIndex;
            int it3 = (lastItemIndex2 + 1 > index$iv2 || index$iv2 >= itemsCount) ? 0 : 1;
            if (it3 != 0) {
                long constraints$iv2 = measuredLineProvider.m628itemConstraintsOenEA2s(index$iv2);
                LazyGridMeasuredItem measuredItem$iv2 = LazyGridMeasuredItemProvider.m625getAndMeasure3p2s80s$default(measuredItemProvider, index$iv2, 0, constraints$iv2, 2, null);
                if (arrayList4 != null) {
                    arrayList = arrayList4;
                } else {
                    Object items$iv2 = new ArrayList();
                    arrayList = (List) items$iv2;
                }
                arrayList.add(measuredItem$iv2);
                arrayList4 = arrayList;
            }
            index$iv$iv2++;
            size2 = size2;
            list4 = list4;
            lastItemIndex = lastItemIndex2;
        }
        int lastItemIndex3 = lastItemIndex;
        List extraItemsAfter = arrayList4 == null ? CollectionsKt.emptyList() : arrayList4;
        if (beforeContentPadding > 0 || spaceBetweenLines < 0) {
            int i2 = 0;
            int size3 = visibleLines4.size();
            int currentFirstLineScrollOffset8 = currentFirstLineScrollOffset7;
            while (true) {
                if (i2 >= size3) {
                    visibleLines = visibleLines4;
                    break;
                }
                visibleLines = visibleLines4;
                int size4 = ((LazyGridMeasuredLine) visibleLines.get(i2)).getMainAxisSizeWithSpacings();
                if (currentFirstLineScrollOffset8 == 0 || size4 > currentFirstLineScrollOffset8 || i2 == CollectionsKt.getLastIndex(visibleLines)) {
                    break;
                }
                currentFirstLineScrollOffset8 -= size4;
                firstLine2 = (LazyGridMeasuredLine) visibleLines.get(i2 + 1);
                i2++;
                visibleLines4 = visibleLines;
            }
            currentFirstLineScrollOffset7 = currentFirstLineScrollOffset8;
            firstLine = firstLine2;
        } else {
            firstLine = firstLine2;
            visibleLines = visibleLines4;
        }
        if (isVertical) {
            layoutWidth = Constraints.m5218getMaxWidthimpl(constraints);
        } else {
            layoutWidth = ConstraintsKt.m5232constrainWidthK40F9xA(constraints, currentMainAxisOffset);
        }
        if (isVertical) {
            layoutHeight = ConstraintsKt.m5231constrainHeightK40F9xA(constraints, currentMainAxisOffset);
        } else {
            layoutHeight = Constraints.m5217getMaxHeightimpl(constraints);
        }
        final List<LazyGridMeasuredItem> listCalculateItemsOffsets = calculateItemsOffsets(visibleLines, extraItemsBefore, extraItemsAfter, layoutWidth, layoutHeight, currentMainAxisOffset, mainAxisAvailableSize, visibleLinesScrollOffset2, isVertical, verticalArrangement, horizontalArrangement, reverseLayout, density);
        int currentMainAxisOffset10 = currentMainAxisOffset;
        placementAnimator.onMeasured((int) consumedScroll3, layoutWidth, layoutHeight, listCalculateItemsOffsets, measuredItemProvider, spanLayoutProvider, isVertical);
        boolean z = lastItemIndex3 != itemsCount + (-1) || currentMainAxisOffset10 > mainAxisAvailableSize;
        MeasureResult measureResultInvoke = layout.invoke(Integer.valueOf(layoutWidth), Integer.valueOf(layoutHeight), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$3
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
                List<LazyGridMeasuredItem> list5 = listCalculateItemsOffsets;
                int size5 = list5.size();
                for (int index$iv3 = 0; index$iv3 < size5; index$iv3++) {
                    Object item$iv2 = list5.get(index$iv3);
                    LazyGridMeasuredItem it4 = (LazyGridMeasuredItem) item$iv2;
                    it4.place(invoke);
                }
            }
        });
        int i3 = -beforeContentPadding;
        int index5 = mainAxisAvailableSize + afterContentPadding;
        if (!extraItemsBefore.isEmpty() || !extraItemsAfter.isEmpty()) {
            ArrayList target$iv = new ArrayList(listCalculateItemsOffsets.size());
            List<LazyGridMeasuredItem> list5 = listCalculateItemsOffsets;
            int size5 = list5.size();
            int $i$f$fastFilter = 0;
            while ($i$f$fastFilter < size5) {
                LazyGridMeasuredItem lazyGridMeasuredItem3 = list5.get($i$f$fastFilter);
                int i4 = size5;
                LazyGridMeasuredItem it4 = lazyGridMeasuredItem3;
                List<LazyGridMeasuredItem> list6 = list5;
                int index6 = it4.getIndex();
                int firstItemIndex3 = firstItemIndex2;
                if (firstItemIndex3 <= index6 && index6 <= lastItemIndex3) {
                    target$iv.add(lazyGridMeasuredItem3);
                }
                $i$f$fastFilter++;
                firstItemIndex2 = firstItemIndex3;
                size5 = i4;
                list5 = list6;
            }
            list = target$iv;
        }
        if (isVertical) {
            list = listCalculateItemsOffsets;
            orientation = Orientation.Vertical;
        } else {
            list = listCalculateItemsOffsets;
            orientation = Orientation.Horizontal;
        }
        return new LazyGridMeasureResult(firstLine, currentFirstLineScrollOffset7, z, consumedScroll3, measureResultInvoke, list, i3, index5, itemsCount, reverseLayout, orientation, afterContentPadding, spaceBetweenLines);
    }

    private static final List<LazyGridMeasuredItem> calculateExtraItems(List<Integer> list, LazyGridMeasuredItemProvider measuredItemProvider, Function1<? super Integer, Constraints> function1, Function1<? super Integer, Boolean> function2) {
        ArrayList arrayList = null;
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            int index = ((Number) item$iv).intValue();
            if (function2.invoke(Integer.valueOf(index)).booleanValue()) {
                long constraints = function1.invoke(Integer.valueOf(index)).getValue();
                LazyGridMeasuredItem measuredItem = LazyGridMeasuredItemProvider.m625getAndMeasure3p2s80s$default(measuredItemProvider, index, 0, constraints, 2, null);
                if (arrayList == null) {
                    Object items = new ArrayList();
                    arrayList = (List) items;
                }
                arrayList.add(measuredItem);
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyGridMeasuredItem> calculateItemsOffsets(List<LazyGridMeasuredLine> list, List<LazyGridMeasuredItem> list2, List<LazyGridMeasuredItem> list3, int layoutWidth, int layoutHeight, int finalMainAxisOffset, int maxOffset, int firstLineScrollOffset, boolean isVertical, Arrangement.Vertical verticalArrangement, Arrangement.Horizontal horizontalArrangement, boolean reverseLayout, Density density) {
        ArrayList positionedItems;
        int mainAxisSize;
        List<LazyGridMeasuredLine> list4 = list;
        int mainAxisLayoutSize = isVertical ? layoutHeight : layoutWidth;
        boolean hasSpareSpace = finalMainAxisOffset < Math.min(mainAxisLayoutSize, maxOffset);
        if (hasSpareSpace) {
            if (!(firstLineScrollOffset == 0)) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        int sum$iv = 0;
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            sum$iv += ((LazyGridMeasuredLine) item$iv$iv).getItems().length;
        }
        ArrayList positionedItems2 = new ArrayList(sum$iv);
        if (hasSpareSpace) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            int linesCount = list.size();
            int[] sizes = new int[linesCount];
            for (int i = 0; i < linesCount; i++) {
                sizes[i] = list4.get(calculateItemsOffsets$reverseAware(i, reverseLayout, linesCount)).getMainAxisSize();
            }
            int[] offsets = new int[linesCount];
            for (int i2 = 0; i2 < linesCount; i2++) {
                offsets[i2] = 0;
            }
            if (isVertical) {
                if (verticalArrangement == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                verticalArrangement.arrange(density, mainAxisLayoutSize, sizes, offsets);
            } else {
                if (horizontalArrangement == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                horizontalArrangement.arrange(density, mainAxisLayoutSize, sizes, LayoutDirection.Ltr, offsets);
            }
            IntRange reverseAwareOffsetIndices = ArraysKt.getIndices((int[]) r7);
            if (reverseLayout) {
                reverseAwareOffsetIndices = RangesKt.reversed(reverseAwareOffsetIndices);
            }
            int index = reverseAwareOffsetIndices.getFirst();
            int last = reverseAwareOffsetIndices.getLast();
            int step = reverseAwareOffsetIndices.getStep();
            if ((step > 0 && index <= last) || (step < 0 && last <= index)) {
                while (true) {
                    int absoluteOffset = offsets[index];
                    LazyGridMeasuredLine line = list4.get(calculateItemsOffsets$reverseAware(index, reverseLayout, linesCount));
                    if (reverseLayout) {
                        mainAxisSize = (mainAxisLayoutSize - absoluteOffset) - line.getMainAxisSize();
                    } else {
                        mainAxisSize = absoluteOffset;
                    }
                    int relativeOffset = mainAxisSize;
                    int linesCount2 = linesCount;
                    IntProgression reverseAwareOffsetIndices2 = reverseAwareOffsetIndices;
                    CollectionsKt.addAll(positionedItems2, line.position(relativeOffset, layoutWidth, layoutHeight));
                    if (index == last) {
                        break;
                    }
                    index += step;
                    list4 = list;
                    linesCount = linesCount2;
                    reverseAwareOffsetIndices = reverseAwareOffsetIndices2;
                }
            }
            positionedItems = positionedItems2;
        } else {
            int currentMainAxis = firstLineScrollOffset;
            List<LazyGridMeasuredItem> list5 = list2;
            int size2 = list5.size();
            int index$iv = 0;
            while (index$iv < size2) {
                Object item$iv = list5.get(index$iv);
                LazyGridMeasuredItem it = (LazyGridMeasuredItem) item$iv;
                int currentMainAxis2 = currentMainAxis - it.getMainAxisSizeWithSpacings();
                ArrayList positionedItems3 = positionedItems2;
                LazyGridMeasuredItem.position$default(it, currentMainAxis2, 0, layoutWidth, layoutHeight, 0, 0, 48, null);
                positionedItems3.add(it);
                index$iv++;
                positionedItems2 = positionedItems3;
                size2 = size2;
                list5 = list5;
                currentMainAxis = currentMainAxis2;
            }
            positionedItems = positionedItems2;
            int currentMainAxis3 = firstLineScrollOffset;
            List<LazyGridMeasuredLine> list6 = list;
            int index$iv2 = 0;
            int size3 = list6.size();
            while (index$iv2 < size3) {
                Object item$iv2 = list6.get(index$iv2);
                LazyGridMeasuredLine it2 = (LazyGridMeasuredLine) item$iv2;
                CollectionsKt.addAll(positionedItems, it2.position(currentMainAxis3, layoutWidth, layoutHeight));
                currentMainAxis3 += it2.getMainAxisSizeWithSpacings();
                index$iv2++;
                list6 = list6;
            }
            List<LazyGridMeasuredItem> list7 = list3;
            int size4 = list7.size();
            int currentMainAxis4 = currentMainAxis3;
            int index$iv3 = 0;
            while (index$iv3 < size4) {
                Object item$iv3 = list7.get(index$iv3);
                LazyGridMeasuredItem it3 = (LazyGridMeasuredItem) item$iv3;
                LazyGridMeasuredItem.position$default(it3, currentMainAxis4, 0, layoutWidth, layoutHeight, 0, 0, 48, null);
                positionedItems.add(it3);
                currentMainAxis4 += it3.getMainAxisSizeWithSpacings();
                index$iv3++;
                size4 = size4;
                list7 = list7;
            }
        }
        return positionedItems;
    }

    private static final int calculateItemsOffsets$reverseAware(int $this$calculateItemsOffsets_u24reverseAware, boolean $reverseLayout, int linesCount) {
        return !$reverseLayout ? $this$calculateItemsOffsets_u24reverseAware : (linesCount - $this$calculateItemsOffsets_u24reverseAware) - 1;
    }
}
