package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.core.app.NotificationCompat;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: Draggable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@DebugMetadata(c = "androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1", f = "Draggable.kt", i = {}, l = {302}, m = "invokeSuspend", n = {}, s = {})
final class DraggableNode$pointerInputNode$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DraggableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DraggableNode$pointerInputNode$1(DraggableNode draggableNode, Continuation<? super DraggableNode$pointerInputNode$1> continuation) {
        super(2, continuation);
        this.this$0 = draggableNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DraggableNode$pointerInputNode$1 draggableNode$pointerInputNode$1 = new DraggableNode$pointerInputNode$1(this.this$0, continuation);
        draggableNode$pointerInputNode$1.L$0 = obj;
        return draggableNode$pointerInputNode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((DraggableNode$pointerInputNode$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputScope $this$SuspendingPointerInputModifierNode = (PointerInputScope) this.L$0;
                if (!this.this$0.enabled) {
                    return Unit.INSTANCE;
                }
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new AnonymousClass1($this$SuspendingPointerInputModifierNode, this.this$0, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: Draggable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1", f = "Draggable.kt", i = {0}, l = {326}, m = "invokeSuspend", n = {"$this$coroutineScope"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PointerInputScope $$this$SuspendingPointerInputModifierNode;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ DraggableNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PointerInputScope pointerInputScope, DraggableNode draggableNode, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$$this$SuspendingPointerInputModifierNode = pointerInputScope;
            this.this$0 = draggableNode;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$SuspendingPointerInputModifierNode, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Draggable.kt */
        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        @DebugMetadata(c = "androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1", f = "Draggable.kt", i = {0, 0, 1, 1, 2, 2, 3, 4, 5}, l = {305, 307, 309, 316, 318, 321}, m = "invokeSuspend", n = {"$this$launch", NotificationCompat.CATEGORY_EVENT, "$this$launch", NotificationCompat.CATEGORY_EVENT, "$this$launch", NotificationCompat.CATEGORY_EVENT, "$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$0", "L$0"})
        static final class C00071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ DraggableNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00071(DraggableNode draggableNode, Continuation<? super C00071> continuation) {
                super(2, continuation);
                this.this$0 = draggableNode;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00071 c00071 = new C00071(this.this$0, continuation);
                c00071.L$0 = obj;
                return c00071;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00071) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:25:0x0077  */
            /* JADX WARN: Code duplicated, block: B:27:0x0094 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:28:0x0095  */
            /* JADX WARN: Code duplicated, block: B:31:0x00a4  */
            /* JADX WARN: Code duplicated, block: B:33:0x00bc A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:34:0x00bd  */
            /* JADX WARN: Code duplicated, block: B:38:0x00e4 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:41:0x00eb A[Catch: CancellationException -> 0x0041, TryCatch #1 {CancellationException -> 0x0041, blocks: (B:36:0x00c3, B:39:0x00e5, B:41:0x00eb, B:46:0x010b, B:48:0x0111, B:16:0x003c), top: B:62:0x003c }] */
            /* JADX WARN: Code duplicated, block: B:43:0x0107 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:44:0x0108  */
            /* JADX WARN: Code duplicated, block: B:46:0x010b A[Catch: CancellationException -> 0x0041, TryCatch #1 {CancellationException -> 0x0041, blocks: (B:36:0x00c3, B:39:0x00e5, B:41:0x00eb, B:46:0x010b, B:48:0x0111, B:16:0x003c), top: B:62:0x003c }] */
            /* JADX WARN: Code duplicated, block: B:48:0x0111 A[Catch: CancellationException -> 0x0041, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x0041, blocks: (B:36:0x00c3, B:39:0x00e5, B:41:0x00eb, B:46:0x010b, B:48:0x0111, B:16:0x003c), top: B:62:0x003c }] */
            /* JADX WARN: Code duplicated, block: B:50:0x0123 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:51:0x0124  */
            /* JADX WARN: Code duplicated, block: B:53:0x0127  */
            /* JADX WARN: Code duplicated, block: B:58:0x013f  */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r12v13 */
            /* JADX WARN: Type inference failed for: r12v15 */
            /* JADX WARN: Type inference failed for: r12v5, types: [T] */
            /* JADX WARN: Type inference failed for: r1v0, types: [int] */
            /* JADX WARN: Type inference failed for: r1v1, types: [androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1] */
            /* JADX WARN: Type inference failed for: r1v10, types: [androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1] */
            /* JADX WARN: Type inference failed for: r1v14, types: [androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1] */
            /* JADX WARN: Type inference failed for: r1v15, types: [androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1] */
            /* JADX WARN: Type inference failed for: r1v16, types: [androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1] */
            /* JADX WARN: Type inference failed for: r1v17, types: [androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1] */
            /* JADX WARN: Type inference failed for: r1v18 */
            /* JADX WARN: Type inference failed for: r1v19 */
            /* JADX WARN: Type inference failed for: r1v2 */
            /* JADX WARN: Type inference failed for: r1v20 */
            /* JADX WARN: Type inference failed for: r1v3, types: [androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1] */
            /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1] */
            /* JADX WARN: Type inference failed for: r1v6 */
            /* JADX WARN: Type inference failed for: r1v7 */
            /* JADX WARN: Type inference failed for: r1v8, types: [androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1] */
            /* JADX WARN: Type inference failed for: r3v14, types: [androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1] */
            /* JADX WARN: Type inference failed for: r3v18 */
            /* JADX WARN: Type inference failed for: r3v23 */
            /* JADX WARN: Type inference failed for: r3v9 */
            /* JADX WARN: Type inference failed for: r5v0 */
            /* JADX WARN: Type inference failed for: r5v6 */
            /* JADX WARN: Type inference failed for: r6v6 */
            /* JADX WARN: Type inference failed for: r7v3 */
            /* JADX WARN: Type inference failed for: r8v1 */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x0124 -> B:23:0x0071). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x0127 -> B:23:0x0071). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x013a -> B:23:0x0071). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x013f -> B:23:0x0071). Please report as a decompilation issue!!! */
            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final java.lang.Object invokeSuspend(java.lang.Object r12) {
                /*
                    Method dump skipped, instruction units count: 346
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1.AnonymousClass1.C00071.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: Draggable.kt */
            @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/foundation/gestures/DragScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            @DebugMetadata(c = "androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$1$1", f = "Draggable.kt", i = {0}, l = {312}, m = "invokeSuspend", n = {"$this$drag"}, s = {"L$0"})
            static final class C00081 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Ref.ObjectRef<DragEvent> $event;
                private /* synthetic */ Object L$0;
                Object L$1;
                int label;
                final /* synthetic */ DraggableNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00081(Ref.ObjectRef<DragEvent> objectRef, DraggableNode draggableNode, Continuation<? super C00081> continuation) {
                    super(2, continuation);
                    this.$event = objectRef;
                    this.this$0 = draggableNode;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00081 c00081 = new C00081(this.$event, this.this$0, continuation);
                    c00081.L$0 = obj;
                    return c00081;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
                    return ((C00081) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:10:0x0034  */
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0075 -> B:22:0x007c). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    C00081 c00081;
                    DragScope dragScope;
                    Object obj2;
                    Object obj3;
                    T t;
                    DragScope dragScope2;
                    Ref.ObjectRef<DragEvent> objectRef;
                    C00081 c00082;
                    Object obj4;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            c00081 = this;
                            dragScope = (DragScope) c00081.L$0;
                            obj2 = obj;
                            if ((c00081.$event.element instanceof DragEvent.DragStopped) && !(c00081.$event.element instanceof DragEvent.DragCancelled)) {
                                DragEvent dragEvent = c00081.$event.element;
                                DragEvent.DragDelta dragDelta = dragEvent instanceof DragEvent.DragDelta ? (DragEvent.DragDelta) dragEvent : null;
                                if (dragDelta != null) {
                                    dragScope.dragBy(DraggableKt.m291toFloat3MmeM6k(dragDelta.getDelta(), c00081.this$0.orientation));
                                }
                                Ref.ObjectRef<DragEvent> objectRef2 = c00081.$event;
                                c00081.L$0 = dragScope;
                                c00081.L$1 = objectRef2;
                                c00081.label = 1;
                                Object objReceive = c00081.this$0.channel.receive(c00081);
                                if (objReceive == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                Object obj5 = coroutine_suspended;
                                obj3 = obj2;
                                t = objReceive;
                                dragScope2 = dragScope;
                                objectRef = objectRef2;
                                c00082 = c00081;
                                obj4 = obj5;
                                objectRef.element = t;
                                obj2 = obj3;
                                coroutine_suspended = obj4;
                                c00081 = c00082;
                                dragScope = dragScope2;
                                if (c00081.$event.element instanceof DragEvent.DragStopped) {
                                }
                                return Unit.INSTANCE;
                            }
                            return Unit.INSTANCE;
                        case 1:
                            Ref.ObjectRef<DragEvent> objectRef3 = (Ref.ObjectRef) this.L$1;
                            DragScope dragScope3 = (DragScope) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            dragScope2 = dragScope3;
                            objectRef = objectRef3;
                            c00082 = this;
                            obj4 = coroutine_suspended;
                            obj3 = obj;
                            t = obj;
                            objectRef.element = t;
                            obj2 = obj3;
                            coroutine_suspended = obj4;
                            c00081 = c00082;
                            dragScope = dragScope2;
                            if (c00081.$event.element instanceof DragEvent.DragStopped) {
                                break;
                            }
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:23:0x0064  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            CancellationException exception;
            CoroutineScope $this$coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope2 = (CoroutineScope) this.L$0;
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope2, null, CoroutineStart.UNDISPATCHED, new C00071(this.this$0, null), 1, null);
                    try {
                        this.L$0 = $this$coroutineScope2;
                        this.label = 1;
                        if (this.$$this$SuspendingPointerInputModifierNode.awaitPointerEventScope(new AnonymousClass2($this$coroutineScope2, this.this$0, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    } catch (CancellationException e) {
                        exception = e;
                        $this$coroutineScope = $this$coroutineScope2;
                        if (!CoroutineScopeKt.isActive($this$coroutineScope)) {
                            throw exception;
                        }
                    }
                case 1:
                    $this$coroutineScope = (CoroutineScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        break;
                    } catch (CancellationException e2) {
                        exception = e2;
                        if (!CoroutineScopeKt.isActive($this$coroutineScope)) {
                            throw exception;
                        }
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: Draggable.kt */
        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        @DebugMetadata(c = "androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1$1$2", f = "Draggable.kt", i = {0, 1, 1}, l = {328, 336}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope", "$this$awaitPointerEventScope", "isDragSuccessful"}, s = {"L$0", "L$0", "I$0"})
        static final class AnonymousClass2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ DraggableNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(CoroutineScope coroutineScope, DraggableNode draggableNode, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$$this$coroutineScope = coroutineScope;
                this.this$0 = draggableNode;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$$this$coroutineScope, this.this$0, continuation);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:17:0x0066  */
            /* JADX WARN: Code duplicated, block: B:19:0x0091 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:20:0x0092  */
            /* JADX WARN: Code duplicated, block: B:23:0x009a  */
            /* JADX WARN: Code duplicated, block: B:26:0x00dc A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:27:0x00dd  */
            /* JADX WARN: Code duplicated, block: B:30:0x00f0  */
            /* JADX WARN: Code duplicated, block: B:32:0x0107  */
            /* JADX WARN: Code duplicated, block: B:33:0x010a  */
            /* JADX WARN: Code duplicated, block: B:35:0x0116  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00dd -> B:68:0x00e7). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x014f -> B:15:0x005c). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x0196 -> B:15:0x005c). Please report as a decompilation issue!!! */
            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final java.lang.Object invokeSuspend(java.lang.Object r26) {
                /*
                    Method dump skipped, instruction units count: 424
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DraggableNode$pointerInputNode$1.AnonymousClass1.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }
}
