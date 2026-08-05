package androidx.compose.material.pullrefresh;

import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationConstants;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.ColorsKt;
import androidx.compose.material.ElevationOverlay;
import androidx.compose.material.ElevationOverlayKt;
import androidx.compose.material.MaterialTheme;
import androidx.compose.material.ProgressIndicatorKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PullRefreshIndicator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0002H\u0002\u001a-\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001aM\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\"\u001a\u00020\u001a2\b\b\u0002\u0010#\u001a\u00020\u001a2\b\b\u0002\u0010$\u001a\u00020!H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001aA\u0010'\u001a\u00020\u0016*\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u0013H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u00100\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0013\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0013\u0010\u0007\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u0013\u0010\n\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u0013\u0010\u000b\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\"\u000e\u0010\f\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0011\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00061²\u0006\n\u00102\u001a\u00020!X\u008a\u0084\u0002²\u0006\n\u00103\u001a\u00020\u0002X\u008a\u0084\u0002"}, d2 = {"AlphaTween", "Landroidx/compose/animation/core/TweenSpec;", "", "ArcRadius", "Landroidx/compose/ui/unit/Dp;", "F", "ArrowHeight", "ArrowWidth", "CrossfadeDurationMs", "", "Elevation", "IndicatorSize", "MaxAlpha", "MaxProgressArc", "MinAlpha", "SpinnerShape", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "StrokeWidth", "ArrowValues", "Landroidx/compose/material/pullrefresh/ArrowValues;", NotificationCompat.CATEGORY_PROGRESS, "CircularArrowIndicator", "", "state", "Landroidx/compose/material/pullrefresh/PullRefreshState;", "color", "Landroidx/compose/ui/graphics/Color;", "modifier", "Landroidx/compose/ui/Modifier;", "CircularArrowIndicator-iJQMabo", "(Landroidx/compose/material/pullrefresh/PullRefreshState;JLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "PullRefreshIndicator", "refreshing", "", "backgroundColor", "contentColor", "scale", "PullRefreshIndicator-jB83MbM", "(ZLandroidx/compose/material/pullrefresh/PullRefreshState;Landroidx/compose/ui/Modifier;JJZLandroidx/compose/runtime/Composer;II)V", "drawArrow", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "arrow", "Landroidx/compose/ui/graphics/Path;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "alpha", "values", "drawArrow-Bx497Mc", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/geometry/Rect;JFLandroidx/compose/material/pullrefresh/ArrowValues;)V", "material_release", "showElevation", "targetAlpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PullRefreshIndicatorKt {
    private static final int CrossfadeDurationMs = 100;
    private static final float MaxAlpha = 1.0f;
    private static final float MaxProgressArc = 0.8f;
    private static final float MinAlpha = 0.3f;
    private static final float IndicatorSize = Dp.m5274constructorimpl(40);
    private static final RoundedCornerShape SpinnerShape = RoundedCornerShapeKt.getCircleShape();
    private static final float ArcRadius = Dp.m5274constructorimpl((float) 7.5d);
    private static final float StrokeWidth = Dp.m5274constructorimpl((float) 2.5d);
    private static final float ArrowWidth = Dp.m5274constructorimpl(10);
    private static final float ArrowHeight = Dp.m5274constructorimpl(5);
    private static final float Elevation = Dp.m5274constructorimpl(6);
    private static final TweenSpec<Float> AlphaTween = AnimationSpecKt.tween$default(AnimationConstants.DefaultDurationMillis, 0, EasingKt.getLinearEasing(), 2, null);

    /* JADX WARN: Code duplicated, block: B:31:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:32:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:35:0x0108  */
    /* JADX WARN: Code duplicated, block: B:36:0x010d  */
    /* JADX WARN: Code duplicated, block: B:39:0x0120  */
    /* JADX WARN: Code duplicated, block: B:40:0x0125  */
    /* JADX WARN: Code duplicated, block: B:43:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:46:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:47:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:58:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:61:0x02cf  */
    /* JADX WARN: Code duplicated, block: B:62:0x02d8  */
    /* JADX INFO: renamed from: PullRefreshIndicator-jB83MbM, reason: not valid java name */
    public static final void m1284PullRefreshIndicatorjB83MbM(final boolean refreshing, final PullRefreshState state, Modifier modifier, long backgroundColor, long contentColor, boolean scale, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long backgroundColor2;
        final long contentColor2;
        final int $dirty;
        boolean scale2;
        Object value$iv$iv;
        State showElevation$delegate;
        ElevationOverlay elevationOverlay;
        Color colorM2961boximpl;
        long jM2981unboximpl;
        float fM5274constructorimpl;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(state, "state");
        Composer $composer2 = $composer.startRestartGroup(308716636);
        ComposerKt.sourceInformation($composer2, "C(PullRefreshIndicator)P(3,5,2,0:c#ui.graphics.Color,1:c#ui.graphics.Color)78@3382L6,79@3424L32,82@3514L98,89@3897L7,93@4025L1067:PullRefreshIndicator.kt#t44y28");
        int $dirty2 = $changed;
        if ((i & 4) != 0) {
            modifier2 = Modifier.INSTANCE;
        } else {
            modifier2 = modifier;
        }
        if ((i & 8) == 0) {
            backgroundColor2 = backgroundColor;
        } else {
            long backgroundColor3 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
            $dirty2 &= -7169;
            backgroundColor2 = backgroundColor3;
        }
        if ((i & 16) == 0) {
            contentColor2 = contentColor;
            $dirty = $dirty2;
        } else {
            long contentColor3 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty2 >> 9) & 14);
            $dirty = $dirty2 & (-57345);
            contentColor2 = contentColor3;
        }
        if ((i & 32) == 0) {
            scale2 = scale;
        } else {
            scale2 = false;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(308716636, $dirty, -1, "androidx.compose.material.pullrefresh.PullRefreshIndicator (PullRefreshIndicator.kt:74)");
        }
        Object key1$iv = Boolean.valueOf(refreshing);
        int i2 = ($dirty & 14) | 64;
        $composer2.startReplaceableGroup(511388516);
        ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer2.changed(key1$iv) | $composer2.changed(state);
        Object it$iv$iv = $composer2.rememberedValue();
        if (!invalid$iv$iv) {
            Object key1$iv2 = Composer.INSTANCE.getEmpty();
            if (it$iv$iv != key1$iv2) {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            showElevation$delegate = (State) value$iv$iv;
            ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localElevationOverlay);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            elevationOverlay = (ElevationOverlay) objConsume;
            $composer2.startReplaceableGroup(52228748);
            ComposerKt.sourceInformation($composer2, "90@3939L53");
            if (elevationOverlay == null) {
                colorM2961boximpl = null;
            } else {
                colorM2961boximpl = Color.m2961boximpl(elevationOverlay.mo1082apply7g2Lkgo(backgroundColor2, Elevation, $composer2, (($dirty >> 9) & 14) | 48));
            }
            $composer2.endReplaceableGroup();
            if (colorM2961boximpl != null) {
                jM2981unboximpl = colorM2961boximpl.m2981unboximpl();
            } else {
                jM2981unboximpl = backgroundColor2;
            }
            long color = jM2981unboximpl;
            Modifier modifierPullRefreshIndicatorTransform = PullRefreshIndicatorTransformKt.pullRefreshIndicatorTransform(SizeKt.m534size3ABfNKs(modifier2, IndicatorSize), state, scale2);
            if (PullRefreshIndicator_jB83MbM$lambda$1(showElevation$delegate)) {
                fM5274constructorimpl = Elevation;
            } else {
                fM5274constructorimpl = Dp.m5274constructorimpl(0);
            }
            RoundedCornerShape roundedCornerShape = SpinnerShape;
            Modifier modifier$iv = BackgroundKt.m159backgroundbw27NRU(ShadowKt.m2643shadows4CzXII$default(modifierPullRefreshIndicatorTransform, fM5274constructorimpl, roundedCornerShape, true, 0L, 0L, 24, null), color, roundedCornerShape);
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i3 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i4 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1406369954, "C100@4312L774:PullRefreshIndicator.kt#t44y28");
            CrossfadeKt.Crossfade(Boolean.valueOf(refreshing), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.composableLambda($composer2, 1853731063, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$PullRefreshIndicator$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer, Integer num) {
                    invoke(bool.booleanValue(), composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean refreshing2, Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C104@4471L605:PullRefreshIndicator.kt#t44y28");
                    int $dirty3 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty3 |= $composer3.changed(refreshing2) ? 4 : 2;
                    }
                    if (($dirty3 & 91) == 18 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1853731063, $changed2, -1, "androidx.compose.material.pullrefresh.PullRefreshIndicator.<anonymous>.<anonymous> (PullRefreshIndicator.kt:103)");
                    }
                    Modifier modifier$iv2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
                    long j = contentColor2;
                    int i5 = $dirty;
                    PullRefreshState pullRefreshState = state;
                    $composer3.startReplaceableGroup(733328855);
                    ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer3, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                    int $changed$iv$iv2 = (54 << 3) & 112;
                    $composer3.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        $composer3.createNode(constructor2);
                    } else {
                        $composer3.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2603constructorimpl($composer3);
                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                    }
                    function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                    $composer3.startReplaceableGroup(2058660585);
                    int i6 = ($changed$iv$iv$iv2 >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i7 = ((54 >> 6) & 112) | 6;
                    Composer $composer4 = $composer3;
                    ComposerKt.sourceInformationMarkerStart($composer4, -2035147121, "C:PullRefreshIndicator.kt#t44y28");
                    float arg0$iv = PullRefreshIndicatorKt.ArcRadius;
                    float other$iv = PullRefreshIndicatorKt.StrokeWidth;
                    float arg0$iv2 = Dp.m5274constructorimpl(2 * Dp.m5274constructorimpl(arg0$iv + other$iv));
                    if (refreshing2) {
                        $composer4.startReplaceableGroup(-2035147035);
                        ComposerKt.sourceInformation($composer4, "111@4719L208");
                        ProgressIndicatorKt.m1162CircularProgressIndicatorLxG7B9w(SizeKt.m534size3ABfNKs(Modifier.INSTANCE, arg0$iv2), j, PullRefreshIndicatorKt.StrokeWidth, 0L, 0, $composer4, ((i5 >> 9) & 112) | 390, 24);
                        $composer4.endReplaceableGroup();
                        $composer4 = $composer4;
                    } else {
                        $composer4.startReplaceableGroup(-2035146781);
                        ComposerKt.sourceInformation($composer4, "117@4973L71");
                        PullRefreshIndicatorKt.m1283CircularArrowIndicatoriJQMabo(pullRefreshState, j, SizeKt.m534size3ABfNKs(Modifier.INSTANCE, arg0$iv2), $composer4, ((i5 >> 9) & 112) | 392);
                        $composer4.endReplaceableGroup();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceableGroup();
                    $composer3.endNode();
                    $composer3.endReplaceableGroup();
                    $composer3.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, ($dirty & 14) | 24960, 10);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier3 = modifier2;
            final long j = backgroundColor2;
            final boolean scale3 = scale2;
            final long j2 = contentColor2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$PullRefreshIndicator$2
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
                    PullRefreshIndicatorKt.m1284PullRefreshIndicatorjB83MbM(refreshing, state, modifier3, j, j2, scale3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        value$iv$iv = SnapshotStateKt.derivedStateOf(new Function0<Boolean>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$PullRefreshIndicator$showElevation$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(refreshing || state.getPosition$material_release() > 0.5f);
            }
        });
        $composer2.updateRememberedValue(value$iv$iv);
        $composer2.endReplaceableGroup();
        showElevation$delegate = (State) value$iv$iv;
        ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay2 = ElevationOverlayKt.getLocalElevationOverlay();
        ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume2 = $composer2.consume(localElevationOverlay2);
        ComposerKt.sourceInformationMarkerEnd($composer2);
        elevationOverlay = (ElevationOverlay) objConsume2;
        $composer2.startReplaceableGroup(52228748);
        ComposerKt.sourceInformation($composer2, "90@3939L53");
        if (elevationOverlay == null) {
            colorM2961boximpl = null;
        } else {
            colorM2961boximpl = Color.m2961boximpl(elevationOverlay.mo1082apply7g2Lkgo(backgroundColor2, Elevation, $composer2, (($dirty >> 9) & 14) | 48));
        }
        $composer2.endReplaceableGroup();
        if (colorM2961boximpl != null) {
            jM2981unboximpl = colorM2961boximpl.m2981unboximpl();
        } else {
            jM2981unboximpl = backgroundColor2;
        }
        long color2 = jM2981unboximpl;
        Modifier modifierPullRefreshIndicatorTransform2 = PullRefreshIndicatorTransformKt.pullRefreshIndicatorTransform(SizeKt.m534size3ABfNKs(modifier2, IndicatorSize), state, scale2);
        if (PullRefreshIndicator_jB83MbM$lambda$1(showElevation$delegate)) {
            fM5274constructorimpl = Elevation;
        } else {
            fM5274constructorimpl = Dp.m5274constructorimpl(0);
        }
        RoundedCornerShape roundedCornerShape2 = SpinnerShape;
        Modifier modifier$iv2 = BackgroundKt.m159backgroundbw27NRU(ShadowKt.m2643shadows4CzXII$default(modifierPullRefreshIndicatorTransform2, fM5274constructorimpl, roundedCornerShape2, true, 0L, 0L, 24, null), color2, roundedCornerShape2);
        $composer2.startReplaceableGroup(733328855);
        ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
        Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
        MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
        int $changed$iv$iv2 = (0 << 3) & 112;
        $composer2.startReplaceableGroup(-1323940314);
        ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
        compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
        CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
        constructor = ComposeUiNode.INSTANCE.getConstructor();
        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
        if (!($composer2.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer2.startReusableNode();
        if ($composer2.getInserting()) {
            function0 = constructor;
            $composer2.createNode(function0);
        } else {
            function0 = constructor;
            $composer2.useNode();
        }
        $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer2);
        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
        }
        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
        function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
        $composer2.startReplaceableGroup(2058660585);
        int i5 = ($changed$iv$iv$iv2 >> 9) & 14;
        ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
        int i6 = ((0 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer2, 1406369954, "C100@4312L774:PullRefreshIndicator.kt#t44y28");
        CrossfadeKt.Crossfade(Boolean.valueOf(refreshing), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.composableLambda($composer2, 1853731063, true, new Function3<Boolean, Composer, Integer, Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$PullRefreshIndicator$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer, Integer num) {
                invoke(bool.booleanValue(), composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean refreshing2, Composer $composer3, int $changed2) {
                ComposerKt.sourceInformation($composer3, "C104@4471L605:PullRefreshIndicator.kt#t44y28");
                int $dirty3 = $changed2;
                if (($changed2 & 14) == 0) {
                    $dirty3 |= $composer3.changed(refreshing2) ? 4 : 2;
                }
                if (($dirty3 & 91) == 18 && $composer3.getSkipping()) {
                    $composer3.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1853731063, $changed2, -1, "androidx.compose.material.pullrefresh.PullRefreshIndicator.<anonymous>.<anonymous> (PullRefreshIndicator.kt:103)");
                }
                Modifier modifier$iv3 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getCenter();
                long j3 = contentColor2;
                int i7 = $dirty;
                PullRefreshState pullRefreshState = state;
                $composer3.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv3 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv3, false, $composer3, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                int $changed$iv$iv3 = (54 << 3) & 112;
                $composer3.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifier$iv3);
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor2);
                } else {
                    $composer3.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2603constructorimpl($composer3);
                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                }
                function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                $composer3.startReplaceableGroup(2058660585);
                int i8 = ($changed$iv$iv$iv3 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i9 = ((54 >> 6) & 112) | 6;
                Composer $composer4 = $composer3;
                ComposerKt.sourceInformationMarkerStart($composer4, -2035147121, "C:PullRefreshIndicator.kt#t44y28");
                float arg0$iv = PullRefreshIndicatorKt.ArcRadius;
                float other$iv = PullRefreshIndicatorKt.StrokeWidth;
                float arg0$iv2 = Dp.m5274constructorimpl(2 * Dp.m5274constructorimpl(arg0$iv + other$iv));
                if (refreshing2) {
                    $composer4.startReplaceableGroup(-2035147035);
                    ComposerKt.sourceInformation($composer4, "111@4719L208");
                    ProgressIndicatorKt.m1162CircularProgressIndicatorLxG7B9w(SizeKt.m534size3ABfNKs(Modifier.INSTANCE, arg0$iv2), j3, PullRefreshIndicatorKt.StrokeWidth, 0L, 0, $composer4, ((i7 >> 9) & 112) | 390, 24);
                    $composer4.endReplaceableGroup();
                    $composer4 = $composer4;
                } else {
                    $composer4.startReplaceableGroup(-2035146781);
                    ComposerKt.sourceInformation($composer4, "117@4973L71");
                    PullRefreshIndicatorKt.m1283CircularArrowIndicatoriJQMabo(pullRefreshState, j3, SizeKt.m534size3ABfNKs(Modifier.INSTANCE, arg0$iv2), $composer4, ((i7 >> 9) & 112) | 392);
                    $composer4.endReplaceableGroup();
                }
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceableGroup();
                $composer3.endNode();
                $composer3.endReplaceableGroup();
                $composer3.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }), $composer2, ($dirty & 14) | 24960, 10);
        ComposerKt.sourceInformationMarkerEnd($composer2);
        ComposerKt.sourceInformationMarkerEnd($composer2);
        $composer2.endReplaceableGroup();
        $composer2.endNode();
        $composer2.endReplaceableGroup();
        $composer2.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier2;
        final long j3 = backgroundColor2;
        final boolean scale4 = scale2;
        final long j4 = contentColor2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$PullRefreshIndicator$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i7) {
                PullRefreshIndicatorKt.m1284PullRefreshIndicatorjB83MbM(refreshing, state, modifier4, j3, j4, scale4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    private static final boolean PullRefreshIndicator_jB83MbM$lambda$1(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: CircularArrowIndicator-iJQMabo, reason: not valid java name */
    public static final void m1283CircularArrowIndicatoriJQMabo(final PullRefreshState state, final long color, final Modifier modifier, Composer $composer, final int $changed) {
        Object value$iv$iv;
        Object value$iv$iv2;
        Composer $composer2 = $composer.startRestartGroup(-486016981);
        ComposerKt.sourceInformation($composer2, "C(CircularArrowIndicator)P(2,0:c#ui.graphics.Color)134@5303L61,136@5389L119,142@5531L74,145@5644L1000:PullRefreshIndicator.kt#t44y28");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-486016981, $changed, -1, "androidx.compose.material.pullrefresh.CircularArrowIndicator (PullRefreshIndicator.kt:129)");
        }
        $composer2.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
        Object it$iv$iv = $composer2.rememberedValue();
        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
            Path $this$CircularArrowIndicator_iJQMabo_u24lambda_u244_u24lambda_u243 = AndroidPath_androidKt.Path();
            $this$CircularArrowIndicator_iJQMabo_u24lambda_u244_u24lambda_u243.mo2866setFillTypeoQ8Xj4U(PathFillType.INSTANCE.m3254getEvenOddRgk1Os());
            value$iv$iv = $this$CircularArrowIndicator_iJQMabo_u24lambda_u244_u24lambda_u243;
            $composer2.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        $composer2.endReplaceableGroup();
        final Path path = (Path) value$iv$iv;
        $composer2.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer2.changed(state);
        Object it$iv$iv2 = $composer2.rememberedValue();
        if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv$iv2 = SnapshotStateKt.derivedStateOf(new Function0<Float>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$CircularArrowIndicator$targetAlpha$2$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Float invoke() {
                    return Float.valueOf(state.getProgress() < 1.0f ? 0.3f : 1.0f);
                }
            });
            $composer2.updateRememberedValue(value$iv$iv2);
        } else {
            value$iv$iv2 = it$iv$iv2;
        }
        $composer2.endReplaceableGroup();
        State targetAlpha$delegate = (State) value$iv$iv2;
        final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(CircularArrowIndicator_iJQMabo$lambda$6(targetAlpha$delegate), AlphaTween, 0.0f, null, null, $composer2, 48, 28);
        CanvasKt.Canvas(SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$CircularArrowIndicator$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semantics) {
                Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
            }
        }, 1, null), new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$CircularArrowIndicator$2
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
            public final void invoke2(DrawScope Canvas) {
                Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
                ArrowValues values = PullRefreshIndicatorKt.ArrowValues(state.getProgress());
                float alpha = stateAnimateFloatAsState.getValue().floatValue();
                float degrees$iv = values.getRotation();
                long j = color;
                Path path2 = path;
                long pivot$iv = Canvas.mo3441getCenterF1C5BW0();
                DrawContext $this$withTransform_u24lambda_u246$iv$iv = Canvas.getDrawContext();
                long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo3448getSizeNHjbRc();
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
                DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
                $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo3454rotateUv8p0NA(degrees$iv, pivot$iv);
                float arcRadius = Canvas.mo327toPx0680j_4(PullRefreshIndicatorKt.ArcRadius) + (Canvas.mo327toPx0680j_4(PullRefreshIndicatorKt.StrokeWidth) / 2.0f);
                Rect arcBounds = new Rect(Offset.m2731getXimpl(androidx.compose.ui.geometry.SizeKt.m2810getCenteruvyYCjk(Canvas.mo3442getSizeNHjbRc())) - arcRadius, Offset.m2732getYimpl(androidx.compose.ui.geometry.SizeKt.m2810getCenteruvyYCjk(Canvas.mo3442getSizeNHjbRc())) - arcRadius, Offset.m2731getXimpl(androidx.compose.ui.geometry.SizeKt.m2810getCenteruvyYCjk(Canvas.mo3442getSizeNHjbRc())) + arcRadius, Offset.m2732getYimpl(androidx.compose.ui.geometry.SizeKt.m2810getCenteruvyYCjk(Canvas.mo3442getSizeNHjbRc())) + arcRadius);
                DrawScope.CC.m3507drawArcyD3GUKo$default(Canvas, j, values.getStartAngle(), values.getEndAngle() - values.getStartAngle(), false, arcBounds.m2766getTopLeftF1C5BW0(), arcBounds.m2764getSizeNHjbRc(), alpha, new Stroke(Canvas.mo327toPx0680j_4(PullRefreshIndicatorKt.StrokeWidth), 0.0f, StrokeCap.INSTANCE.m3326getSquareKaPHkGw(), 0, null, 26, null), null, 0, 768, null);
                PullRefreshIndicatorKt.m1287drawArrowBx497Mc(Canvas, path2, arcBounds, j, alpha, values);
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv.mo3449setSizeuvyYCjk(previousSize$iv$iv);
            }
        }, $composer2, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$CircularArrowIndicator$3
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
                PullRefreshIndicatorKt.m1283CircularArrowIndicatoriJQMabo(state, color, modifier, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    private static final float CircularArrowIndicator_iJQMabo$lambda$6(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrowValues ArrowValues(float progress) {
        float adjustedPercent = (Math.max(Math.min(1.0f, progress) - 0.4f, 0.0f) * 5) / 3;
        float overshootPercent = Math.abs(progress) - 1.0f;
        float linearTension = RangesKt.coerceIn(overshootPercent, 0.0f, 2.0f);
        float tensionPercent = linearTension - (((float) Math.pow(linearTension, 2)) / 4);
        float endTrim = MaxProgressArc * adjustedPercent;
        float rotation = (((0.4f * adjustedPercent) - 0.25f) + tensionPercent) * 0.5f;
        float f = 360;
        float startAngle = rotation * f;
        float endAngle = (rotation + endTrim) * f;
        float scale = Math.min(1.0f, adjustedPercent);
        return new ArrowValues(rotation, startAngle, endAngle, scale);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawArrow-Bx497Mc, reason: not valid java name */
    public static final void m1287drawArrowBx497Mc(DrawScope $this$drawArrow_u2dBx497Mc, Path arrow, Rect bounds, long color, float alpha, ArrowValues values) {
        arrow.reset();
        arrow.moveTo(0.0f, 0.0f);
        float f = ArrowWidth;
        arrow.lineTo($this$drawArrow_u2dBx497Mc.mo327toPx0680j_4(f) * values.getScale(), 0.0f);
        arrow.lineTo(($this$drawArrow_u2dBx497Mc.mo327toPx0680j_4(f) * values.getScale()) / 2, $this$drawArrow_u2dBx497Mc.mo327toPx0680j_4(ArrowHeight) * values.getScale());
        float radius = Math.min(bounds.getWidth(), bounds.getHeight()) / 2.0f;
        float inset = ($this$drawArrow_u2dBx497Mc.mo327toPx0680j_4(f) * values.getScale()) / 2.0f;
        arrow.mo2868translatek4lQ0M(OffsetKt.Offset((Offset.m2731getXimpl(bounds.m2761getCenterF1C5BW0()) + radius) - inset, Offset.m2732getYimpl(bounds.m2761getCenterF1C5BW0()) + ($this$drawArrow_u2dBx497Mc.mo327toPx0680j_4(StrokeWidth) / 2.0f)));
        arrow.close();
        float degrees$iv = values.getEndAngle();
        long pivot$iv = $this$drawArrow_u2dBx497Mc.mo3441getCenterF1C5BW0();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$drawArrow_u2dBx497Mc.getDrawContext();
        long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo3448getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
        DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
        $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo3454rotateUv8p0NA(degrees$iv, pivot$iv);
        DrawScope.CC.m3518drawPathLG529CI$default($this$drawArrow_u2dBx497Mc, arrow, color, alpha, null, null, 0, 56, null);
        $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
        $this$withTransform_u24lambda_u246$iv$iv.mo3449setSizeuvyYCjk(previousSize$iv$iv);
    }
}
