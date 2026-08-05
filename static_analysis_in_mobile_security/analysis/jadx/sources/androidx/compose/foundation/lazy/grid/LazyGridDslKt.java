package androidx.compose.foundation.lazy.grid;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: LazyGridDsl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a~\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a~\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u001a\u001a&\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001dH\u0002\u001a?\u0010!\u001a\u0019\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0\"¢\u0006\u0002\b\u00162\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0003ø\u0001\u0000¢\u0006\u0002\u0010&\u001a?\u0010'\u001a\u0019\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0\"¢\u0006\u0002\b\u00162\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\tH\u0003ø\u0001\u0000¢\u0006\u0002\u0010(\u001aá\u0001\u0010)\u001a\u00020\u0001\"\u0004\b\u0000\u0010**\u00020\u00152\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H*0+2%\b\n\u0010,\u001a\u001f\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010\u001420\b\n\u00101\u001a*\u0012\u0004\u0012\u000202\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000203\u0018\u00010\"¢\u0006\u0002\b\u00162%\b\n\u00104\u001a\u001f\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0006\u0012\u0004\u0018\u0001000\u001423\b\u0004\u00105\u001a-\u0012\u0004\u0012\u000206\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00010\"¢\u0006\u0002\b7¢\u0006\u0002\b\u0016H\u0086\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\u00108\u001aá\u0001\u0010)\u001a\u00020\u0001\"\u0004\b\u0000\u0010**\u00020\u00152\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H*0\u001c2%\b\n\u0010,\u001a\u001f\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010\u001420\b\n\u00101\u001a*\u0012\u0004\u0012\u000202\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000203\u0018\u00010\"¢\u0006\u0002\b\u00162%\b\n\u00104\u001a\u001f\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0006\u0012\u0004\u0018\u0001000\u001423\b\u0004\u00105\u001a-\u0012\u0004\u0012\u000206\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00010\"¢\u0006\u0002\b7¢\u0006\u0002\b\u0016H\u0086\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\u00109\u001aµ\u0002\u0010:\u001a\u00020\u0001\"\u0004\b\u0000\u0010**\u00020\u00152\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H*0+2:\b\n\u0010,\u001a4\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010\"2E\b\n\u00101\u001a?\u0012\u0004\u0012\u000202\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000203\u0018\u00010<¢\u0006\u0002\b\u00162:\b\u0006\u00104\u001a4\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0006\u0012\u0004\u0018\u0001000\"2H\b\u0004\u00105\u001aB\u0012\u0004\u0012\u000206\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00010<¢\u0006\u0002\b7¢\u0006\u0002\b\u0016H\u0086\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\u0010=\u001aµ\u0002\u0010:\u001a\u00020\u0001\"\u0004\b\u0000\u0010**\u00020\u00152\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H*0\u001c2:\b\n\u0010,\u001a4\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010\"2E\b\n\u00101\u001a?\u0012\u0004\u0012\u000202\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000203\u0018\u00010<¢\u0006\u0002\b\u00162:\b\u0006\u00104\u001a4\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0006\u0012\u0004\u0018\u0001000\"2H\b\u0004\u00105\u001aB\u0012\u0004\u0012\u000206\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H*¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00010<¢\u0006\u0002\b7¢\u0006\u0002\b\u0016H\u0086\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\u0010>\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u0006?"}, d2 = {"LazyHorizontalGrid", "", "rows", "Landroidx/compose/foundation/lazy/grid/GridCells;", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/LazyGridScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/grid/GridCells;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LazyVerticalGrid", "columns", "(Landroidx/compose/foundation/lazy/grid/GridCells;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "calculateCellsCrossAxisSizeImpl", "", "", "gridSize", "slotCount", "spacing", "rememberColumnWidthSums", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/foundation/lazy/grid/LazyGridSlots;", "(Landroidx/compose/foundation/lazy/grid/GridCells;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "rememberRowHeightSums", "(Landroidx/compose/foundation/lazy/grid/GridCells;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "items", "T", "", "key", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "item", "", "span", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "contentType", "itemContent", "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/lazy/grid/LazyGridScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "(Landroidx/compose/foundation/lazy/grid/LazyGridScope;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "itemsIndexed", "index", "Lkotlin/Function3;", "(Landroidx/compose/foundation/lazy/grid/LazyGridScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/grid/LazyGridScope;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyGridDslKt {
    /* JADX WARN: Code duplicated, block: B:116:0x0171  */
    /* JADX WARN: Code duplicated, block: B:118:0x017b  */
    /* JADX WARN: Code duplicated, block: B:131:0x01b2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:132:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:133:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:136:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:137:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:139:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:140:0x01db  */
    /* JADX WARN: Code duplicated, block: B:142:0x01df  */
    /* JADX WARN: Code duplicated, block: B:145:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:147:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:148:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:151:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:154:0x0204  */
    /* JADX WARN: Code duplicated, block: B:155:0x0210  */
    /* JADX WARN: Code duplicated, block: B:157:0x0214  */
    /* JADX WARN: Code duplicated, block: B:158:0x021d  */
    /* JADX WARN: Code duplicated, block: B:161:0x022e  */
    /* JADX WARN: Code duplicated, block: B:164:0x029c  */
    /* JADX WARN: Code duplicated, block: B:168:0x02b5  */
    /* JADX WARN: Code duplicated, block: B:169:0x02b8  */
    public static final void LazyVerticalGrid(final GridCells columns, Modifier modifier, LazyGridState state, PaddingValues contentPadding, boolean reverseLayout, Arrangement.Vertical verticalArrangement, Arrangement.Horizontal horizontalArrangement, FlingBehavior flingBehavior, boolean userScrollEnabled, final Function1<? super LazyGridScope, Unit> content, Composer $composer, final int $changed, final int i) {
        PaddingValues contentPadding2;
        boolean reverseLayout2;
        Arrangement.Vertical verticalArrangement2;
        Arrangement.Horizontal horizontalArrangement2;
        int i2;
        Modifier.Companion modifier2;
        LazyGridState state2;
        FlingBehavior flingBehavior2;
        Modifier modifier3;
        boolean userScrollEnabled2;
        int $dirty;
        FlingBehavior flingBehavior3;
        boolean reverseLayout3;
        Arrangement.Vertical verticalArrangement3;
        Arrangement arrangement;
        Arrangement.Vertical verticalArrangement4;
        Modifier modifier4;
        boolean reverseLayout4;
        Arrangement.Vertical verticalArrangement5;
        LazyGridState state3;
        FlingBehavior flingBehavior4;
        boolean userScrollEnabled3;
        PaddingValues contentPadding3;
        Arrangement.Horizontal horizontalArrangement3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(columns, "columns");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1485410512);
        ComposerKt.sourceInformation($composer2, "C(LazyVerticalGrid)P(!1,5,7,2,6,9,4,3,8)64@2980L23,70@3340L15,75@3468L71,74@3442L481:LazyGridDsl.kt#7791vq");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(columns) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty2 |= ((i & 4) == 0 && $composer2.changed(state)) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            contentPadding2 = contentPadding;
        } else if (($changed & 7168) == 0) {
            contentPadding2 = contentPadding;
            $dirty2 |= $composer2.changed(contentPadding2) ? 2048 : 1024;
        } else {
            contentPadding2 = contentPadding;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            reverseLayout2 = reverseLayout;
        } else if (($changed & 57344) == 0) {
            reverseLayout2 = reverseLayout;
            $dirty2 |= $composer2.changed(reverseLayout2) ? 16384 : 8192;
        } else {
            reverseLayout2 = reverseLayout;
        }
        if ((458752 & $changed) == 0) {
            if ((i & 32) == 0) {
                verticalArrangement2 = verticalArrangement;
                int i6 = $composer2.changed(verticalArrangement2) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                verticalArrangement2 = verticalArrangement;
            }
            $dirty2 |= i6;
        } else {
            verticalArrangement2 = verticalArrangement;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            horizontalArrangement2 = horizontalArrangement;
        } else if (($changed & 3670016) == 0) {
            horizontalArrangement2 = horizontalArrangement;
            $dirty2 |= $composer2.changed(horizontalArrangement2) ? 1048576 : 524288;
        } else {
            horizontalArrangement2 = horizontalArrangement;
        }
        if (($changed & 29360128) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer2.changed(flingBehavior)) ? 8388608 : 4194304;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty2 |= $composer2.changed(userScrollEnabled) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) == 0) {
            if (($changed & 1879048192) == 0) {
                i2 = $composer2.changedInstance(content) ? 536870912 : 268435456;
            }
            if ((1533916891 & $dirty2) == 306783378 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        state2 = LazyGridStateKt.rememberLazyGridState(0, 0, $composer2, 0, 3);
                        $dirty2 &= -897;
                    } else {
                        state2 = state;
                    }
                    if (i4 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    }
                    if (i5 != 0) {
                        reverseLayout2 = false;
                    }
                    if ((i & 32) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (reverseLayout2) {
                            verticalArrangement4 = arrangement.getBottom();
                        } else {
                            verticalArrangement4 = arrangement.getTop();
                        }
                        $dirty2 &= -458753;
                        verticalArrangement2 = verticalArrangement4;
                    }
                    if (i7 != 0) {
                        horizontalArrangement2 = Arrangement.INSTANCE.getStart();
                    }
                    if ((i & 128) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                        $dirty2 &= -29360129;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    if (i8 != 0) {
                        userScrollEnabled2 = true;
                        $dirty = $dirty2;
                        flingBehavior3 = flingBehavior2;
                        reverseLayout3 = reverseLayout2;
                        verticalArrangement3 = verticalArrangement2;
                        modifier3 = modifier2;
                    } else {
                        modifier3 = modifier2;
                        userScrollEnabled2 = userScrollEnabled;
                        $dirty = $dirty2;
                        flingBehavior3 = flingBehavior2;
                        reverseLayout3 = reverseLayout2;
                        verticalArrangement3 = verticalArrangement2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                    }
                    if ((i & 128) != 0) {
                        state2 = state;
                        flingBehavior3 = flingBehavior;
                        userScrollEnabled2 = userScrollEnabled;
                        $dirty = (-29360129) & $dirty2;
                        reverseLayout3 = reverseLayout2;
                        verticalArrangement3 = verticalArrangement2;
                        modifier3 = modifier;
                    } else {
                        modifier3 = modifier;
                        state2 = state;
                        flingBehavior3 = flingBehavior;
                        userScrollEnabled2 = userScrollEnabled;
                        $dirty = $dirty2;
                        reverseLayout3 = reverseLayout2;
                        verticalArrangement3 = verticalArrangement2;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1485410512, $dirty, -1, "androidx.compose.foundation.lazy.grid.LazyVerticalGrid (LazyGridDsl.kt:61)");
                }
                LazyGridKt.LazyGrid(modifier3, state2, rememberColumnWidthSums(columns, horizontalArrangement2, contentPadding2, $composer2, ($dirty & 14) | (($dirty >> 15) & 112) | (($dirty >> 3) & 896)), contentPadding2, reverseLayout3, true, flingBehavior3, userScrollEnabled2, verticalArrangement3, horizontalArrangement2, content, $composer2, (($dirty >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (($dirty >> 3) & 112) | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 9) & 234881024) | (($dirty << 9) & 1879048192), ($dirty >> 27) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                reverseLayout4 = reverseLayout3;
                verticalArrangement5 = verticalArrangement3;
                state3 = state2;
                flingBehavior4 = flingBehavior3;
                userScrollEnabled3 = userScrollEnabled2;
                contentPadding3 = contentPadding2;
                horizontalArrangement3 = horizontalArrangement2;
            } else {
                $composer2.skipToGroupEnd();
                flingBehavior4 = flingBehavior;
                userScrollEnabled3 = userScrollEnabled;
                contentPadding3 = contentPadding2;
                horizontalArrangement3 = horizontalArrangement2;
                reverseLayout4 = reverseLayout2;
                verticalArrangement5 = verticalArrangement2;
                modifier4 = modifier;
                state3 = state;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final LazyGridState lazyGridState = state3;
            final PaddingValues paddingValues = contentPadding3;
            final boolean z = reverseLayout4;
            final Arrangement.Vertical vertical = verticalArrangement5;
            final Arrangement.Horizontal horizontal = horizontalArrangement3;
            final FlingBehavior flingBehavior5 = flingBehavior4;
            final boolean z2 = userScrollEnabled3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt.LazyVerticalGrid.1
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

                public final void invoke(Composer composer, int i9) {
                    LazyGridDslKt.LazyVerticalGrid(columns, modifier5, lazyGridState, paddingValues, z, vertical, horizontal, flingBehavior5, z2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty2 |= i2;
        if ((1533916891 & $dirty2) == 306783378) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    state2 = LazyGridStateKt.rememberLazyGridState(0, 0, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    state2 = state;
                }
                if (i4 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                }
                if (i5 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 32) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (reverseLayout2) {
                        verticalArrangement4 = arrangement.getTop();
                    } else {
                        verticalArrangement4 = arrangement.getBottom();
                    }
                    $dirty2 &= -458753;
                    verticalArrangement2 = verticalArrangement4;
                }
                if (i7 != 0) {
                    horizontalArrangement2 = Arrangement.INSTANCE.getStart();
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty2 &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i8 != 0) {
                    userScrollEnabled2 = true;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    verticalArrangement3 = verticalArrangement2;
                    modifier3 = modifier2;
                } else {
                    modifier3 = modifier2;
                    userScrollEnabled2 = userScrollEnabled;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    verticalArrangement3 = verticalArrangement2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    state2 = LazyGridStateKt.rememberLazyGridState(0, 0, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    state2 = state;
                }
                if (i4 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                }
                if (i5 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 32) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (reverseLayout2) {
                        verticalArrangement4 = arrangement.getTop();
                    } else {
                        verticalArrangement4 = arrangement.getBottom();
                    }
                    $dirty2 &= -458753;
                    verticalArrangement2 = verticalArrangement4;
                }
                if (i7 != 0) {
                    horizontalArrangement2 = Arrangement.INSTANCE.getStart();
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty2 &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i8 != 0) {
                    userScrollEnabled2 = true;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    verticalArrangement3 = verticalArrangement2;
                    modifier3 = modifier2;
                } else {
                    modifier3 = modifier2;
                    userScrollEnabled2 = userScrollEnabled;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    verticalArrangement3 = verticalArrangement2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1485410512, $dirty, -1, "androidx.compose.foundation.lazy.grid.LazyVerticalGrid (LazyGridDsl.kt:61)");
            }
            LazyGridKt.LazyGrid(modifier3, state2, rememberColumnWidthSums(columns, horizontalArrangement2, contentPadding2, $composer2, ($dirty & 14) | (($dirty >> 15) & 112) | (($dirty >> 3) & 896)), contentPadding2, reverseLayout3, true, flingBehavior3, userScrollEnabled2, verticalArrangement3, horizontalArrangement2, content, $composer2, (($dirty >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (($dirty >> 3) & 112) | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 9) & 234881024) | (($dirty << 9) & 1879048192), ($dirty >> 27) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            reverseLayout4 = reverseLayout3;
            verticalArrangement5 = verticalArrangement3;
            state3 = state2;
            flingBehavior4 = flingBehavior3;
            userScrollEnabled3 = userScrollEnabled2;
            contentPadding3 = contentPadding2;
            horizontalArrangement3 = horizontalArrangement2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    state2 = LazyGridStateKt.rememberLazyGridState(0, 0, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    state2 = state;
                }
                if (i4 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                }
                if (i5 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 32) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (reverseLayout2) {
                        verticalArrangement4 = arrangement.getTop();
                    } else {
                        verticalArrangement4 = arrangement.getBottom();
                    }
                    $dirty2 &= -458753;
                    verticalArrangement2 = verticalArrangement4;
                }
                if (i7 != 0) {
                    horizontalArrangement2 = Arrangement.INSTANCE.getStart();
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty2 &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i8 != 0) {
                    userScrollEnabled2 = true;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    verticalArrangement3 = verticalArrangement2;
                    modifier3 = modifier2;
                } else {
                    modifier3 = modifier2;
                    userScrollEnabled2 = userScrollEnabled;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    verticalArrangement3 = verticalArrangement2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    state2 = LazyGridStateKt.rememberLazyGridState(0, 0, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    state2 = state;
                }
                if (i4 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                }
                if (i5 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 32) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (reverseLayout2) {
                        verticalArrangement4 = arrangement.getTop();
                    } else {
                        verticalArrangement4 = arrangement.getBottom();
                    }
                    $dirty2 &= -458753;
                    verticalArrangement2 = verticalArrangement4;
                }
                if (i7 != 0) {
                    horizontalArrangement2 = Arrangement.INSTANCE.getStart();
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty2 &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i8 != 0) {
                    userScrollEnabled2 = true;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    verticalArrangement3 = verticalArrangement2;
                    modifier3 = modifier2;
                } else {
                    modifier3 = modifier2;
                    userScrollEnabled2 = userScrollEnabled;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    verticalArrangement3 = verticalArrangement2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1485410512, $dirty, -1, "androidx.compose.foundation.lazy.grid.LazyVerticalGrid (LazyGridDsl.kt:61)");
            }
            LazyGridKt.LazyGrid(modifier3, state2, rememberColumnWidthSums(columns, horizontalArrangement2, contentPadding2, $composer2, ($dirty & 14) | (($dirty >> 15) & 112) | (($dirty >> 3) & 896)), contentPadding2, reverseLayout3, true, flingBehavior3, userScrollEnabled2, verticalArrangement3, horizontalArrangement2, content, $composer2, (($dirty >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (($dirty >> 3) & 112) | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 9) & 234881024) | (($dirty << 9) & 1879048192), ($dirty >> 27) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            reverseLayout4 = reverseLayout3;
            verticalArrangement5 = verticalArrangement3;
            state3 = state2;
            flingBehavior4 = flingBehavior3;
            userScrollEnabled3 = userScrollEnabled2;
            contentPadding3 = contentPadding2;
            horizontalArrangement3 = horizontalArrangement2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final LazyGridState lazyGridState2 = state3;
        final PaddingValues paddingValues2 = contentPadding3;
        final boolean z3 = reverseLayout4;
        final Arrangement.Vertical vertical2 = verticalArrangement5;
        final Arrangement.Horizontal horizontal2 = horizontalArrangement3;
        final FlingBehavior flingBehavior6 = flingBehavior4;
        final boolean z4 = userScrollEnabled3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt.LazyVerticalGrid.1
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

            public final void invoke(Composer composer, int i9) {
                LazyGridDslKt.LazyVerticalGrid(columns, modifier6, lazyGridState2, paddingValues2, z3, vertical2, horizontal2, flingBehavior6, z4, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:116:0x0172  */
    /* JADX WARN: Code duplicated, block: B:118:0x017c  */
    /* JADX WARN: Code duplicated, block: B:131:0x01b3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:132:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:133:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:136:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:137:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:139:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:140:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:142:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:145:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:147:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:148:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:151:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:154:0x0205  */
    /* JADX WARN: Code duplicated, block: B:155:0x0211  */
    /* JADX WARN: Code duplicated, block: B:157:0x0215  */
    /* JADX WARN: Code duplicated, block: B:158:0x021e  */
    /* JADX WARN: Code duplicated, block: B:161:0x022f  */
    /* JADX WARN: Code duplicated, block: B:164:0x029d  */
    /* JADX WARN: Code duplicated, block: B:168:0x02b6  */
    /* JADX WARN: Code duplicated, block: B:169:0x02b9  */
    public static final void LazyHorizontalGrid(final GridCells rows, Modifier modifier, LazyGridState state, PaddingValues contentPadding, boolean reverseLayout, Arrangement.Horizontal horizontalArrangement, Arrangement.Vertical verticalArrangement, FlingBehavior flingBehavior, boolean userScrollEnabled, final Function1<? super LazyGridScope, Unit> content, Composer $composer, final int $changed, final int i) {
        PaddingValues contentPadding2;
        boolean reverseLayout2;
        Arrangement.Horizontal horizontalArrangement2;
        Arrangement.Vertical verticalArrangement2;
        int i2;
        Modifier.Companion modifier2;
        LazyGridState state2;
        FlingBehavior flingBehavior2;
        Modifier modifier3;
        boolean userScrollEnabled2;
        int $dirty;
        FlingBehavior flingBehavior3;
        boolean reverseLayout3;
        Arrangement.Horizontal horizontalArrangement3;
        Arrangement arrangement;
        Arrangement.Horizontal horizontalArrangement4;
        Modifier modifier4;
        boolean reverseLayout4;
        Arrangement.Horizontal horizontalArrangement5;
        LazyGridState state3;
        FlingBehavior flingBehavior4;
        boolean userScrollEnabled3;
        PaddingValues contentPadding3;
        Arrangement.Vertical verticalArrangement3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(rows, "rows");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(2123608858);
        ComposerKt.sourceInformation($composer2, "C(LazyHorizontalGrid)P(6,4,7,1,5,3,9,2,8)117@5477L23,123@5834L15,128@5962L64,127@5936L475:LazyGridDsl.kt#7791vq");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(rows) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty2 |= ((i & 4) == 0 && $composer2.changed(state)) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            contentPadding2 = contentPadding;
        } else if (($changed & 7168) == 0) {
            contentPadding2 = contentPadding;
            $dirty2 |= $composer2.changed(contentPadding2) ? 2048 : 1024;
        } else {
            contentPadding2 = contentPadding;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            reverseLayout2 = reverseLayout;
        } else if (($changed & 57344) == 0) {
            reverseLayout2 = reverseLayout;
            $dirty2 |= $composer2.changed(reverseLayout2) ? 16384 : 8192;
        } else {
            reverseLayout2 = reverseLayout;
        }
        if ((458752 & $changed) == 0) {
            if ((i & 32) == 0) {
                horizontalArrangement2 = horizontalArrangement;
                int i6 = $composer2.changed(horizontalArrangement2) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                horizontalArrangement2 = horizontalArrangement;
            }
            $dirty2 |= i6;
        } else {
            horizontalArrangement2 = horizontalArrangement;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            verticalArrangement2 = verticalArrangement;
        } else if (($changed & 3670016) == 0) {
            verticalArrangement2 = verticalArrangement;
            $dirty2 |= $composer2.changed(verticalArrangement2) ? 1048576 : 524288;
        } else {
            verticalArrangement2 = verticalArrangement;
        }
        if (($changed & 29360128) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer2.changed(flingBehavior)) ? 8388608 : 4194304;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty2 |= $composer2.changed(userScrollEnabled) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) == 0) {
            if (($changed & 1879048192) == 0) {
                i2 = $composer2.changedInstance(content) ? 536870912 : 268435456;
            }
            if ((1533916891 & $dirty2) == 306783378 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        state2 = LazyGridStateKt.rememberLazyGridState(0, 0, $composer2, 0, 3);
                        $dirty2 &= -897;
                    } else {
                        state2 = state;
                    }
                    if (i4 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    }
                    if (i5 != 0) {
                        reverseLayout2 = false;
                    }
                    if ((i & 32) != 0) {
                        arrangement = Arrangement.INSTANCE;
                        if (reverseLayout2) {
                            horizontalArrangement4 = arrangement.getEnd();
                        } else {
                            horizontalArrangement4 = arrangement.getStart();
                        }
                        $dirty2 &= -458753;
                        horizontalArrangement2 = horizontalArrangement4;
                    }
                    if (i7 != 0) {
                        verticalArrangement2 = Arrangement.INSTANCE.getTop();
                    }
                    if ((i & 128) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                        $dirty2 &= -29360129;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    if (i8 != 0) {
                        userScrollEnabled2 = true;
                        $dirty = $dirty2;
                        flingBehavior3 = flingBehavior2;
                        reverseLayout3 = reverseLayout2;
                        horizontalArrangement3 = horizontalArrangement2;
                        modifier3 = modifier2;
                    } else {
                        modifier3 = modifier2;
                        userScrollEnabled2 = userScrollEnabled;
                        $dirty = $dirty2;
                        flingBehavior3 = flingBehavior2;
                        reverseLayout3 = reverseLayout2;
                        horizontalArrangement3 = horizontalArrangement2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                    }
                    if ((i & 128) != 0) {
                        state2 = state;
                        flingBehavior3 = flingBehavior;
                        userScrollEnabled2 = userScrollEnabled;
                        $dirty = (-29360129) & $dirty2;
                        reverseLayout3 = reverseLayout2;
                        horizontalArrangement3 = horizontalArrangement2;
                        modifier3 = modifier;
                    } else {
                        modifier3 = modifier;
                        state2 = state;
                        flingBehavior3 = flingBehavior;
                        userScrollEnabled2 = userScrollEnabled;
                        $dirty = $dirty2;
                        reverseLayout3 = reverseLayout2;
                        horizontalArrangement3 = horizontalArrangement2;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2123608858, $dirty, -1, "androidx.compose.foundation.lazy.grid.LazyHorizontalGrid (LazyGridDsl.kt:114)");
                }
                LazyGridKt.LazyGrid(modifier3, state2, rememberRowHeightSums(rows, verticalArrangement2, contentPadding2, $composer2, ($dirty & 14) | (($dirty >> 15) & 112) | (($dirty >> 3) & 896)), contentPadding2, reverseLayout3, false, flingBehavior3, userScrollEnabled2, verticalArrangement2, horizontalArrangement3, content, $composer2, (($dirty >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (($dirty >> 3) & 112) | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 6) & 234881024) | (($dirty << 12) & 1879048192), ($dirty >> 27) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                reverseLayout4 = reverseLayout3;
                horizontalArrangement5 = horizontalArrangement3;
                state3 = state2;
                flingBehavior4 = flingBehavior3;
                userScrollEnabled3 = userScrollEnabled2;
                contentPadding3 = contentPadding2;
                verticalArrangement3 = verticalArrangement2;
            } else {
                $composer2.skipToGroupEnd();
                flingBehavior4 = flingBehavior;
                userScrollEnabled3 = userScrollEnabled;
                contentPadding3 = contentPadding2;
                verticalArrangement3 = verticalArrangement2;
                reverseLayout4 = reverseLayout2;
                horizontalArrangement5 = horizontalArrangement2;
                modifier4 = modifier;
                state3 = state;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final LazyGridState lazyGridState = state3;
            final PaddingValues paddingValues = contentPadding3;
            final boolean z = reverseLayout4;
            final Arrangement.Horizontal horizontal = horizontalArrangement5;
            final Arrangement.Vertical vertical = verticalArrangement3;
            final FlingBehavior flingBehavior5 = flingBehavior4;
            final boolean z2 = userScrollEnabled3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt.LazyHorizontalGrid.1
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

                public final void invoke(Composer composer, int i9) {
                    LazyGridDslKt.LazyHorizontalGrid(rows, modifier5, lazyGridState, paddingValues, z, horizontal, vertical, flingBehavior5, z2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty2 |= i2;
        if ((1533916891 & $dirty2) == 306783378) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    state2 = LazyGridStateKt.rememberLazyGridState(0, 0, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    state2 = state;
                }
                if (i4 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                }
                if (i5 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 32) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (reverseLayout2) {
                        horizontalArrangement4 = arrangement.getStart();
                    } else {
                        horizontalArrangement4 = arrangement.getEnd();
                    }
                    $dirty2 &= -458753;
                    horizontalArrangement2 = horizontalArrangement4;
                }
                if (i7 != 0) {
                    verticalArrangement2 = Arrangement.INSTANCE.getTop();
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty2 &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i8 != 0) {
                    userScrollEnabled2 = true;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    horizontalArrangement3 = horizontalArrangement2;
                    modifier3 = modifier2;
                } else {
                    modifier3 = modifier2;
                    userScrollEnabled2 = userScrollEnabled;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    horizontalArrangement3 = horizontalArrangement2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    state2 = LazyGridStateKt.rememberLazyGridState(0, 0, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    state2 = state;
                }
                if (i4 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                }
                if (i5 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 32) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (reverseLayout2) {
                        horizontalArrangement4 = arrangement.getStart();
                    } else {
                        horizontalArrangement4 = arrangement.getEnd();
                    }
                    $dirty2 &= -458753;
                    horizontalArrangement2 = horizontalArrangement4;
                }
                if (i7 != 0) {
                    verticalArrangement2 = Arrangement.INSTANCE.getTop();
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty2 &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i8 != 0) {
                    userScrollEnabled2 = true;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    horizontalArrangement3 = horizontalArrangement2;
                    modifier3 = modifier2;
                } else {
                    modifier3 = modifier2;
                    userScrollEnabled2 = userScrollEnabled;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    horizontalArrangement3 = horizontalArrangement2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2123608858, $dirty, -1, "androidx.compose.foundation.lazy.grid.LazyHorizontalGrid (LazyGridDsl.kt:114)");
            }
            LazyGridKt.LazyGrid(modifier3, state2, rememberRowHeightSums(rows, verticalArrangement2, contentPadding2, $composer2, ($dirty & 14) | (($dirty >> 15) & 112) | (($dirty >> 3) & 896)), contentPadding2, reverseLayout3, false, flingBehavior3, userScrollEnabled2, verticalArrangement2, horizontalArrangement3, content, $composer2, (($dirty >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (($dirty >> 3) & 112) | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 6) & 234881024) | (($dirty << 12) & 1879048192), ($dirty >> 27) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            reverseLayout4 = reverseLayout3;
            horizontalArrangement5 = horizontalArrangement3;
            state3 = state2;
            flingBehavior4 = flingBehavior3;
            userScrollEnabled3 = userScrollEnabled2;
            contentPadding3 = contentPadding2;
            verticalArrangement3 = verticalArrangement2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    state2 = LazyGridStateKt.rememberLazyGridState(0, 0, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    state2 = state;
                }
                if (i4 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                }
                if (i5 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 32) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (reverseLayout2) {
                        horizontalArrangement4 = arrangement.getStart();
                    } else {
                        horizontalArrangement4 = arrangement.getEnd();
                    }
                    $dirty2 &= -458753;
                    horizontalArrangement2 = horizontalArrangement4;
                }
                if (i7 != 0) {
                    verticalArrangement2 = Arrangement.INSTANCE.getTop();
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty2 &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i8 != 0) {
                    userScrollEnabled2 = true;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    horizontalArrangement3 = horizontalArrangement2;
                    modifier3 = modifier2;
                } else {
                    modifier3 = modifier2;
                    userScrollEnabled2 = userScrollEnabled;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    horizontalArrangement3 = horizontalArrangement2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    state2 = LazyGridStateKt.rememberLazyGridState(0, 0, $composer2, 0, 3);
                    $dirty2 &= -897;
                } else {
                    state2 = state;
                }
                if (i4 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                }
                if (i5 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 32) != 0) {
                    arrangement = Arrangement.INSTANCE;
                    if (reverseLayout2) {
                        horizontalArrangement4 = arrangement.getStart();
                    } else {
                        horizontalArrangement4 = arrangement.getEnd();
                    }
                    $dirty2 &= -458753;
                    horizontalArrangement2 = horizontalArrangement4;
                }
                if (i7 != 0) {
                    verticalArrangement2 = Arrangement.INSTANCE.getTop();
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty2 &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i8 != 0) {
                    userScrollEnabled2 = true;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    horizontalArrangement3 = horizontalArrangement2;
                    modifier3 = modifier2;
                } else {
                    modifier3 = modifier2;
                    userScrollEnabled2 = userScrollEnabled;
                    $dirty = $dirty2;
                    flingBehavior3 = flingBehavior2;
                    reverseLayout3 = reverseLayout2;
                    horizontalArrangement3 = horizontalArrangement2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2123608858, $dirty, -1, "androidx.compose.foundation.lazy.grid.LazyHorizontalGrid (LazyGridDsl.kt:114)");
            }
            LazyGridKt.LazyGrid(modifier3, state2, rememberRowHeightSums(rows, verticalArrangement2, contentPadding2, $composer2, ($dirty & 14) | (($dirty >> 15) & 112) | (($dirty >> 3) & 896)), contentPadding2, reverseLayout3, false, flingBehavior3, userScrollEnabled2, verticalArrangement2, horizontalArrangement3, content, $composer2, (($dirty >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (($dirty >> 3) & 112) | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 6) & 234881024) | (($dirty << 12) & 1879048192), ($dirty >> 27) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            reverseLayout4 = reverseLayout3;
            horizontalArrangement5 = horizontalArrangement3;
            state3 = state2;
            flingBehavior4 = flingBehavior3;
            userScrollEnabled3 = userScrollEnabled2;
            contentPadding3 = contentPadding2;
            verticalArrangement3 = verticalArrangement2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final LazyGridState lazyGridState2 = state3;
        final PaddingValues paddingValues2 = contentPadding3;
        final boolean z3 = reverseLayout4;
        final Arrangement.Horizontal horizontal2 = horizontalArrangement5;
        final Arrangement.Vertical vertical2 = verticalArrangement3;
        final FlingBehavior flingBehavior6 = flingBehavior4;
        final boolean z4 = userScrollEnabled3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt.LazyHorizontalGrid.1
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

            public final void invoke(Composer composer, int i9) {
                LazyGridDslKt.LazyHorizontalGrid(rows, modifier6, lazyGridState2, paddingValues2, z3, horizontal2, vertical2, flingBehavior6, z4, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    private static final Function2<Density, Constraints, LazyGridSlots> rememberColumnWidthSums(final GridCells columns, final Arrangement.Horizontal horizontalArrangement, final PaddingValues contentPadding, Composer $composer, int $changed) {
        Object value$iv$iv;
        $composer.startReplaceableGroup(-1355301804);
        ComposerKt.sourceInformation($composer, "C(rememberColumnWidthSums)P(!1,2)148@6622L992:LazyGridDsl.kt#7791vq");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1355301804, $changed, -1, "androidx.compose.foundation.lazy.grid.rememberColumnWidthSums (LazyGridDsl.kt:144)");
        }
        int i = ($changed & 14) | ($changed & 112) | ($changed & 896);
        $composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(columns) | $composer.changed(horizontalArrangement) | $composer.changed(contentPadding);
        Object it$iv$iv = $composer.rememberedValue();
        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new GridSlotCache(new Function2<Density, Constraints, LazyGridSlots>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt$rememberColumnWidthSums$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyGridSlots invoke(Density density, Constraints constraints) {
                    return m615invoke0kLqBqw(density, constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyGridSlots m615invoke0kLqBqw(Density $receiver, long constraints) {
                    Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                    if (!(Constraints.m5218getMaxWidthimpl(constraints) != Integer.MAX_VALUE)) {
                        throw new IllegalArgumentException("LazyVerticalGrid's width should be bound by parent.".toString());
                    }
                    float arg0$iv = PaddingKt.calculateStartPadding(contentPadding, LayoutDirection.Ltr);
                    float other$iv = PaddingKt.calculateEndPadding(contentPadding, LayoutDirection.Ltr);
                    int gridWidth = Constraints.m5218getMaxWidthimpl(constraints) - $receiver.mo321roundToPx0680j_4(Dp.m5274constructorimpl(arg0$iv + other$iv));
                    GridCells $this$invoke_0kLqBqw_u24lambda_u243 = columns;
                    Arrangement.Horizontal $this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241 = horizontalArrangement;
                    int[] sizes = CollectionsKt.toIntArray($this$invoke_0kLqBqw_u24lambda_u243.calculateCrossAxisCellSizes($receiver, gridWidth, $receiver.mo321roundToPx0680j_4($this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241.getSpacing())));
                    int[] positions = new int[sizes.length];
                    $this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241.arrange($receiver, gridWidth, sizes, LayoutDirection.Ltr, positions);
                    return new LazyGridSlots(sizes, positions);
                }
            });
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        $composer.endReplaceableGroup();
        Function2<Density, Constraints, LazyGridSlots> function2 = (Function2) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return function2;
    }

    private static final Function2<Density, Constraints, LazyGridSlots> rememberRowHeightSums(final GridCells rows, final Arrangement.Vertical verticalArrangement, final PaddingValues contentPadding, Composer $composer, int $changed) {
        Object value$iv$iv;
        $composer.startReplaceableGroup(239683573);
        ComposerKt.sourceInformation($composer, "C(rememberRowHeightSums)P(1,2)181@7812L926:LazyGridDsl.kt#7791vq");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(239683573, $changed, -1, "androidx.compose.foundation.lazy.grid.rememberRowHeightSums (LazyGridDsl.kt:177)");
        }
        int i = ($changed & 14) | ($changed & 112) | ($changed & 896);
        $composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(rows) | $composer.changed(verticalArrangement) | $composer.changed(contentPadding);
        Object it$iv$iv = $composer.rememberedValue();
        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new GridSlotCache(new Function2<Density, Constraints, LazyGridSlots>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt$rememberRowHeightSums$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyGridSlots invoke(Density density, Constraints constraints) {
                    return m616invoke0kLqBqw(density, constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyGridSlots m616invoke0kLqBqw(Density $receiver, long constraints) {
                    Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                    if (!(Constraints.m5217getMaxHeightimpl(constraints) != Integer.MAX_VALUE)) {
                        throw new IllegalArgumentException("LazyHorizontalGrid's height should be bound by parent.".toString());
                    }
                    float arg0$iv = contentPadding.getTop();
                    float other$iv = contentPadding.getBottom();
                    int gridHeight = Constraints.m5217getMaxHeightimpl(constraints) - $receiver.mo321roundToPx0680j_4(Dp.m5274constructorimpl(arg0$iv + other$iv));
                    GridCells $this$invoke_0kLqBqw_u24lambda_u243 = rows;
                    Arrangement.Vertical $this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241 = verticalArrangement;
                    int[] sizes = CollectionsKt.toIntArray($this$invoke_0kLqBqw_u24lambda_u243.calculateCrossAxisCellSizes($receiver, gridHeight, $receiver.mo321roundToPx0680j_4($this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241.getSpacing())));
                    int[] positions = new int[sizes.length];
                    $this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241.arrange($receiver, gridHeight, sizes, positions);
                    return new LazyGridSlots(sizes, positions);
                }
            });
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        $composer.endReplaceableGroup();
        Function2<Density, Constraints, LazyGridSlots> function2 = (Function2) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return function2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> calculateCellsCrossAxisSizeImpl(int gridSize, int slotCount, int spacing) {
        int gridSizeWithoutSpacing = gridSize - ((slotCount - 1) * spacing);
        int slotSize = gridSizeWithoutSpacing / slotCount;
        int remainingPixels = gridSizeWithoutSpacing % slotCount;
        ArrayList arrayList = new ArrayList(slotCount);
        for (int i = 0; i < slotCount; i++) {
            int it = i;
            arrayList.add(Integer.valueOf((it < remainingPixels ? 1 : 0) + slotSize));
        }
        return arrayList;
    }

    public static /* synthetic */ void items$default(LazyGridScope $this$items_u24default, List items, Function1 key, Function2 span, Function1 contentType, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            span = null;
        }
        if ((i & 8) != 0) {
            Function1 contentType2 = new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt.items.1
                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(T t) {
                    return null;
                }
            };
            contentType = contentType2;
        }
        Intrinsics.checkNotNullParameter($this$items_u24default, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        $this$items_u24default.items(items.size(), key != null ? new AnonymousClass2(key, items) : null, span != null ? new AnonymousClass3(span, items) : null, new AnonymousClass4(contentType, items), ComposableLambdaKt.composableLambdaInstance(699646206, true, new AnonymousClass5(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$2, reason: invalid class name */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass2 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ List<T> $items;
        final /* synthetic */ Function1<T, Object> $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function1<? super T, ? extends Object> function1, List<? extends T> list) {
            super(1);
            this.$key = function1;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object invoke(int i) {
            return this.$key.invoke((T) this.$items.get(i));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$3, reason: invalid class name */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "T", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "it", "", "invoke-_-orMbw", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;I)J"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass3 extends Lambda implements Function2<LazyGridItemSpanScope, Integer, GridItemSpan> {
        final /* synthetic */ List<T> $items;
        final /* synthetic */ Function2<LazyGridItemSpanScope, T, GridItemSpan> $span;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Function2<? super LazyGridItemSpanScope, ? super T, GridItemSpan> function2, List<? extends T> list) {
            super(2);
            this.$span = function2;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ GridItemSpan invoke(LazyGridItemSpanScope lazyGridItemSpanScope, Integer num) {
            return GridItemSpan.m602boximpl(m611invoke_orMbw(lazyGridItemSpanScope, num.intValue()));
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /* JADX INFO: renamed from: invoke-_-orMbw, reason: not valid java name */
        public final long m611invoke_orMbw(LazyGridItemSpanScope lazyGridItemSpanScope, int i) {
            Intrinsics.checkNotNullParameter(lazyGridItemSpanScope, "$this$null");
            return this.$span.invoke(lazyGridItemSpanScope, (T) this.$items.get(i)).getPackedValue();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$4, reason: invalid class name */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass4 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function1<T, Object> $contentType;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(Function1<? super T, ? extends Object> function1, List<? extends T> list) {
            super(1);
            this.$contentType = function1;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object invoke(int i) {
            return this.$contentType.invoke((T) this.$items.get(i));
        }
    }

    public static final <T> void items(LazyGridScope $this$items, List<? extends T> items, Function1<? super T, ? extends Object> function1, Function2<? super LazyGridItemSpanScope, ? super T, GridItemSpan> function2, Function1<? super T, ? extends Object> contentType, Function4<? super LazyGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter($this$items, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        $this$items.items(items.size(), function1 != null ? new AnonymousClass2(function1, items) : null, function2 != null ? new AnonymousClass3(function2, items) : null, new AnonymousClass4(contentType, items), ComposableLambdaKt.composableLambdaInstance(699646206, true, new AnonymousClass5(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$5, reason: invalid class name */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "it", "", "invoke", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass5 extends Lambda implements Function4<LazyGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function4<LazyGridItemScope, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(Function4<? super LazyGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4, List<? extends T> list) {
            super(4);
            this.$itemContent = function4;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyGridItemScope lazyGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final void invoke(LazyGridItemScope items, int i, Composer composer, int i2) {
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation(composer, "C455@19203L22:LazyGridDsl.kt#7791vq");
            int i3 = i2;
            if ((i2 & 14) == 0) {
                i3 |= composer.changed(items) ? 4 : 2;
            }
            if ((i2 & 112) == 0) {
                i3 |= composer.changed(i) ? 32 : 16;
            }
            if ((i3 & 731) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(699646206, i3, -1, "androidx.compose.foundation.lazy.grid.items.<anonymous> (LazyGridDsl.kt:454)");
            }
            this.$itemContent.invoke(items, (T) this.$items.get(i), composer, Integer.valueOf(i3 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static /* synthetic */ void itemsIndexed$default(LazyGridScope $this$itemsIndexed_u24default, List items, Function2 key, Function3 span, Function2 contentType, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            span = null;
        }
        if ((i & 8) != 0) {
            Function2 contentType2 = new Function2() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt.itemsIndexed.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) {
                    return invoke(((Number) p1).intValue(), p2);
                }

                public final Void invoke(int i2, T t) {
                    return null;
                }
            };
            contentType = contentType2;
        }
        Intrinsics.checkNotNullParameter($this$itemsIndexed_u24default, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        $this$itemsIndexed_u24default.items(items.size(), key != null ? new C02352(key, items) : null, span != null ? new C02363(span, items) : null, new C02374(contentType, items), ComposableLambdaKt.composableLambdaInstance(1229287273, true, new C02385(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02352 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ List<T> $items;
        final /* synthetic */ Function2<Integer, T, Object> $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02352(Function2<? super Integer, ? super T, ? extends Object> function2, List<? extends T> list) {
            super(1);
            this.$key = function2;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object invoke(int i) {
            return this.$key.invoke(Integer.valueOf(i), (T) this.$items.get(i));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "T", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "it", "", "invoke-_-orMbw", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;I)J"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02363 extends Lambda implements Function2<LazyGridItemSpanScope, Integer, GridItemSpan> {
        final /* synthetic */ List<T> $items;
        final /* synthetic */ Function3<LazyGridItemSpanScope, Integer, T, GridItemSpan> $span;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02363(Function3<? super LazyGridItemSpanScope, ? super Integer, ? super T, GridItemSpan> function3, List<? extends T> list) {
            super(2);
            this.$span = function3;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ GridItemSpan invoke(LazyGridItemSpanScope lazyGridItemSpanScope, Integer num) {
            return GridItemSpan.m602boximpl(m613invoke_orMbw(lazyGridItemSpanScope, num.intValue()));
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /* JADX INFO: renamed from: invoke-_-orMbw, reason: not valid java name */
        public final long m613invoke_orMbw(LazyGridItemSpanScope lazyGridItemSpanScope, int i) {
            Intrinsics.checkNotNullParameter(lazyGridItemSpanScope, "$this$null");
            return this.$span.invoke(lazyGridItemSpanScope, Integer.valueOf(i), (T) this.$items.get(i)).getPackedValue();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02374 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function2<Integer, T, Object> $contentType;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02374(Function2<? super Integer, ? super T, ? extends Object> function2, List<? extends T> list) {
            super(1);
            this.$contentType = function2;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object invoke(int i) {
            return this.$contentType.invoke(Integer.valueOf(i), (T) this.$items.get(i));
        }
    }

    public static final <T> void itemsIndexed(LazyGridScope $this$itemsIndexed, List<? extends T> items, Function2<? super Integer, ? super T, ? extends Object> function2, Function3<? super LazyGridItemSpanScope, ? super Integer, ? super T, GridItemSpan> function3, Function2<? super Integer, ? super T, ? extends Object> contentType, Function5<? super LazyGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter($this$itemsIndexed, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        $this$itemsIndexed.items(items.size(), function2 != null ? new C02352(function2, items) : null, function3 != null ? new C02363(function3, items) : null, new C02374(contentType, items), ComposableLambdaKt.composableLambdaInstance(1229287273, true, new C02385(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "it", "", "invoke", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02385 extends Lambda implements Function4<LazyGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function5<LazyGridItemScope, Integer, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02385(Function5<? super LazyGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5, List<? extends T> list) {
            super(4);
            this.$itemContent = function5;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyGridItemScope lazyGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final void invoke(LazyGridItemScope items, int i, Composer composer, int i2) {
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation(composer, "C488@20978L26:LazyGridDsl.kt#7791vq");
            int i3 = i2;
            if ((i2 & 14) == 0) {
                i3 |= composer.changed(items) ? 4 : 2;
            }
            if ((i2 & 112) == 0) {
                i3 |= composer.changed(i) ? 32 : 16;
            }
            if ((i3 & 731) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1229287273, i3, -1, "androidx.compose.foundation.lazy.grid.itemsIndexed.<anonymous> (LazyGridDsl.kt:487)");
            }
            this.$itemContent.invoke(items, Integer.valueOf(i), (T) this.$items.get(i), composer, Integer.valueOf((i3 & 14) | (i3 & 112)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static /* synthetic */ void items$default(LazyGridScope $this$items_u24default, Object[] items, Function1 key, Function2 span, Function1 contentType, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            span = null;
        }
        if ((i & 8) != 0) {
            Function1 contentType2 = new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt.items.6
                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(T t) {
                    return null;
                }
            };
            contentType = contentType2;
        }
        Intrinsics.checkNotNullParameter($this$items_u24default, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        $this$items_u24default.items(items.length, key != null ? new AnonymousClass7(key, items) : null, span != null ? new AnonymousClass8(span, items) : null, new AnonymousClass9(contentType, items), ComposableLambdaKt.composableLambdaInstance(407562193, true, new AnonymousClass10(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$7, reason: invalid class name */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass7 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ T[] $items;
        final /* synthetic */ Function1<T, Object> $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass7(Function1<? super T, ? extends Object> function1, T[] tArr) {
            super(1);
            this.$key = function1;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final Object invoke(int index) {
            return this.$key.invoke(this.$items[index]);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$8, reason: invalid class name */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "T", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "it", "", "invoke-_-orMbw", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;I)J"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass8 extends Lambda implements Function2<LazyGridItemSpanScope, Integer, GridItemSpan> {
        final /* synthetic */ T[] $items;
        final /* synthetic */ Function2<LazyGridItemSpanScope, T, GridItemSpan> $span;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass8(Function2<? super LazyGridItemSpanScope, ? super T, GridItemSpan> function2, T[] tArr) {
            super(2);
            this.$span = function2;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ GridItemSpan invoke(LazyGridItemSpanScope lazyGridItemSpanScope, Integer num) {
            return GridItemSpan.m602boximpl(m612invoke_orMbw(lazyGridItemSpanScope, num.intValue()));
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        /* JADX INFO: renamed from: invoke-_-orMbw, reason: not valid java name */
        public final long m612invoke_orMbw(LazyGridItemSpanScope $this$null, int it) {
            Intrinsics.checkNotNullParameter($this$null, "$this$null");
            return this.$span.invoke($this$null, this.$items[it]).getPackedValue();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$9, reason: invalid class name */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass9 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function1<T, Object> $contentType;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass9(Function1<? super T, ? extends Object> function1, T[] tArr) {
            super(1);
            this.$contentType = function1;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final Object invoke(int index) {
            return this.$contentType.invoke(this.$items[index]);
        }
    }

    public static final <T> void items(LazyGridScope $this$items, T[] items, Function1<? super T, ? extends Object> function1, Function2<? super LazyGridItemSpanScope, ? super T, GridItemSpan> function2, Function1<? super T, ? extends Object> contentType, Function4<? super LazyGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter($this$items, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        $this$items.items(items.length, function1 != null ? new AnonymousClass7(function1, items) : null, function2 != null ? new AnonymousClass8(function2, items) : null, new AnonymousClass9(contentType, items), ComposableLambdaKt.composableLambdaInstance(407562193, true, new AnonymousClass10(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$10, reason: invalid class name */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "it", "", "invoke", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass10 extends Lambda implements Function4<LazyGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function4<LazyGridItemScope, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass10(Function4<? super LazyGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4, T[] tArr) {
            super(4);
            this.$itemContent = function4;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyGridItemScope lazyGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final void invoke(LazyGridItemScope items, int it, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation($composer, "C521@22631L22:LazyGridDsl.kt#7791vq");
            int $dirty = $changed;
            if (($changed & 14) == 0) {
                $dirty |= $composer.changed(items) ? 4 : 2;
            }
            if (($changed & 112) == 0) {
                $dirty |= $composer.changed(it) ? 32 : 16;
            }
            if (($dirty & 731) == 146 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(407562193, $dirty, -1, "androidx.compose.foundation.lazy.grid.items.<anonymous> (LazyGridDsl.kt:520)");
            }
            this.$itemContent.invoke(items, this.$items[it], $composer, Integer.valueOf($dirty & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static /* synthetic */ void itemsIndexed$default(LazyGridScope $this$itemsIndexed_u24default, Object[] items, Function2 key, Function3 span, Function2 contentType, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            span = null;
        }
        if ((i & 8) != 0) {
            Function2 contentType2 = new Function2() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt.itemsIndexed.6
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) {
                    return invoke(((Number) p1).intValue(), p2);
                }

                public final Void invoke(int i2, T t) {
                    return null;
                }
            };
            contentType = contentType2;
        }
        Intrinsics.checkNotNullParameter($this$itemsIndexed_u24default, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        $this$itemsIndexed_u24default.items(items.length, key != null ? new C02407(key, items) : null, span != null ? new C02418(span, items) : null, new C02429(contentType, items), ComposableLambdaKt.composableLambdaInstance(-911455938, true, new C023410(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$7, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02407 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ T[] $items;
        final /* synthetic */ Function2<Integer, T, Object> $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02407(Function2<? super Integer, ? super T, ? extends Object> function2, T[] tArr) {
            super(1);
            this.$key = function2;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final Object invoke(int index) {
            return this.$key.invoke(Integer.valueOf(index), this.$items[index]);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$8, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "T", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "it", "", "invoke-_-orMbw", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;I)J"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02418 extends Lambda implements Function2<LazyGridItemSpanScope, Integer, GridItemSpan> {
        final /* synthetic */ T[] $items;
        final /* synthetic */ Function3<LazyGridItemSpanScope, Integer, T, GridItemSpan> $span;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02418(Function3<? super LazyGridItemSpanScope, ? super Integer, ? super T, GridItemSpan> function3, T[] tArr) {
            super(2);
            this.$span = function3;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ GridItemSpan invoke(LazyGridItemSpanScope lazyGridItemSpanScope, Integer num) {
            return GridItemSpan.m602boximpl(m614invoke_orMbw(lazyGridItemSpanScope, num.intValue()));
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        /* JADX INFO: renamed from: invoke-_-orMbw, reason: not valid java name */
        public final long m614invoke_orMbw(LazyGridItemSpanScope $this$null, int it) {
            Intrinsics.checkNotNullParameter($this$null, "$this$null");
            return this.$span.invoke($this$null, Integer.valueOf(it), this.$items[it]).getPackedValue();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$9, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02429 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function2<Integer, T, Object> $contentType;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02429(Function2<? super Integer, ? super T, ? extends Object> function2, T[] tArr) {
            super(1);
            this.$contentType = function2;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final Object invoke(int index) {
            return this.$contentType.invoke(Integer.valueOf(index), this.$items[index]);
        }
    }

    public static final <T> void itemsIndexed(LazyGridScope $this$itemsIndexed, T[] items, Function2<? super Integer, ? super T, ? extends Object> function2, Function3<? super LazyGridItemSpanScope, ? super Integer, ? super T, GridItemSpan> function3, Function2<? super Integer, ? super T, ? extends Object> contentType, Function5<? super LazyGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter($this$itemsIndexed, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        $this$itemsIndexed.items(items.length, function2 != null ? new C02407(function2, items) : null, function3 != null ? new C02418(function3, items) : null, new C02429(contentType, items), ComposableLambdaKt.composableLambdaInstance(-911455938, true, new C023410(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$10, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "it", "", "invoke", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C023410 extends Lambda implements Function4<LazyGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function5<LazyGridItemScope, Integer, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C023410(Function5<? super LazyGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5, T[] tArr) {
            super(4);
            this.$itemContent = function5;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyGridItemScope lazyGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final void invoke(LazyGridItemScope items, int it, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation($composer, "C554@24410L26:LazyGridDsl.kt#7791vq");
            int $dirty = $changed;
            if (($changed & 14) == 0) {
                $dirty |= $composer.changed(items) ? 4 : 2;
            }
            if (($changed & 112) == 0) {
                $dirty |= $composer.changed(it) ? 32 : 16;
            }
            if (($dirty & 731) == 146 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-911455938, $dirty, -1, "androidx.compose.foundation.lazy.grid.itemsIndexed.<anonymous> (LazyGridDsl.kt:553)");
            }
            this.$itemContent.invoke(items, Integer.valueOf(it), this.$items[it], $composer, Integer.valueOf(($dirty & 14) | ($dirty & 112)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }
}
