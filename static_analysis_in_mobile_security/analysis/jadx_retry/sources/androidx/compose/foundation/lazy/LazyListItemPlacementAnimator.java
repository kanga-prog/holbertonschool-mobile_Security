package androidx.compose.foundation.lazy;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimateItemModifierNode;
import androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyListItemPlacementAnimator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J<\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u00062\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0013J\u0006\u0010&\u001a\u00020\u001bJ\u0010\u0010'\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000eH\u0002JE\u0010(\u001a\u00020\u001b*\u00020\u000e26\u0010)\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u001b0*H\u0082\bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00010\nj\b\u0012\u0004\u0012\u00020\u0001`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0012\u001a\u00020\u0013*\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u0004\u0018\u00010\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006."}, d2 = {"Landroidx/compose/foundation/lazy/LazyListItemPlacementAnimator;", "", "()V", "activeKeys", "", "firstVisibleIndex", "", "keyIndexMap", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "movingAwayKeys", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "movingAwayToEndBound", "", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "movingAwayToStartBound", "movingInFromEndBound", "movingInFromStartBound", "hasAnimations", "", "getHasAnimations", "(Landroidx/compose/foundation/lazy/LazyListMeasuredItem;)Z", "node", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateItemModifierNode;", "getNode", "(Ljava/lang/Object;)Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateItemModifierNode;", "initializeNode", "", "item", "mainAxisOffset", "onMeasured", "consumedScroll", "layoutWidth", "layoutHeight", "positionedItems", "itemProvider", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "isVertical", "reset", "startAnimationsIfNeeded", "forEachNode", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "placeableIndex", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyListItemPlacementAnimator {
    private int firstVisibleIndex;
    private final Set<Object> activeKeys = new LinkedHashSet();
    private LazyLayoutKeyIndexMap keyIndexMap = LazyLayoutKeyIndexMap.INSTANCE;
    private final LinkedHashSet<Object> movingAwayKeys = new LinkedHashSet<>();
    private final List<LazyListMeasuredItem> movingInFromStartBound = new ArrayList();
    private final List<LazyListMeasuredItem> movingInFromEndBound = new ArrayList();
    private final List<LazyListMeasuredItem> movingAwayToStartBound = new ArrayList();
    private final List<LazyListMeasuredItem> movingAwayToEndBound = new ArrayList();

    public final void onMeasured(int consumedScroll, int layoutWidth, int layoutHeight, List<LazyListMeasuredItem> positionedItems, LazyListMeasuredItemProvider itemProvider, boolean isVertical) {
        boolean z;
        long scrollOffset;
        int accumulatedOffset;
        Iterable $this$forEach$iv;
        int $i$f$forEach;
        int i;
        LazyListMeasuredItem $this$forEachNode$iv;
        Intrinsics.checkNotNullParameter(positionedItems, "positionedItems");
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        int index$iv$iv = 0;
        int size = positionedItems.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = positionedItems.get(index$iv$iv);
                LazyListMeasuredItem it = (LazyListMeasuredItem) item$iv$iv;
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
        if (!z && this.activeKeys.isEmpty()) {
            reset();
            return;
        }
        int previousFirstVisibleIndex = this.firstVisibleIndex;
        LazyListMeasuredItem lazyListMeasuredItem = (LazyListMeasuredItem) CollectionsKt.firstOrNull((List) positionedItems);
        this.firstVisibleIndex = lazyListMeasuredItem != null ? lazyListMeasuredItem.getIndex() : 0;
        final LazyLayoutKeyIndexMap previousKeyToIndexMap = this.keyIndexMap;
        this.keyIndexMap = itemProvider.getKeyIndexMap();
        int mainAxisLayoutSize = isVertical ? layoutHeight : layoutWidth;
        if (isVertical) {
            scrollOffset = IntOffsetKt.IntOffset(0, consumedScroll);
        } else {
            scrollOffset = IntOffsetKt.IntOffset(consumedScroll, 0);
        }
        this.movingAwayKeys.addAll(this.activeKeys);
        List<LazyListMeasuredItem> list = positionedItems;
        int $i$f$fastForEach = 0;
        int index$iv = 0;
        int size2 = list.size();
        while (index$iv < size2) {
            Object item$iv = list.get(index$iv);
            LazyListMeasuredItem item = (LazyListMeasuredItem) item$iv;
            List<LazyListMeasuredItem> list2 = list;
            this.movingAwayKeys.remove(item.getKey());
            if (!getHasAnimations(item)) {
                i = size2;
                this.activeKeys.remove(item.getKey());
            } else if (!this.activeKeys.contains(item.getKey())) {
                this.activeKeys.add(item.getKey());
                int previousIndex = previousKeyToIndexMap.getIndex(item.getKey());
                if (previousIndex != -1 && item.getIndex() != previousIndex) {
                    if (previousIndex < previousFirstVisibleIndex) {
                        this.movingInFromStartBound.add(item);
                        i = size2;
                    } else {
                        this.movingInFromEndBound.add(item);
                        i = size2;
                    }
                } else {
                    long it2 = item.m597getOffsetBjo55l4(0);
                    initializeNode(item, item.getIsVertical() ? IntOffset.m5393getYimpl(it2) : IntOffset.m5392getXimpl(it2));
                    i = size2;
                }
            } else {
                LazyListMeasuredItem $this$forEachNode$iv2 = item;
                int previousFirstVisibleIndex2 = $this$forEachNode$iv2.getPlaceablesCount();
                int $i$f$fastForEach2 = 0;
                while ($i$f$fastForEach2 < previousFirstVisibleIndex2) {
                    int index$iv2 = $i$f$fastForEach2;
                    int i2 = previousFirstVisibleIndex2;
                    int index$iv3 = size2;
                    LazyLayoutAnimateItemModifierNode it$iv = getNode($this$forEachNode$iv2.getParentData(index$iv2));
                    if (it$iv != null) {
                        $this$forEachNode$iv = $this$forEachNode$iv2;
                        if (!IntOffset.m5391equalsimpl0(it$iv.getRawOffset(), LazyLayoutAnimateItemModifierNode.INSTANCE.m635getNotInitializednOccac())) {
                            long arg0$iv = it$iv.getRawOffset();
                            it$iv.m634setRawOffsetgyyYBs(IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(arg0$iv) + IntOffset.m5392getXimpl(scrollOffset), IntOffset.m5393getYimpl(arg0$iv) + IntOffset.m5393getYimpl(scrollOffset)));
                        }
                    } else {
                        $this$forEachNode$iv = $this$forEachNode$iv2;
                    }
                    $i$f$fastForEach2++;
                    size2 = index$iv3;
                    previousFirstVisibleIndex2 = i2;
                    $this$forEachNode$iv2 = $this$forEachNode$iv;
                }
                i = size2;
                startAnimationsIfNeeded(item);
            }
            index$iv++;
            list = list2;
            previousFirstVisibleIndex = previousFirstVisibleIndex;
            $i$f$fastForEach = $i$f$fastForEach;
            size2 = i;
        }
        int accumulatedOffset2 = 0;
        List<LazyListMeasuredItem> list3 = this.movingInFromStartBound;
        if (list3.size() > 1) {
            CollectionsKt.sortWith(list3, new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemPlacementAnimator$onMeasured$$inlined$sortByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    LazyListMeasuredItem it3 = (LazyListMeasuredItem) t2;
                    LazyListMeasuredItem it4 = (LazyListMeasuredItem) t;
                    return ComparisonsKt.compareValues(Integer.valueOf(previousKeyToIndexMap.getIndex(it3.getKey())), Integer.valueOf(previousKeyToIndexMap.getIndex(it4.getKey())));
                }
            });
        }
        List<LazyListMeasuredItem> list4 = this.movingInFromStartBound;
        int size3 = list4.size();
        for (int index$iv4 = 0; index$iv4 < size3; index$iv4++) {
            Object item$iv2 = list4.get(index$iv4);
            LazyListMeasuredItem item2 = (LazyListMeasuredItem) item$iv2;
            accumulatedOffset2 += item2.getSize();
            int mainAxisOffset = 0 - accumulatedOffset2;
            initializeNode(item2, mainAxisOffset);
            startAnimationsIfNeeded(item2);
        }
        int accumulatedOffset3 = 0;
        List<LazyListMeasuredItem> list5 = this.movingInFromEndBound;
        if (list5.size() > 1) {
            CollectionsKt.sortWith(list5, new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemPlacementAnimator$onMeasured$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    LazyListMeasuredItem it3 = (LazyListMeasuredItem) t;
                    LazyListMeasuredItem it4 = (LazyListMeasuredItem) t2;
                    return ComparisonsKt.compareValues(Integer.valueOf(previousKeyToIndexMap.getIndex(it3.getKey())), Integer.valueOf(previousKeyToIndexMap.getIndex(it4.getKey())));
                }
            });
        }
        List<LazyListMeasuredItem> list6 = this.movingInFromEndBound;
        int size4 = list6.size();
        for (int index$iv5 = 0; index$iv5 < size4; index$iv5++) {
            Object item$iv3 = list6.get(index$iv5);
            LazyListMeasuredItem item3 = (LazyListMeasuredItem) item$iv3;
            int mainAxisOffset2 = mainAxisLayoutSize + accumulatedOffset3;
            accumulatedOffset3 += item3.getSize();
            initializeNode(item3, mainAxisOffset2);
            startAnimationsIfNeeded(item3);
        }
        Iterable $this$forEach$iv2 = this.movingAwayKeys;
        int $i$f$forEach2 = 0;
        for (Object element$iv : $this$forEach$iv2) {
            int newIndex = this.keyIndexMap.getIndex(element$iv);
            if (newIndex == -1) {
                this.activeKeys.remove(element$iv);
                accumulatedOffset = accumulatedOffset3;
                $this$forEach$iv = $this$forEach$iv2;
                $i$f$forEach = $i$f$forEach2;
            } else {
                LazyListMeasuredItem item4 = itemProvider.getAndMeasure(newIndex);
                boolean inProgress = false;
                int placeablesCount = item4.getPlaceablesCount();
                accumulatedOffset = accumulatedOffset3;
                int accumulatedOffset4 = 0;
                while (accumulatedOffset4 < placeablesCount) {
                    int it3 = accumulatedOffset4;
                    Iterable $this$forEach$iv3 = $this$forEach$iv2;
                    int it4 = $i$f$forEach2;
                    LazyLayoutAnimateItemModifierNode node = getNode(item4.getParentData(it3));
                    int it5 = (node == null || !node.isAnimationInProgress()) ? 0 : 1;
                    if (it5 != 0) {
                        inProgress = true;
                    }
                    accumulatedOffset4++;
                    $i$f$forEach2 = it4;
                    $this$forEach$iv2 = $this$forEach$iv3;
                }
                $this$forEach$iv = $this$forEach$iv2;
                $i$f$forEach = $i$f$forEach2;
                if (!inProgress && newIndex == previousKeyToIndexMap.getIndex(element$iv)) {
                    this.activeKeys.remove(element$iv);
                } else if (newIndex < this.firstVisibleIndex) {
                    this.movingAwayToStartBound.add(item4);
                } else {
                    this.movingAwayToEndBound.add(item4);
                }
            }
            accumulatedOffset3 = accumulatedOffset;
            $i$f$forEach2 = $i$f$forEach;
            $this$forEach$iv2 = $this$forEach$iv;
        }
        int accumulatedOffset5 = 0;
        List<LazyListMeasuredItem> list7 = this.movingAwayToStartBound;
        if (list7.size() > 1) {
            CollectionsKt.sortWith(list7, new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemPlacementAnimator$onMeasured$$inlined$sortByDescending$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    LazyListMeasuredItem it6 = (LazyListMeasuredItem) t2;
                    LazyListMeasuredItem it7 = (LazyListMeasuredItem) t;
                    return ComparisonsKt.compareValues(Integer.valueOf(this.this$0.keyIndexMap.getIndex(it6.getKey())), Integer.valueOf(this.this$0.keyIndexMap.getIndex(it7.getKey())));
                }
            });
        }
        List<LazyListMeasuredItem> list8 = this.movingAwayToStartBound;
        int index$iv6 = 0;
        int size5 = list8.size();
        while (index$iv6 < size5) {
            Object item$iv4 = list8.get(index$iv6);
            LazyListMeasuredItem item5 = (LazyListMeasuredItem) item$iv4;
            int accumulatedOffset6 = accumulatedOffset5 + item5.getSize();
            int mainAxisOffset3 = 0 - accumulatedOffset6;
            item5.position(mainAxisOffset3, layoutWidth, layoutHeight);
            positionedItems.add(item5);
            startAnimationsIfNeeded(item5);
            index$iv6++;
            accumulatedOffset5 = accumulatedOffset6;
            list8 = list8;
        }
        int accumulatedOffset7 = 0;
        List<LazyListMeasuredItem> list9 = this.movingAwayToEndBound;
        if (list9.size() > 1) {
            CollectionsKt.sortWith(list9, new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemPlacementAnimator$onMeasured$$inlined$sortBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    LazyListMeasuredItem it6 = (LazyListMeasuredItem) t;
                    LazyListMeasuredItem it7 = (LazyListMeasuredItem) t2;
                    return ComparisonsKt.compareValues(Integer.valueOf(this.this$0.keyIndexMap.getIndex(it6.getKey())), Integer.valueOf(this.this$0.keyIndexMap.getIndex(it7.getKey())));
                }
            });
        }
        List<LazyListMeasuredItem> list10 = this.movingAwayToEndBound;
        int index$iv7 = 0;
        int size6 = list10.size();
        while (index$iv7 < size6) {
            Object item$iv5 = list10.get(index$iv7);
            LazyListMeasuredItem item6 = (LazyListMeasuredItem) item$iv5;
            List<LazyListMeasuredItem> list11 = list10;
            int mainAxisOffset4 = mainAxisLayoutSize + accumulatedOffset7;
            accumulatedOffset7 += item6.getSize();
            item6.position(mainAxisOffset4, layoutWidth, layoutHeight);
            positionedItems.add(item6);
            startAnimationsIfNeeded(item6);
            index$iv7++;
            list10 = list11;
        }
        this.movingInFromStartBound.clear();
        this.movingInFromEndBound.clear();
        this.movingAwayToStartBound.clear();
        this.movingAwayToEndBound.clear();
        this.movingAwayKeys.clear();
    }

    public final void reset() {
        this.activeKeys.clear();
        this.keyIndexMap = LazyLayoutKeyIndexMap.INSTANCE;
        this.firstVisibleIndex = -1;
    }

    private final void initializeNode(LazyListMeasuredItem item, int mainAxisOffset) {
        long targetFirstPlaceableOffset;
        LazyListMeasuredItem lazyListMeasuredItem = item;
        int i = 0;
        long firstPlaceableOffset = lazyListMeasuredItem.m597getOffsetBjo55l4(0);
        if (item.getIsVertical()) {
            targetFirstPlaceableOffset = IntOffset.m5388copyiSbpLlY$default(firstPlaceableOffset, 0, mainAxisOffset, 1, null);
        } else {
            targetFirstPlaceableOffset = IntOffset.m5388copyiSbpLlY$default(firstPlaceableOffset, mainAxisOffset, 0, 2, null);
        }
        LazyListMeasuredItem $this$forEachNode$iv = item;
        int placeablesCount = $this$forEachNode$iv.getPlaceablesCount();
        while (i < placeablesCount) {
            int index$iv = i;
            LazyLayoutAnimateItemModifierNode it$iv = getNode($this$forEachNode$iv.getParentData(index$iv));
            if (it$iv != null) {
                long arg0$iv = lazyListMeasuredItem.m597getOffsetBjo55l4(index$iv);
                long arg0$iv2 = IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(arg0$iv) - IntOffset.m5392getXimpl(firstPlaceableOffset), IntOffset.m5393getYimpl(arg0$iv) - IntOffset.m5393getYimpl(firstPlaceableOffset));
                it$iv.m634setRawOffsetgyyYBs(IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(targetFirstPlaceableOffset) + IntOffset.m5392getXimpl(arg0$iv2), IntOffset.m5393getYimpl(targetFirstPlaceableOffset) + IntOffset.m5393getYimpl(arg0$iv2)));
            }
            i++;
            lazyListMeasuredItem = item;
            targetFirstPlaceableOffset = targetFirstPlaceableOffset;
            $this$forEachNode$iv = $this$forEachNode$iv;
        }
    }

    private final void startAnimationsIfNeeded(LazyListMeasuredItem item) {
        LazyListMeasuredItem $this$forEachNode$iv = item;
        LazyListItemPlacementAnimator this_$iv = this;
        int $i$f$forEachNode = 0;
        int i = 0;
        for (int placeablesCount = $this$forEachNode$iv.getPlaceablesCount(); i < placeablesCount; placeablesCount = placeablesCount) {
            int index$iv = i;
            LazyLayoutAnimateItemModifierNode it$iv = this_$iv.getNode($this$forEachNode$iv.getParentData(index$iv));
            if (it$iv != null) {
                long newTarget = item.m597getOffsetBjo55l4(index$iv);
                long currentTarget = it$iv.getRawOffset();
                if (!IntOffset.m5391equalsimpl0(currentTarget, LazyLayoutAnimateItemModifierNode.INSTANCE.m635getNotInitializednOccac()) && !IntOffset.m5391equalsimpl0(currentTarget, newTarget)) {
                    it$iv.m631animatePlacementDeltagyyYBs(IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(newTarget) - IntOffset.m5392getXimpl(currentTarget), IntOffset.m5393getYimpl(newTarget) - IntOffset.m5393getYimpl(currentTarget)));
                }
                it$iv.m634setRawOffsetgyyYBs(newTarget);
            }
            i++;
            this_$iv = this_$iv;
            $this$forEachNode$iv = $this$forEachNode$iv;
            $i$f$forEachNode = $i$f$forEachNode;
        }
    }

    private final LazyLayoutAnimateItemModifierNode getNode(Object $this$node) {
        if ($this$node instanceof LazyLayoutAnimateItemModifierNode) {
            return (LazyLayoutAnimateItemModifierNode) $this$node;
        }
        return null;
    }

    private final boolean getHasAnimations(LazyListMeasuredItem $this$hasAnimations) {
        int placeablesCount = $this$hasAnimations.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            int index$iv = i;
            if (getNode($this$hasAnimations.getParentData(index$iv)) != null) {
                return true;
            }
        }
        return false;
    }

    private final void forEachNode(LazyListMeasuredItem $this$forEachNode, Function2<? super Integer, ? super LazyLayoutAnimateItemModifierNode, Unit> function2) {
        int placeablesCount = $this$forEachNode.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            int index = i;
            LazyLayoutAnimateItemModifierNode it = getNode($this$forEachNode.getParentData(index));
            if (it != null) {
                function2.invoke(Integer.valueOf(index), it);
            }
        }
    }
}
