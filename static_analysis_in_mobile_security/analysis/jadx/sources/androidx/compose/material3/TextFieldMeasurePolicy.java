package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ8\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\n2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0002J<\u0010\u0011\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\n2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0002J\"\u0010\u0014\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\"\u0010\u0015\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016J/\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00190\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\"\u0010\u001e\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\"\u0010\u001f\u001a\u00020\n*\u00020\u00122\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006 "}, d2 = {"Landroidx/compose/material3/TextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "singleLine", "", "animationProgress", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "intrinsicWidth", "", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "width", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class TextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    public TextFieldMeasurePolicy(boolean singleLine, float animationProgress, PaddingValues paddingValues) {
        Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
        this.singleLine = singleLine;
        this.animationProgress = animationProgress;
        this.paddingValues = paddingValues;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo11measure3p2s80s(final MeasureScope measure, List<? extends Measurable> list, long constraints) {
        Object next;
        Object next2;
        Object next3;
        Object next4;
        Object obj;
        String str;
        Object next5;
        Object next6;
        long supportingConstraints;
        Placeable placeableMo4225measureBRTryo0;
        Measurable it;
        Measurable it2;
        Measurable it3;
        Iterable measurables = list;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        final int topPaddingValue = measure.mo321roundToPx0680j_4(this.paddingValues.getTop());
        int bottomPaddingValue = measure.mo321roundToPx0680j_4(this.paddingValues.getBottom());
        long looseConstraints = Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0);
        Iterator<T> it4 = measurables.iterator();
        do {
            if (!it4.hasNext()) {
                next = null;
                break;
            }
            next = it4.next();
            it3 = (Measurable) next;
        } while (!Intrinsics.areEqual(LayoutIdKt.getLayoutId(it3), "Leading"));
        Measurable measurable = (Measurable) next;
        final Placeable leadingPlaceable = measurable != null ? measurable.mo4225measureBRTryo0(looseConstraints) : null;
        int occupiedSpaceHorizontally = 0 + TextFieldImplKt.widthOrZero(leadingPlaceable);
        int occupiedSpaceVertically = Math.max(0, TextFieldImplKt.heightOrZero(leadingPlaceable));
        Iterator<T> it5 = measurables.iterator();
        do {
            if (!it5.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it5.next();
            it2 = (Measurable) next2;
        } while (!Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Trailing"));
        Measurable measurable2 = (Measurable) next2;
        final Placeable trailingPlaceable = measurable2 != null ? measurable2.mo4225measureBRTryo0(ConstraintsKt.m5235offsetNN6EwU$default(looseConstraints, -occupiedSpaceHorizontally, 0, 2, null)) : null;
        int occupiedSpaceHorizontally2 = occupiedSpaceHorizontally + TextFieldImplKt.widthOrZero(trailingPlaceable);
        int occupiedSpaceVertically2 = Math.max(occupiedSpaceVertically, TextFieldImplKt.heightOrZero(trailingPlaceable));
        Iterator<T> it6 = measurables.iterator();
        do {
            if (!it6.hasNext()) {
                next3 = null;
                break;
            }
            next3 = it6.next();
            it = (Measurable) next3;
        } while (!Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), TextFieldImplKt.PrefixId));
        Measurable measurable3 = (Measurable) next3;
        final Placeable prefixPlaceable = measurable3 != null ? measurable3.mo4225measureBRTryo0(ConstraintsKt.m5235offsetNN6EwU$default(looseConstraints, -occupiedSpaceHorizontally2, 0, 2, null)) : null;
        int occupiedSpaceHorizontally3 = occupiedSpaceHorizontally2 + TextFieldImplKt.widthOrZero(prefixPlaceable);
        int occupiedSpaceVertically3 = Math.max(occupiedSpaceVertically2, TextFieldImplKt.heightOrZero(prefixPlaceable));
        Iterator it7 = measurables.iterator();
        while (true) {
            if (!it7.hasNext()) {
                next4 = null;
                break;
            }
            next4 = it7.next();
            Measurable it8 = (Measurable) next4;
            Iterator it9 = it7;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it8), TextFieldImplKt.SuffixId)) {
                break;
            }
            it7 = it9;
        }
        Measurable measurable4 = (Measurable) next4;
        final Placeable suffixPlaceable = measurable4 != null ? measurable4.mo4225measureBRTryo0(ConstraintsKt.m5235offsetNN6EwU$default(looseConstraints, -occupiedSpaceHorizontally3, 0, 2, null)) : null;
        int occupiedSpaceHorizontally4 = occupiedSpaceHorizontally3 + TextFieldImplKt.widthOrZero(suffixPlaceable);
        int occupiedSpaceVertically4 = Math.max(occupiedSpaceVertically3, TextFieldImplKt.heightOrZero(suffixPlaceable));
        int occupiedSpaceVertically5 = -bottomPaddingValue;
        long labelConstraints = ConstraintsKt.m5234offsetNN6EwU(looseConstraints, -occupiedSpaceHorizontally4, occupiedSpaceVertically5);
        Iterator it10 = measurables.iterator();
        while (true) {
            if (!it10.hasNext()) {
                obj = null;
                break;
            }
            Object next7 = it10.next();
            Measurable it11 = (Measurable) next7;
            Iterator it12 = it10;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it11), "Label")) {
                obj = next7;
                break;
            }
            it10 = it12;
        }
        Measurable measurable5 = (Measurable) obj;
        final Placeable labelPlaceable = measurable5 != null ? measurable5.mo4225measureBRTryo0(labelConstraints) : null;
        int effectiveTopOffset = topPaddingValue + TextFieldImplKt.heightOrZero(labelPlaceable);
        int verticalConstraintOffset = (-effectiveTopOffset) - bottomPaddingValue;
        long textFieldConstraints = ConstraintsKt.m5234offsetNN6EwU(Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0), -occupiedSpaceHorizontally4, verticalConstraintOffset);
        Iterable $this$first$iv = measurables;
        Iterator it13 = $this$first$iv.iterator();
        while (true) {
            int occupiedSpaceHorizontally5 = occupiedSpaceHorizontally4;
            String str2 = "Collection contains no element matching the predicate.";
            if (!it13.hasNext()) {
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            Object element$iv = it13.next();
            Measurable it14 = (Measurable) element$iv;
            Iterable $this$first$iv2 = $this$first$iv;
            int verticalConstraintOffset2 = verticalConstraintOffset;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it14), "TextField")) {
                final Placeable textFieldPlaceable = ((Measurable) element$iv).mo4225measureBRTryo0(textFieldConstraints);
                long placeholderConstraints = textFieldConstraints;
                long placeholderConstraints2 = Constraints.m5208copyZbe2FdA(textFieldConstraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(textFieldConstraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(textFieldConstraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(textFieldConstraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(textFieldConstraints) : 0);
                Iterator it15 = measurables.iterator();
                while (true) {
                    if (!it15.hasNext()) {
                        str = str2;
                        next5 = null;
                        break;
                    }
                    next5 = it15.next();
                    Measurable it16 = (Measurable) next5;
                    Iterator it17 = it15;
                    str = str2;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it16), "Hint")) {
                        break;
                    }
                    it15 = it17;
                    str2 = str;
                }
                Measurable measurable6 = (Measurable) next5;
                final Placeable placeholderPlaceable = measurable6 != null ? measurable6.mo4225measureBRTryo0(placeholderConstraints2) : null;
                long textFieldConstraints2 = placeholderConstraints2;
                long jM5235offsetNN6EwU$default = ConstraintsKt.m5235offsetNN6EwU$default(looseConstraints, 0, -Math.max(occupiedSpaceVertically4, Math.max(TextFieldImplKt.heightOrZero(textFieldPlaceable), TextFieldImplKt.heightOrZero(placeholderPlaceable)) + effectiveTopOffset + bottomPaddingValue), 1, null);
                long supportingConstraints2 = Constraints.m5208copyZbe2FdA(jM5235offsetNN6EwU$default, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(jM5235offsetNN6EwU$default) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(jM5235offsetNN6EwU$default) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(jM5235offsetNN6EwU$default) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(jM5235offsetNN6EwU$default) : 0);
                Iterator it18 = measurables.iterator();
                while (true) {
                    if (!it18.hasNext()) {
                        next6 = null;
                        break;
                    }
                    next6 = it18.next();
                    Measurable it19 = (Measurable) next6;
                    Iterator it20 = it18;
                    long looseConstraints2 = looseConstraints;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it19), TextFieldImplKt.SupportingId)) {
                        break;
                    }
                    it18 = it20;
                    looseConstraints = looseConstraints2;
                }
                Measurable measurable7 = (Measurable) next6;
                if (measurable7 != null) {
                    supportingConstraints = supportingConstraints2;
                    placeableMo4225measureBRTryo0 = measurable7.mo4225measureBRTryo0(supportingConstraints);
                } else {
                    supportingConstraints = supportingConstraints2;
                    placeableMo4225measureBRTryo0 = null;
                }
                final Placeable supportingPlaceable = placeableMo4225measureBRTryo0;
                int supportingHeight = TextFieldImplKt.heightOrZero(supportingPlaceable);
                final int width = TextFieldKt.m1881calculateWidthyeHjK3Y(TextFieldImplKt.widthOrZero(leadingPlaceable), TextFieldImplKt.widthOrZero(trailingPlaceable), TextFieldImplKt.widthOrZero(prefixPlaceable), TextFieldImplKt.widthOrZero(suffixPlaceable), textFieldPlaceable.getWidth(), TextFieldImplKt.widthOrZero(labelPlaceable), TextFieldImplKt.widthOrZero(placeholderPlaceable), constraints);
                final int totalHeight = TextFieldKt.m1880calculateHeightmKXJcVc(textFieldPlaceable.getHeight(), TextFieldImplKt.heightOrZero(labelPlaceable), TextFieldImplKt.heightOrZero(leadingPlaceable), TextFieldImplKt.heightOrZero(trailingPlaceable), TextFieldImplKt.heightOrZero(prefixPlaceable), TextFieldImplKt.heightOrZero(suffixPlaceable), TextFieldImplKt.heightOrZero(placeholderPlaceable), TextFieldImplKt.heightOrZero(supportingPlaceable), this.animationProgress == 1.0f, constraints, measure.getDensity(), this.paddingValues);
                int height = totalHeight - supportingHeight;
                for (Object element$iv2 : measurables) {
                    Measurable it21 = (Measurable) element$iv2;
                    long supportingConstraints3 = supportingConstraints;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it21), TextFieldImplKt.ContainerId)) {
                        final Placeable containerPlaceable = ((Measurable) element$iv2).mo4225measureBRTryo0(ConstraintsKt.Constraints(width != Integer.MAX_VALUE ? width : 0, width, height != Integer.MAX_VALUE ? height : 0, height));
                        return MeasureScope.CC.layout$default(measure, width, totalHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$measure$1
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
                                Placeable placeable = labelPlaceable;
                                if (placeable == null) {
                                    TextFieldKt.placeWithoutLabel(layout, width, totalHeight, textFieldPlaceable, placeholderPlaceable, leadingPlaceable, trailingPlaceable, prefixPlaceable, suffixPlaceable, containerPlaceable, supportingPlaceable, this.singleLine, measure.getDensity(), this.paddingValues);
                                    return;
                                }
                                int i = width;
                                int i2 = totalHeight;
                                Placeable placeable2 = textFieldPlaceable;
                                Placeable placeable3 = placeholderPlaceable;
                                Placeable placeable4 = leadingPlaceable;
                                Placeable placeable5 = trailingPlaceable;
                                Placeable placeable6 = prefixPlaceable;
                                Placeable placeable7 = suffixPlaceable;
                                Placeable placeable8 = containerPlaceable;
                                Placeable placeable9 = supportingPlaceable;
                                boolean z = this.singleLine;
                                int i3 = topPaddingValue;
                                TextFieldKt.placeWithLabel(layout, i, i2, placeable2, placeable, placeable3, placeable4, placeable5, placeable6, placeable7, placeable8, placeable9, z, i3, i3 + labelPlaceable.getHeight(), this.animationProgress, measure.getDensity());
                            }
                        }, 4, null);
                    }
                    long placeholderConstraints3 = textFieldConstraints2;
                    supportingConstraints = supportingConstraints3;
                    textFieldConstraints2 = placeholderConstraints3;
                    placeholderConstraints = placeholderConstraints;
                }
                throw new NoSuchElementException(str);
            }
            measurables = list;
            $this$first$iv = $this$first$iv2;
            verticalConstraintOffset = verticalConstraintOffset2;
            occupiedSpaceHorizontally4 = occupiedSpaceHorizontally5;
        }
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> measurables, int width) {
        Intrinsics.checkNotNullParameter($this$maxIntrinsicHeight, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicHeight($this$maxIntrinsicHeight, measurables, width, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy.maxIntrinsicHeight.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int w) {
                Intrinsics.checkNotNullParameter(intrinsicMeasurable, "intrinsicMeasurable");
                return Integer.valueOf(intrinsicMeasurable.maxIntrinsicHeight(w));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends IntrinsicMeasurable> measurables, int width) {
        Intrinsics.checkNotNullParameter($this$minIntrinsicHeight, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicHeight($this$minIntrinsicHeight, measurables, width, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy.minIntrinsicHeight.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int w) {
                Intrinsics.checkNotNullParameter(intrinsicMeasurable, "intrinsicMeasurable");
                return Integer.valueOf(intrinsicMeasurable.minIntrinsicHeight(w));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> measurables, int height) {
        Intrinsics.checkNotNullParameter($this$maxIntrinsicWidth, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicWidth(measurables, height, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy.maxIntrinsicWidth.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int h) {
                Intrinsics.checkNotNullParameter(intrinsicMeasurable, "intrinsicMeasurable");
                return Integer.valueOf(intrinsicMeasurable.maxIntrinsicWidth(h));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends IntrinsicMeasurable> measurables, int height) {
        Intrinsics.checkNotNullParameter($this$minIntrinsicWidth, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicWidth(measurables, height, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.TextFieldMeasurePolicy.minIntrinsicWidth.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int h) {
                Intrinsics.checkNotNullParameter(intrinsicMeasurable, "intrinsicMeasurable");
                return Integer.valueOf(intrinsicMeasurable.minIntrinsicWidth(h));
            }
        });
    }

    private final int intrinsicWidth(List<? extends IntrinsicMeasurable> measurables, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> intrinsicMeasurer) {
        Object obj;
        Object next;
        Object next2;
        Object next3;
        Object next4;
        Object next5;
        List<? extends IntrinsicMeasurable> $this$first$iv = measurables;
        for (Object element$iv : $this$first$iv) {
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) element$iv), "TextField")) {
                int textFieldWidth = intrinsicMeasurer.invoke(element$iv, Integer.valueOf(height)).intValue();
                Iterator<T> it = measurables.iterator();
                do {
                    obj = null;
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next), "Label"));
                IntrinsicMeasurable it2 = (IntrinsicMeasurable) next;
                int labelWidth = it2 != null ? intrinsicMeasurer.invoke(it2, Integer.valueOf(height)).intValue() : 0;
                Iterator<T> it3 = measurables.iterator();
                do {
                    if (!it3.hasNext()) {
                        next2 = null;
                        break;
                    }
                    next2 = it3.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next2), "Trailing"));
                IntrinsicMeasurable it4 = (IntrinsicMeasurable) next2;
                int trailingWidth = it4 != null ? intrinsicMeasurer.invoke(it4, Integer.valueOf(height)).intValue() : 0;
                Iterator<T> it5 = measurables.iterator();
                do {
                    if (!it5.hasNext()) {
                        next3 = null;
                        break;
                    }
                    next3 = it5.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next3), TextFieldImplKt.PrefixId));
                IntrinsicMeasurable it6 = (IntrinsicMeasurable) next3;
                int prefixWidth = it6 != null ? intrinsicMeasurer.invoke(it6, Integer.valueOf(height)).intValue() : 0;
                Iterator<T> it7 = measurables.iterator();
                do {
                    if (!it7.hasNext()) {
                        next4 = null;
                        break;
                    }
                    next4 = it7.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next4), TextFieldImplKt.SuffixId));
                IntrinsicMeasurable it8 = (IntrinsicMeasurable) next4;
                int suffixWidth = it8 != null ? intrinsicMeasurer.invoke(it8, Integer.valueOf(height)).intValue() : 0;
                Iterator<T> it9 = measurables.iterator();
                do {
                    if (!it9.hasNext()) {
                        next5 = null;
                        break;
                    }
                    next5 = it9.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next5), "Leading"));
                IntrinsicMeasurable it10 = (IntrinsicMeasurable) next5;
                int leadingWidth = it10 != null ? intrinsicMeasurer.invoke(it10, Integer.valueOf(height)).intValue() : 0;
                for (Object obj2 : measurables) {
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj2), "Hint")) {
                        obj = obj2;
                        break;
                    }
                }
                IntrinsicMeasurable it11 = (IntrinsicMeasurable) obj;
                int placeholderWidth = it11 != null ? intrinsicMeasurer.invoke(it11, Integer.valueOf(height)).intValue() : 0;
                return TextFieldKt.m1881calculateWidthyeHjK3Y(leadingWidth, trailingWidth, prefixWidth, suffixWidth, textFieldWidth, labelWidth, placeholderWidth, TextFieldImplKt.getZeroConstraints());
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final int intrinsicHeight(IntrinsicMeasureScope $this$intrinsicHeight, List<? extends IntrinsicMeasurable> list, int width, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        Object obj;
        Object next;
        Object next2;
        Object next3;
        Object next4;
        Object next5;
        Object next6;
        List<? extends IntrinsicMeasurable> $this$first$iv = list;
        for (Object element$iv : $this$first$iv) {
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) element$iv), "TextField")) {
                int textFieldHeight = function2.invoke(element$iv, Integer.valueOf(width)).intValue();
                Iterator<T> it = list.iterator();
                do {
                    obj = null;
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next), "Label"));
                IntrinsicMeasurable it2 = (IntrinsicMeasurable) next;
                int labelHeight = it2 != null ? function2.invoke(it2, Integer.valueOf(width)).intValue() : 0;
                Iterator<T> it3 = list.iterator();
                do {
                    if (!it3.hasNext()) {
                        next2 = null;
                        break;
                    }
                    next2 = it3.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next2), "Trailing"));
                IntrinsicMeasurable it4 = (IntrinsicMeasurable) next2;
                int trailingHeight = it4 != null ? function2.invoke(it4, Integer.valueOf(width)).intValue() : 0;
                Iterator<T> it5 = list.iterator();
                do {
                    if (!it5.hasNext()) {
                        next3 = null;
                        break;
                    }
                    next3 = it5.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next3), "Leading"));
                IntrinsicMeasurable it6 = (IntrinsicMeasurable) next3;
                int leadingHeight = it6 != null ? function2.invoke(it6, Integer.valueOf(width)).intValue() : 0;
                Iterator<T> it7 = list.iterator();
                do {
                    if (!it7.hasNext()) {
                        next4 = null;
                        break;
                    }
                    next4 = it7.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next4), TextFieldImplKt.PrefixId));
                IntrinsicMeasurable it8 = (IntrinsicMeasurable) next4;
                int prefixHeight = it8 != null ? function2.invoke(it8, Integer.valueOf(width)).intValue() : 0;
                Iterator<T> it9 = list.iterator();
                do {
                    if (!it9.hasNext()) {
                        next5 = null;
                        break;
                    }
                    next5 = it9.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next5), TextFieldImplKt.SuffixId));
                IntrinsicMeasurable it10 = (IntrinsicMeasurable) next5;
                int suffixHeight = it10 != null ? function2.invoke(it10, Integer.valueOf(width)).intValue() : 0;
                Iterator<T> it11 = list.iterator();
                do {
                    if (!it11.hasNext()) {
                        next6 = null;
                        break;
                    }
                    next6 = it11.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next6), "Hint"));
                IntrinsicMeasurable it12 = (IntrinsicMeasurable) next6;
                int placeholderHeight = it12 != null ? function2.invoke(it12, Integer.valueOf(width)).intValue() : 0;
                for (Object obj2 : list) {
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj2), TextFieldImplKt.SupportingId)) {
                        obj = obj2;
                        break;
                    }
                }
                IntrinsicMeasurable it13 = (IntrinsicMeasurable) obj;
                int supportingHeight = it13 != null ? function2.invoke(it13, Integer.valueOf(width)).intValue() : 0;
                return TextFieldKt.m1880calculateHeightmKXJcVc(textFieldHeight, labelHeight, leadingHeight, trailingHeight, prefixHeight, suffixHeight, placeholderHeight, supportingHeight, this.animationProgress == 1.0f, TextFieldImplKt.getZeroConstraints(), $this$intrinsicHeight.getDensity(), this.paddingValues);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
