package androidx.compose.material;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Surface.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001a¬\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0088\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001af\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 \u001a\u0090\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u00142\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010#\u001a\u0096\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u00142\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010&2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010'\u001a/\u0010(\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020\u000eH\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-\u001a;\u0010.\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00062"}, d2 = {"Surface", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", "color", "Landroidx/compose/ui/graphics/Color;", "contentColor", OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "elevation", "Landroidx/compose/ui/unit/Dp;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indication", "Landroidx/compose/foundation/Indication;", "enabled", "", "onClickLabel", "", "role", "Landroidx/compose/ui/semantics/Role;", "content", "Landroidx/compose/runtime/Composable;", "Surface-9VG74zQ", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/Indication;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "Surface-LPr_se0", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Surface-F-jzlyU", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "selected", "Surface-Ny5ogXk", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "checked", "onCheckedChange", "Lkotlin/Function1;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "surfaceColorAtElevation", "elevationOverlay", "Landroidx/compose/material/ElevationOverlay;", "absoluteElevation", "surfaceColorAtElevation-cq6XJ1M", "(JLandroidx/compose/material/ElevationOverlay;FLandroidx/compose/runtime/Composer;I)J", "surface", "backgroundColor", "surface-8ww4TTg", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JLandroidx/compose/foundation/BorderStroke;F)Landroidx/compose/ui/Modifier;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SurfaceKt {
    /* JADX WARN: Code duplicated, block: B:102:0x0151  */
    /* JADX WARN: Code duplicated, block: B:105:0x0163  */
    /* JADX WARN: Code duplicated, block: B:107:0x0170  */
    /* JADX WARN: Code duplicated, block: B:109:0x0174  */
    /* JADX WARN: Code duplicated, block: B:110:0x0186  */
    /* JADX WARN: Code duplicated, block: B:113:0x0199  */
    /* JADX WARN: Code duplicated, block: B:116:0x021a  */
    /* JADX WARN: Code duplicated, block: B:121:0x0230  */
    /* JADX WARN: Code duplicated, block: B:123:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x010b  */
    /* JADX WARN: Code duplicated, block: B:86:0x0112  */
    /* JADX WARN: Code duplicated, block: B:96:0x013f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:97:0x0141  */
    /* JADX WARN: Code duplicated, block: B:99:0x0148  */
    /* JADX INFO: renamed from: Surface-F-jzlyU, reason: not valid java name */
    public static final void m1210SurfaceFjzlyU(Modifier modifier, Shape shape, long color, long contentColor, BorderStroke border, float elevation, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        long color2;
        long contentColor2;
        BorderStroke border2;
        int i2;
        float elevation2;
        Shape shape3;
        long contentColor3;
        BorderStroke border3;
        int $dirty;
        Modifier modifier3;
        long color3;
        float elevation3;
        BorderStroke border4;
        long color4;
        Modifier modifier4;
        Shape shape4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1412203386);
        ComposerKt.sourceInformation($composer2, "C(Surface)P(5,6,1:c#ui.graphics.Color,3:c#ui.graphics.Color!1,4:c#ui.unit.Dp)107@5308L6,108@5350L22,*113@5525L7,114@5549L894:Surface.kt#jmzs0o");
        int $dirty2 = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 14) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty2 |= 48;
            shape2 = shape;
        } else if (($changed & 112) == 0) {
            shape2 = shape;
            $dirty2 |= $composer2.changed(shape2) ? 32 : 16;
        } else {
            shape2 = shape;
        }
        if (($changed & 896) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                int i5 = $composer2.changed(color2) ? 256 : 128;
                $dirty2 |= i5;
            } else {
                color2 = color;
            }
            $dirty2 |= i5;
        } else {
            color2 = color;
        }
        if (($changed & 7168) == 0) {
            if ((i & 8) == 0) {
                contentColor2 = contentColor;
                int i6 = $composer2.changed(contentColor2) ? 2048 : 1024;
                $dirty2 |= i6;
            } else {
                contentColor2 = contentColor;
            }
            $dirty2 |= i6;
        } else {
            contentColor2 = contentColor;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty2 |= 24576;
            border2 = border;
        } else if ((57344 & $changed) == 0) {
            border2 = border;
            $dirty2 |= $composer2.changed(border2) ? 16384 : 8192;
        } else {
            border2 = border;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty2 |= $composer2.changed(elevation) ? 131072 : 65536;
        }
        if ((i & 64) == 0) {
            if (($changed & 3670016) == 0) {
                i2 = $composer2.changedInstance(content) ? 1048576 : 524288;
            }
            if (($dirty2 & 2995931) == 599186 || !$composer2.getSkipping()) {
                $composer2.startDefaults();
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        shape2 = RectangleShapeKt.getRectangleShape();
                    }
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                        color2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                    }
                    if ((i & 8) != 0) {
                        long contentColor4 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer2, ($dirty2 >> 6) & 14);
                        $dirty2 &= -7169;
                        contentColor2 = contentColor4;
                    }
                    if (i7 != 0) {
                        border2 = null;
                    }
                    if (i8 != 0) {
                        elevation2 = Dp.m5274constructorimpl(0);
                        shape3 = shape2;
                        contentColor3 = contentColor2;
                        border3 = border2;
                        $dirty = $dirty2;
                        modifier3 = modifier2;
                        color3 = color2;
                    } else {
                        elevation2 = elevation;
                        shape3 = shape2;
                        contentColor3 = contentColor2;
                        border3 = border2;
                        $dirty = $dirty2;
                        modifier3 = modifier2;
                        color3 = color2;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 8) != 0) {
                        elevation2 = elevation;
                        shape3 = shape2;
                        contentColor3 = contentColor2;
                        border3 = border2;
                        $dirty = $dirty2 & (-7169);
                        modifier3 = modifier2;
                        color3 = color2;
                    } else {
                        elevation2 = elevation;
                        shape3 = shape2;
                        contentColor3 = contentColor2;
                        border3 = border2;
                        $dirty = $dirty2;
                        modifier3 = modifier2;
                        color3 = color2;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1412203386, $dirty, -1, "androidx.compose.material.Surface (Surface.kt:104)");
                }
                ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localAbsoluteElevation);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                float arg0$iv = ((Dp) objConsume).m5288unboximpl();
                final float absoluteElevation = Dp.m5274constructorimpl(arg0$iv + elevation2);
                final Modifier modifier5 = modifier3;
                final Shape shape5 = shape3;
                final long j = color3;
                Modifier modifier6 = modifier3;
                final int i9 = $dirty;
                Shape shape6 = shape3;
                final BorderStroke borderStroke = border3;
                final float f = elevation2;
                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor3)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m5272boximpl(absoluteElevation))}, ComposableLambdaKt.composableLambda($composer2, -1822160838, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$1
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
                        ComposerKt.sourceInformation($composer3, "C124@5963L7,122@5834L221,118@5698L739:Surface.kt#jmzs0o");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1822160838, $changed2, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:117)");
                            }
                            Modifier modifier7 = modifier5;
                            Shape shape7 = shape5;
                            long j2 = j;
                            ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
                            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer3.consume(localElevationOverlay);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Modifier modifier$iv = SuspendingPointerInputFilterKt.pointerInput(SemanticsModifierKt.semantics(SurfaceKt.m1216surface8ww4TTg(modifier7, shape7, SurfaceKt.m1217surfaceColorAtElevationcq6XJ1M(j2, (ElevationOverlay) objConsume2, absoluteElevation, $composer3, (i9 >> 6) & 14), borderStroke, f), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$1.1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                    invoke2(semanticsPropertyReceiver);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(SemanticsPropertyReceiver semantics) {
                                    Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                                    SemanticsPropertiesKt.setContainer(semantics, true);
                                }
                            }), Unit.INSTANCE, new AnonymousClass2(null));
                            Function2<Composer, Integer, Unit> function2 = content;
                            int i10 = i9;
                            $composer3.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, true, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            int $changed$iv$iv = (384 << 3) & 112;
                            $composer3.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
                            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                            if (!($composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                                $composer3.createNode(constructor);
                            } else {
                                $composer3.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer3);
                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                            }
                            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                            $composer3.startReplaceableGroup(2058660585);
                            int i11 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i12 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1505970093, "C137@6418L9:Surface.kt#jmzs0o");
                            function2.invoke($composer3, Integer.valueOf((i10 >> 18) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceableGroup();
                            $composer3.endNode();
                            $composer3.endReplaceableGroup();
                            $composer3.endReplaceableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }

                    /* JADX INFO: renamed from: androidx.compose.material.SurfaceKt$Surface$1$2, reason: invalid class name */
                    /* JADX INFO: compiled from: Surface.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                    @DebugMetadata(c = "androidx.compose.material.SurfaceKt$Surface$1$2", f = "Surface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass2 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
                        int label;

                        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
                            super(2, continuation);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass2(continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass2) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure(obj);
                                    return Unit.INSTANCE;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }
                    }
                }), $composer2, 56);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                elevation3 = elevation2;
                border4 = border3;
                color4 = color3;
                modifier4 = modifier6;
                shape4 = shape6;
            } else {
                $composer2.skipToGroupEnd();
                elevation3 = elevation;
                shape4 = shape2;
                color4 = color2;
                contentColor3 = contentColor2;
                border4 = border2;
                modifier4 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier7 = modifier4;
            final Shape shape7 = shape4;
            final long j2 = color4;
            final long j3 = contentColor3;
            final BorderStroke borderStroke2 = border4;
            final float f2 = elevation3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$2
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
                    SurfaceKt.m1210SurfaceFjzlyU(modifier7, shape7, j2, j3, borderStroke2, f2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 1572864;
        $dirty2 |= i2;
        if (($dirty2 & 2995931) == 599186) {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    shape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                    color2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                }
                if ((i & 8) != 0) {
                    long contentColor5 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                    contentColor2 = contentColor5;
                }
                if (i7 != 0) {
                    border2 = null;
                }
                if (i8 != 0) {
                    elevation2 = Dp.m5274constructorimpl(0);
                    shape3 = shape2;
                    contentColor3 = contentColor2;
                    border3 = border2;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    color3 = color2;
                } else {
                    elevation2 = elevation;
                    shape3 = shape2;
                    contentColor3 = contentColor2;
                    border3 = border2;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    color3 = color2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    shape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                    color2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                }
                if ((i & 8) != 0) {
                    long contentColor6 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                    contentColor2 = contentColor6;
                }
                if (i7 != 0) {
                    border2 = null;
                }
                if (i8 != 0) {
                    elevation2 = Dp.m5274constructorimpl(0);
                    shape3 = shape2;
                    contentColor3 = contentColor2;
                    border3 = border2;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    color3 = color2;
                } else {
                    elevation2 = elevation;
                    shape3 = shape2;
                    contentColor3 = contentColor2;
                    border3 = border2;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    color3 = color2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1412203386, $dirty, -1, "androidx.compose.material.Surface (Surface.kt:104)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation2 = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localAbsoluteElevation2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float arg0$iv2 = ((Dp) objConsume2).m5288unboximpl();
            final float absoluteElevation2 = Dp.m5274constructorimpl(arg0$iv2 + elevation2);
            final Modifier modifier8 = modifier3;
            final Shape shape8 = shape3;
            final long j4 = color3;
            Modifier modifier9 = modifier3;
            final int i10 = $dirty;
            Shape shape9 = shape3;
            final BorderStroke borderStroke3 = border3;
            final float f3 = elevation2;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor3)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m5272boximpl(absoluteElevation2))}, ComposableLambdaKt.composableLambda($composer2, -1822160838, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$1
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
                    ComposerKt.sourceInformation($composer3, "C124@5963L7,122@5834L221,118@5698L739:Surface.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1822160838, $changed2, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:117)");
                        }
                        Modifier modifier10 = modifier8;
                        Shape shape10 = shape8;
                        long j5 = j4;
                        ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer3.consume(localElevationOverlay);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier modifier$iv = SuspendingPointerInputFilterKt.pointerInput(SemanticsModifierKt.semantics(SurfaceKt.m1216surface8ww4TTg(modifier10, shape10, SurfaceKt.m1217surfaceColorAtElevationcq6XJ1M(j5, (ElevationOverlay) objConsume3, absoluteElevation2, $composer3, (i10 >> 6) & 14), borderStroke3, f3), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$1.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semantics) {
                                Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                                SemanticsPropertiesKt.setContainer(semantics, true);
                            }
                        }), Unit.INSTANCE, new AnonymousClass2(null));
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i11 = i10;
                        $composer3.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, true, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv = (384 << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            $composer3.createNode(constructor);
                        } else {
                            $composer3.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer3);
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer3.startReplaceableGroup(2058660585);
                        int i12 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i13 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1505970093, "C137@6418L9:Surface.kt#jmzs0o");
                        function2.invoke($composer3, Integer.valueOf((i11 >> 18) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }

                /* JADX INFO: renamed from: androidx.compose.material.SurfaceKt$Surface$1$2, reason: invalid class name */
                /* JADX INFO: compiled from: Surface.kt */
                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                @DebugMetadata(c = "androidx.compose.material.SurfaceKt$Surface$1$2", f = "Surface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass2 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
                    int label;

                    AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass2(continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass2) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                }
            }), $composer2, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            elevation3 = elevation2;
            border4 = border3;
            color4 = color3;
            modifier4 = modifier9;
            shape4 = shape9;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    shape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                    color2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                }
                if ((i & 8) != 0) {
                    long contentColor7 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                    contentColor2 = contentColor7;
                }
                if (i7 != 0) {
                    border2 = null;
                }
                if (i8 != 0) {
                    elevation2 = Dp.m5274constructorimpl(0);
                    shape3 = shape2;
                    contentColor3 = contentColor2;
                    border3 = border2;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    color3 = color2;
                } else {
                    elevation2 = elevation;
                    shape3 = shape2;
                    contentColor3 = contentColor2;
                    border3 = border2;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    color3 = color2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    shape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                    color2 = MaterialTheme.INSTANCE.getColors($composer2, 6).m1052getSurface0d7_KjU();
                }
                if ((i & 8) != 0) {
                    long contentColor8 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer2, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                    contentColor2 = contentColor8;
                }
                if (i7 != 0) {
                    border2 = null;
                }
                if (i8 != 0) {
                    elevation2 = Dp.m5274constructorimpl(0);
                    shape3 = shape2;
                    contentColor3 = contentColor2;
                    border3 = border2;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    color3 = color2;
                } else {
                    elevation2 = elevation;
                    shape3 = shape2;
                    contentColor3 = contentColor2;
                    border3 = border2;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                    color3 = color2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1412203386, $dirty, -1, "androidx.compose.material.Surface (Surface.kt:104)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation3 = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localAbsoluteElevation3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float arg0$iv3 = ((Dp) objConsume3).m5288unboximpl();
            final float absoluteElevation3 = Dp.m5274constructorimpl(arg0$iv3 + elevation2);
            final Modifier modifier10 = modifier3;
            final Shape shape10 = shape3;
            final long j5 = color3;
            Modifier modifier11 = modifier3;
            final int i11 = $dirty;
            Shape shape11 = shape3;
            final BorderStroke borderStroke4 = border3;
            final float f4 = elevation2;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor3)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m5272boximpl(absoluteElevation3))}, ComposableLambdaKt.composableLambda($composer2, -1822160838, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$1
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
                    ComposerKt.sourceInformation($composer3, "C124@5963L7,122@5834L221,118@5698L739:Surface.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1822160838, $changed2, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:117)");
                        }
                        Modifier modifier12 = modifier10;
                        Shape shape12 = shape10;
                        long j6 = j5;
                        ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume4 = $composer3.consume(localElevationOverlay);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier modifier$iv = SuspendingPointerInputFilterKt.pointerInput(SemanticsModifierKt.semantics(SurfaceKt.m1216surface8ww4TTg(modifier12, shape12, SurfaceKt.m1217surfaceColorAtElevationcq6XJ1M(j6, (ElevationOverlay) objConsume4, absoluteElevation3, $composer3, (i11 >> 6) & 14), borderStroke4, f4), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$1.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                invoke2(semanticsPropertyReceiver);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(SemanticsPropertyReceiver semantics) {
                                Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                                SemanticsPropertiesKt.setContainer(semantics, true);
                            }
                        }), Unit.INSTANCE, new AnonymousClass2(null));
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i12 = i11;
                        $composer3.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, true, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv = (384 << 3) & 112;
                        $composer3.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
                        int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            $composer3.createNode(constructor);
                        } else {
                            $composer3.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer3);
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer3.startReplaceableGroup(2058660585);
                        int i13 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i14 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1505970093, "C137@6418L9:Surface.kt#jmzs0o");
                        function2.invoke($composer3, Integer.valueOf((i12 >> 18) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceableGroup();
                        $composer3.endNode();
                        $composer3.endReplaceableGroup();
                        $composer3.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }

                /* JADX INFO: renamed from: androidx.compose.material.SurfaceKt$Surface$1$2, reason: invalid class name */
                /* JADX INFO: compiled from: Surface.kt */
                @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                @DebugMetadata(c = "androidx.compose.material.SurfaceKt$Surface$1$2", f = "Surface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass2 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
                    int label;

                    AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass2(continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass2) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                }
            }), $composer2, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            elevation3 = elevation2;
            border4 = border3;
            color4 = color3;
            modifier4 = modifier11;
            shape4 = shape11;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier12 = modifier4;
        final Shape shape12 = shape4;
        final long j6 = color4;
        final long j7 = contentColor3;
        final BorderStroke borderStroke5 = border4;
        final float f5 = elevation3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$2
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
                SurfaceKt.m1210SurfaceFjzlyU(modifier12, shape12, j6, j7, borderStroke5, f5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:116:0x0177  */
    /* JADX WARN: Code duplicated, block: B:118:0x0184  */
    /* JADX WARN: Code duplicated, block: B:128:0x01c0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:129:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:130:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:132:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:133:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:135:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:139:0x01db  */
    /* JADX WARN: Code duplicated, block: B:140:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:143:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:144:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:146:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:147:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:149:0x0202  */
    /* JADX WARN: Code duplicated, block: B:150:0x020d  */
    /* JADX WARN: Code duplicated, block: B:152:0x0213  */
    /* JADX WARN: Code duplicated, block: B:154:0x0239  */
    /* JADX WARN: Code duplicated, block: B:155:0x0248  */
    /* JADX WARN: Code duplicated, block: B:157:0x0269  */
    /* JADX WARN: Code duplicated, block: B:160:0x0286  */
    /* JADX WARN: Code duplicated, block: B:163:0x030d  */
    /* JADX WARN: Code duplicated, block: B:166:0x0316  */
    /* JADX WARN: Code duplicated, block: B:167:0x0319  */
    /* JADX INFO: renamed from: Surface-LPr_se0, reason: not valid java name */
    public static final void m1211SurfaceLPr_se0(final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, long color, long contentColor, BorderStroke border, float elevation, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        long j;
        int i2;
        Modifier.Companion modifier2;
        boolean enabled2;
        Shape shape3;
        long color2;
        long contentColor2;
        BorderStroke border2;
        float elevation2;
        float elevation3;
        MutableInteractionSource interactionSource2;
        Modifier modifier3;
        boolean enabled3;
        Shape shape4;
        BorderStroke border3;
        long color3;
        long contentColor3;
        int $dirty;
        Object it$iv$iv;
        Object value$iv$iv;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(1560876237);
        ComposerKt.sourceInformation($composer3, "C(Surface)P(8,7,5,9,1:c#ui.graphics.Color,3:c#ui.graphics.Color!1,4:c#ui.unit.Dp,6)216@10794L6,217@10836L22,220@10970L39,*223@11102L7,224@11126L982:Surface.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 256 : 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            shape2 = shape;
        } else if (($changed & 7168) == 0) {
            shape2 = shape;
            $dirty2 |= $composer3.changed(shape2) ? 2048 : 1024;
        } else {
            shape2 = shape;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                j = color;
                int i6 = $composer3.changed(j) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                j = color;
            }
            $dirty2 |= i6;
        } else {
            j = color;
        }
        if (($changed & 458752) == 0) {
            $dirty2 |= ((i & 32) == 0 && $composer3.changed(contentColor)) ? 131072 : 65536;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty2 |= $composer3.changed(border) ? 1048576 : 524288;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty2 |= $composer3.changed(elevation) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) == 0) {
            if ((1879048192 & $changed) == 0) {
                i2 = $composer3.changedInstance(content) ? 536870912 : 268435456;
            }
            if ((1533916891 & $dirty2) == 306783378 || !$composer3.getSkipping()) {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    } else {
                        modifier2 = modifier;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    } else {
                        enabled2 = enabled;
                    }
                    if (i5 != 0) {
                        shape3 = RectangleShapeKt.getRectangleShape();
                    } else {
                        shape3 = shape2;
                    }
                    if ((i & 16) != 0) {
                        color2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                        $dirty2 &= -57345;
                    } else {
                        color2 = j;
                    }
                    if ((i & 32) != 0) {
                        contentColor2 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer3, ($dirty2 >> 12) & 14);
                        $dirty2 &= -458753;
                    } else {
                        contentColor2 = contentColor;
                    }
                    if (i7 != 0) {
                        border2 = null;
                    } else {
                        border2 = border;
                    }
                    if (i8 != 0) {
                        elevation2 = Dp.m5274constructorimpl(0);
                    } else {
                        elevation2 = elevation;
                    }
                    if (i9 != 0) {
                        float elevation4 = elevation2;
                        $composer3.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                        it$iv$iv = $composer3.rememberedValue();
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                            $composer3.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        $composer3.endReplaceableGroup();
                        elevation3 = elevation4;
                        interactionSource2 = (MutableInteractionSource) value$iv$iv;
                        modifier3 = modifier2;
                        enabled3 = enabled2;
                        shape4 = shape3;
                        border3 = border2;
                        color3 = color2;
                        contentColor3 = contentColor2;
                        $dirty = $dirty2;
                    } else {
                        elevation3 = elevation2;
                        interactionSource2 = interactionSource;
                        modifier3 = modifier2;
                        enabled3 = enabled2;
                        shape4 = shape3;
                        border3 = border2;
                        color3 = color2;
                        contentColor3 = contentColor2;
                        $dirty = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 16) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 32) != 0) {
                        modifier3 = modifier;
                        enabled3 = enabled;
                        contentColor3 = contentColor;
                        border3 = border;
                        elevation3 = elevation;
                        interactionSource2 = interactionSource;
                        shape4 = shape2;
                        color3 = j;
                        $dirty = $dirty2 & (-458753);
                    } else {
                        modifier3 = modifier;
                        enabled3 = enabled;
                        contentColor3 = contentColor;
                        border3 = border;
                        elevation3 = elevation;
                        interactionSource2 = interactionSource;
                        shape4 = shape2;
                        color3 = j;
                        $dirty = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1560876237, $dirty, -1, "androidx.compose.material.Surface (Surface.kt:211)");
                }
                ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localAbsoluteElevation);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                float arg0$iv = ((Dp) objConsume).m5288unboximpl();
                final float absoluteElevation = Dp.m5274constructorimpl(arg0$iv + elevation3);
                final Modifier modifier4 = modifier3;
                final Shape shape5 = shape4;
                final long j2 = color3;
                final int i10 = $dirty;
                final BorderStroke borderStroke = border3;
                final float f = elevation3;
                final MutableInteractionSource mutableInteractionSource = interactionSource2;
                final boolean z = enabled3;
                $composer2 = $composer3;
                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor3)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m5272boximpl(absoluteElevation))}, ComposableLambdaKt.composableLambda($composer2, 2031491085, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$4
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
                        ComposerKt.sourceInformation($composer4, "C235@11591L7,233@11462L221,243@11902L16,228@11275L827:Surface.kt#jmzs0o");
                        if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2031491085, $changed2, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:227)");
                            }
                            Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier4);
                            Shape shape6 = shape5;
                            long j3 = j2;
                            ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
                            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object objConsume2 = $composer4.consume(localElevationOverlay);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Modifier modifier$iv = ClickableKt.m190clickableO2vRcR0(SurfaceKt.m1216surface8ww4TTg(modifierMinimumInteractiveComponentSize, shape6, SurfaceKt.m1217surfaceColorAtElevationcq6XJ1M(j3, (ElevationOverlay) objConsume2, absoluteElevation, $composer4, (i10 >> 12) & 14), borderStroke, f), mutableInteractionSource, RippleKt.m1298rememberRipple9IZ8Weo(false, 0.0f, 0L, $composer4, 0, 7), (24 & 4) != 0 ? true : z, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, onClick);
                            Function2<Composer, Integer, Unit> function2 = content;
                            int i11 = i10;
                            $composer4.startReplaceableGroup(733328855);
                            ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, true, $composer4, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            int $changed$iv$iv = (384 << 3) & 112;
                            $composer4.startReplaceableGroup(-1323940314);
                            ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
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
                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer4);
                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                            }
                            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                            $composer4.startReplaceableGroup(2058660585);
                            int i12 = ($changed$iv$iv$iv >> 9) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i13 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, 1505975758, "C249@12083L9:Surface.kt#jmzs0o");
                            function2.invoke($composer4, Integer.valueOf((i11 >> 27) & 14));
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
                }), $composer2, 56);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer3.skipToGroupEnd();
                modifier3 = modifier;
                enabled3 = enabled;
                contentColor3 = contentColor;
                border3 = border;
                elevation3 = elevation;
                interactionSource2 = interactionSource;
                shape4 = shape2;
                color3 = j;
                $composer2 = $composer3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final Modifier modifier5 = modifier3;
            final boolean z2 = enabled3;
            final Shape shape6 = shape4;
            final long j3 = color3;
            final long j4 = contentColor3;
            final BorderStroke borderStroke2 = border3;
            final float f2 = elevation3;
            final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$5
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

                public final void invoke(Composer composer, int i11) {
                    SurfaceKt.m1211SurfaceLPr_se0(onClick, modifier5, z2, shape6, j3, j4, borderStroke2, f2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
        i2 = 805306368;
        $dirty2 |= i2;
        if ((1533916891 & $dirty2) == 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = enabled;
                }
                if (i5 != 0) {
                    shape3 = RectangleShapeKt.getRectangleShape();
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    color2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -57345;
                } else {
                    color2 = j;
                }
                if ((i & 32) != 0) {
                    contentColor2 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer3, ($dirty2 >> 12) & 14);
                    $dirty2 &= -458753;
                } else {
                    contentColor2 = contentColor;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i8 != 0) {
                    elevation2 = Dp.m5274constructorimpl(0);
                } else {
                    elevation2 = elevation;
                }
                if (i9 != 0) {
                    float elevation5 = elevation2;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    elevation3 = elevation5;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    border3 = border2;
                    color3 = color2;
                    contentColor3 = contentColor2;
                    $dirty = $dirty2;
                } else {
                    elevation3 = elevation2;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    border3 = border2;
                    color3 = color2;
                    contentColor3 = contentColor2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = enabled;
                }
                if (i5 != 0) {
                    shape3 = RectangleShapeKt.getRectangleShape();
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    color2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -57345;
                } else {
                    color2 = j;
                }
                if ((i & 32) != 0) {
                    contentColor2 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer3, ($dirty2 >> 12) & 14);
                    $dirty2 &= -458753;
                } else {
                    contentColor2 = contentColor;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i8 != 0) {
                    elevation2 = Dp.m5274constructorimpl(0);
                } else {
                    elevation2 = elevation;
                }
                if (i9 != 0) {
                    float elevation6 = elevation2;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    elevation3 = elevation6;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    border3 = border2;
                    color3 = color2;
                    contentColor3 = contentColor2;
                    $dirty = $dirty2;
                } else {
                    elevation3 = elevation2;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    border3 = border2;
                    color3 = color2;
                    contentColor3 = contentColor2;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1560876237, $dirty, -1, "androidx.compose.material.Surface (Surface.kt:211)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation2 = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localAbsoluteElevation2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            float arg0$iv2 = ((Dp) objConsume2).m5288unboximpl();
            final float absoluteElevation2 = Dp.m5274constructorimpl(arg0$iv2 + elevation3);
            final Modifier modifier6 = modifier3;
            final Shape shape7 = shape4;
            final long j5 = color3;
            final int i11 = $dirty;
            final BorderStroke borderStroke3 = border3;
            final float f3 = elevation3;
            final MutableInteractionSource mutableInteractionSource3 = interactionSource2;
            final boolean z3 = enabled3;
            $composer2 = $composer3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor3)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m5272boximpl(absoluteElevation2))}, ComposableLambdaKt.composableLambda($composer2, 2031491085, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$4
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
                    ComposerKt.sourceInformation($composer4, "C235@11591L7,233@11462L221,243@11902L16,228@11275L827:Surface.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2031491085, $changed2, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:227)");
                        }
                        Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier6);
                        Shape shape8 = shape7;
                        long j6 = j5;
                        ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localElevationOverlay);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Modifier modifier$iv = ClickableKt.m190clickableO2vRcR0(SurfaceKt.m1216surface8ww4TTg(modifierMinimumInteractiveComponentSize, shape8, SurfaceKt.m1217surfaceColorAtElevationcq6XJ1M(j6, (ElevationOverlay) objConsume3, absoluteElevation2, $composer4, (i11 >> 12) & 14), borderStroke3, f3), mutableInteractionSource3, RippleKt.m1298rememberRipple9IZ8Weo(false, 0.0f, 0L, $composer4, 0, 7), (24 & 4) != 0 ? true : z3, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, onClick);
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i12 = i11;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, true, $composer4, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv = (384 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
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
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i13 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i14 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 1505975758, "C249@12083L9:Surface.kt#jmzs0o");
                        function2.invoke($composer4, Integer.valueOf((i12 >> 27) & 14));
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
            }), $composer2, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = enabled;
                }
                if (i5 != 0) {
                    shape3 = RectangleShapeKt.getRectangleShape();
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    color2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -57345;
                } else {
                    color2 = j;
                }
                if ((i & 32) != 0) {
                    contentColor2 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer3, ($dirty2 >> 12) & 14);
                    $dirty2 &= -458753;
                } else {
                    contentColor2 = contentColor;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i8 != 0) {
                    elevation2 = Dp.m5274constructorimpl(0);
                } else {
                    elevation2 = elevation;
                }
                if (i9 != 0) {
                    float elevation7 = elevation2;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    elevation3 = elevation7;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    border3 = border2;
                    color3 = color2;
                    contentColor3 = contentColor2;
                    $dirty = $dirty2;
                } else {
                    elevation3 = elevation2;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    border3 = border2;
                    color3 = color2;
                    contentColor3 = contentColor2;
                    $dirty = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                } else {
                    modifier2 = modifier;
                }
                if (i4 != 0) {
                    enabled2 = true;
                } else {
                    enabled2 = enabled;
                }
                if (i5 != 0) {
                    shape3 = RectangleShapeKt.getRectangleShape();
                } else {
                    shape3 = shape2;
                }
                if ((i & 16) != 0) {
                    color2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                    $dirty2 &= -57345;
                } else {
                    color2 = j;
                }
                if ((i & 32) != 0) {
                    contentColor2 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer3, ($dirty2 >> 12) & 14);
                    $dirty2 &= -458753;
                } else {
                    contentColor2 = contentColor;
                }
                if (i7 != 0) {
                    border2 = null;
                } else {
                    border2 = border;
                }
                if (i8 != 0) {
                    elevation2 = Dp.m5274constructorimpl(0);
                } else {
                    elevation2 = elevation;
                }
                if (i9 != 0) {
                    float elevation8 = elevation2;
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    elevation3 = elevation8;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    border3 = border2;
                    color3 = color2;
                    contentColor3 = contentColor2;
                    $dirty = $dirty2;
                } else {
                    elevation3 = elevation2;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    shape4 = shape3;
                    border3 = border2;
                    color3 = color2;
                    contentColor3 = contentColor2;
                    $dirty = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1560876237, $dirty, -1, "androidx.compose.material.Surface (Surface.kt:211)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation3 = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localAbsoluteElevation3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            float arg0$iv3 = ((Dp) objConsume3).m5288unboximpl();
            final float absoluteElevation3 = Dp.m5274constructorimpl(arg0$iv3 + elevation3);
            final Modifier modifier7 = modifier3;
            final Shape shape8 = shape4;
            final long j6 = color3;
            final int i12 = $dirty;
            final BorderStroke borderStroke4 = border3;
            final float f4 = elevation3;
            final MutableInteractionSource mutableInteractionSource4 = interactionSource2;
            final boolean z4 = enabled3;
            $composer2 = $composer3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor3)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m5272boximpl(absoluteElevation3))}, ComposableLambdaKt.composableLambda($composer2, 2031491085, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$4
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
                    ComposerKt.sourceInformation($composer4, "C235@11591L7,233@11462L221,243@11902L16,228@11275L827:Surface.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2031491085, $changed2, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:227)");
                        }
                        Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier7);
                        Shape shape9 = shape8;
                        long j7 = j6;
                        ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume4 = $composer4.consume(localElevationOverlay);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Modifier modifier$iv = ClickableKt.m190clickableO2vRcR0(SurfaceKt.m1216surface8ww4TTg(modifierMinimumInteractiveComponentSize, shape9, SurfaceKt.m1217surfaceColorAtElevationcq6XJ1M(j7, (ElevationOverlay) objConsume4, absoluteElevation3, $composer4, (i12 >> 12) & 14), borderStroke4, f4), mutableInteractionSource4, RippleKt.m1298rememberRipple9IZ8Weo(false, 0.0f, 0L, $composer4, 0, 7), (24 & 4) != 0 ? true : z4, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, onClick);
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i13 = i12;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, true, $composer4, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv = (384 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
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
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i14 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i15 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 1505975758, "C249@12083L9:Surface.kt#jmzs0o");
                        function2.invoke($composer4, Integer.valueOf((i13 >> 27) & 14));
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
            }), $composer2, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier8 = modifier3;
        final boolean z5 = enabled3;
        final Shape shape9 = shape4;
        final long j7 = color3;
        final long j8 = contentColor3;
        final BorderStroke borderStroke5 = border3;
        final float f5 = elevation3;
        final MutableInteractionSource mutableInteractionSource5 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$5
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
                SurfaceKt.m1211SurfaceLPr_se0(onClick, modifier8, z5, shape9, j7, j8, borderStroke5, f5, mutableInteractionSource5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: renamed from: Surface-Ny5ogXk, reason: not valid java name */
    public static final void m1212SurfaceNy5ogXk(final boolean selected, final Function0<Unit> onClick, Modifier modifier, boolean enabled, Shape shape, long color, long contentColor, BorderStroke border, float elevation, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        int i2;
        int $dirty;
        long color2;
        long contentColor2;
        BorderStroke border2;
        float elevation2;
        MutableInteractionSource interactionSource2;
        Modifier modifier2;
        int $dirty2;
        long contentColor3;
        boolean enabled2;
        long color3;
        Shape shape2;
        Object value$iv$iv;
        Composer $composer2;
        int $dirty3;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(262027249);
        ComposerKt.sourceInformation($composer3, "C(Surface)P(9,8,7,5,10,1:c#ui.graphics.Color,3:c#ui.graphics.Color!1,4:c#ui.unit.Dp,6)330@16547L6,331@16589L22,334@16723L39,*337@16855L7,338@16879L1024:Surface.kt#jmzs0o");
        int $dirty4 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty4 |= $composer3.changed(selected) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty4 |= $composer3.changedInstance(onClick) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty4 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 7168) == 0) {
            z = enabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty4 |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty4 |= $composer3.changed(shape) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                i2 = i4;
                int i6 = $composer3.changed(color) ? 131072 : 65536;
                $dirty4 |= i6;
            } else {
                i2 = i4;
            }
            $dirty4 |= i6;
        } else {
            i2 = i4;
        }
        if (($changed & 3670016) == 0) {
            if ((i & 64) == 0) {
                $dirty3 = $dirty4;
                int i7 = $composer3.changed(contentColor) ? 1048576 : 524288;
                $dirty = $dirty3 | i7;
            } else {
                $dirty3 = $dirty4;
            }
            $dirty = $dirty3 | i7;
        } else {
            $dirty = $dirty4;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty |= 12582912;
        } else if ((29360128 & $changed) == 0) {
            $dirty |= $composer3.changed(border) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty |= $composer3.changed(elevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty |= $composer3.changed(interactionSource) ? 536870912 : 268435456;
        }
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 14) == 0) {
            $dirty1 |= $composer3.changedInstance(content) ? 4 : 2;
        }
        if (($dirty & 1533916891) == 306783378 && ($dirty1 & 11) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier2 = modifier;
            shape2 = shape;
            color3 = color;
            contentColor3 = contentColor;
            border2 = border;
            elevation2 = elevation;
            interactionSource2 = interactionSource;
            $composer2 = $composer3;
            enabled2 = z;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier;
                boolean enabled3 = i2 != 0 ? true : z;
                Shape shape3 = i5 != 0 ? RectangleShapeKt.getRectangleShape() : shape;
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    color2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                } else {
                    color2 = color;
                }
                if ((i & 64) != 0) {
                    contentColor2 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer3, ($dirty >> 15) & 14);
                    $dirty &= -3670017;
                } else {
                    contentColor2 = contentColor;
                }
                BorderStroke border3 = i8 != 0 ? null : border;
                float elevation3 = i9 != 0 ? Dp.m5274constructorimpl(0) : elevation;
                if (i10 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    float elevation4 = elevation3;
                    Object it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    border2 = border3;
                    elevation2 = elevation4;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier2 = modifier3;
                    $dirty2 = $dirty;
                    contentColor3 = contentColor2;
                    enabled2 = enabled3;
                    color3 = color2;
                    shape2 = shape3;
                } else {
                    border2 = border3;
                    elevation2 = elevation3;
                    interactionSource2 = interactionSource;
                    modifier2 = modifier3;
                    $dirty2 = $dirty;
                    contentColor3 = contentColor2;
                    enabled2 = enabled3;
                    color3 = color2;
                    shape2 = shape3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    modifier2 = modifier;
                    shape2 = shape;
                    color3 = color;
                    contentColor3 = contentColor;
                    border2 = border;
                    elevation2 = elevation;
                    interactionSource2 = interactionSource;
                    $dirty2 = $dirty & (-3670017);
                    enabled2 = z;
                } else {
                    modifier2 = modifier;
                    shape2 = shape;
                    color3 = color;
                    contentColor3 = contentColor;
                    border2 = border;
                    elevation2 = elevation;
                    interactionSource2 = interactionSource;
                    $dirty2 = $dirty;
                    enabled2 = z;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(262027249, $dirty2, $dirty1, "androidx.compose.material.Surface (Surface.kt:324)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            float arg0$iv = ((Dp) objConsume).m5288unboximpl();
            final float absoluteElevation = Dp.m5274constructorimpl(arg0$iv + elevation2);
            final Modifier modifier4 = modifier2;
            final int $dirty5 = $dirty1;
            final Shape shape4 = shape2;
            final long j = color3;
            final int i11 = $dirty2;
            final BorderStroke borderStroke = border2;
            final float f = elevation2;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            final boolean z2 = enabled2;
            $composer2 = $composer3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor3)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m5272boximpl(absoluteElevation))}, ComposableLambdaKt.composableLambda($composer2, -1391199439, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$7
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
                    ComposerKt.sourceInformation($composer4, "C349@17344L7,347@17215L221,358@17697L16,342@17028L869:Surface.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1391199439, $changed2, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:341)");
                        }
                        Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier4);
                        Shape shape5 = shape4;
                        long j2 = j;
                        ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localElevationOverlay);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Modifier modifier$iv = SelectableKt.m720selectableO2vRcR0$default(SurfaceKt.m1216surface8ww4TTg(modifierMinimumInteractiveComponentSize, shape5, SurfaceKt.m1217surfaceColorAtElevationcq6XJ1M(j2, (ElevationOverlay) objConsume2, absoluteElevation, $composer4, (i11 >> 15) & 14), borderStroke, f), selected, mutableInteractionSource, RippleKt.m1298rememberRipple9IZ8Weo(false, 0.0f, 0L, $composer4, 0, 7), z2, null, onClick, 16, null);
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i12 = $dirty5;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, true, $composer4, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv = (384 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
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
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i13 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i14 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 1505981553, "C364@17878L9:Surface.kt#jmzs0o");
                        function2.invoke($composer4, Integer.valueOf(i12 & 14));
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
            }), $composer2, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier2;
        final boolean z3 = enabled2;
        final Shape shape5 = shape2;
        final long j2 = color3;
        final long j3 = contentColor3;
        final BorderStroke borderStroke2 = border2;
        final float f2 = elevation2;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$8
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
                SurfaceKt.m1212SurfaceNy5ogXk(selected, onClick, modifier5, z3, shape5, j2, j3, borderStroke2, f2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX INFO: renamed from: Surface-Ny5ogXk, reason: not valid java name */
    public static final void m1213SurfaceNy5ogXk(final boolean checked, final Function1<? super Boolean, Unit> onCheckedChange, Modifier modifier, boolean enabled, Shape shape, long color, long contentColor, BorderStroke border, float elevation, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        int i2;
        int $dirty;
        long color2;
        long contentColor2;
        BorderStroke border2;
        float elevation2;
        MutableInteractionSource interactionSource2;
        Modifier modifier2;
        int $dirty2;
        long contentColor3;
        boolean enabled2;
        long color3;
        Shape shape2;
        Object value$iv$iv;
        Composer $composer2;
        int $dirty3;
        Intrinsics.checkNotNullParameter(onCheckedChange, "onCheckedChange");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(1341569296);
        ComposerKt.sourceInformation($composer3, "C(Surface)P(1,9,8,6,10,2:c#ui.graphics.Color,4:c#ui.graphics.Color!1,5:c#ui.unit.Dp,7)445@22417L6,446@22459L22,449@22593L39,*452@22725L7,453@22749L1034:Surface.kt#jmzs0o");
        int $dirty4 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty4 |= $composer3.changed(checked) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty4 |= $composer3.changedInstance(onCheckedChange) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty4 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 7168) == 0) {
            z = enabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty4 |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty4 |= $composer3.changed(shape) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            if ((i & 32) == 0) {
                i2 = i4;
                int i6 = $composer3.changed(color) ? 131072 : 65536;
                $dirty4 |= i6;
            } else {
                i2 = i4;
            }
            $dirty4 |= i6;
        } else {
            i2 = i4;
        }
        if (($changed & 3670016) == 0) {
            if ((i & 64) == 0) {
                $dirty3 = $dirty4;
                int i7 = $composer3.changed(contentColor) ? 1048576 : 524288;
                $dirty = $dirty3 | i7;
            } else {
                $dirty3 = $dirty4;
            }
            $dirty = $dirty3 | i7;
        } else {
            $dirty = $dirty4;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty |= 12582912;
        } else if ((29360128 & $changed) == 0) {
            $dirty |= $composer3.changed(border) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) == 0) {
            $dirty |= $composer3.changed(elevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty |= $composer3.changed(interactionSource) ? 536870912 : 268435456;
        }
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 14) == 0) {
            $dirty1 |= $composer3.changedInstance(content) ? 4 : 2;
        }
        if (($dirty & 1533916891) == 306783378 && ($dirty1 & 11) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier2 = modifier;
            shape2 = shape;
            color3 = color;
            contentColor3 = contentColor;
            border2 = border;
            elevation2 = elevation;
            interactionSource2 = interactionSource;
            $composer2 = $composer3;
            enabled2 = z;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier;
                boolean enabled3 = i2 != 0 ? true : z;
                Shape shape3 = i5 != 0 ? RectangleShapeKt.getRectangleShape() : shape;
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    color2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                } else {
                    color2 = color;
                }
                if ((i & 64) != 0) {
                    contentColor2 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer3, ($dirty >> 15) & 14);
                    $dirty &= -3670017;
                } else {
                    contentColor2 = contentColor;
                }
                BorderStroke border3 = i8 != 0 ? null : border;
                float elevation3 = i9 != 0 ? Dp.m5274constructorimpl(0) : elevation;
                if (i10 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    float elevation4 = elevation3;
                    Object it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    border2 = border3;
                    elevation2 = elevation4;
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                    modifier2 = modifier3;
                    $dirty2 = $dirty;
                    contentColor3 = contentColor2;
                    enabled2 = enabled3;
                    color3 = color2;
                    shape2 = shape3;
                } else {
                    border2 = border3;
                    elevation2 = elevation3;
                    interactionSource2 = interactionSource;
                    modifier2 = modifier3;
                    $dirty2 = $dirty;
                    contentColor3 = contentColor2;
                    enabled2 = enabled3;
                    color3 = color2;
                    shape2 = shape3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    modifier2 = modifier;
                    shape2 = shape;
                    color3 = color;
                    contentColor3 = contentColor;
                    border2 = border;
                    elevation2 = elevation;
                    interactionSource2 = interactionSource;
                    $dirty2 = $dirty & (-3670017);
                    enabled2 = z;
                } else {
                    modifier2 = modifier;
                    shape2 = shape;
                    color3 = color;
                    contentColor3 = contentColor;
                    border2 = border;
                    elevation2 = elevation;
                    interactionSource2 = interactionSource;
                    $dirty2 = $dirty;
                    enabled2 = z;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1341569296, $dirty2, $dirty1, "androidx.compose.material.Surface (Surface.kt:439)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            float arg0$iv = ((Dp) objConsume).m5288unboximpl();
            final float absoluteElevation = Dp.m5274constructorimpl(arg0$iv + elevation2);
            final Modifier modifier4 = modifier2;
            final int $dirty5 = $dirty1;
            final Shape shape4 = shape2;
            final long j = color3;
            final int i11 = $dirty2;
            final BorderStroke borderStroke = border2;
            final float f = elevation2;
            final MutableInteractionSource mutableInteractionSource = interactionSource2;
            final boolean z2 = enabled2;
            $composer2 = $composer3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor3)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m5272boximpl(absoluteElevation))}, ComposableLambdaKt.composableLambda($composer2, -311657392, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$10
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
                    ComposerKt.sourceInformation($composer4, "C464@23214L7,462@23085L221,473@23563L16,457@22898L879:Surface.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-311657392, $changed2, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:456)");
                        }
                        Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier4);
                        Shape shape5 = shape4;
                        long j2 = j;
                        ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer4.consume(localElevationOverlay);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Modifier modifier$iv = ToggleableKt.m724toggleableO2vRcR0$default(SurfaceKt.m1216surface8ww4TTg(modifierMinimumInteractiveComponentSize, shape5, SurfaceKt.m1217surfaceColorAtElevationcq6XJ1M(j2, (ElevationOverlay) objConsume2, absoluteElevation, $composer4, (i11 >> 15) & 14), borderStroke, f), checked, mutableInteractionSource, RippleKt.m1298rememberRipple9IZ8Weo(false, 0.0f, 0L, $composer4, 0, 7), z2, null, onCheckedChange, 16, null);
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i12 = $dirty5;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, true, $composer4, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv = (384 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
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
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i13 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i14 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 1505987433, "C479@23758L9:Surface.kt#jmzs0o");
                        function2.invoke($composer4, Integer.valueOf(i12 & 14));
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
            }), $composer2, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier5 = modifier2;
        final boolean z3 = enabled2;
        final Shape shape5 = shape2;
        final long j2 = color3;
        final long j3 = contentColor3;
        final BorderStroke borderStroke2 = border2;
        final float f2 = elevation2;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$11
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
                SurfaceKt.m1213SurfaceNy5ogXk(checked, onCheckedChange, modifier5, z3, shape5, j2, j3, borderStroke2, f2, mutableInteractionSource2, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This API is deprecated with the introduction a newer Surface function overload that accepts an onClick().", replaceWith = @ReplaceWith(expression = "Surface(onClick, modifier, enabled, shape, color, contentColor, border, elevation, interactionSource, content)", imports = {}))
    /* JADX INFO: renamed from: Surface-9VG74zQ, reason: not valid java name */
    public static final void m1209Surface9VG74zQ(final Function0<Unit> onClick, Modifier modifier, Shape shape, long color, long contentColor, BorderStroke border, float elevation, MutableInteractionSource interactionSource, Indication indication, boolean enabled, String onClickLabel, Role role, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int $changed1, final int i) {
        int $dirty;
        long color2;
        long contentColor2;
        MutableInteractionSource interactionSource2;
        Indication indication2;
        MutableInteractionSource interactionSource3;
        float elevation2;
        Role role2;
        Indication indication3;
        boolean enabled2;
        String onClickLabel2;
        BorderStroke border2;
        long color3;
        Modifier modifier2;
        Shape shape2;
        int $dirty2;
        Object value$iv$iv;
        Composer $composer2;
        Role role3;
        String onClickLabel3;
        boolean enabled3;
        Indication indication4;
        MutableInteractionSource interactionSource4;
        float elevation3;
        BorderStroke border3;
        long color4;
        Modifier modifier3;
        Shape shape3;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer3 = $composer.startRestartGroup(1585925488);
        ComposerKt.sourceInformation($composer3, "C(Surface)P(9,8,12,1:c#ui.graphics.Color,3:c#ui.graphics.Color!1,4:c#ui.unit.Dp,7,6,5,10,11:c#ui.semantics.Role)573@28985L6,574@29027L22,577@29161L39,578@29248L7,*584@29435L7,585@29459L1128:Surface.kt#jmzs0o");
        int $dirty3 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty3 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty3 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty3 |= $composer3.changed(shape) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty3 |= ((i & 8) == 0 && $composer3.changed(color)) ? 2048 : 1024;
        }
        if (($changed & 57344) == 0) {
            $dirty3 |= ((i & 16) == 0 && $composer3.changed(contentColor)) ? 16384 : 8192;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty3 |= $composer3.changed(border) ? 131072 : 65536;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty3 |= 1572864;
        } else if (($changed & 3670016) == 0) {
            $dirty3 |= $composer3.changed(elevation) ? 1048576 : 524288;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 29360128) == 0) {
            $dirty3 |= $composer3.changed(interactionSource) ? 8388608 : 4194304;
        }
        if (($changed & 234881024) == 0) {
            $dirty3 |= ((i & 256) == 0 && $composer3.changed(indication)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty3 |= 805306368;
        } else if (($changed & 1879048192) == 0) {
            $dirty3 |= $composer3.changed(enabled) ? 536870912 : 268435456;
        }
        int i8 = i & 1024;
        if (i8 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 14) == 0) {
            $dirty1 |= $composer3.changed(onClickLabel) ? 4 : 2;
        }
        int i9 = i & 2048;
        if (i9 != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 112) == 0) {
            $dirty1 |= $composer3.changed(role) ? 32 : 16;
        }
        if ((i & 4096) != 0) {
            $dirty1 |= 384;
        } else if (($changed1 & 896) == 0) {
            $dirty1 |= $composer3.changedInstance(content) ? 256 : 128;
        }
        final int $dirty4 = $dirty1;
        if ((1533916891 & $dirty3) == 306783378 && ($dirty4 & 731) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier;
            shape3 = shape;
            color4 = color;
            contentColor2 = contentColor;
            border3 = border;
            elevation3 = elevation;
            interactionSource4 = interactionSource;
            indication4 = indication;
            enabled3 = enabled;
            onClickLabel3 = onClickLabel;
            role3 = role;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                Shape shape4 = i3 != 0 ? RectangleShapeKt.getRectangleShape() : shape;
                if ((i & 8) != 0) {
                    $dirty = $dirty3 & (-7169);
                    color2 = MaterialTheme.INSTANCE.getColors($composer3, 6).m1052getSurface0d7_KjU();
                } else {
                    $dirty = $dirty3;
                    color2 = color;
                }
                if ((i & 16) != 0) {
                    contentColor2 = ColorsKt.m1066contentColorForek8zF_U(color2, $composer3, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                } else {
                    contentColor2 = contentColor;
                }
                BorderStroke border4 = i4 != 0 ? null : border;
                float elevation4 = i5 != 0 ? Dp.m5274constructorimpl(0) : elevation;
                if (i6 != 0) {
                    $composer3.startReplaceableGroup(-492369756);
                    ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
                    Object it$iv$iv = $composer3.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv$iv);
                    } else {
                        value$iv$iv = it$iv$iv;
                    }
                    $composer3.endReplaceableGroup();
                    interactionSource2 = (MutableInteractionSource) value$iv$iv;
                } else {
                    interactionSource2 = interactionSource;
                }
                if ((i & 256) != 0) {
                    ProvidableCompositionLocal<Indication> localIndication = IndicationKt.getLocalIndication();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localIndication);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    indication2 = (Indication) objConsume;
                    $dirty &= -234881025;
                } else {
                    indication2 = indication;
                }
                boolean enabled4 = i7 != 0 ? true : enabled;
                String onClickLabel4 = i8 != 0 ? null : onClickLabel;
                if (i9 != 0) {
                    interactionSource3 = interactionSource2;
                    elevation2 = elevation4;
                    indication3 = indication2;
                    enabled2 = enabled4;
                    onClickLabel2 = onClickLabel4;
                    role2 = null;
                    border2 = border4;
                    color3 = color2;
                    modifier2 = modifier4;
                    shape2 = shape4;
                    $dirty2 = $dirty;
                } else {
                    interactionSource3 = interactionSource2;
                    elevation2 = elevation4;
                    role2 = role;
                    indication3 = indication2;
                    enabled2 = enabled4;
                    onClickLabel2 = onClickLabel4;
                    border2 = border4;
                    color3 = color2;
                    modifier2 = modifier4;
                    shape2 = shape4;
                    $dirty2 = $dirty;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 256) != 0) {
                    modifier2 = modifier;
                    shape2 = shape;
                    color3 = color;
                    contentColor2 = contentColor;
                    border2 = border;
                    elevation2 = elevation;
                    interactionSource3 = interactionSource;
                    indication3 = indication;
                    enabled2 = enabled;
                    onClickLabel2 = onClickLabel;
                    role2 = role;
                    $dirty2 = $dirty3 & (-234881025);
                } else {
                    modifier2 = modifier;
                    shape2 = shape;
                    color3 = color;
                    contentColor2 = contentColor;
                    border2 = border;
                    elevation2 = elevation;
                    interactionSource3 = interactionSource;
                    indication3 = indication;
                    enabled2 = enabled;
                    onClickLabel2 = onClickLabel;
                    role2 = role;
                    $dirty2 = $dirty3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1585925488, $dirty2, $dirty4, "androidx.compose.material.Surface (Surface.kt:569)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            float arg0$iv = ((Dp) objConsume2).m5288unboximpl();
            final float absoluteElevation = Dp.m5274constructorimpl(arg0$iv + elevation2);
            final Modifier modifier5 = modifier2;
            final Shape shape5 = shape2;
            final long j = color3;
            final int i10 = $dirty2;
            final BorderStroke borderStroke = border2;
            final float f = elevation2;
            final MutableInteractionSource mutableInteractionSource = interactionSource3;
            final Indication indication5 = indication3;
            final boolean z = enabled2;
            final String str = onClickLabel2;
            final Role role4 = role2;
            $composer2 = $composer3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m2961boximpl(contentColor2)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m5272boximpl(absoluteElevation))}, ComposableLambdaKt.composableLambda($composer2, 149594672, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$13
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
                    ComposerKt.sourceInformation($composer4, "C596@29913L7,594@29784L221,589@29608L973:Surface.kt#jmzs0o");
                    if (($changed2 & 11) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(149594672, $changed2, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:588)");
                        }
                        Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier5);
                        Shape shape6 = shape5;
                        long j2 = j;
                        ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
                        ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer4.consume(localElevationOverlay);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Modifier modifier$iv = SurfaceKt.m1216surface8ww4TTg(modifierMinimumInteractiveComponentSize, shape6, SurfaceKt.m1217surfaceColorAtElevationcq6XJ1M(j2, (ElevationOverlay) objConsume3, absoluteElevation, $composer4, (i10 >> 9) & 14), borderStroke, f).then(ClickableKt.m190clickableO2vRcR0(Modifier.INSTANCE, mutableInteractionSource, indication5, z, str, role4, onClick));
                        Function2<Composer, Integer, Unit> function2 = content;
                        int i11 = $dirty4;
                        $composer4.startReplaceableGroup(733328855);
                        ComposerKt.sourceInformation($composer4, "CC(Box)P(2,1,3)69@3214L67,70@3286L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, true, $composer4, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        int $changed$iv$iv = (384 << 3) & 112;
                        $composer4.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation($composer4, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier$iv);
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
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2603constructorimpl($composer4);
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2610setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        function3ModifierMaterializerOf.invoke(SkippableUpdater.m2594boximpl(SkippableUpdater.m2595constructorimpl($composer4)), $composer4, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
                        $composer4.startReplaceableGroup(2058660585);
                        int i12 = ($changed$iv$iv$iv >> 9) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1253629358, "C71@3331L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i13 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 1505994237, "C614@30562L9:Surface.kt#jmzs0o");
                        function2.invoke($composer4, Integer.valueOf((i11 >> 6) & 14));
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
            }), $composer2, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            role3 = role2;
            onClickLabel3 = onClickLabel2;
            enabled3 = enabled2;
            indication4 = indication3;
            interactionSource4 = interactionSource3;
            elevation3 = elevation2;
            border3 = border2;
            color4 = color3;
            modifier3 = modifier2;
            shape3 = shape2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Modifier modifier6 = modifier3;
        final Shape shape6 = shape3;
        final long j2 = color4;
        final long j3 = contentColor2;
        final BorderStroke borderStroke2 = border3;
        final float f2 = elevation3;
        final MutableInteractionSource mutableInteractionSource2 = interactionSource4;
        final Indication indication6 = indication4;
        final boolean z2 = enabled3;
        final String str2 = onClickLabel3;
        final Role role5 = role3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$14
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

            public final void invoke(Composer composer, int i11) {
                SurfaceKt.m1209Surface9VG74zQ(onClick, modifier6, shape6, j2, j3, borderStroke2, f2, mutableInteractionSource2, indication6, z2, str2, role5, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: surface-8ww4TTg, reason: not valid java name */
    public static final Modifier m1216surface8ww4TTg(Modifier $this$surface_u2d8ww4TTg, Shape shape, long backgroundColor, BorderStroke border, float elevation) {
        Modifier modifierM2643shadows4CzXII$default = ShadowKt.m2643shadows4CzXII$default($this$surface_u2d8ww4TTg, elevation, shape, false, 0L, 0L, 24, null);
        Modifier.Companion companionBorder = Modifier.INSTANCE;
        if (border != null) {
            companionBorder = BorderKt.border(companionBorder, border, shape);
        }
        return ClipKt.clip(BackgroundKt.m159backgroundbw27NRU(modifierM2643shadows4CzXII$default.then(companionBorder), backgroundColor, shape), shape);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: surfaceColorAtElevation-cq6XJ1M, reason: not valid java name */
    public static final long m1217surfaceColorAtElevationcq6XJ1M(long color, ElevationOverlay elevationOverlay, float absoluteElevation, Composer $composer, int $changed) {
        long jMo1082apply7g2Lkgo;
        $composer.startReplaceableGroup(1561611256);
        ComposerKt.sourceInformation($composer, "C(surfaceColorAtElevation)P(1:c#ui.graphics.Color,2,0:c#ui.unit.Dp)635@31093L6,636@31164L31:Surface.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1561611256, $changed, -1, "androidx.compose.material.surfaceColorAtElevation (Surface.kt:630)");
        }
        if (Color.m2972equalsimpl0(color, MaterialTheme.INSTANCE.getColors($composer, 6).m1052getSurface0d7_KjU()) && elevationOverlay != null) {
            jMo1082apply7g2Lkgo = elevationOverlay.mo1082apply7g2Lkgo(color, absoluteElevation, $composer, ($changed & 14) | (($changed >> 3) & 112) | (($changed << 3) & 896));
        } else {
            jMo1082apply7g2Lkgo = color;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return jMo1082apply7g2Lkgo;
    }
}
