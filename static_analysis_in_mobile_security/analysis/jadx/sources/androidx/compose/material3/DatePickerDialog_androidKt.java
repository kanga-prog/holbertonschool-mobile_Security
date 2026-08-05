package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.tokens.DatePickerModalTokens;
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
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.DialogProperties;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DatePickerDialog.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u009d\u0001\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00070\u0017¢\u0006\u0002\b\u000b¢\u0006\u0002\b\u0019H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"DialogButtonsCrossAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "DialogButtonsMainAxisSpacing", "DialogButtonsPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "DatePickerDialog", "", "onDismissRequest", "Lkotlin/Function0;", "confirmButton", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "dismissButton", "shape", "Landroidx/compose/ui/graphics/Shape;", "tonalElevation", "colors", "Landroidx/compose/material3/DatePickerColors;", "properties", "Landroidx/compose/ui/window/DialogProperties;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "DatePickerDialog-GmEhDVc", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;FLandroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DatePickerDialog_androidKt {
    private static final PaddingValues DialogButtonsPadding = PaddingKt.m484PaddingValuesa9UjIt4$default(0.0f, 0.0f, Dp.m5274constructorimpl(6), Dp.m5274constructorimpl(8), 3, null);
    private static final float DialogButtonsMainAxisSpacing = Dp.m5274constructorimpl(8);
    private static final float DialogButtonsCrossAxisSpacing = Dp.m5274constructorimpl(12);

    /* JADX WARN: Code duplicated, block: B:104:0x014b  */
    /* JADX WARN: Code duplicated, block: B:106:0x0158  */
    /* JADX WARN: Code duplicated, block: B:116:0x018c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:117:0x018e  */
    /* JADX WARN: Code duplicated, block: B:118:0x0193  */
    /* JADX WARN: Code duplicated, block: B:120:0x0197  */
    /* JADX WARN: Code duplicated, block: B:121:0x0199  */
    /* JADX WARN: Code duplicated, block: B:124:0x019f  */
    /* JADX WARN: Code duplicated, block: B:125:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:127:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:128:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:131:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:132:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:134:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:135:0x0216  */
    /* JADX WARN: Code duplicated, block: B:138:0x022a  */
    /* JADX WARN: Code duplicated, block: B:141:0x0279  */
    /* JADX WARN: Code duplicated, block: B:145:0x028c  */
    /* JADX WARN: Code duplicated, block: B:146:0x028f  */
    /* JADX INFO: renamed from: DatePickerDialog-GmEhDVc, reason: not valid java name */
    public static final void m1475DatePickerDialogGmEhDVc(final Function0<Unit> onDismissRequest, final Function2<? super Composer, ? super Integer, Unit> confirmButton, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Shape shape, float tonalElevation, DatePickerColors colors, DialogProperties properties, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        float f;
        DatePickerColors datePickerColors;
        int i2;
        Modifier.Companion modifier2;
        Function2<? super Composer, ? super Integer, Unit> function3;
        Shape shape3;
        float tonalElevation2;
        DatePickerColors colors2;
        DialogProperties properties2;
        int $dirty;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Shape shape4;
        float tonalElevation3;
        DatePickerColors colors3;
        Modifier modifier4;
        DialogProperties properties3;
        DatePickerColors colors4;
        float tonalElevation4;
        Shape shape5;
        Function2<? super Composer, ? super Integer, Unit> function5;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onDismissRequest, "onDismissRequest");
        Intrinsics.checkNotNullParameter(confirmButton, "confirmButton");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-36517340);
        ComposerKt.sourceInformation($composer2, "C(DatePickerDialog)P(5,1,4,3,7,8:c#ui.unit.Dp!1,6)69@3485L5,71@3602L8,75@3754L1602:DatePickerDialog.android.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changedInstance(onDismissRequest) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changedInstance(confirmButton) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i5 = $composer2.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = tonalElevation;
        } else if ((458752 & $changed) == 0) {
            f = tonalElevation;
            $dirty2 |= $composer2.changed(f) ? 131072 : 65536;
        } else {
            f = tonalElevation;
        }
        if ((3670016 & $changed) == 0) {
            if ((i & 64) == 0) {
                datePickerColors = colors;
                int i7 = $composer2.changed(datePickerColors) ? 1048576 : 524288;
                $dirty2 |= i7;
            } else {
                datePickerColors = colors;
            }
            $dirty2 |= i7;
        } else {
            datePickerColors = colors;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty2 |= $composer2.changed(properties) ? 8388608 : 4194304;
        }
        if ((i & 256) == 0) {
            if ((234881024 & $changed) == 0) {
                i2 = $composer2.changedInstance(content) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
            }
            if ((191739611 & $dirty2) == 38347922 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    } else {
                        function3 = function2;
                    }
                    if ((i & 16) != 0) {
                        shape3 = DatePickerDefaults.INSTANCE.getShape($composer2, 6);
                        $dirty2 &= -57345;
                    } else {
                        shape3 = shape2;
                    }
                    if (i6 != 0) {
                        tonalElevation2 = DatePickerDefaults.INSTANCE.m1474getTonalElevationD9Ej5fM();
                    } else {
                        tonalElevation2 = f;
                    }
                    if ((i & 64) != 0) {
                        colors2 = DatePickerDefaults.INSTANCE.m1473colors1m2CgY(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 805306368, 524287);
                        $dirty2 &= -3670017;
                    } else {
                        colors2 = datePickerColors;
                    }
                    if (i8 != 0) {
                        properties2 = new DialogProperties(false, false, null, false, false, 23, null);
                        $dirty = $dirty2;
                        modifier3 = modifier2;
                        function4 = function3;
                        shape4 = shape3;
                        tonalElevation3 = tonalElevation2;
                        colors3 = colors2;
                    } else {
                        properties2 = properties;
                        $dirty = $dirty2;
                        modifier3 = modifier2;
                        function4 = function3;
                        shape4 = shape3;
                        tonalElevation3 = tonalElevation2;
                        colors3 = colors2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 64) != 0) {
                        properties2 = properties;
                        $dirty = $dirty2 & (-3670017);
                        shape4 = shape2;
                        tonalElevation3 = f;
                        colors3 = datePickerColors;
                        modifier3 = modifier;
                        function4 = function2;
                    } else {
                        properties2 = properties;
                        $dirty = $dirty2;
                        shape4 = shape2;
                        tonalElevation3 = f;
                        colors3 = datePickerColors;
                        modifier3 = modifier;
                        function4 = function2;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-36517340, $dirty, -1, "androidx.compose.material3.DatePickerDialog (DatePickerDialog.android.kt:64)");
                }
                final Shape shape6 = shape4;
                final DatePickerColors datePickerColors2 = colors3;
                final float f2 = tonalElevation3;
                final int i9 = $dirty;
                modifier4 = modifier3;
                final Function2<? super Composer, ? super Integer, Unit> function6 = function4;
                int $dirty3 = $dirty;
                AndroidAlertDialog_androidKt.AlertDialog(onDismissRequest, SizeKt.wrapContentHeight$default(modifier3, null, false, 3, null), properties2, ComposableLambdaKt.composableLambda($composer2, -476003174, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1
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
                        ComposerKt.sourceInformation($composer3, "C80@3909L1441:DatePickerDialog.android.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-476003174, $changed2, -1, "androidx.compose.material3.DatePickerDialog.<anonymous> (DatePickerDialog.android.kt:79)");
                            }
                            Modifier modifierM522heightInVpY3zN4$default = SizeKt.m522heightInVpY3zN4$default(SizeKt.m531requiredWidth3ABfNKs(Modifier.INSTANCE, DatePickerModalTokens.INSTANCE.m2115getContainerWidthD9Ej5fM()), 0.0f, DatePickerModalTokens.INSTANCE.m2114getContainerHeightD9Ej5fM(), 1, null);
                            Shape shape7 = shape6;
                            long containerColor = datePickerColors2.getContainerColor();
                            float f3 = f2;
                            final Function3<ColumnScope, Composer, Integer, Unit> function7 = content;
                            final int i10 = i9;
                            final Function2<Composer, Integer, Unit> function8 = function6;
                            final Function2<Composer, Integer, Unit> function9 = confirmButton;
                            ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer3, 1763752415, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1.1
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
                                    ComposerKt.sourceInformation($composer4, "C88@4230L1110:DatePickerDialog.android.kt#uh7d8r");
                                    if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1763752415, $changed3, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous> (DatePickerDialog.android.kt:87)");
                                        }
                                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getSpaceBetween();
                                        Function3<ColumnScope, Composer, Integer, Unit> function10 = function7;
                                        final int i11 = i10;
                                        final Function2<Composer, Integer, Unit> function11 = function8;
                                        final Function2<Composer, Integer, Unit> function12 = function9;
                                        $composer4.startReplaceableGroup(-483455358);
                                        ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                        Modifier modifier$iv = Modifier.INSTANCE;
                                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                        int $changed$iv$iv = (48 << 3) & 112;
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
                                        int i12 = ($changed$iv$iv$iv >> 9) & 14;
                                        ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                        ColumnScope $this$invoke_u24lambda_u241 = ColumnScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart($composer4, 1146374379, "C89@4303L9,91@4356L970:DatePickerDialog.android.kt#uh7d8r");
                                        function10.invoke($this$invoke_u24lambda_u241, $composer4, Integer.valueOf(((((48 >> 6) & 112) | 6) & 14) | ((i11 >> 21) & 112)));
                                        Modifier modifier$iv2 = PaddingKt.padding($this$invoke_u24lambda_u241.align(Modifier.INSTANCE, Alignment.INSTANCE.getEnd()), DatePickerDialog_androidKt.DialogButtonsPadding);
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
                                        int i13 = ($changed$iv$iv$iv2 >> 9) & 14;
                                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        int i14 = ((0 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer4, -552861189, "C97@4653L9,96@4542L766:DatePickerDialog.android.kt#uh7d8r");
                                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(ColorSchemeKt.toColor(DialogTokens.INSTANCE.getActionLabelTextColor(), $composer4, 6)))}, ComposableLambdaKt.composableLambda($composer4, -926980325, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$1$1
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

                                            public final void invoke(Composer $composer5, int $changed4) {
                                                ComposerKt.sourceInformation($composer5, "C100@4769L10,101@4848L438:DatePickerDialog.android.kt#uh7d8r");
                                                if (($changed4 & 11) != 2 || !$composer5.getSkipping()) {
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-926980325, $changed4, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:98)");
                                                    }
                                                    TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), DialogTokens.INSTANCE.getActionLabelTextFont());
                                                    final Function2<Composer, Integer, Unit> function13 = function11;
                                                    final int i15 = i11;
                                                    final Function2<Composer, Integer, Unit> function14 = function12;
                                                    TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.composableLambda($composer5, -2020639284, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$1$1.1
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

                                                        public final void invoke(Composer $composer6, int $changed5) {
                                                            ComposerKt.sourceInformation($composer6, "C102@4914L346:DatePickerDialog.android.kt#uh7d8r");
                                                            if (($changed5 & 11) != 2 || !$composer6.getSkipping()) {
                                                                if (ComposerKt.isTraceInProgress()) {
                                                                    ComposerKt.traceEventStart(-2020639284, $changed5, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:101)");
                                                                }
                                                                float f4 = DatePickerDialog_androidKt.DialogButtonsMainAxisSpacing;
                                                                float f5 = DatePickerDialog_androidKt.DialogButtonsCrossAxisSpacing;
                                                                final Function2<Composer, Integer, Unit> function15 = function13;
                                                                final int i16 = i15;
                                                                final Function2<Composer, Integer, Unit> function16 = function14;
                                                                AlertDialogKt.m1306AlertDialogFlowRowixp7dh8(f4, f5, ComposableLambdaKt.composableLambda($composer6, -1863712509, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt.DatePickerDialog.1.1.1.1.1.1.1
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

                                                                    public final void invoke(Composer $composer7, int $changed6) {
                                                                        ComposerKt.sourceInformation($composer7, "C107@5215L15:DatePickerDialog.android.kt#uh7d8r");
                                                                        if (($changed6 & 11) != 2 || !$composer7.getSkipping()) {
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                                ComposerKt.traceEventStart(-1863712509, $changed6, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:105)");
                                                                            }
                                                                            Function2<Composer, Integer, Unit> function17 = function15;
                                                                            $composer7.startReplaceableGroup(1710961622);
                                                                            ComposerKt.sourceInformation($composer7, "106@5174L8");
                                                                            if (function17 != null) {
                                                                                function17.invoke($composer7, Integer.valueOf((i16 >> 9) & 14));
                                                                                Unit unit = Unit.INSTANCE;
                                                                            }
                                                                            $composer7.endReplaceableGroup();
                                                                            function16.invoke($composer7, Integer.valueOf((i16 >> 3) & 14));
                                                                            if (ComposerKt.isTraceInProgress()) {
                                                                                ComposerKt.traceEventEnd();
                                                                                return;
                                                                            }
                                                                            return;
                                                                        }
                                                                        $composer7.skipToGroupEnd();
                                                                    }
                                                                }), $composer6, 438);
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
                            });
                            int i11 = i9;
                            SurfaceKt.m1806SurfaceT9BRK9s(modifierM522heightInVpY3zN4$default, shape7, containerColor, 0L, f3, 0.0f, null, composableLambda, $composer3, ((i11 >> 9) & 112) | 12582918 | ((i11 >> 3) & 57344), LocationRequestCompat.QUALITY_LOW_POWER);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, ($dirty3 & 14) | 3072 | (($dirty3 >> 15) & 896), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                properties3 = properties2;
                colors4 = colors3;
                tonalElevation4 = tonalElevation3;
                shape5 = shape4;
                function5 = function4;
            } else {
                $composer2.skipToGroupEnd();
                modifier4 = modifier;
                function5 = function2;
                properties3 = properties;
                shape5 = shape2;
                tonalElevation4 = f;
                colors4 = datePickerColors;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier4;
            final Function2<? super Composer, ? super Integer, Unit> function7 = function5;
            final Shape shape7 = shape5;
            final float f3 = tonalElevation4;
            final DatePickerColors datePickerColors3 = colors4;
            final DialogProperties dialogProperties = properties3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$2
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
                    DatePickerDialog_androidKt.m1475DatePickerDialogGmEhDVc(onDismissRequest, confirmButton, modifier5, function7, shape7, f3, datePickerColors3, dialogProperties, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 100663296;
        $dirty2 |= i2;
        if ((191739611 & $dirty2) == 38347922) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    function3 = null;
                } else {
                    function3 = function2;
                }
                if ((i & 16) != 0) {
                    shape3 = DatePickerDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if (i6 != 0) {
                    tonalElevation2 = DatePickerDefaults.INSTANCE.m1474getTonalElevationD9Ej5fM();
                } else {
                    tonalElevation2 = f;
                }
                if ((i & 64) != 0) {
                    colors2 = DatePickerDefaults.INSTANCE.m1473colors1m2CgY(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 805306368, 524287);
                    $dirty2 &= -3670017;
                } else {
                    colors2 = datePickerColors;
                }
                if (i8 != 0) {
                    properties2 = new DialogProperties(false, false, null, false, false, 23, null);
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    function4 = function3;
                    shape4 = shape3;
                    tonalElevation3 = tonalElevation2;
                    colors3 = colors2;
                } else {
                    properties2 = properties;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    function4 = function3;
                    shape4 = shape3;
                    tonalElevation3 = tonalElevation2;
                    colors3 = colors2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    function3 = null;
                } else {
                    function3 = function2;
                }
                if ((i & 16) != 0) {
                    shape3 = DatePickerDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if (i6 != 0) {
                    tonalElevation2 = DatePickerDefaults.INSTANCE.m1474getTonalElevationD9Ej5fM();
                } else {
                    tonalElevation2 = f;
                }
                if ((i & 64) != 0) {
                    colors2 = DatePickerDefaults.INSTANCE.m1473colors1m2CgY(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 805306368, 524287);
                    $dirty2 &= -3670017;
                } else {
                    colors2 = datePickerColors;
                }
                if (i8 != 0) {
                    properties2 = new DialogProperties(false, false, null, false, false, 23, null);
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    function4 = function3;
                    shape4 = shape3;
                    tonalElevation3 = tonalElevation2;
                    colors3 = colors2;
                } else {
                    properties2 = properties;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    function4 = function3;
                    shape4 = shape3;
                    tonalElevation3 = tonalElevation2;
                    colors3 = colors2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-36517340, $dirty, -1, "androidx.compose.material3.DatePickerDialog (DatePickerDialog.android.kt:64)");
            }
            final Shape shape8 = shape4;
            final DatePickerColors datePickerColors4 = colors3;
            final float f4 = tonalElevation3;
            final int i10 = $dirty;
            modifier4 = modifier3;
            final Function2<? super Composer, ? super Integer, Unit> function8 = function4;
            int $dirty4 = $dirty;
            AndroidAlertDialog_androidKt.AlertDialog(onDismissRequest, SizeKt.wrapContentHeight$default(modifier3, null, false, 3, null), properties2, ComposableLambdaKt.composableLambda($composer2, -476003174, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1
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
                    ComposerKt.sourceInformation($composer3, "C80@3909L1441:DatePickerDialog.android.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-476003174, $changed2, -1, "androidx.compose.material3.DatePickerDialog.<anonymous> (DatePickerDialog.android.kt:79)");
                        }
                        Modifier modifierM522heightInVpY3zN4$default = SizeKt.m522heightInVpY3zN4$default(SizeKt.m531requiredWidth3ABfNKs(Modifier.INSTANCE, DatePickerModalTokens.INSTANCE.m2115getContainerWidthD9Ej5fM()), 0.0f, DatePickerModalTokens.INSTANCE.m2114getContainerHeightD9Ej5fM(), 1, null);
                        Shape shape9 = shape8;
                        long containerColor = datePickerColors4.getContainerColor();
                        float f5 = f4;
                        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function9 = content;
                        final int i11 = i10;
                        final Function2<? super Composer, ? super Integer, Unit> function10 = function8;
                        final Function2<? super Composer, ? super Integer, Unit> function11 = confirmButton;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer3, 1763752415, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1.1
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
                                ComposerKt.sourceInformation($composer4, "C88@4230L1110:DatePickerDialog.android.kt#uh7d8r");
                                if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1763752415, $changed3, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous> (DatePickerDialog.android.kt:87)");
                                    }
                                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getSpaceBetween();
                                    Function3<ColumnScope, Composer, Integer, Unit> function12 = function9;
                                    final int i12 = i11;
                                    final Function2<? super Composer, ? super Integer, Unit> function13 = function10;
                                    final Function2<? super Composer, ? super Integer, Unit> function14 = function11;
                                    $composer4.startReplaceableGroup(-483455358);
                                    ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                    Modifier modifier$iv = Modifier.INSTANCE;
                                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                    int $changed$iv$iv = (48 << 3) & 112;
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
                                    int i13 = ($changed$iv$iv$iv >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                    ColumnScope $this$invoke_u24lambda_u241 = ColumnScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart($composer4, 1146374379, "C89@4303L9,91@4356L970:DatePickerDialog.android.kt#uh7d8r");
                                    function12.invoke($this$invoke_u24lambda_u241, $composer4, Integer.valueOf(((((48 >> 6) & 112) | 6) & 14) | ((i12 >> 21) & 112)));
                                    Modifier modifier$iv2 = PaddingKt.padding($this$invoke_u24lambda_u241.align(Modifier.INSTANCE, Alignment.INSTANCE.getEnd()), DatePickerDialog_androidKt.DialogButtonsPadding);
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
                                    int i14 = ($changed$iv$iv$iv2 >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    int i15 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -552861189, "C97@4653L9,96@4542L766:DatePickerDialog.android.kt#uh7d8r");
                                    CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(ColorSchemeKt.toColor(DialogTokens.INSTANCE.getActionLabelTextColor(), $composer4, 6)))}, ComposableLambdaKt.composableLambda($composer4, -926980325, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$1$1
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

                                        public final void invoke(Composer $composer5, int $changed4) {
                                            ComposerKt.sourceInformation($composer5, "C100@4769L10,101@4848L438:DatePickerDialog.android.kt#uh7d8r");
                                            if (($changed4 & 11) != 2 || !$composer5.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-926980325, $changed4, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:98)");
                                                }
                                                TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), DialogTokens.INSTANCE.getActionLabelTextFont());
                                                final Function2<? super Composer, ? super Integer, Unit> function15 = function13;
                                                final int i16 = i12;
                                                final Function2<? super Composer, ? super Integer, Unit> function16 = function14;
                                                TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.composableLambda($composer5, -2020639284, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$1$1.1
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

                                                    public final void invoke(Composer $composer6, int $changed5) {
                                                        ComposerKt.sourceInformation($composer6, "C102@4914L346:DatePickerDialog.android.kt#uh7d8r");
                                                        if (($changed5 & 11) != 2 || !$composer6.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(-2020639284, $changed5, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:101)");
                                                            }
                                                            float f6 = DatePickerDialog_androidKt.DialogButtonsMainAxisSpacing;
                                                            float f7 = DatePickerDialog_androidKt.DialogButtonsCrossAxisSpacing;
                                                            final Function2<? super Composer, ? super Integer, Unit> function17 = function15;
                                                            final int i17 = i16;
                                                            final Function2<? super Composer, ? super Integer, Unit> function18 = function16;
                                                            AlertDialogKt.m1306AlertDialogFlowRowixp7dh8(f6, f7, ComposableLambdaKt.composableLambda($composer6, -1863712509, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt.DatePickerDialog.1.1.1.1.1.1.1
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

                                                                public final void invoke(Composer $composer7, int $changed6) {
                                                                    ComposerKt.sourceInformation($composer7, "C107@5215L15:DatePickerDialog.android.kt#uh7d8r");
                                                                    if (($changed6 & 11) != 2 || !$composer7.getSkipping()) {
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventStart(-1863712509, $changed6, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:105)");
                                                                        }
                                                                        Function2<Composer, Integer, Unit> function19 = function17;
                                                                        $composer7.startReplaceableGroup(1710961622);
                                                                        ComposerKt.sourceInformation($composer7, "106@5174L8");
                                                                        if (function19 != null) {
                                                                            function19.invoke($composer7, Integer.valueOf((i17 >> 9) & 14));
                                                                            Unit unit = Unit.INSTANCE;
                                                                        }
                                                                        $composer7.endReplaceableGroup();
                                                                        function18.invoke($composer7, Integer.valueOf((i17 >> 3) & 14));
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventEnd();
                                                                            return;
                                                                        }
                                                                        return;
                                                                    }
                                                                    $composer7.skipToGroupEnd();
                                                                }
                                                            }), $composer6, 438);
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
                        });
                        int i12 = i10;
                        SurfaceKt.m1806SurfaceT9BRK9s(modifierM522heightInVpY3zN4$default, shape9, containerColor, 0L, f5, 0.0f, null, composableLambda, $composer3, ((i12 >> 9) & 112) | 12582918 | ((i12 >> 3) & 57344), LocationRequestCompat.QUALITY_LOW_POWER);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, ($dirty4 & 14) | 3072 | (($dirty4 >> 15) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            properties3 = properties2;
            colors4 = colors3;
            tonalElevation4 = tonalElevation3;
            shape5 = shape4;
            function5 = function4;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    function3 = null;
                } else {
                    function3 = function2;
                }
                if ((i & 16) != 0) {
                    shape3 = DatePickerDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if (i6 != 0) {
                    tonalElevation2 = DatePickerDefaults.INSTANCE.m1474getTonalElevationD9Ej5fM();
                } else {
                    tonalElevation2 = f;
                }
                if ((i & 64) != 0) {
                    colors2 = DatePickerDefaults.INSTANCE.m1473colors1m2CgY(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 805306368, 524287);
                    $dirty2 &= -3670017;
                } else {
                    colors2 = datePickerColors;
                }
                if (i8 != 0) {
                    properties2 = new DialogProperties(false, false, null, false, false, 23, null);
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    function4 = function3;
                    shape4 = shape3;
                    tonalElevation3 = tonalElevation2;
                    colors3 = colors2;
                } else {
                    properties2 = properties;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    function4 = function3;
                    shape4 = shape3;
                    tonalElevation3 = tonalElevation2;
                    colors3 = colors2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    function3 = null;
                } else {
                    function3 = function2;
                }
                if ((i & 16) != 0) {
                    shape3 = DatePickerDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if (i6 != 0) {
                    tonalElevation2 = DatePickerDefaults.INSTANCE.m1474getTonalElevationD9Ej5fM();
                } else {
                    tonalElevation2 = f;
                }
                if ((i & 64) != 0) {
                    colors2 = DatePickerDefaults.INSTANCE.m1473colors1m2CgY(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 805306368, 524287);
                    $dirty2 &= -3670017;
                } else {
                    colors2 = datePickerColors;
                }
                if (i8 != 0) {
                    properties2 = new DialogProperties(false, false, null, false, false, 23, null);
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    function4 = function3;
                    shape4 = shape3;
                    tonalElevation3 = tonalElevation2;
                    colors3 = colors2;
                } else {
                    properties2 = properties;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    function4 = function3;
                    shape4 = shape3;
                    tonalElevation3 = tonalElevation2;
                    colors3 = colors2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-36517340, $dirty, -1, "androidx.compose.material3.DatePickerDialog (DatePickerDialog.android.kt:64)");
            }
            final Shape shape9 = shape4;
            final DatePickerColors datePickerColors5 = colors3;
            final float f5 = tonalElevation3;
            final int i11 = $dirty;
            modifier4 = modifier3;
            final Function2<? super Composer, ? super Integer, Unit> function9 = function4;
            int $dirty5 = $dirty;
            AndroidAlertDialog_androidKt.AlertDialog(onDismissRequest, SizeKt.wrapContentHeight$default(modifier3, null, false, 3, null), properties2, ComposableLambdaKt.composableLambda($composer2, -476003174, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1
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
                    ComposerKt.sourceInformation($composer3, "C80@3909L1441:DatePickerDialog.android.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-476003174, $changed2, -1, "androidx.compose.material3.DatePickerDialog.<anonymous> (DatePickerDialog.android.kt:79)");
                        }
                        Modifier modifierM522heightInVpY3zN4$default = SizeKt.m522heightInVpY3zN4$default(SizeKt.m531requiredWidth3ABfNKs(Modifier.INSTANCE, DatePickerModalTokens.INSTANCE.m2115getContainerWidthD9Ej5fM()), 0.0f, DatePickerModalTokens.INSTANCE.m2114getContainerHeightD9Ej5fM(), 1, null);
                        Shape shape10 = shape9;
                        long containerColor = datePickerColors5.getContainerColor();
                        float f6 = f5;
                        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function10 = content;
                        final int i12 = i11;
                        final Function2<? super Composer, ? super Integer, Unit> function11 = function9;
                        final Function2<? super Composer, ? super Integer, Unit> function12 = confirmButton;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer3, 1763752415, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1.1
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
                                ComposerKt.sourceInformation($composer4, "C88@4230L1110:DatePickerDialog.android.kt#uh7d8r");
                                if (($changed3 & 11) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1763752415, $changed3, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous> (DatePickerDialog.android.kt:87)");
                                    }
                                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getSpaceBetween();
                                    Function3<ColumnScope, Composer, Integer, Unit> function13 = function10;
                                    final int i13 = i12;
                                    final Function2<? super Composer, ? super Integer, Unit> function14 = function11;
                                    final Function2<? super Composer, ? super Integer, Unit> function15 = function12;
                                    $composer4.startReplaceableGroup(-483455358);
                                    ComposerKt.sourceInformation($composer4, "CC(Column)P(2,3,1)77@3913L61,78@3979L133:Column.kt#2w3rfo");
                                    Modifier modifier$iv = Modifier.INSTANCE;
                                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                    int $changed$iv$iv = (48 << 3) & 112;
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
                                    int i14 = ($changed$iv$iv$iv >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer4, 276693704, "C79@4027L9:Column.kt#2w3rfo");
                                    ColumnScope $this$invoke_u24lambda_u241 = ColumnScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart($composer4, 1146374379, "C89@4303L9,91@4356L970:DatePickerDialog.android.kt#uh7d8r");
                                    function13.invoke($this$invoke_u24lambda_u241, $composer4, Integer.valueOf(((((48 >> 6) & 112) | 6) & 14) | ((i13 >> 21) & 112)));
                                    Modifier modifier$iv2 = PaddingKt.padding($this$invoke_u24lambda_u241.align(Modifier.INSTANCE, Alignment.INSTANCE.getEnd()), DatePickerDialog_androidKt.DialogButtonsPadding);
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
                                    int i15 = ($changed$iv$iv$iv2 >> 9) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -1253629305, "C72@3384L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    int i16 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer4, -552861189, "C97@4653L9,96@4542L766:DatePickerDialog.android.kt#uh7d8r");
                                    CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(ColorSchemeKt.toColor(DialogTokens.INSTANCE.getActionLabelTextColor(), $composer4, 6)))}, ComposableLambdaKt.composableLambda($composer4, -926980325, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$1$1
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

                                        public final void invoke(Composer $composer5, int $changed4) {
                                            ComposerKt.sourceInformation($composer5, "C100@4769L10,101@4848L438:DatePickerDialog.android.kt#uh7d8r");
                                            if (($changed4 & 11) != 2 || !$composer5.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-926980325, $changed4, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:98)");
                                                }
                                                TextStyle textStyle = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography($composer5, 6), DialogTokens.INSTANCE.getActionLabelTextFont());
                                                final Function2<? super Composer, ? super Integer, Unit> function16 = function14;
                                                final int i17 = i13;
                                                final Function2<? super Composer, ? super Integer, Unit> function17 = function15;
                                                TextKt.ProvideTextStyle(textStyle, ComposableLambdaKt.composableLambda($composer5, -2020639284, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$1$1.1
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

                                                    public final void invoke(Composer $composer6, int $changed5) {
                                                        ComposerKt.sourceInformation($composer6, "C102@4914L346:DatePickerDialog.android.kt#uh7d8r");
                                                        if (($changed5 & 11) != 2 || !$composer6.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(-2020639284, $changed5, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:101)");
                                                            }
                                                            float f7 = DatePickerDialog_androidKt.DialogButtonsMainAxisSpacing;
                                                            float f8 = DatePickerDialog_androidKt.DialogButtonsCrossAxisSpacing;
                                                            final Function2<? super Composer, ? super Integer, Unit> function18 = function16;
                                                            final int i18 = i17;
                                                            final Function2<? super Composer, ? super Integer, Unit> function19 = function17;
                                                            AlertDialogKt.m1306AlertDialogFlowRowixp7dh8(f7, f8, ComposableLambdaKt.composableLambda($composer6, -1863712509, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt.DatePickerDialog.1.1.1.1.1.1.1
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

                                                                public final void invoke(Composer $composer7, int $changed6) {
                                                                    ComposerKt.sourceInformation($composer7, "C107@5215L15:DatePickerDialog.android.kt#uh7d8r");
                                                                    if (($changed6 & 11) != 2 || !$composer7.getSkipping()) {
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventStart(-1863712509, $changed6, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:105)");
                                                                        }
                                                                        Function2<Composer, Integer, Unit> function110 = function18;
                                                                        $composer7.startReplaceableGroup(1710961622);
                                                                        ComposerKt.sourceInformation($composer7, "106@5174L8");
                                                                        if (function110 != null) {
                                                                            function110.invoke($composer7, Integer.valueOf((i18 >> 9) & 14));
                                                                            Unit unit = Unit.INSTANCE;
                                                                        }
                                                                        $composer7.endReplaceableGroup();
                                                                        function19.invoke($composer7, Integer.valueOf((i18 >> 3) & 14));
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventEnd();
                                                                            return;
                                                                        }
                                                                        return;
                                                                    }
                                                                    $composer7.skipToGroupEnd();
                                                                }
                                                            }), $composer6, 438);
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
                        });
                        int i13 = i11;
                        SurfaceKt.m1806SurfaceT9BRK9s(modifierM522heightInVpY3zN4$default, shape10, containerColor, 0L, f6, 0.0f, null, composableLambda, $composer3, ((i13 >> 9) & 112) | 12582918 | ((i13 >> 3) & 57344), LocationRequestCompat.QUALITY_LOW_POWER);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, ($dirty5 & 14) | 3072 | (($dirty5 >> 15) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            properties3 = properties2;
            colors4 = colors3;
            tonalElevation4 = tonalElevation3;
            shape5 = shape4;
            function5 = function4;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier4;
        final Function2<? super Composer, ? super Integer, Unit> function10 = function5;
        final Shape shape10 = shape5;
        final float f6 = tonalElevation4;
        final DatePickerColors datePickerColors6 = colors4;
        final DialogProperties dialogProperties2 = properties3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$2
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
                DatePickerDialog_androidKt.m1475DatePickerDialogGmEhDVc(onDismissRequest, confirmButton, modifier6, function10, shape10, f6, datePickerColors6, dialogProperties2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }
}
