package androidx.compose.material;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Switch.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001aS\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0002\u0010\"\u001a?\u0010#\u001a\u00020\u0016*\u00020$2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020&2\u0006\u0010\u001e\u001a\u00020'H\u0003¢\u0006\u0002\u0010(\u001a1\u0010)\u001a\u00020\u0016*\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u0002H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u00100\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0013\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u000e\u0010\u0007\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0013\u0010\b\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0013\u0010\t\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0013\u0010\n\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0019\u0010\u000b\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\f\u0010\r\"\u0013\u0010\u000e\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0013\u0010\u000f\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0013\u0010\u0010\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0019\u0010\u0011\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0012\u0010\r\"\u0019\u0010\u0013\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0014\u0010\r\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00061²\u0006\n\u00102\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\u0018\u00103\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u001aX\u008a\u0084\u0002²\u0006\n\u00104\u001a\u00020\u0018X\u008a\u0084\u0002²\u0006\n\u0010+\u001a\u00020,X\u008a\u0084\u0002²\u0006\n\u00105\u001a\u00020,X\u008a\u0084\u0002²\u0006\n\u00106\u001a\u00020,X\u008a\u0084\u0002"}, d2 = {"AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "DefaultSwitchPadding", "Landroidx/compose/ui/unit/Dp;", "F", "SwitchHeight", "SwitchPositionalThreshold", "SwitchVelocityThreshold", "SwitchWidth", "ThumbDefaultElevation", "ThumbDiameter", "getThumbDiameter", "()F", "ThumbPathLength", "ThumbPressedElevation", "ThumbRippleRadius", "TrackStrokeWidth", "getTrackStrokeWidth", "TrackWidth", "getTrackWidth", "Switch", "", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "colors", "Landroidx/compose/material/SwitchColors;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SwitchColors;Landroidx/compose/runtime/Composer;II)V", "SwitchImpl", "Landroidx/compose/foundation/layout/BoxScope;", "thumbValue", "Lkotlin/Function0;", "Landroidx/compose/foundation/interaction/InteractionSource;", "(Landroidx/compose/foundation/layout/BoxScope;ZZLandroidx/compose/material/SwitchColors;Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)V", "drawTrack", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "trackColor", "Landroidx/compose/ui/graphics/Color;", "trackWidth", "strokeWidth", "drawTrack-RPmYEkk", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFF)V", "material_release", "forceAnimationCheck", "currentOnCheckedChange", "currentChecked", "thumbColor", "resolvedThumbColor"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwitchKt {
    private static final TweenSpec<Float> AnimationSpec;
    private static final float DefaultSwitchPadding;
    private static final float SwitchHeight;
    private static final float SwitchPositionalThreshold = 0.7f;
    private static final float SwitchVelocityThreshold;
    private static final float SwitchWidth;
    private static final float ThumbDefaultElevation;
    private static final float ThumbDiameter;
    private static final float ThumbPathLength;
    private static final float ThumbPressedElevation;
    private static final float ThumbRippleRadius;
    private static final float TrackStrokeWidth;
    private static final float TrackWidth;

    /* JADX WARN: Code duplicated, block: B:115:0x02c1  */
    /* JADX WARN: Code duplicated, block: B:118:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:119:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:123:0x0311 A[LOOP:0: B:122:0x030f->B:123:0x0311, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:126:0x032c  */
    /* JADX WARN: Code duplicated, block: B:130:0x0339 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:134:0x039a  */
    /* JADX WARN: Code duplicated, block: B:138:0x03aa A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:142:0x03e7  */
    /* JADX WARN: Code duplicated, block: B:143:0x03ea  */
    /* JADX WARN: Code duplicated, block: B:145:0x03ee  */
    /* JADX WARN: Code duplicated, block: B:146:0x040f  */
    /* JADX WARN: Code duplicated, block: B:149:0x0419  */
    /* JADX WARN: Code duplicated, block: B:150:0x0422  */
    /* JADX WARN: Code duplicated, block: B:153:0x0433 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:155:0x0438  */
    /* JADX WARN: Code duplicated, block: B:158:0x04c2  */
    /* JADX WARN: Code duplicated, block: B:161:0x04ce  */
    /* JADX WARN: Code duplicated, block: B:162:0x04d4  */
    /* JADX WARN: Code duplicated, block: B:165:0x0507  */
    /* JADX WARN: Code duplicated, block: B:169:0x051d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:173:0x05ad  */
    /* JADX WARN: Code duplicated, block: B:177:0x05ba A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:181:0x0614  */
    public static final void Switch(final boolean checked, final Function1<? super Boolean, Unit> function1, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, SwitchColors colors, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        MutableInteractionSource mutableInteractionSource;
        SwitchColors switchColors;
        MutableInteractionSource interactionSource2;
        SwitchColors colors2;
        MutableInteractionSource interactionSource3;
        int $dirty;
        Modifier modifier3;
        boolean enabled2;
        Object value$iv$iv;
        Object value$iv$iv2;
        Object value$iv$iv3;
        boolean invalid$iv$iv;
        Object value$iv$iv4;
        Object[] keys$iv;
        int $changed$iv;
        int length;
        boolean invalid$iv;
        int i2;
        Object value$iv$iv5;
        boolean invalid$iv$iv2;
        SwitchKt$Switch$4$1 value$iv$iv6;
        Object objConsume;
        boolean isRtl;
        Modifier.Companion toggleableModifier;
        Modifier.Companion companionMinimumInteractiveComponentSize;
        boolean z2;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        boolean invalid$iv$iv3;
        Object value$iv$iv7;
        Modifier modifier4;
        Object key3$iv;
        Composer $composer2 = $composer.startRestartGroup(25866825);
        ComposerKt.sourceInformation($composer2, "C(Switch)P(!1,5,4,2,3)99@4619L39,100@4702L8,*103@4774L7,108@5156L34,109@5245L7,110@5322L314,118@5671L37,119@5735L29,120@5780L96,120@5769L107,123@5920L315,123@5881L354,132@6285L133,132@6240L178,137@6456L7,152@6897L1004:Switch.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(checked) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 896) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            z = enabled;
        } else if (($changed & 7168) == 0) {
            z = enabled;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            mutableInteractionSource = interactionSource;
        } else if ((57344 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer2.changed(mutableInteractionSource) ? 16384 : 8192;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                switchColors = colors;
                int i6 = $composer2.changed(switchColors) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                switchColors = colors;
            }
            $dirty2 |= i6;
        } else {
            switchColors = colors;
        }
        if ((374491 & $dirty2) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            enabled2 = z;
            interactionSource3 = mutableInteractionSource;
            colors2 = switchColors;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled3 = i4 != 0 ? true : z;
                if (i5 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    Object it$iv$iv = $composer2.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    interactionSource2 = mutableInteractionSource;
                }
                if ((i & 32) != 0) {
                    interactionSource3 = interactionSource2;
                    $dirty = $dirty2 & (-458753);
                    modifier3 = modifier5;
                    enabled2 = enabled3;
                    colors2 = SwitchDefaults.INSTANCE.m1222colorsSQMK_m0(0L, 0L, 0.0f, 0L, 0L, 0.0f, 0L, 0L, 0L, 0L, $composer2, 0, 6, 1023);
                } else {
                    colors2 = colors;
                    interactionSource3 = interactionSource2;
                    $dirty = $dirty2;
                    modifier3 = modifier5;
                    enabled2 = enabled3;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                modifier3 = modifier2;
                enabled2 = z;
                interactionSource3 = mutableInteractionSource;
                colors2 = switchColors;
                $dirty = $dirty2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(25866825, $dirty, -1, "androidx.compose.material.Switch (Switch.kt:94)");
            }
            final float minBound = 0.0f;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$Switch_u24lambda_u241 = (Density) objConsume2;
            final float maxBound = $this$Switch_u24lambda_u241.mo327toPx0680j_4(ThumbPathLength);
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            MutableState forceAnimationCheck$delegate = (MutableState) value$iv$iv2;
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$Switch_u24lambda_u245 = (Density) objConsume3;
            final float switchVelocityThresholdPx = $this$Switch_u24lambda_u245.mo327toPx0680j_4(SwitchVelocityThreshold);
            Object key1$iv = Float.valueOf(switchVelocityThresholdPx);
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv4 = $composer2.changed(key1$iv);
            Object it$iv$iv3 = $composer2.rememberedValue();
            if (!invalid$iv$iv4) {
                Object key1$iv2 = Composer.INSTANCE.getEmpty();
                if (it$iv$iv3 != key1$iv2) {
                    value$iv$iv3 = it$iv$iv3;
                }
                $composer2.endReplaceableGroup();
                final AnchoredDraggableState anchoredDraggableState = (AnchoredDraggableState) value$iv$iv3;
                State currentOnCheckedChange$delegate = SnapshotStateKt.rememberUpdatedState(function1, $composer2, ($dirty >> 3) & 14);
                State currentChecked$delegate = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(checked), $composer2, $dirty & 14);
                Object key2$iv = Float.valueOf(0.0f);
                Object key3$iv2 = Float.valueOf(maxBound);
                $composer2.startReplaceableGroup(1618982084);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(anchoredDraggableState) | $composer2.changed(key2$iv) | $composer2.changed(key3$iv2);
                value$iv$iv4 = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                    key3$iv = Composer.INSTANCE.getEmpty();
                    if (value$iv$iv4 == key3$iv) {
                    }
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv4, $composer2, 0);
                    keys$iv = new Object[]{anchoredDraggableState, currentChecked$delegate, currentOnCheckedChange$delegate, forceAnimationCheck$delegate};
                    $changed$iv = 8;
                    $composer2.startReplaceableGroup(-568225417);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                    length = keys$iv.length;
                    invalid$iv = false;
                    i2 = 0;
                    while (i2 < length) {
                        int $changed$iv2 = $changed$iv;
                        Object key$iv = keys$iv[i2];
                        invalid$iv |= $composer2.changed(key$iv);
                        i2++;
                        $changed$iv = $changed$iv2;
                    }
                    Object it$iv$iv4 = $composer2.rememberedValue();
                    if (!invalid$iv || it$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv5 = new SwitchKt$Switch$3$1(anchoredDraggableState, currentChecked$delegate, currentOnCheckedChange$delegate, forceAnimationCheck$delegate, null);
                        $composer2.updateRememberedValue(value$iv$iv5);
                    } else {
                        value$iv$iv5 = it$iv$iv4;
                    }
                    $composer2.endReplaceableGroup();
                    EffectsKt.LaunchedEffect(anchoredDraggableState, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv5, $composer2, 64);
                    Boolean boolValueOf = Boolean.valueOf(checked);
                    Boolean boolValueOf2 = Boolean.valueOf(Switch$lambda$3(forceAnimationCheck$delegate));
                    Object key1$iv3 = Boolean.valueOf(checked);
                    int i7 = $dirty & 14;
                    $composer2.startReplaceableGroup(511388516);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                    invalid$iv$iv2 = $composer2.changed(key1$iv3) | $composer2.changed(anchoredDraggableState);
                    value$iv$iv6 = $composer2.rememberedValue();
                    if (!invalid$iv$iv2 || value$iv$iv6 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv6 = new SwitchKt$Switch$4$1(checked, anchoredDraggableState, null);
                        $composer2.updateRememberedValue(value$iv$iv6);
                    }
                    $composer2.endReplaceableGroup();
                    EffectsKt.LaunchedEffect(boolValueOf, boolValueOf2, (Function2) value$iv$iv6, $composer2, ($dirty & 14) | 512);
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    objConsume = $composer2.consume(localLayoutDirection);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (objConsume == LayoutDirection.Rtl) {
                        isRtl = true;
                    } else {
                        isRtl = false;
                    }
                    if (function1 != null) {
                        toggleableModifier = ToggleableKt.m723toggleableO2vRcR0(Modifier.INSTANCE, checked, interactionSource3, null, enabled2, Role.m4603boximpl(Role.INSTANCE.m4615getSwitcho7Vup1c()), function1);
                    } else {
                        toggleableModifier = Modifier.INSTANCE;
                    }
                    if (function1 != null) {
                        companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                    } else {
                        companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                    }
                    Modifier modifierThen = modifier3.then(companionMinimumInteractiveComponentSize).then(toggleableModifier);
                    Orientation orientation = Orientation.Horizontal;
                    if (enabled2 || function1 == null) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    Modifier modifier$iv = SizeKt.m528requiredSizeVpY3zN4(PaddingKt.m487padding3ABfNKs(SizeKt.wrapContentSize$default(AnchoredDraggableKt.anchoredDraggable(modifierThen, anchoredDraggableState, orientation, z2, isRtl, interactionSource3), Alignment.INSTANCE.getCenter(), false, 2, null), DefaultSwitchPadding), SwitchWidth, SwitchHeight);
                    $composer2.startReplaceableGroup(733328855);
                    ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv = (0 << 3) & 112;
                    $composer2.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
                    int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        function0 = constructor;
                        $composer2.createNode(function0);
                    } else {
                        function0 = constructor;
                        $composer2.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer2);
                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                    $composer2.startReplaceableGroup(2058660585);
                    int i8 = ($changed$iv$iv$iv >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                    int $changed2 = ((0 >> 6) & 112) | 6;
                    BoxScope $this$Switch_u24lambda_u2413 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1229337700, "C177@7792L42,173@7637L258:Switch.kt#jmzs0o");
                    boolean zBooleanValue = ((Boolean) anchoredDraggableState.getTargetValue()).booleanValue();
                    $composer2.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                    invalid$iv$iv3 = $composer2.changed(anchoredDraggableState);
                    Object it$iv$iv5 = $composer2.rememberedValue();
                    if (!invalid$iv$iv3 || it$iv$iv5 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv7 = (Function0) new Function0<Float>() { // from class: androidx.compose.material.SwitchKt$Switch$5$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Float invoke() {
                                return Float.valueOf(anchoredDraggableState.requireOffset());
                            }
                        };
                        $composer2.updateRememberedValue(value$iv$iv7);
                    } else {
                        value$iv$iv7 = it$iv$iv5;
                    }
                    $composer2.endReplaceableGroup();
                    SwitchImpl($this$Switch_u24lambda_u2413, zBooleanValue, enabled2, colors2, (Function0) value$iv$iv7, interactionSource3, $composer2, ($changed2 & 14) | (($dirty >> 3) & 896) | (($dirty >> 6) & 7168) | (($dirty << 3) & 458752));
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endReplaceableGroup();
                    $composer2.endNode();
                    $composer2.endReplaceableGroup();
                    $composer2.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier4 = modifier3;
                }
                value$iv$iv4 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.SwitchKt$Switch$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        AnchoredDraggableState.updateAnchors$material_release$default(anchoredDraggableState, MapsKt.mapOf(TuplesKt.to(false, Float.valueOf(minBound)), TuplesKt.to(true, Float.valueOf(maxBound))), null, 2, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv4);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv4, $composer2, 0);
                keys$iv = new Object[]{anchoredDraggableState, currentChecked$delegate, currentOnCheckedChange$delegate, forceAnimationCheck$delegate};
                $changed$iv = 8;
                $composer2.startReplaceableGroup(-568225417);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                length = keys$iv.length;
                invalid$iv = false;
                i2 = 0;
                while (i2 < length) {
                    int $changed$iv3 = $changed$iv;
                    Object key$iv2 = keys$iv[i2];
                    invalid$iv |= $composer2.changed(key$iv2);
                    i2++;
                    $changed$iv = $changed$iv3;
                }
                Object it$iv$iv6 = $composer2.rememberedValue();
                if (invalid$iv) {
                }
                value$iv$iv5 = new SwitchKt$Switch$3$1(anchoredDraggableState, currentChecked$delegate, currentOnCheckedChange$delegate, forceAnimationCheck$delegate, null);
                $composer2.updateRememberedValue(value$iv$iv5);
                $composer2.endReplaceableGroup();
                EffectsKt.LaunchedEffect(anchoredDraggableState, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv5, $composer2, 64);
                Boolean boolValueOf3 = Boolean.valueOf(checked);
                Boolean boolValueOf4 = Boolean.valueOf(Switch$lambda$3(forceAnimationCheck$delegate));
                Object key1$iv4 = Boolean.valueOf(checked);
                int i9 = $dirty & 14;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv2 = $composer2.changed(key1$iv4) | $composer2.changed(anchoredDraggableState);
                value$iv$iv6 = $composer2.rememberedValue();
                if (!invalid$iv$iv2) {
                }
                value$iv$iv6 = new SwitchKt$Switch$4$1(checked, anchoredDraggableState, null);
                $composer2.updateRememberedValue(value$iv$iv6);
                $composer2.endReplaceableGroup();
                EffectsKt.LaunchedEffect(boolValueOf3, boolValueOf4, (Function2) value$iv$iv6, $composer2, ($dirty & 14) | 512);
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                objConsume = $composer2.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (objConsume == LayoutDirection.Rtl) {
                    isRtl = true;
                } else {
                    isRtl = false;
                }
                if (function1 != null) {
                    toggleableModifier = ToggleableKt.m723toggleableO2vRcR0(Modifier.INSTANCE, checked, interactionSource3, null, enabled2, Role.m4603boximpl(Role.INSTANCE.m4615getSwitcho7Vup1c()), function1);
                } else {
                    toggleableModifier = Modifier.INSTANCE;
                }
                if (function1 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                Modifier modifierThen2 = modifier3.then(companionMinimumInteractiveComponentSize).then(toggleableModifier);
                Orientation orientation2 = Orientation.Horizontal;
                if (enabled2) {
                    z2 = false;
                } else {
                    z2 = false;
                }
                Modifier modifier$iv2 = SizeKt.m528requiredSizeVpY3zN4(PaddingKt.m487padding3ABfNKs(SizeKt.wrapContentSize$default(AnchoredDraggableKt.anchoredDraggable(modifierThen2, anchoredDraggableState, orientation2, z2, isRtl, interactionSource3), Alignment.INSTANCE.getCenter(), false, 2, null), DefaultSwitchPadding), SwitchWidth, SwitchHeight);
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv2 = (0 << 3) & 112;
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function0 = constructor;
                    $composer2.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer2);
                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i10 = ($changed$iv$iv$iv2 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                int $changed3 = ((0 >> 6) & 112) | 6;
                BoxScope $this$Switch_u24lambda_u2414 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -1229337700, "C177@7792L42,173@7637L258:Switch.kt#jmzs0o");
                boolean zBooleanValue2 = ((Boolean) anchoredDraggableState.getTargetValue()).booleanValue();
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv3 = $composer2.changed(anchoredDraggableState);
                Object it$iv$iv7 = $composer2.rememberedValue();
                if (invalid$iv$iv3) {
                }
                value$iv$iv7 = (Function0) new Function0<Float>() { // from class: androidx.compose.material.SwitchKt$Switch$5$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(anchoredDraggableState.requireOffset());
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv7);
                $composer2.endReplaceableGroup();
                SwitchImpl($this$Switch_u24lambda_u2414, zBooleanValue2, enabled2, colors2, (Function0) value$iv$iv7, interactionSource3, $composer2, ($changed3 & 14) | (($dirty >> 3) & 896) | (($dirty >> 6) & 7168) | (($dirty << 3) & 458752));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
            }
            value$iv$iv3 = new AnchoredDraggableState(Boolean.valueOf(checked), new Function1<Float, Float>() { // from class: androidx.compose.material.SwitchKt$Switch$anchoredDraggableState$1$1
                public final Float invoke(float distance) {
                    return Float.valueOf(0.7f * distance);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Float invoke(Float f) {
                    return invoke(f.floatValue());
                }
            }, new Function0<Float>() { // from class: androidx.compose.material.SwitchKt$Switch$anchoredDraggableState$1$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return Float.valueOf(switchVelocityThresholdPx);
                }
            }, AnimationSpec, null, 16, null);
            $composer2.updateRememberedValue(value$iv$iv3);
            $composer2.endReplaceableGroup();
            final AnchoredDraggableState<Boolean> anchoredDraggableState2 = (AnchoredDraggableState) value$iv$iv3;
            State currentOnCheckedChange$delegate2 = SnapshotStateKt.rememberUpdatedState(function1, $composer2, ($dirty >> 3) & 14);
            State currentChecked$delegate2 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(checked), $composer2, $dirty & 14);
            Object key2$iv2 = Float.valueOf(0.0f);
            Object key3$iv3 = Float.valueOf(maxBound);
            $composer2.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(anchoredDraggableState2) | $composer2.changed(key2$iv2) | $composer2.changed(key3$iv3);
            value$iv$iv4 = $composer2.rememberedValue();
            if (!invalid$iv$iv) {
                key3$iv = Composer.INSTANCE.getEmpty();
                if (value$iv$iv4 == key3$iv) {
                }
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv4, $composer2, 0);
                keys$iv = new Object[]{anchoredDraggableState2, currentChecked$delegate2, currentOnCheckedChange$delegate2, forceAnimationCheck$delegate};
                $changed$iv = 8;
                $composer2.startReplaceableGroup(-568225417);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                length = keys$iv.length;
                invalid$iv = false;
                i2 = 0;
                while (i2 < length) {
                    int $changed$iv4 = $changed$iv;
                    Object key$iv3 = keys$iv[i2];
                    invalid$iv |= $composer2.changed(key$iv3);
                    i2++;
                    $changed$iv = $changed$iv4;
                }
                Object it$iv$iv8 = $composer2.rememberedValue();
                if (invalid$iv) {
                }
                value$iv$iv5 = new SwitchKt$Switch$3$1(anchoredDraggableState2, currentChecked$delegate2, currentOnCheckedChange$delegate2, forceAnimationCheck$delegate, null);
                $composer2.updateRememberedValue(value$iv$iv5);
                $composer2.endReplaceableGroup();
                EffectsKt.LaunchedEffect(anchoredDraggableState2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv5, $composer2, 64);
                Boolean boolValueOf5 = Boolean.valueOf(checked);
                Boolean boolValueOf6 = Boolean.valueOf(Switch$lambda$3(forceAnimationCheck$delegate));
                Object key1$iv5 = Boolean.valueOf(checked);
                int i11 = $dirty & 14;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv2 = $composer2.changed(key1$iv5) | $composer2.changed(anchoredDraggableState2);
                value$iv$iv6 = $composer2.rememberedValue();
                if (!invalid$iv$iv2) {
                }
                value$iv$iv6 = new SwitchKt$Switch$4$1(checked, anchoredDraggableState2, null);
                $composer2.updateRememberedValue(value$iv$iv6);
                $composer2.endReplaceableGroup();
                EffectsKt.LaunchedEffect(boolValueOf5, boolValueOf6, (Function2) value$iv$iv6, $composer2, ($dirty & 14) | 512);
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                objConsume = $composer2.consume(localLayoutDirection3);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (objConsume == LayoutDirection.Rtl) {
                    isRtl = true;
                } else {
                    isRtl = false;
                }
                if (function1 != null) {
                    toggleableModifier = ToggleableKt.m723toggleableO2vRcR0(Modifier.INSTANCE, checked, interactionSource3, null, enabled2, Role.m4603boximpl(Role.INSTANCE.m4615getSwitcho7Vup1c()), function1);
                } else {
                    toggleableModifier = Modifier.INSTANCE;
                }
                if (function1 != null) {
                    companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
                } else {
                    companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
                }
                Modifier modifierThen3 = modifier3.then(companionMinimumInteractiveComponentSize).then(toggleableModifier);
                Orientation orientation3 = Orientation.Horizontal;
                if (enabled2) {
                    z2 = false;
                } else {
                    z2 = false;
                }
                Modifier modifier$iv3 = SizeKt.m528requiredSizeVpY3zN4(PaddingKt.m487padding3ABfNKs(SizeKt.wrapContentSize$default(AnchoredDraggableKt.anchoredDraggable(modifierThen3, anchoredDraggableState2, orientation3, z2, isRtl, interactionSource3), Alignment.INSTANCE.getCenter(), false, 2, null), DefaultSwitchPadding), SwitchWidth, SwitchHeight);
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv3 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv3, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv3 = (0 << 3) & 112;
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifier$iv3);
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function0 = constructor;
                    $composer2.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer2);
                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
                function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i12 = ($changed$iv$iv$iv3 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                int $changed4 = ((0 >> 6) & 112) | 6;
                BoxScope $this$Switch_u24lambda_u2415 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -1229337700, "C177@7792L42,173@7637L258:Switch.kt#jmzs0o");
                boolean zBooleanValue3 = ((Boolean) anchoredDraggableState2.getTargetValue()).booleanValue();
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv3 = $composer2.changed(anchoredDraggableState2);
                Object it$iv$iv9 = $composer2.rememberedValue();
                if (invalid$iv$iv3) {
                }
                value$iv$iv7 = (Function0) new Function0<Float>() { // from class: androidx.compose.material.SwitchKt$Switch$5$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(anchoredDraggableState2.requireOffset());
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv7);
                $composer2.endReplaceableGroup();
                SwitchImpl($this$Switch_u24lambda_u2415, zBooleanValue3, enabled2, colors2, (Function0) value$iv$iv7, interactionSource3, $composer2, ($changed4 & 14) | (($dirty >> 3) & 896) | (($dirty >> 6) & 7168) | (($dirty << 3) & 458752));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
            }
            value$iv$iv4 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.SwitchKt$Switch$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    AnchoredDraggableState.updateAnchors$material_release$default(anchoredDraggableState2, MapsKt.mapOf(TuplesKt.to(false, Float.valueOf(minBound)), TuplesKt.to(true, Float.valueOf(maxBound))), null, 2, null);
                }
            };
            $composer2.updateRememberedValue(value$iv$iv4);
            $composer2.endReplaceableGroup();
            EffectsKt.SideEffect((Function0) value$iv$iv4, $composer2, 0);
            keys$iv = new Object[]{anchoredDraggableState2, currentChecked$delegate2, currentOnCheckedChange$delegate2, forceAnimationCheck$delegate};
            $changed$iv = 8;
            $composer2.startReplaceableGroup(-568225417);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            length = keys$iv.length;
            invalid$iv = false;
            i2 = 0;
            while (i2 < length) {
                int $changed$iv5 = $changed$iv;
                Object key$iv4 = keys$iv[i2];
                invalid$iv |= $composer2.changed(key$iv4);
                i2++;
                $changed$iv = $changed$iv5;
            }
            Object it$iv$iv10 = $composer2.rememberedValue();
            if (invalid$iv) {
            }
            value$iv$iv5 = new SwitchKt$Switch$3$1(anchoredDraggableState2, currentChecked$delegate2, currentOnCheckedChange$delegate2, forceAnimationCheck$delegate, null);
            $composer2.updateRememberedValue(value$iv$iv5);
            $composer2.endReplaceableGroup();
            EffectsKt.LaunchedEffect(anchoredDraggableState2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv5, $composer2, 64);
            Boolean boolValueOf7 = Boolean.valueOf(checked);
            Boolean boolValueOf8 = Boolean.valueOf(Switch$lambda$3(forceAnimationCheck$delegate));
            Object key1$iv6 = Boolean.valueOf(checked);
            int i13 = $dirty & 14;
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer2.changed(key1$iv6) | $composer2.changed(anchoredDraggableState2);
            value$iv$iv6 = $composer2.rememberedValue();
            if (!invalid$iv$iv2) {
            }
            value$iv$iv6 = new SwitchKt$Switch$4$1(checked, anchoredDraggableState2, null);
            $composer2.updateRememberedValue(value$iv$iv6);
            $composer2.endReplaceableGroup();
            EffectsKt.LaunchedEffect(boolValueOf7, boolValueOf8, (Function2) value$iv$iv6, $composer2, ($dirty & 14) | 512);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            objConsume = $composer2.consume(localLayoutDirection4);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (objConsume == LayoutDirection.Rtl) {
                isRtl = true;
            } else {
                isRtl = false;
            }
            if (function1 != null) {
                toggleableModifier = ToggleableKt.m723toggleableO2vRcR0(Modifier.INSTANCE, checked, interactionSource3, null, enabled2, Role.m4603boximpl(Role.INSTANCE.m4615getSwitcho7Vup1c()), function1);
            } else {
                toggleableModifier = Modifier.INSTANCE;
            }
            if (function1 != null) {
                companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
            } else {
                companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
            }
            Modifier modifierThen4 = modifier3.then(companionMinimumInteractiveComponentSize).then(toggleableModifier);
            Orientation orientation4 = Orientation.Horizontal;
            if (enabled2) {
                z2 = false;
            } else {
                z2 = false;
            }
            Modifier modifier$iv4 = SizeKt.m528requiredSizeVpY3zN4(PaddingKt.m487padding3ABfNKs(SizeKt.wrapContentSize$default(AnchoredDraggableKt.anchoredDraggable(modifierThen4, anchoredDraggableState2, orientation4, z2, isRtl, interactionSource3), Alignment.INSTANCE.getCenter(), false, 2, null), DefaultSwitchPadding), SwitchWidth, SwitchHeight);
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv4 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv4, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv4 = (0 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer2.getCurrentCompositionLocalMap();
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifier$iv4);
            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash4);
            function3ModifierMaterializerOf4.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv4 >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i14 = ($changed$iv$iv$iv4 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
            int $changed5 = ((0 >> 6) & 112) | 6;
            BoxScope $this$Switch_u24lambda_u2416 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -1229337700, "C177@7792L42,173@7637L258:Switch.kt#jmzs0o");
            boolean zBooleanValue4 = ((Boolean) anchoredDraggableState2.getTargetValue()).booleanValue();
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv3 = $composer2.changed(anchoredDraggableState2);
            Object it$iv$iv11 = $composer2.rememberedValue();
            if (invalid$iv$iv3) {
            }
            value$iv$iv7 = (Function0) new Function0<Float>() { // from class: androidx.compose.material.SwitchKt$Switch$5$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return Float.valueOf(anchoredDraggableState2.requireOffset());
                }
            };
            $composer2.updateRememberedValue(value$iv$iv7);
            $composer2.endReplaceableGroup();
            SwitchImpl($this$Switch_u24lambda_u2416, zBooleanValue4, enabled2, colors2, (Function0) value$iv$iv7, interactionSource3, $composer2, ($changed5 & 14) | (($dirty >> 3) & 896) | (($dirty >> 6) & 7168) | (($dirty << 3) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final boolean z3 = enabled2;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource3;
        final SwitchColors switchColors2 = colors2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SwitchKt.Switch.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i15) {
                SwitchKt.Switch(checked, function1, modifier6, z3, mutableInteractionSource2, switchColors2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean Switch$lambda$3(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Switch$lambda$4(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1<Boolean, Unit> Switch$lambda$7(State<? extends Function1<? super Boolean, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function1) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean Switch$lambda$8(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SwitchImpl(final BoxScope $this$SwitchImpl, final boolean checked, final boolean enabled, final SwitchColors colors, final Function0<Float> function0, final InteractionSource interactionSource, Composer $composer, final int $changed) {
        Object value$iv$iv;
        SwitchKt$SwitchImpl$1$1 value$iv$iv2;
        float f;
        Object value$iv$iv3;
        int i;
        long jSwitchImpl$lambda$18;
        Object value$iv$iv4;
        Composer $composer2 = $composer.startRestartGroup(70908914);
        ComposerKt.sourceInformation($composer2, "C(SwitchImpl)P(!1,2!1,4)219@8983L46,221@9069L614,221@9035L648,240@9886L28,244@10009L81,241@9919L171,247@10120L28,248@10198L7,*249@10257L7,251@10368L6,250@10307L228,260@10627L43,263@10780L59,257@10540L475:Switch.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed($this$SwitchImpl) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(checked) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(enabled) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 16384 : 8192;
        }
        if ((458752 & $changed) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 131072 : 65536;
        }
        int $dirty2 = $dirty;
        if ((374491 & $dirty2) != 74898 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(70908914, $dirty2, -1, "androidx.compose.material.SwitchImpl (Switch.kt:212)");
            }
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotStateKt.mutableStateListOf();
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            SnapshotStateList interactions = (SnapshotStateList) value$iv$iv;
            int i2 = (($dirty2 >> 15) & 14) | 48;
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(interactionSource) | $composer2.changed(interactions);
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new SwitchKt$SwitchImpl$1$1(interactionSource, interactions, null);
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv2, $composer2, (($dirty2 >> 15) & 14) | 64);
            boolean hasInteraction = !interactions.isEmpty();
            if (hasInteraction) {
                f = ThumbPressedElevation;
            } else {
                f = ThumbDefaultElevation;
            }
            float elevation = f;
            final State<Color> stateTrackColor = colors.trackColor(enabled, checked, $composer2, (($dirty2 >> 6) & 14) | ($dirty2 & 112) | (($dirty2 >> 3) & 896));
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default($this$SwitchImpl.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), 0.0f, 1, null);
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv2 = $composer2.changed(stateTrackColor);
            Object it$iv$iv3 = $composer2.rememberedValue();
            if (invalid$iv$iv2 || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv3 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.SwitchKt$SwitchImpl$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope Canvas) {
                        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
                        SwitchKt.m1224drawTrackRPmYEkk(Canvas, SwitchKt.SwitchImpl$lambda$16(stateTrackColor), Canvas.mo327toPx0680j_4(SwitchKt.getTrackWidth()), Canvas.mo327toPx0680j_4(SwitchKt.getTrackStrokeWidth()));
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv3);
            } else {
                value$iv$iv3 = it$iv$iv3;
            }
            $composer2.endReplaceableGroup();
            CanvasKt.Canvas(modifierFillMaxSize$default, (Function1) value$iv$iv3, $composer2, 0);
            State<Color> stateThumbColor = colors.thumbColor(enabled, checked, $composer2, (($dirty2 >> 6) & 14) | ($dirty2 & 112) | (($dirty2 >> 3) & 896));
            ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localElevationOverlay);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ElevationOverlay elevationOverlay = (ElevationOverlay) objConsume;
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float arg0$iv = ((Dp) objConsume2).m5288unboximpl();
            float arg0$iv2 = Dp.m5274constructorimpl(arg0$iv + elevation);
            $composer2.startReplaceableGroup(-539243578);
            ComposerKt.sourceInformation($composer2, "252@10443L36");
            if (Color.m2972equalsimpl0(SwitchImpl$lambda$18(stateThumbColor), MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU()) && elevationOverlay != null) {
                i = 1157296644;
                jSwitchImpl$lambda$18 = elevationOverlay.mo1082apply7g2Lkgo(SwitchImpl$lambda$18(stateThumbColor), arg0$iv2, $composer2, 0);
            } else {
                i = 1157296644;
                jSwitchImpl$lambda$18 = SwitchImpl$lambda$18(stateThumbColor);
            }
            $composer2.endReplaceableGroup();
            State<Color> stateM68animateColorAsStateeuL9pac = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(jSwitchImpl$lambda$18, null, null, null, $composer2, 0, 14);
            Modifier modifierAlign = $this$SwitchImpl.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart());
            int i3 = ($dirty2 >> 12) & 14;
            $composer2.startReplaceableGroup(i);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv3 = $composer2.changed(function0);
            Object it$iv$iv4 = $composer2.rememberedValue();
            if (invalid$iv$iv3 || it$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv4 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material.SwitchKt$SwitchImpl$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ IntOffset invoke(Density density) {
                        return IntOffset.m5383boximpl(m1225invokeBjo55l4(density));
                    }

                    /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                    public final long m1225invokeBjo55l4(Density offset) {
                        Intrinsics.checkNotNullParameter(offset, "$this$offset");
                        return IntOffsetKt.IntOffset(MathKt.roundToInt(function0.invoke().floatValue()), 0);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv4);
            } else {
                value$iv$iv4 = it$iv$iv4;
            }
            $composer2.endReplaceableGroup();
            SpacerKt.Spacer(BackgroundKt.m159backgroundbw27NRU(ShadowKt.m2643shadows4CzXII$default(SizeKt.m526requiredSize3ABfNKs(IndicationKt.indication(OffsetKt.offset(modifierAlign, (Function1) value$iv$iv4), interactionSource, RippleKt.m1298rememberRipple9IZ8Weo(false, ThumbRippleRadius, 0L, $composer2, 54, 4)), ThumbDiameter), elevation, RoundedCornerShapeKt.getCircleShape(), false, 0L, 0L, 24, null), SwitchImpl$lambda$19(stateM68animateColorAsStateeuL9pac), RoundedCornerShapeKt.getCircleShape()), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SwitchKt.SwitchImpl.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i4) {
                SwitchKt.SwitchImpl($this$SwitchImpl, checked, enabled, colors, function0, interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long SwitchImpl$lambda$16(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m2981unboximpl();
    }

    private static final long SwitchImpl$lambda$18(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m2981unboximpl();
    }

    private static final long SwitchImpl$lambda$19(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m2981unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawTrack-RPmYEkk, reason: not valid java name */
    public static final void m1224drawTrackRPmYEkk(DrawScope $this$drawTrack_u2dRPmYEkk, long trackColor, float trackWidth, float strokeWidth) {
        float strokeRadius = strokeWidth / 2;
        DrawScope.CC.m3514drawLineNGM6Ib0$default($this$drawTrack_u2dRPmYEkk, trackColor, androidx.compose.ui.geometry.OffsetKt.Offset(strokeRadius, Offset.m2732getYimpl($this$drawTrack_u2dRPmYEkk.mo3441getCenterF1C5BW0())), androidx.compose.ui.geometry.OffsetKt.Offset(trackWidth - strokeRadius, Offset.m2732getYimpl($this$drawTrack_u2dRPmYEkk.mo3441getCenterF1C5BW0())), strokeWidth, StrokeCap.INSTANCE.m3325getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
    }

    static {
        float fM5274constructorimpl = Dp.m5274constructorimpl(34);
        TrackWidth = fM5274constructorimpl;
        TrackStrokeWidth = Dp.m5274constructorimpl(14);
        float fM5274constructorimpl2 = Dp.m5274constructorimpl(20);
        ThumbDiameter = fM5274constructorimpl2;
        ThumbRippleRadius = Dp.m5274constructorimpl(24);
        DefaultSwitchPadding = Dp.m5274constructorimpl(2);
        SwitchWidth = fM5274constructorimpl;
        SwitchHeight = fM5274constructorimpl2;
        float arg0$iv = TrackWidth;
        float other$iv = ThumbDiameter;
        ThumbPathLength = Dp.m5274constructorimpl(arg0$iv - other$iv);
        AnimationSpec = new TweenSpec<>(100, 0, null, 6, null);
        ThumbDefaultElevation = Dp.m5274constructorimpl(1);
        ThumbPressedElevation = Dp.m5274constructorimpl(6);
        SwitchVelocityThreshold = Dp.m5274constructorimpl(125);
    }

    public static final float getTrackWidth() {
        return TrackWidth;
    }

    public static final float getTrackStrokeWidth() {
        return TrackStrokeWidth;
    }

    public static final float getThumbDiameter() {
        return ThumbDiameter;
    }
}
