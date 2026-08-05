package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: EditingBuffer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", "target", "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditingBufferKt {
    /* JADX INFO: renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m4914updateRangeAfterDeletepWDy79M(long target, long deleted) {
        int targetMin = TextRange.m4766getMinimpl(target);
        int targetMax = TextRange.m4765getMaximpl(target);
        if (TextRange.m4770intersects5zctL8(deleted, target)) {
            if (TextRange.m4758contains5zctL8(deleted, target)) {
                targetMin = TextRange.m4766getMinimpl(deleted);
                targetMax = targetMin;
            } else if (TextRange.m4758contains5zctL8(target, deleted)) {
                targetMax -= TextRange.m4764getLengthimpl(deleted);
            } else if (TextRange.m4759containsimpl(deleted, targetMin)) {
                targetMin = TextRange.m4766getMinimpl(deleted);
                targetMax -= TextRange.m4764getLengthimpl(deleted);
            } else {
                targetMax = TextRange.m4766getMinimpl(deleted);
            }
        } else if (targetMax > TextRange.m4766getMinimpl(deleted)) {
            targetMin -= TextRange.m4764getLengthimpl(deleted);
            targetMax -= TextRange.m4764getLengthimpl(deleted);
        }
        return TextRangeKt.TextRange(targetMin, targetMax);
    }
}
