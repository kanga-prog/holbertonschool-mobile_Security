package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.material3.tokens.BottomAppBarTokens;
import androidx.compose.material3.tokens.TopAppBarLargeTokens;
import androidx.compose.material3.tokens.TopAppBarMediumTokens;
import androidx.compose.material3.tokens.TopAppBarSmallTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0086\u0001\u0010\u0010\u001a\u00020\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001ao\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\u001c\u0010%\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a\u007f\u0010(\u001a\u00020\u00112\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010+\u001a\u00020,2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.H\u0007¢\u0006\u0002\u0010/\u001a\u007f\u00100\u001a\u00020\u00112\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010+\u001a\u00020,2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.H\u0007¢\u0006\u0002\u0010/\u001a\u007f\u00101\u001a\u00020\u00112\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010+\u001a\u00020,2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.H\u0007¢\u0006\u0002\u0010/\u001a\u0085\u0001\u00102\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0011\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\u0006\u0010!\u001a\u00020\"2\u0006\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0003¢\u0006\u0002\u00107\u001a\u007f\u00108\u001a\u00020\u00112\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010+\u001a\u00020,2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.H\u0007¢\u0006\u0002\u0010/\u001a\u007f\u00109\u001a\u00020\u00112\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010+\u001a\u00020,2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.H\u0007¢\u0006\u0002\u0010/\u001a¦\u0001\u0010:\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u001c2\u0006\u0010>\u001a\u00020\u001c2\u0006\u0010?\u001a\u00020\u001c2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u0006\u00103\u001a\u0002042\u0006\u0010@\u001a\u00020<2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u0002062\u0011\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u0015H\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bH\u0010I\u001a¸\u0001\u0010J\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u0006\u00103\u001a\u0002042\u0006\u0010E\u001a\u00020\u00012\u0011\u0010K\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u0006\u0010L\u001a\u0002042\u0011\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\u0006\u0010!\u001a\u00020\"2\u0006\u0010+\u001a\u00020,2\u0006\u0010M\u001a\u00020\u00012\u0006\u0010N\u001a\u00020\u00012\b\u0010-\u001a\u0004\u0018\u00010.H\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bO\u0010P\u001a+\u0010Q\u001a\u00020R2\b\b\u0002\u0010S\u001a\u00020<2\b\b\u0002\u0010T\u001a\u00020<2\b\b\u0002\u0010U\u001a\u00020<H\u0007¢\u0006\u0002\u0010V\u001aD\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020R2\u0006\u0010Z\u001a\u00020<2\u000e\u0010[\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010\\2\u000e\u0010]\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010^H\u0082@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u0010_\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0019\u0010\u0003\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\u0004\u0010\u0005\"\u0013\u0010\u0006\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0007\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\b\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\t\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\n\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u000b\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0014\u0010\f\u001a\u00020\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006`"}, d2 = {"BottomAppBarHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "BottomAppBarVerticalPadding", "getBottomAppBarVerticalPadding", "()F", "FABHorizontalPadding", "FABVerticalPadding", "LargeTitleBottomPadding", "MediumTitleBottomPadding", "TopAppBarHorizontalPadding", "TopAppBarTitleInset", "TopTitleAlphaEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "getTopTitleAlphaEasing", "()Landroidx/compose/animation/core/CubicBezierEasing;", "BottomAppBar", "", "actions", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "floatingActionButton", "Lkotlin/Function0;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "BottomAppBar-Snr_uVM", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/runtime/Composer;II)V", "content", "BottomAppBar-1oL4kX8", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "CenterAlignedTopAppBar", "title", "navigationIcon", "colors", "Landroidx/compose/material3/TopAppBarColors;", "scrollBehavior", "Landroidx/compose/material3/TopAppBarScrollBehavior;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "LargeTopAppBar", "MediumTopAppBar", "SingleRowTopAppBar", "titleTextStyle", "Landroidx/compose/ui/text/TextStyle;", "centeredTitle", "", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "SmallTopAppBar", "TopAppBar", "TopAppBarLayout", "heightPx", "", "navigationIconContentColor", "titleContentColor", "actionIconContentColor", "titleAlpha", "titleVerticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "titleHorizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "titleBottomPadding", "", "hideTitleSemantics", "TopAppBarLayout-kXwM9vE", "(Landroidx/compose/ui/Modifier;FJJJLkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;FLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "TwoRowsTopAppBar", "smallTitle", "smallTitleTextStyle", "maxHeight", "pinnedHeight", "TwoRowsTopAppBar-tjU4iQQ", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;FLkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;FFLandroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;III)V", "rememberTopAppBarState", "Landroidx/compose/material3/TopAppBarState;", "initialHeightOffsetLimit", "initialHeightOffset", "initialContentOffset", "(FFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarState;", "settleAppBar", "Landroidx/compose/ui/unit/Velocity;", "state", "velocity", "flingAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/material3/TopAppBarState;FLandroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AppBarKt {
    private static final float BottomAppBarHorizontalPadding;
    private static final float BottomAppBarVerticalPadding;
    private static final float FABHorizontalPadding;
    private static final float FABVerticalPadding;
    private static final float LargeTitleBottomPadding;
    private static final float MediumTitleBottomPadding;
    private static final float TopAppBarHorizontalPadding;
    private static final float TopAppBarTitleInset;
    private static final CubicBezierEasing TopTitleAlphaEasing;

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$settleAppBar$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AppBar.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.AppBarKt", f = "AppBar.kt", i = {0, 0, 0, 1}, l = {1622, 1638}, m = "settleAppBar", n = {"state", "snapAnimationSpec", "remainingVelocity", "remainingVelocity"}, s = {"L$0", "L$1", "L$2", "L$0"})
    static final class C03321 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C03321(Continuation<? super C03321> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AppBarKt.settleAppBar(null, 0.0f, null, null, this);
        }
    }

    public static final void TopAppBar(final Function2<? super Composer, ? super Integer, Unit> title, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3M1436getLambda2$material3_release;
        WindowInsets windowInsets2;
        TopAppBarColors topAppBarColors;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior scrollBehavior2;
        int $dirty;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function5;
        TopAppBarColors colors3;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function6;
        WindowInsets windowInsets3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer3 = $composer.startRestartGroup(1906353009);
        ComposerKt.sourceInformation($composer3, "C(TopAppBar)P(5,2,3!1,6)125@6259L12,126@6321L17,132@6511L10,129@6400L374:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(title) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            function4 = function2;
        } else if (($changed & 896) == 0) {
            function4 = function2;
            $dirty2 |= $composer3.changedInstance(function4) ? 256 : 128;
        } else {
            function4 = function2;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            function3M1436getLambda2$material3_release = function3;
        } else if (($changed & 7168) == 0) {
            function3M1436getLambda2$material3_release = function3;
            $dirty2 |= $composer3.changedInstance(function3M1436getLambda2$material3_release) ? 2048 : 1024;
        } else {
            function3M1436getLambda2$material3_release = function3;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                int i5 = $composer3.changed(windowInsets2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i5;
        } else {
            windowInsets2 = windowInsets;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                topAppBarColors = colors;
                int i6 = $composer3.changed(topAppBarColors) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                topAppBarColors = colors;
            }
            $dirty2 |= i6;
        } else {
            topAppBarColors = colors;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if (($changed & 3670016) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty2 |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if (($dirty2 & 2995931) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            function6 = function3M1436getLambda2$material3_release;
            windowInsets3 = windowInsets2;
            $composer2 = $composer3;
            scrollBehavior2 = topAppBarScrollBehavior;
            colors3 = topAppBarColors;
            modifier3 = modifier2;
            function5 = function4;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                Function2<? super Composer, ? super Integer, Unit> function2M1432getLambda1$material3_release = i3 != 0 ? ComposableSingletons$AppBarKt.INSTANCE.m1432getLambda1$material3_release() : function4;
                if (i4 != 0) {
                    function3M1436getLambda2$material3_release = ComposableSingletons$AppBarKt.INSTANCE.m1436getLambda2$material3_release();
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 32) != 0) {
                    colors2 = TopAppBarDefaults.INSTANCE.m2008topAppBarColorszjMxDiM(0L, 0L, 0L, 0L, 0L, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty2 &= -458753;
                } else {
                    colors2 = colors;
                }
                if (i7 != 0) {
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    function5 = function2M1432getLambda1$material3_release;
                    colors3 = colors2;
                    scrollBehavior2 = null;
                    function6 = function3M1436getLambda2$material3_release;
                    windowInsets3 = windowInsets2;
                } else {
                    scrollBehavior2 = scrollBehavior;
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    function5 = function2M1432getLambda1$material3_release;
                    colors3 = colors2;
                    function6 = function3M1436getLambda2$material3_release;
                    windowInsets3 = windowInsets2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                $dirty = $dirty2;
                function6 = function3M1436getLambda2$material3_release;
                windowInsets3 = windowInsets2;
                scrollBehavior2 = topAppBarScrollBehavior;
                colors3 = topAppBarColors;
                modifier3 = modifier2;
                function5 = function4;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1906353009, $dirty, -1, "androidx.compose.material3.TopAppBar (AppBar.kt:120)");
            }
            $composer2 = $composer3;
            SingleRowTopAppBar(modifier3, title, TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer3, 6), TopAppBarSmallTokens.INSTANCE.getHeadlineFont()), false, function5, function6, windowInsets3, colors3, scrollBehavior2, $composer3, (($dirty >> 3) & 14) | 3072 | (($dirty << 3) & 112) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 3670016) | (($dirty << 6) & 29360128) | (($dirty << 6) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function7 = function5;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function8 = function6;
        final WindowInsets windowInsets4 = windowInsets3;
        final TopAppBarColors topAppBarColors2 = colors3;
        final TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.TopAppBar.1
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
                AppBarKt.TopAppBar(title, modifier5, function7, function8, windowInsets4, topAppBarColors2, topAppBarScrollBehavior2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use TopAppBar instead.", replaceWith = @ReplaceWith(expression = "TopAppBar(title, modifier, navigationIcon, actions, windowInsets, colors, scrollBehavior)", imports = {}))
    public static final void SmallTopAppBar(final Function2<? super Composer, ? super Integer, Unit> title, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3M1438getLambda4$material3_release;
        WindowInsets windowInsets2;
        TopAppBarColors topAppBarColors;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        WindowInsets windowInsets3;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior scrollBehavior2;
        int $dirty;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function5;
        WindowInsets windowInsets4;
        TopAppBarColors colors3;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function6;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer3 = $composer.startRestartGroup(-1967617284);
        ComposerKt.sourceInformation($composer3, "C(SmallTopAppBar)P(5,2,3!1,6)188@9238L12,189@9300L17,191@9375L89:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(title) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            function4 = function2;
        } else if (($changed & 896) == 0) {
            function4 = function2;
            $dirty2 |= $composer3.changedInstance(function4) ? 256 : 128;
        } else {
            function4 = function2;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            function3M1438getLambda4$material3_release = function3;
        } else if (($changed & 7168) == 0) {
            function3M1438getLambda4$material3_release = function3;
            $dirty2 |= $composer3.changedInstance(function3M1438getLambda4$material3_release) ? 2048 : 1024;
        } else {
            function3M1438getLambda4$material3_release = function3;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                int i5 = $composer3.changed(windowInsets2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i5;
        } else {
            windowInsets2 = windowInsets;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                topAppBarColors = colors;
                int i6 = $composer3.changed(topAppBarColors) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                topAppBarColors = colors;
            }
            $dirty2 |= i6;
        } else {
            topAppBarColors = colors;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if (($changed & 3670016) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty2 |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if ((2995931 & $dirty2) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            function6 = function3M1438getLambda4$material3_release;
            $composer2 = $composer3;
            scrollBehavior2 = topAppBarScrollBehavior;
            colors3 = topAppBarColors;
            function5 = function4;
            windowInsets4 = windowInsets2;
            modifier3 = modifier2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                Function2<? super Composer, ? super Integer, Unit> function2M1437getLambda3$material3_release = i3 != 0 ? ComposableSingletons$AppBarKt.INSTANCE.m1437getLambda3$material3_release() : function4;
                if (i4 != 0) {
                    function3M1438getLambda4$material3_release = ComposableSingletons$AppBarKt.INSTANCE.m1438getLambda4$material3_release();
                }
                if ((i & 16) != 0) {
                    windowInsets3 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    windowInsets3 = windowInsets2;
                }
                if ((i & 32) != 0) {
                    colors2 = TopAppBarDefaults.INSTANCE.m2008topAppBarColorszjMxDiM(0L, 0L, 0L, 0L, 0L, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty2 &= -458753;
                } else {
                    colors2 = colors;
                }
                if (i7 != 0) {
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    function5 = function2M1437getLambda3$material3_release;
                    windowInsets4 = windowInsets3;
                    colors3 = colors2;
                    scrollBehavior2 = null;
                    function6 = function3M1438getLambda4$material3_release;
                } else {
                    scrollBehavior2 = scrollBehavior;
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    function5 = function2M1437getLambda3$material3_release;
                    windowInsets4 = windowInsets3;
                    colors3 = colors2;
                    function6 = function3M1438getLambda4$material3_release;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                $dirty = $dirty2;
                function6 = function3M1438getLambda4$material3_release;
                scrollBehavior2 = topAppBarScrollBehavior;
                colors3 = topAppBarColors;
                function5 = function4;
                windowInsets4 = windowInsets2;
                modifier3 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1967617284, $dirty, -1, "androidx.compose.material3.SmallTopAppBar (AppBar.kt:183)");
            }
            $composer2 = $composer3;
            TopAppBar(title, modifier3, function5, function6, windowInsets4, colors3, scrollBehavior2, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | ($dirty & 458752) | ($dirty & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function7 = function5;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function8 = function6;
        final WindowInsets windowInsets5 = windowInsets4;
        final TopAppBarColors topAppBarColors2 = colors3;
        final TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SmallTopAppBar.1
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
                AppBarKt.SmallTopAppBar(title, modifier5, function7, function8, windowInsets5, topAppBarColors2, topAppBarScrollBehavior2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static final void CenterAlignedTopAppBar(final Function2<? super Composer, ? super Integer, Unit> title, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3M1440getLambda6$material3_release;
        WindowInsets windowInsets2;
        TopAppBarColors topAppBarColors;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior scrollBehavior2;
        int $dirty;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function5;
        TopAppBarColors colors3;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function6;
        WindowInsets windowInsets3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer3 = $composer.startRestartGroup(-2139286460);
        ComposerKt.sourceInformation($composer3, "C(CenterAlignedTopAppBar)P(5,2,3!1,6)229@11657L12,230@11719L30,237@11930L10,233@11811L381:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(title) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            function4 = function2;
        } else if (($changed & 896) == 0) {
            function4 = function2;
            $dirty2 |= $composer3.changedInstance(function4) ? 256 : 128;
        } else {
            function4 = function2;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            function3M1440getLambda6$material3_release = function3;
        } else if (($changed & 7168) == 0) {
            function3M1440getLambda6$material3_release = function3;
            $dirty2 |= $composer3.changedInstance(function3M1440getLambda6$material3_release) ? 2048 : 1024;
        } else {
            function3M1440getLambda6$material3_release = function3;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                int i5 = $composer3.changed(windowInsets2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i5;
        } else {
            windowInsets2 = windowInsets;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                topAppBarColors = colors;
                int i6 = $composer3.changed(topAppBarColors) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                topAppBarColors = colors;
            }
            $dirty2 |= i6;
        } else {
            topAppBarColors = colors;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if (($changed & 3670016) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty2 |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if (($dirty2 & 2995931) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            function6 = function3M1440getLambda6$material3_release;
            windowInsets3 = windowInsets2;
            $composer2 = $composer3;
            scrollBehavior2 = topAppBarScrollBehavior;
            colors3 = topAppBarColors;
            modifier3 = modifier2;
            function5 = function4;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                Function2<? super Composer, ? super Integer, Unit> function2M1439getLambda5$material3_release = i3 != 0 ? ComposableSingletons$AppBarKt.INSTANCE.m1439getLambda5$material3_release() : function4;
                if (i4 != 0) {
                    function3M1440getLambda6$material3_release = ComposableSingletons$AppBarKt.INSTANCE.m1440getLambda6$material3_release();
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 32) != 0) {
                    colors2 = TopAppBarDefaults.INSTANCE.m2004centerAlignedTopAppBarColorszjMxDiM(0L, 0L, 0L, 0L, 0L, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty2 &= -458753;
                } else {
                    colors2 = colors;
                }
                if (i7 != 0) {
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    function5 = function2M1439getLambda5$material3_release;
                    colors3 = colors2;
                    scrollBehavior2 = null;
                    function6 = function3M1440getLambda6$material3_release;
                    windowInsets3 = windowInsets2;
                } else {
                    scrollBehavior2 = scrollBehavior;
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    function5 = function2M1439getLambda5$material3_release;
                    colors3 = colors2;
                    function6 = function3M1440getLambda6$material3_release;
                    windowInsets3 = windowInsets2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                $dirty = $dirty2;
                function6 = function3M1440getLambda6$material3_release;
                windowInsets3 = windowInsets2;
                scrollBehavior2 = topAppBarScrollBehavior;
                colors3 = topAppBarColors;
                modifier3 = modifier2;
                function5 = function4;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2139286460, $dirty, -1, "androidx.compose.material3.CenterAlignedTopAppBar (AppBar.kt:224)");
            }
            $composer2 = $composer3;
            SingleRowTopAppBar(modifier3, title, TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer3, 6), TopAppBarSmallTokens.INSTANCE.getHeadlineFont()), true, function5, function6, windowInsets3, colors3, scrollBehavior2, $composer3, (($dirty >> 3) & 14) | 3072 | (($dirty << 3) & 112) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 3670016) | (($dirty << 6) & 29360128) | (($dirty << 6) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function7 = function5;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function8 = function6;
        final WindowInsets windowInsets4 = windowInsets3;
        final TopAppBarColors topAppBarColors2 = colors3;
        final TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.CenterAlignedTopAppBar.1
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
                AppBarKt.CenterAlignedTopAppBar(title, modifier5, function7, function8, windowInsets4, topAppBarColors2, topAppBarScrollBehavior2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static final void MediumTopAppBar(final Function2<? super Composer, ? super Integer, Unit> title, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3M1442getLambda8$material3_release;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        TopAppBarScrollBehavior scrollBehavior2;
        int $dirty;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function6;
        WindowInsets windowInsets3;
        TopAppBarColors colors3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer3 = $composer.startRestartGroup(1805417862);
        ComposerKt.sourceInformation($composer3, "C(MediumTopAppBar)P(5,2,3!1,6)284@14510L12,285@14572L23,291@14766L10,292@14868L10,288@14657L646:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(title) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            function4 = function2;
        } else if (($changed & 896) == 0) {
            function4 = function2;
            $dirty2 |= $composer3.changedInstance(function4) ? 256 : 128;
        } else {
            function4 = function2;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            function3M1442getLambda8$material3_release = function3;
        } else if (($changed & 7168) == 0) {
            function3M1442getLambda8$material3_release = function3;
            $dirty2 |= $composer3.changedInstance(function3M1442getLambda8$material3_release) ? 2048 : 1024;
        } else {
            function3M1442getLambda8$material3_release = function3;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                int i5 = $composer3.changed(windowInsets2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i5;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((458752 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                int i6 = $composer3.changed(colors2) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i6;
        } else {
            colors2 = colors;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if (($changed & 3670016) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty2 |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if ((2995931 & $dirty2) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            function5 = function4;
            function6 = function3M1442getLambda8$material3_release;
            $composer2 = $composer3;
            scrollBehavior2 = topAppBarScrollBehavior;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                Function2<? super Composer, ? super Integer, Unit> function2M1441getLambda7$material3_release = i3 != 0 ? ComposableSingletons$AppBarKt.INSTANCE.m1441getLambda7$material3_release() : function4;
                if (i4 != 0) {
                    function3M1442getLambda8$material3_release = ComposableSingletons$AppBarKt.INSTANCE.m1442getLambda8$material3_release();
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    colors2 = TopAppBarDefaults.INSTANCE.m2006mediumTopAppBarColorszjMxDiM(0L, 0L, 0L, 0L, 0L, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                }
                if (i7 != 0) {
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    function5 = function2M1441getLambda7$material3_release;
                    scrollBehavior2 = null;
                    function6 = function3M1442getLambda8$material3_release;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                } else {
                    scrollBehavior2 = scrollBehavior;
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    function5 = function2M1441getLambda7$material3_release;
                    function6 = function3M1442getLambda8$material3_release;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                $dirty = $dirty2;
                modifier3 = modifier2;
                function5 = function4;
                function6 = function3M1442getLambda8$material3_release;
                windowInsets3 = windowInsets2;
                colors3 = colors2;
                scrollBehavior2 = topAppBarScrollBehavior;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1805417862, $dirty, -1, "androidx.compose.material3.MediumTopAppBar (AppBar.kt:279)");
            }
            $composer2 = $composer3;
            m1312TwoRowsTopAppBartjU4iQQ(modifier3, title, TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer3, 6), TopAppBarMediumTokens.INSTANCE.getHeadlineFont()), MediumTitleBottomPadding, title, TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer3, 6), TopAppBarSmallTokens.INSTANCE.getHeadlineFont()), function5, function6, windowInsets3, colors3, TopAppBarMediumTokens.INSTANCE.m2510getContainerHeightD9Ej5fM(), TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM(), scrollBehavior2, $composer2, (($dirty >> 3) & 14) | 3072 | (($dirty << 3) & 112) | (($dirty << 12) & 57344) | (($dirty << 12) & 3670016) | (($dirty << 12) & 29360128) | (($dirty << 12) & 234881024) | (($dirty << 12) & 1879048192), (($dirty >> 12) & 896) | 54, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            windowInsets2 = windowInsets3;
            colors2 = colors3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function7 = function5;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function8 = function6;
        final WindowInsets windowInsets4 = windowInsets2;
        final TopAppBarColors topAppBarColors = colors2;
        final TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.MediumTopAppBar.1
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
                AppBarKt.MediumTopAppBar(title, modifier5, function7, function8, windowInsets4, topAppBarColors, topAppBarScrollBehavior2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static final void LargeTopAppBar(final Function2<? super Composer, ? super Integer, Unit> title, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3M1433getLambda10$material3_release;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        TopAppBarScrollBehavior scrollBehavior2;
        int $dirty;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function6;
        WindowInsets windowInsets3;
        TopAppBarColors colors3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer3 = $composer.startRestartGroup(-474540752);
        ComposerKt.sourceInformation($composer3, "C(LargeTopAppBar)P(5,2,3!1,6)342@17613L12,343@17675L22,348@17839L10,349@17940L10,346@17759L643:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(title) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            function4 = function2;
        } else if (($changed & 896) == 0) {
            function4 = function2;
            $dirty2 |= $composer3.changedInstance(function4) ? 256 : 128;
        } else {
            function4 = function2;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            function3M1433getLambda10$material3_release = function3;
        } else if (($changed & 7168) == 0) {
            function3M1433getLambda10$material3_release = function3;
            $dirty2 |= $composer3.changedInstance(function3M1433getLambda10$material3_release) ? 2048 : 1024;
        } else {
            function3M1433getLambda10$material3_release = function3;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                int i5 = $composer3.changed(windowInsets2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i5;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((458752 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                int i6 = $composer3.changed(colors2) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i6;
        } else {
            colors2 = colors;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if (($changed & 3670016) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty2 |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if ((2995931 & $dirty2) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            function5 = function4;
            function6 = function3M1433getLambda10$material3_release;
            $composer2 = $composer3;
            scrollBehavior2 = topAppBarScrollBehavior;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                Function2<? super Composer, ? super Integer, Unit> function2M1443getLambda9$material3_release = i3 != 0 ? ComposableSingletons$AppBarKt.INSTANCE.m1443getLambda9$material3_release() : function4;
                if (i4 != 0) {
                    function3M1433getLambda10$material3_release = ComposableSingletons$AppBarKt.INSTANCE.m1433getLambda10$material3_release();
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    colors2 = TopAppBarDefaults.INSTANCE.m2005largeTopAppBarColorszjMxDiM(0L, 0L, 0L, 0L, 0L, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                }
                if (i7 != 0) {
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    function5 = function2M1443getLambda9$material3_release;
                    scrollBehavior2 = null;
                    function6 = function3M1433getLambda10$material3_release;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                } else {
                    scrollBehavior2 = scrollBehavior;
                    $dirty = $dirty2;
                    modifier3 = modifier4;
                    function5 = function2M1443getLambda9$material3_release;
                    function6 = function3M1433getLambda10$material3_release;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                $dirty = $dirty2;
                modifier3 = modifier2;
                function5 = function4;
                function6 = function3M1433getLambda10$material3_release;
                windowInsets3 = windowInsets2;
                colors3 = colors2;
                scrollBehavior2 = topAppBarScrollBehavior;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-474540752, $dirty, -1, "androidx.compose.material3.LargeTopAppBar (AppBar.kt:337)");
            }
            $composer2 = $composer3;
            m1312TwoRowsTopAppBartjU4iQQ(modifier3, title, TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer3, 6), TopAppBarLargeTokens.INSTANCE.getHeadlineFont()), LargeTitleBottomPadding, title, TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer3, 6), TopAppBarSmallTokens.INSTANCE.getHeadlineFont()), function5, function6, windowInsets3, colors3, TopAppBarLargeTokens.INSTANCE.m2506getContainerHeightD9Ej5fM(), TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM(), scrollBehavior2, $composer2, (($dirty >> 3) & 14) | 3072 | (($dirty << 3) & 112) | (($dirty << 12) & 57344) | (($dirty << 12) & 3670016) | (($dirty << 12) & 29360128) | (($dirty << 12) & 234881024) | (($dirty << 12) & 1879048192), (($dirty >> 12) & 896) | 54, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            windowInsets2 = windowInsets3;
            colors2 = colors3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function7 = function5;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function8 = function6;
        final WindowInsets windowInsets4 = windowInsets2;
        final TopAppBarColors topAppBarColors = colors2;
        final TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.LargeTopAppBar.1
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
                AppBarKt.LargeTopAppBar(title, modifier5, function7, function8, windowInsets4, topAppBarColors, topAppBarScrollBehavior2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: renamed from: BottomAppBar-Snr_uVM, reason: not valid java name */
    public static final void m1310BottomAppBarSnr_uVM(final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> actions, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, long containerColor, long contentColor, float tonalElevation, PaddingValues contentPadding, WindowInsets windowInsets, Composer $composer, final int $changed, final int i) {
        final Function2<? super Composer, ? super Integer, Unit> function3;
        long containerColor2;
        long contentColor2;
        float tonalElevation2;
        Modifier.Companion modifier2;
        PaddingValues contentPadding2;
        WindowInsets windowInsets2;
        Modifier modifier3;
        PaddingValues contentPadding3;
        WindowInsets windowInsets3;
        Function2<? super Composer, ? super Integer, Unit> function4;
        long containerColor3;
        float tonalElevation3;
        long contentColor3;
        Intrinsics.checkNotNullParameter(actions, "actions");
        Composer $composer2 = $composer.startRestartGroup(2141738945);
        ComposerKt.sourceInformation($composer2, "C(BottomAppBar)P(!1,5,4,1:c#ui.graphics.Color,2:c#ui.graphics.Color,6:c#ui.unit.Dp)398@20365L14,399@20407L31,402@20633L12,403@20651L634:AppBar.kt#uh7d8r");
        final int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changedInstance(actions) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            function3 = function2;
        } else if (($changed & 896) == 0) {
            function3 = function2;
            $dirty |= $composer2.changedInstance(function3) ? 256 : 128;
        } else {
            function3 = function2;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                containerColor2 = containerColor;
                int i4 = $composer2.changed(containerColor2) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                containerColor2 = containerColor;
            }
            $dirty |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                contentColor2 = contentColor;
                int i5 = $composer2.changed(contentColor2) ? 16384 : 8192;
                $dirty |= i5;
            } else {
                contentColor2 = contentColor;
            }
            $dirty |= i5;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            tonalElevation2 = tonalElevation;
        } else if (($changed & 458752) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty |= $composer2.changed(tonalElevation2) ? 131072 : 65536;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty |= $composer2.changed(contentPadding) ? 1048576 : 524288;
        }
        if (($changed & 29360128) == 0) {
            $dirty |= ((i & 128) == 0 && $composer2.changed(windowInsets)) ? 8388608 : 4194304;
        }
        if (($dirty & 23967451) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            contentPadding3 = contentPadding;
            windowInsets3 = windowInsets;
            function4 = function3;
            containerColor3 = containerColor2;
            tonalElevation3 = tonalElevation2;
            contentColor3 = contentColor2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if (i3 != 0) {
                    function3 = null;
                }
                if ((i & 8) != 0) {
                    containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                }
                if (i6 != 0) {
                    tonalElevation2 = BottomAppBarDefaults.INSTANCE.m1323getContainerElevationD9Ej5fM();
                }
                contentPadding2 = i7 != 0 ? BottomAppBarDefaults.INSTANCE.getContentPadding() : contentPadding;
                if ((i & 128) != 0) {
                    windowInsets2 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty &= -29360129;
                } else {
                    windowInsets2 = windowInsets;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 128) != 0) {
                    contentPadding2 = contentPadding;
                    windowInsets2 = windowInsets;
                    $dirty &= -29360129;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                    contentPadding2 = contentPadding;
                    windowInsets2 = windowInsets;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2141738945, $dirty, -1, "androidx.compose.material3.BottomAppBar (AppBar.kt:394)");
            }
            m1309BottomAppBar1oL4kX8(modifier2, containerColor2, contentColor2, tonalElevation2, contentPadding2, windowInsets2, ComposableLambdaKt.composableLambda($composer2, 1974005449, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBar$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                    invoke(rowScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RowScope BottomAppBar, Composer $composer3, int $changed2) {
                    Intrinsics.checkNotNullParameter(BottomAppBar, "$this$BottomAppBar");
                    ComposerKt.sourceInformation($composer3, "C411@20874L9,413@20932L33,414@20974L303:AppBar.kt#uh7d8r");
                    int $dirty2 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty2 |= $composer3.changed(BottomAppBar) ? 4 : 2;
                    }
                    if (($dirty2 & 91) != 18 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1974005449, $dirty2, -1, "androidx.compose.material3.BottomAppBar.<anonymous> (AppBar.kt:410)");
                        }
                        actions.invoke(BottomAppBar, $composer3, Integer.valueOf(($dirty2 & 14) | (($dirty << 3) & 112)));
                        if (function3 != null) {
                            SpacerKt.Spacer(BottomAppBar.weight(Modifier.INSTANCE, 1.0f, true), $composer3, 0);
                            Modifier modifier$iv = PaddingKt.m491paddingqDBjuR0$default(SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, AppBarKt.FABVerticalPadding, AppBarKt.FABHorizontalPadding, 0.0f, 9, null);
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            Function2<Composer, Integer, Unit> function5 = function3;
                            int i8 = $dirty;
                            $composer3.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            int $changed$iv$iv = (54 << 3) & 112;
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
                            int i9 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i10 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1859773471, "C423@21245L22:AppBar.kt#uh7d8r");
                            function5.invoke($composer3, Integer.valueOf((i8 >> 6) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, (($dirty >> 3) & 14) | 1572864 | (($dirty >> 6) & 112) | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty >> 6) & 57344) | (($dirty >> 6) & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            contentPadding3 = contentPadding2;
            windowInsets3 = windowInsets2;
            function4 = function3;
            containerColor3 = containerColor2;
            tonalElevation3 = tonalElevation2;
            contentColor3 = contentColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function5 = function4;
        final long j = containerColor3;
        final long j2 = contentColor3;
        final float f = tonalElevation3;
        final PaddingValues paddingValues = contentPadding3;
        final WindowInsets windowInsets4 = windowInsets3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBar$2
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
                AppBarKt.m1310BottomAppBarSnr_uVM(actions, modifier4, function5, j, j2, f, paddingValues, windowInsets4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:100:0x013b  */
    /* JADX WARN: Code duplicated, block: B:101:0x0140  */
    /* JADX WARN: Code duplicated, block: B:104:0x0146  */
    /* JADX WARN: Code duplicated, block: B:107:0x0152  */
    /* JADX WARN: Code duplicated, block: B:109:0x015e  */
    /* JADX WARN: Code duplicated, block: B:111:0x0167  */
    /* JADX WARN: Code duplicated, block: B:114:0x0172  */
    /* JADX WARN: Code duplicated, block: B:115:0x017f  */
    /* JADX WARN: Code duplicated, block: B:118:0x018b  */
    /* JADX WARN: Code duplicated, block: B:121:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:125:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:126:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:84:0x0107  */
    /* JADX WARN: Code duplicated, block: B:86:0x010f  */
    /* JADX WARN: Code duplicated, block: B:99:0x0139 A[DONT_INVERT] */
    /* JADX INFO: renamed from: BottomAppBar-1oL4kX8, reason: not valid java name */
    public static final void m1309BottomAppBar1oL4kX8(Modifier modifier, long containerColor, long contentColor, float tonalElevation, PaddingValues contentPadding, WindowInsets windowInsets, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        long containerColor2;
        long contentColor2;
        float tonalElevation2;
        PaddingValues contentPadding2;
        WindowInsets windowInsets2;
        int i2;
        Modifier.Companion modifier2;
        final int $dirty;
        final WindowInsets windowInsets3;
        final PaddingValues contentPadding3;
        Modifier modifier3;
        PaddingValues contentPadding4;
        WindowInsets windowInsets4;
        long containerColor3;
        long contentColor3;
        float tonalElevation3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-1391700845);
        ComposerKt.sourceInformation($composer2, "C(BottomAppBar)P(4,0:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.unit.Dp,3,6)456@22906L14,457@22948L31,460@23174L12,468@23508L9,463@23241L693:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            if ((i & 2) == 0) {
                containerColor2 = containerColor;
                int i4 = $composer2.changed(containerColor2) ? 32 : 16;
                $dirty2 |= i4;
            } else {
                containerColor2 = containerColor;
            }
            $dirty2 |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                contentColor2 = contentColor;
                int i5 = $composer2.changed(contentColor2) ? 256 : 128;
                $dirty2 |= i5;
            } else {
                contentColor2 = contentColor;
            }
            $dirty2 |= i5;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty2 |= 3072;
            tonalElevation2 = tonalElevation;
        } else if (($changed & 7168) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty2 |= $composer2.changed(tonalElevation2) ? 2048 : 1024;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty2 |= 24576;
            contentPadding2 = contentPadding;
        } else if (($changed & 57344) == 0) {
            contentPadding2 = contentPadding;
            $dirty2 |= $composer2.changed(contentPadding2) ? 16384 : 8192;
        } else {
            contentPadding2 = contentPadding;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i8 = $composer2.changed(windowInsets2) ? 131072 : 65536;
                $dirty2 |= i8;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i8;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(content) ? 1048576 : 524288;
            }
            if (($dirty2 & 2995931) == 599186 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if ((i & 2) != 0) {
                        containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer2, 6);
                        $dirty2 &= -113;
                    }
                    if ((i & 4) != 0) {
                        contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty2 >> 3) & 14);
                        $dirty2 &= -897;
                    }
                    if (i6 != 0) {
                        tonalElevation2 = BottomAppBarDefaults.INSTANCE.m1323getContainerElevationD9Ej5fM();
                    }
                    if (i7 != 0) {
                        contentPadding2 = BottomAppBarDefaults.INSTANCE.getContentPadding();
                    }
                    if ((i & 32) != 0) {
                        windowInsets3 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer2, 6);
                        $dirty = $dirty2 & (-458753);
                        contentPadding3 = contentPadding2;
                    } else {
                        $dirty = $dirty2;
                        windowInsets3 = windowInsets2;
                        contentPadding3 = contentPadding2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 2) != 0) {
                        $dirty2 &= -113;
                    }
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 32) != 0) {
                        $dirty = (-458753) & $dirty2;
                        windowInsets3 = windowInsets2;
                        contentPadding3 = contentPadding2;
                        modifier2 = modifier;
                    } else {
                        modifier2 = modifier;
                        $dirty = $dirty2;
                        windowInsets3 = windowInsets2;
                        contentPadding3 = contentPadding2;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1391700845, $dirty, -1, "androidx.compose.material3.BottomAppBar (AppBar.kt:454)");
                }
                SurfaceKt.m1806SurfaceT9BRK9s(modifier2, ShapesKt.toShape(BottomAppBarTokens.INSTANCE.getContainerShape(), $composer2, 6), containerColor2, contentColor2, tonalElevation2, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -396569832, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBar$3
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
                        ComposerKt.sourceInformation($composer3, "C471@23563L365:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-396569832, $changed2, -1, "androidx.compose.material3.BottomAppBar.<anonymous> (AppBar.kt:470)");
                            }
                            Modifier modifier$iv = PaddingKt.padding(SizeKt.m520height3ABfNKs(WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), windowInsets3), BottomAppBarTokens.INSTANCE.m2027getContainerHeightD9Ej5fM()), contentPadding3);
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function3 = content;
                            int $changed$iv = (($dirty >> 9) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
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
                            int i9 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function3.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, ($dirty & 14) | 12582912 | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344), 96);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                contentPadding4 = contentPadding3;
                windowInsets4 = windowInsets3;
                containerColor3 = containerColor2;
                contentColor3 = contentColor2;
                tonalElevation3 = tonalElevation2;
            } else {
                $composer2.skipToGroupEnd();
                contentColor3 = contentColor2;
                tonalElevation3 = tonalElevation2;
                windowInsets4 = windowInsets2;
                contentPadding4 = contentPadding2;
                modifier3 = modifier;
                containerColor3 = containerColor2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final long j = containerColor3;
            final long j2 = contentColor3;
            final float f = tonalElevation3;
            final PaddingValues paddingValues = contentPadding4;
            final WindowInsets windowInsets5 = windowInsets4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBar$4
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
                    AppBarKt.m1309BottomAppBar1oL4kX8(modifier4, j, j2, f, paddingValues, windowInsets5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty2 |= i2;
        if (($dirty2 & 2995931) == 599186) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 2) != 0) {
                    containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty2 &= -113;
                }
                if ((i & 4) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                }
                if (i6 != 0) {
                    tonalElevation2 = BottomAppBarDefaults.INSTANCE.m1323getContainerElevationD9Ej5fM();
                }
                if (i7 != 0) {
                    contentPadding2 = BottomAppBarDefaults.INSTANCE.getContentPadding();
                }
                if ((i & 32) != 0) {
                    windowInsets3 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty = $dirty2 & (-458753);
                    contentPadding3 = contentPadding2;
                } else {
                    $dirty = $dirty2;
                    windowInsets3 = windowInsets2;
                    contentPadding3 = contentPadding2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 2) != 0) {
                    containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty2 &= -113;
                }
                if ((i & 4) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                }
                if (i6 != 0) {
                    tonalElevation2 = BottomAppBarDefaults.INSTANCE.m1323getContainerElevationD9Ej5fM();
                }
                if (i7 != 0) {
                    contentPadding2 = BottomAppBarDefaults.INSTANCE.getContentPadding();
                }
                if ((i & 32) != 0) {
                    windowInsets3 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty = $dirty2 & (-458753);
                    contentPadding3 = contentPadding2;
                } else {
                    $dirty = $dirty2;
                    windowInsets3 = windowInsets2;
                    contentPadding3 = contentPadding2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1391700845, $dirty, -1, "androidx.compose.material3.BottomAppBar (AppBar.kt:454)");
            }
            SurfaceKt.m1806SurfaceT9BRK9s(modifier2, ShapesKt.toShape(BottomAppBarTokens.INSTANCE.getContainerShape(), $composer2, 6), containerColor2, contentColor2, tonalElevation2, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -396569832, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBar$3
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
                    ComposerKt.sourceInformation($composer3, "C471@23563L365:AppBar.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-396569832, $changed2, -1, "androidx.compose.material3.BottomAppBar.<anonymous> (AppBar.kt:470)");
                        }
                        Modifier modifier$iv = PaddingKt.padding(SizeKt.m520height3ABfNKs(WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), windowInsets3), BottomAppBarTokens.INSTANCE.m2027getContainerHeightD9Ej5fM()), contentPadding3);
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        Function3<RowScope, Composer, Integer, Unit> function3 = content;
                        int $changed$iv = (($dirty >> 9) & 7168) | 432;
                        $composer3.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
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
                        int i9 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                        function3.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, ($dirty & 14) | 12582912 | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344), 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            contentPadding4 = contentPadding3;
            windowInsets4 = windowInsets3;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            tonalElevation3 = tonalElevation2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 2) != 0) {
                    containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty2 &= -113;
                }
                if ((i & 4) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                }
                if (i6 != 0) {
                    tonalElevation2 = BottomAppBarDefaults.INSTANCE.m1323getContainerElevationD9Ej5fM();
                }
                if (i7 != 0) {
                    contentPadding2 = BottomAppBarDefaults.INSTANCE.getContentPadding();
                }
                if ((i & 32) != 0) {
                    windowInsets3 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty = $dirty2 & (-458753);
                    contentPadding3 = contentPadding2;
                } else {
                    $dirty = $dirty2;
                    windowInsets3 = windowInsets2;
                    contentPadding3 = contentPadding2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 2) != 0) {
                    containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty2 &= -113;
                }
                if ((i & 4) != 0) {
                    contentColor2 = ColorSchemeKt.m1426contentColorForek8zF_U(containerColor2, $composer2, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                }
                if (i6 != 0) {
                    tonalElevation2 = BottomAppBarDefaults.INSTANCE.m1323getContainerElevationD9Ej5fM();
                }
                if (i7 != 0) {
                    contentPadding2 = BottomAppBarDefaults.INSTANCE.getContentPadding();
                }
                if ((i & 32) != 0) {
                    windowInsets3 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty = $dirty2 & (-458753);
                    contentPadding3 = contentPadding2;
                } else {
                    $dirty = $dirty2;
                    windowInsets3 = windowInsets2;
                    contentPadding3 = contentPadding2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1391700845, $dirty, -1, "androidx.compose.material3.BottomAppBar (AppBar.kt:454)");
            }
            SurfaceKt.m1806SurfaceT9BRK9s(modifier2, ShapesKt.toShape(BottomAppBarTokens.INSTANCE.getContainerShape(), $composer2, 6), containerColor2, contentColor2, tonalElevation2, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -396569832, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBar$3
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
                    ComposerKt.sourceInformation($composer3, "C471@23563L365:AppBar.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-396569832, $changed2, -1, "androidx.compose.material3.BottomAppBar.<anonymous> (AppBar.kt:470)");
                        }
                        Modifier modifier$iv = PaddingKt.padding(SizeKt.m520height3ABfNKs(WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), windowInsets3), BottomAppBarTokens.INSTANCE.m2027getContainerHeightD9Ej5fM()), contentPadding3);
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        Function3<RowScope, Composer, Integer, Unit> function3 = content;
                        int $changed$iv = (($dirty >> 9) & 7168) | 432;
                        $composer3.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
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
                        int i9 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                        function3.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, ($dirty & 14) | 12582912 | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344), 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            contentPadding4 = contentPadding3;
            windowInsets4 = windowInsets3;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            tonalElevation3 = tonalElevation2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final long j3 = containerColor3;
        final long j4 = contentColor3;
        final float f2 = tonalElevation3;
        final PaddingValues paddingValues2 = contentPadding4;
        final WindowInsets windowInsets6 = windowInsets4;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBar$4
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
                AppBarKt.m1309BottomAppBar1oL4kX8(modifier5, j3, j4, f2, paddingValues2, windowInsets6, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:27:0x00b3  */
    public static final TopAppBarState rememberTopAppBarState(float initialHeightOffsetLimit, float initialHeightOffset, float initialContentOffset, Composer $composer, int $changed, int i) {
        $composer.startReplaceableGroup(1801969826);
        ComposerKt.sourceInformation($composer, "C(rememberTopAppBarState)P(2,1)799@38640L145,799@38593L192:AppBar.kt#uh7d8r");
        final float initialHeightOffsetLimit2 = (i & 1) != 0 ? -3.4028235E38f : initialHeightOffsetLimit;
        final float initialHeightOffset2 = (i & 2) != 0 ? 0.0f : initialHeightOffset;
        final float initialContentOffset2 = (i & 4) != 0 ? 0.0f : initialContentOffset;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1801969826, $changed, -1, "androidx.compose.material3.rememberTopAppBarState (AppBar.kt:794)");
        }
        Object[] objArr = new Object[0];
        Saver<TopAppBarState, ?> saver = TopAppBarState.INSTANCE.getSaver();
        Object key1$iv = Float.valueOf(initialHeightOffsetLimit2);
        Object key2$iv = Float.valueOf(initialHeightOffset2);
        Object key3$iv = Float.valueOf(initialContentOffset2);
        int i2 = ($changed & 14) | ($changed & 112) | ($changed & 896);
        $composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(key1$iv) | $composer.changed(key2$iv) | $composer.changed(key3$iv);
        Object value$iv$iv = $composer.rememberedValue();
        if (!invalid$iv$iv) {
            Object key1$iv2 = Composer.INSTANCE.getEmpty();
            if (value$iv$iv == key1$iv2) {
            }
            $composer.endReplaceableGroup();
            TopAppBarState topAppBarState = (TopAppBarState) RememberSaveableKt.m2617rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv$iv, $composer, 72, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceableGroup();
            return topAppBarState;
        }
        value$iv$iv = (Function0) new Function0<TopAppBarState>() { // from class: androidx.compose.material3.AppBarKt$rememberTopAppBarState$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TopAppBarState invoke() {
                return new TopAppBarState(initialHeightOffsetLimit2, initialHeightOffset2, initialContentOffset2);
            }
        };
        $composer.updateRememberedValue(value$iv$iv);
        $composer.endReplaceableGroup();
        TopAppBarState topAppBarState2 = (TopAppBarState) RememberSaveableKt.m2617rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv$iv, $composer, 72, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return topAppBarState2;
    }

    static {
        float arg0$iv = Dp.m5274constructorimpl(16);
        float other$iv = Dp.m5274constructorimpl(12);
        BottomAppBarHorizontalPadding = Dp.m5274constructorimpl(arg0$iv - other$iv);
        float arg0$iv2 = Dp.m5274constructorimpl(16);
        float other$iv2 = Dp.m5274constructorimpl(12);
        BottomAppBarVerticalPadding = Dp.m5274constructorimpl(arg0$iv2 - other$iv2);
        float arg0$iv3 = Dp.m5274constructorimpl(16);
        float other$iv3 = BottomAppBarHorizontalPadding;
        FABHorizontalPadding = Dp.m5274constructorimpl(arg0$iv3 - other$iv3);
        float arg0$iv4 = Dp.m5274constructorimpl(12);
        float other$iv4 = BottomAppBarVerticalPadding;
        FABVerticalPadding = Dp.m5274constructorimpl(arg0$iv4 - other$iv4);
        TopTitleAlphaEasing = new CubicBezierEasing(0.8f, 0.0f, 0.8f, 0.15f);
        MediumTitleBottomPadding = Dp.m5274constructorimpl(24);
        LargeTitleBottomPadding = Dp.m5274constructorimpl(28);
        TopAppBarHorizontalPadding = Dp.m5274constructorimpl(4);
        float arg0$iv5 = Dp.m5274constructorimpl(16);
        float other$iv5 = TopAppBarHorizontalPadding;
        TopAppBarTitleInset = Dp.m5274constructorimpl(arg0$iv5 - other$iv5);
    }

    public static final float getBottomAppBarVerticalPadding() {
        return BottomAppBarVerticalPadding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:100:0x0125  */
    /* JADX WARN: Code duplicated, block: B:104:0x0135 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:105:0x0137  */
    /* JADX WARN: Code duplicated, block: B:106:0x013c  */
    /* JADX WARN: Code duplicated, block: B:109:0x0143  */
    /* JADX WARN: Code duplicated, block: B:112:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:116:0x01b7 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:120:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:123:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:126:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:127:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:130:0x024c  */
    /* JADX WARN: Code duplicated, block: B:147:0x02fb  */
    /* JADX WARN: Code duplicated, block: B:150:0x034d  */
    /* JADX WARN: Code duplicated, block: B:153:0x0356  */
    /* JADX WARN: Code duplicated, block: B:154:0x0359  */
    /* JADX WARN: Code duplicated, block: B:69:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:70:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:72:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:74:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:75:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:77:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:80:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:82:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:86:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:90:0x0106  */
    /* JADX WARN: Code duplicated, block: B:92:0x010a  */
    /* JADX WARN: Code duplicated, block: B:94:0x010f  */
    /* JADX WARN: Code duplicated, block: B:96:0x0115  */
    /* JADX WARN: Code duplicated, block: B:97:0x0118  */
    public static final void SingleRowTopAppBar(Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle titleTextStyle, final boolean centeredTitle, final Function2<? super Composer, ? super Integer, Unit> function3, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4, final WindowInsets windowInsets, final TopAppBarColors colors, final TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        int i2;
        int i3;
        int i4;
        int i5;
        final int $dirty;
        Modifier.Companion modifier3;
        boolean invalid$iv$iv;
        Object value$iv$iv;
        float overlappedFraction;
        float colorTransitionFraction;
        float f;
        Modifier.Companion companionDraggable;
        Modifier modifier4;
        Object value$iv$iv2;
        AppBarKt$SingleRowTopAppBar$appBarDragModifier$2$1 value$iv$iv3;
        TopAppBarState state;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer $composer2 = $composer.startRestartGroup(1841601619);
        ComposerKt.sourceInformation($composer2, "C(SingleRowTopAppBar)P(3,6,7,1,4!1,8)*1035@47262L7,1036@47335L167,1036@47324L178,1049@48098L24,1048@48048L151,1086@49505L1166:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        int i6 = i & 1;
        if (i6 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 14) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer2.changed(titleTextStyle) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer2.changed(centeredTitle) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if ((57344 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 16384 : 8192;
        }
        if ((i & 32) == 0) {
            if ((458752 & $changed) == 0) {
                i2 = $composer2.changedInstance(function4) ? 131072 : 65536;
            }
            if ((i & 64) != 0) {
                $dirty2 |= 1572864;
            } else if ((3670016 & $changed) != 0) {
                if ($composer2.changed(windowInsets)) {
                    i3 = 1048576;
                } else {
                    i3 = 524288;
                }
                $dirty2 |= i3;
            }
            if ((i & 128) != 0) {
                if ((29360128 & $changed) == 0) {
                    if ($composer2.changed(colors)) {
                        i4 = 8388608;
                    } else {
                        i4 = 4194304;
                    }
                }
                if ((i & 256) != 0) {
                    if ((234881024 & $changed) == 0) {
                        if ($composer2.changed(scrollBehavior)) {
                            i5 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i5 = 33554432;
                        }
                    }
                    $dirty = $dirty2;
                    if ((191739611 & $dirty) == 38347922 || !$composer2.getSkipping()) {
                        if (i6 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                        }
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer2.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        Density $this$SingleRowTopAppBar_u24lambda_u241 = (Density) objConsume;
                        final float f2 = -$this$SingleRowTopAppBar_u24lambda_u241.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                        Object key2$iv = Float.valueOf(f2);
                        int i7 = ($dirty >> 24) & 14;
                        $composer2.startReplaceableGroup(511388516);
                        ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                        invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv);
                        value$iv$iv = $composer2.rememberedValue();
                        if (!invalid$iv$iv || value$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    TopAppBarState state2;
                                    TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                                    if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f2)) {
                                        TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                                        TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                                        if (state3 == null) {
                                            return;
                                        }
                                        state3.setHeightOffsetLimit(f2);
                                    }
                                }
                            };
                            $composer2.updateRememberedValue(value$iv$iv);
                        }
                        $composer2.endReplaceableGroup();
                        EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                        if (scrollBehavior != null || (state = scrollBehavior.getState()) == null) {
                            overlappedFraction = 0.0f;
                        } else {
                            overlappedFraction = state.getOverlappedFraction();
                        }
                        colorTransitionFraction = overlappedFraction;
                        if (colorTransitionFraction > 0.01f) {
                            f = 1.0f;
                        } else {
                            f = 0.0f;
                        }
                        float fraction = f;
                        State<Color> stateM68animateColorAsStateeuL9pac = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                        final Function2 actionsRow = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                                ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                                if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                                    }
                                    Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                    Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                                    int $changed$iv = (($dirty >> 6) & 7168) | 432;
                                    $composer3.startReplaceableGroup(693286680);
                                    ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                    Modifier modifier$iv = Modifier.INSTANCE;
                                    MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                    int $changed$iv$iv = ($changed$iv << 3) & 112;
                                    $composer3.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume2 = $composer3.consume(localDensity2);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    Density density$iv$iv = (Density) objConsume2;
                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume3 = $composer3.consume(localLayoutDirection);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume3;
                                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume4 = $composer3.consume(localViewConfiguration);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume4;
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
                                    ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                    function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer3.endReplaceableGroup();
                                    $composer3.endNode();
                                    $composer3.endReplaceableGroup();
                                    $composer3.endReplaceableGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer3.skipToGroupEnd();
                            }
                        });
                        $composer2.startReplaceableGroup(-1008376318);
                        ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                        if (scrollBehavior != null || scrollBehavior.getIsPinned()) {
                            companionDraggable = Modifier.INSTANCE;
                        } else {
                            Modifier.Companion companion = Modifier.INSTANCE;
                            Orientation orientation = Orientation.Vertical;
                            int i8 = ($dirty >> 24) & 14;
                            $composer2.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv$iv2 = $composer2.changed(scrollBehavior);
                            Object it$iv$iv = $composer2.rememberedValue();
                            if (invalid$iv$iv2 || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv2 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$appBarDragModifier$1$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Float f3) {
                                        invoke(f3.floatValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(float delta) {
                                        scrollBehavior.getState().setHeightOffset(scrollBehavior.getState().getHeightOffset() + delta);
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv$iv2);
                            } else {
                                value$iv$iv2 = it$iv$iv;
                            }
                            $composer2.endReplaceableGroup();
                            DraggableState draggableStateRememberDraggableState = DraggableKt.rememberDraggableState((Function1) value$iv$iv2, $composer2, 0);
                            Modifier.Companion companion2 = companion;
                            int i9 = ($dirty >> 24) & 14;
                            $composer2.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv$iv3 = $composer2.changed(scrollBehavior);
                            Object it$iv$iv2 = $composer2.rememberedValue();
                            if (invalid$iv$iv3 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv3 = new AppBarKt$SingleRowTopAppBar$appBarDragModifier$2$1(scrollBehavior, null);
                                $composer2.updateRememberedValue(value$iv$iv3);
                            } else {
                                value$iv$iv3 = it$iv$iv2;
                            }
                            $composer2.endReplaceableGroup();
                            companionDraggable = DraggableKt.draggable(companion2, draggableStateRememberDraggableState, orientation, (188 & 4) != 0, (188 & 8) != 0 ? null : null, (188 & 16) != 0 ? false : false, (188 & 32) != 0 ? new DraggableKt.C01841(null) : null, (188 & 64) != 0 ? new DraggableKt.AnonymousClass2(null) : (Function3) value$iv$iv3, (188 & 128) != 0 ? false : false);
                        }
                        $composer2.endReplaceableGroup();
                        Modifier appBarDragModifier = companionDraggable;
                        modifier4 = modifier3;
                        SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                                TopAppBarState state2;
                                ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                                if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                    $composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                                }
                                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                                Object objConsume2 = $composer3.consume(localDensity2);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                                Density $this$invoke_u24lambda_u240 = (Density) objConsume2;
                                float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                                Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                                long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                                long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                                long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                                Function2<Composer, Integer, Unit> function5 = function2;
                                TextStyle textStyle = titleTextStyle;
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                                Function2<Composer, Integer, Unit> function6 = function3;
                                Function2<Composer, Integer, Unit> function7 = actionsRow;
                                int i10 = $dirty;
                                AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i10 << 12) & 458752) | 113246208 | ((i10 << 12) & 3670016), ((i10 >> 6) & 896) | 3126);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), $composer2, 12582912, 122);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        $composer2.skipToGroupEnd();
                        modifier4 = modifier2;
                    }
                    scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup == null) {
                        return;
                    }
                    final Modifier modifier5 = modifier4;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.3
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
                            AppBarKt.SingleRowTopAppBar(modifier5, function2, titleTextStyle, centeredTitle, function3, function4, windowInsets, colors, scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                        }
                    });
                }
                i5 = 100663296;
                $dirty2 |= i5;
                $dirty = $dirty2;
                if ((191739611 & $dirty) == 38347922) {
                    if (i6 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                    }
                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume2 = $composer2.consume(localDensity2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Density $this$SingleRowTopAppBar_u24lambda_u242 = (Density) objConsume2;
                    final float f3 = -$this$SingleRowTopAppBar_u24lambda_u242.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                    Object key2$iv2 = Float.valueOf(f3);
                    int i10 = ($dirty >> 24) & 14;
                    $composer2.startReplaceableGroup(511388516);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv2);
                    value$iv$iv = $composer2.rememberedValue();
                    if (!invalid$iv$iv) {
                    }
                    value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            TopAppBarState state2;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f3)) {
                                TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                                TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                                if (state3 == null) {
                                    return;
                                }
                                state3.setHeightOffsetLimit(f3);
                            }
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv);
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                    if (scrollBehavior != null) {
                        overlappedFraction = 0.0f;
                    } else {
                        overlappedFraction = 0.0f;
                    }
                    colorTransitionFraction = overlappedFraction;
                    if (colorTransitionFraction > 0.01f) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    float fraction2 = f;
                    State<Color> stateM68animateColorAsStateeuL9pac2 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction2, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                    final Function2<? super Composer, ? super Integer, Unit> actionsRow2 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                            ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                                }
                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                                int $changed$iv = (($dirty >> 6) & 7168) | 432;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume3 = $composer3.consume(localDensity3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume3;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume4 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume5 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
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
                                int i11 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    });
                    $composer2.startReplaceableGroup(-1008376318);
                    ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                    if (scrollBehavior != null) {
                        companionDraggable = Modifier.INSTANCE;
                    } else {
                        companionDraggable = Modifier.INSTANCE;
                    }
                    $composer2.endReplaceableGroup();
                    Modifier appBarDragModifier2 = companionDraggable;
                    modifier4 = modifier3;
                    SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier2), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac2), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                            TopAppBarState state2;
                            ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                            }
                            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer3.consume(localDensity3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            Density $this$invoke_u24lambda_u240 = (Density) objConsume3;
                            float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                            Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                            long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                            long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                            long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                            Function2<Composer, Integer, Unit> function5 = function2;
                            TextStyle textStyle = titleTextStyle;
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                            Function2<Composer, Integer, Unit> function6 = function3;
                            Function2<Composer, Integer, Unit> function7 = actionsRow2;
                            int i11 = $dirty;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i11 << 12) & 458752) | 113246208 | ((i11 << 12) & 3670016), ((i11 >> 6) & 896) | 3126);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }), $composer2, 12582912, 122);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    if (i6 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                    }
                    ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume3 = $composer2.consume(localDensity3);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Density $this$SingleRowTopAppBar_u24lambda_u243 = (Density) objConsume3;
                    final float f4 = -$this$SingleRowTopAppBar_u24lambda_u243.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                    Object key2$iv3 = Float.valueOf(f4);
                    int i11 = ($dirty >> 24) & 14;
                    $composer2.startReplaceableGroup(511388516);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv3);
                    value$iv$iv = $composer2.rememberedValue();
                    if (!invalid$iv$iv) {
                    }
                    value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            TopAppBarState state2;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f4)) {
                                TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                                TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                                if (state3 == null) {
                                    return;
                                }
                                state3.setHeightOffsetLimit(f4);
                            }
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv);
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                    if (scrollBehavior != null) {
                        overlappedFraction = 0.0f;
                    } else {
                        overlappedFraction = 0.0f;
                    }
                    colorTransitionFraction = overlappedFraction;
                    if (colorTransitionFraction > 0.01f) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    float fraction3 = f;
                    State<Color> stateM68animateColorAsStateeuL9pac3 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction3, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                    final Function2<? super Composer, ? super Integer, Unit> actionsRow3 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                            ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                                }
                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                                int $changed$iv = (($dirty >> 6) & 7168) | 432;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume4 = $composer3.consume(localDensity4);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume4;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume5 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume5;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume6;
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
                                int i12 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    });
                    $composer2.startReplaceableGroup(-1008376318);
                    ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                    if (scrollBehavior != null) {
                        companionDraggable = Modifier.INSTANCE;
                    } else {
                        companionDraggable = Modifier.INSTANCE;
                    }
                    $composer2.endReplaceableGroup();
                    Modifier appBarDragModifier3 = companionDraggable;
                    modifier4 = modifier3;
                    SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier3), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac3), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                            TopAppBarState state2;
                            ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                            }
                            ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume4 = $composer3.consume(localDensity4);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            Density $this$invoke_u24lambda_u240 = (Density) objConsume4;
                            float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                            Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                            long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                            long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                            long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                            Function2<Composer, Integer, Unit> function5 = function2;
                            TextStyle textStyle = titleTextStyle;
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                            Function2<Composer, Integer, Unit> function6 = function3;
                            Function2<Composer, Integer, Unit> function7 = actionsRow3;
                            int i12 = $dirty;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i12 << 12) & 458752) | 113246208 | ((i12 << 12) & 3670016), ((i12 >> 6) & 896) | 3126);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }), $composer2, 12582912, 122);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup == null) {
                    return;
                }
                final Modifier modifier6 = modifier4;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.3
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
                        AppBarKt.SingleRowTopAppBar(modifier6, function2, titleTextStyle, centeredTitle, function3, function4, windowInsets, colors, scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                    }
                });
            }
            i4 = 12582912;
            $dirty2 |= i4;
            if ((i & 256) != 0) {
                if ((234881024 & $changed) == 0) {
                    if ($composer2.changed(scrollBehavior)) {
                        i5 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i5 = 33554432;
                    }
                }
                $dirty = $dirty2;
                if ((191739611 & $dirty) == 38347922) {
                    if (i6 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                    }
                    ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume4 = $composer2.consume(localDensity4);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Density $this$SingleRowTopAppBar_u24lambda_u244 = (Density) objConsume4;
                    final float f5 = -$this$SingleRowTopAppBar_u24lambda_u244.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                    Object key2$iv4 = Float.valueOf(f5);
                    int i12 = ($dirty >> 24) & 14;
                    $composer2.startReplaceableGroup(511388516);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv4);
                    value$iv$iv = $composer2.rememberedValue();
                    if (!invalid$iv$iv) {
                    }
                    value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            TopAppBarState state2;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f5)) {
                                TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                                TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                                if (state3 == null) {
                                    return;
                                }
                                state3.setHeightOffsetLimit(f5);
                            }
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv);
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                    if (scrollBehavior != null) {
                        overlappedFraction = 0.0f;
                    } else {
                        overlappedFraction = 0.0f;
                    }
                    colorTransitionFraction = overlappedFraction;
                    if (colorTransitionFraction > 0.01f) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    float fraction4 = f;
                    State<Color> stateM68animateColorAsStateeuL9pac4 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction4, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                    final Function2<? super Composer, ? super Integer, Unit> actionsRow4 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                            ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                                }
                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                                int $changed$iv = (($dirty >> 6) & 7168) | 432;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume5 = $composer3.consume(localDensity5);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume5;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume6;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume7 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume7;
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
                                int i13 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    });
                    $composer2.startReplaceableGroup(-1008376318);
                    ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                    if (scrollBehavior != null) {
                        companionDraggable = Modifier.INSTANCE;
                    } else {
                        companionDraggable = Modifier.INSTANCE;
                    }
                    $composer2.endReplaceableGroup();
                    Modifier appBarDragModifier4 = companionDraggable;
                    modifier4 = modifier3;
                    SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier4), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac4), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                            TopAppBarState state2;
                            ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                            }
                            ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume5 = $composer3.consume(localDensity5);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            Density $this$invoke_u24lambda_u240 = (Density) objConsume5;
                            float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                            Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                            long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                            long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                            long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                            Function2<Composer, Integer, Unit> function5 = function2;
                            TextStyle textStyle = titleTextStyle;
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                            Function2<Composer, Integer, Unit> function6 = function3;
                            Function2<Composer, Integer, Unit> function7 = actionsRow4;
                            int i13 = $dirty;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i13 << 12) & 458752) | 113246208 | ((i13 << 12) & 3670016), ((i13 >> 6) & 896) | 3126);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }), $composer2, 12582912, 122);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    if (i6 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                    }
                    ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume5 = $composer2.consume(localDensity5);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Density $this$SingleRowTopAppBar_u24lambda_u245 = (Density) objConsume5;
                    final float f6 = -$this$SingleRowTopAppBar_u24lambda_u245.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                    Object key2$iv5 = Float.valueOf(f6);
                    int i13 = ($dirty >> 24) & 14;
                    $composer2.startReplaceableGroup(511388516);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv5);
                    value$iv$iv = $composer2.rememberedValue();
                    if (!invalid$iv$iv) {
                    }
                    value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            TopAppBarState state2;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f6)) {
                                TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                                TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                                if (state3 == null) {
                                    return;
                                }
                                state3.setHeightOffsetLimit(f6);
                            }
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv);
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                    if (scrollBehavior != null) {
                        overlappedFraction = 0.0f;
                    } else {
                        overlappedFraction = 0.0f;
                    }
                    colorTransitionFraction = overlappedFraction;
                    if (colorTransitionFraction > 0.01f) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    float fraction5 = f;
                    State<Color> stateM68animateColorAsStateeuL9pac5 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction5, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                    final Function2<? super Composer, ? super Integer, Unit> actionsRow5 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                            ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                                }
                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                                int $changed$iv = (($dirty >> 6) & 7168) | 432;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer3.consume(localDensity6);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume6;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume7 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume7;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume8 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume8;
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
                                int i14 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    });
                    $composer2.startReplaceableGroup(-1008376318);
                    ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                    if (scrollBehavior != null) {
                        companionDraggable = Modifier.INSTANCE;
                    } else {
                        companionDraggable = Modifier.INSTANCE;
                    }
                    $composer2.endReplaceableGroup();
                    Modifier appBarDragModifier5 = companionDraggable;
                    modifier4 = modifier3;
                    SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier5), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac5), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                            TopAppBarState state2;
                            ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                            }
                            ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume6 = $composer3.consume(localDensity6);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            Density $this$invoke_u24lambda_u240 = (Density) objConsume6;
                            float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                            Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                            long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                            long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                            long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                            Function2<Composer, Integer, Unit> function5 = function2;
                            TextStyle textStyle = titleTextStyle;
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                            Function2<Composer, Integer, Unit> function6 = function3;
                            Function2<Composer, Integer, Unit> function7 = actionsRow5;
                            int i14 = $dirty;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i14 << 12) & 458752) | 113246208 | ((i14 << 12) & 3670016), ((i14 >> 6) & 896) | 3126);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }), $composer2, 12582912, 122);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup == null) {
                    return;
                }
                final Modifier modifier7 = modifier4;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.3
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

                    public final void invoke(Composer composer, int i14) {
                        AppBarKt.SingleRowTopAppBar(modifier7, function2, titleTextStyle, centeredTitle, function3, function4, windowInsets, colors, scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                    }
                });
            }
            i5 = 100663296;
            $dirty2 |= i5;
            $dirty = $dirty2;
            if ((191739611 & $dirty) == 38347922) {
                if (i6 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                }
                ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume6 = $composer2.consume(localDensity6);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density $this$SingleRowTopAppBar_u24lambda_u246 = (Density) objConsume6;
                final float f7 = -$this$SingleRowTopAppBar_u24lambda_u246.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                Object key2$iv6 = Float.valueOf(f7);
                int i14 = ($dirty >> 24) & 14;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv6);
                value$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                }
                value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        TopAppBarState state2;
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f7)) {
                            TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                            TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                            if (state3 == null) {
                                return;
                            }
                            state3.setHeightOffsetLimit(f7);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                if (scrollBehavior != null) {
                    overlappedFraction = 0.0f;
                } else {
                    overlappedFraction = 0.0f;
                }
                colorTransitionFraction = overlappedFraction;
                if (colorTransitionFraction > 0.01f) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                float fraction6 = f;
                State<Color> stateM68animateColorAsStateeuL9pac6 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction6, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                final Function2<? super Composer, ? super Integer, Unit> actionsRow6 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                        ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                            }
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                            int $changed$iv = (($dirty >> 6) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume7 = $composer3.consume(localDensity7);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume7;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume8 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume8;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume9 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume9;
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
                            int i15 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                $composer2.startReplaceableGroup(-1008376318);
                ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                if (scrollBehavior != null) {
                    companionDraggable = Modifier.INSTANCE;
                } else {
                    companionDraggable = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                Modifier appBarDragModifier6 = companionDraggable;
                modifier4 = modifier3;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier6), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac6), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                        TopAppBarState state2;
                        ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                        }
                        ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume7 = $composer3.consume(localDensity7);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        Density $this$invoke_u24lambda_u240 = (Density) objConsume7;
                        float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                        Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                        long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                        long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                        long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                        Function2<Composer, Integer, Unit> function5 = function2;
                        TextStyle textStyle = titleTextStyle;
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                        Function2<Composer, Integer, Unit> function6 = function3;
                        Function2<Composer, Integer, Unit> function7 = actionsRow6;
                        int i15 = $dirty;
                        AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i15 << 12) & 458752) | 113246208 | ((i15 << 12) & 3670016), ((i15 >> 6) & 896) | 3126);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer2, 12582912, 122);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                if (i6 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                }
                ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume7 = $composer2.consume(localDensity7);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density $this$SingleRowTopAppBar_u24lambda_u247 = (Density) objConsume7;
                final float f8 = -$this$SingleRowTopAppBar_u24lambda_u247.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                Object key2$iv7 = Float.valueOf(f8);
                int i15 = ($dirty >> 24) & 14;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv7);
                value$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                }
                value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        TopAppBarState state2;
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f8)) {
                            TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                            TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                            if (state3 == null) {
                                return;
                            }
                            state3.setHeightOffsetLimit(f8);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                if (scrollBehavior != null) {
                    overlappedFraction = 0.0f;
                } else {
                    overlappedFraction = 0.0f;
                }
                colorTransitionFraction = overlappedFraction;
                if (colorTransitionFraction > 0.01f) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                float fraction7 = f;
                State<Color> stateM68animateColorAsStateeuL9pac7 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction7, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                final Function2<? super Composer, ? super Integer, Unit> actionsRow7 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                        ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                            }
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                            int $changed$iv = (($dirty >> 6) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume8 = $composer3.consume(localDensity8);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume8;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume9 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume9;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume10 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume10;
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
                            int i16 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                $composer2.startReplaceableGroup(-1008376318);
                ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                if (scrollBehavior != null) {
                    companionDraggable = Modifier.INSTANCE;
                } else {
                    companionDraggable = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                Modifier appBarDragModifier7 = companionDraggable;
                modifier4 = modifier3;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier7), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac7), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                        TopAppBarState state2;
                        ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                        }
                        ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume8 = $composer3.consume(localDensity8);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        Density $this$invoke_u24lambda_u240 = (Density) objConsume8;
                        float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                        Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                        long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                        long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                        long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                        Function2<Composer, Integer, Unit> function5 = function2;
                        TextStyle textStyle = titleTextStyle;
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                        Function2<Composer, Integer, Unit> function6 = function3;
                        Function2<Composer, Integer, Unit> function7 = actionsRow7;
                        int i16 = $dirty;
                        AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i16 << 12) & 458752) | 113246208 | ((i16 << 12) & 3670016), ((i16 >> 6) & 896) | 3126);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer2, 12582912, 122);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier8 = modifier4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.3
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
                    AppBarKt.SingleRowTopAppBar(modifier8, function2, titleTextStyle, centeredTitle, function3, function4, windowInsets, colors, scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty2 |= i2;
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if ((3670016 & $changed) != 0) {
            if ($composer2.changed(windowInsets)) {
                i3 = 1048576;
            } else {
                i3 = 524288;
            }
            $dirty2 |= i3;
        }
        if ((i & 128) != 0) {
            if ((29360128 & $changed) == 0) {
                if ($composer2.changed(colors)) {
                    i4 = 8388608;
                } else {
                    i4 = 4194304;
                }
            }
            if ((i & 256) != 0) {
                if ((234881024 & $changed) == 0) {
                    if ($composer2.changed(scrollBehavior)) {
                        i5 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i5 = 33554432;
                    }
                }
                $dirty = $dirty2;
                if ((191739611 & $dirty) == 38347922) {
                    if (i6 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                    }
                    ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume8 = $composer2.consume(localDensity8);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Density $this$SingleRowTopAppBar_u24lambda_u248 = (Density) objConsume8;
                    final float f9 = -$this$SingleRowTopAppBar_u24lambda_u248.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                    Object key2$iv8 = Float.valueOf(f9);
                    int i16 = ($dirty >> 24) & 14;
                    $composer2.startReplaceableGroup(511388516);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv8);
                    value$iv$iv = $composer2.rememberedValue();
                    if (!invalid$iv$iv) {
                    }
                    value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            TopAppBarState state2;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f9)) {
                                TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                                TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                                if (state3 == null) {
                                    return;
                                }
                                state3.setHeightOffsetLimit(f9);
                            }
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv);
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                    if (scrollBehavior != null) {
                        overlappedFraction = 0.0f;
                    } else {
                        overlappedFraction = 0.0f;
                    }
                    colorTransitionFraction = overlappedFraction;
                    if (colorTransitionFraction > 0.01f) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    float fraction8 = f;
                    State<Color> stateM68animateColorAsStateeuL9pac8 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction8, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                    final Function2<? super Composer, ? super Integer, Unit> actionsRow8 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                            ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                                }
                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                                int $changed$iv = (($dirty >> 6) & 7168) | 432;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity9 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume9 = $composer3.consume(localDensity9);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume9;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume10 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume10;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume11 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume11;
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
                                int i17 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    });
                    $composer2.startReplaceableGroup(-1008376318);
                    ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                    if (scrollBehavior != null) {
                        companionDraggable = Modifier.INSTANCE;
                    } else {
                        companionDraggable = Modifier.INSTANCE;
                    }
                    $composer2.endReplaceableGroup();
                    Modifier appBarDragModifier8 = companionDraggable;
                    modifier4 = modifier3;
                    SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier8), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac8), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                            TopAppBarState state2;
                            ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                            }
                            ProvidableCompositionLocal<Density> localDensity9 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume9 = $composer3.consume(localDensity9);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            Density $this$invoke_u24lambda_u240 = (Density) objConsume9;
                            float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                            Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                            long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                            long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                            long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                            Function2<Composer, Integer, Unit> function5 = function2;
                            TextStyle textStyle = titleTextStyle;
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                            Function2<Composer, Integer, Unit> function6 = function3;
                            Function2<Composer, Integer, Unit> function7 = actionsRow8;
                            int i17 = $dirty;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i17 << 12) & 458752) | 113246208 | ((i17 << 12) & 3670016), ((i17 >> 6) & 896) | 3126);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }), $composer2, 12582912, 122);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    if (i6 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                    }
                    ProvidableCompositionLocal<Density> localDensity9 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume9 = $composer2.consume(localDensity9);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Density $this$SingleRowTopAppBar_u24lambda_u249 = (Density) objConsume9;
                    final float f10 = -$this$SingleRowTopAppBar_u24lambda_u249.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                    Object key2$iv9 = Float.valueOf(f10);
                    int i17 = ($dirty >> 24) & 14;
                    $composer2.startReplaceableGroup(511388516);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv9);
                    value$iv$iv = $composer2.rememberedValue();
                    if (!invalid$iv$iv) {
                    }
                    value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            TopAppBarState state2;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f10)) {
                                TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                                TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                                if (state3 == null) {
                                    return;
                                }
                                state3.setHeightOffsetLimit(f10);
                            }
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv);
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                    if (scrollBehavior != null) {
                        overlappedFraction = 0.0f;
                    } else {
                        overlappedFraction = 0.0f;
                    }
                    colorTransitionFraction = overlappedFraction;
                    if (colorTransitionFraction > 0.01f) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    float fraction9 = f;
                    State<Color> stateM68animateColorAsStateeuL9pac9 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction9, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                    final Function2<? super Composer, ? super Integer, Unit> actionsRow9 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                            ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                                }
                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                                int $changed$iv = (($dirty >> 6) & 7168) | 432;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity10 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume10 = $composer3.consume(localDensity10);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume10;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume11 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume11;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume12 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume12;
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
                                int i18 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    });
                    $composer2.startReplaceableGroup(-1008376318);
                    ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                    if (scrollBehavior != null) {
                        companionDraggable = Modifier.INSTANCE;
                    } else {
                        companionDraggable = Modifier.INSTANCE;
                    }
                    $composer2.endReplaceableGroup();
                    Modifier appBarDragModifier9 = companionDraggable;
                    modifier4 = modifier3;
                    SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier9), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac9), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                            TopAppBarState state2;
                            ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                            }
                            ProvidableCompositionLocal<Density> localDensity10 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume10 = $composer3.consume(localDensity10);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            Density $this$invoke_u24lambda_u240 = (Density) objConsume10;
                            float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                            Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                            long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                            long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                            long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                            Function2<Composer, Integer, Unit> function5 = function2;
                            TextStyle textStyle = titleTextStyle;
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                            Function2<Composer, Integer, Unit> function6 = function3;
                            Function2<Composer, Integer, Unit> function7 = actionsRow9;
                            int i18 = $dirty;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i18 << 12) & 458752) | 113246208 | ((i18 << 12) & 3670016), ((i18 >> 6) & 896) | 3126);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }), $composer2, 12582912, 122);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup == null) {
                    return;
                }
                final Modifier modifier9 = modifier4;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.3
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
                        AppBarKt.SingleRowTopAppBar(modifier9, function2, titleTextStyle, centeredTitle, function3, function4, windowInsets, colors, scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                    }
                });
            }
            i5 = 100663296;
            $dirty2 |= i5;
            $dirty = $dirty2;
            if ((191739611 & $dirty) == 38347922) {
                if (i6 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                }
                ProvidableCompositionLocal<Density> localDensity10 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume10 = $composer2.consume(localDensity10);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density $this$SingleRowTopAppBar_u24lambda_u2410 = (Density) objConsume10;
                final float f11 = -$this$SingleRowTopAppBar_u24lambda_u2410.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                Object key2$iv10 = Float.valueOf(f11);
                int i18 = ($dirty >> 24) & 14;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv10);
                value$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                }
                value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        TopAppBarState state2;
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f11)) {
                            TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                            TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                            if (state3 == null) {
                                return;
                            }
                            state3.setHeightOffsetLimit(f11);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                if (scrollBehavior != null) {
                    overlappedFraction = 0.0f;
                } else {
                    overlappedFraction = 0.0f;
                }
                colorTransitionFraction = overlappedFraction;
                if (colorTransitionFraction > 0.01f) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                float fraction10 = f;
                State<Color> stateM68animateColorAsStateeuL9pac10 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction10, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                final Function2<? super Composer, ? super Integer, Unit> actionsRow10 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                        ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                            }
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                            int $changed$iv = (($dirty >> 6) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity11 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume11 = $composer3.consume(localDensity11);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume11;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume12 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume12;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume13 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume13;
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
                            int i19 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                $composer2.startReplaceableGroup(-1008376318);
                ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                if (scrollBehavior != null) {
                    companionDraggable = Modifier.INSTANCE;
                } else {
                    companionDraggable = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                Modifier appBarDragModifier10 = companionDraggable;
                modifier4 = modifier3;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier10), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac10), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                        TopAppBarState state2;
                        ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                        }
                        ProvidableCompositionLocal<Density> localDensity11 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume11 = $composer3.consume(localDensity11);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        Density $this$invoke_u24lambda_u240 = (Density) objConsume11;
                        float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                        Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                        long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                        long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                        long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                        Function2<Composer, Integer, Unit> function5 = function2;
                        TextStyle textStyle = titleTextStyle;
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                        Function2<Composer, Integer, Unit> function6 = function3;
                        Function2<Composer, Integer, Unit> function7 = actionsRow10;
                        int i19 = $dirty;
                        AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i19 << 12) & 458752) | 113246208 | ((i19 << 12) & 3670016), ((i19 >> 6) & 896) | 3126);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer2, 12582912, 122);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                if (i6 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                }
                ProvidableCompositionLocal<Density> localDensity11 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume11 = $composer2.consume(localDensity11);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density $this$SingleRowTopAppBar_u24lambda_u2411 = (Density) objConsume11;
                final float f12 = -$this$SingleRowTopAppBar_u24lambda_u2411.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                Object key2$iv11 = Float.valueOf(f12);
                int i19 = ($dirty >> 24) & 14;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv11);
                value$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                }
                value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        TopAppBarState state2;
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f12)) {
                            TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                            TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                            if (state3 == null) {
                                return;
                            }
                            state3.setHeightOffsetLimit(f12);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                if (scrollBehavior != null) {
                    overlappedFraction = 0.0f;
                } else {
                    overlappedFraction = 0.0f;
                }
                colorTransitionFraction = overlappedFraction;
                if (colorTransitionFraction > 0.01f) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                float fraction11 = f;
                State<Color> stateM68animateColorAsStateeuL9pac11 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction11, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                final Function2<? super Composer, ? super Integer, Unit> actionsRow11 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                        ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                            }
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                            int $changed$iv = (($dirty >> 6) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity12 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume12 = $composer3.consume(localDensity12);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume12;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume13 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume13;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume14 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume14;
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
                            int i110 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                $composer2.startReplaceableGroup(-1008376318);
                ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                if (scrollBehavior != null) {
                    companionDraggable = Modifier.INSTANCE;
                } else {
                    companionDraggable = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                Modifier appBarDragModifier11 = companionDraggable;
                modifier4 = modifier3;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier11), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac11), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                        TopAppBarState state2;
                        ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                        }
                        ProvidableCompositionLocal<Density> localDensity12 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume12 = $composer3.consume(localDensity12);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        Density $this$invoke_u24lambda_u240 = (Density) objConsume12;
                        float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                        Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                        long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                        long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                        long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                        Function2<Composer, Integer, Unit> function5 = function2;
                        TextStyle textStyle = titleTextStyle;
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                        Function2<Composer, Integer, Unit> function6 = function3;
                        Function2<Composer, Integer, Unit> function7 = actionsRow11;
                        int i110 = $dirty;
                        AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i110 << 12) & 458752) | 113246208 | ((i110 << 12) & 3670016), ((i110 >> 6) & 896) | 3126);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer2, 12582912, 122);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier10 = modifier4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.3
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

                public final void invoke(Composer composer, int i110) {
                    AppBarKt.SingleRowTopAppBar(modifier10, function2, titleTextStyle, centeredTitle, function3, function4, windowInsets, colors, scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i4 = 12582912;
        $dirty2 |= i4;
        if ((i & 256) != 0) {
            if ((234881024 & $changed) == 0) {
                if ($composer2.changed(scrollBehavior)) {
                    i5 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i5 = 33554432;
                }
            }
            $dirty = $dirty2;
            if ((191739611 & $dirty) == 38347922) {
                if (i6 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                }
                ProvidableCompositionLocal<Density> localDensity12 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume12 = $composer2.consume(localDensity12);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density $this$SingleRowTopAppBar_u24lambda_u2412 = (Density) objConsume12;
                final float f13 = -$this$SingleRowTopAppBar_u24lambda_u2412.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                Object key2$iv12 = Float.valueOf(f13);
                int i110 = ($dirty >> 24) & 14;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv12);
                value$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                }
                value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        TopAppBarState state2;
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f13)) {
                            TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                            TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                            if (state3 == null) {
                                return;
                            }
                            state3.setHeightOffsetLimit(f13);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                if (scrollBehavior != null) {
                    overlappedFraction = 0.0f;
                } else {
                    overlappedFraction = 0.0f;
                }
                colorTransitionFraction = overlappedFraction;
                if (colorTransitionFraction > 0.01f) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                float fraction12 = f;
                State<Color> stateM68animateColorAsStateeuL9pac12 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction12, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                final Function2<? super Composer, ? super Integer, Unit> actionsRow12 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                        ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                            }
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                            int $changed$iv = (($dirty >> 6) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity13 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume13 = $composer3.consume(localDensity13);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume13;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume14 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume14;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume15 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume15;
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
                            int i111 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                $composer2.startReplaceableGroup(-1008376318);
                ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                if (scrollBehavior != null) {
                    companionDraggable = Modifier.INSTANCE;
                } else {
                    companionDraggable = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                Modifier appBarDragModifier12 = companionDraggable;
                modifier4 = modifier3;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier12), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac12), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                        TopAppBarState state2;
                        ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                        }
                        ProvidableCompositionLocal<Density> localDensity13 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume13 = $composer3.consume(localDensity13);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        Density $this$invoke_u24lambda_u240 = (Density) objConsume13;
                        float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                        Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                        long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                        long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                        long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                        Function2<Composer, Integer, Unit> function5 = function2;
                        TextStyle textStyle = titleTextStyle;
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                        Function2<Composer, Integer, Unit> function6 = function3;
                        Function2<Composer, Integer, Unit> function7 = actionsRow12;
                        int i111 = $dirty;
                        AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i111 << 12) & 458752) | 113246208 | ((i111 << 12) & 3670016), ((i111 >> 6) & 896) | 3126);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer2, 12582912, 122);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                if (i6 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
                }
                ProvidableCompositionLocal<Density> localDensity13 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume13 = $composer2.consume(localDensity13);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density $this$SingleRowTopAppBar_u24lambda_u2413 = (Density) objConsume13;
                final float f14 = -$this$SingleRowTopAppBar_u24lambda_u2413.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
                Object key2$iv13 = Float.valueOf(f14);
                int i111 = ($dirty >> 24) & 14;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv13);
                value$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                }
                value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        TopAppBarState state2;
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f14)) {
                            TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                            TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                            if (state3 == null) {
                                return;
                            }
                            state3.setHeightOffsetLimit(f14);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                if (scrollBehavior != null) {
                    overlappedFraction = 0.0f;
                } else {
                    overlappedFraction = 0.0f;
                }
                colorTransitionFraction = overlappedFraction;
                if (colorTransitionFraction > 0.01f) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                float fraction13 = f;
                State<Color> stateM68animateColorAsStateeuL9pac13 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction13, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
                final Function2<? super Composer, ? super Integer, Unit> actionsRow13 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                        ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                            }
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                            int $changed$iv = (($dirty >> 6) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity14 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume14 = $composer3.consume(localDensity14);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume14;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume15 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume15;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume16 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume16;
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
                            int i112 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                $composer2.startReplaceableGroup(-1008376318);
                ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
                if (scrollBehavior != null) {
                    companionDraggable = Modifier.INSTANCE;
                } else {
                    companionDraggable = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                Modifier appBarDragModifier13 = companionDraggable;
                modifier4 = modifier3;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier13), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac13), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                        TopAppBarState state2;
                        ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                        }
                        ProvidableCompositionLocal<Density> localDensity14 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume14 = $composer3.consume(localDensity14);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        Density $this$invoke_u24lambda_u240 = (Density) objConsume14;
                        float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                        Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                        long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                        long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                        long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                        Function2<Composer, Integer, Unit> function5 = function2;
                        TextStyle textStyle = titleTextStyle;
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                        Function2<Composer, Integer, Unit> function6 = function3;
                        Function2<Composer, Integer, Unit> function7 = actionsRow13;
                        int i112 = $dirty;
                        AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i112 << 12) & 458752) | 113246208 | ((i112 << 12) & 3670016), ((i112 >> 6) & 896) | 3126);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), $composer2, 12582912, 122);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier11 = modifier4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.3
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

                public final void invoke(Composer composer, int i112) {
                    AppBarKt.SingleRowTopAppBar(modifier11, function2, titleTextStyle, centeredTitle, function3, function4, windowInsets, colors, scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i5 = 100663296;
        $dirty2 |= i5;
        $dirty = $dirty2;
        if ((191739611 & $dirty) == 38347922) {
            if (i6 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
            }
            ProvidableCompositionLocal<Density> localDensity14 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume14 = $composer2.consume(localDensity14);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$SingleRowTopAppBar_u24lambda_u2414 = (Density) objConsume14;
            final float f15 = -$this$SingleRowTopAppBar_u24lambda_u2414.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
            Object key2$iv14 = Float.valueOf(f15);
            int i112 = ($dirty >> 24) & 14;
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv14);
            value$iv$iv = $composer2.rememberedValue();
            if (!invalid$iv$iv) {
            }
            value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    TopAppBarState state2;
                    TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                    if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f15)) {
                        TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                        TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                        if (state3 == null) {
                            return;
                        }
                        state3.setHeightOffsetLimit(f15);
                    }
                }
            };
            $composer2.updateRememberedValue(value$iv$iv);
            $composer2.endReplaceableGroup();
            EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
            if (scrollBehavior != null) {
                overlappedFraction = 0.0f;
            } else {
                overlappedFraction = 0.0f;
            }
            colorTransitionFraction = overlappedFraction;
            if (colorTransitionFraction > 0.01f) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            float fraction14 = f;
            State<Color> stateM68animateColorAsStateeuL9pac14 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction14, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
            final Function2<? super Composer, ? super Integer, Unit> actionsRow14 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                    ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                        }
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                        int $changed$iv = (($dirty >> 6) & 7168) | 432;
                        $composer3.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                        int $changed$iv$iv = ($changed$iv << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity15 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume15 = $composer3.consume(localDensity15);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Density density$iv$iv = (Density) objConsume15;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume16 = $composer3.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume16;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume17 = $composer3.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume17;
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
                        int i113 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                        function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            });
            $composer2.startReplaceableGroup(-1008376318);
            ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
            if (scrollBehavior != null) {
                companionDraggable = Modifier.INSTANCE;
            } else {
                companionDraggable = Modifier.INSTANCE;
            }
            $composer2.endReplaceableGroup();
            Modifier appBarDragModifier14 = companionDraggable;
            modifier4 = modifier3;
            SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier14), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac14), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                    TopAppBarState state2;
                    ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                    }
                    ProvidableCompositionLocal<Density> localDensity15 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume15 = $composer3.consume(localDensity15);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                    Density $this$invoke_u24lambda_u240 = (Density) objConsume15;
                    float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                    Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                    long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                    long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                    long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                    Function2<Composer, Integer, Unit> function5 = function2;
                    TextStyle textStyle = titleTextStyle;
                    Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                    Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                    Function2<Composer, Integer, Unit> function6 = function3;
                    Function2<Composer, Integer, Unit> function7 = actionsRow14;
                    int i113 = $dirty;
                    AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i113 << 12) & 458752) | 113246208 | ((i113 << 12) & 3670016), ((i113 >> 6) & 896) | 3126);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, 12582912, 122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            if (i6 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1841601619, $dirty, -1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:1021)");
            }
            ProvidableCompositionLocal<Density> localDensity15 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume15 = $composer2.consume(localDensity15);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$SingleRowTopAppBar_u24lambda_u2415 = (Density) objConsume15;
            final float f16 = -$this$SingleRowTopAppBar_u24lambda_u2415.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM());
            Object key2$iv15 = Float.valueOf(f16);
            int i113 = ($dirty >> 24) & 14;
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv15);
            value$iv$iv = $composer2.rememberedValue();
            if (!invalid$iv$iv) {
            }
            value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    TopAppBarState state2;
                    TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                    if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), f16)) {
                        TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                        TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                        if (state3 == null) {
                            return;
                        }
                        state3.setHeightOffsetLimit(f16);
                    }
                }
            };
            $composer2.updateRememberedValue(value$iv$iv);
            $composer2.endReplaceableGroup();
            EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
            if (scrollBehavior != null) {
                overlappedFraction = 0.0f;
            } else {
                overlappedFraction = 0.0f;
            }
            colorTransitionFraction = overlappedFraction;
            if (colorTransitionFraction > 0.01f) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            float fraction15 = f;
            State<Color> stateM68animateColorAsStateeuL9pac15 = SingleValueAnimationKt.m68animateColorAsStateeuL9pac(colors.m2000containerColorXeAY9LY$material3_release(fraction15, $composer2, ($dirty >> 18) & 112), AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), null, null, $composer2, 48, 12);
            final Function2<? super Composer, ? super Integer, Unit> actionsRow15 = ComposableLambdaKt.composableLambda($composer2, 1520880938, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$actionsRow$1
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
                    ComposerKt.sourceInformation($composer3, "C1055@48284L157:AppBar.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1520880938, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1054)");
                        }
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                        int $changed$iv = (($dirty >> 6) & 7168) | 432;
                        $composer3.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                        int $changed$iv$iv = ($changed$iv << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity16 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume16 = $composer3.consume(localDensity16);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Density density$iv$iv = (Density) objConsume16;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume17 = $composer3.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume17;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume18 = $composer3.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume18;
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
                        int i114 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                        function5.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            });
            $composer2.startReplaceableGroup(-1008376318);
            ComposerKt.sourceInformation($composer2, "1066@48747L118,1066@48724L141,1069@48895L255");
            if (scrollBehavior != null) {
                companionDraggable = Modifier.INSTANCE;
            } else {
                companionDraggable = Modifier.INSTANCE;
            }
            $composer2.endReplaceableGroup();
            Modifier appBarDragModifier15 = companionDraggable;
            modifier4 = modifier3;
            SurfaceKt.m1806SurfaceT9BRK9s(modifier3.then(appBarDragModifier15), null, SingleRowTopAppBar$lambda$3(stateM68animateColorAsStateeuL9pac15), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 376925230, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.2
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
                    TopAppBarState state2;
                    ComposerKt.sourceInformation($composer3, "C*1087@49625L7,1091@49775L890:AppBar.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(376925230, $changed2, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1086)");
                    }
                    ProvidableCompositionLocal<Density> localDensity16 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume16 = $composer3.consume(localDensity16);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                    Density $this$invoke_u24lambda_u240 = (Density) objConsume16;
                    float height = $this$invoke_u24lambda_u240.mo327toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2520getContainerHeightD9Ej5fM()) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset());
                    Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets));
                    long jM2002getNavigationIconContentColor0d7_KjU$material3_release = colors.getNavigationIconContentColor();
                    long jM2003getTitleContentColor0d7_KjU$material3_release = colors.getTitleContentColor();
                    long jM2001getActionIconContentColor0d7_KjU$material3_release = colors.getActionIconContentColor();
                    Function2<Composer, Integer, Unit> function5 = function2;
                    TextStyle textStyle = titleTextStyle;
                    Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                    Arrangement.Horizontal center2 = centeredTitle ? Arrangement.INSTANCE.getCenter() : Arrangement.INSTANCE.getStart();
                    Function2<Composer, Integer, Unit> function6 = function3;
                    Function2<Composer, Integer, Unit> function7 = actionsRow15;
                    int i114 = $dirty;
                    AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifierClipToBounds, height, jM2002getNavigationIconContentColor0d7_KjU$material3_release, jM2003getTitleContentColor0d7_KjU$material3_release, jM2001getActionIconContentColor0d7_KjU$material3_release, function5, textStyle, 1.0f, center, center2, 0, false, function6, function7, $composer3, ((i114 << 12) & 458752) | 113246208 | ((i114 << 12) & 3670016), ((i114 >> 6) & 896) | 3126);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, 12582912, 122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier12 = modifier4;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt.SingleRowTopAppBar.3
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
                AppBarKt.SingleRowTopAppBar(modifier12, function2, titleTextStyle, centeredTitle, function3, function4, windowInsets, colors, scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    private static final long SingleRowTopAppBar$lambda$3(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m2981unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:101:0x0145  */
    /* JADX WARN: Code duplicated, block: B:103:0x0149  */
    /* JADX WARN: Code duplicated, block: B:105:0x014e  */
    /* JADX WARN: Code duplicated, block: B:107:0x0154  */
    /* JADX WARN: Code duplicated, block: B:108:0x0157  */
    /* JADX WARN: Code duplicated, block: B:111:0x015e  */
    /* JADX WARN: Code duplicated, block: B:112:0x0161  */
    /* JADX WARN: Code duplicated, block: B:114:0x0165  */
    /* JADX WARN: Code duplicated, block: B:116:0x016b  */
    /* JADX WARN: Code duplicated, block: B:117:0x016e  */
    /* JADX WARN: Code duplicated, block: B:121:0x0176  */
    /* JADX WARN: Code duplicated, block: B:122:0x0179  */
    /* JADX WARN: Code duplicated, block: B:124:0x017d  */
    /* JADX WARN: Code duplicated, block: B:127:0x0184  */
    /* JADX WARN: Code duplicated, block: B:131:0x018c  */
    /* JADX WARN: Code duplicated, block: B:132:0x018f  */
    /* JADX WARN: Code duplicated, block: B:134:0x0193  */
    /* JADX WARN: Code duplicated, block: B:137:0x019a  */
    /* JADX WARN: Code duplicated, block: B:141:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:147:0x01bf A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:148:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:149:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:152:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:155:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:157:0x025f  */
    /* JADX WARN: Code duplicated, block: B:160:0x026a  */
    /* JADX WARN: Code duplicated, block: B:161:0x026f  */
    /* JADX WARN: Code duplicated, block: B:165:0x028f  */
    /* JADX WARN: Code duplicated, block: B:168:0x029a  */
    /* JADX WARN: Code duplicated, block: B:171:0x02ca  */
    /* JADX WARN: Code duplicated, block: B:172:0x02cd  */
    /* JADX WARN: Code duplicated, block: B:175:0x02d3  */
    /* JADX WARN: Code duplicated, block: B:176:0x02d6  */
    /* JADX WARN: Code duplicated, block: B:179:0x02e5  */
    /* JADX WARN: Code duplicated, block: B:197:0x03a3  */
    /* JADX WARN: Code duplicated, block: B:200:0x0413  */
    /* JADX WARN: Code duplicated, block: B:203:0x041c  */
    /* JADX WARN: Code duplicated, block: B:204:0x0423  */
    /* JADX WARN: Code duplicated, block: B:206:0x045c  */
    /* JADX WARN: Code duplicated, block: B:90:0x0124  */
    /* JADX WARN: Code duplicated, block: B:91:0x012a  */
    /* JADX WARN: Code duplicated, block: B:93:0x012f  */
    /* JADX WARN: Code duplicated, block: B:95:0x0137  */
    /* JADX WARN: Code duplicated, block: B:96:0x013a  */
    /* JADX WARN: Code duplicated, block: B:98:0x013f  */
    /* JADX INFO: renamed from: TwoRowsTopAppBar-tjU4iQQ, reason: not valid java name */
    public static final void m1312TwoRowsTopAppBartjU4iQQ(Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle titleTextStyle, final float titleBottomPadding, final Function2<? super Composer, ? super Integer, Unit> function3, final TextStyle smallTitleTextStyle, final Function2<? super Composer, ? super Integer, Unit> function4, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function5, final WindowInsets windowInsets, final TopAppBarColors colors, final float maxHeight, final float pinnedHeight, final TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        Modifier.Companion modifier2;
        boolean invalid$iv$iv;
        Object value$iv$iv;
        float colorTransitionFraction;
        boolean z;
        final boolean hideTopRowSemantics;
        final boolean hideBottomRowSemantics;
        Modifier.Companion appBarDragModifier;
        Modifier modifier3;
        int $dirty;
        TopAppBarState state;
        Object key3$iv;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer $composer2 = $composer.startRestartGroup(210227552);
        ComposerKt.sourceInformation($composer2, "C(TwoRowsTopAppBar)P(3,9,11,10:c#ui.unit.Dp,7,8,4!1,12!1,2:c#ui.unit.Dp,5:c#ui.unit.Dp)*1146@51718L7,1154@52031L189,1154@52020L200,1166@52751L39,1166@52723L68,1203@54215L2301:AppBar.kt#uh7d8r");
        final int $dirty2 = $changed;
        int $dirty1 = $changed1;
        int i6 = i & 1;
        if (i6 != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer2.changed(titleTextStyle) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer2.changed(titleBottomPadding) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if ((57344 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty2 |= $composer2.changed(smallTitleTextStyle) ? 131072 : 65536;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty2 |= $composer2.changedInstance(function4) ? 1048576 : 524288;
        }
        if ((i & 128) == 0) {
            if ((29360128 & $changed) == 0) {
                i2 = $composer2.changedInstance(function5) ? 8388608 : 4194304;
            }
            if ((i & 256) != 0) {
                $dirty2 |= 100663296;
            } else if ((234881024 & $changed) != 0) {
                if ($composer2.changed(windowInsets)) {
                    i3 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i3 = 33554432;
                }
                $dirty2 |= i3;
            }
            if ((i & 512) != 0) {
                if ((1879048192 & $changed) == 0) {
                    if ($composer2.changed(colors)) {
                        i4 = 536870912;
                    } else {
                        i4 = 268435456;
                    }
                }
                if ((i & 1024) != 0) {
                    $dirty1 |= 6;
                } else if (($changed1 & 14) == 0) {
                    if ($composer2.changed(maxHeight)) {
                        i5 = 4;
                    } else {
                        i5 = 2;
                    }
                    $dirty1 |= i5;
                }
                if ((i & 2048) != 0) {
                    $dirty1 |= 48;
                } else if (($changed1 & 112) == 0) {
                    $dirty1 |= $composer2.changed(pinnedHeight) ? 32 : 16;
                }
                if ((i & 4096) != 0) {
                    $dirty1 |= 384;
                } else if (($changed1 & 896) == 0) {
                    $dirty1 |= $composer2.changed(scrollBehavior) ? 256 : 128;
                }
                if ((1533916891 & $dirty2) != 306783378 && ($dirty1 & 731) == 146 && $composer2.getSkipping()) {
                    $composer2.skipToGroupEnd();
                    modifier3 = modifier;
                    $dirty = $dirty2;
                    $dirty1 = $dirty1;
                } else {
                    if (i6 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(210227552, $dirty2, $dirty1, "androidx.compose.material3.TwoRowsTopAppBar (AppBar.kt:1123)");
                    }
                    if (Dp.m5273compareTo0680j_4(maxHeight, pinnedHeight) <= 0) {
                        throw new IllegalArgumentException("A TwoRowsTopAppBar max height should be greater than its pinned height");
                    }
                    final Ref.FloatRef pinnedHeightPx = new Ref.FloatRef();
                    final Ref.FloatRef maxHeightPx = new Ref.FloatRef();
                    final Ref.IntRef titleBottomPaddingPx = new Ref.IntRef();
                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer2.consume(localDensity);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Density $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u246 = (Density) objConsume;
                    pinnedHeightPx.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u246.mo327toPx0680j_4(pinnedHeight);
                    maxHeightPx.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u246.mo327toPx0680j_4(maxHeight);
                    titleBottomPaddingPx.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u246.mo321roundToPx0680j_4(titleBottomPadding);
                    Object key2$iv = Float.valueOf(pinnedHeightPx.element);
                    Object key3$iv2 = Float.valueOf(maxHeightPx.element);
                    int i7 = ($dirty1 >> 6) & 14;
                    $composer2.startReplaceableGroup(1618982084);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv) | $composer2.changed(key3$iv2);
                    value$iv$iv = $composer2.rememberedValue();
                    if (!invalid$iv$iv) {
                        key3$iv = Composer.INSTANCE.getEmpty();
                        if (value$iv$iv == key3$iv) {
                        }
                        $composer2.endReplaceableGroup();
                        EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                        if (scrollBehavior != null || (state = scrollBehavior.getState()) == null) {
                            colorTransitionFraction = 0.0f;
                        } else {
                            colorTransitionFraction = state.getCollapsedFraction();
                        }
                        State appBarContainerColor$delegate = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
                        final Function2 actionsRow = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                                ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                                if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                                    }
                                    Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                    Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                                    int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                                    $composer3.startReplaceableGroup(693286680);
                                    ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                    Modifier modifier$iv = Modifier.INSTANCE;
                                    MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                    int $changed$iv$iv = ($changed$iv << 3) & 112;
                                    $composer3.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume2 = $composer3.consume(localDensity2);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    Density density$iv$iv = (Density) objConsume2;
                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume3 = $composer3.consume(localLayoutDirection);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume3;
                                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume4 = $composer3.consume(localViewConfiguration);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume4;
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
                                    ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                    function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer3.endReplaceableGroup();
                                    $composer3.endNode();
                                    $composer3.endReplaceableGroup();
                                    $composer3.endReplaceableGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer3.skipToGroupEnd();
                            }
                        });
                        final float topTitleAlpha = TopTitleAlphaEasing.transform(colorTransitionFraction);
                        final float bottomTitleAlpha = 1.0f - colorTransitionFraction;
                        if (colorTransitionFraction < 0.5f) {
                            z = true;
                        } else {
                            z = false;
                        }
                        hideTopRowSemantics = z;
                        if (hideTopRowSemantics) {
                            hideBottomRowSemantics = false;
                        } else {
                            hideBottomRowSemantics = true;
                        }
                        $composer2.startReplaceableGroup(-1609665814);
                        ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
                        if (scrollBehavior != null || scrollBehavior.getIsPinned()) {
                            appBarDragModifier = Modifier.INSTANCE;
                        } else {
                            Modifier.Companion companion = Modifier.INSTANCE;
                            Orientation orientation = Orientation.Vertical;
                            int i8 = ($dirty1 >> 6) & 14;
                            $composer2.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv$iv2 = $composer2.changed(scrollBehavior);
                            Object value$iv$iv2 = $composer2.rememberedValue();
                            if (invalid$iv$iv2 || value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv2 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$appBarDragModifier$1$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                                        invoke(f.floatValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(float delta) {
                                        scrollBehavior.getState().setHeightOffset(scrollBehavior.getState().getHeightOffset() + delta);
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv$iv2);
                            }
                            $composer2.endReplaceableGroup();
                            DraggableState draggableStateRememberDraggableState = DraggableKt.rememberDraggableState((Function1) value$iv$iv2, $composer2, 0);
                            Modifier.Companion companion2 = companion;
                            int i9 = ($dirty1 >> 6) & 14;
                            $composer2.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv$iv3 = $composer2.changed(scrollBehavior);
                            AppBarKt$TwoRowsTopAppBar$appBarDragModifier$2$1 value$iv$iv3 = $composer2.rememberedValue();
                            if (invalid$iv$iv3 || value$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv3 = new AppBarKt$TwoRowsTopAppBar$appBarDragModifier$2$1(scrollBehavior, null);
                                $composer2.updateRememberedValue(value$iv$iv3);
                            }
                            $composer2.endReplaceableGroup();
                            appBarDragModifier = DraggableKt.draggable(companion2, draggableStateRememberDraggableState, orientation, (188 & 4) != 0, (188 & 8) != 0 ? null : null, (188 & 16) != 0 ? false : false, (188 & 32) != 0 ? new DraggableKt.C01841(null) : null, (188 & 64) != 0 ? new DraggableKt.AnonymousClass2(null) : (Function3) value$iv$iv3, (188 & 128) != 0 ? false : false);
                        }
                        $composer2.endReplaceableGroup();
                        final int i10 = $dirty2;
                        modifier3 = modifier2;
                        $dirty = $dirty2;
                        SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                                TopAppBarState state2;
                                ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                                if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                                    }
                                    WindowInsets windowInsets2 = windowInsets;
                                    Ref.FloatRef floatRef = pinnedHeightPx;
                                    TopAppBarColors topAppBarColors = colors;
                                    Function2<Composer, Integer, Unit> function6 = function3;
                                    TextStyle textStyle = smallTitleTextStyle;
                                    float f = topTitleAlpha;
                                    boolean z2 = hideTopRowSemantics;
                                    Function2<Composer, Integer, Unit> function7 = function4;
                                    Function2<Composer, Integer, Unit> function8 = actionsRow;
                                    int i11 = i10;
                                    Ref.FloatRef floatRef2 = maxHeightPx;
                                    TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                                    Function2<Composer, Integer, Unit> function9 = function2;
                                    TextStyle textStyle2 = titleTextStyle;
                                    float f2 = bottomTitleAlpha;
                                    Ref.IntRef intRef = titleBottomPaddingPx;
                                    boolean z3 = hideBottomRowSemantics;
                                    $composer3.startReplaceableGroup(-483455358);
                                    ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                    Modifier modifier$iv = Modifier.INSTANCE;
                                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                    int $changed$iv$iv = (0 << 3) & 112;
                                    $composer3.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume2 = $composer3.consume(localDensity2);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    Density density$iv$iv = (Density) objConsume2;
                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume3 = $composer3.consume(localLayoutDirection);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume3;
                                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume4 = $composer3.consume(localViewConfiguration);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume4;
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
                                    int i12 = ($changed$iv$iv$iv >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                    int i13 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                                    int i14 = i11 << 3;
                                    AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i14 & 458752) | 905969664 | (i14 & 3670016), ((i11 >> 12) & 896) | 3078);
                                    int i15 = i11 << 12;
                                    AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i15 & 458752) | 905969664 | (i15 & 3670016), 3456);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer3.endReplaceableGroup();
                                    $composer3.endNode();
                                    $composer3.endReplaceableGroup();
                                    $composer3.endReplaceableGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer3.skipToGroupEnd();
                            }
                        }), $composer2, 12582912, 122);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            TopAppBarState state2;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), pinnedHeightPx.element - maxHeightPx.element)) {
                                TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                                TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                                if (state3 == null) {
                                    return;
                                }
                                state3.setHeightOffsetLimit(pinnedHeightPx.element - maxHeightPx.element);
                            }
                        }
                    };
                    $composer2.updateRememberedValue(value$iv$iv);
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                    if (scrollBehavior != null) {
                        colorTransitionFraction = 0.0f;
                    } else {
                        colorTransitionFraction = 0.0f;
                    }
                    State appBarContainerColor$delegate2 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
                    final Function2<? super Composer, ? super Integer, Unit> actionsRow2 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                            ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                                }
                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                                int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume2 = $composer3.consume(localDensity2);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume2;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume3 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume3;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume4 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume4;
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
                                int i11 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    });
                    final float topTitleAlpha2 = TopTitleAlphaEasing.transform(colorTransitionFraction);
                    final float bottomTitleAlpha2 = 1.0f - colorTransitionFraction;
                    if (colorTransitionFraction < 0.5f) {
                        z = true;
                    } else {
                        z = false;
                    }
                    hideTopRowSemantics = z;
                    if (hideTopRowSemantics) {
                        hideBottomRowSemantics = true;
                    } else {
                        hideBottomRowSemantics = false;
                    }
                    $composer2.startReplaceableGroup(-1609665814);
                    ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
                    if (scrollBehavior != null) {
                        appBarDragModifier = Modifier.INSTANCE;
                    } else {
                        appBarDragModifier = Modifier.INSTANCE;
                    }
                    $composer2.endReplaceableGroup();
                    final int i11 = $dirty2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate2), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                            TopAppBarState state2;
                            ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                                }
                                WindowInsets windowInsets2 = windowInsets;
                                Ref.FloatRef floatRef = pinnedHeightPx;
                                TopAppBarColors topAppBarColors = colors;
                                Function2<Composer, Integer, Unit> function6 = function3;
                                TextStyle textStyle = smallTitleTextStyle;
                                float f = topTitleAlpha2;
                                boolean z2 = hideTopRowSemantics;
                                Function2<Composer, Integer, Unit> function7 = function4;
                                Function2<Composer, Integer, Unit> function8 = actionsRow2;
                                int i12 = i11;
                                Ref.FloatRef floatRef2 = maxHeightPx;
                                TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                                Function2<Composer, Integer, Unit> function9 = function2;
                                TextStyle textStyle2 = titleTextStyle;
                                float f2 = bottomTitleAlpha2;
                                Ref.IntRef intRef = titleBottomPaddingPx;
                                boolean z3 = hideBottomRowSemantics;
                                $composer3.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                int $changed$iv$iv = (0 << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume2 = $composer3.consume(localDensity2);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume2;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume3 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume3;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume4 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume4;
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
                                int i13 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                int i14 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                                int i15 = i12 << 3;
                                AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i15 & 458752) | 905969664 | (i15 & 3670016), ((i12 >> 12) & 896) | 3078);
                                int i16 = i12 << 12;
                                AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i16 & 458752) | 905969664 | (i16 & 3670016), 3456);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    }), $composer2, 12582912, 122);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup == null) {
                    return;
                }
                final Modifier modifier4 = modifier3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$4
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
                        AppBarKt.m1312TwoRowsTopAppBartjU4iQQ(modifier4, function2, titleTextStyle, titleBottomPadding, function3, smallTitleTextStyle, function4, function5, windowInsets, colors, maxHeight, pinnedHeight, scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                    }
                });
            }
            i4 = 805306368;
            $dirty2 |= i4;
            if ((i & 1024) != 0) {
                $dirty1 |= 6;
            } else if (($changed1 & 14) == 0) {
                if ($composer2.changed(maxHeight)) {
                    i5 = 4;
                } else {
                    i5 = 2;
                }
                $dirty1 |= i5;
            }
            if ((i & 2048) != 0) {
                $dirty1 |= 48;
            } else if (($changed1 & 112) == 0) {
                $dirty1 |= $composer2.changed(pinnedHeight) ? 32 : 16;
            }
            if ((i & 4096) != 0) {
                $dirty1 |= 384;
            } else if (($changed1 & 896) == 0) {
                $dirty1 |= $composer2.changed(scrollBehavior) ? 256 : 128;
            }
            if ((1533916891 & $dirty2) != 306783378) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(210227552, $dirty2, $dirty1, "androidx.compose.material3.TwoRowsTopAppBar (AppBar.kt:1123)");
                }
                if (Dp.m5273compareTo0680j_4(maxHeight, pinnedHeight) <= 0) {
                    throw new IllegalArgumentException("A TwoRowsTopAppBar max height should be greater than its pinned height");
                }
                final Ref.FloatRef pinnedHeightPx2 = new Ref.FloatRef();
                final Ref.FloatRef maxHeightPx2 = new Ref.FloatRef();
                final Ref.IntRef titleBottomPaddingPx2 = new Ref.IntRef();
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u247 = (Density) objConsume2;
                pinnedHeightPx2.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u247.mo327toPx0680j_4(pinnedHeight);
                maxHeightPx2.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u247.mo327toPx0680j_4(maxHeight);
                titleBottomPaddingPx2.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u247.mo321roundToPx0680j_4(titleBottomPadding);
                Object key2$iv2 = Float.valueOf(pinnedHeightPx2.element);
                Object key3$iv3 = Float.valueOf(maxHeightPx2.element);
                int i12 = ($dirty1 >> 6) & 14;
                $composer2.startReplaceableGroup(1618982084);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv2) | $composer2.changed(key3$iv3);
                value$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                    key3$iv = Composer.INSTANCE.getEmpty();
                    if (value$iv$iv == key3$iv) {
                    }
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                    if (scrollBehavior != null) {
                        colorTransitionFraction = 0.0f;
                    } else {
                        colorTransitionFraction = 0.0f;
                    }
                    State appBarContainerColor$delegate3 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
                    final Function2<? super Composer, ? super Integer, Unit> actionsRow3 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                            ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                                }
                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                                int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume3 = $composer3.consume(localDensity3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume3;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume4 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume5 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
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
                                int i13 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    });
                    final float topTitleAlpha3 = TopTitleAlphaEasing.transform(colorTransitionFraction);
                    final float bottomTitleAlpha3 = 1.0f - colorTransitionFraction;
                    if (colorTransitionFraction < 0.5f) {
                        z = true;
                    } else {
                        z = false;
                    }
                    hideTopRowSemantics = z;
                    if (hideTopRowSemantics) {
                        hideBottomRowSemantics = true;
                    } else {
                        hideBottomRowSemantics = false;
                    }
                    $composer2.startReplaceableGroup(-1609665814);
                    ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
                    if (scrollBehavior != null) {
                        appBarDragModifier = Modifier.INSTANCE;
                    } else {
                        appBarDragModifier = Modifier.INSTANCE;
                    }
                    $composer2.endReplaceableGroup();
                    final int i13 = $dirty2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate3), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                            TopAppBarState state2;
                            ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                                }
                                WindowInsets windowInsets2 = windowInsets;
                                Ref.FloatRef floatRef = pinnedHeightPx2;
                                TopAppBarColors topAppBarColors = colors;
                                Function2<Composer, Integer, Unit> function6 = function3;
                                TextStyle textStyle = smallTitleTextStyle;
                                float f = topTitleAlpha3;
                                boolean z2 = hideTopRowSemantics;
                                Function2<Composer, Integer, Unit> function7 = function4;
                                Function2<Composer, Integer, Unit> function8 = actionsRow3;
                                int i14 = i13;
                                Ref.FloatRef floatRef2 = maxHeightPx2;
                                TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                                Function2<Composer, Integer, Unit> function9 = function2;
                                TextStyle textStyle2 = titleTextStyle;
                                float f2 = bottomTitleAlpha3;
                                Ref.IntRef intRef = titleBottomPaddingPx2;
                                boolean z3 = hideBottomRowSemantics;
                                $composer3.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                int $changed$iv$iv = (0 << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume3 = $composer3.consume(localDensity3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume3;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume4 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume5 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
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
                                int i15 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                int i16 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                                int i17 = i14 << 3;
                                AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i17 & 458752) | 905969664 | (i17 & 3670016), ((i14 >> 12) & 896) | 3078);
                                int i18 = i14 << 12;
                                AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i18 & 458752) | 905969664 | (i18 & 3670016), 3456);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    }), $composer2, 12582912, 122);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        TopAppBarState state2;
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), pinnedHeightPx2.element - maxHeightPx2.element)) {
                            TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                            TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                            if (state3 == null) {
                                return;
                            }
                            state3.setHeightOffsetLimit(pinnedHeightPx2.element - maxHeightPx2.element);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                if (scrollBehavior != null) {
                    colorTransitionFraction = 0.0f;
                } else {
                    colorTransitionFraction = 0.0f;
                }
                State appBarContainerColor$delegate4 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
                final Function2<? super Composer, ? super Integer, Unit> actionsRow4 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                        ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                            }
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                            int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer3.consume(localDensity3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume3;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume4 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume5 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
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
                            int i14 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                final float topTitleAlpha4 = TopTitleAlphaEasing.transform(colorTransitionFraction);
                final float bottomTitleAlpha4 = 1.0f - colorTransitionFraction;
                if (colorTransitionFraction < 0.5f) {
                    z = true;
                } else {
                    z = false;
                }
                hideTopRowSemantics = z;
                if (hideTopRowSemantics) {
                    hideBottomRowSemantics = true;
                } else {
                    hideBottomRowSemantics = false;
                }
                $composer2.startReplaceableGroup(-1609665814);
                ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
                if (scrollBehavior != null) {
                    appBarDragModifier = Modifier.INSTANCE;
                } else {
                    appBarDragModifier = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                final int i14 = $dirty2;
                modifier3 = modifier2;
                $dirty = $dirty2;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate4), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                        TopAppBarState state2;
                        ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                            }
                            WindowInsets windowInsets2 = windowInsets;
                            Ref.FloatRef floatRef = pinnedHeightPx2;
                            TopAppBarColors topAppBarColors = colors;
                            Function2<Composer, Integer, Unit> function6 = function3;
                            TextStyle textStyle = smallTitleTextStyle;
                            float f = topTitleAlpha4;
                            boolean z2 = hideTopRowSemantics;
                            Function2<Composer, Integer, Unit> function7 = function4;
                            Function2<Composer, Integer, Unit> function8 = actionsRow4;
                            int i15 = i14;
                            Ref.FloatRef floatRef2 = maxHeightPx2;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            Function2<Composer, Integer, Unit> function9 = function2;
                            TextStyle textStyle2 = titleTextStyle;
                            float f2 = bottomTitleAlpha4;
                            Ref.IntRef intRef = titleBottomPaddingPx2;
                            boolean z3 = hideBottomRowSemantics;
                            $composer3.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer3.consume(localDensity3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume3;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume4 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume4;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume5 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume5;
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
                            int i16 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                            int i17 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                            int i18 = i15 << 3;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i18 & 458752) | 905969664 | (i18 & 3670016), ((i15 >> 12) & 896) | 3078);
                            int i19 = i15 << 12;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i19 & 458752) | 905969664 | (i19 & 3670016), 3456);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, 12582912, 122);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(210227552, $dirty2, $dirty1, "androidx.compose.material3.TwoRowsTopAppBar (AppBar.kt:1123)");
                }
                if (Dp.m5273compareTo0680j_4(maxHeight, pinnedHeight) <= 0) {
                    throw new IllegalArgumentException("A TwoRowsTopAppBar max height should be greater than its pinned height");
                }
                final Ref.FloatRef pinnedHeightPx3 = new Ref.FloatRef();
                final Ref.FloatRef maxHeightPx3 = new Ref.FloatRef();
                final Ref.IntRef titleBottomPaddingPx3 = new Ref.IntRef();
                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer2.consume(localDensity3);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u248 = (Density) objConsume3;
                pinnedHeightPx3.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u248.mo327toPx0680j_4(pinnedHeight);
                maxHeightPx3.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u248.mo327toPx0680j_4(maxHeight);
                titleBottomPaddingPx3.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u248.mo321roundToPx0680j_4(titleBottomPadding);
                Object key2$iv3 = Float.valueOf(pinnedHeightPx3.element);
                Object key3$iv4 = Float.valueOf(maxHeightPx3.element);
                int i15 = ($dirty1 >> 6) & 14;
                $composer2.startReplaceableGroup(1618982084);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv3) | $composer2.changed(key3$iv4);
                value$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                    key3$iv = Composer.INSTANCE.getEmpty();
                    if (value$iv$iv == key3$iv) {
                    }
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                    if (scrollBehavior != null) {
                        colorTransitionFraction = 0.0f;
                    } else {
                        colorTransitionFraction = 0.0f;
                    }
                    State appBarContainerColor$delegate5 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
                    final Function2<? super Composer, ? super Integer, Unit> actionsRow5 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                            ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                                }
                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                                int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume4 = $composer3.consume(localDensity4);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume4;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume5 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume5;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume6;
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
                                int i16 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    });
                    final float topTitleAlpha5 = TopTitleAlphaEasing.transform(colorTransitionFraction);
                    final float bottomTitleAlpha5 = 1.0f - colorTransitionFraction;
                    if (colorTransitionFraction < 0.5f) {
                        z = true;
                    } else {
                        z = false;
                    }
                    hideTopRowSemantics = z;
                    if (hideTopRowSemantics) {
                        hideBottomRowSemantics = true;
                    } else {
                        hideBottomRowSemantics = false;
                    }
                    $composer2.startReplaceableGroup(-1609665814);
                    ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
                    if (scrollBehavior != null) {
                        appBarDragModifier = Modifier.INSTANCE;
                    } else {
                        appBarDragModifier = Modifier.INSTANCE;
                    }
                    $composer2.endReplaceableGroup();
                    final int i16 = $dirty2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate5), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                            TopAppBarState state2;
                            ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                                }
                                WindowInsets windowInsets2 = windowInsets;
                                Ref.FloatRef floatRef = pinnedHeightPx3;
                                TopAppBarColors topAppBarColors = colors;
                                Function2<Composer, Integer, Unit> function6 = function3;
                                TextStyle textStyle = smallTitleTextStyle;
                                float f = topTitleAlpha5;
                                boolean z2 = hideTopRowSemantics;
                                Function2<Composer, Integer, Unit> function7 = function4;
                                Function2<Composer, Integer, Unit> function8 = actionsRow5;
                                int i17 = i16;
                                Ref.FloatRef floatRef2 = maxHeightPx3;
                                TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                                Function2<Composer, Integer, Unit> function9 = function2;
                                TextStyle textStyle2 = titleTextStyle;
                                float f2 = bottomTitleAlpha5;
                                Ref.IntRef intRef = titleBottomPaddingPx3;
                                boolean z3 = hideBottomRowSemantics;
                                $composer3.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                int $changed$iv$iv = (0 << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume4 = $composer3.consume(localDensity4);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume4;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume5 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume5;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume6;
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
                                int i18 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                int i19 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                                int i110 = i17 << 3;
                                AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i110 & 458752) | 905969664 | (i110 & 3670016), ((i17 >> 12) & 896) | 3078);
                                int i111 = i17 << 12;
                                AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i111 & 458752) | 905969664 | (i111 & 3670016), 3456);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    }), $composer2, 12582912, 122);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        TopAppBarState state2;
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), pinnedHeightPx3.element - maxHeightPx3.element)) {
                            TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                            TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                            if (state3 == null) {
                                return;
                            }
                            state3.setHeightOffsetLimit(pinnedHeightPx3.element - maxHeightPx3.element);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                if (scrollBehavior != null) {
                    colorTransitionFraction = 0.0f;
                } else {
                    colorTransitionFraction = 0.0f;
                }
                State appBarContainerColor$delegate6 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
                final Function2<? super Composer, ? super Integer, Unit> actionsRow6 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                        ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                            }
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                            int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume4 = $composer3.consume(localDensity4);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume4;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume5 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume5;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume6 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume6;
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
                            int i17 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                final float topTitleAlpha6 = TopTitleAlphaEasing.transform(colorTransitionFraction);
                final float bottomTitleAlpha6 = 1.0f - colorTransitionFraction;
                if (colorTransitionFraction < 0.5f) {
                    z = true;
                } else {
                    z = false;
                }
                hideTopRowSemantics = z;
                if (hideTopRowSemantics) {
                    hideBottomRowSemantics = true;
                } else {
                    hideBottomRowSemantics = false;
                }
                $composer2.startReplaceableGroup(-1609665814);
                ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
                if (scrollBehavior != null) {
                    appBarDragModifier = Modifier.INSTANCE;
                } else {
                    appBarDragModifier = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                final int i17 = $dirty2;
                modifier3 = modifier2;
                $dirty = $dirty2;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate6), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                        TopAppBarState state2;
                        ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                            }
                            WindowInsets windowInsets2 = windowInsets;
                            Ref.FloatRef floatRef = pinnedHeightPx3;
                            TopAppBarColors topAppBarColors = colors;
                            Function2<Composer, Integer, Unit> function6 = function3;
                            TextStyle textStyle = smallTitleTextStyle;
                            float f = topTitleAlpha6;
                            boolean z2 = hideTopRowSemantics;
                            Function2<Composer, Integer, Unit> function7 = function4;
                            Function2<Composer, Integer, Unit> function8 = actionsRow6;
                            int i18 = i17;
                            Ref.FloatRef floatRef2 = maxHeightPx3;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            Function2<Composer, Integer, Unit> function9 = function2;
                            TextStyle textStyle2 = titleTextStyle;
                            float f2 = bottomTitleAlpha6;
                            Ref.IntRef intRef = titleBottomPaddingPx3;
                            boolean z3 = hideBottomRowSemantics;
                            $composer3.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume4 = $composer3.consume(localDensity4);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume4;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume5 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume5;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume6 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume6;
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
                            int i19 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                            int i110 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                            int i111 = i18 << 3;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i111 & 458752) | 905969664 | (i111 & 3670016), ((i18 >> 12) & 896) | 3078);
                            int i112 = i18 << 12;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i112 & 458752) | 905969664 | (i112 & 3670016), 3456);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, 12582912, 122);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$4
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
                    AppBarKt.m1312TwoRowsTopAppBartjU4iQQ(modifier5, function2, titleTextStyle, titleBottomPadding, function3, smallTitleTextStyle, function4, function5, windowInsets, colors, maxHeight, pinnedHeight, scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i2 = 12582912;
        $dirty2 |= i2;
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if ((234881024 & $changed) != 0) {
            if ($composer2.changed(windowInsets)) {
                i3 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i3 = 33554432;
            }
            $dirty2 |= i3;
        }
        if ((i & 512) != 0) {
            if ((1879048192 & $changed) == 0) {
                if ($composer2.changed(colors)) {
                    i4 = 536870912;
                } else {
                    i4 = 268435456;
                }
            }
            if ((i & 1024) != 0) {
                $dirty1 |= 6;
            } else if (($changed1 & 14) == 0) {
                if ($composer2.changed(maxHeight)) {
                    i5 = 4;
                } else {
                    i5 = 2;
                }
                $dirty1 |= i5;
            }
            if ((i & 2048) != 0) {
                $dirty1 |= 48;
            } else if (($changed1 & 112) == 0) {
                $dirty1 |= $composer2.changed(pinnedHeight) ? 32 : 16;
            }
            if ((i & 4096) != 0) {
                $dirty1 |= 384;
            } else if (($changed1 & 896) == 0) {
                $dirty1 |= $composer2.changed(scrollBehavior) ? 256 : 128;
            }
            if ((1533916891 & $dirty2) != 306783378) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(210227552, $dirty2, $dirty1, "androidx.compose.material3.TwoRowsTopAppBar (AppBar.kt:1123)");
                }
                if (Dp.m5273compareTo0680j_4(maxHeight, pinnedHeight) <= 0) {
                    throw new IllegalArgumentException("A TwoRowsTopAppBar max height should be greater than its pinned height");
                }
                final Ref.FloatRef pinnedHeightPx4 = new Ref.FloatRef();
                final Ref.FloatRef maxHeightPx4 = new Ref.FloatRef();
                final Ref.IntRef titleBottomPaddingPx4 = new Ref.IntRef();
                ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume4 = $composer2.consume(localDensity4);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u249 = (Density) objConsume4;
                pinnedHeightPx4.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u249.mo327toPx0680j_4(pinnedHeight);
                maxHeightPx4.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u249.mo327toPx0680j_4(maxHeight);
                titleBottomPaddingPx4.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u249.mo321roundToPx0680j_4(titleBottomPadding);
                Object key2$iv4 = Float.valueOf(pinnedHeightPx4.element);
                Object key3$iv5 = Float.valueOf(maxHeightPx4.element);
                int i18 = ($dirty1 >> 6) & 14;
                $composer2.startReplaceableGroup(1618982084);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv4) | $composer2.changed(key3$iv5);
                value$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                    key3$iv = Composer.INSTANCE.getEmpty();
                    if (value$iv$iv == key3$iv) {
                    }
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                    if (scrollBehavior != null) {
                        colorTransitionFraction = 0.0f;
                    } else {
                        colorTransitionFraction = 0.0f;
                    }
                    State appBarContainerColor$delegate7 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
                    final Function2<? super Composer, ? super Integer, Unit> actionsRow7 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                            ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                                }
                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                                int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume5 = $composer3.consume(localDensity5);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume5;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume6;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume7 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume7;
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
                                int i19 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    });
                    final float topTitleAlpha7 = TopTitleAlphaEasing.transform(colorTransitionFraction);
                    final float bottomTitleAlpha7 = 1.0f - colorTransitionFraction;
                    if (colorTransitionFraction < 0.5f) {
                        z = true;
                    } else {
                        z = false;
                    }
                    hideTopRowSemantics = z;
                    if (hideTopRowSemantics) {
                        hideBottomRowSemantics = true;
                    } else {
                        hideBottomRowSemantics = false;
                    }
                    $composer2.startReplaceableGroup(-1609665814);
                    ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
                    if (scrollBehavior != null) {
                        appBarDragModifier = Modifier.INSTANCE;
                    } else {
                        appBarDragModifier = Modifier.INSTANCE;
                    }
                    $composer2.endReplaceableGroup();
                    final int i19 = $dirty2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate7), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                            TopAppBarState state2;
                            ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                                }
                                WindowInsets windowInsets2 = windowInsets;
                                Ref.FloatRef floatRef = pinnedHeightPx4;
                                TopAppBarColors topAppBarColors = colors;
                                Function2<Composer, Integer, Unit> function6 = function3;
                                TextStyle textStyle = smallTitleTextStyle;
                                float f = topTitleAlpha7;
                                boolean z2 = hideTopRowSemantics;
                                Function2<Composer, Integer, Unit> function7 = function4;
                                Function2<Composer, Integer, Unit> function8 = actionsRow7;
                                int i110 = i19;
                                Ref.FloatRef floatRef2 = maxHeightPx4;
                                TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                                Function2<Composer, Integer, Unit> function9 = function2;
                                TextStyle textStyle2 = titleTextStyle;
                                float f2 = bottomTitleAlpha7;
                                Ref.IntRef intRef = titleBottomPaddingPx4;
                                boolean z3 = hideBottomRowSemantics;
                                $composer3.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                int $changed$iv$iv = (0 << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume5 = $composer3.consume(localDensity5);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume5;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume6;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume7 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume7;
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
                                int i111 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                int i112 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                                int i113 = i110 << 3;
                                AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i113 & 458752) | 905969664 | (i113 & 3670016), ((i110 >> 12) & 896) | 3078);
                                int i114 = i110 << 12;
                                AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i114 & 458752) | 905969664 | (i114 & 3670016), 3456);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    }), $composer2, 12582912, 122);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        TopAppBarState state2;
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), pinnedHeightPx4.element - maxHeightPx4.element)) {
                            TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                            TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                            if (state3 == null) {
                                return;
                            }
                            state3.setHeightOffsetLimit(pinnedHeightPx4.element - maxHeightPx4.element);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                if (scrollBehavior != null) {
                    colorTransitionFraction = 0.0f;
                } else {
                    colorTransitionFraction = 0.0f;
                }
                State appBarContainerColor$delegate8 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
                final Function2<? super Composer, ? super Integer, Unit> actionsRow8 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                        ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                            }
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                            int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume5 = $composer3.consume(localDensity5);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume5;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume6 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume6;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume7 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume7;
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
                            int i110 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                final float topTitleAlpha8 = TopTitleAlphaEasing.transform(colorTransitionFraction);
                final float bottomTitleAlpha8 = 1.0f - colorTransitionFraction;
                if (colorTransitionFraction < 0.5f) {
                    z = true;
                } else {
                    z = false;
                }
                hideTopRowSemantics = z;
                if (hideTopRowSemantics) {
                    hideBottomRowSemantics = true;
                } else {
                    hideBottomRowSemantics = false;
                }
                $composer2.startReplaceableGroup(-1609665814);
                ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
                if (scrollBehavior != null) {
                    appBarDragModifier = Modifier.INSTANCE;
                } else {
                    appBarDragModifier = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                final int i110 = $dirty2;
                modifier3 = modifier2;
                $dirty = $dirty2;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate8), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                        TopAppBarState state2;
                        ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                            }
                            WindowInsets windowInsets2 = windowInsets;
                            Ref.FloatRef floatRef = pinnedHeightPx4;
                            TopAppBarColors topAppBarColors = colors;
                            Function2<Composer, Integer, Unit> function6 = function3;
                            TextStyle textStyle = smallTitleTextStyle;
                            float f = topTitleAlpha8;
                            boolean z2 = hideTopRowSemantics;
                            Function2<Composer, Integer, Unit> function7 = function4;
                            Function2<Composer, Integer, Unit> function8 = actionsRow8;
                            int i111 = i110;
                            Ref.FloatRef floatRef2 = maxHeightPx4;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            Function2<Composer, Integer, Unit> function9 = function2;
                            TextStyle textStyle2 = titleTextStyle;
                            float f2 = bottomTitleAlpha8;
                            Ref.IntRef intRef = titleBottomPaddingPx4;
                            boolean z3 = hideBottomRowSemantics;
                            $composer3.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume5 = $composer3.consume(localDensity5);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume5;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume6 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume6;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume7 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume7;
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
                            int i112 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                            int i113 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                            int i114 = i111 << 3;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i114 & 458752) | 905969664 | (i114 & 3670016), ((i111 >> 12) & 896) | 3078);
                            int i115 = i111 << 12;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i115 & 458752) | 905969664 | (i115 & 3670016), 3456);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, 12582912, 122);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(210227552, $dirty2, $dirty1, "androidx.compose.material3.TwoRowsTopAppBar (AppBar.kt:1123)");
                }
                if (Dp.m5273compareTo0680j_4(maxHeight, pinnedHeight) <= 0) {
                    throw new IllegalArgumentException("A TwoRowsTopAppBar max height should be greater than its pinned height");
                }
                final Ref.FloatRef pinnedHeightPx5 = new Ref.FloatRef();
                final Ref.FloatRef maxHeightPx5 = new Ref.FloatRef();
                final Ref.IntRef titleBottomPaddingPx5 = new Ref.IntRef();
                ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume5 = $composer2.consume(localDensity5);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u2410 = (Density) objConsume5;
                pinnedHeightPx5.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u2410.mo327toPx0680j_4(pinnedHeight);
                maxHeightPx5.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u2410.mo327toPx0680j_4(maxHeight);
                titleBottomPaddingPx5.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u2410.mo321roundToPx0680j_4(titleBottomPadding);
                Object key2$iv5 = Float.valueOf(pinnedHeightPx5.element);
                Object key3$iv6 = Float.valueOf(maxHeightPx5.element);
                int i111 = ($dirty1 >> 6) & 14;
                $composer2.startReplaceableGroup(1618982084);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv5) | $composer2.changed(key3$iv6);
                value$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                    key3$iv = Composer.INSTANCE.getEmpty();
                    if (value$iv$iv == key3$iv) {
                    }
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                    if (scrollBehavior != null) {
                        colorTransitionFraction = 0.0f;
                    } else {
                        colorTransitionFraction = 0.0f;
                    }
                    State appBarContainerColor$delegate9 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
                    final Function2<? super Composer, ? super Integer, Unit> actionsRow9 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                            ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                                }
                                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                                int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                                $composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                int $changed$iv$iv = ($changed$iv << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer3.consume(localDensity6);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume6;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume7 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume7;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume8 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume8;
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
                                int i112 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                                function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    });
                    final float topTitleAlpha9 = TopTitleAlphaEasing.transform(colorTransitionFraction);
                    final float bottomTitleAlpha9 = 1.0f - colorTransitionFraction;
                    if (colorTransitionFraction < 0.5f) {
                        z = true;
                    } else {
                        z = false;
                    }
                    hideTopRowSemantics = z;
                    if (hideTopRowSemantics) {
                        hideBottomRowSemantics = true;
                    } else {
                        hideBottomRowSemantics = false;
                    }
                    $composer2.startReplaceableGroup(-1609665814);
                    ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
                    if (scrollBehavior != null) {
                        appBarDragModifier = Modifier.INSTANCE;
                    } else {
                        appBarDragModifier = Modifier.INSTANCE;
                    }
                    $composer2.endReplaceableGroup();
                    final int i112 = $dirty2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate9), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                            TopAppBarState state2;
                            ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                                }
                                WindowInsets windowInsets2 = windowInsets;
                                Ref.FloatRef floatRef = pinnedHeightPx5;
                                TopAppBarColors topAppBarColors = colors;
                                Function2<Composer, Integer, Unit> function6 = function3;
                                TextStyle textStyle = smallTitleTextStyle;
                                float f = topTitleAlpha9;
                                boolean z2 = hideTopRowSemantics;
                                Function2<Composer, Integer, Unit> function7 = function4;
                                Function2<Composer, Integer, Unit> function8 = actionsRow9;
                                int i113 = i112;
                                Ref.FloatRef floatRef2 = maxHeightPx5;
                                TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                                Function2<Composer, Integer, Unit> function9 = function2;
                                TextStyle textStyle2 = titleTextStyle;
                                float f2 = bottomTitleAlpha9;
                                Ref.IntRef intRef = titleBottomPaddingPx5;
                                boolean z3 = hideBottomRowSemantics;
                                $composer3.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                Modifier modifier$iv = Modifier.INSTANCE;
                                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                int $changed$iv$iv = (0 << 3) & 112;
                                $composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume6 = $composer3.consume(localDensity6);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                Density density$iv$iv = (Density) objConsume6;
                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume7 = $composer3.consume(localLayoutDirection);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume7;
                                ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                Object objConsume8 = $composer3.consume(localViewConfiguration);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume8;
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
                                int i114 = ($changed$iv$iv$iv >> 9) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                int i115 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                                int i116 = i113 << 3;
                                AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i116 & 458752) | 905969664 | (i116 & 3670016), ((i113 >> 12) & 896) | 3078);
                                int i117 = i113 << 12;
                                AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i117 & 458752) | 905969664 | (i117 & 3670016), 3456);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endReplaceableGroup();
                                $composer3.endNode();
                                $composer3.endReplaceableGroup();
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    }), $composer2, 12582912, 122);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        TopAppBarState state2;
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), pinnedHeightPx5.element - maxHeightPx5.element)) {
                            TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                            TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                            if (state3 == null) {
                                return;
                            }
                            state3.setHeightOffsetLimit(pinnedHeightPx5.element - maxHeightPx5.element);
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                if (scrollBehavior != null) {
                    colorTransitionFraction = 0.0f;
                } else {
                    colorTransitionFraction = 0.0f;
                }
                State appBarContainerColor$delegate10 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
                final Function2<? super Composer, ? super Integer, Unit> actionsRow10 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                        ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                            }
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                            int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume6 = $composer3.consume(localDensity6);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume6;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume7 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume7;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume8 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume8;
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
                            int i113 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                final float topTitleAlpha10 = TopTitleAlphaEasing.transform(colorTransitionFraction);
                final float bottomTitleAlpha10 = 1.0f - colorTransitionFraction;
                if (colorTransitionFraction < 0.5f) {
                    z = true;
                } else {
                    z = false;
                }
                hideTopRowSemantics = z;
                if (hideTopRowSemantics) {
                    hideBottomRowSemantics = true;
                } else {
                    hideBottomRowSemantics = false;
                }
                $composer2.startReplaceableGroup(-1609665814);
                ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
                if (scrollBehavior != null) {
                    appBarDragModifier = Modifier.INSTANCE;
                } else {
                    appBarDragModifier = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                final int i113 = $dirty2;
                modifier3 = modifier2;
                $dirty = $dirty2;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate10), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                        TopAppBarState state2;
                        ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                            }
                            WindowInsets windowInsets2 = windowInsets;
                            Ref.FloatRef floatRef = pinnedHeightPx5;
                            TopAppBarColors topAppBarColors = colors;
                            Function2<Composer, Integer, Unit> function6 = function3;
                            TextStyle textStyle = smallTitleTextStyle;
                            float f = topTitleAlpha10;
                            boolean z2 = hideTopRowSemantics;
                            Function2<Composer, Integer, Unit> function7 = function4;
                            Function2<Composer, Integer, Unit> function8 = actionsRow10;
                            int i114 = i113;
                            Ref.FloatRef floatRef2 = maxHeightPx5;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            Function2<Composer, Integer, Unit> function9 = function2;
                            TextStyle textStyle2 = titleTextStyle;
                            float f2 = bottomTitleAlpha10;
                            Ref.IntRef intRef = titleBottomPaddingPx5;
                            boolean z3 = hideBottomRowSemantics;
                            $composer3.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume6 = $composer3.consume(localDensity6);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume6;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume7 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume7;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume8 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume8;
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
                            int i115 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                            int i116 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                            int i117 = i114 << 3;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i117 & 458752) | 905969664 | (i117 & 3670016), ((i114 >> 12) & 896) | 3078);
                            int i118 = i114 << 12;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i118 & 458752) | 905969664 | (i118 & 3670016), 3456);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, 12582912, 122);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier6 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$4
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
                    AppBarKt.m1312TwoRowsTopAppBartjU4iQQ(modifier6, function2, titleTextStyle, titleBottomPadding, function3, smallTitleTextStyle, function4, function5, windowInsets, colors, maxHeight, pinnedHeight, scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i4 = 805306368;
        $dirty2 |= i4;
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 14) == 0) {
            if ($composer2.changed(maxHeight)) {
                i5 = 4;
            } else {
                i5 = 2;
            }
            $dirty1 |= i5;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 112) == 0) {
            $dirty1 |= $composer2.changed(pinnedHeight) ? 32 : 16;
        }
        if ((i & 4096) != 0) {
            $dirty1 |= 384;
        } else if (($changed1 & 896) == 0) {
            $dirty1 |= $composer2.changed(scrollBehavior) ? 256 : 128;
        }
        if ((1533916891 & $dirty2) != 306783378) {
            if (i6 != 0) {
                modifier2 = Modifier.INSTANCE;
            } else {
                modifier2 = modifier;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(210227552, $dirty2, $dirty1, "androidx.compose.material3.TwoRowsTopAppBar (AppBar.kt:1123)");
            }
            if (Dp.m5273compareTo0680j_4(maxHeight, pinnedHeight) <= 0) {
                throw new IllegalArgumentException("A TwoRowsTopAppBar max height should be greater than its pinned height");
            }
            final Ref.FloatRef pinnedHeightPx6 = new Ref.FloatRef();
            final Ref.FloatRef maxHeightPx6 = new Ref.FloatRef();
            final Ref.IntRef titleBottomPaddingPx6 = new Ref.IntRef();
            ProvidableCompositionLocal<Density> localDensity6 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer2.consume(localDensity6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u2411 = (Density) objConsume6;
            pinnedHeightPx6.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u2411.mo327toPx0680j_4(pinnedHeight);
            maxHeightPx6.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u2411.mo327toPx0680j_4(maxHeight);
            titleBottomPaddingPx6.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u2411.mo321roundToPx0680j_4(titleBottomPadding);
            Object key2$iv6 = Float.valueOf(pinnedHeightPx6.element);
            Object key3$iv7 = Float.valueOf(maxHeightPx6.element);
            int i114 = ($dirty1 >> 6) & 14;
            $composer2.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv6) | $composer2.changed(key3$iv7);
            value$iv$iv = $composer2.rememberedValue();
            if (!invalid$iv$iv) {
                key3$iv = Composer.INSTANCE.getEmpty();
                if (value$iv$iv == key3$iv) {
                }
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                if (scrollBehavior != null) {
                    colorTransitionFraction = 0.0f;
                } else {
                    colorTransitionFraction = 0.0f;
                }
                State appBarContainerColor$delegate11 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
                final Function2<? super Composer, ? super Integer, Unit> actionsRow11 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                        ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                            }
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                            int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume7 = $composer3.consume(localDensity7);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume7;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume8 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume8;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume9 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume9;
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
                            int i115 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                final float topTitleAlpha11 = TopTitleAlphaEasing.transform(colorTransitionFraction);
                final float bottomTitleAlpha11 = 1.0f - colorTransitionFraction;
                if (colorTransitionFraction < 0.5f) {
                    z = true;
                } else {
                    z = false;
                }
                hideTopRowSemantics = z;
                if (hideTopRowSemantics) {
                    hideBottomRowSemantics = true;
                } else {
                    hideBottomRowSemantics = false;
                }
                $composer2.startReplaceableGroup(-1609665814);
                ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
                if (scrollBehavior != null) {
                    appBarDragModifier = Modifier.INSTANCE;
                } else {
                    appBarDragModifier = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                final int i115 = $dirty2;
                modifier3 = modifier2;
                $dirty = $dirty2;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate11), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                        TopAppBarState state2;
                        ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                            }
                            WindowInsets windowInsets2 = windowInsets;
                            Ref.FloatRef floatRef = pinnedHeightPx6;
                            TopAppBarColors topAppBarColors = colors;
                            Function2<Composer, Integer, Unit> function6 = function3;
                            TextStyle textStyle = smallTitleTextStyle;
                            float f = topTitleAlpha11;
                            boolean z2 = hideTopRowSemantics;
                            Function2<Composer, Integer, Unit> function7 = function4;
                            Function2<Composer, Integer, Unit> function8 = actionsRow11;
                            int i116 = i115;
                            Ref.FloatRef floatRef2 = maxHeightPx6;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            Function2<Composer, Integer, Unit> function9 = function2;
                            TextStyle textStyle2 = titleTextStyle;
                            float f2 = bottomTitleAlpha11;
                            Ref.IntRef intRef = titleBottomPaddingPx6;
                            boolean z3 = hideBottomRowSemantics;
                            $composer3.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume7 = $composer3.consume(localDensity7);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume7;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume8 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume8;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume9 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume9;
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
                            int i117 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                            int i118 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                            int i119 = i116 << 3;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i119 & 458752) | 905969664 | (i119 & 3670016), ((i116 >> 12) & 896) | 3078);
                            int i1110 = i116 << 12;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i1110 & 458752) | 905969664 | (i1110 & 3670016), 3456);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, 12582912, 122);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    TopAppBarState state2;
                    TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                    if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), pinnedHeightPx6.element - maxHeightPx6.element)) {
                        TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                        TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                        if (state3 == null) {
                            return;
                        }
                        state3.setHeightOffsetLimit(pinnedHeightPx6.element - maxHeightPx6.element);
                    }
                }
            };
            $composer2.updateRememberedValue(value$iv$iv);
            $composer2.endReplaceableGroup();
            EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
            if (scrollBehavior != null) {
                colorTransitionFraction = 0.0f;
            } else {
                colorTransitionFraction = 0.0f;
            }
            State appBarContainerColor$delegate12 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
            final Function2<? super Composer, ? super Integer, Unit> actionsRow12 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                    ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                        }
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                        int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                        $composer3.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                        int $changed$iv$iv = ($changed$iv << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume7 = $composer3.consume(localDensity7);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Density density$iv$iv = (Density) objConsume7;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume8 = $composer3.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume8;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume9 = $composer3.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume9;
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
                        int i116 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                        function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            });
            final float topTitleAlpha12 = TopTitleAlphaEasing.transform(colorTransitionFraction);
            final float bottomTitleAlpha12 = 1.0f - colorTransitionFraction;
            if (colorTransitionFraction < 0.5f) {
                z = true;
            } else {
                z = false;
            }
            hideTopRowSemantics = z;
            if (hideTopRowSemantics) {
                hideBottomRowSemantics = true;
            } else {
                hideBottomRowSemantics = false;
            }
            $composer2.startReplaceableGroup(-1609665814);
            ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
            if (scrollBehavior != null) {
                appBarDragModifier = Modifier.INSTANCE;
            } else {
                appBarDragModifier = Modifier.INSTANCE;
            }
            $composer2.endReplaceableGroup();
            final int i116 = $dirty2;
            modifier3 = modifier2;
            $dirty = $dirty2;
            SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate12), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                    TopAppBarState state2;
                    ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                        }
                        WindowInsets windowInsets2 = windowInsets;
                        Ref.FloatRef floatRef = pinnedHeightPx6;
                        TopAppBarColors topAppBarColors = colors;
                        Function2<Composer, Integer, Unit> function6 = function3;
                        TextStyle textStyle = smallTitleTextStyle;
                        float f = topTitleAlpha12;
                        boolean z2 = hideTopRowSemantics;
                        Function2<Composer, Integer, Unit> function7 = function4;
                        Function2<Composer, Integer, Unit> function8 = actionsRow12;
                        int i117 = i116;
                        Ref.FloatRef floatRef2 = maxHeightPx6;
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        Function2<Composer, Integer, Unit> function9 = function2;
                        TextStyle textStyle2 = titleTextStyle;
                        float f2 = bottomTitleAlpha12;
                        Ref.IntRef intRef = titleBottomPaddingPx6;
                        boolean z3 = hideBottomRowSemantics;
                        $composer3.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume7 = $composer3.consume(localDensity7);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Density density$iv$iv = (Density) objConsume7;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume8 = $composer3.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume8;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume9 = $composer3.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume9;
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
                        int i118 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        int i119 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                        int i1110 = i117 << 3;
                        AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i1110 & 458752) | 905969664 | (i1110 & 3670016), ((i117 >> 12) & 896) | 3078);
                        int i1111 = i117 << 12;
                        AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i1111 & 458752) | 905969664 | (i1111 & 3670016), 3456);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, 12582912, 122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            if (i6 != 0) {
                modifier2 = Modifier.INSTANCE;
            } else {
                modifier2 = modifier;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(210227552, $dirty2, $dirty1, "androidx.compose.material3.TwoRowsTopAppBar (AppBar.kt:1123)");
            }
            if (Dp.m5273compareTo0680j_4(maxHeight, pinnedHeight) <= 0) {
                throw new IllegalArgumentException("A TwoRowsTopAppBar max height should be greater than its pinned height");
            }
            final Ref.FloatRef pinnedHeightPx7 = new Ref.FloatRef();
            final Ref.FloatRef maxHeightPx7 = new Ref.FloatRef();
            final Ref.IntRef titleBottomPaddingPx7 = new Ref.IntRef();
            ProvidableCompositionLocal<Density> localDensity7 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume7 = $composer2.consume(localDensity7);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u2412 = (Density) objConsume7;
            pinnedHeightPx7.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u2412.mo327toPx0680j_4(pinnedHeight);
            maxHeightPx7.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u2412.mo327toPx0680j_4(maxHeight);
            titleBottomPaddingPx7.element = $this$TwoRowsTopAppBar_tjU4iQQ_u24lambda_u2412.mo321roundToPx0680j_4(titleBottomPadding);
            Object key2$iv7 = Float.valueOf(pinnedHeightPx7.element);
            Object key3$iv8 = Float.valueOf(maxHeightPx7.element);
            int i117 = ($dirty1 >> 6) & 14;
            $composer2.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(scrollBehavior) | $composer2.changed(key2$iv7) | $composer2.changed(key3$iv8);
            value$iv$iv = $composer2.rememberedValue();
            if (!invalid$iv$iv) {
                key3$iv = Composer.INSTANCE.getEmpty();
                if (value$iv$iv == key3$iv) {
                }
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
                if (scrollBehavior != null) {
                    colorTransitionFraction = 0.0f;
                } else {
                    colorTransitionFraction = 0.0f;
                }
                State appBarContainerColor$delegate13 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
                final Function2<? super Composer, ? super Integer, Unit> actionsRow13 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                        ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                            }
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                            int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                            int $changed$iv$iv = ($changed$iv << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume8 = $composer3.consume(localDensity8);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume8;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume9 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume9;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume10 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume10;
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
                            int i118 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                            function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                });
                final float topTitleAlpha13 = TopTitleAlphaEasing.transform(colorTransitionFraction);
                final float bottomTitleAlpha13 = 1.0f - colorTransitionFraction;
                if (colorTransitionFraction < 0.5f) {
                    z = true;
                } else {
                    z = false;
                }
                hideTopRowSemantics = z;
                if (hideTopRowSemantics) {
                    hideBottomRowSemantics = true;
                } else {
                    hideBottomRowSemantics = false;
                }
                $composer2.startReplaceableGroup(-1609665814);
                ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
                if (scrollBehavior != null) {
                    appBarDragModifier = Modifier.INSTANCE;
                } else {
                    appBarDragModifier = Modifier.INSTANCE;
                }
                $composer2.endReplaceableGroup();
                final int i118 = $dirty2;
                modifier3 = modifier2;
                $dirty = $dirty2;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate13), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                        TopAppBarState state2;
                        ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                            }
                            WindowInsets windowInsets2 = windowInsets;
                            Ref.FloatRef floatRef = pinnedHeightPx7;
                            TopAppBarColors topAppBarColors = colors;
                            Function2<Composer, Integer, Unit> function6 = function3;
                            TextStyle textStyle = smallTitleTextStyle;
                            float f = topTitleAlpha13;
                            boolean z2 = hideTopRowSemantics;
                            Function2<Composer, Integer, Unit> function7 = function4;
                            Function2<Composer, Integer, Unit> function8 = actionsRow13;
                            int i119 = i118;
                            Ref.FloatRef floatRef2 = maxHeightPx7;
                            TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                            Function2<Composer, Integer, Unit> function9 = function2;
                            TextStyle textStyle2 = titleTextStyle;
                            float f2 = bottomTitleAlpha13;
                            Ref.IntRef intRef = titleBottomPaddingPx7;
                            boolean z3 = hideBottomRowSemantics;
                            $composer3.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume8 = $composer3.consume(localDensity8);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Density density$iv$iv = (Density) objConsume8;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume9 = $composer3.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume9;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume10 = $composer3.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume10;
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
                            int i1110 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                            int i1111 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                            int i1112 = i119 << 3;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i1112 & 458752) | 905969664 | (i1112 & 3670016), ((i119 >> 12) & 896) | 3078);
                            int i1113 = i119 << 12;
                            AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i1113 & 458752) | 905969664 | (i1113 & 3670016), 3456);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, 12582912, 122);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            value$iv$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    TopAppBarState state2;
                    TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                    if (!Intrinsics.areEqual((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state2.getHeightOffsetLimit()), pinnedHeightPx7.element - maxHeightPx7.element)) {
                        TopAppBarScrollBehavior topAppBarScrollBehavior2 = scrollBehavior;
                        TopAppBarState state3 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
                        if (state3 == null) {
                            return;
                        }
                        state3.setHeightOffsetLimit(pinnedHeightPx7.element - maxHeightPx7.element);
                    }
                }
            };
            $composer2.updateRememberedValue(value$iv$iv);
            $composer2.endReplaceableGroup();
            EffectsKt.SideEffect((Function0) value$iv$iv, $composer2, 0);
            if (scrollBehavior != null) {
                colorTransitionFraction = 0.0f;
            } else {
                colorTransitionFraction = 0.0f;
            }
            State appBarContainerColor$delegate14 = SnapshotStateKt.rememberUpdatedState(Color.m2961boximpl(colors.m2000containerColorXeAY9LY$material3_release(colorTransitionFraction, $composer2, ($dirty2 >> 24) & 112)), $composer2, 0);
            final Function2<? super Composer, ? super Integer, Unit> actionsRow14 = ComposableLambdaKt.composableLambda($composer2, -1048401111, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$actionsRow$1
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
                    ComposerKt.sourceInformation($composer3, "C1170@52876L157:AppBar.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1048401111, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1169)");
                        }
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        Function3<RowScope, Composer, Integer, Unit> function6 = function5;
                        int $changed$iv = (($dirty2 >> 12) & 7168) | 432;
                        $composer3.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                        int $changed$iv$iv = ($changed$iv << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume8 = $composer3.consume(localDensity8);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Density density$iv$iv = (Density) objConsume8;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume9 = $composer3.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume9;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume10 = $composer3.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume10;
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
                        int i119 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
                        function6.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            });
            final float topTitleAlpha14 = TopTitleAlphaEasing.transform(colorTransitionFraction);
            final float bottomTitleAlpha14 = 1.0f - colorTransitionFraction;
            if (colorTransitionFraction < 0.5f) {
                z = true;
            } else {
                z = false;
            }
            hideTopRowSemantics = z;
            if (hideTopRowSemantics) {
                hideBottomRowSemantics = true;
            } else {
                hideBottomRowSemantics = false;
            }
            $composer2.startReplaceableGroup(-1609665814);
            ComposerKt.sourceInformation($composer2, "1187@53760L118,1187@53737L141,1190@53908L255");
            if (scrollBehavior != null) {
                appBarDragModifier = Modifier.INSTANCE;
            } else {
                appBarDragModifier = Modifier.INSTANCE;
            }
            $composer2.endReplaceableGroup();
            final int i119 = $dirty2;
            modifier3 = modifier2;
            $dirty = $dirty2;
            SurfaceKt.m1806SurfaceT9BRK9s(modifier2.then(appBarDragModifier), null, TwoRowsTopAppBar_tjU4iQQ$lambda$8(appBarContainerColor$delegate14), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1985938853, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
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
                    TopAppBarState state2;
                    ComposerKt.sourceInformation($composer3, "C1204@54309L2201:AppBar.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1985938853, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:1203)");
                        }
                        WindowInsets windowInsets2 = windowInsets;
                        Ref.FloatRef floatRef = pinnedHeightPx7;
                        TopAppBarColors topAppBarColors = colors;
                        Function2<Composer, Integer, Unit> function6 = function3;
                        TextStyle textStyle = smallTitleTextStyle;
                        float f = topTitleAlpha14;
                        boolean z2 = hideTopRowSemantics;
                        Function2<Composer, Integer, Unit> function7 = function4;
                        Function2<Composer, Integer, Unit> function8 = actionsRow14;
                        int i1110 = i119;
                        Ref.FloatRef floatRef2 = maxHeightPx7;
                        TopAppBarScrollBehavior topAppBarScrollBehavior = scrollBehavior;
                        Function2<Composer, Integer, Unit> function9 = function2;
                        TextStyle textStyle2 = titleTextStyle;
                        float f2 = bottomTitleAlpha14;
                        Ref.IntRef intRef = titleBottomPaddingPx7;
                        boolean z3 = hideBottomRowSemantics;
                        $composer3.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity8 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume8 = $composer3.consume(localDensity8);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Density density$iv$iv = (Density) objConsume8;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume9 = $composer3.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume9;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume10 = $composer3.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume10;
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
                        int i1111 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        int i1112 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1863905175, "C1205@54330L982,1226@55325L1175:AppBar.kt#uh7d8r");
                        int i1113 = i1110 << 3;
                        AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, windowInsets2)), floatRef.element, topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function6, textStyle, f, Arrangement.INSTANCE.getCenter(), Arrangement.INSTANCE.getStart(), 0, z2, function7, function8, $composer3, (i1113 & 458752) | 905969664 | (i1113 & 3670016), ((i1110 >> 12) & 896) | 3078);
                        int i1114 = i1110 << 12;
                        AppBarKt.m1311TopAppBarLayoutkXwM9vE(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, WindowInsetsKt.m560onlybOOhFvg(windowInsets2, WindowInsetsSides.INSTANCE.m580getHorizontalJoeWqyM()))), (floatRef2.element - floatRef.element) + ((topAppBarScrollBehavior == null || (state2 = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state2.getHeightOffset()), topAppBarColors.getNavigationIconContentColor(), topAppBarColors.getTitleContentColor(), topAppBarColors.getActionIconContentColor(), function9, textStyle2, f2, Arrangement.INSTANCE.getBottom(), Arrangement.INSTANCE.getStart(), intRef.element, z3, ComposableSingletons$AppBarKt.INSTANCE.m1434getLambda11$material3_release(), ComposableSingletons$AppBarKt.INSTANCE.m1435getLambda12$material3_release(), $composer3, (i1114 & 458752) | 905969664 | (i1114 & 3670016), 3456);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, 12582912, 122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier7 = modifier3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$4
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

            public final void invoke(Composer composer, int i1110) {
                AppBarKt.m1312TwoRowsTopAppBartjU4iQQ(modifier7, function2, titleTextStyle, titleBottomPadding, function3, smallTitleTextStyle, function4, function5, windowInsets, colors, maxHeight, pinnedHeight, scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    private static final long TwoRowsTopAppBar_tjU4iQQ$lambda$8(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m2981unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: TopAppBarLayout-kXwM9vE, reason: not valid java name */
    public static final void m1311TopAppBarLayoutkXwM9vE(final Modifier modifier, final float heightPx, final long navigationIconContentColor, final long titleContentColor, final long actionIconContentColor, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle titleTextStyle, final float titleAlpha, final Arrangement.Vertical titleVerticalArrangement, final Arrangement.Horizontal titleHorizontalArrangement, final int titleBottomPadding, final boolean hideTitleSemantics, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int $changed1) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        Composer $composer2;
        final int $dirty;
        Composer $composer3 = $composer.startRestartGroup(-6794037);
        ComposerKt.sourceInformation($composer3, "C(TopAppBarLayout)P(4,2,6:c#ui.graphics.Color,10:c#ui.graphics.Color,0:c#ui.graphics.Color,7,12,8,13,11,9,3,5)1296@58560L4308:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changed(heightPx) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changed(navigationIconContentColor) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty2 |= $composer3.changed(titleContentColor) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            $dirty2 |= $composer3.changed(actionIconContentColor) ? 16384 : 8192;
        }
        if ((458752 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 131072 : 65536;
        }
        if ((3670016 & $changed) == 0) {
            $dirty2 |= $composer3.changed(titleTextStyle) ? 1048576 : 524288;
        }
        if ((29360128 & $changed) == 0) {
            $dirty2 |= $composer3.changed(titleAlpha) ? 8388608 : 4194304;
        }
        if (($changed & 234881024) == 0) {
            $dirty2 |= $composer3.changed(titleVerticalArrangement) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 1879048192) == 0) {
            $dirty2 |= $composer3.changed(titleHorizontalArrangement) ? 536870912 : 268435456;
        }
        if (($changed1 & 14) == 0) {
            $dirty1 |= $composer3.changed(titleBottomPadding) ? 4 : 2;
        }
        if (($changed1 & 112) == 0) {
            $dirty1 |= $composer3.changed(hideTitleSemantics) ? 32 : 16;
        }
        if (($changed1 & 896) == 0) {
            function5 = function3;
            $dirty1 |= $composer3.changedInstance(function5) ? 256 : 128;
        } else {
            function5 = function3;
        }
        if (($changed1 & 7168) == 0) {
            $dirty1 |= $composer3.changedInstance(function4) ? 2048 : 1024;
        }
        int $dirty3 = $dirty1;
        if ((1533916891 & $dirty2) != 306783378 || ($dirty3 & 5851) != 1170 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-6794037, $dirty2, $dirty3, "androidx.compose.material3.TopAppBarLayout (AppBar.kt:1280)");
            }
            MeasurePolicy measurePolicy$iv = new MeasurePolicy() { // from class: androidx.compose.material3.AppBarKt$TopAppBarLayout$2
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
                    return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
                    return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
                    return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
                    return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo11measure3p2s80s(final MeasureScope Layout, List<? extends Measurable> measurables, final long constraints) {
                    int maxTitleWidth;
                    final int titleBaseline;
                    Intrinsics.checkNotNullParameter(Layout, "$this$Layout");
                    Intrinsics.checkNotNullParameter(measurables, "measurables");
                    List<? extends Measurable> $this$first$iv = measurables;
                    for (Object element$iv : $this$first$iv) {
                        Measurable it = (Measurable) element$iv;
                        if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "navigationIcon")) {
                            final Placeable navigationIconPlaceable = ((Measurable) element$iv).mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0));
                            List<? extends Measurable> $this$first$iv2 = measurables;
                            for (Object element$iv2 : $this$first$iv2) {
                                Measurable it2 = (Measurable) element$iv2;
                                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "actionIcons")) {
                                    final Placeable actionIconsPlaceable = ((Measurable) element$iv2).mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0));
                                    if (Constraints.m5218getMaxWidthimpl(constraints) == Integer.MAX_VALUE) {
                                        maxTitleWidth = Constraints.m5218getMaxWidthimpl(constraints);
                                    } else {
                                        maxTitleWidth = RangesKt.coerceAtLeast((Constraints.m5218getMaxWidthimpl(constraints) - navigationIconPlaceable.getWidth()) - actionIconsPlaceable.getWidth(), 0);
                                    }
                                    List<? extends Measurable> $this$first$iv3 = measurables;
                                    for (Object element$iv3 : $this$first$iv3) {
                                        Measurable it3 = (Measurable) element$iv3;
                                        if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it3), "title")) {
                                            final Placeable titlePlaceable = ((Measurable) element$iv3).mo4225measureBRTryo0(Constraints.m5208copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m5220getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m5218getMaxWidthimpl(constraints) : maxTitleWidth, (11 & 4) != 0 ? Constraints.m5219getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m5217getMaxHeightimpl(constraints) : 0));
                                            if (titlePlaceable.get(AlignmentLineKt.getLastBaseline()) != Integer.MIN_VALUE) {
                                                titleBaseline = titlePlaceable.get(AlignmentLineKt.getLastBaseline());
                                            } else {
                                                titleBaseline = 0;
                                            }
                                            final int layoutHeight = MathKt.roundToInt(heightPx);
                                            int iM5218getMaxWidthimpl = Constraints.m5218getMaxWidthimpl(constraints);
                                            final Arrangement.Horizontal horizontal = titleHorizontalArrangement;
                                            final Arrangement.Vertical vertical = titleVerticalArrangement;
                                            final int i = titleBottomPadding;
                                            return MeasureScope.CC.layout$default(Layout, iM5218getMaxWidthimpl, layoutHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.AppBarKt$TopAppBarLayout$2$measure$1
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
                                                public final void invoke2(Placeable.PlacementScope layout) {
                                                    int iMax;
                                                    int height;
                                                    Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                                    Placeable placeable = navigationIconPlaceable;
                                                    Placeable.PlacementScope.placeRelative$default(layout, placeable, 0, (layoutHeight - placeable.getHeight()) / 2, 0.0f, 4, null);
                                                    Placeable placeable2 = titlePlaceable;
                                                    Arrangement.Horizontal horizontal2 = horizontal;
                                                    if (Intrinsics.areEqual(horizontal2, Arrangement.INSTANCE.getCenter())) {
                                                        iMax = (Constraints.m5218getMaxWidthimpl(constraints) - titlePlaceable.getWidth()) / 2;
                                                    } else if (!Intrinsics.areEqual(horizontal2, Arrangement.INSTANCE.getEnd())) {
                                                        iMax = Math.max(Layout.mo321roundToPx0680j_4(AppBarKt.TopAppBarTitleInset), navigationIconPlaceable.getWidth());
                                                    } else {
                                                        iMax = (Constraints.m5218getMaxWidthimpl(constraints) - titlePlaceable.getWidth()) - actionIconsPlaceable.getWidth();
                                                    }
                                                    Arrangement.Vertical vertical2 = vertical;
                                                    if (Intrinsics.areEqual(vertical2, Arrangement.INSTANCE.getCenter())) {
                                                        height = (layoutHeight - titlePlaceable.getHeight()) / 2;
                                                    } else if (Intrinsics.areEqual(vertical2, Arrangement.INSTANCE.getBottom())) {
                                                        height = i == 0 ? layoutHeight - titlePlaceable.getHeight() : (layoutHeight - titlePlaceable.getHeight()) - Math.max(0, (i - titlePlaceable.getHeight()) + titleBaseline);
                                                    } else {
                                                        height = 0;
                                                    }
                                                    Placeable.PlacementScope.placeRelative$default(layout, placeable2, iMax, height, 0.0f, 4, null);
                                                    Placeable.PlacementScope.placeRelative$default(layout, actionIconsPlaceable, Constraints.m5218getMaxWidthimpl(constraints) - actionIconsPlaceable.getWidth(), (layoutHeight - actionIconsPlaceable.getHeight()) / 2, 0.0f, 4, null);
                                                }
                                            }, 4, null);
                                        }
                                    }
                                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                }
                            }
                            throw new NoSuchElementException("Collection contains no element matching the predicate.");
                        }
                    }
                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                }
            };
            int $changed$iv = ($dirty2 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)73@2855L7,74@2910L7,75@2969L7,76@2981L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv = (LayoutDirection) objConsume2;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv = (ViewConfiguration) objConsume3;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier);
            int $changed$iv$iv = (($changed$iv << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, density$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, layoutDirection$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, viewConfiguration$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i = ($changed$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -290535407, "C1298@58590L352,1308@58955L577,1322@59545L336:AppBar.kt#uh7d8r");
            Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "navigationIcon");
            float f = TopAppBarHorizontalPadding;
            Modifier modifier$iv = PaddingKt.m491paddingqDBjuR0$default(modifierLayoutId, f, 0.0f, 0.0f, 0.0f, 14, null);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            $composer2 = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer3.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv = (Density) objConsume4;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            $dirty = $dirty2;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume5 = $composer3.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume5;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer3.consume(localViewConfiguration2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume6;
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv);
            int $changed$iv$iv$iv = ((((6 << 3) & 112) << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor2);
            } else {
                $composer3.useNode();
            }
            $composer3.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, density$iv$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i2 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1090283935, "C1303@58765L163:AppBar.kt#uh7d8r");
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(navigationIconContentColor))}, function5, $composer3, (($dirty3 >> 3) & 112) | 8);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            Modifier modifierM489paddingVpY3zN4$default = PaddingKt.m489paddingVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "title"), f, 0.0f, 2, null);
            Modifier.Companion companionClearAndSetSemantics = Modifier.INSTANCE;
            if (hideTitleSemantics) {
                companionClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(companionClearAndSetSemantics, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.AppBarKt$TopAppBarLayout$1$2
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
            }
            Modifier modifier$iv2 = GraphicsLayerModifierKt.m3127graphicsLayerAp8cVGQ$default(modifierM489paddingVpY3zN4$default.then(companionClearAndSetSemantics), 0.0f, 0.0f, titleAlpha, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131067, null);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv3 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume7 = $composer3.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv2 = (Density) objConsume7;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume8 = $composer3.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume8;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration3 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume9 = $composer3.consume(localViewConfiguration3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume9;
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf3 = LayoutKt.materializerOf(modifier$iv2);
            int $changed$iv$iv$iv2 = ((((0 << 3) & 112) << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor3);
            } else {
                $composer3.useNode();
            }
            $composer3.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i4 = ($changed$iv$iv$iv2 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i5 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1090283420, "C1315@59280L238:AppBar.kt#uh7d8r");
            TextKt.ProvideTextStyle(titleTextStyle, ComposableLambdaKt.composableLambda($composer3, 824316656, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TopAppBarLayout$1$3$1
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
                    ComposerKt.sourceInformation($composer4, "C1316@59343L157:AppBar.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(824316656, $changed2, -1, "androidx.compose.material3.TopAppBarLayout.<anonymous>.<anonymous>.<anonymous> (AppBar.kt:1315)");
                        }
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(titleContentColor))}, function2, $composer4, (($dirty >> 12) & 112) | 8);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), $composer3, (($dirty >> 18) & 14) | 48);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            Modifier modifier$iv3 = PaddingKt.m491paddingqDBjuR0$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "actionIcons"), 0.0f, 0.0f, f, 0.0f, 11, null);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv4 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv3, false, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume10 = $composer3.consume(localDensity4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density$iv$iv3 = (Density) objConsume10;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume11 = $composer3.consume(localLayoutDirection4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection$iv$iv3 = (LayoutDirection) objConsume11;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration4 = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume12 = $composer3.consume(localViewConfiguration4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv3 = (ViewConfiguration) objConsume12;
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf4 = LayoutKt.materializerOf(modifier$iv3);
            int $changed$iv$iv$iv3 = ((((6 << 3) & 112) << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor4);
            } else {
                $composer3.useNode();
            }
            $composer3.disableReusing();
            Composer $this$Layout_u24lambda_u2d0$iv$iv3 = Updater.m2603constructorimpl($composer3);
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, density$iv$iv3, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, layoutDirection$iv$iv3, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv3, viewConfiguration$iv$iv3, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf4.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i6 = ($changed$iv$iv$iv3 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i7 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1090282985, "C1327@59715L152:AppBar.kt#uh7d8r");
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(actionIconContentColor))}, function4, $composer3, (($dirty3 >> 6) & 112) | 8);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $dirty = $dirty2;
            $composer2 = $composer3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TopAppBarLayout$3
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
                AppBarKt.m1311TopAppBarLayoutkXwM9vE(modifier, heightPx, navigationIconContentColor, titleContentColor, actionIconContentColor, function2, titleTextStyle, titleAlpha, titleVerticalArrangement, titleHorizontalArrangement, titleBottomPadding, hideTitleSemantics, function3, function4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:39:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:40:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:43:0x0123 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:44:0x0124  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object settleAppBar(final TopAppBarState state, float velocity, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, Continuation<? super Velocity> continuation) {
        C03321 c03321;
        AnimationSpec<Float> animationSpec2;
        Ref.FloatRef remainingVelocity;
        final TopAppBarState state2;
        TopAppBarState state3;
        AnimationSpec<Float> animationSpec3;
        Ref.FloatRef remainingVelocity2;
        AnimationState animationStateAnimationState$default;
        float heightOffsetLimit;
        Float fBoxFloat;
        Function1<AnimationScope<Float, AnimationVector1D>, Unit> function1;
        Ref.FloatRef remainingVelocity3;
        if (continuation instanceof C03321) {
            c03321 = (C03321) continuation;
            if ((c03321.label & Integer.MIN_VALUE) != 0) {
                c03321.label -= Integer.MIN_VALUE;
            } else {
                c03321 = new C03321(continuation);
            }
        } else {
            c03321 = new C03321(continuation);
        }
        C03321 c03322 = c03321;
        Object $result = c03322.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c03322.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (state.getCollapsedFraction() >= 0.01f) {
                    if (!(state.getCollapsedFraction() == 1.0f)) {
                        final Ref.FloatRef remainingVelocity4 = new Ref.FloatRef();
                        remainingVelocity4.element = velocity;
                        if (decayAnimationSpec != null && Math.abs(velocity) > 1.0f) {
                            final Ref.FloatRef lastValue = new Ref.FloatRef();
                            AnimationState animationStateAnimationState$default2 = AnimationStateKt.AnimationState$default(0.0f, velocity, 0L, 0L, false, 28, null);
                            Function1<AnimationScope<Float, AnimationVector1D>, Unit> function2 = new Function1<AnimationScope<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material3.AppBarKt.settleAppBar.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
                                    invoke2(animationScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(AnimationScope<Float, AnimationVector1D> animateDecay) {
                                    Intrinsics.checkNotNullParameter(animateDecay, "$this$animateDecay");
                                    float delta = animateDecay.getValue().floatValue() - lastValue.element;
                                    float initialHeightOffset = state.getHeightOffset();
                                    state.setHeightOffset(initialHeightOffset + delta);
                                    float consumed = Math.abs(initialHeightOffset - state.getHeightOffset());
                                    lastValue.element = animateDecay.getValue().floatValue();
                                    remainingVelocity4.element = animateDecay.getVelocity().floatValue();
                                    if (Math.abs(delta - consumed) > 0.5f) {
                                        animateDecay.cancelAnimation();
                                    }
                                }
                            };
                            c03322.L$0 = state;
                            c03322.L$1 = animationSpec;
                            c03322.L$2 = remainingVelocity4;
                            c03322.label = 1;
                            if (SuspendAnimationKt.animateDecay$default(animationStateAnimationState$default2, decayAnimationSpec, false, function2, c03322, 2, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            state3 = state;
                            animationSpec3 = animationSpec;
                            remainingVelocity2 = remainingVelocity4;
                            remainingVelocity = remainingVelocity2;
                            state2 = state3;
                            animationSpec2 = animationSpec3;
                        } else {
                            animationSpec2 = animationSpec;
                            remainingVelocity = remainingVelocity4;
                            state2 = state;
                        }
                        if (animationSpec2 != null && state2.getHeightOffset() < 0.0f && state2.getHeightOffset() > state2.getHeightOffsetLimit()) {
                            animationStateAnimationState$default = AnimationStateKt.AnimationState$default(state2.getHeightOffset(), 0.0f, 0L, 0L, false, 30, null);
                            if (state2.getCollapsedFraction() < 0.5f) {
                                heightOffsetLimit = 0.0f;
                            } else {
                                heightOffsetLimit = state2.getHeightOffsetLimit();
                            }
                            fBoxFloat = Boxing.boxFloat(heightOffsetLimit);
                            function1 = new Function1<AnimationScope<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material3.AppBarKt.settleAppBar.3
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
                                    invoke2(animationScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(AnimationScope<Float, AnimationVector1D> animateTo) {
                                    Intrinsics.checkNotNullParameter(animateTo, "$this$animateTo");
                                    state2.setHeightOffset(animateTo.getValue().floatValue());
                                }
                            };
                            c03322.L$0 = remainingVelocity;
                            c03322.L$1 = null;
                            c03322.L$2 = null;
                            c03322.label = 2;
                            if (SuspendAnimationKt.animateTo(animationStateAnimationState$default, fBoxFloat, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : animationSpec2, (4 & 4) != 0 ? false : false, (4 & 8) != 0 ? SuspendAnimationKt.C01582.INSTANCE : function1, c03322) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            remainingVelocity3 = remainingVelocity;
                            remainingVelocity = remainingVelocity3;
                        }
                        return Velocity.m5490boximpl(VelocityKt.Velocity(0.0f, remainingVelocity.element));
                    }
                }
                return Velocity.m5490boximpl(Velocity.INSTANCE.m5510getZero9UxMQ8M());
            case 1:
                remainingVelocity2 = (Ref.FloatRef) c03322.L$2;
                animationSpec3 = (AnimationSpec) c03322.L$1;
                state3 = (TopAppBarState) c03322.L$0;
                ResultKt.throwOnFailure($result);
                remainingVelocity = remainingVelocity2;
                state2 = state3;
                animationSpec2 = animationSpec3;
                if (animationSpec2 != null) {
                    animationStateAnimationState$default = AnimationStateKt.AnimationState$default(state2.getHeightOffset(), 0.0f, 0L, 0L, false, 30, null);
                    if (state2.getCollapsedFraction() < 0.5f) {
                        heightOffsetLimit = 0.0f;
                    } else {
                        heightOffsetLimit = state2.getHeightOffsetLimit();
                    }
                    fBoxFloat = Boxing.boxFloat(heightOffsetLimit);
                    function1 = new Function1<AnimationScope<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material3.AppBarKt.settleAppBar.3
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
                            invoke2(animationScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(AnimationScope<Float, AnimationVector1D> animateTo) {
                            Intrinsics.checkNotNullParameter(animateTo, "$this$animateTo");
                            state2.setHeightOffset(animateTo.getValue().floatValue());
                        }
                    };
                    c03322.L$0 = remainingVelocity;
                    c03322.L$1 = null;
                    c03322.L$2 = null;
                    c03322.label = 2;
                    if (SuspendAnimationKt.animateTo(animationStateAnimationState$default, fBoxFloat, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : animationSpec2, (4 & 4) != 0 ? false : false, (4 & 8) != 0 ? SuspendAnimationKt.C01582.INSTANCE : function1, c03322) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    remainingVelocity3 = remainingVelocity;
                    remainingVelocity = remainingVelocity3;
                }
                return Velocity.m5490boximpl(VelocityKt.Velocity(0.0f, remainingVelocity.element));
            case 2:
                remainingVelocity3 = (Ref.FloatRef) c03322.L$0;
                ResultKt.throwOnFailure($result);
                remainingVelocity = remainingVelocity3;
                return Velocity.m5490boximpl(VelocityKt.Velocity(0.0f, remainingVelocity.element));
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final CubicBezierEasing getTopTitleAlphaEasing() {
        return TopTitleAlphaEasing;
    }
}
