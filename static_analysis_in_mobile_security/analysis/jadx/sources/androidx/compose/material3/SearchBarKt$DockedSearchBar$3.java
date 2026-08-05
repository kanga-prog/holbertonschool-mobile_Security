package androidx.compose.material3;

import androidx.compose.ui.focus.FocusManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: SearchBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.SearchBarKt$DockedSearchBar$3", f = "SearchBar.kt", i = {}, l = {411}, m = "invokeSuspend", n = {}, s = {})
final class SearchBarKt$DockedSearchBar$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $active;
    final /* synthetic */ FocusManager $focusManager;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SearchBarKt$DockedSearchBar$3(boolean z, FocusManager focusManager, Continuation<? super SearchBarKt$DockedSearchBar$3> continuation) {
        super(2, continuation);
        this.$active = z;
        this.$focusManager = focusManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SearchBarKt$DockedSearchBar$3(this.$active, this.$focusManager, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SearchBarKt$DockedSearchBar$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SearchBarKt$DockedSearchBar$3 searchBarKt$DockedSearchBar$3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!this.$active) {
                    this.label = 1;
                    if (DelayKt.delay(100L, this) != coroutine_suspended) {
                        searchBarKt$DockedSearchBar$3 = this;
                        FocusManager.CC.clearFocus$default(searchBarKt$DockedSearchBar$3.$focusManager, false, 1, null);
                    } else {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                searchBarKt$DockedSearchBar$3 = this;
                ResultKt.throwOnFailure($result);
                FocusManager.CC.clearFocus$default(searchBarKt$DockedSearchBar$3.$focusManager, false, 1, null);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
