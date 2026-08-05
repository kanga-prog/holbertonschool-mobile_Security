package androidx.compose.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.material3.tokens.RadioButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RadioButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aM\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007¢\u0006\u0002\u0010\u0014\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0013\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\u0005\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\u0006\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"RadioAnimationDuration", "", "RadioButtonDotSize", "Landroidx/compose/ui/unit/Dp;", "F", "RadioButtonPadding", "RadioStrokeWidth", "RadioButton", "", "selected", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "colors", "Landroidx/compose/material3/RadioButtonColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/RadioButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RadioButtonKt {
    private static final int RadioAnimationDuration = 100;
    private static final float RadioButtonPadding = Dp.m5274constructorimpl(2);
    private static final float RadioButtonDotSize = Dp.m5274constructorimpl(12);
    private static final float RadioStrokeWidth = Dp.m5274constructorimpl(2);

    public static final void RadioButton(final boolean selected, final Function0<Unit> function0, Modifier modifier, boolean enabled, RadioButtonColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        RadioButtonColors radioButtonColors;
        MutableInteractionSource interactionSource2;
        RadioButtonColors colors2;
        int $dirty;
        Modifier modifier3;
        boolean enabled2;
        RadioButtonColors colors3;
        Object value$iv$iv;
        float fM5274constructorimpl;
        int i2;
        Modifier.Companion selectableModifier;
        Object value$iv$iv2;
        Composer $composer2 = $composer.startRestartGroup(408580840);
        ComposerKt.sourceInformation($composer2, "C(RadioButton)P(5,4,3,1)77@3761L8,78@3821L39,80@3885L164,84@4078L29,114@5054L415,101@4636L833:RadioButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(selected) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 32 : 16;
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
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                radioButtonColors = colors;
                int i5 = $composer2.changed(radioButtonColors) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                radioButtonColors = colors;
            }
            $dirty2 |= i5;
        } else {
            radioButtonColors = colors;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            interactionSource2 = interactionSource;
        } else if ((458752 & $changed) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer2.changed(interactionSource2) ? 131072 : 65536;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((374491 & $dirty2) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enabled2 = z;
            colors3 = radioButtonColors;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled3 = i4 != 0 ? true : z;
                if ((i & 16) != 0) {
                    colors2 = RadioButtonDefaults.INSTANCE.m1683colorsro_MJ88(0L, 0L, 0L, 0L, $composer2, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = radioButtonColors;
                }
                if (i6 != 0) {
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
                    MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) value$iv$iv;
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    enabled2 = enabled3;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                } else {
                    interactionSource2 = interactionSource;
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    enabled2 = enabled3;
                    colors3 = colors2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                $dirty = $dirty2;
                modifier3 = modifier2;
                enabled2 = z;
                colors3 = radioButtonColors;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(408580840, $dirty, -1, "androidx.compose.material3.RadioButton (RadioButton.kt:72)");
            }
            if (selected) {
                float arg0$iv = RadioButtonDotSize;
                fM5274constructorimpl = Dp.m5274constructorimpl(arg0$iv / 2);
            } else {
                fM5274constructorimpl = Dp.m5274constructorimpl(0);
            }
            int $dirty3 = $dirty;
            final State<Dp> stateM80animateDpAsStateAjpBEmI = AnimateAsStateKt.m80animateDpAsStateAjpBEmI(fM5274constructorimpl, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, $composer2, 48, 12);
            final State<Color> stateRadioColor$material3_release = colors3.radioColor$material3_release(enabled2, selected, $composer2, (($dirty3 >> 9) & 14) | (($dirty3 << 3) & 112) | (($dirty3 >> 6) & 896));
            $composer2.startReplaceableGroup(735546407);
            ComposerKt.sourceInformation($composer2, "93@4433L136");
            if (function0 != null) {
                Modifier.Companion companion = Modifier.INSTANCE;
                int iM4614getRadioButtono7Vup1c = Role.INSTANCE.m4614getRadioButtono7Vup1c();
                float arg0$iv2 = RadioButtonTokens.INSTANCE.m2431getStateLayerSizeD9Ej5fM();
                i2 = 2;
                selectableModifier = SelectableKt.m719selectableO2vRcR0(companion, selected, interactionSource2, RippleKt.m1298rememberRipple9IZ8Weo(false, Dp.m5274constructorimpl(arg0$iv2 / 2), 0L, $composer2, 54, 4), enabled2, Role.m4603boximpl(iM4614getRadioButtono7Vup1c), function0);
            } else {
                i2 = 2;
                selectableModifier = Modifier.INSTANCE;
            }
            $composer2.endReplaceableGroup();
            Modifier modifierM526requiredSize3ABfNKs = SizeKt.m526requiredSize3ABfNKs(PaddingKt.m487padding3ABfNKs(SizeKt.wrapContentSize$default(modifier3.then(function0 != null ? InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE) : Modifier.INSTANCE).then(selectableModifier), Alignment.INSTANCE.getCenter(), false, i2, null), RadioButtonPadding), RadioButtonTokens.INSTANCE.m2430getIconSizeD9Ej5fM());
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(stateRadioColor$material3_release) | $composer2.changed(stateM80animateDpAsStateAjpBEmI);
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.RadioButtonKt$RadioButton$2$1
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
                        float strokeWidth = Canvas.mo327toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
                        long jM2981unboximpl = stateRadioColor$material3_release.getValue().m2981unboximpl();
                        float arg0$iv3 = RadioButtonTokens.INSTANCE.m2430getIconSizeD9Ej5fM();
                        float f = 2;
                        DrawScope.CC.m3509drawCircleVaOC9Bg$default(Canvas, jM2981unboximpl, Canvas.mo327toPx0680j_4(Dp.m5274constructorimpl(arg0$iv3 / 2)) - (strokeWidth / f), 0L, 0.0f, new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
                        if (Dp.m5273compareTo0680j_4(stateM80animateDpAsStateAjpBEmI.getValue().m5288unboximpl(), Dp.m5274constructorimpl(0)) > 0) {
                            DrawScope.CC.m3509drawCircleVaOC9Bg$default(Canvas, stateRadioColor$material3_release.getValue().m2981unboximpl(), Canvas.mo327toPx0680j_4(stateM80animateDpAsStateAjpBEmI.getValue().m5288unboximpl()) - (strokeWidth / f), 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            CanvasKt.Canvas(modifierM526requiredSize3ABfNKs, (Function1) value$iv$iv2, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final boolean z2 = enabled2;
        final RadioButtonColors radioButtonColors2 = colors3;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.RadioButtonKt.RadioButton.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i7) {
                RadioButtonKt.RadioButton(selected, function0, modifier5, z2, radioButtonColors2, mutableInteractionSource2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }
}
