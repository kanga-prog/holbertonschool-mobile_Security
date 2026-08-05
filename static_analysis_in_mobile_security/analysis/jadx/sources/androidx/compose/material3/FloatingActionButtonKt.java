package androidx.compose.material3;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimationSpecKt;
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
import androidx.compose.material3.tokens.ExtendedFabPrimaryTokens;
import androidx.compose.material3.tokens.FabPrimaryLargeTokens;
import androidx.compose.material3.tokens.FabPrimarySmallTokens;
import androidx.compose.material3.tokens.FabPrimaryTokens;
import androidx.compose.material3.tokens.MotionTokens;
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
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FloatingActionButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\u001a}\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u001c\u0010\u0019\u001a\u0018\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000b0\u001a¢\u0006\u0002\b\u001c¢\u0006\u0002\b\u001dH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u008f\u0001\u0010\n\u001a\u00020\u000b2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u001c2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u001c2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001ar\u0010&\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u001cH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001ar\u0010)\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u001cH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b*\u0010(\u001ar\u0010+\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u001cH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010(\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0007\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\b\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\t\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006-"}, d2 = {"ExtendedFabCollapseAnimation", "Landroidx/compose/animation/ExitTransition;", "ExtendedFabEndIconPadding", "Landroidx/compose/ui/unit/Dp;", "F", "ExtendedFabExpandAnimation", "Landroidx/compose/animation/EnterTransition;", "ExtendedFabMinimumWidth", "ExtendedFabStartIconPadding", "ExtendedFabTextPadding", "ExtendedFloatingActionButton", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "elevation", "Landroidx/compose/material3/FloatingActionButtonElevation;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "ExtendedFloatingActionButton-X-z6DiA", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material3/FloatingActionButtonElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "text", "icon", "expanded", "", "ExtendedFloatingActionButton-ElI5-7k", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material3/FloatingActionButtonElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "FloatingActionButton", "FloatingActionButton-X-z6DiA", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material3/FloatingActionButtonElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "LargeFloatingActionButton", "LargeFloatingActionButton-X-z6DiA", "SmallFloatingActionButton", "SmallFloatingActionButton-X-z6DiA", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FloatingActionButtonKt {
    private static final float ExtendedFabStartIconPadding = Dp.m5274constructorimpl(16);
    private static final float ExtendedFabEndIconPadding = Dp.m5274constructorimpl(12);
    private static final float ExtendedFabTextPadding = Dp.m5274constructorimpl(20);
    private static final float ExtendedFabMinimumWidth = Dp.m5274constructorimpl(80);
    private static final ExitTransition ExtendedFabCollapseAnimation = EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(100, 0, MotionTokens.INSTANCE.getEasingLinearCubicBezier(), 2, null), 0.0f, 2, null).plus(EnterExitTransitionKt.shrinkHorizontally$default(AnimationSpecKt.tween$default(500, 0, MotionTokens.INSTANCE.getEasingEmphasizedCubicBezier(), 2, null), Alignment.INSTANCE.getStart(), false, null, 12, null));
    private static final EnterTransition ExtendedFabExpandAnimation = EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween(ComposerKt.invocationKey, 100, MotionTokens.INSTANCE.getEasingLinearCubicBezier()), 0.0f, 2, null).plus(EnterExitTransitionKt.expandHorizontally$default(AnimationSpecKt.tween$default(500, 0, MotionTokens.INSTANCE.getEasingEmphasizedCubicBezier(), 2, null), Alignment.INSTANCE.getStart(), false, null, 12, null));

    /* JADX WARN: Code duplicated, block: B:112:0x016e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:113:0x0170  */
    /* JADX WARN: Code duplicated, block: B:114:0x0175  */
    /* JADX WARN: Code duplicated, block: B:117:0x017c  */
    /* JADX WARN: Code duplicated, block: B:120:0x0189  */
    /* JADX WARN: Code duplicated, block: B:123:0x0196  */
    /* JADX WARN: Code duplicated, block: B:124:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:127:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:129:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:131:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:132:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:134:0x020d  */
    /* JADX WARN: Code duplicated, block: B:137:0x0226  */
    /* JADX WARN: Code duplicated, block: B:140:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:144:0x02ce  */
    /* JADX WARN: Code duplicated, block: B:146:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x0128  */
    /* JADX WARN: Code duplicated, block: B:96:0x0135  */
    /* JADX INFO: renamed from: FloatingActionButton-X-z6DiA, reason: not valid java name */
    public static final void m1555FloatingActionButtonXz6DiA(final Function0<Unit> onClick, Modifier modifier, Shape shape, long containerColor, long contentColor, FloatingActionButtonElevation elevation, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        long containerColor2;
        final long contentColor2;
        FloatingActionButtonElevation elevation2;
        int i2;
        Modifier.Companion modifier2;
        long contentColor3;
        final int $dirty;
        MutableInteractionSource interactionSource2;
        Shape shape3;
        long containerColor3;
        FloatingActionButtonElevation elevation3;
        Modifier modifier3;
        Object it$iv$iv;
        Object value$iv$iv;
        long contentColor4;
        FloatingActionButtonElevation elevation4;
        Composer $composer2;
        Modifier modifier4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-731723913);
        ComposerKt.sourceInformation($composer3, "C(FloatingActionButton)P(6,5,7,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3,4)98@4887L5,99@4951L14,100@4993L31,101@5102L11,102@5165L39,111@5476L53,112@5573L54,105@5251L1386:FloatingActionButton.kt#uh7d8r");
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
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                shape2 = shape;
                int i4 = $composer3.changed(shape2) ? 256 : 128;
                $dirty2 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                containerColor2 = containerColor;
                int i5 = $composer3.changed(containerColor2) ? 2048 : 1024;
                $dirty2 |= i5;
            } else {
                containerColor2 = containerColor;
            }
            $dirty2 |= i5;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                contentColor2 = contentColor;
                int i6 = $composer3.changed(contentColor2) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                contentColor2 = contentColor;
            }
            $dirty2 |= i6;
        } else {
            contentColor2 = contentColor;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                elevation2 = elevation;
                int i7 = $composer3.changed(elevation2) ? 131072 : 65536;
                $dirty2 |= i7;
            } else {
                elevation2 = elevation;
            }
            $dirty2 |= i7;
        } else {
            elevation2 = elevation;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 1048576 : 524288;
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
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                        shape2 = FloatingActionButtonDefaults.INSTANCE.getShape($composer3, 6);
                    }
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                        containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                    }
                    if ((i & 16) != 0) {
                        contentColor3 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty2 >> 9) & 14);
                        $dirty2 &= -57345;
                    } else {
                        contentColor3 = contentColor2;
                    }
                    if ((i & 32) != 0) {
                        elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                        $dirty2 &= -458753;
                    }
                    if (i8 != 0) {
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
                        contentColor2 = contentColor3;
                        shape3 = shape2;
                        containerColor3 = containerColor2;
                        elevation3 = elevation2;
                        $dirty = $dirty3;
                        modifier3 = modifier5;
                    } else {
                        Modifier modifier6 = modifier2;
                        $dirty = $dirty2;
                        interactionSource2 = interactionSource;
                        contentColor2 = contentColor3;
                        shape3 = shape2;
                        containerColor3 = containerColor2;
                        elevation3 = elevation2;
                        modifier3 = modifier6;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 8) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        $dirty = $dirty2 & (-458753);
                        interactionSource2 = interactionSource;
                        shape3 = shape2;
                        containerColor3 = containerColor2;
                        elevation3 = elevation2;
                        modifier3 = modifier;
                    } else {
                        interactionSource2 = interactionSource;
                        $dirty = $dirty2;
                        shape3 = shape2;
                        containerColor3 = containerColor2;
                        elevation3 = elevation2;
                        modifier3 = modifier;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-731723913, $dirty, -1, "androidx.compose.material3.FloatingActionButton (FloatingActionButton.kt:95)");
                }
                contentColor4 = contentColor2;
                elevation4 = elevation3;
                $composer2 = $composer3;
                modifier4 = modifier3;
                SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$2
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
                }, 1, null), false, shape3, containerColor3, contentColor4, elevation3.tonalElevation$material3_release(interactionSource2, $composer3, (($dirty >> 18) & 14) | (($dirty >> 12) & 112)).getValue().m5288unboximpl(), elevation3.shadowElevation$material3_release(interactionSource2, $composer3, (($dirty >> 18) & 14) | (($dirty >> 12) & 112)).getValue().m5288unboximpl(), null, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 1249316354, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$3
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
                        ComposerKt.sourceInformation($composer4, "C115@5698L933:FloatingActionButton.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1249316354, $changed2, -1, "androidx.compose.material3.FloatingActionButton.<anonymous> (FloatingActionButton.kt:114)");
                            }
                            ProvidedValue[] providedValueArr = {ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor2))};
                            final Function2<Composer, Integer, Unit> function2 = content;
                            final int i9 = $dirty;
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer4, -945978686, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$3.1
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
                                    ComposerKt.sourceInformation($composer5, "C121@6166L10,120@6118L503:FloatingActionButton.kt#uh7d8r");
                                    if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-945978686, $changed3, -1, "androidx.compose.material3.FloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:115)");
                                        }
                                        TextStyle textStyleFromToken = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), ExtendedFabPrimaryTokens.INSTANCE.getLabelTextFont());
                                        final Function2<Composer, Integer, Unit> function3 = function2;
                                        final int i10 = i9;
                                        TextKt.ProvideTextStyle(textStyleFromToken, ComposableLambdaKt.composableLambda($composer5, 167946739, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt.FloatingActionButton.3.1.1
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
                                                ComposerKt.sourceInformation($composer6, "C123@6260L347:FloatingActionButton.kt#uh7d8r");
                                                if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(167946739, $changed4, -1, "androidx.compose.material3.FloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:122)");
                                                    }
                                                    Modifier modifier$iv = SizeKt.m518defaultMinSizeVpY3zN4(Modifier.INSTANCE, FabPrimaryTokens.INSTANCE.m2187getContainerWidthD9Ej5fM(), FabPrimaryTokens.INSTANCE.m2186getContainerHeightD9Ej5fM());
                                                    Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                                                    Function2<Composer, Integer, Unit> function4 = function3;
                                                    int i11 = i10;
                                                    $composer6.startReplaceableGroup(733328855);
                                                    ComposerKt.sourceInformation($composer6, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                                    MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer6, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                                                    int $changed$iv$iv = (54 << 3) & 112;
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
                                                    int i12 = ($changed$iv$iv$iv >> 9) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                    int i13 = ((54 >> 6) & 112) | 6;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1329862953, "C130@6596L9:FloatingActionButton.kt#uh7d8r");
                                                    function4.invoke($composer6, Integer.valueOf((i11 >> 21) & 14));
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
                }), $composer2, ($dirty & 14) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty << 9) & 1879048192), 6, 260);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier4 = modifier;
                interactionSource2 = interactionSource;
                $composer2 = $composer3;
                shape3 = shape2;
                containerColor3 = containerColor2;
                elevation4 = elevation2;
                contentColor4 = contentColor2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier7 = modifier4;
            final Shape shape4 = shape3;
            final long j = containerColor3;
            final long j2 = contentColor4;
            final FloatingActionButtonElevation floatingActionButtonElevation = elevation4;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$4
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
                    FloatingActionButtonKt.m1555FloatingActionButtonXz6DiA(onClick, modifier7, shape4, j, j2, floatingActionButtonElevation, mutableInteractionSource, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
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
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                    shape2 = FloatingActionButtonDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    contentColor3 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty2 >> 9) & 14);
                    $dirty2 &= -57345;
                } else {
                    contentColor3 = contentColor2;
                }
                if ((i & 32) != 0) {
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                    $dirty2 &= -458753;
                }
                if (i8 != 0) {
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
                    contentColor2 = contentColor3;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    elevation3 = elevation2;
                    $dirty = $dirty4;
                    modifier3 = modifier8;
                } else {
                    Modifier modifier9 = modifier2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    contentColor2 = contentColor3;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    elevation3 = elevation2;
                    modifier3 = modifier9;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                    shape2 = FloatingActionButtonDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    contentColor3 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty2 >> 9) & 14);
                    $dirty2 &= -57345;
                } else {
                    contentColor3 = contentColor2;
                }
                if ((i & 32) != 0) {
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                    $dirty2 &= -458753;
                }
                if (i8 != 0) {
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
                    contentColor2 = contentColor3;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    elevation3 = elevation2;
                    $dirty = $dirty5;
                    modifier3 = modifier10;
                } else {
                    Modifier modifier11 = modifier2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    contentColor2 = contentColor3;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    elevation3 = elevation2;
                    modifier3 = modifier11;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-731723913, $dirty, -1, "androidx.compose.material3.FloatingActionButton (FloatingActionButton.kt:95)");
            }
            contentColor4 = contentColor2;
            elevation4 = elevation3;
            $composer2 = $composer3;
            modifier4 = modifier3;
            SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$2
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
            }, 1, null), false, shape3, containerColor3, contentColor4, elevation3.tonalElevation$material3_release(interactionSource2, $composer3, (($dirty >> 18) & 14) | (($dirty >> 12) & 112)).getValue().m5288unboximpl(), elevation3.shadowElevation$material3_release(interactionSource2, $composer3, (($dirty >> 18) & 14) | (($dirty >> 12) & 112)).getValue().m5288unboximpl(), null, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 1249316354, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$3
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
                    ComposerKt.sourceInformation($composer4, "C115@5698L933:FloatingActionButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1249316354, $changed2, -1, "androidx.compose.material3.FloatingActionButton.<anonymous> (FloatingActionButton.kt:114)");
                        }
                        ProvidedValue[] providedValueArr = {ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor2))};
                        final Function2<? super Composer, ? super Integer, Unit> function2 = content;
                        final int i9 = $dirty;
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer4, -945978686, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$3.1
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
                                ComposerKt.sourceInformation($composer5, "C121@6166L10,120@6118L503:FloatingActionButton.kt#uh7d8r");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-945978686, $changed3, -1, "androidx.compose.material3.FloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:115)");
                                    }
                                    TextStyle textStyleFromToken = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), ExtendedFabPrimaryTokens.INSTANCE.getLabelTextFont());
                                    final Function2<? super Composer, ? super Integer, Unit> function3 = function2;
                                    final int i10 = i9;
                                    TextKt.ProvideTextStyle(textStyleFromToken, ComposableLambdaKt.composableLambda($composer5, 167946739, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt.FloatingActionButton.3.1.1
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
                                            ComposerKt.sourceInformation($composer6, "C123@6260L347:FloatingActionButton.kt#uh7d8r");
                                            if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(167946739, $changed4, -1, "androidx.compose.material3.FloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:122)");
                                                }
                                                Modifier modifier$iv = SizeKt.m518defaultMinSizeVpY3zN4(Modifier.INSTANCE, FabPrimaryTokens.INSTANCE.m2187getContainerWidthD9Ej5fM(), FabPrimaryTokens.INSTANCE.m2186getContainerHeightD9Ej5fM());
                                                Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                                                Function2<Composer, Integer, Unit> function4 = function3;
                                                int i11 = i10;
                                                $composer6.startReplaceableGroup(733328855);
                                                ComposerKt.sourceInformation($composer6, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                                MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer6, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                                                int $changed$iv$iv = (54 << 3) & 112;
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
                                                int i12 = ($changed$iv$iv$iv >> 9) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                int i13 = ((54 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -1329862953, "C130@6596L9:FloatingActionButton.kt#uh7d8r");
                                                function4.invoke($composer6, Integer.valueOf((i11 >> 21) & 14));
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
            }), $composer2, ($dirty & 14) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty << 9) & 1879048192), 6, 260);
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
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                    shape2 = FloatingActionButtonDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    contentColor3 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty2 >> 9) & 14);
                    $dirty2 &= -57345;
                } else {
                    contentColor3 = contentColor2;
                }
                if ((i & 32) != 0) {
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                    $dirty2 &= -458753;
                }
                if (i8 != 0) {
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
                    contentColor2 = contentColor3;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    elevation3 = elevation2;
                    $dirty = $dirty6;
                    modifier3 = modifier12;
                } else {
                    Modifier modifier13 = modifier2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    contentColor2 = contentColor3;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    elevation3 = elevation2;
                    modifier3 = modifier13;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                    shape2 = FloatingActionButtonDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    contentColor3 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty2 >> 9) & 14);
                    $dirty2 &= -57345;
                } else {
                    contentColor3 = contentColor2;
                }
                if ((i & 32) != 0) {
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                    $dirty2 &= -458753;
                }
                if (i8 != 0) {
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
                    contentColor2 = contentColor3;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    elevation3 = elevation2;
                    $dirty = $dirty7;
                    modifier3 = modifier14;
                } else {
                    Modifier modifier15 = modifier2;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    contentColor2 = contentColor3;
                    shape3 = shape2;
                    containerColor3 = containerColor2;
                    elevation3 = elevation2;
                    modifier3 = modifier15;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-731723913, $dirty, -1, "androidx.compose.material3.FloatingActionButton (FloatingActionButton.kt:95)");
            }
            contentColor4 = contentColor2;
            elevation4 = elevation3;
            $composer2 = $composer3;
            modifier4 = modifier3;
            SurfaceKt.m1809Surfaceo_FOJdg(onClick, SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$2
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
            }, 1, null), false, shape3, containerColor3, contentColor4, elevation3.tonalElevation$material3_release(interactionSource2, $composer3, (($dirty >> 18) & 14) | (($dirty >> 12) & 112)).getValue().m5288unboximpl(), elevation3.shadowElevation$material3_release(interactionSource2, $composer3, (($dirty >> 18) & 14) | (($dirty >> 12) & 112)).getValue().m5288unboximpl(), null, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 1249316354, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$3
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
                    ComposerKt.sourceInformation($composer4, "C115@5698L933:FloatingActionButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1249316354, $changed2, -1, "androidx.compose.material3.FloatingActionButton.<anonymous> (FloatingActionButton.kt:114)");
                        }
                        ProvidedValue[] providedValueArr = {ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor2))};
                        final Function2<? super Composer, ? super Integer, Unit> function2 = content;
                        final int i9 = $dirty;
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer4, -945978686, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$3.1
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
                                ComposerKt.sourceInformation($composer5, "C121@6166L10,120@6118L503:FloatingActionButton.kt#uh7d8r");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-945978686, $changed3, -1, "androidx.compose.material3.FloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:115)");
                                    }
                                    TextStyle textStyleFromToken = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), ExtendedFabPrimaryTokens.INSTANCE.getLabelTextFont());
                                    final Function2<? super Composer, ? super Integer, Unit> function3 = function2;
                                    final int i10 = i9;
                                    TextKt.ProvideTextStyle(textStyleFromToken, ComposableLambdaKt.composableLambda($composer5, 167946739, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt.FloatingActionButton.3.1.1
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
                                            ComposerKt.sourceInformation($composer6, "C123@6260L347:FloatingActionButton.kt#uh7d8r");
                                            if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(167946739, $changed4, -1, "androidx.compose.material3.FloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:122)");
                                                }
                                                Modifier modifier$iv = SizeKt.m518defaultMinSizeVpY3zN4(Modifier.INSTANCE, FabPrimaryTokens.INSTANCE.m2187getContainerWidthD9Ej5fM(), FabPrimaryTokens.INSTANCE.m2186getContainerHeightD9Ej5fM());
                                                Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                                                Function2<Composer, Integer, Unit> function4 = function3;
                                                int i11 = i10;
                                                $composer6.startReplaceableGroup(733328855);
                                                ComposerKt.sourceInformation($composer6, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                                MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer6, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                                                int $changed$iv$iv = (54 << 3) & 112;
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
                                                int i12 = ($changed$iv$iv$iv >> 9) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                int i13 = ((54 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer6, -1329862953, "C130@6596L9:FloatingActionButton.kt#uh7d8r");
                                                function4.invoke($composer6, Integer.valueOf((i11 >> 21) & 14));
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
            }), $composer2, ($dirty & 14) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty << 9) & 1879048192), 6, 260);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier16 = modifier4;
        final Shape shape5 = shape3;
        final long j3 = containerColor3;
        final long j4 = contentColor4;
        final FloatingActionButtonElevation floatingActionButtonElevation2 = elevation4;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$4
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
                FloatingActionButtonKt.m1555FloatingActionButtonXz6DiA(onClick, modifier16, shape5, j3, j4, floatingActionButtonElevation2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:112:0x0176 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:113:0x0178  */
    /* JADX WARN: Code duplicated, block: B:114:0x017d  */
    /* JADX WARN: Code duplicated, block: B:117:0x0184  */
    /* JADX WARN: Code duplicated, block: B:118:0x018f  */
    /* JADX WARN: Code duplicated, block: B:121:0x0195  */
    /* JADX WARN: Code duplicated, block: B:124:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:125:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:128:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:129:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:131:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:133:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:134:0x0200  */
    /* JADX WARN: Code duplicated, block: B:136:0x0212  */
    /* JADX WARN: Code duplicated, block: B:139:0x0223  */
    /* JADX WARN: Code duplicated, block: B:142:0x0290  */
    /* JADX WARN: Code duplicated, block: B:146:0x029a  */
    /* JADX WARN: Code duplicated, block: B:148:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x012a  */
    /* JADX WARN: Code duplicated, block: B:96:0x0137  */
    /* JADX INFO: renamed from: SmallFloatingActionButton-X-z6DiA, reason: not valid java name */
    public static final void m1557SmallFloatingActionButtonXz6DiA(final Function0<Unit> onClick, Modifier modifier, Shape shape, long containerColor, long contentColor, FloatingActionButtonElevation elevation, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        long containerColor2;
        long j;
        FloatingActionButtonElevation elevation2;
        MutableInteractionSource mutableInteractionSource;
        int i2;
        Modifier.Companion modifier2;
        Shape shape3;
        int $dirty;
        long contentColor2;
        Modifier modifier3;
        FloatingActionButtonElevation elevation3;
        int $dirty2;
        MutableInteractionSource interactionSource2;
        Object it$iv$iv;
        Object value$iv$iv;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(1444748300);
        ComposerKt.sourceInformation($composer3, "C(SmallFloatingActionButton)P(6,5,7,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3,4)166@8483L10,167@8552L14,168@8594L31,169@8703L11,170@8766L39,173@8852L431:FloatingActionButton.kt#uh7d8r");
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
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                shape2 = shape;
                int i4 = $composer3.changed(shape2) ? 256 : 128;
                $dirty3 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                containerColor2 = containerColor;
                int i5 = $composer3.changed(containerColor2) ? 2048 : 1024;
                $dirty3 |= i5;
            } else {
                containerColor2 = containerColor;
            }
            $dirty3 |= i5;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                j = contentColor;
                int i6 = $composer3.changed(j) ? 16384 : 8192;
                $dirty3 |= i6;
            } else {
                j = contentColor;
            }
            $dirty3 |= i6;
        } else {
            j = contentColor;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                elevation2 = elevation;
                int i7 = $composer3.changed(elevation2) ? 131072 : 65536;
                $dirty3 |= i7;
            } else {
                elevation2 = elevation;
            }
            $dirty3 |= i7;
        } else {
            elevation2 = elevation;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty3 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 3670016) == 0) {
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
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        $dirty3 &= -897;
                        shape3 = FloatingActionButtonDefaults.INSTANCE.getSmallShape($composer3, 6);
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 8) != 0) {
                        $dirty3 &= -7169;
                        containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                    }
                    if ((i & 16) != 0) {
                        $dirty = $dirty3 & (-57345);
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty3 >> 9) & 14);
                    } else {
                        $dirty = $dirty3;
                        contentColor2 = j;
                    }
                    if ((i & 32) != 0) {
                        $dirty &= -458753;
                        elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
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
                        modifier3 = modifier2;
                        elevation3 = elevation2;
                        $dirty2 = $dirty;
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    } else {
                        modifier3 = modifier2;
                        elevation3 = elevation2;
                        $dirty2 = $dirty;
                        interactionSource2 = mutableInteractionSource;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty3 &= -897;
                    }
                    if ((i & 8) != 0) {
                        $dirty3 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty3 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        modifier3 = modifier;
                        shape3 = shape2;
                        containerColor2 = containerColor2;
                        contentColor2 = j;
                        interactionSource2 = mutableInteractionSource;
                        elevation3 = elevation2;
                        $dirty2 = $dirty3 & (-458753);
                    } else {
                        modifier3 = modifier;
                        shape3 = shape2;
                        containerColor2 = containerColor2;
                        contentColor2 = j;
                        interactionSource2 = mutableInteractionSource;
                        elevation3 = elevation2;
                        $dirty2 = $dirty3;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1444748300, $dirty2, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:163)");
                }
                $composer2 = $composer3;
                m1555FloatingActionButtonXz6DiA(onClick, SizeKt.m538sizeInqDBjuR0$default(modifier3, FabPrimarySmallTokens.INSTANCE.m2176getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2175getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), shape3, containerColor2, contentColor2, elevation3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | ($dirty2 & 3670016) | ($dirty2 & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier3 = modifier;
                shape3 = shape2;
                containerColor2 = containerColor2;
                contentColor2 = j;
                interactionSource2 = mutableInteractionSource;
                elevation3 = elevation2;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final Shape shape4 = shape3;
            final long j2 = containerColor2;
            final long j3 = contentColor2;
            final FloatingActionButtonElevation floatingActionButtonElevation = elevation3;
            final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$SmallFloatingActionButton$2
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
                    FloatingActionButtonKt.m1557SmallFloatingActionButtonXz6DiA(onClick, modifier4, shape4, j2, j3, floatingActionButtonElevation, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 12582912;
        $dirty3 |= i2;
        if ((23967451 & $dirty3) == 4793490) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getSmallShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty3 >> 9) & 14);
                } else {
                    $dirty = $dirty3;
                    contentColor2 = j;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
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
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = mutableInteractionSource;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getSmallShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty3 >> 9) & 14);
                } else {
                    $dirty = $dirty3;
                    contentColor2 = j;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
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
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = mutableInteractionSource;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1444748300, $dirty2, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:163)");
            }
            $composer2 = $composer3;
            m1555FloatingActionButtonXz6DiA(onClick, SizeKt.m538sizeInqDBjuR0$default(modifier3, FabPrimarySmallTokens.INSTANCE.m2176getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2175getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), shape3, containerColor2, contentColor2, elevation3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | ($dirty2 & 3670016) | ($dirty2 & 29360128), 0);
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
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getSmallShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty3 >> 9) & 14);
                } else {
                    $dirty = $dirty3;
                    contentColor2 = j;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
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
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = mutableInteractionSource;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getSmallShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty3 >> 9) & 14);
                } else {
                    $dirty = $dirty3;
                    contentColor2 = j;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
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
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = mutableInteractionSource;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1444748300, $dirty2, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:163)");
            }
            $composer2 = $composer3;
            m1555FloatingActionButtonXz6DiA(onClick, SizeKt.m538sizeInqDBjuR0$default(modifier3, FabPrimarySmallTokens.INSTANCE.m2176getContainerWidthD9Ej5fM(), FabPrimarySmallTokens.INSTANCE.m2175getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), shape3, containerColor2, contentColor2, elevation3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | ($dirty2 & 3670016) | ($dirty2 & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Shape shape5 = shape3;
        final long j4 = containerColor2;
        final long j5 = contentColor2;
        final FloatingActionButtonElevation floatingActionButtonElevation2 = elevation3;
        final MutableInteractionSource mutableInteractionSource3 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$SmallFloatingActionButton$2
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
                FloatingActionButtonKt.m1557SmallFloatingActionButtonXz6DiA(onClick, modifier5, shape5, j4, j5, floatingActionButtonElevation2, mutableInteractionSource3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:112:0x0176 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:113:0x0178  */
    /* JADX WARN: Code duplicated, block: B:114:0x017d  */
    /* JADX WARN: Code duplicated, block: B:117:0x0184  */
    /* JADX WARN: Code duplicated, block: B:118:0x018f  */
    /* JADX WARN: Code duplicated, block: B:121:0x0195  */
    /* JADX WARN: Code duplicated, block: B:124:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:125:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:128:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:129:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:131:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:133:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:134:0x0200  */
    /* JADX WARN: Code duplicated, block: B:136:0x0212  */
    /* JADX WARN: Code duplicated, block: B:139:0x0223  */
    /* JADX WARN: Code duplicated, block: B:142:0x0290  */
    /* JADX WARN: Code duplicated, block: B:146:0x029a  */
    /* JADX WARN: Code duplicated, block: B:148:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x012a  */
    /* JADX WARN: Code duplicated, block: B:96:0x0137  */
    /* JADX INFO: renamed from: LargeFloatingActionButton-X-z6DiA, reason: not valid java name */
    public static final void m1556LargeFloatingActionButtonXz6DiA(final Function0<Unit> onClick, Modifier modifier, Shape shape, long containerColor, long contentColor, FloatingActionButtonElevation elevation, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        long containerColor2;
        long j;
        FloatingActionButtonElevation elevation2;
        MutableInteractionSource mutableInteractionSource;
        int i2;
        Modifier.Companion modifier2;
        Shape shape3;
        int $dirty;
        long contentColor2;
        Modifier modifier3;
        FloatingActionButtonElevation elevation3;
        int $dirty2;
        MutableInteractionSource interactionSource2;
        Object it$iv$iv;
        Object value$iv$iv;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-1650866856);
        ComposerKt.sourceInformation($composer3, "C(LargeFloatingActionButton)P(6,5,7,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3,4)218@11129L10,219@11198L14,220@11240L31,221@11349L11,222@11412L39,225@11498L431:FloatingActionButton.kt#uh7d8r");
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
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                shape2 = shape;
                int i4 = $composer3.changed(shape2) ? 256 : 128;
                $dirty3 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                containerColor2 = containerColor;
                int i5 = $composer3.changed(containerColor2) ? 2048 : 1024;
                $dirty3 |= i5;
            } else {
                containerColor2 = containerColor;
            }
            $dirty3 |= i5;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                j = contentColor;
                int i6 = $composer3.changed(j) ? 16384 : 8192;
                $dirty3 |= i6;
            } else {
                j = contentColor;
            }
            $dirty3 |= i6;
        } else {
            j = contentColor;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                elevation2 = elevation;
                int i7 = $composer3.changed(elevation2) ? 131072 : 65536;
                $dirty3 |= i7;
            } else {
                elevation2 = elevation;
            }
            $dirty3 |= i7;
        } else {
            elevation2 = elevation;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty3 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 3670016) == 0) {
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
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 4) != 0) {
                        $dirty3 &= -897;
                        shape3 = FloatingActionButtonDefaults.INSTANCE.getLargeShape($composer3, 6);
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 8) != 0) {
                        $dirty3 &= -7169;
                        containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                    }
                    if ((i & 16) != 0) {
                        $dirty = $dirty3 & (-57345);
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty3 >> 9) & 14);
                    } else {
                        $dirty = $dirty3;
                        contentColor2 = j;
                    }
                    if ((i & 32) != 0) {
                        $dirty &= -458753;
                        elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
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
                        modifier3 = modifier2;
                        elevation3 = elevation2;
                        $dirty2 = $dirty;
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    } else {
                        modifier3 = modifier2;
                        elevation3 = elevation2;
                        $dirty2 = $dirty;
                        interactionSource2 = mutableInteractionSource;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty3 &= -897;
                    }
                    if ((i & 8) != 0) {
                        $dirty3 &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty3 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        modifier3 = modifier;
                        shape3 = shape2;
                        containerColor2 = containerColor2;
                        contentColor2 = j;
                        interactionSource2 = mutableInteractionSource;
                        elevation3 = elevation2;
                        $dirty2 = $dirty3 & (-458753);
                    } else {
                        modifier3 = modifier;
                        shape3 = shape2;
                        containerColor2 = containerColor2;
                        contentColor2 = j;
                        interactionSource2 = mutableInteractionSource;
                        elevation3 = elevation2;
                        $dirty2 = $dirty3;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1650866856, $dirty2, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:215)");
                }
                $composer2 = $composer3;
                m1555FloatingActionButtonXz6DiA(onClick, SizeKt.m538sizeInqDBjuR0$default(modifier3, FabPrimaryLargeTokens.INSTANCE.m2165getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2164getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), shape3, containerColor2, contentColor2, elevation3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | ($dirty2 & 3670016) | ($dirty2 & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier3 = modifier;
                shape3 = shape2;
                containerColor2 = containerColor2;
                contentColor2 = j;
                interactionSource2 = mutableInteractionSource;
                elevation3 = elevation2;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final Shape shape4 = shape3;
            final long j2 = containerColor2;
            final long j3 = contentColor2;
            final FloatingActionButtonElevation floatingActionButtonElevation = elevation3;
            final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$LargeFloatingActionButton$2
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
                    FloatingActionButtonKt.m1556LargeFloatingActionButtonXz6DiA(onClick, modifier4, shape4, j2, j3, floatingActionButtonElevation, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 12582912;
        $dirty3 |= i2;
        if ((23967451 & $dirty3) == 4793490) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getLargeShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty3 >> 9) & 14);
                } else {
                    $dirty = $dirty3;
                    contentColor2 = j;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
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
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = mutableInteractionSource;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getLargeShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty3 >> 9) & 14);
                } else {
                    $dirty = $dirty3;
                    contentColor2 = j;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
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
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = mutableInteractionSource;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1650866856, $dirty2, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:215)");
            }
            $composer2 = $composer3;
            m1555FloatingActionButtonXz6DiA(onClick, SizeKt.m538sizeInqDBjuR0$default(modifier3, FabPrimaryLargeTokens.INSTANCE.m2165getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2164getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), shape3, containerColor2, contentColor2, elevation3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | ($dirty2 & 3670016) | ($dirty2 & 29360128), 0);
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
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getLargeShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty3 >> 9) & 14);
                } else {
                    $dirty = $dirty3;
                    contentColor2 = j;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
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
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = mutableInteractionSource;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getLargeShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty3 >> 9) & 14);
                } else {
                    $dirty = $dirty3;
                    contentColor2 = j;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
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
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    modifier3 = modifier2;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = mutableInteractionSource;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1650866856, $dirty2, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:215)");
            }
            $composer2 = $composer3;
            m1555FloatingActionButtonXz6DiA(onClick, SizeKt.m538sizeInqDBjuR0$default(modifier3, FabPrimaryLargeTokens.INSTANCE.m2165getContainerWidthD9Ej5fM(), FabPrimaryLargeTokens.INSTANCE.m2164getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), shape3, containerColor2, contentColor2, elevation3, interactionSource2, content, $composer3, ($dirty2 & 14) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | ($dirty2 & 3670016) | ($dirty2 & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Shape shape5 = shape3;
        final long j4 = containerColor2;
        final long j5 = contentColor2;
        final FloatingActionButtonElevation floatingActionButtonElevation2 = elevation3;
        final MutableInteractionSource mutableInteractionSource3 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$LargeFloatingActionButton$2
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
                FloatingActionButtonKt.m1556LargeFloatingActionButtonXz6DiA(onClick, modifier5, shape5, j4, j5, floatingActionButtonElevation2, mutableInteractionSource3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: renamed from: ExtendedFloatingActionButton-X-z6DiA, reason: not valid java name */
    public static final void m1554ExtendedFloatingActionButtonXz6DiA(final Function0<Unit> onClick, Modifier modifier, Shape shape, long containerColor, long contentColor, FloatingActionButtonElevation elevation, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        long containerColor2;
        long j;
        FloatingActionButtonElevation elevation2;
        MutableInteractionSource mutableInteractionSource;
        Shape shape3;
        int $dirty;
        long contentColor2;
        Modifier modifier2;
        FloatingActionButtonElevation elevation3;
        final int $dirty2;
        MutableInteractionSource interactionSource2;
        Object value$iv$iv;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-326283107);
        ComposerKt.sourceInformation($composer3, "C(ExtendedFloatingActionButton)P(6,5,7,0:c#ui.graphics.Color,2:c#ui.graphics.Color,3,4)273@13913L16,274@13988L14,275@14030L31,276@14139L11,277@14202L39,280@14297L595:FloatingActionButton.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty3 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty3 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                shape2 = shape;
                int i3 = $composer3.changed(shape2) ? 256 : 128;
                $dirty3 |= i3;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i3;
        } else {
            shape2 = shape;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                containerColor2 = containerColor;
                int i4 = $composer3.changed(containerColor2) ? 2048 : 1024;
                $dirty3 |= i4;
            } else {
                containerColor2 = containerColor;
            }
            $dirty3 |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                j = contentColor;
                int i5 = $composer3.changed(j) ? 16384 : 8192;
                $dirty3 |= i5;
            } else {
                j = contentColor;
            }
            $dirty3 |= i5;
        } else {
            j = contentColor;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                elevation2 = elevation;
                int i6 = $composer3.changed(elevation2) ? 131072 : 65536;
                $dirty3 |= i6;
            } else {
                elevation2 = elevation;
            }
            $dirty3 |= i6;
        } else {
            elevation2 = elevation;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty3 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 3670016) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty3 |= $composer3.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 128) != 0) {
            $dirty3 |= 12582912;
        } else if ((29360128 & $changed) == 0) {
            $dirty3 |= $composer3.changedInstance(content) ? 8388608 : 4194304;
        }
        if ((23967451 & $dirty3) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier2 = modifier;
            shape3 = shape2;
            containerColor2 = containerColor2;
            contentColor2 = j;
            interactionSource2 = mutableInteractionSource;
            elevation3 = elevation2;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape($composer3, 6);
                } else {
                    shape3 = shape2;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty3 >> 9) & 14);
                } else {
                    $dirty = $dirty3;
                    contentColor2 = j;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                }
                if (i7 != 0) {
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
                    modifier2 = modifier3;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    modifier2 = modifier3;
                    elevation3 = elevation2;
                    $dirty2 = $dirty;
                    interactionSource2 = mutableInteractionSource;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    modifier2 = modifier;
                    shape3 = shape2;
                    containerColor2 = containerColor2;
                    contentColor2 = j;
                    interactionSource2 = mutableInteractionSource;
                    elevation3 = elevation2;
                    $dirty2 = $dirty3 & (-458753);
                } else {
                    modifier2 = modifier;
                    shape3 = shape2;
                    containerColor2 = containerColor2;
                    contentColor2 = j;
                    interactionSource2 = mutableInteractionSource;
                    elevation3 = elevation2;
                    $dirty2 = $dirty3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-326283107, $dirty2, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:270)");
            }
            $composer2 = $composer3;
            m1555FloatingActionButtonXz6DiA(onClick, modifier2, shape3, containerColor2, contentColor2, elevation3, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 398457247, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$2
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
                    ComposerKt.sourceInformation($composer4, "C289@14570L316:FloatingActionButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(398457247, $changed2, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:288)");
                        }
                        Modifier modifier$iv = PaddingKt.m489paddingVpY3zN4$default(SizeKt.m538sizeInqDBjuR0$default(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabMinimumWidth, 0.0f, 0.0f, 0.0f, 14, null), FloatingActionButtonKt.ExtendedFabTextPadding, 0.0f, 2, null);
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getCenter();
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        Function3<RowScope, Composer, Integer, Unit> function3 = content;
                        int $changed$iv = (($dirty2 >> 12) & 7168) | 438;
                        $composer4.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer4, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer4, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
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
                        int i8 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                        function3.invoke(RowScopeInstance.INSTANCE, $composer4, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
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
            }), $composer3, ($dirty2 & 14) | 12582912 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | ($dirty2 & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier2;
        final Shape shape4 = shape3;
        final long j2 = containerColor2;
        final long j3 = contentColor2;
        final FloatingActionButtonElevation floatingActionButtonElevation = elevation3;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
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
                FloatingActionButtonKt.m1554ExtendedFloatingActionButtonXz6DiA(onClick, modifier4, shape4, j2, j3, floatingActionButtonElevation, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: renamed from: ExtendedFloatingActionButton-ElI5-7k, reason: not valid java name */
    public static final void m1553ExtendedFloatingActionButtonElI57k(final Function2<? super Composer, ? super Integer, Unit> text, final Function2<? super Composer, ? super Integer, Unit> icon, final Function0<Unit> onClick, Modifier modifier, boolean expanded, Shape shape, long containerColor, long contentColor, FloatingActionButtonElevation elevation, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        Shape shape2;
        int $dirty;
        FloatingActionButtonElevation floatingActionButtonElevation;
        MutableInteractionSource mutableInteractionSource;
        int $dirty2;
        Shape shape3;
        long containerColor2;
        long contentColor2;
        int $dirty3;
        FloatingActionButtonElevation elevation2;
        Shape shape4;
        final boolean expanded2;
        final int $dirty4;
        MutableInteractionSource interactionSource2;
        Modifier modifier3;
        FloatingActionButtonElevation elevation3;
        Object value$iv$iv;
        boolean expanded3;
        Composer $composer2;
        int $dirty5;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer3 = $composer.startRestartGroup(-1387401842);
        ComposerKt.sourceInformation($composer3, "C(ExtendedFloatingActionButton)P(9,4,7,6,3,8,0:c#ui.graphics.Color,1:c#ui.graphics.Color)343@17455L16,344@17530L14,345@17572L31,346@17681L11,347@17744L39,349@17793L1269:FloatingActionButton.kt#uh7d8r");
        int $dirty6 = $changed;
        if ((i & 1) != 0) {
            $dirty6 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty6 |= $composer3.changedInstance(text) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty6 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty6 |= $composer3.changedInstance(icon) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty6 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty6 |= $composer3.changedInstance(onClick) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty6 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 7168) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty6 |= 24576;
            z = expanded;
        } else if (($changed & 57344) == 0) {
            z = expanded;
            $dirty6 |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = expanded;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i4 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty6 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty6 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 3670016) == 0) {
            $dirty6 |= ((i & 64) == 0 && $composer3.changed(containerColor)) ? 1048576 : 524288;
        }
        if (($changed & 29360128) == 0) {
            if ((i & 128) == 0) {
                $dirty5 = $dirty6;
                int i5 = $composer3.changed(contentColor) ? 8388608 : 4194304;
                $dirty = $dirty5 | i5;
            } else {
                $dirty5 = $dirty6;
            }
            $dirty = $dirty5 | i5;
        } else {
            $dirty = $dirty6;
        }
        if (($changed & 234881024) == 0) {
            if ((i & 256) == 0) {
                floatingActionButtonElevation = elevation;
                int i6 = $composer3.changed(floatingActionButtonElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty |= i6;
            } else {
                floatingActionButtonElevation = elevation;
            }
            $dirty |= i6;
        } else {
            floatingActionButtonElevation = elevation;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty |= 805306368;
            mutableInteractionSource = interactionSource;
        } else if ((1879048192 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty |= $composer3.changed(mutableInteractionSource) ? 536870912 : 268435456;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if (($dirty & 1533916891) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            containerColor2 = containerColor;
            contentColor2 = contentColor;
            expanded3 = z;
            shape4 = shape2;
            interactionSource2 = mutableInteractionSource;
            elevation3 = floatingActionButtonElevation;
            $composer2 = $composer3;
            modifier3 = modifier2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                boolean expanded4 = i3 != 0 ? true : z;
                if ((i & 32) != 0) {
                    int $dirty7 = $dirty & (-458753);
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape($composer3, 6);
                    $dirty2 = $dirty7;
                } else {
                    $dirty2 = $dirty;
                    shape3 = shape2;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                    containerColor2 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                } else {
                    containerColor2 = containerColor;
                }
                if ((i & 128) != 0) {
                    $dirty3 = $dirty2 & (-29360129);
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer3, ($dirty2 >> 18) & 14);
                } else {
                    contentColor2 = contentColor;
                    $dirty3 = $dirty2;
                }
                if ((i & 256) != 0) {
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m1550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                    $dirty3 &= -234881025;
                } else {
                    elevation2 = floatingActionButtonElevation;
                }
                if (i7 != 0) {
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
                    MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) value$iv$iv;
                    shape4 = shape3;
                    expanded2 = expanded4;
                    $dirty4 = $dirty3;
                    modifier3 = modifier4;
                    elevation3 = elevation2;
                    interactionSource2 = mutableInteractionSource2;
                } else {
                    shape4 = shape3;
                    expanded2 = expanded4;
                    $dirty4 = $dirty3;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier4;
                    elevation3 = elevation2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                }
                if ((i & 256) != 0) {
                    containerColor2 = containerColor;
                    contentColor2 = contentColor;
                    $dirty4 = $dirty & (-234881025);
                    modifier3 = modifier2;
                    shape4 = shape2;
                    interactionSource2 = mutableInteractionSource;
                    elevation3 = floatingActionButtonElevation;
                    expanded2 = z;
                } else {
                    containerColor2 = containerColor;
                    contentColor2 = contentColor;
                    shape4 = shape2;
                    interactionSource2 = mutableInteractionSource;
                    elevation3 = floatingActionButtonElevation;
                    $dirty4 = $dirty;
                    modifier3 = modifier2;
                    expanded2 = z;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1387401842, $dirty4, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:337)");
            }
            expanded3 = expanded2;
            $composer2 = $composer3;
            m1555FloatingActionButtonXz6DiA(onClick, modifier3, shape4, containerColor2, contentColor2, elevation3, interactionSource2, ComposableLambdaKt.composableLambda($composer3, 1172118032, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5
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
                    float startPadding;
                    float endPadding;
                    float fM2187getContainerWidthD9Ej5fM;
                    ComposerKt.sourceInformation($composer4, "C361@18218L838:FloatingActionButton.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1172118032, $changed2, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:357)");
                        }
                        if (expanded2) {
                            startPadding = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                        } else {
                            startPadding = Dp.m5274constructorimpl(0);
                        }
                        if (expanded2) {
                            endPadding = FloatingActionButtonKt.ExtendedFabTextPadding;
                        } else {
                            endPadding = Dp.m5274constructorimpl(0);
                        }
                        Modifier.Companion companion = Modifier.INSTANCE;
                        if (expanded2) {
                            fM2187getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                        } else {
                            fM2187getContainerWidthD9Ej5fM = FabPrimaryTokens.INSTANCE.m2187getContainerWidthD9Ej5fM();
                        }
                        Modifier modifierM491paddingqDBjuR0$default = PaddingKt.m491paddingqDBjuR0$default(SizeKt.m538sizeInqDBjuR0$default(companion, fM2187getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), startPadding, 0.0f, endPadding, 0.0f, 10, null);
                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                        Arrangement.HorizontalOrVertical start = expanded2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                        Function2<Composer, Integer, Unit> function2 = icon;
                        final int i8 = $dirty4;
                        boolean z2 = expanded2;
                        final Function2<Composer, Integer, Unit> function3 = text;
                        $composer4.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer4, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(start, centerVertically, $composer4, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv = (384 << 3) & 112;
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
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifierM491paddingqDBjuR0$default);
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
                        ComposerKt.sourceInformationMarkerStart($composer4, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                        RowScope $this$invoke_u24lambda_u240 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1419543422, "C371@18666L6,372@18685L361:FloatingActionButton.kt#uh7d8r");
                        function2.invoke($composer4, Integer.valueOf((i8 >> 3) & 14));
                        AnimatedVisibilityKt.AnimatedVisibility($this$invoke_u24lambda_u240, z2, (Modifier) null, FloatingActionButtonKt.ExtendedFabExpandAnimation, FloatingActionButtonKt.ExtendedFabCollapseAnimation, (String) null, ComposableLambdaKt.composableLambda($composer4, 176242764, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                invoke(animatedVisibilityScope, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedVisibilityScope AnimatedVisibility, Composer $composer5, int $changed3) {
                                Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
                                ComposerKt.sourceInformation($composer5, "C377@18878L154:FloatingActionButton.kt#uh7d8r");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(176242764, $changed3, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:376)");
                                }
                                Modifier modifier$iv = SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$1$1.1
                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver clearAndSetSemantics) {
                                        Intrinsics.checkNotNullParameter(clearAndSetSemantics, "$this$clearAndSetSemantics");
                                    }
                                });
                                Function2<Composer, Integer, Unit> function4 = function3;
                                int i10 = i8;
                                $composer5.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer5, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
                                MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                int $changed$iv$iv2 = (0 << 3) & 112;
                                $composer5.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume4 = $composer5.consume(localDensity2);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                Density density$iv$iv2 = (Density) objConsume4;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume5 = $composer5.consume(localLayoutDirection2);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer5.consume(localViewConfiguration2);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv);
                                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                if (!($composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer5.startReusableNode();
                                if ($composer5.getInserting()) {
                                    $composer5.createNode(constructor2);
                                } else {
                                    $composer5.useNode();
                                }
                                $composer5.disableReusing();
                                Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer5);
                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                $composer5.enableReusing();
                                function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                $composer5.startReplaceableGroup(2058660585);
                                int i11 = ($changed$iv$iv$iv2 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                int i12 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer5, 180818004, "C378@18938L49,379@19008L6:FloatingActionButton.kt#uh7d8r");
                                SpacerKt.Spacer(SizeKt.m539width3ABfNKs(Modifier.INSTANCE, FloatingActionButtonKt.ExtendedFabEndIconPadding), $composer5, 6);
                                function4.invoke($composer5, Integer.valueOf(i10 & 14));
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endReplaceableGroup();
                                $composer5.endNode();
                                $composer5.endReplaceableGroup();
                                $composer5.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), $composer4, ((((384 >> 6) & 112) | 6) & 14) | 1600512 | ((i8 >> 9) & 112), 18);
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
            }), $composer3, (($dirty4 >> 6) & 14) | 12582912 | (($dirty4 >> 6) & 112) | (($dirty4 >> 9) & 896) | (($dirty4 >> 9) & 7168) | (($dirty4 >> 9) & 57344) | (($dirty4 >> 9) & 458752) | (($dirty4 >> 9) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final boolean z2 = expanded3;
        final Shape shape5 = shape4;
        final long j = containerColor2;
        final long j2 = contentColor2;
        final FloatingActionButtonElevation floatingActionButtonElevation2 = elevation3;
        final MutableInteractionSource mutableInteractionSource3 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$6
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
                FloatingActionButtonKt.m1553ExtendedFloatingActionButtonElI57k(text, icon, onClick, modifier5, z2, shape5, j, j2, floatingActionButtonElevation2, mutableInteractionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }
}
