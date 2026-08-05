package androidx.compose.ui.text.input;

import android.graphics.Matrix;
import android.view.inputmethod.CursorAnchorInfo;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CursorAnchorInfoBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u001c\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\f"}, d2 = {"build", "Landroid/view/inputmethod/CursorAnchorInfo;", "Landroid/view/inputmethod/CursorAnchorInfo$Builder;", "textFieldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "matrix", "Landroid/graphics/Matrix;", "setInsertionMarker", "selectionStart", "", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CursorAnchorInfoBuilderKt {
    public static final CursorAnchorInfo build(CursorAnchorInfo.Builder $this$build, TextFieldValue textFieldValue, TextLayoutResult textLayoutResult, Matrix matrix) {
        Intrinsics.checkNotNullParameter($this$build, "<this>");
        Intrinsics.checkNotNullParameter(textFieldValue, "textFieldValue");
        Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        $this$build.reset();
        $this$build.setMatrix(matrix);
        int selectionStart = TextRange.m4766getMinimpl(textFieldValue.getSelection());
        int selectionEnd = TextRange.m4765getMaximpl(textFieldValue.getSelection());
        $this$build.setSelectionRange(selectionStart, selectionEnd);
        setInsertionMarker($this$build, selectionStart, textLayoutResult);
        TextRange composition = textFieldValue.getComposition();
        int compositionStart = composition != null ? TextRange.m4766getMinimpl(composition.getPackedValue()) : -1;
        TextRange composition2 = textFieldValue.getComposition();
        int compositionEnd = composition2 != null ? TextRange.m4765getMaximpl(composition2.getPackedValue()) : -1;
        boolean z = false;
        if (compositionStart >= 0 && compositionStart < compositionEnd) {
            z = true;
        }
        if (z) {
            $this$build.setComposingText(compositionStart, textFieldValue.getText().subSequence(compositionStart, compositionEnd));
        }
        CursorAnchorInfo cursorAnchorInfoBuild = $this$build.build();
        Intrinsics.checkNotNullExpressionValue(cursorAnchorInfoBuild, "build()");
        return cursorAnchorInfoBuild;
    }

    private static final CursorAnchorInfo.Builder setInsertionMarker(CursorAnchorInfo.Builder $this$setInsertionMarker, int selectionStart, TextLayoutResult textLayoutResult) {
        if (selectionStart < 0) {
            return $this$setInsertionMarker;
        }
        Rect cursorRect = textLayoutResult.getCursorRect(selectionStart);
        boolean isRtl = textLayoutResult.getBidiRunDirection(selectionStart) == ResolvedTextDirection.Rtl;
        int flags = isRtl ? 0 | 4 : 0;
        $this$setInsertionMarker.setInsertionMarkerLocation(cursorRect.getLeft(), cursorRect.getTop(), cursorRect.getBottom(), cursorRect.getBottom(), flags);
        return $this$setInsertionMarker;
    }
}
