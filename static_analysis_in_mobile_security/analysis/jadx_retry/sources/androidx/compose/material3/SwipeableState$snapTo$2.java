package androidx.compose.material3;

import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Swipeable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u00002\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@"}, d2 = {"T", "", "", "anchors", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
final class SwipeableState$snapTo$2<T> implements FlowCollector<Map<Float, ? extends T>> {
    final /* synthetic */ T $targetValue;
    final /* synthetic */ SwipeableState<T> this$0;

    SwipeableState$snapTo$2(T t, SwipeableState<T> swipeableState) {
        this.$targetValue = t;
        this.this$0 = swipeableState;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
        return emit((Map) value, (Continuation<? super Unit>) $completion);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object emit(Map<Float, ? extends T> map, Continuation<? super Unit> continuation) {
        SwipeableState$snapTo$2$emit$1 swipeableState$snapTo$2$emit$1;
        SwipeableState$snapTo$2<T> swipeableState$snapTo$2;
        if (continuation instanceof SwipeableState$snapTo$2$emit$1) {
            swipeableState$snapTo$2$emit$1 = (SwipeableState$snapTo$2$emit$1) continuation;
            if ((swipeableState$snapTo$2$emit$1.label & Integer.MIN_VALUE) != 0) {
                swipeableState$snapTo$2$emit$1.label -= Integer.MIN_VALUE;
            } else {
                swipeableState$snapTo$2$emit$1 = new SwipeableState$snapTo$2$emit$1(this, continuation);
            }
        } else {
            swipeableState$snapTo$2$emit$1 = new SwipeableState$snapTo$2$emit$1(this, continuation);
        }
        SwipeableState$snapTo$2$emit$1 swipeableState$snapTo$2$emit$2 = swipeableState$snapTo$2$emit$1;
        Object $result = swipeableState$snapTo$2$emit$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (swipeableState$snapTo$2$emit$2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Float targetOffset = SwipeableKt.getOffset(map, this.$targetValue);
                if (targetOffset == null) {
                    throw new IllegalArgumentException("The target value must have an associated anchor.".toString());
                }
                SwipeableState<T> swipeableState = this.this$0;
                float fFloatValue = targetOffset.floatValue();
                swipeableState$snapTo$2$emit$2.L$0 = this;
                swipeableState$snapTo$2$emit$2.label = 1;
                if (swipeableState.snapInternalToOffset(fFloatValue, swipeableState$snapTo$2$emit$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                swipeableState$snapTo$2 = this;
                break;
                break;
            case 1:
                swipeableState$snapTo$2 = (SwipeableState$snapTo$2) swipeableState$snapTo$2$emit$2.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        swipeableState$snapTo$2.this$0.setCurrentValue(swipeableState$snapTo$2.$targetValue);
        return Unit.INSTANCE;
    }
}
