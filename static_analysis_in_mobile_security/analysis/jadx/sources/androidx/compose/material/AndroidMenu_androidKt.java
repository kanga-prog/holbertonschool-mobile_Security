package androidx.compose.material;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
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
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidMenu.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aq\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014\u001ag\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001aa\u0010\u0017\u001a\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0007¢\u0006\u0002\u0010\u001f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006 "}, d2 = {"DropdownMenu", "", "expanded", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "offset", "Landroidx/compose/ui/unit/DpOffset;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "properties", "Landroidx/compose/ui/window/PopupProperties;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "DropdownMenu-4kj-_NE", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/ScrollState;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenu-ILWXrKs", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenuItem", "onClick", "enabled", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Landroidx/compose/foundation/layout/RowScope;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidMenu_androidKt {
    /* JADX WARN: Code duplicated, block: B:72:0x00e8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:74:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:76:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:77:0x010a  */
    /* JADX WARN: Code duplicated, block: B:79:0x010e  */
    /* JADX WARN: Code duplicated, block: B:80:0x0126  */
    /* JADX WARN: Code duplicated, block: B:83:0x012e  */
    /* JADX WARN: Code duplicated, block: B:86:0x0174  */
    /* JADX WARN: Code duplicated, block: B:90:0x017e  */
    /* JADX WARN: Code duplicated, block: B:92:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced by a DropdownMenu function with a ScrollState parameter", replaceWith = @ReplaceWith(expression = "DropdownMenu(expanded,onDismissRequest, modifier, offset, rememberScrollState(), properties, content)", imports = {"androidx.compose.foundation.rememberScrollState"}))
    /* JADX INFO: renamed from: DropdownMenu-ILWXrKs, reason: not valid java name */
    public static final /* synthetic */ void m974DropdownMenuILWXrKs(final boolean expanded, final Function0 onDismissRequest, Modifier modifier, long offset, PopupProperties properties, final Function3 content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        PopupProperties popupProperties;
        int i2;
        int $dirty;
        Modifier modifier3;
        long offset2;
        PopupProperties properties2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onDismissRequest, "onDismissRequest");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-840283139);
        ComposerKt.sourceInformation($composer2, "C(DropdownMenu)P(1,4,2,3:c#ui.unit.DpOffset,5)96@4692L21,91@4548L219:AndroidMenu.android.kt#jmzs0o");
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
            popupProperties = properties;
        } else if ((57344 & $changed) == 0) {
            popupProperties = properties;
            $dirty2 |= $composer2.changed(popupProperties) ? 16384 : 8192;
        } else {
            popupProperties = properties;
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
                    offset2 = DpKt.m5295DpOffsetYgX7TsA(Dp.m5274constructorimpl(0), Dp.m5274constructorimpl(0));
                } else {
                    offset2 = j;
                }
                if (i5 != 0) {
                    properties2 = new PopupProperties(true, false, false, null, false, false, 62, null);
                } else {
                    properties2 = popupProperties;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-840283139, $dirty, -1, "androidx.compose.material.DropdownMenu (AndroidMenu.android.kt:84)");
                }
                m973DropdownMenu4kj_NE(expanded, onDismissRequest, modifier3, offset2, ScrollKt.rememberScrollState(0, $composer2, 0, 1), properties2, content, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752) | (3670016 & ($dirty << 3)), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                offset2 = j;
                properties2 = popupProperties;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final long j2 = offset2;
            final PopupProperties popupProperties2 = properties2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$1
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
                    AndroidMenu_androidKt.m974DropdownMenuILWXrKs(expanded, onDismissRequest, modifier4, j2, popupProperties2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
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
            } else {
                properties2 = popupProperties;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-840283139, $dirty, -1, "androidx.compose.material.DropdownMenu (AndroidMenu.android.kt:84)");
            }
            m973DropdownMenu4kj_NE(expanded, onDismissRequest, modifier3, offset2, ScrollKt.rememberScrollState(0, $composer2, 0, 1), properties2, content, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752) | (3670016 & ($dirty << 3)), 0);
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
                offset2 = DpKt.m5295DpOffsetYgX7TsA(Dp.m5274constructorimpl(0), Dp.m5274constructorimpl(0));
            } else {
                offset2 = j;
            }
            if (i5 != 0) {
                properties2 = new PopupProperties(true, false, false, null, false, false, 62, null);
            } else {
                properties2 = popupProperties;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-840283139, $dirty, -1, "androidx.compose.material.DropdownMenu (AndroidMenu.android.kt:84)");
            }
            m973DropdownMenu4kj_NE(expanded, onDismissRequest, modifier3, offset2, ScrollKt.rememberScrollState(0, $composer2, 0, 1), properties2, content, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752) | (3670016 & ($dirty << 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final long j3 = offset2;
        final PopupProperties popupProperties3 = properties2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$1
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
                AndroidMenu_androidKt.m974DropdownMenuILWXrKs(expanded, onDismissRequest, modifier5, j3, popupProperties3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0157  */
    /* JADX WARN: Code duplicated, block: B:102:0x015a  */
    /* JADX WARN: Code duplicated, block: B:103:0x0178  */
    /* JADX WARN: Code duplicated, block: B:106:0x0187  */
    /* JADX WARN: Code duplicated, block: B:109:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:110:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:113:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:117:0x0201  */
    /* JADX WARN: Code duplicated, block: B:119:0x0221  */
    /* JADX WARN: Code duplicated, block: B:120:0x023e  */
    /* JADX WARN: Code duplicated, block: B:123:0x0288  */
    /* JADX WARN: Code duplicated, block: B:127:0x0296 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:132:0x0300  */
    /* JADX WARN: Code duplicated, block: B:137:0x0312  */
    /* JADX WARN: Code duplicated, block: B:139:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x0109  */
    /* JADX WARN: Code duplicated, block: B:85:0x0110  */
    /* JADX WARN: Code duplicated, block: B:92:0x012a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:93:0x012c  */
    /* JADX WARN: Code duplicated, block: B:94:0x0131  */
    /* JADX WARN: Code duplicated, block: B:96:0x0134  */
    /* JADX WARN: Code duplicated, block: B:99:0x014b  */
    /* JADX INFO: renamed from: DropdownMenu-4kj-_NE, reason: not valid java name */
    public static final void m973DropdownMenu4kj_NE(final boolean expanded, final Function0<Unit> onDismissRequest, Modifier modifier, long offset, ScrollState scrollState, PopupProperties properties, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long offset2;
        ScrollState scrollState2;
        PopupProperties popupProperties;
        int i2;
        Modifier.Companion modifier3;
        PopupProperties properties2;
        ScrollState scrollState3;
        long offset3;
        Modifier modifier4;
        final int $dirty;
        Object it$iv$iv;
        Object value$iv$iv;
        final MutableTransitionState expandedStates;
        Object value$iv$iv2;
        boolean invalid$iv$iv;
        Object value$iv$iv3;
        Modifier modifier5;
        ScrollState scrollState4;
        PopupProperties properties3;
        long offset4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onDismissRequest, "onDismissRequest");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-2135362555);
        ComposerKt.sourceInformation($composer2, "C(DropdownMenu)P(1,4,2,3:c#ui.unit.DpOffset,6,5)151@7781L21,155@7950L42,159@8140L51,160@8227L7,164@8351L131,168@8492L443:AndroidMenu.android.kt#jmzs0o");
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
            offset2 = offset;
        } else if (($changed & 7168) == 0) {
            offset2 = offset;
            $dirty2 |= $composer2.changed(offset2) ? 2048 : 1024;
        } else {
            offset2 = offset;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                scrollState2 = scrollState;
                int i5 = $composer2.changed(scrollState2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                scrollState2 = scrollState;
            }
            $dirty2 |= i5;
        } else {
            scrollState2 = scrollState;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            popupProperties = properties;
        } else if ((458752 & $changed) == 0) {
            popupProperties = properties;
            $dirty2 |= $composer2.changed(popupProperties) ? 131072 : 65536;
        } else {
            popupProperties = properties;
        }
        if ((i & 64) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(content) ? 1048576 : 524288;
            }
            if (($dirty2 & 2995931) == 599186 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        offset2 = DpKt.m5295DpOffsetYgX7TsA(Dp.m5274constructorimpl(0), Dp.m5274constructorimpl(0));
                    }
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                        scrollState2 = ScrollKt.rememberScrollState(0, $composer2, 0, 1);
                    }
                    if (i6 != 0) {
                        properties2 = new PopupProperties(true, false, false, null, false, false, 62, null);
                        scrollState3 = scrollState2;
                        offset3 = offset2;
                        modifier4 = modifier3;
                        $dirty = $dirty2;
                    } else {
                        properties2 = popupProperties;
                        scrollState3 = scrollState2;
                        offset3 = offset2;
                        modifier4 = modifier3;
                        $dirty = $dirty2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    properties2 = popupProperties;
                    scrollState3 = scrollState2;
                    offset3 = offset2;
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2135362555, $dirty, -1, "androidx.compose.material.DropdownMenu (AndroidMenu.android.kt:146)");
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
                    value$iv$iv2 = $composer2.rememberedValue();
                    if (value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m3349boximpl(TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ()), null, 2, null);
                        $composer2.updateRememberedValue(value$iv$iv2);
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
                    value$iv$iv3 = $composer2.rememberedValue();
                    if (!invalid$iv$iv || value$iv$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv3 = (Function2) new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
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
                    }
                    $composer2.endReplaceableGroup();
                    DropdownMenuPositionProvider popupPositionProvider = new DropdownMenuPositionProvider(offset3, density, (Function2) value$iv$iv3, null);
                    final ScrollState scrollState5 = scrollState3;
                    final Modifier modifier6 = modifier4;
                    int $dirty3 = $dirty;
                    AndroidPopup_androidKt.Popup(popupPositionProvider, onDismissRequest, properties2, ComposableLambdaKt.composableLambda($composer2, -47803778, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$2
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
                            ComposerKt.sourceInformation($composer3, "C173@8667L258:AndroidMenu.android.kt#jmzs0o");
                            if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-47803778, $changed2, -1, "androidx.compose.material.DropdownMenu.<anonymous> (AndroidMenu.android.kt:172)");
                                }
                                MutableTransitionState<Boolean> mutableTransitionState = expandedStates;
                                MutableState<TransformOrigin> mutableState = transformOriginState;
                                ScrollState scrollState6 = scrollState5;
                                Modifier modifier7 = modifier6;
                                Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                                int i7 = MutableTransitionState.$stable | 48;
                                int i8 = $dirty;
                                MenuKt.DropdownMenuContent(mutableTransitionState, mutableState, scrollState6, modifier7, function3, $composer3, i7 | ((i8 >> 6) & 896) | ((i8 << 3) & 7168) | ((i8 >> 6) & 57344), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    }), $composer2, ($dirty3 & 112) | 3072 | (($dirty3 >> 9) & 896), 0);
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier5 = modifier4;
                scrollState4 = scrollState3;
                properties3 = properties2;
                offset4 = offset3;
            } else {
                $composer2.skipToGroupEnd();
                modifier5 = modifier2;
                offset4 = offset2;
                scrollState4 = scrollState2;
                properties3 = popupProperties;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier7 = modifier5;
            final long j = offset4;
            final ScrollState scrollState6 = scrollState4;
            final PopupProperties popupProperties2 = properties3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$3
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
                    AndroidMenu_androidKt.m973DropdownMenu4kj_NE(expanded, onDismissRequest, modifier7, j, scrollState6, popupProperties2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty2 |= i2;
        if (($dirty2 & 2995931) == 599186) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    offset2 = DpKt.m5295DpOffsetYgX7TsA(Dp.m5274constructorimpl(0), Dp.m5274constructorimpl(0));
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer2, 0, 1);
                }
                if (i6 != 0) {
                    properties2 = new PopupProperties(true, false, false, null, false, false, 62, null);
                    scrollState3 = scrollState2;
                    offset3 = offset2;
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                } else {
                    properties2 = popupProperties;
                    scrollState3 = scrollState2;
                    offset3 = offset2;
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    offset2 = DpKt.m5295DpOffsetYgX7TsA(Dp.m5274constructorimpl(0), Dp.m5274constructorimpl(0));
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer2, 0, 1);
                }
                if (i6 != 0) {
                    properties2 = new PopupProperties(true, false, false, null, false, false, 62, null);
                    scrollState3 = scrollState2;
                    offset3 = offset2;
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                } else {
                    properties2 = popupProperties;
                    scrollState3 = scrollState2;
                    offset3 = offset2;
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2135362555, $dirty, -1, "androidx.compose.material.DropdownMenu (AndroidMenu.android.kt:146)");
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
                value$iv$iv2 = $composer2.rememberedValue();
                if (value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m3349boximpl(TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ()), null, 2, null);
                    $composer2.updateRememberedValue(value$iv$iv2);
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
                value$iv$iv3 = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                }
                value$iv$iv3 = (Function2) new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
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
                DropdownMenuPositionProvider popupPositionProvider2 = new DropdownMenuPositionProvider(offset3, density2, (Function2) value$iv$iv3, null);
                final ScrollState scrollState7 = scrollState3;
                final Modifier modifier8 = modifier4;
                int $dirty4 = $dirty;
                AndroidPopup_androidKt.Popup(popupPositionProvider2, onDismissRequest, properties2, ComposableLambdaKt.composableLambda($composer2, -47803778, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$2
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
                        ComposerKt.sourceInformation($composer3, "C173@8667L258:AndroidMenu.android.kt#jmzs0o");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-47803778, $changed2, -1, "androidx.compose.material.DropdownMenu.<anonymous> (AndroidMenu.android.kt:172)");
                            }
                            MutableTransitionState<Boolean> mutableTransitionState = expandedStates;
                            MutableState<TransformOrigin> mutableState = transformOriginState2;
                            ScrollState scrollState8 = scrollState7;
                            Modifier modifier9 = modifier8;
                            Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                            int i7 = MutableTransitionState.$stable | 48;
                            int i8 = $dirty;
                            MenuKt.DropdownMenuContent(mutableTransitionState, mutableState, scrollState8, modifier9, function3, $composer3, i7 | ((i8 >> 6) & 896) | ((i8 << 3) & 7168) | ((i8 >> 6) & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, ($dirty4 & 112) | 3072 | (($dirty4 >> 9) & 896), 0);
            } else {
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                value$iv$iv2 = $composer2.rememberedValue();
                if (value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m3349boximpl(TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ()), null, 2, null);
                    $composer2.updateRememberedValue(value$iv$iv2);
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
                value$iv$iv3 = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                }
                value$iv$iv3 = (Function2) new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
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
                DropdownMenuPositionProvider popupPositionProvider3 = new DropdownMenuPositionProvider(offset3, density3, (Function2) value$iv$iv3, null);
                final ScrollState scrollState8 = scrollState3;
                final Modifier modifier9 = modifier4;
                int $dirty5 = $dirty;
                AndroidPopup_androidKt.Popup(popupPositionProvider3, onDismissRequest, properties2, ComposableLambdaKt.composableLambda($composer2, -47803778, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$2
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
                        ComposerKt.sourceInformation($composer3, "C173@8667L258:AndroidMenu.android.kt#jmzs0o");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-47803778, $changed2, -1, "androidx.compose.material.DropdownMenu.<anonymous> (AndroidMenu.android.kt:172)");
                            }
                            MutableTransitionState<Boolean> mutableTransitionState = expandedStates;
                            MutableState<TransformOrigin> mutableState = transformOriginState3;
                            ScrollState scrollState9 = scrollState8;
                            Modifier modifier10 = modifier9;
                            Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                            int i7 = MutableTransitionState.$stable | 48;
                            int i8 = $dirty;
                            MenuKt.DropdownMenuContent(mutableTransitionState, mutableState, scrollState9, modifier10, function3, $composer3, i7 | ((i8 >> 6) & 896) | ((i8 << 3) & 7168) | ((i8 >> 6) & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, ($dirty5 & 112) | 3072 | (($dirty5 >> 9) & 896), 0);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier4;
            scrollState4 = scrollState3;
            properties3 = properties2;
            offset4 = offset3;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    offset2 = DpKt.m5295DpOffsetYgX7TsA(Dp.m5274constructorimpl(0), Dp.m5274constructorimpl(0));
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer2, 0, 1);
                }
                if (i6 != 0) {
                    properties2 = new PopupProperties(true, false, false, null, false, false, 62, null);
                    scrollState3 = scrollState2;
                    offset3 = offset2;
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                } else {
                    properties2 = popupProperties;
                    scrollState3 = scrollState2;
                    offset3 = offset2;
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    offset2 = DpKt.m5295DpOffsetYgX7TsA(Dp.m5274constructorimpl(0), Dp.m5274constructorimpl(0));
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer2, 0, 1);
                }
                if (i6 != 0) {
                    properties2 = new PopupProperties(true, false, false, null, false, false, 62, null);
                    scrollState3 = scrollState2;
                    offset3 = offset2;
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                } else {
                    properties2 = popupProperties;
                    scrollState3 = scrollState2;
                    offset3 = offset2;
                    modifier4 = modifier3;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2135362555, $dirty, -1, "androidx.compose.material.DropdownMenu (AndroidMenu.android.kt:146)");
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
                value$iv$iv2 = $composer2.rememberedValue();
                if (value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m3349boximpl(TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ()), null, 2, null);
                    $composer2.updateRememberedValue(value$iv$iv2);
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
                value$iv$iv3 = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                }
                value$iv$iv3 = (Function2) new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
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
                DropdownMenuPositionProvider popupPositionProvider4 = new DropdownMenuPositionProvider(offset3, density4, (Function2) value$iv$iv3, null);
                final ScrollState scrollState9 = scrollState3;
                final Modifier modifier10 = modifier4;
                int $dirty6 = $dirty;
                AndroidPopup_androidKt.Popup(popupPositionProvider4, onDismissRequest, properties2, ComposableLambdaKt.composableLambda($composer2, -47803778, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$2
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
                        ComposerKt.sourceInformation($composer3, "C173@8667L258:AndroidMenu.android.kt#jmzs0o");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-47803778, $changed2, -1, "androidx.compose.material.DropdownMenu.<anonymous> (AndroidMenu.android.kt:172)");
                            }
                            MutableTransitionState<Boolean> mutableTransitionState = expandedStates;
                            MutableState<TransformOrigin> mutableState = transformOriginState4;
                            ScrollState scrollState10 = scrollState9;
                            Modifier modifier11 = modifier10;
                            Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                            int i7 = MutableTransitionState.$stable | 48;
                            int i8 = $dirty;
                            MenuKt.DropdownMenuContent(mutableTransitionState, mutableState, scrollState10, modifier11, function3, $composer3, i7 | ((i8 >> 6) & 896) | ((i8 << 3) & 7168) | ((i8 >> 6) & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, ($dirty6 & 112) | 3072 | (($dirty6 >> 9) & 896), 0);
            } else {
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                value$iv$iv2 = $composer2.rememberedValue();
                if (value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m3349boximpl(TransformOrigin.INSTANCE.m3362getCenterSzJe1aQ()), null, 2, null);
                    $composer2.updateRememberedValue(value$iv$iv2);
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
                value$iv$iv3 = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                }
                value$iv$iv3 = (Function2) new Function2<IntRect, IntRect, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$popupPositionProvider$1$1
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
                DropdownMenuPositionProvider popupPositionProvider5 = new DropdownMenuPositionProvider(offset3, density5, (Function2) value$iv$iv3, null);
                final ScrollState scrollState10 = scrollState3;
                final Modifier modifier11 = modifier4;
                int $dirty7 = $dirty;
                AndroidPopup_androidKt.Popup(popupPositionProvider5, onDismissRequest, properties2, ComposableLambdaKt.composableLambda($composer2, -47803778, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$2
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
                        ComposerKt.sourceInformation($composer3, "C173@8667L258:AndroidMenu.android.kt#jmzs0o");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-47803778, $changed2, -1, "androidx.compose.material.DropdownMenu.<anonymous> (AndroidMenu.android.kt:172)");
                            }
                            MutableTransitionState<Boolean> mutableTransitionState = expandedStates;
                            MutableState<TransformOrigin> mutableState = transformOriginState5;
                            ScrollState scrollState11 = scrollState10;
                            Modifier modifier12 = modifier11;
                            Function3<ColumnScope, Composer, Integer, Unit> function3 = content;
                            int i7 = MutableTransitionState.$stable | 48;
                            int i8 = $dirty;
                            MenuKt.DropdownMenuContent(mutableTransitionState, mutableState, scrollState11, modifier12, function3, $composer3, i7 | ((i8 >> 6) & 896) | ((i8 << 3) & 7168) | ((i8 >> 6) & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, ($dirty7 & 112) | 3072 | (($dirty7 >> 9) & 896), 0);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier4;
            scrollState4 = scrollState3;
            properties3 = properties2;
            offset4 = offset3;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier12 = modifier5;
        final long j2 = offset4;
        final ScrollState scrollState11 = scrollState4;
        final PopupProperties popupProperties3 = properties3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt$DropdownMenu$3
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
                AndroidMenu_androidKt.m973DropdownMenu4kj_NE(expanded, onDismissRequest, modifier12, j2, scrollState11, popupProperties3, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:72:0x00e6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:76:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:77:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:79:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:80:0x0102  */
    /* JADX WARN: Code duplicated, block: B:82:0x0106  */
    /* JADX WARN: Code duplicated, block: B:84:0x0123  */
    /* JADX WARN: Code duplicated, block: B:85:0x012e  */
    /* JADX WARN: Code duplicated, block: B:89:0x013e  */
    /* JADX WARN: Code duplicated, block: B:92:0x0172  */
    /* JADX WARN: Code duplicated, block: B:96:0x017c  */
    /* JADX WARN: Code duplicated, block: B:98:? A[RETURN, SYNTHETIC] */
    public static final void DropdownMenuItem(final Function0<Unit> onClick, Modifier modifier, boolean enabled, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        PaddingValues paddingValues;
        MutableInteractionSource interactionSource2;
        int i2;
        int $dirty;
        Modifier modifier3;
        boolean enabled2;
        PaddingValues contentPadding2;
        Object it$iv$iv;
        Object value$iv$iv;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-1988562892);
        ComposerKt.sourceInformation($composer2, "C(DropdownMenuItem)P(5,4,2,1,3)207@10118L39,210@10212L227:AndroidMenu.android.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changedInstance(onClick) ? 4 : 2;
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
            z = enabled;
        } else if (($changed & 896) == 0) {
            z = enabled;
            $dirty2 |= $composer2.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            paddingValues = contentPadding;
        } else if (($changed & 7168) == 0) {
            paddingValues = contentPadding;
            $dirty2 |= $composer2.changed(paddingValues) ? 2048 : 1024;
        } else {
            paddingValues = contentPadding;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty2 |= 24576;
            interactionSource2 = interactionSource;
        } else if (($changed & 57344) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer2.changed(interactionSource2) ? 16384 : 8192;
        } else {
            interactionSource2 = interactionSource;
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
                    enabled2 = true;
                } else {
                    enabled2 = z;
                }
                if (i5 != 0) {
                    contentPadding2 = MenuDefaults.INSTANCE.getDropdownMenuItemContentPadding();
                } else {
                    contentPadding2 = paddingValues;
                }
                if (i6 != 0) {
                    $composer2.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer2.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer2.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer2.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1988562892, $dirty, -1, "androidx.compose.material.DropdownMenuItem (AndroidMenu.android.kt:202)");
                }
                MenuKt.DropdownMenuItemContent(onClick, modifier3, enabled2, contentPadding2, interactionSource2, content, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | ($dirty & 458752), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                enabled2 = z;
                contentPadding2 = paddingValues;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier4 = modifier3;
            final boolean z2 = enabled2;
            final PaddingValues paddingValues2 = contentPadding2;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt.DropdownMenuItem.2
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
                    AndroidMenu_androidKt.DropdownMenuItem(onClick, modifier4, z2, paddingValues2, mutableInteractionSource, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
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
                enabled2 = true;
            } else {
                enabled2 = z;
            }
            if (i5 != 0) {
                contentPadding2 = MenuDefaults.INSTANCE.getDropdownMenuItemContentPadding();
            } else {
                contentPadding2 = paddingValues;
            }
            if (i6 != 0) {
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv = $composer2.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer2.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                $composer2.endReplaceableGroup();
                interactionSource2 = (MutableInteractionSource) value$iv$iv;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1988562892, $dirty, -1, "androidx.compose.material.DropdownMenuItem (AndroidMenu.android.kt:202)");
            }
            MenuKt.DropdownMenuItemContent(onClick, modifier3, enabled2, contentPadding2, interactionSource2, content, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | ($dirty & 458752), 0);
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
                enabled2 = true;
            } else {
                enabled2 = z;
            }
            if (i5 != 0) {
                contentPadding2 = MenuDefaults.INSTANCE.getDropdownMenuItemContentPadding();
            } else {
                contentPadding2 = paddingValues;
            }
            if (i6 != 0) {
                $composer2.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
                it$iv$iv = $composer2.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer2.updateRememberedValue(value$iv$iv);
                } else {
                    value$iv$iv = it$iv$iv;
                }
                $composer2.endReplaceableGroup();
                interactionSource2 = (MutableInteractionSource) value$iv$iv;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1988562892, $dirty, -1, "androidx.compose.material.DropdownMenuItem (AndroidMenu.android.kt:202)");
            }
            MenuKt.DropdownMenuItemContent(onClick, modifier3, enabled2, contentPadding2, interactionSource2, content, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | ($dirty & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier3;
        final boolean z3 = enabled2;
        final PaddingValues paddingValues3 = contentPadding2;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AndroidMenu_androidKt.DropdownMenuItem.2
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
                AndroidMenu_androidKt.DropdownMenuItem(onClick, modifier5, z3, paddingValues3, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }
}
