package com.holberton.task1;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import com.holberton.task1.ui.theme.ThemeKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0005\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a\r\u0010\u0005\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0006\u001a\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b\u001a\u001c\u0010\n\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\r\u001a\"\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\r¨\u0006\u0010²\u0006\n\u0010\u0011\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\bX\u008a\u008e\u0002"}, d2 = {"FlagChallenge", "", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "FlagChallengePreview", "(Landroidx/compose/runtime/Composer;I)V", "hexToAscii", "", "hexStr", "xorDeobfuscate", "input", "", "", "key", "xorObfuscate", "app_debug", "userInput", "resultMessage"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class MainActivityKt {
    public static final void FlagChallenge(Modifier modifier, Composer $composer, final int $changed, final int i) {
        Object value$iv$iv;
        Object value$iv$iv2;
        Function0<ComposeUiNode> function0;
        long jM1367getError0d7_KjU;
        final Modifier modifier2;
        Composer $composer2 = $composer.startRestartGroup(-162751028);
        ComposerKt.sourceInformation($composer2, "C(FlagChallenge)35@1167L31,36@1224L31,55@2076L1448:MainActivity.kt#wyyfmi");
        if (($changed & 1) == 0 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
        } else {
            Modifier.Companion modifier3 = (i & 1) != 0 ? Modifier.INSTANCE : modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-162751028, $changed, -1, "com.holberton.task1.FlagChallenge (MainActivity.kt:33)");
            }
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            final MutableState userInput$delegate = (MutableState) value$iv$iv;
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            final MutableState resultMessage$delegate = (MutableState) value$iv$iv2;
            Modifier modifier4 = modifier3;
            final String correctFlag = hexToAscii("486f6c626572746f6e7b") + xorDeobfuscate(xorObfuscate(CollectionsKt.listOf((Object[]) new Integer[]{71, 111, 111, 100, 95}), 42), 42) + xorDeobfuscate(xorObfuscate(CollectionsKt.listOf((Object[]) new Integer[]{106, 111, 98}), 42), 42) + "_on_your_" + hexToAscii("6669727374") + "_static_analysis_" + hexToAscii("6578657263697365") + "}";
            Modifier modifier$iv = PaddingKt.m487padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5274constructorimpl(16));
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getCenter();
            $composer2.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation($composer2, "CC(Column)P(2,3,1)75@3779L61,76@3845L133:Column.kt#2w3rfo");
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            int $changed$iv$iv = (54 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            int i2 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 276693570, "C77@3893L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1466916109, "C62@2324L10,62@2261L84,67@2470L18,65@2392L192,72@2594L41,76@2711L379,75@2681L510,91@3201L41,96@3352L10,94@3278L240:MainActivity.kt#wyyfmi");
            TextKt.m1884Text4IGK_g("Enter the flag to verify:", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer2, MaterialTheme.$stable).getBodyLarge(), $composer2, 6, 0, 65534);
            String strFlagChallenge$lambda$1 = FlagChallenge$lambda$1(userInput$delegate);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(userInput$delegate);
            Object value$iv$iv3 = $composer2.rememberedValue();
            if (invalid$iv$iv || value$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv3 = (Function1) new Function1<String, Unit>() { // from class: com.holberton.task1.MainActivityKt$FlagChallenge$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str) {
                        invoke2(str);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        userInput$delegate.setValue(it);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv3);
            }
            $composer2.endReplaceableGroup();
            OutlinedTextFieldKt.OutlinedTextField(strFlagChallenge$lambda$1, (Function1<? super String, Unit>) value$iv$iv3, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.m5556getLambda4$app_debug(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer2, 1573248, 0, 0, 8388536);
            SpacerKt.Spacer(SizeKt.m520height3ABfNKs(Modifier.INSTANCE, Dp.m5274constructorimpl(16)), $composer2, 6);
            $composer2.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            boolean invalid$iv$iv2 = $composer2.changed(resultMessage$delegate) | $composer2.changed(userInput$delegate) | $composer2.changed(correctFlag);
            Object value$iv$iv4 = $composer2.rememberedValue();
            if (invalid$iv$iv2 || value$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv4 = (Function0) new Function0<Unit>() { // from class: com.holberton.task1.MainActivityKt$FlagChallenge$1$2$1
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
                        String str;
                        MutableState<String> mutableState = resultMessage$delegate;
                        if (Intrinsics.areEqual(StringsKt.trim((CharSequence) MainActivityKt.FlagChallenge$lambda$1(userInput$delegate)).toString(), StringsKt.trim((CharSequence) correctFlag).toString())) {
                            str = "Correct! Well done.";
                        } else {
                            str = "Wrong flag, try again!";
                        }
                        mutableState.setValue(str);
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv4);
            }
            $composer2.endReplaceableGroup();
            ButtonKt.Button((Function0) value$iv$iv4, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m5557getLambda5$app_debug(), $composer2, 805306416, 508);
            SpacerKt.Spacer(SizeKt.m520height3ABfNKs(Modifier.INSTANCE, Dp.m5274constructorimpl(16)), $composer2, 6);
            String strFlagChallenge$lambda$4 = FlagChallenge$lambda$4(resultMessage$delegate);
            TextStyle bodyLarge = MaterialTheme.INSTANCE.getTypography($composer2, MaterialTheme.$stable).getBodyLarge();
            if (Intrinsics.areEqual(FlagChallenge$lambda$4(resultMessage$delegate), "Correct! Well done.")) {
                $composer2.startReplaceableGroup(-1466914906);
                ComposerKt.sourceInformation($composer2, "97@3452L11");
                jM1367getError0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).m1385getPrimary0d7_KjU();
            } else {
                $composer2.startReplaceableGroup(-1466914867);
                ComposerKt.sourceInformation($composer2, "97@3491L11");
                jM1367getError0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).m1367getError0d7_KjU();
            }
            $composer2.endReplaceableGroup();
            TextKt.m1884Text4IGK_g(strFlagChallenge$lambda$4, (Modifier) null, jM1367getError0d7_KjU, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodyLarge, $composer2, 0, 0, 65530);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.holberton.task1.MainActivityKt.FlagChallenge.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i4) {
                MainActivityKt.FlagChallenge(modifier2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String FlagChallenge$lambda$1(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    private static final String FlagChallenge$lambda$4(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    public static final String hexToAscii(String hexStr) {
        Intrinsics.checkNotNullParameter(hexStr, "hexStr");
        StringBuilder output = new StringBuilder("");
        int i = 0;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, hexStr.length() - 1, 2);
        if (0 <= progressionLastElement) {
            while (true) {
                String str = hexStr.substring(i, i + 2);
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                output.append((char) Integer.parseInt(str, CharsKt.checkRadix(16)));
                if (i == progressionLastElement) {
                    break;
                }
                i += 2;
            }
        }
        String string = output.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public static final List<Integer> xorObfuscate(List<Integer> input, int key) {
        Intrinsics.checkNotNullParameter(input, "input");
        List<Integer> $this$map$iv = input;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            int it = ((Number) item$iv$iv).intValue();
            destination$iv$iv.add(Integer.valueOf(it ^ key));
        }
        return (List) destination$iv$iv;
    }

    public static final String xorDeobfuscate(List<Integer> input, int key) {
        Intrinsics.checkNotNullParameter(input, "input");
        List<Integer> $this$map$iv = input;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            int it = ((Number) item$iv$iv).intValue();
            destination$iv$iv.add(Character.valueOf((char) (it ^ key)));
        }
        return CollectionsKt.joinToString$default((List) destination$iv$iv, "", null, null, 0, null, null, 62, null);
    }

    public static final void FlagChallengePreview(Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1825070305);
        ComposerKt.sourceInformation($composer2, "C(FlagChallengePreview)125@4215L42:MainActivity.kt#wyyfmi");
        if ($changed != 0 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1825070305, $changed, -1, "com.holberton.task1.FlagChallengePreview (MainActivity.kt:124)");
            }
            ThemeKt.Task1Theme(false, false, ComposableSingletons$MainActivityKt.INSTANCE.m5558getLambda6$app_debug(), $composer2, 384, 3);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.holberton.task1.MainActivityKt.FlagChallengePreview.1
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
                MainActivityKt.FlagChallengePreview(composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }
}
