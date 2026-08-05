package androidx.compose.foundation.text;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationConstants;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.relocation.BringIntoViewRequester;
import androidx.compose.foundation.relocation.BringIntoViewRequesterKt;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.SelectionHandleInfo;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.SimpleLayoutKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteAllCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.FinishComposingTextCommand;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: CoreTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aî\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u001323\b\u0002\u0010\u001d\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001fH\u0001¢\u0006\u0002\u0010#\u001a0\u0010$\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&2\u0011\u0010'\u001a\r\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u001fH\u0003¢\u0006\u0002\u0010(\u001a\u001d\u0010)\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&2\u0006\u0010*\u001a\u00020\u0013H\u0003¢\u0006\u0002\u0010+\u001a\u0015\u0010,\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\u0010-\u001a \u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00101\u001a\u000202H\u0002\u001a0\u00103\u001a\u00020\u00012\u0006\u00104\u001a\u0002052\u0006\u0010/\u001a\u0002002\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00101\u001a\u000202H\u0002\u001a\u0010\u00106\u001a\u00020\u00012\u0006\u0010/\u001a\u000200H\u0002\u001a \u00107\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u0013H\u0002\u001a5\u0010;\u001a\u00020\u0001*\u00020<2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\r2\u0006\u00101\u001a\u000202H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010@\u001a\u001c\u0010A\u001a\u00020\u0007*\u00020\u00072\u0006\u0010/\u001a\u0002002\u0006\u0010%\u001a\u00020&H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006B"}, d2 = {"CoreTextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "onTextLayout", "Landroidx/compose/ui/text/TextLayoutResult;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "softWrap", "", "maxLines", "", "minLines", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "enabled", "readOnly", "decorationBox", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "innerTextField", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;ZIILandroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/foundation/text/KeyboardActions;ZZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "CoreTextFieldRootBox", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "content", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "SelectionToolbarAndHandles", "show", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;ZLandroidx/compose/runtime/Composer;I)V", "TextFieldCursorHandle", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/runtime/Composer;I)V", "notifyFocusedRect", "state", "Landroidx/compose/foundation/text/TextFieldState;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "notifyTextInputServiceOnFocusChange", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "onBlur", "tapToFocus", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "allowKeyboard", "bringSelectionEndIntoView", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "textDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "textLayoutResult", "(Landroidx/compose/foundation/relocation/BringIntoViewRequester;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/foundation/text/TextDelegate;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/text/input/OffsetMapping;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "previewKeyEventToDeselectOnBack", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CoreTextFieldKt {
    public static final void CoreTextField(final TextFieldValue value, final Function1<? super TextFieldValue, Unit> onValueChange, Modifier modifier, TextStyle textStyle, VisualTransformation visualTransformation, Function1<? super TextLayoutResult, Unit> function1, MutableInteractionSource interactionSource, Brush cursorBrush, boolean softWrap, int maxLines, int minLines, ImeOptions imeOptions, KeyboardActions keyboardActions, boolean enabled, boolean readOnly, Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        ImeOptions imeOptions2;
        TextStyle textStyle2;
        Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function3M769getLambda1$foundation_release;
        boolean softWrap2;
        Modifier modifier2;
        Brush cursorBrush2;
        int minLines2;
        KeyboardActions keyboardActions2;
        boolean enabled2;
        boolean readOnly2;
        ImeOptions imeOptions3;
        VisualTransformation visualTransformation2;
        Function1<? super TextLayoutResult, Unit> function2;
        MutableInteractionSource interactionSource2;
        int $dirty1;
        int maxLines2;
        Object value$iv$iv;
        Object value$iv$iv2;
        MutableInteractionSource interactionSource3;
        TransformedText transformedTextM822applyCompositionDecoration72CqOWE;
        Object value$iv$iv$iv;
        Object value$iv$iv3;
        Modifier modifierPointerHoverIcon$default;
        final int maxLines3;
        ImeOptions imeOptions4;
        boolean enabled3;
        final VisualTransformation visualTransformation3;
        Composer $composer2;
        Modifier modifier3;
        MutableInteractionSource interactionSource4;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(onValueChange, "onValueChange");
        Composer $composer3 = $composer.startRestartGroup(-958708118);
        ComposerKt.sourceInformation($composer3, "C(CoreTextField)P(14,10,8,13,15,9,4!1,12,6,7,3,5,2,11)202@11283L29,206@11481L7,207@11520L7,208@11581L7,209@11649L7,210@11714L7,218@12023L42,215@11930L135,221@12106L268,233@12679L21,234@12717L324,262@13440L26,265@13526L51,271@13829L7,272@13880L7,273@13937L7,277@14048L24,278@14106L37,545@24922L86,549@25014L515,597@26765L4554:CoreTextField.kt#423gt5");
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
            $dirty |= $composer3.changedInstance(onValueChange) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(textStyle) ? 2048 : 1024;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 131072 : 65536;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty |= $composer3.changed(interactionSource) ? 1048576 : 524288;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty |= $composer3.changed(cursorBrush) ? 8388608 : 4194304;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty |= $composer3.changed(softWrap) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty |= $composer3.changed(maxLines) ? 536870912 : 268435456;
        }
        int i10 = i & 1024;
        if (i10 != 0) {
            $dirty2 |= 6;
        } else if (($changed1 & 14) == 0) {
            $dirty2 |= $composer3.changed(minLines) ? 4 : 2;
        }
        if (($changed1 & 112) == 0) {
            $dirty2 |= ((i & 2048) == 0 && $composer3.changed(imeOptions)) ? 32 : 16;
        }
        int i11 = i & 4096;
        if (i11 != 0) {
            $dirty2 |= 384;
        } else if (($changed1 & 896) == 0) {
            $dirty2 |= $composer3.changed(keyboardActions) ? 256 : 128;
        }
        int i12 = i & 8192;
        if (i12 != 0) {
            $dirty2 |= 3072;
        } else if (($changed1 & 7168) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 2048 : 1024;
        }
        int i13 = i & 16384;
        if (i13 != 0) {
            $dirty2 |= 24576;
        } else if (($changed1 & 57344) == 0) {
            $dirty2 |= $composer3.changed(readOnly) ? 16384 : 8192;
        }
        int i14 = i & 32768;
        if (i14 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & 458752) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 131072 : 65536;
        }
        if (($dirty & 1533916891) == 306783378 && (374491 & $dirty2) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier;
            textStyle2 = textStyle;
            visualTransformation3 = visualTransformation;
            function2 = function1;
            interactionSource4 = interactionSource;
            cursorBrush2 = cursorBrush;
            softWrap2 = softWrap;
            maxLines3 = maxLines;
            minLines2 = minLines;
            imeOptions4 = imeOptions;
            keyboardActions2 = keyboardActions;
            enabled3 = enabled;
            readOnly2 = readOnly;
            function3M769getLambda1$foundation_release = function3;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                TextStyle textStyle3 = i3 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle;
                VisualTransformation visualTransformation4 = i4 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                AnonymousClass1 anonymousClass1 = i5 != 0 ? new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                    }
                } : function1;
                MutableInteractionSource interactionSource5 = i6 != 0 ? null : interactionSource;
                SolidColor cursorBrush3 = i7 != 0 ? new SolidColor(Color.INSTANCE.m3007getUnspecified0d7_KjU(), null) : cursorBrush;
                boolean softWrap3 = i8 != 0 ? true : softWrap;
                int maxLines4 = i9 != 0 ? Integer.MAX_VALUE : maxLines;
                int minLines3 = i10 != 0 ? 1 : minLines;
                if ((i & 2048) != 0) {
                    imeOptions2 = ImeOptions.INSTANCE.getDefault();
                    $dirty2 &= -113;
                } else {
                    imeOptions2 = imeOptions;
                }
                KeyboardActions keyboardActions3 = i11 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                boolean enabled4 = i12 != 0 ? true : enabled;
                boolean readOnly3 = i13 != 0 ? false : readOnly;
                if (i14 != 0) {
                    textStyle2 = textStyle3;
                    softWrap2 = softWrap3;
                    cursorBrush2 = cursorBrush3;
                    minLines2 = minLines3;
                    keyboardActions2 = keyboardActions3;
                    enabled2 = enabled4;
                    readOnly2 = readOnly3;
                    imeOptions3 = imeOptions2;
                    function3M769getLambda1$foundation_release = ComposableSingletons$CoreTextFieldKt.INSTANCE.m769getLambda1$foundation_release();
                    visualTransformation2 = visualTransformation4;
                    function2 = anonymousClass1;
                    interactionSource2 = interactionSource5;
                    $dirty1 = $dirty2;
                    modifier2 = modifier4;
                    maxLines2 = maxLines4;
                } else {
                    textStyle2 = textStyle3;
                    function3M769getLambda1$foundation_release = function3;
                    softWrap2 = softWrap3;
                    modifier2 = modifier4;
                    cursorBrush2 = cursorBrush3;
                    minLines2 = minLines3;
                    keyboardActions2 = keyboardActions3;
                    enabled2 = enabled4;
                    readOnly2 = readOnly3;
                    imeOptions3 = imeOptions2;
                    visualTransformation2 = visualTransformation4;
                    function2 = anonymousClass1;
                    interactionSource2 = interactionSource5;
                    $dirty1 = $dirty2;
                    maxLines2 = maxLines4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2048) != 0) {
                    $dirty2 &= -113;
                }
                modifier2 = modifier;
                textStyle2 = textStyle;
                visualTransformation2 = visualTransformation;
                function2 = function1;
                interactionSource2 = interactionSource;
                cursorBrush2 = cursorBrush;
                softWrap2 = softWrap;
                maxLines2 = maxLines;
                minLines2 = minLines;
                imeOptions3 = imeOptions;
                keyboardActions2 = keyboardActions;
                enabled2 = enabled;
                readOnly2 = readOnly;
                function3M769getLambda1$foundation_release = function3;
                $dirty1 = $dirty2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-958708118, $dirty, $dirty1, "androidx.compose.foundation.text.CoreTextField (CoreTextField.kt:183)");
            }
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new FocusRequester();
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer3.endReplaceableGroup();
            final FocusRequester focusRequester = (FocusRequester) value$iv$iv;
            ProvidableCompositionLocal<TextInputService> localTextInputService = CompositionLocalsKt.getLocalTextInputService();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localTextInputService);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final TextInputService textInputService = (TextInputService) objConsume;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            final int $dirty3 = $dirty1;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Density density = (Density) objConsume2;
            ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver = CompositionLocalsKt.getLocalFontFamilyResolver();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localFontFamilyResolver);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            FontFamily.Resolver fontFamilyResolver = (FontFamily.Resolver) objConsume3;
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer3.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            long selectionBackgroundColor = ((SelectionColors) objConsume4).getSelectionBackgroundColor();
            ProvidableCompositionLocal<FocusManager> localFocusManager = CompositionLocalsKt.getLocalFocusManager();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume5 = $composer3.consume(localFocusManager);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            FocusManager focusManager = (FocusManager) objConsume5;
            boolean singleLine = maxLines2 == 1 && !softWrap2 && imeOptions3.getSingleLine();
            final Orientation orientation = singleLine ? Orientation.Horizontal : Orientation.Vertical;
            int maxLines5 = maxLines2;
            Object[] objArr = {orientation};
            Saver<TextFieldScrollerPosition, Object> saver = TextFieldScrollerPosition.INSTANCE.getSaver();
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer3.changed(orientation);
            Modifier modifier5 = modifier2;
            Object it$iv$iv2 = $composer3.rememberedValue();
            if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = (Function0) new Function0<TextFieldScrollerPosition>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$scrollerPosition$1$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final TextFieldScrollerPosition invoke() {
                        return new TextFieldScrollerPosition(orientation, 0.0f, 2, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer3.endReplaceableGroup();
            final TextFieldScrollerPosition scrollerPosition = (TextFieldScrollerPosition) RememberSaveableKt.m2617rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv$iv2, $composer3, 72, 4);
            int i15 = ($dirty & 14) | (($dirty >> 9) & 112);
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean invalid$iv$iv2 = $composer3.changed(value) | $composer3.changed(visualTransformation2);
            Object value$iv$iv4 = $composer3.rememberedValue();
            if (invalid$iv$iv2 || value$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                TransformedText transformed = ValidatingOffsetMappingKt.filterWithValidation(visualTransformation2, value.getText());
                TextRange composition = value.getComposition();
                if (composition != null) {
                    interactionSource3 = interactionSource2;
                    long it = composition.getPackedValue();
                    transformedTextM822applyCompositionDecoration72CqOWE = TextFieldDelegate.INSTANCE.m822applyCompositionDecoration72CqOWE(it, transformed);
                    if (transformedTextM822applyCompositionDecoration72CqOWE == null) {
                    }
                    value$iv$iv4 = transformedTextM822applyCompositionDecoration72CqOWE;
                    $composer3.updateRememberedValue(value$iv$iv4);
                } else {
                    interactionSource3 = interactionSource2;
                }
                transformedTextM822applyCompositionDecoration72CqOWE = transformed;
                value$iv$iv4 = transformedTextM822applyCompositionDecoration72CqOWE;
                $composer3.updateRememberedValue(value$iv$iv4);
            } else {
                interactionSource3 = interactionSource2;
            }
            $composer3.endReplaceableGroup();
            final TransformedText transformedText = (TransformedText) value$iv$iv4;
            AnnotatedString visualText = transformedText.getText();
            final OffsetMapping offsetMapping = transformedText.getOffsetMapping();
            RecomposeScope scope = ComposablesKt.getCurrentRecomposeScope($composer3, 0);
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object value$iv$iv5 = $composer3.rememberedValue();
            if (value$iv$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv5 = new TextFieldState(new TextDelegate(visualText, textStyle2, 0, 0, softWrap2, 0, density, fontFamilyResolver, null, AnimationConstants.DefaultDurationMillis, null), scope);
                $composer3.updateRememberedValue(value$iv$iv5);
            }
            $composer3.endReplaceableGroup();
            final TextFieldState state = (TextFieldState) value$iv$iv5;
            state.m844updatefnh65Uc(value.getText(), visualText, textStyle2, softWrap2, density, fontFamilyResolver, onValueChange, keyboardActions2, focusManager, selectionBackgroundColor);
            state.getProcessor().reset(value, state.getInputSession());
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object value$iv$iv6 = $composer3.rememberedValue();
            if (value$iv$iv6 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv6 = new UndoManager(0, 1, null);
                $composer3.updateRememberedValue(value$iv$iv6);
            }
            $composer3.endReplaceableGroup();
            UndoManager undoManager = (UndoManager) value$iv$iv6;
            UndoManager.snapshotIfNeeded$default(undoManager, value, 0L, 2, null);
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object value$iv$iv7 = $composer3.rememberedValue();
            if (value$iv$iv7 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv7 = new TextFieldSelectionManager(undoManager);
                $composer3.updateRememberedValue(value$iv$iv7);
            }
            $composer3.endReplaceableGroup();
            final TextFieldSelectionManager manager = (TextFieldSelectionManager) value$iv$iv7;
            manager.setOffsetMapping$foundation_release(offsetMapping);
            manager.setVisualTransformation$foundation_release(visualTransformation2);
            manager.setOnValueChange$foundation_release(state.getOnValueChange());
            manager.setState$foundation_release(state);
            manager.setValue$foundation_release(value);
            ProvidableCompositionLocal<ClipboardManager> localClipboardManager = CompositionLocalsKt.getLocalClipboardManager();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer3.consume(localClipboardManager);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            manager.setClipboardManager$foundation_release((ClipboardManager) objConsume6);
            ProvidableCompositionLocal<TextToolbar> localTextToolbar = CompositionLocalsKt.getLocalTextToolbar();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume7 = $composer3.consume(localTextToolbar);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            manager.setTextToolbar((TextToolbar) objConsume7);
            ProvidableCompositionLocal<HapticFeedback> localHapticFeedback = CompositionLocalsKt.getLocalHapticFeedback();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume8 = $composer3.consume(localHapticFeedback);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            manager.setHapticFeedBack((HapticFeedback) objConsume8);
            manager.setFocusRequester(focusRequester);
            manager.setEditable(!readOnly2);
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv$iv);
            } else {
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer3.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope coroutineScope = wrapper$iv.getCoroutineScope();
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv3 = $composer3.rememberedValue();
            if (it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv3 = BringIntoViewRequesterKt.BringIntoViewRequester();
                $composer3.updateRememberedValue(value$iv$iv3);
            } else {
                value$iv$iv3 = it$iv$iv3;
            }
            $composer3.endReplaceableGroup();
            final BringIntoViewRequester bringIntoViewRequester = (BringIntoViewRequester) value$iv$iv3;
            final ImeOptions imeOptions5 = imeOptions3;
            MutableInteractionSource interactionSource6 = interactionSource3;
            Modifier focusModifier = TextFieldGestureModifiersKt.textFieldFocusModifier(Modifier.INSTANCE, enabled2, focusRequester, interactionSource6, new Function1<FocusState, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$focusModifier$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) throws Throwable {
                    invoke2(focusState);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(FocusState it2) throws Throwable {
                    TextLayoutResultProxy layoutResult;
                    Intrinsics.checkNotNullParameter(it2, "it");
                    if (state.getHasFocus() == it2.isFocused()) {
                        return;
                    }
                    state.setHasFocus(it2.isFocused());
                    TextInputService textInputService2 = textInputService;
                    if (textInputService2 != null) {
                        CoreTextFieldKt.notifyTextInputServiceOnFocusChange(textInputService2, state, value, imeOptions5, offsetMapping);
                        if (it2.isFocused() && (layoutResult = state.getLayoutResult()) != null) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new CoreTextFieldKt$CoreTextField$focusModifier$1$1$1(bringIntoViewRequester, value, state, layoutResult, offsetMapping, null), 3, null);
                        }
                    }
                    if (!it2.isFocused()) {
                        TextFieldSelectionManager.m956deselect_kEHs6E$foundation_release$default(manager, null, 1, null);
                    }
                }
            });
            $composer3.startReplaceableGroup(-55008291);
            ComposerKt.sourceInformation($composer3, "327@16148L163");
            if (enabled2 && !readOnly2) {
                EffectsKt.DisposableEffect(state, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                        final TextFieldState textFieldState = state;
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$2$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                if (textFieldState.getHasFocus()) {
                                    CoreTextFieldKt.onBlur(textFieldState);
                                }
                            }
                        };
                    }
                }, $composer3, 8);
            }
            $composer3.endReplaceableGroup();
            if (TouchMode_androidKt.isInTouchMode()) {
                Modifier selectionModifier = TextFieldGestureModifiersKt.longPressDragGestureFilter(Modifier.INSTANCE, manager.getTouchSelectionObserver(), enabled2);
                final boolean z = readOnly2;
                modifierPointerHoverIcon$default = PointerIconKt.pointerHoverIcon$default(TextFieldPressGestureFilterKt.tapPressTextFieldModifier(Modifier.INSTANCE, interactionSource6, enabled2, new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$pointerModifier$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m770invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m770invokek4lQ0M(long offset) {
                        CoreTextFieldKt.tapToFocus(state, focusRequester, !z);
                        if (state.getHasFocus()) {
                            if (state.getHandleState() != HandleState.Selection) {
                                TextLayoutResultProxy layoutResult = state.getLayoutResult();
                                if (layoutResult != null) {
                                    TextFieldState textFieldState = state;
                                    TextFieldDelegate.INSTANCE.m824setCursorOffsetULxng0E$foundation_release(offset, layoutResult, textFieldState.getProcessor(), offsetMapping, textFieldState.getOnValueChange());
                                    if (textFieldState.getTextDelegate().getText().length() > 0) {
                                        textFieldState.setHandleState(HandleState.Cursor);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            manager.m959deselect_kEHs6E$foundation_release(Offset.m2720boximpl(offset));
                        }
                    }
                }).then(selectionModifier), TextPointerIcon_androidKt.getTextPointerIcon(), false, 2, null);
            } else {
                modifierPointerHoverIcon$default = PointerIconKt.pointerHoverIcon$default(TextFieldGestureModifiersKt.mouseDragGestureDetector(Modifier.INSTANCE, manager.getMouseSelectionObserver(), enabled2), TextPointerIcon_androidKt.getTextPointerIcon(), false, 2, null);
            }
            Modifier pointerModifier = modifierPointerHoverIcon$default;
            final Modifier drawModifier = DrawModifierKt.drawBehind(Modifier.INSTANCE, new Function1<DrawScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$drawModifier$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope drawBehind) {
                    Intrinsics.checkNotNullParameter(drawBehind, "$this$drawBehind");
                    TextLayoutResultProxy layoutResult = state.getLayoutResult();
                    if (layoutResult == null) {
                        return;
                    }
                    TextFieldValue textFieldValue = value;
                    OffsetMapping offsetMapping2 = offsetMapping;
                    TextFieldState textFieldState = state;
                    Canvas canvas = drawBehind.getDrawContext().getCanvas();
                    TextFieldDelegate.INSTANCE.draw$foundation_release(canvas, textFieldValue, offsetMapping2, layoutResult.getValue(), textFieldState.getSelectionPaint());
                }
            });
            final boolean z2 = enabled2;
            final Modifier onPositionedModifier = OnGloballyPositionedModifierKt.onGloballyPositioned(Modifier.INSTANCE, new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$onPositionedModifier$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) throws Throwable {
                    invoke2(layoutCoordinates);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LayoutCoordinates it2) throws Throwable {
                    Intrinsics.checkNotNullParameter(it2, "it");
                    state.setLayoutCoordinates(it2);
                    if (z2) {
                        if (state.getHandleState() == HandleState.Selection) {
                            if (state.getShowFloatingToolbar()) {
                                manager.showSelectionToolbar$foundation_release();
                            } else {
                                manager.hideSelectionToolbar$foundation_release();
                            }
                            state.setShowSelectionHandleStart(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                            state.setShowSelectionHandleEnd(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, false));
                        } else if (state.getHandleState() == HandleState.Cursor) {
                            state.setShowCursorHandle(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(manager, true));
                        }
                        CoreTextFieldKt.notifyFocusedRect(state, value, offsetMapping);
                    }
                    TextLayoutResultProxy layoutResult = state.getLayoutResult();
                    if (layoutResult == null) {
                        return;
                    }
                    layoutResult.setInnerTextFieldCoordinates(it2);
                }
            });
            final boolean isPassword = visualTransformation2 instanceof PasswordVisualTransformation;
            VisualTransformation visualTransformation5 = visualTransformation2;
            final ImeOptions imeOptions6 = imeOptions3;
            boolean z3 = false;
            final boolean z4 = enabled2;
            boolean enabled5 = enabled2;
            final boolean enabled6 = readOnly2;
            final ImeOptions imeOptions7 = imeOptions3;
            Modifier semanticsModifier = SemanticsModifierKt.semantics(Modifier.INSTANCE, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1
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
                public final void invoke2(final SemanticsPropertyReceiver semantics) {
                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                    SemanticsPropertiesKt.m4622setImeAction4L7nppU(semantics, imeOptions6.getImeAction());
                    SemanticsPropertiesKt.setEditableText(semantics, transformedText.getText());
                    SemanticsPropertiesKt.m4625setTextSelectionRangeFDrldGo(semantics, value.getSelection());
                    if (!z4) {
                        SemanticsPropertiesKt.disabled(semantics);
                    }
                    if (isPassword) {
                        SemanticsPropertiesKt.password(semantics);
                    }
                    final TextFieldState textFieldState = state;
                    SemanticsPropertiesKt.getTextLayoutResult$default(semantics, null, new Function1<List<TextLayoutResult>, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Boolean invoke(List<TextLayoutResult> it2) {
                            boolean z5;
                            Intrinsics.checkNotNullParameter(it2, "it");
                            if (textFieldState.getLayoutResult() != null) {
                                TextLayoutResultProxy layoutResult = textFieldState.getLayoutResult();
                                Intrinsics.checkNotNull(layoutResult);
                                it2.add(layoutResult.getValue());
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            return Boolean.valueOf(z5);
                        }
                    }, 1, null);
                    final TextFieldState textFieldState2 = state;
                    SemanticsPropertiesKt.setText$default(semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Boolean invoke(AnnotatedString text) {
                            Unit unit;
                            Intrinsics.checkNotNullParameter(text, "text");
                            TextInputSession session = textFieldState2.getInputSession();
                            if (session != null) {
                                TextFieldState textFieldState3 = textFieldState2;
                                TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new DeleteAllCommand(), new CommitTextCommand(text, 1)}), textFieldState3.getProcessor(), textFieldState3.getOnValueChange(), session);
                                unit = Unit.INSTANCE;
                            } else {
                                unit = null;
                            }
                            if (unit == null) {
                                SemanticsPropertyReceiver semanticsPropertyReceiver = semantics;
                                textFieldState2.getOnValueChange().invoke(new TextFieldValue(text.getText(), TextRangeKt.TextRange(text.getText().length()), (TextRange) null, 4, (DefaultConstructorMarker) null));
                            }
                            return true;
                        }
                    }, 1, null);
                    final TextFieldState textFieldState3 = state;
                    final TextFieldValue textFieldValue = value;
                    SemanticsPropertiesKt.insertTextAtCursor$default(semantics, null, new Function1<AnnotatedString, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Boolean invoke(AnnotatedString text) {
                            Unit unit;
                            Intrinsics.checkNotNullParameter(text, "text");
                            TextInputSession session = textFieldState3.getInputSession();
                            if (session != null) {
                                TextFieldState textFieldState4 = textFieldState3;
                                TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(CollectionsKt.listOf((Object[]) new EditCommand[]{new FinishComposingTextCommand(), new CommitTextCommand(text, 1)}), textFieldState4.getProcessor(), textFieldState4.getOnValueChange(), session);
                                unit = Unit.INSTANCE;
                            } else {
                                unit = null;
                            }
                            if (unit == null) {
                                SemanticsPropertyReceiver semanticsPropertyReceiver = semantics;
                                TextFieldValue textFieldValue2 = textFieldValue;
                                TextFieldState textFieldState5 = textFieldState3;
                                String newText = StringsKt.replaceRange((CharSequence) textFieldValue2.getText(), TextRange.m4768getStartimpl(textFieldValue2.getSelection()), TextRange.m4763getEndimpl(textFieldValue2.getSelection()), (CharSequence) text).toString();
                                long newCursor = TextRangeKt.TextRange(TextRange.m4768getStartimpl(textFieldValue2.getSelection()) + text.length());
                                textFieldState5.getOnValueChange().invoke(new TextFieldValue(newText, newCursor, (TextRange) null, 4, (DefaultConstructorMarker) null));
                            }
                            return true;
                        }
                    }, 1, null);
                    final OffsetMapping offsetMapping2 = offsetMapping;
                    final boolean z5 = z4;
                    final TextFieldValue textFieldValue2 = value;
                    final TextFieldSelectionManager textFieldSelectionManager = manager;
                    final TextFieldState textFieldState4 = state;
                    SemanticsPropertiesKt.setSelection$default(semantics, null, new Function3<Integer, Integer, Boolean, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Boolean invoke(Integer num, Integer num2, Boolean bool) {
                            return invoke(num.intValue(), num2.intValue(), bool.booleanValue());
                        }

                        public final Boolean invoke(int selectionStart, int selectionEnd, boolean relativeToOriginalText) {
                            int start;
                            int end;
                            if (relativeToOriginalText) {
                                start = selectionStart;
                            } else {
                                start = offsetMapping2.transformedToOriginal(selectionStart);
                            }
                            if (relativeToOriginalText) {
                                end = selectionEnd;
                            } else {
                                end = offsetMapping2.transformedToOriginal(selectionEnd);
                            }
                            boolean z6 = false;
                            if (z5 && (start != TextRange.m4768getStartimpl(textFieldValue2.getSelection()) || end != TextRange.m4763getEndimpl(textFieldValue2.getSelection()))) {
                                if (RangesKt.coerceAtMost(start, end) >= 0 && RangesKt.coerceAtLeast(start, end) <= textFieldValue2.getText().length()) {
                                    if (relativeToOriginalText || start == end) {
                                        textFieldSelectionManager.exitSelectionMode$foundation_release();
                                    } else {
                                        textFieldSelectionManager.enterSelectionMode$foundation_release();
                                    }
                                    textFieldState4.getOnValueChange().invoke(new TextFieldValue(textFieldValue2.getText(), TextRangeKt.TextRange(start, end), (TextRange) null, 4, (DefaultConstructorMarker) null));
                                    z6 = true;
                                } else {
                                    textFieldSelectionManager.exitSelectionMode$foundation_release();
                                }
                            }
                            return Boolean.valueOf(z6);
                        }
                    }, 1, null);
                    final TextFieldState textFieldState5 = state;
                    final ImeOptions imeOptions8 = imeOptions6;
                    SemanticsPropertiesKt.performImeAction$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            textFieldState5.getOnImeActionPerformed().invoke(ImeAction.m4915boximpl(imeOptions8.getImeAction()));
                            return true;
                        }
                    }, 1, null);
                    final TextFieldState textFieldState6 = state;
                    final FocusRequester focusRequester2 = focusRequester;
                    final boolean z6 = enabled6;
                    SemanticsPropertiesKt.onClick$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1.6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            CoreTextFieldKt.tapToFocus(textFieldState6, focusRequester2, !z6);
                            return true;
                        }
                    }, 1, null);
                    final TextFieldSelectionManager textFieldSelectionManager2 = manager;
                    SemanticsPropertiesKt.onLongClick$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1.7
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            textFieldSelectionManager2.enterSelectionMode$foundation_release();
                            return true;
                        }
                    }, 1, null);
                    if (!TextRange.m4762getCollapsedimpl(value.getSelection()) && !isPassword) {
                        final TextFieldSelectionManager textFieldSelectionManager3 = manager;
                        SemanticsPropertiesKt.copyText$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1.8
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                TextFieldSelectionManager.copy$foundation_release$default(textFieldSelectionManager3, false, 1, null);
                                return true;
                            }
                        }, 1, null);
                        if (z4 && !enabled6) {
                            final TextFieldSelectionManager textFieldSelectionManager4 = manager;
                            SemanticsPropertiesKt.cutText$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1.9
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    textFieldSelectionManager4.cut$foundation_release();
                                    return true;
                                }
                            }, 1, null);
                        }
                    }
                    if (z4 && !enabled6) {
                        final TextFieldSelectionManager textFieldSelectionManager5 = manager;
                        SemanticsPropertiesKt.pasteText$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1.10
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                textFieldSelectionManager5.paste$foundation_release();
                                return true;
                            }
                        }, 1, null);
                    }
                }
            });
            final Modifier cursorModifier = TextFieldCursorKt.cursor(Modifier.INSTANCE, state, value, offsetMapping, cursorBrush2, enabled5 && !readOnly2);
            EffectsKt.DisposableEffect(manager, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                    Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                    final TextFieldSelectionManager textFieldSelectionManager = manager;
                    return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                        }
                    };
                }
            }, $composer3, 8);
            EffectsKt.DisposableEffect(imeOptions7, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                    Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                    if (textInputService != null && state.getHasFocus()) {
                        state.setInputSession(TextFieldDelegate.INSTANCE.restartInput$foundation_release(textInputService, value, state.getProcessor(), imeOptions7, state.getOnValueChange(), state.getOnImeActionPerformed()));
                    }
                    return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                        }
                    };
                }
            }, $composer3, ($dirty3 >> 3) & 14);
            Modifier.Companion companion = Modifier.INSTANCE;
            Function1<TextFieldValue, Unit> onValueChange2 = state.getOnValueChange();
            boolean z5 = !readOnly2;
            boolean z6 = maxLines5 == 1;
            int imeAction = imeOptions7.getImeAction();
            maxLines3 = maxLines5;
            imeOptions4 = imeOptions7;
            Modifier textKeyInputModifier = TextFieldKeyInputKt.m830textFieldKeyInput2WJ9YEU(companion, state, manager, value, onValueChange2, z5, z6, offsetMapping, undoManager, imeAction);
            Modifier decorationBoxModifier = OnGloballyPositionedModifierKt.onGloballyPositioned(TextFieldScrollKt.textFieldScrollable(previewKeyEventToDeselectOnBack(TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus(modifier5.then(focusModifier), state, focusManager), state, manager).then(textKeyInputModifier), scrollerPosition, interactionSource6, enabled5).then(pointerModifier).then(semanticsModifier), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$decorationBoxModifier$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                    invoke2(layoutCoordinates);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LayoutCoordinates it2) {
                    Intrinsics.checkNotNullParameter(it2, "it");
                    TextLayoutResultProxy layoutResult = state.getLayoutResult();
                    if (layoutResult == null) {
                        return;
                    }
                    layoutResult.setDecorationBoxCoordinates(it2);
                }
            });
            if (enabled5 && state.getHasFocus() && TouchMode_androidKt.isInTouchMode()) {
                z3 = true;
            }
            final boolean showHandleAndMagnifier = z3;
            final Modifier magnifierModifier = showHandleAndMagnifier ? TextFieldSelectionManager_androidKt.textFieldMagnifier(Modifier.INSTANCE, manager) : Modifier.INSTANCE;
            enabled3 = enabled5;
            final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function4 = function3M769getLambda1$foundation_release;
            visualTransformation3 = visualTransformation5;
            final TextStyle textStyle4 = textStyle2;
            final int i16 = minLines2;
            final boolean z7 = readOnly2;
            final Function1<? super TextLayoutResult, Unit> function5 = function2;
            Function2<Composer, Integer, Unit> function6 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5
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
                    ComposerKt.sourceInformation($composer4, "C598@26828L4485:CoreTextField.kt#423gt5");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-374338080, $changed2, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:597)");
                        }
                        Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> function7 = function4;
                        final TextFieldState textFieldState = state;
                        final TextStyle textStyle5 = textStyle4;
                        final int i17 = i16;
                        final int i18 = maxLines3;
                        final TextFieldScrollerPosition textFieldScrollerPosition = scrollerPosition;
                        final TextFieldValue textFieldValue = value;
                        final VisualTransformation visualTransformation6 = visualTransformation3;
                        final Modifier modifier6 = cursorModifier;
                        final Modifier modifier7 = drawModifier;
                        final Modifier modifier8 = onPositionedModifier;
                        final Modifier modifier9 = magnifierModifier;
                        final BringIntoViewRequester bringIntoViewRequester2 = bringIntoViewRequester;
                        final TextFieldSelectionManager textFieldSelectionManager = manager;
                        final boolean z8 = showHandleAndMagnifier;
                        final boolean z9 = z7;
                        final Function1<TextLayoutResult, Unit> function8 = function5;
                        final OffsetMapping offsetMapping2 = offsetMapping;
                        final Density density2 = density;
                        function7.invoke(ComposableLambdaKt.composableLambda($composer4, 2032502107, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1
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
                                ComposerKt.sourceInformation($composer5, "C623@27932L3371:CoreTextField.kt#423gt5");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2032502107, $changed3, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:598)");
                                    }
                                    Modifier modifierHeightInLines = HeightInLinesModifierKt.heightInLines(SizeKt.m522heightInVpY3zN4$default(Modifier.INSTANCE, textFieldState.m842getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), textStyle5, i17, i18);
                                    TextFieldScrollerPosition textFieldScrollerPosition2 = textFieldScrollerPosition;
                                    TextFieldValue textFieldValue2 = textFieldValue;
                                    VisualTransformation visualTransformation7 = visualTransformation6;
                                    final TextFieldState textFieldState2 = textFieldState;
                                    Modifier coreTextFieldModifier = BringIntoViewRequesterKt.bringIntoViewRequester(TextFieldSizeKt.textFieldMinSize(TextFieldScrollKt.textFieldScroll(modifierHeightInLines, textFieldScrollerPosition2, textFieldValue2, visualTransformation7, new Function0<TextLayoutResultProxy>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$coreTextFieldModifier$1
                                        {
                                            super(0);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final TextLayoutResultProxy invoke() {
                                            return textFieldState2.getLayoutResult();
                                        }
                                    }).then(modifier6).then(modifier7), textStyle5).then(modifier8).then(modifier9), bringIntoViewRequester2);
                                    final TextFieldSelectionManager textFieldSelectionManager2 = textFieldSelectionManager;
                                    final TextFieldState textFieldState3 = textFieldState;
                                    final boolean z10 = z8;
                                    final boolean z11 = z9;
                                    final Function1<TextLayoutResult, Unit> function9 = function8;
                                    final TextFieldValue textFieldValue3 = textFieldValue;
                                    final OffsetMapping offsetMapping3 = offsetMapping2;
                                    final Density density3 = density2;
                                    final int i19 = i18;
                                    SimpleLayoutKt.SimpleLayout(coreTextFieldModifier, ComposableLambdaKt.composableLambda($composer5, -363167407, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1
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

                                        /* JADX WARN: Code duplicated, block: B:35:0x013b  */
                                        public final void invoke(Composer $composer6, int $changed4) {
                                            boolean z12;
                                            ComposerKt.sourceInformation($composer6, "C624@27986L2699,677@30703L327,689@31231L40:CoreTextField.kt#423gt5");
                                            if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-363167407, $changed4, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous>.<anonymous> (CoreTextField.kt:623)");
                                                }
                                                final TextFieldState textFieldState4 = textFieldState3;
                                                final Function1<TextLayoutResult, Unit> function10 = function9;
                                                final TextFieldValue textFieldValue4 = textFieldValue3;
                                                final OffsetMapping offsetMapping4 = offsetMapping3;
                                                final Density density4 = density3;
                                                final int i20 = i19;
                                                MeasurePolicy measurePolicy$iv = new MeasurePolicy() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1.2
                                                    @Override // androidx.compose.ui.layout.MeasurePolicy
                                                    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i21) {
                                                        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i21);
                                                    }

                                                    @Override // androidx.compose.ui.layout.MeasurePolicy
                                                    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i21) {
                                                        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i21);
                                                    }

                                                    @Override // androidx.compose.ui.layout.MeasurePolicy
                                                    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i21) {
                                                        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i21);
                                                    }

                                                    @Override // androidx.compose.ui.layout.MeasurePolicy
                                                    /* JADX INFO: renamed from: measure-3p2s80s */
                                                    public MeasureResult mo11measure3p2s80s(MeasureScope measure, List<? extends Measurable> measurables, long constraints) throws Throwable {
                                                        Intrinsics.checkNotNullParameter(measure, "$this$measure");
                                                        Intrinsics.checkNotNullParameter(measurables, "measurables");
                                                        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                                                        TextFieldState textFieldState5 = textFieldState4;
                                                        Snapshot snapshot$iv = this_$iv.createNonObservableSnapshot();
                                                        try {
                                                            Snapshot previous$iv$iv = snapshot$iv.makeCurrent();
                                                            try {
                                                                TextLayoutResultProxy layoutResult = textFieldState5.getLayoutResult();
                                                                TextLayoutResult prevResult = layoutResult != null ? layoutResult.getValue() : null;
                                                                snapshot$iv.restoreCurrent(previous$iv$iv);
                                                                snapshot$iv.dispose();
                                                                Triple<Integer, Integer, TextLayoutResult> tripleM823layout_EkL_Y$foundation_release = TextFieldDelegate.INSTANCE.m823layout_EkL_Y$foundation_release(textFieldState4.getTextDelegate(), constraints, measure.getLayoutDirection(), prevResult);
                                                                int width = tripleM823layout_EkL_Y$foundation_release.component1().intValue();
                                                                int height = tripleM823layout_EkL_Y$foundation_release.component2().intValue();
                                                                TextLayoutResult result = tripleM823layout_EkL_Y$foundation_release.component3();
                                                                if (!Intrinsics.areEqual(prevResult, result)) {
                                                                    textFieldState4.setLayoutResult(new TextLayoutResultProxy(result));
                                                                    function10.invoke(result);
                                                                    CoreTextFieldKt.notifyFocusedRect(textFieldState4, textFieldValue4, offsetMapping4);
                                                                }
                                                                TextFieldState textFieldState6 = textFieldState4;
                                                                Density $this$measure_3p2s80s_u24lambda_u241 = density4;
                                                                textFieldState6.m843setMinHeightForSingleLineField0680j_4($this$measure_3p2s80s_u24lambda_u241.mo324toDpu2uoSUM(i20 == 1 ? TextDelegateKt.ceilToIntPx(result.getLineBottom(0)) : 0));
                                                                return measure.layout(width, height, MapsKt.mapOf(TuplesKt.to(AlignmentLineKt.getFirstBaseline(), Integer.valueOf(MathKt.roundToInt(result.getFirstBaseline()))), TuplesKt.to(AlignmentLineKt.getLastBaseline(), Integer.valueOf(MathKt.roundToInt(result.getLastBaseline())))), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1$2$measure$2
                                                                    @Override // kotlin.jvm.functions.Function1
                                                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                                        invoke2(placementScope);
                                                                        return Unit.INSTANCE;
                                                                    }

                                                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                    public final void invoke2(Placeable.PlacementScope layout) {
                                                                        Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                                                    }
                                                                });
                                                            } catch (Throwable th) {
                                                                snapshot$iv.restoreCurrent(previous$iv$iv);
                                                                throw th;
                                                            }
                                                        } catch (Throwable th2) {
                                                            snapshot$iv.dispose();
                                                            throw th2;
                                                        }
                                                    }

                                                    @Override // androidx.compose.ui.layout.MeasurePolicy
                                                    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> measurables, int height) {
                                                        Intrinsics.checkNotNullParameter($this$maxIntrinsicWidth, "<this>");
                                                        Intrinsics.checkNotNullParameter(measurables, "measurables");
                                                        textFieldState4.getTextDelegate().layoutIntrinsics($this$maxIntrinsicWidth.getLayoutDirection());
                                                        return textFieldState4.getTextDelegate().getMaxIntrinsicWidth();
                                                    }
                                                };
                                                $composer6.startReplaceableGroup(-1323940314);
                                                ComposerKt.sourceInformation($composer6, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                                Modifier modifier$iv = Modifier.INSTANCE;
                                                int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                                                CompositionLocalMap localMap$iv = $composer6.getCurrentCompositionLocalMap();
                                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
                                                int $changed$iv$iv = ((0 << 9) & 7168) | 6;
                                                if (!($composer6.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer6.startReusableNode();
                                                if ($composer6.getInserting()) {
                                                    $composer6.createNode(constructor);
                                                } else {
                                                    $composer6.useNode();
                                                }
                                                Composer $this$Layout_u24lambda_u240$iv = Updater.m2603constructorimpl($composer6);
                                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                                                    $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                                                    $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                                                }
                                                function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv >> 3) & 112));
                                                $composer6.startReplaceableGroup(2058660585);
                                                int i21 = ($changed$iv$iv >> 9) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer6, 1017244823, "C:CoreTextField.kt#423gt5");
                                                ComposerKt.sourceInformationMarkerEnd($composer6);
                                                $composer6.endReplaceableGroup();
                                                $composer6.endNode();
                                                $composer6.endReplaceableGroup();
                                                TextFieldSelectionManager textFieldSelectionManager3 = textFieldSelectionManager2;
                                                if (textFieldState3.getHandleState() != HandleState.Selection || textFieldState3.getLayoutCoordinates() == null) {
                                                    z12 = false;
                                                } else {
                                                    LayoutCoordinates layoutCoordinates = textFieldState3.getLayoutCoordinates();
                                                    Intrinsics.checkNotNull(layoutCoordinates);
                                                    if (layoutCoordinates.isAttached() && z10) {
                                                        z12 = true;
                                                    } else {
                                                        z12 = false;
                                                    }
                                                }
                                                CoreTextFieldKt.SelectionToolbarAndHandles(textFieldSelectionManager3, z12, $composer6, 8);
                                                if (textFieldState3.getHandleState() == HandleState.Cursor && !z11 && z10) {
                                                    CoreTextFieldKt.TextFieldCursorHandle(textFieldSelectionManager2, $composer6, 8);
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer6.skipToGroupEnd();
                                        }
                                    }), $composer5, 48, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }), $composer4, Integer.valueOf((($dirty3 >> 12) & 112) | 6));
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
            CoreTextFieldRootBox(decorationBoxModifier, manager, ComposableLambdaKt.composableLambda($composer2, -374338080, true, function6), $composer2, 448);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            interactionSource4 = interactionSource6;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier3;
        final TextStyle textStyle5 = textStyle2;
        final VisualTransformation visualTransformation6 = visualTransformation3;
        final Function1<? super TextLayoutResult, Unit> function7 = function2;
        final MutableInteractionSource mutableInteractionSource = interactionSource4;
        final Brush brush = cursorBrush2;
        final boolean z8 = softWrap2;
        final int i17 = maxLines3;
        final int i18 = minLines2;
        final ImeOptions imeOptions8 = imeOptions4;
        final KeyboardActions keyboardActions4 = keyboardActions2;
        final boolean z9 = enabled3;
        final boolean z10 = readOnly2;
        final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function8 = function3M769getLambda1$foundation_release;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.6
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
                CoreTextFieldKt.CoreTextField(value, onValueChange, modifier6, textStyle5, visualTransformation6, function7, mutableInteractionSource, brush, z8, i17, i18, imeOptions8, keyboardActions4, z9, z10, function8, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CoreTextFieldRootBox(final Modifier modifier, final TextFieldSelectionManager manager, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-20551815);
        ComposerKt.sourceInformation($composer2, "C(CoreTextFieldRootBox)P(2,1)702@31477L95:CoreTextField.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-20551815, $changed, -1, "androidx.compose.foundation.text.CoreTextFieldRootBox (CoreTextField.kt:697)");
        }
        int $changed$iv = ($changed & 14) | 384;
        $composer2.startReplaceableGroup(733328855);
        ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, true, $composer2, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
        int $changed$iv$iv = ($changed$iv << 3) & 112;
        $composer2.startReplaceableGroup(-1323940314);
        ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
        CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier);
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
        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer2);
        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
        }
        function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
        $composer2.startReplaceableGroup(2058660585);
        int i = ($changed$iv$iv$iv >> 9) & 14;
        ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        int i2 = (($changed$iv >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer2, -1087270836, "C703@31533L33:CoreTextField.kt#423gt5");
        $composer2.startReplaceableGroup(-1985516685);
        ComposerKt.sourceInformation($composer2, "CC(ContextMenuArea)P(1)29@1062L9:ContextMenu.android.kt#423gt5");
        function2.invoke($composer2, Integer.valueOf((((($changed >> 3) & 112) | 8) >> 3) & 14));
        $composer2.endReplaceableGroup();
        ComposerKt.sourceInformationMarkerEnd($composer2);
        ComposerKt.sourceInformationMarkerEnd($composer2);
        $composer2.endReplaceableGroup();
        $composer2.endNode();
        $composer2.endReplaceableGroup();
        $composer2.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextFieldRootBox.2
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

                public final void invoke(Composer composer, int i3) {
                    CoreTextFieldKt.CoreTextFieldRootBox(modifier, manager, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    private static final Modifier previewKeyEventToDeselectOnBack(Modifier $this$previewKeyEventToDeselectOnBack, final TextFieldState state, final TextFieldSelectionManager manager) {
        return KeyInputModifierKt.onPreviewKeyEvent($this$previewKeyEventToDeselectOnBack, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.previewKeyEventToDeselectOnBack.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m771invokeZmokQxo(keyEvent.m3957unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m771invokeZmokQxo(android.view.KeyEvent keyEvent) {
                boolean z;
                Intrinsics.checkNotNullParameter(keyEvent, "keyEvent");
                if (state.getHandleState() == HandleState.Selection && KeyEventHelpers_androidKt.m773cancelsTextSelectionZmokQxo(keyEvent)) {
                    z = true;
                    TextFieldSelectionManager.m956deselect_kEHs6E$foundation_release$default(manager, null, 1, null);
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void tapToFocus(TextFieldState state, FocusRequester focusRequester, boolean allowKeyboard) {
        TextInputSession inputSession;
        if (!state.getHasFocus()) {
            focusRequester.requestFocus();
        } else {
            if (!allowKeyboard || (inputSession = state.getInputSession()) == null) {
                return;
            }
            inputSession.showSoftwareKeyboard();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyTextInputServiceOnFocusChange(TextInputService textInputService, TextFieldState state, TextFieldValue value, ImeOptions imeOptions, OffsetMapping offsetMapping) throws Throwable {
        if (state.getHasFocus()) {
            state.setInputSession(TextFieldDelegate.INSTANCE.onFocus$foundation_release(textInputService, value, state.getProcessor(), imeOptions, state.getOnValueChange(), state.getOnImeActionPerformed()));
            notifyFocusedRect(state, value, offsetMapping);
        } else {
            onBlur(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBlur(TextFieldState state) {
        TextInputSession session = state.getInputSession();
        if (session != null) {
            TextFieldDelegate.INSTANCE.onBlur$foundation_release(session, state.getProcessor(), state.getOnValueChange());
        }
        state.setInputSession(null);
    }

    public static final Object bringSelectionEndIntoView(BringIntoViewRequester $this$bringSelectionEndIntoView, TextFieldValue value, TextDelegate textDelegate, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, Continuation<? super Unit> continuation) {
        Rect selectionEndBounds;
        int selectionEndInTransformed = offsetMapping.originalToTransformed(TextRange.m4765getMaximpl(value.getSelection()));
        if (selectionEndInTransformed < textLayoutResult.getLayoutInput().getText().length()) {
            selectionEndBounds = textLayoutResult.getBoundingBox(selectionEndInTransformed);
        } else if (selectionEndInTransformed != 0) {
            selectionEndBounds = textLayoutResult.getBoundingBox(selectionEndInTransformed - 1);
        } else {
            long defaultSize = TextFieldDelegateKt.computeSizeForDefaultText$default(textDelegate.getStyle(), textDelegate.getDensity(), textDelegate.getFontFamilyResolver(), null, 0, 24, null);
            selectionEndBounds = new Rect(0.0f, 0.0f, 1.0f, IntSize.m5433getHeightimpl(defaultSize));
        }
        Object objBringIntoView = $this$bringSelectionEndIntoView.bringIntoView(selectionEndBounds, continuation);
        return objBringIntoView == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objBringIntoView : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SelectionToolbarAndHandles(final TextFieldSelectionManager manager, final boolean show, Composer $composer, final int $changed) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        Composer $composer2 = $composer.startRestartGroup(626339208);
        ComposerKt.sourceInformation($composer2, "C(SelectionToolbarAndHandles)1044@45268L202:CoreTextField.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(626339208, $changed, -1, "androidx.compose.foundation.text.SelectionToolbarAndHandles (CoreTextField.kt:1025)");
        }
        if (show) {
            TextFieldState state = manager.getState();
            TextLayoutResult textLayoutResult = null;
            if (state != null && (layoutResult = state.getLayoutResult()) != null && (value = layoutResult.getValue()) != null) {
                TextFieldState state2 = manager.getState();
                if (!(state2 != null ? state2.getIsLayoutResultStale() : true)) {
                    textLayoutResult = value;
                }
            }
            if (textLayoutResult != null) {
                TextLayoutResult it = textLayoutResult;
                if (!TextRange.m4762getCollapsedimpl(manager.getValue$foundation_release().getSelection())) {
                    int startOffset = manager.getOffsetMapping().originalToTransformed(TextRange.m4768getStartimpl(manager.getValue$foundation_release().getSelection()));
                    int endOffset = manager.getOffsetMapping().originalToTransformed(TextRange.m4763getEndimpl(manager.getValue$foundation_release().getSelection()));
                    ResolvedTextDirection startDirection = it.getBidiRunDirection(startOffset);
                    ResolvedTextDirection endDirection = it.getBidiRunDirection(Math.max(endOffset - 1, 0));
                    $composer2.startReplaceableGroup(-498389736);
                    ComposerKt.sourceInformation($composer2, "1037@44945L203");
                    TextFieldState state3 = manager.getState();
                    if (state3 != null && state3.getShowSelectionHandleStart()) {
                        TextFieldSelectionManagerKt.TextFieldSelectionHandle(true, startDirection, manager, $composer2, 518);
                    }
                    $composer2.endReplaceableGroup();
                    TextFieldState state4 = manager.getState();
                    if (state4 != null && state4.getShowSelectionHandleEnd()) {
                        TextFieldSelectionManagerKt.TextFieldSelectionHandle(false, endDirection, manager, $composer2, 518);
                    }
                }
                TextFieldState textFieldState = manager.getState();
                if (textFieldState != null) {
                    if (manager.isTextChanged$foundation_release()) {
                        textFieldState.setShowFloatingToolbar(false);
                    }
                    if (textFieldState.getHasFocus()) {
                        if (textFieldState.getShowFloatingToolbar()) {
                            manager.showSelectionToolbar$foundation_release();
                        } else {
                            manager.hideSelectionToolbar$foundation_release();
                        }
                    }
                }
            }
        } else {
            manager.hideSelectionToolbar$foundation_release();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.SelectionToolbarAndHandles.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i) {
                CoreTextFieldKt.SelectionToolbarAndHandles(manager, show, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    public static final void TextFieldCursorHandle(final TextFieldSelectionManager manager, Composer $composer, final int $changed) {
        Object value$iv$iv;
        Object value$iv$iv2;
        Intrinsics.checkNotNullParameter(manager, "manager");
        Composer $composer2 = $composer.startRestartGroup(-1436003720);
        ComposerKt.sourceInformation($composer2, "C(TextFieldCursorHandle)1070@46313L50,1071@46426L7,1078@46683L205,1072@46443L483:CoreTextField.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1436003720, $changed, -1, "androidx.compose.foundation.text.TextFieldCursorHandle (CoreTextField.kt:1068)");
        }
        TextFieldState state = manager.getState();
        if (state != null && state.getShowCursorHandle()) {
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(manager);
            Object it$iv$iv = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = manager.cursorDragObserver$foundation_release();
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            TextDragObserver observer = (TextDragObserver) value$iv$iv;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final long position = manager.m961getCursorPositiontuRUvjQ$foundation_release((Density) objConsume);
            Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, observer, new C02561(observer, null));
            Object key1$iv = Offset.m2720boximpl(position);
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv2 = $composer2.changed(key1$iv);
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv2 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1
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
                        semantics.set(SelectionHandlesKt.getSelectionHandleInfoKey(), new SelectionHandleInfo(Handle.Cursor, position, null));
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            AndroidCursorHandle_androidKt.m751CursorHandleULxng0E(position, SemanticsModifierKt.semantics$default(modifierPointerInput, false, (Function1) value$iv$iv2, 1, null), null, $composer2, 384);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.TextFieldCursorHandle.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i) {
                CoreTextFieldKt.TextFieldCursorHandle(manager, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyFocusedRect(TextFieldState state, TextFieldValue value, OffsetMapping offsetMapping) throws Throwable {
        TextInputSession inputSession;
        LayoutCoordinates layoutCoordinates;
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot snapshot$iv = this_$iv.createNonObservableSnapshot();
        try {
            Snapshot previous$iv$iv = snapshot$iv.makeCurrent();
            try {
                TextLayoutResultProxy layoutResult = state.getLayoutResult();
                if (layoutResult != null && (inputSession = state.getInputSession()) != null && (layoutCoordinates = state.getLayoutCoordinates()) != null) {
                    TextFieldDelegate.INSTANCE.notifyFocusedRect$foundation_release(value, state.getTextDelegate(), layoutResult.getValue(), layoutCoordinates, inputSession, state.getHasFocus(), offsetMapping);
                    Unit unit = Unit.INSTANCE;
                    snapshot$iv.restoreCurrent(previous$iv$iv);
                    snapshot$iv.dispose();
                    return;
                }
                try {
                    snapshot$iv.restoreCurrent(previous$iv$iv);
                    snapshot$iv.dispose();
                } catch (Throwable th) {
                    th = th;
                    snapshot$iv.dispose();
                    throw th;
                }
            } catch (Throwable th2) {
                snapshot$iv.restoreCurrent(previous$iv$iv);
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CoreTextField.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1", f = "CoreTextField.kt", i = {}, l = {1077}, m = "invokeSuspend", n = {}, s = {})
    static final class C02561 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ TextDragObserver $observer;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02561(TextDragObserver textDragObserver, Continuation<? super C02561> continuation) {
            super(2, continuation);
            this.$observer = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02561 c02561 = new C02561(this.$observer, continuation);
            c02561.L$0 = obj;
            return c02561;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C02561) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                    this.label = 1;
                    if (LongPressTextDragObserverKt.detectDownAndDragGesturesWithObserver($this$pointerInput, this.$observer, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }
}
