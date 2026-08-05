package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorModel;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.DoubleFunction;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Color.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a<\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u0018\u0010\n\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a2\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u00142\b\b\u0002\u0010\u000f\u001a\u00020\u0014H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\u0018\u0010\n\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0017H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a1\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0082\b\u001a-\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\fH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a\u0010\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\fH\u0002\u001a!\u0010'\u001a\u00020\u0002*\u00020\u00022\u0006\u0010(\u001a\u00020\u0002H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a\u0019\u0010+\u001a\u00020,*\u00020\u0002H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a\u0019\u0010/\u001a\u00020\f*\u00020\u0002H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a+\u00102\u001a\u00020\u0002*\u00020\u00022\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u000204H\u0086\bø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a\u0019\u00107\u001a\u00020\u0014*\u00020\u0002H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b8\u00109\"\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\"\u0010\u0007\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\u0082\u0002\u0012\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0005\b\u009920\u0001¨\u0006:"}, d2 = {"isSpecified", "", "Landroidx/compose/ui/graphics/Color;", "isSpecified-8_81llA$annotations", "(J)V", "isSpecified-8_81llA", "(J)Z", "isUnspecified", "isUnspecified-8_81llA$annotations", "isUnspecified-8_81llA", "Color", "red", "", "green", "blue", "alpha", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "(FFFFLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "color", "", "(I)J", "(IIII)J", "", "(J)J", "compositeComponent", "fgC", "bgC", "fgA", "bgA", "a", "lerp", "start", "stop", "fraction", "lerp-jxsXWHM", "(JJF)J", "saturate", "v", "compositeOver", "background", "compositeOver--OWjLjI", "(JJ)J", "getComponents", "", "getComponents-8_81llA", "(J)[F", "luminance", "luminance-8_81llA", "(J)F", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-DxMtmZc", "(JLkotlin/jvm/functions/Function0;)J", "toArgb", "toArgb-8_81llA", "(J)I", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ColorKt {
    /* JADX INFO: renamed from: isSpecified-8_81llA$annotations, reason: not valid java name */
    public static /* synthetic */ void m3019isSpecified8_81llA$annotations(long j) {
    }

    /* JADX INFO: renamed from: isUnspecified-8_81llA$annotations, reason: not valid java name */
    public static /* synthetic */ void m3021isUnspecified8_81llA$annotations(long j) {
    }

    public static /* synthetic */ long Color$default(float f, float f2, float f3, float f4, ColorSpace colorSpace, int i, Object obj) {
        if ((i & 8) != 0) {
            f4 = 1.0f;
        }
        if ((i & 16) != 0) {
            colorSpace = ColorSpaces.INSTANCE.getSrgb();
        }
        return Color(f, f2, f3, f4, colorSpace);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0063  */
    public static final long Color(float red, float green, float blue, float alpha, ColorSpace colorSpace) {
        boolean z;
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        if (red <= colorSpace.getMaxValue(0) && colorSpace.getMinValue(0) <= red) {
            if (green <= colorSpace.getMaxValue(1) && colorSpace.getMinValue(1) <= green) {
                if (blue <= colorSpace.getMaxValue(2) && colorSpace.getMinValue(2) <= blue) {
                    if (0.0f <= alpha && alpha <= 1.0f) {
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException(("red = " + red + ", green = " + green + ", blue = " + blue + ", alpha = " + alpha + " outside the range for " + colorSpace).toString());
        }
        if (colorSpace.getIsSrgb()) {
            int argb = ((int) ((255.0f * blue) + 0.5f)) | (((int) ((alpha * 255.0f) + 0.5f)) << 24) | (((int) ((red * 255.0f) + 0.5f)) << 16) | (((int) ((green * 255.0f) + 0.5f)) << 8);
            return Color.m2967constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(argb) & 4294967295L) << 32));
        }
        if (!(colorSpace.getComponentCount() == 3)) {
            throw new IllegalArgumentException("Color only works with ColorSpaces with 3 components".toString());
        }
        int id = colorSpace.getId();
        if (!(id != -1)) {
            throw new IllegalArgumentException("Unknown color space, please use a color space in ColorSpaces".toString());
        }
        short r = Float16.m3074constructorimpl(red);
        short g = Float16.m3074constructorimpl(green);
        short b = Float16.m3074constructorimpl(blue);
        int a = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + 0.5f);
        return Color.m2967constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(g) & 65535) << 32) | ULong.m5737constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(r) & 65535) << 48)) | ULong.m5737constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(b) & 65535) << 16)) | ULong.m5737constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(a) & 1023) << 6)) | ULong.m5737constructorimpl(ULong.m5737constructorimpl(id) & 63)));
    }

    public static final long Color(int color) {
        return Color.m2967constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(color) << 32));
    }

    public static final long Color(long color) {
        return Color.m2967constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(ULong.m5737constructorimpl(color) & 4294967295L) << 32));
    }

    public static /* synthetic */ long Color$default(int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i4 = 255;
        }
        return Color(i, i2, i3, i4);
    }

    public static final long Color(int red, int green, int blue, int alpha) {
        int color = ((alpha & 255) << 24) | ((red & 255) << 16) | ((green & 255) << 8) | (blue & 255);
        return Color(color);
    }

    /* JADX INFO: renamed from: lerp-jxsXWHM, reason: not valid java name */
    public static final long m3022lerpjxsXWHM(long start, long stop, float fraction) {
        ColorSpace colorSpace = ColorSpaces.INSTANCE.getOklab();
        long startColor = Color.m2968convertvNxB06k(start, colorSpace);
        long endColor = Color.m2968convertvNxB06k(stop, colorSpace);
        float startAlpha = Color.m2973getAlphaimpl(startColor);
        float startL = Color.m2977getRedimpl(startColor);
        float startA = Color.m2976getGreenimpl(startColor);
        float startB = Color.m2974getBlueimpl(startColor);
        float endAlpha = Color.m2973getAlphaimpl(endColor);
        float endL = Color.m2977getRedimpl(endColor);
        float endA = Color.m2976getGreenimpl(endColor);
        float endB = Color.m2974getBlueimpl(endColor);
        long interpolated = Color(MathHelpersKt.lerp(startL, endL, fraction), MathHelpersKt.lerp(startA, endA, fraction), MathHelpersKt.lerp(startB, endB, fraction), MathHelpersKt.lerp(startAlpha, endAlpha, fraction), colorSpace);
        return Color.m2968convertvNxB06k(interpolated, Color.m2975getColorSpaceimpl(stop));
    }

    /* JADX INFO: renamed from: compositeOver--OWjLjI, reason: not valid java name */
    public static final long m3016compositeOverOWjLjI(long $this$compositeOver_u2d_u2dOWjLjI, long background) {
        long fg = Color.m2968convertvNxB06k($this$compositeOver_u2d_u2dOWjLjI, Color.m2975getColorSpaceimpl(background));
        float bgA = Color.m2973getAlphaimpl(background);
        float fgA = Color.m2973getAlphaimpl(fg);
        float a = ((1.0f - fgA) * bgA) + fgA;
        float fgC$iv = Color.m2977getRedimpl(fg);
        float bgC$iv = Color.m2977getRedimpl(background);
        float fgC$iv2 = (a > 0.0f ? 1 : (a == 0.0f ? 0 : -1)) == 0 ? 0.0f : ((fgC$iv * fgA) + ((bgC$iv * bgA) * (1.0f - fgA))) / a;
        float fgC$iv3 = Color.m2976getGreenimpl(fg);
        float bgC$iv2 = Color.m2976getGreenimpl(background);
        float fgC$iv4 = (a > 0.0f ? 1 : (a == 0.0f ? 0 : -1)) == 0 ? 0.0f : ((fgC$iv3 * fgA) + ((bgC$iv2 * bgA) * (1.0f - fgA))) / a;
        float fgC$iv5 = Color.m2974getBlueimpl(fg);
        float bgC$iv3 = Color.m2974getBlueimpl(background);
        float b = a == 0.0f ? 0.0f : ((fgC$iv5 * fgA) + ((bgC$iv3 * bgA) * (1.0f - fgA))) / a;
        return Color(fgC$iv2, fgC$iv4, b, a, Color.m2975getColorSpaceimpl(background));
    }

    private static final float compositeComponent(float fgC, float bgC, float fgA, float bgA, float a) {
        if (a == 0.0f) {
            return 0.0f;
        }
        return ((fgC * fgA) + ((bgC * bgA) * (1.0f - fgA))) / a;
    }

    /* JADX INFO: renamed from: getComponents-8_81llA, reason: not valid java name */
    private static final float[] m3017getComponents8_81llA(long $this$getComponents_u2d8_81llA) {
        return new float[]{Color.m2977getRedimpl($this$getComponents_u2d8_81llA), Color.m2976getGreenimpl($this$getComponents_u2d8_81llA), Color.m2974getBlueimpl($this$getComponents_u2d8_81llA), Color.m2973getAlphaimpl($this$getComponents_u2d8_81llA)};
    }

    /* JADX INFO: renamed from: luminance-8_81llA, reason: not valid java name */
    public static final float m3023luminance8_81llA(long $this$luminance_u2d8_81llA) {
        ColorSpace colorSpace = Color.m2975getColorSpaceimpl($this$luminance_u2d8_81llA);
        if (!ColorModel.m3378equalsimpl0(colorSpace.getModel(), ColorModel.INSTANCE.m3385getRgbxdoWZVw())) {
            throw new IllegalArgumentException(("The specified color must be encoded in an RGB color space. The supplied color space is " + ((Object) ColorModel.m3381toStringimpl(colorSpace.getModel()))).toString());
        }
        Intrinsics.checkNotNull(colorSpace, "null cannot be cast to non-null type androidx.compose.ui.graphics.colorspace.Rgb");
        DoubleFunction eotf = ((Rgb) colorSpace).getEotfFunc();
        double r = eotf.invoke(Color.m2977getRedimpl($this$luminance_u2d8_81llA));
        double g = eotf.invoke(Color.m2976getGreenimpl($this$luminance_u2d8_81llA));
        double b = eotf.invoke(Color.m2974getBlueimpl($this$luminance_u2d8_81llA));
        return saturate((float) ((0.2126d * r) + (0.7152d * g) + (0.0722d * b)));
    }

    private static final float saturate(float v) {
        if (v <= 0.0f) {
            return 0.0f;
        }
        if (v >= 1.0f) {
            return 1.0f;
        }
        return v;
    }

    /* JADX INFO: renamed from: toArgb-8_81llA, reason: not valid java name */
    public static final int m3025toArgb8_81llA(long $this$toArgb_u2d8_81llA) {
        return (int) ULong.m5737constructorimpl(Color.m2968convertvNxB06k($this$toArgb_u2d8_81llA, ColorSpaces.INSTANCE.getSrgb()) >>> 32);
    }

    /* JADX INFO: renamed from: isSpecified-8_81llA, reason: not valid java name */
    public static final boolean m3018isSpecified8_81llA(long $this$isSpecified) {
        return $this$isSpecified != Color.INSTANCE.m3007getUnspecified0d7_KjU();
    }

    /* JADX INFO: renamed from: isUnspecified-8_81llA, reason: not valid java name */
    public static final boolean m3020isUnspecified8_81llA(long $this$isUnspecified) {
        return $this$isUnspecified == Color.INSTANCE.m3007getUnspecified0d7_KjU();
    }

    /* JADX INFO: renamed from: takeOrElse-DxMtmZc, reason: not valid java name */
    public static final long m3024takeOrElseDxMtmZc(long $this$takeOrElse_u2dDxMtmZc, Function0<Color> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return ($this$takeOrElse_u2dDxMtmZc > Color.INSTANCE.m3007getUnspecified0d7_KjU() ? 1 : ($this$takeOrElse_u2dDxMtmZc == Color.INSTANCE.m3007getUnspecified0d7_KjU() ? 0 : -1)) != 0 ? $this$takeOrElse_u2dDxMtmZc : block.invoke().m2981unboximpl();
    }
}
