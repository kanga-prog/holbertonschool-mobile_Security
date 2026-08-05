package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusTraversal.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\u0007\u001a\u00020\b*\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000e\u001a\u000e\u0010\u000f\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\u0000\u001a\u000e\u0010\u0010\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\u0002\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0001H\u0000\u001a=\u0010\u0013\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0015H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017\"\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0005*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"activeChild", "Landroidx/compose/ui/focus/FocusTargetNode;", "getActiveChild", "(Landroidx/compose/ui/focus/FocusTargetNode;)Landroidx/compose/ui/focus/FocusTargetNode;", "isEligibleForFocusSearch", "", "(Landroidx/compose/ui/focus/FocusTargetNode;)Z", "customFocusSearch", "Landroidx/compose/ui/focus/FocusRequester;", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "customFocusSearch--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/focus/FocusRequester;", "findActiveFocusNode", "findNonDeactivatedParent", "focusRect", "Landroidx/compose/ui/geometry/Rect;", "focusSearch", "onFound", "Lkotlin/Function1;", "focusSearch-sMXa3k8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILandroidx/compose/ui/unit/LayoutDirection;Lkotlin/jvm/functions/Function1;)Z", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FocusTraversalKt {

    /* JADX INFO: compiled from: FocusTraversal.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: customFocusSearch--OM-vw8, reason: not valid java name */
    public static final FocusRequester m2683customFocusSearchOMvw8(FocusTargetNode customFocusSearch, int focusDirection, LayoutDirection layoutDirection) {
        FocusRequester end;
        FocusRequester start;
        Intrinsics.checkNotNullParameter(customFocusSearch, "$this$customFocusSearch");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        FocusProperties focusProperties = customFocusSearch.fetchFocusProperties$ui_release();
        if (FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2663getNextdhqQ8s())) {
            return focusProperties.getNext();
        }
        if (FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2665getPreviousdhqQ8s())) {
            return focusProperties.getPrevious();
        }
        if (FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2667getUpdhqQ8s())) {
            return focusProperties.getUp();
        }
        if (FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2658getDowndhqQ8s())) {
            return focusProperties.getDown();
        }
        if (FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2662getLeftdhqQ8s())) {
            switch (WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()]) {
                case 1:
                    start = focusProperties.getStart();
                    break;
                case 2:
                    start = focusProperties.getEnd();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            FocusRequester it = start;
            if (it == FocusRequester.INSTANCE.getDefault()) {
                start = null;
            }
            if (start != null) {
                return start;
            }
            return focusProperties.getLeft();
        }
        if (FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2666getRightdhqQ8s())) {
            switch (WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()]) {
                case 1:
                    end = focusProperties.getEnd();
                    break;
                case 2:
                    end = focusProperties.getStart();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            FocusRequester it2 = end;
            if (it2 == FocusRequester.INSTANCE.getDefault()) {
                end = null;
            }
            if (end != null) {
                return end;
            }
            return focusProperties.getRight();
        }
        if (FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2659getEnterdhqQ8s())) {
            return focusProperties.getEnter().invoke(FocusDirection.m2647boximpl(focusDirection));
        }
        if (FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2660getExitdhqQ8s())) {
            return focusProperties.getExit().invoke(FocusDirection.m2647boximpl(focusDirection));
        }
        throw new IllegalStateException("invalid FocusDirection".toString());
    }

    /* JADX INFO: renamed from: focusSearch-sMXa3k8, reason: not valid java name */
    public static final boolean m2684focusSearchsMXa3k8(FocusTargetNode focusSearch, int focusDirection, LayoutDirection layoutDirection, Function1<? super FocusTargetNode, Boolean> onFound) {
        int direction;
        Boolean boolM2696twoDimensionalFocusSearchOMvw8;
        Intrinsics.checkNotNullParameter(focusSearch, "$this$focusSearch");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        Intrinsics.checkNotNullParameter(onFound, "onFound");
        if (FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2663getNextdhqQ8s()) ? true : FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2665getPreviousdhqQ8s())) {
            return OneDimensionalFocusSearchKt.m2687oneDimensionalFocusSearchOMvw8(focusSearch, focusDirection, onFound);
        }
        if (FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2662getLeftdhqQ8s()) ? true : FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2666getRightdhqQ8s()) ? true : FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2667getUpdhqQ8s()) ? true : FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2658getDowndhqQ8s())) {
            Boolean boolM2696twoDimensionalFocusSearchOMvw9 = TwoDimensionalFocusSearchKt.m2696twoDimensionalFocusSearchOMvw8(focusSearch, focusDirection, onFound);
            if (boolM2696twoDimensionalFocusSearchOMvw9 != null) {
                return boolM2696twoDimensionalFocusSearchOMvw9.booleanValue();
            }
            return false;
        }
        if (!FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2659getEnterdhqQ8s())) {
            if (!FocusDirection.m2650equalsimpl0(focusDirection, FocusDirection.INSTANCE.m2660getExitdhqQ8s())) {
                throw new IllegalStateException(("Focus search invoked with invalid FocusDirection " + ((Object) FocusDirection.m2652toStringimpl(focusDirection))).toString());
            }
            FocusTargetNode focusTargetNodeFindActiveFocusNode = findActiveFocusNode(focusSearch);
            FocusTargetNode it = focusTargetNodeFindActiveFocusNode != null ? findNonDeactivatedParent(focusTargetNodeFindActiveFocusNode) : null;
            if (it == null || Intrinsics.areEqual(it, focusSearch)) {
                return false;
            }
            return onFound.invoke(it).booleanValue();
        }
        switch (WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()]) {
            case 1:
                direction = FocusDirection.INSTANCE.m2666getRightdhqQ8s();
                break;
            case 2:
                direction = FocusDirection.INSTANCE.m2662getLeftdhqQ8s();
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        FocusTargetNode focusTargetNodeFindActiveFocusNode2 = findActiveFocusNode(focusSearch);
        if (focusTargetNodeFindActiveFocusNode2 == null || (boolM2696twoDimensionalFocusSearchOMvw8 = TwoDimensionalFocusSearchKt.m2696twoDimensionalFocusSearchOMvw8(focusTargetNodeFindActiveFocusNode2, direction, onFound)) == null) {
            return false;
        }
        return boolM2696twoDimensionalFocusSearchOMvw8.booleanValue();
    }

    public static final Rect focusRect(FocusTargetNode $this$focusRect) {
        Rect rectLocalBoundingBoxOf;
        Intrinsics.checkNotNullParameter($this$focusRect, "<this>");
        NodeCoordinator it = $this$focusRect.getCoordinator();
        return (it == null || (rectLocalBoundingBoxOf = LayoutCoordinatesKt.findRootCoordinates(it).localBoundingBoxOf(it, false)) == null) ? Rect.INSTANCE.getZero() : rectLocalBoundingBoxOf;
    }

    public static final boolean isEligibleForFocusSearch(FocusTargetNode $this$isEligibleForFocusSearch) {
        LayoutNode layoutNode;
        LayoutNode layoutNode2;
        Intrinsics.checkNotNullParameter($this$isEligibleForFocusSearch, "<this>");
        NodeCoordinator coordinator$ui_release = $this$isEligibleForFocusSearch.getCoordinator();
        if ((coordinator$ui_release == null || (layoutNode2 = coordinator$ui_release.getLayoutNode()) == null || !layoutNode2.isPlaced()) ? false : true) {
            NodeCoordinator coordinator$ui_release2 = $this$isEligibleForFocusSearch.getCoordinator();
            if ((coordinator$ui_release2 == null || (layoutNode = coordinator$ui_release2.getLayoutNode()) == null || !layoutNode.isAttached()) ? false : true) {
                return true;
            }
        }
        return false;
    }

    public static final FocusTargetNode getActiveChild(FocusTargetNode $this$activeChild) {
        DelegatableNode $this$visitChildren_u2d6rFNWt0$iv;
        int type$iv;
        Intrinsics.checkNotNullParameter($this$activeChild, "<this>");
        if (!$this$activeChild.getNode().getIsAttached()) {
            return null;
        }
        FocusTargetNode $this$visitChildren_u2d6rFNWt0$iv2 = $this$activeChild;
        int type$iv2 = NodeKind.m4443constructorimpl(1024);
        if (!$this$visitChildren_u2d6rFNWt0$iv2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node".toString());
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2d6rFNWt0$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2d6rFNWt0$iv2.getNode());
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (branches$iv$iv.isNotEmpty()) {
            int i = 1;
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        type$iv2 = type$iv2;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & type$iv2) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode it = (FocusTargetNode) nodePop;
                                switch (WhenMappings.$EnumSwitchMapping$1[it.getFocusState().ordinal()]) {
                                    case 1:
                                    case 2:
                                    case 3:
                                        return it;
                                    case 4:
                                    default:
                                        $this$visitChildren_u2d6rFNWt0$iv = $this$visitChildren_u2d6rFNWt0$iv2;
                                        type$iv = type$iv2;
                                        break;
                                }
                            } else {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & type$iv2) != 0) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if ((next$iv$iv.getKindSet() & type$iv2) != 0) {
                                            count$iv$iv++;
                                            if (count$iv$iv == i) {
                                                nodePop = next$iv$iv;
                                                $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv2;
                                                type$iv2 = type$iv2;
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
                                            }
                                        } else {
                                            $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv2;
                                            type$iv2 = type$iv2;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv2;
                                        type$iv2 = type$iv2;
                                        i = 1;
                                    }
                                    $this$visitChildren_u2d6rFNWt0$iv = $this$visitChildren_u2d6rFNWt0$iv2;
                                    type$iv = type$iv2;
                                    if (count$iv$iv == 1) {
                                        $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv;
                                        type$iv2 = type$iv;
                                        i = 1;
                                    }
                                } else {
                                    $this$visitChildren_u2d6rFNWt0$iv = $this$visitChildren_u2d6rFNWt0$iv2;
                                    type$iv = type$iv2;
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                            $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv;
                            type$iv2 = type$iv;
                            i = 1;
                        }
                        type$iv2 = type$iv2;
                        break;
                    }
                    node$iv$iv = node$iv$iv.getChild();
                    type$iv2 = type$iv2;
                    i = 1;
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
            }
        }
        return null;
    }

    public static final FocusTargetNode findActiveFocusNode(FocusTargetNode $this$findActiveFocusNode) {
        DelegatableNode $this$visitChildren_u2d6rFNWt0$iv;
        int type$iv;
        int i;
        Intrinsics.checkNotNullParameter($this$findActiveFocusNode, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$1[$this$findActiveFocusNode.getFocusState().ordinal()]) {
            case 1:
            case 3:
                return $this$findActiveFocusNode;
            case 2:
                FocusTargetNode $this$visitChildren_u2d6rFNWt0$iv2 = $this$findActiveFocusNode;
                int type$iv2 = NodeKind.m4443constructorimpl(1024);
                int i2 = 0;
                if (!$this$visitChildren_u2d6rFNWt0$iv2.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitChildren called on an unattached node".toString());
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitChildren_u2d6rFNWt0$iv2.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2d6rFNWt0$iv2.getNode());
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (branches$iv$iv.isNotEmpty()) {
                    int i3 = 1;
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                i2 = i2;
                            }
                            if ((node$iv$iv.getKindSet() & type$iv2) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector = null;
                                Modifier.Node nodePop = it$iv;
                                while (nodePop != null) {
                                    if (nodePop instanceof FocusTargetNode) {
                                        FocusTargetNode node = (FocusTargetNode) nodePop;
                                        FocusTargetNode it = findActiveFocusNode(node);
                                        if (it != null) {
                                            return it;
                                        }
                                        $this$visitChildren_u2d6rFNWt0$iv = $this$visitChildren_u2d6rFNWt0$iv2;
                                        type$iv = type$iv2;
                                        i = i2;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv = nodePop;
                                        if (((this_$iv$iv$iv.getKindSet() & type$iv2) != 0) && (nodePop instanceof DelegatingNode)) {
                                            int count$iv$iv = 0;
                                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                            while (node$iv$iv$iv != null) {
                                                Modifier.Node next$iv$iv = node$iv$iv$iv;
                                                if ((next$iv$iv.getKindSet() & type$iv2) != 0) {
                                                    count$iv$iv++;
                                                    if (count$iv$iv == i3) {
                                                        nodePop = next$iv$iv;
                                                        $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv2;
                                                        type$iv2 = type$iv2;
                                                        i2 = i2;
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
                                                    }
                                                } else {
                                                    $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv2;
                                                    type$iv2 = type$iv2;
                                                    i2 = i2;
                                                }
                                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                                $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv2;
                                                type$iv2 = type$iv2;
                                                i2 = i2;
                                                i3 = 1;
                                            }
                                            $this$visitChildren_u2d6rFNWt0$iv = $this$visitChildren_u2d6rFNWt0$iv2;
                                            type$iv = type$iv2;
                                            i = i2;
                                            if (count$iv$iv == 1) {
                                                $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv;
                                                type$iv2 = type$iv;
                                                i2 = i;
                                                i3 = 1;
                                            }
                                        } else {
                                            $this$visitChildren_u2d6rFNWt0$iv = $this$visitChildren_u2d6rFNWt0$iv2;
                                            type$iv = type$iv2;
                                            i = i2;
                                        }
                                    }
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv;
                                    type$iv2 = type$iv;
                                    i2 = i;
                                    i3 = 1;
                                }
                                i2 = i2;
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            $this$visitChildren_u2d6rFNWt0$iv2 = $this$visitChildren_u2d6rFNWt0$iv2;
                            i2 = i2;
                            i3 = 1;
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv);
                    }
                    break;
                }
                return null;
            case 4:
                return null;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final FocusTargetNode findNonDeactivatedParent(FocusTargetNode $this$findNonDeactivatedParent) {
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
        int type$iv;
        boolean includeSelf$iv;
        int i;
        NodeChain nodes;
        int type$iv2;
        boolean includeSelf$iv2;
        int i2;
        int type$iv3;
        FocusTargetNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$findNonDeactivatedParent;
        int type$iv4 = NodeKind.m4443constructorimpl(1024);
        boolean includeSelf$iv3 = false;
        int i3 = 0;
        if (!$this$visitAncestors_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2.getNode().getParent();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dY_u2dYKmho_u24default$iv2);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & type$iv4) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode it = (FocusTargetNode) nodePop;
                                if (it.fetchFocusProperties$ui_release().getCanFocus()) {
                                    return it;
                                }
                                type$iv2 = type$iv4;
                                includeSelf$iv2 = includeSelf$iv3;
                                i2 = i3;
                            } else {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & type$iv4) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    type$iv2 = type$iv4;
                                    includeSelf$iv2 = includeSelf$iv3;
                                    i2 = i3;
                                } else {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if ((next$iv$iv.getKindSet() & type$iv4) != 0) {
                                            count$iv$iv++;
                                            type$iv3 = type$iv4;
                                            if (count$iv$iv == 1) {
                                                nodePop = next$iv$iv;
                                                includeSelf$iv3 = includeSelf$iv3;
                                                i3 = i3;
                                            } else {
                                                mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                Modifier.Node theNode$iv$iv = nodePop;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector != null) {
                                                        mutableVector.add(theNode$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector != null) {
                                                    mutableVector.add(next$iv$iv);
                                                }
                                                count$iv$iv = count$iv$iv;
                                            }
                                        } else {
                                            type$iv3 = type$iv4;
                                            includeSelf$iv3 = includeSelf$iv3;
                                            i3 = i3;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        type$iv4 = type$iv3;
                                        includeSelf$iv3 = includeSelf$iv3;
                                        i3 = i3;
                                    }
                                    type$iv2 = type$iv4;
                                    includeSelf$iv2 = includeSelf$iv3;
                                    i2 = i3;
                                    if (count$iv$iv == 1) {
                                        type$iv4 = type$iv2;
                                        includeSelf$iv3 = includeSelf$iv2;
                                        i3 = i2;
                                    }
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                            type$iv4 = type$iv2;
                            includeSelf$iv3 = includeSelf$iv2;
                            i3 = i2;
                        }
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                    type$iv4 = type$iv4;
                    includeSelf$iv3 = includeSelf$iv3;
                    i3 = i3;
                }
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                type$iv = type$iv4;
                includeSelf$iv = includeSelf$iv3;
                i = i3;
            } else {
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                type$iv = type$iv4;
                includeSelf$iv = includeSelf$iv3;
                i = i3;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui_release();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
            type$iv4 = type$iv;
            includeSelf$iv3 = includeSelf$iv;
            i3 = i;
        }
        return null;
    }
}
