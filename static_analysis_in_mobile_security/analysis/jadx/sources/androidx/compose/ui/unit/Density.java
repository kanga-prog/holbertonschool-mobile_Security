package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: Density.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u000b\u001a\u00020\f*\u00020\rH\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u000b\u001a\u00020\f*\u00020\u0010H\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\r*\u00020\u0010H\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001c\u0010\u0013\u001a\u00020\r*\u00020\u0003H\u0017ø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001c\u0010\u0013\u001a\u00020\r*\u00020\fH\u0017ø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u001a*\u00020\u001bH\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u0003*\u00020\rH\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010\u0017J\u0019\u0010\u001e\u001a\u00020\u0003*\u00020\u0010H\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b \u0010\u0015J\f\u0010!\u001a\u00020\"*\u00020#H\u0017J\u0019\u0010$\u001a\u00020\u001b*\u00020\u001aH\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010\u001dJ\u0019\u0010&\u001a\u00020\u0010*\u00020\rH\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010(J\u001c\u0010&\u001a\u00020\u0010*\u00020\u0003H\u0017ø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010(J\u001c\u0010&\u001a\u00020\u0010*\u00020\fH\u0017ø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010*R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0005\u001a\u0004\b\n\u0010\u0007ø\u0001\u0003\u0082\u0002\u0015\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0002\b!\n\u0004\b!0\u0001¨\u0006+À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/unit/Density;", "", "density", "", "getDensity$annotations", "()V", "getDensity", "()F", "fontScale", "getFontScale$annotations", "getFontScale", "roundToPx", "", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-GaN1DYA", "(J)F", "toDp-u2uoSUM", "(F)F", "(I)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "Landroidx/compose/ui/geometry/Size;", "toDpSize-k-rfVVM", "(J)J", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-0xMU5do", "(F)J", "toSp-kPz2Gy4", "(I)J", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Density {
    float getDensity();

    float getFontScale();

    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    int mo320roundToPxR2X_6o(long j);

    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    int mo321roundToPx0680j_4(float f);

    /* JADX INFO: renamed from: toDp-GaN1DYA */
    float mo322toDpGaN1DYA(long j);

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    float mo323toDpu2uoSUM(float f);

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    float mo324toDpu2uoSUM(int i);

    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    long mo325toDpSizekrfVVM(long j);

    /* JADX INFO: renamed from: toPx--R2X_6o */
    float mo326toPxR2X_6o(long j);

    /* JADX INFO: renamed from: toPx-0680j_4 */
    float mo327toPx0680j_4(float f);

    Rect toRect(DpRect dpRect);

    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    long mo328toSizeXkaWNTQ(long j);

    /* JADX INFO: renamed from: toSp-0xMU5do */
    long mo329toSp0xMU5do(float f);

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    long mo330toSpkPz2Gy4(float f);

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    long mo331toSpkPz2Gy4(int i);

    /* JADX INFO: renamed from: androidx.compose.ui.unit.Density$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Density.kt */
    public final /* synthetic */ class CC {
        /* JADX INFO: renamed from: $default$toPx-0680j_4, reason: not valid java name */
        public static float m5243$default$toPx0680j_4(Density _this, float $this$toPx_u2d0680j_4) {
            return _this.getDensity() * $this$toPx_u2d0680j_4;
        }

        /* JADX INFO: renamed from: $default$roundToPx-0680j_4, reason: not valid java name */
        public static int m5237$default$roundToPx0680j_4(Density _this, float $this$roundToPx_u2d0680j_4) {
            float px = _this.mo327toPx0680j_4($this$roundToPx_u2d0680j_4);
            if (Float.isInfinite(px)) {
                return Integer.MAX_VALUE;
            }
            return MathKt.roundToInt(px);
        }

        /* JADX INFO: renamed from: $default$toSp-0xMU5do, reason: not valid java name */
        public static long m5245$default$toSp0xMU5do(Density _this, float $this$toSp_u2d0xMU5do) {
            return TextUnitKt.getSp($this$toSp_u2d0xMU5do / _this.getFontScale());
        }

        /* JADX INFO: renamed from: $default$toPx--R2X_6o, reason: not valid java name */
        public static float m5242$default$toPxR2X_6o(Density _this, long $this$toPx_u2d_u2dR2X_6o) {
            if (TextUnitType.m5483equalsimpl0(TextUnit.m5454getTypeUIouoOA($this$toPx_u2d_u2dR2X_6o), TextUnitType.INSTANCE.m5488getSpUIouoOA())) {
                return TextUnit.m5455getValueimpl($this$toPx_u2d_u2dR2X_6o) * _this.getFontScale() * _this.getDensity();
            }
            throw new IllegalStateException("Only Sp can convert to Px".toString());
        }

        /* JADX INFO: renamed from: $default$roundToPx--R2X_6o, reason: not valid java name */
        public static int m5236$default$roundToPxR2X_6o(Density _this, long $this$roundToPx_u2d_u2dR2X_6o) {
            return MathKt.roundToInt(_this.mo326toPxR2X_6o($this$roundToPx_u2d_u2dR2X_6o));
        }

        /* JADX INFO: renamed from: $default$toDp-GaN1DYA, reason: not valid java name */
        public static float m5238$default$toDpGaN1DYA(Density _this, long $this$toDp_u2dGaN1DYA) {
            if (TextUnitType.m5483equalsimpl0(TextUnit.m5454getTypeUIouoOA($this$toDp_u2dGaN1DYA), TextUnitType.INSTANCE.m5488getSpUIouoOA())) {
                return Dp.m5274constructorimpl(TextUnit.m5455getValueimpl($this$toDp_u2dGaN1DYA) * _this.getFontScale());
            }
            throw new IllegalStateException("Only Sp can convert to Px".toString());
        }

        /* JADX INFO: renamed from: $default$toDp-u2uoSUM, reason: not valid java name */
        public static float m5240$default$toDpu2uoSUM(Density _this, int $this$toDp_u2du2uoSUM) {
            float $this$dp$iv = $this$toDp_u2du2uoSUM / _this.getDensity();
            return Dp.m5274constructorimpl($this$dp$iv);
        }

        /* JADX INFO: renamed from: $default$toSp-kPz2Gy4, reason: not valid java name */
        public static long m5247$default$toSpkPz2Gy4(Density _this, int $this$toSp_u2dkPz2Gy4) {
            return TextUnitKt.getSp($this$toSp_u2dkPz2Gy4 / (_this.getFontScale() * _this.getDensity()));
        }

        /* JADX INFO: renamed from: $default$toDp-u2uoSUM, reason: not valid java name */
        public static float m5239$default$toDpu2uoSUM(Density _this, float $this$toDp_u2du2uoSUM) {
            float $this$dp$iv = $this$toDp_u2du2uoSUM / _this.getDensity();
            return Dp.m5274constructorimpl($this$dp$iv);
        }

        /* JADX INFO: renamed from: $default$toSp-kPz2Gy4, reason: not valid java name */
        public static long m5246$default$toSpkPz2Gy4(Density _this, float $this$toSp_u2dkPz2Gy4) {
            return TextUnitKt.getSp($this$toSp_u2dkPz2Gy4 / (_this.getFontScale() * _this.getDensity()));
        }

        public static Rect $default$toRect(Density _this, DpRect $this$toRect) {
            Intrinsics.checkNotNullParameter($this$toRect, "<this>");
            return new Rect(_this.mo327toPx0680j_4($this$toRect.m5357getLeftD9Ej5fM()), _this.mo327toPx0680j_4($this$toRect.m5359getTopD9Ej5fM()), _this.mo327toPx0680j_4($this$toRect.m5358getRightD9Ej5fM()), _this.mo327toPx0680j_4($this$toRect.m5356getBottomD9Ej5fM()));
        }

        /* JADX INFO: renamed from: $default$toSize-XkaWNTQ, reason: not valid java name */
        public static long m5244$default$toSizeXkaWNTQ(Density _this, long $this$toSize_u2dXkaWNTQ) {
            if ($this$toSize_u2dXkaWNTQ != DpSize.INSTANCE.m5381getUnspecifiedMYxV2XQ()) {
                return SizeKt.Size(_this.mo327toPx0680j_4(DpSize.m5372getWidthD9Ej5fM($this$toSize_u2dXkaWNTQ)), _this.mo327toPx0680j_4(DpSize.m5370getHeightD9Ej5fM($this$toSize_u2dXkaWNTQ)));
            }
            return Size.INSTANCE.m2808getUnspecifiedNHjbRc();
        }

        /* JADX INFO: renamed from: $default$toDpSize-k-rfVVM, reason: not valid java name */
        public static long m5241$default$toDpSizekrfVVM(Density _this, long $this$toDpSize_u2dk_u2drfVVM) {
            if ($this$toDpSize_u2dk_u2drfVVM != Size.INSTANCE.m2808getUnspecifiedNHjbRc()) {
                return DpKt.m5296DpSizeYgX7TsA(_this.mo323toDpu2uoSUM(Size.m2800getWidthimpl($this$toDpSize_u2dk_u2drfVVM)), _this.mo323toDpu2uoSUM(Size.m2797getHeightimpl($this$toDpSize_u2dk_u2drfVVM)));
            }
            return DpSize.INSTANCE.m5381getUnspecifiedMYxV2XQ();
        }
    }

    /* JADX INFO: compiled from: Density.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void getDensity$annotations() {
        }

        public static /* synthetic */ void getFontScale$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m5267toPx0680j_4(Density $this, float $receiver) {
            return CC.m5243$default$toPx0680j_4($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m5261roundToPx0680j_4(Density $this, float $receiver) {
            return CC.m5237$default$roundToPx0680j_4($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m5269toSp0xMU5do(Density $this, float $receiver) {
            return CC.m5245$default$toSp0xMU5do($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m5266toPxR2X_6o(Density $this, long $receiver) {
            return CC.m5242$default$toPxR2X_6o($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m5260roundToPxR2X_6o(Density $this, long $receiver) {
            return CC.m5236$default$roundToPxR2X_6o($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m5262toDpGaN1DYA(Density $this, long $receiver) {
            return CC.m5238$default$toDpGaN1DYA($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5264toDpu2uoSUM(Density $this, int $receiver) {
            return CC.m5240$default$toDpu2uoSUM($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5271toSpkPz2Gy4(Density $this, int $receiver) {
            return CC.m5247$default$toSpkPz2Gy4($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5263toDpu2uoSUM(Density $this, float $receiver) {
            return CC.m5239$default$toDpu2uoSUM($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5270toSpkPz2Gy4(Density $this, float $receiver) {
            return CC.m5246$default$toSpkPz2Gy4($this, $receiver);
        }

        @Deprecated
        public static Rect toRect(Density $this, DpRect receiver) {
            Intrinsics.checkNotNullParameter(receiver, "$receiver");
            return CC.$default$toRect($this, receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m5268toSizeXkaWNTQ(Density $this, long $receiver) {
            return CC.m5244$default$toSizeXkaWNTQ($this, $receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m5265toDpSizekrfVVM(Density $this, long $receiver) {
            return CC.m5241$default$toDpSizekrfVVM($this, $receiver);
        }
    }
}
