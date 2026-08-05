package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.tokens.OutlinedTextFieldTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b)\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JS\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u0004H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u009c\u0002\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\u0011\u0010\"\u001a\r\u0012\u0004\u0012\u00020\u00130#¢\u0006\u0002\b$2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0016\u001a\u00020\u00152\u0015\b\u0002\u0010(\u001a\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u00010#¢\u0006\u0002\b$2\u0015\b\u0002\u0010)\u001a\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u00010#¢\u0006\u0002\b$2\u0015\b\u0002\u0010*\u001a\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u00010#¢\u0006\u0002\b$2\u0015\b\u0002\u0010+\u001a\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u00010#¢\u0006\u0002\b$2\u0015\b\u0002\u0010,\u001a\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u00010#¢\u0006\u0002\b$2\u0015\b\u0002\u0010-\u001a\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u00010#¢\u0006\u0002\b$2\u0015\b\u0002\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u00010#¢\u0006\u0002\b$2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010/\u001a\u0002002\u0013\b\u0002\u00101\u001a\r\u0012\u0004\u0012\u00020\u00130#¢\u0006\u0002\b$H\u0007¢\u0006\u0002\u00102JÃ\u0003\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002042\b\b\u0002\u00106\u001a\u0002042\b\b\u0002\u00107\u001a\u0002042\b\b\u0002\u00108\u001a\u0002042\b\b\u0002\u00109\u001a\u0002042\b\b\u0002\u0010:\u001a\u0002042\b\b\u0002\u0010;\u001a\u0002042\b\b\u0002\u0010<\u001a\u0002042\b\b\u0002\u0010=\u001a\u0002042\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u0002042\b\b\u0002\u0010A\u001a\u0002042\b\b\u0002\u0010B\u001a\u0002042\b\b\u0002\u0010C\u001a\u0002042\b\b\u0002\u0010D\u001a\u0002042\b\b\u0002\u0010E\u001a\u0002042\b\b\u0002\u0010F\u001a\u0002042\b\b\u0002\u0010G\u001a\u0002042\b\b\u0002\u0010H\u001a\u0002042\b\b\u0002\u0010I\u001a\u0002042\b\b\u0002\u0010J\u001a\u0002042\b\b\u0002\u0010K\u001a\u0002042\b\b\u0002\u0010L\u001a\u0002042\b\b\u0002\u0010M\u001a\u0002042\b\b\u0002\u0010N\u001a\u0002042\b\b\u0002\u0010O\u001a\u0002042\b\b\u0002\u0010P\u001a\u0002042\b\b\u0002\u0010Q\u001a\u0002042\b\b\u0002\u0010R\u001a\u0002042\b\b\u0002\u0010S\u001a\u0002042\b\b\u0002\u0010T\u001a\u0002042\b\b\u0002\u0010U\u001a\u0002042\b\b\u0002\u0010V\u001a\u0002042\b\b\u0002\u0010W\u001a\u0002042\b\b\u0002\u0010X\u001a\u0002042\b\b\u0002\u0010Y\u001a\u0002042\b\b\u0002\u0010Z\u001a\u0002042\b\b\u0002\u0010[\u001a\u0002042\b\b\u0002\u0010\\\u001a\u0002042\b\b\u0002\u0010]\u001a\u0002042\b\b\u0002\u0010^\u001a\u0002042\b\b\u0002\u0010_\u001a\u000204H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b`\u0010aJ;\u0010/\u001a\u0002002\b\b\u0002\u0010b\u001a\u00020\u00042\b\b\u0002\u0010c\u001a\u00020\u00042\b\b\u0002\u0010d\u001a\u00020\u00042\b\b\u0002\u0010e\u001a\u00020\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bf\u0010gR\u001c\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u001c\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u001c\u0010\f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u0011\u0010\u000e\u001a\u00020\u000f8G¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006h"}, d2 = {"Landroidx/compose/material3/OutlinedTextFieldDefaults;", "", "()V", "FocusedBorderThickness", "Landroidx/compose/ui/unit/Dp;", "getFocusedBorderThickness-D9Ej5fM", "()F", "F", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "ContainerBox", "", "enabled", "", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "colors", "Landroidx/compose/material3/TextFieldColors;", "focusedBorderThickness", "unfocusedBorderThickness", "ContainerBox-nbWgWpA", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "DecorationBox", "value", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "singleLine", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "container", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "focusedTextColor", "Landroidx/compose/ui/graphics/Color;", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedSupportingTextColor", "unfocusedSupportingTextColor", "disabledSupportingTextColor", "errorSupportingTextColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "colors-0hiis_0", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIIII)Landroidx/compose/material3/TextFieldColors;", "start", "top", "end", "bottom", "contentPadding-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OutlinedTextFieldDefaults {
    public static final int $stable = 0;
    public static final OutlinedTextFieldDefaults INSTANCE = new OutlinedTextFieldDefaults();
    private static final float MinHeight = Dp.m5274constructorimpl(56);
    private static final float MinWidth = Dp.m5274constructorimpl(280);
    private static final float UnfocusedBorderThickness = Dp.m5274constructorimpl(1);
    private static final float FocusedBorderThickness = Dp.m5274constructorimpl(2);

    private OutlinedTextFieldDefaults() {
    }

    public final Shape getShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(-1066756961);
        ComposerKt.sourceInformation($composer, "C1347@74442L9:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1066756961, $changed, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.<get-shape> (TextFieldDefaults.kt:1347)");
        }
        Shape shape = ShapesKt.toShape(OutlinedTextFieldTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return shape;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1652getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1653getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m1654getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m1651getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* JADX INFO: renamed from: ContainerBox-nbWgWpA, reason: not valid java name */
    public final void m1648ContainerBoxnbWgWpA(final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, Shape shape, float focusedBorderThickness, float unfocusedBorderThickness, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        float focusedBorderThickness2;
        float f;
        Shape shape3;
        Shape shape4;
        float focusedBorderThickness3;
        float unfocusedBorderThickness2;
        int $dirty;
        float unfocusedBorderThickness3;
        float unfocusedBorderThickness4;
        Shape shape5;
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(colors, "colors");
        Composer $composer2 = $composer.startRestartGroup(1461761386);
        ComposerKt.sourceInformation($composer2, "C(ContainerBox)P(1,4,3!1,5,2:c#ui.unit.Dp,6:c#ui.unit.Dp)1394@76327L9,1398@76498L203,1410@76843L51,1406@76710L199:TextFieldDefaults.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(enabled) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changed(isError) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer2.changed(interactionSource) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i2 = $composer2.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i2;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i2;
        } else {
            shape2 = shape;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                focusedBorderThickness2 = focusedBorderThickness;
                int i3 = $composer2.changed(focusedBorderThickness2) ? 131072 : 65536;
                $dirty2 |= i3;
            } else {
                focusedBorderThickness2 = focusedBorderThickness;
            }
            $dirty2 |= i3;
        } else {
            focusedBorderThickness2 = focusedBorderThickness;
        }
        if ((3670016 & $changed) == 0) {
            if ((i & 64) == 0) {
                f = unfocusedBorderThickness;
                int i4 = $composer2.changed(f) ? 1048576 : 524288;
                $dirty2 |= i4;
            } else {
                f = unfocusedBorderThickness;
            }
            $dirty2 |= i4;
        } else {
            f = unfocusedBorderThickness;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if ((29360128 & $changed) == 0) {
            $dirty2 |= $composer2.changed(this) ? 8388608 : 4194304;
        }
        if ((23967451 & $dirty2) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            shape5 = shape2;
            unfocusedBorderThickness4 = focusedBorderThickness2;
            unfocusedBorderThickness3 = f;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if ((i & 16) != 0) {
                    shape3 = ShapesKt.toShape(OutlinedTextFieldTokens.INSTANCE.getContainerShape(), $composer2, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    focusedBorderThickness2 = FocusedBorderThickness;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    shape4 = shape3;
                    unfocusedBorderThickness2 = UnfocusedBorderThickness;
                    focusedBorderThickness3 = focusedBorderThickness2;
                } else {
                    shape4 = shape3;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
                    $dirty = $dirty2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                shape4 = shape2;
                focusedBorderThickness3 = focusedBorderThickness2;
                unfocusedBorderThickness2 = f;
                $dirty = $dirty2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1461761386, $dirty, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.ContainerBox (TextFieldDefaults.kt:1389)");
            }
            int $dirty3 = $dirty;
            Shape shape6 = shape4;
            State borderStroke = TextFieldDefaultsKt.m1873animateBorderStrokeAsStateNuRrP5Q(enabled, isError, interactionSource, colors, focusedBorderThickness3, unfocusedBorderThickness2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752));
            BoxKt.Box(BackgroundKt.m159backgroundbw27NRU(BorderKt.border(Modifier.INSTANCE, (BorderStroke) borderStroke.getValue(), shape6), colors.containerColor$material3_release(enabled, isError, interactionSource, $composer2, ($dirty3 & 14) | ($dirty3 & 112) | ($dirty3 & 896) | ($dirty3 & 7168)).getValue().m2981unboximpl(), shape6), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            unfocusedBorderThickness3 = unfocusedBorderThickness2;
            unfocusedBorderThickness4 = focusedBorderThickness3;
            shape5 = shape6;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Shape shape7 = shape5;
        final float f2 = unfocusedBorderThickness4;
        final float f3 = unfocusedBorderThickness3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$ContainerBox$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i5) {
                this.$tmp0_rcvr.m1648ContainerBoxnbWgWpA(enabled, isError, interactionSource, colors, shape7, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: renamed from: contentPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1647contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults outlinedTextFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return outlinedTextFieldDefaults.m1650contentPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: contentPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1650contentPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m483PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: colors-0hiis_0, reason: not valid java name */
    public final TextFieldColors m1649colors0hiis_0(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedBorderColor, long unfocusedBorderColor, long disabledBorderColor, long errorBorderColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int $changed4, int i, int i2) {
        long disabledTextColor2;
        SelectionColors selectionColors2;
        long disabledBorderColor2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long disabledLabelColor2;
        long disabledPlaceholderColor2;
        long disabledSupportingTextColor2;
        long disabledPrefixColor2;
        long disabledSuffixColor2;
        $composer.startReplaceableGroup(1767617725);
        ComposerKt.sourceInformation($composer, "C(colors)P(30:c#ui.graphics.Color,41:c#ui.graphics.Color,9:c#ui.graphics.Color,20:c#ui.graphics.Color,23:c#ui.graphics.Color,34:c#ui.graphics.Color,2:c#ui.graphics.Color,12:c#ui.graphics.Color,0:c#ui.graphics.Color,13:c#ui.graphics.Color,32,22:c#ui.graphics.Color,33:c#ui.graphics.Color,1:c#ui.graphics.Color,11:c#ui.graphics.Color,25:c#ui.graphics.Color,36:c#ui.graphics.Color,4:c#ui.graphics.Color,15:c#ui.graphics.Color,31:c#ui.graphics.Color,42:c#ui.graphics.Color,10:c#ui.graphics.Color,21:c#ui.graphics.Color,24:c#ui.graphics.Color,35:c#ui.graphics.Color,3:c#ui.graphics.Color,14:c#ui.graphics.Color,26:c#ui.graphics.Color,37:c#ui.graphics.Color,5:c#ui.graphics.Color,16:c#ui.graphics.Color,29:c#ui.graphics.Color,40:c#ui.graphics.Color,8:c#ui.graphics.Color,19:c#ui.graphics.Color,27:c#ui.graphics.Color,38:c#ui.graphics.Color,6:c#ui.graphics.Color,17:c#ui.graphics.Color,28:c#ui.graphics.Color,39:c#ui.graphics.Color,7:c#ui.graphics.Color,18:c#ui.graphics.Color)1479@81602L9,1480@81684L9,1481@81773L9,1483@81928L9,1488@82236L9,1489@82326L9,1490@82409L7,1491@82496L9,1492@82582L9,1493@82675L9,1495@82836L9,1496@82934L9,1497@83029L9,1498@83131L9,1500@83305L9,1501@83405L9,1502@83502L9,1504@83619L9,1505@83783L9,1506@83869L9,1507@83952L9,1508@84042L9,1510@84198L9,1511@84296L9,1512@84396L9,1513@84492L9,1515@84660L9,1516@84760L9,1517@84857L9,1519@84974L9,1520@85136L9,1521@85224L9,1522@85314L9,1523@85403L9,1525@85561L9,1526@85649L9,1527@85739L9,1528@85828L9,1530@85986L9:TextFieldDefaults.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusInputColor(), $composer, 6) : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputColor(), $composer, 6) : unfocusedTextColor;
        if ((i & 4) != 0) {
            long color = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledTextColor2 = Color.m2969copywmQWz5c(color, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        long errorTextColor2 = (i & 8) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorInputColor(), $composer, 6) : errorTextColor;
        long focusedContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : focusedContainerColor;
        long unfocusedContainerColor2 = (i & 32) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : unfocusedContainerColor;
        long disabledContainerColor2 = (i & 64) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : disabledContainerColor;
        long errorContainerColor2 = (i & 128) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : errorContainerColor;
        long cursorColor2 = (i & 256) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getCaretColor(), $composer, 6) : cursorColor;
        long errorCursorColor2 = (i & 512) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorFocusCaretColor(), $composer, 6) : errorCursorColor;
        if ((i & 1024) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) objConsume;
        } else {
            selectionColors2 = selectionColors;
        }
        long focusedBorderColor2 = (i & 2048) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusOutlineColor(), $composer, 6) : focusedBorderColor;
        long unfocusedBorderColor2 = (i & 4096) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getOutlineColor(), $composer, 6) : unfocusedBorderColor;
        if ((i & 8192) != 0) {
            long color2 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledOutlineColor(), $composer, 6);
            disabledBorderColor2 = Color.m2969copywmQWz5c(color2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color2) : 0.12f, (14 & 2) != 0 ? Color.m2977getRedimpl(color2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color2) : 0.0f);
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        long errorBorderColor2 = (i & 16384) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorOutlineColor(), $composer, 6) : errorBorderColor;
        long focusedLeadingIconColor2 = (32768 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusLeadingIconColor(), $composer, 6) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (65536 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getLeadingIconColor(), $composer, 6) : unfocusedLeadingIconColor;
        if ((131072 & i) != 0) {
            long color3 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), $composer, 6);
            disabledLeadingIconColor2 = Color.m2969copywmQWz5c(color3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color3) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color3) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (262144 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorLeadingIconColor(), $composer, 6) : errorLeadingIconColor;
        long focusedTrailingIconColor2 = (524288 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusTrailingIconColor(), $composer, 6) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (1048576 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getTrailingIconColor(), $composer, 6) : unfocusedTrailingIconColor;
        if ((2097152 & i) != 0) {
            long color4 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), $composer, 6);
            disabledTrailingIconColor2 = Color.m2969copywmQWz5c(color4, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color4) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color4) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color4) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color4) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (4194304 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorTrailingIconColor(), $composer, 6) : errorTrailingIconColor;
        long focusedLabelColor2 = (8388608 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusLabelColor(), $composer, 6) : focusedLabelColor;
        long unfocusedLabelColor2 = (16777216 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getLabelColor(), $composer, 6) : unfocusedLabelColor;
        if ((33554432 & i) != 0) {
            long color5 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledLabelColor(), $composer, 6);
            disabledLabelColor2 = Color.m2969copywmQWz5c(color5, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color5) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color5) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color5) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color5) : 0.0f);
        } else {
            disabledLabelColor2 = disabledLabelColor;
        }
        long errorLabelColor2 = (67108864 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorLabelColor(), $composer, 6) : errorLabelColor;
        long focusedPlaceholderColor2 = (134217728 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (268435456 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : unfocusedPlaceholderColor;
        if ((536870912 & i) != 0) {
            long color6 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledPlaceholderColor2 = Color.m2969copywmQWz5c(color6, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color6) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color6) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color6) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color6) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        long errorPlaceholderColor2 = (i & 1073741824) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : errorPlaceholderColor;
        long focusedSupportingTextColor2 = (i2 & 1) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusSupportingColor(), $composer, 6) : focusedSupportingTextColor;
        long unfocusedSupportingTextColor2 = (i2 & 2) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getSupportingColor(), $composer, 6) : unfocusedSupportingTextColor;
        if ((i2 & 4) != 0) {
            long color7 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledSupportingColor(), $composer, 6);
            disabledSupportingTextColor2 = Color.m2969copywmQWz5c(color7, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color7) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color7) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color7) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color7) : 0.0f);
        } else {
            disabledSupportingTextColor2 = disabledSupportingTextColor;
        }
        long errorSupportingTextColor2 = (i2 & 8) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorSupportingColor(), $composer, 6) : errorSupportingTextColor;
        long focusedPrefixColor2 = (i2 & 16) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : focusedPrefixColor;
        long unfocusedPrefixColor2 = (i2 & 32) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : unfocusedPrefixColor;
        if ((i2 & 64) != 0) {
            long color8 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6);
            disabledPrefixColor2 = Color.m2969copywmQWz5c(color8, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color8) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color8) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color8) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color8) : 0.0f);
        } else {
            disabledPrefixColor2 = disabledPrefixColor;
        }
        long errorPrefixColor2 = (i2 & 128) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : errorPrefixColor;
        long focusedSuffixColor2 = (i2 & 256) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 512) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : unfocusedSuffixColor;
        if ((i2 & 1024) != 0) {
            long color9 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6);
            disabledSuffixColor2 = Color.m2969copywmQWz5c(color9, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color9) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color9) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color9) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color9) : 0.0f);
        } else {
            disabledSuffixColor2 = disabledSuffixColor;
        }
        long errorSuffixColor2 = (i2 & 2048) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1767617725, $changed, $changed1, "androidx.compose.material3.OutlinedTextFieldDefaults.colors (TextFieldDefaults.kt:1478)");
        }
        TextFieldColors textFieldColors = new TextFieldColors(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, focusedContainerColor2, unfocusedContainerColor2, disabledContainerColor2, errorContainerColor2, cursorColor2, errorCursorColor2, selectionColors2, focusedBorderColor2, unfocusedBorderColor2, disabledBorderColor2, errorBorderColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, errorPlaceholderColor2, focusedSupportingTextColor2, unfocusedSupportingTextColor2, disabledSupportingTextColor2, errorSupportingTextColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return textFieldColors;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0156  */
    /* JADX WARN: Code duplicated, block: B:102:0x015c  */
    /* JADX WARN: Code duplicated, block: B:104:0x0164  */
    /* JADX WARN: Code duplicated, block: B:105:0x0167  */
    /* JADX WARN: Code duplicated, block: B:107:0x016c  */
    /* JADX WARN: Code duplicated, block: B:110:0x0172  */
    /* JADX WARN: Code duplicated, block: B:111:0x0177  */
    /* JADX WARN: Code duplicated, block: B:113:0x017b  */
    /* JADX WARN: Code duplicated, block: B:115:0x0183  */
    /* JADX WARN: Code duplicated, block: B:116:0x0186  */
    /* JADX WARN: Code duplicated, block: B:118:0x018b  */
    /* JADX WARN: Code duplicated, block: B:121:0x0191  */
    /* JADX WARN: Code duplicated, block: B:122:0x0196  */
    /* JADX WARN: Code duplicated, block: B:124:0x019a  */
    /* JADX WARN: Code duplicated, block: B:126:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:127:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:129:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:132:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:133:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:135:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:137:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:139:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:142:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:143:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:145:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:148:0x01de  */
    /* JADX WARN: Code duplicated, block: B:150:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:153:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:160:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:163:0x0203  */
    /* JADX WARN: Code duplicated, block: B:171:0x021c  */
    /* JADX WARN: Code duplicated, block: B:174:0x0224  */
    /* JADX WARN: Code duplicated, block: B:175:0x022b  */
    /* JADX WARN: Code duplicated, block: B:177:0x022f  */
    /* JADX WARN: Code duplicated, block: B:179:0x0237  */
    /* JADX WARN: Code duplicated, block: B:180:0x023a  */
    /* JADX WARN: Code duplicated, block: B:182:0x023f  */
    /* JADX WARN: Code duplicated, block: B:185:0x0247  */
    /* JADX WARN: Code duplicated, block: B:186:0x024e  */
    /* JADX WARN: Code duplicated, block: B:188:0x0252  */
    /* JADX WARN: Code duplicated, block: B:190:0x025a  */
    /* JADX WARN: Code duplicated, block: B:191:0x025d  */
    /* JADX WARN: Code duplicated, block: B:193:0x0262  */
    /* JADX WARN: Code duplicated, block: B:202:0x029f  */
    /* JADX WARN: Code duplicated, block: B:204:0x02a6  */
    /* JADX WARN: Code duplicated, block: B:214:0x02db A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:215:0x02dd  */
    /* JADX WARN: Code duplicated, block: B:216:0x02df  */
    /* JADX WARN: Code duplicated, block: B:218:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:219:0x02e5  */
    /* JADX WARN: Code duplicated, block: B:221:0x02e9  */
    /* JADX WARN: Code duplicated, block: B:222:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:224:0x02ef  */
    /* JADX WARN: Code duplicated, block: B:225:0x02f1  */
    /* JADX WARN: Code duplicated, block: B:227:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:228:0x02f7  */
    /* JADX WARN: Code duplicated, block: B:230:0x02fb  */
    /* JADX WARN: Code duplicated, block: B:231:0x02fd  */
    /* JADX WARN: Code duplicated, block: B:233:0x0301  */
    /* JADX WARN: Code duplicated, block: B:234:0x0303  */
    /* JADX WARN: Code duplicated, block: B:236:0x0307  */
    /* JADX WARN: Code duplicated, block: B:237:0x0309  */
    /* JADX WARN: Code duplicated, block: B:240:0x030f  */
    /* JADX WARN: Code duplicated, block: B:241:0x0386  */
    /* JADX WARN: Code duplicated, block: B:244:0x038f  */
    /* JADX WARN: Code duplicated, block: B:245:0x03b3  */
    /* JADX WARN: Code duplicated, block: B:247:0x03b7  */
    /* JADX WARN: Code duplicated, block: B:248:0x03f3  */
    /* JADX WARN: Code duplicated, block: B:251:0x041a  */
    /* JADX WARN: Code duplicated, block: B:254:0x04c0  */
    /* JADX WARN: Code duplicated, block: B:258:0x04ca  */
    /* JADX WARN: Code duplicated, block: B:260:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:71:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:72:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:74:0x0103  */
    /* JADX WARN: Code duplicated, block: B:77:0x010b  */
    /* JADX WARN: Code duplicated, block: B:78:0x0112  */
    /* JADX WARN: Code duplicated, block: B:80:0x0116  */
    /* JADX WARN: Code duplicated, block: B:82:0x011e  */
    /* JADX WARN: Code duplicated, block: B:83:0x0121  */
    /* JADX WARN: Code duplicated, block: B:85:0x0126  */
    /* JADX WARN: Code duplicated, block: B:88:0x012c  */
    /* JADX WARN: Code duplicated, block: B:89:0x0133  */
    /* JADX WARN: Code duplicated, block: B:91:0x0139  */
    /* JADX WARN: Code duplicated, block: B:93:0x0141  */
    /* JADX WARN: Code duplicated, block: B:94:0x0144  */
    /* JADX WARN: Code duplicated, block: B:96:0x0149  */
    /* JADX WARN: Code duplicated, block: B:99:0x014f  */
    public final void DecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> innerTextField, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function9, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        boolean isError2;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Function2<? super Composer, ? super Integer, Unit> function16;
        TextFieldColors colors2;
        PaddingValues contentPadding2;
        Function2<? super Composer, ? super Integer, Unit> function17;
        Function2<? super Composer, ? super Integer, Unit> function2ComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Function2<? super Composer, ? super Integer, Unit> function22;
        boolean isError3;
        Function2<? super Composer, ? super Integer, Unit> function23;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        int $dirty1;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
        Intrinsics.checkNotNullParameter(visualTransformation, "visualTransformation");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Composer $composer3 = $composer.startRestartGroup(-350442135);
        ComposerKt.sourceInformation($composer3, "C(DecorationBox)P(15,4,3,11,16,5,6,7,9,8,14,10,12,13!1,2)1652@93520L8,1663@93808L709:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer3.changedInstance(innerTextField) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer3.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(singleLine) ? 2048 : 1024;
        }
        int i20 = 8192;
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
                i2 = $composer3.changed(interactionSource) ? 131072 : 65536;
            }
            i3 = i & 64;
            if (i3 != 0) {
                $dirty |= 1572864;
            } else if (($changed & 3670016) != 0) {
                if ($composer3.changed(isError)) {
                    i4 = 1048576;
                } else {
                    i4 = 524288;
                }
                $dirty |= i4;
            }
            i5 = i & 128;
            if (i5 != 0) {
                $dirty |= 12582912;
            } else if (($changed & 29360128) != 0) {
                if ($composer3.changedInstance(function2)) {
                    i6 = 8388608;
                } else {
                    i6 = 4194304;
                }
                $dirty |= i6;
            }
            i7 = i & 256;
            if (i7 != 0) {
                $dirty |= 100663296;
            } else if (($changed & 234881024) != 0) {
                if ($composer3.changedInstance(function3)) {
                    i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i8 = 33554432;
                }
                $dirty |= i8;
            }
            i9 = i & 512;
            if (i9 != 0) {
                $dirty |= 805306368;
            } else if (($changed & 1879048192) != 0) {
                if ($composer3.changedInstance(function4)) {
                    i10 = 536870912;
                } else {
                    i10 = 268435456;
                }
                $dirty |= i10;
            }
            i11 = i & 1024;
            if (i11 != 0) {
                $dirty2 |= 6;
            } else if (($changed1 & 14) != 0) {
                if ($composer3.changedInstance(function5)) {
                    i12 = 4;
                } else {
                    i12 = 2;
                }
                $dirty2 |= i12;
            }
            i13 = i & 2048;
            if (i13 != 0) {
                $dirty2 |= 48;
            } else if (($changed1 & 112) != 0) {
                if ($composer3.changedInstance(function6)) {
                    i14 = 32;
                } else {
                    i14 = 16;
                }
                $dirty2 |= i14;
            }
            i15 = i & 4096;
            if (i15 != 0) {
                $dirty2 |= 384;
            } else if (($changed1 & 896) != 0) {
                $dirty2 |= $composer3.changedInstance(function7) ? 256 : 128;
            }
            i16 = i & 8192;
            if (i16 != 0) {
                $dirty2 |= 3072;
            } else if (($changed1 & 7168) != 0) {
                $dirty2 |= $composer3.changedInstance(function8) ? 2048 : 1024;
            }
            if (($changed1 & 57344) != 0) {
                if ((i & 16384) == 0 && $composer3.changed(colors)) {
                    i20 = 16384;
                }
                $dirty2 |= i20;
            }
            if (($changed1 & 458752) != 0) {
                $dirty2 |= ((i & 32768) == 0 || !$composer3.changed(contentPadding)) ? 65536 : 131072;
            }
            i17 = i & 65536;
            if (i17 != 0) {
                $dirty2 |= 1572864;
            } else if (($changed1 & 3670016) != 0) {
                if ($composer3.changedInstance(function9)) {
                    i18 = 1048576;
                } else {
                    i18 = 524288;
                }
                $dirty2 |= i18;
            }
            if ((i & 131072) != 0) {
                $dirty2 |= 12582912;
            } else if (($changed1 & 29360128) != 0) {
                if ($composer3.changed(this)) {
                    i19 = 8388608;
                } else {
                    i19 = 4194304;
                }
                $dirty2 |= i19;
            }
            if (($dirty & 1533916891) != 306783378 && (23967451 & $dirty2) == 4793490 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                isError3 = isError;
                function20 = function2;
                function22 = function3;
                function18 = function4;
                function17 = function5;
                function19 = function6;
                function21 = function7;
                function23 = function8;
                colors3 = colors;
                contentPadding3 = contentPadding;
                function2ComposableLambda = function9;
                $composer2 = $composer3;
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        isError2 = false;
                    } else {
                        isError2 = isError;
                    }
                    if (i5 != 0) {
                        function10 = null;
                    } else {
                        function10 = function2;
                    }
                    if (i7 != 0) {
                        function11 = null;
                    } else {
                        function11 = function3;
                    }
                    if (i9 != 0) {
                        function12 = null;
                    } else {
                        function12 = function4;
                    }
                    if (i11 != 0) {
                        function13 = null;
                    } else {
                        function13 = function5;
                    }
                    if (i13 != 0) {
                        function14 = null;
                    } else {
                        function14 = function6;
                    }
                    if (i15 != 0) {
                        function15 = null;
                    } else {
                        function15 = function7;
                    }
                    if (i16 != 0) {
                        function16 = null;
                    } else {
                        function16 = function8;
                    }
                    if ((i & 16384) != 0) {
                        colors2 = m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 12) & 7168, Integer.MAX_VALUE, 4095);
                        $dirty2 &= -57345;
                    } else {
                        colors2 = colors;
                    }
                    if ((i & 32768) != 0) {
                        contentPadding2 = m1647contentPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        $dirty2 &= -458753;
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i17 != 0) {
                        final boolean z = isError2;
                        final TextFieldColors textFieldColors = colors2;
                        final int i21 = $dirty;
                        final int i22 = $dirty2;
                        function17 = function13;
                        function18 = function12;
                        function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -1448570018, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer4, int $changed2) {
                                ComposerKt.sourceInformation($composer4, "C1655@93646L135:TextFieldDefaults.kt#uh7d8r");
                                if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1448570018, $changed2, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:1654)");
                                    }
                                    OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                    boolean z2 = enabled;
                                    boolean z3 = z;
                                    InteractionSource interactionSource2 = interactionSource;
                                    TextFieldColors textFieldColors2 = textFieldColors;
                                    int i23 = i21;
                                    outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z2, z3, interactionSource2, textFieldColors2, null, 0.0f, 0.0f, $composer4, ((i23 >> 9) & 896) | ((i23 >> 6) & 14) | 12582912 | ((i23 >> 15) & 112) | ((i22 >> 3) & 7168), 112);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        });
                        function19 = function14;
                        function20 = function10;
                        function21 = function15;
                        function22 = function11;
                        isError3 = isError2;
                        function23 = function16;
                        colors3 = colors2;
                        contentPadding3 = contentPadding2;
                        $dirty1 = $dirty2;
                    } else {
                        function17 = function13;
                        function2ComposableLambda = function9;
                        function18 = function12;
                        function19 = function14;
                        function20 = function10;
                        function21 = function15;
                        function22 = function11;
                        isError3 = isError2;
                        function23 = function16;
                        colors3 = colors2;
                        contentPadding3 = contentPadding2;
                        $dirty1 = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 16384) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((32768 & i) != 0) {
                        $dirty2 &= -458753;
                    }
                    isError3 = isError;
                    function20 = function2;
                    function22 = function3;
                    function18 = function4;
                    function17 = function5;
                    function19 = function6;
                    function21 = function7;
                    function23 = function8;
                    colors3 = colors;
                    contentPadding3 = contentPadding;
                    function2ComposableLambda = function9;
                    $dirty1 = $dirty2;
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-350442135, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox (TextFieldDefaults.kt:1637)");
                }
                int $dirty3 = $dirty;
                $composer2 = $composer3;
                TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, value, innerTextField, visualTransformation, function20, function22, function18, function17, function19, function21, function23, singleLine, enabled, isError3, interactionSource, contentPadding3, colors3, function2ComposableLambda, $composer2, (($dirty3 << 3) & 112) | 6 | (($dirty3 << 3) & 896) | (($dirty3 >> 3) & 7168) | (($dirty3 >> 9) & 57344) | (($dirty3 >> 9) & 458752) | (($dirty3 >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024) | (($dirty1 << 21) & 1879048192), (($dirty1 >> 9) & 14) | (($dirty3 >> 6) & 112) | ($dirty3 & 896) | (($dirty3 >> 9) & 7168) | (($dirty3 >> 3) & 57344) | ($dirty1 & 458752) | (($dirty1 << 6) & 3670016) | (($dirty1 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final boolean z2 = isError3;
            final Function2<? super Composer, ? super Integer, Unit> function24 = function20;
            final Function2<? super Composer, ? super Integer, Unit> function25 = function22;
            final Function2<? super Composer, ? super Integer, Unit> function26 = function18;
            final Function2<? super Composer, ? super Integer, Unit> function27 = function17;
            final Function2<? super Composer, ? super Integer, Unit> function28 = function19;
            final Function2<? super Composer, ? super Integer, Unit> function29 = function21;
            final Function2<? super Composer, ? super Integer, Unit> function30 = function23;
            final TextFieldColors textFieldColors2 = colors3;
            final PaddingValues paddingValues = contentPadding3;
            final Function2<? super Composer, ? super Integer, Unit> function31 = function2ComposableLambda;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.2
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

                public final void invoke(Composer composer, int i23) {
                    OutlinedTextFieldDefaults.this.DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z2, function24, function25, function26, function27, function28, function29, function30, textFieldColors2, paddingValues, function31, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty |= i2;
        i3 = i & 64;
        if (i3 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) != 0) {
            if ($composer3.changed(isError)) {
                i4 = 1048576;
            } else {
                i4 = 524288;
            }
            $dirty |= i4;
        }
        i5 = i & 128;
        if (i5 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 29360128) != 0) {
            if ($composer3.changedInstance(function2)) {
                i6 = 8388608;
            } else {
                i6 = 4194304;
            }
            $dirty |= i6;
        }
        i7 = i & 256;
        if (i7 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) != 0) {
            if ($composer3.changedInstance(function3)) {
                i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i8 = 33554432;
            }
            $dirty |= i8;
        }
        i9 = i & 512;
        if (i9 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) != 0) {
            if ($composer3.changedInstance(function4)) {
                i10 = 536870912;
            } else {
                i10 = 268435456;
            }
            $dirty |= i10;
        }
        i11 = i & 1024;
        if (i11 != 0) {
            $dirty2 |= 6;
        } else if (($changed1 & 14) != 0) {
            if ($composer3.changedInstance(function5)) {
                i12 = 4;
            } else {
                i12 = 2;
            }
            $dirty2 |= i12;
        }
        i13 = i & 2048;
        if (i13 != 0) {
            $dirty2 |= 48;
        } else if (($changed1 & 112) != 0) {
            if ($composer3.changedInstance(function6)) {
                i14 = 32;
            } else {
                i14 = 16;
            }
            $dirty2 |= i14;
        }
        i15 = i & 4096;
        if (i15 != 0) {
            $dirty2 |= 384;
        } else if (($changed1 & 896) != 0) {
            $dirty2 |= $composer3.changedInstance(function7) ? 256 : 128;
        }
        i16 = i & 8192;
        if (i16 != 0) {
            $dirty2 |= 3072;
        } else if (($changed1 & 7168) != 0) {
            $dirty2 |= $composer3.changedInstance(function8) ? 2048 : 1024;
        }
        if (($changed1 & 57344) != 0) {
            if ((i & 16384) == 0) {
                i20 = 16384;
            }
            $dirty2 |= i20;
        }
        if (($changed1 & 458752) != 0) {
            $dirty2 |= ((i & 32768) == 0 || !$composer3.changed(contentPadding)) ? 65536 : 131072;
        }
        i17 = i & 65536;
        if (i17 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed1 & 3670016) != 0) {
            if ($composer3.changedInstance(function9)) {
                i18 = 1048576;
            } else {
                i18 = 524288;
            }
            $dirty2 |= i18;
        }
        if ((i & 131072) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed1 & 29360128) != 0) {
            if ($composer3.changed(this)) {
                i19 = 8388608;
            } else {
                i19 = 4194304;
            }
            $dirty2 |= i19;
        }
        if (($dirty & 1533916891) != 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i16 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    colors2 = m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 12) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -57345;
                } else {
                    colors2 = colors;
                }
                if ((i & 32768) != 0) {
                    contentPadding2 = m1647contentPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -458753;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i17 != 0) {
                    final boolean z3 = isError2;
                    final TextFieldColors textFieldColors3 = colors2;
                    final int i23 = $dirty;
                    final int i24 = $dirty2;
                    function17 = function13;
                    function18 = function12;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -1448570018, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C1655@93646L135:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1448570018, $changed2, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:1654)");
                                }
                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                boolean z4 = enabled;
                                boolean z5 = z3;
                                InteractionSource interactionSource2 = interactionSource;
                                TextFieldColors textFieldColors4 = textFieldColors3;
                                int i25 = i23;
                                outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z4, z5, interactionSource2, textFieldColors4, null, 0.0f, 0.0f, $composer4, ((i25 >> 9) & 896) | ((i25 >> 6) & 14) | 12582912 | ((i25 >> 15) & 112) | ((i24 >> 3) & 7168), 112);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    });
                    function19 = function14;
                    function20 = function10;
                    function21 = function15;
                    function22 = function11;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                } else {
                    function17 = function13;
                    function2ComposableLambda = function9;
                    function18 = function12;
                    function19 = function14;
                    function20 = function10;
                    function21 = function15;
                    function22 = function11;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i16 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    colors2 = m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 12) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -57345;
                } else {
                    colors2 = colors;
                }
                if ((i & 32768) != 0) {
                    contentPadding2 = m1647contentPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -458753;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i17 != 0) {
                    final boolean z4 = isError2;
                    final TextFieldColors textFieldColors4 = colors2;
                    final int i25 = $dirty;
                    final int i26 = $dirty2;
                    function17 = function13;
                    function18 = function12;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -1448570018, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C1655@93646L135:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1448570018, $changed2, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:1654)");
                                }
                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                boolean z5 = enabled;
                                boolean z6 = z4;
                                InteractionSource interactionSource2 = interactionSource;
                                TextFieldColors textFieldColors5 = textFieldColors4;
                                int i27 = i25;
                                outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z5, z6, interactionSource2, textFieldColors5, null, 0.0f, 0.0f, $composer4, ((i27 >> 9) & 896) | ((i27 >> 6) & 14) | 12582912 | ((i27 >> 15) & 112) | ((i26 >> 3) & 7168), 112);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    });
                    function19 = function14;
                    function20 = function10;
                    function21 = function15;
                    function22 = function11;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                } else {
                    function17 = function13;
                    function2ComposableLambda = function9;
                    function18 = function12;
                    function19 = function14;
                    function20 = function10;
                    function21 = function15;
                    function22 = function11;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-350442135, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox (TextFieldDefaults.kt:1637)");
            }
            int $dirty4 = $dirty;
            $composer2 = $composer3;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, value, innerTextField, visualTransformation, function20, function22, function18, function17, function19, function21, function23, singleLine, enabled, isError3, interactionSource, contentPadding3, colors3, function2ComposableLambda, $composer2, (($dirty4 << 3) & 112) | 6 | (($dirty4 << 3) & 896) | (($dirty4 >> 3) & 7168) | (($dirty4 >> 9) & 57344) | (($dirty4 >> 9) & 458752) | (($dirty4 >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024) | (($dirty1 << 21) & 1879048192), (($dirty1 >> 9) & 14) | (($dirty4 >> 6) & 112) | ($dirty4 & 896) | (($dirty4 >> 9) & 7168) | (($dirty4 >> 3) & 57344) | ($dirty1 & 458752) | (($dirty1 << 6) & 3670016) | (($dirty1 << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i16 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    colors2 = m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 12) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -57345;
                } else {
                    colors2 = colors;
                }
                if ((i & 32768) != 0) {
                    contentPadding2 = m1647contentPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -458753;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i17 != 0) {
                    final boolean z5 = isError2;
                    final TextFieldColors textFieldColors5 = colors2;
                    final int i27 = $dirty;
                    final int i28 = $dirty2;
                    function17 = function13;
                    function18 = function12;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -1448570018, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C1655@93646L135:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1448570018, $changed2, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:1654)");
                                }
                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                boolean z6 = enabled;
                                boolean z7 = z5;
                                InteractionSource interactionSource2 = interactionSource;
                                TextFieldColors textFieldColors6 = textFieldColors5;
                                int i29 = i27;
                                outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z6, z7, interactionSource2, textFieldColors6, null, 0.0f, 0.0f, $composer4, ((i29 >> 9) & 896) | ((i29 >> 6) & 14) | 12582912 | ((i29 >> 15) & 112) | ((i28 >> 3) & 7168), 112);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    });
                    function19 = function14;
                    function20 = function10;
                    function21 = function15;
                    function22 = function11;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                } else {
                    function17 = function13;
                    function2ComposableLambda = function9;
                    function18 = function12;
                    function19 = function14;
                    function20 = function10;
                    function21 = function15;
                    function22 = function11;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i16 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    colors2 = m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 12) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -57345;
                } else {
                    colors2 = colors;
                }
                if ((i & 32768) != 0) {
                    contentPadding2 = m1647contentPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -458753;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i17 != 0) {
                    final boolean z6 = isError2;
                    final TextFieldColors textFieldColors6 = colors2;
                    final int i29 = $dirty;
                    final int i210 = $dirty2;
                    function17 = function13;
                    function18 = function12;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -1448570018, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C1655@93646L135:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1448570018, $changed2, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:1654)");
                                }
                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                boolean z7 = enabled;
                                boolean z8 = z6;
                                InteractionSource interactionSource2 = interactionSource;
                                TextFieldColors textFieldColors7 = textFieldColors6;
                                int i211 = i29;
                                outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z7, z8, interactionSource2, textFieldColors7, null, 0.0f, 0.0f, $composer4, ((i211 >> 9) & 896) | ((i211 >> 6) & 14) | 12582912 | ((i211 >> 15) & 112) | ((i210 >> 3) & 7168), 112);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    });
                    function19 = function14;
                    function20 = function10;
                    function21 = function15;
                    function22 = function11;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                } else {
                    function17 = function13;
                    function2ComposableLambda = function9;
                    function18 = function12;
                    function19 = function14;
                    function20 = function10;
                    function21 = function15;
                    function22 = function11;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-350442135, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox (TextFieldDefaults.kt:1637)");
            }
            int $dirty5 = $dirty;
            $composer2 = $composer3;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, value, innerTextField, visualTransformation, function20, function22, function18, function17, function19, function21, function23, singleLine, enabled, isError3, interactionSource, contentPadding3, colors3, function2ComposableLambda, $composer2, (($dirty5 << 3) & 112) | 6 | (($dirty5 << 3) & 896) | (($dirty5 >> 3) & 7168) | (($dirty5 >> 9) & 57344) | (($dirty5 >> 9) & 458752) | (($dirty5 >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024) | (($dirty1 << 21) & 1879048192), (($dirty1 >> 9) & 14) | (($dirty5 >> 6) & 112) | ($dirty5 & 896) | (($dirty5 >> 9) & 7168) | (($dirty5 >> 3) & 57344) | ($dirty1 & 458752) | (($dirty1 << 6) & 3670016) | (($dirty1 << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final boolean z7 = isError3;
        final Function2<? super Composer, ? super Integer, Unit> function210 = function20;
        final Function2<? super Composer, ? super Integer, Unit> function211 = function22;
        final Function2<? super Composer, ? super Integer, Unit> function212 = function18;
        final Function2<? super Composer, ? super Integer, Unit> function213 = function17;
        final Function2<? super Composer, ? super Integer, Unit> function214 = function19;
        final Function2<? super Composer, ? super Integer, Unit> function215 = function21;
        final Function2<? super Composer, ? super Integer, Unit> function32 = function23;
        final TextFieldColors textFieldColors7 = colors3;
        final PaddingValues paddingValues2 = contentPadding3;
        final Function2<? super Composer, ? super Integer, Unit> function33 = function2ComposableLambda;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.2
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

            public final void invoke(Composer composer, int i211) {
                OutlinedTextFieldDefaults.this.DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z7, function210, function211, function212, function213, function214, function215, function32, textFieldColors7, paddingValues2, function33, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }
}
