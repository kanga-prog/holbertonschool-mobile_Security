package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
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
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: LazyStaggeredGridDsl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0086\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0086\u0001\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000f2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001f\u001a?\u0010 \u001a\u0019\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0!¢\u0006\u0002\b\u00162\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\b\u001a\u00020\tH\u0003ø\u0001\u0001¢\u0006\u0002\u0010%\u001a?\u0010&\u001a\u0019\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0!¢\u0006\u0002\b\u00162\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0003ø\u0001\u0001¢\u0006\u0002\u0010'\u001aÓ\u0001\u0010(\u001a\u00020\u0001\"\u0004\b\u0000\u0010)*\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H)0*2%\b\n\u0010+\u001a\u001f\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020/\u0018\u00010\u00142%\b\u0006\u00100\u001a\u001f\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0006\u0012\u0004\u0018\u00010/0\u00142%\b\n\u00101\u001a\u001f\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u000202\u0018\u00010\u001423\b\u0004\u00103\u001a-\u0012\u0004\u0012\u000204\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00010!¢\u0006\u0002\b5¢\u0006\u0002\b\u0016H\u0086\bø\u0001\u0002¢\u0006\u0002\u00106\u001aÓ\u0001\u0010(\u001a\u00020\u0001\"\u0004\b\u0000\u0010)*\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H)072%\b\n\u0010+\u001a\u001f\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020/\u0018\u00010\u00142%\b\u0006\u00100\u001a\u001f\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0006\u0012\u0004\u0018\u00010/0\u00142%\b\n\u00101\u001a\u001f\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u000202\u0018\u00010\u001423\b\u0004\u00103\u001a-\u0012\u0004\u0012\u000204\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00010!¢\u0006\u0002\b5¢\u0006\u0002\b\u0016H\u0086\bø\u0001\u0002¢\u0006\u0002\u00108\u001a§\u0002\u00109\u001a\u00020\u0001\"\u0004\b\u0000\u0010)*\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H)0*2:\b\n\u0010+\u001a4\u0012\u0013\u0012\u00110:¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020/\u0018\u00010!2:\b\u0006\u00100\u001a4\u0012\u0013\u0012\u00110:¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0006\u0012\u0004\u0018\u00010/0!2:\b\n\u00101\u001a4\u0012\u0013\u0012\u00110:¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u000202\u0018\u00010!2H\b\u0004\u00103\u001aB\u0012\u0004\u0012\u000204\u0012\u0013\u0012\u00110:¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00010<¢\u0006\u0002\b5¢\u0006\u0002\b\u0016H\u0086\bø\u0001\u0002¢\u0006\u0002\u0010=\u001a§\u0002\u00109\u001a\u00020\u0001\"\u0004\b\u0000\u0010)*\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H)072:\b\n\u0010+\u001a4\u0012\u0013\u0012\u00110:¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020/\u0018\u00010!2:\b\u0006\u00100\u001a4\u0012\u0013\u0012\u00110:¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0006\u0012\u0004\u0018\u00010/0!2:\b\n\u00101\u001a4\u0012\u0013\u0012\u00110:¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u000202\u0018\u00010!2H\b\u0004\u00103\u001aB\u0012\u0004\u0012\u000204\u0012\u0013\u0012\u00110:¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00010<¢\u0006\u0002\b5¢\u0006\u0002\b\u0016H\u0086\bø\u0001\u0002¢\u0006\u0002\u0010>\u0082\u0002\u0012\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u0006?"}, d2 = {"LazyHorizontalStaggeredGrid", "", "rows", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalItemSpacing", "Landroidx/compose/ui/unit/Dp;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;", "Lkotlin/ExtensionFunctionType;", "LazyHorizontalStaggeredGrid-cJHQLPU", "(Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LazyVerticalStaggeredGrid", "columns", "verticalItemSpacing", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "LazyVerticalStaggeredGrid-zadm560", "(Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/foundation/layout/PaddingValues;ZFLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "rememberColumnSlots", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "(Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "rememberRowSlots", "(Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "items", "T", "", "key", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "item", "", "contentType", "span", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;", "itemContent", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "itemsIndexed", "", "index", "Lkotlin/Function3;", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridDslKt {
    /* JADX WARN: Code duplicated, block: B:110:0x0165  */
    /* JADX WARN: Code duplicated, block: B:112:0x016f  */
    /* JADX WARN: Code duplicated, block: B:122:0x01a2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:123:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:124:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:126:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:127:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:129:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:130:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:132:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:133:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:135:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:138:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:139:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:142:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:143:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:145:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:146:0x0201  */
    /* JADX WARN: Code duplicated, block: B:149:0x020c  */
    /* JADX WARN: Code duplicated, block: B:152:0x027b  */
    /* JADX WARN: Code duplicated, block: B:156:0x0294  */
    /* JADX WARN: Code duplicated, block: B:157:0x0297  */
    /* JADX INFO: renamed from: LazyVerticalStaggeredGrid-zadm560, reason: not valid java name */
    public static final void m666LazyVerticalStaggeredGridzadm560(final StaggeredGridCells columns, Modifier modifier, LazyStaggeredGridState state, PaddingValues contentPadding, boolean reverseLayout, float verticalItemSpacing, Arrangement.Horizontal horizontalArrangement, FlingBehavior flingBehavior, boolean userScrollEnabled, final Function1<? super LazyStaggeredGridScope, Unit> content, Composer $composer, final int $changed, final int i) {
        PaddingValues paddingValues;
        boolean z;
        float f;
        int i2;
        Modifier.Companion modifier2;
        LazyStaggeredGridState state2;
        PaddingValues contentPadding2;
        boolean reverseLayout2;
        float verticalItemSpacing2;
        Arrangement.HorizontalOrVertical horizontalArrangement2;
        FlingBehavior flingBehavior2;
        boolean userScrollEnabled2;
        boolean userScrollEnabled3;
        Modifier modifier3;
        LazyStaggeredGridState state3;
        PaddingValues contentPadding3;
        boolean reverseLayout3;
        float verticalItemSpacing3;
        Arrangement.Horizontal horizontalArrangement3;
        FlingBehavior flingBehavior3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(columns, "columns");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1695323794);
        ComposerKt.sourceInformation($composer2, "C(LazyVerticalStaggeredGrid)P(!1,5,7,2,6,9:c#ui.unit.Dp,4,3,8)64@3068L32,69@3365L15,83@3878L67,73@3476L502:LazyStaggeredGridDsl.kt#fzvcnm");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(columns) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            paddingValues = contentPadding;
        } else if (($changed & 7168) == 0) {
            paddingValues = contentPadding;
            $dirty |= $composer2.changed(paddingValues) ? 2048 : 1024;
        } else {
            paddingValues = contentPadding;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            z = reverseLayout;
        } else if (($changed & 57344) == 0) {
            z = reverseLayout;
            $dirty |= $composer2.changed(z) ? 16384 : 8192;
        } else {
            z = reverseLayout;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = verticalItemSpacing;
        } else if (($changed & 458752) == 0) {
            f = verticalItemSpacing;
            $dirty |= $composer2.changed(f) ? 131072 : 65536;
        } else {
            f = verticalItemSpacing;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty |= $composer2.changed(horizontalArrangement) ? 1048576 : 524288;
        }
        if (($changed & 29360128) == 0) {
            $dirty |= ((i & 128) == 0 && $composer2.changed(flingBehavior)) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty |= $composer2.changed(userScrollEnabled) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) == 0) {
            if ((1879048192 & $changed) == 0) {
                i2 = $composer2.changedInstance(content) ? 536870912 : 268435456;
            }
            if (i4 != 4 && (1533916891 & $dirty) == 306783378 && $composer2.getSkipping()) {
                $composer2.skipToGroupEnd();
                horizontalArrangement3 = horizontalArrangement;
                flingBehavior3 = flingBehavior;
                userScrollEnabled3 = userScrollEnabled;
                contentPadding3 = paddingValues;
                verticalItemSpacing3 = f;
                reverseLayout3 = z;
                modifier3 = modifier;
                state3 = state;
            } else {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        state2 = LazyStaggeredGridStateKt.rememberLazyStaggeredGridState(0, 0, $composer2, 0, 3);
                        $dirty &= -897;
                    } else {
                        state2 = state;
                    }
                    if (i5 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = paddingValues;
                    }
                    if (i6 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = z;
                    }
                    if (i7 != 0) {
                        verticalItemSpacing2 = Dp.m5274constructorimpl(0);
                    } else {
                        verticalItemSpacing2 = f;
                    }
                    if (i8 != 0) {
                        horizontalArrangement2 = Arrangement.INSTANCE.m394spacedBy0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        horizontalArrangement2 = horizontalArrangement;
                    }
                    if ((i & 128) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                        $dirty &= -29360129;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    if (i9 != 0) {
                        userScrollEnabled2 = true;
                    } else {
                        userScrollEnabled2 = userScrollEnabled;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if (i4 != 0) {
                        $dirty &= -897;
                    }
                    if ((i & 128) != 0) {
                        modifier2 = modifier;
                        state2 = state;
                        flingBehavior2 = flingBehavior;
                        $dirty &= -29360129;
                        contentPadding2 = paddingValues;
                        verticalItemSpacing2 = f;
                        reverseLayout2 = z;
                        horizontalArrangement2 = horizontalArrangement;
                        userScrollEnabled2 = userScrollEnabled;
                    } else {
                        modifier2 = modifier;
                        state2 = state;
                        flingBehavior2 = flingBehavior;
                        userScrollEnabled2 = userScrollEnabled;
                        contentPadding2 = paddingValues;
                        verticalItemSpacing2 = f;
                        reverseLayout2 = z;
                        horizontalArrangement2 = horizontalArrangement;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1695323794, $dirty, -1, "androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid (LazyStaggeredGridDsl.kt:61)");
                }
                LazyStaggeredGridKt.m671LazyStaggeredGridLJWHXA8(state2, Orientation.Vertical, rememberColumnSlots(columns, horizontalArrangement2, contentPadding2, $composer2, (($dirty >> 3) & 896) | ($dirty & 14) | (($dirty >> 15) & 112)), modifier2, contentPadding2, reverseLayout2, flingBehavior2, userScrollEnabled2, verticalItemSpacing2, horizontalArrangement2.getSpacing(), content, $composer2, (($dirty << 6) & 7168) | 56 | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 9) & 234881024), ($dirty >> 27) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                userScrollEnabled3 = userScrollEnabled2;
                modifier3 = modifier2;
                state3 = state2;
                contentPadding3 = contentPadding2;
                reverseLayout3 = reverseLayout2;
                verticalItemSpacing3 = verticalItemSpacing2;
                horizontalArrangement3 = horizontalArrangement2;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final LazyStaggeredGridState lazyStaggeredGridState = state3;
            final PaddingValues paddingValues2 = contentPadding3;
            final boolean z2 = reverseLayout3;
            final float f2 = verticalItemSpacing3;
            final Arrangement.Horizontal horizontal = horizontalArrangement3;
            final FlingBehavior flingBehavior4 = flingBehavior3;
            final boolean z3 = userScrollEnabled3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$LazyVerticalStaggeredGrid$1
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

                public final void invoke(Composer composer, int i10) {
                    LazyStaggeredGridDslKt.m666LazyVerticalStaggeredGridzadm560(columns, modifier4, lazyStaggeredGridState, paddingValues2, z2, f2, horizontal, flingBehavior4, z3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty |= i2;
        if (i4 != 4) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    state2 = LazyStaggeredGridStateKt.rememberLazyStaggeredGridState(0, 0, $composer2, 0, 3);
                    $dirty &= -897;
                } else {
                    state2 = state;
                }
                if (i5 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    contentPadding2 = paddingValues;
                }
                if (i6 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = z;
                }
                if (i7 != 0) {
                    verticalItemSpacing2 = Dp.m5274constructorimpl(0);
                } else {
                    verticalItemSpacing2 = f;
                }
                if (i8 != 0) {
                    horizontalArrangement2 = Arrangement.INSTANCE.m394spacedBy0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    horizontalArrangement2 = horizontalArrangement;
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i9 != 0) {
                    userScrollEnabled2 = true;
                } else {
                    userScrollEnabled2 = userScrollEnabled;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    state2 = LazyStaggeredGridStateKt.rememberLazyStaggeredGridState(0, 0, $composer2, 0, 3);
                    $dirty &= -897;
                } else {
                    state2 = state;
                }
                if (i5 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    contentPadding2 = paddingValues;
                }
                if (i6 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = z;
                }
                if (i7 != 0) {
                    verticalItemSpacing2 = Dp.m5274constructorimpl(0);
                } else {
                    verticalItemSpacing2 = f;
                }
                if (i8 != 0) {
                    horizontalArrangement2 = Arrangement.INSTANCE.m394spacedBy0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    horizontalArrangement2 = horizontalArrangement;
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i9 != 0) {
                    userScrollEnabled2 = true;
                } else {
                    userScrollEnabled2 = userScrollEnabled;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1695323794, $dirty, -1, "androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid (LazyStaggeredGridDsl.kt:61)");
            }
            LazyStaggeredGridKt.m671LazyStaggeredGridLJWHXA8(state2, Orientation.Vertical, rememberColumnSlots(columns, horizontalArrangement2, contentPadding2, $composer2, (($dirty >> 3) & 896) | ($dirty & 14) | (($dirty >> 15) & 112)), modifier2, contentPadding2, reverseLayout2, flingBehavior2, userScrollEnabled2, verticalItemSpacing2, horizontalArrangement2.getSpacing(), content, $composer2, (($dirty << 6) & 7168) | 56 | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 9) & 234881024), ($dirty >> 27) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            userScrollEnabled3 = userScrollEnabled2;
            modifier3 = modifier2;
            state3 = state2;
            contentPadding3 = contentPadding2;
            reverseLayout3 = reverseLayout2;
            verticalItemSpacing3 = verticalItemSpacing2;
            horizontalArrangement3 = horizontalArrangement2;
            flingBehavior3 = flingBehavior2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    state2 = LazyStaggeredGridStateKt.rememberLazyStaggeredGridState(0, 0, $composer2, 0, 3);
                    $dirty &= -897;
                } else {
                    state2 = state;
                }
                if (i5 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    contentPadding2 = paddingValues;
                }
                if (i6 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = z;
                }
                if (i7 != 0) {
                    verticalItemSpacing2 = Dp.m5274constructorimpl(0);
                } else {
                    verticalItemSpacing2 = f;
                }
                if (i8 != 0) {
                    horizontalArrangement2 = Arrangement.INSTANCE.m394spacedBy0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    horizontalArrangement2 = horizontalArrangement;
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i9 != 0) {
                    userScrollEnabled2 = true;
                } else {
                    userScrollEnabled2 = userScrollEnabled;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    state2 = LazyStaggeredGridStateKt.rememberLazyStaggeredGridState(0, 0, $composer2, 0, 3);
                    $dirty &= -897;
                } else {
                    state2 = state;
                }
                if (i5 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    contentPadding2 = paddingValues;
                }
                if (i6 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = z;
                }
                if (i7 != 0) {
                    verticalItemSpacing2 = Dp.m5274constructorimpl(0);
                } else {
                    verticalItemSpacing2 = f;
                }
                if (i8 != 0) {
                    horizontalArrangement2 = Arrangement.INSTANCE.m394spacedBy0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    horizontalArrangement2 = horizontalArrangement;
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i9 != 0) {
                    userScrollEnabled2 = true;
                } else {
                    userScrollEnabled2 = userScrollEnabled;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1695323794, $dirty, -1, "androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid (LazyStaggeredGridDsl.kt:61)");
            }
            LazyStaggeredGridKt.m671LazyStaggeredGridLJWHXA8(state2, Orientation.Vertical, rememberColumnSlots(columns, horizontalArrangement2, contentPadding2, $composer2, (($dirty >> 3) & 896) | ($dirty & 14) | (($dirty >> 15) & 112)), modifier2, contentPadding2, reverseLayout2, flingBehavior2, userScrollEnabled2, verticalItemSpacing2, horizontalArrangement2.getSpacing(), content, $composer2, (($dirty << 6) & 7168) | 56 | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 9) & 234881024), ($dirty >> 27) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            userScrollEnabled3 = userScrollEnabled2;
            modifier3 = modifier2;
            state3 = state2;
            contentPadding3 = contentPadding2;
            reverseLayout3 = reverseLayout2;
            verticalItemSpacing3 = verticalItemSpacing2;
            horizontalArrangement3 = horizontalArrangement2;
            flingBehavior3 = flingBehavior2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final LazyStaggeredGridState lazyStaggeredGridState2 = state3;
        final PaddingValues paddingValues3 = contentPadding3;
        final boolean z4 = reverseLayout3;
        final float f3 = verticalItemSpacing3;
        final Arrangement.Horizontal horizontal2 = horizontalArrangement3;
        final FlingBehavior flingBehavior5 = flingBehavior3;
        final boolean z5 = userScrollEnabled3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$LazyVerticalStaggeredGrid$1
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

            public final void invoke(Composer composer, int i10) {
                LazyStaggeredGridDslKt.m666LazyVerticalStaggeredGridzadm560(columns, modifier5, lazyStaggeredGridState2, paddingValues3, z4, f3, horizontal2, flingBehavior5, z5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    private static final Function2<Density, Constraints, LazyStaggeredGridSlots> rememberColumnSlots(final StaggeredGridCells columns, final Arrangement.Horizontal horizontalArrangement, final PaddingValues contentPadding, Composer $composer, int $changed) {
        Object value$iv$iv;
        $composer.startReplaceableGroup(-1038659655);
        ComposerKt.sourceInformation($composer, "C(rememberColumnSlots)P(!1,2)94@4216L1127:LazyStaggeredGridDsl.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1038659655, $changed, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberColumnSlots (LazyStaggeredGridDsl.kt:90)");
        }
        int i = ($changed & 14) | ($changed & 112) | ($changed & 896);
        $composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(columns) | $composer.changed(horizontalArrangement) | $composer.changed(contentPadding);
        Object it$iv$iv = $composer.rememberedValue();
        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new LazyStaggeredGridSlotCache(new Function2<Density, Constraints, LazyStaggeredGridSlots>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$rememberColumnSlots$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyStaggeredGridSlots invoke(Density density, Constraints constraints) {
                    return m667invoke0kLqBqw(density, constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyStaggeredGridSlots m667invoke0kLqBqw(Density $receiver, long constraints) {
                    Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                    if (!(Constraints.m5218getMaxWidthimpl(constraints) != Integer.MAX_VALUE)) {
                        throw new IllegalArgumentException("LazyVerticalStaggeredGrid's width should be bound by parent.".toString());
                    }
                    float arg0$iv = PaddingKt.calculateStartPadding(contentPadding, LayoutDirection.Ltr);
                    float other$iv = PaddingKt.calculateEndPadding(contentPadding, LayoutDirection.Ltr);
                    int gridWidth = Constraints.m5218getMaxWidthimpl(constraints) - $receiver.mo321roundToPx0680j_4(Dp.m5274constructorimpl(arg0$iv + other$iv));
                    StaggeredGridCells $this$invoke_0kLqBqw_u24lambda_u243 = columns;
                    Arrangement.Horizontal $this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241 = horizontalArrangement;
                    int[] sizes = $this$invoke_0kLqBqw_u24lambda_u243.calculateCrossAxisCellSizes($receiver, gridWidth, $receiver.mo321roundToPx0680j_4($this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241.getSpacing()));
                    int[] positions = new int[sizes.length];
                    $this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241.arrange($receiver, gridWidth, sizes, LayoutDirection.Ltr, positions);
                    return new LazyStaggeredGridSlots(positions, sizes);
                }
            });
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        $composer.endReplaceableGroup();
        Function2<Density, Constraints, LazyStaggeredGridSlots> function2 = (Function2) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return function2;
    }

    /* JADX WARN: Code duplicated, block: B:110:0x0166  */
    /* JADX WARN: Code duplicated, block: B:112:0x0170  */
    /* JADX WARN: Code duplicated, block: B:122:0x01a3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:123:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:124:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:126:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:127:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:129:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:130:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:132:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:133:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:135:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:136:0x01df  */
    /* JADX WARN: Code duplicated, block: B:138:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:139:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:142:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:143:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:145:0x0200  */
    /* JADX WARN: Code duplicated, block: B:146:0x0202  */
    /* JADX WARN: Code duplicated, block: B:149:0x020d  */
    /* JADX WARN: Code duplicated, block: B:152:0x027c  */
    /* JADX WARN: Code duplicated, block: B:156:0x0295  */
    /* JADX WARN: Code duplicated, block: B:157:0x0298  */
    /* JADX INFO: renamed from: LazyHorizontalStaggeredGrid-cJHQLPU, reason: not valid java name */
    public static final void m665LazyHorizontalStaggeredGridcJHQLPU(final StaggeredGridCells rows, Modifier modifier, LazyStaggeredGridState state, PaddingValues contentPadding, boolean reverseLayout, Arrangement.Vertical verticalArrangement, float horizontalItemSpacing, FlingBehavior flingBehavior, boolean userScrollEnabled, final Function1<? super LazyStaggeredGridScope, Unit> content, Composer $composer, final int $changed, final int i) {
        PaddingValues paddingValues;
        boolean z;
        Arrangement.Vertical vertical;
        int i2;
        Modifier.Companion modifier2;
        LazyStaggeredGridState state2;
        PaddingValues contentPadding2;
        boolean reverseLayout2;
        Arrangement.HorizontalOrVertical verticalArrangement2;
        float horizontalItemSpacing2;
        FlingBehavior flingBehavior2;
        boolean userScrollEnabled2;
        boolean userScrollEnabled3;
        Modifier modifier3;
        LazyStaggeredGridState state3;
        PaddingValues contentPadding3;
        boolean reverseLayout3;
        Arrangement.Vertical verticalArrangement3;
        float horizontalItemSpacing3;
        FlingBehavior flingBehavior3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(rows, "rows");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-8666074);
        ComposerKt.sourceInformation($composer2, "C(LazyHorizontalStaggeredGrid)P(6,4,7,1,5,9,3:c#ui.unit.Dp,2,8)154@7006L32,159@7301L15,173@7816L59,163@7412L496:LazyStaggeredGridDsl.kt#fzvcnm");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(rows) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            paddingValues = contentPadding;
        } else if (($changed & 7168) == 0) {
            paddingValues = contentPadding;
            $dirty |= $composer2.changed(paddingValues) ? 2048 : 1024;
        } else {
            paddingValues = contentPadding;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            z = reverseLayout;
        } else if (($changed & 57344) == 0) {
            z = reverseLayout;
            $dirty |= $composer2.changed(z) ? 16384 : 8192;
        } else {
            z = reverseLayout;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            vertical = verticalArrangement;
        } else if (($changed & 458752) == 0) {
            vertical = verticalArrangement;
            $dirty |= $composer2.changed(vertical) ? 131072 : 65536;
        } else {
            vertical = verticalArrangement;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty |= $composer2.changed(horizontalItemSpacing) ? 1048576 : 524288;
        }
        if (($changed & 29360128) == 0) {
            $dirty |= ((i & 128) == 0 && $composer2.changed(flingBehavior)) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty |= $composer2.changed(userScrollEnabled) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) == 0) {
            if ((1879048192 & $changed) == 0) {
                i2 = $composer2.changedInstance(content) ? 536870912 : 268435456;
            }
            if (i4 != 4 && (1533916891 & $dirty) == 306783378 && $composer2.getSkipping()) {
                $composer2.skipToGroupEnd();
                horizontalItemSpacing3 = horizontalItemSpacing;
                flingBehavior3 = flingBehavior;
                userScrollEnabled3 = userScrollEnabled;
                contentPadding3 = paddingValues;
                verticalArrangement3 = vertical;
                reverseLayout3 = z;
                modifier3 = modifier;
                state3 = state;
            } else {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        state2 = LazyStaggeredGridStateKt.rememberLazyStaggeredGridState(0, 0, $composer2, 0, 3);
                        $dirty &= -897;
                    } else {
                        state2 = state;
                    }
                    if (i5 != 0) {
                        contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        contentPadding2 = paddingValues;
                    }
                    if (i6 != 0) {
                        reverseLayout2 = false;
                    } else {
                        reverseLayout2 = z;
                    }
                    if (i7 != 0) {
                        verticalArrangement2 = Arrangement.INSTANCE.m394spacedBy0680j_4(Dp.m5274constructorimpl(0));
                    } else {
                        verticalArrangement2 = vertical;
                    }
                    if (i8 != 0) {
                        horizontalItemSpacing2 = Dp.m5274constructorimpl(0);
                    } else {
                        horizontalItemSpacing2 = horizontalItemSpacing;
                    }
                    if ((i & 128) != 0) {
                        flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                        $dirty &= -29360129;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    if (i9 != 0) {
                        userScrollEnabled2 = true;
                    } else {
                        userScrollEnabled2 = userScrollEnabled;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if (i4 != 0) {
                        $dirty &= -897;
                    }
                    if ((i & 128) != 0) {
                        modifier2 = modifier;
                        state2 = state;
                        flingBehavior2 = flingBehavior;
                        $dirty &= -29360129;
                        contentPadding2 = paddingValues;
                        verticalArrangement2 = vertical;
                        reverseLayout2 = z;
                        horizontalItemSpacing2 = horizontalItemSpacing;
                        userScrollEnabled2 = userScrollEnabled;
                    } else {
                        modifier2 = modifier;
                        state2 = state;
                        flingBehavior2 = flingBehavior;
                        userScrollEnabled2 = userScrollEnabled;
                        contentPadding2 = paddingValues;
                        verticalArrangement2 = vertical;
                        reverseLayout2 = z;
                        horizontalItemSpacing2 = horizontalItemSpacing;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-8666074, $dirty, -1, "androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid (LazyStaggeredGridDsl.kt:151)");
                }
                LazyStaggeredGridKt.m671LazyStaggeredGridLJWHXA8(state2, Orientation.Horizontal, rememberRowSlots(rows, verticalArrangement2, contentPadding2, $composer2, (($dirty >> 3) & 896) | ($dirty & 14) | (($dirty >> 12) & 112)), modifier2, contentPadding2, reverseLayout2, flingBehavior2, userScrollEnabled2, horizontalItemSpacing2, verticalArrangement2.getSpacing(), content, $composer2, (($dirty << 6) & 7168) | 56 | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 6) & 234881024), ($dirty >> 27) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                userScrollEnabled3 = userScrollEnabled2;
                modifier3 = modifier2;
                state3 = state2;
                contentPadding3 = contentPadding2;
                reverseLayout3 = reverseLayout2;
                verticalArrangement3 = verticalArrangement2;
                horizontalItemSpacing3 = horizontalItemSpacing2;
                flingBehavior3 = flingBehavior2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final LazyStaggeredGridState lazyStaggeredGridState = state3;
            final PaddingValues paddingValues2 = contentPadding3;
            final boolean z2 = reverseLayout3;
            final Arrangement.Vertical vertical2 = verticalArrangement3;
            final float f = horizontalItemSpacing3;
            final FlingBehavior flingBehavior4 = flingBehavior3;
            final boolean z3 = userScrollEnabled3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$LazyHorizontalStaggeredGrid$1
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

                public final void invoke(Composer composer, int i10) {
                    LazyStaggeredGridDslKt.m665LazyHorizontalStaggeredGridcJHQLPU(rows, modifier4, lazyStaggeredGridState, paddingValues2, z2, vertical2, f, flingBehavior4, z3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty |= i2;
        if (i4 != 4) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    state2 = LazyStaggeredGridStateKt.rememberLazyStaggeredGridState(0, 0, $composer2, 0, 3);
                    $dirty &= -897;
                } else {
                    state2 = state;
                }
                if (i5 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    contentPadding2 = paddingValues;
                }
                if (i6 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = z;
                }
                if (i7 != 0) {
                    verticalArrangement2 = Arrangement.INSTANCE.m394spacedBy0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    verticalArrangement2 = vertical;
                }
                if (i8 != 0) {
                    horizontalItemSpacing2 = Dp.m5274constructorimpl(0);
                } else {
                    horizontalItemSpacing2 = horizontalItemSpacing;
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i9 != 0) {
                    userScrollEnabled2 = true;
                } else {
                    userScrollEnabled2 = userScrollEnabled;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    state2 = LazyStaggeredGridStateKt.rememberLazyStaggeredGridState(0, 0, $composer2, 0, 3);
                    $dirty &= -897;
                } else {
                    state2 = state;
                }
                if (i5 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    contentPadding2 = paddingValues;
                }
                if (i6 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = z;
                }
                if (i7 != 0) {
                    verticalArrangement2 = Arrangement.INSTANCE.m394spacedBy0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    verticalArrangement2 = vertical;
                }
                if (i8 != 0) {
                    horizontalItemSpacing2 = Dp.m5274constructorimpl(0);
                } else {
                    horizontalItemSpacing2 = horizontalItemSpacing;
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i9 != 0) {
                    userScrollEnabled2 = true;
                } else {
                    userScrollEnabled2 = userScrollEnabled;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-8666074, $dirty, -1, "androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid (LazyStaggeredGridDsl.kt:151)");
            }
            LazyStaggeredGridKt.m671LazyStaggeredGridLJWHXA8(state2, Orientation.Horizontal, rememberRowSlots(rows, verticalArrangement2, contentPadding2, $composer2, (($dirty >> 3) & 896) | ($dirty & 14) | (($dirty >> 12) & 112)), modifier2, contentPadding2, reverseLayout2, flingBehavior2, userScrollEnabled2, horizontalItemSpacing2, verticalArrangement2.getSpacing(), content, $composer2, (($dirty << 6) & 7168) | 56 | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 6) & 234881024), ($dirty >> 27) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            userScrollEnabled3 = userScrollEnabled2;
            modifier3 = modifier2;
            state3 = state2;
            contentPadding3 = contentPadding2;
            reverseLayout3 = reverseLayout2;
            verticalArrangement3 = verticalArrangement2;
            horizontalItemSpacing3 = horizontalItemSpacing2;
            flingBehavior3 = flingBehavior2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    state2 = LazyStaggeredGridStateKt.rememberLazyStaggeredGridState(0, 0, $composer2, 0, 3);
                    $dirty &= -897;
                } else {
                    state2 = state;
                }
                if (i5 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    contentPadding2 = paddingValues;
                }
                if (i6 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = z;
                }
                if (i7 != 0) {
                    verticalArrangement2 = Arrangement.INSTANCE.m394spacedBy0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    verticalArrangement2 = vertical;
                }
                if (i8 != 0) {
                    horizontalItemSpacing2 = Dp.m5274constructorimpl(0);
                } else {
                    horizontalItemSpacing2 = horizontalItemSpacing;
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i9 != 0) {
                    userScrollEnabled2 = true;
                } else {
                    userScrollEnabled2 = userScrollEnabled;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    state2 = LazyStaggeredGridStateKt.rememberLazyStaggeredGridState(0, 0, $composer2, 0, 3);
                    $dirty &= -897;
                } else {
                    state2 = state;
                }
                if (i5 != 0) {
                    contentPadding2 = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    contentPadding2 = paddingValues;
                }
                if (i6 != 0) {
                    reverseLayout2 = false;
                } else {
                    reverseLayout2 = z;
                }
                if (i7 != 0) {
                    verticalArrangement2 = Arrangement.INSTANCE.m394spacedBy0680j_4(Dp.m5274constructorimpl(0));
                } else {
                    verticalArrangement2 = vertical;
                }
                if (i8 != 0) {
                    horizontalItemSpacing2 = Dp.m5274constructorimpl(0);
                } else {
                    horizontalItemSpacing2 = horizontalItemSpacing;
                }
                if ((i & 128) != 0) {
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer2, 6);
                    $dirty &= -29360129;
                } else {
                    flingBehavior2 = flingBehavior;
                }
                if (i9 != 0) {
                    userScrollEnabled2 = true;
                } else {
                    userScrollEnabled2 = userScrollEnabled;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-8666074, $dirty, -1, "androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid (LazyStaggeredGridDsl.kt:151)");
            }
            LazyStaggeredGridKt.m671LazyStaggeredGridLJWHXA8(state2, Orientation.Horizontal, rememberRowSlots(rows, verticalArrangement2, contentPadding2, $composer2, (($dirty >> 3) & 896) | ($dirty & 14) | (($dirty >> 12) & 112)), modifier2, contentPadding2, reverseLayout2, flingBehavior2, userScrollEnabled2, horizontalItemSpacing2, verticalArrangement2.getSpacing(), content, $composer2, (($dirty << 6) & 7168) | 56 | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128) | (($dirty << 6) & 234881024), ($dirty >> 27) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            userScrollEnabled3 = userScrollEnabled2;
            modifier3 = modifier2;
            state3 = state2;
            contentPadding3 = contentPadding2;
            reverseLayout3 = reverseLayout2;
            verticalArrangement3 = verticalArrangement2;
            horizontalItemSpacing3 = horizontalItemSpacing2;
            flingBehavior3 = flingBehavior2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final LazyStaggeredGridState lazyStaggeredGridState2 = state3;
        final PaddingValues paddingValues3 = contentPadding3;
        final boolean z4 = reverseLayout3;
        final Arrangement.Vertical vertical3 = verticalArrangement3;
        final float f2 = horizontalItemSpacing3;
        final FlingBehavior flingBehavior5 = flingBehavior3;
        final boolean z5 = userScrollEnabled3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$LazyHorizontalStaggeredGrid$1
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

            public final void invoke(Composer composer, int i10) {
                LazyStaggeredGridDslKt.m665LazyHorizontalStaggeredGridcJHQLPU(rows, modifier5, lazyStaggeredGridState2, paddingValues3, z4, vertical3, f2, flingBehavior5, z5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    private static final Function2<Density, Constraints, LazyStaggeredGridSlots> rememberRowSlots(final StaggeredGridCells rows, final Arrangement.Vertical verticalArrangement, final PaddingValues contentPadding, Composer $composer, int $changed) {
        Object value$iv$iv;
        $composer.startReplaceableGroup(-675899235);
        ComposerKt.sourceInformation($composer, "C(rememberRowSlots)P(1,2)184@8133L953:LazyStaggeredGridDsl.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-675899235, $changed, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberRowSlots (LazyStaggeredGridDsl.kt:180)");
        }
        int i = ($changed & 14) | ($changed & 112) | ($changed & 896);
        $composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(rows) | $composer.changed(verticalArrangement) | $composer.changed(contentPadding);
        Object it$iv$iv = $composer.rememberedValue();
        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = new LazyStaggeredGridSlotCache(new Function2<Density, Constraints, LazyStaggeredGridSlots>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$rememberRowSlots$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyStaggeredGridSlots invoke(Density density, Constraints constraints) {
                    return m668invoke0kLqBqw(density, constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyStaggeredGridSlots m668invoke0kLqBqw(Density $receiver, long constraints) {
                    Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                    if (!(Constraints.m5217getMaxHeightimpl(constraints) != Integer.MAX_VALUE)) {
                        throw new IllegalArgumentException("LazyHorizontalStaggeredGrid's height should be bound by parent.".toString());
                    }
                    float arg0$iv = contentPadding.getTop();
                    float other$iv = contentPadding.getBottom();
                    int gridHeight = Constraints.m5217getMaxHeightimpl(constraints) - $receiver.mo321roundToPx0680j_4(Dp.m5274constructorimpl(arg0$iv + other$iv));
                    StaggeredGridCells $this$invoke_0kLqBqw_u24lambda_u243 = rows;
                    Arrangement.Vertical $this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241 = verticalArrangement;
                    int[] sizes = $this$invoke_0kLqBqw_u24lambda_u243.calculateCrossAxisCellSizes($receiver, gridHeight, $receiver.mo321roundToPx0680j_4($this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241.getSpacing()));
                    int[] positions = new int[sizes.length];
                    $this$invoke_0kLqBqw_u24lambda_u243_u24lambda_u242_u24lambda_u241.arrange($receiver, gridHeight, sizes, positions);
                    return new LazyStaggeredGridSlots(positions, sizes);
                }
            });
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        $composer.endReplaceableGroup();
        Function2<Density, Constraints, LazyStaggeredGridSlots> function2 = (Function2) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return function2;
    }

    public static /* synthetic */ void items$default(LazyStaggeredGridScope $this$items_u24default, List items, Function1 key, Function1 contentType, Function1 span, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            Function1 contentType2 = new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt.items.1
                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(T t) {
                    return null;
                }
            };
            contentType = contentType2;
        }
        if ((i & 8) != 0) {
            span = null;
        }
        Intrinsics.checkNotNullParameter($this$items_u24default, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        $this$items_u24default.items(items.size(), key != null ? new LazyStaggeredGridDslKt$items$2$1(key, items) : null, new AnonymousClass3(contentType, items), span != null ? new LazyStaggeredGridDslKt$items$4$1(span, items) : null, ComposableLambdaKt.composableLambdaInstance(-886456479, true, new AnonymousClass5(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$items$3, reason: invalid class name */
    /* JADX INFO: compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass3 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function1<T, Object> $contentType;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Function1<? super T, ? extends Object> function1, List<? extends T> list) {
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

    public static final <T> void items(LazyStaggeredGridScope $this$items, List<? extends T> items, Function1<? super T, ? extends Object> function1, Function1<? super T, ? extends Object> contentType, Function1<? super T, StaggeredGridItemSpan> function2, Function4<? super LazyStaggeredGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Function1 it;
        LazyStaggeredGridDslKt$items$4$1 lazyStaggeredGridDslKt$items$4$1;
        Intrinsics.checkNotNullParameter($this$items, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        int size = items.size();
        if (function1 == null) {
            it = null;
        } else {
            it = new LazyStaggeredGridDslKt$items$2$1(function1, items);
        }
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(contentType, items);
        if (function2 == null) {
            lazyStaggeredGridDslKt$items$4$1 = null;
        } else {
            lazyStaggeredGridDslKt$items$4$1 = new LazyStaggeredGridDslKt$items$4$1(function2, items);
        }
        $this$items.items(size, it, anonymousClass3, lazyStaggeredGridDslKt$items$4$1, ComposableLambdaKt.composableLambdaInstance(-886456479, true, new AnonymousClass5(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$items$5, reason: invalid class name */
    /* JADX INFO: compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "index", "", "invoke", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass5 extends Lambda implements Function4<LazyStaggeredGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function4<LazyStaggeredGridItemScope, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(Function4<? super LazyStaggeredGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4, List<? extends T> list) {
            super(4);
            this.$itemContent = function4;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyStaggeredGridItemScope lazyStaggeredGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyStaggeredGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final void invoke(LazyStaggeredGridItemScope items, int i, Composer composer, int i2) {
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation(composer, "C336@14825L25:LazyStaggeredGridDsl.kt#fzvcnm");
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
                ComposerKt.traceEventStart(-886456479, i3, -1, "androidx.compose.foundation.lazy.staggeredgrid.items.<anonymous> (LazyStaggeredGridDsl.kt:336)");
            }
            this.$itemContent.invoke(items, (T) this.$items.get(i), composer, Integer.valueOf(i3 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static /* synthetic */ void itemsIndexed$default(LazyStaggeredGridScope $this$itemsIndexed_u24default, List items, Function2 key, Function2 contentType, Function2 span, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            Function2 contentType2 = new Function2() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt.itemsIndexed.1
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
        if ((i & 8) != 0) {
            span = null;
        }
        Intrinsics.checkNotNullParameter($this$itemsIndexed_u24default, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        $this$itemsIndexed_u24default.items(items.size(), key != null ? new LazyStaggeredGridDslKt$itemsIndexed$2$1(key, items) : null, new C02473(contentType, items), span != null ? new LazyStaggeredGridDslKt$itemsIndexed$4$1(span, items) : null, ComposableLambdaKt.composableLambdaInstance(284833944, true, new C02485(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$itemsIndexed$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02473 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function2<Integer, T, Object> $contentType;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02473(Function2<? super Integer, ? super T, ? extends Object> function2, List<? extends T> list) {
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

    public static final <T> void itemsIndexed(LazyStaggeredGridScope $this$itemsIndexed, List<? extends T> items, Function2<? super Integer, ? super T, ? extends Object> function2, Function2<? super Integer, ? super T, ? extends Object> contentType, Function2<? super Integer, ? super T, StaggeredGridItemSpan> function3, Function5<? super LazyStaggeredGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        LazyStaggeredGridDslKt$itemsIndexed$2$1 lazyStaggeredGridDslKt$itemsIndexed$2$1;
        LazyStaggeredGridDslKt$itemsIndexed$4$1 lazyStaggeredGridDslKt$itemsIndexed$4$1;
        Intrinsics.checkNotNullParameter($this$itemsIndexed, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        int size = items.size();
        if (function2 == null) {
            lazyStaggeredGridDslKt$itemsIndexed$2$1 = null;
        } else {
            lazyStaggeredGridDslKt$itemsIndexed$2$1 = new LazyStaggeredGridDslKt$itemsIndexed$2$1(function2, items);
        }
        C02473 c02473 = new C02473(contentType, items);
        if (function3 == null) {
            lazyStaggeredGridDslKt$itemsIndexed$4$1 = null;
        } else {
            lazyStaggeredGridDslKt$itemsIndexed$4$1 = new LazyStaggeredGridDslKt$itemsIndexed$4$1(function3, items);
        }
        $this$itemsIndexed.items(size, lazyStaggeredGridDslKt$itemsIndexed$2$1, c02473, lazyStaggeredGridDslKt$itemsIndexed$4$1, ComposableLambdaKt.composableLambdaInstance(284833944, true, new C02485(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$itemsIndexed$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "index", "", "invoke", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02485 extends Lambda implements Function4<LazyStaggeredGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function5<LazyStaggeredGridItemScope, Integer, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02485(Function5<? super LazyStaggeredGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5, List<? extends T> list) {
            super(4);
            this.$itemContent = function5;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyStaggeredGridItemScope lazyStaggeredGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyStaggeredGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final void invoke(LazyStaggeredGridItemScope items, int i, Composer composer, int i2) {
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation(composer, "C375@16700L32:LazyStaggeredGridDsl.kt#fzvcnm");
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
                ComposerKt.traceEventStart(284833944, i3, -1, "androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed.<anonymous> (LazyStaggeredGridDsl.kt:375)");
            }
            this.$itemContent.invoke(items, Integer.valueOf(i), (T) this.$items.get(i), composer, Integer.valueOf((i3 & 14) | (i3 & 112)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static /* synthetic */ void items$default(LazyStaggeredGridScope $this$items_u24default, Object[] items, Function1 key, Function1 contentType, Function1 span, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            Function1 contentType2 = new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt.items.6
                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(T t) {
                    return null;
                }
            };
            contentType = contentType2;
        }
        if ((i & 8) != 0) {
            span = null;
        }
        Intrinsics.checkNotNullParameter($this$items_u24default, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        $this$items_u24default.items(items.length, key != null ? new LazyStaggeredGridDslKt$items$7$1(key, items) : null, new AnonymousClass8(contentType, items), span != null ? new LazyStaggeredGridDslKt$items$9$1(span, items) : null, ComposableLambdaKt.composableLambdaInstance(2101296000, true, new AnonymousClass10(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$items$8, reason: invalid class name */
    /* JADX INFO: compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass8 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function1<T, Object> $contentType;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass8(Function1<? super T, ? extends Object> function1, T[] tArr) {
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

    public static final <T> void items(LazyStaggeredGridScope $this$items, T[] items, Function1<? super T, ? extends Object> function1, Function1<? super T, ? extends Object> contentType, Function1<? super T, StaggeredGridItemSpan> function2, Function4<? super LazyStaggeredGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Function1 it;
        LazyStaggeredGridDslKt$items$9$1 lazyStaggeredGridDslKt$items$9$1;
        Intrinsics.checkNotNullParameter($this$items, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        int length = items.length;
        if (function1 == null) {
            it = null;
        } else {
            it = new LazyStaggeredGridDslKt$items$7$1(function1, items);
        }
        AnonymousClass8 anonymousClass8 = new AnonymousClass8(contentType, items);
        if (function2 == null) {
            lazyStaggeredGridDslKt$items$9$1 = null;
        } else {
            lazyStaggeredGridDslKt$items$9$1 = new LazyStaggeredGridDslKt$items$9$1(function2, items);
        }
        $this$items.items(length, it, anonymousClass8, lazyStaggeredGridDslKt$items$9$1, ComposableLambdaKt.composableLambdaInstance(2101296000, true, new AnonymousClass10(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$items$10, reason: invalid class name */
    /* JADX INFO: compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "index", "", "invoke", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass10 extends Lambda implements Function4<LazyStaggeredGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function4<LazyStaggeredGridItemScope, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass10(Function4<? super LazyStaggeredGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4, T[] tArr) {
            super(4);
            this.$itemContent = function4;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyStaggeredGridItemScope lazyStaggeredGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyStaggeredGridItemScope, num.intValue(), composer, num2.intValue());
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
        public final void invoke(LazyStaggeredGridItemScope items, int index, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation($composer, "C414@18477L25:LazyStaggeredGridDsl.kt#fzvcnm");
            int $dirty = $changed;
            if (($changed & 14) == 0) {
                $dirty |= $composer.changed(items) ? 4 : 2;
            }
            if (($changed & 112) == 0) {
                $dirty |= $composer.changed(index) ? 32 : 16;
            }
            if (($dirty & 731) == 146 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2101296000, $dirty, -1, "androidx.compose.foundation.lazy.staggeredgrid.items.<anonymous> (LazyStaggeredGridDsl.kt:414)");
            }
            this.$itemContent.invoke(items, this.$items[index], $composer, Integer.valueOf($dirty & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static /* synthetic */ void itemsIndexed$default(LazyStaggeredGridScope $this$itemsIndexed_u24default, Object[] items, Function2 key, Function2 contentType, Function2 span, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            Function2 contentType2 = new Function2() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt.itemsIndexed.6
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
        if ((i & 8) != 0) {
            span = null;
        }
        Intrinsics.checkNotNullParameter($this$itemsIndexed_u24default, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        $this$itemsIndexed_u24default.items(items.length, key != null ? new LazyStaggeredGridDslKt$itemsIndexed$7$1(key, items) : null, new C02508(contentType, items), span != null ? new LazyStaggeredGridDslKt$itemsIndexed$9$1(span, items) : null, ComposableLambdaKt.composableLambdaInstance(-804487775, true, new C024610(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$itemsIndexed$8, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02508 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function2<Integer, T, Object> $contentType;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02508(Function2<? super Integer, ? super T, ? extends Object> function2, T[] tArr) {
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

    public static final <T> void itemsIndexed(LazyStaggeredGridScope $this$itemsIndexed, T[] items, Function2<? super Integer, ? super T, ? extends Object> function2, Function2<? super Integer, ? super T, ? extends Object> contentType, Function2<? super Integer, ? super T, StaggeredGridItemSpan> function3, Function5<? super LazyStaggeredGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        LazyStaggeredGridDslKt$itemsIndexed$7$1 lazyStaggeredGridDslKt$itemsIndexed$7$1;
        LazyStaggeredGridDslKt$itemsIndexed$9$1 lazyStaggeredGridDslKt$itemsIndexed$9$1;
        Intrinsics.checkNotNullParameter($this$itemsIndexed, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        int length = items.length;
        if (function2 == null) {
            lazyStaggeredGridDslKt$itemsIndexed$7$1 = null;
        } else {
            lazyStaggeredGridDslKt$itemsIndexed$7$1 = new LazyStaggeredGridDslKt$itemsIndexed$7$1(function2, items);
        }
        C02508 c02508 = new C02508(contentType, items);
        if (function3 == null) {
            lazyStaggeredGridDslKt$itemsIndexed$9$1 = null;
        } else {
            lazyStaggeredGridDslKt$itemsIndexed$9$1 = new LazyStaggeredGridDslKt$itemsIndexed$9$1(function3, items);
        }
        $this$itemsIndexed.items(length, lazyStaggeredGridDslKt$itemsIndexed$7$1, c02508, lazyStaggeredGridDslKt$itemsIndexed$9$1, ComposableLambdaKt.composableLambdaInstance(-804487775, true, new C024610(itemContent, items)));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$itemsIndexed$10, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "index", "", "invoke", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C024610 extends Lambda implements Function4<LazyStaggeredGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function5<LazyStaggeredGridItemScope, Integer, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C024610(Function5<? super LazyStaggeredGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5, T[] tArr) {
            super(4);
            this.$itemContent = function5;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyStaggeredGridItemScope lazyStaggeredGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyStaggeredGridItemScope, num.intValue(), composer, num2.intValue());
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
        public final void invoke(LazyStaggeredGridItemScope items, int index, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation($composer, "C453@20356L32:LazyStaggeredGridDsl.kt#fzvcnm");
            int $dirty = $changed;
            if (($changed & 14) == 0) {
                $dirty |= $composer.changed(items) ? 4 : 2;
            }
            if (($changed & 112) == 0) {
                $dirty |= $composer.changed(index) ? 32 : 16;
            }
            if (($dirty & 731) == 146 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-804487775, $dirty, -1, "androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed.<anonymous> (LazyStaggeredGridDsl.kt:453)");
            }
            this.$itemContent.invoke(items, Integer.valueOf(index), this.$items[index], $composer, Integer.valueOf(($dirty & 14) | ($dirty & 112)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }
}
