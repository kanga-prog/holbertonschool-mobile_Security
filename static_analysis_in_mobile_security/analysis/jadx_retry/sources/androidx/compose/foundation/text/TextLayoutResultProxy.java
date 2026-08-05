package androidx.compose.foundation.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextLayoutResultProxy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017J%\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u001aø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b \u0010!J\u0019\u0010\"\u001a\u00020\u001a*\u00020\u001aH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010$J\u0019\u0010%\u001a\u00020\u001a*\u00020\u001aH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010$R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006'"}, d2 = {"Landroidx/compose/foundation/text/TextLayoutResultProxy;", "", "value", "Landroidx/compose/ui/text/TextLayoutResult;", "(Landroidx/compose/ui/text/TextLayoutResult;)V", "decorationBoxCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getDecorationBoxCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setDecorationBoxCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "innerTextFieldCoordinates", "getInnerTextFieldCoordinates", "setInnerTextFieldCoordinates", "getValue", "()Landroidx/compose/ui/text/TextLayoutResult;", "getLineEnd", "", "lineIndex", "visibleEnd", "", "getLineForVerticalPosition", "vertical", "", "getOffsetForPosition", "position", "Landroidx/compose/ui/geometry/Offset;", "coerceInVisibleBounds", "getOffsetForPosition-3MmeM6k", "(JZ)I", "isPositionOnText", "offset", "isPositionOnText-k-4lQ0M", "(J)Z", "coercedInVisibleBoundsOfInputText", "coercedInVisibleBoundsOfInputText-MK-Hz9U", "(J)J", "relativeToInputText", "relativeToInputText-MK-Hz9U", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextLayoutResultProxy {
    private LayoutCoordinates decorationBoxCoordinates;
    private LayoutCoordinates innerTextFieldCoordinates;
    private final TextLayoutResult value;

    public TextLayoutResultProxy(TextLayoutResult value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
    }

    public final TextLayoutResult getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: getOffsetForPosition-3MmeM6k$default, reason: not valid java name */
    public static /* synthetic */ int m848getOffsetForPosition3MmeM6k$default(TextLayoutResultProxy textLayoutResultProxy, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return textLayoutResultProxy.m850getOffsetForPosition3MmeM6k(j, z);
    }

    /* JADX INFO: renamed from: getOffsetForPosition-3MmeM6k, reason: not valid java name */
    public final int m850getOffsetForPosition3MmeM6k(long position, boolean coerceInVisibleBounds) {
        long it = position;
        if (coerceInVisibleBounds) {
            it = m847coercedInVisibleBoundsOfInputTextMKHz9U(it);
        }
        long relativePosition = m849relativeToInputTextMKHz9U(it);
        return this.value.m4740getOffsetForPositionk4lQ0M(relativePosition);
    }

    public final int getLineForVerticalPosition(float vertical) {
        float relativeVertical = Offset.m2732getYimpl(m849relativeToInputTextMKHz9U(m847coercedInVisibleBoundsOfInputTextMKHz9U(OffsetKt.Offset(0.0f, vertical))));
        return this.value.getLineForVerticalPosition(relativeVertical);
    }

    public static /* synthetic */ int getLineEnd$default(TextLayoutResultProxy textLayoutResultProxy, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return textLayoutResultProxy.getLineEnd(i, z);
    }

    public final int getLineEnd(int lineIndex, boolean visibleEnd) {
        return this.value.getLineEnd(lineIndex, visibleEnd);
    }

    /* JADX INFO: renamed from: isPositionOnText-k-4lQ0M, reason: not valid java name */
    public final boolean m851isPositionOnTextk4lQ0M(long offset) {
        long relativeOffset = m849relativeToInputTextMKHz9U(m847coercedInVisibleBoundsOfInputTextMKHz9U(offset));
        int line = this.value.getLineForVerticalPosition(Offset.m2732getYimpl(relativeOffset));
        return Offset.m2731getXimpl(relativeOffset) >= this.value.getLineLeft(line) && Offset.m2731getXimpl(relativeOffset) <= this.value.getLineRight(line);
    }

    public final LayoutCoordinates getInnerTextFieldCoordinates() {
        return this.innerTextFieldCoordinates;
    }

    public final void setInnerTextFieldCoordinates(LayoutCoordinates layoutCoordinates) {
        this.innerTextFieldCoordinates = layoutCoordinates;
    }

    public final LayoutCoordinates getDecorationBoxCoordinates() {
        return this.decorationBoxCoordinates;
    }

    public final void setDecorationBoxCoordinates(LayoutCoordinates layoutCoordinates) {
        this.decorationBoxCoordinates = layoutCoordinates;
    }

    /* JADX INFO: renamed from: relativeToInputText-MK-Hz9U, reason: not valid java name */
    private final long m849relativeToInputTextMKHz9U(long $this$relativeToInputText_u2dMK_u2dHz9U) {
        Offset offsetM2720boximpl;
        long jMo4233localPositionOfR5De75A;
        LayoutCoordinates innerTextFieldCoordinates = this.innerTextFieldCoordinates;
        if (innerTextFieldCoordinates != null) {
            LayoutCoordinates decorationBoxCoordinates = this.decorationBoxCoordinates;
            if (decorationBoxCoordinates != null) {
                if (innerTextFieldCoordinates.isAttached() && decorationBoxCoordinates.isAttached()) {
                    jMo4233localPositionOfR5De75A = innerTextFieldCoordinates.mo4233localPositionOfR5De75A(decorationBoxCoordinates, $this$relativeToInputText_u2dMK_u2dHz9U);
                } else {
                    jMo4233localPositionOfR5De75A = $this$relativeToInputText_u2dMK_u2dHz9U;
                }
                offsetM2720boximpl = Offset.m2720boximpl(jMo4233localPositionOfR5De75A);
            } else {
                offsetM2720boximpl = null;
            }
            if (offsetM2720boximpl != null) {
                return offsetM2720boximpl.getPackedValue();
            }
        }
        return $this$relativeToInputText_u2dMK_u2dHz9U;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0020  */
    /* JADX INFO: renamed from: coercedInVisibleBoundsOfInputText-MK-Hz9U, reason: not valid java name */
    private final long m847coercedInVisibleBoundsOfInputTextMKHz9U(long $this$coercedInVisibleBoundsOfInputText_u2dMK_u2dHz9U) {
        Rect zero;
        LayoutCoordinates innerTextFieldCoordinates = this.innerTextFieldCoordinates;
        if (innerTextFieldCoordinates != null) {
            if (innerTextFieldCoordinates.isAttached()) {
                LayoutCoordinates layoutCoordinates = this.decorationBoxCoordinates;
                zero = null;
                if (layoutCoordinates != null) {
                    zero = LayoutCoordinates.CC.localBoundingBoxOf$default(layoutCoordinates, innerTextFieldCoordinates, false, 2, null);
                }
            } else {
                zero = Rect.INSTANCE.getZero();
            }
            if (zero == null) {
                zero = Rect.INSTANCE.getZero();
            }
        } else {
            zero = Rect.INSTANCE.getZero();
        }
        Rect visibleInnerTextFieldRect = zero;
        return TextLayoutResultProxyKt.m853coerceIn3MmeM6k($this$coercedInVisibleBoundsOfInputText_u2dMK_u2dHz9U, visibleInnerTextFieldRect);
    }
}
