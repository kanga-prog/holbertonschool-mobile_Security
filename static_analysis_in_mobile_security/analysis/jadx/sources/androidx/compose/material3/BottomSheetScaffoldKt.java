package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BottomSheetScaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0017\u001a\u0083\u0002\u0010\u0000\u001a\u00020\u00012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\f2\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015¢\u0006\u0002\b\u00052\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00102\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u0017\u0010\u001d\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 \u001aq\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020%26\u0010&\u001a2\u0012\u0013\u0012\u00110#¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0013\u0012\u00110+¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u00010'2!\u0010-\u001a\u001d\u0012\u0013\u0012\u00110#¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001aÃ\u0001\u0010.\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015¢\u0006\u0002\b\u00052&\u0010/\u001a\"\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052&\u00101\u001a\"\u0012\u0013\u0012\u001102¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u00052\u0006\u0010\u000b\u001a\u00020\f2\f\u00104\u001a\b\u0012\u0004\u0012\u00020+0\u00152\u0006\u00105\u001a\u00020%2\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b6\u00107\u001a\u0090\u0001\u00108\u001a\u00020\u00012\u0006\u0010$\u001a\u00020%2\u0006\u00109\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00103\u001a\u00020+2\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\f2\u0006\u0010<\u001a\u00020\f2\u0013\u0010=\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015¢\u0006\u0002\b\u00052\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0006H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b>\u0010?\u001a!\u0010@\u001a\u00020\n2\b\b\u0002\u0010A\u001a\u00020%2\b\b\u0002\u0010B\u001a\u00020\u001aH\u0007¢\u0006\u0002\u0010C\u001a7\u0010D\u001a\u00020%2\b\b\u0002\u0010E\u001a\u00020#2\u0014\b\u0002\u0010F\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00170\u00032\b\b\u0002\u0010G\u001a\u00020\u0017H\u0007¢\u0006\u0002\u0010H\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006I"}, d2 = {"BottomSheetScaffold", "", "sheetContent", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material3/BottomSheetScaffoldState;", "sheetPeekHeight", "Landroidx/compose/ui/unit/Dp;", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetContainerColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "sheetTonalElevation", "sheetShadowElevation", "sheetDragHandle", "Lkotlin/Function0;", "sheetSwipeEnabled", "", "topBar", "snackbarHost", "Landroidx/compose/material3/SnackbarHostState;", "containerColor", "contentColor", "content", "Landroidx/compose/foundation/layout/PaddingValues;", "BottomSheetScaffold-6cEcpDs", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/BottomSheetScaffoldState;FLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;JJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "BottomSheetScaffoldAnchorChangeHandler", "Landroidx/compose/material3/AnchorChangeHandler;", "Landroidx/compose/material3/SheetValue;", "state", "Landroidx/compose/material3/SheetState;", "animateTo", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "target", "", "velocity", "snapTo", "BottomSheetScaffoldLayout", "body", "innerPadding", "bottomSheet", "", "layoutHeight", "sheetOffset", "sheetState", "BottomSheetScaffoldLayout-PxNyym8", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;FLkotlin/jvm/functions/Function0;Landroidx/compose/material3/SheetState;JJLandroidx/compose/runtime/Composer;I)V", "StandardBottomSheet", "peekHeight", "shape", "tonalElevation", "shadowElevation", "dragHandle", "StandardBottomSheet-8oydGBM", "(Landroidx/compose/material3/SheetState;FZFLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "rememberBottomSheetScaffoldState", "bottomSheetState", "snackbarHostState", "(Landroidx/compose/material3/SheetState;Landroidx/compose/material3/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/BottomSheetScaffoldState;", "rememberStandardBottomSheetState", "initialValue", "confirmValueChange", "skipHiddenState", "(Landroidx/compose/material3/SheetValue;Lkotlin/jvm/functions/Function1;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BottomSheetScaffoldKt {
    /* JADX WARN: Code duplicated, block: B:113:0x016c  */
    /* JADX WARN: Code duplicated, block: B:114:0x0171  */
    /* JADX WARN: Code duplicated, block: B:116:0x0175  */
    /* JADX WARN: Code duplicated, block: B:118:0x017d  */
    /* JADX WARN: Code duplicated, block: B:119:0x0180  */
    /* JADX WARN: Code duplicated, block: B:121:0x0185  */
    /* JADX WARN: Code duplicated, block: B:124:0x018b  */
    /* JADX WARN: Code duplicated, block: B:125:0x0190  */
    /* JADX WARN: Code duplicated, block: B:127:0x0194  */
    /* JADX WARN: Code duplicated, block: B:129:0x019c  */
    /* JADX WARN: Code duplicated, block: B:130:0x019f  */
    /* JADX WARN: Code duplicated, block: B:132:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:135:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:136:0x01af  */
    /* JADX WARN: Code duplicated, block: B:138:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:141:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:143:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:146:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:148:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:151:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:154:0x01df  */
    /* JADX WARN: Code duplicated, block: B:157:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:165:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:168:0x0207  */
    /* JADX WARN: Code duplicated, block: B:170:0x020c  */
    /* JADX WARN: Code duplicated, block: B:172:0x0210  */
    /* JADX WARN: Code duplicated, block: B:174:0x0216  */
    /* JADX WARN: Code duplicated, block: B:175:0x0219  */
    /* JADX WARN: Code duplicated, block: B:178:0x0226  */
    /* JADX WARN: Code duplicated, block: B:184:0x025b  */
    /* JADX WARN: Code duplicated, block: B:186:0x0265  */
    /* JADX WARN: Code duplicated, block: B:208:0x02d7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:209:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:210:0x02de  */
    /* JADX WARN: Code duplicated, block: B:213:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:214:0x02f1  */
    /* JADX WARN: Code duplicated, block: B:216:0x02f7  */
    /* JADX WARN: Code duplicated, block: B:217:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:220:0x0305  */
    /* JADX WARN: Code duplicated, block: B:221:0x0311  */
    /* JADX WARN: Code duplicated, block: B:224:0x0317  */
    /* JADX WARN: Code duplicated, block: B:225:0x0326  */
    /* JADX WARN: Code duplicated, block: B:228:0x032e  */
    /* JADX WARN: Code duplicated, block: B:229:0x033b  */
    /* JADX WARN: Code duplicated, block: B:231:0x033f  */
    /* JADX WARN: Code duplicated, block: B:232:0x0346  */
    /* JADX WARN: Code duplicated, block: B:234:0x034a  */
    /* JADX WARN: Code duplicated, block: B:235:0x0351  */
    /* JADX WARN: Code duplicated, block: B:237:0x0355  */
    /* JADX WARN: Code duplicated, block: B:238:0x035c  */
    /* JADX WARN: Code duplicated, block: B:240:0x0360  */
    /* JADX WARN: Code duplicated, block: B:241:0x0362  */
    /* JADX WARN: Code duplicated, block: B:243:0x0366  */
    /* JADX WARN: Code duplicated, block: B:244:0x0368  */
    /* JADX WARN: Code duplicated, block: B:246:0x036c  */
    /* JADX WARN: Code duplicated, block: B:247:0x0373  */
    /* JADX WARN: Code duplicated, block: B:250:0x037b  */
    /* JADX WARN: Code duplicated, block: B:251:0x038b  */
    /* JADX WARN: Code duplicated, block: B:254:0x0395  */
    /* JADX WARN: Code duplicated, block: B:255:0x03b3  */
    /* JADX WARN: Code duplicated, block: B:258:0x03cd  */
    /* JADX WARN: Code duplicated, block: B:259:0x03d8  */
    /* JADX WARN: Code duplicated, block: B:262:0x0443  */
    /* JADX WARN: Code duplicated, block: B:266:0x0454 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:270:0x04b3  */
    /* JADX WARN: Code duplicated, block: B:274:0x04da  */
    /* JADX WARN: Code duplicated, block: B:275:0x04dd  */
    /* JADX INFO: renamed from: BottomSheetScaffold-6cEcpDs, reason: not valid java name */
    public static final void m1327BottomSheetScaffold6cEcpDs(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> sheetContent, Modifier modifier, BottomSheetScaffoldState scaffoldState, float sheetPeekHeight, Shape sheetShape, long sheetContainerColor, long sheetContentColor, float sheetTonalElevation, float sheetShadowElevation, Function2<? super Composer, ? super Integer, Unit> function2, boolean sheetSwipeEnabled, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function4, long containerColor, long contentColor, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int $changed1, final int i) {
        int $dirty;
        int i2;
        int $dirty2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        Modifier.Companion modifier2;
        BottomSheetScaffoldState scaffoldState2;
        float sheetPeekHeight2;
        Shape sheetShape2;
        long sheetContainerColor2;
        long sheetContentColor2;
        float sheetTonalElevation2;
        float sheetShadowElevation2;
        Function2<? super Composer, ? super Integer, Unit> function2M1444getLambda1$material3_release;
        boolean sheetSwipeEnabled2;
        Function2<? super Composer, ? super Integer, Unit> function5;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function3M1445getLambda2$material3_release;
        float sheetTonalElevation3;
        long containerColor2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        float sheetShadowElevation3;
        long contentColor2;
        long containerColor3;
        final int $dirty1;
        int $dirty3;
        Modifier modifier3;
        final BottomSheetScaffoldState scaffoldState3;
        float sheetTonalElevation4;
        boolean sheetSwipeEnabled3;
        Function2<? super Composer, ? super Integer, Unit> function7;
        boolean invalid$iv$iv;
        Object value$iv$iv;
        float sheetTonalElevation5;
        Function2<? super Composer, ? super Integer, Unit> function8;
        float sheetShadowElevation4;
        Function2<? super Composer, ? super Integer, Unit> function9;
        boolean sheetSwipeEnabled4;
        Modifier modifier4;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function10;
        float sheetPeekHeight3;
        Shape sheetShape3;
        long sheetContainerColor3;
        long sheetContentColor3;
        long containerColor4;
        long contentColor3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int $dirty4;
        Intrinsics.checkNotNullParameter(sheetContent, "sheetContent");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(144898711);
        ComposerKt.sourceInformation($composer2, "C(BottomSheetScaffold)P(6,3,4,9:c#ui.unit.Dp,11,5:c#ui.graphics.Color,7:c#ui.graphics.Color,13:c#ui.unit.Dp,10:c#ui.unit.Dp,8,12,15,14,0:c#ui.graphics.Color,2:c#ui.graphics.Color)99@5165L34,101@5308L13,102@5376L14,103@5423L36,110@5880L11,111@5927L31,122@6281L50,114@6017L1096:BottomSheetScaffold.kt#uh7d8r");
        int $dirty5 = $changed;
        int $dirty6 = $changed1;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty5 |= $composer2.changedInstance(sheetContent) ? 4 : 2;
        }
        int i10 = i & 2;
        if (i10 != 0) {
            $dirty5 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty5 |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty5 |= ((i & 4) == 0 && $composer2.changed(scaffoldState)) ? 256 : 128;
        }
        int i11 = i & 8;
        int i12 = 2048;
        if (i11 != 0) {
            $dirty5 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty5 |= $composer2.changed(sheetPeekHeight) ? 2048 : 1024;
        }
        if (($changed & 57344) == 0) {
            $dirty5 |= ((i & 16) == 0 && $composer2.changed(sheetShape)) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            $dirty5 |= ((i & 32) == 0 && $composer2.changed(sheetContainerColor)) ? 131072 : 65536;
        }
        if (($changed & 3670016) == 0) {
            if ((i & 64) == 0) {
                $dirty4 = $dirty5;
                int i13 = $composer2.changed(sheetContentColor) ? 1048576 : 524288;
                $dirty = $dirty4 | i13;
            } else {
                $dirty4 = $dirty5;
            }
            $dirty = $dirty4 | i13;
        } else {
            $dirty = $dirty5;
        }
        int i14 = i & 128;
        if (i14 != 0) {
            $dirty |= 12582912;
        } else if ((29360128 & $changed) == 0) {
            $dirty |= $composer2.changed(sheetTonalElevation) ? 8388608 : 4194304;
        }
        int i15 = i & 256;
        if (i15 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty |= $composer2.changed(sheetShadowElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i16 = i & 512;
        if (i16 == 0) {
            if (($changed & 1879048192) == 0) {
                i2 = $composer2.changedInstance(function2) ? 536870912 : 268435456;
            }
            $dirty2 = $dirty;
            i3 = i & 1024;
            if (i3 != 0) {
                $dirty6 |= 6;
            } else if (($changed1 & 14) != 0) {
                if ($composer2.changed(sheetSwipeEnabled)) {
                    i4 = 4;
                } else {
                    i4 = 2;
                }
                $dirty6 |= i4;
            }
            i5 = i & 2048;
            if (i5 != 0) {
                $dirty6 |= 48;
            } else if (($changed1 & 112) != 0) {
                if ($composer2.changedInstance(function3)) {
                    i6 = 32;
                } else {
                    i6 = 16;
                }
                $dirty6 |= i6;
            }
            i7 = i & 4096;
            if (i7 != 0) {
                $dirty6 |= 384;
            } else if (($changed1 & 896) != 0) {
                $dirty6 |= $composer2.changedInstance(function4) ? 256 : 128;
            }
            if (($changed1 & 7168) == 0) {
                if ((i & 8192) == 0) {
                    i8 = i7;
                    if (!$composer2.changed(containerColor)) {
                    }
                    $dirty6 |= i12;
                } else {
                    i8 = i7;
                }
                i12 = 1024;
                $dirty6 |= i12;
            } else {
                i8 = i7;
            }
            if (($changed1 & 57344) != 0) {
                $dirty6 |= ((i & 16384) == 0 || !$composer2.changed(contentColor)) ? 8192 : 16384;
            }
            if ((i & 32768) != 0) {
                if (($changed1 & 458752) == 0) {
                    if ($composer2.changedInstance(content)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                }
                if (($dirty2 & 1533916891) != 306783378 && (374491 & $dirty6) == 74898 && $composer2.getSkipping()) {
                    $composer2.skipToGroupEnd();
                    modifier4 = modifier;
                    scaffoldState3 = scaffoldState;
                    sheetPeekHeight3 = sheetPeekHeight;
                    sheetShape3 = sheetShape;
                    sheetContainerColor3 = sheetContainerColor;
                    sheetContentColor3 = sheetContentColor;
                    sheetTonalElevation5 = sheetTonalElevation;
                    sheetShadowElevation4 = sheetShadowElevation;
                    function9 = function2;
                    sheetSwipeEnabled4 = sheetSwipeEnabled;
                    function8 = function3;
                    function10 = function4;
                    containerColor4 = containerColor;
                    contentColor3 = contentColor;
                } else {
                    $composer2.startDefaults();
                    if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                        if (i10 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        } else {
                            modifier2 = modifier;
                        }
                        if ((i & 4) != 0) {
                            scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                            $dirty2 &= -897;
                        } else {
                            scaffoldState2 = scaffoldState;
                        }
                        if (i11 != 0) {
                            sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                        } else {
                            sheetPeekHeight2 = sheetPeekHeight;
                        }
                        if ((i & 16) != 0) {
                            sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                            $dirty2 &= -57345;
                        } else {
                            sheetShape2 = sheetShape;
                        }
                        if ((i & 32) != 0) {
                            $dirty2 &= -458753;
                            sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                        } else {
                            sheetContainerColor2 = sheetContainerColor;
                        }
                        if ((i & 64) != 0) {
                            sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                            $dirty2 &= -3670017;
                        } else {
                            sheetContentColor2 = sheetContentColor;
                        }
                        if (i14 != 0) {
                            sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                        } else {
                            sheetTonalElevation2 = sheetTonalElevation;
                        }
                        if (i15 != 0) {
                            sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                        } else {
                            sheetShadowElevation2 = sheetShadowElevation;
                        }
                        if (i16 != 0) {
                            function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                        } else {
                            function2M1444getLambda1$material3_release = function2;
                        }
                        if (i3 != 0) {
                            sheetSwipeEnabled2 = true;
                        } else {
                            sheetSwipeEnabled2 = sheetSwipeEnabled;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        } else {
                            function5 = function3;
                        }
                        if (i8 != 0) {
                            function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                        } else {
                            function3M1445getLambda2$material3_release = function4;
                        }
                        sheetTonalElevation3 = sheetTonalElevation2;
                        if ((i & 8192) != 0) {
                            containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                            $dirty6 &= -7169;
                        } else {
                            containerColor2 = containerColor;
                        }
                        function6 = function2M1444getLambda1$material3_release;
                        if ((i & 16384) != 0) {
                            sheetShadowElevation3 = sheetShadowElevation2;
                            $dirty1 = $dirty6 & (-57345);
                            $dirty3 = $dirty2;
                            contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                            sheetTonalElevation4 = sheetTonalElevation3;
                            containerColor3 = containerColor2;
                            sheetSwipeEnabled3 = sheetSwipeEnabled2;
                            modifier3 = modifier2;
                            scaffoldState3 = scaffoldState2;
                            function7 = function6;
                        } else {
                            sheetShadowElevation3 = sheetShadowElevation2;
                            contentColor2 = contentColor;
                            containerColor3 = containerColor2;
                            $dirty1 = $dirty6;
                            $dirty3 = $dirty2;
                            modifier3 = modifier2;
                            scaffoldState3 = scaffoldState2;
                            sheetTonalElevation4 = sheetTonalElevation3;
                            sheetSwipeEnabled3 = sheetSwipeEnabled2;
                            function7 = function6;
                        }
                    } else {
                        $composer2.skipToGroupEnd();
                        if ((i & 4) != 0) {
                            $dirty2 &= -897;
                        }
                        if ((i & 16) != 0) {
                            $dirty2 &= -57345;
                        }
                        if ((i & 32) != 0) {
                            $dirty2 &= -458753;
                        }
                        if ((i & 64) != 0) {
                            $dirty2 &= -3670017;
                        }
                        if ((i & 8192) != 0) {
                            $dirty6 &= -7169;
                        }
                        if ((i & 16384) != 0) {
                            int i17 = $dirty6 & (-57345);
                            scaffoldState3 = scaffoldState;
                            sheetPeekHeight2 = sheetPeekHeight;
                            sheetShape2 = sheetShape;
                            sheetContainerColor2 = sheetContainerColor;
                            sheetContentColor2 = sheetContentColor;
                            sheetTonalElevation4 = sheetTonalElevation;
                            sheetShadowElevation3 = sheetShadowElevation;
                            function7 = function2;
                            function5 = function3;
                            function3M1445getLambda2$material3_release = function4;
                            containerColor3 = containerColor;
                            contentColor2 = contentColor;
                            $dirty1 = i17;
                            $dirty3 = $dirty2;
                            modifier3 = modifier;
                            sheetSwipeEnabled3 = sheetSwipeEnabled;
                        } else {
                            modifier3 = modifier;
                            scaffoldState3 = scaffoldState;
                            sheetPeekHeight2 = sheetPeekHeight;
                            sheetShape2 = sheetShape;
                            sheetContainerColor2 = sheetContainerColor;
                            sheetContentColor2 = sheetContentColor;
                            sheetShadowElevation3 = sheetShadowElevation;
                            function7 = function2;
                            function5 = function3;
                            function3M1445getLambda2$material3_release = function4;
                            containerColor3 = containerColor;
                            contentColor2 = contentColor;
                            $dirty1 = $dirty6;
                            $dirty3 = $dirty2;
                            sheetTonalElevation4 = sheetTonalElevation;
                            sheetSwipeEnabled3 = sheetSwipeEnabled;
                        }
                    }
                    $composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(144898711, $dirty3, $dirty1, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:96)");
                    }
                    SheetState bottomSheetState = scaffoldState3.getBottomSheetState();
                    final BottomSheetScaffoldState bottomSheetScaffoldState = scaffoldState3;
                    final float f = sheetPeekHeight2;
                    final boolean z = sheetSwipeEnabled3;
                    final Shape shape = sheetShape2;
                    final long j = sheetContainerColor2;
                    final long j2 = sheetContentColor2;
                    final float f2 = sheetTonalElevation4;
                    final float f3 = sheetShadowElevation3;
                    final Function2<? super Composer, ? super Integer, Unit> function11 = function7;
                    final int i18 = $dirty3;
                    final int i19 = $dirty1;
                    Function3<Integer, Composer, Integer, Unit> function12 = new Function3<Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, Composer composer, Integer num2) {
                            invoke(num.intValue(), composer, num2.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int layoutHeight, Composer $composer3, int $changed2) {
                            ComposerKt.sourceInformation($composer3, "C127@6516L581:BottomSheetScaffold.kt#uh7d8r");
                            int $dirty7 = $changed2;
                            if (($changed2 & 14) == 0) {
                                $dirty7 |= $composer3.changed(layoutHeight) ? 4 : 2;
                            }
                            if (($dirty7 & 91) != 18 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(106433656, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:126)");
                                }
                                Shape shape2 = shape;
                                long j3 = j;
                                long j4 = j2;
                                float f4 = f2;
                                float f5 = f3;
                                Function2<Composer, Integer, Unit> function13 = function11;
                                Function3<ColumnScope, Composer, Integer, Unit> function14 = sheetContent;
                                int i20 = i18;
                                BottomSheetScaffoldKt.m1329StandardBottomSheet8oydGBM(bottomSheetScaffoldState.getBottomSheetState(), f, z, layoutHeight, shape2, j3, j4, f4, f5, function13, function14, $composer3, ((i20 >> 6) & 112) | ((i19 << 6) & 896) | (i20 & 57344) | (i20 & 458752) | (i20 & 3670016) | (i20 & 29360128) | (i20 & 234881024) | (i20 & 1879048192), i20 & 14);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    };
                    float sheetTonalElevation6 = sheetTonalElevation4;
                    float sheetShadowElevation5 = sheetShadowElevation3;
                    ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer2, 106433656, true, function12);
                    Function2<? super Composer, ? super Integer, Unit> function13 = function7;
                    ComposableLambda composableLambda2 = ComposableLambdaKt.composableLambda($composer2, -1629779374, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$2
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
                            ComposerKt.sourceInformation($composer3, "C119@6159L45:BottomSheetScaffold.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1629779374, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:118)");
                            }
                            function3M1445getLambda2$material3_release.invoke(scaffoldState3.getSnackbarHostState(), $composer3, Integer.valueOf(($dirty1 >> 3) & 112));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    int i20 = ($dirty3 >> 6) & 14;
                    $composer2.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer2.changed(scaffoldState3);
                    boolean sheetSwipeEnabled5 = sheetSwipeEnabled3;
                    Object it$iv$iv = $composer2.rememberedValue();
                    if (!invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$3$1
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(scaffoldState3.getBottomSheetState().requireOffset());
                            }
                        };
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    m1328BottomSheetScaffoldLayoutPxNyym8(modifier3, function5, content, composableLambda, composableLambda2, sheetPeekHeight2, (Function0) value$iv$iv, bottomSheetState, containerColor3, contentColor2, $composer2, (($dirty3 >> 3) & 14) | 27648 | ($dirty1 & 112) | (($dirty1 >> 9) & 896) | (($dirty3 << 6) & 458752) | (($dirty1 << 15) & 234881024) | (1879048192 & ($dirty1 << 15)));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    sheetTonalElevation5 = sheetTonalElevation6;
                    function8 = function5;
                    sheetShadowElevation4 = sheetShadowElevation5;
                    function9 = function13;
                    sheetSwipeEnabled4 = sheetSwipeEnabled5;
                    modifier4 = modifier3;
                    function10 = function3M1445getLambda2$material3_release;
                    sheetPeekHeight3 = sheetPeekHeight2;
                    sheetShape3 = sheetShape2;
                    sheetContainerColor3 = sheetContainerColor2;
                    sheetContentColor3 = sheetContentColor2;
                    containerColor4 = containerColor3;
                    contentColor3 = contentColor2;
                }
                scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup == null) {
                    return;
                }
                final Modifier modifier5 = modifier4;
                final BottomSheetScaffoldState bottomSheetScaffoldState2 = scaffoldState3;
                final float f4 = sheetPeekHeight3;
                final Shape shape2 = sheetShape3;
                final long j3 = sheetContainerColor3;
                final long j4 = sheetContentColor3;
                final float f5 = sheetTonalElevation5;
                final float f6 = sheetShadowElevation4;
                final Function2<? super Composer, ? super Integer, Unit> function14 = function9;
                final boolean z2 = sheetSwipeEnabled4;
                final Function2<? super Composer, ? super Integer, Unit> function15 = function8;
                final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function16 = function10;
                final long j5 = containerColor4;
                final long j6 = contentColor3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$4
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

                    public final void invoke(Composer composer, int i21) {
                        BottomSheetScaffoldKt.m1327BottomSheetScaffold6cEcpDs(sheetContent, modifier5, bottomSheetScaffoldState2, f4, shape2, j3, j4, f5, f6, function14, z2, function15, function16, j5, j6, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                    }
                });
            }
            i9 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            $dirty6 |= i9;
            if (($dirty2 & 1533916891) != 306783378) {
                $composer2.startDefaults();
                if (($changed & 1) != 0) {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                        $dirty2 &= -897;
                    } else {
                        scaffoldState2 = scaffoldState;
                    }
                    if (i11 != 0) {
                        sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                    } else {
                        sheetPeekHeight2 = sheetPeekHeight;
                    }
                    if ((i & 16) != 0) {
                        sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                        $dirty2 &= -57345;
                    } else {
                        sheetShape2 = sheetShape;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                        sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                    } else {
                        sheetContainerColor2 = sheetContainerColor;
                    }
                    if ((i & 64) != 0) {
                        sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                        $dirty2 &= -3670017;
                    } else {
                        sheetContentColor2 = sheetContentColor;
                    }
                    if (i14 != 0) {
                        sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetTonalElevation2 = sheetTonalElevation;
                    }
                    if (i15 != 0) {
                        sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetShadowElevation2 = sheetShadowElevation;
                    }
                    if (i16 != 0) {
                        function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                    } else {
                        function2M1444getLambda1$material3_release = function2;
                    }
                    if (i3 != 0) {
                        sheetSwipeEnabled2 = true;
                    } else {
                        sheetSwipeEnabled2 = sheetSwipeEnabled;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    } else {
                        function5 = function3;
                    }
                    if (i8 != 0) {
                        function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                    } else {
                        function3M1445getLambda2$material3_release = function4;
                    }
                    sheetTonalElevation3 = sheetTonalElevation2;
                    if ((i & 8192) != 0) {
                        containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                        $dirty6 &= -7169;
                    } else {
                        containerColor2 = containerColor;
                    }
                    function6 = function2M1444getLambda1$material3_release;
                    if ((i & 16384) != 0) {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        $dirty1 = $dirty6 & (-57345);
                        $dirty3 = $dirty2;
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                        sheetTonalElevation4 = sheetTonalElevation3;
                        containerColor3 = containerColor2;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        function7 = function6;
                    } else {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        contentColor2 = contentColor;
                        containerColor3 = containerColor2;
                        $dirty1 = $dirty6;
                        $dirty3 = $dirty2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        sheetTonalElevation4 = sheetTonalElevation3;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        function7 = function6;
                    }
                } else {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                        $dirty2 &= -897;
                    } else {
                        scaffoldState2 = scaffoldState;
                    }
                    if (i11 != 0) {
                        sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                    } else {
                        sheetPeekHeight2 = sheetPeekHeight;
                    }
                    if ((i & 16) != 0) {
                        sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                        $dirty2 &= -57345;
                    } else {
                        sheetShape2 = sheetShape;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                        sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                    } else {
                        sheetContainerColor2 = sheetContainerColor;
                    }
                    if ((i & 64) != 0) {
                        sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                        $dirty2 &= -3670017;
                    } else {
                        sheetContentColor2 = sheetContentColor;
                    }
                    if (i14 != 0) {
                        sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetTonalElevation2 = sheetTonalElevation;
                    }
                    if (i15 != 0) {
                        sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetShadowElevation2 = sheetShadowElevation;
                    }
                    if (i16 != 0) {
                        function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                    } else {
                        function2M1444getLambda1$material3_release = function2;
                    }
                    if (i3 != 0) {
                        sheetSwipeEnabled2 = true;
                    } else {
                        sheetSwipeEnabled2 = sheetSwipeEnabled;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    } else {
                        function5 = function3;
                    }
                    if (i8 != 0) {
                        function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                    } else {
                        function3M1445getLambda2$material3_release = function4;
                    }
                    sheetTonalElevation3 = sheetTonalElevation2;
                    if ((i & 8192) != 0) {
                        containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                        $dirty6 &= -7169;
                    } else {
                        containerColor2 = containerColor;
                    }
                    function6 = function2M1444getLambda1$material3_release;
                    if ((i & 16384) != 0) {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        $dirty1 = $dirty6 & (-57345);
                        $dirty3 = $dirty2;
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                        sheetTonalElevation4 = sheetTonalElevation3;
                        containerColor3 = containerColor2;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        function7 = function6;
                    } else {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        contentColor2 = contentColor;
                        containerColor3 = containerColor2;
                        $dirty1 = $dirty6;
                        $dirty3 = $dirty2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        sheetTonalElevation4 = sheetTonalElevation3;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        function7 = function6;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(144898711, $dirty3, $dirty1, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:96)");
                }
                SheetState bottomSheetState2 = scaffoldState3.getBottomSheetState();
                final BottomSheetScaffoldState bottomSheetScaffoldState3 = scaffoldState3;
                final float f7 = sheetPeekHeight2;
                final boolean z3 = sheetSwipeEnabled3;
                final Shape shape3 = sheetShape2;
                final long j7 = sheetContainerColor2;
                final long j8 = sheetContentColor2;
                final float f8 = sheetTonalElevation4;
                final float f9 = sheetShadowElevation3;
                final Function2<? super Composer, ? super Integer, Unit> function17 = function7;
                final int i110 = $dirty3;
                final int i111 = $dirty1;
                Function3<Integer, Composer, Integer, Unit> function18 = new Function3<Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Composer composer, Integer num2) {
                        invoke(num.intValue(), composer, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int layoutHeight, Composer $composer3, int $changed2) {
                        ComposerKt.sourceInformation($composer3, "C127@6516L581:BottomSheetScaffold.kt#uh7d8r");
                        int $dirty7 = $changed2;
                        if (($changed2 & 14) == 0) {
                            $dirty7 |= $composer3.changed(layoutHeight) ? 4 : 2;
                        }
                        if (($dirty7 & 91) != 18 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(106433656, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:126)");
                            }
                            Shape shape4 = shape3;
                            long j9 = j7;
                            long j10 = j8;
                            float f10 = f8;
                            float f11 = f9;
                            Function2<Composer, Integer, Unit> function19 = function17;
                            Function3<ColumnScope, Composer, Integer, Unit> function110 = sheetContent;
                            int i21 = i110;
                            BottomSheetScaffoldKt.m1329StandardBottomSheet8oydGBM(bottomSheetScaffoldState3.getBottomSheetState(), f7, z3, layoutHeight, shape4, j9, j10, f10, f11, function19, function110, $composer3, ((i21 >> 6) & 112) | ((i111 << 6) & 896) | (i21 & 57344) | (i21 & 458752) | (i21 & 3670016) | (i21 & 29360128) | (i21 & 234881024) | (i21 & 1879048192), i21 & 14);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                };
                float sheetTonalElevation7 = sheetTonalElevation4;
                float sheetShadowElevation6 = sheetShadowElevation3;
                ComposableLambda composableLambda3 = ComposableLambdaKt.composableLambda($composer2, 106433656, true, function18);
                Function2<? super Composer, ? super Integer, Unit> function19 = function7;
                ComposableLambda composableLambda4 = ComposableLambdaKt.composableLambda($composer2, -1629779374, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$2
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
                        ComposerKt.sourceInformation($composer3, "C119@6159L45:BottomSheetScaffold.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1629779374, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:118)");
                        }
                        function3M1445getLambda2$material3_release.invoke(scaffoldState3.getSnackbarHostState(), $composer3, Integer.valueOf(($dirty1 >> 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                });
                int i21 = ($dirty3 >> 6) & 14;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scaffoldState3);
                boolean sheetSwipeEnabled6 = sheetSwipeEnabled3;
                Object it$iv$iv2 = $composer2.rememberedValue();
                if (invalid$iv$iv) {
                }
                value$iv$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$3$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(scaffoldState3.getBottomSheetState().requireOffset());
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                m1328BottomSheetScaffoldLayoutPxNyym8(modifier3, function5, content, composableLambda3, composableLambda4, sheetPeekHeight2, (Function0) value$iv$iv, bottomSheetState2, containerColor3, contentColor2, $composer2, (($dirty3 >> 3) & 14) | 27648 | ($dirty1 & 112) | (($dirty1 >> 9) & 896) | (($dirty3 << 6) & 458752) | (($dirty1 << 15) & 234881024) | (1879048192 & ($dirty1 << 15)));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                sheetTonalElevation5 = sheetTonalElevation7;
                function8 = function5;
                sheetShadowElevation4 = sheetShadowElevation6;
                function9 = function19;
                sheetSwipeEnabled4 = sheetSwipeEnabled6;
                modifier4 = modifier3;
                function10 = function3M1445getLambda2$material3_release;
                sheetPeekHeight3 = sheetPeekHeight2;
                sheetShape3 = sheetShape2;
                sheetContainerColor3 = sheetContainerColor2;
                sheetContentColor3 = sheetContentColor2;
                containerColor4 = containerColor3;
                contentColor3 = contentColor2;
            } else {
                $composer2.startDefaults();
                if (($changed & 1) != 0) {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                        $dirty2 &= -897;
                    } else {
                        scaffoldState2 = scaffoldState;
                    }
                    if (i11 != 0) {
                        sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                    } else {
                        sheetPeekHeight2 = sheetPeekHeight;
                    }
                    if ((i & 16) != 0) {
                        sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                        $dirty2 &= -57345;
                    } else {
                        sheetShape2 = sheetShape;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                        sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                    } else {
                        sheetContainerColor2 = sheetContainerColor;
                    }
                    if ((i & 64) != 0) {
                        sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                        $dirty2 &= -3670017;
                    } else {
                        sheetContentColor2 = sheetContentColor;
                    }
                    if (i14 != 0) {
                        sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetTonalElevation2 = sheetTonalElevation;
                    }
                    if (i15 != 0) {
                        sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetShadowElevation2 = sheetShadowElevation;
                    }
                    if (i16 != 0) {
                        function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                    } else {
                        function2M1444getLambda1$material3_release = function2;
                    }
                    if (i3 != 0) {
                        sheetSwipeEnabled2 = true;
                    } else {
                        sheetSwipeEnabled2 = sheetSwipeEnabled;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    } else {
                        function5 = function3;
                    }
                    if (i8 != 0) {
                        function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                    } else {
                        function3M1445getLambda2$material3_release = function4;
                    }
                    sheetTonalElevation3 = sheetTonalElevation2;
                    if ((i & 8192) != 0) {
                        containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                        $dirty6 &= -7169;
                    } else {
                        containerColor2 = containerColor;
                    }
                    function6 = function2M1444getLambda1$material3_release;
                    if ((i & 16384) != 0) {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        $dirty1 = $dirty6 & (-57345);
                        $dirty3 = $dirty2;
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                        sheetTonalElevation4 = sheetTonalElevation3;
                        containerColor3 = containerColor2;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        function7 = function6;
                    } else {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        contentColor2 = contentColor;
                        containerColor3 = containerColor2;
                        $dirty1 = $dirty6;
                        $dirty3 = $dirty2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        sheetTonalElevation4 = sheetTonalElevation3;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        function7 = function6;
                    }
                } else {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                        $dirty2 &= -897;
                    } else {
                        scaffoldState2 = scaffoldState;
                    }
                    if (i11 != 0) {
                        sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                    } else {
                        sheetPeekHeight2 = sheetPeekHeight;
                    }
                    if ((i & 16) != 0) {
                        sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                        $dirty2 &= -57345;
                    } else {
                        sheetShape2 = sheetShape;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                        sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                    } else {
                        sheetContainerColor2 = sheetContainerColor;
                    }
                    if ((i & 64) != 0) {
                        sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                        $dirty2 &= -3670017;
                    } else {
                        sheetContentColor2 = sheetContentColor;
                    }
                    if (i14 != 0) {
                        sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetTonalElevation2 = sheetTonalElevation;
                    }
                    if (i15 != 0) {
                        sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetShadowElevation2 = sheetShadowElevation;
                    }
                    if (i16 != 0) {
                        function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                    } else {
                        function2M1444getLambda1$material3_release = function2;
                    }
                    if (i3 != 0) {
                        sheetSwipeEnabled2 = true;
                    } else {
                        sheetSwipeEnabled2 = sheetSwipeEnabled;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    } else {
                        function5 = function3;
                    }
                    if (i8 != 0) {
                        function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                    } else {
                        function3M1445getLambda2$material3_release = function4;
                    }
                    sheetTonalElevation3 = sheetTonalElevation2;
                    if ((i & 8192) != 0) {
                        containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                        $dirty6 &= -7169;
                    } else {
                        containerColor2 = containerColor;
                    }
                    function6 = function2M1444getLambda1$material3_release;
                    if ((i & 16384) != 0) {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        $dirty1 = $dirty6 & (-57345);
                        $dirty3 = $dirty2;
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                        sheetTonalElevation4 = sheetTonalElevation3;
                        containerColor3 = containerColor2;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        function7 = function6;
                    } else {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        contentColor2 = contentColor;
                        containerColor3 = containerColor2;
                        $dirty1 = $dirty6;
                        $dirty3 = $dirty2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        sheetTonalElevation4 = sheetTonalElevation3;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        function7 = function6;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(144898711, $dirty3, $dirty1, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:96)");
                }
                SheetState bottomSheetState3 = scaffoldState3.getBottomSheetState();
                final BottomSheetScaffoldState bottomSheetScaffoldState4 = scaffoldState3;
                final float f10 = sheetPeekHeight2;
                final boolean z4 = sheetSwipeEnabled3;
                final Shape shape4 = sheetShape2;
                final long j9 = sheetContainerColor2;
                final long j10 = sheetContentColor2;
                final float f11 = sheetTonalElevation4;
                final float f12 = sheetShadowElevation3;
                final Function2<? super Composer, ? super Integer, Unit> function110 = function7;
                final int i112 = $dirty3;
                final int i113 = $dirty1;
                Function3<Integer, Composer, Integer, Unit> function111 = new Function3<Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Composer composer, Integer num2) {
                        invoke(num.intValue(), composer, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int layoutHeight, Composer $composer3, int $changed2) {
                        ComposerKt.sourceInformation($composer3, "C127@6516L581:BottomSheetScaffold.kt#uh7d8r");
                        int $dirty7 = $changed2;
                        if (($changed2 & 14) == 0) {
                            $dirty7 |= $composer3.changed(layoutHeight) ? 4 : 2;
                        }
                        if (($dirty7 & 91) != 18 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(106433656, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:126)");
                            }
                            Shape shape5 = shape4;
                            long j11 = j9;
                            long j12 = j10;
                            float f13 = f11;
                            float f14 = f12;
                            Function2<Composer, Integer, Unit> function112 = function110;
                            Function3<ColumnScope, Composer, Integer, Unit> function113 = sheetContent;
                            int i22 = i112;
                            BottomSheetScaffoldKt.m1329StandardBottomSheet8oydGBM(bottomSheetScaffoldState4.getBottomSheetState(), f10, z4, layoutHeight, shape5, j11, j12, f13, f14, function112, function113, $composer3, ((i22 >> 6) & 112) | ((i113 << 6) & 896) | (i22 & 57344) | (i22 & 458752) | (i22 & 3670016) | (i22 & 29360128) | (i22 & 234881024) | (i22 & 1879048192), i22 & 14);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                };
                float sheetTonalElevation8 = sheetTonalElevation4;
                float sheetShadowElevation7 = sheetShadowElevation3;
                ComposableLambda composableLambda5 = ComposableLambdaKt.composableLambda($composer2, 106433656, true, function111);
                Function2<? super Composer, ? super Integer, Unit> function112 = function7;
                ComposableLambda composableLambda6 = ComposableLambdaKt.composableLambda($composer2, -1629779374, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$2
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
                        ComposerKt.sourceInformation($composer3, "C119@6159L45:BottomSheetScaffold.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1629779374, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:118)");
                        }
                        function3M1445getLambda2$material3_release.invoke(scaffoldState3.getSnackbarHostState(), $composer3, Integer.valueOf(($dirty1 >> 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                });
                int i22 = ($dirty3 >> 6) & 14;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scaffoldState3);
                boolean sheetSwipeEnabled7 = sheetSwipeEnabled3;
                Object it$iv$iv3 = $composer2.rememberedValue();
                if (invalid$iv$iv) {
                }
                value$iv$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$3$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(scaffoldState3.getBottomSheetState().requireOffset());
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                m1328BottomSheetScaffoldLayoutPxNyym8(modifier3, function5, content, composableLambda5, composableLambda6, sheetPeekHeight2, (Function0) value$iv$iv, bottomSheetState3, containerColor3, contentColor2, $composer2, (($dirty3 >> 3) & 14) | 27648 | ($dirty1 & 112) | (($dirty1 >> 9) & 896) | (($dirty3 << 6) & 458752) | (($dirty1 << 15) & 234881024) | (1879048192 & ($dirty1 << 15)));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                sheetTonalElevation5 = sheetTonalElevation8;
                function8 = function5;
                sheetShadowElevation4 = sheetShadowElevation7;
                function9 = function112;
                sheetSwipeEnabled4 = sheetSwipeEnabled7;
                modifier4 = modifier3;
                function10 = function3M1445getLambda2$material3_release;
                sheetPeekHeight3 = sheetPeekHeight2;
                sheetShape3 = sheetShape2;
                sheetContainerColor3 = sheetContainerColor2;
                sheetContentColor3 = sheetContentColor2;
                containerColor4 = containerColor3;
                contentColor3 = contentColor2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier6 = modifier4;
            final BottomSheetScaffoldState bottomSheetScaffoldState5 = scaffoldState3;
            final float f13 = sheetPeekHeight3;
            final Shape shape5 = sheetShape3;
            final long j11 = sheetContainerColor3;
            final long j12 = sheetContentColor3;
            final float f14 = sheetTonalElevation5;
            final float f15 = sheetShadowElevation4;
            final Function2<? super Composer, ? super Integer, Unit> function113 = function9;
            final boolean z5 = sheetSwipeEnabled4;
            final Function2<? super Composer, ? super Integer, Unit> function114 = function8;
            final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function115 = function10;
            final long j13 = containerColor4;
            final long j14 = contentColor3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$4
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

                public final void invoke(Composer composer, int i23) {
                    BottomSheetScaffoldKt.m1327BottomSheetScaffold6cEcpDs(sheetContent, modifier6, bottomSheetScaffoldState5, f13, shape5, j11, j12, f14, f15, function113, z5, function114, function115, j13, j14, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty |= i2;
        $dirty2 = $dirty;
        i3 = i & 1024;
        if (i3 != 0) {
            $dirty6 |= 6;
        } else if (($changed1 & 14) != 0) {
            if ($composer2.changed(sheetSwipeEnabled)) {
                i4 = 4;
            } else {
                i4 = 2;
            }
            $dirty6 |= i4;
        }
        i5 = i & 2048;
        if (i5 != 0) {
            $dirty6 |= 48;
        } else if (($changed1 & 112) != 0) {
            if ($composer2.changedInstance(function3)) {
                i6 = 32;
            } else {
                i6 = 16;
            }
            $dirty6 |= i6;
        }
        i7 = i & 4096;
        if (i7 != 0) {
            $dirty6 |= 384;
        } else if (($changed1 & 896) != 0) {
            $dirty6 |= $composer2.changedInstance(function4) ? 256 : 128;
        }
        if (($changed1 & 7168) == 0) {
            if ((i & 8192) == 0) {
                i8 = i7;
                if (!$composer2.changed(containerColor)) {
                }
                $dirty6 |= i12;
            } else {
                i8 = i7;
            }
            i12 = 1024;
            $dirty6 |= i12;
        } else {
            i8 = i7;
        }
        if (($changed1 & 57344) != 0) {
            $dirty6 |= ((i & 16384) == 0 || !$composer2.changed(contentColor)) ? 8192 : 16384;
        }
        if ((i & 32768) != 0) {
            if (($changed1 & 458752) == 0) {
                if ($composer2.changedInstance(content)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
            }
            if (($dirty2 & 1533916891) != 306783378) {
                $composer2.startDefaults();
                if (($changed & 1) != 0) {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                        $dirty2 &= -897;
                    } else {
                        scaffoldState2 = scaffoldState;
                    }
                    if (i11 != 0) {
                        sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                    } else {
                        sheetPeekHeight2 = sheetPeekHeight;
                    }
                    if ((i & 16) != 0) {
                        sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                        $dirty2 &= -57345;
                    } else {
                        sheetShape2 = sheetShape;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                        sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                    } else {
                        sheetContainerColor2 = sheetContainerColor;
                    }
                    if ((i & 64) != 0) {
                        sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                        $dirty2 &= -3670017;
                    } else {
                        sheetContentColor2 = sheetContentColor;
                    }
                    if (i14 != 0) {
                        sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetTonalElevation2 = sheetTonalElevation;
                    }
                    if (i15 != 0) {
                        sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetShadowElevation2 = sheetShadowElevation;
                    }
                    if (i16 != 0) {
                        function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                    } else {
                        function2M1444getLambda1$material3_release = function2;
                    }
                    if (i3 != 0) {
                        sheetSwipeEnabled2 = true;
                    } else {
                        sheetSwipeEnabled2 = sheetSwipeEnabled;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    } else {
                        function5 = function3;
                    }
                    if (i8 != 0) {
                        function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                    } else {
                        function3M1445getLambda2$material3_release = function4;
                    }
                    sheetTonalElevation3 = sheetTonalElevation2;
                    if ((i & 8192) != 0) {
                        containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                        $dirty6 &= -7169;
                    } else {
                        containerColor2 = containerColor;
                    }
                    function6 = function2M1444getLambda1$material3_release;
                    if ((i & 16384) != 0) {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        $dirty1 = $dirty6 & (-57345);
                        $dirty3 = $dirty2;
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                        sheetTonalElevation4 = sheetTonalElevation3;
                        containerColor3 = containerColor2;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        function7 = function6;
                    } else {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        contentColor2 = contentColor;
                        containerColor3 = containerColor2;
                        $dirty1 = $dirty6;
                        $dirty3 = $dirty2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        sheetTonalElevation4 = sheetTonalElevation3;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        function7 = function6;
                    }
                } else {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                        $dirty2 &= -897;
                    } else {
                        scaffoldState2 = scaffoldState;
                    }
                    if (i11 != 0) {
                        sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                    } else {
                        sheetPeekHeight2 = sheetPeekHeight;
                    }
                    if ((i & 16) != 0) {
                        sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                        $dirty2 &= -57345;
                    } else {
                        sheetShape2 = sheetShape;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                        sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                    } else {
                        sheetContainerColor2 = sheetContainerColor;
                    }
                    if ((i & 64) != 0) {
                        sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                        $dirty2 &= -3670017;
                    } else {
                        sheetContentColor2 = sheetContentColor;
                    }
                    if (i14 != 0) {
                        sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetTonalElevation2 = sheetTonalElevation;
                    }
                    if (i15 != 0) {
                        sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetShadowElevation2 = sheetShadowElevation;
                    }
                    if (i16 != 0) {
                        function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                    } else {
                        function2M1444getLambda1$material3_release = function2;
                    }
                    if (i3 != 0) {
                        sheetSwipeEnabled2 = true;
                    } else {
                        sheetSwipeEnabled2 = sheetSwipeEnabled;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    } else {
                        function5 = function3;
                    }
                    if (i8 != 0) {
                        function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                    } else {
                        function3M1445getLambda2$material3_release = function4;
                    }
                    sheetTonalElevation3 = sheetTonalElevation2;
                    if ((i & 8192) != 0) {
                        containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                        $dirty6 &= -7169;
                    } else {
                        containerColor2 = containerColor;
                    }
                    function6 = function2M1444getLambda1$material3_release;
                    if ((i & 16384) != 0) {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        $dirty1 = $dirty6 & (-57345);
                        $dirty3 = $dirty2;
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                        sheetTonalElevation4 = sheetTonalElevation3;
                        containerColor3 = containerColor2;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        function7 = function6;
                    } else {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        contentColor2 = contentColor;
                        containerColor3 = containerColor2;
                        $dirty1 = $dirty6;
                        $dirty3 = $dirty2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        sheetTonalElevation4 = sheetTonalElevation3;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        function7 = function6;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(144898711, $dirty3, $dirty1, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:96)");
                }
                SheetState bottomSheetState4 = scaffoldState3.getBottomSheetState();
                final BottomSheetScaffoldState bottomSheetScaffoldState6 = scaffoldState3;
                final float f16 = sheetPeekHeight2;
                final boolean z6 = sheetSwipeEnabled3;
                final Shape shape6 = sheetShape2;
                final long j15 = sheetContainerColor2;
                final long j16 = sheetContentColor2;
                final float f17 = sheetTonalElevation4;
                final float f18 = sheetShadowElevation3;
                final Function2<? super Composer, ? super Integer, Unit> function116 = function7;
                final int i114 = $dirty3;
                final int i115 = $dirty1;
                Function3<Integer, Composer, Integer, Unit> function117 = new Function3<Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Composer composer, Integer num2) {
                        invoke(num.intValue(), composer, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int layoutHeight, Composer $composer3, int $changed2) {
                        ComposerKt.sourceInformation($composer3, "C127@6516L581:BottomSheetScaffold.kt#uh7d8r");
                        int $dirty7 = $changed2;
                        if (($changed2 & 14) == 0) {
                            $dirty7 |= $composer3.changed(layoutHeight) ? 4 : 2;
                        }
                        if (($dirty7 & 91) != 18 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(106433656, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:126)");
                            }
                            Shape shape7 = shape6;
                            long j17 = j15;
                            long j18 = j16;
                            float f19 = f17;
                            float f110 = f18;
                            Function2<Composer, Integer, Unit> function118 = function116;
                            Function3<ColumnScope, Composer, Integer, Unit> function119 = sheetContent;
                            int i23 = i114;
                            BottomSheetScaffoldKt.m1329StandardBottomSheet8oydGBM(bottomSheetScaffoldState6.getBottomSheetState(), f16, z6, layoutHeight, shape7, j17, j18, f19, f110, function118, function119, $composer3, ((i23 >> 6) & 112) | ((i115 << 6) & 896) | (i23 & 57344) | (i23 & 458752) | (i23 & 3670016) | (i23 & 29360128) | (i23 & 234881024) | (i23 & 1879048192), i23 & 14);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                };
                float sheetTonalElevation9 = sheetTonalElevation4;
                float sheetShadowElevation8 = sheetShadowElevation3;
                ComposableLambda composableLambda7 = ComposableLambdaKt.composableLambda($composer2, 106433656, true, function117);
                Function2<? super Composer, ? super Integer, Unit> function118 = function7;
                ComposableLambda composableLambda8 = ComposableLambdaKt.composableLambda($composer2, -1629779374, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$2
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
                        ComposerKt.sourceInformation($composer3, "C119@6159L45:BottomSheetScaffold.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1629779374, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:118)");
                        }
                        function3M1445getLambda2$material3_release.invoke(scaffoldState3.getSnackbarHostState(), $composer3, Integer.valueOf(($dirty1 >> 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                });
                int i23 = ($dirty3 >> 6) & 14;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scaffoldState3);
                boolean sheetSwipeEnabled8 = sheetSwipeEnabled3;
                Object it$iv$iv4 = $composer2.rememberedValue();
                if (invalid$iv$iv) {
                }
                value$iv$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$3$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(scaffoldState3.getBottomSheetState().requireOffset());
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                m1328BottomSheetScaffoldLayoutPxNyym8(modifier3, function5, content, composableLambda7, composableLambda8, sheetPeekHeight2, (Function0) value$iv$iv, bottomSheetState4, containerColor3, contentColor2, $composer2, (($dirty3 >> 3) & 14) | 27648 | ($dirty1 & 112) | (($dirty1 >> 9) & 896) | (($dirty3 << 6) & 458752) | (($dirty1 << 15) & 234881024) | (1879048192 & ($dirty1 << 15)));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                sheetTonalElevation5 = sheetTonalElevation9;
                function8 = function5;
                sheetShadowElevation4 = sheetShadowElevation8;
                function9 = function118;
                sheetSwipeEnabled4 = sheetSwipeEnabled8;
                modifier4 = modifier3;
                function10 = function3M1445getLambda2$material3_release;
                sheetPeekHeight3 = sheetPeekHeight2;
                sheetShape3 = sheetShape2;
                sheetContainerColor3 = sheetContainerColor2;
                sheetContentColor3 = sheetContentColor2;
                containerColor4 = containerColor3;
                contentColor3 = contentColor2;
            } else {
                $composer2.startDefaults();
                if (($changed & 1) != 0) {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                        $dirty2 &= -897;
                    } else {
                        scaffoldState2 = scaffoldState;
                    }
                    if (i11 != 0) {
                        sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                    } else {
                        sheetPeekHeight2 = sheetPeekHeight;
                    }
                    if ((i & 16) != 0) {
                        sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                        $dirty2 &= -57345;
                    } else {
                        sheetShape2 = sheetShape;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                        sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                    } else {
                        sheetContainerColor2 = sheetContainerColor;
                    }
                    if ((i & 64) != 0) {
                        sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                        $dirty2 &= -3670017;
                    } else {
                        sheetContentColor2 = sheetContentColor;
                    }
                    if (i14 != 0) {
                        sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetTonalElevation2 = sheetTonalElevation;
                    }
                    if (i15 != 0) {
                        sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetShadowElevation2 = sheetShadowElevation;
                    }
                    if (i16 != 0) {
                        function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                    } else {
                        function2M1444getLambda1$material3_release = function2;
                    }
                    if (i3 != 0) {
                        sheetSwipeEnabled2 = true;
                    } else {
                        sheetSwipeEnabled2 = sheetSwipeEnabled;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    } else {
                        function5 = function3;
                    }
                    if (i8 != 0) {
                        function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                    } else {
                        function3M1445getLambda2$material3_release = function4;
                    }
                    sheetTonalElevation3 = sheetTonalElevation2;
                    if ((i & 8192) != 0) {
                        containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                        $dirty6 &= -7169;
                    } else {
                        containerColor2 = containerColor;
                    }
                    function6 = function2M1444getLambda1$material3_release;
                    if ((i & 16384) != 0) {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        $dirty1 = $dirty6 & (-57345);
                        $dirty3 = $dirty2;
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                        sheetTonalElevation4 = sheetTonalElevation3;
                        containerColor3 = containerColor2;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        function7 = function6;
                    } else {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        contentColor2 = contentColor;
                        containerColor3 = containerColor2;
                        $dirty1 = $dirty6;
                        $dirty3 = $dirty2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        sheetTonalElevation4 = sheetTonalElevation3;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        function7 = function6;
                    }
                } else {
                    if (i10 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                        $dirty2 &= -897;
                    } else {
                        scaffoldState2 = scaffoldState;
                    }
                    if (i11 != 0) {
                        sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                    } else {
                        sheetPeekHeight2 = sheetPeekHeight;
                    }
                    if ((i & 16) != 0) {
                        sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                        $dirty2 &= -57345;
                    } else {
                        sheetShape2 = sheetShape;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                        sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                    } else {
                        sheetContainerColor2 = sheetContainerColor;
                    }
                    if ((i & 64) != 0) {
                        sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                        $dirty2 &= -3670017;
                    } else {
                        sheetContentColor2 = sheetContentColor;
                    }
                    if (i14 != 0) {
                        sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetTonalElevation2 = sheetTonalElevation;
                    }
                    if (i15 != 0) {
                        sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                    } else {
                        sheetShadowElevation2 = sheetShadowElevation;
                    }
                    if (i16 != 0) {
                        function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                    } else {
                        function2M1444getLambda1$material3_release = function2;
                    }
                    if (i3 != 0) {
                        sheetSwipeEnabled2 = true;
                    } else {
                        sheetSwipeEnabled2 = sheetSwipeEnabled;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    } else {
                        function5 = function3;
                    }
                    if (i8 != 0) {
                        function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                    } else {
                        function3M1445getLambda2$material3_release = function4;
                    }
                    sheetTonalElevation3 = sheetTonalElevation2;
                    if ((i & 8192) != 0) {
                        containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                        $dirty6 &= -7169;
                    } else {
                        containerColor2 = containerColor;
                    }
                    function6 = function2M1444getLambda1$material3_release;
                    if ((i & 16384) != 0) {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        $dirty1 = $dirty6 & (-57345);
                        $dirty3 = $dirty2;
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                        sheetTonalElevation4 = sheetTonalElevation3;
                        containerColor3 = containerColor2;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        function7 = function6;
                    } else {
                        sheetShadowElevation3 = sheetShadowElevation2;
                        contentColor2 = contentColor;
                        containerColor3 = containerColor2;
                        $dirty1 = $dirty6;
                        $dirty3 = $dirty2;
                        modifier3 = modifier2;
                        scaffoldState3 = scaffoldState2;
                        sheetTonalElevation4 = sheetTonalElevation3;
                        sheetSwipeEnabled3 = sheetSwipeEnabled2;
                        function7 = function6;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(144898711, $dirty3, $dirty1, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:96)");
                }
                SheetState bottomSheetState5 = scaffoldState3.getBottomSheetState();
                final BottomSheetScaffoldState bottomSheetScaffoldState7 = scaffoldState3;
                final float f19 = sheetPeekHeight2;
                final boolean z7 = sheetSwipeEnabled3;
                final Shape shape7 = sheetShape2;
                final long j17 = sheetContainerColor2;
                final long j18 = sheetContentColor2;
                final float f110 = sheetTonalElevation4;
                final float f111 = sheetShadowElevation3;
                final Function2<? super Composer, ? super Integer, Unit> function119 = function7;
                final int i116 = $dirty3;
                final int i117 = $dirty1;
                Function3<Integer, Composer, Integer, Unit> function1110 = new Function3<Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Composer composer, Integer num2) {
                        invoke(num.intValue(), composer, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int layoutHeight, Composer $composer3, int $changed2) {
                        ComposerKt.sourceInformation($composer3, "C127@6516L581:BottomSheetScaffold.kt#uh7d8r");
                        int $dirty7 = $changed2;
                        if (($changed2 & 14) == 0) {
                            $dirty7 |= $composer3.changed(layoutHeight) ? 4 : 2;
                        }
                        if (($dirty7 & 91) != 18 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(106433656, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:126)");
                            }
                            Shape shape8 = shape7;
                            long j19 = j17;
                            long j110 = j18;
                            float f112 = f110;
                            float f113 = f111;
                            Function2<Composer, Integer, Unit> function1111 = function119;
                            Function3<ColumnScope, Composer, Integer, Unit> function1112 = sheetContent;
                            int i24 = i116;
                            BottomSheetScaffoldKt.m1329StandardBottomSheet8oydGBM(bottomSheetScaffoldState7.getBottomSheetState(), f19, z7, layoutHeight, shape8, j19, j110, f112, f113, function1111, function1112, $composer3, ((i24 >> 6) & 112) | ((i117 << 6) & 896) | (i24 & 57344) | (i24 & 458752) | (i24 & 3670016) | (i24 & 29360128) | (i24 & 234881024) | (i24 & 1879048192), i24 & 14);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                };
                float sheetTonalElevation10 = sheetTonalElevation4;
                float sheetShadowElevation9 = sheetShadowElevation3;
                ComposableLambda composableLambda9 = ComposableLambdaKt.composableLambda($composer2, 106433656, true, function1110);
                Function2<? super Composer, ? super Integer, Unit> function1111 = function7;
                ComposableLambda composableLambda10 = ComposableLambdaKt.composableLambda($composer2, -1629779374, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$2
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
                        ComposerKt.sourceInformation($composer3, "C119@6159L45:BottomSheetScaffold.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1629779374, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:118)");
                        }
                        function3M1445getLambda2$material3_release.invoke(scaffoldState3.getSnackbarHostState(), $composer3, Integer.valueOf(($dirty1 >> 3) & 112));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                });
                int i24 = ($dirty3 >> 6) & 14;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scaffoldState3);
                boolean sheetSwipeEnabled9 = sheetSwipeEnabled3;
                Object it$iv$iv5 = $composer2.rememberedValue();
                if (invalid$iv$iv) {
                }
                value$iv$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$3$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(scaffoldState3.getBottomSheetState().requireOffset());
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                m1328BottomSheetScaffoldLayoutPxNyym8(modifier3, function5, content, composableLambda9, composableLambda10, sheetPeekHeight2, (Function0) value$iv$iv, bottomSheetState5, containerColor3, contentColor2, $composer2, (($dirty3 >> 3) & 14) | 27648 | ($dirty1 & 112) | (($dirty1 >> 9) & 896) | (($dirty3 << 6) & 458752) | (($dirty1 << 15) & 234881024) | (1879048192 & ($dirty1 << 15)));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                sheetTonalElevation5 = sheetTonalElevation10;
                function8 = function5;
                sheetShadowElevation4 = sheetShadowElevation9;
                function9 = function1111;
                sheetSwipeEnabled4 = sheetSwipeEnabled9;
                modifier4 = modifier3;
                function10 = function3M1445getLambda2$material3_release;
                sheetPeekHeight3 = sheetPeekHeight2;
                sheetShape3 = sheetShape2;
                sheetContainerColor3 = sheetContainerColor2;
                sheetContentColor3 = sheetContentColor2;
                containerColor4 = containerColor3;
                contentColor3 = contentColor2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier7 = modifier4;
            final BottomSheetScaffoldState bottomSheetScaffoldState8 = scaffoldState3;
            final float f112 = sheetPeekHeight3;
            final Shape shape8 = sheetShape3;
            final long j19 = sheetContainerColor3;
            final long j110 = sheetContentColor3;
            final float f113 = sheetTonalElevation5;
            final float f114 = sheetShadowElevation4;
            final Function2<? super Composer, ? super Integer, Unit> function1112 = function9;
            final boolean z8 = sheetSwipeEnabled4;
            final Function2<? super Composer, ? super Integer, Unit> function1113 = function8;
            final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function1114 = function10;
            final long j111 = containerColor4;
            final long j112 = contentColor3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$4
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

                public final void invoke(Composer composer, int i25) {
                    BottomSheetScaffoldKt.m1327BottomSheetScaffold6cEcpDs(sheetContent, modifier7, bottomSheetScaffoldState8, f112, shape8, j19, j110, f113, f114, function1112, z8, function1113, function1114, j111, j112, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i9 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty6 |= i9;
        if (($dirty2 & 1533916891) != 306783378) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i10 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    scaffoldState2 = scaffoldState;
                }
                if (i11 != 0) {
                    sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                } else {
                    sheetPeekHeight2 = sheetPeekHeight;
                }
                if ((i & 16) != 0) {
                    sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                    $dirty2 &= -57345;
                } else {
                    sheetShape2 = sheetShape;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                } else {
                    sheetContainerColor2 = sheetContainerColor;
                }
                if ((i & 64) != 0) {
                    sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                    $dirty2 &= -3670017;
                } else {
                    sheetContentColor2 = sheetContentColor;
                }
                if (i14 != 0) {
                    sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                } else {
                    sheetTonalElevation2 = sheetTonalElevation;
                }
                if (i15 != 0) {
                    sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                } else {
                    sheetShadowElevation2 = sheetShadowElevation;
                }
                if (i16 != 0) {
                    function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                } else {
                    function2M1444getLambda1$material3_release = function2;
                }
                if (i3 != 0) {
                    sheetSwipeEnabled2 = true;
                } else {
                    sheetSwipeEnabled2 = sheetSwipeEnabled;
                }
                if (i5 != 0) {
                    function5 = null;
                } else {
                    function5 = function3;
                }
                if (i8 != 0) {
                    function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                } else {
                    function3M1445getLambda2$material3_release = function4;
                }
                sheetTonalElevation3 = sheetTonalElevation2;
                if ((i & 8192) != 0) {
                    containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty6 &= -7169;
                } else {
                    containerColor2 = containerColor;
                }
                function6 = function2M1444getLambda1$material3_release;
                if ((i & 16384) != 0) {
                    sheetShadowElevation3 = sheetShadowElevation2;
                    $dirty1 = $dirty6 & (-57345);
                    $dirty3 = $dirty2;
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                    sheetTonalElevation4 = sheetTonalElevation3;
                    containerColor3 = containerColor2;
                    sheetSwipeEnabled3 = sheetSwipeEnabled2;
                    modifier3 = modifier2;
                    scaffoldState3 = scaffoldState2;
                    function7 = function6;
                } else {
                    sheetShadowElevation3 = sheetShadowElevation2;
                    contentColor2 = contentColor;
                    containerColor3 = containerColor2;
                    $dirty1 = $dirty6;
                    $dirty3 = $dirty2;
                    modifier3 = modifier2;
                    scaffoldState3 = scaffoldState2;
                    sheetTonalElevation4 = sheetTonalElevation3;
                    sheetSwipeEnabled3 = sheetSwipeEnabled2;
                    function7 = function6;
                }
            } else {
                if (i10 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    scaffoldState2 = scaffoldState;
                }
                if (i11 != 0) {
                    sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                } else {
                    sheetPeekHeight2 = sheetPeekHeight;
                }
                if ((i & 16) != 0) {
                    sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                    $dirty2 &= -57345;
                } else {
                    sheetShape2 = sheetShape;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                } else {
                    sheetContainerColor2 = sheetContainerColor;
                }
                if ((i & 64) != 0) {
                    sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                    $dirty2 &= -3670017;
                } else {
                    sheetContentColor2 = sheetContentColor;
                }
                if (i14 != 0) {
                    sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                } else {
                    sheetTonalElevation2 = sheetTonalElevation;
                }
                if (i15 != 0) {
                    sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                } else {
                    sheetShadowElevation2 = sheetShadowElevation;
                }
                if (i16 != 0) {
                    function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                } else {
                    function2M1444getLambda1$material3_release = function2;
                }
                if (i3 != 0) {
                    sheetSwipeEnabled2 = true;
                } else {
                    sheetSwipeEnabled2 = sheetSwipeEnabled;
                }
                if (i5 != 0) {
                    function5 = null;
                } else {
                    function5 = function3;
                }
                if (i8 != 0) {
                    function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                } else {
                    function3M1445getLambda2$material3_release = function4;
                }
                sheetTonalElevation3 = sheetTonalElevation2;
                if ((i & 8192) != 0) {
                    containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty6 &= -7169;
                } else {
                    containerColor2 = containerColor;
                }
                function6 = function2M1444getLambda1$material3_release;
                if ((i & 16384) != 0) {
                    sheetShadowElevation3 = sheetShadowElevation2;
                    $dirty1 = $dirty6 & (-57345);
                    $dirty3 = $dirty2;
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                    sheetTonalElevation4 = sheetTonalElevation3;
                    containerColor3 = containerColor2;
                    sheetSwipeEnabled3 = sheetSwipeEnabled2;
                    modifier3 = modifier2;
                    scaffoldState3 = scaffoldState2;
                    function7 = function6;
                } else {
                    sheetShadowElevation3 = sheetShadowElevation2;
                    contentColor2 = contentColor;
                    containerColor3 = containerColor2;
                    $dirty1 = $dirty6;
                    $dirty3 = $dirty2;
                    modifier3 = modifier2;
                    scaffoldState3 = scaffoldState2;
                    sheetTonalElevation4 = sheetTonalElevation3;
                    sheetSwipeEnabled3 = sheetSwipeEnabled2;
                    function7 = function6;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(144898711, $dirty3, $dirty1, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:96)");
            }
            SheetState bottomSheetState6 = scaffoldState3.getBottomSheetState();
            final BottomSheetScaffoldState bottomSheetScaffoldState9 = scaffoldState3;
            final float f115 = sheetPeekHeight2;
            final boolean z9 = sheetSwipeEnabled3;
            final Shape shape9 = sheetShape2;
            final long j113 = sheetContainerColor2;
            final long j114 = sheetContentColor2;
            final float f116 = sheetTonalElevation4;
            final float f117 = sheetShadowElevation3;
            final Function2<? super Composer, ? super Integer, Unit> function1115 = function7;
            final int i118 = $dirty3;
            final int i119 = $dirty1;
            Function3<Integer, Composer, Integer, Unit> function1116 = new Function3<Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Integer num, Composer composer, Integer num2) {
                    invoke(num.intValue(), composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int layoutHeight, Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C127@6516L581:BottomSheetScaffold.kt#uh7d8r");
                    int $dirty7 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty7 |= $composer3.changed(layoutHeight) ? 4 : 2;
                    }
                    if (($dirty7 & 91) != 18 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(106433656, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:126)");
                        }
                        Shape shape10 = shape9;
                        long j115 = j113;
                        long j116 = j114;
                        float f118 = f116;
                        float f119 = f117;
                        Function2<Composer, Integer, Unit> function1117 = function1115;
                        Function3<ColumnScope, Composer, Integer, Unit> function1118 = sheetContent;
                        int i25 = i118;
                        BottomSheetScaffoldKt.m1329StandardBottomSheet8oydGBM(bottomSheetScaffoldState9.getBottomSheetState(), f115, z9, layoutHeight, shape10, j115, j116, f118, f119, function1117, function1118, $composer3, ((i25 >> 6) & 112) | ((i119 << 6) & 896) | (i25 & 57344) | (i25 & 458752) | (i25 & 3670016) | (i25 & 29360128) | (i25 & 234881024) | (i25 & 1879048192), i25 & 14);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            };
            float sheetTonalElevation11 = sheetTonalElevation4;
            float sheetShadowElevation10 = sheetShadowElevation3;
            ComposableLambda composableLambda11 = ComposableLambdaKt.composableLambda($composer2, 106433656, true, function1116);
            Function2<? super Composer, ? super Integer, Unit> function1117 = function7;
            ComposableLambda composableLambda12 = ComposableLambdaKt.composableLambda($composer2, -1629779374, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$2
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
                    ComposerKt.sourceInformation($composer3, "C119@6159L45:BottomSheetScaffold.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1629779374, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:118)");
                    }
                    function3M1445getLambda2$material3_release.invoke(scaffoldState3.getSnackbarHostState(), $composer3, Integer.valueOf(($dirty1 >> 3) & 112));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            });
            int i25 = ($dirty3 >> 6) & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(scaffoldState3);
            boolean sheetSwipeEnabled10 = sheetSwipeEnabled3;
            Object it$iv$iv6 = $composer2.rememberedValue();
            if (invalid$iv$iv) {
            }
            value$iv$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$3$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return Float.valueOf(scaffoldState3.getBottomSheetState().requireOffset());
                }
            };
            $composer2.updateRememberedValue(value$iv$iv);
            $composer2.endReplaceableGroup();
            m1328BottomSheetScaffoldLayoutPxNyym8(modifier3, function5, content, composableLambda11, composableLambda12, sheetPeekHeight2, (Function0) value$iv$iv, bottomSheetState6, containerColor3, contentColor2, $composer2, (($dirty3 >> 3) & 14) | 27648 | ($dirty1 & 112) | (($dirty1 >> 9) & 896) | (($dirty3 << 6) & 458752) | (($dirty1 << 15) & 234881024) | (1879048192 & ($dirty1 << 15)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            sheetTonalElevation5 = sheetTonalElevation11;
            function8 = function5;
            sheetShadowElevation4 = sheetShadowElevation10;
            function9 = function1117;
            sheetSwipeEnabled4 = sheetSwipeEnabled10;
            modifier4 = modifier3;
            function10 = function3M1445getLambda2$material3_release;
            sheetPeekHeight3 = sheetPeekHeight2;
            sheetShape3 = sheetShape2;
            sheetContainerColor3 = sheetContainerColor2;
            sheetContentColor3 = sheetContentColor2;
            containerColor4 = containerColor3;
            contentColor3 = contentColor2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i10 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    scaffoldState2 = scaffoldState;
                }
                if (i11 != 0) {
                    sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                } else {
                    sheetPeekHeight2 = sheetPeekHeight;
                }
                if ((i & 16) != 0) {
                    sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                    $dirty2 &= -57345;
                } else {
                    sheetShape2 = sheetShape;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                } else {
                    sheetContainerColor2 = sheetContainerColor;
                }
                if ((i & 64) != 0) {
                    sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                    $dirty2 &= -3670017;
                } else {
                    sheetContentColor2 = sheetContentColor;
                }
                if (i14 != 0) {
                    sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                } else {
                    sheetTonalElevation2 = sheetTonalElevation;
                }
                if (i15 != 0) {
                    sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                } else {
                    sheetShadowElevation2 = sheetShadowElevation;
                }
                if (i16 != 0) {
                    function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                } else {
                    function2M1444getLambda1$material3_release = function2;
                }
                if (i3 != 0) {
                    sheetSwipeEnabled2 = true;
                } else {
                    sheetSwipeEnabled2 = sheetSwipeEnabled;
                }
                if (i5 != 0) {
                    function5 = null;
                } else {
                    function5 = function3;
                }
                if (i8 != 0) {
                    function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                } else {
                    function3M1445getLambda2$material3_release = function4;
                }
                sheetTonalElevation3 = sheetTonalElevation2;
                if ((i & 8192) != 0) {
                    containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty6 &= -7169;
                } else {
                    containerColor2 = containerColor;
                }
                function6 = function2M1444getLambda1$material3_release;
                if ((i & 16384) != 0) {
                    sheetShadowElevation3 = sheetShadowElevation2;
                    $dirty1 = $dirty6 & (-57345);
                    $dirty3 = $dirty2;
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                    sheetTonalElevation4 = sheetTonalElevation3;
                    containerColor3 = containerColor2;
                    sheetSwipeEnabled3 = sheetSwipeEnabled2;
                    modifier3 = modifier2;
                    scaffoldState3 = scaffoldState2;
                    function7 = function6;
                } else {
                    sheetShadowElevation3 = sheetShadowElevation2;
                    contentColor2 = contentColor;
                    containerColor3 = containerColor2;
                    $dirty1 = $dirty6;
                    $dirty3 = $dirty2;
                    modifier3 = modifier2;
                    scaffoldState3 = scaffoldState2;
                    sheetTonalElevation4 = sheetTonalElevation3;
                    sheetSwipeEnabled3 = sheetSwipeEnabled2;
                    function7 = function6;
                }
            } else {
                if (i10 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    scaffoldState2 = scaffoldState;
                }
                if (i11 != 0) {
                    sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m1326getSheetPeekHeightD9Ej5fM();
                } else {
                    sheetPeekHeight2 = sheetPeekHeight;
                }
                if ((i & 16) != 0) {
                    sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer2, 6);
                    $dirty2 &= -57345;
                } else {
                    sheetShape2 = sheetShape;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer2, 6);
                } else {
                    sheetContainerColor2 = sheetContainerColor;
                }
                if ((i & 64) != 0) {
                    sheetContentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(sheetContainerColor2, $composer2, ($dirty2 >> 15) & 14);
                    $dirty2 &= -3670017;
                } else {
                    sheetContentColor2 = sheetContentColor;
                }
                if (i14 != 0) {
                    sheetTonalElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                } else {
                    sheetTonalElevation2 = sheetTonalElevation;
                }
                if (i15 != 0) {
                    sheetShadowElevation2 = BottomSheetDefaults.INSTANCE.m1325getElevationD9Ej5fM();
                } else {
                    sheetShadowElevation2 = sheetShadowElevation;
                }
                if (i16 != 0) {
                    function2M1444getLambda1$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1444getLambda1$material3_release();
                } else {
                    function2M1444getLambda1$material3_release = function2;
                }
                if (i3 != 0) {
                    sheetSwipeEnabled2 = true;
                } else {
                    sheetSwipeEnabled2 = sheetSwipeEnabled;
                }
                if (i5 != 0) {
                    function5 = null;
                } else {
                    function5 = function3;
                }
                if (i8 != 0) {
                    function3M1445getLambda2$material3_release = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1445getLambda2$material3_release();
                } else {
                    function3M1445getLambda2$material3_release = function4;
                }
                sheetTonalElevation3 = sheetTonalElevation2;
                if ((i & 8192) != 0) {
                    containerColor2 = MaterialTheme.INSTANCE.getColorScheme($composer2, 6).m1390getSurface0d7_KjU();
                    $dirty6 &= -7169;
                } else {
                    containerColor2 = containerColor;
                }
                function6 = function2M1444getLambda1$material3_release;
                if ((i & 16384) != 0) {
                    sheetShadowElevation3 = sheetShadowElevation2;
                    $dirty1 = $dirty6 & (-57345);
                    $dirty3 = $dirty2;
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty6 >> 9) & 14);
                    sheetTonalElevation4 = sheetTonalElevation3;
                    containerColor3 = containerColor2;
                    sheetSwipeEnabled3 = sheetSwipeEnabled2;
                    modifier3 = modifier2;
                    scaffoldState3 = scaffoldState2;
                    function7 = function6;
                } else {
                    sheetShadowElevation3 = sheetShadowElevation2;
                    contentColor2 = contentColor;
                    containerColor3 = containerColor2;
                    $dirty1 = $dirty6;
                    $dirty3 = $dirty2;
                    modifier3 = modifier2;
                    scaffoldState3 = scaffoldState2;
                    sheetTonalElevation4 = sheetTonalElevation3;
                    sheetSwipeEnabled3 = sheetSwipeEnabled2;
                    function7 = function6;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(144898711, $dirty3, $dirty1, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:96)");
            }
            SheetState bottomSheetState7 = scaffoldState3.getBottomSheetState();
            final BottomSheetScaffoldState bottomSheetScaffoldState10 = scaffoldState3;
            final float f118 = sheetPeekHeight2;
            final boolean z10 = sheetSwipeEnabled3;
            final Shape shape10 = sheetShape2;
            final long j115 = sheetContainerColor2;
            final long j116 = sheetContentColor2;
            final float f119 = sheetTonalElevation4;
            final float f1110 = sheetShadowElevation3;
            final Function2<? super Composer, ? super Integer, Unit> function1118 = function7;
            final int i1110 = $dirty3;
            final int i1111 = $dirty1;
            Function3<Integer, Composer, Integer, Unit> function1119 = new Function3<Integer, Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Integer num, Composer composer, Integer num2) {
                    invoke(num.intValue(), composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int layoutHeight, Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C127@6516L581:BottomSheetScaffold.kt#uh7d8r");
                    int $dirty7 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty7 |= $composer3.changed(layoutHeight) ? 4 : 2;
                    }
                    if (($dirty7 & 91) != 18 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(106433656, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:126)");
                        }
                        Shape shape11 = shape10;
                        long j117 = j115;
                        long j118 = j116;
                        float f1111 = f119;
                        float f1112 = f1110;
                        Function2<Composer, Integer, Unit> function11110 = function1118;
                        Function3<ColumnScope, Composer, Integer, Unit> function11111 = sheetContent;
                        int i26 = i1110;
                        BottomSheetScaffoldKt.m1329StandardBottomSheet8oydGBM(bottomSheetScaffoldState10.getBottomSheetState(), f118, z10, layoutHeight, shape11, j117, j118, f1111, f1112, function11110, function11111, $composer3, ((i26 >> 6) & 112) | ((i1111 << 6) & 896) | (i26 & 57344) | (i26 & 458752) | (i26 & 3670016) | (i26 & 29360128) | (i26 & 234881024) | (i26 & 1879048192), i26 & 14);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            };
            float sheetTonalElevation12 = sheetTonalElevation4;
            float sheetShadowElevation11 = sheetShadowElevation3;
            ComposableLambda composableLambda13 = ComposableLambdaKt.composableLambda($composer2, 106433656, true, function1119);
            Function2<? super Composer, ? super Integer, Unit> function11110 = function7;
            ComposableLambda composableLambda14 = ComposableLambdaKt.composableLambda($composer2, -1629779374, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$2
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
                    ComposerKt.sourceInformation($composer3, "C119@6159L45:BottomSheetScaffold.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1629779374, $changed2, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:118)");
                    }
                    function3M1445getLambda2$material3_release.invoke(scaffoldState3.getSnackbarHostState(), $composer3, Integer.valueOf(($dirty1 >> 3) & 112));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            });
            int i26 = ($dirty3 >> 6) & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(scaffoldState3);
            boolean sheetSwipeEnabled11 = sheetSwipeEnabled3;
            Object it$iv$iv7 = $composer2.rememberedValue();
            if (invalid$iv$iv) {
            }
            value$iv$iv = (Function0) new Function0<Float>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$3$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return Float.valueOf(scaffoldState3.getBottomSheetState().requireOffset());
                }
            };
            $composer2.updateRememberedValue(value$iv$iv);
            $composer2.endReplaceableGroup();
            m1328BottomSheetScaffoldLayoutPxNyym8(modifier3, function5, content, composableLambda13, composableLambda14, sheetPeekHeight2, (Function0) value$iv$iv, bottomSheetState7, containerColor3, contentColor2, $composer2, (($dirty3 >> 3) & 14) | 27648 | ($dirty1 & 112) | (($dirty1 >> 9) & 896) | (($dirty3 << 6) & 458752) | (($dirty1 << 15) & 234881024) | (1879048192 & ($dirty1 << 15)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            sheetTonalElevation5 = sheetTonalElevation12;
            function8 = function5;
            sheetShadowElevation4 = sheetShadowElevation11;
            function9 = function11110;
            sheetSwipeEnabled4 = sheetSwipeEnabled11;
            modifier4 = modifier3;
            function10 = function3M1445getLambda2$material3_release;
            sheetPeekHeight3 = sheetPeekHeight2;
            sheetShape3 = sheetShape2;
            sheetContainerColor3 = sheetContainerColor2;
            sheetContentColor3 = sheetContentColor2;
            containerColor4 = containerColor3;
            contentColor3 = contentColor2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier8 = modifier4;
        final BottomSheetScaffoldState bottomSheetScaffoldState11 = scaffoldState3;
        final float f1111 = sheetPeekHeight3;
        final Shape shape11 = sheetShape3;
        final long j117 = sheetContainerColor3;
        final long j118 = sheetContentColor3;
        final float f1112 = sheetTonalElevation5;
        final float f1113 = sheetShadowElevation4;
        final Function2<? super Composer, ? super Integer, Unit> function11111 = function9;
        final boolean z11 = sheetSwipeEnabled4;
        final Function2<? super Composer, ? super Integer, Unit> function11112 = function8;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function11113 = function10;
        final long j119 = containerColor4;
        final long j1110 = contentColor3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$4
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

            public final void invoke(Composer composer, int i27) {
                BottomSheetScaffoldKt.m1327BottomSheetScaffold6cEcpDs(sheetContent, modifier8, bottomSheetScaffoldState11, f1111, shape11, j117, j118, f1112, f1113, function11111, z11, function11112, function11113, j119, j1110, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    public static final BottomSheetScaffoldState rememberBottomSheetScaffoldState(SheetState bottomSheetState, SnackbarHostState snackbarHostState, Composer $composer, int $changed, int i) {
        Object value$iv$iv;
        Object value$iv$iv2;
        $composer.startReplaceableGroup(-1474606134);
        ComposerKt.sourceInformation($composer, "C(rememberBottomSheetScaffoldState)167@7874L34,168@7953L32,170@8027L196:BottomSheetScaffold.kt#uh7d8r");
        if ((i & 1) != 0) {
            bottomSheetState = rememberStandardBottomSheetState(null, null, false, $composer, 0, 7);
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
            ComposerKt.traceEventStart(-1474606134, $changed, -1, "androidx.compose.material3.rememberBottomSheetScaffoldState (BottomSheetScaffold.kt:166)");
        }
        int i2 = ($changed & 14) | ($changed & 112);
        $composer.startReplaceableGroup(511388516);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(bottomSheetState) | $composer.changed(snackbarHostState);
        Object it$iv$iv2 = $composer.rememberedValue();
        if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new BottomSheetScaffoldState(bottomSheetState, snackbarHostState);
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv2;
        }
        $composer.endReplaceableGroup();
        BottomSheetScaffoldState bottomSheetScaffoldState = (BottomSheetScaffoldState) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return bottomSheetScaffoldState;
    }

    public static final SheetState rememberStandardBottomSheetState(SheetValue initialValue, Function1<? super SheetValue, Boolean> function1, boolean skipHiddenState, Composer $composer, int $changed, int i) {
        $composer.startReplaceableGroup(678511581);
        ComposerKt.sourceInformation($composer, "C(rememberStandardBottomSheetState)P(1)192@8853L76:BottomSheetScaffold.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialValue = SheetValue.PartiallyExpanded;
        }
        if ((i & 2) != 0) {
            Function1 confirmValueChange = new Function1<SheetValue, Boolean>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt.rememberStandardBottomSheetState.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(SheetValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
            function1 = confirmValueChange;
        }
        if ((i & 4) != 0) {
            skipHiddenState = true;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(678511581, $changed, -1, "androidx.compose.material3.rememberStandardBottomSheetState (BottomSheetScaffold.kt:188)");
        }
        SheetState sheetStateRememberSheetState = SheetDefaultsKt.rememberSheetState(false, function1, initialValue, skipHiddenState, $composer, ($changed & 112) | 6 | (($changed << 6) & 896) | (($changed << 3) & 7168), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return sheetStateRememberSheetState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:128:0x0399  */
    /* JADX INFO: renamed from: StandardBottomSheet-8oydGBM, reason: not valid java name */
    public static final void m1329StandardBottomSheet8oydGBM(final SheetState state, final float peekHeight, final boolean sheetSwipeEnabled, final float layoutHeight, final Shape shape, final long containerColor, final long contentColor, final float tonalElevation, final float shadowElevation, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1) {
        Object value$iv$iv$iv;
        Object value$iv$iv;
        Object value$iv$iv2;
        boolean invalid$iv$iv;
        Object value$iv$iv3;
        Composer $composer2 = $composer.startRestartGroup(-763942484);
        ComposerKt.sourceInformation($composer2, "C(StandardBottomSheet)P(9,5:c#ui.unit.Dp,8,4,7,0:c#ui.graphics.Color,2:c#ui.graphics.Color,10:c#ui.unit.Dp,6:c#ui.unit.Dp,3)209@9346L24,*210@9412L7,214@9580L467,235@10256L318,252@10976L530,229@10052L3768:BottomSheetScaffold.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(peekHeight) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(sheetSwipeEnabled) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(layoutHeight) ? 2048 : 1024;
        }
        if (($changed & 57344) == 0) {
            $dirty |= $composer2.changed(shape) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            $dirty |= $composer2.changed(containerColor) ? 131072 : 65536;
        }
        if ((3670016 & $changed) == 0) {
            $dirty |= $composer2.changed(contentColor) ? 1048576 : 524288;
        }
        if ((29360128 & $changed) == 0) {
            $dirty |= $composer2.changed(tonalElevation) ? 8388608 : 4194304;
        }
        if (($changed & 234881024) == 0) {
            $dirty |= $composer2.changed(shadowElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 1879048192) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 536870912 : 268435456;
        }
        if (($changed1 & 14) == 0) {
            $dirty1 |= $composer2.changedInstance(function3) ? 4 : 2;
        }
        final int $dirty2 = $dirty1;
        if ((1533916891 & $dirty) != 306783378 || ($dirty2 & 11) != 2 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-763942484, $dirty, $dirty2, "androidx.compose.material3.StandardBottomSheet (BottomSheetScaffold.kt:196)");
            }
            $composer2.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer2, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2));
                $composer2.updateRememberedValue(value$iv$iv$iv);
            } else {
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer2.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            $composer2.endReplaceableGroup();
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$StandardBottomSheet_8oydGBM_u24lambda_u243 = (Density) objConsume;
            final float peekHeightPx = $this$StandardBottomSheet_8oydGBM_u24lambda_u243.mo327toPx0680j_4(peekHeight);
            Orientation orientation = Orientation.Vertical;
            int i = ($dirty & 14) | 64;
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean invalid$iv$iv2 = $composer2.changed(state) | $composer2.changed(scope);
            Object it$iv$iv = $composer2.rememberedValue();
            if (invalid$iv$iv2 || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = BottomSheetScaffoldAnchorChangeHandler(state, new Function2<SheetValue, Float, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$anchorChangeHandler$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(SheetValue sheetValue, Float f) {
                        invoke(sheetValue, f.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(SheetValue target, float velocity) {
                        Intrinsics.checkNotNullParameter(target, "target");
                        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(state, target, velocity, null), 3, null);
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$anchorChangeHandler$1$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$anchorChangeHandler$1$1$1", f = "BottomSheetScaffold.kt", i = {}, l = {220}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $state;
                        final /* synthetic */ SheetValue $target;
                        final /* synthetic */ float $velocity;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SheetState sheetState, SheetValue sheetValue, float f, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$state = sheetState;
                            this.$target = sheetValue;
                            this.$velocity = f;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$state, this.$target, this.$velocity, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$state.getSwipeableState$material3_release().animateTo(this.$target, this.$velocity, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }
                }, new Function1<SheetValue, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$anchorChangeHandler$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SheetValue sheetValue) {
                        invoke2(sheetValue);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SheetValue target) {
                        Intrinsics.checkNotNullParameter(target, "target");
                        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(state, target, null), 3, null);
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$anchorChangeHandler$1$2$1, reason: invalid class name */
                    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$anchorChangeHandler$1$2$1", f = "BottomSheetScaffold.kt", i = {}, l = {226}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SheetState $state;
                        final /* synthetic */ SheetValue $target;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SheetState sheetState, SheetValue sheetValue, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$state = sheetState;
                            this.$target = sheetValue;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$state, this.$target, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$state.getSwipeableState$material3_release().snapTo(this.$target, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    break;
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }
                });
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            AnchorChangeHandler anchorChangeHandler = (AnchorChangeHandler) value$iv$iv;
            Modifier modifierM525requiredHeightInVpY3zN4$default = SizeKt.m525requiredHeightInVpY3zN4$default(SizeKt.fillMaxWidth$default(SizeKt.m541widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, SheetDefaultsKt.getBottomSheetMaxWidth(), 1, null), 0.0f, 1, null), peekHeight, 0.0f, 2, null);
            Object key1$iv = state.getSwipeableState$material3_release();
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv3 = $composer2.changed(key1$iv);
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (!invalid$iv$iv3) {
                Object key1$iv2 = Composer.INSTANCE.getEmpty();
                if (it$iv$iv2 != key1$iv2) {
                    value$iv$iv2 = it$iv$iv2;
                }
                $composer2.endReplaceableGroup();
                Modifier modifierSwipeableV2 = SwipeableV2Kt.swipeableV2(NestedScrollModifierKt.nestedScroll$default(modifierM525requiredHeightInVpY3zN4$default, (NestedScrollConnection) value$iv$iv2, null, 2, null), state.getSwipeableState$material3_release(), orientation, (16 & 4) != 0 ? true : sheetSwipeEnabled, (16 & 8) != 0 ? false : false, (16 & 16) != 0 ? null : null);
                SwipeableV2State<SheetValue> swipeableState$material3_release = state.getSwipeableState$material3_release();
                Set of = SetsKt.setOf((Object[]) new SheetValue[]{SheetValue.Hidden, SheetValue.PartiallyExpanded, SheetValue.Expanded});
                Object key2$iv = Float.valueOf(layoutHeight);
                Object key3$iv = Float.valueOf(peekHeightPx);
                int i2 = ($dirty & 14) | (($dirty >> 6) & 112);
                final int $dirty3 = $dirty;
                $composer2.startReplaceableGroup(1618982084);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(state) | $composer2.changed(key2$iv) | $composer2.changed(key3$iv);
                Object it$iv$iv3 = $composer2.rememberedValue();
                if (!invalid$iv$iv || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv3 = (Function2) new Function2<SheetValue, IntSize, Float>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2$1

                        /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                        public /* synthetic */ class WhenMappings {
                            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                            static {
                                int[] iArr = new int[SheetValue.values().length];
                                try {
                                    iArr[SheetValue.PartiallyExpanded.ordinal()] = 1;
                                } catch (NoSuchFieldError e) {
                                }
                                try {
                                    iArr[SheetValue.Expanded.ordinal()] = 2;
                                } catch (NoSuchFieldError e2) {
                                }
                                try {
                                    iArr[SheetValue.Hidden.ordinal()] = 3;
                                } catch (NoSuchFieldError e3) {
                                }
                                $EnumSwitchMapping$0 = iArr;
                            }
                        }

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Float invoke(SheetValue sheetValue, IntSize intSize) {
                            return m1333invokeO0kMr_c(sheetValue, intSize.getPackedValue());
                        }

                        /* JADX INFO: renamed from: invoke-O0kMr_c, reason: not valid java name */
                        public final Float m1333invokeO0kMr_c(SheetValue value, long sheetSize) {
                            Intrinsics.checkNotNullParameter(value, "value");
                            switch (WhenMappings.$EnumSwitchMapping$0[value.ordinal()]) {
                                case 1:
                                    if (state.getSkipPartiallyExpanded()) {
                                        return null;
                                    }
                                    return Float.valueOf(layoutHeight - peekHeightPx);
                                case 2:
                                    if (IntSize.m5433getHeightimpl(sheetSize) == MathKt.roundToInt(peekHeightPx)) {
                                        return null;
                                    }
                                    return Float.valueOf(Math.max(0.0f, layoutHeight - IntSize.m5433getHeightimpl(sheetSize)));
                                case 3:
                                    if (state.getSkipHiddenState()) {
                                        return null;
                                    }
                                    return Float.valueOf(layoutHeight);
                                default:
                                    throw new NoWhenBranchMatchedException();
                            }
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv3);
                } else {
                    value$iv$iv3 = it$iv$iv3;
                }
                $composer2.endReplaceableGroup();
                SurfaceKt.m1806SurfaceT9BRK9s(SwipeableV2Kt.swipeAnchors(modifierSwipeableV2, swipeableState$material3_release, of, anchorChangeHandler, (Function2) value$iv$iv3), shape, containerColor, contentColor, tonalElevation, shadowElevation, null, ComposableLambdaKt.composableLambda($composer2, -1381492089, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3
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
                        ComposerKt.sourceInformation($composer3, "C270@11700L2114:BottomSheetScaffold.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1381492089, $changed2, -1, "androidx.compose.material3.StandardBottomSheet.<anonymous> (BottomSheetScaffold.kt:269)");
                            }
                            Modifier modifier$iv = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Function2<Composer, Integer, Unit> function4 = function2;
                            Function3<ColumnScope, Composer, Integer, Unit> function5 = function3;
                            int i3 = $dirty2;
                            final SheetState sheetState = state;
                            final boolean z = sheetSwipeEnabled;
                            final CoroutineScope coroutineScope = scope;
                            int i4 = $dirty3;
                            $composer3.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                            int $changed$iv$iv = (6 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer3.consume(localDensity2);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume2;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume3;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume4 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume4;
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                            if (!($composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                                $composer3.createNode(constructor);
                            } else {
                                $composer3.useNode();
                            }
                            $composer3.disableReusing();
                            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer3);
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                            $composer3.enableReusing();
                            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                            $composer3.startReplaceableGroup(2058660585);
                            int i5 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                            int $changed3 = ((6 >> 6) & 112) | 6;
                            ColumnScope $this$invoke_u24lambda_u241 = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer3, -176229648, "C309@13795L9:BottomSheetScaffold.kt#uh7d8r");
                            $composer3.startReplaceableGroup(-176229648);
                            ComposerKt.sourceInformation($composer3, "273@11839L54,274@11935L48,275@12024L47,276@12088L1680");
                            if (function4 != null) {
                                final String partialExpandActionLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1738getBottomSheetPartialExpandDescriptionadMyvUU(), $composer3, 6);
                                final String dismissActionLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1734getBottomSheetDismissDescriptionadMyvUU(), $composer3, 6);
                                final String expandActionLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1736getBottomSheetExpandDescriptionadMyvUU(), $composer3, 6);
                                Modifier modifier$iv2 = SemanticsModifierKt.semantics($this$invoke_u24lambda_u241.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally()), true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver semantics) {
                                        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                                        final SheetState $this$invoke_u24lambda_u240 = sheetState;
                                        boolean z2 = z;
                                        String str = expandActionLabel;
                                        String str2 = partialExpandActionLabel;
                                        SheetState sheetState2 = sheetState;
                                        String str3 = dismissActionLabel;
                                        final CoroutineScope coroutineScope2 = coroutineScope;
                                        if ($this$invoke_u24lambda_u240.getSwipeableState$material3_release().getAnchors$material3_release().size() > 1 && z2) {
                                            if ($this$invoke_u24lambda_u240.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                                if ($this$invoke_u24lambda_u240.getSwipeableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                    SemanticsPropertiesKt.expand(semantics, str, new Function0<Boolean>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(0);
                                                        }

                                                        /* JADX WARN: Can't rename method to resolve collision */
                                                        @Override // kotlin.jvm.functions.Function0
                                                        public final Boolean invoke() {
                                                            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1($this$invoke_u24lambda_u240, null), 3, null);
                                                            return true;
                                                        }

                                                        /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$1, reason: invalid class name */
                                                        /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                                        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                                        @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$1", f = "BottomSheetScaffold.kt", i = {}, l = {287}, m = "invokeSuspend", n = {}, s = {})
                                                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                            final /* synthetic */ SheetState $this_with;
                                                            int label;

                                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                            AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                                super(2, continuation);
                                                                this.$this_with = sheetState;
                                                            }

                                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                                return new AnonymousClass1(this.$this_with, continuation);
                                                            }

                                                            @Override // kotlin.jvm.functions.Function2
                                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                            }

                                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                            public final Object invokeSuspend(Object $result) {
                                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                switch (this.label) {
                                                                    case 0:
                                                                        ResultKt.throwOnFailure($result);
                                                                        this.label = 1;
                                                                        if (this.$this_with.expand(this) == coroutine_suspended) {
                                                                            return coroutine_suspended;
                                                                        }
                                                                        break;
                                                                    case 1:
                                                                        ResultKt.throwOnFailure($result);
                                                                        break;
                                                                    default:
                                                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                                }
                                                                return Unit.INSTANCE;
                                                            }
                                                        }
                                                    });
                                                }
                                            } else if ($this$invoke_u24lambda_u240.getSwipeableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                                SemanticsPropertiesKt.collapse(semantics, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$2
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1($this$invoke_u24lambda_u240, null), 3, null);
                                                        return true;
                                                    }

                                                    /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$2$1, reason: invalid class name */
                                                    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                                    @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$2$1", f = "BottomSheetScaffold.kt", i = {}, l = {293}, m = "invokeSuspend", n = {}, s = {})
                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ SheetState $this_with;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                            super(2, continuation);
                                                            this.$this_with = sheetState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new AnonymousClass1(this.$this_with, continuation);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Object invokeSuspend(Object $result) {
                                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                            switch (this.label) {
                                                                case 0:
                                                                    ResultKt.throwOnFailure($result);
                                                                    this.label = 1;
                                                                    if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                        return coroutine_suspended;
                                                                    }
                                                                    break;
                                                                case 1:
                                                                    ResultKt.throwOnFailure($result);
                                                                    break;
                                                                default:
                                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                            }
                                                            return Unit.INSTANCE;
                                                        }
                                                    }
                                                });
                                            }
                                            if (!sheetState2.getSkipHiddenState()) {
                                                SemanticsPropertiesKt.dismiss(semantics, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$3
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1($this$invoke_u24lambda_u240, null), 3, null);
                                                        return true;
                                                    }

                                                    /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$3$1, reason: invalid class name */
                                                    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                                    @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$3$1", f = "BottomSheetScaffold.kt", i = {}, l = {299}, m = "invokeSuspend", n = {}, s = {})
                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ SheetState $this_with;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                            super(2, continuation);
                                                            this.$this_with = sheetState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new AnonymousClass1(this.$this_with, continuation);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Object invokeSuspend(Object $result) {
                                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                            switch (this.label) {
                                                                case 0:
                                                                    ResultKt.throwOnFailure($result);
                                                                    this.label = 1;
                                                                    if (this.$this_with.hide(this) == coroutine_suspended) {
                                                                        return coroutine_suspended;
                                                                    }
                                                                    break;
                                                                case 1:
                                                                    ResultKt.throwOnFailure($result);
                                                                    break;
                                                                default:
                                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                            }
                                                            return Unit.INSTANCE;
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                });
                                $composer3.startReplaceableGroup(733328855);
                                ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                int $changed$iv$iv2 = (0 << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume5 = $composer3.consume(localDensity3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv2 = (Density) objConsume5;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer3.consume(localLayoutDirection2);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume6;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume7 = $composer3.consume(localViewConfiguration2);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume7;
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                if (!($composer3.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer3.startReusableNode();
                                if ($composer3.getInserting()) {
                                    $composer3.createNode(constructor2);
                                } else {
                                    $composer3.useNode();
                                }
                                $composer3.disableReusing();
                                Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer3);
                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                $composer3.enableReusing();
                                function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                $composer3.startReplaceableGroup(2058660585);
                                int i6 = ($changed$iv$iv$iv2 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                int i7 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer3, 1084282841, "C306@13738L12:BottomSheetScaffold.kt#uh7d8r");
                                function4.invoke($composer3, Integer.valueOf((i4 >> 27) & 14));
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                            }
                            $composer3.endReplaceableGroup();
                            function5.invoke($this$invoke_u24lambda_u241, $composer3, Integer.valueOf(($changed3 & 14) | ((i3 << 3) & 112)));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, (($dirty3 >> 9) & 112) | 12582912 | (($dirty3 >> 9) & 896) | (($dirty3 >> 9) & 7168) | (($dirty3 >> 9) & 57344) | (($dirty3 >> 9) & 458752), 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            value$iv$iv2 = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(state, orientation, new Function1<Float, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                    invoke(f.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(float it) {
                    BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AnonymousClass1(state, it, null), 3, null);
                }

                /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$1$1$1, reason: invalid class name */
                /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$1$1$1", f = "BottomSheetScaffold.kt", i = {}, l = {240}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ float $it;
                    final /* synthetic */ SheetState $state;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(SheetState sheetState, float f, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$state = sheetState;
                        this.$it = f;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$state, this.$it, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                this.label = 1;
                                if (this.$state.settle$material3_release(this.$it, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                break;
                            case 1:
                                ResultKt.throwOnFailure($result);
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        return Unit.INSTANCE;
                    }
                }
            });
            $composer2.updateRememberedValue(value$iv$iv2);
            $composer2.endReplaceableGroup();
            Modifier modifierSwipeableV3 = SwipeableV2Kt.swipeableV2(NestedScrollModifierKt.nestedScroll$default(modifierM525requiredHeightInVpY3zN4$default, (NestedScrollConnection) value$iv$iv2, null, 2, null), state.getSwipeableState$material3_release(), orientation, (16 & 4) != 0 ? true : sheetSwipeEnabled, (16 & 8) != 0 ? false : false, (16 & 16) != 0 ? null : null);
            SwipeableV2State<SheetValue> swipeableState$material3_release2 = state.getSwipeableState$material3_release();
            Set of2 = SetsKt.setOf((Object[]) new SheetValue[]{SheetValue.Hidden, SheetValue.PartiallyExpanded, SheetValue.Expanded});
            Object key2$iv2 = Float.valueOf(layoutHeight);
            Object key3$iv2 = Float.valueOf(peekHeightPx);
            int i3 = ($dirty & 14) | (($dirty >> 6) & 112);
            final int $dirty4 = $dirty;
            $composer2.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(state) | $composer2.changed(key2$iv2) | $composer2.changed(key3$iv2);
            Object it$iv$iv4 = $composer2.rememberedValue();
            if (!invalid$iv$iv) {
            }
            value$iv$iv3 = (Function2) new Function2<SheetValue, IntSize, Float>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$2$1

                /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[SheetValue.values().length];
                        try {
                            iArr[SheetValue.PartiallyExpanded.ordinal()] = 1;
                        } catch (NoSuchFieldError e) {
                        }
                        try {
                            iArr[SheetValue.Expanded.ordinal()] = 2;
                        } catch (NoSuchFieldError e2) {
                        }
                        try {
                            iArr[SheetValue.Hidden.ordinal()] = 3;
                        } catch (NoSuchFieldError e3) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Float invoke(SheetValue sheetValue, IntSize intSize) {
                    return m1333invokeO0kMr_c(sheetValue, intSize.getPackedValue());
                }

                /* JADX INFO: renamed from: invoke-O0kMr_c, reason: not valid java name */
                public final Float m1333invokeO0kMr_c(SheetValue value, long sheetSize) {
                    Intrinsics.checkNotNullParameter(value, "value");
                    switch (WhenMappings.$EnumSwitchMapping$0[value.ordinal()]) {
                        case 1:
                            if (state.getSkipPartiallyExpanded()) {
                                return null;
                            }
                            return Float.valueOf(layoutHeight - peekHeightPx);
                        case 2:
                            if (IntSize.m5433getHeightimpl(sheetSize) == MathKt.roundToInt(peekHeightPx)) {
                                return null;
                            }
                            return Float.valueOf(Math.max(0.0f, layoutHeight - IntSize.m5433getHeightimpl(sheetSize)));
                        case 3:
                            if (state.getSkipHiddenState()) {
                                return null;
                            }
                            return Float.valueOf(layoutHeight);
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                }
            };
            $composer2.updateRememberedValue(value$iv$iv3);
            $composer2.endReplaceableGroup();
            SurfaceKt.m1806SurfaceT9BRK9s(SwipeableV2Kt.swipeAnchors(modifierSwipeableV3, swipeableState$material3_release2, of2, anchorChangeHandler, (Function2) value$iv$iv3), shape, containerColor, contentColor, tonalElevation, shadowElevation, null, ComposableLambdaKt.composableLambda($composer2, -1381492089, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3
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
                    ComposerKt.sourceInformation($composer3, "C270@11700L2114:BottomSheetScaffold.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1381492089, $changed2, -1, "androidx.compose.material3.StandardBottomSheet.<anonymous> (BottomSheetScaffold.kt:269)");
                        }
                        Modifier modifier$iv = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        Function2<Composer, Integer, Unit> function4 = function2;
                        Function3<ColumnScope, Composer, Integer, Unit> function5 = function3;
                        int i4 = $dirty2;
                        final SheetState sheetState = state;
                        final boolean z = sheetSwipeEnabled;
                        final CoroutineScope coroutineScope = scope;
                        int i5 = $dirty4;
                        $composer3.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                        int $changed$iv$iv = (6 << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer3.consume(localDensity2);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Density density$iv$iv = (Density) objConsume2;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer3.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume3;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume4 = $composer3.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume4;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            $composer3.createNode(constructor);
                        } else {
                            $composer3.useNode();
                        }
                        $composer3.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer3);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer3.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer3.startReplaceableGroup(2058660585);
                        int i6 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                        int $changed3 = ((6 >> 6) & 112) | 6;
                        ColumnScope $this$invoke_u24lambda_u241 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, -176229648, "C309@13795L9:BottomSheetScaffold.kt#uh7d8r");
                        $composer3.startReplaceableGroup(-176229648);
                        ComposerKt.sourceInformation($composer3, "273@11839L54,274@11935L48,275@12024L47,276@12088L1680");
                        if (function4 != null) {
                            final String partialExpandActionLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1738getBottomSheetPartialExpandDescriptionadMyvUU(), $composer3, 6);
                            final String dismissActionLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1734getBottomSheetDismissDescriptionadMyvUU(), $composer3, 6);
                            final String expandActionLabel = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1736getBottomSheetExpandDescriptionadMyvUU(), $composer3, 6);
                            Modifier modifier$iv2 = SemanticsModifierKt.semantics($this$invoke_u24lambda_u241.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally()), true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semantics) {
                                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                                    final SheetState $this$invoke_u24lambda_u240 = sheetState;
                                    boolean z2 = z;
                                    String str = expandActionLabel;
                                    String str2 = partialExpandActionLabel;
                                    SheetState sheetState2 = sheetState;
                                    String str3 = dismissActionLabel;
                                    final CoroutineScope coroutineScope2 = coroutineScope;
                                    if ($this$invoke_u24lambda_u240.getSwipeableState$material3_release().getAnchors$material3_release().size() > 1 && z2) {
                                        if ($this$invoke_u24lambda_u240.getCurrentValue() == SheetValue.PartiallyExpanded) {
                                            if ($this$invoke_u24lambda_u240.getSwipeableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.Expanded).booleanValue()) {
                                                SemanticsPropertiesKt.expand(semantics, str, new Function0<Boolean>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(0);
                                                    }

                                                    /* JADX WARN: Can't rename method to resolve collision */
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Boolean invoke() {
                                                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1($this$invoke_u24lambda_u240, null), 3, null);
                                                        return true;
                                                    }

                                                    /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$1, reason: invalid class name */
                                                    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                                    @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$1$1", f = "BottomSheetScaffold.kt", i = {}, l = {287}, m = "invokeSuspend", n = {}, s = {})
                                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                        final /* synthetic */ SheetState $this_with;
                                                        int label;

                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                            super(2, continuation);
                                                            this.$this_with = sheetState;
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                            return new AnonymousClass1(this.$this_with, continuation);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function2
                                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                        }

                                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                        public final Object invokeSuspend(Object $result) {
                                                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                            switch (this.label) {
                                                                case 0:
                                                                    ResultKt.throwOnFailure($result);
                                                                    this.label = 1;
                                                                    if (this.$this_with.expand(this) == coroutine_suspended) {
                                                                        return coroutine_suspended;
                                                                    }
                                                                    break;
                                                                case 1:
                                                                    ResultKt.throwOnFailure($result);
                                                                    break;
                                                                default:
                                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                            }
                                                            return Unit.INSTANCE;
                                                        }
                                                    }
                                                });
                                            }
                                        } else if ($this$invoke_u24lambda_u240.getSwipeableState$material3_release().getConfirmValueChange$material3_release().invoke(SheetValue.PartiallyExpanded).booleanValue()) {
                                            SemanticsPropertiesKt.collapse(semantics, str2, new Function0<Boolean>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$2
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1($this$invoke_u24lambda_u240, null), 3, null);
                                                    return true;
                                                }

                                                /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$2$1, reason: invalid class name */
                                                /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                                @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$2$1", f = "BottomSheetScaffold.kt", i = {}, l = {293}, m = "invokeSuspend", n = {}, s = {})
                                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ SheetState $this_with;
                                                    int label;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                        super(2, continuation);
                                                        this.$this_with = sheetState;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new AnonymousClass1(this.$this_with, continuation);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Object invokeSuspend(Object $result) {
                                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                        switch (this.label) {
                                                            case 0:
                                                                ResultKt.throwOnFailure($result);
                                                                this.label = 1;
                                                                if (this.$this_with.partialExpand(this) == coroutine_suspended) {
                                                                    return coroutine_suspended;
                                                                }
                                                                break;
                                                            case 1:
                                                                ResultKt.throwOnFailure($result);
                                                                break;
                                                            default:
                                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                        }
                                                        return Unit.INSTANCE;
                                                    }
                                                }
                                            });
                                        }
                                        if (!sheetState2.getSkipHiddenState()) {
                                            SemanticsPropertiesKt.dismiss(semantics, str3, new Function0<Boolean>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$3
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(0);
                                                }

                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Boolean invoke() {
                                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1($this$invoke_u24lambda_u240, null), 3, null);
                                                    return true;
                                                }

                                                /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$3$1, reason: invalid class name */
                                                /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                                                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                                                @DebugMetadata(c = "androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3$1$1$1$3$1", f = "BottomSheetScaffold.kt", i = {}, l = {299}, m = "invokeSuspend", n = {}, s = {})
                                                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ SheetState $this_with;
                                                    int label;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass1(SheetState sheetState, Continuation<? super AnonymousClass1> continuation) {
                                                        super(2, continuation);
                                                        this.$this_with = sheetState;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new AnonymousClass1(this.$this_with, continuation);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Object invokeSuspend(Object $result) {
                                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                        switch (this.label) {
                                                            case 0:
                                                                ResultKt.throwOnFailure($result);
                                                                this.label = 1;
                                                                if (this.$this_with.hide(this) == coroutine_suspended) {
                                                                    return coroutine_suspended;
                                                                }
                                                                break;
                                                            case 1:
                                                                ResultKt.throwOnFailure($result);
                                                                break;
                                                            default:
                                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                        }
                                                        return Unit.INSTANCE;
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }
                            });
                            $composer3.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv2 = (0 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume5 = $composer3.consume(localDensity3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv2 = (Density) objConsume5;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume6 = $composer3.consume(localLayoutDirection2);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume6;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume7 = $composer3.consume(localViewConfiguration2);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume7;
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                            if (!($composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                                $composer3.createNode(constructor2);
                            } else {
                                $composer3.useNode();
                            }
                            $composer3.disableReusing();
                            Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer3);
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                            $composer3.enableReusing();
                            function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                            $composer3.startReplaceableGroup(2058660585);
                            int i7 = ($changed$iv$iv$iv2 >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i8 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1084282841, "C306@13738L12:BottomSheetScaffold.kt#uh7d8r");
                            function4.invoke($composer3, Integer.valueOf((i5 >> 27) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                        }
                        $composer3.endReplaceableGroup();
                        function5.invoke($this$invoke_u24lambda_u241, $composer3, Integer.valueOf(($changed3 & 14) | ((i4 << 3) & 112)));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, (($dirty4 >> 9) & 112) | 12582912 | (($dirty4 >> 9) & 896) | (($dirty4 >> 9) & 7168) | (($dirty4 >> 9) & 57344) | (($dirty4 >> 9) & 458752), 64);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$4
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

            public final void invoke(Composer composer, int i4) {
                BottomSheetScaffoldKt.m1329StandardBottomSheet8oydGBM(state, peekHeight, sheetSwipeEnabled, layoutHeight, shape, containerColor, contentColor, tonalElevation, shadowElevation, function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: BottomSheetScaffoldLayout-PxNyym8, reason: not valid java name */
    public static final void m1328BottomSheetScaffoldLayoutPxNyym8(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function3<? super Integer, ? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final float sheetPeekHeight, final Function0<Float> function0, final SheetState sheetState, final long containerColor, final long contentColor, Composer $composer, final int $changed) {
        int i;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-1120561936);
        ComposerKt.sourceInformation($composer3, "C(BottomSheetScaffoldLayout)P(4,9!2,8,6:c#ui.unit.Dp,5,7,2:c#ui.graphics.Color,3:c#ui.graphics.Color)328@14299L1935,328@14282L1952:BottomSheetScaffold.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
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
            $dirty |= $composer3.changed(sheetPeekHeight) ? 131072 : 65536;
        }
        if ((3670016 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 1048576 : 524288;
        }
        if ((29360128 & $changed) == 0) {
            $dirty |= $composer3.changed(sheetState) ? 8388608 : 4194304;
        }
        if ((234881024 & $changed) == 0) {
            $dirty |= $composer3.changed(containerColor) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        final int $dirty2 = (1879048192 & $changed) == 0 ? $dirty | ($composer3.changed(contentColor) ? 536870912 : 268435456) : $dirty;
        if (($dirty2 & 1533916891) != 306783378 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1120561936, $dirty2, -1, "androidx.compose.material3.BottomSheetScaffoldLayout (BottomSheetScaffold.kt:316)");
            }
            Object[] keys$iv = {function4, function0, function2, modifier, Color.m2961boximpl(containerColor), Color.m2961boximpl(contentColor), function3, Dp.m5272boximpl(sheetPeekHeight), function5, sheetState};
            $composer3.startReplaceableGroup(-568225417);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv = false;
            for (Object key$iv : keys$iv) {
                invalid$iv |= $composer3.changed(key$iv);
            }
            Object value$iv$iv = $composer3.rememberedValue();
            if (invalid$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                i = 0;
                value$iv$iv = new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1

                    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
                    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[SheetValue.values().length];
                            try {
                                iArr[SheetValue.PartiallyExpanded.ordinal()] = 1;
                            } catch (NoSuchFieldError e) {
                            }
                            try {
                                iArr[SheetValue.Expanded.ordinal()] = 2;
                            } catch (NoSuchFieldError e2) {
                            }
                            try {
                                iArr[SheetValue.Hidden.ordinal()] = 3;
                            } catch (NoSuchFieldError e3) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                        return m1332invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                    }

                    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m1332invoke0kLqBqw(SubcomposeMeasureScope SubcomposeLayout, long constraints) {
                        Placeable placeableMo4225measureBRTryo0;
                        final int snackbarOffsetY;
                        Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                        int layoutWidth = Constraints.m5218getMaxWidthimpl(constraints);
                        final int layoutHeight = Constraints.m5217getMaxHeightimpl(constraints);
                        long looseConstraints = Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0);
                        BottomSheetScaffoldLayoutSlot bottomSheetScaffoldLayoutSlot = BottomSheetScaffoldLayoutSlot.Sheet;
                        final Function3<Integer, Composer, Integer, Unit> function6 = function4;
                        final int i2 = $dirty2;
                        final Placeable sheetPlaceable = SubcomposeLayout.subcompose(bottomSheetScaffoldLayoutSlot, ComposableLambdaKt.composableLambdaInstance(-1192048628, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1$sheetPlaceable$1
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
                                ComposerKt.sourceInformation($composer4, "C334@14581L25:BottomSheetScaffold.kt#uh7d8r");
                                if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1192048628, $changed2, -1, "androidx.compose.material3.BottomSheetScaffoldLayout.<anonymous>.<anonymous>.<anonymous> (BottomSheetScaffold.kt:333)");
                                }
                                function6.invoke(Integer.valueOf(layoutHeight), $composer4, Integer.valueOf((i2 >> 6) & 112));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        })).get(0).mo4225measureBRTryo0(looseConstraints);
                        final int sheetOffsetY = MathKt.roundToInt(function0.invoke().floatValue());
                        final int sheetOffsetX = Math.max(0, (layoutWidth - sheetPlaceable.getWidth()) / 2);
                        final Function2<Composer, Integer, Unit> function7 = function2;
                        if (function7 != null) {
                            final int i3 = $dirty2;
                            placeableMo4225measureBRTryo0 = SubcomposeLayout.subcompose(BottomSheetScaffoldLayoutSlot.TopBar, ComposableLambdaKt.composableLambdaInstance(-873203005, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1$topBarPlaceable$1$1
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
                                    ComposerKt.sourceInformation($composer4, "C340@14892L8:BottomSheetScaffold.kt#uh7d8r");
                                    if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                        $composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-873203005, $changed2, -1, "androidx.compose.material3.BottomSheetScaffoldLayout.<anonymous>.<anonymous>.<anonymous>.<anonymous> (BottomSheetScaffold.kt:340)");
                                    }
                                    function7.invoke($composer4, Integer.valueOf((i3 >> 3) & 14));
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            })).get(0).mo4225measureBRTryo0(looseConstraints);
                        } else {
                            placeableMo4225measureBRTryo0 = null;
                        }
                        final Placeable topBarPlaceable = placeableMo4225measureBRTryo0;
                        final int topBarHeight = topBarPlaceable != null ? topBarPlaceable.getHeight() : 0;
                        long bodyConstraints = Constraints.m5208copyZbe2FdA(looseConstraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(looseConstraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(looseConstraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(looseConstraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(looseConstraints) : layoutHeight - topBarHeight);
                        BottomSheetScaffoldLayoutSlot bottomSheetScaffoldLayoutSlot2 = BottomSheetScaffoldLayoutSlot.Body;
                        final Modifier modifier2 = modifier;
                        final long j = containerColor;
                        final long j2 = contentColor;
                        final int sheetOffsetX2 = $dirty2;
                        final Function3<PaddingValues, Composer, Integer, Unit> function8 = function3;
                        final float f = sheetPeekHeight;
                        final Placeable bodyPlaceable = SubcomposeLayout.subcompose(bottomSheetScaffoldLayoutSlot2, ComposableLambdaKt.composableLambdaInstance(-1459220575, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1$bodyPlaceable$1
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
                                ComposerKt.sourceInformation($composer4, "C347@15198L194:BottomSheetScaffold.kt#uh7d8r");
                                if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1459220575, $changed2, -1, "androidx.compose.material3.BottomSheetScaffoldLayout.<anonymous>.<anonymous>.<anonymous> (BottomSheetScaffold.kt:346)");
                                    }
                                    Modifier modifier3 = modifier2;
                                    long j3 = j;
                                    long j4 = j2;
                                    final Function3<PaddingValues, Composer, Integer, Unit> function9 = function8;
                                    final float f2 = f;
                                    final int i4 = sheetOffsetX2;
                                    ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, 1725620860, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1$bodyPlaceable$1.1
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

                                        public final void invoke(Composer $composer5, int $changed3) {
                                            ComposerKt.sourceInformation($composer5, "C351@15345L45:BottomSheetScaffold.kt#uh7d8r");
                                            if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                                $composer5.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1725620860, $changed3, -1, "androidx.compose.material3.BottomSheetScaffoldLayout.<anonymous>.<anonymous>.<anonymous>.<anonymous> (BottomSheetScaffold.kt:351)");
                                            }
                                            function9.invoke(PaddingKt.m484PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, f2, 7, null), $composer5, Integer.valueOf((i4 >> 3) & 112));
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    });
                                    int i5 = sheetOffsetX2;
                                    SurfaceKt.m1806SurfaceT9BRK9s(modifier3, null, j3, j4, 0.0f, 0.0f, null, composableLambda, $composer4, (i5 & 14) | 12582912 | ((i5 >> 18) & 896) | ((i5 >> 18) & 7168), 114);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        })).get(0).mo4225measureBRTryo0(bodyConstraints);
                        final Placeable snackbarPlaceable = SubcomposeLayout.subcompose(BottomSheetScaffoldLayoutSlot.Snackbar, function5).get(0).mo4225measureBRTryo0(looseConstraints);
                        final int snackbarOffsetX = (layoutWidth - snackbarPlaceable.getWidth()) / 2;
                        switch (WhenMappings.$EnumSwitchMapping$0[sheetState.getCurrentValue().ordinal()]) {
                            case 1:
                                snackbarOffsetY = sheetOffsetY - snackbarPlaceable.getHeight();
                                break;
                            case 2:
                            case 3:
                                snackbarOffsetY = layoutHeight - snackbarPlaceable.getHeight();
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                        return MeasureScope.CC.layout$default(SubcomposeLayout, layoutWidth, layoutHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                Placeable.PlacementScope.placeRelative$default(layout, bodyPlaceable, 0, topBarHeight, 0.0f, 4, null);
                                Placeable placeable = topBarPlaceable;
                                if (placeable != null) {
                                    Placeable.PlacementScope.placeRelative$default(layout, placeable, 0, 0, 0.0f, 4, null);
                                }
                                Placeable.PlacementScope.placeRelative$default(layout, sheetPlaceable, sheetOffsetX, sheetOffsetY, 0.0f, 4, null);
                                Placeable.PlacementScope.placeRelative$default(layout, snackbarPlaceable, snackbarOffsetX, snackbarOffsetY, 0.0f, 4, null);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2
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
                BottomSheetScaffoldKt.m1328BottomSheetScaffoldLayoutPxNyym8(modifier, function2, function3, function4, function5, sheetPeekHeight, function0, sheetState, containerColor, contentColor, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnchorChangeHandler<SheetValue> BottomSheetScaffoldAnchorChangeHandler(final SheetState state, final Function2<? super SheetValue, ? super Float, Unit> function2, final Function1<? super SheetValue, Unit> function1) {
        return new AnchorChangeHandler<SheetValue>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt.BottomSheetScaffoldAnchorChangeHandler.1

            /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldAnchorChangeHandler$1$WhenMappings */
            /* JADX INFO: compiled from: BottomSheetScaffold.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[SheetValue.values().length];
                    try {
                        iArr[SheetValue.Hidden.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[SheetValue.Expanded.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // androidx.compose.material3.AnchorChangeHandler
            public final void onAnchorsChanged(SheetValue previousTarget, Map<SheetValue, Float> previousAnchors, Map<SheetValue, Float> newAnchors) {
                SheetValue newTarget;
                Intrinsics.checkNotNullParameter(previousTarget, "previousTarget");
                Intrinsics.checkNotNullParameter(previousAnchors, "previousAnchors");
                Intrinsics.checkNotNullParameter(newAnchors, "newAnchors");
                Float previousTargetOffset = previousAnchors.get(previousTarget);
                switch (WhenMappings.$EnumSwitchMapping$0[previousTarget.ordinal()]) {
                    case 1:
                    case 2:
                        newTarget = SheetValue.PartiallyExpanded;
                        break;
                    case 3:
                        newTarget = !newAnchors.containsKey(SheetValue.Expanded) ? SheetValue.PartiallyExpanded : SheetValue.Expanded;
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                float newTargetOffset = ((Number) MapsKt.getValue(newAnchors, newTarget)).floatValue();
                if (!Intrinsics.areEqual(newTargetOffset, previousTargetOffset)) {
                    if (state.getSwipeableState$material3_release().isAnimationRunning()) {
                        function2.invoke(newTarget, Float.valueOf(state.getSwipeableState$material3_release().getLastVelocity()));
                    } else {
                        function1.invoke(newTarget);
                    }
                }
            }
        };
    }
}
