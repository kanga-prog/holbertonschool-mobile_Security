package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyLayoutAnimateItemModifierNode;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LazyGridMeasuredItem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001Bp\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0010\u00108\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<J:\u0010=\u001a\u00020:2\u0006\u0010>\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010?\u001a\u00020\u00032\u0006\u0010@\u001a\u00020\u00032\b\b\u0002\u0010/\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u0003J.\u0010A\u001a\u00020\u0013*\u00020\u00132\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030CH\u0082\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bD\u0010ER\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010 R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010#\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010%\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u000e\u0010'\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R)\u0010)\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0013@RX\u0096\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010-\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b.\u0010\u0019R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010/\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0019R\u001f\u00101\u001a\u000202X\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010,\u001a\u0004\b3\u0010+R\u0019\u0010\u0012\u001a\u00020\u0013X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010,R\u001b\u00104\u001a\u00020\u0003*\u00020\u00138BX\u0082\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b5\u00106R\u0018\u0010#\u001a\u00020\u0003*\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u00107\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006F"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemInfo;", "index", "", "key", "", "isVertical", "", "crossAxisSize", "mainAxisSpacing", "reverseLayout", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "beforeContentPadding", "afterContentPadding", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "visualOffset", "Landroidx/compose/ui/unit/IntOffset;", "contentType", "(ILjava/lang/Object;ZIIZLandroidx/compose/ui/unit/LayoutDirection;IILjava/util/List;JLjava/lang/Object;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "<set-?>", "column", "getColumn", "()I", "getContentType", "()Ljava/lang/Object;", "crossAxisOffset", "getCrossAxisOffset", "getCrossAxisSize", "getIndex", "()Z", "getKey", "mainAxisLayoutSize", "mainAxisSize", "getMainAxisSize", "mainAxisSizeWithSpacings", "getMainAxisSizeWithSpacings", "maxMainAxisOffset", "minMainAxisOffset", "offset", "getOffset-nOcc-ac", "()J", "J", "placeablesCount", "getPlaceablesCount", "row", "getRow", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "mainAxis", "getMainAxis--gyyYBs", "(J)I", "(Landroidx/compose/ui/layout/Placeable;)I", "getParentData", "place", "", "scope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "position", "mainAxisOffset", "layoutWidth", "layoutHeight", "copy", "mainAxisMap", "Lkotlin/Function1;", "copy-4Tuh3kE", "(JLkotlin/jvm/functions/Function1;)J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyGridMeasuredItem implements LazyGridItemInfo {
    private final int afterContentPadding;
    private final int beforeContentPadding;
    private int column;
    private final Object contentType;
    private final int crossAxisSize;
    private final int index;
    private final boolean isVertical;
    private final Object key;
    private final LayoutDirection layoutDirection;
    private int mainAxisLayoutSize;
    private final int mainAxisSize;
    private final int mainAxisSizeWithSpacings;
    private int maxMainAxisOffset;
    private int minMainAxisOffset;
    private long offset;
    private final List<Placeable> placeables;
    private final boolean reverseLayout;
    private int row;
    private final long size;
    private final long visualOffset;

    public /* synthetic */ LazyGridMeasuredItem(int i, Object obj, boolean z, int i2, int i3, boolean z2, LayoutDirection layoutDirection, int i4, int i5, List list, long j, Object obj2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, obj, z, i2, i3, z2, layoutDirection, i4, i5, list, j, obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private LazyGridMeasuredItem(int index, Object key, boolean isVertical, int crossAxisSize, int mainAxisSpacing, boolean reverseLayout, LayoutDirection layoutDirection, int beforeContentPadding, int afterContentPadding, List<? extends Placeable> placeables, long visualOffset, Object contentType) {
        long jIntSize;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        Intrinsics.checkNotNullParameter(placeables, "placeables");
        this.index = index;
        this.key = key;
        this.isVertical = isVertical;
        this.crossAxisSize = crossAxisSize;
        this.reverseLayout = reverseLayout;
        this.layoutDirection = layoutDirection;
        this.beforeContentPadding = beforeContentPadding;
        this.afterContentPadding = afterContentPadding;
        this.placeables = placeables;
        this.visualOffset = visualOffset;
        this.contentType = contentType;
        this.mainAxisLayoutSize = Integer.MIN_VALUE;
        int maxMainAxis = 0;
        List<Placeable> list = this.placeables;
        int size = list.size();
        int index$iv = 0;
        while (index$iv < size) {
            Object item$iv = list.get(index$iv);
            Placeable it = (Placeable) item$iv;
            int i = size;
            maxMainAxis = Math.max(maxMainAxis, this.isVertical ? it.getHeight() : it.getWidth());
            index$iv++;
            size = i;
        }
        this.mainAxisSize = maxMainAxis;
        this.mainAxisSizeWithSpacings = RangesKt.coerceAtLeast(maxMainAxis + mainAxisSpacing, 0);
        if (this.isVertical) {
            jIntSize = IntSizeKt.IntSize(this.crossAxisSize, maxMainAxis);
        } else {
            jIntSize = IntSizeKt.IntSize(maxMainAxis, this.crossAxisSize);
        }
        this.size = jIntSize;
        this.offset = IntOffset.INSTANCE.m5402getZeronOccac();
        this.row = -1;
        this.column = -1;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public Object getKey() {
        return this.key;
    }

    /* JADX INFO: renamed from: isVertical, reason: from getter */
    public final boolean getIsVertical() {
        return this.isVertical;
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public Object getContentType() {
        return this.contentType;
    }

    public final int getMainAxisSize() {
        return this.mainAxisSize;
    }

    public final int getMainAxisSizeWithSpacings() {
        return this.mainAxisSizeWithSpacings;
    }

    public final int getPlaceablesCount() {
        return this.placeables.size();
    }

    public final Object getParentData(int index) {
        return this.placeables.get(index).getParentData();
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    /* JADX INFO: renamed from: getSize-YbymL2g, reason: from getter */
    public long getSize() {
        return this.size;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    /* JADX INFO: renamed from: getOffset-nOcc-ac, reason: from getter */
    public long getOffset() {
        return this.offset;
    }

    public final int getCrossAxisOffset() {
        return this.isVertical ? IntOffset.m5392getXimpl(getOffset()) : IntOffset.m5393getYimpl(getOffset());
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public int getRow() {
        return this.row;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public int getColumn() {
        return this.column;
    }

    public static /* synthetic */ void position$default(LazyGridMeasuredItem lazyGridMeasuredItem, int i, int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
        int i8;
        int i9;
        if ((i7 & 16) == 0) {
            i8 = i5;
        } else {
            i8 = -1;
        }
        if ((i7 & 32) == 0) {
            i9 = i6;
        } else {
            i9 = -1;
        }
        lazyGridMeasuredItem.position(i, i2, i3, i4, i8, i9);
    }

    public final void position(int mainAxisOffset, int crossAxisOffset, int layoutWidth, int layoutHeight, int row, int column) {
        int crossAxisOffset2;
        long jIntOffset;
        boolean z = this.isVertical;
        this.mainAxisLayoutSize = z ? layoutHeight : layoutWidth;
        int crossAxisLayoutSize = z ? layoutWidth : layoutHeight;
        if (z && this.layoutDirection == LayoutDirection.Rtl) {
            crossAxisOffset2 = (crossAxisLayoutSize - crossAxisOffset) - this.crossAxisSize;
        } else {
            crossAxisOffset2 = crossAxisOffset;
        }
        if (this.isVertical) {
            jIntOffset = IntOffsetKt.IntOffset(crossAxisOffset2, mainAxisOffset);
        } else {
            jIntOffset = IntOffsetKt.IntOffset(mainAxisOffset, crossAxisOffset2);
        }
        this.offset = jIntOffset;
        this.row = row;
        this.column = column;
        this.minMainAxisOffset = -this.beforeContentPadding;
        this.maxMainAxisOffset = this.mainAxisLayoutSize + this.afterContentPadding;
    }

    public final void place(Placeable.PlacementScope scope) {
        int mainAxisOffset;
        int iM5393getYimpl;
        Intrinsics.checkNotNullParameter(scope, "scope");
        int i = 0;
        if (!(this.mainAxisLayoutSize != Integer.MIN_VALUE)) {
            throw new IllegalArgumentException("position() should be called first".toString());
        }
        int placeablesCount = getPlaceablesCount();
        int i2 = 0;
        while (i2 < placeablesCount) {
            int index = i2;
            Placeable placeable = this.placeables.get(index);
            int minOffset = this.minMainAxisOffset - getMainAxisSize(placeable);
            int maxOffset = this.maxMainAxisOffset;
            long offset = getOffset();
            Object parentData = getParentData(index);
            LazyLayoutAnimateItemModifierNode animateNode = parentData instanceof LazyLayoutAnimateItemModifierNode ? (LazyLayoutAnimateItemModifierNode) parentData : null;
            if (animateNode != null) {
                long other$iv = animateNode.m632getPlacementDeltanOccac();
                long animatedOffset = IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(offset) + IntOffset.m5392getXimpl(other$iv), IntOffset.m5393getYimpl(offset) + IntOffset.m5393getYimpl(other$iv));
                if ((m624getMainAxisgyyYBs(offset) <= minOffset && m624getMainAxisgyyYBs(animatedOffset) <= minOffset) || (m624getMainAxisgyyYBs(offset) >= maxOffset && m624getMainAxisgyyYBs(animatedOffset) >= maxOffset)) {
                    animateNode.cancelAnimation();
                }
                offset = animatedOffset;
            }
            if (this.reverseLayout) {
                long $this$copy_u2d4Tuh3kE$iv = offset;
                if (this.isVertical) {
                    mainAxisOffset = IntOffset.m5392getXimpl($this$copy_u2d4Tuh3kE$iv);
                } else {
                    int mainAxisOffset2 = IntOffset.m5392getXimpl($this$copy_u2d4Tuh3kE$iv);
                    mainAxisOffset = (this.mainAxisLayoutSize - mainAxisOffset2) - getMainAxisSize(placeable);
                }
                if (this.isVertical) {
                    int mainAxisOffset3 = IntOffset.m5393getYimpl($this$copy_u2d4Tuh3kE$iv);
                    iM5393getYimpl = (this.mainAxisLayoutSize - mainAxisOffset3) - getMainAxisSize(placeable);
                } else {
                    iM5393getYimpl = IntOffset.m5393getYimpl($this$copy_u2d4Tuh3kE$iv);
                }
                offset = IntOffsetKt.IntOffset(mainAxisOffset, iM5393getYimpl);
            }
            long other$iv2 = this.visualOffset;
            long other$iv3 = IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(offset) + IntOffset.m5392getXimpl(other$iv2), IntOffset.m5393getYimpl(offset) + IntOffset.m5393getYimpl(other$iv2));
            if (this.isVertical) {
                Placeable.PlacementScope.m4287placeWithLayeraW9wM$default(scope, placeable, other$iv3, 0.0f, null, 6, null);
            } else {
                Placeable.PlacementScope.m4286placeRelativeWithLayeraW9wM$default(scope, placeable, other$iv3, 0.0f, null, 6, null);
            }
            i2++;
            i = i;
        }
    }

    /* JADX INFO: renamed from: getMainAxis--gyyYBs, reason: not valid java name */
    private final int m624getMainAxisgyyYBs(long $this$mainAxis) {
        return this.isVertical ? IntOffset.m5393getYimpl($this$mainAxis) : IntOffset.m5392getXimpl($this$mainAxis);
    }

    private final int getMainAxisSize(Placeable $this$mainAxisSize) {
        return this.isVertical ? $this$mainAxisSize.getHeight() : $this$mainAxisSize.getWidth();
    }

    /* JADX INFO: renamed from: copy-4Tuh3kE, reason: not valid java name */
    private final long m623copy4Tuh3kE(long $this$copy_u2d4Tuh3kE, Function1<? super Integer, Integer> function1) {
        return IntOffsetKt.IntOffset(this.isVertical ? IntOffset.m5392getXimpl($this$copy_u2d4Tuh3kE) : function1.invoke(Integer.valueOf(IntOffset.m5392getXimpl($this$copy_u2d4Tuh3kE))).intValue(), this.isVertical ? function1.invoke(Integer.valueOf(IntOffset.m5393getYimpl($this$copy_u2d4Tuh3kE))).intValue() : IntOffset.m5393getYimpl($this$copy_u2d4Tuh3kE));
    }
}
