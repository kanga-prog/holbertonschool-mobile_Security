package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DelegatableNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\u001a\u001a\u0010\u0004\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0002\u001a8\u0010\t\u001a\n\u0012\u0004\u0012\u0002H\u000b\u0018\u00010\n\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\u0080\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u000e\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u0007H\u0000\u001aG\u0010\u0012\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a%\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001a\u001a\n\u0010\u001b\u001a\u00020\u0005*\u00020\u0002\u001a6\u0010\u001c\u001a\u0004\u0018\u0001H\u000b\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u001d*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\u0080\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u0007*\u00020\u00022\u0006\u0010 \u001a\u00020!H\u0000\u001a\u0016\u0010\"\u001a\u0004\u0018\u00010\u0007*\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0002\u001a%\u0010#\u001a\u00020$*\u00020\u00022\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\rH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010&\u001a\n\u0010'\u001a\u00020(*\u00020\u0002\u001a\n\u0010)\u001a\u00020**\u00020\u0002\u001a\f\u0010+\u001a\u00020,*\u00020\u0002H\u0000\u001a\f\u0010-\u001a\u00020.*\u00020\u0002H\u0000\u001aQ\u0010/\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\b\b\u0002\u00100\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b1\u00102\u001a6\u0010/\u001a\u00020\u0005*\u00020\u00022\u0006\u0010 \u001a\u00020!2\b\b\u0002\u00100\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002\u001aG\u00103\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b4\u00105\u001a,\u00103\u001a\u00020\u0005*\u00020\u00022\u0006\u0010 \u001a\u00020!2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002\u001aG\u00106\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b7\u00105\u001a,\u00106\u001a\u00020\u0005*\u00020\u00022\u0006\u0010 \u001a\u00020!2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002\u001aG\u00108\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b9\u00105\u001a,\u00108\u001a\u00020\u0005*\u00020\u00022\u0006\u0010 \u001a\u00020!2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002\u001aS\u0010:\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\n\u0010;\u001a\u0006\u0012\u0002\b\u00030\r2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b<\u0010=\u001aG\u0010>\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b?\u00105\u001aG\u0010@\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bA\u00105\u001a,\u0010@\u001a\u00020\u0005*\u00020\u00022\u0006\u0010 \u001a\u00020!2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0015H\u0080\bø\u0001\u0002\u001aG\u0010B\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u000b\u0018\u0001*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00010\u0015H\u0080\bø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bC\u00105\u001a,\u0010B\u001a\u00020\u0005*\u00020\u00022\u0006\u0010 \u001a\u00020!2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0015H\u0080\bø\u0001\u0002\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\u0082\u0002\u0012\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u0006D"}, d2 = {"isDelegationRoot", "", "Landroidx/compose/ui/node/DelegatableNode;", "(Landroidx/compose/ui/node/DelegatableNode;)Z", "addLayoutNodeChildren", "", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/Modifier$Node;", "node", "ancestors", "", "T", "type", "Landroidx/compose/ui/node/NodeKind;", "ancestors-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Ljava/util/List;", "asLayoutModifierNode", "Landroidx/compose/ui/node/LayoutModifierNode;", "dispatchForKind", "kind", "block", "Lkotlin/Function1;", "dispatchForKind-6rFNWt0", "(Landroidx/compose/ui/Modifier$Node;ILkotlin/jvm/functions/Function1;)V", "has", "has-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Z", "invalidateSubtree", "nearestAncestor", "", "nearestAncestor-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Ljava/lang/Object;", "mask", "", "pop", "requireCoordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "requireCoordinator-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Landroidx/compose/ui/node/NodeCoordinator;", "requireDensity", "Landroidx/compose/ui/unit/Density;", "requireLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "requireLayoutNode", "Landroidx/compose/ui/node/LayoutNode;", "requireOwner", "Landroidx/compose/ui/node/Owner;", "visitAncestors", "includeSelf", "visitAncestors-Y-YKmho", "(Landroidx/compose/ui/node/DelegatableNode;IZLkotlin/jvm/functions/Function1;)V", "visitChildren", "visitChildren-6rFNWt0", "(Landroidx/compose/ui/node/DelegatableNode;ILkotlin/jvm/functions/Function1;)V", "visitLocalAncestors", "visitLocalAncestors-6rFNWt0", "visitLocalDescendants", "visitLocalDescendants-6rFNWt0", "visitSelfAndAncestors", "untilType", "visitSelfAndAncestors-5BbP62I", "(Landroidx/compose/ui/node/DelegatableNode;IILkotlin/jvm/functions/Function1;)V", "visitSelfAndChildren", "visitSelfAndChildren-6rFNWt0", "visitSubtree", "visitSubtree-6rFNWt0", "visitSubtreeIf", "visitSubtreeIf-6rFNWt0", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DelegatableNodeKt {
    public static final boolean isDelegationRoot(DelegatableNode $this$isDelegationRoot) {
        Intrinsics.checkNotNullParameter($this$isDelegationRoot, "<this>");
        return $this$isDelegationRoot.getNode() == $this$isDelegationRoot;
    }

    public static /* synthetic */ void visitAncestors$default(DelegatableNode $this$visitAncestors_u24default, int mask, boolean includeSelf, Function1 block, int i, Object obj) {
        NodeChain nodes;
        if ((i & 2) != 0) {
            includeSelf = false;
        }
        Intrinsics.checkNotNullParameter($this$visitAncestors_u24default, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (!$this$visitAncestors_u24default.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node = $this$visitAncestors_u24default.getNode();
        if (!includeSelf) {
            node = node.getParent();
        }
        LayoutNode layout = requireLayoutNode($this$visitAncestors_u24default);
        while (layout != null) {
            Modifier.Node head = layout.getNodes().getHead();
            if ((head.getAggregateChildKindSet() & mask) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & mask) != 0) {
                        block.invoke(node);
                    }
                    node = node.getParent();
                }
            }
            layout = layout.getParent$ui_release();
            node = (layout == null || (nodes = layout.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    public static final void visitAncestors(DelegatableNode $this$visitAncestors, int mask, boolean includeSelf, Function1<? super Modifier.Node, Unit> block) {
        NodeChain nodes;
        Intrinsics.checkNotNullParameter($this$visitAncestors, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (!$this$visitAncestors.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node = $this$visitAncestors.getNode();
        if (!includeSelf) {
            node = node.getParent();
        }
        LayoutNode layout = requireLayoutNode($this$visitAncestors);
        while (layout != null) {
            Modifier.Node head = layout.getNodes().getHead();
            if ((head.getAggregateChildKindSet() & mask) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & mask) != 0) {
                        block.invoke(node);
                    }
                    node = node.getParent();
                }
            }
            layout = layout.getParent$ui_release();
            node = (layout == null || (nodes = layout.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    public static final Modifier.Node nearestAncestor(DelegatableNode $this$nearestAncestor, int mask) {
        NodeChain nodes;
        Intrinsics.checkNotNullParameter($this$nearestAncestor, "<this>");
        if (!$this$nearestAncestor.getNode().getIsAttached()) {
            throw new IllegalStateException("nearestAncestor called on an unattached node".toString());
        }
        Modifier.Node node = $this$nearestAncestor.getNode().getParent();
        LayoutNode layout = requireLayoutNode($this$nearestAncestor);
        while (true) {
            Modifier.Node tail = null;
            if (layout == null) {
                return null;
            }
            Modifier.Node head = layout.getNodes().getHead();
            if ((head.getAggregateChildKindSet() & mask) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & mask) != 0) {
                        return node;
                    }
                    node = node.getParent();
                }
            }
            layout = layout.getParent$ui_release();
            if (layout != null && (nodes = layout.getNodes()) != null) {
                tail = nodes.getTail();
            }
            node = tail;
        }
    }

    public static final void visitSubtree(DelegatableNode $this$visitSubtree, int mask, Function1<? super Modifier.Node, Unit> block) {
        Intrinsics.checkNotNullParameter($this$visitSubtree, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (!$this$visitSubtree.getNode().getIsAttached()) {
            throw new IllegalStateException("visitSubtree called on an unattached node".toString());
        }
        Modifier.Node node = $this$visitSubtree.getNode().getChild();
        NestedVectorStack nodes = new NestedVectorStack();
        for (LayoutNode layout = requireLayoutNode($this$visitSubtree); layout != null; layout = nodes.isNotEmpty() ? (LayoutNode) nodes.pop() : null) {
            Modifier.Node node2 = node == null ? layout.getNodes().getHead() : node;
            if ((node2.getAggregateChildKindSet() & mask) != 0) {
                while (node2 != null) {
                    if ((node2.getKindSet() & mask) != 0) {
                        block.invoke(node2);
                    }
                    node2 = node2.getChild();
                }
            }
            node = null;
            nodes.push(layout.get_children$ui_release());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addLayoutNodeChildren(MutableVector<Modifier.Node> mutableVector, Modifier.Node node) {
        MutableVector<LayoutNode> mutableVector2 = requireLayoutNode(node).get_children$ui_release();
        int size$iv = mutableVector2.getSize();
        if (size$iv <= 0) {
            return;
        }
        int i$iv = size$iv - 1;
        Object[] content$iv = mutableVector2.getContent();
        do {
            LayoutNode it = (LayoutNode) content$iv[i$iv];
            mutableVector.add(it.getNodes().getHead());
            i$iv--;
        } while (i$iv >= 0);
    }

    public static final void visitChildren(DelegatableNode $this$visitChildren, int mask, Function1<? super Modifier.Node, Unit> block) {
        Intrinsics.checkNotNullParameter($this$visitChildren, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (!$this$visitChildren.getNode().getIsAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node".toString());
        }
        MutableVector branches = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = $this$visitChildren.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(branches, $this$visitChildren.getNode());
        } else {
            branches.add(child);
        }
        while (branches.isNotEmpty()) {
            Modifier.Node branch = (Modifier.Node) branches.removeAt(branches.getSize() - 1);
            if ((branch.getAggregateChildKindSet() & mask) == 0) {
                addLayoutNodeChildren(branches, branch);
            } else {
                for (Modifier.Node node = branch; node != null; node = node.getChild()) {
                    if ((node.getKindSet() & mask) != 0) {
                        block.invoke(node);
                        break;
                    }
                }
            }
        }
    }

    public static final void visitSubtreeIf(DelegatableNode $this$visitSubtreeIf, int mask, Function1<? super Modifier.Node, Boolean> block) {
        Intrinsics.checkNotNullParameter($this$visitSubtreeIf, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (!$this$visitSubtreeIf.getNode().getIsAttached()) {
            throw new IllegalStateException("visitSubtreeIf called on an unattached node".toString());
        }
        MutableVector branches = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = $this$visitSubtreeIf.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(branches, $this$visitSubtreeIf.getNode());
        } else {
            branches.add(child);
        }
        while (branches.isNotEmpty()) {
            Modifier.Node branch = (Modifier.Node) branches.removeAt(branches.getSize() - 1);
            if ((branch.getAggregateChildKindSet() & mask) != 0) {
                Modifier.Node node = branch;
                while (true) {
                    if (node != null) {
                        if ((node.getKindSet() & mask) != 0) {
                            boolean diveDeeper = block.invoke(node).booleanValue();
                            if (!diveDeeper) {
                                break;
                            }
                        }
                        node = node.getChild();
                    }
                }
            }
            addLayoutNodeChildren(branches, branch);
        }
    }

    public static final void visitLocalDescendants(DelegatableNode $this$visitLocalDescendants, int mask, Function1<? super Modifier.Node, Unit> block) {
        Intrinsics.checkNotNullParameter($this$visitLocalDescendants, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (!$this$visitLocalDescendants.getNode().getIsAttached()) {
            throw new IllegalStateException("visitLocalDescendants called on an unattached node".toString());
        }
        Modifier.Node self = $this$visitLocalDescendants.getNode();
        if ((self.getAggregateChildKindSet() & mask) == 0) {
            return;
        }
        for (Modifier.Node next = self.getChild(); next != null; next = next.getChild()) {
            if ((next.getKindSet() & mask) != 0) {
                block.invoke(next);
            }
        }
    }

    public static final void visitLocalAncestors(DelegatableNode $this$visitLocalAncestors, int mask, Function1<? super Modifier.Node, Unit> block) {
        Intrinsics.checkNotNullParameter($this$visitLocalAncestors, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (!$this$visitLocalAncestors.getNode().getIsAttached()) {
            throw new IllegalStateException("visitLocalAncestors called on an unattached node".toString());
        }
        for (Modifier.Node next = $this$visitLocalAncestors.getNode().getParent(); next != null; next = next.getParent()) {
            if ((next.getKindSet() & mask) != 0) {
                block.invoke(next);
            }
        }
    }

    /* JADX INFO: renamed from: visitLocalDescendants-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m4345visitLocalDescendants6rFNWt0(DelegatableNode visitLocalDescendants, int type, Function1<? super T, Unit> block) {
        int i;
        int mask$iv;
        int i2;
        Object mutableVector;
        block = block;
        Intrinsics.checkNotNullParameter(visitLocalDescendants, "$this$visitLocalDescendants");
        Intrinsics.checkNotNullParameter(block, "block");
        int i3 = 0;
        int mask$iv2 = type;
        if (!visitLocalDescendants.getNode().getIsAttached()) {
            throw new IllegalStateException("visitLocalDescendants called on an unattached node".toString());
        }
        Modifier.Node self$iv = visitLocalDescendants.getNode();
        if ((self$iv.getAggregateChildKindSet() & mask$iv2) == 0) {
            return;
        }
        Modifier.Node next$iv = self$iv.getChild();
        while (next$iv != null) {
            if ((next$iv.getKindSet() & mask$iv2) != 0) {
                Object it = next$iv;
                Object stack$iv = null;
                Object node$iv = it;
                while (node$iv != null) {
                    Intrinsics.reifiedOperationMarker(3, "T");
                    if (node$iv instanceof Object) {
                        block.invoke(node$iv);
                        i = i3;
                        mask$iv = mask$iv2;
                    } else {
                        Modifier.Node this_$iv$iv = (Modifier.Node) node$iv;
                        if (!((this_$iv$iv.getKindSet() & type) != 0) || !(node$iv instanceof DelegatingNode)) {
                            i = i3;
                            mask$iv = mask$iv2;
                        } else {
                            int count$iv = 0;
                            DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv;
                            Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                            while (node$iv$iv != null) {
                                Modifier.Node next$iv2 = node$iv$iv;
                                if (!((next$iv2.getKindSet() & type) != 0)) {
                                    i2 = i3;
                                    mask$iv2 = mask$iv2;
                                } else {
                                    count$iv++;
                                    i2 = i3;
                                    if (count$iv == 1) {
                                        node$iv = next$iv2;
                                        mask$iv2 = mask$iv2;
                                    } else {
                                        Object obj = (MutableVector) stack$iv;
                                        if (obj != null) {
                                            mutableVector = obj;
                                        } else {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        stack$iv = mutableVector;
                                        Modifier.Node theNode$iv = (Modifier.Node) node$iv;
                                        if (theNode$iv != null) {
                                            MutableVector mutableVector2 = (MutableVector) stack$iv;
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(theNode$iv);
                                            }
                                            node$iv = null;
                                        }
                                        MutableVector mutableVector3 = (MutableVector) stack$iv;
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(next$iv2);
                                        }
                                    }
                                }
                                node$iv$iv = node$iv$iv.getChild();
                                i3 = i2;
                                mask$iv2 = mask$iv2;
                            }
                            i = i3;
                            mask$iv = mask$iv2;
                            if (count$iv == 1) {
                                i3 = i;
                                mask$iv2 = mask$iv;
                            }
                        }
                    }
                    node$iv = pop((MutableVector) stack$iv);
                    i3 = i;
                    mask$iv2 = mask$iv;
                }
            }
            next$iv = next$iv.getChild();
            block = block;
            i3 = i3;
            mask$iv2 = mask$iv2;
        }
    }

    /* JADX INFO: renamed from: visitLocalAncestors-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m4344visitLocalAncestors6rFNWt0(DelegatableNode visitLocalAncestors, int type, Function1<? super T, Unit> block) {
        int i;
        int mask$iv;
        int i2;
        Object mutableVector;
        block = block;
        Intrinsics.checkNotNullParameter(visitLocalAncestors, "$this$visitLocalAncestors");
        Intrinsics.checkNotNullParameter(block, "block");
        int i3 = 0;
        int mask$iv2 = type;
        if (!visitLocalAncestors.getNode().getIsAttached()) {
            throw new IllegalStateException("visitLocalAncestors called on an unattached node".toString());
        }
        Modifier.Node next$iv = visitLocalAncestors.getNode().getParent();
        while (next$iv != null) {
            if ((next$iv.getKindSet() & mask$iv2) != 0) {
                Object it = next$iv;
                Object stack$iv = null;
                Object node$iv = it;
                while (node$iv != null) {
                    Intrinsics.reifiedOperationMarker(3, "T");
                    if (node$iv instanceof Object) {
                        block.invoke(node$iv);
                        i = i3;
                        mask$iv = mask$iv2;
                    } else {
                        Modifier.Node this_$iv$iv = (Modifier.Node) node$iv;
                        if (!((this_$iv$iv.getKindSet() & type) != 0) || !(node$iv instanceof DelegatingNode)) {
                            i = i3;
                            mask$iv = mask$iv2;
                        } else {
                            int count$iv = 0;
                            DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv;
                            Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                            while (node$iv$iv != null) {
                                Modifier.Node next$iv2 = node$iv$iv;
                                if (!((next$iv2.getKindSet() & type) != 0)) {
                                    i2 = i3;
                                    mask$iv2 = mask$iv2;
                                } else {
                                    count$iv++;
                                    i2 = i3;
                                    if (count$iv == 1) {
                                        node$iv = next$iv2;
                                        mask$iv2 = mask$iv2;
                                    } else {
                                        Object obj = (MutableVector) stack$iv;
                                        if (obj != null) {
                                            mutableVector = obj;
                                        } else {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        stack$iv = mutableVector;
                                        Modifier.Node theNode$iv = (Modifier.Node) node$iv;
                                        if (theNode$iv != null) {
                                            MutableVector mutableVector2 = (MutableVector) stack$iv;
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(theNode$iv);
                                            }
                                            node$iv = null;
                                        }
                                        MutableVector mutableVector3 = (MutableVector) stack$iv;
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(next$iv2);
                                        }
                                    }
                                }
                                node$iv$iv = node$iv$iv.getChild();
                                i3 = i2;
                                mask$iv2 = mask$iv2;
                            }
                            i = i3;
                            mask$iv = mask$iv2;
                            if (count$iv == 1) {
                                i3 = i;
                                mask$iv2 = mask$iv;
                            }
                        }
                    }
                    node$iv = pop((MutableVector) stack$iv);
                    i3 = i;
                    mask$iv2 = mask$iv;
                }
            }
            next$iv = next$iv.getParent();
            block = block;
            i3 = i3;
            mask$iv2 = mask$iv2;
        }
    }

    /* JADX INFO: renamed from: visitAncestors-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m4342visitAncestorsYYKmho$default(DelegatableNode visitAncestors, int type, boolean includeSelf, Function1 block, int i, Object obj) {
        boolean includeSelf2;
        int i2;
        int mask$iv;
        NodeChain nodes;
        int mask$iv2;
        DelegatingNode this_$iv$iv;
        block = block;
        boolean includeSelf3 = (i & 2) != 0 ? false : includeSelf;
        Intrinsics.checkNotNullParameter(visitAncestors, "$this$visitAncestors");
        Intrinsics.checkNotNullParameter(block, "block");
        int i3 = 0;
        int mask$iv3 = type;
        if (!visitAncestors.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv = visitAncestors.getNode();
        if (!includeSelf3) {
            node$iv = node$iv.getParent();
        }
        LayoutNode layout$iv = requireLayoutNode(visitAncestors);
        while (layout$iv != null) {
            Modifier.Node head$iv = layout$iv.getNodes().getHead();
            if ((head$iv.getAggregateChildKindSet() & mask$iv3) != 0) {
                while (node$iv != null) {
                    if ((node$iv.getKindSet() & mask$iv3) != 0) {
                        Modifier.Node it = node$iv;
                        Object stack$iv = null;
                        Modifier.Node this_$iv$iv2 = it;
                        while (this_$iv$iv2 != null) {
                            includeSelf3 = includeSelf3;
                            i3 = i3;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (this_$iv$iv2 instanceof Object) {
                                block.invoke(this_$iv$iv2);
                                mask$iv2 = mask$iv3;
                            } else {
                                if (((this_$iv$iv2.getKindSet() & type) != 0) && (this_$iv$iv2 instanceof DelegatingNode)) {
                                    int count$iv = 0;
                                    DelegatingNode this_$iv$iv3 = (DelegatingNode) this_$iv$iv2;
                                    Modifier.Node node$iv$iv = this_$iv$iv3.getDelegate();
                                    while (node$iv$iv != null) {
                                        Modifier.Node next$iv = node$iv$iv;
                                        if ((next$iv.getKindSet() & type) != 0) {
                                            count$iv++;
                                            this_$iv$iv = this_$iv$iv3;
                                            if (count$iv == 1) {
                                                this_$iv$iv2 = next$iv;
                                                mask$iv3 = mask$iv3;
                                            } else {
                                                Object mutableVector = (MutableVector) stack$iv;
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                stack$iv = mutableVector;
                                                Modifier.Node theNode$iv = this_$iv$iv2;
                                                if (theNode$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv);
                                                    }
                                                    this_$iv$iv2 = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv);
                                                }
                                                count$iv = count$iv;
                                            }
                                        } else {
                                            this_$iv$iv = this_$iv$iv3;
                                            mask$iv3 = mask$iv3;
                                        }
                                        node$iv$iv = node$iv$iv.getChild();
                                        this_$iv$iv3 = this_$iv$iv;
                                        mask$iv3 = mask$iv3;
                                    }
                                    mask$iv2 = mask$iv3;
                                    if (count$iv == 1) {
                                        mask$iv3 = mask$iv2;
                                    }
                                } else {
                                    mask$iv2 = mask$iv3;
                                }
                            }
                            this_$iv$iv2 = pop((MutableVector) stack$iv);
                            mask$iv3 = mask$iv2;
                        }
                    }
                    node$iv = node$iv.getParent();
                    includeSelf3 = includeSelf3;
                    block = block;
                    i3 = i3;
                    mask$iv3 = mask$iv3;
                }
                includeSelf2 = includeSelf3;
                i2 = i3;
                mask$iv = mask$iv3;
            } else {
                includeSelf2 = includeSelf3;
                i2 = i3;
                mask$iv = mask$iv3;
            }
            layout$iv = layout$iv.getParent$ui_release();
            node$iv = (layout$iv == null || (nodes = layout$iv.getNodes()) == null) ? null : nodes.getTail();
            includeSelf3 = includeSelf2;
            block = block;
            i3 = i2;
            mask$iv3 = mask$iv;
        }
    }

    /* JADX INFO: renamed from: visitAncestors-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m4341visitAncestorsYYKmho(DelegatableNode visitAncestors, int type, boolean includeSelf, Function1<? super T, Unit> block) {
        int i;
        int mask$iv;
        DelegatableNode $this$visitAncestors$iv;
        NodeChain nodes;
        int mask$iv2;
        DelegatableNode $this$visitAncestors$iv2;
        block = block;
        Intrinsics.checkNotNullParameter(visitAncestors, "$this$visitAncestors");
        Intrinsics.checkNotNullParameter(block, "block");
        int i2 = 0;
        int mask$iv3 = type;
        DelegatableNode $this$visitAncestors$iv3 = visitAncestors;
        if (!$this$visitAncestors$iv3.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv = $this$visitAncestors$iv3.getNode();
        if (!includeSelf) {
            node$iv = node$iv.getParent();
        }
        LayoutNode layout$iv = requireLayoutNode($this$visitAncestors$iv3);
        while (layout$iv != null) {
            Modifier.Node head$iv = layout$iv.getNodes().getHead();
            if ((head$iv.getAggregateChildKindSet() & mask$iv3) != 0) {
                while (node$iv != null) {
                    if ((node$iv.getKindSet() & mask$iv3) != 0) {
                        Object it = node$iv;
                        Object stack$iv = null;
                        Object node$iv2 = it;
                        while (node$iv2 != null) {
                            i2 = i2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (node$iv2 instanceof Object) {
                                block.invoke(node$iv2);
                                mask$iv2 = mask$iv3;
                                $this$visitAncestors$iv2 = $this$visitAncestors$iv3;
                            } else {
                                Modifier.Node this_$iv$iv = (Modifier.Node) node$iv2;
                                if (((this_$iv$iv.getKindSet() & type) != 0) && (node$iv2 instanceof DelegatingNode)) {
                                    int count$iv = 0;
                                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv2;
                                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                    while (node$iv$iv != null) {
                                        Modifier.Node next$iv = node$iv$iv;
                                        if ((next$iv.getKindSet() & type) != 0) {
                                            count$iv++;
                                            if (count$iv == 1) {
                                                node$iv2 = next$iv;
                                                mask$iv3 = mask$iv3;
                                                $this$visitAncestors$iv3 = $this$visitAncestors$iv3;
                                            } else {
                                                Object mutableVector = (MutableVector) stack$iv;
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                stack$iv = mutableVector;
                                                Modifier.Node theNode$iv = (Modifier.Node) node$iv2;
                                                if (theNode$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv);
                                                    }
                                                    node$iv2 = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv);
                                                }
                                                count$iv = count$iv;
                                            }
                                        } else {
                                            mask$iv3 = mask$iv3;
                                            $this$visitAncestors$iv3 = $this$visitAncestors$iv3;
                                        }
                                        node$iv$iv = node$iv$iv.getChild();
                                        mask$iv3 = mask$iv3;
                                        $this$visitAncestors$iv3 = $this$visitAncestors$iv3;
                                    }
                                    mask$iv2 = mask$iv3;
                                    $this$visitAncestors$iv2 = $this$visitAncestors$iv3;
                                    if (count$iv == 1) {
                                        mask$iv3 = mask$iv2;
                                        $this$visitAncestors$iv3 = $this$visitAncestors$iv2;
                                    }
                                } else {
                                    mask$iv2 = mask$iv3;
                                    $this$visitAncestors$iv2 = $this$visitAncestors$iv3;
                                }
                            }
                            node$iv2 = pop((MutableVector) stack$iv);
                            mask$iv3 = mask$iv2;
                            $this$visitAncestors$iv3 = $this$visitAncestors$iv2;
                        }
                    }
                    node$iv = node$iv.getParent();
                    block = block;
                    i2 = i2;
                    mask$iv3 = mask$iv3;
                    $this$visitAncestors$iv3 = $this$visitAncestors$iv3;
                }
                i = i2;
                mask$iv = mask$iv3;
                $this$visitAncestors$iv = $this$visitAncestors$iv3;
            } else {
                i = i2;
                mask$iv = mask$iv3;
                $this$visitAncestors$iv = $this$visitAncestors$iv3;
            }
            layout$iv = layout$iv.getParent$ui_release();
            node$iv = (layout$iv == null || (nodes = layout$iv.getNodes()) == null) ? null : nodes.getTail();
            block = block;
            i2 = i;
            mask$iv3 = mask$iv;
            $this$visitAncestors$iv3 = $this$visitAncestors$iv;
        }
    }

    /* JADX INFO: renamed from: visitSelfAndAncestors-5BbP62I, reason: not valid java name */
    public static final /* synthetic */ <T> void m4346visitSelfAndAncestors5BbP62I(DelegatableNode visitSelfAndAncestors, int type, int untilType, Function1<? super T, Unit> block) {
        int i;
        Modifier.Node self;
        int mask$iv;
        NodeChain nodes;
        Modifier.Node self2;
        int mask$iv2;
        block = block;
        Intrinsics.checkNotNullParameter(visitSelfAndAncestors, "$this$visitSelfAndAncestors");
        Intrinsics.checkNotNullParameter(block, "block");
        int i2 = 0;
        Modifier.Node self3 = visitSelfAndAncestors.getNode();
        int mask$iv3 = type | untilType;
        if (!visitSelfAndAncestors.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv = visitSelfAndAncestors.getNode();
        LayoutNode layout$iv = requireLayoutNode(visitSelfAndAncestors);
        while (layout$iv != null) {
            Modifier.Node head$iv = layout$iv.getNodes().getHead();
            if ((head$iv.getAggregateChildKindSet() & mask$iv3) == 0) {
                i = i2;
                self = self3;
                mask$iv = mask$iv3;
            } else {
                while (node$iv != null) {
                    if ((node$iv.getKindSet() & mask$iv3) != 0) {
                        Modifier.Node it = node$iv;
                        if (it != self3) {
                            if ((it.getKindSet() & untilType) != 0) {
                                return;
                            }
                        }
                        if ((it.getKindSet() & type) != 0) {
                            Object stack$iv = null;
                            Object node$iv2 = it;
                            while (node$iv2 != null) {
                                i2 = i2;
                                Intrinsics.reifiedOperationMarker(3, "T");
                                if (node$iv2 instanceof Object) {
                                    block.invoke(node$iv2);
                                    self2 = self3;
                                    mask$iv2 = mask$iv3;
                                } else {
                                    Modifier.Node this_$iv$iv = (Modifier.Node) node$iv2;
                                    if (!((this_$iv$iv.getKindSet() & type) != 0) || !(node$iv2 instanceof DelegatingNode)) {
                                        self2 = self3;
                                        mask$iv2 = mask$iv3;
                                    } else {
                                        int count$iv = 0;
                                        DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv2;
                                        Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                        while (node$iv$iv != null) {
                                            Modifier.Node next$iv = node$iv$iv;
                                            if (!((next$iv.getKindSet() & type) != 0)) {
                                                self3 = self3;
                                                mask$iv3 = mask$iv3;
                                            } else {
                                                count$iv++;
                                                if (count$iv == 1) {
                                                    node$iv2 = next$iv;
                                                    self3 = self3;
                                                    mask$iv3 = mask$iv3;
                                                } else {
                                                    Object mutableVector = (MutableVector) stack$iv;
                                                    if (mutableVector == null) {
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    }
                                                    stack$iv = mutableVector;
                                                    Modifier.Node theNode$iv = (Modifier.Node) node$iv2;
                                                    if (theNode$iv != null) {
                                                        MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv);
                                                        }
                                                        node$iv2 = null;
                                                    }
                                                    MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv);
                                                    }
                                                    count$iv = count$iv;
                                                }
                                            }
                                            node$iv$iv = node$iv$iv.getChild();
                                            self3 = self3;
                                            mask$iv3 = mask$iv3;
                                        }
                                        self2 = self3;
                                        mask$iv2 = mask$iv3;
                                        if (count$iv == 1) {
                                            self3 = self2;
                                            mask$iv3 = mask$iv2;
                                        }
                                    }
                                }
                                node$iv2 = pop((MutableVector) stack$iv);
                                self3 = self2;
                                mask$iv3 = mask$iv2;
                            }
                        }
                    }
                    node$iv = node$iv.getParent();
                    block = block;
                    i2 = i2;
                    self3 = self3;
                    mask$iv3 = mask$iv3;
                }
                i = i2;
                self = self3;
                mask$iv = mask$iv3;
            }
            layout$iv = layout$iv.getParent$ui_release();
            node$iv = (layout$iv == null || (nodes = layout$iv.getNodes()) == null) ? null : nodes.getTail();
            block = block;
            i2 = i;
            self3 = self;
            mask$iv3 = mask$iv;
        }
    }

    /* JADX INFO: renamed from: ancestors-64DMado, reason: not valid java name */
    public static final /* synthetic */ <T> List<T> m4336ancestors64DMado(DelegatableNode ancestors, int type) {
        int i;
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
        boolean includeSelf$iv;
        NodeChain nodes;
        boolean includeSelf$iv2;
        Object result;
        Object mutableVector;
        Intrinsics.checkNotNullParameter(ancestors, "$this$ancestors");
        int i2 = 0;
        Object result2 = null;
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = ancestors;
        boolean includeSelf$iv3 = false;
        if (!$this$visitAncestors_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2.getNode().getParent();
        LayoutNode layout$iv$iv = requireLayoutNode($this$visitAncestors_u2dY_u2dYKmho_u24default$iv2);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & type) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & type) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        Object stack$iv$iv = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop instanceof Object) {
                                Modifier.Node node = nodePop;
                                if (result2 == null) {
                                    Object result3 = new ArrayList();
                                    result2 = (List) result3;
                                }
                                ((List) result2).add(node);
                                includeSelf$iv2 = includeSelf$iv3;
                            } else {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & type) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    includeSelf$iv2 = includeSelf$iv3;
                                    result2 = result2;
                                } else {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if ((next$iv$iv.getKindSet() & type) != 0) {
                                            count$iv$iv++;
                                            result = result2;
                                            if (count$iv$iv == 1) {
                                                nodePop = next$iv$iv;
                                                includeSelf$iv3 = includeSelf$iv3;
                                            } else {
                                                Object obj = (MutableVector) stack$iv$iv;
                                                if (obj == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    mutableVector = obj;
                                                }
                                                stack$iv$iv = mutableVector;
                                                Modifier.Node theNode$iv$iv = nodePop;
                                                if (theNode$iv$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv$iv);
                                                }
                                                count$iv$iv = count$iv$iv;
                                            }
                                        } else {
                                            result = result2;
                                            includeSelf$iv3 = includeSelf$iv3;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        result2 = result;
                                        includeSelf$iv3 = includeSelf$iv3;
                                    }
                                    Object result4 = result2;
                                    includeSelf$iv2 = includeSelf$iv3;
                                    if (count$iv$iv == 1) {
                                        result2 = result4;
                                        includeSelf$iv3 = includeSelf$iv2;
                                    } else {
                                        result2 = result4;
                                    }
                                }
                            }
                            nodePop = pop((MutableVector) stack$iv$iv);
                            includeSelf$iv3 = includeSelf$iv2;
                        }
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    i2 = i2;
                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                    includeSelf$iv3 = includeSelf$iv3;
                }
                i = i2;
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                includeSelf$iv = includeSelf$iv3;
            } else {
                i = i2;
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                includeSelf$iv = includeSelf$iv3;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui_release();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            i2 = i;
            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
            includeSelf$iv3 = includeSelf$iv;
        }
        return (List) result2;
    }

    /* JADX INFO: renamed from: nearestAncestor-64DMado, reason: not valid java name */
    public static final /* synthetic */ <T> T m4339nearestAncestor64DMado(DelegatableNode nearestAncestor, int i) {
        int i2;
        DelegatableNode delegatableNode;
        boolean z;
        int i3;
        NodeChain nodes;
        DelegatableNode delegatableNode2;
        boolean z2;
        int i4;
        DelegatableNode delegatableNode3;
        Intrinsics.checkNotNullParameter(nearestAncestor, "$this$nearestAncestor");
        int i5 = 0;
        DelegatableNode delegatableNode4 = nearestAncestor;
        boolean z3 = false;
        int i6 = 0;
        if (!delegatableNode4.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node parent = delegatableNode4.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode4);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & i) != 0) {
                        Object obj = null;
                        Modifier.Node nodePop = parent;
                        while (nodePop != null) {
                            i5 = i5;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (nodePop instanceof Object) {
                                return (T) nodePop;
                            }
                            if (((nodePop.getKindSet() & i) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                delegatableNode2 = delegatableNode4;
                                z2 = z3;
                                i4 = i6;
                                nodePop = pop((MutableVector) obj);
                                delegatableNode4 = delegatableNode2;
                                z3 = z2;
                                i6 = i4;
                            } else {
                                int i7 = 0;
                                Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                                while (delegate$ui_release != null) {
                                    Modifier.Node node = delegate$ui_release;
                                    if ((node.getKindSet() & i) != 0) {
                                        i7++;
                                        delegatableNode3 = delegatableNode4;
                                        if (i7 == 1) {
                                            nodePop = node;
                                            z3 = z3;
                                            i6 = i6;
                                        } else {
                                            MutableVector mutableVector = (MutableVector) obj;
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            obj = mutableVector;
                                            Modifier.Node node2 = nodePop;
                                            if (node2 != null) {
                                                MutableVector mutableVector2 = (MutableVector) obj;
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(node2);
                                                }
                                                nodePop = null;
                                            }
                                            MutableVector mutableVector3 = (MutableVector) obj;
                                            if (mutableVector3 != null) {
                                                mutableVector3.add(node);
                                            }
                                            i7 = i7;
                                        }
                                    } else {
                                        delegatableNode3 = delegatableNode4;
                                        z3 = z3;
                                        i6 = i6;
                                    }
                                    delegate$ui_release = delegate$ui_release.getChild();
                                    delegatableNode4 = delegatableNode3;
                                    z3 = z3;
                                    i6 = i6;
                                }
                                delegatableNode2 = delegatableNode4;
                                z2 = z3;
                                i4 = i6;
                                if (i7 == 1) {
                                    delegatableNode4 = delegatableNode2;
                                    z3 = z2;
                                    i6 = i4;
                                } else {
                                    nodePop = pop((MutableVector) obj);
                                    delegatableNode4 = delegatableNode2;
                                    z3 = z2;
                                    i6 = i4;
                                }
                            }
                        }
                    }
                    parent = parent.getParent();
                    i5 = i5;
                    delegatableNode4 = delegatableNode4;
                    z3 = z3;
                    i6 = i6;
                }
                i2 = i5;
                delegatableNode = delegatableNode4;
                z = z3;
                i3 = i6;
            } else {
                i2 = i5;
                delegatableNode = delegatableNode4;
                z = z3;
                i3 = i6;
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
            i5 = i2;
            delegatableNode4 = delegatableNode;
            z3 = z;
            i6 = i3;
        }
        return null;
    }

    /* JADX INFO: renamed from: visitSubtree-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m4348visitSubtree6rFNWt0(DelegatableNode visitSubtree, int type, Function1<? super T, Unit> block) {
        int i;
        int mask$iv;
        DelegatableNode $this$visitSubtree$iv;
        int mask$iv2;
        DelegatableNode $this$visitSubtree$iv2;
        block = block;
        Intrinsics.checkNotNullParameter(visitSubtree, "$this$visitSubtree");
        Intrinsics.checkNotNullParameter(block, "block");
        int i2 = 0;
        int mask$iv3 = type;
        DelegatableNode $this$visitSubtree$iv3 = visitSubtree;
        if (!$this$visitSubtree$iv3.getNode().getIsAttached()) {
            throw new IllegalStateException("visitSubtree called on an unattached node".toString());
        }
        Modifier.Node node$iv = $this$visitSubtree$iv3.getNode().getChild();
        LayoutNode layout$iv = requireLayoutNode($this$visitSubtree$iv3);
        NestedVectorStack nodes$iv = new NestedVectorStack();
        while (layout$iv != null) {
            Modifier.Node node$iv2 = node$iv == null ? layout$iv.getNodes().getHead() : node$iv;
            if ((node$iv2.getAggregateChildKindSet() & mask$iv3) != 0) {
                while (node$iv2 != null) {
                    if ((node$iv2.getKindSet() & mask$iv3) != 0) {
                        Object it = node$iv2;
                        Object stack$iv = null;
                        Object node$iv3 = it;
                        while (node$iv3 != null) {
                            i2 = i2;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (node$iv3 instanceof Object) {
                                block.invoke(node$iv3);
                                mask$iv2 = mask$iv3;
                                $this$visitSubtree$iv2 = $this$visitSubtree$iv3;
                            } else {
                                Modifier.Node this_$iv$iv = (Modifier.Node) node$iv3;
                                if (((this_$iv$iv.getKindSet() & type) != 0) && (node$iv3 instanceof DelegatingNode)) {
                                    int count$iv = 0;
                                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv3;
                                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                    while (node$iv$iv != null) {
                                        Modifier.Node next$iv = node$iv$iv;
                                        if ((next$iv.getKindSet() & type) != 0) {
                                            count$iv++;
                                            if (count$iv == 1) {
                                                node$iv3 = next$iv;
                                                mask$iv3 = mask$iv3;
                                                $this$visitSubtree$iv3 = $this$visitSubtree$iv3;
                                            } else {
                                                Object mutableVector = (MutableVector) stack$iv;
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                stack$iv = mutableVector;
                                                Modifier.Node theNode$iv = (Modifier.Node) node$iv3;
                                                if (theNode$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv);
                                                    }
                                                    node$iv3 = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv);
                                                }
                                                count$iv = count$iv;
                                            }
                                        } else {
                                            mask$iv3 = mask$iv3;
                                            $this$visitSubtree$iv3 = $this$visitSubtree$iv3;
                                        }
                                        node$iv$iv = node$iv$iv.getChild();
                                        mask$iv3 = mask$iv3;
                                        $this$visitSubtree$iv3 = $this$visitSubtree$iv3;
                                    }
                                    mask$iv2 = mask$iv3;
                                    $this$visitSubtree$iv2 = $this$visitSubtree$iv3;
                                    if (count$iv == 1) {
                                        mask$iv3 = mask$iv2;
                                        $this$visitSubtree$iv3 = $this$visitSubtree$iv2;
                                    }
                                } else {
                                    mask$iv2 = mask$iv3;
                                    $this$visitSubtree$iv2 = $this$visitSubtree$iv3;
                                }
                            }
                            node$iv3 = pop((MutableVector) stack$iv);
                            mask$iv3 = mask$iv2;
                            $this$visitSubtree$iv3 = $this$visitSubtree$iv2;
                        }
                    }
                    node$iv2 = node$iv2.getChild();
                    block = block;
                    i2 = i2;
                    mask$iv3 = mask$iv3;
                    $this$visitSubtree$iv3 = $this$visitSubtree$iv3;
                }
                i = i2;
                mask$iv = mask$iv3;
                $this$visitSubtree$iv = $this$visitSubtree$iv3;
            } else {
                i = i2;
                mask$iv = mask$iv3;
                $this$visitSubtree$iv = $this$visitSubtree$iv3;
            }
            node$iv = null;
            nodes$iv.push(layout$iv.get_children$ui_release());
            layout$iv = nodes$iv.isNotEmpty() ? (LayoutNode) nodes$iv.pop() : null;
            block = block;
            i2 = i;
            mask$iv3 = mask$iv;
            $this$visitSubtree$iv3 = $this$visitSubtree$iv;
        }
    }

    /* JADX INFO: renamed from: visitChildren-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m4343visitChildren6rFNWt0(DelegatableNode visitChildren, int type, Function1<? super T, Unit> block) {
        int mask$iv;
        DelegatableNode $this$visitChildren$iv;
        block = block;
        Intrinsics.checkNotNullParameter(visitChildren, "$this$visitChildren");
        Intrinsics.checkNotNullParameter(block, "block");
        int i = 0;
        int mask$iv2 = type;
        DelegatableNode $this$visitChildren$iv2 = visitChildren;
        if (!$this$visitChildren$iv2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node".toString());
        }
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitChildren$iv2.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitChildren$iv2.getNode());
        } else {
            branches$iv.add(child$iv);
        }
        while (branches$iv.isNotEmpty()) {
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv2) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv == null) {
                        block = block;
                        $this$visitChildren$iv2 = $this$visitChildren$iv2;
                        break;
                    }
                    if ((node$iv.getKindSet() & mask$iv2) != 0) {
                        Object it = node$iv;
                        Object stack$iv = null;
                        Object node$iv2 = it;
                        while (node$iv2 != null) {
                            i = i;
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (node$iv2 instanceof Object) {
                                block.invoke(node$iv2);
                                mask$iv = mask$iv2;
                                $this$visitChildren$iv = $this$visitChildren$iv2;
                            } else {
                                Modifier.Node this_$iv$iv = (Modifier.Node) node$iv2;
                                if (((this_$iv$iv.getKindSet() & type) != 0) && (node$iv2 instanceof DelegatingNode)) {
                                    int count$iv = 0;
                                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv2;
                                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                    while (node$iv$iv != null) {
                                        Modifier.Node next$iv = node$iv$iv;
                                        if ((next$iv.getKindSet() & type) != 0) {
                                            count$iv++;
                                            if (count$iv == 1) {
                                                node$iv2 = next$iv;
                                                mask$iv2 = mask$iv2;
                                                $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                            } else {
                                                Object mutableVector = (MutableVector) stack$iv;
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                stack$iv = mutableVector;
                                                Modifier.Node theNode$iv = (Modifier.Node) node$iv2;
                                                if (theNode$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv);
                                                    }
                                                    node$iv2 = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv);
                                                }
                                                count$iv = count$iv;
                                            }
                                        } else {
                                            mask$iv2 = mask$iv2;
                                            $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                        }
                                        node$iv$iv = node$iv$iv.getChild();
                                        mask$iv2 = mask$iv2;
                                        $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                    }
                                    mask$iv = mask$iv2;
                                    $this$visitChildren$iv = $this$visitChildren$iv2;
                                    if (count$iv == 1) {
                                        mask$iv2 = mask$iv;
                                        $this$visitChildren$iv2 = $this$visitChildren$iv;
                                    }
                                } else {
                                    mask$iv = mask$iv2;
                                    $this$visitChildren$iv = $this$visitChildren$iv2;
                                }
                            }
                            node$iv2 = pop((MutableVector) stack$iv);
                            mask$iv2 = mask$iv;
                            $this$visitChildren$iv2 = $this$visitChildren$iv;
                        }
                        block = block;
                        $this$visitChildren$iv2 = $this$visitChildren$iv2;
                        break;
                    }
                    node$iv = node$iv.getChild();
                    block = block;
                    $this$visitChildren$iv2 = $this$visitChildren$iv2;
                }
            } else {
                addLayoutNodeChildren(branches$iv, branch$iv);
            }
        }
    }

    /* JADX INFO: renamed from: visitSelfAndChildren-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m4347visitSelfAndChildren6rFNWt0(DelegatableNode visitSelfAndChildren, int type, Function1<? super T, Unit> block) {
        int count$iv;
        int mask$iv;
        DelegatableNode $this$visitChildren$iv;
        int $i$f$visitChildren;
        int mask$iv2;
        Object mutableVector;
        int i;
        block = block;
        Intrinsics.checkNotNullParameter(visitSelfAndChildren, "$this$visitSelfAndChildren");
        Intrinsics.checkNotNullParameter(block, "block");
        int i2 = 0;
        Object $this$dispatchForKind_u2d6rFNWt0$iv = visitSelfAndChildren.getNode();
        Object stack$iv = null;
        Object node$iv = $this$dispatchForKind_u2d6rFNWt0$iv;
        while (true) {
            count$iv = 3;
            int i3 = 1;
            if (node$iv == null) {
                break;
            }
            Intrinsics.reifiedOperationMarker(3, "T");
            if (node$iv instanceof Object) {
                block.invoke(node$iv);
                i = i2;
            } else {
                Modifier.Node this_$iv$iv = (Modifier.Node) node$iv;
                if (!((this_$iv$iv.getKindSet() & type) != 0) || !(node$iv instanceof DelegatingNode)) {
                    i = i2;
                } else {
                    int count$iv2 = 0;
                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv;
                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                    while (node$iv$iv != null) {
                        Modifier.Node next$iv = node$iv$iv;
                        if (!((next$iv.getKindSet() & type) != 0)) {
                            i2 = i2;
                        } else {
                            count$iv2++;
                            if (count$iv2 == i3) {
                                node$iv = next$iv;
                                i2 = i2;
                            } else {
                                Object obj = (MutableVector) stack$iv;
                                if (obj == null) {
                                    Object mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                    obj = mutableVector2;
                                }
                                stack$iv = obj;
                                Modifier.Node theNode$iv = (Modifier.Node) node$iv;
                                if (theNode$iv != null) {
                                    MutableVector mutableVector3 = (MutableVector) stack$iv;
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(theNode$iv);
                                    }
                                    node$iv = null;
                                }
                                MutableVector mutableVector4 = (MutableVector) stack$iv;
                                if (mutableVector4 != null) {
                                    mutableVector4.add(next$iv);
                                }
                            }
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        i3 = 1;
                        i2 = i2;
                    }
                    i = i2;
                    if (count$iv2 == 1) {
                        i2 = i;
                    }
                }
            }
            node$iv = pop((MutableVector) stack$iv);
            i2 = i;
        }
        int mask$iv3 = type;
        DelegatableNode $this$visitChildren$iv2 = visitSelfAndChildren;
        int $i$f$visitChildren2 = 0;
        if (!$this$visitChildren$iv2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node".toString());
        }
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitChildren$iv2.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitChildren$iv2.getNode());
        } else {
            branches$iv.add(child$iv);
        }
        while (branches$iv.isNotEmpty()) {
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv3) == 0) {
                addLayoutNodeChildren(branches$iv, branch$iv);
            } else {
                Modifier.Node node$iv2 = branch$iv;
                while (true) {
                    if (node$iv2 == null) {
                        block = block;
                        mask$iv3 = mask$iv3;
                        count$iv = 3;
                        break;
                    }
                    if ((node$iv2.getKindSet() & mask$iv3) != 0) {
                        Object it = node$iv2;
                        Object stack$iv2 = null;
                        Object node$iv3 = it;
                        while (node$iv3 != null) {
                            Intrinsics.reifiedOperationMarker(count$iv, "T");
                            if (node$iv3 instanceof Object) {
                                block.invoke(node$iv3);
                                mask$iv = mask$iv3;
                                $this$visitChildren$iv = $this$visitChildren$iv2;
                                $i$f$visitChildren = $i$f$visitChildren2;
                            } else {
                                Modifier.Node this_$iv$iv3 = (Modifier.Node) node$iv3;
                                if (!((this_$iv$iv3.getKindSet() & type) != 0) || !(node$iv3 instanceof DelegatingNode)) {
                                    mask$iv = mask$iv3;
                                    $this$visitChildren$iv = $this$visitChildren$iv2;
                                    $i$f$visitChildren = $i$f$visitChildren2;
                                } else {
                                    int count$iv3 = 0;
                                    DelegatingNode this_$iv$iv4 = (DelegatingNode) node$iv3;
                                    Modifier.Node node$iv$iv2 = this_$iv$iv4.getDelegate();
                                    while (node$iv$iv2 != null) {
                                        Modifier.Node next$iv2 = node$iv$iv2;
                                        if (!((next$iv2.getKindSet() & type) != 0)) {
                                            mask$iv2 = mask$iv3;
                                            $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                            $i$f$visitChildren2 = $i$f$visitChildren2;
                                        } else {
                                            count$iv3++;
                                            mask$iv2 = mask$iv3;
                                            if (count$iv3 == 1) {
                                                node$iv3 = next$iv2;
                                                $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                                $i$f$visitChildren2 = $i$f$visitChildren2;
                                            } else {
                                                Object obj2 = (MutableVector) stack$iv2;
                                                if (obj2 == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    mutableVector = obj2;
                                                }
                                                stack$iv2 = mutableVector;
                                                Modifier.Node theNode$iv2 = (Modifier.Node) node$iv3;
                                                if (theNode$iv2 != null) {
                                                    MutableVector mutableVector5 = (MutableVector) stack$iv2;
                                                    if (mutableVector5 != null) {
                                                        mutableVector5.add(theNode$iv2);
                                                    }
                                                    node$iv3 = null;
                                                }
                                                MutableVector mutableVector6 = (MutableVector) stack$iv2;
                                                if (mutableVector6 != null) {
                                                    mutableVector6.add(next$iv2);
                                                }
                                            }
                                        }
                                        node$iv$iv2 = node$iv$iv2.getChild();
                                        mask$iv3 = mask$iv2;
                                        $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                        $i$f$visitChildren2 = $i$f$visitChildren2;
                                    }
                                    mask$iv = mask$iv3;
                                    $this$visitChildren$iv = $this$visitChildren$iv2;
                                    $i$f$visitChildren = $i$f$visitChildren2;
                                    if (count$iv3 == 1) {
                                        mask$iv3 = mask$iv;
                                        $this$visitChildren$iv2 = $this$visitChildren$iv;
                                        $i$f$visitChildren2 = $i$f$visitChildren;
                                        count$iv = 3;
                                    }
                                }
                            }
                            node$iv3 = pop((MutableVector) stack$iv2);
                            mask$iv3 = mask$iv;
                            $this$visitChildren$iv2 = $this$visitChildren$iv;
                            $i$f$visitChildren2 = $i$f$visitChildren;
                            count$iv = 3;
                        }
                        block = block;
                        mask$iv3 = mask$iv3;
                        count$iv = 3;
                        break;
                    }
                    node$iv2 = node$iv2.getChild();
                    block = block;
                    mask$iv3 = mask$iv3;
                    count$iv = 3;
                }
            }
        }
    }

    /* JADX INFO: renamed from: visitSubtreeIf-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m4349visitSubtreeIf6rFNWt0(DelegatableNode visitSubtreeIf, int type, Function1<? super T, Boolean> block) {
        boolean z;
        int mask$iv;
        DelegatableNode $this$visitSubtreeIf$iv;
        block = block;
        Intrinsics.checkNotNullParameter(visitSubtreeIf, "$this$visitSubtreeIf");
        Intrinsics.checkNotNullParameter(block, "block");
        int i = 0;
        int mask$iv2 = type;
        DelegatableNode $this$visitSubtreeIf$iv2 = visitSubtreeIf;
        if (!$this$visitSubtreeIf$iv2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitSubtreeIf called on an unattached node".toString());
        }
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitSubtreeIf$iv2.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitSubtreeIf$iv2.getNode());
        } else {
            branches$iv.add(child$iv);
        }
        while (branches$iv.isNotEmpty()) {
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv2) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv != null) {
                        if ((node$iv.getKindSet() & mask$iv2) == 0) {
                            i = i;
                            mask$iv2 = mask$iv2;
                            $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
                        } else {
                            Object node = node$iv;
                            Object stack$iv = null;
                            Object node$iv2 = node;
                            while (true) {
                                if (node$iv2 != null) {
                                    i = i;
                                    Intrinsics.reifiedOperationMarker(3, "T");
                                    if (node$iv2 instanceof Object) {
                                        Object it = node$iv2;
                                        if (!block.invoke(it).booleanValue()) {
                                            z = false;
                                            break;
                                        } else {
                                            mask$iv = mask$iv2;
                                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv2;
                                        }
                                    } else {
                                        Modifier.Node this_$iv$iv = (Modifier.Node) node$iv2;
                                        if (!((this_$iv$iv.getKindSet() & type) != 0) || !(node$iv2 instanceof DelegatingNode)) {
                                            mask$iv = mask$iv2;
                                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv2;
                                        } else {
                                            int count$iv = 0;
                                            DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv2;
                                            Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                            while (node$iv$iv != null) {
                                                Modifier.Node next$iv = node$iv$iv;
                                                if (!((next$iv.getKindSet() & type) != 0)) {
                                                    mask$iv2 = mask$iv2;
                                                    $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
                                                } else {
                                                    count$iv++;
                                                    if (count$iv == 1) {
                                                        node$iv2 = next$iv;
                                                        mask$iv2 = mask$iv2;
                                                        $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
                                                    } else {
                                                        Object mutableVector = (MutableVector) stack$iv;
                                                        if (mutableVector == null) {
                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                        }
                                                        stack$iv = mutableVector;
                                                        Modifier.Node theNode$iv = (Modifier.Node) node$iv2;
                                                        if (theNode$iv != null) {
                                                            MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                            if (mutableVector2 != null) {
                                                                mutableVector2.add(theNode$iv);
                                                            }
                                                            node$iv2 = null;
                                                        }
                                                        MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                        if (mutableVector3 != null) {
                                                            mutableVector3.add(next$iv);
                                                        }
                                                        count$iv = count$iv;
                                                    }
                                                }
                                                node$iv$iv = node$iv$iv.getChild();
                                                mask$iv2 = mask$iv2;
                                                $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
                                            }
                                            mask$iv = mask$iv2;
                                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv2;
                                            if (count$iv == 1) {
                                                mask$iv2 = mask$iv;
                                                $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv;
                                            }
                                        }
                                    }
                                    node$iv2 = pop((MutableVector) stack$iv);
                                    mask$iv2 = mask$iv;
                                    $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv;
                                } else {
                                    i = i;
                                    z = true;
                                    break;
                                }
                            }
                            boolean diveDeeper$iv = z;
                            if (!diveDeeper$iv) {
                                i = i;
                                mask$iv2 = mask$iv2;
                                $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
                                break;
                            }
                        }
                        node$iv = node$iv.getChild();
                        block = block;
                        i = i;
                        mask$iv2 = mask$iv2;
                        $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
                    }
                }
            }
            addLayoutNodeChildren(branches$iv, branch$iv);
            i = i;
            mask$iv2 = mask$iv2;
            $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
        }
    }

    /* JADX INFO: renamed from: has-64DMado, reason: not valid java name */
    public static final boolean m4338has64DMado(DelegatableNode has, int type) {
        Intrinsics.checkNotNullParameter(has, "$this$has");
        return (has.getNode().getAggregateChildKindSet() & type) != 0;
    }

    /* JADX INFO: renamed from: requireCoordinator-64DMado, reason: not valid java name */
    public static final NodeCoordinator m4340requireCoordinator64DMado(DelegatableNode requireCoordinator, int kind) {
        Intrinsics.checkNotNullParameter(requireCoordinator, "$this$requireCoordinator");
        NodeCoordinator coordinator = requireCoordinator.getNode().getCoordinator();
        Intrinsics.checkNotNull(coordinator);
        if (coordinator.getTail() != requireCoordinator || !NodeKindKt.m4452getIncludeSelfInTraversalH91voCI(kind)) {
            return coordinator;
        }
        NodeCoordinator wrapped = coordinator.getWrapped();
        Intrinsics.checkNotNull(wrapped);
        return wrapped;
    }

    public static final LayoutNode requireLayoutNode(DelegatableNode $this$requireLayoutNode) {
        Intrinsics.checkNotNullParameter($this$requireLayoutNode, "<this>");
        NodeCoordinator coordinator = $this$requireLayoutNode.getNode().getCoordinator();
        if (coordinator == null) {
            throw new IllegalStateException("Cannot obtain node coordinator. Is the Modifier.Node attached?".toString());
        }
        return coordinator.getLayoutNode();
    }

    public static final Owner requireOwner(DelegatableNode $this$requireOwner) {
        Intrinsics.checkNotNullParameter($this$requireOwner, "<this>");
        Owner owner = requireLayoutNode($this$requireOwner).getOwner();
        if (owner != null) {
            return owner;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public static final Density requireDensity(DelegatableNode $this$requireDensity) {
        Intrinsics.checkNotNullParameter($this$requireDensity, "<this>");
        return requireLayoutNode($this$requireDensity).getDensity();
    }

    public static final LayoutDirection requireLayoutDirection(DelegatableNode $this$requireLayoutDirection) {
        Intrinsics.checkNotNullParameter($this$requireLayoutDirection, "<this>");
        return requireLayoutNode($this$requireLayoutDirection).getLayoutDirection();
    }

    public static final void invalidateSubtree(DelegatableNode $this$invalidateSubtree) {
        Intrinsics.checkNotNullParameter($this$invalidateSubtree, "<this>");
        if ($this$invalidateSubtree.getNode().getIsAttached()) {
            LayoutNode.invalidateSubtree$default(requireLayoutNode($this$invalidateSubtree), false, 1, null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x005b  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final LayoutModifierNode asLayoutModifierNode(Modifier.Node node) {
        Modifier.Node child;
        Intrinsics.checkNotNullParameter(node, "<this>");
        int kind$iv = (node.getKindSet() & NodeKind.m4443constructorimpl(2)) != 0 ? 1 : 0;
        if (kind$iv == 0) {
            return null;
        }
        if (node instanceof LayoutModifierNode) {
            return (LayoutModifierNode) node;
        }
        if (node instanceof DelegatingNode) {
            Modifier.Node node2 = ((DelegatingNode) node).getDelegate();
            while (node2 != null) {
                if (node2 instanceof LayoutModifierNode) {
                    return (LayoutModifierNode) node2;
                }
                if (node2 instanceof DelegatingNode) {
                    Modifier.Node this_$iv = node2;
                    int kind$iv2 = (this_$iv.getKindSet() & NodeKind.m4443constructorimpl(2)) != 0 ? 1 : 0;
                    if (kind$iv2 != 0) {
                        child = ((DelegatingNode) node2).getDelegate();
                    } else {
                        child = node2.getChild();
                    }
                } else {
                    child = node2.getChild();
                }
                node2 = child;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: dispatchForKind-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m4337dispatchForKind6rFNWt0(Modifier.Node dispatchForKind, int kind, Function1<? super T, Unit> block) {
        block = block;
        Intrinsics.checkNotNullParameter(dispatchForKind, "$this$dispatchForKind");
        Intrinsics.checkNotNullParameter(block, "block");
        Object stack = null;
        Object node = dispatchForKind;
        while (node != null) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if (node instanceof Object) {
                block.invoke(node);
            } else {
                Modifier.Node this_$iv = (Modifier.Node) node;
                int i = 1;
                if (((this_$iv.getKindSet() & kind) != 0) && (node instanceof DelegatingNode)) {
                    int count = 0;
                    DelegatingNode this_$iv2 = (DelegatingNode) node;
                    Modifier.Node node$iv = this_$iv2.getDelegate();
                    while (node$iv != null) {
                        Modifier.Node next = node$iv;
                        if ((next.getKindSet() & kind) != 0) {
                            count++;
                            if (count == i) {
                                node = next;
                            } else {
                                Object obj = (MutableVector) stack;
                                if (obj == null) {
                                    Object mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    obj = mutableVector;
                                }
                                stack = obj;
                                Modifier.Node theNode = (Modifier.Node) node;
                                if (theNode != null) {
                                    MutableVector mutableVector2 = (MutableVector) stack;
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(theNode);
                                    }
                                    node = null;
                                }
                                MutableVector mutableVector3 = (MutableVector) stack;
                                if (mutableVector3 != null) {
                                    mutableVector3.add(next);
                                }
                            }
                        }
                        node$iv = node$iv.getChild();
                        i = 1;
                    }
                    if (count == 1) {
                    }
                }
            }
            node = pop((MutableVector) stack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier.Node pop(MutableVector<Modifier.Node> mutableVector) {
        if (mutableVector == null || mutableVector.isEmpty()) {
            return null;
        }
        return mutableVector.removeAt(mutableVector.getSize() - 1);
    }
}
