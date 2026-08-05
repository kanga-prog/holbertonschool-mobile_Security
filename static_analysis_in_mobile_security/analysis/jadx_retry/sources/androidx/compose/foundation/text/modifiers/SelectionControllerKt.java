package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.LongPressTextDragObserverKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextPointerIcon_androidKt;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.foundation.text.selection.SelectionRegistrarKt;
import androidx.compose.foundation.text.selection.TextSelectionMouseDetectorKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SelectionController.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a<\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a+\u0010\f\u001a\u00020\u000b*\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"makeSelectionModifier", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "selectableId", "", "layoutCoordinates", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "isInTouchMode", "", "outOfBoundary", "start", "Landroidx/compose/ui/geometry/Offset;", "end", "outOfBoundary-2x9bVx0", "(Landroidx/compose/ui/text/TextLayoutResult;JJ)Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionControllerKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeSelectionModifier$mouseSelectionObserver$1, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v0, types: [androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeSelectionModifier$longPressDragObserver$1, java.lang.Object] */
    public static final Modifier makeSelectionModifier(final SelectionRegistrar $this$makeSelectionModifier, final long selectableId, final Function0<? extends LayoutCoordinates> function0, final Function0<TextLayoutResult> function1, boolean isInTouchMode) {
        if (isInTouchMode) {
            ?? r7 = new TextDragObserver() { // from class: androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeSelectionModifier$longPressDragObserver$1
                private long lastPosition = Offset.INSTANCE.m2747getZeroF1C5BW0();
                private long dragTotalDistance = Offset.INSTANCE.m2747getZeroF1C5BW0();

                public final long getLastPosition() {
                    return this.lastPosition;
                }

                public final void setLastPosition(long j) {
                    this.lastPosition = j;
                }

                public final long getDragTotalDistance() {
                    return this.dragTotalDistance;
                }

                public final void setDragTotalDistance(long j) {
                    this.dragTotalDistance = j;
                }

                @Override // androidx.compose.foundation.text.TextDragObserver
                /* JADX INFO: renamed from: onDown-k-4lQ0M */
                public void mo816onDownk4lQ0M(long point) {
                }

                @Override // androidx.compose.foundation.text.TextDragObserver
                public void onUp() {
                }

                @Override // androidx.compose.foundation.text.TextDragObserver
                /* JADX INFO: renamed from: onStart-k-4lQ0M */
                public void mo818onStartk4lQ0M(long startPoint) {
                    LayoutCoordinates it = function0.invoke();
                    if (it != null) {
                        Function0<TextLayoutResult> function2 = function1;
                        SelectionRegistrar selectionRegistrar = $this$makeSelectionModifier;
                        long j = selectableId;
                        if (!it.isAttached()) {
                            return;
                        }
                        if (SelectionControllerKt.m876outOfBoundary2x9bVx0(function2.invoke(), startPoint, startPoint)) {
                            selectionRegistrar.notifySelectionUpdateSelectAll(j);
                        } else {
                            selectionRegistrar.mo950notifySelectionUpdateStartd4ec7I(it, startPoint, SelectionAdjustment.INSTANCE.getWord());
                        }
                        this.lastPosition = startPoint;
                    }
                    if (SelectionRegistrarKt.hasSelection($this$makeSelectionModifier, selectableId)) {
                        this.dragTotalDistance = Offset.INSTANCE.m2747getZeroF1C5BW0();
                    }
                }

                @Override // androidx.compose.foundation.text.TextDragObserver
                /* JADX INFO: renamed from: onDrag-k-4lQ0M */
                public void mo817onDragk4lQ0M(long delta) {
                    LayoutCoordinates it = function0.invoke();
                    if (it != null) {
                        SelectionRegistrar selectionRegistrar = $this$makeSelectionModifier;
                        long j = selectableId;
                        Function0<TextLayoutResult> function2 = function1;
                        if (it.isAttached() && SelectionRegistrarKt.hasSelection(selectionRegistrar, j)) {
                            long jM2736plusMKHz9U = Offset.m2736plusMKHz9U(this.dragTotalDistance, delta);
                            this.dragTotalDistance = jM2736plusMKHz9U;
                            long newPosition = Offset.m2736plusMKHz9U(this.lastPosition, jM2736plusMKHz9U);
                            if (!SelectionControllerKt.m876outOfBoundary2x9bVx0(function2.invoke(), this.lastPosition, newPosition)) {
                                boolean consumed = selectionRegistrar.mo949notifySelectionUpdate5iVPX68(it, newPosition, this.lastPosition, false, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate());
                                if (consumed) {
                                    this.lastPosition = newPosition;
                                    this.dragTotalDistance = Offset.INSTANCE.m2747getZeroF1C5BW0();
                                }
                            }
                        }
                    }
                }

                @Override // androidx.compose.foundation.text.TextDragObserver
                public void onStop() {
                    if (SelectionRegistrarKt.hasSelection($this$makeSelectionModifier, selectableId)) {
                        $this$makeSelectionModifier.notifySelectionUpdateEnd();
                    }
                }

                @Override // androidx.compose.foundation.text.TextDragObserver
                public void onCancel() {
                    if (SelectionRegistrarKt.hasSelection($this$makeSelectionModifier, selectableId)) {
                        $this$makeSelectionModifier.notifySelectionUpdateEnd();
                    }
                }
            };
            return SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, (Object) r7, new AnonymousClass1(r7, null));
        }
        ?? r1 = new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeSelectionModifier$mouseSelectionObserver$1
            private long lastPosition = Offset.INSTANCE.m2747getZeroF1C5BW0();

            public final long getLastPosition() {
                return this.lastPosition;
            }

            public final void setLastPosition(long j) {
                this.lastPosition = j;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtend-k-4lQ0M, reason: not valid java name */
            public boolean mo878onExtendk4lQ0M(long downPosition) {
                LayoutCoordinates layoutCoordinates = function0.invoke();
                if (layoutCoordinates == null) {
                    return false;
                }
                SelectionRegistrar selectionRegistrar = $this$makeSelectionModifier;
                long j = selectableId;
                if (!layoutCoordinates.isAttached()) {
                    return false;
                }
                boolean consumed = selectionRegistrar.mo949notifySelectionUpdate5iVPX68(layoutCoordinates, downPosition, this.lastPosition, false, SelectionAdjustment.INSTANCE.getNone());
                if (consumed) {
                    this.lastPosition = downPosition;
                }
                return SelectionRegistrarKt.hasSelection(selectionRegistrar, j);
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M, reason: not valid java name */
            public boolean mo879onExtendDragk4lQ0M(long dragPosition) {
                LayoutCoordinates layoutCoordinates = function0.invoke();
                if (layoutCoordinates != null) {
                    SelectionRegistrar selectionRegistrar = $this$makeSelectionModifier;
                    long j = selectableId;
                    if (!layoutCoordinates.isAttached() || !SelectionRegistrarKt.hasSelection(selectionRegistrar, j)) {
                        return false;
                    }
                    boolean consumed = selectionRegistrar.mo949notifySelectionUpdate5iVPX68(layoutCoordinates, dragPosition, this.lastPosition, false, SelectionAdjustment.INSTANCE.getNone());
                    if (consumed) {
                        this.lastPosition = dragPosition;
                        return true;
                    }
                    return true;
                }
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onStart-3MmeM6k, reason: not valid java name */
            public boolean mo880onStart3MmeM6k(long downPosition, SelectionAdjustment adjustment) {
                Intrinsics.checkNotNullParameter(adjustment, "adjustment");
                LayoutCoordinates it = function0.invoke();
                if (it == null) {
                    return false;
                }
                SelectionRegistrar selectionRegistrar = $this$makeSelectionModifier;
                long j = selectableId;
                if (!it.isAttached()) {
                    return false;
                }
                selectionRegistrar.mo950notifySelectionUpdateStartd4ec7I(it, downPosition, adjustment);
                this.lastPosition = downPosition;
                return SelectionRegistrarKt.hasSelection(selectionRegistrar, j);
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onDrag-3MmeM6k, reason: not valid java name */
            public boolean mo877onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment) {
                Intrinsics.checkNotNullParameter(adjustment, "adjustment");
                LayoutCoordinates it = function0.invoke();
                if (it != null) {
                    SelectionRegistrar selectionRegistrar = $this$makeSelectionModifier;
                    long j = selectableId;
                    if (!it.isAttached() || !SelectionRegistrarKt.hasSelection(selectionRegistrar, j)) {
                        return false;
                    }
                    boolean consumed = selectionRegistrar.mo949notifySelectionUpdate5iVPX68(it, dragPosition, this.lastPosition, false, adjustment);
                    if (consumed) {
                        this.lastPosition = dragPosition;
                        return true;
                    }
                    return true;
                }
                return true;
            }
        };
        return PointerIconKt.pointerHoverIcon$default(SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, (Object) r1, new AnonymousClass2(r1, null)), TextPointerIcon_androidKt.getTextPointerIcon(), false, 2, null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeSelectionModifier$1, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionController.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeSelectionModifier$1", f = "SelectionController.kt", i = {}, l = {256}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SelectionControllerKt$makeSelectionModifier$longPressDragObserver$1 $longPressDragObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SelectionControllerKt$makeSelectionModifier$longPressDragObserver$1 selectionControllerKt$makeSelectionModifier$longPressDragObserver$1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$longPressDragObserver = selectionControllerKt$makeSelectionModifier$longPressDragObserver$1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$longPressDragObserver, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (LongPressTextDragObserverKt.detectDragGesturesAfterLongPressWithObserver((PointerInputScope) this.L$0, this.$longPressDragObserver, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeSelectionModifier$2, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionController.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeSelectionModifier$2", f = "SelectionController.kt", i = {}, l = {345}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SelectionControllerKt$makeSelectionModifier$mouseSelectionObserver$1 $mouseSelectionObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(SelectionControllerKt$makeSelectionModifier$mouseSelectionObserver$1 selectionControllerKt$makeSelectionModifier$mouseSelectionObserver$1, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$mouseSelectionObserver = selectionControllerKt$makeSelectionModifier$mouseSelectionObserver$1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$mouseSelectionObserver, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                    this.label = 1;
                    if (TextSelectionMouseDetectorKt.mouseSelectionDetector($this$pointerInput, this.$mouseSelectionObserver, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: outOfBoundary-2x9bVx0, reason: not valid java name */
    public static final boolean m876outOfBoundary2x9bVx0(TextLayoutResult $this$outOfBoundary_u2d2x9bVx0, long start, long end) {
        if ($this$outOfBoundary_u2d2x9bVx0 == null) {
            return false;
        }
        int lastOffset = $this$outOfBoundary_u2d2x9bVx0.getLayoutInput().getText().getText().length();
        int rawStartOffset = $this$outOfBoundary_u2d2x9bVx0.m4740getOffsetForPositionk4lQ0M(start);
        int rawEndOffset = $this$outOfBoundary_u2d2x9bVx0.m4740getOffsetForPositionk4lQ0M(end);
        return (rawStartOffset >= lastOffset + (-1) && rawEndOffset >= lastOffset + (-1)) || (rawStartOffset < 0 && rawEndOffset < 0);
    }
}
