package androidx.compose.ui.node;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MyersDiff.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0083@\u0018\u00002\u00020\u0001B\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f¢\u0006\u0004\b \u0010!J\u001a\u0010\"\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b'\u0010\u000bJ\u000f\u0010(\u001a\u00020)H\u0016¢\u0006\u0004\b*\u0010+R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000bR\u0011\u0010\u001a\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u000b\u0088\u0001\u0002ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Landroidx/compose/ui/node/Snake;", "", "data", "", "constructor-impl", "([I)[I", "getData", "()[I", "diagonalSize", "", "getDiagonalSize-impl", "([I)I", "endX", "getEndX-impl", "endY", "getEndY-impl", "hasAdditionOrRemoval", "", "getHasAdditionOrRemoval-impl", "([I)Z", "isAddition", "isAddition-impl", "reverse", "getReverse-impl", "startX", "getStartX-impl", "startY", "getStartY-impl", "addDiagonalToStack", "", "diagonals", "Landroidx/compose/ui/node/IntStack;", "addDiagonalToStack-impl", "([ILandroidx/compose/ui/node/IntStack;)V", "equals", "other", "equals-impl", "([ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "([I)Ljava/lang/String;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
final class Snake {
    private final int[] data;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Snake m4501boximpl(int[] iArr) {
        return new Snake(iArr);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int[] m4502constructorimpl(int[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return data;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4503equalsimpl(int[] iArr, Object obj) {
        return (obj instanceof Snake) && Intrinsics.areEqual(iArr, ((Snake) obj).m4515unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4504equalsimpl0(int[] iArr, int[] iArr2) {
        return Intrinsics.areEqual(iArr, iArr2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4512hashCodeimpl(int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    public boolean equals(Object obj) {
        return m4503equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m4512hashCodeimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int[] m4515unboximpl() {
        return this.data;
    }

    private /* synthetic */ Snake(int[] data) {
        this.data = data;
    }

    public final int[] getData() {
        return this.data;
    }

    /* JADX INFO: renamed from: getStartX-impl, reason: not valid java name */
    public static final int m4510getStartXimpl(int[] arg0) {
        return arg0[0];
    }

    /* JADX INFO: renamed from: getStartY-impl, reason: not valid java name */
    public static final int m4511getStartYimpl(int[] arg0) {
        return arg0[1];
    }

    /* JADX INFO: renamed from: getEndX-impl, reason: not valid java name */
    public static final int m4506getEndXimpl(int[] arg0) {
        return arg0[2];
    }

    /* JADX INFO: renamed from: getEndY-impl, reason: not valid java name */
    public static final int m4507getEndYimpl(int[] arg0) {
        return arg0[3];
    }

    /* JADX INFO: renamed from: getReverse-impl, reason: not valid java name */
    public static final boolean m4509getReverseimpl(int[] arg0) {
        return arg0[4] != 0;
    }

    /* JADX INFO: renamed from: getDiagonalSize-impl, reason: not valid java name */
    public static final int m4505getDiagonalSizeimpl(int[] arg0) {
        return Math.min(m4506getEndXimpl(arg0) - m4510getStartXimpl(arg0), m4507getEndYimpl(arg0) - m4511getStartYimpl(arg0));
    }

    /* JADX INFO: renamed from: getHasAdditionOrRemoval-impl, reason: not valid java name */
    private static final boolean m4508getHasAdditionOrRemovalimpl(int[] arg0) {
        return m4507getEndYimpl(arg0) - m4511getStartYimpl(arg0) != m4506getEndXimpl(arg0) - m4510getStartXimpl(arg0);
    }

    /* JADX INFO: renamed from: isAddition-impl, reason: not valid java name */
    private static final boolean m4513isAdditionimpl(int[] arg0) {
        return m4507getEndYimpl(arg0) - m4511getStartYimpl(arg0) > m4506getEndXimpl(arg0) - m4510getStartXimpl(arg0);
    }

    /* JADX INFO: renamed from: addDiagonalToStack-impl, reason: not valid java name */
    public static final void m4500addDiagonalToStackimpl(int[] arg0, IntStack diagonals) {
        Intrinsics.checkNotNullParameter(diagonals, "diagonals");
        if (m4508getHasAdditionOrRemovalimpl(arg0)) {
            if (m4509getReverseimpl(arg0)) {
                diagonals.pushDiagonal(m4510getStartXimpl(arg0), m4511getStartYimpl(arg0), m4505getDiagonalSizeimpl(arg0));
                return;
            } else if (m4513isAdditionimpl(arg0)) {
                diagonals.pushDiagonal(m4510getStartXimpl(arg0), m4511getStartYimpl(arg0) + 1, m4505getDiagonalSizeimpl(arg0));
                return;
            } else {
                diagonals.pushDiagonal(m4510getStartXimpl(arg0) + 1, m4511getStartYimpl(arg0), m4505getDiagonalSizeimpl(arg0));
                return;
            }
        }
        diagonals.pushDiagonal(m4510getStartXimpl(arg0), m4511getStartYimpl(arg0), m4506getEndXimpl(arg0) - m4510getStartXimpl(arg0));
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4514toStringimpl(int[] arg0) {
        return "Snake(" + m4510getStartXimpl(arg0) + ',' + m4511getStartYimpl(arg0) + ',' + m4506getEndXimpl(arg0) + ',' + m4507getEndYimpl(arg0) + ',' + m4509getReverseimpl(arg0) + ')';
    }

    public String toString() {
        return m4514toStringimpl(this.data);
    }
}
