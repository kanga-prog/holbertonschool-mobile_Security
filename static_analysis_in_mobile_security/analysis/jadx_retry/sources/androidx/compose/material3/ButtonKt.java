package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
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
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Button.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u008d\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008d\u0001\u0010\u001a\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008d\u0001\u0010\u001b\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008d\u0001\u0010\u001c\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008d\u0001\u0010\u001d\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019¨\u0006\u001e"}, d2 = {"Button", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/ButtonColors;", "elevation", "Landroidx/compose/material3/ButtonElevation;", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ButtonColors;Landroidx/compose/material3/ButtonElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ElevatedButton", "FilledTonalButton", "OutlinedButton", "TextButton", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ButtonKt {
    /* JADX WARN: Code duplicated, block: B:116:0x0177  */
    /* JADX WARN: Code duplicated, block: B:118:0x0184  */
    /* JADX WARN: Code duplicated, block: B:131:0x01bd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:132:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:133:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:135:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:138:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:141:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:142:0x0202  */
    /* JADX WARN: Code duplicated, block: B:145:0x020b  */
    /* JADX WARN: Code duplicated, block: B:146:0x0222  */
    /* JADX WARN: Code duplicated, block: B:148:0x0225  */
    /* JADX WARN: Code duplicated, block: B:149:0x0227  */
    /* JADX WARN: Code duplicated, block: B:151:0x022b  */
    /* JADX WARN: Code duplicated, block: B:152:0x0232  */
    /* JADX WARN: Code duplicated, block: B:154:0x0236  */
    /* JADX WARN: Code duplicated, block: B:156:0x025a  */
    /* JADX WARN: Code duplicated, block: B:157:0x0269  */
    /* JADX WARN: Code duplicated, block: B:159:0x0286  */
    /* JADX WARN: Code duplicated, block: B:162:0x02a1  */
    /* JADX WARN: Code duplicated, block: B:165:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:166:0x02e8  */
    /* JADX WARN: Code duplicated, block: B:169:0x0303  */
    /* JADX WARN: Code duplicated, block: B:170:0x030f  */
    /* JADX WARN: Code duplicated, block: B:173:0x0325  */
    /* JADX WARN: Code duplicated, block: B:174:0x0327  */
    /* JADX WARN: Code duplicated, block: B:177:0x0342  */
    /* JADX WARN: Code duplicated, block: B:178:0x034d  */
    /* JADX WARN: Code duplicated, block: B:181:0x03ba  */
    /* JADX WARN: Code duplicated, block: B:185:0x03c4  */
    /* JADX WARN: Code duplicated, block: B:187:? A[RETURN, SYNTHETIC] */
    public static final void Button(final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, ButtonColors colors, ButtonElevation elevation, BorderStroke border, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        boolean enabled2;
        Shape shape2;
        ButtonColors buttonColors;
        ButtonElevation buttonElevation;
        MutableInteractionSource mutableInteractionSource;
        int i2;
        Modifier.Companion modifier2;
        ButtonColors colors2;
        ButtonElevation elevation2;
        BorderStroke border2;
        PaddingValues contentPadding2;
        int $dirty;
        MutableInteractionSource interactionSource2;
        BorderStroke border3;
        ButtonColors colors3;
        Shape shape3;
        ButtonElevation elevation3;
        PaddingValues contentPadding3;
        Modifier modifier3;
        Object it$iv$iv;
        Object value$iv$iv;
        State<Dp> stateShadowElevation$material3_release;
        float shadowElevation;
        State<Dp> state;
        float tonalElevation;
        ButtonElevation elevation4;
        ButtonColors colors4;
        Modifier modifier4;
        boolean enabled3;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(650121315);
        ComposerKt.sourceInformation($composer3, "C(Button)P(8,7,5,9,1,4!1,3,6)108@5507L5,109@5556L14,110@5621L17,113@5791L39,116@5913L23,117@5973L21,120@6195L1045:Button.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
            enabled2 = enabled;
        } else if (($changed & 896) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i5 = $composer3.changed(shape2) ? 2048 : 1024;
                $dirty2 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                buttonColors = colors;
                int i6 = $composer3.changed(buttonColors) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                buttonColors = colors;
            }
            $dirty2 |= i6;
        } else {
            buttonColors = colors;
        }
        if ((458752 & $changed) == 0) {
            if ((i & 32) == 0) {
                buttonElevation = elevation;
                int i7 = $composer3.changed(buttonElevation) ? 131072 : 65536;
                $dirty2 |= i7;
            } else {
                buttonElevation = elevation;
            }
            $dirty2 |= i7;
        } else {
            buttonElevation = elevation;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty2 |= 1572864;
        } else if ((3670016 & $changed) == 0) {
            $dirty2 |= $composer3.changed(border) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty2 |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty2 |= 100663296;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 234881024) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer3.changed(mutableInteractionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 512) == 0) {
            if (($changed & 1879048192) == 0) {
                i2 = $composer3.changedInstance(content) ? 536870912 : 268435456;
            }
            if ((1533916891 & $dirty2) == 306783378 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    }
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                        shape2 = ButtonDefaults.INSTANCE.getShape($composer3, 6);
                    }
                    if ((i & 16) != 0) {
                        colors2 = ButtonDefaults.INSTANCE.m1334buttonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                        $dirty2 &= -57345;
                    } else {
                        colors2 = buttonColors;
                    }
                    if ((i & 32) != 0) {
                        elevation2 = ButtonDefaults.INSTANCE.m1335buttonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                        $dirty2 &= -458753;
                    } else {
                        elevation2 = buttonElevation;
                    }
                    if (i8 != 0) {
                        border2 = null;
                    } else {
                        border2 = border;
                    }
                    if (i9 != 0) {
                        contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i10 != 0) {
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        Modifier modifier5 = modifier2;
                        it$iv$iv = $composer3.rememberedValue();
                        int $dirty3 = $dirty2;
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                        border3 = border2;
                        colors3 = colors2;
                        shape3 = shape2;
                        elevation3 = elevation2;
                        contentPadding3 = contentPadding2;
                        $dirty = $dirty3;
                        modifier3 = modifier5;
                    } else {
                        Modifier modifier6 = modifier2;
                        $dirty = $dirty2;
                        interactionSource2 = interactionSource;
                        border3 = border2;
                        colors3 = colors2;
                        shape3 = shape2;
                        elevation3 = elevation2;
                        contentPadding3 = contentPadding2;
                        modifier3 = modifier6;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        $dirty = $dirty2 & (-458753);
                        modifier3 = modifier;
                        border3 = border;
                        contentPadding3 = contentPadding;
                        shape3 = shape2;
                        colors3 = buttonColors;
                        elevation3 = buttonElevation;
                        interactionSource2 = mutableInteractionSource;
                    } else {
                        modifier3 = modifier;
                        border3 = border;
                        contentPadding3 = contentPadding;
                        $dirty = $dirty2;
                        shape3 = shape2;
                        colors3 = buttonColors;
                        elevation3 = buttonElevation;
                        interactionSource2 = mutableInteractionSource;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(650121315, $dirty, -1, "androidx.compose.material3.Button (Button.kt:104)");
                }
                long containerColor = colors3.containerColor$material3_release(enabled2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl();
                final long contentColor = colors3.contentColor$material3_release(enabled2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl();
                $composer3.startReplaceableGroup(823569249);
                ComposerKt.sourceInformation($composer3, "118@6038L43");
                if (elevation3 == null) {
                    stateShadowElevation$material3_release = null;
                } else {
                    stateShadowElevation$material3_release = elevation3.shadowElevation$material3_release(enabled2, interactionSource2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 21) & 112) | (($dirty >> 9) & 896));
                }
                $composer3.endReplaceableGroup();
                if (stateShadowElevation$material3_release != null) {
                    shadowElevation = stateShadowElevation$material3_release.getValue().m5288unboximpl();
                } else {
                    shadowElevation = Dp.m5274constructorimpl(0);
                }
                $composer3.startReplaceableGroup(823569344);
                ComposerKt.sourceInformation($composer3, "119@6133L42");
                if (elevation3 == null) {
                    state = null;
                } else {
                    state = elevation3.tonalElevation$material3_release(enabled2, interactionSource2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 21) & 112) | (($dirty >> 9) & 896));
                }
                $composer3.endReplaceableGroup();
                if (state != null) {
                    tonalElevation = state.getValue().m5288unboximpl();
                } else {
                    tonalElevation = Dp.m5274constructorimpl(0);
                }
                final PaddingValues paddingValues = contentPadding3;
                final int i11 = $dirty;
                elevation4 = elevation3;
                colors4 = colors3;
                modifier4 = modifier3;
                enabled3 = enabled2;
                $composer2 = $composer3;
                SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semantics) {
                        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                        SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4610getButtono7Vup1c());
                    }
                }, 1, null), enabled2, shape3, containerColor, contentColor, tonalElevation, shadowElevation, border3, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 956488494, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.3
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

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C132@6583L651:Button.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(956488494, $changed2, -1, "androidx.compose.material3.Button.<anonymous> (Button.kt:131)");
                            }
                            ProvidedValue[] providedValueArr = {ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor))};
                            final PaddingValues paddingValues2 = paddingValues;
                            final Function3<RowScope, Composer, Integer, Unit> function3 = content;
                            final int i12 = i11;
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer4, 1582292974, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.3.1
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

                                public final void invoke(Composer $composer5, int $changed3) {
                                    ComposerKt.sourceInformation($composer5, "C133@6702L10,133@6663L561:Button.kt#uh7d8r");
                                    if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1582292974, $changed3, -1, "androidx.compose.material3.Button.<anonymous>.<anonymous> (Button.kt:132)");
                                        }
                                        TextStyle labelLarge = MaterialTheme.INSTANCE.getTypography($composer5, 6).getLabelLarge();
                                        final PaddingValues paddingValues3 = paddingValues2;
                                        final Function3<RowScope, Composer, Integer, Unit> function4 = function3;
                                        final int i13 = i12;
                                        TextKt.ProvideTextStyle(labelLarge, ComposableLambdaKt.composableLambda($composer5, -2136309793, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.3.1.1
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

                                            public final void invoke(Composer $composer6, int $changed4) {
                                                ComposerKt.sourceInformation($composer6, "C134@6743L467:Button.kt#uh7d8r");
                                                if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-2136309793, $changed4, -1, "androidx.compose.material3.Button.<anonymous>.<anonymous>.<anonymous> (Button.kt:133)");
                                                    }
                                                    Modifier modifier$iv = PaddingKt.padding(SizeKt.m518defaultMinSizeVpY3zN4(Modifier.INSTANCE, ButtonDefaults.INSTANCE.m1343getMinWidthD9Ej5fM(), ButtonDefaults.INSTANCE.m1342getMinHeightD9Ej5fM()), paddingValues3);
                                                    Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getCenter();
                                                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                                    Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                                                    int $changed$iv = ((i13 >> 18) & 7168) | 432;
                                                    $composer6.startReplaceableGroup(693286680);
                                                    ComposerKt.sourceInformation($composer6, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                                    MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer6, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                                    int $changed$iv$iv = ($changed$iv << 3) & 112;
                                                    $composer6.startReplaceableGroup(-1323940314);
                                                    ComposerKt.sourceInformation($composer6, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume = $composer6.consume(localDensity);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    Density density$iv$iv = (Density) objConsume;
                                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume2 = $composer6.consume(localLayoutDirection);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                                                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume3 = $composer6.consume(localViewConfiguration);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                                                    int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                                                    if (!($composer6.getApplier() instanceof Applier)) {
                                                        ComposablesKt.invalidApplier();
                                                    }
                                                    $composer6.startReusableNode();
                                                    if ($composer6.getInserting()) {
                                                        $composer6.createNode(constructor);
                                                    } else {
                                                        $composer6.useNode();
                                                    }
                                                    $composer6.disableReusing();
                                                    Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer6);
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                                    $composer6.enableReusing();
                                                    function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                                    $composer6.startReplaceableGroup(2058660585);
                                                    int i14 = ($changed$iv$iv$iv >> 9) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                                    function5.invoke(RowScopeInstance.INSTANCE, $composer6, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    $composer6.endReplaceableGroup();
                                                    $composer6.endNode();
                                                    $composer6.endReplaceableGroup();
                                                    $composer6.endReplaceableGroup();
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                $composer6.skipToGroupEnd();
                                            }
                                        }), $composer5, 48);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer5.skipToGroupEnd();
                                }
                            }), $composer4, 56);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }), $composer2, ($dirty & 14) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 6) & 234881024) | (($dirty << 3) & 1879048192), 6, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier4 = modifier;
                border3 = border;
                contentPadding3 = contentPadding;
                enabled3 = enabled2;
                $composer2 = $composer3;
                shape3 = shape2;
                colors4 = buttonColors;
                elevation4 = buttonElevation;
                interactionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier7 = modifier4;
            final boolean z = enabled3;
            final Shape shape4 = shape3;
            final ButtonColors buttonColors2 = colors4;
            final ButtonElevation buttonElevation2 = elevation4;
            final BorderStroke borderStroke = border3;
            final PaddingValues paddingValues2 = contentPadding3;
            final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.4
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

                public final void invoke(Composer composer, int i12) {
                    ButtonKt.Button(onClick, modifier7, z, shape4, buttonColors2, buttonElevation2, borderStroke, paddingValues2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty2 |= i2;
        if ((1533916891 & $dirty2) == 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = ButtonDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1334buttonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = buttonColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = ButtonDefaults.INSTANCE.m1335buttonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty2 &= -458753;
                } else {
                    elevation2 = buttonElevation;
                }
                if (i8 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i9 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i10 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier8 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty4 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    border3 = border2;
                    colors3 = colors2;
                    shape3 = shape2;
                    elevation3 = elevation2;
                    contentPadding3 = contentPadding2;
                    $dirty = $dirty4;
                    modifier3 = modifier8;
                } else {
                    Modifier modifier9 = modifier2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    border3 = border2;
                    colors3 = colors2;
                    shape3 = shape2;
                    elevation3 = elevation2;
                    contentPadding3 = contentPadding2;
                    modifier3 = modifier9;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = ButtonDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1334buttonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = buttonColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = ButtonDefaults.INSTANCE.m1335buttonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty2 &= -458753;
                } else {
                    elevation2 = buttonElevation;
                }
                if (i8 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i9 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i10 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier10 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty5 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    border3 = border2;
                    colors3 = colors2;
                    shape3 = shape2;
                    elevation3 = elevation2;
                    contentPadding3 = contentPadding2;
                    $dirty = $dirty5;
                    modifier3 = modifier10;
                } else {
                    Modifier modifier11 = modifier2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    border3 = border2;
                    colors3 = colors2;
                    shape3 = shape2;
                    elevation3 = elevation2;
                    contentPadding3 = contentPadding2;
                    modifier3 = modifier11;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(650121315, $dirty, -1, "androidx.compose.material3.Button (Button.kt:104)");
            }
            long containerColor2 = colors3.containerColor$material3_release(enabled2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl();
            final long contentColor2 = colors3.contentColor$material3_release(enabled2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl();
            $composer3.startReplaceableGroup(823569249);
            ComposerKt.sourceInformation($composer3, "118@6038L43");
            if (elevation3 == null) {
                stateShadowElevation$material3_release = null;
            } else {
                stateShadowElevation$material3_release = elevation3.shadowElevation$material3_release(enabled2, interactionSource2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 21) & 112) | (($dirty >> 9) & 896));
            }
            $composer3.endReplaceableGroup();
            if (stateShadowElevation$material3_release != null) {
                shadowElevation = stateShadowElevation$material3_release.getValue().m5288unboximpl();
            } else {
                shadowElevation = Dp.m5274constructorimpl(0);
            }
            $composer3.startReplaceableGroup(823569344);
            ComposerKt.sourceInformation($composer3, "119@6133L42");
            if (elevation3 == null) {
                state = null;
            } else {
                state = elevation3.tonalElevation$material3_release(enabled2, interactionSource2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 21) & 112) | (($dirty >> 9) & 896));
            }
            $composer3.endReplaceableGroup();
            if (state != null) {
                tonalElevation = state.getValue().m5288unboximpl();
            } else {
                tonalElevation = Dp.m5274constructorimpl(0);
            }
            final PaddingValues paddingValues3 = contentPadding3;
            final int i12 = $dirty;
            elevation4 = elevation3;
            colors4 = colors3;
            modifier4 = modifier3;
            enabled3 = enabled2;
            $composer2 = $composer3;
            SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4610getButtono7Vup1c());
                }
            }, 1, null), enabled2, shape3, containerColor2, contentColor2, tonalElevation, shadowElevation, border3, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 956488494, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.3
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

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C132@6583L651:Button.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(956488494, $changed2, -1, "androidx.compose.material3.Button.<anonymous> (Button.kt:131)");
                        }
                        ProvidedValue[] providedValueArr = {ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor2))};
                        final PaddingValues paddingValues4 = paddingValues3;
                        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3 = content;
                        final int i13 = i12;
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer4, 1582292974, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.3.1
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

                            public final void invoke(Composer $composer5, int $changed3) {
                                ComposerKt.sourceInformation($composer5, "C133@6702L10,133@6663L561:Button.kt#uh7d8r");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1582292974, $changed3, -1, "androidx.compose.material3.Button.<anonymous>.<anonymous> (Button.kt:132)");
                                    }
                                    TextStyle labelLarge = MaterialTheme.INSTANCE.getTypography($composer5, 6).getLabelLarge();
                                    final PaddingValues paddingValues5 = paddingValues4;
                                    final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4 = function3;
                                    final int i14 = i13;
                                    TextKt.ProvideTextStyle(labelLarge, ComposableLambdaKt.composableLambda($composer5, -2136309793, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.3.1.1
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

                                        public final void invoke(Composer $composer6, int $changed4) {
                                            ComposerKt.sourceInformation($composer6, "C134@6743L467:Button.kt#uh7d8r");
                                            if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-2136309793, $changed4, -1, "androidx.compose.material3.Button.<anonymous>.<anonymous>.<anonymous> (Button.kt:133)");
                                                }
                                                Modifier modifier$iv = PaddingKt.padding(SizeKt.m518defaultMinSizeVpY3zN4(Modifier.INSTANCE, ButtonDefaults.INSTANCE.m1343getMinWidthD9Ej5fM(), ButtonDefaults.INSTANCE.m1342getMinHeightD9Ej5fM()), paddingValues5);
                                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getCenter();
                                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                                Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                                                int $changed$iv = ((i14 >> 18) & 7168) | 432;
                                                $composer6.startReplaceableGroup(693286680);
                                                ComposerKt.sourceInformation($composer6, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer6, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                                $composer6.startReplaceableGroup(-1323940314);
                                                ComposerKt.sourceInformation($composer6, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                                ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                Object objConsume = $composer6.consume(localDensity);
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                Density density$iv$iv = (Density) objConsume;
                                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                                ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                Object objConsume2 = $composer6.consume(localLayoutDirection);
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                                ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                Object objConsume3 = $composer6.consume(localViewConfiguration);
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                                                int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                                                if (!($composer6.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer6.startReusableNode();
                                                if ($composer6.getInserting()) {
                                                    $composer6.createNode(constructor);
                                                } else {
                                                    $composer6.useNode();
                                                }
                                                $composer6.disableReusing();
                                                Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer6);
                                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                                $composer6.enableReusing();
                                                function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                                $composer6.startReplaceableGroup(2058660585);
                                                int i15 = ($changed$iv$iv$iv >> 9) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                                function5.invoke(RowScopeInstance.INSTANCE, $composer6, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                $composer6.endReplaceableGroup();
                                                $composer6.endNode();
                                                $composer6.endReplaceableGroup();
                                                $composer6.endReplaceableGroup();
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer6.skipToGroupEnd();
                                        }
                                    }), $composer5, 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }), $composer4, 56);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty & 14) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 6) & 234881024) | (($dirty << 3) & 1879048192), 6, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = ButtonDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1334buttonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = buttonColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = ButtonDefaults.INSTANCE.m1335buttonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty2 &= -458753;
                } else {
                    elevation2 = buttonElevation;
                }
                if (i8 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i9 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i10 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier12 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty6 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    border3 = border2;
                    colors3 = colors2;
                    shape3 = shape2;
                    elevation3 = elevation2;
                    contentPadding3 = contentPadding2;
                    $dirty = $dirty6;
                    modifier3 = modifier12;
                } else {
                    Modifier modifier13 = modifier2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    border3 = border2;
                    colors3 = colors2;
                    shape3 = shape2;
                    elevation3 = elevation2;
                    contentPadding3 = contentPadding2;
                    modifier3 = modifier13;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = ButtonDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1334buttonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = buttonColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = ButtonDefaults.INSTANCE.m1335buttonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty2 &= -458753;
                } else {
                    elevation2 = buttonElevation;
                }
                if (i8 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i9 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i10 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier14 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty7 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    border3 = border2;
                    colors3 = colors2;
                    shape3 = shape2;
                    elevation3 = elevation2;
                    contentPadding3 = contentPadding2;
                    $dirty = $dirty7;
                    modifier3 = modifier14;
                } else {
                    Modifier modifier15 = modifier2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    border3 = border2;
                    colors3 = colors2;
                    shape3 = shape2;
                    elevation3 = elevation2;
                    contentPadding3 = contentPadding2;
                    modifier3 = modifier15;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(650121315, $dirty, -1, "androidx.compose.material3.Button (Button.kt:104)");
            }
            long containerColor3 = colors3.containerColor$material3_release(enabled2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl();
            final long contentColor3 = colors3.contentColor$material3_release(enabled2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl();
            $composer3.startReplaceableGroup(823569249);
            ComposerKt.sourceInformation($composer3, "118@6038L43");
            if (elevation3 == null) {
                stateShadowElevation$material3_release = null;
            } else {
                stateShadowElevation$material3_release = elevation3.shadowElevation$material3_release(enabled2, interactionSource2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 21) & 112) | (($dirty >> 9) & 896));
            }
            $composer3.endReplaceableGroup();
            if (stateShadowElevation$material3_release != null) {
                shadowElevation = stateShadowElevation$material3_release.getValue().m5288unboximpl();
            } else {
                shadowElevation = Dp.m5274constructorimpl(0);
            }
            $composer3.startReplaceableGroup(823569344);
            ComposerKt.sourceInformation($composer3, "119@6133L42");
            if (elevation3 == null) {
                state = null;
            } else {
                state = elevation3.tonalElevation$material3_release(enabled2, interactionSource2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 21) & 112) | (($dirty >> 9) & 896));
            }
            $composer3.endReplaceableGroup();
            if (state != null) {
                tonalElevation = state.getValue().m5288unboximpl();
            } else {
                tonalElevation = Dp.m5274constructorimpl(0);
            }
            final PaddingValues paddingValues4 = contentPadding3;
            final int i13 = $dirty;
            elevation4 = elevation3;
            colors4 = colors3;
            modifier4 = modifier3;
            enabled3 = enabled2;
            $composer2 = $composer3;
            SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4610getButtono7Vup1c());
                }
            }, 1, null), enabled2, shape3, containerColor3, contentColor3, tonalElevation, shadowElevation, border3, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 956488494, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.3
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

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C132@6583L651:Button.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(956488494, $changed2, -1, "androidx.compose.material3.Button.<anonymous> (Button.kt:131)");
                        }
                        ProvidedValue[] providedValueArr = {ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor3))};
                        final PaddingValues paddingValues5 = paddingValues4;
                        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3 = content;
                        final int i14 = i13;
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer4, 1582292974, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.3.1
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

                            public final void invoke(Composer $composer5, int $changed3) {
                                ComposerKt.sourceInformation($composer5, "C133@6702L10,133@6663L561:Button.kt#uh7d8r");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1582292974, $changed3, -1, "androidx.compose.material3.Button.<anonymous>.<anonymous> (Button.kt:132)");
                                    }
                                    TextStyle labelLarge = MaterialTheme.INSTANCE.getTypography($composer5, 6).getLabelLarge();
                                    final PaddingValues paddingValues6 = paddingValues5;
                                    final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4 = function3;
                                    final int i15 = i14;
                                    TextKt.ProvideTextStyle(labelLarge, ComposableLambdaKt.composableLambda($composer5, -2136309793, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.3.1.1
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

                                        public final void invoke(Composer $composer6, int $changed4) {
                                            ComposerKt.sourceInformation($composer6, "C134@6743L467:Button.kt#uh7d8r");
                                            if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-2136309793, $changed4, -1, "androidx.compose.material3.Button.<anonymous>.<anonymous>.<anonymous> (Button.kt:133)");
                                                }
                                                Modifier modifier$iv = PaddingKt.padding(SizeKt.m518defaultMinSizeVpY3zN4(Modifier.INSTANCE, ButtonDefaults.INSTANCE.m1343getMinWidthD9Ej5fM(), ButtonDefaults.INSTANCE.m1342getMinHeightD9Ej5fM()), paddingValues6);
                                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getCenter();
                                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                                Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                                                int $changed$iv = ((i15 >> 18) & 7168) | 432;
                                                $composer6.startReplaceableGroup(693286680);
                                                ComposerKt.sourceInformation($composer6, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer6, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                                $composer6.startReplaceableGroup(-1323940314);
                                                ComposerKt.sourceInformation($composer6, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                                ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                Object objConsume = $composer6.consume(localDensity);
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                Density density$iv$iv = (Density) objConsume;
                                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                                ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                Object objConsume2 = $composer6.consume(localLayoutDirection);
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                                ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                Object objConsume3 = $composer6.consume(localViewConfiguration);
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                                                int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                                                if (!($composer6.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer6.startReusableNode();
                                                if ($composer6.getInserting()) {
                                                    $composer6.createNode(constructor);
                                                } else {
                                                    $composer6.useNode();
                                                }
                                                $composer6.disableReusing();
                                                Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer6);
                                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                                $composer6.enableReusing();
                                                function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                                $composer6.startReplaceableGroup(2058660585);
                                                int i16 = ($changed$iv$iv$iv >> 9) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                                function5.invoke(RowScopeInstance.INSTANCE, $composer6, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                $composer6.endReplaceableGroup();
                                                $composer6.endNode();
                                                $composer6.endReplaceableGroup();
                                                $composer6.endReplaceableGroup();
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer6.skipToGroupEnd();
                                        }
                                    }), $composer5, 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }), $composer4, 56);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty & 14) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 6) & 234881024) | (($dirty << 3) & 1879048192), 6, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier16 = modifier4;
        final boolean z2 = enabled3;
        final Shape shape5 = shape3;
        final ButtonColors buttonColors3 = colors4;
        final ButtonElevation buttonElevation3 = elevation4;
        final BorderStroke borderStroke2 = border3;
        final PaddingValues paddingValues5 = contentPadding3;
        final MutableInteractionSource mutableInteractionSource3 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.Button.4
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

            public final void invoke(Composer composer, int i14) {
                ButtonKt.Button(onClick, modifier16, z2, shape5, buttonColors3, buttonElevation3, borderStroke2, paddingValues5, mutableInteractionSource3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:116:0x017b  */
    /* JADX WARN: Code duplicated, block: B:118:0x0185  */
    /* JADX WARN: Code duplicated, block: B:131:0x01c8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:132:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:133:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:135:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:139:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:140:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:143:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:144:0x0209  */
    /* JADX WARN: Code duplicated, block: B:147:0x0211  */
    /* JADX WARN: Code duplicated, block: B:148:0x022d  */
    /* JADX WARN: Code duplicated, block: B:150:0x0233  */
    /* JADX WARN: Code duplicated, block: B:151:0x0235  */
    /* JADX WARN: Code duplicated, block: B:153:0x0239  */
    /* JADX WARN: Code duplicated, block: B:154:0x0240  */
    /* JADX WARN: Code duplicated, block: B:156:0x0244  */
    /* JADX WARN: Code duplicated, block: B:158:0x0263  */
    /* JADX WARN: Code duplicated, block: B:159:0x026e  */
    /* JADX WARN: Code duplicated, block: B:161:0x0285  */
    /* JADX WARN: Code duplicated, block: B:164:0x029e  */
    /* JADX WARN: Code duplicated, block: B:167:0x02f8  */
    /* JADX WARN: Code duplicated, block: B:171:0x0302  */
    /* JADX WARN: Code duplicated, block: B:173:? A[RETURN, SYNTHETIC] */
    public static final void ElevatedButton(final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, ButtonColors colors, ButtonElevation elevation, BorderStroke border, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Shape shape2;
        ButtonColors buttonColors;
        BorderStroke borderStroke;
        MutableInteractionSource mutableInteractionSource;
        int i2;
        Modifier.Companion modifier2;
        boolean enabled2;
        Shape shape3;
        int $dirty;
        ButtonColors colors2;
        ButtonElevation elevation2;
        BorderStroke border2;
        PaddingValues contentPadding2;
        MutableInteractionSource interactionSource2;
        ButtonElevation elevation3;
        BorderStroke border3;
        PaddingValues contentPadding3;
        int $dirty2;
        ButtonColors colors3;
        Modifier modifier3;
        Object it$iv$iv;
        Object value$iv$iv;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(1466887385);
        ComposerKt.sourceInformation($composer3, "C(ElevatedButton)P(8,7,5,9,1,4!1,3,6)199@10107L13,200@10164L22,201@10237L25,204@10415L39,207@10509L314:Button.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty3 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty3 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty3 |= 384;
            z = enabled;
        } else if (($changed & 896) == 0) {
            z = enabled;
            $dirty3 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i5 = $composer3.changed(shape2) ? 2048 : 1024;
                $dirty3 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i5;
        } else {
            shape2 = shape;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                buttonColors = colors;
                int i6 = $composer3.changed(buttonColors) ? 16384 : 8192;
                $dirty3 |= i6;
            } else {
                buttonColors = colors;
            }
            $dirty3 |= i6;
        } else {
            buttonColors = colors;
        }
        if (($changed & 458752) == 0) {
            $dirty3 |= ((i & 32) == 0 && $composer3.changed(elevation)) ? 131072 : 65536;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty3 |= 1572864;
            borderStroke = border;
        } else if (($changed & 3670016) == 0) {
            borderStroke = border;
            $dirty3 |= $composer3.changed(borderStroke) ? 1048576 : 524288;
        } else {
            borderStroke = border;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty3 |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty3 |= 100663296;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 234881024) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty3 |= $composer3.changed(mutableInteractionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 512) == 0) {
            if (($changed & 1879048192) == 0) {
                i2 = $composer3.changedInstance(content) ? 536870912 : 268435456;
            }
            if ((1533916891 & $dirty3) == 306783378 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    } else {
                        enabled2 = z;
                    }
                    if ((i & 8) != 0) {
                        $dirty3 &= -7169;
                        shape3 = ButtonDefaults.INSTANCE.getElevatedShape($composer3, 6);
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 16) != 0) {
                        $dirty = $dirty3 & (-57345);
                        colors2 = ButtonDefaults.INSTANCE.m1336elevatedButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    } else {
                        $dirty = $dirty3;
                        colors2 = buttonColors;
                    }
                    if ((i & 32) != 0) {
                        elevation2 = ButtonDefaults.INSTANCE.m1337elevatedButtonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                        $dirty &= -458753;
                    } else {
                        elevation2 = elevation;
                    }
                    if (i7 != 0) {
                        border2 = null;
                    } else {
                        border2 = border;
                    }
                    if (i8 != 0) {
                        contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        it$iv$iv = $composer3.rememberedValue();
                        Modifier modifier4 = modifier2;
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                        elevation3 = elevation2;
                        border3 = border2;
                        contentPadding3 = contentPadding2;
                        $dirty2 = $dirty;
                        colors3 = colors2;
                        modifier3 = modifier4;
                    } else {
                        interactionSource2 = interactionSource;
                        elevation3 = elevation2;
                        border3 = border2;
                        contentPadding3 = contentPadding2;
                        $dirty2 = $dirty;
                        colors3 = colors2;
                        modifier3 = modifier2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty3 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty3 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        modifier3 = modifier;
                        elevation3 = elevation;
                        contentPadding3 = contentPadding;
                        $dirty2 = (-458753) & $dirty3;
                        enabled2 = z;
                        shape3 = shape2;
                        colors3 = buttonColors;
                        border3 = borderStroke;
                        interactionSource2 = mutableInteractionSource;
                    } else {
                        modifier3 = modifier;
                        elevation3 = elevation;
                        contentPadding3 = contentPadding;
                        $dirty2 = $dirty3;
                        enabled2 = z;
                        shape3 = shape2;
                        colors3 = buttonColors;
                        border3 = borderStroke;
                        interactionSource2 = mutableInteractionSource;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1466887385, $dirty2, -1, "androidx.compose.material3.ElevatedButton (Button.kt:195)");
                }
                $composer2 = $composer3;
                Button(onClick, modifier3, enabled2, shape3, colors3, elevation3, border3, contentPadding3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | ($dirty2 & 458752) | ($dirty2 & 3670016) | ($dirty2 & 29360128) | ($dirty2 & 234881024) | ($dirty2 & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier3 = modifier;
                elevation3 = elevation;
                contentPadding3 = contentPadding;
                enabled2 = z;
                shape3 = shape2;
                colors3 = buttonColors;
                border3 = borderStroke;
                interactionSource2 = mutableInteractionSource;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier3;
            final boolean z2 = enabled2;
            final Shape shape4 = shape3;
            final ButtonColors buttonColors2 = colors3;
            final ButtonElevation buttonElevation = elevation3;
            final BorderStroke borderStroke2 = border3;
            final PaddingValues paddingValues = contentPadding3;
            final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.ElevatedButton.2
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

                public final void invoke(Composer composer, int i10) {
                    ButtonKt.ElevatedButton(onClick, modifier5, z2, shape4, buttonColors2, buttonElevation, borderStroke2, paddingValues, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty3 |= i2;
        if ((1533916891 & $dirty3) == 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    shape3 = ButtonDefaults.INSTANCE.getElevatedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = ButtonDefaults.INSTANCE.m1336elevatedButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = buttonColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = ButtonDefaults.INSTANCE.m1337elevatedButtonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    Modifier modifier6 = modifier2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier6;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    shape3 = ButtonDefaults.INSTANCE.getElevatedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = ButtonDefaults.INSTANCE.m1336elevatedButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = buttonColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = ButtonDefaults.INSTANCE.m1337elevatedButtonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    Modifier modifier7 = modifier2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier7;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1466887385, $dirty2, -1, "androidx.compose.material3.ElevatedButton (Button.kt:195)");
            }
            $composer2 = $composer3;
            Button(onClick, modifier3, enabled2, shape3, colors3, elevation3, border3, contentPadding3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | ($dirty2 & 458752) | ($dirty2 & 3670016) | ($dirty2 & 29360128) | ($dirty2 & 234881024) | ($dirty2 & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    shape3 = ButtonDefaults.INSTANCE.getElevatedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = ButtonDefaults.INSTANCE.m1336elevatedButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = buttonColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = ButtonDefaults.INSTANCE.m1337elevatedButtonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    Modifier modifier8 = modifier2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier8;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    shape3 = ButtonDefaults.INSTANCE.getElevatedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = ButtonDefaults.INSTANCE.m1336elevatedButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = buttonColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = ButtonDefaults.INSTANCE.m1337elevatedButtonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    Modifier modifier9 = modifier2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier9;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1466887385, $dirty2, -1, "androidx.compose.material3.ElevatedButton (Button.kt:195)");
            }
            $composer2 = $composer3;
            Button(onClick, modifier3, enabled2, shape3, colors3, elevation3, border3, contentPadding3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | ($dirty2 & 458752) | ($dirty2 & 3670016) | ($dirty2 & 29360128) | ($dirty2 & 234881024) | ($dirty2 & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier10 = modifier3;
        final boolean z3 = enabled2;
        final Shape shape5 = shape3;
        final ButtonColors buttonColors3 = colors3;
        final ButtonElevation buttonElevation2 = elevation3;
        final BorderStroke borderStroke3 = border3;
        final PaddingValues paddingValues2 = contentPadding3;
        final MutableInteractionSource mutableInteractionSource3 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.ElevatedButton.2
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

            public final void invoke(Composer composer, int i10) {
                ButtonKt.ElevatedButton(onClick, modifier10, z3, shape5, buttonColors3, buttonElevation2, borderStroke3, paddingValues2, mutableInteractionSource3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:116:0x017b  */
    /* JADX WARN: Code duplicated, block: B:118:0x0185  */
    /* JADX WARN: Code duplicated, block: B:131:0x01c8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:132:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:133:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:135:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:139:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:140:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:143:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:144:0x0209  */
    /* JADX WARN: Code duplicated, block: B:147:0x0211  */
    /* JADX WARN: Code duplicated, block: B:148:0x022d  */
    /* JADX WARN: Code duplicated, block: B:150:0x0233  */
    /* JADX WARN: Code duplicated, block: B:151:0x0235  */
    /* JADX WARN: Code duplicated, block: B:153:0x0239  */
    /* JADX WARN: Code duplicated, block: B:154:0x0240  */
    /* JADX WARN: Code duplicated, block: B:156:0x0244  */
    /* JADX WARN: Code duplicated, block: B:158:0x0263  */
    /* JADX WARN: Code duplicated, block: B:159:0x026e  */
    /* JADX WARN: Code duplicated, block: B:161:0x0285  */
    /* JADX WARN: Code duplicated, block: B:164:0x029e  */
    /* JADX WARN: Code duplicated, block: B:167:0x02f8  */
    /* JADX WARN: Code duplicated, block: B:171:0x0302  */
    /* JADX WARN: Code duplicated, block: B:173:? A[RETURN, SYNTHETIC] */
    public static final void FilledTonalButton(final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, ButtonColors colors, ButtonElevation elevation, BorderStroke border, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Shape shape2;
        ButtonColors buttonColors;
        BorderStroke borderStroke;
        MutableInteractionSource mutableInteractionSource;
        int i2;
        Modifier.Companion modifier2;
        boolean enabled2;
        Shape shape3;
        int $dirty;
        ButtonColors colors2;
        ButtonElevation elevation2;
        BorderStroke border2;
        PaddingValues contentPadding2;
        MutableInteractionSource interactionSource2;
        ButtonElevation elevation3;
        BorderStroke border3;
        PaddingValues contentPadding3;
        int $dirty2;
        ButtonColors colors3;
        Modifier modifier3;
        Object it$iv$iv;
        Object value$iv$iv;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-1717924381);
        ComposerKt.sourceInformation($composer3, "C(FilledTonalButton)P(8,7,5,9,1,4!1,3,6)269@13745L16,270@13805L25,271@13881L28,274@14062L39,277@14156L314:Button.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty3 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty3 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty3 |= 384;
            z = enabled;
        } else if (($changed & 896) == 0) {
            z = enabled;
            $dirty3 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i5 = $composer3.changed(shape2) ? 2048 : 1024;
                $dirty3 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i5;
        } else {
            shape2 = shape;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                buttonColors = colors;
                int i6 = $composer3.changed(buttonColors) ? 16384 : 8192;
                $dirty3 |= i6;
            } else {
                buttonColors = colors;
            }
            $dirty3 |= i6;
        } else {
            buttonColors = colors;
        }
        if (($changed & 458752) == 0) {
            $dirty3 |= ((i & 32) == 0 && $composer3.changed(elevation)) ? 131072 : 65536;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty3 |= 1572864;
            borderStroke = border;
        } else if (($changed & 3670016) == 0) {
            borderStroke = border;
            $dirty3 |= $composer3.changed(borderStroke) ? 1048576 : 524288;
        } else {
            borderStroke = border;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty3 |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty3 |= 100663296;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 234881024) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty3 |= $composer3.changed(mutableInteractionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 512) == 0) {
            if (($changed & 1879048192) == 0) {
                i2 = $composer3.changedInstance(content) ? 536870912 : 268435456;
            }
            if ((1533916891 & $dirty3) == 306783378 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    } else {
                        enabled2 = z;
                    }
                    if ((i & 8) != 0) {
                        $dirty3 &= -7169;
                        shape3 = ButtonDefaults.INSTANCE.getFilledTonalShape($composer3, 6);
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 16) != 0) {
                        $dirty = $dirty3 & (-57345);
                        colors2 = ButtonDefaults.INSTANCE.m1338filledTonalButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    } else {
                        $dirty = $dirty3;
                        colors2 = buttonColors;
                    }
                    if ((i & 32) != 0) {
                        elevation2 = ButtonDefaults.INSTANCE.m1339filledTonalButtonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                        $dirty &= -458753;
                    } else {
                        elevation2 = elevation;
                    }
                    if (i7 != 0) {
                        border2 = null;
                    } else {
                        border2 = border;
                    }
                    if (i8 != 0) {
                        contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        it$iv$iv = $composer3.rememberedValue();
                        Modifier modifier4 = modifier2;
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                        elevation3 = elevation2;
                        border3 = border2;
                        contentPadding3 = contentPadding2;
                        $dirty2 = $dirty;
                        colors3 = colors2;
                        modifier3 = modifier4;
                    } else {
                        interactionSource2 = interactionSource;
                        elevation3 = elevation2;
                        border3 = border2;
                        contentPadding3 = contentPadding2;
                        $dirty2 = $dirty;
                        colors3 = colors2;
                        modifier3 = modifier2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty3 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty3 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        modifier3 = modifier;
                        elevation3 = elevation;
                        contentPadding3 = contentPadding;
                        $dirty2 = (-458753) & $dirty3;
                        enabled2 = z;
                        shape3 = shape2;
                        colors3 = buttonColors;
                        border3 = borderStroke;
                        interactionSource2 = mutableInteractionSource;
                    } else {
                        modifier3 = modifier;
                        elevation3 = elevation;
                        contentPadding3 = contentPadding;
                        $dirty2 = $dirty3;
                        enabled2 = z;
                        shape3 = shape2;
                        colors3 = buttonColors;
                        border3 = borderStroke;
                        interactionSource2 = mutableInteractionSource;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1717924381, $dirty2, -1, "androidx.compose.material3.FilledTonalButton (Button.kt:265)");
                }
                $composer2 = $composer3;
                Button(onClick, modifier3, enabled2, shape3, colors3, elevation3, border3, contentPadding3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | ($dirty2 & 458752) | ($dirty2 & 3670016) | ($dirty2 & 29360128) | ($dirty2 & 234881024) | ($dirty2 & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier3 = modifier;
                elevation3 = elevation;
                contentPadding3 = contentPadding;
                enabled2 = z;
                shape3 = shape2;
                colors3 = buttonColors;
                border3 = borderStroke;
                interactionSource2 = mutableInteractionSource;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier3;
            final boolean z2 = enabled2;
            final Shape shape4 = shape3;
            final ButtonColors buttonColors2 = colors3;
            final ButtonElevation buttonElevation = elevation3;
            final BorderStroke borderStroke2 = border3;
            final PaddingValues paddingValues = contentPadding3;
            final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.FilledTonalButton.2
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

                public final void invoke(Composer composer, int i10) {
                    ButtonKt.FilledTonalButton(onClick, modifier5, z2, shape4, buttonColors2, buttonElevation, borderStroke2, paddingValues, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty3 |= i2;
        if ((1533916891 & $dirty3) == 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    shape3 = ButtonDefaults.INSTANCE.getFilledTonalShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = ButtonDefaults.INSTANCE.m1338filledTonalButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = buttonColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = ButtonDefaults.INSTANCE.m1339filledTonalButtonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    Modifier modifier6 = modifier2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier6;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    shape3 = ButtonDefaults.INSTANCE.getFilledTonalShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = ButtonDefaults.INSTANCE.m1338filledTonalButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = buttonColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = ButtonDefaults.INSTANCE.m1339filledTonalButtonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    Modifier modifier7 = modifier2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier7;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1717924381, $dirty2, -1, "androidx.compose.material3.FilledTonalButton (Button.kt:265)");
            }
            $composer2 = $composer3;
            Button(onClick, modifier3, enabled2, shape3, colors3, elevation3, border3, contentPadding3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | ($dirty2 & 458752) | ($dirty2 & 3670016) | ($dirty2 & 29360128) | ($dirty2 & 234881024) | ($dirty2 & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    shape3 = ButtonDefaults.INSTANCE.getFilledTonalShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = ButtonDefaults.INSTANCE.m1338filledTonalButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = buttonColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = ButtonDefaults.INSTANCE.m1339filledTonalButtonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    Modifier modifier8 = modifier2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier8;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    shape3 = ButtonDefaults.INSTANCE.getFilledTonalShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = ButtonDefaults.INSTANCE.m1338filledTonalButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = buttonColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = ButtonDefaults.INSTANCE.m1339filledTonalButtonElevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    Modifier modifier9 = modifier2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier9;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    contentPadding3 = contentPadding2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1717924381, $dirty2, -1, "androidx.compose.material3.FilledTonalButton (Button.kt:265)");
            }
            $composer2 = $composer3;
            Button(onClick, modifier3, enabled2, shape3, colors3, elevation3, border3, contentPadding3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | ($dirty2 & 458752) | ($dirty2 & 3670016) | ($dirty2 & 29360128) | ($dirty2 & 234881024) | ($dirty2 & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier10 = modifier3;
        final boolean z3 = enabled2;
        final Shape shape5 = shape3;
        final ButtonColors buttonColors3 = colors3;
        final ButtonElevation buttonElevation2 = elevation3;
        final BorderStroke borderStroke3 = border3;
        final PaddingValues paddingValues2 = contentPadding3;
        final MutableInteractionSource mutableInteractionSource3 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.FilledTonalButton.2
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

            public final void invoke(Composer composer, int i10) {
                ButtonKt.FilledTonalButton(onClick, modifier10, z3, shape5, buttonColors3, buttonElevation2, borderStroke3, paddingValues2, mutableInteractionSource3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:116:0x0177  */
    /* JADX WARN: Code duplicated, block: B:118:0x017e  */
    /* JADX WARN: Code duplicated, block: B:131:0x01c4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:132:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:133:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:135:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:139:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:140:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:143:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:145:0x0202  */
    /* JADX WARN: Code duplicated, block: B:146:0x0204  */
    /* JADX WARN: Code duplicated, block: B:149:0x0209  */
    /* JADX WARN: Code duplicated, block: B:150:0x0214  */
    /* JADX WARN: Code duplicated, block: B:152:0x0217  */
    /* JADX WARN: Code duplicated, block: B:153:0x021e  */
    /* JADX WARN: Code duplicated, block: B:155:0x0222  */
    /* JADX WARN: Code duplicated, block: B:157:0x0246  */
    /* JADX WARN: Code duplicated, block: B:158:0x0255  */
    /* JADX WARN: Code duplicated, block: B:160:0x0276  */
    /* JADX WARN: Code duplicated, block: B:163:0x0295  */
    /* JADX WARN: Code duplicated, block: B:166:0x02f0  */
    /* JADX WARN: Code duplicated, block: B:170:0x02fa  */
    /* JADX WARN: Code duplicated, block: B:172:? A[RETURN, SYNTHETIC] */
    public static final void OutlinedButton(final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, ButtonColors colors, ButtonElevation elevation, BorderStroke border, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        ButtonColors colors2;
        ButtonElevation buttonElevation;
        BorderStroke borderStroke;
        int i2;
        Modifier.Companion modifier2;
        boolean enabled2;
        Shape shape2;
        ButtonElevation elevation2;
        BorderStroke border2;
        PaddingValues contentPadding2;
        PaddingValues contentPadding3;
        int $dirty;
        MutableInteractionSource interactionSource2;
        Modifier modifier3;
        boolean enabled3;
        Shape shape3;
        BorderStroke border3;
        ButtonColors colors3;
        ButtonElevation elevation3;
        Object it$iv$iv;
        Object value$iv$iv;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-1694808287);
        ComposerKt.sourceInformation($composer3, "C(OutlinedButton)P(8,7,5,9,1,4!1,3,6)338@17289L13,339@17346L22,341@17453L20,343@17592L39,346@17686L314:Button.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty2 |= ((i & 8) == 0 && $composer3.changed(shape)) ? 2048 : 1024;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                int i5 = $composer3.changed(colors2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i5;
        } else {
            colors2 = colors;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            buttonElevation = elevation;
        } else if (($changed & 458752) == 0) {
            buttonElevation = elevation;
            $dirty2 |= $composer3.changed(buttonElevation) ? 131072 : 65536;
        } else {
            buttonElevation = elevation;
        }
        if (($changed & 3670016) == 0) {
            if ((i & 64) == 0) {
                borderStroke = border;
                int i7 = $composer3.changed(borderStroke) ? 1048576 : 524288;
                $dirty2 |= i7;
            } else {
                borderStroke = border;
            }
            $dirty2 |= i7;
        } else {
            borderStroke = border;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty2 |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) == 0) {
            if (($changed & 1879048192) == 0) {
                i2 = $composer3.changedInstance(content) ? 536870912 : 268435456;
            }
            if ((1533916891 & $dirty2) == 306783378 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    } else {
                        enabled2 = enabled;
                    }
                    if ((i & 8) != 0) {
                        shape2 = ButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                        $dirty2 &= -7169;
                    } else {
                        shape2 = shape;
                    }
                    if ((i & 16) != 0) {
                        colors2 = ButtonDefaults.INSTANCE.m1344outlinedButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                        $dirty2 &= -57345;
                    }
                    if (i6 != 0) {
                        elevation2 = null;
                    } else {
                        elevation2 = buttonElevation;
                    }
                    if ((i & 64) != 0) {
                        border2 = ButtonDefaults.INSTANCE.getOutlinedButtonBorder($composer3, 6);
                        $dirty2 &= -3670017;
                    } else {
                        border2 = borderStroke;
                    }
                    if (i8 != 0) {
                        contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        PaddingValues contentPadding4 = contentPadding2;
                        it$iv$iv = $composer3.rememberedValue();
                        int $dirty3 = $dirty2;
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        contentPadding3 = contentPadding4;
                        $dirty = $dirty3;
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                        modifier3 = modifier2;
                        enabled3 = enabled2;
                        shape3 = shape2;
                        border3 = border2;
                        colors3 = colors2;
                        elevation3 = elevation2;
                    } else {
                        contentPadding3 = contentPadding2;
                        $dirty = $dirty2;
                        interactionSource2 = interactionSource;
                        modifier3 = modifier2;
                        enabled3 = enabled2;
                        shape3 = shape2;
                        border3 = border2;
                        colors3 = colors2;
                        elevation3 = elevation2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 64) != 0) {
                        modifier3 = modifier;
                        enabled3 = enabled;
                        shape3 = shape;
                        contentPadding3 = contentPadding;
                        interactionSource2 = interactionSource;
                        border3 = borderStroke;
                        colors3 = colors2;
                        elevation3 = buttonElevation;
                        $dirty = (-3670017) & $dirty2;
                    } else {
                        modifier3 = modifier;
                        enabled3 = enabled;
                        shape3 = shape;
                        contentPadding3 = contentPadding;
                        interactionSource2 = interactionSource;
                        border3 = borderStroke;
                        colors3 = colors2;
                        elevation3 = buttonElevation;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1694808287, $dirty, -1, "androidx.compose.material3.OutlinedButton (Button.kt:334)");
                }
                $composer2 = $composer3;
                Button(onClick, modifier3, enabled3, shape3, colors3, elevation3, border3, contentPadding3, interactionSource2, content, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | ($dirty & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier3 = modifier;
                enabled3 = enabled;
                shape3 = shape;
                contentPadding3 = contentPadding;
                interactionSource2 = interactionSource;
                border3 = borderStroke;
                colors3 = colors2;
                elevation3 = buttonElevation;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final boolean z = enabled3;
            final Shape shape4 = shape3;
            final ButtonColors buttonColors = colors3;
            final ButtonElevation buttonElevation2 = elevation3;
            final BorderStroke borderStroke2 = border3;
            final PaddingValues paddingValues = contentPadding3;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.OutlinedButton.2
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

                public final void invoke(Composer composer, int i10) {
                    ButtonKt.OutlinedButton(onClick, modifier4, z, shape4, buttonColors, buttonElevation2, borderStroke2, paddingValues, mutableInteractionSource, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty2 |= i2;
        if ((1533916891 & $dirty2) == 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = enabled;
                }
                if ((i & 8) != 0) {
                    shape2 = ButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape2 = shape;
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1344outlinedButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                }
                if (i6 != 0) {
                    elevation2 = null;
                } else {
                    elevation2 = buttonElevation;
                }
                if ((i & 64) != 0) {
                    border2 = ButtonDefaults.INSTANCE.getOutlinedButtonBorder($composer3, 6);
                    $dirty2 &= -3670017;
                } else {
                    border2 = borderStroke;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    PaddingValues contentPadding5 = contentPadding2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty4 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    contentPadding3 = contentPadding5;
                    $dirty = $dirty4;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    border3 = border2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                } else {
                    contentPadding3 = contentPadding2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    border3 = border2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = enabled;
                }
                if ((i & 8) != 0) {
                    shape2 = ButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape2 = shape;
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1344outlinedButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                }
                if (i6 != 0) {
                    elevation2 = null;
                } else {
                    elevation2 = buttonElevation;
                }
                if ((i & 64) != 0) {
                    border2 = ButtonDefaults.INSTANCE.getOutlinedButtonBorder($composer3, 6);
                    $dirty2 &= -3670017;
                } else {
                    border2 = borderStroke;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    PaddingValues contentPadding6 = contentPadding2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty5 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    contentPadding3 = contentPadding6;
                    $dirty = $dirty5;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    border3 = border2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                } else {
                    contentPadding3 = contentPadding2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    border3 = border2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1694808287, $dirty, -1, "androidx.compose.material3.OutlinedButton (Button.kt:334)");
            }
            $composer2 = $composer3;
            Button(onClick, modifier3, enabled3, shape3, colors3, elevation3, border3, contentPadding3, interactionSource2, content, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | ($dirty & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = enabled;
                }
                if ((i & 8) != 0) {
                    shape2 = ButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape2 = shape;
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1344outlinedButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                }
                if (i6 != 0) {
                    elevation2 = null;
                } else {
                    elevation2 = buttonElevation;
                }
                if ((i & 64) != 0) {
                    border2 = ButtonDefaults.INSTANCE.getOutlinedButtonBorder($composer3, 6);
                    $dirty2 &= -3670017;
                } else {
                    border2 = borderStroke;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    PaddingValues contentPadding7 = contentPadding2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty6 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    contentPadding3 = contentPadding7;
                    $dirty = $dirty6;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    border3 = border2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                } else {
                    contentPadding3 = contentPadding2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    border3 = border2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = enabled;
                }
                if ((i & 8) != 0) {
                    shape2 = ButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape2 = shape;
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1344outlinedButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                }
                if (i6 != 0) {
                    elevation2 = null;
                } else {
                    elevation2 = buttonElevation;
                }
                if ((i & 64) != 0) {
                    border2 = ButtonDefaults.INSTANCE.getOutlinedButtonBorder($composer3, 6);
                    $dirty2 &= -3670017;
                } else {
                    border2 = borderStroke;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    PaddingValues contentPadding8 = contentPadding2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty7 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    contentPadding3 = contentPadding8;
                    $dirty = $dirty7;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    border3 = border2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                } else {
                    contentPadding3 = contentPadding2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    border3 = border2;
                    colors3 = colors2;
                    elevation3 = elevation2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1694808287, $dirty, -1, "androidx.compose.material3.OutlinedButton (Button.kt:334)");
            }
            $composer2 = $composer3;
            Button(onClick, modifier3, enabled3, shape3, colors3, elevation3, border3, contentPadding3, interactionSource2, content, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | ($dirty & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final boolean z2 = enabled3;
        final Shape shape5 = shape3;
        final ButtonColors buttonColors2 = colors3;
        final ButtonElevation buttonElevation3 = elevation3;
        final BorderStroke borderStroke3 = border3;
        final PaddingValues paddingValues2 = contentPadding3;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.OutlinedButton.2
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

            public final void invoke(Composer composer, int i10) {
                ButtonKt.OutlinedButton(onClick, modifier5, z2, shape5, buttonColors2, buttonElevation3, borderStroke3, paddingValues2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:116:0x017c  */
    /* JADX WARN: Code duplicated, block: B:118:0x0186  */
    /* JADX WARN: Code duplicated, block: B:128:0x01c2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:129:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:130:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:132:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:133:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:137:0x01df  */
    /* JADX WARN: Code duplicated, block: B:140:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:141:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:143:0x0201  */
    /* JADX WARN: Code duplicated, block: B:144:0x0203  */
    /* JADX WARN: Code duplicated, block: B:146:0x0206  */
    /* JADX WARN: Code duplicated, block: B:148:0x0209  */
    /* JADX WARN: Code duplicated, block: B:149:0x0210  */
    /* JADX WARN: Code duplicated, block: B:151:0x0214  */
    /* JADX WARN: Code duplicated, block: B:153:0x0238  */
    /* JADX WARN: Code duplicated, block: B:154:0x0247  */
    /* JADX WARN: Code duplicated, block: B:156:0x0268  */
    /* JADX WARN: Code duplicated, block: B:159:0x0287  */
    /* JADX WARN: Code duplicated, block: B:162:0x02e2  */
    /* JADX WARN: Code duplicated, block: B:166:0x02ec  */
    /* JADX WARN: Code duplicated, block: B:168:? A[RETURN, SYNTHETIC] */
    public static final void TextButton(final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, ButtonColors colors, ButtonElevation elevation, BorderStroke border, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        ButtonColors buttonColors;
        ButtonElevation buttonElevation;
        BorderStroke border2;
        int i2;
        Modifier.Companion modifier2;
        boolean enabled2;
        Shape shape2;
        ButtonColors colors2;
        ButtonElevation elevation2;
        PaddingValues contentPadding2;
        PaddingValues contentPadding3;
        int $dirty;
        MutableInteractionSource interactionSource2;
        Modifier modifier3;
        boolean enabled3;
        Shape shape3;
        ButtonColors colors3;
        BorderStroke border3;
        ButtonElevation elevation3;
        Object it$iv$iv;
        Object value$iv$iv;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-2106428362);
        ComposerKt.sourceInformation($composer3, "C(TextButton)P(8,7,5,9,1,4!1,3,6)409@20955L9,410@21008L18,414@21229L39,417@21323L314:Button.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty2 |= ((i & 8) == 0 && $composer3.changed(shape)) ? 2048 : 1024;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                buttonColors = colors;
                int i5 = $composer3.changed(buttonColors) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                buttonColors = colors;
            }
            $dirty2 |= i5;
        } else {
            buttonColors = colors;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            buttonElevation = elevation;
        } else if (($changed & 458752) == 0) {
            buttonElevation = elevation;
            $dirty2 |= $composer3.changed(buttonElevation) ? 131072 : 65536;
        } else {
            buttonElevation = elevation;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            border2 = border;
        } else if (($changed & 3670016) == 0) {
            border2 = border;
            $dirty2 |= $composer3.changed(border2) ? 1048576 : 524288;
        } else {
            border2 = border;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty2 |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) == 0) {
            if (($changed & 1879048192) == 0) {
                i2 = $composer3.changedInstance(content) ? 536870912 : 268435456;
            }
            if ((1533916891 & $dirty2) == 306783378 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    } else {
                        enabled2 = enabled;
                    }
                    if ((i & 8) != 0) {
                        shape2 = ButtonDefaults.INSTANCE.getTextShape($composer3, 6);
                        $dirty2 &= -7169;
                    } else {
                        shape2 = shape;
                    }
                    if ((i & 16) != 0) {
                        colors2 = ButtonDefaults.INSTANCE.m1345textButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                        $dirty2 &= -57345;
                    } else {
                        colors2 = buttonColors;
                    }
                    if (i6 != 0) {
                        elevation2 = null;
                    } else {
                        elevation2 = buttonElevation;
                    }
                    if (i7 != 0) {
                        border2 = null;
                    }
                    if (i8 != 0) {
                        contentPadding2 = ButtonDefaults.INSTANCE.getTextButtonContentPadding();
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i9 != 0) {
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        PaddingValues contentPadding4 = contentPadding2;
                        it$iv$iv = $composer3.rememberedValue();
                        int $dirty3 = $dirty2;
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        contentPadding3 = contentPadding4;
                        $dirty = $dirty3;
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                        modifier3 = modifier2;
                        enabled3 = enabled2;
                        shape3 = shape2;
                        colors3 = colors2;
                        border3 = border2;
                        elevation3 = elevation2;
                    } else {
                        contentPadding3 = contentPadding2;
                        $dirty = $dirty2;
                        interactionSource2 = interactionSource;
                        modifier3 = modifier2;
                        enabled3 = enabled2;
                        shape3 = shape2;
                        colors3 = colors2;
                        border3 = border2;
                        elevation3 = elevation2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        modifier3 = modifier;
                        enabled3 = enabled;
                        shape3 = shape;
                        contentPadding3 = contentPadding;
                        interactionSource2 = interactionSource;
                        border3 = border2;
                        colors3 = buttonColors;
                        elevation3 = buttonElevation;
                        $dirty = $dirty2 & (-57345);
                    } else {
                        modifier3 = modifier;
                        enabled3 = enabled;
                        shape3 = shape;
                        contentPadding3 = contentPadding;
                        interactionSource2 = interactionSource;
                        border3 = border2;
                        colors3 = buttonColors;
                        elevation3 = buttonElevation;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2106428362, $dirty, -1, "androidx.compose.material3.TextButton (Button.kt:405)");
                }
                $composer2 = $composer3;
                Button(onClick, modifier3, enabled3, shape3, colors3, elevation3, border3, contentPadding3, interactionSource2, content, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | ($dirty & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier3 = modifier;
                enabled3 = enabled;
                shape3 = shape;
                contentPadding3 = contentPadding;
                interactionSource2 = interactionSource;
                border3 = border2;
                colors3 = buttonColors;
                elevation3 = buttonElevation;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final boolean z = enabled3;
            final Shape shape4 = shape3;
            final ButtonColors buttonColors2 = colors3;
            final ButtonElevation buttonElevation2 = elevation3;
            final BorderStroke borderStroke = border3;
            final PaddingValues paddingValues = contentPadding3;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.TextButton.2
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

                public final void invoke(Composer composer, int i10) {
                    ButtonKt.TextButton(onClick, modifier4, z, shape4, buttonColors2, buttonElevation2, borderStroke, paddingValues, mutableInteractionSource, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty2 |= i2;
        if ((1533916891 & $dirty2) == 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = enabled;
                }
                if ((i & 8) != 0) {
                    shape2 = ButtonDefaults.INSTANCE.getTextShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape2 = shape;
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1345textButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = buttonColors;
                }
                if (i6 != 0) {
                    elevation2 = null;
                } else {
                    elevation2 = buttonElevation;
                }
                if (i7 != 0) {
                    border2 = null;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getTextButtonContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    PaddingValues contentPadding5 = contentPadding2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty4 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    contentPadding3 = contentPadding5;
                    $dirty = $dirty4;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    elevation3 = elevation2;
                } else {
                    contentPadding3 = contentPadding2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    elevation3 = elevation2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = enabled;
                }
                if ((i & 8) != 0) {
                    shape2 = ButtonDefaults.INSTANCE.getTextShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape2 = shape;
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1345textButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = buttonColors;
                }
                if (i6 != 0) {
                    elevation2 = null;
                } else {
                    elevation2 = buttonElevation;
                }
                if (i7 != 0) {
                    border2 = null;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getTextButtonContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    PaddingValues contentPadding6 = contentPadding2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty5 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    contentPadding3 = contentPadding6;
                    $dirty = $dirty5;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    elevation3 = elevation2;
                } else {
                    contentPadding3 = contentPadding2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    elevation3 = elevation2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2106428362, $dirty, -1, "androidx.compose.material3.TextButton (Button.kt:405)");
            }
            $composer2 = $composer3;
            Button(onClick, modifier3, enabled3, shape3, colors3, elevation3, border3, contentPadding3, interactionSource2, content, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | ($dirty & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = enabled;
                }
                if ((i & 8) != 0) {
                    shape2 = ButtonDefaults.INSTANCE.getTextShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape2 = shape;
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1345textButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = buttonColors;
                }
                if (i6 != 0) {
                    elevation2 = null;
                } else {
                    elevation2 = buttonElevation;
                }
                if (i7 != 0) {
                    border2 = null;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getTextButtonContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    PaddingValues contentPadding7 = contentPadding2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty6 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    contentPadding3 = contentPadding7;
                    $dirty = $dirty6;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    elevation3 = elevation2;
                } else {
                    contentPadding3 = contentPadding2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    elevation3 = elevation2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = enabled;
                }
                if ((i & 8) != 0) {
                    shape2 = ButtonDefaults.INSTANCE.getTextShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape2 = shape;
                }
                if ((i & 16) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1345textButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = buttonColors;
                }
                if (i6 != 0) {
                    elevation2 = null;
                } else {
                    elevation2 = buttonElevation;
                }
                if (i7 != 0) {
                    border2 = null;
                }
                if (i8 != 0) {
                    contentPadding2 = ButtonDefaults.INSTANCE.getTextButtonContentPadding();
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    PaddingValues contentPadding8 = contentPadding2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty7 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    contentPadding3 = contentPadding8;
                    $dirty = $dirty7;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    elevation3 = elevation2;
                } else {
                    contentPadding3 = contentPadding2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    elevation3 = elevation2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2106428362, $dirty, -1, "androidx.compose.material3.TextButton (Button.kt:405)");
            }
            $composer2 = $composer3;
            Button(onClick, modifier3, enabled3, shape3, colors3, elevation3, border3, contentPadding3, interactionSource2, content, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | ($dirty & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final boolean z2 = enabled3;
        final Shape shape5 = shape3;
        final ButtonColors buttonColors3 = colors3;
        final ButtonElevation buttonElevation3 = elevation3;
        final BorderStroke borderStroke2 = border3;
        final PaddingValues paddingValues2 = contentPadding3;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ButtonKt.TextButton.2
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

            public final void invoke(Composer composer, int i10) {
                ButtonKt.TextButton(onClick, modifier5, z2, shape5, buttonColors3, buttonElevation3, borderStroke2, paddingValues2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }
}
