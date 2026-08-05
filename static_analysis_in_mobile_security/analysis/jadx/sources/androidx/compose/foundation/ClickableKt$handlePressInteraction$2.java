package androidx.compose.foundation;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: compiled from: Clickable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@DebugMetadata(c = "androidx.compose.foundation.ClickableKt$handlePressInteraction$2", f = "Clickable.kt", i = {0, 1, 2}, l = {299, 301, 308, 309, 318}, m = "invokeSuspend", n = {"delayJob", "success", "release"}, s = {"L$0", "Z$0", "L$0"})
final class ClickableKt$handlePressInteraction$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Boolean> $delayPressInteraction;
    final /* synthetic */ AbstractClickableNode.InteractionData $interactionData;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ long $pressPoint;
    final /* synthetic */ PressGestureScope $this_handlePressInteraction;
    private /* synthetic */ Object L$0;
    boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ClickableKt$handlePressInteraction$2(PressGestureScope pressGestureScope, long j, MutableInteractionSource mutableInteractionSource, AbstractClickableNode.InteractionData interactionData, Function0<Boolean> function0, Continuation<? super ClickableKt$handlePressInteraction$2> continuation) {
        super(2, continuation);
        this.$this_handlePressInteraction = pressGestureScope;
        this.$pressPoint = j;
        this.$interactionSource = mutableInteractionSource;
        this.$interactionData = interactionData;
        this.$delayPressInteraction = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ClickableKt$handlePressInteraction$2 clickableKt$handlePressInteraction$2 = new ClickableKt$handlePressInteraction$2(this.$this_handlePressInteraction, this.$pressPoint, this.$interactionSource, this.$interactionData, this.$delayPressInteraction, continuation);
        clickableKt$handlePressInteraction$2.L$0 = obj;
        return clickableKt$handlePressInteraction$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ClickableKt$handlePressInteraction$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x008e  */
    /* JADX WARN: Code duplicated, block: B:18:0x009e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x009f  */
    /* JADX WARN: Code duplicated, block: B:21:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:23:0x00c1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:27:0x00d6 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:30:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:32:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:34:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:35:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:38:0x0108 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:39:0x0109  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ClickableKt$handlePressInteraction$2 clickableKt$handlePressInteraction$2;
        Object $result;
        Job delayJob;
        Object objTryAwaitRelease;
        boolean success;
        PressInteraction.Press pressInteraction;
        MutableInteractionSource mutableInteractionSource;
        PressInteraction cancel;
        PressInteraction endInteraction;
        ClickableKt$handlePressInteraction$2 clickableKt$handlePressInteraction$3;
        Object $result2;
        boolean success2;
        PressInteraction.Press press;
        PressInteraction.Release release;
        PressInteraction.Release release2;
        ClickableKt$handlePressInteraction$2 clickableKt$handlePressInteraction$4;
        Object $result3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                clickableKt$handlePressInteraction$2 = this;
                $result = obj;
                CoroutineScope $this$coroutineScope = (CoroutineScope) clickableKt$handlePressInteraction$2.L$0;
                delayJob = BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new ClickableKt$handlePressInteraction$2$delayJob$1(clickableKt$handlePressInteraction$2.$delayPressInteraction, clickableKt$handlePressInteraction$2.$pressPoint, clickableKt$handlePressInteraction$2.$interactionSource, clickableKt$handlePressInteraction$2.$interactionData, null), 3, null);
                clickableKt$handlePressInteraction$2.L$0 = delayJob;
                clickableKt$handlePressInteraction$2.label = 1;
                objTryAwaitRelease = clickableKt$handlePressInteraction$2.$this_handlePressInteraction.tryAwaitRelease(clickableKt$handlePressInteraction$2);
                if (objTryAwaitRelease == coroutine_suspended) {
                    return coroutine_suspended;
                }
                success = ((Boolean) objTryAwaitRelease).booleanValue();
                if (delayJob.isActive()) {
                    clickableKt$handlePressInteraction$2.L$0 = null;
                    clickableKt$handlePressInteraction$2.Z$0 = success;
                    clickableKt$handlePressInteraction$2.label = 2;
                    if (JobKt.cancelAndJoin(delayJob, clickableKt$handlePressInteraction$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    success2 = success;
                    if (success2) {
                        press = new PressInteraction.Press(clickableKt$handlePressInteraction$2.$pressPoint, null);
                        release = new PressInteraction.Release(press);
                        clickableKt$handlePressInteraction$2.L$0 = release;
                        clickableKt$handlePressInteraction$2.label = 3;
                        if (clickableKt$handlePressInteraction$2.$interactionSource.emit(press, clickableKt$handlePressInteraction$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        release2 = release;
                        clickableKt$handlePressInteraction$2.L$0 = null;
                        clickableKt$handlePressInteraction$2.label = 4;
                        if (clickableKt$handlePressInteraction$2.$interactionSource.emit(release2, clickableKt$handlePressInteraction$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        clickableKt$handlePressInteraction$4 = clickableKt$handlePressInteraction$2;
                        $result3 = $result;
                        clickableKt$handlePressInteraction$2 = clickableKt$handlePressInteraction$4;
                    }
                } else {
                    pressInteraction = clickableKt$handlePressInteraction$2.$interactionData.getPressInteraction();
                    if (pressInteraction != null) {
                        mutableInteractionSource = clickableKt$handlePressInteraction$2.$interactionSource;
                        if (success) {
                            cancel = new PressInteraction.Release(pressInteraction);
                        } else {
                            cancel = new PressInteraction.Cancel(pressInteraction);
                        }
                        endInteraction = cancel;
                        clickableKt$handlePressInteraction$2.L$0 = null;
                        clickableKt$handlePressInteraction$2.label = 5;
                        if (mutableInteractionSource.emit(endInteraction, clickableKt$handlePressInteraction$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        clickableKt$handlePressInteraction$3 = clickableKt$handlePressInteraction$2;
                        $result2 = $result;
                        clickableKt$handlePressInteraction$2 = clickableKt$handlePressInteraction$3;
                    }
                }
                clickableKt$handlePressInteraction$2.$interactionData.setPressInteraction(null);
                return Unit.INSTANCE;
            case 1:
                clickableKt$handlePressInteraction$2 = this;
                $result = obj;
                delayJob = (Job) clickableKt$handlePressInteraction$2.L$0;
                ResultKt.throwOnFailure($result);
                objTryAwaitRelease = $result;
                success = ((Boolean) objTryAwaitRelease).booleanValue();
                if (delayJob.isActive()) {
                    clickableKt$handlePressInteraction$2.L$0 = null;
                    clickableKt$handlePressInteraction$2.Z$0 = success;
                    clickableKt$handlePressInteraction$2.label = 2;
                    if (JobKt.cancelAndJoin(delayJob, clickableKt$handlePressInteraction$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    success2 = success;
                    if (success2) {
                        press = new PressInteraction.Press(clickableKt$handlePressInteraction$2.$pressPoint, null);
                        release = new PressInteraction.Release(press);
                        clickableKt$handlePressInteraction$2.L$0 = release;
                        clickableKt$handlePressInteraction$2.label = 3;
                        if (clickableKt$handlePressInteraction$2.$interactionSource.emit(press, clickableKt$handlePressInteraction$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        release2 = release;
                        clickableKt$handlePressInteraction$2.L$0 = null;
                        clickableKt$handlePressInteraction$2.label = 4;
                        if (clickableKt$handlePressInteraction$2.$interactionSource.emit(release2, clickableKt$handlePressInteraction$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        clickableKt$handlePressInteraction$4 = clickableKt$handlePressInteraction$2;
                        $result3 = $result;
                        clickableKt$handlePressInteraction$2 = clickableKt$handlePressInteraction$4;
                    }
                } else {
                    pressInteraction = clickableKt$handlePressInteraction$2.$interactionData.getPressInteraction();
                    if (pressInteraction != null) {
                        mutableInteractionSource = clickableKt$handlePressInteraction$2.$interactionSource;
                        if (success) {
                            cancel = new PressInteraction.Release(pressInteraction);
                        } else {
                            cancel = new PressInteraction.Cancel(pressInteraction);
                        }
                        endInteraction = cancel;
                        clickableKt$handlePressInteraction$2.L$0 = null;
                        clickableKt$handlePressInteraction$2.label = 5;
                        if (mutableInteractionSource.emit(endInteraction, clickableKt$handlePressInteraction$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        clickableKt$handlePressInteraction$3 = clickableKt$handlePressInteraction$2;
                        $result2 = $result;
                        clickableKt$handlePressInteraction$2 = clickableKt$handlePressInteraction$3;
                    }
                }
                clickableKt$handlePressInteraction$2.$interactionData.setPressInteraction(null);
                return Unit.INSTANCE;
            case 2:
                clickableKt$handlePressInteraction$2 = this;
                $result = obj;
                success2 = clickableKt$handlePressInteraction$2.Z$0;
                ResultKt.throwOnFailure($result);
                if (success2) {
                    press = new PressInteraction.Press(clickableKt$handlePressInteraction$2.$pressPoint, null);
                    release = new PressInteraction.Release(press);
                    clickableKt$handlePressInteraction$2.L$0 = release;
                    clickableKt$handlePressInteraction$2.label = 3;
                    if (clickableKt$handlePressInteraction$2.$interactionSource.emit(press, clickableKt$handlePressInteraction$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    release2 = release;
                    clickableKt$handlePressInteraction$2.L$0 = null;
                    clickableKt$handlePressInteraction$2.label = 4;
                    if (clickableKt$handlePressInteraction$2.$interactionSource.emit(release2, clickableKt$handlePressInteraction$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    clickableKt$handlePressInteraction$4 = clickableKt$handlePressInteraction$2;
                    $result3 = $result;
                    clickableKt$handlePressInteraction$2 = clickableKt$handlePressInteraction$4;
                }
                clickableKt$handlePressInteraction$2.$interactionData.setPressInteraction(null);
                return Unit.INSTANCE;
            case 3:
                clickableKt$handlePressInteraction$2 = this;
                $result = obj;
                release2 = (PressInteraction.Release) clickableKt$handlePressInteraction$2.L$0;
                ResultKt.throwOnFailure($result);
                clickableKt$handlePressInteraction$2.L$0 = null;
                clickableKt$handlePressInteraction$2.label = 4;
                if (clickableKt$handlePressInteraction$2.$interactionSource.emit(release2, clickableKt$handlePressInteraction$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                clickableKt$handlePressInteraction$4 = clickableKt$handlePressInteraction$2;
                $result3 = $result;
                clickableKt$handlePressInteraction$2 = clickableKt$handlePressInteraction$4;
                clickableKt$handlePressInteraction$2.$interactionData.setPressInteraction(null);
                return Unit.INSTANCE;
            case 4:
                clickableKt$handlePressInteraction$4 = this;
                $result3 = obj;
                ResultKt.throwOnFailure($result3);
                clickableKt$handlePressInteraction$2 = clickableKt$handlePressInteraction$4;
                clickableKt$handlePressInteraction$2.$interactionData.setPressInteraction(null);
                return Unit.INSTANCE;
            case 5:
                clickableKt$handlePressInteraction$3 = this;
                $result2 = obj;
                ResultKt.throwOnFailure($result2);
                clickableKt$handlePressInteraction$2 = clickableKt$handlePressInteraction$3;
                clickableKt$handlePressInteraction$2.$interactionData.setPressInteraction(null);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
