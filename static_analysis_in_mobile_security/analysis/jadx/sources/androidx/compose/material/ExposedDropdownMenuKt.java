package androidx.compose.material;

import android.graphics.Rect;
import android.view.View;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.Ref;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExposedDropdownMenu.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001aQ\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\f\u001a6\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0005H\u0002\u001a\"\u0010\u0015\u001a\u00020\u0007*\u00020\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002¨\u0006\u0019²\u0006\n\u0010\u001a\u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\n\u0010\u001b\u001a\u00020\u0013X\u008a\u008e\u0002"}, d2 = {"ExposedDropdownMenuBox", "", "expanded", "", "onExpandedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Landroidx/compose/material/ExposedDropdownMenuBoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "updateHeight", "view", "Landroid/view/View;", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "verticalMarginInPx", "", "onHeightUpdate", "expandable", "Lkotlin/Function0;", "menuLabel", "", "material_release", "width", "menuHeight"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExposedDropdownMenuKt {
    /* JADX WARN: Code duplicated, block: B:100:0x037e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:104:0x042c  */
    /* JADX WARN: Code duplicated, block: B:108:0x0439 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:112:0x0469  */
    /* JADX WARN: Code duplicated, block: B:77:0x021c  */
    /* JADX WARN: Code duplicated, block: B:78:0x0229  */
    /* JADX WARN: Code duplicated, block: B:81:0x027f  */
    /* JADX WARN: Code duplicated, block: B:84:0x028a  */
    /* JADX WARN: Code duplicated, block: B:85:0x028f  */
    /* JADX WARN: Code duplicated, block: B:89:0x0323  */
    /* JADX WARN: Code duplicated, block: B:92:0x032f  */
    /* JADX WARN: Code duplicated, block: B:93:0x0335  */
    /* JADX WARN: Code duplicated, block: B:96:0x0368  */
    public static final void ExposedDropdownMenuBox(final boolean expanded, final Function1<? super Boolean, Unit> onExpandedChange, Modifier modifier, final Function3<? super ExposedDropdownMenuBoxScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Object value$iv$iv;
        Object value$iv$iv2;
        Object value$iv$iv3;
        Object it$iv$iv;
        Object value$iv$iv4;
        Modifier modifier3;
        boolean invalid$iv$iv;
        Object value$iv$iv5;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        boolean invalid$iv$iv2;
        Object value$iv$iv6;
        Object key2$iv;
        Intrinsics.checkNotNullParameter(onExpandedChange, "onExpandedChange");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1456052980);
        ComposerKt.sourceInformation($composer2, "C(ExposedDropdownMenuBox)P(1,3,2)96@4191L7,97@4224L7,98@4249L33,99@4305L33,101@4439L37,103@4494L486,116@5006L29,130@5417L31,131@5474L38,118@5041L550,137@5608L59,137@5597L70,141@5673L441:ExposedDropdownMenu.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(expanded) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changedInstance(onExpandedChange) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 896) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 5851) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1456052980, $dirty2, -1, "androidx.compose.material.ExposedDropdownMenuBox (ExposedDropdownMenu.kt:90)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Density density = (Density) objConsume;
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final View view = (View) objConsume2;
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotIntStateKt.mutableIntStateOf(0);
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            final MutableIntState width$delegate = (MutableIntState) value$iv$iv;
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv3 = $composer2.rememberedValue();
            if (it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = SnapshotIntStateKt.mutableIntStateOf(0);
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv3;
            }
            $composer2.endReplaceableGroup();
            final MutableIntState menuHeight$delegate = (MutableIntState) value$iv$iv2;
            final int verticalMarginInPx = density.mo321roundToPx0680j_4(MenuKt.getMenuVerticalMargin());
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv4 = $composer2.rememberedValue();
            if (it$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv3 = new Ref();
                $composer2.updateRememberedValue(value$iv$iv3);
            } else {
                value$iv$iv3 = it$iv$iv4;
            }
            $composer2.endReplaceableGroup();
            final Ref coordinates = (Ref) value$iv$iv3;
            Object key2$iv2 = Integer.valueOf(ExposedDropdownMenuBox$lambda$4(menuHeight$delegate));
            Object key3$iv = Integer.valueOf(ExposedDropdownMenuBox$lambda$1(width$delegate));
            $composer2.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            boolean invalid$iv$iv3 = $composer2.changed(density) | $composer2.changed(key2$iv2) | $composer2.changed(key3$iv);
            Object value$iv$iv7 = $composer2.rememberedValue();
            if (!invalid$iv$iv3) {
                Object key3$iv2 = Composer.INSTANCE.getEmpty();
                if (value$iv$iv7 == key3$iv2) {
                }
                $composer2.endReplaceableGroup();
                ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1 scope = (ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1) value$iv$iv7;
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv = $composer2.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv4 = new FocusRequester();
                    $composer2.updateRememberedValue(value$iv$iv4);
                } else {
                    value$iv$iv4 = it$iv$iv;
                }
                $composer2.endReplaceableGroup();
                final FocusRequester focusRequester = (FocusRequester) value$iv$iv4;
                Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(modifier4, new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt.ExposedDropdownMenuBox.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$2(width$delegate, IntSize.m5434getWidthimpl(it.mo4232getSizeYbymL2g()));
                        coordinates.setValue(it);
                        View rootView = view.getRootView();
                        Intrinsics.checkNotNullExpressionValue(rootView, "view.rootView");
                        LayoutCoordinates value = coordinates.getValue();
                        int i3 = verticalMarginInPx;
                        final MutableIntState mutableIntState = menuHeight$delegate;
                        ExposedDropdownMenuKt.updateHeight(rootView, value, i3, new Function1<Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt.ExposedDropdownMenuBox.1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                invoke(num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int newHeight) {
                                ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$5(mutableIntState, newHeight);
                            }
                        });
                    }
                });
                Object key2$iv3 = Boolean.valueOf(expanded);
                int i3 = (($dirty2 >> 3) & 14) | (($dirty2 << 3) & 112);
                modifier3 = modifier4;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(onExpandedChange) | $composer2.changed(key2$iv3);
                value$iv$iv5 = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                    key2$iv = Composer.INSTANCE.getEmpty();
                    if (value$iv$iv5 == key2$iv) {
                    }
                    $composer2.endReplaceableGroup();
                    Modifier modifier$iv = FocusRequesterModifierKt.focusRequester(expandable(modifierOnGloballyPositioned, (Function0) value$iv$iv5, Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1204getExposedDropdownMenuUdPEhr4(), $composer2, 6)), focusRequester);
                    $composer2.startReplaceableGroup(733328855);
                    ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                    int $i$f$Box = ((0 >> 3) & 14) | ((0 >> 3) & 112);
                    MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer2, $i$f$Box);
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
                    int i4 = ($changed$iv$iv$iv >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i5 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1560854722, "C134@5576L9:ExposedDropdownMenu.kt#jmzs0o");
                    content.invoke(scope, $composer2, Integer.valueOf(($dirty2 >> 6) & 112));
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endReplaceableGroup();
                    $composer2.endNode();
                    $composer2.endReplaceableGroup();
                    $composer2.endReplaceableGroup();
                    Object key1$iv = Boolean.valueOf(expanded);
                    int i6 = ($dirty2 & 14) | 48;
                    $composer2.startReplaceableGroup(511388516);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                    invalid$iv$iv2 = $composer2.changed(key1$iv) | $composer2.changed(focusRequester);
                    Object it$iv$iv5 = $composer2.rememberedValue();
                    if (!invalid$iv$iv2 || it$iv$iv5 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv6 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$4$1
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
                                if (expanded) {
                                    focusRequester.requestFocus();
                                }
                            }
                        };
                        $composer2.updateRememberedValue(value$iv$iv6);
                    } else {
                        value$iv$iv6 = it$iv$iv5;
                    }
                    $composer2.endReplaceableGroup();
                    EffectsKt.SideEffect((Function0) value$iv$iv6, $composer2, 0);
                    EffectsKt.DisposableEffect(view, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.material.ExposedDropdownMenuKt.ExposedDropdownMenuBox.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                            Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                            View view2 = view;
                            final View view3 = view;
                            final Ref<LayoutCoordinates> ref = coordinates;
                            final int i7 = verticalMarginInPx;
                            final MutableIntState mutableIntState = menuHeight$delegate;
                            final OnGlobalLayoutListener listener = new OnGlobalLayoutListener(view2, new Function0<Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$5$listener$1
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
                                    View rootView = view3.getRootView();
                                    Intrinsics.checkNotNullExpressionValue(rootView, "view.rootView");
                                    LayoutCoordinates value = ref.getValue();
                                    int i8 = i7;
                                    final MutableIntState mutableIntState2 = mutableIntState;
                                    ExposedDropdownMenuKt.updateHeight(rootView, value, i8, new Function1<Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$5$listener$1.1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                            invoke(num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(int newHeight) {
                                            ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$5(mutableIntState2, newHeight);
                                        }
                                    });
                                }
                            });
                            return new DisposableEffectResult() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$5$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    listener.dispose();
                                }
                            };
                        }
                    }, $composer2, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                value$iv$iv5 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$2$1
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
                        onExpandedChange.invoke(Boolean.valueOf(!expanded));
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv5);
                $composer2.endReplaceableGroup();
                Modifier modifier$iv2 = FocusRequesterModifierKt.focusRequester(expandable(modifierOnGloballyPositioned, (Function0) value$iv$iv5, Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1204getExposedDropdownMenuUdPEhr4(), $composer2, 6)), focusRequester);
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                int $i$f$Box2 = ((0 >> 3) & 14) | ((0 >> 3) & 112);
                MeasurePolicy measurePolicy$iv2 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv2, false, $composer2, $i$f$Box2);
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
                int i7 = ($changed$iv$iv$iv2 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i8 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1560854722, "C134@5576L9:ExposedDropdownMenu.kt#jmzs0o");
                content.invoke(scope, $composer2, Integer.valueOf(($dirty2 >> 6) & 112));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                Object key1$iv2 = Boolean.valueOf(expanded);
                int i9 = ($dirty2 & 14) | 48;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv2 = $composer2.changed(key1$iv2) | $composer2.changed(focusRequester);
                Object it$iv$iv6 = $composer2.rememberedValue();
                if (invalid$iv$iv2) {
                }
                value$iv$iv6 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$4$1
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
                        if (expanded) {
                            focusRequester.requestFocus();
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv6);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv6, $composer2, 0);
                EffectsKt.DisposableEffect(view, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.material.ExposedDropdownMenuKt.ExposedDropdownMenuBox.5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                        View view2 = view;
                        final View view3 = view;
                        final Ref<LayoutCoordinates> ref = coordinates;
                        final int i10 = verticalMarginInPx;
                        final MutableIntState mutableIntState = menuHeight$delegate;
                        final OnGlobalLayoutListener listener = new OnGlobalLayoutListener(view2, new Function0<Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$5$listener$1
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
                                View rootView = view3.getRootView();
                                Intrinsics.checkNotNullExpressionValue(rootView, "view.rootView");
                                LayoutCoordinates value = ref.getValue();
                                int i11 = i10;
                                final MutableIntState mutableIntState2 = mutableIntState;
                                ExposedDropdownMenuKt.updateHeight(rootView, value, i11, new Function1<Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$5$listener$1.1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                        invoke(num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(int newHeight) {
                                        ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$5(mutableIntState2, newHeight);
                                    }
                                });
                            }
                        });
                        return new DisposableEffectResult() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$5$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                listener.dispose();
                            }
                        };
                    }
                }, $composer2, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            value$iv$iv7 = new ExposedDropdownMenuBoxScope() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1
                @Override // androidx.compose.material.ExposedDropdownMenuBoxScope
                public /* synthetic */ void ExposedDropdownMenu(boolean z, Function0 function1, Modifier modifier5, ScrollState scrollState, Function3 function3, Composer composer, int i10, int i11) {
                    ExposedDropdownMenuBoxScope.CC.$default$ExposedDropdownMenu(this, z, function1, modifier5, scrollState, function3, composer, i10, i11);
                }

                @Override // androidx.compose.material.ExposedDropdownMenuBoxScope
                public Modifier exposedDropdownSize(Modifier $this$exposedDropdownSize, boolean matchTextFieldWidth) {
                    Intrinsics.checkNotNullParameter($this$exposedDropdownSize, "<this>");
                    Density $this$exposedDropdownSize_u24lambda_u241 = density;
                    MutableIntState mutableIntState = menuHeight$delegate;
                    MutableIntState mutableIntState2 = width$delegate;
                    Modifier it = SizeKt.m522heightInVpY3zN4$default($this$exposedDropdownSize, 0.0f, $this$exposedDropdownSize_u24lambda_u241.mo324toDpu2uoSUM(ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$4(mutableIntState)), 1, null);
                    if (matchTextFieldWidth) {
                        return SizeKt.m539width3ABfNKs(it, $this$exposedDropdownSize_u24lambda_u241.mo324toDpu2uoSUM(ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$1(mutableIntState2)));
                    }
                    return it;
                }
            };
            $composer2.updateRememberedValue(value$iv$iv7);
            $composer2.endReplaceableGroup();
            ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1 scope2 = (ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1) value$iv$iv7;
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv4 = new FocusRequester();
                $composer2.updateRememberedValue(value$iv$iv4);
            } else {
                value$iv$iv4 = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            final FocusRequester focusRequester2 = (FocusRequester) value$iv$iv4;
            Modifier modifierOnGloballyPositioned2 = OnGloballyPositionedModifierKt.onGloballyPositioned(modifier4, new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt.ExposedDropdownMenuBox.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                    invoke2(layoutCoordinates);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LayoutCoordinates it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$2(width$delegate, IntSize.m5434getWidthimpl(it.mo4232getSizeYbymL2g()));
                    coordinates.setValue(it);
                    View rootView = view.getRootView();
                    Intrinsics.checkNotNullExpressionValue(rootView, "view.rootView");
                    LayoutCoordinates value = coordinates.getValue();
                    int i10 = verticalMarginInPx;
                    final MutableIntState mutableIntState = menuHeight$delegate;
                    ExposedDropdownMenuKt.updateHeight(rootView, value, i10, new Function1<Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt.ExposedDropdownMenuBox.1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                            invoke(num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int newHeight) {
                            ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$5(mutableIntState, newHeight);
                        }
                    });
                }
            });
            Object key2$iv4 = Boolean.valueOf(expanded);
            int i10 = (($dirty2 >> 3) & 14) | (($dirty2 << 3) & 112);
            modifier3 = modifier4;
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(onExpandedChange) | $composer2.changed(key2$iv4);
            value$iv$iv5 = $composer2.rememberedValue();
            if (!invalid$iv$iv) {
                key2$iv = Composer.INSTANCE.getEmpty();
                if (value$iv$iv5 == key2$iv) {
                }
                $composer2.endReplaceableGroup();
                Modifier modifier$iv3 = FocusRequesterModifierKt.focusRequester(expandable(modifierOnGloballyPositioned2, (Function0) value$iv$iv5, Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1204getExposedDropdownMenuUdPEhr4(), $composer2, 6)), focusRequester2);
                $composer2.startReplaceableGroup(733328855);
                ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                int $i$f$Box3 = ((0 >> 3) & 14) | ((0 >> 3) & 112);
                MeasurePolicy measurePolicy$iv3 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv3, false, $composer2, $i$f$Box3);
                int $changed$iv$iv3 = (0 << 3) & 112;
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifier$iv3);
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 9) & 7168) | 6;
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
                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
                function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i11 = ($changed$iv$iv$iv3 >> 9) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i12 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1560854722, "C134@5576L9:ExposedDropdownMenu.kt#jmzs0o");
                content.invoke(scope2, $composer2, Integer.valueOf(($dirty2 >> 6) & 112));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                $composer2.endReplaceableGroup();
                Object key1$iv3 = Boolean.valueOf(expanded);
                int i13 = ($dirty2 & 14) | 48;
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv2 = $composer2.changed(key1$iv3) | $composer2.changed(focusRequester2);
                Object it$iv$iv7 = $composer2.rememberedValue();
                if (invalid$iv$iv2) {
                }
                value$iv$iv6 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$4$1
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
                        if (expanded) {
                            focusRequester2.requestFocus();
                        }
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv6);
                $composer2.endReplaceableGroup();
                EffectsKt.SideEffect((Function0) value$iv$iv6, $composer2, 0);
                EffectsKt.DisposableEffect(view, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.material.ExposedDropdownMenuKt.ExposedDropdownMenuBox.5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                        View view2 = view;
                        final View view3 = view;
                        final Ref<LayoutCoordinates> ref = coordinates;
                        final int i14 = verticalMarginInPx;
                        final MutableIntState mutableIntState = menuHeight$delegate;
                        final OnGlobalLayoutListener listener = new OnGlobalLayoutListener(view2, new Function0<Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$5$listener$1
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
                                View rootView = view3.getRootView();
                                Intrinsics.checkNotNullExpressionValue(rootView, "view.rootView");
                                LayoutCoordinates value = ref.getValue();
                                int i15 = i14;
                                final MutableIntState mutableIntState2 = mutableIntState;
                                ExposedDropdownMenuKt.updateHeight(rootView, value, i15, new Function1<Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$5$listener$1.1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                        invoke(num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(int newHeight) {
                                        ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$5(mutableIntState2, newHeight);
                                    }
                                });
                            }
                        });
                        return new DisposableEffectResult() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$5$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                listener.dispose();
                            }
                        };
                    }
                }, $composer2, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            value$iv$iv5 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$2$1
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
                    onExpandedChange.invoke(Boolean.valueOf(!expanded));
                }
            };
            $composer2.updateRememberedValue(value$iv$iv5);
            $composer2.endReplaceableGroup();
            Modifier modifier$iv4 = FocusRequesterModifierKt.focusRequester(expandable(modifierOnGloballyPositioned2, (Function0) value$iv$iv5, Strings_androidKt.m1208getString4foXLRw(Strings.INSTANCE.m1204getExposedDropdownMenuUdPEhr4(), $composer2, 6)), focusRequester2);
            $composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer2, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
            int $i$f$Box4 = ((0 >> 3) & 14) | ((0 >> 3) & 112);
            MeasurePolicy measurePolicy$iv4 = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv4, false, $composer2, $i$f$Box4);
            int $changed$iv$iv4 = (0 << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer2.getCurrentCompositionLocalMap();
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifier$iv4);
            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 9) & 7168) | 6;
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
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash4);
            function3ModifierMaterializerOf4.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv4 >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i14 = ($changed$iv$iv$iv4 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            int i15 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1560854722, "C134@5576L9:ExposedDropdownMenu.kt#jmzs0o");
            content.invoke(scope2, $composer2, Integer.valueOf(($dirty2 >> 6) & 112));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            Object key1$iv4 = Boolean.valueOf(expanded);
            int i16 = ($dirty2 & 14) | 48;
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer2.changed(key1$iv4) | $composer2.changed(focusRequester2);
            Object it$iv$iv8 = $composer2.rememberedValue();
            if (invalid$iv$iv2) {
            }
            value$iv$iv6 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$4$1
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
                    if (expanded) {
                        focusRequester2.requestFocus();
                    }
                }
            };
            $composer2.updateRememberedValue(value$iv$iv6);
            $composer2.endReplaceableGroup();
            EffectsKt.SideEffect((Function0) value$iv$iv6, $composer2, 0);
            EffectsKt.DisposableEffect(view, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.material.ExposedDropdownMenuKt.ExposedDropdownMenuBox.5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                    Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                    View view2 = view;
                    final View view3 = view;
                    final Ref<LayoutCoordinates> ref = coordinates;
                    final int i17 = verticalMarginInPx;
                    final MutableIntState mutableIntState = menuHeight$delegate;
                    final OnGlobalLayoutListener listener = new OnGlobalLayoutListener(view2, new Function0<Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$5$listener$1
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
                            View rootView = view3.getRootView();
                            Intrinsics.checkNotNullExpressionValue(rootView, "view.rootView");
                            LayoutCoordinates value = ref.getValue();
                            int i18 = i17;
                            final MutableIntState mutableIntState2 = mutableIntState;
                            ExposedDropdownMenuKt.updateHeight(rootView, value, i18, new Function1<Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$5$listener$1.1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                    invoke(num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(int newHeight) {
                                    ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$5(mutableIntState2, newHeight);
                                }
                            });
                        }
                    });
                    return new DisposableEffectResult() { // from class: androidx.compose.material.ExposedDropdownMenuKt$ExposedDropdownMenuBox$5$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            listener.dispose();
                        }
                    };
                }
            }, $composer2, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt.ExposedDropdownMenuBox.6
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

            public final void invoke(Composer composer, int i17) {
                ExposedDropdownMenuKt.ExposedDropdownMenuBox(expanded, onExpandedChange, modifier5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int ExposedDropdownMenuBox$lambda$1(MutableIntState $width$delegate) {
        MutableIntState $this$getValue$iv = $width$delegate;
        return $this$getValue$iv.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ExposedDropdownMenuBox$lambda$2(MutableIntState $width$delegate, int value) {
        $width$delegate.setIntValue(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int ExposedDropdownMenuBox$lambda$4(MutableIntState $menuHeight$delegate) {
        MutableIntState $this$getValue$iv = $menuHeight$delegate;
        return $this$getValue$iv.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ExposedDropdownMenuBox$lambda$5(MutableIntState $menuHeight$delegate, int value) {
        $menuHeight$delegate.setIntValue(value);
    }

    /* JADX INFO: renamed from: androidx.compose.material.ExposedDropdownMenuKt$expandable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ExposedDropdownMenu.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "androidx.compose.material.ExposedDropdownMenuKt$expandable$1", f = "ExposedDropdownMenu.kt", i = {}, l = {521}, m = "invokeSuspend", n = {}, s = {})
    static final class C02861 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $onExpandedChange;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02861(Function0<Unit> function0, Continuation<? super C02861> continuation) {
            super(2, continuation);
            this.$onExpandedChange = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02861 c02861 = new C02861(this.$onExpandedChange, continuation);
            c02861.L$0 = obj;
            return c02861;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C02861) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.material.ExposedDropdownMenuKt$expandable$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: ExposedDropdownMenu.kt */
        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        @DebugMetadata(c = "androidx.compose.material.ExposedDropdownMenuKt$expandable$1$1", f = "ExposedDropdownMenu.kt", i = {0}, l = {524, 525}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
        static final class C00491 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function0<Unit> $onExpandedChange;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00491(Function0<Unit> function0, Continuation<? super C00491> continuation) {
                super(2, continuation);
                this.$onExpandedChange = function0;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00491 c00491 = new C00491(this.$onExpandedChange, continuation);
                c00491.L$0 = obj;
                return c00491;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00491) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:13:0x004f A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:14:0x0050  */
            /* JADX WARN: Code duplicated, block: B:17:0x0056  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                C00491 c00491;
                AwaitPointerEventScope $this$awaitEachGesture;
                Object objWaitForUpOrCancellation;
                PointerInputChange upEvent;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        c00491 = this;
                        $this$awaitEachGesture = (AwaitPointerEventScope) c00491.L$0;
                        c00491.L$0 = $this$awaitEachGesture;
                        c00491.label = 1;
                        if (TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture, false, PointerEventPass.Initial, c00491, 1, null) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        c00491.L$0 = null;
                        c00491.label = 2;
                        objWaitForUpOrCancellation = TapGestureDetectorKt.waitForUpOrCancellation($this$awaitEachGesture, PointerEventPass.Initial, c00491);
                        if (objWaitForUpOrCancellation == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objWaitForUpOrCancellation;
                        upEvent = (PointerInputChange) $result;
                        if (upEvent != null) {
                            c00491.$onExpandedChange.invoke();
                        }
                        return Unit.INSTANCE;
                    case 1:
                        c00491 = this;
                        $this$awaitEachGesture = (AwaitPointerEventScope) c00491.L$0;
                        ResultKt.throwOnFailure($result);
                        c00491.L$0 = null;
                        c00491.label = 2;
                        objWaitForUpOrCancellation = TapGestureDetectorKt.waitForUpOrCancellation($this$awaitEachGesture, PointerEventPass.Initial, c00491);
                        if (objWaitForUpOrCancellation == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objWaitForUpOrCancellation;
                        upEvent = (PointerInputChange) $result;
                        if (upEvent != null) {
                            c00491.$onExpandedChange.invoke();
                        }
                        return Unit.INSTANCE;
                    case 2:
                        ResultKt.throwOnFailure($result);
                        c00491 = this;
                        upEvent = (PointerInputChange) $result;
                        if (upEvent != null) {
                            c00491.$onExpandedChange.invoke();
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputScope $this$pointerInput = (PointerInputScope) this.L$0;
                    this.label = 1;
                    if (ForEachGestureKt.awaitEachGesture($this$pointerInput, new C00491(this.$onExpandedChange, null), this) == coroutine_suspended) {
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

    private static final Modifier expandable(Modifier $this$expandable, final Function0<Unit> function0, final String menuLabel) {
        return SemanticsModifierKt.semantics$default(SuspendingPointerInputFilterKt.pointerInput($this$expandable, Unit.INSTANCE, new C02861(function0, null)), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ExposedDropdownMenuKt.expandable.2
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
                SemanticsPropertiesKt.setContentDescription(semantics, menuLabel);
                final Function0<Unit> function1 = function0;
                SemanticsPropertiesKt.onClick$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ExposedDropdownMenuKt.expandable.2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        function1.invoke();
                        return true;
                    }
                }, 1, null);
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateHeight(View view, LayoutCoordinates coordinates, int verticalMarginInPx, Function1<? super Integer, Unit> function1) {
        if (coordinates == null) {
            return;
        }
        Rect it = new Rect();
        view.getWindowVisibleDisplayFrame(it);
        float heightAbove = LayoutCoordinatesKt.boundsInWindow(coordinates).getTop() - it.top;
        float heightBelow = (it.bottom - it.top) - LayoutCoordinatesKt.boundsInWindow(coordinates).getBottom();
        function1.invoke(Integer.valueOf(((int) Math.max(heightAbove, heightBelow)) - verticalMarginInPx));
    }
}
