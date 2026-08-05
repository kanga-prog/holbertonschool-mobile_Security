package androidx.compose.ui.tooling.animation;

import androidx.compose.animation.core.Transition;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: TransitionComposeAnimation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u0002H\u0000¨\u0006\u0003"}, d2 = {"parse", "Landroidx/compose/ui/tooling/animation/TransitionComposeAnimation;", "Landroidx/compose/animation/core/Transition;", "ui-tooling_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TransitionComposeAnimationKt {
    /* JADX WARN: Code duplicated, block: B:8:0x0021  */
    public static final TransitionComposeAnimation<?> parse(Transition<?> transition) {
        Set states;
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Object state = transition.getCurrentState();
        if (state == null) {
            return null;
        }
        Object[] enumConstants = state.getClass().getEnumConstants();
        if (enumConstants != null) {
            Intrinsics.checkNotNullExpressionValue(enumConstants, "enumConstants");
            states = ArraysKt.toSet(enumConstants);
            if (states == null) {
                states = SetsKt.setOf(state);
            }
        } else {
            states = SetsKt.setOf(state);
        }
        String label = transition.getLabel();
        if (label == null) {
            label = Reflection.getOrCreateKotlinClass(state.getClass()).getSimpleName();
        }
        return new TransitionComposeAnimation<>(transition, states, label);
    }
}
