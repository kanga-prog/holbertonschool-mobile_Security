package androidx.compose.material;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Typography.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"DefaultTextStyle", "Landroidx/compose/ui/text/TextStyle;", "getDefaultTextStyle", "()Landroidx/compose/ui/text/TextStyle;", "LocalTypography", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material/Typography;", "getLocalTypography", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "withDefaultFontFamily", "default", "Landroidx/compose/ui/text/font/FontFamily;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TypographyKt {
    private static final TextStyle DefaultTextStyle;
    private static final ProvidableCompositionLocal<Typography> LocalTypography;

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextStyle withDefaultFontFamily(TextStyle $this$withDefaultFontFamily, FontFamily fontFamily) {
        if ($this$withDefaultFontFamily.getFontFamily() != null) {
            return $this$withDefaultFontFamily;
        }
        return $this$withDefaultFontFamily.m4786copyv2rsoow((16252927 & 1) != 0 ? $this$withDefaultFontFamily.spanStyle.m4727getColor0d7_KjU() : 0L, (16252927 & 2) != 0 ? $this$withDefaultFontFamily.spanStyle.getFontSize() : 0L, (16252927 & 4) != 0 ? $this$withDefaultFontFamily.spanStyle.getFontWeight() : null, (16252927 & 8) != 0 ? $this$withDefaultFontFamily.spanStyle.getFontStyle() : null, (16252927 & 16) != 0 ? $this$withDefaultFontFamily.spanStyle.getFontSynthesis() : null, (16252927 & 32) != 0 ? $this$withDefaultFontFamily.spanStyle.getFontFamily() : fontFamily, (16252927 & 64) != 0 ? $this$withDefaultFontFamily.spanStyle.getFontFeatureSettings() : null, (16252927 & 128) != 0 ? $this$withDefaultFontFamily.spanStyle.getLetterSpacing() : 0L, (16252927 & 256) != 0 ? $this$withDefaultFontFamily.spanStyle.getBaselineShift() : null, (16252927 & 512) != 0 ? $this$withDefaultFontFamily.spanStyle.getTextGeometricTransform() : null, (16252927 & 1024) != 0 ? $this$withDefaultFontFamily.spanStyle.getLocaleList() : null, (16252927 & 2048) != 0 ? $this$withDefaultFontFamily.spanStyle.getBackground() : 0L, (16252927 & 4096) != 0 ? $this$withDefaultFontFamily.spanStyle.getTextDecoration() : null, (16252927 & 8192) != 0 ? $this$withDefaultFontFamily.spanStyle.getShadow() : null, (16252927 & 16384) != 0 ? $this$withDefaultFontFamily.spanStyle.getDrawStyle() : null, (16252927 & 32768) != 0 ? $this$withDefaultFontFamily.paragraphStyle.getTextAlign() : null, (16252927 & 65536) != 0 ? $this$withDefaultFontFamily.paragraphStyle.getTextDirection() : null, (16252927 & 131072) != 0 ? $this$withDefaultFontFamily.paragraphStyle.getLineHeight() : 0L, (16252927 & 262144) != 0 ? $this$withDefaultFontFamily.paragraphStyle.getTextIndent() : null, (16252927 & 524288) != 0 ? $this$withDefaultFontFamily.platformStyle : null, (16252927 & 1048576) != 0 ? $this$withDefaultFontFamily.paragraphStyle.getLineHeightStyle() : null, (16252927 & 2097152) != 0 ? $this$withDefaultFontFamily.paragraphStyle.getLineBreak() : null, (16252927 & 4194304) != 0 ? $this$withDefaultFontFamily.paragraphStyle.getHyphens() : null, (16252927 & 8388608) != 0 ? $this$withDefaultFontFamily.paragraphStyle.getTextMotion() : null);
    }

    static {
        TextStyle textStyle = TextStyle.INSTANCE.getDefault();
        DefaultTextStyle = textStyle.m4786copyv2rsoow((16252927 & 1) != 0 ? textStyle.spanStyle.m4727getColor0d7_KjU() : 0L, (16252927 & 2) != 0 ? textStyle.spanStyle.getFontSize() : 0L, (16252927 & 4) != 0 ? textStyle.spanStyle.getFontWeight() : null, (16252927 & 8) != 0 ? textStyle.spanStyle.getFontStyle() : null, (16252927 & 16) != 0 ? textStyle.spanStyle.getFontSynthesis() : null, (16252927 & 32) != 0 ? textStyle.spanStyle.getFontFamily() : null, (16252927 & 64) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : null, (16252927 & 128) != 0 ? textStyle.spanStyle.getLetterSpacing() : 0L, (16252927 & 256) != 0 ? textStyle.spanStyle.getBaselineShift() : null, (16252927 & 512) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : null, (16252927 & 1024) != 0 ? textStyle.spanStyle.getLocaleList() : null, (16252927 & 2048) != 0 ? textStyle.spanStyle.getBackground() : 0L, (16252927 & 4096) != 0 ? textStyle.spanStyle.getTextDecoration() : null, (16252927 & 8192) != 0 ? textStyle.spanStyle.getShadow() : null, (16252927 & 16384) != 0 ? textStyle.spanStyle.getDrawStyle() : null, (16252927 & 32768) != 0 ? textStyle.paragraphStyle.getTextAlign() : null, (16252927 & 65536) != 0 ? textStyle.paragraphStyle.getTextDirection() : null, (16252927 & 131072) != 0 ? textStyle.paragraphStyle.getLineHeight() : 0L, (16252927 & 262144) != 0 ? textStyle.paragraphStyle.getTextIndent() : null, (16252927 & 524288) != 0 ? textStyle.platformStyle : DefaultPlatformTextStyle_androidKt.defaultPlatformTextStyle(), (16252927 & 1048576) != 0 ? textStyle.paragraphStyle.getLineHeightStyle() : null, (16252927 & 2097152) != 0 ? textStyle.paragraphStyle.getLineBreak() : null, (16252927 & 4194304) != 0 ? textStyle.paragraphStyle.getHyphens() : null, (16252927 & 8388608) != 0 ? textStyle.paragraphStyle.getTextMotion() : null);
        LocalTypography = CompositionLocalKt.staticCompositionLocalOf(new Function0<Typography>() { // from class: androidx.compose.material.TypographyKt$LocalTypography$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Typography invoke() {
                return new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
            }
        });
    }

    public static final TextStyle getDefaultTextStyle() {
        return DefaultTextStyle;
    }

    public static final ProvidableCompositionLocal<Typography> getLocalTypography() {
        return LocalTypography;
    }
}
