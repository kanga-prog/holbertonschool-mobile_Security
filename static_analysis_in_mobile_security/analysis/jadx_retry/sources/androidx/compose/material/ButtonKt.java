package androidx.compose.material;

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
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
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
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
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
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\u001a\u008d\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008d\u0001\u0010\u001a\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008d\u0001\u0010\u001b\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019¨\u0006\u001c²\u0006\n\u0010\u001d\u001a\u00020\u001eX\u008a\u0084\u0002"}, d2 = {"Button", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "elevation", "Landroidx/compose/material/ButtonElevation;", "shape", "Landroidx/compose/ui/graphics/Shape;", OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "colors", "Landroidx/compose/material/ButtonColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/ButtonElevation;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/material/ButtonColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "OutlinedButton", "TextButton", "material_release", "contentColor", "Landroidx/compose/ui/graphics/Color;"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ButtonKt {
    public static final void Button(final Function0<Unit> onClick, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, ButtonElevation elevation, Shape shape, BorderStroke border, ButtonColors colors, PaddingValues contentPadding, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        boolean z;
        MutableInteractionSource mutableInteractionSource;
        ButtonElevation elevation2;
        BorderStroke borderStroke;
        PaddingValues paddingValues;
        MutableInteractionSource interactionSource2;
        CornerBasedShape shape2;
        ButtonColors colors2;
        final PaddingValues contentPadding2;
        final int $dirty;
        Shape shape3;
        BorderStroke border2;
        ButtonColors colors3;
        ButtonElevation elevation3;
        Modifier modifier2;
        boolean enabled2;
        Object value$iv$iv;
        ButtonColors colors4;
        PaddingValues contentPadding3;
        ButtonElevation elevation4;
        boolean enabled3;
        Modifier modifier3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-2116133464);
        ComposerKt.sourceInformation($composer3, "C(Button)P(8,7,5,6,4,9!2,3)97@4664L39,98@4754L11,99@4800L6,101@4890L14,105@5053L21,111@5250L24,106@5079L1119:Button.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            z = enabled;
        } else if (($changed & 896) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 7168) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer3.changed(mutableInteractionSource) ? 2048 : 1024;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                elevation2 = elevation;
                int i5 = $composer3.changed(elevation2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                elevation2 = elevation;
            }
            $dirty2 |= i5;
        } else {
            elevation2 = elevation;
        }
        if ((458752 & $changed) == 0) {
            $dirty2 |= ((i & 32) == 0 && $composer3.changed(shape)) ? 131072 : 65536;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            borderStroke = border;
        } else if (($changed & 3670016) == 0) {
            borderStroke = border;
            $dirty2 |= $composer3.changed(borderStroke) ? 1048576 : 524288;
        } else {
            borderStroke = border;
        }
        if (($changed & 29360128) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer3.changed(colors)) ? 8388608 : 4194304;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty2 |= 100663296;
            paddingValues = contentPadding;
        } else if (($changed & 234881024) == 0) {
            paddingValues = contentPadding;
            $dirty2 |= $composer3.changed(paddingValues) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            paddingValues = contentPadding;
        }
        if ((i & 512) != 0) {
            $dirty2 |= 805306368;
        } else if ((1879048192 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(content) ? 536870912 : 268435456;
        }
        int $dirty3 = $dirty2;
        if ((1533916891 & $dirty3) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier;
            shape3 = shape;
            colors4 = colors;
            enabled3 = z;
            interactionSource2 = mutableInteractionSource;
            border2 = borderStroke;
            contentPadding3 = paddingValues;
            elevation4 = elevation2;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                boolean enabled4 = i3 != 0 ? true : z;
                if (i4 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Object it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    interactionSource2 = mutableInteractionSource;
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                    elevation2 = ButtonDefaults.INSTANCE.m1014elevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                }
                if ((i & 32) != 0) {
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall();
                    $dirty3 &= -458753;
                } else {
                    shape2 = shape;
                }
                BorderStroke border3 = i6 != 0 ? null : border;
                if ((i & 128) != 0) {
                    colors2 = ButtonDefaults.INSTANCE.m1013buttonColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty3 &= -29360129;
                } else {
                    colors2 = colors;
                }
                if (i7 != 0) {
                    shape3 = shape2;
                    border2 = border3;
                    colors3 = colors2;
                    contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
                    elevation3 = elevation2;
                    modifier2 = modifier4;
                    enabled2 = enabled4;
                    $dirty = $dirty3;
                } else {
                    contentPadding2 = contentPadding;
                    $dirty = $dirty3;
                    shape3 = shape2;
                    border2 = border3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    modifier2 = modifier4;
                    enabled2 = enabled4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                }
                if ((i & 128) != 0) {
                    $dirty3 &= -29360129;
                }
                shape3 = shape;
                colors3 = colors;
                $dirty = $dirty3;
                interactionSource2 = mutableInteractionSource;
                border2 = borderStroke;
                contentPadding2 = paddingValues;
                elevation3 = elevation2;
                modifier2 = modifier;
                enabled2 = z;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2116133464, $dirty, -1, "androidx.compose.material.Button (Button.kt:93)");
            }
            final State<Color> stateContentColor = colors3.contentColor(enabled2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 18) & 112));
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ButtonKt.Button.2
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
            }, 1, null);
            long jM2981unboximpl = colors3.backgroundColor(enabled2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 18) & 112)).getValue().m2981unboximpl();
            long jButton$lambda$1 = Button$lambda$1(stateContentColor);
            long jM2969copywmQWz5c = Color.m2969copywmQWz5c(jButton$lambda$1, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jButton$lambda$1) : 1.0f, (14 & 2) != 0 ? Color.m2977getRedimpl(jButton$lambda$1) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jButton$lambda$1) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jButton$lambda$1) : 0.0f);
            $composer3.startReplaceableGroup(-423487112);
            ComposerKt.sourceInformation($composer3, "114@5392L37");
            State<Dp> stateElevation = elevation3 != null ? elevation3.elevation(enabled2, interactionSource2, $composer3, (($dirty >> 6) & 896) | (($dirty >> 6) & 14) | (($dirty >> 6) & 112)) : null;
            $composer3.endReplaceableGroup();
            colors4 = colors3;
            contentPadding3 = contentPadding2;
            elevation4 = elevation3;
            enabled3 = enabled2;
            modifier3 = modifier2;
            $composer2 = $composer3;
            SurfaceKt.m1211SurfaceLPr_se0(onClick, modifierSemantics$default, enabled2, shape3, jM2981unboximpl, jM2969copywmQWz5c, border2, stateElevation != null ? stateElevation.getValue().m5288unboximpl() : Dp.m5274constructorimpl(0), interactionSource2, ComposableLambdaKt.composableLambda($composer3, 7524271, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ButtonKt.Button.3
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
                    ComposerKt.sourceInformation($composer4, "C117@5509L683:Button.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(7524271, $changed2, -1, "androidx.compose.material.Button.<anonymous> (Button.kt:116)");
                        }
                        ProvidedValue[] providedValueArr = {ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m2973getAlphaimpl(ButtonKt.Button$lambda$1(stateContentColor))))};
                        final PaddingValues paddingValues2 = contentPadding2;
                        final Function3<RowScope, Composer, Integer, Unit> function3 = content;
                        final int i8 = $dirty;
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer4, -1699085201, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ButtonKt.Button.3.1
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
                                ComposerKt.sourceInformation($composer5, "C119@5651L10,118@5595L587:Button.kt#jmzs0o");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1699085201, $changed3, -1, "androidx.compose.material.Button.<anonymous>.<anonymous> (Button.kt:117)");
                                    }
                                    TextStyle button = MaterialTheme.INSTANCE.getTypography($composer5, 6).getButton();
                                    final PaddingValues paddingValues3 = paddingValues2;
                                    final Function3<RowScope, Composer, Integer, Unit> function4 = function3;
                                    final int i9 = i8;
                                    TextKt.ProvideTextStyle(button, ComposableLambdaKt.composableLambda($composer5, -630330208, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ButtonKt.Button.3.1.1
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
                                            ComposerKt.sourceInformation($composer6, "C121@5701L467:Button.kt#jmzs0o");
                                            if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-630330208, $changed4, -1, "androidx.compose.material.Button.<anonymous>.<anonymous>.<anonymous> (Button.kt:120)");
                                                }
                                                Modifier modifier$iv = PaddingKt.padding(SizeKt.m518defaultMinSizeVpY3zN4(Modifier.INSTANCE, ButtonDefaults.INSTANCE.m1019getMinWidthD9Ej5fM(), ButtonDefaults.INSTANCE.m1018getMinHeightD9Ej5fM()), paddingValues3);
                                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getCenter();
                                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                                Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                                                int $changed$iv = ((i9 >> 18) & 7168) | 432;
                                                $composer6.startReplaceableGroup(693286680);
                                                ComposerKt.sourceInformation($composer6, "CC(Row)P(2,1,3)76@3779L58,77@3842L130:Row.kt#2w3rfo");
                                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer6, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                                $composer6.startReplaceableGroup(-1323940314);
                                                ComposerKt.sourceInformation($composer6, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                CompositionLocalMap localMap$iv$iv = $composer6.getCurrentCompositionLocalMap();
                                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
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
                                                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer6);
                                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                                }
                                                function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                                $composer6.startReplaceableGroup(2058660585);
                                                int i10 = ($changed$iv$iv$iv >> 9) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -326682417, "C78@3887L9:Row.kt#2w3rfo");
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
            }), $composer2, ($dirty & 14) | 805306368 | ($dirty & 896) | (($dirty >> 6) & 7168) | ($dirty & 3670016) | (($dirty << 15) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final boolean z2 = enabled3;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        final ButtonElevation buttonElevation = elevation4;
        final Shape shape4 = shape3;
        final BorderStroke borderStroke2 = border2;
        final ButtonColors buttonColors = colors4;
        final PaddingValues paddingValues2 = contentPadding3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ButtonKt.Button.4
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
                ButtonKt.Button(onClick, modifier5, z2, mutableInteractionSource2, buttonElevation, shape4, borderStroke2, buttonColors, paddingValues2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long Button$lambda$1(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m2981unboximpl();
    }

    public static final void OutlinedButton(Function0<Unit> onClick, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, ButtonElevation elevation, Shape shape, BorderStroke border, ButtonColors colors, PaddingValues contentPadding, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, int $changed, int i) {
        MutableInteractionSource interactionSource2;
        Object value$iv$iv;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        $composer.startReplaceableGroup(-1776134358);
        ComposerKt.sourceInformation($composer, "C(OutlinedButton)P(8,7,5,6,4,9!2,3)171@8120L39,173@8234L6,174@8291L14,175@8349L22,178@8489L270:Button.kt#jmzs0o");
        Modifier modifier2 = (i & 2) != 0 ? Modifier.INSTANCE : modifier;
        boolean enabled2 = (i & 4) != 0 ? true : enabled;
        if ((i & 8) != 0) {
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                $composer.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer.endReplaceableGroup();
            interactionSource2 = (MutableInteractionSource) value$iv$iv;
        } else {
            interactionSource2 = interactionSource;
        }
        ButtonElevation elevation2 = (i & 16) != 0 ? null : elevation;
        Shape shape2 = (i & 32) != 0 ? MaterialTheme.INSTANCE.getShapes($composer, 6).getSmall() : shape;
        BorderStroke border2 = (i & 64) != 0 ? ButtonDefaults.INSTANCE.getOutlinedBorder($composer, 6) : border;
        ButtonColors colors2 = (i & 128) != 0 ? ButtonDefaults.INSTANCE.m1021outlinedButtonColorsRGew2ao(0L, 0L, 0L, $composer, 3072, 7) : colors;
        PaddingValues contentPadding2 = (i & 256) != 0 ? ButtonDefaults.INSTANCE.getContentPadding() : contentPadding;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1776134358, $changed, -1, "androidx.compose.material.OutlinedButton (Button.kt:167)");
        }
        Button(onClick, modifier2, enabled2, interactionSource2, elevation2, shape2, border2, colors2, contentPadding2, content, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (57344 & $changed) | (458752 & $changed) | (3670016 & $changed) | (29360128 & $changed) | (234881024 & $changed) | (1879048192 & $changed), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    public static final void TextButton(Function0<Unit> onClick, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, ButtonElevation elevation, Shape shape, BorderStroke border, ButtonColors colors, PaddingValues contentPadding, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, int $changed, int i) {
        MutableInteractionSource interactionSource2;
        Object value$iv$iv;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        $composer.startReplaceableGroup(288797557);
        ComposerKt.sourceInformation($composer, "C(TextButton)P(8,7,5,6,4,9!2,3)225@10691L39,227@10805L6,229@10895L18,232@11041L270:Button.kt#jmzs0o");
        Modifier modifier2 = (i & 2) != 0 ? Modifier.INSTANCE : modifier;
        boolean enabled2 = (i & 4) != 0 ? true : enabled;
        if ((i & 8) != 0) {
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                $composer.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer.endReplaceableGroup();
            interactionSource2 = (MutableInteractionSource) value$iv$iv;
        } else {
            interactionSource2 = interactionSource;
        }
        ButtonElevation elevation2 = (i & 16) != 0 ? null : elevation;
        Shape shape2 = (i & 32) != 0 ? MaterialTheme.INSTANCE.getShapes($composer, 6).getSmall() : shape;
        BorderStroke border2 = (i & 64) != 0 ? null : border;
        ButtonColors colors2 = (i & 128) != 0 ? ButtonDefaults.INSTANCE.m1022textButtonColorsRGew2ao(0L, 0L, 0L, $composer, 3072, 7) : colors;
        PaddingValues contentPadding2 = (i & 256) != 0 ? ButtonDefaults.INSTANCE.getTextButtonContentPadding() : contentPadding;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(288797557, $changed, -1, "androidx.compose.material.TextButton (Button.kt:221)");
        }
        Button(onClick, modifier2, enabled2, interactionSource2, elevation2, shape2, border2, colors2, contentPadding2, content, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (57344 & $changed) | (458752 & $changed) | (3670016 & $changed) | (29360128 & $changed) | (234881024 & $changed) | (1879048192 & $changed), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }
}
