package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteractionKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.material3.tokens.SwitchTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Switch.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aj\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0017¢\u0006\u0002\b\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0007¢\u0006\u0002\u0010\u001e\u001a|\u0010\u001f\u001a\u00020\u000f*\u00020 2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00020\"2\u0013\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0017¢\u0006\u0002\b\u00182\u0006\u0010\u001c\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0004H\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010*\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0013\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0019\u0010\u0007\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\b\u0010\t\"\u0013\u0010\n\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0013\u0010\u000b\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0019\u0010\f\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\r\u0010\t\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "SwitchHeight", "Landroidx/compose/ui/unit/Dp;", "F", "SwitchWidth", "ThumbDiameter", "getThumbDiameter", "()F", "ThumbPadding", "ThumbPathLength", "UncheckedThumbDiameter", "getUncheckedThumbDiameter", "Switch", "", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "thumbContent", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "enabled", "colors", "Landroidx/compose/material3/SwitchColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/SwitchColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SwitchImpl", "Landroidx/compose/foundation/layout/BoxScope;", "thumbValue", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/interaction/InteractionSource;", "thumbShape", "Landroidx/compose/ui/graphics/Shape;", "uncheckedThumbDiameter", "minBound", "maxBound", "SwitchImpl-0DmnUew", "(Landroidx/compose/foundation/layout/BoxScope;ZZLandroidx/compose/material3/SwitchColors;Landroidx/compose/runtime/State;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/graphics/Shape;FFFLandroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwitchKt {
    private static final TweenSpec<Float> AnimationSpec;
    private static final float ThumbPadding;
    private static final float ThumbPathLength;
    private static final float ThumbDiameter = SwitchTokens.INSTANCE.m2474getSelectedHandleWidthD9Ej5fM();
    private static final float UncheckedThumbDiameter = SwitchTokens.INSTANCE.m2481getUnselectedHandleWidthD9Ej5fM();
    private static final float SwitchWidth = SwitchTokens.INSTANCE.m2479getTrackWidthD9Ej5fM();
    private static final float SwitchHeight = SwitchTokens.INSTANCE.m2477getTrackHeightD9Ej5fM();

    public static final void Switch(final boolean z, final Function1<? super Boolean, Unit> function1, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, boolean z2, SwitchColors switchColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function3;
        boolean z3;
        SwitchColors switchColors2;
        MutableInteractionSource mutableInteractionSource2;
        SwitchColors switchColorsM1826colorsV1nXRL4;
        int i3;
        MutableInteractionSource mutableInteractionSource3;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function4;
        boolean z4;
        SwitchColors switchColors3;
        Object objMutableInteractionSource;
        Object obj;
        boolean z5;
        Modifier.Companion companionM723toggleableO2vRcR0;
        Modifier modifier4;
        Composer composerStartRestartGroup = composer.startRestartGroup(1580463220);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Switch)P(!1,5,4,6,2)96@4503L8,97@4563L39,*106@4851L7,107@4926L7,108@4986L111,113@5161L36,114@5214L24,116@5244L156,121@5406L216,145@6082L848:Switch.kt#uh7d8r");
        int i4 = i;
        if ((i2 & 1) != 0) {
            i4 |= 6;
        } else if ((i & 14) == 0) {
            i4 |= composerStartRestartGroup.changed(z) ? 4 : 2;
        }
        if ((i2 & 2) != 0) {
            i4 |= 48;
        } else if ((i & 112) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i5 = i2 & 4;
        if (i5 != 0) {
            i4 |= 384;
            modifier2 = modifier;
        } else if ((i & 896) == 0) {
            modifier2 = modifier;
            i4 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i2 & 8;
        if (i6 != 0) {
            i4 |= 3072;
            function3 = function2;
        } else if ((i & 7168) == 0) {
            function3 = function2;
            i4 |= composerStartRestartGroup.changedInstance(function3) ? 2048 : 1024;
        } else {
            function3 = function2;
        }
        int i7 = i2 & 16;
        if (i7 != 0) {
            i4 |= 24576;
            z3 = z2;
        } else if ((57344 & i) == 0) {
            z3 = z2;
            i4 |= composerStartRestartGroup.changed(z3) ? 16384 : 8192;
        } else {
            z3 = z2;
        }
        if ((i & 458752) == 0) {
            if ((i2 & 32) == 0) {
                switchColors2 = switchColors;
                int i8 = composerStartRestartGroup.changed(switchColors2) ? 131072 : 65536;
                i4 |= i8;
            } else {
                switchColors2 = switchColors;
            }
            i4 |= i8;
        } else {
            switchColors2 = switchColors;
        }
        int i9 = i2 & 64;
        if (i9 != 0) {
            i4 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
        } else if ((i & 3670016) == 0) {
            mutableInteractionSource2 = mutableInteractionSource;
            i4 |= composerStartRestartGroup.changed(mutableInteractionSource2) ? 1048576 : 524288;
        } else {
            mutableInteractionSource2 = mutableInteractionSource;
        }
        if ((2995931 & i4) == 599186 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier4 = modifier2;
            function4 = function3;
            z4 = z3;
            switchColors3 = switchColors2;
        } else {
            composerStartRestartGroup.startDefaults();
            String str = "CC(remember):Composables.kt#9igjgp";
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                Modifier.Companion companion = i5 != 0 ? Modifier.INSTANCE : modifier2;
                Function2<? super Composer, ? super Integer, Unit> function5 = i6 != 0 ? null : function3;
                boolean z6 = i7 != 0 ? true : z3;
                if ((i2 & 32) != 0) {
                    switchColorsM1826colorsV1nXRL4 = SwitchDefaults.INSTANCE.m1826colorsV1nXRL4(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, 1572864, SupportMenu.USER_MASK);
                    i4 &= -458753;
                } else {
                    switchColorsM1826colorsV1nXRL4 = switchColors;
                }
                if (i9 != 0) {
                    composerStartRestartGroup.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation(composerStartRestartGroup, str);
                    Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                    int i10 = i4;
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objMutableInteractionSource);
                    } else {
                        objMutableInteractionSource = objRememberedValue;
                    }
                    composerStartRestartGroup.endReplaceableGroup();
                    i3 = i10;
                    mutableInteractionSource3 = (MutableInteractionSource) objMutableInteractionSource;
                    modifier3 = companion;
                    function4 = function5;
                    z4 = z6;
                    switchColors3 = switchColorsM1826colorsV1nXRL4;
                } else {
                    i3 = i4;
                    mutableInteractionSource3 = mutableInteractionSource;
                    modifier3 = companion;
                    function4 = function5;
                    z4 = z6;
                    switchColors3 = switchColorsM1826colorsV1nXRL4;
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 32) != 0) {
                    i4 &= -458753;
                }
                function4 = function3;
                z4 = z3;
                mutableInteractionSource3 = mutableInteractionSource2;
                str = "CC(remember):Composables.kt#9igjgp";
                switchColors3 = switchColors2;
                i3 = i4;
                modifier3 = modifier2;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1580463220, i3, -1, "androidx.compose.material3.Switch (Switch.kt:90)");
            }
            float f = function4 == null ? UncheckedThumbDiameter : ThumbDiameter;
            float fM5274constructorimpl = Dp.m5274constructorimpl(Dp.m5274constructorimpl(SwitchHeight - f) / 2);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final float fMo327toPx0680j_4 = ((Density) objConsume).mo327toPx0680j_4(fM5274constructorimpl);
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            float f2 = ThumbPathLength;
            final float fMo327toPx0680j_5 = ((Density) objConsume2).mo327toPx0680j_4(f2);
            Float fValueOf = Float.valueOf(fMo327toPx0680j_4);
            Float fValueOf2 = Float.valueOf(fMo327toPx0680j_5);
            composerStartRestartGroup.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(fValueOf) | composerStartRestartGroup.changed(fValueOf2);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                obj = (Function1) new Function1<Boolean, Float>() { // from class: androidx.compose.material3.SwitchKt$Switch$valueToOffset$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public final Float invoke(boolean value) {
                        return Float.valueOf(value ? fMo327toPx0680j_5 : fMo327toPx0680j_4);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Float invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue2;
            }
            composerStartRestartGroup.endReplaceableGroup();
            final float fFloatValue = ((Number) ((Function1) obj).invoke(Boolean.valueOf(z))).floatValue();
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composerStartRestartGroup, str);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = AnimatableKt.Animatable$default(fFloatValue, 0.0f, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceableGroup();
            final Animatable animatable = (Animatable) objRememberedValue3;
            composerStartRestartGroup.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composerStartRestartGroup, str);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            composerStartRestartGroup.endReplaceableGroup();
            final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue4).getCoroutineScope();
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.SideEffect(new Function0<Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.2
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
                    Animatable.updateBounds$default(animatable, Float.valueOf(fMo327toPx0680j_4), null, 2, null);
                }
            }, composerStartRestartGroup, 0);
            EffectsKt.DisposableEffect(Boolean.valueOf(z), new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.material3.SwitchKt.Switch.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                    Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                    if (!(animatable.getTargetValue().floatValue() == fFloatValue)) {
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(animatable, fFloatValue, null), 3, null);
                    }
                    return new DisposableEffectResult() { // from class: androidx.compose.material3.SwitchKt$Switch$3$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                        }
                    };
                }

                /* JADX INFO: renamed from: androidx.compose.material3.SwitchKt$Switch$3$1, reason: invalid class name */
                /* JADX INFO: compiled from: Switch.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.SwitchKt$Switch$3$1", f = "Switch.kt", i = {}, l = {125}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Animatable<Float, AnimationVector1D> $offset;
                    final /* synthetic */ float $targetValue;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, float f, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$offset = animatable;
                        this.$targetValue = f;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$offset, this.$targetValue, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                Animatable<Float, AnimationVector1D> animatable = this.$offset;
                                Float fBoxFloat = Boxing.boxFloat(this.$targetValue);
                                TweenSpec tweenSpec = SwitchKt.AnimationSpec;
                                this.label = 1;
                                if (animatable.animateTo(fBoxFloat, (4 & 2) != 0 ? animatable.defaultSpringSpec : tweenSpec, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                break;
                            case 1:
                                ResultKt.throwOnFailure($result);
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        return Unit.INSTANCE;
                    }
                }
            }, composerStartRestartGroup, i3 & 14);
            if (function1 != null) {
                z5 = false;
                companionM723toggleableO2vRcR0 = ToggleableKt.m723toggleableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource3, null, z4, Role.m4603boximpl(Role.INSTANCE.m4615getSwitcho7Vup1c()), function1);
            } else {
                z5 = false;
                companionM723toggleableO2vRcR0 = Modifier.INSTANCE;
            }
            Modifier modifierM528requiredSizeVpY3zN4 = SizeKt.m528requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(function1 != null ? InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE) : Modifier.INSTANCE).then(companionM723toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), z5, 2, null), SwitchWidth, SwitchHeight);
            boolean z7 = z5;
            composerStartRestartGroup.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composerStartRestartGroup, (((z7 ? 1 : 0) >> 3) & 14) | (((z7 ? 1 : 0) >> 3) & 112));
            int i11 = ((z7 ? 1 : 0) << 3) & 112;
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = composerStartRestartGroup.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume3;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume4 = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume4;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume5 = composerStartRestartGroup.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ViewConfiguration viewConfiguration = (ViewConfiguration) objConsume5;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifierM528requiredSizeVpY3zN4);
            int i12 = ((i11 << 9) & 7168) | 6;
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerStartRestartGroup.disableReusing();
            Composer composerM2603constructorimpl = Updater.m2603constructorimpl(composerStartRestartGroup);
            Updater.m2610setimpl(composerM2603constructorimpl, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl(composerM2603constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl(composerM2603constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl(composerM2603constructorimpl, viewConfiguration, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            composerStartRestartGroup.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, Integer.valueOf((i12 >> 3) & 112));
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            int i13 = (i12 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i14 = (((z7 ? 1 : 0) >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1873061389, "C164@6720L9,158@6473L451:Switch.kt#uh7d8r");
            modifier4 = modifier3;
            m1828SwitchImpl0DmnUew(boxScopeInstance, z, z4, switchColors3, animatable.asState(), function4, mutableInteractionSource3, ShapesKt.toShape(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), f, fM5274constructorimpl, f2, composerStartRestartGroup, (i14 & 14) | ((i3 << 3) & 112) | ((i3 >> 6) & 896) | ((i3 >> 6) & 7168) | ((i3 << 6) & 458752) | (i3 & 3670016), 6);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            mutableInteractionSource2 = mutableInteractionSource3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier4;
        final Function2<? super Composer, ? super Integer, Unit> function6 = function4;
        final boolean z8 = z4;
        final SwitchColors switchColors4 = switchColors3;
        final MutableInteractionSource mutableInteractionSource4 = mutableInteractionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i15) {
                SwitchKt.Switch(z, function1, modifier5, function6, z8, switchColors4, mutableInteractionSource4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SwitchImpl-0DmnUew, reason: not valid java name */
    public static final void m1828SwitchImpl0DmnUew(final BoxScope $this$SwitchImpl_u2d0DmnUew, final boolean checked, final boolean enabled, final SwitchColors colors, final State<Float> state, final Function2<? super Composer, ? super Integer, Unit> function2, final InteractionSource interactionSource, final Shape thumbShape, final float uncheckedThumbDiameter, final float minBound, final float maxBound, Composer $composer, final int $changed, final int $changed1) {
        int $dirty1;
        float other$iv;
        final float thumbOffset;
        Composer $composer2;
        float arg0$iv;
        Composer $composer3 = $composer.startRestartGroup(-1968109941);
        ComposerKt.sourceInformation($composer3, "C(SwitchImpl)P(!1,2!1,8,6!1,7,9:c#ui.unit.Dp,5:c#ui.unit.Dp,4:c#ui.unit.Dp)187@7372L28,188@7440L25,*190@7508L7,210@8141L9,217@8350L29,222@8469L951:Switch.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if (($changed & 14) == 0) {
            $dirty |= $composer3.changed($this$SwitchImpl_u2d0DmnUew) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer3.changed(checked) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer3.changed(enabled) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(colors) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty |= $composer3.changed(state) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 131072 : 65536;
        }
        if (($changed & 3670016) == 0) {
            $dirty |= $composer3.changed(interactionSource) ? 1048576 : 524288;
        }
        if (($changed & 29360128) == 0) {
            $dirty |= $composer3.changed(thumbShape) ? 8388608 : 4194304;
        }
        if (($changed & 234881024) == 0) {
            $dirty |= $composer3.changed(uncheckedThumbDiameter) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 1879048192) == 0) {
            $dirty |= $composer3.changed(minBound) ? 536870912 : 268435456;
        }
        if (($changed1 & 14) == 0) {
            $dirty2 |= $composer3.changed(maxBound) ? 4 : 2;
        }
        if (($dirty & 1533916891) != 306783378 || ($dirty2 & 11) != 2 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1968109941, $dirty, $dirty2, "androidx.compose.material3.SwitchImpl (Switch.kt:175)");
            }
            State<Color> stateTrackColor$material3_release = colors.trackColor$material3_release(enabled, checked, $composer3, (($dirty >> 6) & 14) | ($dirty & 112) | (($dirty >> 3) & 896));
            State<Boolean> stateCollectIsPressedAsState = PressInteractionKt.collectIsPressedAsState(interactionSource, $composer3, ($dirty >> 18) & 14);
            $dirty1 = $dirty2;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$SwitchImpl_0DmnUew_u24lambda_u248 = (Density) objConsume;
            float thumbValueDp = $this$SwitchImpl_0DmnUew_u24lambda_u248.mo323toDpu2uoSUM(state.getValue().floatValue());
            if (SwitchImpl_0DmnUew$lambda$7(stateCollectIsPressedAsState)) {
                other$iv = SwitchTokens.INSTANCE.m2472getPressedHandleWidthD9Ej5fM();
            } else {
                float arg0$iv2 = ThumbDiameter;
                float arg0$iv3 = Dp.m5274constructorimpl(arg0$iv2 - uncheckedThumbDiameter);
                float arg0$iv4 = Dp.m5274constructorimpl(thumbValueDp - minBound);
                float other$iv2 = Dp.m5274constructorimpl(maxBound - minBound);
                other$iv = Dp.m5274constructorimpl(uncheckedThumbDiameter + Dp.m5274constructorimpl(arg0$iv3 * (arg0$iv4 / other$iv2)));
            }
            $composer3.startReplaceableGroup(-993794105);
            ComposerKt.sourceInformation($composer3, "*199@7849L7");
            if (SwitchImpl_0DmnUew$lambda$7(stateCollectIsPressedAsState)) {
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer3.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density $this$SwitchImpl_0DmnUew_u24lambda_u249 = (Density) objConsume2;
                if (checked) {
                    float arg0$iv5 = ThumbPathLength;
                    float other$iv3 = SwitchTokens.INSTANCE.m2478getTrackOutlineWidthD9Ej5fM();
                    arg0$iv = Dp.m5274constructorimpl(arg0$iv5 - other$iv3);
                } else {
                    arg0$iv = SwitchTokens.INSTANCE.m2478getTrackOutlineWidthD9Ej5fM();
                }
                thumbOffset = $this$SwitchImpl_0DmnUew_u24lambda_u249.mo327toPx0680j_4(arg0$iv);
            } else {
                thumbOffset = state.getValue().floatValue();
            }
            $composer3.endReplaceableGroup();
            Shape trackShape = ShapesKt.toShape(SwitchTokens.INSTANCE.getTrackShape(), $composer3, 6);
            Modifier modifier = BackgroundKt.m159backgroundbw27NRU(BorderKt.m171borderxT4_qwU(SizeKt.m520height3ABfNKs(SizeKt.m539width3ABfNKs($this$SwitchImpl_u2d0DmnUew.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), SwitchWidth), SwitchHeight), SwitchTokens.INSTANCE.m2478getTrackOutlineWidthD9Ej5fM(), colors.borderColor$material3_release(enabled, checked, $composer3, (($dirty >> 6) & 14) | ($dirty & 112) | (($dirty >> 3) & 896)).getValue().m2981unboximpl(), trackShape), SwitchImpl_0DmnUew$lambda$6(stateTrackColor$material3_release), trackShape);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            int $i$f$Box = ((0 >> 3) & 14) | ((0 >> 3) & 112);
            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, $i$f$Box);
            int $changed$iv$iv = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv = (Density) objConsume3;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer3.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume5 = $composer3.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier);
            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            $composer3.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            int i2 = ((0 >> 6) & 112) | 6;
            BoxScope $this$SwitchImpl_0DmnUew_u24lambda_u2413 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1420969751, "C223@8518L28,228@8706L42,231@8870L64,225@8599L815:Switch.kt#uh7d8r");
            long resolvedThumbColor = SwitchImpl_0DmnUew$lambda$13$lambda$10(colors.thumbColor$material3_release(enabled, checked, $composer3, (($dirty >> 6) & 14) | ($dirty & 112) | (($dirty >> 3) & 896)));
            Modifier modifierAlign = $this$SwitchImpl_0DmnUew_u24lambda_u2413.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart());
            Object key1$iv = Float.valueOf(thumbOffset);
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer3.changed(key1$iv);
            Object value$iv$iv = $composer3.rememberedValue();
            if (invalid$iv$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material3.SwitchKt$SwitchImpl$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ IntOffset invoke(Density density) {
                        return IntOffset.m5383boximpl(m1830invokeBjo55l4(density));
                    }

                    /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                    public final long m1830invokeBjo55l4(Density offset) {
                        Intrinsics.checkNotNullParameter(offset, "$this$offset");
                        return IntOffsetKt.IntOffset(MathKt.roundToInt(thumbOffset), 0);
                    }
                };
                $composer3.updateRememberedValue(value$iv$iv);
            }
            $composer3.endReplaceableGroup();
            Modifier modifierOffset = OffsetKt.offset(modifierAlign, (Function1) value$iv$iv);
            float arg0$iv6 = SwitchTokens.INSTANCE.m2476getStateLayerSizeD9Ej5fM();
            float thumbOffset2 = 2;
            Modifier modifier$iv = BackgroundKt.m159backgroundbw27NRU(SizeKt.m526requiredSize3ABfNKs(IndicationKt.indication(modifierOffset, interactionSource, RippleKt.m1298rememberRipple9IZ8Weo(false, Dp.m5274constructorimpl(arg0$iv6 / thumbOffset2), 0L, $composer3, 54, 4)), other$iv), resolvedThumbColor, thumbShape);
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv2 = (48 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer3.consume(localDensity4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv2 = (Density) objConsume6;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume7 = $composer3.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume7;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
            $composer2 = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume8 = $composer3.consume(localViewConfiguration2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume8;
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv);
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor2);
            } else {
                $composer3.useNode();
            }
            $composer3.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i3 = ($changed$iv$iv$iv2 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i4 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -2040677196, "C:Switch.kt#uh7d8r");
            $composer3.startReplaceableGroup(1420970387);
            ComposerKt.sourceInformation($composer3, "238@9196L27,239@9240L150");
            if (function2 != null) {
                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(colors.iconColor$material3_release(enabled, checked, $composer3, (($dirty >> 6) & 14) | ($dirty & 112) | (($dirty >> 3) & 896)).getValue())}, function2, $composer3, (($dirty >> 12) & 112) | 8);
            }
            $composer3.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $dirty1 = $dirty2;
            $composer2 = $composer3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt$SwitchImpl$2
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

            public final void invoke(Composer composer, int i5) {
                SwitchKt.m1828SwitchImpl0DmnUew($this$SwitchImpl_u2d0DmnUew, checked, enabled, colors, state, function2, interactionSource, thumbShape, uncheckedThumbDiameter, minBound, maxBound, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
            }
        });
    }

    private static final long SwitchImpl_0DmnUew$lambda$6(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m2981unboximpl();
    }

    private static final boolean SwitchImpl_0DmnUew$lambda$7(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    private static final long SwitchImpl_0DmnUew$lambda$13$lambda$10(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m2981unboximpl();
    }

    static {
        float arg0$iv = SwitchHeight;
        float other$iv = ThumbDiameter;
        ThumbPadding = Dp.m5274constructorimpl(Dp.m5274constructorimpl(arg0$iv - other$iv) / 2);
        float arg0$iv2 = SwitchWidth;
        float other$iv2 = ThumbDiameter;
        float arg0$iv3 = Dp.m5274constructorimpl(arg0$iv2 - other$iv2);
        float other$iv3 = ThumbPadding;
        ThumbPathLength = Dp.m5274constructorimpl(arg0$iv3 - other$iv3);
        AnimationSpec = new TweenSpec<>(100, 0, null, 6, null);
    }

    public static final float getThumbDiameter() {
        return ThumbDiameter;
    }

    public static final float getUncheckedThumbDiameter() {
        return UncheckedThumbDiameter;
    }
}
