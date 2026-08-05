package androidx.compose.material3;

import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IncludeFontPaddingHelper.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"copyAndSetFontPadding", "Landroidx/compose/ui/text/TextStyle;", "style", "includeFontPadding", "", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IncludeFontPaddingHelper_androidKt {
    public static final TextStyle copyAndSetFontPadding(TextStyle style, boolean includeFontPadding) {
        Intrinsics.checkNotNullParameter(style, "style");
        return style.m4782copyCXVQc50((3653631 & 1) != 0 ? style.spanStyle.m4727getColor0d7_KjU() : 0L, (3653631 & 2) != 0 ? style.spanStyle.getFontSize() : 0L, (3653631 & 4) != 0 ? style.spanStyle.getFontWeight() : null, (3653631 & 8) != 0 ? style.spanStyle.getFontStyle() : null, (3653631 & 16) != 0 ? style.spanStyle.getFontSynthesis() : null, (3653631 & 32) != 0 ? style.spanStyle.getFontFamily() : null, (3653631 & 64) != 0 ? style.spanStyle.getFontFeatureSettings() : null, (3653631 & 128) != 0 ? style.spanStyle.getLetterSpacing() : 0L, (3653631 & 256) != 0 ? style.spanStyle.getBaselineShift() : null, (3653631 & 512) != 0 ? style.spanStyle.getTextGeometricTransform() : null, (3653631 & 1024) != 0 ? style.spanStyle.getLocaleList() : null, (3653631 & 2048) != 0 ? style.spanStyle.getBackground() : 0L, (3653631 & 4096) != 0 ? style.spanStyle.getTextDecoration() : null, (3653631 & 8192) != 0 ? style.spanStyle.getShadow() : null, (3653631 & 16384) != 0 ? style.paragraphStyle.getTextAlign() : null, (3653631 & 32768) != 0 ? style.paragraphStyle.getTextDirection() : null, (3653631 & 65536) != 0 ? style.paragraphStyle.getLineHeight() : 0L, (3653631 & 131072) != 0 ? style.paragraphStyle.getTextIndent() : null, (3653631 & 262144) != 0 ? style.platformStyle : new PlatformTextStyle(includeFontPadding), (3653631 & 524288) != 0 ? style.paragraphStyle.getLineHeightStyle() : null, (3653631 & 1048576) != 0 ? style.paragraphStyle.getLineBreak() : null, (3653631 & 2097152) != 0 ? style.paragraphStyle.getHyphens() : null);
    }
}
