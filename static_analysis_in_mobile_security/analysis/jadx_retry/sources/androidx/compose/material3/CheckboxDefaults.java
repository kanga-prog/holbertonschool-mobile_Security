package androidx.compose.material3;

import androidx.compose.material3.tokens.CheckboxTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: compiled from: Checkbox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JQ\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u0006H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\r\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Landroidx/compose/material3/CheckboxDefaults;", "", "()V", "colors", "Landroidx/compose/material3/CheckboxColors;", "checkedColor", "Landroidx/compose/ui/graphics/Color;", "uncheckedColor", "checkmarkColor", "disabledCheckedColor", "disabledUncheckedColor", "disabledIndeterminateColor", "colors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/CheckboxColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CheckboxDefaults {
    public static final int $stable = 0;
    public static final CheckboxDefaults INSTANCE = new CheckboxDefaults();

    private CheckboxDefaults() {
    }

    /* JADX INFO: renamed from: colors-5tl4gsc, reason: not valid java name */
    public final CheckboxColors m1352colors5tl4gsc(long checkedColor, long uncheckedColor, long checkmarkColor, long disabledCheckedColor, long disabledUncheckedColor, long disabledIndeterminateColor, Composer $composer, int $changed, int i) {
        long disabledCheckedColor2;
        long disabledUncheckedColor2;
        $composer.startReplaceableGroup(-89536160);
        ComposerKt.sourceInformation($composer, "C(colors)P(0:c#ui.graphics.Color,5:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.graphics.Color,3:c#ui.graphics.Color)199@8691L11,201@8811L11,203@8931L11,205@9052L11,209@9284L11:Checkbox.kt#uh7d8r");
        long checkedColor2 = (i & 1) != 0 ? ColorSchemeKt.fromToken(MaterialTheme.INSTANCE.getColorScheme($composer, 6), CheckboxTokens.INSTANCE.getSelectedContainerColor()) : checkedColor;
        long uncheckedColor2 = (i & 2) != 0 ? ColorSchemeKt.fromToken(MaterialTheme.INSTANCE.getColorScheme($composer, 6), CheckboxTokens.INSTANCE.getUnselectedOutlineColor()) : uncheckedColor;
        long checkmarkColor2 = (i & 4) != 0 ? ColorSchemeKt.fromToken(MaterialTheme.INSTANCE.getColorScheme($composer, 6), CheckboxTokens.INSTANCE.getSelectedIconColor()) : checkmarkColor;
        if ((i & 8) != 0) {
            long jFromToken = ColorSchemeKt.fromToken(MaterialTheme.INSTANCE.getColorScheme($composer, 6), CheckboxTokens.INSTANCE.getSelectedDisabledContainerColor());
            disabledCheckedColor2 = Color.m2969copywmQWz5c(jFromToken, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jFromToken) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(jFromToken) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jFromToken) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jFromToken) : 0.0f);
        } else {
            disabledCheckedColor2 = disabledCheckedColor;
        }
        if ((i & 16) != 0) {
            long jFromToken2 = ColorSchemeKt.fromToken(MaterialTheme.INSTANCE.getColorScheme($composer, 6), CheckboxTokens.INSTANCE.getUnselectedDisabledOutlineColor());
            disabledUncheckedColor2 = Color.m2969copywmQWz5c(jFromToken2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jFromToken2) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(jFromToken2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jFromToken2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jFromToken2) : 0.0f);
        } else {
            disabledUncheckedColor2 = disabledUncheckedColor;
        }
        long disabledIndeterminateColor2 = (i & 32) != 0 ? disabledCheckedColor2 : disabledIndeterminateColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-89536160, $changed, -1, "androidx.compose.material3.CheckboxDefaults.colors (Checkbox.kt:197)");
        }
        long j = checkmarkColor2;
        long jM2969copywmQWz5c = Color.m2969copywmQWz5c(j, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j) : 0.0f, (14 & 2) != 0 ? Color.m2977getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j) : 0.0f);
        long j2 = checkedColor2;
        long jM2969copywmQWz5c2 = Color.m2969copywmQWz5c(j2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j2) : 0.0f, (14 & 2) != 0 ? Color.m2977getRedimpl(j2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j2) : 0.0f);
        long j3 = disabledUncheckedColor2;
        CheckboxColors checkboxColors = new CheckboxColors(checkmarkColor2, jM2969copywmQWz5c, checkedColor2, jM2969copywmQWz5c2, disabledCheckedColor2, Color.m2969copywmQWz5c(j3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j3) : 0.0f, (14 & 2) != 0 ? Color.m2977getRedimpl(j3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j3) : 0.0f), disabledIndeterminateColor2, checkedColor2, uncheckedColor2, disabledCheckedColor2, disabledIndeterminateColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return checkboxColors;
    }
}
