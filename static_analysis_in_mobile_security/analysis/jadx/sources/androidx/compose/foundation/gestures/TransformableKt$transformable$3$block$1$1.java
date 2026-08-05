package androidx.compose.foundation.gestures;

import androidx.compose.runtime.State;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import java.util.concurrent.CancellationException;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: Transformable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt$transformable$3$block$1$1", f = "Transformable.kt", i = {}, l = {124}, m = "invokeSuspend", n = {}, s = {})
final class TransformableKt$transformable$3$block$1$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Channel<TransformEvent> $channel;
    final /* synthetic */ State<Boolean> $updatePanZoomLock;
    final /* synthetic */ State<Function0<Boolean>> $updatedCanPan;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    TransformableKt$transformable$3$block$1$1(State<Boolean> state, Channel<TransformEvent> channel, State<? extends Function0<Boolean>> state2, Continuation<? super TransformableKt$transformable$3$block$1$1> continuation) {
        super(2, continuation);
        this.$updatePanZoomLock = state;
        this.$channel = channel;
        this.$updatedCanPan = state2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TransformableKt$transformable$3$block$1$1 transformableKt$transformable$3$block$1$1 = new TransformableKt$transformable$3$block$1$1(this.$updatePanZoomLock, this.$channel, this.$updatedCanPan, continuation);
        transformableKt$transformable$3$block$1$1.L$0 = obj;
        return transformableKt$transformable$3$block$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((TransformableKt$transformable$3$block$1$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableKt$transformable$3$block$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: Transformable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt$transformable$3$block$1$1$1", f = "Transformable.kt", i = {}, l = {125}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PointerInputScope $$this$null;
        final /* synthetic */ Channel<TransformEvent> $channel;
        final /* synthetic */ State<Boolean> $updatePanZoomLock;
        final /* synthetic */ State<Function0<Boolean>> $updatedCanPan;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(PointerInputScope pointerInputScope, State<Boolean> state, Channel<TransformEvent> channel, State<? extends Function0<Boolean>> state2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$$this$null = pointerInputScope;
            this.$updatePanZoomLock = state;
            this.$channel = channel;
            this.$updatedCanPan = state2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$null, this.$updatePanZoomLock, this.$channel, this.$updatedCanPan, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableKt$transformable$3$block$1$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Transformable.kt */
        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt$transformable$3$block$1$1$1$1", f = "Transformable.kt", i = {}, l = {WorkQueueKt.MASK}, m = "invokeSuspend", n = {}, s = {})
        static final class C00161 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            final /* synthetic */ Channel<TransformEvent> $channel;
            final /* synthetic */ State<Boolean> $updatePanZoomLock;
            final /* synthetic */ State<Function0<Boolean>> $updatedCanPan;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00161(State<Boolean> state, Channel<TransformEvent> channel, State<? extends Function0<Boolean>> state2, CoroutineScope coroutineScope, Continuation<? super C00161> continuation) {
                super(2, continuation);
                this.$updatePanZoomLock = state;
                this.$channel = channel;
                this.$updatedCanPan = state2;
                this.$$this$coroutineScope = coroutineScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00161 c00161 = new C00161(this.$updatePanZoomLock, this.$channel, this.$updatedCanPan, this.$$this$coroutineScope, continuation);
                c00161.L$0 = obj;
                return c00161;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00161) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:28:0x0054 A[Catch: all -> 0x0016, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0016, blocks: (B:7:0x0012, B:23:0x0048, B:28:0x0054), top: B:31:0x0006 }] */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v3 */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                CancellationException exception;
                C00161 c00161;
                C00161 coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                try {
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            AwaitPointerEventScope $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                            try {
                                this.label = 1;
                                if (TransformableKt.detectZoom($this$awaitEachGesture, this.$updatePanZoomLock, this.$channel, this.$updatedCanPan, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                c00161 = this;
                                c00161.$channel.mo7066trySendJP2dKIU(TransformEvent.TransformStopped.INSTANCE);
                                return Unit.INSTANCE;
                            } catch (CancellationException e) {
                                exception = e;
                                c00161 = this;
                                if (!CoroutineScopeKt.isActive(c00161.$$this$coroutineScope)) {
                                    throw exception;
                                }
                            } catch (Throwable th) {
                                exception = th;
                                coroutine_suspended = this;
                                coroutine_suspended.$channel.mo7066trySendJP2dKIU(TransformEvent.TransformStopped.INSTANCE);
                                throw exception;
                            }
                        case 1:
                            c00161 = this;
                            try {
                                ResultKt.throwOnFailure($result);
                                break;
                            } catch (CancellationException e2) {
                                exception = e2;
                                if (!CoroutineScopeKt.isActive(c00161.$$this$coroutineScope)) {
                                    throw exception;
                                }
                            }
                            c00161.$channel.mo7066trySendJP2dKIU(TransformEvent.TransformStopped.INSTANCE);
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                } catch (Throwable th2) {
                    exception = th2;
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    this.label = 1;
                    if (ForEachGestureKt.awaitEachGesture(this.$$this$null, new C00161(this.$updatePanZoomLock, this.$channel, this.$updatedCanPan, $this$coroutineScope, null), this) == coroutine_suspended) {
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

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputScope $this$null = (PointerInputScope) this.L$0;
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new AnonymousClass1($this$null, this.$updatePanZoomLock, this.$channel, this.$updatedCanPan, null), this) == coroutine_suspended) {
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
