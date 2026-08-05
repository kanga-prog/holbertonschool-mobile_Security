package androidx.compose.animation;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.animation.core.Transition;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AnimatedVisibility.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001ak\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0003¢\u0006\u0002\u0010\u0012\u001aR\u0010\u0000\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00130\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0083\b¢\u0006\u0002\u0010\u0014\u001aa\u0010\u0015\u001a\u00020\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u00172\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0002\u0010\u001a\u001aJ\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00072\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u001c¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u001d\u001a[\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0002\u0010\u001e\u001am\u0010\u0015\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0002\u0010\u001f\u001ae\u0010\u0015\u001a\u00020\u0001*\u00020 2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u00172\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0002\u0010!\u001a_\u0010\u0015\u001a\u00020\u0001*\u00020 2\u0006\u0010\u0005\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0002\u0010\"\u001ae\u0010\u0015\u001a\u00020\u0001*\u00020#2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u00172\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0002\u0010$\u001a_\u0010\u0015\u001a\u00020\u0001*\u00020#2\u0006\u0010\u0005\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0002\u0010%\u001a9\u0010&\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010'\u001a\u0002H\u0002H\u0003¢\u0006\u0002\u0010(¨\u0006)"}, d2 = {"AnimatedEnterExitImpl", "", "T", "transition", "Landroidx/compose/animation/core/Transition;", "visible", "Lkotlin/Function1;", "", "modifier", "Landroidx/compose/ui/Modifier;", "enter", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "content", "Landroidx/compose/animation/AnimatedVisibilityScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/animation/EnterExitState;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "AnimatedVisibility", "visibleState", "Landroidx/compose/animation/core/MutableTransitionState;", "label", "", "(Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "initiallyVisible", "Lkotlin/Function0;", "(ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "(ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/ColumnScope;", "(Landroidx/compose/foundation/layout/ColumnScope;Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/foundation/layout/ColumnScope;ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/RowScope;", "(Landroidx/compose/foundation/layout/RowScope;Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/foundation/layout/RowScope;ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "targetEnterExit", "targetState", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/EnterExitState;", "animation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AnimatedVisibilityKt {
    /* JADX WARN: Code duplicated, block: B:73:0x00e5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:74:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:75:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:78:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:79:0x010e  */
    /* JADX WARN: Code duplicated, block: B:81:0x0112  */
    /* JADX WARN: Code duplicated, block: B:82:0x012d  */
    /* JADX WARN: Code duplicated, block: B:84:0x0131  */
    /* JADX WARN: Code duplicated, block: B:87:0x013a  */
    /* JADX WARN: Code duplicated, block: B:90:0x0180  */
    /* JADX WARN: Code duplicated, block: B:94:0x018a  */
    /* JADX WARN: Code duplicated, block: B:96:? A[RETURN, SYNTHETIC] */
    public static final void AnimatedVisibility(final boolean visible, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exitTransition;
        String label2;
        int i2;
        int $dirty;
        Modifier modifier3;
        EnterTransition enter2;
        ExitTransition exit2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(2088733774);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)127@6727L32,128@6764L73:AnimatedVisibility.kt#xbi5r1");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(visible) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
            enterTransition = enter;
        } else if (($changed & 896) == 0) {
            enterTransition = enter;
            $dirty2 |= $composer2.changed(enterTransition) ? 256 : 128;
        } else {
            enterTransition = enter;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            exitTransition = exit;
        } else if (($changed & 7168) == 0) {
            exitTransition = exit;
            $dirty2 |= $composer2.changed(exitTransition) ? 2048 : 1024;
        } else {
            exitTransition = exit;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty2 |= 24576;
            label2 = label;
        } else if (($changed & 57344) == 0) {
            label2 = label;
            $dirty2 |= $composer2.changed(label2) ? 16384 : 8192;
        } else {
            label2 = label;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
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
                    enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
                } else {
                    enter2 = enterTransition;
                }
                if (i5 != 0) {
                    exit2 = EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
                } else {
                    exit2 = exitTransition;
                }
                if (i6 != 0) {
                    label2 = "AnimatedVisibility";
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2088733774, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:119)");
                }
                Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(visible), label2, $composer2, ($dirty & 14) | (($dirty >> 9) & 112), 0);
                AnimatedEnterExitImpl(transition, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                }, modifier3, enter2, exit2, content, $composer2, (($dirty << 3) & 896) | 48 | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | ($dirty & 458752));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                enter2 = enterTransition;
                exit2 = exitTransition;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final EnterTransition enterTransition2 = enter2;
            final ExitTransition exitTransition2 = exit2;
            final String str = label2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.2
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

                public final void invoke(Composer composer, int i7) {
                    AnimatedVisibilityKt.AnimatedVisibility(visible, modifier4, enterTransition2, exitTransition2, str, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
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
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
            } else {
                enter2 = enterTransition;
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            } else {
                exit2 = exitTransition;
            }
            if (i6 != 0) {
                label2 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2088733774, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:119)");
            }
            Transition transition2 = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(visible), label2, $composer2, ($dirty & 14) | (($dirty >> 9) & 112), 0);
            AnimatedEnterExitImpl(transition2, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.1
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter2, exit2, content, $composer2, (($dirty << 3) & 896) | 48 | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | ($dirty & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
            } else {
                enter2 = enterTransition;
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            } else {
                exit2 = exitTransition;
            }
            if (i6 != 0) {
                label2 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2088733774, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:119)");
            }
            Transition transition3 = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(visible), label2, $composer2, ($dirty & 14) | (($dirty >> 9) & 112), 0);
            AnimatedEnterExitImpl(transition3, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.1
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter2, exit2, content, $composer2, (($dirty << 3) & 896) | 48 | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | ($dirty & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final EnterTransition enterTransition3 = enter2;
        final ExitTransition exitTransition3 = exit2;
        final String str2 = label2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.2
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

            public final void invoke(Composer composer, int i7) {
                AnimatedVisibilityKt.AnimatedVisibility(visible, modifier5, enterTransition3, exitTransition3, str2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:73:0x00f8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:74:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:75:0x0101  */
    /* JADX WARN: Code duplicated, block: B:78:0x0108  */
    /* JADX WARN: Code duplicated, block: B:79:0x0123  */
    /* JADX WARN: Code duplicated, block: B:81:0x0127  */
    /* JADX WARN: Code duplicated, block: B:83:0x013d  */
    /* JADX WARN: Code duplicated, block: B:86:0x0146  */
    /* JADX WARN: Code duplicated, block: B:89:0x018e  */
    /* JADX WARN: Code duplicated, block: B:94:0x019b  */
    /* JADX WARN: Code duplicated, block: B:96:? A[RETURN, SYNTHETIC] */
    public static final void AnimatedVisibility(final RowScope $this$AnimatedVisibility, final boolean visible, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exit2;
        String label2;
        int i2;
        Modifier modifier3;
        EnterTransition enter2;
        String label3;
        ExitTransition exit3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter($this$AnimatedVisibility, "<this>");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-1741346906);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)202@11021L32,203@11058L73:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(visible) ? 32 : 16;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 896) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 3072;
            enterTransition = enter;
        } else if (($changed & 7168) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 2048 : 1024;
        } else {
            enterTransition = enter;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 24576;
            exit2 = exit;
        } else if (($changed & 57344) == 0) {
            exit2 = exit;
            $dirty |= $composer2.changed(exit2) ? 16384 : 8192;
        } else {
            exit2 = exit;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            label2 = label;
        } else if (($changed & 458752) == 0) {
            label2 = label;
            $dirty |= $composer2.changed(label2) ? 131072 : 65536;
        } else {
            label2 = label;
        }
        if ((i & 32) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(content) ? 1048576 : 524288;
            }
            if (($dirty & 2995921) == 599184 || !$composer2.getSkipping()) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandHorizontally$default(null, null, false, null, 15, null));
                } else {
                    enter2 = enterTransition;
                }
                if (i5 != 0) {
                    exit2 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkHorizontally$default(null, null, false, null, 15, null));
                }
                if (i6 != 0) {
                    label2 = "AnimatedVisibility";
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1741346906, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:194)");
                }
                Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(visible), label2, $composer2, (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
                AnimatedEnterExitImpl(transition, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.3
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                }, modifier3, enter2, exit2, content, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                label3 = label2;
                exit3 = exit2;
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                enter2 = enterTransition;
                label3 = label2;
                exit3 = exit2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final EnterTransition enterTransition2 = enter2;
            final ExitTransition exitTransition = exit3;
            final String str = label3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.4
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

                public final void invoke(Composer composer, int i7) {
                    AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, visible, modifier4, enterTransition2, exitTransition, str, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty |= i2;
        if (($dirty & 2995921) == 599184) {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandHorizontally$default(null, null, false, null, 15, null));
            } else {
                enter2 = enterTransition;
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkHorizontally$default(null, null, false, null, 15, null));
            }
            if (i6 != 0) {
                label2 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1741346906, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:194)");
            }
            Transition transition2 = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(visible), label2, $composer2, (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            AnimatedEnterExitImpl(transition2, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.3
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter2, exit2, content, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label3 = label2;
            exit3 = exit2;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandHorizontally$default(null, null, false, null, 15, null));
            } else {
                enter2 = enterTransition;
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkHorizontally$default(null, null, false, null, 15, null));
            }
            if (i6 != 0) {
                label2 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1741346906, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:194)");
            }
            Transition transition3 = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(visible), label2, $composer2, (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            AnimatedEnterExitImpl(transition3, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.3
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter2, exit2, content, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label3 = label2;
            exit3 = exit2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final EnterTransition enterTransition3 = enter2;
        final ExitTransition exitTransition2 = exit3;
        final String str2 = label3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.4
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

            public final void invoke(Composer composer, int i7) {
                AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, visible, modifier5, enterTransition3, exitTransition2, str2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:73:0x00f8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:74:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:75:0x0101  */
    /* JADX WARN: Code duplicated, block: B:78:0x0108  */
    /* JADX WARN: Code duplicated, block: B:79:0x0123  */
    /* JADX WARN: Code duplicated, block: B:81:0x0127  */
    /* JADX WARN: Code duplicated, block: B:83:0x013d  */
    /* JADX WARN: Code duplicated, block: B:86:0x0146  */
    /* JADX WARN: Code duplicated, block: B:89:0x018e  */
    /* JADX WARN: Code duplicated, block: B:94:0x019b  */
    /* JADX WARN: Code duplicated, block: B:96:? A[RETURN, SYNTHETIC] */
    public static final void AnimatedVisibility(final ColumnScope $this$AnimatedVisibility, final boolean visible, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exit2;
        String label2;
        int i2;
        Modifier modifier3;
        EnterTransition enter2;
        String label3;
        ExitTransition exit3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter($this$AnimatedVisibility, "<this>");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1766503102);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)275@15293L32,276@15330L73:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(visible) ? 32 : 16;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 896) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 3072;
            enterTransition = enter;
        } else if (($changed & 7168) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 2048 : 1024;
        } else {
            enterTransition = enter;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 24576;
            exit2 = exit;
        } else if (($changed & 57344) == 0) {
            exit2 = exit;
            $dirty |= $composer2.changed(exit2) ? 16384 : 8192;
        } else {
            exit2 = exit;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            label2 = label;
        } else if (($changed & 458752) == 0) {
            label2 = label;
            $dirty |= $composer2.changed(label2) ? 131072 : 65536;
        } else {
            label2 = label;
        }
        if ((i & 32) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(content) ? 1048576 : 524288;
            }
            if (($dirty & 2995921) == 599184 || !$composer2.getSkipping()) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null));
                } else {
                    enter2 = enterTransition;
                }
                if (i5 != 0) {
                    exit2 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null));
                }
                if (i6 != 0) {
                    label2 = "AnimatedVisibility";
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1766503102, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:267)");
                }
                Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(visible), label2, $composer2, (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
                AnimatedEnterExitImpl(transition, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.5
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                }, modifier3, enter2, exit2, content, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                label3 = label2;
                exit3 = exit2;
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                enter2 = enterTransition;
                label3 = label2;
                exit3 = exit2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final EnterTransition enterTransition2 = enter2;
            final ExitTransition exitTransition = exit3;
            final String str = label3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.6
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

                public final void invoke(Composer composer, int i7) {
                    AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, visible, modifier4, enterTransition2, exitTransition, str, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty |= i2;
        if (($dirty & 2995921) == 599184) {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null));
            } else {
                enter2 = enterTransition;
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null));
            }
            if (i6 != 0) {
                label2 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1766503102, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:267)");
            }
            Transition transition2 = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(visible), label2, $composer2, (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            AnimatedEnterExitImpl(transition2, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.5
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter2, exit2, content, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label3 = label2;
            exit3 = exit2;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null));
            } else {
                enter2 = enterTransition;
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null));
            }
            if (i6 != 0) {
                label2 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1766503102, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:267)");
            }
            Transition transition3 = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(visible), label2, $composer2, (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            AnimatedEnterExitImpl(transition3, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.5
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter2, exit2, content, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label3 = label2;
            exit3 = exit2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final EnterTransition enterTransition3 = enter2;
        final ExitTransition exitTransition2 = exit3;
        final String str2 = label3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.6
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

            public final void invoke(Composer composer, int i7) {
                AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, visible, modifier5, enterTransition3, exitTransition2, str2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:72:0x00e6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:77:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:78:0x010f  */
    /* JADX WARN: Code duplicated, block: B:80:0x0113  */
    /* JADX WARN: Code duplicated, block: B:81:0x0129  */
    /* JADX WARN: Code duplicated, block: B:83:0x012d  */
    /* JADX WARN: Code duplicated, block: B:86:0x0136  */
    /* JADX WARN: Code duplicated, block: B:89:0x017b  */
    /* JADX WARN: Code duplicated, block: B:93:0x0185  */
    /* JADX WARN: Code duplicated, block: B:95:? A[RETURN, SYNTHETIC] */
    public static final void AnimatedVisibility(final MutableTransitionState<Boolean> visibleState, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exitTransition;
        String label2;
        int i2;
        int $dirty;
        Modifier modifier3;
        EnterTransition enter2;
        ExitTransition exit2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(visibleState, "visibleState");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-222898426);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)381@20708L37,382@20750L73:AnimatedVisibility.kt#xbi5r1");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(visibleState) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
            enterTransition = enter;
        } else if (($changed & 896) == 0) {
            enterTransition = enter;
            $dirty2 |= $composer2.changed(enterTransition) ? 256 : 128;
        } else {
            enterTransition = enter;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            exitTransition = exit;
        } else if (($changed & 7168) == 0) {
            exitTransition = exit;
            $dirty2 |= $composer2.changed(exitTransition) ? 2048 : 1024;
        } else {
            exitTransition = exit;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty2 |= 24576;
            label2 = label;
        } else if (($changed & 57344) == 0) {
            label2 = label;
            $dirty2 |= $composer2.changed(label2) ? 16384 : 8192;
        } else {
            label2 = label;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
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
                    enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
                } else {
                    enter2 = enterTransition;
                }
                if (i5 != 0) {
                    exit2 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null));
                } else {
                    exit2 = exitTransition;
                }
                if (i6 != 0) {
                    label2 = "AnimatedVisibility";
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-222898426, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:373)");
                }
                Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition((MutableTransitionState) visibleState, label2, $composer2, MutableTransitionState.$stable | ($dirty & 14) | (($dirty >> 9) & 112), 0);
                AnimatedEnterExitImpl(transition, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.7
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                }, modifier3, enter2, exit2, content, $composer2, (($dirty << 3) & 896) | 48 | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | ($dirty & 458752));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                enter2 = enterTransition;
                exit2 = exitTransition;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final EnterTransition enterTransition2 = enter2;
            final ExitTransition exitTransition2 = exit2;
            final String str = label2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.8
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

                public final void invoke(Composer composer, int i7) {
                    AnimatedVisibilityKt.AnimatedVisibility(visibleState, modifier4, enterTransition2, exitTransition2, str, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
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
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
            } else {
                enter2 = enterTransition;
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null));
            } else {
                exit2 = exitTransition;
            }
            if (i6 != 0) {
                label2 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-222898426, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:373)");
            }
            Transition transition2 = androidx.compose.animation.core.TransitionKt.updateTransition((MutableTransitionState) visibleState, label2, $composer2, MutableTransitionState.$stable | ($dirty & 14) | (($dirty >> 9) & 112), 0);
            AnimatedEnterExitImpl(transition2, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.7
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter2, exit2, content, $composer2, (($dirty << 3) & 896) | 48 | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | ($dirty & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
            } else {
                enter2 = enterTransition;
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null));
            } else {
                exit2 = exitTransition;
            }
            if (i6 != 0) {
                label2 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-222898426, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:373)");
            }
            Transition transition3 = androidx.compose.animation.core.TransitionKt.updateTransition((MutableTransitionState) visibleState, label2, $composer2, MutableTransitionState.$stable | ($dirty & 14) | (($dirty >> 9) & 112), 0);
            AnimatedEnterExitImpl(transition3, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.7
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter2, exit2, content, $composer2, (($dirty << 3) & 896) | 48 | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | ($dirty & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final EnterTransition enterTransition3 = enter2;
        final ExitTransition exitTransition3 = exit2;
        final String str2 = label2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.8
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

            public final void invoke(Composer composer, int i7) {
                AnimatedVisibilityKt.AnimatedVisibility(visibleState, modifier5, enterTransition3, exitTransition3, str2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:72:0x00f9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:74:0x0102  */
    /* JADX WARN: Code duplicated, block: B:77:0x0109  */
    /* JADX WARN: Code duplicated, block: B:78:0x0124  */
    /* JADX WARN: Code duplicated, block: B:80:0x0128  */
    /* JADX WARN: Code duplicated, block: B:82:0x0143  */
    /* JADX WARN: Code duplicated, block: B:85:0x014c  */
    /* JADX WARN: Code duplicated, block: B:88:0x0193  */
    /* JADX WARN: Code duplicated, block: B:93:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:95:? A[RETURN, SYNTHETIC] */
    public static final void AnimatedVisibility(final RowScope $this$AnimatedVisibility, final MutableTransitionState<Boolean> visibleState, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exit2;
        String label2;
        int i2;
        Modifier modifier3;
        EnterTransition enter2;
        String label3;
        ExitTransition exit3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter($this$AnimatedVisibility, "<this>");
        Intrinsics.checkNotNullParameter(visibleState, "visibleState");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(836509870);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)456@25058L37,457@25100L73:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(visibleState) ? 32 : 16;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 896) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 3072;
            enterTransition = enter;
        } else if (($changed & 7168) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 2048 : 1024;
        } else {
            enterTransition = enter;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 24576;
            exit2 = exit;
        } else if (($changed & 57344) == 0) {
            exit2 = exit;
            $dirty |= $composer2.changed(exit2) ? 16384 : 8192;
        } else {
            exit2 = exit;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            label2 = label;
        } else if (($changed & 458752) == 0) {
            label2 = label;
            $dirty |= $composer2.changed(label2) ? 131072 : 65536;
        } else {
            label2 = label;
        }
        if ((i & 32) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(content) ? 1048576 : 524288;
            }
            if (($dirty & 2995921) == 599184 || !$composer2.getSkipping()) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enter2 = EnterExitTransitionKt.expandHorizontally$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
                } else {
                    enter2 = enterTransition;
                }
                if (i5 != 0) {
                    exit2 = EnterExitTransitionKt.shrinkHorizontally$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
                }
                if (i6 != 0) {
                    label2 = "AnimatedVisibility";
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(836509870, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:448)");
                }
                Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition((MutableTransitionState) visibleState, label2, $composer2, MutableTransitionState.$stable | (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
                AnimatedEnterExitImpl(transition, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.9
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                }, modifier3, enter2, exit2, content, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                label3 = label2;
                exit3 = exit2;
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                enter2 = enterTransition;
                label3 = label2;
                exit3 = exit2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final EnterTransition enterTransition2 = enter2;
            final ExitTransition exitTransition = exit3;
            final String str = label3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.10
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

                public final void invoke(Composer composer, int i7) {
                    AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, visibleState, modifier4, enterTransition2, exitTransition, str, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty |= i2;
        if (($dirty & 2995921) == 599184) {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                enter2 = EnterExitTransitionKt.expandHorizontally$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
            } else {
                enter2 = enterTransition;
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.shrinkHorizontally$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            }
            if (i6 != 0) {
                label2 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(836509870, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:448)");
            }
            Transition transition2 = androidx.compose.animation.core.TransitionKt.updateTransition((MutableTransitionState) visibleState, label2, $composer2, MutableTransitionState.$stable | (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            AnimatedEnterExitImpl(transition2, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.9
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter2, exit2, content, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label3 = label2;
            exit3 = exit2;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                enter2 = EnterExitTransitionKt.expandHorizontally$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
            } else {
                enter2 = enterTransition;
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.shrinkHorizontally$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            }
            if (i6 != 0) {
                label2 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(836509870, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:448)");
            }
            Transition transition3 = androidx.compose.animation.core.TransitionKt.updateTransition((MutableTransitionState) visibleState, label2, $composer2, MutableTransitionState.$stable | (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            AnimatedEnterExitImpl(transition3, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.9
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter2, exit2, content, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label3 = label2;
            exit3 = exit2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final EnterTransition enterTransition3 = enter2;
        final ExitTransition exitTransition2 = exit3;
        final String str2 = label3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.10
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

            public final void invoke(Composer composer, int i7) {
                AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, visibleState, modifier5, enterTransition3, exitTransition2, str2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:72:0x00f9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:74:0x0102  */
    /* JADX WARN: Code duplicated, block: B:77:0x0109  */
    /* JADX WARN: Code duplicated, block: B:78:0x0124  */
    /* JADX WARN: Code duplicated, block: B:80:0x0128  */
    /* JADX WARN: Code duplicated, block: B:82:0x0143  */
    /* JADX WARN: Code duplicated, block: B:85:0x014c  */
    /* JADX WARN: Code duplicated, block: B:88:0x0193  */
    /* JADX WARN: Code duplicated, block: B:93:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:95:? A[RETURN, SYNTHETIC] */
    public static final void AnimatedVisibility(final ColumnScope $this$AnimatedVisibility, final MutableTransitionState<Boolean> visibleState, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exit2;
        String label2;
        int i2;
        Modifier modifier3;
        EnterTransition enter2;
        String label3;
        ExitTransition exit3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter($this$AnimatedVisibility, "<this>");
        Intrinsics.checkNotNullParameter(visibleState, "visibleState");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-850656618);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)532@29500L37,533@29542L73:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(visibleState) ? 32 : 16;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 896) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 3072;
            enterTransition = enter;
        } else if (($changed & 7168) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 2048 : 1024;
        } else {
            enterTransition = enter;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 24576;
            exit2 = exit;
        } else if (($changed & 57344) == 0) {
            exit2 = exit;
            $dirty |= $composer2.changed(exit2) ? 16384 : 8192;
        } else {
            exit2 = exit;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            label2 = label;
        } else if (($changed & 458752) == 0) {
            label2 = label;
            $dirty |= $composer2.changed(label2) ? 131072 : 65536;
        } else {
            label2 = label;
        }
        if ((i & 32) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(content) ? 1048576 : 524288;
            }
            if (($dirty & 2995921) == 599184 || !$composer2.getSkipping()) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    enter2 = EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
                } else {
                    enter2 = enterTransition;
                }
                if (i5 != 0) {
                    exit2 = EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
                }
                if (i6 != 0) {
                    label2 = "AnimatedVisibility";
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-850656618, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:524)");
                }
                Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition((MutableTransitionState) visibleState, label2, $composer2, MutableTransitionState.$stable | (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
                AnimatedEnterExitImpl(transition, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.11
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                }, modifier3, enter2, exit2, content, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                label3 = label2;
                exit3 = exit2;
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                enter2 = enterTransition;
                label3 = label2;
                exit3 = exit2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final EnterTransition enterTransition2 = enter2;
            final ExitTransition exitTransition = exit3;
            final String str = label3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.12
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

                public final void invoke(Composer composer, int i7) {
                    AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, visibleState, modifier4, enterTransition2, exitTransition, str, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty |= i2;
        if (($dirty & 2995921) == 599184) {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                enter2 = EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
            } else {
                enter2 = enterTransition;
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            }
            if (i6 != 0) {
                label2 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-850656618, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:524)");
            }
            Transition transition2 = androidx.compose.animation.core.TransitionKt.updateTransition((MutableTransitionState) visibleState, label2, $composer2, MutableTransitionState.$stable | (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            AnimatedEnterExitImpl(transition2, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.11
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter2, exit2, content, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label3 = label2;
            exit3 = exit2;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                enter2 = EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
            } else {
                enter2 = enterTransition;
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            }
            if (i6 != 0) {
                label2 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-850656618, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:524)");
            }
            Transition transition3 = androidx.compose.animation.core.TransitionKt.updateTransition((MutableTransitionState) visibleState, label2, $composer2, MutableTransitionState.$stable | (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            AnimatedEnterExitImpl(transition3, new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.11
                public final Boolean invoke(boolean it) {
                    return Boolean.valueOf(it);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                    return invoke(bool.booleanValue());
                }
            }, modifier3, enter2, exit2, content, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label3 = label2;
            exit3 = exit2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final EnterTransition enterTransition3 = enter2;
        final ExitTransition exitTransition2 = exit3;
        final String str2 = label3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.12
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

            public final void invoke(Composer composer, int i7) {
                AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, visibleState, modifier5, enterTransition3, exitTransition2, str2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:71:0x00e8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:72:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:73:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:76:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:77:0x0111  */
    /* JADX WARN: Code duplicated, block: B:79:0x0115  */
    /* JADX WARN: Code duplicated, block: B:82:0x0134  */
    /* JADX WARN: Code duplicated, block: B:85:0x0163  */
    /* JADX WARN: Code duplicated, block: B:90:0x016f  */
    /* JADX WARN: Code duplicated, block: B:92:? A[RETURN, SYNTHETIC] */
    public static final <T> void AnimatedVisibility(final Transition<T> transition, final Function1<? super T, Boolean> visible, Modifier modifier, EnterTransition enter, ExitTransition exit, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exit2;
        int i2;
        int $dirty;
        Modifier modifier3;
        EnterTransition enter2;
        ExitTransition exit3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(visible, "visible");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1031950689);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(4,3,1,2)606@33860L68:AnimatedVisibility.kt#xbi5r1");
        int $dirty2 = $changed;
        if ((i & Integer.MIN_VALUE) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(transition) ? 4 : 2;
        }
        if ((i & 1) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changedInstance(visible) ? 32 : 16;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 896) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 3072;
            enterTransition = enter;
        } else if (($changed & 7168) == 0) {
            enterTransition = enter;
            $dirty2 |= $composer2.changed(enterTransition) ? 2048 : 1024;
        } else {
            enterTransition = enter;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 24576;
            exit2 = exit;
        } else if (($changed & 57344) == 0) {
            exit2 = exit;
            $dirty2 |= $composer2.changed(exit2) ? 16384 : 8192;
        } else {
            exit2 = exit;
        }
        if ((i & 16) == 0) {
            if (($changed & 458752) == 0) {
                i2 = $composer2.changedInstance(content) ? 131072 : 65536;
            }
            $dirty = $dirty2;
            if ((374491 & $dirty) == 74898 || !$composer2.getSkipping()) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 == 0) {
                    enter2 = enterTransition;
                } else {
                    enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
                }
                if (i5 != 0) {
                    exit2 = EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1031950689, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:600)");
                }
                AnimatedEnterExitImpl(transition, visible, modifier3, enter2, exit2, content, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                exit3 = exit2;
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                enter2 = enterTransition;
                exit3 = exit2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final EnterTransition enterTransition2 = enter2;
            final ExitTransition exitTransition = exit3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.13
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
                    AnimatedVisibilityKt.AnimatedVisibility(transition, visible, modifier4, enterTransition2, exitTransition, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
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
            if (i4 == 0) {
                enter2 = enterTransition;
            } else {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1031950689, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:600)");
            }
            AnimatedEnterExitImpl(transition, visible, modifier3, enter2, exit2, content, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            exit3 = exit2;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 == 0) {
                enter2 = enterTransition;
            } else {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
            }
            if (i5 != 0) {
                exit2 = EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1031950689, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:600)");
            }
            AnimatedEnterExitImpl(transition, visible, modifier3, enter2, exit2, content, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            exit3 = exit2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final EnterTransition enterTransition3 = enter2;
        final ExitTransition exitTransition2 = exit3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.13
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
                AnimatedVisibilityKt.AnimatedVisibility(transition, visible, modifier5, enterTransition3, exitTransition2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    @Deprecated(message = "AnimatedVisibility no longer accepts initiallyVisible as a parameter, please use AnimatedVisibility(MutableTransitionState, Modifier, ...) API instead", replaceWith = @ReplaceWith(expression = "AnimatedVisibility(transitionState = remember { MutableTransitionState(initiallyVisible) }\n.apply { targetState = visible },\nmodifier = modifier,\nenter = enter,\nexit = exit) {\ncontent() \n}", imports = {"androidx.compose.animation.core.MutableTransitionState"}))
    public static final void AnimatedVisibility(final boolean visible, Modifier modifier, final EnterTransition enter, final ExitTransition exit, final boolean initiallyVisible, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Object value$iv$iv;
        Intrinsics.checkNotNullParameter(enter, "enter");
        Intrinsics.checkNotNullParameter(exit, "exit");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1121582420);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)*709@38686L53,708@38647L214:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(visible) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(enter) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(exit) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if ((57344 & $changed) == 0) {
            $dirty |= $composer2.changed(initiallyVisible) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((458752 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 131072 : 65536;
        }
        final int $dirty2 = $dirty;
        if ((374491 & $dirty2) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1121582420, $dirty2, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:701)");
            }
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new MutableTransitionState(Boolean.valueOf(initiallyVisible));
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            MutableTransitionState $this$AnimatedVisibility_u24lambda_u241 = (MutableTransitionState) value$iv$iv;
            $this$AnimatedVisibility_u24lambda_u241.setTargetState(Boolean.valueOf(visible));
            AnimatedVisibility((MutableTransitionState<Boolean>) value$iv$iv, modifier3, enter, exit, (String) null, ComposableLambdaKt.composableLambda($composer2, 1996320812, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.16
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

                public final void invoke(AnimatedVisibilityScope AnimatedVisibility, Composer $composer3, int $changed2) {
                    Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
                    ComposerKt.sourceInformation($composer3, "C715@38850L9:AnimatedVisibility.kt#xbi5r1");
                    if (($changed2 & 81) == 16 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1996320812, $changed2, -1, "androidx.compose.animation.AnimatedVisibility.<anonymous> (AnimatedVisibility.kt:714)");
                    }
                    content.invoke($composer3, Integer.valueOf(($dirty2 >> 15) & 14));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, MutableTransitionState.$stable | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier4 = modifier3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.17
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
                AnimatedVisibilityKt.AnimatedVisibility(visible, modifier4, enter, exit, initiallyVisible, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> void AnimatedEnterExitImpl(final Transition<T> transition, final Function1<? super T, Boolean> function1, final Modifier modifier, final EnterTransition enter, final ExitTransition exit, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Object value$iv$iv;
        Object value$iv$iv$iv;
        AnimatedVisibilityKt$AnimatedEnterExitImpl$1$1 value$iv$iv2;
        Object value$iv$iv$iv2;
        Object value$iv$iv$iv3;
        Composer $composer2 = $composer.startRestartGroup(808253933);
        ComposerKt.sourceInformation($composer2, "C(AnimatedEnterExitImpl)P(4,5,3,1,2)734@39380L85,739@39603L116,743@39761L270,743@39729L302,752@40041L165:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(transition) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(modifier) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changed(enter) ? 2048 : 1024;
        }
        if (($changed & 57344) == 0) {
            $dirty |= $composer2.changed(exit) ? 16384 : 8192;
        }
        if ((458752 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 131072 : 65536;
        }
        int $dirty2 = $dirty;
        if ((374491 & $dirty2) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(808253933, $dirty2, -1, "androidx.compose.animation.AnimatedEnterExitImpl (AnimatedVisibility.kt:726)");
            }
            int i = $dirty2 & 14;
            $composer2.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(transition);
            Object it$iv$iv = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(function1.invoke(transition.getCurrentState()), null, 2, null);
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            MutableState isAnimationVisible = (MutableState) value$iv$iv;
            if (function1.invoke(transition.getTargetState()).booleanValue() || ((Boolean) isAnimationVisible.getValue()).booleanValue() || transition.isSeeking()) {
                int $changed$iv = ($dirty2 & 14) | 48;
                $composer2.startReplaceableGroup(1215497572);
                ComposerKt.sourceInformation($composer2, "CC(createChildTransition)786@31174L36,787@31234L74,788@31331L39,789@31382L63:Transition.kt#pdpnli");
                int i2 = $changed$iv & 14;
                $composer2.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean invalid$iv$iv$iv = $composer2.changed(transition);
                Object it$iv$iv$iv = $composer2.rememberedValue();
                if (invalid$iv$iv$iv || it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv$iv = transition.getCurrentState();
                    $composer2.updateRememberedValue(value$iv$iv$iv);
                } else {
                    value$iv$iv$iv = it$iv$iv$iv;
                }
                $composer2.endReplaceableGroup();
                Object it = transition.isSeeking() ? transition.getCurrentState() : value$iv$iv$iv;
                int $changed2 = ($changed$iv >> 3) & 112;
                $composer2.startReplaceableGroup(-1220581778);
                ComposerKt.sourceInformation($composer2, "C740@39681L28:AnimatedVisibility.kt#xbi5r1");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1220581778, $changed2, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:739)");
                }
                Object initialState$iv = targetEnterExit(transition, function1, it, $composer2, ($dirty2 & 14) | ($dirty2 & 112) | (($changed2 << 6) & 896));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer2.endReplaceableGroup();
                Object it2 = transition.getTargetState();
                int $changed3 = ($changed$iv >> 3) & 112;
                $composer2.startReplaceableGroup(-1220581778);
                ComposerKt.sourceInformation($composer2, "C740@39681L28:AnimatedVisibility.kt#xbi5r1");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1220581778, $changed3, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:739)");
                }
                Object targetState$iv = targetEnterExit(transition, function1, it2, $composer2, ($dirty2 & 14) | ($dirty2 & 112) | (($changed3 << 6) & 896));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer2.endReplaceableGroup();
                Transition childTransition = androidx.compose.animation.core.TransitionKt.createChildTransitionInternal(transition, initialState$iv, targetState$iv, "EnterExitTransition", $composer2, ($changed$iv & 14) | (($changed$iv << 6) & 7168));
                $composer2.endReplaceableGroup();
                $composer2.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                boolean invalid$iv$iv2 = $composer2.changed(childTransition) | $composer2.changed(isAnimationVisible);
                Object it$iv$iv2 = $composer2.rememberedValue();
                if (invalid$iv$iv2 || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = new AnimatedVisibilityKt$AnimatedEnterExitImpl$1$1(childTransition, isAnimationVisible, null);
                    $composer2.updateRememberedValue(value$iv$iv2);
                } else {
                    value$iv$iv2 = it$iv$iv2;
                }
                $composer2.endReplaceableGroup();
                EffectsKt.LaunchedEffect(childTransition, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv$iv2, $composer2, 64);
                int $changed$iv2 = (($dirty2 >> 3) & 112) | (($dirty2 >> 3) & 896) | (($dirty2 >> 3) & 7168) | (($dirty2 >> 3) & 57344);
                $composer2.startReplaceableGroup(-1967270694);
                ComposerKt.sourceInformation($composer2, "CC(AnimatedEnterExitImpl)P(4,3,1,2)777@40847L64,780@41019L39,781@41089L50,778@40920L229:AnimatedVisibility.kt#xbi5r1");
                if (childTransition.getCurrentState() == EnterExitState.Visible || childTransition.getTargetState() == EnterExitState.Visible) {
                    int i3 = $changed$iv2 & 14;
                    $composer2.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation($composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                    boolean invalid$iv$iv$iv2 = $composer2.changed(childTransition);
                    Object it$iv$iv$iv2 = $composer2.rememberedValue();
                    if (invalid$iv$iv$iv2 || it$iv$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv$iv2 = new AnimatedVisibilityScopeImpl(childTransition);
                        $composer2.updateRememberedValue(value$iv$iv$iv2);
                    } else {
                        value$iv$iv$iv2 = it$iv$iv$iv2;
                    }
                    $composer2.endReplaceableGroup();
                    AnimatedVisibilityScopeImpl scope$iv = (AnimatedVisibilityScopeImpl) value$iv$iv$iv2;
                    Modifier modifier$iv$iv = modifier.then(EnterExitTransitionKt.createModifier(childTransition, enter, exit, "Built-in", $composer2, ($changed$iv2 & 14) | 3072 | (($changed$iv2 >> 3) & 112) | (($changed$iv2 >> 3) & 896)));
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    Object it$iv$iv$iv3 = $composer2.rememberedValue();
                    if (it$iv$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv$iv3 = new AnimatedEnterExitMeasurePolicy(scope$iv);
                        $composer2.updateRememberedValue(value$iv$iv$iv3);
                    } else {
                        value$iv$iv$iv3 = it$iv$iv$iv3;
                    }
                    $composer2.endReplaceableGroup();
                    MeasurePolicy measurePolicy$iv$iv = (MeasurePolicy) value$iv$iv$iv3;
                    $composer2.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv$iv);
                    int $changed$iv$iv$iv = ((384 << 9) & 7168) | 6;
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        $composer2.createNode(constructor);
                    } else {
                        $composer2.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer2);
                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                    $composer2.startReplaceableGroup(2058660585);
                    int i4 = ($changed$iv$iv$iv >> 9) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -174037743, "C779@40958L9:AnimatedVisibility.kt#xbi5r1");
                    function3.invoke(scope$iv, $composer2, Integer.valueOf((($changed$iv2 >> 9) & 112) | 8));
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endReplaceableGroup();
                    $composer2.endNode();
                    $composer2.endReplaceableGroup();
                }
                $composer2.endReplaceableGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedEnterExitImpl.2
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

            public final void invoke(Composer composer, int i5) {
                AnimatedVisibilityKt.AnimatedEnterExitImpl(transition, function1, modifier, enter, exit, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    private static final void AnimatedEnterExitImpl(Transition<EnterExitState> transition, Modifier modifier, EnterTransition enter, ExitTransition exit, Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, int $changed) {
        Object value$iv$iv;
        Object value$iv$iv2;
        $composer.startReplaceableGroup(-1967270694);
        ComposerKt.sourceInformation($composer, "CC(AnimatedEnterExitImpl)P(4,3,1,2)777@40847L64,780@41019L39,781@41089L50,778@40920L229:AnimatedVisibility.kt#xbi5r1");
        if (transition.getCurrentState() == EnterExitState.Visible || transition.getTargetState() == EnterExitState.Visible) {
            int i = $changed & 14;
            $composer.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer.changed(transition);
            Object it$iv$iv = $composer.rememberedValue();
            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = new AnimatedVisibilityScopeImpl(transition);
                $composer.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer.endReplaceableGroup();
            AnimatedVisibilityScopeImpl scope = (AnimatedVisibilityScopeImpl) value$iv$iv;
            Modifier modifier$iv = modifier.then(EnterExitTransitionKt.createModifier(transition, enter, exit, "Built-in", $composer, ($changed & 14) | 3072 | (($changed >> 3) & 112) | (($changed >> 3) & 896)));
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv2 = $composer.rememberedValue();
            if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new AnimatedEnterExitMeasurePolicy(scope);
                $composer.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer.endReplaceableGroup();
            MeasurePolicy measurePolicy$iv = (MeasurePolicy) value$iv$iv2;
            $composer.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv = $composer.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
            int $changed$iv$iv = ((384 << 9) & 7168) | 6;
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m2603constructorimpl($composer);
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer)), $composer, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            $composer.startReplaceableGroup(2058660585);
            int i2 = ($changed$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -174037743, "C779@40958L9:AnimatedVisibility.kt#xbi5r1");
            function3.invoke(scope, $composer, Integer.valueOf((($changed >> 9) & 112) | 8));
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endReplaceableGroup();
            $composer.endNode();
            $composer.endReplaceableGroup();
        }
        $composer.endReplaceableGroup();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> EnterExitState targetEnterExit(Transition<T> transition, Function1<? super T, Boolean> function1, T t, Composer $composer, int $changed) {
        Object value$iv$iv;
        EnterExitState enterExitState;
        $composer.startReplaceableGroup(361571134);
        ComposerKt.sourceInformation($composer, "C(targetEnterExit)P(1):AnimatedVisibility.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(361571134, $changed, -1, "androidx.compose.animation.targetEnterExit (AnimatedVisibility.kt:830)");
        }
        $composer.startMovableGroup(-721837504, transition);
        ComposerKt.sourceInformation($composer, "846@43297L34");
        if (transition.isSeeking()) {
            if (function1.invoke(t).booleanValue()) {
                enterExitState = EnterExitState.Visible;
            } else if (function1.invoke(transition.getCurrentState()).booleanValue()) {
                enterExitState = EnterExitState.PostExit;
            } else {
                enterExitState = EnterExitState.PreEnter;
            }
        } else {
            $composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer.endReplaceableGroup();
            MutableState hasBeenVisible = (MutableState) value$iv$iv;
            if (function1.invoke(transition.getCurrentState()).booleanValue()) {
                hasBeenVisible.setValue(true);
            }
            if (function1.invoke(t).booleanValue()) {
                enterExitState = EnterExitState.Visible;
            } else if (((Boolean) hasBeenVisible.getValue()).booleanValue()) {
                enterExitState = EnterExitState.PostExit;
            } else {
                enterExitState = EnterExitState.PreEnter;
            }
        }
        $composer.endMovableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return enterExitState;
    }
}
