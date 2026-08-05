package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b \n\u0002\u0010(\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\b9\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u009f\u00012\u00020\u0001:\u0002\u009f\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\n2\u0006\u00104\u001a\u00020\nJ\u000e\u00105\u001a\u0002022\u0006\u00104\u001a\u00020\nJ\u0010\u00106\u001a\u00020\u00072\b\b\u0002\u00107\u001a\u00020\nJ\u000e\u00108\u001a\u00020\n2\u0006\u00106\u001a\u00020\u0007J\r\u00109\u001a\u000202H\u0000¢\u0006\u0002\b:J\u0006\u0010;\u001a\u000202J\u0010\u0010<\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\nH\u0002J\b\u0010>\u001a\u000202H\u0002J\u0006\u0010?\u001a\u000202J\u0010\u0010@\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\nH\u0002J\u0010\u0010A\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\nH\u0002J \u0010B\u001a\u00020\n2\u0006\u00106\u001a\u00020\n2\u0006\u0010C\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010D\u001a\u00020\n2\u0006\u00107\u001a\u00020\nH\u0002J\u0010\u0010E\u001a\u00020\n2\u0006\u0010D\u001a\u00020\nH\u0002J(\u0010F\u001a\u00020\n2\u0006\u00107\u001a\u00020\n2\u0006\u0010G\u001a\u00020\n2\u0006\u0010C\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010H\u001a\u00020\nJ\u0006\u0010I\u001a\u000202J\u000e\u0010J\u001a\u0002022\u0006\u00106\u001a\u00020\u0007J\u000e\u0010J\u001a\u0002022\u0006\u00107\u001a\u00020\nJ \u0010K\u001a\u0002022\u0006\u0010\"\u001a\u00020\n2\u0006\u0010H\u001a\u00020\n2\u0006\u0010L\u001a\u00020\nH\u0002J\u0010\u0010M\u001a\u0004\u0018\u00010\u00012\u0006\u00107\u001a\u00020\nJ\u0010\u0010N\u001a\u00020\n2\u0006\u00107\u001a\u00020\nH\u0002J\u000e\u0010O\u001a\u00020\n2\u0006\u00107\u001a\u00020\nJ\u0010\u0010P\u001a\u0004\u0018\u00010\u00012\u0006\u00107\u001a\u00020\nJ\u000e\u0010Q\u001a\u00020\n2\u0006\u00107\u001a\u00020\nJ\u000e\u0010R\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010SJ\u0006\u0010T\u001a\u00020UJ\u000e\u0010V\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\nJ\u0016\u0010W\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\n2\u0006\u0010=\u001a\u00020\nJ\u000e\u0010X\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\nJ\u0010\u0010Y\u001a\u0002022\b\u0010Z\u001a\u0004\u0018\u00010\u0001J\u0010\u0010[\u001a\u0002022\u0006\u0010&\u001a\u00020\nH\u0002J\u000e\u0010\\\u001a\u0002022\u0006\u0010]\u001a\u00020\nJ\u0018\u0010^\u001a\u0002022\u0006\u0010&\u001a\u00020\n2\u0006\u0010=\u001a\u00020\nH\u0002J\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\nJ\u000e\u0010_\u001a\b\u0012\u0004\u0012\u00020\n0`H\u0002J\u0010\u0010a\u001a\u0002022\b\b\u0002\u0010=\u001a\u00020\nJ \u0010b\u001a\u0002022\u0006\u0010c\u001a\u00020\n2\u0006\u0010d\u001a\u00020\n2\u0006\u0010&\u001a\u00020\nH\u0002J&\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00070`2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00107\u001a\u00020\n2\b\b\u0002\u0010f\u001a\u00020\u000eJ\u000e\u0010g\u001a\u0002022\u0006\u0010h\u001a\u00020\nJ\u0010\u0010i\u001a\u0002022\u0006\u00107\u001a\u00020\nH\u0002J$\u0010j\u001a\b\u0012\u0004\u0012\u00020\u00070`2\u0006\u0010h\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00107\u001a\u00020\nJ\u0018\u0010k\u001a\u0002022\u0006\u00107\u001a\u00020\n2\u0006\u0010=\u001a\u00020\nH\u0002J$\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00070`2\u0006\u00106\u001a\u00020\u00072\u0006\u0010h\u001a\u00020\n2\u0006\u0010m\u001a\u00020\u0000J\u0010\u0010n\u001a\u0004\u0018\u00010\u00012\u0006\u00106\u001a\u00020\u0007J\u0010\u0010n\u001a\u0004\u0018\u00010\u00012\u0006\u00107\u001a\u00020\nJ\u000e\u0010 \u001a\u00020\n2\u0006\u00107\u001a\u00020\nJ\u000e\u0010\"\u001a\u00020\n2\u0006\u00106\u001a\u00020\u0007J\u000e\u0010\"\u001a\u00020\n2\u0006\u00107\u001a\u00020\nJ\u0010\u0010o\u001a\u00020\n2\u0006\u00107\u001a\u00020\nH\u0002J\u0018\u0010p\u001a\u00020\n2\u0006\u00107\u001a\u00020\n2\u0006\u0010G\u001a\u00020\nH\u0002J\b\u0010q\u001a\u000202H\u0002J\u0018\u0010r\u001a\u00020\u000e2\u0006\u0010G\u001a\u00020\n2\u0006\u0010&\u001a\u00020\nH\u0002J\u0006\u0010s\u001a\u00020\u000eJ\u0018\u0010t\u001a\u00020\u000e2\u0006\u0010u\u001a\u00020\n2\u0006\u0010v\u001a\u00020\nH\u0002J \u0010w\u001a\u0002022\u0006\u0010u\u001a\u00020\n2\u0006\u0010v\u001a\u00020\n2\u0006\u0010=\u001a\u00020\nH\u0002J\u0006\u0010x\u001a\u000202J\b\u0010y\u001a\u00020\nH\u0002J\b\u0010z\u001a\u000202H\u0002J\u000e\u0010{\u001a\u0002022\u0006\u00106\u001a\u00020\u0007J\u0010\u0010|\u001a\u0002022\b\u0010Z\u001a\u0004\u0018\u00010\u0001J\u001a\u0010|\u001a\u0004\u0018\u00010\u00012\u0006\u00107\u001a\u00020\n2\b\u0010Z\u001a\u0004\u0018\u00010\u0001J\b\u0010}\u001a\u0004\u0018\u00010\u0001J\u0006\u0010~\u001a\u00020\nJ\u0006\u0010\u007f\u001a\u000202J\u0019\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u00012\u0006\u00106\u001a\u00020\u00072\u0006\u00107\u001a\u00020\nJ\u001a\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u00012\u0007\u0010\u0081\u0001\u001a\u00020\n2\u0006\u00107\u001a\u00020\nJ\u001a\u0010\u0082\u0001\u001a\u0002022\u0006\u0010]\u001a\u00020\n2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0001J%\u0010\u0082\u0001\u001a\u0002022\u0006\u0010]\u001a\u00020\n2\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00012\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0001J\u0007\u0010\u0085\u0001\u001a\u000202J\u000f\u0010\u0085\u0001\u001a\u0002022\u0006\u0010]\u001a\u00020\nJ\u001a\u0010\u0085\u0001\u001a\u0002022\u0006\u0010]\u001a\u00020\n2\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0001J/\u0010\u0085\u0001\u001a\u0002022\u0006\u0010]\u001a\u00020\n2\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001f\u001a\u00020\u000e2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0001H\u0002J\u001a\u0010\u0087\u0001\u001a\u0002022\u0006\u0010]\u001a\u00020\n2\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0001J$\u0010\u0087\u0001\u001a\u0002022\u0006\u0010]\u001a\u00020\n2\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00012\b\u0010n\u001a\u0004\u0018\u00010\u0001J\t\u0010\u0088\u0001\u001a\u00020UH\u0016J\u0013\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u00012\b\u0010Z\u001a\u0004\u0018\u00010\u0001J\u001b\u0010\u008a\u0001\u001a\u0002022\u0007\u0010\u008b\u0001\u001a\u00020\n2\u0007\u0010\u008c\u0001\u001a\u00020\nH\u0002J\u0011\u0010\u008d\u0001\u001a\u0002022\b\u0010Z\u001a\u0004\u0018\u00010\u0001J\u0011\u0010\u008e\u0001\u001a\u0002022\u0006\u0010=\u001a\u00020\nH\u0002J\u0019\u0010\u008f\u0001\u001a\u0002022\u0006\u0010=\u001a\u00020\n2\u0006\u0010|\u001a\u00020%H\u0002J\u0019\u0010\u0090\u0001\u001a\u0002022\u0006\u00106\u001a\u00020\u00072\b\u0010Z\u001a\u0004\u0018\u00010\u0001J\u0011\u0010\u0090\u0001\u001a\u0002022\b\u0010Z\u001a\u0004\u0018\u00010\u0001J\u001b\u0010\u0091\u0001\u001a\u0002022\u0006\u00107\u001a\u00020\n2\b\u0010Z\u001a\u0004\u0018\u00010\u0001H\u0002J\u0011\u0010\u0092\u0001\u001a\u0002022\b\u0010Z\u001a\u0004\u0018\u00010\u0001J\u000f\u0010\u0093\u0001\u001a\u000202H\u0000¢\u0006\u0003\b\u0094\u0001J\u000f\u0010\u0095\u0001\u001a\u000202H\u0000¢\u0006\u0003\b\u0096\u0001J\u0015\u0010\u0097\u0001\u001a\u00020\n*\u00020\u001c2\u0006\u00103\u001a\u00020\nH\u0002J\u0014\u0010D\u001a\u00020\n*\u00020\u001c2\u0006\u00103\u001a\u00020\nH\u0002J\u0013\u0010\u0098\u0001\u001a\b\u0012\u0004\u0012\u00020\n0`*\u00020\u001cH\u0002J\u001b\u0010\u0099\u0001\u001a\u000202*\b0\u009a\u0001j\u0003`\u009b\u00012\u0006\u00107\u001a\u00020\nH\u0002J\u0015\u0010\u009c\u0001\u001a\u00020\n*\u00020\u001c2\u0006\u00103\u001a\u00020\nH\u0002J\u0014\u0010\"\u001a\u00020\n*\u00020\u001c2\u0006\u00107\u001a\u00020\nH\u0002J\u0015\u0010\u009d\u0001\u001a\u00020\n*\u00020\u001c2\u0006\u00103\u001a\u00020\nH\u0002J\u001d\u0010\u009e\u0001\u001a\u000202*\u00020\u001c2\u0006\u00103\u001a\u00020\n2\u0006\u0010D\u001a\u00020\nH\u0002R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001e\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0011R\u0011\u0010\u001f\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0011R\u000e\u0010 \u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\fR\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\fR\u0018\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010)X\u0082\u000e¢\u0006\u0004\n\u0002\u0010*R\u000e\u0010+\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100¨\u0006 \u0001"}, d2 = {"Landroidx/compose/runtime/SlotWriter;", "", "table", "Landroidx/compose/runtime/SlotTable;", "(Landroidx/compose/runtime/SlotTable;)V", "anchors", "Ljava/util/ArrayList;", "Landroidx/compose/runtime/Anchor;", "Lkotlin/collections/ArrayList;", "capacity", "", "getCapacity", "()I", "<set-?>", "", "closed", "getClosed", "()Z", "currentGroup", "getCurrentGroup", "currentGroupEnd", "currentSlot", "currentSlotEnd", "endStack", "Landroidx/compose/runtime/IntStack;", "groupGapLen", "groupGapStart", "groups", "", "insertCount", "isGroupEnd", "isNode", "nodeCount", "nodeCountStack", "parent", "getParent", "pendingRecalculateMarks", "Landroidx/compose/runtime/PrioritySet;", "size", "getSize$runtime_release", "slots", "", "[Ljava/lang/Object;", "slotsGapLen", "slotsGapOwner", "slotsGapStart", "startStack", "getTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "addToGroupSizeAlongSpine", "", "address", "amount", "advanceBy", "anchor", "index", "anchorIndex", "bashGroup", "bashGroup$runtime_release", "beginInsert", "childContainsAnyMarks", "group", "clearSlotGap", "close", "containsAnyGroupMarks", "containsGroupMark", "dataAnchorToDataIndex", "gapLen", "dataIndex", "dataIndexToDataAddress", "dataIndexToDataAnchor", "gapStart", "endGroup", "endInsert", "ensureStarted", "fixParentAnchorsFor", "firstChild", "groupAux", "groupIndexToAddress", "groupKey", "groupObjectKey", "groupSize", "groupSlots", "", "groupsAsString", "", "indexInCurrentGroup", "indexInGroup", "indexInParent", "insertAux", "value", "insertGroups", "insertParentGroup", "key", "insertSlots", "keys", "", "markGroup", "moveAnchors", "originalLocation", "newLocation", "moveFrom", "removeSourceGroup", "moveGroup", "offset", "moveGroupGapTo", "moveIntoGroupFrom", "moveSlotGapTo", "moveTo", "writer", "node", "parentAnchorToIndex", "parentIndexToAnchor", "recalculateMarks", "removeAnchors", "removeGroup", "removeGroups", "start", "len", "removeSlots", "reset", "restoreCurrentGroupEnd", "saveCurrentGroupEnd", "seek", "set", "skip", "skipGroup", "skipToGroupEnd", "slot", "groupIndex", "startData", "aux", "objectKey", "startGroup", "dataKey", "startNode", "toString", "update", "updateAnchors", "previousGapStart", "newGapStart", "updateAux", "updateContainsMark", "updateContainsMarkNow", "updateNode", "updateNodeOfGroup", "updateParentNode", "verifyDataAnchors", "verifyDataAnchors$runtime_release", "verifyParentAnchors", "verifyParentAnchors$runtime_release", "auxIndex", "dataIndexes", "groupAsString", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "nodeIndex", "slotIndex", "updateDataIndex", "Companion", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SlotWriter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private ArrayList<Anchor> anchors;
    private boolean closed;
    private int currentGroup;
    private int currentGroupEnd;
    private int currentSlot;
    private int currentSlotEnd;
    private final IntStack endStack;
    private int groupGapLen;
    private int groupGapStart;
    private int[] groups;
    private int insertCount;
    private int nodeCount;
    private final IntStack nodeCountStack;
    private int parent;
    private PrioritySet pendingRecalculateMarks;
    private Object[] slots;
    private int slotsGapLen;
    private int slotsGapOwner;
    private int slotsGapStart;
    private final IntStack startStack;
    private final SlotTable table;

    public SlotWriter(SlotTable table) {
        Intrinsics.checkNotNullParameter(table, "table");
        this.table = table;
        this.groups = table.getGroups();
        this.slots = table.getSlots();
        this.anchors = table.getAnchors$runtime_release();
        this.groupGapStart = table.getGroupsSize();
        this.groupGapLen = (this.groups.length / 5) - table.getGroupsSize();
        this.currentGroupEnd = table.getGroupsSize();
        this.slotsGapStart = table.getSlotsSize();
        this.slotsGapLen = this.slots.length - table.getSlotsSize();
        this.slotsGapOwner = table.getGroupsSize();
        this.startStack = new IntStack();
        this.endStack = new IntStack();
        this.nodeCountStack = new IntStack();
        this.parent = -1;
    }

    /* JADX INFO: renamed from: getTable$runtime_release, reason: from getter */
    public final SlotTable getTable() {
        return this.table;
    }

    public final int getCurrentGroup() {
        return this.currentGroup;
    }

    public final boolean isGroupEnd() {
        return this.currentGroup == this.currentGroupEnd;
    }

    public final boolean isNode() {
        int i = this.currentGroup;
        return i < this.currentGroupEnd && SlotTableKt.isNode(this.groups, groupIndexToAddress(i));
    }

    public final boolean isNode(int index) {
        return SlotTableKt.isNode(this.groups, groupIndexToAddress(index));
    }

    public final int nodeCount(int index) {
        return SlotTableKt.nodeCount(this.groups, groupIndexToAddress(index));
    }

    public final int groupKey(int index) {
        return SlotTableKt.key(this.groups, groupIndexToAddress(index));
    }

    public final Object groupObjectKey(int index) {
        int address = groupIndexToAddress(index);
        if (SlotTableKt.hasObjectKey(this.groups, address)) {
            return this.slots[SlotTableKt.objectKeyIndex(this.groups, address)];
        }
        return null;
    }

    public final int groupSize(int index) {
        return SlotTableKt.groupSize(this.groups, groupIndexToAddress(index));
    }

    public final Object groupAux(int index) {
        int address = groupIndexToAddress(index);
        return SlotTableKt.hasAux(this.groups, address) ? this.slots[auxIndex(this.groups, address)] : Composer.INSTANCE.getEmpty();
    }

    public final boolean indexInParent(int index) {
        int i = this.parent;
        return (index > i && index < this.currentGroupEnd) || (i == 0 && index == 0);
    }

    public final boolean indexInCurrentGroup(int index) {
        return indexInGroup(index, this.currentGroup);
    }

    public final boolean indexInGroup(int index, int group) {
        int openIndex;
        int end;
        if (group == this.parent) {
            end = this.currentGroupEnd;
        } else {
            end = (group <= this.startStack.peekOr(0) && (openIndex = this.startStack.indexOf(group)) >= 0) ? (getCapacity() - this.groupGapLen) - this.endStack.peek(openIndex) : groupSize(group) + group;
        }
        return index > group && index < end;
    }

    public final Object node(int index) {
        int address = groupIndexToAddress(index);
        if (SlotTableKt.isNode(this.groups, address)) {
            return this.slots[dataIndexToDataAddress(nodeIndex(this.groups, address))];
        }
        return null;
    }

    public final Object node(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        return node(anchor.toIndexFor(this));
    }

    public final int getParent() {
        return this.parent;
    }

    public final int parent(int index) {
        return parent(this.groups, index);
    }

    public final int parent(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        if (anchor.getValid()) {
            return parent(this.groups, anchorIndex(anchor));
        }
        return -1;
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final void close() {
        this.closed = true;
        if (this.startStack.isEmpty()) {
            moveGroupGapTo(getSize$runtime_release());
            moveSlotGapTo(this.slots.length - this.slotsGapLen, this.groupGapStart);
            clearSlotGap();
            recalculateMarks();
        }
        this.table.close$runtime_release(this, this.groups, this.groupGapStart, this.slots, this.slotsGapStart, this.anchors);
    }

    public final void reset() {
        boolean value$iv = this.insertCount == 0;
        if (value$iv) {
            recalculateMarks();
            this.currentGroup = 0;
            this.currentGroupEnd = getCapacity() - this.groupGapLen;
            this.currentSlot = 0;
            this.currentSlotEnd = 0;
            this.nodeCount = 0;
            return;
        }
        ComposerKt.composeRuntimeError("Cannot reset when inserting".toString());
        throw new KotlinNothingValueException();
    }

    public final Object update(Object value) {
        Object result = skip();
        set(value);
        return result;
    }

    public final void updateAux(Object value) {
        int address = groupIndexToAddress(this.currentGroup);
        boolean value$iv = SlotTableKt.hasAux(this.groups, address);
        if (value$iv) {
            this.slots[dataIndexToDataAddress(auxIndex(this.groups, address))] = value;
        } else {
            ComposerKt.composeRuntimeError("Updating the data of a group that was not created with a data slot".toString());
            throw new KotlinNothingValueException();
        }
    }

    public final void insertAux(Object value) {
        boolean value$iv = this.insertCount >= 0;
        if (value$iv) {
            int parent = this.parent;
            int parentGroupAddress = groupIndexToAddress(parent);
            boolean value$iv2 = !SlotTableKt.hasAux(this.groups, parentGroupAddress);
            if (value$iv2) {
                insertSlots(1, parent);
                int auxIndex = auxIndex(this.groups, parentGroupAddress);
                int auxAddress = dataIndexToDataAddress(auxIndex);
                int i = this.currentSlot;
                if (i > auxIndex) {
                    int slotsToMove = i - auxIndex;
                    if (!(slotsToMove < 3)) {
                        throw new IllegalStateException("Moving more than two slot not supported".toString());
                    }
                    if (slotsToMove > 1) {
                        Object[] objArr = this.slots;
                        objArr[auxAddress + 2] = objArr[auxAddress + 1];
                    }
                    Object[] objArr2 = this.slots;
                    objArr2[auxAddress + 1] = objArr2[auxAddress];
                }
                SlotTableKt.addAux(this.groups, parentGroupAddress);
                this.slots[auxAddress] = value;
                this.currentSlot++;
                return;
            }
            ComposerKt.composeRuntimeError("Group already has auxiliary data".toString());
            throw new KotlinNothingValueException();
        }
        ComposerKt.composeRuntimeError("Cannot insert auxiliary data when not inserting".toString());
        throw new KotlinNothingValueException();
    }

    public final void updateNode(Object value) {
        updateNodeOfGroup(this.currentGroup, value);
    }

    public final void updateNode(Anchor anchor, Object value) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        updateNodeOfGroup(anchor.toIndexFor(this), value);
    }

    public final void updateParentNode(Object value) {
        updateNodeOfGroup(this.parent, value);
    }

    public final void set(Object value) {
        int i = this.currentSlot;
        boolean value$iv = i <= this.currentSlotEnd;
        if (value$iv) {
            this.slots[dataIndexToDataAddress(i - 1)] = value;
        } else {
            ComposerKt.composeRuntimeError("Writing to an invalid slot".toString());
            throw new KotlinNothingValueException();
        }
    }

    public final Object set(int index, Object value) {
        int address = groupIndexToAddress(this.currentGroup);
        int slotsStart = slotIndex(this.groups, address);
        int slotsEnd = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1));
        int slotsIndex = slotsStart + index;
        boolean value$iv = slotsIndex >= slotsStart && slotsIndex < slotsEnd;
        if (!value$iv) {
            Object message$iv = "Write to an invalid slot index " + index + " for group " + this.currentGroup;
            ComposerKt.composeRuntimeError(message$iv.toString());
            throw new KotlinNothingValueException();
        }
        int slotAddress = dataIndexToDataAddress(slotsIndex);
        Object[] objArr = this.slots;
        Object result = objArr[slotAddress];
        objArr[slotAddress] = value;
        return result;
    }

    public final Object skip() {
        if (this.insertCount > 0) {
            insertSlots(1, this.parent);
        }
        Object[] objArr = this.slots;
        int i = this.currentSlot;
        this.currentSlot = i + 1;
        return objArr[dataIndexToDataAddress(i)];
    }

    public final Object slot(Anchor anchor, int index) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        return slot(anchorIndex(anchor), index);
    }

    public final Object slot(int groupIndex, int index) {
        int address = groupIndexToAddress(groupIndex);
        int slotsStart = slotIndex(this.groups, address);
        int slotsEnd = dataIndex(this.groups, groupIndexToAddress(groupIndex + 1));
        int slotsIndex = slotsStart + index;
        boolean z = false;
        if (slotsStart <= slotsIndex && slotsIndex < slotsEnd) {
            z = true;
        }
        if (!z) {
            return Composer.INSTANCE.getEmpty();
        }
        int slotAddress = dataIndexToDataAddress(slotsIndex);
        return this.slots[slotAddress];
    }

    public final void advanceBy(int amount) {
        boolean value$iv = amount >= 0;
        if (value$iv) {
            if (!(this.insertCount <= 0)) {
                throw new IllegalStateException("Cannot call seek() while inserting".toString());
            }
            if (amount == 0) {
                return;
            }
            int index = this.currentGroup + amount;
            boolean value$iv2 = index >= this.parent && index <= this.currentGroupEnd;
            if (!value$iv2) {
                Object message$iv = "Cannot seek outside the current group (" + this.parent + '-' + this.currentGroupEnd + ')';
                ComposerKt.composeRuntimeError(message$iv.toString());
                throw new KotlinNothingValueException();
            }
            this.currentGroup = index;
            int newSlot = dataIndex(this.groups, groupIndexToAddress(index));
            this.currentSlot = newSlot;
            this.currentSlotEnd = newSlot;
            return;
        }
        ComposerKt.composeRuntimeError("Cannot seek backwards".toString());
        throw new KotlinNothingValueException();
    }

    public final void seek(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        advanceBy(anchor.toIndexFor(this) - this.currentGroup);
    }

    public final void skipToGroupEnd() {
        int newGroup = this.currentGroupEnd;
        this.currentGroup = newGroup;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(newGroup));
    }

    public final void beginInsert() {
        int i = this.insertCount;
        this.insertCount = i + 1;
        if (i == 0) {
            saveCurrentGroupEnd();
        }
    }

    public final void endInsert() {
        int i = this.insertCount;
        if (!(i > 0)) {
            throw new IllegalStateException("Unbalanced begin/end insert".toString());
        }
        int i2 = i - 1;
        this.insertCount = i2;
        if (i2 == 0) {
            boolean value$iv = this.nodeCountStack.getTos() == this.startStack.getTos();
            if (value$iv) {
                restoreCurrentGroupEnd();
            } else {
                ComposerKt.composeRuntimeError("startGroup/endGroup mismatch while inserting".toString());
                throw new KotlinNothingValueException();
            }
        }
    }

    public final void startGroup() {
        boolean value$iv = this.insertCount == 0;
        if (value$iv) {
            startGroup(0, Composer.INSTANCE.getEmpty(), false, Composer.INSTANCE.getEmpty());
        } else {
            ComposerKt.composeRuntimeError("Key must be supplied when inserting".toString());
            throw new KotlinNothingValueException();
        }
    }

    public final void startGroup(int key) {
        startGroup(key, Composer.INSTANCE.getEmpty(), false, Composer.INSTANCE.getEmpty());
    }

    public final void startGroup(int key, Object dataKey) {
        startGroup(key, dataKey, false, Composer.INSTANCE.getEmpty());
    }

    public final void startNode(int key, Object objectKey) {
        startGroup(key, objectKey, true, Composer.INSTANCE.getEmpty());
    }

    public final void startNode(int key, Object objectKey, Object node) {
        startGroup(key, objectKey, true, node);
    }

    public final void startData(int key, Object objectKey, Object aux) {
        startGroup(key, objectKey, false, aux);
    }

    public final void startData(int key, Object aux) {
        startGroup(key, Composer.INSTANCE.getEmpty(), false, aux);
    }

    private final void startGroup(int key, Object objectKey, boolean isNode, Object aux) {
        int iGroupSize;
        boolean z = this.insertCount > 0;
        this.nodeCountStack.push(this.nodeCount);
        if (z) {
            insertGroups(1);
            int i = this.currentGroup;
            int iGroupIndexToAddress = groupIndexToAddress(i);
            boolean z2 = objectKey != Composer.INSTANCE.getEmpty();
            boolean z3 = (isNode || aux == Composer.INSTANCE.getEmpty()) ? false : true;
            SlotTableKt.initGroup(this.groups, iGroupIndexToAddress, key, isNode, z2, z3, this.parent, this.currentSlot);
            this.currentSlotEnd = this.currentSlot;
            int i2 = (isNode ? 1 : 0) + (z2 ? 1 : 0) + (z3 ? 1 : 0);
            if (i2 > 0) {
                insertSlots(i2, i);
                Object[] objArr = this.slots;
                int i3 = this.currentSlot;
                if (isNode) {
                    objArr[i3] = aux;
                    i3++;
                }
                if (z2) {
                    objArr[i3] = objectKey;
                    i3++;
                }
                if (z3) {
                    objArr[i3] = aux;
                    i3++;
                }
                this.currentSlot = i3;
            }
            this.nodeCount = 0;
            iGroupSize = i + 1;
            this.parent = i;
            this.currentGroup = iGroupSize;
        } else {
            this.startStack.push(this.parent);
            saveCurrentGroupEnd();
            int i4 = this.currentGroup;
            int iGroupIndexToAddress2 = groupIndexToAddress(i4);
            if (!Intrinsics.areEqual(aux, Composer.INSTANCE.getEmpty())) {
                if (isNode) {
                    updateNode(aux);
                } else {
                    updateAux(aux);
                }
            }
            this.currentSlot = slotIndex(this.groups, iGroupIndexToAddress2);
            this.currentSlotEnd = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1));
            this.nodeCount = SlotTableKt.nodeCount(this.groups, iGroupIndexToAddress2);
            this.parent = i4;
            this.currentGroup = i4 + 1;
            iGroupSize = SlotTableKt.groupSize(this.groups, iGroupIndexToAddress2) + i4;
        }
        this.currentGroupEnd = iGroupSize;
    }

    public final int endGroup() {
        int nodesDelta;
        boolean inserting = this.insertCount > 0;
        int currentGroup = this.currentGroup;
        int currentGroupEnd = this.currentGroupEnd;
        int groupIndex = this.parent;
        int groupAddress = groupIndexToAddress(groupIndex);
        int newNodes = this.nodeCount;
        int newGroupSize = currentGroup - groupIndex;
        boolean isNode = SlotTableKt.isNode(this.groups, groupAddress);
        if (inserting) {
            SlotTableKt.updateGroupSize(this.groups, groupAddress, newGroupSize);
            SlotTableKt.updateNodeCount(this.groups, groupAddress, newNodes);
            this.nodeCount = this.nodeCountStack.pop() + (isNode ? 1 : newNodes);
            this.parent = parent(this.groups, groupIndex);
        } else {
            if ((currentGroup != currentGroupEnd ? 0 : 1) != 0) {
                int oldGroupSize = SlotTableKt.groupSize(this.groups, groupAddress);
                int oldNodes = SlotTableKt.nodeCount(this.groups, groupAddress);
                SlotTableKt.updateGroupSize(this.groups, groupAddress, newGroupSize);
                SlotTableKt.updateNodeCount(this.groups, groupAddress, newNodes);
                int newParent = this.startStack.pop();
                restoreCurrentGroupEnd();
                this.parent = newParent;
                int groupParent = parent(this.groups, groupIndex);
                int iPop = this.nodeCountStack.pop();
                this.nodeCount = iPop;
                if (groupParent == newParent) {
                    nodesDelta = isNode ? 0 : newNodes - oldNodes;
                    this.nodeCount = iPop + nodesDelta;
                } else {
                    int groupSizeDelta = newGroupSize - oldGroupSize;
                    nodesDelta = isNode ? 0 : newNodes - oldNodes;
                    if (groupSizeDelta != 0 || nodesDelta != 0) {
                        int current = groupParent;
                        while (current != 0 && current != newParent && (nodesDelta != 0 || groupSizeDelta != 0)) {
                            boolean inserting2 = inserting;
                            int currentAddress = groupIndexToAddress(current);
                            if (groupSizeDelta != 0) {
                                int newSize = SlotTableKt.groupSize(this.groups, currentAddress) + groupSizeDelta;
                                SlotTableKt.updateGroupSize(this.groups, currentAddress, newSize);
                            }
                            if (nodesDelta != 0) {
                                int[] iArr = this.groups;
                                SlotTableKt.updateNodeCount(iArr, currentAddress, SlotTableKt.nodeCount(iArr, currentAddress) + nodesDelta);
                            }
                            if (SlotTableKt.isNode(this.groups, currentAddress)) {
                                nodesDelta = 0;
                            }
                            current = parent(this.groups, current);
                            inserting = inserting2;
                            oldGroupSize = oldGroupSize;
                            currentGroup = currentGroup;
                        }
                    }
                    this.nodeCount += nodesDelta;
                }
            } else {
                ComposerKt.composeRuntimeError("Expected to be at the end of a group".toString());
                throw new KotlinNothingValueException();
            }
        }
        return newNodes;
    }

    public final void bashGroup$runtime_release() {
        startGroup();
        while (!isGroupEnd()) {
            insertParentGroup(-3);
            skipGroup();
        }
        endGroup();
    }

    public final void ensureStarted(int index) {
        boolean value$iv = this.insertCount <= 0;
        if (value$iv) {
            int parent = this.parent;
            if (parent != index) {
                boolean value$iv2 = index >= parent && index < this.currentGroupEnd;
                if (!value$iv2) {
                    Object message$iv = "Started group at " + index + " must be a subgroup of the group at " + parent;
                    ComposerKt.composeRuntimeError(message$iv.toString());
                    throw new KotlinNothingValueException();
                }
                int oldCurrent = this.currentGroup;
                int oldCurrentSlot = this.currentSlot;
                int oldCurrentSlotEnd = this.currentSlotEnd;
                this.currentGroup = index;
                startGroup();
                this.currentGroup = oldCurrent;
                this.currentSlot = oldCurrentSlot;
                this.currentSlotEnd = oldCurrentSlotEnd;
                return;
            }
            return;
        }
        ComposerKt.composeRuntimeError("Cannot call ensureStarted() while inserting".toString());
        throw new KotlinNothingValueException();
    }

    public final void ensureStarted(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        ensureStarted(anchor.toIndexFor(this));
    }

    public final int skipGroup() {
        int groupAddress = groupIndexToAddress(this.currentGroup);
        int newGroup = this.currentGroup + SlotTableKt.groupSize(this.groups, groupAddress);
        this.currentGroup = newGroup;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(newGroup));
        if (SlotTableKt.isNode(this.groups, groupAddress)) {
            return 1;
        }
        return SlotTableKt.nodeCount(this.groups, groupAddress);
    }

    public final boolean removeGroup() {
        boolean value$iv = this.insertCount == 0;
        if (value$iv) {
            int oldGroup = this.currentGroup;
            int oldSlot = this.currentSlot;
            int count = skipGroup();
            PrioritySet it = this.pendingRecalculateMarks;
            if (it != null) {
                while (it.isNotEmpty() && it.peek() >= oldGroup) {
                    it.takeMax();
                }
            }
            boolean anchorsRemoved = removeGroups(oldGroup, this.currentGroup - oldGroup);
            removeSlots(oldSlot, this.currentSlot - oldSlot, oldGroup - 1);
            this.currentGroup = oldGroup;
            this.currentSlot = oldSlot;
            this.nodeCount -= count;
            return anchorsRemoved;
        }
        ComposerKt.composeRuntimeError("Cannot remove group while inserting".toString());
        throw new KotlinNothingValueException();
    }

    public final Iterator<Object> groupSlots() {
        int start = dataIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int[] iArr = this.groups;
        int i = this.currentGroup;
        int end = dataIndex(iArr, groupIndexToAddress(i + groupSize(i)));
        return new AnonymousClass1(start, end, this);
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.SlotWriter$groupSlots$1, reason: invalid class name */
    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\t\u0010\t\u001a\u00020\nH\u0096\u0002J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0096\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"androidx/compose/runtime/SlotWriter$groupSlots$1", "", "", "current", "", "getCurrent", "()I", "setCurrent", "(I)V", "hasNext", "", "next", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<Object>, KMappedMarker {
        final /* synthetic */ int $end;
        private int current;
        final /* synthetic */ SlotWriter this$0;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1(int $start, int $end, SlotWriter $receiver) {
            this.$end = $end;
            this.this$0 = $receiver;
            this.current = $start;
        }

        public final int getCurrent() {
            return this.current;
        }

        public final void setCurrent(int i) {
            this.current = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.current < this.$end;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (!hasNext()) {
                return null;
            }
            Object[] objArr = this.this$0.slots;
            SlotWriter slotWriter = this.this$0;
            int i = this.current;
            this.current = i + 1;
            return objArr[slotWriter.dataIndexToDataAddress(i)];
        }
    }

    public final void moveGroup(int offset) {
        boolean value$iv = this.insertCount == 0;
        if (value$iv) {
            boolean value$iv2 = offset >= 0;
            if (value$iv2) {
                if (offset == 0) {
                    return;
                }
                int current = this.currentGroup;
                int parent = this.parent;
                int parentEnd = this.currentGroupEnd;
                int groupToMove = current;
                for (int count = offset; count > 0; count--) {
                    groupToMove += SlotTableKt.groupSize(this.groups, groupIndexToAddress(groupToMove));
                    boolean value$iv3 = groupToMove <= parentEnd;
                    if (!value$iv3) {
                        ComposerKt.composeRuntimeError("Parameter offset is out of bounds".toString());
                        throw new KotlinNothingValueException();
                    }
                }
                int moveLen = SlotTableKt.groupSize(this.groups, groupIndexToAddress(groupToMove));
                int currentSlot = this.currentSlot;
                int dataStart = dataIndex(this.groups, groupIndexToAddress(groupToMove));
                int dataEnd = dataIndex(this.groups, groupIndexToAddress(groupToMove + moveLen));
                int moveDataLen = dataEnd - dataStart;
                insertSlots(moveDataLen, Math.max(this.currentGroup - 1, 0));
                insertGroups(moveLen);
                int[] groups = this.groups;
                int moveLocationAddress = groupIndexToAddress(groupToMove + moveLen);
                int moveLocationOffset = moveLocationAddress * 5;
                int currentAddress = groupIndexToAddress(current);
                ArraysKt.copyInto(groups, groups, currentAddress * 5, moveLocationOffset, moveLocationOffset + (moveLen * 5));
                if (moveDataLen > 0) {
                    Object[] slots = this.slots;
                    int parentEnd2 = dataEnd + moveDataLen;
                    ArraysKt.copyInto(slots, slots, currentSlot, dataIndexToDataAddress(dataStart + moveDataLen), dataIndexToDataAddress(parentEnd2));
                }
                int dataMoveDistance = (dataStart + moveDataLen) - currentSlot;
                int slotsGapStart = this.slotsGapStart;
                int slotsGapLen = this.slotsGapLen;
                int slotsCapacity = this.slots.length;
                int count2 = this.slotsGapOwner;
                int currentSlot2 = current + moveLen;
                int dataEnd2 = current;
                while (dataEnd2 < currentSlot2) {
                    int i = currentSlot2;
                    int groupAddress = groupIndexToAddress(dataEnd2);
                    int oldIndex = dataIndex(groups, groupAddress);
                    int moveLocationAddress2 = moveLocationAddress;
                    int moveLocationAddress3 = oldIndex - dataMoveDistance;
                    int dataMoveDistance2 = dataMoveDistance;
                    int dataMoveDistance3 = count2 < groupAddress ? 0 : slotsGapStart;
                    int newAnchor = dataIndexToDataAnchor(moveLocationAddress3, dataMoveDistance3, slotsGapLen, slotsCapacity);
                    updateDataIndex(groups, groupAddress, newAnchor);
                    dataEnd2++;
                    currentSlot2 = i;
                    moveLocationAddress = moveLocationAddress2;
                    dataMoveDistance = dataMoveDistance2;
                }
                int dataMoveDistance4 = groupToMove + moveLen;
                moveAnchors(dataMoveDistance4, current, moveLen);
                boolean anchorsRemoved = removeGroups(groupToMove + moveLen, moveLen);
                boolean value$iv4 = !anchorsRemoved;
                if (value$iv4) {
                    fixParentAnchorsFor(parent, this.currentGroupEnd, current);
                    if (moveDataLen > 0) {
                        removeSlots(dataStart + moveDataLen, moveDataLen, (groupToMove + moveLen) - 1);
                        return;
                    }
                    return;
                }
                ComposerKt.composeRuntimeError("Unexpectedly removed anchors".toString());
                throw new KotlinNothingValueException();
            }
            ComposerKt.composeRuntimeError("Parameter offset is out of bounds".toString());
            throw new KotlinNothingValueException();
        }
        ComposerKt.composeRuntimeError("Cannot move a group while inserting".toString());
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J@\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\fH\u0002¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/SlotWriter$Companion;", "", "()V", "moveGroup", "", "Landroidx/compose/runtime/Anchor;", "fromWriter", "Landroidx/compose/runtime/SlotWriter;", "fromIndex", "", "toWriter", "updateFromCursor", "", "updateToCursor", "removeSourceGroup", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        static /* synthetic */ List moveGroup$default(Companion companion, SlotWriter slotWriter, int i, SlotWriter slotWriter2, boolean z, boolean z2, boolean z3, int i2, Object obj) {
            return companion.moveGroup(slotWriter, i, slotWriter2, z, z2, (i2 & 32) != 0 ? true : z3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<Anchor> moveGroup(SlotWriter fromWriter, int fromIndex, SlotWriter toWriter, boolean updateFromCursor, boolean updateToCursor, boolean removeSourceGroup) {
            ArrayList arrayListEmptyList;
            boolean anchorsRemoved;
            int groupsToMove = fromWriter.groupSize(fromIndex);
            int sourceGroupsEnd = fromIndex + groupsToMove;
            int sourceSlotsStart = fromWriter.dataIndex(fromIndex);
            int sourceSlotsEnd = fromWriter.dataIndex(sourceGroupsEnd);
            int slotsToMove = sourceSlotsEnd - sourceSlotsStart;
            boolean hasMarks = fromWriter.containsAnyGroupMarks(fromIndex);
            toWriter.insertGroups(groupsToMove);
            toWriter.insertSlots(slotsToMove, toWriter.getCurrentGroup());
            if (fromWriter.groupGapStart < sourceGroupsEnd) {
                fromWriter.moveGroupGapTo(sourceGroupsEnd);
            }
            if (fromWriter.slotsGapStart < sourceSlotsEnd) {
                fromWriter.moveSlotGapTo(sourceSlotsEnd, sourceGroupsEnd);
            }
            int[] groups = toWriter.groups;
            int currentGroup = toWriter.getCurrentGroup();
            ArraysKt.copyInto(fromWriter.groups, groups, currentGroup * 5, fromIndex * 5, sourceGroupsEnd * 5);
            Object[] slots = toWriter.slots;
            int currentSlot = toWriter.currentSlot;
            ArraysKt.copyInto(fromWriter.slots, slots, currentSlot, sourceSlotsStart, sourceSlotsEnd);
            int parent = toWriter.getParent();
            SlotTableKt.updateParentAnchor(groups, currentGroup, parent);
            int parentDelta = currentGroup - fromIndex;
            int moveEnd = currentGroup + groupsToMove;
            int dataIndexDelta = currentSlot - toWriter.dataIndex(groups, currentGroup);
            int slotsGapOwner = toWriter.slotsGapOwner;
            int slotsGapLen = toWriter.slotsGapLen;
            int slotsCapacity = slots.length;
            int slotsGapOwner2 = slotsGapOwner;
            int currentSlot2 = currentGroup;
            while (true) {
                if (currentSlot2 >= moveEnd) {
                    break;
                }
                if (currentSlot2 != currentGroup) {
                    int previousParent = SlotTableKt.parentAnchor(groups, currentSlot2);
                    int moveEnd2 = previousParent + parentDelta;
                    SlotTableKt.updateParentAnchor(groups, currentSlot2, moveEnd2);
                }
                int newDataIndex = toWriter.dataIndex(groups, currentSlot2) + dataIndexDelta;
                int dataIndexDelta2 = slotsGapOwner2 < currentSlot2 ? 0 : toWriter.slotsGapStart;
                int parentDelta2 = parentDelta;
                int newDataAnchor = toWriter.dataIndexToDataAnchor(newDataIndex, dataIndexDelta2, slotsGapLen, slotsCapacity);
                SlotTableKt.updateDataAnchor(groups, currentSlot2, newDataAnchor);
                if (currentSlot2 == slotsGapOwner2) {
                    slotsGapOwner2++;
                }
                currentSlot2++;
                moveEnd = moveEnd;
                dataIndexDelta = dataIndexDelta;
                parentDelta = parentDelta2;
            }
            toWriter.slotsGapOwner = slotsGapOwner2;
            int startAnchors = SlotTableKt.locationOf(fromWriter.anchors, fromIndex, fromWriter.getSize$runtime_release());
            int endAnchors = SlotTableKt.locationOf(fromWriter.anchors, sourceGroupsEnd, fromWriter.getSize$runtime_release());
            if (startAnchors < endAnchors) {
                ArrayList sourceAnchors = fromWriter.anchors;
                ArrayList anchors = new ArrayList(endAnchors - startAnchors);
                int anchorDelta = currentGroup - fromIndex;
                int slotsCapacity2 = startAnchors;
                while (slotsCapacity2 < endAnchors) {
                    int slotsGapOwner3 = slotsGapOwner2;
                    Object obj = sourceAnchors.get(slotsCapacity2);
                    Intrinsics.checkNotNullExpressionValue(obj, "sourceAnchors[anchorIndex]");
                    Anchor sourceAnchor = (Anchor) obj;
                    sourceAnchor.setLocation$runtime_release(sourceAnchor.getLocation() + anchorDelta);
                    anchors.add(sourceAnchor);
                    slotsCapacity2++;
                    slotsGapOwner2 = slotsGapOwner3;
                    slotsGapLen = slotsGapLen;
                }
                int insertLocation = SlotTableKt.locationOf(toWriter.anchors, toWriter.getCurrentGroup(), toWriter.getSize$runtime_release());
                toWriter.anchors.addAll(insertLocation, anchors);
                sourceAnchors.subList(startAnchors, endAnchors).clear();
                arrayListEmptyList = anchors;
            } else {
                arrayListEmptyList = CollectionsKt.emptyList();
            }
            int parentGroup = fromWriter.parent(fromIndex);
            int iNodeCount = 1;
            if (!removeSourceGroup) {
                anchorsRemoved = false;
            } else if (!updateFromCursor) {
                anchorsRemoved = fromWriter.removeGroups(fromIndex, groupsToMove);
                fromWriter.removeSlots(sourceSlotsStart, slotsToMove, fromIndex - 1);
            } else {
                boolean needsStartGroups = parentGroup >= 0;
                if (needsStartGroups) {
                    fromWriter.startGroup();
                    fromWriter.advanceBy(parentGroup - fromWriter.getCurrentGroup());
                    fromWriter.startGroup();
                }
                fromWriter.advanceBy(fromIndex - fromWriter.getCurrentGroup());
                anchorsRemoved = fromWriter.removeGroup();
                if (needsStartGroups) {
                    fromWriter.skipToGroupEnd();
                    fromWriter.endGroup();
                    fromWriter.skipToGroupEnd();
                    fromWriter.endGroup();
                }
            }
            boolean value$iv = anchorsRemoved ? false : true;
            if (value$iv) {
                int i = toWriter.nodeCount;
                if (!SlotTableKt.isNode(groups, currentGroup)) {
                    iNodeCount = SlotTableKt.nodeCount(groups, currentGroup);
                }
                toWriter.nodeCount = i + iNodeCount;
                if (updateToCursor) {
                    toWriter.currentGroup = currentGroup + groupsToMove;
                    toWriter.currentSlot = currentSlot + slotsToMove;
                }
                if (hasMarks) {
                    toWriter.updateContainsMark(parent);
                }
                return arrayListEmptyList;
            }
            ComposerKt.composeRuntimeError("Unexpectedly removed anchors".toString());
            throw new KotlinNothingValueException();
        }
    }

    public final List<Anchor> moveTo(Anchor anchor, int offset, SlotWriter writer) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        Intrinsics.checkNotNullParameter(writer, "writer");
        ComposerKt.runtimeCheck(writer.insertCount > 0);
        ComposerKt.runtimeCheck(this.insertCount == 0);
        ComposerKt.runtimeCheck(anchor.getValid());
        int location = anchorIndex(anchor) + offset;
        int currentGroup = this.currentGroup;
        ComposerKt.runtimeCheck(currentGroup <= location && location < this.currentGroupEnd);
        int parent = parent(location);
        int size = groupSize(location);
        int nodes = isNode(location) ? 1 : nodeCount(location);
        List<Anchor> listMoveGroup$default = Companion.moveGroup$default(INSTANCE, this, location, writer, false, false, false, 32, null);
        updateContainsMark(parent);
        int current = parent;
        boolean updatingNodes = nodes > 0;
        while (current >= currentGroup) {
            int currentAddress = groupIndexToAddress(current);
            int[] iArr = this.groups;
            SlotTableKt.updateGroupSize(iArr, currentAddress, SlotTableKt.groupSize(iArr, currentAddress) - size);
            if (updatingNodes) {
                if (SlotTableKt.isNode(this.groups, currentAddress)) {
                    updatingNodes = false;
                } else {
                    int[] iArr2 = this.groups;
                    SlotTableKt.updateNodeCount(iArr2, currentAddress, SlotTableKt.nodeCount(iArr2, currentAddress) - nodes);
                }
            }
            current = parent(current);
        }
        if (updatingNodes) {
            ComposerKt.runtimeCheck(this.nodeCount >= nodes);
            this.nodeCount -= nodes;
        }
        return listMoveGroup$default;
    }

    public static /* synthetic */ List moveFrom$default(SlotWriter slotWriter, SlotTable slotTable, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        return slotWriter.moveFrom(slotTable, i, z);
    }

    public final List<Anchor> moveFrom(SlotTable table, int index, boolean removeSourceGroup) {
        Intrinsics.checkNotNullParameter(table, "table");
        ComposerKt.runtimeCheck(this.insertCount > 0);
        if (index == 0 && this.currentGroup == 0 && this.table.getGroupsSize() == 0 && SlotTableKt.groupSize(table.getGroups(), index) == table.getGroupsSize()) {
            int[] myGroups = this.groups;
            Object[] mySlots = this.slots;
            ArrayList<Anchor> arrayList = this.anchors;
            int[] groups = table.getGroups();
            int groupsSize = table.getGroupsSize();
            Object[] slots = table.getSlots();
            int slotsSize = table.getSlotsSize();
            this.groups = groups;
            this.slots = slots;
            this.anchors = table.getAnchors$runtime_release();
            this.groupGapStart = groupsSize;
            this.groupGapLen = (groups.length / 5) - groupsSize;
            this.slotsGapStart = slotsSize;
            this.slotsGapLen = slots.length - slotsSize;
            this.slotsGapOwner = groupsSize;
            table.setTo$runtime_release(myGroups, 0, mySlots, 0, arrayList);
            return this.anchors;
        }
        SlotWriter writer$iv = table.openWriter();
        try {
            return INSTANCE.moveGroup(writer$iv, index, this, true, true, removeSourceGroup);
        } finally {
            writer$iv.close();
        }
    }

    public final void insertParentGroup(int key) {
        boolean value$iv = this.insertCount == 0;
        if (value$iv) {
            boolean value$iv2 = isGroupEnd();
            if (value$iv2) {
                beginInsert();
                startGroup(key);
                endGroup();
                endInsert();
                return;
            }
            int currentGroup = this.currentGroup;
            int parent = parent(this.groups, currentGroup);
            int currentGroupEnd = parent + groupSize(parent);
            int remainingSize = currentGroupEnd - currentGroup;
            int nodeCount = 0;
            int currentNewChild = currentGroup;
            while (currentNewChild < currentGroupEnd) {
                int newChildAddress = groupIndexToAddress(currentNewChild);
                nodeCount += SlotTableKt.nodeCount(this.groups, newChildAddress);
                currentNewChild += SlotTableKt.groupSize(this.groups, newChildAddress);
            }
            int currentSlot = SlotTableKt.dataAnchor(this.groups, groupIndexToAddress(currentGroup));
            beginInsert();
            insertGroups(1);
            endInsert();
            int currentAddress = groupIndexToAddress(currentGroup);
            SlotTableKt.initGroup(this.groups, currentAddress, key, false, false, false, parent, currentSlot);
            SlotTableKt.updateGroupSize(this.groups, currentAddress, remainingSize + 1);
            SlotTableKt.updateNodeCount(this.groups, currentAddress, nodeCount);
            int parentAddress = groupIndexToAddress(parent);
            addToGroupSizeAlongSpine(parentAddress, 1);
            fixParentAnchorsFor(parent, currentGroupEnd, currentGroup);
            this.currentGroup = currentGroupEnd;
            return;
        }
        ComposerKt.composeRuntimeError("Writer cannot be inserting".toString());
        throw new KotlinNothingValueException();
    }

    public final void addToGroupSizeAlongSpine(int address, int amount) {
        int current = address;
        while (current > 0) {
            int[] iArr = this.groups;
            SlotTableKt.updateGroupSize(iArr, current, SlotTableKt.groupSize(iArr, current) + amount);
            int parentAnchor = SlotTableKt.parentAnchor(this.groups, current);
            int parentGroup = parentAnchorToIndex(parentAnchor);
            int parentAddress = groupIndexToAddress(parentGroup);
            current = parentAddress;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final List<Anchor> moveIntoGroupFrom(int offset, SlotTable table, int index) {
        boolean z;
        Intrinsics.checkNotNullParameter(table, "table");
        if (this.insertCount <= 0) {
            z = groupSize(this.currentGroup + offset) == 1;
        }
        ComposerKt.runtimeCheck(z);
        int previousCurrentGroup = this.currentGroup;
        int previousCurrentSlot = this.currentSlot;
        int previousCurrentSlotEnd = this.currentSlotEnd;
        advanceBy(offset);
        startGroup();
        beginInsert();
        SlotWriter writer$iv = table.openWriter();
        try {
            List<Anchor> listMoveGroup$default = Companion.moveGroup$default(INSTANCE, writer$iv, index, this, false, true, false, 32, null);
            writer$iv.close();
            endInsert();
            endGroup();
            this.currentGroup = previousCurrentGroup;
            this.currentSlot = previousCurrentSlot;
            this.currentSlotEnd = previousCurrentSlotEnd;
            return listMoveGroup$default;
        } catch (Throwable th) {
            writer$iv.close();
            throw th;
        }
    }

    public static /* synthetic */ Anchor anchor$default(SlotWriter slotWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = slotWriter.currentGroup;
        }
        return slotWriter.anchor(i);
    }

    public final Anchor anchor(int index) {
        ArrayList<Anchor> arrayList = this.anchors;
        int effectiveSize$iv = getSize$runtime_release();
        int location$iv = SlotTableKt.search(arrayList, index, effectiveSize$iv);
        if (location$iv < 0) {
            Anchor anchor$iv = new Anchor(index <= this.groupGapStart ? index : -(getSize$runtime_release() - index));
            arrayList.add(-(location$iv + 1), anchor$iv);
            return anchor$iv;
        }
        Anchor anchor = arrayList.get(location$iv);
        Intrinsics.checkNotNullExpressionValue(anchor, "get(location)");
        return anchor;
    }

    public static /* synthetic */ void markGroup$default(SlotWriter slotWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = slotWriter.parent;
        }
        slotWriter.markGroup(i);
    }

    public final void markGroup(int group) {
        int groupAddress = groupIndexToAddress(group);
        if (!SlotTableKt.hasMark(this.groups, groupAddress)) {
            SlotTableKt.updateMark(this.groups, groupAddress, true);
            if (!SlotTableKt.containsMark(this.groups, groupAddress)) {
                updateContainsMark(parent(group));
            }
        }
    }

    private final boolean containsGroupMark(int group) {
        return group >= 0 && SlotTableKt.containsMark(this.groups, groupIndexToAddress(group));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean containsAnyGroupMarks(int group) {
        return group >= 0 && SlotTableKt.containsAnyMark(this.groups, groupIndexToAddress(group));
    }

    private final void recalculateMarks() {
        PrioritySet set = this.pendingRecalculateMarks;
        if (set != null) {
            while (set.isNotEmpty()) {
                updateContainsMarkNow(set.takeMax(), set);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void updateContainsMark(int group) {
        if (group >= 0) {
            PrioritySet prioritySet = this.pendingRecalculateMarks;
            if (prioritySet == null) {
                prioritySet = new PrioritySet(null, 1, 0 == true ? 1 : 0);
                this.pendingRecalculateMarks = prioritySet;
            }
            prioritySet.add(group);
        }
    }

    private final void updateContainsMarkNow(int group, PrioritySet set) {
        int groupAddress = groupIndexToAddress(group);
        boolean containsAnyMarks = childContainsAnyMarks(group);
        boolean markChanges = SlotTableKt.containsMark(this.groups, groupAddress) != containsAnyMarks;
        if (markChanges) {
            SlotTableKt.updateContainsMark(this.groups, groupAddress, containsAnyMarks);
            int parent = parent(group);
            if (parent >= 0) {
                set.add(parent);
            }
        }
    }

    private final boolean childContainsAnyMarks(int group) {
        int child = group + 1;
        int end = groupSize(group) + group;
        while (child < end) {
            if (SlotTableKt.containsAnyMark(this.groups, groupIndexToAddress(child))) {
                return true;
            }
            child += groupSize(child);
        }
        return false;
    }

    public final int anchorIndex(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        int it = anchor.getLocation();
        return it < 0 ? getSize$runtime_release() + it : it;
    }

    public String toString() {
        return "SlotWriter(current = " + this.currentGroup + " end=" + this.currentGroupEnd + " size = " + getSize$runtime_release() + " gap=" + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + ')';
    }

    private final void saveCurrentGroupEnd() {
        this.endStack.push((getCapacity() - this.groupGapLen) - this.currentGroupEnd);
    }

    private final int restoreCurrentGroupEnd() {
        int newGroupEnd = (getCapacity() - this.groupGapLen) - this.endStack.pop();
        this.currentGroupEnd = newGroupEnd;
        return newGroupEnd;
    }

    private final void fixParentAnchorsFor(int parent, int endGroup, int firstChild) {
        int parentAnchor = parentIndexToAnchor(parent, this.groupGapStart);
        int child = firstChild;
        while (child < endGroup) {
            SlotTableKt.updateParentAnchor(this.groups, groupIndexToAddress(child), parentAnchor);
            int childEnd = SlotTableKt.groupSize(this.groups, groupIndexToAddress(child)) + child;
            fixParentAnchorsFor(child, childEnd, child + 1);
            child = childEnd;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveGroupGapTo(int index) {
        int gapLen = this.groupGapLen;
        int gapStart = this.groupGapStart;
        if (gapStart != index) {
            if (!this.anchors.isEmpty()) {
                updateAnchors(gapStart, index);
            }
            if (gapLen > 0) {
                int[] groups = this.groups;
                int groupPhysicalAddress = index * 5;
                int groupPhysicalGapLen = gapLen * 5;
                int groupPhysicalGapStart = gapStart * 5;
                if (index < gapStart) {
                    ArraysKt.copyInto(groups, groups, groupPhysicalAddress + groupPhysicalGapLen, groupPhysicalAddress, groupPhysicalGapStart);
                } else {
                    ArraysKt.copyInto(groups, groups, groupPhysicalGapStart, groupPhysicalGapStart + groupPhysicalGapLen, groupPhysicalAddress + groupPhysicalGapLen);
                }
            }
            int groupAddress = index < gapStart ? index + gapLen : gapStart;
            int capacity = getCapacity();
            ComposerKt.runtimeCheck(groupAddress < capacity);
            while (groupAddress < capacity) {
                int oldAnchor = SlotTableKt.parentAnchor(this.groups, groupAddress);
                int oldIndex = parentAnchorToIndex(oldAnchor);
                int newAnchor = parentIndexToAnchor(oldIndex, index);
                if (newAnchor != oldAnchor) {
                    SlotTableKt.updateParentAnchor(this.groups, groupAddress, newAnchor);
                }
                groupAddress++;
                if (groupAddress == index) {
                    groupAddress += gapLen;
                }
            }
        }
        this.groupGapStart = index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveSlotGapTo(int index, int group) {
        int gapLen = this.slotsGapLen;
        int gapStart = this.slotsGapStart;
        int slotsGapOwner = this.slotsGapOwner;
        if (gapStart != index) {
            Object[] slots = this.slots;
            if (index < gapStart) {
                ArraysKt.copyInto(slots, slots, index + gapLen, index, gapStart);
            } else {
                ArraysKt.copyInto(slots, slots, gapStart, gapStart + gapLen, index + gapLen);
            }
        }
        int newSlotsGapOwner = Math.min(group + 1, getSize$runtime_release());
        if (slotsGapOwner != newSlotsGapOwner) {
            int slotsSize = this.slots.length - gapLen;
            if (newSlotsGapOwner < slotsGapOwner) {
                int updateAddress = groupIndexToAddress(newSlotsGapOwner);
                int stopUpdateAddress = groupIndexToAddress(slotsGapOwner);
                int groupGapStart = this.groupGapStart;
                while (updateAddress < stopUpdateAddress) {
                    int anchor = SlotTableKt.dataAnchor(this.groups, updateAddress);
                    boolean value$iv = anchor >= 0;
                    if (value$iv) {
                        SlotTableKt.updateDataAnchor(this.groups, updateAddress, -((slotsSize - anchor) + 1));
                        updateAddress++;
                        if (updateAddress == groupGapStart) {
                            updateAddress += this.groupGapLen;
                        }
                    } else {
                        ComposerKt.composeRuntimeError("Unexpected anchor value, expected a positive anchor".toString());
                        throw new KotlinNothingValueException();
                    }
                }
            } else {
                int updateAddress2 = groupIndexToAddress(slotsGapOwner);
                int stopUpdateAddress2 = groupIndexToAddress(newSlotsGapOwner);
                while (updateAddress2 < stopUpdateAddress2) {
                    int anchor2 = SlotTableKt.dataAnchor(this.groups, updateAddress2);
                    boolean value$iv2 = anchor2 < 0;
                    if (value$iv2) {
                        SlotTableKt.updateDataAnchor(this.groups, updateAddress2, slotsSize + anchor2 + 1);
                        updateAddress2++;
                        if (updateAddress2 == this.groupGapStart) {
                            updateAddress2 += this.groupGapLen;
                        }
                    } else {
                        ComposerKt.composeRuntimeError("Unexpected anchor value, expected a negative anchor".toString());
                        throw new KotlinNothingValueException();
                    }
                }
            }
            this.slotsGapOwner = newSlotsGapOwner;
        }
        this.slotsGapStart = index;
    }

    private final void clearSlotGap() {
        int slotsGapStart = this.slotsGapStart;
        int slotsGapEnd = this.slotsGapLen + slotsGapStart;
        ArraysKt.fill(this.slots, (Object) null, slotsGapStart, slotsGapEnd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertGroups(int size) {
        if (size > 0) {
            int currentGroup = this.currentGroup;
            moveGroupGapTo(currentGroup);
            int gapStart = this.groupGapStart;
            int gapLen = this.groupGapLen;
            int oldCapacity = this.groups.length / 5;
            int oldSize = oldCapacity - gapLen;
            if (gapLen < size) {
                int[] groups = this.groups;
                int newCapacity = Math.max(Math.max(oldCapacity * 2, oldSize + size), 32);
                int[] newGroups = new int[newCapacity * 5];
                int newGapLen = newCapacity - oldSize;
                int oldGapEndAddress = gapStart + gapLen;
                int newGapEndAddress = gapStart + newGapLen;
                ArraysKt.copyInto(groups, newGroups, 0, 0, gapStart * 5);
                ArraysKt.copyInto(groups, newGroups, newGapEndAddress * 5, oldGapEndAddress * 5, oldCapacity * 5);
                this.groups = newGroups;
                gapLen = newGapLen;
            }
            int currentEnd = this.currentGroupEnd;
            if (currentEnd >= gapStart) {
                this.currentGroupEnd = currentEnd + size;
            }
            this.groupGapStart = gapStart + size;
            this.groupGapLen = gapLen - size;
            int index = oldSize > 0 ? dataIndex(currentGroup + size) : 0;
            int anchor = dataIndexToDataAnchor(index, this.slotsGapOwner < gapStart ? 0 : this.slotsGapStart, this.slotsGapLen, this.slots.length);
            int i = gapStart + size;
            for (int groupAddress = gapStart; groupAddress < i; groupAddress++) {
                SlotTableKt.updateDataAnchor(this.groups, groupAddress, anchor);
            }
            int groupAddress2 = this.slotsGapOwner;
            if (groupAddress2 >= gapStart) {
                this.slotsGapOwner = groupAddress2 + size;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertSlots(int size, int group) {
        if (size > 0) {
            moveSlotGapTo(this.currentSlot, group);
            int gapStart = this.slotsGapStart;
            int gapLen = this.slotsGapLen;
            if (gapLen < size) {
                Object[] slots = this.slots;
                int oldCapacity = slots.length;
                int oldSize = oldCapacity - gapLen;
                int newCapacity = Math.max(Math.max(oldCapacity * 2, oldSize + size), 32);
                Object[] newData = new Object[newCapacity];
                for (int i = 0; i < newCapacity; i++) {
                    newData[i] = null;
                }
                int newGapLen = newCapacity - oldSize;
                int oldGapEndAddress = gapStart + gapLen;
                int newGapEndAddress = gapStart + newGapLen;
                ArraysKt.copyInto(slots, newData, 0, 0, gapStart);
                ArraysKt.copyInto(slots, newData, newGapEndAddress, oldGapEndAddress, oldCapacity);
                this.slots = newData;
                gapLen = newGapLen;
            }
            int currentDataEnd = this.currentSlotEnd;
            if (currentDataEnd >= gapStart) {
                this.currentSlotEnd = currentDataEnd + size;
            }
            this.slotsGapStart = gapStart + size;
            this.slotsGapLen = gapLen - size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean removeGroups(int start, int len) {
        if (len > 0) {
            ArrayList<Anchor> arrayList = this.anchors;
            moveGroupGapTo(start);
            boolean anchorsRemoved = arrayList.isEmpty() ^ true ? removeAnchors(start, len) : false;
            this.groupGapStart = start;
            int previousGapLen = this.groupGapLen;
            int newGapLen = previousGapLen + len;
            this.groupGapLen = newGapLen;
            int slotsGapOwner = this.slotsGapOwner;
            if (slotsGapOwner > start) {
                this.slotsGapOwner = Math.max(start, slotsGapOwner - len);
            }
            int i = this.currentGroupEnd;
            if (i >= this.groupGapStart) {
                this.currentGroupEnd = i - len;
            }
            if (!containsGroupMark(this.parent)) {
                return anchorsRemoved;
            }
            updateContainsMark(this.parent);
            return anchorsRemoved;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSlots(int start, int len, int group) {
        if (len > 0) {
            int gapLen = this.slotsGapLen;
            int removeEnd = start + len;
            moveSlotGapTo(removeEnd, group);
            this.slotsGapStart = start;
            this.slotsGapLen = gapLen + len;
            ArraysKt.fill(this.slots, (Object) null, start, start + len);
            int currentDataEnd = this.currentSlotEnd;
            if (currentDataEnd >= start) {
                this.currentSlotEnd = currentDataEnd - len;
            }
        }
    }

    private final void updateNodeOfGroup(int index, Object value) {
        int address = groupIndexToAddress(index);
        int[] iArr = this.groups;
        boolean value$iv = address < iArr.length && SlotTableKt.isNode(iArr, address);
        if (!value$iv) {
            Object message$iv = "Updating the node of a group at " + index + " that was not created with as a node group";
            ComposerKt.composeRuntimeError(message$iv.toString());
            throw new KotlinNothingValueException();
        }
        this.slots[dataIndexToDataAddress(nodeIndex(this.groups, address))] = value;
    }

    private final void updateAnchors(int previousGapStart, int newGapStart) {
        int gapLen = this.groupGapLen;
        int size = getCapacity() - gapLen;
        if (previousGapStart < newGapStart) {
            for (int index = SlotTableKt.locationOf(this.anchors, previousGapStart, size); index < this.anchors.size(); index++) {
                Anchor anchor = this.anchors.get(index);
                Intrinsics.checkNotNullExpressionValue(anchor, "anchors[index]");
                Anchor anchor2 = anchor;
                int location = anchor2.getLocation();
                if (location < 0) {
                    int newLocation = size + location;
                    if (newLocation < newGapStart) {
                        anchor2.setLocation$runtime_release(size + location);
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            return;
        }
        for (int index2 = SlotTableKt.locationOf(this.anchors, newGapStart, size); index2 < this.anchors.size(); index2++) {
            Anchor anchor3 = this.anchors.get(index2);
            Intrinsics.checkNotNullExpressionValue(anchor3, "anchors[index]");
            Anchor anchor4 = anchor3;
            int location2 = anchor4.getLocation();
            if (location2 >= 0) {
                anchor4.setLocation$runtime_release(-(size - location2));
            } else {
                return;
            }
        }
    }

    private final boolean removeAnchors(int gapStart, int size) {
        int gapLen = this.groupGapLen;
        int removeEnd = gapStart + size;
        int groupsSize = getCapacity() - gapLen;
        int it = SlotTableKt.locationOf(this.anchors, gapStart + size, groupsSize);
        if (it >= this.anchors.size()) {
            it--;
        }
        int removeAnchorEnd = 0;
        int removeAnchorStart = it + 1;
        while (it >= 0) {
            Anchor anchor = this.anchors.get(it);
            Intrinsics.checkNotNullExpressionValue(anchor, "anchors[index]");
            Anchor anchor2 = anchor;
            int location = anchorIndex(anchor2);
            if (location < gapStart) {
                break;
            }
            if (location < removeEnd) {
                anchor2.setLocation$runtime_release(Integer.MIN_VALUE);
                removeAnchorStart = it;
                if (removeAnchorEnd == 0) {
                    removeAnchorEnd = it + 1;
                }
            }
            it--;
        }
        boolean it2 = removeAnchorStart < removeAnchorEnd;
        if (it2) {
            this.anchors.subList(removeAnchorStart, removeAnchorEnd).clear();
        }
        return it2;
    }

    private final void moveAnchors(int originalLocation, int newLocation, int size) {
        int end = originalLocation + size;
        int groupsSize = getSize$runtime_release();
        int index = SlotTableKt.locationOf(this.anchors, originalLocation, groupsSize);
        List removedAnchors = new ArrayList();
        if (index >= 0) {
            while (index < this.anchors.size()) {
                Anchor anchor = this.anchors.get(index);
                Intrinsics.checkNotNullExpressionValue(anchor, "anchors[index]");
                Anchor anchor2 = anchor;
                int location = anchorIndex(anchor2);
                if (location < originalLocation || location >= end) {
                    break;
                }
                removedAnchors.add(anchor2);
                this.anchors.remove(index);
            }
        }
        int moveDelta = newLocation - originalLocation;
        int index$iv = 0;
        int size2 = removedAnchors.size();
        while (index$iv < size2) {
            Object item$iv = removedAnchors.get(index$iv);
            Anchor anchor3 = (Anchor) item$iv;
            int anchorIndex = anchorIndex(anchor3);
            int newAnchorIndex = anchorIndex + moveDelta;
            if (newAnchorIndex >= this.groupGapStart) {
                anchor3.setLocation$runtime_release(-(groupsSize - newAnchorIndex));
            } else {
                anchor3.setLocation$runtime_release(newAnchorIndex);
            }
            int insertIndex = SlotTableKt.locationOf(this.anchors, newAnchorIndex, groupsSize);
            this.anchors.add(insertIndex, anchor3);
            index$iv++;
            end = end;
        }
    }

    public final String groupsAsString() {
        StringBuilder $this$groupsAsString_u24lambda_u2435 = new StringBuilder();
        int size$runtime_release = getSize$runtime_release();
        for (int index = 0; index < size$runtime_release; index++) {
            groupAsString($this$groupsAsString_u24lambda_u2435, index);
            $this$groupsAsString_u24lambda_u2435.append('\n');
        }
        String string = $this$groupsAsString_u24lambda_u2435.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    private final void groupAsString(StringBuilder $this$groupAsString, int index) {
        int address = groupIndexToAddress(index);
        $this$groupAsString.append("Group(");
        if (index < 10) {
            $this$groupAsString.append(' ');
        }
        if (index < 100) {
            $this$groupAsString.append(' ');
        }
        if (index < 1000) {
            $this$groupAsString.append(' ');
        }
        $this$groupAsString.append(index);
        if (address != index) {
            $this$groupAsString.append("(");
            $this$groupAsString.append(address);
            $this$groupAsString.append(")");
        }
        $this$groupAsString.append('#');
        $this$groupAsString.append(SlotTableKt.groupSize(this.groups, address));
        boolean openGroup = groupAsString$isStarted(this, index);
        if (openGroup) {
            $this$groupAsString.append('?');
        }
        $this$groupAsString.append('^');
        $this$groupAsString.append(parentAnchorToIndex(SlotTableKt.parentAnchor(this.groups, address)));
        $this$groupAsString.append(": key=");
        $this$groupAsString.append(SlotTableKt.key(this.groups, address));
        $this$groupAsString.append(", nodes=");
        $this$groupAsString.append(SlotTableKt.nodeCount(this.groups, address));
        if (openGroup) {
            $this$groupAsString.append('?');
        }
        $this$groupAsString.append(", dataAnchor=");
        $this$groupAsString.append(SlotTableKt.dataAnchor(this.groups, address));
        $this$groupAsString.append(", parentAnchor=");
        $this$groupAsString.append(SlotTableKt.parentAnchor(this.groups, address));
        if (SlotTableKt.isNode(this.groups, address)) {
            $this$groupAsString.append(", node=" + this.slots[dataIndexToDataAddress(nodeIndex(this.groups, address))]);
        }
        int startData = slotIndex(this.groups, address);
        int endData = dataIndex(this.groups, address + 1);
        if (endData > startData) {
            $this$groupAsString.append(", [");
            for (int dataIndex = startData; dataIndex < endData; dataIndex++) {
                if (dataIndex != startData) {
                    $this$groupAsString.append(", ");
                }
                int dataAddress = dataIndexToDataAddress(dataIndex);
                $this$groupAsString.append(String.valueOf(this.slots[dataAddress]));
            }
            $this$groupAsString.append(']');
        }
        $this$groupAsString.append(")");
    }

    private static final boolean groupAsString$isStarted(SlotWriter this$0, int index) {
        return index < this$0.currentGroup && (index == this$0.parent || this$0.startStack.indexOf(index) >= 0 || groupAsString$isStarted(this$0, this$0.parent(index)));
    }

    public final void verifyDataAnchors$runtime_release() {
        int previousDataIndex = 0;
        int owner = this.slotsGapOwner;
        boolean ownerFound = false;
        int slotsSize = this.slots.length - this.slotsGapLen;
        int index = 0;
        int size$runtime_release = getSize$runtime_release();
        while (index < size$runtime_release) {
            int address = groupIndexToAddress(index);
            int dataAnchor = SlotTableKt.dataAnchor(this.groups, address);
            int dataIndex = dataIndex(this.groups, address);
            if (!(dataIndex >= previousDataIndex)) {
                throw new IllegalStateException(("Data index out of order at " + index + ", previous = " + previousDataIndex + ", current = " + dataIndex).toString());
            }
            if (!(dataIndex <= slotsSize)) {
                throw new IllegalStateException(("Data index, " + dataIndex + ", out of bound at " + index).toString());
            }
            if (dataAnchor < 0 && !ownerFound) {
                if (!(owner == index)) {
                    throw new IllegalStateException(("Expected the slot gap owner to be " + owner + " found gap at " + index).toString());
                }
                ownerFound = true;
            }
            previousDataIndex = dataIndex;
            index++;
        }
    }

    public final void verifyParentAnchors$runtime_release() {
        int gapStart = this.groupGapStart;
        int gapLen = this.groupGapLen;
        int capacity = getCapacity();
        int groupAddress = 0;
        while (true) {
            if (groupAddress < gapStart) {
                if (SlotTableKt.parentAnchor(this.groups, groupAddress) > -2) {
                    groupAddress++;
                } else {
                    throw new IllegalStateException(("Expected a start relative anchor at " + groupAddress).toString());
                }
            } else {
                for (int groupAddress2 = gapStart + gapLen; groupAddress2 < capacity; groupAddress2++) {
                    int parentAnchor = SlotTableKt.parentAnchor(this.groups, groupAddress2);
                    int parentIndex = parentAnchorToIndex(parentAnchor);
                    if (parentIndex < gapStart) {
                        if (!(parentAnchor > -2)) {
                            throw new IllegalStateException(("Expected a start relative anchor at " + groupAddress2).toString());
                        }
                    } else {
                        if (!(parentAnchor <= -2)) {
                            throw new IllegalStateException(("Expected an end relative anchor at " + groupAddress2).toString());
                        }
                    }
                }
                return;
            }
        }
    }

    public final int getSize$runtime_release() {
        return getCapacity() - this.groupGapLen;
    }

    private final int getCapacity() {
        return this.groups.length / 5;
    }

    private final int groupIndexToAddress(int index) {
        return index < this.groupGapStart ? index : this.groupGapLen + index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAddress(int dataIndex) {
        return dataIndex < this.slotsGapStart ? dataIndex : this.slotsGapLen + dataIndex;
    }

    private final int parent(int[] $this$parent, int index) {
        return parentAnchorToIndex(SlotTableKt.parentAnchor($this$parent, groupIndexToAddress(index)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int index) {
        return dataIndex(this.groups, groupIndexToAddress(index));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int[] $this$dataIndex, int address) {
        return address >= getCapacity() ? this.slots.length - this.slotsGapLen : dataAnchorToDataIndex(SlotTableKt.dataAnchor($this$dataIndex, address), this.slotsGapLen, this.slots.length);
    }

    private final int slotIndex(int[] $this$slotIndex, int address) {
        return address >= getCapacity() ? this.slots.length - this.slotsGapLen : dataAnchorToDataIndex(SlotTableKt.slotAnchor($this$slotIndex, address), this.slotsGapLen, this.slots.length);
    }

    private final void updateDataIndex(int[] $this$updateDataIndex, int address, int dataIndex) {
        SlotTableKt.updateDataAnchor($this$updateDataIndex, address, dataIndexToDataAnchor(dataIndex, this.slotsGapStart, this.slotsGapLen, this.slots.length));
    }

    private final int nodeIndex(int[] $this$nodeIndex, int address) {
        return dataIndex($this$nodeIndex, address);
    }

    private final int auxIndex(int[] $this$auxIndex, int address) {
        return dataIndex($this$auxIndex, address) + SlotTableKt.countOneBits(SlotTableKt.groupInfo($this$auxIndex, address) >> 29);
    }

    private final List<Integer> dataIndexes(int[] $this$dataIndexes) {
        List it = SlotTableKt.dataAnchors$default(this.groups, 0, 1, null);
        List $this$fastMap$iv = CollectionsKt.plus((Collection) CollectionsKt.slice(it, RangesKt.until(0, this.groupGapStart)), (Iterable) CollectionsKt.slice(it, RangesKt.until(this.groupGapStart + this.groupGapLen, $this$dataIndexes.length / 5)));
        ArrayList target$iv = new ArrayList($this$fastMap$iv.size());
        int index$iv$iv = 0;
        int size = $this$fastMap$iv.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
            int anchor = ((Number) item$iv$iv).intValue();
            target$iv.add(Integer.valueOf(dataAnchorToDataIndex(anchor, this.slotsGapLen, this.slots.length)));
            index$iv$iv++;
            $this$fastMap$iv = $this$fastMap$iv;
        }
        return target$iv;
    }

    private final List<Integer> keys() {
        List $this$fastFilterIndexed$iv = SlotTableKt.keys$default(this.groups, 0, 1, null);
        List target$iv = new ArrayList($this$fastFilterIndexed$iv.size());
        int size = $this$fastFilterIndexed$iv.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = $this$fastFilterIndexed$iv.get(index$iv$iv);
            int index$iv = index$iv$iv;
            ((Number) item$iv$iv).intValue();
            int i = this.groupGapStart;
            if (index$iv < i || index$iv >= i + this.groupGapLen) {
                target$iv.add(item$iv$iv);
            }
        }
        return target$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAnchor(int index, int gapStart, int gapLen, int capacity) {
        return index > gapStart ? -(((capacity - gapLen) - index) + 1) : index;
    }

    private final int dataAnchorToDataIndex(int anchor, int gapLen, int capacity) {
        return anchor < 0 ? (capacity - gapLen) + anchor + 1 : anchor;
    }

    private final int parentIndexToAnchor(int index, int gapStart) {
        return index < gapStart ? index : -((getSize$runtime_release() - index) + 2);
    }

    private final int parentAnchorToIndex(int index) {
        return index > -2 ? index : (getSize$runtime_release() + index) - (-2);
    }
}
