package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LazyAnimateScroll.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0017\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0082\b\u001a%\u0010\f\u001a\u00020\b*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u0013\u0010\u0005\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0006\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"BoundDistance", "Landroidx/compose/ui/unit/Dp;", "F", "DEBUG", "", "MinimumDistance", "TargetDistance", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "animateScrollToItem", "Landroidx/compose/foundation/lazy/layout/LazyAnimateScrollScope;", "index", "", "scrollOffset", "(Landroidx/compose/foundation/lazy/layout/LazyAnimateScrollScope;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyAnimateScrollKt {
    private static final boolean DEBUG = false;
    private static final float TargetDistance = Dp.m5274constructorimpl(2500);
    private static final float BoundDistance = Dp.m5274constructorimpl(1500);
    private static final float MinimumDistance = Dp.m5274constructorimpl(50);

    private static final void debugLog(Function0<String> function0) {
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt$animateScrollToItem$2, reason: invalid class name */
    /* JADX INFO: compiled from: LazyAnimateScroll.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt$animateScrollToItem$2", f = "LazyAnimateScroll.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1}, l = {137, 237}, m = "invokeSuspend", n = {"$this$scroll", "loop", "anim", "loops", "targetDistancePx", "boundDistancePx", "minDistancePx", "forward", "$this$scroll"}, s = {"L$0", "L$1", "L$2", "L$3", "F$0", "F$1", "F$2", "I$0", "L$0"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $index;
        final /* synthetic */ int $scrollOffset;
        final /* synthetic */ LazyAnimateScrollScope $this_animateScrollToItem;
        float F$0;
        float F$1;
        float F$2;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(int i, LazyAnimateScrollScope lazyAnimateScrollScope, int i2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$index = i;
            this.$this_animateScrollToItem = lazyAnimateScrollScope;
            this.$scrollOffset = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$index, this.$this_animateScrollToItem, this.$scrollOffset, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:27:0x00df A[Catch: ItemFoundInScroll -> 0x01ea, TryCatch #0 {ItemFoundInScroll -> 0x01ea, blocks: (B:25:0x00db, B:27:0x00df, B:29:0x00e7, B:40:0x010f), top: B:86:0x00db }] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r13v1, types: [T, androidx.compose.animation.core.AnimationState] */
        /* JADX WARN: Type inference failed for: r4v11, types: [T, androidx.compose.animation.core.AnimationState] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x01b7 -> B:92:0x01bf). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AnonymousClass2 anonymousClass2;
            Object $result;
            ItemFoundInScroll itemFound;
            AnonymousClass2 anonymousClass3;
            final ScrollScope $this$scroll;
            float boundDistancePx;
            Ref.BooleanRef loop;
            Ref.IntRef loops;
            float targetDistancePx;
            int i;
            ScrollScope $this$scroll2;
            float minDistancePx;
            Ref.ObjectRef anim;
            Object $result2;
            final float target;
            final ScrollScope $this$scroll3;
            Object $result3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            float f = 0.0f;
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    anonymousClass2 = this;
                    $result = obj;
                    ScrollScope $this$scroll4 = (ScrollScope) anonymousClass2.L$0;
                    int i2 = anonymousClass2.$index;
                    if (!(((float) i2) >= 0.0f)) {
                        throw new IllegalArgumentException(("Index should be non-negative (" + i2 + ')').toString());
                    }
                    try {
                        Density $this$invokeSuspend_u24lambda_u241 = anonymousClass2.$this_animateScrollToItem.getDensity();
                        float targetDistancePx2 = $this$invokeSuspend_u24lambda_u241.mo327toPx0680j_4(LazyAnimateScrollKt.TargetDistance);
                        Density $this$invokeSuspend_u24lambda_u242 = anonymousClass2.$this_animateScrollToItem.getDensity();
                        float boundDistancePx2 = $this$invokeSuspend_u24lambda_u242.mo327toPx0680j_4(LazyAnimateScrollKt.BoundDistance);
                        Density $this$invokeSuspend_u24lambda_u243 = anonymousClass2.$this_animateScrollToItem.getDensity();
                        float minDistancePx2 = $this$invokeSuspend_u24lambda_u243.mo327toPx0680j_4(LazyAnimateScrollKt.MinimumDistance);
                        Ref.BooleanRef loop2 = new Ref.BooleanRef();
                        loop2.element = true;
                        Ref.ObjectRef anim2 = new Ref.ObjectRef();
                        anim2.element = AnimationStateKt.AnimationState$default(0.0f, 0.0f, 0L, 0L, false, 30, null);
                        Integer targetItemInitialOffset = anonymousClass2.$this_animateScrollToItem.getTargetItemOffset(anonymousClass2.$index);
                        if (targetItemInitialOffset != null) {
                            throw new ItemFoundInScroll(targetItemInitialOffset.intValue(), (AnimationState) anim2.element);
                        }
                        int i3 = anonymousClass2.$index > anonymousClass2.$this_animateScrollToItem.getFirstVisibleItemIndex() ? 1 : 0;
                        Ref.IntRef loops2 = new Ref.IntRef();
                        loops2.element = 1;
                        boundDistancePx = boundDistancePx2;
                        loop = loop2;
                        loops = loops2;
                        targetDistancePx = targetDistancePx2;
                        i = i3;
                        $this$scroll2 = $this$scroll4;
                        minDistancePx = minDistancePx2;
                        anim = anim2;
                        try {
                            if (loop.element || anonymousClass2.$this_animateScrollToItem.getItemCount() <= 0) {
                                return Unit.INSTANCE;
                            }
                            try {
                                try {
                                    float expectedDistance = anonymousClass2.$this_animateScrollToItem.expectedDistanceTo(anonymousClass2.$index, anonymousClass2.$scrollOffset);
                                    if (Math.abs(expectedDistance) >= targetDistancePx) {
                                        target = i != 0 ? targetDistancePx : -targetDistancePx;
                                    } else {
                                        float absTargetPx = Math.max(Math.abs(expectedDistance), minDistancePx);
                                        target = i != 0 ? absTargetPx : -absTargetPx;
                                    }
                                    int i4 = anonymousClass2.$index;
                                    int i5 = anonymousClass2.$scrollOffset;
                                    LazyAnimateScrollScope lazyAnimateScrollScope = anonymousClass2.$this_animateScrollToItem;
                                    anim.element = AnimationStateKt.copy$default((AnimationState) anim.element, 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
                                    final Ref.FloatRef floatRef = new Ref.FloatRef();
                                    $this$scroll3 = $this$scroll2;
                                    AnimationState animationState = (AnimationState) anim.element;
                                    Float fBoxFloat = Boxing.boxFloat(target);
                                    boolean z = !((((Number) ((AnimationState) anim.element).getVelocity()).floatValue() > f ? 1 : (((Number) ((AnimationState) anim.element).getVelocity()).floatValue() == f ? 0 : -1)) == 0);
                                    final LazyAnimateScrollScope lazyAnimateScrollScope2 = anonymousClass2.$this_animateScrollToItem;
                                    final int i6 = anonymousClass2.$index;
                                    final boolean z2 = i != 0;
                                    $result3 = $result;
                                    final int i7 = anonymousClass2.$scrollOffset;
                                    final Ref.BooleanRef loop3 = loop;
                                    final Ref.ObjectRef anim3 = anim;
                                    final Ref.IntRef loops3 = loops;
                                    float targetDistancePx3 = targetDistancePx;
                                    final float boundDistancePx3 = boundDistancePx;
                                    Function1<AnimationScope<Float, AnimationVector1D>, Unit> function1 = new Function1<AnimationScope<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt.animateScrollToItem.2.3
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
                                            invoke2(animationScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(AnimationScope<Float, AnimationVector1D> animateTo) {
                                            float coercedValue;
                                            Intrinsics.checkNotNullParameter(animateTo, "$this$animateTo");
                                            Integer targetItemOffset = lazyAnimateScrollScope2.getTargetItemOffset(i6);
                                            if (targetItemOffset == null) {
                                                if (target > 0.0f) {
                                                    coercedValue = RangesKt.coerceAtMost(animateTo.getValue().floatValue(), target);
                                                } else {
                                                    coercedValue = RangesKt.coerceAtLeast(animateTo.getValue().floatValue(), target);
                                                }
                                                float delta = coercedValue - floatRef.element;
                                                float consumed = $this$scroll3.scrollBy(delta);
                                                targetItemOffset = lazyAnimateScrollScope2.getTargetItemOffset(i6);
                                                if (targetItemOffset == null && !AnonymousClass2.invokeSuspend$isOvershot(z2, lazyAnimateScrollScope2, i6, i7)) {
                                                    if (!(delta == consumed)) {
                                                        animateTo.cancelAnimation();
                                                        loop3.element = false;
                                                        return;
                                                    }
                                                    floatRef.element += delta;
                                                    if (z2) {
                                                        if (animateTo.getValue().floatValue() > boundDistancePx3) {
                                                            animateTo.cancelAnimation();
                                                        }
                                                    } else if (animateTo.getValue().floatValue() < (-boundDistancePx3)) {
                                                        animateTo.cancelAnimation();
                                                    }
                                                    if (z2) {
                                                        if (loops3.element >= 2 && i6 - lazyAnimateScrollScope2.getLastVisibleItemIndex() > lazyAnimateScrollScope2.getNumOfItemsForTeleport()) {
                                                            LazyAnimateScrollScope lazyAnimateScrollScope3 = lazyAnimateScrollScope2;
                                                            lazyAnimateScrollScope3.snapToItem($this$scroll3, i6 - lazyAnimateScrollScope3.getNumOfItemsForTeleport(), 0);
                                                        }
                                                    } else if (loops3.element >= 2 && lazyAnimateScrollScope2.getFirstVisibleItemIndex() - i6 > lazyAnimateScrollScope2.getNumOfItemsForTeleport()) {
                                                        LazyAnimateScrollScope lazyAnimateScrollScope4 = lazyAnimateScrollScope2;
                                                        lazyAnimateScrollScope4.snapToItem($this$scroll3, i6 + lazyAnimateScrollScope4.getNumOfItemsForTeleport(), 0);
                                                    }
                                                }
                                            }
                                            if (AnonymousClass2.invokeSuspend$isOvershot(z2, lazyAnimateScrollScope2, i6, i7)) {
                                                lazyAnimateScrollScope2.snapToItem($this$scroll3, i6, i7);
                                                loop3.element = false;
                                                animateTo.cancelAnimation();
                                            } else if (targetItemOffset != null) {
                                                throw new ItemFoundInScroll(targetItemOffset.intValue(), anim3.element);
                                            }
                                        }
                                    };
                                    anonymousClass2.L$0 = $this$scroll3;
                                    anonymousClass2.L$1 = loop3;
                                    anonymousClass2.L$2 = anim3;
                                    anonymousClass2.L$3 = loops3;
                                    anonymousClass2.F$0 = targetDistancePx3;
                                    anonymousClass2.F$1 = boundDistancePx3;
                                    anonymousClass2.F$2 = minDistancePx;
                                    anonymousClass2.I$0 = i;
                                    anonymousClass2.label = 1;
                                    if (SuspendAnimationKt.animateTo(animationState, fBoxFloat, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : null, (4 & 4) != 0 ? false : z, (4 & 8) != 0 ? SuspendAnimationKt.C01582.INSTANCE : function1, anonymousClass2) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    try {
                                        $result = $result3;
                                        targetDistancePx = targetDistancePx3;
                                        $this$scroll2 = $this$scroll3;
                                        boundDistancePx = boundDistancePx3;
                                        loop = loop3;
                                        anim = anim3;
                                        loops = loops3;
                                        loops.element++;
                                        f = 0.0f;
                                        if (loop.element) {
                                        }
                                    } catch (ItemFoundInScroll e) {
                                        itemFound = e;
                                        anonymousClass3 = anonymousClass2;
                                        $this$scroll = $this$scroll2;
                                    }
                                    return Unit.INSTANCE;
                                } catch (ItemFoundInScroll e2) {
                                    itemFound = e2;
                                    $this$scroll = $this$scroll3;
                                    anonymousClass3 = anonymousClass2;
                                }
                            } catch (ItemFoundInScroll e3) {
                                $result = $result3;
                                itemFound = e3;
                                $this$scroll = $this$scroll3;
                                anonymousClass3 = anonymousClass2;
                            }
                        } catch (ItemFoundInScroll e4) {
                            itemFound = e4;
                            $this$scroll = $this$scroll2;
                            anonymousClass3 = anonymousClass2;
                        }
                        AnimationState anim4 = AnimationStateKt.copy$default((AnimationState) itemFound.getPreviousAnimation(), 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
                        final float target2 = itemFound.getItemOffset() + anonymousClass3.$scrollOffset;
                        final Ref.FloatRef prevValue = new Ref.FloatRef();
                        Float fBoxFloat2 = Boxing.boxFloat(target2);
                        boolean z3 = ((Number) anim4.getVelocity()).floatValue() == 0.0f;
                        Function1<AnimationScope<Float, AnimationVector1D>, Unit> function2 = new Function1<AnimationScope<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt.animateScrollToItem.2.5
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
                                invoke2(animationScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Code duplicated, block: B:19:0x0064  */
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(AnimationScope<Float, AnimationVector1D> animateTo) {
                                Intrinsics.checkNotNullParameter(animateTo, "$this$animateTo");
                                float f2 = target2;
                                float fCoerceAtLeast = 0.0f;
                                if (f2 > 0.0f) {
                                    fCoerceAtLeast = RangesKt.coerceAtMost(animateTo.getValue().floatValue(), target2);
                                } else if (f2 < 0.0f) {
                                    fCoerceAtLeast = RangesKt.coerceAtLeast(animateTo.getValue().floatValue(), target2);
                                }
                                float coercedValue = fCoerceAtLeast;
                                float delta = coercedValue - prevValue.element;
                                float consumed = $this$scroll.scrollBy(delta);
                                if (delta == consumed) {
                                    if (!(coercedValue == animateTo.getValue().floatValue())) {
                                        animateTo.cancelAnimation();
                                    }
                                } else {
                                    animateTo.cancelAnimation();
                                }
                                prevValue.element += delta;
                            }
                        };
                        AnonymousClass2 anonymousClass4 = anonymousClass3;
                        anonymousClass3.L$0 = $this$scroll;
                        anonymousClass3.L$1 = null;
                        anonymousClass3.L$2 = null;
                        anonymousClass3.L$3 = null;
                        anonymousClass3.label = 2;
                        if (SuspendAnimationKt.animateTo(anim4, fBoxFloat2, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : null, (4 & 4) != 0 ? false : !z3, (4 & 8) != 0 ? SuspendAnimationKt.C01582.INSTANCE : function2, anonymousClass4) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result;
                        anonymousClass3.$this_animateScrollToItem.snapToItem($this$scroll, anonymousClass3.$index, anonymousClass3.$scrollOffset);
                        return Unit.INSTANCE;
                    } catch (ItemFoundInScroll e5) {
                        itemFound = e5;
                        anonymousClass3 = anonymousClass2;
                        $this$scroll = $this$scroll4;
                    }
                    break;
                case 1:
                    anonymousClass2 = this;
                    $result = obj;
                    i = anonymousClass2.I$0;
                    minDistancePx = anonymousClass2.F$2;
                    float boundDistancePx4 = anonymousClass2.F$1;
                    float targetDistancePx4 = anonymousClass2.F$0;
                    loops = (Ref.IntRef) anonymousClass2.L$3;
                    Ref.ObjectRef anim5 = (Ref.ObjectRef) anonymousClass2.L$2;
                    Ref.BooleanRef loop4 = (Ref.BooleanRef) anonymousClass2.L$1;
                    ScrollScope $this$scroll5 = (ScrollScope) anonymousClass2.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        boundDistancePx = boundDistancePx4;
                        loop = loop4;
                        $this$scroll2 = $this$scroll5;
                        targetDistancePx = targetDistancePx4;
                        anim = anim5;
                        loops.element++;
                        f = 0.0f;
                        if (loop.element) {
                        }
                        break;
                    } catch (ItemFoundInScroll e6) {
                        itemFound = e6;
                        anonymousClass3 = anonymousClass2;
                        $this$scroll = $this$scroll5;
                        break;
                    }
                    return Unit.INSTANCE;
                case 2:
                    anonymousClass3 = this;
                    $result2 = obj;
                    $this$scroll = (ScrollScope) anonymousClass3.L$0;
                    ResultKt.throwOnFailure($result2);
                    anonymousClass3.$this_animateScrollToItem.snapToItem($this$scroll, anonymousClass3.$index, anonymousClass3.$scrollOffset);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$isOvershot(boolean forward, LazyAnimateScrollScope $this_animateScrollToItem, int $index, int $scrollOffset) {
            if (forward) {
                if ($this_animateScrollToItem.getFirstVisibleItemIndex() > $index) {
                    return true;
                }
                if ($this_animateScrollToItem.getFirstVisibleItemIndex() != $index || $this_animateScrollToItem.getFirstVisibleItemScrollOffset() <= $scrollOffset) {
                    return false;
                }
                return true;
            }
            if ($this_animateScrollToItem.getFirstVisibleItemIndex() < $index) {
                return true;
            }
            if ($this_animateScrollToItem.getFirstVisibleItemIndex() != $index || $this_animateScrollToItem.getFirstVisibleItemScrollOffset() >= $scrollOffset) {
                return false;
            }
            return true;
        }
    }

    public static final Object animateScrollToItem(LazyAnimateScrollScope $this$animateScrollToItem, int index, int scrollOffset, Continuation<? super Unit> continuation) {
        Object objScroll = $this$animateScrollToItem.scroll(new AnonymousClass2(index, $this$animateScrollToItem, scrollOffset, null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }
}
