package com.holberton.task1.ui.theme;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.DynamicTonalPaletteKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.core.view.WindowCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Theme.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00040\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"DarkColorScheme", "Landroidx/compose/material3/ColorScheme;", "LightColorScheme", "Task1Theme", "", "darkTheme", "", "dynamicColor", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(ZZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "app_debug"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class ThemeKt {
    private static final ColorScheme DarkColorScheme = ColorSchemeKt.m1428darkColorSchemeG1PFcw$default(ColorKt.getPurple80(), 0, 0, 0, 0, ColorKt.getPurpleGrey80(), 0, 0, 0, ColorKt.getPink80(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870366, null);
    private static final ColorScheme LightColorScheme = ColorSchemeKt.m1430lightColorSchemeG1PFcw$default(ColorKt.getPurple40(), 0, 0, 0, 0, ColorKt.getPurpleGrey40(), 0, 0, 0, ColorKt.getPink40(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870366, null);

    /* JADX WARN: Code duplicated, block: B:54:0x0096  */
    /* JADX WARN: Code duplicated, block: B:63:0x00d6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:65:0x00db  */
    /* JADX WARN: Code duplicated, block: B:68:0x0106  */
    /* JADX WARN: Code duplicated, block: B:71:0x012b  */
    public static final void Task1Theme(final boolean darkTheme, boolean dynamicColor, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        int $dirty;
        ColorScheme colorSchemeDynamicDarkColorScheme;
        final ColorScheme colorScheme;
        final View view;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1137886697);
        ComposerKt.sourceInformation($composer2, "C(Task1Theme)P(1,2)41@1260L21,55@1767L7,64@2070L114:Theme.kt#33sybn");
        int $dirty2 = $changed;
        if (($changed & 14) == 0) {
            $dirty2 |= ((i & 1) == 0 && $composer2.changed(darkTheme)) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changed(dynamicColor) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer2.changedInstance(content) ? 256 : 128;
        }
        if (($dirty2 & 731) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if ((i & 1) != 0) {
                    darkTheme = DarkThemeKt.isSystemInDarkTheme($composer2, 0);
                    $dirty2 &= -15;
                }
                if (i2 != 0) {
                    dynamicColor = true;
                } else {
                    $dirty = $dirty2;
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1137886697, $dirty, -1, "com.holberton.task1.ui.theme.Task1Theme (Theme.kt:45)");
                }
                $composer2.startReplaceableGroup(-1020377716);
                ComposerKt.sourceInformation($composer2, "48@1550L7");
                if (!dynamicColor && Build.VERSION.SDK_INT >= 31) {
                    ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer2.consume(localContext);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Context context = (Context) objConsume;
                    colorSchemeDynamicDarkColorScheme = darkTheme ? DynamicTonalPaletteKt.dynamicDarkColorScheme(context) : DynamicTonalPaletteKt.dynamicLightColorScheme(context);
                } else if (darkTheme) {
                    colorSchemeDynamicDarkColorScheme = DarkColorScheme;
                } else {
                    colorSchemeDynamicDarkColorScheme = LightColorScheme;
                }
                $composer2.endReplaceableGroup();
                colorScheme = colorSchemeDynamicDarkColorScheme;
                ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localView);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                view = (View) objConsume2;
                $composer2.startReplaceableGroup(-1020377365);
                ComposerKt.sourceInformation($composer2, "57@1813L245");
                if (!view.isInEditMode()) {
                    EffectsKt.SideEffect(new Function0<Unit>() { // from class: com.holberton.task1.ui.theme.ThemeKt.Task1Theme.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            Context context2 = view.getContext();
                            Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type android.app.Activity");
                            Window window = ((Activity) context2).getWindow();
                            window.setStatusBarColor(androidx.compose.ui.graphics.ColorKt.m3025toArgb8_81llA(colorScheme.m1385getPrimary0d7_KjU()));
                            WindowCompat.getInsetsController(window, view).setAppearanceLightStatusBars(darkTheme);
                        }
                    }, $composer2, 0);
                }
                $composer2.endReplaceableGroup();
                MaterialThemeKt.MaterialTheme(colorScheme, null, TypeKt.getTypography(), content, $composer2, (($dirty << 3) & 7168) | 384, 2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 1) != 0) {
                    $dirty2 &= -15;
                }
            }
            $dirty = $dirty2;
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1137886697, $dirty, -1, "com.holberton.task1.ui.theme.Task1Theme (Theme.kt:45)");
            }
            $composer2.startReplaceableGroup(-1020377716);
            ComposerKt.sourceInformation($composer2, "48@1550L7");
            if (!dynamicColor) {
                if (darkTheme) {
                    colorSchemeDynamicDarkColorScheme = DarkColorScheme;
                } else {
                    colorSchemeDynamicDarkColorScheme = LightColorScheme;
                }
            } else if (darkTheme) {
                colorSchemeDynamicDarkColorScheme = DarkColorScheme;
            } else {
                colorSchemeDynamicDarkColorScheme = LightColorScheme;
            }
            $composer2.endReplaceableGroup();
            colorScheme = colorSchemeDynamicDarkColorScheme;
            ProvidableCompositionLocal<View> localView2 = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localView2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            view = (View) objConsume3;
            $composer2.startReplaceableGroup(-1020377365);
            ComposerKt.sourceInformation($composer2, "57@1813L245");
            if (!view.isInEditMode()) {
                EffectsKt.SideEffect(new Function0<Unit>() { // from class: com.holberton.task1.ui.theme.ThemeKt.Task1Theme.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Context context2 = view.getContext();
                        Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type android.app.Activity");
                        Window window = ((Activity) context2).getWindow();
                        window.setStatusBarColor(androidx.compose.ui.graphics.ColorKt.m3025toArgb8_81llA(colorScheme.m1385getPrimary0d7_KjU()));
                        WindowCompat.getInsetsController(window, view).setAppearanceLightStatusBars(darkTheme);
                    }
                }, $composer2, 0);
            }
            $composer2.endReplaceableGroup();
            MaterialThemeKt.MaterialTheme(colorScheme, null, TypeKt.getTypography(), content, $composer2, (($dirty << 3) & 7168) | 384, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final boolean z = darkTheme;
        final boolean z2 = dynamicColor;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.holberton.task1.ui.theme.ThemeKt.Task1Theme.2
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

            public final void invoke(Composer composer, int i3) {
                ThemeKt.Task1Theme(z, z2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }
}
