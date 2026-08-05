package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Card.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0081\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a_\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0018\u001au\u0010\u0019\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u001a\u001aS\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u001b\u001a\u007f\u0010\u001c\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a]\u0010\u001c\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0018¨\u0006\u001d"}, d2 = {"Card", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/CardColors;", "elevation", "Landroidx/compose/material3/CardElevation;", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Landroidx/compose/foundation/BorderStroke;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ElevatedCard", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "OutlinedCard", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CardKt {
    /* JADX WARN: Code duplicated, block: B:101:0x014a  */
    /* JADX WARN: Code duplicated, block: B:102:0x0161  */
    /* JADX WARN: Code duplicated, block: B:104:0x0164  */
    /* JADX WARN: Code duplicated, block: B:105:0x0166  */
    /* JADX WARN: Code duplicated, block: B:108:0x0171  */
    /* JADX WARN: Code duplicated, block: B:111:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:115:0x020c  */
    /* JADX WARN: Code duplicated, block: B:116:0x020f  */
    /* JADX WARN: Code duplicated, block: B:73:0x00db  */
    /* JADX WARN: Code duplicated, block: B:75:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:88:0x0108 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:89:0x010a  */
    /* JADX WARN: Code duplicated, block: B:90:0x010f  */
    /* JADX WARN: Code duplicated, block: B:93:0x0114  */
    /* JADX WARN: Code duplicated, block: B:94:0x011d  */
    /* JADX WARN: Code duplicated, block: B:97:0x0122  */
    /* JADX WARN: Code duplicated, block: B:98:0x0143  */
    public static final void Card(Modifier modifier, Shape shape, CardColors colors, CardElevation elevation, BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        BorderStroke borderStroke;
        int i2;
        Modifier.Companion modifier3;
        Shape shape3;
        CardColors colors2;
        CardElevation elevation2;
        BorderStroke border2;
        Modifier modifier4;
        Shape shape4;
        CardColors colors3;
        CardElevation elevation3;
        BorderStroke border3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1179621553);
        ComposerKt.sourceInformation($composer2, "C(Card)P(4,5,1,3)77@3629L5,78@3674L12,79@3732L15,86@3923L30,87@3991L28,88@4062L56,89@4162L57,83@3839L460:Card.kt#uh7d8r");
        final int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 14) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 112) == 0) {
            if ((i & 2) == 0) {
                shape2 = shape;
                int i4 = $composer2.changed(shape2) ? 32 : 16;
                $dirty |= i4;
            } else {
                shape2 = shape;
            }
            $dirty |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                cardColors = colors;
                int i5 = $composer2.changed(cardColors) ? 256 : 128;
                $dirty |= i5;
            } else {
                cardColors = colors;
            }
            $dirty |= i5;
        } else {
            cardColors = colors;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                cardElevation = elevation;
                int i6 = $composer2.changed(cardElevation) ? 2048 : 1024;
                $dirty |= i6;
            } else {
                cardElevation = elevation;
            }
            $dirty |= i6;
        } else {
            cardElevation = elevation;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty |= 24576;
            borderStroke = border;
        } else if ((57344 & $changed) == 0) {
            borderStroke = border;
            $dirty |= $composer2.changed(borderStroke) ? 16384 : 8192;
        } else {
            borderStroke = border;
        }
        if ((i & 32) == 0) {
            if ((458752 & $changed) == 0) {
                i2 = $composer2.changedInstance(content) ? 131072 : 65536;
            }
            if ((374491 & $dirty) == 74898 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if ((i & 2) != 0) {
                        shape3 = CardDefaults.INSTANCE.getShape($composer2, 6);
                        $dirty &= -113;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 4) != 0) {
                        colors2 = CardDefaults.INSTANCE.m1346cardColorsro_MJ88(0L, 0L, 0L, 0L, $composer2, 24576, 15);
                        $dirty &= -897;
                    } else {
                        colors2 = cardColors;
                    }
                    if ((i & 8) != 0) {
                        elevation2 = CardDefaults.INSTANCE.m1347cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, 1572864, 63);
                        $dirty &= -7169;
                    } else {
                        elevation2 = cardElevation;
                    }
                    if (i7 != 0) {
                        border2 = null;
                    } else {
                        border2 = border;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 2) != 0) {
                        $dirty &= -113;
                    }
                    if ((i & 4) != 0) {
                        $dirty &= -897;
                    }
                    if ((i & 8) != 0) {
                        $dirty &= -7169;
                    }
                    modifier3 = modifier2;
                    shape3 = shape2;
                    colors2 = cardColors;
                    elevation2 = cardElevation;
                    border2 = borderStroke;
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1179621553, $dirty, -1, "androidx.compose.material3.Card (Card.kt:75)");
                }
                SurfaceKt.m1806SurfaceT9BRK9s(modifier3, shape3, colors2.containerColor$material3_release(true, $composer2, (($dirty >> 3) & 112) | 6).getValue().m2981unboximpl(), colors2.contentColor$material3_release(true, $composer2, (($dirty >> 3) & 112) | 6).getValue().m2981unboximpl(), elevation2.tonalElevation$material3_release(true, null, $composer2, (($dirty >> 3) & 896) | 54).getValue().m5288unboximpl(), elevation2.shadowElevation$material3_release(true, null, $composer2, (($dirty >> 3) & 896) | 54).getValue().m5288unboximpl(), border2, ComposableLambdaKt.composableLambda($composer2, 664103990, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.1
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

                    public final void invoke(Composer $composer3, int $changed2) {
                        ComposerKt.sourceInformation($composer3, "C92@4268L25:Card.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(664103990, $changed2, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:91)");
                        }
                        Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                        int $changed$iv = ($dirty >> 6) & 7168;
                        $composer3.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                        int $changed$iv$iv = ($changed$iv << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer3.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer3.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer3.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
                        int i8 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                        function3.invoke(ColumnScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer2, 12582912 | ($dirty & 14) | ($dirty & 112) | (($dirty << 6) & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
                shape4 = shape3;
                colors3 = colors2;
                elevation3 = elevation2;
                border3 = border2;
            } else {
                $composer2.skipToGroupEnd();
                modifier4 = modifier2;
                shape4 = shape2;
                colors3 = cardColors;
                border3 = borderStroke;
                elevation3 = cardElevation;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final Shape shape5 = shape4;
            final CardColors cardColors2 = colors3;
            final CardElevation cardElevation2 = elevation3;
            final BorderStroke borderStroke2 = border3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.2
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
                    CardKt.Card(modifier5, shape5, cardColors2, cardElevation2, borderStroke2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty |= i2;
        if ((374491 & $dirty) == 74898) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i & 4) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1346cardColorsro_MJ88(0L, 0L, 0L, 0L, $composer2, 24576, 15);
                    $dirty &= -897;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 8) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1347cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, 1572864, 63);
                    $dirty &= -7169;
                } else {
                    elevation2 = cardElevation;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i & 4) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1346cardColorsro_MJ88(0L, 0L, 0L, 0L, $composer2, 24576, 15);
                    $dirty &= -897;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 8) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1347cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, 1572864, 63);
                    $dirty &= -7169;
                } else {
                    elevation2 = cardElevation;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1179621553, $dirty, -1, "androidx.compose.material3.Card (Card.kt:75)");
            }
            SurfaceKt.m1806SurfaceT9BRK9s(modifier3, shape3, colors2.containerColor$material3_release(true, $composer2, (($dirty >> 3) & 112) | 6).getValue().m2981unboximpl(), colors2.contentColor$material3_release(true, $composer2, (($dirty >> 3) & 112) | 6).getValue().m2981unboximpl(), elevation2.tonalElevation$material3_release(true, null, $composer2, (($dirty >> 3) & 896) | 54).getValue().m5288unboximpl(), elevation2.shadowElevation$material3_release(true, null, $composer2, (($dirty >> 3) & 896) | 54).getValue().m5288unboximpl(), border2, ComposableLambdaKt.composableLambda($composer2, 664103990, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.1
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

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C92@4268L25:Card.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(664103990, $changed2, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:91)");
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                    int $changed$iv = ($dirty >> 6) & 7168;
                    $composer3.startReplaceableGroup(-483455358);
                    ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                    int $changed$iv$iv = ($changed$iv << 3) & 112;
                    $composer3.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localDensity);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Density density$iv$iv = (Density) objConsume;
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume2 = $composer3.consume(localLayoutDirection);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume3 = $composer3.consume(localViewConfiguration);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
                    int i8 = ($changed$iv$iv$iv >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                    function3.invoke(ColumnScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, 12582912 | ($dirty & 14) | ($dirty & 112) | (($dirty << 6) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            shape4 = shape3;
            colors3 = colors2;
            elevation3 = elevation2;
            border3 = border2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i & 4) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1346cardColorsro_MJ88(0L, 0L, 0L, 0L, $composer2, 24576, 15);
                    $dirty &= -897;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 8) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1347cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, 1572864, 63);
                    $dirty &= -7169;
                } else {
                    elevation2 = cardElevation;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i & 4) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1346cardColorsro_MJ88(0L, 0L, 0L, 0L, $composer2, 24576, 15);
                    $dirty &= -897;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 8) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1347cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, 1572864, 63);
                    $dirty &= -7169;
                } else {
                    elevation2 = cardElevation;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1179621553, $dirty, -1, "androidx.compose.material3.Card (Card.kt:75)");
            }
            SurfaceKt.m1806SurfaceT9BRK9s(modifier3, shape3, colors2.containerColor$material3_release(true, $composer2, (($dirty >> 3) & 112) | 6).getValue().m2981unboximpl(), colors2.contentColor$material3_release(true, $composer2, (($dirty >> 3) & 112) | 6).getValue().m2981unboximpl(), elevation2.tonalElevation$material3_release(true, null, $composer2, (($dirty >> 3) & 896) | 54).getValue().m5288unboximpl(), elevation2.shadowElevation$material3_release(true, null, $composer2, (($dirty >> 3) & 896) | 54).getValue().m5288unboximpl(), border2, ComposableLambdaKt.composableLambda($composer2, 664103990, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.1
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

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C92@4268L25:Card.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(664103990, $changed2, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:91)");
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                    int $changed$iv = ($dirty >> 6) & 7168;
                    $composer3.startReplaceableGroup(-483455358);
                    ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                    int $changed$iv$iv = ($changed$iv << 3) & 112;
                    $composer3.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localDensity);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Density density$iv$iv = (Density) objConsume;
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume2 = $composer3.consume(localLayoutDirection);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume3 = $composer3.consume(localViewConfiguration);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
                    int i8 = ($changed$iv$iv$iv >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                    function3.invoke(ColumnScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, 12582912 | ($dirty & 14) | ($dirty & 112) | (($dirty << 6) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            shape4 = shape3;
            colors3 = colors2;
            elevation3 = elevation2;
            border3 = border2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final Shape shape6 = shape4;
        final CardColors cardColors3 = colors3;
        final CardElevation cardElevation3 = elevation3;
        final BorderStroke borderStroke3 = border3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.2
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
                CardKt.Card(modifier6, shape6, cardColors3, cardElevation3, borderStroke3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:105:0x014a  */
    /* JADX WARN: Code duplicated, block: B:107:0x0157  */
    /* JADX WARN: Code duplicated, block: B:120:0x018f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:121:0x0191  */
    /* JADX WARN: Code duplicated, block: B:122:0x0196  */
    /* JADX WARN: Code duplicated, block: B:124:0x019a  */
    /* JADX WARN: Code duplicated, block: B:125:0x019c  */
    /* JADX WARN: Code duplicated, block: B:128:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:129:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:132:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:133:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:137:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:139:0x01f3  */
    /* JADX WARN: Code duplicated, block: B:140:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:142:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:144:0x021c  */
    /* JADX WARN: Code duplicated, block: B:145:0x0227  */
    /* JADX WARN: Code duplicated, block: B:147:0x023e  */
    /* JADX WARN: Code duplicated, block: B:150:0x0258  */
    /* JADX WARN: Code duplicated, block: B:153:0x0323  */
    /* JADX WARN: Code duplicated, block: B:158:0x032f  */
    /* JADX WARN: Code duplicated, block: B:160:? A[RETURN, SYNTHETIC] */
    public static final void Card(final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, CardColors colors, CardElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        BorderStroke border2;
        int i2;
        Modifier.Companion modifier2;
        boolean enabled2;
        Shape shape3;
        CardColors colors2;
        CardElevation elevation2;
        BorderStroke border3;
        Modifier modifier3;
        final int $dirty;
        MutableInteractionSource interactionSource2;
        boolean enabled3;
        BorderStroke border4;
        Shape shape4;
        CardColors colors3;
        CardElevation elevation3;
        Object it$iv$iv;
        Object value$iv$iv;
        CardColors colors4;
        boolean enabled4;
        CardElevation elevation4;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-2024281376);
        ComposerKt.sourceInformation($composer3, "C(Card)P(7,6,4,8,1,3!1,5)135@6366L5,136@6411L12,137@6469L15,139@6570L39,147@6805L23,148@6866L21,149@6930L42,150@7016L43,142@6667L519:Card.kt#uh7d8r");
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
            z = enabled;
        } else if (($changed & 896) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
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
                cardColors = colors;
                int i6 = $composer3.changed(cardColors) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                cardColors = colors;
            }
            $dirty2 |= i6;
        } else {
            cardColors = colors;
        }
        if ((458752 & $changed) == 0) {
            if ((i & 32) == 0) {
                cardElevation = elevation;
                int i7 = $composer3.changed(cardElevation) ? 131072 : 65536;
                $dirty2 |= i7;
            } else {
                cardElevation = elevation;
            }
            $dirty2 |= i7;
        } else {
            cardElevation = elevation;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty2 |= 1572864;
            border2 = border;
        } else if ((3670016 & $changed) == 0) {
            border2 = border;
            $dirty2 |= $composer3.changed(border2) ? 1048576 : 524288;
        } else {
            border2 = border;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 8388608 : 4194304;
        }
        if ((i & 256) == 0) {
            if (($changed & 234881024) == 0) {
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
                    if ((i & 8) != 0) {
                        shape3 = CardDefaults.INSTANCE.getShape($composer3, 6);
                        $dirty2 &= -7169;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 16) != 0) {
                        colors2 = CardDefaults.INSTANCE.m1346cardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                        $dirty2 &= -57345;
                    } else {
                        colors2 = cardColors;
                    }
                    if ((i & 32) != 0) {
                        elevation2 = CardDefaults.INSTANCE.m1347cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                        $dirty2 &= -458753;
                    } else {
                        elevation2 = cardElevation;
                    }
                    if (i8 != 0) {
                        border3 = null;
                    } else {
                        border3 = border;
                    }
                    if (i9 != 0) {
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
                        modifier3 = modifier4;
                        $dirty = $dirty3;
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                        enabled3 = enabled2;
                        border4 = border3;
                        shape4 = shape3;
                        colors3 = colors2;
                        elevation3 = elevation2;
                    } else {
                        modifier3 = modifier2;
                        $dirty = $dirty2;
                        interactionSource2 = interactionSource;
                        enabled3 = enabled2;
                        border4 = border3;
                        shape4 = shape3;
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
                    if ((i & 32) != 0) {
                        modifier3 = modifier;
                        interactionSource2 = interactionSource;
                        $dirty = $dirty2 & (-458753);
                        enabled3 = z;
                        shape4 = shape2;
                        colors3 = cardColors;
                        border4 = border2;
                        elevation3 = cardElevation;
                    } else {
                        modifier3 = modifier;
                        interactionSource2 = interactionSource;
                        $dirty = $dirty2;
                        enabled3 = z;
                        shape4 = shape2;
                        colors3 = cardColors;
                        border4 = border2;
                        elevation3 = cardElevation;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2024281376, $dirty, -1, "androidx.compose.material3.Card (Card.kt:131)");
                }
                colors4 = colors3;
                enabled4 = enabled3;
                elevation4 = elevation3;
                $composer2 = $composer3;
                SurfaceKt.m1809Surfaceo_FOJdg(onClick, modifier3, enabled4, shape4, colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), elevation3.tonalElevation$material3_release(enabled3, interactionSource2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 18) & 112) | (($dirty >> 9) & 896)).getValue().m5288unboximpl(), elevation3.shadowElevation$material3_release(enabled3, interactionSource2, $composer3, (($dirty >> 9) & 896) | (($dirty >> 6) & 14) | (($dirty >> 18) & 112)).getValue().m5288unboximpl(), border4, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 776921067, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.4
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
                        ComposerKt.sourceInformation($composer4, "C154@7155L25:Card.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                            $composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(776921067, $changed2, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:153)");
                        }
                        Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                        int $changed$iv = ($dirty >> 15) & 7168;
                        $composer4.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                        int $changed$iv$iv = ($changed$iv << 3) & 112;
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
                        ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                        function3.invoke(ColumnScopeInstance.INSTANCE, $composer4, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
                        $composer4.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 6) & 234881024) | (($dirty << 6) & 1879048192), 6, 0);
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
                colors4 = cardColors;
                elevation4 = cardElevation;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier3;
            final boolean z2 = enabled4;
            final Shape shape5 = shape4;
            final CardColors cardColors2 = colors4;
            final CardElevation cardElevation2 = elevation4;
            final BorderStroke borderStroke = border2;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.5
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
                    CardKt.Card(onClick, modifier5, z2, shape5, cardColors2, cardElevation2, borderStroke, mutableInteractionSource, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
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
                if ((i & 8) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1346cardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1347cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    elevation2 = cardElevation;
                }
                if (i8 != 0) {
                    border3 = null;
                } else {
                    border3 = border;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier6 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty4 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    modifier3 = modifier6;
                    $dirty = $dirty4;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    enabled3 = enabled2;
                    border4 = border3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                } else {
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    enabled3 = enabled2;
                    border4 = border3;
                    shape4 = shape3;
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
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1346cardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1347cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    elevation2 = cardElevation;
                }
                if (i8 != 0) {
                    border3 = null;
                } else {
                    border3 = border;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier7 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty5 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    modifier3 = modifier7;
                    $dirty = $dirty5;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    enabled3 = enabled2;
                    border4 = border3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                } else {
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    enabled3 = enabled2;
                    border4 = border3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2024281376, $dirty, -1, "androidx.compose.material3.Card (Card.kt:131)");
            }
            colors4 = colors3;
            enabled4 = enabled3;
            elevation4 = elevation3;
            $composer2 = $composer3;
            SurfaceKt.m1809Surfaceo_FOJdg(onClick, modifier3, enabled4, shape4, colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), elevation3.tonalElevation$material3_release(enabled3, interactionSource2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 18) & 112) | (($dirty >> 9) & 896)).getValue().m5288unboximpl(), elevation3.shadowElevation$material3_release(enabled3, interactionSource2, $composer3, (($dirty >> 9) & 896) | (($dirty >> 6) & 14) | (($dirty >> 18) & 112)).getValue().m5288unboximpl(), border4, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 776921067, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.4
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
                    ComposerKt.sourceInformation($composer4, "C154@7155L25:Card.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(776921067, $changed2, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:153)");
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                    int $changed$iv = ($dirty >> 15) & 7168;
                    $composer4.startReplaceableGroup(-483455358);
                    ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                    int $changed$iv$iv = ($changed$iv << 3) & 112;
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
                    ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                    function3.invoke(ColumnScopeInstance.INSTANCE, $composer4, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endReplaceableGroup();
                    $composer4.endNode();
                    $composer4.endReplaceableGroup();
                    $composer4.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 6) & 234881024) | (($dirty << 6) & 1879048192), 6, 0);
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
                if ((i & 8) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1346cardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1347cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    elevation2 = cardElevation;
                }
                if (i8 != 0) {
                    border3 = null;
                } else {
                    border3 = border;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier8 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty6 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    modifier3 = modifier8;
                    $dirty = $dirty6;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    enabled3 = enabled2;
                    border4 = border3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                } else {
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    enabled3 = enabled2;
                    border4 = border3;
                    shape4 = shape3;
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
                    enabled2 = z;
                }
                if ((i & 8) != 0) {
                    shape3 = CardDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1346cardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -57345;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1347cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty2 &= -458753;
                } else {
                    elevation2 = cardElevation;
                }
                if (i8 != 0) {
                    border3 = null;
                } else {
                    border3 = border;
                }
                if (i9 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Modifier modifier9 = modifier2;
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty7 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    modifier3 = modifier9;
                    $dirty = $dirty7;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    enabled3 = enabled2;
                    border4 = border3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                } else {
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    enabled3 = enabled2;
                    border4 = border3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2024281376, $dirty, -1, "androidx.compose.material3.Card (Card.kt:131)");
            }
            colors4 = colors3;
            enabled4 = enabled3;
            elevation4 = elevation3;
            $composer2 = $composer3;
            SurfaceKt.m1809Surfaceo_FOJdg(onClick, modifier3, enabled4, shape4, colors3.containerColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), colors3.contentColor$material3_release(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 112)).getValue().m2981unboximpl(), elevation3.tonalElevation$material3_release(enabled3, interactionSource2, $composer3, (($dirty >> 6) & 14) | (($dirty >> 18) & 112) | (($dirty >> 9) & 896)).getValue().m5288unboximpl(), elevation3.shadowElevation$material3_release(enabled3, interactionSource2, $composer3, (($dirty >> 9) & 896) | (($dirty >> 6) & 14) | (($dirty >> 18) & 112)).getValue().m5288unboximpl(), border4, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 776921067, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.4
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
                    ComposerKt.sourceInformation($composer4, "C154@7155L25:Card.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(776921067, $changed2, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:153)");
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                    int $changed$iv = ($dirty >> 15) & 7168;
                    $composer4.startReplaceableGroup(-483455358);
                    ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                    int $changed$iv$iv = ($changed$iv << 3) & 112;
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
                    ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                    function3.invoke(ColumnScopeInstance.INSTANCE, $composer4, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endReplaceableGroup();
                    $composer4.endNode();
                    $composer4.endReplaceableGroup();
                    $composer4.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 6) & 234881024) | (($dirty << 6) & 1879048192), 6, 0);
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
        final CardColors cardColors3 = colors4;
        final CardElevation cardElevation3 = elevation4;
        final BorderStroke borderStroke2 = border2;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.5
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
                CardKt.Card(onClick, modifier10, z3, shape6, cardColors3, cardElevation3, borderStroke2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static final void ElevatedCard(Modifier modifier, Shape shape, CardColors colors, CardElevation elevation, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        Shape shape3;
        CardColors colors2;
        int $dirty;
        Modifier modifier3;
        Shape shape4;
        CardColors colors3;
        CardElevation elevation2;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(895940201);
        ComposerKt.sourceInformation($composer3, "C(ElevatedCard)P(3,4!1,2)185@8633L13,186@8686L20,187@8752L23,189@8829L140:Card.kt#uh7d8r");
        int $dirty2 = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 14) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 112) == 0) {
            if ((i & 2) == 0) {
                shape2 = shape;
                int i3 = $composer3.changed(shape2) ? 32 : 16;
                $dirty2 |= i3;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i3;
        } else {
            shape2 = shape;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                cardColors = colors;
                int i4 = $composer3.changed(cardColors) ? 256 : 128;
                $dirty2 |= i4;
            } else {
                cardColors = colors;
            }
            $dirty2 |= i4;
        } else {
            cardColors = colors;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                cardElevation = elevation;
                int i5 = $composer3.changed(cardElevation) ? 2048 : 1024;
                $dirty2 |= i5;
            } else {
                cardElevation = elevation;
            }
            $dirty2 |= i5;
        } else {
            cardElevation = elevation;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if ((57344 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(content) ? 16384 : 8192;
        }
        if ((46811 & $dirty2) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            shape4 = shape2;
            colors3 = cardColors;
            elevation2 = cardElevation;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getElevatedShape($composer3, 6);
                    $dirty2 &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i & 4) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1348elevatedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -897;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 8) != 0) {
                    $dirty = $dirty2 & (-7169);
                    modifier3 = modifier4;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation2 = CardDefaults.INSTANCE.m1349elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation2 = cardElevation;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                $dirty = $dirty2;
                modifier3 = modifier2;
                shape4 = shape2;
                colors3 = cardColors;
                elevation2 = cardElevation;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(895940201, $dirty, -1, "androidx.compose.material3.ElevatedCard (Card.kt:183)");
            }
            $composer2 = $composer3;
            Card(modifier3, shape4, colors3, elevation2, null, content, $composer3, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Shape shape5 = shape4;
        final CardColors cardColors2 = colors3;
        final CardElevation cardElevation2 = elevation2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.1
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

            public final void invoke(Composer composer, int i6) {
                CardKt.ElevatedCard(modifier5, shape5, cardColors2, cardElevation2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:109:0x0170 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:110:0x0172  */
    /* JADX WARN: Code duplicated, block: B:111:0x0177  */
    /* JADX WARN: Code duplicated, block: B:113:0x017a  */
    /* JADX WARN: Code duplicated, block: B:114:0x017e  */
    /* JADX WARN: Code duplicated, block: B:117:0x0184  */
    /* JADX WARN: Code duplicated, block: B:118:0x0190  */
    /* JADX WARN: Code duplicated, block: B:121:0x0196  */
    /* JADX WARN: Code duplicated, block: B:122:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:125:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:126:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:128:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:130:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:131:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:133:0x0210  */
    /* JADX WARN: Code duplicated, block: B:136:0x0223  */
    /* JADX WARN: Code duplicated, block: B:139:0x0277  */
    /* JADX WARN: Code duplicated, block: B:143:0x0281  */
    /* JADX WARN: Code duplicated, block: B:145:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x012a  */
    /* JADX WARN: Code duplicated, block: B:96:0x0137  */
    public static final void ElevatedCard(final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, CardColors colors, CardElevation elevation, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        MutableInteractionSource mutableInteractionSource;
        int i2;
        Modifier.Companion modifier3;
        boolean enabled2;
        Shape shape3;
        int $dirty;
        CardColors colors2;
        CardElevation elevation2;
        MutableInteractionSource interactionSource2;
        CardElevation elevation3;
        int $dirty2;
        CardColors colors3;
        Modifier modifier4;
        Object it$iv$iv;
        Object value$iv$iv;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-1850977784);
        ComposerKt.sourceInformation($composer3, "C(ElevatedCard)P(6,5,3,7!1,2,4)234@10976L13,235@11029L20,236@11095L23,237@11170L39,239@11263L229:Card.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty3 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
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
                cardColors = colors;
                int i6 = $composer3.changed(cardColors) ? 16384 : 8192;
                $dirty3 |= i6;
            } else {
                cardColors = colors;
            }
            $dirty3 |= i6;
        } else {
            cardColors = colors;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                cardElevation = elevation;
                int i7 = $composer3.changed(cardElevation) ? 131072 : 65536;
                $dirty3 |= i7;
            } else {
                cardElevation = elevation;
            }
            $dirty3 |= i7;
        } else {
            cardElevation = elevation;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty3 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if ((3670016 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty3 |= $composer3.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 128) == 0) {
            if (($changed & 29360128) == 0) {
                i2 = $composer3.changedInstance(content) ? 8388608 : 4194304;
            }
            if ((23967451 & $dirty3) == 4793490 || !$composer3.getSkipping()) {
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
                        $dirty3 &= -7169;
                        shape3 = CardDefaults.INSTANCE.getElevatedShape($composer3, 6);
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 16) != 0) {
                        $dirty = $dirty3 & (-57345);
                        colors2 = CardDefaults.INSTANCE.m1348elevatedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    } else {
                        $dirty = $dirty3;
                        colors2 = cardColors;
                    }
                    if ((i & 32) != 0) {
                        elevation2 = CardDefaults.INSTANCE.m1349elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                        $dirty &= -458753;
                    } else {
                        elevation2 = elevation;
                    }
                    if (i8 != 0) {
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
                        elevation3 = elevation2;
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                        $dirty2 = $dirty;
                        colors3 = colors2;
                        modifier4 = modifier3;
                    } else {
                        interactionSource2 = interactionSource;
                        elevation3 = elevation2;
                        $dirty2 = $dirty;
                        colors3 = colors2;
                        modifier4 = modifier3;
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
                        modifier4 = modifier2;
                        enabled2 = z;
                        shape3 = shape2;
                        colors3 = cardColors;
                        interactionSource2 = mutableInteractionSource;
                        elevation3 = cardElevation;
                        $dirty2 = $dirty3 & (-458753);
                    } else {
                        modifier4 = modifier2;
                        enabled2 = z;
                        shape3 = shape2;
                        colors3 = cardColors;
                        interactionSource2 = mutableInteractionSource;
                        elevation3 = cardElevation;
                        $dirty2 = $dirty3;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1850977784, $dirty2, -1, "androidx.compose.material3.ElevatedCard (Card.kt:230)");
                }
                $composer2 = $composer3;
                Card(onClick, modifier4, enabled2, shape3, colors3, elevation3, null, interactionSource2, content, $composer3, ($dirty2 & 14) | 1572864 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | ($dirty2 & 57344) | ($dirty2 & 458752) | (($dirty2 << 3) & 29360128) | (234881024 & ($dirty2 << 3)), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier4 = modifier2;
                enabled2 = z;
                shape3 = shape2;
                colors3 = cardColors;
                interactionSource2 = mutableInteractionSource;
                elevation3 = cardElevation;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final boolean z2 = enabled2;
            final Shape shape4 = shape3;
            final CardColors cardColors2 = colors3;
            final CardElevation cardElevation2 = elevation3;
            final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.3
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
                    CardKt.ElevatedCard(onClick, modifier5, z2, shape4, cardColors2, cardElevation2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 12582912;
        $dirty3 |= i2;
        if ((23967451 & $dirty3) == 4793490) {
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
                    $dirty3 &= -7169;
                    shape3 = CardDefaults.INSTANCE.getElevatedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = CardDefaults.INSTANCE.m1348elevatedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1349elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if (i8 != 0) {
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
                    elevation3 = elevation2;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier4 = modifier3;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
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
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    shape3 = CardDefaults.INSTANCE.getElevatedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = CardDefaults.INSTANCE.m1348elevatedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1349elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if (i8 != 0) {
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
                    elevation3 = elevation2;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier4 = modifier3;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier4 = modifier3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1850977784, $dirty2, -1, "androidx.compose.material3.ElevatedCard (Card.kt:230)");
            }
            $composer2 = $composer3;
            Card(onClick, modifier4, enabled2, shape3, colors3, elevation3, null, interactionSource2, content, $composer3, ($dirty2 & 14) | 1572864 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | ($dirty2 & 57344) | ($dirty2 & 458752) | (($dirty2 << 3) & 29360128) | (234881024 & ($dirty2 << 3)), 0);
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
                    $dirty3 &= -7169;
                    shape3 = CardDefaults.INSTANCE.getElevatedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = CardDefaults.INSTANCE.m1348elevatedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1349elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if (i8 != 0) {
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
                    elevation3 = elevation2;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier4 = modifier3;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
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
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    shape3 = CardDefaults.INSTANCE.getElevatedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = CardDefaults.INSTANCE.m1348elevatedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1349elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if (i8 != 0) {
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
                    elevation3 = elevation2;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier4 = modifier3;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    colors3 = colors2;
                    modifier4 = modifier3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1850977784, $dirty2, -1, "androidx.compose.material3.ElevatedCard (Card.kt:230)");
            }
            $composer2 = $composer3;
            Card(onClick, modifier4, enabled2, shape3, colors3, elevation3, null, interactionSource2, content, $composer3, ($dirty2 & 14) | 1572864 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | ($dirty2 & 57344) | ($dirty2 & 458752) | (($dirty2 << 3) & 29360128) | (234881024 & ($dirty2 << 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final boolean z3 = enabled2;
        final Shape shape5 = shape3;
        final CardColors cardColors3 = colors3;
        final CardElevation cardElevation3 = elevation3;
        final MutableInteractionSource mutableInteractionSource3 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.ElevatedCard.3
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
                CardKt.ElevatedCard(onClick, modifier6, z3, shape5, cardColors3, cardElevation3, mutableInteractionSource3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0133  */
    /* JADX WARN: Code duplicated, block: B:101:0x014f  */
    /* JADX WARN: Code duplicated, block: B:104:0x0154  */
    /* JADX WARN: Code duplicated, block: B:105:0x016b  */
    /* JADX WARN: Code duplicated, block: B:108:0x0170  */
    /* JADX WARN: Code duplicated, block: B:109:0x0183  */
    /* JADX WARN: Code duplicated, block: B:112:0x0193  */
    /* JADX WARN: Code duplicated, block: B:115:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:119:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:121:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:75:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:91:0x0118 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:92:0x011a  */
    /* JADX WARN: Code duplicated, block: B:93:0x011f  */
    /* JADX WARN: Code duplicated, block: B:96:0x0124  */
    /* JADX WARN: Code duplicated, block: B:97:0x012e  */
    public static final void OutlinedCard(Modifier modifier, Shape shape, CardColors colors, CardElevation elevation, BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        BorderStroke borderStroke;
        int i2;
        Modifier.Companion modifier3;
        Shape shape3;
        CardColors colors2;
        CardElevation elevation2;
        BorderStroke border2;
        int $dirty;
        Modifier modifier4;
        Shape shape4;
        CardColors colors3;
        CardElevation elevation3;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(740336179);
        ComposerKt.sourceInformation($composer3, "C(OutlinedCard)P(4,5,1,3)279@13027L13,280@13080L20,281@13146L23,282@13211L20,284@13285L142:Card.kt#uh7d8r");
        int $dirty2 = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 14) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 112) == 0) {
            if ((i & 2) == 0) {
                shape2 = shape;
                int i4 = $composer3.changed(shape2) ? 32 : 16;
                $dirty2 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                cardColors = colors;
                int i5 = $composer3.changed(cardColors) ? 256 : 128;
                $dirty2 |= i5;
            } else {
                cardColors = colors;
            }
            $dirty2 |= i5;
        } else {
            cardColors = colors;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                cardElevation = elevation;
                int i6 = $composer3.changed(cardElevation) ? 2048 : 1024;
                $dirty2 |= i6;
            } else {
                cardElevation = elevation;
            }
            $dirty2 |= i6;
        } else {
            cardElevation = elevation;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                borderStroke = border;
                int i7 = $composer3.changed(borderStroke) ? 16384 : 8192;
                $dirty2 |= i7;
            } else {
                borderStroke = border;
            }
            $dirty2 |= i7;
        } else {
            borderStroke = border;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
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
                    if ((i & 2) != 0) {
                        shape3 = CardDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                        $dirty2 &= -113;
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 4) != 0) {
                        colors2 = CardDefaults.INSTANCE.m1350outlinedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                        $dirty2 &= -897;
                    } else {
                        colors2 = cardColors;
                    }
                    if ((i & 8) != 0) {
                        elevation2 = CardDefaults.INSTANCE.m1351outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                        $dirty2 &= -7169;
                    } else {
                        elevation2 = cardElevation;
                    }
                    if ((i & 16) != 0) {
                        modifier4 = modifier3;
                        shape4 = shape3;
                        colors3 = colors2;
                        elevation3 = elevation2;
                        border2 = CardDefaults.INSTANCE.outlinedCardBorder(false, $composer3, 48, 1);
                        $dirty = $dirty2 & (-57345);
                    } else {
                        border2 = border;
                        $dirty = $dirty2;
                        modifier4 = modifier3;
                        shape4 = shape3;
                        colors3 = colors2;
                        elevation3 = elevation2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 2) != 0) {
                        $dirty2 &= -113;
                    }
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        modifier4 = modifier2;
                        shape4 = shape2;
                        colors3 = cardColors;
                        border2 = borderStroke;
                        elevation3 = cardElevation;
                        $dirty = $dirty2 & (-57345);
                    } else {
                        modifier4 = modifier2;
                        shape4 = shape2;
                        colors3 = cardColors;
                        border2 = borderStroke;
                        elevation3 = cardElevation;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(740336179, $dirty, -1, "androidx.compose.material3.OutlinedCard (Card.kt:277)");
                }
                $composer2 = $composer3;
                Card(modifier4, shape4, colors3, elevation3, border2, content, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier4 = modifier2;
                shape4 = shape2;
                colors3 = cardColors;
                $composer2 = $composer3;
                border2 = borderStroke;
                elevation3 = cardElevation;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final Shape shape5 = shape4;
            final CardColors cardColors2 = colors3;
            final CardElevation cardElevation2 = elevation3;
            final BorderStroke borderStroke2 = border2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.1
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
                    CardKt.OutlinedCard(modifier5, shape5, cardColors2, cardElevation2, borderStroke2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
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
                if ((i & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    $dirty2 &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i & 4) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1350outlinedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -897;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 8) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1351outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty2 &= -7169;
                } else {
                    elevation2 = cardElevation;
                }
                if ((i & 16) != 0) {
                    modifier4 = modifier3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border2 = CardDefaults.INSTANCE.outlinedCardBorder(false, $composer3, 48, 1);
                    $dirty = $dirty2 & (-57345);
                } else {
                    border2 = border;
                    $dirty = $dirty2;
                    modifier4 = modifier3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    $dirty2 &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i & 4) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1350outlinedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -897;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 8) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1351outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty2 &= -7169;
                } else {
                    elevation2 = cardElevation;
                }
                if ((i & 16) != 0) {
                    modifier4 = modifier3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border2 = CardDefaults.INSTANCE.outlinedCardBorder(false, $composer3, 48, 1);
                    $dirty = $dirty2 & (-57345);
                } else {
                    border2 = border;
                    $dirty = $dirty2;
                    modifier4 = modifier3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(740336179, $dirty, -1, "androidx.compose.material3.OutlinedCard (Card.kt:277)");
            }
            $composer2 = $composer3;
            Card(modifier4, shape4, colors3, elevation3, border2, content, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752), 0);
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
                if ((i & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    $dirty2 &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i & 4) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1350outlinedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -897;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 8) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1351outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty2 &= -7169;
                } else {
                    elevation2 = cardElevation;
                }
                if ((i & 16) != 0) {
                    modifier4 = modifier3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border2 = CardDefaults.INSTANCE.outlinedCardBorder(false, $composer3, 48, 1);
                    $dirty = $dirty2 & (-57345);
                } else {
                    border2 = border;
                    $dirty = $dirty2;
                    modifier4 = modifier3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    shape3 = CardDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    $dirty2 &= -113;
                } else {
                    shape3 = shape2;
                }
                if ((i & 4) != 0) {
                    colors2 = CardDefaults.INSTANCE.m1350outlinedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    $dirty2 &= -897;
                } else {
                    colors2 = cardColors;
                }
                if ((i & 8) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1351outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty2 &= -7169;
                } else {
                    elevation2 = cardElevation;
                }
                if ((i & 16) != 0) {
                    modifier4 = modifier3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                    border2 = CardDefaults.INSTANCE.outlinedCardBorder(false, $composer3, 48, 1);
                    $dirty = $dirty2 & (-57345);
                } else {
                    border2 = border;
                    $dirty = $dirty2;
                    modifier4 = modifier3;
                    shape4 = shape3;
                    colors3 = colors2;
                    elevation3 = elevation2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(740336179, $dirty, -1, "androidx.compose.material3.OutlinedCard (Card.kt:277)");
            }
            $composer2 = $composer3;
            Card(modifier4, shape4, colors3, elevation3, border2, content, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final Shape shape6 = shape4;
        final CardColors cardColors3 = colors3;
        final CardElevation cardElevation3 = elevation3;
        final BorderStroke borderStroke3 = border2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.1
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
                CardKt.OutlinedCard(modifier6, shape6, cardColors3, cardElevation3, borderStroke3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:105:0x014b  */
    /* JADX WARN: Code duplicated, block: B:107:0x0152  */
    /* JADX WARN: Code duplicated, block: B:123:0x019c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:124:0x019e  */
    /* JADX WARN: Code duplicated, block: B:125:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:127:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:130:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:131:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:134:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:135:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:138:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:139:0x0208  */
    /* JADX WARN: Code duplicated, block: B:142:0x0212  */
    /* JADX WARN: Code duplicated, block: B:143:0x0227  */
    /* JADX WARN: Code duplicated, block: B:145:0x022d  */
    /* JADX WARN: Code duplicated, block: B:147:0x024c  */
    /* JADX WARN: Code duplicated, block: B:148:0x0257  */
    /* JADX WARN: Code duplicated, block: B:150:0x026e  */
    /* JADX WARN: Code duplicated, block: B:153:0x0287  */
    /* JADX WARN: Code duplicated, block: B:156:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:160:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:162:? A[RETURN, SYNTHETIC] */
    public static final void OutlinedCard(final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, CardColors colors, CardElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        boolean enabled2;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        BorderStroke borderStroke;
        int i2;
        Modifier.Companion modifier2;
        Shape shape3;
        int $dirty;
        CardColors colors2;
        CardElevation elevation2;
        boolean enabled3;
        BorderStroke border2;
        MutableInteractionSource interactionSource2;
        CardElevation elevation3;
        BorderStroke border3;
        CardColors colors3;
        int $dirty2;
        Modifier modifier3;
        boolean enabled4;
        Object it$iv$iv;
        Object value$iv$iv;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-727137250);
        ComposerKt.sourceInformation($composer3, "C(OutlinedCard)P(7,6,4,8,1,3!1,5)330@15524L13,331@15577L20,332@15643L23,333@15708L27,334@15787L39,336@15880L231:Card.kt#uh7d8r");
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
            enabled2 = enabled;
        } else if (($changed & 896) == 0) {
            enabled2 = enabled;
            $dirty3 |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
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
                cardColors = colors;
                int i6 = $composer3.changed(cardColors) ? 16384 : 8192;
                $dirty3 |= i6;
            } else {
                cardColors = colors;
            }
            $dirty3 |= i6;
        } else {
            cardColors = colors;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                cardElevation = elevation;
                int i7 = $composer3.changed(cardElevation) ? 131072 : 65536;
                $dirty3 |= i7;
            } else {
                cardElevation = elevation;
            }
            $dirty3 |= i7;
        } else {
            cardElevation = elevation;
        }
        if (($changed & 3670016) == 0) {
            if ((i & 64) == 0) {
                borderStroke = border;
                int i8 = $composer3.changed(borderStroke) ? 1048576 : 524288;
                $dirty3 |= i8;
            } else {
                borderStroke = border;
            }
            $dirty3 |= i8;
        } else {
            borderStroke = border;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty3 |= $composer3.changed(interactionSource) ? 8388608 : 4194304;
        }
        if ((i & 256) == 0) {
            if (($changed & 234881024) == 0) {
                i2 = $composer3.changedInstance(content) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
            }
            if ((191739611 & $dirty3) == 38347922 || !$composer3.getSkipping()) {
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
                        $dirty3 &= -7169;
                        shape3 = CardDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 16) != 0) {
                        $dirty = $dirty3 & (-57345);
                        colors2 = CardDefaults.INSTANCE.m1350outlinedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                    } else {
                        $dirty = $dirty3;
                        colors2 = cardColors;
                    }
                    if ((i & 32) != 0) {
                        elevation2 = CardDefaults.INSTANCE.m1351outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                        $dirty &= -458753;
                    } else {
                        elevation2 = elevation;
                    }
                    if ((i & 64) != 0) {
                        border2 = CardDefaults.INSTANCE.outlinedCardBorder(enabled3, $composer3, (($dirty >> 6) & 14) | 48, 0);
                        $dirty &= -3670017;
                    } else {
                        border2 = border;
                    }
                    if (i9 != 0) {
                        enabled3 = enabled2;
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        it$iv$iv = $composer3.rememberedValue();
                        Modifier modifier4 = modifier2;
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            enabled3 = enabled2;
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            enabled3 = enabled2;
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                        elevation3 = elevation2;
                        border3 = border2;
                        colors3 = colors2;
                        $dirty2 = $dirty;
                        modifier3 = modifier4;
                        enabled4 = enabled3;
                    } else {
                        enabled3 = enabled2;
                        enabled3 = enabled2;
                        interactionSource2 = interactionSource;
                        elevation3 = elevation2;
                        border3 = border2;
                        colors3 = colors2;
                        $dirty2 = $dirty;
                        modifier3 = modifier2;
                        enabled4 = enabled3;
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
                        $dirty3 &= -458753;
                    }
                    if ((i & 64) != 0) {
                        modifier3 = modifier;
                        interactionSource2 = interactionSource;
                        enabled4 = enabled2;
                        shape3 = shape2;
                        colors3 = cardColors;
                        border3 = borderStroke;
                        elevation3 = cardElevation;
                        $dirty2 = (-3670017) & $dirty3;
                    } else {
                        modifier3 = modifier;
                        interactionSource2 = interactionSource;
                        enabled4 = enabled2;
                        shape3 = shape2;
                        colors3 = cardColors;
                        border3 = borderStroke;
                        elevation3 = cardElevation;
                        $dirty2 = $dirty3;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-727137250, $dirty2, -1, "androidx.compose.material3.OutlinedCard (Card.kt:326)");
                }
                $composer2 = $composer3;
                Card(onClick, modifier3, enabled4, shape3, colors3, elevation3, border3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | ($dirty2 & 57344) | ($dirty2 & 458752) | ($dirty2 & 3670016) | ($dirty2 & 29360128) | ($dirty2 & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier3 = modifier;
                interactionSource2 = interactionSource;
                enabled4 = enabled2;
                shape3 = shape2;
                colors3 = cardColors;
                border3 = borderStroke;
                elevation3 = cardElevation;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier3;
            final boolean z = enabled4;
            final Shape shape4 = shape3;
            final CardColors cardColors2 = colors3;
            final CardElevation cardElevation2 = elevation3;
            final BorderStroke borderStroke2 = border3;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.3
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
                    CardKt.OutlinedCard(onClick, modifier5, z, shape4, cardColors2, cardElevation2, borderStroke2, mutableInteractionSource, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 100663296;
        $dirty3 |= i2;
        if ((191739611 & $dirty3) == 38347922) {
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
                    $dirty3 &= -7169;
                    shape3 = CardDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = CardDefaults.INSTANCE.m1350outlinedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1351outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if ((i & 64) != 0) {
                    border2 = CardDefaults.INSTANCE.outlinedCardBorder(enabled3, $composer3, (($dirty >> 6) & 14) | 48, 0);
                    $dirty &= -3670017;
                } else {
                    border2 = border;
                }
                if (i9 != 0) {
                    enabled3 = enabled2;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    Modifier modifier6 = modifier2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        enabled3 = enabled2;
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        enabled3 = enabled2;
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    elevation3 = elevation2;
                    border3 = border2;
                    colors3 = colors2;
                    $dirty2 = $dirty;
                    modifier3 = modifier6;
                    enabled4 = enabled3;
                } else {
                    enabled3 = enabled2;
                    enabled3 = enabled2;
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    colors3 = colors2;
                    $dirty2 = $dirty;
                    modifier3 = modifier2;
                    enabled4 = enabled3;
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
                    $dirty3 &= -7169;
                    shape3 = CardDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = CardDefaults.INSTANCE.m1350outlinedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1351outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if ((i & 64) != 0) {
                    border2 = CardDefaults.INSTANCE.outlinedCardBorder(enabled3, $composer3, (($dirty >> 6) & 14) | 48, 0);
                    $dirty &= -3670017;
                } else {
                    border2 = border;
                }
                if (i9 != 0) {
                    enabled3 = enabled2;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    Modifier modifier7 = modifier2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        enabled3 = enabled2;
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        enabled3 = enabled2;
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    elevation3 = elevation2;
                    border3 = border2;
                    colors3 = colors2;
                    $dirty2 = $dirty;
                    modifier3 = modifier7;
                    enabled4 = enabled3;
                } else {
                    enabled3 = enabled2;
                    enabled3 = enabled2;
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    colors3 = colors2;
                    $dirty2 = $dirty;
                    modifier3 = modifier2;
                    enabled4 = enabled3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-727137250, $dirty2, -1, "androidx.compose.material3.OutlinedCard (Card.kt:326)");
            }
            $composer2 = $composer3;
            Card(onClick, modifier3, enabled4, shape3, colors3, elevation3, border3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | ($dirty2 & 57344) | ($dirty2 & 458752) | ($dirty2 & 3670016) | ($dirty2 & 29360128) | ($dirty2 & 234881024), 0);
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
                    $dirty3 &= -7169;
                    shape3 = CardDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = CardDefaults.INSTANCE.m1350outlinedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1351outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if ((i & 64) != 0) {
                    border2 = CardDefaults.INSTANCE.outlinedCardBorder(enabled3, $composer3, (($dirty >> 6) & 14) | 48, 0);
                    $dirty &= -3670017;
                } else {
                    border2 = border;
                }
                if (i9 != 0) {
                    enabled3 = enabled2;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    Modifier modifier8 = modifier2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        enabled3 = enabled2;
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        enabled3 = enabled2;
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    elevation3 = elevation2;
                    border3 = border2;
                    colors3 = colors2;
                    $dirty2 = $dirty;
                    modifier3 = modifier8;
                    enabled4 = enabled3;
                } else {
                    enabled3 = enabled2;
                    enabled3 = enabled2;
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    colors3 = colors2;
                    $dirty2 = $dirty;
                    modifier3 = modifier2;
                    enabled4 = enabled3;
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
                    $dirty3 &= -7169;
                    shape3 = CardDefaults.INSTANCE.getOutlinedShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = CardDefaults.INSTANCE.m1350outlinedCardColorsro_MJ88(0L, 0L, 0L, 0L, $composer3, 24576, 15);
                } else {
                    $dirty = $dirty3;
                    colors2 = cardColors;
                }
                if ((i & 32) != 0) {
                    elevation2 = CardDefaults.INSTANCE.m1351outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty &= -458753;
                } else {
                    elevation2 = elevation;
                }
                if ((i & 64) != 0) {
                    border2 = CardDefaults.INSTANCE.outlinedCardBorder(enabled3, $composer3, (($dirty >> 6) & 14) | 48, 0);
                    $dirty &= -3670017;
                } else {
                    border2 = border;
                }
                if (i9 != 0) {
                    enabled3 = enabled2;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    Modifier modifier9 = modifier2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        enabled3 = enabled2;
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        enabled3 = enabled2;
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    elevation3 = elevation2;
                    border3 = border2;
                    colors3 = colors2;
                    $dirty2 = $dirty;
                    modifier3 = modifier9;
                    enabled4 = enabled3;
                } else {
                    enabled3 = enabled2;
                    enabled3 = enabled2;
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    colors3 = colors2;
                    $dirty2 = $dirty;
                    modifier3 = modifier2;
                    enabled4 = enabled3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-727137250, $dirty2, -1, "androidx.compose.material3.OutlinedCard (Card.kt:326)");
            }
            $composer2 = $composer3;
            Card(onClick, modifier3, enabled4, shape3, colors3, elevation3, border3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | ($dirty2 & 57344) | ($dirty2 & 458752) | ($dirty2 & 3670016) | ($dirty2 & 29360128) | ($dirty2 & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier10 = modifier3;
        final boolean z2 = enabled4;
        final Shape shape5 = shape3;
        final CardColors cardColors3 = colors3;
        final CardElevation cardElevation3 = elevation3;
        final BorderStroke borderStroke3 = border3;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.OutlinedCard.3
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
                CardKt.OutlinedCard(onClick, modifier10, z2, shape5, cardColors3, cardElevation3, borderStroke3, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }
}
