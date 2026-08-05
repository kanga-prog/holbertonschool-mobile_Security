package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BeyondBoundsLayout.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aD\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\u0010\u0005\u001a\u0015\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0006¢\u0006\u0002\b\bH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"searchBeyondBounds", "T", "Landroidx/compose/ui/focus/FocusTargetNode;", "direction", "Landroidx/compose/ui/focus/FocusDirection;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/BeyondBoundsLayout$BeyondBoundsScope;", "Lkotlin/ExtensionFunctionType;", "searchBeyondBounds--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BeyondBoundsLayoutKt {
    /* JADX INFO: renamed from: searchBeyondBounds--OM-vw8, reason: not valid java name */
    public static final <T> T m2646searchBeyondBoundsOMvw8(FocusTargetNode searchBeyondBounds, int i, Function1<? super BeyondBoundsLayout.BeyondBoundsScope, ? extends T> block) {
        Modifier.Node node;
        int iM4211getBeforehoxUOeE;
        FocusTargetNode focusTargetNode;
        int i2;
        int i3;
        NodeChain nodes;
        FocusTargetNode focusTargetNode2;
        int i4;
        int i5;
        FocusTargetNode focusTargetNode3;
        Intrinsics.checkNotNullParameter(searchBeyondBounds, "$this$searchBeyondBounds");
        Intrinsics.checkNotNullParameter(block, "block");
        FocusTargetNode focusTargetNode4 = searchBeyondBounds;
        int iM4443constructorimpl = NodeKind.m4443constructorimpl(1024);
        int i6 = 0;
        if (!focusTargetNode4.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node parent = focusTargetNode4.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode4);
        loop0: while (true) {
            if (layoutNodeRequireLayoutNode == null) {
                node = null;
                break;
            }
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM4443constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4443constructorimpl) != 0) {
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = parent;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                node = nodePop;
                                break loop0;
                            }
                            if (((nodePop.getKindSet() & iM4443constructorimpl) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                focusTargetNode2 = focusTargetNode4;
                                i4 = iM4443constructorimpl;
                                i5 = i6;
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                                focusTargetNode4 = focusTargetNode2;
                                iM4443constructorimpl = i4;
                                i6 = i5;
                            } else {
                                int i7 = 0;
                                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                while (delegate != null) {
                                    Modifier.Node node2 = delegate;
                                    if ((node2.getKindSet() & iM4443constructorimpl) != 0) {
                                        i7++;
                                        focusTargetNode3 = focusTargetNode4;
                                        if (i7 == 1) {
                                            nodePop = node2;
                                            iM4443constructorimpl = iM4443constructorimpl;
                                            i6 = i6;
                                        } else {
                                            MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                            Modifier.Node node3 = nodePop;
                                            if (node3 != null) {
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(node3);
                                                }
                                                nodePop = null;
                                            }
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(node2);
                                            }
                                            mutableVector = mutableVector2;
                                            i7 = i7;
                                        }
                                    } else {
                                        focusTargetNode3 = focusTargetNode4;
                                        iM4443constructorimpl = iM4443constructorimpl;
                                        i6 = i6;
                                    }
                                    delegate = delegate.getChild();
                                    focusTargetNode4 = focusTargetNode3;
                                    iM4443constructorimpl = iM4443constructorimpl;
                                    i6 = i6;
                                }
                                focusTargetNode2 = focusTargetNode4;
                                i4 = iM4443constructorimpl;
                                i5 = i6;
                                if (i7 == 1) {
                                    focusTargetNode4 = focusTargetNode2;
                                    iM4443constructorimpl = i4;
                                    i6 = i5;
                                } else {
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    focusTargetNode4 = focusTargetNode2;
                                    iM4443constructorimpl = i4;
                                    i6 = i5;
                                }
                            }
                        }
                    }
                    parent = parent.getParent();
                    focusTargetNode4 = focusTargetNode4;
                    iM4443constructorimpl = iM4443constructorimpl;
                    i6 = i6;
                }
                focusTargetNode = focusTargetNode4;
                i2 = iM4443constructorimpl;
                i3 = i6;
            } else {
                focusTargetNode = focusTargetNode4;
                i2 = iM4443constructorimpl;
                i3 = i6;
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
            focusTargetNode4 = focusTargetNode;
            iM4443constructorimpl = i2;
            i6 = i3;
        }
        FocusTargetNode focusTargetNode5 = (FocusTargetNode) node;
        if (focusTargetNode5 != null && Intrinsics.areEqual(focusTargetNode5.getBeyondBoundsLayoutParent(), searchBeyondBounds.getBeyondBoundsLayoutParent())) {
            return null;
        }
        T t = null;
        BeyondBoundsLayout beyondBoundsLayoutParent = searchBeyondBounds.getBeyondBoundsLayoutParent();
        if (beyondBoundsLayoutParent == null) {
            return t;
        }
        if (FocusDirection.m2650equalsimpl0(i, FocusDirection.INSTANCE.m2667getUpdhqQ8s())) {
            iM4211getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4209getAbovehoxUOeE();
        } else if (FocusDirection.m2650equalsimpl0(i, FocusDirection.INSTANCE.m2658getDowndhqQ8s())) {
            iM4211getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4212getBelowhoxUOeE();
        } else if (FocusDirection.m2650equalsimpl0(i, FocusDirection.INSTANCE.m2662getLeftdhqQ8s())) {
            iM4211getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4213getLefthoxUOeE();
        } else if (FocusDirection.m2650equalsimpl0(i, FocusDirection.INSTANCE.m2666getRightdhqQ8s())) {
            iM4211getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4214getRighthoxUOeE();
        } else if (FocusDirection.m2650equalsimpl0(i, FocusDirection.INSTANCE.m2663getNextdhqQ8s())) {
            iM4211getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4210getAfterhoxUOeE();
        } else {
            if (!FocusDirection.m2650equalsimpl0(i, FocusDirection.INSTANCE.m2665getPreviousdhqQ8s())) {
                throw new IllegalStateException("Unsupported direction for beyond bounds layout".toString());
            }
            iM4211getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4211getBeforehoxUOeE();
        }
        return (T) beyondBoundsLayoutParent.mo641layouto7g1Pn8(iM4211getBeforehoxUOeE, block);
    }
}
