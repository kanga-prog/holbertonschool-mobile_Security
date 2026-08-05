package androidx.compose.material;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001ae\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00072\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\n0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001aq\u0010\u001b\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\n0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u0087\u0001\u0010\u001f\u001a\u00020\n2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\n0!¢\u0006\u0002\b\u00172\b\b\u0002\u0010\u0013\u001a\u00020\u00072\u0015\b\u0002\u0010\"\u001a\u000f\u0012\u0004\u0012\u00020\n\u0018\u00010!¢\u0006\u0002\b\u00172\u001e\b\u0002\u0010#\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\n0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u0001H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001ae\u0010\u001f\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\n0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a\u0019\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)H\u0080\b\u001a,\u0010,\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020)0-2\u0006\u0010.\u001a\u00020)2\u0006\u0010+\u001a\u00020)2\u0006\u0010/\u001a\u00020)H\u0000\u001a\u0011\u00100\u001a\u00020)2\u0006\u00101\u001a\u00020)H\u0082\b\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0004\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0005\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00062"}, d2 = {"AppBarHeight", "Landroidx/compose/ui/unit/Dp;", "F", "AppBarHorizontalPadding", "BottomAppBarCutoutOffset", "BottomAppBarRoundedEdgeRadius", "TitleIconModifier", "Landroidx/compose/ui/Modifier;", "TitleInsetWithoutIcon", "AppBar", "", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "elevation", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "shape", "Landroidx/compose/ui/graphics/Shape;", "modifier", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "AppBar-celAv9A", "(JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomAppBar", "cutoutShape", "BottomAppBar-Y1yfwus", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/graphics/Shape;FLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "TopAppBar", "title", "Lkotlin/Function0;", "navigationIcon", "actions", "TopAppBar-xWeB9-s", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;JJFLandroidx/compose/runtime/Composer;II)V", "TopAppBar-HsRjFd4", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "calculateCutoutCircleYIntercept", "", "cutoutRadius", "verticalOffset", "calculateRoundedEdgeIntercept", "Lkotlin/Pair;", "controlPointX", "radius", "square", "x", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AppBarKt {
    private static final float AppBarHeight = Dp.m5274constructorimpl(56);
    private static final float AppBarHorizontalPadding = Dp.m5274constructorimpl(4);
    private static final float BottomAppBarCutoutOffset;
    private static final float BottomAppBarRoundedEdgeRadius;
    private static final Modifier TitleIconModifier;
    private static final Modifier TitleInsetWithoutIcon;

    /* JADX INFO: renamed from: TopAppBar-xWeB9-s, reason: not valid java name */
    public static final void m980TopAppBarxWeB9s(final Function2<? super Composer, ? super Integer, Unit> title, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, long backgroundColor, long contentColor, float elevation, Composer $composer, final int $changed, final int i) {
        final Function2<? super Composer, ? super Integer, Unit> function4;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3M1071getLambda1$material_release;
        long backgroundColor2;
        long contentColor2;
        float elevation2;
        Modifier.Companion modifier2;
        final int $dirty;
        long contentColor3;
        Modifier modifier3;
        long contentColor4;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function6;
        long backgroundColor3;
        float elevation3;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer2 = $composer.startRestartGroup(-2087748139);
        ComposerKt.sourceInformation($composer2, "C(TopAppBar)P(6,4,5!1,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.unit.Dp)81@3902L6,82@3951L32,85@4047L1254:AppBar.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changedInstance(title) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            function4 = function2;
        } else if (($changed & 896) == 0) {
            function4 = function2;
            $dirty2 |= $composer2.changedInstance(function4) ? 256 : 128;
        } else {
            function4 = function2;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            function3M1071getLambda1$material_release = function3;
        } else if (($changed & 7168) == 0) {
            function3M1071getLambda1$material_release = function3;
            $dirty2 |= $composer2.changedInstance(function3M1071getLambda1$material_release) ? 2048 : 1024;
        } else {
            function3M1071getLambda1$material_release = function3;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                backgroundColor2 = backgroundColor;
                int i5 = $composer2.changed(backgroundColor2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                backgroundColor2 = backgroundColor;
            }
            $dirty2 |= i5;
        } else {
            backgroundColor2 = backgroundColor;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                contentColor2 = contentColor;
                int i6 = $composer2.changed(contentColor2) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                contentColor2 = contentColor;
            }
            $dirty2 |= i6;
        } else {
            contentColor2 = contentColor;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            elevation2 = elevation;
        } else if (($changed & 3670016) == 0) {
            elevation2 = elevation;
            $dirty2 |= $composer2.changed(elevation2) ? 1048576 : 524288;
        } else {
            elevation2 = elevation;
        }
        if (($dirty2 & 2995931) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            function6 = function3M1071getLambda1$material_release;
            backgroundColor3 = backgroundColor2;
            elevation3 = elevation2;
            contentColor4 = contentColor2;
            modifier3 = modifier;
            function5 = function4;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if (i3 != 0) {
                    function4 = null;
                }
                if (i4 != 0) {
                    function3M1071getLambda1$material_release = ComposableSingletons$AppBarKt.INSTANCE.m1071getLambda1$material_release();
                }
                if ((i & 16) != 0) {
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    contentColor2 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty2 >> 12) & 14);
                    $dirty2 &= -458753;
                }
                if (i7 != 0) {
                    $dirty = $dirty2;
                    elevation2 = AppBarDefaults.INSTANCE.m976getTopAppBarElevationD9Ej5fM();
                    contentColor3 = contentColor2;
                } else {
                    $dirty = $dirty2;
                    contentColor3 = contentColor2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-458753);
                    contentColor3 = contentColor2;
                    modifier2 = modifier;
                } else {
                    modifier2 = modifier;
                    $dirty = $dirty2;
                    contentColor3 = contentColor2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2087748139, $dirty, -1, "androidx.compose.material.TopAppBar (AppBar.kt:76)");
            }
            m977AppBarcelAv9A(backgroundColor2, contentColor3, elevation2, AppBarDefaults.INSTANCE.getContentPadding(), RectangleShapeKt.getRectangleShape(), modifier2, ComposableLambdaKt.composableLambda($composer2, -1484077694, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.AppBarKt$TopAppBar$1
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

                public final void invoke(RowScope AppBar, Composer $composer3, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    Function0<ComposeUiNode> function1;
                    Intrinsics.checkNotNullParameter(AppBar, "$this$AppBar");
                    ComposerKt.sourceInformation($composer3, "C104@4595L378,116@5048L6,116@4983L312:AppBar.kt#jmzs0o");
                    int $dirty3 = $changed2;
                    if (($changed2 & 14) == 0) {
                        $dirty3 |= $composer3.changed(AppBar) ? 4 : 2;
                    }
                    if (($dirty3 & 91) != 18 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1484077694, $changed2, -1, "androidx.compose.material.TopAppBar.<anonymous> (AppBar.kt:92)");
                        }
                        if (function4 == null) {
                            $composer3.startReplaceableGroup(-512812651);
                            ComposerKt.sourceInformation($composer3, "94@4259L29");
                            SpacerKt.Spacer(AppBarKt.TitleInsetWithoutIcon, $composer3, 6);
                            $composer3.endReplaceableGroup();
                        } else {
                            $composer3.startReplaceableGroup(-512812592);
                            ComposerKt.sourceInformation($composer3, "96@4318L257");
                            Modifier modifier$iv = AppBarKt.TitleIconModifier;
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                            Function2<Composer, Integer, Unit> function7 = function4;
                            int i8 = $dirty;
                            $composer3.startReplaceableGroup(693286680);
                            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)76@3779L58,77@3842L130:Row.kt#2w3rfo");
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                            int $changed$iv$iv = (390 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                            Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                            if (!($composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                                function0 = constructor;
                                $composer3.createNode(function0);
                            } else {
                                function0 = constructor;
                                $composer3.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer3);
                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                            }
                            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                            $composer3.startReplaceableGroup(2058660585);
                            int i9 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -326682417, "C78@3887L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            int i10 = ((390 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -2027905960, "C98@4493L4,97@4407L154:AppBar.kt#jmzs0o");
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getHigh($composer3, 6)))}, function7, $composer3, ((i8 >> 3) & 112) | 8);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                        }
                        Modifier modifier$iv2 = RowScope.CC.weight$default(AppBar, SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null);
                        Alignment.Vertical verticalAlignment$iv2 = Alignment.INSTANCE.getCenterVertically();
                        final Function2<Composer, Integer, Unit> function8 = title;
                        final int i11 = $dirty;
                        $composer3.startReplaceableGroup(693286680);
                        ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)76@3779L58,77@3842L130:Row.kt#2w3rfo");
                        Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv2 = (384 << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.Companion.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifier$iv2);
                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function1 = constructor2;
                            $composer3.createNode(function1);
                        } else {
                            function1 = constructor2;
                            $composer3.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2603constructorimpl($composer3);
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                        }
                        function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                        $composer3.startReplaceableGroup(2058660585);
                        int i12 = ($changed$iv$iv$iv2 >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -326682417, "C78@3887L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                        int i13 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2027905635, "C108@4771L10,108@4732L231:AppBar.kt#jmzs0o");
                        TextKt.ProvideTextStyle(MaterialTheme.INSTANCE.getTypography($composer3, 6).getH6(), ComposableLambdaKt.composableLambda($composer3, -2021518195, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AppBarKt$TopAppBar$1$2$1
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
                                ComposerKt.sourceInformation($composer4, "C110@4890L4,109@4804L145:AppBar.kt#jmzs0o");
                                if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2021518195, $changed3, -1, "androidx.compose.material.TopAppBar.<anonymous>.<anonymous>.<anonymous> (AppBar.kt:108)");
                                    }
                                    CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getHigh($composer4, 6)))}, function8, $composer4, ((i11 << 3) & 112) | 8);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        }), $composer3, 48);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        ProvidedValue[] providedValueArr = {ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getMedium($composer3, 6)))};
                        final Function3<RowScope, Composer, Integer, Unit> function9 = function3M1071getLambda1$material_release;
                        final int i14 = $dirty;
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer3, 1157662914, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AppBarKt$TopAppBar$1.3
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
                                ComposerKt.sourceInformation($composer4, "C117@5070L215:AppBar.kt#jmzs0o");
                                if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1157662914, $changed3, -1, "androidx.compose.material.TopAppBar.<anonymous>.<anonymous> (AppBar.kt:116)");
                                    }
                                    Modifier modifier$iv3 = SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null);
                                    Arrangement.Horizontal horizontalArrangement$iv3 = Arrangement.INSTANCE.getEnd();
                                    Alignment.Vertical verticalAlignment$iv3 = Alignment.INSTANCE.getCenterVertically();
                                    Function3<RowScope, Composer, Integer, Unit> function10 = function9;
                                    int $changed$iv = (i14 & 7168) | 438;
                                    $composer4.startReplaceableGroup(693286680);
                                    ComposerKt.sourceInformation($composer4, "CC(Row)P(2,1,3)76@3779L58,77@3842L130:Row.kt#2w3rfo");
                                    MeasurePolicy measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv3, verticalAlignment$iv3, $composer4, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                    int $changed$iv$iv3 = ($changed$iv << 3) & 112;
                                    $composer4.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                    int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                                    CompositionLocalMap localMap$iv$iv3 = $composer4.getCurrentCompositionLocalMap();
                                    Function0<ComposeUiNode> constructor3 = ComposeUiNode.Companion.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifier$iv3);
                                    int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
                                    if (!($composer4.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    $composer4.startReusableNode();
                                    if ($composer4.getInserting()) {
                                        $composer4.createNode(constructor3);
                                    } else {
                                        $composer4.useNode();
                                    }
                                    Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m2603constructorimpl($composer4);
                                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.Companion.getSetMeasurePolicy());
                                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.Companion.getSetCompositeKeyHash();
                                    if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                                    }
                                    function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                                    $composer4.startReplaceableGroup(2058660585);
                                    int i15 = ($changed$iv$iv$iv3 >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -326682417, "C78@3887L9:Row.kt#2w3rfo");
                                    function10.invoke(RowScopeInstance.INSTANCE, $composer4, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
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
                        }), $composer3, 56);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, (($dirty >> 12) & 14) | 1600512 | (($dirty >> 12) & 112) | (($dirty >> 12) & 896) | (($dirty << 12) & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            contentColor4 = contentColor3;
            function5 = function4;
            function6 = function3M1071getLambda1$material_release;
            backgroundColor3 = backgroundColor2;
            elevation3 = elevation2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function7 = function5;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function8 = function6;
        final long j = backgroundColor3;
        final long j2 = contentColor4;
        final float f = elevation3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AppBarKt$TopAppBar$2
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
                AppBarKt.m980TopAppBarxWeB9s(title, modifier4, function7, function8, j, j2, f, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0170  */
    /* JADX WARN: Code duplicated, block: B:105:0x01be  */
    /* JADX WARN: Code duplicated, block: B:109:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:111:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:75:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:85:0x0117 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:86:0x0119  */
    /* JADX WARN: Code duplicated, block: B:87:0x011e  */
    /* JADX WARN: Code duplicated, block: B:90:0x0123  */
    /* JADX WARN: Code duplicated, block: B:91:0x0131  */
    /* JADX WARN: Code duplicated, block: B:94:0x0136  */
    /* JADX WARN: Code duplicated, block: B:96:0x0143  */
    /* JADX WARN: Code duplicated, block: B:98:0x014c  */
    /* JADX WARN: Code duplicated, block: B:99:0x015d  */
    /* JADX INFO: renamed from: TopAppBar-HsRjFd4, reason: not valid java name */
    public static final void m979TopAppBarHsRjFd4(Modifier modifier, long backgroundColor, long contentColor, float elevation, PaddingValues contentPadding, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        long contentColor2;
        float elevation2;
        PaddingValues paddingValues;
        int i2;
        Modifier.Companion modifier3;
        long backgroundColor2;
        Modifier modifier4;
        long backgroundColor3;
        long contentColor3;
        float elevation3;
        PaddingValues contentPadding2;
        int $dirty;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1897058582);
        ComposerKt.sourceInformation($composer2, "C(TopAppBar)P(5,0:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp,3)156@6973L6,157@7022L32,162@7231L182:AppBar.kt#jmzs0o");
        int $dirty2 = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 14) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 112) == 0) {
            if ((i & 2) == 0) {
                j = backgroundColor;
                int i4 = $composer2.changed(j) ? 32 : 16;
                $dirty2 |= i4;
            } else {
                j = backgroundColor;
            }
            $dirty2 |= i4;
        } else {
            j = backgroundColor;
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
            elevation2 = elevation;
        } else if (($changed & 7168) == 0) {
            elevation2 = elevation;
            $dirty2 |= $composer2.changed(elevation2) ? 2048 : 1024;
        } else {
            elevation2 = elevation;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty2 |= 24576;
            paddingValues = contentPadding;
        } else if ((57344 & $changed) == 0) {
            paddingValues = contentPadding;
            $dirty2 |= $composer2.changed(paddingValues) ? 16384 : 8192;
        } else {
            paddingValues = contentPadding;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
                i2 = $composer2.changedInstance(content) ? 131072 : 65536;
            }
            if ((374491 & $dirty2) == 74898 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if ((i & 2) != 0) {
                        backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                        $dirty2 &= -113;
                    } else {
                        backgroundColor2 = j;
                    }
                    if ((i & 4) != 0) {
                        long contentColor4 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty2 >> 3) & 14);
                        $dirty2 &= -897;
                        contentColor2 = contentColor4;
                    }
                    if (i6 != 0) {
                        elevation2 = AppBarDefaults.INSTANCE.m976getTopAppBarElevationD9Ej5fM();
                    }
                    if (i7 != 0) {
                        modifier4 = modifier3;
                        $dirty = $dirty2;
                        backgroundColor3 = backgroundColor2;
                        contentPadding2 = AppBarDefaults.INSTANCE.getContentPadding();
                        contentColor3 = contentColor2;
                        elevation3 = elevation2;
                    } else {
                        modifier4 = modifier3;
                        backgroundColor3 = backgroundColor2;
                        contentColor3 = contentColor2;
                        elevation3 = elevation2;
                        contentPadding2 = paddingValues;
                        $dirty = $dirty2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 2) != 0) {
                        $dirty2 &= -113;
                    }
                    if ((i & 4) != 0) {
                        modifier4 = modifier2;
                        backgroundColor3 = j;
                        contentColor3 = contentColor2;
                        elevation3 = elevation2;
                        contentPadding2 = paddingValues;
                        $dirty = $dirty2 & (-897);
                    } else {
                        modifier4 = modifier2;
                        backgroundColor3 = j;
                        contentColor3 = contentColor2;
                        elevation3 = elevation2;
                        contentPadding2 = paddingValues;
                        $dirty = $dirty2;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1897058582, $dirty, -1, "androidx.compose.material.TopAppBar (AppBar.kt:154)");
                }
                m977AppBarcelAv9A(backgroundColor3, contentColor3, elevation3, contentPadding2, RectangleShapeKt.getRectangleShape(), modifier4, content, $composer2, (($dirty >> 3) & 14) | 24576 | (($dirty >> 3) & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty << 15) & 458752) | (3670016 & ($dirty << 3)), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
                modifier4 = modifier2;
                backgroundColor3 = j;
                contentColor3 = contentColor2;
                elevation3 = elevation2;
                contentPadding2 = paddingValues;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final long j2 = backgroundColor3;
            final long j3 = contentColor3;
            final float f = elevation3;
            final PaddingValues paddingValues2 = contentPadding2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AppBarKt$TopAppBar$3
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
                    AppBarKt.m979TopAppBarHsRjFd4(modifier5, j2, j3, f, paddingValues2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty2 |= i2;
        if ((374491 & $dirty2) == 74898) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                    $dirty2 &= -113;
                } else {
                    backgroundColor2 = j;
                }
                if ((i & 4) != 0) {
                    long contentColor5 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                    contentColor2 = contentColor5;
                }
                if (i6 != 0) {
                    elevation2 = AppBarDefaults.INSTANCE.m976getTopAppBarElevationD9Ej5fM();
                }
                if (i7 != 0) {
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                    backgroundColor3 = backgroundColor2;
                    contentPadding2 = AppBarDefaults.INSTANCE.getContentPadding();
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                } else {
                    modifier4 = modifier3;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    contentPadding2 = paddingValues;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                    $dirty2 &= -113;
                } else {
                    backgroundColor2 = j;
                }
                if ((i & 4) != 0) {
                    long contentColor6 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                    contentColor2 = contentColor6;
                }
                if (i6 != 0) {
                    elevation2 = AppBarDefaults.INSTANCE.m976getTopAppBarElevationD9Ej5fM();
                }
                if (i7 != 0) {
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                    backgroundColor3 = backgroundColor2;
                    contentPadding2 = AppBarDefaults.INSTANCE.getContentPadding();
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                } else {
                    modifier4 = modifier3;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    contentPadding2 = paddingValues;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1897058582, $dirty, -1, "androidx.compose.material.TopAppBar (AppBar.kt:154)");
            }
            m977AppBarcelAv9A(backgroundColor3, contentColor3, elevation3, contentPadding2, RectangleShapeKt.getRectangleShape(), modifier4, content, $composer2, (($dirty >> 3) & 14) | 24576 | (($dirty >> 3) & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty << 15) & 458752) | (3670016 & ($dirty << 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                    $dirty2 &= -113;
                } else {
                    backgroundColor2 = j;
                }
                if ((i & 4) != 0) {
                    long contentColor7 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                    contentColor2 = contentColor7;
                }
                if (i6 != 0) {
                    elevation2 = AppBarDefaults.INSTANCE.m976getTopAppBarElevationD9Ej5fM();
                }
                if (i7 != 0) {
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                    backgroundColor3 = backgroundColor2;
                    contentPadding2 = AppBarDefaults.INSTANCE.getContentPadding();
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                } else {
                    modifier4 = modifier3;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    contentPadding2 = paddingValues;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) != 0) {
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                    $dirty2 &= -113;
                } else {
                    backgroundColor2 = j;
                }
                if ((i & 4) != 0) {
                    long contentColor8 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                    contentColor2 = contentColor8;
                }
                if (i6 != 0) {
                    elevation2 = AppBarDefaults.INSTANCE.m976getTopAppBarElevationD9Ej5fM();
                }
                if (i7 != 0) {
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                    backgroundColor3 = backgroundColor2;
                    contentPadding2 = AppBarDefaults.INSTANCE.getContentPadding();
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                } else {
                    modifier4 = modifier3;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    contentPadding2 = paddingValues;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1897058582, $dirty, -1, "androidx.compose.material.TopAppBar (AppBar.kt:154)");
            }
            m977AppBarcelAv9A(backgroundColor3, contentColor3, elevation3, contentPadding2, RectangleShapeKt.getRectangleShape(), modifier4, content, $composer2, (($dirty >> 3) & 14) | 24576 | (($dirty >> 3) & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty << 15) & 458752) | (3670016 & ($dirty << 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final long j4 = backgroundColor3;
        final long j5 = contentColor3;
        final float f2 = elevation3;
        final PaddingValues paddingValues3 = contentPadding2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AppBarKt$TopAppBar$3
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
                AppBarKt.m979TopAppBarHsRjFd4(modifier6, j4, j5, f2, paddingValues3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0151  */
    /* JADX WARN: Code duplicated, block: B:104:0x0163  */
    /* JADX WARN: Code duplicated, block: B:106:0x0170  */
    /* JADX WARN: Code duplicated, block: B:108:0x0174  */
    /* JADX WARN: Code duplicated, block: B:110:0x017d  */
    /* JADX WARN: Code duplicated, block: B:111:0x018f  */
    /* JADX WARN: Code duplicated, block: B:114:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:117:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:119:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:123:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:124:0x01de  */
    /* JADX WARN: Code duplicated, block: B:127:0x0227  */
    /* JADX WARN: Code duplicated, block: B:131:0x0231  */
    /* JADX WARN: Code duplicated, block: B:133:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x010b  */
    /* JADX WARN: Code duplicated, block: B:86:0x0112  */
    /* JADX WARN: Code duplicated, block: B:96:0x0144 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:97:0x0146  */
    /* JADX WARN: Code duplicated, block: B:98:0x014b  */
    /* JADX INFO: renamed from: BottomAppBar-Y1yfwus, reason: not valid java name */
    public static final void m978BottomAppBarY1yfwus(Modifier modifier, long backgroundColor, long contentColor, Shape cutoutShape, float elevation, PaddingValues contentPadding, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        long backgroundColor2;
        long contentColor2;
        Shape cutoutShape2;
        float elevation2;
        int i2;
        Modifier.Companion modifier2;
        PaddingValues contentPadding2;
        Modifier modifier3;
        int $dirty;
        long backgroundColor3;
        long contentColor3;
        float elevation3;
        Shape cutoutShape3;
        FabPlacement fabPlacement;
        Shape shape;
        Shape cutoutShape4;
        boolean z;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-1651948973);
        ComposerKt.sourceInformation($composer2, "C(BottomAppBar)P(6,0:c#ui.graphics.Color,2:c#ui.graphics.Color,4,5:c#ui.unit.Dp,3)216@9902L6,217@9951L32,223@10232L7,229@10422L152:AppBar.kt#jmzs0o");
        int $dirty2 = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            if ((i & 2) == 0) {
                backgroundColor2 = backgroundColor;
                int i4 = $composer2.changed(backgroundColor2) ? 32 : 16;
                $dirty2 |= i4;
            } else {
                backgroundColor2 = backgroundColor;
            }
            $dirty2 |= i4;
        } else {
            backgroundColor2 = backgroundColor;
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
            cutoutShape2 = cutoutShape;
        } else if (($changed & 7168) == 0) {
            cutoutShape2 = cutoutShape;
            $dirty2 |= $composer2.changed(cutoutShape2) ? 2048 : 1024;
        } else {
            cutoutShape2 = cutoutShape;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty2 |= 24576;
            elevation2 = elevation;
        } else if ((57344 & $changed) == 0) {
            elevation2 = elevation;
            $dirty2 |= $composer2.changed(elevation2) ? 16384 : 8192;
        } else {
            elevation2 = elevation;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty2 |= $composer2.changed(contentPadding) ? 131072 : 65536;
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
                        $dirty2 &= -113;
                        backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                    }
                    if ((i & 4) != 0) {
                        long contentColor4 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty2 >> 3) & 14);
                        $dirty2 &= -897;
                        contentColor2 = contentColor4;
                    }
                    if (i6 != 0) {
                        cutoutShape2 = null;
                    }
                    if (i7 != 0) {
                        elevation2 = AppBarDefaults.INSTANCE.m975getBottomAppBarElevationD9Ej5fM();
                    }
                    if (i8 != 0) {
                        modifier3 = modifier2;
                        $dirty = $dirty2;
                        contentPadding2 = AppBarDefaults.INSTANCE.getContentPadding();
                        backgroundColor3 = backgroundColor2;
                        contentColor3 = contentColor2;
                        elevation3 = elevation2;
                        cutoutShape3 = cutoutShape2;
                    } else {
                        contentPadding2 = contentPadding;
                        modifier3 = modifier2;
                        $dirty = $dirty2;
                        backgroundColor3 = backgroundColor2;
                        contentColor3 = contentColor2;
                        elevation3 = elevation2;
                        cutoutShape3 = cutoutShape2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 2) != 0) {
                        $dirty2 &= -113;
                    }
                    if ((i & 4) != 0) {
                        modifier3 = modifier;
                        contentPadding2 = contentPadding;
                        $dirty = $dirty2 & (-897);
                        backgroundColor3 = backgroundColor2;
                        contentColor3 = contentColor2;
                        elevation3 = elevation2;
                        cutoutShape3 = cutoutShape2;
                    } else {
                        modifier3 = modifier;
                        contentPadding2 = contentPadding;
                        $dirty = $dirty2;
                        backgroundColor3 = backgroundColor2;
                        contentColor3 = contentColor2;
                        elevation3 = elevation2;
                        cutoutShape3 = cutoutShape2;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1651948973, $dirty, -1, "androidx.compose.material.BottomAppBar (AppBar.kt:214)");
                }
                ProvidableCompositionLocal<FabPlacement> localFabPlacement = ScaffoldKt.getLocalFabPlacement();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localFabPlacement);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                fabPlacement = (FabPlacement) objConsume;
                if (cutoutShape3 != null) {
                    z = false;
                    if (fabPlacement != null && fabPlacement.getIsDocked()) {
                        z = true;
                    }
                    if (z) {
                        shape = new BottomAppBarCutoutShape(cutoutShape3, fabPlacement);
                    } else {
                        shape = RectangleShapeKt.getRectangleShape();
                    }
                } else {
                    shape = RectangleShapeKt.getRectangleShape();
                }
                cutoutShape4 = cutoutShape3;
                m977AppBarcelAv9A(backgroundColor3, contentColor3, elevation3, contentPadding2, shape, modifier3, content, $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty << 15) & 458752) | ($dirty & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier;
                contentPadding2 = contentPadding;
                backgroundColor3 = backgroundColor2;
                contentColor3 = contentColor2;
                cutoutShape4 = cutoutShape2;
                elevation3 = elevation2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final long j = backgroundColor3;
            final long j2 = contentColor3;
            final Shape shape2 = cutoutShape4;
            final float f = elevation3;
            final PaddingValues paddingValues = contentPadding2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AppBarKt$BottomAppBar$1
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
                    AppBarKt.m978BottomAppBarY1yfwus(modifier4, j, j2, shape2, f, paddingValues, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
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
                    $dirty2 &= -113;
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                }
                if ((i & 4) != 0) {
                    long contentColor5 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                    contentColor2 = contentColor5;
                }
                if (i6 != 0) {
                    cutoutShape2 = null;
                }
                if (i7 != 0) {
                    elevation2 = AppBarDefaults.INSTANCE.m975getBottomAppBarElevationD9Ej5fM();
                }
                if (i8 != 0) {
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    contentPadding2 = AppBarDefaults.INSTANCE.getContentPadding();
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    cutoutShape3 = cutoutShape2;
                } else {
                    contentPadding2 = contentPadding;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    cutoutShape3 = cutoutShape2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                }
                if ((i & 4) != 0) {
                    long contentColor6 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                    contentColor2 = contentColor6;
                }
                if (i6 != 0) {
                    cutoutShape2 = null;
                }
                if (i7 != 0) {
                    elevation2 = AppBarDefaults.INSTANCE.m975getBottomAppBarElevationD9Ej5fM();
                }
                if (i8 != 0) {
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    contentPadding2 = AppBarDefaults.INSTANCE.getContentPadding();
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    cutoutShape3 = cutoutShape2;
                } else {
                    contentPadding2 = contentPadding;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    cutoutShape3 = cutoutShape2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1651948973, $dirty, -1, "androidx.compose.material.BottomAppBar (AppBar.kt:214)");
            }
            ProvidableCompositionLocal<FabPlacement> localFabPlacement2 = ScaffoldKt.getLocalFabPlacement();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localFabPlacement2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            fabPlacement = (FabPlacement) objConsume2;
            if (cutoutShape3 != null) {
                z = false;
                if (fabPlacement != null) {
                    z = true;
                }
                if (z) {
                    shape = new BottomAppBarCutoutShape(cutoutShape3, fabPlacement);
                } else {
                    shape = RectangleShapeKt.getRectangleShape();
                }
            } else {
                shape = RectangleShapeKt.getRectangleShape();
            }
            cutoutShape4 = cutoutShape3;
            m977AppBarcelAv9A(backgroundColor3, contentColor3, elevation3, contentPadding2, shape, modifier3, content, $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty << 15) & 458752) | ($dirty & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                }
                if ((i & 4) != 0) {
                    long contentColor7 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                    contentColor2 = contentColor7;
                }
                if (i6 != 0) {
                    cutoutShape2 = null;
                }
                if (i7 != 0) {
                    elevation2 = AppBarDefaults.INSTANCE.m975getBottomAppBarElevationD9Ej5fM();
                }
                if (i8 != 0) {
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    contentPadding2 = AppBarDefaults.INSTANCE.getContentPadding();
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    cutoutShape3 = cutoutShape2;
                } else {
                    contentPadding2 = contentPadding;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    cutoutShape3 = cutoutShape2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer2, 6));
                }
                if ((i & 4) != 0) {
                    long contentColor8 = ColorsKt.m1066contentColorForek8zF_U(backgroundColor2, $composer2, ($dirty2 >> 3) & 14);
                    $dirty2 &= -897;
                    contentColor2 = contentColor8;
                }
                if (i6 != 0) {
                    cutoutShape2 = null;
                }
                if (i7 != 0) {
                    elevation2 = AppBarDefaults.INSTANCE.m975getBottomAppBarElevationD9Ej5fM();
                }
                if (i8 != 0) {
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    contentPadding2 = AppBarDefaults.INSTANCE.getContentPadding();
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    cutoutShape3 = cutoutShape2;
                } else {
                    contentPadding2 = contentPadding;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                    elevation3 = elevation2;
                    cutoutShape3 = cutoutShape2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1651948973, $dirty, -1, "androidx.compose.material.BottomAppBar (AppBar.kt:214)");
            }
            ProvidableCompositionLocal<FabPlacement> localFabPlacement3 = ScaffoldKt.getLocalFabPlacement();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localFabPlacement3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            fabPlacement = (FabPlacement) objConsume3;
            if (cutoutShape3 != null) {
                z = false;
                if (fabPlacement != null) {
                    z = true;
                }
                if (z) {
                    shape = new BottomAppBarCutoutShape(cutoutShape3, fabPlacement);
                } else {
                    shape = RectangleShapeKt.getRectangleShape();
                }
            } else {
                shape = RectangleShapeKt.getRectangleShape();
            }
            cutoutShape4 = cutoutShape3;
            m977AppBarcelAv9A(backgroundColor3, contentColor3, elevation3, contentPadding2, shape, modifier3, content, $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty << 15) & 458752) | ($dirty & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final long j3 = backgroundColor3;
        final long j4 = contentColor3;
        final Shape shape3 = cutoutShape4;
        final float f2 = elevation3;
        final PaddingValues paddingValues2 = contentPadding2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AppBarKt$BottomAppBar$1
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
                AppBarKt.m978BottomAppBarY1yfwus(modifier5, j3, j4, shape3, f2, paddingValues2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    private static final float square(float x) {
        return x * x;
    }

    public static final float calculateCutoutCircleYIntercept(float cutoutRadius, float verticalOffset) {
        return -((float) Math.sqrt((cutoutRadius * cutoutRadius) - (verticalOffset * verticalOffset)));
    }

    public static final Pair<Float, Float> calculateRoundedEdgeIntercept(float controlPointX, float verticalOffset, float radius) {
        Float fValueOf;
        Float fValueOf2;
        Pair pair;
        Float fValueOf3;
        Float fValueOf4;
        float discriminant = verticalOffset * verticalOffset * radius * radius * (((controlPointX * controlPointX) + (verticalOffset * verticalOffset)) - (radius * radius));
        float divisor = (controlPointX * controlPointX) + (verticalOffset * verticalOffset);
        float bCoefficient = radius * radius * controlPointX;
        float xSolutionA = (bCoefficient - ((float) Math.sqrt(discriminant))) / divisor;
        float xSolutionB = (((float) Math.sqrt(discriminant)) + bCoefficient) / divisor;
        float ySolutionA = (float) Math.sqrt((radius * radius) - (xSolutionA * xSolutionA));
        float ySolutionB = (float) Math.sqrt((radius * radius) - (xSolutionB * xSolutionB));
        if (verticalOffset > 0.0f) {
            if (ySolutionA > ySolutionB) {
                fValueOf3 = Float.valueOf(xSolutionA);
                fValueOf4 = Float.valueOf(ySolutionA);
            } else {
                fValueOf3 = Float.valueOf(xSolutionB);
                fValueOf4 = Float.valueOf(ySolutionB);
            }
            pair = TuplesKt.to(fValueOf3, fValueOf4);
        } else {
            if (ySolutionA < ySolutionB) {
                fValueOf = Float.valueOf(xSolutionA);
                fValueOf2 = Float.valueOf(ySolutionA);
            } else {
                fValueOf = Float.valueOf(xSolutionB);
                fValueOf2 = Float.valueOf(ySolutionB);
            }
            pair = TuplesKt.to(fValueOf, fValueOf2);
        }
        float xSolution = ((Number) pair.component1()).floatValue();
        float ySolution = ((Number) pair.component2()).floatValue();
        float adjustedYSolution = xSolution < controlPointX ? -ySolution : ySolution;
        return TuplesKt.to(Float.valueOf(xSolution), Float.valueOf(adjustedYSolution));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: AppBar-celAv9A, reason: not valid java name */
    public static final void m977AppBarcelAv9A(final long backgroundColor, final long contentColor, final float elevation, final PaddingValues contentPadding, final Shape shape, Modifier modifier, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(-1249680788);
        ComposerKt.sourceInformation($composer2, "C(AppBar)P(0:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp,3,6,5)513@22344L583:AppBar.kt#jmzs0o");
        final int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(backgroundColor) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(contentColor) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(elevation) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(contentPadding) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if ((57344 & $changed) == 0) {
            $dirty |= $composer2.changed(shape) ? 16384 : 8192;
        }
        int i2 = i & 32;
        if (i2 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else if (($changed & 458752) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if ((3670016 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 1048576 : 524288;
        }
        if ((2995931 & $dirty) != 599186 || !$composer2.getSkipping()) {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1249680788, $dirty, -1, "androidx.compose.material.AppBar (AppBar.kt:504)");
            }
            SurfaceKt.m1210SurfaceFjzlyU(modifier2, shape, backgroundColor, contentColor, null, elevation, ComposableLambdaKt.composableLambda($composer2, -1027830352, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AppBarKt$AppBar$1
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
                    ComposerKt.sourceInformation($composer3, "C520@22586L6,520@22521L400:AppBar.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1027830352, $changed2, -1, "androidx.compose.material.AppBar.<anonymous> (AppBar.kt:519)");
                        }
                        ProvidedValue[] providedValueArr = {ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getMedium($composer3, 6)))};
                        final PaddingValues paddingValues = contentPadding;
                        final Function3<RowScope, Composer, Integer, Unit> function4 = function3;
                        final int i3 = $dirty;
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda($composer3, 1296061040, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AppBarKt$AppBar$1.1
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
                                ComposerKt.sourceInformation($composer4, "C521@22608L303:AppBar.kt#jmzs0o");
                                if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1296061040, $changed3, -1, "androidx.compose.material.AppBar.<anonymous>.<anonymous> (AppBar.kt:520)");
                                    }
                                    Modifier modifier$iv = SizeKt.m520height3ABfNKs(PaddingKt.padding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), paddingValues), AppBarKt.AppBarHeight);
                                    Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                                    Function3<RowScope, Composer, Integer, Unit> function5 = function4;
                                    int $changed$iv = ((i3 >> 9) & 7168) | 432;
                                    $composer4.startReplaceableGroup(693286680);
                                    ComposerKt.sourceInformation($composer4, "CC(Row)P(2,1,3)76@3779L58,77@3842L130:Row.kt#2w3rfo");
                                    MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer4, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                                    int $changed$iv$iv = ($changed$iv << 3) & 112;
                                    $composer4.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                                    CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
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
                                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer4);
                                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.Companion.getSetMeasurePolicy());
                                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                    }
                                    function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                                    $composer4.startReplaceableGroup(2058660585);
                                    int i4 = ($changed$iv$iv$iv >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -326682417, "C78@3887L9:Row.kt#2w3rfo");
                                    function5.invoke(RowScopeInstance.INSTANCE, $composer4, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
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
                        }), $composer3, 56);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, (($dirty >> 15) & 14) | 1572864 | (($dirty >> 9) & 112) | (($dirty << 6) & 896) | (($dirty << 6) & 7168) | (($dirty << 9) & 458752), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AppBarKt$AppBar$2
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
                AppBarKt.m977AppBarcelAv9A(backgroundColor, contentColor, elevation, contentPadding, shape, modifier4, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    static {
        Modifier.Companion companion = Modifier.INSTANCE;
        float arg0$iv = Dp.m5274constructorimpl(16);
        float other$iv = AppBarHorizontalPadding;
        TitleInsetWithoutIcon = SizeKt.m539width3ABfNKs(companion, Dp.m5274constructorimpl(arg0$iv - other$iv));
        Modifier modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null);
        float arg0$iv2 = Dp.m5274constructorimpl(72);
        float other$iv2 = AppBarHorizontalPadding;
        TitleIconModifier = SizeKt.m539width3ABfNKs(modifierFillMaxHeight$default, Dp.m5274constructorimpl(arg0$iv2 - other$iv2));
        BottomAppBarCutoutOffset = Dp.m5274constructorimpl(8);
        BottomAppBarRoundedEdgeRadius = Dp.m5274constructorimpl(4);
    }
}
