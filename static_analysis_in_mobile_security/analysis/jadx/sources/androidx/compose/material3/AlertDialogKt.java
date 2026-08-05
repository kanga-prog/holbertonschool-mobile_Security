package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.DialogTokens;
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
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: AlertDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a©\u0001\u0010\f\u001a\u00020\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\r0\u000f¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0013\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f¢\u0006\u0002\b\u00102\u0013\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f¢\u0006\u0002\b\u00102\u0013\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f¢\u0006\u0002\b\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u0019H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a8\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u00012\u0011\u0010$\u001a\r\u0012\u0004\u0012\u00020\r0\u000f¢\u0006\u0002\b\u0010H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b%\u0010&\"\u0019\u0010\u0000\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\"\u0019\u0010\u0005\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0006\u0010\u0003\"\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"DialogMaxWidth", "Landroidx/compose/ui/unit/Dp;", "getDialogMaxWidth", "()F", "F", "DialogMinWidth", "getDialogMinWidth", "DialogPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "IconPadding", "TextPadding", "TitlePadding", "AlertDialogContent", "", "buttons", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "icon", "title", "text", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "tonalElevation", "buttonContentColor", "iconContentColor", "titleContentColor", "textContentColor", "AlertDialogContent-4hvqGtA", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JFJJJJLandroidx/compose/runtime/Composer;III)V", "AlertDialogFlowRow", "mainAxisSpacing", "crossAxisSpacing", "content", "AlertDialogFlowRow-ixp7dh8", "(FFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AlertDialogKt {
    private static final float DialogMinWidth = Dp.m5274constructorimpl(280);
    private static final float DialogMaxWidth = Dp.m5274constructorimpl(560);
    private static final PaddingValues DialogPadding = PaddingKt.m480PaddingValues0680j_4(Dp.m5274constructorimpl(24));
    private static final PaddingValues IconPadding = PaddingKt.m484PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m5274constructorimpl(16), 7, null);
    private static final PaddingValues TitlePadding = PaddingKt.m484PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m5274constructorimpl(16), 7, null);
    private static final PaddingValues TextPadding = PaddingKt.m484PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m5274constructorimpl(24), 7, null);

    /* JADX WARN: Code duplicated, block: B:101:0x013f  */
    /* JADX WARN: Code duplicated, block: B:102:0x0145  */
    /* JADX WARN: Code duplicated, block: B:104:0x014a  */
    /* JADX WARN: Code duplicated, block: B:106:0x0152  */
    /* JADX WARN: Code duplicated, block: B:107:0x0155  */
    /* JADX WARN: Code duplicated, block: B:109:0x0159  */
    /* JADX WARN: Code duplicated, block: B:112:0x0160  */
    /* JADX WARN: Code duplicated, block: B:113:0x0165  */
    /* JADX WARN: Code duplicated, block: B:115:0x0169  */
    /* JADX WARN: Code duplicated, block: B:117:0x0171  */
    /* JADX WARN: Code duplicated, block: B:118:0x0174  */
    /* JADX WARN: Code duplicated, block: B:120:0x0179  */
    /* JADX WARN: Code duplicated, block: B:123:0x017f  */
    /* JADX WARN: Code duplicated, block: B:124:0x0184  */
    /* JADX WARN: Code duplicated, block: B:126:0x0188  */
    /* JADX WARN: Code duplicated, block: B:129:0x0191  */
    /* JADX WARN: Code duplicated, block: B:131:0x0195  */
    /* JADX WARN: Code duplicated, block: B:140:0x01ba A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:141:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:142:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:145:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:148:0x0234  */
    /* JADX WARN: Code duplicated, block: B:152:0x023f  */
    /* JADX WARN: Code duplicated, block: B:153:0x0242  */
    /* JADX WARN: Code duplicated, block: B:68:0x00da  */
    /* JADX WARN: Code duplicated, block: B:69:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:71:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:73:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:74:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:76:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:79:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:80:0x0104  */
    /* JADX WARN: Code duplicated, block: B:82:0x0109  */
    /* JADX WARN: Code duplicated, block: B:84:0x0111  */
    /* JADX WARN: Code duplicated, block: B:85:0x0114  */
    /* JADX WARN: Code duplicated, block: B:87:0x0119  */
    /* JADX WARN: Code duplicated, block: B:90:0x011f  */
    /* JADX WARN: Code duplicated, block: B:91:0x0125  */
    /* JADX WARN: Code duplicated, block: B:93:0x012a  */
    /* JADX WARN: Code duplicated, block: B:95:0x0132  */
    /* JADX WARN: Code duplicated, block: B:96:0x0135  */
    /* JADX WARN: Code duplicated, block: B:98:0x0139  */
    /* JADX INFO: renamed from: AlertDialogContent-4hvqGtA, reason: not valid java name */
    public static final void m1305AlertDialogContent4hvqGtA(final Function2<? super Composer, ? super Integer, Unit> buttons, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Shape shape, final long containerColor, final float tonalElevation, final long buttonContentColor, final long iconContentColor, final long titleContentColor, final long textContentColor, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        final int $dirty;
        int i7;
        int $dirty1;
        Modifier modifier3;
        Composer $composer2;
        Modifier modifier4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(buttons, "buttons");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Composer $composer3 = $composer.startRestartGroup(1522575799);
        ComposerKt.sourceInformation($composer3, "C(AlertDialogContent)P(1,5,3,9,7,6,2:c#ui.graphics.Color,11:c#ui.unit.Dp,0:c#ui.graphics.Color,4:c#ui.graphics.Color,10:c#ui.graphics.Color,8:c#ui.graphics.Color)51@1823L2647:AlertDialog.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty3 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(buttons) ? 4 : 2;
        }
        int i8 = i & 2;
        if (i8 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty2 |= $composer3.changedInstance(function4) ? 16384 : 8192;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
                i2 = $composer3.changed(shape) ? 131072 : 65536;
            }
            if ((i & 64) != 0) {
                $dirty2 |= 1572864;
            } else if (($changed & 3670016) != 0) {
                if ($composer3.changed(containerColor)) {
                    i3 = 1048576;
                } else {
                    i3 = 524288;
                }
                $dirty2 |= i3;
            }
            if ((i & 128) != 0) {
                $dirty2 |= 12582912;
            } else if ((29360128 & $changed) != 0) {
                if ($composer3.changed(tonalElevation)) {
                    i4 = 8388608;
                } else {
                    i4 = 4194304;
                }
                $dirty2 |= i4;
            }
            if ((i & 256) != 0) {
                $dirty2 |= 100663296;
            } else if ((234881024 & $changed) != 0) {
                if ($composer3.changed(buttonContentColor)) {
                    i5 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i5 = 33554432;
                }
                $dirty2 |= i5;
            }
            if ((i & 512) != 0) {
                $dirty2 |= 805306368;
            } else if ((1879048192 & $changed) != 0) {
                if ($composer3.changed(iconContentColor)) {
                    i6 = 536870912;
                } else {
                    i6 = 268435456;
                }
                $dirty2 |= i6;
            }
            $dirty = $dirty2;
            if ((i & 1024) != 0) {
                $dirty3 |= 6;
            } else if (($changed1 & 14) != 0) {
                if ($composer3.changed(titleContentColor)) {
                    i7 = 4;
                } else {
                    i7 = 2;
                }
                $dirty3 |= i7;
            }
            if ((i & 2048) != 0) {
                $dirty3 |= 48;
            } else if (($changed1 & 112) != 0) {
                $dirty3 |= $composer3.changed(textContentColor) ? 32 : 16;
            }
            $dirty1 = $dirty3;
            if ((1533916891 & $dirty) != 306783378 && ($dirty1 & 91) == 18 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                modifier4 = modifier2;
                $composer2 = $composer3;
            } else {
                if (i8 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1522575799, $dirty, $dirty1, "androidx.compose.material3.AlertDialogContent (AlertDialog.kt:37)");
                }
                $composer2 = $composer3;
                SurfaceKt.m1806SurfaceT9BRK9s(modifier3, shape, containerColor, 0L, tonalElevation, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -2126308228, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1
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
                        ComposerKt.sourceInformation($composer4, "C57@1973L2491:AlertDialog.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2126308228, $changed2, -1, "androidx.compose.material3.AlertDialogContent.<anonymous> (AlertDialog.kt:56)");
                            }
                            Modifier modifier$iv = PaddingKt.padding(Modifier.INSTANCE, AlertDialogKt.DialogPadding);
                            final Function2<Composer, Integer, Unit> function5 = function2;
                            final Function2<Composer, Integer, Unit> function6 = function3;
                            final Function2<Composer, Integer, Unit> function7 = function4;
                            long j = iconContentColor;
                            final int i9 = $dirty;
                            long j2 = titleContentColor;
                            long j3 = textContentColor;
                            long j4 = buttonContentColor;
                            final Function2<Composer, Integer, Unit> function8 = buttons;
                            $composer4.startReplaceableGroup(-483455358);
                            ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                            int $changed$iv$iv = (6 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume = $composer4.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density density$iv$iv = (Density) objConsume;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer4.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume3 = $composer4.consume(localViewConfiguration);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
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
                            int i10 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                            int i11 = ((6 >> 6) & 112) | 6;
                            final ColumnScope $this$invoke_u24lambda_u244 = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer4, 76440726, "C108@4081L373:AlertDialog.kt#uh7d8r");
                            $composer4.startReplaceableGroup(76440732);
                            ComposerKt.sourceInformation($composer4, "*61@2088L339");
                            if (function5 != null) {
                                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(j))}, ComposableLambdaKt.composableLambda($composer4, 934657765, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$1$1
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
                                        ComposerKt.sourceInformation($composer5, "C62@2180L229:AlertDialog.kt#uh7d8r");
                                        if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(934657765, $changed3, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:61)");
                                            }
                                            Modifier modifier$iv2 = $this$invoke_u24lambda_u244.align(PaddingKt.padding(Modifier.INSTANCE, AlertDialogKt.IconPadding), Alignment.INSTANCE.getCenterHorizontally());
                                            Function2<Composer, Integer, Unit> function9 = function5;
                                            int i12 = i9;
                                            $composer5.startReplaceableGroup(733328855);
                                            ComposerKt.sourceInformation($composer5, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                            MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                            int $changed$iv$iv2 = (0 << 3) & 112;
                                            $composer5.startReplaceableGroup(-1323940314);
                                            ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                            ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                            Object objConsume4 = $composer5.consume(localDensity2);
                                            ComposerKt.sourceInformationMarkerEnd($composer5);
                                            Density density$iv$iv2 = (Density) objConsume4;
                                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                            ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                            Object objConsume5 = $composer5.consume(localLayoutDirection2);
                                            ComposerKt.sourceInformationMarkerEnd($composer5);
                                            LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                            ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                            Object objConsume6 = $composer5.consume(localViewConfiguration2);
                                            ComposerKt.sourceInformationMarkerEnd($composer5);
                                            ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
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
                                            ComposerKt.sourceInformationMarkerStart($composer5, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                            int i14 = ((0 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer5, 691155642, "C67@2381L6:AlertDialog.kt#uh7d8r");
                                            function9.invoke($composer5, Integer.valueOf((i12 >> 6) & 14));
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
                                }), $composer4, 56);
                            }
                            $composer4.endReplaceableGroup();
                            $composer4.startReplaceableGroup(76441127);
                            ComposerKt.sourceInformation($composer4, "*72@2483L895");
                            if (function6 != null) {
                                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(j2))}, ComposableLambdaKt.composableLambda($composer4, 1845262876, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$2$1
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
                                        ComposerKt.sourceInformation($composer5, "C73@2606L10,74@2674L686:AlertDialog.kt#uh7d8r");
                                        if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1845262876, $changed3, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:72)");
                                            }
                                            TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), DialogTokens.INSTANCE.getHeadlineFont());
                                            final ColumnScope columnScope = $this$invoke_u24lambda_u244;
                                            final Function2<Composer, Integer, Unit> function9 = function5;
                                            final Function2<Composer, Integer, Unit> function10 = function6;
                                            final int i12 = i9;
                                            TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.composableLambda($composer5, 483464909, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$2$1.1
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

                                                public final void invoke(Composer $composer6, int $changed4) {
                                                    Alignment.Horizontal centerHorizontally;
                                                    ComposerKt.sourceInformation($composer6, "C75@2728L610:AlertDialog.kt#uh7d8r");
                                                    if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(483464909, $changed4, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:74)");
                                                        }
                                                        ColumnScope columnScope2 = columnScope;
                                                        Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, AlertDialogKt.TitlePadding);
                                                        if (function9 == null) {
                                                            centerHorizontally = Alignment.INSTANCE.getStart();
                                                        } else {
                                                            centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                                                        }
                                                        Modifier modifier$iv2 = columnScope2.align(modifierPadding, centerHorizontally);
                                                        Function2<Composer, Integer, Unit> function11 = function10;
                                                        int i13 = i12;
                                                        $composer6.startReplaceableGroup(733328855);
                                                        ComposerKt.sourceInformation($composer6, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                                        MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer6, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                                        int $changed$iv$iv2 = (0 << 3) & 112;
                                                        $composer6.startReplaceableGroup(-1323940314);
                                                        ComposerKt.sourceInformation($composer6, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                                        ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                        Object objConsume4 = $composer6.consume(localDensity2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        Density density$iv$iv2 = (Density) objConsume4;
                                                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                                        ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                        Object objConsume5 = $composer6.consume(localLayoutDirection2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                                                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                                        ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                        Object objConsume6 = $composer6.consume(localViewConfiguration2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                                                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                                        if (!($composer6.getApplier() instanceof Applier)) {
                                                            ComposablesKt.invalidApplier();
                                                        }
                                                        $composer6.startReusableNode();
                                                        if ($composer6.getInserting()) {
                                                            $composer6.createNode(constructor2);
                                                        } else {
                                                            $composer6.useNode();
                                                        }
                                                        $composer6.disableReusing();
                                                        Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer6);
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                                        $composer6.enableReusing();
                                                        function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                                        $composer6.startReplaceableGroup(2058660585);
                                                        int i14 = ($changed$iv$iv$iv2 >> 9) & 14;
                                                        ComposerKt.sourceInformationMarkerStart($composer6, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                        int i15 = ((0 >> 6) & 112) | 6;
                                                        ComposerKt.sourceInformationMarkerStart($composer6, 821918967, "C87@3305L7:AlertDialog.kt#uh7d8r");
                                                        function11.invoke($composer6, Integer.valueOf((i13 >> 9) & 14));
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        $composer6.endReplaceableGroup();
                                                        $composer6.endNode();
                                                        $composer6.endReplaceableGroup();
                                                        $composer6.endReplaceableGroup();
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    $composer6.skipToGroupEnd();
                                                }
                                            }), $composer5, 48);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer5.skipToGroupEnd();
                                    }
                                }), $composer4, 56);
                            }
                            $composer4.endReplaceableGroup();
                            $composer4.startReplaceableGroup(76442077);
                            ComposerKt.sourceInformation($composer4, "*93@3433L621");
                            if (function7 != null) {
                                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(j3))}, ComposableLambdaKt.composableLambda($composer4, 613970333, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$3$1
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
                                        ComposerKt.sourceInformation($composer5, "C95@3579L10,96@3653L383:AlertDialog.kt#uh7d8r");
                                        if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(613970333, $changed3, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:93)");
                                            }
                                            TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), DialogTokens.INSTANCE.getSupportingTextFont());
                                            final ColumnScope columnScope = $this$invoke_u24lambda_u244;
                                            final Function2<Composer, Integer, Unit> function9 = function7;
                                            final int i12 = i9;
                                            TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.composableLambda($composer5, -747827634, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$3$1.1
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

                                                public final void invoke(Composer $composer6, int $changed4) {
                                                    ComposerKt.sourceInformation($composer6, "C97@3707L307:AlertDialog.kt#uh7d8r");
                                                    if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-747827634, $changed4, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:96)");
                                                        }
                                                        ColumnScope columnScope2 = columnScope;
                                                        Modifier modifier$iv2 = columnScope2.align(PaddingKt.padding(columnScope2.weight(Modifier.INSTANCE, 1.0f, false), AlertDialogKt.TextPadding), Alignment.INSTANCE.getStart());
                                                        Function2<Composer, Integer, Unit> function10 = function9;
                                                        int i13 = i12;
                                                        $composer6.startReplaceableGroup(733328855);
                                                        ComposerKt.sourceInformation($composer6, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                                        MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer6, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                                        int $changed$iv$iv2 = (0 << 3) & 112;
                                                        $composer6.startReplaceableGroup(-1323940314);
                                                        ComposerKt.sourceInformation($composer6, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                                        ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                        Object objConsume4 = $composer6.consume(localDensity2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        Density density$iv$iv2 = (Density) objConsume4;
                                                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                                        ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                        Object objConsume5 = $composer6.consume(localLayoutDirection2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                                                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                                        ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                        Object objConsume6 = $composer6.consume(localViewConfiguration2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                                                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                                        if (!($composer6.getApplier() instanceof Applier)) {
                                                            ComposablesKt.invalidApplier();
                                                        }
                                                        $composer6.startReusableNode();
                                                        if ($composer6.getInserting()) {
                                                            $composer6.createNode(constructor2);
                                                        } else {
                                                            $composer6.useNode();
                                                        }
                                                        $composer6.disableReusing();
                                                        Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer6);
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                                        Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                                        $composer6.enableReusing();
                                                        function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                                        $composer6.startReplaceableGroup(2058660585);
                                                        int i14 = ($changed$iv$iv$iv2 >> 9) & 14;
                                                        ComposerKt.sourceInformationMarkerStart($composer6, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                        int i15 = ((0 >> 6) & 112) | 6;
                                                        ComposerKt.sourceInformationMarkerStart($composer6, 821919644, "C103@3982L6:AlertDialog.kt#uh7d8r");
                                                        function10.invoke($composer6, Integer.valueOf((i13 >> 12) & 14));
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        ComposerKt.sourceInformationMarkerEnd($composer6);
                                                        $composer6.endReplaceableGroup();
                                                        $composer6.endNode();
                                                        $composer6.endReplaceableGroup();
                                                        $composer6.endReplaceableGroup();
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    $composer6.skipToGroupEnd();
                                                }
                                            }), $composer5, 48);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer5.skipToGroupEnd();
                                    }
                                }), $composer4, 56);
                            }
                            $composer4.endReplaceableGroup();
                            Modifier modifier$iv2 = $this$invoke_u24lambda_u244.align(Modifier.INSTANCE, Alignment.INSTANCE.getEnd());
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv2 = (0 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume4 = $composer4.consume(localDensity2);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Density density$iv$iv2 = (Density) objConsume4;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume5 = $composer4.consume(localLayoutDirection2);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                            Object objConsume6 = $composer4.consume(localViewConfiguration2);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
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
                            int i12 = ($changed$iv$iv$iv2 >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i13 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, 24965084, "C109@4145L295:AlertDialog.kt#uh7d8r");
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(j4))}, ComposableLambdaKt.composableLambda($composer4, -433542216, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$4$1
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
                                    ComposerKt.sourceInformation($composer5, "C111@4293L10,112@4368L54:AlertDialog.kt#uh7d8r");
                                    if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-433542216, $changed3, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:109)");
                                        }
                                        TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), DialogTokens.INSTANCE.getActionLabelTextFont());
                                        TextKt.ProvideTextStyle(textStyle, function8, $composer5, (i9 << 3) & 112);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer5.skipToGroupEnd();
                                }
                            }), $composer4, 56);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceableGroup();
                            $composer4.endNode();
                            $composer4.endReplaceableGroup();
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
                }), $composer2, (($dirty >> 3) & 14) | 12582912 | (($dirty >> 12) & 112) | (($dirty >> 12) & 896) | (($dirty >> 9) & 57344), LocationRequestCompat.QUALITY_LOW_POWER);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier4 = modifier3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$2
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
                    AlertDialogKt.m1305AlertDialogContent4hvqGtA(buttons, modifier5, function2, function3, function4, shape, containerColor, tonalElevation, buttonContentColor, iconContentColor, titleContentColor, textContentColor, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty2 |= i2;
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 3670016) != 0) {
            if ($composer3.changed(containerColor)) {
                i3 = 1048576;
            } else {
                i3 = 524288;
            }
            $dirty2 |= i3;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if ((29360128 & $changed) != 0) {
            if ($composer3.changed(tonalElevation)) {
                i4 = 8388608;
            } else {
                i4 = 4194304;
            }
            $dirty2 |= i4;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if ((234881024 & $changed) != 0) {
            if ($composer3.changed(buttonContentColor)) {
                i5 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i5 = 33554432;
            }
            $dirty2 |= i5;
        }
        if ((i & 512) != 0) {
            $dirty2 |= 805306368;
        } else if ((1879048192 & $changed) != 0) {
            if ($composer3.changed(iconContentColor)) {
                i6 = 536870912;
            } else {
                i6 = 268435456;
            }
            $dirty2 |= i6;
        }
        $dirty = $dirty2;
        if ((i & 1024) != 0) {
            $dirty3 |= 6;
        } else if (($changed1 & 14) != 0) {
            if ($composer3.changed(titleContentColor)) {
                i7 = 4;
            } else {
                i7 = 2;
            }
            $dirty3 |= i7;
        }
        if ((i & 2048) != 0) {
            $dirty3 |= 48;
        } else if (($changed1 & 112) != 0) {
            $dirty3 |= $composer3.changed(textContentColor) ? 32 : 16;
        }
        $dirty1 = $dirty3;
        if ((1533916891 & $dirty) != 306783378) {
            if (i8 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1522575799, $dirty, $dirty1, "androidx.compose.material3.AlertDialogContent (AlertDialog.kt:37)");
            }
            $composer2 = $composer3;
            SurfaceKt.m1806SurfaceT9BRK9s(modifier3, shape, containerColor, 0L, tonalElevation, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -2126308228, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1
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
                    ComposerKt.sourceInformation($composer4, "C57@1973L2491:AlertDialog.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2126308228, $changed2, -1, "androidx.compose.material3.AlertDialogContent.<anonymous> (AlertDialog.kt:56)");
                        }
                        Modifier modifier$iv = PaddingKt.padding(Modifier.INSTANCE, AlertDialogKt.DialogPadding);
                        final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                        final Function2<? super Composer, ? super Integer, Unit> function6 = function3;
                        final Function2<? super Composer, ? super Integer, Unit> function7 = function4;
                        long j = iconContentColor;
                        final int i9 = $dirty;
                        long j2 = titleContentColor;
                        long j3 = textContentColor;
                        long j4 = buttonContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function8 = buttons;
                        $composer4.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                        int $changed$iv$iv = (6 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
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
                        int i10 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                        int i11 = ((6 >> 6) & 112) | 6;
                        final ColumnScope $this$invoke_u24lambda_u244 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer4, 76440726, "C108@4081L373:AlertDialog.kt#uh7d8r");
                        $composer4.startReplaceableGroup(76440732);
                        ComposerKt.sourceInformation($composer4, "*61@2088L339");
                        if (function5 != null) {
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(j))}, ComposableLambdaKt.composableLambda($composer4, 934657765, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$1$1
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
                                    ComposerKt.sourceInformation($composer5, "C62@2180L229:AlertDialog.kt#uh7d8r");
                                    if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(934657765, $changed3, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:61)");
                                        }
                                        Modifier modifier$iv2 = $this$invoke_u24lambda_u244.align(PaddingKt.padding(Modifier.INSTANCE, AlertDialogKt.IconPadding), Alignment.INSTANCE.getCenterHorizontally());
                                        Function2<Composer, Integer, Unit> function9 = function5;
                                        int i12 = i9;
                                        $composer5.startReplaceableGroup(733328855);
                                        ComposerKt.sourceInformation($composer5, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                        MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                        int $changed$iv$iv2 = (0 << 3) & 112;
                                        $composer5.startReplaceableGroup(-1323940314);
                                        ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                        Object objConsume4 = $composer5.consume(localDensity2);
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        Density density$iv$iv2 = (Density) objConsume4;
                                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                        Object objConsume5 = $composer5.consume(localLayoutDirection2);
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                        Object objConsume6 = $composer5.consume(localViewConfiguration2);
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
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
                                        ComposerKt.sourceInformationMarkerStart($composer5, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        int i14 = ((0 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer5, 691155642, "C67@2381L6:AlertDialog.kt#uh7d8r");
                                        function9.invoke($composer5, Integer.valueOf((i12 >> 6) & 14));
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
                            }), $composer4, 56);
                        }
                        $composer4.endReplaceableGroup();
                        $composer4.startReplaceableGroup(76441127);
                        ComposerKt.sourceInformation($composer4, "*72@2483L895");
                        if (function6 != null) {
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(j2))}, ComposableLambdaKt.composableLambda($composer4, 1845262876, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$2$1
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
                                    ComposerKt.sourceInformation($composer5, "C73@2606L10,74@2674L686:AlertDialog.kt#uh7d8r");
                                    if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1845262876, $changed3, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:72)");
                                        }
                                        TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), DialogTokens.INSTANCE.getHeadlineFont());
                                        final ColumnScope columnScope = $this$invoke_u24lambda_u244;
                                        final Function2<? super Composer, ? super Integer, Unit> function9 = function5;
                                        final Function2<? super Composer, ? super Integer, Unit> function10 = function6;
                                        final int i12 = i9;
                                        TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.composableLambda($composer5, 483464909, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$2$1.1
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

                                            public final void invoke(Composer $composer6, int $changed4) {
                                                Alignment.Horizontal centerHorizontally;
                                                ComposerKt.sourceInformation($composer6, "C75@2728L610:AlertDialog.kt#uh7d8r");
                                                if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(483464909, $changed4, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:74)");
                                                    }
                                                    ColumnScope columnScope2 = columnScope;
                                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, AlertDialogKt.TitlePadding);
                                                    if (function9 == null) {
                                                        centerHorizontally = Alignment.INSTANCE.getStart();
                                                    } else {
                                                        centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                                                    }
                                                    Modifier modifier$iv2 = columnScope2.align(modifierPadding, centerHorizontally);
                                                    Function2<Composer, Integer, Unit> function11 = function10;
                                                    int i13 = i12;
                                                    $composer6.startReplaceableGroup(733328855);
                                                    ComposerKt.sourceInformation($composer6, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                                    MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer6, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                                    int $changed$iv$iv2 = (0 << 3) & 112;
                                                    $composer6.startReplaceableGroup(-1323940314);
                                                    ComposerKt.sourceInformation($composer6, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume4 = $composer6.consume(localDensity2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    Density density$iv$iv2 = (Density) objConsume4;
                                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume5 = $composer6.consume(localLayoutDirection2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                                                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume6 = $composer6.consume(localViewConfiguration2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                                                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                                    if (!($composer6.getApplier() instanceof Applier)) {
                                                        ComposablesKt.invalidApplier();
                                                    }
                                                    $composer6.startReusableNode();
                                                    if ($composer6.getInserting()) {
                                                        $composer6.createNode(constructor2);
                                                    } else {
                                                        $composer6.useNode();
                                                    }
                                                    $composer6.disableReusing();
                                                    Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer6);
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                                    $composer6.enableReusing();
                                                    function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                                    $composer6.startReplaceableGroup(2058660585);
                                                    int i14 = ($changed$iv$iv$iv2 >> 9) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                    int i15 = ((0 >> 6) & 112) | 6;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 821918967, "C87@3305L7:AlertDialog.kt#uh7d8r");
                                                    function11.invoke($composer6, Integer.valueOf((i13 >> 9) & 14));
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    $composer6.endReplaceableGroup();
                                                    $composer6.endNode();
                                                    $composer6.endReplaceableGroup();
                                                    $composer6.endReplaceableGroup();
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                $composer6.skipToGroupEnd();
                                            }
                                        }), $composer5, 48);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer5.skipToGroupEnd();
                                }
                            }), $composer4, 56);
                        }
                        $composer4.endReplaceableGroup();
                        $composer4.startReplaceableGroup(76442077);
                        ComposerKt.sourceInformation($composer4, "*93@3433L621");
                        if (function7 != null) {
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(j3))}, ComposableLambdaKt.composableLambda($composer4, 613970333, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$3$1
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
                                    ComposerKt.sourceInformation($composer5, "C95@3579L10,96@3653L383:AlertDialog.kt#uh7d8r");
                                    if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(613970333, $changed3, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:93)");
                                        }
                                        TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), DialogTokens.INSTANCE.getSupportingTextFont());
                                        final ColumnScope columnScope = $this$invoke_u24lambda_u244;
                                        final Function2<? super Composer, ? super Integer, Unit> function9 = function7;
                                        final int i12 = i9;
                                        TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.composableLambda($composer5, -747827634, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$3$1.1
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

                                            public final void invoke(Composer $composer6, int $changed4) {
                                                ComposerKt.sourceInformation($composer6, "C97@3707L307:AlertDialog.kt#uh7d8r");
                                                if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-747827634, $changed4, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:96)");
                                                    }
                                                    ColumnScope columnScope2 = columnScope;
                                                    Modifier modifier$iv2 = columnScope2.align(PaddingKt.padding(columnScope2.weight(Modifier.INSTANCE, 1.0f, false), AlertDialogKt.TextPadding), Alignment.INSTANCE.getStart());
                                                    Function2<Composer, Integer, Unit> function10 = function9;
                                                    int i13 = i12;
                                                    $composer6.startReplaceableGroup(733328855);
                                                    ComposerKt.sourceInformation($composer6, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                                    MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer6, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                                    int $changed$iv$iv2 = (0 << 3) & 112;
                                                    $composer6.startReplaceableGroup(-1323940314);
                                                    ComposerKt.sourceInformation($composer6, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume4 = $composer6.consume(localDensity2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    Density density$iv$iv2 = (Density) objConsume4;
                                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume5 = $composer6.consume(localLayoutDirection2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                                                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume6 = $composer6.consume(localViewConfiguration2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                                                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                                    if (!($composer6.getApplier() instanceof Applier)) {
                                                        ComposablesKt.invalidApplier();
                                                    }
                                                    $composer6.startReusableNode();
                                                    if ($composer6.getInserting()) {
                                                        $composer6.createNode(constructor2);
                                                    } else {
                                                        $composer6.useNode();
                                                    }
                                                    $composer6.disableReusing();
                                                    Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer6);
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                                    $composer6.enableReusing();
                                                    function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                                    $composer6.startReplaceableGroup(2058660585);
                                                    int i14 = ($changed$iv$iv$iv2 >> 9) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                    int i15 = ((0 >> 6) & 112) | 6;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 821919644, "C103@3982L6:AlertDialog.kt#uh7d8r");
                                                    function10.invoke($composer6, Integer.valueOf((i13 >> 12) & 14));
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    $composer6.endReplaceableGroup();
                                                    $composer6.endNode();
                                                    $composer6.endReplaceableGroup();
                                                    $composer6.endReplaceableGroup();
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                $composer6.skipToGroupEnd();
                                            }
                                        }), $composer5, 48);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer5.skipToGroupEnd();
                                }
                            }), $composer4, 56);
                        }
                        $composer4.endReplaceableGroup();
                        Modifier modifier$iv2 = $this$invoke_u24lambda_u244.align(Modifier.INSTANCE, Alignment.INSTANCE.getEnd());
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv2 = (0 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume4 = $composer4.consume(localDensity2);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv2 = (Density) objConsume4;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume5 = $composer4.consume(localLayoutDirection2);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume6 = $composer4.consume(localViewConfiguration2);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
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
                        int i12 = ($changed$iv$iv$iv2 >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i13 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 24965084, "C109@4145L295:AlertDialog.kt#uh7d8r");
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(j4))}, ComposableLambdaKt.composableLambda($composer4, -433542216, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$4$1
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
                                ComposerKt.sourceInformation($composer5, "C111@4293L10,112@4368L54:AlertDialog.kt#uh7d8r");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-433542216, $changed3, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:109)");
                                    }
                                    TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), DialogTokens.INSTANCE.getActionLabelTextFont());
                                    TextKt.ProvideTextStyle(textStyle, function8, $composer5, (i9 << 3) & 112);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }), $composer4, 56);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
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
            }), $composer2, (($dirty >> 3) & 14) | 12582912 | (($dirty >> 12) & 112) | (($dirty >> 12) & 896) | (($dirty >> 9) & 57344), LocationRequestCompat.QUALITY_LOW_POWER);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
        } else {
            if (i8 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1522575799, $dirty, $dirty1, "androidx.compose.material3.AlertDialogContent (AlertDialog.kt:37)");
            }
            $composer2 = $composer3;
            SurfaceKt.m1806SurfaceT9BRK9s(modifier3, shape, containerColor, 0L, tonalElevation, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -2126308228, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1
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
                    ComposerKt.sourceInformation($composer4, "C57@1973L2491:AlertDialog.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2126308228, $changed2, -1, "androidx.compose.material3.AlertDialogContent.<anonymous> (AlertDialog.kt:56)");
                        }
                        Modifier modifier$iv = PaddingKt.padding(Modifier.INSTANCE, AlertDialogKt.DialogPadding);
                        final Function2<? super Composer, ? super Integer, Unit> function5 = function2;
                        final Function2<? super Composer, ? super Integer, Unit> function6 = function3;
                        final Function2<? super Composer, ? super Integer, Unit> function7 = function4;
                        long j = iconContentColor;
                        final int i9 = $dirty;
                        long j2 = titleContentColor;
                        long j3 = textContentColor;
                        long j4 = buttonContentColor;
                        final Function2<? super Composer, ? super Integer, Unit> function8 = buttons;
                        $composer4.startReplaceableGroup(-483455358);
                        ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                        int $changed$iv$iv = (6 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer4.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv = (Density) objConsume;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv = (LayoutDirection) objConsume2;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localViewConfiguration);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
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
                        int i10 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                        int i11 = ((6 >> 6) & 112) | 6;
                        final ColumnScope $this$invoke_u24lambda_u244 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer4, 76440726, "C108@4081L373:AlertDialog.kt#uh7d8r");
                        $composer4.startReplaceableGroup(76440732);
                        ComposerKt.sourceInformation($composer4, "*61@2088L339");
                        if (function5 != null) {
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(j))}, ComposableLambdaKt.composableLambda($composer4, 934657765, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$1$1
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
                                    ComposerKt.sourceInformation($composer5, "C62@2180L229:AlertDialog.kt#uh7d8r");
                                    if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(934657765, $changed3, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:61)");
                                        }
                                        Modifier modifier$iv2 = $this$invoke_u24lambda_u244.align(PaddingKt.padding(Modifier.INSTANCE, AlertDialogKt.IconPadding), Alignment.INSTANCE.getCenterHorizontally());
                                        Function2<Composer, Integer, Unit> function9 = function5;
                                        int i12 = i9;
                                        $composer5.startReplaceableGroup(733328855);
                                        ComposerKt.sourceInformation($composer5, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                        MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                        int $changed$iv$iv2 = (0 << 3) & 112;
                                        $composer5.startReplaceableGroup(-1323940314);
                                        ComposerKt.sourceInformation($composer5, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                        Object objConsume4 = $composer5.consume(localDensity2);
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        Density density$iv$iv2 = (Density) objConsume4;
                                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                        Object objConsume5 = $composer5.consume(localLayoutDirection2);
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                        ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                        Object objConsume6 = $composer5.consume(localViewConfiguration2);
                                        ComposerKt.sourceInformationMarkerEnd($composer5);
                                        ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
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
                                        ComposerKt.sourceInformationMarkerStart($composer5, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        int i14 = ((0 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer5, 691155642, "C67@2381L6:AlertDialog.kt#uh7d8r");
                                        function9.invoke($composer5, Integer.valueOf((i12 >> 6) & 14));
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
                            }), $composer4, 56);
                        }
                        $composer4.endReplaceableGroup();
                        $composer4.startReplaceableGroup(76441127);
                        ComposerKt.sourceInformation($composer4, "*72@2483L895");
                        if (function6 != null) {
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(j2))}, ComposableLambdaKt.composableLambda($composer4, 1845262876, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$2$1
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
                                    ComposerKt.sourceInformation($composer5, "C73@2606L10,74@2674L686:AlertDialog.kt#uh7d8r");
                                    if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1845262876, $changed3, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:72)");
                                        }
                                        TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), DialogTokens.INSTANCE.getHeadlineFont());
                                        final ColumnScope columnScope = $this$invoke_u24lambda_u244;
                                        final Function2<? super Composer, ? super Integer, Unit> function9 = function5;
                                        final Function2<? super Composer, ? super Integer, Unit> function10 = function6;
                                        final int i12 = i9;
                                        TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.composableLambda($composer5, 483464909, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$2$1.1
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

                                            public final void invoke(Composer $composer6, int $changed4) {
                                                Alignment.Horizontal centerHorizontally;
                                                ComposerKt.sourceInformation($composer6, "C75@2728L610:AlertDialog.kt#uh7d8r");
                                                if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(483464909, $changed4, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:74)");
                                                    }
                                                    ColumnScope columnScope2 = columnScope;
                                                    Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, AlertDialogKt.TitlePadding);
                                                    if (function9 == null) {
                                                        centerHorizontally = Alignment.INSTANCE.getStart();
                                                    } else {
                                                        centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                                                    }
                                                    Modifier modifier$iv2 = columnScope2.align(modifierPadding, centerHorizontally);
                                                    Function2<Composer, Integer, Unit> function11 = function10;
                                                    int i13 = i12;
                                                    $composer6.startReplaceableGroup(733328855);
                                                    ComposerKt.sourceInformation($composer6, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                                    MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer6, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                                    int $changed$iv$iv2 = (0 << 3) & 112;
                                                    $composer6.startReplaceableGroup(-1323940314);
                                                    ComposerKt.sourceInformation($composer6, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume4 = $composer6.consume(localDensity2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    Density density$iv$iv2 = (Density) objConsume4;
                                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume5 = $composer6.consume(localLayoutDirection2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                                                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume6 = $composer6.consume(localViewConfiguration2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                                                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                                    if (!($composer6.getApplier() instanceof Applier)) {
                                                        ComposablesKt.invalidApplier();
                                                    }
                                                    $composer6.startReusableNode();
                                                    if ($composer6.getInserting()) {
                                                        $composer6.createNode(constructor2);
                                                    } else {
                                                        $composer6.useNode();
                                                    }
                                                    $composer6.disableReusing();
                                                    Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer6);
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                                    $composer6.enableReusing();
                                                    function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                                    $composer6.startReplaceableGroup(2058660585);
                                                    int i14 = ($changed$iv$iv$iv2 >> 9) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                    int i15 = ((0 >> 6) & 112) | 6;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 821918967, "C87@3305L7:AlertDialog.kt#uh7d8r");
                                                    function11.invoke($composer6, Integer.valueOf((i13 >> 9) & 14));
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    $composer6.endReplaceableGroup();
                                                    $composer6.endNode();
                                                    $composer6.endReplaceableGroup();
                                                    $composer6.endReplaceableGroup();
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                $composer6.skipToGroupEnd();
                                            }
                                        }), $composer5, 48);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer5.skipToGroupEnd();
                                }
                            }), $composer4, 56);
                        }
                        $composer4.endReplaceableGroup();
                        $composer4.startReplaceableGroup(76442077);
                        ComposerKt.sourceInformation($composer4, "*93@3433L621");
                        if (function7 != null) {
                            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(j3))}, ComposableLambdaKt.composableLambda($composer4, 613970333, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$3$1
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
                                    ComposerKt.sourceInformation($composer5, "C95@3579L10,96@3653L383:AlertDialog.kt#uh7d8r");
                                    if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(613970333, $changed3, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:93)");
                                        }
                                        TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), DialogTokens.INSTANCE.getSupportingTextFont());
                                        final ColumnScope columnScope = $this$invoke_u24lambda_u244;
                                        final Function2<? super Composer, ? super Integer, Unit> function9 = function7;
                                        final int i12 = i9;
                                        TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.composableLambda($composer5, -747827634, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$3$1.1
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

                                            public final void invoke(Composer $composer6, int $changed4) {
                                                ComposerKt.sourceInformation($composer6, "C97@3707L307:AlertDialog.kt#uh7d8r");
                                                if (($changed4 & 11) != 2 || !$composer6.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-747827634, $changed4, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:96)");
                                                    }
                                                    ColumnScope columnScope2 = columnScope;
                                                    Modifier modifier$iv2 = columnScope2.align(PaddingKt.padding(columnScope2.weight(Modifier.INSTANCE, 1.0f, false), AlertDialogKt.TextPadding), Alignment.INSTANCE.getStart());
                                                    Function2<Composer, Integer, Unit> function10 = function9;
                                                    int i13 = i12;
                                                    $composer6.startReplaceableGroup(733328855);
                                                    ComposerKt.sourceInformation($composer6, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                                    MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer6, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                                    int $changed$iv$iv2 = (0 << 3) & 112;
                                                    $composer6.startReplaceableGroup(-1323940314);
                                                    ComposerKt.sourceInformation($composer6, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                                                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume4 = $composer6.consume(localDensity2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    Density density$iv$iv2 = (Density) objConsume4;
                                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume5 = $composer6.consume(localLayoutDirection2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                                                    ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "C:CompositionLocal.kt#9igjgp");
                                                    Object objConsume6 = $composer6.consume(localViewConfiguration2);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
                                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifier$iv2);
                                                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
                                                    if (!($composer6.getApplier() instanceof Applier)) {
                                                        ComposablesKt.invalidApplier();
                                                    }
                                                    $composer6.startReusableNode();
                                                    if ($composer6.getInserting()) {
                                                        $composer6.createNode(constructor2);
                                                    } else {
                                                        $composer6.useNode();
                                                    }
                                                    $composer6.disableReusing();
                                                    Composer $this$Layout_u24lambda_u2d0$iv$iv2 = Updater.m2603constructorimpl($composer6);
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, density$iv$iv2, ComposeUiNode.INSTANCE.getSetDensity());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                                    Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv2, viewConfiguration$iv$iv2, ComposeUiNode.INSTANCE.getSetViewConfiguration());
                                                    $composer6.enableReusing();
                                                    function3MaterializerOf2.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer6)), $composer6, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
                                                    $composer6.startReplaceableGroup(2058660585);
                                                    int i14 = ($changed$iv$iv$iv2 >> 9) & 14;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                    int i15 = ((0 >> 6) & 112) | 6;
                                                    ComposerKt.sourceInformationMarkerStart($composer6, 821919644, "C103@3982L6:AlertDialog.kt#uh7d8r");
                                                    function10.invoke($composer6, Integer.valueOf((i13 >> 12) & 14));
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    ComposerKt.sourceInformationMarkerEnd($composer6);
                                                    $composer6.endReplaceableGroup();
                                                    $composer6.endNode();
                                                    $composer6.endReplaceableGroup();
                                                    $composer6.endReplaceableGroup();
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                $composer6.skipToGroupEnd();
                                            }
                                        }), $composer5, 48);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer5.skipToGroupEnd();
                                }
                            }), $composer4, 56);
                        }
                        $composer4.endReplaceableGroup();
                        Modifier modifier$iv2 = $this$invoke_u24lambda_u244.align(Modifier.INSTANCE, Alignment.INSTANCE.getEnd());
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv2 = (0 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "C(Layout)P(!1,2)74@2915L7,75@2970L7,76@3029L7,77@3041L460:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume4 = $composer4.consume(localDensity2);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Density density$iv$iv2 = (Density) objConsume4;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume5 = $composer4.consume(localLayoutDirection2);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume5;
                        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration2 = CompositionLocalsKt.getLocalViewConfiguration();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume6 = $composer4.consume(localViewConfiguration2);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ViewConfiguration viewConfiguration$iv$iv2 = (ViewConfiguration) objConsume6;
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
                        int i12 = ($changed$iv$iv$iv2 >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i13 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 24965084, "C109@4145L295:AlertDialog.kt#uh7d8r");
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(j4))}, ComposableLambdaKt.composableLambda($composer4, -433542216, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$1$1$4$1
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
                                ComposerKt.sourceInformation($composer5, "C111@4293L10,112@4368L54:AlertDialog.kt#uh7d8r");
                                if (($changed3 & 11) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-433542216, $changed3, -1, "androidx.compose.material3.AlertDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:109)");
                                    }
                                    TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), DialogTokens.INSTANCE.getActionLabelTextFont());
                                    TextKt.ProvideTextStyle(textStyle, function8, $composer5, (i9 << 3) & 112);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }), $composer4, 56);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endReplaceableGroup();
                        $composer4.endNode();
                        $composer4.endReplaceableGroup();
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
            }), $composer2, (($dirty >> 3) & 14) | 12582912 | (($dirty >> 12) & 112) | (($dirty >> 12) & 896) | (($dirty >> 9) & 57344), LocationRequestCompat.QUALITY_LOW_POWER);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogContent$2
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
                AlertDialogKt.m1305AlertDialogContent4hvqGtA(buttons, modifier6, function2, function3, function4, shape, containerColor, tonalElevation, buttonContentColor, iconContentColor, titleContentColor, textContentColor, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX INFO: renamed from: AlertDialogFlowRow-ixp7dh8, reason: not valid java name */
    public static final void m1306AlertDialogFlowRowixp7dh8(final float mainAxisSpacing, final float crossAxisSpacing, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(586821353);
        ComposerKt.sourceInformation($composer2, "C(AlertDialogFlowRow)P(2:c#ui.unit.Dp,1:c#ui.unit.Dp)129@4728L3185:AlertDialog.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(mainAxisSpacing) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(crossAxisSpacing) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 256 : 128;
        }
        if (($dirty & 731) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(586821353, $dirty, -1, "androidx.compose.material3.AlertDialogFlowRow (AlertDialog.kt:124)");
            }
            MeasurePolicy measurePolicy$iv = new MeasurePolicy() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogFlowRow$1
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
                public final MeasureResult mo11measure3p2s80s(final MeasureScope Layout, List<? extends Measurable> measurables, long constraints) {
                    Intrinsics.checkNotNullParameter(Layout, "$this$Layout");
                    Intrinsics.checkNotNullParameter(measurables, "measurables");
                    final List sequences = new ArrayList();
                    List crossAxisSizes = new ArrayList();
                    final List crossAxisPositions = new ArrayList();
                    Ref.IntRef mainAxisSpace = new Ref.IntRef();
                    Ref.IntRef crossAxisSpace = new Ref.IntRef();
                    List currentSequence = new ArrayList();
                    Ref.IntRef currentMainAxisSize = new Ref.IntRef();
                    Ref.IntRef currentCrossAxisSize = new Ref.IntRef();
                    for (Measurable measurable : measurables) {
                        Placeable placeable = measurable.mo4225measureBRTryo0(constraints);
                        if (!measure_3p2s80s$canAddToCurrentSequence(currentSequence, currentMainAxisSize, Layout, mainAxisSpacing, constraints, placeable)) {
                            measure_3p2s80s$startNewSequence(sequences, crossAxisSpace, Layout, crossAxisSpacing, currentSequence, crossAxisSizes, currentCrossAxisSize, crossAxisPositions, mainAxisSpace, currentMainAxisSize);
                        }
                        if (!(!currentSequence.isEmpty())) {
                            currentMainAxisSize = currentMainAxisSize;
                        } else {
                            currentMainAxisSize = currentMainAxisSize;
                            currentMainAxisSize.element += Layout.mo321roundToPx0680j_4(mainAxisSpacing);
                        }
                        currentSequence.add(placeable);
                        currentMainAxisSize.element += placeable.getWidth();
                        currentCrossAxisSize.element = Math.max(currentCrossAxisSize.element, placeable.getHeight());
                        currentCrossAxisSize = currentCrossAxisSize;
                    }
                    Ref.IntRef currentCrossAxisSize2 = currentCrossAxisSize;
                    if (!currentSequence.isEmpty()) {
                        measure_3p2s80s$startNewSequence(sequences, crossAxisSpace, Layout, crossAxisSpacing, currentSequence, crossAxisSizes, currentCrossAxisSize2, crossAxisPositions, mainAxisSpace, currentMainAxisSize);
                    }
                    final int mainAxisLayoutSize = Math.max(mainAxisSpace.element, Constraints.m5220getMinWidthimpl(constraints));
                    int crossAxisLayoutSize = Math.max(crossAxisSpace.element, Constraints.m5219getMinHeightimpl(constraints));
                    final float f = mainAxisSpacing;
                    return MeasureScope.CC.layout$default(Layout, mainAxisLayoutSize, crossAxisLayoutSize, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogFlowRow$1$measure$1
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
                            Intrinsics.checkNotNullParameter(layout, "$this$layout");
                            Iterable $this$forEachIndexed$iv = sequences;
                            MeasureScope measureScope = Layout;
                            float f2 = f;
                            int i = mainAxisLayoutSize;
                            List<Integer> list = crossAxisPositions;
                            int index$iv = 0;
                            for (Object item$iv : $this$forEachIndexed$iv) {
                                int index$iv2 = index$iv + 1;
                                if (index$iv < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                List placeables = (List) item$iv;
                                int i2 = index$iv;
                                int size = placeables.size();
                                int[] childrenMainAxisSizes = new int[size];
                                int i3 = 0;
                                while (i3 < size) {
                                    childrenMainAxisSizes[i3] = ((Placeable) placeables.get(i3)).getWidth() + (i3 < CollectionsKt.getLastIndex(placeables) ? measureScope.mo321roundToPx0680j_4(f2) : 0);
                                    i3++;
                                }
                                Arrangement.Horizontal arrangement = Arrangement.INSTANCE.getEnd();
                                int length = childrenMainAxisSizes.length;
                                int[] mainAxisPositions = new int[length];
                                for (int i4 = 0; i4 < length; i4++) {
                                    mainAxisPositions[i4] = 0;
                                }
                                arrangement.arrange(measureScope, i, childrenMainAxisSizes, measureScope.getLayoutDirection(), mainAxisPositions);
                                List $this$forEachIndexed$iv2 = placeables;
                                int index$iv3 = 0;
                                for (Object item$iv2 : $this$forEachIndexed$iv2) {
                                    int index$iv4 = index$iv3 + 1;
                                    if (index$iv3 < 0) {
                                        CollectionsKt.throwIndexOverflow();
                                    }
                                    Placeable placeable2 = (Placeable) item$iv2;
                                    int j = index$iv3;
                                    Placeable.PlacementScope.place$default(layout, placeable2, mainAxisPositions[j], list.get(i2).intValue(), 0.0f, 4, null);
                                    index$iv3 = index$iv4;
                                    placeables = placeables;
                                }
                                index$iv = index$iv2;
                            }
                        }
                    }, 4, null);
                }

                private static final boolean measure_3p2s80s$canAddToCurrentSequence(List<Placeable> list, Ref.IntRef currentMainAxisSize, MeasureScope $this_Layout, float $mainAxisSpacing, long $constraints, Placeable placeable) {
                    return list.isEmpty() || (currentMainAxisSize.element + $this_Layout.mo321roundToPx0680j_4($mainAxisSpacing)) + placeable.getWidth() <= Constraints.m5218getMaxWidthimpl($constraints);
                }

                private static final void measure_3p2s80s$startNewSequence(List<List<Placeable>> list, Ref.IntRef crossAxisSpace, MeasureScope $this_Layout, float $crossAxisSpacing, List<Placeable> list2, List<Integer> list3, Ref.IntRef currentCrossAxisSize, List<Integer> list4, Ref.IntRef mainAxisSpace, Ref.IntRef currentMainAxisSize) {
                    if (!list.isEmpty()) {
                        crossAxisSpace.element += $this_Layout.mo321roundToPx0680j_4($crossAxisSpacing);
                    }
                    list.add(CollectionsKt.toList(list2));
                    list3.add(Integer.valueOf(currentCrossAxisSize.element));
                    list4.add(Integer.valueOf(crossAxisSpace.element));
                    crossAxisSpace.element += currentCrossAxisSize.element;
                    mainAxisSpace.element = Math.max(mainAxisSpace.element, currentMainAxisSize.element);
                    list2.clear();
                    currentMainAxisSize.element = 0;
                    currentCrossAxisSize.element = 0;
                }
            };
            int $changed$iv = ($dirty >> 6) & 14;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)73@2855L7,74@2910L7,75@2969L7,76@2981L460:Layout.kt#80mrfh");
            Modifier modifier$iv = Modifier.INSTANCE;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density$iv = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection$iv = (LayoutDirection) objConsume2;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ViewConfiguration viewConfiguration$iv = (ViewConfiguration) objConsume3;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
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
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, density$iv, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, layoutDirection$iv, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, viewConfiguration$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            content.invoke($composer2, Integer.valueOf(($changed$iv$iv >> 9) & 14));
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogFlowRow$2
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

            public final void invoke(Composer composer, int i) {
                AlertDialogKt.m1306AlertDialogFlowRowixp7dh8(mainAxisSpacing, crossAxisSpacing, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    public static final float getDialogMinWidth() {
        return DialogMinWidth;
    }

    public static final float getDialogMaxWidth() {
        return DialogMaxWidth;
    }
}
