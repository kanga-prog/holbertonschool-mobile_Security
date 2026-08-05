package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ColorScheme.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b|\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001Bð\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010 Jµ\u0002\u0010|\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b}\u0010~J\t\u0010\u007f\u001a\u00030\u0080\u0001H\u0016R4\u0010\u0010\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R4\u0010\u0019\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b*\u0010'\u001a\u0004\b(\u0010#\"\u0004\b)\u0010%R4\u0010\u001b\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b-\u0010'\u001a\u0004\b+\u0010#\"\u0004\b,\u0010%R4\u0010\u0018\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b0\u0010'\u001a\u0004\b.\u0010#\"\u0004\b/\u0010%R4\u0010\u0007\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b3\u0010'\u001a\u0004\b1\u0010#\"\u0004\b2\u0010%R4\u0010\u0017\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b6\u0010'\u001a\u0004\b4\u0010#\"\u0004\b5\u0010%R4\u0010\u0011\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b9\u0010'\u001a\u0004\b7\u0010#\"\u0004\b8\u0010%R4\u0010\u001a\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b<\u0010'\u001a\u0004\b:\u0010#\"\u0004\b;\u0010%R4\u0010\u001c\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b?\u0010'\u001a\u0004\b=\u0010#\"\u0004\b>\u0010%R4\u0010\u0004\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bB\u0010'\u001a\u0004\b@\u0010#\"\u0004\bA\u0010%R4\u0010\u0006\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bE\u0010'\u001a\u0004\bC\u0010#\"\u0004\bD\u0010%R4\u0010\t\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bH\u0010'\u001a\u0004\bF\u0010#\"\u0004\bG\u0010%R4\u0010\u000b\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bK\u0010'\u001a\u0004\bI\u0010#\"\u0004\bJ\u0010%R4\u0010\u0013\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bN\u0010'\u001a\u0004\bL\u0010#\"\u0004\bM\u0010%R4\u0010\u0015\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bQ\u0010'\u001a\u0004\bO\u0010#\"\u0004\bP\u0010%R4\u0010\r\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bT\u0010'\u001a\u0004\bR\u0010#\"\u0004\bS\u0010%R4\u0010\u000f\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bW\u0010'\u001a\u0004\bU\u0010#\"\u0004\bV\u0010%R4\u0010\u001d\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bZ\u0010'\u001a\u0004\bX\u0010#\"\u0004\bY\u0010%R4\u0010\u001e\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b]\u0010'\u001a\u0004\b[\u0010#\"\u0004\b\\\u0010%R4\u0010\u0002\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b`\u0010'\u001a\u0004\b^\u0010#\"\u0004\b_\u0010%R4\u0010\u0005\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bc\u0010'\u001a\u0004\ba\u0010#\"\u0004\bb\u0010%R4\u0010\u001f\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bf\u0010'\u001a\u0004\bd\u0010#\"\u0004\be\u0010%R4\u0010\b\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bi\u0010'\u001a\u0004\bg\u0010#\"\u0004\bh\u0010%R4\u0010\n\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bl\u0010'\u001a\u0004\bj\u0010#\"\u0004\bk\u0010%R4\u0010\u0012\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bo\u0010'\u001a\u0004\bm\u0010#\"\u0004\bn\u0010%R4\u0010\u0016\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\br\u0010'\u001a\u0004\bp\u0010#\"\u0004\bq\u0010%R4\u0010\u0014\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bu\u0010'\u001a\u0004\bs\u0010#\"\u0004\bt\u0010%R4\u0010\f\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\bx\u0010'\u001a\u0004\bv\u0010#\"\u0004\bw\u0010%R4\u0010\u000e\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00038F@@X\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b{\u0010'\u001a\u0004\by\u0010#\"\u0004\bz\u0010%\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0081\u0001"}, d2 = {"Landroidx/compose/material3/ColorScheme;", "", "primary", "Landroidx/compose/ui/graphics/Color;", "onPrimary", "primaryContainer", "onPrimaryContainer", "inversePrimary", "secondary", "onSecondary", "secondaryContainer", "onSecondaryContainer", "tertiary", "onTertiary", "tertiaryContainer", "onTertiaryContainer", "background", "onBackground", "surface", "onSurface", "surfaceVariant", "onSurfaceVariant", "surfaceTint", "inverseSurface", "inverseOnSurface", "error", "onError", "errorContainer", "onErrorContainer", "outline", "outlineVariant", "scrim", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "<set-?>", "getBackground-0d7_KjU", "()J", "setBackground-8_81llA$material3_release", "(J)V", "background$delegate", "Landroidx/compose/runtime/MutableState;", "getError-0d7_KjU", "setError-8_81llA$material3_release", "error$delegate", "getErrorContainer-0d7_KjU", "setErrorContainer-8_81llA$material3_release", "errorContainer$delegate", "getInverseOnSurface-0d7_KjU", "setInverseOnSurface-8_81llA$material3_release", "inverseOnSurface$delegate", "getInversePrimary-0d7_KjU", "setInversePrimary-8_81llA$material3_release", "inversePrimary$delegate", "getInverseSurface-0d7_KjU", "setInverseSurface-8_81llA$material3_release", "inverseSurface$delegate", "getOnBackground-0d7_KjU", "setOnBackground-8_81llA$material3_release", "onBackground$delegate", "getOnError-0d7_KjU", "setOnError-8_81llA$material3_release", "onError$delegate", "getOnErrorContainer-0d7_KjU", "setOnErrorContainer-8_81llA$material3_release", "onErrorContainer$delegate", "getOnPrimary-0d7_KjU", "setOnPrimary-8_81llA$material3_release", "onPrimary$delegate", "getOnPrimaryContainer-0d7_KjU", "setOnPrimaryContainer-8_81llA$material3_release", "onPrimaryContainer$delegate", "getOnSecondary-0d7_KjU", "setOnSecondary-8_81llA$material3_release", "onSecondary$delegate", "getOnSecondaryContainer-0d7_KjU", "setOnSecondaryContainer-8_81llA$material3_release", "onSecondaryContainer$delegate", "getOnSurface-0d7_KjU", "setOnSurface-8_81llA$material3_release", "onSurface$delegate", "getOnSurfaceVariant-0d7_KjU", "setOnSurfaceVariant-8_81llA$material3_release", "onSurfaceVariant$delegate", "getOnTertiary-0d7_KjU", "setOnTertiary-8_81llA$material3_release", "onTertiary$delegate", "getOnTertiaryContainer-0d7_KjU", "setOnTertiaryContainer-8_81llA$material3_release", "onTertiaryContainer$delegate", "getOutline-0d7_KjU", "setOutline-8_81llA$material3_release", "outline$delegate", "getOutlineVariant-0d7_KjU", "setOutlineVariant-8_81llA$material3_release", "outlineVariant$delegate", "getPrimary-0d7_KjU", "setPrimary-8_81llA$material3_release", "primary$delegate", "getPrimaryContainer-0d7_KjU", "setPrimaryContainer-8_81llA$material3_release", "primaryContainer$delegate", "getScrim-0d7_KjU", "setScrim-8_81llA$material3_release", "scrim$delegate", "getSecondary-0d7_KjU", "setSecondary-8_81llA$material3_release", "secondary$delegate", "getSecondaryContainer-0d7_KjU", "setSecondaryContainer-8_81llA$material3_release", "secondaryContainer$delegate", "getSurface-0d7_KjU", "setSurface-8_81llA$material3_release", "surface$delegate", "getSurfaceTint-0d7_KjU", "setSurfaceTint-8_81llA$material3_release", "surfaceTint$delegate", "getSurfaceVariant-0d7_KjU", "setSurfaceVariant-8_81llA$material3_release", "surfaceVariant$delegate", "getTertiary-0d7_KjU", "setTertiary-8_81llA$material3_release", "tertiary$delegate", "getTertiaryContainer-0d7_KjU", "setTertiaryContainer-8_81llA$material3_release", "tertiaryContainer$delegate", "copy", "copy-G1PFc-w", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Landroidx/compose/material3/ColorScheme;", "toString", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ColorScheme {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: background$delegate, reason: from kotlin metadata */
    private final MutableState background;

    /* JADX INFO: renamed from: error$delegate, reason: from kotlin metadata */
    private final MutableState error;

    /* JADX INFO: renamed from: errorContainer$delegate, reason: from kotlin metadata */
    private final MutableState errorContainer;

    /* JADX INFO: renamed from: inverseOnSurface$delegate, reason: from kotlin metadata */
    private final MutableState inverseOnSurface;

    /* JADX INFO: renamed from: inversePrimary$delegate, reason: from kotlin metadata */
    private final MutableState inversePrimary;

    /* JADX INFO: renamed from: inverseSurface$delegate, reason: from kotlin metadata */
    private final MutableState inverseSurface;

    /* JADX INFO: renamed from: onBackground$delegate, reason: from kotlin metadata */
    private final MutableState onBackground;

    /* JADX INFO: renamed from: onError$delegate, reason: from kotlin metadata */
    private final MutableState onError;

    /* JADX INFO: renamed from: onErrorContainer$delegate, reason: from kotlin metadata */
    private final MutableState onErrorContainer;

    /* JADX INFO: renamed from: onPrimary$delegate, reason: from kotlin metadata */
    private final MutableState onPrimary;

    /* JADX INFO: renamed from: onPrimaryContainer$delegate, reason: from kotlin metadata */
    private final MutableState onPrimaryContainer;

    /* JADX INFO: renamed from: onSecondary$delegate, reason: from kotlin metadata */
    private final MutableState onSecondary;

    /* JADX INFO: renamed from: onSecondaryContainer$delegate, reason: from kotlin metadata */
    private final MutableState onSecondaryContainer;

    /* JADX INFO: renamed from: onSurface$delegate, reason: from kotlin metadata */
    private final MutableState onSurface;

    /* JADX INFO: renamed from: onSurfaceVariant$delegate, reason: from kotlin metadata */
    private final MutableState onSurfaceVariant;

    /* JADX INFO: renamed from: onTertiary$delegate, reason: from kotlin metadata */
    private final MutableState onTertiary;

    /* JADX INFO: renamed from: onTertiaryContainer$delegate, reason: from kotlin metadata */
    private final MutableState onTertiaryContainer;

    /* JADX INFO: renamed from: outline$delegate, reason: from kotlin metadata */
    private final MutableState outline;

    /* JADX INFO: renamed from: outlineVariant$delegate, reason: from kotlin metadata */
    private final MutableState outlineVariant;

    /* JADX INFO: renamed from: primary$delegate, reason: from kotlin metadata */
    private final MutableState primary;

    /* JADX INFO: renamed from: primaryContainer$delegate, reason: from kotlin metadata */
    private final MutableState primaryContainer;

    /* JADX INFO: renamed from: scrim$delegate, reason: from kotlin metadata */
    private final MutableState scrim;

    /* JADX INFO: renamed from: secondary$delegate, reason: from kotlin metadata */
    private final MutableState secondary;

    /* JADX INFO: renamed from: secondaryContainer$delegate, reason: from kotlin metadata */
    private final MutableState secondaryContainer;

    /* JADX INFO: renamed from: surface$delegate, reason: from kotlin metadata */
    private final MutableState surface;

    /* JADX INFO: renamed from: surfaceTint$delegate, reason: from kotlin metadata */
    private final MutableState surfaceTint;

    /* JADX INFO: renamed from: surfaceVariant$delegate, reason: from kotlin metadata */
    private final MutableState surfaceVariant;

    /* JADX INFO: renamed from: tertiary$delegate, reason: from kotlin metadata */
    private final MutableState tertiary;

    /* JADX INFO: renamed from: tertiaryContainer$delegate, reason: from kotlin metadata */
    private final MutableState tertiaryContainer;

    public /* synthetic */ ColorScheme(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29);
    }

    private ColorScheme(long primary, long onPrimary, long primaryContainer, long onPrimaryContainer, long inversePrimary, long secondary, long onSecondary, long secondaryContainer, long onSecondaryContainer, long tertiary, long onTertiary, long tertiaryContainer, long onTertiaryContainer, long background, long onBackground, long surface, long onSurface, long surfaceVariant, long onSurfaceVariant, long surfaceTint, long inverseSurface, long inverseOnSurface, long error, long onError, long errorContainer, long onErrorContainer, long outline, long outlineVariant, long scrim) {
        this.primary = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(primary), SnapshotStateKt.structuralEqualityPolicy());
        this.onPrimary = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(onPrimary), SnapshotStateKt.structuralEqualityPolicy());
        this.primaryContainer = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(primaryContainer), SnapshotStateKt.structuralEqualityPolicy());
        this.onPrimaryContainer = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(onPrimaryContainer), SnapshotStateKt.structuralEqualityPolicy());
        this.inversePrimary = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(inversePrimary), SnapshotStateKt.structuralEqualityPolicy());
        this.secondary = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(secondary), SnapshotStateKt.structuralEqualityPolicy());
        this.onSecondary = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(onSecondary), SnapshotStateKt.structuralEqualityPolicy());
        this.secondaryContainer = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(secondaryContainer), SnapshotStateKt.structuralEqualityPolicy());
        this.onSecondaryContainer = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(onSecondaryContainer), SnapshotStateKt.structuralEqualityPolicy());
        this.tertiary = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(tertiary), SnapshotStateKt.structuralEqualityPolicy());
        this.onTertiary = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(onTertiary), SnapshotStateKt.structuralEqualityPolicy());
        this.tertiaryContainer = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(tertiaryContainer), SnapshotStateKt.structuralEqualityPolicy());
        this.onTertiaryContainer = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(onTertiaryContainer), SnapshotStateKt.structuralEqualityPolicy());
        this.background = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(background), SnapshotStateKt.structuralEqualityPolicy());
        this.onBackground = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(onBackground), SnapshotStateKt.structuralEqualityPolicy());
        this.surface = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(surface), SnapshotStateKt.structuralEqualityPolicy());
        this.onSurface = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(onSurface), SnapshotStateKt.structuralEqualityPolicy());
        this.surfaceVariant = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(surfaceVariant), SnapshotStateKt.structuralEqualityPolicy());
        this.onSurfaceVariant = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(onSurfaceVariant), SnapshotStateKt.structuralEqualityPolicy());
        this.surfaceTint = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(surfaceTint), SnapshotStateKt.structuralEqualityPolicy());
        this.inverseSurface = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(inverseSurface), SnapshotStateKt.structuralEqualityPolicy());
        this.inverseOnSurface = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(inverseOnSurface), SnapshotStateKt.structuralEqualityPolicy());
        this.error = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(error), SnapshotStateKt.structuralEqualityPolicy());
        this.onError = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(onError), SnapshotStateKt.structuralEqualityPolicy());
        this.errorContainer = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(errorContainer), SnapshotStateKt.structuralEqualityPolicy());
        this.onErrorContainer = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(onErrorContainer), SnapshotStateKt.structuralEqualityPolicy());
        this.outline = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(outline), SnapshotStateKt.structuralEqualityPolicy());
        this.outlineVariant = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(outlineVariant), SnapshotStateKt.structuralEqualityPolicy());
        this.scrim = SnapshotStateKt.mutableStateOf(Color.m2961boximpl(scrim), SnapshotStateKt.structuralEqualityPolicy());
    }

    /* JADX INFO: renamed from: getPrimary-0d7_KjU, reason: not valid java name */
    public final long m1385getPrimary0d7_KjU() {
        State $this$getValue$iv = this.primary;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setPrimary-8_81llA$material3_release, reason: not valid java name */
    public final void m1414setPrimary8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.primary;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOnPrimary-0d7_KjU, reason: not valid java name */
    public final long m1375getOnPrimary0d7_KjU() {
        State $this$getValue$iv = this.onPrimary;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOnPrimary-8_81llA$material3_release, reason: not valid java name */
    public final void m1404setOnPrimary8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.onPrimary;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getPrimaryContainer-0d7_KjU, reason: not valid java name */
    public final long m1386getPrimaryContainer0d7_KjU() {
        State $this$getValue$iv = this.primaryContainer;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setPrimaryContainer-8_81llA$material3_release, reason: not valid java name */
    public final void m1415setPrimaryContainer8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.primaryContainer;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOnPrimaryContainer-0d7_KjU, reason: not valid java name */
    public final long m1376getOnPrimaryContainer0d7_KjU() {
        State $this$getValue$iv = this.onPrimaryContainer;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOnPrimaryContainer-8_81llA$material3_release, reason: not valid java name */
    public final void m1405setOnPrimaryContainer8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.onPrimaryContainer;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getInversePrimary-0d7_KjU, reason: not valid java name */
    public final long m1370getInversePrimary0d7_KjU() {
        State $this$getValue$iv = this.inversePrimary;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setInversePrimary-8_81llA$material3_release, reason: not valid java name */
    public final void m1399setInversePrimary8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.inversePrimary;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getSecondary-0d7_KjU, reason: not valid java name */
    public final long m1388getSecondary0d7_KjU() {
        State $this$getValue$iv = this.secondary;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setSecondary-8_81llA$material3_release, reason: not valid java name */
    public final void m1417setSecondary8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.secondary;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOnSecondary-0d7_KjU, reason: not valid java name */
    public final long m1377getOnSecondary0d7_KjU() {
        State $this$getValue$iv = this.onSecondary;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOnSecondary-8_81llA$material3_release, reason: not valid java name */
    public final void m1406setOnSecondary8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.onSecondary;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getSecondaryContainer-0d7_KjU, reason: not valid java name */
    public final long m1389getSecondaryContainer0d7_KjU() {
        State $this$getValue$iv = this.secondaryContainer;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setSecondaryContainer-8_81llA$material3_release, reason: not valid java name */
    public final void m1418setSecondaryContainer8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.secondaryContainer;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOnSecondaryContainer-0d7_KjU, reason: not valid java name */
    public final long m1378getOnSecondaryContainer0d7_KjU() {
        State $this$getValue$iv = this.onSecondaryContainer;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOnSecondaryContainer-8_81llA$material3_release, reason: not valid java name */
    public final void m1407setOnSecondaryContainer8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.onSecondaryContainer;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getTertiary-0d7_KjU, reason: not valid java name */
    public final long m1393getTertiary0d7_KjU() {
        State $this$getValue$iv = this.tertiary;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setTertiary-8_81llA$material3_release, reason: not valid java name */
    public final void m1422setTertiary8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.tertiary;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOnTertiary-0d7_KjU, reason: not valid java name */
    public final long m1381getOnTertiary0d7_KjU() {
        State $this$getValue$iv = this.onTertiary;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOnTertiary-8_81llA$material3_release, reason: not valid java name */
    public final void m1410setOnTertiary8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.onTertiary;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getTertiaryContainer-0d7_KjU, reason: not valid java name */
    public final long m1394getTertiaryContainer0d7_KjU() {
        State $this$getValue$iv = this.tertiaryContainer;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setTertiaryContainer-8_81llA$material3_release, reason: not valid java name */
    public final void m1423setTertiaryContainer8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.tertiaryContainer;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOnTertiaryContainer-0d7_KjU, reason: not valid java name */
    public final long m1382getOnTertiaryContainer0d7_KjU() {
        State $this$getValue$iv = this.onTertiaryContainer;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOnTertiaryContainer-8_81llA$material3_release, reason: not valid java name */
    public final void m1411setOnTertiaryContainer8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.onTertiaryContainer;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getBackground-0d7_KjU, reason: not valid java name */
    public final long m1366getBackground0d7_KjU() {
        State $this$getValue$iv = this.background;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setBackground-8_81llA$material3_release, reason: not valid java name */
    public final void m1395setBackground8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.background;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOnBackground-0d7_KjU, reason: not valid java name */
    public final long m1372getOnBackground0d7_KjU() {
        State $this$getValue$iv = this.onBackground;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOnBackground-8_81llA$material3_release, reason: not valid java name */
    public final void m1401setOnBackground8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.onBackground;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getSurface-0d7_KjU, reason: not valid java name */
    public final long m1390getSurface0d7_KjU() {
        State $this$getValue$iv = this.surface;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setSurface-8_81llA$material3_release, reason: not valid java name */
    public final void m1419setSurface8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.surface;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOnSurface-0d7_KjU, reason: not valid java name */
    public final long m1379getOnSurface0d7_KjU() {
        State $this$getValue$iv = this.onSurface;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOnSurface-8_81llA$material3_release, reason: not valid java name */
    public final void m1408setOnSurface8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.onSurface;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getSurfaceVariant-0d7_KjU, reason: not valid java name */
    public final long m1392getSurfaceVariant0d7_KjU() {
        State $this$getValue$iv = this.surfaceVariant;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setSurfaceVariant-8_81llA$material3_release, reason: not valid java name */
    public final void m1421setSurfaceVariant8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.surfaceVariant;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOnSurfaceVariant-0d7_KjU, reason: not valid java name */
    public final long m1380getOnSurfaceVariant0d7_KjU() {
        State $this$getValue$iv = this.onSurfaceVariant;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOnSurfaceVariant-8_81llA$material3_release, reason: not valid java name */
    public final void m1409setOnSurfaceVariant8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.onSurfaceVariant;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getSurfaceTint-0d7_KjU, reason: not valid java name */
    public final long m1391getSurfaceTint0d7_KjU() {
        State $this$getValue$iv = this.surfaceTint;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setSurfaceTint-8_81llA$material3_release, reason: not valid java name */
    public final void m1420setSurfaceTint8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.surfaceTint;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getInverseSurface-0d7_KjU, reason: not valid java name */
    public final long m1371getInverseSurface0d7_KjU() {
        State $this$getValue$iv = this.inverseSurface;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setInverseSurface-8_81llA$material3_release, reason: not valid java name */
    public final void m1400setInverseSurface8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.inverseSurface;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getInverseOnSurface-0d7_KjU, reason: not valid java name */
    public final long m1369getInverseOnSurface0d7_KjU() {
        State $this$getValue$iv = this.inverseOnSurface;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setInverseOnSurface-8_81llA$material3_release, reason: not valid java name */
    public final void m1398setInverseOnSurface8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.inverseOnSurface;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getError-0d7_KjU, reason: not valid java name */
    public final long m1367getError0d7_KjU() {
        State $this$getValue$iv = this.error;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setError-8_81llA$material3_release, reason: not valid java name */
    public final void m1396setError8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.error;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOnError-0d7_KjU, reason: not valid java name */
    public final long m1373getOnError0d7_KjU() {
        State $this$getValue$iv = this.onError;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOnError-8_81llA$material3_release, reason: not valid java name */
    public final void m1402setOnError8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.onError;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getErrorContainer-0d7_KjU, reason: not valid java name */
    public final long m1368getErrorContainer0d7_KjU() {
        State $this$getValue$iv = this.errorContainer;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setErrorContainer-8_81llA$material3_release, reason: not valid java name */
    public final void m1397setErrorContainer8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.errorContainer;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOnErrorContainer-0d7_KjU, reason: not valid java name */
    public final long m1374getOnErrorContainer0d7_KjU() {
        State $this$getValue$iv = this.onErrorContainer;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOnErrorContainer-8_81llA$material3_release, reason: not valid java name */
    public final void m1403setOnErrorContainer8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.onErrorContainer;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOutline-0d7_KjU, reason: not valid java name */
    public final long m1383getOutline0d7_KjU() {
        State $this$getValue$iv = this.outline;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOutline-8_81llA$material3_release, reason: not valid java name */
    public final void m1412setOutline8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.outline;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getOutlineVariant-0d7_KjU, reason: not valid java name */
    public final long m1384getOutlineVariant0d7_KjU() {
        State $this$getValue$iv = this.outlineVariant;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setOutlineVariant-8_81llA$material3_release, reason: not valid java name */
    public final void m1413setOutlineVariant8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.outlineVariant;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: getScrim-0d7_KjU, reason: not valid java name */
    public final long m1387getScrim0d7_KjU() {
        State $this$getValue$iv = this.scrim;
        return ((Color) $this$getValue$iv.getValue()).m2981unboximpl();
    }

    /* JADX INFO: renamed from: setScrim-8_81llA$material3_release, reason: not valid java name */
    public final void m1416setScrim8_81llA$material3_release(long j) {
        MutableState $this$setValue$iv = this.scrim;
        $this$setValue$iv.setValue(Color.m2961boximpl(j));
    }

    /* JADX INFO: renamed from: copy-G1PFc-w, reason: not valid java name */
    public final ColorScheme m1365copyG1PFcw(long primary, long onPrimary, long primaryContainer, long onPrimaryContainer, long inversePrimary, long secondary, long onSecondary, long secondaryContainer, long onSecondaryContainer, long tertiary, long onTertiary, long tertiaryContainer, long onTertiaryContainer, long background, long onBackground, long surface, long onSurface, long surfaceVariant, long onSurfaceVariant, long surfaceTint, long inverseSurface, long inverseOnSurface, long error, long onError, long errorContainer, long onErrorContainer, long outline, long outlineVariant, long scrim) {
        return new ColorScheme(primary, onPrimary, primaryContainer, onPrimaryContainer, inversePrimary, secondary, onSecondary, secondaryContainer, onSecondaryContainer, tertiary, onTertiary, tertiaryContainer, onTertiaryContainer, background, onBackground, surface, onSurface, surfaceVariant, onSurfaceVariant, surfaceTint, inverseSurface, inverseOnSurface, error, onError, errorContainer, onErrorContainer, outline, outlineVariant, scrim, null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ColorScheme(primary=").append((Object) Color.m2979toStringimpl(m1385getPrimary0d7_KjU())).append("onPrimary=").append((Object) Color.m2979toStringimpl(m1375getOnPrimary0d7_KjU())).append("primaryContainer=").append((Object) Color.m2979toStringimpl(m1386getPrimaryContainer0d7_KjU())).append("onPrimaryContainer=").append((Object) Color.m2979toStringimpl(m1376getOnPrimaryContainer0d7_KjU())).append("inversePrimary=").append((Object) Color.m2979toStringimpl(m1370getInversePrimary0d7_KjU())).append("secondary=").append((Object) Color.m2979toStringimpl(m1388getSecondary0d7_KjU())).append("onSecondary=").append((Object) Color.m2979toStringimpl(m1377getOnSecondary0d7_KjU())).append("secondaryContainer=").append((Object) Color.m2979toStringimpl(m1389getSecondaryContainer0d7_KjU())).append("onSecondaryContainer=").append((Object) Color.m2979toStringimpl(m1378getOnSecondaryContainer0d7_KjU())).append("tertiary=").append((Object) Color.m2979toStringimpl(m1393getTertiary0d7_KjU())).append("onTertiary=").append((Object) Color.m2979toStringimpl(m1381getOnTertiary0d7_KjU())).append("tertiaryContainer=");
        sb.append((Object) Color.m2979toStringimpl(m1394getTertiaryContainer0d7_KjU())).append("onTertiaryContainer=").append((Object) Color.m2979toStringimpl(m1382getOnTertiaryContainer0d7_KjU())).append("background=").append((Object) Color.m2979toStringimpl(m1366getBackground0d7_KjU())).append("onBackground=").append((Object) Color.m2979toStringimpl(m1372getOnBackground0d7_KjU())).append("surface=").append((Object) Color.m2979toStringimpl(m1390getSurface0d7_KjU())).append("onSurface=").append((Object) Color.m2979toStringimpl(m1379getOnSurface0d7_KjU())).append("surfaceVariant=").append((Object) Color.m2979toStringimpl(m1392getSurfaceVariant0d7_KjU())).append("onSurfaceVariant=").append((Object) Color.m2979toStringimpl(m1380getOnSurfaceVariant0d7_KjU())).append("surfaceTint=").append((Object) Color.m2979toStringimpl(m1391getSurfaceTint0d7_KjU())).append("inverseSurface=").append((Object) Color.m2979toStringimpl(m1371getInverseSurface0d7_KjU())).append("inverseOnSurface=").append((Object) Color.m2979toStringimpl(m1369getInverseOnSurface0d7_KjU())).append("error=").append((Object) Color.m2979toStringimpl(m1367getError0d7_KjU()));
        sb.append("onError=").append((Object) Color.m2979toStringimpl(m1373getOnError0d7_KjU())).append("errorContainer=").append((Object) Color.m2979toStringimpl(m1368getErrorContainer0d7_KjU())).append("onErrorContainer=").append((Object) Color.m2979toStringimpl(m1374getOnErrorContainer0d7_KjU())).append("outline=").append((Object) Color.m2979toStringimpl(m1383getOutline0d7_KjU())).append("outlineVariant=").append((Object) Color.m2979toStringimpl(m1384getOutlineVariant0d7_KjU())).append("scrim=").append((Object) Color.m2979toStringimpl(m1387getScrim0d7_KjU())).append(')');
        return sb.toString();
    }
}
