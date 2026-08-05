package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: NestedScrollModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rJ!\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013ø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface NestedScrollConnection {
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    Object mo334onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation);

    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    long mo335onPostScrollDzOQY0M(long consumed, long available, int source);

    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    Object mo336onPreFlingQWom1Mo(long j, Continuation<? super Velocity> continuation);

    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    long mo337onPreScrollOzD1aCk(long available, int source);

    /* JADX INFO: renamed from: androidx.compose.ui.input.nestedscroll.NestedScrollConnection$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: NestedScrollModifier.kt */
    public final /* synthetic */ class CC {
        /* JADX INFO: renamed from: $default$onPreScroll-OzD1aCk, reason: not valid java name */
        public static long m3979$default$onPreScrollOzD1aCk(NestedScrollConnection _this, long available, int source) {
            return Offset.INSTANCE.m2747getZeroF1C5BW0();
        }

        /* JADX INFO: renamed from: $default$onPostScroll-DzOQY0M, reason: not valid java name */
        public static long m3977$default$onPostScrollDzOQY0M(NestedScrollConnection _this, long consumed, long available, int source) {
            return Offset.INSTANCE.m2747getZeroF1C5BW0();
        }

        /* JADX INFO: renamed from: onPreFling-QWom1Mo$suspendImpl, reason: not valid java name */
        public static /* synthetic */ Object m3985onPreFlingQWom1Mo$suspendImpl(NestedScrollConnection $this, long available, Continuation<? super Velocity> continuation) {
            return Velocity.m5490boximpl(Velocity.INSTANCE.m5510getZero9UxMQ8M());
        }

        /* JADX INFO: renamed from: onPostFling-RZ2iAVY$suspendImpl, reason: not valid java name */
        public static /* synthetic */ Object m3984onPostFlingRZ2iAVY$suspendImpl(NestedScrollConnection $this, long consumed, long available, Continuation<? super Velocity> continuation) {
            return Velocity.m5490boximpl(Velocity.INSTANCE.m5510getZero9UxMQ8M());
        }
    }

    /* JADX INFO: compiled from: NestedScrollModifier.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: onPreScroll-OzD1aCk, reason: not valid java name */
        public static long m3989onPreScrollOzD1aCk(NestedScrollConnection $this, long available, int source) {
            return CC.m3979$default$onPreScrollOzD1aCk($this, available, source);
        }

        @Deprecated
        /* JADX INFO: renamed from: onPostScroll-DzOQY0M, reason: not valid java name */
        public static long m3987onPostScrollDzOQY0M(NestedScrollConnection $this, long consumed, long available, int source) {
            return CC.m3977$default$onPostScrollDzOQY0M($this, consumed, available, source);
        }

        @Deprecated
        /* JADX INFO: renamed from: onPreFling-QWom1Mo, reason: not valid java name */
        public static Object m3988onPreFlingQWom1Mo(NestedScrollConnection $this, long available, Continuation<? super Velocity> continuation) {
            return CC.m3985onPreFlingQWom1Mo$suspendImpl($this, available, continuation);
        }

        @Deprecated
        /* JADX INFO: renamed from: onPostFling-RZ2iAVY, reason: not valid java name */
        public static Object m3986onPostFlingRZ2iAVY(NestedScrollConnection $this, long consumed, long available, Continuation<? super Velocity> continuation) {
            return CC.m3984onPostFlingRZ2iAVY$suspendImpl($this, consumed, available, continuation);
        }
    }
}
