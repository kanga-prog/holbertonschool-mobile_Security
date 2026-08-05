package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2", f = "Scrollable.kt", i = {}, l = {500}, m = "invokeSuspend", n = {}, s = {})
final class ScrollingLogic$doFlingAnimation$2 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $available;
    final /* synthetic */ Ref.LongRef $result;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ ScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScrollingLogic$doFlingAnimation$2(ScrollingLogic scrollingLogic, Ref.LongRef longRef, long j, Continuation<? super ScrollingLogic$doFlingAnimation$2> continuation) {
        super(2, continuation);
        this.this$0 = scrollingLogic;
        this.$result = longRef;
        this.$available = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ScrollingLogic$doFlingAnimation$2 scrollingLogic$doFlingAnimation$2 = new ScrollingLogic$doFlingAnimation$2(this.this$0, this.$result, this.$available, continuation);
        scrollingLogic$doFlingAnimation$2.L$0 = obj;
        return scrollingLogic$doFlingAnimation$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
        return ((ScrollingLogic$doFlingAnimation$2) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        ScrollingLogic scrollingLogic;
        Ref.LongRef longRef;
        ScrollingLogic scrollingLogic2;
        long j;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final ScrollScope $this$scroll = (ScrollScope) this.L$0;
                final ScrollingLogic scrollingLogic3 = this.this$0;
                final Function1<Offset, Offset> function1 = new Function1<Offset, Offset>() { // from class: androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2$outerScopeScroll$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Offset invoke(Offset offset) {
                        return Offset.m2720boximpl(m350invokeMKHz9U(offset.getPackedValue()));
                    }

                    /* JADX INFO: renamed from: invoke-MK-Hz9U, reason: not valid java name */
                    public final long m350invokeMKHz9U(long delta) {
                        ScrollingLogic scrollingLogic4 = scrollingLogic3;
                        return scrollingLogic4.m342reverseIfNeededMKHz9U(scrollingLogic4.m338dispatchScroll3eAAhYA($this$scroll, scrollingLogic4.m342reverseIfNeededMKHz9U(delta), NestedScrollSource.INSTANCE.m4003getFlingWNlRxjI()));
                    }
                };
                final ScrollingLogic scrollingLogic4 = this.this$0;
                ScrollScope scrollScope = new ScrollScope() { // from class: androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2$scope$1
                    @Override // androidx.compose.foundation.gestures.ScrollScope
                    public float scrollBy(float pixels) {
                        ScrollingLogic scrollingLogic5 = scrollingLogic4;
                        return scrollingLogic5.m346toFloatk4lQ0M(function1.invoke(Offset.m2720boximpl(scrollingLogic5.m347toOffsettuRUvjQ(pixels))).getPackedValue());
                    }
                };
                scrollingLogic = this.this$0;
                longRef = this.$result;
                long j2 = this.$available;
                FlingBehavior $this$invokeSuspend_u24lambda_u241_u24lambda_u240 = scrollingLogic.getFlingBehavior();
                long j3 = longRef.element;
                ScrollScope scrollScope2 = scrollScope;
                float fReverseIfNeeded = scrollingLogic.reverseIfNeeded(scrollingLogic.m345toFloatTH1AsA0(j2));
                this.L$0 = scrollingLogic;
                this.L$1 = scrollingLogic;
                this.L$2 = longRef;
                this.J$0 = j3;
                this.label = 1;
                Object objPerformFling = $this$invokeSuspend_u24lambda_u241_u24lambda_u240.performFling(scrollScope2, fReverseIfNeeded, this);
                if (objPerformFling == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = objPerformFling;
                scrollingLogic2 = scrollingLogic;
                j = j3;
                break;
            case 1:
                j = this.J$0;
                longRef = (Ref.LongRef) this.L$2;
                scrollingLogic = (ScrollingLogic) this.L$1;
                ScrollingLogic scrollingLogic5 = (ScrollingLogic) this.L$0;
                ResultKt.throwOnFailure($result);
                scrollingLogic2 = scrollingLogic5;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        longRef.element = scrollingLogic.m348updateQWom1Mo(j, scrollingLogic2.reverseIfNeeded(((Number) $result).floatValue()));
        return Unit.INSTANCE;
    }
}
