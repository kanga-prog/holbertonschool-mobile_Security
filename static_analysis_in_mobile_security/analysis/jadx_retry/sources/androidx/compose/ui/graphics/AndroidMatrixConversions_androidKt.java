package androidx.compose.ui.graphics;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidMatrixConversions.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\t"}, d2 = {"setFrom", "", "Landroid/graphics/Matrix;", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "setFrom-EL8BTi8", "(Landroid/graphics/Matrix;[F)V", "setFrom-tU-YjHk", "([FLandroid/graphics/Matrix;)V", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidMatrixConversions_androidKt {
    /* JADX INFO: renamed from: setFrom-tU-YjHk, reason: not valid java name */
    public static final void m2844setFromtUYjHk(float[] setFrom, android.graphics.Matrix matrix) {
        Intrinsics.checkNotNullParameter(setFrom, "$this$setFrom");
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        matrix.getValues(setFrom);
        float scaleX = setFrom[0];
        float skewX = setFrom[1];
        float translateX = setFrom[2];
        float skewY = setFrom[3];
        float scaleY = setFrom[4];
        float translateY = setFrom[5];
        float persp0 = setFrom[6];
        float persp1 = setFrom[7];
        float persp2 = setFrom[8];
        setFrom[0] = scaleX;
        setFrom[1] = skewY;
        setFrom[2] = 0.0f;
        setFrom[3] = persp0;
        setFrom[4] = skewX;
        setFrom[5] = scaleY;
        setFrom[6] = 0.0f;
        setFrom[7] = persp1;
        setFrom[8] = 0.0f;
        setFrom[9] = 0.0f;
        setFrom[10] = 1.0f;
        setFrom[11] = 0.0f;
        setFrom[12] = translateX;
        setFrom[13] = translateY;
        setFrom[14] = 0.0f;
        setFrom[15] = persp2;
    }

    /* JADX WARN: Code duplicated, block: B:38:0x008d  */
    /* JADX INFO: renamed from: setFrom-EL8BTi8, reason: not valid java name */
    public static final void m2843setFromEL8BTi8(android.graphics.Matrix setFrom, float[] matrix) {
        boolean z;
        Intrinsics.checkNotNullParameter(setFrom, "$this$setFrom");
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        if (matrix[(0 * 4) + 2] == 0.0f) {
            if (matrix[(1 * 4) + 2] == 0.0f) {
                if (matrix[(2 * 4) + 2] == 1.0f) {
                    if (matrix[(3 * 4) + 2] == 0.0f) {
                        if (matrix[(2 * 4) + 0] == 0.0f) {
                            if (matrix[(2 * 4) + 1] == 0.0f) {
                                if (matrix[(2 * 4) + 3] == 0.0f) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                            } else {
                                z = false;
                            }
                        } else {
                            z = false;
                        }
                    } else {
                        z = false;
                    }
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException("Android does not support arbitrary transforms".toString());
        }
        float scaleX = matrix[0];
        float skewY = matrix[1];
        float v2 = matrix[2];
        float persp0 = matrix[3];
        float skewX = matrix[4];
        float scaleY = matrix[5];
        float v6 = matrix[6];
        float persp1 = matrix[7];
        float v8 = matrix[8];
        float translateX = matrix[12];
        float translateY = matrix[13];
        float persp2 = matrix[15];
        matrix[0] = scaleX;
        matrix[1] = skewX;
        matrix[2] = translateX;
        matrix[3] = skewY;
        matrix[4] = scaleY;
        matrix[5] = translateY;
        matrix[6] = persp0;
        matrix[7] = persp1;
        matrix[8] = persp2;
        setFrom.setValues(matrix);
        matrix[0] = scaleX;
        matrix[1] = skewY;
        matrix[2] = v2;
        matrix[3] = persp0;
        matrix[4] = skewX;
        matrix[5] = scaleY;
        matrix[6] = v6;
        matrix[7] = persp1;
        matrix[8] = v8;
    }
}
