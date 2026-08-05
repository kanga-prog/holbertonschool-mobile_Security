package androidx.compose.ui.text.platform;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.EmojiSupportMatch;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.PlatformParagraphStyle;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.intl.AndroidLocale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.intl.PlatformLocale;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.unit.Density;
import androidx.core.text.TextUtilsCompat;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidParagraphIntrinsics.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aP\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00022\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\f0\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u001a+\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\b\u001a\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"hasEmojiCompat", "", "Landroidx/compose/ui/text/TextStyle;", "getHasEmojiCompat", "(Landroidx/compose/ui/text/TextStyle;)Z", "ActualParagraphIntrinsics", "Landroidx/compose/ui/text/ParagraphIntrinsics;", "text", "", "style", "spanStyles", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/SpanStyle;", "placeholders", "Landroidx/compose/ui/text/Placeholder;", "density", "Landroidx/compose/ui/unit/Density;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "resolveTextDirectionHeuristics", "", "textDirection", "Landroidx/compose/ui/text/style/TextDirection;", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "resolveTextDirectionHeuristics-9GRLPo0", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidParagraphIntrinsics_androidKt {
    /* JADX INFO: renamed from: resolveTextDirectionHeuristics-9GRLPo0$default, reason: not valid java name */
    public static /* synthetic */ int m5000resolveTextDirectionHeuristics9GRLPo0$default(TextDirection textDirection, LocaleList localeList, int i, Object obj) {
        if ((i & 1) != 0) {
            textDirection = null;
        }
        if ((i & 2) != 0) {
            localeList = null;
        }
        return m4999resolveTextDirectionHeuristics9GRLPo0(textDirection, localeList);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x006d  */
    /* JADX INFO: renamed from: resolveTextDirectionHeuristics-9GRLPo0, reason: not valid java name */
    public static final int m4999resolveTextDirectionHeuristics9GRLPo0(TextDirection textDirection, LocaleList localeList) {
        Locale currentLocale;
        int value = textDirection != null ? textDirection.getValue() : TextDirection.INSTANCE.m5147getContents_7Xco();
        if (TextDirection.m5143equalsimpl0(value, TextDirection.INSTANCE.m5148getContentOrLtrs_7Xco())) {
            return 2;
        }
        if (TextDirection.m5143equalsimpl0(value, TextDirection.INSTANCE.m5149getContentOrRtls_7Xco())) {
            return 3;
        }
        if (TextDirection.m5143equalsimpl0(value, TextDirection.INSTANCE.m5150getLtrs_7Xco())) {
            return 0;
        }
        if (TextDirection.m5143equalsimpl0(value, TextDirection.INSTANCE.m5151getRtls_7Xco())) {
            return 1;
        }
        if (TextDirection.m5143equalsimpl0(value, TextDirection.INSTANCE.m5147getContents_7Xco())) {
            if (localeList != null) {
                PlatformLocale platformLocale = localeList.get(0).getPlatformLocale();
                Intrinsics.checkNotNull(platformLocale, "null cannot be cast to non-null type androidx.compose.ui.text.intl.AndroidLocale");
                currentLocale = ((AndroidLocale) platformLocale).getJavaLocale();
                if (currentLocale == null) {
                    currentLocale = Locale.getDefault();
                }
            } else {
                currentLocale = Locale.getDefault();
            }
            switch (TextUtilsCompat.getLayoutDirectionFromLocale(currentLocale)) {
                case 0:
                default:
                    return 2;
                case 1:
                    return 3;
            }
        }
        throw new IllegalStateException("Invalid TextDirection.".toString());
    }

    public static final ParagraphIntrinsics ActualParagraphIntrinsics(String text, TextStyle style, List<AnnotatedString.Range<SpanStyle>> spanStyles, List<AnnotatedString.Range<Placeholder>> placeholders, Density density, FontFamily.Resolver fontFamilyResolver) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(spanStyles, "spanStyles");
        Intrinsics.checkNotNullParameter(placeholders, "placeholders");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(fontFamilyResolver, "fontFamilyResolver");
        return new AndroidParagraphIntrinsics(text, style, spanStyles, placeholders, fontFamilyResolver, density);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getHasEmojiCompat(TextStyle $this$hasEmojiCompat) {
        PlatformParagraphStyle paragraphSyle;
        PlatformTextStyle platformStyle = $this$hasEmojiCompat.getPlatformStyle();
        EmojiSupportMatch emojiSupportMatchM4644boximpl = (platformStyle == null || (paragraphSyle = platformStyle.getParagraphSyle()) == null) ? null : EmojiSupportMatch.m4644boximpl(paragraphSyle.getEmojiSupportMatch());
        return !(emojiSupportMatchM4644boximpl == null ? false : EmojiSupportMatch.m4647equalsimpl0(emojiSupportMatchM4644boximpl.getValue(), EmojiSupportMatch.INSTANCE.m4652getNone_3YsG6Y()));
    }
}
