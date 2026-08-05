package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Scaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a´\u0001\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0013\b\u0002\u0010\f\u001a\r\u0012\u0004\u0012\u00020\t0\r¢\u0006\u0002\b\u000e2\u0013\b\u0002\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\t0\r¢\u0006\u0002\b\u000e2\u0013\b\u0002\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\t0\r¢\u0006\u0002\b\u000e2\u0013\b\u0002\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\t0\r¢\u0006\u0002\b\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\t0\u001a¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u008a\u0001\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u00132\u0011\u0010\f\u001a\r\u0012\u0004\u0012\u00020\t0\r¢\u0006\u0002\b\u000e2\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\t0\u001a¢\u0006\u0002\b\u000e2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\t0\r¢\u0006\u0002\b\u000e2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\t0\r¢\u0006\u0002\b\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\t0\r¢\u0006\u0002\b\u000eH\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u001c\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"FabSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "LocalFabPlacement", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/FabPlacement;", "getLocalFabPlacement", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "Scaffold", "", "modifier", "Landroidx/compose/ui/Modifier;", "topBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "bottomBar", "snackbarHost", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material3/FabPosition;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "contentWindowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/PaddingValues;", "Scaffold-TvnljyQ", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;IJJLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ScaffoldLayout", "fabPosition", "snackbar", "fab", "ScaffoldLayout-FMILGgc", "(ILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScaffoldKt {
    private static final ProvidableCompositionLocal<FabPlacement> LocalFabPlacement = CompositionLocalKt.staticCompositionLocalOf(new Function0<FabPlacement>() { // from class: androidx.compose.material3.ScaffoldKt$LocalFabPlacement$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FabPlacement invoke() {
            return null;
        }
    });
    private static final float FabSpacing = Dp.m5274constructorimpl(16);

    /* JADX WARN: Code duplicated, block: B:117:0x0175  */
    /* JADX WARN: Code duplicated, block: B:119:0x0185  */
    /* JADX WARN: Code duplicated, block: B:132:0x01c1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:133:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:134:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:136:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:137:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:139:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:140:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:142:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:143:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:145:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:146:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:148:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:149:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:152:0x0203  */
    /* JADX WARN: Code duplicated, block: B:153:0x0214  */
    /* JADX WARN: Code duplicated, block: B:156:0x021c  */
    /* JADX WARN: Code duplicated, block: B:157:0x0227  */
    /* JADX WARN: Code duplicated, block: B:160:0x022c  */
    /* JADX WARN: Code duplicated, block: B:161:0x023b  */
    /* JADX WARN: Code duplicated, block: B:164:0x024a  */
    /* JADX WARN: Code duplicated, block: B:167:0x02a1  */
    /* JADX WARN: Code duplicated, block: B:171:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:172:0x02c2  */
    /* JADX INFO: renamed from: Scaffold-TvnljyQ, reason: not valid java name */
    public static final void m1688ScaffoldTvnljyQ(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, int floatingActionButtonPosition, long containerColor, long contentColor, WindowInsets contentWindowInsets, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        int i2;
        long j;
        int i3;
        Modifier.Companion modifier2;
        Function2<? super Composer, ? super Integer, Unit> function2M1452getLambda1$material3_release;
        Function2<? super Composer, ? super Integer, Unit> function2M1453getLambda2$material3_release;
        Function2<? super Composer, ? super Integer, Unit> function2M1454getLambda3$material3_release;
        Function2<? super Composer, ? super Integer, Unit> function2M1455getLambda4$material3_release;
        int floatingActionButtonPosition2;
        long containerColor2;
        long contentColor2;
        WindowInsets contentWindowInsets2;
        int $dirty;
        int $dirty2;
        Modifier modifier3;
        int floatingActionButtonPosition3;
        Function2<? super Composer, ? super Integer, Unit> function9;
        long containerColor3;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        long contentColor3;
        WindowInsets contentWindowInsets3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-1219521777);
        ComposerKt.sourceInformation($composer2, "C(Scaffold)P(7,9!1,8,5,6:c#material3.FabPosition,1:c#ui.graphics.Color,3:c#ui.graphics.Color,4)80@4062L11,81@4112L31,82@4202L19,85@4280L405:Scaffold.kt#uh7d8r");
        int $dirty3 = $changed;
        int i4 = i & 1;
        if (i4 != 0) {
            $dirty3 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty3 |= $composer2.changed(modifier) ? 4 : 2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty3 |= 48;
            function6 = function2;
        } else if (($changed & 112) == 0) {
            function6 = function2;
            $dirty3 |= $composer2.changedInstance(function6) ? 32 : 16;
        } else {
            function6 = function2;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty3 |= 384;
            function7 = function3;
        } else if (($changed & 896) == 0) {
            function7 = function3;
            $dirty3 |= $composer2.changedInstance(function7) ? 256 : 128;
        } else {
            function7 = function3;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty3 |= $composer2.changedInstance(function4) ? 2048 : 1024;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty3 |= 24576;
            function8 = function5;
        } else if ((57344 & $changed) == 0) {
            function8 = function5;
            $dirty3 |= $composer2.changedInstance(function8) ? 16384 : 8192;
        } else {
            function8 = function5;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty3 |= $composer2.changed(floatingActionButtonPosition) ? 131072 : 65536;
        }
        if (($changed & 3670016) == 0) {
            if ((i & 64) == 0) {
                i2 = i8;
                int i10 = $composer2.changed(containerColor) ? 1048576 : 524288;
                $dirty3 |= i10;
            } else {
                i2 = i8;
            }
            $dirty3 |= i10;
        } else {
            i2 = i8;
        }
        if (($changed & 29360128) == 0) {
            if ((i & 128) == 0) {
                j = contentColor;
                int i11 = $composer2.changed(j) ? 8388608 : 4194304;
                $dirty3 |= i11;
            } else {
                j = contentColor;
            }
            $dirty3 |= i11;
        } else {
            j = contentColor;
        }
        if ((234881024 & $changed) == 0) {
            $dirty3 |= ((i & 256) == 0 && $composer2.changed(contentWindowInsets)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) == 0) {
            if ((1879048192 & $changed) == 0) {
                i3 = $composer2.changedInstance(content) ? 536870912 : 268435456;
            }
            if ((1533916891 & $dirty3) == 306783378 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i5 != 0) {
                        function2M1452getLambda1$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1452getLambda1$material3_release();
                    } else {
                        function2M1452getLambda1$material3_release = function6;
                    }
                    if (i6 != 0) {
                        function2M1453getLambda2$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1453getLambda2$material3_release();
                    } else {
                        function2M1453getLambda2$material3_release = function7;
                    }
                    if (i7 != 0) {
                        function2M1454getLambda3$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1454getLambda3$material3_release();
                    } else {
                        function2M1454getLambda3$material3_release = function4;
                    }
                    if (i2 != 0) {
                        function2M1455getLambda4$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1455getLambda4$material3_release();
                    } else {
                        function2M1455getLambda4$material3_release = function8;
                    }
                    if (i9 != 0) {
                        floatingActionButtonPosition2 = FabPosition.INSTANCE.m1537getEndERTFSPs();
                    } else {
                        floatingActionButtonPosition2 = floatingActionButtonPosition;
                    }
                    if ((i & 64) != 0) {
                        $dirty3 &= -3670017;
                        containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1366getBackground0d7_KjU();
                    } else {
                        containerColor2 = containerColor;
                    }
                    if ((i & 128) != 0) {
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty3 >> 18) & 14);
                        $dirty3 &= -29360129;
                    } else {
                        contentColor2 = j;
                    }
                    if ((i & 256) != 0) {
                        contentWindowInsets2 = ScaffoldDefaults.INSTANCE.getContentWindowInsets($composer2, 6);
                        $dirty = $dirty3 & (-234881025);
                        $dirty2 = floatingActionButtonPosition2;
                        function7 = function2M1453getLambda2$material3_release;
                    } else {
                        contentWindowInsets2 = contentWindowInsets;
                        $dirty = $dirty3;
                        $dirty2 = floatingActionButtonPosition2;
                        function7 = function2M1453getLambda2$material3_release;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 64) != 0) {
                        $dirty3 &= -3670017;
                    }
                    if ((i & 128) != 0) {
                        $dirty3 &= -29360129;
                    }
                    if ((i & 256) != 0) {
                        int i12 = $dirty3 & (-234881025);
                        $dirty2 = floatingActionButtonPosition;
                        containerColor2 = containerColor;
                        function2M1452getLambda1$material3_release = function6;
                        contentColor2 = j;
                        function2M1455getLambda4$material3_release = function8;
                        function2M1454getLambda3$material3_release = function4;
                        contentWindowInsets2 = contentWindowInsets;
                        $dirty = i12;
                        modifier2 = modifier;
                    } else {
                        modifier2 = modifier;
                        containerColor2 = containerColor;
                        function2M1452getLambda1$material3_release = function6;
                        contentColor2 = j;
                        function2M1455getLambda4$material3_release = function8;
                        function2M1454getLambda3$material3_release = function4;
                        contentWindowInsets2 = contentWindowInsets;
                        $dirty = $dirty3;
                        $dirty2 = floatingActionButtonPosition;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1219521777, $dirty, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:73)");
                }
                final int i13 = $dirty2;
                final Function2<? super Composer, ? super Integer, Unit> function13 = function2M1452getLambda1$material3_release;
                final Function2<? super Composer, ? super Integer, Unit> function14 = function2M1454getLambda3$material3_release;
                final Function2<? super Composer, ? super Integer, Unit> function15 = function2M1455getLambda4$material3_release;
                final WindowInsets windowInsets = contentWindowInsets2;
                final Function2<? super Composer, ? super Integer, Unit> function16 = function7;
                final int i14 = $dirty;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier2, null, containerColor2, contentColor2, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -1979205334, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$1
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
                        ComposerKt.sourceInformation($composer3, "C86@4372L307:Scaffold.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1979205334, $changed2, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:85)");
                            }
                            int i15 = i13;
                            Function2<Composer, Integer, Unit> function17 = function13;
                            Function3<PaddingValues, Composer, Integer, Unit> function18 = content;
                            Function2<Composer, Integer, Unit> function19 = function14;
                            Function2<Composer, Integer, Unit> function20 = function15;
                            WindowInsets windowInsets2 = windowInsets;
                            Function2<Composer, Integer, Unit> function21 = function16;
                            int i16 = i14;
                            ScaffoldKt.m1689ScaffoldLayoutFMILGgc(i15, function17, function18, function19, function20, windowInsets2, function21, $composer3, ((i16 >> 15) & 14) | (i16 & 112) | ((i16 >> 21) & 896) | (i16 & 7168) | (57344 & i16) | ((i16 >> 9) & 458752) | ((i16 << 12) & 3670016));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, ($dirty & 14) | 12582912 | (($dirty >> 12) & 896) | (($dirty >> 12) & 7168), 114);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                floatingActionButtonPosition3 = $dirty2;
                function9 = function2M1452getLambda1$material3_release;
                containerColor3 = containerColor2;
                function10 = function2M1454getLambda3$material3_release;
                function11 = function2M1455getLambda4$material3_release;
                function12 = function7;
                contentColor3 = contentColor2;
                contentWindowInsets3 = contentWindowInsets2;
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier;
                function10 = function4;
                floatingActionButtonPosition3 = floatingActionButtonPosition;
                containerColor3 = containerColor;
                contentWindowInsets3 = contentWindowInsets;
                function9 = function6;
                function12 = function7;
                contentColor3 = j;
                function11 = function8;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final Function2<? super Composer, ? super Integer, Unit> function17 = function9;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function12;
            final Function2<? super Composer, ? super Integer, Unit> function19 = function10;
            final Function2<? super Composer, ? super Integer, Unit> function20 = function11;
            final int i15 = floatingActionButtonPosition3;
            final long j2 = containerColor3;
            final long j3 = contentColor3;
            final WindowInsets windowInsets2 = contentWindowInsets3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
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

                public final void invoke(Composer composer, int i16) {
                    ScaffoldKt.m1688ScaffoldTvnljyQ(modifier4, function17, function18, function19, function20, i15, j2, j3, windowInsets2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i3 = 805306368;
        $dirty3 |= i3;
        if ((1533916891 & $dirty3) == 306783378) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i5 != 0) {
                    function2M1452getLambda1$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1452getLambda1$material3_release();
                } else {
                    function2M1452getLambda1$material3_release = function6;
                }
                if (i6 != 0) {
                    function2M1453getLambda2$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1453getLambda2$material3_release();
                } else {
                    function2M1453getLambda2$material3_release = function7;
                }
                if (i7 != 0) {
                    function2M1454getLambda3$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1454getLambda3$material3_release();
                } else {
                    function2M1454getLambda3$material3_release = function4;
                }
                if (i2 != 0) {
                    function2M1455getLambda4$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1455getLambda4$material3_release();
                } else {
                    function2M1455getLambda4$material3_release = function8;
                }
                if (i9 != 0) {
                    floatingActionButtonPosition2 = FabPosition.INSTANCE.m1537getEndERTFSPs();
                } else {
                    floatingActionButtonPosition2 = floatingActionButtonPosition;
                }
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                    containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1366getBackground0d7_KjU();
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 128) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty3 >> 18) & 14);
                    $dirty3 &= -29360129;
                } else {
                    contentColor2 = j;
                }
                if ((i & 256) != 0) {
                    contentWindowInsets2 = ScaffoldDefaults.INSTANCE.getContentWindowInsets($composer2, 6);
                    $dirty = $dirty3 & (-234881025);
                    $dirty2 = floatingActionButtonPosition2;
                    function7 = function2M1453getLambda2$material3_release;
                } else {
                    contentWindowInsets2 = contentWindowInsets;
                    $dirty = $dirty3;
                    $dirty2 = floatingActionButtonPosition2;
                    function7 = function2M1453getLambda2$material3_release;
                }
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i5 != 0) {
                    function2M1452getLambda1$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1452getLambda1$material3_release();
                } else {
                    function2M1452getLambda1$material3_release = function6;
                }
                if (i6 != 0) {
                    function2M1453getLambda2$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1453getLambda2$material3_release();
                } else {
                    function2M1453getLambda2$material3_release = function7;
                }
                if (i7 != 0) {
                    function2M1454getLambda3$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1454getLambda3$material3_release();
                } else {
                    function2M1454getLambda3$material3_release = function4;
                }
                if (i2 != 0) {
                    function2M1455getLambda4$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1455getLambda4$material3_release();
                } else {
                    function2M1455getLambda4$material3_release = function8;
                }
                if (i9 != 0) {
                    floatingActionButtonPosition2 = FabPosition.INSTANCE.m1537getEndERTFSPs();
                } else {
                    floatingActionButtonPosition2 = floatingActionButtonPosition;
                }
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                    containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1366getBackground0d7_KjU();
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 128) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty3 >> 18) & 14);
                    $dirty3 &= -29360129;
                } else {
                    contentColor2 = j;
                }
                if ((i & 256) != 0) {
                    contentWindowInsets2 = ScaffoldDefaults.INSTANCE.getContentWindowInsets($composer2, 6);
                    $dirty = $dirty3 & (-234881025);
                    $dirty2 = floatingActionButtonPosition2;
                    function7 = function2M1453getLambda2$material3_release;
                } else {
                    contentWindowInsets2 = contentWindowInsets;
                    $dirty = $dirty3;
                    $dirty2 = floatingActionButtonPosition2;
                    function7 = function2M1453getLambda2$material3_release;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1219521777, $dirty, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:73)");
            }
            final int i16 = $dirty2;
            final Function2<? super Composer, ? super Integer, Unit> function110 = function2M1452getLambda1$material3_release;
            final Function2<? super Composer, ? super Integer, Unit> function111 = function2M1454getLambda3$material3_release;
            final Function2<? super Composer, ? super Integer, Unit> function112 = function2M1455getLambda4$material3_release;
            final WindowInsets windowInsets3 = contentWindowInsets2;
            final Function2<? super Composer, ? super Integer, Unit> function113 = function7;
            final int i17 = $dirty;
            SurfaceKt.m1806SurfaceT9BRK9s(modifier2, null, containerColor2, contentColor2, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -1979205334, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$1
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
                    ComposerKt.sourceInformation($composer3, "C86@4372L307:Scaffold.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1979205334, $changed2, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:85)");
                        }
                        int i18 = i16;
                        Function2<Composer, Integer, Unit> function114 = function110;
                        Function3<PaddingValues, Composer, Integer, Unit> function115 = content;
                        Function2<Composer, Integer, Unit> function116 = function111;
                        Function2<Composer, Integer, Unit> function21 = function112;
                        WindowInsets windowInsets4 = windowInsets3;
                        Function2<Composer, Integer, Unit> function22 = function113;
                        int i19 = i17;
                        ScaffoldKt.m1689ScaffoldLayoutFMILGgc(i18, function114, function115, function116, function21, windowInsets4, function22, $composer3, ((i19 >> 15) & 14) | (i19 & 112) | ((i19 >> 21) & 896) | (i19 & 7168) | (57344 & i19) | ((i19 >> 9) & 458752) | ((i19 << 12) & 3670016));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, ($dirty & 14) | 12582912 | (($dirty >> 12) & 896) | (($dirty >> 12) & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            floatingActionButtonPosition3 = $dirty2;
            function9 = function2M1452getLambda1$material3_release;
            containerColor3 = containerColor2;
            function10 = function2M1454getLambda3$material3_release;
            function11 = function2M1455getLambda4$material3_release;
            function12 = function7;
            contentColor3 = contentColor2;
            contentWindowInsets3 = contentWindowInsets2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i5 != 0) {
                    function2M1452getLambda1$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1452getLambda1$material3_release();
                } else {
                    function2M1452getLambda1$material3_release = function6;
                }
                if (i6 != 0) {
                    function2M1453getLambda2$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1453getLambda2$material3_release();
                } else {
                    function2M1453getLambda2$material3_release = function7;
                }
                if (i7 != 0) {
                    function2M1454getLambda3$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1454getLambda3$material3_release();
                } else {
                    function2M1454getLambda3$material3_release = function4;
                }
                if (i2 != 0) {
                    function2M1455getLambda4$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1455getLambda4$material3_release();
                } else {
                    function2M1455getLambda4$material3_release = function8;
                }
                if (i9 != 0) {
                    floatingActionButtonPosition2 = FabPosition.INSTANCE.m1537getEndERTFSPs();
                } else {
                    floatingActionButtonPosition2 = floatingActionButtonPosition;
                }
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                    containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1366getBackground0d7_KjU();
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 128) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty3 >> 18) & 14);
                    $dirty3 &= -29360129;
                } else {
                    contentColor2 = j;
                }
                if ((i & 256) != 0) {
                    contentWindowInsets2 = ScaffoldDefaults.INSTANCE.getContentWindowInsets($composer2, 6);
                    $dirty = $dirty3 & (-234881025);
                    $dirty2 = floatingActionButtonPosition2;
                    function7 = function2M1453getLambda2$material3_release;
                } else {
                    contentWindowInsets2 = contentWindowInsets;
                    $dirty = $dirty3;
                    $dirty2 = floatingActionButtonPosition2;
                    function7 = function2M1453getLambda2$material3_release;
                }
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i5 != 0) {
                    function2M1452getLambda1$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1452getLambda1$material3_release();
                } else {
                    function2M1452getLambda1$material3_release = function6;
                }
                if (i6 != 0) {
                    function2M1453getLambda2$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1453getLambda2$material3_release();
                } else {
                    function2M1453getLambda2$material3_release = function7;
                }
                if (i7 != 0) {
                    function2M1454getLambda3$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1454getLambda3$material3_release();
                } else {
                    function2M1454getLambda3$material3_release = function4;
                }
                if (i2 != 0) {
                    function2M1455getLambda4$material3_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1455getLambda4$material3_release();
                } else {
                    function2M1455getLambda4$material3_release = function8;
                }
                if (i9 != 0) {
                    floatingActionButtonPosition2 = FabPosition.INSTANCE.m1537getEndERTFSPs();
                } else {
                    floatingActionButtonPosition2 = floatingActionButtonPosition;
                }
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                    containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1366getBackground0d7_KjU();
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 128) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty3 >> 18) & 14);
                    $dirty3 &= -29360129;
                } else {
                    contentColor2 = j;
                }
                if ((i & 256) != 0) {
                    contentWindowInsets2 = ScaffoldDefaults.INSTANCE.getContentWindowInsets($composer2, 6);
                    $dirty = $dirty3 & (-234881025);
                    $dirty2 = floatingActionButtonPosition2;
                    function7 = function2M1453getLambda2$material3_release;
                } else {
                    contentWindowInsets2 = contentWindowInsets;
                    $dirty = $dirty3;
                    $dirty2 = floatingActionButtonPosition2;
                    function7 = function2M1453getLambda2$material3_release;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1219521777, $dirty, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:73)");
            }
            final int i18 = $dirty2;
            final Function2<? super Composer, ? super Integer, Unit> function114 = function2M1452getLambda1$material3_release;
            final Function2<? super Composer, ? super Integer, Unit> function115 = function2M1454getLambda3$material3_release;
            final Function2<? super Composer, ? super Integer, Unit> function116 = function2M1455getLambda4$material3_release;
            final WindowInsets windowInsets4 = contentWindowInsets2;
            final Function2<? super Composer, ? super Integer, Unit> function117 = function7;
            final int i19 = $dirty;
            SurfaceKt.m1806SurfaceT9BRK9s(modifier2, null, containerColor2, contentColor2, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -1979205334, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$1
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
                    ComposerKt.sourceInformation($composer3, "C86@4372L307:Scaffold.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1979205334, $changed2, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:85)");
                        }
                        int i110 = i18;
                        Function2<Composer, Integer, Unit> function118 = function114;
                        Function3<PaddingValues, Composer, Integer, Unit> function119 = content;
                        Function2<Composer, Integer, Unit> function1110 = function115;
                        Function2<Composer, Integer, Unit> function21 = function116;
                        WindowInsets windowInsets5 = windowInsets4;
                        Function2<Composer, Integer, Unit> function22 = function117;
                        int i111 = i19;
                        ScaffoldKt.m1689ScaffoldLayoutFMILGgc(i110, function118, function119, function1110, function21, windowInsets5, function22, $composer3, ((i111 >> 15) & 14) | (i111 & 112) | ((i111 >> 21) & 896) | (i111 & 7168) | (57344 & i111) | ((i111 >> 9) & 458752) | ((i111 << 12) & 3670016));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, ($dirty & 14) | 12582912 | (($dirty >> 12) & 896) | (($dirty >> 12) & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            floatingActionButtonPosition3 = $dirty2;
            function9 = function2M1452getLambda1$material3_release;
            containerColor3 = containerColor2;
            function10 = function2M1454getLambda3$material3_release;
            function11 = function2M1455getLambda4$material3_release;
            function12 = function7;
            contentColor3 = contentColor2;
            contentWindowInsets3 = contentWindowInsets2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function118 = function9;
        final Function2<? super Composer, ? super Integer, Unit> function119 = function12;
        final Function2<? super Composer, ? super Integer, Unit> function120 = function10;
        final Function2<? super Composer, ? super Integer, Unit> function21 = function11;
        final int i110 = floatingActionButtonPosition3;
        final long j4 = containerColor3;
        final long j5 = contentColor3;
        final WindowInsets windowInsets5 = contentWindowInsets3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
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

            public final void invoke(Composer composer, int i111) {
                ScaffoldKt.m1688ScaffoldTvnljyQ(modifier5, function118, function119, function120, function21, i110, j4, j5, windowInsets5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ScaffoldLayout-FMILGgc, reason: not valid java name */
    public static final void m1689ScaffoldLayoutFMILGgc(final int fabPosition, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final WindowInsets contentWindowInsets, final Function2<? super Composer, ? super Integer, Unit> function6, Composer $composer, final int $changed) {
        int i;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-975511942);
        ComposerKt.sourceInformation($composer3, "C(ScaffoldLayout)P(4:c#material3.FabPosition,6,1,5,3,2)121@5603L6544,121@5586L6561:Scaffold.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(fabPosition) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function5) ? 16384 : 8192;
        }
        if ((458752 & $changed) == 0) {
            $dirty |= $composer3.changed(contentWindowInsets) ? 131072 : 65536;
        }
        if ((3670016 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function6) ? 1048576 : 524288;
        }
        final int $dirty2 = $dirty;
        if ((2995931 & $dirty2) != 599186 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-975511942, $dirty2, -1, "androidx.compose.material3.ScaffoldLayout (Scaffold.kt:111)");
            }
            Object[] keys$iv = {function2, function4, contentWindowInsets, function5, FabPosition.m1529boximpl(fabPosition), function6, function3};
            $composer3.startReplaceableGroup(-568225417);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv = false;
            for (Object key$iv : keys$iv) {
                invalid$iv |= $composer3.changed(key$iv);
            }
            Object value$iv$iv = $composer3.rememberedValue();
            if (invalid$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                i = 0;
                value$iv$iv = new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                        return m1691invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                    }

                    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m1691invoke0kLqBqw(final SubcomposeMeasureScope SubcomposeLayout, long constraints) {
                        Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                        final int layoutWidth = Constraints.m5218getMaxWidthimpl(constraints);
                        final int layoutHeight = Constraints.m5217getMaxHeightimpl(constraints);
                        final long looseConstraints = Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0);
                        final Function2<Composer, Integer, Unit> function7 = function2;
                        final Function2<Composer, Integer, Unit> function8 = function4;
                        final Function2<Composer, Integer, Unit> function9 = function5;
                        final int i2 = fabPosition;
                        final WindowInsets windowInsets = contentWindowInsets;
                        final Function2<Composer, Integer, Unit> function10 = function6;
                        final int i3 = $dirty2;
                        final Function3<PaddingValues, Composer, Integer, Unit> function11 = function3;
                        return MeasureScope.CC.layout$default(SubcomposeLayout, layoutWidth, layoutHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope layout) {
                                Object maxElem$iv;
                                Object maxElem$iv2;
                                Object maxElem$iv3;
                                FabPlacement fabPlacement;
                                Object maxElem$iv4;
                                Integer numValueOf;
                                int iIntValue;
                                Object maxElem$iv5;
                                Object maxElem$iv6;
                                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                Iterable $this$map$iv = SubcomposeLayout.subcompose(ScaffoldLayoutContent.TopBar, function7);
                                long j = looseConstraints;
                                int i4 = 10;
                                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                for (Object item$iv$iv : $this$map$iv) {
                                    Measurable it = (Measurable) item$iv$iv;
                                    destination$iv$iv.add(it.mo4225measureBRTryo0(j));
                                }
                                final List topBarPlaceables = (List) destination$iv$iv;
                                List $this$maxByOrNull$iv = topBarPlaceables;
                                Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
                                if (iterator$iv.hasNext()) {
                                    maxElem$iv = iterator$iv.next();
                                    if (iterator$iv.hasNext()) {
                                        Placeable it2 = (Placeable) maxElem$iv;
                                        int maxValue$iv = it2.getHeight();
                                        while (true) {
                                            Object e$iv = iterator$iv.next();
                                            Placeable it3 = (Placeable) e$iv;
                                            int v$iv = it3.getHeight();
                                            if (maxValue$iv < v$iv) {
                                                maxElem$iv = e$iv;
                                                maxValue$iv = v$iv;
                                            }
                                            if (!iterator$iv.hasNext()) {
                                                break;
                                            } else {
                                                i4 = 10;
                                            }
                                        }
                                    }
                                } else {
                                    maxElem$iv = null;
                                }
                                Placeable placeable = (Placeable) maxElem$iv;
                                final int topBarHeight = placeable != null ? placeable.getHeight() : 0;
                                Iterable $this$map$iv2 = SubcomposeLayout.subcompose(ScaffoldLayoutContent.Snackbar, function8);
                                WindowInsets windowInsets2 = windowInsets;
                                SubcomposeMeasureScope subcomposeMeasureScope = SubcomposeLayout;
                                long j2 = looseConstraints;
                                int $i$f$map = 0;
                                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, i4));
                                for (Object item$iv$iv2 : $this$map$iv2) {
                                    Measurable it4 = (Measurable) item$iv$iv2;
                                    SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                                    Iterable $this$map$iv3 = $this$map$iv2;
                                    int leftInset = windowInsets2.getLeft(subcomposeMeasureScope2, subcomposeMeasureScope.getLayoutDirection());
                                    int $i$f$map2 = $i$f$map;
                                    int rightInset = windowInsets2.getRight(subcomposeMeasureScope2, subcomposeMeasureScope.getLayoutDirection());
                                    int bottomInset = windowInsets2.getBottom(subcomposeMeasureScope2);
                                    WindowInsets windowInsets3 = windowInsets2;
                                    int i5 = (-leftInset) - rightInset;
                                    int leftInset2 = -bottomInset;
                                    destination$iv$iv2.add(it4.mo4225measureBRTryo0(ConstraintsKt.m5234offsetNN6EwU(j2, i5, leftInset2)));
                                    $this$map$iv2 = $this$map$iv3;
                                    $i$f$map = $i$f$map2;
                                    windowInsets2 = windowInsets3;
                                }
                                Iterable snackbarPlaceables = (List) destination$iv$iv2;
                                Iterable $this$maxByOrNull$iv2 = snackbarPlaceables;
                                Iterator iterator$iv2 = $this$maxByOrNull$iv2.iterator();
                                if (iterator$iv2.hasNext()) {
                                    maxElem$iv2 = iterator$iv2.next();
                                    if (iterator$iv2.hasNext()) {
                                        Placeable it5 = (Placeable) maxElem$iv2;
                                        int maxValue$iv2 = it5.getHeight();
                                        do {
                                            Object e$iv2 = iterator$iv2.next();
                                            Placeable it6 = (Placeable) e$iv2;
                                            int v$iv2 = it6.getHeight();
                                            if (maxValue$iv2 < v$iv2) {
                                                maxElem$iv2 = e$iv2;
                                                maxValue$iv2 = v$iv2;
                                            }
                                        } while (iterator$iv2.hasNext());
                                    }
                                } else {
                                    maxElem$iv2 = null;
                                }
                                Placeable placeable2 = (Placeable) maxElem$iv2;
                                int snackbarHeight = placeable2 != null ? placeable2.getHeight() : 0;
                                Iterable $this$maxByOrNull$iv3 = snackbarPlaceables;
                                Iterator iterator$iv3 = $this$maxByOrNull$iv3.iterator();
                                if (iterator$iv3.hasNext()) {
                                    maxElem$iv3 = iterator$iv3.next();
                                    if (iterator$iv3.hasNext()) {
                                        Placeable it7 = (Placeable) maxElem$iv3;
                                        int maxValue$iv3 = it7.getWidth();
                                        do {
                                            Object e$iv3 = iterator$iv3.next();
                                            Placeable it8 = (Placeable) e$iv3;
                                            int v$iv3 = it8.getWidth();
                                            if (maxValue$iv3 < v$iv3) {
                                                maxElem$iv3 = e$iv3;
                                                maxValue$iv3 = v$iv3;
                                            }
                                        } while (iterator$iv3.hasNext());
                                    }
                                } else {
                                    maxElem$iv3 = null;
                                }
                                Placeable placeable3 = (Placeable) maxElem$iv3;
                                int snackbarWidth = placeable3 != null ? placeable3.getWidth() : 0;
                                Iterable $this$mapNotNull$iv = SubcomposeLayout.subcompose(ScaffoldLayoutContent.Fab, function9);
                                WindowInsets windowInsets4 = windowInsets;
                                SubcomposeMeasureScope subcomposeMeasureScope3 = SubcomposeLayout;
                                long j3 = looseConstraints;
                                int $i$f$mapNotNull = 0;
                                Collection destination$iv$iv3 = new ArrayList();
                                Iterable $this$mapNotNullTo$iv$iv = $this$mapNotNull$iv;
                                Iterator it9 = $this$mapNotNullTo$iv$iv.iterator();
                                while (true) {
                                    Iterable $this$mapNotNull$iv2 = $this$mapNotNull$iv;
                                    if (!it9.hasNext()) {
                                        break;
                                    }
                                    Object element$iv$iv$iv = it9.next();
                                    Measurable measurable = (Measurable) element$iv$iv$iv;
                                    int $i$f$mapNotNull2 = $i$f$mapNotNull;
                                    SubcomposeMeasureScope subcomposeMeasureScope4 = subcomposeMeasureScope3;
                                    int leftInset3 = windowInsets4.getLeft(subcomposeMeasureScope4, subcomposeMeasureScope3.getLayoutDirection());
                                    Iterable $this$mapNotNullTo$iv$iv2 = $this$mapNotNullTo$iv$iv;
                                    int rightInset2 = windowInsets4.getRight(subcomposeMeasureScope4, subcomposeMeasureScope3.getLayoutDirection());
                                    int bottomInset2 = windowInsets4.getBottom(subcomposeMeasureScope4);
                                    WindowInsets windowInsets5 = windowInsets4;
                                    SubcomposeMeasureScope subcomposeMeasureScope5 = subcomposeMeasureScope3;
                                    Placeable it10 = measurable.mo4225measureBRTryo0(ConstraintsKt.m5234offsetNN6EwU(j3, (-leftInset3) - rightInset2, -bottomInset2));
                                    if (!((it10.getHeight() == 0 || it10.getWidth() == 0) ? false : true)) {
                                        it10 = null;
                                    }
                                    if (it10 != null) {
                                        destination$iv$iv3.add(it10);
                                    }
                                    $this$mapNotNull$iv = $this$mapNotNull$iv2;
                                    $i$f$mapNotNull = $i$f$mapNotNull2;
                                    $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
                                    windowInsets4 = windowInsets5;
                                    subcomposeMeasureScope3 = subcomposeMeasureScope5;
                                }
                                Iterable fabPlaceables = (List) destination$iv$iv3;
                                if (!((Collection) fabPlaceables).isEmpty()) {
                                    Iterable $this$maxByOrNull$iv4 = fabPlaceables;
                                    Iterator iterator$iv4 = $this$maxByOrNull$iv4.iterator();
                                    if (iterator$iv4.hasNext()) {
                                        maxElem$iv5 = iterator$iv4.next();
                                        if (iterator$iv4.hasNext()) {
                                            Placeable it11 = (Placeable) maxElem$iv5;
                                            int maxValue$iv4 = it11.getWidth();
                                            do {
                                                Object e$iv4 = iterator$iv4.next();
                                                Placeable it12 = (Placeable) e$iv4;
                                                int v$iv4 = it12.getWidth();
                                                if (maxValue$iv4 < v$iv4) {
                                                    maxElem$iv5 = e$iv4;
                                                    maxValue$iv4 = v$iv4;
                                                }
                                            } while (iterator$iv4.hasNext());
                                        }
                                    } else {
                                        maxElem$iv5 = null;
                                    }
                                    Intrinsics.checkNotNull(maxElem$iv5);
                                    int fabWidth = ((Placeable) maxElem$iv5).getWidth();
                                    Iterable $this$maxByOrNull$iv5 = fabPlaceables;
                                    Iterator iterator$iv5 = $this$maxByOrNull$iv5.iterator();
                                    if (iterator$iv5.hasNext()) {
                                        maxElem$iv6 = iterator$iv5.next();
                                        if (iterator$iv5.hasNext()) {
                                            Placeable it13 = (Placeable) maxElem$iv6;
                                            int maxValue$iv5 = it13.getHeight();
                                            do {
                                                Object e$iv5 = iterator$iv5.next();
                                                Placeable it14 = (Placeable) e$iv5;
                                                int v$iv5 = it14.getHeight();
                                                if (maxValue$iv5 < v$iv5) {
                                                    maxElem$iv6 = e$iv5;
                                                    maxValue$iv5 = v$iv5;
                                                }
                                            } while (iterator$iv5.hasNext());
                                        }
                                    } else {
                                        maxElem$iv6 = null;
                                    }
                                    Intrinsics.checkNotNull(maxElem$iv6);
                                    int fabHeight = ((Placeable) maxElem$iv6).getHeight();
                                    int fabLeftOffset = FabPosition.m1532equalsimpl0(i2, FabPosition.INSTANCE.m1537getEndERTFSPs()) ? SubcomposeLayout.getLayoutDirection() == LayoutDirection.Ltr ? (layoutWidth - SubcomposeLayout.mo321roundToPx0680j_4(ScaffoldKt.FabSpacing)) - fabWidth : SubcomposeLayout.mo321roundToPx0680j_4(ScaffoldKt.FabSpacing) : (layoutWidth - fabWidth) / 2;
                                    fabPlacement = new FabPlacement(fabLeftOffset, fabWidth, fabHeight);
                                } else {
                                    fabPlacement = null;
                                }
                                final FabPlacement fabPlacement2 = fabPlacement;
                                SubcomposeMeasureScope subcomposeMeasureScope6 = SubcomposeLayout;
                                ScaffoldLayoutContent scaffoldLayoutContent = ScaffoldLayoutContent.BottomBar;
                                final Function2<Composer, Integer, Unit> function12 = function10;
                                final int i6 = i3;
                                Iterable $this$map$iv4 = subcomposeMeasureScope6.subcompose(scaffoldLayoutContent, ComposableLambdaKt.composableLambdaInstance(-1455477816, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1$1$1$bottomBarPlaceables$1
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

                                    public final void invoke(Composer $composer4, int $changed2) {
                                        ComposerKt.sourceInformation($composer4, "C194@8951L144:Scaffold.kt#uh7d8r");
                                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1455477816, $changed2, -1, "androidx.compose.material3.ScaffoldLayout.<anonymous>.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:193)");
                                            }
                                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ScaffoldKt.getLocalFabPlacement().provides(fabPlacement2)}, function12, $composer4, ((i6 >> 15) & 112) | 8);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer4.skipToGroupEnd();
                                    }
                                }));
                                long j4 = looseConstraints;
                                Collection destination$iv$iv4 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv4, 10));
                                for (Object item$iv$iv3 : $this$map$iv4) {
                                    Measurable it15 = (Measurable) item$iv$iv3;
                                    destination$iv$iv4.add(it15.mo4225measureBRTryo0(j4));
                                }
                                final List bottomBarPlaceables = (List) destination$iv$iv4;
                                List $this$maxByOrNull$iv6 = bottomBarPlaceables;
                                Iterator iterator$iv6 = $this$maxByOrNull$iv6.iterator();
                                if (iterator$iv6.hasNext()) {
                                    maxElem$iv4 = iterator$iv6.next();
                                    if (iterator$iv6.hasNext()) {
                                        Placeable it16 = (Placeable) maxElem$iv4;
                                        int maxValue$iv6 = it16.getHeight();
                                        do {
                                            Object e$iv6 = iterator$iv6.next();
                                            Placeable it17 = (Placeable) e$iv6;
                                            int v$iv6 = it17.getHeight();
                                            if (maxValue$iv6 < v$iv6) {
                                                maxElem$iv4 = e$iv6;
                                                maxValue$iv6 = v$iv6;
                                            }
                                        } while (iterator$iv6.hasNext());
                                    }
                                } else {
                                    maxElem$iv4 = null;
                                }
                                Placeable placeable4 = (Placeable) maxElem$iv4;
                                final Integer bottomBarHeight = placeable4 != null ? Integer.valueOf(placeable4.getHeight()) : null;
                                if (fabPlacement2 != null) {
                                    SubcomposeMeasureScope subcomposeMeasureScope7 = SubcomposeLayout;
                                    numValueOf = Integer.valueOf(bottomBarHeight == null ? fabPlacement2.getHeight() + subcomposeMeasureScope7.mo321roundToPx0680j_4(ScaffoldKt.FabSpacing) + windowInsets.getBottom(subcomposeMeasureScope7) : bottomBarHeight.intValue() + fabPlacement2.getHeight() + subcomposeMeasureScope7.mo321roundToPx0680j_4(ScaffoldKt.FabSpacing));
                                } else {
                                    numValueOf = null;
                                }
                                Integer fabOffsetFromBottom = numValueOf;
                                if (snackbarHeight != 0) {
                                    iIntValue = snackbarHeight + (fabOffsetFromBottom != null ? fabOffsetFromBottom.intValue() : bottomBarHeight != null ? bottomBarHeight.intValue() : windowInsets.getBottom(SubcomposeLayout));
                                } else {
                                    iIntValue = 0;
                                }
                                int snackbarOffsetFromBottom = iIntValue;
                                SubcomposeMeasureScope subcomposeMeasureScope8 = SubcomposeLayout;
                                ScaffoldLayoutContent scaffoldLayoutContent2 = ScaffoldLayoutContent.MainContent;
                                final WindowInsets windowInsets6 = windowInsets;
                                final SubcomposeMeasureScope subcomposeMeasureScope9 = SubcomposeLayout;
                                final Function3<PaddingValues, Composer, Integer, Unit> function13 = function11;
                                final int i7 = i3;
                                Iterable $this$map$iv5 = subcomposeMeasureScope8.subcompose(scaffoldLayoutContent2, ComposableLambdaKt.composableLambdaInstance(1643221465, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1$1$1$bodyContentPlaceables$1
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

                                    public final void invoke(Composer $composer4, int $changed2) {
                                        float top;
                                        float bottom;
                                        Integer num;
                                        ComposerKt.sourceInformation($composer4, "C238@10996L21:Scaffold.kt#uh7d8r");
                                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1643221465, $changed2, -1, "androidx.compose.material3.ScaffoldLayout.<anonymous>.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:220)");
                                            }
                                            PaddingValues insets = WindowInsetsKt.asPaddingValues(windowInsets6, subcomposeMeasureScope9);
                                            if (topBarPlaceables.isEmpty()) {
                                                top = insets.getTop();
                                            } else {
                                                top = subcomposeMeasureScope9.mo324toDpu2uoSUM(topBarHeight);
                                            }
                                            if (bottomBarPlaceables.isEmpty() || (num = bottomBarHeight) == null) {
                                                bottom = insets.getBottom();
                                            } else {
                                                bottom = subcomposeMeasureScope9.mo324toDpu2uoSUM(num.intValue());
                                            }
                                            PaddingValues innerPadding = PaddingKt.m483PaddingValuesa9UjIt4(PaddingKt.calculateStartPadding(insets, subcomposeMeasureScope9.getLayoutDirection()), top, PaddingKt.calculateEndPadding(insets, subcomposeMeasureScope9.getLayoutDirection()), bottom);
                                            function13.invoke(innerPadding, $composer4, Integer.valueOf((i7 >> 3) & 112));
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer4.skipToGroupEnd();
                                    }
                                }));
                                long j5 = looseConstraints;
                                Collection destination$iv$iv5 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv5, 10));
                                for (Object item$iv$iv4 : $this$map$iv5) {
                                    Measurable it18 = (Measurable) item$iv$iv4;
                                    destination$iv$iv5.add(it18.mo4225measureBRTryo0(j5));
                                }
                                Iterable bodyContentPlaceables = (List) destination$iv$iv5;
                                Iterable $this$forEach$iv = bodyContentPlaceables;
                                for (Object element$iv : $this$forEach$iv) {
                                    Placeable it19 = (Placeable) element$iv;
                                    Placeable.PlacementScope.place$default(layout, it19, 0, 0, 0.0f, 4, null);
                                    fabPlacement2 = fabPlacement2;
                                }
                                FabPlacement fabPlacement3 = fabPlacement2;
                                List $this$forEach$iv2 = topBarPlaceables;
                                for (Object element$iv2 : $this$forEach$iv2) {
                                    Placeable it20 = (Placeable) element$iv2;
                                    Placeable.PlacementScope.place$default(layout, it20, 0, 0, 0.0f, 4, null);
                                }
                                Iterable $this$forEach$iv3 = snackbarPlaceables;
                                int i8 = layoutWidth;
                                WindowInsets windowInsets7 = windowInsets;
                                SubcomposeMeasureScope subcomposeMeasureScope10 = SubcomposeLayout;
                                int i9 = layoutHeight;
                                for (Object element$iv3 : $this$forEach$iv3) {
                                    Placeable it21 = (Placeable) element$iv3;
                                    Placeable.PlacementScope.place$default(layout, it21, ((i8 - snackbarWidth) / 2) + windowInsets7.getLeft(subcomposeMeasureScope10, subcomposeMeasureScope10.getLayoutDirection()), i9 - snackbarOffsetFromBottom, 0.0f, 4, null);
                                    i9 = i9;
                                }
                                List $this$forEach$iv4 = bottomBarPlaceables;
                                int i10 = layoutHeight;
                                for (Object element$iv4 : $this$forEach$iv4) {
                                    Placeable it22 = (Placeable) element$iv4;
                                    Placeable.PlacementScope.place$default(layout, it22, 0, i10 - (bottomBarHeight != null ? bottomBarHeight.intValue() : 0), 0.0f, 4, null);
                                }
                                if (fabPlacement3 != null) {
                                    int i11 = layoutHeight;
                                    Iterable $this$forEach$iv5 = fabPlaceables;
                                    for (Object element$iv5 : $this$forEach$iv5) {
                                        Placeable it23 = (Placeable) element$iv5;
                                        int left = fabPlacement3.getLeft();
                                        Intrinsics.checkNotNull(fabOffsetFromBottom);
                                        Placeable.PlacementScope.place$default(layout, it23, left, i11 - fabOffsetFromBottom.intValue(), 0.0f, 4, null);
                                    }
                                    Unit unit = Unit.INSTANCE;
                                    Unit unit2 = Unit.INSTANCE;
                                }
                            }
                        }, 4, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                i = 0;
            }
            $composer3.endReplaceableGroup();
            $composer2 = $composer3;
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) value$iv$iv, $composer2, i, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$2
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

            public final void invoke(Composer composer, int i2) {
                ScaffoldKt.m1689ScaffoldLayoutFMILGgc(fabPosition, function2, function3, function4, function5, contentWindowInsets, function6, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    public static final ProvidableCompositionLocal<FabPlacement> getLocalFabPlacement() {
        return LocalFabPlacement;
    }
}
