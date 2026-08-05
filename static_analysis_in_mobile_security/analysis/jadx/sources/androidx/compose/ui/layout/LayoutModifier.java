package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LayoutModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u001c\u0010\b\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0003H\u0016J)\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0005\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001c\u0010\u0012\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u001c\u0010\u0013\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0003H\u0016ø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/layout/LayoutModifier;", "Landroidx/compose/ui/Modifier$Element;", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface LayoutModifier extends Modifier.Element {
    int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i);

    int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i);

    /* JADX INFO: renamed from: measure-3p2s80s */
    MeasureResult mo37measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j);

    int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i);

    int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i);

    /* JADX INFO: renamed from: androidx.compose.ui.layout.LayoutModifier$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: LayoutModifier.kt */
    public final /* synthetic */ class CC {
        public static int $default$minIntrinsicWidth(LayoutModifier _this, IntrinsicMeasureScope $this$minIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
            Intrinsics.checkNotNullParameter($this$minIntrinsicWidth, "<this>");
            Intrinsics.checkNotNullParameter(measurable, "measurable");
            return MeasuringIntrinsics.INSTANCE.minWidth(_this, $this$minIntrinsicWidth, measurable, height);
        }

        public static int $default$minIntrinsicHeight(LayoutModifier _this, IntrinsicMeasureScope $this$minIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
            Intrinsics.checkNotNullParameter($this$minIntrinsicHeight, "<this>");
            Intrinsics.checkNotNullParameter(measurable, "measurable");
            return MeasuringIntrinsics.INSTANCE.minHeight(_this, $this$minIntrinsicHeight, measurable, width);
        }

        public static int $default$maxIntrinsicWidth(LayoutModifier _this, IntrinsicMeasureScope $this$maxIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
            Intrinsics.checkNotNullParameter($this$maxIntrinsicWidth, "<this>");
            Intrinsics.checkNotNullParameter(measurable, "measurable");
            return MeasuringIntrinsics.INSTANCE.maxWidth(_this, $this$maxIntrinsicWidth, measurable, height);
        }

        public static int $default$maxIntrinsicHeight(LayoutModifier _this, IntrinsicMeasureScope $this$maxIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
            Intrinsics.checkNotNullParameter($this$maxIntrinsicHeight, "<this>");
            Intrinsics.checkNotNullParameter(measurable, "measurable");
            return MeasuringIntrinsics.INSTANCE.maxHeight(_this, $this$maxIntrinsicHeight, measurable, width);
        }
    }

    /* JADX INFO: compiled from: LayoutModifier.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean all(LayoutModifier $this, Function1<? super Modifier.Element, Boolean> predicate) {
            Intrinsics.checkNotNullParameter(predicate, "predicate");
            return Modifier.Element.CC.$default$all($this, predicate);
        }

        @Deprecated
        public static boolean any(LayoutModifier $this, Function1<? super Modifier.Element, Boolean> predicate) {
            Intrinsics.checkNotNullParameter(predicate, "predicate");
            return Modifier.Element.CC.$default$any($this, predicate);
        }

        @Deprecated
        public static <R> R foldIn(LayoutModifier layoutModifier, R r, Function2<? super R, ? super Modifier.Element, ? extends R> operation) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            return (R) Modifier.Element.CC.$default$foldIn(layoutModifier, r, operation);
        }

        @Deprecated
        public static <R> R foldOut(LayoutModifier layoutModifier, R r, Function2<? super Modifier.Element, ? super R, ? extends R> operation) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            return (R) Modifier.Element.CC.$default$foldOut(layoutModifier, r, operation);
        }

        @Deprecated
        public static Modifier then(LayoutModifier $this, Modifier other) {
            Intrinsics.checkNotNullParameter(other, "other");
            return Modifier.CC.$default$then($this, other);
        }

        @Deprecated
        public static int minIntrinsicWidth(LayoutModifier $this, IntrinsicMeasureScope receiver, IntrinsicMeasurable measurable, int height) {
            Intrinsics.checkNotNullParameter(receiver, "$receiver");
            Intrinsics.checkNotNullParameter(measurable, "measurable");
            return CC.$default$minIntrinsicWidth($this, receiver, measurable, height);
        }

        @Deprecated
        public static int minIntrinsicHeight(LayoutModifier $this, IntrinsicMeasureScope receiver, IntrinsicMeasurable measurable, int width) {
            Intrinsics.checkNotNullParameter(receiver, "$receiver");
            Intrinsics.checkNotNullParameter(measurable, "measurable");
            return CC.$default$minIntrinsicHeight($this, receiver, measurable, width);
        }

        @Deprecated
        public static int maxIntrinsicWidth(LayoutModifier $this, IntrinsicMeasureScope receiver, IntrinsicMeasurable measurable, int height) {
            Intrinsics.checkNotNullParameter(receiver, "$receiver");
            Intrinsics.checkNotNullParameter(measurable, "measurable");
            return CC.$default$maxIntrinsicWidth($this, receiver, measurable, height);
        }

        @Deprecated
        public static int maxIntrinsicHeight(LayoutModifier $this, IntrinsicMeasureScope receiver, IntrinsicMeasurable measurable, int width) {
            Intrinsics.checkNotNullParameter(receiver, "$receiver");
            Intrinsics.checkNotNullParameter(measurable, "measurable");
            return CC.$default$maxIntrinsicHeight($this, receiver, measurable, width);
        }
    }
}
