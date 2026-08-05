package androidx.compose.material3;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidMenu.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001ag\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u008e\u0001\u0010\u0013\u001a\u00020\u00012\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u000f2\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0007¢\u0006\u0002\u0010\u001f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006 "}, d2 = {"DropdownMenu", "", "expanded", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "offset", "Landroidx/compose/ui/unit/DpOffset;", "properties", "Landroidx/compose/ui/window/PopupProperties;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "DropdownMenu-ILWXrKs", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenuItem", "text", "onClick", "leadingIcon", "trailingIcon", "enabled", "colors", "Landroidx/compose/material3/MenuItemColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/MenuItemColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidMenu_androidKt {
    /* JADX WARN: Code duplicated, block: B:103:0x0220 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:108:0x0282  */
    /* JADX WARN: Code duplicated, block: B:113:0x028e  */
    /* JADX WARN: Code duplicated, block: B:115:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x00e5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:76:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:77:0x0107  */
    /* JADX WARN: Code duplicated, block: B:79:0x010b  */
    /* JADX WARN: Code duplicated, block: B:82:0x0129  */
    /* JADX WARN: Code duplicated, block: B:85:0x014f  */
    /* JADX WARN: Code duplicated, block: B:86:0x0163  */
    /* JADX WARN: Code duplicated, block: B:89:0x0183  */
    /* JADX WARN: Code duplicated, block: B:93:0x0194  */
    /* JADX WARN: Code duplicated, block: B:95:0x01af  */
    /* JADX WARN: Code duplicated, block: B:96:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:99:0x0213  */
    /* JADX INFO: renamed from: DropdownMenu-ILWXrKs, reason: not valid java name */
    public static final void m1308DropdownMenuILWXrKs(final boolean expanded, final Function0<Unit> onDismissRequest, Modifier modifier, long offset, PopupProperties properties, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        PopupProperties properties2;
        int i2;
        final int $dirty;
        Modifier modifier3;
        long offset2;
        Object it$iv$iv;
        Object value$iv$iv;
        final MutableTransitionState expandedStates;
        Object it$iv$iv2;
        Object value$iv$iv2;
        boolean invalid$iv$iv;
        Object value$iv$iv3;
        PopupProperties properties3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onDismissRequest, "onDismissRequest");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(354826666);
        ComposerKt.sourceInformation($composer2, "C(DropdownMenu)P(1,4,2,3:c#ui.unit.DpOffset,5)81@4015L42,85@4205L51,86@4292L7,90@4416L131,94@4557L400:AndroidMenu.android.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(expanded) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changedInstance(onDismissRequest) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 896) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            j = offset;
        } else if (($changed & 7168) == 0) {
            j = offset;
            $dirty2 |= $composer2.changed(j) ? 2048 : 1024;
        } else {
            j = offset;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            properties2 = properties;
        } else if ((57344 & $changed) == 0) {
            properties2 = properties;
            $dirty2 |= $composer2.changed(properties2) ? 16384 : 8192;
        } else {
            properties2 = properties;
        }
        if ((i & 32) == 0) {
            if ((458752 & $changed) == 0) {
                i2 = $composer2.changedInstance(content) ? 131072 : 65536;
            }
            $dirty = $dirty2;
            if ((374491 & $dirty) == 74898 || !$composer2.getSkipping()) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    offset2 = DpKt.m5295DpOffsetYgX7TsA(Dp.m5274constructorimpl(0), Dp.m5274constructorimpl(0));
                } else {
                    offset2 = j;
                }
                if (i5 != 0) {
                    properties2 = new PopupProperties(true, false, false, null, false, false, 62, null);
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(354826666, $dirty, -1, "androidx.compose.material3.DropdownMenu (AndroidMenu.android.kt:73)");
                }
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv = $composer2.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = new MutableTransitionState(false);
                    $composer2.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                $composer2.endReplaceableGroup();
                expandedStates = (MutableTransitionState) value$iv$iv;
                expandedStates.setTargetState(Boolean.valueOf(expanded));
                if (((Boolean) expandedStates.getCurrentState()).booleanValue() || ((Boolean) expandedStates.getTargetState()).booleanValue()) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv2 = $composer2.rememberedValue();
                    if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m3349boximpl(TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ()), null, 2, null);
                        $composer2.updateRememberedValue(value$iv$iv2);
                    } else {
                        value$iv$iv2 = it$iv$iv2;
                    }
                    $composer2.endReplaceableGroup();
                    final MutableState transformOriginState = (MutableState) value$iv$iv2;
                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer2.consume(localDensity);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Density density = (Density) objConsume;
                    $composer2.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                    invalid$iv$iv = $composer2.changed(transformOriginState);
                    Object it$iv$iv3 = $composer2.rememberedValue();
                    if (!invalid$iv$iv || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv3 = (Function2) new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(IntRect intRect, IntRect intRect2) {
                                invoke2(intRect, intRect2);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(IntRect parentBounds, IntRect menuBounds) {
                                Intrinsics.checkNotNullParameter(parentBounds, "parentBounds");
                                Intrinsics.checkNotNullParameter(menuBounds, "menuBounds");
                                transformOriginState.setValue(TransformOrigin.m3349boximpl(MenuKt.calculateTransformOrigin(parentBounds, menuBounds)));
                            }
                        };
                        $composer2.updateRememberedValue(value$iv$iv3);
                    } else {
                        value$iv$iv3 = it$iv$iv3;
                    }
                    $composer2.endReplaceableGroup();
                    DropdownMenuPositionProvider popupPositionProvider = new DropdownMenuPositionProvider(offset2, density, (Function2) value$iv$iv3, null);
                    final Modifier modifier4 = modifier3;
                    AndroidPopup_androidKt.Popup(popupPositionProvider, onDismissRequest, properties2, ComposableLambdaKt.composableLambda($composer2, -1192563503, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$1
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
                            ComposerKt.sourceInformation($composer3, "C99@4732L215:AndroidMenu.android.kt#uh7d8r");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1192563503, $changed2, -1, "androidx.compose.material3.DropdownMenu.<anonymous> (AndroidMenu.android.kt:98)");
                                }
                                MutableTransitionState<Boolean> mutableTransitionState = expandedStates;
                                MutableState<TransformOrigin> mutableState = transformOriginState;
                                Modifier modifier5 = modifier4;
                                Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                                int i6 = MutableTransitionState.$stable | 48;
                                int i7 = $dirty;
                                MenuKt.DropdownMenuContent(mutableTransitionState, mutableState, modifier5, function3, $composer3, i6 | (i7 & 896) | ((i7 >> 6) & 7168), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    }), $composer2, ($dirty & 112) | 3072 | (($dirty >> 6) & 896), 0);
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                properties3 = properties2;
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                offset2 = j;
                properties3 = properties2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier3;
            final long j2 = offset2;
            final PopupProperties popupProperties = properties3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$2
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

                public final void invoke(Composer composer, int i6) {
                    AndroidMenu_androidKt.m1308DropdownMenuILWXrKs(expanded, onDismissRequest, modifier5, j2, popupProperties, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty2 |= i2;
        $dirty = $dirty2;
        if ((374491 & $dirty) == 74898) {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                offset2 = DpKt.m5295DpOffsetYgX7TsA(Dp.m5274constructorimpl(0), Dp.m5274constructorimpl(0));
            } else {
                offset2 = j;
            }
            if (i5 != 0) {
                properties2 = new PopupProperties(true, false, false, null, false, false, 62, null);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(354826666, $dirty, -1, "androidx.compose.material3.DropdownMenu (AndroidMenu.android.kt:73)");
            }
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new MutableTransitionState(false);
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            expandedStates = (MutableTransitionState) value$iv$iv;
            expandedStates.setTargetState(Boolean.valueOf(expanded));
            if (((Boolean) expandedStates.getCurrentState()).booleanValue()) {
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv2 = $composer2.rememberedValue();
                if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m3349boximpl(TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ()), null, 2, null);
                    $composer2.updateRememberedValue(value$iv$iv2);
                } else {
                    value$iv$iv2 = it$iv$iv2;
                }
                $composer2.endReplaceableGroup();
                final MutableState<TransformOrigin> transformOriginState2 = (MutableState) value$iv$iv2;
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density2 = (Density) objConsume2;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(transformOriginState2);
                Object it$iv$iv4 = $composer2.rememberedValue();
                if (invalid$iv$iv) {
                }
                value$iv$iv3 = (Function2) new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IntRect intRect, IntRect intRect2) {
                        invoke2(intRect, intRect2);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IntRect parentBounds, IntRect menuBounds) {
                        Intrinsics.checkNotNullParameter(parentBounds, "parentBounds");
                        Intrinsics.checkNotNullParameter(menuBounds, "menuBounds");
                        transformOriginState2.setValue(TransformOrigin.m3349boximpl(MenuKt.calculateTransformOrigin(parentBounds, menuBounds)));
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv3);
                $composer2.endReplaceableGroup();
                DropdownMenuPositionProvider popupPositionProvider2 = new DropdownMenuPositionProvider(offset2, density2, (Function2) value$iv$iv3, null);
                final Modifier modifier6 = modifier3;
                AndroidPopup_androidKt.Popup(popupPositionProvider2, onDismissRequest, properties2, ComposableLambdaKt.composableLambda($composer2, -1192563503, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$1
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
                        ComposerKt.sourceInformation($composer3, "C99@4732L215:AndroidMenu.android.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1192563503, $changed2, -1, "androidx.compose.material3.DropdownMenu.<anonymous> (AndroidMenu.android.kt:98)");
                            }
                            MutableTransitionState<Boolean> mutableTransitionState = expandedStates;
                            MutableState<TransformOrigin> mutableState = transformOriginState2;
                            Modifier modifier7 = modifier6;
                            Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                            int i6 = MutableTransitionState.$stable | 48;
                            int i7 = $dirty;
                            MenuKt.DropdownMenuContent(mutableTransitionState, mutableState, modifier7, function3, $composer3, i6 | (i7 & 896) | ((i7 >> 6) & 7168), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, ($dirty & 112) | 3072 | (($dirty >> 6) & 896), 0);
            } else {
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv2 = $composer2.rememberedValue();
                if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m3349boximpl(TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ()), null, 2, null);
                    $composer2.updateRememberedValue(value$iv$iv2);
                } else {
                    value$iv$iv2 = it$iv$iv2;
                }
                $composer2.endReplaceableGroup();
                final MutableState<TransformOrigin> transformOriginState3 = (MutableState) value$iv$iv2;
                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer2.consume(localDensity3);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density3 = (Density) objConsume3;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(transformOriginState3);
                Object it$iv$iv5 = $composer2.rememberedValue();
                if (invalid$iv$iv) {
                }
                value$iv$iv3 = (Function2) new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IntRect intRect, IntRect intRect2) {
                        invoke2(intRect, intRect2);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IntRect parentBounds, IntRect menuBounds) {
                        Intrinsics.checkNotNullParameter(parentBounds, "parentBounds");
                        Intrinsics.checkNotNullParameter(menuBounds, "menuBounds");
                        transformOriginState3.setValue(TransformOrigin.m3349boximpl(MenuKt.calculateTransformOrigin(parentBounds, menuBounds)));
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv3);
                $composer2.endReplaceableGroup();
                DropdownMenuPositionProvider popupPositionProvider3 = new DropdownMenuPositionProvider(offset2, density3, (Function2) value$iv$iv3, null);
                final Modifier modifier7 = modifier3;
                AndroidPopup_androidKt.Popup(popupPositionProvider3, onDismissRequest, properties2, ComposableLambdaKt.composableLambda($composer2, -1192563503, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$1
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
                        ComposerKt.sourceInformation($composer3, "C99@4732L215:AndroidMenu.android.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1192563503, $changed2, -1, "androidx.compose.material3.DropdownMenu.<anonymous> (AndroidMenu.android.kt:98)");
                            }
                            MutableTransitionState<Boolean> mutableTransitionState = expandedStates;
                            MutableState<TransformOrigin> mutableState = transformOriginState3;
                            Modifier modifier8 = modifier7;
                            Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                            int i6 = MutableTransitionState.$stable | 48;
                            int i7 = $dirty;
                            MenuKt.DropdownMenuContent(mutableTransitionState, mutableState, modifier8, function3, $composer3, i6 | (i7 & 896) | ((i7 >> 6) & 7168), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, ($dirty & 112) | 3072 | (($dirty >> 6) & 896), 0);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            properties3 = properties2;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                offset2 = DpKt.m5295DpOffsetYgX7TsA(Dp.m5274constructorimpl(0), Dp.m5274constructorimpl(0));
            } else {
                offset2 = j;
            }
            if (i5 != 0) {
                properties2 = new PopupProperties(true, false, false, null, false, false, 62, null);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(354826666, $dirty, -1, "androidx.compose.material3.DropdownMenu (AndroidMenu.android.kt:73)");
            }
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new MutableTransitionState(false);
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            expandedStates = (MutableTransitionState) value$iv$iv;
            expandedStates.setTargetState(Boolean.valueOf(expanded));
            if (((Boolean) expandedStates.getCurrentState()).booleanValue()) {
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv2 = $composer2.rememberedValue();
                if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m3349boximpl(TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ()), null, 2, null);
                    $composer2.updateRememberedValue(value$iv$iv2);
                } else {
                    value$iv$iv2 = it$iv$iv2;
                }
                $composer2.endReplaceableGroup();
                final MutableState<TransformOrigin> transformOriginState4 = (MutableState) value$iv$iv2;
                ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume4 = $composer2.consume(localDensity4);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density4 = (Density) objConsume4;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(transformOriginState4);
                Object it$iv$iv6 = $composer2.rememberedValue();
                if (invalid$iv$iv) {
                }
                value$iv$iv3 = (Function2) new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IntRect intRect, IntRect intRect2) {
                        invoke2(intRect, intRect2);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IntRect parentBounds, IntRect menuBounds) {
                        Intrinsics.checkNotNullParameter(parentBounds, "parentBounds");
                        Intrinsics.checkNotNullParameter(menuBounds, "menuBounds");
                        transformOriginState4.setValue(TransformOrigin.m3349boximpl(MenuKt.calculateTransformOrigin(parentBounds, menuBounds)));
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv3);
                $composer2.endReplaceableGroup();
                DropdownMenuPositionProvider popupPositionProvider4 = new DropdownMenuPositionProvider(offset2, density4, (Function2) value$iv$iv3, null);
                final Modifier modifier8 = modifier3;
                AndroidPopup_androidKt.Popup(popupPositionProvider4, onDismissRequest, properties2, ComposableLambdaKt.composableLambda($composer2, -1192563503, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$1
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
                        ComposerKt.sourceInformation($composer3, "C99@4732L215:AndroidMenu.android.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1192563503, $changed2, -1, "androidx.compose.material3.DropdownMenu.<anonymous> (AndroidMenu.android.kt:98)");
                            }
                            MutableTransitionState<Boolean> mutableTransitionState = expandedStates;
                            MutableState<TransformOrigin> mutableState = transformOriginState4;
                            Modifier modifier9 = modifier8;
                            Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                            int i6 = MutableTransitionState.$stable | 48;
                            int i7 = $dirty;
                            MenuKt.DropdownMenuContent(mutableTransitionState, mutableState, modifier9, function3, $composer3, i6 | (i7 & 896) | ((i7 >> 6) & 7168), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, ($dirty & 112) | 3072 | (($dirty >> 6) & 896), 0);
            } else {
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv2 = $composer2.rememberedValue();
                if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m3349boximpl(TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ()), null, 2, null);
                    $composer2.updateRememberedValue(value$iv$iv2);
                } else {
                    value$iv$iv2 = it$iv$iv2;
                }
                $composer2.endReplaceableGroup();
                final MutableState<TransformOrigin> transformOriginState5 = (MutableState) value$iv$iv2;
                ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume5 = $composer2.consume(localDensity5);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Density density5 = (Density) objConsume5;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(transformOriginState5);
                Object it$iv$iv7 = $composer2.rememberedValue();
                if (invalid$iv$iv) {
                }
                value$iv$iv3 = (Function2) new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IntRect intRect, IntRect intRect2) {
                        invoke2(intRect, intRect2);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IntRect parentBounds, IntRect menuBounds) {
                        Intrinsics.checkNotNullParameter(parentBounds, "parentBounds");
                        Intrinsics.checkNotNullParameter(menuBounds, "menuBounds");
                        transformOriginState5.setValue(TransformOrigin.m3349boximpl(MenuKt.calculateTransformOrigin(parentBounds, menuBounds)));
                    }
                };
                $composer2.updateRememberedValue(value$iv$iv3);
                $composer2.endReplaceableGroup();
                DropdownMenuPositionProvider popupPositionProvider5 = new DropdownMenuPositionProvider(offset2, density5, (Function2) value$iv$iv3, null);
                final Modifier modifier9 = modifier3;
                AndroidPopup_androidKt.Popup(popupPositionProvider5, onDismissRequest, properties2, ComposableLambdaKt.composableLambda($composer2, -1192563503, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$1
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
                        ComposerKt.sourceInformation($composer3, "C99@4732L215:AndroidMenu.android.kt#uh7d8r");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1192563503, $changed2, -1, "androidx.compose.material3.DropdownMenu.<anonymous> (AndroidMenu.android.kt:98)");
                            }
                            MutableTransitionState<Boolean> mutableTransitionState = expandedStates;
                            MutableState<TransformOrigin> mutableState = transformOriginState5;
                            Modifier modifier10 = modifier9;
                            Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                            int i6 = MutableTransitionState.$stable | 48;
                            int i7 = $dirty;
                            MenuKt.DropdownMenuContent(mutableTransitionState, mutableState, modifier10, function3, $composer3, i6 | (i7 & 896) | ((i7 >> 6) & 7168), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, ($dirty & 112) | 3072 | (($dirty >> 6) & 896), 0);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            properties3 = properties2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier10 = modifier3;
        final long j3 = offset2;
        final PopupProperties popupProperties2 = properties3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$2
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

            public final void invoke(Composer composer, int i6) {
                AndroidMenu_androidKt.m1308DropdownMenuILWXrKs(expanded, onDismissRequest, modifier10, j3, popupProperties2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static final void DropdownMenuItem(final Function2<? super Composer, ? super Integer, Unit> text, final Function0<Unit> onClick, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, boolean enabled, MenuItemColors colors, PaddingValues contentPadding, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function4;
        boolean z;
        MenuItemColors colors2;
        PaddingValues contentPadding2;
        int $dirty;
        MutableInteractionSource interactionSource2;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Function2<? super Composer, ? super Integer, Unit> function6;
        boolean enabled2;
        MenuItemColors colors3;
        Object value$iv$iv;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer3 = $composer.startRestartGroup(1826340448);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenuItem)P(7,6,5,4,8,2)144@6904L12,146@7049L39,148@7098L319:AndroidMenu.android.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(text) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changedInstance(onClick) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 2048 : 1024;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
            function4 = function3;
        } else if (($changed & 57344) == 0) {
            function4 = function3;
            $dirty2 |= $composer3.changedInstance(function4) ? 16384 : 8192;
        } else {
            function4 = function3;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z = enabled;
        } else if (($changed & 458752) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 131072 : 65536;
        } else {
            z = enabled;
        }
        if (($changed & 3670016) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer3.changed(colors)) ? 1048576 : 524288;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty2 |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($dirty2 & 191739611) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            function5 = function2;
            colors3 = colors;
            contentPadding2 = contentPadding;
            interactionSource2 = interactionSource;
            function6 = function4;
            $composer2 = $composer3;
            enabled2 = z;
            modifier2 = modifier;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
                Function2<? super Composer, ? super Integer, Unit> function7 = i3 != 0 ? null : function2;
                Function2<? super Composer, ? super Integer, Unit> function8 = i4 != 0 ? null : function4;
                boolean enabled3 = i5 != 0 ? true : z;
                if ((i & 64) != 0) {
                    colors2 = MenuDefaults.INSTANCE.m1610itemColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer3, 1572864, 63);
                    $dirty2 &= -3670017;
                } else {
                    colors2 = colors;
                }
                PaddingValues contentPadding3 = i6 != 0 ? MenuDefaults.INSTANCE.getDropdownMenuItemContentPadding() : contentPadding;
                if (i7 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    PaddingValues contentPadding4 = contentPadding3;
                    Object it$iv$iv = $composer3.rememberedValue();
                    int $dirty3 = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    contentPadding2 = contentPadding4;
                    $dirty = $dirty3;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier2 = modifier3;
                    function5 = function7;
                    function6 = function8;
                    enabled2 = enabled3;
                    colors3 = colors2;
                } else {
                    contentPadding2 = contentPadding3;
                    $dirty = $dirty2;
                    interactionSource2 = interactionSource;
                    modifier2 = modifier3;
                    function5 = function7;
                    function6 = function8;
                    enabled2 = enabled3;
                    colors3 = colors2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 64) != 0) {
                    function5 = function2;
                    colors3 = colors;
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    $dirty = $dirty2 & (-3670017);
                    function6 = function4;
                    enabled2 = z;
                    modifier2 = modifier;
                } else {
                    function5 = function2;
                    colors3 = colors;
                    contentPadding2 = contentPadding;
                    interactionSource2 = interactionSource;
                    $dirty = $dirty2;
                    function6 = function4;
                    enabled2 = z;
                    modifier2 = modifier;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1826340448, $dirty, -1, "androidx.compose.material3.DropdownMenuItem (AndroidMenu.android.kt:137)");
            }
            $composer2 = $composer3;
            MenuKt.DropdownMenuItemContent(text, onClick, modifier2, function5, function6, enabled2, colors3, contentPadding2, interactionSource2, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | ($dirty & 458752) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier2;
        final Function2<? super Composer, ? super Integer, Unit> function9 = function5;
        final Function2<? super Composer, ? super Integer, Unit> function10 = function6;
        final boolean z2 = enabled2;
        final MenuItemColors menuItemColors = colors3;
        final PaddingValues paddingValues = contentPadding2;
        final MutableInteractionSource mutableInteractionSource = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt.DropdownMenuItem.2
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
                AndroidMenu_androidKt.DropdownMenuItem(text, onClick, modifier4, function9, function10, z2, menuItemColors, paddingValues, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }
}
