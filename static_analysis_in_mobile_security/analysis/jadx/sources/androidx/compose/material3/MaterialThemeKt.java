package androidx.compose.material3;

import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material.ripple.RippleAlpha;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.material.ripple.RippleThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.Color;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MaterialTheme.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0011\u0010\f\u001a\r\u0012\u0004\u0012\u00020\u00050\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0002\u0010\u000f\u001a\u0015\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0002\u0010\u0012\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"DefaultRippleAlpha", "Landroidx/compose/material/ripple/RippleAlpha;", "TextSelectionBackgroundOpacity", "", "MaterialTheme", "", "colorScheme", "Landroidx/compose/material3/ColorScheme;", "shapes", "Landroidx/compose/material3/Shapes;", "typography", "Landroidx/compose/material3/Typography;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/material3/Shapes;Landroidx/compose/material3/Typography;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "rememberTextSelectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/selection/TextSelectionColors;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MaterialThemeKt {
    private static final RippleAlpha DefaultRippleAlpha = new RippleAlpha(0.16f, 0.12f, 0.08f, 0.12f);
    public static final float TextSelectionBackgroundOpacity = 0.4f;

    public static final void MaterialTheme(ColorScheme colorScheme, Shapes shapes, Typography typography, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        ColorScheme colorScheme2;
        Shapes shapes2;
        Typography typography2;
        int $dirty;
        final Typography typography3;
        Object value$iv$iv;
        Typography typography4;
        ColorScheme colorScheme3;
        Shapes shapes3;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-2127166334);
        ComposerKt.sourceInformation($composer2, "C(MaterialTheme)P(!1,2,3)58@2824L11,59@2872L6,60@2923L10,*63@3007L194,70@3286L16,71@3329L50,72@3384L417:MaterialTheme.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 14) == 0) {
            if ((i & 1) == 0) {
                colorScheme2 = colorScheme;
                int i2 = $composer2.changed(colorScheme2) ? 4 : 2;
                $dirty2 |= i2;
            } else {
                colorScheme2 = colorScheme;
            }
            $dirty2 |= i2;
        } else {
            colorScheme2 = colorScheme;
        }
        if (($changed & 112) == 0) {
            if ((i & 2) == 0) {
                shapes2 = shapes;
                int i3 = $composer2.changed(shapes2) ? 32 : 16;
                $dirty2 |= i3;
            } else {
                shapes2 = shapes;
            }
            $dirty2 |= i3;
        } else {
            shapes2 = shapes;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                typography2 = typography;
                int i4 = $composer2.changed(typography2) ? 256 : 128;
                $dirty2 |= i4;
            } else {
                typography2 = typography;
            }
            $dirty2 |= i4;
        } else {
            typography2 = typography;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer2.changedInstance(content) ? 2048 : 1024;
        }
        if (($dirty2 & 5851) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            shapes3 = shapes2;
            typography4 = typography2;
            colorScheme3 = colorScheme2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if ((i & 1) != 0) {
                    colorScheme2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6);
                    $dirty2 &= -15;
                }
                if ((i & 2) != 0) {
                    shapes2 = MaterialTheme.INSTANCE.getShapes($composer2, 6);
                    $dirty2 &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty = $dirty2 & (-897);
                    typography3 = MaterialTheme.INSTANCE.getTypography($composer2, 6);
                } else {
                    $dirty = $dirty2;
                    typography3 = typography2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 1) != 0) {
                    $dirty2 &= -15;
                }
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty = $dirty2 & (-897);
                    typography3 = typography2;
                } else {
                    $dirty = $dirty2;
                    typography3 = typography2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2127166334, $dirty, -1, "androidx.compose.material3.MaterialTheme (MaterialTheme.kt:57)");
            }
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                ColorScheme colorScheme4 = colorScheme2;
                value$iv$iv = colorScheme4.m1365copyG1PFcw((536870911 & 1) != 0 ? colorScheme4.m1385getPrimary0d7_KjU() : 0L, (536870911 & 2) != 0 ? colorScheme4.m1375getOnPrimary0d7_KjU() : 0L, (536870911 & 4) != 0 ? colorScheme4.m1386getPrimaryContainer0d7_KjU() : 0L, (536870911 & 8) != 0 ? colorScheme4.m1376getOnPrimaryContainer0d7_KjU() : 0L, (536870911 & 16) != 0 ? colorScheme4.m1370getInversePrimary0d7_KjU() : 0L, (536870911 & 32) != 0 ? colorScheme4.m1388getSecondary0d7_KjU() : 0L, (536870911 & 64) != 0 ? colorScheme4.m1377getOnSecondary0d7_KjU() : 0L, (536870911 & 128) != 0 ? colorScheme4.m1389getSecondaryContainer0d7_KjU() : 0L, (536870911 & 256) != 0 ? colorScheme4.m1378getOnSecondaryContainer0d7_KjU() : 0L, (536870911 & 512) != 0 ? colorScheme4.m1393getTertiary0d7_KjU() : 0L, (536870911 & 1024) != 0 ? colorScheme4.m1381getOnTertiary0d7_KjU() : 0L, (536870911 & 2048) != 0 ? colorScheme4.m1394getTertiaryContainer0d7_KjU() : 0L, (536870911 & 4096) != 0 ? colorScheme4.m1382getOnTertiaryContainer0d7_KjU() : 0L, (536870911 & 8192) != 0 ? colorScheme4.m1366getBackground0d7_KjU() : 0L, (536870911 & 16384) != 0 ? colorScheme4.m1372getOnBackground0d7_KjU() : 0L, (536870911 & 32768) != 0 ? colorScheme4.m1390getSurface0d7_KjU() : 0L, (536870911 & 65536) != 0 ? colorScheme4.m1379getOnSurface0d7_KjU() : 0L, (536870911 & 131072) != 0 ? colorScheme4.m1392getSurfaceVariant0d7_KjU() : 0L, (536870911 & 262144) != 0 ? colorScheme4.m1380getOnSurfaceVariant0d7_KjU() : 0L, (536870911 & 524288) != 0 ? colorScheme4.m1391getSurfaceTint0d7_KjU() : 0L, (536870911 & 1048576) != 0 ? colorScheme4.m1371getInverseSurface0d7_KjU() : 0L, (536870911 & 2097152) != 0 ? colorScheme4.m1369getInverseOnSurface0d7_KjU() : 0L, (536870911 & 4194304) != 0 ? colorScheme4.m1367getError0d7_KjU() : 0L, (536870911 & 8388608) != 0 ? colorScheme4.m1373getOnError0d7_KjU() : 0L, (536870911 & 16777216) != 0 ? colorScheme4.m1368getErrorContainer0d7_KjU() : 0L, (536870911 & 33554432) != 0 ? colorScheme4.m1374getOnErrorContainer0d7_KjU() : 0L, (536870911 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? colorScheme4.m1383getOutline0d7_KjU() : 0L, (536870911 & 134217728) != 0 ? colorScheme4.m1384getOutlineVariant0d7_KjU() : 0L, (536870911 & 268435456) != 0 ? colorScheme4.m1387getScrim0d7_KjU() : 0L);
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            ColorScheme $this$MaterialTheme_u24lambda_u241 = (ColorScheme) value$iv$iv;
            ColorSchemeKt.updateColorSchemeFrom($this$MaterialTheme_u24lambda_u241, colorScheme2);
            ColorScheme rememberedColorScheme = (ColorScheme) value$iv$iv;
            final int $dirty3 = $dirty;
            Indication rippleIndication = RippleKt.m1298rememberRipple9IZ8Weo(false, 0.0f, 0L, $composer2, 0, 7);
            SelectionColors selectionColors = rememberTextSelectionColors(rememberedColorScheme, $composer2, 0);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ColorSchemeKt.getLocalColorScheme().provides(rememberedColorScheme), IndicationKt.getLocalIndication().provides(rippleIndication), RippleThemeKt.getLocalRippleTheme().provides(MaterialRippleTheme.INSTANCE), ShapesKt.getLocalShapes().provides(shapes2), TextSelectionColorsKt.getLocalTextSelectionColors().provides(selectionColors), TypographyKt.getLocalTypography().provides(typography3)}, ComposableLambdaKt.composableLambda($composer2, -1066563262, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialTheme.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C80@3730L65:MaterialTheme.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1066563262, $changed2, -1, "androidx.compose.material3.MaterialTheme.<anonymous> (MaterialTheme.kt:79)");
                    }
                    TextKt.ProvideTextStyle(typography3.getBodyLarge(), content, $composer3, ($dirty3 >> 6) & 112);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            typography4 = typography3;
            colorScheme3 = colorScheme2;
            shapes3 = shapes2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final ColorScheme colorScheme5 = colorScheme3;
        final Shapes shapes4 = shapes3;
        final Typography typography5 = typography4;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MaterialThemeKt.MaterialTheme.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i5) {
                MaterialThemeKt.MaterialTheme(colorScheme5, shapes4, typography5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static final SelectionColors rememberTextSelectionColors(ColorScheme colorScheme, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(colorScheme, "colorScheme");
        $composer.startReplaceableGroup(1866455512);
        ComposerKt.sourceInformation($composer, "C(rememberTextSelectionColors)134@5274L198:MaterialTheme.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1866455512, $changed, -1, "androidx.compose.material3.rememberTextSelectionColors (MaterialTheme.kt:132)");
        }
        long primaryColor = colorScheme.m1385getPrimary0d7_KjU();
        Object key1$iv = Color.m2961boximpl(primaryColor);
        $composer.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(key1$iv);
        Object value$iv$iv = $composer.rememberedValue();
        if (invalid$iv$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new SelectionColors(primaryColor, Color.m2969copywmQWz5c(primaryColor, (14 & 1) != 0 ? Color.m2973getAlphaimpl(primaryColor) : 0.4f, (14 & 2) != 0 ? Color.m2977getRedimpl(primaryColor) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(primaryColor) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(primaryColor) : 0.0f), null);
            $composer.updateRememberedValue(value$iv$iv);
        }
        $composer.endReplaceableGroup();
        SelectionColors selectionColors = (SelectionColors) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return selectionColors;
    }
}
