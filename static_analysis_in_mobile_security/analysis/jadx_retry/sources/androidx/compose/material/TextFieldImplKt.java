package androidx.compose.material;

import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutIdParentData;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextFieldImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aß\u0001\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\f2\u0011\u0010$\u001a\r\u0012\u0004\u0012\u00020 0%¢\u0006\u0002\b&2\u0006\u0010'\u001a\u00020(2\u0013\u0010)\u001a\u000f\u0012\u0004\u0012\u00020 \u0018\u00010%¢\u0006\u0002\b&2\u0015\b\u0002\u0010*\u001a\u000f\u0012\u0004\u0012\u00020 \u0018\u00010%¢\u0006\u0002\b&2\u0015\b\u0002\u0010+\u001a\u000f\u0012\u0004\u0012\u00020 \u0018\u00010%¢\u0006\u0002\b&2\u0015\b\u0002\u0010,\u001a\u000f\u0012\u0004\u0012\u00020 \u0018\u00010%¢\u0006\u0002\b&2\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020.2\b\b\u0002\u00100\u001a\u00020.2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0015\b\u0002\u00107\u001a\u000f\u0012\u0004\u0012\u00020 \u0018\u00010%¢\u0006\u0002\b&H\u0001¢\u0006\u0002\u00108\u001aW\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020;2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010=2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010?2 \u0010@\u001a\u001c\u0012\u0004\u0012\u00020 0%¢\u0006\u0002\b&¢\u0006\f\bA\u0012\b\bB\u0012\u0004\b\u0003\u0010\u0000H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bC\u0010D\u001a\u0012\u0010E\u001a\u00020\u00012\b\u0010F\u001a\u0004\u0018\u00010GH\u0000\u001a\u0012\u0010H\u001a\u00020\u00012\b\u0010F\u001a\u0004\u0018\u00010GH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0019\u0010\u0002\u001a\u00020\u0003X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u000e\u0010\u000b\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000\"\u0019\u0010\u0012\u001a\u00020\u0003X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0013\u0010\u0005\"\u000e\u0010\u0014\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000\"\u0019\u0010\u0015\u001a\u00020\u0016X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018\"\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u001b*\u00020\u001c8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006I"}, d2 = {"AnimationDuration", "", "HorizontalIconPadding", "Landroidx/compose/ui/unit/Dp;", "getHorizontalIconPadding", "()F", "F", "IconDefaultSizeModifier", "Landroidx/compose/ui/Modifier;", "getIconDefaultSizeModifier", "()Landroidx/compose/ui/Modifier;", "LabelId", "", "LeadingId", "PlaceholderAnimationDelayOrDuration", "PlaceholderAnimationDuration", "PlaceholderId", "TextFieldId", "TextFieldPadding", "getTextFieldPadding", "TrailingId", "ZeroConstraints", "Landroidx/compose/ui/unit/Constraints;", "getZeroConstraints", "()J", "J", "layoutId", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "getLayoutId", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;)Ljava/lang/Object;", "CommonDecorationBox", "", "type", "Landroidx/compose/material/TextFieldType;", "value", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "singleLine", "", "enabled", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "colors", "Landroidx/compose/material/TextFieldColors;", OutlinedTextFieldKt.BorderId, "(Landroidx/compose/material/TextFieldType;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/material/TextFieldColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "Decoration", "contentColor", "Landroidx/compose/ui/graphics/Color;", "typography", "Landroidx/compose/ui/text/TextStyle;", "contentAlpha", "", "content", "Landroidx/compose/runtime/ComposableOpenTarget;", "index", "Decoration-euL9pac", "(JLandroidx/compose/ui/text/TextStyle;Ljava/lang/Float;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "heightOrZero", "placeable", "Landroidx/compose/ui/layout/Placeable;", "widthOrZero", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldImplKt {
    public static final int AnimationDuration = 150;
    public static final String LabelId = "Label";
    public static final String LeadingId = "Leading";
    private static final int PlaceholderAnimationDelayOrDuration = 67;
    private static final int PlaceholderAnimationDuration = 83;
    public static final String PlaceholderId = "Hint";
    public static final String TextFieldId = "TextField";
    public static final String TrailingId = "Trailing";
    private static final long ZeroConstraints = ConstraintsKt.Constraints(0, 0, 0, 0);
    private static final float TextFieldPadding = Dp.m5274constructorimpl(16);
    private static final float HorizontalIconPadding = Dp.m5274constructorimpl(12);
    private static final Modifier IconDefaultSizeModifier = SizeKt.m518defaultMinSizeVpY3zN4(Modifier.INSTANCE, Dp.m5274constructorimpl(48), Dp.m5274constructorimpl(48));

    public static final void CommonDecorationBox(final TextFieldType type, final String value, final Function2<? super Composer, ? super Integer, Unit> innerTextField, final VisualTransformation visualTransformation, final Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, boolean singleLine, boolean enabled, boolean isError, final InteractionSource interactionSource, final PaddingValues contentPadding, final TextFieldColors colors, Function2<? super Composer, ? super Integer, Unit> function6, Composer $composer, final int $changed, final int $changed1, final int i) {
        Object value$iv$iv;
        InputPhase inputPhase;
        long j;
        long j2;
        Composer $composer2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        boolean isError2;
        boolean isError3;
        boolean enabled2;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function2<? super Composer, ? super Integer, Unit> function9;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
        Intrinsics.checkNotNullParameter(visualTransformation, "visualTransformation");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(contentPadding, "contentPadding");
        Intrinsics.checkNotNullParameter(colors, "colors");
        Composer $composer3 = $composer.startRestartGroup(-712568069);
        ComposerKt.sourceInformation($composer3, "C(CommonDecorationBox)P(12,13,4,14,7,9,8,11,10,3,6,5,2,1)80@3167L105,84@3322L25,101@3932L10,108@4267L5253:TextFieldImpl.kt#jmzs0o");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(type) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer3.changed(value) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer3.changedInstance(innerTextField) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 16384 : 8192;
        }
        int i2 = i & 32;
        if (i2 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 131072 : 65536;
        }
        int i3 = i & 64;
        if (i3 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 1048576 : 524288;
        }
        int i4 = i & 128;
        if (i4 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty |= $composer3.changedInstance(function5) ? 8388608 : 4194304;
        }
        int i5 = i & 256;
        if (i5 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty |= $composer3.changed(singleLine) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i6 = i & 512;
        if (i6 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty |= $composer3.changed(enabled) ? 536870912 : 268435456;
        }
        int i7 = i & 1024;
        if (i7 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 14) == 0) {
            $dirty1 |= $composer3.changed(isError) ? 4 : 2;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 112) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 32 : 16;
        }
        if ((i & 4096) != 0) {
            $dirty1 |= 384;
        } else if (($changed1 & 896) == 0) {
            $dirty1 |= $composer3.changed(contentPadding) ? 256 : 128;
        }
        if ((i & 8192) != 0) {
            $dirty1 |= 3072;
        } else if (($changed1 & 7168) == 0) {
            $dirty1 |= $composer3.changed(colors) ? 2048 : 1024;
        }
        int i8 = i & 16384;
        if (i8 != 0) {
            $dirty1 |= 24576;
        } else if (($changed1 & 57344) == 0) {
            $dirty1 |= $composer3.changedInstance(function6) ? 16384 : 8192;
        }
        if (($dirty & 1533916891) == 306783378 && (46811 & $dirty1) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            function10 = function3;
            function9 = function4;
            function8 = function5;
            enabled2 = singleLine;
            isError3 = enabled;
            isError2 = isError;
            function7 = function6;
            $composer2 = $composer3;
        } else {
            Function2<? super Composer, ? super Integer, Unit> function11 = i2 != 0 ? null : function3;
            Function2<? super Composer, ? super Integer, Unit> function12 = i3 != 0 ? null : function4;
            Function2<? super Composer, ? super Integer, Unit> function13 = i4 != 0 ? null : function5;
            boolean singleLine2 = i5 != 0 ? false : singleLine;
            boolean enabled3 = i6 != 0 ? true : enabled;
            boolean isError4 = i7 != 0 ? false : isError;
            Function2<? super Composer, ? super Integer, Unit> function14 = i8 != 0 ? null : function6;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-712568069, $dirty, $dirty1, "androidx.compose.material.CommonDecorationBox (TextFieldImpl.kt:63)");
            }
            int i9 = (($dirty >> 3) & 14) | (($dirty >> 6) & 112);
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer3.changed(value) | $composer3.changed(visualTransformation);
            Object it$iv$iv = $composer3.rememberedValue();
            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = visualTransformation.filter(new AnnotatedString(value, null, null, 6, null));
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer3.endReplaceableGroup();
            final String transformedText = ((TransformedText) value$iv$iv).getText().getText();
            boolean isFocused = FocusInteractionKt.collectIsFocusedAsState(interactionSource, $composer3, ($dirty1 >> 3) & 14).getValue().booleanValue();
            if (isFocused) {
                inputPhase = InputPhase.Focused;
            } else {
                inputPhase = transformedText.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
            }
            InputPhase inputState = inputPhase;
            final int $dirty2 = $dirty;
            final int $dirty3 = $dirty1;
            final boolean z = enabled3;
            final boolean z2 = isError4;
            Function3<InputPhase, Composer, Integer, Color> function15 = new Function3<InputPhase, Composer, Integer, Color>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$labelColor$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Color invoke(InputPhase inputPhase2, Composer composer, Integer num) {
                    return Color.m2961boximpl(m1263invokeXeAY9LY(inputPhase2, composer, num.intValue()));
                }

                /* JADX INFO: renamed from: invoke-XeAY9LY, reason: not valid java name */
                public final long m1263invokeXeAY9LY(InputPhase it, Composer $composer4, int $changed2) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    $composer4.startReplaceableGroup(697243846);
                    ComposerKt.sourceInformation($composer4, "C92@3610L273:TextFieldImpl.kt#jmzs0o");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(697243846, $changed2, -1, "androidx.compose.material.CommonDecorationBox.<anonymous> (TextFieldImpl.kt:91)");
                    }
                    TextFieldColors textFieldColors = colors;
                    boolean z3 = z;
                    boolean z4 = it == InputPhase.UnfocusedEmpty ? false : z2;
                    InteractionSource interactionSource2 = interactionSource;
                    int i10 = ($dirty2 >> 27) & 14;
                    int i11 = $dirty3;
                    long jM2981unboximpl = textFieldColors.labelColor(z3, z4, interactionSource2, $composer4, i10 | ((i11 << 3) & 896) | (i11 & 7168)).getValue().m2981unboximpl();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer4.endReplaceableGroup();
                    return jM2981unboximpl;
                }
            };
            Typography typography = MaterialTheme.INSTANCE.getTypography($composer3, 6);
            TextStyle subtitle1 = typography.getSubtitle1();
            TextStyle caption = typography.getCaption();
            final boolean shouldOverrideTextStyleColor = (Color.m2972equalsimpl0(subtitle1.m4789getColor0d7_KjU(), Color.INSTANCE.m3007getUnspecified0d7_KjU()) && !Color.m2972equalsimpl0(caption.m4789getColor0d7_KjU(), Color.INSTANCE.m3007getUnspecified0d7_KjU())) || (!Color.m2972equalsimpl0(subtitle1.m4789getColor0d7_KjU(), Color.INSTANCE.m3007getUnspecified0d7_KjU()) && Color.m2972equalsimpl0(caption.m4789getColor0d7_KjU(), Color.INSTANCE.m3007getUnspecified0d7_KjU()));
            TextFieldTransitionScope textFieldTransitionScope = TextFieldTransitionScope.INSTANCE;
            $composer3.startReplaceableGroup(2129141006);
            ComposerKt.sourceInformation($composer3, "*110@4363L10,111@4455L22");
            long $this$CommonDecorationBox_u24lambda_u242 = MaterialTheme.INSTANCE.getTypography($composer3, 6).getCaption().m4789getColor0d7_KjU();
            if (shouldOverrideTextStyleColor) {
                long $this$takeOrElse_u2dDxMtmZc$iv = $this$CommonDecorationBox_u24lambda_u242;
                if (!($this$takeOrElse_u2dDxMtmZc$iv != Color.INSTANCE.m3007getUnspecified0d7_KjU())) {
                    $this$takeOrElse_u2dDxMtmZc$iv = function15.invoke(inputState, $composer3, 0).m2981unboximpl();
                }
                j = $this$takeOrElse_u2dDxMtmZc$iv;
            } else {
                j = $this$CommonDecorationBox_u24lambda_u242;
            }
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(2129141197);
            ComposerKt.sourceInformation($composer3, "*113@4554L10,114@4648L22");
            long $this$CommonDecorationBox_u24lambda_u244 = MaterialTheme.INSTANCE.getTypography($composer3, 6).getSubtitle1().m4789getColor0d7_KjU();
            if (shouldOverrideTextStyleColor) {
                long $this$takeOrElse_u2dDxMtmZc$iv2 = $this$CommonDecorationBox_u24lambda_u244;
                if (!($this$takeOrElse_u2dDxMtmZc$iv2 != Color.INSTANCE.m3007getUnspecified0d7_KjU())) {
                    $this$takeOrElse_u2dDxMtmZc$iv2 = function15.invoke(inputState, $composer3, 0).m2981unboximpl();
                }
                j2 = $this$takeOrElse_u2dDxMtmZc$iv2;
            } else {
                j2 = $this$CommonDecorationBox_u24lambda_u244;
            }
            $composer3.endReplaceableGroup();
            final Function2<? super Composer, ? super Integer, Unit> function16 = function11;
            final boolean z3 = isError4;
            final boolean z4 = enabled3;
            final Function2<? super Composer, ? super Integer, Unit> function17 = function12;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function13;
            final boolean z5 = singleLine2;
            final Function2<? super Composer, ? super Integer, Unit> function19 = function14;
            $composer2 = $composer3;
            textFieldTransitionScope.m1268TransitionDTcfvLk(inputState, j, j2, function15, function2 != null, ComposableLambdaKt.composableLambda($composer2, 341865432, true, new Function6<Float, Color, Color, Float, Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt.CommonDecorationBox.3

                /* JADX INFO: renamed from: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$WhenMappings */
                /* JADX INFO: compiled from: TextFieldImpl.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[TextFieldType.values().length];
                        try {
                            iArr[TextFieldType.Filled.ordinal()] = 1;
                        } catch (NoSuchFieldError e) {
                        }
                        try {
                            iArr[TextFieldType.Outlined.ordinal()] = 2;
                        } catch (NoSuchFieldError e2) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(6);
                }

                @Override // kotlin.jvm.functions.Function6
                public /* bridge */ /* synthetic */ Unit invoke(Float f, Color color, Color color2, Float f2, Composer composer, Integer num) {
                    m1261invokeRIQooxk(f.floatValue(), color.m2981unboximpl(), color2.m2981unboximpl(), f2.floatValue(), composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code duplicated, block: B:53:0x00e3  */
                /* JADX INFO: renamed from: invoke-RIQooxk, reason: not valid java name */
                public final void m1261invokeRIQooxk(final float labelProgress, final long labelTextStyleColor, final long labelContentColor, final float placeholderAlphaProgress, Composer $composer4, int $changed2) {
                    boolean z6;
                    Function2 decoratedLabel;
                    Function3 function3ComposableLambda;
                    Object value$iv$iv2;
                    long jM2981unboximpl;
                    long jM2981unboximpl2;
                    Object value$iv$iv3;
                    ComposerKt.sourceInformation($composer4, "CP(1,2:c#ui.graphics.Color,0:c#ui.graphics.Color)151@6394L30,152@6480L43:TextFieldImpl.kt#jmzs0o");
                    int $dirty4 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty4 |= $composer4.changed(labelProgress) ? 4 : 2;
                    }
                    if (($changed2 & 112) == 0) {
                        $dirty4 |= $composer4.changed(labelTextStyleColor) ? 32 : 16;
                    }
                    if (($changed2 & 896) == 0) {
                        $dirty4 |= $composer4.changed(labelContentColor) ? 256 : 128;
                    }
                    if (($changed2 & 7168) == 0) {
                        $dirty4 |= $composer4.changed(placeholderAlphaProgress) ? 2048 : 1024;
                    }
                    final int $dirty5 = $dirty4;
                    if ((46811 & $dirty5) != 9362 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(341865432, $dirty5, -1, "androidx.compose.material.CommonDecorationBox.<anonymous> (TextFieldImpl.kt:118)");
                        }
                        final Function2<Composer, Integer, Unit> function20 = function2;
                        if (function20 != null) {
                            final boolean z7 = shouldOverrideTextStyleColor;
                            z6 = true;
                            decoratedLabel = ComposableLambdaKt.composableLambda($composer4, 362863774, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$decoratedLabel$1$1
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
                                    ComposerKt.sourceInformation($composer5, "C*123@5027L10,124@5083L10,129@5294L55:TextFieldImpl.kt#jmzs0o");
                                    if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                        $composer5.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(362863774, $changed3, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:121)");
                                    }
                                    TextStyle it = TextStyleKt.lerp(MaterialTheme.INSTANCE.getTypography($composer5, 6).getSubtitle1(), MaterialTheme.INSTANCE.getTypography($composer5, 6).getCaption(), labelProgress);
                                    TextStyle labelTextStyle = z7 ? it.m4786copyv2rsoow((16252927 & 1) != 0 ? it.spanStyle.m4727getColor0d7_KjU() : labelTextStyleColor, (16252927 & 2) != 0 ? it.spanStyle.getFontSize() : 0L, (16252927 & 4) != 0 ? it.spanStyle.getFontWeight() : null, (16252927 & 8) != 0 ? it.spanStyle.getFontStyle() : null, (16252927 & 16) != 0 ? it.spanStyle.getFontSynthesis() : null, (16252927 & 32) != 0 ? it.spanStyle.getFontFamily() : null, (16252927 & 64) != 0 ? it.spanStyle.getFontFeatureSettings() : null, (16252927 & 128) != 0 ? it.spanStyle.getLetterSpacing() : 0L, (16252927 & 256) != 0 ? it.spanStyle.getBaselineShift() : null, (16252927 & 512) != 0 ? it.spanStyle.getTextGeometricTransform() : null, (16252927 & 1024) != 0 ? it.spanStyle.getLocaleList() : null, (16252927 & 2048) != 0 ? it.spanStyle.getBackground() : 0L, (16252927 & 4096) != 0 ? it.spanStyle.getTextDecoration() : null, (16252927 & 8192) != 0 ? it.spanStyle.getShadow() : null, (16252927 & 16384) != 0 ? it.spanStyle.getDrawStyle() : null, (16252927 & 32768) != 0 ? it.paragraphStyle.getTextAlign() : null, (16252927 & 65536) != 0 ? it.paragraphStyle.getTextDirection() : null, (16252927 & 131072) != 0 ? it.paragraphStyle.getLineHeight() : 0L, (16252927 & 262144) != 0 ? it.paragraphStyle.getTextIndent() : null, (16252927 & 524288) != 0 ? it.platformStyle : null, (16252927 & 1048576) != 0 ? it.paragraphStyle.getLineHeightStyle() : null, (16252927 & 2097152) != 0 ? it.paragraphStyle.getLineBreak() : null, (16252927 & 4194304) != 0 ? it.paragraphStyle.getHyphens() : null, (16252927 & 8388608) != 0 ? it.paragraphStyle.getTextMotion() : null) : it;
                                    TextFieldImplKt.m1260DecorationeuL9pac(labelContentColor, labelTextStyle, null, function20, $composer5, (($dirty5 >> 6) & 14) | 384, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            });
                        } else {
                            z6 = true;
                            decoratedLabel = null;
                        }
                        if (function16 == null) {
                            function3ComposableLambda = null;
                        } else {
                            if ((transformedText.length() == 0) && placeholderAlphaProgress > 0.0f) {
                                final TextFieldColors textFieldColors = colors;
                                final boolean z8 = z4;
                                final int i10 = $dirty2;
                                final int i11 = $dirty3;
                                final Function2<Composer, Integer, Unit> function21 = function16;
                                function3ComposableLambda = ComposableLambdaKt.composableLambda($composer4, 1120552650, z6, new Function3<Modifier, Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$decoratedPlaceholder$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(Modifier modifier, Composer composer, Integer num) {
                                        invoke(modifier, composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Modifier modifier, Composer $composer5, int $changed3) {
                                        Intrinsics.checkNotNullParameter(modifier, "modifier");
                                        ComposerKt.sourceInformation($composer5, "C138@5768L341:TextFieldImpl.kt#jmzs0o");
                                        int $dirty6 = $changed3;
                                        if (($changed3 & 14) == 0) {
                                            $dirty6 |= $composer5.changed(modifier) ? 4 : 2;
                                        }
                                        if (($dirty6 & 91) != 18 || !$composer5.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1120552650, $changed3, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:137)");
                                            }
                                            Modifier modifier$iv = AlphaKt.alpha(modifier, placeholderAlphaProgress);
                                            TextFieldColors textFieldColors2 = textFieldColors;
                                            boolean z9 = z8;
                                            int i12 = i10;
                                            int i13 = i11;
                                            Function2<Composer, Integer, Unit> function22 = function21;
                                            $composer5.startReplaceableGroup(733328855);
                                            ComposerKt.sourceInformation($composer5, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                            int $changed$iv$iv = (0 << 3) & 112;
                                            $composer5.startReplaceableGroup(-1323940314);
                                            ComposerKt.sourceInformation($composer5, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                            CompositionLocalMap localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
                                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
                                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                                            if (!($composer5.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer5.startReusableNode();
                                            if ($composer5.getInserting()) {
                                                $composer5.createNode(constructor);
                                            } else {
                                                $composer5.useNode();
                                            }
                                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer5);
                                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                            }
                                            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                            $composer5.startReplaceableGroup(2058660585);
                                            int i14 = ($changed$iv$iv$iv >> 9) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer5, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                            int i15 = ((0 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer5, -1536681527, "C140@5902L25,141@5990L10,139@5840L247:TextFieldImpl.kt#jmzs0o");
                                            TextFieldImplKt.m1260DecorationeuL9pac(textFieldColors2.placeholderColor(z9, $composer5, ((i12 >> 27) & 14) | ((i13 >> 6) & 112)).getValue().m2981unboximpl(), MaterialTheme.INSTANCE.getTypography($composer5, 6).getSubtitle1(), null, function22, $composer5, (i12 >> 6) & 7168, 4);
                                            ComposerKt.sourceInformationMarkerEnd($composer5);
                                            ComposerKt.sourceInformationMarkerEnd($composer5);
                                            $composer5.endReplaceableGroup();
                                            $composer5.endNode();
                                            $composer5.endReplaceableGroup();
                                            $composer5.endReplaceableGroup();
                                            boolean propagateMinConstraints$iv = ComposerKt.isTraceInProgress();
                                            if (propagateMinConstraints$iv) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer5.skipToGroupEnd();
                                    }
                                });
                            } else {
                                function3ComposableLambda = null;
                            }
                        }
                        Function3 decoratedPlaceholder = function3ComposableLambda;
                        final String defaultErrorMessage = Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1203getDefaultErrorMessageUdPEhr4(), $composer4, 6);
                        Modifier.Companion companion = Modifier.INSTANCE;
                        Object key1$iv = Boolean.valueOf(z3);
                        final boolean z9 = z3;
                        int i12 = $dirty3 & 14;
                        $composer4.startReplaceableGroup(511388516);
                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2):Composables.kt#9igjgp");
                        boolean invalid$iv$iv2 = $composer4.changed(key1$iv) | $composer4.changed(defaultErrorMessage);
                        Object it$iv$iv2 = $composer4.rememberedValue();
                        if (invalid$iv$iv2 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv2 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$decorationBoxModifier$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semantics) {
                                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                                    if (z9) {
                                        SemanticsPropertiesKt.error(semantics, defaultErrorMessage);
                                    }
                                }
                            };
                            $composer4.updateRememberedValue(value$iv$iv2);
                        } else {
                            value$iv$iv2 = it$iv$iv2;
                        }
                        $composer4.endReplaceableGroup();
                        Modifier decorationBoxModifier = SemanticsModifierKt.semantics$default(companion, false, (Function1) value$iv$iv2, 1, null);
                        if (colors instanceof TextFieldColorsWithIcons) {
                            $composer4.startReplaceableGroup(-1083197701);
                            ComposerKt.sourceInformation($composer4, "155@6617L53");
                            TextFieldColorsWithIcons textFieldColorsWithIcons = (TextFieldColorsWithIcons) colors;
                            boolean z10 = z4;
                            boolean z11 = z3;
                            InteractionSource interactionSource2 = interactionSource;
                            int i13 = ($dirty2 >> 27) & 14;
                            int i14 = $dirty3;
                            jM2981unboximpl = textFieldColorsWithIcons.leadingIconColor(z10, z11, interactionSource2, $composer4, ((i14 << 3) & 896) | i13 | ((i14 << 3) & 112)).getValue().m2981unboximpl();
                            $composer4.endReplaceableGroup();
                        } else {
                            $composer4.startReplaceableGroup(-1083197605);
                            ComposerKt.sourceInformation($composer4, "157@6713L34");
                            TextFieldColors textFieldColors2 = colors;
                            boolean z12 = z4;
                            boolean z13 = z3;
                            int i15 = ($dirty2 >> 27) & 14;
                            int i16 = $dirty3;
                            jM2981unboximpl = textFieldColors2.leadingIconColor(z12, z13, $composer4, i15 | ((i16 << 3) & 112) | ((i16 >> 3) & 896)).getValue().m2981unboximpl();
                            $composer4.endReplaceableGroup();
                        }
                        final long leadingIconColor = jM2981unboximpl;
                        final Function2<Composer, Integer, Unit> function22 = function17;
                        Function2 it = function22 != null ? ComposableLambdaKt.composableLambda($composer4, 1505327088, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$decoratedLeading$1$1
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
                                ComposerKt.sourceInformation($composer5, "C161@6883L57:TextFieldImpl.kt#jmzs0o");
                                if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1505327088, $changed3, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:160)");
                                }
                                TextFieldImplKt.m1260DecorationeuL9pac(leadingIconColor, null, null, function22, $composer5, 0, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }) : null;
                        if (colors instanceof TextFieldColorsWithIcons) {
                            $composer4.startReplaceableGroup(-1083197259);
                            ComposerKt.sourceInformation($composer4, "166@7059L54");
                            TextFieldColorsWithIcons textFieldColorsWithIcons2 = (TextFieldColorsWithIcons) colors;
                            boolean z14 = z4;
                            boolean z15 = z3;
                            InteractionSource interactionSource3 = interactionSource;
                            int i17 = ($dirty2 >> 27) & 14;
                            int i18 = $dirty3;
                            jM2981unboximpl2 = textFieldColorsWithIcons2.trailingIconColor(z14, z15, interactionSource3, $composer4, ((i18 << 3) & 896) | i17 | ((i18 << 3) & 112)).getValue().m2981unboximpl();
                            $composer4.endReplaceableGroup();
                        } else {
                            $composer4.startReplaceableGroup(-1083197162);
                            ComposerKt.sourceInformation($composer4, "168@7156L35");
                            TextFieldColors textFieldColors3 = colors;
                            boolean z16 = z4;
                            boolean z17 = z3;
                            int i19 = ($dirty2 >> 27) & 14;
                            int i20 = $dirty3;
                            jM2981unboximpl2 = textFieldColors3.trailingIconColor(z16, z17, $composer4, i19 | ((i20 << 3) & 112) | ((i20 >> 3) & 896)).getValue().m2981unboximpl();
                            $composer4.endReplaceableGroup();
                        }
                        final long trailingIconColor = jM2981unboximpl2;
                        final Function2<Composer, Integer, Unit> function23 = function18;
                        Function2 decoratedTrailing = function23 != null ? ComposableLambdaKt.composableLambda($composer4, -1894727196, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$decoratedTrailing$1$1
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
                                ComposerKt.sourceInformation($composer5, "C172@7329L58:TextFieldImpl.kt#jmzs0o");
                                if (($changed3 & 11) == 2 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1894727196, $changed3, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:171)");
                                }
                                TextFieldImplKt.m1260DecorationeuL9pac(trailingIconColor, null, null, function23, $composer5, 0, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }) : null;
                        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                            case 1:
                                $composer4.startReplaceableGroup(-1083196826);
                                ComposerKt.sourceInformation($composer4, "178@7489L485");
                                Function2<Composer, Integer, Unit> function24 = innerTextField;
                                boolean z18 = z5;
                                PaddingValues paddingValues = contentPadding;
                                int i21 = $dirty2;
                                TextFieldKt.TextFieldLayout(decorationBoxModifier, function24, decoratedLabel, decoratedPlaceholder, it, decoratedTrailing, z18, labelProgress, paddingValues, $composer4, ((i21 >> 6) & 3670016) | ((i21 >> 3) & 112) | (($dirty5 << 21) & 29360128) | (($dirty3 << 18) & 234881024));
                                $composer4.endReplaceableGroup();
                                break;
                            case 2:
                                $composer4.startReplaceableGroup(-1083196270);
                                ComposerKt.sourceInformation($composer4, "192@8096L38,210@8904L420,202@8496L994");
                                $composer4.startReplaceableGroup(-492369756);
                                ComposerKt.sourceInformation($composer4, "CC(remember):Composables.kt#9igjgp");
                                Object it$iv$iv3 = $composer4.rememberedValue();
                                if (it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                                    value$iv$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m2788boximpl(Size.INSTANCE.m2809getZeroNHjbRc()), null, 2, null);
                                    $composer4.updateRememberedValue(value$iv$iv3);
                                } else {
                                    value$iv$iv3 = it$iv$iv3;
                                }
                                $composer4.endReplaceableGroup();
                                final MutableState labelSize = (MutableState) value$iv$iv3;
                                final PaddingValues paddingValues2 = contentPadding;
                                final Function2<Composer, Integer, Unit> function25 = function19;
                                final int i22 = $dirty3;
                                Function2 drawBorder = ComposableLambdaKt.composableLambda($composer4, 139886979, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$drawBorder$1
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
                                        ComposerKt.sourceInformation($composer5, "C194@8214L246:TextFieldImpl.kt#jmzs0o");
                                        if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(139886979, $changed3, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:193)");
                                            }
                                            Modifier modifier$iv = OutlinedTextFieldKt.m1159outlineCutout12SF9DM(LayoutIdKt.layoutId(Modifier.INSTANCE, OutlinedTextFieldKt.BorderId), labelSize.getValue().getPackedValue(), paddingValues2);
                                            Function2<Composer, Integer, Unit> function26 = function25;
                                            int i23 = i22;
                                            $composer5.startReplaceableGroup(733328855);
                                            ComposerKt.sourceInformation($composer5, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, true, $composer5, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                            int $changed$iv$iv = (384 << 3) & 112;
                                            $composer5.startReplaceableGroup(-1323940314);
                                            ComposerKt.sourceInformation($composer5, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                            CompositionLocalMap localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
                                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
                                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                                            if (!($composer5.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer5.startReusableNode();
                                            if ($composer5.getInserting()) {
                                                $composer5.createNode(constructor);
                                            } else {
                                                $composer5.useNode();
                                            }
                                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer5);
                                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                            }
                                            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                            $composer5.startReplaceableGroup(2058660585);
                                            int i24 = ($changed$iv$iv$iv >> 9) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer5, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                            int i25 = ((384 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer5, -1536678945, "C:TextFieldImpl.kt#jmzs0o");
                                            $composer5.startReplaceableGroup(1661576646);
                                            ComposerKt.sourceInformation($composer5, "198@8430L8");
                                            if (function26 != null) {
                                                function26.invoke($composer5, Integer.valueOf((i23 >> 12) & 14));
                                            }
                                            $composer5.endReplaceableGroup();
                                            ComposerKt.sourceInformationMarkerEnd($composer5);
                                            ComposerKt.sourceInformationMarkerEnd($composer5);
                                            $composer5.endReplaceableGroup();
                                            $composer5.endNode();
                                            $composer5.endReplaceableGroup();
                                            $composer5.endReplaceableGroup();
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer5.skipToGroupEnd();
                                    }
                                });
                                Function2<Composer, Integer, Unit> function26 = innerTextField;
                                boolean z19 = z5;
                                Object key1$iv2 = Float.valueOf(labelProgress);
                                int i23 = ($dirty5 & 14) | 48;
                                $composer4.startReplaceableGroup(511388516);
                                ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2):Composables.kt#9igjgp");
                                boolean invalid$iv$iv3 = $composer4.changed(key1$iv2) | $composer4.changed(labelSize);
                                Object value$iv$iv4 = $composer4.rememberedValue();
                                if (invalid$iv$iv3 || value$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                                    value$iv$iv4 = (Function1) new Function1<Size, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Size size) {
                                            m1262invokeuvyYCjk(size.getPackedValue());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke-uvyYCjk, reason: not valid java name */
                                        public final void m1262invokeuvyYCjk(long it2) {
                                            float labelWidth = Size.m2800getWidthimpl(it2) * labelProgress;
                                            float labelHeight = Size.m2797getHeightimpl(it2) * labelProgress;
                                            if (Size.m2800getWidthimpl(labelSize.getValue().getPackedValue()) == labelWidth) {
                                                if (Size.m2797getHeightimpl(labelSize.getValue().getPackedValue()) == labelHeight) {
                                                    return;
                                                }
                                            }
                                            labelSize.setValue(Size.m2788boximpl(androidx.compose.ui.geometry.SizeKt.Size(labelWidth, labelHeight)));
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv$iv4);
                                }
                                $composer4.endReplaceableGroup();
                                Function1 function1 = (Function1) value$iv$iv4;
                                PaddingValues paddingValues3 = contentPadding;
                                int i24 = $dirty2;
                                OutlinedTextFieldKt.OutlinedTextFieldLayout(decorationBoxModifier, function26, decoratedPlaceholder, decoratedLabel, it, decoratedTrailing, z19, labelProgress, function1, drawBorder, paddingValues3, $composer4, ((i24 >> 3) & 112) | 805306368 | ((i24 >> 6) & 3670016) | (($dirty5 << 21) & 29360128), ($dirty3 >> 6) & 14);
                                $composer4.endReplaceableGroup();
                                break;
                            default:
                                $composer4.startReplaceableGroup(-1083194783);
                                $composer4.endReplaceableGroup();
                                break;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer2, 1769472);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function7 = function14;
            isError2 = isError4;
            isError3 = enabled3;
            enabled2 = singleLine2;
            function8 = function13;
            function9 = function12;
            function10 = function11;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Function2<? super Composer, ? super Integer, Unit> function20 = function10;
        final Function2<? super Composer, ? super Integer, Unit> function21 = function9;
        final Function2<? super Composer, ? super Integer, Unit> function22 = function8;
        final boolean z6 = enabled2;
        final boolean z7 = isError3;
        final boolean z8 = isError2;
        final Function2<? super Composer, ? super Integer, Unit> function23 = function7;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt.CommonDecorationBox.4
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
                TextFieldImplKt.CommonDecorationBox(type, value, innerTextField, visualTransformation, function2, function20, function21, function22, z6, z7, z8, interactionSource, contentPadding, colors, function23, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX INFO: renamed from: Decoration-euL9pac, reason: not valid java name */
    public static final void m1260DecorationeuL9pac(final long contentColor, TextStyle typography, Float contentAlpha, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        TextStyle textStyle;
        Float f;
        TextStyle typography2;
        Float contentAlpha2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-399493340);
        ComposerKt.sourceInformation($composer2, "C(Decoration)P(2:c#ui.graphics.Color,3,1):TextFieldImpl.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(contentColor) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            textStyle = typography;
        } else if (($changed & 112) == 0) {
            textStyle = typography;
            $dirty |= $composer2.changed(textStyle) ? 32 : 16;
        } else {
            textStyle = typography;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            f = contentAlpha;
        } else if (($changed & 896) == 0) {
            f = contentAlpha;
            $dirty |= $composer2.changed(f) ? 256 : 128;
        } else {
            f = contentAlpha;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 2048 : 1024;
        }
        final int $dirty2 = $dirty;
        if (($dirty2 & 5851) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            typography2 = textStyle;
            contentAlpha2 = f;
        } else {
            TextStyle typography3 = i2 != 0 ? null : textStyle;
            Float contentAlpha3 = i3 != 0 ? null : f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-399493340, $dirty2, -1, "androidx.compose.material.Decoration (TextFieldImpl.kt:233)");
            }
            final Float f2 = contentAlpha3;
            Function2 colorAndEmphasis = ComposableLambdaKt.composableLambda($composer2, 494684590, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$Decoration$colorAndEmphasis$1
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
                    ComposerKt.sourceInformation($composer3, "C240@9912L476:TextFieldImpl.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(494684590, $changed2, -1, "androidx.compose.material.Decoration.<anonymous> (TextFieldImpl.kt:239)");
                        }
                        ProvidedValue[] providedValueArr = {ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor))};
                        final Float f3 = f2;
                        final Function2<Composer, Integer, Unit> function2 = content;
                        final int i4 = $dirty2;
                        final long j = contentColor;
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer3, -1132188434, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$Decoration$colorAndEmphasis$1.1
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

                            public final void invoke(Composer $composer4, int $changed3) {
                                ComposerKt.sourceInformation($composer4, "C:TextFieldImpl.kt#jmzs0o");
                                if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1132188434, $changed3, -1, "androidx.compose.material.Decoration.<anonymous>.<anonymous> (TextFieldImpl.kt:240)");
                                    }
                                    if (f3 != null) {
                                        $composer4.startReplaceableGroup(-452621938);
                                        ComposerKt.sourceInformation($composer4, "242@10036L142");
                                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentAlphaKt.getLocalContentAlpha().provides(f3)}, function2, $composer4, ((i4 >> 6) & 112) | 8);
                                        $composer4.endReplaceableGroup();
                                    } else {
                                        $composer4.startReplaceableGroup(-452621758);
                                        ComposerKt.sourceInformation($composer4, "247@10216L148");
                                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m2973getAlphaimpl(j)))}, function2, $composer4, ((i4 >> 6) & 112) | 8);
                                        $composer4.endReplaceableGroup();
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        }), $composer3, 56);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            });
            if (typography3 != null) {
                $composer2.startReplaceableGroup(-2009952671);
                ComposerKt.sourceInformation($composer2, "254@10423L46");
                TextKt.ProvideTextStyle(typography3, colorAndEmphasis, $composer2, (($dirty2 >> 3) & 14) | 48);
            } else {
                $composer2.startReplaceableGroup(-2009952619);
                ComposerKt.sourceInformation($composer2, "254@10475L18");
                colorAndEmphasis.invoke($composer2, 6);
            }
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            typography2 = typography3;
            contentAlpha2 = contentAlpha3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final TextStyle textStyle2 = typography2;
        final Float f3 = contentAlpha2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$Decoration$1
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

            public final void invoke(Composer composer, int i4) {
                TextFieldImplKt.m1260DecorationeuL9pac(contentColor, textStyle2, f3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static final int widthOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getWidth();
        }
        return 0;
    }

    public static final int heightOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getHeight();
        }
        return 0;
    }

    public static final Object getLayoutId(IntrinsicMeasurable $this$layoutId) {
        Intrinsics.checkNotNullParameter($this$layoutId, "<this>");
        Object parentData = $this$layoutId.getParentData();
        LayoutIdParentData layoutIdParentData = parentData instanceof LayoutIdParentData ? (LayoutIdParentData) parentData : null;
        if (layoutIdParentData != null) {
            return layoutIdParentData.getLayoutId();
        }
        return null;
    }

    public static final long getZeroConstraints() {
        return ZeroConstraints;
    }

    public static final float getTextFieldPadding() {
        return TextFieldPadding;
    }

    public static final float getHorizontalIconPadding() {
        return HorizontalIconPadding;
    }

    public static final Modifier getIconDefaultSizeModifier() {
        return IconDefaultSizeModifier;
    }
}
