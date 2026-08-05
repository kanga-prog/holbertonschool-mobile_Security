package androidx.compose.material;

import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JS\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\u00102\b\b\u0002\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\u0006H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b$\u0010%J×\u0001\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0015\b\u0002\u0010/\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00100\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u00103\u001a\u0002042\u0013\b\u0002\u00105\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+H\u0007¢\u0006\u0002\u00106JÂ\u0001\u00107\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0015\b\u0002\u0010/\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00100\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u00103\u001a\u000204H\u0007¢\u0006\u0002\u00108Jç\u0001\u00109\u001a\u00020 2\b\b\u0002\u0010:\u001a\u00020;2\b\b\u0002\u0010<\u001a\u00020;2\b\b\u0002\u0010=\u001a\u00020;2\b\b\u0002\u0010>\u001a\u00020;2\b\b\u0002\u0010?\u001a\u00020;2\b\b\u0002\u0010@\u001a\u00020;2\b\b\u0002\u0010A\u001a\u00020;2\b\b\u0002\u0010B\u001a\u00020;2\b\b\u0002\u0010C\u001a\u00020;2\b\b\u0002\u0010D\u001a\u00020;2\b\b\u0002\u0010E\u001a\u00020;2\b\b\u0002\u0010F\u001a\u00020;2\b\b\u0002\u0010G\u001a\u00020;2\b\b\u0002\u0010H\u001a\u00020;2\b\b\u0002\u0010I\u001a\u00020;2\b\b\u0002\u0010J\u001a\u00020;2\b\b\u0002\u0010K\u001a\u00020;2\b\b\u0002\u0010L\u001a\u00020;2\b\b\u0002\u0010M\u001a\u00020;2\b\b\u0002\u0010N\u001a\u00020;2\b\b\u0002\u0010O\u001a\u00020;H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bP\u0010QJ=\u0010R\u001a\u0002042\b\b\u0002\u0010S\u001a\u00020\u00062\b\b\u0002\u0010T\u001a\u00020\u00062\b\b\u0002\u0010U\u001a\u00020\u00062\b\b\u0002\u0010V\u001a\u00020\u0006H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bW\u0010XJç\u0001\u0010Y\u001a\u00020 2\b\b\u0002\u0010:\u001a\u00020;2\b\b\u0002\u0010<\u001a\u00020;2\b\b\u0002\u0010=\u001a\u00020;2\b\b\u0002\u0010>\u001a\u00020;2\b\b\u0002\u0010?\u001a\u00020;2\b\b\u0002\u0010Z\u001a\u00020;2\b\b\u0002\u0010[\u001a\u00020;2\b\b\u0002\u0010\\\u001a\u00020;2\b\b\u0002\u0010]\u001a\u00020;2\b\b\u0002\u0010D\u001a\u00020;2\b\b\u0002\u0010E\u001a\u00020;2\b\b\u0002\u0010F\u001a\u00020;2\b\b\u0002\u0010G\u001a\u00020;2\b\b\u0002\u0010H\u001a\u00020;2\b\b\u0002\u0010I\u001a\u00020;2\b\b\u0002\u0010J\u001a\u00020;2\b\b\u0002\u0010K\u001a\u00020;2\b\b\u0002\u0010L\u001a\u00020;2\b\b\u0002\u0010M\u001a\u00020;2\b\b\u0002\u0010N\u001a\u00020;2\b\b\u0002\u0010O\u001a\u00020;H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b^\u0010QJ=\u0010_\u001a\u0002042\b\b\u0002\u0010S\u001a\u00020\u00062\b\b\u0002\u0010U\u001a\u00020\u00062\b\b\u0002\u0010T\u001a\u00020\u00062\b\b\u0002\u0010V\u001a\u00020\u0006H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b`\u0010XJ=\u0010a\u001a\u0002042\b\b\u0002\u0010S\u001a\u00020\u00062\b\b\u0002\u0010T\u001a\u00020\u00062\b\b\u0002\u0010U\u001a\u00020\u00062\b\b\u0002\u0010V\u001a\u00020\u0006H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bb\u0010XJM\u0010c\u001a\u00020d*\u00020d2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010e\u001a\u00020\u00062\b\b\u0002\u0010f\u001a\u00020\u0006H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bg\u0010hR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\f\u0010\bR\u001c\u0010\r\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u001c\u0010\u0015\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0016\u0010\bR\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006i"}, d2 = {"Landroidx/compose/material/TextFieldDefaults;", "", "()V", "BackgroundOpacity", "", "FocusedBorderThickness", "Landroidx/compose/ui/unit/Dp;", "getFocusedBorderThickness-D9Ej5fM", "()F", "F", "IconOpacity", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "OutlinedTextFieldShape", "Landroidx/compose/ui/graphics/Shape;", "getOutlinedTextFieldShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "TextFieldShape", "getTextFieldShape", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM", "UnfocusedIndicatorLineOpacity", "BorderBox", "", "enabled", "", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "colors", "Landroidx/compose/material/TextFieldColors;", "shape", "focusedBorderThickness", "unfocusedBorderThickness", "BorderBox-nbWgWpA", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "OutlinedTextFieldDecorationBox", "value", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "singleLine", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", OutlinedTextFieldKt.BorderId, "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "TextFieldDecorationBox", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;III)V", "outlinedTextFieldColors", "textColor", "Landroidx/compose/ui/graphics/Color;", "disabledTextColor", "backgroundColor", "cursorColor", "errorCursorColor", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "leadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "trailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "placeholderColor", "disabledPlaceholderColor", "outlinedTextFieldColors-dx8h9Zs", "(JJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIII)Landroidx/compose/material/TextFieldColors;", "outlinedTextFieldPadding", "start", "top", "end", "bottom", "outlinedTextFieldPadding-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "textFieldColors", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "textFieldColors-dx8h9Zs", "textFieldWithLabelPadding", "textFieldWithLabelPadding-a9UjIt4", "textFieldWithoutLabelPadding", "textFieldWithoutLabelPadding-a9UjIt4", "indicatorLine", "Landroidx/compose/ui/Modifier;", "focusedIndicatorLineThickness", "unfocusedIndicatorLineThickness", "indicatorLine-gv0btCI", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material/TextFieldColors;FF)Landroidx/compose/ui/Modifier;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldDefaults {
    public static final int $stable = 0;
    public static final float BackgroundOpacity = 0.12f;
    public static final float IconOpacity = 0.54f;
    public static final float UnfocusedIndicatorLineOpacity = 0.42f;
    public static final TextFieldDefaults INSTANCE = new TextFieldDefaults();
    private static final float MinHeight = Dp.m5274constructorimpl(56);
    private static final float MinWidth = Dp.m5274constructorimpl(280);
    private static final float UnfocusedBorderThickness = Dp.m5274constructorimpl(1);
    private static final float FocusedBorderThickness = Dp.m5274constructorimpl(2);

    private TextFieldDefaults() {
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1249getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1250getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    public final Shape getTextFieldShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1117199624, "C215@7733L6:TextFieldDefaults.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1117199624, $changed, -1, "androidx.compose.material.TextFieldDefaults.<get-TextFieldShape> (TextFieldDefaults.kt:215)");
        }
        CornerBasedShape cornerBasedShapeCopy$default = CornerBasedShape.copy$default(MaterialTheme.INSTANCE.getShapes($composer, 6).getSmall(), null, null, CornerSizeKt.getZeroCornerSize(), CornerSizeKt.getZeroCornerSize(), 3, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cornerBasedShapeCopy$default;
    }

    public final Shape getOutlinedTextFieldShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1899109048, "C224@8035L6:TextFieldDefaults.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1899109048, $changed, -1, "androidx.compose.material.TextFieldDefaults.<get-OutlinedTextFieldShape> (TextFieldDefaults.kt:224)");
        }
        CornerBasedShape small = MaterialTheme.INSTANCE.getShapes($composer, 6).getSmall();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return small;
    }

    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m1251getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m1248getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m1243indicatorLinegv0btCI$default(TextFieldDefaults textFieldDefaults, Modifier modifier, boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, float f, float f2, int i, Object obj) {
        float f3;
        float f4;
        if ((i & 16) == 0) {
            f3 = f;
        } else {
            f3 = FocusedBorderThickness;
        }
        if ((i & 32) == 0) {
            f4 = f2;
        } else {
            f4 = UnfocusedBorderThickness;
        }
        return textFieldDefaults.m1252indicatorLinegv0btCI(modifier, z, z2, interactionSource, textFieldColors, f3, f4);
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI, reason: not valid java name */
    public final Modifier m1252indicatorLinegv0btCI(Modifier indicatorLine, final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, final float focusedIndicatorLineThickness, final float unfocusedIndicatorLineThickness) {
        Intrinsics.checkNotNullParameter(indicatorLine, "$this$indicatorLine");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(colors, "colors");
        return ComposedModifierKt.composed(indicatorLine, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.TextFieldDefaults$indicatorLine-gv0btCI$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                Intrinsics.checkNotNullParameter($this$null, "$this$null");
                $this$null.setName("indicatorLine");
                $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
                $this$null.getProperties().set("isError", Boolean.valueOf(isError));
                $this$null.getProperties().set("interactionSource", interactionSource);
                $this$null.getProperties().set("colors", colors);
                $this$null.getProperties().set("focusedIndicatorLineThickness", Dp.m5272boximpl(focusedIndicatorLineThickness));
                $this$null.getProperties().set("unfocusedIndicatorLineThickness", Dp.m5272boximpl(unfocusedIndicatorLineThickness));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material.TextFieldDefaults$indicatorLine$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer $composer, int $changed) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                $composer.startReplaceableGroup(1398930845);
                ComposerKt.sourceInformation($composer, "C281@10437L217:TextFieldDefaults.kt#jmzs0o");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1398930845, $changed, -1, "androidx.compose.material.TextFieldDefaults.indicatorLine.<anonymous> (TextFieldDefaults.kt:280)");
                }
                State stroke = TextFieldDefaultsKt.m1259animateBorderStrokeAsStateNuRrP5Q(enabled, isError, interactionSource, colors, focusedIndicatorLineThickness, unfocusedIndicatorLineThickness, $composer, 0);
                Modifier modifierDrawIndicatorLine = TextFieldKt.drawIndicatorLine(Modifier.INSTANCE, (BorderStroke) stroke.getValue());
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceableGroup();
                return modifierDrawIndicatorLine;
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:108:0x014c  */
    /* JADX WARN: Code duplicated, block: B:110:0x0150  */
    /* JADX WARN: Code duplicated, block: B:113:0x015e  */
    /* JADX WARN: Code duplicated, block: B:116:0x0167  */
    /* JADX WARN: Code duplicated, block: B:117:0x0171  */
    /* JADX WARN: Code duplicated, block: B:120:0x0180  */
    /* JADX WARN: Code duplicated, block: B:123:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:128:0x01df  */
    /* JADX WARN: Code duplicated, block: B:130:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x011a  */
    /* JADX WARN: Code duplicated, block: B:95:0x012a  */
    /* JADX INFO: renamed from: BorderBox-nbWgWpA, reason: not valid java name */
    public final void m1247BorderBoxnbWgWpA(final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, Shape shape, float focusedBorderThickness, float unfocusedBorderThickness, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        float focusedBorderThickness2;
        float f;
        int i2;
        Shape shape3;
        float focusedBorderThickness3;
        float unfocusedBorderThickness2;
        int $dirty;
        float unfocusedBorderThickness3;
        float unfocusedBorderThickness4;
        Shape shape4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(colors, "colors");
        Composer $composer2 = $composer.startRestartGroup(943754022);
        ComposerKt.sourceInformation($composer2, "C(BorderBox)P(1,4,3!1,5,2:c#ui.unit.Dp,6:c#ui.unit.Dp)314@11791L22,318@11975L203,326@12187L47:TextFieldDefaults.kt#jmzs0o");
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
                int i3 = $composer2.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i3;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i3;
        } else {
            shape2 = shape;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                focusedBorderThickness2 = focusedBorderThickness;
                int i4 = $composer2.changed(focusedBorderThickness2) ? 131072 : 65536;
                $dirty2 |= i4;
            } else {
                focusedBorderThickness2 = focusedBorderThickness;
            }
            $dirty2 |= i4;
        } else {
            focusedBorderThickness2 = focusedBorderThickness;
        }
        if ((3670016 & $changed) == 0) {
            if ((i & 64) == 0) {
                f = unfocusedBorderThickness;
                int i5 = $composer2.changed(f) ? 1048576 : 524288;
                $dirty2 |= i5;
            } else {
                f = unfocusedBorderThickness;
            }
            $dirty2 |= i5;
        } else {
            f = unfocusedBorderThickness;
        }
        if ((i & 128) == 0) {
            if ((29360128 & $changed) == 0) {
                i2 = $composer2.changed(this) ? 8388608 : 4194304;
            }
            if ((23967451 & $dirty2) == 4793490 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if ((i & 16) != 0) {
                        shape2 = getOutlinedTextFieldShape($composer2, ($dirty2 >> 21) & 14);
                        $dirty2 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        $dirty2 &= -458753;
                        focusedBorderThickness2 = FocusedBorderThickness;
                    }
                    if ((i & 64) != 0) {
                        $dirty = $dirty2 & (-3670017);
                        shape3 = shape2;
                        unfocusedBorderThickness2 = UnfocusedBorderThickness;
                        focusedBorderThickness3 = focusedBorderThickness2;
                    } else {
                        shape3 = shape2;
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
                    shape3 = shape2;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
                    $dirty = $dirty2;
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(943754022, $dirty, -1, "androidx.compose.material.TextFieldDefaults.BorderBox (TextFieldDefaults.kt:309)");
                }
                Shape shape5 = shape3;
                State borderStroke = TextFieldDefaultsKt.m1259animateBorderStrokeAsStateNuRrP5Q(enabled, isError, interactionSource, colors, focusedBorderThickness3, unfocusedBorderThickness2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752));
                BoxKt.Box(BorderKt.border(Modifier.INSTANCE, (BorderStroke) borderStroke.getValue(), shape5), $composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                unfocusedBorderThickness3 = unfocusedBorderThickness2;
                unfocusedBorderThickness4 = focusedBorderThickness3;
                shape4 = shape5;
            } else {
                $composer2.skipToGroupEnd();
                shape4 = shape2;
                unfocusedBorderThickness4 = focusedBorderThickness2;
                unfocusedBorderThickness3 = f;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Shape shape6 = shape4;
            final float f2 = unfocusedBorderThickness4;
            final float f3 = unfocusedBorderThickness3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults$BorderBox$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    this.$tmp0_rcvr.m1247BorderBoxnbWgWpA(enabled, isError, interactionSource, colors, shape6, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 12582912;
        $dirty2 |= i2;
        if ((23967451 & $dirty2) == 4793490) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if ((i & 16) != 0) {
                    shape2 = getOutlinedTextFieldShape($composer2, ($dirty2 >> 21) & 14);
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    focusedBorderThickness2 = FocusedBorderThickness;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    shape3 = shape2;
                    unfocusedBorderThickness2 = UnfocusedBorderThickness;
                    focusedBorderThickness3 = focusedBorderThickness2;
                } else {
                    shape3 = shape2;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
                    $dirty = $dirty2;
                }
            } else {
                if ((i & 16) != 0) {
                    shape2 = getOutlinedTextFieldShape($composer2, ($dirty2 >> 21) & 14);
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    focusedBorderThickness2 = FocusedBorderThickness;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    shape3 = shape2;
                    unfocusedBorderThickness2 = UnfocusedBorderThickness;
                    focusedBorderThickness3 = focusedBorderThickness2;
                } else {
                    shape3 = shape2;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(943754022, $dirty, -1, "androidx.compose.material.TextFieldDefaults.BorderBox (TextFieldDefaults.kt:309)");
            }
            Shape shape7 = shape3;
            State borderStroke2 = TextFieldDefaultsKt.m1259animateBorderStrokeAsStateNuRrP5Q(enabled, isError, interactionSource, colors, focusedBorderThickness3, unfocusedBorderThickness2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752));
            BoxKt.Box(BorderKt.border(Modifier.INSTANCE, (BorderStroke) borderStroke2.getValue(), shape7), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            unfocusedBorderThickness3 = unfocusedBorderThickness2;
            unfocusedBorderThickness4 = focusedBorderThickness3;
            shape4 = shape7;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if ((i & 16) != 0) {
                    shape2 = getOutlinedTextFieldShape($composer2, ($dirty2 >> 21) & 14);
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    focusedBorderThickness2 = FocusedBorderThickness;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    shape3 = shape2;
                    unfocusedBorderThickness2 = UnfocusedBorderThickness;
                    focusedBorderThickness3 = focusedBorderThickness2;
                } else {
                    shape3 = shape2;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
                    $dirty = $dirty2;
                }
            } else {
                if ((i & 16) != 0) {
                    shape2 = getOutlinedTextFieldShape($composer2, ($dirty2 >> 21) & 14);
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    focusedBorderThickness2 = FocusedBorderThickness;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    shape3 = shape2;
                    unfocusedBorderThickness2 = UnfocusedBorderThickness;
                    focusedBorderThickness3 = focusedBorderThickness2;
                } else {
                    shape3 = shape2;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(943754022, $dirty, -1, "androidx.compose.material.TextFieldDefaults.BorderBox (TextFieldDefaults.kt:309)");
            }
            Shape shape8 = shape3;
            State borderStroke3 = TextFieldDefaultsKt.m1259animateBorderStrokeAsStateNuRrP5Q(enabled, isError, interactionSource, colors, focusedBorderThickness3, unfocusedBorderThickness2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752));
            BoxKt.Box(BorderKt.border(Modifier.INSTANCE, (BorderStroke) borderStroke3.getValue(), shape8), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            unfocusedBorderThickness3 = unfocusedBorderThickness2;
            unfocusedBorderThickness4 = focusedBorderThickness3;
            shape4 = shape8;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Shape shape9 = shape4;
        final float f4 = unfocusedBorderThickness4;
        final float f5 = unfocusedBorderThickness3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults$BorderBox$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i6) {
                this.$tmp0_rcvr.m1247BorderBoxnbWgWpA(enabled, isError, interactionSource, colors, shape9, f4, f5, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1245textFieldWithLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getFirstBaselineOffset();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldBottomPadding();
        }
        return textFieldDefaults.m1256textFieldWithLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1256textFieldWithLabelPaddinga9UjIt4(float start, float end, float top, float bottom) {
        return PaddingKt.m483PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1246textFieldWithoutLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
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
        return textFieldDefaults.m1257textFieldWithoutLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1257textFieldWithoutLabelPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m483PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1244outlinedTextFieldPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
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
        return textFieldDefaults.m1254outlinedTextFieldPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1254outlinedTextFieldPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m483PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: textFieldColors-dx8h9Zs, reason: not valid java name */
    public final TextFieldColors m1255textFieldColorsdx8h9Zs(long textColor, long disabledTextColor, long backgroundColor, long cursorColor, long errorCursorColor, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long leadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long trailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long placeholderColor, long disabledPlaceholderColor, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        long textColor2;
        long disabledTextColor2;
        long backgroundColor2;
        long focusedIndicatorColor2;
        long unfocusedIndicatorColor2;
        long disabledIndicatorColor2;
        long leadingIconColor2;
        long disabledLeadingIconColor2;
        long trailingIconColor2;
        long disabledTrailingIconColor2;
        long focusedLabelColor2;
        long unfocusedLabelColor2;
        long disabledLabelColor2;
        long placeholderColor2;
        long disabledPlaceholderColor2;
        $composer.startReplaceableGroup(231892599);
        ComposerKt.sourceInformation($composer, "C(textFieldColors)P(17:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,8:c#ui.graphics.Color,13:c#ui.graphics.Color,19:c#ui.graphics.Color,2:c#ui.graphics.Color,9:c#ui.graphics.Color,15:c#ui.graphics.Color,4:c#ui.graphics.Color,11:c#ui.graphics.Color,18:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,14:c#ui.graphics.Color,20:c#ui.graphics.Color,3:c#ui.graphics.Color,10:c#ui.graphics.Color,16:c#ui.graphics.Color,5:c#ui.graphics.Color)377@14111L7,377@14142L7,378@14215L8,379@14273L6,380@14366L6,381@14430L6,383@14509L6,383@14550L4,385@14624L6,386@14776L8,387@14838L6,389@14912L6,390@15041L8,393@15170L6,394@15301L8,395@15366L6,397@15441L6,397@15482L4,398@15540L6,398@15575L6,399@15658L8,400@15716L6,401@15778L6,401@15813L6,402@15899L8:TextFieldDefaults.kt#jmzs0o");
        if ((i & 1) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long jM2981unboximpl = ((Color) objConsume).m2981unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd($composer);
            textColor2 = Color.m2969copywmQWz5c(jM2981unboximpl, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM2981unboximpl) : ((Number) objConsume2).floatValue(), (14 & 2) != 0 ? Color.m2977getRedimpl(jM2981unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM2981unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM2981unboximpl) : 0.0f);
        } else {
            textColor2 = textColor;
        }
        if ((i & 2) != 0) {
            long j = textColor2;
            disabledTextColor2 = Color.m2969copywmQWz5c(j, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        if ((i & 4) != 0) {
            long jM1047getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1047getOnSurface0d7_KjU();
            backgroundColor2 = Color.m2969copywmQWz5c(jM1047getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1047getOnSurface0d7_KjU) : 0.12f, (14 & 2) != 0 ? Color.m2977getRedimpl(jM1047getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1047getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1047getOnSurface0d7_KjU) : 0.0f);
        } else {
            backgroundColor2 = backgroundColor;
        }
        long cursorColor2 = (i & 8) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1048getPrimary0d7_KjU() : cursorColor;
        long errorCursorColor2 = (i & 16) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1042getError0d7_KjU() : errorCursorColor;
        if ((i & 32) != 0) {
            long jM1048getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1048getPrimary0d7_KjU();
            focusedIndicatorColor2 = Color.m2969copywmQWz5c(jM1048getPrimary0d7_KjU, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1048getPrimary0d7_KjU) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(jM1048getPrimary0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1048getPrimary0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1048getPrimary0d7_KjU) : 0.0f);
        } else {
            focusedIndicatorColor2 = focusedIndicatorColor;
        }
        if ((i & 64) != 0) {
            long jM1047getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1047getOnSurface0d7_KjU();
            unfocusedIndicatorColor2 = Color.m2969copywmQWz5c(jM1047getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1047getOnSurface0d7_KjU2) : 0.42f, (14 & 2) != 0 ? Color.m2977getRedimpl(jM1047getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1047getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1047getOnSurface0d7_KjU2) : 0.0f);
        } else {
            unfocusedIndicatorColor2 = unfocusedIndicatorColor;
        }
        if ((i & 128) != 0) {
            long j2 = unfocusedIndicatorColor2;
            disabledIndicatorColor2 = Color.m2969copywmQWz5c(j2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j2) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j2) : 0.0f);
        } else {
            disabledIndicatorColor2 = disabledIndicatorColor;
        }
        long errorIndicatorColor2 = (i & 256) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1042getError0d7_KjU() : errorIndicatorColor;
        if ((i & 512) != 0) {
            long jM1047getOnSurface0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1047getOnSurface0d7_KjU();
            leadingIconColor2 = Color.m2969copywmQWz5c(jM1047getOnSurface0d7_KjU3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1047getOnSurface0d7_KjU3) : 0.54f, (14 & 2) != 0 ? Color.m2977getRedimpl(jM1047getOnSurface0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1047getOnSurface0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1047getOnSurface0d7_KjU3) : 0.0f);
        } else {
            leadingIconColor2 = leadingIconColor;
        }
        if ((i & 1024) != 0) {
            long j3 = leadingIconColor2;
            disabledLeadingIconColor2 = Color.m2969copywmQWz5c(j3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j3) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j3) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (i & 2048) != 0 ? leadingIconColor2 : errorLeadingIconColor;
        if ((i & 4096) != 0) {
            long jM1047getOnSurface0d7_KjU4 = MaterialTheme.INSTANCE.getColors($composer, 6).m1047getOnSurface0d7_KjU();
            trailingIconColor2 = Color.m2969copywmQWz5c(jM1047getOnSurface0d7_KjU4, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1047getOnSurface0d7_KjU4) : 0.54f, (14 & 2) != 0 ? Color.m2977getRedimpl(jM1047getOnSurface0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1047getOnSurface0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1047getOnSurface0d7_KjU4) : 0.0f);
        } else {
            trailingIconColor2 = trailingIconColor;
        }
        if ((i & 8192) != 0) {
            long j4 = trailingIconColor2;
            disabledTrailingIconColor2 = Color.m2969copywmQWz5c(j4, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j4) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j4) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j4) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j4) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (i & 16384) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1042getError0d7_KjU() : errorTrailingIconColor;
        if ((32768 & i) != 0) {
            long jM1048getPrimary0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1048getPrimary0d7_KjU();
            focusedLabelColor2 = Color.m2969copywmQWz5c(jM1048getPrimary0d7_KjU2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1048getPrimary0d7_KjU2) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(jM1048getPrimary0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1048getPrimary0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1048getPrimary0d7_KjU2) : 0.0f);
        } else {
            focusedLabelColor2 = focusedLabelColor;
        }
        if ((65536 & i) != 0) {
            long jM1047getOnSurface0d7_KjU5 = MaterialTheme.INSTANCE.getColors($composer, 6).m1047getOnSurface0d7_KjU();
            unfocusedLabelColor2 = Color.m2969copywmQWz5c(jM1047getOnSurface0d7_KjU5, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1047getOnSurface0d7_KjU5) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(jM1047getOnSurface0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1047getOnSurface0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1047getOnSurface0d7_KjU5) : 0.0f);
        } else {
            unfocusedLabelColor2 = unfocusedLabelColor;
        }
        if ((131072 & i) != 0) {
            long j5 = unfocusedLabelColor2;
            disabledLabelColor2 = Color.m2969copywmQWz5c(j5, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j5) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j5) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j5) : 0.0f);
        } else {
            disabledLabelColor2 = disabledLabelColor;
        }
        long errorLabelColor2 = (262144 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1042getError0d7_KjU() : errorLabelColor;
        if ((524288 & i) != 0) {
            long jM1047getOnSurface0d7_KjU6 = MaterialTheme.INSTANCE.getColors($composer, 6).m1047getOnSurface0d7_KjU();
            placeholderColor2 = Color.m2969copywmQWz5c(jM1047getOnSurface0d7_KjU6, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1047getOnSurface0d7_KjU6) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(jM1047getOnSurface0d7_KjU6) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1047getOnSurface0d7_KjU6) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1047getOnSurface0d7_KjU6) : 0.0f);
        } else {
            placeholderColor2 = placeholderColor;
        }
        if ((i & 1048576) != 0) {
            long j6 = placeholderColor2;
            disabledPlaceholderColor2 = Color.m2969copywmQWz5c(j6, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j6) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j6) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j6) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j6) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(231892599, $changed, $changed1, "androidx.compose.material.TextFieldDefaults.textFieldColors (TextFieldDefaults.kt:376)");
        }
        DefaultTextFieldColors defaultTextFieldColors = new DefaultTextFieldColors(textColor2, disabledTextColor2, cursorColor2, errorCursorColor2, focusedIndicatorColor2, unfocusedIndicatorColor2, errorIndicatorColor2, disabledIndicatorColor2, leadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, trailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, backgroundColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, placeholderColor2, disabledPlaceholderColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultTextFieldColors;
    }

    /* JADX INFO: renamed from: outlinedTextFieldColors-dx8h9Zs, reason: not valid java name */
    public final TextFieldColors m1253outlinedTextFieldColorsdx8h9Zs(long textColor, long disabledTextColor, long backgroundColor, long cursorColor, long errorCursorColor, long focusedBorderColor, long unfocusedBorderColor, long disabledBorderColor, long errorBorderColor, long leadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long trailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long placeholderColor, long disabledPlaceholderColor, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        long textColor2;
        long disabledTextColor2;
        long focusedBorderColor2;
        long unfocusedBorderColor2;
        long disabledBorderColor2;
        long leadingIconColor2;
        long disabledLeadingIconColor2;
        long trailingIconColor2;
        long disabledTrailingIconColor2;
        long focusedLabelColor2;
        long unfocusedLabelColor2;
        long disabledLabelColor2;
        long placeholderColor2;
        long disabledPlaceholderColor2;
        $composer.startReplaceableGroup(1762667317);
        ComposerKt.sourceInformation($composer, "C(outlinedTextFieldColors)P(17:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,9:c#ui.graphics.Color,13:c#ui.graphics.Color,19:c#ui.graphics.Color,2:c#ui.graphics.Color,8:c#ui.graphics.Color,15:c#ui.graphics.Color,4:c#ui.graphics.Color,11:c#ui.graphics.Color,18:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,14:c#ui.graphics.Color,20:c#ui.graphics.Color,3:c#ui.graphics.Color,10:c#ui.graphics.Color,16:c#ui.graphics.Color,5:c#ui.graphics.Color)435@17427L7,435@17458L7,436@17531L8,438@17637L6,439@17701L6,441@17777L6,441@17818L4,443@17889L6,443@17932L8,444@18027L8,445@18086L6,447@18160L6,448@18289L8,451@18418L6,452@18549L8,453@18614L6,455@18689L6,455@18730L4,456@18788L6,456@18823L6,457@18906L8,458@18964L6,459@19026L6,459@19061L6,460@19147L8:TextFieldDefaults.kt#jmzs0o");
        if ((i & 1) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long jM2981unboximpl = ((Color) objConsume).m2981unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd($composer);
            textColor2 = Color.m2969copywmQWz5c(jM2981unboximpl, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM2981unboximpl) : ((Number) objConsume2).floatValue(), (14 & 2) != 0 ? Color.m2977getRedimpl(jM2981unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM2981unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM2981unboximpl) : 0.0f);
        } else {
            textColor2 = textColor;
        }
        if ((i & 2) != 0) {
            long j = textColor2;
            disabledTextColor2 = Color.m2969copywmQWz5c(j, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        long backgroundColor2 = (i & 4) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : backgroundColor;
        long cursorColor2 = (i & 8) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1048getPrimary0d7_KjU() : cursorColor;
        long errorCursorColor2 = (i & 16) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1042getError0d7_KjU() : errorCursorColor;
        if ((i & 32) != 0) {
            long jM1048getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1048getPrimary0d7_KjU();
            focusedBorderColor2 = Color.m2969copywmQWz5c(jM1048getPrimary0d7_KjU, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1048getPrimary0d7_KjU) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(jM1048getPrimary0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1048getPrimary0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1048getPrimary0d7_KjU) : 0.0f);
        } else {
            focusedBorderColor2 = focusedBorderColor;
        }
        if ((i & 64) != 0) {
            long jM1047getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1047getOnSurface0d7_KjU();
            unfocusedBorderColor2 = Color.m2969copywmQWz5c(jM1047getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1047getOnSurface0d7_KjU) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(jM1047getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1047getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1047getOnSurface0d7_KjU) : 0.0f);
        } else {
            unfocusedBorderColor2 = unfocusedBorderColor;
        }
        if ((i & 128) != 0) {
            long j2 = unfocusedBorderColor2;
            disabledBorderColor2 = Color.m2969copywmQWz5c(j2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j2) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j2) : 0.0f);
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        long errorBorderColor2 = (i & 256) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1042getError0d7_KjU() : errorBorderColor;
        if ((i & 512) != 0) {
            long jM1047getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1047getOnSurface0d7_KjU();
            leadingIconColor2 = Color.m2969copywmQWz5c(jM1047getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1047getOnSurface0d7_KjU2) : 0.54f, (14 & 2) != 0 ? Color.m2977getRedimpl(jM1047getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1047getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1047getOnSurface0d7_KjU2) : 0.0f);
        } else {
            leadingIconColor2 = leadingIconColor;
        }
        if ((i & 1024) != 0) {
            long j3 = leadingIconColor2;
            disabledLeadingIconColor2 = Color.m2969copywmQWz5c(j3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j3) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j3) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (i & 2048) != 0 ? leadingIconColor2 : errorLeadingIconColor;
        if ((i & 4096) != 0) {
            long jM1047getOnSurface0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1047getOnSurface0d7_KjU();
            trailingIconColor2 = Color.m2969copywmQWz5c(jM1047getOnSurface0d7_KjU3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1047getOnSurface0d7_KjU3) : 0.54f, (14 & 2) != 0 ? Color.m2977getRedimpl(jM1047getOnSurface0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1047getOnSurface0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1047getOnSurface0d7_KjU3) : 0.0f);
        } else {
            trailingIconColor2 = trailingIconColor;
        }
        if ((i & 8192) != 0) {
            long j4 = trailingIconColor2;
            disabledTrailingIconColor2 = Color.m2969copywmQWz5c(j4, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j4) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j4) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j4) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j4) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (i & 16384) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1042getError0d7_KjU() : errorTrailingIconColor;
        if ((32768 & i) != 0) {
            long jM1048getPrimary0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1048getPrimary0d7_KjU();
            focusedLabelColor2 = Color.m2969copywmQWz5c(jM1048getPrimary0d7_KjU2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1048getPrimary0d7_KjU2) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(jM1048getPrimary0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1048getPrimary0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1048getPrimary0d7_KjU2) : 0.0f);
        } else {
            focusedLabelColor2 = focusedLabelColor;
        }
        if ((65536 & i) != 0) {
            long jM1047getOnSurface0d7_KjU4 = MaterialTheme.INSTANCE.getColors($composer, 6).m1047getOnSurface0d7_KjU();
            unfocusedLabelColor2 = Color.m2969copywmQWz5c(jM1047getOnSurface0d7_KjU4, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1047getOnSurface0d7_KjU4) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(jM1047getOnSurface0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1047getOnSurface0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1047getOnSurface0d7_KjU4) : 0.0f);
        } else {
            unfocusedLabelColor2 = unfocusedLabelColor;
        }
        if ((131072 & i) != 0) {
            long j5 = unfocusedLabelColor2;
            disabledLabelColor2 = Color.m2969copywmQWz5c(j5, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j5) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j5) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j5) : 0.0f);
        } else {
            disabledLabelColor2 = disabledLabelColor;
        }
        long errorLabelColor2 = (262144 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1042getError0d7_KjU() : errorLabelColor;
        if ((524288 & i) != 0) {
            long jM1047getOnSurface0d7_KjU5 = MaterialTheme.INSTANCE.getColors($composer, 6).m1047getOnSurface0d7_KjU();
            placeholderColor2 = Color.m2969copywmQWz5c(jM1047getOnSurface0d7_KjU5, (14 & 1) != 0 ? Color.m2973getAlphaimpl(jM1047getOnSurface0d7_KjU5) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(jM1047getOnSurface0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(jM1047getOnSurface0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(jM1047getOnSurface0d7_KjU5) : 0.0f);
        } else {
            placeholderColor2 = placeholderColor;
        }
        if ((i & 1048576) != 0) {
            long j6 = placeholderColor2;
            disabledPlaceholderColor2 = Color.m2969copywmQWz5c(j6, (14 & 1) != 0 ? Color.m2973getAlphaimpl(j6) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m2977getRedimpl(j6) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(j6) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(j6) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1762667317, $changed, $changed1, "androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors (TextFieldDefaults.kt:434)");
        }
        DefaultTextFieldColors defaultTextFieldColors = new DefaultTextFieldColors(textColor2, disabledTextColor2, cursorColor2, errorCursorColor2, focusedBorderColor2, unfocusedBorderColor2, errorBorderColor2, disabledBorderColor2, leadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, trailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, backgroundColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, placeholderColor2, disabledPlaceholderColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultTextFieldColors;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0154  */
    /* JADX WARN: Code duplicated, block: B:102:0x015a  */
    /* JADX WARN: Code duplicated, block: B:104:0x0162  */
    /* JADX WARN: Code duplicated, block: B:105:0x0165  */
    /* JADX WARN: Code duplicated, block: B:107:0x016a  */
    /* JADX WARN: Code duplicated, block: B:110:0x0170  */
    /* JADX WARN: Code duplicated, block: B:111:0x0175  */
    /* JADX WARN: Code duplicated, block: B:113:0x0179  */
    /* JADX WARN: Code duplicated, block: B:115:0x0181  */
    /* JADX WARN: Code duplicated, block: B:116:0x0184  */
    /* JADX WARN: Code duplicated, block: B:118:0x0189  */
    /* JADX WARN: Code duplicated, block: B:121:0x018f  */
    /* JADX WARN: Code duplicated, block: B:129:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:132:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:140:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:143:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:144:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:146:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:148:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:150:0x01db  */
    /* JADX WARN: Code duplicated, block: B:159:0x020d  */
    /* JADX WARN: Code duplicated, block: B:161:0x0214  */
    /* JADX WARN: Code duplicated, block: B:171:0x024c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:172:0x024e  */
    /* JADX WARN: Code duplicated, block: B:173:0x0250  */
    /* JADX WARN: Code duplicated, block: B:175:0x0254  */
    /* JADX WARN: Code duplicated, block: B:177:0x0258  */
    /* JADX WARN: Code duplicated, block: B:178:0x025a  */
    /* JADX WARN: Code duplicated, block: B:180:0x025e  */
    /* JADX WARN: Code duplicated, block: B:181:0x0260  */
    /* JADX WARN: Code duplicated, block: B:183:0x0264  */
    /* JADX WARN: Code duplicated, block: B:184:0x0266  */
    /* JADX WARN: Code duplicated, block: B:187:0x026c  */
    /* JADX WARN: Code duplicated, block: B:188:0x02ac  */
    /* JADX WARN: Code duplicated, block: B:191:0x02b2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:192:0x02b4  */
    /* JADX WARN: Code duplicated, block: B:193:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:195:0x02ec  */
    /* JADX WARN: Code duplicated, block: B:198:0x0304  */
    /* JADX WARN: Code duplicated, block: B:201:0x038d  */
    /* JADX WARN: Code duplicated, block: B:205:0x0397  */
    /* JADX WARN: Code duplicated, block: B:207:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:67:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:71:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:72:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:74:0x0101  */
    /* JADX WARN: Code duplicated, block: B:77:0x0107  */
    /* JADX WARN: Code duplicated, block: B:78:0x010e  */
    /* JADX WARN: Code duplicated, block: B:80:0x0114  */
    /* JADX WARN: Code duplicated, block: B:82:0x011c  */
    /* JADX WARN: Code duplicated, block: B:83:0x011f  */
    /* JADX WARN: Code duplicated, block: B:85:0x0124  */
    /* JADX WARN: Code duplicated, block: B:88:0x012a  */
    /* JADX WARN: Code duplicated, block: B:89:0x0131  */
    /* JADX WARN: Code duplicated, block: B:91:0x0137  */
    /* JADX WARN: Code duplicated, block: B:93:0x013f  */
    /* JADX WARN: Code duplicated, block: B:94:0x0142  */
    /* JADX WARN: Code duplicated, block: B:96:0x0147  */
    /* JADX WARN: Code duplicated, block: B:99:0x014d  */
    public final void TextFieldDecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> innerTextField, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, TextFieldColors colors, PaddingValues contentPadding, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        Function2<? super Composer, ? super Integer, Unit> function6;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        boolean isError2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function2<? super Composer, ? super Integer, Unit> function9;
        TextFieldColors colors2;
        PaddingValues contentPadding2;
        Function2<? super Composer, ? super Integer, Unit> function10;
        int $dirty1;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        boolean isError3;
        TextFieldColors colors3;
        Function2<? super Composer, ? super Integer, Unit> function13;
        PaddingValues contentPadding3;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
        Intrinsics.checkNotNullParameter(visualTransformation, "visualTransformation");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Composer $composer3 = $composer.startRestartGroup(1171040065);
        ComposerKt.sourceInformation($composer3, "C(TextFieldDecorationBox)P(11,3,2,9,12,4,5,6,8,7,10)554@25036L17,562@25270L569:TextFieldDefaults.kt#jmzs0o");
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
                function6 = function2;
            } else if (($changed & 29360128) == 0) {
                function6 = function2;
                if ($composer3.changedInstance(function6)) {
                    i6 = 8388608;
                } else {
                    i6 = 4194304;
                }
                $dirty |= i6;
            } else {
                function6 = function2;
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
            if (($changed1 & 112) != 0) {
                $dirty2 |= ((i & 2048) == 0 || !$composer3.changed(colors)) ? 16 : 32;
            }
            if (($changed1 & 896) != 0) {
                $dirty2 |= ((i & 4096) == 0 || !$composer3.changed(contentPadding)) ? 128 : 256;
            }
            if ((i & 8192) != 0) {
                $dirty2 |= 3072;
            } else if (($changed1 & 7168) != 0) {
                $dirty2 |= $composer3.changed(this) ? 2048 : 1024;
            }
            if (($dirty & 1533916891) != 306783378 && ($dirty2 & 5851) == 1170 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                isError3 = isError;
                function10 = function3;
                function11 = function4;
                function12 = function5;
                colors3 = colors;
                contentPadding2 = contentPadding;
                $composer2 = $composer3;
                function13 = function6;
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        isError2 = false;
                    } else {
                        isError2 = isError;
                    }
                    if (i5 != 0) {
                        function6 = null;
                    }
                    if (i7 != 0) {
                        function7 = null;
                    } else {
                        function7 = function3;
                    }
                    if (i9 != 0) {
                        function8 = null;
                    } else {
                        function8 = function4;
                    }
                    if (i11 != 0) {
                        function9 = null;
                    } else {
                        function9 = function5;
                    }
                    if ((i & 2048) != 0) {
                        colors2 = m1255textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty2 >> 6) & 112, 2097151);
                        $dirty2 &= -113;
                    } else {
                        colors2 = colors;
                    }
                    if ((i & 4096) != 0) {
                        if (function6 == null) {
                            contentPadding3 = m1246textFieldWithoutLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        } else {
                            contentPadding3 = m1245textFieldWithLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        }
                        function10 = function7;
                        function11 = function8;
                        function12 = function9;
                        isError3 = isError2;
                        colors3 = colors2;
                        contentPadding2 = contentPadding3;
                        function13 = function6;
                        $dirty1 = $dirty2 & (-897);
                    } else {
                        contentPadding2 = contentPadding;
                        function10 = function7;
                        $dirty1 = $dirty2;
                        function11 = function8;
                        function12 = function9;
                        isError3 = isError2;
                        colors3 = colors2;
                        function13 = function6;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 2048) != 0) {
                        $dirty2 &= -113;
                    }
                    if ((i & 4096) != 0) {
                        isError3 = isError;
                        function10 = function3;
                        function11 = function4;
                        function12 = function5;
                        colors3 = colors;
                        contentPadding2 = contentPadding;
                        $dirty1 = $dirty2 & (-897);
                        function13 = function6;
                    } else {
                        isError3 = isError;
                        function10 = function3;
                        function11 = function4;
                        function12 = function5;
                        colors3 = colors;
                        contentPadding2 = contentPadding;
                        $dirty1 = $dirty2;
                        function13 = function6;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1171040065, $dirty, $dirty1, "androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:542)");
                }
                $composer2 = $composer3;
                TextFieldImplKt.CommonDecorationBox(TextFieldType.Filled, value, innerTextField, visualTransformation, function13, function10, function11, function12, singleLine, enabled, isError3, interactionSource, contentPadding2, colors3, null, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752) | (3670016 & ($dirty >> 9)) | (($dirty1 << 21) & 29360128) | (($dirty << 15) & 234881024) | (($dirty << 21) & 1879048192), (($dirty >> 18) & 14) | (($dirty >> 12) & 112) | ($dirty1 & 896) | (($dirty1 << 6) & 7168), 16384);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final boolean z = isError3;
            final Function2<? super Composer, ? super Integer, Unit> function14 = function13;
            final Function2<? super Composer, ? super Integer, Unit> function15 = function10;
            final Function2<? super Composer, ? super Integer, Unit> function16 = function11;
            final Function2<? super Composer, ? super Integer, Unit> function17 = function12;
            final TextFieldColors textFieldColors = colors3;
            final PaddingValues paddingValues = contentPadding2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox.1
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
                    TextFieldDefaults.this.TextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z, function14, function15, function16, function17, textFieldColors, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
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
            function6 = function2;
        } else if (($changed & 29360128) == 0) {
            function6 = function2;
            if ($composer3.changedInstance(function6)) {
                i6 = 8388608;
            } else {
                i6 = 4194304;
            }
            $dirty |= i6;
        } else {
            function6 = function2;
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
        if (($changed1 & 112) != 0) {
            $dirty2 |= ((i & 2048) == 0 || !$composer3.changed(colors)) ? 16 : 32;
        }
        if (($changed1 & 896) != 0) {
            $dirty2 |= ((i & 4096) == 0 || !$composer3.changed(contentPadding)) ? 128 : 256;
        }
        if ((i & 8192) != 0) {
            $dirty2 |= 3072;
        } else if (($changed1 & 7168) != 0) {
            $dirty2 |= $composer3.changed(this) ? 2048 : 1024;
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
                    function6 = null;
                }
                if (i7 != 0) {
                    function7 = null;
                } else {
                    function7 = function3;
                }
                if (i9 != 0) {
                    function8 = null;
                } else {
                    function8 = function4;
                }
                if (i11 != 0) {
                    function9 = null;
                } else {
                    function9 = function5;
                }
                if ((i & 2048) != 0) {
                    colors2 = m1255textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty2 >> 6) & 112, 2097151);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                if ((i & 4096) != 0) {
                    if (function6 == null) {
                        contentPadding3 = m1246textFieldWithoutLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding3 = m1245textFieldWithLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    function10 = function7;
                    function11 = function8;
                    function12 = function9;
                    isError3 = isError2;
                    colors3 = colors2;
                    contentPadding2 = contentPadding3;
                    function13 = function6;
                    $dirty1 = $dirty2 & (-897);
                } else {
                    contentPadding2 = contentPadding;
                    function10 = function7;
                    $dirty1 = $dirty2;
                    function11 = function8;
                    function12 = function9;
                    isError3 = isError2;
                    colors3 = colors2;
                    function13 = function6;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function6 = null;
                }
                if (i7 != 0) {
                    function7 = null;
                } else {
                    function7 = function3;
                }
                if (i9 != 0) {
                    function8 = null;
                } else {
                    function8 = function4;
                }
                if (i11 != 0) {
                    function9 = null;
                } else {
                    function9 = function5;
                }
                if ((i & 2048) != 0) {
                    colors2 = m1255textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty2 >> 6) & 112, 2097151);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                if ((i & 4096) != 0) {
                    if (function6 == null) {
                        contentPadding3 = m1246textFieldWithoutLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding3 = m1245textFieldWithLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    function10 = function7;
                    function11 = function8;
                    function12 = function9;
                    isError3 = isError2;
                    colors3 = colors2;
                    contentPadding2 = contentPadding3;
                    function13 = function6;
                    $dirty1 = $dirty2 & (-897);
                } else {
                    contentPadding2 = contentPadding;
                    function10 = function7;
                    $dirty1 = $dirty2;
                    function11 = function8;
                    function12 = function9;
                    isError3 = isError2;
                    colors3 = colors2;
                    function13 = function6;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1171040065, $dirty, $dirty1, "androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:542)");
            }
            $composer2 = $composer3;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Filled, value, innerTextField, visualTransformation, function13, function10, function11, function12, singleLine, enabled, isError3, interactionSource, contentPadding2, colors3, null, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752) | (3670016 & ($dirty >> 9)) | (($dirty1 << 21) & 29360128) | (($dirty << 15) & 234881024) | (($dirty << 21) & 1879048192), (($dirty >> 18) & 14) | (($dirty >> 12) & 112) | ($dirty1 & 896) | (($dirty1 << 6) & 7168), 16384);
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
                    function6 = null;
                }
                if (i7 != 0) {
                    function7 = null;
                } else {
                    function7 = function3;
                }
                if (i9 != 0) {
                    function8 = null;
                } else {
                    function8 = function4;
                }
                if (i11 != 0) {
                    function9 = null;
                } else {
                    function9 = function5;
                }
                if ((i & 2048) != 0) {
                    colors2 = m1255textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty2 >> 6) & 112, 2097151);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                if ((i & 4096) != 0) {
                    if (function6 == null) {
                        contentPadding3 = m1246textFieldWithoutLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding3 = m1245textFieldWithLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    function10 = function7;
                    function11 = function8;
                    function12 = function9;
                    isError3 = isError2;
                    colors3 = colors2;
                    contentPadding2 = contentPadding3;
                    function13 = function6;
                    $dirty1 = $dirty2 & (-897);
                } else {
                    contentPadding2 = contentPadding;
                    function10 = function7;
                    $dirty1 = $dirty2;
                    function11 = function8;
                    function12 = function9;
                    isError3 = isError2;
                    colors3 = colors2;
                    function13 = function6;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function6 = null;
                }
                if (i7 != 0) {
                    function7 = null;
                } else {
                    function7 = function3;
                }
                if (i9 != 0) {
                    function8 = null;
                } else {
                    function8 = function4;
                }
                if (i11 != 0) {
                    function9 = null;
                } else {
                    function9 = function5;
                }
                if ((i & 2048) != 0) {
                    colors2 = m1255textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty2 >> 6) & 112, 2097151);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                if ((i & 4096) != 0) {
                    if (function6 == null) {
                        contentPadding3 = m1246textFieldWithoutLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding3 = m1245textFieldWithLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    function10 = function7;
                    function11 = function8;
                    function12 = function9;
                    isError3 = isError2;
                    colors3 = colors2;
                    contentPadding2 = contentPadding3;
                    function13 = function6;
                    $dirty1 = $dirty2 & (-897);
                } else {
                    contentPadding2 = contentPadding;
                    function10 = function7;
                    $dirty1 = $dirty2;
                    function11 = function8;
                    function12 = function9;
                    isError3 = isError2;
                    colors3 = colors2;
                    function13 = function6;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1171040065, $dirty, $dirty1, "androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:542)");
            }
            $composer2 = $composer3;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Filled, value, innerTextField, visualTransformation, function13, function10, function11, function12, singleLine, enabled, isError3, interactionSource, contentPadding2, colors3, null, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752) | (3670016 & ($dirty >> 9)) | (($dirty1 << 21) & 29360128) | (($dirty << 15) & 234881024) | (($dirty << 21) & 1879048192), (($dirty >> 18) & 14) | (($dirty >> 12) & 112) | ($dirty1 & 896) | (($dirty1 << 6) & 7168), 16384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final boolean z2 = isError3;
        final Function2<? super Composer, ? super Integer, Unit> function18 = function13;
        final Function2<? super Composer, ? super Integer, Unit> function19 = function10;
        final Function2<? super Composer, ? super Integer, Unit> function110 = function11;
        final Function2<? super Composer, ? super Integer, Unit> function111 = function12;
        final TextFieldColors textFieldColors2 = colors3;
        final PaddingValues paddingValues2 = contentPadding2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox.1
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
                TextFieldDefaults.this.TextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z2, function18, function19, function110, function111, textFieldColors2, paddingValues2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0158  */
    /* JADX WARN: Code duplicated, block: B:102:0x015e  */
    /* JADX WARN: Code duplicated, block: B:104:0x0166  */
    /* JADX WARN: Code duplicated, block: B:105:0x0169  */
    /* JADX WARN: Code duplicated, block: B:107:0x016e  */
    /* JADX WARN: Code duplicated, block: B:110:0x0174  */
    /* JADX WARN: Code duplicated, block: B:111:0x0179  */
    /* JADX WARN: Code duplicated, block: B:113:0x017d  */
    /* JADX WARN: Code duplicated, block: B:115:0x0185  */
    /* JADX WARN: Code duplicated, block: B:116:0x0188  */
    /* JADX WARN: Code duplicated, block: B:118:0x018d  */
    /* JADX WARN: Code duplicated, block: B:121:0x0193  */
    /* JADX WARN: Code duplicated, block: B:129:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:132:0x01af  */
    /* JADX WARN: Code duplicated, block: B:140:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:143:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:144:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:146:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:148:0x01da  */
    /* JADX WARN: Code duplicated, block: B:150:0x01df  */
    /* JADX WARN: Code duplicated, block: B:153:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:154:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:156:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:159:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:161:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:170:0x0232  */
    /* JADX WARN: Code duplicated, block: B:172:0x0239  */
    /* JADX WARN: Code duplicated, block: B:182:0x0275 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:183:0x0277  */
    /* JADX WARN: Code duplicated, block: B:184:0x0279  */
    /* JADX WARN: Code duplicated, block: B:186:0x027d  */
    /* JADX WARN: Code duplicated, block: B:187:0x027f  */
    /* JADX WARN: Code duplicated, block: B:189:0x0283  */
    /* JADX WARN: Code duplicated, block: B:190:0x0285  */
    /* JADX WARN: Code duplicated, block: B:192:0x0289  */
    /* JADX WARN: Code duplicated, block: B:193:0x028b  */
    /* JADX WARN: Code duplicated, block: B:195:0x028f  */
    /* JADX WARN: Code duplicated, block: B:196:0x0291  */
    /* JADX WARN: Code duplicated, block: B:199:0x0297  */
    /* JADX WARN: Code duplicated, block: B:200:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:203:0x02df  */
    /* JADX WARN: Code duplicated, block: B:204:0x02f4  */
    /* JADX WARN: Code duplicated, block: B:206:0x02f8  */
    /* JADX WARN: Code duplicated, block: B:207:0x032c  */
    /* JADX WARN: Code duplicated, block: B:210:0x034b  */
    /* JADX WARN: Code duplicated, block: B:213:0x03dc  */
    /* JADX WARN: Code duplicated, block: B:217:0x03e6  */
    /* JADX WARN: Code duplicated, block: B:219:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:71:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:72:0x0100  */
    /* JADX WARN: Code duplicated, block: B:74:0x0105  */
    /* JADX WARN: Code duplicated, block: B:77:0x010b  */
    /* JADX WARN: Code duplicated, block: B:78:0x0112  */
    /* JADX WARN: Code duplicated, block: B:80:0x0118  */
    /* JADX WARN: Code duplicated, block: B:82:0x0120  */
    /* JADX WARN: Code duplicated, block: B:83:0x0123  */
    /* JADX WARN: Code duplicated, block: B:85:0x0128  */
    /* JADX WARN: Code duplicated, block: B:88:0x012e  */
    /* JADX WARN: Code duplicated, block: B:89:0x0135  */
    /* JADX WARN: Code duplicated, block: B:91:0x013b  */
    /* JADX WARN: Code duplicated, block: B:93:0x0143  */
    /* JADX WARN: Code duplicated, block: B:94:0x0146  */
    /* JADX WARN: Code duplicated, block: B:96:0x014b  */
    /* JADX WARN: Code duplicated, block: B:99:0x0151  */
    public final void OutlinedTextFieldDecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> innerTextField, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function6, Composer $composer, final int $changed, final int $changed1, final int i) {
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
        boolean isError2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function2<? super Composer, ? super Integer, Unit> function9;
        Function2<? super Composer, ? super Integer, Unit> function10;
        TextFieldColors colors2;
        Function2<? super Composer, ? super Integer, Unit> function11;
        PaddingValues contentPadding2;
        PaddingValues contentPadding3;
        Function2<? super Composer, ? super Integer, Unit> function2ComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        boolean isError3;
        TextFieldColors colors3;
        int $dirty1;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
        Intrinsics.checkNotNullParameter(visualTransformation, "visualTransformation");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Composer $composer3 = $composer.startRestartGroup(-1280721485);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextFieldDecorationBox)P(12,4,3,10,13,5,6,7,9,8,11,1,2)646@30369L25,652@30600L600:TextFieldDefaults.kt#jmzs0o");
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
            if (($changed1 & 112) != 0) {
                $dirty2 |= ((i & 2048) == 0 || !$composer3.changed(colors)) ? 16 : 32;
            }
            if (($changed1 & 896) != 0) {
                $dirty2 |= ((i & 4096) == 0 || !$composer3.changed(contentPadding)) ? 128 : 256;
            }
            i13 = i & 8192;
            if (i13 != 0) {
                $dirty2 |= 3072;
            } else if (($changed1 & 7168) != 0) {
                $dirty2 |= $composer3.changedInstance(function6) ? 2048 : 1024;
            }
            if ((i & 16384) != 0) {
                $dirty2 |= 24576;
            } else if (($changed1 & 57344) != 0) {
                $dirty2 |= $composer3.changed(this) ? 16384 : 8192;
            }
            if (($dirty & 1533916891) != 306783378 && (46811 & $dirty2) == 9362 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                isError3 = isError;
                function15 = function2;
                function12 = function3;
                function13 = function4;
                function14 = function5;
                colors3 = colors;
                contentPadding3 = contentPadding;
                function2ComposableLambda = function6;
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
                        function7 = null;
                    } else {
                        function7 = function2;
                    }
                    if (i7 != 0) {
                        function8 = null;
                    } else {
                        function8 = function3;
                    }
                    if (i9 != 0) {
                        function9 = null;
                    } else {
                        function9 = function4;
                    }
                    if (i11 != 0) {
                        function10 = null;
                    } else {
                        function10 = function5;
                    }
                    if ((i & 2048) != 0) {
                        colors2 = m1253outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty2 >> 9) & 112, 2097151);
                        $dirty2 &= -113;
                    } else {
                        colors2 = colors;
                    }
                    function11 = function8;
                    if ((i & 4096) != 0) {
                        contentPadding2 = m1244outlinedTextFieldPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        $dirty2 &= -897;
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i13 != 0) {
                        final boolean z = isError2;
                        final TextFieldColors textFieldColors = colors2;
                        final int i14 = $dirty;
                        final int i15 = $dirty2;
                        contentPadding3 = contentPadding2;
                        function12 = function11;
                        function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, 1261916269, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.1
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
                                ComposerKt.sourceInformation($composer4, "C649@30519L54:TextFieldDefaults.kt#jmzs0o");
                                if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1261916269, $changed2, -1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:648)");
                                }
                                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                                boolean z2 = enabled;
                                boolean z3 = z;
                                InteractionSource interactionSource2 = interactionSource;
                                TextFieldColors textFieldColors2 = textFieldColors;
                                int i16 = i14;
                                textFieldDefaults.m1247BorderBoxnbWgWpA(z2, z3, interactionSource2, textFieldColors2, null, 0.0f, 0.0f, $composer4, ((i16 >> 9) & 896) | ((i16 >> 6) & 14) | 12582912 | ((i16 >> 15) & 112) | ((i15 << 6) & 7168), 112);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        });
                        function13 = function9;
                        function14 = function10;
                        function15 = function7;
                        isError3 = isError2;
                        colors3 = colors2;
                        $dirty1 = $dirty2;
                    } else {
                        contentPadding3 = contentPadding2;
                        function2ComposableLambda = function6;
                        function12 = function11;
                        function13 = function9;
                        function14 = function10;
                        function15 = function7;
                        isError3 = isError2;
                        colors3 = colors2;
                        $dirty1 = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 2048) != 0) {
                        $dirty2 &= -113;
                    }
                    if ((i & 4096) != 0) {
                        isError3 = isError;
                        function15 = function2;
                        function12 = function3;
                        function13 = function4;
                        function14 = function5;
                        colors3 = colors;
                        contentPadding3 = contentPadding;
                        function2ComposableLambda = function6;
                        $dirty1 = $dirty2 & (-897);
                    } else {
                        isError3 = isError;
                        function15 = function2;
                        function12 = function3;
                        function13 = function4;
                        function14 = function5;
                        colors3 = colors;
                        contentPadding3 = contentPadding;
                        function2ComposableLambda = function6;
                        $dirty1 = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1280721485, $dirty, $dirty1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:634)");
                }
                $composer2 = $composer3;
                TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, value, innerTextField, visualTransformation, function15, function12, function13, function14, singleLine, enabled, isError3, interactionSource, contentPadding3, colors3, function2ComposableLambda, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty << 15) & 234881024) | (($dirty << 21) & 1879048192), (($dirty >> 18) & 14) | (($dirty >> 12) & 112) | ($dirty1 & 896) | (($dirty1 << 6) & 7168) | (($dirty1 << 3) & 57344), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final boolean z2 = isError3;
            final Function2<? super Composer, ? super Integer, Unit> function16 = function15;
            final Function2<? super Composer, ? super Integer, Unit> function17 = function12;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function13;
            final Function2<? super Composer, ? super Integer, Unit> function19 = function14;
            final TextFieldColors textFieldColors2 = colors3;
            final PaddingValues paddingValues = contentPadding3;
            final Function2<? super Composer, ? super Integer, Unit> function20 = function2ComposableLambda;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.2
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

                public final void invoke(Composer composer, int i16) {
                    TextFieldDefaults.this.OutlinedTextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z2, function16, function17, function18, function19, textFieldColors2, paddingValues, function20, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
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
        if (($changed1 & 112) != 0) {
            $dirty2 |= ((i & 2048) == 0 || !$composer3.changed(colors)) ? 16 : 32;
        }
        if (($changed1 & 896) != 0) {
            $dirty2 |= ((i & 4096) == 0 || !$composer3.changed(contentPadding)) ? 128 : 256;
        }
        i13 = i & 8192;
        if (i13 != 0) {
            $dirty2 |= 3072;
        } else if (($changed1 & 7168) != 0) {
            $dirty2 |= $composer3.changedInstance(function6) ? 2048 : 1024;
        }
        if ((i & 16384) != 0) {
            $dirty2 |= 24576;
        } else if (($changed1 & 57344) != 0) {
            $dirty2 |= $composer3.changed(this) ? 16384 : 8192;
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
                    function7 = null;
                } else {
                    function7 = function2;
                }
                if (i7 != 0) {
                    function8 = null;
                } else {
                    function8 = function3;
                }
                if (i9 != 0) {
                    function9 = null;
                } else {
                    function9 = function4;
                }
                if (i11 != 0) {
                    function10 = null;
                } else {
                    function10 = function5;
                }
                if ((i & 2048) != 0) {
                    colors2 = m1253outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty2 >> 9) & 112, 2097151);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                function11 = function8;
                if ((i & 4096) != 0) {
                    contentPadding2 = m1244outlinedTextFieldPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -897;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i13 != 0) {
                    final boolean z3 = isError2;
                    final TextFieldColors textFieldColors3 = colors2;
                    final int i16 = $dirty;
                    final int i17 = $dirty2;
                    contentPadding3 = contentPadding2;
                    function12 = function11;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, 1261916269, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C649@30519L54:TextFieldDefaults.kt#jmzs0o");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1261916269, $changed2, -1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:648)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z4 = enabled;
                            boolean z5 = z3;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors4 = textFieldColors3;
                            int i18 = i16;
                            textFieldDefaults.m1247BorderBoxnbWgWpA(z4, z5, interactionSource2, textFieldColors4, null, 0.0f, 0.0f, $composer4, ((i18 >> 9) & 896) | ((i18 >> 6) & 14) | 12582912 | ((i18 >> 15) & 112) | ((i17 << 6) & 7168), 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function13 = function9;
                    function14 = function10;
                    function15 = function7;
                    isError3 = isError2;
                    colors3 = colors2;
                    $dirty1 = $dirty2;
                } else {
                    contentPadding3 = contentPadding2;
                    function2ComposableLambda = function6;
                    function12 = function11;
                    function13 = function9;
                    function14 = function10;
                    function15 = function7;
                    isError3 = isError2;
                    colors3 = colors2;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function7 = null;
                } else {
                    function7 = function2;
                }
                if (i7 != 0) {
                    function8 = null;
                } else {
                    function8 = function3;
                }
                if (i9 != 0) {
                    function9 = null;
                } else {
                    function9 = function4;
                }
                if (i11 != 0) {
                    function10 = null;
                } else {
                    function10 = function5;
                }
                if ((i & 2048) != 0) {
                    colors2 = m1253outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty2 >> 9) & 112, 2097151);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                function11 = function8;
                if ((i & 4096) != 0) {
                    contentPadding2 = m1244outlinedTextFieldPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -897;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i13 != 0) {
                    final boolean z4 = isError2;
                    final TextFieldColors textFieldColors4 = colors2;
                    final int i18 = $dirty;
                    final int i19 = $dirty2;
                    contentPadding3 = contentPadding2;
                    function12 = function11;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, 1261916269, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C649@30519L54:TextFieldDefaults.kt#jmzs0o");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1261916269, $changed2, -1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:648)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z5 = enabled;
                            boolean z6 = z4;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors5 = textFieldColors4;
                            int i110 = i18;
                            textFieldDefaults.m1247BorderBoxnbWgWpA(z5, z6, interactionSource2, textFieldColors5, null, 0.0f, 0.0f, $composer4, ((i110 >> 9) & 896) | ((i110 >> 6) & 14) | 12582912 | ((i110 >> 15) & 112) | ((i19 << 6) & 7168), 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function13 = function9;
                    function14 = function10;
                    function15 = function7;
                    isError3 = isError2;
                    colors3 = colors2;
                    $dirty1 = $dirty2;
                } else {
                    contentPadding3 = contentPadding2;
                    function2ComposableLambda = function6;
                    function12 = function11;
                    function13 = function9;
                    function14 = function10;
                    function15 = function7;
                    isError3 = isError2;
                    colors3 = colors2;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1280721485, $dirty, $dirty1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:634)");
            }
            $composer2 = $composer3;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, value, innerTextField, visualTransformation, function15, function12, function13, function14, singleLine, enabled, isError3, interactionSource, contentPadding3, colors3, function2ComposableLambda, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty << 15) & 234881024) | (($dirty << 21) & 1879048192), (($dirty >> 18) & 14) | (($dirty >> 12) & 112) | ($dirty1 & 896) | (($dirty1 << 6) & 7168) | (($dirty1 << 3) & 57344), 0);
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
                    function7 = null;
                } else {
                    function7 = function2;
                }
                if (i7 != 0) {
                    function8 = null;
                } else {
                    function8 = function3;
                }
                if (i9 != 0) {
                    function9 = null;
                } else {
                    function9 = function4;
                }
                if (i11 != 0) {
                    function10 = null;
                } else {
                    function10 = function5;
                }
                if ((i & 2048) != 0) {
                    colors2 = m1253outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty2 >> 9) & 112, 2097151);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                function11 = function8;
                if ((i & 4096) != 0) {
                    contentPadding2 = m1244outlinedTextFieldPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -897;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i13 != 0) {
                    final boolean z5 = isError2;
                    final TextFieldColors textFieldColors5 = colors2;
                    final int i110 = $dirty;
                    final int i111 = $dirty2;
                    contentPadding3 = contentPadding2;
                    function12 = function11;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, 1261916269, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C649@30519L54:TextFieldDefaults.kt#jmzs0o");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1261916269, $changed2, -1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:648)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z6 = enabled;
                            boolean z7 = z5;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors6 = textFieldColors5;
                            int i112 = i110;
                            textFieldDefaults.m1247BorderBoxnbWgWpA(z6, z7, interactionSource2, textFieldColors6, null, 0.0f, 0.0f, $composer4, ((i112 >> 9) & 896) | ((i112 >> 6) & 14) | 12582912 | ((i112 >> 15) & 112) | ((i111 << 6) & 7168), 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function13 = function9;
                    function14 = function10;
                    function15 = function7;
                    isError3 = isError2;
                    colors3 = colors2;
                    $dirty1 = $dirty2;
                } else {
                    contentPadding3 = contentPadding2;
                    function2ComposableLambda = function6;
                    function12 = function11;
                    function13 = function9;
                    function14 = function10;
                    function15 = function7;
                    isError3 = isError2;
                    colors3 = colors2;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function7 = null;
                } else {
                    function7 = function2;
                }
                if (i7 != 0) {
                    function8 = null;
                } else {
                    function8 = function3;
                }
                if (i9 != 0) {
                    function9 = null;
                } else {
                    function9 = function4;
                }
                if (i11 != 0) {
                    function10 = null;
                } else {
                    function10 = function5;
                }
                if ((i & 2048) != 0) {
                    colors2 = m1253outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty2 >> 9) & 112, 2097151);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                function11 = function8;
                if ((i & 4096) != 0) {
                    contentPadding2 = m1244outlinedTextFieldPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -897;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i13 != 0) {
                    final boolean z6 = isError2;
                    final TextFieldColors textFieldColors6 = colors2;
                    final int i112 = $dirty;
                    final int i113 = $dirty2;
                    contentPadding3 = contentPadding2;
                    function12 = function11;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, 1261916269, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C649@30519L54:TextFieldDefaults.kt#jmzs0o");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1261916269, $changed2, -1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:648)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z7 = enabled;
                            boolean z8 = z6;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors7 = textFieldColors6;
                            int i114 = i112;
                            textFieldDefaults.m1247BorderBoxnbWgWpA(z7, z8, interactionSource2, textFieldColors7, null, 0.0f, 0.0f, $composer4, ((i114 >> 9) & 896) | ((i114 >> 6) & 14) | 12582912 | ((i114 >> 15) & 112) | ((i113 << 6) & 7168), 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function13 = function9;
                    function14 = function10;
                    function15 = function7;
                    isError3 = isError2;
                    colors3 = colors2;
                    $dirty1 = $dirty2;
                } else {
                    contentPadding3 = contentPadding2;
                    function2ComposableLambda = function6;
                    function12 = function11;
                    function13 = function9;
                    function14 = function10;
                    function15 = function7;
                    isError3 = isError2;
                    colors3 = colors2;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1280721485, $dirty, $dirty1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:634)");
            }
            $composer2 = $composer3;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, value, innerTextField, visualTransformation, function15, function12, function13, function14, singleLine, enabled, isError3, interactionSource, contentPadding3, colors3, function2ComposableLambda, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty << 15) & 234881024) | (($dirty << 21) & 1879048192), (($dirty >> 18) & 14) | (($dirty >> 12) & 112) | ($dirty1 & 896) | (($dirty1 << 6) & 7168) | (($dirty1 << 3) & 57344), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final boolean z7 = isError3;
        final Function2<? super Composer, ? super Integer, Unit> function110 = function15;
        final Function2<? super Composer, ? super Integer, Unit> function111 = function12;
        final Function2<? super Composer, ? super Integer, Unit> function112 = function13;
        final Function2<? super Composer, ? super Integer, Unit> function113 = function14;
        final TextFieldColors textFieldColors7 = colors3;
        final PaddingValues paddingValues2 = contentPadding3;
        final Function2<? super Composer, ? super Integer, Unit> function21 = function2ComposableLambda;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.2
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

            public final void invoke(Composer composer, int i114) {
                TextFieldDefaults.this.OutlinedTextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z7, function110, function111, function112, function113, textFieldColors7, paddingValues2, function21, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }
}
