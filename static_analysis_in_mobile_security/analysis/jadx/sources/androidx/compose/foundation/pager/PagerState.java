package androidx.compose.foundation.pager;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.ScrollExtensionsKt;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayoutKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.IntState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.layout.RemeasurementModifier;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PagerState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J:\u0010\u0099\u0001\u001a\u00030\u009a\u00012\u0007\u0010\u009b\u0001\u001a\u00020\u00032\t\b\u0002\u0010\u009c\u0001\u001a\u00020\u00052\u0010\b\u0002\u0010\u009d\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050\u009e\u0001H\u0086@ø\u0001\u0000¢\u0006\u0003\u0010\u009f\u0001J\u001a\u0010 \u0001\u001a\u00030\u009a\u00012\b\u0010¡\u0001\u001a\u00030¢\u0001H\u0000¢\u0006\u0003\b£\u0001J\u0014\u0010¤\u0001\u001a\u00030\u009a\u0001H\u0082@ø\u0001\u0000¢\u0006\u0003\u0010¥\u0001J\u0013\u0010¦\u0001\u001a\u00030\u009a\u00012\u0007\u0010§\u0001\u001a\u00020EH\u0002J\u0012\u0010¨\u0001\u001a\u00020\u00052\u0007\u0010©\u0001\u001a\u00020\u0005H\u0016J\u0010\u0010ª\u0001\u001a\u00020\u00052\u0007\u0010\u009b\u0001\u001a\u00020\u0003J\u0013\u0010«\u0001\u001a\u00030\u009a\u00012\u0007\u0010©\u0001\u001a\u00020\u0005H\u0002J\u0012\u0010¬\u0001\u001a\u00020\u00052\u0007\u0010\u00ad\u0001\u001a\u00020\u0005H\u0002JN\u0010®\u0001\u001a\u00030\u009a\u00012\b\u0010¯\u0001\u001a\u00030°\u00012.\u0010±\u0001\u001a)\b\u0001\u0012\u0005\u0012\u00030³\u0001\u0012\f\u0012\n\u0012\u0005\u0012\u00030\u009a\u00010´\u0001\u0012\u0007\u0012\u0005\u0018\u00010µ\u00010²\u0001¢\u0006\u0003\b¶\u0001H\u0096@ø\u0001\u0000¢\u0006\u0003\u0010·\u0001J(\u0010¸\u0001\u001a\u00030\u009a\u00012\u0007\u0010\u009b\u0001\u001a\u00020\u00032\t\b\u0002\u0010\u009c\u0001\u001a\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0003\u0010¹\u0001J\r\u0010º\u0001\u001a\u00020\u0003*\u00020\u0003H\u0002R+\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\u000f\u001a\u00020\u0010X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R+\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\u00178F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR+\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\u00178F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\"\u0010\u001e\u001a\u0004\b \u0010\u001a\"\u0004\b!\u0010\u001cR\u0011\u0010#\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b$\u0010\nR\u001b\u0010%\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b&\u0010'R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010,\u001a\u00020-X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0014\u00102\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b3\u0010'R\u0014\u00104\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010\nR\u0014\u00106\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b7\u0010\nR\u000e\u00108\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010'R\u0011\u0010;\u001a\u00020<8F¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0014\u0010?\u001a\u00020@X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u0014\u0010C\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010\u001aR\u0014\u0010D\u001a\u00020E8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bF\u0010GR\u001b\u0010H\u001a\u00020I8@X\u0080\u0084\u0002¢\u0006\f\u001a\u0004\bL\u0010M*\u0004\bJ\u0010KR\u001e\u0010N\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bO\u0010\nR\u0014\u0010P\u001a\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010\nR\u0012\u0010R\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\bS\u0010\nR\u0014\u0010T\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bU\u0010\nR\u0014\u0010V\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bW\u0010\nR\u0014\u0010X\u001a\b\u0012\u0004\u0012\u00020E0YX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010Z\u001a\u00020[X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010]R\u0014\u0010^\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b_\u0010'R\u0014\u0010`\u001a\u00020aX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010cR\u001a\u0010d\u001a\u00020\u0017X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\u001a\"\u0004\bf\u0010\u001cR%\u0010g\u001a\u00020hX\u0080\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010m\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR/\u0010o\u001a\u0004\u0018\u00010n2\b\u0010\u0007\u001a\u0004\u0018\u00010n8@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bt\u0010\u001e\u001a\u0004\bp\u0010q\"\u0004\br\u0010sR\u0014\u0010u\u001a\u00020vX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bw\u0010xR\u000e\u0010y\u001a\u00020zX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010{\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b|\u0010'R\u000e\u0010}\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010~\u001a\u00020\u00038FX\u0086\u0084\u0002¢\u0006\r\n\u0005\b\u0080\u0001\u0010)\u001a\u0004\b\u007f\u0010\nR/\u0010\u0081\u0001\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0015\n\u0005\b\u0084\u0001\u0010\u000e\u001a\u0005\b\u0082\u0001\u0010\n\"\u0005\b\u0083\u0001\u0010\fR1\u0010\u0085\u0001\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00058@@@X\u0080\u008e\u0002¢\u0006\u0017\n\u0006\b\u0089\u0001\u0010\u008a\u0001\u001a\u0005\b\u0086\u0001\u0010'\"\u0006\b\u0087\u0001\u0010\u0088\u0001R\u001e\u0010\u008b\u0001\u001a\u00020\u00038FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u008d\u0001\u0010)\u001a\u0005\b\u008c\u0001\u0010\nR:\u0010\u008f\u0001\u001a\u00030\u008e\u00012\u0007\u0010\u0007\u001a\u00030\u008e\u00018@@@X\u0080\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0015\n\u0005\b\u0092\u0001\u0010\u001e\u001a\u0005\b\u0090\u0001\u0010j\"\u0005\b\u0091\u0001\u0010lR\u001f\u0010\u0093\u0001\u001a\n\u0012\u0005\u0012\u00030\u0095\u00010\u0094\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001R\u000f\u0010\u0098\u0001\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006»\u0001"}, d2 = {"Landroidx/compose/foundation/pager/PagerState;", "Landroidx/compose/foundation/gestures/ScrollableState;", "initialPage", "", "initialPageOffsetFraction", "", "(IF)V", "<set-?>", "animationTargetPage", "getAnimationTargetPage", "()I", "setAnimationTargetPage", "(I)V", "animationTargetPage$delegate", "Landroidx/compose/runtime/MutableIntState;", "awaitLayoutModifier", "Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "getAwaitLayoutModifier$foundation_release", "()Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "beyondBoundsInfo", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "getBeyondBoundsInfo$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "", "canScrollBackward", "getCanScrollBackward", "()Z", "setCanScrollBackward", "(Z)V", "canScrollBackward$delegate", "Landroidx/compose/runtime/MutableState;", "canScrollForward", "getCanScrollForward", "setCanScrollForward", "canScrollForward$delegate", "currentPage", "getCurrentPage", "currentPageOffsetFraction", "getCurrentPageOffsetFraction", "()F", "currentPageOffsetFraction$delegate", "Landroidx/compose/runtime/State;", "currentPrefetchHandle", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "density", "Landroidx/compose/ui/unit/Density;", "getDensity$foundation_release", "()Landroidx/compose/ui/unit/Density;", "setDensity$foundation_release", "(Landroidx/compose/ui/unit/Density;)V", "distanceToSnapPosition", "getDistanceToSnapPosition", "firstVisiblePage", "getFirstVisiblePage$foundation_release", "firstVisiblePageOffset", "getFirstVisiblePageOffset$foundation_release", "indexToPrefetch", "getInitialPage", "getInitialPageOffsetFraction", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "getInteractionSource", "()Landroidx/compose/foundation/interaction/InteractionSource;", "internalInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getInternalInteractionSource$foundation_release", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "isScrollInProgress", "layoutInfo", "Landroidx/compose/foundation/pager/PagerLayoutInfo;", "getLayoutInfo$foundation_release", "()Landroidx/compose/foundation/pager/PagerLayoutInfo;", "nearestRange", "Lkotlin/ranges/IntRange;", "getNearestRange$foundation_release$delegate", "(Landroidx/compose/foundation/pager/PagerState;)Ljava/lang/Object;", "getNearestRange$foundation_release", "()Lkotlin/ranges/IntRange;", "numMeasurePasses", "getNumMeasurePasses$foundation_release", "pageAvailableSpace", "getPageAvailableSpace", "pageCount", "getPageCount", "pageSize", "getPageSize$foundation_release", "pageSpacing", "getPageSpacing$foundation_release", "pagerLayoutInfoState", "Landroidx/compose/runtime/MutableState;", "pinnedPages", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "getPinnedPages$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "positionThresholdFraction", "getPositionThresholdFraction", "prefetchState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "getPrefetchState$foundation_release", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "prefetchingEnabled", "getPrefetchingEnabled$foundation_release", "setPrefetchingEnabled$foundation_release", "premeasureConstraints", "Landroidx/compose/ui/unit/Constraints;", "getPremeasureConstraints-msEJaDk$foundation_release", "()J", "setPremeasureConstraints-BRTryo0$foundation_release", "(J)V", "J", "Landroidx/compose/ui/layout/Remeasurement;", "remeasurement", "getRemeasurement$foundation_release", "()Landroidx/compose/ui/layout/Remeasurement;", "setRemeasurement", "(Landroidx/compose/ui/layout/Remeasurement;)V", "remeasurement$delegate", "remeasurementModifier", "Landroidx/compose/ui/layout/RemeasurementModifier;", "getRemeasurementModifier$foundation_release", "()Landroidx/compose/ui/layout/RemeasurementModifier;", "scrollPosition", "Landroidx/compose/foundation/pager/PagerScrollPosition;", "scrollToBeConsumed", "getScrollToBeConsumed$foundation_release", "scrollableState", "settledPage", "getSettledPage", "settledPage$delegate", "settledPageState", "getSettledPageState", "setSettledPageState", "settledPageState$delegate", "snapRemainingScrollOffset", "getSnapRemainingScrollOffset$foundation_release", "setSnapRemainingScrollOffset$foundation_release", "(F)V", "snapRemainingScrollOffset$delegate", "Landroidx/compose/runtime/MutableFloatState;", "targetPage", "getTargetPage", "targetPage$delegate", "Landroidx/compose/ui/geometry/Offset;", "upDownDifference", "getUpDownDifference-F1C5BW0$foundation_release", "setUpDownDifference-k-4lQ0M$foundation_release", "upDownDifference$delegate", "visiblePages", "", "Landroidx/compose/foundation/pager/PageInfo;", "getVisiblePages", "()Ljava/util/List;", "wasScrollingForward", "animateScrollToPage", "", "page", "pageOffsetFraction", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(IFLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "applyMeasureResult", "result", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "applyMeasureResult$foundation_release", "awaitScrollDependencies", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelPrefetchIfVisibleItemsChanged", "info", "dispatchRawDelta", "delta", "getOffsetFractionForPage", "notifyPrefetch", "performScroll", "distance", "scroll", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/ScrollScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollToPage", "(IFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coerceInPageRange", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class PagerState implements ScrollableState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: animationTargetPage$delegate, reason: from kotlin metadata */
    private final MutableIntState animationTargetPage;
    private final AwaitFirstLayoutModifier awaitLayoutModifier;
    private final LazyLayoutBeyondBoundsInfo beyondBoundsInfo;

    /* JADX INFO: renamed from: canScrollBackward$delegate, reason: from kotlin metadata */
    private final MutableState canScrollBackward;

    /* JADX INFO: renamed from: canScrollForward$delegate, reason: from kotlin metadata */
    private final MutableState canScrollForward;

    /* JADX INFO: renamed from: currentPageOffsetFraction$delegate, reason: from kotlin metadata */
    private final State currentPageOffsetFraction;
    private LazyLayoutPrefetchState.PrefetchHandle currentPrefetchHandle;
    private Density density;
    private int indexToPrefetch;
    private final int initialPage;
    private final float initialPageOffsetFraction;
    private final MutableInteractionSource internalInteractionSource;
    private int numMeasurePasses;
    private MutableState<PagerLayoutInfo> pagerLayoutInfoState;
    private final LazyLayoutPinnedItemList pinnedPages;
    private final LazyLayoutPrefetchState prefetchState;
    private boolean prefetchingEnabled;
    private long premeasureConstraints;

    /* JADX INFO: renamed from: remeasurement$delegate, reason: from kotlin metadata */
    private final MutableState remeasurement;
    private final RemeasurementModifier remeasurementModifier;
    private final PagerScrollPosition scrollPosition;
    private float scrollToBeConsumed;
    private final ScrollableState scrollableState;

    /* JADX INFO: renamed from: settledPage$delegate, reason: from kotlin metadata */
    private final State settledPage;

    /* JADX INFO: renamed from: settledPageState$delegate, reason: from kotlin metadata */
    private final MutableIntState settledPageState;

    /* JADX INFO: renamed from: snapRemainingScrollOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState snapRemainingScrollOffset;

    /* JADX INFO: renamed from: targetPage$delegate, reason: from kotlin metadata */
    private final State targetPage;

    /* JADX INFO: renamed from: upDownDifference$delegate, reason: from kotlin metadata */
    private final MutableState upDownDifference;
    private boolean wasScrollingForward;

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerState$animateScrollToPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: PagerState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.pager.PagerState", f = "PagerState.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2}, l = {453, 478, 490}, m = "animateScrollToPage", n = {"this", "animationSpec", "page", "pageOffsetFraction", "this", "animationSpec", "pageOffsetFraction", "targetPage", "preJumpPosition", "this"}, s = {"L$0", "L$1", "I$0", "F$0", "L$0", "L$1", "F$0", "I$0", "I$1", "L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PagerState.this.animateScrollToPage(0, 0.0f, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerState$scroll$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PagerState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.pager.PagerState", f = "PagerState.kt", i = {0, 0, 0}, l = {502, 503}, m = "scroll$suspendImpl", n = {"$this", "scrollPriority", "block"}, s = {"L$0", "L$1", "L$2"})
    static final class C02521 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C02521(Continuation<? super C02521> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PagerState.scroll$suspendImpl(PagerState.this, null, null, this);
        }
    }

    public PagerState() {
        this(0, 0.0f, 3, null);
    }

    public abstract int getPageCount();

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public Object scroll(MutatePriority mutatePriority, Function2<? super ScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        return scroll$suspendImpl(this, mutatePriority, function2, continuation);
    }

    public PagerState(int initialPage, float initialPageOffsetFraction) {
        this.initialPage = initialPage;
        this.initialPageOffsetFraction = initialPageOffsetFraction;
        double d = initialPageOffsetFraction;
        if (!(-0.5d <= d && d <= 0.5d)) {
            throw new IllegalArgumentException(("initialPageOffsetFraction " + initialPageOffsetFraction + " is not within the range -0.5 to 0.5").toString());
        }
        this.upDownDifference = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m2720boximpl(Offset.INSTANCE.m2747getZeroF1C5BW0()), null, 2, null);
        this.snapRemainingScrollOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        PagerScrollPosition pagerScrollPosition = new PagerScrollPosition(initialPage, 0);
        this.scrollPosition = pagerScrollPosition;
        this.scrollableState = ScrollableStateKt.ScrollableState(new Function1<Float, Float>() { // from class: androidx.compose.foundation.pager.PagerState$scrollableState$1
            {
                super(1);
            }

            public final Float invoke(float it) {
                return Float.valueOf(-this.this$0.performScroll(-it));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Float invoke(Float f) {
                return invoke(f.floatValue());
            }
        });
        this.prefetchingEnabled = true;
        this.indexToPrefetch = -1;
        this.pagerLayoutInfoState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(PagerStateKt.getEmptyLayoutInfo(), null, 2, null);
        this.density = PagerStateKt.UnitDensity;
        this.internalInteractionSource = InteractionSourceKt.MutableInteractionSource();
        this.animationTargetPage = SnapshotIntStateKt.mutableIntStateOf(-1);
        this.settledPageState = SnapshotIntStateKt.mutableIntStateOf(initialPage);
        this.settledPage = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Integer>() { // from class: androidx.compose.foundation.pager.PagerState$settledPage$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                int currentPage;
                if (this.this$0.isScrollInProgress()) {
                    currentPage = this.this$0.getSettledPageState();
                } else {
                    currentPage = this.this$0.getCurrentPage();
                }
                return Integer.valueOf(currentPage);
            }
        });
        this.targetPage = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Integer>() { // from class: androidx.compose.foundation.pager.PagerState$targetPage$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                int finalPage;
                if (this.this$0.isScrollInProgress()) {
                    if (this.this$0.getAnimationTargetPage() != -1) {
                        finalPage = this.this$0.getAnimationTargetPage();
                    } else {
                        if (!(this.this$0.getSnapRemainingScrollOffset$foundation_release() == 0.0f)) {
                            float pageDisplacement = this.this$0.getSnapRemainingScrollOffset$foundation_release() / this.this$0.getPageAvailableSpace();
                            finalPage = this.this$0.getCurrentPage() + MathKt.roundToInt(pageDisplacement);
                        } else if (Math.abs(this.this$0.getCurrentPageOffsetFraction()) >= Math.abs(this.this$0.getPositionThresholdFraction())) {
                            finalPage = this.this$0.getCurrentPage() + ((int) Math.signum(this.this$0.getCurrentPageOffsetFraction()));
                        } else {
                            finalPage = this.this$0.getCurrentPage();
                        }
                    }
                } else {
                    finalPage = this.this$0.getCurrentPage();
                }
                return Integer.valueOf(this.this$0.coerceInPageRange(finalPage));
            }
        });
        this.currentPageOffsetFraction = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Float>() { // from class: androidx.compose.foundation.pager.PagerState$currentPageOffsetFraction$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                Object it$iv;
                float fCoerceIn;
                List<PageInfo> visiblePagesInfo = this.this$0.getLayoutInfo$foundation_release().getVisiblePagesInfo();
                PagerState pagerState = this.this$0;
                int index$iv$iv = 0;
                int size = visiblePagesInfo.size();
                while (true) {
                    if (index$iv$iv < size) {
                        Object item$iv$iv = visiblePagesInfo.get(index$iv$iv);
                        it$iv = item$iv$iv;
                        PageInfo it = (PageInfo) it$iv;
                        if (it.getIndex() == pagerState.getCurrentPage()) {
                            break;
                        }
                        index$iv$iv++;
                    } else {
                        it$iv = null;
                        break;
                    }
                }
                PageInfo pageInfo = (PageInfo) it$iv;
                int currentPagePositionOffset = pageInfo != null ? pageInfo.getOffset() : 0;
                float pageUsedSpace = this.this$0.getPageAvailableSpace();
                if (pageUsedSpace == 0.0f) {
                    fCoerceIn = this.this$0.getInitialPageOffsetFraction();
                } else {
                    fCoerceIn = RangesKt.coerceIn((-currentPagePositionOffset) / pageUsedSpace, -0.5f, 0.5f);
                }
                return Float.valueOf(fCoerceIn);
            }
        });
        this.prefetchState = new LazyLayoutPrefetchState();
        this.beyondBoundsInfo = new LazyLayoutBeyondBoundsInfo();
        this.awaitLayoutModifier = new AwaitFirstLayoutModifier();
        this.remeasurement = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.remeasurementModifier = new RemeasurementModifier() { // from class: androidx.compose.foundation.pager.PagerState$remeasurementModifier$1
            @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
            public /* synthetic */ boolean all(Function1 function1) {
                return Modifier.Element.CC.$default$all(this, function1);
            }

            @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
            public /* synthetic */ boolean any(Function1 function1) {
                return Modifier.Element.CC.$default$any(this, function1);
            }

            @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
            public /* synthetic */ Object foldIn(Object obj, Function2 function2) {
                return Modifier.Element.CC.$default$foldIn(this, obj, function2);
            }

            @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
            public /* synthetic */ Object foldOut(Object obj, Function2 function2) {
                return Modifier.Element.CC.$default$foldOut(this, obj, function2);
            }

            @Override // androidx.compose.ui.Modifier
            public /* synthetic */ Modifier then(Modifier modifier) {
                return Modifier.CC.$default$then(this, modifier);
            }

            @Override // androidx.compose.ui.layout.RemeasurementModifier
            public void onRemeasurementAvailable(Remeasurement remeasurement) {
                Intrinsics.checkNotNullParameter(remeasurement, "remeasurement");
                this.this$0.setRemeasurement(remeasurement);
            }
        };
        this.premeasureConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null);
        this.pinnedPages = new LazyLayoutPinnedItemList();
        pagerScrollPosition.getNearestRangeState();
        this.canScrollForward = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.canScrollBackward = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
    }

    public /* synthetic */ PagerState(int i, float f, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? 0.0f : f);
    }

    public final int getInitialPage() {
        return this.initialPage;
    }

    public final float getInitialPageOffsetFraction() {
        return this.initialPageOffsetFraction;
    }

    /* JADX INFO: renamed from: getUpDownDifference-F1C5BW0$foundation_release, reason: not valid java name */
    public final long m716getUpDownDifferenceF1C5BW0$foundation_release() {
        State $this$getValue$iv = this.upDownDifference;
        return ((Offset) $this$getValue$iv.getValue()).getPackedValue();
    }

    /* JADX INFO: renamed from: setUpDownDifference-k-4lQ0M$foundation_release, reason: not valid java name */
    public final void m718setUpDownDifferencek4lQ0M$foundation_release(long j) {
        MutableState $this$setValue$iv = this.upDownDifference;
        $this$setValue$iv.setValue(Offset.m2720boximpl(j));
    }

    public final float getSnapRemainingScrollOffset$foundation_release() {
        FloatState $this$getValue$iv = this.snapRemainingScrollOffset;
        return $this$getValue$iv.getFloatValue();
    }

    public final void setSnapRemainingScrollOffset$foundation_release(float f) {
        MutableFloatState $this$setValue$iv = this.snapRemainingScrollOffset;
        $this$setValue$iv.setFloatValue(f);
    }

    public final int getFirstVisiblePage$foundation_release() {
        return this.scrollPosition.getFirstVisiblePage();
    }

    public final int getFirstVisiblePageOffset$foundation_release() {
        return this.scrollPosition.getScrollOffset();
    }

    /* JADX INFO: renamed from: getScrollToBeConsumed$foundation_release, reason: from getter */
    public final float getScrollToBeConsumed() {
        return this.scrollToBeConsumed;
    }

    /* JADX INFO: renamed from: getNumMeasurePasses$foundation_release, reason: from getter */
    public final int getNumMeasurePasses() {
        return this.numMeasurePasses;
    }

    /* JADX INFO: renamed from: getPrefetchingEnabled$foundation_release, reason: from getter */
    public final boolean getPrefetchingEnabled() {
        return this.prefetchingEnabled;
    }

    public final void setPrefetchingEnabled$foundation_release(boolean z) {
        this.prefetchingEnabled = z;
    }

    public final PagerLayoutInfo getLayoutInfo$foundation_release() {
        return this.pagerLayoutInfoState.getValue();
    }

    public final int getPageSpacing$foundation_release() {
        return this.pagerLayoutInfoState.getValue().getPageSpacing();
    }

    public final int getPageSize$foundation_release() {
        return this.pagerLayoutInfoState.getValue().getPageSize();
    }

    /* JADX INFO: renamed from: getDensity$foundation_release, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    public final void setDensity$foundation_release(Density density) {
        Intrinsics.checkNotNullParameter(density, "<set-?>");
        this.density = density;
    }

    private final List<PageInfo> getVisiblePages() {
        return this.pagerLayoutInfoState.getValue().getVisiblePagesInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getPageAvailableSpace() {
        return getPageSize$foundation_release() + getPageSpacing$foundation_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getPositionThresholdFraction() {
        Density $this$_get_positionThresholdFraction__u24lambda_u241 = this.density;
        float minThreshold = Math.min($this$_get_positionThresholdFraction__u24lambda_u241.mo327toPx0680j_4(PagerStateKt.getDefaultPositionThreshold()), getPageSize$foundation_release() / 2.0f);
        return minThreshold / getPageSize$foundation_release();
    }

    private final float getDistanceToSnapPosition() {
        PageInfo it = getLayoutInfo$foundation_release().getClosestPageToSnapPosition();
        if (it != null) {
            return SnapPositionInLayoutKt.calculateDistanceToDesiredSnapPosition(this.density, PagerLayoutInfoKt.getMainAxisViewportSize(getLayoutInfo$foundation_release()), getLayoutInfo$foundation_release().getBeforeContentPadding(), getLayoutInfo$foundation_release().getAfterContentPadding(), getLayoutInfo$foundation_release().getPageSize(), it.getOffset(), it.getIndex(), PagerStateKt.getSnapAlignmentStartToStart());
        }
        return 0.0f;
    }

    /* JADX INFO: renamed from: getInternalInteractionSource$foundation_release, reason: from getter */
    public final MutableInteractionSource getInternalInteractionSource() {
        return this.internalInteractionSource;
    }

    public final InteractionSource getInteractionSource() {
        return this.internalInteractionSource;
    }

    public final int getCurrentPage() {
        return this.scrollPosition.getCurrentPage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getAnimationTargetPage() {
        IntState $this$getValue$iv = this.animationTargetPage;
        return $this$getValue$iv.getIntValue();
    }

    private final void setAnimationTargetPage(int i) {
        MutableIntState $this$setValue$iv = this.animationTargetPage;
        $this$setValue$iv.setIntValue(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSettledPageState() {
        IntState $this$getValue$iv = this.settledPageState;
        return $this$getValue$iv.getIntValue();
    }

    private final void setSettledPageState(int i) {
        MutableIntState $this$setValue$iv = this.settledPageState;
        $this$setValue$iv.setIntValue(i);
    }

    public final int getSettledPage() {
        State $this$getValue$iv = this.settledPage;
        return ((Number) $this$getValue$iv.getValue()).intValue();
    }

    public final int getTargetPage() {
        State $this$getValue$iv = this.targetPage;
        return ((Number) $this$getValue$iv.getValue()).intValue();
    }

    public final float getCurrentPageOffsetFraction() {
        State $this$getValue$iv = this.currentPageOffsetFraction;
        return ((Number) $this$getValue$iv.getValue()).floatValue();
    }

    /* JADX INFO: renamed from: getPrefetchState$foundation_release, reason: from getter */
    public final LazyLayoutPrefetchState getPrefetchState() {
        return this.prefetchState;
    }

    /* JADX INFO: renamed from: getBeyondBoundsInfo$foundation_release, reason: from getter */
    public final LazyLayoutBeyondBoundsInfo getBeyondBoundsInfo() {
        return this.beyondBoundsInfo;
    }

    /* JADX INFO: renamed from: getAwaitLayoutModifier$foundation_release, reason: from getter */
    public final AwaitFirstLayoutModifier getAwaitLayoutModifier() {
        return this.awaitLayoutModifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRemeasurement(Remeasurement remeasurement) {
        MutableState $this$setValue$iv = this.remeasurement;
        $this$setValue$iv.setValue(remeasurement);
    }

    public final Remeasurement getRemeasurement$foundation_release() {
        State $this$getValue$iv = this.remeasurement;
        return (Remeasurement) $this$getValue$iv.getValue();
    }

    /* JADX INFO: renamed from: getRemeasurementModifier$foundation_release, reason: from getter */
    public final RemeasurementModifier getRemeasurementModifier() {
        return this.remeasurementModifier;
    }

    /* JADX INFO: renamed from: getPremeasureConstraints-msEJaDk$foundation_release, reason: not valid java name and from getter */
    public final long getPremeasureConstraints() {
        return this.premeasureConstraints;
    }

    /* JADX INFO: renamed from: setPremeasureConstraints-BRTryo0$foundation_release, reason: not valid java name */
    public final void m717setPremeasureConstraintsBRTryo0$foundation_release(long j) {
        this.premeasureConstraints = j;
    }

    /* JADX INFO: renamed from: getPinnedPages$foundation_release, reason: from getter */
    public final LazyLayoutPinnedItemList getPinnedPages() {
        return this.pinnedPages;
    }

    public final IntRange getNearestRange$foundation_release() {
        State $this$getValue$iv = this.scrollPosition.getNearestRangeState();
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerState$scrollToPage$2, reason: invalid class name */
    /* JADX INFO: compiled from: PagerState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.foundation.pager.PagerState$scrollToPage$2", f = "PagerState.kt", i = {}, l = {421}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $page;
        final /* synthetic */ float $pageOffsetFraction;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(float f, int i, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$pageOffsetFraction = f;
            this.$page = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PagerState.this.new AnonymousClass2(this.$pageOffsetFraction, this.$page, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AnonymousClass2 anonymousClass2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (PagerState.this.awaitScrollDependencies(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    anonymousClass2 = this;
                    break;
                    break;
                case 1:
                    anonymousClass2 = this;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            float f = anonymousClass2.$pageOffsetFraction;
            double d = f;
            if (!(-0.5d <= d && d <= 0.5d)) {
                throw new IllegalArgumentException(("pageOffsetFraction " + f + " is not within the range -0.5 to 0.5").toString());
            }
            PagerState.this.scrollPosition.requestPosition(PagerState.this.coerceInPageRange(anonymousClass2.$page), MathKt.roundToInt(PagerState.this.getPageAvailableSpace() * anonymousClass2.$pageOffsetFraction));
            Remeasurement remeasurement$foundation_release = PagerState.this.getRemeasurement$foundation_release();
            if (remeasurement$foundation_release != null) {
                remeasurement$foundation_release.forceRemeasure();
            }
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object scrollToPage$default(PagerState pagerState, int i, float f, Continuation continuation, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scrollToPage");
        }
        if ((i2 & 2) != 0) {
            f = 0.0f;
        }
        return pagerState.scrollToPage(i, f, continuation);
    }

    public final Object scrollToPage(int page, float pageOffsetFraction, Continuation<? super Unit> continuation) {
        Object objScroll$default = ScrollableState.CC.scroll$default(this, null, new AnonymousClass2(pageOffsetFraction, page, null), continuation, 1, null);
        return objScroll$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll$default : Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateScrollToPage$default(PagerState pagerState, int i, float f, AnimationSpec animationSpec, Continuation continuation, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: animateScrollToPage");
        }
        if ((i2 & 2) != 0) {
            f = 0.0f;
        }
        if ((i2 & 4) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        return pagerState.animateScrollToPage(i, f, animationSpec, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:37:0x00db  */
    /* JADX WARN: Code duplicated, block: B:39:0x00e1 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:40:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:42:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:44:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:45:0x0104  */
    /* JADX WARN: Code duplicated, block: B:48:0x012d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:49:0x012e  */
    /* JADX WARN: Code duplicated, block: B:54:0x016b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:57:0x0173  */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    public final Object animateScrollToPage(int page, float pageOffsetFraction, AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        PagerState pagerState;
        float pageOffsetFraction2;
        int page2;
        AnimationSpec<Float> animationSpec2;
        double d;
        int currentPosition;
        int targetPage;
        int lastVisiblePageIndex;
        int currentPosition2;
        PagerState pagerState2;
        int firstVisiblePageIndex;
        int iCoerceAtMost;
        int preJumpPosition;
        float pageOffsetFraction3;
        AnimationSpec<Float> animationSpec3;
        PagerState pagerState3;
        int targetPage2;
        int preJumpPosition2;
        float displacement;
        if (continuation instanceof AnonymousClass1) {
            AnonymousClass1 anonymousClass2 = (AnonymousClass1) continuation;
            if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                anonymousClass2.label -= Integer.MIN_VALUE;
                anonymousClass1 = anonymousClass2;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object $result = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        boolean z = false;
        switch (anonymousClass1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (page == getCurrentPage()) {
                    if (getCurrentPageOffsetFraction() == pageOffsetFraction) {
                        return Unit.INSTANCE;
                    }
                }
                anonymousClass1.L$0 = this;
                anonymousClass1.L$1 = animationSpec;
                anonymousClass1.I$0 = page;
                anonymousClass1.F$0 = pageOffsetFraction;
                anonymousClass1.label = 1;
                if (awaitScrollDependencies(anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                pagerState = this;
                pageOffsetFraction2 = pageOffsetFraction;
                page2 = page;
                animationSpec2 = animationSpec;
                d = pageOffsetFraction2;
                if (-0.5d <= d && d <= 0.5d) {
                    z = true;
                }
                if (z) {
                    throw new IllegalArgumentException(("pageOffsetFraction " + pageOffsetFraction2 + " is not within the range -0.5 to 0.5").toString());
                }
                currentPosition = pagerState.getCurrentPage();
                targetPage = pagerState.coerceInPageRange(page2);
                pagerState.setAnimationTargetPage(targetPage);
                int firstVisiblePageIndex2 = ((PageInfo) CollectionsKt.first((List) pagerState.getVisiblePages())).getIndex();
                int lastVisiblePageIndex2 = ((PageInfo) CollectionsKt.last((List) pagerState.getVisiblePages())).getIndex();
                if (page2 <= pagerState.getCurrentPage() && page2 > lastVisiblePageIndex2) {
                    firstVisiblePageIndex = pagerState.getCurrentPage();
                    if (Math.abs(page2 - firstVisiblePageIndex) >= 3) {
                        if (page2 > pagerState.getCurrentPage()) {
                            iCoerceAtMost = RangesKt.coerceAtLeast(page2 - pagerState.getVisiblePages().size(), currentPosition);
                        } else {
                            iCoerceAtMost = RangesKt.coerceAtMost(pagerState.getVisiblePages().size(), currentPosition) + page2;
                        }
                        preJumpPosition = iCoerceAtMost;
                        anonymousClass1.L$0 = pagerState;
                        anonymousClass1.L$1 = animationSpec2;
                        anonymousClass1.F$0 = pageOffsetFraction2;
                        anonymousClass1.I$0 = targetPage;
                        anonymousClass1.I$1 = preJumpPosition;
                        anonymousClass1.label = 2;
                        if (scrollToPage$default(pagerState, iCoerceAtMost, 0.0f, anonymousClass1, 2, null) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        pageOffsetFraction3 = pageOffsetFraction2;
                        animationSpec3 = animationSpec2;
                        pagerState3 = pagerState;
                        targetPage2 = targetPage;
                        preJumpPosition2 = preJumpPosition;
                        currentPosition2 = preJumpPosition2;
                        targetPage = targetPage2;
                        pageOffsetFraction2 = pageOffsetFraction3;
                        animationSpec2 = animationSpec3;
                        pagerState2 = pagerState3;
                    }
                    int targetOffset = targetPage * pagerState2.getPageAvailableSpace();
                    int currentOffset = currentPosition2 * pagerState2.getPageAvailableSpace();
                    float pageOffsetToSnappedPosition = pagerState2.getDistanceToSnapPosition() + (pagerState2.getPageAvailableSpace() * pageOffsetFraction2);
                    displacement = (targetOffset - currentOffset) + pageOffsetToSnappedPosition;
                    anonymousClass1.L$0 = pagerState2;
                    anonymousClass1.L$1 = null;
                    anonymousClass1.label = 3;
                    if (ScrollExtensionsKt.animateScrollBy(pagerState2, displacement, animationSpec2, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pagerState2.setAnimationTargetPage(-1);
                    return Unit.INSTANCE;
                }
                lastVisiblePageIndex = pagerState.getCurrentPage();
                if (page2 < lastVisiblePageIndex && page2 < firstVisiblePageIndex2) {
                    firstVisiblePageIndex = pagerState.getCurrentPage();
                    if (Math.abs(page2 - firstVisiblePageIndex) >= 3) {
                        if (page2 > pagerState.getCurrentPage()) {
                            iCoerceAtMost = RangesKt.coerceAtLeast(page2 - pagerState.getVisiblePages().size(), currentPosition);
                        } else {
                            iCoerceAtMost = RangesKt.coerceAtMost(pagerState.getVisiblePages().size(), currentPosition) + page2;
                        }
                        preJumpPosition = iCoerceAtMost;
                        anonymousClass1.L$0 = pagerState;
                        anonymousClass1.L$1 = animationSpec2;
                        anonymousClass1.F$0 = pageOffsetFraction2;
                        anonymousClass1.I$0 = targetPage;
                        anonymousClass1.I$1 = preJumpPosition;
                        anonymousClass1.label = 2;
                        if (scrollToPage$default(pagerState, iCoerceAtMost, 0.0f, anonymousClass1, 2, null) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        pageOffsetFraction3 = pageOffsetFraction2;
                        animationSpec3 = animationSpec2;
                        pagerState3 = pagerState;
                        targetPage2 = targetPage;
                        preJumpPosition2 = preJumpPosition;
                        currentPosition2 = preJumpPosition2;
                        targetPage = targetPage2;
                        pageOffsetFraction2 = pageOffsetFraction3;
                        animationSpec2 = animationSpec3;
                        pagerState2 = pagerState3;
                    }
                    int targetOffset2 = targetPage * pagerState2.getPageAvailableSpace();
                    int currentOffset2 = currentPosition2 * pagerState2.getPageAvailableSpace();
                    float pageOffsetToSnappedPosition2 = pagerState2.getDistanceToSnapPosition() + (pagerState2.getPageAvailableSpace() * pageOffsetFraction2);
                    displacement = (targetOffset2 - currentOffset2) + pageOffsetToSnappedPosition2;
                    anonymousClass1.L$0 = pagerState2;
                    anonymousClass1.L$1 = null;
                    anonymousClass1.label = 3;
                    if (ScrollExtensionsKt.animateScrollBy(pagerState2, displacement, animationSpec2, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pagerState2.setAnimationTargetPage(-1);
                    return Unit.INSTANCE;
                }
                currentPosition2 = currentPosition;
                pagerState2 = pagerState;
                int targetOffset3 = targetPage * pagerState2.getPageAvailableSpace();
                int currentOffset3 = currentPosition2 * pagerState2.getPageAvailableSpace();
                float pageOffsetToSnappedPosition3 = pagerState2.getDistanceToSnapPosition() + (pagerState2.getPageAvailableSpace() * pageOffsetFraction2);
                displacement = (targetOffset3 - currentOffset3) + pageOffsetToSnappedPosition3;
                anonymousClass1.L$0 = pagerState2;
                anonymousClass1.L$1 = null;
                anonymousClass1.label = 3;
                if (ScrollExtensionsKt.animateScrollBy(pagerState2, displacement, animationSpec2, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                pagerState2.setAnimationTargetPage(-1);
                return Unit.INSTANCE;
            case 1:
                float pageOffsetFraction4 = anonymousClass1.F$0;
                page2 = anonymousClass1.I$0;
                AnimationSpec<Float> animationSpec4 = (AnimationSpec) anonymousClass1.L$1;
                PagerState pagerState4 = (PagerState) anonymousClass1.L$0;
                ResultKt.throwOnFailure($result);
                pageOffsetFraction2 = pageOffsetFraction4;
                animationSpec2 = animationSpec4;
                pagerState = pagerState4;
                d = pageOffsetFraction2;
                if (-0.5d <= d) {
                    z = true;
                }
                if (z) {
                    throw new IllegalArgumentException(("pageOffsetFraction " + pageOffsetFraction2 + " is not within the range -0.5 to 0.5").toString());
                }
                currentPosition = pagerState.getCurrentPage();
                targetPage = pagerState.coerceInPageRange(page2);
                pagerState.setAnimationTargetPage(targetPage);
                int firstVisiblePageIndex3 = ((PageInfo) CollectionsKt.first((List) pagerState.getVisiblePages())).getIndex();
                int lastVisiblePageIndex3 = ((PageInfo) CollectionsKt.last((List) pagerState.getVisiblePages())).getIndex();
                if (page2 <= pagerState.getCurrentPage()) {
                    lastVisiblePageIndex = pagerState.getCurrentPage();
                    if (page2 < lastVisiblePageIndex) {
                        firstVisiblePageIndex = pagerState.getCurrentPage();
                        if (Math.abs(page2 - firstVisiblePageIndex) >= 3) {
                            if (page2 > pagerState.getCurrentPage()) {
                                iCoerceAtMost = RangesKt.coerceAtLeast(page2 - pagerState.getVisiblePages().size(), currentPosition);
                            } else {
                                iCoerceAtMost = RangesKt.coerceAtMost(pagerState.getVisiblePages().size(), currentPosition) + page2;
                            }
                            preJumpPosition = iCoerceAtMost;
                            anonymousClass1.L$0 = pagerState;
                            anonymousClass1.L$1 = animationSpec2;
                            anonymousClass1.F$0 = pageOffsetFraction2;
                            anonymousClass1.I$0 = targetPage;
                            anonymousClass1.I$1 = preJumpPosition;
                            anonymousClass1.label = 2;
                            if (scrollToPage$default(pagerState, iCoerceAtMost, 0.0f, anonymousClass1, 2, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            pageOffsetFraction3 = pageOffsetFraction2;
                            animationSpec3 = animationSpec2;
                            pagerState3 = pagerState;
                            targetPage2 = targetPage;
                            preJumpPosition2 = preJumpPosition;
                            currentPosition2 = preJumpPosition2;
                            targetPage = targetPage2;
                            pageOffsetFraction2 = pageOffsetFraction3;
                            animationSpec2 = animationSpec3;
                            pagerState2 = pagerState3;
                        }
                        int targetOffset4 = targetPage * pagerState2.getPageAvailableSpace();
                        int currentOffset4 = currentPosition2 * pagerState2.getPageAvailableSpace();
                        float pageOffsetToSnappedPosition4 = pagerState2.getDistanceToSnapPosition() + (pagerState2.getPageAvailableSpace() * pageOffsetFraction2);
                        displacement = (targetOffset4 - currentOffset4) + pageOffsetToSnappedPosition4;
                        anonymousClass1.L$0 = pagerState2;
                        anonymousClass1.L$1 = null;
                        anonymousClass1.label = 3;
                        if (ScrollExtensionsKt.animateScrollBy(pagerState2, displacement, animationSpec2, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        pagerState2.setAnimationTargetPage(-1);
                        return Unit.INSTANCE;
                    }
                } else {
                    lastVisiblePageIndex = pagerState.getCurrentPage();
                    if (page2 < lastVisiblePageIndex) {
                        firstVisiblePageIndex = pagerState.getCurrentPage();
                        if (Math.abs(page2 - firstVisiblePageIndex) >= 3) {
                            if (page2 > pagerState.getCurrentPage()) {
                                iCoerceAtMost = RangesKt.coerceAtLeast(page2 - pagerState.getVisiblePages().size(), currentPosition);
                            } else {
                                iCoerceAtMost = RangesKt.coerceAtMost(pagerState.getVisiblePages().size(), currentPosition) + page2;
                            }
                            preJumpPosition = iCoerceAtMost;
                            anonymousClass1.L$0 = pagerState;
                            anonymousClass1.L$1 = animationSpec2;
                            anonymousClass1.F$0 = pageOffsetFraction2;
                            anonymousClass1.I$0 = targetPage;
                            anonymousClass1.I$1 = preJumpPosition;
                            anonymousClass1.label = 2;
                            if (scrollToPage$default(pagerState, iCoerceAtMost, 0.0f, anonymousClass1, 2, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            pageOffsetFraction3 = pageOffsetFraction2;
                            animationSpec3 = animationSpec2;
                            pagerState3 = pagerState;
                            targetPage2 = targetPage;
                            preJumpPosition2 = preJumpPosition;
                            currentPosition2 = preJumpPosition2;
                            targetPage = targetPage2;
                            pageOffsetFraction2 = pageOffsetFraction3;
                            animationSpec2 = animationSpec3;
                            pagerState2 = pagerState3;
                        }
                        int targetOffset5 = targetPage * pagerState2.getPageAvailableSpace();
                        int currentOffset5 = currentPosition2 * pagerState2.getPageAvailableSpace();
                        float pageOffsetToSnappedPosition5 = pagerState2.getDistanceToSnapPosition() + (pagerState2.getPageAvailableSpace() * pageOffsetFraction2);
                        displacement = (targetOffset5 - currentOffset5) + pageOffsetToSnappedPosition5;
                        anonymousClass1.L$0 = pagerState2;
                        anonymousClass1.L$1 = null;
                        anonymousClass1.label = 3;
                        if (ScrollExtensionsKt.animateScrollBy(pagerState2, displacement, animationSpec2, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        pagerState2.setAnimationTargetPage(-1);
                        return Unit.INSTANCE;
                    }
                }
                currentPosition2 = currentPosition;
                pagerState2 = pagerState;
                int targetOffset6 = targetPage * pagerState2.getPageAvailableSpace();
                int currentOffset6 = currentPosition2 * pagerState2.getPageAvailableSpace();
                float pageOffsetToSnappedPosition6 = pagerState2.getDistanceToSnapPosition() + (pagerState2.getPageAvailableSpace() * pageOffsetFraction2);
                displacement = (targetOffset6 - currentOffset6) + pageOffsetToSnappedPosition6;
                anonymousClass1.L$0 = pagerState2;
                anonymousClass1.L$1 = null;
                anonymousClass1.label = 3;
                if (ScrollExtensionsKt.animateScrollBy(pagerState2, displacement, animationSpec2, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                pagerState2.setAnimationTargetPage(-1);
                return Unit.INSTANCE;
            case 2:
                preJumpPosition2 = anonymousClass1.I$1;
                targetPage2 = anonymousClass1.I$0;
                pageOffsetFraction3 = anonymousClass1.F$0;
                animationSpec3 = (AnimationSpec) anonymousClass1.L$1;
                pagerState3 = (PagerState) anonymousClass1.L$0;
                ResultKt.throwOnFailure($result);
                currentPosition2 = preJumpPosition2;
                targetPage = targetPage2;
                pageOffsetFraction2 = pageOffsetFraction3;
                animationSpec2 = animationSpec3;
                pagerState2 = pagerState3;
                int targetOffset7 = targetPage * pagerState2.getPageAvailableSpace();
                int currentOffset7 = currentPosition2 * pagerState2.getPageAvailableSpace();
                float pageOffsetToSnappedPosition7 = pagerState2.getDistanceToSnapPosition() + (pagerState2.getPageAvailableSpace() * pageOffsetFraction2);
                displacement = (targetOffset7 - currentOffset7) + pageOffsetToSnappedPosition7;
                anonymousClass1.L$0 = pagerState2;
                anonymousClass1.L$1 = null;
                anonymousClass1.label = 3;
                if (ScrollExtensionsKt.animateScrollBy(pagerState2, displacement, animationSpec2, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                pagerState2.setAnimationTargetPage(-1);
                return Unit.INSTANCE;
            case 3:
                pagerState2 = (PagerState) anonymousClass1.L$0;
                ResultKt.throwOnFailure($result);
                pagerState2.setAnimationTargetPage(-1);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitScrollDependencies(Continuation<? super Unit> continuation) throws Throwable {
        Object objWaitForFirstLayout = this.awaitLayoutModifier.waitForFirstLayout(continuation);
        return objWaitForFirstLayout == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWaitForFirstLayout : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0069 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    static /* synthetic */ Object scroll$suspendImpl(PagerState $this, MutatePriority scrollPriority, Function2<? super ScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        C02521 c02521;
        PagerState $this2;
        Function2<? super ScrollScope, ? super Continuation<? super Unit>, ? extends Object> function3;
        ScrollableState scrollableState;
        if (continuation instanceof C02521) {
            c02521 = (C02521) continuation;
            if ((c02521.label & Integer.MIN_VALUE) != 0) {
                c02521.label -= Integer.MIN_VALUE;
            } else {
                c02521 = $this.new C02521(continuation);
            }
        } else {
            c02521 = $this.new C02521(continuation);
        }
        C02521 c02522 = c02521;
        Object $result = c02522.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02522.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                c02522.L$0 = $this;
                c02522.L$1 = scrollPriority;
                c02522.L$2 = function2;
                c02522.label = 1;
                if ($this.awaitScrollDependencies(c02522) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $this2 = $this;
                function3 = function2;
                scrollableState = $this2.scrollableState;
                c02522.L$0 = null;
                c02522.L$1 = null;
                c02522.L$2 = null;
                c02522.label = 2;
                if (scrollableState.scroll(scrollPriority, function3, c02522) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                function3 = (Function2) c02522.L$2;
                scrollPriority = (MutatePriority) c02522.L$1;
                $this2 = (PagerState) c02522.L$0;
                ResultKt.throwOnFailure($result);
                scrollableState = $this2.scrollableState;
                c02522.L$0 = null;
                c02522.L$1 = null;
                c02522.L$2 = null;
                c02522.label = 2;
                if (scrollableState.scroll(scrollPriority, function3, c02522) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public float dispatchRawDelta(float delta) {
        return this.scrollableState.dispatchRawDelta(delta);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    private final void setCanScrollForward(boolean z) {
        MutableState $this$setValue$iv = this.canScrollForward;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollForward() {
        State $this$getValue$iv = this.canScrollForward;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    private final void setCanScrollBackward(boolean z) {
        MutableState $this$setValue$iv = this.canScrollBackward;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollBackward() {
        State $this$getValue$iv = this.canScrollBackward;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void applyMeasureResult$foundation_release(PagerMeasureResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.scrollPosition.updateFromMeasureResult(result);
        this.scrollToBeConsumed -= result.getConsumedScroll();
        this.pagerLayoutInfoState.setValue(result);
        setCanScrollForward(result.getCanScrollForward());
        MeasuredPage firstVisiblePage = result.getFirstVisiblePage();
        setCanScrollBackward(((firstVisiblePage != null ? firstVisiblePage.getIndex() : 0) == 0 && result.getFirstVisiblePageOffset() == 0) ? false : true);
        this.numMeasurePasses++;
        cancelPrefetchIfVisibleItemsChanged(result);
        if (!isScrollInProgress()) {
            setSettledPageState(getCurrentPage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int coerceInPageRange(int $this$coerceInPageRange) {
        if (getPageCount() > 0) {
            return RangesKt.coerceIn($this$coerceInPageRange, 0, getPageCount() - 1);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float performScroll(float distance) {
        if ((distance < 0.0f && !getCanScrollForward()) || (distance > 0.0f && !getCanScrollBackward())) {
            return 0.0f;
        }
        if (!(Math.abs(this.scrollToBeConsumed) <= 0.5f)) {
            throw new IllegalStateException(("entered drag with non-zero pending scroll: " + this.scrollToBeConsumed).toString());
        }
        float f = this.scrollToBeConsumed + distance;
        this.scrollToBeConsumed = f;
        if (Math.abs(f) > 0.5f) {
            float preScrollToBeConsumed = this.scrollToBeConsumed;
            Remeasurement remeasurement$foundation_release = getRemeasurement$foundation_release();
            if (remeasurement$foundation_release != null) {
                remeasurement$foundation_release.forceRemeasure();
            }
            if (this.prefetchingEnabled) {
                notifyPrefetch(preScrollToBeConsumed - this.scrollToBeConsumed);
            }
        }
        float preScrollToBeConsumed2 = this.scrollToBeConsumed;
        if (Math.abs(preScrollToBeConsumed2) <= 0.5f) {
            return distance;
        }
        float scrollConsumed = distance - this.scrollToBeConsumed;
        this.scrollToBeConsumed = 0.0f;
        return scrollConsumed;
    }

    private final void notifyPrefetch(float delta) {
        int indexToPrefetch;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle;
        if (!this.prefetchingEnabled) {
            return;
        }
        PagerLayoutInfo info = getLayoutInfo$foundation_release();
        if (!info.getVisiblePagesInfo().isEmpty()) {
            boolean scrollingForward = delta < 0.0f;
            if (scrollingForward) {
                indexToPrefetch = ((PageInfo) CollectionsKt.last((List) info.getVisiblePagesInfo())).getIndex() + 1;
            } else {
                indexToPrefetch = ((PageInfo) CollectionsKt.first((List) info.getVisiblePagesInfo())).getIndex() - 1;
            }
            if (indexToPrefetch != this.indexToPrefetch) {
                if (indexToPrefetch >= 0 && indexToPrefetch < info.getPagesCount()) {
                    if (this.wasScrollingForward != scrollingForward && (prefetchHandle = this.currentPrefetchHandle) != null) {
                        prefetchHandle.cancel();
                    }
                    this.wasScrollingForward = scrollingForward;
                    this.indexToPrefetch = indexToPrefetch;
                    this.currentPrefetchHandle = this.prefetchState.m654schedulePrefetch0kLqBqw(indexToPrefetch, this.premeasureConstraints);
                }
            }
        }
    }

    private final void cancelPrefetchIfVisibleItemsChanged(PagerLayoutInfo info) {
        int expectedPrefetchIndex;
        if (this.indexToPrefetch != -1 && (!info.getVisiblePagesInfo().isEmpty())) {
            if (this.wasScrollingForward) {
                expectedPrefetchIndex = ((PageInfo) CollectionsKt.last((List) info.getVisiblePagesInfo())).getIndex() + 1;
            } else {
                expectedPrefetchIndex = ((PageInfo) CollectionsKt.first((List) info.getVisiblePagesInfo())).getIndex() - 1;
            }
            if (this.indexToPrefetch != expectedPrefetchIndex) {
                this.indexToPrefetch = -1;
                LazyLayoutPrefetchState.PrefetchHandle prefetchHandle = this.currentPrefetchHandle;
                if (prefetchHandle != null) {
                    prefetchHandle.cancel();
                }
                this.currentPrefetchHandle = null;
            }
        }
    }

    public final float getOffsetFractionForPage(int page) {
        boolean z = false;
        if (page >= 0 && page <= getPageCount()) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(("page " + page + " is not within the range 0 to pageCount").toString());
        }
        return (getCurrentPage() - page) + getCurrentPageOffsetFraction();
    }
}
