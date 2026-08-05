package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusRequesterModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0005"}, d2 = {"captureFocus", "", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "freeFocus", "requestFocus", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FocusRequesterModifierNodeKt {
    public static final boolean requestFocus(FocusRequesterModifierNode $this$requestFocus) {
        int type$iv;
        DelegatingNode this_$iv$iv$iv;
        int i;
        Intrinsics.checkNotNullParameter($this$requestFocus, "<this>");
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$requestFocus;
        int type$iv2 = NodeKind.m4443constructorimpl(1024);
        int i2 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv.getNode();
        MutableVector mutableVector = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            int i3 = 1;
            if (nodePop == null) {
                int mask$iv$iv = type$iv2;
                if (!$this$visitSelfAndChildren_u2d6rFNWt0$iv.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitChildren called on an unattached node".toString());
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSelfAndChildren_u2d6rFNWt0$iv.getNode());
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (branches$iv$iv.isNotEmpty()) {
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector2 = null;
                                Modifier.Node this_$iv$iv$iv2 = it$iv;
                                while (this_$iv$iv$iv2 != null) {
                                    if (this_$iv$iv$iv2 instanceof FocusTargetNode) {
                                        FocusTargetNode focusTarget = (FocusTargetNode) this_$iv$iv$iv2;
                                        FocusProperties focusProperties = focusTarget.fetchFocusProperties$ui_release();
                                        if (focusProperties.getCanFocus()) {
                                            return FocusTransactionsKt.requestFocus(focusTarget);
                                        }
                                        int mask$iv$iv2 = FocusDirection.INSTANCE.m2659getEnterdhqQ8s();
                                        return TwoDimensionalFocusSearchKt.m2692findChildCorrespondingToFocusEnterOMvw8(focusTarget, mask$iv$iv2, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusRequesterModifierNodeKt$requestFocus$1$1
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Boolean invoke(FocusTargetNode it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                return Boolean.valueOf(FocusTransactionsKt.requestFocus(it));
                                            }
                                        });
                                    }
                                    mask$iv$iv = mask$iv$iv;
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                                    if (((this_$iv$iv$iv2.getKindSet() & type$iv2) != 0) && (this_$iv$iv$iv2 instanceof DelegatingNode)) {
                                        int count$iv$iv = 0;
                                        DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv2;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv3.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            if ((next$iv$iv.getKindSet() & type$iv2) != 0) {
                                                count$iv$iv++;
                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                                if (count$iv$iv == 1) {
                                                    this_$iv$iv$iv2 = next$iv$iv;
                                                    type$iv2 = type$iv2;
                                                } else {
                                                    mutableVector2 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                    Modifier.Node theNode$iv$iv = this_$iv$iv$iv2;
                                                    if (theNode$iv$iv != null) {
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv$iv);
                                                        }
                                                        this_$iv$iv$iv2 = null;
                                                    }
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(next$iv$iv);
                                                    }
                                                    count$iv$iv = count$iv$iv;
                                                }
                                            } else {
                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                                type$iv2 = type$iv2;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            this_$iv$iv$iv3 = this_$iv$iv$iv;
                                            type$iv2 = type$iv2;
                                        }
                                        type$iv = type$iv2;
                                        if (count$iv$iv == 1) {
                                            type$iv2 = type$iv;
                                        } else {
                                            this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector2);
                                            type$iv2 = type$iv;
                                        }
                                    } else {
                                        type$iv = type$iv2;
                                        this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector2);
                                        type$iv2 = type$iv;
                                    }
                                }
                                $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                                break;
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
                    }
                }
                return false;
            }
            if (nodePop instanceof FocusTargetNode) {
                FocusTargetNode focusTarget2 = (FocusTargetNode) nodePop;
                FocusProperties focusProperties2 = focusTarget2.fetchFocusProperties$ui_release();
                return focusProperties2.getCanFocus() ? FocusTransactionsKt.requestFocus(focusTarget2) : TwoDimensionalFocusSearchKt.m2692findChildCorrespondingToFocusEnterOMvw8(focusTarget2, FocusDirection.INSTANCE.m2659getEnterdhqQ8s(), new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusRequesterModifierNodeKt$requestFocus$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(FocusTargetNode it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Boolean.valueOf(FocusTransactionsKt.requestFocus(it));
                    }
                });
            }
            Modifier.Node this_$iv$iv$iv4 = nodePop;
            if (((this_$iv$iv$iv4.getKindSet() & type$iv2) != 0) && (nodePop instanceof DelegatingNode)) {
                int count$iv$iv2 = 0;
                DelegatingNode this_$iv$iv$iv5 = (DelegatingNode) nodePop;
                Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv5.getDelegate();
                while (node$iv$iv$iv2 != null) {
                    Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                    if ((next$iv$iv2.getKindSet() & type$iv2) != 0) {
                        count$iv$iv2++;
                        if (count$iv$iv2 == i3) {
                            nodePop = next$iv$iv2;
                            i2 = i2;
                        } else {
                            mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                            Modifier.Node theNode$iv$iv2 = nodePop;
                            if (theNode$iv$iv2 != null) {
                                if (mutableVector != null) {
                                    mutableVector.add(theNode$iv$iv2);
                                }
                                nodePop = null;
                            }
                            if (mutableVector != null) {
                                mutableVector.add(next$iv$iv2);
                            }
                        }
                    } else {
                        i2 = i2;
                    }
                    node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                    i3 = 1;
                    i2 = i2;
                }
                i = i2;
                if (count$iv$iv2 == 1) {
                    i2 = i;
                } else {
                    nodePop = DelegatableNodeKt.pop(mutableVector);
                    i2 = i;
                }
            } else {
                i = i2;
                nodePop = DelegatableNodeKt.pop(mutableVector);
                i2 = i;
            }
        }
    }

    public static final boolean captureFocus(FocusRequesterModifierNode $this$captureFocus) {
        int mask$iv$iv;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv;
        int type$iv;
        int mask$iv$iv2;
        int i;
        Intrinsics.checkNotNullParameter($this$captureFocus, "<this>");
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$captureFocus;
        int type$iv2 = NodeKind.m4443constructorimpl(1024);
        int i2 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv2.getNode();
        MutableVector mutableVector = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            int i3 = 1;
            if (nodePop == null) {
                int mask$iv$iv3 = type$iv2;
                if (!$this$visitSelfAndChildren_u2d6rFNWt0$iv2.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitChildren called on an unattached node".toString());
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv2.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSelfAndChildren_u2d6rFNWt0$iv2.getNode());
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (branches$iv$iv.isNotEmpty()) {
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                mask$iv$iv3 = mask$iv$iv3;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector2 = null;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    if (nodePop2 instanceof FocusTargetNode) {
                                        FocusTargetNode it = (FocusTargetNode) nodePop2;
                                        if (FocusTransactionsKt.captureFocus(it)) {
                                            return true;
                                        }
                                        mask$iv$iv = mask$iv$iv3;
                                        $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                                        type$iv = type$iv2;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv = nodePop2;
                                        if (((this_$iv$iv$iv.getKindSet() & type$iv2) != 0) && (nodePop2 instanceof DelegatingNode)) {
                                            int count$iv$iv = 0;
                                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop2;
                                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                            while (node$iv$iv$iv != null) {
                                                Modifier.Node next$iv$iv = node$iv$iv$iv;
                                                if ((next$iv$iv.getKindSet() & type$iv2) != 0) {
                                                    count$iv$iv++;
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    if (count$iv$iv == 1) {
                                                        nodePop2 = next$iv$iv;
                                                        $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                                                        type$iv2 = type$iv2;
                                                    } else {
                                                        mutableVector2 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                        Modifier.Node theNode$iv$iv = nodePop2;
                                                        if (theNode$iv$iv != null) {
                                                            if (mutableVector2 != null) {
                                                                mutableVector2.add(theNode$iv$iv);
                                                            }
                                                            nodePop2 = null;
                                                        }
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(next$iv$iv);
                                                        }
                                                    }
                                                } else {
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                                                    type$iv2 = type$iv2;
                                                }
                                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                                mask$iv$iv3 = mask$iv$iv2;
                                                $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                                                type$iv2 = type$iv2;
                                            }
                                            mask$iv$iv = mask$iv$iv3;
                                            $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                                            type$iv = type$iv2;
                                            if (count$iv$iv == 1) {
                                                mask$iv$iv3 = mask$iv$iv;
                                                $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                                                type$iv2 = type$iv;
                                            }
                                        } else {
                                            mask$iv$iv = mask$iv$iv3;
                                            $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                                            type$iv = type$iv2;
                                        }
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                                    mask$iv$iv3 = mask$iv$iv;
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                                    type$iv2 = type$iv;
                                }
                                mask$iv$iv3 = mask$iv$iv3;
                                break;
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            mask$iv$iv3 = mask$iv$iv3;
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
                    }
                }
                return false;
            }
            if (nodePop instanceof FocusTargetNode) {
                FocusTargetNode it2 = (FocusTargetNode) nodePop;
                if (FocusTransactionsKt.captureFocus(it2)) {
                    return true;
                }
                i = i2;
            } else {
                Modifier.Node this_$iv$iv$iv3 = nodePop;
                if (((this_$iv$iv$iv3.getKindSet() & type$iv2) != 0) && (nodePop instanceof DelegatingNode)) {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv4.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                        if ((next$iv$iv2.getKindSet() & type$iv2) != 0) {
                            count$iv$iv2++;
                            if (count$iv$iv2 == i3) {
                                nodePop = next$iv$iv2;
                                i2 = i2;
                            } else {
                                mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                Modifier.Node theNode$iv$iv2 = nodePop;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector != null) {
                                        mutableVector.add(theNode$iv$iv2);
                                    }
                                    nodePop = null;
                                }
                                if (mutableVector != null) {
                                    mutableVector.add(next$iv$iv2);
                                }
                            }
                        } else {
                            i2 = i2;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        i3 = 1;
                        i2 = i2;
                    }
                    i = i2;
                    if (count$iv$iv2 == 1) {
                        i2 = i;
                    }
                } else {
                    i = i2;
                }
            }
            nodePop = DelegatableNodeKt.pop(mutableVector);
            i2 = i;
        }
    }

    public static final boolean freeFocus(FocusRequesterModifierNode $this$freeFocus) {
        int mask$iv$iv;
        DelegatableNode $this$visitSelfAndChildren_u2d6rFNWt0$iv;
        int type$iv;
        int mask$iv$iv2;
        int i;
        Intrinsics.checkNotNullParameter($this$freeFocus, "<this>");
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$freeFocus;
        int type$iv2 = NodeKind.m4443constructorimpl(1024);
        int i2 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv2.getNode();
        MutableVector mutableVector = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            int i3 = 1;
            if (nodePop == null) {
                int mask$iv$iv3 = type$iv2;
                if (!$this$visitSelfAndChildren_u2d6rFNWt0$iv2.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitChildren called on an unattached node".toString());
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv2.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSelfAndChildren_u2d6rFNWt0$iv2.getNode());
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (branches$iv$iv.isNotEmpty()) {
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                mask$iv$iv3 = mask$iv$iv3;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector2 = null;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    if (nodePop2 instanceof FocusTargetNode) {
                                        FocusTargetNode it = (FocusTargetNode) nodePop2;
                                        if (FocusTransactionsKt.freeFocus(it)) {
                                            return true;
                                        }
                                        mask$iv$iv = mask$iv$iv3;
                                        $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                                        type$iv = type$iv2;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv = nodePop2;
                                        if (((this_$iv$iv$iv.getKindSet() & type$iv2) != 0) && (nodePop2 instanceof DelegatingNode)) {
                                            int count$iv$iv = 0;
                                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop2;
                                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                            while (node$iv$iv$iv != null) {
                                                Modifier.Node next$iv$iv = node$iv$iv$iv;
                                                if ((next$iv$iv.getKindSet() & type$iv2) != 0) {
                                                    count$iv$iv++;
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    if (count$iv$iv == 1) {
                                                        nodePop2 = next$iv$iv;
                                                        $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                                                        type$iv2 = type$iv2;
                                                    } else {
                                                        mutableVector2 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                        Modifier.Node theNode$iv$iv = nodePop2;
                                                        if (theNode$iv$iv != null) {
                                                            if (mutableVector2 != null) {
                                                                mutableVector2.add(theNode$iv$iv);
                                                            }
                                                            nodePop2 = null;
                                                        }
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(next$iv$iv);
                                                        }
                                                    }
                                                } else {
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                                                    type$iv2 = type$iv2;
                                                }
                                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                                mask$iv$iv3 = mask$iv$iv2;
                                                $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                                                type$iv2 = type$iv2;
                                            }
                                            mask$iv$iv = mask$iv$iv3;
                                            $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                                            type$iv = type$iv2;
                                            if (count$iv$iv == 1) {
                                                mask$iv$iv3 = mask$iv$iv;
                                                $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                                                type$iv2 = type$iv;
                                            }
                                        } else {
                                            mask$iv$iv = mask$iv$iv3;
                                            $this$visitSelfAndChildren_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2d6rFNWt0$iv2;
                                            type$iv = type$iv2;
                                        }
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                                    mask$iv$iv3 = mask$iv$iv;
                                    $this$visitSelfAndChildren_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2d6rFNWt0$iv;
                                    type$iv2 = type$iv;
                                }
                                mask$iv$iv3 = mask$iv$iv3;
                                break;
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            mask$iv$iv3 = mask$iv$iv3;
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
                    }
                }
                return false;
            }
            if (nodePop instanceof FocusTargetNode) {
                FocusTargetNode it2 = (FocusTargetNode) nodePop;
                if (FocusTransactionsKt.freeFocus(it2)) {
                    return true;
                }
                i = i2;
            } else {
                Modifier.Node this_$iv$iv$iv3 = nodePop;
                if (((this_$iv$iv$iv3.getKindSet() & type$iv2) != 0) && (nodePop instanceof DelegatingNode)) {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv4.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                        if ((next$iv$iv2.getKindSet() & type$iv2) != 0) {
                            count$iv$iv2++;
                            if (count$iv$iv2 == i3) {
                                nodePop = next$iv$iv2;
                                i2 = i2;
                            } else {
                                mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                Modifier.Node theNode$iv$iv2 = nodePop;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector != null) {
                                        mutableVector.add(theNode$iv$iv2);
                                    }
                                    nodePop = null;
                                }
                                if (mutableVector != null) {
                                    mutableVector.add(next$iv$iv2);
                                }
                            }
                        } else {
                            i2 = i2;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        i3 = 1;
                        i2 = i2;
                    }
                    i = i2;
                    if (count$iv$iv2 == 1) {
                        i2 = i;
                    }
                } else {
                    i = i2;
                }
            }
            nodePop = DelegatableNodeKt.pop(mutableVector);
            i2 = i;
        }
    }
}
