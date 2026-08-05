package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Swipeable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u00002\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@"}, d2 = {"T", "", "", "anchors", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
final class SwipeableState$animateTo$2<T> implements FlowCollector<Map<Float, ? extends T>> {
    final /* synthetic */ AnimationSpec<Float> $anim;
    final /* synthetic */ T $targetValue;
    final /* synthetic */ SwipeableState<T> this$0;

    SwipeableState$animateTo$2(T t, SwipeableState<T> swipeableState, AnimationSpec<Float> animationSpec) {
        this.$targetValue = t;
        this.this$0 = swipeableState;
        this.$anim = animationSpec;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
        return emit((Map) value, (Continuation<? super Unit>) $completion);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Failed to calculate best type for var: r8v0 androidx.compose.material3.SwipeableState$animateTo$2
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r8v0 androidx.compose.material3.SwipeableState$animateTo$2, new type: androidx.compose.material3.SwipeableState$animateTo$2
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryInsertCasts(FixTypesVisitor.java:477)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Can't change type for register without SSA variable: (r8 I:androidx.compose.material3.SwipeableState$animateTo$2 A[D('this' androidx.compose.material3.SwipeableState$animateTo$2)])
    	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:50)
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.lambda$applyUpdates$1(TypeUpdateInfo.java:80)
    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
    	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.applyUpdates(TypeUpdateInfo.java:80)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:104)
    	... 8 more
     */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x00ea: IGET (r9 I:androidx.compose.material3.SwipeableState<T>) = 
      (r8 I:androidx.compose.material3.SwipeableState$animateTo$2 A[D('this' androidx.compose.material3.SwipeableState$animateTo$2)])
     androidx.compose.material3.SwipeableState$animateTo$2.this$0 androidx.compose.material3.SwipeableState, block:B:39:0x00ea */
    /* JADX WARN: Type inference failed for: r4v0, types: [int, java.util.Map] */
    /* JADX WARN: Type inference failed for: r9v0, types: [androidx.compose.material3.SwipeableState, androidx.compose.material3.SwipeableState<T>] */
    public final Object emit(Map<Float, ? extends T> map, Continuation<? super Unit> continuation) {
        SwipeableState$animateTo$2$emit$1 swipeableState$animateTo$2$emit$1;
        ?? r9;
        SwipeableState$animateTo$2<T> swipeableState$animateTo$2;
        Map<Float, ? extends T> map2;
        if (continuation instanceof SwipeableState$animateTo$2$emit$1) {
            swipeableState$animateTo$2$emit$1 = (SwipeableState$animateTo$2$emit$1) continuation;
            if ((swipeableState$animateTo$2$emit$1.label & Integer.MIN_VALUE) != 0) {
                swipeableState$animateTo$2$emit$1.label -= Integer.MIN_VALUE;
            } else {
                swipeableState$animateTo$2$emit$1 = new SwipeableState$animateTo$2$emit$1(this, continuation);
            }
        } else {
            swipeableState$animateTo$2$emit$1 = new SwipeableState$animateTo$2$emit$1(this, continuation);
        }
        Object obj = swipeableState$animateTo$2$emit$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r4 = swipeableState$animateTo$2$emit$1.label;
        try {
            switch (r4) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    swipeableState$animateTo$2 = this;
                    map2 = map;
                    Float offset = SwipeableKt.getOffset(map2, swipeableState$animateTo$2.$targetValue);
                    if (offset == null) {
                        throw new IllegalArgumentException("The target value must have an associated anchor.".toString());
                    }
                    SwipeableState<T> swipeableState = swipeableState$animateTo$2.this$0;
                    float fFloatValue = offset.floatValue();
                    AnimationSpec<Float> animationSpec = swipeableState$animateTo$2.$anim;
                    swipeableState$animateTo$2$emit$1.L$0 = swipeableState$animateTo$2;
                    swipeableState$animateTo$2$emit$1.L$1 = map2;
                    swipeableState$animateTo$2$emit$1.label = 1;
                    if (swipeableState.animateInternalToOffset(fFloatValue, animationSpec, swipeableState$animateTo$2$emit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    map2 = (Map) swipeableState$animateTo$2$emit$1.L$1;
                    swipeableState$animateTo$2 = (SwipeableState$animateTo$2) swipeableState$animateTo$2$emit$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            float fFloatValue2 = ((Number) ((SwipeableState) swipeableState$animateTo$2.this$0).absoluteOffset.getValue()).floatValue();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<Float, ? extends T> entry : map2.entrySet()) {
                if (Math.abs(entry.getKey().floatValue() - fFloatValue2) < 0.5f) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            Object objFirstOrNull = CollectionsKt.firstOrNull(linkedHashMap.values());
            if (objFirstOrNull == null) {
                objFirstOrNull = swipeableState$animateTo$2.this$0.getCurrentValue();
            }
            swipeableState$animateTo$2.this$0.setCurrentValue(objFirstOrNull);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            SwipeableState$animateTo$2 swipeableState$animateTo$3 = (SwipeableState<T>) r8.this$0;
            float fFloatValue3 = ((Number) ((SwipeableState) r9).absoluteOffset.getValue()).floatValue();
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry entry2 : r4.entrySet()) {
                if (Math.abs(((Number) entry2.getKey()).floatValue() - fFloatValue3) < 0.5f) {
                    linkedHashMap2.put(entry2.getKey(), entry2.getValue());
                }
            }
            Object objFirstOrNull2 = CollectionsKt.firstOrNull(linkedHashMap2.values());
            if (objFirstOrNull2 == null) {
                objFirstOrNull2 = swipeableState$animateTo$3.this$0.getCurrentValue();
            }
            swipeableState$animateTo$3.this$0.setCurrentValue(objFirstOrNull2);
            throw th;
        }
    }
}
