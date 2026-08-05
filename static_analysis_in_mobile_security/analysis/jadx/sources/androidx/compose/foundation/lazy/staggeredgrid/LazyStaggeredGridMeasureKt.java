package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LazyStaggeredGridMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u0017\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0082\b\u001a5\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\n0\u000e¢\u0006\u0002\b\u000fH\u0083\b¢\u0006\u0002\u0010\u0010\u001aJ\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u000e2!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u000eH\u0083\b\u001a;\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u00142\u0012\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0003H\u0002¢\u0006\u0002\u0010!\u001a\u001d\u0010\"\u001a\u00020\b*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u001d0\u001cH\u0002¢\u0006\u0002\u0010#\u001a\u001c\u0010$\u001a\u00020\u0005*\u00020\u00142\u0006\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u0003H\u0002\u001a\u001c\u0010'\u001a\u00020\u0003*\u00020\u00142\u0006\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u0003H\u0002\u001a.\u0010*\u001a\u00020\u0005*\u00020+2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u000eH\u0082\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-\u001a\f\u0010.\u001a\u00020\u0003*\u00020\u001fH\u0002\u001a2\u0010/\u001a\u00020\u0003\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u001c2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00030\u000eH\u0082\b¢\u0006\u0002\u00100\u001a\u0016\u00101\u001a\u00020\u0003*\u00020\u001f2\b\b\u0002\u00102\u001a\u00020\u0003H\u0000\u001a!\u00103\u001a\u00020\u0003*\u00020\u001f2\u0006\u00104\u001a\u00020+H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b5\u00106\u001a,\u00107\u001a\u000208*\u00020\u00142\u0006\u00109\u001a\u00020\u00032\u0006\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020\u001f2\u0006\u0010<\u001a\u00020\u0001H\u0003\u001a\u007f\u0010=\u001a\u000208*\u00020\f2\u0006\u0010>\u001a\u00020?2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u00012\u0006\u0010H\u001a\u00020\u00012\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u00032\u0006\u0010L\u001a\u00020\u00032\u0006\u0010M\u001a\u00020\u00032\u0006\u0010N\u001a\u00020\u0003H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bO\u0010P\u001a\u0014\u0010Q\u001a\u00020\u0005*\u00020\u001f2\u0006\u0010R\u001a\u00020\u0003H\u0002\u001a!\u0010S\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eH\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006T"}, d2 = {"DebugLoggingEnabled", "", "Unset", "", "debugLog", "", "message", "Lkotlin/Function0;", "", "withDebugLogging", "T", "scope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "calculateExtraItems", "", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;", "position", "filter", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "itemIndex", "calculateVisibleItems", "measuredItems", "", "Lkotlin/collections/ArrayDeque;", "itemScrollOffsets", "", "mainAxisLayoutSize", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;[Lkotlin/collections/ArrayDeque;[II)Ljava/util/List;", "debugRender", "([Lkotlin/collections/ArrayDeque;)Ljava/lang/String;", "ensureIndicesInRange", "indices", "itemCount", "findPreviousItemIndex", "item", "lane", "forEach", "Landroidx/compose/foundation/lazy/staggeredgrid/SpanRange;", "forEach-nIS5qE8", "(JLkotlin/jvm/functions/Function1;)V", "indexOfMaxValue", "indexOfMinBy", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "indexOfMinValue", "minBound", "maxInRange", "indexRange", "maxInRange-jy6DScQ", "([IJ)I", "measure", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "initialScrollDelta", "initialItemIndices", "initialItemOffsets", "canRestartMeasure", "measureStaggeredGrid", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "pinnedItems", "itemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "resolvedSlots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "reverseLayout", "contentOffset", "Landroidx/compose/ui/unit/IntOffset;", "mainAxisAvailableSize", "mainAxisSpacing", "beforeContentPadding", "afterContentPadding", "measureStaggeredGrid-dSVRQoE", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Ljava/util/List;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;JZZJIIII)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "offsetBy", "delta", "transform", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridMeasureKt {
    private static final boolean DebugLoggingEnabled = false;
    private static final int Unset = Integer.MIN_VALUE;

    private static final <T> T withDebugLogging(LazyLayoutMeasureScope scope, Function1<? super LazyLayoutMeasureScope, ? extends T> function1) {
        return function1.invoke(scope);
    }

    private static final String debugRender(ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr) {
        return "";
    }

    private static final void debugLog(Function0<String> function0) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22, types: [int[]] */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX INFO: renamed from: measureStaggeredGrid-dSVRQoE, reason: not valid java name */
    public static final LazyStaggeredGridMeasureResult m679measureStaggeredGriddSVRQoE(LazyLayoutMeasureScope measureStaggeredGrid, LazyStaggeredGridState state, List<Integer> pinnedItems, LazyStaggeredGridItemProvider itemProvider, LazyStaggeredGridSlots resolvedSlots, long j, boolean z, boolean z2, long j2, int i, int i2, int i3, int i4) {
        int i5;
        int iM678maxInRangejy6DScQ;
        T t;
        T t2;
        char c;
        Intrinsics.checkNotNullParameter(measureStaggeredGrid, "$this$measureStaggeredGrid");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(pinnedItems, "pinnedItems");
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        Intrinsics.checkNotNullParameter(resolvedSlots, "resolvedSlots");
        LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext = new LazyStaggeredGridMeasureContext(state, pinnedItems, itemProvider, resolvedSlots, j, z, measureStaggeredGrid, i, j2, i3, i4, z2, i2, null);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Snapshot.Companion companion = Snapshot.INSTANCE;
        int i6 = 0;
        Snapshot snapshotCreateNonObservableSnapshot = companion.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                try {
                    int[] iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release = state.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(itemProvider, state.getScrollPosition().getIndices());
                    int[] offsets = state.getScrollPosition().getOffsets();
                    if (iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release.length == lazyStaggeredGridMeasureContext.getLaneCount()) {
                        t = iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release;
                    } else {
                        lazyStaggeredGridMeasureContext.getLaneInfo().reset();
                        int[] iArr = new int[lazyStaggeredGridMeasureContext.getLaneCount()];
                        int length = iArr.length;
                        int i7 = 0;
                        int[] iArr2 = iArr;
                        while (i7 < length) {
                            Snapshot.Companion companion2 = companion;
                            try {
                                if (i7 >= iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release.length) {
                                    i5 = i6;
                                } else {
                                    i5 = i6;
                                    if (iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release[i7] != -1) {
                                        try {
                                            iM678maxInRangejy6DScQ = iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release[i7];
                                        } catch (Throwable th) {
                                            th = th;
                                            snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                            throw th;
                                        }
                                    }
                                    iArr[i7] = iM678maxInRangejy6DScQ;
                                    lazyStaggeredGridMeasureContext.getLaneInfo().setLane(iArr[i7], i7);
                                    i7++;
                                    iArr2 = iArr2;
                                    companion = companion2;
                                    i6 = i5;
                                }
                                iM678maxInRangejy6DScQ = i7 == 0 ? 0 : m678maxInRangejy6DScQ(iArr, SpanRange.m688constructorimpl(0, i7)) + 1;
                                iArr[i7] = iM678maxInRangejy6DScQ;
                                lazyStaggeredGridMeasureContext.getLaneInfo().setLane(iArr[i7], i7);
                                i7++;
                                iArr2 = iArr2;
                                companion = companion2;
                                i6 = i5;
                            } catch (Throwable th2) {
                                th = th2;
                                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                throw th;
                            }
                        }
                        t = iArr2;
                    }
                    objectRef.element = t;
                    if (offsets.length == lazyStaggeredGridMeasureContext.getLaneCount()) {
                        t2 = offsets;
                    } else {
                        t2 = new int[lazyStaggeredGridMeasureContext.getLaneCount()];
                        int i8 = 0;
                        int length2 = t2.length;
                        while (i8 < length2) {
                            if (i8 < offsets.length) {
                                c = offsets[i8];
                            } else {
                                c = i8 == 0 ? 0 : t2[i8 - 1];
                            }
                            t2[i8] = c;
                            i8++;
                        }
                    }
                    objectRef2.element = t2;
                    Unit unit = Unit.INSTANCE;
                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    snapshotCreateNonObservableSnapshot.dispose();
                    return measure(lazyStaggeredGridMeasureContext, MathKt.roundToInt(state.getScrollToBeConsumed()), (int[]) objectRef.element, (int[]) objectRef2.element, true);
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
                snapshotCreateNonObservableSnapshot.dispose();
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    /* JADX WARN: Code duplicated, block: B:122:0x0385  */
    /* JADX WARN: Code duplicated, block: B:377:0x09e3  */
    /* JADX WARN: Code duplicated, block: B:380:0x0a3f  */
    /* JADX WARN: Code duplicated, block: B:382:0x0a91  */
    /* JADX WARN: Code duplicated, block: B:383:0x0a93  */
    /* JADX WARN: Code duplicated, block: B:385:0x0a96  */
    /* JADX WARN: Code duplicated, block: B:386:0x0a99  */
    /* JADX WARN: Code duplicated, block: B:389:0x0ad9  */
    /* JADX WARN: Code duplicated, block: B:390:0x0adb  */
    /* JADX WARN: Code duplicated, block: B:392:0x0ade  */
    /* JADX WARN: Code duplicated, block: B:394:0x0ae8  */
    /* JADX WARN: Code duplicated, block: B:395:0x0aef  */
    /* JADX WARN: Code duplicated, block: B:398:0x0b12  */
    /* JADX WARN: Code duplicated, block: B:400:0x0b16  */
    /* JADX WARN: Code duplicated, block: B:431:0x0b65 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:433:0x0397 A[EDGE_INSN: B:433:0x0397->B:124:0x0397 BREAK  A[LOOP:9: B:98:0x034c->B:435:0x034c], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:434:0x0b4a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:485:0x0b1c A[SYNTHETIC] */
    private static final LazyStaggeredGridMeasureResult measure(final LazyStaggeredGridMeasureContext $this$measure, int initialScrollDelta, int[] initialItemIndices, int[] initialItemOffsets, boolean canRestartMeasure) {
        boolean z;
        int currentLaneIndex;
        int itemIndex;
        int itemCount;
        long spanRange;
        boolean z2;
        int i;
        LazyStaggeredGridMeasuredItem measuredItem;
        int offset;
        long $this$isFullSpan$iv;
        boolean z3;
        int[] gaps;
        int[] gaps2;
        int i2;
        int i$iv;
        int lane;
        boolean z4;
        boolean z5;
        int[] currentItemOffsets;
        int[] firstItemIndices;
        LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext;
        int toScrollBack;
        boolean z6;
        boolean canScrollForward;
        boolean z7;
        List<Integer> list;
        ArrayDeque[] measuredItems;
        int itemCount2;
        boolean z8;
        int[] firstItemOffsets;
        int[] currentItemOffsets2;
        int layoutHeight;
        boolean z9;
        int scrollDelta;
        int maxOffsetLane;
        boolean gapDetected;
        int maxOffsetLane2;
        boolean gapDetected2;
        boolean z10;
        int scrollDelta2;
        int minOffset;
        int[] firstItemIndices2;
        String str;
        int laneToCheckForGaps;
        int initialLaneToMeasure;
        $this$measure = $this$measure;
        LazyLayoutMeasureScope scope$iv = $this$measure.getMeasureScope();
        int itemCount3 = $this$measure.getItemProvider().getItemCount();
        if (itemCount3 <= 0 || $this$measure.getLaneCount() == 0) {
            return new LazyStaggeredGridMeasureResult(initialItemIndices, initialItemOffsets, 0.0f, MeasureScope.CC.layout$default(scope$iv, Constraints.m5220getMinWidthimpl($this$measure.getConstraints()), Constraints.m5219getMinHeightimpl($this$measure.getConstraints()), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt$measure$1$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope layout) {
                    Intrinsics.checkNotNullParameter(layout, "$this$layout");
                }
            }, 4, null), false, false, $this$measure.getIsVertical(), itemCount3, CollectionsKt.emptyList(), IntSizeKt.IntSize(Constraints.m5220getMinWidthimpl($this$measure.getConstraints()), Constraints.m5219getMinHeightimpl($this$measure.getConstraints())), -$this$measure.getBeforeContentPadding(), $this$measure.getMainAxisAvailableSize() + $this$measure.getAfterContentPadding(), $this$measure.getBeforeContentPadding(), $this$measure.getAfterContentPadding(), $this$measure.getMainAxisSpacing(), null);
        }
        int scrollDelta3 = initialScrollDelta;
        int[] firstItemIndices3 = Arrays.copyOf(initialItemIndices, initialItemIndices.length);
        String str2 = "copyOf(this, size)";
        Intrinsics.checkNotNullExpressionValue(firstItemIndices3, "copyOf(this, size)");
        int[] firstItemOffsets2 = Arrays.copyOf(initialItemOffsets, initialItemOffsets.length);
        Intrinsics.checkNotNullExpressionValue(firstItemOffsets2, "copyOf(this, size)");
        ensureIndicesInRange($this$measure, firstItemIndices3, itemCount3);
        offsetBy(firstItemOffsets2, -scrollDelta3);
        int laneCount = $this$measure.getLaneCount();
        ArrayDeque[] arrayDequeArr = new ArrayDeque[laneCount];
        for (int i3 = 0; i3 < laneCount; i3++) {
            arrayDequeArr[i3] = new ArrayDeque(16);
        }
        ArrayDeque[] measuredItems2 = arrayDequeArr;
        offsetBy(firstItemOffsets2, -$this$measure.getBeforeContentPadding());
        int laneToCheckForGaps2 = -1;
        while (measure$lambda$38$hasSpaceBeforeFirst(firstItemIndices3, firstItemOffsets2, $this$measure)) {
            int laneIndex = indexOfMaxValue(firstItemIndices3);
            int itemIndex2 = firstItemIndices3[laneIndex];
            int length = firstItemOffsets2.length;
            for (int i4 = 0; i4 < length; i4++) {
                if (firstItemIndices3[i4] != firstItemIndices3[laneIndex] && firstItemOffsets2[i4] < firstItemOffsets2[laneIndex]) {
                    firstItemOffsets2[i4] = firstItemOffsets2[laneIndex];
                }
            }
            int previousItemIndex = findPreviousItemIndex($this$measure, itemIndex2, laneIndex);
            if (previousItemIndex < 0) {
                laneToCheckForGaps2 = laneIndex;
                break;
            }
            long spanRange2 = $this$measure.m675getSpanRangelOCCd4c($this$measure.getItemProvider(), previousItemIndex, laneIndex);
            int laneToCheckForGaps3 = laneToCheckForGaps2;
            int itemCount4 = itemCount3;
            $this$measure.getLaneInfo().setLane(previousItemIndex, ((int) (spanRange2 & 4294967295L)) - ((int) (spanRange2 >> 32)) != 1 ? -2 : (int) (spanRange2 >> 32));
            LazyStaggeredGridMeasuredItem measuredItem2 = $this$measure.getMeasuredItemProvider().m683getAndMeasurejy6DScQ(previousItemIndex, spanRange2);
            int offset2 = m678maxInRangejy6DScQ(firstItemOffsets2, spanRange2);
            long $this$isFullSpan$iv2 = spanRange2 >> 32;
            int[] gaps3 = ((int) (spanRange2 & 4294967295L)) - ((int) $this$isFullSpan$iv2) != 1 ? $this$measure.getLaneInfo().getGaps(previousItemIndex) : null;
            long $this$forEach_u2dnIS5qE8$iv = spanRange2 & 4294967295L;
            int i5 = (int) $this$forEach_u2dnIS5qE8$iv;
            for (int i$iv2 = (int) (spanRange2 >> 32); i$iv2 < i5; i$iv2++) {
                int lane2 = i$iv2;
                firstItemIndices3[lane2] = previousItemIndex;
                int gap = gaps3 == null ? 0 : gaps3[lane2];
                firstItemOffsets2[lane2] = offset2 + measuredItem2.getSizeWithSpacings() + gap;
            }
            laneToCheckForGaps2 = laneToCheckForGaps3;
            itemCount3 = itemCount4;
        }
        int $i$f$debugLog = $this$measure.getBeforeContentPadding();
        int minOffset2 = -$i$f$debugLog;
        if (firstItemOffsets2[0] < minOffset2) {
            scrollDelta3 += firstItemOffsets2[0];
            offsetBy(firstItemOffsets2, minOffset2 - firstItemOffsets2[0]);
        }
        int $i$f$debugLog2 = $this$measure.getBeforeContentPadding();
        offsetBy(firstItemOffsets2, $i$f$debugLog2);
        int i6 = -1;
        int laneToCheckForGaps4 = laneToCheckForGaps2 == -1 ? ArraysKt.indexOf(firstItemIndices3, 0) : laneToCheckForGaps2;
        if (laneToCheckForGaps4 != -1 && measure$lambda$38$misalignedStart(firstItemIndices3, $this$measure, firstItemOffsets2, laneToCheckForGaps4) && canRestartMeasure) {
            $this$measure.getLaneInfo().reset();
            int length2 = firstItemIndices3.length;
            int[] iArr = new int[length2];
            for (int i7 = 0; i7 < length2; i7++) {
                iArr[i7] = -1;
            }
            int length3 = firstItemOffsets2.length;
            int[] iArr2 = new int[length3];
            for (int i8 = 0; i8 < length3; i8++) {
                iArr2[i8] = firstItemOffsets2[laneToCheckForGaps4];
            }
            return measure($this$measure, scrollDelta3, iArr, iArr2, false);
        }
        int[] currentItemIndices = Arrays.copyOf(firstItemIndices3, firstItemIndices3.length);
        Intrinsics.checkNotNullExpressionValue(currentItemIndices, "copyOf(this, size)");
        int length4 = firstItemOffsets2.length;
        int[] iArr3 = new int[length4];
        for (int i9 = 0; i9 < length4; i9++) {
            iArr3[i9] = -firstItemOffsets2[i9];
        }
        int[] currentItemOffsets3 = iArr3;
        int maxOffset = RangesKt.coerceAtLeast($this$measure.getMainAxisAvailableSize() + $this$measure.getAfterContentPadding(), 0);
        int initialItemsMeasured = 0;
        int initialLaneToMeasure2 = indexOfMinValue$default(currentItemIndices, 0, 1, null);
        while (initialLaneToMeasure2 != i6 && initialItemsMeasured < $this$measure.getLaneCount()) {
            int itemIndex3 = currentItemIndices[initialLaneToMeasure2];
            int laneIndex2 = initialLaneToMeasure2;
            int initialLaneToMeasure3 = indexOfMinValue(currentItemIndices, itemIndex3);
            int initialItemsMeasured2 = initialItemsMeasured + 1;
            if (itemIndex3 >= 0) {
                initialLaneToMeasure = initialLaneToMeasure3;
                long spanRange3 = $this$measure.m675getSpanRangelOCCd4c($this$measure.getItemProvider(), itemIndex3, laneIndex2);
                LazyStaggeredGridMeasuredItem measuredItem3 = $this$measure.getMeasuredItemProvider().m683getAndMeasurejy6DScQ(itemIndex3, spanRange3);
                laneToCheckForGaps = laneToCheckForGaps4;
                scrollDelta2 = scrollDelta3;
                firstItemIndices2 = firstItemIndices3;
                str = str2;
                $this$measure.getLaneInfo().setLane(itemIndex3, ((int) (spanRange3 & 4294967295L)) - ((int) (spanRange3 >> 32)) != 1 ? -2 : (int) (spanRange3 >> 32));
                int offset3 = m678maxInRangejy6DScQ(currentItemOffsets3, spanRange3) + measuredItem3.getSizeWithSpacings();
                minOffset = minOffset2;
                int i$iv3 = (int) (spanRange3 >> 32);
                int i10 = (int) (spanRange3 & 4294967295L);
                int i$iv4 = i$iv3;
                while (i$iv4 < i10) {
                    int lane3 = i$iv4;
                    currentItemOffsets3[lane3] = offset3;
                    currentItemIndices[lane3] = itemIndex3;
                    measuredItems2[lane3].addLast(measuredItem3);
                    i$iv4++;
                    offset3 = offset3;
                }
                if (currentItemOffsets3[(int) (spanRange3 >> 32)] <= minOffset + $this$measure.getMainAxisSpacing()) {
                    measuredItem3.setVisible(false);
                }
                long $this$isFullSpan$iv3 = spanRange3 >> 32;
                if (((int) (spanRange3 & 4294967295L)) - ((int) $this$isFullSpan$iv3) != 1) {
                    initialItemsMeasured = $this$measure.getLaneCount();
                    initialLaneToMeasure2 = initialLaneToMeasure;
                    laneToCheckForGaps4 = laneToCheckForGaps;
                    minOffset2 = minOffset;
                    firstItemIndices3 = firstItemIndices2;
                    scrollDelta3 = scrollDelta2;
                    str2 = str;
                    i6 = -1;
                }
            } else {
                scrollDelta2 = scrollDelta3;
                minOffset = minOffset2;
                firstItemIndices2 = firstItemIndices3;
                str = str2;
                laneToCheckForGaps = laneToCheckForGaps4;
                initialLaneToMeasure = initialLaneToMeasure3;
            }
            initialLaneToMeasure2 = initialLaneToMeasure;
            initialItemsMeasured = initialItemsMeasured2;
            laneToCheckForGaps4 = laneToCheckForGaps;
            minOffset2 = minOffset;
            firstItemIndices3 = firstItemIndices2;
            scrollDelta3 = scrollDelta2;
            str2 = str;
            i6 = -1;
        }
        int scrollDelta4 = scrollDelta3;
        int minOffset3 = minOffset2;
        int[] firstItemIndices4 = firstItemIndices3;
        String str3 = str2;
        while (true) {
            int[] $this$any$iv = currentItemOffsets3;
            int length5 = $this$any$iv.length;
            int i11 = 0;
            while (true) {
                if (i11 >= length5) {
                    z = false;
                    break;
                }
                int element$iv = $this$any$iv[i11];
                if (element$iv < maxOffset || element$iv <= 0) {
                    z = true;
                    break;
                }
                i11++;
            }
            if (!z) {
                ArrayDeque[] arrayDequeArr2 = measuredItems2;
                int length6 = arrayDequeArr2.length;
                int i12 = 0;
                while (true) {
                    if (i12 >= length6) {
                        z10 = true;
                        break;
                    }
                    if (!arrayDequeArr2[i12].isEmpty()) {
                        z10 = false;
                        break;
                    }
                    i12++;
                }
                if (!z10) {
                    itemCount = itemCount3;
                    break;
                }
                currentLaneIndex = indexOfMinValue$default(currentItemOffsets3, 0, 1, null);
                itemIndex = ArraysKt.maxOrThrow(currentItemIndices) + 1;
                itemCount = itemCount3;
                if (itemIndex >= itemCount) {
                    break;
                }
                itemCount3 = itemCount;
                firstItemOffsets2 = firstItemOffsets2;
                int[] currentItemOffsets4 = currentItemOffsets3;
                maxOffset = maxOffset;
                initialLaneToMeasure2 = initialLaneToMeasure2;
                int[] firstItemIndices5 = firstItemIndices4;
                int scrollDelta5 = scrollDelta4;
                measuredItems2 = measuredItems2;
                currentItemIndices = currentItemIndices;
                str3 = str3;
                initialItemsMeasured = initialItemsMeasured;
                spanRange = $this$measure.m675getSpanRangelOCCd4c($this$measure.getItemProvider(), itemIndex, currentLaneIndex);
                LazyStaggeredGridLaneInfo laneInfo = $this$measure.getLaneInfo();
                scrollDelta4 = scrollDelta5;
                if (((int) (spanRange & 4294967295L)) - ((int) (spanRange >> 32)) != 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    i = -2;
                } else {
                    i = (int) (spanRange >> 32);
                }
                laneInfo.setLane(itemIndex, i);
                measuredItem = $this$measure.getMeasuredItemProvider().m683getAndMeasurejy6DScQ(itemIndex, spanRange);
                currentItemOffsets3 = currentItemOffsets4;
                offset = m678maxInRangejy6DScQ(currentItemOffsets3, spanRange);
                firstItemIndices4 = firstItemIndices5;
                $this$isFullSpan$iv = spanRange >> 32;
                if (((int) (spanRange & 4294967295L)) - ((int) $this$isFullSpan$iv) != 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    gaps = $this$measure.getLaneInfo().getGaps(itemIndex);
                    if (gaps == null) {
                        gaps = new int[$this$measure.getLaneCount()];
                    }
                } else {
                    gaps = null;
                }
                gaps2 = gaps;
                int i$iv5 = (int) (spanRange >> 32);
                i2 = (int) (spanRange & 4294967295L);
                for (i$iv = i$iv5; i$iv < i2; i$iv++) {
                    lane = i$iv;
                    if (gaps2 != null) {
                        gaps2[lane] = offset - currentItemOffsets3[lane];
                    }
                    currentItemIndices[lane] = itemIndex;
                    currentItemOffsets3[lane] = measuredItem.getSizeWithSpacings() + offset;
                    measuredItems2[lane].addLast(measuredItem);
                }
                $this$measure.getLaneInfo().setGaps(itemIndex, gaps2);
                if (currentItemOffsets3[(int) (spanRange >> 32)] <= minOffset3 + $this$measure.getMainAxisSpacing()) {
                    measuredItem.setVisible(false);
                }
            } else {
                currentLaneIndex = indexOfMinValue$default(currentItemOffsets3, 0, 1, null);
                itemIndex = ArraysKt.maxOrThrow(currentItemIndices) + 1;
                itemCount = itemCount3;
                if (itemIndex >= itemCount) {
                    break;
                    break;
                }
                itemCount3 = itemCount;
                firstItemOffsets2 = firstItemOffsets2;
                int[] currentItemOffsets5 = currentItemOffsets3;
                maxOffset = maxOffset;
                initialLaneToMeasure2 = initialLaneToMeasure2;
                int[] firstItemIndices6 = firstItemIndices4;
                int scrollDelta6 = scrollDelta4;
                measuredItems2 = measuredItems2;
                currentItemIndices = currentItemIndices;
                str3 = str3;
                initialItemsMeasured = initialItemsMeasured;
                spanRange = $this$measure.m675getSpanRangelOCCd4c($this$measure.getItemProvider(), itemIndex, currentLaneIndex);
                LazyStaggeredGridLaneInfo laneInfo2 = $this$measure.getLaneInfo();
                scrollDelta4 = scrollDelta6;
                if (((int) (spanRange & 4294967295L)) - ((int) (spanRange >> 32)) != 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    i = -2;
                } else {
                    i = (int) (spanRange >> 32);
                }
                laneInfo2.setLane(itemIndex, i);
                measuredItem = $this$measure.getMeasuredItemProvider().m683getAndMeasurejy6DScQ(itemIndex, spanRange);
                currentItemOffsets3 = currentItemOffsets5;
                offset = m678maxInRangejy6DScQ(currentItemOffsets3, spanRange);
                firstItemIndices4 = firstItemIndices6;
                $this$isFullSpan$iv = spanRange >> 32;
                if (((int) (spanRange & 4294967295L)) - ((int) $this$isFullSpan$iv) != 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    gaps = $this$measure.getLaneInfo().getGaps(itemIndex);
                    if (gaps == null) {
                        gaps = new int[$this$measure.getLaneCount()];
                    }
                } else {
                    gaps = null;
                }
                gaps2 = gaps;
                int i$iv6 = (int) (spanRange >> 32);
                i2 = (int) (spanRange & 4294967295L);
                while (i$iv < i2) {
                    lane = i$iv;
                    if (gaps2 != null) {
                        gaps2[lane] = offset - currentItemOffsets3[lane];
                    }
                    currentItemIndices[lane] = itemIndex;
                    currentItemOffsets3[lane] = measuredItem.getSizeWithSpacings() + offset;
                    measuredItems2[lane].addLast(measuredItem);
                }
                $this$measure.getLaneInfo().setGaps(itemIndex, gaps2);
                if (currentItemOffsets3[(int) (spanRange >> 32)] <= minOffset3 + $this$measure.getMainAxisSpacing()) {
                    measuredItem.setVisible(false);
                }
            }
        }
        int length7 = measuredItems2.length;
        for (int laneIndex3 = 0; laneIndex3 < length7; laneIndex3++) {
            ArrayDeque laneItems = measuredItems2[laneIndex3];
            while (laneItems.size() > 1 && !((LazyStaggeredGridMeasuredItem) laneItems.first()).getIsVisible()) {
                LazyStaggeredGridMeasuredItem item = (LazyStaggeredGridMeasuredItem) laneItems.removeFirst();
                int[] gaps4 = item.getSpan() != 1 ? $this$measure.getLaneInfo().getGaps(item.getIndex()) : null;
                firstItemOffsets2[laneIndex3] = firstItemOffsets2[laneIndex3] - (item.getSizeWithSpacings() + (gaps4 == null ? 0 : gaps4[laneIndex3]));
            }
            LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem = (LazyStaggeredGridMeasuredItem) laneItems.firstOrNull();
            firstItemIndices4[laneIndex3] = lazyStaggeredGridMeasuredItem != null ? lazyStaggeredGridMeasuredItem.getIndex() : -1;
        }
        int[] $this$any$iv2 = currentItemIndices;
        int length8 = $this$any$iv2.length;
        int i13 = 0;
        while (true) {
            if (i13 >= length8) {
                z4 = false;
                break;
            }
            int element$iv2 = $this$any$iv2[i13];
            int it = element$iv2 == itemCount + (-1) ? 1 : 0;
            if (it != 0) {
                z4 = true;
                break;
            }
            i13++;
        }
        if (z4) {
            offsetBy(currentItemOffsets3, -$this$measure.getMainAxisSpacing());
        }
        int[] $this$all$iv = currentItemOffsets3;
        int length9 = $this$all$iv.length;
        int i14 = 0;
        while (true) {
            if (i14 >= length9) {
                z5 = true;
                break;
            }
            int element$iv3 = $this$all$iv[i14];
            int it2 = element$iv3 < $this$measure.getMainAxisAvailableSize() ? 1 : 0;
            if (it2 == 0) {
                z5 = false;
                break;
            }
            i14++;
        }
        if (z5) {
            int maxOffsetLane3 = indexOfMaxValue(currentItemOffsets3);
            int toScrollBack2 = $this$measure.getMainAxisAvailableSize() - currentItemOffsets3[maxOffsetLane3];
            offsetBy(firstItemOffsets2, -toScrollBack2);
            offsetBy(currentItemOffsets3, toScrollBack2);
            boolean gapDetected3 = false;
            while (true) {
                int[] $this$any$iv3 = firstItemOffsets2;
                int length10 = $this$any$iv3.length;
                int i15 = 0;
                while (true) {
                    if (i15 >= length10) {
                        maxOffsetLane = maxOffsetLane3;
                        gapDetected = gapDetected3;
                        maxOffsetLane2 = 0;
                        break;
                    }
                    int element$iv4 = $this$any$iv3[i15];
                    maxOffsetLane = maxOffsetLane3;
                    gapDetected = gapDetected3;
                    if (element$iv4 < $this$measure.getBeforeContentPadding()) {
                        maxOffsetLane2 = 1;
                        break;
                    }
                    i15++;
                    maxOffsetLane3 = maxOffsetLane;
                    gapDetected3 = gapDetected;
                }
                if (maxOffsetLane2 == 0) {
                    firstItemIndices = firstItemIndices4;
                    gapDetected2 = gapDetected;
                    break;
                }
                int laneIndex4 = indexOfMinValue$default(firstItemOffsets2, 0, 1, null);
                boolean gapDetected4 = laneIndex4 != indexOfMaxValue(firstItemIndices4) ? true : gapDetected;
                int currentIndex = firstItemIndices4[laneIndex4] == -1 ? itemCount : firstItemIndices4[laneIndex4];
                int previousIndex = findPreviousItemIndex($this$measure, currentIndex, laneIndex4);
                if (previousIndex < 0) {
                    if (!gapDetected4) {
                        firstItemIndices = firstItemIndices4;
                        if (measure$lambda$38$misalignedStart(firstItemIndices, $this$measure, firstItemOffsets2, laneIndex4)) {
                        }
                        gapDetected2 = gapDetected4;
                        break;
                    }
                    firstItemIndices = firstItemIndices4;
                    if (!canRestartMeasure) {
                        gapDetected2 = gapDetected4;
                        break;
                    }
                    $this$measure.getLaneInfo().reset();
                    int length11 = firstItemIndices.length;
                    int[] iArr4 = new int[length11];
                    for (int i16 = 0; i16 < length11; i16++) {
                        iArr4[i16] = -1;
                    }
                    int length12 = firstItemOffsets2.length;
                    int[] iArr5 = new int[length12];
                    for (int initialLaneToMeasure4 = 0; initialLaneToMeasure4 < length12; initialLaneToMeasure4++) {
                        iArr5[initialLaneToMeasure4] = firstItemOffsets2[laneIndex4];
                    }
                    return measure($this$measure, scrollDelta4, iArr4, iArr5, false);
                }
                boolean gapDetected5 = gapDetected4;
                int initialLaneToMeasure5 = initialLaneToMeasure2;
                int[] firstItemIndices7 = firstItemIndices4;
                int initialLaneToMeasure6 = scrollDelta4;
                int itemCount5 = itemCount;
                long spanRange4 = $this$measure.m675getSpanRangelOCCd4c($this$measure.getItemProvider(), previousIndex, laneIndex4);
                int[] currentItemIndices2 = currentItemIndices;
                int[] currentItemOffsets6 = currentItemOffsets3;
                $this$measure.getLaneInfo().setLane(previousIndex, ((int) (spanRange4 & 4294967295L)) - ((int) (spanRange4 >> 32)) != 1 ? -2 : (int) (spanRange4 >> 32));
                LazyStaggeredGridMeasuredItem measuredItem4 = $this$measure.getMeasuredItemProvider().m683getAndMeasurejy6DScQ(previousIndex, spanRange4);
                int offset4 = m678maxInRangejy6DScQ(firstItemOffsets2, spanRange4);
                int maxOffset2 = maxOffset;
                long $this$isFullSpan$iv4 = spanRange4 >> 32;
                int[] gaps5 = ((int) (spanRange4 & 4294967295L)) - ((int) $this$isFullSpan$iv4) != 1 ? $this$measure.getLaneInfo().getGaps(previousIndex) : null;
                int i$iv7 = (int) (spanRange4 >> 32);
                int i17 = (int) (spanRange4 & 4294967295L);
                int i$iv8 = i$iv7;
                while (i$iv8 < i17) {
                    int lane4 = i$iv8;
                    int i18 = i17;
                    if (firstItemOffsets2[lane4] != offset4) {
                        gapDetected5 = true;
                    }
                    measuredItems2[lane4].addFirst(measuredItem4);
                    firstItemIndices7[lane4] = previousIndex;
                    int gap2 = gaps5 == null ? 0 : gaps5[lane4];
                    firstItemOffsets2[lane4] = offset4 + measuredItem4.getSizeWithSpacings() + gap2;
                    i$iv8++;
                    i17 = i18;
                }
                gapDetected3 = gapDetected5;
                maxOffsetLane3 = maxOffsetLane;
                initialLaneToMeasure2 = initialLaneToMeasure5;
                itemCount = itemCount5;
                currentItemIndices = currentItemIndices2;
                maxOffset = maxOffset2;
                currentItemOffsets3 = currentItemOffsets6;
                firstItemIndices4 = firstItemIndices7;
                scrollDelta4 = initialLaneToMeasure6;
            }
            if (gapDetected2 && canRestartMeasure) {
                $this$measure.getLaneInfo().reset();
                return measure($this$measure, scrollDelta4, firstItemIndices, firstItemOffsets2, false);
            }
            lazyStaggeredGridMeasureContext = $this$measure;
            int scrollDelta7 = scrollDelta4 + toScrollBack2;
            int minOffsetLane = indexOfMinValue$default(firstItemOffsets2, 0, 1, null);
            if (firstItemOffsets2[minOffsetLane] < 0) {
                int offsetValue = firstItemOffsets2[minOffsetLane];
                currentItemOffsets = currentItemOffsets3;
                offsetBy(currentItemOffsets, offsetValue);
                offsetBy(firstItemOffsets2, -offsetValue);
                toScrollBack = scrollDelta7 + offsetValue;
            } else {
                currentItemOffsets = currentItemOffsets3;
                toScrollBack = scrollDelta7;
            }
        } else {
            currentItemOffsets = currentItemOffsets3;
            maxOffset = maxOffset;
            firstItemIndices = firstItemIndices4;
            int scrollDelta8 = scrollDelta4;
            itemCount = itemCount;
            currentItemIndices = currentItemIndices;
            lazyStaggeredGridMeasureContext = $this$measure;
            toScrollBack = scrollDelta8;
        }
        float consumedScroll = (MathKt.getSign(MathKt.roundToInt($this$measure.getState().getScrollToBeConsumed())) != MathKt.getSign(toScrollBack) || Math.abs(MathKt.roundToInt($this$measure.getState().getScrollToBeConsumed())) < Math.abs(toScrollBack)) ? $this$measure.getState().getScrollToBeConsumed() : toScrollBack;
        int[] $this$transform$iv = Arrays.copyOf(firstItemOffsets2, firstItemOffsets2.length);
        Intrinsics.checkNotNullExpressionValue($this$transform$iv, str3);
        int length13 = $this$transform$iv.length;
        for (int i$iv9 = 0; i$iv9 < length13; i$iv9++) {
            int it3 = $this$transform$iv[i$iv9];
            $this$transform$iv[i$iv9] = -it3;
        }
        int $i$f$debugLog3 = $this$measure.getBeforeContentPadding();
        if ($i$f$debugLog3 > $this$measure.getMainAxisSpacing()) {
            int laneIndex5 = 0;
            int length14 = measuredItems2.length;
            while (laneIndex5 < length14) {
                ArrayDeque laneItems2 = measuredItems2[laneIndex5];
                int i19 = 0;
                int size = laneItems2.size();
                while (true) {
                    if (i19 >= size) {
                        scrollDelta = toScrollBack;
                        break;
                    }
                    LazyStaggeredGridMeasuredItem item2 = (LazyStaggeredGridMeasuredItem) laneItems2.get(i19);
                    scrollDelta = toScrollBack;
                    int[] gaps6 = $this$measure.getLaneInfo().getGaps(item2.getIndex());
                    int size2 = item2.getSizeWithSpacings() + (gaps6 == null ? 0 : gaps6[laneIndex5]);
                    if (i19 == CollectionsKt.getLastIndex(laneItems2) || firstItemOffsets2[laneIndex5] == 0 || firstItemOffsets2[laneIndex5] < size2) {
                        break;
                    }
                    firstItemOffsets2[laneIndex5] = firstItemOffsets2[laneIndex5] - size2;
                    firstItemIndices[laneIndex5] = ((LazyStaggeredGridMeasuredItem) laneItems2.get(i19 + 1)).getIndex();
                    i19++;
                    toScrollBack = scrollDelta;
                }
                laneIndex5++;
                toScrollBack = scrollDelta;
            }
        }
        int $i$f$debugLog4 = $this$measure.getBeforeContentPadding();
        int contentPadding = $i$f$debugLog4 + $this$measure.getAfterContentPadding();
        int layoutWidth = $this$measure.getIsVertical() ? Constraints.m5218getMaxWidthimpl($this$measure.getConstraints()) : ConstraintsKt.m5232constrainWidthK40F9xA($this$measure.getConstraints(), ArraysKt.maxOrThrow(currentItemOffsets) + contentPadding);
        int layoutHeight2 = $this$measure.getIsVertical() ? ConstraintsKt.m5231constrainHeightK40F9xA($this$measure.getConstraints(), ArraysKt.maxOrThrow(currentItemOffsets) + contentPadding) : Constraints.m5217getMaxHeightimpl($this$measure.getConstraints());
        int it4 = Math.min($this$measure.getIsVertical() ? layoutHeight2 : layoutWidth, $this$measure.getMainAxisAvailableSize());
        int mainAxisLayoutSize = (it4 - $this$measure.getBeforeContentPadding()) + $this$measure.getAfterContentPadding();
        int extraItemOffset = $this$transform$iv[0];
        List listEmptyList = null;
        List<Integer> pinnedItems = $this$measure.getPinnedItems();
        int extraItemOffset2 = extraItemOffset;
        int extraItemOffset3 = pinnedItems.size();
        int $i$f$calculateExtraItems = 0;
        while ($i$f$calculateExtraItems < extraItemOffset3) {
            Object item$iv$iv = pinnedItems.get($i$f$calculateExtraItems);
            List<Integer> list2 = pinnedItems;
            int index$iv = ((Number) item$iv$iv).intValue();
            int i20 = extraItemOffset3;
            int initialItemsMeasured3 = initialItemsMeasured;
            int lane5 = $this$measure.getLaneInfo().getLane(index$iv);
            switch (lane5) {
                case -2:
                case -1:
                    int[] $this$all$iv2 = firstItemIndices;
                    firstItemOffsets = firstItemOffsets2;
                    currentItemOffsets2 = currentItemOffsets;
                    int length15 = $this$all$iv2.length;
                    layoutHeight = layoutHeight2;
                    int layoutHeight3 = 0;
                    while (true) {
                        if (layoutHeight3 >= length15) {
                            z9 = true;
                        } else {
                            int element$iv5 = $this$all$iv2[layoutHeight3];
                            int i21 = length15;
                            int it5 = element$iv5 > index$iv ? 1 : 0;
                            if (it5 == 0) {
                                z9 = false;
                            } else {
                                layoutHeight3++;
                                length15 = i21;
                            }
                        }
                        break;
                    }
                    break;
                default:
                    layoutHeight = layoutHeight2;
                    firstItemOffsets = firstItemOffsets2;
                    currentItemOffsets2 = currentItemOffsets;
                    z9 = firstItemIndices[lane5] > index$iv;
                    break;
            }
            if (z9) {
                long spanRange$iv = $this$measure.m675getSpanRangelOCCd4c($this$measure.getItemProvider(), index$iv, 0);
                if (listEmptyList == null) {
                    Object result$iv = new ArrayList();
                    listEmptyList = (List) result$iv;
                }
                LazyStaggeredGridMeasuredItem measuredItem$iv = $this$measure.getMeasuredItemProvider().m683getAndMeasurejy6DScQ(index$iv, spanRange$iv);
                int index$iv2 = extraItemOffset2 - measuredItem$iv.getSizeWithSpacings();
                measuredItem$iv.position(index$iv2, 0, mainAxisLayoutSize);
                listEmptyList.add(measuredItem$iv);
                extraItemOffset2 = index$iv2;
            }
            $i$f$calculateExtraItems++;
            firstItemIndices = firstItemIndices;
            pinnedItems = list2;
            extraItemOffset3 = i20;
            initialItemsMeasured = initialItemsMeasured3;
            firstItemOffsets2 = firstItemOffsets;
            currentItemOffsets = currentItemOffsets2;
            layoutHeight2 = layoutHeight;
        }
        int layoutHeight4 = layoutHeight2;
        int[] firstItemOffsets3 = firstItemOffsets2;
        int[] currentItemOffsets7 = currentItemOffsets;
        int[] currentItemOffsets8 = firstItemIndices;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List extraItemsBefore = listEmptyList;
        List<LazyStaggeredGridMeasuredItem> listCalculateVisibleItems = calculateVisibleItems(lazyStaggeredGridMeasureContext, measuredItems2, $this$transform$iv, mainAxisLayoutSize);
        int extraItemOffset4 = $this$transform$iv[0];
        int $i$f$calculateExtraItems2 = 0;
        List listEmptyList2 = null;
        List<Integer> pinnedItems2 = $this$measure.getPinnedItems();
        int $i$f$fastForEach = 0;
        int index$iv$iv = 0;
        int size3 = pinnedItems2.size();
        while (index$iv$iv < size3) {
            Object item$iv$iv2 = pinnedItems2.get(index$iv$iv);
            int i22 = size3;
            int index$iv3 = ((Number) item$iv$iv2).intValue();
            int $i$f$fastForEach2 = $i$f$fastForEach;
            int $i$f$fastForEach3 = itemCount;
            int itemIndex4 = $i$f$calculateExtraItems2;
            if (index$iv3 < $i$f$fastForEach3) {
                list = pinnedItems2;
                int lane6 = $this$measure.getLaneInfo().getLane(index$iv3);
                switch (lane6) {
                    case -2:
                    case -1:
                        int[] $this$all$iv3 = currentItemIndices;
                        measuredItems = measuredItems2;
                        int length16 = $this$all$iv3.length;
                        itemCount2 = $i$f$fastForEach3;
                        int itemCount6 = 0;
                        while (true) {
                            if (itemCount6 >= length16) {
                                z8 = true;
                            } else {
                                int element$iv6 = $this$all$iv3[itemCount6];
                                int i23 = length16;
                                int it6 = element$iv6 < index$iv3 ? 1 : 0;
                                if (it6 == 0) {
                                    z8 = false;
                                } else {
                                    itemCount6++;
                                    length16 = i23;
                                }
                            }
                            break;
                        }
                        break;
                    default:
                        measuredItems = measuredItems2;
                        itemCount2 = $i$f$fastForEach3;
                        z8 = currentItemIndices[lane6] < index$iv3;
                        break;
                }
            } else {
                measuredItems = measuredItems2;
                list = pinnedItems2;
                itemCount2 = $i$f$fastForEach3;
                z8 = false;
            }
            if (z8) {
                long spanRange$iv2 = $this$measure.m675getSpanRangelOCCd4c($this$measure.getItemProvider(), index$iv3, 0);
                if (listEmptyList2 == null) {
                    Object result$iv2 = new ArrayList();
                    listEmptyList2 = (List) result$iv2;
                }
                LazyStaggeredGridMeasuredItem measuredItem$iv2 = $this$measure.getMeasuredItemProvider().m683getAndMeasurejy6DScQ(index$iv3, spanRange$iv2);
                measuredItem$iv2.position(extraItemOffset4, 0, mainAxisLayoutSize);
                extraItemOffset4 += measuredItem$iv2.getSizeWithSpacings();
                listEmptyList2.add(measuredItem$iv2);
            }
            index$iv$iv++;
            size3 = i22;
            $i$f$calculateExtraItems2 = itemIndex4;
            $i$f$fastForEach = $i$f$fastForEach2;
            pinnedItems2 = list;
            measuredItems2 = measuredItems;
            itemCount = itemCount2;
        }
        int itemCount7 = itemCount;
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        List extraItemsAfter = listEmptyList2;
        final List positionedItems = new ArrayList();
        positionedItems.addAll(extraItemsBefore);
        positionedItems.addAll(listCalculateVisibleItems);
        positionedItems.addAll(extraItemsAfter);
        $this$measure.getState().getPlacementAnimator().onMeasured((int) consumedScroll, layoutWidth, layoutHeight4, positionedItems, $this$measure.getMeasuredItemProvider(), $this$measure.getIsVertical(), $this$measure.getLaneCount());
        boolean canScrollBackward = currentItemOffsets8[0] != 0 || firstItemOffsets3[0] > 0;
        int length17 = currentItemOffsets7.length;
        int i24 = 0;
        while (true) {
            if (i24 < length17) {
                int element$iv7 = currentItemOffsets7[i24];
                List extraItemsBefore2 = extraItemsBefore;
                if (element$iv7 > $this$measure.getMainAxisAvailableSize()) {
                    z6 = true;
                } else {
                    i24++;
                    extraItemsBefore = extraItemsBefore2;
                }
            } else {
                z6 = false;
            }
        }
        if (z6) {
            canScrollForward = true;
        } else {
            int[] $this$all$iv4 = currentItemIndices;
            int length18 = $this$all$iv4.length;
            int i25 = 0;
            while (true) {
                if (i25 < length18) {
                    int element$iv8 = $this$all$iv4[i25];
                    int it7 = element$iv8 < itemCount7 + (-1) ? 1 : 0;
                    if (it7 == 0) {
                        z7 = false;
                    } else {
                        i25++;
                    }
                } else {
                    z7 = true;
                }
            }
            if (z7) {
                canScrollForward = true;
            } else {
                canScrollForward = false;
            }
        }
        return new LazyStaggeredGridMeasureResult(currentItemOffsets8, firstItemOffsets3, consumedScroll, MeasureScope.CC.layout$default(scope$iv, layoutWidth, layoutHeight4, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt$measure$1$29
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
            public final void invoke2(Placeable.PlacementScope layout) {
                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                List<LazyStaggeredGridMeasuredItem> list3 = positionedItems;
                LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext2 = $this$measure;
                int size4 = list3.size();
                for (int index$iv4 = 0; index$iv4 < size4; index$iv4++) {
                    Object item$iv = list3.get(index$iv4);
                    LazyStaggeredGridMeasuredItem item3 = (LazyStaggeredGridMeasuredItem) item$iv;
                    item3.place(layout, lazyStaggeredGridMeasureContext2);
                }
            }
        }, 4, null), canScrollForward, canScrollBackward, $this$measure.getIsVertical(), itemCount7, listCalculateVisibleItems, IntSizeKt.IntSize(layoutWidth, layoutHeight4), minOffset3, maxOffset, $this$measure.getBeforeContentPadding(), $this$measure.getAfterContentPadding(), $this$measure.getMainAxisSpacing(), null);
    }

    private static final boolean measure$lambda$38$hasSpaceBeforeFirst(int[] firstItemIndices, int[] firstItemOffsets, LazyStaggeredGridMeasureContext $this_measure) {
        int length = firstItemIndices.length;
        for (int lane = 0; lane < length; lane++) {
            int itemIndex = firstItemIndices[lane];
            int itemOffset = firstItemOffsets[lane];
            if (itemOffset < Math.max(-$this_measure.getMainAxisSpacing(), 0) && itemIndex > 0) {
                return true;
            }
        }
        return false;
    }

    private static final boolean measure$lambda$38$misalignedStart(int[] firstItemIndices, LazyStaggeredGridMeasureContext $this_measure, int[] firstItemOffsets, int referenceLane) {
        int lane = 0;
        int length = firstItemIndices.length;
        while (true) {
            boolean z = false;
            if (lane < length) {
                if (findPreviousItemIndex($this_measure, firstItemIndices[lane], lane) == -1 && firstItemOffsets[lane] != firstItemOffsets[referenceLane]) {
                    z = true;
                }
                boolean misalignedOffsets = z;
                if (misalignedOffsets) {
                    return true;
                }
                lane++;
            } else {
                int length2 = firstItemIndices.length;
                for (int lane2 = 0; lane2 < length2; lane2++) {
                    boolean moreItemsInOtherLanes = findPreviousItemIndex($this_measure, firstItemIndices[lane2], lane2) != -1 && firstItemOffsets[lane2] >= firstItemOffsets[referenceLane];
                    if (moreItemsInOtherLanes) {
                        return true;
                    }
                }
                int firstItemLane = $this_measure.getLaneInfo().getLane(0);
                return (firstItemLane == 0 || firstItemLane == -1 || firstItemLane == -2) ? false : true;
            }
        }
    }

    private static final List<LazyStaggeredGridMeasuredItem> calculateVisibleItems(LazyStaggeredGridMeasureContext $this$calculateVisibleItems, ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr, int[] itemScrollOffsets, int mainAxisLayoutSize) {
        boolean z;
        int size = 0;
        for (ArrayDeque<LazyStaggeredGridMeasuredItem> arrayDeque : arrayDequeArr) {
            size += arrayDeque.size();
        }
        ArrayList positionedItems = new ArrayList(size);
        while (true) {
            int length = arrayDequeArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                z = true;
                if (!arrayDequeArr[i].isEmpty()) {
                    break;
                }
                i++;
            }
            if (!z) {
                return positionedItems;
            }
            int result$iv = -1;
            int min$iv = Integer.MAX_VALUE;
            int length2 = arrayDequeArr.length;
            for (int i$iv = 0; i$iv < length2; i$iv++) {
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemFirstOrNull = arrayDequeArr[i$iv].firstOrNull();
                int value$iv = lazyStaggeredGridMeasuredItemFirstOrNull != null ? lazyStaggeredGridMeasuredItemFirstOrNull.getIndex() : Integer.MAX_VALUE;
                if (min$iv > value$iv) {
                    min$iv = value$iv;
                    result$iv = i$iv;
                }
            }
            int laneIndex = result$iv;
            LazyStaggeredGridMeasuredItem item = arrayDequeArr[laneIndex].removeFirst();
            if (item.getLane() == laneIndex) {
                long spanRange = SpanRange.m688constructorimpl(item.getLane(), item.getSpan());
                int mainAxisOffset = m678maxInRangejy6DScQ(itemScrollOffsets, spanRange);
                int crossAxisOffset = $this$calculateVisibleItems.getResolvedSlots().getPositions()[laneIndex];
                if (item.getPlaceablesCount() != 0) {
                    item.position(mainAxisOffset, crossAxisOffset, mainAxisLayoutSize);
                    positionedItems.add(item);
                    int i$iv2 = (int) (spanRange >> 32);
                    int i2 = (int) (spanRange & 4294967295L);
                    for (int i$iv3 = i$iv2; i$iv3 < i2; i$iv3++) {
                        int lane = i$iv3;
                        itemScrollOffsets[lane] = mainAxisOffset + item.getSizeWithSpacings();
                    }
                }
            }
        }
    }

    private static final List<LazyStaggeredGridMeasuredItem> calculateExtraItems(LazyStaggeredGridMeasureContext $this$calculateExtraItems, Function1<? super LazyStaggeredGridMeasuredItem, Unit> function1, Function1<? super Integer, Boolean> function2) {
        ArrayList arrayList = null;
        List<Integer> pinnedItems = $this$calculateExtraItems.getPinnedItems();
        int size = pinnedItems.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = pinnedItems.get(index$iv);
            int index = ((Number) item$iv).intValue();
            if (function2.invoke(Integer.valueOf(index)).booleanValue()) {
                long spanRange = $this$calculateExtraItems.m675getSpanRangelOCCd4c($this$calculateExtraItems.getItemProvider(), index, 0);
                if (arrayList == null) {
                    Object result = new ArrayList();
                    arrayList = (List) result;
                }
                LazyStaggeredGridMeasuredItem measuredItem = $this$calculateExtraItems.getMeasuredItemProvider().m683getAndMeasurejy6DScQ(index, spanRange);
                function1.invoke(measuredItem);
                arrayList.add(measuredItem);
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    /* JADX INFO: renamed from: forEach-nIS5qE8, reason: not valid java name */
    private static final void m677forEachnIS5qE8(long $this$forEach_u2dnIS5qE8, Function1<? super Integer, Unit> function1) {
        int i = (int) (4294967295L & $this$forEach_u2dnIS5qE8);
        for (int i2 = (int) ($this$forEach_u2dnIS5qE8 >> 32); i2 < i; i2++) {
            function1.invoke(Integer.valueOf(i2));
        }
    }

    private static final void offsetBy(int[] $this$offsetBy, int delta) {
        int length = $this$offsetBy.length;
        for (int i = 0; i < length; i++) {
            $this$offsetBy[i] = $this$offsetBy[i] + delta;
        }
    }

    /* JADX INFO: renamed from: maxInRange-jy6DScQ, reason: not valid java name */
    private static final int m678maxInRangejy6DScQ(int[] $this$maxInRange_u2djy6DScQ, long indexRange) {
        int max = Integer.MIN_VALUE;
        int i = (int) (4294967295L & indexRange);
        for (int i$iv = (int) (indexRange >> 32); i$iv < i; i$iv++) {
            int it = i$iv;
            max = Math.max(max, $this$maxInRange_u2djy6DScQ[it]);
        }
        return max;
    }

    public static /* synthetic */ int indexOfMinValue$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Integer.MIN_VALUE;
        }
        return indexOfMinValue(iArr, i);
    }

    public static final int indexOfMinValue(int[] $this$indexOfMinValue, int minBound) {
        Intrinsics.checkNotNullParameter($this$indexOfMinValue, "<this>");
        int result = -1;
        int min = Integer.MAX_VALUE;
        int length = $this$indexOfMinValue.length;
        for (int i = 0; i < length; i++) {
            int i2 = minBound + 1;
            int i3 = $this$indexOfMinValue[i];
            boolean z = false;
            if (i2 <= i3 && i3 < min) {
                z = true;
            }
            if (z) {
                min = $this$indexOfMinValue[i];
                result = i;
            }
        }
        return result;
    }

    private static final <T> int indexOfMinBy(T[] tArr, Function1<? super T, Integer> function1) {
        int result = -1;
        int min = Integer.MAX_VALUE;
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            int value = function1.invoke(tArr[i]).intValue();
            if (min > value) {
                min = value;
                result = i;
            }
        }
        return result;
    }

    private static final int indexOfMaxValue(int[] $this$indexOfMaxValue) {
        int result = -1;
        int max = Integer.MIN_VALUE;
        int length = $this$indexOfMaxValue.length;
        for (int i = 0; i < length; i++) {
            if (max < $this$indexOfMaxValue[i]) {
                max = $this$indexOfMaxValue[i];
                result = i;
            }
        }
        return result;
    }

    private static final int[] transform(int[] $this$transform, Function1<? super Integer, Integer> function1) {
        int length = $this$transform.length;
        for (int i = 0; i < length; i++) {
            $this$transform[i] = function1.invoke(Integer.valueOf($this$transform[i])).intValue();
        }
        return $this$transform;
    }

    private static final void ensureIndicesInRange(LazyStaggeredGridMeasureContext $this$ensureIndicesInRange, int[] indices, int itemCount) {
        int length = indices.length - 1;
        if (length >= 0) {
            do {
                int i = length;
                length--;
                while (true) {
                    if (indices[i] < itemCount && $this$ensureIndicesInRange.getLaneInfo().assignedToLane(indices[i], i)) {
                        break;
                    } else {
                        indices[i] = findPreviousItemIndex($this$ensureIndicesInRange, indices[i], i);
                    }
                }
                if (indices[i] >= 0 && !$this$ensureIndicesInRange.isFullSpan($this$ensureIndicesInRange.getItemProvider(), indices[i])) {
                    $this$ensureIndicesInRange.getLaneInfo().setLane(indices[i], i);
                }
            } while (length >= 0);
        }
    }

    private static final int findPreviousItemIndex(LazyStaggeredGridMeasureContext $this$findPreviousItemIndex, int item, int lane) {
        return $this$findPreviousItemIndex.getLaneInfo().findPreviousItemIndex(item, lane);
    }
}
