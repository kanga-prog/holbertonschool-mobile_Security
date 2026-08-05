package androidx.compose.ui.modifier;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ModifierLocalModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u00012\u00020\u0002J)\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\b0\t2\u0006\u0010\u000f\u001a\u0002H\bH\u0016¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R$\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalReadScope;", "Landroidx/compose/ui/node/DelegatableNode;", "providedValues", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "getProvidedValues", "()Landroidx/compose/ui/modifier/ModifierLocalMap;", "current", "T", "Landroidx/compose/ui/modifier/ModifierLocal;", "getCurrent", "(Landroidx/compose/ui/modifier/ModifierLocal;)Ljava/lang/Object;", "provide", "", "key", "value", "(Landroidx/compose/ui/modifier/ModifierLocal;Ljava/lang/Object;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ModifierLocalModifierNode extends ModifierLocalReadScope, DelegatableNode {
    @Override // androidx.compose.ui.modifier.ModifierLocalReadScope
    <T> T getCurrent(ModifierLocal<T> modifierLocal);

    ModifierLocalMap getProvidedValues();

    <T> void provide(ModifierLocal<T> key, T value);

    /* JADX INFO: renamed from: androidx.compose.ui.modifier.ModifierLocalModifierNode$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ModifierLocalModifierNode.kt */
    public final /* synthetic */ class CC {
        public static void $default$provide(ModifierLocalModifierNode _this, ModifierLocal key, Object value) {
            Intrinsics.checkNotNullParameter(key, "key");
            if (!(_this.getProvidedValues() != EmptyMap.INSTANCE)) {
                throw new IllegalArgumentException("In order to provide locals you must override providedValues: ModifierLocalMap".toString());
            }
            if (!_this.getProvidedValues().contains$ui_release(key)) {
                throw new IllegalArgumentException(("Any provided key must be initially provided in the overridden providedValues: ModifierLocalMap property. Key " + key + " was not found.").toString());
            }
            _this.getProvidedValues().mo4324set$ui_release(key, value);
        }

        public static Object $default$getCurrent(ModifierLocalModifierNode _this, ModifierLocal $this$current) {
            DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
            int type$iv;
            boolean includeSelf$iv;
            NodeChain nodes;
            boolean includeSelf$iv2;
            int type$iv2;
            Intrinsics.checkNotNullParameter($this$current, "<this>");
            if (!_this.getNode().getIsAttached()) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            ModifierLocalModifierNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = _this;
            int type$iv3 = NodeKind.m4443constructorimpl(32);
            boolean includeSelf$iv3 = false;
            if (!$this$visitAncestors_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node".toString());
            }
            Modifier.Node node$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2.getNode().getParent();
            LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dY_u2dYKmho_u24default$iv2);
            while (layout$iv$iv != null) {
                Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
                if ((head$iv$iv.getAggregateChildKindSet() & type$iv3) != 0) {
                    while (node$iv$iv != null) {
                        if ((node$iv$iv.getKindSet() & type$iv3) != 0) {
                            Modifier.Node it$iv = node$iv$iv;
                            MutableVector mutableVector = null;
                            Modifier.Node nodePop = it$iv;
                            while (nodePop != null) {
                                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                                if (nodePop instanceof ModifierLocalModifierNode) {
                                    ModifierLocalModifierNode it = (ModifierLocalModifierNode) nodePop;
                                    includeSelf$iv2 = includeSelf$iv3;
                                    if (it.getProvidedValues().contains$ui_release($this$current)) {
                                        return it.getProvidedValues().get$ui_release($this$current);
                                    }
                                    type$iv2 = type$iv3;
                                } else {
                                    includeSelf$iv2 = includeSelf$iv3;
                                    Modifier.Node this_$iv$iv$iv = nodePop;
                                    int kindSet = this_$iv$iv$iv.getKindSet() & type$iv3;
                                    int i = 1;
                                    if ((kindSet != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                        type$iv2 = type$iv3;
                                    } else {
                                        int count$iv$iv = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            if ((next$iv$iv.getKindSet() & type$iv3) != 0) {
                                                count$iv$iv++;
                                                if (count$iv$iv == i) {
                                                    nodePop = next$iv$iv;
                                                    type$iv3 = type$iv3;
                                                } else {
                                                    MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                    Modifier.Node theNode$iv$iv = nodePop;
                                                    if (theNode$iv$iv != null) {
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv$iv);
                                                        }
                                                        nodePop = null;
                                                    }
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(next$iv$iv);
                                                    }
                                                    mutableVector = mutableVector2;
                                                    count$iv$iv = count$iv$iv;
                                                }
                                            } else {
                                                type$iv3 = type$iv3;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            type$iv3 = type$iv3;
                                            i = 1;
                                        }
                                        type$iv2 = type$iv3;
                                        if (count$iv$iv == 1) {
                                            includeSelf$iv3 = includeSelf$iv2;
                                            type$iv3 = type$iv2;
                                        }
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                                includeSelf$iv3 = includeSelf$iv2;
                                type$iv3 = type$iv2;
                            }
                        }
                        node$iv$iv = node$iv$iv.getParent();
                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                        includeSelf$iv3 = includeSelf$iv3;
                        type$iv3 = type$iv3;
                    }
                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                    type$iv = type$iv3;
                    includeSelf$iv = includeSelf$iv3;
                } else {
                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                    type$iv = type$iv3;
                    includeSelf$iv = includeSelf$iv3;
                }
                layout$iv$iv = layout$iv$iv.getParent$ui_release();
                node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
                includeSelf$iv3 = includeSelf$iv;
                type$iv3 = type$iv;
            }
            return $this$current.getDefaultFactory$ui_release().invoke();
        }
    }
}
