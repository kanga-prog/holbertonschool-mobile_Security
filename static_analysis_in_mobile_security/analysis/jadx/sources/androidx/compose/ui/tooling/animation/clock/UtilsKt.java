package androidx.compose.ui.tooling.animation.clock;

import androidx.compose.animation.core.Animation;
import androidx.compose.animation.core.AnimationKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.animation.core.RepeatableSpec;
import androidx.compose.animation.core.SnapSpec;
import androidx.compose.animation.core.StartOffset;
import androidx.compose.animation.core.StartOffsetType;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec;
import androidx.compose.animation.tooling.TransitionInfo;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.tooling.animation.states.TargetState;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\u001a\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0000\u001a\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0000\u001a5\u0010\n\u001a\n\u0012\u0004\u0012\u0002H\f\u0018\u00010\u000b\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u0002H\f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0000¢\u0006\u0002\u0010\u0011\u001a&\u0010\u0012\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0013R\u0006\u0012\u0002\b\u00030\u00140\u0001*\u0006\u0012\u0002\b\u00030\u0014H\u0000\u001aH\u0010\u0015\u001a\u00020\u0016\"\u0004\b\u0000\u0010\f\"\b\b\u0001\u0010\u0017*\u00020\u0018*\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u00170\u00192\u0006\u0010\u001a\u001a\u00020\u00022\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\f0\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u0006H\u0000\u001a>\u0010\u0015\u001a\u00020\u0016\"\u0004\b\u0000\u0010\f\"\b\b\u0001\u0010\u0017*\u00020\u0018*\u0012\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u00170\u001eR\u00020\u001f2\b\b\u0002\u0010\u001d\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u0006H\u0000\u001aB\u0010\u0015\u001a\u00020\u0016\"\u0004\b\u0000\u0010\f\"\b\b\u0001\u0010\u0017*\u00020\u0018\"\u0004\b\u0002\u0010!*\u0018\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u00170\u0013R\b\u0012\u0004\u0012\u0002H!0\u00142\b\b\u0002\u0010\u001d\u001a\u00020\u0006H\u0000\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\"²\u0006\u001a\u0010#\u001a\u00020\u0006\"\u0004\b\u0000\u0010\f\"\b\b\u0001\u0010\u0017*\u00020\u0018X\u008a\u0084\u0002²\u0006&\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\f0%\"\u0004\b\u0000\u0010\f\"\b\b\u0001\u0010\u0017*\u00020\u0018X\u008a\u0084\u0002²\u0006&\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\f0%\"\u0004\b\u0000\u0010\f\"\b\b\u0001\u0010\u0017*\u00020\u0018X\u008a\u0084\u0002"}, d2 = {"IGNORE_TRANSITIONS", "", "", "getIGNORE_TRANSITIONS", "()Ljava/util/List;", "millisToNanos", "", "timeMs", "nanosToMillis", "timeNs", "parseParametersToValue", "Landroidx/compose/ui/tooling/animation/states/TargetState;", "T", "currentValue", "par1", "", "par2", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Landroidx/compose/ui/tooling/animation/states/TargetState;", "allAnimations", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "Landroidx/compose/animation/core/Transition;", "createTransitionInfo", "Landroidx/compose/animation/tooling/TransitionInfo;", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/animation/core/Animation;", "label", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "stepMs", "Landroidx/compose/animation/core/InfiniteTransition$TransitionAnimationState;", "Landroidx/compose/animation/core/InfiniteTransition;", "endTimeMs", "S", "ui-tooling_release", "startTimeMs", "values", ""}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UtilsKt {
    private static final List<String> IGNORE_TRANSITIONS = CollectionsKt.listOf("TransformOriginInterruptionHandling");

    public static final List<String> getIGNORE_TRANSITIONS() {
        return IGNORE_TRANSITIONS;
    }

    public static final long nanosToMillis(long timeNs) {
        return (((long) 999999) + timeNs) / ((long) DurationKt.NANOS_IN_MILLIS);
    }

    public static final long millisToNanos(long timeMs) {
        return AnimationKt.MillisToNanos * timeMs;
    }

    public static final List<Transition<?>.TransitionAnimationState<?, ?>> allAnimations(Transition<?> transition) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Iterable $this$flatMap$iv = transition.getTransitions();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            Transition it = (Transition) element$iv$iv;
            Iterable list$iv$iv = allAnimations(it);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        List descendantAnimations = (List) destination$iv$iv;
        return CollectionsKt.plus((Collection) transition.getAnimations(), (Iterable) descendantAnimations);
    }

    public static /* synthetic */ TransitionInfo createTransitionInfo$default(Transition.TransitionAnimationState transitionAnimationState, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 1;
        }
        return createTransitionInfo(transitionAnimationState, j);
    }

    public static final <T, V extends AnimationVector, S> TransitionInfo createTransitionInfo(Transition<S>.TransitionAnimationState<T, V> transitionAnimationState, long stepMs) {
        Intrinsics.checkNotNullParameter(transitionAnimationState, "<this>");
        return createTransitionInfo(transitionAnimationState.getAnimation(), transitionAnimationState.getLabel(), transitionAnimationState.getAnimationSpec(), stepMs);
    }

    public static /* synthetic */ TransitionInfo createTransitionInfo$default(Animation animation, String str, AnimationSpec animationSpec, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = 1;
        }
        return createTransitionInfo(animation, str, animationSpec, j);
    }

    public static final <T, V extends AnimationVector> TransitionInfo createTransitionInfo(final Animation<T, V> animation, String label, final AnimationSpec<T> animationSpec, final long stepMs) {
        Intrinsics.checkNotNullParameter(animation, "<this>");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        final long endTimeMs = nanosToMillis(animation.getDurationNanos());
        final Lazy startTimeMs$delegate = LazyKt.lazy(new Function0<Long>() { // from class: androidx.compose.ui.tooling.animation.clock.UtilsKt$createTransitionInfo$startTimeMs$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Long invoke() {
                Number numberValueOf;
                Object obj = animationSpec;
                if (obj instanceof TweenSpec) {
                    numberValueOf = Integer.valueOf(((TweenSpec) obj).getDelay());
                } else if (obj instanceof SnapSpec) {
                    numberValueOf = Integer.valueOf(((SnapSpec) obj).getDelay());
                } else if (obj instanceof KeyframesSpec) {
                    numberValueOf = Integer.valueOf(((KeyframesSpec) obj).getConfig().getDelayMillis());
                } else if (obj instanceof RepeatableSpec) {
                    if (StartOffsetType.m123equalsimpl0(StartOffset.m116getOffsetTypeEo1U57Q(((RepeatableSpec) obj).getInitialStartOffset()), StartOffsetType.INSTANCE.m127getDelayEo1U57Q())) {
                        numberValueOf = Integer.valueOf(StartOffset.m115getOffsetMillisimpl(((RepeatableSpec) animationSpec).getInitialStartOffset()));
                    } else {
                        numberValueOf = 0L;
                    }
                } else if (obj instanceof InfiniteRepeatableSpec) {
                    if (StartOffsetType.m123equalsimpl0(StartOffset.m116getOffsetTypeEo1U57Q(((InfiniteRepeatableSpec) obj).getInitialStartOffset()), StartOffsetType.INSTANCE.m127getDelayEo1U57Q())) {
                        numberValueOf = Integer.valueOf(StartOffset.m115getOffsetMillisimpl(((InfiniteRepeatableSpec) animationSpec).getInitialStartOffset()));
                    } else {
                        numberValueOf = 0L;
                    }
                } else {
                    numberValueOf = obj instanceof VectorizedDurationBasedAnimationSpec ? Integer.valueOf(((VectorizedDurationBasedAnimationSpec) obj).getDelayMillis()) : 0L;
                }
                return Long.valueOf(numberValueOf.longValue());
            }
        });
        Lazy values$delegate = LazyKt.lazy(new Function0<Map<Long, T>>() { // from class: androidx.compose.ui.tooling.animation.clock.UtilsKt$createTransitionInfo$values$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // kotlin.jvm.functions.Function0
            public final Map<Long, T> invoke() {
                Map values = new LinkedHashMap();
                values.put(Long.valueOf(UtilsKt.createTransitionInfo$lambda$1(startTimeMs$delegate)), animation.getValueFromNanos(UtilsKt.millisToNanos(UtilsKt.createTransitionInfo$lambda$1(startTimeMs$delegate))));
                values.put(Long.valueOf(endTimeMs), animation.getValueFromNanos(UtilsKt.millisToNanos(endTimeMs)));
                long millis = UtilsKt.createTransitionInfo$lambda$1(startTimeMs$delegate);
                while (millis <= endTimeMs) {
                    values.put(Long.valueOf(millis), animation.getValueFromNanos(UtilsKt.millisToNanos(millis)));
                    millis += stepMs;
                }
                return values;
            }
        });
        String name = animationSpec.getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "animationSpec.javaClass.name");
        return new TransitionInfo(label, name, createTransitionInfo$lambda$1(startTimeMs$delegate), endTimeMs, createTransitionInfo$lambda$2(values$delegate));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long createTransitionInfo$lambda$1(Lazy<Long> lazy) {
        return lazy.getValue().longValue();
    }

    private static final <T> Map<Long, T> createTransitionInfo$lambda$2(Lazy<? extends Map<Long, T>> lazy) {
        return lazy.getValue();
    }

    public static /* synthetic */ TransitionInfo createTransitionInfo$default(InfiniteTransition.TransitionAnimationState transitionAnimationState, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 1;
        }
        return createTransitionInfo(transitionAnimationState, j, j2);
    }

    public static final <T, V extends AnimationVector> TransitionInfo createTransitionInfo(final InfiniteTransition.TransitionAnimationState<T, V> transitionAnimationState, final long stepMs, final long endTimeMs) {
        Intrinsics.checkNotNullParameter(transitionAnimationState, "<this>");
        final long startTimeMs = 0;
        Lazy values$delegate = LazyKt.lazy(new Function0<Map<Long, T>>() { // from class: androidx.compose.ui.tooling.animation.clock.UtilsKt$createTransitionInfo$values$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<Long, T> invoke() {
                Map values = new LinkedHashMap();
                values.put(Long.valueOf(startTimeMs), transitionAnimationState.getAnimation().getValueFromNanos(UtilsKt.millisToNanos(startTimeMs)));
                values.put(Long.valueOf(endTimeMs), transitionAnimationState.getAnimation().getValueFromNanos(UtilsKt.millisToNanos(endTimeMs)));
                long millis = startTimeMs;
                while (millis <= endTimeMs) {
                    values.put(Long.valueOf(millis), transitionAnimationState.getAnimation().getValueFromNanos(UtilsKt.millisToNanos(millis)));
                    millis += stepMs;
                }
                return values;
            }
        });
        String label = transitionAnimationState.getLabel();
        String name = transitionAnimationState.getAnimationSpec().getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "animationSpec.javaClass.name");
        return new TransitionInfo(label, name, 0L, endTimeMs, createTransitionInfo$lambda$3(values$delegate));
    }

    private static final <T> Map<Long, T> createTransitionInfo$lambda$3(Lazy<? extends Map<Long, T>> lazy) {
        return lazy.getValue();
    }

    public static final <T> TargetState<T> parseParametersToValue(T t, Object par1, Object obj) {
        TargetState<T> targetState;
        Intrinsics.checkNotNullParameter(par1, "par1");
        if (t == null) {
            return null;
        }
        TargetState parametersToValue$parseDp = parseParametersToValue$parseDp(t, par1, obj);
        if (parametersToValue$parseDp != null) {
            return parametersToValue$parseDp;
        }
        if (!parseParametersToValue$parametersAreValid(par1, obj)) {
            return null;
        }
        Intrinsics.checkNotNull(obj);
        if (parseParametersToValue$parametersHasTheSameType(t, par1, obj)) {
            return new TargetState<>(par1, obj);
        }
        if (!(par1 instanceof List) || !(obj instanceof List)) {
            return null;
        }
        try {
            if (t instanceof IntSize) {
                Object obj2 = ((List) par1).get(0);
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
                int iIntValue = ((Integer) obj2).intValue();
                Object obj3 = ((List) par1).get(1);
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
                IntSize intSizeM5426boximpl = IntSize.m5426boximpl(IntSizeKt.IntSize(iIntValue, ((Integer) obj3).intValue()));
                Object obj4 = ((List) obj).get(0);
                Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.Int");
                int iIntValue2 = ((Integer) obj4).intValue();
                Object obj5 = ((List) obj).get(1);
                Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.Int");
                targetState = new TargetState<>(intSizeM5426boximpl, IntSize.m5426boximpl(IntSizeKt.IntSize(iIntValue2, ((Integer) obj5).intValue())));
            } else if (t instanceof IntOffset) {
                Object obj6 = ((List) par1).get(0);
                Intrinsics.checkNotNull(obj6, "null cannot be cast to non-null type kotlin.Int");
                int iIntValue3 = ((Integer) obj6).intValue();
                Object obj7 = ((List) par1).get(1);
                Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type kotlin.Int");
                IntOffset intOffsetM5383boximpl = IntOffset.m5383boximpl(IntOffsetKt.IntOffset(iIntValue3, ((Integer) obj7).intValue()));
                Object obj8 = ((List) obj).get(0);
                Intrinsics.checkNotNull(obj8, "null cannot be cast to non-null type kotlin.Int");
                int iIntValue4 = ((Integer) obj8).intValue();
                Object obj9 = ((List) obj).get(1);
                Intrinsics.checkNotNull(obj9, "null cannot be cast to non-null type kotlin.Int");
                targetState = new TargetState<>(intOffsetM5383boximpl, IntOffset.m5383boximpl(IntOffsetKt.IntOffset(iIntValue4, ((Integer) obj9).intValue())));
            } else if (t instanceof Size) {
                Object obj10 = ((List) par1).get(0);
                Intrinsics.checkNotNull(obj10, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue = ((Float) obj10).floatValue();
                Object obj11 = ((List) par1).get(1);
                Intrinsics.checkNotNull(obj11, "null cannot be cast to non-null type kotlin.Float");
                Size sizeM2788boximpl = Size.m2788boximpl(SizeKt.Size(fFloatValue, ((Float) obj11).floatValue()));
                Object obj12 = ((List) obj).get(0);
                Intrinsics.checkNotNull(obj12, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue2 = ((Float) obj12).floatValue();
                Object obj13 = ((List) obj).get(1);
                Intrinsics.checkNotNull(obj13, "null cannot be cast to non-null type kotlin.Float");
                targetState = new TargetState<>(sizeM2788boximpl, Size.m2788boximpl(SizeKt.Size(fFloatValue2, ((Float) obj13).floatValue())));
            } else if (t instanceof Offset) {
                Object obj14 = ((List) par1).get(0);
                Intrinsics.checkNotNull(obj14, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue3 = ((Float) obj14).floatValue();
                Object obj15 = ((List) par1).get(1);
                Intrinsics.checkNotNull(obj15, "null cannot be cast to non-null type kotlin.Float");
                Offset offsetM2720boximpl = Offset.m2720boximpl(OffsetKt.Offset(fFloatValue3, ((Float) obj15).floatValue()));
                Object obj16 = ((List) obj).get(0);
                Intrinsics.checkNotNull(obj16, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue4 = ((Float) obj16).floatValue();
                Object obj17 = ((List) obj).get(1);
                Intrinsics.checkNotNull(obj17, "null cannot be cast to non-null type kotlin.Float");
                targetState = new TargetState<>(offsetM2720boximpl, Offset.m2720boximpl(OffsetKt.Offset(fFloatValue4, ((Float) obj17).floatValue())));
            } else if (t instanceof Rect) {
                Object obj18 = ((List) par1).get(0);
                Intrinsics.checkNotNull(obj18, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue5 = ((Float) obj18).floatValue();
                Object obj19 = ((List) par1).get(1);
                Intrinsics.checkNotNull(obj19, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue6 = ((Float) obj19).floatValue();
                Object obj20 = ((List) par1).get(2);
                Intrinsics.checkNotNull(obj20, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue7 = ((Float) obj20).floatValue();
                Object obj21 = ((List) par1).get(3);
                Intrinsics.checkNotNull(obj21, "null cannot be cast to non-null type kotlin.Float");
                Rect rect = new Rect(fFloatValue5, fFloatValue6, fFloatValue7, ((Float) obj21).floatValue());
                Object obj22 = ((List) obj).get(0);
                Intrinsics.checkNotNull(obj22, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue8 = ((Float) obj22).floatValue();
                Object obj23 = ((List) obj).get(1);
                Intrinsics.checkNotNull(obj23, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue9 = ((Float) obj23).floatValue();
                Object obj24 = ((List) obj).get(2);
                Intrinsics.checkNotNull(obj24, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue10 = ((Float) obj24).floatValue();
                Object obj25 = ((List) obj).get(3);
                Intrinsics.checkNotNull(obj25, "null cannot be cast to non-null type kotlin.Float");
                targetState = new TargetState<>(rect, new Rect(fFloatValue8, fFloatValue9, fFloatValue10, ((Float) obj25).floatValue()));
            } else if (t instanceof Color) {
                Object obj26 = ((List) par1).get(0);
                Intrinsics.checkNotNull(obj26, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue11 = ((Float) obj26).floatValue();
                Object obj27 = ((List) par1).get(1);
                Intrinsics.checkNotNull(obj27, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue12 = ((Float) obj27).floatValue();
                Object obj28 = ((List) par1).get(2);
                Intrinsics.checkNotNull(obj28, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue13 = ((Float) obj28).floatValue();
                Object obj29 = ((List) par1).get(3);
                Intrinsics.checkNotNull(obj29, "null cannot be cast to non-null type kotlin.Float");
                Color colorM2961boximpl = Color.m2961boximpl(ColorKt.Color$default(fFloatValue11, fFloatValue12, fFloatValue13, ((Float) obj29).floatValue(), null, 16, null));
                Object obj30 = ((List) obj).get(0);
                Intrinsics.checkNotNull(obj30, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue14 = ((Float) obj30).floatValue();
                Object obj31 = ((List) obj).get(1);
                Intrinsics.checkNotNull(obj31, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue15 = ((Float) obj31).floatValue();
                Object obj32 = ((List) obj).get(2);
                Intrinsics.checkNotNull(obj32, "null cannot be cast to non-null type kotlin.Float");
                float fFloatValue16 = ((Float) obj32).floatValue();
                Object obj33 = ((List) obj).get(3);
                Intrinsics.checkNotNull(obj33, "null cannot be cast to non-null type kotlin.Float");
                targetState = new TargetState<>(colorM2961boximpl, Color.m2961boximpl(ColorKt.Color$default(fFloatValue14, fFloatValue15, fFloatValue16, ((Float) obj33).floatValue(), null, 16, null)));
            } else if (t instanceof Dp) {
                Object obj34 = ((List) par1).get(0);
                Intrinsics.checkNotNull(obj34);
                Object obj35 = ((List) obj).get(0);
                Intrinsics.checkNotNull(obj35);
                targetState = (TargetState<T>) parseParametersToValue$parseDp(t, obj34, obj35);
            } else {
                if (parseParametersToValue$parametersAreValid(((List) par1).get(0), ((List) obj).get(0))) {
                    Object obj36 = ((List) par1).get(0);
                    Intrinsics.checkNotNull(obj36);
                    Object obj37 = ((List) obj).get(0);
                    Intrinsics.checkNotNull(obj37);
                    if (parseParametersToValue$parametersHasTheSameType(t, obj36, obj37)) {
                        targetState = new TargetState<>(((List) par1).get(0), ((List) obj).get(0));
                    }
                }
                return null;
            }
            Intrinsics.checkNotNull(targetState, "null cannot be cast to non-null type androidx.compose.ui.tooling.animation.states.TargetState<T of androidx.compose.ui.tooling.animation.clock.UtilsKt.parseParametersToValue>");
            return targetState;
        } catch (ClassCastException e) {
            return null;
        } catch (IllegalArgumentException e2) {
            return null;
        } catch (IndexOutOfBoundsException e3) {
            return null;
        } catch (NullPointerException e4) {
            return null;
        }
    }

    private static final boolean parseParametersToValue$parametersAreValid(Object par1, Object par2) {
        return (par1 == null || par2 == null || par1.getClass() != par2.getClass()) ? false : true;
    }

    private static final boolean parseParametersToValue$parametersHasTheSameType(Object value, Object par1, Object par2) {
        return value.getClass() == par1.getClass() && value.getClass() == par2.getClass();
    }

    private static final Dp parseParametersToValue$getDp(Object par) {
        Dp dpM5272boximpl = par instanceof Dp ? (Dp) par : null;
        if (dpM5272boximpl == null) {
            Float f = par instanceof Float ? (Float) par : null;
            if (f != null) {
                float $this$dp$iv = f.floatValue();
                dpM5272boximpl = Dp.m5272boximpl(Dp.m5274constructorimpl($this$dp$iv));
            } else {
                dpM5272boximpl = null;
            }
            if (dpM5272boximpl == null) {
                Double d = par instanceof Double ? (Double) par : null;
                if (d != null) {
                    double $this$dp$iv2 = d.doubleValue();
                    dpM5272boximpl = Dp.m5272boximpl(Dp.m5274constructorimpl((float) $this$dp$iv2));
                } else {
                    dpM5272boximpl = null;
                }
                if (dpM5272boximpl == null) {
                    Integer num = par instanceof Integer ? (Integer) par : null;
                    if (num == null) {
                        return null;
                    }
                    int $this$dp$iv3 = num.intValue();
                    return Dp.m5272boximpl(Dp.m5274constructorimpl($this$dp$iv3));
                }
            }
        }
        return dpM5272boximpl;
    }

    private static final <T> TargetState<Dp> parseParametersToValue$parseDp(T t, Object par1, Object par2) {
        if (!(t instanceof Dp) || par2 == null) {
            return null;
        }
        if ((par1 instanceof Dp) && (par2 instanceof Dp)) {
            return new TargetState<>(par1, par2);
        }
        Dp dp1 = parseParametersToValue$getDp(par1);
        Dp dp2 = parseParametersToValue$getDp(par2);
        if (dp1 == null || dp2 == null) {
            return null;
        }
        return new TargetState<>(dp1, dp2);
    }
}
