package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyLayoutAnimateItemModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyLayoutAnimateItemModifierNode$animatePlacementDelta$1", f = "LazyLayoutAnimateItemModifierNode.kt", i = {0}, l = {97, 103}, m = "invokeSuspend", n = {"spec"}, s = {"L$0"})
final class LazyLayoutAnimateItemModifierNode$animatePlacementDelta$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $totalDelta;
    Object L$0;
    int label;
    final /* synthetic */ LazyLayoutAnimateItemModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyLayoutAnimateItemModifierNode$animatePlacementDelta$1(LazyLayoutAnimateItemModifierNode lazyLayoutAnimateItemModifierNode, long j, Continuation<? super LazyLayoutAnimateItemModifierNode$animatePlacementDelta$1> continuation) {
        super(2, continuation);
        this.this$0 = lazyLayoutAnimateItemModifierNode;
        this.$totalDelta = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LazyLayoutAnimateItemModifierNode$animatePlacementDelta$1(this.this$0, this.$totalDelta, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LazyLayoutAnimateItemModifierNode$animatePlacementDelta$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00ce A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:31:0x00cf  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [androidx.compose.foundation.lazy.layout.LazyLayoutAnimateItemModifierNode$animatePlacementDelta$1] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.foundation.lazy.layout.LazyLayoutAnimateItemModifierNode$animatePlacementDelta$1] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SpringSpec placementAnimationSpec;
        final long jIntOffset;
        Animatable animatable;
        Function1<Animatable<IntOffset, AnimationVector2D>, Unit> function1;
        Continuation continuation;
        ?? r0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
            switch (r1) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    LazyLayoutAnimateItemModifierNode$animatePlacementDelta$1 lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1 = this;
                    placementAnimationSpec = (!lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1.this$0.placementDeltaAnimation.isRunning() || (lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1.this$0.getPlacementAnimationSpec() instanceof SpringSpec)) ? lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1.this$0.getPlacementAnimationSpec() : LazyLayoutAnimateItemModifierNodeKt.InterruptionSpec;
                    boolean zIsRunning = lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1.this$0.placementDeltaAnimation.isRunning();
                    r1 = lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1;
                    if (!zIsRunning) {
                        lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1.L$0 = placementAnimationSpec;
                        lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1.label = 1;
                        if (lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1.this$0.placementDeltaAnimation.snapTo(IntOffset.m5383boximpl(lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1.$totalDelta), lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1) == coroutine_suspended) {
                            r1 = lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1;
                            return coroutine_suspended;
                        }
                    }
                    r1 = lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1;
                    long packedValue = ((IntOffset) r1.this$0.placementDeltaAnimation.getValue()).getPackedValue();
                    long j = r1.$totalDelta;
                    jIntOffset = IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(packedValue) - IntOffset.m5392getXimpl(j), IntOffset.m5393getYimpl(packedValue) - IntOffset.m5393getYimpl(j));
                    animatable = r1.this$0.placementDeltaAnimation;
                    final LazyLayoutAnimateItemModifierNode lazyLayoutAnimateItemModifierNode = r1.this$0;
                    function1 = new Function1<Animatable<IntOffset, AnimationVector2D>, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutAnimateItemModifierNode$animatePlacementDelta$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Animatable<IntOffset, AnimationVector2D> animatable2) {
                            invoke2(animatable2);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Animatable<IntOffset, AnimationVector2D> animateTo) {
                            Intrinsics.checkNotNullParameter(animateTo, "$this$animateTo");
                            LazyLayoutAnimateItemModifierNode lazyLayoutAnimateItemModifierNode2 = lazyLayoutAnimateItemModifierNode;
                            long arg0$iv = animateTo.getValue().getPackedValue();
                            long other$iv = jIntOffset;
                            lazyLayoutAnimateItemModifierNode2.m630setPlacementDeltagyyYBs(IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(arg0$iv) - IntOffset.m5392getXimpl(other$iv), IntOffset.m5393getYimpl(arg0$iv) - IntOffset.m5393getYimpl(other$iv)));
                        }
                    };
                    continuation = (Continuation) r1;
                    r1.L$0 = null;
                    r1.label = 2;
                    if (animatable.animateTo(IntOffset.m5383boximpl(jIntOffset), (4 & 2) != 0 ? animatable.defaultSpringSpec : placementAnimationSpec, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : function1, continuation) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    r0 = r1;
                    r0.this$0.setAnimationInProgress(false);
                    return Unit.INSTANCE;
                case 1:
                    LazyLayoutAnimateItemModifierNode$animatePlacementDelta$1 lazyLayoutAnimateItemModifierNode$animatePlacementDelta$2 = this;
                    placementAnimationSpec = (FiniteAnimationSpec) lazyLayoutAnimateItemModifierNode$animatePlacementDelta$2.L$0;
                    ResultKt.throwOnFailure(obj);
                    r1 = lazyLayoutAnimateItemModifierNode$animatePlacementDelta$2;
                    r1 = lazyLayoutAnimateItemModifierNode$animatePlacementDelta$1;
                    long packedValue2 = ((IntOffset) r1.this$0.placementDeltaAnimation.getValue()).getPackedValue();
                    long j2 = r1.$totalDelta;
                    jIntOffset = IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(packedValue2) - IntOffset.m5392getXimpl(j2), IntOffset.m5393getYimpl(packedValue2) - IntOffset.m5393getYimpl(j2));
                    animatable = r1.this$0.placementDeltaAnimation;
                    final LazyLayoutAnimateItemModifierNode lazyLayoutAnimateItemModifierNode2 = r1.this$0;
                    function1 = new Function1<Animatable<IntOffset, AnimationVector2D>, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutAnimateItemModifierNode$animatePlacementDelta$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Animatable<IntOffset, AnimationVector2D> animatable2) {
                            invoke2(animatable2);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Animatable<IntOffset, AnimationVector2D> animateTo) {
                            Intrinsics.checkNotNullParameter(animateTo, "$this$animateTo");
                            LazyLayoutAnimateItemModifierNode lazyLayoutAnimateItemModifierNode3 = lazyLayoutAnimateItemModifierNode2;
                            long arg0$iv = animateTo.getValue().getPackedValue();
                            long other$iv = jIntOffset;
                            lazyLayoutAnimateItemModifierNode3.m630setPlacementDeltagyyYBs(IntOffsetKt.IntOffset(IntOffset.m5392getXimpl(arg0$iv) - IntOffset.m5392getXimpl(other$iv), IntOffset.m5393getYimpl(arg0$iv) - IntOffset.m5393getYimpl(other$iv)));
                        }
                    };
                    continuation = (Continuation) r1;
                    r1.L$0 = null;
                    r1.label = 2;
                    if (animatable.animateTo(IntOffset.m5383boximpl(jIntOffset), (4 & 2) != 0 ? animatable.defaultSpringSpec : placementAnimationSpec, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : function1, continuation) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    r0 = r1;
                    r0.this$0.setAnimationInProgress(false);
                    return Unit.INSTANCE;
                case 2:
                    r0 = this;
                    try {
                        ResultKt.throwOnFailure(obj);
                        r0.this$0.setAnimationInProgress(false);
                        break;
                    } catch (CancellationException e) {
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (CancellationException e2) {
        }
    }
}
