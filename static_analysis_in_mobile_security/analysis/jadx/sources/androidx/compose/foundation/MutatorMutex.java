package androidx.compose.foundation;

import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: MutatorMutex.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J?\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u001c\u0010\r\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010JX\u0010\u0011\u001a\u0002H\n\"\u0004\b\u0000\u0010\u0012\"\u0004\b\u0001\u0010\n2\u0006\u0010\u0013\u001a\u0002H\u00122\b\b\u0002\u0010\u000b\u001a\u00020\f2'\u0010\r\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0014¢\u0006\u0002\b\u0015H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0002R\"\u0010\u0003\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/MutatorMutex;", "", "()V", "currentMutator", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/foundation/MutatorMutex$Mutator;", "Landroidx/compose/foundation/AtomicReference;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "mutate", "R", "priority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mutateWith", "T", "receiver", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryMutateOrCancel", "", "mutator", "Mutator", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MutatorMutex {
    public static final int $stable = 0;
    private final AtomicReference<Mutator> currentMutator = new AtomicReference<>(null);
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, null);

    /* JADX INFO: compiled from: MutatorMutex.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0000J\u0006\u0010\u000e\u001a\u00020\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/MutatorMutex$Mutator;", "", "priority", "Landroidx/compose/foundation/MutatePriority;", "job", "Lkotlinx/coroutines/Job;", "(Landroidx/compose/foundation/MutatePriority;Lkotlinx/coroutines/Job;)V", "getJob", "()Lkotlinx/coroutines/Job;", "getPriority", "()Landroidx/compose/foundation/MutatePriority;", "canInterrupt", "", "other", "cancel", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Mutator {
        private final Job job;
        private final MutatePriority priority;

        public Mutator(MutatePriority priority, Job job) {
            Intrinsics.checkNotNullParameter(priority, "priority");
            Intrinsics.checkNotNullParameter(job, "job");
            this.priority = priority;
            this.job = job;
        }

        public final Job getJob() {
            return this.job;
        }

        public final MutatePriority getPriority() {
            return this.priority;
        }

        public final boolean canInterrupt(Mutator other) {
            Intrinsics.checkNotNullParameter(other, "other");
            return this.priority.compareTo(other.priority) >= 0;
        }

        public final void cancel() {
            this.job.cancel((CancellationException) new MutationInterruptedException());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void tryMutateOrCancel(Mutator mutator) {
        Mutator oldMutator;
        do {
            oldMutator = this.currentMutator.get();
            if (oldMutator != null && !mutator.canInterrupt(oldMutator)) {
                throw new CancellationException("Current mutation had a higher priority");
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.currentMutator, oldMutator, mutator));
        if (oldMutator != null) {
            oldMutator.cancel();
        }
    }

    public static /* synthetic */ Object mutate$default(MutatorMutex mutatorMutex, MutatePriority mutatePriority, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return mutatorMutex.mutate(mutatePriority, function1, continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.foundation.MutatorMutex$mutate$2, reason: invalid class name */
    /* JADX INFO: compiled from: MutatorMutex.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"R", "Lkotlinx/coroutines/CoroutineScope;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.foundation.MutatorMutex$mutate$2", f = "MutatorMutex.kt", i = {0, 0, 1, 1}, l = {186, 132}, m = "invokeSuspend", n = {"mutator", "$this$withLock_u24default$iv", "mutator", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class AnonymousClass2<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function1<Continuation<? super R>, Object> $block;
        final /* synthetic */ MutatePriority $priority;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ MutatorMutex this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(MutatePriority mutatePriority, MutatorMutex mutatorMutex, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$priority = mutatePriority;
            this.this$0 = mutatorMutex;
            this.$block = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$priority, this.this$0, this.$block, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:19:0x00a8 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:20:0x00a9  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            AnonymousClass2<R> anonymousClass2;
            Function1<Continuation<? super R>, Object> function1;
            Mutator mutator;
            Mutator mutator2;
            Mutex $this$withLock_u24default$iv;
            MutatorMutex mutatorMutex;
            Object owner$iv;
            Object owner$iv2;
            Mutex $this$withLock_u24default$iv2;
            Mutator mutator3;
            Throwable th;
            AnonymousClass2<R> anonymousClass3;
            Mutator mutator4;
            Object objInvoke;
            Throwable th2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    anonymousClass2 = this;
                    CoroutineScope $this$coroutineScope = (CoroutineScope) anonymousClass2.L$0;
                    MutatePriority mutatePriority = anonymousClass2.$priority;
                    CoroutineContext.Element element = $this$coroutineScope.getCoroutineContext().get(Job.INSTANCE);
                    Intrinsics.checkNotNull(element);
                    Mutator mutator5 = new Mutator(mutatePriority, (Job) element);
                    anonymousClass2.this$0.tryMutateOrCancel(mutator5);
                    Mutex $this$withLock_u24default$iv3 = anonymousClass2.this$0.mutex;
                    function1 = anonymousClass2.$block;
                    MutatorMutex mutatorMutex2 = anonymousClass2.this$0;
                    anonymousClass2.L$0 = mutator5;
                    anonymousClass2.L$1 = $this$withLock_u24default$iv3;
                    anonymousClass2.L$2 = function1;
                    anonymousClass2.L$3 = mutatorMutex2;
                    anonymousClass2.label = 1;
                    if ($this$withLock_u24default$iv3.lock(null, anonymousClass2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutator = mutator5;
                    mutator2 = null;
                    $this$withLock_u24default$iv = $this$withLock_u24default$iv3;
                    mutatorMutex = mutatorMutex2;
                    owner$iv = null;
                    try {
                        anonymousClass2.L$0 = mutator;
                        anonymousClass2.L$1 = $this$withLock_u24default$iv;
                        anonymousClass2.L$2 = mutatorMutex;
                        anonymousClass2.L$3 = null;
                        anonymousClass2.label = 2;
                        objInvoke = function1.invoke(anonymousClass2);
                        if (objInvoke == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objInvoke;
                        owner$iv2 = owner$iv;
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                        mutator3 = mutator;
                        try {
                            MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex.currentMutator, mutator3, null);
                            $this$withLock_u24default$iv2.unlock(owner$iv2);
                            return $result;
                        } catch (Throwable th3) {
                            th2 = th3;
                            $this$withLock_u24default$iv2.unlock(owner$iv2);
                            throw th2;
                        }
                    } catch (Throwable th4) {
                        owner$iv2 = owner$iv;
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                        mutator3 = mutator;
                        th = th4;
                        anonymousClass3 = anonymousClass2;
                        mutator4 = mutator2;
                        try {
                            MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex.currentMutator, mutator3, null);
                            throw th;
                        } catch (Throwable th5) {
                            th2 = th5;
                            $this$withLock_u24default$iv2.unlock(owner$iv2);
                            throw th2;
                        }
                    }
                case 1:
                    anonymousClass2 = this;
                    mutator2 = null;
                    mutatorMutex = (MutatorMutex) anonymousClass2.L$3;
                    function1 = (Function1) anonymousClass2.L$2;
                    owner$iv = null;
                    $this$withLock_u24default$iv = (Mutex) anonymousClass2.L$1;
                    mutator = (Mutator) anonymousClass2.L$0;
                    ResultKt.throwOnFailure($result);
                    anonymousClass2.L$0 = mutator;
                    anonymousClass2.L$1 = $this$withLock_u24default$iv;
                    anonymousClass2.L$2 = mutatorMutex;
                    anonymousClass2.L$3 = null;
                    anonymousClass2.label = 2;
                    objInvoke = function1.invoke(anonymousClass2);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objInvoke;
                    owner$iv2 = owner$iv;
                    $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                    mutator3 = mutator;
                    MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex.currentMutator, mutator3, null);
                    $this$withLock_u24default$iv2.unlock(owner$iv2);
                    return $result;
                case 2:
                    anonymousClass3 = this;
                    mutator4 = null;
                    mutatorMutex = (MutatorMutex) anonymousClass3.L$2;
                    owner$iv2 = null;
                    $this$withLock_u24default$iv2 = (Mutex) anonymousClass3.L$1;
                    mutator3 = (Mutator) anonymousClass3.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex.currentMutator, mutator3, null);
                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                        return $result;
                    } catch (Throwable th6) {
                        th = th6;
                        MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex.currentMutator, mutator3, null);
                        throw th;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final <R> Object mutate(MutatePriority priority, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super R> continuation) {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(priority, this, function1, null), continuation);
    }

    public static /* synthetic */ Object mutateWith$default(MutatorMutex mutatorMutex, Object obj, MutatePriority mutatePriority, Function2 function2, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return mutatorMutex.mutateWith(obj, mutatePriority, function2, continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.foundation.MutatorMutex$mutateWith$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MutatorMutex.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u00020\u0002H\u008a@"}, d2 = {"T", "R", "Lkotlinx/coroutines/CoroutineScope;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.foundation.MutatorMutex$mutateWith$2", f = "MutatorMutex.kt", i = {0, 0, 1, 1}, l = {186, 173}, m = "invokeSuspend", n = {"mutator", "$this$withLock_u24default$iv", "mutator", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C01712<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function2<T, Continuation<? super R>, Object> $block;
        final /* synthetic */ MutatePriority $priority;
        final /* synthetic */ T $receiver;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        final /* synthetic */ MutatorMutex this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C01712(MutatePriority mutatePriority, MutatorMutex mutatorMutex, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, T t, Continuation<? super C01712> continuation) {
            super(2, continuation);
            this.$priority = mutatePriority;
            this.this$0 = mutatorMutex;
            this.$block = function2;
            this.$receiver = t;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01712 c01712 = new C01712(this.$priority, this.this$0, this.$block, this.$receiver, continuation);
            c01712.L$0 = obj;
            return c01712;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
            return ((C01712) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:19:0x00b0 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:20:0x00b1  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            C01712<R> c01712;
            Function2 function2;
            Object obj;
            Object mutator;
            Mutator mutator2;
            Mutex $this$withLock_u24default$iv;
            MutatorMutex mutatorMutex;
            Object owner$iv;
            Object owner$iv2;
            Mutex $this$withLock_u24default$iv2;
            Object owner$iv3;
            Throwable th;
            C01712<R> c01713;
            Mutator mutator3;
            Object objInvoke;
            Throwable th2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    c01712 = this;
                    CoroutineScope $this$coroutineScope = (CoroutineScope) c01712.L$0;
                    MutatePriority mutatePriority = c01712.$priority;
                    CoroutineContext.Element element = $this$coroutineScope.getCoroutineContext().get(Job.INSTANCE);
                    Intrinsics.checkNotNull(element);
                    Mutator mutator4 = new Mutator(mutatePriority, (Job) element);
                    c01712.this$0.tryMutateOrCancel(mutator4);
                    Mutex $this$withLock_u24default$iv3 = c01712.this$0.mutex;
                    function2 = c01712.$block;
                    obj = c01712.$receiver;
                    MutatorMutex mutatorMutex2 = c01712.this$0;
                    c01712.L$0 = mutator4;
                    c01712.L$1 = $this$withLock_u24default$iv3;
                    c01712.L$2 = function2;
                    c01712.L$3 = obj;
                    c01712.L$4 = mutatorMutex2;
                    c01712.label = 1;
                    if ($this$withLock_u24default$iv3.lock(null, c01712) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutator = mutator4;
                    mutator2 = null;
                    $this$withLock_u24default$iv = $this$withLock_u24default$iv3;
                    mutatorMutex = mutatorMutex2;
                    owner$iv = null;
                    try {
                        c01712.L$0 = mutator;
                        c01712.L$1 = $this$withLock_u24default$iv;
                        c01712.L$2 = mutatorMutex;
                        c01712.L$3 = null;
                        c01712.L$4 = null;
                        c01712.label = 2;
                        objInvoke = function2.invoke(obj, c01712);
                        if (objInvoke == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objInvoke;
                        owner$iv2 = owner$iv;
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                        owner$iv3 = mutator;
                        try {
                            MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex.currentMutator, owner$iv3, null);
                            $this$withLock_u24default$iv2.unlock(owner$iv2);
                            return $result;
                        } catch (Throwable th3) {
                            th2 = th3;
                            $this$withLock_u24default$iv2.unlock(owner$iv2);
                            throw th2;
                        }
                    } catch (Throwable th4) {
                        owner$iv2 = owner$iv;
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                        owner$iv3 = mutator;
                        th = th4;
                        c01713 = c01712;
                        mutator3 = mutator2;
                        try {
                            MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex.currentMutator, owner$iv3, null);
                            throw th;
                        } catch (Throwable th5) {
                            th2 = th5;
                            $this$withLock_u24default$iv2.unlock(owner$iv2);
                            throw th2;
                        }
                    }
                case 1:
                    c01712 = this;
                    mutator2 = null;
                    mutatorMutex = (MutatorMutex) c01712.L$4;
                    obj = c01712.L$3;
                    function2 = (Function2) c01712.L$2;
                    owner$iv = null;
                    $this$withLock_u24default$iv = (Mutex) c01712.L$1;
                    mutator = (Mutator) c01712.L$0;
                    ResultKt.throwOnFailure($result);
                    c01712.L$0 = mutator;
                    c01712.L$1 = $this$withLock_u24default$iv;
                    c01712.L$2 = mutatorMutex;
                    c01712.L$3 = null;
                    c01712.L$4 = null;
                    c01712.label = 2;
                    objInvoke = function2.invoke(obj, c01712);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objInvoke;
                    owner$iv2 = owner$iv;
                    $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                    owner$iv3 = mutator;
                    MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex.currentMutator, owner$iv3, null);
                    $this$withLock_u24default$iv2.unlock(owner$iv2);
                    return $result;
                case 2:
                    c01713 = this;
                    mutator3 = null;
                    mutatorMutex = (MutatorMutex) c01713.L$2;
                    owner$iv2 = null;
                    $this$withLock_u24default$iv2 = (Mutex) c01713.L$1;
                    owner$iv3 = (Mutator) c01713.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex.currentMutator, owner$iv3, null);
                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                        return $result;
                    } catch (Throwable th6) {
                        th = th6;
                        MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex.currentMutator, owner$iv3, null);
                        throw th;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final <T, R> Object mutateWith(T t, MutatePriority priority, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        return CoroutineScopeKt.coroutineScope(new C01712(priority, this, function2, t, null), continuation);
    }
}
