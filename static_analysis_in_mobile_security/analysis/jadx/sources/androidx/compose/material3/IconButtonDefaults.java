package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.FilledIconButtonTokens;
import androidx.compose.material3.tokens.FilledTonalIconButtonTokens;
import androidx.compose.material3.tokens.IconButtonTokens;
import androidx.compose.material3.tokens.OutlinedIconButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;

/* JADX INFO: compiled from: IconButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J=\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\fH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011JQ\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0015\u001a\u00020\fH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017J=\u0010\u0018\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\fH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u0011JQ\u0010\u001a\u001a\u00020\u00132\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0015\u001a\u00020\fH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u0017J=\u0010\u001c\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\fH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u0011JQ\u0010\u001e\u001a\u00020\u00132\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0015\u001a\u00020\fH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010\u0017J\u0015\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0007¢\u0006\u0002\u0010$J=\u0010%\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\fH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010\u0011J\u001f\u0010'\u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010(\u001a\u00020#H\u0007¢\u0006\u0002\u0010)JQ\u0010*\u001a\u00020\u00132\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0015\u001a\u00020\fH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010\u0017R\u0011\u0010\u0003\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006,"}, d2 = {"Landroidx/compose/material3/IconButtonDefaults;", "", "()V", "filledShape", "Landroidx/compose/ui/graphics/Shape;", "getFilledShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "outlinedShape", "getOutlinedShape", "filledIconButtonColors", "Landroidx/compose/material3/IconButtonColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledContainerColor", "disabledContentColor", "filledIconButtonColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/IconButtonColors;", "filledIconToggleButtonColors", "Landroidx/compose/material3/IconToggleButtonColors;", "checkedContainerColor", "checkedContentColor", "filledIconToggleButtonColors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/IconToggleButtonColors;", "filledTonalIconButtonColors", "filledTonalIconButtonColors-ro_MJ88", "filledTonalIconToggleButtonColors", "filledTonalIconToggleButtonColors-5tl4gsc", "iconButtonColors", "iconButtonColors-ro_MJ88", "iconToggleButtonColors", "iconToggleButtonColors-5tl4gsc", "outlinedIconButtonBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "(ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "outlinedIconButtonColors", "outlinedIconButtonColors-ro_MJ88", "outlinedIconToggleButtonBorder", "checked", "(ZZLandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "outlinedIconToggleButtonColors", "outlinedIconToggleButtonColors-5tl4gsc", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class IconButtonDefaults {
    public static final int $stable = 0;
    public static final IconButtonDefaults INSTANCE = new IconButtonDefaults();

    private IconButtonDefaults() {
    }

    public final Shape getFilledShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1265841879);
        ComposerKt.sourceInformation($composer, "C537@26248L9:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1265841879, $changed, -1, "androidx.compose.material3.IconButtonDefaults.<get-filledShape> (IconButton.kt:537)");
        }
        Shape shape = ShapesKt.toShape(FilledIconButtonTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return shape;
    }

    public final Shape getOutlinedShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1327125527);
        ComposerKt.sourceInformation($composer, "C542@26422L9:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1327125527, $changed, -1, "androidx.compose.material3.IconButtonDefaults.<get-outlinedShape> (IconButton.kt:541)");
        }
        Shape shape = ShapesKt.toShape(OutlinedIconButtonTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return shape;
    }

    /* JADX INFO: renamed from: iconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m1562iconButtonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long disabledContentColor2;
        $composer.startReplaceableGroup(999008085);
        ComposerKt.sourceInformation($composer, "C(iconButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)555@27040L7:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : containerColor;
        if ((i & 2) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            contentColor2 = ((Color) objConsume).m2981unboximpl();
        } else {
            contentColor2 = contentColor;
        }
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : disabledContainerColor;
        if ((i & 8) != 0) {
            long j = contentColor2;
            disabledContentColor2 = Color.m2969copywmQWz5c(j, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j) : 0.0f);
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(999008085, $changed, -1, "androidx.compose.material3.IconButtonDefaults.iconButtonColors (IconButton.kt:553)");
        }
        IconButtonColors iconButtonColors = new IconButtonColors(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return iconButtonColors;
    }

    /* JADX INFO: renamed from: iconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m1563iconToggleButtonColors5tl4gsc(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, long checkedContainerColor, long checkedContentColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long disabledContentColor2;
        $composer.startReplaceableGroup(-2020719549);
        ComposerKt.sourceInformation($composer, "C(iconToggleButtonColors)P(2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color,5:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color)581@28297L7,586@28610L9:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : containerColor;
        if ((i & 2) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            contentColor2 = ((Color) objConsume).m2981unboximpl();
        } else {
            contentColor2 = contentColor;
        }
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : disabledContainerColor;
        if ((i & 8) != 0) {
            long j = contentColor2;
            disabledContentColor2 = Color.m2969copywmQWz5c(j, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j) : 0.0f);
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        long checkedContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : checkedContainerColor;
        long checkedContentColor2 = (i & 32) != 0 ? ColorSchemeKt.toColor(IconButtonTokens.INSTANCE.getSelectedIconColor(), $composer, 6) : checkedContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2020719549, $changed, -1, "androidx.compose.material3.IconButtonDefaults.iconToggleButtonColors (IconButton.kt:579)");
        }
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2, checkedContainerColor2, checkedContentColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return iconToggleButtonColors;
    }

    /* JADX INFO: renamed from: filledIconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m1558filledIconButtonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long disabledContainerColor2;
        long disabledContentColor2;
        $composer.startReplaceableGroup(-669858473);
        ComposerKt.sourceInformation($composer, "C(filledIconButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)607@29603L9,608@29644L31,609@29763L9,611@29924L9:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? ColorSchemeKt.toColor(FilledIconButtonTokens.INSTANCE.getContainerColor(), $composer, 6) : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer, $changed & 14) : contentColor;
        if ((i & 4) != 0) {
            long color = ColorSchemeKt.toColor(FilledIconButtonTokens.INSTANCE.getDisabledContainerColor(), $composer, 6);
            disabledContainerColor2 = Color.m2969copywmQWz5c(color, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color) : 0.12f, (14 & 2) != 0 ? Color.m2977getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color) : 0.0f);
        } else {
            disabledContainerColor2 = disabledContainerColor;
        }
        if ((i & 8) != 0) {
            long color2 = ColorSchemeKt.toColor(FilledIconButtonTokens.INSTANCE.getDisabledColor(), $composer, 6);
            disabledContentColor2 = Color.m2969copywmQWz5c(color2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color2) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color2) : 0.0f);
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-669858473, $changed, -1, "androidx.compose.material3.IconButtonDefaults.filledIconButtonColors (IconButton.kt:606)");
        }
        IconButtonColors iconButtonColors = new IconButtonColors(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return iconButtonColors;
    }

    /* JADX INFO: renamed from: filledIconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m1559filledIconToggleButtonColors5tl4gsc(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, long checkedContainerColor, long checkedContentColor, Composer $composer, int $changed, int i) {
        long disabledContainerColor2;
        long disabledContentColor2;
        $composer.startReplaceableGroup(1887173701);
        ComposerKt.sourceInformation($composer, "C(filledIconToggleButtonColors)P(2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color,5:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color)634@31068L9,637@31287L9,638@31384L9,640@31545L9,642@31707L9,643@31755L38:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? ColorSchemeKt.toColor(FilledIconButtonTokens.INSTANCE.getUnselectedContainerColor(), $composer, 6) : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.toColor(FilledIconButtonTokens.INSTANCE.getToggleUnselectedColor(), $composer, 6) : contentColor;
        if ((i & 4) != 0) {
            long color = ColorSchemeKt.toColor(FilledIconButtonTokens.INSTANCE.getDisabledContainerColor(), $composer, 6);
            disabledContainerColor2 = Color.m2969copywmQWz5c(color, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color) : 0.12f, (14 & 2) != 0 ? Color.m2977getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color) : 0.0f);
        } else {
            disabledContainerColor2 = disabledContainerColor;
        }
        if ((i & 8) != 0) {
            long color2 = ColorSchemeKt.toColor(FilledIconButtonTokens.INSTANCE.getDisabledColor(), $composer, 6);
            disabledContentColor2 = Color.m2969copywmQWz5c(color2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color2) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color2) : 0.0f);
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        long checkedContainerColor2 = (i & 16) != 0 ? ColorSchemeKt.toColor(FilledIconButtonTokens.INSTANCE.getSelectedContainerColor(), $composer, 6) : checkedContainerColor;
        long checkedContentColor2 = (i & 32) != 0 ? ColorSchemeKt.m1426contentColorForek8zF_U(checkedContainerColor2, $composer, ($changed >> 12) & 14) : checkedContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1887173701, $changed, -1, "androidx.compose.material3.IconButtonDefaults.filledIconToggleButtonColors (IconButton.kt:633)");
        }
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2, checkedContainerColor2, checkedContentColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return iconToggleButtonColors;
    }

    /* JADX INFO: renamed from: filledTonalIconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m1560filledTonalIconButtonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long disabledContainerColor2;
        long disabledContentColor2;
        $composer.startReplaceableGroup(-18532843);
        ComposerKt.sourceInformation($composer, "C(filledTonalIconButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)665@32799L9,666@32840L31,667@32964L9,669@33135L9:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? ColorSchemeKt.toColor(FilledTonalIconButtonTokens.INSTANCE.getContainerColor(), $composer, 6) : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer, $changed & 14) : contentColor;
        if ((i & 4) != 0) {
            long color = ColorSchemeKt.toColor(FilledTonalIconButtonTokens.INSTANCE.getDisabledContainerColor(), $composer, 6);
            disabledContainerColor2 = Color.m2969copywmQWz5c(color, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color) : 0.12f, (14 & 2) != 0 ? Color.m2977getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color) : 0.0f);
        } else {
            disabledContainerColor2 = disabledContainerColor;
        }
        if ((i & 8) != 0) {
            long color2 = ColorSchemeKt.toColor(FilledTonalIconButtonTokens.INSTANCE.getDisabledColor(), $composer, 6);
            disabledContentColor2 = Color.m2969copywmQWz5c(color2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color2) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color2) : 0.0f);
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-18532843, $changed, -1, "androidx.compose.material3.IconButtonDefaults.filledTonalIconButtonColors (IconButton.kt:664)");
        }
        IconButtonColors iconButtonColors = new IconButtonColors(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return iconButtonColors;
    }

    /* JADX INFO: renamed from: filledTonalIconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m1561filledTonalIconToggleButtonColors5tl4gsc(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, long checkedContainerColor, long checkedContentColor, Composer $composer, int $changed, int i) {
        long disabledContainerColor2;
        long disabledContentColor2;
        $composer.startReplaceableGroup(-19426557);
        ComposerKt.sourceInformation($composer, "C(filledTonalIconToggleButtonColors)P(2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color,5:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color)692@34299L9,693@34340L31,694@34464L9,696@34635L9,699@34819L9,700@34915L9:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? ColorSchemeKt.toColor(FilledTonalIconButtonTokens.INSTANCE.getUnselectedContainerColor(), $composer, 6) : containerColor;
        long contentColor2 = (i & 2) != 0 ? ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer, $changed & 14) : contentColor;
        if ((i & 4) != 0) {
            long color = ColorSchemeKt.toColor(FilledTonalIconButtonTokens.INSTANCE.getDisabledContainerColor(), $composer, 6);
            disabledContainerColor2 = Color.m2969copywmQWz5c(color, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color) : 0.12f, (14 & 2) != 0 ? Color.m2977getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color) : 0.0f);
        } else {
            disabledContainerColor2 = disabledContainerColor;
        }
        if ((i & 8) != 0) {
            long color2 = ColorSchemeKt.toColor(FilledTonalIconButtonTokens.INSTANCE.getDisabledColor(), $composer, 6);
            disabledContentColor2 = Color.m2969copywmQWz5c(color2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color2) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color2) : 0.0f);
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        long checkedContainerColor2 = (i & 16) != 0 ? ColorSchemeKt.toColor(FilledTonalIconButtonTokens.INSTANCE.getSelectedContainerColor(), $composer, 6) : checkedContainerColor;
        long checkedContentColor2 = (i & 32) != 0 ? ColorSchemeKt.toColor(FilledTonalIconButtonTokens.INSTANCE.getToggleSelectedColor(), $composer, 6) : checkedContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-19426557, $changed, -1, "androidx.compose.material3.IconButtonDefaults.filledTonalIconToggleButtonColors (IconButton.kt:691)");
        }
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2, checkedContainerColor2, checkedContentColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return iconToggleButtonColors;
    }

    /* JADX INFO: renamed from: outlinedIconButtonColors-ro_MJ88, reason: not valid java name */
    public final IconButtonColors m1564outlinedIconButtonColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long disabledContentColor2;
        $composer.startReplaceableGroup(-1030517545);
        ComposerKt.sourceInformation($composer, "C(outlinedIconButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)723@35948L7:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : containerColor;
        if ((i & 2) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            contentColor2 = ((Color) objConsume).m2981unboximpl();
        } else {
            contentColor2 = contentColor;
        }
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : disabledContainerColor;
        if ((i & 8) != 0) {
            long j = contentColor2;
            disabledContentColor2 = Color.m2969copywmQWz5c(j, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j) : 0.0f);
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1030517545, $changed, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonColors (IconButton.kt:721)");
        }
        IconButtonColors iconButtonColors = new IconButtonColors(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return iconButtonColors;
    }

    /* JADX INFO: renamed from: outlinedIconToggleButtonColors-5tl4gsc, reason: not valid java name */
    public final IconToggleButtonColors m1565outlinedIconToggleButtonColors5tl4gsc(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, long checkedContainerColor, long checkedContentColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long disabledContentColor2;
        $composer.startReplaceableGroup(2130592709);
        ComposerKt.sourceInformation($composer, "C(outlinedIconToggleButtonColors)P(2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color,5:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color)749@37225L7,754@37511L9,755@37559L38:IconButton.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : containerColor;
        if ((i & 2) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            contentColor2 = ((Color) objConsume).m2981unboximpl();
        } else {
            contentColor2 = contentColor;
        }
        long disabledContainerColor2 = (i & 4) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : disabledContainerColor;
        if ((i & 8) != 0) {
            long j = contentColor2;
            disabledContentColor2 = Color.m2969copywmQWz5c(j, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j) : 0.0f);
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        long checkedContainerColor2 = (i & 16) != 0 ? ColorSchemeKt.toColor(OutlinedIconButtonTokens.INSTANCE.getSelectedContainerColor(), $composer, 6) : checkedContainerColor;
        long checkedContentColor2 = (i & 32) != 0 ? ColorSchemeKt.m1426contentColorForek8zF_U(checkedContainerColor2, $composer, ($changed >> 12) & 14) : checkedContentColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2130592709, $changed, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonColors (IconButton.kt:747)");
        }
        IconToggleButtonColors iconToggleButtonColors = new IconToggleButtonColors(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2, checkedContainerColor2, checkedContentColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return iconToggleButtonColors;
    }

    public final BorderStroke outlinedIconToggleButtonBorder(boolean enabled, boolean checked, Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1244729690);
        ComposerKt.sourceInformation($composer, "C(outlinedIconToggleButtonBorder)P(1)778@38429L33:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1244729690, $changed, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconToggleButtonBorder (IconButton.kt:774)");
        }
        if (checked) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceableGroup();
            return null;
        }
        BorderStroke borderStrokeOutlinedIconButtonBorder = outlinedIconButtonBorder(enabled, $composer, ($changed & 14) | (($changed >> 3) & 112));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return borderStrokeOutlinedIconButtonBorder;
    }

    public final BorderStroke outlinedIconButtonBorder(boolean enabled, Composer $composer, int $changed) {
        long color;
        Object value$iv$iv;
        $composer.startReplaceableGroup(-511461558);
        ComposerKt.sourceInformation($composer, "C(outlinedIconButtonBorder)794@38982L108:IconButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-511461558, $changed, -1, "androidx.compose.material3.IconButtonDefaults.outlinedIconButtonBorder (IconButton.kt:787)");
        }
        if (enabled) {
            $composer.startReplaceableGroup(1252615214);
            ComposerKt.sourceInformation($composer, "789@38805L7");
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            color = ((Color) objConsume).m2981unboximpl();
            $composer.endReplaceableGroup();
        } else {
            $composer.startReplaceableGroup(1252615269);
            ComposerKt.sourceInformation($composer, "791@38860L7");
            ProvidableCompositionLocal<Color> localContentColor2 = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localContentColor2);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long jM2981unboximpl = ((Color) objConsume2).m2981unboximpl();
            color = Color.m2969copywmQWz5c(jM2981unboximpl, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM2981unboximpl) : 0.12f, (14 & 2) != 0 ? Color.m2977getRedimpl(jM2981unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM2981unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM2981unboximpl) : 0.0f);
            $composer.endReplaceableGroup();
        }
        Object key1$iv = Color.m2961boximpl(color);
        $composer.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(key1$iv);
        Object it$iv$iv = $composer.rememberedValue();
        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = BorderStrokeKt.m187BorderStrokecXLIe8U(OutlinedIconButtonTokens.INSTANCE.m2336getUnselectedOutlineWidthD9Ej5fM(), color);
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        $composer.endReplaceableGroup();
        BorderStroke borderStroke = (BorderStroke) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return borderStroke;
    }
}
