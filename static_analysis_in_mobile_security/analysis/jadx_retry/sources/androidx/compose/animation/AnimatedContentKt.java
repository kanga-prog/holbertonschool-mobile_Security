package androidx.compose.animation;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.VisibilityThresholdsKt;
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
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnimatedContent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a´\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001f\b\u0002\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000721\u0010\u0013\u001a-\u0012\u0004\u0012\u00020\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u0017\u001aS\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2>\b\u0002\u0010\u001c\u001a8\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0 0\u0014ø\u0001\u0000\u001a¬\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020!2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001f\b\u0002\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000721\u0010\u0013\u001a-\u0012\u0004\u0012\u00020\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\"\u001a\u0015\u0010#\u001a\u00020\t*\u00020$2\u0006\u0010%\u001a\u00020&H\u0086\u0004\u001a\u0015\u0010'\u001a\u00020\t*\u00020$2\u0006\u0010%\u001a\u00020&H\u0087\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006("}, d2 = {"AnimatedContent", "", "S", "targetState", "modifier", "Landroidx/compose/ui/Modifier;", "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/AnimatedContentTransitionScope;", "Landroidx/compose/animation/ContentTransform;", "Lkotlin/ExtensionFunctionType;", "contentAlignment", "Landroidx/compose/ui/Alignment;", "label", "", "contentKey", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "", "content", "Lkotlin/Function2;", "Landroidx/compose/animation/AnimatedContentScope;", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "SizeTransform", "Landroidx/compose/animation/SizeTransform;", "clip", "", "sizeAnimationSpec", "Landroidx/compose/ui/unit/IntSize;", "initialSize", "targetSize", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Landroidx/compose/animation/core/Transition;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "togetherWith", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "with", "animation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AnimatedContentKt {
    /* JADX WARN: Code duplicated, block: B:101:0x0186  */
    /* JADX WARN: Code duplicated, block: B:106:0x0191  */
    /* JADX WARN: Code duplicated, block: B:108:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x0108 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:84:0x010a  */
    /* JADX WARN: Code duplicated, block: B:85:0x0111  */
    /* JADX WARN: Code duplicated, block: B:87:0x0115  */
    /* JADX WARN: Code duplicated, block: B:88:0x011c  */
    /* JADX WARN: Code duplicated, block: B:90:0x0120  */
    /* JADX WARN: Code duplicated, block: B:91:0x0129  */
    /* JADX WARN: Code duplicated, block: B:93:0x012d  */
    /* JADX WARN: Code duplicated, block: B:95:0x0132  */
    /* JADX WARN: Code duplicated, block: B:98:0x013d  */
    public static final <S> void AnimatedContent(final S s, Modifier modifier, Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function1, Alignment contentAlignment, String label, Function1<? super S, ? extends Object> function2, final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) throws Throwable {
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function3;
        Alignment alignment;
        String label2;
        Function1<? super S, ? extends Object> function4;
        int i2;
        Modifier modifier2;
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function5;
        Alignment contentAlignment2;
        Function1<? super S, ? extends Object> function6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(2132720749);
        ComposerKt.sourceInformation($composer2, "C(AnimatedContent)P(5,4,6,1,3,2)140@7413L58,141@7487L136:AnimatedContent.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(s) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            function3 = function1;
        } else if (($changed & 896) == 0) {
            function3 = function1;
            $dirty |= $composer2.changedInstance(function3) ? 256 : 128;
        } else {
            function3 = function1;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            alignment = contentAlignment;
        } else if (($changed & 7168) == 0) {
            alignment = contentAlignment;
            $dirty |= $composer2.changed(alignment) ? 2048 : 1024;
        } else {
            alignment = contentAlignment;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            label2 = label;
        } else if (($changed & 57344) == 0) {
            label2 = label;
            $dirty |= $composer2.changed(label2) ? 16384 : 8192;
        } else {
            label2 = label;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function4 = function2;
        } else if (($changed & 458752) == 0) {
            function4 = function2;
            $dirty |= $composer2.changedInstance(function4) ? 131072 : 65536;
        } else {
            function4 = function2;
        }
        if ((i & 64) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(content) ? 1048576 : 524288;
            }
            if (($dirty & 2995931) == 599186 || !$composer2.getSkipping()) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    function5 = new Function1<AnimatedContentTransitionScope<S>, ContentTransform>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.1
                        @Override // kotlin.jvm.functions.Function1
                        public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
                            Intrinsics.checkNotNullParameter(animatedContentTransitionScope, "$this$null");
                            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m43scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
                        }
                    };
                } else {
                    function5 = function3;
                }
                if (i5 != 0) {
                    contentAlignment2 = Alignment.INSTANCE.getTopStart();
                } else {
                    contentAlignment2 = alignment;
                }
                if (i6 != 0) {
                    label2 = "AnimatedContent";
                }
                if (i7 != 0) {
                    function4 = new Function1<S, S>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.2
                        @Override // kotlin.jvm.functions.Function1
                        public final S invoke(S s2) {
                            return s2;
                        }
                    };
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2132720749, $dirty, -1, "androidx.compose.animation.AnimatedContent (AnimatedContent.kt:127)");
                }
                Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(s, label2, $composer2, ($dirty & 8) | ($dirty & 14) | (($dirty >> 9) & 112), 0);
                AnimatedContent(transition, modifier2, function5, contentAlignment2, function4, content, $composer2, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function6 = function4;
            } else {
                $composer2.skipToGroupEnd();
                modifier2 = modifier;
                function5 = function3;
                contentAlignment2 = alignment;
                function6 = function4;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier3 = modifier2;
            final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function7 = function5;
            final Alignment alignment2 = contentAlignment2;
            final String str = label2;
            final Function1<? super S, ? extends Object> function8 = function6;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i8) throws Throwable {
                    AnimatedContentKt.AnimatedContent(s, modifier3, function7, alignment2, str, function8, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty |= i2;
        if (($dirty & 2995931) == 599186) {
            if (i3 != 0) {
                modifier2 = Modifier.INSTANCE;
            } else {
                modifier2 = modifier;
            }
            if (i4 != 0) {
                function5 = new Function1<AnimatedContentTransitionScope<S>, ContentTransform>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.1
                    @Override // kotlin.jvm.functions.Function1
                    public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
                        Intrinsics.checkNotNullParameter(animatedContentTransitionScope, "$this$null");
                        return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m43scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
                    }
                };
            } else {
                function5 = function3;
            }
            if (i5 != 0) {
                contentAlignment2 = Alignment.INSTANCE.getTopStart();
            } else {
                contentAlignment2 = alignment;
            }
            if (i6 != 0) {
                label2 = "AnimatedContent";
            }
            if (i7 != 0) {
                function4 = new Function1<S, S>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.2
                    @Override // kotlin.jvm.functions.Function1
                    public final S invoke(S s2) {
                        return s2;
                    }
                };
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2132720749, $dirty, -1, "androidx.compose.animation.AnimatedContent (AnimatedContent.kt:127)");
            }
            Transition transition2 = androidx.compose.animation.core.TransitionKt.updateTransition(s, label2, $composer2, ($dirty & 8) | ($dirty & 14) | (($dirty >> 9) & 112), 0);
            AnimatedContent(transition2, modifier2, function5, contentAlignment2, function4, content, $composer2, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function6 = function4;
        } else {
            if (i3 != 0) {
                modifier2 = Modifier.INSTANCE;
            } else {
                modifier2 = modifier;
            }
            if (i4 != 0) {
                function5 = new Function1<AnimatedContentTransitionScope<S>, ContentTransform>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.1
                    @Override // kotlin.jvm.functions.Function1
                    public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
                        Intrinsics.checkNotNullParameter(animatedContentTransitionScope, "$this$null");
                        return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m43scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
                    }
                };
            } else {
                function5 = function3;
            }
            if (i5 != 0) {
                contentAlignment2 = Alignment.INSTANCE.getTopStart();
            } else {
                contentAlignment2 = alignment;
            }
            if (i6 != 0) {
                label2 = "AnimatedContent";
            }
            if (i7 != 0) {
                function4 = new Function1<S, S>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.2
                    @Override // kotlin.jvm.functions.Function1
                    public final S invoke(S s2) {
                        return s2;
                    }
                };
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2132720749, $dirty, -1, "androidx.compose.animation.AnimatedContent (AnimatedContent.kt:127)");
            }
            Transition transition3 = androidx.compose.animation.core.TransitionKt.updateTransition(s, label2, $composer2, ($dirty & 8) | ($dirty & 14) | (($dirty >> 9) & 112), 0);
            AnimatedContent(transition3, modifier2, function5, contentAlignment2, function4, content, $composer2, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function6 = function4;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier2;
        final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function9 = function5;
        final Alignment alignment3 = contentAlignment2;
        final String str2 = label2;
        final Function1<? super S, ? extends Object> function10 = function6;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i8) throws Throwable {
                AnimatedContentKt.AnimatedContent(s, modifier4, function9, alignment3, str2, function10, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    public static /* synthetic */ SizeTransform SizeTransform$default(boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            function2 = new Function2<IntSize, IntSize, SpringSpec<IntSize>>() { // from class: androidx.compose.animation.AnimatedContentKt.SizeTransform.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ SpringSpec<IntSize> invoke(IntSize intSize, IntSize intSize2) {
                    return m10invokeTemP2vQ(intSize.getPackedValue(), intSize2.getPackedValue());
                }

                /* JADX INFO: renamed from: invoke-TemP2vQ, reason: not valid java name */
                public final SpringSpec<IntSize> m10invokeTemP2vQ(long j, long j2) {
                    return AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5426boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
                }
            };
        }
        return SizeTransform(z, function2);
    }

    public static final SizeTransform SizeTransform(boolean clip, Function2<? super IntSize, ? super IntSize, ? extends FiniteAnimationSpec<IntSize>> sizeAnimationSpec) {
        Intrinsics.checkNotNullParameter(sizeAnimationSpec, "sizeAnimationSpec");
        return new SizeTransformImpl(clip, sizeAnimationSpec);
    }

    public static final ContentTransform togetherWith(EnterTransition $this$togetherWith, ExitTransition exit) {
        Intrinsics.checkNotNullParameter($this$togetherWith, "<this>");
        Intrinsics.checkNotNullParameter(exit, "exit");
        return new ContentTransform($this$togetherWith, exit, 0.0f, null, 12, null);
    }

    @Deprecated(message = "Infix fun EnterTransition.with(ExitTransition) has been renamed to togetherWith", replaceWith = @ReplaceWith(expression = "togetherWith(exit)", imports = {}))
    public static final ContentTransform with(EnterTransition $this$with, ExitTransition exit) {
        Intrinsics.checkNotNullParameter($this$with, "<this>");
        Intrinsics.checkNotNullParameter(exit, "exit");
        return new ContentTransform($this$with, exit, 0.0f, null, 12, null);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01db  */
    /* JADX WARN: Code duplicated, block: B:104:0x01e8 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:108:0x020a  */
    /* JADX WARN: Code duplicated, block: B:111:0x0222  */
    /* JADX WARN: Code duplicated, block: B:113:0x0229  */
    /* JADX WARN: Code duplicated, block: B:115:0x0238  */
    /* JADX WARN: Code duplicated, block: B:118:0x0249  */
    /* JADX WARN: Code duplicated, block: B:120:0x0253  */
    /* JADX WARN: Code duplicated, block: B:124:0x026a  */
    /* JADX WARN: Code duplicated, block: B:129:0x0283  */
    /* JADX WARN: Code duplicated, block: B:132:0x02a6 A[LOOP:0: B:127:0x027d->B:132:0x02a6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:136:0x02b7  */
    /* JADX WARN: Code duplicated, block: B:137:0x02bf  */
    /* JADX WARN: Code duplicated, block: B:140:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:144:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:146:0x02f7 A[LOOP:2: B:145:0x02f5->B:146:0x02f7, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:150:0x0375  */
    /* JADX WARN: Code duplicated, block: B:154:0x0380  */
    /* JADX WARN: Code duplicated, block: B:157:0x03c0  */
    /* JADX WARN: Code duplicated, block: B:158:0x03ce  */
    /* JADX WARN: Code duplicated, block: B:161:0x040a  */
    /* JADX WARN: Code duplicated, block: B:164:0x0416  */
    /* JADX WARN: Code duplicated, block: B:165:0x041a  */
    /* JADX WARN: Code duplicated, block: B:168:0x044a  */
    /* JADX WARN: Code duplicated, block: B:172:0x0460 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:177:0x04bb  */
    /* JADX WARN: Code duplicated, block: B:180:0x04e6  */
    /* JADX WARN: Code duplicated, block: B:184:0x0517  */
    /* JADX WARN: Code duplicated, block: B:188:0x0521  */
    /* JADX WARN: Code duplicated, block: B:190:0x02ae A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:191:0x02b3 A[EDGE_INSN: B:191:0x02b3->B:134:0x02b3 BREAK  A[LOOP:0: B:127:0x027d->B:132:0x02a6], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:194:0x04ed A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:196:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x00e6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:76:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:78:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:80:0x0101  */
    /* JADX WARN: Code duplicated, block: B:83:0x010d  */
    /* JADX WARN: Code duplicated, block: B:86:0x014b  */
    /* JADX WARN: Code duplicated, block: B:90:0x015a  */
    /* JADX WARN: Code duplicated, block: B:93:0x018f  */
    /* JADX WARN: Code duplicated, block: B:97:0x019d  */
    public static final <S> void AnimatedContent(final Transition<S> transition, Modifier modifier, Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function1, Alignment contentAlignment, Function1<? super S, ? extends Object> function2, final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) throws Throwable {
        Modifier modifier2;
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function3;
        Alignment contentAlignment2;
        Function1<? super S, ? extends Object> function4;
        int i2;
        int $dirty;
        Modifier modifier3;
        LayoutDirection layoutDirection;
        boolean invalid$iv$iv;
        Object value$iv$iv;
        AnimatedContentTransitionScopeImpl rootScope;
        boolean invalid$iv$iv2;
        Object value$iv$iv2;
        final SnapshotStateList currentlyVisible;
        boolean invalid$iv$iv3;
        Object value$iv$iv3;
        Map contentMap;
        SnapshotStateList $this$fastForEach$iv;
        int size;
        int index$iv;
        Map contentMap2;
        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function5;
        Alignment contentAlignment3;
        AnimatedContentTransitionScopeImpl rootScope2;
        boolean invalid$iv$iv4;
        Object value$iv$iv4;
        Object it$iv$iv;
        Object value$iv$iv5;
        int compositeKeyHash$iv;
        Function0<ComposeUiNode> constructor;
        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf;
        Composer $this$Layout_u24lambda_u240$iv;
        int i3;
        SnapshotStateList $this$forEach$iv;
        Function2 function6;
        SnapshotStateList $this$indexOfFirst$iv;
        int $i$f$indexOfFirst;
        int index$iv2;
        Iterator it;
        int id;
        List $this$indexOfFirst$iv2;
        int $i$f$indexOfFirst2;
        Object objInvoke;
        Object it2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-114689412);
        ComposerKt.sourceInformation($composer2, "C(AnimatedContent)P(3,4,1,2)692@32995L7,693@33023L106,698@33218L51,699@33291L62,786@37539L58,787@37631L45,797@37946L52,788@37681L323:AnimatedContent.kt#xbi5r1");
        int $dirty2 = $changed;
        if ((i & Integer.MIN_VALUE) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(transition) ? 4 : 2;
        }
        int i4 = i & 1;
        if (i4 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty2 |= 384;
            function3 = function1;
        } else if (($changed & 896) == 0) {
            function3 = function1;
            $dirty2 |= $composer2.changedInstance(function3) ? 256 : 128;
        } else {
            function3 = function1;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty2 |= 3072;
            contentAlignment2 = contentAlignment;
        } else if (($changed & 7168) == 0) {
            contentAlignment2 = contentAlignment;
            $dirty2 |= $composer2.changed(contentAlignment2) ? 2048 : 1024;
        } else {
            contentAlignment2 = contentAlignment;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty2 |= 24576;
            function4 = function2;
        } else if ((57344 & $changed) == 0) {
            function4 = function2;
            $dirty2 |= $composer2.changedInstance(function4) ? 16384 : 8192;
        } else {
            function4 = function2;
        }
        if ((i & 16) == 0) {
            if ((458752 & $changed) == 0) {
                i2 = $composer2.changedInstance(content) ? 131072 : 65536;
            }
            $dirty = $dirty2;
            if ((374491 & $dirty) == 74898 || !$composer2.getSkipping()) {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    function3 = new Function1<AnimatedContentTransitionScope<S>, ContentTransform>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.4
                        @Override // kotlin.jvm.functions.Function1
                        public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
                            Intrinsics.checkNotNullParameter(animatedContentTransitionScope, "$this$null");
                            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m43scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
                        }
                    };
                }
                if (i6 != 0) {
                    contentAlignment2 = Alignment.INSTANCE.getTopStart();
                }
                if (i7 != 0) {
                    function4 = new Function1<S, S>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.5
                        @Override // kotlin.jvm.functions.Function1
                        public final S invoke(S s) {
                            return s;
                        }
                    };
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-114689412, $dirty, -1, "androidx.compose.animation.AnimatedContent (AnimatedContent.kt:681)");
                }
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                layoutDirection = (LayoutDirection) objConsume;
                int i8 = $dirty & 14;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv = $composer2.changed(transition);
                Object it$iv$iv2 = $composer2.rememberedValue();
                if (!invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = new AnimatedContentTransitionScopeImpl(transition, contentAlignment2, layoutDirection);
                    $composer2.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv2;
                }
                $composer2.endReplaceableGroup();
                rootScope = (AnimatedContentTransitionScopeImpl) value$iv$iv;
                int i9 = $dirty & 14;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv2 = $composer2.changed(transition);
                value$iv$iv2 = $composer2.rememberedValue();
                if (invalid$iv$iv2 || value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = SnapshotStateKt.mutableStateListOf(transition.getCurrentState());
                    $composer2.updateRememberedValue(value$iv$iv2);
                }
                $composer2.endReplaceableGroup();
                currentlyVisible = (SnapshotStateList) value$iv$iv2;
                int i10 = $dirty & 14;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                invalid$iv$iv3 = $composer2.changed(transition);
                Object it$iv$iv3 = $composer2.rememberedValue();
                if (!invalid$iv$iv3 || it$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv3 = new LinkedHashMap();
                    $composer2.updateRememberedValue(value$iv$iv3);
                } else {
                    value$iv$iv3 = it$iv$iv3;
                }
                $composer2.endReplaceableGroup();
                contentMap = (Map) value$iv$iv3;
                if (!currentlyVisible.contains(transition.getCurrentState())) {
                    currentlyVisible.clear();
                    currentlyVisible.add(transition.getCurrentState());
                }
                if (Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                    if (currentlyVisible.size() == 1 || !Intrinsics.areEqual(currentlyVisible.get(0), transition.getCurrentState())) {
                        currentlyVisible.clear();
                        currentlyVisible.add(transition.getCurrentState());
                    }
                    if (contentMap.size() == 1 || contentMap.containsKey(transition.getCurrentState())) {
                        contentMap.clear();
                    }
                    rootScope.setContentAlignment$animation_release(contentAlignment2);
                    rootScope.setLayoutDirection$animation_release(layoutDirection);
                }
                if (!Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState()) && !currentlyVisible.contains(transition.getTargetState())) {
                    $this$indexOfFirst$iv = currentlyVisible;
                    $i$f$indexOfFirst = 0;
                    index$iv2 = 0;
                    it = $this$indexOfFirst$iv.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            index$iv2 = -1;
                            break;
                        }
                        $this$indexOfFirst$iv2 = $this$indexOfFirst$iv;
                        $i$f$indexOfFirst2 = $i$f$indexOfFirst;
                        objInvoke = function4.invoke(it.next());
                        it2 = transition.getTargetState();
                        if (Intrinsics.areEqual(objInvoke, function4.invoke(it2))) {
                            break;
                        }
                        index$iv2++;
                        $i$f$indexOfFirst = $i$f$indexOfFirst2;
                        $this$indexOfFirst$iv = $this$indexOfFirst$iv2;
                    }
                    id = index$iv2;
                    if (id == -1) {
                        currentlyVisible.add(transition.getTargetState());
                    } else {
                        currentlyVisible.set(id, transition.getTargetState());
                    }
                }
                if (contentMap.containsKey(transition.getTargetState()) || !contentMap.containsKey(transition.getCurrentState())) {
                    contentMap.clear();
                    $this$fastForEach$iv = currentlyVisible;
                    size = $this$fastForEach$iv.size();
                    index$iv = 0;
                    while (index$iv < size) {
                        final Object item$iv = $this$fastForEach$iv.get(index$iv);
                        List $this$fastForEach$iv2 = $this$fastForEach$iv;
                        final int i11 = $dirty;
                        Map contentMap3 = contentMap;
                        final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function7 = function3;
                        Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function8 = function3;
                        final AnimatedContentTransitionScopeImpl animatedContentTransitionScopeImpl = rootScope;
                        contentMap3.put(item$iv, ComposableLambdaKt.composableLambda($composer2, 885640742, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1
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

                            /* JADX WARN: Code duplicated, block: B:29:0x00f9  */
                            /* JADX WARN: Code duplicated, block: B:30:0x010d  */
                            /* JADX WARN: Code duplicated, block: B:33:0x0184  */
                            /* JADX WARN: Code duplicated, block: B:36:? A[RETURN, SYNTHETIC] */
                            /* JADX WARN: Type inference fix 'apply assigned field type' failed
                            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                             */
                            public final void invoke(Composer $composer3, int $changed2) {
                                ContentTransform value$iv$iv6;
                                Object value$iv$iv7;
                                S s;
                                Transition<S> transition2;
                                Object it$iv$iv4;
                                Object value$iv$iv8;
                                ComposerKt.sourceInformation($composer3, "C740@35270L38,744@35484L323,751@35840L125,756@36148L1332:AnimatedContent.kt#xbi5r1");
                                if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                    $composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(885640742, $changed2, -1, "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous> (AnimatedContent.kt:739)");
                                }
                                Function1<AnimatedContentTransitionScope<S>, ContentTransform> function9 = function7;
                                Object obj = animatedContentTransitionScopeImpl;
                                $composer3.startReplaceableGroup(-492369756);
                                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                                Object it$iv$iv5 = $composer3.rememberedValue();
                                if (it$iv$iv5 == Composer.INSTANCE.getEmpty()) {
                                    value$iv$iv6 = function9.invoke(obj);
                                    $composer3.updateRememberedValue(value$iv$iv6);
                                } else {
                                    value$iv$iv6 = it$iv$iv5;
                                }
                                $composer3.endReplaceableGroup();
                                final ContentTransform specOnEnter = (ContentTransform) value$iv$iv6;
                                Object key1$iv = Boolean.valueOf(Intrinsics.areEqual(transition.getSegment().getTargetState(), item$iv));
                                Transition<S> transition3 = transition;
                                S s2 = item$iv;
                                Function1<AnimatedContentTransitionScope<S>, ContentTransform> function10 = function7;
                                Object obj2 = animatedContentTransitionScopeImpl;
                                $composer3.startReplaceableGroup(1157296644);
                                ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                boolean invalid$iv$iv5 = $composer3.changed(key1$iv);
                                Object it$iv$iv6 = $composer3.rememberedValue();
                                if (!invalid$iv$iv5) {
                                    Object key1$iv2 = Composer.INSTANCE.getEmpty();
                                    if (it$iv$iv6 != key1$iv2) {
                                        value$iv$iv7 = it$iv$iv6;
                                    }
                                    $composer3.endReplaceableGroup();
                                    ExitTransition exit = (ExitTransition) value$iv$iv7;
                                    s = item$iv;
                                    transition2 = transition;
                                    $composer3.startReplaceableGroup(-492369756);
                                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                                    it$iv$iv4 = $composer3.rememberedValue();
                                    if (it$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                                        value$iv$iv8 = new AnimatedContentTransitionScopeImpl.ChildData(Intrinsics.areEqual(s, transition2.getTargetState()));
                                        $composer3.updateRememberedValue(value$iv$iv8);
                                    } else {
                                        value$iv$iv8 = it$iv$iv4;
                                    }
                                    $composer3.endReplaceableGroup();
                                    AnimatedContentTransitionScopeImpl.ChildData childData = (AnimatedContentTransitionScopeImpl.ChildData) value$iv$iv8;
                                    EnterTransition targetContentEnter = specOnEnter.getTargetContentEnter();
                                    Modifier modifierLayout = LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.1
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                            return m9invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                        }

                                        /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                        public final MeasureResult m9invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                                            Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                            Intrinsics.checkNotNullParameter(measurable, "measurable");
                                            final Placeable placeable = measurable.mo4225measureBRTryo0(constraints);
                                            int width = placeable.getWidth();
                                            int height = placeable.getHeight();
                                            final ContentTransform contentTransform = specOnEnter;
                                            return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.6.1.1.1
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
                                                public final void invoke2(Placeable.PlacementScope layout2) {
                                                    Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                                                    layout2.place(placeable, 0, 0, contentTransform.getTargetContentZIndex());
                                                }
                                            }, 4, null);
                                        }
                                    });
                                    childData.setTarget(Intrinsics.areEqual(item$iv, transition.getTargetState()));
                                    Modifier modifierThen = modifierLayout.then(childData);
                                    Transition<S> transition4 = transition;
                                    final S s3 = item$iv;
                                    Function1 function11 = new Function1<S, Boolean>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.3
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        /* JADX WARN: Can't rename method to resolve collision */
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Boolean invoke(S s4) {
                                            return Boolean.valueOf(Intrinsics.areEqual(s4, s3));
                                        }
                                    };
                                    final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl2 = animatedContentTransitionScopeImpl;
                                    final S s4 = item$iv;
                                    final SnapshotStateList<S> snapshotStateList = currentlyVisible;
                                    final Function4<AnimatedContentScope, S, Composer, Integer, Unit> function12 = content;
                                    final int i12 = i11;
                                    AnimatedVisibilityKt.AnimatedVisibility(transition4, function11, modifierThen, targetContentEnter, exit, ComposableLambdaKt.composableLambda($composer3, -1894897681, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.4
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                            invoke(animatedVisibilityScope, composer, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX WARN: Type inference fix 'apply assigned field type' failed
                                        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                                        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                                        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                                        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                                         */
                                        /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
                                            jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v4 boolean
                                            	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                                            	at jadx.core.dex.visitors.typeinference.FinishTypeInference.lambda$visit$0(FinishTypeInference.java:27)
                                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                                            	at jadx.core.dex.visitors.typeinference.FinishTypeInference.visit(FinishTypeInference.java:22)
                                            */
                                        public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r11, androidx.compose.runtime.Composer r12, int r13) {
                                            /*
                                                r10 = this;
                                                java.lang.String r0 = "$this$AnimatedVisibility"
                                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                                                java.lang.String r0 = "C770@36930L253,*778@37345L43,779@37416L24:AnimatedContent.kt#xbi5r1"
                                                androidx.compose.runtime.ComposerKt.sourceInformation(r12, r0)
                                                r0 = r13
                                                r1 = r13 & 14
                                                if (r1 != 0) goto L19
                                                boolean r1 = r12.changed(r11)
                                                if (r1 == 0) goto L17
                                                r1 = 4
                                                goto L18
                                            L17:
                                                r1 = 2
                                            L18:
                                                r0 = r0 | r1
                                            L19:
                                                r1 = r0 & 91
                                                r2 = 18
                                                if (r1 != r2) goto L2b
                                                boolean r1 = r12.getSkipping()
                                                if (r1 != 0) goto L26
                                                goto L2b
                                            L26:
                                                r12.skipToGroupEnd()
                                                goto Lac
                                            L2b:
                                                boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                                if (r1 == 0) goto L3a
                                                r1 = -1
                                                java.lang.String r2 = "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous>.<anonymous> (AnimatedContent.kt:768)"
                                                r3 = -1894897681(0xffffffff8f0e27ef, float:-7.0088315E-30)
                                                androidx.compose.runtime.ComposerKt.traceEventStart(r3, r0, r1, r2)
                                            L3a:
                                                androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1 r1 = new androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1
                                                androidx.compose.runtime.snapshots.SnapshotStateList<S> r2 = r4
                                                S r3 = r3
                                                androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r4 = r2
                                                r1.<init>()
                                                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                                                r2 = r0 & 14
                                                androidx.compose.runtime.EffectsKt.DisposableEffect(r11, r1, r12, r2)
                                                androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r1 = r2
                                                java.util.Map r1 = r1.getTargetSizeMap$animation_release()
                                                S r2 = r3
                                                r3 = r11
                                                androidx.compose.animation.AnimatedVisibilityScopeImpl r3 = (androidx.compose.animation.AnimatedVisibilityScopeImpl) r3
                                                androidx.compose.runtime.MutableState r3 = r3.getTargetSize$animation_release()
                                                r1.put(r2, r3)
                                                r1 = 0
                                                r2 = 0
                                                r3 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
                                                r12.startReplaceableGroup(r3)
                                                java.lang.String r3 = "CC(remember):Composables.kt#9igjgp"
                                                androidx.compose.runtime.ComposerKt.sourceInformation(r12, r3)
                                                r3 = 0
                                                r4 = r12
                                                r5 = 0
                                                java.lang.Object r6 = r4.rememberedValue()
                                                r7 = 0
                                                androidx.compose.runtime.Composer$Companion r8 = androidx.compose.runtime.Composer.INSTANCE
                                                java.lang.Object r8 = r8.getEmpty()
                                                if (r6 != r8) goto L87
                                                r8 = 0
                                                androidx.compose.animation.AnimatedContentScopeImpl r9 = new androidx.compose.animation.AnimatedContentScopeImpl
                                                r9.<init>(r11)
                                                r8 = r9
                                                r4.updateRememberedValue(r8)
                                                goto L88
                                            L87:
                                                r8 = r6
                                            L88:
                                                r12.endReplaceableGroup()
                                                kotlin.jvm.functions.Function4<androidx.compose.animation.AnimatedContentScope, S, androidx.compose.runtime.Composer, java.lang.Integer, kotlin.Unit> r1 = r5
                                                S r2 = r3
                                                int r3 = r6
                                                r4 = r8
                                                androidx.compose.animation.AnimatedContentScopeImpl r4 = (androidx.compose.animation.AnimatedContentScopeImpl) r4
                                                r5 = 0
                                                int r3 = r3 >> 9
                                                r3 = r3 & 896(0x380, float:1.256E-42)
                                                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                                                r1.invoke(r4, r2, r12, r3)
                                                boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                                if (r1 == 0) goto Lac
                                                androidx.compose.runtime.ComposerKt.traceEventEnd()
                                            Lac:
                                                return
                                            */
                                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.AnonymousClass4.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                                        }
                                    }), $composer3, 196608 | (i11 & 14), 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                                value$iv$iv7 = Intrinsics.areEqual(transition3.getSegment().getTargetState(), s2) ? ExitTransition.INSTANCE.getNone() : function10.invoke(obj2).getInitialContentExit();
                                $composer3.updateRememberedValue(value$iv$iv7);
                                $composer3.endReplaceableGroup();
                                ExitTransition exit2 = (ExitTransition) value$iv$iv7;
                                s = item$iv;
                                transition2 = transition;
                                $composer3.startReplaceableGroup(-492369756);
                                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                                it$iv$iv4 = $composer3.rememberedValue();
                                if (it$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                                    value$iv$iv8 = new AnimatedContentTransitionScopeImpl.ChildData(Intrinsics.areEqual(s, transition2.getTargetState()));
                                    $composer3.updateRememberedValue(value$iv$iv8);
                                } else {
                                    value$iv$iv8 = it$iv$iv4;
                                }
                                $composer3.endReplaceableGroup();
                                AnimatedContentTransitionScopeImpl.ChildData childData2 = (AnimatedContentTransitionScopeImpl.ChildData) value$iv$iv8;
                                EnterTransition targetContentEnter2 = specOnEnter.getTargetContentEnter();
                                Modifier modifierLayout2 = LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m9invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m9invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                                        Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                        Intrinsics.checkNotNullParameter(measurable, "measurable");
                                        final Placeable placeable = measurable.mo4225measureBRTryo0(constraints);
                                        int width = placeable.getWidth();
                                        int height = placeable.getHeight();
                                        final ContentTransform contentTransform = specOnEnter;
                                        return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.6.1.1.1
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
                                            public final void invoke2(Placeable.PlacementScope layout2) {
                                                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                                                layout2.place(placeable, 0, 0, contentTransform.getTargetContentZIndex());
                                            }
                                        }, 4, null);
                                    }
                                });
                                childData2.setTarget(Intrinsics.areEqual(item$iv, transition.getTargetState()));
                                Modifier modifierThen2 = modifierLayout2.then(childData2);
                                Transition<S> transition5 = transition;
                                final S s5 = item$iv;
                                Function1 function13 = new Function1<S, Boolean>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(S s6) {
                                        return Boolean.valueOf(Intrinsics.areEqual(s6, s5));
                                    }
                                };
                                final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl3 = animatedContentTransitionScopeImpl;
                                final S s6 = item$iv;
                                final SnapshotStateList<S> snapshotStateList2 = currentlyVisible;
                                final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function14 = content;
                                final int i13 = i11;
                                AnimatedVisibilityKt.AnimatedVisibility(transition5, function13, modifierThen2, targetContentEnter2, exit2, ComposableLambdaKt.composableLambda($composer3, -1894897681, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.4
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                        invoke(animatedVisibilityScope, composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
                                        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v4 boolean
                                        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                                        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.lambda$visit$0(FinishTypeInference.java:27)
                                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                                        */
                                    public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r11, androidx.compose.runtime.Composer r12, int r13) {
                                        /*
                                            r10 = this;
                                            java.lang.String r0 = "$this$AnimatedVisibility"
                                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                                            java.lang.String r0 = "C770@36930L253,*778@37345L43,779@37416L24:AnimatedContent.kt#xbi5r1"
                                            androidx.compose.runtime.ComposerKt.sourceInformation(r12, r0)
                                            r0 = r13
                                            r1 = r13 & 14
                                            if (r1 != 0) goto L19
                                            boolean r1 = r12.changed(r11)
                                            if (r1 == 0) goto L17
                                            r1 = 4
                                            goto L18
                                        L17:
                                            r1 = 2
                                        L18:
                                            r0 = r0 | r1
                                        L19:
                                            r1 = r0 & 91
                                            r2 = 18
                                            if (r1 != r2) goto L2b
                                            boolean r1 = r12.getSkipping()
                                            if (r1 != 0) goto L26
                                            goto L2b
                                        L26:
                                            r12.skipToGroupEnd()
                                            goto Lac
                                        L2b:
                                            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                            if (r1 == 0) goto L3a
                                            r1 = -1
                                            java.lang.String r2 = "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous>.<anonymous> (AnimatedContent.kt:768)"
                                            r3 = -1894897681(0xffffffff8f0e27ef, float:-7.0088315E-30)
                                            androidx.compose.runtime.ComposerKt.traceEventStart(r3, r0, r1, r2)
                                        L3a:
                                            androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1 r1 = new androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1
                                            androidx.compose.runtime.snapshots.SnapshotStateList<S> r2 = r4
                                            S r3 = r3
                                            androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r4 = r2
                                            r1.<init>()
                                            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                                            r2 = r0 & 14
                                            androidx.compose.runtime.EffectsKt.DisposableEffect(r11, r1, r12, r2)
                                            androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r1 = r2
                                            java.util.Map r1 = r1.getTargetSizeMap$animation_release()
                                            S r2 = r3
                                            r3 = r11
                                            androidx.compose.animation.AnimatedVisibilityScopeImpl r3 = (androidx.compose.animation.AnimatedVisibilityScopeImpl) r3
                                            androidx.compose.runtime.MutableState r3 = r3.getTargetSize$animation_release()
                                            r1.put(r2, r3)
                                            r1 = 0
                                            r2 = 0
                                            r3 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
                                            r12.startReplaceableGroup(r3)
                                            java.lang.String r3 = "CC(remember):Composables.kt#9igjgp"
                                            androidx.compose.runtime.ComposerKt.sourceInformation(r12, r3)
                                            r3 = 0
                                            r4 = r12
                                            r5 = 0
                                            java.lang.Object r6 = r4.rememberedValue()
                                            r7 = 0
                                            androidx.compose.runtime.Composer$Companion r8 = androidx.compose.runtime.Composer.INSTANCE
                                            java.lang.Object r8 = r8.getEmpty()
                                            if (r6 != r8) goto L87
                                            r8 = 0
                                            androidx.compose.animation.AnimatedContentScopeImpl r9 = new androidx.compose.animation.AnimatedContentScopeImpl
                                            r9.<init>(r11)
                                            r8 = r9
                                            r4.updateRememberedValue(r8)
                                            goto L88
                                        L87:
                                            r8 = r6
                                        L88:
                                            r12.endReplaceableGroup()
                                            kotlin.jvm.functions.Function4<androidx.compose.animation.AnimatedContentScope, S, androidx.compose.runtime.Composer, java.lang.Integer, kotlin.Unit> r1 = r5
                                            S r2 = r3
                                            int r3 = r6
                                            r4 = r8
                                            androidx.compose.animation.AnimatedContentScopeImpl r4 = (androidx.compose.animation.AnimatedContentScopeImpl) r4
                                            r5 = 0
                                            int r3 = r3 >> 9
                                            r3 = r3 & 896(0x380, float:1.256E-42)
                                            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                                            r1.invoke(r4, r2, r12, r3)
                                            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                            if (r1 == 0) goto Lac
                                            androidx.compose.runtime.ComposerKt.traceEventEnd()
                                        Lac:
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.AnonymousClass4.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                                    }
                                }), $composer3, 196608 | (i11 & 14), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        layoutDirection = layoutDirection;
                        index$iv++;
                        contentMap = contentMap3;
                        function3 = function8;
                        rootScope = rootScope;
                        size = size;
                        $this$fastForEach$iv = $this$fastForEach$iv2;
                        $dirty = $dirty;
                        contentAlignment2 = contentAlignment2;
                    }
                    contentMap2 = contentMap;
                    function5 = function3;
                    contentAlignment3 = contentAlignment2;
                    rootScope2 = rootScope;
                } else {
                    contentMap2 = contentMap;
                    function5 = function3;
                    contentAlignment3 = contentAlignment2;
                    rootScope2 = rootScope;
                }
                Object key2$iv = transition.getSegment();
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                invalid$iv$iv4 = $composer2.changed(rootScope2) | $composer2.changed(key2$iv);
                Object it$iv$iv4 = $composer2.rememberedValue();
                if (!invalid$iv$iv4 || it$iv$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv4 = function5.invoke(rootScope2);
                    $composer2.updateRememberedValue(value$iv$iv4);
                } else {
                    value$iv$iv4 = it$iv$iv4;
                }
                $composer2.endReplaceableGroup();
                Object key2$iv2 = value$iv$iv4;
                ContentTransform contentTransform = (ContentTransform) key2$iv2;
                Modifier sizeModifier = rootScope2.createSizeAnimationModifier$animation_release(contentTransform, $composer2, 72);
                Modifier modifierThen = modifier3.then(sizeModifier);
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv = $composer2.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv5 = new AnimatedContentMeasurePolicy(rootScope2);
                    $composer2.updateRememberedValue(value$iv$iv5);
                } else {
                    value$iv$iv5 = it$iv$iv;
                }
                $composer2.endReplaceableGroup();
                AnimatedContentMeasurePolicy animatedContentMeasurePolicy = (AnimatedContentMeasurePolicy) value$iv$iv5;
                $composer2.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierThen);
                int $changed$iv$iv = ((384 << 9) & 7168) | 6;
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor);
                } else {
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv = Updater.m2603constructorimpl($composer2);
                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, animatedContentMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                    $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                    $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                }
                function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv >> 3) & 112));
                $composer2.startReplaceableGroup(2058660585);
                int i12 = ($changed$iv$iv >> 9) & 14;
                i3 = 0;
                ComposerKt.sourceInformationMarkerStart($composer2, -740836641, "C:AnimatedContent.kt#xbi5r1");
                $composer2.startReplaceableGroup(-441507761);
                ComposerKt.sourceInformation($composer2, "");
                $this$forEach$iv = currentlyVisible;
                for (Object element$iv : $this$forEach$iv) {
                    Iterable $this$forEach$iv2 = $this$forEach$iv;
                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function9 = function3ModifierMaterializerOf;
                    int i13 = i3;
                    $composer2.startMovableGroup(-1739559893, function4.invoke(element$iv));
                    ComposerKt.sourceInformation($composer2, "793@37870L8");
                    function6 = (Function2) contentMap2.get(element$iv);
                    if (function6 != null) {
                        function6.invoke($composer2, 0);
                    }
                    $composer2.endMovableGroup();
                    function3ModifierMaterializerOf = function9;
                    $this$forEach$iv = $this$forEach$iv2;
                    i3 = i13;
                }
                $composer2.endReplaceableGroup();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceableGroup();
                $composer2.endNode();
                $composer2.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                function5 = function3;
                contentAlignment3 = contentAlignment2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function10 = function5;
            final Alignment alignment = contentAlignment3;
            final Function1<? super S, ? extends Object> function11 = function4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.9
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i14) throws Throwable {
                    AnimatedContentKt.AnimatedContent(transition, modifier4, function10, alignment, function11, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty2 |= i2;
        $dirty = $dirty2;
        if ((374491 & $dirty) == 74898) {
            if (i4 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i5 != 0) {
                function3 = new Function1<AnimatedContentTransitionScope<S>, ContentTransform>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.4
                    @Override // kotlin.jvm.functions.Function1
                    public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
                        Intrinsics.checkNotNullParameter(animatedContentTransitionScope, "$this$null");
                        return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m43scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
                    }
                };
            }
            if (i6 != 0) {
                contentAlignment2 = Alignment.INSTANCE.getTopStart();
            }
            if (i7 != 0) {
                function4 = new Function1<S, S>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.5
                    @Override // kotlin.jvm.functions.Function1
                    public final S invoke(S s) {
                        return s;
                    }
                };
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-114689412, $dirty, -1, "androidx.compose.animation.AnimatedContent (AnimatedContent.kt:681)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localLayoutDirection2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            layoutDirection = (LayoutDirection) objConsume2;
            int i14 = $dirty & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(transition);
            Object it$iv$iv5 = $composer2.rememberedValue();
            if (invalid$iv$iv) {
                value$iv$iv = new AnimatedContentTransitionScopeImpl(transition, contentAlignment2, layoutDirection);
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = new AnimatedContentTransitionScopeImpl(transition, contentAlignment2, layoutDirection);
                $composer2.updateRememberedValue(value$iv$iv);
            }
            $composer2.endReplaceableGroup();
            rootScope = (AnimatedContentTransitionScopeImpl) value$iv$iv;
            int i15 = $dirty & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer2.changed(transition);
            value$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv2) {
                value$iv$iv2 = SnapshotStateKt.mutableStateListOf(transition.getCurrentState());
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = SnapshotStateKt.mutableStateListOf(transition.getCurrentState());
                $composer2.updateRememberedValue(value$iv$iv2);
            }
            $composer2.endReplaceableGroup();
            currentlyVisible = (SnapshotStateList) value$iv$iv2;
            int i16 = $dirty & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv3 = $composer2.changed(transition);
            Object it$iv$iv6 = $composer2.rememberedValue();
            if (invalid$iv$iv3) {
            }
            value$iv$iv3 = new LinkedHashMap();
            $composer2.updateRememberedValue(value$iv$iv3);
            $composer2.endReplaceableGroup();
            contentMap = (Map) value$iv$iv3;
            if (!currentlyVisible.contains(transition.getCurrentState())) {
                currentlyVisible.clear();
                currentlyVisible.add(transition.getCurrentState());
            }
            if (Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                if (currentlyVisible.size() == 1) {
                    currentlyVisible.clear();
                    currentlyVisible.add(transition.getCurrentState());
                } else {
                    currentlyVisible.clear();
                    currentlyVisible.add(transition.getCurrentState());
                }
                if (contentMap.size() == 1) {
                    contentMap.clear();
                } else {
                    contentMap.clear();
                }
                rootScope.setContentAlignment$animation_release(contentAlignment2);
                rootScope.setLayoutDirection$animation_release(layoutDirection);
            }
            if (!Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                $this$indexOfFirst$iv = currentlyVisible;
                $i$f$indexOfFirst = 0;
                index$iv2 = 0;
                it = $this$indexOfFirst$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        index$iv2 = -1;
                        break;
                    }
                    $this$indexOfFirst$iv2 = $this$indexOfFirst$iv;
                    $i$f$indexOfFirst2 = $i$f$indexOfFirst;
                    objInvoke = function4.invoke(it.next());
                    it2 = transition.getTargetState();
                    if (Intrinsics.areEqual(objInvoke, function4.invoke(it2))) {
                        break;
                        break;
                    } else {
                        index$iv2++;
                        $i$f$indexOfFirst = $i$f$indexOfFirst2;
                        $this$indexOfFirst$iv = $this$indexOfFirst$iv2;
                    }
                }
                id = index$iv2;
                if (id == -1) {
                    currentlyVisible.add(transition.getTargetState());
                } else {
                    currentlyVisible.set(id, transition.getTargetState());
                }
            }
            if (contentMap.containsKey(transition.getTargetState())) {
                contentMap.clear();
                $this$fastForEach$iv = currentlyVisible;
                size = $this$fastForEach$iv.size();
                index$iv = 0;
                while (index$iv < size) {
                    final S item$iv2 = $this$fastForEach$iv.get(index$iv);
                    List $this$fastForEach$iv3 = $this$fastForEach$iv;
                    final int i17 = $dirty;
                    Map contentMap4 = contentMap;
                    final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function12 = function3;
                    Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function13 = function3;
                    final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl2 = rootScope;
                    contentMap4.put(item$iv2, ComposableLambdaKt.composableLambda($composer2, 885640742, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1
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

                        /* JADX WARN: Code duplicated, block: B:29:0x00f9  */
                        /* JADX WARN: Code duplicated, block: B:30:0x010d  */
                        /* JADX WARN: Code duplicated, block: B:33:0x0184  */
                        /* JADX WARN: Code duplicated, block: B:36:? A[RETURN, SYNTHETIC] */
                        /* JADX WARN: Type inference fix 'apply assigned field type' failed
                        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                         */
                        public final void invoke(Composer $composer3, int $changed2) {
                            ContentTransform value$iv$iv6;
                            Object value$iv$iv7;
                            S s;
                            Transition<S> transition2;
                            Object it$iv$iv7;
                            Object value$iv$iv8;
                            ComposerKt.sourceInformation($composer3, "C740@35270L38,744@35484L323,751@35840L125,756@36148L1332:AnimatedContent.kt#xbi5r1");
                            if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(885640742, $changed2, -1, "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous> (AnimatedContent.kt:739)");
                            }
                            Function1<AnimatedContentTransitionScope<S>, ContentTransform> function14 = function12;
                            Object obj = animatedContentTransitionScopeImpl2;
                            $composer3.startReplaceableGroup(-492369756);
                            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                            Object it$iv$iv8 = $composer3.rememberedValue();
                            if (it$iv$iv8 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv6 = function14.invoke(obj);
                                $composer3.updateRememberedValue(value$iv$iv6);
                            } else {
                                value$iv$iv6 = it$iv$iv8;
                            }
                            $composer3.endReplaceableGroup();
                            final ContentTransform specOnEnter = (ContentTransform) value$iv$iv6;
                            Object key1$iv = Boolean.valueOf(Intrinsics.areEqual(transition.getSegment().getTargetState(), item$iv2));
                            Transition<S> transition3 = transition;
                            S s2 = item$iv2;
                            Function1<AnimatedContentTransitionScope<S>, ContentTransform> function15 = function12;
                            Object obj2 = animatedContentTransitionScopeImpl2;
                            $composer3.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv$iv5 = $composer3.changed(key1$iv);
                            Object it$iv$iv9 = $composer3.rememberedValue();
                            if (!invalid$iv$iv5) {
                                Object key1$iv2 = Composer.INSTANCE.getEmpty();
                                if (it$iv$iv9 != key1$iv2) {
                                    value$iv$iv7 = it$iv$iv9;
                                }
                                $composer3.endReplaceableGroup();
                                ExitTransition exit2 = (ExitTransition) value$iv$iv7;
                                s = item$iv2;
                                transition2 = transition;
                                $composer3.startReplaceableGroup(-492369756);
                                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                                it$iv$iv7 = $composer3.rememberedValue();
                                if (it$iv$iv7 == Composer.INSTANCE.getEmpty()) {
                                    value$iv$iv8 = new AnimatedContentTransitionScopeImpl.ChildData(Intrinsics.areEqual(s, transition2.getTargetState()));
                                    $composer3.updateRememberedValue(value$iv$iv8);
                                } else {
                                    value$iv$iv8 = it$iv$iv7;
                                }
                                $composer3.endReplaceableGroup();
                                AnimatedContentTransitionScopeImpl.ChildData childData2 = (AnimatedContentTransitionScopeImpl.ChildData) value$iv$iv8;
                                EnterTransition targetContentEnter2 = specOnEnter.getTargetContentEnter();
                                Modifier modifierLayout2 = LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m9invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m9invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                                        Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                        Intrinsics.checkNotNullParameter(measurable, "measurable");
                                        final Placeable placeable = measurable.mo4225measureBRTryo0(constraints);
                                        int width = placeable.getWidth();
                                        int height = placeable.getHeight();
                                        final ContentTransform contentTransform2 = specOnEnter;
                                        return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.6.1.1.1
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
                                            public final void invoke2(Placeable.PlacementScope layout2) {
                                                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                                                layout2.place(placeable, 0, 0, contentTransform2.getTargetContentZIndex());
                                            }
                                        }, 4, null);
                                    }
                                });
                                childData2.setTarget(Intrinsics.areEqual(item$iv2, transition.getTargetState()));
                                Modifier modifierThen2 = modifierLayout2.then(childData2);
                                Transition<S> transition5 = transition;
                                final S s5 = item$iv2;
                                Function1 function16 = new Function1<S, Boolean>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(S s6) {
                                        return Boolean.valueOf(Intrinsics.areEqual(s6, s5));
                                    }
                                };
                                final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl3 = animatedContentTransitionScopeImpl2;
                                final S s6 = item$iv2;
                                final SnapshotStateList<S> snapshotStateList2 = currentlyVisible;
                                final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function17 = content;
                                final int i18 = i17;
                                AnimatedVisibilityKt.AnimatedVisibility(transition5, function16, modifierThen2, targetContentEnter2, exit2, ComposableLambdaKt.composableLambda($composer3, -1894897681, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.4
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                        invoke(animatedVisibilityScope, composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
                                        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v4 boolean
                                        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                                        */
                                    public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r11, androidx.compose.runtime.Composer r12, int r13) {
                                        /*
                                            r10 = this;
                                            java.lang.String r0 = "$this$AnimatedVisibility"
                                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                                            java.lang.String r0 = "C770@36930L253,*778@37345L43,779@37416L24:AnimatedContent.kt#xbi5r1"
                                            androidx.compose.runtime.ComposerKt.sourceInformation(r12, r0)
                                            r0 = r13
                                            r1 = r13 & 14
                                            if (r1 != 0) goto L19
                                            boolean r1 = r12.changed(r11)
                                            if (r1 == 0) goto L17
                                            r1 = 4
                                            goto L18
                                        L17:
                                            r1 = 2
                                        L18:
                                            r0 = r0 | r1
                                        L19:
                                            r1 = r0 & 91
                                            r2 = 18
                                            if (r1 != r2) goto L2b
                                            boolean r1 = r12.getSkipping()
                                            if (r1 != 0) goto L26
                                            goto L2b
                                        L26:
                                            r12.skipToGroupEnd()
                                            goto Lac
                                        L2b:
                                            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                            if (r1 == 0) goto L3a
                                            r1 = -1
                                            java.lang.String r2 = "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous>.<anonymous> (AnimatedContent.kt:768)"
                                            r3 = -1894897681(0xffffffff8f0e27ef, float:-7.0088315E-30)
                                            androidx.compose.runtime.ComposerKt.traceEventStart(r3, r0, r1, r2)
                                        L3a:
                                            androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1 r1 = new androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1
                                            androidx.compose.runtime.snapshots.SnapshotStateList<S> r2 = r4
                                            S r3 = r3
                                            androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r4 = r2
                                            r1.<init>()
                                            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                                            r2 = r0 & 14
                                            androidx.compose.runtime.EffectsKt.DisposableEffect(r11, r1, r12, r2)
                                            androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r1 = r2
                                            java.util.Map r1 = r1.getTargetSizeMap$animation_release()
                                            S r2 = r3
                                            r3 = r11
                                            androidx.compose.animation.AnimatedVisibilityScopeImpl r3 = (androidx.compose.animation.AnimatedVisibilityScopeImpl) r3
                                            androidx.compose.runtime.MutableState r3 = r3.getTargetSize$animation_release()
                                            r1.put(r2, r3)
                                            r1 = 0
                                            r2 = 0
                                            r3 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
                                            r12.startReplaceableGroup(r3)
                                            java.lang.String r3 = "CC(remember):Composables.kt#9igjgp"
                                            androidx.compose.runtime.ComposerKt.sourceInformation(r12, r3)
                                            r3 = 0
                                            r4 = r12
                                            r5 = 0
                                            java.lang.Object r6 = r4.rememberedValue()
                                            r7 = 0
                                            androidx.compose.runtime.Composer$Companion r8 = androidx.compose.runtime.Composer.INSTANCE
                                            java.lang.Object r8 = r8.getEmpty()
                                            if (r6 != r8) goto L87
                                            r8 = 0
                                            androidx.compose.animation.AnimatedContentScopeImpl r9 = new androidx.compose.animation.AnimatedContentScopeImpl
                                            r9.<init>(r11)
                                            r8 = r9
                                            r4.updateRememberedValue(r8)
                                            goto L88
                                        L87:
                                            r8 = r6
                                        L88:
                                            r12.endReplaceableGroup()
                                            kotlin.jvm.functions.Function4<androidx.compose.animation.AnimatedContentScope, S, androidx.compose.runtime.Composer, java.lang.Integer, kotlin.Unit> r1 = r5
                                            S r2 = r3
                                            int r3 = r6
                                            r4 = r8
                                            androidx.compose.animation.AnimatedContentScopeImpl r4 = (androidx.compose.animation.AnimatedContentScopeImpl) r4
                                            r5 = 0
                                            int r3 = r3 >> 9
                                            r3 = r3 & 896(0x380, float:1.256E-42)
                                            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                                            r1.invoke(r4, r2, r12, r3)
                                            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                            if (r1 == 0) goto Lac
                                            androidx.compose.runtime.ComposerKt.traceEventEnd()
                                        Lac:
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.AnonymousClass4.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                                    }
                                }), $composer3, 196608 | (i17 & 14), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                            value$iv$iv7 = Intrinsics.areEqual(transition3.getSegment().getTargetState(), s2) ? ExitTransition.INSTANCE.getNone() : function15.invoke(obj2).getInitialContentExit();
                            $composer3.updateRememberedValue(value$iv$iv7);
                            $composer3.endReplaceableGroup();
                            ExitTransition exit3 = (ExitTransition) value$iv$iv7;
                            s = item$iv2;
                            transition2 = transition;
                            $composer3.startReplaceableGroup(-492369756);
                            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                            it$iv$iv7 = $composer3.rememberedValue();
                            if (it$iv$iv7 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv8 = new AnimatedContentTransitionScopeImpl.ChildData(Intrinsics.areEqual(s, transition2.getTargetState()));
                                $composer3.updateRememberedValue(value$iv$iv8);
                            } else {
                                value$iv$iv8 = it$iv$iv7;
                            }
                            $composer3.endReplaceableGroup();
                            AnimatedContentTransitionScopeImpl.ChildData childData3 = (AnimatedContentTransitionScopeImpl.ChildData) value$iv$iv8;
                            EnterTransition targetContentEnter3 = specOnEnter.getTargetContentEnter();
                            Modifier modifierLayout3 = LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.1
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                    return m9invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                }

                                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                public final MeasureResult m9invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                                    Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                    Intrinsics.checkNotNullParameter(measurable, "measurable");
                                    final Placeable placeable = measurable.mo4225measureBRTryo0(constraints);
                                    int width = placeable.getWidth();
                                    int height = placeable.getHeight();
                                    final ContentTransform contentTransform2 = specOnEnter;
                                    return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.6.1.1.1
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
                                        public final void invoke2(Placeable.PlacementScope layout2) {
                                            Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                                            layout2.place(placeable, 0, 0, contentTransform2.getTargetContentZIndex());
                                        }
                                    }, 4, null);
                                }
                            });
                            childData3.setTarget(Intrinsics.areEqual(item$iv2, transition.getTargetState()));
                            Modifier modifierThen3 = modifierLayout3.then(childData3);
                            Transition<S> transition6 = transition;
                            final S s7 = item$iv2;
                            Function1 function18 = new Function1<S, Boolean>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(S s8) {
                                    return Boolean.valueOf(Intrinsics.areEqual(s8, s7));
                                }
                            };
                            final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl4 = animatedContentTransitionScopeImpl2;
                            final S s8 = item$iv2;
                            final SnapshotStateList<S> snapshotStateList3 = currentlyVisible;
                            final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function19 = content;
                            final int i19 = i17;
                            AnimatedVisibilityKt.AnimatedVisibility(transition6, function18, modifierThen3, targetContentEnter3, exit3, ComposableLambdaKt.composableLambda($composer3, -1894897681, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.4
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                    invoke(animatedVisibilityScope, composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
                                    jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v4 boolean
                                    */
                                public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r11, androidx.compose.runtime.Composer r12, int r13) {
                                    /*
                                        r10 = this;
                                        java.lang.String r0 = "$this$AnimatedVisibility"
                                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                                        java.lang.String r0 = "C770@36930L253,*778@37345L43,779@37416L24:AnimatedContent.kt#xbi5r1"
                                        androidx.compose.runtime.ComposerKt.sourceInformation(r12, r0)
                                        r0 = r13
                                        r1 = r13 & 14
                                        if (r1 != 0) goto L19
                                        boolean r1 = r12.changed(r11)
                                        if (r1 == 0) goto L17
                                        r1 = 4
                                        goto L18
                                    L17:
                                        r1 = 2
                                    L18:
                                        r0 = r0 | r1
                                    L19:
                                        r1 = r0 & 91
                                        r2 = 18
                                        if (r1 != r2) goto L2b
                                        boolean r1 = r12.getSkipping()
                                        if (r1 != 0) goto L26
                                        goto L2b
                                    L26:
                                        r12.skipToGroupEnd()
                                        goto Lac
                                    L2b:
                                        boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                        if (r1 == 0) goto L3a
                                        r1 = -1
                                        java.lang.String r2 = "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous>.<anonymous> (AnimatedContent.kt:768)"
                                        r3 = -1894897681(0xffffffff8f0e27ef, float:-7.0088315E-30)
                                        androidx.compose.runtime.ComposerKt.traceEventStart(r3, r0, r1, r2)
                                    L3a:
                                        androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1 r1 = new androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1
                                        androidx.compose.runtime.snapshots.SnapshotStateList<S> r2 = r4
                                        S r3 = r3
                                        androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r4 = r2
                                        r1.<init>()
                                        kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                                        r2 = r0 & 14
                                        androidx.compose.runtime.EffectsKt.DisposableEffect(r11, r1, r12, r2)
                                        androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r1 = r2
                                        java.util.Map r1 = r1.getTargetSizeMap$animation_release()
                                        S r2 = r3
                                        r3 = r11
                                        androidx.compose.animation.AnimatedVisibilityScopeImpl r3 = (androidx.compose.animation.AnimatedVisibilityScopeImpl) r3
                                        androidx.compose.runtime.MutableState r3 = r3.getTargetSize$animation_release()
                                        r1.put(r2, r3)
                                        r1 = 0
                                        r2 = 0
                                        r3 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
                                        r12.startReplaceableGroup(r3)
                                        java.lang.String r3 = "CC(remember):Composables.kt#9igjgp"
                                        androidx.compose.runtime.ComposerKt.sourceInformation(r12, r3)
                                        r3 = 0
                                        r4 = r12
                                        r5 = 0
                                        java.lang.Object r6 = r4.rememberedValue()
                                        r7 = 0
                                        androidx.compose.runtime.Composer$Companion r8 = androidx.compose.runtime.Composer.INSTANCE
                                        java.lang.Object r8 = r8.getEmpty()
                                        if (r6 != r8) goto L87
                                        r8 = 0
                                        androidx.compose.animation.AnimatedContentScopeImpl r9 = new androidx.compose.animation.AnimatedContentScopeImpl
                                        r9.<init>(r11)
                                        r8 = r9
                                        r4.updateRememberedValue(r8)
                                        goto L88
                                    L87:
                                        r8 = r6
                                    L88:
                                        r12.endReplaceableGroup()
                                        kotlin.jvm.functions.Function4<androidx.compose.animation.AnimatedContentScope, S, androidx.compose.runtime.Composer, java.lang.Integer, kotlin.Unit> r1 = r5
                                        S r2 = r3
                                        int r3 = r6
                                        r4 = r8
                                        androidx.compose.animation.AnimatedContentScopeImpl r4 = (androidx.compose.animation.AnimatedContentScopeImpl) r4
                                        r5 = 0
                                        int r3 = r3 >> 9
                                        r3 = r3 & 896(0x380, float:1.256E-42)
                                        java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                                        r1.invoke(r4, r2, r12, r3)
                                        boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                        if (r1 == 0) goto Lac
                                        androidx.compose.runtime.ComposerKt.traceEventEnd()
                                    Lac:
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.AnonymousClass4.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                                }
                            }), $composer3, 196608 | (i17 & 14), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    layoutDirection = layoutDirection;
                    index$iv++;
                    contentMap = contentMap4;
                    function3 = function13;
                    rootScope = rootScope;
                    size = size;
                    $this$fastForEach$iv = $this$fastForEach$iv3;
                    $dirty = $dirty;
                    contentAlignment2 = contentAlignment2;
                }
                contentMap2 = contentMap;
                function5 = function3;
                contentAlignment3 = contentAlignment2;
                rootScope2 = rootScope;
            } else {
                contentMap.clear();
                $this$fastForEach$iv = currentlyVisible;
                size = $this$fastForEach$iv.size();
                index$iv = 0;
                while (index$iv < size) {
                    final S item$iv3 = $this$fastForEach$iv.get(index$iv);
                    List $this$fastForEach$iv4 = $this$fastForEach$iv;
                    final int i18 = $dirty;
                    Map contentMap5 = contentMap;
                    final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function14 = function3;
                    Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function15 = function3;
                    final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl3 = rootScope;
                    contentMap5.put(item$iv3, ComposableLambdaKt.composableLambda($composer2, 885640742, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1
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

                        /* JADX WARN: Code duplicated, block: B:29:0x00f9  */
                        /* JADX WARN: Code duplicated, block: B:30:0x010d  */
                        /* JADX WARN: Code duplicated, block: B:33:0x0184  */
                        /* JADX WARN: Code duplicated, block: B:36:? A[RETURN, SYNTHETIC] */
                        /* JADX WARN: Type inference fix 'apply assigned field type' failed
                        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                         */
                        public final void invoke(Composer $composer3, int $changed2) {
                            ContentTransform value$iv$iv6;
                            Object value$iv$iv7;
                            S s;
                            Transition<S> transition2;
                            Object it$iv$iv7;
                            Object value$iv$iv8;
                            ComposerKt.sourceInformation($composer3, "C740@35270L38,744@35484L323,751@35840L125,756@36148L1332:AnimatedContent.kt#xbi5r1");
                            if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(885640742, $changed2, -1, "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous> (AnimatedContent.kt:739)");
                            }
                            Function1<AnimatedContentTransitionScope<S>, ContentTransform> function16 = function14;
                            Object obj = animatedContentTransitionScopeImpl3;
                            $composer3.startReplaceableGroup(-492369756);
                            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                            Object it$iv$iv8 = $composer3.rememberedValue();
                            if (it$iv$iv8 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv6 = function16.invoke(obj);
                                $composer3.updateRememberedValue(value$iv$iv6);
                            } else {
                                value$iv$iv6 = it$iv$iv8;
                            }
                            $composer3.endReplaceableGroup();
                            final ContentTransform specOnEnter = (ContentTransform) value$iv$iv6;
                            Object key1$iv = Boolean.valueOf(Intrinsics.areEqual(transition.getSegment().getTargetState(), item$iv3));
                            Transition<S> transition3 = transition;
                            S s2 = item$iv3;
                            Function1<AnimatedContentTransitionScope<S>, ContentTransform> function17 = function14;
                            Object obj2 = animatedContentTransitionScopeImpl3;
                            $composer3.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv$iv5 = $composer3.changed(key1$iv);
                            Object it$iv$iv9 = $composer3.rememberedValue();
                            if (!invalid$iv$iv5) {
                                Object key1$iv2 = Composer.INSTANCE.getEmpty();
                                if (it$iv$iv9 != key1$iv2) {
                                    value$iv$iv7 = it$iv$iv9;
                                }
                                $composer3.endReplaceableGroup();
                                ExitTransition exit3 = (ExitTransition) value$iv$iv7;
                                s = item$iv3;
                                transition2 = transition;
                                $composer3.startReplaceableGroup(-492369756);
                                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                                it$iv$iv7 = $composer3.rememberedValue();
                                if (it$iv$iv7 == Composer.INSTANCE.getEmpty()) {
                                    value$iv$iv8 = new AnimatedContentTransitionScopeImpl.ChildData(Intrinsics.areEqual(s, transition2.getTargetState()));
                                    $composer3.updateRememberedValue(value$iv$iv8);
                                } else {
                                    value$iv$iv8 = it$iv$iv7;
                                }
                                $composer3.endReplaceableGroup();
                                AnimatedContentTransitionScopeImpl.ChildData childData3 = (AnimatedContentTransitionScopeImpl.ChildData) value$iv$iv8;
                                EnterTransition targetContentEnter3 = specOnEnter.getTargetContentEnter();
                                Modifier modifierLayout3 = LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m9invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m9invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                                        Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                        Intrinsics.checkNotNullParameter(measurable, "measurable");
                                        final Placeable placeable = measurable.mo4225measureBRTryo0(constraints);
                                        int width = placeable.getWidth();
                                        int height = placeable.getHeight();
                                        final ContentTransform contentTransform2 = specOnEnter;
                                        return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.6.1.1.1
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
                                            public final void invoke2(Placeable.PlacementScope layout2) {
                                                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                                                layout2.place(placeable, 0, 0, contentTransform2.getTargetContentZIndex());
                                            }
                                        }, 4, null);
                                    }
                                });
                                childData3.setTarget(Intrinsics.areEqual(item$iv3, transition.getTargetState()));
                                Modifier modifierThen3 = modifierLayout3.then(childData3);
                                Transition<S> transition6 = transition;
                                final S s7 = item$iv3;
                                Function1 function18 = new Function1<S, Boolean>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(S s8) {
                                        return Boolean.valueOf(Intrinsics.areEqual(s8, s7));
                                    }
                                };
                                final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl4 = animatedContentTransitionScopeImpl3;
                                final S s8 = item$iv3;
                                final SnapshotStateList<S> snapshotStateList3 = currentlyVisible;
                                final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function19 = content;
                                final int i19 = i18;
                                AnimatedVisibilityKt.AnimatedVisibility(transition6, function18, modifierThen3, targetContentEnter3, exit3, ComposableLambdaKt.composableLambda($composer3, -1894897681, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.4
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                        invoke(animatedVisibilityScope, composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
                                        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v4 boolean
                                        */
                                    public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r11, androidx.compose.runtime.Composer r12, int r13) {
                                        /*
                                            r10 = this;
                                            java.lang.String r0 = "$this$AnimatedVisibility"
                                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                                            java.lang.String r0 = "C770@36930L253,*778@37345L43,779@37416L24:AnimatedContent.kt#xbi5r1"
                                            androidx.compose.runtime.ComposerKt.sourceInformation(r12, r0)
                                            r0 = r13
                                            r1 = r13 & 14
                                            if (r1 != 0) goto L19
                                            boolean r1 = r12.changed(r11)
                                            if (r1 == 0) goto L17
                                            r1 = 4
                                            goto L18
                                        L17:
                                            r1 = 2
                                        L18:
                                            r0 = r0 | r1
                                        L19:
                                            r1 = r0 & 91
                                            r2 = 18
                                            if (r1 != r2) goto L2b
                                            boolean r1 = r12.getSkipping()
                                            if (r1 != 0) goto L26
                                            goto L2b
                                        L26:
                                            r12.skipToGroupEnd()
                                            goto Lac
                                        L2b:
                                            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                            if (r1 == 0) goto L3a
                                            r1 = -1
                                            java.lang.String r2 = "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous>.<anonymous> (AnimatedContent.kt:768)"
                                            r3 = -1894897681(0xffffffff8f0e27ef, float:-7.0088315E-30)
                                            androidx.compose.runtime.ComposerKt.traceEventStart(r3, r0, r1, r2)
                                        L3a:
                                            androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1 r1 = new androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1
                                            androidx.compose.runtime.snapshots.SnapshotStateList<S> r2 = r4
                                            S r3 = r3
                                            androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r4 = r2
                                            r1.<init>()
                                            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                                            r2 = r0 & 14
                                            androidx.compose.runtime.EffectsKt.DisposableEffect(r11, r1, r12, r2)
                                            androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r1 = r2
                                            java.util.Map r1 = r1.getTargetSizeMap$animation_release()
                                            S r2 = r3
                                            r3 = r11
                                            androidx.compose.animation.AnimatedVisibilityScopeImpl r3 = (androidx.compose.animation.AnimatedVisibilityScopeImpl) r3
                                            androidx.compose.runtime.MutableState r3 = r3.getTargetSize$animation_release()
                                            r1.put(r2, r3)
                                            r1 = 0
                                            r2 = 0
                                            r3 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
                                            r12.startReplaceableGroup(r3)
                                            java.lang.String r3 = "CC(remember):Composables.kt#9igjgp"
                                            androidx.compose.runtime.ComposerKt.sourceInformation(r12, r3)
                                            r3 = 0
                                            r4 = r12
                                            r5 = 0
                                            java.lang.Object r6 = r4.rememberedValue()
                                            r7 = 0
                                            androidx.compose.runtime.Composer$Companion r8 = androidx.compose.runtime.Composer.INSTANCE
                                            java.lang.Object r8 = r8.getEmpty()
                                            if (r6 != r8) goto L87
                                            r8 = 0
                                            androidx.compose.animation.AnimatedContentScopeImpl r9 = new androidx.compose.animation.AnimatedContentScopeImpl
                                            r9.<init>(r11)
                                            r8 = r9
                                            r4.updateRememberedValue(r8)
                                            goto L88
                                        L87:
                                            r8 = r6
                                        L88:
                                            r12.endReplaceableGroup()
                                            kotlin.jvm.functions.Function4<androidx.compose.animation.AnimatedContentScope, S, androidx.compose.runtime.Composer, java.lang.Integer, kotlin.Unit> r1 = r5
                                            S r2 = r3
                                            int r3 = r6
                                            r4 = r8
                                            androidx.compose.animation.AnimatedContentScopeImpl r4 = (androidx.compose.animation.AnimatedContentScopeImpl) r4
                                            r5 = 0
                                            int r3 = r3 >> 9
                                            r3 = r3 & 896(0x380, float:1.256E-42)
                                            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                                            r1.invoke(r4, r2, r12, r3)
                                            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                            if (r1 == 0) goto Lac
                                            androidx.compose.runtime.ComposerKt.traceEventEnd()
                                        Lac:
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.AnonymousClass4.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                                    }
                                }), $composer3, 196608 | (i18 & 14), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                            value$iv$iv7 = Intrinsics.areEqual(transition3.getSegment().getTargetState(), s2) ? ExitTransition.INSTANCE.getNone() : function17.invoke(obj2).getInitialContentExit();
                            $composer3.updateRememberedValue(value$iv$iv7);
                            $composer3.endReplaceableGroup();
                            ExitTransition exit4 = (ExitTransition) value$iv$iv7;
                            s = item$iv3;
                            transition2 = transition;
                            $composer3.startReplaceableGroup(-492369756);
                            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                            it$iv$iv7 = $composer3.rememberedValue();
                            if (it$iv$iv7 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv8 = new AnimatedContentTransitionScopeImpl.ChildData(Intrinsics.areEqual(s, transition2.getTargetState()));
                                $composer3.updateRememberedValue(value$iv$iv8);
                            } else {
                                value$iv$iv8 = it$iv$iv7;
                            }
                            $composer3.endReplaceableGroup();
                            AnimatedContentTransitionScopeImpl.ChildData childData4 = (AnimatedContentTransitionScopeImpl.ChildData) value$iv$iv8;
                            EnterTransition targetContentEnter4 = specOnEnter.getTargetContentEnter();
                            Modifier modifierLayout4 = LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.1
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                    return m9invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                }

                                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                public final MeasureResult m9invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                                    Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                    Intrinsics.checkNotNullParameter(measurable, "measurable");
                                    final Placeable placeable = measurable.mo4225measureBRTryo0(constraints);
                                    int width = placeable.getWidth();
                                    int height = placeable.getHeight();
                                    final ContentTransform contentTransform2 = specOnEnter;
                                    return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.6.1.1.1
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
                                        public final void invoke2(Placeable.PlacementScope layout2) {
                                            Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                                            layout2.place(placeable, 0, 0, contentTransform2.getTargetContentZIndex());
                                        }
                                    }, 4, null);
                                }
                            });
                            childData4.setTarget(Intrinsics.areEqual(item$iv3, transition.getTargetState()));
                            Modifier modifierThen4 = modifierLayout4.then(childData4);
                            Transition<S> transition7 = transition;
                            final S s9 = item$iv3;
                            Function1 function110 = new Function1<S, Boolean>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(S s10) {
                                    return Boolean.valueOf(Intrinsics.areEqual(s10, s9));
                                }
                            };
                            final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl5 = animatedContentTransitionScopeImpl3;
                            final S s10 = item$iv3;
                            final SnapshotStateList<S> snapshotStateList4 = currentlyVisible;
                            final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function111 = content;
                            final int i110 = i18;
                            AnimatedVisibilityKt.AnimatedVisibility(transition7, function110, modifierThen4, targetContentEnter4, exit4, ComposableLambdaKt.composableLambda($composer3, -1894897681, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.4
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                    invoke(animatedVisibilityScope, composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
                                    jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v4 boolean
                                    */
                                public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r11, androidx.compose.runtime.Composer r12, int r13) {
                                    /*
                                        r10 = this;
                                        java.lang.String r0 = "$this$AnimatedVisibility"
                                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                                        java.lang.String r0 = "C770@36930L253,*778@37345L43,779@37416L24:AnimatedContent.kt#xbi5r1"
                                        androidx.compose.runtime.ComposerKt.sourceInformation(r12, r0)
                                        r0 = r13
                                        r1 = r13 & 14
                                        if (r1 != 0) goto L19
                                        boolean r1 = r12.changed(r11)
                                        if (r1 == 0) goto L17
                                        r1 = 4
                                        goto L18
                                    L17:
                                        r1 = 2
                                    L18:
                                        r0 = r0 | r1
                                    L19:
                                        r1 = r0 & 91
                                        r2 = 18
                                        if (r1 != r2) goto L2b
                                        boolean r1 = r12.getSkipping()
                                        if (r1 != 0) goto L26
                                        goto L2b
                                    L26:
                                        r12.skipToGroupEnd()
                                        goto Lac
                                    L2b:
                                        boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                        if (r1 == 0) goto L3a
                                        r1 = -1
                                        java.lang.String r2 = "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous>.<anonymous> (AnimatedContent.kt:768)"
                                        r3 = -1894897681(0xffffffff8f0e27ef, float:-7.0088315E-30)
                                        androidx.compose.runtime.ComposerKt.traceEventStart(r3, r0, r1, r2)
                                    L3a:
                                        androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1 r1 = new androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1
                                        androidx.compose.runtime.snapshots.SnapshotStateList<S> r2 = r4
                                        S r3 = r3
                                        androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r4 = r2
                                        r1.<init>()
                                        kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                                        r2 = r0 & 14
                                        androidx.compose.runtime.EffectsKt.DisposableEffect(r11, r1, r12, r2)
                                        androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r1 = r2
                                        java.util.Map r1 = r1.getTargetSizeMap$animation_release()
                                        S r2 = r3
                                        r3 = r11
                                        androidx.compose.animation.AnimatedVisibilityScopeImpl r3 = (androidx.compose.animation.AnimatedVisibilityScopeImpl) r3
                                        androidx.compose.runtime.MutableState r3 = r3.getTargetSize$animation_release()
                                        r1.put(r2, r3)
                                        r1 = 0
                                        r2 = 0
                                        r3 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
                                        r12.startReplaceableGroup(r3)
                                        java.lang.String r3 = "CC(remember):Composables.kt#9igjgp"
                                        androidx.compose.runtime.ComposerKt.sourceInformation(r12, r3)
                                        r3 = 0
                                        r4 = r12
                                        r5 = 0
                                        java.lang.Object r6 = r4.rememberedValue()
                                        r7 = 0
                                        androidx.compose.runtime.Composer$Companion r8 = androidx.compose.runtime.Composer.INSTANCE
                                        java.lang.Object r8 = r8.getEmpty()
                                        if (r6 != r8) goto L87
                                        r8 = 0
                                        androidx.compose.animation.AnimatedContentScopeImpl r9 = new androidx.compose.animation.AnimatedContentScopeImpl
                                        r9.<init>(r11)
                                        r8 = r9
                                        r4.updateRememberedValue(r8)
                                        goto L88
                                    L87:
                                        r8 = r6
                                    L88:
                                        r12.endReplaceableGroup()
                                        kotlin.jvm.functions.Function4<androidx.compose.animation.AnimatedContentScope, S, androidx.compose.runtime.Composer, java.lang.Integer, kotlin.Unit> r1 = r5
                                        S r2 = r3
                                        int r3 = r6
                                        r4 = r8
                                        androidx.compose.animation.AnimatedContentScopeImpl r4 = (androidx.compose.animation.AnimatedContentScopeImpl) r4
                                        r5 = 0
                                        int r3 = r3 >> 9
                                        r3 = r3 & 896(0x380, float:1.256E-42)
                                        java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                                        r1.invoke(r4, r2, r12, r3)
                                        boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                        if (r1 == 0) goto Lac
                                        androidx.compose.runtime.ComposerKt.traceEventEnd()
                                    Lac:
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.AnonymousClass4.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                                }
                            }), $composer3, 196608 | (i18 & 14), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    layoutDirection = layoutDirection;
                    index$iv++;
                    contentMap = contentMap5;
                    function3 = function15;
                    rootScope = rootScope;
                    size = size;
                    $this$fastForEach$iv = $this$fastForEach$iv4;
                    $dirty = $dirty;
                    contentAlignment2 = contentAlignment2;
                }
                contentMap2 = contentMap;
                function5 = function3;
                contentAlignment3 = contentAlignment2;
                rootScope2 = rootScope;
            }
            Object key2$iv3 = transition.getSegment();
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv4 = $composer2.changed(rootScope2) | $composer2.changed(key2$iv3);
            Object it$iv$iv7 = $composer2.rememberedValue();
            if (invalid$iv$iv4) {
                value$iv$iv4 = function5.invoke(rootScope2);
                $composer2.updateRememberedValue(value$iv$iv4);
            } else {
                value$iv$iv4 = function5.invoke(rootScope2);
                $composer2.updateRememberedValue(value$iv$iv4);
            }
            $composer2.endReplaceableGroup();
            Object key2$iv4 = value$iv$iv4;
            ContentTransform contentTransform2 = (ContentTransform) key2$iv4;
            Modifier sizeModifier2 = rootScope2.createSizeAnimationModifier$animation_release(contentTransform2, $composer2, 72);
            Modifier modifierThen2 = modifier3.then(sizeModifier2);
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv5 = new AnimatedContentMeasurePolicy(rootScope2);
                $composer2.updateRememberedValue(value$iv$iv5);
            } else {
                value$iv$iv5 = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            AnimatedContentMeasurePolicy animatedContentMeasurePolicy2 = (AnimatedContentMeasurePolicy) value$iv$iv5;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv2 = $composer2.getCurrentCompositionLocalMap();
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierThen2);
            int $changed$iv$iv2 = ((384 << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, animatedContentMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, localMap$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash2);
            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv2 >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i19 = ($changed$iv$iv2 >> 9) & 14;
            i3 = 0;
            ComposerKt.sourceInformationMarkerStart($composer2, -740836641, "C:AnimatedContent.kt#xbi5r1");
            $composer2.startReplaceableGroup(-441507761);
            ComposerKt.sourceInformation($composer2, "");
            $this$forEach$iv = currentlyVisible;
            while (r18.hasNext()) {
                Iterable $this$forEach$iv3 = $this$forEach$iv;
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function16 = function3ModifierMaterializerOf;
                int i110 = i3;
                $composer2.startMovableGroup(-1739559893, function4.invoke(element$iv));
                ComposerKt.sourceInformation($composer2, "793@37870L8");
                function6 = (Function2) contentMap2.get(element$iv);
                if (function6 != null) {
                    function6.invoke($composer2, 0);
                }
                $composer2.endMovableGroup();
                function3ModifierMaterializerOf = function16;
                $this$forEach$iv = $this$forEach$iv3;
                i3 = i110;
            }
            $composer2.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            if (i4 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i5 != 0) {
                function3 = new Function1<AnimatedContentTransitionScope<S>, ContentTransform>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.4
                    @Override // kotlin.jvm.functions.Function1
                    public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
                        Intrinsics.checkNotNullParameter(animatedContentTransitionScope, "$this$null");
                        return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m43scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
                    }
                };
            }
            if (i6 != 0) {
                contentAlignment2 = Alignment.INSTANCE.getTopStart();
            }
            if (i7 != 0) {
                function4 = new Function1<S, S>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.5
                    @Override // kotlin.jvm.functions.Function1
                    public final S invoke(S s) {
                        return s;
                    }
                };
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-114689412, $dirty, -1, "androidx.compose.animation.AnimatedContent (AnimatedContent.kt:681)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localLayoutDirection3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            layoutDirection = (LayoutDirection) objConsume3;
            int i111 = $dirty & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv = $composer2.changed(transition);
            Object it$iv$iv8 = $composer2.rememberedValue();
            if (invalid$iv$iv) {
                value$iv$iv = new AnimatedContentTransitionScopeImpl(transition, contentAlignment2, layoutDirection);
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = new AnimatedContentTransitionScopeImpl(transition, contentAlignment2, layoutDirection);
                $composer2.updateRememberedValue(value$iv$iv);
            }
            $composer2.endReplaceableGroup();
            rootScope = (AnimatedContentTransitionScopeImpl) value$iv$iv;
            int i112 = $dirty & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv2 = $composer2.changed(transition);
            value$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv2) {
                value$iv$iv2 = SnapshotStateKt.mutableStateListOf(transition.getCurrentState());
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = SnapshotStateKt.mutableStateListOf(transition.getCurrentState());
                $composer2.updateRememberedValue(value$iv$iv2);
            }
            $composer2.endReplaceableGroup();
            currentlyVisible = (SnapshotStateList) value$iv$iv2;
            int i113 = $dirty & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            invalid$iv$iv3 = $composer2.changed(transition);
            Object it$iv$iv9 = $composer2.rememberedValue();
            if (invalid$iv$iv3) {
            }
            value$iv$iv3 = new LinkedHashMap();
            $composer2.updateRememberedValue(value$iv$iv3);
            $composer2.endReplaceableGroup();
            contentMap = (Map) value$iv$iv3;
            if (!currentlyVisible.contains(transition.getCurrentState())) {
                currentlyVisible.clear();
                currentlyVisible.add(transition.getCurrentState());
            }
            if (Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                if (currentlyVisible.size() == 1) {
                    currentlyVisible.clear();
                    currentlyVisible.add(transition.getCurrentState());
                } else {
                    currentlyVisible.clear();
                    currentlyVisible.add(transition.getCurrentState());
                }
                if (contentMap.size() == 1) {
                    contentMap.clear();
                } else {
                    contentMap.clear();
                }
                rootScope.setContentAlignment$animation_release(contentAlignment2);
                rootScope.setLayoutDirection$animation_release(layoutDirection);
            }
            if (!Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                $this$indexOfFirst$iv = currentlyVisible;
                $i$f$indexOfFirst = 0;
                index$iv2 = 0;
                it = $this$indexOfFirst$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        index$iv2 = -1;
                        break;
                    }
                    $this$indexOfFirst$iv2 = $this$indexOfFirst$iv;
                    $i$f$indexOfFirst2 = $i$f$indexOfFirst;
                    objInvoke = function4.invoke(it.next());
                    it2 = transition.getTargetState();
                    if (Intrinsics.areEqual(objInvoke, function4.invoke(it2))) {
                        break;
                        break;
                    } else {
                        index$iv2++;
                        $i$f$indexOfFirst = $i$f$indexOfFirst2;
                        $this$indexOfFirst$iv = $this$indexOfFirst$iv2;
                    }
                }
                id = index$iv2;
                if (id == -1) {
                    currentlyVisible.add(transition.getTargetState());
                } else {
                    currentlyVisible.set(id, transition.getTargetState());
                }
            }
            if (contentMap.containsKey(transition.getTargetState())) {
                contentMap.clear();
                $this$fastForEach$iv = currentlyVisible;
                size = $this$fastForEach$iv.size();
                index$iv = 0;
                while (index$iv < size) {
                    final S item$iv4 = $this$fastForEach$iv.get(index$iv);
                    List $this$fastForEach$iv5 = $this$fastForEach$iv;
                    final int i114 = $dirty;
                    Map contentMap6 = contentMap;
                    final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function17 = function3;
                    Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function18 = function3;
                    final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl4 = rootScope;
                    contentMap6.put(item$iv4, ComposableLambdaKt.composableLambda($composer2, 885640742, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1
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

                        /* JADX WARN: Code duplicated, block: B:29:0x00f9  */
                        /* JADX WARN: Code duplicated, block: B:30:0x010d  */
                        /* JADX WARN: Code duplicated, block: B:33:0x0184  */
                        /* JADX WARN: Code duplicated, block: B:36:? A[RETURN, SYNTHETIC] */
                        /* JADX WARN: Type inference fix 'apply assigned field type' failed
                        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                         */
                        public final void invoke(Composer $composer3, int $changed2) {
                            ContentTransform value$iv$iv6;
                            Object value$iv$iv7;
                            S s;
                            Transition<S> transition2;
                            Object it$iv$iv10;
                            Object value$iv$iv8;
                            ComposerKt.sourceInformation($composer3, "C740@35270L38,744@35484L323,751@35840L125,756@36148L1332:AnimatedContent.kt#xbi5r1");
                            if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(885640742, $changed2, -1, "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous> (AnimatedContent.kt:739)");
                            }
                            Function1<AnimatedContentTransitionScope<S>, ContentTransform> function19 = function17;
                            Object obj = animatedContentTransitionScopeImpl4;
                            $composer3.startReplaceableGroup(-492369756);
                            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                            Object it$iv$iv11 = $composer3.rememberedValue();
                            if (it$iv$iv11 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv6 = function19.invoke(obj);
                                $composer3.updateRememberedValue(value$iv$iv6);
                            } else {
                                value$iv$iv6 = it$iv$iv11;
                            }
                            $composer3.endReplaceableGroup();
                            final ContentTransform specOnEnter = (ContentTransform) value$iv$iv6;
                            Object key1$iv = Boolean.valueOf(Intrinsics.areEqual(transition.getSegment().getTargetState(), item$iv4));
                            Transition<S> transition3 = transition;
                            S s2 = item$iv4;
                            Function1<AnimatedContentTransitionScope<S>, ContentTransform> function110 = function17;
                            Object obj2 = animatedContentTransitionScopeImpl4;
                            $composer3.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv$iv5 = $composer3.changed(key1$iv);
                            Object it$iv$iv12 = $composer3.rememberedValue();
                            if (!invalid$iv$iv5) {
                                Object key1$iv2 = Composer.INSTANCE.getEmpty();
                                if (it$iv$iv12 != key1$iv2) {
                                    value$iv$iv7 = it$iv$iv12;
                                }
                                $composer3.endReplaceableGroup();
                                ExitTransition exit4 = (ExitTransition) value$iv$iv7;
                                s = item$iv4;
                                transition2 = transition;
                                $composer3.startReplaceableGroup(-492369756);
                                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                                it$iv$iv10 = $composer3.rememberedValue();
                                if (it$iv$iv10 == Composer.INSTANCE.getEmpty()) {
                                    value$iv$iv8 = new AnimatedContentTransitionScopeImpl.ChildData(Intrinsics.areEqual(s, transition2.getTargetState()));
                                    $composer3.updateRememberedValue(value$iv$iv8);
                                } else {
                                    value$iv$iv8 = it$iv$iv10;
                                }
                                $composer3.endReplaceableGroup();
                                AnimatedContentTransitionScopeImpl.ChildData childData4 = (AnimatedContentTransitionScopeImpl.ChildData) value$iv$iv8;
                                EnterTransition targetContentEnter4 = specOnEnter.getTargetContentEnter();
                                Modifier modifierLayout4 = LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m9invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m9invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                                        Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                        Intrinsics.checkNotNullParameter(measurable, "measurable");
                                        final Placeable placeable = measurable.mo4225measureBRTryo0(constraints);
                                        int width = placeable.getWidth();
                                        int height = placeable.getHeight();
                                        final ContentTransform contentTransform3 = specOnEnter;
                                        return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.6.1.1.1
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
                                            public final void invoke2(Placeable.PlacementScope layout2) {
                                                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                                                layout2.place(placeable, 0, 0, contentTransform3.getTargetContentZIndex());
                                            }
                                        }, 4, null);
                                    }
                                });
                                childData4.setTarget(Intrinsics.areEqual(item$iv4, transition.getTargetState()));
                                Modifier modifierThen4 = modifierLayout4.then(childData4);
                                Transition<S> transition7 = transition;
                                final S s9 = item$iv4;
                                Function1 function111 = new Function1<S, Boolean>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(S s10) {
                                        return Boolean.valueOf(Intrinsics.areEqual(s10, s9));
                                    }
                                };
                                final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl5 = animatedContentTransitionScopeImpl4;
                                final S s10 = item$iv4;
                                final SnapshotStateList<S> snapshotStateList4 = currentlyVisible;
                                final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function112 = content;
                                final int i115 = i114;
                                AnimatedVisibilityKt.AnimatedVisibility(transition7, function111, modifierThen4, targetContentEnter4, exit4, ComposableLambdaKt.composableLambda($composer3, -1894897681, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.4
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                        invoke(animatedVisibilityScope, composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
                                        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v4 boolean
                                        */
                                    public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r11, androidx.compose.runtime.Composer r12, int r13) {
                                        /*
                                            r10 = this;
                                            java.lang.String r0 = "$this$AnimatedVisibility"
                                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                                            java.lang.String r0 = "C770@36930L253,*778@37345L43,779@37416L24:AnimatedContent.kt#xbi5r1"
                                            androidx.compose.runtime.ComposerKt.sourceInformation(r12, r0)
                                            r0 = r13
                                            r1 = r13 & 14
                                            if (r1 != 0) goto L19
                                            boolean r1 = r12.changed(r11)
                                            if (r1 == 0) goto L17
                                            r1 = 4
                                            goto L18
                                        L17:
                                            r1 = 2
                                        L18:
                                            r0 = r0 | r1
                                        L19:
                                            r1 = r0 & 91
                                            r2 = 18
                                            if (r1 != r2) goto L2b
                                            boolean r1 = r12.getSkipping()
                                            if (r1 != 0) goto L26
                                            goto L2b
                                        L26:
                                            r12.skipToGroupEnd()
                                            goto Lac
                                        L2b:
                                            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                            if (r1 == 0) goto L3a
                                            r1 = -1
                                            java.lang.String r2 = "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous>.<anonymous> (AnimatedContent.kt:768)"
                                            r3 = -1894897681(0xffffffff8f0e27ef, float:-7.0088315E-30)
                                            androidx.compose.runtime.ComposerKt.traceEventStart(r3, r0, r1, r2)
                                        L3a:
                                            androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1 r1 = new androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1
                                            androidx.compose.runtime.snapshots.SnapshotStateList<S> r2 = r4
                                            S r3 = r3
                                            androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r4 = r2
                                            r1.<init>()
                                            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                                            r2 = r0 & 14
                                            androidx.compose.runtime.EffectsKt.DisposableEffect(r11, r1, r12, r2)
                                            androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r1 = r2
                                            java.util.Map r1 = r1.getTargetSizeMap$animation_release()
                                            S r2 = r3
                                            r3 = r11
                                            androidx.compose.animation.AnimatedVisibilityScopeImpl r3 = (androidx.compose.animation.AnimatedVisibilityScopeImpl) r3
                                            androidx.compose.runtime.MutableState r3 = r3.getTargetSize$animation_release()
                                            r1.put(r2, r3)
                                            r1 = 0
                                            r2 = 0
                                            r3 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
                                            r12.startReplaceableGroup(r3)
                                            java.lang.String r3 = "CC(remember):Composables.kt#9igjgp"
                                            androidx.compose.runtime.ComposerKt.sourceInformation(r12, r3)
                                            r3 = 0
                                            r4 = r12
                                            r5 = 0
                                            java.lang.Object r6 = r4.rememberedValue()
                                            r7 = 0
                                            androidx.compose.runtime.Composer$Companion r8 = androidx.compose.runtime.Composer.INSTANCE
                                            java.lang.Object r8 = r8.getEmpty()
                                            if (r6 != r8) goto L87
                                            r8 = 0
                                            androidx.compose.animation.AnimatedContentScopeImpl r9 = new androidx.compose.animation.AnimatedContentScopeImpl
                                            r9.<init>(r11)
                                            r8 = r9
                                            r4.updateRememberedValue(r8)
                                            goto L88
                                        L87:
                                            r8 = r6
                                        L88:
                                            r12.endReplaceableGroup()
                                            kotlin.jvm.functions.Function4<androidx.compose.animation.AnimatedContentScope, S, androidx.compose.runtime.Composer, java.lang.Integer, kotlin.Unit> r1 = r5
                                            S r2 = r3
                                            int r3 = r6
                                            r4 = r8
                                            androidx.compose.animation.AnimatedContentScopeImpl r4 = (androidx.compose.animation.AnimatedContentScopeImpl) r4
                                            r5 = 0
                                            int r3 = r3 >> 9
                                            r3 = r3 & 896(0x380, float:1.256E-42)
                                            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                                            r1.invoke(r4, r2, r12, r3)
                                            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                            if (r1 == 0) goto Lac
                                            androidx.compose.runtime.ComposerKt.traceEventEnd()
                                        Lac:
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.AnonymousClass4.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                                    }
                                }), $composer3, 196608 | (i114 & 14), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                            value$iv$iv7 = Intrinsics.areEqual(transition3.getSegment().getTargetState(), s2) ? ExitTransition.INSTANCE.getNone() : function110.invoke(obj2).getInitialContentExit();
                            $composer3.updateRememberedValue(value$iv$iv7);
                            $composer3.endReplaceableGroup();
                            ExitTransition exit5 = (ExitTransition) value$iv$iv7;
                            s = item$iv4;
                            transition2 = transition;
                            $composer3.startReplaceableGroup(-492369756);
                            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                            it$iv$iv10 = $composer3.rememberedValue();
                            if (it$iv$iv10 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv8 = new AnimatedContentTransitionScopeImpl.ChildData(Intrinsics.areEqual(s, transition2.getTargetState()));
                                $composer3.updateRememberedValue(value$iv$iv8);
                            } else {
                                value$iv$iv8 = it$iv$iv10;
                            }
                            $composer3.endReplaceableGroup();
                            AnimatedContentTransitionScopeImpl.ChildData childData5 = (AnimatedContentTransitionScopeImpl.ChildData) value$iv$iv8;
                            EnterTransition targetContentEnter5 = specOnEnter.getTargetContentEnter();
                            Modifier modifierLayout5 = LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.1
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                    return m9invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                }

                                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                public final MeasureResult m9invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                                    Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                    Intrinsics.checkNotNullParameter(measurable, "measurable");
                                    final Placeable placeable = measurable.mo4225measureBRTryo0(constraints);
                                    int width = placeable.getWidth();
                                    int height = placeable.getHeight();
                                    final ContentTransform contentTransform3 = specOnEnter;
                                    return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.6.1.1.1
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
                                        public final void invoke2(Placeable.PlacementScope layout2) {
                                            Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                                            layout2.place(placeable, 0, 0, contentTransform3.getTargetContentZIndex());
                                        }
                                    }, 4, null);
                                }
                            });
                            childData5.setTarget(Intrinsics.areEqual(item$iv4, transition.getTargetState()));
                            Modifier modifierThen5 = modifierLayout5.then(childData5);
                            Transition<S> transition8 = transition;
                            final S s11 = item$iv4;
                            Function1 function113 = new Function1<S, Boolean>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(S s12) {
                                    return Boolean.valueOf(Intrinsics.areEqual(s12, s11));
                                }
                            };
                            final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl6 = animatedContentTransitionScopeImpl4;
                            final S s12 = item$iv4;
                            final SnapshotStateList<S> snapshotStateList5 = currentlyVisible;
                            final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function114 = content;
                            final int i116 = i114;
                            AnimatedVisibilityKt.AnimatedVisibility(transition8, function113, modifierThen5, targetContentEnter5, exit5, ComposableLambdaKt.composableLambda($composer3, -1894897681, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.4
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                    invoke(animatedVisibilityScope, composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
                                    jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v4 boolean
                                    */
                                public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r11, androidx.compose.runtime.Composer r12, int r13) {
                                    /*
                                        r10 = this;
                                        java.lang.String r0 = "$this$AnimatedVisibility"
                                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                                        java.lang.String r0 = "C770@36930L253,*778@37345L43,779@37416L24:AnimatedContent.kt#xbi5r1"
                                        androidx.compose.runtime.ComposerKt.sourceInformation(r12, r0)
                                        r0 = r13
                                        r1 = r13 & 14
                                        if (r1 != 0) goto L19
                                        boolean r1 = r12.changed(r11)
                                        if (r1 == 0) goto L17
                                        r1 = 4
                                        goto L18
                                    L17:
                                        r1 = 2
                                    L18:
                                        r0 = r0 | r1
                                    L19:
                                        r1 = r0 & 91
                                        r2 = 18
                                        if (r1 != r2) goto L2b
                                        boolean r1 = r12.getSkipping()
                                        if (r1 != 0) goto L26
                                        goto L2b
                                    L26:
                                        r12.skipToGroupEnd()
                                        goto Lac
                                    L2b:
                                        boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                        if (r1 == 0) goto L3a
                                        r1 = -1
                                        java.lang.String r2 = "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous>.<anonymous> (AnimatedContent.kt:768)"
                                        r3 = -1894897681(0xffffffff8f0e27ef, float:-7.0088315E-30)
                                        androidx.compose.runtime.ComposerKt.traceEventStart(r3, r0, r1, r2)
                                    L3a:
                                        androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1 r1 = new androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1
                                        androidx.compose.runtime.snapshots.SnapshotStateList<S> r2 = r4
                                        S r3 = r3
                                        androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r4 = r2
                                        r1.<init>()
                                        kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                                        r2 = r0 & 14
                                        androidx.compose.runtime.EffectsKt.DisposableEffect(r11, r1, r12, r2)
                                        androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r1 = r2
                                        java.util.Map r1 = r1.getTargetSizeMap$animation_release()
                                        S r2 = r3
                                        r3 = r11
                                        androidx.compose.animation.AnimatedVisibilityScopeImpl r3 = (androidx.compose.animation.AnimatedVisibilityScopeImpl) r3
                                        androidx.compose.runtime.MutableState r3 = r3.getTargetSize$animation_release()
                                        r1.put(r2, r3)
                                        r1 = 0
                                        r2 = 0
                                        r3 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
                                        r12.startReplaceableGroup(r3)
                                        java.lang.String r3 = "CC(remember):Composables.kt#9igjgp"
                                        androidx.compose.runtime.ComposerKt.sourceInformation(r12, r3)
                                        r3 = 0
                                        r4 = r12
                                        r5 = 0
                                        java.lang.Object r6 = r4.rememberedValue()
                                        r7 = 0
                                        androidx.compose.runtime.Composer$Companion r8 = androidx.compose.runtime.Composer.INSTANCE
                                        java.lang.Object r8 = r8.getEmpty()
                                        if (r6 != r8) goto L87
                                        r8 = 0
                                        androidx.compose.animation.AnimatedContentScopeImpl r9 = new androidx.compose.animation.AnimatedContentScopeImpl
                                        r9.<init>(r11)
                                        r8 = r9
                                        r4.updateRememberedValue(r8)
                                        goto L88
                                    L87:
                                        r8 = r6
                                    L88:
                                        r12.endReplaceableGroup()
                                        kotlin.jvm.functions.Function4<androidx.compose.animation.AnimatedContentScope, S, androidx.compose.runtime.Composer, java.lang.Integer, kotlin.Unit> r1 = r5
                                        S r2 = r3
                                        int r3 = r6
                                        r4 = r8
                                        androidx.compose.animation.AnimatedContentScopeImpl r4 = (androidx.compose.animation.AnimatedContentScopeImpl) r4
                                        r5 = 0
                                        int r3 = r3 >> 9
                                        r3 = r3 & 896(0x380, float:1.256E-42)
                                        java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                                        r1.invoke(r4, r2, r12, r3)
                                        boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                        if (r1 == 0) goto Lac
                                        androidx.compose.runtime.ComposerKt.traceEventEnd()
                                    Lac:
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.AnonymousClass4.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                                }
                            }), $composer3, 196608 | (i114 & 14), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    layoutDirection = layoutDirection;
                    index$iv++;
                    contentMap = contentMap6;
                    function3 = function18;
                    rootScope = rootScope;
                    size = size;
                    $this$fastForEach$iv = $this$fastForEach$iv5;
                    $dirty = $dirty;
                    contentAlignment2 = contentAlignment2;
                }
                contentMap2 = contentMap;
                function5 = function3;
                contentAlignment3 = contentAlignment2;
                rootScope2 = rootScope;
            } else {
                contentMap.clear();
                $this$fastForEach$iv = currentlyVisible;
                size = $this$fastForEach$iv.size();
                index$iv = 0;
                while (index$iv < size) {
                    final S item$iv5 = $this$fastForEach$iv.get(index$iv);
                    List $this$fastForEach$iv6 = $this$fastForEach$iv;
                    final int i115 = $dirty;
                    Map contentMap7 = contentMap;
                    final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function19 = function3;
                    Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function110 = function3;
                    final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl5 = rootScope;
                    contentMap7.put(item$iv5, ComposableLambdaKt.composableLambda($composer2, 885640742, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1
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

                        /* JADX WARN: Code duplicated, block: B:29:0x00f9  */
                        /* JADX WARN: Code duplicated, block: B:30:0x010d  */
                        /* JADX WARN: Code duplicated, block: B:33:0x0184  */
                        /* JADX WARN: Code duplicated, block: B:36:? A[RETURN, SYNTHETIC] */
                        /* JADX WARN: Type inference fix 'apply assigned field type' failed
                        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                         */
                        public final void invoke(Composer $composer3, int $changed2) {
                            ContentTransform value$iv$iv6;
                            Object value$iv$iv7;
                            S s;
                            Transition<S> transition2;
                            Object it$iv$iv10;
                            Object value$iv$iv8;
                            ComposerKt.sourceInformation($composer3, "C740@35270L38,744@35484L323,751@35840L125,756@36148L1332:AnimatedContent.kt#xbi5r1");
                            if (($changed2 & 11) == 2 && $composer3.getSkipping()) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(885640742, $changed2, -1, "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous> (AnimatedContent.kt:739)");
                            }
                            Function1<AnimatedContentTransitionScope<S>, ContentTransform> function111 = function19;
                            Object obj = animatedContentTransitionScopeImpl5;
                            $composer3.startReplaceableGroup(-492369756);
                            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                            Object it$iv$iv11 = $composer3.rememberedValue();
                            if (it$iv$iv11 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv6 = function111.invoke(obj);
                                $composer3.updateRememberedValue(value$iv$iv6);
                            } else {
                                value$iv$iv6 = it$iv$iv11;
                            }
                            $composer3.endReplaceableGroup();
                            final ContentTransform specOnEnter = (ContentTransform) value$iv$iv6;
                            Object key1$iv = Boolean.valueOf(Intrinsics.areEqual(transition.getSegment().getTargetState(), item$iv5));
                            Transition<S> transition3 = transition;
                            S s2 = item$iv5;
                            Function1<AnimatedContentTransitionScope<S>, ContentTransform> function112 = function19;
                            Object obj2 = animatedContentTransitionScopeImpl5;
                            $composer3.startReplaceableGroup(1157296644);
                            ComposerKt.sourceInformation($composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                            boolean invalid$iv$iv5 = $composer3.changed(key1$iv);
                            Object it$iv$iv12 = $composer3.rememberedValue();
                            if (!invalid$iv$iv5) {
                                Object key1$iv2 = Composer.INSTANCE.getEmpty();
                                if (it$iv$iv12 != key1$iv2) {
                                    value$iv$iv7 = it$iv$iv12;
                                }
                                $composer3.endReplaceableGroup();
                                ExitTransition exit5 = (ExitTransition) value$iv$iv7;
                                s = item$iv5;
                                transition2 = transition;
                                $composer3.startReplaceableGroup(-492369756);
                                ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                                it$iv$iv10 = $composer3.rememberedValue();
                                if (it$iv$iv10 == Composer.INSTANCE.getEmpty()) {
                                    value$iv$iv8 = new AnimatedContentTransitionScopeImpl.ChildData(Intrinsics.areEqual(s, transition2.getTargetState()));
                                    $composer3.updateRememberedValue(value$iv$iv8);
                                } else {
                                    value$iv$iv8 = it$iv$iv10;
                                }
                                $composer3.endReplaceableGroup();
                                AnimatedContentTransitionScopeImpl.ChildData childData5 = (AnimatedContentTransitionScopeImpl.ChildData) value$iv$iv8;
                                EnterTransition targetContentEnter5 = specOnEnter.getTargetContentEnter();
                                Modifier modifierLayout5 = LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m9invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m9invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                                        Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                        Intrinsics.checkNotNullParameter(measurable, "measurable");
                                        final Placeable placeable = measurable.mo4225measureBRTryo0(constraints);
                                        int width = placeable.getWidth();
                                        int height = placeable.getHeight();
                                        final ContentTransform contentTransform3 = specOnEnter;
                                        return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.6.1.1.1
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
                                            public final void invoke2(Placeable.PlacementScope layout2) {
                                                Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                                                layout2.place(placeable, 0, 0, contentTransform3.getTargetContentZIndex());
                                            }
                                        }, 4, null);
                                    }
                                });
                                childData5.setTarget(Intrinsics.areEqual(item$iv5, transition.getTargetState()));
                                Modifier modifierThen5 = modifierLayout5.then(childData5);
                                Transition<S> transition8 = transition;
                                final S s11 = item$iv5;
                                Function1 function113 = new Function1<S, Boolean>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Boolean invoke(S s12) {
                                        return Boolean.valueOf(Intrinsics.areEqual(s12, s11));
                                    }
                                };
                                final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl6 = animatedContentTransitionScopeImpl5;
                                final S s12 = item$iv5;
                                final SnapshotStateList<S> snapshotStateList5 = currentlyVisible;
                                final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function114 = content;
                                final int i116 = i115;
                                AnimatedVisibilityKt.AnimatedVisibility(transition8, function113, modifierThen5, targetContentEnter5, exit5, ComposableLambdaKt.composableLambda($composer3, -1894897681, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.4
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                        invoke(animatedVisibilityScope, composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
                                        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v4 boolean
                                        */
                                    public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r11, androidx.compose.runtime.Composer r12, int r13) {
                                        /*
                                            r10 = this;
                                            java.lang.String r0 = "$this$AnimatedVisibility"
                                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                                            java.lang.String r0 = "C770@36930L253,*778@37345L43,779@37416L24:AnimatedContent.kt#xbi5r1"
                                            androidx.compose.runtime.ComposerKt.sourceInformation(r12, r0)
                                            r0 = r13
                                            r1 = r13 & 14
                                            if (r1 != 0) goto L19
                                            boolean r1 = r12.changed(r11)
                                            if (r1 == 0) goto L17
                                            r1 = 4
                                            goto L18
                                        L17:
                                            r1 = 2
                                        L18:
                                            r0 = r0 | r1
                                        L19:
                                            r1 = r0 & 91
                                            r2 = 18
                                            if (r1 != r2) goto L2b
                                            boolean r1 = r12.getSkipping()
                                            if (r1 != 0) goto L26
                                            goto L2b
                                        L26:
                                            r12.skipToGroupEnd()
                                            goto Lac
                                        L2b:
                                            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                            if (r1 == 0) goto L3a
                                            r1 = -1
                                            java.lang.String r2 = "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous>.<anonymous> (AnimatedContent.kt:768)"
                                            r3 = -1894897681(0xffffffff8f0e27ef, float:-7.0088315E-30)
                                            androidx.compose.runtime.ComposerKt.traceEventStart(r3, r0, r1, r2)
                                        L3a:
                                            androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1 r1 = new androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1
                                            androidx.compose.runtime.snapshots.SnapshotStateList<S> r2 = r4
                                            S r3 = r3
                                            androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r4 = r2
                                            r1.<init>()
                                            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                                            r2 = r0 & 14
                                            androidx.compose.runtime.EffectsKt.DisposableEffect(r11, r1, r12, r2)
                                            androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r1 = r2
                                            java.util.Map r1 = r1.getTargetSizeMap$animation_release()
                                            S r2 = r3
                                            r3 = r11
                                            androidx.compose.animation.AnimatedVisibilityScopeImpl r3 = (androidx.compose.animation.AnimatedVisibilityScopeImpl) r3
                                            androidx.compose.runtime.MutableState r3 = r3.getTargetSize$animation_release()
                                            r1.put(r2, r3)
                                            r1 = 0
                                            r2 = 0
                                            r3 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
                                            r12.startReplaceableGroup(r3)
                                            java.lang.String r3 = "CC(remember):Composables.kt#9igjgp"
                                            androidx.compose.runtime.ComposerKt.sourceInformation(r12, r3)
                                            r3 = 0
                                            r4 = r12
                                            r5 = 0
                                            java.lang.Object r6 = r4.rememberedValue()
                                            r7 = 0
                                            androidx.compose.runtime.Composer$Companion r8 = androidx.compose.runtime.Composer.INSTANCE
                                            java.lang.Object r8 = r8.getEmpty()
                                            if (r6 != r8) goto L87
                                            r8 = 0
                                            androidx.compose.animation.AnimatedContentScopeImpl r9 = new androidx.compose.animation.AnimatedContentScopeImpl
                                            r9.<init>(r11)
                                            r8 = r9
                                            r4.updateRememberedValue(r8)
                                            goto L88
                                        L87:
                                            r8 = r6
                                        L88:
                                            r12.endReplaceableGroup()
                                            kotlin.jvm.functions.Function4<androidx.compose.animation.AnimatedContentScope, S, androidx.compose.runtime.Composer, java.lang.Integer, kotlin.Unit> r1 = r5
                                            S r2 = r3
                                            int r3 = r6
                                            r4 = r8
                                            androidx.compose.animation.AnimatedContentScopeImpl r4 = (androidx.compose.animation.AnimatedContentScopeImpl) r4
                                            r5 = 0
                                            int r3 = r3 >> 9
                                            r3 = r3 & 896(0x380, float:1.256E-42)
                                            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                                            r1.invoke(r4, r2, r12, r3)
                                            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                            if (r1 == 0) goto Lac
                                            androidx.compose.runtime.ComposerKt.traceEventEnd()
                                        Lac:
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.AnonymousClass4.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                                    }
                                }), $composer3, 196608 | (i115 & 14), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                            value$iv$iv7 = Intrinsics.areEqual(transition3.getSegment().getTargetState(), s2) ? ExitTransition.INSTANCE.getNone() : function112.invoke(obj2).getInitialContentExit();
                            $composer3.updateRememberedValue(value$iv$iv7);
                            $composer3.endReplaceableGroup();
                            ExitTransition exit6 = (ExitTransition) value$iv$iv7;
                            s = item$iv5;
                            transition2 = transition;
                            $composer3.startReplaceableGroup(-492369756);
                            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                            it$iv$iv10 = $composer3.rememberedValue();
                            if (it$iv$iv10 == Composer.INSTANCE.getEmpty()) {
                                value$iv$iv8 = new AnimatedContentTransitionScopeImpl.ChildData(Intrinsics.areEqual(s, transition2.getTargetState()));
                                $composer3.updateRememberedValue(value$iv$iv8);
                            } else {
                                value$iv$iv8 = it$iv$iv10;
                            }
                            $composer3.endReplaceableGroup();
                            AnimatedContentTransitionScopeImpl.ChildData childData6 = (AnimatedContentTransitionScopeImpl.ChildData) value$iv$iv8;
                            EnterTransition targetContentEnter6 = specOnEnter.getTargetContentEnter();
                            Modifier modifierLayout6 = LayoutModifierKt.layout(Modifier.INSTANCE, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.1
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                    return m9invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                }

                                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                public final MeasureResult m9invoke3p2s80s(MeasureScope layout, Measurable measurable, long constraints) {
                                    Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                    Intrinsics.checkNotNullParameter(measurable, "measurable");
                                    final Placeable placeable = measurable.mo4225measureBRTryo0(constraints);
                                    int width = placeable.getWidth();
                                    int height = placeable.getHeight();
                                    final ContentTransform contentTransform3 = specOnEnter;
                                    return MeasureScope.CC.layout$default(layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.6.1.1.1
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
                                        public final void invoke2(Placeable.PlacementScope layout2) {
                                            Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                                            layout2.place(placeable, 0, 0, contentTransform3.getTargetContentZIndex());
                                        }
                                    }, 4, null);
                                }
                            });
                            childData6.setTarget(Intrinsics.areEqual(item$iv5, transition.getTargetState()));
                            Modifier modifierThen6 = modifierLayout6.then(childData6);
                            Transition<S> transition9 = transition;
                            final S s13 = item$iv5;
                            Function1 function115 = new Function1<S, Boolean>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(S s14) {
                                    return Boolean.valueOf(Intrinsics.areEqual(s14, s13));
                                }
                            };
                            final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl7 = animatedContentTransitionScopeImpl5;
                            final S s14 = item$iv5;
                            final SnapshotStateList<S> snapshotStateList6 = currentlyVisible;
                            final Function4<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, Unit> function116 = content;
                            final int i117 = i115;
                            AnimatedVisibilityKt.AnimatedVisibility(transition9, function115, modifierThen6, targetContentEnter6, exit6, ComposableLambdaKt.composableLambda($composer3, -1894897681, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.4
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                    invoke(animatedVisibilityScope, composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
                                    jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v4 boolean
                                    */
                                public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r11, androidx.compose.runtime.Composer r12, int r13) {
                                    /*
                                        r10 = this;
                                        java.lang.String r0 = "$this$AnimatedVisibility"
                                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                                        java.lang.String r0 = "C770@36930L253,*778@37345L43,779@37416L24:AnimatedContent.kt#xbi5r1"
                                        androidx.compose.runtime.ComposerKt.sourceInformation(r12, r0)
                                        r0 = r13
                                        r1 = r13 & 14
                                        if (r1 != 0) goto L19
                                        boolean r1 = r12.changed(r11)
                                        if (r1 == 0) goto L17
                                        r1 = 4
                                        goto L18
                                    L17:
                                        r1 = 2
                                    L18:
                                        r0 = r0 | r1
                                    L19:
                                        r1 = r0 & 91
                                        r2 = 18
                                        if (r1 != r2) goto L2b
                                        boolean r1 = r12.getSkipping()
                                        if (r1 != 0) goto L26
                                        goto L2b
                                    L26:
                                        r12.skipToGroupEnd()
                                        goto Lac
                                    L2b:
                                        boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                        if (r1 == 0) goto L3a
                                        r1 = -1
                                        java.lang.String r2 = "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous>.<anonymous> (AnimatedContent.kt:768)"
                                        r3 = -1894897681(0xffffffff8f0e27ef, float:-7.0088315E-30)
                                        androidx.compose.runtime.ComposerKt.traceEventStart(r3, r0, r1, r2)
                                    L3a:
                                        androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1 r1 = new androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$4$1
                                        androidx.compose.runtime.snapshots.SnapshotStateList<S> r2 = r4
                                        S r3 = r3
                                        androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r4 = r2
                                        r1.<init>()
                                        kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                                        r2 = r0 & 14
                                        androidx.compose.runtime.EffectsKt.DisposableEffect(r11, r1, r12, r2)
                                        androidx.compose.animation.AnimatedContentTransitionScopeImpl<S> r1 = r2
                                        java.util.Map r1 = r1.getTargetSizeMap$animation_release()
                                        S r2 = r3
                                        r3 = r11
                                        androidx.compose.animation.AnimatedVisibilityScopeImpl r3 = (androidx.compose.animation.AnimatedVisibilityScopeImpl) r3
                                        androidx.compose.runtime.MutableState r3 = r3.getTargetSize$animation_release()
                                        r1.put(r2, r3)
                                        r1 = 0
                                        r2 = 0
                                        r3 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
                                        r12.startReplaceableGroup(r3)
                                        java.lang.String r3 = "CC(remember):Composables.kt#9igjgp"
                                        androidx.compose.runtime.ComposerKt.sourceInformation(r12, r3)
                                        r3 = 0
                                        r4 = r12
                                        r5 = 0
                                        java.lang.Object r6 = r4.rememberedValue()
                                        r7 = 0
                                        androidx.compose.runtime.Composer$Companion r8 = androidx.compose.runtime.Composer.INSTANCE
                                        java.lang.Object r8 = r8.getEmpty()
                                        if (r6 != r8) goto L87
                                        r8 = 0
                                        androidx.compose.animation.AnimatedContentScopeImpl r9 = new androidx.compose.animation.AnimatedContentScopeImpl
                                        r9.<init>(r11)
                                        r8 = r9
                                        r4.updateRememberedValue(r8)
                                        goto L88
                                    L87:
                                        r8 = r6
                                    L88:
                                        r12.endReplaceableGroup()
                                        kotlin.jvm.functions.Function4<androidx.compose.animation.AnimatedContentScope, S, androidx.compose.runtime.Composer, java.lang.Integer, kotlin.Unit> r1 = r5
                                        S r2 = r3
                                        int r3 = r6
                                        r4 = r8
                                        androidx.compose.animation.AnimatedContentScopeImpl r4 = (androidx.compose.animation.AnimatedContentScopeImpl) r4
                                        r5 = 0
                                        int r3 = r3 >> 9
                                        r3 = r3 & 896(0x380, float:1.256E-42)
                                        java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                                        r1.invoke(r4, r2, r12, r3)
                                        boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                        if (r1 == 0) goto Lac
                                        androidx.compose.runtime.ComposerKt.traceEventEnd()
                                    Lac:
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1.AnonymousClass4.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                                }
                            }), $composer3, 196608 | (i115 & 14), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                    layoutDirection = layoutDirection;
                    index$iv++;
                    contentMap = contentMap7;
                    function3 = function110;
                    rootScope = rootScope;
                    size = size;
                    $this$fastForEach$iv = $this$fastForEach$iv6;
                    $dirty = $dirty;
                    contentAlignment2 = contentAlignment2;
                }
                contentMap2 = contentMap;
                function5 = function3;
                contentAlignment3 = contentAlignment2;
                rootScope2 = rootScope;
            }
            Object key2$iv5 = transition.getSegment();
            $composer2.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
            invalid$iv$iv4 = $composer2.changed(rootScope2) | $composer2.changed(key2$iv5);
            Object it$iv$iv10 = $composer2.rememberedValue();
            if (invalid$iv$iv4) {
                value$iv$iv4 = function5.invoke(rootScope2);
                $composer2.updateRememberedValue(value$iv$iv4);
            } else {
                value$iv$iv4 = function5.invoke(rootScope2);
                $composer2.updateRememberedValue(value$iv$iv4);
            }
            $composer2.endReplaceableGroup();
            Object key2$iv6 = value$iv$iv4;
            ContentTransform contentTransform3 = (ContentTransform) key2$iv6;
            Modifier sizeModifier3 = rootScope2.createSizeAnimationModifier$animation_release(contentTransform3, $composer2, 72);
            Modifier modifierThen3 = modifier3.then(sizeModifier3);
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv5 = new AnimatedContentMeasurePolicy(rootScope2);
                $composer2.updateRememberedValue(value$iv$iv5);
            } else {
                value$iv$iv5 = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            AnimatedContentMeasurePolicy animatedContentMeasurePolicy3 = (AnimatedContentMeasurePolicy) value$iv$iv5;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv3 = $composer2.getCurrentCompositionLocalMap();
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierThen3);
            int $changed$iv$iv3 = ((384 << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv = Updater.m2603constructorimpl($composer2);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, animatedContentMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, localMap$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash3);
            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv3 >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i116 = ($changed$iv$iv3 >> 9) & 14;
            i3 = 0;
            ComposerKt.sourceInformationMarkerStart($composer2, -740836641, "C:AnimatedContent.kt#xbi5r1");
            $composer2.startReplaceableGroup(-441507761);
            ComposerKt.sourceInformation($composer2, "");
            $this$forEach$iv = currentlyVisible;
            while (r18.hasNext()) {
                Iterable $this$forEach$iv4 = $this$forEach$iv;
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function111 = function3ModifierMaterializerOf;
                int i117 = i3;
                $composer2.startMovableGroup(-1739559893, function4.invoke(element$iv));
                ComposerKt.sourceInformation($composer2, "793@37870L8");
                function6 = (Function2) contentMap2.get(element$iv);
                if (function6 != null) {
                    function6.invoke($composer2, 0);
                }
                $composer2.endMovableGroup();
                function3ModifierMaterializerOf = function111;
                $this$forEach$iv = $this$forEach$iv4;
                i3 = i117;
            }
            $composer2.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final Function1<? super AnimatedContentTransitionScope<S>, ContentTransform> function112 = function5;
        final Alignment alignment2 = contentAlignment3;
        final Function1<? super S, ? extends Object> function113 = function4;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedContentKt.AnimatedContent.9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) throws Throwable {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i118) throws Throwable {
                AnimatedContentKt.AnimatedContent(transition, modifier5, function112, alignment2, function113, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }
}
