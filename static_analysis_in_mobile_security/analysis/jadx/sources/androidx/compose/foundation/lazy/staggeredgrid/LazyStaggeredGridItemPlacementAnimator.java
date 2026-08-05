package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutAnimateItemModifierNode;
import androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyStaggeredGridItemPlacementAnimator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0004H\u0002JD\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u0004J\u0006\u0010(\u001a\u00020\u001cJ\u0010\u0010)\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000fH\u0002J!\u0010*\u001a\u00020\u001c*\u00020\u000f2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001c0,H\u0082\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u000bj\b\u0012\u0004\u0012\u00020\u0001`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0013\u001a\u00020\u0014*\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018*\u0004\u0018\u00010\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006-"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemPlacementAnimator;", "", "()V", "firstVisibleIndex", "", "keyIndexMap", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "keyToItemInfoMap", "", "Landroidx/compose/foundation/lazy/staggeredgrid/ItemInfo;", "movingAwayKeys", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "movingAwayToEndBound", "", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "movingAwayToStartBound", "movingInFromEndBound", "movingInFromStartBound", "hasAnimations", "", "getHasAnimations", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;)Z", "node", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateItemModifierNode;", "getNode", "(Ljava/lang/Object;)Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateItemModifierNode;", "initializeNode", "", "item", "mainAxisOffset", "onMeasured", "consumedScroll", "layoutWidth", "layoutHeight", "positionedItems", "itemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureProvider;", "isVertical", "laneCount", "reset", "startAnimationsIfNeeded", "forEachNode", "block", "Lkotlin/Function1;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridItemPlacementAnimator {
    private int firstVisibleIndex;
    private final Map<Object, ItemInfo> keyToItemInfoMap = new LinkedHashMap();
    private LazyLayoutKeyIndexMap keyIndexMap = LazyLayoutKeyIndexMap.INSTANCE;
    private final LinkedHashSet<Object> movingAwayKeys = new LinkedHashSet<>();
    private final List<LazyStaggeredGridMeasuredItem> movingInFromStartBound = new ArrayList();
    private final List<LazyStaggeredGridMeasuredItem> movingInFromEndBound = new ArrayList();
    private final List<LazyStaggeredGridMeasuredItem> movingAwayToStartBound = new ArrayList();
    private final List<LazyStaggeredGridMeasuredItem> movingAwayToEndBound = new ArrayList();

    public final void onMeasured(int consumedScroll, int layoutWidth, int layoutHeight, List<LazyStaggeredGridMeasuredItem> positionedItems, LazyStaggeredGridMeasureProvider itemProvider, boolean isVertical, int laneCount) {
        boolean z;
        long scrollOffset;
        final LazyLayoutKeyIndexMap previousKeyToIndexMap;
        List<LazyStaggeredGridMeasuredItem> list;
        int mainAxisLayoutSize;
        Iterable $this$forEach$iv;
        int $i$f$forEach;
        int previousFirstVisibleIndex;
        LazyLayoutKeyIndexMap previousKeyToIndexMap2;
        int mainAxisLayoutSize2;
        List<LazyStaggeredGridMeasuredItem> list2;
        int $i$f$fastForEach;
        int i;
        LazyStaggeredGridMeasuredItem $this$forEachNode$iv;
        LazyStaggeredGridItemPlacementAnimator this_$iv;
        LazyLayoutKeyIndexMap previousKeyToIndexMap3;
        int mainAxisLayoutSize3;
        Intrinsics.checkNotNullParameter(positionedItems, "positionedItems");
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        int index$iv$iv = 0;
        int size = positionedItems.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = positionedItems.get(index$iv$iv);
                LazyStaggeredGridMeasuredItem it = (LazyStaggeredGridMeasuredItem) item$iv$iv;
                if (getHasAnimations(it)) {
                    z = true;
                    break;
                }
                index$iv$iv++;
            } else {
                z = false;
                break;
            }
        }
        if (!z && this.keyToItemInfoMap.isEmpty()) {
            reset();
            return;
        }
        int previousFirstVisibleIndex2 = this.firstVisibleIndex;
        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem = (LazyStaggeredGridMeasuredItem) CollectionsKt.firstOrNull((List) positionedItems);
        this.firstVisibleIndex = lazyStaggeredGridMeasuredItem != null ? lazyStaggeredGridMeasuredItem.getIndex() : 0;
        LazyLayoutKeyIndexMap previousKeyToIndexMap4 = this.keyIndexMap;
        this.keyIndexMap = itemProvider.getKeyIndexMap();
        int mainAxisLayoutSize4 = isVertical ? layoutHeight : layoutWidth;
        if (isVertical) {
            scrollOffset = IntOffsetKt.IntOffset(0, consumedScroll);
        } else {
            scrollOffset = IntOffsetKt.IntOffset(consumedScroll, 0);
        }
        this.movingAwayKeys.addAll(this.keyToItemInfoMap.keySet());
        List<LazyStaggeredGridMeasuredItem> list3 = positionedItems;
        int $i$f$fastForEach2 = 0;
        int index$iv = 0;
        int size2 = list3.size();
        while (index$iv < size2) {
            Object item$iv = list3.get(index$iv);
            LazyStaggeredGridMeasuredItem item = (LazyStaggeredGridMeasuredItem) item$iv;
            this.movingAwayKeys.remove(item.getKey());
            if (getHasAnimations(item)) {
                ItemInfo itemInfo = this.keyToItemInfoMap.get(item.getKey());
                if (itemInfo == null) {
                    list2 = list3;
                    $i$f$fastForEach = $i$f$fastForEach2;
                    i = size2;
                    this.keyToItemInfoMap.put(item.getKey(), new ItemInfo(item.getLane(), item.getSpan(), item.getCrossAxisOffset()));
                    int previousIndex = previousKeyToIndexMap4.getIndex(item.getKey());
                    if (previousIndex != -1 && item.getIndex() != previousIndex) {
                        if (previousIndex < previousFirstVisibleIndex2) {
                            this.movingInFromStartBound.add(item);
                            previousFirstVisibleIndex = previousFirstVisibleIndex2;
                            previousKeyToIndexMap2 = previousKeyToIndexMap4;
                            mainAxisLayoutSize2 = mainAxisLayoutSize4;
                        } else {
                            this.movingInFromEndBound.add(item);
                            previousFirstVisibleIndex = previousFirstVisibleIndex2;
                            previousKeyToIndexMap2 = previousKeyToIndexMap4;
                            mainAxisLayoutSize2 = mainAxisLayoutSize4;
                        }
                    } else {
                        long it2 = item.getOffset();
                        initializeNode(item, item.getIsVertical() ? IntOffset.m5393getYimpl(it2) : IntOffset.m5392getXimpl(it2));
                        previousFirstVisibleIndex = previousFirstVisibleIndex2;
                        previousKeyToIndexMap2 = previousKeyToIndexMap4;
                        mainAxisLayoutSize2 = mainAxisLayoutSize4;
                    }
                } else {
                    list2 = list3;
                    $i$f$fastForEach = $i$f$fastForEach2;
                    i = size2;
                    LazyStaggeredGridMeasuredItem $this$forEachNode$iv2 = item;
                    LazyStaggeredGridItemPlacementAnimator this_$iv2 = this;
                    int placeablesCount = $this$forEachNode$iv2.getPlaceablesCount();
                    int i2 = 0;
                    while (i2 < placeablesCount) {
                        int index$iv2 = i2;
                        int previousFirstVisibleIndex3 = previousFirstVisibleIndex2;
                        LazyLayoutAnimateItemModifierNode it3 = this_$iv2.getNode($this$forEachNode$iv2.getParentData(index$iv2));
                        if (it3 != null) {
                            $this$forEachNode$iv = $this$forEachNode$iv2;
                            this_$iv = this_$iv2;
                            previousKeyToIndexMap3 = previousKeyToIndexMap4;
                            mainAxisLayoutSize3 = mainAxisLayoutSize4;
                            if (!IntOffset.m5391equalsimpl0(it3.getRawOffset(), LazyLayoutAnimateItemModifierNode.INSTANCE.m635getNotInitializednOccac())) {
                                long arg0$iv = it3.getRawOffset();
                                it3.m634setRawOffsetgyyYBs(IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(arg0$iv) + IntOffset.m5392getXimpl(scrollOffset), IntOffset.m5393getYimpl(arg0$iv) + IntOffset.m5393getYimpl(scrollOffset)));
                            }
                        } else {
                            $this$forEachNode$iv = $this$forEachNode$iv2;
                            this_$iv = this_$iv2;
                            previousKeyToIndexMap3 = previousKeyToIndexMap4;
                            mainAxisLayoutSize3 = mainAxisLayoutSize4;
                        }
                        i2++;
                        previousFirstVisibleIndex2 = previousFirstVisibleIndex3;
                        $this$forEachNode$iv2 = $this$forEachNode$iv;
                        this_$iv2 = this_$iv;
                        previousKeyToIndexMap4 = previousKeyToIndexMap3;
                        mainAxisLayoutSize4 = mainAxisLayoutSize3;
                    }
                    previousFirstVisibleIndex = previousFirstVisibleIndex2;
                    previousKeyToIndexMap2 = previousKeyToIndexMap4;
                    mainAxisLayoutSize2 = mainAxisLayoutSize4;
                    itemInfo.setLane(item.getLane());
                    itemInfo.setSpan(item.getSpan());
                    itemInfo.setCrossAxisOffset(item.getCrossAxisOffset());
                    startAnimationsIfNeeded(item);
                }
            } else {
                previousFirstVisibleIndex = previousFirstVisibleIndex2;
                previousKeyToIndexMap2 = previousKeyToIndexMap4;
                mainAxisLayoutSize2 = mainAxisLayoutSize4;
                list2 = list3;
                $i$f$fastForEach = $i$f$fastForEach2;
                i = size2;
                this.keyToItemInfoMap.remove(item.getKey());
            }
            index$iv++;
            list3 = list2;
            $i$f$fastForEach2 = $i$f$fastForEach;
            size2 = i;
            previousFirstVisibleIndex2 = previousFirstVisibleIndex;
            previousKeyToIndexMap4 = previousKeyToIndexMap2;
            mainAxisLayoutSize4 = mainAxisLayoutSize2;
        }
        LazyLayoutKeyIndexMap previousKeyToIndexMap5 = previousKeyToIndexMap4;
        int mainAxisLayoutSize5 = mainAxisLayoutSize4;
        int[] accumulatedOffsetPerLane = new int[laneCount];
        for (int i3 = 0; i3 < laneCount; i3++) {
            accumulatedOffsetPerLane[i3] = 0;
        }
        if (!(!this.movingInFromStartBound.isEmpty())) {
            previousKeyToIndexMap = previousKeyToIndexMap5;
        } else {
            List<LazyStaggeredGridMeasuredItem> list4 = this.movingInFromStartBound;
            if (list4.size() > 1) {
                previousKeyToIndexMap = previousKeyToIndexMap5;
                CollectionsKt.sortWith(list4, new Comparator() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemPlacementAnimator$onMeasured$$inlined$sortByDescending$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        LazyStaggeredGridMeasuredItem it4 = (LazyStaggeredGridMeasuredItem) t2;
                        LazyStaggeredGridMeasuredItem it5 = (LazyStaggeredGridMeasuredItem) t;
                        return ComparisonsKt.compareValues(Integer.valueOf(previousKeyToIndexMap.getIndex(it4.getKey())), Integer.valueOf(previousKeyToIndexMap.getIndex(it5.getKey())));
                    }
                });
            } else {
                previousKeyToIndexMap = previousKeyToIndexMap5;
            }
            List<LazyStaggeredGridMeasuredItem> list5 = this.movingInFromStartBound;
            int size3 = list5.size();
            for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
                Object item$iv2 = list5.get(index$iv3);
                LazyStaggeredGridMeasuredItem item2 = (LazyStaggeredGridMeasuredItem) item$iv2;
                int lane = item2.getLane();
                accumulatedOffsetPerLane[lane] = accumulatedOffsetPerLane[lane] + item2.getMainAxisSize();
                int mainAxisOffset = 0 - accumulatedOffsetPerLane[item2.getLane()];
                initializeNode(item2, mainAxisOffset);
                startAnimationsIfNeeded(item2);
            }
            ArraysKt.fill$default(accumulatedOffsetPerLane, 0, 0, 0, 6, (Object) null);
        }
        if (!this.movingInFromEndBound.isEmpty()) {
            List<LazyStaggeredGridMeasuredItem> list6 = this.movingInFromEndBound;
            if (list6.size() > 1) {
                CollectionsKt.sortWith(list6, new Comparator() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemPlacementAnimator$onMeasured$$inlined$sortBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        LazyStaggeredGridMeasuredItem it4 = (LazyStaggeredGridMeasuredItem) t;
                        LazyStaggeredGridMeasuredItem it5 = (LazyStaggeredGridMeasuredItem) t2;
                        return ComparisonsKt.compareValues(Integer.valueOf(previousKeyToIndexMap.getIndex(it4.getKey())), Integer.valueOf(previousKeyToIndexMap.getIndex(it5.getKey())));
                    }
                });
            }
            List<LazyStaggeredGridMeasuredItem> list7 = this.movingInFromEndBound;
            int size4 = list7.size();
            for (int index$iv4 = 0; index$iv4 < size4; index$iv4++) {
                Object item$iv3 = list7.get(index$iv4);
                LazyStaggeredGridMeasuredItem item3 = (LazyStaggeredGridMeasuredItem) item$iv3;
                int mainAxisOffset2 = mainAxisLayoutSize5 + accumulatedOffsetPerLane[item3.getLane()];
                int lane2 = item3.getLane();
                accumulatedOffsetPerLane[lane2] = accumulatedOffsetPerLane[lane2] + item3.getMainAxisSize();
                initializeNode(item3, mainAxisOffset2);
                startAnimationsIfNeeded(item3);
            }
            ArraysKt.fill$default(accumulatedOffsetPerLane, 0, 0, 0, 6, (Object) null);
        }
        Iterable $this$forEach$iv2 = this.movingAwayKeys;
        int $i$f$forEach2 = 0;
        for (Object element$iv : $this$forEach$iv2) {
            ItemInfo itemInfo2 = (ItemInfo) MapsKt.getValue(this.keyToItemInfoMap, element$iv);
            int newIndex = this.keyIndexMap.getIndex(element$iv);
            if (newIndex == -1) {
                this.keyToItemInfoMap.remove(element$iv);
                $this$forEach$iv = $this$forEach$iv2;
                $i$f$forEach = $i$f$forEach2;
            } else {
                LazyStaggeredGridMeasuredItem item4 = itemProvider.m683getAndMeasurejy6DScQ(newIndex, SpanRange.m688constructorimpl(itemInfo2.getLane(), itemInfo2.getSpan()));
                $this$forEach$iv = $this$forEach$iv2;
                int placeablesCount2 = item4.getPlaceablesCount();
                boolean inProgress = false;
                int i4 = 0;
                while (i4 < placeablesCount2) {
                    int it4 = i4;
                    int i5 = placeablesCount2;
                    int it5 = $i$f$forEach2;
                    LazyLayoutAnimateItemModifierNode node = getNode(item4.getParentData(it4));
                    int it6 = (node == null || !node.isAnimationInProgress()) ? 0 : 1;
                    if (it6 != 0) {
                        inProgress = true;
                    }
                    i4++;
                    $i$f$forEach2 = it5;
                    placeablesCount2 = i5;
                }
                $i$f$forEach = $i$f$forEach2;
                if (!inProgress && newIndex == previousKeyToIndexMap.getIndex(element$iv)) {
                    this.keyToItemInfoMap.remove(element$iv);
                } else if (newIndex < this.firstVisibleIndex) {
                    this.movingAwayToStartBound.add(item4);
                } else {
                    this.movingAwayToEndBound.add(item4);
                }
            }
            $this$forEach$iv2 = $this$forEach$iv;
            $i$f$forEach2 = $i$f$forEach;
        }
        if (!(!((Collection) this.movingAwayToStartBound).isEmpty())) {
            list = positionedItems;
            mainAxisLayoutSize = mainAxisLayoutSize5;
        } else {
            List<LazyStaggeredGridMeasuredItem> list8 = this.movingAwayToStartBound;
            if (list8.size() > 1) {
                CollectionsKt.sortWith(list8, new Comparator() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemPlacementAnimator$onMeasured$$inlined$sortByDescending$2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        LazyStaggeredGridMeasuredItem it7 = (LazyStaggeredGridMeasuredItem) t2;
                        LazyStaggeredGridMeasuredItem it8 = (LazyStaggeredGridMeasuredItem) t;
                        return ComparisonsKt.compareValues(Integer.valueOf(this.this$0.keyIndexMap.getIndex(it7.getKey())), Integer.valueOf(this.this$0.keyIndexMap.getIndex(it8.getKey())));
                    }
                });
            }
            List<LazyStaggeredGridMeasuredItem> list9 = this.movingAwayToStartBound;
            int index$iv5 = 0;
            int size5 = list9.size();
            while (index$iv5 < size5) {
                Object item$iv4 = list9.get(index$iv5);
                LazyStaggeredGridMeasuredItem item5 = (LazyStaggeredGridMeasuredItem) item$iv4;
                int lane3 = item5.getLane();
                accumulatedOffsetPerLane[lane3] = accumulatedOffsetPerLane[lane3] + item5.getMainAxisSize();
                int mainAxisOffset3 = 0 - accumulatedOffsetPerLane[item5.getLane()];
                List<LazyStaggeredGridMeasuredItem> list10 = list9;
                ItemInfo itemInfo3 = (ItemInfo) MapsKt.getValue(this.keyToItemInfoMap, item5.getKey());
                item5.position(mainAxisOffset3, itemInfo3.getCrossAxisOffset(), mainAxisLayoutSize5);
                positionedItems.add(item5);
                startAnimationsIfNeeded(item5);
                index$iv5++;
                list9 = list10;
            }
            mainAxisLayoutSize = mainAxisLayoutSize5;
            list = positionedItems;
            ArraysKt.fill$default(accumulatedOffsetPerLane, 0, 0, 0, 6, (Object) null);
        }
        if (!this.movingAwayToEndBound.isEmpty()) {
            List<LazyStaggeredGridMeasuredItem> list11 = this.movingAwayToEndBound;
            if (list11.size() > 1) {
                CollectionsKt.sortWith(list11, new Comparator() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemPlacementAnimator$onMeasured$$inlined$sortBy$2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        LazyStaggeredGridMeasuredItem it7 = (LazyStaggeredGridMeasuredItem) t;
                        LazyStaggeredGridMeasuredItem it8 = (LazyStaggeredGridMeasuredItem) t2;
                        return ComparisonsKt.compareValues(Integer.valueOf(this.this$0.keyIndexMap.getIndex(it7.getKey())), Integer.valueOf(this.this$0.keyIndexMap.getIndex(it8.getKey())));
                    }
                });
            }
            List<LazyStaggeredGridMeasuredItem> list12 = this.movingAwayToEndBound;
            int index$iv6 = 0;
            int size6 = list12.size();
            while (index$iv6 < size6) {
                Object item$iv5 = list12.get(index$iv6);
                LazyStaggeredGridMeasuredItem item6 = (LazyStaggeredGridMeasuredItem) item$iv5;
                int mainAxisOffset4 = accumulatedOffsetPerLane[item6.getLane()] + mainAxisLayoutSize;
                int lane4 = item6.getLane();
                accumulatedOffsetPerLane[lane4] = accumulatedOffsetPerLane[lane4] + item6.getMainAxisSize();
                int[] accumulatedOffsetPerLane2 = accumulatedOffsetPerLane;
                ItemInfo itemInfo4 = (ItemInfo) MapsKt.getValue(this.keyToItemInfoMap, item6.getKey());
                item6.position(mainAxisOffset4, itemInfo4.getCrossAxisOffset(), mainAxisLayoutSize);
                list.add(item6);
                startAnimationsIfNeeded(item6);
                index$iv6++;
                accumulatedOffsetPerLane = accumulatedOffsetPerLane2;
            }
        }
        this.movingInFromStartBound.clear();
        this.movingInFromEndBound.clear();
        this.movingAwayToStartBound.clear();
        this.movingAwayToEndBound.clear();
        this.movingAwayKeys.clear();
    }

    public final void reset() {
        this.keyToItemInfoMap.clear();
        this.keyIndexMap = LazyLayoutKeyIndexMap.INSTANCE;
        this.firstVisibleIndex = -1;
    }

    private final void initializeNode(LazyStaggeredGridMeasuredItem item, int mainAxisOffset) {
        long targetFirstPlaceableOffset;
        long firstPlaceableOffset = item.getOffset();
        if (item.getIsVertical()) {
            targetFirstPlaceableOffset = IntOffset.m5388copyiSbpLlY$default(firstPlaceableOffset, 0, mainAxisOffset, 1, null);
        } else {
            targetFirstPlaceableOffset = IntOffset.m5388copyiSbpLlY$default(firstPlaceableOffset, mainAxisOffset, 0, 2, null);
        }
        LazyStaggeredGridMeasuredItem $this$forEachNode$iv = item;
        LazyStaggeredGridItemPlacementAnimator this_$iv = this;
        int placeablesCount = $this$forEachNode$iv.getPlaceablesCount();
        int i = 0;
        while (i < placeablesCount) {
            int index$iv = i;
            LazyLayoutAnimateItemModifierNode node = this_$iv.getNode($this$forEachNode$iv.getParentData(index$iv));
            if (node != null) {
                long arg0$iv = item.getOffset();
                long diffToFirstPlaceableOffset = IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(arg0$iv) - IntOffset.m5392getXimpl(firstPlaceableOffset), IntOffset.m5393getYimpl(arg0$iv) - IntOffset.m5393getYimpl(firstPlaceableOffset));
                node.m634setRawOffsetgyyYBs(IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(targetFirstPlaceableOffset) + IntOffset.m5392getXimpl(diffToFirstPlaceableOffset), IntOffset.m5393getYimpl(targetFirstPlaceableOffset) + IntOffset.m5393getYimpl(diffToFirstPlaceableOffset)));
            }
            i++;
            $this$forEachNode$iv = $this$forEachNode$iv;
            this_$iv = this_$iv;
        }
    }

    private final void startAnimationsIfNeeded(LazyStaggeredGridMeasuredItem item) {
        int placeablesCount = item.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            int index$iv = i;
            LazyLayoutAnimateItemModifierNode node = getNode(item.getParentData(index$iv));
            if (node != null) {
                long newTarget = item.getOffset();
                long currentTarget = node.getRawOffset();
                if (!IntOffset.m5391equalsimpl0(currentTarget, LazyLayoutAnimateItemModifierNode.INSTANCE.m635getNotInitializednOccac()) && !IntOffset.m5391equalsimpl0(currentTarget, newTarget)) {
                    node.m631animatePlacementDeltagyyYBs(IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(newTarget) - IntOffset.m5392getXimpl(currentTarget), IntOffset.m5393getYimpl(newTarget) - IntOffset.m5393getYimpl(currentTarget)));
                }
                node.m634setRawOffsetgyyYBs(newTarget);
            }
        }
    }

    private final LazyLayoutAnimateItemModifierNode getNode(Object $this$node) {
        if ($this$node instanceof LazyLayoutAnimateItemModifierNode) {
            return (LazyLayoutAnimateItemModifierNode) $this$node;
        }
        return null;
    }

    private final boolean getHasAnimations(LazyStaggeredGridMeasuredItem $this$hasAnimations) {
        int placeablesCount = $this$hasAnimations.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            int index$iv = i;
            if (getNode($this$hasAnimations.getParentData(index$iv)) != null) {
                return true;
            }
        }
        return false;
    }

    private final void forEachNode(LazyStaggeredGridMeasuredItem $this$forEachNode, Function1<? super LazyLayoutAnimateItemModifierNode, Unit> function1) {
        int placeablesCount = $this$forEachNode.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            int index = i;
            LazyLayoutAnimateItemModifierNode node = getNode($this$forEachNode.getParentData(index));
            if (node != null) {
                function1.invoke(node);
            }
        }
    }
}
