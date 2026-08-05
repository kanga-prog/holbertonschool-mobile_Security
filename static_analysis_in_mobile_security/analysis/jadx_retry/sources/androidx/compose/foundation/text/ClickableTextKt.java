package androidx.compose.foundation.text;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ClickableText.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u008f\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00062\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014\u001ay\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00062\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001a!\u0010\u0017\u001a\u00020\f*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001c\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"ClickableText", "", "text", "Landroidx/compose/ui/text/AnnotatedString;", "onHover", "Lkotlin/Function1;", "", "modifier", "Landroidx/compose/ui/Modifier;", "style", "Landroidx/compose/ui/text/TextStyle;", "softWrap", "", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "maxLines", "onTextLayout", "Landroidx/compose/ui/text/TextLayoutResult;", "onClick", "ClickableText-03UYbkw", "(Landroidx/compose/ui/text/AnnotatedString;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;ZIILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "ClickableText-4YKlhWE", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;ZIILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "containsWithinBounds", "Landroidx/compose/ui/text/MultiParagraph;", "positionOffset", "Landroidx/compose/ui/geometry/Offset;", "containsWithinBounds-Uv8p0NA", "(Landroidx/compose/ui/text/MultiParagraph;J)Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ClickableTextKt {
    /* JADX WARN: Code duplicated, block: B:100:0x014f  */
    /* JADX WARN: Code duplicated, block: B:101:0x0153  */
    /* JADX WARN: Code duplicated, block: B:103:0x0157  */
    /* JADX WARN: Code duplicated, block: B:104:0x0160  */
    /* JADX WARN: Code duplicated, block: B:106:0x0164  */
    /* JADX WARN: Code duplicated, block: B:107:0x016a  */
    /* JADX WARN: Code duplicated, block: B:109:0x016e  */
    /* JADX WARN: Code duplicated, block: B:110:0x0174  */
    /* JADX WARN: Code duplicated, block: B:113:0x017c  */
    /* JADX WARN: Code duplicated, block: B:116:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:117:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:120:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:124:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:127:0x0245  */
    /* JADX WARN: Code duplicated, block: B:131:0x0253 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:135:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:139:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:141:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x0139 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:95:0x013b  */
    /* JADX WARN: Code duplicated, block: B:97:0x0142  */
    /* JADX WARN: Code duplicated, block: B:98:0x014b  */
    /* JADX INFO: renamed from: ClickableText-4YKlhWE, reason: not valid java name */
    public static final void m760ClickableText4YKlhWE(final AnnotatedString text, Modifier modifier, TextStyle style, boolean softWrap, int overflow, int maxLines, Function1<? super TextLayoutResult, Unit> function1, final Function1<? super Integer, Unit> onClick, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TextStyle textStyle;
        boolean z;
        int i2;
        int i3;
        TextStyle style2;
        boolean softWrap2;
        int overflow2;
        int maxLines2;
        final Function1<? super TextLayoutResult, Unit> function2;
        Object it$iv$iv;
        Object value$iv$iv;
        final MutableState layoutResult;
        boolean invalid$iv$iv;
        ClickableTextKt$ClickableText$pressIndicator$1$1 value$iv$iv2;
        boolean invalid$iv$iv2;
        Object value$iv$iv3;
        Modifier modifier3;
        Function1<? super TextLayoutResult, Unit> function3;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer3 = $composer.startRestartGroup(-246609449);
        ComposerKt.sourceInformation($composer3, "C(ClickableText)P(7,1,6,5,4:c#ui.text.style.TextOverflow!1,3)79@3637L52,80@3746L184,95@4151L76,88@3936L297:ClickableText.kt#423gt5");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(text) ? 4 : 2;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty |= 384;
            textStyle = style;
        } else if (($changed & 896) == 0) {
            textStyle = style;
            $dirty |= $composer3.changed(textStyle) ? 256 : 128;
        } else {
            textStyle = style;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            z = softWrap;
        } else if (($changed & 7168) == 0) {
            z = softWrap;
            $dirty |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = softWrap;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty |= 24576;
            i2 = overflow;
        } else if (($changed & 57344) == 0) {
            i2 = overflow;
            $dirty |= $composer3.changed(i2) ? 16384 : 8192;
        } else {
            i2 = overflow;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty |= $composer3.changed(maxLines) ? 131072 : 65536;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 1048576 : 524288;
        }
        if ((i & 128) == 0) {
            if ((29360128 & $changed) == 0) {
                i3 = $composer3.changedInstance(onClick) ? 8388608 : 4194304;
            }
            if ((23967451 & $dirty) == 4793490 || !$composer3.getSkipping()) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 == 0) {
                    style2 = textStyle;
                } else {
                    style2 = TextStyle.INSTANCE.getDefault();
                }
                if (i6 == 0) {
                    softWrap2 = z;
                } else {
                    softWrap2 = true;
                }
                if (i7 == 0) {
                    overflow2 = i2;
                } else {
                    overflow2 = TextOverflow.INSTANCE.m5181getClipgIe3tQ8();
                }
                if (i8 == 0) {
                    maxLines2 = maxLines;
                } else {
                    maxLines2 = Integer.MAX_VALUE;
                }
                if (i9 == 0) {
                    function2 = function1;
                } else {
                    function2 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                        }
                    };
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, $dirty, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:69)");
                }
                $composer3.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv = $composer3.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    $composer3.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                $composer3.endReplaceableGroup();
                layoutResult = (MutableState) value$iv$iv;
                Modifier.Companion companion = Modifier.INSTANCE;
                int i10 = (($dirty >> 18) & 112) | 6;
                $composer3.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer3.changed(layoutResult) | $composer3.changed(onClick);
                Object it$iv$iv2 = $composer3.rememberedValue();
                if (!invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(layoutResult, onClick, null);
                    $composer3.updateRememberedValue(value$iv$iv2);
                } else {
                    value$iv$iv2 = it$iv$iv2;
                }
                $composer3.endReplaceableGroup();
                Modifier pressIndicator = SuspendingPointerInputFilterKt.pointerInput(companion, onClick, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv2);
                Modifier modifierThen = modifier2.then(pressIndicator);
                int i11 = (($dirty >> 15) & 112) | 6;
                $composer3.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv2 = $composer3.changed(layoutResult) | $composer3.changed(function2);
                value$iv$iv3 = $composer3.rememberedValue();
                if (!invalid$iv$iv2 || value$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            layoutResult.setValue(it);
                            function2.invoke(it);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv$iv3);
                }
                $composer3.endReplaceableGroup();
                modifier3 = modifier2;
                function3 = function2;
                $composer2 = $composer3;
                BasicTextKt.m755BasicTextRWo7tUw(text, modifierThen, style2, (Function1) value$iv$iv3, overflow2, softWrap2, maxLines2, 0, null, null, $composer3, ($dirty & 14) | ($dirty & 896) | (57344 & $dirty) | (($dirty << 6) & 458752) | (($dirty << 3) & 3670016), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                maxLines2 = maxLines;
                function3 = function1;
                modifier3 = modifier2;
                style2 = textStyle;
                softWrap2 = z;
                overflow2 = i2;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final TextStyle textStyle2 = style2;
            final boolean z2 = softWrap2;
            final int i12 = overflow2;
            final int i13 = maxLines2;
            final Function1<? super TextLayoutResult, Unit> function4 = function3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
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
                    ClickableTextKt.m760ClickableText4YKlhWE(text, modifier4, textStyle2, z2, i12, i13, function4, onClick, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i3 = 12582912;
        $dirty |= i3;
        if ((23967451 & $dirty) == 4793490) {
            if (i4 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i5 == 0) {
                style2 = textStyle;
            } else {
                style2 = TextStyle.INSTANCE.getDefault();
            }
            if (i6 == 0) {
                softWrap2 = z;
            } else {
                softWrap2 = true;
            }
            if (i7 == 0) {
                overflow2 = i2;
            } else {
                overflow2 = TextOverflow.INSTANCE.m5181getClipgIe3tQ8();
            }
            if (i8 == 0) {
                maxLines2 = maxLines;
            } else {
                maxLines2 = Integer.MAX_VALUE;
            }
            if (i9 == 0) {
                function2 = function1;
            } else {
                function2 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                    }
                };
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-246609449, $dirty, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:69)");
            }
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer3.endReplaceableGroup();
            layoutResult = (MutableState) value$iv$iv;
            Modifier.Companion companion2 = Modifier.INSTANCE;
            int i14 = (($dirty >> 18) & 112) | 6;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(layoutResult) | $composer3.changed(onClick);
            Object it$iv$iv3 = $composer3.rememberedValue();
            if (invalid$iv$iv) {
                value$iv$iv2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(layoutResult, onClick, null);
                $composer3.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(layoutResult, onClick, null);
                $composer3.updateRememberedValue(value$iv$iv2);
            }
            $composer3.endReplaceableGroup();
            Modifier pressIndicator2 = SuspendingPointerInputFilterKt.pointerInput(companion2, onClick, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv2);
            Modifier modifierThen2 = modifier2.then(pressIndicator2);
            int i15 = (($dirty >> 15) & 112) | 6;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer3.changed(layoutResult) | $composer3.changed(function2);
            value$iv$iv3 = $composer3.rememberedValue();
            if (!invalid$iv$iv2) {
            }
            value$iv$iv3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                    invoke2(textLayoutResult);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TextLayoutResult it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    layoutResult.setValue(it);
                    function2.invoke(it);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv3);
            $composer3.endReplaceableGroup();
            modifier3 = modifier2;
            function3 = function2;
            $composer2 = $composer3;
            BasicTextKt.m755BasicTextRWo7tUw(text, modifierThen2, style2, (Function1) value$iv$iv3, overflow2, softWrap2, maxLines2, 0, null, null, $composer3, ($dirty & 14) | ($dirty & 896) | (57344 & $dirty) | (($dirty << 6) & 458752) | (($dirty << 3) & 3670016), 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            if (i4 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (i5 == 0) {
                style2 = textStyle;
            } else {
                style2 = TextStyle.INSTANCE.getDefault();
            }
            if (i6 == 0) {
                softWrap2 = z;
            } else {
                softWrap2 = true;
            }
            if (i7 == 0) {
                overflow2 = i2;
            } else {
                overflow2 = TextOverflow.INSTANCE.m5181getClipgIe3tQ8();
            }
            if (i8 == 0) {
                maxLines2 = maxLines;
            } else {
                maxLines2 = Integer.MAX_VALUE;
            }
            if (i9 == 0) {
                function2 = function1;
            } else {
                function2 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                    }
                };
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-246609449, $dirty, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:69)");
            }
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer3.endReplaceableGroup();
            layoutResult = (MutableState) value$iv$iv;
            Modifier.Companion companion3 = Modifier.INSTANCE;
            int i16 = (($dirty >> 18) & 112) | 6;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(layoutResult) | $composer3.changed(onClick);
            Object it$iv$iv4 = $composer3.rememberedValue();
            if (invalid$iv$iv) {
                value$iv$iv2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(layoutResult, onClick, null);
                $composer3.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(layoutResult, onClick, null);
                $composer3.updateRememberedValue(value$iv$iv2);
            }
            $composer3.endReplaceableGroup();
            Modifier pressIndicator3 = SuspendingPointerInputFilterKt.pointerInput(companion3, onClick, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv2);
            Modifier modifierThen3 = modifier2.then(pressIndicator3);
            int i17 = (($dirty >> 15) & 112) | 6;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer3.changed(layoutResult) | $composer3.changed(function2);
            value$iv$iv3 = $composer3.rememberedValue();
            if (!invalid$iv$iv2) {
            }
            value$iv$iv3 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                    invoke2(textLayoutResult);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TextLayoutResult it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    layoutResult.setValue(it);
                    function2.invoke(it);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv3);
            $composer3.endReplaceableGroup();
            modifier3 = modifier2;
            function3 = function2;
            $composer2 = $composer3;
            BasicTextKt.m755BasicTextRWo7tUw(text, modifierThen3, style2, (Function1) value$iv$iv3, overflow2, softWrap2, maxLines2, 0, null, null, $composer3, ($dirty & 14) | ($dirty & 896) | (57344 & $dirty) | (($dirty << 6) & 458752) | (($dirty << 3) & 3670016), 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final TextStyle textStyle3 = style2;
        final boolean z3 = softWrap2;
        final int i18 = overflow2;
        final int i19 = maxLines2;
        final Function1<? super TextLayoutResult, Unit> function5 = function3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$3
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
                ClickableTextKt.m760ClickableText4YKlhWE(text, modifier5, textStyle3, z3, i18, i19, function5, onClick, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:104:0x015e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:105:0x0160  */
    /* JADX WARN: Code duplicated, block: B:106:0x0166  */
    /* JADX WARN: Code duplicated, block: B:108:0x016a  */
    /* JADX WARN: Code duplicated, block: B:109:0x0173  */
    /* JADX WARN: Code duplicated, block: B:111:0x0177  */
    /* JADX WARN: Code duplicated, block: B:112:0x017b  */
    /* JADX WARN: Code duplicated, block: B:114:0x017f  */
    /* JADX WARN: Code duplicated, block: B:115:0x0188  */
    /* JADX WARN: Code duplicated, block: B:117:0x018c  */
    /* JADX WARN: Code duplicated, block: B:118:0x0192  */
    /* JADX WARN: Code duplicated, block: B:120:0x0196  */
    /* JADX WARN: Code duplicated, block: B:121:0x019c  */
    /* JADX WARN: Code duplicated, block: B:124:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:127:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:128:0x01df  */
    /* JADX WARN: Code duplicated, block: B:131:0x021d  */
    /* JADX WARN: Code duplicated, block: B:132:0x023a  */
    /* JADX WARN: Code duplicated, block: B:135:0x0295  */
    /* JADX WARN: Code duplicated, block: B:139:0x02a3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:143:0x0307  */
    /* JADX WARN: Code duplicated, block: B:147:0x0311  */
    /* JADX WARN: Code duplicated, block: B:149:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: ClickableText-03UYbkw, reason: not valid java name */
    public static final void m759ClickableText03UYbkw(final AnnotatedString text, final Function1<? super Integer, Unit> onHover, Modifier modifier, TextStyle style, boolean softWrap, int overflow, int maxLines, Function1<? super TextLayoutResult, Unit> function1, final Function1<? super Integer, Unit> onClick, Composer $composer, final int $changed, final int i) {
        TextStyle textStyle;
        boolean z;
        int i2;
        int i3;
        int $dirty;
        Modifier modifier2;
        TextStyle style2;
        boolean softWrap2;
        int overflow2;
        int maxLines2;
        final Function1<? super TextLayoutResult, Unit> function2;
        Object it$iv$iv;
        Object value$iv$iv;
        Object value$iv$iv$iv;
        boolean invalid$iv$iv;
        Object value$iv$iv2;
        Modifier modifier3;
        Function1<? super TextLayoutResult, Unit> function3;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(onHover, "onHover");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer3 = $composer.startRestartGroup(1020774372);
        ComposerKt.sourceInformation($composer3, "C(ClickableText)P(8,3,1,7,6,5:c#ui.text.style.TextOverflow!1,4)153@6860L52,154@6938L24,183@7878L76,176@7657L303:ClickableText.kt#423gt5");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changed(text) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changedInstance(onHover) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            textStyle = style;
        } else if (($changed & 7168) == 0) {
            textStyle = style;
            $dirty2 |= $composer3.changed(textStyle) ? 2048 : 1024;
        } else {
            textStyle = style;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty2 |= 24576;
            z = softWrap;
        } else if (($changed & 57344) == 0) {
            z = softWrap;
            $dirty2 |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = softWrap;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i2 = overflow;
        } else if (($changed & 458752) == 0) {
            i2 = overflow;
            $dirty2 |= $composer3.changed(i2) ? 131072 : 65536;
        } else {
            i2 = overflow;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty2 |= $composer3.changed(maxLines) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? 8388608 : 4194304;
        }
        if ((i & 256) == 0) {
            if ((234881024 & $changed) == 0) {
                i3 = $composer3.changedInstance(onClick) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
            }
            $dirty = $dirty2;
            if ((191739611 & $dirty) == 38347922 || !$composer3.getSkipping()) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i5 != 0) {
                    style2 = TextStyle.INSTANCE.getDefault();
                } else {
                    style2 = textStyle;
                }
                if (i6 != 0) {
                    softWrap2 = true;
                } else {
                    softWrap2 = z;
                }
                if (i7 != 0) {
                    overflow2 = TextOverflow.INSTANCE.m5181getClipgIe3tQ8();
                } else {
                    overflow2 = i2;
                }
                if (i8 != 0) {
                    maxLines2 = Integer.MAX_VALUE;
                } else {
                    maxLines2 = maxLines;
                }
                if (i9 != 0) {
                    function2 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                        }
                    };
                } else {
                    function2 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1020774372, $dirty, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:142)");
                }
                $composer3.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv = $composer3.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    $composer3.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                $composer3.endReplaceableGroup();
                final MutableState layoutResult = (MutableState) value$iv$iv;
                $composer3.startReplaceableGroup(773894976);
                ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
                $composer3.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                value$iv$iv$iv = $composer3.rememberedValue();
                if (value$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                    $composer3.updateRememberedValue(value$iv$iv$iv);
                }
                $composer3.endReplaceableGroup();
                CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
                CoroutineScope coroutineScope = wrapper$iv.getCoroutineScope();
                $composer3.endReplaceableGroup();
                Modifier pointerInputModifier = SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, onClick, onHover, new ClickableTextKt$ClickableText$pointerInputModifier$1(coroutineScope, onHover, layoutResult, onClick, null));
                Modifier modifierThen = modifier2.then(pointerInputModifier);
                int i10 = (($dirty >> 18) & 112) | 6;
                $composer3.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv = $composer3.changed(layoutResult) | $composer3.changed(function2);
                value$iv$iv2 = $composer3.rememberedValue();
                if (!invalid$iv$iv || value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                            invoke2(textLayoutResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextLayoutResult it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            layoutResult.setValue(it);
                            function2.invoke(it);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv$iv2);
                }
                $composer3.endReplaceableGroup();
                modifier3 = modifier2;
                function3 = function2;
                $composer2 = $composer3;
                BasicTextKt.m755BasicTextRWo7tUw(text, modifierThen, style2, (Function1) value$iv$iv2, overflow2, softWrap2, maxLines2, 0, null, null, $composer3, ($dirty & 14) | (($dirty >> 3) & 896) | (($dirty >> 3) & 57344) | (($dirty << 3) & 458752) | ($dirty & 3670016), 896);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier3 = modifier;
                maxLines2 = maxLines;
                function3 = function1;
                style2 = textStyle;
                overflow2 = i2;
                softWrap2 = z;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final TextStyle textStyle2 = style2;
            final boolean z2 = softWrap2;
            final int i11 = overflow2;
            final int i12 = maxLines2;
            final Function1<? super TextLayoutResult, Unit> function4 = function3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$6
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

                public final void invoke(Composer composer, int i13) {
                    ClickableTextKt.m759ClickableText03UYbkw(text, onHover, modifier4, textStyle2, z2, i11, i12, function4, onClick, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i3 = 100663296;
        $dirty2 |= i3;
        $dirty = $dirty2;
        if ((191739611 & $dirty) == 38347922) {
            if (i4 != 0) {
                modifier2 = Modifier.INSTANCE;
            } else {
                modifier2 = modifier;
            }
            if (i5 != 0) {
                style2 = TextStyle.INSTANCE.getDefault();
            } else {
                style2 = textStyle;
            }
            if (i6 != 0) {
                softWrap2 = true;
            } else {
                softWrap2 = z;
            }
            if (i7 != 0) {
                overflow2 = TextOverflow.INSTANCE.m5181getClipgIe3tQ8();
            } else {
                overflow2 = i2;
            }
            if (i8 != 0) {
                maxLines2 = Integer.MAX_VALUE;
            } else {
                maxLines2 = maxLines;
            }
            if (i9 != 0) {
                function2 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                    }
                };
            } else {
                function2 = function1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1020774372, $dirty, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:142)");
            }
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer3.endReplaceableGroup();
            final MutableState<TextLayoutResult> layoutResult2 = (MutableState) value$iv$iv;
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            value$iv$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv$iv);
            }
            $composer3.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv2 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            CoroutineScope coroutineScope2 = wrapper$iv2.getCoroutineScope();
            $composer3.endReplaceableGroup();
            Modifier pointerInputModifier2 = SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, onClick, onHover, new ClickableTextKt$ClickableText$pointerInputModifier$1(coroutineScope2, onHover, layoutResult2, onClick, null));
            Modifier modifierThen2 = modifier2.then(pointerInputModifier2);
            int i13 = (($dirty >> 18) & 112) | 6;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(layoutResult2) | $composer3.changed(function2);
            value$iv$iv2 = $composer3.rememberedValue();
            if (!invalid$iv$iv) {
            }
            value$iv$iv2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                    invoke2(textLayoutResult);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TextLayoutResult it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    layoutResult2.setValue(it);
                    function2.invoke(it);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv2);
            $composer3.endReplaceableGroup();
            modifier3 = modifier2;
            function3 = function2;
            $composer2 = $composer3;
            BasicTextKt.m755BasicTextRWo7tUw(text, modifierThen2, style2, (Function1) value$iv$iv2, overflow2, softWrap2, maxLines2, 0, null, null, $composer3, ($dirty & 14) | (($dirty >> 3) & 896) | (($dirty >> 3) & 57344) | (($dirty << 3) & 458752) | ($dirty & 3670016), 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            if (i4 != 0) {
                modifier2 = Modifier.INSTANCE;
            } else {
                modifier2 = modifier;
            }
            if (i5 != 0) {
                style2 = TextStyle.INSTANCE.getDefault();
            } else {
                style2 = textStyle;
            }
            if (i6 != 0) {
                softWrap2 = true;
            } else {
                softWrap2 = z;
            }
            if (i7 != 0) {
                overflow2 = TextOverflow.INSTANCE.m5181getClipgIe3tQ8();
            } else {
                overflow2 = i2;
            }
            if (i8 != 0) {
                maxLines2 = Integer.MAX_VALUE;
            } else {
                maxLines2 = maxLines;
            }
            if (i9 != 0) {
                function2 = new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$4
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                    }
                };
            } else {
                function2 = function1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1020774372, $dirty, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:142)");
            }
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer3.endReplaceableGroup();
            final MutableState<TextLayoutResult> layoutResult3 = (MutableState) value$iv$iv;
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)488@20446L144:Effects.kt#9igjgp");
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            value$iv$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv$iv);
            }
            $composer3.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv3 = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            CoroutineScope coroutineScope3 = wrapper$iv3.getCoroutineScope();
            $composer3.endReplaceableGroup();
            Modifier pointerInputModifier3 = SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, onClick, onHover, new ClickableTextKt$ClickableText$pointerInputModifier$1(coroutineScope3, onHover, layoutResult3, onClick, null));
            Modifier modifierThen3 = modifier2.then(pointerInputModifier3);
            int i14 = (($dirty >> 18) & 112) | 6;
            $composer3.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer3, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv = $composer3.changed(layoutResult3) | $composer3.changed(function2);
            value$iv$iv2 = $composer3.rememberedValue();
            if (!invalid$iv$iv) {
            }
            value$iv$iv2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$5$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                    invoke2(textLayoutResult);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TextLayoutResult it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    layoutResult3.setValue(it);
                    function2.invoke(it);
                }
            };
            $composer3.updateRememberedValue(value$iv$iv2);
            $composer3.endReplaceableGroup();
            modifier3 = modifier2;
            function3 = function2;
            $composer2 = $composer3;
            BasicTextKt.m755BasicTextRWo7tUw(text, modifierThen3, style2, (Function1) value$iv$iv2, overflow2, softWrap2, maxLines2, 0, null, null, $composer3, ($dirty & 14) | (($dirty >> 3) & 896) | (($dirty >> 3) & 57344) | (($dirty << 3) & 458752) | ($dirty & 3670016), 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final TextStyle textStyle3 = style2;
        final boolean z3 = softWrap2;
        final int i15 = overflow2;
        final int i16 = maxLines2;
        final Function1<? super TextLayoutResult, Unit> function5 = function3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ClickableTextKt$ClickableText$6
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
                ClickableTextKt.m759ClickableText03UYbkw(text, onHover, modifier5, textStyle3, z3, i15, i16, function5, onClick, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer ClickableText_03UYbkw$getOffset(MutableState<TextLayoutResult> mutableState, long positionOffset) {
        MultiParagraph it;
        TextLayoutResult value = mutableState.getValue();
        if (value == null || (it = value.getMultiParagraph()) == null) {
            return null;
        }
        if (!m761containsWithinBoundsUv8p0NA(it, positionOffset)) {
            it = null;
        }
        if (it != null) {
            return Integer.valueOf(it.m4656getOffsetForPositionk4lQ0M(positionOffset));
        }
        return null;
    }

    /* JADX INFO: renamed from: containsWithinBounds-Uv8p0NA, reason: not valid java name */
    private static final boolean m761containsWithinBoundsUv8p0NA(MultiParagraph $this$containsWithinBounds_u2dUv8p0NA, long positionOffset) {
        float x = Offset.m2721component1impl(positionOffset);
        float y = Offset.m2722component2impl(positionOffset);
        return x > 0.0f && y >= 0.0f && x <= $this$containsWithinBounds_u2dUv8p0NA.getWidth() && y <= $this$containsWithinBounds_u2dUv8p0NA.getHeight();
    }
}
