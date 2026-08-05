package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: TapGestureDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0011\u0010\u000f\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0013\u001a\u00020\u0010J\u0011\u0010\u0014\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0011\u0010\u0015\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u001a\u0010\u0016\u001a\u00020\u0017*\u00020\u0018H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u0016\u001a\u00020\u0017*\u00020\u001bH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010\u001e\u001a\u00020\u0018*\u00020\u001bH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010\u001e\u001a\u00020\u0018*\u00020\u0005H\u0097\u0001ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u001d\u0010\u001e\u001a\u00020\u0018*\u00020\u0017H\u0097\u0001ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010#J\u001a\u0010$\u001a\u00020%*\u00020&H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u001a\u0010)\u001a\u00020\u0005*\u00020\u0018H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b*\u0010\"J\u001a\u0010)\u001a\u00020\u0005*\u00020\u001bH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b+\u0010 J\r\u0010,\u001a\u00020-*\u00020.H\u0097\u0001J\u001a\u0010/\u001a\u00020&*\u00020%H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b0\u0010(J\u001a\u00101\u001a\u00020\u001b*\u00020\u0018H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b2\u00103J\u001d\u00101\u001a\u00020\u001b*\u00020\u0005H\u0097\u0001ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b4\u00103J\u001d\u00101\u001a\u00020\u001b*\u00020\u0017H\u0097\u0001ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b4\u00105R\u0014\u0010\u0003\u001a\u00020\u00058\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00058\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00066"}, d2 = {"Landroidx/compose/foundation/gestures/PressGestureScopeImpl;", "Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/unit/Density;", "density", "(Landroidx/compose/ui/unit/Density;)V", "", "getDensity", "()F", "fontScale", "getFontScale", "isCanceled", "", "isReleased", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "awaitRelease", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancel", "release", "reset", "tryAwaitRelease", "roundToPx", "", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-GaN1DYA", "(J)F", "toDp-u2uoSUM", "(F)F", "(I)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "Landroidx/compose/ui/geometry/Size;", "toDpSize-k-rfVVM", "(J)J", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-0xMU5do", "(F)J", "toSp-kPz2Gy4", "(I)J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class PressGestureScopeImpl implements PressGestureScope, Density {
    private final /* synthetic */ Density $$delegate_0;
    private boolean isCanceled;
    private boolean isReleased;
    private final Mutex mutex;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.PressGestureScopeImpl$awaitRelease$1, reason: invalid class name */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.PressGestureScopeImpl", f = "TapGestureDetector.kt", i = {}, l = {363}, m = "awaitRelease", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PressGestureScopeImpl.this.awaitRelease(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.PressGestureScopeImpl$reset$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.PressGestureScopeImpl", f = "TapGestureDetector.kt", i = {0}, l = {357}, m = "reset", n = {"this"}, s = {"L$0"})
    static final class C01881 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C01881(Continuation<? super C01881> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PressGestureScopeImpl.this.reset(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.PressGestureScopeImpl$tryAwaitRelease$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.PressGestureScopeImpl", f = "TapGestureDetector.kt", i = {0}, l = {370}, m = "tryAwaitRelease", n = {"this"}, s = {"L$0"})
    static final class C01891 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C01891(Continuation<? super C01891> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PressGestureScopeImpl.this.tryAwaitRelease(this);
        }
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.$$delegate_0.getDensity();
    }

    @Override // androidx.compose.ui.unit.Density
    public float getFontScale() {
        return this.$$delegate_0.getFontScale();
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
    public int mo320roundToPxR2X_6o(long j) {
        return this.$$delegate_0.mo320roundToPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
    public int mo321roundToPx0680j_4(float f) {
        return this.$$delegate_0.mo321roundToPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
    public float mo322toDpGaN1DYA(long j) {
        return this.$$delegate_0.mo322toDpGaN1DYA(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
    public float mo323toDpu2uoSUM(float f) {
        return this.$$delegate_0.mo323toDpu2uoSUM(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
    public float mo324toDpu2uoSUM(int i) {
        return this.$$delegate_0.mo324toDpu2uoSUM(i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
    public long mo325toDpSizekrfVVM(long j) {
        return this.$$delegate_0.mo325toDpSizekrfVVM(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
    public float mo326toPxR2X_6o(long j) {
        return this.$$delegate_0.mo326toPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
    public float mo327toPx0680j_4(float f) {
        return this.$$delegate_0.mo327toPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    public Rect toRect(DpRect dpRect) {
        Intrinsics.checkNotNullParameter(dpRect, "<this>");
        return this.$$delegate_0.toRect(dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
    public long mo328toSizeXkaWNTQ(long j) {
        return this.$$delegate_0.mo328toSizeXkaWNTQ(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
    public long mo329toSp0xMU5do(float f) {
        return this.$$delegate_0.mo329toSp0xMU5do(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
    public long mo330toSpkPz2Gy4(float f) {
        return this.$$delegate_0.mo330toSpkPz2Gy4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
    public long mo331toSpkPz2Gy4(int i) {
        return this.$$delegate_0.mo331toSpkPz2Gy4(i);
    }

    public PressGestureScopeImpl(Density density) {
        Intrinsics.checkNotNullParameter(density, "density");
        this.$$delegate_0 = density;
        this.mutex = MutexKt.Mutex(false);
    }

    public final void cancel() {
        this.isCanceled = true;
        Mutex.DefaultImpls.unlock$default(this.mutex, null, 1, null);
    }

    public final void release() {
        this.isReleased = true;
        Mutex.DefaultImpls.unlock$default(this.mutex, null, 1, null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object reset(Continuation<? super Unit> continuation) {
        C01881 c01881;
        PressGestureScopeImpl pressGestureScopeImpl;
        if (continuation instanceof C01881) {
            c01881 = (C01881) continuation;
            if ((c01881.label & Integer.MIN_VALUE) != 0) {
                c01881.label -= Integer.MIN_VALUE;
            } else {
                c01881 = new C01881(continuation);
            }
        } else {
            c01881 = new C01881(continuation);
        }
        C01881 c01882 = c01881;
        Object $result = c01882.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c01882.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Mutex mutex = this.mutex;
                c01882.L$0 = this;
                c01882.label = 1;
                if (Mutex.DefaultImpls.lock$default(mutex, null, c01882, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                pressGestureScopeImpl = this;
                break;
                break;
            case 1:
                pressGestureScopeImpl = (PressGestureScopeImpl) c01882.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        pressGestureScopeImpl.isReleased = false;
        pressGestureScopeImpl.isCanceled = false;
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.compose.foundation.gestures.PressGestureScope
    public Object awaitRelease(Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Object objTryAwaitRelease;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object $result = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                anonymousClass2.label = 1;
                objTryAwaitRelease = tryAwaitRelease(anonymousClass2);
                if (objTryAwaitRelease == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objTryAwaitRelease = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (!((Boolean) objTryAwaitRelease).booleanValue()) {
            throw new GestureCancellationException("The press gesture was canceled.");
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.compose.foundation.gestures.PressGestureScope
    public Object tryAwaitRelease(Continuation<? super Boolean> continuation) {
        C01891 c01891;
        PressGestureScopeImpl pressGestureScopeImpl;
        PressGestureScopeImpl pressGestureScopeImpl2;
        if (continuation instanceof C01891) {
            c01891 = (C01891) continuation;
            if ((c01891.label & Integer.MIN_VALUE) != 0) {
                c01891.label -= Integer.MIN_VALUE;
            } else {
                c01891 = new C01891(continuation);
            }
        } else {
            c01891 = new C01891(continuation);
        }
        C01891 c01892 = c01891;
        Object $result = c01892.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c01892.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                pressGestureScopeImpl = this;
                if (!pressGestureScopeImpl.isReleased && !pressGestureScopeImpl.isCanceled) {
                    Mutex mutex = pressGestureScopeImpl.mutex;
                    c01892.L$0 = pressGestureScopeImpl;
                    c01892.label = 1;
                    if (Mutex.DefaultImpls.lock$default(mutex, null, c01892, 1, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pressGestureScopeImpl2 = pressGestureScopeImpl;
                    Mutex.DefaultImpls.unlock$default(pressGestureScopeImpl2.mutex, null, 1, null);
                    pressGestureScopeImpl = pressGestureScopeImpl2;
                }
                return Boxing.boxBoolean(pressGestureScopeImpl.isReleased);
            case 1:
                pressGestureScopeImpl2 = (PressGestureScopeImpl) c01892.L$0;
                ResultKt.throwOnFailure($result);
                Mutex.DefaultImpls.unlock$default(pressGestureScopeImpl2.mutex, null, 1, null);
                pressGestureScopeImpl = pressGestureScopeImpl2;
                return Boxing.boxBoolean(pressGestureScopeImpl.isReleased);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
