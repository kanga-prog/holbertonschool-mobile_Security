package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapGestureDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u0006H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004ø\u0001\u0001\u0082\u0002\n\n\u0002\b\u0019\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/unit/Density;", "awaitRelease", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryAwaitRelease", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PressGestureScope extends Density {
    Object awaitRelease(Continuation<? super Unit> continuation);

    Object tryAwaitRelease(Continuation<? super Boolean> continuation);

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.PressGestureScope$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m308roundToPxR2X_6o(PressGestureScope $this, long $receiver) {
            return Density.CC.m5236$default$roundToPxR2X_6o($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m309roundToPx0680j_4(PressGestureScope $this, float $receiver) {
            return Density.CC.m5237$default$roundToPx0680j_4($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m310toDpGaN1DYA(PressGestureScope $this, long $receiver) {
            return Density.CC.m5238$default$toDpGaN1DYA($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m311toDpu2uoSUM(PressGestureScope $this, float $receiver) {
            return Density.CC.m5239$default$toDpu2uoSUM($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m312toDpu2uoSUM(PressGestureScope $this, int $receiver) {
            return Density.CC.m5240$default$toDpu2uoSUM((Density) $this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m313toDpSizekrfVVM(PressGestureScope $this, long $receiver) {
            return Density.CC.m5241$default$toDpSizekrfVVM($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m314toPxR2X_6o(PressGestureScope $this, long $receiver) {
            return Density.CC.m5242$default$toPxR2X_6o($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m315toPx0680j_4(PressGestureScope $this, float $receiver) {
            return Density.CC.m5243$default$toPx0680j_4($this, $receiver);
        }

        @Deprecated
        public static Rect toRect(PressGestureScope $this, DpRect receiver) {
            Intrinsics.checkNotNullParameter(receiver, "$receiver");
            return Density.CC.$default$toRect($this, receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m316toSizeXkaWNTQ(PressGestureScope $this, long $receiver) {
            return Density.CC.m5244$default$toSizeXkaWNTQ($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m317toSp0xMU5do(PressGestureScope $this, float $receiver) {
            return Density.CC.m5245$default$toSp0xMU5do($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m318toSpkPz2Gy4(PressGestureScope $this, float $receiver) {
            return Density.CC.m5246$default$toSpkPz2Gy4($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m319toSpkPz2Gy4(PressGestureScope $this, int $receiver) {
            return Density.CC.m5247$default$toSpkPz2Gy4((Density) $this, $receiver);
        }
    }
}
