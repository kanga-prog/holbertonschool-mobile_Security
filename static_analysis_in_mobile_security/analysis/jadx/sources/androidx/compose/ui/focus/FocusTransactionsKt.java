package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.Owner;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusTransactions.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a \u0010\u0003\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\u001e\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a!\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000e\u001a!\u0010\u000f\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u000e\u001a!\u0010\u0011\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u000e\u001a!\u0010\u0013\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u000e\u001a\f\u0010\u0015\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0016\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0014\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002H\u0002\u001a\f\u0010\u0019\u001a\u00020\u0001*\u00020\u0002H\u0002\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"captureFocus", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "clearChildFocus", "forced", "refreshFocusEvents", "clearFocus", "freeFocus", "grantFocus", "performCustomClearFocus", "Landroidx/compose/ui/focus/CustomDestinationResult;", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "performCustomClearFocus-Mxy_nc0", "(Landroidx/compose/ui/focus/FocusTargetNode;I)Landroidx/compose/ui/focus/CustomDestinationResult;", "performCustomEnter", "performCustomEnter-Mxy_nc0", "performCustomExit", "performCustomExit-Mxy_nc0", "performCustomRequestFocus", "performCustomRequestFocus-Mxy_nc0", "performRequestFocus", "requestFocus", "requestFocusForChild", "childNode", "requestFocusForOwner", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FocusTransactionsKt {

    /* JADX INFO: compiled from: FocusTransactions.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final boolean requestFocus(FocusTargetNode $this$requestFocus) {
        Intrinsics.checkNotNullParameter($this$requestFocus, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[m2682performCustomRequestFocusMxy_nc0($this$requestFocus, FocusDirection.INSTANCE.m2659getEnterdhqQ8s()).ordinal()]) {
            case 1:
                return performRequestFocus($this$requestFocus);
            case 2:
                return true;
            case 3:
            case 4:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final boolean performRequestFocus(FocusTargetNode $this$performRequestFocus) {
        Modifier.Node nodePop;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
        NodeChain nodes;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv2;
        Intrinsics.checkNotNullParameter($this$performRequestFocus, "<this>");
        int i = 1;
        switch (WhenMappings.$EnumSwitchMapping$1[$this$performRequestFocus.getFocusState().ordinal()]) {
            case 1:
            case 2:
                FocusEventModifierNodeKt.refreshFocusEventNodes($this$performRequestFocus);
                return true;
            case 3:
                boolean z = clearChildFocus$default($this$performRequestFocus, false, false, 3, null) && grantFocus($this$performRequestFocus);
                boolean success = z;
                if (success) {
                    FocusEventModifierNodeKt.refreshFocusEventNodes($this$performRequestFocus);
                }
                return z;
            case 4:
                FocusTargetNode $this$nearestAncestor_u2d64DMado$iv3 = $this$performRequestFocus;
                int iM4443constructorimpl = NodeKind.m4443constructorimpl(1024);
                if (!$this$nearestAncestor_u2d64DMado$iv3.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                }
                Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv3.getNode().getParent();
                LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv3);
                while (true) {
                    if (layout$iv$iv$iv != null) {
                        Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                        if ((head$iv$iv$iv.getAggregateChildKindSet() & iM4443constructorimpl) != 0) {
                            while (true) {
                                if (node$iv$iv$iv != null) {
                                    if ((node$iv$iv$iv.getKindSet() & iM4443constructorimpl) != 0) {
                                        Modifier.Node it$iv$iv = node$iv$iv$iv;
                                        MutableVector mutableVector = null;
                                        nodePop = it$iv$iv;
                                        while (true) {
                                            if (nodePop == null) {
                                                continue;
                                            } else if (!(nodePop instanceof FocusTargetNode)) {
                                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                                if (((this_$iv$iv$iv$iv.getKindSet() & iM4443constructorimpl) != 0) && (nodePop instanceof DelegatingNode)) {
                                                    int count$iv$iv$iv = 0;
                                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                                    while (node$iv$iv$iv$iv != null) {
                                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                                        if ((next$iv$iv$iv.getKindSet() & iM4443constructorimpl) != 0) {
                                                            count$iv$iv$iv++;
                                                            if (count$iv$iv$iv == i) {
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
                                                                count$iv$iv$iv = count$iv$iv$iv;
                                                            }
                                                        } else {
                                                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                                        }
                                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                                        i = 1;
                                                    }
                                                    $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv3;
                                                    if (count$iv$iv$iv == 1) {
                                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                                        i = 1;
                                                    } else {
                                                        nodePop = DelegatableNodeKt.pop(mutableVector);
                                                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                                        i = 1;
                                                    }
                                                } else {
                                                    $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv3;
                                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                                    $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv2;
                                                    i = 1;
                                                }
                                            }
                                        }
                                    }
                                    node$iv$iv$iv = node$iv$iv$iv.getParent();
                                    $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                    i = 1;
                                } else {
                                    $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                                }
                            }
                        } else {
                            $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                        }
                        layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui_release();
                        node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes = layout$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv;
                        i = 1;
                    } else {
                        nodePop = null;
                    }
                }
                FocusTargetNode focusTargetNode = (FocusTargetNode) nodePop;
                if (focusTargetNode != null) {
                    return requestFocusForChild(focusTargetNode, $this$performRequestFocus);
                }
                boolean z2 = requestFocusForOwner($this$performRequestFocus) && grantFocus($this$performRequestFocus);
                boolean success2 = z2;
                if (success2) {
                    FocusEventModifierNodeKt.refreshFocusEventNodes($this$performRequestFocus);
                }
                return z2;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final boolean captureFocus(FocusTargetNode $this$captureFocus) {
        Intrinsics.checkNotNullParameter($this$captureFocus, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$1[$this$captureFocus.getFocusState().ordinal()]) {
            case 1:
                $this$captureFocus.setFocusState(FocusStateImpl.Captured);
                FocusEventModifierNodeKt.refreshFocusEventNodes($this$captureFocus);
                return true;
            case 2:
                return true;
            case 3:
            case 4:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final boolean freeFocus(FocusTargetNode $this$freeFocus) {
        Intrinsics.checkNotNullParameter($this$freeFocus, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$1[$this$freeFocus.getFocusState().ordinal()]) {
            case 1:
                return true;
            case 2:
                $this$freeFocus.setFocusState(FocusStateImpl.Active);
                FocusEventModifierNodeKt.refreshFocusEventNodes($this$freeFocus);
                return true;
            case 3:
            case 4:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static /* synthetic */ boolean clearFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return clearFocus(focusTargetNode, z, z2);
    }

    public static final boolean clearFocus(FocusTargetNode $this$clearFocus, boolean forced, boolean refreshFocusEvents) {
        Intrinsics.checkNotNullParameter($this$clearFocus, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$1[$this$clearFocus.getFocusState().ordinal()]) {
            case 1:
                $this$clearFocus.setFocusState(FocusStateImpl.Inactive);
                if (!refreshFocusEvents) {
                    return true;
                }
                FocusEventModifierNodeKt.refreshFocusEventNodes($this$clearFocus);
                return true;
            case 2:
                if (forced) {
                    $this$clearFocus.setFocusState(FocusStateImpl.Inactive);
                    if (refreshFocusEvents) {
                        FocusEventModifierNodeKt.refreshFocusEventNodes($this$clearFocus);
                    }
                }
                return forced;
            case 3:
                if (clearChildFocus($this$clearFocus, forced, refreshFocusEvents)) {
                    $this$clearFocus.setFocusState(FocusStateImpl.Inactive);
                    if (!refreshFocusEvents) {
                        return true;
                    }
                    FocusEventModifierNodeKt.refreshFocusEventNodes($this$clearFocus);
                    return true;
                }
                return false;
            case 4:
                return true;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final boolean grantFocus(final FocusTargetNode $this$grantFocus) {
        ObserverModifierNodeKt.observeReads($this$grantFocus, new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusTransactionsKt.grantFocus.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                $this$grantFocus.fetchFocusProperties$ui_release();
            }
        });
        switch (WhenMappings.$EnumSwitchMapping$1[$this$grantFocus.getFocusState().ordinal()]) {
            case 3:
            case 4:
                $this$grantFocus.setFocusState(FocusStateImpl.Active);
                break;
        }
        return true;
    }

    static /* synthetic */ boolean clearChildFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return clearChildFocus(focusTargetNode, z, z2);
    }

    private static final boolean clearChildFocus(FocusTargetNode $this$clearChildFocus, boolean forced, boolean refreshFocusEvents) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild($this$clearChildFocus);
        if (activeChild != null) {
            return clearFocus(activeChild, forced, refreshFocusEvents);
        }
        return true;
    }

    private static final boolean requestFocusForChild(FocusTargetNode $this$requestFocusForChild, FocusTargetNode childNode) {
        Modifier.Node node;
        Object obj;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
        int type$iv;
        int i;
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv;
        NodeChain nodes;
        int type$iv2;
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv2;
        int type$iv3;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv2;
        int type$iv4;
        int i2;
        NodeChain nodes2;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv3;
        int type$iv5;
        int i3;
        FocusTargetNode $this$nearestAncestor_u2d64DMado$iv4 = childNode;
        int type$iv6 = NodeKind.m4443constructorimpl(1024);
        int i4 = 0;
        if (!$this$nearestAncestor_u2d64DMado$iv4.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv4.getNode().getParent();
        LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv4);
        loop0: while (true) {
            int i5 = 1;
            if (layout$iv$iv$iv == null) {
                node = null;
                break;
            }
            Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv.getAggregateChildKindSet() & type$iv6) != 0) {
                while (node$iv$iv$iv != null) {
                    if ((node$iv$iv$iv.getKindSet() & type$iv6) != 0) {
                        Modifier.Node it$iv$iv = node$iv$iv$iv;
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = it$iv$iv;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                node = nodePop;
                                break loop0;
                            }
                            Modifier.Node this_$iv$iv$iv$iv = nodePop;
                            if (((this_$iv$iv$iv$iv.getKindSet() & type$iv6) != 0) && (nodePop instanceof DelegatingNode)) {
                                int count$iv$iv$iv = 0;
                                DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                while (node$iv$iv$iv$iv != null) {
                                    Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                    if ((next$iv$iv$iv.getKindSet() & type$iv6) != 0) {
                                        count$iv$iv$iv++;
                                        if (count$iv$iv$iv == i5) {
                                            nodePop = next$iv$iv$iv;
                                            $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv4;
                                            type$iv6 = type$iv6;
                                            i4 = i4;
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
                                        $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv4;
                                        type$iv6 = type$iv6;
                                        i4 = i4;
                                    }
                                    node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                    $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv4;
                                    type$iv6 = type$iv6;
                                    i4 = i4;
                                    i5 = 1;
                                }
                                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                type$iv5 = type$iv6;
                                i3 = i4;
                                if (count$iv$iv$iv == 1) {
                                    $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv3;
                                    type$iv6 = type$iv5;
                                    i4 = i3;
                                    i5 = 1;
                                } else {
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv3;
                                    type$iv6 = type$iv5;
                                    i4 = i3;
                                    i5 = 1;
                                }
                            } else {
                                $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                type$iv5 = type$iv6;
                                i3 = i4;
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                                $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv3;
                                type$iv6 = type$iv5;
                                i4 = i3;
                                i5 = 1;
                            }
                        }
                    }
                    node$iv$iv$iv = node$iv$iv$iv.getParent();
                    $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv4;
                    type$iv6 = type$iv6;
                    i4 = i4;
                    i5 = 1;
                }
                $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv4;
                type$iv4 = type$iv6;
                i2 = i4;
            } else {
                $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv4;
                type$iv4 = type$iv6;
                i2 = i4;
            }
            layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui_release();
            node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
            $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv2;
            type$iv6 = type$iv4;
            i4 = i2;
        }
        if (!Intrinsics.areEqual(node, $this$requestFocusForChild)) {
            throw new IllegalStateException("Non child node cannot request focus.".toString());
        }
        switch (WhenMappings.$EnumSwitchMapping$1[$this$requestFocusForChild.getFocusState().ordinal()]) {
            case 1:
                boolean success = grantFocus(childNode);
                if (!success) {
                    return success;
                }
                $this$requestFocusForChild.setFocusState(FocusStateImpl.ActiveParent);
                FocusEventModifierNodeKt.refreshFocusEventNodes(childNode);
                FocusEventModifierNodeKt.refreshFocusEventNodes($this$requestFocusForChild);
                return success;
            case 2:
                return false;
            case 3:
                if (FocusTraversalKt.getActiveChild($this$requestFocusForChild) == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                boolean success2 = clearChildFocus$default($this$requestFocusForChild, false, false, 3, null) && grantFocus(childNode);
                if (success2) {
                    FocusEventModifierNodeKt.refreshFocusEventNodes(childNode);
                }
                return success2;
            case 4:
                FocusTargetNode $this$nearestAncestor_u2d64DMado$iv5 = $this$requestFocusForChild;
                int type$iv7 = NodeKind.m4443constructorimpl(1024);
                int i6 = 0;
                DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3 = $this$nearestAncestor_u2d64DMado$iv5;
                if (!$this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                }
                Modifier.Node node$iv$iv$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3.getNode().getParent();
                LayoutNode layout$iv$iv$iv2 = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3);
                while (true) {
                    if (layout$iv$iv$iv2 != null) {
                        Modifier.Node head$iv$iv$iv2 = layout$iv$iv$iv2.getNodes().getHead();
                        if ((head$iv$iv$iv2.getAggregateChildKindSet() & type$iv7) != 0) {
                            while (true) {
                                if (node$iv$iv$iv2 != null) {
                                    if ((node$iv$iv$iv2.getKindSet() & type$iv7) != 0) {
                                        Modifier.Node it$iv$iv2 = node$iv$iv$iv2;
                                        MutableVector mutableVector3 = null;
                                        Modifier.Node nodePop2 = it$iv$iv2;
                                        while (true) {
                                            if (nodePop2 != null) {
                                                i6 = i6;
                                                if (nodePop2 instanceof FocusTargetNode) {
                                                    Object it$iv = nodePop2;
                                                    obj = it$iv;
                                                } else {
                                                    Modifier.Node this_$iv$iv$iv$iv3 = nodePop2;
                                                    if (((this_$iv$iv$iv$iv3.getKindSet() & type$iv7) != 0) && (nodePop2 instanceof DelegatingNode)) {
                                                        int count$iv$iv$iv2 = 0;
                                                        DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                                        Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                                                        while (node$iv$iv$iv$iv2 != null) {
                                                            Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv2;
                                                            if ((next$iv$iv$iv2.getKindSet() & type$iv7) != 0) {
                                                                count$iv$iv$iv2++;
                                                                type$iv3 = type$iv7;
                                                                if (count$iv$iv$iv2 == 1) {
                                                                    nodePop2 = next$iv$iv$iv2;
                                                                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3;
                                                                } else {
                                                                    MutableVector mutableVector4 = mutableVector3 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector3;
                                                                    Modifier.Node theNode$iv$iv$iv2 = nodePop2;
                                                                    if (theNode$iv$iv$iv2 != null) {
                                                                        if (mutableVector4 != null) {
                                                                            mutableVector4.add(theNode$iv$iv$iv2);
                                                                        }
                                                                        nodePop2 = null;
                                                                    }
                                                                    if (mutableVector4 != null) {
                                                                        mutableVector4.add(next$iv$iv$iv2);
                                                                    }
                                                                    mutableVector3 = mutableVector4;
                                                                    count$iv$iv$iv2 = count$iv$iv$iv2;
                                                                }
                                                            } else {
                                                                type$iv3 = type$iv7;
                                                                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3;
                                                            }
                                                            node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                                                            type$iv7 = type$iv3;
                                                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3;
                                                        }
                                                        type$iv2 = type$iv7;
                                                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3;
                                                        if (count$iv$iv$iv2 == 1) {
                                                            type$iv7 = type$iv2;
                                                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv2;
                                                        } else {
                                                            nodePop2 = DelegatableNodeKt.pop(mutableVector3);
                                                            type$iv7 = type$iv2;
                                                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv2;
                                                        }
                                                    } else {
                                                        type$iv2 = type$iv7;
                                                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3;
                                                        nodePop2 = DelegatableNodeKt.pop(mutableVector3);
                                                        type$iv7 = type$iv2;
                                                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv2;
                                                    }
                                                }
                                            } else {
                                                continue;
                                            }
                                        }
                                    }
                                    node$iv$iv$iv2 = node$iv$iv$iv2.getParent();
                                    $this$nearestAncestor_u2d64DMado$iv5 = $this$nearestAncestor_u2d64DMado$iv5;
                                    i6 = i6;
                                    type$iv7 = type$iv7;
                                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3;
                                } else {
                                    $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv5;
                                    type$iv = type$iv7;
                                    i = i6;
                                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3;
                                }
                            }
                        } else {
                            $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv5;
                            type$iv = type$iv7;
                            i = i6;
                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3;
                        }
                        layout$iv$iv$iv2 = layout$iv$iv$iv2.getParent$ui_release();
                        node$iv$iv$iv2 = (layout$iv$iv$iv2 == null || (nodes = layout$iv$iv$iv2.getNodes()) == null) ? null : nodes.getTail();
                        $this$nearestAncestor_u2d64DMado$iv5 = $this$nearestAncestor_u2d64DMado$iv;
                        i6 = i;
                        type$iv7 = type$iv;
                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv$iv;
                    } else {
                        obj = null;
                    }
                }
                FocusTargetNode focusParent = (FocusTargetNode) obj;
                if (focusParent == null && requestFocusForOwner($this$requestFocusForChild)) {
                    $this$requestFocusForChild.setFocusState(FocusStateImpl.Active);
                    FocusEventModifierNodeKt.refreshFocusEventNodes($this$requestFocusForChild);
                    return requestFocusForChild($this$requestFocusForChild, childNode);
                }
                if (focusParent == null || !requestFocusForChild(focusParent, $this$requestFocusForChild)) {
                    return false;
                }
                boolean zRequestFocusForChild = requestFocusForChild($this$requestFocusForChild, childNode);
                if ($this$requestFocusForChild.getFocusState() == FocusStateImpl.ActiveParent) {
                    return zRequestFocusForChild;
                }
                throw new IllegalStateException("Check failed.".toString());
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final boolean requestFocusForOwner(FocusTargetNode $this$requestFocusForOwner) {
        LayoutNode layoutNode;
        Owner owner;
        NodeCoordinator coordinator$ui_release = $this$requestFocusForOwner.getCoordinator();
        if (coordinator$ui_release == null || (layoutNode = coordinator$ui_release.getLayoutNode()) == null || (owner = layoutNode.getOwner()) == null) {
            throw new IllegalStateException("Owner not initialized.".toString());
        }
        return owner.requestFocus();
    }

    /* JADX INFO: renamed from: performCustomRequestFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m2682performCustomRequestFocusMxy_nc0(FocusTargetNode performCustomRequestFocus, int focusDirection) {
        Modifier.Node node;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
        int type$iv;
        NodeChain nodes;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv2;
        int type$iv2;
        Intrinsics.checkNotNullParameter(performCustomRequestFocus, "$this$performCustomRequestFocus");
        switch (WhenMappings.$EnumSwitchMapping$1[performCustomRequestFocus.getFocusState().ordinal()]) {
            case 1:
            case 2:
                return CustomDestinationResult.None;
            case 3:
                FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(performCustomRequestFocus);
                if (activeChild != null) {
                    return m2679performCustomClearFocusMxy_nc0(activeChild, focusDirection);
                }
                throw new IllegalStateException("Required value was null.".toString());
            case 4:
                FocusTargetNode $this$nearestAncestor_u2d64DMado$iv3 = performCustomRequestFocus;
                int type$iv3 = NodeKind.m4443constructorimpl(1024);
                if (!$this$nearestAncestor_u2d64DMado$iv3.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                }
                Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv3.getNode().getParent();
                LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv3);
                while (true) {
                    int i = 1;
                    if (layout$iv$iv$iv != null) {
                        Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                        if ((head$iv$iv$iv.getAggregateChildKindSet() & type$iv3) != 0) {
                            while (true) {
                                if (node$iv$iv$iv != null) {
                                    if ((node$iv$iv$iv.getKindSet() & type$iv3) != 0) {
                                        Modifier.Node it$iv$iv = node$iv$iv$iv;
                                        MutableVector mutableVector = null;
                                        Modifier.Node nodePop = it$iv$iv;
                                        while (true) {
                                            if (nodePop == null) {
                                                continue;
                                            } else if (nodePop instanceof FocusTargetNode) {
                                                node = nodePop;
                                            } else {
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
                                    }
                                    node$iv$iv$iv = node$iv$iv$iv.getParent();
                                    $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv3;
                                    type$iv3 = type$iv3;
                                    i = 1;
                                } else {
                                    $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                                    type$iv = type$iv3;
                                }
                            }
                        } else {
                            $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv3;
                            type$iv = type$iv3;
                        }
                        layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui_release();
                        node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes = layout$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
                        $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv;
                        type$iv3 = type$iv;
                    } else {
                        node = null;
                    }
                }
                FocusTargetNode focusParent = (FocusTargetNode) node;
                if (focusParent == null) {
                    return CustomDestinationResult.None;
                }
                switch (WhenMappings.$EnumSwitchMapping$1[focusParent.getFocusState().ordinal()]) {
                    case 1:
                        return m2680performCustomEnterMxy_nc0(focusParent, focusDirection);
                    case 2:
                        return CustomDestinationResult.Cancelled;
                    case 3:
                        return m2682performCustomRequestFocusMxy_nc0(focusParent, focusDirection);
                    case 4:
                        CustomDestinationResult it = m2682performCustomRequestFocusMxy_nc0(focusParent, focusDirection);
                        CustomDestinationResult customDestinationResult = !(it == CustomDestinationResult.None) ? it : null;
                        return customDestinationResult == null ? m2680performCustomEnterMxy_nc0(focusParent, focusDirection) : customDestinationResult;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: renamed from: performCustomClearFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m2679performCustomClearFocusMxy_nc0(FocusTargetNode performCustomClearFocus, int focusDirection) {
        Intrinsics.checkNotNullParameter(performCustomClearFocus, "$this$performCustomClearFocus");
        switch (WhenMappings.$EnumSwitchMapping$1[performCustomClearFocus.getFocusState().ordinal()]) {
            case 1:
            case 4:
                return CustomDestinationResult.None;
            case 2:
                return CustomDestinationResult.Cancelled;
            case 3:
                FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(performCustomClearFocus);
                if (activeChild != null) {
                    CustomDestinationResult it = m2679performCustomClearFocusMxy_nc0(activeChild, focusDirection);
                    if (it == CustomDestinationResult.None) {
                        it = null;
                    }
                    if (it == null) {
                        return m2681performCustomExitMxy_nc0(performCustomClearFocus, focusDirection);
                    }
                    return it;
                }
                throw new IllegalStateException("Required value was null.".toString());
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: renamed from: performCustomEnter-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m2680performCustomEnterMxy_nc0(FocusTargetNode $this$performCustomEnter_u2dMxy_nc0, int focusDirection) {
        CustomDestinationResult customDestinationResult;
        if (!$this$performCustomEnter_u2dMxy_nc0.isProcessingCustomEnter) {
            $this$performCustomEnter_u2dMxy_nc0.isProcessingCustomEnter = true;
            try {
                FocusRequester it$iv = $this$performCustomEnter_u2dMxy_nc0.fetchFocusProperties$ui_release().getEnter().invoke(FocusDirection.m2647boximpl(focusDirection));
                if (it$iv != FocusRequester.INSTANCE.getDefault()) {
                    if (it$iv == FocusRequester.INSTANCE.getCancel()) {
                        customDestinationResult = CustomDestinationResult.Cancelled;
                    } else {
                        customDestinationResult = it$iv.focus$ui_release() ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                    }
                    return customDestinationResult;
                }
            } finally {
                $this$performCustomEnter_u2dMxy_nc0.isProcessingCustomEnter = false;
            }
        }
        return CustomDestinationResult.None;
    }

    /* JADX INFO: renamed from: performCustomExit-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m2681performCustomExitMxy_nc0(FocusTargetNode $this$performCustomExit_u2dMxy_nc0, int focusDirection) {
        CustomDestinationResult customDestinationResult;
        if (!$this$performCustomExit_u2dMxy_nc0.isProcessingCustomExit) {
            $this$performCustomExit_u2dMxy_nc0.isProcessingCustomExit = true;
            try {
                FocusRequester it$iv = $this$performCustomExit_u2dMxy_nc0.fetchFocusProperties$ui_release().getExit().invoke(FocusDirection.m2647boximpl(focusDirection));
                if (it$iv != FocusRequester.INSTANCE.getDefault()) {
                    if (it$iv == FocusRequester.INSTANCE.getCancel()) {
                        customDestinationResult = CustomDestinationResult.Cancelled;
                    } else {
                        customDestinationResult = it$iv.focus$ui_release() ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                    }
                    return customDestinationResult;
                }
            } finally {
                $this$performCustomExit_u2dMxy_nc0.isProcessingCustomExit = false;
            }
        }
        return CustomDestinationResult.None;
    }
}
