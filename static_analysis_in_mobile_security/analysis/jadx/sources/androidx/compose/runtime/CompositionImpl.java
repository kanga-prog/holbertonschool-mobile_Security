package androidx.compose.runtime;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.collection.IdentityArrayMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.collection.IdentityScopeMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Composition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0002\u0094\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\b\u0010U\u001a\u00020\u001cH\u0002J\u001e\u0010V\u001a\u00020\u001c2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020/0X2\u0006\u0010Y\u001a\u00020\u0010H\u0002J\b\u0010Z\u001a\u00020\u001cH\u0016Jc\u0010[\u001a\u00020\u001c2Y\u0010\u0013\u001aU\u0012Q\u0012O\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0006¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c0\u0015j\u0002`\u001d0\u0014H\u0002J\b\u0010\\\u001a\u00020\u001cH\u0016J\b\u0010]\u001a\u00020\u001cH\u0016J\b\u0010^\u001a\u00020\u001cH\u0002J \u0010_\u001a\u00020\u001c2\u0011\u0010`\u001a\r\u0012\u0004\u0012\u00020\u001c0\u001f¢\u0006\u0002\b H\u0016¢\u0006\u0002\u0010$J3\u0010a\u001a\u0002Hb\"\u0004\b\u0000\u0010b2\b\u0010c\u001a\u0004\u0018\u00010\u00012\u0006\u0010d\u001a\u00020;2\f\u0010e\u001a\b\u0012\u0004\u0012\u0002Hb0\u001fH\u0016¢\u0006\u0002\u0010fJ\b\u0010g\u001a\u00020\u001cH\u0016J\u0010\u0010h\u001a\u00020\u001c2\u0006\u0010i\u001a\u00020jH\u0016J\b\u0010k\u001a\u00020\u001cH\u0002J\b\u0010l\u001a\u00020\u001cH\u0002J\"\u0010m\u001a\u0002Hn\"\u0004\b\u0000\u0010n2\f\u0010e\u001a\b\u0012\u0004\u0012\u0002Hn0\u001fH\u0082\b¢\u0006\u0002\u0010oJK\u0010p\u001a\u0002Hn\"\u0004\b\u0000\u0010n25\u0010e\u001a1\u0012'\u0012%\u0012\u0004\u0012\u00020*\u0012\f\u0012\n\u0012\u0004\u0012\u00020/\u0018\u00010>0=¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u0002Hn0qH\u0082\b¢\u0006\u0002\u0010rJ$\u0010s\u001a\u00020\u001c2\u001a\u0010t\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020v\u0012\u0006\u0012\u0004\u0018\u00010v0u0)H\u0016J\u001a\u0010w\u001a\u00020x2\u0006\u0010y\u001a\u00020*2\b\u0010z\u001a\u0004\u0018\u00010/H\u0016J\b\u0010{\u001a\u00020\u001cH\u0016J\"\u0010|\u001a\u00020x2\u0006\u0010y\u001a\u00020*2\u0006\u0010}\u001a\u00020~2\b\u0010z\u001a\u0004\u0018\u00010/H\u0002J\u000f\u0010\u007f\u001a\u00020\u001c2\u0007\u0010\u0080\u0001\u001a\u00020;J\u0012\u0010\u0081\u0001\u001a\u00020\u001c2\u0007\u0010\u0082\u0001\u001a\u00020/H\u0002J\u0017\u0010\u0083\u0001\u001a\u00020\u00102\f\u0010W\u001a\b\u0012\u0004\u0012\u00020/0XH\u0016J\u0017\u0010\u0084\u0001\u001a\u00020\u001c2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001fH\u0016J\t\u0010\u0085\u0001\u001a\u00020\u0010H\u0016J\u0011\u0010\u0086\u0001\u001a\u00020\u001c2\u0006\u0010y\u001a\u00020*H\u0016J\u0017\u0010\u0087\u0001\u001a\u00020\u001c2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020/0XH\u0016J\u0012\u0010\u0088\u0001\u001a\u00020\u001c2\u0007\u0010\u0082\u0001\u001a\u00020/H\u0016J\u0012\u0010\u0089\u0001\u001a\u00020\u001c2\u0007\u0010\u0082\u0001\u001a\u00020/H\u0016J\u001b\u0010\u008a\u0001\u001a\u00020\u001c2\n\u0010i\u001a\u0006\u0012\u0002\b\u000303H\u0000¢\u0006\u0003\b\u008b\u0001J\u001f\u0010\u008c\u0001\u001a\u00020\u001c2\u0006\u0010z\u001a\u00020/2\u0006\u0010y\u001a\u00020*H\u0000¢\u0006\u0003\b\u008d\u0001J!\u0010\u008e\u0001\u001a\u00020\u001c2\u0011\u0010`\u001a\r\u0012\u0004\u0012\u00020\u001c0\u001f¢\u0006\u0002\b H\u0016¢\u0006\u0002\u0010$J\u001d\u0010\u008f\u0001\u001a\u0016\u0012\u0004\u0012\u00020*\u0012\f\u0012\n\u0012\u0004\u0012\u00020/\u0018\u00010>0=H\u0002J#\u0010\u0090\u0001\u001a\u0002Hn\"\u0004\b\u0000\u0010n2\f\u0010e\u001a\b\u0012\u0004\u0012\u0002Hn0\u001fH\u0082\b¢\u0006\u0002\u0010oJ\u001b\u0010\u0091\u0001\u001a\u00020\u00102\u0006\u0010y\u001a\u00020*2\b\u0010z\u001a\u0004\u0018\u00010/H\u0002J\u0011\u0010\u0092\u0001\u001a\u00020\u001c2\u0006\u0010Q\u001a\u00020RH\u0002J\t\u0010\u0093\u0001\u001a\u00020\u001cH\u0016JE\u0010V\u001a\u0016\u0012\u0004\u0012\u00020*\u0018\u00010\fj\n\u0012\u0004\u0012\u00020*\u0018\u0001`\u000e*\u0016\u0012\u0004\u0012\u00020*\u0018\u00010\fj\n\u0012\u0004\u0012\u00020*\u0018\u0001`\u000e2\u0007\u0010\u0082\u0001\u001a\u00020/2\u0006\u0010Y\u001a\u00020\u0010H\u0002R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012Ra\u0010\u0013\u001aU\u0012Q\u0012O\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0006¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c0\u0015j\u0002`\u001d0\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\u001e\u001a\r\u0012\u0004\u0012\u00020\u001c0\u001f¢\u0006\u0002\b X\u0086\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u001e\u0010-\u001a\u0012\u0012\u0004\u0012\u00020*0\fj\b\u0012\u0004\u0012\u00020*`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020/0)8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b0\u0010,R\u0018\u00101\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030302X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u0010\u0012R\u0014\u00107\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u0010\u0012R\u0010\u00109\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010<\u001a\u0016\u0012\u0004\u0012\u00020*\u0012\f\u0012\n\u0012\u0004\u0012\u00020/\u0018\u00010>0=X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010?\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010\u0012R\u0014\u0010@\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b@\u0010\u0012R\u0011\u0010A\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u0012Ra\u0010B\u001aU\u0012Q\u0012O\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0006¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c0\u0015j\u0002`\u001d0\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010D\u001a\b\u0012\u0004\u0012\u00020*02X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010E\u001a\b\u0012\u0004\u0012\u00020*02X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020/0)8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010,R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010H\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0012\"\u0004\bJ\u0010KR\"\u0010L\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010/0Mj\n\u0012\u0006\u0012\u0004\u0018\u00010/`NX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\bO\u0010PR\u0014\u0010Q\u001a\u00020RX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bS\u0010T¨\u0006\u0095\u0001"}, d2 = {"Landroidx/compose/runtime/CompositionImpl;", "Landroidx/compose/runtime/ControlledComposition;", "Landroidx/compose/runtime/RecomposeScopeOwner;", "parent", "Landroidx/compose/runtime/CompositionContext;", "applier", "Landroidx/compose/runtime/Applier;", "recomposeContext", "Lkotlin/coroutines/CoroutineContext;", "(Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/Applier;Lkotlin/coroutines/CoroutineContext;)V", "_recomposeContext", "abandonSet", "Ljava/util/HashSet;", "Landroidx/compose/runtime/RememberObserver;", "Lkotlin/collections/HashSet;", "areChildrenComposing", "", "getAreChildrenComposing", "()Z", "changes", "", "Lkotlin/Function3;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "", "Landroidx/compose/runtime/Change;", "composable", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "getComposable", "()Lkotlin/jvm/functions/Function2;", "setComposable", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "composer", "Landroidx/compose/runtime/ComposerImpl;", "conditionalScopes", "", "Landroidx/compose/runtime/RecomposeScopeImpl;", "getConditionalScopes$runtime_release", "()Ljava/util/List;", "conditionallyInvalidatedScopes", "derivedStateDependencies", "", "getDerivedStateDependencies$runtime_release", "derivedStates", "Landroidx/compose/runtime/collection/IdentityScopeMap;", "Landroidx/compose/runtime/DerivedState;", "disposed", "hasInvalidations", "getHasInvalidations", "hasPendingChanges", "getHasPendingChanges", "invalidationDelegate", "invalidationDelegateGroup", "", "invalidations", "Landroidx/compose/runtime/collection/IdentityArrayMap;", "Landroidx/compose/runtime/collection/IdentityArraySet;", "isComposing", "isDisposed", "isRoot", "lateChanges", "lock", "observations", "observationsProcessed", "observedObjects", "getObservedObjects$runtime_release", "pendingInvalidScopes", "getPendingInvalidScopes$runtime_release", "setPendingInvalidScopes$runtime_release", "(Z)V", "pendingModifications", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/AtomicReference;", "getRecomposeContext", "()Lkotlin/coroutines/CoroutineContext;", "slotTable", "Landroidx/compose/runtime/SlotTable;", "getSlotTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "abandonChanges", "addPendingInvalidationsLocked", "values", "", "forgetConditionalScopes", "applyChanges", "applyChangesInLocked", "applyLateChanges", "changesApplied", "cleanUpDerivedStateObservations", "composeContent", "content", "delegateInvalidations", "R", "to", "groupIndex", "block", "(Landroidx/compose/runtime/ControlledComposition;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "dispose", "disposeUnusedMovableContent", "state", "Landroidx/compose/runtime/MovableContentState;", "drainPendingModificationsForCompositionLocked", "drainPendingModificationsLocked", "guardChanges", "T", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "guardInvalidationsLocked", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "insertMovableContent", "references", "Lkotlin/Pair;", "Landroidx/compose/runtime/MovableContentStateReference;", "invalidate", "Landroidx/compose/runtime/InvalidationResult;", "scope", "instance", "invalidateAll", "invalidateChecked", "anchor", "Landroidx/compose/runtime/Anchor;", "invalidateGroupsWithKey", "key", "invalidateScopeOfLocked", "value", "observesAnyOf", "prepareCompose", "recompose", "recomposeScopeReleased", "recordModificationsOf", "recordReadOf", "recordWriteOf", "removeDerivedStateObservation", "removeDerivedStateObservation$runtime_release", "removeObservation", "removeObservation$runtime_release", "setContent", "takeInvalidations", "trackAbandonedValues", "tryImminentInvalidation", "validateRecomposeScopeAnchors", "verifyConsistent", "RememberEventDispatcher", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CompositionImpl implements ControlledComposition, RecomposeScopeOwner {
    private final CoroutineContext _recomposeContext;
    private final HashSet<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final List<Function3<Applier<?>, SlotWriter, RememberManager, Unit>> changes;
    private Function2<? super Composer, ? super Integer, Unit> composable;
    private final ComposerImpl composer;
    private final HashSet<RecomposeScopeImpl> conditionallyInvalidatedScopes;
    private final IdentityScopeMap<DerivedState<?>> derivedStates;
    private boolean disposed;
    private CompositionImpl invalidationDelegate;
    private int invalidationDelegateGroup;
    private IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> invalidations;
    private final boolean isRoot;
    private final List<Function3<Applier<?>, SlotWriter, RememberManager, Unit>> lateChanges;
    private final Object lock;
    private final IdentityScopeMap<RecomposeScopeImpl> observations;
    private final IdentityScopeMap<RecomposeScopeImpl> observationsProcessed;
    private final CompositionContext parent;
    private boolean pendingInvalidScopes;
    private final AtomicReference<Object> pendingModifications;
    private final SlotTable slotTable;

    public CompositionImpl(CompositionContext parent, Applier<?> applier, CoroutineContext recomposeContext) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(applier, "applier");
        this.parent = parent;
        this.applier = applier;
        this.pendingModifications = new AtomicReference<>(null);
        this.lock = new Object();
        HashSet<RememberObserver> hashSet = new HashSet<>();
        this.abandonSet = hashSet;
        SlotTable slotTable = new SlotTable();
        this.slotTable = slotTable;
        this.observations = new IdentityScopeMap<>();
        this.conditionallyInvalidatedScopes = new HashSet<>();
        this.derivedStates = new IdentityScopeMap<>();
        ArrayList arrayList = new ArrayList();
        this.changes = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.lateChanges = arrayList2;
        this.observationsProcessed = new IdentityScopeMap<>();
        this.invalidations = new IdentityArrayMap<>(0, 1, null);
        ComposerImpl it = new ComposerImpl(applier, parent, slotTable, hashSet, arrayList, arrayList2, this);
        parent.registerComposer$runtime_release(it);
        this.composer = it;
        this._recomposeContext = recomposeContext;
        this.isRoot = parent instanceof Recomposer;
        this.composable = ComposableSingletons$CompositionKt.INSTANCE.m2570getLambda1$runtime_release();
    }

    public /* synthetic */ CompositionImpl(CompositionContext compositionContext, Applier applier, CoroutineContext coroutineContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(compositionContext, applier, (i & 4) != 0 ? null : coroutineContext);
    }

    /* JADX INFO: renamed from: getSlotTable$runtime_release, reason: from getter */
    public final SlotTable getSlotTable() {
        return this.slotTable;
    }

    public final List<Object> getObservedObjects$runtime_release() {
        return ArraysKt.filterNotNull(this.observations.getValues());
    }

    public final List<Object> getDerivedStateDependencies$runtime_release() {
        return ArraysKt.filterNotNull(this.derivedStates.getValues());
    }

    public final List<RecomposeScopeImpl> getConditionalScopes$runtime_release() {
        return CollectionsKt.toList(this.conditionallyInvalidatedScopes);
    }

    /* JADX INFO: renamed from: getPendingInvalidScopes$runtime_release, reason: from getter */
    public final boolean getPendingInvalidScopes() {
        return this.pendingInvalidScopes;
    }

    public final void setPendingInvalidScopes$runtime_release(boolean z) {
        this.pendingInvalidScopes = z;
    }

    public final CoroutineContext getRecomposeContext() {
        CoroutineContext coroutineContext = this._recomposeContext;
        return coroutineContext == null ? this.parent.getRecomposeCoroutineContext$runtime_release() : coroutineContext;
    }

    /* JADX INFO: renamed from: isRoot, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    private final boolean getAreChildrenComposing() {
        return this.composer.getAreChildrenComposing$runtime_release();
    }

    public final Function2<Composer, Integer, Unit> getComposable() {
        return this.composable;
    }

    public final void setComposable(Function2<? super Composer, ? super Integer, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.composable = function2;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean isComposing() {
        return this.composer.getIsComposing();
    }

    @Override // androidx.compose.runtime.Composition
    /* JADX INFO: renamed from: isDisposed, reason: from getter */
    public boolean getDisposed() {
        return this.disposed;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean getHasPendingChanges() {
        boolean hasPendingChanges$runtime_release;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            hasPendingChanges$runtime_release = this.composer.getHasPendingChanges$runtime_release();
        }
        return hasPendingChanges$runtime_release;
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(Function2<? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        if (!(!this.disposed)) {
            throw new IllegalStateException("The composition is disposed".toString());
        }
        this.composable = content;
        this.parent.composeInitial$runtime_release(this, content);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0046  */
    public final void invalidateGroupsWithKey(int key) {
        boolean forceComposition;
        boolean z;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                try {
                    List<RecomposeScopeImpl> listInvalidateGroupsWithKey$runtime_release = this.slotTable.invalidateGroupsWithKey$runtime_release(key);
                    if (listInvalidateGroupsWithKey$runtime_release != null) {
                        int index$iv$iv = 0;
                        int size = listInvalidateGroupsWithKey$runtime_release.size();
                        while (true) {
                            if (index$iv$iv < size) {
                                Object item$iv$iv = listInvalidateGroupsWithKey$runtime_release.get(index$iv$iv);
                                RecomposeScopeImpl it = (RecomposeScopeImpl) item$iv$iv;
                                if (it.invalidateForResult(null) == InvalidationResult.IGNORED) {
                                    z = true;
                                    break;
                                }
                                index$iv$iv++;
                            } else {
                                z = false;
                                break;
                            }
                        }
                        if (z) {
                            forceComposition = true;
                        } else {
                            forceComposition = false;
                        }
                    } else {
                        forceComposition = true;
                    }
                    if (forceComposition && this.composer.forceRecomposeScopes$runtime_release()) {
                        this.parent.invalidate$runtime_release(this);
                    }
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private final void drainPendingModificationsForCompositionLocked() {
        Object toRecord = this.pendingModifications.getAndSet(CompositionKt.PendingApplyNoModifications);
        if (toRecord != null) {
            if (Intrinsics.areEqual(toRecord, CompositionKt.PendingApplyNoModifications)) {
                ComposerKt.composeRuntimeError("pending composition has not been applied");
                throw new KotlinNothingValueException();
            }
            if (toRecord instanceof Set) {
                addPendingInvalidationsLocked((Set) toRecord, true);
                return;
            }
            if (!(toRecord instanceof Object[])) {
                ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
                throw new KotlinNothingValueException();
            }
            for (Set<? extends Object> set : (Set[]) toRecord) {
                addPendingInvalidationsLocked(set, true);
            }
        }
    }

    private final void drainPendingModificationsLocked() {
        Object toRecord = this.pendingModifications.getAndSet(null);
        if (!Intrinsics.areEqual(toRecord, CompositionKt.PendingApplyNoModifications)) {
            if (toRecord instanceof Set) {
                addPendingInvalidationsLocked((Set) toRecord, false);
                return;
            }
            if (!(toRecord instanceof Object[])) {
                if (toRecord == null) {
                    ComposerKt.composeRuntimeError("calling recordModificationsOf and applyChanges concurrently is not supported");
                    throw new KotlinNothingValueException();
                }
                ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
                throw new KotlinNothingValueException();
            }
            for (Set<? extends Object> set : (Set[]) toRecord) {
                addPendingInvalidationsLocked(set, false);
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void composeContent(Function2<? super Composer, ? super Integer, Unit> content) throws Exception {
        Intrinsics.checkNotNullParameter(content, "content");
        try {
            Object lock$iv = this.lock;
            synchronized (lock$iv) {
                try {
                    try {
                        drainPendingModificationsForCompositionLocked();
                        IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> identityArrayMapTakeInvalidations = takeInvalidations();
                        try {
                            try {
                                this.composer.composeContent$runtime_release(identityArrayMapTakeInvalidations, content);
                                Unit unit = Unit.INSTANCE;
                                Unit unit2 = Unit.INSTANCE;
                                Unit unit3 = Unit.INSTANCE;
                            } catch (Throwable th) {
                                e$iv = th;
                                throw e$iv;
                            }
                        } catch (Exception e$iv) {
                            this.invalidations = identityArrayMapTakeInvalidations;
                            throw e$iv;
                        }
                    } catch (Throwable th2) {
                        e$iv = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (0 == 0) {
                        try {
                            if (!this.abandonSet.isEmpty()) {
                                new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                            }
                        } catch (Exception e$iv2) {
                            abandonChanges();
                            throw e$iv2;
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            if (!this.disposed) {
                this.disposed = true;
                this.composable = ComposableSingletons$CompositionKt.INSTANCE.m2571getLambda2$runtime_release();
                List<Function3<Applier<?>, SlotWriter, RememberManager, Unit>> deferredChanges$runtime_release = this.composer.getDeferredChanges$runtime_release();
                if (deferredChanges$runtime_release != null) {
                    applyChangesInLocked(deferredChanges$runtime_release);
                }
                boolean nonEmptySlotTable = this.slotTable.getGroupsSize() > 0;
                if (nonEmptySlotTable || (true ^ this.abandonSet.isEmpty())) {
                    RememberEventDispatcher manager = new RememberEventDispatcher(this.abandonSet);
                    if (nonEmptySlotTable) {
                        this.applier.onBeginChanges();
                        SlotTable this_$iv = this.slotTable;
                        SlotWriter writer$iv = this_$iv.openWriter();
                        try {
                            ComposerKt.removeCurrentGroup(writer$iv, manager);
                            Unit unit = Unit.INSTANCE;
                            writer$iv.close();
                            this.applier.clear();
                            this.applier.onEndChanges();
                            manager.dispatchRememberObservers();
                        } catch (Throwable th) {
                            writer$iv.close();
                            throw th;
                        }
                    }
                    manager.dispatchAbandons();
                }
                this.composer.dispose$runtime_release();
            }
            Unit unit2 = Unit.INSTANCE;
        }
        this.parent.unregisterComposition$runtime_release(this);
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        boolean z;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            z = this.invalidations.getSize() > 0;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordModificationsOf(Set<? extends Object> values) {
        Object obj;
        Object objPlus;
        Intrinsics.checkNotNullParameter(values, "values");
        do {
            obj = this.pendingModifications.get();
            if (obj == null ? true : Intrinsics.areEqual(obj, CompositionKt.PendingApplyNoModifications)) {
                objPlus = values;
            } else if (obj instanceof Set) {
                objPlus = new Set[]{obj, values};
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalStateException(("corrupt pendingModifications: " + this.pendingModifications).toString());
                }
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.collections.Set<kotlin.Any>>");
                objPlus = ArraysKt.plus((Set<? extends Object>[]) obj, values);
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingModifications, obj, objPlus));
        if (obj != null) {
            return;
        }
        synchronized (this.lock) {
            drainPendingModificationsLocked();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean observesAnyOf(Set<? extends Object> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        for (Object value : values) {
            if (this.observations.contains(value) || this.derivedStates.contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void prepareCompose(Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.composer.prepareCompose$runtime_release(block);
    }

    private final HashSet<RecomposeScopeImpl> addPendingInvalidationsLocked(HashSet<RecomposeScopeImpl> hashSet, Object value, boolean forgetConditionalScopes) {
        HashSet<RecomposeScopeImpl> hashSet2 = hashSet;
        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap = this.observations;
        int index$iv = identityScopeMap.find(value);
        if (index$iv >= 0) {
            IdentityArraySet this_$iv$iv = identityScopeMap.scopeSetAt(index$iv);
            Object[] values$iv$iv = this_$iv$iv.getValues();
            int size = this_$iv$iv.size();
            for (int i$iv$iv = 0; i$iv$iv < size; i$iv$iv++) {
                Object obj = values$iv$iv[i$iv$iv];
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                RecomposeScopeImpl scope = (RecomposeScopeImpl) obj;
                if (!this.observationsProcessed.remove(value, scope) && scope.invalidateForResult(value) != InvalidationResult.IGNORED) {
                    if (scope.isConditional() && !forgetConditionalScopes) {
                        this.conditionallyInvalidatedScopes.add(scope);
                    } else {
                        if (hashSet2 == null) {
                            hashSet2 = new HashSet<>();
                        }
                        hashSet2.add(scope);
                    }
                }
            }
        }
        return hashSet2;
    }

    /* JADX WARN: Code duplicated, block: B:111:0x01a1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:57:0x019b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:58:0x019d  */
    private final void addPendingInvalidationsLocked(Set<? extends Object> values, boolean forgetConditionalScopes) {
        boolean z;
        HashSet<RecomposeScopeImpl> hashSetAddPendingInvalidationsLocked = null;
        Set $this$fastForEach$iv = values;
        int $i$f$fastForEach = 0;
        Object obj = null;
        if ($this$fastForEach$iv instanceof IdentityArraySet) {
            IdentityArraySet this_$iv$iv = (IdentityArraySet) $this$fastForEach$iv;
            Object[] values$iv$iv = this_$iv$iv.getValues();
            int i$iv$iv = 0;
            int size = this_$iv$iv.size();
            while (i$iv$iv < size) {
                Object value = values$iv$iv[i$iv$iv];
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                if (value instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) value).invalidateForResult(obj);
                } else {
                    hashSetAddPendingInvalidationsLocked = addPendingInvalidationsLocked(hashSetAddPendingInvalidationsLocked, value, forgetConditionalScopes);
                    IdentityScopeMap<DerivedState<?>> identityScopeMap = this.derivedStates;
                    int index$iv = identityScopeMap.find(value);
                    if (index$iv >= 0) {
                        IdentityArraySet this_$iv$iv2 = identityScopeMap.scopeSetAt(index$iv);
                        Object[] values$iv$iv2 = this_$iv$iv2.getValues();
                        int size2 = this_$iv$iv2.size();
                        HashSet<RecomposeScopeImpl> hashSetAddPendingInvalidationsLocked2 = hashSetAddPendingInvalidationsLocked;
                        int i$iv$iv2 = 0;
                        while (i$iv$iv2 < size2) {
                            int i = size2;
                            Object obj2 = values$iv$iv2[i$iv$iv2];
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                            DerivedState it = (DerivedState) obj2;
                            hashSetAddPendingInvalidationsLocked2 = addPendingInvalidationsLocked(hashSetAddPendingInvalidationsLocked2, it, forgetConditionalScopes);
                            i$iv$iv2++;
                            size2 = i;
                        }
                        hashSetAddPendingInvalidationsLocked = hashSetAddPendingInvalidationsLocked2;
                    }
                }
                i$iv$iv++;
                this_$iv$iv = this_$iv$iv;
                $i$f$fastForEach = $i$f$fastForEach;
                obj = null;
            }
        } else {
            Set $this$forEach$iv$iv = $this$fastForEach$iv;
            for (Object element$iv$iv : $this$forEach$iv$iv) {
                if (element$iv$iv instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) element$iv$iv).invalidateForResult(null);
                } else {
                    hashSetAddPendingInvalidationsLocked = addPendingInvalidationsLocked(hashSetAddPendingInvalidationsLocked, element$iv$iv, forgetConditionalScopes);
                    IdentityScopeMap<DerivedState<?>> identityScopeMap2 = this.derivedStates;
                    int index$iv2 = identityScopeMap2.find(element$iv$iv);
                    if (index$iv2 >= 0) {
                        IdentityArraySet this_$iv$iv3 = identityScopeMap2.scopeSetAt(index$iv2);
                        Object[] values$iv$iv3 = this_$iv$iv3.getValues();
                        int size3 = this_$iv$iv3.size();
                        HashSet<RecomposeScopeImpl> hashSetAddPendingInvalidationsLocked3 = hashSetAddPendingInvalidationsLocked;
                        int i$iv$iv3 = 0;
                        while (i$iv$iv3 < size3) {
                            int i2 = size3;
                            Object obj3 = values$iv$iv3[i$iv$iv3];
                            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                            DerivedState it2 = (DerivedState) obj3;
                            hashSetAddPendingInvalidationsLocked3 = addPendingInvalidationsLocked(hashSetAddPendingInvalidationsLocked3, it2, forgetConditionalScopes);
                            i$iv$iv3++;
                            size3 = i2;
                        }
                        hashSetAddPendingInvalidationsLocked = hashSetAddPendingInvalidationsLocked3;
                    }
                }
                $this$forEach$iv$iv = $this$forEach$iv$iv;
                $this$fastForEach$iv = $this$fastForEach$iv;
            }
        }
        if (!forgetConditionalScopes || !(!this.conditionallyInvalidatedScopes.isEmpty())) {
            if (hashSetAddPendingInvalidationsLocked != null) {
                HashSet<RecomposeScopeImpl> hashSet = hashSetAddPendingInvalidationsLocked;
                int i3 = 0;
                IdentityScopeMap<RecomposeScopeImpl> identityScopeMap3 = this.observations;
                int $i$f$removeValueIf = 0;
                int[] valueOrder$iv$iv = identityScopeMap3.getValueOrder();
                IdentityArraySet<RecomposeScopeImpl>[] scopeSets = identityScopeMap3.getScopeSets();
                Object[] values$iv$iv4 = identityScopeMap3.getValues();
                int destinationIndex$iv$iv = 0;
                int i$iv$iv4 = 0;
                int size4 = identityScopeMap3.getSize();
                while (i$iv$iv4 < size4) {
                    int valueIndex$iv$iv = valueOrder$iv$iv[i$iv$iv4];
                    IdentityArraySet<RecomposeScopeImpl> identityArraySet = scopeSets[valueIndex$iv$iv];
                    Intrinsics.checkNotNull(identityArraySet);
                    Object[] values$iv$iv5 = identityArraySet.getValues();
                    int size$iv$iv = identityArraySet.size();
                    HashSet<RecomposeScopeImpl> hashSet2 = hashSetAddPendingInvalidationsLocked;
                    int destinationIndex$iv$iv2 = 0;
                    int destinationIndex$iv$iv3 = i3;
                    int i$iv$iv5 = 0;
                    while (i$iv$iv5 < size$iv$iv) {
                        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap4 = identityScopeMap3;
                        Object item$iv$iv = values$iv$iv5[i$iv$iv5];
                        Intrinsics.checkNotNull(item$iv$iv, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                        int $i$f$removeValueIf2 = $i$f$removeValueIf;
                        if (!hashSet.contains((RecomposeScopeImpl) item$iv$iv)) {
                            if (destinationIndex$iv$iv2 != i$iv$iv5) {
                                values$iv$iv5[destinationIndex$iv$iv2] = item$iv$iv;
                            }
                            destinationIndex$iv$iv2++;
                        }
                        i$iv$iv5++;
                        identityScopeMap3 = identityScopeMap4;
                        $i$f$removeValueIf = $i$f$removeValueIf2;
                    }
                    IdentityScopeMap<RecomposeScopeImpl> identityScopeMap5 = identityScopeMap3;
                    int $i$f$removeValueIf3 = $i$f$removeValueIf;
                    for (int i$iv$iv6 = destinationIndex$iv$iv2; i$iv$iv6 < size$iv$iv; i$iv$iv6++) {
                        values$iv$iv5[i$iv$iv6] = null;
                    }
                    ((IdentityArraySet) identityArraySet).size = destinationIndex$iv$iv2;
                    if (identityArraySet.size() > 0) {
                        if (destinationIndex$iv$iv != i$iv$iv4) {
                            int destinationKeyOrder$iv$iv = valueOrder$iv$iv[destinationIndex$iv$iv];
                            valueOrder$iv$iv[destinationIndex$iv$iv] = valueIndex$iv$iv;
                            valueOrder$iv$iv[i$iv$iv4] = destinationKeyOrder$iv$iv;
                        }
                        destinationIndex$iv$iv++;
                    }
                    i$iv$iv4++;
                    i3 = destinationIndex$iv$iv3;
                    identityScopeMap3 = identityScopeMap5;
                    hashSetAddPendingInvalidationsLocked = hashSet2;
                    $i$f$removeValueIf = $i$f$removeValueIf3;
                }
                int size5 = identityScopeMap3.getSize();
                for (int i$iv$iv7 = destinationIndex$iv$iv; i$iv$iv7 < size5; i$iv$iv7++) {
                    values$iv$iv4[valueOrder$iv$iv[i$iv$iv7]] = null;
                }
                identityScopeMap3.setSize(destinationIndex$iv$iv);
                cleanUpDerivedStateObservations();
                return;
            }
            return;
        }
        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap6 = this.observations;
        int $i$f$removeValueIf4 = 0;
        int $i$f$removingScopes = 0;
        int[] valueOrder$iv$iv2 = identityScopeMap6.getValueOrder();
        IdentityArraySet<RecomposeScopeImpl>[] scopeSets2 = identityScopeMap6.getScopeSets();
        Object[] values$iv$iv6 = identityScopeMap6.getValues();
        int destinationIndex$iv$iv4 = 0;
        int i$iv$iv8 = 0;
        int size6 = identityScopeMap6.getSize();
        while (i$iv$iv8 < size6) {
            int valueIndex$iv$iv2 = valueOrder$iv$iv2[i$iv$iv8];
            IdentityArraySet<RecomposeScopeImpl> identityArraySet2 = scopeSets2[valueIndex$iv$iv2];
            Intrinsics.checkNotNull(identityArraySet2);
            Object[] values$iv$iv7 = identityArraySet2.getValues();
            int size$iv$iv2 = identityArraySet2.size();
            int destinationIndex$iv$iv5 = 0;
            IdentityScopeMap<RecomposeScopeImpl> identityScopeMap7 = identityScopeMap6;
            int i$iv$iv9 = 0;
            while (i$iv$iv9 < size$iv$iv2) {
                int $i$f$removeValueIf5 = $i$f$removeValueIf4;
                Object item$iv$iv2 = values$iv$iv7[i$iv$iv9];
                Intrinsics.checkNotNull(item$iv$iv2, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                int $i$f$removingScopes2 = $i$f$removingScopes;
                RecomposeScopeImpl scope = (RecomposeScopeImpl) item$iv$iv2;
                IdentityArraySet<RecomposeScopeImpl>[] identityArraySetArr = scopeSets2;
                if (!this.conditionallyInvalidatedScopes.contains(scope)) {
                    if (!(hashSetAddPendingInvalidationsLocked != null && hashSetAddPendingInvalidationsLocked.contains(scope))) {
                        z = false;
                    }
                    if (z) {
                        if (destinationIndex$iv$iv5 != i$iv$iv9) {
                            values$iv$iv7[destinationIndex$iv$iv5] = item$iv$iv2;
                        }
                        destinationIndex$iv$iv5++;
                    }
                    i$iv$iv9++;
                    $i$f$removeValueIf4 = $i$f$removeValueIf5;
                    $i$f$removingScopes = $i$f$removingScopes2;
                    scopeSets2 = identityArraySetArr;
                }
                z = true;
                if (z) {
                    if (destinationIndex$iv$iv5 != i$iv$iv9) {
                        values$iv$iv7[destinationIndex$iv$iv5] = item$iv$iv2;
                    }
                    destinationIndex$iv$iv5++;
                }
                i$iv$iv9++;
                $i$f$removeValueIf4 = $i$f$removeValueIf5;
                $i$f$removingScopes = $i$f$removingScopes2;
                scopeSets2 = identityArraySetArr;
            }
            int $i$f$removeValueIf6 = $i$f$removeValueIf4;
            int $i$f$removingScopes3 = $i$f$removingScopes;
            IdentityArraySet<RecomposeScopeImpl>[] identityArraySetArr2 = scopeSets2;
            for (int i$iv$iv10 = destinationIndex$iv$iv5; i$iv$iv10 < size$iv$iv2; i$iv$iv10++) {
                values$iv$iv7[i$iv$iv10] = null;
            }
            ((IdentityArraySet) identityArraySet2).size = destinationIndex$iv$iv5;
            if (identityArraySet2.size() > 0) {
                if (destinationIndex$iv$iv4 != i$iv$iv8) {
                    int destinationKeyOrder$iv$iv2 = valueOrder$iv$iv2[destinationIndex$iv$iv4];
                    valueOrder$iv$iv2[destinationIndex$iv$iv4] = valueIndex$iv$iv2;
                    valueOrder$iv$iv2[i$iv$iv8] = destinationKeyOrder$iv$iv2;
                }
                destinationIndex$iv$iv4++;
            }
            i$iv$iv8++;
            identityScopeMap6 = identityScopeMap7;
            $i$f$removeValueIf4 = $i$f$removeValueIf6;
            $i$f$removingScopes = $i$f$removingScopes3;
            scopeSets2 = identityArraySetArr2;
        }
        int size7 = identityScopeMap6.getSize();
        for (int i$iv$iv11 = destinationIndex$iv$iv4; i$iv$iv11 < size7; i$iv$iv11++) {
            values$iv$iv6[valueOrder$iv$iv2[i$iv$iv11]] = null;
        }
        identityScopeMap6.setSize(destinationIndex$iv$iv4);
        this.conditionallyInvalidatedScopes.clear();
        cleanUpDerivedStateObservations();
    }

    private final void cleanUpDerivedStateObservations() {
        IdentityScopeMap<DerivedState<?>> identityScopeMap = this.derivedStates;
        int $i$f$removeValueIf = 0;
        int $i$f$removingScopes = 0;
        int[] valueOrder$iv$iv = identityScopeMap.getValueOrder();
        IdentityArraySet<DerivedState<?>>[] scopeSets = identityScopeMap.getScopeSets();
        Object[] values$iv$iv = identityScopeMap.getValues();
        int destinationIndex$iv$iv = 0;
        int i$iv$iv = 0;
        int size = identityScopeMap.getSize();
        while (i$iv$iv < size) {
            int valueIndex$iv$iv = valueOrder$iv$iv[i$iv$iv];
            IdentityArraySet<DerivedState<?>> identityArraySet = scopeSets[valueIndex$iv$iv];
            Intrinsics.checkNotNull(identityArraySet);
            Object[] values$iv$iv2 = identityArraySet.getValues();
            int size$iv$iv = identityArraySet.size();
            IdentityScopeMap<DerivedState<?>> identityScopeMap2 = identityScopeMap;
            int destinationIndex$iv$iv2 = 0;
            int destinationIndex$iv$iv3 = $i$f$removeValueIf;
            int $i$f$removeValueIf2 = 0;
            while ($i$f$removeValueIf2 < size$iv$iv) {
                int $i$f$removingScopes2 = $i$f$removingScopes;
                Object item$iv$iv = values$iv$iv2[$i$f$removeValueIf2];
                IdentityArraySet<DerivedState<?>>[] identityArraySetArr = scopeSets;
                Intrinsics.checkNotNull(item$iv$iv, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                DerivedState derivedState = (DerivedState) item$iv$iv;
                int i = size;
                if (!(!this.observations.contains(derivedState))) {
                    if (destinationIndex$iv$iv2 != $i$f$removeValueIf2) {
                        values$iv$iv2[destinationIndex$iv$iv2] = item$iv$iv;
                    }
                    destinationIndex$iv$iv2++;
                }
                $i$f$removeValueIf2++;
                $i$f$removingScopes = $i$f$removingScopes2;
                scopeSets = identityArraySetArr;
                size = i;
            }
            int $i$f$removingScopes3 = $i$f$removingScopes;
            IdentityArraySet<DerivedState<?>>[] identityArraySetArr2 = scopeSets;
            int i2 = size;
            for (int i$iv$iv2 = destinationIndex$iv$iv2; i$iv$iv2 < size$iv$iv; i$iv$iv2++) {
                values$iv$iv2[i$iv$iv2] = null;
            }
            ((IdentityArraySet) identityArraySet).size = destinationIndex$iv$iv2;
            if (identityArraySet.size() > 0) {
                if (destinationIndex$iv$iv != i$iv$iv) {
                    int destinationKeyOrder$iv$iv = valueOrder$iv$iv[destinationIndex$iv$iv];
                    valueOrder$iv$iv[destinationIndex$iv$iv] = valueIndex$iv$iv;
                    valueOrder$iv$iv[i$iv$iv] = destinationKeyOrder$iv$iv;
                }
                destinationIndex$iv$iv++;
            }
            i$iv$iv++;
            $i$f$removeValueIf = destinationIndex$iv$iv3;
            $i$f$removingScopes = $i$f$removingScopes3;
            identityScopeMap = identityScopeMap2;
            scopeSets = identityArraySetArr2;
            size = i2;
        }
        int size2 = identityScopeMap.getSize();
        for (int i$iv$iv3 = destinationIndex$iv$iv; i$iv$iv3 < size2; i$iv$iv3++) {
            values$iv$iv[valueOrder$iv$iv[i$iv$iv3]] = null;
        }
        identityScopeMap.setSize(destinationIndex$iv$iv);
        if (!(!this.conditionallyInvalidatedScopes.isEmpty())) {
            return;
        }
        Iterator<RecomposeScopeImpl> it = this.conditionallyInvalidatedScopes.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator()");
        while (it.hasNext()) {
            RecomposeScopeImpl scope = it.next();
            if (!scope.isConditional()) {
                it.remove();
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition, androidx.compose.runtime.RecomposeScopeOwner
    public void recordReadOf(Object value) {
        RecomposeScopeImpl it;
        Intrinsics.checkNotNullParameter(value, "value");
        if (!getAreChildrenComposing() && (it = this.composer.getCurrentRecomposeScope$runtime_release()) != null) {
            it.setUsed(true);
            boolean alreadyRead = it.recordRead(value);
            if (!alreadyRead) {
                this.observations.add(value, it);
                if (value instanceof DerivedState) {
                    this.derivedStates.removeScope(value);
                    for (Object dependency : ((DerivedState) value).getCurrentRecord().getDependencies()) {
                        if (dependency != null) {
                            this.derivedStates.add(dependency, value);
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    private final void invalidateScopeOfLocked(Object value) {
        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap = this.observations;
        int index$iv = identityScopeMap.find(value);
        if (index$iv >= 0) {
            IdentityArraySet this_$iv$iv = identityScopeMap.scopeSetAt(index$iv);
            Object[] values$iv$iv = this_$iv$iv.getValues();
            int size = this_$iv$iv.size();
            for (int i$iv$iv = 0; i$iv$iv < size; i$iv$iv++) {
                Object obj = values$iv$iv[i$iv$iv];
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                RecomposeScopeImpl scope = (RecomposeScopeImpl) obj;
                if (scope.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                    this.observationsProcessed.add(value, scope);
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void recordWriteOf(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            invalidateScopeOfLocked(value);
            IdentityScopeMap<DerivedState<?>> identityScopeMap = this.derivedStates;
            int index$iv = identityScopeMap.find(value);
            if (index$iv >= 0) {
                IdentityArraySet this_$iv$iv = identityScopeMap.scopeSetAt(index$iv);
                Object[] values$iv$iv = this_$iv$iv.getValues();
                int size = this_$iv$iv.size();
                for (int i$iv$iv = 0; i$iv$iv < size; i$iv$iv++) {
                    Object obj = values$iv$iv[i$iv$iv];
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                    DerivedState it = (DerivedState) obj;
                    invalidateScopeOfLocked(it);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean recompose() {
        boolean shouldDrain;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            drainPendingModificationsForCompositionLocked();
            try {
                IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> identityArrayMapTakeInvalidations = takeInvalidations();
                try {
                    shouldDrain = this.composer.recompose$runtime_release(identityArrayMapTakeInvalidations);
                    if (!shouldDrain) {
                        drainPendingModificationsLocked();
                    }
                } catch (Exception e$iv) {
                    this.invalidations = identityArrayMapTakeInvalidations;
                    throw e$iv;
                }
            } catch (Throwable e$iv2) {
                if (0 == 0) {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                    } catch (Exception e$iv3) {
                        abandonChanges();
                        throw e$iv3;
                    }
                }
                throw e$iv2;
            }
        }
        return shouldDrain;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void insertMovableContent(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) throws Exception {
        boolean z;
        Intrinsics.checkNotNullParameter(references, "references");
        int index$iv$iv = 0;
        int size = references.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = references.get(index$iv$iv);
                if (!Intrinsics.areEqual(((Pair) item$iv$iv).getFirst().getComposition(), this)) {
                    z = false;
                    break;
                }
                index$iv$iv++;
            } else {
                z = true;
                break;
            }
        }
        ComposerKt.runtimeCheck(z);
        try {
            this.composer.insertMovableContentReferences(references);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable th) {
            if (0 == 0) {
                try {
                    if (true ^ this.abandonSet.isEmpty()) {
                        new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                    }
                } catch (Exception e$iv) {
                    abandonChanges();
                    throw e$iv;
                }
            }
            throw th;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void disposeUnusedMovableContent(MovableContentState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        RememberEventDispatcher manager = new RememberEventDispatcher(this.abandonSet);
        SlotTable slotTable = state.getSlotTable();
        SlotWriter writer$iv = slotTable.openWriter();
        try {
            ComposerKt.removeCurrentGroup(writer$iv, manager);
            Unit unit = Unit.INSTANCE;
            writer$iv.close();
            manager.dispatchRememberObservers();
        } catch (Throwable th) {
            writer$iv.close();
            throw th;
        }
    }

    private final void applyChangesInLocked(List<Function3<Applier<?>, SlotWriter, RememberManager, Unit>> changes) {
        int $i$f$trace;
        int $i$f$trace2;
        RememberEventDispatcher manager = new RememberEventDispatcher(this.abandonSet);
        try {
            if (!changes.isEmpty()) {
                Object token$iv = Trace.INSTANCE.beginSection("Compose:applyChanges");
                try {
                    this.applier.onBeginChanges();
                    SlotTable this_$iv = this.slotTable;
                    SlotWriter writer$iv = this_$iv.openWriter();
                    try {
                        try {
                            Applier<?> applier = this.applier;
                            int index$iv = 0;
                            try {
                                for (int size = changes.size(); index$iv < size; size = size) {
                                    Object item$iv = changes.get(index$iv);
                                    ((Function3) item$iv).invoke(applier, writer$iv, manager);
                                    index$iv++;
                                }
                                changes.clear();
                                Unit unit = Unit.INSTANCE;
                                writer$iv.close();
                                this.applier.onEndChanges();
                                Unit unit2 = Unit.INSTANCE;
                                Trace.INSTANCE.endSection(token$iv);
                                manager.dispatchRememberObservers();
                                manager.dispatchSideEffects();
                                if (this.pendingInvalidScopes) {
                                    String sectionName$iv = "Compose:unobserve";
                                    int size$iv$iv = 0;
                                    Object token$iv2 = Trace.INSTANCE.beginSection(sectionName$iv);
                                    int i = 0;
                                    try {
                                        this.pendingInvalidScopes = false;
                                        IdentityScopeMap<RecomposeScopeImpl> identityScopeMap = this.observations;
                                        int $i$f$removeValueIf = 0;
                                        int[] valueOrder$iv$iv = identityScopeMap.getValueOrder();
                                        IdentityArraySet<RecomposeScopeImpl>[] scopeSets = identityScopeMap.getScopeSets();
                                        Object[] values$iv$iv = identityScopeMap.getValues();
                                        int destinationIndex$iv$iv = 0;
                                        int i$iv$iv = 0;
                                        int size2 = identityScopeMap.getSize();
                                        while (i$iv$iv < size2) {
                                            int valueIndex$iv$iv = valueOrder$iv$iv[i$iv$iv];
                                            IdentityArraySet<RecomposeScopeImpl> identityArraySet = scopeSets[valueIndex$iv$iv];
                                            Intrinsics.checkNotNull(identityArraySet);
                                            Object[] values$iv$iv2 = identityArraySet.getValues();
                                            int size$iv$iv2 = identityArraySet.size();
                                            int i2 = i;
                                            int destinationIndex$iv$iv2 = 0;
                                            String sectionName$iv2 = sectionName$iv;
                                            int i$iv$iv2 = 0;
                                            while (true) {
                                                $i$f$trace = size$iv$iv;
                                                $i$f$trace2 = size$iv$iv2;
                                                if (i$iv$iv2 >= $i$f$trace2) {
                                                    break;
                                                }
                                                IdentityScopeMap<RecomposeScopeImpl> identityScopeMap2 = identityScopeMap;
                                                try {
                                                    Object item$iv$iv = values$iv$iv2[i$iv$iv2];
                                                    int $i$f$removeValueIf2 = $i$f$removeValueIf;
                                                    Intrinsics.checkNotNull(item$iv$iv, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                                    RecomposeScopeImpl scope = (RecomposeScopeImpl) item$iv$iv;
                                                    if (!(!scope.getValid())) {
                                                        if (destinationIndex$iv$iv2 != i$iv$iv2) {
                                                            values$iv$iv2[destinationIndex$iv$iv2] = item$iv$iv;
                                                        }
                                                        destinationIndex$iv$iv2++;
                                                    }
                                                    i$iv$iv2++;
                                                    identityScopeMap = identityScopeMap2;
                                                    $i$f$removeValueIf = $i$f$removeValueIf2;
                                                    size$iv$iv2 = $i$f$trace2;
                                                    size$iv$iv = $i$f$trace;
                                                } catch (Throwable th) {
                                                    th = th;
                                                    Trace.INSTANCE.endSection(token$iv2);
                                                    throw th;
                                                }
                                            }
                                            IdentityScopeMap<RecomposeScopeImpl> identityScopeMap3 = identityScopeMap;
                                            int $i$f$removeValueIf3 = $i$f$removeValueIf;
                                            for (int i$iv$iv3 = destinationIndex$iv$iv2; i$iv$iv3 < $i$f$trace2; i$iv$iv3++) {
                                                values$iv$iv2[i$iv$iv3] = null;
                                            }
                                            ((IdentityArraySet) identityArraySet).size = destinationIndex$iv$iv2;
                                            if (identityArraySet.size() > 0) {
                                                if (destinationIndex$iv$iv != i$iv$iv) {
                                                    int destinationKeyOrder$iv$iv = valueOrder$iv$iv[destinationIndex$iv$iv];
                                                    valueOrder$iv$iv[destinationIndex$iv$iv] = valueIndex$iv$iv;
                                                    valueOrder$iv$iv[i$iv$iv] = destinationKeyOrder$iv$iv;
                                                }
                                                destinationIndex$iv$iv++;
                                            }
                                            i$iv$iv++;
                                            sectionName$iv = sectionName$iv2;
                                            identityScopeMap = identityScopeMap3;
                                            size$iv$iv = $i$f$trace;
                                            i = i2;
                                            $i$f$removeValueIf = $i$f$removeValueIf3;
                                        }
                                        int size3 = identityScopeMap.getSize();
                                        for (int i$iv$iv4 = destinationIndex$iv$iv; i$iv$iv4 < size3; i$iv$iv4++) {
                                            values$iv$iv[valueOrder$iv$iv[i$iv$iv4]] = null;
                                        }
                                        identityScopeMap.setSize(destinationIndex$iv$iv);
                                        cleanUpDerivedStateObservations();
                                        Unit unit3 = Unit.INSTANCE;
                                        Trace.INSTANCE.endSection(token$iv2);
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                }
                                if (this.lateChanges.isEmpty()) {
                                    manager.dispatchAbandons();
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                writer$iv.close();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        Trace.INSTANCE.endSection(token$iv);
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            } else {
                if (!this.lateChanges.isEmpty()) {
                    return;
                }
                manager.dispatchAbandons();
            }
        } catch (Throwable th7) {
            if (this.lateChanges.isEmpty()) {
                manager.dispatchAbandons();
            }
            throw th7;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyChanges() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                applyChangesInLocked(this.changes);
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                if (0 == 0) {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                    } catch (Exception e$iv) {
                        abandonChanges();
                        throw e$iv;
                    }
                }
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyLateChanges() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                if (!this.lateChanges.isEmpty()) {
                    applyChangesInLocked(this.lateChanges);
                }
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                if (0 == 0) {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                    } catch (Exception e$iv) {
                        abandonChanges();
                        throw e$iv;
                    }
                }
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void changesApplied() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                this.composer.changesApplied$runtime_release();
                if (!this.abandonSet.isEmpty()) {
                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                }
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                if (0 == 0) {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                    } catch (Exception e$iv) {
                        abandonChanges();
                        throw e$iv;
                    }
                }
                throw th;
            }
        }
    }

    private final <T> T guardInvalidationsLocked(Function1<? super IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>>, ? extends T> block) throws Exception {
        IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> identityArrayMapTakeInvalidations = takeInvalidations();
        try {
            return block.invoke(identityArrayMapTakeInvalidations);
        } catch (Exception e) {
            this.invalidations = identityArrayMapTakeInvalidations;
            throw e;
        }
    }

    private final <T> T guardChanges(Function0<? extends T> block) throws Exception {
        try {
            try {
                T tInvoke = block.invoke();
                InlineMarker.finallyStart(1);
                return tInvoke;
            } finally {
                InlineMarker.finallyStart(1);
                if (0 == 0 && (!this.abandonSet.isEmpty())) {
                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                }
                InlineMarker.finallyEnd(1);
            }
        } catch (Exception e) {
            abandonChanges();
            throw e;
        }
    }

    private final void abandonChanges() {
        this.pendingModifications.set(null);
        this.changes.clear();
        this.lateChanges.clear();
        this.abandonSet.clear();
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void invalidateAll() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            Object[] $this$forEach$iv = this.slotTable.getSlots();
            for (Object element$iv : $this$forEach$iv) {
                RecomposeScopeImpl recomposeScopeImpl = element$iv instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) element$iv : null;
                if (recomposeScopeImpl != null) {
                    recomposeScopeImpl.invalidate();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void verifyConsistent() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            if (!isComposing()) {
                this.composer.verifyConsistent$runtime_release();
                this.slotTable.verifyWellFormed();
                validateRecomposeScopeAnchors(this.slotTable);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public <R> R delegateInvalidations(ControlledComposition to, int groupIndex, Function0<? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (to != null && !Intrinsics.areEqual(to, this) && groupIndex >= 0) {
            this.invalidationDelegate = (CompositionImpl) to;
            this.invalidationDelegateGroup = groupIndex;
            try {
                return block.invoke();
            } finally {
                this.invalidationDelegate = null;
                this.invalidationDelegateGroup = 0;
            }
        }
        return block.invoke();
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public InvalidationResult invalidate(RecomposeScopeImpl scope, Object instance) {
        CompositionImpl delegate;
        Intrinsics.checkNotNullParameter(scope, "scope");
        if (scope.getDefaultsInScope()) {
            scope.setDefaultsInvalid(true);
        }
        Anchor anchor = scope.getAnchor();
        if (anchor == null || !anchor.getValid()) {
            return InvalidationResult.IGNORED;
        }
        if (!this.slotTable.ownsAnchor(anchor)) {
            Object lock$iv = this.lock;
            synchronized (lock$iv) {
                delegate = this.invalidationDelegate;
            }
            if (delegate != null && delegate.tryImminentInvalidation(scope, instance)) {
                return InvalidationResult.IMMINENT;
            }
            return InvalidationResult.IGNORED;
        }
        if (!scope.getCanRecompose()) {
            return InvalidationResult.IGNORED;
        }
        return invalidateChecked(scope, anchor, instance);
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public void recomposeScopeReleased(RecomposeScopeImpl scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.pendingInvalidScopes = true;
    }

    private final boolean tryImminentInvalidation(RecomposeScopeImpl scope, Object instance) {
        return isComposing() && this.composer.tryImminentInvalidation$runtime_release(scope, instance);
    }

    private final InvalidationResult invalidateChecked(RecomposeScopeImpl scope, Anchor anchor, Object instance) {
        CompositionImpl compositionImpl;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            CompositionImpl changeDelegate = this.invalidationDelegate;
            if (changeDelegate == null) {
                compositionImpl = null;
            } else if (this.slotTable.groupContainsAnchor(this.invalidationDelegateGroup, anchor)) {
                compositionImpl = changeDelegate;
            } else {
                compositionImpl = null;
            }
            CompositionImpl delegate = compositionImpl;
            if (delegate == null) {
                if (tryImminentInvalidation(scope, instance)) {
                    return InvalidationResult.IMMINENT;
                }
                if (instance != null) {
                    CompositionKt.addValue(this.invalidations, scope, instance);
                } else {
                    this.invalidations.set(scope, null);
                }
            }
            if (delegate != null) {
                return delegate.invalidateChecked(scope, anchor, instance);
            }
            this.parent.invalidate$runtime_release(this);
            return isComposing() ? InvalidationResult.DEFERRED : InvalidationResult.SCHEDULED;
        }
    }

    public final void removeObservation$runtime_release(Object instance, RecomposeScopeImpl scope) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.observations.remove(instance, scope);
    }

    public final void removeDerivedStateObservation$runtime_release(DerivedState<?> state) {
        Intrinsics.checkNotNullParameter(state, "state");
        if (!this.observations.contains(state)) {
            this.derivedStates.removeScope(state);
        }
    }

    private final IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> takeInvalidations() {
        IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> identityArrayMap = this.invalidations;
        this.invalidations = new IdentityArrayMap<>(0, 1, null);
        return identityArrayMap;
    }

    private final void validateRecomposeScopeAnchors(SlotTable slotTable) {
        Object[] $this$mapNotNull$iv = slotTable.getSlots();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            RecomposeScopeImpl recomposeScopeImpl = element$iv$iv$iv instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) element$iv$iv$iv : null;
            if (recomposeScopeImpl != null) {
                destination$iv$iv.add(recomposeScopeImpl);
            }
        }
        List scopes = (List) destination$iv$iv;
        int size = scopes.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = scopes.get(index$iv);
            RecomposeScopeImpl scope = (RecomposeScopeImpl) item$iv;
            Anchor anchor = scope.getAnchor();
            if (anchor != null && !slotTable.slotsOf$runtime_release(anchor.toIndexFor(slotTable)).contains(scope)) {
                int dataIndex = ArraysKt.indexOf((RecomposeScopeImpl[]) slotTable.getSlots(), scope);
                throw new IllegalStateException(("Misaligned anchor " + anchor + " in scope " + scope + " encountered, scope found at " + dataIndex).toString());
            }
        }
    }

    private final <T> T trackAbandonedValues(Function0<? extends T> block) {
        try {
            T tInvoke = block.invoke();
            InlineMarker.finallyStart(1);
            return tInvoke;
        } finally {
            InlineMarker.finallyStart(1);
            if (0 == 0 && (!this.abandonSet.isEmpty())) {
                new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
            }
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX INFO: compiled from: Composition.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u0006\u0010\u0010\u001a\u00020\u000eJ\u0006\u0010\u0011\u001a\u00020\u000eJ\u0006\u0010\u0012\u001a\u00020\u000eJ\u0010\u0010\t\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\u0010\u0010\n\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\u0016\u0010\u0013\u001a\u00020\u000e2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/compose/runtime/CompositionImpl$RememberEventDispatcher;", "Landroidx/compose/runtime/RememberManager;", "abandoning", "", "Landroidx/compose/runtime/RememberObserver;", "(Ljava/util/Set;)V", "deactivating", "", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "forgetting", "releasing", "remembering", "sideEffects", "Lkotlin/Function0;", "", "instance", "dispatchAbandons", "dispatchRememberObservers", "dispatchSideEffects", "sideEffect", "effect", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class RememberEventDispatcher implements RememberManager {
        private final Set<RememberObserver> abandoning;
        private List<ComposeNodeLifecycleCallback> deactivating;
        private final List<RememberObserver> forgetting;
        private List<ComposeNodeLifecycleCallback> releasing;
        private final List<RememberObserver> remembering;
        private final List<Function0<Unit>> sideEffects;

        public RememberEventDispatcher(Set<RememberObserver> abandoning) {
            Intrinsics.checkNotNullParameter(abandoning, "abandoning");
            this.abandoning = abandoning;
            this.remembering = new ArrayList();
            this.forgetting = new ArrayList();
            this.sideEffects = new ArrayList();
        }

        @Override // androidx.compose.runtime.RememberManager
        public void remembering(RememberObserver instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            int index = this.forgetting.lastIndexOf(instance);
            if (index >= 0) {
                this.forgetting.remove(index);
                this.abandoning.remove(instance);
            } else {
                this.remembering.add(instance);
            }
        }

        @Override // androidx.compose.runtime.RememberManager
        public void forgetting(RememberObserver instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            int index = this.remembering.lastIndexOf(instance);
            if (index >= 0) {
                this.remembering.remove(index);
                this.abandoning.remove(instance);
            } else {
                this.forgetting.add(instance);
            }
        }

        @Override // androidx.compose.runtime.RememberManager
        public void sideEffect(Function0<Unit> effect) {
            Intrinsics.checkNotNullParameter(effect, "effect");
            this.sideEffects.add(effect);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void deactivating(ComposeNodeLifecycleCallback instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            ArrayList arrayList = this.deactivating;
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.deactivating = arrayList;
            }
            arrayList.add(instance);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void releasing(ComposeNodeLifecycleCallback instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            ArrayList arrayList = this.releasing;
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.releasing = arrayList;
            }
            arrayList.add(instance);
        }

        public final void dispatchRememberObservers() {
            List<ComposeNodeLifecycleCallback> list = this.deactivating;
            List<ComposeNodeLifecycleCallback> list2 = list;
            if (!(list2 == null || list2.isEmpty())) {
                Object token$iv = Trace.INSTANCE.beginSection("Compose:deactivations");
                try {
                    for (int i = list.size() - 1; -1 < i; i--) {
                        list.get(i).onDeactivate();
                    }
                    Unit unit = Unit.INSTANCE;
                    Trace.INSTANCE.endSection(token$iv);
                    list.clear();
                } catch (Throwable th) {
                    Trace.INSTANCE.endSection(token$iv);
                    throw th;
                }
            }
            if (!this.forgetting.isEmpty()) {
                Object token$iv2 = Trace.INSTANCE.beginSection("Compose:onForgotten");
                try {
                    for (int i2 = this.forgetting.size() - 1; -1 < i2; i2--) {
                        RememberObserver instance = this.forgetting.get(i2);
                        if (!this.abandoning.contains(instance)) {
                            instance.onForgotten();
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    Trace.INSTANCE.endSection(token$iv2);
                } catch (Throwable th2) {
                    Trace.INSTANCE.endSection(token$iv2);
                    throw th2;
                }
            }
            if (!this.remembering.isEmpty()) {
                Object token$iv3 = Trace.INSTANCE.beginSection("Compose:onRemembered");
                try {
                    List<RememberObserver> list3 = this.remembering;
                    int size = list3.size();
                    for (int index$iv = 0; index$iv < size; index$iv++) {
                        Object item$iv = list3.get(index$iv);
                        RememberObserver instance2 = (RememberObserver) item$iv;
                        this.abandoning.remove(instance2);
                        instance2.onRemembered();
                    }
                    Unit unit3 = Unit.INSTANCE;
                    Trace.INSTANCE.endSection(token$iv3);
                } catch (Throwable th3) {
                    Trace.INSTANCE.endSection(token$iv3);
                    throw th3;
                }
            }
            List<ComposeNodeLifecycleCallback> list4 = this.releasing;
            List<ComposeNodeLifecycleCallback> list5 = list4;
            if (!(list5 == null || list5.isEmpty())) {
                Object token$iv4 = Trace.INSTANCE.beginSection("Compose:releases");
                try {
                    for (int i3 = list4.size() - 1; -1 < i3; i3--) {
                        list4.get(i3).onRelease();
                    }
                    Unit unit4 = Unit.INSTANCE;
                    Trace.INSTANCE.endSection(token$iv4);
                    list4.clear();
                } catch (Throwable th4) {
                    Trace.INSTANCE.endSection(token$iv4);
                    throw th4;
                }
            }
        }

        public final void dispatchSideEffects() {
            if (!this.sideEffects.isEmpty()) {
                Object token$iv = Trace.INSTANCE.beginSection("Compose:sideeffects");
                try {
                    List<Function0<Unit>> list = this.sideEffects;
                    int size = list.size();
                    for (int index$iv = 0; index$iv < size; index$iv++) {
                        Object item$iv = list.get(index$iv);
                        ((Function0) item$iv).invoke();
                    }
                    this.sideEffects.clear();
                    Unit unit = Unit.INSTANCE;
                } finally {
                    Trace.INSTANCE.endSection(token$iv);
                }
            }
        }

        public final void dispatchAbandons() {
            if (!this.abandoning.isEmpty()) {
                Object token$iv = Trace.INSTANCE.beginSection("Compose:abandons");
                try {
                    Iterator<RememberObserver> it = this.abandoning.iterator();
                    while (it.hasNext()) {
                        RememberObserver instance = it.next();
                        it.remove();
                        instance.onAbandoned();
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                    Trace.INSTANCE.endSection(token$iv);
                }
            }
        }
    }
}
