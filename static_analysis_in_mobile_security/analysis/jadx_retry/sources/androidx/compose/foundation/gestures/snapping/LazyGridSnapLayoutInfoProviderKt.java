package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.SplineBasedDecayKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo;
import androidx.compose.foundation.lazy.grid.LazyGridLayoutInfo;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LazyGridSnapLayoutInfoProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a\u0014\u0010\u000b\u001a\u00020\u0001*\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0010"}, d2 = {"singleAxisViewportSize", "", "Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "getSingleAxisViewportSize", "(Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;)I", "SnapLayoutInfoProvider", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "lazyGridState", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "positionInLayout", "Landroidx/compose/foundation/gestures/snapping/SnapPositionInLayout;", "offsetOnMainAxis", "Landroidx/compose/foundation/lazy/grid/LazyGridItemInfo;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "sizeOnMainAxis", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyGridSnapLayoutInfoProviderKt {
    public static /* synthetic */ SnapLayoutInfoProvider SnapLayoutInfoProvider$default(LazyGridState lazyGridState, SnapPositionInLayout snapPositionInLayout, int i, Object obj) {
        if ((i & 2) != 0) {
            snapPositionInLayout = SnapPositionInLayout.INSTANCE.getCenterToCenter();
        }
        return SnapLayoutInfoProvider(lazyGridState, snapPositionInLayout);
    }

    public static final SnapLayoutInfoProvider SnapLayoutInfoProvider(final LazyGridState lazyGridState, final SnapPositionInLayout positionInLayout) {
        Intrinsics.checkNotNullParameter(lazyGridState, "lazyGridState");
        Intrinsics.checkNotNullParameter(positionInLayout, "positionInLayout");
        return new SnapLayoutInfoProvider() { // from class: androidx.compose.foundation.gestures.snapping.LazyGridSnapLayoutInfoProviderKt.SnapLayoutInfoProvider.1
            private final LazyGridLayoutInfo getLayoutInfo() {
                return lazyGridState.getLayoutInfo();
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateApproachOffset(Density $this$calculateApproachOffset, float initialVelocity) {
                Intrinsics.checkNotNullParameter($this$calculateApproachOffset, "<this>");
                DecayAnimationSpec decayAnimationSpec = SplineBasedDecayKt.splineBasedDecay($this$calculateApproachOffset);
                float offset = Math.abs(DecayAnimationSpecKt.calculateTargetValue(decayAnimationSpec, 0.0f, initialVelocity));
                float finalDecayOffset = RangesKt.coerceAtLeast(offset - calculateSnapStepSize($this$calculateApproachOffset), 0.0f);
                if (finalDecayOffset == 0.0f) {
                    return finalDecayOffset;
                }
                return Math.signum(initialVelocity) * finalDecayOffset;
            }

            private final List<LazyGridItemInfo> getSingleAxisItems() {
                List<LazyGridItemInfo> visibleItemsInfo = lazyGridState.getLayoutInfo().getVisibleItemsInfo();
                LazyGridState lazyGridState2 = lazyGridState;
                List target$iv = new ArrayList(visibleItemsInfo.size());
                int size = visibleItemsInfo.size();
                for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                    LazyGridItemInfo lazyGridItemInfo = visibleItemsInfo.get(index$iv$iv);
                    LazyGridItemInfo it = lazyGridItemInfo;
                    boolean z = false;
                    if (lazyGridState2.getLayoutInfo().getOrientation() == Orientation.Horizontal) {
                        if (it.getRow() == 0) {
                            z = true;
                        }
                    } else if (it.getColumn() == 0) {
                        z = true;
                    }
                    if (z) {
                        target$iv.add(lazyGridItemInfo);
                    }
                }
                List $this$fastFilter$iv = target$iv;
                return $this$fastFilter$iv;
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateSnappingOffset(Density $this$calculateSnappingOffset, float currentVelocity) {
                Intrinsics.checkNotNullParameter($this$calculateSnappingOffset, "<this>");
                float distanceFromItemBeforeTarget = Float.NEGATIVE_INFINITY;
                List<LazyGridItemInfo> visibleItemsInfo = getLayoutInfo().getVisibleItemsInfo();
                SnapPositionInLayout snapPositionInLayout = positionInLayout;
                int size = visibleItemsInfo.size();
                float distanceFromItemAfterTarget = Float.POSITIVE_INFINITY;
                int index$iv = 0;
                while (index$iv < size) {
                    Object item$iv = visibleItemsInfo.get(index$iv);
                    LazyGridItemInfo item = (LazyGridItemInfo) item$iv;
                    int singleAxisViewportSize = LazyGridSnapLayoutInfoProviderKt.getSingleAxisViewportSize(getLayoutInfo());
                    int beforeContentPadding = getLayoutInfo().getBeforeContentPadding();
                    int afterContentPadding = getLayoutInfo().getAfterContentPadding();
                    int iSizeOnMainAxis = LazyGridSnapLayoutInfoProviderKt.sizeOnMainAxis(item, getLayoutInfo().getOrientation());
                    int iOffsetOnMainAxis = LazyGridSnapLayoutInfoProviderKt.offsetOnMainAxis(item, getLayoutInfo().getOrientation());
                    int index$iv2 = item.getIndex();
                    int index$iv3 = index$iv;
                    float distance = SnapPositionInLayoutKt.calculateDistanceToDesiredSnapPosition($this$calculateSnappingOffset, singleAxisViewportSize, beforeContentPadding, afterContentPadding, iSizeOnMainAxis, iOffsetOnMainAxis, index$iv2, snapPositionInLayout);
                    if (distance <= 0.0f && distance > distanceFromItemBeforeTarget) {
                        distanceFromItemBeforeTarget = distance;
                    }
                    if (distance >= 0.0f && distance < distanceFromItemAfterTarget) {
                        distanceFromItemAfterTarget = distance;
                    }
                    index$iv = index$iv3 + 1;
                }
                return SnapFlingBehaviorKt.calculateFinalOffset(currentVelocity, distanceFromItemBeforeTarget, distanceFromItemAfterTarget);
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateSnapStepSize(Density $this$calculateSnapStepSize) {
                int sum$iv;
                Intrinsics.checkNotNullParameter($this$calculateSnapStepSize, "<this>");
                if (!getSingleAxisItems().isEmpty()) {
                    if (getLayoutInfo().getOrientation() == Orientation.Vertical) {
                        List<LazyGridItemInfo> singleAxisItems = getSingleAxisItems();
                        sum$iv = 0;
                        int size = singleAxisItems.size();
                        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                            Object item$iv$iv = singleAxisItems.get(index$iv$iv);
                            LazyGridItemInfo it = (LazyGridItemInfo) item$iv$iv;
                            sum$iv += IntSize.m5433getHeightimpl(it.getSize());
                        }
                    } else {
                        List<LazyGridItemInfo> singleAxisItems2 = getSingleAxisItems();
                        sum$iv = 0;
                        int size2 = singleAxisItems2.size();
                        for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
                            Object item$iv$iv2 = singleAxisItems2.get(index$iv$iv2);
                            LazyGridItemInfo it2 = (LazyGridItemInfo) item$iv$iv2;
                            sum$iv += IntSize.m5434getWidthimpl(it2.getSize());
                        }
                    }
                    int size3 = sum$iv;
                    return size3 / getSingleAxisItems().size();
                }
                return 0.0f;
            }
        };
    }

    public static final int getSingleAxisViewportSize(LazyGridLayoutInfo $this$singleAxisViewportSize) {
        Intrinsics.checkNotNullParameter($this$singleAxisViewportSize, "<this>");
        if ($this$singleAxisViewportSize.getOrientation() == Orientation.Vertical) {
            return IntSize.m5433getHeightimpl($this$singleAxisViewportSize.mo601getViewportSizeYbymL2g());
        }
        return IntSize.m5434getWidthimpl($this$singleAxisViewportSize.mo601getViewportSizeYbymL2g());
    }

    public static final int sizeOnMainAxis(LazyGridItemInfo $this$sizeOnMainAxis, Orientation orientation) {
        Intrinsics.checkNotNullParameter($this$sizeOnMainAxis, "<this>");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        if (orientation == Orientation.Vertical) {
            return IntSize.m5433getHeightimpl($this$sizeOnMainAxis.getSize());
        }
        return IntSize.m5434getWidthimpl($this$sizeOnMainAxis.getSize());
    }

    public static final int offsetOnMainAxis(LazyGridItemInfo $this$offsetOnMainAxis, Orientation orientation) {
        Intrinsics.checkNotNullParameter($this$offsetOnMainAxis, "<this>");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        if (orientation == Orientation.Vertical) {
            return IntOffset.m5393getYimpl($this$offsetOnMainAxis.getOffset());
        }
        return IntOffset.m5392getXimpl($this$offsetOnMainAxis.getOffset());
    }
}
