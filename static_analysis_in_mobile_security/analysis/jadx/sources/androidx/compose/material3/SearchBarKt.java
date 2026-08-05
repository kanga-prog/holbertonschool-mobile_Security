package androidx.compose.material3;

import android.content.res.Configuration;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.shape.GenericShape;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
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
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SearchBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0080\u0002\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\"0&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\"0&2\u0006\u0010(\u001a\u00020)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\"0&2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020)2\u0015\b\u0002\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u00020\u00102\b\b\u0002\u00108\u001a\u0002092\u001c\u0010:\u001a\u0018\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\"0&¢\u0006\u0002\b0¢\u0006\u0002\b<H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001a\u008a\u0002\u0010?\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\"0&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\"0&2\u0006\u0010(\u001a\u00020)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\"0&2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020)2\u0015\b\u0002\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u00020\u00102\b\b\u0002\u0010@\u001a\u00020A2\b\b\u0002\u00108\u001a\u0002092\u001c\u0010:\u001a\u0018\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\"0&¢\u0006\u0002\b0¢\u0006\u0002\b<H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bB\u0010C\u001aÆ\u0001\u0010D\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\"0&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\"0&2\u0006\u0010(\u001a\u00020)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\"0&2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020)2\u0015\b\u0002\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\b\b\u0002\u00105\u001a\u00020E2\b\b\u0002\u00108\u001a\u000209H\u0003¢\u0006\u0002\u0010F\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0019\u0010\u000f\u001a\u00020\u0010X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012\"\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0019\u0010\u0018\u001a\u00020\u0010X\u0082\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0013\u0012\u0004\b\u0019\u0010\u001a\"\u0013\u0010\u001b\u001a\u00020\u0010X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0013\"\u0013\u0010\u001c\u001a\u00020\u0010X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0013\"\u0019\u0010\u001d\u001a\u00020\u0010X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u001e\u0010\u0012\"\u0019\u0010\u001f\u001a\u00020\u0010X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b \u0010\u0012\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006G"}, d2 = {"AnimationDelayMillis", "", "AnimationEnterDurationMillis", "AnimationEnterEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "AnimationEnterFloatSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "AnimationEnterSizeSpec", "Landroidx/compose/ui/unit/IntSize;", "AnimationExitDurationMillis", "AnimationExitEasing", "AnimationExitFloatSpec", "AnimationExitSizeSpec", "DockedActiveTableMaxHeightScreenRatio", "DockedActiveTableMinHeight", "Landroidx/compose/ui/unit/Dp;", "getDockedActiveTableMinHeight", "()F", "F", "DockedEnterTransition", "Landroidx/compose/animation/EnterTransition;", "DockedExitTransition", "Landroidx/compose/animation/ExitTransition;", "SearchBarCornerRadius", "getSearchBarCornerRadius$annotations", "()V", "SearchBarIconOffsetX", "SearchBarMaxWidth", "SearchBarMinWidth", "getSearchBarMinWidth", "SearchBarVerticalPadding", "getSearchBarVerticalPadding", "DockedSearchBar", "", "query", "", "onQueryChange", "Lkotlin/Function1;", "onSearch", "active", "", "onActiveChange", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "placeholder", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "leadingIcon", "trailingIcon", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/SearchBarColors;", "tonalElevation", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "DockedSearchBar-rpjkMjA", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SearchBar", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "SearchBar-Id_Pb_0", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SearchBarInputField", "Landroidx/compose/material3/TextFieldColors;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SearchBarKt {
    private static final int AnimationDelayMillis = 100;
    private static final int AnimationEnterDurationMillis = 600;
    private static final CubicBezierEasing AnimationEnterEasing;
    private static final FiniteAnimationSpec<Float> AnimationEnterFloatSpec;
    private static final FiniteAnimationSpec<IntSize> AnimationEnterSizeSpec;
    private static final int AnimationExitDurationMillis = 350;
    private static final CubicBezierEasing AnimationExitEasing;
    private static final FiniteAnimationSpec<Float> AnimationExitFloatSpec;
    private static final FiniteAnimationSpec<IntSize> AnimationExitSizeSpec;
    private static final float DockedActiveTableMaxHeightScreenRatio = 0.6666667f;
    private static final float DockedActiveTableMinHeight;
    private static final EnterTransition DockedEnterTransition;
    private static final ExitTransition DockedExitTransition;
    private static final float SearchBarCornerRadius;
    private static final float SearchBarIconOffsetX;
    private static final float SearchBarMaxWidth;
    private static final float SearchBarMinWidth;
    private static final float SearchBarVerticalPadding;

    private static /* synthetic */ void getSearchBarCornerRadius$annotations() {
    }

    /* JADX WARN: Code duplicated, block: B:180:0x0257  */
    /* JADX WARN: Code duplicated, block: B:182:0x0260  */
    /* JADX WARN: Code duplicated, block: B:195:0x02aa A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:196:0x02ac  */
    /* JADX WARN: Code duplicated, block: B:197:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:199:0x02b5  */
    /* JADX WARN: Code duplicated, block: B:200:0x02b7  */
    /* JADX WARN: Code duplicated, block: B:202:0x02bb  */
    /* JADX WARN: Code duplicated, block: B:203:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:205:0x02c1  */
    /* JADX WARN: Code duplicated, block: B:206:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:208:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:209:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:212:0x02cf  */
    /* JADX WARN: Code duplicated, block: B:213:0x02db  */
    /* JADX WARN: Code duplicated, block: B:216:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:217:0x02f8  */
    /* JADX WARN: Code duplicated, block: B:219:0x02fc  */
    /* JADX WARN: Code duplicated, block: B:220:0x0303  */
    /* JADX WARN: Code duplicated, block: B:223:0x030b  */
    /* JADX WARN: Code duplicated, block: B:224:0x0317  */
    /* JADX WARN: Code duplicated, block: B:226:0x031d  */
    /* JADX WARN: Code duplicated, block: B:228:0x0341  */
    /* JADX WARN: Code duplicated, block: B:229:0x0350  */
    /* JADX WARN: Code duplicated, block: B:231:0x0374  */
    /* JADX WARN: Code duplicated, block: B:234:0x0396  */
    /* JADX WARN: Code duplicated, block: B:236:0x03a0  */
    /* JADX WARN: Code duplicated, block: B:237:0x03a3  */
    /* JADX WARN: Code duplicated, block: B:239:0x03a6  */
    /* JADX WARN: Code duplicated, block: B:240:0x03a9  */
    /* JADX WARN: Code duplicated, block: B:243:0x042e  */
    /* JADX WARN: Code duplicated, block: B:244:0x044a  */
    /* JADX WARN: Code duplicated, block: B:247:0x048a  */
    /* JADX WARN: Code duplicated, block: B:251:0x049a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:254:0x04a4  */
    /* JADX WARN: Code duplicated, block: B:255:0x04b7  */
    /* JADX WARN: Code duplicated, block: B:257:0x04c1  */
    /* JADX WARN: Code duplicated, block: B:258:0x04c4  */
    /* JADX WARN: Code duplicated, block: B:262:0x04fb  */
    /* JADX WARN: Code duplicated, block: B:263:0x050c  */
    /* JADX WARN: Code duplicated, block: B:266:0x0537  */
    /* JADX WARN: Code duplicated, block: B:270:0x0548 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:274:0x05a9  */
    /* JADX WARN: Code duplicated, block: B:278:0x05b9 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:282:0x0601  */
    /* JADX WARN: Code duplicated, block: B:286:0x060e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:290:0x06d3  */
    /* JADX WARN: Code duplicated, block: B:294:0x06de  */
    /* JADX WARN: Code duplicated, block: B:297:0x0700  */
    /* JADX WARN: Code duplicated, block: B:301:0x0717  */
    /* JADX WARN: Code duplicated, block: B:302:0x071a  */
    /* JADX INFO: renamed from: SearchBar-Id_Pb_0, reason: not valid java name */
    public static final void m1700SearchBarId_Pb_0(final String query, final Function1<? super String, Unit> onQueryChange, final Function1<? super String, Unit> onSearch, final boolean active, final Function1<? super Boolean, Unit> onActiveChange, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Shape shape, SearchBarColors colors, float tonalElevation, WindowInsets windowInsets, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int $changed1, final int i) {
        MutableInteractionSource mutableInteractionSource;
        int i2;
        Modifier.Companion modifier2;
        boolean enabled2;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Shape shape2;
        SearchBarColors colors2;
        float tonalElevation2;
        SearchBarColors colors3;
        WindowInsets windowInsets2;
        Function2<? super Composer, ? super Integer, Unit> function8;
        SearchBarColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function9;
        Function2<? super Composer, ? super Integer, Unit> function10;
        boolean enabled3;
        Shape shape3;
        float tonalElevation3;
        MutableInteractionSource interactionSource2;
        Modifier modifier3;
        final WindowInsets windowInsets3;
        int $dirty1;
        Object it$iv$iv;
        Object value$iv$iv;
        float f;
        FiniteAnimationSpec<Float> finiteAnimationSpec;
        final State<Float> stateAnimateFloatAsState;
        final Density density;
        Shape defaultInputFieldShape;
        Shape defaultFullScreenShape;
        Object value$iv$iv2;
        State useFullScreenShape$delegate;
        boolean invalid$iv$iv;
        GenericShape value$iv$iv3;
        Object value$iv$iv4;
        boolean invalid$iv$iv2;
        Object value$iv$iv5;
        boolean invalid$iv$iv3;
        Object value$iv$iv6;
        boolean invalid$iv$iv4;
        Object value$iv$iv7;
        WindowInsets windowInsets4;
        Modifier modifier4;
        Composer $composer2;
        boolean invalid$iv$iv5;
        Object value$iv$iv8;
        SearchBarColors colors5;
        float tonalElevation4;
        MutableInteractionSource interactionSource3;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        boolean enabled4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(onQueryChange, "onQueryChange");
        Intrinsics.checkNotNullParameter(onSearch, "onSearch");
        Intrinsics.checkNotNullParameter(onActiveChange, "onActiveChange");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(156000634);
        ComposerKt.sourceInformation($composer3, "C(SearchBar)P(11,8,9!1,7,6,3,10,5,14,12!1,13:c#ui.unit.Dp,15,4)170@8704L15,171@8769L8,173@8884L12,174@8948L39,177@9084L160,182@9287L7,183@9326L7,185@9386L15,186@9453L15,187@9499L101,190@9625L578,210@10651L34,211@10707L179,221@10994L38,225@11170L112,229@11354L1074,218@10892L2701,282@13599L306,291@13941L37,291@13911L67:SearchBar.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(query) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer3.changedInstance(onQueryChange) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer3.changedInstance(onSearch) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(active) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty |= $composer3.changedInstance(onActiveChange) ? 16384 : 8192;
        }
        int i3 = i & 32;
        if (i3 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty |= $composer3.changed(modifier) ? 131072 : 65536;
        }
        int i4 = i & 64;
        if (i4 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty |= $composer3.changed(enabled) ? 1048576 : 524288;
        }
        int i5 = i & 128;
        if (i5 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        }
        int i6 = i & 256;
        if (i6 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 536870912 : 268435456;
        }
        if (($changed1 & 14) == 0) {
            $dirty2 |= ((i & 1024) == 0 && $composer3.changed(shape)) ? 4 : 2;
        }
        if (($changed1 & 112) == 0) {
            $dirty2 |= ((i & 2048) == 0 && $composer3.changed(colors)) ? 32 : 16;
        }
        int i8 = i & 4096;
        if (i8 != 0) {
            $dirty2 |= 384;
        } else if (($changed1 & 896) == 0) {
            $dirty2 |= $composer3.changed(tonalElevation) ? 256 : 128;
        }
        if (($changed1 & 7168) == 0) {
            $dirty2 |= ((i & 8192) == 0 && $composer3.changed(windowInsets)) ? 2048 : 1024;
        }
        int i9 = i & 16384;
        if (i9 != 0) {
            $dirty2 |= 24576;
            mutableInteractionSource = interactionSource;
        } else if (($changed1 & 57344) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer3.changed(mutableInteractionSource) ? 16384 : 8192;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 32768) == 0) {
            if (($changed1 & 458752) == 0) {
                i2 = $composer3.changedInstance(content) ? 131072 : 65536;
            }
            if (($dirty & 1533916891) != 306783378 && (374491 & $dirty2) == 74898 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                modifier4 = modifier;
                enabled4 = enabled;
                function11 = function2;
                function12 = function3;
                function13 = function4;
                shape3 = shape;
                colors5 = colors;
                tonalElevation4 = tonalElevation;
                windowInsets4 = windowInsets;
                $composer2 = $composer3;
                interactionSource3 = mutableInteractionSource;
            } else {
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
                        function5 = null;
                    } else {
                        function5 = function2;
                    }
                    if (i6 != 0) {
                        function6 = null;
                    } else {
                        function6 = function3;
                    }
                    if (i7 != 0) {
                        function7 = null;
                    } else {
                        function7 = function4;
                    }
                    if ((i & 1024) != 0) {
                        shape2 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
                        $dirty2 &= -15;
                    } else {
                        shape2 = shape;
                    }
                    if ((i & 2048) != 0) {
                        colors2 = SearchBarDefaults.INSTANCE.m1694colorsKlgxPg(0L, 0L, null, $composer3, 3072, 7);
                        $dirty2 &= -113;
                    } else {
                        colors2 = colors;
                    }
                    if (i8 != 0) {
                        tonalElevation2 = SearchBarDefaults.INSTANCE.m1695getElevationD9Ej5fM();
                    } else {
                        tonalElevation2 = tonalElevation;
                    }
                    colors3 = colors2;
                    if ((i & 8192) != 0) {
                        windowInsets2 = SearchBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                        $dirty2 &= -7169;
                    } else {
                        windowInsets2 = windowInsets;
                    }
                    if (i9 != 0) {
                        WindowInsets windowInsets5 = windowInsets2;
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
                        function8 = function5;
                        colors4 = colors3;
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                        function9 = function6;
                        function10 = function7;
                        enabled3 = enabled2;
                        shape3 = shape2;
                        tonalElevation3 = tonalElevation2;
                        modifier3 = modifier2;
                        windowInsets3 = windowInsets5;
                        $dirty1 = $dirty3;
                    } else {
                        function8 = function5;
                        colors4 = colors3;
                        function9 = function6;
                        function10 = function7;
                        enabled3 = enabled2;
                        shape3 = shape2;
                        tonalElevation3 = tonalElevation2;
                        interactionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        windowInsets3 = windowInsets2;
                        $dirty1 = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 1024) != 0) {
                        $dirty2 &= -15;
                    }
                    if ((i & 2048) != 0) {
                        $dirty2 &= -113;
                    }
                    if ((i & 8192) != 0) {
                        enabled3 = enabled;
                        function8 = function2;
                        function9 = function3;
                        function10 = function4;
                        shape3 = shape;
                        colors4 = colors;
                        tonalElevation3 = tonalElevation;
                        windowInsets3 = windowInsets;
                        $dirty1 = $dirty2 & (-7169);
                        interactionSource2 = mutableInteractionSource;
                        modifier3 = modifier;
                    } else {
                        enabled3 = enabled;
                        function8 = function2;
                        function9 = function3;
                        function10 = function4;
                        shape3 = shape;
                        colors4 = colors;
                        tonalElevation3 = tonalElevation;
                        windowInsets3 = windowInsets;
                        $dirty1 = $dirty2;
                        interactionSource2 = mutableInteractionSource;
                        modifier3 = modifier;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(156000634, $dirty, $dirty1, "androidx.compose.material3.SearchBar (SearchBar.kt:159)");
                }
                if (active) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                if (active) {
                    finiteAnimationSpec = AnimationEnterFloatSpec;
                } else {
                    finiteAnimationSpec = AnimationExitFloatSpec;
                }
                stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, finiteAnimationSpec, 0.0f, null, null, $composer3, 64, 28);
                ProvidableCompositionLocal<FocusManager> localFocusManager = CompositionLocalsKt.getLocalFocusManager();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localFocusManager);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                FocusManager focusManager = (FocusManager) objConsume;
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                final int $dirty4 = $dirty;
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer3.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                density = (Density) objConsume2;
                defaultInputFieldShape = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
                defaultFullScreenShape = SearchBarDefaults.INSTANCE.getFullScreenShape($composer3, 6);
                $composer3.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                value$iv$iv2 = $composer3.rememberedValue();
                if (value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$useFullScreenShape$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            return Boolean.valueOf(stateAnimateFloatAsState.getValue().floatValue() == 1.0f);
                        }
                    });
                    $composer3.updateRememberedValue(value$iv$iv2);
                }
                $composer3.endReplaceableGroup();
                useFullScreenShape$delegate = (State) value$iv$iv2;
                Object key1$iv = Boolean.valueOf(SearchBar_Id_Pb_0$lambda$2(useFullScreenShape$delegate));
                int i10 = ($dirty1 << 3) & 112;
                $composer3.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer3.changed(key1$iv) | $composer3.changed(shape3);
                value$iv$iv3 = $composer3.rememberedValue();
                if (!invalid$iv$iv || value$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                    if (Intrinsics.areEqual(shape3, defaultInputFieldShape)) {
                        value$iv$iv3 = new GenericShape(new Function3<Path, Size, LayoutDirection, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$animatedShape$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(Path path, Size size, LayoutDirection layoutDirection) {
                                m1702invoke12SF9DM(path, size.getPackedValue(), layoutDirection);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-12SF9DM, reason: not valid java name */
                            public final void m1702invoke12SF9DM(Path $receiver, long size, LayoutDirection layoutDirection) {
                                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                                Intrinsics.checkNotNullParameter(layoutDirection, "<anonymous parameter 1>");
                                Density $this$invoke_12SF9DM_u24lambda_u240 = density;
                                State<Float> state = stateAnimateFloatAsState;
                                float arg0$iv = SearchBarKt.SearchBarCornerRadius;
                                float other$iv = 1 - state.getValue().floatValue();
                                float radius = $this$invoke_12SF9DM_u24lambda_u240.mo327toPx0680j_4(Dp.m5274constructorimpl(arg0$iv * other$iv));
                                $receiver.addRoundRect(RoundRectKt.m2786RoundRectsniSvfs(SizeKt.m2821toRectuvyYCjk(size), CornerRadiusKt.CornerRadius$default(radius, 0.0f, 2, null)));
                            }
                        });
                    } else if (SearchBar_Id_Pb_0$lambda$2(useFullScreenShape$delegate)) {
                        value$iv$iv3 = defaultFullScreenShape;
                    } else {
                        value$iv$iv3 = shape3;
                    }
                    $composer3.updateRememberedValue(value$iv$iv3);
                }
                $composer3.endReplaceableGroup();
                Shape animatedShape = (Shape) value$iv$iv3;
                $composer3.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                value$iv$iv4 = $composer3.rememberedValue();
                if (value$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv4 = new MutableWindowInsets(null, 1, null);
                    $composer3.updateRememberedValue(value$iv$iv4);
                }
                $composer3.endReplaceableGroup();
                final MutableWindowInsets unconsumedInsets = (MutableWindowInsets) value$iv$iv4;
                $composer3.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv2 = $composer3.changed(density);
                Object it$iv$iv2 = $composer3.rememberedValue();
                if (!invalid$iv$iv2 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv5 = SnapshotStateKt.derivedStateOf(new Function0<Dp>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$topPadding$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Dp invoke() {
                            return Dp.m5272boximpl(m1703invokeD9Ej5fM());
                        }

                        /* JADX INFO: renamed from: invoke-D9Ej5fM, reason: not valid java name */
                        public final float m1703invokeD9Ej5fM() {
                            float arg0$iv = SearchBarKt.getSearchBarVerticalPadding();
                            float other$iv = WindowInsetsKt.asPaddingValues(unconsumedInsets, density).getTop();
                            return Dp.m5274constructorimpl(arg0$iv + other$iv);
                        }
                    });
                    $composer3.updateRememberedValue(value$iv$iv5);
                } else {
                    value$iv$iv5 = it$iv$iv2;
                }
                $composer3.endReplaceableGroup();
                final State topPadding = (State) value$iv$iv5;
                long containerColor = colors4.getContainerColor();
                long jM1426contentColorForek8zF_U = ColorSchemeKt.m1426contentColorForek8zF_U(colors4.getContainerColor(), $composer3, 0);
                Modifier modifierZIndex = ZIndexModifierKt.zIndex(modifier3, 1.0f);
                int i11 = (($dirty1 >> 6) & 112) | 6;
                $composer3.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv3 = $composer3.changed(unconsumedInsets) | $composer3.changed(windowInsets3);
                value$iv$iv6 = $composer3.rememberedValue();
                if (!invalid$iv$iv3 || value$iv$iv6 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv6 = (Function1) new Function1<WindowInsets, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(WindowInsets windowInsets6) {
                            invoke2(windowInsets6);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(WindowInsets consumedInsets) {
                            Intrinsics.checkNotNullParameter(consumedInsets, "consumedInsets");
                            unconsumedInsets.setInsets(WindowInsetsKt.exclude(windowInsets3, consumedInsets));
                        }
                    };
                    $composer3.updateRememberedValue(value$iv$iv6);
                }
                $composer3.endReplaceableGroup();
                Modifier modifierConsumeWindowInsets = WindowInsetsPaddingKt.consumeWindowInsets(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifierZIndex, (Function1) value$iv$iv6), unconsumedInsets);
                $composer3.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv4 = $composer3.changed(topPadding) | $composer3.changed(stateAnimateFloatAsState);
                Object it$iv$iv3 = $composer3.rememberedValue();
                if (!invalid$iv$iv4 || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv7 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                            return m1701invoke3p2s80s(measureScope, measurable, constraints.getValue());
                        }

                        /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                        public final MeasureResult m1701invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                            Intrinsics.checkNotNullParameter(layout, "$this$layout");
                            Intrinsics.checkNotNullParameter(measurable, "measurable");
                            final int animatedTopPadding = layout.mo321roundToPx0680j_4(DpKt.m5317lerpMdfbLM(topPadding.getValue().m5288unboximpl(), Dp.m5274constructorimpl(0), stateAnimateFloatAsState.getValue().floatValue()));
                            int startWidth = RangesKt.coerceAtMost(Math.max(Constraints.m5220getMinWidthimpl(constraints), layout.mo321roundToPx0680j_4(SearchBarKt.getSearchBarMinWidth())), Math.min(Constraints.m5218getMaxWidthimpl(constraints), layout.mo321roundToPx0680j_4(SearchBarKt.SearchBarMaxWidth)));
                            int startHeight = RangesKt.coerceAtMost(Math.max(Constraints.m5219getMinHeightimpl(constraints), layout.mo321roundToPx0680j_4(SearchBarDefaults.INSTANCE.m1696getInputFieldHeightD9Ej5fM())), Constraints.m5217getMaxHeightimpl(constraints));
                            int endWidth = Constraints.m5218getMaxWidthimpl(constraints);
                            int endHeight = Constraints.m5217getMaxHeightimpl(constraints);
                            int width = MathHelpersKt.lerp(startWidth, endWidth, stateAnimateFloatAsState.getValue().floatValue());
                            int height = MathHelpersKt.lerp(startHeight, endHeight, stateAnimateFloatAsState.getValue().floatValue()) + animatedTopPadding;
                            final Placeable placeable = measurable.mo4225measureBRTryo0(ConstraintsKt.m5235offsetNN6EwU$default(Constraints.INSTANCE.m5226fixedJhjzzOo(width, height), 0, -animatedTopPadding, 1, null));
                            return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$3$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                    invoke2(placementScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Placeable.PlacementScope layout2) {
                                    Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                                    Placeable.PlacementScope.placeRelative$default(layout2, placeable, 0, animatedTopPadding, 0.0f, 4, null);
                                }
                            }, 4, null);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv$iv7);
                } else {
                    value$iv$iv7 = it$iv$iv3;
                }
                $composer3.endReplaceableGroup();
                Modifier modifierLayout = LayoutModifierKt.layout(modifierConsumeWindowInsets, (Function3) value$iv$iv7);
                final boolean z = enabled3;
                final Function2<? super Composer, ? super Integer, Unit> function14 = function8;
                final Function2<? super Composer, ? super Integer, Unit> function15 = function9;
                final int $dirty5 = $dirty1;
                final Function2<? super Composer, ? super Integer, Unit> function16 = function10;
                final SearchBarColors searchBarColors = colors4;
                windowInsets4 = windowInsets3;
                final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
                modifier4 = modifier3;
                Function2<Composer, Integer, Unit> function17 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$4
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
                        Object value$iv$iv9;
                        Object value$iv$iv10;
                        Object value$iv$iv11;
                        ComposerKt.sourceInformation($composer4, "C251@12445L1142:SearchBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-867266817, $changed2, -1, "androidx.compose.material3.SearchBar.<anonymous> (SearchBar.kt:250)");
                            }
                            String str = query;
                            Function1<String, Unit> function1 = onQueryChange;
                            Function1<String, Unit> function18 = onSearch;
                            boolean z2 = active;
                            Function1<Boolean, Unit> function19 = onActiveChange;
                            boolean z3 = z;
                            Function2<Composer, Integer, Unit> function20 = function14;
                            Function2<Composer, Integer, Unit> function21 = function15;
                            Function2<Composer, Integer, Unit> function22 = function16;
                            SearchBarColors searchBarColors2 = searchBarColors;
                            MutableInteractionSource mutableInteractionSource3 = mutableInteractionSource2;
                            int i12 = $dirty4;
                            int $i$f$Layout = $dirty5;
                            final State<Float> state = stateAnimateFloatAsState;
                            State<Dp> state2 = topPadding;
                            Function3<ColumnScope, Composer, Integer, Unit> function23 = content;
                            $composer4.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer4.consume(localDensity2);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density density$iv$iv = (Density) objConsume3;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume4 = $composer4.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume5 = $composer4.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
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
                            int i13 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                            int i14 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -685095508, "C252@12498L93,255@12604L590,270@13227L115:SearchBar.kt#uh7d8r");
                            $composer4.startReplaceableGroup(-492369756);
                            ComposerKt.sourceInformation($composer4, "CC(remember):Composables.kt#9igjgp");
                            Object it$iv$iv4 = $composer4.rememberedValue();
                            if (it$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv9 = new AnimatedPaddingValues(state, state2);
                                $composer4.updateRememberedValue(value$iv$iv9);
                            } else {
                                value$iv$iv9 = it$iv$iv4;
                            }
                            $composer4.endReplaceableGroup();
                            AnimatedPaddingValues animatedInputFieldPadding = (AnimatedPaddingValues) value$iv$iv9;
                            SearchBarKt.SearchBarInputField(str, function1, function18, z2, function19, PaddingKt.padding(Modifier.INSTANCE, animatedInputFieldPadding), z3, function20, function21, function22, searchBarColors2.getInputFieldColors(), mutableInteractionSource3, $composer4, (i12 & 1879048192) | (i12 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i12 & 112) | (i12 & 896) | (i12 & 7168) | (57344 & i12) | (3670016 & i12) | (29360128 & i12) | (234881024 & i12), ($i$f$Layout >> 9) & 112, 0);
                            $composer4.startReplaceableGroup(-492369756);
                            ComposerKt.sourceInformation($composer4, "CC(remember):Composables.kt#9igjgp");
                            Object it$iv$iv5 = $composer4.rememberedValue();
                            if (it$iv$iv5 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv10 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$4$1$showResults$2$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        return Boolean.valueOf(state.getValue().floatValue() > 0.0f);
                                    }
                                });
                                $composer4.updateRememberedValue(value$iv$iv10);
                            } else {
                                value$iv$iv10 = it$iv$iv5;
                            }
                            $composer4.endReplaceableGroup();
                            State showResults$delegate = (State) value$iv$iv10;
                            $composer4.startReplaceableGroup(551421060);
                            ComposerKt.sourceInformation($composer4, "274@13420L35,274@13390L173");
                            if (invoke$lambda$5$lambda$2(showResults$delegate)) {
                                Modifier.Companion companion = Modifier.INSTANCE;
                                $composer4.startReplaceableGroup(1157296644);
                                ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                boolean invalid$iv$iv6 = $composer4.changed(state);
                                Object it$iv$iv6 = $composer4.rememberedValue();
                                if (invalid$iv$iv6 || it$iv$iv6 == Composer.INSTANCE.getEmpty()) {
                                    value$iv$iv11 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$4$1$1$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                            invoke2(graphicsLayerScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(GraphicsLayerScope graphicsLayer) {
                                            Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
                                            graphicsLayer.setAlpha(state.getValue().floatValue());
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv$iv11);
                                } else {
                                    value$iv$iv11 = it$iv$iv6;
                                }
                                $composer4.endReplaceableGroup();
                                Modifier modifier$iv2 = GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) value$iv$iv11);
                                $composer4.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                int $changed$iv$iv2 = (0 << 3) & 112;
                                $composer4.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer4.consume(localDensity3);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                Density density$iv$iv2 = (Density) objConsume6;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume7 = $composer4.consume(localLayoutDirection2);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume7;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume8 = $composer4.consume(localViewConfiguration2);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume8;
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                if (!($composer4.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer4.startReusableNode();
                                if ($composer4.getInserting()) {
                                    $composer4.createNode(constructor2);
                                } else {
                                    $composer4.useNode();
                                }
                                $composer4.disableReusing();
                                Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer4);
                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                $composer4.enableReusing();
                                function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                $composer4.startReplaceableGroup(2058660585);
                                int i15 = ($changed$iv$iv$iv2 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                ColumnScope $this$invoke_u24lambda_u245_u24lambda_u244 = ColumnScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart($composer4, 1234674882, "C275@13479L36,276@13536L9:SearchBar.kt#uh7d8r");
                                DividerKt.m1508Divider9IZ8Weo(null, 0.0f, searchBarColors2.getDividerColor(), $composer4, 0, 3);
                                function23.invoke($this$invoke_u24lambda_u245_u24lambda_u244, $composer4, Integer.valueOf(((((0 >> 6) & 112) | 6) & 14) | (($i$f$Layout >> 12) & 112)));
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endReplaceableGroup();
                                $composer4.endNode();
                                $composer4.endReplaceableGroup();
                                $composer4.endReplaceableGroup();
                            }
                            $composer4.endReplaceableGroup();
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

                    private static final boolean invoke$lambda$5$lambda$2(State<Boolean> state) {
                        Object thisObj$iv = state.getValue();
                        return ((Boolean) thisObj$iv).booleanValue();
                    }
                };
                $composer2 = $composer3;
                SurfaceKt.m1806SurfaceT9BRK9s(modifierLayout, animatedShape, containerColor, jM1426contentColorForek8zF_U, tonalElevation3, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -867266817, true, function17), $composer2, (($dirty5 << 6) & 57344) | 12582912, 96);
                EffectsKt.LaunchedEffect(Boolean.valueOf(active), new SearchBarKt$SearchBar$5(active, focusManager, null), $composer2, (($dirty4 >> 9) & 14) | 64);
                int i12 = ($dirty4 >> 12) & 14;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv5 = $composer2.changed(onActiveChange);
                Object it$iv$iv4 = $composer2.rememberedValue();
                if (!invalid$iv$iv5 || it$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv8 = new Function0<Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$6$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            onActiveChange.invoke(false);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv8);
                } else {
                    value$iv$iv8 = it$iv$iv4;
                }
                $composer2.endReplaceableGroup();
                BackHandlerKt.BackHandler(active, (Function0) value$iv$iv8, $composer2, ($dirty4 >> 9) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                colors5 = colors4;
                tonalElevation4 = tonalElevation3;
                interactionSource3 = interactionSource2;
                function11 = function8;
                function12 = function9;
                function13 = function10;
                enabled4 = enabled3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final boolean z2 = enabled4;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function11;
            final Function2<? super Composer, ? super Integer, Unit> function19 = function12;
            final Function2<? super Composer, ? super Integer, Unit> function20 = function13;
            final Shape shape4 = shape3;
            final SearchBarColors searchBarColors2 = colors5;
            final float f2 = tonalElevation4;
            final WindowInsets windowInsets6 = windowInsets4;
            final MutableInteractionSource mutableInteractionSource3 = interactionSource3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$7
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
                    SearchBarKt.m1700SearchBarId_Pb_0(query, onQueryChange, onSearch, active, onActiveChange, modifier5, z2, function18, function19, function20, shape4, searchBarColors2, f2, windowInsets6, mutableInteractionSource3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty2 |= i2;
        if (($dirty & 1533916891) != 306783378) {
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
                    function5 = null;
                } else {
                    function5 = function2;
                }
                if (i6 != 0) {
                    function6 = null;
                } else {
                    function6 = function3;
                }
                if (i7 != 0) {
                    function7 = null;
                } else {
                    function7 = function4;
                }
                if ((i & 1024) != 0) {
                    shape2 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
                    $dirty2 &= -15;
                } else {
                    shape2 = shape;
                }
                if ((i & 2048) != 0) {
                    colors2 = SearchBarDefaults.INSTANCE.m1694colorsKlgxPg(0L, 0L, null, $composer3, 3072, 7);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                if (i8 != 0) {
                    tonalElevation2 = SearchBarDefaults.INSTANCE.m1695getElevationD9Ej5fM();
                } else {
                    tonalElevation2 = tonalElevation;
                }
                colors3 = colors2;
                if ((i & 8192) != 0) {
                    windowInsets2 = SearchBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    windowInsets2 = windowInsets;
                }
                if (i9 != 0) {
                    WindowInsets windowInsets7 = windowInsets2;
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
                    function8 = function5;
                    colors4 = colors3;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    function9 = function6;
                    function10 = function7;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    tonalElevation3 = tonalElevation2;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets7;
                    $dirty1 = $dirty6;
                } else {
                    function8 = function5;
                    colors4 = colors3;
                    function9 = function6;
                    function10 = function7;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    tonalElevation3 = tonalElevation2;
                    interactionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets2;
                    $dirty1 = $dirty2;
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
                    function5 = null;
                } else {
                    function5 = function2;
                }
                if (i6 != 0) {
                    function6 = null;
                } else {
                    function6 = function3;
                }
                if (i7 != 0) {
                    function7 = null;
                } else {
                    function7 = function4;
                }
                if ((i & 1024) != 0) {
                    shape2 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
                    $dirty2 &= -15;
                } else {
                    shape2 = shape;
                }
                if ((i & 2048) != 0) {
                    colors2 = SearchBarDefaults.INSTANCE.m1694colorsKlgxPg(0L, 0L, null, $composer3, 3072, 7);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                if (i8 != 0) {
                    tonalElevation2 = SearchBarDefaults.INSTANCE.m1695getElevationD9Ej5fM();
                } else {
                    tonalElevation2 = tonalElevation;
                }
                colors3 = colors2;
                if ((i & 8192) != 0) {
                    windowInsets2 = SearchBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    windowInsets2 = windowInsets;
                }
                if (i9 != 0) {
                    WindowInsets windowInsets8 = windowInsets2;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty7 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    function8 = function5;
                    colors4 = colors3;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    function9 = function6;
                    function10 = function7;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    tonalElevation3 = tonalElevation2;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets8;
                    $dirty1 = $dirty7;
                } else {
                    function8 = function5;
                    colors4 = colors3;
                    function9 = function6;
                    function10 = function7;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    tonalElevation3 = tonalElevation2;
                    interactionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets2;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(156000634, $dirty, $dirty1, "androidx.compose.material3.SearchBar (SearchBar.kt:159)");
            }
            if (active) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            if (active) {
                finiteAnimationSpec = AnimationEnterFloatSpec;
            } else {
                finiteAnimationSpec = AnimationExitFloatSpec;
            }
            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, finiteAnimationSpec, 0.0f, null, null, $composer3, 64, 28);
            ProvidableCompositionLocal<FocusManager> localFocusManager2 = CompositionLocalsKt.getLocalFocusManager();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localFocusManager2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            FocusManager focusManager2 = (FocusManager) objConsume3;
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            final int $dirty8 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer3.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            density = (Density) objConsume4;
            defaultInputFieldShape = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
            defaultFullScreenShape = SearchBarDefaults.INSTANCE.getFullScreenShape($composer3, 6);
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            value$iv$iv2 = $composer3.rememberedValue();
            if (value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$useFullScreenShape$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(stateAnimateFloatAsState.getValue().floatValue() == 1.0f);
                    }
                });
                $composer3.updateRememberedValue(value$iv$iv2);
            }
            $composer3.endReplaceableGroup();
            useFullScreenShape$delegate = (State) value$iv$iv2;
            Object key1$iv2 = Boolean.valueOf(SearchBar_Id_Pb_0$lambda$2(useFullScreenShape$delegate));
            int i13 = ($dirty1 << 3) & 112;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(key1$iv2) | $composer3.changed(shape3);
            value$iv$iv3 = $composer3.rememberedValue();
            if (!invalid$iv$iv) {
            }
            if (Intrinsics.areEqual(shape3, defaultInputFieldShape)) {
                value$iv$iv3 = new GenericShape(new Function3<Path, Size, LayoutDirection, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$animatedShape$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Path path, Size size, LayoutDirection layoutDirection) {
                        m1702invoke12SF9DM(path, size.getPackedValue(), layoutDirection);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-12SF9DM, reason: not valid java name */
                    public final void m1702invoke12SF9DM(Path $receiver, long size, LayoutDirection layoutDirection) {
                        Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                        Intrinsics.checkNotNullParameter(layoutDirection, "<anonymous parameter 1>");
                        Density $this$invoke_12SF9DM_u24lambda_u240 = density;
                        State<Float> state = stateAnimateFloatAsState;
                        float arg0$iv = SearchBarKt.SearchBarCornerRadius;
                        float other$iv = 1 - state.getValue().floatValue();
                        float radius = $this$invoke_12SF9DM_u24lambda_u240.mo327toPx0680j_4(Dp.m5274constructorimpl(arg0$iv * other$iv));
                        $receiver.addRoundRect(RoundRectKt.m2786RoundRectsniSvfs(SizeKt.m2821toRectuvyYCjk(size), CornerRadiusKt.CornerRadius$default(radius, 0.0f, 2, null)));
                    }
                });
            } else if (SearchBar_Id_Pb_0$lambda$2(useFullScreenShape$delegate)) {
                value$iv$iv3 = defaultFullScreenShape;
            } else {
                value$iv$iv3 = shape3;
            }
            $composer3.updateRememberedValue(value$iv$iv3);
            $composer3.endReplaceableGroup();
            Shape animatedShape2 = (Shape) value$iv$iv3;
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            value$iv$iv4 = $composer3.rememberedValue();
            if (value$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv4 = new MutableWindowInsets(null, 1, null);
                $composer3.updateRememberedValue(value$iv$iv4);
            }
            $composer3.endReplaceableGroup();
            final MutableWindowInsets unconsumedInsets2 = (MutableWindowInsets) value$iv$iv4;
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer3.changed(density);
            Object it$iv$iv5 = $composer3.rememberedValue();
            if (invalid$iv$iv2) {
            }
            value$iv$iv5 = SnapshotStateKt.derivedStateOf(new Function0<Dp>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$topPadding$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Dp invoke() {
                    return Dp.m5272boximpl(m1703invokeD9Ej5fM());
                }

                /* JADX INFO: renamed from: invoke-D9Ej5fM, reason: not valid java name */
                public final float m1703invokeD9Ej5fM() {
                    float arg0$iv = SearchBarKt.getSearchBarVerticalPadding();
                    float other$iv = WindowInsetsKt.asPaddingValues(unconsumedInsets2, density).getTop();
                    return Dp.m5274constructorimpl(arg0$iv + other$iv);
                }
            });
            $composer3.updateRememberedValue(value$iv$iv5);
            $composer3.endReplaceableGroup();
            final State<Dp> topPadding2 = (State) value$iv$iv5;
            long containerColor2 = colors4.getContainerColor();
            long jM1426contentColorForek8zF_U2 = ColorSchemeKt.m1426contentColorForek8zF_U(colors4.getContainerColor(), $composer3, 0);
            Modifier modifierZIndex2 = ZIndexModifierKt.zIndex(modifier3, 1.0f);
            int i14 = (($dirty1 >> 6) & 112) | 6;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv3 = $composer3.changed(unconsumedInsets2) | $composer3.changed(windowInsets3);
            value$iv$iv6 = $composer3.rememberedValue();
            if (!invalid$iv$iv3) {
            }
            value$iv$iv6 = (Function1) new Function1<WindowInsets, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(WindowInsets windowInsets9) {
                    invoke2(windowInsets9);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(WindowInsets consumedInsets) {
                    Intrinsics.checkNotNullParameter(consumedInsets, "consumedInsets");
                    unconsumedInsets2.setInsets(WindowInsetsKt.exclude(windowInsets3, consumedInsets));
                }
            };
            $composer3.updateRememberedValue(value$iv$iv6);
            $composer3.endReplaceableGroup();
            Modifier modifierConsumeWindowInsets2 = WindowInsetsPaddingKt.consumeWindowInsets(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifierZIndex2, (Function1) value$iv$iv6), unconsumedInsets2);
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv4 = $composer3.changed(topPadding2) | $composer3.changed(stateAnimateFloatAsState);
            Object it$iv$iv6 = $composer3.rememberedValue();
            if (invalid$iv$iv4) {
            }
            value$iv$iv7 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                    return m1701invoke3p2s80s(measureScope, measurable, constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                public final MeasureResult m1701invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                    Intrinsics.checkNotNullParameter(layout, "$this$layout");
                    Intrinsics.checkNotNullParameter(measurable, "measurable");
                    final int animatedTopPadding = layout.mo321roundToPx0680j_4(DpKt.m5317lerpMdfbLM(topPadding2.getValue().m5288unboximpl(), Dp.m5274constructorimpl(0), stateAnimateFloatAsState.getValue().floatValue()));
                    int startWidth = RangesKt.coerceAtMost(Math.max(Constraints.m5220getMinWidthimpl(constraints), layout.mo321roundToPx0680j_4(SearchBarKt.getSearchBarMinWidth())), Math.min(Constraints.m5218getMaxWidthimpl(constraints), layout.mo321roundToPx0680j_4(SearchBarKt.SearchBarMaxWidth)));
                    int startHeight = RangesKt.coerceAtMost(Math.max(Constraints.m5219getMinHeightimpl(constraints), layout.mo321roundToPx0680j_4(SearchBarDefaults.INSTANCE.m1696getInputFieldHeightD9Ej5fM())), Constraints.m5217getMaxHeightimpl(constraints));
                    int endWidth = Constraints.m5218getMaxWidthimpl(constraints);
                    int endHeight = Constraints.m5217getMaxHeightimpl(constraints);
                    int width = MathHelpersKt.lerp(startWidth, endWidth, stateAnimateFloatAsState.getValue().floatValue());
                    int height = MathHelpersKt.lerp(startHeight, endHeight, stateAnimateFloatAsState.getValue().floatValue()) + animatedTopPadding;
                    final Placeable placeable = measurable.mo4225measureBRTryo0(ConstraintsKt.m5235offsetNN6EwU$default(Constraints.INSTANCE.m5226fixedJhjzzOo(width, height), 0, -animatedTopPadding, 1, null));
                    return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$3$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope layout2) {
                            Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                            Placeable.PlacementScope.placeRelative$default(layout2, placeable, 0, animatedTopPadding, 0.0f, 4, null);
                        }
                    }, 4, null);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv7);
            $composer3.endReplaceableGroup();
            Modifier modifierLayout2 = LayoutModifierKt.layout(modifierConsumeWindowInsets2, (Function3) value$iv$iv7);
            final boolean z3 = enabled3;
            final Function2<? super Composer, ? super Integer, Unit> function110 = function8;
            final Function2<? super Composer, ? super Integer, Unit> function111 = function9;
            final int $dirty9 = $dirty1;
            final Function2<? super Composer, ? super Integer, Unit> function112 = function10;
            final SearchBarColors searchBarColors3 = colors4;
            windowInsets4 = windowInsets3;
            final MutableInteractionSource mutableInteractionSource4 = interactionSource2;
            modifier4 = modifier3;
            Function2<Composer, Integer, Unit> function113 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$4
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
                    Object value$iv$iv9;
                    Object value$iv$iv10;
                    Object value$iv$iv11;
                    ComposerKt.sourceInformation($composer4, "C251@12445L1142:SearchBar.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-867266817, $changed2, -1, "androidx.compose.material3.SearchBar.<anonymous> (SearchBar.kt:250)");
                        }
                        String str = query;
                        Function1<String, Unit> function1 = onQueryChange;
                        Function1<String, Unit> function114 = onSearch;
                        boolean z4 = active;
                        Function1<Boolean, Unit> function115 = onActiveChange;
                        boolean z5 = z3;
                        Function2<Composer, Integer, Unit> function21 = function110;
                        Function2<Composer, Integer, Unit> function22 = function111;
                        Function2<Composer, Integer, Unit> function23 = function112;
                        SearchBarColors searchBarColors4 = searchBarColors3;
                        MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource4;
                        int i15 = $dirty8;
                        int $i$f$Layout = $dirty9;
                        final State<Float> state = stateAnimateFloatAsState;
                        State<Dp> state2 = topPadding2;
                        Function3<ColumnScope, Composer, Integer, Unit> function24 = content;
                        $composer4.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume5 = $composer4.consume(localDensity3);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume5;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume6 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume6;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume7 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume7;
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
                        int i16 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        int i17 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -685095508, "C252@12498L93,255@12604L590,270@13227L115:SearchBar.kt#uh7d8r");
                        $composer4.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer4, "CC(remember):Composables.kt#9igjgp");
                        Object it$iv$iv7 = $composer4.rememberedValue();
                        if (it$iv$iv7 == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv9 = new AnimatedPaddingValues(state, state2);
                            $composer4.updateRememberedValue(value$iv$iv9);
                        } else {
                            value$iv$iv9 = it$iv$iv7;
                        }
                        $composer4.endReplaceableGroup();
                        AnimatedPaddingValues animatedInputFieldPadding = (AnimatedPaddingValues) value$iv$iv9;
                        SearchBarKt.SearchBarInputField(str, function1, function114, z4, function115, PaddingKt.padding(Modifier.INSTANCE, animatedInputFieldPadding), z5, function21, function22, function23, searchBarColors4.getInputFieldColors(), mutableInteractionSource5, $composer4, (i15 & 1879048192) | (i15 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i15 & 112) | (i15 & 896) | (i15 & 7168) | (57344 & i15) | (3670016 & i15) | (29360128 & i15) | (234881024 & i15), ($i$f$Layout >> 9) & 112, 0);
                        $composer4.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer4, "CC(remember):Composables.kt#9igjgp");
                        Object it$iv$iv8 = $composer4.rememberedValue();
                        if (it$iv$iv8 == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv10 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$4$1$showResults$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    return Boolean.valueOf(state.getValue().floatValue() > 0.0f);
                                }
                            });
                            $composer4.updateRememberedValue(value$iv$iv10);
                        } else {
                            value$iv$iv10 = it$iv$iv8;
                        }
                        $composer4.endReplaceableGroup();
                        State showResults$delegate = (State) value$iv$iv10;
                        $composer4.startReplaceableGroup(551421060);
                        ComposerKt.sourceInformation($composer4, "274@13420L35,274@13390L173");
                        if (invoke$lambda$5$lambda$2(showResults$delegate)) {
                            Modifier.Companion companion = Modifier.INSTANCE;
                            $composer4.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv$iv6 = $composer4.changed(state);
                            Object it$iv$iv9 = $composer4.rememberedValue();
                            if (invalid$iv$iv6 || it$iv$iv9 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv11 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$4$1$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                        invoke2(graphicsLayerScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(GraphicsLayerScope graphicsLayer) {
                                        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
                                        graphicsLayer.setAlpha(state.getValue().floatValue());
                                    }
                                };
                                $composer4.updateRememberedValue(value$iv$iv11);
                            } else {
                                value$iv$iv11 = it$iv$iv9;
                            }
                            $composer4.endReplaceableGroup();
                            Modifier modifier$iv2 = GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) value$iv$iv11);
                            $composer4.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv2 = (0 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume8 = $composer4.consume(localDensity4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density density$iv$iv2 = (Density) objConsume8;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume9 = $composer4.consume(localLayoutDirection2);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume9;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume10 = $composer4.consume(localViewConfiguration2);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume10;
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                $composer4.createNode(constructor2);
                            } else {
                                $composer4.useNode();
                            }
                            $composer4.disableReusing();
                            Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer4);
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                            $composer4.enableReusing();
                            function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i18 = ($changed$iv$iv$iv2 >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                            ColumnScope $this$invoke_u24lambda_u245_u24lambda_u244 = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer4, 1234674882, "C275@13479L36,276@13536L9:SearchBar.kt#uh7d8r");
                            DividerKt.m1508Divider9IZ8Weo(null, 0.0f, searchBarColors4.getDividerColor(), $composer4, 0, 3);
                            function24.invoke($this$invoke_u24lambda_u245_u24lambda_u244, $composer4, Integer.valueOf(((((0 >> 6) & 112) | 6) & 14) | (($i$f$Layout >> 12) & 112)));
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                        }
                        $composer4.endReplaceableGroup();
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

                private static final boolean invoke$lambda$5$lambda$2(State<Boolean> state) {
                    Object thisObj$iv = state.getValue();
                    return ((Boolean) thisObj$iv).booleanValue();
                }
            };
            $composer2 = $composer3;
            SurfaceKt.m1806SurfaceT9BRK9s(modifierLayout2, animatedShape2, containerColor2, jM1426contentColorForek8zF_U2, tonalElevation3, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -867266817, true, function113), $composer2, (($dirty9 << 6) & 57344) | 12582912, 96);
            EffectsKt.LaunchedEffect(Boolean.valueOf(active), new SearchBarKt$SearchBar$5(active, focusManager2, null), $composer2, (($dirty8 >> 9) & 14) | 64);
            int i15 = ($dirty8 >> 12) & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv5 = $composer2.changed(onActiveChange);
            Object it$iv$iv7 = $composer2.rememberedValue();
            if (invalid$iv$iv5) {
                value$iv$iv8 = new Function0<Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$6$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        onActiveChange.invoke(false);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv8);
            } else {
                value$iv$iv8 = new Function0<Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$6$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        onActiveChange.invoke(false);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv8);
            }
            $composer2.endReplaceableGroup();
            BackHandlerKt.BackHandler(active, (Function0) value$iv$iv8, $composer2, ($dirty8 >> 9) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors5 = colors4;
            tonalElevation4 = tonalElevation3;
            interactionSource3 = interactionSource2;
            function11 = function8;
            function12 = function9;
            function13 = function10;
            enabled4 = enabled3;
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
                    function5 = null;
                } else {
                    function5 = function2;
                }
                if (i6 != 0) {
                    function6 = null;
                } else {
                    function6 = function3;
                }
                if (i7 != 0) {
                    function7 = null;
                } else {
                    function7 = function4;
                }
                if ((i & 1024) != 0) {
                    shape2 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
                    $dirty2 &= -15;
                } else {
                    shape2 = shape;
                }
                if ((i & 2048) != 0) {
                    colors2 = SearchBarDefaults.INSTANCE.m1694colorsKlgxPg(0L, 0L, null, $composer3, 3072, 7);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                if (i8 != 0) {
                    tonalElevation2 = SearchBarDefaults.INSTANCE.m1695getElevationD9Ej5fM();
                } else {
                    tonalElevation2 = tonalElevation;
                }
                colors3 = colors2;
                if ((i & 8192) != 0) {
                    windowInsets2 = SearchBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    windowInsets2 = windowInsets;
                }
                if (i9 != 0) {
                    WindowInsets windowInsets9 = windowInsets2;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty10 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    function8 = function5;
                    colors4 = colors3;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    function9 = function6;
                    function10 = function7;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    tonalElevation3 = tonalElevation2;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets9;
                    $dirty1 = $dirty10;
                } else {
                    function8 = function5;
                    colors4 = colors3;
                    function9 = function6;
                    function10 = function7;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    tonalElevation3 = tonalElevation2;
                    interactionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets2;
                    $dirty1 = $dirty2;
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
                    function5 = null;
                } else {
                    function5 = function2;
                }
                if (i6 != 0) {
                    function6 = null;
                } else {
                    function6 = function3;
                }
                if (i7 != 0) {
                    function7 = null;
                } else {
                    function7 = function4;
                }
                if ((i & 1024) != 0) {
                    shape2 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
                    $dirty2 &= -15;
                } else {
                    shape2 = shape;
                }
                if ((i & 2048) != 0) {
                    colors2 = SearchBarDefaults.INSTANCE.m1694colorsKlgxPg(0L, 0L, null, $composer3, 3072, 7);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                if (i8 != 0) {
                    tonalElevation2 = SearchBarDefaults.INSTANCE.m1695getElevationD9Ej5fM();
                } else {
                    tonalElevation2 = tonalElevation;
                }
                colors3 = colors2;
                if ((i & 8192) != 0) {
                    windowInsets2 = SearchBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty2 &= -7169;
                } else {
                    windowInsets2 = windowInsets;
                }
                if (i9 != 0) {
                    WindowInsets windowInsets10 = windowInsets2;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    int $dirty11 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    function8 = function5;
                    colors4 = colors3;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    function9 = function6;
                    function10 = function7;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    tonalElevation3 = tonalElevation2;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets10;
                    $dirty1 = $dirty11;
                } else {
                    function8 = function5;
                    colors4 = colors3;
                    function9 = function6;
                    function10 = function7;
                    enabled3 = enabled2;
                    shape3 = shape2;
                    tonalElevation3 = tonalElevation2;
                    interactionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets2;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(156000634, $dirty, $dirty1, "androidx.compose.material3.SearchBar (SearchBar.kt:159)");
            }
            if (active) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            if (active) {
                finiteAnimationSpec = AnimationEnterFloatSpec;
            } else {
                finiteAnimationSpec = AnimationExitFloatSpec;
            }
            stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, finiteAnimationSpec, 0.0f, null, null, $composer3, 64, 28);
            ProvidableCompositionLocal<FocusManager> localFocusManager3 = CompositionLocalsKt.getLocalFocusManager();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume5 = $composer3.consume(localFocusManager3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            FocusManager focusManager3 = (FocusManager) objConsume5;
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            final int $dirty12 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer3.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            density = (Density) objConsume6;
            defaultInputFieldShape = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
            defaultFullScreenShape = SearchBarDefaults.INSTANCE.getFullScreenShape($composer3, 6);
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            value$iv$iv2 = $composer3.rememberedValue();
            if (value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$useFullScreenShape$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(stateAnimateFloatAsState.getValue().floatValue() == 1.0f);
                    }
                });
                $composer3.updateRememberedValue(value$iv$iv2);
            }
            $composer3.endReplaceableGroup();
            useFullScreenShape$delegate = (State) value$iv$iv2;
            Object key1$iv3 = Boolean.valueOf(SearchBar_Id_Pb_0$lambda$2(useFullScreenShape$delegate));
            int i16 = ($dirty1 << 3) & 112;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(key1$iv3) | $composer3.changed(shape3);
            value$iv$iv3 = $composer3.rememberedValue();
            if (!invalid$iv$iv) {
            }
            if (Intrinsics.areEqual(shape3, defaultInputFieldShape)) {
                value$iv$iv3 = new GenericShape(new Function3<Path, Size, LayoutDirection, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$animatedShape$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Path path, Size size, LayoutDirection layoutDirection) {
                        m1702invoke12SF9DM(path, size.getPackedValue(), layoutDirection);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-12SF9DM, reason: not valid java name */
                    public final void m1702invoke12SF9DM(Path $receiver, long size, LayoutDirection layoutDirection) {
                        Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                        Intrinsics.checkNotNullParameter(layoutDirection, "<anonymous parameter 1>");
                        Density $this$invoke_12SF9DM_u24lambda_u240 = density;
                        State<Float> state = stateAnimateFloatAsState;
                        float arg0$iv = SearchBarKt.SearchBarCornerRadius;
                        float other$iv = 1 - state.getValue().floatValue();
                        float radius = $this$invoke_12SF9DM_u24lambda_u240.mo327toPx0680j_4(Dp.m5274constructorimpl(arg0$iv * other$iv));
                        $receiver.addRoundRect(RoundRectKt.m2786RoundRectsniSvfs(SizeKt.m2821toRectuvyYCjk(size), CornerRadiusKt.CornerRadius$default(radius, 0.0f, 2, null)));
                    }
                });
            } else if (SearchBar_Id_Pb_0$lambda$2(useFullScreenShape$delegate)) {
                value$iv$iv3 = defaultFullScreenShape;
            } else {
                value$iv$iv3 = shape3;
            }
            $composer3.updateRememberedValue(value$iv$iv3);
            $composer3.endReplaceableGroup();
            Shape animatedShape3 = (Shape) value$iv$iv3;
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            value$iv$iv4 = $composer3.rememberedValue();
            if (value$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv4 = new MutableWindowInsets(null, 1, null);
                $composer3.updateRememberedValue(value$iv$iv4);
            }
            $composer3.endReplaceableGroup();
            final MutableWindowInsets unconsumedInsets3 = (MutableWindowInsets) value$iv$iv4;
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer3.changed(density);
            Object it$iv$iv8 = $composer3.rememberedValue();
            if (invalid$iv$iv2) {
            }
            value$iv$iv5 = SnapshotStateKt.derivedStateOf(new Function0<Dp>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$topPadding$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Dp invoke() {
                    return Dp.m5272boximpl(m1703invokeD9Ej5fM());
                }

                /* JADX INFO: renamed from: invoke-D9Ej5fM, reason: not valid java name */
                public final float m1703invokeD9Ej5fM() {
                    float arg0$iv = SearchBarKt.getSearchBarVerticalPadding();
                    float other$iv = WindowInsetsKt.asPaddingValues(unconsumedInsets3, density).getTop();
                    return Dp.m5274constructorimpl(arg0$iv + other$iv);
                }
            });
            $composer3.updateRememberedValue(value$iv$iv5);
            $composer3.endReplaceableGroup();
            final State<Dp> topPadding3 = (State) value$iv$iv5;
            long containerColor3 = colors4.getContainerColor();
            long jM1426contentColorForek8zF_U3 = ColorSchemeKt.m1426contentColorForek8zF_U(colors4.getContainerColor(), $composer3, 0);
            Modifier modifierZIndex3 = ZIndexModifierKt.zIndex(modifier3, 1.0f);
            int i17 = (($dirty1 >> 6) & 112) | 6;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv3 = $composer3.changed(unconsumedInsets3) | $composer3.changed(windowInsets3);
            value$iv$iv6 = $composer3.rememberedValue();
            if (!invalid$iv$iv3) {
            }
            value$iv$iv6 = (Function1) new Function1<WindowInsets, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(WindowInsets windowInsets11) {
                    invoke2(windowInsets11);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(WindowInsets consumedInsets) {
                    Intrinsics.checkNotNullParameter(consumedInsets, "consumedInsets");
                    unconsumedInsets3.setInsets(WindowInsetsKt.exclude(windowInsets3, consumedInsets));
                }
            };
            $composer3.updateRememberedValue(value$iv$iv6);
            $composer3.endReplaceableGroup();
            Modifier modifierConsumeWindowInsets3 = WindowInsetsPaddingKt.consumeWindowInsets(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifierZIndex3, (Function1) value$iv$iv6), unconsumedInsets3);
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv4 = $composer3.changed(topPadding3) | $composer3.changed(stateAnimateFloatAsState);
            Object it$iv$iv9 = $composer3.rememberedValue();
            if (invalid$iv$iv4) {
            }
            value$iv$iv7 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                    return m1701invoke3p2s80s(measureScope, measurable, constraints.getValue());
                }

                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                public final MeasureResult m1701invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                    Intrinsics.checkNotNullParameter(layout, "$this$layout");
                    Intrinsics.checkNotNullParameter(measurable, "measurable");
                    final int animatedTopPadding = layout.mo321roundToPx0680j_4(DpKt.m5317lerpMdfbLM(topPadding3.getValue().m5288unboximpl(), Dp.m5274constructorimpl(0), stateAnimateFloatAsState.getValue().floatValue()));
                    int startWidth = RangesKt.coerceAtMost(Math.max(Constraints.m5220getMinWidthimpl(constraints), layout.mo321roundToPx0680j_4(SearchBarKt.getSearchBarMinWidth())), Math.min(Constraints.m5218getMaxWidthimpl(constraints), layout.mo321roundToPx0680j_4(SearchBarKt.SearchBarMaxWidth)));
                    int startHeight = RangesKt.coerceAtMost(Math.max(Constraints.m5219getMinHeightimpl(constraints), layout.mo321roundToPx0680j_4(SearchBarDefaults.INSTANCE.m1696getInputFieldHeightD9Ej5fM())), Constraints.m5217getMaxHeightimpl(constraints));
                    int endWidth = Constraints.m5218getMaxWidthimpl(constraints);
                    int endHeight = Constraints.m5217getMaxHeightimpl(constraints);
                    int width = MathHelpersKt.lerp(startWidth, endWidth, stateAnimateFloatAsState.getValue().floatValue());
                    int height = MathHelpersKt.lerp(startHeight, endHeight, stateAnimateFloatAsState.getValue().floatValue()) + animatedTopPadding;
                    final Placeable placeable = measurable.mo4225measureBRTryo0(ConstraintsKt.m5235offsetNN6EwU$default(Constraints.INSTANCE.m5226fixedJhjzzOo(width, height), 0, -animatedTopPadding, 1, null));
                    return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$3$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope layout2) {
                            Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                            Placeable.PlacementScope.placeRelative$default(layout2, placeable, 0, animatedTopPadding, 0.0f, 4, null);
                        }
                    }, 4, null);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv7);
            $composer3.endReplaceableGroup();
            Modifier modifierLayout3 = LayoutModifierKt.layout(modifierConsumeWindowInsets3, (Function3) value$iv$iv7);
            final boolean z4 = enabled3;
            final Function2<? super Composer, ? super Integer, Unit> function114 = function8;
            final Function2<? super Composer, ? super Integer, Unit> function115 = function9;
            final int $dirty13 = $dirty1;
            final Function2<? super Composer, ? super Integer, Unit> function116 = function10;
            final SearchBarColors searchBarColors4 = colors4;
            windowInsets4 = windowInsets3;
            final MutableInteractionSource mutableInteractionSource5 = interactionSource2;
            modifier4 = modifier3;
            Function2<Composer, Integer, Unit> function117 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$4
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
                    Object value$iv$iv9;
                    Object value$iv$iv10;
                    Object value$iv$iv11;
                    ComposerKt.sourceInformation($composer4, "C251@12445L1142:SearchBar.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-867266817, $changed2, -1, "androidx.compose.material3.SearchBar.<anonymous> (SearchBar.kt:250)");
                        }
                        String str = query;
                        Function1<String, Unit> function1 = onQueryChange;
                        Function1<String, Unit> function118 = onSearch;
                        boolean z5 = active;
                        Function1<Boolean, Unit> function119 = onActiveChange;
                        boolean z6 = z4;
                        Function2<Composer, Integer, Unit> function21 = function114;
                        Function2<Composer, Integer, Unit> function22 = function115;
                        Function2<Composer, Integer, Unit> function23 = function116;
                        SearchBarColors searchBarColors5 = searchBarColors4;
                        MutableInteractionSource mutableInteractionSource6 = mutableInteractionSource5;
                        int i18 = $dirty12;
                        int $i$f$Layout = $dirty13;
                        final State<Float> state = stateAnimateFloatAsState;
                        State<Dp> state2 = topPadding3;
                        Function3<ColumnScope, Composer, Integer, Unit> function24 = content;
                        $composer4.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume7 = $composer4.consume(localDensity4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume7;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume8 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume8;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume9 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume9;
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
                        int i19 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        int i110 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -685095508, "C252@12498L93,255@12604L590,270@13227L115:SearchBar.kt#uh7d8r");
                        $composer4.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer4, "CC(remember):Composables.kt#9igjgp");
                        Object it$iv$iv10 = $composer4.rememberedValue();
                        if (it$iv$iv10 == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv9 = new AnimatedPaddingValues(state, state2);
                            $composer4.updateRememberedValue(value$iv$iv9);
                        } else {
                            value$iv$iv9 = it$iv$iv10;
                        }
                        $composer4.endReplaceableGroup();
                        AnimatedPaddingValues animatedInputFieldPadding = (AnimatedPaddingValues) value$iv$iv9;
                        SearchBarKt.SearchBarInputField(str, function1, function118, z5, function119, PaddingKt.padding(Modifier.INSTANCE, animatedInputFieldPadding), z6, function21, function22, function23, searchBarColors5.getInputFieldColors(), mutableInteractionSource6, $composer4, (i18 & 1879048192) | (i18 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i18 & 112) | (i18 & 896) | (i18 & 7168) | (57344 & i18) | (3670016 & i18) | (29360128 & i18) | (234881024 & i18), ($i$f$Layout >> 9) & 112, 0);
                        $composer4.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer4, "CC(remember):Composables.kt#9igjgp");
                        Object it$iv$iv11 = $composer4.rememberedValue();
                        if (it$iv$iv11 == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv10 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$4$1$showResults$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    return Boolean.valueOf(state.getValue().floatValue() > 0.0f);
                                }
                            });
                            $composer4.updateRememberedValue(value$iv$iv10);
                        } else {
                            value$iv$iv10 = it$iv$iv11;
                        }
                        $composer4.endReplaceableGroup();
                        State showResults$delegate = (State) value$iv$iv10;
                        $composer4.startReplaceableGroup(551421060);
                        ComposerKt.sourceInformation($composer4, "274@13420L35,274@13390L173");
                        if (invoke$lambda$5$lambda$2(showResults$delegate)) {
                            Modifier.Companion companion = Modifier.INSTANCE;
                            $composer4.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv$iv6 = $composer4.changed(state);
                            Object it$iv$iv12 = $composer4.rememberedValue();
                            if (invalid$iv$iv6 || it$iv$iv12 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv11 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$4$1$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                                        invoke2(graphicsLayerScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(GraphicsLayerScope graphicsLayer) {
                                        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
                                        graphicsLayer.setAlpha(state.getValue().floatValue());
                                    }
                                };
                                $composer4.updateRememberedValue(value$iv$iv11);
                            } else {
                                value$iv$iv11 = it$iv$iv12;
                            }
                            $composer4.endReplaceableGroup();
                            Modifier modifier$iv2 = GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) value$iv$iv11);
                            $composer4.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv2 = (0 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume10 = $composer4.consume(localDensity5);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density density$iv$iv2 = (Density) objConsume10;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume11 = $composer4.consume(localLayoutDirection2);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume11;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume12 = $composer4.consume(localViewConfiguration2);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume12;
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                $composer4.createNode(constructor2);
                            } else {
                                $composer4.useNode();
                            }
                            $composer4.disableReusing();
                            Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer4);
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                            $composer4.enableReusing();
                            function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i111 = ($changed$iv$iv$iv2 >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                            ColumnScope $this$invoke_u24lambda_u245_u24lambda_u244 = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer4, 1234674882, "C275@13479L36,276@13536L9:SearchBar.kt#uh7d8r");
                            DividerKt.m1508Divider9IZ8Weo(null, 0.0f, searchBarColors5.getDividerColor(), $composer4, 0, 3);
                            function24.invoke($this$invoke_u24lambda_u245_u24lambda_u244, $composer4, Integer.valueOf(((((0 >> 6) & 112) | 6) & 14) | (($i$f$Layout >> 12) & 112)));
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
                            $composer4.endReplaceableGroup();
                        }
                        $composer4.endReplaceableGroup();
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

                private static final boolean invoke$lambda$5$lambda$2(State<Boolean> state) {
                    Object thisObj$iv = state.getValue();
                    return ((Boolean) thisObj$iv).booleanValue();
                }
            };
            $composer2 = $composer3;
            SurfaceKt.m1806SurfaceT9BRK9s(modifierLayout3, animatedShape3, containerColor3, jM1426contentColorForek8zF_U3, tonalElevation3, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -867266817, true, function117), $composer2, (($dirty13 << 6) & 57344) | 12582912, 96);
            EffectsKt.LaunchedEffect(Boolean.valueOf(active), new SearchBarKt$SearchBar$5(active, focusManager3, null), $composer2, (($dirty12 >> 9) & 14) | 64);
            int i18 = ($dirty12 >> 12) & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv5 = $composer2.changed(onActiveChange);
            Object it$iv$iv10 = $composer2.rememberedValue();
            if (invalid$iv$iv5) {
                value$iv$iv8 = new Function0<Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$6$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        onActiveChange.invoke(false);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv8);
            } else {
                value$iv$iv8 = new Function0<Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$6$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        onActiveChange.invoke(false);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv8);
            }
            $composer2.endReplaceableGroup();
            BackHandlerKt.BackHandler(active, (Function0) value$iv$iv8, $composer2, ($dirty12 >> 9) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors5 = colors4;
            tonalElevation4 = tonalElevation3;
            interactionSource3 = interactionSource2;
            function11 = function8;
            function12 = function9;
            function13 = function10;
            enabled4 = enabled3;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final boolean z5 = enabled4;
        final Function2<? super Composer, ? super Integer, Unit> function118 = function11;
        final Function2<? super Composer, ? super Integer, Unit> function119 = function12;
        final Function2<? super Composer, ? super Integer, Unit> function21 = function13;
        final Shape shape5 = shape3;
        final SearchBarColors searchBarColors5 = colors5;
        final float f3 = tonalElevation4;
        final WindowInsets windowInsets11 = windowInsets4;
        final MutableInteractionSource mutableInteractionSource6 = interactionSource3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$7
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
                SearchBarKt.m1700SearchBarId_Pb_0(query, onQueryChange, onSearch, active, onActiveChange, modifier6, z5, function118, function119, function21, shape5, searchBarColors5, f3, windowInsets11, mutableInteractionSource6, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean SearchBar_Id_Pb_0$lambda$2(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: renamed from: DockedSearchBar-rpjkMjA, reason: not valid java name */
    public static final void m1699DockedSearchBarrpjkMjA(final String query, final Function1<? super String, Unit> onQueryChange, final Function1<? super String, Unit> onSearch, final boolean active, final Function1<? super Boolean, Unit> onActiveChange, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Shape shape, SearchBarColors colors, float tonalElevation, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int $changed1, final int i) {
        Shape shape2;
        SearchBarColors colors2;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Shape shape3;
        int $dirty1;
        MutableInteractionSource interactionSource2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        boolean enabled2;
        float tonalElevation2;
        SearchBarColors colors3;
        Modifier modifier2;
        Object value$iv$iv;
        Composer $composer2;
        Modifier modifier3;
        Object value$iv$iv2;
        SearchBarColors colors4;
        float tonalElevation3;
        MutableInteractionSource interactionSource3;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function2<? super Composer, ? super Integer, Unit> function9;
        Shape shape4;
        boolean enabled3;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(onQueryChange, "onQueryChange");
        Intrinsics.checkNotNullParameter(onSearch, "onSearch");
        Intrinsics.checkNotNullParameter(onActiveChange, "onActiveChange");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(-451213062);
        ComposerKt.sourceInformation($composer3, "C(DockedSearchBar)P(11,8,9!1,7,6,3,10,5,14,12!1,13:c#ui.unit.Dp,4)353@17339L11,354@17400L8,356@17514L39,359@17649L7,364@17756L38,361@17662L1564,406@19232L306,415@19574L37,415@19544L67:SearchBar.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(query) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer3.changedInstance(onQueryChange) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer3.changedInstance(onSearch) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(active) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty |= $composer3.changedInstance(onActiveChange) ? 16384 : 8192;
        }
        int i2 = i & 32;
        if (i2 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty |= $composer3.changed(modifier) ? 131072 : 65536;
        }
        int i3 = i & 64;
        if (i3 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty |= $composer3.changed(enabled) ? 1048576 : 524288;
        }
        int i4 = i & 128;
        if (i4 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        }
        int i5 = i & 256;
        if (i5 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i6 = i & 512;
        if (i6 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 536870912 : 268435456;
        }
        if (($changed1 & 14) == 0) {
            $dirty2 |= ((i & 1024) == 0 && $composer3.changed(shape)) ? 4 : 2;
        }
        if (($changed1 & 112) == 0) {
            $dirty2 |= ((i & 2048) == 0 && $composer3.changed(colors)) ? 32 : 16;
        }
        int i7 = i & 4096;
        if (i7 != 0) {
            $dirty2 |= 384;
        } else if (($changed1 & 896) == 0) {
            $dirty2 |= $composer3.changed(tonalElevation) ? 256 : 128;
        }
        int i8 = i & 8192;
        if (i8 != 0) {
            $dirty2 |= 3072;
        } else if (($changed1 & 7168) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 2048 : 1024;
        }
        if ((i & 16384) != 0) {
            $dirty2 |= 24576;
        } else if (($changed1 & 57344) == 0) {
            $dirty2 |= $composer3.changedInstance(content) ? 16384 : 8192;
        }
        if ((1533916891 & $dirty) == 306783378 && (46811 & $dirty2) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier;
            enabled3 = enabled;
            function10 = function2;
            function8 = function3;
            function9 = function4;
            shape4 = shape;
            colors4 = colors;
            tonalElevation3 = tonalElevation;
            interactionSource3 = interactionSource;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                boolean enabled4 = i3 != 0 ? true : enabled;
                Function2<? super Composer, ? super Integer, Unit> function11 = i4 != 0 ? null : function2;
                Function2<? super Composer, ? super Integer, Unit> function12 = i5 != 0 ? null : function3;
                Function2<? super Composer, ? super Integer, Unit> function13 = i6 != 0 ? null : function4;
                if ((i & 1024) != 0) {
                    shape2 = SearchBarDefaults.INSTANCE.getDockedShape($composer3, 6);
                    $dirty2 &= -15;
                } else {
                    shape2 = shape;
                }
                if ((i & 2048) != 0) {
                    colors2 = SearchBarDefaults.INSTANCE.m1694colorsKlgxPg(0L, 0L, null, $composer3, 3072, 7);
                    $dirty2 &= -113;
                } else {
                    colors2 = colors;
                }
                float tonalElevation4 = i7 != 0 ? SearchBarDefaults.INSTANCE.m1695getElevationD9Ej5fM() : tonalElevation;
                if (i8 != 0) {
                    Shape shape5 = shape2;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Object it$iv$iv = $composer3.rememberedValue();
                    int $dirty3 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    function5 = function11;
                    shape3 = shape5;
                    $dirty1 = $dirty3;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    function6 = function12;
                    function7 = function13;
                    enabled2 = enabled4;
                    tonalElevation2 = tonalElevation4;
                    colors3 = colors2;
                    modifier2 = modifier4;
                } else {
                    function5 = function11;
                    shape3 = shape2;
                    $dirty1 = $dirty2;
                    interactionSource2 = interactionSource;
                    function6 = function12;
                    function7 = function13;
                    enabled2 = enabled4;
                    tonalElevation2 = tonalElevation4;
                    colors3 = colors2;
                    modifier2 = modifier4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 1024) != 0) {
                    $dirty2 &= -15;
                }
                if ((i & 2048) != 0) {
                    modifier2 = modifier;
                    enabled2 = enabled;
                    function5 = function2;
                    function6 = function3;
                    function7 = function4;
                    shape3 = shape;
                    colors3 = colors;
                    tonalElevation2 = tonalElevation;
                    interactionSource2 = interactionSource;
                    $dirty1 = $dirty2 & (-113);
                } else {
                    modifier2 = modifier;
                    enabled2 = enabled;
                    function5 = function2;
                    function6 = function3;
                    function7 = function4;
                    shape3 = shape;
                    colors3 = colors;
                    tonalElevation2 = tonalElevation;
                    interactionSource2 = interactionSource;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-451213062, $dirty, $dirty1, "androidx.compose.material3.DockedSearchBar (SearchBar.kt:342)");
            }
            ProvidableCompositionLocal<FocusManager> localFocusManager = CompositionLocalsKt.getLocalFocusManager();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localFocusManager);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            FocusManager focusManager = (FocusManager) objConsume;
            long containerColor = colors3.getContainerColor();
            long jM1426contentColorForek8zF_U = ColorSchemeKt.m1426contentColorForek8zF_U(colors3.getContainerColor(), $composer3, 0);
            final int $dirty4 = $dirty;
            $composer2 = $composer3;
            final boolean z = enabled2;
            final Function2<? super Composer, ? super Integer, Unit> function14 = function5;
            final Function2<? super Composer, ? super Integer, Unit> function15 = function6;
            final Function2<? super Composer, ? super Integer, Unit> function16 = function7;
            final SearchBarColors searchBarColors = colors3;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            final int $dirty5 = $dirty1;
            modifier3 = modifier2;
            SurfaceKt.m1806SurfaceT9BRK9s(androidx.compose.foundation.layout.SizeKt.m539width3ABfNKs(ZIndexModifierKt.zIndex(modifier2, 1.0f), SearchBarMinWidth), shape3, containerColor, jM1426contentColorForek8zF_U, tonalElevation2, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -1764436203, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$DockedSearchBar$2
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
                    ComposerKt.sourceInformation($composer4, "C370@17943L1277:SearchBar.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1764436203, $changed2, -1, "androidx.compose.material3.DockedSearchBar.<anonymous> (SearchBar.kt:369)");
                        }
                        String str = query;
                        Function1<String, Unit> function1 = onQueryChange;
                        Function1<String, Unit> function17 = onSearch;
                        boolean z2 = active;
                        Function1<Boolean, Unit> function18 = onActiveChange;
                        boolean z3 = z;
                        Function2<Composer, Integer, Unit> function19 = function14;
                        Function2<Composer, Integer, Unit> function20 = function15;
                        Function2<Composer, Integer, Unit> function21 = function16;
                        final SearchBarColors searchBarColors2 = searchBarColors;
                        MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
                        int i9 = $dirty4;
                        final int i10 = $dirty5;
                        final Function3<ColumnScope, Composer, Integer, Unit> function22 = content;
                        $composer4.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume2;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume3;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume4 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume4;
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
                        int i11 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                        ColumnScope $this$invoke_u24lambda_u240 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer4, 1253284504, "C371@17964L502,385@18480L730:SearchBar.kt#uh7d8r");
                        SearchBarKt.SearchBarInputField(str, function1, function17, z2, function18, null, z3, function19, function20, function21, searchBarColors2.getInputFieldColors(), mutableInteractionSource2, $composer4, (i9 & 14) | (i9 & 112) | (i9 & 896) | (i9 & 7168) | (57344 & i9) | (3670016 & i9) | (29360128 & i9) | (234881024 & i9) | (1879048192 & i9), (i10 >> 6) & 112, 32);
                        AnimatedVisibilityKt.AnimatedVisibility($this$invoke_u24lambda_u240, z2, (Modifier) null, SearchBarKt.DockedEnterTransition, SearchBarKt.DockedExitTransition, (String) null, ComposableLambdaKt.composableLambda($composer4, 393964167, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$DockedSearchBar$2$1$1
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
                                Object value$iv$iv3;
                                Object value$iv$iv4;
                                Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
                                ComposerKt.sourceInformation($composer5, "C*390@18696L7,391@18754L115,394@18902L110,398@19030L166:SearchBar.kt#uh7d8r");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(393964167, $changed3, -1, "androidx.compose.material3.DockedSearchBar.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:389)");
                                }
                                ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                Object objConsume5 = $composer5.consume(localConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                int $this$dp$iv = ((Configuration) objConsume5).screenHeightDp;
                                float screenHeight = Dp.m5274constructorimpl($this$dp$iv);
                                Object key1$iv = Dp.m5272boximpl(screenHeight);
                                $composer5.startReplaceableGroup(1157296644);
                                ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                boolean invalid$iv$iv = $composer5.changed(key1$iv);
                                Object it$iv$iv2 = $composer5.rememberedValue();
                                if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                                    float other$iv = Dp.m5274constructorimpl(screenHeight * 0.6666667f);
                                    value$iv$iv3 = Dp.m5272boximpl(other$iv);
                                    $composer5.updateRememberedValue(value$iv$iv3);
                                } else {
                                    value$iv$iv3 = it$iv$iv2;
                                }
                                $composer5.endReplaceableGroup();
                                float maxHeight = ((Dp) value$iv$iv3).m5288unboximpl();
                                Object key1$iv2 = Dp.m5272boximpl(maxHeight);
                                $composer5.startReplaceableGroup(1157296644);
                                ComposerKt.sourceInformation($composer5, "CC(remember)P(1):Composables.kt#9igjgp");
                                boolean invalid$iv$iv2 = $composer5.changed(key1$iv2);
                                Object it$iv$iv3 = $composer5.rememberedValue();
                                if (invalid$iv$iv2 || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                                    value$iv$iv4 = Dp.m5272boximpl(((Dp) RangesKt.coerceAtMost(Dp.m5272boximpl(SearchBarKt.getDockedActiveTableMinHeight()), Dp.m5272boximpl(maxHeight))).m5288unboximpl());
                                    $composer5.updateRememberedValue(value$iv$iv4);
                                } else {
                                    value$iv$iv4 = it$iv$iv3;
                                }
                                $composer5.endReplaceableGroup();
                                float minHeight = ((Dp) value$iv$iv4).m5288unboximpl();
                                Modifier modifier$iv2 = androidx.compose.foundation.layout.SizeKt.m521heightInVpY3zN4(Modifier.INSTANCE, minHeight, maxHeight);
                                SearchBarColors searchBarColors3 = searchBarColors2;
                                Function3<ColumnScope, Composer, Integer, Unit> function23 = function22;
                                int i12 = i10;
                                $composer5.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer5, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                int $changed$iv$iv2 = (0 << 3) & 112;
                                $composer5.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer5.consume(localDensity2);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                Density density$iv$iv2 = (Density) objConsume6;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume7 = $composer5.consume(localLayoutDirection2);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume7;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume8 = $composer5.consume(localViewConfiguration2);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume8;
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
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
                                int i13 = ($changed$iv$iv$iv2 >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                int $changed4 = ((0 >> 6) & 112) | 6;
                                ColumnScope $this$invoke_u24lambda_u242 = ColumnScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart($composer5, 1001846230, "C399@19112L36,400@19169L9:SearchBar.kt#uh7d8r");
                                DividerKt.m1508Divider9IZ8Weo(null, 0.0f, searchBarColors3.getDividerColor(), $composer5, 0, 3);
                                function23.invoke($this$invoke_u24lambda_u242, $composer5, Integer.valueOf(($changed4 & 14) | ((i12 >> 9) & 112)));
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
                        }), $composer4, ((((0 >> 6) & 112) | 6) & 14) | 1600512 | ((i9 >> 6) & 112), 18);
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
            }), $composer2, (($dirty5 << 3) & 112) | 12582912 | (($dirty5 << 6) & 57344), 96);
            EffectsKt.LaunchedEffect(Boolean.valueOf(active), new SearchBarKt$DockedSearchBar$3(active, focusManager, null), $composer2, (($dirty4 >> 9) & 14) | 64);
            int i9 = ($dirty4 >> 12) & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(onActiveChange);
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new Function0<Unit>() { // from class: androidx.compose.material3.SearchBarKt$DockedSearchBar$4$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        onActiveChange.invoke(false);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            BackHandlerKt.BackHandler(active, (Function0) value$iv$iv2, $composer2, ($dirty4 >> 9) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors4 = colors3;
            tonalElevation3 = tonalElevation2;
            interactionSource3 = interactionSource2;
            function8 = function6;
            function9 = function7;
            shape4 = shape3;
            enabled3 = enabled2;
            function10 = function5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final boolean z2 = enabled3;
        final Function2<? super Composer, ? super Integer, Unit> function17 = function10;
        final Function2<? super Composer, ? super Integer, Unit> function18 = function8;
        final Function2<? super Composer, ? super Integer, Unit> function19 = function9;
        final Shape shape6 = shape4;
        final SearchBarColors searchBarColors2 = colors4;
        final float f = tonalElevation3;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$DockedSearchBar$5
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
                SearchBarKt.m1699DockedSearchBarrpjkMjA(query, onQueryChange, onSearch, active, onActiveChange, modifier5, z2, function17, function18, function19, shape6, searchBarColors2, f, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:216:0x05bc  */
    public static final void SearchBarInputField(final String query, final Function1<? super String, Unit> function1, final Function1<? super String, Unit> function2, final boolean active, final Function1<? super Boolean, Unit> function3, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, TextFieldColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        TextFieldColors colors2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        MutableInteractionSource interactionSource2;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function2<? super Composer, ? super Integer, Unit> function9;
        boolean enabled2;
        int $dirty1;
        Modifier modifier2;
        TextFieldColors colors3;
        Object value$iv$iv;
        Object value$iv$iv2;
        long textColor;
        int $dirty;
        final TextFieldColors colors4;
        Modifier modifier3;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-1330051158);
        ComposerKt.sourceInformation($composer3, "C(SearchBarInputField)P(10,7,8!1,6,5,2,9,4,11)433@20114L18,434@20184L39,436@20254L29,437@20310L34,438@20385L39,450@20838L42,451@20904L304,463@21299L7,464@21384L28,466@21547L19,443@20594L2004:SearchBar.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty3 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changed(query) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer3.changed(active) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if ((57344 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 16384 : 8192;
        }
        int i2 = i & 32;
        if (i2 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 131072 : 65536;
        }
        int i3 = i & 64;
        if (i3 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 1048576 : 524288;
        }
        int i4 = i & 128;
        if (i4 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty2 |= $composer3.changedInstance(function4) ? 8388608 : 4194304;
        }
        int i5 = i & 256;
        if (i5 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty2 |= $composer3.changedInstance(function5) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i6 = i & 512;
        if (i6 != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty2 |= $composer3.changedInstance(function6) ? 536870912 : 268435456;
        }
        if (($changed1 & 14) == 0) {
            $dirty3 |= ((i & 1024) == 0 && $composer3.changed(colors)) ? 4 : 2;
        }
        int i7 = i & 2048;
        if (i7 != 0) {
            $dirty3 |= 48;
        } else if (($changed1 & 112) == 0) {
            $dirty3 |= $composer3.changed(interactionSource) ? 32 : 16;
        }
        if (($dirty2 & 1533916891) == 306783378 && ($dirty3 & 91) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier;
            enabled2 = enabled;
            function9 = function4;
            function7 = function5;
            function8 = function6;
            colors4 = colors;
            interactionSource2 = interactionSource;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                boolean enabled3 = i3 != 0 ? true : enabled;
                Function2<? super Composer, ? super Integer, Unit> function10 = i4 != 0 ? null : function4;
                Function2<? super Composer, ? super Integer, Unit> function11 = i5 != 0 ? null : function5;
                Function2<? super Composer, ? super Integer, Unit> function12 = i6 != 0 ? null : function6;
                if ((i & 1024) != 0) {
                    colors2 = SearchBarDefaults.INSTANCE.m1698inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 24576, 16383);
                    $dirty3 &= -15;
                } else {
                    colors2 = colors;
                }
                if (i7 != 0) {
                    Function2<? super Composer, ? super Integer, Unit> function13 = function11;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Object it$iv$iv = $composer3.rememberedValue();
                    int $dirty4 = $dirty3;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    function7 = function13;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    function8 = function12;
                    function9 = function10;
                    enabled2 = enabled3;
                    $dirty1 = $dirty4;
                    modifier2 = modifier4;
                    colors3 = colors2;
                } else {
                    function7 = function11;
                    interactionSource2 = interactionSource;
                    function8 = function12;
                    function9 = function10;
                    enabled2 = enabled3;
                    $dirty1 = $dirty3;
                    modifier2 = modifier4;
                    colors3 = colors2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 1024) != 0) {
                    $dirty3 &= -15;
                }
                modifier2 = modifier;
                enabled2 = enabled;
                function9 = function4;
                function7 = function5;
                function8 = function6;
                colors3 = colors;
                interactionSource2 = interactionSource;
                $dirty1 = $dirty3;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1330051158, $dirty2, $dirty1, "androidx.compose.material3.SearchBarInputField (SearchBar.kt:422)");
            }
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv2 = $composer3.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new FocusRequester();
                $composer3.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer3.endReplaceableGroup();
            final FocusRequester focusRequester = (FocusRequester) value$iv$iv2;
            final String searchSemantics = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1778getSearchBarSearchadMyvUU(), $composer3, 6);
            final String suggestionsAvailableSemantics = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1782getSuggestionsAvailableadMyvUU(), $composer3, 6);
            $composer3.startReplaceableGroup(462567106);
            ComposerKt.sourceInformation($composer3, "*439@20460L7,440@20502L74");
            ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localTextStyle);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            long $this$takeOrElse_u2dDxMtmZc$iv = ((TextStyle) objConsume).m4789getColor0d7_KjU();
            if (($this$takeOrElse_u2dDxMtmZc$iv != Color.INSTANCE.m3007getUnspecified0d7_KjU() ? 1 : 0) != 0) {
                textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            } else {
                textColor = colors3.textColor$material3_release(enabled2, false, interactionSource2, $composer3, (($dirty1 << 9) & 7168) | (($dirty2 >> 18) & 14) | 48 | (($dirty1 << 3) & 896)).getValue().m2981unboximpl();
            }
            $composer3.endReplaceableGroup();
            Modifier modifier5 = modifier2;
            Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(androidx.compose.foundation.layout.SizeKt.fillMaxWidth$default(androidx.compose.foundation.layout.SizeKt.m520height3ABfNKs(modifier2, SearchBarDefaults.INSTANCE.m1696getInputFieldHeightD9Ej5fM()), 0.0f, 1, null), focusRequester);
            int i8 = ($dirty2 >> 12) & 14;
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer3.changed(function3);
            Object value$iv$iv3 = $composer3.rememberedValue();
            if (invalid$iv$iv || value$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv3 = (Function1) new Function1<FocusState, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBarInputField$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                        invoke2(focusState);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FocusState it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        if (it.isFocused()) {
                            function3.invoke(true);
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv$iv3);
            }
            $composer3.endReplaceableGroup();
            Modifier modifierOnFocusChanged = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester, (Function1) value$iv$iv3);
            Object[] keys$iv = {searchSemantics, Boolean.valueOf(active), suggestionsAvailableSemantics, focusRequester};
            $composer3.startReplaceableGroup(-568225417);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            int length = keys$iv.length;
            boolean invalid$iv = false;
            int i9 = 0;
            while (i9 < length) {
                int i10 = length;
                Object key$iv = keys$iv[i9];
                invalid$iv |= $composer3.changed(key$iv);
                i9++;
                length = i10;
            }
            Object value$iv$iv4 = $composer3.rememberedValue();
            if (invalid$iv || value$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBarInputField$3$1
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
                        SemanticsPropertiesKt.setContentDescription(semantics, searchSemantics);
                        if (active) {
                            SemanticsPropertiesKt.setStateDescription(semantics, suggestionsAvailableSemantics);
                        }
                        final FocusRequester focusRequester2 = focusRequester;
                        SemanticsPropertiesKt.onClick$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBarKt$SearchBarInputField$3$1.1
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                focusRequester2.requestFocus();
                                return true;
                            }
                        }, 1, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv$iv4);
            }
            $composer3.endReplaceableGroup();
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierOnFocusChanged, false, (Function1) value$iv$iv4, 1, null);
            ProvidableCompositionLocal<TextStyle> localTextStyle2 = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localTextStyle2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            TextStyle textStyleMerge = ((TextStyle) objConsume2).merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (TextAlign) null, (TextDirection) null, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, (LineBreak) null, (Hyphens) null, 4194302, (DefaultConstructorMarker) null));
            SolidColor solidColor = new SolidColor(colors3.cursorColor$material3_release(false, $composer3, (($dirty1 << 3) & 112) | 6).getValue().m2981unboximpl(), null);
            KeyboardOptions keyboardOptions = new KeyboardOptions(0, false, 0, ImeAction.INSTANCE.m4936getSearcheUduSuo(), 7, null);
            int i11 = (($dirty2 >> 6) & 14) | (($dirty2 << 3) & 112);
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean invalid$iv$iv2 = $composer3.changed(function2) | $composer3.changed(query);
            Object value$iv$iv5 = $composer3.rememberedValue();
            if (invalid$iv$iv2) {
                $dirty = $dirty2;
            } else {
                $dirty = $dirty2;
                if (value$iv$iv5 == Composer.INSTANCE.getEmpty()) {
                }
                $composer3.endReplaceableGroup();
                SolidColor solidColor2 = solidColor;
                final int $dirty5 = $dirty;
                final boolean z = enabled2;
                final MutableInteractionSource mutableInteractionSource = interactionSource2;
                final Function2<? super Composer, ? super Integer, Unit> function14 = function9;
                final int $dirty6 = $dirty1;
                final Function2<? super Composer, ? super Integer, Unit> function15 = function7;
                colors4 = colors3;
                final Function2<? super Composer, ? super Integer, Unit> function16 = function8;
                modifier3 = modifier5;
                $composer2 = $composer3;
                BasicTextFieldKt.BasicTextField(query, function1, modifierSemantics$default, enabled2, false, textStyleMerge, keyboardOptions, new KeyboardActions(null, null, null, null, (Function1) value$iv$iv5, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource2, (Brush) solidColor2, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.composableLambda($composer3, 584727264, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt.SearchBarInputField.5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function17, Composer composer, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function17, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> innerTextField, Composer $composer4, int $changed2) {
                        Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
                        ComposerKt.sourceInformation($composer4, "C483@22406L15,469@21702L880:SearchBar.kt#uh7d8r");
                        int $dirty7 = $changed2;
                        if (($changed2 & 14) == 0) {
                            $dirty7 |= $composer4.changedInstance(innerTextField) ? 4 : 2;
                        }
                        int $dirty8 = $dirty7;
                        if (($dirty8 & 91) != 18 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(584727264, $dirty8, -1, "androidx.compose.material3.SearchBarInputField.<anonymous> (SearchBar.kt:468)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            String str = query;
                            boolean z2 = z;
                            VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                            MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
                            Function2<Composer, Integer, Unit> function17 = function14;
                            final Function2<Composer, Integer, Unit> function18 = function15;
                            ComposableLambda composableLambda = function18 != null ? ComposableLambdaKt.composableLambda($composer4, -967380630, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBarInputField$5$1$1
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
                                    ComposerKt.sourceInformation($composer5, "C478@22113L60:SearchBar.kt#uh7d8r");
                                    if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-967380630, $changed3, -1, "androidx.compose.material3.SearchBarInputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:477)");
                                        }
                                        Modifier modifier$iv = OffsetKt.m448offsetVpY3zN4$default(Modifier.INSTANCE, SearchBarKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function19 = function18;
                                        $composer5.startReplaceableGroup(733328855);
                                        ComposerKt.sourceInformation($composer5, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer5, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                                        int $changed$iv$iv = (6 << 3) & 112;
                                        $composer5.startReplaceableGroup(-1323940314);
                                        ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                        Object objConsume3 = $composer5.consume(localDensity);
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        Density density$iv$iv = (Density) objConsume3;
                                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                        Object objConsume4 = $composer5.consume(localLayoutDirection);
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
                                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                        Object objConsume5 = $composer5.consume(localViewConfiguration);
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
                                        $composer5.disableReusing();
                                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer5);
                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                        $composer5.enableReusing();
                                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                        $composer5.startReplaceableGroup(2058660585);
                                        int i12 = ($changed$iv$iv$iv >> 9) & 14;
                                        ComposerKt.sourceInformationMarkerStart($composer5, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        int i13 = ((6 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer5, 643341534, "C478@22162L9:SearchBar.kt#uh7d8r");
                                        function19.invoke($composer5, 0);
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
                            }) : null;
                            final Function2<Composer, Integer, Unit> function19 = function16;
                            ComposableLambda composableLambda2 = function19 != null ? ComposableLambdaKt.composableLambda($composer4, -2117865162, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBarInputField$5$2$1
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
                                    ComposerKt.sourceInformation($composer5, "C481@22280L62:SearchBar.kt#uh7d8r");
                                    if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-2117865162, $changed3, -1, "androidx.compose.material3.SearchBarInputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:480)");
                                        }
                                        Modifier.Companion companion = Modifier.INSTANCE;
                                        float arg0$iv = SearchBarKt.SearchBarIconOffsetX;
                                        Modifier modifier$iv = OffsetKt.m448offsetVpY3zN4$default(companion, Dp.m5274constructorimpl(-arg0$iv), 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function20 = function19;
                                        $composer5.startReplaceableGroup(733328855);
                                        ComposerKt.sourceInformation($composer5, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                        int $changed$iv$iv = (0 << 3) & 112;
                                        $composer5.startReplaceableGroup(-1323940314);
                                        ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                        Object objConsume3 = $composer5.consume(localDensity);
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        Density density$iv$iv = (Density) objConsume3;
                                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                        Object objConsume4 = $composer5.consume(localLayoutDirection);
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
                                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                        Object objConsume5 = $composer5.consume(localViewConfiguration);
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
                                        $composer5.disableReusing();
                                        Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer5);
                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                        $composer5.enableReusing();
                                        function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                        $composer5.startReplaceableGroup(2058660585);
                                        int i12 = ($changed$iv$iv$iv >> 9) & 14;
                                        ComposerKt.sourceInformationMarkerStart($composer5, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        int i13 = ((0 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer5, 643341702, "C481@22330L10:SearchBar.kt#uh7d8r");
                                        function20.invoke($composer5, 0);
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
                            }) : null;
                            Shape inputFieldShape = SearchBarDefaults.INSTANCE.getInputFieldShape($composer4, 6);
                            TextFieldColors textFieldColors = colors4;
                            PaddingValues paddingValuesM1845contentPaddingWithoutLabela9UjIt4$default = TextFieldDefaults.m1845contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                            Function2<Composer, Integer, Unit> function2M1456getLambda1$material3_release = ComposableSingletons$SearchBarKt.INSTANCE.m1456getLambda1$material3_release();
                            int i12 = $dirty5;
                            int i13 = $dirty6;
                            textFieldDefaults.DecorationBox(str, innerTextField, z2, true, none, mutableInteractionSource2, false, null, function17, composableLambda, composableLambda2, null, null, null, inputFieldShape, textFieldColors, paddingValuesM1845contentPaddingWithoutLabela9UjIt4$default, function2M1456getLambda1$material3_release, $composer4, (i12 & 14) | 27648 | (($dirty8 << 3) & 112) | ((i12 >> 12) & 896) | ((i13 << 12) & 458752) | ((i12 << 3) & 234881024), ((i13 << 15) & 458752) | 113246208, 14528);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }), $composer3, ($dirty5 & 14) | 102236160 | ($dirty5 & 112) | (($dirty5 >> 9) & 7168), (($dirty6 << 6) & 7168) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            value$iv$iv5 = (Function1) new Function1<KeyboardActionScope, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBarInputField$4$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeyboardActionScope keyboardActionScope) {
                    invoke2(keyboardActionScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeyboardActionScope $receiver) {
                    Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                    function2.invoke(query);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv5);
            $composer3.endReplaceableGroup();
            SolidColor solidColor3 = solidColor;
            final int $dirty7 = $dirty;
            final boolean z2 = enabled2;
            final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
            final Function2<? super Composer, ? super Integer, Unit> function17 = function9;
            final int $dirty8 = $dirty1;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function7;
            colors4 = colors3;
            final Function2<? super Composer, ? super Integer, Unit> function19 = function8;
            modifier3 = modifier5;
            $composer2 = $composer3;
            BasicTextFieldKt.BasicTextField(query, function1, modifierSemantics$default, enabled2, false, textStyleMerge, keyboardOptions, new KeyboardActions(null, null, null, null, (Function1) value$iv$iv5, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource2, (Brush) solidColor3, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.composableLambda($composer3, 584727264, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt.SearchBarInputField.5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function110, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function110, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> innerTextField, Composer $composer4, int $changed2) {
                    Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
                    ComposerKt.sourceInformation($composer4, "C483@22406L15,469@21702L880:SearchBar.kt#uh7d8r");
                    int $dirty9 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty9 |= $composer4.changedInstance(innerTextField) ? 4 : 2;
                    }
                    int $dirty10 = $dirty9;
                    if (($dirty10 & 91) != 18 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(584727264, $dirty10, -1, "androidx.compose.material3.SearchBarInputField.<anonymous> (SearchBar.kt:468)");
                        }
                        TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                        String str = query;
                        boolean z3 = z2;
                        VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                        MutableInteractionSource mutableInteractionSource3 = mutableInteractionSource2;
                        Function2<Composer, Integer, Unit> function110 = function17;
                        final Function2<? super Composer, ? super Integer, Unit> function111 = function18;
                        ComposableLambda composableLambda = function111 != null ? ComposableLambdaKt.composableLambda($composer4, -967380630, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBarInputField$5$1$1
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
                                ComposerKt.sourceInformation($composer5, "C478@22113L60:SearchBar.kt#uh7d8r");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-967380630, $changed3, -1, "androidx.compose.material3.SearchBarInputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:477)");
                                    }
                                    Modifier modifier$iv = OffsetKt.m448offsetVpY3zN4$default(Modifier.INSTANCE, SearchBarKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function112 = function111;
                                    $composer5.startReplaceableGroup(733328855);
                                    ComposerKt.sourceInformation($composer5, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                    MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer5, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                                    int $changed$iv$iv = (6 << 3) & 112;
                                    $composer5.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                    ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume3 = $composer5.consume(localDensity);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    Density density$iv$iv = (Density) objConsume3;
                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                    ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume4 = $composer5.consume(localLayoutDirection);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
                                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                    ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume5 = $composer5.consume(localViewConfiguration);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
                                    $composer5.disableReusing();
                                    Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer5);
                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                    $composer5.enableReusing();
                                    function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                    $composer5.startReplaceableGroup(2058660585);
                                    int i12 = ($changed$iv$iv$iv >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer5, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    int i13 = ((6 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer5, 643341534, "C478@22162L9:SearchBar.kt#uh7d8r");
                                    function112.invoke($composer5, 0);
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
                        }) : null;
                        final Function2<? super Composer, ? super Integer, Unit> function112 = function19;
                        ComposableLambda composableLambda2 = function112 != null ? ComposableLambdaKt.composableLambda($composer4, -2117865162, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBarInputField$5$2$1
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
                                ComposerKt.sourceInformation($composer5, "C481@22280L62:SearchBar.kt#uh7d8r");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2117865162, $changed3, -1, "androidx.compose.material3.SearchBarInputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:480)");
                                    }
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    float arg0$iv = SearchBarKt.SearchBarIconOffsetX;
                                    Modifier modifier$iv = OffsetKt.m448offsetVpY3zN4$default(companion, Dp.m5274constructorimpl(-arg0$iv), 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function20 = function112;
                                    $composer5.startReplaceableGroup(733328855);
                                    ComposerKt.sourceInformation($composer5, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                    MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                    int $changed$iv$iv = (0 << 3) & 112;
                                    $composer5.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                                    ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume3 = $composer5.consume(localDensity);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    Density density$iv$iv = (Density) objConsume3;
                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                    ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume4 = $composer5.consume(localLayoutDirection);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
                                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                    ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume5 = $composer5.consume(localViewConfiguration);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
                                    $composer5.disableReusing();
                                    Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer5);
                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                    $composer5.enableReusing();
                                    function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer5)), $composer5, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                    $composer5.startReplaceableGroup(2058660585);
                                    int i12 = ($changed$iv$iv$iv >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer5, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    int i13 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer5, 643341702, "C481@22330L10:SearchBar.kt#uh7d8r");
                                    function20.invoke($composer5, 0);
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
                        }) : null;
                        Shape inputFieldShape = SearchBarDefaults.INSTANCE.getInputFieldShape($composer4, 6);
                        TextFieldColors textFieldColors = colors4;
                        PaddingValues paddingValuesM1845contentPaddingWithoutLabela9UjIt4$default = TextFieldDefaults.m1845contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        Function2<Composer, Integer, Unit> function2M1456getLambda1$material3_release = ComposableSingletons$SearchBarKt.INSTANCE.m1456getLambda1$material3_release();
                        int i12 = $dirty7;
                        int i13 = $dirty8;
                        textFieldDefaults.DecorationBox(str, innerTextField, z3, true, none, mutableInteractionSource3, false, null, function110, composableLambda, composableLambda2, null, null, null, inputFieldShape, textFieldColors, paddingValuesM1845contentPaddingWithoutLabela9UjIt4$default, function2M1456getLambda1$material3_release, $composer4, (i12 & 14) | 27648 | (($dirty10 << 3) & 112) | ((i12 >> 12) & 896) | ((i13 << 12) & 458752) | ((i12 << 3) & 234881024), ((i13 << 15) & 458752) | 113246208, 14528);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer3, ($dirty7 & 14) | 102236160 | ($dirty7 & 112) | (($dirty7 >> 9) & 7168), (($dirty8 << 6) & 7168) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier3;
        final boolean z3 = enabled2;
        final Function2<? super Composer, ? super Integer, Unit> function20 = function9;
        final Function2<? super Composer, ? super Integer, Unit> function21 = function7;
        final Function2<? super Composer, ? super Integer, Unit> function22 = function8;
        final TextFieldColors textFieldColors = colors4;
        final MutableInteractionSource mutableInteractionSource3 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt.SearchBarInputField.6
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
                SearchBarKt.SearchBarInputField(query, function1, function2, active, function3, modifier6, z3, function20, function21, function22, textFieldColors, mutableInteractionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    static {
        float arg0$iv = SearchBarDefaults.INSTANCE.m1696getInputFieldHeightD9Ej5fM();
        SearchBarCornerRadius = Dp.m5274constructorimpl(arg0$iv / 2);
        DockedActiveTableMinHeight = Dp.m5274constructorimpl(240);
        SearchBarMinWidth = Dp.m5274constructorimpl(360);
        SearchBarMaxWidth = Dp.m5274constructorimpl(720);
        SearchBarVerticalPadding = Dp.m5274constructorimpl(8);
        SearchBarIconOffsetX = Dp.m5274constructorimpl(4);
        CubicBezierEasing easingEmphasizedDecelerateCubicBezier = MotionTokens.INSTANCE.getEasingEmphasizedDecelerateCubicBezier();
        AnimationEnterEasing = easingEmphasizedDecelerateCubicBezier;
        CubicBezierEasing cubicBezierEasing = new CubicBezierEasing(0.0f, 1.0f, 0.0f, 1.0f);
        AnimationExitEasing = cubicBezierEasing;
        TweenSpec tweenSpecTween = AnimationSpecKt.tween(AnimationEnterDurationMillis, 100, easingEmphasizedDecelerateCubicBezier);
        AnimationEnterFloatSpec = tweenSpecTween;
        TweenSpec tweenSpecTween2 = AnimationSpecKt.tween(AnimationExitDurationMillis, 100, cubicBezierEasing);
        AnimationExitFloatSpec = tweenSpecTween2;
        TweenSpec tweenSpecTween3 = AnimationSpecKt.tween(AnimationEnterDurationMillis, 100, easingEmphasizedDecelerateCubicBezier);
        AnimationEnterSizeSpec = tweenSpecTween3;
        TweenSpec tweenSpecTween4 = AnimationSpecKt.tween(AnimationExitDurationMillis, 100, cubicBezierEasing);
        AnimationExitSizeSpec = tweenSpecTween4;
        DockedEnterTransition = EnterExitTransitionKt.fadeIn$default(tweenSpecTween, 0.0f, 2, null).plus(EnterExitTransitionKt.expandVertically$default(tweenSpecTween3, null, false, null, 14, null));
        DockedExitTransition = EnterExitTransitionKt.fadeOut$default(tweenSpecTween2, 0.0f, 2, null).plus(EnterExitTransitionKt.shrinkVertically$default(tweenSpecTween4, null, false, null, 14, null));
    }

    public static final float getDockedActiveTableMinHeight() {
        return DockedActiveTableMinHeight;
    }

    public static final float getSearchBarMinWidth() {
        return SearchBarMinWidth;
    }

    public static final float getSearchBarVerticalPadding() {
        return SearchBarVerticalPadding;
    }
}
