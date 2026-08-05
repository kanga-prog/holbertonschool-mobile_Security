package androidx.compose.material3;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Tooltip.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1", f = "Tooltip.kt", i = {}, l = {212}, m = "invokeSuspend", n = {}, s = {})
final class TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Job> $onLongPress;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1(Function0<? extends Job> function0, Continuation<? super TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1> continuation) {
        super(2, continuation);
        this.$onLongPress = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1 tooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1 = new TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1(this.$onLongPress, continuation);
        tooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1.L$0 = obj;
        return tooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: Tooltip.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1$1", f = "Tooltip.kt", i = {0, 0, 0, 1, 1}, l = {217, 221, 229}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "pass", "longPressTimeout", "$this$awaitEachGesture", "pass"}, s = {"L$0", "L$1", "J$0", "L$0", "L$1"})
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Job> $onLongPress;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function0<? extends Job> function0, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$onLongPress = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$onLongPress, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.material3.TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Tooltip.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.material3.TooltipKt$TooltipBox$scope$1$1$tooltipAnchor$1$1$1", f = "Tooltip.kt", i = {}, l = {222}, m = "invokeSuspend", n = {}, s = {})
        static final class C01051 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super PointerInputChange>, Object> {
            final /* synthetic */ PointerEventPass $pass;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01051(PointerEventPass pointerEventPass, Continuation<? super C01051> continuation) {
                super(2, continuation);
                this.$pass = pointerEventPass;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C01051 c01051 = new C01051(this.$pass, continuation);
                c01051.L$0 = obj;
                return c01051;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super PointerInputChange> continuation) {
                return ((C01051) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        AwaitPointerEventScope $this$withTimeout = (AwaitPointerEventScope) this.L$0;
                        this.label = 1;
                        Object objWaitForUpOrCancellation = TapGestureDetectorKt.waitForUpOrCancellation($this$withTimeout, this.$pass, this);
                        return objWaitForUpOrCancellation == coroutine_suspended ? coroutine_suspended : objWaitForUpOrCancellation;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        return $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:20:0x007d A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:21:0x007e  */
        /* JADX WARN: Code duplicated, block: B:27:0x0099 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:28:0x009a  */
        /* JADX WARN: Code duplicated, block: B:32:0x00b0 A[LOOP:0: B:30:0x00aa->B:32:0x00b0, LOOP_END] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AnonymousClass1 anonymousClass1;
            AwaitPointerEventScope $this$awaitEachGesture;
            long longPressTimeout;
            PointerEventPass pass;
            PointerEventPass pass2;
            AwaitPointerEventScope $this$awaitEachGesture2;
            Object objAwaitPointerEvent;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    anonymousClass1 = this;
                    AwaitPointerEventScope $this$awaitEachGesture3 = (AwaitPointerEventScope) anonymousClass1.L$0;
                    long longPressTimeout2 = $this$awaitEachGesture3.getViewConfiguration().getLongPressTimeoutMillis();
                    PointerEventPass pass3 = PointerEventPass.Initial;
                    anonymousClass1.L$0 = $this$awaitEachGesture3;
                    anonymousClass1.L$1 = pass3;
                    anonymousClass1.J$0 = longPressTimeout2;
                    anonymousClass1.label = 1;
                    if (TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture3, false, pass3, anonymousClass1, 1, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$awaitEachGesture = $this$awaitEachGesture3;
                    longPressTimeout = longPressTimeout2;
                    pass = pass3;
                    try {
                        anonymousClass1.L$0 = $this$awaitEachGesture;
                        anonymousClass1.L$1 = pass;
                        anonymousClass1.label = 2;
                        if ($this$awaitEachGesture.withTimeout(longPressTimeout, new C01051(pass, null), anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    } catch (PointerEventTimeoutCancellationException e) {
                        pass2 = pass;
                        $this$awaitEachGesture2 = $this$awaitEachGesture;
                        anonymousClass1.$onLongPress.invoke();
                        anonymousClass1.L$0 = null;
                        anonymousClass1.L$1 = null;
                        anonymousClass1.label = 3;
                        objAwaitPointerEvent = $this$awaitEachGesture2.awaitPointerEvent(pass2, anonymousClass1);
                        if (objAwaitPointerEvent == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objAwaitPointerEvent;
                        PointerEvent event = (PointerEvent) $result;
                        Iterable $this$forEach$iv = event.getChanges();
                        for (Object element$iv : $this$forEach$iv) {
                            PointerInputChange it = (PointerInputChange) element$iv;
                            it.consume();
                        }
                    }
                    break;
                case 1:
                    anonymousClass1 = this;
                    longPressTimeout = anonymousClass1.J$0;
                    pass = (PointerEventPass) anonymousClass1.L$1;
                    $this$awaitEachGesture = (AwaitPointerEventScope) anonymousClass1.L$0;
                    ResultKt.throwOnFailure($result);
                    anonymousClass1.L$0 = $this$awaitEachGesture;
                    anonymousClass1.L$1 = pass;
                    anonymousClass1.label = 2;
                    if ($this$awaitEachGesture.withTimeout(longPressTimeout, new C01051(pass, null), anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 2:
                    anonymousClass1 = this;
                    pass2 = (PointerEventPass) anonymousClass1.L$1;
                    $this$awaitEachGesture2 = (AwaitPointerEventScope) anonymousClass1.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        break;
                    } catch (PointerEventTimeoutCancellationException e2) {
                        anonymousClass1.$onLongPress.invoke();
                        anonymousClass1.L$0 = null;
                        anonymousClass1.L$1 = null;
                        anonymousClass1.label = 3;
                        objAwaitPointerEvent = $this$awaitEachGesture2.awaitPointerEvent(pass2, anonymousClass1);
                        if (objAwaitPointerEvent == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objAwaitPointerEvent;
                        PointerEvent event2 = (PointerEvent) $result;
                        Iterable $this$forEach$iv2 = event2.getChanges();
                        while (r3.hasNext()) {
                            PointerInputChange it2 = (PointerInputChange) element$iv;
                            it2.consume();
                        }
                    }
                    return Unit.INSTANCE;
                case 3:
                    ResultKt.throwOnFailure($result);
                    PointerEvent event3 = (PointerEvent) $result;
                    Iterable $this$forEach$iv3 = event3.getChanges();
                    while (r3.hasNext()) {
                        PointerInputChange it3 = (PointerInputChange) element$iv;
                        it3.consume();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                this.label = 1;
                if (ForEachGestureKt.awaitEachGesture($this$pointerInput, new AnonymousClass1(this.$onLongPress, null), this) == coroutine_suspended) {
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
