package androidx.compose.ui.text.input;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlatformTextInputAdapter.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"dispose", "", "Landroidx/compose/ui/text/input/PlatformTextInputAdapter;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PlatformTextInputAdapter_androidKt {
    public static final void dispose(PlatformTextInputAdapter $this$dispose) {
        Intrinsics.checkNotNullParameter($this$dispose, "<this>");
        $this$dispose.onDisposed();
    }
}
