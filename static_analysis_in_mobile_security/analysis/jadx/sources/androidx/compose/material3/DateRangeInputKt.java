package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DateRangeInput.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0001¢\u0006\u0002\u0010\r\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"TextFieldSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "DateRangeInputContent", "", "stateData", "Landroidx/compose/material3/StateData;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "dateValidator", "Lkotlin/Function1;", "", "", "(Landroidx/compose/material3/StateData;Landroidx/compose/material3/DatePickerFormatter;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DateRangeInputKt {
    private static final float TextFieldSpacing = Dp.m5274constructorimpl(8);

    /* JADX WARN: Code duplicated, block: B:71:0x0445  */
    public static final void DateRangeInputContent(final StateData stateData, final DatePickerFormatter dateFormatter, final Function1<? super Long, Boolean> dateValidator, Composer $composer, final int $changed) {
        Object value$iv$iv;
        int i;
        LayoutDirection layoutDirection$iv$iv;
        Composer $composer2;
        boolean invalid$iv$iv;
        Object value$iv$iv2;
        Intrinsics.checkNotNullParameter(stateData, "stateData");
        Intrinsics.checkNotNullParameter(dateFormatter, "dateFormatter");
        Intrinsics.checkNotNullParameter(dateValidator, "dateValidator");
        Composer $composer3 = $composer.startRestartGroup(-1163802470);
        ComposerKt.sourceInformation($composer3, "C(DateRangeInputContent)P(2)37@1398L15,38@1440L97,41@1565L45,42@1645L44,43@1723L45,44@1797L50,45@1877L482,57@2364L1939:DateRangeInput.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(stateData) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer3.changed(dateFormatter) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer3.changedInstance(dateValidator) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 731) != 146 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1163802470, $dirty2, -1, "androidx.compose.material3.DateRangeInputContent (DateRangeInput.kt:31)");
            }
            Locale defaultLocale = CalendarModel_androidKt.defaultLocale($composer3, 0);
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv2 = $composer3.changed(defaultLocale);
            Object it$iv$iv = $composer3.rememberedValue();
            if (invalid$iv$iv2 || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = stateData.getCalendarModel().getDateInputFormat(defaultLocale);
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer3.endReplaceableGroup();
            DateInputFormat dateInputFormat = (DateInputFormat) value$iv$iv;
            String errorDatePattern = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1743getDateInputInvalidForPatternadMyvUU(), $composer3, 6);
            String errorDateOutOfYearRange = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1745getDateInputInvalidYearRangeadMyvUU(), $composer3, 6);
            String errorInvalidNotAllowed = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1744getDateInputInvalidNotAllowedadMyvUU(), $composer3, 6);
            String errorInvalidRange = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1764getDateRangeInputInvalidRangeInputadMyvUU(), $composer3, 6);
            int i2 = $dirty2 & 112;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean invalid$iv$iv3 = $composer3.changed(dateInputFormat) | $composer3.changed(dateFormatter);
            Object value$iv$iv3 = $composer3.rememberedValue();
            if (invalid$iv$iv3 || value$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                i = 6;
                value$iv$iv3 = new DateInputValidator(stateData, dateInputFormat, dateFormatter, dateValidator, errorDatePattern, errorDateOutOfYearRange, errorInvalidNotAllowed, errorInvalidRange);
                $composer3.updateRememberedValue(value$iv$iv3);
            } else {
                i = 6;
            }
            $composer3.endReplaceableGroup();
            DateInputValidator dateInputValidator = (DateInputValidator) value$iv$iv3;
            Modifier modifier$iv = PaddingKt.padding(Modifier.INSTANCE, DateInputKt.getInputTextFieldPadding());
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.m394spacedBy0680j_4(TextFieldSpacing);
            $composer3.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)78@3913L58,79@3976L130:Row.kt#2w3rfo");
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((54 >> 3) & 14) | ((54 >> 3) & 112));
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
            LayoutDirection layoutDirection$iv$iv2 = (LayoutDirection) objConsume2;
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ViewConfiguration viewConfiguration$iv$iv = (ViewConfiguration) objConsume3;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifier$iv);
            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | i;
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
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, layoutDirection$iv$iv2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            Updater.m2610setimpl($this$Layout_u24lambda_u2d0$iv$iv, viewConfiguration$iv$iv, ComposeUiNode.INSTANCE.getSetViewConfiguration());
            $composer3.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i3 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -326682283, "C80@4021L9:Row.kt#2w3rfo");
            int i4 = ((54 >> 6) & 112) | 6;
            RowScope $this$DateRangeInputContent_u24lambda_u244 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1849028810, "C62@2625L56,74@3193L52,63@2690L763,80@3481L54,92@4041L50,81@3544L753:DateRangeInput.kt#uh7d8r");
            final String pattern = dateInputFormat.getPatternWithDelimiters().toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(pattern, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            final String startRangeText = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1770getDateRangePickerStartHeadlineadMyvUU(), $composer3, 6);
            Modifier modifierWeight$default = RowScope.CC.weight$default($this$DateRangeInputContent_u24lambda_u244, Modifier.INSTANCE, 0.5f, false, 2, null);
            ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer3, 576559191, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    Object value$iv$iv4;
                    ComposerKt.sourceInformation($composer4, "C67@2865L96,66@2794L168:DateRangeInput.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(576559191, $changed2, -1, "androidx.compose.material3.DateRangeInputContent.<anonymous>.<anonymous> (DateRangeInput.kt:65)");
                        }
                        String str = startRangeText;
                        Modifier.Companion companion = Modifier.INSTANCE;
                        Object key1$iv = startRangeText;
                        Object key2$iv = pattern;
                        final String str2 = startRangeText;
                        final String str3 = pattern;
                        $composer4.startReplaceableGroup(511388516);
                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2):Composables.kt#9igjgp");
                        boolean invalid$iv$iv4 = $composer4.changed(key1$iv) | $composer4.changed(key2$iv);
                        Object it$iv$iv2 = $composer4.rememberedValue();
                        if (invalid$iv$iv4 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$1$1$1
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
                                    SemanticsPropertiesKt.setContentDescription(semantics, str2 + ", " + str3);
                                }
                            };
                            $composer4.updateRememberedValue(value$iv$iv4);
                        } else {
                            value$iv$iv4 = it$iv$iv2;
                        }
                        $composer4.endReplaceableGroup();
                        TextKt.m1884Text4IGK_g(str, SemanticsModifierKt.semantics$default(companion, false, (Function1) value$iv$iv4, 1, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131068);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            });
            ComposableLambda composableLambda2 = ComposableLambdaKt.composableLambda($composer3, 1726391478, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C71@3006L59:DateRangeInput.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1726391478, $changed2, -1, "androidx.compose.material3.DateRangeInputContent.<anonymous>.<anonymous> (DateRangeInput.kt:71)");
                    }
                    TextKt.m1884Text4IGK_g(pattern, SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$2.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver clearAndSetSemantics) {
                            Intrinsics.checkNotNullParameter(clearAndSetSemantics, "$this$clearAndSetSemantics");
                        }
                    }), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131068);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            });
            CalendarDate value = stateData.getSelectedStartDate().getValue();
            int i5 = $dirty2 & 14;
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv4 = $composer3.changed(stateData);
            Object value$iv$iv4 = $composer3.rememberedValue();
            if (!invalid$iv$iv4) {
                layoutDirection$iv$iv = layoutDirection$iv$iv2;
                if (value$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                }
                $composer3.endReplaceableGroup();
                $composer2 = $composer3;
                DateInputKt.m1464DateInputTextFieldzm97o8M(modifierWeight$default, composableLambda, composableLambda2, stateData, value, (Function1) value$iv$iv4, InputIdentifier.INSTANCE.m1585getStartDateInputJ2x2o4M(), dateInputValidator, dateInputFormat, defaultLocale, $composer3, (($dirty2 << 9) & 7168) | 1075315120);
                final String endRangeText = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1767getDateRangePickerEndHeadlineadMyvUU(), $composer3, 6);
                Modifier modifierWeight$default2 = RowScope.CC.weight$default($this$DateRangeInputContent_u24lambda_u244, Modifier.INSTANCE, 0.5f, false, 2, null);
                ComposableLambda composableLambda3 = ComposableLambdaKt.composableLambda($composer3, -663502784, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        Object value$iv$iv5;
                        ComposerKt.sourceInformation($composer4, "C85@3717L94,84@3648L164:DateRangeInput.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-663502784, $changed2, -1, "androidx.compose.material3.DateRangeInputContent.<anonymous>.<anonymous> (DateRangeInput.kt:83)");
                            }
                            String str = endRangeText;
                            Modifier.Companion companion = Modifier.INSTANCE;
                            Object key1$iv = endRangeText;
                            Object key2$iv = pattern;
                            final String str2 = endRangeText;
                            final String str3 = pattern;
                            $composer4.startReplaceableGroup(511388516);
                            ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2):Composables.kt#9igjgp");
                            boolean invalid$iv$iv5 = $composer4.changed(key1$iv) | $composer4.changed(key2$iv);
                            Object it$iv$iv2 = $composer4.rememberedValue();
                            if (invalid$iv$iv5 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv5 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$4$1$1
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
                                        SemanticsPropertiesKt.setContentDescription(semantics, str2 + ", " + str3);
                                    }
                                };
                                $composer4.updateRememberedValue(value$iv$iv5);
                            } else {
                                value$iv$iv5 = it$iv$iv2;
                            }
                            $composer4.endReplaceableGroup();
                            TextKt.m1884Text4IGK_g(str, SemanticsModifierKt.semantics$default(companion, false, (Function1) value$iv$iv5, 1, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131068);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                });
                ComposableLambda composableLambda4 = ComposableLambdaKt.composableLambda($composer3, 518729951, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C89@3856L59:DateRangeInput.kt#uh7d8r");
                        if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                            $composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(518729951, $changed2, -1, "androidx.compose.material3.DateRangeInputContent.<anonymous>.<anonymous> (DateRangeInput.kt:89)");
                        }
                        TextKt.m1884Text4IGK_g(pattern, SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$5.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver clearAndSetSemantics) {
                                Intrinsics.checkNotNullParameter(clearAndSetSemantics, "$this$clearAndSetSemantics");
                            }
                        }), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131068);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                });
                CalendarDate value2 = stateData.getSelectedEndDate().getValue();
                int i6 = $dirty2 & 14;
                $composer3.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer3.changed(stateData);
                Object it$iv$iv2 = $composer3.rememberedValue();
                if (!invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = (Function1) new Function1<CalendarDate, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$6$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(CalendarDate calendarDate) {
                            invoke2(calendarDate);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(CalendarDate date) {
                            stateData.getSelectedEndDate().setValue(date);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv$iv2);
                } else {
                    value$iv$iv2 = it$iv$iv2;
                }
                $composer3.endReplaceableGroup();
                DateInputKt.m1464DateInputTextFieldzm97o8M(modifierWeight$default2, composableLambda3, composableLambda4, stateData, value2, (Function1) value$iv$iv2, InputIdentifier.INSTANCE.m1583getEndDateInputJ2x2o4M(), dateInputValidator, dateInputFormat, defaultLocale, $composer3, (($dirty2 << 9) & 7168) | 1075315120);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                layoutDirection$iv$iv = layoutDirection$iv$iv2;
            }
            value$iv$iv4 = (Function1) new Function1<CalendarDate, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$3$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(CalendarDate calendarDate) {
                    invoke2(calendarDate);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(CalendarDate date) {
                    stateData.getSelectedStartDate().setValue(date);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv4);
            $composer3.endReplaceableGroup();
            $composer2 = $composer3;
            DateInputKt.m1464DateInputTextFieldzm97o8M(modifierWeight$default, composableLambda, composableLambda2, stateData, value, (Function1) value$iv$iv4, InputIdentifier.INSTANCE.m1585getStartDateInputJ2x2o4M(), dateInputValidator, dateInputFormat, defaultLocale, $composer3, (($dirty2 << 9) & 7168) | 1075315120);
            final String endRangeText2 = Strings_androidKt.m1797getStringNWtq28(Strings.INSTANCE.m1767getDateRangePickerEndHeadlineadMyvUU(), $composer3, 6);
            Modifier modifierWeight$default3 = RowScope.CC.weight$default($this$DateRangeInputContent_u24lambda_u244, Modifier.INSTANCE, 0.5f, false, 2, null);
            ComposableLambda composableLambda5 = ComposableLambdaKt.composableLambda($composer3, -663502784, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    Object value$iv$iv5;
                    ComposerKt.sourceInformation($composer4, "C85@3717L94,84@3648L164:DateRangeInput.kt#uh7d8r");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-663502784, $changed2, -1, "androidx.compose.material3.DateRangeInputContent.<anonymous>.<anonymous> (DateRangeInput.kt:83)");
                        }
                        String str = endRangeText2;
                        Modifier.Companion companion = Modifier.INSTANCE;
                        Object key1$iv = endRangeText2;
                        Object key2$iv = pattern;
                        final String str2 = endRangeText2;
                        final String str3 = pattern;
                        $composer4.startReplaceableGroup(511388516);
                        ComposerKt.sourceInformation($composer4, "CC(remember)P(1,2):Composables.kt#9igjgp");
                        boolean invalid$iv$iv5 = $composer4.changed(key1$iv) | $composer4.changed(key2$iv);
                        Object it$iv$iv3 = $composer4.rememberedValue();
                        if (invalid$iv$iv5 || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv5 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$4$1$1
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
                                    SemanticsPropertiesKt.setContentDescription(semantics, str2 + ", " + str3);
                                }
                            };
                            $composer4.updateRememberedValue(value$iv$iv5);
                        } else {
                            value$iv$iv5 = it$iv$iv3;
                        }
                        $composer4.endReplaceableGroup();
                        TextKt.m1884Text4IGK_g(str, SemanticsModifierKt.semantics$default(companion, false, (Function1) value$iv$iv5, 1, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131068);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            });
            ComposableLambda composableLambda6 = ComposableLambdaKt.composableLambda($composer3, 518729951, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C89@3856L59:DateRangeInput.kt#uh7d8r");
                    if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(518729951, $changed2, -1, "androidx.compose.material3.DateRangeInputContent.<anonymous>.<anonymous> (DateRangeInput.kt:89)");
                    }
                    TextKt.m1884Text4IGK_g(pattern, SemanticsModifierKt.clearAndSetSemantics(Modifier.INSTANCE, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$5.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver clearAndSetSemantics) {
                            Intrinsics.checkNotNullParameter(clearAndSetSemantics, "$this$clearAndSetSemantics");
                        }
                    }), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131068);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            });
            CalendarDate value3 = stateData.getSelectedEndDate().getValue();
            int i7 = $dirty2 & 14;
            $composer3.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(stateData);
            Object it$iv$iv3 = $composer3.rememberedValue();
            if (!invalid$iv$iv) {
            }
            value$iv$iv2 = (Function1) new Function1<CalendarDate, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$1$6$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(CalendarDate calendarDate) {
                    invoke2(calendarDate);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(CalendarDate date) {
                    stateData.getSelectedEndDate().setValue(date);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv2);
            $composer3.endReplaceableGroup();
            DateInputKt.m1464DateInputTextFieldzm97o8M(modifierWeight$default3, composableLambda5, composableLambda6, stateData, value3, (Function1) value$iv$iv2, InputIdentifier.INSTANCE.m1583getEndDateInputJ2x2o4M(), dateInputValidator, dateInputFormat, defaultLocale, $composer3, (($dirty2 << 9) & 7168) | 1075315120);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangeInputKt.DateRangeInputContent.2
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
                DateRangeInputKt.DateRangeInputContent(stateData, dateFormatter, dateValidator, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }
}
