package androidx.compose.material;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
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
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a¢\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\u0013\b\u0002\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\u0019\b\u0002\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u00102\u0013\b\u0002\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192 \b\u0002\u0010\u001a\u001a\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0013¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00192\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"2\b\b\u0002\u0010&\u001a\u00020\"2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a£\u0001\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u00172\u0016\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u0010¢\u0006\u0002\b.2\u001c\u0010'\u001a\u0018\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u0010¢\u0006\u0002\b.2\u0016\u0010/\u001a\u0012\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u0010¢\u0006\u0002\b.2\u0016\u00100\u001a\u0012\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u0010¢\u0006\u0002\b.2\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u0010¢\u0006\u0002\b.H\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001a!\u00103\u001a\u00020\r2\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u00020\u0014H\u0007¢\u0006\u0002\u00107\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u001c\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00068"}, d2 = {"FabSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "LocalFabPlacement", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material/FabPlacement;", "getLocalFabPlacement", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "Scaffold", "", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material/ScaffoldState;", "topBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "bottomBar", "snackbarHost", "Lkotlin/Function1;", "Landroidx/compose/material/SnackbarHostState;", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material/FabPosition;", "isFloatingActionButtonDocked", "", "drawerContent", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "drawerGesturesEnabled", "drawerShape", "Landroidx/compose/ui/graphics/Shape;", "drawerElevation", "drawerBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "drawerContentColor", "drawerScrimColor", "backgroundColor", "contentColor", "content", "Landroidx/compose/foundation/layout/PaddingValues;", "Scaffold-27mzLpw", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material/ScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLkotlin/jvm/functions/Function3;ZLandroidx/compose/ui/graphics/Shape;FJJJJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "ScaffoldLayout", "isFabDocked", "fabPosition", "Landroidx/compose/ui/UiComposable;", "snackbar", "fab", "ScaffoldLayout-MDYNRJg", "(ZILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "rememberScaffoldState", "drawerState", "Landroidx/compose/material/DrawerState;", "snackbarHostState", "(Landroidx/compose/material/DrawerState;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/ScaffoldState;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScaffoldKt {
    private static final ProvidableCompositionLocal<FabPlacement> LocalFabPlacement = CompositionLocalKt.staticCompositionLocalOf(new Function0<FabPlacement>() { // from class: androidx.compose.material.ScaffoldKt$LocalFabPlacement$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FabPlacement invoke() {
            return null;
        }
    });
    private static final float FabSpacing = Dp.m5274constructorimpl(16);

    public static final ScaffoldState rememberScaffoldState(DrawerState drawerState, SnackbarHostState snackbarHostState, Composer $composer, int $changed, int i) {
        Object value$iv$iv;
        Object value$iv$iv2;
        $composer.startReplaceableGroup(1569641925);
        ComposerKt.sourceInformation($composer, "C(rememberScaffoldState)63@2263L39,64@2347L32,65@2399L62:Scaffold.kt#jmzs0o");
        if ((i & 1) != 0) {
            drawerState = DrawerKt.rememberDrawerState(DrawerValue.Closed, null, $composer, 6, 2);
        }
        if ((i & 2) != 0) {
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new SnackbarHostState();
                $composer.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv;
            }
            $composer.endReplaceableGroup();
            snackbarHostState = (SnackbarHostState) value$iv$iv2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1569641925, $changed, -1, "androidx.compose.material.rememberScaffoldState (Scaffold.kt:62)");
        }
        $composer.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
        Object it$iv$iv2 = $composer.rememberedValue();
        if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new ScaffoldState(drawerState, snackbarHostState);
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv2;
        }
        $composer.endReplaceableGroup();
        ScaffoldState scaffoldState = (ScaffoldState) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return scaffoldState;
    }

    /* JADX WARN: Code duplicated, block: B:206:0x0295  */
    /* JADX WARN: Code duplicated, block: B:208:0x029c  */
    /* JADX WARN: Code duplicated, block: B:233:0x02fe A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:234:0x0300  */
    /* JADX WARN: Code duplicated, block: B:235:0x0305  */
    /* JADX WARN: Code duplicated, block: B:238:0x030b  */
    /* JADX WARN: Code duplicated, block: B:239:0x0318  */
    /* JADX WARN: Code duplicated, block: B:241:0x031e  */
    /* JADX WARN: Code duplicated, block: B:242:0x0325  */
    /* JADX WARN: Code duplicated, block: B:244:0x0329  */
    /* JADX WARN: Code duplicated, block: B:245:0x0330  */
    /* JADX WARN: Code duplicated, block: B:247:0x0334  */
    /* JADX WARN: Code duplicated, block: B:248:0x033b  */
    /* JADX WARN: Code duplicated, block: B:250:0x033f  */
    /* JADX WARN: Code duplicated, block: B:251:0x0346  */
    /* JADX WARN: Code duplicated, block: B:253:0x034a  */
    /* JADX WARN: Code duplicated, block: B:254:0x0351  */
    /* JADX WARN: Code duplicated, block: B:256:0x0355  */
    /* JADX WARN: Code duplicated, block: B:257:0x0357  */
    /* JADX WARN: Code duplicated, block: B:259:0x035b  */
    /* JADX WARN: Code duplicated, block: B:260:0x035d  */
    /* JADX WARN: Code duplicated, block: B:262:0x0361  */
    /* JADX WARN: Code duplicated, block: B:263:0x0363  */
    /* JADX WARN: Code duplicated, block: B:266:0x036e  */
    /* JADX WARN: Code duplicated, block: B:267:0x037d  */
    /* JADX WARN: Code duplicated, block: B:269:0x0381  */
    /* JADX WARN: Code duplicated, block: B:270:0x0388  */
    /* JADX WARN: Code duplicated, block: B:273:0x038e  */
    /* JADX WARN: Code duplicated, block: B:274:0x03a1  */
    /* JADX WARN: Code duplicated, block: B:277:0x03ac  */
    /* JADX WARN: Code duplicated, block: B:278:0x03b7  */
    /* JADX WARN: Code duplicated, block: B:281:0x03bd  */
    /* JADX WARN: Code duplicated, block: B:282:0x03cb  */
    /* JADX WARN: Code duplicated, block: B:285:0x03d5  */
    /* JADX WARN: Code duplicated, block: B:286:0x03e5  */
    /* JADX WARN: Code duplicated, block: B:289:0x03eb  */
    /* JADX WARN: Code duplicated, block: B:290:0x0407  */
    /* JADX WARN: Code duplicated, block: B:293:0x041f  */
    /* JADX WARN: Code duplicated, block: B:296:0x0454  */
    /* JADX WARN: Code duplicated, block: B:297:0x04cd  */
    /* JADX WARN: Code duplicated, block: B:300:0x04f0  */
    /* JADX WARN: Code duplicated, block: B:304:0x051f  */
    /* JADX WARN: Code duplicated, block: B:305:0x0522  */
    /* JADX INFO: renamed from: Scaffold-27mzLpw, reason: not valid java name */
    public static final void m1181Scaffold27mzLpw(Modifier modifier, ScaffoldState scaffoldState, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, int floatingActionButtonPosition, boolean isFloatingActionButtonDocked, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function6, boolean drawerGesturesEnabled, Shape drawerShape, float drawerElevation, long drawerBackgroundColor, long drawerContentColor, long drawerScrimColor, long backgroundColor, long contentColor, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        int i3;
        Modifier.Companion modifier2;
        ScaffoldState scaffoldState2;
        Function2<? super Composer, ? super Integer, Unit> function2M1074getLambda1$material_release;
        Function2<? super Composer, ? super Integer, Unit> function2M1075getLambda2$material_release;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function3M1076getLambda3$material_release;
        Function2<? super Composer, ? super Integer, Unit> function2M1077getLambda4$material_release;
        int floatingActionButtonPosition2;
        boolean isFloatingActionButtonDocked2;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function7;
        boolean drawerGesturesEnabled2;
        int $dirty;
        ScaffoldState scaffoldState3;
        CornerBasedShape drawerShape2;
        float drawerElevation2;
        int $dirty1;
        long drawerBackgroundColor2;
        boolean isFloatingActionButtonDocked3;
        long drawerContentColor2;
        long drawerScrimColor2;
        long backgroundColor2;
        int $dirty2;
        Shape drawerShape3;
        long contentColor2;
        long backgroundColor3;
        int $dirty3;
        Modifier modifier3;
        boolean isFloatingActionButtonDocked4;
        ScaffoldState scaffoldState4;
        final Function3 child;
        boolean isFloatingActionButtonDocked5;
        ScaffoldState scaffoldState5;
        Modifier modifier4;
        Shape drawerShape4;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function8;
        boolean drawerGesturesEnabled3;
        float drawerElevation3;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function9;
        Function2<? super Composer, ? super Integer, Unit> function10;
        int floatingActionButtonPosition3;
        long drawerContentColor3;
        long drawerBackgroundColor3;
        long drawerScrimColor3;
        long backgroundColor4;
        long contentColor3;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1037492569);
        ComposerKt.sourceInformation($composer2, "C(Scaffold)P(14,15,17,1,16,11,12:c#material.FabPosition,13,5,8,10,7:c#ui.unit.Dp,4:c#ui.graphics.Color,6:c#ui.graphics.Color,9:c#ui.graphics.Color,0:c#ui.graphics.Color,3:c#ui.graphics.Color)160@7052L23,169@7562L6,171@7677L6,172@7725L38,173@7810L10,174@7865L6,175@7910L32:Scaffold.kt#jmzs0o");
        int $dirty4 = $changed;
        int $dirty5 = $changed1;
        int i4 = i & 1;
        if (i4 != 0) {
            $dirty4 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty4 |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty4 |= ((i & 2) == 0 && $composer2.changed(scaffoldState)) ? 32 : 16;
        }
        int i5 = i & 4;
        int i6 = 128;
        if (i5 != 0) {
            $dirty4 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty4 |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty4 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty4 |= $composer2.changedInstance(function3) ? 2048 : 1024;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty4 |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty4 |= $composer2.changedInstance(function4) ? 16384 : 8192;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty4 |= $composer2.changedInstance(function5) ? 131072 : 65536;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty4 |= 1572864;
            i2 = floatingActionButtonPosition;
        } else if (($changed & 3670016) == 0) {
            i2 = floatingActionButtonPosition;
            $dirty4 |= $composer2.changed(i2) ? 1048576 : 524288;
        } else {
            i2 = floatingActionButtonPosition;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty4 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty4 |= $composer2.changed(isFloatingActionButtonDocked) ? 8388608 : 4194304;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty4 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty4 |= $composer2.changedInstance(function6) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty4 |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty4 |= $composer2.changed(drawerGesturesEnabled) ? 536870912 : 268435456;
        }
        if (($changed1 & 14) == 0) {
            $dirty5 |= ((i & 1024) == 0 && $composer2.changed(drawerShape)) ? 4 : 2;
        }
        int i14 = i & 2048;
        if (i14 != 0) {
            $dirty5 |= 48;
        } else if (($changed1 & 112) == 0) {
            $dirty5 |= $composer2.changed(drawerElevation) ? 32 : 16;
        }
        if (($changed1 & 896) == 0) {
            if ((i & 4096) == 0 && $composer2.changed(drawerBackgroundColor)) {
                i6 = 256;
            }
            $dirty5 |= i6;
        }
        if (($changed1 & 7168) == 0) {
            $dirty5 |= ((i & 8192) == 0 && $composer2.changed(drawerContentColor)) ? 2048 : 1024;
        }
        if (($changed1 & 57344) == 0) {
            $dirty5 |= ((i & 16384) == 0 && $composer2.changed(drawerScrimColor)) ? 16384 : 8192;
        }
        if (($changed1 & 458752) == 0) {
            $dirty5 |= ((i & 32768) == 0 && $composer2.changed(backgroundColor)) ? 131072 : 65536;
        }
        if (($changed1 & 3670016) == 0) {
            $dirty5 |= ((i & 65536) == 0 && $composer2.changed(contentColor)) ? 1048576 : 524288;
        }
        if ((i & 131072) == 0) {
            if (($changed1 & 29360128) == 0) {
                i3 = $composer2.changedInstance(content) ? 8388608 : 4194304;
            }
            if (($dirty4 & 1533916891) != 306783378 && (23967451 & $dirty5) == 4793490 && $composer2.getSkipping()) {
                $composer2.skipToGroupEnd();
                modifier4 = modifier;
                scaffoldState5 = scaffoldState;
                function11 = function2;
                function12 = function3;
                function9 = function4;
                function10 = function5;
                isFloatingActionButtonDocked5 = isFloatingActionButtonDocked;
                function8 = function6;
                drawerGesturesEnabled3 = drawerGesturesEnabled;
                drawerShape4 = drawerShape;
                drawerElevation3 = drawerElevation;
                drawerBackgroundColor3 = drawerBackgroundColor;
                drawerContentColor3 = drawerContentColor;
                drawerScrimColor3 = drawerScrimColor;
                backgroundColor4 = backgroundColor;
                contentColor3 = contentColor;
                floatingActionButtonPosition3 = i2;
            } else {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 2) != 0) {
                        scaffoldState2 = rememberScaffoldState(null, null, $composer2, 0, 3);
                        $dirty4 &= -113;
                    } else {
                        scaffoldState2 = scaffoldState;
                    }
                    if (i5 != 0) {
                        function2M1074getLambda1$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1074getLambda1$material_release();
                    } else {
                        function2M1074getLambda1$material_release = function2;
                    }
                    if (i7 != 0) {
                        function2M1075getLambda2$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1075getLambda2$material_release();
                    } else {
                        function2M1075getLambda2$material_release = function3;
                    }
                    if (i8 != 0) {
                        function3M1076getLambda3$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1076getLambda3$material_release();
                    } else {
                        function3M1076getLambda3$material_release = function4;
                    }
                    if (i9 != 0) {
                        function2M1077getLambda4$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1077getLambda4$material_release();
                    } else {
                        function2M1077getLambda4$material_release = function5;
                    }
                    if (i10 != 0) {
                        floatingActionButtonPosition2 = FabPosition.INSTANCE.m1119getEnd5ygKITE();
                    } else {
                        floatingActionButtonPosition2 = floatingActionButtonPosition;
                    }
                    if (i11 != 0) {
                        isFloatingActionButtonDocked2 = false;
                    } else {
                        isFloatingActionButtonDocked2 = isFloatingActionButtonDocked;
                    }
                    if (i12 != 0) {
                        function7 = null;
                    } else {
                        function7 = function6;
                    }
                    if (i13 != 0) {
                        drawerGesturesEnabled2 = true;
                    } else {
                        drawerGesturesEnabled2 = drawerGesturesEnabled;
                    }
                    $dirty = $dirty4;
                    scaffoldState3 = scaffoldState2;
                    if ((i & 1024) != 0) {
                        drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer2, 6).getLarge();
                        $dirty5 &= -15;
                    } else {
                        drawerShape2 = drawerShape;
                    }
                    if (i14 != 0) {
                        drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                    } else {
                        drawerElevation2 = drawerElevation;
                    }
                    if ((i & 4096) != 0) {
                        $dirty1 = $dirty5 & (-897);
                        drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                    } else {
                        $dirty1 = $dirty5;
                        drawerBackgroundColor2 = drawerBackgroundColor;
                    }
                    isFloatingActionButtonDocked3 = isFloatingActionButtonDocked2;
                    if ((i & 8192) != 0) {
                        drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer2, ($dirty1 >> 6) & 14);
                        $dirty1 &= -7169;
                    } else {
                        drawerContentColor2 = drawerContentColor;
                    }
                    if ((i & 16384) != 0) {
                        drawerScrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                        $dirty1 &= -57345;
                    } else {
                        drawerScrimColor2 = drawerScrimColor;
                    }
                    if ((32768 & i) != 0) {
                        backgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1041getBackground0d7_KjU();
                        $dirty1 &= -458753;
                    } else {
                        backgroundColor2 = backgroundColor;
                    }
                    if ((i & 65536) != 0) {
                        $dirty2 = $dirty;
                        drawerShape3 = drawerShape2;
                        $dirty3 = $dirty1 & (-3670017);
                        contentColor2 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty1 >> 15) & 14);
                        isFloatingActionButtonDocked4 = isFloatingActionButtonDocked3;
                        backgroundColor3 = backgroundColor2;
                        modifier3 = modifier2;
                        scaffoldState4 = scaffoldState3;
                    } else {
                        $dirty2 = $dirty;
                        drawerShape3 = drawerShape2;
                        contentColor2 = contentColor;
                        backgroundColor3 = backgroundColor2;
                        $dirty3 = $dirty1;
                        modifier3 = modifier2;
                        isFloatingActionButtonDocked4 = isFloatingActionButtonDocked3;
                        scaffoldState4 = scaffoldState3;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 2) != 0) {
                        $dirty4 &= -113;
                    }
                    if ((i & 1024) != 0) {
                        $dirty5 &= -15;
                    }
                    if ((i & 4096) != 0) {
                        $dirty5 &= -897;
                    }
                    if ((i & 8192) != 0) {
                        $dirty5 &= -7169;
                    }
                    if ((i & 16384) != 0) {
                        $dirty5 &= -57345;
                    }
                    if ((32768 & i) != 0) {
                        $dirty5 &= -458753;
                    }
                    if ((i & 65536) != 0) {
                        $dirty5 &= -3670017;
                    }
                    function2M1074getLambda1$material_release = function2;
                    function2M1075getLambda2$material_release = function3;
                    function3M1076getLambda3$material_release = function4;
                    isFloatingActionButtonDocked4 = isFloatingActionButtonDocked;
                    function7 = function6;
                    drawerGesturesEnabled2 = drawerGesturesEnabled;
                    drawerShape3 = drawerShape;
                    drawerElevation2 = drawerElevation;
                    drawerBackgroundColor2 = drawerBackgroundColor;
                    drawerContentColor2 = drawerContentColor;
                    drawerScrimColor2 = drawerScrimColor;
                    backgroundColor3 = backgroundColor;
                    contentColor2 = contentColor;
                    $dirty2 = $dirty4;
                    $dirty3 = $dirty5;
                    floatingActionButtonPosition2 = i2;
                    modifier3 = modifier;
                    scaffoldState4 = scaffoldState;
                    function2M1077getLambda4$material_release = function5;
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1037492569, $dirty2, $dirty3, "androidx.compose.material.Scaffold (Scaffold.kt:158)");
                }
                final long j = backgroundColor3;
                final long j2 = contentColor2;
                final int i15 = $dirty3;
                final boolean z = isFloatingActionButtonDocked4;
                final int i16 = floatingActionButtonPosition2;
                final Function2<? super Composer, ? super Integer, Unit> function13 = function2M1074getLambda1$material_release;
                final Function2<? super Composer, ? super Integer, Unit> function14 = function2M1077getLambda4$material_release;
                final Function2<? super Composer, ? super Integer, Unit> function15 = function2M1075getLambda2$material_release;
                final int i17 = $dirty2;
                final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function16 = function3M1076getLambda3$material_release;
                final ScaffoldState scaffoldState6 = scaffoldState4;
                boolean isFloatingActionButtonDocked6 = isFloatingActionButtonDocked4;
                child = ComposableLambdaKt.composableLambda($composer2, 1823402604, true, new Function3<Modifier, Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$Scaffold$child$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Modifier modifier5, Composer composer, Integer num) {
                        invoke(modifier5, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Modifier childModifier, Composer $composer3, int $changed2) {
                        Intrinsics.checkNotNullParameter(childModifier, "childModifier");
                        ComposerKt.sourceInformation($composer3, "C179@8062L525:Scaffold.kt#jmzs0o");
                        int $dirty6 = $changed2;
                        if (($changed2 & 14) == 0) {
                            $dirty6 |= $composer3.changed(childModifier) ? 4 : 2;
                        }
                        int $dirty7 = $dirty6;
                        if (($dirty7 & 91) != 18 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1823402604, $dirty7, -1, "androidx.compose.material.Scaffold.<anonymous> (Scaffold.kt:178)");
                            }
                            long j3 = j;
                            long j4 = j2;
                            final boolean z2 = z;
                            final int i18 = i16;
                            final Function2<Composer, Integer, Unit> function17 = function13;
                            final Function3<PaddingValues, Composer, Integer, Unit> function18 = content;
                            final Function2<Composer, Integer, Unit> function19 = function14;
                            final Function2<Composer, Integer, Unit> function20 = function15;
                            final int i19 = i17;
                            final int i20 = i15;
                            final Function3<SnackbarHostState, Composer, Integer, Unit> function21 = function16;
                            final ScaffoldState scaffoldState7 = scaffoldState6;
                            int i21 = i15;
                            SurfaceKt.m1210SurfaceFjzlyU(childModifier, null, j3, j4, null, 0.0f, ComposableLambdaKt.composableLambda($composer3, -1128984656, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$Scaffold$child$1.1
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

                                public final void invoke(Composer $composer4, int $changed3) {
                                    ComposerKt.sourceInformation($composer4, "C180@8164L413:Scaffold.kt#jmzs0o");
                                    if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1128984656, $changed3, -1, "androidx.compose.material.Scaffold.<anonymous>.<anonymous> (Scaffold.kt:179)");
                                        }
                                        boolean z3 = z2;
                                        int i22 = i18;
                                        Function2<Composer, Integer, Unit> function22 = function17;
                                        Function3<PaddingValues, Composer, Integer, Unit> function23 = function18;
                                        final Function3<SnackbarHostState, Composer, Integer, Unit> function24 = function21;
                                        final ScaffoldState scaffoldState8 = scaffoldState7;
                                        final int i23 = i19;
                                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, 533782017, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt.Scaffold.child.1.1.1
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

                                            public final void invoke(Composer $composer5, int $changed4) {
                                                ComposerKt.sourceInformation($composer5, "C186@8417L45:Scaffold.kt#jmzs0o");
                                                if (($changed4 & 11) == 2 && $composer5.getSkipping()) {
                                                    $composer5.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(533782017, $changed4, -1, "androidx.compose.material.Scaffold.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:185)");
                                                }
                                                function24.invoke(scaffoldState8.getSnackbarHostState(), $composer5, Integer.valueOf((i23 >> 9) & 112));
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        });
                                        Function2<Composer, Integer, Unit> function25 = function19;
                                        Function2<Composer, Integer, Unit> function26 = function20;
                                        int i24 = i19;
                                        ScaffoldKt.m1182ScaffoldLayoutMDYNRJg(z3, i22, function22, function23, composableLambda, function25, function26, $composer4, ((i24 >> 21) & 14) | 24576 | ((i24 >> 15) & 112) | (i24 & 896) | ((i20 >> 12) & 7168) | (458752 & i24) | ((i24 << 9) & 3670016));
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer4.skipToGroupEnd();
                                }
                            }), $composer3, 1572864 | ($dirty7 & 14) | ((i21 >> 9) & 896) | ((i21 >> 9) & 7168), 50);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                if (function7 != null) {
                    $composer2.startReplaceableGroup(-1013848234);
                    ComposerKt.sourceInformation($composer2, "195@8636L487");
                    DrawerKt.m1091ModalDrawerGs3lGvM(function7, modifier3, scaffoldState4.getDrawerState(), drawerGesturesEnabled2, drawerShape3, drawerElevation2, drawerBackgroundColor2, drawerContentColor2, drawerScrimColor2, ComposableLambdaKt.composableLambda($composer2, 100842932, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$Scaffold$1
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
                            ComposerKt.sourceInformation($composer3, "C205@9096L15:Scaffold.kt#jmzs0o");
                            if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(100842932, $changed2, -1, "androidx.compose.material.Scaffold.<anonymous> (Scaffold.kt:205)");
                            }
                            child.invoke(Modifier.INSTANCE, $composer3, 54);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }), $composer2, (($dirty2 >> 24) & 14) | 805306368 | (($dirty2 << 3) & 112) | (($dirty2 >> 18) & 7168) | (($dirty3 << 12) & 57344) | (($dirty3 << 12) & 458752) | (($dirty3 << 12) & 3670016) | (($dirty3 << 12) & 29360128) | (($dirty3 << 12) & 234881024), 0);
                    $composer2.endReplaceableGroup();
                } else {
                    $composer2.startReplaceableGroup(-1013847725);
                    ComposerKt.sourceInformation($composer2, "208@9145L15");
                    child.invoke(modifier3, $composer2, Integer.valueOf(($dirty2 & 14) | 48));
                    $composer2.endReplaceableGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                isFloatingActionButtonDocked5 = isFloatingActionButtonDocked6;
                scaffoldState5 = scaffoldState4;
                modifier4 = modifier3;
                drawerShape4 = drawerShape3;
                function8 = function7;
                drawerGesturesEnabled3 = drawerGesturesEnabled2;
                drawerElevation3 = drawerElevation2;
                function9 = function3M1076getLambda3$material_release;
                function10 = function2M1077getLambda4$material_release;
                floatingActionButtonPosition3 = floatingActionButtonPosition2;
                drawerContentColor3 = drawerContentColor2;
                drawerBackgroundColor3 = drawerBackgroundColor2;
                drawerScrimColor3 = drawerScrimColor2;
                backgroundColor4 = backgroundColor3;
                contentColor3 = contentColor2;
                function11 = function2M1074getLambda1$material_release;
                function12 = function2M1075getLambda2$material_release;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final ScaffoldState scaffoldState7 = scaffoldState5;
            final Function2<? super Composer, ? super Integer, Unit> function17 = function11;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function12;
            final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function19 = function9;
            final Function2<? super Composer, ? super Integer, Unit> function20 = function10;
            final int i18 = floatingActionButtonPosition3;
            final boolean z2 = isFloatingActionButtonDocked5;
            final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function21 = function8;
            final boolean z3 = drawerGesturesEnabled3;
            final Shape shape = drawerShape4;
            final float f = drawerElevation3;
            final long j3 = drawerBackgroundColor3;
            final long j4 = drawerContentColor3;
            final long j5 = drawerScrimColor3;
            final long j6 = backgroundColor4;
            final long j7 = contentColor3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$Scaffold$2
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

                public final void invoke(Composer composer, int i19) {
                    ScaffoldKt.m1181Scaffold27mzLpw(modifier5, scaffoldState7, function17, function18, function19, function20, i18, z2, function21, z3, shape, f, j3, j4, j5, j6, j7, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i3 = 12582912;
        $dirty5 |= i3;
        if (($dirty4 & 1533916891) != 306783378) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 2) != 0) {
                    scaffoldState2 = rememberScaffoldState(null, null, $composer2, 0, 3);
                    $dirty4 &= -113;
                } else {
                    scaffoldState2 = scaffoldState;
                }
                if (i5 != 0) {
                    function2M1074getLambda1$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1074getLambda1$material_release();
                } else {
                    function2M1074getLambda1$material_release = function2;
                }
                if (i7 != 0) {
                    function2M1075getLambda2$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1075getLambda2$material_release();
                } else {
                    function2M1075getLambda2$material_release = function3;
                }
                if (i8 != 0) {
                    function3M1076getLambda3$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1076getLambda3$material_release();
                } else {
                    function3M1076getLambda3$material_release = function4;
                }
                if (i9 != 0) {
                    function2M1077getLambda4$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1077getLambda4$material_release();
                } else {
                    function2M1077getLambda4$material_release = function5;
                }
                if (i10 != 0) {
                    floatingActionButtonPosition2 = FabPosition.INSTANCE.m1119getEnd5ygKITE();
                } else {
                    floatingActionButtonPosition2 = floatingActionButtonPosition;
                }
                if (i11 != 0) {
                    isFloatingActionButtonDocked2 = false;
                } else {
                    isFloatingActionButtonDocked2 = isFloatingActionButtonDocked;
                }
                if (i12 != 0) {
                    function7 = null;
                } else {
                    function7 = function6;
                }
                if (i13 != 0) {
                    drawerGesturesEnabled2 = true;
                } else {
                    drawerGesturesEnabled2 = drawerGesturesEnabled;
                }
                $dirty = $dirty4;
                scaffoldState3 = scaffoldState2;
                if ((i & 1024) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer2, 6).getLarge();
                    $dirty5 &= -15;
                } else {
                    drawerShape2 = drawerShape;
                }
                if (i14 != 0) {
                    drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                } else {
                    drawerElevation2 = drawerElevation;
                }
                if ((i & 4096) != 0) {
                    $dirty1 = $dirty5 & (-897);
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                } else {
                    $dirty1 = $dirty5;
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                isFloatingActionButtonDocked3 = isFloatingActionButtonDocked2;
                if ((i & 8192) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer2, ($dirty1 >> 6) & 14);
                    $dirty1 &= -7169;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 16384) != 0) {
                    drawerScrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                    $dirty1 &= -57345;
                } else {
                    drawerScrimColor2 = drawerScrimColor;
                }
                if ((32768 & i) != 0) {
                    backgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1041getBackground0d7_KjU();
                    $dirty1 &= -458753;
                } else {
                    backgroundColor2 = backgroundColor;
                }
                if ((i & 65536) != 0) {
                    $dirty2 = $dirty;
                    drawerShape3 = drawerShape2;
                    $dirty3 = $dirty1 & (-3670017);
                    contentColor2 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty1 >> 15) & 14);
                    isFloatingActionButtonDocked4 = isFloatingActionButtonDocked3;
                    backgroundColor3 = backgroundColor2;
                    modifier3 = modifier2;
                    scaffoldState4 = scaffoldState3;
                } else {
                    $dirty2 = $dirty;
                    drawerShape3 = drawerShape2;
                    contentColor2 = contentColor;
                    backgroundColor3 = backgroundColor2;
                    $dirty3 = $dirty1;
                    modifier3 = modifier2;
                    isFloatingActionButtonDocked4 = isFloatingActionButtonDocked3;
                    scaffoldState4 = scaffoldState3;
                }
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 2) != 0) {
                    scaffoldState2 = rememberScaffoldState(null, null, $composer2, 0, 3);
                    $dirty4 &= -113;
                } else {
                    scaffoldState2 = scaffoldState;
                }
                if (i5 != 0) {
                    function2M1074getLambda1$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1074getLambda1$material_release();
                } else {
                    function2M1074getLambda1$material_release = function2;
                }
                if (i7 != 0) {
                    function2M1075getLambda2$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1075getLambda2$material_release();
                } else {
                    function2M1075getLambda2$material_release = function3;
                }
                if (i8 != 0) {
                    function3M1076getLambda3$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1076getLambda3$material_release();
                } else {
                    function3M1076getLambda3$material_release = function4;
                }
                if (i9 != 0) {
                    function2M1077getLambda4$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1077getLambda4$material_release();
                } else {
                    function2M1077getLambda4$material_release = function5;
                }
                if (i10 != 0) {
                    floatingActionButtonPosition2 = FabPosition.INSTANCE.m1119getEnd5ygKITE();
                } else {
                    floatingActionButtonPosition2 = floatingActionButtonPosition;
                }
                if (i11 != 0) {
                    isFloatingActionButtonDocked2 = false;
                } else {
                    isFloatingActionButtonDocked2 = isFloatingActionButtonDocked;
                }
                if (i12 != 0) {
                    function7 = null;
                } else {
                    function7 = function6;
                }
                if (i13 != 0) {
                    drawerGesturesEnabled2 = true;
                } else {
                    drawerGesturesEnabled2 = drawerGesturesEnabled;
                }
                $dirty = $dirty4;
                scaffoldState3 = scaffoldState2;
                if ((i & 1024) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer2, 6).getLarge();
                    $dirty5 &= -15;
                } else {
                    drawerShape2 = drawerShape;
                }
                if (i14 != 0) {
                    drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                } else {
                    drawerElevation2 = drawerElevation;
                }
                if ((i & 4096) != 0) {
                    $dirty1 = $dirty5 & (-897);
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                } else {
                    $dirty1 = $dirty5;
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                isFloatingActionButtonDocked3 = isFloatingActionButtonDocked2;
                if ((i & 8192) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer2, ($dirty1 >> 6) & 14);
                    $dirty1 &= -7169;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 16384) != 0) {
                    drawerScrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                    $dirty1 &= -57345;
                } else {
                    drawerScrimColor2 = drawerScrimColor;
                }
                if ((32768 & i) != 0) {
                    backgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1041getBackground0d7_KjU();
                    $dirty1 &= -458753;
                } else {
                    backgroundColor2 = backgroundColor;
                }
                if ((i & 65536) != 0) {
                    $dirty2 = $dirty;
                    drawerShape3 = drawerShape2;
                    $dirty3 = $dirty1 & (-3670017);
                    contentColor2 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty1 >> 15) & 14);
                    isFloatingActionButtonDocked4 = isFloatingActionButtonDocked3;
                    backgroundColor3 = backgroundColor2;
                    modifier3 = modifier2;
                    scaffoldState4 = scaffoldState3;
                } else {
                    $dirty2 = $dirty;
                    drawerShape3 = drawerShape2;
                    contentColor2 = contentColor;
                    backgroundColor3 = backgroundColor2;
                    $dirty3 = $dirty1;
                    modifier3 = modifier2;
                    isFloatingActionButtonDocked4 = isFloatingActionButtonDocked3;
                    scaffoldState4 = scaffoldState3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1037492569, $dirty2, $dirty3, "androidx.compose.material.Scaffold (Scaffold.kt:158)");
            }
            final long j8 = backgroundColor3;
            final long j9 = contentColor2;
            final int i19 = $dirty3;
            final boolean z4 = isFloatingActionButtonDocked4;
            final int i110 = floatingActionButtonPosition2;
            final Function2<? super Composer, ? super Integer, Unit> function110 = function2M1074getLambda1$material_release;
            final Function2<? super Composer, ? super Integer, Unit> function111 = function2M1077getLambda4$material_release;
            final Function2<? super Composer, ? super Integer, Unit> function112 = function2M1075getLambda2$material_release;
            final int i111 = $dirty2;
            final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function113 = function3M1076getLambda3$material_release;
            final ScaffoldState scaffoldState8 = scaffoldState4;
            boolean isFloatingActionButtonDocked7 = isFloatingActionButtonDocked4;
            child = ComposableLambdaKt.composableLambda($composer2, 1823402604, true, new Function3<Modifier, Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$Scaffold$child$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Modifier modifier6, Composer composer, Integer num) {
                    invoke(modifier6, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Modifier childModifier, Composer $composer3, int $changed2) {
                    Intrinsics.checkNotNullParameter(childModifier, "childModifier");
                    ComposerKt.sourceInformation($composer3, "C179@8062L525:Scaffold.kt#jmzs0o");
                    int $dirty6 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty6 |= $composer3.changed(childModifier) ? 4 : 2;
                    }
                    int $dirty7 = $dirty6;
                    if (($dirty7 & 91) != 18 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1823402604, $dirty7, -1, "androidx.compose.material.Scaffold.<anonymous> (Scaffold.kt:178)");
                        }
                        long j10 = j8;
                        long j11 = j9;
                        final boolean z5 = z4;
                        final int i112 = i110;
                        final Function2<? super Composer, ? super Integer, Unit> function114 = function110;
                        final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function115 = content;
                        final Function2<? super Composer, ? super Integer, Unit> function116 = function111;
                        final Function2<? super Composer, ? super Integer, Unit> function22 = function112;
                        final int i113 = i111;
                        final int i20 = i19;
                        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function23 = function113;
                        final ScaffoldState scaffoldState9 = scaffoldState8;
                        int i21 = i19;
                        SurfaceKt.m1210SurfaceFjzlyU(childModifier, null, j10, j11, null, 0.0f, ComposableLambdaKt.composableLambda($composer3, -1128984656, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$Scaffold$child$1.1
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

                            public final void invoke(Composer $composer4, int $changed3) {
                                ComposerKt.sourceInformation($composer4, "C180@8164L413:Scaffold.kt#jmzs0o");
                                if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1128984656, $changed3, -1, "androidx.compose.material.Scaffold.<anonymous>.<anonymous> (Scaffold.kt:179)");
                                    }
                                    boolean z6 = z5;
                                    int i22 = i112;
                                    Function2<Composer, Integer, Unit> function24 = function114;
                                    Function3<PaddingValues, Composer, Integer, Unit> function25 = function115;
                                    final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function26 = function23;
                                    final ScaffoldState scaffoldState10 = scaffoldState9;
                                    final int i23 = i113;
                                    ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, 533782017, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt.Scaffold.child.1.1.1
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

                                        public final void invoke(Composer $composer5, int $changed4) {
                                            ComposerKt.sourceInformation($composer5, "C186@8417L45:Scaffold.kt#jmzs0o");
                                            if (($changed4 & 11) == 2 && $composer5.getSkipping()) {
                                                $composer5.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(533782017, $changed4, -1, "androidx.compose.material.Scaffold.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:185)");
                                            }
                                            function26.invoke(scaffoldState10.getSnackbarHostState(), $composer5, Integer.valueOf((i23 >> 9) & 112));
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    });
                                    Function2<Composer, Integer, Unit> function27 = function116;
                                    Function2<Composer, Integer, Unit> function28 = function22;
                                    int i24 = i113;
                                    ScaffoldKt.m1182ScaffoldLayoutMDYNRJg(z6, i22, function24, function25, composableLambda, function27, function28, $composer4, ((i24 >> 21) & 14) | 24576 | ((i24 >> 15) & 112) | (i24 & 896) | ((i20 >> 12) & 7168) | (458752 & i24) | ((i24 << 9) & 3670016));
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        }), $composer3, 1572864 | ($dirty7 & 14) | ((i21 >> 9) & 896) | ((i21 >> 9) & 7168), 50);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            });
            if (function7 != null) {
                $composer2.startReplaceableGroup(-1013848234);
                ComposerKt.sourceInformation($composer2, "195@8636L487");
                DrawerKt.m1091ModalDrawerGs3lGvM(function7, modifier3, scaffoldState4.getDrawerState(), drawerGesturesEnabled2, drawerShape3, drawerElevation2, drawerBackgroundColor2, drawerContentColor2, drawerScrimColor2, ComposableLambdaKt.composableLambda($composer2, 100842932, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$Scaffold$1
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
                        ComposerKt.sourceInformation($composer3, "C205@9096L15:Scaffold.kt#jmzs0o");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(100842932, $changed2, -1, "androidx.compose.material.Scaffold.<anonymous> (Scaffold.kt:205)");
                        }
                        child.invoke(Modifier.INSTANCE, $composer3, 54);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer2, (($dirty2 >> 24) & 14) | 805306368 | (($dirty2 << 3) & 112) | (($dirty2 >> 18) & 7168) | (($dirty3 << 12) & 57344) | (($dirty3 << 12) & 458752) | (($dirty3 << 12) & 3670016) | (($dirty3 << 12) & 29360128) | (($dirty3 << 12) & 234881024), 0);
                $composer2.endReplaceableGroup();
            } else {
                $composer2.startReplaceableGroup(-1013847725);
                ComposerKt.sourceInformation($composer2, "208@9145L15");
                child.invoke(modifier3, $composer2, Integer.valueOf(($dirty2 & 14) | 48));
                $composer2.endReplaceableGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            isFloatingActionButtonDocked5 = isFloatingActionButtonDocked7;
            scaffoldState5 = scaffoldState4;
            modifier4 = modifier3;
            drawerShape4 = drawerShape3;
            function8 = function7;
            drawerGesturesEnabled3 = drawerGesturesEnabled2;
            drawerElevation3 = drawerElevation2;
            function9 = function3M1076getLambda3$material_release;
            function10 = function2M1077getLambda4$material_release;
            floatingActionButtonPosition3 = floatingActionButtonPosition2;
            drawerContentColor3 = drawerContentColor2;
            drawerBackgroundColor3 = drawerBackgroundColor2;
            drawerScrimColor3 = drawerScrimColor2;
            backgroundColor4 = backgroundColor3;
            contentColor3 = contentColor2;
            function11 = function2M1074getLambda1$material_release;
            function12 = function2M1075getLambda2$material_release;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 2) != 0) {
                    scaffoldState2 = rememberScaffoldState(null, null, $composer2, 0, 3);
                    $dirty4 &= -113;
                } else {
                    scaffoldState2 = scaffoldState;
                }
                if (i5 != 0) {
                    function2M1074getLambda1$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1074getLambda1$material_release();
                } else {
                    function2M1074getLambda1$material_release = function2;
                }
                if (i7 != 0) {
                    function2M1075getLambda2$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1075getLambda2$material_release();
                } else {
                    function2M1075getLambda2$material_release = function3;
                }
                if (i8 != 0) {
                    function3M1076getLambda3$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1076getLambda3$material_release();
                } else {
                    function3M1076getLambda3$material_release = function4;
                }
                if (i9 != 0) {
                    function2M1077getLambda4$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1077getLambda4$material_release();
                } else {
                    function2M1077getLambda4$material_release = function5;
                }
                if (i10 != 0) {
                    floatingActionButtonPosition2 = FabPosition.INSTANCE.m1119getEnd5ygKITE();
                } else {
                    floatingActionButtonPosition2 = floatingActionButtonPosition;
                }
                if (i11 != 0) {
                    isFloatingActionButtonDocked2 = false;
                } else {
                    isFloatingActionButtonDocked2 = isFloatingActionButtonDocked;
                }
                if (i12 != 0) {
                    function7 = null;
                } else {
                    function7 = function6;
                }
                if (i13 != 0) {
                    drawerGesturesEnabled2 = true;
                } else {
                    drawerGesturesEnabled2 = drawerGesturesEnabled;
                }
                $dirty = $dirty4;
                scaffoldState3 = scaffoldState2;
                if ((i & 1024) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer2, 6).getLarge();
                    $dirty5 &= -15;
                } else {
                    drawerShape2 = drawerShape;
                }
                if (i14 != 0) {
                    drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                } else {
                    drawerElevation2 = drawerElevation;
                }
                if ((i & 4096) != 0) {
                    $dirty1 = $dirty5 & (-897);
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                } else {
                    $dirty1 = $dirty5;
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                isFloatingActionButtonDocked3 = isFloatingActionButtonDocked2;
                if ((i & 8192) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer2, ($dirty1 >> 6) & 14);
                    $dirty1 &= -7169;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 16384) != 0) {
                    drawerScrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                    $dirty1 &= -57345;
                } else {
                    drawerScrimColor2 = drawerScrimColor;
                }
                if ((32768 & i) != 0) {
                    backgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1041getBackground0d7_KjU();
                    $dirty1 &= -458753;
                } else {
                    backgroundColor2 = backgroundColor;
                }
                if ((i & 65536) != 0) {
                    $dirty2 = $dirty;
                    drawerShape3 = drawerShape2;
                    $dirty3 = $dirty1 & (-3670017);
                    contentColor2 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty1 >> 15) & 14);
                    isFloatingActionButtonDocked4 = isFloatingActionButtonDocked3;
                    backgroundColor3 = backgroundColor2;
                    modifier3 = modifier2;
                    scaffoldState4 = scaffoldState3;
                } else {
                    $dirty2 = $dirty;
                    drawerShape3 = drawerShape2;
                    contentColor2 = contentColor;
                    backgroundColor3 = backgroundColor2;
                    $dirty3 = $dirty1;
                    modifier3 = modifier2;
                    isFloatingActionButtonDocked4 = isFloatingActionButtonDocked3;
                    scaffoldState4 = scaffoldState3;
                }
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 2) != 0) {
                    scaffoldState2 = rememberScaffoldState(null, null, $composer2, 0, 3);
                    $dirty4 &= -113;
                } else {
                    scaffoldState2 = scaffoldState;
                }
                if (i5 != 0) {
                    function2M1074getLambda1$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1074getLambda1$material_release();
                } else {
                    function2M1074getLambda1$material_release = function2;
                }
                if (i7 != 0) {
                    function2M1075getLambda2$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1075getLambda2$material_release();
                } else {
                    function2M1075getLambda2$material_release = function3;
                }
                if (i8 != 0) {
                    function3M1076getLambda3$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1076getLambda3$material_release();
                } else {
                    function3M1076getLambda3$material_release = function4;
                }
                if (i9 != 0) {
                    function2M1077getLambda4$material_release = ComposableSingletons$ScaffoldKt.INSTANCE.m1077getLambda4$material_release();
                } else {
                    function2M1077getLambda4$material_release = function5;
                }
                if (i10 != 0) {
                    floatingActionButtonPosition2 = FabPosition.INSTANCE.m1119getEnd5ygKITE();
                } else {
                    floatingActionButtonPosition2 = floatingActionButtonPosition;
                }
                if (i11 != 0) {
                    isFloatingActionButtonDocked2 = false;
                } else {
                    isFloatingActionButtonDocked2 = isFloatingActionButtonDocked;
                }
                if (i12 != 0) {
                    function7 = null;
                } else {
                    function7 = function6;
                }
                if (i13 != 0) {
                    drawerGesturesEnabled2 = true;
                } else {
                    drawerGesturesEnabled2 = drawerGesturesEnabled;
                }
                $dirty = $dirty4;
                scaffoldState3 = scaffoldState2;
                if ((i & 1024) != 0) {
                    drawerShape2 = MaterialTheme.INSTANCE.getShapes($composer2, 6).getLarge();
                    $dirty5 &= -15;
                } else {
                    drawerShape2 = drawerShape;
                }
                if (i14 != 0) {
                    drawerElevation2 = DrawerDefaults.INSTANCE.m1088getElevationD9Ej5fM();
                } else {
                    drawerElevation2 = drawerElevation;
                }
                if ((i & 4096) != 0) {
                    $dirty1 = $dirty5 & (-897);
                    drawerBackgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                } else {
                    $dirty1 = $dirty5;
                    drawerBackgroundColor2 = drawerBackgroundColor;
                }
                isFloatingActionButtonDocked3 = isFloatingActionButtonDocked2;
                if ((i & 8192) != 0) {
                    drawerContentColor2 = ColorsKt.m1066contentColorForek8zF_U(drawerBackgroundColor2, $composer2, ($dirty1 >> 6) & 14);
                    $dirty1 &= -7169;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if ((i & 16384) != 0) {
                    drawerScrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                    $dirty1 &= -57345;
                } else {
                    drawerScrimColor2 = drawerScrimColor;
                }
                if ((32768 & i) != 0) {
                    backgroundColor2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1041getBackground0d7_KjU();
                    $dirty1 &= -458753;
                } else {
                    backgroundColor2 = backgroundColor;
                }
                if ((i & 65536) != 0) {
                    $dirty2 = $dirty;
                    drawerShape3 = drawerShape2;
                    $dirty3 = $dirty1 & (-3670017);
                    contentColor2 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty1 >> 15) & 14);
                    isFloatingActionButtonDocked4 = isFloatingActionButtonDocked3;
                    backgroundColor3 = backgroundColor2;
                    modifier3 = modifier2;
                    scaffoldState4 = scaffoldState3;
                } else {
                    $dirty2 = $dirty;
                    drawerShape3 = drawerShape2;
                    contentColor2 = contentColor;
                    backgroundColor3 = backgroundColor2;
                    $dirty3 = $dirty1;
                    modifier3 = modifier2;
                    isFloatingActionButtonDocked4 = isFloatingActionButtonDocked3;
                    scaffoldState4 = scaffoldState3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1037492569, $dirty2, $dirty3, "androidx.compose.material.Scaffold (Scaffold.kt:158)");
            }
            final long j10 = backgroundColor3;
            final long j11 = contentColor2;
            final int i112 = $dirty3;
            final boolean z5 = isFloatingActionButtonDocked4;
            final int i113 = floatingActionButtonPosition2;
            final Function2<? super Composer, ? super Integer, Unit> function114 = function2M1074getLambda1$material_release;
            final Function2<? super Composer, ? super Integer, Unit> function115 = function2M1077getLambda4$material_release;
            final Function2<? super Composer, ? super Integer, Unit> function116 = function2M1075getLambda2$material_release;
            final int i114 = $dirty2;
            final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function117 = function3M1076getLambda3$material_release;
            final ScaffoldState scaffoldState9 = scaffoldState4;
            boolean isFloatingActionButtonDocked8 = isFloatingActionButtonDocked4;
            child = ComposableLambdaKt.composableLambda($composer2, 1823402604, true, new Function3<Modifier, Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$Scaffold$child$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Modifier modifier6, Composer composer, Integer num) {
                    invoke(modifier6, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Modifier childModifier, Composer $composer3, int $changed2) {
                    Intrinsics.checkNotNullParameter(childModifier, "childModifier");
                    ComposerKt.sourceInformation($composer3, "C179@8062L525:Scaffold.kt#jmzs0o");
                    int $dirty6 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty6 |= $composer3.changed(childModifier) ? 4 : 2;
                    }
                    int $dirty7 = $dirty6;
                    if (($dirty7 & 91) != 18 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1823402604, $dirty7, -1, "androidx.compose.material.Scaffold.<anonymous> (Scaffold.kt:178)");
                        }
                        long j12 = j10;
                        long j13 = j11;
                        final boolean z6 = z5;
                        final int i115 = i113;
                        final Function2<? super Composer, ? super Integer, Unit> function118 = function114;
                        final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function119 = content;
                        final Function2<? super Composer, ? super Integer, Unit> function1110 = function115;
                        final Function2<? super Composer, ? super Integer, Unit> function22 = function116;
                        final int i116 = i114;
                        final int i20 = i112;
                        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function23 = function117;
                        final ScaffoldState scaffoldState10 = scaffoldState9;
                        int i21 = i112;
                        SurfaceKt.m1210SurfaceFjzlyU(childModifier, null, j12, j13, null, 0.0f, ComposableLambdaKt.composableLambda($composer3, -1128984656, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$Scaffold$child$1.1
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

                            public final void invoke(Composer $composer4, int $changed3) {
                                ComposerKt.sourceInformation($composer4, "C180@8164L413:Scaffold.kt#jmzs0o");
                                if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1128984656, $changed3, -1, "androidx.compose.material.Scaffold.<anonymous>.<anonymous> (Scaffold.kt:179)");
                                    }
                                    boolean z7 = z6;
                                    int i22 = i115;
                                    Function2<Composer, Integer, Unit> function24 = function118;
                                    Function3<PaddingValues, Composer, Integer, Unit> function25 = function119;
                                    final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function26 = function23;
                                    final ScaffoldState scaffoldState11 = scaffoldState10;
                                    final int i23 = i116;
                                    ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, 533782017, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt.Scaffold.child.1.1.1
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

                                        public final void invoke(Composer $composer5, int $changed4) {
                                            ComposerKt.sourceInformation($composer5, "C186@8417L45:Scaffold.kt#jmzs0o");
                                            if (($changed4 & 11) == 2 && $composer5.getSkipping()) {
                                                $composer5.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(533782017, $changed4, -1, "androidx.compose.material.Scaffold.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:185)");
                                            }
                                            function26.invoke(scaffoldState11.getSnackbarHostState(), $composer5, Integer.valueOf((i23 >> 9) & 112));
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    });
                                    Function2<Composer, Integer, Unit> function27 = function1110;
                                    Function2<Composer, Integer, Unit> function28 = function22;
                                    int i24 = i116;
                                    ScaffoldKt.m1182ScaffoldLayoutMDYNRJg(z7, i22, function24, function25, composableLambda, function27, function28, $composer4, ((i24 >> 21) & 14) | 24576 | ((i24 >> 15) & 112) | (i24 & 896) | ((i20 >> 12) & 7168) | (458752 & i24) | ((i24 << 9) & 3670016));
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        }), $composer3, 1572864 | ($dirty7 & 14) | ((i21 >> 9) & 896) | ((i21 >> 9) & 7168), 50);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            });
            if (function7 != null) {
                $composer2.startReplaceableGroup(-1013848234);
                ComposerKt.sourceInformation($composer2, "195@8636L487");
                DrawerKt.m1091ModalDrawerGs3lGvM(function7, modifier3, scaffoldState4.getDrawerState(), drawerGesturesEnabled2, drawerShape3, drawerElevation2, drawerBackgroundColor2, drawerContentColor2, drawerScrimColor2, ComposableLambdaKt.composableLambda($composer2, 100842932, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$Scaffold$1
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
                        ComposerKt.sourceInformation($composer3, "C205@9096L15:Scaffold.kt#jmzs0o");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(100842932, $changed2, -1, "androidx.compose.material.Scaffold.<anonymous> (Scaffold.kt:205)");
                        }
                        child.invoke(Modifier.INSTANCE, $composer3, 54);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer2, (($dirty2 >> 24) & 14) | 805306368 | (($dirty2 << 3) & 112) | (($dirty2 >> 18) & 7168) | (($dirty3 << 12) & 57344) | (($dirty3 << 12) & 458752) | (($dirty3 << 12) & 3670016) | (($dirty3 << 12) & 29360128) | (($dirty3 << 12) & 234881024), 0);
                $composer2.endReplaceableGroup();
            } else {
                $composer2.startReplaceableGroup(-1013847725);
                ComposerKt.sourceInformation($composer2, "208@9145L15");
                child.invoke(modifier3, $composer2, Integer.valueOf(($dirty2 & 14) | 48));
                $composer2.endReplaceableGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            isFloatingActionButtonDocked5 = isFloatingActionButtonDocked8;
            scaffoldState5 = scaffoldState4;
            modifier4 = modifier3;
            drawerShape4 = drawerShape3;
            function8 = function7;
            drawerGesturesEnabled3 = drawerGesturesEnabled2;
            drawerElevation3 = drawerElevation2;
            function9 = function3M1076getLambda3$material_release;
            function10 = function2M1077getLambda4$material_release;
            floatingActionButtonPosition3 = floatingActionButtonPosition2;
            drawerContentColor3 = drawerContentColor2;
            drawerBackgroundColor3 = drawerBackgroundColor2;
            drawerScrimColor3 = drawerScrimColor2;
            backgroundColor4 = backgroundColor3;
            contentColor3 = contentColor2;
            function11 = function2M1074getLambda1$material_release;
            function12 = function2M1075getLambda2$material_release;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final ScaffoldState scaffoldState10 = scaffoldState5;
        final Function2<? super Composer, ? super Integer, Unit> function118 = function11;
        final Function2<? super Composer, ? super Integer, Unit> function119 = function12;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function120 = function9;
        final Function2<? super Composer, ? super Integer, Unit> function22 = function10;
        final int i115 = floatingActionButtonPosition3;
        final boolean z6 = isFloatingActionButtonDocked5;
        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function23 = function8;
        final boolean z7 = drawerGesturesEnabled3;
        final Shape shape2 = drawerShape4;
        final float f2 = drawerElevation3;
        final long j12 = drawerBackgroundColor3;
        final long j13 = drawerContentColor3;
        final long j14 = drawerScrimColor3;
        final long j15 = backgroundColor4;
        final long j16 = contentColor3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$Scaffold$2
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

            public final void invoke(Composer composer, int i116) {
                ScaffoldKt.m1181Scaffold27mzLpw(modifier6, scaffoldState10, function118, function119, function120, function22, i115, z6, function23, z7, shape2, f2, j12, j13, j14, j15, j16, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ScaffoldLayout-MDYNRJg, reason: not valid java name */
    public static final void m1182ScaffoldLayoutMDYNRJg(final boolean isFabDocked, final int fabPosition, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function6, Composer $composer, final int $changed) {
        int i;
        Composer $composer2 = $composer.startRestartGroup(-1401632215);
        ComposerKt.sourceInformation($composer2, "C(ScaffoldLayout)P(4,3:c#material.FabPosition,6,1,5,2)236@10234L4586,236@10217L4603:Scaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(isFabDocked) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(fabPosition) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? 16384 : 8192;
        }
        if ((458752 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function5) ? 131072 : 65536;
        }
        if ((3670016 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function6) ? 1048576 : 524288;
        }
        final int $dirty2 = $dirty;
        if ((2995931 & $dirty2) != 599186 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1401632215, $dirty2, -1, "androidx.compose.material.ScaffoldLayout (Scaffold.kt:227)");
            }
            Object[] keys$iv = {function2, function4, function5, FabPosition.m1111boximpl(fabPosition), Boolean.valueOf(isFabDocked), function6, function3};
            $composer2.startReplaceableGroup(-568225417);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv = false;
            for (Object key$iv : keys$iv) {
                invalid$iv |= $composer2.changed(key$iv);
            }
            Object value$iv$iv = $composer2.rememberedValue();
            if (invalid$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                i = 0;
                value$iv$iv = new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                        return m1184invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                    }

                    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m1184invoke0kLqBqw(final SubcomposeMeasureScope SubcomposeLayout, long constraints) {
                        Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                        final int layoutWidth = Constraints.m5218getMaxWidthimpl(constraints);
                        final int layoutHeight = Constraints.m5217getMaxHeightimpl(constraints);
                        final long looseConstraints = Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0);
                        final Function2<Composer, Integer, Unit> function7 = function2;
                        final Function2<Composer, Integer, Unit> function8 = function4;
                        final Function2<Composer, Integer, Unit> function9 = function5;
                        final int i2 = fabPosition;
                        final boolean z = isFabDocked;
                        final Function2<Composer, Integer, Unit> function10 = function6;
                        final int i3 = $dirty2;
                        final Function3<PaddingValues, Composer, Integer, Unit> function11 = function3;
                        return MeasureScope.CC.layout$default(SubcomposeLayout, layoutWidth, layoutHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1.1
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
                                FabPlacement fabPlacement;
                                Object maxElem$iv3;
                                Integer numValueOf;
                                Object maxElem$iv4;
                                Object maxElem$iv5;
                                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                List<Measurable> listSubcompose = SubcomposeLayout.subcompose(ScaffoldLayoutContent.TopBar, function7);
                                long j = looseConstraints;
                                List target$iv = new ArrayList(listSubcompose.size());
                                int size = listSubcompose.size();
                                for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                                    Object item$iv$iv = listSubcompose.get(index$iv$iv);
                                    Measurable it = (Measurable) item$iv$iv;
                                    target$iv.add(it.mo4225measureBRTryo0(j));
                                }
                                List topBarPlaceables = target$iv;
                                if (!topBarPlaceables.isEmpty()) {
                                    maxElem$iv = topBarPlaceables.get(0);
                                    Placeable it2 = (Placeable) maxElem$iv;
                                    int maxValue$iv = it2.getHeight();
                                    int i$iv = 1;
                                    int lastIndex = CollectionsKt.getLastIndex(topBarPlaceables);
                                    if (1 <= lastIndex) {
                                        while (true) {
                                            Object e$iv = topBarPlaceables.get(i$iv);
                                            Placeable it3 = (Placeable) e$iv;
                                            int v$iv = it3.getHeight();
                                            if (maxValue$iv < v$iv) {
                                                maxElem$iv = e$iv;
                                                maxValue$iv = v$iv;
                                            }
                                            if (i$iv == lastIndex) {
                                                break;
                                            } else {
                                                i$iv++;
                                            }
                                        }
                                    }
                                } else {
                                    maxElem$iv = null;
                                }
                                Placeable placeable = (Placeable) maxElem$iv;
                                int topBarHeight = placeable != null ? placeable.getHeight() : 0;
                                List<Measurable> listSubcompose2 = SubcomposeLayout.subcompose(ScaffoldLayoutContent.Snackbar, function8);
                                long j2 = looseConstraints;
                                List target$iv2 = new ArrayList(listSubcompose2.size());
                                int size2 = listSubcompose2.size();
                                for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
                                    Object item$iv$iv2 = listSubcompose2.get(index$iv$iv2);
                                    Measurable it4 = (Measurable) item$iv$iv2;
                                    target$iv2.add(it4.mo4225measureBRTryo0(j2));
                                }
                                List $this$fastMaxBy$iv = target$iv2;
                                if (!$this$fastMaxBy$iv.isEmpty()) {
                                    maxElem$iv2 = $this$fastMaxBy$iv.get(0);
                                    Placeable it5 = (Placeable) maxElem$iv2;
                                    int maxValue$iv2 = it5.getHeight();
                                    int i$iv2 = 1;
                                    int lastIndex2 = CollectionsKt.getLastIndex($this$fastMaxBy$iv);
                                    if (1 <= lastIndex2) {
                                        while (true) {
                                            Object e$iv2 = $this$fastMaxBy$iv.get(i$iv2);
                                            Placeable it6 = (Placeable) e$iv2;
                                            int v$iv2 = it6.getHeight();
                                            if (maxValue$iv2 < v$iv2) {
                                                maxElem$iv2 = e$iv2;
                                                maxValue$iv2 = v$iv2;
                                            }
                                            if (i$iv2 == lastIndex2) {
                                                break;
                                            } else {
                                                i$iv2++;
                                            }
                                        }
                                    }
                                } else {
                                    maxElem$iv2 = null;
                                }
                                Placeable placeable2 = (Placeable) maxElem$iv2;
                                int snackbarHeight = placeable2 != null ? placeable2.getHeight() : 0;
                                List<Measurable> listSubcompose3 = SubcomposeLayout.subcompose(ScaffoldLayoutContent.Fab, function9);
                                long j3 = looseConstraints;
                                int $i$f$fastMap = 0;
                                List target$iv3 = new ArrayList(listSubcompose3.size());
                                int index$iv$iv3 = 0;
                                int size3 = listSubcompose3.size();
                                while (index$iv$iv3 < size3) {
                                    Object item$iv$iv3 = listSubcompose3.get(index$iv$iv3);
                                    int $i$f$fastMap2 = $i$f$fastMap;
                                    Measurable measurable = (Measurable) item$iv$iv3;
                                    target$iv3.add(measurable.mo4225measureBRTryo0(j3));
                                    index$iv$iv3++;
                                    listSubcompose3 = listSubcompose3;
                                    $i$f$fastMap = $i$f$fastMap2;
                                }
                                List fabPlaceables = target$iv3;
                                if (!fabPlaceables.isEmpty()) {
                                    if (!fabPlaceables.isEmpty()) {
                                        maxElem$iv4 = fabPlaceables.get(0);
                                        Placeable it7 = (Placeable) maxElem$iv4;
                                        int maxValue$iv3 = it7.getWidth();
                                        int i$iv3 = 1;
                                        int lastIndex3 = CollectionsKt.getLastIndex(fabPlaceables);
                                        if (1 <= lastIndex3) {
                                            while (true) {
                                                Object e$iv3 = fabPlaceables.get(i$iv3);
                                                Placeable it8 = (Placeable) e$iv3;
                                                int v$iv3 = it8.getWidth();
                                                if (maxValue$iv3 < v$iv3) {
                                                    maxElem$iv4 = e$iv3;
                                                    maxValue$iv3 = v$iv3;
                                                }
                                                if (i$iv3 == lastIndex3) {
                                                    break;
                                                } else {
                                                    i$iv3++;
                                                }
                                            }
                                        }
                                    } else {
                                        maxElem$iv4 = null;
                                    }
                                    Placeable placeable3 = (Placeable) maxElem$iv4;
                                    int fabWidth = placeable3 != null ? placeable3.getWidth() : 0;
                                    if (!fabPlaceables.isEmpty()) {
                                        maxElem$iv5 = fabPlaceables.get(0);
                                        Placeable it9 = (Placeable) maxElem$iv5;
                                        int maxValue$iv4 = it9.getHeight();
                                        int i$iv4 = 1;
                                        int lastIndex4 = CollectionsKt.getLastIndex(fabPlaceables);
                                        if (1 <= lastIndex4) {
                                            while (true) {
                                                Object e$iv4 = fabPlaceables.get(i$iv4);
                                                Placeable it10 = (Placeable) e$iv4;
                                                int height = it10.getHeight();
                                                if (maxValue$iv4 < height) {
                                                    maxElem$iv5 = e$iv4;
                                                    maxValue$iv4 = height;
                                                }
                                                if (i$iv4 == lastIndex4) {
                                                    break;
                                                } else {
                                                    i$iv4++;
                                                }
                                            }
                                        }
                                    } else {
                                        maxElem$iv5 = null;
                                    }
                                    Placeable placeable4 = (Placeable) maxElem$iv5;
                                    int fabHeight = placeable4 != null ? placeable4.getHeight() : 0;
                                    if (fabWidth == 0 || fabHeight == 0) {
                                        fabPlacement = null;
                                    } else {
                                        int fabLeftOffset = FabPosition.m1114equalsimpl0(i2, FabPosition.INSTANCE.m1119getEnd5ygKITE()) ? SubcomposeLayout.getLayoutDirection() == LayoutDirection.Ltr ? (layoutWidth - SubcomposeLayout.mo321roundToPx0680j_4(ScaffoldKt.FabSpacing)) - fabWidth : SubcomposeLayout.mo321roundToPx0680j_4(ScaffoldKt.FabSpacing) : (layoutWidth - fabWidth) / 2;
                                        fabPlacement = new FabPlacement(z, fabLeftOffset, fabWidth, fabHeight);
                                    }
                                } else {
                                    fabPlacement = null;
                                }
                                final FabPlacement fabPlacement2 = fabPlacement;
                                SubcomposeMeasureScope subcomposeMeasureScope = SubcomposeLayout;
                                ScaffoldLayoutContent scaffoldLayoutContent = ScaffoldLayoutContent.BottomBar;
                                final Function2<Composer, Integer, Unit> function12 = function10;
                                final int i4 = i3;
                                List<Measurable> listSubcompose4 = subcomposeMeasureScope.subcompose(scaffoldLayoutContent, ComposableLambdaKt.composableLambdaInstance(1529070963, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1$1$bottomBarPlaceables$1
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
                                        ComposerKt.sourceInformation($composer3, "C289@12424L144:Scaffold.kt#jmzs0o");
                                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1529070963, $changed2, -1, "androidx.compose.material.ScaffoldLayout.<anonymous>.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:288)");
                                            }
                                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ScaffoldKt.getLocalFabPlacement().provides(fabPlacement2)}, function12, $composer3, ((i4 >> 15) & 112) | 8);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer3.skipToGroupEnd();
                                    }
                                }));
                                long j4 = looseConstraints;
                                int $i$f$fastMap3 = 0;
                                List target$iv4 = new ArrayList(listSubcompose4.size());
                                int index$iv$iv4 = 0;
                                int size4 = listSubcompose4.size();
                                while (index$iv$iv4 < size4) {
                                    Object item$iv$iv4 = listSubcompose4.get(index$iv$iv4);
                                    int $i$f$fastMap4 = $i$f$fastMap3;
                                    Measurable it11 = (Measurable) item$iv$iv4;
                                    target$iv4.add(it11.mo4225measureBRTryo0(j4));
                                    index$iv$iv4++;
                                    size4 = size4;
                                    $i$f$fastMap3 = $i$f$fastMap4;
                                }
                                List $this$fastMaxBy$iv2 = target$iv4;
                                if (!$this$fastMaxBy$iv2.isEmpty()) {
                                    maxElem$iv3 = $this$fastMaxBy$iv2.get(0);
                                    Placeable it12 = (Placeable) maxElem$iv3;
                                    int maxValue$iv5 = it12.getHeight();
                                    int i$iv5 = 1;
                                    int lastIndex5 = CollectionsKt.getLastIndex($this$fastMaxBy$iv2);
                                    if (1 <= lastIndex5) {
                                        while (true) {
                                            Object e$iv5 = $this$fastMaxBy$iv2.get(i$iv5);
                                            Placeable it13 = (Placeable) e$iv5;
                                            int height2 = it13.getHeight();
                                            if (maxValue$iv5 < height2) {
                                                maxElem$iv3 = e$iv5;
                                                maxValue$iv5 = height2;
                                            }
                                            if (i$iv5 == lastIndex5) {
                                                break;
                                            } else {
                                                i$iv5++;
                                            }
                                        }
                                    }
                                } else {
                                    maxElem$iv3 = null;
                                }
                                Placeable placeable5 = (Placeable) maxElem$iv3;
                                final int bottomBarHeight = placeable5 != null ? placeable5.getHeight() : 0;
                                if (fabPlacement2 != null) {
                                    SubcomposeMeasureScope subcomposeMeasureScope2 = SubcomposeLayout;
                                    numValueOf = Integer.valueOf(bottomBarHeight == 0 ? fabPlacement2.getHeight() + subcomposeMeasureScope2.mo321roundToPx0680j_4(ScaffoldKt.FabSpacing) : z ? bottomBarHeight + (fabPlacement2.getHeight() / 2) : fabPlacement2.getHeight() + bottomBarHeight + subcomposeMeasureScope2.mo321roundToPx0680j_4(ScaffoldKt.FabSpacing));
                                } else {
                                    numValueOf = null;
                                }
                                Integer fabOffsetFromBottom = numValueOf;
                                int snackbarOffsetFromBottom = snackbarHeight != 0 ? snackbarHeight + (fabOffsetFromBottom != null ? fabOffsetFromBottom.intValue() : bottomBarHeight) : 0;
                                int bodyContentHeight = layoutHeight - topBarHeight;
                                SubcomposeMeasureScope subcomposeMeasureScope3 = SubcomposeLayout;
                                ScaffoldLayoutContent scaffoldLayoutContent2 = ScaffoldLayoutContent.MainContent;
                                final SubcomposeMeasureScope subcomposeMeasureScope4 = SubcomposeLayout;
                                final Function3<PaddingValues, Composer, Integer, Unit> function13 = function11;
                                final int i5 = i3;
                                List<Measurable> listSubcompose5 = subcomposeMeasureScope3.subcompose(scaffoldLayoutContent2, ComposableLambdaKt.composableLambdaInstance(-1132241596, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1$1$bodyContentPlaceables$1
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
                                        ComposerKt.sourceInformation($composer3, "C321@13846L21:Scaffold.kt#jmzs0o");
                                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1132241596, $changed2, -1, "androidx.compose.material.ScaffoldLayout.<anonymous>.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:319)");
                                            }
                                            PaddingValues innerPadding = PaddingKt.m484PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, subcomposeMeasureScope4.mo324toDpu2uoSUM(bottomBarHeight), 7, null);
                                            function13.invoke(innerPadding, $composer3, Integer.valueOf((i5 >> 6) & 112));
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer3.skipToGroupEnd();
                                    }
                                }));
                                long j5 = looseConstraints;
                                List target$iv5 = new ArrayList(listSubcompose5.size());
                                List<Measurable> list = listSubcompose5;
                                int size5 = list.size();
                                int $i$f$fastMap5 = 0;
                                while ($i$f$fastMap5 < size5) {
                                    Object item$iv$iv5 = list.get($i$f$fastMap5);
                                    List<Measurable> list2 = list;
                                    Measurable it14 = (Measurable) item$iv$iv5;
                                    long j6 = j5;
                                    target$iv5.add(it14.mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(j6, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(j6) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(j6) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(j6) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(j6) : bodyContentHeight)));
                                    $i$f$fastMap5++;
                                    size5 = size5;
                                    list = list2;
                                    j5 = j5;
                                }
                                List bodyContentPlaceables = target$iv5;
                                List $this$fastForEach$iv = bodyContentPlaceables;
                                int index$iv = 0;
                                for (int size6 = $this$fastForEach$iv.size(); index$iv < size6; size6 = size6) {
                                    Object item$iv = $this$fastForEach$iv.get(index$iv);
                                    Placeable it15 = (Placeable) item$iv;
                                    Placeable.PlacementScope.place$default(layout, it15, 0, topBarHeight, 0.0f, 4, null);
                                    index$iv++;
                                    bottomBarHeight = bottomBarHeight;
                                    $this$fastForEach$iv = $this$fastForEach$iv;
                                }
                                int bottomBarHeight2 = bottomBarHeight;
                                List $this$fastForEach$iv2 = topBarPlaceables;
                                int size7 = $this$fastForEach$iv2.size();
                                int index$iv2 = 0;
                                while (index$iv2 < size7) {
                                    Object item$iv2 = $this$fastForEach$iv2.get(index$iv2);
                                    Placeable it16 = (Placeable) item$iv2;
                                    Placeable.PlacementScope.place$default(layout, it16, 0, 0, 0.0f, 4, null);
                                    index$iv2++;
                                    size7 = size7;
                                    $this$fastForEach$iv2 = $this$fastForEach$iv2;
                                }
                                int i6 = layoutHeight;
                                List $this$fastForEach$iv3 = $this$fastMaxBy$iv;
                                int size8 = $this$fastForEach$iv3.size();
                                int index$iv3 = 0;
                                while (index$iv3 < size8) {
                                    Object item$iv3 = $this$fastForEach$iv3.get(index$iv3);
                                    Placeable it17 = (Placeable) item$iv3;
                                    Placeable.PlacementScope.place$default(layout, it17, 0, i6 - snackbarOffsetFromBottom, 0.0f, 4, null);
                                    index$iv3++;
                                    size8 = size8;
                                    $this$fastForEach$iv3 = $this$fastForEach$iv3;
                                    i6 = i6;
                                }
                                int i7 = layoutHeight;
                                List $this$fastForEach$iv4 = $this$fastMaxBy$iv2;
                                int size9 = $this$fastForEach$iv4.size();
                                int index$iv4 = 0;
                                while (index$iv4 < size9) {
                                    Object item$iv4 = $this$fastForEach$iv4.get(index$iv4);
                                    Placeable it18 = (Placeable) item$iv4;
                                    Placeable.PlacementScope.place$default(layout, it18, 0, i7 - bottomBarHeight2, 0.0f, 4, null);
                                    index$iv4++;
                                    size9 = size9;
                                    $this$fastForEach$iv4 = $this$fastForEach$iv4;
                                    i7 = i7;
                                }
                                int i8 = layoutHeight;
                                List $this$fastForEach$iv5 = fabPlaceables;
                                int size10 = $this$fastForEach$iv5.size();
                                int index$iv5 = 0;
                                while (index$iv5 < size10) {
                                    Object item$iv5 = $this$fastForEach$iv5.get(index$iv5);
                                    Placeable it19 = (Placeable) item$iv5;
                                    Placeable.PlacementScope.place$default(layout, it19, fabPlacement2 != null ? fabPlacement2.getLeft() : 0, i8 - (fabOffsetFromBottom != null ? fabOffsetFromBottom.intValue() : 0), 0.0f, 4, null);
                                    index$iv5++;
                                    size10 = size10;
                                    $this$fastForEach$iv5 = $this$fastForEach$iv5;
                                    i8 = i8;
                                }
                            }
                        }, 4, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                i = 0;
            }
            $composer2.endReplaceableGroup();
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) value$iv$iv, $composer2, i, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$2
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
                ScaffoldKt.m1182ScaffoldLayoutMDYNRJg(isFabDocked, fabPosition, function2, function3, function4, function5, function6, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    public static final ProvidableCompositionLocal<FabPlacement> getLocalFabPlacement() {
        return LocalFabPlacement;
    }
}
