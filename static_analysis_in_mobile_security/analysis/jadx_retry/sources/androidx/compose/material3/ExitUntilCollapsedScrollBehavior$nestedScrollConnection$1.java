package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rJ%\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"androidx/compose/material3/ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1 implements NestedScrollConnection {
    final /* synthetic */ ExitUntilCollapsedScrollBehavior this$0;

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    public /* synthetic */ Object mo336onPreFlingQWom1Mo(long j, Continuation continuation) {
        return NestedScrollConnection.CC.m3985onPreFlingQWom1Mo$suspendImpl(this, j, continuation);
    }

    ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1(ExitUntilCollapsedScrollBehavior $receiver) {
        this.this$0 = $receiver;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo337onPreScrollOzD1aCk(long available, int source) {
        if (!this.this$0.getCanScroll().invoke().booleanValue() || Offset.m2732getYimpl(available) > 0.0f) {
            return Offset.INSTANCE.m2747getZeroF1C5BW0();
        }
        float prevHeightOffset = this.this$0.getState().getHeightOffset();
        this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Offset.m2732getYimpl(available));
        if (!(prevHeightOffset == this.this$0.getState().getHeightOffset())) {
            return Offset.m2725copydBAh8RU$default(available, 0.0f, 0.0f, 2, null);
        }
        return Offset.INSTANCE.m2747getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo335onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.this$0.getCanScroll().invoke().booleanValue()) {
            return Offset.INSTANCE.m2747getZeroF1C5BW0();
        }
        TopAppBarState state = this.this$0.getState();
        state.setContentOffset(state.getContentOffset() + Offset.m2732getYimpl(consumed));
        if (Offset.m2732getYimpl(available) < 0.0f || Offset.m2732getYimpl(consumed) < 0.0f) {
            float oldHeightOffset = this.this$0.getState().getHeightOffset();
            this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Offset.m2732getYimpl(consumed));
            return OffsetKt.Offset(0.0f, this.this$0.getState().getHeightOffset() - oldHeightOffset);
        }
        if ((Offset.m2732getYimpl(consumed) == 0.0f) && Offset.m2732getYimpl(available) > 0.0f) {
            this.this$0.getState().setContentOffset(0.0f);
        }
        if (Offset.m2732getYimpl(available) > 0.0f) {
            float oldHeightOffset2 = this.this$0.getState().getHeightOffset();
            this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Offset.m2732getYimpl(available));
            return OffsetKt.Offset(0.0f, this.this$0.getState().getHeightOffset() - oldHeightOffset2);
        }
        return Offset.INSTANCE.m2747getZeroF1C5BW0();
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0083 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public Object mo334onPostFlingRZ2iAVY(long consumed, long j, Continuation<? super Velocity> continuation) {
        ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1;
        ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1 exitUntilCollapsedScrollBehavior$nestedScrollConnection$1;
        long available;
        Object objM3984onPostFlingRZ2iAVY$suspendImpl;
        long packedValue;
        Object obj;
        if (continuation instanceof ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1) {
            exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 = (ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1) continuation;
            if ((exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1.label & Integer.MIN_VALUE) != 0) {
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1.label -= Integer.MIN_VALUE;
            } else {
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 = new ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1(this, continuation);
            }
        } else {
            exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 = new ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1(this, continuation);
        }
        ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1;
        Object $result = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1 = this;
                available = j;
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.L$0 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1;
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0 = available;
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.label = 1;
                objM3984onPostFlingRZ2iAVY$suspendImpl = NestedScrollConnection.CC.m3984onPostFlingRZ2iAVY$suspendImpl(exitUntilCollapsedScrollBehavior$nestedScrollConnection$1, consumed, available, exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2);
                if (objM3984onPostFlingRZ2iAVY$suspendImpl == coroutine_suspended) {
                    return coroutine_suspended;
                }
                packedValue = ((Velocity) objM3984onPostFlingRZ2iAVY$suspendImpl).getPackedValue();
                TopAppBarState state = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getState();
                float fM5500getYimpl = Velocity.m5500getYimpl(available);
                DecayAnimationSpec<Float> flingAnimationSpec = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getFlingAnimationSpec();
                AnimationSpec<Float> snapAnimationSpec = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getSnapAnimationSpec();
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.L$0 = null;
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0 = packedValue;
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.label = 2;
                obj = AppBarKt.settleAppBar(state, fM5500getYimpl, flingAnimationSpec, snapAnimationSpec, exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Velocity.m5490boximpl(Velocity.m5503plusAH228Gc(packedValue, ((Velocity) obj).getPackedValue()));
            case 1:
                available = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0;
                ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1 exitUntilCollapsedScrollBehavior$nestedScrollConnection$2 = (ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1) exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.L$0;
                ResultKt.throwOnFailure($result);
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$2;
                objM3984onPostFlingRZ2iAVY$suspendImpl = $result;
                packedValue = ((Velocity) objM3984onPostFlingRZ2iAVY$suspendImpl).getPackedValue();
                TopAppBarState state2 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getState();
                float fM5500getYimpl2 = Velocity.m5500getYimpl(available);
                DecayAnimationSpec<Float> flingAnimationSpec2 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getFlingAnimationSpec();
                AnimationSpec<Float> snapAnimationSpec2 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getSnapAnimationSpec();
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.L$0 = null;
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0 = packedValue;
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.label = 2;
                obj = AppBarKt.settleAppBar(state2, fM5500getYimpl2, flingAnimationSpec2, snapAnimationSpec2, exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Velocity.m5490boximpl(Velocity.m5503plusAH228Gc(packedValue, ((Velocity) obj).getPackedValue()));
            case 2:
                long j2 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0;
                ResultKt.throwOnFailure($result);
                packedValue = j2;
                obj = $result;
                return Velocity.m5490boximpl(Velocity.m5503plusAH228Gc(packedValue, ((Velocity) obj).getPackedValue()));
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
