package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
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

/* JADX INFO: compiled from: OutlinedTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B4\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000bø\u0001\u0000¢\u0006\u0002\u0010\fJ<\u0010\r\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u000e2\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u0015H\u0002J<\u0010\u0016\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u000e2\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u0015H\u0002J\"\u0010\u0018\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\"\u0010\u0019\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J/\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00112\u0006\u0010\u001e\u001a\u00020\u001fH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010!J\"\u0010\"\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\"\u0010#\u001a\u00020\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u000eH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Landroidx/compose/material3/OutlinedTextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "onLabelMeasured", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Size;", "", "singleLine", "", "animationProgress", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(Lkotlin/jvm/functions/Function1;ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "intrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicWidth", "height", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class OutlinedTextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final Function1<Size, Unit> onLabelMeasured;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    /* JADX WARN: Multi-variable type inference failed */
    public OutlinedTextFieldMeasurePolicy(Function1<? super Size, Unit> onLabelMeasured, boolean singleLine, float animationProgress, PaddingValues paddingValues) {
        Intrinsics.checkNotNullParameter(onLabelMeasured, "onLabelMeasured");
        Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
        this.onLabelMeasured = onLabelMeasured;
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
        long textConstraints;
        Object next5;
        int occupiedSpaceVertically;
        long relaxedConstraints;
        Object next6;
        Measurable it;
        Measurable it2;
        Measurable it3;
        Iterable measurables = list;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        int bottomPadding = measure.mo321roundToPx0680j_4(this.paddingValues.getBottom());
        long relaxedConstraints2 = Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0);
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
        final Placeable leadingPlaceable = measurable != null ? measurable.mo4225measureBRTryo0(relaxedConstraints2) : null;
        int occupiedSpaceHorizontally = 0 + TextFieldImplKt.widthOrZero(leadingPlaceable);
        int occupiedSpaceVertically2 = Math.max(0, TextFieldImplKt.heightOrZero(leadingPlaceable));
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
        final Placeable trailingPlaceable = measurable2 != null ? measurable2.mo4225measureBRTryo0(ConstraintsKt.m5235offsetNN6EwU$default(relaxedConstraints2, -occupiedSpaceHorizontally, 0, 2, null)) : null;
        int occupiedSpaceHorizontally2 = occupiedSpaceHorizontally + TextFieldImplKt.widthOrZero(trailingPlaceable);
        int occupiedSpaceVertically3 = Math.max(occupiedSpaceVertically2, TextFieldImplKt.heightOrZero(trailingPlaceable));
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
        final Placeable prefixPlaceable = measurable3 != null ? measurable3.mo4225measureBRTryo0(ConstraintsKt.m5235offsetNN6EwU$default(relaxedConstraints2, -occupiedSpaceHorizontally2, 0, 2, null)) : null;
        int occupiedSpaceHorizontally3 = occupiedSpaceHorizontally2 + TextFieldImplKt.widthOrZero(prefixPlaceable);
        int occupiedSpaceVertically4 = Math.max(occupiedSpaceVertically3, TextFieldImplKt.heightOrZero(prefixPlaceable));
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
        final Placeable suffixPlaceable = measurable4 != null ? measurable4.mo4225measureBRTryo0(ConstraintsKt.m5235offsetNN6EwU$default(relaxedConstraints2, -occupiedSpaceHorizontally3, 0, 2, null)) : null;
        int occupiedSpaceHorizontally4 = occupiedSpaceHorizontally3 + TextFieldImplKt.widthOrZero(suffixPlaceable);
        int occupiedSpaceVertically5 = Math.max(occupiedSpaceVertically4, TextFieldImplKt.heightOrZero(suffixPlaceable));
        boolean isLabelInMiddleSection = this.animationProgress < 1.0f;
        int labelHorizontalPaddingOffset = measure.mo321roundToPx0680j_4(this.paddingValues.mo437calculateLeftPaddingu2uoSUM(measure.getLayoutDirection())) + measure.mo321roundToPx0680j_4(this.paddingValues.mo438calculateRightPaddingu2uoSUM(measure.getLayoutDirection()));
        int width = labelHorizontalPaddingOffset;
        long labelConstraints = ConstraintsKt.m5234offsetNN6EwU(relaxedConstraints2, isLabelInMiddleSection ? (-occupiedSpaceHorizontally4) - labelHorizontalPaddingOffset : -labelHorizontalPaddingOffset, -bottomPadding);
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
        if (labelPlaceable != null) {
            this.onLabelMeasured.invoke(Size.m2788boximpl(SizeKt.Size(labelPlaceable.getWidth(), labelPlaceable.getHeight())));
        }
        int topPadding = Math.max(TextFieldImplKt.heightOrZero(labelPlaceable) / 2, measure.mo321roundToPx0680j_4(this.paddingValues.getTop()));
        long jM5234offsetNN6EwU = ConstraintsKt.m5234offsetNN6EwU(constraints, -occupiedSpaceHorizontally4, (-bottomPadding) - topPadding);
        long textConstraints2 = Constraints.m5208copyZbe2FdA(jM5234offsetNN6EwU, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(jM5234offsetNN6EwU) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(jM5234offsetNN6EwU) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(jM5234offsetNN6EwU) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(jM5234offsetNN6EwU) : 0);
        Iterable $this$first$iv = measurables;
        int $i$f$first = 0;
        Iterator it13 = $this$first$iv.iterator();
        while (true) {
            int occupiedSpaceHorizontally5 = occupiedSpaceHorizontally4;
            if (!it13.hasNext()) {
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            Object element$iv = it13.next();
            Measurable it14 = (Measurable) element$iv;
            Iterable $this$first$iv2 = $this$first$iv;
            int $i$f$first2 = $i$f$first;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it14), "TextField")) {
                final Placeable textFieldPlaceable = ((Measurable) element$iv).mo4225measureBRTryo0(textConstraints2);
                long placeholderConstraints = Constraints.m5208copyZbe2FdA(textConstraints2, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(textConstraints2) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(textConstraints2) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(textConstraints2) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(textConstraints2) : 0);
                Iterator<T> it15 = measurables.iterator();
                while (true) {
                    if (!it15.hasNext()) {
                        textConstraints = textConstraints2;
                        next5 = null;
                        break;
                    }
                    next5 = it15.next();
                    Measurable it16 = (Measurable) next5;
                    textConstraints = textConstraints2;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it16), "Hint")) {
                        break;
                    }
                    textConstraints2 = textConstraints;
                }
                Measurable measurable6 = (Measurable) next5;
                final Placeable placeholderPlaceable = measurable6 != null ? measurable6.mo4225measureBRTryo0(placeholderConstraints) : null;
                int occupiedSpaceVertically6 = Math.max(occupiedSpaceVertically5, Math.max(TextFieldImplKt.heightOrZero(textFieldPlaceable), TextFieldImplKt.heightOrZero(placeholderPlaceable)) + topPadding + bottomPadding);
                long jM5235offsetNN6EwU$default = ConstraintsKt.m5235offsetNN6EwU$default(relaxedConstraints2, 0, -occupiedSpaceVertically6, 1, null);
                long supportingConstraints = Constraints.m5208copyZbe2FdA(jM5235offsetNN6EwU$default, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(jM5235offsetNN6EwU$default) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(jM5235offsetNN6EwU$default) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(jM5235offsetNN6EwU$default) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(jM5235offsetNN6EwU$default) : 0);
                Iterator<T> it17 = measurables.iterator();
                while (true) {
                    if (!it17.hasNext()) {
                        occupiedSpaceVertically = occupiedSpaceVertically6;
                        relaxedConstraints = relaxedConstraints2;
                        next6 = null;
                        break;
                    }
                    next6 = it17.next();
                    Measurable it18 = (Measurable) next6;
                    occupiedSpaceVertically = occupiedSpaceVertically6;
                    relaxedConstraints = relaxedConstraints2;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it18), TextFieldImplKt.SupportingId)) {
                        break;
                    }
                    occupiedSpaceVertically6 = occupiedSpaceVertically;
                    relaxedConstraints2 = relaxedConstraints;
                }
                Measurable measurable7 = (Measurable) next6;
                final Placeable supportingPlaceable = measurable7 != null ? measurable7.mo4225measureBRTryo0(supportingConstraints) : null;
                int supportingHeight = TextFieldImplKt.heightOrZero(supportingPlaceable);
                final int width2 = OutlinedTextFieldKt.m1658calculateWidthDHJA7U0(TextFieldImplKt.widthOrZero(leadingPlaceable), TextFieldImplKt.widthOrZero(trailingPlaceable), TextFieldImplKt.widthOrZero(prefixPlaceable), TextFieldImplKt.widthOrZero(suffixPlaceable), textFieldPlaceable.getWidth(), TextFieldImplKt.widthOrZero(labelPlaceable), TextFieldImplKt.widthOrZero(placeholderPlaceable), isLabelInMiddleSection, constraints, measure.getDensity(), this.paddingValues);
                final int totalHeight = OutlinedTextFieldKt.m1657calculateHeightDHJA7U0(TextFieldImplKt.heightOrZero(leadingPlaceable), TextFieldImplKt.heightOrZero(trailingPlaceable), TextFieldImplKt.heightOrZero(prefixPlaceable), TextFieldImplKt.heightOrZero(suffixPlaceable), textFieldPlaceable.getHeight(), TextFieldImplKt.heightOrZero(labelPlaceable), TextFieldImplKt.heightOrZero(placeholderPlaceable), TextFieldImplKt.heightOrZero(supportingPlaceable), constraints, measure.getDensity(), this.paddingValues);
                int height = totalHeight - supportingHeight;
                Iterable $this$first$iv3 = measurables;
                for (Object element$iv2 : $this$first$iv3) {
                    Measurable it19 = (Measurable) element$iv2;
                    Iterable $this$first$iv4 = $this$first$iv3;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it19), TextFieldImplKt.ContainerId)) {
                        final Placeable containerPlaceable = ((Measurable) element$iv2).mo4225measureBRTryo0(ConstraintsKt.Constraints(width2 != Integer.MAX_VALUE ? width2 : 0, width2, height != Integer.MAX_VALUE ? height : 0, height));
                        return MeasureScope.CC.layout$default(measure, width2, totalHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$measure$2
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
                                OutlinedTextFieldKt.place(layout, totalHeight, width2, leadingPlaceable, trailingPlaceable, prefixPlaceable, suffixPlaceable, textFieldPlaceable, labelPlaceable, placeholderPlaceable, containerPlaceable, supportingPlaceable, this.animationProgress, this.singleLine, measure.getDensity(), measure.getLayoutDirection(), this.paddingValues);
                            }
                        }, 4, null);
                    }
                    height = height;
                    $this$first$iv3 = $this$first$iv4;
                    labelConstraints = labelConstraints;
                    width = width;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            measurables = list;
            $this$first$iv = $this$first$iv2;
            $i$f$first = $i$f$first2;
            occupiedSpaceHorizontally4 = occupiedSpaceHorizontally5;
            labelConstraints = labelConstraints;
        }
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> measurables, int width) {
        Intrinsics.checkNotNullParameter($this$maxIntrinsicHeight, "<this>");
        Intrinsics.checkNotNullParameter(measurables, "measurables");
        return intrinsicHeight($this$maxIntrinsicHeight, measurables, width, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy.maxIntrinsicHeight.1
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
        return intrinsicHeight($this$minIntrinsicHeight, measurables, width, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy.minIntrinsicHeight.1
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
        return intrinsicWidth($this$maxIntrinsicWidth, measurables, height, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy.maxIntrinsicWidth.1
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
        return intrinsicWidth($this$minIntrinsicWidth, measurables, height, new Function2<IntrinsicMeasurable, Integer, Integer>() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy.minIntrinsicWidth.1
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

    private final int intrinsicWidth(IntrinsicMeasureScope $this$intrinsicWidth, List<? extends IntrinsicMeasurable> list, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        Object obj;
        Object next;
        Object next2;
        Object next3;
        Object next4;
        Object next5;
        List<? extends IntrinsicMeasurable> $this$first$iv = list;
        for (Object element$iv : $this$first$iv) {
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) element$iv), "TextField")) {
                int textFieldWidth = function2.invoke(element$iv, Integer.valueOf(height)).intValue();
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
                int labelWidth = it2 != null ? function2.invoke(it2, Integer.valueOf(height)).intValue() : 0;
                Iterator<T> it3 = list.iterator();
                do {
                    if (!it3.hasNext()) {
                        next2 = null;
                        break;
                    }
                    next2 = it3.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next2), "Trailing"));
                IntrinsicMeasurable it4 = (IntrinsicMeasurable) next2;
                int trailingWidth = it4 != null ? function2.invoke(it4, Integer.valueOf(height)).intValue() : 0;
                Iterator<T> it5 = list.iterator();
                do {
                    if (!it5.hasNext()) {
                        next3 = null;
                        break;
                    }
                    next3 = it5.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next3), "Leading"));
                IntrinsicMeasurable it6 = (IntrinsicMeasurable) next3;
                int leadingWidth = it6 != null ? function2.invoke(it6, Integer.valueOf(height)).intValue() : 0;
                Iterator<T> it7 = list.iterator();
                do {
                    if (!it7.hasNext()) {
                        next4 = null;
                        break;
                    }
                    next4 = it7.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next4), TextFieldImplKt.PrefixId));
                IntrinsicMeasurable it8 = (IntrinsicMeasurable) next4;
                int prefixWidth = it8 != null ? function2.invoke(it8, Integer.valueOf(height)).intValue() : 0;
                Iterator<T> it9 = list.iterator();
                do {
                    if (!it9.hasNext()) {
                        next5 = null;
                        break;
                    }
                    next5 = it9.next();
                } while (!Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) next5), TextFieldImplKt.SuffixId));
                IntrinsicMeasurable it10 = (IntrinsicMeasurable) next5;
                int suffixWidth = it10 != null ? function2.invoke(it10, Integer.valueOf(height)).intValue() : 0;
                for (Object obj2 : list) {
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj2), "Hint")) {
                        obj = obj2;
                        break;
                    }
                }
                IntrinsicMeasurable it11 = (IntrinsicMeasurable) obj;
                int placeholderWidth = it11 != null ? function2.invoke(it11, Integer.valueOf(height)).intValue() : 0;
                return OutlinedTextFieldKt.m1658calculateWidthDHJA7U0(leadingWidth, trailingWidth, prefixWidth, suffixWidth, textFieldWidth, labelWidth, placeholderWidth, this.animationProgress < 1.0f, TextFieldImplKt.getZeroConstraints(), $this$intrinsicWidth.getDensity(), this.paddingValues);
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
                return OutlinedTextFieldKt.m1657calculateHeightDHJA7U0(leadingHeight, trailingHeight, prefixHeight, suffixHeight, textFieldHeight, labelHeight, placeholderHeight, supportingHeight, TextFieldImplKt.getZeroConstraints(), $this$intrinsicHeight.getDensity(), this.paddingValues);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
