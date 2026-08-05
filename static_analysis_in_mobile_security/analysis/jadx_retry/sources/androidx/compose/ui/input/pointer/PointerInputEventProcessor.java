package androidx.compose.ui.input.pointer;

import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.LayoutNode;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PointerInputEventProcessor.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\nø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputEventProcessor;", "", "root", "Landroidx/compose/ui/node/LayoutNode;", "(Landroidx/compose/ui/node/LayoutNode;)V", "hitPathTracker", "Landroidx/compose/ui/input/pointer/HitPathTracker;", "hitResult", "Landroidx/compose/ui/node/HitTestResult;", "isProcessing", "", "pointerInputChangeEventProducer", "Landroidx/compose/ui/input/pointer/PointerInputChangeEventProducer;", "getRoot", "()Landroidx/compose/ui/node/LayoutNode;", "process", "Landroidx/compose/ui/input/pointer/ProcessResult;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerInputEvent;", "positionCalculator", "Landroidx/compose/ui/input/pointer/PositionCalculator;", "isInBounds", "process-BIzXfog", "(Landroidx/compose/ui/input/pointer/PointerInputEvent;Landroidx/compose/ui/input/pointer/PositionCalculator;Z)I", "processCancel", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PointerInputEventProcessor {
    private final HitPathTracker hitPathTracker;
    private final HitTestResult hitResult;
    private boolean isProcessing;
    private final PointerInputChangeEventProducer pointerInputChangeEventProducer;
    private final LayoutNode root;

    public PointerInputEventProcessor(LayoutNode root) {
        Intrinsics.checkNotNullParameter(root, "root");
        this.root = root;
        this.hitPathTracker = new HitPathTracker(root.getCoordinates());
        this.pointerInputChangeEventProducer = new PointerInputChangeEventProducer();
        this.hitResult = new HitTestResult();
    }

    public final LayoutNode getRoot() {
        return this.root;
    }

    /* JADX INFO: renamed from: process-BIzXfog$default, reason: not valid java name */
    public static /* synthetic */ int m4124processBIzXfog$default(PointerInputEventProcessor pointerInputEventProcessor, PointerInputEvent pointerInputEvent, PositionCalculator positionCalculator, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return pointerInputEventProcessor.m4125processBIzXfog(pointerInputEvent, positionCalculator, z);
    }

    /* JADX INFO: renamed from: process-BIzXfog, reason: not valid java name */
    public final int m4125processBIzXfog(PointerInputEvent pointerEvent, PositionCalculator positionCalculator, boolean isInBounds) {
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(pointerEvent, "pointerEvent");
        Intrinsics.checkNotNullParameter(positionCalculator, "positionCalculator");
        if (this.isProcessing) {
            return PointerInputEventProcessorKt.ProcessResult(false, false);
        }
        boolean z3 = true;
        try {
            this.isProcessing = true;
            InternalPointerEvent internalPointerEvent = this.pointerInputChangeEventProducer.produce(pointerEvent, positionCalculator);
            Iterable $this$any$iv = internalPointerEvent.getChanges().values();
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        PointerInputChange it2 = (PointerInputChange) element$iv;
                        if (it2.getPressed() || it2.getPreviousPressed()) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
            } else {
                z = false;
            }
            boolean isHover = !z;
            Iterable $this$forEach$iv = internalPointerEvent.getChanges().values();
            for (Object element$iv2 : $this$forEach$iv) {
                PointerInputChange pointerInputChange = (PointerInputChange) element$iv2;
                if (isHover || PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange)) {
                    boolean isTouchEvent = PointerType.m4178equalsimpl0(pointerInputChange.getType(), PointerType.INSTANCE.m4185getTouchT8wyACA());
                    this.root.m4368hitTestM_7yMNQ$ui_release(pointerInputChange.getPosition(), this.hitResult, (8 & 4) != 0 ? false : isTouchEvent, (8 & 8) != 0);
                    if (this.hitResult.isEmpty() ^ z3) {
                        this.hitPathTracker.m4035addHitPathKNwqfcY(pointerInputChange.getId(), this.hitResult);
                        this.hitResult.clear();
                    }
                }
                z3 = true;
            }
            this.hitPathTracker.removeDetachedPointerInputFilters();
            try {
                boolean dispatchedToSomething = this.hitPathTracker.dispatchChanges(internalPointerEvent, isInBounds);
                if (internalPointerEvent.getSuppressMovementConsumption()) {
                    z2 = false;
                } else {
                    Iterable $this$any$iv2 = internalPointerEvent.getChanges().values();
                    if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
                        Iterator it3 = $this$any$iv2.iterator();
                        while (true) {
                            if (it3.hasNext()) {
                                Object element$iv3 = it3.next();
                                PointerInputChange it4 = (PointerInputChange) element$iv3;
                                if (PointerEventKt.positionChangedIgnoreConsumed(it4) && it4.isConsumed()) {
                                    z2 = true;
                                    break;
                                }
                            } else {
                                z2 = false;
                                break;
                            }
                        }
                    } else {
                        z2 = false;
                    }
                }
                boolean anyMovementConsumed = z2;
                int iProcessResult = PointerInputEventProcessorKt.ProcessResult(dispatchedToSomething, anyMovementConsumed);
                this.isProcessing = false;
                return iProcessResult;
            } catch (Throwable th) {
                th = th;
                this.isProcessing = false;
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final void processCancel() {
        if (!this.isProcessing) {
            this.pointerInputChangeEventProducer.clear();
            this.hitPathTracker.processCancel();
        }
    }
}
