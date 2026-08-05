package androidx.compose.material;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
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
import androidx.compose.ui.draw.ClipKt;
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
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Chip.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u008e\u0001\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u001b2\u001c\u0010\u001c\u001a\u0018\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u000b0\u001d¢\u0006\u0002\b\u001b¢\u0006\u0002\b\u001fH\u0007¢\u0006\u0002\u0010 \u001aÄ\u0001\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u00112\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020#2\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u001b2\u0015\b\u0002\u0010$\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u001b2\u0015\b\u0002\u0010%\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r¢\u0006\u0002\b\u001b2\u001c\u0010\u001c\u001a\u0018\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u000b0\u001d¢\u0006\u0002\b\u001b¢\u0006\u0002\b\u001fH\u0007¢\u0006\u0002\u0010&\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0004\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0005\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0013\u0010\t\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'²\u0006\n\u0010(\u001a\u00020)X\u008a\u0084\u0002²\u0006\n\u0010*\u001a\u00020)X\u008a\u0084\u0002"}, d2 = {"HorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "LeadingIconEndSpacing", "LeadingIconStartSpacing", "SelectedIconContainerSize", "SelectedOverlayOpacity", "", "SurfaceOverlayOpacity", "TrailingIconSpacing", "Chip", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "shape", "Landroidx/compose/ui/graphics/Shape;", OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "colors", "Landroidx/compose/material/ChipColors;", "leadingIcon", "Landroidx/compose/runtime/Composable;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/material/ChipColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "FilterChip", "selected", "Landroidx/compose/material/SelectableChipColors;", "selectedIcon", "trailingIcon", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/material/SelectableChipColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "material_release", "contentColor", "Landroidx/compose/ui/graphics/Color;", "leadingIconContentColor"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChipKt {
    private static final float SelectedOverlayOpacity = 0.16f;
    private static final float SurfaceOverlayOpacity = 0.12f;
    private static final float HorizontalPadding = Dp.m5274constructorimpl(12);
    private static final float LeadingIconStartSpacing = Dp.m5274constructorimpl(4);
    private static final float LeadingIconEndSpacing = Dp.m5274constructorimpl(8);
    private static final float TrailingIconSpacing = Dp.m5274constructorimpl(8);
    private static final float SelectedIconContainerSize = Dp.m5274constructorimpl(24);

    /* JADX WARN: Code duplicated, block: B:105:0x0152  */
    /* JADX WARN: Code duplicated, block: B:107:0x015f  */
    /* JADX WARN: Code duplicated, block: B:117:0x0197 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:118:0x0199  */
    /* JADX WARN: Code duplicated, block: B:119:0x019e  */
    /* JADX WARN: Code duplicated, block: B:121:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:122:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:124:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:126:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:127:0x01db  */
    /* JADX WARN: Code duplicated, block: B:129:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:132:0x01f3  */
    /* JADX WARN: Code duplicated, block: B:134:0x020f  */
    /* JADX WARN: Code duplicated, block: B:137:0x0215  */
    /* JADX WARN: Code duplicated, block: B:138:0x0232  */
    /* JADX WARN: Code duplicated, block: B:140:0x0236  */
    /* JADX WARN: Code duplicated, block: B:141:0x0246  */
    /* JADX WARN: Code duplicated, block: B:144:0x025d  */
    /* JADX WARN: Code duplicated, block: B:147:0x030f  */
    /* JADX WARN: Code duplicated, block: B:151:0x0319  */
    /* JADX WARN: Code duplicated, block: B:153:? A[RETURN, SYNTHETIC] */
    public static final void Chip(final Function0<Unit> onClick, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, Shape shape, BorderStroke border, ChipColors colors, Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        MutableInteractionSource interactionSource2;
        Shape shape2;
        BorderStroke border2;
        int i2;
        Modifier.Companion modifier2;
        boolean enabled2;
        ChipColors colors2;
        boolean enabled3;
        Function2<? super Composer, ? super Integer, Unit> function3;
        int $dirty;
        MutableInteractionSource interactionSource3;
        Shape shape3;
        BorderStroke border3;
        Modifier modifier3;
        ChipColors colors3;
        Object it$iv$iv;
        Object value$iv$iv;
        ChipColors colors4;
        boolean enabled4;
        Modifier modifier4;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-368396408);
        ComposerKt.sourceInformation($composer3, "C(Chip)P(7,6,3,4,8!2,5)91@4193L39,92@4267L6,94@4384L12,98@4529L21,104@4726L24,99@4555L1787:Chip.kt#jmzs0o");
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
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            interactionSource2 = interactionSource;
        } else if (($changed & 7168) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer3.changed(interactionSource2) ? 2048 : 1024;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i6 = $composer3.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i6;
        } else {
            shape2 = shape;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            border2 = border;
        } else if ((458752 & $changed) == 0) {
            border2 = border;
            $dirty2 |= $composer3.changed(border2) ? 131072 : 65536;
        } else {
            border2 = border;
        }
        if (($changed & 3670016) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer3.changed(colors)) ? 1048576 : 524288;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
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
                        enabled2 = enabled;
                    }
                    if (i5 != 0) {
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
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                        shape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                    }
                    if (i7 != 0) {
                        border2 = null;
                    }
                    if ((i & 64) != 0) {
                        colors2 = ChipDefaults.INSTANCE.m1031chipColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                        $dirty2 &= -3670017;
                    } else {
                        colors2 = colors;
                    }
                    if (i8 != 0) {
                        enabled3 = enabled2;
                        function3 = null;
                        $dirty = $dirty2;
                        interactionSource3 = interactionSource2;
                        shape3 = shape2;
                        border3 = border2;
                        modifier3 = modifier2;
                        colors3 = colors2;
                    } else {
                        enabled3 = enabled2;
                        function3 = function2;
                        $dirty = $dirty2;
                        interactionSource3 = interactionSource2;
                        shape3 = shape2;
                        border3 = border2;
                        modifier3 = modifier2;
                        colors3 = colors2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 64) != 0) {
                        enabled3 = enabled;
                        function3 = function2;
                        $dirty = $dirty2 & (-3670017);
                        interactionSource3 = interactionSource2;
                        shape3 = shape2;
                        border3 = border2;
                        modifier3 = modifier;
                        colors3 = colors;
                    } else {
                        enabled3 = enabled;
                        function3 = function2;
                        $dirty = $dirty2;
                        interactionSource3 = interactionSource2;
                        shape3 = shape2;
                        border3 = border2;
                        modifier3 = modifier;
                        colors3 = colors;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-368396408, $dirty, -1, "androidx.compose.material.Chip (Chip.kt:87)");
                }
                final State<Color> stateContentColor = colors3.contentColor(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 15) & 112));
                Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.2
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
                long jM2981unboximpl = colors3.backgroundColor(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 15) & 112)).getValue().m2981unboximpl();
                long jChip$lambda$1 = Chip$lambda$1(stateContentColor);
                final Function2<? super Composer, ? super Integer, Unit> function4 = function3;
                final ChipColors chipColors = colors3;
                final boolean z = enabled3;
                final int i9 = $dirty;
                colors4 = colors3;
                enabled4 = enabled3;
                modifier4 = modifier3;
                $composer2 = $composer3;
                SurfaceKt.m1211SurfaceLPr_se0(onClick, modifierSemantics$default, enabled3, shape3, jM2981unboximpl, Color.m2969copywmQWz5c(jChip$lambda$1, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jChip$lambda$1) : 1.0f, (14 & 2) != 0 ? Color.m2977getRedimpl(jChip$lambda$1) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jChip$lambda$1) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jChip$lambda$1) : 0.0f), border3, 0.0f, interactionSource3, ComposableLambdaKt.composableLambda($composer3, 139076687, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.3
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
                        ComposerKt.sourceInformation($composer4, "C109@4894L1442:Chip.kt#jmzs0o");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(139076687, $changed2, -1, "androidx.compose.material.Chip.<anonymous> (Chip.kt:108)");
                            }
                            ProvidedValue[] providedValueArr = {ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m2973getAlphaimpl(ChipKt.Chip$lambda$1(stateContentColor))))};
                            final Function2<Composer, Integer, Unit> function5 = function4;
                            final ChipColors chipColors2 = chipColors;
                            final boolean z2 = z;
                            final int i10 = i9;
                            final Function3<RowScope, Composer, Integer, Unit> function6 = content;
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer4, 667535631, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.3.1
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
                                    ComposerKt.sourceInformation($composer5, "C111@5036L10,110@4980L1346:Chip.kt#jmzs0o");
                                    if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(667535631, $changed3, -1, "androidx.compose.material.Chip.<anonymous>.<anonymous> (Chip.kt:109)");
                                        }
                                        TextStyle body2 = MaterialTheme.INSTANCE.getTypography($composer5, 6).getBody2();
                                        final Function2<Composer, Integer, Unit> function7 = function5;
                                        final ChipColors chipColors3 = chipColors2;
                                        final boolean z3 = z2;
                                        final int i11 = i10;
                                        final Function3<RowScope, Composer, Integer, Unit> function8 = function6;
                                        TextKt.ProvideTextStyle(body2, ComposableLambdaKt.composableLambda($composer5, -1131213696, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.3.1.1
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
                                                float fM5274constructorimpl;
                                                Function0<ComposeUiNode> function0;
                                                ComposerKt.sourceInformation($composer6, "C113@5085L1227:Chip.kt#jmzs0o");
                                                if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-1131213696, $changed4, -1, "androidx.compose.material.Chip.<anonymous>.<anonymous>.<anonymous> (Chip.kt:112)");
                                                    }
                                                    Modifier modifierM519defaultMinSizeVpY3zN4$default = SizeKt.m519defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, ChipDefaults.INSTANCE.m1034getMinHeightD9Ej5fM(), 1, null);
                                                    if (function7 == null) {
                                                        fM5274constructorimpl = ChipKt.HorizontalPadding;
                                                    } else {
                                                        fM5274constructorimpl = Dp.m5274constructorimpl(0);
                                                    }
                                                    Modifier modifier$iv = PaddingKt.m491paddingqDBjuR0$default(modifierM519defaultMinSizeVpY3zN4$default, fM5274constructorimpl, 0.0f, ChipKt.HorizontalPadding, 0.0f, 10, null);
                                                    Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                                                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                                    Function2<Composer, Integer, Unit> function9 = function7;
                                                    ChipColors chipColors4 = chipColors3;
                                                    boolean z4 = z3;
                                                    int i12 = i11;
                                                    Function3<RowScope, Composer, Integer, Unit> function10 = function8;
                                                    $composer6.startReplaceableGroup(693286680);
                                                    ComposerKt.sourceInformation($composer6, "CC(Row)P(2,1,3)76@3779L58,77@3842L130:Row.kt#2w3rfo");
                                                    MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer6, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                                    int $changed$iv$iv = (432 << 3) & 112;
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
                                                        function0 = constructor;
                                                        $composer6.createNode(function0);
                                                    } else {
                                                        function0 = constructor;
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
                                                    int i13 = ($changed$iv$iv$iv >> 9) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -326682417, "C78@3887L9:Row.kt#2w3rfo");
                                                    int $changed5 = ((432 >> 6) & 112) | 6;
                                                    RowScope $this$invoke_u24lambda_u241 = RowScopeInstance.INSTANCE;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2084788937, "C137@6285L9:Chip.kt#jmzs0o");
                                                    $composer6.startReplaceableGroup(2084788937);
                                                    ComposerKt.sourceInformation($composer6, "128@5738L47,129@5848L32,130@5905L267,135@6197L45");
                                                    if (function9 != null) {
                                                        SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, ChipKt.LeadingIconStartSpacing), $composer6, 6);
                                                        State<Color> stateLeadingIconContentColor = chipColors4.leadingIconContentColor(z4, $composer6, ((i12 >> 6) & 14) | ((i12 >> 15) & 112));
                                                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(invoke$lambda$1$lambda$0(stateLeadingIconContentColor))), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m2973getAlphaimpl(invoke$lambda$1$lambda$0(stateLeadingIconContentColor))))}, function9, $composer6, ((i12 >> 18) & 112) | 8);
                                                        SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, ChipKt.LeadingIconEndSpacing), $composer6, 6);
                                                    }
                                                    $composer6.endReplaceableGroup();
                                                    function10.invoke($this$invoke_u24lambda_u241, $composer6, Integer.valueOf(($changed5 & 14) | ((i12 >> 21) & 112)));
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
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

                                            private static final long invoke$lambda$1$lambda$0(State<Color> state) {
                                                Object thisObj$iv = state.getValue();
                                                return ((Color) thisObj$iv).m2981unboximpl();
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
                }), $composer2, ($dirty & 14) | 805306368 | ($dirty & 896) | (($dirty >> 3) & 7168) | (($dirty << 3) & 3670016) | (($dirty << 15) & 234881024), 128);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier4 = modifier;
                enabled4 = enabled;
                colors4 = colors;
                function3 = function2;
                interactionSource3 = interactionSource2;
                shape3 = shape2;
                border3 = border2;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final boolean z2 = enabled4;
            final MutableInteractionSource mutableInteractionSource = interactionSource3;
            final Shape shape4 = shape3;
            final BorderStroke borderStroke = border3;
            final ChipColors chipColors2 = colors4;
            final Function2<? super Composer, ? super Integer, Unit> function5 = function3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.4
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
                    ChipKt.Chip(onClick, modifier5, z2, mutableInteractionSource, shape4, borderStroke, chipColors2, function5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
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
                    enabled2 = enabled;
                }
                if (i5 != 0) {
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
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                }
                if (i7 != 0) {
                    border2 = null;
                }
                if ((i & 64) != 0) {
                    colors2 = ChipDefaults.INSTANCE.m1031chipColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -3670017;
                } else {
                    colors2 = colors;
                }
                if (i8 != 0) {
                    enabled3 = enabled2;
                    function3 = null;
                    $dirty = $dirty2;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    border3 = border2;
                    modifier3 = modifier2;
                    colors3 = colors2;
                } else {
                    enabled3 = enabled2;
                    function3 = function2;
                    $dirty = $dirty2;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    border3 = border2;
                    modifier3 = modifier2;
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
                    enabled2 = enabled;
                }
                if (i5 != 0) {
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
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                }
                if (i7 != 0) {
                    border2 = null;
                }
                if ((i & 64) != 0) {
                    colors2 = ChipDefaults.INSTANCE.m1031chipColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -3670017;
                } else {
                    colors2 = colors;
                }
                if (i8 != 0) {
                    enabled3 = enabled2;
                    function3 = null;
                    $dirty = $dirty2;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    border3 = border2;
                    modifier3 = modifier2;
                    colors3 = colors2;
                } else {
                    enabled3 = enabled2;
                    function3 = function2;
                    $dirty = $dirty2;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    border3 = border2;
                    modifier3 = modifier2;
                    colors3 = colors2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-368396408, $dirty, -1, "androidx.compose.material.Chip (Chip.kt:87)");
            }
            final State<Color> stateContentColor2 = colors3.contentColor(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 15) & 112));
            Modifier modifierSemantics$default2 = SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.2
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
            long jM2981unboximpl2 = colors3.backgroundColor(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 15) & 112)).getValue().m2981unboximpl();
            long jChip$lambda$2 = Chip$lambda$1(stateContentColor2);
            final Function2<? super Composer, ? super Integer, Unit> function6 = function3;
            final ChipColors chipColors3 = colors3;
            final boolean z3 = enabled3;
            final int i10 = $dirty;
            colors4 = colors3;
            enabled4 = enabled3;
            modifier4 = modifier3;
            $composer2 = $composer3;
            SurfaceKt.m1211SurfaceLPr_se0(onClick, modifierSemantics$default2, enabled3, shape3, jM2981unboximpl2, Color.m2969copywmQWz5c(jChip$lambda$2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jChip$lambda$2) : 1.0f, (14 & 2) != 0 ? Color.m2977getRedimpl(jChip$lambda$2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jChip$lambda$2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jChip$lambda$2) : 0.0f), border3, 0.0f, interactionSource3, ComposableLambdaKt.composableLambda($composer3, 139076687, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.3
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
                    ComposerKt.sourceInformation($composer4, "C109@4894L1442:Chip.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(139076687, $changed2, -1, "androidx.compose.material.Chip.<anonymous> (Chip.kt:108)");
                        }
                        ProvidedValue[] providedValueArr = {ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m2973getAlphaimpl(ChipKt.Chip$lambda$1(stateContentColor2))))};
                        final Function2<? super Composer, ? super Integer, Unit> function7 = function6;
                        final ChipColors chipColors4 = chipColors3;
                        final boolean z4 = z3;
                        final int i11 = i10;
                        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function8 = content;
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer4, 667535631, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.3.1
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
                                ComposerKt.sourceInformation($composer5, "C111@5036L10,110@4980L1346:Chip.kt#jmzs0o");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(667535631, $changed3, -1, "androidx.compose.material.Chip.<anonymous>.<anonymous> (Chip.kt:109)");
                                    }
                                    TextStyle body2 = MaterialTheme.INSTANCE.getTypography($composer5, 6).getBody2();
                                    final Function2<? super Composer, ? super Integer, Unit> function9 = function7;
                                    final ChipColors chipColors5 = chipColors4;
                                    final boolean z5 = z4;
                                    final int i12 = i11;
                                    final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function10 = function8;
                                    TextKt.ProvideTextStyle(body2, ComposableLambdaKt.composableLambda($composer5, -1131213696, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.3.1.1
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
                                            float fM5274constructorimpl;
                                            Function0<ComposeUiNode> function0;
                                            ComposerKt.sourceInformation($composer6, "C113@5085L1227:Chip.kt#jmzs0o");
                                            if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1131213696, $changed4, -1, "androidx.compose.material.Chip.<anonymous>.<anonymous>.<anonymous> (Chip.kt:112)");
                                                }
                                                Modifier modifierM519defaultMinSizeVpY3zN4$default = SizeKt.m519defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, ChipDefaults.INSTANCE.m1034getMinHeightD9Ej5fM(), 1, null);
                                                if (function9 == null) {
                                                    fM5274constructorimpl = ChipKt.HorizontalPadding;
                                                } else {
                                                    fM5274constructorimpl = Dp.m5274constructorimpl(0);
                                                }
                                                Modifier modifier$iv = PaddingKt.m491paddingqDBjuR0$default(modifierM519defaultMinSizeVpY3zN4$default, fM5274constructorimpl, 0.0f, ChipKt.HorizontalPadding, 0.0f, 10, null);
                                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                                Function2<Composer, Integer, Unit> function11 = function9;
                                                ChipColors chipColors6 = chipColors5;
                                                boolean z6 = z5;
                                                int i13 = i12;
                                                Function3<RowScope, Composer, Integer, Unit> function12 = function10;
                                                $composer6.startReplaceableGroup(693286680);
                                                ComposerKt.sourceInformation($composer6, "CC(Row)P(2,1,3)76@3779L58,77@3842L130:Row.kt#2w3rfo");
                                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer6, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                                int $changed$iv$iv = (432 << 3) & 112;
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
                                                    function0 = constructor;
                                                    $composer6.createNode(function0);
                                                } else {
                                                    function0 = constructor;
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
                                                int i14 = ($changed$iv$iv$iv >> 9) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -326682417, "C78@3887L9:Row.kt#2w3rfo");
                                                int $changed5 = ((432 >> 6) & 112) | 6;
                                                RowScope $this$invoke_u24lambda_u241 = RowScopeInstance.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart($composer6, 2084788937, "C137@6285L9:Chip.kt#jmzs0o");
                                                $composer6.startReplaceableGroup(2084788937);
                                                ComposerKt.sourceInformation($composer6, "128@5738L47,129@5848L32,130@5905L267,135@6197L45");
                                                if (function11 != null) {
                                                    SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, ChipKt.LeadingIconStartSpacing), $composer6, 6);
                                                    State<Color> stateLeadingIconContentColor = chipColors6.leadingIconContentColor(z6, $composer6, ((i13 >> 6) & 14) | ((i13 >> 15) & 112));
                                                    CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(invoke$lambda$1$lambda$0(stateLeadingIconContentColor))), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m2973getAlphaimpl(invoke$lambda$1$lambda$0(stateLeadingIconContentColor))))}, function11, $composer6, ((i13 >> 18) & 112) | 8);
                                                    SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, ChipKt.LeadingIconEndSpacing), $composer6, 6);
                                                }
                                                $composer6.endReplaceableGroup();
                                                function12.invoke($this$invoke_u24lambda_u241, $composer6, Integer.valueOf(($changed5 & 14) | ((i13 >> 21) & 112)));
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
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

                                        private static final long invoke$lambda$1$lambda$0(State<Color> state) {
                                            Object thisObj$iv = state.getValue();
                                            return ((Color) thisObj$iv).m2981unboximpl();
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
            }), $composer2, ($dirty & 14) | 805306368 | ($dirty & 896) | (($dirty >> 3) & 7168) | (($dirty << 3) & 3670016) | (($dirty << 15) & 234881024), 128);
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
                if (i5 != 0) {
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
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                }
                if (i7 != 0) {
                    border2 = null;
                }
                if ((i & 64) != 0) {
                    colors2 = ChipDefaults.INSTANCE.m1031chipColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -3670017;
                } else {
                    colors2 = colors;
                }
                if (i8 != 0) {
                    enabled3 = enabled2;
                    function3 = null;
                    $dirty = $dirty2;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    border3 = border2;
                    modifier3 = modifier2;
                    colors3 = colors2;
                } else {
                    enabled3 = enabled2;
                    function3 = function2;
                    $dirty = $dirty2;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    border3 = border2;
                    modifier3 = modifier2;
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
                    enabled2 = enabled;
                }
                if (i5 != 0) {
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
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                }
                if (i7 != 0) {
                    border2 = null;
                }
                if ((i & 64) != 0) {
                    colors2 = ChipDefaults.INSTANCE.m1031chipColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -3670017;
                } else {
                    colors2 = colors;
                }
                if (i8 != 0) {
                    enabled3 = enabled2;
                    function3 = null;
                    $dirty = $dirty2;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    border3 = border2;
                    modifier3 = modifier2;
                    colors3 = colors2;
                } else {
                    enabled3 = enabled2;
                    function3 = function2;
                    $dirty = $dirty2;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    border3 = border2;
                    modifier3 = modifier2;
                    colors3 = colors2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-368396408, $dirty, -1, "androidx.compose.material.Chip (Chip.kt:87)");
            }
            final State<Color> stateContentColor3 = colors3.contentColor(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 15) & 112));
            Modifier modifierSemantics$default3 = SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.2
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
            long jM2981unboximpl3 = colors3.backgroundColor(enabled3, $composer3, (($dirty >> 6) & 14) | (($dirty >> 15) & 112)).getValue().m2981unboximpl();
            long jChip$lambda$3 = Chip$lambda$1(stateContentColor3);
            final Function2<? super Composer, ? super Integer, Unit> function7 = function3;
            final ChipColors chipColors4 = colors3;
            final boolean z4 = enabled3;
            final int i11 = $dirty;
            colors4 = colors3;
            enabled4 = enabled3;
            modifier4 = modifier3;
            $composer2 = $composer3;
            SurfaceKt.m1211SurfaceLPr_se0(onClick, modifierSemantics$default3, enabled3, shape3, jM2981unboximpl3, Color.m2969copywmQWz5c(jChip$lambda$3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jChip$lambda$3) : 1.0f, (14 & 2) != 0 ? Color.m2977getRedimpl(jChip$lambda$3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jChip$lambda$3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jChip$lambda$3) : 0.0f), border3, 0.0f, interactionSource3, ComposableLambdaKt.composableLambda($composer3, 139076687, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.3
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
                    ComposerKt.sourceInformation($composer4, "C109@4894L1442:Chip.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(139076687, $changed2, -1, "androidx.compose.material.Chip.<anonymous> (Chip.kt:108)");
                        }
                        ProvidedValue[] providedValueArr = {ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m2973getAlphaimpl(ChipKt.Chip$lambda$1(stateContentColor3))))};
                        final Function2<? super Composer, ? super Integer, Unit> function8 = function7;
                        final ChipColors chipColors5 = chipColors4;
                        final boolean z5 = z4;
                        final int i12 = i11;
                        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function9 = content;
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer4, 667535631, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.3.1
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
                                ComposerKt.sourceInformation($composer5, "C111@5036L10,110@4980L1346:Chip.kt#jmzs0o");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(667535631, $changed3, -1, "androidx.compose.material.Chip.<anonymous>.<anonymous> (Chip.kt:109)");
                                    }
                                    TextStyle body2 = MaterialTheme.INSTANCE.getTypography($composer5, 6).getBody2();
                                    final Function2<? super Composer, ? super Integer, Unit> function10 = function8;
                                    final ChipColors chipColors6 = chipColors5;
                                    final boolean z6 = z5;
                                    final int i13 = i12;
                                    final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function11 = function9;
                                    TextKt.ProvideTextStyle(body2, ComposableLambdaKt.composableLambda($composer5, -1131213696, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.3.1.1
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
                                            float fM5274constructorimpl;
                                            Function0<ComposeUiNode> function0;
                                            ComposerKt.sourceInformation($composer6, "C113@5085L1227:Chip.kt#jmzs0o");
                                            if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1131213696, $changed4, -1, "androidx.compose.material.Chip.<anonymous>.<anonymous>.<anonymous> (Chip.kt:112)");
                                                }
                                                Modifier modifierM519defaultMinSizeVpY3zN4$default = SizeKt.m519defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, ChipDefaults.INSTANCE.m1034getMinHeightD9Ej5fM(), 1, null);
                                                if (function10 == null) {
                                                    fM5274constructorimpl = ChipKt.HorizontalPadding;
                                                } else {
                                                    fM5274constructorimpl = Dp.m5274constructorimpl(0);
                                                }
                                                Modifier modifier$iv = PaddingKt.m491paddingqDBjuR0$default(modifierM519defaultMinSizeVpY3zN4$default, fM5274constructorimpl, 0.0f, ChipKt.HorizontalPadding, 0.0f, 10, null);
                                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                                Function2<Composer, Integer, Unit> function12 = function10;
                                                ChipColors chipColors7 = chipColors6;
                                                boolean z7 = z6;
                                                int i14 = i13;
                                                Function3<RowScope, Composer, Integer, Unit> function13 = function11;
                                                $composer6.startReplaceableGroup(693286680);
                                                ComposerKt.sourceInformation($composer6, "CC(Row)P(2,1,3)76@3779L58,77@3842L130:Row.kt#2w3rfo");
                                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer6, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                                int $changed$iv$iv = (432 << 3) & 112;
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
                                                    function0 = constructor;
                                                    $composer6.createNode(function0);
                                                } else {
                                                    function0 = constructor;
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
                                                int i15 = ($changed$iv$iv$iv >> 9) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -326682417, "C78@3887L9:Row.kt#2w3rfo");
                                                int $changed5 = ((432 >> 6) & 112) | 6;
                                                RowScope $this$invoke_u24lambda_u241 = RowScopeInstance.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart($composer6, 2084788937, "C137@6285L9:Chip.kt#jmzs0o");
                                                $composer6.startReplaceableGroup(2084788937);
                                                ComposerKt.sourceInformation($composer6, "128@5738L47,129@5848L32,130@5905L267,135@6197L45");
                                                if (function12 != null) {
                                                    SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, ChipKt.LeadingIconStartSpacing), $composer6, 6);
                                                    State<Color> stateLeadingIconContentColor = chipColors7.leadingIconContentColor(z7, $composer6, ((i14 >> 6) & 14) | ((i14 >> 15) & 112));
                                                    CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(invoke$lambda$1$lambda$0(stateLeadingIconContentColor))), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m2973getAlphaimpl(invoke$lambda$1$lambda$0(stateLeadingIconContentColor))))}, function12, $composer6, ((i14 >> 18) & 112) | 8);
                                                    SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, ChipKt.LeadingIconEndSpacing), $composer6, 6);
                                                }
                                                $composer6.endReplaceableGroup();
                                                function13.invoke($this$invoke_u24lambda_u241, $composer6, Integer.valueOf(($changed5 & 14) | ((i14 >> 21) & 112)));
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
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

                                        private static final long invoke$lambda$1$lambda$0(State<Color> state) {
                                            Object thisObj$iv = state.getValue();
                                            return ((Color) thisObj$iv).m2981unboximpl();
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
            }), $composer2, ($dirty & 14) | 805306368 | ($dirty & 896) | (($dirty >> 3) & 7168) | (($dirty << 3) & 3670016) | (($dirty << 15) & 234881024), 128);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final boolean z5 = enabled4;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource3;
        final Shape shape5 = shape3;
        final BorderStroke borderStroke2 = border3;
        final ChipColors chipColors5 = colors4;
        final Function2<? super Composer, ? super Integer, Unit> function8 = function3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.Chip.4
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
                ChipKt.Chip(onClick, modifier6, z5, mutableInteractionSource2, shape5, borderStroke2, chipColors5, function8, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long Chip$lambda$1(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m2981unboximpl();
    }

    public static final void FilterChip(final boolean selected, final Function0<Unit> onClick, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, Shape shape, BorderStroke border, SelectableChipColors colors, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        MutableInteractionSource interactionSource2;
        CornerBasedShape shape2;
        SelectableChipColors colors2;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Function2<? super Composer, ? super Integer, Unit> function6;
        MutableInteractionSource interactionSource3;
        Shape shape3;
        Function2<? super Composer, ? super Integer, Unit> function7;
        BorderStroke border2;
        SelectableChipColors colors3;
        boolean enabled2;
        int $dirty;
        Object value$iv$iv;
        final SelectableChipColors colors4;
        final boolean enabled3;
        Modifier modifier3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-1259208246);
        ComposerKt.sourceInformation($composer3, "C(FilterChip)P(8,7,6,3,4,10!2,5,9,11)188@8733L39,189@8807L6,191@8934L18,198@9265L31,205@9503L34,199@9301L4037:Chip.kt#jmzs0o");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changed(selected) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changedInstance(onClick) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 896) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 2048 : 1024;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            $dirty2 |= ((i & 32) == 0 && $composer3.changed(shape)) ? 131072 : 65536;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty2 |= $composer3.changed(border) ? 1048576 : 524288;
        }
        if (($changed & 29360128) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer3.changed(colors)) ? 8388608 : 4194304;
        }
        int i6 = i & 256;
        if (i6 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 536870912 : 268435456;
        }
        int i8 = i & 1024;
        if (i8 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 14) == 0) {
            $dirty1 |= $composer3.changedInstance(function4) ? 4 : 2;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 112) == 0) {
            $dirty1 |= $composer3.changedInstance(content) ? 32 : 16;
        }
        final int $dirty3 = $dirty1;
        if ((1533916891 & $dirty2) == 306783378 && ($dirty3 & 91) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            enabled3 = enabled;
            interactionSource3 = interactionSource;
            shape3 = shape;
            border2 = border;
            colors4 = colors;
            function6 = function2;
            function7 = function3;
            function5 = function4;
            modifier3 = modifier2;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i3 != 0 ? true : enabled;
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
                    interactionSource2 = interactionSource;
                }
                if ((i & 32) != 0) {
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                    $dirty2 &= -458753;
                } else {
                    shape2 = shape;
                }
                BorderStroke border3 = i5 != 0 ? null : border;
                if ((i & 128) != 0) {
                    colors2 = ChipDefaults.INSTANCE.m1032filterChipColorsJ08w3E(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 805306368, FrameMetricsAggregator.EVERY_DURATION);
                    $dirty2 &= -29360129;
                } else {
                    colors2 = colors;
                }
                Function2<? super Composer, ? super Integer, Unit> function8 = i6 != 0 ? null : function2;
                Function2<? super Composer, ? super Integer, Unit> function9 = i7 != 0 ? null : function3;
                if (i8 != 0) {
                    function6 = function8;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    function7 = function9;
                    function5 = null;
                    border2 = border3;
                    colors3 = colors2;
                    modifier2 = modifier4;
                    enabled2 = enabled4;
                    $dirty = $dirty2;
                } else {
                    function5 = function4;
                    function6 = function8;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    function7 = function9;
                    border2 = border3;
                    colors3 = colors2;
                    modifier2 = modifier4;
                    enabled2 = enabled4;
                    $dirty = $dirty2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 128) != 0) {
                    enabled2 = enabled;
                    interactionSource3 = interactionSource;
                    shape3 = shape;
                    border2 = border;
                    colors3 = colors;
                    function6 = function2;
                    function7 = function3;
                    function5 = function4;
                    $dirty = (-29360129) & $dirty2;
                } else {
                    enabled2 = enabled;
                    interactionSource3 = interactionSource;
                    shape3 = shape;
                    border2 = border;
                    colors3 = colors;
                    function6 = function2;
                    function7 = function3;
                    function5 = function4;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1259208246, $dirty, $dirty3, "androidx.compose.material.FilterChip (Chip.kt:183)");
            }
            final State<Color> stateContentColor = colors3.contentColor(enabled2, selected, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 15) & 896));
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier2, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ChipKt.FilterChip.2
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
            }, 1, null);
            long jM2981unboximpl = colors3.backgroundColor(enabled2, selected, $composer3, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 15) & 896)).getValue().m2981unboximpl();
            long jM2981unboximpl2 = stateContentColor.getValue().m2981unboximpl();
            final Function2<? super Composer, ? super Integer, Unit> function10 = function6;
            final int $dirty4 = $dirty;
            final Function2<? super Composer, ? super Integer, Unit> function11 = function7;
            colors4 = colors3;
            final Function2<? super Composer, ? super Integer, Unit> function12 = function5;
            enabled3 = enabled2;
            modifier3 = modifier2;
            $composer2 = $composer3;
            SurfaceKt.m1212SurfaceNy5ogXk(selected, onClick, modifierSemantics$default, enabled3, shape3, jM2981unboximpl, Color.m2969copywmQWz5c(jM2981unboximpl2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM2981unboximpl2) : 1.0f, (14 & 2) != 0 ? Color.m2977getRedimpl(jM2981unboximpl2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM2981unboximpl2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM2981unboximpl2) : 0.0f), border2, 0.0f, interactionSource3, ComposableLambdaKt.composableLambda($composer3, 722126431, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.FilterChip.3
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
                    ComposerKt.sourceInformation($composer4, "C210@9687L3645:Chip.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(722126431, $changed2, -1, "androidx.compose.material.FilterChip.<anonymous> (Chip.kt:209)");
                        }
                        ProvidedValue[] providedValueArr = {ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m2973getAlphaimpl(stateContentColor.getValue().m2981unboximpl())))};
                        final Function2<Composer, Integer, Unit> function13 = function10;
                        final boolean z = selected;
                        final Function2<Composer, Integer, Unit> function14 = function11;
                        final Function2<Composer, Integer, Unit> function15 = function12;
                        final Function3<RowScope, Composer, Integer, Unit> function16 = content;
                        final int i9 = $dirty3;
                        final SelectableChipColors selectableChipColors = colors4;
                        final boolean z2 = enabled3;
                        final int i10 = $dirty4;
                        final State<Color> state = stateContentColor;
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer4, 1582291359, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.FilterChip.3.1
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
                                ComposerKt.sourceInformation($composer5, "C212@9835L10,211@9779L3543:Chip.kt#jmzs0o");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1582291359, $changed3, -1, "androidx.compose.material.FilterChip.<anonymous>.<anonymous> (Chip.kt:210)");
                                    }
                                    TextStyle body2 = MaterialTheme.INSTANCE.getTypography($composer5, 6).getBody2();
                                    final Function2<Composer, Integer, Unit> function17 = function13;
                                    final boolean z3 = z;
                                    final Function2<Composer, Integer, Unit> function18 = function14;
                                    final Function2<Composer, Integer, Unit> function19 = function15;
                                    final Function3<RowScope, Composer, Integer, Unit> function20 = function16;
                                    final int i11 = i9;
                                    final SelectableChipColors selectableChipColors2 = selectableChipColors;
                                    final boolean z4 = z2;
                                    final int i12 = i10;
                                    final State<Color> state2 = state;
                                    TextKt.ProvideTextStyle(body2, ComposableLambdaKt.composableLambda($composer5, -1543702066, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.FilterChip.3.1.1
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
                                            float fM5274constructorimpl;
                                            float fM5274constructorimpl2;
                                            Function0<ComposeUiNode> function0;
                                            Function0<ComposeUiNode> function1;
                                            Composer $composer7;
                                            Function0<ComposeUiNode> function21;
                                            ComposerKt.sourceInformation($composer6, "C214@9884L3424:Chip.kt#jmzs0o");
                                            if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1543702066, $changed4, -1, "androidx.compose.material.FilterChip.<anonymous>.<anonymous>.<anonymous> (Chip.kt:213)");
                                                }
                                                Modifier modifierM519defaultMinSizeVpY3zN4$default = SizeKt.m519defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, ChipDefaults.INSTANCE.m1034getMinHeightD9Ej5fM(), 1, null);
                                                if (function17 == null && (!z3 || function18 == null)) {
                                                    fM5274constructorimpl = ChipKt.HorizontalPadding;
                                                } else {
                                                    fM5274constructorimpl = Dp.m5274constructorimpl(0);
                                                }
                                                if (function19 == null) {
                                                    fM5274constructorimpl2 = ChipKt.HorizontalPadding;
                                                } else {
                                                    fM5274constructorimpl2 = Dp.m5274constructorimpl(0);
                                                }
                                                Modifier modifier$iv = PaddingKt.m491paddingqDBjuR0$default(modifierM519defaultMinSizeVpY3zN4$default, fM5274constructorimpl, 0.0f, fM5274constructorimpl2, 0.0f, 10, null);
                                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                                Function2<Composer, Integer, Unit> function22 = function17;
                                                boolean z5 = z3;
                                                Function2<Composer, Integer, Unit> function23 = function18;
                                                Function3<RowScope, Composer, Integer, Unit> function24 = function20;
                                                int i13 = i11;
                                                Function2<Composer, Integer, Unit> function25 = function19;
                                                SelectableChipColors selectableChipColors3 = selectableChipColors2;
                                                boolean z6 = z4;
                                                int i14 = i12;
                                                State<Color> state3 = state2;
                                                $composer6.startReplaceableGroup(693286680);
                                                ComposerKt.sourceInformation($composer6, "CC(Row)P(2,1,3)76@3779L58,77@3842L130:Row.kt#2w3rfo");
                                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer6, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                                int $changed$iv$iv = (432 << 3) & 112;
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
                                                    function0 = constructor;
                                                    $composer6.createNode(function0);
                                                } else {
                                                    function0 = constructor;
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
                                                int i15 = ($changed$iv$iv$iv >> 9) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -326682417, "C78@3887L9:Row.kt#2w3rfo");
                                                int i16 = ((432 >> 6) & 112) | 6;
                                                RowScope $this$invoke_u24lambda_u242 = RowScopeInstance.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -1943412047, "C276@13036L9:Chip.kt#jmzs0o");
                                                $composer6.startReplaceableGroup(-1943412047);
                                                ComposerKt.sourceInformation($composer6, "237@10896L47,238@10968L1955,274@12948L45");
                                                if (function22 != null || (z5 && function23 != null)) {
                                                    SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, ChipKt.LeadingIconStartSpacing), $composer6, 6);
                                                    $composer6.startReplaceableGroup(733328855);
                                                    ComposerKt.sourceInformation($composer6, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                                                    Modifier modifier$iv2 = Modifier.INSTANCE;
                                                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                                    MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer6, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                                    int $changed$iv$iv2 = (0 << 3) & 112;
                                                    $composer6.startReplaceableGroup(-1323940314);
                                                    ComposerKt.sourceInformation($composer6, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                                    int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                    CompositionLocalMap localMap$iv$iv2 = $composer6.getCurrentCompositionLocalMap();
                                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                                                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                                    if (!($composer6.getApplier() instanceof Applier)) {
                                                        ComposablesKt.invalidApplier();
                                                    }
                                                    $composer6.startReusableNode();
                                                    if ($composer6.getInserting()) {
                                                        function1 = constructor2;
                                                        $composer6.createNode(function1);
                                                    } else {
                                                        function1 = constructor2;
                                                        $composer6.useNode();
                                                    }
                                                    Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2603constructorimpl($composer6);
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                                                    }
                                                    function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                                    $composer6.startReplaceableGroup(2058660585);
                                                    int i17 = ($changed$iv$iv$iv2 >> 9) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                    int i18 = ((0 >> 6) & 112) | 6;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 649985685, "C:Chip.kt#jmzs0o");
                                                    $composer6.startReplaceableGroup(649985685);
                                                    ComposerKt.sourceInformation($composer6, "240@11091L141,244@11265L297");
                                                    if (function22 != null) {
                                                        State<Color> stateLeadingIconColor = selectableChipColors3.leadingIconColor(z6, z5, $composer6, ((i14 >> 9) & 14) | ((i14 << 3) & 112) | ((i14 >> 15) & 896));
                                                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(stateLeadingIconColor.getValue()), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m2973getAlphaimpl(stateLeadingIconColor.getValue().m2981unboximpl())))}, function22, $composer6, ((i14 >> 21) & 112) | 8);
                                                    }
                                                    $composer6.endReplaceableGroup();
                                                    $composer6.startReplaceableGroup(-1943411233);
                                                    ComposerKt.sourceInformation($composer6, "263@12416L451");
                                                    if (z5 && function23 != null) {
                                                        Modifier.Companion overlayModifier = Modifier.INSTANCE;
                                                        long iconColor = state3.getValue().m2981unboximpl();
                                                        $composer6.startReplaceableGroup(649986516);
                                                        ComposerKt.sourceInformation($composer6, "261@12309L34");
                                                        if (function22 != null) {
                                                            overlayModifier = ClipKt.clip(BackgroundKt.m159backgroundbw27NRU(SizeKt.m526requiredSize3ABfNKs(Modifier.INSTANCE, ChipKt.SelectedIconContainerSize), state3.getValue().m2981unboximpl(), RoundedCornerShapeKt.getCircleShape()), RoundedCornerShapeKt.getCircleShape());
                                                            iconColor = selectableChipColors3.backgroundColor(z6, z5, $composer6, ((i14 >> 9) & 14) | ((i14 << 3) & 112) | ((i14 >> 15) & 896)).getValue().m2981unboximpl();
                                                        }
                                                        $composer6.endReplaceableGroup();
                                                        Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
                                                        $composer6.startReplaceableGroup(733328855);
                                                        ComposerKt.sourceInformation($composer6, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                                                        MeasurePolicy measurePolicy$iv3 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer6, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                                        int $changed$iv$iv3 = (48 << 3) & 112;
                                                        $composer6.startReplaceableGroup(-1323940314);
                                                        ComposerKt.sourceInformation($composer6, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                                        int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                        CompositionLocalMap localMap$iv$iv3 = $composer6.getCurrentCompositionLocalMap();
                                                        Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(overlayModifier);
                                                        int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
                                                        if (!($composer6.getApplier() instanceof Applier)) {
                                                            ComposablesKt.invalidApplier();
                                                        }
                                                        $composer6.startReusableNode();
                                                        if ($composer6.getInserting()) {
                                                            function21 = constructor3;
                                                            $composer6.createNode(function21);
                                                        } else {
                                                            function21 = constructor3;
                                                            $composer6.useNode();
                                                        }
                                                        Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2603constructorimpl($composer6);
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                        if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                                                            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                                                            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                                                        }
                                                        function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                                                        $composer6.startReplaceableGroup(2058660585);
                                                        int i19 = ($changed$iv$iv$iv3 >> 9) & 14;
                                                        ComposerKt.sourceInformationMarkerStart($composer6, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                                                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                                        int i20 = ((48 >> 6) & 112) | 6;
                                                        ComposerKt.sourceInformationMarkerStart($composer6, 333805201, "C267@12629L204:Chip.kt#jmzs0o");
                                                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(iconColor))}, function23, $composer6, ((i14 >> 24) & 112) | 8);
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        $composer6.endReplaceableGroup();
                                                        $composer6.endNode();
                                                        $composer6.endReplaceableGroup();
                                                        $composer6.endReplaceableGroup();
                                                    }
                                                    $composer6.endReplaceableGroup();
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    r6.endReplaceableGroup();
                                                    r6.endNode();
                                                    r6.endReplaceableGroup();
                                                    r6.endReplaceableGroup();
                                                    $composer7 = $composer6;
                                                    SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, ChipKt.LeadingIconEndSpacing), $composer7, 6);
                                                } else {
                                                    $composer7 = $composer6;
                                                }
                                                $composer7.endReplaceableGroup();
                                                function24.invoke($this$invoke_u24lambda_u242, $composer7, Integer.valueOf((i16 & 14) | (i13 & 112)));
                                                $composer7.startReplaceableGroup(-1181292829);
                                                ComposerKt.sourceInformation($composer7, "278@13118L43,279@13186L14,280@13225L43");
                                                if (function25 != null) {
                                                    SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, ChipKt.TrailingIconSpacing), $composer7, 6);
                                                    function25.invoke($composer7, Integer.valueOf(i13 & 14));
                                                    SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, ChipKt.TrailingIconSpacing), $composer7, 6);
                                                }
                                                $composer7.endReplaceableGroup();
                                                ComposerKt.sourceInformationMarkerEnd($composer7);
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
            }), $composer3, ($dirty4 & 14) | ($dirty4 & 112) | ($dirty4 & 7168) | (($dirty4 >> 3) & 57344) | (($dirty4 << 3) & 29360128) | (($dirty4 << 15) & 1879048192), 6, 256);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final boolean z = enabled3;
        final MutableInteractionSource mutableInteractionSource = interactionSource3;
        final Shape shape4 = shape3;
        final BorderStroke borderStroke = border2;
        final SelectableChipColors selectableChipColors = colors4;
        final Function2<? super Composer, ? super Integer, Unit> function13 = function6;
        final Function2<? super Composer, ? super Integer, Unit> function14 = function7;
        final Function2<? super Composer, ? super Integer, Unit> function15 = function5;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ChipKt.FilterChip.4
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
                ChipKt.FilterChip(selected, onClick, modifier5, z, mutableInteractionSource, shape4, borderStroke, selectableChipColors, function13, function14, function15, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }
}
