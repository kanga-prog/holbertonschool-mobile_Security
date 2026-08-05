package androidx.compose.ui.focus;

import android.view.KeyEvent;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.key.SoftKeyboardInterceptionModifierNode;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: FocusOwnerImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0018\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\u001d\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b \u0010!J\u001d\u0010\"\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010!J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020&H\u0016J\n\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u001d\u0010)\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020+H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-J\b\u0010.\u001a\u00020\u0005H\u0016J\u0010\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u000201H\u0016J\u0010\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u000202H\u0016J\u0010\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0014H\u0016J\b\u00103\u001a\u00020\u0005H\u0016J\u001d\u00104\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020+H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b5\u0010-J\u000e\u00106\u001a\u0004\u0018\u000107*\u000208H\u0002J\\\u00109\u001a\u00020\u0005\"\n\b\u0000\u0010:\u0018\u0001*\u000208*\u0002082\f\u0010;\u001a\b\u0012\u0004\u0012\u0002H:0<2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u0002H:\u0012\u0004\u0012\u00020\u00050\u00032\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u0002H:\u0012\u0004\u0012\u00020\u00050\u0003H\u0082\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b?\u0010@R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006A"}, d2 = {"Landroidx/compose/ui/focus/FocusOwnerImpl;", "Landroidx/compose/ui/focus/FocusOwner;", "onRequestApplyChangesListener", "Lkotlin/Function1;", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function1;)V", "focusInvalidationManager", "Landroidx/compose/ui/focus/FocusInvalidationManager;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "rootFocusNode", "Landroidx/compose/ui/focus/FocusTargetNode;", "getRootFocusNode$ui_release", "()Landroidx/compose/ui/focus/FocusTargetNode;", "setRootFocusNode$ui_release", "(Landroidx/compose/ui/focus/FocusTargetNode;)V", "clearFocus", "force", "", "refreshFocusEvents", "dispatchInterceptedSoftKeyboardEvent", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "dispatchInterceptedSoftKeyboardEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "dispatchKeyEvent", "dispatchKeyEvent-ZmokQxo", "dispatchRotaryEvent", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/rotary/RotaryScrollEvent;", "getFocusRect", "Landroidx/compose/ui/geometry/Rect;", "moveFocus", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "moveFocus-3ESFkO8", "(I)Z", "releaseFocus", "scheduleInvalidation", "node", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "takeFocus", "wrapAroundFocus", "wrapAroundFocus-3ESFkO8", "lastLocalKeyInputNode", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DelegatableNode;", "traverseAncestors", "T", "type", "Landroidx/compose/ui/node/NodeKind;", "onPreVisit", "onVisit", "traverseAncestors-Y-YKmho", "(Landroidx/compose/ui/node/DelegatableNode;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FocusOwnerImpl implements FocusOwner {
    private final FocusInvalidationManager focusInvalidationManager;
    public LayoutDirection layoutDirection;
    private final Modifier modifier;
    private FocusTargetNode rootFocusNode;

    /* JADX INFO: compiled from: FocusOwnerImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public FocusOwnerImpl(Function1<? super Function0<Unit>, Unit> onRequestApplyChangesListener) {
        Intrinsics.checkNotNullParameter(onRequestApplyChangesListener, "onRequestApplyChangesListener");
        this.rootFocusNode = new FocusTargetNode();
        this.focusInvalidationManager = new FocusInvalidationManager(onRequestApplyChangesListener);
        this.modifier = new ModifierNodeElement<FocusTargetNode>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$modifier$1
            @Override // androidx.compose.ui.node.ModifierNodeElement
            public FocusTargetNode create() {
                return this.this$0.getRootFocusNode();
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public void update(FocusTargetNode node) {
                Intrinsics.checkNotNullParameter(node, "node");
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
                Intrinsics.checkNotNullParameter($this$inspectableProperties, "<this>");
                $this$inspectableProperties.setName("RootFocusTarget");
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public int hashCode() {
                return this.this$0.getRootFocusNode().hashCode();
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public boolean equals(Object other) {
                return other == this;
            }
        };
    }

    /* JADX INFO: renamed from: getRootFocusNode$ui_release, reason: from getter */
    public final FocusTargetNode getRootFocusNode() {
        return this.rootFocusNode;
    }

    public final void setRootFocusNode$ui_release(FocusTargetNode focusTargetNode) {
        Intrinsics.checkNotNullParameter(focusTargetNode, "<set-?>");
        this.rootFocusNode = focusTargetNode;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public Modifier getModifier() {
        return this.modifier;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public LayoutDirection getLayoutDirection() {
        LayoutDirection layoutDirection = this.layoutDirection;
        if (layoutDirection != null) {
            return layoutDirection;
        }
        Intrinsics.throwUninitializedPropertyAccessException("layoutDirection");
        return null;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void setLayoutDirection(LayoutDirection layoutDirection) {
        Intrinsics.checkNotNullParameter(layoutDirection, "<set-?>");
        this.layoutDirection = layoutDirection;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void takeFocus() {
        if (this.rootFocusNode.getFocusState() == FocusStateImpl.Inactive) {
            this.rootFocusNode.setFocusState(FocusStateImpl.Active);
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void releaseFocus() {
        FocusTransactionsKt.clearFocus(this.rootFocusNode, true, true);
    }

    @Override // androidx.compose.ui.focus.FocusManager
    public void clearFocus(boolean force) {
        clearFocus(force, true);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void clearFocus(boolean force, boolean refreshFocusEvents) {
        FocusStateImpl focusStateImpl;
        if (!force) {
            switch (WhenMappings.$EnumSwitchMapping$0[FocusTransactionsKt.m2679performCustomClearFocusMxy_nc0(this.rootFocusNode, FocusDirection.INSTANCE.m2660getExitdhqQ8s()).ordinal()]) {
                case 1:
                case 2:
                case 3:
                    return;
            }
        }
        FocusStateImpl rootInitialState = this.rootFocusNode.getFocusState();
        if (FocusTransactionsKt.clearFocus(this.rootFocusNode, force, refreshFocusEvents)) {
            FocusTargetNode focusTargetNode = this.rootFocusNode;
            switch (WhenMappings.$EnumSwitchMapping$1[rootInitialState.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    focusStateImpl = FocusStateImpl.Active;
                    break;
                case 4:
                    focusStateImpl = FocusStateImpl.Inactive;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            focusTargetNode.setFocusState(focusStateImpl);
        }
    }

    @Override // androidx.compose.ui.focus.FocusManager
    /* JADX INFO: renamed from: moveFocus-3ESFkO8 */
    public boolean mo2668moveFocus3ESFkO8(final int focusDirection) {
        final FocusTargetNode source = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        if (source == null) {
            return false;
        }
        FocusRequester it = FocusTraversalKt.m2683customFocusSearchOMvw8(source, focusDirection, getLayoutDirection());
        if (it != FocusRequester.INSTANCE.getDefault()) {
            return it != FocusRequester.INSTANCE.getCancel() && it.focus$ui_release();
        }
        final Ref.BooleanRef isCancelled = new Ref.BooleanRef();
        boolean foundNextItem = FocusTraversalKt.m2684focusSearchsMXa3k8(this.rootFocusNode, focusDirection, getLayoutDirection(), new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$moveFocus$foundNextItem$1

            /* JADX INFO: compiled from: FocusOwnerImpl.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[CustomDestinationResult.values().length];
                    try {
                        iArr[CustomDestinationResult.Redirected.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[CustomDestinationResult.Cancelled.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    try {
                        iArr[CustomDestinationResult.None.ordinal()] = 4;
                    } catch (NoSuchFieldError e4) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode destination) {
                Modifier.Node nodePop;
                boolean zPerformRequestFocus;
                DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
                int type$iv;
                NodeChain nodes;
                DelegatableNode $this$nearestAncestor_u2d64DMado$iv2;
                int type$iv2;
                Intrinsics.checkNotNullParameter(destination, "destination");
                if (Intrinsics.areEqual(destination, source)) {
                    return false;
                }
                FocusTargetNode $this$nearestAncestor_u2d64DMado$iv3 = destination;
                int type$iv3 = NodeKind.m4443constructorimpl(1024);
                if (!$this$nearestAncestor_u2d64DMado$iv3.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                }
                Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv3.getNode().getParent();
                LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv3);
                loop0: while (true) {
                    int i = 1;
                    if (layout$iv$iv$iv == null) {
                        nodePop = null;
                        break;
                    }
                    Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                    if ((head$iv$iv$iv.getAggregateChildKindSet() & type$iv3) != 0) {
                        while (node$iv$iv$iv != null) {
                            if ((node$iv$iv$iv.getKindSet() & type$iv3) != 0) {
                                Modifier.Node it$iv$iv = node$iv$iv$iv;
                                MutableVector mutableVector = null;
                                nodePop = it$iv$iv;
                                while (nodePop != null) {
                                    if (nodePop instanceof FocusTargetNode) {
                                        break loop0;
                                    }
                                    Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                    if (((this_$iv$iv$iv$iv.getKindSet() & type$iv3) != 0) && (nodePop instanceof DelegatingNode)) {
                                        int count$iv$iv$iv = 0;
                                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                            if ((next$iv$iv$iv.getKindSet() & type$iv3) != 0) {
                                                count$iv$iv$iv++;
                                                if (count$iv$iv$iv == i) {
                                                    nodePop = next$iv$iv$iv;
                                                    $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                                    type$iv3 = type$iv3;
                                                } else {
                                                    MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                    Modifier.Node theNode$iv$iv$iv = nodePop;
                                                    if (theNode$iv$iv$iv != null) {
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv$iv$iv);
                                                        }
                                                        nodePop = null;
                                                    }
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(next$iv$iv$iv);
                                                    }
                                                    mutableVector = mutableVector2;
                                                    count$iv$iv$iv = count$iv$iv$iv;
                                                }
                                            } else {
                                                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                                type$iv3 = type$iv3;
                                            }
                                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                            type$iv3 = type$iv3;
                                            i = 1;
                                        }
                                        $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv3;
                                        type$iv2 = type$iv3;
                                        if (count$iv$iv$iv == 1) {
                                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                            type$iv3 = type$iv2;
                                            i = 1;
                                        } else {
                                            nodePop = DelegatableNodeKt.pop(mutableVector);
                                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                            type$iv3 = type$iv2;
                                            i = 1;
                                        }
                                    } else {
                                        $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv3;
                                        type$iv2 = type$iv3;
                                        nodePop = DelegatableNodeKt.pop(mutableVector);
                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                        type$iv3 = type$iv2;
                                        i = 1;
                                    }
                                }
                            }
                            node$iv$iv$iv = node$iv$iv$iv.getParent();
                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                            type$iv3 = type$iv3;
                            i = 1;
                        }
                        $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                        type$iv = type$iv3;
                    } else {
                        $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                        type$iv = type$iv3;
                    }
                    layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui_release();
                    node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes = layout$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
                    $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv;
                    type$iv3 = type$iv;
                }
                if (nodePop == null) {
                    throw new IllegalStateException("Focus search landed at the root.".toString());
                }
                switch (WhenMappings.$EnumSwitchMapping$0[FocusTransactionsKt.m2682performCustomRequestFocusMxy_nc0(destination, focusDirection).ordinal()]) {
                    case 1:
                        zPerformRequestFocus = true;
                        break;
                    case 2:
                    case 3:
                        isCancelled.element = true;
                        zPerformRequestFocus = true;
                        break;
                    case 4:
                        zPerformRequestFocus = FocusTransactionsKt.performRequestFocus(destination);
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                return Boolean.valueOf(zPerformRequestFocus);
            }
        });
        if (isCancelled.element) {
            return false;
        }
        return foundNextItem || m2672wrapAroundFocus3ESFkO8(focusDirection);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: dispatchKeyEvent-ZmokQxo */
    public boolean mo2670dispatchKeyEventZmokQxo(KeyEvent keyEvent) {
        DelegatableNode $this$traverseAncestors_u2dY_u2dYKmho$iv;
        int type$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
        int i;
        int size;
        Modifier.Node focusedKeyInputNode;
        FocusOwnerImpl this_$iv;
        int i2;
        NodeChain nodes;
        FocusOwnerImpl this_$iv2;
        int i3;
        FocusOwnerImpl this_$iv3;
        Object obj;
        FocusTargetNode activeFocusTarget;
        NodeChain nodes2;
        FocusTargetNode activeFocusTarget2;
        Intrinsics.checkNotNullParameter(keyEvent, "keyEvent");
        FocusTargetNode activeFocusTarget3 = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        if (activeFocusTarget3 == null) {
            throw new IllegalStateException("Event can't be processed because we do not have an active focus target.".toString());
        }
        Modifier.Node nodeLastLocalKeyInputNode = lastLocalKeyInputNode(activeFocusTarget3);
        int i4 = 1;
        if (nodeLastLocalKeyInputNode == null) {
            FocusTargetNode $this$nearestAncestor_u2d64DMado$iv = activeFocusTarget3;
            int iM4443constructorimpl = NodeKind.m4443constructorimpl(8192);
            if (!$this$nearestAncestor_u2d64DMado$iv.getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node".toString());
            }
            Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv.getNode().getParent();
            LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv);
            loop0: while (true) {
                if (layout$iv$iv$iv == null) {
                    obj = null;
                    break;
                }
                Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                if ((head$iv$iv$iv.getAggregateChildKindSet() & iM4443constructorimpl) != 0) {
                    while (node$iv$iv$iv != null) {
                        if ((node$iv$iv$iv.getKindSet() & iM4443constructorimpl) != 0) {
                            Modifier.Node it$iv$iv = node$iv$iv$iv;
                            MutableVector mutableVector = null;
                            Modifier.Node nodePop = it$iv$iv;
                            while (nodePop != null) {
                                if (nodePop instanceof KeyInputModifierNode) {
                                    obj = nodePop;
                                    break loop0;
                                }
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv$iv.getKindSet() & iM4443constructorimpl) != 0) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        if ((next$iv$iv$iv.getKindSet() & iM4443constructorimpl) != 0) {
                                            count$iv$iv$iv++;
                                            if (count$iv$iv$iv == i4) {
                                                nodePop = next$iv$iv$iv;
                                                activeFocusTarget3 = activeFocusTarget3;
                                            } else {
                                                MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                Modifier.Node theNode$iv$iv$iv = nodePop;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(next$iv$iv$iv);
                                                }
                                                mutableVector = mutableVector2;
                                            }
                                        } else {
                                            activeFocusTarget3 = activeFocusTarget3;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        activeFocusTarget3 = activeFocusTarget3;
                                        i4 = 1;
                                    }
                                    activeFocusTarget2 = activeFocusTarget3;
                                    if (count$iv$iv$iv == 1) {
                                        activeFocusTarget3 = activeFocusTarget2;
                                        i4 = 1;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector);
                                        activeFocusTarget3 = activeFocusTarget2;
                                        i4 = 1;
                                    }
                                } else {
                                    activeFocusTarget2 = activeFocusTarget3;
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    activeFocusTarget3 = activeFocusTarget2;
                                    i4 = 1;
                                }
                            }
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getParent();
                        i4 = 1;
                        activeFocusTarget3 = activeFocusTarget3;
                    }
                    activeFocusTarget = activeFocusTarget3;
                } else {
                    activeFocusTarget = activeFocusTarget3;
                }
                layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui_release();
                node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                i4 = 1;
                activeFocusTarget3 = activeFocusTarget;
            }
            KeyInputModifierNode keyInputModifierNode = (KeyInputModifierNode) obj;
            nodeLastLocalKeyInputNode = keyInputModifierNode != null ? keyInputModifierNode.getNode() : null;
        }
        Modifier.Node focusedKeyInputNode2 = nodeLastLocalKeyInputNode;
        if (focusedKeyInputNode2 == null) {
            return false;
        }
        Modifier.Node $this$traverseAncestors_u2dY_u2dYKmho$iv2 = focusedKeyInputNode2;
        int type$iv2 = NodeKind.m4443constructorimpl(8192);
        FocusOwnerImpl this_$iv4 = this;
        int i5 = 0;
        List list = null;
        if (!$this$traverseAncestors_u2dY_u2dYKmho$iv2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv$iv$iv$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv2.getNode().getParent();
        LayoutNode layout$iv$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$traverseAncestors_u2dY_u2dYKmho$iv2);
        while (layout$iv$iv$iv$iv != null) {
            Modifier.Node head$iv$iv$iv$iv = layout$iv$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                while (node$iv$iv$iv$iv2 != null) {
                    if ((node$iv$iv$iv$iv2.getKindSet() & type$iv2) != 0) {
                        Modifier.Node it$iv$iv$iv = node$iv$iv$iv$iv2;
                        MutableVector mutableVector3 = null;
                        Modifier.Node nodePop2 = it$iv$iv$iv;
                        while (nodePop2 != null) {
                            focusedKeyInputNode2 = focusedKeyInputNode2;
                            if (nodePop2 instanceof KeyInputModifierNode) {
                                Modifier.Node node = nodePop2;
                                if (list == null) {
                                    Object result$iv$iv = new ArrayList();
                                    list = (List) result$iv$iv;
                                }
                                list.add(node);
                                this_$iv2 = this_$iv4;
                                i3 = i5;
                            } else {
                                Modifier.Node this_$iv$iv$iv$iv$iv = nodePop2;
                                if (((this_$iv$iv$iv$iv$iv.getKindSet() & type$iv2) != 0) && (nodePop2 instanceof DelegatingNode)) {
                                    int count$iv$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop2;
                                    Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                        if ((next$iv$iv$iv$iv.getKindSet() & type$iv2) != 0) {
                                            count$iv$iv$iv$iv++;
                                            this_$iv3 = this_$iv4;
                                            if (count$iv$iv$iv$iv == 1) {
                                                nodePop2 = next$iv$iv$iv$iv;
                                                i5 = i5;
                                            } else {
                                                MutableVector mutableVector4 = mutableVector3 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector3;
                                                Modifier.Node theNode$iv$iv$iv$iv = nodePop2;
                                                if (theNode$iv$iv$iv$iv != null) {
                                                    if (mutableVector4 != null) {
                                                        mutableVector4.add(theNode$iv$iv$iv$iv);
                                                    }
                                                    nodePop2 = null;
                                                }
                                                if (mutableVector4 != null) {
                                                    mutableVector4.add(next$iv$iv$iv$iv);
                                                }
                                                mutableVector3 = mutableVector4;
                                                count$iv$iv$iv$iv = count$iv$iv$iv$iv;
                                            }
                                        } else {
                                            this_$iv3 = this_$iv4;
                                            i5 = i5;
                                        }
                                        node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                        this_$iv4 = this_$iv3;
                                        i5 = i5;
                                    }
                                    this_$iv2 = this_$iv4;
                                    i3 = i5;
                                    if (count$iv$iv$iv$iv == 1) {
                                        this_$iv4 = this_$iv2;
                                        i5 = i3;
                                    }
                                } else {
                                    this_$iv2 = this_$iv4;
                                    i3 = i5;
                                }
                            }
                            nodePop2 = DelegatableNodeKt.pop(mutableVector3);
                            this_$iv4 = this_$iv2;
                            i5 = i3;
                        }
                    }
                    node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getParent();
                    focusedKeyInputNode2 = focusedKeyInputNode2;
                    this_$iv4 = this_$iv4;
                    i5 = i5;
                }
                focusedKeyInputNode = focusedKeyInputNode2;
                this_$iv = this_$iv4;
                i2 = i5;
            } else {
                focusedKeyInputNode = focusedKeyInputNode2;
                this_$iv = this_$iv4;
                i2 = i5;
            }
            layout$iv$iv$iv$iv = layout$iv$iv$iv$iv.getParent$ui_release();
            node$iv$iv$iv$iv2 = (layout$iv$iv$iv$iv == null || (nodes = layout$iv$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            focusedKeyInputNode2 = focusedKeyInputNode;
            this_$iv4 = this_$iv;
            i5 = i2;
        }
        List ancestors$iv = list;
        if (ancestors$iv != null && (size = ancestors$iv.size() - 1) >= 0) {
            do {
                int index$iv$iv = size;
                size--;
                Object item$iv$iv = ancestors$iv.get(index$iv$iv);
                KeyInputModifierNode it = (KeyInputModifierNode) item$iv$iv;
                if (it.mo144onPreKeyEventZmokQxo(keyEvent)) {
                    return true;
                }
            } while (size >= 0);
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$traverseAncestors_u2dY_u2dYKmho$iv2.getNode();
        int i6 = 0;
        MutableVector mutableVector5 = null;
        Modifier.Node nodePop3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
        while (nodePop3 != null) {
            if (nodePop3 instanceof KeyInputModifierNode) {
                KeyInputModifierNode it2 = (KeyInputModifierNode) nodePop3;
                if (it2.mo144onPreKeyEventZmokQxo(keyEvent)) {
                    return true;
                }
                $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                i = i6;
            } else {
                Modifier.Node this_$iv$iv$iv = nodePop3;
                if (((this_$iv$iv$iv.getKindSet() & type$iv2) != 0) && (nodePop3 instanceof DelegatingNode)) {
                    int count$iv$iv = 0;
                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop3;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv2.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv = node$iv$iv$iv2;
                        if ((next$iv$iv.getKindSet() & type$iv2) != 0) {
                            count$iv$iv++;
                            if (count$iv$iv == 1) {
                                nodePop3 = next$iv$iv;
                                $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                                i6 = i6;
                            } else {
                                mutableVector5 = mutableVector5 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector5;
                                Modifier.Node theNode$iv$iv = nodePop3;
                                if (theNode$iv$iv != null) {
                                    if (mutableVector5 != null) {
                                        mutableVector5.add(theNode$iv$iv);
                                    }
                                    nodePop3 = null;
                                }
                                if (mutableVector5 != null) {
                                    mutableVector5.add(next$iv$iv);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                            i6 = i6;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                        i6 = i6;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                    i = i6;
                    MutableVector mutableVector6 = mutableVector5;
                    if (count$iv$iv == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                        i6 = i;
                        mutableVector5 = mutableVector6;
                    } else {
                        mutableVector5 = mutableVector6;
                    }
                } else {
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                    i = i6;
                }
            }
            nodePop3 = DelegatableNodeKt.pop(mutableVector5);
            $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
            i6 = i;
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$traverseAncestors_u2dY_u2dYKmho$iv2.getNode();
        MutableVector mutableVector7 = null;
        Modifier.Node nodePop4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
        while (nodePop4 != null) {
            if (nodePop4 instanceof KeyInputModifierNode) {
                KeyInputModifierNode it3 = (KeyInputModifierNode) nodePop4;
                if (it3.mo142onKeyEventZmokQxo(keyEvent)) {
                    return true;
                }
                $this$traverseAncestors_u2dY_u2dYKmho$iv = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                type$iv = type$iv2;
                $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
            } else {
                Modifier.Node this_$iv$iv$iv3 = nodePop4;
                if (((this_$iv$iv$iv3.getKindSet() & type$iv2) != 0) && (nodePop4 instanceof DelegatingNode)) {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop4;
                    Modifier.Node node$iv$iv$iv3 = this_$iv$iv$iv4.getDelegate();
                    while (node$iv$iv$iv3 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv3;
                        if ((next$iv$iv2.getKindSet() & type$iv2) != 0) {
                            count$iv$iv2++;
                            if (count$iv$iv2 == 1) {
                                nodePop4 = next$iv$iv2;
                                $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                                type$iv2 = type$iv2;
                                $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                            } else {
                                mutableVector7 = mutableVector7 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector7;
                                Modifier.Node theNode$iv$iv2 = nodePop4;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector7 != null) {
                                        mutableVector7.add(theNode$iv$iv2);
                                    }
                                    nodePop4 = null;
                                }
                                if (mutableVector7 != null) {
                                    mutableVector7.add(next$iv$iv2);
                                }
                            }
                        } else {
                            $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                            type$iv2 = type$iv2;
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                        }
                        node$iv$iv$iv3 = node$iv$iv$iv3.getChild();
                        $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                        type$iv2 = type$iv2;
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                    }
                    $this$traverseAncestors_u2dY_u2dYKmho$iv = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                    type$iv = type$iv2;
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                    if (count$iv$iv2 == 1) {
                        $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv;
                        type$iv2 = type$iv;
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                    }
                } else {
                    $this$traverseAncestors_u2dY_u2dYKmho$iv = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                    type$iv = type$iv2;
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                }
            }
            nodePop4 = DelegatableNodeKt.pop(mutableVector7);
            $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv;
            type$iv2 = type$iv;
            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        }
        if (ancestors$iv == null) {
            return false;
        }
        int size2 = ancestors$iv.size();
        for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
            Object item$iv$iv2 = ancestors$iv.get(index$iv$iv2);
            KeyInputModifierNode it4 = (KeyInputModifierNode) item$iv$iv2;
            if (it4.mo142onKeyEventZmokQxo(keyEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: dispatchInterceptedSoftKeyboardEvent-ZmokQxo */
    public boolean mo2669dispatchInterceptedSoftKeyboardEventZmokQxo(KeyEvent keyEvent) {
        SoftKeyboardInterceptionModifierNode softKeyboardInterceptionModifierNode;
        DelegatableNode $this$traverseAncestors_u2dY_u2dYKmho$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        int type$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
        int i;
        int size;
        SoftKeyboardInterceptionModifierNode focusedSoftKeyboardInterceptionNode;
        FocusOwnerImpl this_$iv;
        int i2;
        NodeChain nodes;
        FocusOwnerImpl this_$iv2;
        int i3;
        FocusOwnerImpl this_$iv3;
        DelegatableNode delegatableNode;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
        NodeChain nodes2;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv2;
        Intrinsics.checkNotNullParameter(keyEvent, "keyEvent");
        DelegatableNode delegatableNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        int i4 = 1;
        if (delegatableNodeFindActiveFocusNode != null) {
            DelegatableNode $this$nearestAncestor_u2d64DMado$iv3 = delegatableNodeFindActiveFocusNode;
            int iM4443constructorimpl = NodeKind.m4443constructorimpl(131072);
            if (!$this$nearestAncestor_u2d64DMado$iv3.getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node".toString());
            }
            Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv3.getNode().getParent();
            LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv3);
            loop0: while (true) {
                if (layout$iv$iv$iv == null) {
                    delegatableNode = null;
                    break;
                }
                Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                if ((head$iv$iv$iv.getAggregateChildKindSet() & iM4443constructorimpl) != 0) {
                    while (node$iv$iv$iv != null) {
                        if ((node$iv$iv$iv.getKindSet() & iM4443constructorimpl) != 0) {
                            Modifier.Node it$iv$iv = node$iv$iv$iv;
                            MutableVector mutableVector = null;
                            Modifier.Node nodePop = it$iv$iv;
                            while (nodePop != null) {
                                if (nodePop instanceof SoftKeyboardInterceptionModifierNode) {
                                    delegatableNode = nodePop;
                                    break loop0;
                                }
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv$iv.getKindSet() & iM4443constructorimpl) != 0) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        if ((next$iv$iv$iv.getKindSet() & iM4443constructorimpl) != 0) {
                                            count$iv$iv$iv++;
                                            if (count$iv$iv$iv == i4) {
                                                nodePop = next$iv$iv$iv;
                                                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                            } else {
                                                MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                Modifier.Node theNode$iv$iv$iv = nodePop;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(next$iv$iv$iv);
                                                }
                                                mutableVector = mutableVector2;
                                            }
                                        } else {
                                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                        i4 = 1;
                                    }
                                    $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv3;
                                    if (count$iv$iv$iv == 1) {
                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                        i4 = 1;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector);
                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                        i4 = 1;
                                    }
                                } else {
                                    $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv3;
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                    i4 = 1;
                                }
                            }
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getParent();
                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                        i4 = 1;
                    }
                    $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                } else {
                    $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                }
                layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui_release();
                node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv;
                i4 = 1;
            }
            softKeyboardInterceptionModifierNode = (SoftKeyboardInterceptionModifierNode) delegatableNode;
        } else {
            softKeyboardInterceptionModifierNode = null;
        }
        SoftKeyboardInterceptionModifierNode focusedSoftKeyboardInterceptionNode2 = softKeyboardInterceptionModifierNode;
        if (focusedSoftKeyboardInterceptionNode2 == null) {
            return false;
        }
        SoftKeyboardInterceptionModifierNode $this$traverseAncestors_u2dY_u2dYKmho$iv2 = focusedSoftKeyboardInterceptionNode2;
        int type$iv2 = NodeKind.m4443constructorimpl(131072);
        FocusOwnerImpl this_$iv4 = this;
        int i5 = 0;
        List list = null;
        if (!$this$traverseAncestors_u2dY_u2dYKmho$iv2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv$iv$iv$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv2.getNode().getParent();
        LayoutNode layout$iv$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$traverseAncestors_u2dY_u2dYKmho$iv2);
        while (layout$iv$iv$iv$iv != null) {
            Modifier.Node head$iv$iv$iv$iv = layout$iv$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                while (node$iv$iv$iv$iv2 != null) {
                    if ((node$iv$iv$iv$iv2.getKindSet() & type$iv2) != 0) {
                        Modifier.Node it$iv$iv$iv = node$iv$iv$iv$iv2;
                        MutableVector mutableVector3 = null;
                        Modifier.Node nodePop2 = it$iv$iv$iv;
                        while (nodePop2 != null) {
                            focusedSoftKeyboardInterceptionNode2 = focusedSoftKeyboardInterceptionNode2;
                            if (nodePop2 instanceof SoftKeyboardInterceptionModifierNode) {
                                Modifier.Node node = nodePop2;
                                if (list == null) {
                                    Object result$iv$iv = new ArrayList();
                                    list = (List) result$iv$iv;
                                }
                                list.add(node);
                                this_$iv2 = this_$iv4;
                                i3 = i5;
                            } else {
                                Modifier.Node this_$iv$iv$iv$iv$iv = nodePop2;
                                if (((this_$iv$iv$iv$iv$iv.getKindSet() & type$iv2) != 0) && (nodePop2 instanceof DelegatingNode)) {
                                    int count$iv$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop2;
                                    Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                        if ((next$iv$iv$iv$iv.getKindSet() & type$iv2) != 0) {
                                            count$iv$iv$iv$iv++;
                                            this_$iv3 = this_$iv4;
                                            if (count$iv$iv$iv$iv == 1) {
                                                nodePop2 = next$iv$iv$iv$iv;
                                                i5 = i5;
                                            } else {
                                                MutableVector mutableVector4 = mutableVector3 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector3;
                                                Modifier.Node theNode$iv$iv$iv$iv = nodePop2;
                                                if (theNode$iv$iv$iv$iv != null) {
                                                    if (mutableVector4 != null) {
                                                        mutableVector4.add(theNode$iv$iv$iv$iv);
                                                    }
                                                    nodePop2 = null;
                                                }
                                                if (mutableVector4 != null) {
                                                    mutableVector4.add(next$iv$iv$iv$iv);
                                                }
                                                mutableVector3 = mutableVector4;
                                                count$iv$iv$iv$iv = count$iv$iv$iv$iv;
                                            }
                                        } else {
                                            this_$iv3 = this_$iv4;
                                            i5 = i5;
                                        }
                                        node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                        this_$iv4 = this_$iv3;
                                        i5 = i5;
                                    }
                                    this_$iv2 = this_$iv4;
                                    i3 = i5;
                                    if (count$iv$iv$iv$iv == 1) {
                                        this_$iv4 = this_$iv2;
                                        i5 = i3;
                                    }
                                } else {
                                    this_$iv2 = this_$iv4;
                                    i3 = i5;
                                }
                            }
                            nodePop2 = DelegatableNodeKt.pop(mutableVector3);
                            this_$iv4 = this_$iv2;
                            i5 = i3;
                        }
                    }
                    node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getParent();
                    focusedSoftKeyboardInterceptionNode2 = focusedSoftKeyboardInterceptionNode2;
                    this_$iv4 = this_$iv4;
                    i5 = i5;
                }
                focusedSoftKeyboardInterceptionNode = focusedSoftKeyboardInterceptionNode2;
                this_$iv = this_$iv4;
                i2 = i5;
            } else {
                focusedSoftKeyboardInterceptionNode = focusedSoftKeyboardInterceptionNode2;
                this_$iv = this_$iv4;
                i2 = i5;
            }
            layout$iv$iv$iv$iv = layout$iv$iv$iv$iv.getParent$ui_release();
            node$iv$iv$iv$iv2 = (layout$iv$iv$iv$iv == null || (nodes = layout$iv$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            focusedSoftKeyboardInterceptionNode2 = focusedSoftKeyboardInterceptionNode;
            this_$iv4 = this_$iv;
            i5 = i2;
        }
        List ancestors$iv = list;
        if (ancestors$iv != null && (size = ancestors$iv.size() - 1) >= 0) {
            do {
                int index$iv$iv = size;
                size--;
                Object item$iv$iv = ancestors$iv.get(index$iv$iv);
                SoftKeyboardInterceptionModifierNode it = (SoftKeyboardInterceptionModifierNode) item$iv$iv;
                if (it.mo3656onPreInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                    return true;
                }
            } while (size >= 0);
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$traverseAncestors_u2dY_u2dYKmho$iv2.getNode();
        int i6 = 0;
        MutableVector mutableVector5 = null;
        Modifier.Node nodePop3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
        while (nodePop3 != null) {
            if (nodePop3 instanceof SoftKeyboardInterceptionModifierNode) {
                SoftKeyboardInterceptionModifierNode it2 = (SoftKeyboardInterceptionModifierNode) nodePop3;
                if (it2.mo3656onPreInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                    return true;
                }
                $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                i = i6;
            } else {
                Modifier.Node this_$iv$iv$iv = nodePop3;
                if (((this_$iv$iv$iv.getKindSet() & type$iv2) != 0) && (nodePop3 instanceof DelegatingNode)) {
                    int count$iv$iv = 0;
                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop3;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv2.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv = node$iv$iv$iv2;
                        if ((next$iv$iv.getKindSet() & type$iv2) != 0) {
                            count$iv$iv++;
                            if (count$iv$iv == 1) {
                                nodePop3 = next$iv$iv;
                                $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                                i6 = i6;
                            } else {
                                mutableVector5 = mutableVector5 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector5;
                                Modifier.Node theNode$iv$iv = nodePop3;
                                if (theNode$iv$iv != null) {
                                    if (mutableVector5 != null) {
                                        mutableVector5.add(theNode$iv$iv);
                                    }
                                    nodePop3 = null;
                                }
                                if (mutableVector5 != null) {
                                    mutableVector5.add(next$iv$iv);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                            i6 = i6;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                        i6 = i6;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                    i = i6;
                    MutableVector mutableVector6 = mutableVector5;
                    if (count$iv$iv == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                        i6 = i;
                        mutableVector5 = mutableVector6;
                    } else {
                        mutableVector5 = mutableVector6;
                    }
                } else {
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                    i = i6;
                }
            }
            nodePop3 = DelegatableNodeKt.pop(mutableVector5);
            $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
            i6 = i;
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$traverseAncestors_u2dY_u2dYKmho$iv2.getNode();
        MutableVector mutableVector7 = null;
        Modifier.Node nodePop4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
        while (nodePop4 != null) {
            if (nodePop4 instanceof SoftKeyboardInterceptionModifierNode) {
                SoftKeyboardInterceptionModifierNode it3 = (SoftKeyboardInterceptionModifierNode) nodePop4;
                if (it3.mo3655onInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                    return true;
                }
                $this$traverseAncestors_u2dY_u2dYKmho$iv = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                type$iv = type$iv2;
            } else {
                Modifier.Node this_$iv$iv$iv3 = nodePop4;
                if (((this_$iv$iv$iv3.getKindSet() & type$iv2) != 0) && (nodePop4 instanceof DelegatingNode)) {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop4;
                    Modifier.Node node$iv$iv$iv3 = this_$iv$iv$iv4.getDelegate();
                    while (node$iv$iv$iv3 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv3;
                        if ((next$iv$iv2.getKindSet() & type$iv2) != 0) {
                            count$iv$iv2++;
                            if (count$iv$iv2 == 1) {
                                nodePop4 = next$iv$iv2;
                                $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                                $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                                type$iv2 = type$iv2;
                            } else {
                                mutableVector7 = mutableVector7 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector7;
                                Modifier.Node theNode$iv$iv2 = nodePop4;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector7 != null) {
                                        mutableVector7.add(theNode$iv$iv2);
                                    }
                                    nodePop4 = null;
                                }
                                if (mutableVector7 != null) {
                                    mutableVector7.add(next$iv$iv2);
                                }
                            }
                        } else {
                            $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                            type$iv2 = type$iv2;
                        }
                        node$iv$iv$iv3 = node$iv$iv$iv3.getChild();
                        $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                        type$iv2 = type$iv2;
                    }
                    $this$traverseAncestors_u2dY_u2dYKmho$iv = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                    type$iv = type$iv2;
                    if (count$iv$iv2 == 1) {
                        $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv;
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                        type$iv2 = type$iv;
                    }
                } else {
                    $this$traverseAncestors_u2dY_u2dYKmho$iv = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                    type$iv = type$iv2;
                }
            }
            nodePop4 = DelegatableNodeKt.pop(mutableVector7);
            $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv;
            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
            type$iv2 = type$iv;
        }
        if (ancestors$iv == null) {
            return false;
        }
        int size2 = ancestors$iv.size();
        for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
            Object item$iv$iv2 = ancestors$iv.get(index$iv$iv2);
            SoftKeyboardInterceptionModifierNode it4 = (SoftKeyboardInterceptionModifierNode) item$iv$iv2;
            if (it4.mo3655onInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean dispatchRotaryEvent(RotaryScrollEvent event) {
        RotaryInputModifierNode rotaryInputModifierNode;
        DelegatableNode $this$traverseAncestors_u2dY_u2dYKmho$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        int type$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
        int i;
        int size;
        RotaryInputModifierNode focusedRotaryInputNode;
        FocusOwnerImpl this_$iv;
        int i2;
        NodeChain nodes;
        FocusOwnerImpl this_$iv2;
        int i3;
        FocusOwnerImpl this_$iv3;
        DelegatableNode delegatableNode;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
        NodeChain nodes2;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv2;
        Intrinsics.checkNotNullParameter(event, "event");
        DelegatableNode delegatableNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        int i4 = 1;
        if (delegatableNodeFindActiveFocusNode != null) {
            DelegatableNode $this$nearestAncestor_u2d64DMado$iv3 = delegatableNodeFindActiveFocusNode;
            int iM4443constructorimpl = NodeKind.m4443constructorimpl(16384);
            if (!$this$nearestAncestor_u2d64DMado$iv3.getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node".toString());
            }
            Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv3.getNode().getParent();
            LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv3);
            loop0: while (true) {
                if (layout$iv$iv$iv == null) {
                    delegatableNode = null;
                    break;
                }
                Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                if ((head$iv$iv$iv.getAggregateChildKindSet() & iM4443constructorimpl) != 0) {
                    while (node$iv$iv$iv != null) {
                        if ((node$iv$iv$iv.getKindSet() & iM4443constructorimpl) != 0) {
                            Modifier.Node it$iv$iv = node$iv$iv$iv;
                            MutableVector mutableVector = null;
                            Modifier.Node nodePop = it$iv$iv;
                            while (nodePop != null) {
                                if (nodePop instanceof RotaryInputModifierNode) {
                                    delegatableNode = nodePop;
                                    break loop0;
                                }
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv$iv.getKindSet() & iM4443constructorimpl) != 0) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        if ((next$iv$iv$iv.getKindSet() & iM4443constructorimpl) != 0) {
                                            count$iv$iv$iv++;
                                            if (count$iv$iv$iv == i4) {
                                                nodePop = next$iv$iv$iv;
                                                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                            } else {
                                                MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                Modifier.Node theNode$iv$iv$iv = nodePop;
                                                if (theNode$iv$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(next$iv$iv$iv);
                                                }
                                                mutableVector = mutableVector2;
                                            }
                                        } else {
                                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                        i4 = 1;
                                    }
                                    $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv3;
                                    if (count$iv$iv$iv == 1) {
                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                        i4 = 1;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector);
                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                        i4 = 1;
                                    }
                                } else {
                                    $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv3;
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                    i4 = 1;
                                }
                            }
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getParent();
                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                        i4 = 1;
                    }
                    $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                } else {
                    $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                }
                layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui_release();
                node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv;
                i4 = 1;
            }
            rotaryInputModifierNode = (RotaryInputModifierNode) delegatableNode;
        } else {
            rotaryInputModifierNode = null;
        }
        RotaryInputModifierNode focusedRotaryInputNode2 = rotaryInputModifierNode;
        if (focusedRotaryInputNode2 == null) {
            return false;
        }
        RotaryInputModifierNode $this$traverseAncestors_u2dY_u2dYKmho$iv2 = focusedRotaryInputNode2;
        int type$iv2 = NodeKind.m4443constructorimpl(16384);
        FocusOwnerImpl this_$iv4 = this;
        int i5 = 0;
        List list = null;
        if (!$this$traverseAncestors_u2dY_u2dYKmho$iv2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv$iv$iv$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv2.getNode().getParent();
        LayoutNode layout$iv$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$traverseAncestors_u2dY_u2dYKmho$iv2);
        while (layout$iv$iv$iv$iv != null) {
            Modifier.Node head$iv$iv$iv$iv = layout$iv$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                while (node$iv$iv$iv$iv2 != null) {
                    if ((node$iv$iv$iv$iv2.getKindSet() & type$iv2) != 0) {
                        Modifier.Node it$iv$iv$iv = node$iv$iv$iv$iv2;
                        MutableVector mutableVector3 = null;
                        Modifier.Node nodePop2 = it$iv$iv$iv;
                        while (nodePop2 != null) {
                            focusedRotaryInputNode2 = focusedRotaryInputNode2;
                            if (nodePop2 instanceof RotaryInputModifierNode) {
                                Modifier.Node node = nodePop2;
                                if (list == null) {
                                    Object result$iv$iv = new ArrayList();
                                    list = (List) result$iv$iv;
                                }
                                list.add(node);
                                this_$iv2 = this_$iv4;
                                i3 = i5;
                            } else {
                                Modifier.Node this_$iv$iv$iv$iv$iv = nodePop2;
                                if (((this_$iv$iv$iv$iv$iv.getKindSet() & type$iv2) != 0) && (nodePop2 instanceof DelegatingNode)) {
                                    int count$iv$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop2;
                                    Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                        if ((next$iv$iv$iv$iv.getKindSet() & type$iv2) != 0) {
                                            count$iv$iv$iv$iv++;
                                            this_$iv3 = this_$iv4;
                                            if (count$iv$iv$iv$iv == 1) {
                                                nodePop2 = next$iv$iv$iv$iv;
                                                i5 = i5;
                                            } else {
                                                MutableVector mutableVector4 = mutableVector3 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector3;
                                                Modifier.Node theNode$iv$iv$iv$iv = nodePop2;
                                                if (theNode$iv$iv$iv$iv != null) {
                                                    if (mutableVector4 != null) {
                                                        mutableVector4.add(theNode$iv$iv$iv$iv);
                                                    }
                                                    nodePop2 = null;
                                                }
                                                if (mutableVector4 != null) {
                                                    mutableVector4.add(next$iv$iv$iv$iv);
                                                }
                                                mutableVector3 = mutableVector4;
                                                count$iv$iv$iv$iv = count$iv$iv$iv$iv;
                                            }
                                        } else {
                                            this_$iv3 = this_$iv4;
                                            i5 = i5;
                                        }
                                        node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                        this_$iv4 = this_$iv3;
                                        i5 = i5;
                                    }
                                    this_$iv2 = this_$iv4;
                                    i3 = i5;
                                    if (count$iv$iv$iv$iv == 1) {
                                        this_$iv4 = this_$iv2;
                                        i5 = i3;
                                    }
                                } else {
                                    this_$iv2 = this_$iv4;
                                    i3 = i5;
                                }
                            }
                            nodePop2 = DelegatableNodeKt.pop(mutableVector3);
                            this_$iv4 = this_$iv2;
                            i5 = i3;
                        }
                    }
                    node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getParent();
                    focusedRotaryInputNode2 = focusedRotaryInputNode2;
                    this_$iv4 = this_$iv4;
                    i5 = i5;
                }
                focusedRotaryInputNode = focusedRotaryInputNode2;
                this_$iv = this_$iv4;
                i2 = i5;
            } else {
                focusedRotaryInputNode = focusedRotaryInputNode2;
                this_$iv = this_$iv4;
                i2 = i5;
            }
            layout$iv$iv$iv$iv = layout$iv$iv$iv$iv.getParent$ui_release();
            node$iv$iv$iv$iv2 = (layout$iv$iv$iv$iv == null || (nodes = layout$iv$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            focusedRotaryInputNode2 = focusedRotaryInputNode;
            this_$iv4 = this_$iv;
            i5 = i2;
        }
        List ancestors$iv = list;
        if (ancestors$iv != null && (size = ancestors$iv.size() - 1) >= 0) {
            do {
                int index$iv$iv = size;
                size--;
                Object item$iv$iv = ancestors$iv.get(index$iv$iv);
                RotaryInputModifierNode it = (RotaryInputModifierNode) item$iv$iv;
                if (it.onPreRotaryScrollEvent(event)) {
                    return true;
                }
            } while (size >= 0);
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$traverseAncestors_u2dY_u2dYKmho$iv2.getNode();
        int i6 = 0;
        MutableVector mutableVector5 = null;
        Modifier.Node nodePop3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
        while (nodePop3 != null) {
            if (nodePop3 instanceof RotaryInputModifierNode) {
                RotaryInputModifierNode it2 = (RotaryInputModifierNode) nodePop3;
                if (it2.onPreRotaryScrollEvent(event)) {
                    return true;
                }
                $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                i = i6;
            } else {
                Modifier.Node this_$iv$iv$iv = nodePop3;
                if (((this_$iv$iv$iv.getKindSet() & type$iv2) != 0) && (nodePop3 instanceof DelegatingNode)) {
                    int count$iv$iv = 0;
                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop3;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv2.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv = node$iv$iv$iv2;
                        if ((next$iv$iv.getKindSet() & type$iv2) != 0) {
                            count$iv$iv++;
                            if (count$iv$iv == 1) {
                                nodePop3 = next$iv$iv;
                                $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                                i6 = i6;
                            } else {
                                mutableVector5 = mutableVector5 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector5;
                                Modifier.Node theNode$iv$iv = nodePop3;
                                if (theNode$iv$iv != null) {
                                    if (mutableVector5 != null) {
                                        mutableVector5.add(theNode$iv$iv);
                                    }
                                    nodePop3 = null;
                                }
                                if (mutableVector5 != null) {
                                    mutableVector5.add(next$iv$iv);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                            i6 = i6;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                        i6 = i6;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                    i = i6;
                    MutableVector mutableVector6 = mutableVector5;
                    if (count$iv$iv == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                        i6 = i;
                        mutableVector5 = mutableVector6;
                    } else {
                        mutableVector5 = mutableVector6;
                    }
                } else {
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                    i = i6;
                }
            }
            nodePop3 = DelegatableNodeKt.pop(mutableVector5);
            $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
            i6 = i;
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$traverseAncestors_u2dY_u2dYKmho$iv2.getNode();
        MutableVector mutableVector7 = null;
        Modifier.Node nodePop4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
        while (nodePop4 != null) {
            if (nodePop4 instanceof RotaryInputModifierNode) {
                RotaryInputModifierNode it3 = (RotaryInputModifierNode) nodePop4;
                if (it3.onRotaryScrollEvent(event)) {
                    return true;
                }
                $this$traverseAncestors_u2dY_u2dYKmho$iv = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                type$iv = type$iv2;
            } else {
                Modifier.Node this_$iv$iv$iv3 = nodePop4;
                if (((this_$iv$iv$iv3.getKindSet() & type$iv2) != 0) && (nodePop4 instanceof DelegatingNode)) {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop4;
                    Modifier.Node node$iv$iv$iv3 = this_$iv$iv$iv4.getDelegate();
                    while (node$iv$iv$iv3 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv3;
                        if ((next$iv$iv2.getKindSet() & type$iv2) != 0) {
                            count$iv$iv2++;
                            if (count$iv$iv2 == 1) {
                                nodePop4 = next$iv$iv2;
                                $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                                $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                                type$iv2 = type$iv2;
                            } else {
                                mutableVector7 = mutableVector7 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector7;
                                Modifier.Node theNode$iv$iv2 = nodePop4;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector7 != null) {
                                        mutableVector7.add(theNode$iv$iv2);
                                    }
                                    nodePop4 = null;
                                }
                                if (mutableVector7 != null) {
                                    mutableVector7.add(next$iv$iv2);
                                }
                            }
                        } else {
                            $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                            type$iv2 = type$iv2;
                        }
                        node$iv$iv$iv3 = node$iv$iv$iv3.getChild();
                        $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                        type$iv2 = type$iv2;
                    }
                    $this$traverseAncestors_u2dY_u2dYKmho$iv = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                    type$iv = type$iv2;
                    if (count$iv$iv2 == 1) {
                        $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv;
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                        type$iv2 = type$iv;
                    }
                } else {
                    $this$traverseAncestors_u2dY_u2dYKmho$iv = $this$traverseAncestors_u2dY_u2dYKmho$iv2;
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                    type$iv = type$iv2;
                }
            }
            nodePop4 = DelegatableNodeKt.pop(mutableVector7);
            $this$traverseAncestors_u2dY_u2dYKmho$iv2 = $this$traverseAncestors_u2dY_u2dYKmho$iv;
            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
            type$iv2 = type$iv;
        }
        if (ancestors$iv == null) {
            return false;
        }
        int size2 = ancestors$iv.size();
        for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
            Object item$iv$iv2 = ancestors$iv.get(index$iv$iv2);
            RotaryInputModifierNode it4 = (RotaryInputModifierNode) item$iv$iv2;
            if (it4.onRotaryScrollEvent(event)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusTargetNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusEventModifierNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusPropertiesModifierNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    /* JADX INFO: renamed from: traverseAncestors-Y-YKmho, reason: not valid java name */
    private final /* synthetic */ <T extends DelegatableNode> void m2671traverseAncestorsYYKmho(DelegatableNode $this$traverseAncestors_u2dY_u2dYKmho, int type, Function1<? super T, Unit> function1, Function1<? super T, Unit> function2) {
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        int i;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2;
        int i2;
        int size;
        int i3;
        DelegatableNode $this$ancestors_u2d64DMado$iv;
        int i4;
        NodeChain nodes;
        int i5;
        DelegatableNode $this$ancestors_u2d64DMado$iv2;
        int i6;
        DelegatableNode $this$ancestors_u2d64DMado$iv3;
        int i7;
        int i8;
        function1 = function1;
        int i9 = 0;
        DelegatableNode $this$ancestors_u2d64DMado$iv4 = $this$traverseAncestors_u2dY_u2dYKmho;
        int i10 = 0;
        Object result$iv = null;
        if (!$this$ancestors_u2d64DMado$iv4.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv$iv$iv = $this$ancestors_u2d64DMado$iv4.getNode().getParent();
        LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$ancestors_u2d64DMado$iv4);
        while (layout$iv$iv$iv != null) {
            Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv.getAggregateChildKindSet() & type) != 0) {
                while (node$iv$iv$iv != null) {
                    if ((node$iv$iv$iv.getKindSet() & type) != 0) {
                        Modifier.Node it$iv$iv = node$iv$iv$iv;
                        Object stack$iv$iv$iv = null;
                        Modifier.Node this_$iv$iv$iv$iv = it$iv$iv;
                        while (this_$iv$iv$iv$iv != null) {
                            i9 = i9;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (this_$iv$iv$iv$iv instanceof Object) {
                                Modifier.Node node = this_$iv$iv$iv$iv;
                                if (result$iv == null) {
                                    Object result$iv2 = new ArrayList();
                                    result$iv = (List) result$iv2;
                                }
                                $this$ancestors_u2d64DMado$iv3 = $this$ancestors_u2d64DMado$iv4;
                                ((List) result$iv).add(node);
                                i7 = i10;
                            } else {
                                $this$ancestors_u2d64DMado$iv3 = $this$ancestors_u2d64DMado$iv4;
                                if (((this_$iv$iv$iv$iv.getKindSet() & type) != 0) && (this_$iv$iv$iv$iv instanceof DelegatingNode)) {
                                    int count$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) this_$iv$iv$iv$iv;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        DelegatingNode this_$iv$iv$iv$iv3 = this_$iv$iv$iv$iv2;
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        if ((next$iv$iv$iv.getKindSet() & type) != 0) {
                                            count$iv$iv$iv++;
                                            i8 = i10;
                                            if (count$iv$iv$iv == 1) {
                                                this_$iv$iv$iv$iv = next$iv$iv$iv;
                                                result$iv = result$iv;
                                            } else {
                                                Object mutableVector = (MutableVector) stack$iv$iv$iv;
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                stack$iv$iv$iv = mutableVector;
                                                Modifier.Node theNode$iv$iv$iv = this_$iv$iv$iv$iv;
                                                if (theNode$iv$iv$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv$iv);
                                                    }
                                                    this_$iv$iv$iv$iv = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv$iv$iv);
                                                }
                                                count$iv$iv$iv = count$iv$iv$iv;
                                            }
                                        } else {
                                            i8 = i10;
                                            result$iv = result$iv;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        this_$iv$iv$iv$iv2 = this_$iv$iv$iv$iv3;
                                        i10 = i8;
                                        result$iv = result$iv;
                                    }
                                    i7 = i10;
                                    Object result$iv3 = result$iv;
                                    if (count$iv$iv$iv == 1) {
                                        $this$ancestors_u2d64DMado$iv4 = $this$ancestors_u2d64DMado$iv3;
                                        i10 = i7;
                                        result$iv = result$iv3;
                                    } else {
                                        result$iv = result$iv3;
                                    }
                                } else {
                                    i7 = i10;
                                    result$iv = result$iv;
                                }
                            }
                            this_$iv$iv$iv$iv = DelegatableNodeKt.pop((MutableVector) stack$iv$iv$iv);
                            $this$ancestors_u2d64DMado$iv4 = $this$ancestors_u2d64DMado$iv3;
                            i10 = i7;
                        }
                        i5 = i9;
                        $this$ancestors_u2d64DMado$iv2 = $this$ancestors_u2d64DMado$iv4;
                        i6 = i10;
                    } else {
                        i5 = i9;
                        $this$ancestors_u2d64DMado$iv2 = $this$ancestors_u2d64DMado$iv4;
                        i6 = i10;
                    }
                    node$iv$iv$iv = node$iv$iv$iv.getParent();
                    i9 = i5;
                    $this$ancestors_u2d64DMado$iv4 = $this$ancestors_u2d64DMado$iv2;
                    i10 = i6;
                }
                i3 = i9;
                $this$ancestors_u2d64DMado$iv = $this$ancestors_u2d64DMado$iv4;
                i4 = i10;
            } else {
                i3 = i9;
                $this$ancestors_u2d64DMado$iv = $this$ancestors_u2d64DMado$iv4;
                i4 = i10;
            }
            layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui_release();
            node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes = layout$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            i9 = i3;
            $this$ancestors_u2d64DMado$iv4 = $this$ancestors_u2d64DMado$iv;
            i10 = i4;
        }
        List ancestors = (List) result$iv;
        if (ancestors != null && (size = ancestors.size() - 1) >= 0) {
            do {
                int index$iv = size;
                size--;
                Object item$iv = ancestors.get(index$iv);
                function1.invoke(item$iv);
            } while (size >= 0);
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$traverseAncestors_u2dY_u2dYKmho.getNode();
        int i11 = 0;
        Object stack$iv = null;
        Modifier.Node node$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
        while (node$iv != null) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if (node$iv instanceof Object) {
                function1.invoke(node$iv);
                $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                i2 = i11;
            } else {
                Modifier.Node this_$iv$iv = node$iv;
                if (((this_$iv$iv.getKindSet() & type) != 0) && (node$iv instanceof DelegatingNode)) {
                    int count$iv = 0;
                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv;
                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                    while (node$iv$iv != null) {
                        Modifier.Node next$iv = node$iv$iv;
                        if ((next$iv.getKindSet() & type) != 0) {
                            count$iv++;
                            if (count$iv == 1) {
                                node$iv = next$iv;
                                $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                i11 = i11;
                            } else {
                                Object obj = (MutableVector) stack$iv;
                                if (obj == null) {
                                    Object mutableVector4 = new MutableVector(new Modifier.Node[16], 0);
                                    obj = mutableVector4;
                                }
                                stack$iv = obj;
                                Modifier.Node theNode$iv = node$iv;
                                if (theNode$iv != null) {
                                    MutableVector mutableVector5 = (MutableVector) stack$iv;
                                    if (mutableVector5 != null) {
                                        mutableVector5.add(theNode$iv);
                                    }
                                    node$iv = null;
                                }
                                MutableVector mutableVector6 = (MutableVector) stack$iv;
                                if (mutableVector6 != null) {
                                    mutableVector6.add(next$iv);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                            i11 = i11;
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                        i11 = i11;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                    i2 = i11;
                    if (count$iv == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                        i11 = i2;
                    }
                } else {
                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                    i2 = i11;
                }
            }
            node$iv = DelegatableNodeKt.pop((MutableVector) stack$iv);
            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv2;
            i11 = i2;
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$traverseAncestors_u2dY_u2dYKmho.getNode();
        int i12 = 0;
        Object stack$iv2 = null;
        Modifier.Node node$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv4;
        while (node$iv2 != null) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if (node$iv2 instanceof Object) {
                function2.invoke(node$iv2);
                $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv4;
                i = i12;
            } else {
                Modifier.Node this_$iv$iv3 = node$iv2;
                if (((this_$iv$iv3.getKindSet() & type) != 0) && (node$iv2 instanceof DelegatingNode)) {
                    int count$iv2 = 0;
                    DelegatingNode this_$iv$iv4 = (DelegatingNode) node$iv2;
                    Modifier.Node node$iv$iv2 = this_$iv$iv4.getDelegate();
                    while (node$iv$iv2 != null) {
                        Modifier.Node next$iv2 = node$iv$iv2;
                        if ((next$iv2.getKindSet() & type) != 0) {
                            count$iv2++;
                            if (count$iv2 == 1) {
                                node$iv2 = next$iv2;
                                $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv4;
                                i12 = i12;
                            } else {
                                Object obj2 = (MutableVector) stack$iv2;
                                if (obj2 == null) {
                                    Object mutableVector7 = new MutableVector(new Modifier.Node[16], 0);
                                    obj2 = mutableVector7;
                                }
                                stack$iv2 = obj2;
                                Modifier.Node theNode$iv2 = node$iv2;
                                if (theNode$iv2 != null) {
                                    MutableVector mutableVector8 = (MutableVector) stack$iv2;
                                    if (mutableVector8 != null) {
                                        mutableVector8.add(theNode$iv2);
                                    }
                                    node$iv2 = null;
                                }
                                MutableVector mutableVector9 = (MutableVector) stack$iv2;
                                if (mutableVector9 != null) {
                                    mutableVector9.add(next$iv2);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv4;
                            i12 = i12;
                        }
                        node$iv$iv2 = node$iv$iv2.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv4;
                        i12 = i12;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv4;
                    i = i12;
                    if (count$iv2 == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv;
                        i12 = i;
                    }
                } else {
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv4;
                    i = i12;
                }
            }
            node$iv2 = DelegatableNodeKt.pop((MutableVector) stack$iv2);
            $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv;
            i12 = i;
        }
        if (ancestors != null) {
            int size2 = ancestors.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = ancestors.get(index$iv2);
                function2.invoke(item$iv2);
            }
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public Rect getFocusRect() {
        FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        if (focusTargetNodeFindActiveFocusNode != null) {
            return FocusTraversalKt.focusRect(focusTargetNodeFindActiveFocusNode);
        }
        return null;
    }

    private final Modifier.Node lastLocalKeyInputNode(DelegatableNode $this$lastLocalKeyInputNode) {
        Modifier.Node node = null;
        int mask$iv = NodeKind.m4443constructorimpl(1024) | NodeKind.m4443constructorimpl(8192);
        if (!$this$lastLocalKeyInputNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitLocalDescendants called on an unattached node".toString());
        }
        Modifier.Node self$iv = $this$lastLocalKeyInputNode.getNode();
        if ((self$iv.getAggregateChildKindSet() & mask$iv) != 0) {
            for (Modifier.Node next$iv = self$iv.getChild(); next$iv != null; next$iv = next$iv.getChild()) {
                if ((next$iv.getKindSet() & mask$iv) != 0) {
                    Modifier.Node modifierNode = next$iv;
                    if ((modifierNode.getKindSet() & NodeKind.m4443constructorimpl(1024)) != 0) {
                        return node;
                    }
                    node = modifierNode;
                }
            }
        }
        return node;
    }

    /* JADX INFO: renamed from: wrapAroundFocus-3ESFkO8, reason: not valid java name */
    private final boolean m2672wrapAroundFocus3ESFkO8(int focusDirection) {
        if (!this.rootFocusNode.getFocusState().getHasFocus() || this.rootFocusNode.getFocusState().isFocused()) {
            return false;
        }
        if (!(FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2663getNextdhqQ8s()) ? true : FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2665getPreviousdhqQ8s()))) {
            return false;
        }
        clearFocus(false);
        if (this.rootFocusNode.getFocusState().isFocused()) {
            return mo2668moveFocus3ESFkO8(focusDirection);
        }
        return false;
    }
}
