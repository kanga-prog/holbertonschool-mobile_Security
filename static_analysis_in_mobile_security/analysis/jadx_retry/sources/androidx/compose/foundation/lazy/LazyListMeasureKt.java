package androidx.compose.foundation.lazy;

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
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LazyListMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u008c\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001aB\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0004H\u0002\u001a4\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0004H\u0002\u001aî\u0001\u0010\u001f\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010,\u001a\u00020-2\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00042/\u0010.\u001a+\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020200¢\u0006\u0002\b3\u0012\u0004\u0012\u0002040/H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b5\u00106\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00067"}, d2 = {"calculateItemsOffsets", "", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "items", "", "extraItemsBefore", "extraItemsAfter", "layoutWidth", "", "layoutHeight", "finalMainAxisOffset", "maxOffset", "itemsScrollOffset", "isVertical", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "createItemsAfterList", "visibleItems", "measuredItemProvider", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "itemsCount", "beyondBoundsItemCount", "pinnedItems", "createItemsBeforeList", "currentFirstItemIndex", "measureLazyList", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenItems", "firstVisibleItemIndex", "firstVisibleItemScrollOffset", "scrollToBeConsumed", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "headerIndexes", "placementAnimator", "Landroidx/compose/foundation/lazy/LazyListItemPlacementAnimator;", "layout", "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyList-CD5nmq0", "(ILandroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;IIIIIIFJZLjava/util/List;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/LazyListItemPlacementAnimator;ILjava/util/List;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyListMeasureKt {
    /* JADX INFO: renamed from: measureLazyList-CD5nmq0, reason: not valid java name */
    public static final LazyListMeasureResult m594measureLazyListCD5nmq0(int itemsCount, LazyListMeasuredItemProvider measuredItemProvider, int mainAxisAvailableSize, int beforeContentPadding, int afterContentPadding, int spaceBetweenItems, int firstVisibleItemIndex, int firstVisibleItemScrollOffset, float scrollToBeConsumed, long constraints, boolean isVertical, List<Integer> headerIndexes, Arrangement.Vertical verticalArrangement, Arrangement.Horizontal horizontalArrangement, boolean reverseLayout, Density density, LazyListItemPlacementAnimator placementAnimator, int beyondBoundsItemCount, List<Integer> pinnedItems, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> layout) {
        int scrollDelta;
        int maxCrossAxis;
        int currentFirstItemIndex;
        int maxCrossAxis2;
        int currentFirstItemScrollOffset;
        LazyListMeasuredItem firstItem;
        LazyListMeasuredItem lazyListMeasuredItemFindOrComposeLazyListHeader;
        List<LazyListMeasuredItem> list;
        int currentMainAxisOffset;
        Intrinsics.checkNotNullParameter(measuredItemProvider, "measuredItemProvider");
        Intrinsics.checkNotNullParameter(headerIndexes, "headerIndexes");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(placementAnimator, "placementAnimator");
        Intrinsics.checkNotNullParameter(pinnedItems, "pinnedItems");
        Intrinsics.checkNotNullParameter(layout, "layout");
        String str = "Failed requirement.";
        if (!(beforeContentPadding >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(afterContentPadding >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (itemsCount <= 0) {
            return new LazyListMeasureResult(null, 0, false, 0.0f, layout.invoke(Integer.valueOf(Constraints.m5220getMinWidthimpl(constraints)), Integer.valueOf(Constraints.m5219getMinHeightimpl(constraints)), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$measureLazyList$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope invoke) {
                    Intrinsics.checkNotNullParameter(invoke, "$this$invoke");
                }
            }), CollectionsKt.emptyList(), -beforeContentPadding, mainAxisAvailableSize + afterContentPadding, 0, reverseLayout, isVertical ? Orientation.Vertical : Orientation.Horizontal, afterContentPadding, spaceBetweenItems);
        }
        int currentFirstItemIndex2 = firstVisibleItemIndex;
        int currentFirstItemScrollOffset2 = firstVisibleItemScrollOffset;
        if (currentFirstItemIndex2 >= itemsCount) {
            currentFirstItemIndex2 = itemsCount - 1;
            currentFirstItemScrollOffset2 = 0;
        }
        int scrollDelta2 = MathKt.roundToInt(scrollToBeConsumed);
        int currentFirstItemScrollOffset3 = currentFirstItemScrollOffset2 - scrollDelta2;
        if (currentFirstItemIndex2 == 0 && currentFirstItemScrollOffset3 < 0) {
            scrollDelta2 += currentFirstItemScrollOffset3;
            currentFirstItemScrollOffset3 = 0;
        }
        ArrayDeque visibleItems = new ArrayDeque();
        int minOffset = (-beforeContentPadding) + (spaceBetweenItems < 0 ? spaceBetweenItems : 0);
        int currentFirstItemScrollOffset4 = currentFirstItemScrollOffset3 + minOffset;
        int maxCrossAxis3 = 0;
        while (currentFirstItemScrollOffset4 < 0 && currentFirstItemIndex2 > 0) {
            int previous = currentFirstItemIndex2 - 1;
            String str2 = str;
            LazyListMeasuredItem measuredItem = measuredItemProvider.getAndMeasure(previous);
            visibleItems.add(0, measuredItem);
            maxCrossAxis3 = Math.max(maxCrossAxis3, measuredItem.getCrossAxisSize());
            currentFirstItemScrollOffset4 += measuredItem.getSizeWithSpacings();
            currentFirstItemIndex2 = previous;
            str = str2;
        }
        String str3 = str;
        if (currentFirstItemScrollOffset4 < minOffset) {
            scrollDelta2 += currentFirstItemScrollOffset4;
            currentFirstItemScrollOffset4 = minOffset;
        }
        int currentFirstItemScrollOffset5 = currentFirstItemScrollOffset4 - minOffset;
        int index = currentFirstItemIndex2;
        int currentFirstItemIndex3 = currentFirstItemIndex2;
        int maxMainAxis = RangesKt.coerceAtLeast(mainAxisAvailableSize + afterContentPadding, 0);
        int currentMainAxisOffset2 = -currentFirstItemScrollOffset5;
        int currentMainAxisOffset3 = currentMainAxisOffset2;
        ArrayDeque $this$fastForEach$iv = visibleItems;
        int index2 = index;
        int index3 = $this$fastForEach$iv.size();
        for (int currentFirstItemScrollOffset6 = 0; currentFirstItemScrollOffset6 < index3; currentFirstItemScrollOffset6++) {
            Object item$iv = $this$fastForEach$iv.get(currentFirstItemScrollOffset6);
            index2++;
            currentMainAxisOffset3 += ((LazyListMeasuredItem) item$iv).getSizeWithSpacings();
        }
        int currentMainAxisOffset4 = currentMainAxisOffset3;
        int currentFirstItemScrollOffset7 = currentFirstItemScrollOffset5;
        int index4 = index2;
        while (index4 < itemsCount && (currentMainAxisOffset4 < maxMainAxis || currentMainAxisOffset4 <= 0 || visibleItems.isEmpty())) {
            int maxMainAxis2 = maxMainAxis;
            LazyListMeasuredItem measuredItem2 = measuredItemProvider.getAndMeasure(index4);
            int currentMainAxisOffset5 = currentMainAxisOffset4 + measuredItem2.getSizeWithSpacings();
            if (currentMainAxisOffset5 > minOffset) {
                currentMainAxisOffset = currentMainAxisOffset5;
            } else {
                currentMainAxisOffset = currentMainAxisOffset5;
                int currentMainAxisOffset6 = itemsCount - 1;
                if (index4 != currentMainAxisOffset6) {
                    currentFirstItemScrollOffset7 -= measuredItem2.getSizeWithSpacings();
                    currentFirstItemIndex3 = index4 + 1;
                }
                index4++;
                currentMainAxisOffset4 = currentMainAxisOffset;
                maxMainAxis = maxMainAxis2;
            }
            int currentMainAxisOffset7 = measuredItem2.getCrossAxisSize();
            int maxCrossAxis4 = Math.max(maxCrossAxis3, currentMainAxisOffset7);
            visibleItems.add(measuredItem2);
            maxCrossAxis3 = maxCrossAxis4;
            index4++;
            currentMainAxisOffset4 = currentMainAxisOffset;
            maxMainAxis = maxMainAxis2;
        }
        if (currentMainAxisOffset4 >= mainAxisAvailableSize) {
            index4 = index4;
            minOffset = minOffset;
            scrollDelta = scrollDelta2;
            maxCrossAxis = maxCrossAxis3;
            currentFirstItemIndex = currentFirstItemIndex3;
            maxCrossAxis2 = currentMainAxisOffset4;
        } else {
            int toScrollBack = mainAxisAvailableSize - currentMainAxisOffset4;
            currentFirstItemScrollOffset7 -= toScrollBack;
            int currentMainAxisOffset8 = currentMainAxisOffset4 + toScrollBack;
            while (currentFirstItemScrollOffset7 < beforeContentPadding && currentFirstItemIndex3 > 0) {
                int previousIndex = currentFirstItemIndex3 - 1;
                int index5 = index4;
                LazyListMeasuredItem measuredItem3 = measuredItemProvider.getAndMeasure(previousIndex);
                visibleItems.add(0, measuredItem3);
                maxCrossAxis3 = Math.max(maxCrossAxis3, measuredItem3.getCrossAxisSize());
                currentFirstItemScrollOffset7 += measuredItem3.getSizeWithSpacings();
                currentFirstItemIndex3 = previousIndex;
                index4 = index5;
                minOffset = minOffset;
            }
            int scrollDelta3 = scrollDelta2 + toScrollBack;
            if (currentFirstItemScrollOffset7 >= 0) {
                scrollDelta = scrollDelta3;
                maxCrossAxis = maxCrossAxis3;
                currentFirstItemIndex = currentFirstItemIndex3;
                maxCrossAxis2 = currentMainAxisOffset8;
            } else {
                int scrollDelta4 = scrollDelta3 + currentFirstItemScrollOffset7;
                int currentMainAxisOffset9 = currentMainAxisOffset8 + currentFirstItemScrollOffset7;
                currentFirstItemScrollOffset7 = 0;
                scrollDelta = scrollDelta4;
                maxCrossAxis = maxCrossAxis3;
                currentFirstItemIndex = currentFirstItemIndex3;
                maxCrossAxis2 = currentMainAxisOffset9;
            }
        }
        int currentMainAxisOffset10 = MathKt.roundToInt(scrollToBeConsumed);
        float consumedScroll = (MathKt.getSign(currentMainAxisOffset10) == MathKt.getSign(scrollDelta) && Math.abs(MathKt.roundToInt(scrollToBeConsumed)) >= Math.abs(scrollDelta)) ? scrollDelta : scrollToBeConsumed;
        if (!(currentFirstItemScrollOffset7 >= 0)) {
            throw new IllegalArgumentException(str3.toString());
        }
        int visibleItemsScrollOffset = -currentFirstItemScrollOffset7;
        LazyListMeasuredItem firstItem2 = (LazyListMeasuredItem) visibleItems.first();
        if (beforeContentPadding > 0 || spaceBetweenItems < 0) {
            int maxCrossAxis5 = visibleItems.size();
            int scrollDelta5 = 0;
            while (scrollDelta5 < maxCrossAxis5) {
                int i = maxCrossAxis5;
                int size = ((LazyListMeasuredItem) visibleItems.get(scrollDelta5)).getSizeWithSpacings();
                if (currentFirstItemScrollOffset7 == 0 || size > currentFirstItemScrollOffset7 || scrollDelta5 == CollectionsKt.getLastIndex(visibleItems)) {
                    break;
                }
                currentFirstItemScrollOffset7 -= size;
                firstItem2 = (LazyListMeasuredItem) visibleItems.get(scrollDelta5 + 1);
                scrollDelta5++;
                maxCrossAxis5 = i;
            }
            currentFirstItemScrollOffset = currentFirstItemScrollOffset7;
            firstItem = firstItem2;
        } else {
            currentFirstItemScrollOffset = currentFirstItemScrollOffset7;
            firstItem = firstItem2;
        }
        List<LazyListMeasuredItem> listCreateItemsBeforeList = createItemsBeforeList(currentFirstItemIndex, measuredItemProvider, beyondBoundsItemCount, pinnedItems);
        List<LazyListMeasuredItem> list2 = listCreateItemsBeforeList;
        int $i$f$fastForEach = list2.size();
        int currentFirstItemIndex4 = 0;
        int maxCrossAxis6 = maxCrossAxis;
        while (currentFirstItemIndex4 < $i$f$fastForEach) {
            Object item$iv2 = list2.get(currentFirstItemIndex4);
            maxCrossAxis6 = Math.max(maxCrossAxis6, ((LazyListMeasuredItem) item$iv2).getCrossAxisSize());
            currentFirstItemIndex4++;
            list2 = list2;
        }
        List<LazyListMeasuredItem> listCreateItemsAfterList = createItemsAfterList(visibleItems, measuredItemProvider, itemsCount, beyondBoundsItemCount, pinnedItems);
        List<LazyListMeasuredItem> list3 = listCreateItemsAfterList;
        int $i$f$fastForEach2 = list3.size();
        int maxCrossAxis7 = maxCrossAxis6;
        int maxCrossAxis8 = 0;
        while (maxCrossAxis8 < $i$f$fastForEach2) {
            Object item$iv3 = list3.get(maxCrossAxis8);
            maxCrossAxis7 = Math.max(maxCrossAxis7, ((LazyListMeasuredItem) item$iv3).getCrossAxisSize());
            maxCrossAxis8++;
            list3 = list3;
        }
        boolean noExtraItems = Intrinsics.areEqual(firstItem, visibleItems.first()) && listCreateItemsBeforeList.isEmpty() && listCreateItemsAfterList.isEmpty();
        int layoutWidth = ConstraintsKt.m5232constrainWidthK40F9xA(constraints, isVertical ? maxCrossAxis7 : maxCrossAxis2);
        int layoutHeight = ConstraintsKt.m5231constrainHeightK40F9xA(constraints, isVertical ? maxCrossAxis2 : maxCrossAxis7);
        List<LazyListMeasuredItem> listCalculateItemsOffsets = calculateItemsOffsets(visibleItems, listCreateItemsBeforeList, listCreateItemsAfterList, layoutWidth, layoutHeight, maxCrossAxis2, mainAxisAvailableSize, visibleItemsScrollOffset, isVertical, verticalArrangement, horizontalArrangement, reverseLayout, density);
        int index6 = index4;
        int currentMainAxisOffset11 = maxCrossAxis2;
        placementAnimator.onMeasured((int) consumedScroll, layoutWidth, layoutHeight, listCalculateItemsOffsets, measuredItemProvider, isVertical);
        if (!headerIndexes.isEmpty()) {
            lazyListMeasuredItemFindOrComposeLazyListHeader = LazyListHeadersKt.findOrComposeLazyListHeader(listCalculateItemsOffsets, measuredItemProvider, headerIndexes, beforeContentPadding, layoutWidth, layoutHeight);
        } else {
            lazyListMeasuredItemFindOrComposeLazyListHeader = null;
        }
        final LazyListMeasuredItem headerItem = lazyListMeasuredItemFindOrComposeLazyListHeader;
        boolean z = index6 < itemsCount || currentMainAxisOffset11 > mainAxisAvailableSize;
        final List<LazyListMeasuredItem> list4 = listCalculateItemsOffsets;
        MeasureResult measureResultInvoke = layout.invoke(Integer.valueOf(layoutWidth), Integer.valueOf(layoutHeight), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$measureLazyList$5
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
                List<LazyListMeasuredItem> list5 = list4;
                LazyListMeasuredItem lazyListMeasuredItem = headerItem;
                int size2 = list5.size();
                for (int index$iv = 0; index$iv < size2; index$iv++) {
                    Object item$iv4 = list5.get(index$iv);
                    LazyListMeasuredItem it = (LazyListMeasuredItem) item$iv4;
                    if (it != lazyListMeasuredItem) {
                        it.place(invoke);
                    }
                }
                LazyListMeasuredItem lazyListMeasuredItem2 = headerItem;
                if (lazyListMeasuredItem2 != null) {
                    lazyListMeasuredItem2.place(invoke);
                }
            }
        });
        int i2 = -beforeContentPadding;
        int maxOffset = mainAxisAvailableSize + afterContentPadding;
        if (noExtraItems) {
            list = list4;
        } else {
            ArrayList target$iv = new ArrayList(list4.size());
            List<LazyListMeasuredItem> list5 = list4;
            int size2 = list5.size();
            int index$iv$iv = 0;
            while (index$iv$iv < size2) {
                LazyListMeasuredItem lazyListMeasuredItem = list5.get(index$iv$iv);
                int i3 = size2;
                List<LazyListMeasuredItem> list6 = list5;
                LazyListMeasuredItem it = lazyListMeasuredItem;
                List<LazyListMeasuredItem> list7 = list4;
                if ((it.getIndex() >= ((LazyListMeasuredItem) visibleItems.first()).getIndex() && it.getIndex() <= ((LazyListMeasuredItem) visibleItems.last()).getIndex()) || it == headerItem) {
                    target$iv.add(lazyListMeasuredItem);
                }
                index$iv$iv++;
                list5 = list6;
                size2 = i3;
                list4 = list7;
            }
            list = list4;
            list4 = target$iv;
        }
        return new LazyListMeasureResult(firstItem, currentFirstItemScrollOffset, z, consumedScroll, measureResultInvoke, list4, i2, maxOffset, itemsCount, reverseLayout, isVertical ? Orientation.Vertical : Orientation.Horizontal, afterContentPadding, spaceBetweenItems);
    }

    private static final List<LazyListMeasuredItem> createItemsAfterList(List<LazyListMeasuredItem> list, LazyListMeasuredItemProvider measuredItemProvider, int itemsCount, int beyondBoundsItemCount, List<Integer> list2) {
        ArrayList arrayList = null;
        int end = Math.min(((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex() + beyondBoundsItemCount, itemsCount - 1);
        int i = ((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex() + 1;
        if (i <= end) {
            while (true) {
                if (arrayList == null) {
                    Object list3 = new ArrayList();
                    arrayList = (List) list3;
                }
                arrayList.add(measuredItemProvider.getAndMeasure(i));
                if (i == end) {
                    break;
                }
                i++;
            }
        }
        int size = list2.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list2.get(index$iv);
            int index = ((Number) item$iv).intValue();
            if (index > end) {
                if (arrayList == null) {
                    Object list4 = new ArrayList();
                    arrayList = (List) list4;
                }
                arrayList.add(measuredItemProvider.getAndMeasure(index));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyListMeasuredItem> createItemsBeforeList(int currentFirstItemIndex, LazyListMeasuredItemProvider measuredItemProvider, int beyondBoundsItemCount, List<Integer> list) {
        ArrayList arrayList = null;
        int start = Math.max(0, currentFirstItemIndex - beyondBoundsItemCount);
        int i = currentFirstItemIndex - 1;
        if (start <= i) {
            while (true) {
                if (arrayList == null) {
                    Object list2 = new ArrayList();
                    arrayList = (List) list2;
                }
                arrayList.add(measuredItemProvider.getAndMeasure(i));
                if (i == start) {
                    break;
                }
                i--;
            }
        }
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            int index = ((Number) item$iv).intValue();
            if (index < start) {
                if (arrayList == null) {
                    Object list3 = new ArrayList();
                    arrayList = (List) list3;
                }
                arrayList.add(measuredItemProvider.getAndMeasure(index));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyListMeasuredItem> calculateItemsOffsets(List<LazyListMeasuredItem> list, List<LazyListMeasuredItem> list2, List<LazyListMeasuredItem> list3, int layoutWidth, int layoutHeight, int finalMainAxisOffset, int maxOffset, int itemsScrollOffset, boolean isVertical, Arrangement.Vertical verticalArrangement, Arrangement.Horizontal horizontalArrangement, boolean reverseLayout, Density density) {
        int[] offsets;
        int size;
        List<LazyListMeasuredItem> list4 = list;
        int mainAxisLayoutSize = isVertical ? layoutHeight : layoutWidth;
        boolean hasSpareSpace = finalMainAxisOffset < Math.min(mainAxisLayoutSize, maxOffset);
        if (hasSpareSpace) {
            if (!(itemsScrollOffset == 0)) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        ArrayList positionedItems = new ArrayList(list.size() + list2.size() + list3.size());
        if (hasSpareSpace) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            int itemsCount = list.size();
            int[] sizes = new int[itemsCount];
            for (int i = 0; i < itemsCount; i++) {
                sizes[i] = list4.get(calculateItemsOffsets$reverseAware(i, reverseLayout, itemsCount)).getSize();
            }
            int[] offsets2 = new int[itemsCount];
            for (int i2 = 0; i2 < itemsCount; i2++) {
                offsets2[i2] = 0;
            }
            if (isVertical) {
                if (verticalArrangement == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                verticalArrangement.arrange(density, mainAxisLayoutSize, sizes, offsets2);
                offsets = offsets2;
            } else {
                if (horizontalArrangement == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                offsets = offsets2;
                horizontalArrangement.arrange(density, mainAxisLayoutSize, sizes, LayoutDirection.Ltr, offsets);
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
                    LazyListMeasuredItem item = list4.get(calculateItemsOffsets$reverseAware(index, reverseLayout, itemsCount));
                    if (reverseLayout) {
                        size = (mainAxisLayoutSize - absoluteOffset) - item.getSize();
                    } else {
                        size = absoluteOffset;
                    }
                    int relativeOffset = size;
                    item.position(relativeOffset, layoutWidth, layoutHeight);
                    positionedItems.add(item);
                    if (index == last) {
                        break;
                    }
                    index += step;
                    list4 = list;
                }
            }
        } else {
            int currentMainAxis = itemsScrollOffset;
            int size2 = list2.size();
            for (int index$iv = 0; index$iv < size2; index$iv++) {
                Object item$iv = list2.get(index$iv);
                LazyListMeasuredItem it = (LazyListMeasuredItem) item$iv;
                currentMainAxis -= it.getSizeWithSpacings();
                it.position(currentMainAxis, layoutWidth, layoutHeight);
                positionedItems.add(it);
            }
            int currentMainAxis2 = itemsScrollOffset;
            int size3 = list.size();
            for (int index$iv2 = 0; index$iv2 < size3; index$iv2++) {
                Object item$iv2 = list.get(index$iv2);
                LazyListMeasuredItem it2 = (LazyListMeasuredItem) item$iv2;
                it2.position(currentMainAxis2, layoutWidth, layoutHeight);
                positionedItems.add(it2);
                currentMainAxis2 += it2.getSizeWithSpacings();
            }
            int size4 = list3.size();
            for (int index$iv3 = 0; index$iv3 < size4; index$iv3++) {
                Object item$iv3 = list3.get(index$iv3);
                LazyListMeasuredItem it3 = (LazyListMeasuredItem) item$iv3;
                it3.position(currentMainAxis2, layoutWidth, layoutHeight);
                positionedItems.add(it3);
                currentMainAxis2 += it3.getSizeWithSpacings();
            }
        }
        return positionedItems;
    }

    private static final int calculateItemsOffsets$reverseAware(int $this$calculateItemsOffsets_u24reverseAware, boolean $reverseLayout, int itemsCount) {
        return !$reverseLayout ? $this$calculateItemsOffsets_u24reverseAware : (itemsCount - $this$calculateItemsOffsets_u24reverseAware) - 1;
    }
}
