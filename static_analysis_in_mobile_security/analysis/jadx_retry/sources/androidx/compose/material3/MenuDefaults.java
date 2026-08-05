package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.MenuTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: compiled from: Menu.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JQ\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\nH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Landroidx/compose/material3/MenuDefaults;", "", "()V", "DropdownMenuItemContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getDropdownMenuItemContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "itemColors", "Landroidx/compose/material3/MenuItemColors;", "textColor", "Landroidx/compose/ui/graphics/Color;", "leadingIconColor", "trailingIconColor", "disabledTextColor", "disabledLeadingIconColor", "disabledTrailingIconColor", "itemColors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/MenuItemColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MenuDefaults {
    public static final int $stable = 0;
    public static final MenuDefaults INSTANCE = new MenuDefaults();
    private static final PaddingValues DropdownMenuItemContentPadding = PaddingKt.m481PaddingValuesYgX7TsA(MenuKt.DropdownMenuItemHorizontalPadding, Dp.m5274constructorimpl(0));

    private MenuDefaults() {
    }

    /* JADX INFO: renamed from: itemColors-5tl4gsc, reason: not valid java name */
    public final MenuItemColors m1610itemColors5tl4gsc(long textColor, long leadingIconColor, long trailingIconColor, long disabledTextColor, long disabledLeadingIconColor, long disabledTrailingIconColor, Composer $composer, int $changed, int i) {
        long disabledTextColor2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        $composer.startReplaceableGroup(-1278543580);
        ComposerKt.sourceInformation($composer, "C(itemColors)P(4:c#ui.graphics.Color,3:c#ui.graphics.Color,5:c#ui.graphics.Color,1:c#ui.graphics.Color,0:c#ui.graphics.Color,2:c#ui.graphics.Color)235@8727L9,236@8808L9,237@8891L9,239@8991L9,241@9163L9,243@9335L9:Menu.kt#uh7d8r");
        long textColor2 = (i & 1) != 0 ? ColorSchemeKt.toColor(MenuTokens.INSTANCE.getListItemLabelTextColor(), $composer, 6) : textColor;
        long leadingIconColor2 = (i & 2) != 0 ? ColorSchemeKt.toColor(MenuTokens.INSTANCE.getListItemLeadingIconColor(), $composer, 6) : leadingIconColor;
        long trailingIconColor2 = (i & 4) != 0 ? ColorSchemeKt.toColor(MenuTokens.INSTANCE.getListItemTrailingIconColor(), $composer, 6) : trailingIconColor;
        if ((i & 8) != 0) {
            long color = ColorSchemeKt.toColor(MenuTokens.INSTANCE.getListItemDisabledLabelTextColor(), $composer, 6);
            disabledTextColor2 = Color.m2969copywmQWz5c(color, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        if ((i & 16) != 0) {
            long color2 = ColorSchemeKt.toColor(MenuTokens.INSTANCE.getListItemDisabledLeadingIconColor(), $composer, 6);
            disabledLeadingIconColor2 = Color.m2969copywmQWz5c(color2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color2) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color2) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        if ((i & 32) != 0) {
            long color3 = ColorSchemeKt.toColor(MenuTokens.INSTANCE.getListItemDisabledTrailingIconColor(), $composer, 6);
            disabledTrailingIconColor2 = Color.m2969copywmQWz5c(color3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color3) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color3) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1278543580, $changed, -1, "androidx.compose.material3.MenuDefaults.itemColors (Menu.kt:234)");
        }
        MenuItemColors menuItemColors = new MenuItemColors(textColor2, leadingIconColor2, trailingIconColor2, disabledTextColor2, disabledLeadingIconColor2, disabledTrailingIconColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return menuItemColors;
    }

    public final PaddingValues getDropdownMenuItemContentPadding() {
        return DropdownMenuItemContentPadding;
    }
}
