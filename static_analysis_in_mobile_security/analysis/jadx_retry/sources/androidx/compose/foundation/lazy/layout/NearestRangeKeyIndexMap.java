package androidx.compose.foundation.lazy.layout;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: LazyLayoutKeyIndexMap.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\tH\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\fH\u0016R\u0018\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/compose/foundation/lazy/layout/NearestRangeKeyIndexMap;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "nearestRange", "Lkotlin/ranges/IntRange;", "intervalContent", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;", "(Lkotlin/ranges/IntRange;Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;)V", "keys", "", "", "[Ljava/lang/Object;", "keysStartIndex", "", "map", "", "getIndex", "key", "getKey", "index", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NearestRangeKeyIndexMap implements LazyLayoutKeyIndexMap {
    private final Object[] keys;
    private final int keysStartIndex;
    private final Map<Object, Integer> map;

    public NearestRangeKeyIndexMap(IntRange nearestRange, LazyLayoutIntervalContent<?> intervalContent) {
        Intrinsics.checkNotNullParameter(nearestRange, "nearestRange");
        Intrinsics.checkNotNullParameter(intervalContent, "intervalContent");
        IntervalList list = intervalContent.getIntervals();
        final int first = nearestRange.getFirst();
        if (first >= 0) {
            final int last = Math.min(nearestRange.getLast(), list.getSize() - 1);
            if (last >= first) {
                this.keys = new Object[(last - first) + 1];
                this.keysStartIndex = first;
                final HashMap map = new HashMap();
                list.forEach(first, last, new Function1<IntervalList.Interval<? extends LazyLayoutIntervalContent.Interval>, Unit>() { // from class: androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(IntervalList.Interval<? extends LazyLayoutIntervalContent.Interval> interval) {
                        invoke2(interval);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Code duplicated, block: B:7:0x0040  */
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IntervalList.Interval<? extends LazyLayoutIntervalContent.Interval> it) {
                        Object key;
                        Intrinsics.checkNotNullParameter(it, "it");
                        Function1<Integer, Object> key2 = it.getValue().getKey();
                        int start = Math.max(first, it.getStartIndex());
                        int end = Math.min(last, (it.getStartIndex() + it.getSize()) - 1);
                        int i = start;
                        if (i > end) {
                            return;
                        }
                        while (true) {
                            if (key2 == null) {
                                key = Lazy_androidKt.getDefaultLazyLayoutKey(i);
                            } else {
                                key = key2.invoke(Integer.valueOf(i - it.getStartIndex()));
                                if (key == null) {
                                    key = Lazy_androidKt.getDefaultLazyLayoutKey(i);
                                }
                            }
                            map.put(key, Integer.valueOf(i));
                            this.keys[i - this.keysStartIndex] = key;
                            if (i == end) {
                                return;
                            } else {
                                i++;
                            }
                        }
                    }
                });
                this.map = map;
                return;
            }
            this.map = MapsKt.emptyMap();
            this.keys = new Object[0];
            this.keysStartIndex = 0;
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap
    public int getIndex(Object key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Integer num = this.map.get(key);
        if (num == null) {
            num = -1;
        }
        return num.intValue();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap
    public Object getKey(int index) {
        Object[] objArr = this.keys;
        int i = index - this.keysStartIndex;
        if (i < 0 || i > ArraysKt.getLastIndex(objArr)) {
            return null;
        }
        return objArr[i];
    }
}
