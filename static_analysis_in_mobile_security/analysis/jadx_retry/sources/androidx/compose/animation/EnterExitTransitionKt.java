package androidx.compose.animation;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EnterExitTransition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aT\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142#\b\u0002\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00170\u0016H\u0007ø\u0001\u0000\u001aT\u0010\u001b\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u001c2\b\b\u0002\u0010\u0013\u001a\u00020\u00142#\b\u0002\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\b0\u0016H\u0007ø\u0001\u0000\u001aT\u0010\u001f\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\b\b\u0002\u0010\u0011\u001a\u00020 2\b\b\u0002\u0010\u0013\u001a\u00020\u00142#\b\u0002\u0010!\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00170\u0016H\u0007ø\u0001\u0000\u001a\"\u0010#\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\b\b\u0002\u0010$\u001a\u00020\u0004H\u0007\u001a\"\u0010%\u001a\u00020&2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\b\b\u0002\u0010'\u001a\u00020\u0004H\u0007\u001a9\u0010(\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\b\b\u0002\u0010)\u001a\u00020\u00042\b\b\u0002\u0010*\u001a\u00020\u000bH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a9\u0010-\u001a\u00020&2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\b\b\u0002\u0010.\u001a\u00020\u00042\b\b\u0002\u0010*\u001a\u00020\u000bH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u00100\u001aT\u00101\u001a\u00020&2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\b\b\u0002\u00102\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142#\b\u0002\u00103\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00170\u0016H\u0007ø\u0001\u0000\u001aT\u00104\u001a\u00020&2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\b\b\u0002\u00102\u001a\u00020\u001c2\b\b\u0002\u0010\u0013\u001a\u00020\u00142#\b\u0002\u00105\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\b0\u0016H\u0007ø\u0001\u0000\u001aT\u00106\u001a\u00020&2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\b\b\u0002\u00102\u001a\u00020 2\b\b\u0002\u0010\u0013\u001a\u00020\u00142#\b\u0002\u00107\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00170\u0016H\u0007ø\u0001\u0000\u001a>\u00108\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102!\u00109\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00060\u0016H\u0007ø\u0001\u0000\u001a@\u0010:\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102#\b\u0002\u0010;\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00170\u0016H\u0007ø\u0001\u0000\u001a@\u0010<\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102#\b\u0002\u0010=\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00170\u0016H\u0007ø\u0001\u0000\u001a>\u0010>\u001a\u00020&2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102!\u0010?\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00060\u0016H\u0007ø\u0001\u0000\u001a@\u0010@\u001a\u00020&2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102#\b\u0002\u0010A\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00170\u0016H\u0007ø\u0001\u0000\u001a@\u0010B\u001a\u00020&2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102#\b\u0002\u0010C\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00170\u0016H\u0007ø\u0001\u0000\u001a/\u0010D\u001a\u00020E*\b\u0012\u0004\u0012\u00020G0F2\u0006\u0010H\u001a\u00020\u000e2\u0006\u0010I\u001a\u00020&2\u0006\u0010J\u001a\u00020KH\u0001¢\u0006\u0002\u0010L\u001aB\u0010M\u001a\u00020E*\u00020E2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020G0F2\u000e\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010Q0P2\u000e\u0010R\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010Q0P2\u0006\u0010S\u001a\u00020KH\u0002\u001aB\u0010T\u001a\u00020E*\u00020E2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020G0F2\u000e\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010U0P2\u000e\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010U0P2\u0006\u0010S\u001a\u00020KH\u0002\u001a\f\u0010V\u001a\u00020\u001c*\u00020\u0012H\u0002\u001a\f\u0010V\u001a\u00020\u001c*\u00020 H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\"\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\"\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006W²\u0006\n\u0010X\u001a\u00020\u0014X\u008a\u008e\u0002²\u0006\n\u0010Y\u001a\u00020\u0014X\u008a\u008e\u0002²\u0006\n\u0010Z\u001a\u00020\u0004X\u008a\u0084\u0002²\u0006\n\u0010[\u001a\u00020\u0004X\u008a\u0084\u0002²\u0006\n\u0010*\u001a\u00020\u000bX\u008a\u0084\u0002²\u0006\n\u0010\\\u001a\u00020\u0014X\u008a\u008e\u0002²\u0006\n\u0010\\\u001a\u00020\u0014X\u008a\u008e\u0002"}, d2 = {"DefaultAlpha", "Landroidx/compose/runtime/MutableFloatState;", "DefaultAlphaAndScaleSpring", "Landroidx/compose/animation/core/SpringSpec;", "", "DefaultOffsetAnimationSpec", "Landroidx/compose/ui/unit/IntOffset;", "DefaultSizeAnimationSpec", "Landroidx/compose/ui/unit/IntSize;", "TransformOriginVectorConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/graphics/TransformOrigin;", "Landroidx/compose/animation/core/AnimationVector2D;", "expandHorizontally", "Landroidx/compose/animation/EnterTransition;", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "expandFrom", "Landroidx/compose/ui/Alignment$Horizontal;", "clip", "", "initialWidth", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "fullWidth", "expandIn", "Landroidx/compose/ui/Alignment;", "initialSize", "fullSize", "expandVertically", "Landroidx/compose/ui/Alignment$Vertical;", "initialHeight", "fullHeight", "fadeIn", "initialAlpha", "fadeOut", "Landroidx/compose/animation/ExitTransition;", "targetAlpha", "scaleIn", "initialScale", "transformOrigin", "scaleIn-L8ZKh-E", "(Landroidx/compose/animation/core/FiniteAnimationSpec;FJ)Landroidx/compose/animation/EnterTransition;", "scaleOut", "targetScale", "scaleOut-L8ZKh-E", "(Landroidx/compose/animation/core/FiniteAnimationSpec;FJ)Landroidx/compose/animation/ExitTransition;", "shrinkHorizontally", "shrinkTowards", "targetWidth", "shrinkOut", "targetSize", "shrinkVertically", "targetHeight", "slideIn", "initialOffset", "slideInHorizontally", "initialOffsetX", "slideInVertically", "initialOffsetY", "slideOut", "targetOffset", "slideOutHorizontally", "targetOffsetX", "slideOutVertically", "targetOffsetY", "createModifier", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/EnterExitState;", "enter", "exit", "label", "", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "shrinkExpand", "transition", "expand", "Landroidx/compose/runtime/State;", "Landroidx/compose/animation/ChangeSize;", "shrink", "labelPrefix", "slideInOut", "Landroidx/compose/animation/Slide;", "toAlignment", "animation_release", "shouldAnimateAlpha", "shouldAnimateScale", "alpha", "scale", "shouldAnimate"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EnterExitTransitionKt {
    private static final TwoWayConverter<TransformOrigin, AnimationVector2D> TransformOriginVectorConverter = VectorConvertersKt.TwoWayConverter(new Function1<TransformOrigin, AnimationVector2D>() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ AnimationVector2D invoke(TransformOrigin transformOrigin) {
            return m46invoke__ExYCQ(transformOrigin.getPackedValue());
        }

        /* JADX INFO: renamed from: invoke-__ExYCQ, reason: not valid java name */
        public final AnimationVector2D m46invoke__ExYCQ(long it) {
            return new AnimationVector2D(TransformOrigin.m3357getPivotFractionXimpl(it), TransformOrigin.m3358getPivotFractionYimpl(it));
        }
    }, new Function1<AnimationVector2D, TransformOrigin>() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$2
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ TransformOrigin invoke(AnimationVector2D animationVector2D) {
            return TransformOrigin.m3349boximpl(m47invokeLIALnN8(animationVector2D));
        }

        /* JADX INFO: renamed from: invoke-LIALnN8, reason: not valid java name */
        public final long m47invokeLIALnN8(AnimationVector2D it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return TransformOriginKt.TransformOrigin(it.getV1(), it.getV2());
        }
    });
    private static final MutableFloatState DefaultAlpha = PrimitiveSnapshotStateKt.mutableFloatStateOf(1.0f);
    private static final SpringSpec<Float> DefaultAlphaAndScaleSpring = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
    private static final SpringSpec<IntOffset> DefaultOffsetAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5383boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
    private static final SpringSpec<IntSize> DefaultSizeAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5426boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);

    /* JADX INFO: compiled from: EnterExitTransition.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EnterExitState.values().length];
            try {
                iArr[EnterExitState.Visible.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[EnterExitState.PreEnter.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[EnterExitState.PostExit.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ EnterTransition fadeIn$default(FiniteAnimationSpec finiteAnimationSpec, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        return fadeIn(finiteAnimationSpec, f);
    }

    public static final EnterTransition fadeIn(FiniteAnimationSpec<Float> animationSpec, float initialAlpha) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return new EnterTransitionImpl(new TransitionData(new Fade(initialAlpha, animationSpec), null, null, null, 14, null));
    }

    public static /* synthetic */ ExitTransition fadeOut$default(FiniteAnimationSpec finiteAnimationSpec, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        return fadeOut(finiteAnimationSpec, f);
    }

    public static final ExitTransition fadeOut(FiniteAnimationSpec<Float> animationSpec, float targetAlpha) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return new ExitTransitionImpl(new TransitionData(new Fade(targetAlpha, animationSpec), null, null, null, 14, null));
    }

    public static /* synthetic */ EnterTransition slideIn$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5383boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        return slideIn(finiteAnimationSpec, function1);
    }

    public static final EnterTransition slideIn(FiniteAnimationSpec<IntOffset> animationSpec, Function1<? super IntSize, IntOffset> initialOffset) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(initialOffset, "initialOffset");
        return new EnterTransitionImpl(new TransitionData(null, new Slide(initialOffset, animationSpec), null, null, 13, null));
    }

    public static /* synthetic */ ExitTransition slideOut$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5383boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        return slideOut(finiteAnimationSpec, function1);
    }

    public static final ExitTransition slideOut(FiniteAnimationSpec<IntOffset> animationSpec, Function1<? super IntSize, IntOffset> targetOffset) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(targetOffset, "targetOffset");
        return new ExitTransitionImpl(new TransitionData(null, new Slide(targetOffset, animationSpec), null, null, 13, null));
    }

    /* JADX INFO: renamed from: scaleIn-L8ZKh-E$default, reason: not valid java name */
    public static /* synthetic */ EnterTransition m43scaleInL8ZKhE$default(FiniteAnimationSpec finiteAnimationSpec, float f, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        if ((i & 4) != 0) {
            j = TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ();
        }
        return m42scaleInL8ZKhE(finiteAnimationSpec, f, j);
    }

    /* JADX INFO: renamed from: scaleIn-L8ZKh-E, reason: not valid java name */
    public static final EnterTransition m42scaleInL8ZKhE(FiniteAnimationSpec<Float> animationSpec, float initialScale, long transformOrigin) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return new EnterTransitionImpl(new TransitionData(null, null, null, new Scale(initialScale, transformOrigin, animationSpec, null), 7, null));
    }

    /* JADX INFO: renamed from: scaleOut-L8ZKh-E$default, reason: not valid java name */
    public static /* synthetic */ ExitTransition m45scaleOutL8ZKhE$default(FiniteAnimationSpec finiteAnimationSpec, float f, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        if ((i & 4) != 0) {
            j = TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ();
        }
        return m44scaleOutL8ZKhE(finiteAnimationSpec, f, j);
    }

    /* JADX INFO: renamed from: scaleOut-L8ZKh-E, reason: not valid java name */
    public static final ExitTransition m44scaleOutL8ZKhE(FiniteAnimationSpec<Float> animationSpec, float targetScale, long transformOrigin) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        return new ExitTransitionImpl(new TransitionData(null, null, null, new Scale(targetScale, transformOrigin, animationSpec, null), 7, null));
    }

    public static /* synthetic */ EnterTransition expandIn$default(FiniteAnimationSpec finiteAnimationSpec, Alignment alignment, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5426boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            alignment = Alignment.INSTANCE.getBottomEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandIn.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                    return IntSize.m5426boximpl(m49invokemzRDjE0(intSize.getPackedValue()));
                }

                /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
                public final long m49invokemzRDjE0(long it) {
                    return IntSizeKt.IntSize(0, 0);
                }
            };
        }
        return expandIn(finiteAnimationSpec, alignment, z, function1);
    }

    public static final EnterTransition expandIn(FiniteAnimationSpec<IntSize> animationSpec, Alignment expandFrom, boolean clip, Function1<? super IntSize, IntSize> initialSize) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(expandFrom, "expandFrom");
        Intrinsics.checkNotNullParameter(initialSize, "initialSize");
        return new EnterTransitionImpl(new TransitionData(null, null, new ChangeSize(expandFrom, initialSize, animationSpec, clip), null, 11, null));
    }

    public static /* synthetic */ ExitTransition shrinkOut$default(FiniteAnimationSpec finiteAnimationSpec, Alignment alignment, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5426boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            alignment = Alignment.INSTANCE.getBottomEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkOut.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                    return IntSize.m5426boximpl(m52invokemzRDjE0(intSize.getPackedValue()));
                }

                /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
                public final long m52invokemzRDjE0(long it) {
                    return IntSizeKt.IntSize(0, 0);
                }
            };
        }
        return shrinkOut(finiteAnimationSpec, alignment, z, function1);
    }

    public static final ExitTransition shrinkOut(FiniteAnimationSpec<IntSize> animationSpec, Alignment shrinkTowards, boolean clip, Function1<? super IntSize, IntSize> targetSize) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(shrinkTowards, "shrinkTowards");
        Intrinsics.checkNotNullParameter(targetSize, "targetSize");
        return new ExitTransitionImpl(new TransitionData(null, null, new ChangeSize(shrinkTowards, targetSize, animationSpec, clip), null, 11, null));
    }

    public static /* synthetic */ EnterTransition expandHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5426boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            horizontal = Alignment.INSTANCE.getEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandHorizontally.1
                public final Integer invoke(int it) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return expandHorizontally(finiteAnimationSpec, horizontal, z, function1);
    }

    public static final EnterTransition expandHorizontally(FiniteAnimationSpec<IntSize> animationSpec, Alignment.Horizontal expandFrom, boolean clip, final Function1<? super Integer, Integer> initialWidth) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(expandFrom, "expandFrom");
        Intrinsics.checkNotNullParameter(initialWidth, "initialWidth");
        return expandIn(animationSpec, toAlignment(expandFrom), clip, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m5426boximpl(m48invokemzRDjE0(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m48invokemzRDjE0(long it) {
                return IntSizeKt.IntSize(initialWidth.invoke(Integer.valueOf(IntSize.m5434getWidthimpl(it))).intValue(), IntSize.m5433getHeightimpl(it));
            }
        });
    }

    public static /* synthetic */ EnterTransition expandVertically$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5426boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            vertical = Alignment.INSTANCE.getBottom();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandVertically.1
                public final Integer invoke(int it) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return expandVertically(finiteAnimationSpec, vertical, z, function1);
    }

    public static final EnterTransition expandVertically(FiniteAnimationSpec<IntSize> animationSpec, Alignment.Vertical expandFrom, boolean clip, final Function1<? super Integer, Integer> initialHeight) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(expandFrom, "expandFrom");
        Intrinsics.checkNotNullParameter(initialHeight, "initialHeight");
        return expandIn(animationSpec, toAlignment(expandFrom), clip, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m5426boximpl(m50invokemzRDjE0(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m50invokemzRDjE0(long it) {
                return IntSizeKt.IntSize(IntSize.m5434getWidthimpl(it), initialHeight.invoke(Integer.valueOf(IntSize.m5433getHeightimpl(it))).intValue());
            }
        });
    }

    public static /* synthetic */ ExitTransition shrinkHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5426boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            horizontal = Alignment.INSTANCE.getEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkHorizontally.1
                public final Integer invoke(int it) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return shrinkHorizontally(finiteAnimationSpec, horizontal, z, function1);
    }

    public static final ExitTransition shrinkHorizontally(FiniteAnimationSpec<IntSize> animationSpec, Alignment.Horizontal shrinkTowards, boolean clip, final Function1<? super Integer, Integer> targetWidth) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(shrinkTowards, "shrinkTowards");
        Intrinsics.checkNotNullParameter(targetWidth, "targetWidth");
        return shrinkOut(animationSpec, toAlignment(shrinkTowards), clip, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m5426boximpl(m51invokemzRDjE0(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m51invokemzRDjE0(long it) {
                return IntSizeKt.IntSize(targetWidth.invoke(Integer.valueOf(IntSize.m5434getWidthimpl(it))).intValue(), IntSize.m5433getHeightimpl(it));
            }
        });
    }

    public static /* synthetic */ ExitTransition shrinkVertically$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5426boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            vertical = Alignment.INSTANCE.getBottom();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkVertically.1
                public final Integer invoke(int it) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return shrinkVertically(finiteAnimationSpec, vertical, z, function1);
    }

    public static final ExitTransition shrinkVertically(FiniteAnimationSpec<IntSize> animationSpec, Alignment.Vertical shrinkTowards, boolean clip, final Function1<? super Integer, Integer> targetHeight) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(shrinkTowards, "shrinkTowards");
        Intrinsics.checkNotNullParameter(targetHeight, "targetHeight");
        return shrinkOut(animationSpec, toAlignment(shrinkTowards), clip, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m5426boximpl(m53invokemzRDjE0(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m53invokemzRDjE0(long it) {
                return IntSizeKt.IntSize(IntSize.m5434getWidthimpl(it), targetHeight.invoke(Integer.valueOf(IntSize.m5433getHeightimpl(it))).intValue());
            }
        });
    }

    public static /* synthetic */ EnterTransition slideInHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5383boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInHorizontally.1
                public final Integer invoke(int it) {
                    return Integer.valueOf((-it) / 2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return slideInHorizontally(finiteAnimationSpec, function1);
    }

    public static final EnterTransition slideInHorizontally(FiniteAnimationSpec<IntOffset> animationSpec, final Function1<? super Integer, Integer> initialOffsetX) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(initialOffsetX, "initialOffsetX");
        return slideIn(animationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m5383boximpl(m54invokemHKZG7I(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m54invokemHKZG7I(long it) {
                return IntOffsetKt.IntOffset(initialOffsetX.invoke(Integer.valueOf(IntSize.m5434getWidthimpl(it))).intValue(), 0);
            }
        });
    }

    public static /* synthetic */ EnterTransition slideInVertically$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5383boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInVertically.1
                public final Integer invoke(int it) {
                    return Integer.valueOf((-it) / 2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return slideInVertically(finiteAnimationSpec, function1);
    }

    public static final EnterTransition slideInVertically(FiniteAnimationSpec<IntOffset> animationSpec, final Function1<? super Integer, Integer> initialOffsetY) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(initialOffsetY, "initialOffsetY");
        return slideIn(animationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m5383boximpl(m55invokemHKZG7I(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m55invokemHKZG7I(long it) {
                return IntOffsetKt.IntOffset(0, initialOffsetY.invoke(Integer.valueOf(IntSize.m5433getHeightimpl(it))).intValue());
            }
        });
    }

    public static /* synthetic */ ExitTransition slideOutHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5383boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutHorizontally.1
                public final Integer invoke(int it) {
                    return Integer.valueOf((-it) / 2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return slideOutHorizontally(finiteAnimationSpec, function1);
    }

    public static final ExitTransition slideOutHorizontally(FiniteAnimationSpec<IntOffset> animationSpec, final Function1<? super Integer, Integer> targetOffsetX) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(targetOffsetX, "targetOffsetX");
        return slideOut(animationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m5383boximpl(m56invokemHKZG7I(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m56invokemHKZG7I(long it) {
                return IntOffsetKt.IntOffset(targetOffsetX.invoke(Integer.valueOf(IntSize.m5434getWidthimpl(it))).intValue(), 0);
            }
        });
    }

    public static /* synthetic */ ExitTransition slideOutVertically$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5383boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutVertically.1
                public final Integer invoke(int it) {
                    return Integer.valueOf((-it) / 2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return slideOutVertically(finiteAnimationSpec, function1);
    }

    public static final ExitTransition slideOutVertically(FiniteAnimationSpec<IntOffset> animationSpec, final Function1<? super Integer, Integer> targetOffsetY) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(targetOffsetY, "targetOffsetY");
        return slideOut(animationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m5383boximpl(m57invokemHKZG7I(intSize.getPackedValue()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m57invokemHKZG7I(long it) {
                return IntOffsetKt.IntOffset(0, targetOffsetY.invoke(Integer.valueOf(IntSize.m5433getHeightimpl(it))).intValue());
            }
        });
    }

    private static final Alignment toAlignment(Alignment.Horizontal $this$toAlignment) {
        if (Intrinsics.areEqual($this$toAlignment, Alignment.INSTANCE.getStart())) {
            return Alignment.INSTANCE.getCenterStart();
        }
        return Intrinsics.areEqual($this$toAlignment, Alignment.INSTANCE.getEnd()) ? Alignment.INSTANCE.getCenterEnd() : Alignment.INSTANCE.getCenter();
    }

    private static final Alignment toAlignment(Alignment.Vertical $this$toAlignment) {
        if (Intrinsics.areEqual($this$toAlignment, Alignment.INSTANCE.getTop())) {
            return Alignment.INSTANCE.getTopCenter();
        }
        return Intrinsics.areEqual($this$toAlignment, Alignment.INSTANCE.getBottom()) ? Alignment.INSTANCE.getBottomCenter() : Alignment.INSTANCE.getCenter();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:125:0x04be  */
    /* JADX WARN: Code duplicated, block: B:128:0x050b  */
    /* JADX WARN: Code duplicated, block: B:130:0x0515 A[PHI: r0
      0x0515: PHI (r0v60 androidx.compose.animation.Scale) = (r0v57 androidx.compose.animation.Scale), (r0v59 androidx.compose.animation.Scale) binds: [B:129:0x0513, B:132:0x0526] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:131:0x051e  */
    /* JADX WARN: Code duplicated, block: B:135:0x052c  */
    /* JADX WARN: Code duplicated, block: B:137:0x0536 A[PHI: r0
      0x0536: PHI (r0v51 androidx.compose.animation.Scale) = (r0v48 androidx.compose.animation.Scale), (r0v50 androidx.compose.animation.Scale) binds: [B:136:0x0534, B:139:0x0547] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:138:0x053f  */
    /* JADX WARN: Code duplicated, block: B:144:0x0589  */
    /* JADX WARN: Code duplicated, block: B:145:0x0592  */
    /* JADX WARN: Code duplicated, block: B:148:0x059f  */
    /* JADX WARN: Code duplicated, block: B:150:0x05a5  */
    /* JADX WARN: Code duplicated, block: B:152:0x05af A[PHI: r6
      0x05af: PHI (r6v52 androidx.compose.animation.Scale) = (r6v48 androidx.compose.animation.Scale), (r6v50 androidx.compose.animation.Scale) binds: [B:151:0x05ad, B:154:0x05c0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:153:0x05b8  */
    /* JADX WARN: Code duplicated, block: B:157:0x05c6  */
    /* JADX WARN: Code duplicated, block: B:159:0x05d0 A[PHI: r6
      0x05d0: PHI (r6v32 androidx.compose.animation.Scale) = (r6v28 androidx.compose.animation.Scale), (r6v30 androidx.compose.animation.Scale) binds: [B:158:0x05ce, B:161:0x05e1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:160:0x05d9  */
    /* JADX WARN: Code duplicated, block: B:164:0x05e7  */
    /* JADX WARN: Code duplicated, block: B:166:0x05eb  */
    /* JADX WARN: Code duplicated, block: B:167:0x05f0  */
    /* JADX WARN: Code duplicated, block: B:170:0x05fc  */
    /* JADX WARN: Code duplicated, block: B:173:0x0620  */
    /* JADX WARN: Code duplicated, block: B:174:0x0629  */
    /* JADX WARN: Code duplicated, block: B:177:0x0636  */
    /* JADX WARN: Code duplicated, block: B:179:0x063c  */
    /* JADX WARN: Code duplicated, block: B:181:0x0646 A[PHI: r7
      0x0646: PHI (r7v30 androidx.compose.animation.Scale) = (r7v26 androidx.compose.animation.Scale), (r7v28 androidx.compose.animation.Scale) binds: [B:180:0x0644, B:183:0x0657] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:182:0x064f  */
    /* JADX WARN: Code duplicated, block: B:186:0x065d  */
    /* JADX WARN: Code duplicated, block: B:188:0x0667 A[PHI: r7
      0x0667: PHI (r7v16 androidx.compose.animation.Scale) = (r7v12 androidx.compose.animation.Scale), (r7v14 androidx.compose.animation.Scale) binds: [B:187:0x0665, B:190:0x0678] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:189:0x0670  */
    /* JADX WARN: Code duplicated, block: B:193:0x067e  */
    /* JADX WARN: Code duplicated, block: B:195:0x0682  */
    /* JADX WARN: Code duplicated, block: B:196:0x0687  */
    /* JADX WARN: Code duplicated, block: B:199:0x0693  */
    /* JADX WARN: Code duplicated, block: B:202:0x06ee  */
    /* JADX WARN: Code duplicated, block: B:206:0x06f9  */
    public static final Modifier createModifier(Transition<EnterExitState> transition, final EnterTransition enter, final ExitTransition exit, String label, Composer $composer, int $changed) {
        Object value$iv$iv;
        TransformOrigin transformOrigin;
        Object value$iv$iv2;
        String str;
        String str2;
        String str3;
        TransformOrigin transformOrigin2;
        Modifier modifier;
        MutableFloatState mutableFloatStateCreateTransitionAnimation;
        Modifier modifier2;
        Object value$iv$iv3;
        Object value$iv$iv4;
        float scale;
        final State scale$delegate;
        Scale scale2;
        TransformOrigin transformOriginM3349boximpl;
        TransformOrigin transformOriginWhenVisible;
        int $changed2;
        EnterExitState it;
        TransformOrigin transformOriginM3349boximpl2;
        Scale scale3;
        long jM3362getCenterSzJe1aQ;
        int $changed3;
        EnterExitState it2;
        TransformOrigin transformOriginM3349boximpl3;
        Scale scale4;
        long jM3362getCenterSzJe1aQ2;
        final State transformOrigin$delegate;
        boolean invalid$iv$iv;
        Object value$iv$iv5;
        Scale scale5;
        Scale scale6;
        Scale scale7;
        float alpha;
        float alpha2;
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(enter, "enter");
        Intrinsics.checkNotNullParameter(exit, "exit");
        Intrinsics.checkNotNullParameter(label, "label");
        $composer.startReplaceableGroup(914000546);
        ComposerKt.sourceInformation($composer, "C(createModifier)831@36300L38,832@36348L37,836@36443L43,837@36496L42,844@36867L40,845@36938L40:EnterExitTransition.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(914000546, $changed, -1, "androidx.compose.animation.createModifier (EnterExitTransition.kt:819)");
        }
        Modifier modifier3 = shrinkExpand(slideInOut(Modifier.INSTANCE, transition, SnapshotStateKt.rememberUpdatedState(enter.getData().getSlide(), $composer, 0), SnapshotStateKt.rememberUpdatedState(exit.getData().getSlide(), $composer, 0), label), transition, SnapshotStateKt.rememberUpdatedState(enter.getData().getChangeSize(), $composer, 0), SnapshotStateKt.rememberUpdatedState(exit.getData().getChangeSize(), $composer, 0), label);
        int i = $changed & 14;
        $composer.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean invalid$iv$iv2 = $composer.changed(transition);
        Object it$iv$iv = $composer.rememberedValue();
        if (invalid$iv$iv2 || it$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        $composer.endReplaceableGroup();
        MutableState shouldAnimateAlpha$delegate = (MutableState) value$iv$iv;
        int i2 = $changed & 14;
        $composer.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean invalid$iv$iv3 = $composer.changed(transition);
        Object it$iv$iv2 = $composer.rememberedValue();
        if (invalid$iv$iv3 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
            transformOrigin = null;
            value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            $composer.updateRememberedValue(value$iv$iv2);
        } else {
            value$iv$iv2 = it$iv$iv2;
            transformOrigin = null;
        }
        $composer.endReplaceableGroup();
        MutableState shouldAnimateScale$delegate = (MutableState) value$iv$iv2;
        if (transition.getCurrentState() != transition.getTargetState() || transition.isSeeking()) {
            if (enter.getData().getFade() != null || exit.getData().getFade() != null) {
                createModifier$lambda$2(shouldAnimateAlpha$delegate, true);
            }
            if (enter.getData().getScale() != null || exit.getData().getScale() != null) {
                createModifier$lambda$5(shouldAnimateScale$delegate, true);
            }
        } else {
            createModifier$lambda$2(shouldAnimateAlpha$delegate, false);
            createModifier$lambda$5(shouldAnimateScale$delegate, false);
        }
        $composer.startReplaceableGroup(1657241561);
        ComposerKt.sourceInformation($composer, "869@37922L27,859@37401L796");
        float scale8 = 1.0f;
        if (createModifier$lambda$1(shouldAnimateAlpha$delegate)) {
            Function3<Transition.Segment<EnterExitState>, Composer, Integer, FiniteAnimationSpec<Float>> function3 = new Function3<Transition.Segment<EnterExitState>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$alpha$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<EnterExitState> segment, Composer composer, Integer num) {
                    return invoke(segment, composer, num.intValue());
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<EnterExitState> animateFloat, Composer $composer2, int $changed4) {
                    SpringSpec animationSpec;
                    Fade fade;
                    Intrinsics.checkNotNullParameter(animateFloat, "$this$animateFloat");
                    $composer2.startReplaceableGroup(-57153604);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-57153604, $changed4, -1, "androidx.compose.animation.createModifier.<anonymous> (EnterExitTransition.kt:860)");
                    }
                    if (animateFloat.isTransitioningTo(EnterExitState.PreEnter, EnterExitState.Visible)) {
                        Fade fade2 = enter.getData().getFade();
                        if (fade2 == null || (animationSpec = fade2.getAnimationSpec()) == null) {
                            animationSpec = EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                        }
                    } else if (!animateFloat.isTransitioningTo(EnterExitState.Visible, EnterExitState.PostExit) || (fade = exit.getData().getFade()) == null || (animationSpec = fade.getAnimationSpec()) == null) {
                        animationSpec = EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer2.endReplaceableGroup();
                    return animationSpec;
                }
            };
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object value$iv$iv6 = $composer.rememberedValue();
            if (value$iv$iv6 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv6 = label + " alpha";
                $composer.updateRememberedValue(value$iv$iv6);
            }
            $composer.endReplaceableGroup();
            String label$iv = (String) value$iv$iv6;
            int $changed$iv = ($changed & 14) | 384;
            $composer.startReplaceableGroup(-1338768149);
            ComposerKt.sourceInformation($composer, "CC(animateFloat)P(2)939@37552L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv = (($changed$iv << 3) & 57344) | ($changed$iv & 14) | (($changed$iv << 3) & 896) | (($changed$iv << 3) & 7168);
            $composer.startReplaceableGroup(-142660079);
            ComposerKt.sourceInformation($composer, "CC(animateValue)P(3,2)857@34142L32,858@34197L31,859@34253L23,861@34289L89:Transition.kt#pdpnli");
            int $changed4 = ($changed$iv$iv >> 9) & 112;
            EnterExitState it3 = transition.getCurrentState();
            $composer.startReplaceableGroup(755689166);
            ComposerKt.sourceInformation($composer, "C:EnterExitTransition.kt#xbi5r1");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(755689166, $changed4, -1, "androidx.compose.animation.createModifier.<anonymous> (EnterExitTransition.kt:870)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[it3.ordinal()]) {
                case 1:
                    alpha = 1.0f;
                    break;
                case 2:
                    Fade fade = enter.getData().getFade();
                    alpha = fade == null ? 1.0f : fade.getAlpha();
                    break;
                case 3:
                    Fade fade2 = exit.getData().getFade();
                    alpha = fade2 == null ? 1.0f : fade2.getAlpha();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceableGroup();
            Object initialValue$iv$iv = Float.valueOf(alpha);
            str = "C:EnterExitTransition.kt#xbi5r1";
            int $changed5 = ($changed$iv$iv >> 9) & 112;
            EnterExitState it4 = transition.getTargetState();
            $composer.startReplaceableGroup(755689166);
            ComposerKt.sourceInformation($composer, str);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(755689166, $changed5, -1, "androidx.compose.animation.createModifier.<anonymous> (EnterExitTransition.kt:870)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[it4.ordinal()]) {
                case 1:
                    alpha2 = 1.0f;
                    break;
                case 2:
                    Fade fade3 = enter.getData().getFade();
                    alpha2 = fade3 == null ? 1.0f : fade3.getAlpha();
                    break;
                case 3:
                    Fade fade4 = exit.getData().getFade();
                    alpha2 = fade4 == null ? 1.0f : fade4.getAlpha();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceableGroup();
            Object targetValue$iv$iv = Float.valueOf(alpha2);
            str2 = "CC(animateValue)P(3,2)857@34142L32,858@34197L31,859@34253L23,861@34289L89:Transition.kt#pdpnli";
            str3 = "CC(remember)P(1):Composables.kt#9igjgp";
            transformOrigin2 = null;
            modifier = modifier3;
            mutableFloatStateCreateTransitionAnimation = androidx.compose.animation.core.TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv, targetValue$iv$iv, function3.invoke(transition.getSegment(), $composer, Integer.valueOf(($changed$iv$iv >> 3) & 112)), vectorConverter, label$iv, $composer, ($changed$iv$iv & 14) | (($changed$iv$iv << 9) & 57344) | (($changed$iv$iv << 6) & 458752));
            $composer.endReplaceableGroup();
            $composer.endReplaceableGroup();
        } else {
            str = "C:EnterExitTransition.kt#xbi5r1";
            str2 = "CC(animateValue)P(3,2)857@34142L32,858@34197L31,859@34253L23,861@34289L89:Transition.kt#pdpnli";
            str3 = "CC(remember)P(1):Composables.kt#9igjgp";
            transformOrigin2 = transformOrigin;
            modifier = modifier3;
            shouldAnimateAlpha$delegate = shouldAnimateAlpha$delegate;
            mutableFloatStateCreateTransitionAnimation = DefaultAlpha;
        }
        $composer.endReplaceableGroup();
        final State alpha$delegate = mutableFloatStateCreateTransitionAnimation;
        if (createModifier$lambda$4(shouldAnimateScale$delegate)) {
            $composer.startReplaceableGroup(1657242461);
            ComposerKt.sourceInformation($composer, "892@38813L27,882@38290L800,908@39583L536,921@40163L157");
            Function3<Transition.Segment<EnterExitState>, Composer, Integer, FiniteAnimationSpec<Float>> function4 = new Function3<Transition.Segment<EnterExitState>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$scale$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<EnterExitState> segment, Composer composer, Integer num) {
                    return invoke(segment, composer, num.intValue());
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<EnterExitState> animateFloat, Composer $composer2, int $changed6) {
                    SpringSpec animationSpec;
                    Scale scale9;
                    Intrinsics.checkNotNullParameter(animateFloat, "$this$animateFloat");
                    $composer2.startReplaceableGroup(-53984035);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-53984035, $changed6, -1, "androidx.compose.animation.createModifier.<anonymous> (EnterExitTransition.kt:883)");
                    }
                    if (animateFloat.isTransitioningTo(EnterExitState.PreEnter, EnterExitState.Visible)) {
                        Scale scale10 = enter.getData().getScale();
                        if (scale10 == null || (animationSpec = scale10.getAnimationSpec()) == null) {
                            animationSpec = EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                        }
                    } else if (!animateFloat.isTransitioningTo(EnterExitState.Visible, EnterExitState.PostExit) || (scale9 = exit.getData().getScale()) == null || (animationSpec = scale9.getAnimationSpec()) == null) {
                        animationSpec = EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer2.endReplaceableGroup();
                    return animationSpec;
                }
            };
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv3 = $composer.rememberedValue();
            if (it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv4 = label + " scale";
                $composer.updateRememberedValue(value$iv$iv4);
            } else {
                value$iv$iv4 = it$iv$iv3;
            }
            $composer.endReplaceableGroup();
            String label$iv2 = (String) value$iv$iv4;
            int $changed$iv2 = ($changed & 14) | 384;
            $composer.startReplaceableGroup(-1338768149);
            ComposerKt.sourceInformation($composer, "CC(animateFloat)P(2)939@37552L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv2 = (($changed$iv2 << 3) & 57344) | ($changed$iv2 & 14) | (($changed$iv2 << 3) & 896) | (($changed$iv2 << 3) & 7168);
            $composer.startReplaceableGroup(-142660079);
            String str4 = str2;
            ComposerKt.sourceInformation($composer, str4);
            int $changed6 = ($changed$iv$iv2 >> 9) & 112;
            EnterExitState it5 = transition.getCurrentState();
            $composer.startReplaceableGroup(-596129937);
            String str5 = str;
            ComposerKt.sourceInformation($composer, str5);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-596129937, $changed6, -1, "androidx.compose.animation.createModifier.<anonymous> (EnterExitTransition.kt:893)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[it5.ordinal()]) {
                case 1:
                    scale = 1.0f;
                    break;
                case 2:
                    Scale scale9 = enter.getData().getScale();
                    scale = scale9 == null ? 1.0f : scale9.getScale();
                    break;
                case 3:
                    Scale scale10 = exit.getData().getScale();
                    scale = scale10 == null ? 1.0f : scale10.getScale();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceableGroup();
            Object initialValue$iv$iv2 = Float.valueOf(scale);
            int $changed7 = ($changed$iv$iv2 >> 9) & 112;
            EnterExitState it6 = transition.getTargetState();
            $composer.startReplaceableGroup(-596129937);
            ComposerKt.sourceInformation($composer, str5);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-596129937, $changed7, -1, "androidx.compose.animation.createModifier.<anonymous> (EnterExitTransition.kt:893)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[it6.ordinal()]) {
                case 1:
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceableGroup();
                    Object targetValue$iv$iv2 = Float.valueOf(scale8);
                    scale$delegate = androidx.compose.animation.core.TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv2, targetValue$iv$iv2, function4.invoke(transition.getSegment(), $composer, Integer.valueOf(($changed$iv$iv2 >> 3) & 112)), vectorConverter2, label$iv2, $composer, ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344) | (($changed$iv$iv2 << 6) & 458752));
                    $composer.endReplaceableGroup();
                    $composer.endReplaceableGroup();
                    if (transition.getCurrentState() == EnterExitState.PreEnter) {
                        scale7 = enter.getData().getScale();
                        if (scale7 != null && (scale7 = exit.getData().getScale()) == null) {
                            transformOriginM3349boximpl = transformOrigin2;
                        } else {
                            transformOriginM3349boximpl = TransformOrigin.m3349boximpl(scale7.m65getTransformOriginSzJe1aQ());
                        }
                    } else {
                        scale2 = exit.getData().getScale();
                        if (scale2 != null && (scale2 = enter.getData().getScale()) == null) {
                            transformOriginM3349boximpl = transformOrigin2;
                        } else {
                            transformOriginM3349boximpl = TransformOrigin.m3349boximpl(scale2.m65getTransformOriginSzJe1aQ());
                        }
                    }
                    transformOriginWhenVisible = transformOriginM3349boximpl;
                    TwoWayConverter<TransformOrigin, AnimationVector2D> twoWayConverter = TransformOriginVectorConverter;
                    int $changed$iv3 = ($changed & 14) | 3136;
                    $composer.startReplaceableGroup(-142660079);
                    ComposerKt.sourceInformation($composer, str4);
                    Function3 transitionSpec$iv = new Function3<Transition.Segment<EnterExitState>, Composer, Integer, SpringSpec<TransformOrigin>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$$inlined$animateValue$1
                        public final SpringSpec<TransformOrigin> invoke(Transition.Segment<EnterExitState> segment, Composer $composer2, int $changed8) {
                            Intrinsics.checkNotNullParameter(segment, "$this$null");
                            $composer2.startReplaceableGroup(-895531546);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-895531546, $changed8, -1, "androidx.compose.animation.core.animateValue.<anonymous> (Transition.kt:852)");
                            }
                            SpringSpec<TransformOrigin> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            $composer2.endReplaceableGroup();
                            return springSpecSpring$default;
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ SpringSpec<TransformOrigin> invoke(Transition.Segment<EnterExitState> segment, Composer composer, Integer num) {
                            return invoke(segment, composer, num.intValue());
                        }
                    };
                    $changed2 = ($changed$iv3 >> 9) & 112;
                    it = transition.getCurrentState();
                    $composer.startReplaceableGroup(-288165413);
                    ComposerKt.sourceInformation($composer, str5);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-288165413, $changed2, -1, "androidx.compose.animation.createModifier.<anonymous> (EnterExitTransition.kt:911)");
                    }
                    switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                        case 1:
                            transformOriginM3349boximpl2 = transformOriginWhenVisible;
                            break;
                        case 2:
                            scale3 = enter.getData().getScale();
                            if (scale3 == null || (scale3 = exit.getData().getScale()) != null) {
                                transformOriginM3349boximpl2 = TransformOrigin.m3349boximpl(scale3.m65getTransformOriginSzJe1aQ());
                            } else {
                                transformOriginM3349boximpl2 = transformOrigin2;
                            }
                            break;
                        case 3:
                            scale6 = exit.getData().getScale();
                            if (scale6 == null || (scale6 = enter.getData().getScale()) != null) {
                                transformOriginM3349boximpl2 = TransformOrigin.m3349boximpl(scale6.m65getTransformOriginSzJe1aQ());
                            } else {
                                transformOriginM3349boximpl2 = transformOrigin2;
                            }
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                    if (transformOriginM3349boximpl2 != null) {
                        jM3362getCenterSzJe1aQ = transformOriginM3349boximpl2.getPackedValue();
                    } else {
                        jM3362getCenterSzJe1aQ = TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceableGroup();
                    Object initialValue$iv = TransformOrigin.m3349boximpl(jM3362getCenterSzJe1aQ);
                    $changed3 = ($changed$iv3 >> 9) & 112;
                    it2 = transition.getTargetState();
                    $composer.startReplaceableGroup(-288165413);
                    ComposerKt.sourceInformation($composer, str5);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-288165413, $changed3, -1, "androidx.compose.animation.createModifier.<anonymous> (EnterExitTransition.kt:911)");
                    }
                    switch (WhenMappings.$EnumSwitchMapping$0[it2.ordinal()]) {
                        case 1:
                            transformOriginM3349boximpl3 = transformOriginWhenVisible;
                            break;
                        case 2:
                            scale4 = enter.getData().getScale();
                            if (scale4 == null || (scale4 = exit.getData().getScale()) != null) {
                                transformOriginM3349boximpl3 = TransformOrigin.m3349boximpl(scale4.m65getTransformOriginSzJe1aQ());
                            } else {
                                transformOriginM3349boximpl3 = transformOrigin2;
                            }
                            break;
                        case 3:
                            scale5 = exit.getData().getScale();
                            if (scale5 == null || (scale5 = enter.getData().getScale()) != null) {
                                transformOriginM3349boximpl3 = TransformOrigin.m3349boximpl(scale5.m65getTransformOriginSzJe1aQ());
                            } else {
                                transformOriginM3349boximpl3 = transformOrigin2;
                            }
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                    if (transformOriginM3349boximpl3 != null) {
                        jM3362getCenterSzJe1aQ2 = transformOriginM3349boximpl3.getPackedValue();
                    } else {
                        jM3362getCenterSzJe1aQ2 = TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceableGroup();
                    Object targetValue$iv = TransformOrigin.m3349boximpl(jM3362getCenterSzJe1aQ2);
                    SpringSpec<TransformOrigin> animationSpec$iv = transitionSpec$iv.invoke(transition.getSegment(), $composer, Integer.valueOf(($changed$iv3 >> 3) & 112));
                    transformOrigin$delegate = androidx.compose.animation.core.TransitionKt.createTransitionAnimation(transition, initialValue$iv, targetValue$iv, animationSpec$iv, twoWayConverter, "TransformOriginInterruptionHandling", $composer, ($changed$iv3 & 14) | (($changed$iv3 << 9) & 57344) | (($changed$iv3 << 6) & 458752));
                    $composer.endReplaceableGroup();
                    $composer.startReplaceableGroup(1618982084);
                    ComposerKt.sourceInformation($composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer.changed(alpha$delegate) | $composer.changed(scale$delegate) | $composer.changed(transformOrigin$delegate);
                    Object it$iv$iv4 = $composer.rememberedValue();
                    if (!invalid$iv$iv || it$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv5 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$1$1
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
                                graphicsLayer.setAlpha(EnterExitTransitionKt.createModifier$lambda$8(alpha$delegate));
                                graphicsLayer.setScaleX(EnterExitTransitionKt.createModifier$lambda$11(scale$delegate));
                                graphicsLayer.setScaleY(EnterExitTransitionKt.createModifier$lambda$11(scale$delegate));
                                graphicsLayer.mo3140setTransformOrigin__ExYCQ(EnterExitTransitionKt.createModifier$lambda$13(transformOrigin$delegate));
                            }
                        };
                        $composer.updateRememberedValue(value$iv$iv5);
                    } else {
                        value$iv$iv5 = it$iv$iv4;
                    }
                    $composer.endReplaceableGroup();
                    modifier2 = GraphicsLayerModifierKt.graphicsLayer(modifier, (Function1) value$iv$iv5);
                    $composer.endReplaceableGroup();
                    break;
                case 2:
                    Scale scale11 = enter.getData().getScale();
                    if (scale11 != null) {
                        scale8 = scale11.getScale();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceableGroup();
                    Object targetValue$iv$iv3 = Float.valueOf(scale8);
                    scale$delegate = androidx.compose.animation.core.TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv2, targetValue$iv$iv3, function4.invoke(transition.getSegment(), $composer, Integer.valueOf(($changed$iv$iv2 >> 3) & 112)), vectorConverter2, label$iv2, $composer, ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344) | (($changed$iv$iv2 << 6) & 458752));
                    $composer.endReplaceableGroup();
                    $composer.endReplaceableGroup();
                    if (transition.getCurrentState() == EnterExitState.PreEnter) {
                        scale7 = enter.getData().getScale();
                        if (scale7 != null) {
                            transformOriginM3349boximpl = TransformOrigin.m3349boximpl(scale7.m65getTransformOriginSzJe1aQ());
                        } else {
                            transformOriginM3349boximpl = transformOrigin2;
                        }
                    } else {
                        scale2 = exit.getData().getScale();
                        if (scale2 != null) {
                            transformOriginM3349boximpl = TransformOrigin.m3349boximpl(scale2.m65getTransformOriginSzJe1aQ());
                        } else {
                            transformOriginM3349boximpl = transformOrigin2;
                        }
                    }
                    transformOriginWhenVisible = transformOriginM3349boximpl;
                    TwoWayConverter<TransformOrigin, AnimationVector2D> twoWayConverter2 = TransformOriginVectorConverter;
                    int $changed$iv4 = ($changed & 14) | 3136;
                    $composer.startReplaceableGroup(-142660079);
                    ComposerKt.sourceInformation($composer, str4);
                    Function3 transitionSpec$iv2 = new Function3<Transition.Segment<EnterExitState>, Composer, Integer, SpringSpec<TransformOrigin>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$$inlined$animateValue$1
                        public final SpringSpec<TransformOrigin> invoke(Transition.Segment<EnterExitState> segment, Composer $composer2, int $changed8) {
                            Intrinsics.checkNotNullParameter(segment, "$this$null");
                            $composer2.startReplaceableGroup(-895531546);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-895531546, $changed8, -1, "androidx.compose.animation.core.animateValue.<anonymous> (Transition.kt:852)");
                            }
                            SpringSpec<TransformOrigin> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            $composer2.endReplaceableGroup();
                            return springSpecSpring$default;
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ SpringSpec<TransformOrigin> invoke(Transition.Segment<EnterExitState> segment, Composer composer, Integer num) {
                            return invoke(segment, composer, num.intValue());
                        }
                    };
                    $changed2 = ($changed$iv4 >> 9) & 112;
                    it = transition.getCurrentState();
                    $composer.startReplaceableGroup(-288165413);
                    ComposerKt.sourceInformation($composer, str5);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-288165413, $changed2, -1, "androidx.compose.animation.createModifier.<anonymous> (EnterExitTransition.kt:911)");
                    }
                    switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                        case 1:
                            transformOriginM3349boximpl2 = transformOriginWhenVisible;
                            break;
                        case 2:
                            scale3 = enter.getData().getScale();
                            if (scale3 == null) {
                                transformOriginM3349boximpl2 = transformOrigin2;
                            } else {
                                transformOriginM3349boximpl2 = TransformOrigin.m3349boximpl(scale3.m65getTransformOriginSzJe1aQ());
                            }
                            break;
                        case 3:
                            scale6 = exit.getData().getScale();
                            if (scale6 == null) {
                                transformOriginM3349boximpl2 = transformOrigin2;
                            } else {
                                transformOriginM3349boximpl2 = TransformOrigin.m3349boximpl(scale6.m65getTransformOriginSzJe1aQ());
                            }
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                    if (transformOriginM3349boximpl2 != null) {
                        jM3362getCenterSzJe1aQ = transformOriginM3349boximpl2.getPackedValue();
                    } else {
                        jM3362getCenterSzJe1aQ = TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceableGroup();
                    Object initialValue$iv2 = TransformOrigin.m3349boximpl(jM3362getCenterSzJe1aQ);
                    $changed3 = ($changed$iv4 >> 9) & 112;
                    it2 = transition.getTargetState();
                    $composer.startReplaceableGroup(-288165413);
                    ComposerKt.sourceInformation($composer, str5);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-288165413, $changed3, -1, "androidx.compose.animation.createModifier.<anonymous> (EnterExitTransition.kt:911)");
                    }
                    switch (WhenMappings.$EnumSwitchMapping$0[it2.ordinal()]) {
                        case 1:
                            transformOriginM3349boximpl3 = transformOriginWhenVisible;
                            break;
                        case 2:
                            scale4 = enter.getData().getScale();
                            if (scale4 == null) {
                                transformOriginM3349boximpl3 = transformOrigin2;
                            } else {
                                transformOriginM3349boximpl3 = TransformOrigin.m3349boximpl(scale4.m65getTransformOriginSzJe1aQ());
                            }
                            break;
                        case 3:
                            scale5 = exit.getData().getScale();
                            if (scale5 == null) {
                                transformOriginM3349boximpl3 = transformOrigin2;
                            } else {
                                transformOriginM3349boximpl3 = TransformOrigin.m3349boximpl(scale5.m65getTransformOriginSzJe1aQ());
                            }
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                    if (transformOriginM3349boximpl3 != null) {
                        jM3362getCenterSzJe1aQ2 = transformOriginM3349boximpl3.getPackedValue();
                    } else {
                        jM3362getCenterSzJe1aQ2 = TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceableGroup();
                    Object targetValue$iv2 = TransformOrigin.m3349boximpl(jM3362getCenterSzJe1aQ2);
                    SpringSpec<TransformOrigin> animationSpec$iv2 = transitionSpec$iv2.invoke(transition.getSegment(), $composer, Integer.valueOf(($changed$iv4 >> 3) & 112));
                    transformOrigin$delegate = androidx.compose.animation.core.TransitionKt.createTransitionAnimation(transition, initialValue$iv2, targetValue$iv2, animationSpec$iv2, twoWayConverter2, "TransformOriginInterruptionHandling", $composer, ($changed$iv4 & 14) | (($changed$iv4 << 9) & 57344) | (($changed$iv4 << 6) & 458752));
                    $composer.endReplaceableGroup();
                    $composer.startReplaceableGroup(1618982084);
                    ComposerKt.sourceInformation($composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer.changed(alpha$delegate) | $composer.changed(scale$delegate) | $composer.changed(transformOrigin$delegate);
                    Object it$iv$iv5 = $composer.rememberedValue();
                    if (invalid$iv$iv) {
                        value$iv$iv5 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$1$1
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
                                graphicsLayer.setAlpha(EnterExitTransitionKt.createModifier$lambda$8(alpha$delegate));
                                graphicsLayer.setScaleX(EnterExitTransitionKt.createModifier$lambda$11(scale$delegate));
                                graphicsLayer.setScaleY(EnterExitTransitionKt.createModifier$lambda$11(scale$delegate));
                                graphicsLayer.mo3140setTransformOrigin__ExYCQ(EnterExitTransitionKt.createModifier$lambda$13(transformOrigin$delegate));
                            }
                        };
                        $composer.updateRememberedValue(value$iv$iv5);
                    } else {
                        value$iv$iv5 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$1$1
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
                                graphicsLayer.setAlpha(EnterExitTransitionKt.createModifier$lambda$8(alpha$delegate));
                                graphicsLayer.setScaleX(EnterExitTransitionKt.createModifier$lambda$11(scale$delegate));
                                graphicsLayer.setScaleY(EnterExitTransitionKt.createModifier$lambda$11(scale$delegate));
                                graphicsLayer.mo3140setTransformOrigin__ExYCQ(EnterExitTransitionKt.createModifier$lambda$13(transformOrigin$delegate));
                            }
                        };
                        $composer.updateRememberedValue(value$iv$iv5);
                    }
                    $composer.endReplaceableGroup();
                    modifier2 = GraphicsLayerModifierKt.graphicsLayer(modifier, (Function1) value$iv$iv5);
                    $composer.endReplaceableGroup();
                    break;
                case 3:
                    Scale scale12 = exit.getData().getScale();
                    if (scale12 != null) {
                        scale8 = scale12.getScale();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceableGroup();
                    Object targetValue$iv$iv4 = Float.valueOf(scale8);
                    scale$delegate = androidx.compose.animation.core.TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv2, targetValue$iv$iv4, function4.invoke(transition.getSegment(), $composer, Integer.valueOf(($changed$iv$iv2 >> 3) & 112)), vectorConverter2, label$iv2, $composer, ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344) | (($changed$iv$iv2 << 6) & 458752));
                    $composer.endReplaceableGroup();
                    $composer.endReplaceableGroup();
                    if (transition.getCurrentState() == EnterExitState.PreEnter) {
                        scale7 = enter.getData().getScale();
                        if (scale7 != null) {
                            transformOriginM3349boximpl = TransformOrigin.m3349boximpl(scale7.m65getTransformOriginSzJe1aQ());
                        } else {
                            transformOriginM3349boximpl = transformOrigin2;
                        }
                    } else {
                        scale2 = exit.getData().getScale();
                        if (scale2 != null) {
                            transformOriginM3349boximpl = TransformOrigin.m3349boximpl(scale2.m65getTransformOriginSzJe1aQ());
                        } else {
                            transformOriginM3349boximpl = transformOrigin2;
                        }
                    }
                    transformOriginWhenVisible = transformOriginM3349boximpl;
                    TwoWayConverter<TransformOrigin, AnimationVector2D> twoWayConverter3 = TransformOriginVectorConverter;
                    int $changed$iv5 = ($changed & 14) | 3136;
                    $composer.startReplaceableGroup(-142660079);
                    ComposerKt.sourceInformation($composer, str4);
                    Function3 transitionSpec$iv3 = new Function3<Transition.Segment<EnterExitState>, Composer, Integer, SpringSpec<TransformOrigin>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$$inlined$animateValue$1
                        public final SpringSpec<TransformOrigin> invoke(Transition.Segment<EnterExitState> segment, Composer $composer2, int $changed8) {
                            Intrinsics.checkNotNullParameter(segment, "$this$null");
                            $composer2.startReplaceableGroup(-895531546);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-895531546, $changed8, -1, "androidx.compose.animation.core.animateValue.<anonymous> (Transition.kt:852)");
                            }
                            SpringSpec<TransformOrigin> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            $composer2.endReplaceableGroup();
                            return springSpecSpring$default;
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ SpringSpec<TransformOrigin> invoke(Transition.Segment<EnterExitState> segment, Composer composer, Integer num) {
                            return invoke(segment, composer, num.intValue());
                        }
                    };
                    $changed2 = ($changed$iv5 >> 9) & 112;
                    it = transition.getCurrentState();
                    $composer.startReplaceableGroup(-288165413);
                    ComposerKt.sourceInformation($composer, str5);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-288165413, $changed2, -1, "androidx.compose.animation.createModifier.<anonymous> (EnterExitTransition.kt:911)");
                    }
                    switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                        case 1:
                            transformOriginM3349boximpl2 = transformOriginWhenVisible;
                            break;
                        case 2:
                            scale3 = enter.getData().getScale();
                            if (scale3 == null) {
                                transformOriginM3349boximpl2 = transformOrigin2;
                            } else {
                                transformOriginM3349boximpl2 = TransformOrigin.m3349boximpl(scale3.m65getTransformOriginSzJe1aQ());
                            }
                            break;
                        case 3:
                            scale6 = exit.getData().getScale();
                            if (scale6 == null) {
                                transformOriginM3349boximpl2 = transformOrigin2;
                            } else {
                                transformOriginM3349boximpl2 = TransformOrigin.m3349boximpl(scale6.m65getTransformOriginSzJe1aQ());
                            }
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                    if (transformOriginM3349boximpl2 != null) {
                        jM3362getCenterSzJe1aQ = transformOriginM3349boximpl2.getPackedValue();
                    } else {
                        jM3362getCenterSzJe1aQ = TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceableGroup();
                    Object initialValue$iv3 = TransformOrigin.m3349boximpl(jM3362getCenterSzJe1aQ);
                    $changed3 = ($changed$iv5 >> 9) & 112;
                    it2 = transition.getTargetState();
                    $composer.startReplaceableGroup(-288165413);
                    ComposerKt.sourceInformation($composer, str5);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-288165413, $changed3, -1, "androidx.compose.animation.createModifier.<anonymous> (EnterExitTransition.kt:911)");
                    }
                    switch (WhenMappings.$EnumSwitchMapping$0[it2.ordinal()]) {
                        case 1:
                            transformOriginM3349boximpl3 = transformOriginWhenVisible;
                            break;
                        case 2:
                            scale4 = enter.getData().getScale();
                            if (scale4 == null) {
                                transformOriginM3349boximpl3 = transformOrigin2;
                            } else {
                                transformOriginM3349boximpl3 = TransformOrigin.m3349boximpl(scale4.m65getTransformOriginSzJe1aQ());
                            }
                            break;
                        case 3:
                            scale5 = exit.getData().getScale();
                            if (scale5 == null) {
                                transformOriginM3349boximpl3 = transformOrigin2;
                            } else {
                                transformOriginM3349boximpl3 = TransformOrigin.m3349boximpl(scale5.m65getTransformOriginSzJe1aQ());
                            }
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                    if (transformOriginM3349boximpl3 != null) {
                        jM3362getCenterSzJe1aQ2 = transformOriginM3349boximpl3.getPackedValue();
                    } else {
                        jM3362getCenterSzJe1aQ2 = TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceableGroup();
                    Object targetValue$iv3 = TransformOrigin.m3349boximpl(jM3362getCenterSzJe1aQ2);
                    SpringSpec<TransformOrigin> animationSpec$iv3 = transitionSpec$iv3.invoke(transition.getSegment(), $composer, Integer.valueOf(($changed$iv5 >> 3) & 112));
                    transformOrigin$delegate = androidx.compose.animation.core.TransitionKt.createTransitionAnimation(transition, initialValue$iv3, targetValue$iv3, animationSpec$iv3, twoWayConverter3, "TransformOriginInterruptionHandling", $composer, ($changed$iv5 & 14) | (($changed$iv5 << 9) & 57344) | (($changed$iv5 << 6) & 458752));
                    $composer.endReplaceableGroup();
                    $composer.startReplaceableGroup(1618982084);
                    ComposerKt.sourceInformation($composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer.changed(alpha$delegate) | $composer.changed(scale$delegate) | $composer.changed(transformOrigin$delegate);
                    Object it$iv$iv6 = $composer.rememberedValue();
                    if (invalid$iv$iv) {
                        value$iv$iv5 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$1$1
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
                                graphicsLayer.setAlpha(EnterExitTransitionKt.createModifier$lambda$8(alpha$delegate));
                                graphicsLayer.setScaleX(EnterExitTransitionKt.createModifier$lambda$11(scale$delegate));
                                graphicsLayer.setScaleY(EnterExitTransitionKt.createModifier$lambda$11(scale$delegate));
                                graphicsLayer.mo3140setTransformOrigin__ExYCQ(EnterExitTransitionKt.createModifier$lambda$13(transformOrigin$delegate));
                            }
                        };
                        $composer.updateRememberedValue(value$iv$iv5);
                    } else {
                        value$iv$iv5 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$1$1
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
                                graphicsLayer.setAlpha(EnterExitTransitionKt.createModifier$lambda$8(alpha$delegate));
                                graphicsLayer.setScaleX(EnterExitTransitionKt.createModifier$lambda$11(scale$delegate));
                                graphicsLayer.setScaleY(EnterExitTransitionKt.createModifier$lambda$11(scale$delegate));
                                graphicsLayer.mo3140setTransformOrigin__ExYCQ(EnterExitTransitionKt.createModifier$lambda$13(transformOrigin$delegate));
                            }
                        };
                        $composer.updateRememberedValue(value$iv$iv5);
                    }
                    $composer.endReplaceableGroup();
                    modifier2 = GraphicsLayerModifierKt.graphicsLayer(modifier, (Function1) value$iv$iv5);
                    $composer.endReplaceableGroup();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } else {
            modifier2 = modifier;
            if (createModifier$lambda$1(shouldAnimateAlpha$delegate)) {
                $composer.startReplaceableGroup(1657244550);
                ComposerKt.sourceInformation($composer, "928@40400L42");
                $composer.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer, str3);
                boolean invalid$iv$iv4 = $composer.changed(alpha$delegate);
                Object it$iv$iv7 = $composer.rememberedValue();
                if (invalid$iv$iv4 || it$iv$iv7 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv3 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$2$1
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
                            graphicsLayer.setAlpha(EnterExitTransitionKt.createModifier$lambda$8(alpha$delegate));
                        }
                    };
                    $composer.updateRememberedValue(value$iv$iv3);
                } else {
                    value$iv$iv3 = it$iv$iv7;
                }
                $composer.endReplaceableGroup();
                modifier2 = GraphicsLayerModifierKt.graphicsLayer(modifier2, (Function1) value$iv$iv3);
                $composer.endReplaceableGroup();
            } else {
                $composer.startReplaceableGroup(1657244642);
                $composer.endReplaceableGroup();
            }
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return modifier2;
    }

    private static final boolean createModifier$lambda$1(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    private static final void createModifier$lambda$2(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    private static final boolean createModifier$lambda$4(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    private static final void createModifier$lambda$5(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float createModifier$lambda$8(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float createModifier$lambda$11(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long createModifier$lambda$13(State<TransformOrigin> state) {
        Object thisObj$iv = state.getValue();
        return ((TransformOrigin) thisObj$iv).getPackedValue();
    }

    private static final Modifier slideInOut(Modifier $this$slideInOut, final Transition<EnterExitState> transition, final State<Slide> state, final State<Slide> state2, final String labelPrefix) {
        return ComposedModifierKt.composed$default($this$slideInOut, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInOut.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            private static final boolean invoke$lambda$1(MutableState<Boolean> mutableState) {
                MutableState<Boolean> $this$getValue$iv = mutableState;
                return $this$getValue$iv.getValue().booleanValue();
            }

            private static final void invoke$lambda$2(MutableState<Boolean> mutableState, boolean value) {
                mutableState.setValue(Boolean.valueOf(value));
            }

            public final Modifier invoke(Modifier composed, Composer $composer, int $changed) {
                Object key1$iv;
                Modifier modifierThen;
                Object value$iv$iv;
                Object value$iv$iv2;
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                $composer.startReplaceableGroup(158379472);
                ComposerKt.sourceInformation($composer, "C952@41279L46,964@41704L33,962@41628L119,966@41771L88:EnterExitTransition.kt#xbi5r1");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(158379472, $changed, -1, "androidx.compose.animation.slideInOut.<anonymous> (EnterExitTransition.kt:949)");
                }
                Object key1$iv2 = transition;
                $composer.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean invalid$iv$iv = $composer.changed(key1$iv2);
                Object it$iv$iv = $composer.rememberedValue();
                if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    key1$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer.updateRememberedValue(key1$iv);
                } else {
                    key1$iv = it$iv$iv;
                }
                $composer.endReplaceableGroup();
                MutableState shouldAnimate$delegate = (MutableState) key1$iv;
                if (transition.getCurrentState() == transition.getTargetState() && !transition.isSeeking()) {
                    invoke$lambda$2(shouldAnimate$delegate, false);
                } else if (state.getValue() != null || state2.getValue() != null) {
                    invoke$lambda$2(shouldAnimate$delegate, true);
                }
                if (invoke$lambda$1(shouldAnimate$delegate)) {
                    Transition<EnterExitState> transition2 = transition;
                    TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE);
                    String str = labelPrefix;
                    $composer.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
                    Object it$iv$iv2 = $composer.rememberedValue();
                    if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = str + " slide";
                        $composer.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv2;
                    }
                    $composer.endReplaceableGroup();
                    Transition.DeferredAnimation animation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition2, vectorConverter, (String) value$iv$iv, $composer, 448, 0);
                    Object key1$iv3 = transition;
                    State<Slide> state3 = state;
                    State<Slide> state4 = state2;
                    $composer.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
                    boolean invalid$iv$iv2 = $composer.changed(key1$iv3);
                    Object it$iv$iv3 = $composer.rememberedValue();
                    if (invalid$iv$iv2 || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv2 = new SlideModifier(animation, state3, state4);
                        $composer.updateRememberedValue(value$iv$iv2);
                    } else {
                        value$iv$iv2 = it$iv$iv3;
                    }
                    $composer.endReplaceableGroup();
                    SlideModifier modifier = (SlideModifier) value$iv$iv2;
                    modifierThen = composed.then(modifier);
                } else {
                    modifierThen = composed;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceableGroup();
                return modifierThen;
            }
        }, 1, null);
    }

    private static final Modifier shrinkExpand(Modifier $this$shrinkExpand, final Transition<EnterExitState> transition, final State<ChangeSize> state, final State<ChangeSize> state2, final String labelPrefix) {
        return ComposedModifierKt.composed$default($this$shrinkExpand, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkExpand.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            private static final boolean invoke$lambda$1(MutableState<Boolean> mutableState) {
                MutableState<Boolean> $this$getValue$iv = mutableState;
                return $this$getValue$iv.getValue().booleanValue();
            }

            private static final void invoke$lambda$2(MutableState<Boolean> mutableState, boolean value) {
                mutableState.setValue(Boolean.valueOf(value));
            }

            /* JADX WARN: Code duplicated, block: B:67:0x0233  */
            /* JADX WARN: Code duplicated, block: B:68:0x0238  */
            /* JADX WARN: Code duplicated, block: B:70:0x023e  */
            /* JADX WARN: Code duplicated, block: B:72:0x0246  */
            /* JADX WARN: Code duplicated, block: B:79:0x0261  */
            /* JADX WARN: Code duplicated, block: B:81:0x0264  */
            /* JADX WARN: Code duplicated, block: B:86:0x0276  */
            /* JADX WARN: Code duplicated, block: B:89:0x027a  */
            /* JADX WARN: Code duplicated, block: B:90:0x027d  */
            /* JADX WARN: Code duplicated, block: B:94:0x0288  */
            public final Modifier invoke(Modifier composed, Composer $composer, int $changed) {
                Object key1$iv;
                Modifier modifierThen;
                Alignment alignment;
                Alignment alignment2;
                Object value$iv$iv;
                Object value$iv$iv2;
                Object value$iv$iv3;
                ExpandShrinkModifier expandShrinkModifier;
                Alignment topStart;
                ChangeSize value;
                boolean z;
                boolean z2;
                boolean disableClip;
                Modifier.Companion companionClipToBounds;
                ChangeSize value2;
                boolean z3;
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                $composer.startReplaceableGroup(-140634085);
                ComposerKt.sourceInformation($composer, "C1034@44322L46,1044@44676L396,1057@45186L41,1055@45112L125,1066@45547L218:EnterExitTransition.kt#xbi5r1");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-140634085, $changed, -1, "androidx.compose.animation.shrinkExpand.<anonymous> (EnterExitTransition.kt:1030)");
                }
                Object key1$iv2 = transition;
                $composer.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean invalid$iv$iv = $composer.changed(key1$iv2);
                Object it$iv$iv = $composer.rememberedValue();
                if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    key1$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer.updateRememberedValue(key1$iv);
                } else {
                    key1$iv = it$iv$iv;
                }
                $composer.endReplaceableGroup();
                MutableState shouldAnimate$delegate = (MutableState) key1$iv;
                if (transition.getCurrentState() == transition.getTargetState() && !transition.isSeeking()) {
                    invoke$lambda$2(shouldAnimate$delegate, false);
                } else if (state.getValue() != null || state2.getValue() != null) {
                    invoke$lambda$2(shouldAnimate$delegate, true);
                }
                if (invoke$lambda$1(shouldAnimate$delegate)) {
                    boolean it = transition.getSegment().isTransitioningTo(EnterExitState.PreEnter, EnterExitState.Visible);
                    State<ChangeSize> state3 = state;
                    State<ChangeSize> state4 = state2;
                    if (it) {
                        ChangeSize value3 = state3.getValue();
                        if (value3 == null || (alignment = value3.getAlignment()) == null) {
                            ChangeSize value4 = state4.getValue();
                            alignment = value4 != null ? value4.getAlignment() : null;
                        }
                    } else {
                        ChangeSize value5 = state4.getValue();
                        if (value5 == null || (alignment2 = value5.getAlignment()) == null) {
                            ChangeSize value6 = state3.getValue();
                            alignment = value6 != null ? value6.getAlignment() : null;
                        } else {
                            alignment = alignment2;
                        }
                    }
                    State alignment3 = SnapshotStateKt.rememberUpdatedState(alignment, $composer, 0);
                    Transition<EnterExitState> transition2 = transition;
                    TwoWayConverter<IntSize, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntSize.INSTANCE);
                    String str = labelPrefix;
                    $composer.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
                    Object it$iv$iv2 = $composer.rememberedValue();
                    if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = str + " shrink/expand";
                        $composer.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv2;
                    }
                    $composer.endReplaceableGroup();
                    Transition.DeferredAnimation sizeAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition2, vectorConverter, (String) value$iv$iv, $composer, 448, 0);
                    $composer.startMovableGroup(-1553213689, Boolean.valueOf(transition.getCurrentState() == transition.getTargetState()));
                    ComposerKt.sourceInformation($composer, "1062@45432L54,1060@45348L152");
                    Transition<EnterExitState> transition3 = transition;
                    TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter2 = VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE);
                    String str2 = labelPrefix;
                    $composer.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
                    Object it$iv$iv3 = $composer.rememberedValue();
                    if (it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv2 = str2 + " InterruptionHandlingOffset";
                        $composer.updateRememberedValue(value$iv$iv2);
                    } else {
                        value$iv$iv2 = it$iv$iv3;
                    }
                    $composer.endReplaceableGroup();
                    Transition.DeferredAnimation offsetAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition3, vectorConverter2, (String) value$iv$iv2, $composer, 448, 0);
                    $composer.endMovableGroup();
                    Object key1$iv3 = transition;
                    State<ChangeSize> state5 = state;
                    State<ChangeSize> state6 = state2;
                    $composer.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
                    boolean invalid$iv$iv2 = $composer.changed(key1$iv3);
                    Object it$iv$iv4 = $composer.rememberedValue();
                    if (!invalid$iv$iv2) {
                        Object key1$iv4 = Composer.INSTANCE.getEmpty();
                        if (it$iv$iv4 != key1$iv4) {
                            value$iv$iv3 = it$iv$iv4;
                        }
                        $composer.endReplaceableGroup();
                        expandShrinkModifier = (ExpandShrinkModifier) value$iv$iv3;
                        if (transition.getCurrentState() == transition.getTargetState()) {
                            expandShrinkModifier.setCurrentAlignment(null);
                        } else if (expandShrinkModifier.getCurrentAlignment() == null) {
                            topStart = (Alignment) alignment3.getValue();
                            if (topStart == null) {
                                topStart = Alignment.INSTANCE.getTopStart();
                            }
                            expandShrinkModifier.setCurrentAlignment(topStart);
                        }
                        value = state.getValue();
                        if (value != null || value.getClip()) {
                            z = false;
                        } else {
                            z = true;
                        }
                        if (z) {
                            z2 = true;
                        } else {
                            value2 = state2.getValue();
                            if (value2 != null || value2.getClip()) {
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                            if (z3) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                        }
                        disableClip = z2;
                        companionClipToBounds = Modifier.INSTANCE;
                        if (!disableClip) {
                            companionClipToBounds = ClipKt.clipToBounds(companionClipToBounds);
                        }
                        modifierThen = composed.then(companionClipToBounds).then(expandShrinkModifier);
                    }
                    value$iv$iv3 = new ExpandShrinkModifier(sizeAnimation, offsetAnimation, state5, state6, alignment3);
                    $composer.updateRememberedValue(value$iv$iv3);
                    $composer.endReplaceableGroup();
                    expandShrinkModifier = (ExpandShrinkModifier) value$iv$iv3;
                    if (transition.getCurrentState() == transition.getTargetState()) {
                        expandShrinkModifier.setCurrentAlignment(null);
                    } else if (expandShrinkModifier.getCurrentAlignment() == null) {
                        topStart = (Alignment) alignment3.getValue();
                        if (topStart == null) {
                            topStart = Alignment.INSTANCE.getTopStart();
                        }
                        expandShrinkModifier.setCurrentAlignment(topStart);
                    }
                    value = state.getValue();
                    if (value != null) {
                        z = false;
                    } else {
                        z = false;
                    }
                    if (z) {
                        z2 = true;
                    } else {
                        value2 = state2.getValue();
                        if (value2 != null) {
                            z3 = false;
                        } else {
                            z3 = false;
                        }
                        if (z3) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                    }
                    disableClip = z2;
                    companionClipToBounds = Modifier.INSTANCE;
                    if (!disableClip) {
                        companionClipToBounds = ClipKt.clipToBounds(companionClipToBounds);
                    }
                    modifierThen = composed.then(companionClipToBounds).then(expandShrinkModifier);
                } else {
                    modifierThen = composed;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceableGroup();
                return modifierThen;
            }
        }, 1, null);
    }
}
