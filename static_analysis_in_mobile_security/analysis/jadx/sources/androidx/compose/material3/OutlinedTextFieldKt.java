package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
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
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: OutlinedTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u001aÖ\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00070\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0015\b\u0002\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,H\u0007¢\u0006\u0002\u0010-\u001a¨\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00070\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0015\b\u0002\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,H\u0007¢\u0006\u0002\u0010.\u001aÖ\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020/2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u00070\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0015\b\u0002\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,H\u0007¢\u0006\u0002\u00100\u001a¨\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020/2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u00070\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0015\b\u0002\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,H\u0007¢\u0006\u0002\u00101\u001a\u0083\u0002\u00102\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0011\u00103\u001a\r\u0012\u0004\u0012\u00020\u00070\u0014¢\u0006\u0002\b\u00152\u0019\u0010\u0016\u001a\u0015\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000b¢\u0006\u0002\b\u00152\u0013\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0013\u00104\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0013\u00105\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0013\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0006\u0010#\u001a\u00020\u000f2\u0006\u00106\u001a\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\u00070\u000b2\u0011\u0010:\u001a\r\u0012\u0004\u0012\u00020\u00070\u0014¢\u0006\u0002\b\u00152\u0013\u0010;\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014¢\u0006\u0002\b\u00152\u0006\u0010<\u001a\u00020=H\u0001ø\u0001\u0000¢\u0006\u0002\u0010>\u001am\u0010?\u001a\u00020%2\u0006\u0010@\u001a\u00020%2\u0006\u0010A\u001a\u00020%2\u0006\u0010B\u001a\u00020%2\u0006\u0010C\u001a\u00020%2\u0006\u0010D\u001a\u00020%2\u0006\u0010E\u001a\u00020%2\u0006\u0010F\u001a\u00020%2\u0006\u0010G\u001a\u00020%2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u0002072\u0006\u0010<\u001a\u00020=H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bK\u0010L\u001am\u0010M\u001a\u00020%2\u0006\u0010N\u001a\u00020%2\u0006\u0010O\u001a\u00020%2\u0006\u0010P\u001a\u00020%2\u0006\u0010Q\u001a\u00020%2\u0006\u0010R\u001a\u00020%2\u0006\u0010S\u001a\u00020%2\u0006\u0010T\u001a\u00020%2\u0006\u0010U\u001a\u00020\u000f2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u0002072\u0006\u0010<\u001a\u00020=H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bV\u0010W\u001a)\u0010X\u001a\u00020\r*\u00020\r2\u0006\u0010Y\u001a\u0002092\u0006\u0010<\u001a\u00020=H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bZ\u0010[\u001a\u009a\u0001\u0010\\\u001a\u00020\u0007*\u00020]2\u0006\u0010^\u001a\u00020%2\u0006\u0010_\u001a\u00020%2\b\u0010`\u001a\u0004\u0018\u00010a2\b\u0010b\u001a\u0004\u0018\u00010a2\b\u0010c\u001a\u0004\u0018\u00010a2\b\u0010d\u001a\u0004\u0018\u00010a2\u0006\u0010e\u001a\u00020a2\b\u0010f\u001a\u0004\u0018\u00010a2\b\u0010g\u001a\u0004\u0018\u00010a2\u0006\u0010h\u001a\u00020a2\b\u0010i\u001a\u0004\u0018\u00010a2\u0006\u00106\u001a\u0002072\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010J\u001a\u0002072\u0006\u0010j\u001a\u00020k2\u0006\u0010<\u001a\u00020=H\u0002\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0019\u0010\u0003\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006l"}, d2 = {"OutlinedTextFieldInnerPadding", "Landroidx/compose/ui/unit/Dp;", "F", "OutlinedTextFieldTopPadding", "getOutlinedTextFieldTopPadding", "()F", "OutlinedTextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "label", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "isError", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "minLines", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/TextFieldColors;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "OutlinedTextFieldLayout", "textField", "leading", "trailing", "animationProgress", "", "onLabelMeasured", "Landroidx/compose/ui/geometry/Size;", "container", "supporting", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZFLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;II)V", "calculateHeight", "leadingPlaceableHeight", "trailingPlaceableHeight", "prefixPlaceableHeight", "suffixPlaceableHeight", "textFieldPlaceableHeight", "labelPlaceableHeight", "placeholderPlaceableHeight", "supportingPlaceableHeight", "constraints", "Landroidx/compose/ui/unit/Constraints;", "density", "calculateHeight-DHJA7U0", "(IIIIIIIIJFLandroidx/compose/foundation/layout/PaddingValues;)I", "calculateWidth", "leadingPlaceableWidth", "trailingPlaceableWidth", "prefixPlaceableWidth", "suffixPlaceableWidth", "textFieldPlaceableWidth", "labelPlaceableWidth", "placeholderPlaceableWidth", "isLabelInMiddleSection", "calculateWidth-DHJA7U0", "(IIIIIIIZJFLandroidx/compose/foundation/layout/PaddingValues;)I", "outlineCutout", "labelSize", "outlineCutout-12SF9DM", "(Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/layout/PaddingValues;)Landroidx/compose/ui/Modifier;", "place", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "totalHeight", "width", "leadingPlaceable", "Landroidx/compose/ui/layout/Placeable;", "trailingPlaceable", "prefixPlaceable", "suffixPlaceable", "textFieldPlaceable", "labelPlaceable", "placeholderPlaceable", "containerPlaceable", "supportingPlaceable", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OutlinedTextFieldKt {
    private static final float OutlinedTextFieldInnerPadding = Dp.m5274constructorimpl(4);
    private static final float OutlinedTextFieldTopPadding = Dp.m5274constructorimpl(8);

    public static final void OutlinedTextField(final String value, final Function1<? super String, Unit> onValueChange, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        int i2;
        int maxLines2;
        boolean readOnly2;
        TextStyle textStyle2;
        VisualTransformation visualTransformation2;
        KeyboardOptions keyboardOptions2;
        KeyboardActions keyboardActions2;
        boolean singleLine2;
        int minLines2;
        MutableInteractionSource interactionSource2;
        Shape shape2;
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource interactionSource3;
        Shape shape3;
        int $dirty2;
        Function2<? super Composer, ? super Integer, Unit> function9;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        boolean isError2;
        int maxLines3;
        int $dirty;
        int $dirty1;
        TextFieldColors colors2;
        TextStyle textStyle3;
        Object value$iv$iv;
        TextFieldColors colors3;
        TextStyle textStyle4;
        Composer $composer2;
        Shape shape4;
        MutableInteractionSource interactionSource4;
        int maxLines4;
        boolean isError3;
        Function2<? super Composer, ? super Integer, Unit> function16;
        Function2<? super Composer, ? super Integer, Unit> function17;
        Function2<? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Function2<? super Composer, ? super Integer, Unit> function22;
        boolean enabled3;
        Modifier modifier3;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(onValueChange, "onValueChange");
        Composer $composer3 = $composer.startRestartGroup(-1922450045);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextField)P(21,11,10,1,14,19,6,12,7,20,13,17,18,3,22,5,4,16,8,9,2,15)145@8187L7,160@8939L39,161@9025L5,162@9088L8,170@9450L15,170@9384L2436:OutlinedTextField.kt#uh7d8r");
        int $dirty3 = $changed;
        int $dirty4 = $changed1;
        int $dirty5 = $changed2;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty3 |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty3 |= $composer3.changedInstance(onValueChange) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty3 |= $composer3.changed(enabled) ? 2048 : 1024;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty3 |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty3 |= $composer3.changed(readOnly) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            $dirty3 |= ((i & 32) == 0 && $composer3.changed(textStyle)) ? 131072 : 65536;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty3 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty3 |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty3 |= $composer3.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty3 |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty3 |= $composer3.changedInstance(function5) ? 536870912 : 268435456;
        }
        int i10 = i & 1024;
        if (i10 != 0) {
            $dirty4 |= 6;
        } else if (($changed1 & 14) == 0) {
            $dirty4 |= $composer3.changedInstance(function6) ? 4 : 2;
        }
        int i11 = i & 2048;
        if (i11 != 0) {
            $dirty4 |= 48;
        } else if (($changed1 & 112) == 0) {
            $dirty4 |= $composer3.changedInstance(function7) ? 32 : 16;
        }
        int i12 = i & 4096;
        if (i12 != 0) {
            $dirty4 |= 384;
        } else if (($changed1 & 896) == 0) {
            $dirty4 |= $composer3.changedInstance(function8) ? 256 : 128;
        }
        int i13 = i & 8192;
        if (i13 != 0) {
            $dirty4 |= 3072;
            i2 = i13;
        } else {
            i2 = i13;
            if (($changed1 & 7168) == 0) {
                $dirty4 |= $composer3.changed(isError) ? 2048 : 1024;
            }
        }
        int i14 = i & 16384;
        if (i14 != 0) {
            $dirty4 |= 24576;
        } else if (($changed1 & 57344) == 0) {
            $dirty4 |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        int i15 = i & 32768;
        if (i15 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & 458752) == 0) {
            $dirty4 |= $composer3.changed(keyboardOptions) ? 131072 : 65536;
        }
        int i16 = i & 65536;
        if (i16 != 0) {
            $dirty4 |= 1572864;
        } else if (($changed1 & 3670016) == 0) {
            $dirty4 |= $composer3.changed(keyboardActions) ? 1048576 : 524288;
        }
        int i17 = i & 131072;
        if (i17 != 0) {
            $dirty4 |= 12582912;
        } else if (($changed1 & 29360128) == 0) {
            $dirty4 |= $composer3.changed(singleLine) ? 8388608 : 4194304;
        }
        if (($changed1 & 234881024) == 0) {
            if ((i & 262144) == 0) {
                maxLines2 = maxLines;
                int i18 = $composer3.changed(maxLines2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty4 |= i18;
            } else {
                maxLines2 = maxLines;
            }
            $dirty4 |= i18;
        } else {
            maxLines2 = maxLines;
        }
        int i19 = i & 524288;
        if (i19 != 0) {
            $dirty4 |= 805306368;
        } else if (($changed1 & 1879048192) == 0) {
            $dirty4 |= $composer3.changed(minLines) ? 536870912 : 268435456;
        }
        int i20 = i & 1048576;
        if (i20 != 0) {
            $dirty5 |= 6;
        } else if (($changed2 & 14) == 0) {
            $dirty5 |= $composer3.changed(interactionSource) ? 4 : 2;
        }
        if (($changed2 & 112) == 0) {
            $dirty5 |= ((i & 2097152) == 0 && $composer3.changed(shape)) ? 32 : 16;
        }
        if (($changed2 & 896) == 0) {
            $dirty5 |= ((i & 4194304) == 0 && $composer3.changed(colors)) ? 256 : 128;
        }
        if (($dirty3 & 1533916891) == 306783378 && (1533916891 & $dirty4) == 306783378 && ($dirty5 & 731) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier;
            enabled3 = enabled;
            readOnly2 = readOnly;
            textStyle4 = textStyle;
            function22 = function2;
            function21 = function3;
            function20 = function4;
            function19 = function5;
            function18 = function6;
            function17 = function7;
            function16 = function8;
            isError3 = isError;
            visualTransformation2 = visualTransformation;
            keyboardOptions2 = keyboardOptions;
            keyboardActions2 = keyboardActions;
            singleLine2 = singleLine;
            minLines2 = minLines;
            interactionSource4 = interactionSource;
            shape4 = shape;
            colors3 = colors;
            maxLines4 = maxLines2;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier;
                boolean enabled4 = i4 != 0 ? true : enabled;
                readOnly2 = i5 != 0 ? false : readOnly;
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    textStyle2 = (TextStyle) objConsume;
                    $dirty3 &= -458753;
                } else {
                    textStyle2 = textStyle;
                }
                Function2<? super Composer, ? super Integer, Unit> function23 = i6 != 0 ? null : function2;
                Function2<? super Composer, ? super Integer, Unit> function24 = i7 != 0 ? null : function3;
                Function2<? super Composer, ? super Integer, Unit> function25 = i8 != 0 ? null : function4;
                Function2<? super Composer, ? super Integer, Unit> function26 = i9 != 0 ? null : function5;
                Function2<? super Composer, ? super Integer, Unit> function27 = i10 != 0 ? null : function6;
                Function2<? super Composer, ? super Integer, Unit> function28 = i11 != 0 ? null : function7;
                Function2<? super Composer, ? super Integer, Unit> function29 = i12 != 0 ? null : function8;
                boolean isError4 = i2 != 0 ? false : isError;
                visualTransformation2 = i14 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                keyboardOptions2 = i15 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                keyboardActions2 = i16 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                singleLine2 = i17 != 0 ? false : singleLine;
                if ((i & 262144) != 0) {
                    maxLines2 = singleLine2 ? 1 : Integer.MAX_VALUE;
                    $dirty4 &= -234881025;
                }
                minLines2 = i19 != 0 ? 1 : minLines;
                if (i20 != 0) {
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
                if ((2097152 & i) != 0) {
                    shape2 = OutlinedTextFieldDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty5 &= -113;
                } else {
                    shape2 = shape;
                }
                if ((i & 4194304) != 0) {
                    modifier2 = modifier4;
                    enabled2 = enabled4;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    $dirty2 = $dirty5 & (-897);
                    function9 = function24;
                    function10 = function25;
                    function11 = function26;
                    function12 = function27;
                    function13 = function23;
                    function14 = function28;
                    function15 = function29;
                    isError2 = isError4;
                    maxLines3 = maxLines2;
                    $dirty = $dirty3;
                    $dirty1 = $dirty4;
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                    textStyle3 = textStyle2;
                } else {
                    modifier2 = modifier4;
                    enabled2 = enabled4;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    $dirty2 = $dirty5;
                    function9 = function24;
                    function10 = function25;
                    function11 = function26;
                    function12 = function27;
                    function13 = function23;
                    function14 = function28;
                    function15 = function29;
                    isError2 = isError4;
                    maxLines3 = maxLines2;
                    $dirty = $dirty3;
                    $dirty1 = $dirty4;
                    colors2 = colors;
                    textStyle3 = textStyle2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                }
                if ((i & 262144) != 0) {
                    $dirty4 &= -234881025;
                }
                if ((2097152 & i) != 0) {
                    $dirty5 &= -113;
                }
                if ((i & 4194304) != 0) {
                    $dirty5 &= -897;
                }
                modifier2 = modifier;
                enabled2 = enabled;
                readOnly2 = readOnly;
                function13 = function2;
                function9 = function3;
                function10 = function4;
                function11 = function5;
                function12 = function6;
                function14 = function7;
                function15 = function8;
                isError2 = isError;
                visualTransformation2 = visualTransformation;
                keyboardOptions2 = keyboardOptions;
                keyboardActions2 = keyboardActions;
                singleLine2 = singleLine;
                minLines2 = minLines;
                interactionSource3 = interactionSource;
                shape3 = shape;
                colors2 = colors;
                $dirty = $dirty3;
                $dirty1 = $dirty4;
                $dirty2 = $dirty5;
                maxLines3 = maxLines2;
                textStyle3 = textStyle;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1922450045, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:139)");
            }
            $composer3.startReplaceableGroup(1663535868);
            ComposerKt.sourceInformation($composer3, "*166@9248L46");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle3.m4789getColor0d7_KjU();
            long textColor = ($this$takeOrElse_u2dDxMtmZc$iv > Color.INSTANCE.m3007getUnspecified0d7_KjU() ? 1 : ($this$takeOrElse_u2dDxMtmZc$iv == Color.INSTANCE.m3007getUnspecified0d7_KjU() ? 0 : -1)) != 0 ? $this$takeOrElse_u2dDxMtmZc$iv : colors2.textColor$material3_release(enabled2, isError2, interactionSource3, $composer3, (($dirty >> 9) & 14) | (($dirty1 >> 6) & 112) | (($dirty2 << 6) & 896) | (($dirty2 << 3) & 7168)).getValue().m2981unboximpl();
            $composer3.endReplaceableGroup();
            final TextStyle mergedTextStyle = textStyle3.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, (LineBreak) null, (Hyphens) null, 4194302, (DefaultConstructorMarker) null));
            ProvidedValue[] providedValueArr = {TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors2.getSelectionColors($composer3, ($dirty2 >> 6) & 14))};
            final Function2<? super Composer, ? super Integer, Unit> function30 = function13;
            final Modifier modifier5 = modifier2;
            final TextFieldColors textFieldColors = colors2;
            final boolean z = isError2;
            final int i21 = $dirty1;
            final int i22 = $dirty2;
            final boolean z2 = enabled2;
            final boolean z3 = readOnly2;
            final int $dirty6 = $dirty;
            final KeyboardOptions keyboardOptions3 = keyboardOptions2;
            final KeyboardActions keyboardActions3 = keyboardActions2;
            colors3 = colors2;
            final boolean z4 = singleLine2;
            textStyle4 = textStyle3;
            final int i23 = maxLines3;
            final int i24 = minLines2;
            final VisualTransformation visualTransformation3 = visualTransformation2;
            final MutableInteractionSource mutableInteractionSource = interactionSource3;
            final Function2<? super Composer, ? super Integer, Unit> function31 = function9;
            final Function2<? super Composer, ? super Integer, Unit> function32 = function10;
            final Function2<? super Composer, ? super Integer, Unit> function33 = function11;
            final Function2<? super Composer, ? super Integer, Unit> function34 = function12;
            final Function2<? super Composer, ? super Integer, Unit> function35 = function14;
            final Function2<? super Composer, ? super Integer, Unit> function36 = function15;
            final Shape shape5 = shape3;
            Function2<Composer, Integer, Unit> function37 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.2
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
                    Modifier modifierM491paddingqDBjuR0$default;
                    ComposerKt.sourceInformation($composer4, "C190@10308L20,171@9477L2337:OutlinedTextField.kt#uh7d8r");
                    if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1886965181, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:170)");
                        }
                        if (function30 != null) {
                            modifierM491paddingqDBjuR0$default = PaddingKt.m491paddingqDBjuR0$default(SemanticsModifierKt.semantics(modifier5, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.2.1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semantics) {
                                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                                }
                            }), 0.0f, OutlinedTextFieldKt.getOutlinedTextFieldTopPadding(), 0.0f, 0.0f, 13, null);
                        } else {
                            modifierM491paddingqDBjuR0$default = modifier5;
                        }
                        Modifier modifierM518defaultMinSizeVpY3zN4 = SizeKt.m518defaultMinSizeVpY3zN4(modifierM491paddingqDBjuR0$default, OutlinedTextFieldDefaults.INSTANCE.m1653getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m1652getMinHeightD9Ej5fM());
                        SolidColor solidColor = new SolidColor(textFieldColors.cursorColor$material3_release(z, $composer4, ((i21 >> 9) & 14) | ((i22 >> 3) & 112)).getValue().m2981unboximpl(), null);
                        final String str = value;
                        final boolean z5 = z2;
                        final boolean z6 = z4;
                        final VisualTransformation visualTransformation4 = visualTransformation3;
                        final MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
                        final boolean z7 = z;
                        final Function2<Composer, Integer, Unit> function38 = function30;
                        final Function2<Composer, Integer, Unit> function39 = function31;
                        final Function2<Composer, Integer, Unit> function40 = function32;
                        final Function2<Composer, Integer, Unit> function41 = function33;
                        final Function2<Composer, Integer, Unit> function42 = function34;
                        final Function2<Composer, Integer, Unit> function43 = function35;
                        final Function2<Composer, Integer, Unit> function44 = function36;
                        final TextFieldColors textFieldColors2 = textFieldColors;
                        final int i25 = $dirty6;
                        final int i26 = i21;
                        final int i27 = i22;
                        final Shape shape6 = shape5;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, 1474611661, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.2.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function45, Composer composer, Integer num) {
                                invoke((Function2<? super Composer, ? super Integer, Unit>) function45, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Function2<? super Composer, ? super Integer, Unit> innerTextField, Composer $composer5, int $changed4) {
                                Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
                                ComposerKt.sourceInformation($composer5, "C199@10744L1046:OutlinedTextField.kt#uh7d8r");
                                int $dirty7 = $changed4;
                                if (($changed4 & 14) == 0) {
                                    $dirty7 |= $composer5.changedInstance(innerTextField) ? 4 : 2;
                                }
                                int $dirty8 = $dirty7;
                                if (($dirty8 & 91) != 18 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1474611661, $dirty8, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:198)");
                                    }
                                    OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                    String str2 = str;
                                    boolean z8 = z5;
                                    boolean z9 = z6;
                                    VisualTransformation visualTransformation5 = visualTransformation4;
                                    MutableInteractionSource mutableInteractionSource3 = mutableInteractionSource2;
                                    boolean z10 = z7;
                                    Function2<Composer, Integer, Unit> function45 = function38;
                                    Function2<Composer, Integer, Unit> function46 = function39;
                                    Function2<Composer, Integer, Unit> function47 = function40;
                                    Function2<Composer, Integer, Unit> function48 = function41;
                                    Function2<Composer, Integer, Unit> function49 = function42;
                                    Function2<Composer, Integer, Unit> function50 = function43;
                                    Function2<Composer, Integer, Unit> function51 = function44;
                                    TextFieldColors textFieldColors3 = textFieldColors2;
                                    final boolean z11 = z5;
                                    final boolean z12 = z7;
                                    final MutableInteractionSource mutableInteractionSource4 = mutableInteractionSource2;
                                    final TextFieldColors textFieldColors4 = textFieldColors2;
                                    final Shape shape7 = shape6;
                                    final int i28 = i25;
                                    final int i29 = i26;
                                    final int i30 = i27;
                                    ComposableLambda composableLambda2 = ComposableLambdaKt.composableLambda($composer5, 2108828640, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.2.2.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                            invoke(composer, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer $composer6, int $changed5) {
                                            ComposerKt.sourceInformation($composer6, "C216@11520L230:OutlinedTextField.kt#uh7d8r");
                                            if (($changed5 & 11) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(2108828640, $changed5, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:215)");
                                                }
                                                OutlinedTextFieldDefaults outlinedTextFieldDefaults2 = OutlinedTextFieldDefaults.INSTANCE;
                                                boolean z13 = z11;
                                                boolean z14 = z12;
                                                MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource4;
                                                TextFieldColors textFieldColors5 = textFieldColors4;
                                                Shape shape8 = shape7;
                                                int i31 = ((i28 >> 9) & 14) | 12582912 | ((i29 >> 6) & 112);
                                                int i32 = i30;
                                                outlinedTextFieldDefaults2.m1648ContainerBoxnbWgWpA(z13, z14, mutableInteractionSource5, textFieldColors5, shape8, 0.0f, 0.0f, $composer6, i31 | ((i32 << 6) & 896) | ((i32 << 3) & 7168) | ((i32 << 9) & 57344), 96);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer6.skipToGroupEnd();
                                        }
                                    });
                                    int i31 = i25;
                                    int i32 = i26;
                                    int i33 = i27;
                                    outlinedTextFieldDefaults.DecorationBox(str2, innerTextField, z8, z9, visualTransformation5, mutableInteractionSource3, z10, function45, function46, function47, function48, function49, function50, function51, textFieldColors3, null, composableLambda2, $composer5, (i31 & 14) | (($dirty8 << 3) & 112) | ((i31 >> 3) & 896) | ((i32 >> 12) & 7168) | (i32 & 57344) | ((i33 << 15) & 458752) | ((i32 << 9) & 3670016) | ((i31 << 3) & 29360128) | ((i31 << 3) & 234881024) | ((i31 << 3) & 1879048192), ((i31 >> 27) & 14) | 14155776 | ((i32 << 3) & 112) | ((i32 << 3) & 896) | ((i32 << 3) & 7168) | ((i33 << 6) & 57344), 32768);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        });
                        int i28 = $dirty6;
                        int i29 = (i28 & 57344) | (i28 & 14) | (i28 & 112) | (i28 & 7168);
                        int i30 = i21;
                        BasicTextFieldKt.BasicTextField(value, onValueChange, modifierM518defaultMinSizeVpY3zN4, z2, z3, mergedTextStyle, keyboardOptions3, keyboardActions3, z4, i23, i24, visualTransformation3, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource, solidColor, composableLambda, $composer4, i29 | ((i30 << 3) & 3670016) | ((i30 << 3) & 29360128) | ((i30 << 3) & 234881024) | ((i30 << 3) & 1879048192), ((i30 >> 27) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i30 >> 9) & 112) | ((i22 << 9) & 7168), 4096);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            };
            $composer2 = $composer3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer2, -1886965181, true, function37), $composer2, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape4 = shape3;
            interactionSource4 = interactionSource3;
            maxLines4 = maxLines3;
            isError3 = isError2;
            function16 = function15;
            function17 = function14;
            function18 = function12;
            function19 = function11;
            function20 = function10;
            function21 = function9;
            function22 = function13;
            enabled3 = enabled2;
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier3;
        final boolean z5 = enabled3;
        final boolean z6 = readOnly2;
        final TextStyle textStyle5 = textStyle4;
        final Function2<? super Composer, ? super Integer, Unit> function38 = function22;
        final Function2<? super Composer, ? super Integer, Unit> function39 = function21;
        final Function2<? super Composer, ? super Integer, Unit> function40 = function20;
        final Function2<? super Composer, ? super Integer, Unit> function41 = function19;
        final Function2<? super Composer, ? super Integer, Unit> function42 = function18;
        final Function2<? super Composer, ? super Integer, Unit> function43 = function17;
        final Function2<? super Composer, ? super Integer, Unit> function44 = function16;
        final boolean z7 = isError3;
        final VisualTransformation visualTransformation4 = visualTransformation2;
        final KeyboardOptions keyboardOptions4 = keyboardOptions2;
        final KeyboardActions keyboardActions4 = keyboardActions2;
        final boolean z8 = singleLine2;
        final int i25 = maxLines4;
        final int i26 = minLines2;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource4;
        final Shape shape6 = shape4;
        final TextFieldColors textFieldColors2 = colors3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3
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

            public final void invoke(Composer composer, int i27) {
                OutlinedTextFieldKt.OutlinedTextField(value, onValueChange, modifier6, z5, z6, textStyle5, function38, function39, function40, function41, function42, function43, function44, z7, visualTransformation4, keyboardOptions4, keyboardActions4, z8, i25, i26, mutableInteractionSource2, shape6, textFieldColors2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), RecomposeScopeImplKt.updateChangedFlags($changed2), i);
            }
        });
    }

    public static final void OutlinedTextField(final TextFieldValue value, final Function1<? super TextFieldValue, Unit> onValueChange, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        int i2;
        int maxLines2;
        boolean readOnly2;
        TextStyle textStyle2;
        VisualTransformation visualTransformation2;
        KeyboardOptions keyboardOptions2;
        KeyboardActions keyboardActions2;
        boolean singleLine2;
        int minLines2;
        MutableInteractionSource interactionSource2;
        Shape shape2;
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource interactionSource3;
        Shape shape3;
        int $dirty2;
        Function2<? super Composer, ? super Integer, Unit> function9;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        boolean isError2;
        int maxLines3;
        int $dirty;
        int $dirty1;
        TextFieldColors colors2;
        TextStyle textStyle3;
        Object value$iv$iv;
        TextFieldColors colors3;
        TextStyle textStyle4;
        Composer $composer2;
        Shape shape4;
        MutableInteractionSource interactionSource4;
        int maxLines4;
        boolean isError3;
        Function2<? super Composer, ? super Integer, Unit> function16;
        Function2<? super Composer, ? super Integer, Unit> function17;
        Function2<? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Function2<? super Composer, ? super Integer, Unit> function22;
        boolean enabled3;
        Modifier modifier3;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(onValueChange, "onValueChange");
        Composer $composer3 = $composer.startRestartGroup(-1570442800);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextField)P(21,11,10,1,14,19,6,12,7,20,13,17,18,3,22,5,4,16,8,9,2,15)304@16929L7,319@17681L39,320@17767L5,321@17830L8,329@18192L15,329@18126L2441:OutlinedTextField.kt#uh7d8r");
        int $dirty3 = $changed;
        int $dirty4 = $changed1;
        int $dirty5 = $changed2;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty3 |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty3 |= $composer3.changedInstance(onValueChange) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty3 |= $composer3.changed(enabled) ? 2048 : 1024;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty3 |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty3 |= $composer3.changed(readOnly) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            $dirty3 |= ((i & 32) == 0 && $composer3.changed(textStyle)) ? 131072 : 65536;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty3 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty3 |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty3 |= $composer3.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty3 |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty3 |= $composer3.changedInstance(function5) ? 536870912 : 268435456;
        }
        int i10 = i & 1024;
        if (i10 != 0) {
            $dirty4 |= 6;
        } else if (($changed1 & 14) == 0) {
            $dirty4 |= $composer3.changedInstance(function6) ? 4 : 2;
        }
        int i11 = i & 2048;
        if (i11 != 0) {
            $dirty4 |= 48;
        } else if (($changed1 & 112) == 0) {
            $dirty4 |= $composer3.changedInstance(function7) ? 32 : 16;
        }
        int i12 = i & 4096;
        if (i12 != 0) {
            $dirty4 |= 384;
        } else if (($changed1 & 896) == 0) {
            $dirty4 |= $composer3.changedInstance(function8) ? 256 : 128;
        }
        int i13 = i & 8192;
        if (i13 != 0) {
            $dirty4 |= 3072;
            i2 = i13;
        } else {
            i2 = i13;
            if (($changed1 & 7168) == 0) {
                $dirty4 |= $composer3.changed(isError) ? 2048 : 1024;
            }
        }
        int i14 = i & 16384;
        if (i14 != 0) {
            $dirty4 |= 24576;
        } else if (($changed1 & 57344) == 0) {
            $dirty4 |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        int i15 = i & 32768;
        if (i15 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & 458752) == 0) {
            $dirty4 |= $composer3.changed(keyboardOptions) ? 131072 : 65536;
        }
        int i16 = i & 65536;
        if (i16 != 0) {
            $dirty4 |= 1572864;
        } else if (($changed1 & 3670016) == 0) {
            $dirty4 |= $composer3.changed(keyboardActions) ? 1048576 : 524288;
        }
        int i17 = i & 131072;
        if (i17 != 0) {
            $dirty4 |= 12582912;
        } else if (($changed1 & 29360128) == 0) {
            $dirty4 |= $composer3.changed(singleLine) ? 8388608 : 4194304;
        }
        if (($changed1 & 234881024) == 0) {
            if ((i & 262144) == 0) {
                maxLines2 = maxLines;
                int i18 = $composer3.changed(maxLines2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty4 |= i18;
            } else {
                maxLines2 = maxLines;
            }
            $dirty4 |= i18;
        } else {
            maxLines2 = maxLines;
        }
        int i19 = i & 524288;
        if (i19 != 0) {
            $dirty4 |= 805306368;
        } else if (($changed1 & 1879048192) == 0) {
            $dirty4 |= $composer3.changed(minLines) ? 536870912 : 268435456;
        }
        int i20 = i & 1048576;
        if (i20 != 0) {
            $dirty5 |= 6;
        } else if (($changed2 & 14) == 0) {
            $dirty5 |= $composer3.changed(interactionSource) ? 4 : 2;
        }
        if (($changed2 & 112) == 0) {
            $dirty5 |= ((i & 2097152) == 0 && $composer3.changed(shape)) ? 32 : 16;
        }
        if (($changed2 & 896) == 0) {
            $dirty5 |= ((i & 4194304) == 0 && $composer3.changed(colors)) ? 256 : 128;
        }
        if (($dirty3 & 1533916891) == 306783378 && (1533916891 & $dirty4) == 306783378 && ($dirty5 & 731) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier;
            enabled3 = enabled;
            readOnly2 = readOnly;
            textStyle4 = textStyle;
            function22 = function2;
            function21 = function3;
            function20 = function4;
            function19 = function5;
            function18 = function6;
            function17 = function7;
            function16 = function8;
            isError3 = isError;
            visualTransformation2 = visualTransformation;
            keyboardOptions2 = keyboardOptions;
            keyboardActions2 = keyboardActions;
            singleLine2 = singleLine;
            minLines2 = minLines;
            interactionSource4 = interactionSource;
            shape4 = shape;
            colors3 = colors;
            maxLines4 = maxLines2;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier;
                boolean enabled4 = i4 != 0 ? true : enabled;
                readOnly2 = i5 != 0 ? false : readOnly;
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    textStyle2 = (TextStyle) objConsume;
                    $dirty3 &= -458753;
                } else {
                    textStyle2 = textStyle;
                }
                Function2<? super Composer, ? super Integer, Unit> function23 = i6 != 0 ? null : function2;
                Function2<? super Composer, ? super Integer, Unit> function24 = i7 != 0 ? null : function3;
                Function2<? super Composer, ? super Integer, Unit> function25 = i8 != 0 ? null : function4;
                Function2<? super Composer, ? super Integer, Unit> function26 = i9 != 0 ? null : function5;
                Function2<? super Composer, ? super Integer, Unit> function27 = i10 != 0 ? null : function6;
                Function2<? super Composer, ? super Integer, Unit> function28 = i11 != 0 ? null : function7;
                Function2<? super Composer, ? super Integer, Unit> function29 = i12 != 0 ? null : function8;
                boolean isError4 = i2 != 0 ? false : isError;
                visualTransformation2 = i14 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                keyboardOptions2 = i15 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                keyboardActions2 = i16 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                singleLine2 = i17 != 0 ? false : singleLine;
                if ((i & 262144) != 0) {
                    maxLines2 = singleLine2 ? 1 : Integer.MAX_VALUE;
                    $dirty4 &= -234881025;
                }
                minLines2 = i19 != 0 ? 1 : minLines;
                if (i20 != 0) {
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
                if ((2097152 & i) != 0) {
                    shape2 = OutlinedTextFieldDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty5 &= -113;
                } else {
                    shape2 = shape;
                }
                if ((i & 4194304) != 0) {
                    modifier2 = modifier4;
                    enabled2 = enabled4;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    $dirty2 = $dirty5 & (-897);
                    function9 = function24;
                    function10 = function25;
                    function11 = function26;
                    function12 = function27;
                    function13 = function23;
                    function14 = function28;
                    function15 = function29;
                    isError2 = isError4;
                    maxLines3 = maxLines2;
                    $dirty = $dirty3;
                    $dirty1 = $dirty4;
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                    textStyle3 = textStyle2;
                } else {
                    modifier2 = modifier4;
                    enabled2 = enabled4;
                    interactionSource3 = interactionSource2;
                    shape3 = shape2;
                    $dirty2 = $dirty5;
                    function9 = function24;
                    function10 = function25;
                    function11 = function26;
                    function12 = function27;
                    function13 = function23;
                    function14 = function28;
                    function15 = function29;
                    isError2 = isError4;
                    maxLines3 = maxLines2;
                    $dirty = $dirty3;
                    $dirty1 = $dirty4;
                    colors2 = colors;
                    textStyle3 = textStyle2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                }
                if ((i & 262144) != 0) {
                    $dirty4 &= -234881025;
                }
                if ((2097152 & i) != 0) {
                    $dirty5 &= -113;
                }
                if ((i & 4194304) != 0) {
                    $dirty5 &= -897;
                }
                modifier2 = modifier;
                enabled2 = enabled;
                readOnly2 = readOnly;
                function13 = function2;
                function9 = function3;
                function10 = function4;
                function11 = function5;
                function12 = function6;
                function14 = function7;
                function15 = function8;
                isError2 = isError;
                visualTransformation2 = visualTransformation;
                keyboardOptions2 = keyboardOptions;
                keyboardActions2 = keyboardActions;
                singleLine2 = singleLine;
                minLines2 = minLines;
                interactionSource3 = interactionSource;
                shape3 = shape;
                colors2 = colors;
                $dirty = $dirty3;
                $dirty1 = $dirty4;
                $dirty2 = $dirty5;
                maxLines3 = maxLines2;
                textStyle3 = textStyle;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1570442800, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:298)");
            }
            $composer3.startReplaceableGroup(1663544610);
            ComposerKt.sourceInformation($composer3, "*325@17990L46");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle3.m4789getColor0d7_KjU();
            long textColor = ($this$takeOrElse_u2dDxMtmZc$iv > Color.INSTANCE.m3007getUnspecified0d7_KjU() ? 1 : ($this$takeOrElse_u2dDxMtmZc$iv == Color.INSTANCE.m3007getUnspecified0d7_KjU() ? 0 : -1)) != 0 ? $this$takeOrElse_u2dDxMtmZc$iv : colors2.textColor$material3_release(enabled2, isError2, interactionSource3, $composer3, (($dirty >> 9) & 14) | (($dirty1 >> 6) & 112) | (($dirty2 << 6) & 896) | (($dirty2 << 3) & 7168)).getValue().m2981unboximpl();
            $composer3.endReplaceableGroup();
            final TextStyle mergedTextStyle = textStyle3.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, (LineBreak) null, (Hyphens) null, 4194302, (DefaultConstructorMarker) null));
            ProvidedValue[] providedValueArr = {TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors2.getSelectionColors($composer3, ($dirty2 >> 6) & 14))};
            final Function2<? super Composer, ? super Integer, Unit> function30 = function13;
            final Modifier modifier5 = modifier2;
            final TextFieldColors textFieldColors = colors2;
            final boolean z = isError2;
            final int i21 = $dirty1;
            final int i22 = $dirty2;
            final boolean z2 = enabled2;
            final boolean z3 = readOnly2;
            final int $dirty6 = $dirty;
            final KeyboardOptions keyboardOptions3 = keyboardOptions2;
            final KeyboardActions keyboardActions3 = keyboardActions2;
            colors3 = colors2;
            final boolean z4 = singleLine2;
            textStyle4 = textStyle3;
            final int i23 = maxLines3;
            final int i24 = minLines2;
            final VisualTransformation visualTransformation3 = visualTransformation2;
            final MutableInteractionSource mutableInteractionSource = interactionSource3;
            final Function2<? super Composer, ? super Integer, Unit> function31 = function9;
            final Function2<? super Composer, ? super Integer, Unit> function32 = function10;
            final Function2<? super Composer, ? super Integer, Unit> function33 = function11;
            final Function2<? super Composer, ? super Integer, Unit> function34 = function12;
            final Function2<? super Composer, ? super Integer, Unit> function35 = function14;
            final Function2<? super Composer, ? super Integer, Unit> function36 = function15;
            final Shape shape5 = shape3;
            Function2<Composer, Integer, Unit> function37 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.5
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
                    Modifier modifierM491paddingqDBjuR0$default;
                    ComposerKt.sourceInformation($composer4, "C349@19050L20,330@18219L2342:OutlinedTextField.kt#uh7d8r");
                    if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1830921872, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:329)");
                        }
                        if (function30 != null) {
                            modifierM491paddingqDBjuR0$default = PaddingKt.m491paddingqDBjuR0$default(SemanticsModifierKt.semantics(modifier5, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.5.1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semantics) {
                                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                                }
                            }), 0.0f, OutlinedTextFieldKt.getOutlinedTextFieldTopPadding(), 0.0f, 0.0f, 13, null);
                        } else {
                            modifierM491paddingqDBjuR0$default = modifier5;
                        }
                        Modifier modifierM518defaultMinSizeVpY3zN4 = SizeKt.m518defaultMinSizeVpY3zN4(modifierM491paddingqDBjuR0$default, OutlinedTextFieldDefaults.INSTANCE.m1653getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m1652getMinHeightD9Ej5fM());
                        SolidColor solidColor = new SolidColor(textFieldColors.cursorColor$material3_release(z, $composer4, ((i21 >> 9) & 14) | ((i22 >> 3) & 112)).getValue().m2981unboximpl(), null);
                        final TextFieldValue textFieldValue = value;
                        final boolean z5 = z2;
                        final boolean z6 = z4;
                        final VisualTransformation visualTransformation4 = visualTransformation3;
                        final MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
                        final boolean z7 = z;
                        final Function2<Composer, Integer, Unit> function38 = function30;
                        final Function2<Composer, Integer, Unit> function39 = function31;
                        final Function2<Composer, Integer, Unit> function40 = function32;
                        final Function2<Composer, Integer, Unit> function41 = function33;
                        final Function2<Composer, Integer, Unit> function42 = function34;
                        final Function2<Composer, Integer, Unit> function43 = function35;
                        final Function2<Composer, Integer, Unit> function44 = function36;
                        final TextFieldColors textFieldColors2 = textFieldColors;
                        final int i25 = $dirty6;
                        final int i26 = i21;
                        final int i27 = i22;
                        final Shape shape6 = shape5;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, -757328870, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.5.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function45, Composer composer, Integer num) {
                                invoke((Function2<? super Composer, ? super Integer, Unit>) function45, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Function2<? super Composer, ? super Integer, Unit> innerTextField, Composer $composer5, int $changed4) {
                                Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
                                ComposerKt.sourceInformation($composer5, "C358@19486L1051:OutlinedTextField.kt#uh7d8r");
                                int $dirty7 = $changed4;
                                if (($changed4 & 14) == 0) {
                                    $dirty7 |= $composer5.changedInstance(innerTextField) ? 4 : 2;
                                }
                                int $dirty8 = $dirty7;
                                if (($dirty8 & 91) == 18 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-757328870, $dirty8, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:357)");
                                }
                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                String text = textFieldValue.getText();
                                boolean z8 = z5;
                                boolean z9 = z6;
                                VisualTransformation visualTransformation5 = visualTransformation4;
                                MutableInteractionSource mutableInteractionSource3 = mutableInteractionSource2;
                                boolean z10 = z7;
                                Function2<Composer, Integer, Unit> function45 = function38;
                                Function2<Composer, Integer, Unit> function46 = function39;
                                Function2<Composer, Integer, Unit> function47 = function40;
                                Function2<Composer, Integer, Unit> function48 = function41;
                                Function2<Composer, Integer, Unit> function49 = function42;
                                Function2<Composer, Integer, Unit> function50 = function43;
                                Function2<Composer, Integer, Unit> function51 = function44;
                                TextFieldColors textFieldColors3 = textFieldColors2;
                                final boolean z11 = z5;
                                final boolean z12 = z7;
                                final MutableInteractionSource mutableInteractionSource4 = mutableInteractionSource2;
                                final TextFieldColors textFieldColors4 = textFieldColors2;
                                final Shape shape7 = shape6;
                                final int i28 = i25;
                                final int i29 = i26;
                                final int i30 = i27;
                                ComposableLambda composableLambda2 = ComposableLambdaKt.composableLambda($composer5, 255570733, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.5.2.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer6, int $changed5) {
                                        ComposerKt.sourceInformation($composer6, "C375@20267L230:OutlinedTextField.kt#uh7d8r");
                                        if (($changed5 & 11) != 2 || !$composer6.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(255570733, $changed5, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:374)");
                                            }
                                            OutlinedTextFieldDefaults outlinedTextFieldDefaults2 = OutlinedTextFieldDefaults.INSTANCE;
                                            boolean z13 = z11;
                                            boolean z14 = z12;
                                            MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource4;
                                            TextFieldColors textFieldColors5 = textFieldColors4;
                                            Shape shape8 = shape7;
                                            int i31 = ((i28 >> 9) & 14) | 12582912 | ((i29 >> 6) & 112);
                                            int i32 = i30;
                                            outlinedTextFieldDefaults2.m1648ContainerBoxnbWgWpA(z13, z14, mutableInteractionSource5, textFieldColors5, shape8, 0.0f, 0.0f, $composer6, i31 | ((i32 << 6) & 896) | ((i32 << 3) & 7168) | ((i32 << 9) & 57344), 96);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer6.skipToGroupEnd();
                                    }
                                });
                                int i31 = i25;
                                int i32 = i26;
                                int i33 = i27;
                                outlinedTextFieldDefaults.DecorationBox(text, innerTextField, z8, z9, visualTransformation5, mutableInteractionSource3, z10, function45, function46, function47, function48, function49, function50, function51, textFieldColors3, null, composableLambda2, $composer5, (($dirty8 << 3) & 112) | ((i31 >> 3) & 896) | ((i32 >> 12) & 7168) | (i32 & 57344) | ((i33 << 15) & 458752) | ((i32 << 9) & 3670016) | ((i31 << 3) & 29360128) | ((i31 << 3) & 234881024) | ((i31 << 3) & 1879048192), ((i31 >> 27) & 14) | 14155776 | ((i32 << 3) & 112) | ((i32 << 3) & 896) | ((i32 << 3) & 7168) | ((i33 << 6) & 57344), 32768);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        });
                        int i28 = $dirty6;
                        int i29 = (i28 & 57344) | (i28 & 14) | (i28 & 112) | (i28 & 7168);
                        int i30 = i21;
                        BasicTextFieldKt.BasicTextField(value, onValueChange, modifierM518defaultMinSizeVpY3zN4, z2, z3, mergedTextStyle, keyboardOptions3, keyboardActions3, z4, i23, i24, visualTransformation3, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource, solidColor, composableLambda, $composer4, i29 | ((i30 << 3) & 3670016) | ((i30 << 3) & 29360128) | ((i30 << 3) & 234881024) | ((i30 << 3) & 1879048192), ((i30 >> 27) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i30 >> 9) & 112) | ((i22 << 9) & 7168), 4096);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            };
            $composer2 = $composer3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer2, 1830921872, true, function37), $composer2, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape4 = shape3;
            interactionSource4 = interactionSource3;
            maxLines4 = maxLines3;
            isError3 = isError2;
            function16 = function15;
            function17 = function14;
            function18 = function12;
            function19 = function11;
            function20 = function10;
            function21 = function9;
            function22 = function13;
            enabled3 = enabled2;
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier3;
        final boolean z5 = enabled3;
        final boolean z6 = readOnly2;
        final TextStyle textStyle5 = textStyle4;
        final Function2<? super Composer, ? super Integer, Unit> function38 = function22;
        final Function2<? super Composer, ? super Integer, Unit> function39 = function21;
        final Function2<? super Composer, ? super Integer, Unit> function40 = function20;
        final Function2<? super Composer, ? super Integer, Unit> function41 = function19;
        final Function2<? super Composer, ? super Integer, Unit> function42 = function18;
        final Function2<? super Composer, ? super Integer, Unit> function43 = function17;
        final Function2<? super Composer, ? super Integer, Unit> function44 = function16;
        final boolean z7 = isError3;
        final VisualTransformation visualTransformation4 = visualTransformation2;
        final KeyboardOptions keyboardOptions4 = keyboardOptions2;
        final KeyboardActions keyboardActions4 = keyboardActions2;
        final boolean z8 = singleLine2;
        final int i25 = maxLines4;
        final int i26 = minLines2;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource4;
        final Shape shape6 = shape4;
        final TextFieldColors textFieldColors2 = colors3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.6
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

            public final void invoke(Composer composer, int i27) {
                OutlinedTextFieldKt.OutlinedTextField(value, onValueChange, modifier6, z5, z6, textStyle5, function38, function39, function40, function41, function42, function43, function44, z7, visualTransformation4, keyboardOptions4, keyboardActions4, z8, i25, i26, mutableInteractionSource2, shape6, textFieldColors2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), RecomposeScopeImplKt.updateChangedFlags($changed2), i);
            }
        });
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use overload with prefix and suffix parameters")
    public static final /* synthetic */ void OutlinedTextField(final String value, final Function1 onValueChange, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2 label, Function2 placeholder, Function2 leadingIcon, Function2 trailingIcon, Function2 supportingText, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        TextStyle textStyle2;
        boolean singleLine2;
        int maxLines2;
        int $dirty1;
        int minLines2;
        MutableInteractionSource interactionSource2;
        Shape shape2;
        int $dirty2;
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource interactionSource3;
        int maxLines3;
        TextFieldColors colors2;
        Shape shape3;
        int $dirty3;
        TextStyle textStyle3;
        Function2 leadingIcon2;
        Function2 placeholder2;
        Function2 trailingIcon2;
        Function2 supportingText2;
        boolean isError2;
        VisualTransformation visualTransformation2;
        Function2 label2;
        KeyboardOptions keyboardOptions2;
        KeyboardActions keyboardActions2;
        boolean readOnly2;
        int $dirty;
        int $dirty4;
        Object value$iv$iv;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(onValueChange, "onValueChange");
        Composer $composer3 = $composer.startRestartGroup(-1575225237);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextField)P(19,11,10,1,13,17,6,12,7,18,16,3,20,5,4,15,8,9,2,14)398@20920L7,411@21580L39,412@21666L5,413@21729L8,415@21746L771:OutlinedTextField.kt#uh7d8r");
        int $dirty5 = $changed;
        int $dirty6 = $changed1;
        int $dirty7 = $changed2;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty5 |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty5 |= $composer3.changedInstance(onValueChange) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty5 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty5 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty5 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty5 |= $composer3.changed(enabled) ? 2048 : 1024;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty5 |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty5 |= $composer3.changed(readOnly) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            $dirty5 |= ((i & 32) == 0 && $composer3.changed(textStyle)) ? 131072 : 65536;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty5 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty5 |= $composer3.changedInstance(label) ? 1048576 : 524288;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty5 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty5 |= $composer3.changedInstance(placeholder) ? 8388608 : 4194304;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty5 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty5 |= $composer3.changedInstance(leadingIcon) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i8 = i & 512;
        if (i8 != 0) {
            $dirty5 |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty5 |= $composer3.changedInstance(trailingIcon) ? 536870912 : 268435456;
        }
        int i9 = i & 1024;
        if (i9 != 0) {
            $dirty6 |= 6;
        } else if (($changed1 & 14) == 0) {
            $dirty6 |= $composer3.changedInstance(supportingText) ? 4 : 2;
        }
        int i10 = i & 2048;
        if (i10 != 0) {
            $dirty6 |= 48;
        } else if (($changed1 & 112) == 0) {
            $dirty6 |= $composer3.changed(isError) ? 32 : 16;
        }
        int i11 = i & 4096;
        if (i11 != 0) {
            $dirty6 |= 384;
        } else if (($changed1 & 896) == 0) {
            $dirty6 |= $composer3.changed(visualTransformation) ? 256 : 128;
        }
        int i12 = i & 8192;
        if (i12 != 0) {
            $dirty6 |= 3072;
        } else if (($changed1 & 7168) == 0) {
            $dirty6 |= $composer3.changed(keyboardOptions) ? 2048 : 1024;
        }
        int i13 = i & 16384;
        if (i13 != 0) {
            $dirty6 |= 24576;
        } else if (($changed1 & 57344) == 0) {
            $dirty6 |= $composer3.changed(keyboardActions) ? 16384 : 8192;
        }
        int i14 = i & 32768;
        if (i14 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & 458752) == 0) {
            $dirty6 |= $composer3.changed(singleLine) ? 131072 : 65536;
        }
        if (($changed1 & 3670016) == 0) {
            $dirty6 |= ((i & 65536) == 0 && $composer3.changed(maxLines)) ? 1048576 : 524288;
        }
        int i15 = i & 131072;
        if (i15 != 0) {
            $dirty6 |= 12582912;
        } else if (($changed1 & 29360128) == 0) {
            $dirty6 |= $composer3.changed(minLines) ? 8388608 : 4194304;
        }
        int i16 = i & 262144;
        if (i16 != 0) {
            $dirty6 |= 100663296;
        } else if (($changed1 & 234881024) == 0) {
            $dirty6 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed1 & 1879048192) == 0) {
            $dirty6 |= ((i & 524288) == 0 && $composer3.changed(shape)) ? 536870912 : 268435456;
        }
        if (($changed2 & 14) == 0) {
            $dirty7 |= ((i & 1048576) == 0 && $composer3.changed(colors)) ? 4 : 2;
        }
        if (($dirty5 & 1533916891) == 306783378 && (1533916891 & $dirty6) == 306783378 && ($dirty7 & 11) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier2 = modifier;
            enabled2 = enabled;
            readOnly2 = readOnly;
            textStyle3 = textStyle;
            label2 = label;
            placeholder2 = placeholder;
            leadingIcon2 = leadingIcon;
            trailingIcon2 = trailingIcon;
            supportingText2 = supportingText;
            isError2 = isError;
            visualTransformation2 = visualTransformation;
            keyboardOptions2 = keyboardOptions;
            keyboardActions2 = keyboardActions;
            singleLine2 = singleLine;
            maxLines3 = maxLines;
            minLines2 = minLines;
            interactionSource3 = interactionSource;
            shape3 = shape;
            colors2 = colors;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
                boolean enabled3 = i3 != 0 ? true : enabled;
                boolean readOnly3 = i4 != 0 ? false : readOnly;
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    textStyle2 = (TextStyle) objConsume;
                    $dirty5 &= -458753;
                } else {
                    textStyle2 = textStyle;
                }
                Function2 label3 = i5 != 0 ? null : label;
                Function2 placeholder3 = i6 != 0 ? null : placeholder;
                Function2 leadingIcon3 = i7 != 0 ? null : leadingIcon;
                Function2 trailingIcon3 = i8 != 0 ? null : trailingIcon;
                Function2 supportingText3 = i9 != 0 ? null : supportingText;
                boolean isError3 = i10 != 0 ? false : isError;
                VisualTransformation visualTransformation3 = i11 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                KeyboardOptions keyboardOptions3 = i12 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                KeyboardActions keyboardActions3 = i13 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                singleLine2 = i14 != 0 ? false : singleLine;
                if ((i & 65536) != 0) {
                    maxLines2 = $dirty6 & (-3670017);
                    $dirty1 = singleLine2 ? 1 : Integer.MAX_VALUE;
                } else {
                    maxLines2 = $dirty6;
                    $dirty1 = maxLines;
                }
                minLines2 = i15 != 0 ? 1 : minLines;
                if (i16 != 0) {
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
                if ((i & 524288) != 0) {
                    shape2 = OutlinedTextFieldDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 = maxLines2 & (-1879048193);
                } else {
                    shape2 = shape;
                    $dirty2 = maxLines2;
                }
                if ((i & 1048576) != 0) {
                    modifier2 = modifier3;
                    enabled2 = enabled3;
                    interactionSource3 = interactionSource2;
                    maxLines3 = $dirty1;
                    shape3 = shape2;
                    $dirty3 = $dirty7 & (-15);
                    textStyle3 = textStyle2;
                    leadingIcon2 = leadingIcon3;
                    placeholder2 = placeholder3;
                    trailingIcon2 = trailingIcon3;
                    supportingText2 = supportingText3;
                    isError2 = isError3;
                    visualTransformation2 = visualTransformation3;
                    label2 = label3;
                    keyboardOptions2 = keyboardOptions3;
                    keyboardActions2 = keyboardActions3;
                    readOnly2 = readOnly3;
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                    $dirty = $dirty5;
                    $dirty4 = $dirty2;
                } else {
                    modifier2 = modifier3;
                    enabled2 = enabled3;
                    interactionSource3 = interactionSource2;
                    maxLines3 = $dirty1;
                    colors2 = colors;
                    shape3 = shape2;
                    $dirty3 = $dirty7;
                    textStyle3 = textStyle2;
                    leadingIcon2 = leadingIcon3;
                    placeholder2 = placeholder3;
                    trailingIcon2 = trailingIcon3;
                    supportingText2 = supportingText3;
                    isError2 = isError3;
                    visualTransformation2 = visualTransformation3;
                    label2 = label3;
                    keyboardOptions2 = keyboardOptions3;
                    keyboardActions2 = keyboardActions3;
                    readOnly2 = readOnly3;
                    $dirty = $dirty5;
                    $dirty4 = $dirty2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 65536) != 0) {
                    $dirty6 &= -3670017;
                }
                if ((i & 524288) != 0) {
                    $dirty6 &= -1879048193;
                }
                if ((i & 1048576) != 0) {
                    $dirty7 &= -15;
                }
                modifier2 = modifier;
                enabled2 = enabled;
                readOnly2 = readOnly;
                textStyle3 = textStyle;
                label2 = label;
                placeholder2 = placeholder;
                leadingIcon2 = leadingIcon;
                trailingIcon2 = trailingIcon;
                supportingText2 = supportingText;
                isError2 = isError;
                visualTransformation2 = visualTransformation;
                keyboardOptions2 = keyboardOptions;
                keyboardActions2 = keyboardActions;
                singleLine2 = singleLine;
                maxLines3 = maxLines;
                minLines2 = minLines;
                interactionSource3 = interactionSource;
                shape3 = shape;
                colors2 = colors;
                $dirty = $dirty5;
                $dirty4 = $dirty6;
                $dirty3 = $dirty7;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1575225237, $dirty, $dirty4, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:392)");
            }
            $composer2 = $composer3;
            OutlinedTextField(value, (Function1<? super String, Unit>) onValueChange, modifier2, enabled2, readOnly2, textStyle3, (Function2<? super Composer, ? super Integer, Unit>) label2, (Function2<? super Composer, ? super Integer, Unit>) placeholder2, (Function2<? super Composer, ? super Integer, Unit>) leadingIcon2, (Function2<? super Composer, ? super Integer, Unit>) trailingIcon2, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) supportingText2, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, singleLine2, maxLines3, minLines2, interactionSource3, shape3, colors2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | ($dirty & 1879048192), (($dirty4 << 6) & 896) | 54 | (($dirty4 << 6) & 7168) | (($dirty4 << 6) & 57344) | (($dirty4 << 6) & 458752) | (($dirty4 << 6) & 3670016) | (($dirty4 << 6) & 29360128) | (($dirty4 << 6) & 234881024) | (($dirty4 << 6) & 1879048192), (($dirty4 >> 24) & 14) | (($dirty4 >> 24) & 112) | (($dirty3 << 6) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier2;
        final boolean z = enabled2;
        final boolean z2 = readOnly2;
        final TextStyle textStyle4 = textStyle3;
        final Function2 function2 = label2;
        final Function2 function3 = placeholder2;
        final Function2 function4 = leadingIcon2;
        final Function2 function5 = trailingIcon2;
        final Function2 function6 = supportingText2;
        final boolean z3 = isError2;
        final VisualTransformation visualTransformation4 = visualTransformation2;
        final KeyboardOptions keyboardOptions4 = keyboardOptions2;
        final KeyboardActions keyboardActions4 = keyboardActions2;
        final boolean z4 = singleLine2;
        final int i17 = maxLines3;
        final int i18 = minLines2;
        final MutableInteractionSource mutableInteractionSource = interactionSource3;
        final Shape shape4 = shape3;
        final TextFieldColors textFieldColors = colors2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.8
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

            public final void invoke(Composer composer, int i19) {
                OutlinedTextFieldKt.OutlinedTextField(value, onValueChange, modifier4, z, z2, textStyle4, function2, function3, function4, function5, function6, z3, visualTransformation4, keyboardOptions4, keyboardActions4, z4, i17, i18, mutableInteractionSource, shape4, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), RecomposeScopeImplKt.updateChangedFlags($changed2), i);
            }
        });
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use overload with prefix and suffix parameters")
    public static final /* synthetic */ void OutlinedTextField(final TextFieldValue value, final Function1 onValueChange, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2 label, Function2 placeholder, Function2 leadingIcon, Function2 trailingIcon, Function2 supportingText, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        TextStyle textStyle2;
        boolean singleLine2;
        int maxLines2;
        int $dirty1;
        int minLines2;
        MutableInteractionSource interactionSource2;
        Shape shape2;
        int $dirty2;
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource interactionSource3;
        int maxLines3;
        TextFieldColors colors2;
        Shape shape3;
        int $dirty3;
        TextStyle textStyle3;
        Function2 leadingIcon2;
        Function2 placeholder2;
        Function2 trailingIcon2;
        Function2 supportingText2;
        boolean isError2;
        VisualTransformation visualTransformation2;
        Function2 label2;
        KeyboardOptions keyboardOptions2;
        KeyboardActions keyboardActions2;
        boolean readOnly2;
        int $dirty;
        int $dirty4;
        Object value$iv$iv;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(onValueChange, "onValueChange");
        Composer $composer3 = $composer.startRestartGroup(-989817544);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextField)P(19,11,10,1,13,17,6,12,7,18,16,3,20,5,4,15,8,9,2,14)451@22886L7,464@23546L39,465@23632L5,466@23695L8,468@23712L771:OutlinedTextField.kt#uh7d8r");
        int $dirty5 = $changed;
        int $dirty6 = $changed1;
        int $dirty7 = $changed2;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty5 |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty5 |= $composer3.changedInstance(onValueChange) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty5 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty5 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty5 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty5 |= $composer3.changed(enabled) ? 2048 : 1024;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty5 |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty5 |= $composer3.changed(readOnly) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            $dirty5 |= ((i & 32) == 0 && $composer3.changed(textStyle)) ? 131072 : 65536;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty5 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty5 |= $composer3.changedInstance(label) ? 1048576 : 524288;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty5 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty5 |= $composer3.changedInstance(placeholder) ? 8388608 : 4194304;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty5 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty5 |= $composer3.changedInstance(leadingIcon) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i8 = i & 512;
        if (i8 != 0) {
            $dirty5 |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty5 |= $composer3.changedInstance(trailingIcon) ? 536870912 : 268435456;
        }
        int i9 = i & 1024;
        if (i9 != 0) {
            $dirty6 |= 6;
        } else if (($changed1 & 14) == 0) {
            $dirty6 |= $composer3.changedInstance(supportingText) ? 4 : 2;
        }
        int i10 = i & 2048;
        if (i10 != 0) {
            $dirty6 |= 48;
        } else if (($changed1 & 112) == 0) {
            $dirty6 |= $composer3.changed(isError) ? 32 : 16;
        }
        int i11 = i & 4096;
        if (i11 != 0) {
            $dirty6 |= 384;
        } else if (($changed1 & 896) == 0) {
            $dirty6 |= $composer3.changed(visualTransformation) ? 256 : 128;
        }
        int i12 = i & 8192;
        if (i12 != 0) {
            $dirty6 |= 3072;
        } else if (($changed1 & 7168) == 0) {
            $dirty6 |= $composer3.changed(keyboardOptions) ? 2048 : 1024;
        }
        int i13 = i & 16384;
        if (i13 != 0) {
            $dirty6 |= 24576;
        } else if (($changed1 & 57344) == 0) {
            $dirty6 |= $composer3.changed(keyboardActions) ? 16384 : 8192;
        }
        int i14 = i & 32768;
        if (i14 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & 458752) == 0) {
            $dirty6 |= $composer3.changed(singleLine) ? 131072 : 65536;
        }
        if (($changed1 & 3670016) == 0) {
            $dirty6 |= ((i & 65536) == 0 && $composer3.changed(maxLines)) ? 1048576 : 524288;
        }
        int i15 = i & 131072;
        if (i15 != 0) {
            $dirty6 |= 12582912;
        } else if (($changed1 & 29360128) == 0) {
            $dirty6 |= $composer3.changed(minLines) ? 8388608 : 4194304;
        }
        int i16 = i & 262144;
        if (i16 != 0) {
            $dirty6 |= 100663296;
        } else if (($changed1 & 234881024) == 0) {
            $dirty6 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed1 & 1879048192) == 0) {
            $dirty6 |= ((i & 524288) == 0 && $composer3.changed(shape)) ? 536870912 : 268435456;
        }
        if (($changed2 & 14) == 0) {
            $dirty7 |= ((i & 1048576) == 0 && $composer3.changed(colors)) ? 4 : 2;
        }
        if (($dirty5 & 1533916891) == 306783378 && (1533916891 & $dirty6) == 306783378 && ($dirty7 & 11) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier2 = modifier;
            enabled2 = enabled;
            readOnly2 = readOnly;
            textStyle3 = textStyle;
            label2 = label;
            placeholder2 = placeholder;
            leadingIcon2 = leadingIcon;
            trailingIcon2 = trailingIcon;
            supportingText2 = supportingText;
            isError2 = isError;
            visualTransformation2 = visualTransformation;
            keyboardOptions2 = keyboardOptions;
            keyboardActions2 = keyboardActions;
            singleLine2 = singleLine;
            maxLines3 = maxLines;
            minLines2 = minLines;
            interactionSource3 = interactionSource;
            shape3 = shape;
            colors2 = colors;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
                boolean enabled3 = i3 != 0 ? true : enabled;
                boolean readOnly3 = i4 != 0 ? false : readOnly;
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    textStyle2 = (TextStyle) objConsume;
                    $dirty5 &= -458753;
                } else {
                    textStyle2 = textStyle;
                }
                Function2 label3 = i5 != 0 ? null : label;
                Function2 placeholder3 = i6 != 0 ? null : placeholder;
                Function2 leadingIcon3 = i7 != 0 ? null : leadingIcon;
                Function2 trailingIcon3 = i8 != 0 ? null : trailingIcon;
                Function2 supportingText3 = i9 != 0 ? null : supportingText;
                boolean isError3 = i10 != 0 ? false : isError;
                VisualTransformation visualTransformation3 = i11 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                KeyboardOptions keyboardOptions3 = i12 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                KeyboardActions keyboardActions3 = i13 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                singleLine2 = i14 != 0 ? false : singleLine;
                if ((i & 65536) != 0) {
                    maxLines2 = $dirty6 & (-3670017);
                    $dirty1 = singleLine2 ? 1 : Integer.MAX_VALUE;
                } else {
                    maxLines2 = $dirty6;
                    $dirty1 = maxLines;
                }
                minLines2 = i15 != 0 ? 1 : minLines;
                if (i16 != 0) {
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
                if ((i & 524288) != 0) {
                    shape2 = OutlinedTextFieldDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 = maxLines2 & (-1879048193);
                } else {
                    shape2 = shape;
                    $dirty2 = maxLines2;
                }
                if ((i & 1048576) != 0) {
                    modifier2 = modifier3;
                    enabled2 = enabled3;
                    interactionSource3 = interactionSource2;
                    maxLines3 = $dirty1;
                    shape3 = shape2;
                    $dirty3 = $dirty7 & (-15);
                    textStyle3 = textStyle2;
                    leadingIcon2 = leadingIcon3;
                    placeholder2 = placeholder3;
                    trailingIcon2 = trailingIcon3;
                    supportingText2 = supportingText3;
                    isError2 = isError3;
                    visualTransformation2 = visualTransformation3;
                    label2 = label3;
                    keyboardOptions2 = keyboardOptions3;
                    keyboardActions2 = keyboardActions3;
                    readOnly2 = readOnly3;
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                    $dirty = $dirty5;
                    $dirty4 = $dirty2;
                } else {
                    modifier2 = modifier3;
                    enabled2 = enabled3;
                    interactionSource3 = interactionSource2;
                    maxLines3 = $dirty1;
                    colors2 = colors;
                    shape3 = shape2;
                    $dirty3 = $dirty7;
                    textStyle3 = textStyle2;
                    leadingIcon2 = leadingIcon3;
                    placeholder2 = placeholder3;
                    trailingIcon2 = trailingIcon3;
                    supportingText2 = supportingText3;
                    isError2 = isError3;
                    visualTransformation2 = visualTransformation3;
                    label2 = label3;
                    keyboardOptions2 = keyboardOptions3;
                    keyboardActions2 = keyboardActions3;
                    readOnly2 = readOnly3;
                    $dirty = $dirty5;
                    $dirty4 = $dirty2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 65536) != 0) {
                    $dirty6 &= -3670017;
                }
                if ((i & 524288) != 0) {
                    $dirty6 &= -1879048193;
                }
                if ((i & 1048576) != 0) {
                    $dirty7 &= -15;
                }
                modifier2 = modifier;
                enabled2 = enabled;
                readOnly2 = readOnly;
                textStyle3 = textStyle;
                label2 = label;
                placeholder2 = placeholder;
                leadingIcon2 = leadingIcon;
                trailingIcon2 = trailingIcon;
                supportingText2 = supportingText;
                isError2 = isError;
                visualTransformation2 = visualTransformation;
                keyboardOptions2 = keyboardOptions;
                keyboardActions2 = keyboardActions;
                singleLine2 = singleLine;
                maxLines3 = maxLines;
                minLines2 = minLines;
                interactionSource3 = interactionSource;
                shape3 = shape;
                colors2 = colors;
                $dirty = $dirty5;
                $dirty4 = $dirty6;
                $dirty3 = $dirty7;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-989817544, $dirty, $dirty4, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:445)");
            }
            $composer2 = $composer3;
            OutlinedTextField(value, (Function1<? super TextFieldValue, Unit>) onValueChange, modifier2, enabled2, readOnly2, textStyle3, (Function2<? super Composer, ? super Integer, Unit>) label2, (Function2<? super Composer, ? super Integer, Unit>) placeholder2, (Function2<? super Composer, ? super Integer, Unit>) leadingIcon2, (Function2<? super Composer, ? super Integer, Unit>) trailingIcon2, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) supportingText2, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, singleLine2, maxLines3, minLines2, interactionSource3, shape3, colors2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | ($dirty & 1879048192), (($dirty4 << 6) & 896) | 54 | (($dirty4 << 6) & 7168) | (($dirty4 << 6) & 57344) | (($dirty4 << 6) & 458752) | (($dirty4 << 6) & 3670016) | (($dirty4 << 6) & 29360128) | (($dirty4 << 6) & 234881024) | (($dirty4 << 6) & 1879048192), (($dirty4 >> 24) & 14) | (($dirty4 >> 24) & 112) | (($dirty3 << 6) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier2;
        final boolean z = enabled2;
        final boolean z2 = readOnly2;
        final TextStyle textStyle4 = textStyle3;
        final Function2 function2 = label2;
        final Function2 function3 = placeholder2;
        final Function2 function4 = leadingIcon2;
        final Function2 function5 = trailingIcon2;
        final Function2 function6 = supportingText2;
        final boolean z3 = isError2;
        final VisualTransformation visualTransformation4 = visualTransformation2;
        final KeyboardOptions keyboardOptions4 = keyboardOptions2;
        final KeyboardActions keyboardActions4 = keyboardActions2;
        final boolean z4 = singleLine2;
        final int i17 = maxLines3;
        final int i18 = minLines2;
        final MutableInteractionSource mutableInteractionSource = interactionSource3;
        final Shape shape4 = shape3;
        final TextFieldColors textFieldColors = colors2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.10
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

            public final void invoke(Composer composer, int i19) {
                OutlinedTextFieldKt.OutlinedTextField(value, onValueChange, modifier4, z, z2, textStyle4, function2, function3, function4, function5, function6, z3, visualTransformation4, keyboardOptions4, keyboardActions4, z4, i17, i18, mutableInteractionSource, shape4, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), RecomposeScopeImplKt.updateChangedFlags($changed2), i);
            }
        });
    }

    public static final void OutlinedTextFieldLayout(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> textField, final Function3<? super Modifier, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function6, final Function2<? super Composer, ? super Integer, Unit> function7, final boolean singleLine, final float animationProgress, final Function1<? super Size, Unit> onLabelMeasured, final Function2<? super Composer, ? super Integer, Unit> container, final Function2<? super Composer, ? super Integer, Unit> function8, final PaddingValues paddingValues, Composer $composer, final int $changed, final int $changed1) {
        Function2<? super Composer, ? super Integer, Unit> function9;
        PaddingValues paddingValues2;
        float startPadding;
        float endPadding;
        String str;
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        Intrinsics.checkNotNullParameter(textField, "textField");
        Intrinsics.checkNotNullParameter(onLabelMeasured, "onLabelMeasured");
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
        Composer $composer2 = $composer.startRestartGroup(1408290209);
        ComposerKt.sourceInformation($composer2, "C(OutlinedTextFieldLayout)P(4,12,7,2,3,13,8,10,9!1,5!1,11)518@25327L239,526@25614L7,527@25626L3534:OutlinedTextField.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changedInstance(textField) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? 16384 : 8192;
        }
        if ((458752 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function5) ? 131072 : 65536;
        }
        if ((3670016 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function6) ? 1048576 : 524288;
        }
        if ((29360128 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function7) ? 8388608 : 4194304;
        }
        if ((234881024 & $changed) == 0) {
            $dirty |= $composer2.changed(singleLine) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((1879048192 & $changed) == 0) {
            $dirty |= $composer2.changed(animationProgress) ? 536870912 : 268435456;
        }
        int $dirty2 = $dirty;
        if (($changed1 & 14) == 0) {
            $dirty1 |= $composer2.changedInstance(onLabelMeasured) ? 4 : 2;
        }
        if (($changed1 & 112) == 0) {
            function9 = container;
            $dirty1 |= $composer2.changedInstance(function9) ? 32 : 16;
        } else {
            function9 = container;
        }
        if (($changed1 & 896) == 0) {
            $dirty1 |= $composer2.changedInstance(function8) ? 256 : 128;
        }
        if (($changed1 & 7168) == 0) {
            paddingValues2 = paddingValues;
            $dirty1 |= $composer2.changed(paddingValues2) ? 2048 : 1024;
        } else {
            paddingValues2 = paddingValues;
        }
        int $dirty3 = $dirty1;
        if (($dirty2 & 1533916891) != 306783378 || ($dirty3 & 5851) != 1170 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1408290209, $dirty2, $dirty3, "androidx.compose.material3.OutlinedTextFieldLayout (OutlinedTextField.kt:502)");
            }
            Object[] keys$iv = {onLabelMeasured, Boolean.valueOf(singleLine), Float.valueOf(animationProgress), paddingValues2};
            $composer2.startReplaceableGroup(-568225417);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv = false;
            int length = keys$iv.length;
            int i = 0;
            while (i < length) {
                int i2 = length;
                Object key$iv = keys$iv[i];
                invalid$iv |= $composer2.changed(key$iv);
                i++;
                length = i2;
            }
            Object value$iv$iv = $composer2.rememberedValue();
            if (invalid$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new OutlinedTextFieldMeasurePolicy(onLabelMeasured, singleLine, animationProgress, paddingValues2);
                $composer2.updateRememberedValue(value$iv$iv);
            }
            $composer2.endReplaceableGroup();
            OutlinedTextFieldMeasurePolicy measurePolicy = (OutlinedTextFieldMeasurePolicy) value$iv$iv;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume;
            int $changed$iv = ($dirty2 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)73@2855L7,74@2910L7,75@2969L7,76@2981L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv = (Density) objConsume2;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv = (LayoutDirection) objConsume3;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer2.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv = (ViewConfiguration) objConsume4;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier);
            int $changed$iv$iv = (($changed$iv << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, density$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, layoutDirection$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, viewConfiguration$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i3 = ($changed$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1116455022, "C530@25695L11,600@28180L228:OutlinedTextField.kt#uh7d8r");
            function9.invoke($composer2, Integer.valueOf(($dirty3 >> 3) & 14));
            $composer2.startReplaceableGroup(1116455047);
            ComposerKt.sourceInformation($composer2, "533@25759L219");
            if (function4 != null) {
                Modifier modifier$iv = LayoutIdKt.layoutId(Modifier.INSTANCE, "Leading").then(TextFieldImplKt.getIconDefaultSizeModifier());
                Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume5 = $composer2.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density$iv$iv = (Density) objConsume5;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume6 = $composer2.consume(localLayoutDirection3);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume6;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume7 = $composer2.consume(localViewConfiguration2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume7;
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv);
                int $changed$iv$iv$iv = ((((48 << 3) & 112) << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor2);
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
                function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i4 = ($changed$iv$iv$iv >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i5 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1828313841, "C537@25951L9:OutlinedTextField.kt#uh7d8r");
                function4.invoke($composer2, Integer.valueOf(($dirty2 >> 12) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
            }
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(1116455332);
            ComposerKt.sourceInformation($composer2, "541@26045L221");
            if (function5 != null) {
                Modifier modifier$iv2 = LayoutIdKt.layoutId(Modifier.INSTANCE, "Trailing").then(TextFieldImplKt.getIconDefaultSizeModifier());
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume8 = $composer2.consume(localDensity3);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density$iv$iv2 = (Density) objConsume8;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume9 = $composer2.consume(localLayoutDirection4);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume9;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration3 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume10 = $composer2.consume(localViewConfiguration3);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume10;
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf3 = LayoutKt.materializerOf(modifier$iv2);
                int $changed$iv$iv$iv2 = ((((48 << 3) & 112) << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor3);
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
                function3MaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i6 = ($changed$iv$iv$iv2 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i7 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1828313554, "C545@26238L10:OutlinedTextField.kt#uh7d8r");
                function5.invoke($composer2, Integer.valueOf(($dirty2 >> 15) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
            }
            $composer2.endReplaceableGroup();
            float startTextFieldPadding = PaddingKt.calculateStartPadding(paddingValues2, layoutDirection);
            float endTextFieldPadding = PaddingKt.calculateEndPadding(paddingValues2, layoutDirection);
            if (function4 != null) {
                float other$iv = TextFieldImplKt.getHorizontalIconPadding();
                float other$iv2 = Dp.m5274constructorimpl(startTextFieldPadding - other$iv);
                float minimumValue$iv = Dp.m5274constructorimpl(0);
                float $this$coerceAtLeast_u2dYgX7TsA$iv = Dp.m5274constructorimpl(RangesKt.coerceAtLeast(other$iv2, minimumValue$iv));
                startPadding = $this$coerceAtLeast_u2dYgX7TsA$iv;
            } else {
                startPadding = startTextFieldPadding;
            }
            if (function5 != null) {
                float other$iv3 = TextFieldImplKt.getHorizontalIconPadding();
                float other$iv4 = Dp.m5274constructorimpl(endTextFieldPadding - other$iv3);
                float minimumValue$iv2 = Dp.m5274constructorimpl(0);
                float $this$coerceAtLeast_u2dYgX7TsA$iv2 = Dp.m5274constructorimpl(RangesKt.coerceAtLeast(other$iv4, minimumValue$iv2));
                endPadding = $this$coerceAtLeast_u2dYgX7TsA$iv2;
            } else {
                endPadding = endTextFieldPadding;
            }
            $composer2.startReplaceableGroup(1116456222);
            ComposerKt.sourceInformation($composer2, "564@26933L334");
            if (function6 != null) {
                Modifier modifier$iv3 = PaddingKt.m491paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m522heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.PrefixId), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), startPadding, 0.0f, TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, 10, null);
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv3 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv3, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                $composer2.startReplaceableGroup(-1323940314);
                str = "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh";
                ComposerKt.sourceInformation($composer2, str);
                ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume11 = $composer2.consume(localDensity4);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density$iv$iv3 = (Density) objConsume11;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection5 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume12 = $composer2.consume(localLayoutDirection5);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LayoutDirection layoutDirection$iv$iv3 = (LayoutDirection) objConsume12;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration4 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume13 = $composer2.consume(localViewConfiguration4);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ViewConfiguration viewConfiguration$iv$iv3 = (ViewConfiguration) objConsume13;
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf4 = LayoutKt.materializerOf(modifier$iv3);
                int $changed$iv$iv$iv3 = ((((0 << 3) & 112) << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor4);
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
                function3MaterializerOf4.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i8 = ($changed$iv$iv$iv3 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i9 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1828312551, "C571@27241L8:OutlinedTextField.kt#uh7d8r");
                function6.invoke($composer2, Integer.valueOf(($dirty2 >> 18) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
            } else {
                str = r5;
            }
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(1116456621);
            ComposerKt.sourceInformation($composer2, "575@27332L332");
            if (function7 != null) {
                Modifier modifier$iv4 = PaddingKt.m491paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m522heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.SuffixId), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, endPadding, 0.0f, 10, null);
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv4 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv4, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, str);
                ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume14 = $composer2.consume(localDensity5);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density$iv$iv4 = (Density) objConsume14;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection6 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume15 = $composer2.consume(localLayoutDirection6);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LayoutDirection layoutDirection$iv$iv4 = (LayoutDirection) objConsume15;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration5 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume16 = $composer2.consume(localViewConfiguration5);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ViewConfiguration viewConfiguration$iv$iv4 = (ViewConfiguration) objConsume16;
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf5 = LayoutKt.materializerOf(modifier$iv4);
                int $changed$iv$iv$iv4 = ((((0 << 3) & 112) << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor5);
                } else {
                    $composer2.useNode();
                }
                $composer2.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv4 = Updater.m2603constructorimpl($composer2);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv4, density$iv$iv4, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv4, layoutDirection$iv$iv4, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv4, viewConfiguration$iv$iv4, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer2.enableReusing();
                function3MaterializerOf5.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv4 >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i10 = ($changed$iv$iv$iv4 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                int i11 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1828312154, "C582@27638L8:OutlinedTextField.kt#uh7d8r");
                function7.invoke($composer2, Integer.valueOf(($dirty2 >> 21) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
            }
            $composer2.endReplaceableGroup();
            Modifier textPadding = PaddingKt.m491paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m522heightInVpY3zN4$default(Modifier.INSTANCE, TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), function6 == null ? startPadding : Dp.m5274constructorimpl(0), 0.0f, function7 == null ? endPadding : Dp.m5274constructorimpl(0), 0.0f, 10, null);
            $composer2.startReplaceableGroup(1116457331);
            ComposerKt.sourceInformation($composer2, "595@28047L105");
            if (function3 != null) {
                function3.invoke(LayoutIdKt.layoutId(Modifier.INSTANCE, "Hint").then(textPadding), $composer2, Integer.valueOf(($dirty2 >> 3) & 112));
            }
            $composer2.endReplaceableGroup();
            Modifier modifier$iv5 = LayoutIdKt.layoutId(Modifier.INSTANCE, "TextField").then(textPadding);
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv5 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv5 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv5, true, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, str);
            ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume17 = $composer2.consume(localDensity6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv$iv5 = (Density) objConsume17;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection7 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume18 = $composer2.consume(localLayoutDirection7);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv$iv5 = (LayoutDirection) objConsume18;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration6 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume19 = $composer2.consume(localViewConfiguration6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv$iv5 = (ViewConfiguration) objConsume19;
            Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf6 = LayoutKt.materializerOf(modifier$iv5);
            int $changed$iv$iv$iv5 = ((((384 << 3) & 112) << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor6);
            } else {
                $composer2.useNode();
            }
            $composer2.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv5 = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv5, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv5, density$iv$iv5, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv5, layoutDirection$iv$iv5, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv5, viewConfiguration$iv$iv5, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer2.enableReusing();
            function3MaterializerOf6.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv5 >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i12 = ($changed$iv$iv$iv5 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
            int i13 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1828311409, "C606@28383L11:OutlinedTextField.kt#uh7d8r");
            textField.invoke($composer2, Integer.valueOf(($dirty2 >> 3) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(1116457749);
            ComposerKt.sourceInformation($composer2, "610@28459L237");
            if (function2 != null) {
                Modifier modifier$iv6 = LayoutIdKt.layoutId(SizeKt.wrapContentHeight$default(SizeKt.m522heightInVpY3zN4$default(Modifier.INSTANCE, DpKt.m5317lerpMdfbLM(TextFieldImplKt.getMinTextLineHeight(), TextFieldImplKt.getMinFocusedLabelLineHeight(), animationProgress), 0.0f, 2, null), null, false, 3, null), "Label");
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv6 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv6 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv6, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, str);
                ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume20 = $composer2.consume(localDensity7);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density$iv$iv6 = (Density) objConsume20;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection8 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume21 = $composer2.consume(localLayoutDirection8);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LayoutDirection layoutDirection$iv$iv6 = (LayoutDirection) objConsume21;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration7 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume22 = $composer2.consume(localViewConfiguration7);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ViewConfiguration viewConfiguration$iv$iv6 = (ViewConfiguration) objConsume22;
                Function0<ComposeUiNode> constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf7 = LayoutKt.materializerOf(modifier$iv6);
                int $changed$iv$iv$iv6 = ((((0 << 3) & 112) << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor7);
                } else {
                    $composer2.useNode();
                }
                $composer2.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv6 = Updater.m2603constructorimpl($composer2);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv6, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv6, density$iv$iv6, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv6, layoutDirection$iv$iv6, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv6, viewConfiguration$iv$iv6, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer2.enableReusing();
                function3MaterializerOf7.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv6 >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i14 = ($changed$iv$iv$iv6 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                int i15 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1828311105, "C614@28687L7:OutlinedTextField.kt#uh7d8r");
                function2.invoke($composer2, Integer.valueOf(($dirty2 >> 9) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
            }
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(-2058764510);
            ComposerKt.sourceInformation($composer2, "619@28822L269");
            if (function8 != null) {
                Modifier modifier$iv7 = PaddingKt.padding(SizeKt.wrapContentHeight$default(SizeKt.m522heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.SupportingId), TextFieldImplKt.getMinSupportingTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldDefaults.m1850supportingTextPaddinga9UjIt4$material3_release$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null));
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv7 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv7 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv7, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, str);
                ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume23 = $composer2.consume(localDensity8);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density$iv$iv7 = (Density) objConsume23;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection9 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume24 = $composer2.consume(localLayoutDirection9);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                LayoutDirection layoutDirection$iv$iv7 = (LayoutDirection) objConsume24;
                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration8 = CompositionLocalsKt.getLocalViewConfiguration();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "C:CompositionLocal.kt#9igjgp");
                Object objConsume25 = $composer2.consume(localViewConfiguration8);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ViewConfiguration viewConfiguration$iv$iv7 = (ViewConfiguration) objConsume25;
                Function0<ComposeUiNode> constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf8 = LayoutKt.materializerOf(modifier$iv7);
                int $changed$iv$iv$iv7 = ((((0 << 3) & 112) << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor8);
                } else {
                    $composer2.useNode();
                }
                $composer2.disableReusing();
                Composer $this$Layout_u24lambda_u2d0$iv$iv7 = Updater.m2603constructorimpl($composer2);
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv7, measurePolicy$iv7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv7, density$iv$iv7, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv7, layoutDirection$iv$iv7, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv7, viewConfiguration$iv$iv7, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                $composer2.enableReusing();
                function3MaterializerOf8.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv7 >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i16 = ($changed$iv$iv$iv7 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                int i17 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1828310715, "C624@29077L12:OutlinedTextField.kt#uh7d8r");
                function8.invoke($composer2, Integer.valueOf(($dirty3 >> 6) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
            }
            $composer2.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
            $dirty3 = $dirty3;
            $composer2 = $composer2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextFieldLayout.2
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

            public final void invoke(Composer composer, int i18) {
                OutlinedTextFieldKt.OutlinedTextFieldLayout(modifier, textField, function3, function2, function4, function5, function6, function7, singleLine, animationProgress, onLabelMeasured, container, function8, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateWidth-DHJA7U0, reason: not valid java name */
    public static final int m1658calculateWidthDHJA7U0(int leadingPlaceableWidth, int trailingPlaceableWidth, int prefixPlaceableWidth, int suffixPlaceableWidth, int textFieldPlaceableWidth, int labelPlaceableWidth, int placeholderPlaceableWidth, boolean isLabelInMiddleSection, long constraints, float density, PaddingValues paddingValues) {
        int affixTotalWidth = prefixPlaceableWidth + suffixPlaceableWidth;
        int focusedLabelWidth = 0;
        int middleSection = Math.max(textFieldPlaceableWidth + affixTotalWidth, Math.max(placeholderPlaceableWidth + affixTotalWidth, isLabelInMiddleSection ? labelPlaceableWidth : 0));
        int wrappedWidth = leadingPlaceableWidth + middleSection + trailingPlaceableWidth;
        if (!isLabelInMiddleSection) {
            float arg0$iv = paddingValues.mo437calculateLeftPaddingu2uoSUM(LayoutDirection.Ltr);
            float other$iv = paddingValues.mo438calculateRightPaddingu2uoSUM(LayoutDirection.Ltr);
            float labelHorizontalPadding = Dp.m5274constructorimpl(arg0$iv + other$iv) * density;
            focusedLabelWidth = labelPlaceableWidth + MathKt.roundToInt(labelHorizontalPadding);
        }
        return Math.max(wrappedWidth, Math.max(focusedLabelWidth, Constraints.m5220getMinWidthimpl(constraints)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateHeight-DHJA7U0, reason: not valid java name */
    public static final int m1657calculateHeightDHJA7U0(int leadingPlaceableHeight, int trailingPlaceableHeight, int prefixPlaceableHeight, int suffixPlaceableHeight, int textFieldPlaceableHeight, int labelPlaceableHeight, int placeholderPlaceableHeight, int supportingPlaceableHeight, long constraints, float density, PaddingValues paddingValues) {
        int inputFieldHeight = Math.max(textFieldPlaceableHeight, placeholderPlaceableHeight);
        float topPadding = paddingValues.getTop() * density;
        float bottomPadding = paddingValues.getBottom() * density;
        float middleSectionHeight = inputFieldHeight + bottomPadding + Math.max(topPadding, labelPlaceableHeight / 2.0f);
        return Math.max(Constraints.m5219getMinHeightimpl(constraints), ComparisonsKt.maxOf(leadingPlaceableHeight, trailingPlaceableHeight, prefixPlaceableHeight, suffixPlaceableHeight, MathKt.roundToInt(middleSectionHeight)) + supportingPlaceableHeight);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void place(Placeable.PlacementScope $this$place, int totalHeight, int width, Placeable leadingPlaceable, Placeable trailingPlaceable, Placeable prefixPlaceable, Placeable suffixPlaceable, Placeable textFieldPlaceable, Placeable labelPlaceable, Placeable placeholderPlaceable, Placeable containerPlaceable, Placeable supportingPlaceable, float animationProgress, boolean singleLine, float density, LayoutDirection layoutDirection, PaddingValues paddingValues) {
        int iAlign;
        float fWidthOrZero;
        Placeable.PlacementScope.m4284place70tqf50$default($this$place, containerPlaceable, IntOffset.INSTANCE.m5402getZeronOccac(), 0.0f, 2, null);
        int height = totalHeight - TextFieldImplKt.heightOrZero(supportingPlaceable);
        int topPadding = MathKt.roundToInt(paddingValues.getTop() * density);
        int startPadding = MathKt.roundToInt(PaddingKt.calculateStartPadding(paddingValues, layoutDirection) * density);
        float iconPadding = TextFieldImplKt.getHorizontalIconPadding() * density;
        if (leadingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, leadingPlaceable, 0, Alignment.INSTANCE.getCenterVertically().align(leadingPlaceable.getHeight(), height), 0.0f, 4, null);
        }
        if (trailingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, trailingPlaceable, width - trailingPlaceable.getWidth(), Alignment.INSTANCE.getCenterVertically().align(trailingPlaceable.getHeight(), height), 0.0f, 4, null);
        }
        if (labelPlaceable != null) {
            if (singleLine) {
                iAlign = Alignment.INSTANCE.getCenterVertically().align(labelPlaceable.getHeight(), height);
            } else {
                iAlign = topPadding;
            }
            int startPositionY = iAlign;
            int positionY = MathHelpersKt.lerp(startPositionY, -(labelPlaceable.getHeight() / 2), animationProgress);
            if (leadingPlaceable == null) {
                fWidthOrZero = 0.0f;
            } else {
                fWidthOrZero = (TextFieldImplKt.widthOrZero(leadingPlaceable) - iconPadding) * (1 - animationProgress);
            }
            int positionX = MathKt.roundToInt(fWidthOrZero) + startPadding;
            Placeable.PlacementScope.placeRelative$default($this$place, labelPlaceable, positionX, positionY, 0.0f, 4, null);
        }
        if (prefixPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, prefixPlaceable, TextFieldImplKt.widthOrZero(leadingPlaceable), place$calculateVerticalPosition(singleLine, height, topPadding, labelPlaceable, prefixPlaceable), 0.0f, 4, null);
        }
        if (suffixPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, suffixPlaceable, (width - TextFieldImplKt.widthOrZero(trailingPlaceable)) - suffixPlaceable.getWidth(), place$calculateVerticalPosition(singleLine, height, topPadding, labelPlaceable, suffixPlaceable), 0.0f, 4, null);
        }
        int textHorizontalPosition = TextFieldImplKt.widthOrZero(leadingPlaceable) + TextFieldImplKt.widthOrZero(prefixPlaceable);
        Placeable.PlacementScope.placeRelative$default($this$place, textFieldPlaceable, textHorizontalPosition, place$calculateVerticalPosition(singleLine, height, topPadding, labelPlaceable, textFieldPlaceable), 0.0f, 4, null);
        if (placeholderPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, placeholderPlaceable, textHorizontalPosition, place$calculateVerticalPosition(singleLine, height, topPadding, labelPlaceable, placeholderPlaceable), 0.0f, 4, null);
        }
        if (supportingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$place, supportingPlaceable, 0, height, 0.0f, 4, null);
        }
    }

    private static final int place$calculateVerticalPosition(boolean $singleLine, int height, int topPadding, Placeable $labelPlaceable, Placeable placeable) {
        int iAlign;
        if ($singleLine) {
            iAlign = Alignment.INSTANCE.getCenterVertically().align(placeable.getHeight(), height);
        } else {
            iAlign = topPadding;
        }
        return Math.max(iAlign, TextFieldImplKt.heightOrZero($labelPlaceable) / 2);
    }

    /* JADX INFO: renamed from: outlineCutout-12SF9DM, reason: not valid java name */
    public static final Modifier m1659outlineCutout12SF9DM(Modifier outlineCutout, final long labelSize, final PaddingValues paddingValues) {
        Intrinsics.checkNotNullParameter(outlineCutout, "$this$outlineCutout");
        Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
        return DrawModifierKt.drawWithContent(outlineCutout, new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt$outlineCutout$1

            /* JADX INFO: compiled from: OutlinedTextField.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[LayoutDirection.values().length];
                    try {
                        iArr[LayoutDirection.Rtl.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope drawWithContent) {
                float right;
                Intrinsics.checkNotNullParameter(drawWithContent, "$this$drawWithContent");
                float labelWidth = Size.m2800getWidthimpl(labelSize);
                if (labelWidth > 0.0f) {
                    float innerPadding = drawWithContent.mo327toPx0680j_4(OutlinedTextFieldKt.OutlinedTextFieldInnerPadding);
                    float leftLtr = drawWithContent.mo327toPx0680j_4(paddingValues.mo437calculateLeftPaddingu2uoSUM(drawWithContent.getLayoutDirection())) - innerPadding;
                    float f = 2;
                    float rightLtr = leftLtr + labelWidth + (f * innerPadding);
                    float left = WhenMappings.$EnumSwitchMapping$0[drawWithContent.getLayoutDirection().ordinal()] == 1 ? Size.m2800getWidthimpl(drawWithContent.mo3442getSizeNHjbRc()) - rightLtr : RangesKt.coerceAtLeast(leftLtr, 0.0f);
                    if (WhenMappings.$EnumSwitchMapping$0[drawWithContent.getLayoutDirection().ordinal()] == 1) {
                        right = Size.m2800getWidthimpl(drawWithContent.mo3442getSizeNHjbRc()) - RangesKt.coerceAtLeast(leftLtr, 0.0f);
                    } else {
                        right = rightLtr;
                    }
                    float labelHeight = Size.m2797getHeightimpl(labelSize);
                    ContentDrawScope $this$clipRect_u2drOu3jXo$iv = drawWithContent;
                    float top$iv = (-labelHeight) / f;
                    float bottom$iv = labelHeight / f;
                    int clipOp$iv = ClipOp.INSTANCE.m2959getDifferencertfAjoo();
                    DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$clipRect_u2drOu3jXo$iv.getDrawContext();
                    long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo3448getSizeNHjbRc();
                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
                    DrawTransform $this$clipRect_rOu3jXo_u24lambda_u244$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
                    $this$clipRect_rOu3jXo_u24lambda_u244$iv.mo3451clipRectN_I0leg(left, top$iv, right, bottom$iv, clipOp$iv);
                    drawWithContent.drawContent();
                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u246$iv$iv.mo3449setSizeuvyYCjk(previousSize$iv$iv);
                    return;
                }
                drawWithContent.drawContent();
            }
        });
    }

    public static final float getOutlinedTextFieldTopPadding() {
        return OutlinedTextFieldTopPadding;
    }
}
