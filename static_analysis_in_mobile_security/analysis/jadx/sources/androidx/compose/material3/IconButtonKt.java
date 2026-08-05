package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.material3.tokens.FilledIconButtonTokens;
import androidx.compose.material3.tokens.FilledTonalIconButtonTokens;
import androidx.compose.material3.tokens.IconButtonTokens;
import androidx.compose.material3.tokens.OutlinedIconButtonTokens;
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
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
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

/* JADX INFO: compiled from: IconButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a`\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001an\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00072\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00152\b\b\u0002\u0010\f\u001a\u00020\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u0016\u001a`\u0010\u0017\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001an\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00072\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00152\b\b\u0002\u0010\f\u001a\u00020\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u0016\u001aV\u0010\u0019\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u001a\u001ad\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00072\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00152\b\b\u0002\u0010\f\u001a\u00020\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u001c\u001al\u0010\u001d\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010\f\u001a\u00020\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010 \u001az\u0010!\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00072\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00152\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010\f\u001a\u00020\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\"¨\u0006#"}, d2 = {"FilledIconButton", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/IconButtonColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/IconButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "FilledIconToggleButton", "checked", "onCheckedChange", "Lkotlin/Function1;", "Landroidx/compose/material3/IconToggleButtonColors;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/IconToggleButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "FilledTonalIconButton", "FilledTonalIconToggleButton", "IconButton", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/IconButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "IconToggleButton", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/IconToggleButtonColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "OutlinedIconButton", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/IconButtonColors;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "OutlinedIconToggleButton", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/IconToggleButtonColors;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IconButtonKt {
    /* JADX WARN: Code duplicated, block: B:100:0x016f  */
    /* JADX WARN: Code duplicated, block: B:103:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:106:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:107:0x02b5  */
    /* JADX WARN: Code duplicated, block: B:110:0x038a  */
    /* JADX WARN: Code duplicated, block: B:113:0x0393  */
    /* JADX WARN: Code duplicated, block: B:114:0x0396  */
    /* JADX WARN: Code duplicated, block: B:72:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:74:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:81:0x00fe A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:82:0x0100  */
    /* JADX WARN: Code duplicated, block: B:83:0x0105  */
    /* JADX WARN: Code duplicated, block: B:85:0x0108  */
    /* JADX WARN: Code duplicated, block: B:86:0x010a  */
    /* JADX WARN: Code duplicated, block: B:89:0x010f  */
    /* JADX WARN: Code duplicated, block: B:90:0x0127  */
    /* JADX WARN: Code duplicated, block: B:92:0x012a  */
    /* JADX WARN: Code duplicated, block: B:94:0x0147  */
    /* JADX WARN: Code duplicated, block: B:95:0x0152  */
    /* JADX WARN: Code duplicated, block: B:97:0x0160  */
    public static final void IconButton(final Function0<Unit> onClick, Modifier modifier, boolean enabled, IconButtonColors colors, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        IconButtonColors iconButtonColors;
        MutableInteractionSource interactionSource2;
        int i2;
        Modifier.Companion modifier3;
        boolean enabled2;
        IconButtonColors colors2;
        int $dirty;
        Modifier modifier4;
        boolean enabled3;
        IconButtonColors colors3;
        Object it$iv$iv;
        Object value$iv$iv;
        Composer $composer2;
        Function0<ComposeUiNode> constructor;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-1142896114);
        ComposerKt.sourceInformation($composer3, "C(IconButton)P(5,4,2!1,3)77@3836L18,78@3906L39,85@4173L9,86@4223L23,92@4468L135,81@3991L840:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
            z = enabled;
        } else if (($changed & 896) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                iconButtonColors = colors;
                int i5 = $composer3.changed(iconButtonColors) ? 2048 : 1024;
                $dirty2 |= i5;
            } else {
                iconButtonColors = colors;
            }
            $dirty2 |= i5;
        } else {
            iconButtonColors = colors;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty2 |= 24576;
            interactionSource2 = interactionSource;
        } else if ((57344 & $changed) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer3.changed(interactionSource2) ? 16384 : 8192;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((i & 32) == 0) {
            if ((458752 & $changed) == 0) {
                i2 = $composer3.changedInstance(content) ? 131072 : 65536;
            }
            if ((374491 & $dirty2) == 74898 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    } else {
                        enabled2 = z;
                    }
                    if ((i & 8) != 0) {
                        colors2 = IconButtonDefaults.INSTANCE.m1562iconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                        $dirty2 &= -7169;
                    } else {
                        colors2 = iconButtonColors;
                    }
                    if (i6 != 0) {
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        it$iv$iv = $composer3.rememberedValue();
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                        $dirty = $dirty2;
                        modifier4 = modifier3;
                        enabled3 = enabled2;
                        colors3 = colors2;
                    } else {
                        interactionSource2 = interactionSource;
                        $dirty = $dirty2;
                        modifier4 = modifier3;
                        enabled3 = enabled2;
                        colors3 = colors2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                    enabled3 = z;
                    colors3 = iconButtonColors;
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1142896114, $dirty, -1, "androidx.compose.material3.IconButton (IconButton.kt:73)");
                }
                Modifier modifierM160backgroundbw27NRU$default = BackgroundKt.m160backgroundbw27NRU$default(ClipKt.clip(SizeKt.m534size3ABfNKs(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier4), IconButtonTokens.INSTANCE.m2267getStateLayerSizeD9Ej5fM()), ShapesKt.toShape(IconButtonTokens.INSTANCE.getStateLayerShape(), $composer3, 6)), colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 6) & 112)).getValue().m2981unboximpl(), null, 2, null);
                int iM4610getButtono7Vup1c = Role.INSTANCE.m4610getButtono7Vup1c();
                float arg0$iv = IconButtonTokens.INSTANCE.m2267getStateLayerSizeD9Ej5fM();
                int $dirty3 = $dirty;
                $composer2 = $composer3;
                Modifier modifier$iv = ClickableKt.m190clickableO2vRcR0(modifierM160backgroundbw27NRU$default, interactionSource2, RippleKt.m1298rememberRipple9IZ8Weo(false, Dp.m5274constructorimpl(arg0$iv / 2), 0L, $composer3, 54, 4), (24 & 4) != 0 ? true : enabled3, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : Role.m4603boximpl(iM4610getButtono7Vup1c), onClick);
                Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                int $changed$iv$iv = (48 << 3) & 112;
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density$iv$iv = (Density) objConsume;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer2.consume(localViewConfiguration);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor);
                } else {
                    $composer2.useNode();
                }
                $composer2.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer2);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer2.enableReusing();
                function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i7 = ($changed$iv$iv$iv >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i8 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1676789754, "C99@4705L21,100@4741L84:IconButton.kt#uh7d8r");
                long contentColor = colors3.contentColor$material3_release(enabled3, $composer2, (($dirty3 >> 6) & 14) | (($dirty3 >> 6) & 112)).getValue().m2981unboximpl();
                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor))}, content, $composer2, (($dirty3 >> 12) & 112) | 8);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier4 = modifier2;
                enabled3 = z;
                colors3 = iconButtonColors;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final boolean z2 = enabled3;
            final IconButtonColors iconButtonColors2 = colors3;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.IconButton.3
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

                public final void invoke(Composer composer, int i9) {
                    IconButtonKt.IconButton(onClick, modifier5, z2, iconButtonColors2, mutableInteractionSource, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty2 |= i2;
        if ((374491 & $dirty2) == 74898) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1562iconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -7169;
                } else {
                    colors2 = iconButtonColors;
                }
                if (i6 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    $dirty = $dirty2;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                } else {
                    interactionSource2 = interactionSource;
                    $dirty = $dirty2;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1562iconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -7169;
                } else {
                    colors2 = iconButtonColors;
                }
                if (i6 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    $dirty = $dirty2;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                } else {
                    interactionSource2 = interactionSource;
                    $dirty = $dirty2;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1142896114, $dirty, -1, "androidx.compose.material3.IconButton (IconButton.kt:73)");
            }
            Modifier modifierM160backgroundbw27NRU$default2 = BackgroundKt.m160backgroundbw27NRU$default(ClipKt.clip(SizeKt.m534size3ABfNKs(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier4), IconButtonTokens.INSTANCE.m2267getStateLayerSizeD9Ej5fM()), ShapesKt.toShape(IconButtonTokens.INSTANCE.getStateLayerShape(), $composer3, 6)), colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 6) & 112)).getValue().m2981unboximpl(), null, 2, null);
            int iM4610getButtono7Vup1c2 = Role.INSTANCE.m4610getButtono7Vup1c();
            float arg0$iv2 = IconButtonTokens.INSTANCE.m2267getStateLayerSizeD9Ej5fM();
            int $dirty4 = $dirty;
            $composer2 = $composer3;
            Modifier modifier$iv2 = ClickableKt.m190clickableO2vRcR0(modifierM160backgroundbw27NRU$default2, interactionSource2, RippleKt.m1298rememberRipple9IZ8Weo(false, Dp.m5274constructorimpl(arg0$iv2 / 2), 0L, $composer3, 54, 4), (24 & 4) != 0 ? true : enabled3, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : Role.m4603boximpl(iM4610getButtono7Vup1c2), onClick);
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv2 = (48 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer2.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv2 = (Density) objConsume4;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume5 = $composer2.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer2.consume(localViewConfiguration2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i9 = ($changed$iv$iv$iv2 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i10 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1676789754, "C99@4705L21,100@4741L84:IconButton.kt#uh7d8r");
            long contentColor2 = colors3.contentColor$material3_release(enabled3, $composer2, (($dirty4 >> 6) & 14) | (($dirty4 >> 6) & 112)).getValue().m2981unboximpl();
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor2))}, content, $composer2, (($dirty4 >> 12) & 112) | 8);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1562iconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -7169;
                } else {
                    colors2 = iconButtonColors;
                }
                if (i6 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    $dirty = $dirty2;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                } else {
                    interactionSource2 = interactionSource;
                    $dirty = $dirty2;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1562iconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -7169;
                } else {
                    colors2 = iconButtonColors;
                }
                if (i6 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    $dirty = $dirty2;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                } else {
                    interactionSource2 = interactionSource;
                    $dirty = $dirty2;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1142896114, $dirty, -1, "androidx.compose.material3.IconButton (IconButton.kt:73)");
            }
            Modifier modifierM160backgroundbw27NRU$default3 = BackgroundKt.m160backgroundbw27NRU$default(ClipKt.clip(SizeKt.m534size3ABfNKs(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier4), IconButtonTokens.INSTANCE.m2267getStateLayerSizeD9Ej5fM()), ShapesKt.toShape(IconButtonTokens.INSTANCE.getStateLayerShape(), $composer3, 6)), colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 6) & 112)).getValue().m2981unboximpl(), null, 2, null);
            int iM4610getButtono7Vup1c3 = Role.INSTANCE.m4610getButtono7Vup1c();
            float arg0$iv3 = IconButtonTokens.INSTANCE.m2267getStateLayerSizeD9Ej5fM();
            int $dirty5 = $dirty;
            $composer2 = $composer3;
            Modifier modifier$iv3 = ClickableKt.m190clickableO2vRcR0(modifierM160backgroundbw27NRU$default3, interactionSource2, RippleKt.m1298rememberRipple9IZ8Weo(false, Dp.m5274constructorimpl(arg0$iv3 / 2), 0L, $composer3, 54, 4), (24 & 4) != 0 ? true : enabled3, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : Role.m4603boximpl(iM4610getButtono7Vup1c3), onClick);
            Alignment contentAlignment$iv3 = Alignment.INSTANCE.getCenter();
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv3 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv3, false, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv3 = (48 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume7 = $composer2.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv3 = (Density) objConsume7;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume8 = $composer2.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv3 = (LayoutDirection) objConsume8;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration3 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume9 = $composer2.consume(localViewConfiguration3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv3 = (ViewConfiguration) objConsume9;
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf3 = LayoutKt.materializerOf(modifier$iv3);
            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv3 = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, density$iv$iv3, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, layoutDirection$iv$iv3, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, viewConfiguration$iv$iv3, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i11 = ($changed$iv$iv$iv3 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i12 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1676789754, "C99@4705L21,100@4741L84:IconButton.kt#uh7d8r");
            long contentColor3 = colors3.contentColor$material3_release(enabled3, $composer2, (($dirty5 >> 6) & 14) | (($dirty5 >> 6) & 112)).getValue().m2981unboximpl();
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor3))}, content, $composer2, (($dirty5 >> 12) & 112) | 8);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final boolean z3 = enabled3;
        final IconButtonColors iconButtonColors3 = colors3;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.IconButton.3
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

            public final void invoke(Composer composer, int i13) {
                IconButtonKt.IconButton(onClick, modifier6, z3, iconButtonColors3, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:101:0x014d  */
    /* JADX WARN: Code duplicated, block: B:103:0x016d  */
    /* JADX WARN: Code duplicated, block: B:104:0x0178  */
    /* JADX WARN: Code duplicated, block: B:106:0x0189  */
    /* JADX WARN: Code duplicated, block: B:109:0x019b  */
    /* JADX WARN: Code duplicated, block: B:112:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:115:0x02de  */
    /* JADX WARN: Code duplicated, block: B:116:0x02e2  */
    /* JADX WARN: Code duplicated, block: B:119:0x03bc  */
    /* JADX WARN: Code duplicated, block: B:123:0x03c9  */
    /* JADX WARN: Code duplicated, block: B:124:0x03cc  */
    /* JADX WARN: Code duplicated, block: B:82:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:84:0x0107  */
    /* JADX WARN: Code duplicated, block: B:91:0x011f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:92:0x0121  */
    /* JADX WARN: Code duplicated, block: B:93:0x0126  */
    /* JADX WARN: Code duplicated, block: B:95:0x0129  */
    /* JADX WARN: Code duplicated, block: B:96:0x012b  */
    /* JADX WARN: Code duplicated, block: B:99:0x0130  */
    public static final void IconToggleButton(final boolean checked, final Function1<? super Boolean, Unit> onCheckedChange, Modifier modifier, boolean enabled, IconToggleButtonColors colors, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        IconToggleButtonColors colors2;
        MutableInteractionSource mutableInteractionSource;
        int i2;
        Modifier.Companion modifier3;
        boolean enabled2;
        int $dirty;
        boolean enabled3;
        IconToggleButtonColors colors3;
        MutableInteractionSource interactionSource2;
        Modifier modifier4;
        Object it$iv$iv;
        Object value$iv$iv;
        Function0<ComposeUiNode> constructor;
        Modifier modifier5;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onCheckedChange, "onCheckedChange");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(692561811);
        ComposerKt.sourceInformation($composer2, "C(IconToggleButton)P(!1,6,5,3!1,4)138@6848L24,139@6924L39,146@7191L9,147@7241L32,154@7545L135,142@7009L908:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(checked) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changedInstance(onCheckedChange) ? 32 : 16;
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
                colors2 = colors;
                int i5 = $composer2.changed(colors2) ? 16384 : 8192;
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
            mutableInteractionSource = interactionSource;
        } else if ((458752 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer2.changed(mutableInteractionSource) ? 131072 : 65536;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 64) == 0) {
            if ((3670016 & $changed) == 0) {
                i2 = $composer2.changedInstance(content) ? 1048576 : 524288;
            }
            if ((2995931 & $dirty2) == 599186 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    } else {
                        enabled2 = z;
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                        colors2 = IconButtonDefaults.INSTANCE.m1563iconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer2, 1572864, 63);
                    }
                    if (i6 != 0) {
                        $composer2.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                        it$iv$iv = $composer2.rememberedValue();
                        int $dirty3 = $dirty2;
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer2.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer2.endReplaceableGroup();
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                        modifier4 = modifier3;
                        enabled3 = enabled2;
                        colors3 = colors2;
                        $dirty = $dirty3;
                    } else {
                        $dirty = $dirty2;
                        enabled3 = enabled2;
                        colors3 = colors2;
                        interactionSource2 = mutableInteractionSource;
                        modifier4 = modifier3;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    $dirty = $dirty2;
                    enabled3 = z;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                    modifier4 = modifier2;
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(692561811, $dirty, -1, "androidx.compose.material3.IconToggleButton (IconButton.kt:133)");
                }
                Modifier modifierM160backgroundbw27NRU$default = BackgroundKt.m160backgroundbw27NRU$default(ClipKt.clip(SizeKt.m534size3ABfNKs(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier4), IconButtonTokens.INSTANCE.m2267getStateLayerSizeD9Ej5fM()), ShapesKt.toShape(IconButtonTokens.INSTANCE.getStateLayerShape(), $composer2, 6)), colors3.containerColor$material3_release(enabled3, checked, $composer2, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 6) & 896)).getValue().m2981unboximpl(), null, 2, null);
                int iM4611getCheckboxo7Vup1c = Role.INSTANCE.m4611getCheckboxo7Vup1c();
                float arg0$iv = IconButtonTokens.INSTANCE.m2267getStateLayerSizeD9Ej5fM();
                int $dirty4 = $dirty;
                Modifier modifier$iv = ToggleableKt.m723toggleableO2vRcR0(modifierM160backgroundbw27NRU$default, checked, interactionSource2, RippleKt.m1298rememberRipple9IZ8Weo(false, Dp.m5274constructorimpl(arg0$iv / 2), 0L, $composer2, 54, 4), enabled3, Role.m4603boximpl(iM4611getCheckboxo7Vup1c), onCheckedChange);
                Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                int $changed$iv$iv = (48 << 3) & 112;
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density$iv$iv = (Density) objConsume;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                Modifier modifier6 = modifier4;
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer2.consume(localViewConfiguration);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor);
                } else {
                    $composer2.useNode();
                }
                $composer2.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer2);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer2.enableReusing();
                function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i7 = ($changed$iv$iv$iv >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i8 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 165225739, "C161@7782L30,162@7827L84:IconButton.kt#uh7d8r");
                long contentColor = colors3.contentColor$material3_release(enabled3, checked, $composer2, (($dirty4 >> 9) & 14) | (($dirty4 << 3) & 112) | (($dirty4 >> 6) & 896)).getValue().m2981unboximpl();
                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor))}, content, $composer2, (($dirty4 >> 15) & 112) | 8);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier5 = modifier6;
            } else {
                $composer2.skipToGroupEnd();
                modifier5 = modifier2;
                enabled3 = z;
                colors3 = colors2;
                interactionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier7 = modifier5;
            final boolean z2 = enabled3;
            final IconToggleButtonColors iconToggleButtonColors = colors3;
            final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.IconToggleButton.3
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

                public final void invoke(Composer composer, int i9) {
                    IconButtonKt.IconToggleButton(checked, onCheckedChange, modifier7, z2, iconToggleButtonColors, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty2 |= i2;
        if ((2995931 & $dirty2) == 599186) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1563iconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer2, 1572864, 63);
                }
                if (i6 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    int $dirty5 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    $dirty = $dirty5;
                } else {
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                    modifier4 = modifier3;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1563iconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer2, 1572864, 63);
                }
                if (i6 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    int $dirty6 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    $dirty = $dirty6;
                } else {
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                    modifier4 = modifier3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(692561811, $dirty, -1, "androidx.compose.material3.IconToggleButton (IconButton.kt:133)");
            }
            Modifier modifierM160backgroundbw27NRU$default2 = BackgroundKt.m160backgroundbw27NRU$default(ClipKt.clip(SizeKt.m534size3ABfNKs(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier4), IconButtonTokens.INSTANCE.m2267getStateLayerSizeD9Ej5fM()), ShapesKt.toShape(IconButtonTokens.INSTANCE.getStateLayerShape(), $composer2, 6)), colors3.containerColor$material3_release(enabled3, checked, $composer2, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 6) & 896)).getValue().m2981unboximpl(), null, 2, null);
            int iM4611getCheckboxo7Vup1c2 = Role.INSTANCE.m4611getCheckboxo7Vup1c();
            float arg0$iv2 = IconButtonTokens.INSTANCE.m2267getStateLayerSizeD9Ej5fM();
            int $dirty7 = $dirty;
            Modifier modifier$iv2 = ToggleableKt.m723toggleableO2vRcR0(modifierM160backgroundbw27NRU$default2, checked, interactionSource2, RippleKt.m1298rememberRipple9IZ8Weo(false, Dp.m5274constructorimpl(arg0$iv2 / 2), 0L, $composer2, 54, 4), enabled3, Role.m4603boximpl(iM4611getCheckboxo7Vup1c2), onCheckedChange);
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv2 = (48 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer2.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv2 = (Density) objConsume4;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            Modifier modifier8 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume5 = $composer2.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer2.consume(localViewConfiguration2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i9 = ($changed$iv$iv$iv2 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i10 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 165225739, "C161@7782L30,162@7827L84:IconButton.kt#uh7d8r");
            long contentColor2 = colors3.contentColor$material3_release(enabled3, checked, $composer2, (($dirty7 >> 9) & 14) | (($dirty7 << 3) & 112) | (($dirty7 >> 6) & 896)).getValue().m2981unboximpl();
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor2))}, content, $composer2, (($dirty7 >> 15) & 112) | 8);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier8;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1563iconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer2, 1572864, 63);
                }
                if (i6 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    int $dirty8 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    $dirty = $dirty8;
                } else {
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                    modifier4 = modifier3;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1563iconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer2, 1572864, 63);
                }
                if (i6 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    int $dirty9 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    $dirty = $dirty9;
                } else {
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    colors3 = colors2;
                    interactionSource2 = mutableInteractionSource;
                    modifier4 = modifier3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(692561811, $dirty, -1, "androidx.compose.material3.IconToggleButton (IconButton.kt:133)");
            }
            Modifier modifierM160backgroundbw27NRU$default3 = BackgroundKt.m160backgroundbw27NRU$default(ClipKt.clip(SizeKt.m534size3ABfNKs(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier4), IconButtonTokens.INSTANCE.m2267getStateLayerSizeD9Ej5fM()), ShapesKt.toShape(IconButtonTokens.INSTANCE.getStateLayerShape(), $composer2, 6)), colors3.containerColor$material3_release(enabled3, checked, $composer2, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 6) & 896)).getValue().m2981unboximpl(), null, 2, null);
            int iM4611getCheckboxo7Vup1c3 = Role.INSTANCE.m4611getCheckboxo7Vup1c();
            float arg0$iv3 = IconButtonTokens.INSTANCE.m2267getStateLayerSizeD9Ej5fM();
            int $dirty10 = $dirty;
            Modifier modifier$iv3 = ToggleableKt.m723toggleableO2vRcR0(modifierM160backgroundbw27NRU$default3, checked, interactionSource2, RippleKt.m1298rememberRipple9IZ8Weo(false, Dp.m5274constructorimpl(arg0$iv3 / 2), 0L, $composer2, 54, 4), enabled3, Role.m4603boximpl(iM4611getCheckboxo7Vup1c3), onCheckedChange);
            Alignment contentAlignment$iv3 = Alignment.INSTANCE.getCenter();
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv3 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv3, false, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv3 = (48 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume7 = $composer2.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv3 = (Density) objConsume7;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            Modifier modifier9 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume8 = $composer2.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv3 = (LayoutDirection) objConsume8;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration3 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume9 = $composer2.consume(localViewConfiguration3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv3 = (ViewConfiguration) objConsume9;
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf3 = LayoutKt.materializerOf(modifier$iv3);
            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv3 = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, density$iv$iv3, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, layoutDirection$iv$iv3, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, viewConfiguration$iv$iv3, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i11 = ($changed$iv$iv$iv3 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i12 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 165225739, "C161@7782L30,162@7827L84:IconButton.kt#uh7d8r");
            long contentColor3 = colors3.contentColor$material3_release(enabled3, checked, $composer2, (($dirty10 >> 9) & 14) | (($dirty10 << 3) & 112) | (($dirty10 >> 6) & 896)).getValue().m2981unboximpl();
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor3))}, content, $composer2, (($dirty10 >> 15) & 112) | 8);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier9;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier10 = modifier5;
        final boolean z3 = enabled3;
        final IconToggleButtonColors iconToggleButtonColors2 = colors3;
        final MutableInteractionSource mutableInteractionSource3 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.IconToggleButton.3
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

            public final void invoke(Composer composer, int i13) {
                IconButtonKt.IconToggleButton(checked, onCheckedChange, modifier10, z3, iconToggleButtonColors2, mutableInteractionSource3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:102:0x013e  */
    /* JADX WARN: Code duplicated, block: B:105:0x014c  */
    /* JADX WARN: Code duplicated, block: B:107:0x0165  */
    /* JADX WARN: Code duplicated, block: B:109:0x0185  */
    /* JADX WARN: Code duplicated, block: B:110:0x0190  */
    /* JADX WARN: Code duplicated, block: B:112:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:115:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:118:0x0248  */
    /* JADX WARN: Code duplicated, block: B:123:0x0254  */
    /* JADX WARN: Code duplicated, block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x0104  */
    /* JADX WARN: Code duplicated, block: B:85:0x010e  */
    /* JADX WARN: Code duplicated, block: B:95:0x012e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:96:0x0130  */
    /* JADX WARN: Code duplicated, block: B:97:0x0135  */
    /* JADX WARN: Code duplicated, block: B:99:0x0138  */
    public static final void FilledIconButton(final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, IconButtonColors colors, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        IconButtonColors colors2;
        MutableInteractionSource interactionSource2;
        int i2;
        Modifier.Companion modifier3;
        Modifier modifier4;
        boolean enabled3;
        Shape shape3;
        IconButtonColors colors3;
        MutableInteractionSource interactionSource3;
        final int $dirty;
        Object it$iv$iv;
        Object value$iv$iv;
        IconButtonColors colors4;
        boolean enabled4;
        Modifier modifier5;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(1594730011);
        ComposerKt.sourceInformation($composer3, "C(FilledIconButton)P(5,4,2,6!1,3)200@9870L11,201@9933L24,202@10009L39,209@10241L23,210@10298L21,204@10090L441:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
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
                colors2 = colors;
                int i6 = $composer3.changed(colors2) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i6;
        } else {
            colors2 = colors;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            interactionSource2 = interactionSource;
        } else if ((458752 & $changed) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer3.changed(interactionSource2) ? 131072 : 65536;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((i & 64) == 0) {
            if ((3670016 & $changed) == 0) {
                i2 = $composer3.changedInstance(content) ? 1048576 : 524288;
            }
            if ((2995931 & $dirty2) == 599186 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    }
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                        shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                        colors2 = IconButtonDefaults.INSTANCE.m1558filledIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    }
                    if (i7 != 0) {
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        it$iv$iv = $composer3.rememberedValue();
                        int $dirty3 = $dirty2;
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        $dirty = $dirty3;
                        interactionSource3 = (MutableInteractionSource) value$iv$iv;
                        modifier4 = modifier3;
                        enabled3 = enabled2;
                        shape3 = shape2;
                        colors3 = colors2;
                    } else {
                        modifier4 = modifier3;
                        enabled3 = enabled2;
                        shape3 = shape2;
                        colors3 = colors2;
                        interactionSource3 = interactionSource2;
                        $dirty = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    modifier4 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource3 = interactionSource2;
                    $dirty = $dirty2;
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1594730011, $dirty, -1, "androidx.compose.material3.FilledIconButton (IconButton.kt:196)");
                }
                int $dirty4 = $dirty;
                colors4 = colors3;
                enabled4 = enabled3;
                modifier5 = modifier4;
                $composer2 = $composer3;
                SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier4, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconButton.2
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
                }, 1, null), enabled4, shape3, colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), 0.0f, 0.0f, null, interactionSource3, ComposableLambdaKt.composableLambda($composer3, -1560623888, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconButton.3
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
                        ComposerKt.sourceInformation($composer4, "C213@10377L152:IconButton.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1560623888, $changed2, -1, "androidx.compose.material3.FilledIconButton.<anonymous> (IconButton.kt:212)");
                            }
                            Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, FilledIconButtonTokens.INSTANCE.m2231getContainerSizeD9Ej5fM());
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function2 = content;
                            int i8 = $dirty;
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            int $changed$iv$iv = (54 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume = $composer4.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density density$iv$iv = (Density) objConsume;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer4.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer4.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                $composer4.createNode(constructor);
                            } else {
                                $composer4.useNode();
                            }
                            $composer4.disableReusing();
                            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                            $composer4.enableReusing();
                            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i9 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i10 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1557816860, "C217@10514L9:IconButton.kt#uh7d8r");
                            function2.invoke($composer4, Integer.valueOf((i8 >> 18) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }), $composer2, ($dirty4 & 14) | ($dirty4 & 896) | ($dirty4 & 7168) | (($dirty4 << 12) & 1879048192), 6, 448);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                interactionSource2 = interactionSource3;
            } else {
                $composer3.skipToGroupEnd();
                $composer2 = $composer3;
                modifier5 = modifier2;
                enabled4 = enabled2;
                shape3 = shape2;
                colors4 = colors2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier6 = modifier5;
            final boolean z = enabled4;
            final Shape shape4 = shape3;
            final IconButtonColors iconButtonColors = colors4;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconButton.4
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

                public final void invoke(Composer composer, int i8) {
                    IconButtonKt.FilledIconButton(onClick, modifier6, z, shape4, iconButtonColors, mutableInteractionSource, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty2 |= i2;
        if ((2995931 & $dirty2) == 599186) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1558filledIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty5 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    $dirty = $dirty5;
                    interactionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                } else {
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource3 = interactionSource2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1558filledIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty6 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    $dirty = $dirty6;
                    interactionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                } else {
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource3 = interactionSource2;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1594730011, $dirty, -1, "androidx.compose.material3.FilledIconButton (IconButton.kt:196)");
            }
            int $dirty7 = $dirty;
            colors4 = colors3;
            enabled4 = enabled3;
            modifier5 = modifier4;
            $composer2 = $composer3;
            SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier4, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconButton.2
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
            }, 1, null), enabled4, shape3, colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), 0.0f, 0.0f, null, interactionSource3, ComposableLambdaKt.composableLambda($composer3, -1560623888, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconButton.3
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
                    ComposerKt.sourceInformation($composer4, "C213@10377L152:IconButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1560623888, $changed2, -1, "androidx.compose.material3.FilledIconButton.<anonymous> (IconButton.kt:212)");
                        }
                        Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, FilledIconButtonTokens.INSTANCE.m2231getContainerSizeD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i8 = $dirty;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(constructor);
                        } else {
                            $composer4.useNode();
                        }
                        $composer4.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer4.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i9 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i10 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1557816860, "C217@10514L9:IconButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i8 >> 18) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty7 & 14) | ($dirty7 & 896) | ($dirty7 & 7168) | (($dirty7 << 12) & 1879048192), 6, 448);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1558filledIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty8 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    $dirty = $dirty8;
                    interactionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                } else {
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource3 = interactionSource2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1558filledIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty9 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    $dirty = $dirty9;
                    interactionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                } else {
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource3 = interactionSource2;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1594730011, $dirty, -1, "androidx.compose.material3.FilledIconButton (IconButton.kt:196)");
            }
            int $dirty10 = $dirty;
            colors4 = colors3;
            enabled4 = enabled3;
            modifier5 = modifier4;
            $composer2 = $composer3;
            SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier4, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconButton.2
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
            }, 1, null), enabled4, shape3, colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), 0.0f, 0.0f, null, interactionSource3, ComposableLambdaKt.composableLambda($composer3, -1560623888, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconButton.3
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
                    ComposerKt.sourceInformation($composer4, "C213@10377L152:IconButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1560623888, $changed2, -1, "androidx.compose.material3.FilledIconButton.<anonymous> (IconButton.kt:212)");
                        }
                        Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, FilledIconButtonTokens.INSTANCE.m2231getContainerSizeD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i8 = $dirty;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(constructor);
                        } else {
                            $composer4.useNode();
                        }
                        $composer4.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer4.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i9 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i10 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1557816860, "C217@10514L9:IconButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i8 >> 18) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty10 & 14) | ($dirty10 & 896) | ($dirty10 & 7168) | (($dirty10 << 12) & 1879048192), 6, 448);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource3;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier7 = modifier5;
        final boolean z2 = enabled4;
        final Shape shape5 = shape3;
        final IconButtonColors iconButtonColors2 = colors4;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconButton.4
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

            public final void invoke(Composer composer, int i8) {
                IconButtonKt.FilledIconButton(onClick, modifier7, z2, shape5, iconButtonColors2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:102:0x013e  */
    /* JADX WARN: Code duplicated, block: B:105:0x014c  */
    /* JADX WARN: Code duplicated, block: B:107:0x0165  */
    /* JADX WARN: Code duplicated, block: B:109:0x0185  */
    /* JADX WARN: Code duplicated, block: B:110:0x0190  */
    /* JADX WARN: Code duplicated, block: B:112:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:115:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:118:0x0248  */
    /* JADX WARN: Code duplicated, block: B:123:0x0254  */
    /* JADX WARN: Code duplicated, block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x0104  */
    /* JADX WARN: Code duplicated, block: B:85:0x010e  */
    /* JADX WARN: Code duplicated, block: B:95:0x012e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:96:0x0130  */
    /* JADX WARN: Code duplicated, block: B:97:0x0135  */
    /* JADX WARN: Code duplicated, block: B:99:0x0138  */
    public static final void FilledTonalIconButton(final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, IconButtonColors colors, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        IconButtonColors colors2;
        MutableInteractionSource interactionSource2;
        int i2;
        Modifier.Companion modifier3;
        Modifier modifier4;
        boolean enabled3;
        Shape shape3;
        IconButtonColors colors3;
        MutableInteractionSource interactionSource3;
        final int $dirty;
        Object it$iv$iv;
        Object value$iv$iv;
        IconButtonColors colors4;
        boolean enabled4;
        Modifier modifier5;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-783937767);
        ComposerKt.sourceInformation($composer3, "C(FilledTonalIconButton)P(5,4,2,6!1,3)260@12819L11,261@12882L29,262@12963L39,269@13195L23,270@13252L21,264@13044L446:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
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
                colors2 = colors;
                int i6 = $composer3.changed(colors2) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i6;
        } else {
            colors2 = colors;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            interactionSource2 = interactionSource;
        } else if ((458752 & $changed) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer3.changed(interactionSource2) ? 131072 : 65536;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((i & 64) == 0) {
            if ((3670016 & $changed) == 0) {
                i2 = $composer3.changedInstance(content) ? 1048576 : 524288;
            }
            if ((2995931 & $dirty2) == 599186 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    }
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                        shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                        colors2 = IconButtonDefaults.INSTANCE.m1560filledTonalIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    }
                    if (i7 != 0) {
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        it$iv$iv = $composer3.rememberedValue();
                        int $dirty3 = $dirty2;
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        $dirty = $dirty3;
                        interactionSource3 = (MutableInteractionSource) value$iv$iv;
                        modifier4 = modifier3;
                        enabled3 = enabled2;
                        shape3 = shape2;
                        colors3 = colors2;
                    } else {
                        modifier4 = modifier3;
                        enabled3 = enabled2;
                        shape3 = shape2;
                        colors3 = colors2;
                        interactionSource3 = interactionSource2;
                        $dirty = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    modifier4 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource3 = interactionSource2;
                    $dirty = $dirty2;
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-783937767, $dirty, -1, "androidx.compose.material3.FilledTonalIconButton (IconButton.kt:256)");
                }
                int $dirty4 = $dirty;
                colors4 = colors3;
                enabled4 = enabled3;
                modifier5 = modifier4;
                $composer2 = $composer3;
                SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier4, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconButton.2
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
                }, 1, null), enabled4, shape3, colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), 0.0f, 0.0f, null, interactionSource3, ComposableLambdaKt.composableLambda($composer3, -1772884636, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconButton.3
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
                        ComposerKt.sourceInformation($composer4, "C273@13331L157:IconButton.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1772884636, $changed2, -1, "androidx.compose.material3.FilledTonalIconButton.<anonymous> (IconButton.kt:272)");
                            }
                            Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, FilledTonalIconButtonTokens.INSTANCE.m2247getContainerSizeD9Ej5fM());
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function2 = content;
                            int i8 = $dirty;
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            int $changed$iv$iv = (54 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume = $composer4.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density density$iv$iv = (Density) objConsume;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer4.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer4.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                $composer4.createNode(constructor);
                            } else {
                                $composer4.useNode();
                            }
                            $composer4.disableReusing();
                            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                            $composer4.enableReusing();
                            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i9 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i10 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, 151425855, "C277@13473L9:IconButton.kt#uh7d8r");
                            function2.invoke($composer4, Integer.valueOf((i8 >> 18) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }), $composer2, ($dirty4 & 14) | ($dirty4 & 896) | ($dirty4 & 7168) | (($dirty4 << 12) & 1879048192), 6, 448);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                interactionSource2 = interactionSource3;
            } else {
                $composer3.skipToGroupEnd();
                $composer2 = $composer3;
                modifier5 = modifier2;
                enabled4 = enabled2;
                shape3 = shape2;
                colors4 = colors2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier6 = modifier5;
            final boolean z = enabled4;
            final Shape shape4 = shape3;
            final IconButtonColors iconButtonColors = colors4;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconButton.4
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

                public final void invoke(Composer composer, int i8) {
                    IconButtonKt.FilledTonalIconButton(onClick, modifier6, z, shape4, iconButtonColors, mutableInteractionSource, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty2 |= i2;
        if ((2995931 & $dirty2) == 599186) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1560filledTonalIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty5 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    $dirty = $dirty5;
                    interactionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                } else {
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource3 = interactionSource2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1560filledTonalIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty6 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    $dirty = $dirty6;
                    interactionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                } else {
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource3 = interactionSource2;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-783937767, $dirty, -1, "androidx.compose.material3.FilledTonalIconButton (IconButton.kt:256)");
            }
            int $dirty7 = $dirty;
            colors4 = colors3;
            enabled4 = enabled3;
            modifier5 = modifier4;
            $composer2 = $composer3;
            SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier4, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconButton.2
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
            }, 1, null), enabled4, shape3, colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), 0.0f, 0.0f, null, interactionSource3, ComposableLambdaKt.composableLambda($composer3, -1772884636, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconButton.3
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
                    ComposerKt.sourceInformation($composer4, "C273@13331L157:IconButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1772884636, $changed2, -1, "androidx.compose.material3.FilledTonalIconButton.<anonymous> (IconButton.kt:272)");
                        }
                        Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, FilledTonalIconButtonTokens.INSTANCE.m2247getContainerSizeD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i8 = $dirty;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(constructor);
                        } else {
                            $composer4.useNode();
                        }
                        $composer4.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer4.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i9 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i10 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 151425855, "C277@13473L9:IconButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i8 >> 18) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty7 & 14) | ($dirty7 & 896) | ($dirty7 & 7168) | (($dirty7 << 12) & 1879048192), 6, 448);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1560filledTonalIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty8 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    $dirty = $dirty8;
                    interactionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                } else {
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource3 = interactionSource2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1560filledTonalIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty9 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    $dirty = $dirty9;
                    interactionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                } else {
                    modifier4 = modifier3;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    interactionSource3 = interactionSource2;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-783937767, $dirty, -1, "androidx.compose.material3.FilledTonalIconButton (IconButton.kt:256)");
            }
            int $dirty10 = $dirty;
            colors4 = colors3;
            enabled4 = enabled3;
            modifier5 = modifier4;
            $composer2 = $composer3;
            SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier4, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconButton.2
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
            }, 1, null), enabled4, shape3, colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), 0.0f, 0.0f, null, interactionSource3, ComposableLambdaKt.composableLambda($composer3, -1772884636, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconButton.3
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
                    ComposerKt.sourceInformation($composer4, "C273@13331L157:IconButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1772884636, $changed2, -1, "androidx.compose.material3.FilledTonalIconButton.<anonymous> (IconButton.kt:272)");
                        }
                        Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, FilledTonalIconButtonTokens.INSTANCE.m2247getContainerSizeD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i8 = $dirty;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(constructor);
                        } else {
                            $composer4.useNode();
                        }
                        $composer4.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer4.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i9 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i10 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 151425855, "C277@13473L9:IconButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i8 >> 18) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty10 & 14) | ($dirty10 & 896) | ($dirty10 & 7168) | (($dirty10 << 12) & 1879048192), 6, 448);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource3;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier7 = modifier5;
        final boolean z2 = enabled4;
        final Shape shape5 = shape3;
        final IconButtonColors iconButtonColors2 = colors4;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconButton.4
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

            public final void invoke(Composer composer, int i8) {
                IconButtonKt.FilledTonalIconButton(onClick, modifier7, z2, shape5, iconButtonColors2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:105:0x015b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:106:0x015d  */
    /* JADX WARN: Code duplicated, block: B:107:0x0162  */
    /* JADX WARN: Code duplicated, block: B:109:0x0166  */
    /* JADX WARN: Code duplicated, block: B:110:0x0168  */
    /* JADX WARN: Code duplicated, block: B:113:0x016d  */
    /* JADX WARN: Code duplicated, block: B:114:0x0177  */
    /* JADX WARN: Code duplicated, block: B:117:0x017c  */
    /* JADX WARN: Code duplicated, block: B:118:0x0197  */
    /* JADX WARN: Code duplicated, block: B:120:0x019a  */
    /* JADX WARN: Code duplicated, block: B:122:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:123:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:125:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:128:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:131:0x028d  */
    /* JADX WARN: Code duplicated, block: B:136:0x0299  */
    /* JADX WARN: Code duplicated, block: B:138:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x0122  */
    /* JADX WARN: Code duplicated, block: B:95:0x012f  */
    public static final void FilledIconToggleButton(final boolean checked, final Function1<? super Boolean, Unit> onCheckedChange, Modifier modifier, boolean enabled, Shape shape, IconToggleButtonColors colors, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Shape shape2;
        IconToggleButtonColors iconToggleButtonColors;
        MutableInteractionSource interactionSource2;
        int i2;
        Modifier.Companion modifier2;
        boolean enabled2;
        Shape shape3;
        IconToggleButtonColors colors2;
        Modifier modifier3;
        MutableInteractionSource interactionSource3;
        int $dirty;
        boolean enabled3;
        Shape shape4;
        IconToggleButtonColors colors3;
        Object it$iv$iv;
        Object value$iv$iv;
        boolean enabled4;
        Composer $composer2;
        IconToggleButtonColors colors4;
        Modifier modifier4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onCheckedChange, "onCheckedChange");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-1708189280);
        ComposerKt.sourceInformation($composer3, "C(FilledIconToggleButton)P(!1,6,5,3,7!1,4)318@15646L11,319@15715L30,320@15797L39,328@16070L32,329@16136L30,322@15878L500:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changed(checked) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changedInstance(onCheckedChange) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            z = enabled;
        } else if (($changed & 7168) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i5 = $composer3.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        if ((458752 & $changed) == 0) {
            if ((i & 32) == 0) {
                iconToggleButtonColors = colors;
                int i6 = $composer3.changed(iconToggleButtonColors) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                iconToggleButtonColors = colors;
            }
            $dirty2 |= i6;
        } else {
            iconToggleButtonColors = colors;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            interactionSource2 = interactionSource;
        } else if ((3670016 & $changed) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer3.changed(interactionSource2) ? 1048576 : 524288;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((i & 128) == 0) {
            if ((29360128 & $changed) == 0) {
                i2 = $composer3.changedInstance(content) ? 8388608 : 4194304;
            }
            if ((23967451 & $dirty2) == 4793490 || !$composer3.getSkipping()) {
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
                    if ((i & 16) != 0) {
                        shape3 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                        $dirty2 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 32) != 0) {
                        colors2 = IconButtonDefaults.INSTANCE.m1559filledIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                        $dirty2 &= -458753;
                    } else {
                        colors2 = iconToggleButtonColors;
                    }
                    if (i7 != 0) {
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        Modifier modifier5 = modifier2;
                        it$iv$iv = $composer3.rememberedValue();
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) value$iv$iv;
                        modifier3 = modifier5;
                        interactionSource3 = mutableInteractionSource;
                        $dirty = $dirty2;
                        enabled3 = enabled2;
                        shape4 = shape3;
                        colors3 = colors2;
                    } else {
                        modifier3 = modifier2;
                        interactionSource3 = interactionSource;
                        $dirty = $dirty2;
                        enabled3 = enabled2;
                        shape4 = shape3;
                        colors3 = colors2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        modifier3 = modifier;
                        $dirty = $dirty2 & (-458753);
                        enabled3 = z;
                        shape4 = shape2;
                        interactionSource3 = interactionSource2;
                        colors3 = iconToggleButtonColors;
                    } else {
                        modifier3 = modifier;
                        $dirty = $dirty2;
                        enabled3 = z;
                        shape4 = shape2;
                        interactionSource3 = interactionSource2;
                        colors3 = iconToggleButtonColors;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1708189280, $dirty, -1, "androidx.compose.material3.FilledIconToggleButton (IconButton.kt:313)");
                }
                final int $dirty3 = $dirty;
                enabled4 = enabled3;
                $composer2 = $composer3;
                colors4 = colors3;
                modifier4 = modifier3;
                SurfaceKt.m1808Surfaced85dljk(checked, onCheckedChange, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconToggleButton.2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semantics) {
                        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                        SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4611getCheckboxo7Vup1c());
                    }
                }, 1, null), enabled4, shape4, colors3.containerColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), 0.0f, 0.0f, (BorderStroke) null, interactionSource3, ComposableLambdaKt.composableLambda($composer3, 1235871670, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconToggleButton.3
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
                        ComposerKt.sourceInformation($composer4, "C332@16224L152:IconButton.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1235871670, $changed2, -1, "androidx.compose.material3.FilledIconToggleButton.<anonymous> (IconButton.kt:331)");
                            }
                            Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, FilledIconButtonTokens.INSTANCE.m2231getContainerSizeD9Ej5fM());
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function2 = content;
                            int i8 = $dirty3;
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            int $changed$iv$iv = (54 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume = $composer4.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density density$iv$iv = (Density) objConsume;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer4.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer4.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                $composer4.createNode(constructor);
                            } else {
                                $composer4.useNode();
                            }
                            $composer4.disableReusing();
                            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                            $composer4.enableReusing();
                            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i9 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i10 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, 1822112071, "C336@16361L9:IconButton.kt#uh7d8r");
                            function2.invoke($composer4, Integer.valueOf((i8 >> 21) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }), $composer2, ($dirty3 & 14) | ($dirty3 & 112) | ($dirty3 & 7168) | ($dirty3 & 57344), (($dirty3 >> 18) & 14) | 48, 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                interactionSource2 = interactionSource3;
            } else {
                $composer3.skipToGroupEnd();
                modifier4 = modifier;
                enabled4 = z;
                shape4 = shape2;
                colors4 = iconToggleButtonColors;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier6 = modifier4;
            final boolean z2 = enabled4;
            final Shape shape5 = shape4;
            final IconToggleButtonColors iconToggleButtonColors2 = colors4;
            final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconToggleButton.4
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

                public final void invoke(Composer composer, int i8) {
                    IconButtonKt.FilledIconToggleButton(checked, onCheckedChange, modifier6, z2, shape5, iconToggleButtonColors2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 12582912;
        $dirty2 |= i2;
        if ((23967451 & $dirty2) == 4793490) {
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
                if ((i & 16) != 0) {
                    shape3 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1559filledIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    colors2 = iconToggleButtonColors;
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier7 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    MutableInteractionSource mutableInteractionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier7;
                    interactionSource3 = mutableInteractionSource3;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                } else {
                    modifier3 = modifier2;
                    interactionSource3 = interactionSource;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
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
                if ((i & 16) != 0) {
                    shape3 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1559filledIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    colors2 = iconToggleButtonColors;
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier8 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    MutableInteractionSource mutableInteractionSource4 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier8;
                    interactionSource3 = mutableInteractionSource4;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                } else {
                    modifier3 = modifier2;
                    interactionSource3 = interactionSource;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1708189280, $dirty, -1, "androidx.compose.material3.FilledIconToggleButton (IconButton.kt:313)");
            }
            final int $dirty4 = $dirty;
            enabled4 = enabled3;
            $composer2 = $composer3;
            colors4 = colors3;
            modifier4 = modifier3;
            SurfaceKt.m1808Surfaced85dljk(checked, onCheckedChange, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconToggleButton.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4611getCheckboxo7Vup1c());
                }
            }, 1, null), enabled4, shape4, colors3.containerColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), 0.0f, 0.0f, (BorderStroke) null, interactionSource3, ComposableLambdaKt.composableLambda($composer3, 1235871670, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconToggleButton.3
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
                    ComposerKt.sourceInformation($composer4, "C332@16224L152:IconButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1235871670, $changed2, -1, "androidx.compose.material3.FilledIconToggleButton.<anonymous> (IconButton.kt:331)");
                        }
                        Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, FilledIconButtonTokens.INSTANCE.m2231getContainerSizeD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i8 = $dirty4;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(constructor);
                        } else {
                            $composer4.useNode();
                        }
                        $composer4.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer4.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i9 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i10 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 1822112071, "C336@16361L9:IconButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i8 >> 21) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty4 & 14) | ($dirty4 & 112) | ($dirty4 & 7168) | ($dirty4 & 57344), (($dirty4 >> 18) & 14) | 48, 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource3;
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
                if ((i & 16) != 0) {
                    shape3 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1559filledIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    colors2 = iconToggleButtonColors;
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier9 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    MutableInteractionSource mutableInteractionSource5 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier9;
                    interactionSource3 = mutableInteractionSource5;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                } else {
                    modifier3 = modifier2;
                    interactionSource3 = interactionSource;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
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
                if ((i & 16) != 0) {
                    shape3 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1559filledIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    colors2 = iconToggleButtonColors;
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier10 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    MutableInteractionSource mutableInteractionSource6 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier10;
                    interactionSource3 = mutableInteractionSource6;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                } else {
                    modifier3 = modifier2;
                    interactionSource3 = interactionSource;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1708189280, $dirty, -1, "androidx.compose.material3.FilledIconToggleButton (IconButton.kt:313)");
            }
            final int $dirty5 = $dirty;
            enabled4 = enabled3;
            $composer2 = $composer3;
            colors4 = colors3;
            modifier4 = modifier3;
            SurfaceKt.m1808Surfaced85dljk(checked, onCheckedChange, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconToggleButton.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4611getCheckboxo7Vup1c());
                }
            }, 1, null), enabled4, shape4, colors3.containerColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), 0.0f, 0.0f, (BorderStroke) null, interactionSource3, ComposableLambdaKt.composableLambda($composer3, 1235871670, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconToggleButton.3
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
                    ComposerKt.sourceInformation($composer4, "C332@16224L152:IconButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1235871670, $changed2, -1, "androidx.compose.material3.FilledIconToggleButton.<anonymous> (IconButton.kt:331)");
                        }
                        Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, FilledIconButtonTokens.INSTANCE.m2231getContainerSizeD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i8 = $dirty5;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(constructor);
                        } else {
                            $composer4.useNode();
                        }
                        $composer4.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer4.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i9 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i10 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 1822112071, "C336@16361L9:IconButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i8 >> 21) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty5 & 14) | ($dirty5 & 112) | ($dirty5 & 7168) | ($dirty5 & 57344), (($dirty5 >> 18) & 14) | 48, 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource3;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier11 = modifier4;
        final boolean z3 = enabled4;
        final Shape shape6 = shape4;
        final IconToggleButtonColors iconToggleButtonColors3 = colors4;
        final MutableInteractionSource mutableInteractionSource7 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledIconToggleButton.4
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

            public final void invoke(Composer composer, int i8) {
                IconButtonKt.FilledIconToggleButton(checked, onCheckedChange, modifier11, z3, shape6, iconToggleButtonColors3, mutableInteractionSource7, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:105:0x015b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:106:0x015d  */
    /* JADX WARN: Code duplicated, block: B:107:0x0162  */
    /* JADX WARN: Code duplicated, block: B:109:0x0166  */
    /* JADX WARN: Code duplicated, block: B:110:0x0168  */
    /* JADX WARN: Code duplicated, block: B:113:0x016d  */
    /* JADX WARN: Code duplicated, block: B:114:0x0177  */
    /* JADX WARN: Code duplicated, block: B:117:0x017c  */
    /* JADX WARN: Code duplicated, block: B:118:0x0197  */
    /* JADX WARN: Code duplicated, block: B:120:0x019a  */
    /* JADX WARN: Code duplicated, block: B:122:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:123:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:125:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:128:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:131:0x028d  */
    /* JADX WARN: Code duplicated, block: B:136:0x0299  */
    /* JADX WARN: Code duplicated, block: B:138:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x0122  */
    /* JADX WARN: Code duplicated, block: B:95:0x012f  */
    public static final void FilledTonalIconToggleButton(final boolean checked, final Function1<? super Boolean, Unit> onCheckedChange, Modifier modifier, boolean enabled, Shape shape, IconToggleButtonColors colors, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Shape shape2;
        IconToggleButtonColors iconToggleButtonColors;
        MutableInteractionSource interactionSource2;
        int i2;
        Modifier.Companion modifier2;
        boolean enabled2;
        Shape shape3;
        IconToggleButtonColors colors2;
        Modifier modifier3;
        MutableInteractionSource interactionSource3;
        int $dirty;
        boolean enabled3;
        Shape shape4;
        IconToggleButtonColors colors3;
        Object it$iv$iv;
        Object value$iv$iv;
        boolean enabled4;
        Composer $composer2;
        IconToggleButtonColors colors4;
        Modifier modifier4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onCheckedChange, "onCheckedChange");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(1676089246);
        ComposerKt.sourceInformation($composer3, "C(FilledTonalIconToggleButton)P(!1,6,5,3,7!1,4)382@18890L11,383@18959L35,384@19046L39,392@19319L32,393@19385L30,386@19127L505:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changed(checked) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changedInstance(onCheckedChange) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            z = enabled;
        } else if (($changed & 7168) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i5 = $composer3.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        if ((458752 & $changed) == 0) {
            if ((i & 32) == 0) {
                iconToggleButtonColors = colors;
                int i6 = $composer3.changed(iconToggleButtonColors) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                iconToggleButtonColors = colors;
            }
            $dirty2 |= i6;
        } else {
            iconToggleButtonColors = colors;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            interactionSource2 = interactionSource;
        } else if ((3670016 & $changed) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer3.changed(interactionSource2) ? 1048576 : 524288;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((i & 128) == 0) {
            if ((29360128 & $changed) == 0) {
                i2 = $composer3.changedInstance(content) ? 8388608 : 4194304;
            }
            if ((23967451 & $dirty2) == 4793490 || !$composer3.getSkipping()) {
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
                    if ((i & 16) != 0) {
                        shape3 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                        $dirty2 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 32) != 0) {
                        colors2 = IconButtonDefaults.INSTANCE.m1561filledTonalIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                        $dirty2 &= -458753;
                    } else {
                        colors2 = iconToggleButtonColors;
                    }
                    if (i7 != 0) {
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        Modifier modifier5 = modifier2;
                        it$iv$iv = $composer3.rememberedValue();
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) value$iv$iv;
                        modifier3 = modifier5;
                        interactionSource3 = mutableInteractionSource;
                        $dirty = $dirty2;
                        enabled3 = enabled2;
                        shape4 = shape3;
                        colors3 = colors2;
                    } else {
                        modifier3 = modifier2;
                        interactionSource3 = interactionSource;
                        $dirty = $dirty2;
                        enabled3 = enabled2;
                        shape4 = shape3;
                        colors3 = colors2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        modifier3 = modifier;
                        $dirty = $dirty2 & (-458753);
                        enabled3 = z;
                        shape4 = shape2;
                        interactionSource3 = interactionSource2;
                        colors3 = iconToggleButtonColors;
                    } else {
                        modifier3 = modifier;
                        $dirty = $dirty2;
                        enabled3 = z;
                        shape4 = shape2;
                        interactionSource3 = interactionSource2;
                        colors3 = iconToggleButtonColors;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1676089246, $dirty, -1, "androidx.compose.material3.FilledTonalIconToggleButton (IconButton.kt:377)");
                }
                final int $dirty3 = $dirty;
                enabled4 = enabled3;
                $composer2 = $composer3;
                colors4 = colors3;
                modifier4 = modifier3;
                SurfaceKt.m1808Surfaced85dljk(checked, onCheckedChange, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconToggleButton.2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semantics) {
                        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                        SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4611getCheckboxo7Vup1c());
                    }
                }, 1, null), enabled4, shape4, colors3.containerColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), 0.0f, 0.0f, (BorderStroke) null, interactionSource3, ComposableLambdaKt.composableLambda($composer3, -58218680, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconToggleButton.3
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
                        ComposerKt.sourceInformation($composer4, "C396@19473L157:IconButton.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-58218680, $changed2, -1, "androidx.compose.material3.FilledTonalIconToggleButton.<anonymous> (IconButton.kt:395)");
                            }
                            Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, FilledTonalIconButtonTokens.INSTANCE.m2247getContainerSizeD9Ej5fM());
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function2 = content;
                            int i8 = $dirty3;
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            int $changed$iv$iv = (54 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume = $composer4.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density density$iv$iv = (Density) objConsume;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer4.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer4.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                $composer4.createNode(constructor);
                            } else {
                                $composer4.useNode();
                            }
                            $composer4.disableReusing();
                            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                            $composer4.enableReusing();
                            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i9 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i10 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, 919448265, "C400@19615L9:IconButton.kt#uh7d8r");
                            function2.invoke($composer4, Integer.valueOf((i8 >> 21) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }), $composer2, ($dirty3 & 14) | ($dirty3 & 112) | ($dirty3 & 7168) | ($dirty3 & 57344), (($dirty3 >> 18) & 14) | 48, 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                interactionSource2 = interactionSource3;
            } else {
                $composer3.skipToGroupEnd();
                modifier4 = modifier;
                enabled4 = z;
                shape4 = shape2;
                colors4 = iconToggleButtonColors;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier6 = modifier4;
            final boolean z2 = enabled4;
            final Shape shape5 = shape4;
            final IconToggleButtonColors iconToggleButtonColors2 = colors4;
            final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconToggleButton.4
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

                public final void invoke(Composer composer, int i8) {
                    IconButtonKt.FilledTonalIconToggleButton(checked, onCheckedChange, modifier6, z2, shape5, iconToggleButtonColors2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 12582912;
        $dirty2 |= i2;
        if ((23967451 & $dirty2) == 4793490) {
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
                if ((i & 16) != 0) {
                    shape3 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1561filledTonalIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    colors2 = iconToggleButtonColors;
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier7 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    MutableInteractionSource mutableInteractionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier7;
                    interactionSource3 = mutableInteractionSource3;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                } else {
                    modifier3 = modifier2;
                    interactionSource3 = interactionSource;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
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
                if ((i & 16) != 0) {
                    shape3 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1561filledTonalIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    colors2 = iconToggleButtonColors;
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier8 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    MutableInteractionSource mutableInteractionSource4 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier8;
                    interactionSource3 = mutableInteractionSource4;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                } else {
                    modifier3 = modifier2;
                    interactionSource3 = interactionSource;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1676089246, $dirty, -1, "androidx.compose.material3.FilledTonalIconToggleButton (IconButton.kt:377)");
            }
            final int $dirty4 = $dirty;
            enabled4 = enabled3;
            $composer2 = $composer3;
            colors4 = colors3;
            modifier4 = modifier3;
            SurfaceKt.m1808Surfaced85dljk(checked, onCheckedChange, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconToggleButton.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4611getCheckboxo7Vup1c());
                }
            }, 1, null), enabled4, shape4, colors3.containerColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), 0.0f, 0.0f, (BorderStroke) null, interactionSource3, ComposableLambdaKt.composableLambda($composer3, -58218680, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconToggleButton.3
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
                    ComposerKt.sourceInformation($composer4, "C396@19473L157:IconButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-58218680, $changed2, -1, "androidx.compose.material3.FilledTonalIconToggleButton.<anonymous> (IconButton.kt:395)");
                        }
                        Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, FilledTonalIconButtonTokens.INSTANCE.m2247getContainerSizeD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i8 = $dirty4;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(constructor);
                        } else {
                            $composer4.useNode();
                        }
                        $composer4.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer4.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i9 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i10 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 919448265, "C400@19615L9:IconButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i8 >> 21) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty4 & 14) | ($dirty4 & 112) | ($dirty4 & 7168) | ($dirty4 & 57344), (($dirty4 >> 18) & 14) | 48, 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource3;
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
                if ((i & 16) != 0) {
                    shape3 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1561filledTonalIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    colors2 = iconToggleButtonColors;
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier9 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    MutableInteractionSource mutableInteractionSource5 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier9;
                    interactionSource3 = mutableInteractionSource5;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                } else {
                    modifier3 = modifier2;
                    interactionSource3 = interactionSource;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
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
                if ((i & 16) != 0) {
                    shape3 = IconButtonDefaults.INSTANCE.getFilledShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1561filledTonalIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    colors2 = iconToggleButtonColors;
                }
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier10 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    MutableInteractionSource mutableInteractionSource6 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier10;
                    interactionSource3 = mutableInteractionSource6;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                } else {
                    modifier3 = modifier2;
                    interactionSource3 = interactionSource;
                    $dirty = $dirty2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1676089246, $dirty, -1, "androidx.compose.material3.FilledTonalIconToggleButton (IconButton.kt:377)");
            }
            final int $dirty5 = $dirty;
            enabled4 = enabled3;
            $composer2 = $composer3;
            colors4 = colors3;
            modifier4 = modifier3;
            SurfaceKt.m1808Surfaced85dljk(checked, onCheckedChange, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconToggleButton.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4611getCheckboxo7Vup1c());
                }
            }, 1, null), enabled4, shape4, colors3.containerColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), 0.0f, 0.0f, (BorderStroke) null, interactionSource3, ComposableLambdaKt.composableLambda($composer3, -58218680, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconToggleButton.3
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
                    ComposerKt.sourceInformation($composer4, "C396@19473L157:IconButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-58218680, $changed2, -1, "androidx.compose.material3.FilledTonalIconToggleButton.<anonymous> (IconButton.kt:395)");
                        }
                        Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, FilledTonalIconButtonTokens.INSTANCE.m2247getContainerSizeD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i8 = $dirty5;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(constructor);
                        } else {
                            $composer4.useNode();
                        }
                        $composer4.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer4.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i9 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i10 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 919448265, "C400@19615L9:IconButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i8 >> 21) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty5 & 14) | ($dirty5 & 112) | ($dirty5 & 7168) | ($dirty5 & 57344), (($dirty5 >> 18) & 14) | 48, 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource3;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier11 = modifier4;
        final boolean z3 = enabled4;
        final Shape shape6 = shape4;
        final IconToggleButtonColors iconToggleButtonColors3 = colors4;
        final MutableInteractionSource mutableInteractionSource7 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.FilledTonalIconToggleButton.4
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

            public final void invoke(Composer composer, int i8) {
                IconButtonKt.FilledTonalIconToggleButton(checked, onCheckedChange, modifier11, z3, shape6, iconToggleButtonColors3, mutableInteractionSource7, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:109:0x0163 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:110:0x0165  */
    /* JADX WARN: Code duplicated, block: B:112:0x016c  */
    /* JADX WARN: Code duplicated, block: B:115:0x0172  */
    /* JADX WARN: Code duplicated, block: B:118:0x0180  */
    /* JADX WARN: Code duplicated, block: B:121:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:123:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:125:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:126:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:128:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:131:0x020c  */
    /* JADX WARN: Code duplicated, block: B:134:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:139:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:141:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x0122  */
    /* JADX WARN: Code duplicated, block: B:96:0x012f  */
    public static final void OutlinedIconButton(final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, IconButtonColors colors, BorderStroke border, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        IconButtonColors colors2;
        BorderStroke border2;
        MutableInteractionSource interactionSource2;
        int i2;
        MutableInteractionSource interactionSource3;
        Modifier modifier3;
        boolean enabled3;
        Shape shape3;
        IconButtonColors colors3;
        BorderStroke border3;
        final int $dirty;
        Object it$iv$iv;
        Object value$iv$iv;
        Modifier modifier4;
        IconButtonColors colors4;
        boolean enabled4;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-1746603025);
        ComposerKt.sourceInformation($composer3, "C(OutlinedIconButton)P(6,5,3,7,1!1,4)446@22052L13,447@22117L26,448@22192L33,449@22277L39,456@22509L23,457@22566L21,451@22358L464:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
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
                colors2 = colors;
                int i6 = $composer3.changed(colors2) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i6;
        } else {
            colors2 = colors;
        }
        if ((458752 & $changed) == 0) {
            if ((i & 32) == 0) {
                border2 = border;
                int i7 = $composer3.changed(border2) ? 131072 : 65536;
                $dirty2 |= i7;
            } else {
                border2 = border;
            }
            $dirty2 |= i7;
        } else {
            border2 = border;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty2 |= 1572864;
            interactionSource2 = interactionSource;
        } else if ((3670016 & $changed) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer3.changed(interactionSource2) ? 1048576 : 524288;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((i & 128) == 0) {
            if ((29360128 & $changed) == 0) {
                i2 = $composer3.changedInstance(content) ? 8388608 : 4194304;
            }
            if ((23967451 & $dirty2) == 4793490 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    }
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                        shape2 = IconButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                        colors2 = IconButtonDefaults.INSTANCE.m1564outlinedIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    }
                    if ((i & 32) != 0) {
                        BorderStroke border4 = IconButtonDefaults.INSTANCE.outlinedIconButtonBorder(enabled2, $composer3, (($dirty2 >> 6) & 14) | 48);
                        $dirty2 &= -458753;
                        border2 = border4;
                    }
                    if (i8 != 0) {
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        it$iv$iv = $composer3.rememberedValue();
                        int $dirty3 = $dirty2;
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        interactionSource3 = (MutableInteractionSource) value$iv$iv;
                        modifier3 = modifier2;
                        enabled3 = enabled2;
                        shape3 = shape2;
                        colors3 = colors2;
                        border3 = border2;
                        $dirty = $dirty3;
                    } else {
                        interactionSource3 = interactionSource;
                        modifier3 = modifier2;
                        enabled3 = enabled2;
                        shape3 = shape2;
                        colors3 = colors2;
                        border3 = border2;
                        $dirty = $dirty2;
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
                        shape3 = shape2;
                        colors3 = colors2;
                        border3 = border2;
                        interactionSource3 = interactionSource2;
                        $dirty = $dirty2 & (-458753);
                        modifier3 = modifier2;
                        enabled3 = enabled2;
                    } else {
                        modifier3 = modifier2;
                        shape3 = shape2;
                        colors3 = colors2;
                        border3 = border2;
                        interactionSource3 = interactionSource2;
                        $dirty = $dirty2;
                        enabled3 = enabled2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1746603025, $dirty, -1, "androidx.compose.material3.OutlinedIconButton (IconButton.kt:442)");
                }
                modifier4 = modifier3;
                int $dirty4 = $dirty;
                colors4 = colors3;
                enabled4 = enabled3;
                $composer2 = $composer3;
                SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconButton.2
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
                }, 1, null), enabled4, shape3, colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), 0.0f, 0.0f, border3, interactionSource3, ComposableLambdaKt.composableLambda($composer3, 582332538, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconButton.3
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
                        ComposerKt.sourceInformation($composer4, "C461@22666L154:IconButton.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(582332538, $changed2, -1, "androidx.compose.material3.OutlinedIconButton.<anonymous> (IconButton.kt:460)");
                            }
                            Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, OutlinedIconButtonTokens.INSTANCE.m2334getContainerSizeD9Ej5fM());
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function2 = content;
                            int i9 = $dirty;
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            int $changed$iv$iv = (54 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume = $composer4.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density density$iv$iv = (Density) objConsume;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer4.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer4.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                $composer4.createNode(constructor);
                            } else {
                                $composer4.useNode();
                            }
                            $composer4.disableReusing();
                            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                            $composer4.enableReusing();
                            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i10 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i11 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -369022041, "C465@22805L9:IconButton.kt#uh7d8r");
                            function2.invoke($composer4, Integer.valueOf((i9 >> 21) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }), $composer2, ($dirty4 & 14) | ($dirty4 & 896) | ($dirty4 & 7168) | (($dirty4 << 9) & 234881024) | (($dirty4 << 9) & 1879048192), 6, 192);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                border2 = border3;
                interactionSource2 = interactionSource3;
            } else {
                $composer3.skipToGroupEnd();
                $composer2 = $composer3;
                modifier4 = modifier2;
                enabled4 = enabled2;
                shape3 = shape2;
                colors4 = colors2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final boolean z = enabled4;
            final Shape shape4 = shape3;
            final IconButtonColors iconButtonColors = colors4;
            final BorderStroke borderStroke = border2;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconButton.4
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

                public final void invoke(Composer composer, int i9) {
                    IconButtonKt.OutlinedIconButton(onClick, modifier5, z, shape4, iconButtonColors, borderStroke, mutableInteractionSource, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 12582912;
        $dirty2 |= i2;
        if ((23967451 & $dirty2) == 4793490) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1564outlinedIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                }
                if ((i & 32) != 0) {
                    BorderStroke border5 = IconButtonDefaults.INSTANCE.outlinedIconButtonBorder(enabled2, $composer3, (($dirty2 >> 6) & 14) | 48);
                    $dirty2 &= -458753;
                    border2 = border5;
                }
                if (i8 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty5 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    $dirty = $dirty5;
                } else {
                    interactionSource3 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1564outlinedIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                }
                if ((i & 32) != 0) {
                    BorderStroke border6 = IconButtonDefaults.INSTANCE.outlinedIconButtonBorder(enabled2, $composer3, (($dirty2 >> 6) & 14) | 48);
                    $dirty2 &= -458753;
                    border2 = border6;
                }
                if (i8 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty6 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    $dirty = $dirty6;
                } else {
                    interactionSource3 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1746603025, $dirty, -1, "androidx.compose.material3.OutlinedIconButton (IconButton.kt:442)");
            }
            modifier4 = modifier3;
            int $dirty7 = $dirty;
            colors4 = colors3;
            enabled4 = enabled3;
            $composer2 = $composer3;
            SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconButton.2
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
            }, 1, null), enabled4, shape3, colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), 0.0f, 0.0f, border3, interactionSource3, ComposableLambdaKt.composableLambda($composer3, 582332538, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconButton.3
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
                    ComposerKt.sourceInformation($composer4, "C461@22666L154:IconButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(582332538, $changed2, -1, "androidx.compose.material3.OutlinedIconButton.<anonymous> (IconButton.kt:460)");
                        }
                        Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, OutlinedIconButtonTokens.INSTANCE.m2334getContainerSizeD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i9 = $dirty;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(constructor);
                        } else {
                            $composer4.useNode();
                        }
                        $composer4.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer4.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i10 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i11 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -369022041, "C465@22805L9:IconButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i9 >> 21) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty7 & 14) | ($dirty7 & 896) | ($dirty7 & 7168) | (($dirty7 << 9) & 234881024) | (($dirty7 << 9) & 1879048192), 6, 192);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border3;
            interactionSource2 = interactionSource3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1564outlinedIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                }
                if ((i & 32) != 0) {
                    BorderStroke border7 = IconButtonDefaults.INSTANCE.outlinedIconButtonBorder(enabled2, $composer3, (($dirty2 >> 6) & 14) | 48);
                    $dirty2 &= -458753;
                    border2 = border7;
                }
                if (i8 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty8 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    $dirty = $dirty8;
                } else {
                    interactionSource3 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = IconButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    colors2 = IconButtonDefaults.INSTANCE.m1564outlinedIconButtonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                }
                if ((i & 32) != 0) {
                    BorderStroke border8 = IconButtonDefaults.INSTANCE.outlinedIconButtonBorder(enabled2, $composer3, (($dirty2 >> 6) & 14) | 48);
                    $dirty2 &= -458753;
                    border2 = border8;
                }
                if (i8 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty9 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource3 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    $dirty = $dirty9;
                } else {
                    interactionSource3 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    colors3 = colors2;
                    border3 = border2;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1746603025, $dirty, -1, "androidx.compose.material3.OutlinedIconButton (IconButton.kt:442)");
            }
            modifier4 = modifier3;
            int $dirty10 = $dirty;
            colors4 = colors3;
            enabled4 = enabled3;
            $composer2 = $composer3;
            SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconButton.2
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
            }, 1, null), enabled4, shape3, colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), 0.0f, 0.0f, border3, interactionSource3, ComposableLambdaKt.composableLambda($composer3, 582332538, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconButton.3
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
                    ComposerKt.sourceInformation($composer4, "C461@22666L154:IconButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(582332538, $changed2, -1, "androidx.compose.material3.OutlinedIconButton.<anonymous> (IconButton.kt:460)");
                        }
                        Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, OutlinedIconButtonTokens.INSTANCE.m2334getContainerSizeD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i9 = $dirty;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(constructor);
                        } else {
                            $composer4.useNode();
                        }
                        $composer4.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer4.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i10 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i11 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -369022041, "C465@22805L9:IconButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i9 >> 21) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty10 & 14) | ($dirty10 & 896) | ($dirty10 & 7168) | (($dirty10 << 9) & 234881024) | (($dirty10 << 9) & 1879048192), 6, 192);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border3;
            interactionSource2 = interactionSource3;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final boolean z2 = enabled4;
        final Shape shape5 = shape3;
        final IconButtonColors iconButtonColors2 = colors4;
        final BorderStroke borderStroke2 = border2;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconButton.4
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

            public final void invoke(Composer composer, int i9) {
                IconButtonKt.OutlinedIconButton(onClick, modifier6, z2, shape5, iconButtonColors2, borderStroke2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:104:0x0142  */
    /* JADX WARN: Code duplicated, block: B:106:0x0152  */
    /* JADX WARN: Code duplicated, block: B:119:0x0188 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:120:0x018a  */
    /* JADX WARN: Code duplicated, block: B:121:0x018f  */
    /* JADX WARN: Code duplicated, block: B:123:0x0193  */
    /* JADX WARN: Code duplicated, block: B:124:0x0195  */
    /* JADX WARN: Code duplicated, block: B:127:0x019a  */
    /* JADX WARN: Code duplicated, block: B:128:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:131:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:132:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:135:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:136:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:138:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:140:0x0206  */
    /* JADX WARN: Code duplicated, block: B:141:0x0211  */
    /* JADX WARN: Code duplicated, block: B:143:0x0227  */
    /* JADX WARN: Code duplicated, block: B:146:0x023e  */
    /* JADX WARN: Code duplicated, block: B:149:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:154:0x02f0  */
    /* JADX WARN: Code duplicated, block: B:156:? A[RETURN, SYNTHETIC] */
    public static final void OutlinedIconToggleButton(final boolean checked, final Function1<? super Boolean, Unit> onCheckedChange, Modifier modifier, boolean enabled, Shape shape, IconToggleButtonColors colors, BorderStroke border, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Shape shape2;
        IconToggleButtonColors iconToggleButtonColors;
        BorderStroke border2;
        int i2;
        Modifier.Companion modifier2;
        boolean enabled2;
        Shape shape3;
        IconToggleButtonColors colors2;
        BorderStroke border3;
        MutableInteractionSource interactionSource2;
        boolean enabled3;
        Shape shape4;
        IconToggleButtonColors colors3;
        BorderStroke border4;
        int $dirty;
        Object it$iv$iv;
        Object value$iv$iv;
        Modifier modifier3;
        IconToggleButtonColors colors4;
        boolean enabled4;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onCheckedChange, "onCheckedChange");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(1470292106);
        ComposerKt.sourceInformation($composer3, "C(OutlinedIconToggleButton)P(1,7,6,4,8,2!1,5)508@25156L13,509@25227L32,510@25308L48,511@25408L39,519@25681L32,520@25747L30,513@25489L523:IconButton.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changed(checked) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changedInstance(onCheckedChange) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            z = enabled;
        } else if (($changed & 7168) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i5 = $composer3.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        if ((458752 & $changed) == 0) {
            if ((i & 32) == 0) {
                iconToggleButtonColors = colors;
                int i6 = $composer3.changed(iconToggleButtonColors) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                iconToggleButtonColors = colors;
            }
            $dirty2 |= i6;
        } else {
            iconToggleButtonColors = colors;
        }
        if ((3670016 & $changed) == 0) {
            if ((i & 64) == 0) {
                border2 = border;
                int i7 = $composer3.changed(border2) ? 1048576 : 524288;
                $dirty2 |= i7;
            } else {
                border2 = border;
            }
            $dirty2 |= i7;
        } else {
            border2 = border;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 8388608 : 4194304;
        }
        if ((i & 256) == 0) {
            if ((234881024 & $changed) == 0) {
                i2 = $composer3.changedInstance(content) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
            }
            if ((191739611 & $dirty2) == 38347922 || !$composer3.getSkipping()) {
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
                    if ((i & 16) != 0) {
                        shape3 = IconButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                        $dirty2 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 32) != 0) {
                        colors2 = IconButtonDefaults.INSTANCE.m1565outlinedIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                        $dirty2 &= -458753;
                    } else {
                        colors2 = iconToggleButtonColors;
                    }
                    if ((i & 64) != 0) {
                        border3 = IconButtonDefaults.INSTANCE.outlinedIconToggleButtonBorder(enabled2, checked, $composer3, (($dirty2 >> 9) & 14) | 384 | (($dirty2 << 3) & 112));
                        $dirty2 &= -3670017;
                    } else {
                        border3 = border2;
                    }
                    if (i8 != 0) {
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        Modifier modifier4 = modifier2;
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
                        enabled3 = enabled2;
                        shape4 = shape3;
                        colors3 = colors2;
                        border4 = border3;
                        modifier2 = modifier4;
                        $dirty = $dirty3;
                    } else {
                        interactionSource2 = interactionSource;
                        enabled3 = enabled2;
                        shape4 = shape3;
                        colors3 = colors2;
                        border4 = border3;
                        $dirty = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                    }
                    if ((i & 64) != 0) {
                        interactionSource2 = interactionSource;
                        enabled3 = z;
                        shape4 = shape2;
                        border4 = border2;
                        colors3 = iconToggleButtonColors;
                        $dirty = $dirty2 & (-3670017);
                        modifier2 = modifier;
                    } else {
                        modifier2 = modifier;
                        interactionSource2 = interactionSource;
                        enabled3 = z;
                        shape4 = shape2;
                        border4 = border2;
                        colors3 = iconToggleButtonColors;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1470292106, $dirty, -1, "androidx.compose.material3.OutlinedIconToggleButton (IconButton.kt:503)");
                }
                final int $dirty4 = $dirty;
                modifier3 = modifier2;
                colors4 = colors3;
                enabled4 = enabled3;
                $composer2 = $composer3;
                SurfaceKt.m1808Surfaced85dljk(checked, onCheckedChange, SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconToggleButton.2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semantics) {
                        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                        SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4611getCheckboxo7Vup1c());
                    }
                }, 1, null), enabled4, shape4, colors3.containerColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), 0.0f, 0.0f, border4, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 1207657396, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconToggleButton.3
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
                        ComposerKt.sourceInformation($composer4, "C524@25856L154:IconButton.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1207657396, $changed2, -1, "androidx.compose.material3.OutlinedIconToggleButton.<anonymous> (IconButton.kt:523)");
                            }
                            Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, OutlinedIconButtonTokens.INSTANCE.m2334getContainerSizeD9Ej5fM());
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function2 = content;
                            int i9 = $dirty4;
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            int $changed$iv$iv = (54 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume = $composer4.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density density$iv$iv = (Density) objConsume;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer4.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer4.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                $composer4.createNode(constructor);
                            } else {
                                $composer4.useNode();
                            }
                            $composer4.disableReusing();
                            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                            $composer4.enableReusing();
                            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i10 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i11 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -866750295, "C528@25995L9:IconButton.kt#uh7d8r");
                            function2.invoke($composer4, Integer.valueOf((i9 >> 24) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }), $composer2, ($dirty4 & 14) | ($dirty4 & 112) | ($dirty4 & 7168) | ($dirty4 & 57344) | (1879048192 & ($dirty4 << 9)), (($dirty4 >> 21) & 14) | 48, 384);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                border2 = border4;
            } else {
                $composer3.skipToGroupEnd();
                modifier3 = modifier;
                interactionSource2 = interactionSource;
                $composer2 = $composer3;
                enabled4 = z;
                shape4 = shape2;
                colors4 = iconToggleButtonColors;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier3;
            final boolean z2 = enabled4;
            final Shape shape5 = shape4;
            final IconToggleButtonColors iconToggleButtonColors2 = colors4;
            final BorderStroke borderStroke = border2;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconToggleButton.4
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

                public final void invoke(Composer composer, int i9) {
                    IconButtonKt.OutlinedIconToggleButton(checked, onCheckedChange, modifier5, z2, shape5, iconToggleButtonColors2, borderStroke, mutableInteractionSource, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 100663296;
        $dirty2 |= i2;
        if ((191739611 & $dirty2) == 38347922) {
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
                if ((i & 16) != 0) {
                    shape3 = IconButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1565outlinedIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    colors2 = iconToggleButtonColors;
                }
                if ((i & 64) != 0) {
                    border3 = IconButtonDefaults.INSTANCE.outlinedIconToggleButtonBorder(enabled2, checked, $composer3, (($dirty2 >> 9) & 14) | 384 | (($dirty2 << 3) & 112));
                    $dirty2 &= -3670017;
                } else {
                    border3 = border2;
                }
                if (i8 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier6 = modifier2;
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
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                    border4 = border3;
                    modifier2 = modifier6;
                    $dirty = $dirty5;
                } else {
                    interactionSource2 = interactionSource;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                    border4 = border3;
                    $dirty = $dirty2;
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
                if ((i & 16) != 0) {
                    shape3 = IconButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1565outlinedIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    colors2 = iconToggleButtonColors;
                }
                if ((i & 64) != 0) {
                    border3 = IconButtonDefaults.INSTANCE.outlinedIconToggleButtonBorder(enabled2, checked, $composer3, (($dirty2 >> 9) & 14) | 384 | (($dirty2 << 3) & 112));
                    $dirty2 &= -3670017;
                } else {
                    border3 = border2;
                }
                if (i8 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier7 = modifier2;
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
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                    border4 = border3;
                    modifier2 = modifier7;
                    $dirty = $dirty6;
                } else {
                    interactionSource2 = interactionSource;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                    border4 = border3;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1470292106, $dirty, -1, "androidx.compose.material3.OutlinedIconToggleButton (IconButton.kt:503)");
            }
            final int $dirty7 = $dirty;
            modifier3 = modifier2;
            colors4 = colors3;
            enabled4 = enabled3;
            $composer2 = $composer3;
            SurfaceKt.m1808Surfaced85dljk(checked, onCheckedChange, SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconToggleButton.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4611getCheckboxo7Vup1c());
                }
            }, 1, null), enabled4, shape4, colors3.containerColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), 0.0f, 0.0f, border4, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 1207657396, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconToggleButton.3
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
                    ComposerKt.sourceInformation($composer4, "C524@25856L154:IconButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1207657396, $changed2, -1, "androidx.compose.material3.OutlinedIconToggleButton.<anonymous> (IconButton.kt:523)");
                        }
                        Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, OutlinedIconButtonTokens.INSTANCE.m2334getContainerSizeD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i9 = $dirty7;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(constructor);
                        } else {
                            $composer4.useNode();
                        }
                        $composer4.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer4.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i10 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i11 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -866750295, "C528@25995L9:IconButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i9 >> 24) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty7 & 14) | ($dirty7 & 112) | ($dirty7 & 7168) | ($dirty7 & 57344) | (1879048192 & ($dirty7 << 9)), (($dirty7 >> 21) & 14) | 48, 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border4;
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
                if ((i & 16) != 0) {
                    shape3 = IconButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1565outlinedIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    colors2 = iconToggleButtonColors;
                }
                if ((i & 64) != 0) {
                    border3 = IconButtonDefaults.INSTANCE.outlinedIconToggleButtonBorder(enabled2, checked, $composer3, (($dirty2 >> 9) & 14) | 384 | (($dirty2 << 3) & 112));
                    $dirty2 &= -3670017;
                } else {
                    border3 = border2;
                }
                if (i8 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier8 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty8 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                    border4 = border3;
                    modifier2 = modifier8;
                    $dirty = $dirty8;
                } else {
                    interactionSource2 = interactionSource;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                    border4 = border3;
                    $dirty = $dirty2;
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
                if ((i & 16) != 0) {
                    shape3 = IconButtonDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    colors2 = IconButtonDefaults.INSTANCE.m1565outlinedIconToggleButtonColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    colors2 = iconToggleButtonColors;
                }
                if ((i & 64) != 0) {
                    border3 = IconButtonDefaults.INSTANCE.outlinedIconToggleButtonBorder(enabled2, checked, $composer3, (($dirty2 >> 9) & 14) | 384 | (($dirty2 << 3) & 112));
                    $dirty2 &= -3670017;
                } else {
                    border3 = border2;
                }
                if (i8 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier9 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty9 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                    border4 = border3;
                    modifier2 = modifier9;
                    $dirty = $dirty9;
                } else {
                    interactionSource2 = interactionSource;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    colors3 = colors2;
                    border4 = border3;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1470292106, $dirty, -1, "androidx.compose.material3.OutlinedIconToggleButton (IconButton.kt:503)");
            }
            final int $dirty10 = $dirty;
            modifier3 = modifier2;
            colors4 = colors3;
            enabled4 = enabled3;
            $composer2 = $composer3;
            SurfaceKt.m1808Surfaced85dljk(checked, onCheckedChange, SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconToggleButton.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.m4624setRolekuIjeqM(semantics, Role.INSTANCE.m4611getCheckboxo7Vup1c());
                }
            }, 1, null), enabled4, shape4, colors3.containerColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, checked, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 9) & 896)).getValue().m2981unboximpl(), 0.0f, 0.0f, border4, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 1207657396, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconToggleButton.3
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
                    ComposerKt.sourceInformation($composer4, "C524@25856L154:IconButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1207657396, $changed2, -1, "androidx.compose.material3.OutlinedIconToggleButton.<anonymous> (IconButton.kt:523)");
                        }
                        Modifier modifier$iv = SizeKt.m534size3ABfNKs(Modifier.INSTANCE, OutlinedIconButtonTokens.INSTANCE.m2334getContainerSizeD9Ej5fM());
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i9 = $dirty10;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        int $changed$iv$iv = (54 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            $composer4.createNode(constructor);
                        } else {
                            $composer4.useNode();
                        }
                        $composer4.disableReusing();
                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                        $composer4.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i10 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i11 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -866750295, "C528@25995L9:IconButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i9 >> 24) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, ($dirty10 & 14) | ($dirty10 & 112) | ($dirty10 & 7168) | ($dirty10 & 57344) | (1879048192 & ($dirty10 << 9)), (($dirty10 >> 21) & 14) | 48, 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border4;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier10 = modifier3;
        final boolean z3 = enabled4;
        final Shape shape6 = shape4;
        final IconToggleButtonColors iconToggleButtonColors3 = colors4;
        final BorderStroke borderStroke2 = border2;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.IconButtonKt.OutlinedIconToggleButton.4
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

            public final void invoke(Composer composer, int i9) {
                IconButtonKt.OutlinedIconToggleButton(checked, onCheckedChange, modifier10, z3, shape6, iconToggleButtonColors3, borderStroke2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }
}
