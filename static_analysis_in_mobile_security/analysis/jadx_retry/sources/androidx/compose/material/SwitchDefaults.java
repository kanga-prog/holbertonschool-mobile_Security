package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: Switch.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jy\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u0006H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Landroidx/compose/material/SwitchDefaults;", "", "()V", "colors", "Landroidx/compose/material/SwitchColors;", "checkedThumbColor", "Landroidx/compose/ui/graphics/Color;", "checkedTrackColor", "checkedTrackAlpha", "", "uncheckedThumbColor", "uncheckedTrackColor", "uncheckedTrackAlpha", "disabledCheckedThumbColor", "disabledCheckedTrackColor", "disabledUncheckedThumbColor", "disabledUncheckedTrackColor", "colors-SQMK_m0", "(JJFJJFJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material/SwitchColors;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SwitchDefaults {
    public static final int $stable = 0;
    public static final SwitchDefaults INSTANCE = new SwitchDefaults();

    private SwitchDefaults() {
    }

    /* JADX INFO: renamed from: colors-SQMK_m0, reason: not valid java name */
    public final SwitchColors m1222colorsSQMK_m0(long checkedThumbColor, long checkedTrackColor, float checkedTrackAlpha, long uncheckedThumbColor, long uncheckedTrackColor, float uncheckedTrackAlpha, long disabledCheckedThumbColor, long disabledCheckedTrackColor, long disabledUncheckedThumbColor, long disabledUncheckedTrackColor, Composer $composer, int $changed, int $changed1, int i) {
        long disabledCheckedThumbColor2;
        long disabledCheckedTrackColor2;
        long disabledUncheckedThumbColor2;
        long disabledUncheckedTrackColor2;
        $composer.startReplaceableGroup(-1032127534);
        ComposerKt.sourceInformation($composer, "C(colors)P(0:c#ui.graphics.Color,2:c#ui.graphics.Color!1,7:c#ui.graphics.Color,9:c#ui.graphics.Color,8,3:c#ui.graphics.Color,4:c#ui.graphics.Color,5:c#ui.graphics.Color,6:c#ui.graphics.Color)321@13073L6,324@13245L6,325@13312L6,328@13474L8,329@13525L6,331@13642L8,332@13693L6,334@13814L8,335@13865L6,337@13986L8,338@14037L6:Switch.kt#jmzs0o");
        long checkedThumbColor2 = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1051getSecondaryVariant0d7_KjU() : checkedThumbColor;
        long checkedTrackColor2 = (i & 2) != 0 ? checkedThumbColor2 : checkedTrackColor;
        float checkedTrackAlpha2 = (i & 4) != 0 ? 0.54f : checkedTrackAlpha;
        long uncheckedThumbColor2 = (i & 8) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1052getSurface0d7_KjU() : uncheckedThumbColor;
        long uncheckedTrackColor2 = (i & 16) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1047getOnSurface0d7_KjU() : uncheckedTrackColor;
        float uncheckedTrackAlpha2 = (i & 32) != 0 ? 0.38f : uncheckedTrackAlpha;
        if ((i & 64) != 0) {
            long j = checkedThumbColor2;
            disabledCheckedThumbColor2 = ColorKt.m3016compositeOverOWjLjI(Color.m2969copywmQWz5c(j, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1052getSurface0d7_KjU());
        } else {
            disabledCheckedThumbColor2 = disabledCheckedThumbColor;
        }
        if ((i & 128) != 0) {
            long j2 = checkedTrackColor2;
            long jM2969copywmQWz5c = Color.m2969copywmQWz5c(j2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j2) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j2) : 0.0f);
            long disabledCheckedThumbColor3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1052getSurface0d7_KjU();
            disabledCheckedTrackColor2 = ColorKt.m3016compositeOverOWjLjI(jM2969copywmQWz5c, disabledCheckedThumbColor3);
        } else {
            disabledCheckedTrackColor2 = disabledCheckedTrackColor;
        }
        if ((i & 256) != 0) {
            long j3 = uncheckedThumbColor2;
            long jM2969copywmQWz5c2 = Color.m2969copywmQWz5c(j3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j3) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j3) : 0.0f);
            long uncheckedThumbColor3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1052getSurface0d7_KjU();
            disabledUncheckedThumbColor2 = ColorKt.m3016compositeOverOWjLjI(jM2969copywmQWz5c2, uncheckedThumbColor3);
        } else {
            disabledUncheckedThumbColor2 = disabledUncheckedThumbColor;
        }
        if ((i & 512) != 0) {
            long j4 = uncheckedTrackColor2;
            disabledUncheckedTrackColor2 = ColorKt.m3016compositeOverOWjLjI(Color.m2969copywmQWz5c(j4, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j4) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j4) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j4) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j4) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1052getSurface0d7_KjU());
        } else {
            disabledUncheckedTrackColor2 = disabledUncheckedTrackColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1032127534, $changed, $changed1, "androidx.compose.material.SwitchDefaults.colors (Switch.kt:320)");
        }
        long j5 = checkedTrackColor2;
        long j6 = uncheckedTrackColor2;
        long j7 = disabledCheckedTrackColor2;
        long j8 = disabledUncheckedTrackColor2;
        DefaultSwitchColors defaultSwitchColors = new DefaultSwitchColors(checkedThumbColor2, Color.m2969copywmQWz5c(j5, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j5) : checkedTrackAlpha2, (14 & 2) != 0 ? Color.m2977getRedimpl(j5) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j5) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j5) : 0.0f), uncheckedThumbColor2, Color.m2969copywmQWz5c(j6, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j6) : uncheckedTrackAlpha2, (14 & 2) != 0 ? Color.m2977getRedimpl(j6) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j6) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j6) : 0.0f), disabledCheckedThumbColor2, Color.m2969copywmQWz5c(j7, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j7) : checkedTrackAlpha2, (14 & 2) != 0 ? Color.m2977getRedimpl(j7) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j7) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j7) : 0.0f), disabledUncheckedThumbColor2, Color.m2969copywmQWz5c(j8, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j8) : uncheckedTrackAlpha2, (14 & 2) != 0 ? Color.m2977getRedimpl(j8) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j8) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j8) : 0.0f), null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultSwitchColors;
    }
}
