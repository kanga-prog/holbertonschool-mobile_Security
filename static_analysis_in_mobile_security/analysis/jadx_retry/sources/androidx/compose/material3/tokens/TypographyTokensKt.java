package androidx.compose.material3.tokens;

import androidx.compose.material3.DefaultPlatformTextStyle_androidKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;

/* JADX INFO: compiled from: TypographyTokens.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"DefaultTextStyle", "Landroidx/compose/ui/text/TextStyle;", "getDefaultTextStyle", "()Landroidx/compose/ui/text/TextStyle;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TypographyTokensKt {
    private static final TextStyle DefaultTextStyle;

    static {
        TextStyle textStyle = TextStyle.INSTANCE.getDefault();
        DefaultTextStyle = textStyle.m4782copyCXVQc50((3653631 & 1) != 0 ? textStyle.spanStyle.m4727getColor0d7_KjU() : 0L, (3653631 & 2) != 0 ? textStyle.spanStyle.getFontSize() : 0L, (3653631 & 4) != 0 ? textStyle.spanStyle.getFontWeight() : null, (3653631 & 8) != 0 ? textStyle.spanStyle.getFontStyle() : null, (3653631 & 16) != 0 ? textStyle.spanStyle.getFontSynthesis() : null, (3653631 & 32) != 0 ? textStyle.spanStyle.getFontFamily() : null, (3653631 & 64) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : null, (3653631 & 128) != 0 ? textStyle.spanStyle.getLetterSpacing() : 0L, (3653631 & 256) != 0 ? textStyle.spanStyle.getBaselineShift() : null, (3653631 & 512) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : null, (3653631 & 1024) != 0 ? textStyle.spanStyle.getLocaleList() : null, (3653631 & 2048) != 0 ? textStyle.spanStyle.getBackground() : 0L, (3653631 & 4096) != 0 ? textStyle.spanStyle.getTextDecoration() : null, (3653631 & 8192) != 0 ? textStyle.spanStyle.getShadow() : null, (3653631 & 16384) != 0 ? textStyle.paragraphStyle.getTextAlign() : null, (3653631 & 32768) != 0 ? textStyle.paragraphStyle.getTextDirection() : null, (3653631 & 65536) != 0 ? textStyle.paragraphStyle.getLineHeight() : 0L, (3653631 & 131072) != 0 ? textStyle.paragraphStyle.getTextIndent() : null, (3653631 & 262144) != 0 ? textStyle.platformStyle : DefaultPlatformTextStyle_androidKt.defaultPlatformTextStyle(), (3653631 & 524288) != 0 ? textStyle.paragraphStyle.getLineHeightStyle() : null, (3653631 & 1048576) != 0 ? textStyle.paragraphStyle.getLineBreak() : null, (3653631 & 2097152) != 0 ? textStyle.paragraphStyle.getHyphens() : null);
    }

    public static final TextStyle getDefaultTextStyle() {
        return DefaultTextStyle;
    }
}
