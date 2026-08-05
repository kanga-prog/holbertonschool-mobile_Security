package androidx.compose.material;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Strings.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0006"}, d2 = {"getString", "", "string", "Landroidx/compose/material/Strings;", "getString-4foXLRw", "(ILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Strings_androidKt {
    /* JADX INFO: renamed from: getString-4foXLRw, reason: not valid java name */
    public static final String m1208getString4foXLRw(int string, Composer $composer, int $changed) {
        String string2;
        $composer.startReplaceableGroup(-726638443);
        ComposerKt.sourceInformation($composer, "C(getString)P(0:c#material.Strings)25@921L7,26@962L7:Strings.android.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-726638443, $changed, -1, "androidx.compose.material.getString (Strings.android.kt:24)");
        }
        ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        $composer.consume(localConfiguration);
        ComposerKt.sourceInformationMarkerEnd($composer);
        CompositionLocal this_$iv = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(this_$iv);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Resources resources = ((Context) objConsume).getResources();
        if (Strings.m1197equalsimpl0(string, Strings.INSTANCE.m1205getNavigationMenuUdPEhr4())) {
            string2 = resources.getString(androidx.compose.ui.R.string.navigation_menu);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.navigation_menu)");
        } else if (Strings.m1197equalsimpl0(string, Strings.INSTANCE.m1201getCloseDrawerUdPEhr4())) {
            string2 = resources.getString(androidx.compose.ui.R.string.close_drawer);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.close_drawer)");
        } else if (Strings.m1197equalsimpl0(string, Strings.INSTANCE.m1202getCloseSheetUdPEhr4())) {
            string2 = resources.getString(androidx.compose.ui.R.string.close_sheet);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.close_sheet)");
        } else if (Strings.m1197equalsimpl0(string, Strings.INSTANCE.m1203getDefaultErrorMessageUdPEhr4())) {
            string2 = resources.getString(androidx.compose.ui.R.string.default_error_message);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…ng.default_error_message)");
        } else if (Strings.m1197equalsimpl0(string, Strings.INSTANCE.m1204getExposedDropdownMenuUdPEhr4())) {
            string2 = resources.getString(androidx.compose.ui.R.string.dropdown_menu);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.dropdown_menu)");
        } else if (Strings.m1197equalsimpl0(string, Strings.INSTANCE.m1207getSliderRangeStartUdPEhr4())) {
            string2 = resources.getString(androidx.compose.ui.R.string.range_start);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.range_start)");
        } else if (Strings.m1197equalsimpl0(string, Strings.INSTANCE.m1206getSliderRangeEndUdPEhr4())) {
            string2 = resources.getString(androidx.compose.ui.R.string.range_end);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.range_end)");
        } else {
            string2 = "";
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return string2;
    }
}
