package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.tokens.FilledTextFieldTokens;
import androidx.compose.material3.tokens.OutlinedTextFieldTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\bC\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J7\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\u001c\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010'J¦\u0002\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u001f0,¢\u0006\u0002\b-2\u0006\u0010 \u001a\u00020!2\u0006\u0010.\u001a\u00020!2\u0006\u0010/\u001a\u0002002\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010\"\u001a\u00020!2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00103\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00104\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00105\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00106\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00107\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\b\b\u0002\u0010\u001c\u001a\u00020\u00152\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u00108\u001a\u0002092\u0013\b\u0002\u0010:\u001a\r\u0012\u0004\u0012\u00020\u001f0,¢\u0006\u0002\b-H\u0007¢\u0006\u0002\u0010;J7\u0010<\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\u001c\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010'JS\u0010=\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\u001c\u001a\u00020\u00152\b\b\u0002\u0010>\u001a\u00020\u00042\b\b\u0002\u0010?\u001a\u00020\u0004H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b@\u0010AJ\u009c\u0002\u0010B\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u001f0,¢\u0006\u0002\b-2\u0006\u0010 \u001a\u00020!2\u0006\u0010.\u001a\u00020!2\u0006\u0010/\u001a\u0002002\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010\"\u001a\u00020!2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00103\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00104\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00105\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00106\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00107\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u00108\u001a\u0002092\u0013\b\u0002\u0010:\u001a\r\u0012\u0004\u0012\u00020\u001f0,¢\u0006\u0002\b-H\u0007¢\u0006\u0002\u0010CJî\u0001\u0010B\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u001f0,¢\u0006\u0002\b-2\u0006\u0010 \u001a\u00020!2\u0006\u0010.\u001a\u00020!2\u0006\u0010/\u001a\u0002002\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010\"\u001a\u00020!2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00103\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00104\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00107\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u00108\u001a\u0002092\u0013\b\u0002\u0010:\u001a\r\u0012\u0004\u0012\u00020\u001f0,¢\u0006\u0002\b-H\u0007¢\u0006\u0002\u0010DJ¦\u0002\u0010E\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u001f0,¢\u0006\u0002\b-2\u0006\u0010 \u001a\u00020!2\u0006\u0010.\u001a\u00020!2\u0006\u0010/\u001a\u0002002\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010\"\u001a\u00020!2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00103\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00104\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00105\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00106\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00107\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\b\b\u0002\u0010\u001c\u001a\u00020\u00152\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u00108\u001a\u0002092\u0013\b\u0002\u0010:\u001a\r\u0012\u0004\u0012\u00020\u001f0,¢\u0006\u0002\b-H\u0007¢\u0006\u0002\u0010;Jø\u0001\u0010E\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020\u001f0,¢\u0006\u0002\b-2\u0006\u0010 \u001a\u00020!2\u0006\u0010.\u001a\u00020!2\u0006\u0010/\u001a\u0002002\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010\"\u001a\u00020!2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00103\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00104\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00107\u001a\u000f\u0012\u0004\u0012\u00020\u001f\u0018\u00010,¢\u0006\u0002\b-2\b\b\u0002\u0010\u001c\u001a\u00020\u00152\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u00108\u001a\u0002092\u0013\b\u0002\u0010:\u001a\r\u0012\u0004\u0012\u00020\u001f0,¢\u0006\u0002\b-H\u0007¢\u0006\u0002\u0010FJÃ\u0003\u0010%\u001a\u00020&2\b\b\u0002\u0010G\u001a\u00020H2\b\b\u0002\u0010I\u001a\u00020H2\b\b\u0002\u0010J\u001a\u00020H2\b\b\u0002\u0010K\u001a\u00020H2\b\b\u0002\u0010L\u001a\u00020H2\b\b\u0002\u0010M\u001a\u00020H2\b\b\u0002\u0010N\u001a\u00020H2\b\b\u0002\u0010O\u001a\u00020H2\b\b\u0002\u0010P\u001a\u00020H2\b\b\u0002\u0010Q\u001a\u00020H2\b\b\u0002\u0010R\u001a\u00020S2\b\b\u0002\u0010T\u001a\u00020H2\b\b\u0002\u0010U\u001a\u00020H2\b\b\u0002\u0010V\u001a\u00020H2\b\b\u0002\u0010W\u001a\u00020H2\b\b\u0002\u0010X\u001a\u00020H2\b\b\u0002\u0010Y\u001a\u00020H2\b\b\u0002\u0010Z\u001a\u00020H2\b\b\u0002\u0010[\u001a\u00020H2\b\b\u0002\u0010\\\u001a\u00020H2\b\b\u0002\u0010]\u001a\u00020H2\b\b\u0002\u0010^\u001a\u00020H2\b\b\u0002\u0010_\u001a\u00020H2\b\b\u0002\u0010`\u001a\u00020H2\b\b\u0002\u0010a\u001a\u00020H2\b\b\u0002\u0010b\u001a\u00020H2\b\b\u0002\u0010c\u001a\u00020H2\b\b\u0002\u0010d\u001a\u00020H2\b\b\u0002\u0010e\u001a\u00020H2\b\b\u0002\u0010f\u001a\u00020H2\b\b\u0002\u0010g\u001a\u00020H2\b\b\u0002\u0010h\u001a\u00020H2\b\b\u0002\u0010i\u001a\u00020H2\b\b\u0002\u0010j\u001a\u00020H2\b\b\u0002\u0010k\u001a\u00020H2\b\b\u0002\u0010l\u001a\u00020H2\b\b\u0002\u0010m\u001a\u00020H2\b\b\u0002\u0010n\u001a\u00020H2\b\b\u0002\u0010o\u001a\u00020H2\b\b\u0002\u0010p\u001a\u00020H2\b\b\u0002\u0010q\u001a\u00020H2\b\b\u0002\u0010r\u001a\u00020H2\b\b\u0002\u0010s\u001a\u00020HH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bt\u0010uJ;\u0010v\u001a\u0002092\b\b\u0002\u0010w\u001a\u00020\u00042\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b{\u0010|J;\u0010}\u001a\u0002092\b\b\u0002\u0010w\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b~\u0010|J\u0086\u0003\u0010\u007f\u001a\u00020&2\t\b\u0002\u0010\u0080\u0001\u001a\u00020H2\b\b\u0002\u0010J\u001a\u00020H2\t\b\u0002\u0010\u0081\u0001\u001a\u00020H2\b\b\u0002\u0010P\u001a\u00020H2\b\b\u0002\u0010Q\u001a\u00020H2\b\b\u0002\u0010R\u001a\u00020S2\t\b\u0002\u0010\u0082\u0001\u001a\u00020H2\t\b\u0002\u0010\u0083\u0001\u001a\u00020H2\t\b\u0002\u0010\u0084\u0001\u001a\u00020H2\t\b\u0002\u0010\u0085\u0001\u001a\u00020H2\b\b\u0002\u0010X\u001a\u00020H2\b\b\u0002\u0010Y\u001a\u00020H2\b\b\u0002\u0010Z\u001a\u00020H2\b\b\u0002\u0010[\u001a\u00020H2\b\b\u0002\u0010\\\u001a\u00020H2\b\b\u0002\u0010]\u001a\u00020H2\b\b\u0002\u0010^\u001a\u00020H2\b\b\u0002\u0010_\u001a\u00020H2\b\b\u0002\u0010`\u001a\u00020H2\b\b\u0002\u0010a\u001a\u00020H2\b\b\u0002\u0010b\u001a\u00020H2\b\b\u0002\u0010c\u001a\u00020H2\t\b\u0002\u0010\u0086\u0001\u001a\u00020H2\b\b\u0002\u0010f\u001a\u00020H2\b\b\u0002\u0010h\u001a\u00020H2\b\b\u0002\u0010i\u001a\u00020H2\b\b\u0002\u0010j\u001a\u00020H2\b\b\u0002\u0010k\u001a\u00020H2\b\b\u0002\u0010l\u001a\u00020H2\b\b\u0002\u0010m\u001a\u00020H2\b\b\u0002\u0010n\u001a\u00020H2\b\b\u0002\u0010o\u001a\u00020H2\b\b\u0002\u0010p\u001a\u00020H2\b\b\u0002\u0010q\u001a\u00020H2\b\b\u0002\u0010r\u001a\u00020H2\b\b\u0002\u0010s\u001a\u00020HH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001J¶\u0003\u0010\u007f\u001a\u00020&2\b\b\u0002\u0010G\u001a\u00020H2\b\b\u0002\u0010I\u001a\u00020H2\b\b\u0002\u0010J\u001a\u00020H2\b\b\u0002\u0010K\u001a\u00020H2\t\b\u0002\u0010\u0081\u0001\u001a\u00020H2\b\b\u0002\u0010O\u001a\u00020H2\b\b\u0002\u0010P\u001a\u00020H2\b\b\u0002\u0010Q\u001a\u00020H2\b\b\u0002\u0010R\u001a\u00020S2\t\b\u0002\u0010\u0082\u0001\u001a\u00020H2\t\b\u0002\u0010\u0083\u0001\u001a\u00020H2\t\b\u0002\u0010\u0084\u0001\u001a\u00020H2\t\b\u0002\u0010\u0085\u0001\u001a\u00020H2\b\b\u0002\u0010X\u001a\u00020H2\b\b\u0002\u0010Y\u001a\u00020H2\b\b\u0002\u0010Z\u001a\u00020H2\b\b\u0002\u0010[\u001a\u00020H2\b\b\u0002\u0010\\\u001a\u00020H2\b\b\u0002\u0010]\u001a\u00020H2\b\b\u0002\u0010^\u001a\u00020H2\b\b\u0002\u0010_\u001a\u00020H2\b\b\u0002\u0010`\u001a\u00020H2\b\b\u0002\u0010a\u001a\u00020H2\b\b\u0002\u0010b\u001a\u00020H2\b\b\u0002\u0010c\u001a\u00020H2\b\b\u0002\u0010d\u001a\u00020H2\b\b\u0002\u0010e\u001a\u00020H2\b\b\u0002\u0010f\u001a\u00020H2\b\b\u0002\u0010g\u001a\u00020H2\b\b\u0002\u0010h\u001a\u00020H2\b\b\u0002\u0010i\u001a\u00020H2\b\b\u0002\u0010j\u001a\u00020H2\b\b\u0002\u0010k\u001a\u00020H2\b\b\u0002\u0010l\u001a\u00020H2\b\b\u0002\u0010m\u001a\u00020H2\b\b\u0002\u0010n\u001a\u00020H2\b\b\u0002\u0010o\u001a\u00020H2\b\b\u0002\u0010p\u001a\u00020H2\b\b\u0002\u0010q\u001a\u00020H2\b\b\u0002\u0010r\u001a\u00020H2\b\b\u0002\u0010s\u001a\u00020HH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J?\u0010\u008b\u0001\u001a\u0002092\b\b\u0002\u0010w\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u0004H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0005\b\u008c\u0001\u0010|J?\u0010\u008d\u0001\u001a\u0002092\b\b\u0002\u0010w\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u0004H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0005\b\u008e\u0001\u0010|J\u0083\u0003\u0010\u008f\u0001\u001a\u00020&2\t\b\u0002\u0010\u0080\u0001\u001a\u00020H2\b\b\u0002\u0010J\u001a\u00020H2\t\b\u0002\u0010\u0081\u0001\u001a\u00020H2\b\b\u0002\u0010P\u001a\u00020H2\b\b\u0002\u0010Q\u001a\u00020H2\b\b\u0002\u0010R\u001a\u00020S2\b\b\u0002\u0010T\u001a\u00020H2\b\b\u0002\u0010U\u001a\u00020H2\b\b\u0002\u0010V\u001a\u00020H2\b\b\u0002\u0010W\u001a\u00020H2\b\b\u0002\u0010X\u001a\u00020H2\b\b\u0002\u0010Y\u001a\u00020H2\b\b\u0002\u0010Z\u001a\u00020H2\b\b\u0002\u0010[\u001a\u00020H2\b\b\u0002\u0010\\\u001a\u00020H2\b\b\u0002\u0010]\u001a\u00020H2\b\b\u0002\u0010^\u001a\u00020H2\b\b\u0002\u0010_\u001a\u00020H2\b\b\u0002\u0010`\u001a\u00020H2\b\b\u0002\u0010a\u001a\u00020H2\b\b\u0002\u0010b\u001a\u00020H2\b\b\u0002\u0010c\u001a\u00020H2\t\b\u0002\u0010\u0086\u0001\u001a\u00020H2\b\b\u0002\u0010f\u001a\u00020H2\b\b\u0002\u0010h\u001a\u00020H2\b\b\u0002\u0010i\u001a\u00020H2\b\b\u0002\u0010j\u001a\u00020H2\b\b\u0002\u0010k\u001a\u00020H2\b\b\u0002\u0010l\u001a\u00020H2\b\b\u0002\u0010m\u001a\u00020H2\b\b\u0002\u0010n\u001a\u00020H2\b\b\u0002\u0010o\u001a\u00020H2\b\b\u0002\u0010p\u001a\u00020H2\b\b\u0002\u0010q\u001a\u00020H2\b\b\u0002\u0010r\u001a\u00020H2\b\b\u0002\u0010s\u001a\u00020HH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0090\u0001\u0010\u0088\u0001J³\u0003\u0010\u008f\u0001\u001a\u00020&2\b\b\u0002\u0010G\u001a\u00020H2\b\b\u0002\u0010I\u001a\u00020H2\b\b\u0002\u0010J\u001a\u00020H2\b\b\u0002\u0010K\u001a\u00020H2\t\b\u0002\u0010\u0081\u0001\u001a\u00020H2\b\b\u0002\u0010O\u001a\u00020H2\b\b\u0002\u0010P\u001a\u00020H2\b\b\u0002\u0010Q\u001a\u00020H2\b\b\u0002\u0010R\u001a\u00020S2\b\b\u0002\u0010T\u001a\u00020H2\b\b\u0002\u0010U\u001a\u00020H2\b\b\u0002\u0010V\u001a\u00020H2\b\b\u0002\u0010W\u001a\u00020H2\b\b\u0002\u0010X\u001a\u00020H2\b\b\u0002\u0010Y\u001a\u00020H2\b\b\u0002\u0010Z\u001a\u00020H2\b\b\u0002\u0010[\u001a\u00020H2\b\b\u0002\u0010\\\u001a\u00020H2\b\b\u0002\u0010]\u001a\u00020H2\b\b\u0002\u0010^\u001a\u00020H2\b\b\u0002\u0010_\u001a\u00020H2\b\b\u0002\u0010`\u001a\u00020H2\b\b\u0002\u0010a\u001a\u00020H2\b\b\u0002\u0010b\u001a\u00020H2\b\b\u0002\u0010c\u001a\u00020H2\b\b\u0002\u0010d\u001a\u00020H2\b\b\u0002\u0010e\u001a\u00020H2\b\b\u0002\u0010f\u001a\u00020H2\b\b\u0002\u0010g\u001a\u00020H2\b\b\u0002\u0010h\u001a\u00020H2\b\b\u0002\u0010i\u001a\u00020H2\b\b\u0002\u0010j\u001a\u00020H2\b\b\u0002\u0010k\u001a\u00020H2\b\b\u0002\u0010l\u001a\u00020H2\b\b\u0002\u0010m\u001a\u00020H2\b\b\u0002\u0010n\u001a\u00020H2\b\b\u0002\u0010o\u001a\u00020H2\b\b\u0002\u0010p\u001a\u00020H2\b\b\u0002\u0010q\u001a\u00020H2\b\b\u0002\u0010r\u001a\u00020H2\b\b\u0002\u0010s\u001a\u00020HH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0091\u0001\u0010\u008a\u0001J?\u0010\u0092\u0001\u001a\u0002092\b\b\u0002\u0010w\u001a\u00020\u00042\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u0004H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0005\b\u0093\u0001\u0010|J?\u0010\u0094\u0001\u001a\u0002092\b\b\u0002\u0010w\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u0004H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0005\b\u0095\u0001\u0010|JT\u0010\u0096\u0001\u001a\u00030\u0097\u0001*\u00030\u0097\u00012\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\t\b\u0002\u0010\u0098\u0001\u001a\u00020\u00042\t\b\u0002\u0010\u0099\u0001\u001a\u00020\u0004H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001R'\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\t\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u001c\u0010\u000b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u001c\u0010\r\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R'\u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0010\u0010\u0002\u001a\u0004\b\u0011\u0010\u0007R\u001c\u0010\u0012\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0013\u0010\u0007R\u001a\u0010\u0014\u001a\u00020\u00158GX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0002\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u00158GX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001a\u0010\u0002\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u001c\u001a\u00020\u00158G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0018\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u009c\u0001"}, d2 = {"Landroidx/compose/material3/TextFieldDefaults;", "", "()V", "FocusedBorderThickness", "Landroidx/compose/ui/unit/Dp;", "getFocusedBorderThickness-D9Ej5fM$annotations", "getFocusedBorderThickness-D9Ej5fM", "()F", "F", "FocusedIndicatorThickness", "getFocusedIndicatorThickness-D9Ej5fM", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM$annotations", "getUnfocusedBorderThickness-D9Ej5fM", "UnfocusedIndicatorThickness", "getUnfocusedIndicatorThickness-D9Ej5fM", "filledShape", "Landroidx/compose/ui/graphics/Shape;", "getFilledShape$annotations", "getFilledShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "outlinedShape", "getOutlinedShape$annotations", "getOutlinedShape", "shape", "getShape", "ContainerBox", "", "enabled", "", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "colors", "Landroidx/compose/material3/TextFieldColors;", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)V", "DecorationBox", "value", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "singleLine", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "container", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "FilledContainerBox", "OutlinedBorderContainerBox", "focusedBorderThickness", "unfocusedBorderThickness", "OutlinedBorderContainerBox-nbWgWpA", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "OutlinedTextFieldDecorationBox", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "TextFieldDecorationBox", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "focusedTextColor", "Landroidx/compose/ui/graphics/Color;", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedSupportingTextColor", "unfocusedSupportingTextColor", "disabledSupportingTextColor", "errorSupportingTextColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "colors-0hiis_0", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIIII)Landroidx/compose/material3/TextFieldColors;", "contentPaddingWithLabel", "start", "end", "top", "bottom", "contentPaddingWithLabel-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "contentPaddingWithoutLabel", "contentPaddingWithoutLabel-a9UjIt4", "outlinedTextFieldColors", "textColor", "containerColor", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "placeholderColor", "outlinedTextFieldColors-eS1Emto", "(JJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIII)Landroidx/compose/material3/TextFieldColors;", "outlinedTextFieldColors-M37tBTI", "(JJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIIII)Landroidx/compose/material3/TextFieldColors;", "outlinedTextFieldPadding", "outlinedTextFieldPadding-a9UjIt4", "supportingTextPadding", "supportingTextPadding-a9UjIt4$material3_release", "textFieldColors", "textFieldColors-eS1Emto", "textFieldColors-M37tBTI", "textFieldWithLabelPadding", "textFieldWithLabelPadding-a9UjIt4", "textFieldWithoutLabelPadding", "textFieldWithoutLabelPadding-a9UjIt4", "indicatorLine", "Landroidx/compose/ui/Modifier;", "focusedIndicatorLineThickness", "unfocusedIndicatorLineThickness", "indicatorLine-gv0btCI", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;FF)Landroidx/compose/ui/Modifier;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldDefaults {
    public static final int $stable = 0;
    private static final float FocusedBorderThickness;
    private static final float FocusedIndicatorThickness;
    public static final TextFieldDefaults INSTANCE = new TextFieldDefaults();
    private static final float MinHeight = Dp.m5274constructorimpl(56);
    private static final float MinWidth = Dp.m5274constructorimpl(280);
    private static final float UnfocusedBorderThickness;
    private static final float UnfocusedIndicatorThickness;

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.shape`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.shape", imports = {}))
    public static /* synthetic */ void getFilledShape$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Split into `TextFieldDefaults.FocusedIndicatorThickness` and `OutlinedTextFieldDefaults.FocusedBorderThickness`. Please update as appropriate.", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.FocusedIndicatorThickness", imports = {}))
    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m1846getFocusedBorderThicknessD9Ej5fM$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `OutlinedTextFieldDefaults.shape`", replaceWith = @ReplaceWith(expression = "OutlinedTextFieldDefaults.shape", imports = {"androidx.compose.material.OutlinedTextFieldDefaults"}))
    public static /* synthetic */ void getOutlinedShape$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Split into `TextFieldDefaults.UnfocusedIndicatorThickness` and `OutlinedTextFieldDefaults.UnfocusedBorderThickness`. Please update as appropriate.", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.UnfocusedIndicatorThickness", imports = {}))
    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m1847getUnfocusedBorderThicknessD9Ej5fM$annotations() {
    }

    private TextFieldDefaults() {
    }

    public final Shape getShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(-1941327459);
        ComposerKt.sourceInformation($composer, "C57@2544L9:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1941327459, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-shape> (TextFieldDefaults.kt:57)");
        }
        Shape shape = ShapesKt.toShape(FilledTextFieldTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return shape;
    }

    static {
        float fM5274constructorimpl = Dp.m5274constructorimpl(1);
        UnfocusedIndicatorThickness = fM5274constructorimpl;
        float fM5274constructorimpl2 = Dp.m5274constructorimpl(2);
        FocusedIndicatorThickness = fM5274constructorimpl2;
        UnfocusedBorderThickness = fM5274constructorimpl;
        FocusedBorderThickness = fM5274constructorimpl2;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1859getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1860getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* JADX INFO: renamed from: getUnfocusedIndicatorThickness-D9Ej5fM, reason: not valid java name */
    public final float m1862getUnfocusedIndicatorThicknessD9Ej5fM() {
        return UnfocusedIndicatorThickness;
    }

    /* JADX INFO: renamed from: getFocusedIndicatorThickness-D9Ej5fM, reason: not valid java name */
    public final float m1858getFocusedIndicatorThicknessD9Ej5fM() {
        return FocusedIndicatorThickness;
    }

    /* JADX WARN: Code duplicated, block: B:83:0x010a  */
    public final void ContainerBox(final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, Shape shape, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        int $dirty;
        Shape shape3;
        Shape shape4;
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(colors, "colors");
        Composer $composer2 = $composer.startRestartGroup(918564008);
        ComposerKt.sourceInformation($composer2, "C(ContainerBox)P(1,3,2)100@4155L5,104@4239L51,102@4178L203:TextFieldDefaults.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(enabled) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changed(isError) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer2.changed(interactionSource) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if ((57344 & $changed) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i2 = $composer2.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i2;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i2;
        } else {
            shape2 = shape;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((458752 & $changed) == 0) {
            $dirty2 |= $composer2.changed(this) ? 131072 : 65536;
        }
        if ((374491 & $dirty2) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            shape4 = shape2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty = $dirty2 & (-57345);
                    shape3 = shape2;
                } else {
                    $dirty = $dirty2;
                    shape3 = shape2;
                }
            } else if ((i & 16) != 0) {
                $dirty = $dirty2 & (-57345);
                shape3 = INSTANCE.getShape($composer2, 6);
            } else {
                $dirty = $dirty2;
                shape3 = shape2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(918564008, $dirty, -1, "androidx.compose.material3.TextFieldDefaults.ContainerBox (TextFieldDefaults.kt:95)");
            }
            shape4 = shape3;
            BoxKt.Box(m1848indicatorLinegv0btCI$default(this, BackgroundKt.m159backgroundbw27NRU(Modifier.INSTANCE, colors.containerColor$material3_release(enabled, isError, interactionSource, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168)).getValue().m2981unboximpl(), shape3), enabled, isError, interactionSource, colors, 0.0f, 0.0f, 48, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Shape shape5 = shape4;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.ContainerBox.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i3) {
                TextFieldDefaults.this.ContainerBox(enabled, isError, interactionSource, colors, shape5, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m1848indicatorLinegv0btCI$default(TextFieldDefaults textFieldDefaults, Modifier modifier, boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, float f, float f2, int i, Object obj) {
        float f3;
        float f4;
        if ((i & 16) == 0) {
            f3 = f;
        } else {
            f3 = FocusedIndicatorThickness;
        }
        if ((i & 32) == 0) {
            f4 = f2;
        } else {
            f4 = UnfocusedIndicatorThickness;
        }
        return textFieldDefaults.m1863indicatorLinegv0btCI(modifier, z, z2, interactionSource, textFieldColors, f3, f4);
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI, reason: not valid java name */
    public final Modifier m1863indicatorLinegv0btCI(Modifier indicatorLine, final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, final float focusedIndicatorLineThickness, final float unfocusedIndicatorLineThickness) {
        Intrinsics.checkNotNullParameter(indicatorLine, "$this$indicatorLine");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(colors, "colors");
        return ComposedModifierKt.composed(indicatorLine, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults$indicatorLine-gv0btCI$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                Intrinsics.checkNotNullParameter($this$null, "$this$null");
                $this$null.setName("indicatorLine");
                $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
                $this$null.getProperties().set("isError", Boolean.valueOf(isError));
                $this$null.getProperties().set("interactionSource", interactionSource);
                $this$null.getProperties().set("colors", colors);
                $this$null.getProperties().set("focusedIndicatorLineThickness", Dp.m5272boximpl(focusedIndicatorLineThickness));
                $this$null.getProperties().set("unfocusedIndicatorLineThickness", Dp.m5272boximpl(unfocusedIndicatorLineThickness));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material3.TextFieldDefaults$indicatorLine$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer $composer, int $changed) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                $composer.startReplaceableGroup(-891038934);
                ComposerKt.sourceInformation($composer, "C140@6028L217:TextFieldDefaults.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-891038934, $changed, -1, "androidx.compose.material3.TextFieldDefaults.indicatorLine.<anonymous> (TextFieldDefaults.kt:139)");
                }
                State stroke = TextFieldDefaultsKt.m1873animateBorderStrokeAsStateNuRrP5Q(enabled, isError, interactionSource, colors, focusedIndicatorLineThickness, unfocusedIndicatorLineThickness, $composer, 0);
                Modifier modifierDrawIndicatorLine = TextFieldKt.drawIndicatorLine(Modifier.INSTANCE, (BorderStroke) stroke.getValue());
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceableGroup();
                return modifierDrawIndicatorLine;
            }
        });
    }

    /* JADX INFO: renamed from: contentPaddingWithLabel-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1844contentPaddingWithLabela9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        return textFieldDefaults.m1855contentPaddingWithLabela9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: contentPaddingWithLabel-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1855contentPaddingWithLabela9UjIt4(float start, float end, float top, float bottom) {
        return PaddingKt.m483PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: contentPaddingWithoutLabel-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1845contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return textFieldDefaults.m1856contentPaddingWithoutLabela9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: contentPaddingWithoutLabel-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1856contentPaddingWithoutLabela9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m483PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: supportingTextPadding-a9UjIt4$material3_release$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1850supportingTextPaddinga9UjIt4$material3_release$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getSupportingTopPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = Dp.m5274constructorimpl(0);
        }
        return textFieldDefaults.m1867supportingTextPaddinga9UjIt4$material3_release(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: supportingTextPadding-a9UjIt4$material3_release, reason: not valid java name */
    public final PaddingValues m1867supportingTextPaddinga9UjIt4$material3_release(float start, float top, float end, float bottom) {
        return PaddingKt.m483PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: colors-0hiis_0, reason: not valid java name */
    public final TextFieldColors m1854colors0hiis_0(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int $changed4, int i, int i2) {
        long disabledTextColor2;
        SelectionColors selectionColors2;
        long disabledIndicatorColor2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long disabledLabelColor2;
        long disabledPlaceholderColor2;
        long disabledSupportingTextColor2;
        long disabledPrefixColor2;
        long disabledSuffixColor2;
        $composer.startReplaceableGroup(1513344955);
        ComposerKt.sourceInformation($composer, "C(colors)P(30:c#ui.graphics.Color,41:c#ui.graphics.Color,9:c#ui.graphics.Color,20:c#ui.graphics.Color,22:c#ui.graphics.Color,33:c#ui.graphics.Color,1:c#ui.graphics.Color,11:c#ui.graphics.Color,0:c#ui.graphics.Color,12:c#ui.graphics.Color,32,23:c#ui.graphics.Color,34:c#ui.graphics.Color,2:c#ui.graphics.Color,13:c#ui.graphics.Color,25:c#ui.graphics.Color,36:c#ui.graphics.Color,4:c#ui.graphics.Color,15:c#ui.graphics.Color,31:c#ui.graphics.Color,42:c#ui.graphics.Color,10:c#ui.graphics.Color,21:c#ui.graphics.Color,24:c#ui.graphics.Color,35:c#ui.graphics.Color,3:c#ui.graphics.Color,14:c#ui.graphics.Color,26:c#ui.graphics.Color,37:c#ui.graphics.Color,5:c#ui.graphics.Color,16:c#ui.graphics.Color,29:c#ui.graphics.Color,40:c#ui.graphics.Color,8:c#ui.graphics.Color,19:c#ui.graphics.Color,27:c#ui.graphics.Color,38:c#ui.graphics.Color,6:c#ui.graphics.Color,17:c#ui.graphics.Color,28:c#ui.graphics.Color,39:c#ui.graphics.Color,7:c#ui.graphics.Color,18:c#ui.graphics.Color)247@12192L9,248@12272L9,249@12359L9,251@12510L9,252@12597L9,253@12686L9,254@12774L9,255@12859L9,256@12932L9,257@13020L9,258@13103L7,259@13199L9,260@13294L9,261@13396L9,263@13572L9,264@13668L9,265@13761L9,266@13861L9,268@14031L9,269@14129L9,270@14224L9,271@14326L9,273@14499L9,274@14583L9,275@14664L9,276@14752L9,278@14904L9,279@15000L9,280@15098L9,281@15192L9,283@15356L9,284@15454L9,285@15549L9,286@15651L9,288@15822L9,289@15908L9,290@15996L9,291@16083L9,293@16237L9,294@16323L9,295@16411L9,296@16498L9,298@16652L9:TextFieldDefaults.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusInputColor(), $composer, 6) : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputColor(), $composer, 6) : unfocusedTextColor;
        if ((i & 4) != 0) {
            long color = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledTextColor2 = Color.m2969copywmQWz5c(color, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        long errorTextColor2 = (i & 8) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorInputColor(), $composer, 6) : errorTextColor;
        long focusedContainerColor2 = (i & 16) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getContainerColor(), $composer, 6) : focusedContainerColor;
        long unfocusedContainerColor2 = (i & 32) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getContainerColor(), $composer, 6) : unfocusedContainerColor;
        long disabledContainerColor2 = (i & 64) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getContainerColor(), $composer, 6) : disabledContainerColor;
        long errorContainerColor2 = (i & 128) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getContainerColor(), $composer, 6) : errorContainerColor;
        long cursorColor2 = (i & 256) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getCaretColor(), $composer, 6) : cursorColor;
        long errorCursorColor2 = (i & 512) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorFocusCaretColor(), $composer, 6) : errorCursorColor;
        if ((i & 1024) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) objConsume;
        } else {
            selectionColors2 = selectionColors;
        }
        long focusedIndicatorColor2 = (i & 2048) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusActiveIndicatorColor(), $composer, 6) : focusedIndicatorColor;
        long unfocusedIndicatorColor2 = (i & 4096) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getActiveIndicatorColor(), $composer, 6) : unfocusedIndicatorColor;
        if ((i & 8192) != 0) {
            long color2 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledActiveIndicatorColor(), $composer, 6);
            disabledIndicatorColor2 = Color.m2969copywmQWz5c(color2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color2) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color2) : 0.0f);
        } else {
            disabledIndicatorColor2 = disabledIndicatorColor;
        }
        long errorIndicatorColor2 = (i & 16384) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorActiveIndicatorColor(), $composer, 6) : errorIndicatorColor;
        long focusedLeadingIconColor2 = (32768 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusLeadingIconColor(), $composer, 6) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (65536 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getLeadingIconColor(), $composer, 6) : unfocusedLeadingIconColor;
        if ((131072 & i) != 0) {
            long color3 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), $composer, 6);
            disabledLeadingIconColor2 = Color.m2969copywmQWz5c(color3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color3) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color3) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (262144 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorLeadingIconColor(), $composer, 6) : errorLeadingIconColor;
        long focusedTrailingIconColor2 = (524288 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusTrailingIconColor(), $composer, 6) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (1048576 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getTrailingIconColor(), $composer, 6) : unfocusedTrailingIconColor;
        if ((2097152 & i) != 0) {
            long color4 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), $composer, 6);
            disabledTrailingIconColor2 = Color.m2969copywmQWz5c(color4, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color4) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color4) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color4) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color4) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (4194304 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorTrailingIconColor(), $composer, 6) : errorTrailingIconColor;
        long focusedLabelColor2 = (8388608 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusLabelColor(), $composer, 6) : focusedLabelColor;
        long unfocusedLabelColor2 = (16777216 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getLabelColor(), $composer, 6) : unfocusedLabelColor;
        if ((33554432 & i) != 0) {
            long color5 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledLabelColor(), $composer, 6);
            disabledLabelColor2 = Color.m2969copywmQWz5c(color5, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color5) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color5) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color5) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color5) : 0.0f);
        } else {
            disabledLabelColor2 = disabledLabelColor;
        }
        long errorLabelColor2 = (67108864 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorLabelColor(), $composer, 6) : errorLabelColor;
        long focusedPlaceholderColor2 = (134217728 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (268435456 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : unfocusedPlaceholderColor;
        if ((536870912 & i) != 0) {
            long color6 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledPlaceholderColor2 = Color.m2969copywmQWz5c(color6, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color6) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color6) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color6) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color6) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        long errorPlaceholderColor2 = (i & 1073741824) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : errorPlaceholderColor;
        long focusedSupportingTextColor2 = (i2 & 1) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusSupportingColor(), $composer, 6) : focusedSupportingTextColor;
        long unfocusedSupportingTextColor2 = (i2 & 2) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getSupportingColor(), $composer, 6) : unfocusedSupportingTextColor;
        if ((i2 & 4) != 0) {
            long color7 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledSupportingColor(), $composer, 6);
            disabledSupportingTextColor2 = Color.m2969copywmQWz5c(color7, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color7) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color7) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color7) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color7) : 0.0f);
        } else {
            disabledSupportingTextColor2 = disabledSupportingTextColor;
        }
        long errorSupportingTextColor2 = (i2 & 8) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorSupportingColor(), $composer, 6) : errorSupportingTextColor;
        long focusedPrefixColor2 = (i2 & 16) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : focusedPrefixColor;
        long unfocusedPrefixColor2 = (i2 & 32) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : unfocusedPrefixColor;
        if ((i2 & 64) != 0) {
            long color8 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6);
            disabledPrefixColor2 = Color.m2969copywmQWz5c(color8, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color8) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color8) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color8) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color8) : 0.0f);
        } else {
            disabledPrefixColor2 = disabledPrefixColor;
        }
        long errorPrefixColor2 = (i2 & 128) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : errorPrefixColor;
        long focusedSuffixColor2 = (i2 & 256) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 512) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : unfocusedSuffixColor;
        if ((i2 & 1024) != 0) {
            long color9 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6);
            disabledSuffixColor2 = Color.m2969copywmQWz5c(color9, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color9) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color9) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color9) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color9) : 0.0f);
        } else {
            disabledSuffixColor2 = disabledSuffixColor;
        }
        long errorSuffixColor2 = (i2 & 2048) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1513344955, $changed, $changed1, "androidx.compose.material3.TextFieldDefaults.colors (TextFieldDefaults.kt:246)");
        }
        TextFieldColors textFieldColors = new TextFieldColors(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, focusedContainerColor2, unfocusedContainerColor2, disabledContainerColor2, errorContainerColor2, cursorColor2, errorCursorColor2, selectionColors2, focusedIndicatorColor2, unfocusedIndicatorColor2, disabledIndicatorColor2, errorIndicatorColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, errorPlaceholderColor2, focusedSupportingTextColor2, unfocusedSupportingTextColor2, disabledSupportingTextColor2, errorSupportingTextColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return textFieldColors;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0156  */
    /* JADX WARN: Code duplicated, block: B:102:0x015c  */
    /* JADX WARN: Code duplicated, block: B:104:0x0164  */
    /* JADX WARN: Code duplicated, block: B:105:0x0167  */
    /* JADX WARN: Code duplicated, block: B:107:0x016c  */
    /* JADX WARN: Code duplicated, block: B:110:0x0172  */
    /* JADX WARN: Code duplicated, block: B:111:0x0177  */
    /* JADX WARN: Code duplicated, block: B:113:0x017b  */
    /* JADX WARN: Code duplicated, block: B:115:0x0183  */
    /* JADX WARN: Code duplicated, block: B:116:0x0186  */
    /* JADX WARN: Code duplicated, block: B:118:0x018b  */
    /* JADX WARN: Code duplicated, block: B:121:0x0191  */
    /* JADX WARN: Code duplicated, block: B:122:0x0196  */
    /* JADX WARN: Code duplicated, block: B:124:0x019a  */
    /* JADX WARN: Code duplicated, block: B:126:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:127:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:129:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:132:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:133:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:135:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:137:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:139:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:142:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:143:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:145:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:148:0x01de  */
    /* JADX WARN: Code duplicated, block: B:150:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:153:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:161:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:164:0x0203  */
    /* JADX WARN: Code duplicated, block: B:172:0x021c  */
    /* JADX WARN: Code duplicated, block: B:175:0x0222  */
    /* JADX WARN: Code duplicated, block: B:183:0x0238  */
    /* JADX WARN: Code duplicated, block: B:186:0x0240  */
    /* JADX WARN: Code duplicated, block: B:187:0x0247  */
    /* JADX WARN: Code duplicated, block: B:189:0x024b  */
    /* JADX WARN: Code duplicated, block: B:191:0x0253  */
    /* JADX WARN: Code duplicated, block: B:192:0x0256  */
    /* JADX WARN: Code duplicated, block: B:194:0x025b  */
    /* JADX WARN: Code duplicated, block: B:197:0x0263  */
    /* JADX WARN: Code duplicated, block: B:198:0x026a  */
    /* JADX WARN: Code duplicated, block: B:200:0x0270  */
    /* JADX WARN: Code duplicated, block: B:202:0x0278  */
    /* JADX WARN: Code duplicated, block: B:203:0x027b  */
    /* JADX WARN: Code duplicated, block: B:205:0x0280  */
    /* JADX WARN: Code duplicated, block: B:214:0x02bf  */
    /* JADX WARN: Code duplicated, block: B:216:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:229:0x0305 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:230:0x0307  */
    /* JADX WARN: Code duplicated, block: B:231:0x0309  */
    /* JADX WARN: Code duplicated, block: B:233:0x030d  */
    /* JADX WARN: Code duplicated, block: B:234:0x030f  */
    /* JADX WARN: Code duplicated, block: B:236:0x0313  */
    /* JADX WARN: Code duplicated, block: B:237:0x0315  */
    /* JADX WARN: Code duplicated, block: B:239:0x0319  */
    /* JADX WARN: Code duplicated, block: B:240:0x031b  */
    /* JADX WARN: Code duplicated, block: B:242:0x031f  */
    /* JADX WARN: Code duplicated, block: B:243:0x0321  */
    /* JADX WARN: Code duplicated, block: B:245:0x0325  */
    /* JADX WARN: Code duplicated, block: B:246:0x0327  */
    /* JADX WARN: Code duplicated, block: B:248:0x032b  */
    /* JADX WARN: Code duplicated, block: B:249:0x032d  */
    /* JADX WARN: Code duplicated, block: B:251:0x0331  */
    /* JADX WARN: Code duplicated, block: B:252:0x0333  */
    /* JADX WARN: Code duplicated, block: B:255:0x0339  */
    /* JADX WARN: Code duplicated, block: B:256:0x0347  */
    /* JADX WARN: Code duplicated, block: B:259:0x0351  */
    /* JADX WARN: Code duplicated, block: B:260:0x03c8  */
    /* JADX WARN: Code duplicated, block: B:263:0x03ce A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:264:0x03d0  */
    /* JADX WARN: Code duplicated, block: B:265:0x03ef  */
    /* JADX WARN: Code duplicated, block: B:267:0x0414  */
    /* JADX WARN: Code duplicated, block: B:269:0x0418  */
    /* JADX WARN: Code duplicated, block: B:270:0x0456  */
    /* JADX WARN: Code duplicated, block: B:273:0x047d  */
    /* JADX WARN: Code duplicated, block: B:276:0x0523  */
    /* JADX WARN: Code duplicated, block: B:281:0x0545  */
    /* JADX WARN: Code duplicated, block: B:283:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:71:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:72:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:74:0x0103  */
    /* JADX WARN: Code duplicated, block: B:77:0x010b  */
    /* JADX WARN: Code duplicated, block: B:78:0x0112  */
    /* JADX WARN: Code duplicated, block: B:80:0x0116  */
    /* JADX WARN: Code duplicated, block: B:82:0x011e  */
    /* JADX WARN: Code duplicated, block: B:83:0x0121  */
    /* JADX WARN: Code duplicated, block: B:85:0x0126  */
    /* JADX WARN: Code duplicated, block: B:88:0x012c  */
    /* JADX WARN: Code duplicated, block: B:89:0x0133  */
    /* JADX WARN: Code duplicated, block: B:91:0x0139  */
    /* JADX WARN: Code duplicated, block: B:93:0x0141  */
    /* JADX WARN: Code duplicated, block: B:94:0x0144  */
    /* JADX WARN: Code duplicated, block: B:96:0x0149  */
    /* JADX WARN: Code duplicated, block: B:99:0x014f  */
    public final void DecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> innerTextField, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, Shape shape, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function9, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        boolean isError2;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Function2<? super Composer, ? super Integer, Unit> function16;
        Shape shape2;
        TextFieldColors colors2;
        PaddingValues contentPadding2;
        TextFieldColors colors3;
        Function2<? super Composer, ? super Integer, Unit> function2ComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function17;
        Function2<? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        boolean isError3;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Shape shape3;
        PaddingValues contentPadding3;
        Function2<? super Composer, ? super Integer, Unit> function22;
        int $dirty1;
        Composer $composer2;
        Function2<? super Composer, ? super Integer, Unit> function23;
        PaddingValues contentPadding4;
        TextFieldColors colors4;
        Shape shape4;
        Function2<? super Composer, ? super Integer, Unit> function24;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Function2<? super Composer, ? super Integer, Unit> function28;
        Function2<? super Composer, ? super Integer, Unit> function29;
        boolean isError4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
        Intrinsics.checkNotNullParameter(visualTransformation, "visualTransformation");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Composer $composer3 = $composer.startRestartGroup(289640444);
        ComposerKt.sourceInformation($composer3, "C(DecorationBox)P(16,4,3,12,17,5,6,7,9,8,15,10,13,14,11!1,2)425@24643L5,426@24684L8,437@25039L707:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer3.changedInstance(innerTextField) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer3.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(singleLine) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
                i2 = $composer3.changed(interactionSource) ? 131072 : 65536;
            }
            i3 = i & 64;
            if (i3 != 0) {
                $dirty |= 1572864;
            } else if (($changed & 3670016) != 0) {
                if ($composer3.changed(isError)) {
                    i4 = 1048576;
                } else {
                    i4 = 524288;
                }
                $dirty |= i4;
            }
            i5 = i & 128;
            if (i5 != 0) {
                $dirty |= 12582912;
            } else if (($changed & 29360128) != 0) {
                if ($composer3.changedInstance(function2)) {
                    i6 = 8388608;
                } else {
                    i6 = 4194304;
                }
                $dirty |= i6;
            }
            i7 = i & 256;
            if (i7 != 0) {
                $dirty |= 100663296;
            } else if (($changed & 234881024) != 0) {
                if ($composer3.changedInstance(function3)) {
                    i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i8 = 33554432;
                }
                $dirty |= i8;
            }
            i9 = i & 512;
            if (i9 != 0) {
                $dirty |= 805306368;
            } else if (($changed & 1879048192) != 0) {
                if ($composer3.changedInstance(function4)) {
                    i10 = 536870912;
                } else {
                    i10 = 268435456;
                }
                $dirty |= i10;
            }
            i11 = i & 1024;
            if (i11 != 0) {
                $dirty2 |= 6;
            } else if (($changed1 & 14) != 0) {
                if ($composer3.changedInstance(function5)) {
                    i12 = 4;
                } else {
                    i12 = 2;
                }
                $dirty2 |= i12;
            }
            i13 = i & 2048;
            if (i13 != 0) {
                $dirty2 |= 48;
            } else if (($changed1 & 112) != 0) {
                if ($composer3.changedInstance(function6)) {
                    i14 = 32;
                } else {
                    i14 = 16;
                }
                $dirty2 |= i14;
            }
            i15 = i & 4096;
            if (i15 != 0) {
                $dirty2 |= 384;
            } else if (($changed1 & 896) != 0) {
                $dirty2 |= $composer3.changedInstance(function7) ? 256 : 128;
            }
            i16 = i & 8192;
            if (i16 != 0) {
                $dirty2 |= 3072;
            } else if (($changed1 & 7168) != 0) {
                $dirty2 |= $composer3.changedInstance(function8) ? 2048 : 1024;
            }
            if (($changed1 & 57344) != 0) {
                $dirty2 |= ((i & 16384) == 0 || !$composer3.changed(shape)) ? 8192 : 16384;
            }
            if (($changed1 & 458752) != 0) {
                $dirty2 |= ((i & 32768) == 0 || !$composer3.changed(colors)) ? 65536 : 131072;
            }
            if (($changed1 & 3670016) != 0) {
                $dirty2 |= ((i & 65536) == 0 || !$composer3.changed(contentPadding)) ? 524288 : 1048576;
            }
            i17 = i & 131072;
            if (i17 != 0) {
                $dirty2 |= 12582912;
            } else if (($changed1 & 29360128) != 0) {
                if ($composer3.changedInstance(function9)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                $dirty2 |= i18;
            }
            if ((i & 262144) != 0) {
                $dirty2 |= 100663296;
            } else if (($changed1 & 234881024) != 0) {
                if ($composer3.changed(this)) {
                    i19 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i19 = 33554432;
                }
                $dirty2 |= i19;
            }
            if (($dirty & 1533916891) != 306783378 && (191739611 & $dirty2) == 38347922 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                isError4 = isError;
                function29 = function2;
                function28 = function3;
                function12 = function4;
                function27 = function5;
                function26 = function6;
                function25 = function7;
                function24 = function8;
                shape4 = shape;
                colors4 = colors;
                contentPadding4 = contentPadding;
                function23 = function9;
                $composer2 = $composer3;
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        isError2 = false;
                    } else {
                        isError2 = isError;
                    }
                    if (i5 != 0) {
                        function10 = null;
                    } else {
                        function10 = function2;
                    }
                    if (i7 != 0) {
                        function11 = null;
                    } else {
                        function11 = function3;
                    }
                    if (i9 != 0) {
                        function12 = null;
                    } else {
                        function12 = function4;
                    }
                    if (i11 != 0) {
                        function13 = null;
                    } else {
                        function13 = function5;
                    }
                    if (i13 != 0) {
                        function14 = null;
                    } else {
                        function14 = function6;
                    }
                    if (i15 != 0) {
                        function15 = null;
                    } else {
                        function15 = function7;
                    }
                    if (i16 != 0) {
                        function16 = null;
                    } else {
                        function16 = function8;
                    }
                    if ((i & 16384) != 0) {
                        shape2 = INSTANCE.getShape($composer3, 6);
                        $dirty2 &= -57345;
                    } else {
                        shape2 = shape;
                    }
                    if ((32768 & i) != 0) {
                        colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 15) & 7168, Integer.MAX_VALUE, 4095);
                        $dirty2 &= -458753;
                    } else {
                        colors2 = colors;
                    }
                    if ((i & 65536) != 0) {
                        if (function10 == null) {
                            contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        } else {
                            contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        }
                        $dirty2 &= -3670017;
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i17 != 0) {
                        final boolean z = isError2;
                        final TextFieldColors textFieldColors = colors2;
                        final Shape shape5 = shape2;
                        final int i20 = $dirty;
                        final int i21 = $dirty2;
                        colors3 = colors2;
                        function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -435523791, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.DecorationBox.1
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
                                ComposerKt.sourceInformation($composer4, "C434@24948L64:TextFieldDefaults.kt#uh7d8r");
                                if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-435523791, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:433)");
                                }
                                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                                boolean z2 = enabled;
                                boolean z3 = z;
                                InteractionSource interactionSource2 = interactionSource;
                                TextFieldColors textFieldColors2 = textFieldColors;
                                Shape shape6 = shape5;
                                int i22 = i20;
                                int i23 = ((i22 >> 9) & 896) | ((i22 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i22 >> 15) & 112);
                                int i24 = i21;
                                textFieldDefaults.ContainerBox(z2, z3, interactionSource2, textFieldColors2, shape6, $composer4, i23 | ((i24 >> 6) & 7168) | (i24 & 57344), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        });
                        function17 = function14;
                        function18 = function10;
                        function19 = function15;
                        function20 = function11;
                        isError3 = isError2;
                        function21 = function16;
                        shape3 = shape2;
                        contentPadding3 = contentPadding2;
                        function22 = function13;
                        $dirty1 = $dirty2;
                    } else {
                        colors3 = colors2;
                        function2ComposableLambda = function9;
                        function17 = function14;
                        function18 = function10;
                        function19 = function15;
                        function20 = function11;
                        isError3 = isError2;
                        function21 = function16;
                        shape3 = shape2;
                        contentPadding3 = contentPadding2;
                        function22 = function13;
                        $dirty1 = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 16384) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((32768 & i) != 0) {
                        $dirty2 &= -458753;
                    }
                    if ((i & 65536) != 0) {
                        $dirty2 &= -3670017;
                    }
                    isError3 = isError;
                    function18 = function2;
                    function20 = function3;
                    function12 = function4;
                    function22 = function5;
                    function17 = function6;
                    function19 = function7;
                    function21 = function8;
                    shape3 = shape;
                    colors3 = colors;
                    contentPadding3 = contentPadding;
                    function2ComposableLambda = function9;
                    $dirty1 = $dirty2;
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(289640444, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.DecorationBox (TextFieldDefaults.kt:410)");
                }
                int $dirty3 = $dirty;
                $composer2 = $composer3;
                TextFieldImplKt.CommonDecorationBox(TextFieldType.Filled, value, innerTextField, visualTransformation, function18, function20, function12, function22, function17, function19, function21, singleLine, enabled, isError3, interactionSource, contentPadding3, colors3, function2ComposableLambda, $composer2, (($dirty3 << 3) & 112) | 6 | (($dirty3 << 3) & 896) | (($dirty3 >> 3) & 7168) | (($dirty3 >> 9) & 57344) | (($dirty3 >> 9) & 458752) | (($dirty3 >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024) | (($dirty1 << 21) & 1879048192), (($dirty1 >> 9) & 14) | (($dirty3 >> 6) & 112) | ($dirty3 & 896) | (($dirty3 >> 9) & 7168) | (($dirty3 >> 3) & 57344) | (($dirty1 >> 3) & 458752) | (($dirty1 << 3) & 3670016) | ($dirty1 & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function23 = function2ComposableLambda;
                contentPadding4 = contentPadding3;
                colors4 = colors3;
                shape4 = shape3;
                function24 = function21;
                function25 = function19;
                function26 = function17;
                function27 = function22;
                function28 = function20;
                function29 = function18;
                isError4 = isError3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final boolean z2 = isError4;
            final Function2<? super Composer, ? super Integer, Unit> function30 = function29;
            final Function2<? super Composer, ? super Integer, Unit> function31 = function28;
            final Function2<? super Composer, ? super Integer, Unit> function32 = function12;
            final Function2<? super Composer, ? super Integer, Unit> function33 = function27;
            final Function2<? super Composer, ? super Integer, Unit> function34 = function26;
            final Function2<? super Composer, ? super Integer, Unit> function35 = function25;
            final Function2<? super Composer, ? super Integer, Unit> function36 = function24;
            final Shape shape6 = shape4;
            final TextFieldColors textFieldColors2 = colors4;
            final PaddingValues paddingValues = contentPadding4;
            final Function2<? super Composer, ? super Integer, Unit> function37 = function23;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.DecorationBox.2
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

                public final void invoke(Composer composer, int i22) {
                    TextFieldDefaults.this.DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z2, function30, function31, function32, function33, function34, function35, function36, shape6, textFieldColors2, paddingValues, function37, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty |= i2;
        i3 = i & 64;
        if (i3 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) != 0) {
            if ($composer3.changed(isError)) {
                i4 = 1048576;
            } else {
                i4 = 524288;
            }
            $dirty |= i4;
        }
        i5 = i & 128;
        if (i5 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 29360128) != 0) {
            if ($composer3.changedInstance(function2)) {
                i6 = 8388608;
            } else {
                i6 = 4194304;
            }
            $dirty |= i6;
        }
        i7 = i & 256;
        if (i7 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) != 0) {
            if ($composer3.changedInstance(function3)) {
                i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i8 = 33554432;
            }
            $dirty |= i8;
        }
        i9 = i & 512;
        if (i9 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) != 0) {
            if ($composer3.changedInstance(function4)) {
                i10 = 536870912;
            } else {
                i10 = 268435456;
            }
            $dirty |= i10;
        }
        i11 = i & 1024;
        if (i11 != 0) {
            $dirty2 |= 6;
        } else if (($changed1 & 14) != 0) {
            if ($composer3.changedInstance(function5)) {
                i12 = 4;
            } else {
                i12 = 2;
            }
            $dirty2 |= i12;
        }
        i13 = i & 2048;
        if (i13 != 0) {
            $dirty2 |= 48;
        } else if (($changed1 & 112) != 0) {
            if ($composer3.changedInstance(function6)) {
                i14 = 32;
            } else {
                i14 = 16;
            }
            $dirty2 |= i14;
        }
        i15 = i & 4096;
        if (i15 != 0) {
            $dirty2 |= 384;
        } else if (($changed1 & 896) != 0) {
            $dirty2 |= $composer3.changedInstance(function7) ? 256 : 128;
        }
        i16 = i & 8192;
        if (i16 != 0) {
            $dirty2 |= 3072;
        } else if (($changed1 & 7168) != 0) {
            $dirty2 |= $composer3.changedInstance(function8) ? 2048 : 1024;
        }
        if (($changed1 & 57344) != 0) {
            $dirty2 |= ((i & 16384) == 0 || !$composer3.changed(shape)) ? 8192 : 16384;
        }
        if (($changed1 & 458752) != 0) {
            $dirty2 |= ((i & 32768) == 0 || !$composer3.changed(colors)) ? 65536 : 131072;
        }
        if (($changed1 & 3670016) != 0) {
            $dirty2 |= ((i & 65536) == 0 || !$composer3.changed(contentPadding)) ? 524288 : 1048576;
        }
        i17 = i & 131072;
        if (i17 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed1 & 29360128) != 0) {
            if ($composer3.changedInstance(function9)) {
                i18 = 8388608;
            } else {
                i18 = 4194304;
            }
            $dirty2 |= i18;
        }
        if ((i & 262144) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed1 & 234881024) != 0) {
            if ($composer3.changed(this)) {
                i19 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i19 = 33554432;
            }
            $dirty2 |= i19;
        }
        if (($dirty & 1533916891) != 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i16 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape2 = shape;
                }
                if ((32768 & i) != 0) {
                    colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 15) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -458753;
                } else {
                    colors2 = colors;
                }
                if ((i & 65536) != 0) {
                    if (function10 == null) {
                        contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty2 &= -3670017;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i17 != 0) {
                    final boolean z3 = isError2;
                    final TextFieldColors textFieldColors3 = colors2;
                    final Shape shape7 = shape2;
                    final int i22 = $dirty;
                    final int i23 = $dirty2;
                    colors3 = colors2;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -435523791, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.DecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C434@24948L64:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-435523791, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:433)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z4 = enabled;
                            boolean z5 = z3;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors4 = textFieldColors3;
                            Shape shape8 = shape7;
                            int i24 = i22;
                            int i25 = ((i24 >> 9) & 896) | ((i24 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i24 >> 15) & 112);
                            int i26 = i23;
                            textFieldDefaults.ContainerBox(z4, z5, interactionSource2, textFieldColors4, shape8, $composer4, i25 | ((i26 >> 6) & 7168) | (i26 & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function17 = function14;
                    function18 = function10;
                    function19 = function15;
                    function20 = function11;
                    isError3 = isError2;
                    function21 = function16;
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    function22 = function13;
                    $dirty1 = $dirty2;
                } else {
                    colors3 = colors2;
                    function2ComposableLambda = function9;
                    function17 = function14;
                    function18 = function10;
                    function19 = function15;
                    function20 = function11;
                    isError3 = isError2;
                    function21 = function16;
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    function22 = function13;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i16 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape2 = shape;
                }
                if ((32768 & i) != 0) {
                    colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 15) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -458753;
                } else {
                    colors2 = colors;
                }
                if ((i & 65536) != 0) {
                    if (function10 == null) {
                        contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty2 &= -3670017;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i17 != 0) {
                    final boolean z4 = isError2;
                    final TextFieldColors textFieldColors4 = colors2;
                    final Shape shape8 = shape2;
                    final int i24 = $dirty;
                    final int i25 = $dirty2;
                    colors3 = colors2;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -435523791, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.DecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C434@24948L64:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-435523791, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:433)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z5 = enabled;
                            boolean z6 = z4;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors5 = textFieldColors4;
                            Shape shape9 = shape8;
                            int i26 = i24;
                            int i27 = ((i26 >> 9) & 896) | ((i26 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i26 >> 15) & 112);
                            int i28 = i25;
                            textFieldDefaults.ContainerBox(z5, z6, interactionSource2, textFieldColors5, shape9, $composer4, i27 | ((i28 >> 6) & 7168) | (i28 & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function17 = function14;
                    function18 = function10;
                    function19 = function15;
                    function20 = function11;
                    isError3 = isError2;
                    function21 = function16;
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    function22 = function13;
                    $dirty1 = $dirty2;
                } else {
                    colors3 = colors2;
                    function2ComposableLambda = function9;
                    function17 = function14;
                    function18 = function10;
                    function19 = function15;
                    function20 = function11;
                    isError3 = isError2;
                    function21 = function16;
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    function22 = function13;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(289640444, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.DecorationBox (TextFieldDefaults.kt:410)");
            }
            int $dirty4 = $dirty;
            $composer2 = $composer3;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Filled, value, innerTextField, visualTransformation, function18, function20, function12, function22, function17, function19, function21, singleLine, enabled, isError3, interactionSource, contentPadding3, colors3, function2ComposableLambda, $composer2, (($dirty4 << 3) & 112) | 6 | (($dirty4 << 3) & 896) | (($dirty4 >> 3) & 7168) | (($dirty4 >> 9) & 57344) | (($dirty4 >> 9) & 458752) | (($dirty4 >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024) | (($dirty1 << 21) & 1879048192), (($dirty1 >> 9) & 14) | (($dirty4 >> 6) & 112) | ($dirty4 & 896) | (($dirty4 >> 9) & 7168) | (($dirty4 >> 3) & 57344) | (($dirty1 >> 3) & 458752) | (($dirty1 << 3) & 3670016) | ($dirty1 & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function23 = function2ComposableLambda;
            contentPadding4 = contentPadding3;
            colors4 = colors3;
            shape4 = shape3;
            function24 = function21;
            function25 = function19;
            function26 = function17;
            function27 = function22;
            function28 = function20;
            function29 = function18;
            isError4 = isError3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i16 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape2 = shape;
                }
                if ((32768 & i) != 0) {
                    colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 15) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -458753;
                } else {
                    colors2 = colors;
                }
                if ((i & 65536) != 0) {
                    if (function10 == null) {
                        contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty2 &= -3670017;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i17 != 0) {
                    final boolean z5 = isError2;
                    final TextFieldColors textFieldColors5 = colors2;
                    final Shape shape9 = shape2;
                    final int i26 = $dirty;
                    final int i27 = $dirty2;
                    colors3 = colors2;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -435523791, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.DecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C434@24948L64:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-435523791, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:433)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z6 = enabled;
                            boolean z7 = z5;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors6 = textFieldColors5;
                            Shape shape10 = shape9;
                            int i28 = i26;
                            int i29 = ((i28 >> 9) & 896) | ((i28 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i28 >> 15) & 112);
                            int i210 = i27;
                            textFieldDefaults.ContainerBox(z6, z7, interactionSource2, textFieldColors6, shape10, $composer4, i29 | ((i210 >> 6) & 7168) | (i210 & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function17 = function14;
                    function18 = function10;
                    function19 = function15;
                    function20 = function11;
                    isError3 = isError2;
                    function21 = function16;
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    function22 = function13;
                    $dirty1 = $dirty2;
                } else {
                    colors3 = colors2;
                    function2ComposableLambda = function9;
                    function17 = function14;
                    function18 = function10;
                    function19 = function15;
                    function20 = function11;
                    isError3 = isError2;
                    function21 = function16;
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    function22 = function13;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i16 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape2 = shape;
                }
                if ((32768 & i) != 0) {
                    colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 15) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -458753;
                } else {
                    colors2 = colors;
                }
                if ((i & 65536) != 0) {
                    if (function10 == null) {
                        contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty2 &= -3670017;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i17 != 0) {
                    final boolean z6 = isError2;
                    final TextFieldColors textFieldColors6 = colors2;
                    final Shape shape10 = shape2;
                    final int i28 = $dirty;
                    final int i29 = $dirty2;
                    colors3 = colors2;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -435523791, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.DecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C434@24948L64:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-435523791, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:433)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z7 = enabled;
                            boolean z8 = z6;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors7 = textFieldColors6;
                            Shape shape11 = shape10;
                            int i210 = i28;
                            int i211 = ((i210 >> 9) & 896) | ((i210 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i210 >> 15) & 112);
                            int i212 = i29;
                            textFieldDefaults.ContainerBox(z7, z8, interactionSource2, textFieldColors7, shape11, $composer4, i211 | ((i212 >> 6) & 7168) | (i212 & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function17 = function14;
                    function18 = function10;
                    function19 = function15;
                    function20 = function11;
                    isError3 = isError2;
                    function21 = function16;
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    function22 = function13;
                    $dirty1 = $dirty2;
                } else {
                    colors3 = colors2;
                    function2ComposableLambda = function9;
                    function17 = function14;
                    function18 = function10;
                    function19 = function15;
                    function20 = function11;
                    isError3 = isError2;
                    function21 = function16;
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    function22 = function13;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(289640444, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.DecorationBox (TextFieldDefaults.kt:410)");
            }
            int $dirty5 = $dirty;
            $composer2 = $composer3;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Filled, value, innerTextField, visualTransformation, function18, function20, function12, function22, function17, function19, function21, singleLine, enabled, isError3, interactionSource, contentPadding3, colors3, function2ComposableLambda, $composer2, (($dirty5 << 3) & 112) | 6 | (($dirty5 << 3) & 896) | (($dirty5 >> 3) & 7168) | (($dirty5 >> 9) & 57344) | (($dirty5 >> 9) & 458752) | (($dirty5 >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024) | (($dirty1 << 21) & 1879048192), (($dirty1 >> 9) & 14) | (($dirty5 >> 6) & 112) | ($dirty5 & 896) | (($dirty5 >> 9) & 7168) | (($dirty5 >> 3) & 57344) | (($dirty1 >> 3) & 458752) | (($dirty1 << 3) & 3670016) | ($dirty1 & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function23 = function2ComposableLambda;
            contentPadding4 = contentPadding3;
            colors4 = colors3;
            shape4 = shape3;
            function24 = function21;
            function25 = function19;
            function26 = function17;
            function27 = function22;
            function28 = function20;
            function29 = function18;
            isError4 = isError3;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final boolean z7 = isError4;
        final Function2<? super Composer, ? super Integer, Unit> function38 = function29;
        final Function2<? super Composer, ? super Integer, Unit> function39 = function28;
        final Function2<? super Composer, ? super Integer, Unit> function310 = function12;
        final Function2<? super Composer, ? super Integer, Unit> function311 = function27;
        final Function2<? super Composer, ? super Integer, Unit> function312 = function26;
        final Function2<? super Composer, ? super Integer, Unit> function313 = function25;
        final Function2<? super Composer, ? super Integer, Unit> function314 = function24;
        final Shape shape11 = shape4;
        final TextFieldColors textFieldColors7 = colors4;
        final PaddingValues paddingValues2 = contentPadding4;
        final Function2<? super Composer, ? super Integer, Unit> function315 = function23;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.DecorationBox.2
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

            public final void invoke(Composer composer, int i210) {
                TextFieldDefaults.this.DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z7, function38, function39, function310, function311, function312, function313, function314, shape11, textFieldColors7, paddingValues2, function315, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    public final Shape getOutlinedShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(-584749279);
        ComposerKt.sourceInformation($composer, "C465@26096L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-584749279, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-outlinedShape> (TextFieldDefaults.kt:465)");
        }
        Shape shape = OutlinedTextFieldDefaults.INSTANCE.getShape($composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return shape;
    }

    public final Shape getFilledShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(611926497);
        ComposerKt.sourceInformation($composer, "C472@26334L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(611926497, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-filledShape> (TextFieldDefaults.kt:472)");
        }
        Shape shape = getShape($composer, $changed & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return shape;
    }

    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m1861getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m1857getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* JADX WARN: Code duplicated, block: B:83:0x010e  */
    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.ContainerBox`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.ContainerBox(\n        enabled = enabled,\n        isError = isError,\n        interactionSource = interactionSource,\n        colors = colors,\n        shape = shape,\n    )", imports = {}))
    public final void FilledContainerBox(final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, Shape shape, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        int $dirty;
        Shape shape3;
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(colors, "colors");
        Composer $composer2 = $composer.startRestartGroup(-1358797526);
        ComposerKt.sourceInformation($composer2, "C(FilledContainerBox)P(1,3,2)508@27835L5,509@27850L168:TextFieldDefaults.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(enabled) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changed(isError) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer2.changed(interactionSource) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i2 = $composer2.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i2;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i2;
        } else {
            shape2 = shape;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & 458752) == 0) {
            $dirty2 |= $composer2.changed(this) ? 131072 : 65536;
        }
        if ((374491 & $dirty2) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            shape3 = shape2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty = $dirty2 & (-57345);
                    shape3 = shape2;
                } else {
                    $dirty = $dirty2;
                    shape3 = shape2;
                }
            } else if ((i & 16) != 0) {
                $dirty = $dirty2 & (-57345);
                shape3 = INSTANCE.getShape($composer2, 6);
            } else {
                $dirty = $dirty2;
                shape3 = shape2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1358797526, $dirty, -1, "androidx.compose.material3.TextFieldDefaults.FilledContainerBox (TextFieldDefaults.kt:503)");
            }
            ContainerBox(enabled, isError, interactionSource, colors, shape3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Shape shape4 = shape3;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.FilledContainerBox.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i3) {
                TextFieldDefaults.this.FilledContainerBox(enabled, isError, interactionSource, colors, shape4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `OutlinedTextFieldDefaults.ContainerBox`", replaceWith = @ReplaceWith(expression = "OutlinedTextFieldDefaults.ContainerBox(\n        enabled = enabled,\n        isError = isError,\n        interactionSource = interactionSource,\n        colors = colors,\n        shape = shape,\n        focusedBorderThickness = focusedBorderThickness,\n        unfocusedBorderThickness = unfocusedBorderThickness,\n    )", imports = {"androidx.compose.material.OutlinedTextFieldDefaults"}))
    /* JADX INFO: renamed from: OutlinedBorderContainerBox-nbWgWpA, reason: not valid java name */
    public final void m1853OutlinedBorderContainerBoxnbWgWpA(final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, Shape shape, float focusedBorderThickness, float unfocusedBorderThickness, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        float focusedBorderThickness2;
        float f;
        Shape shape3;
        Shape shape4;
        float focusedBorderThickness3;
        float unfocusedBorderThickness2;
        int $dirty;
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(colors, "colors");
        Composer $composer2 = $composer.startRestartGroup(-1998946250);
        ComposerKt.sourceInformation($composer2, "C(OutlinedBorderContainerBox)P(1,4,3!1,5,2:c#ui.unit.Dp,6:c#ui.unit.Dp)538@28994L9,541@29216L286:TextFieldDefaults.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed(enabled) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 112) == 0) {
            $dirty2 |= $composer2.changed(isError) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 896) == 0) {
            $dirty2 |= $composer2.changed(interactionSource) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty2 |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if (($changed & 57344) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i2 = $composer2.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i2;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i2;
        } else {
            shape2 = shape;
        }
        int i3 = i & 32;
        if (i3 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            focusedBorderThickness2 = focusedBorderThickness;
        } else if (($changed & 458752) == 0) {
            focusedBorderThickness2 = focusedBorderThickness;
            $dirty2 |= $composer2.changed(focusedBorderThickness2) ? 131072 : 65536;
        } else {
            focusedBorderThickness2 = focusedBorderThickness;
        }
        int i4 = i & 64;
        if (i4 != 0) {
            $dirty2 |= 1572864;
            f = unfocusedBorderThickness;
        } else if (($changed & 3670016) == 0) {
            f = unfocusedBorderThickness;
            $dirty2 |= $composer2.changed(f) ? 1048576 : 524288;
        } else {
            f = unfocusedBorderThickness;
        }
        if (($dirty2 & 2995931) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            shape4 = shape2;
            focusedBorderThickness3 = focusedBorderThickness2;
            unfocusedBorderThickness2 = f;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if ((i & 16) != 0) {
                    shape3 = ShapesKt.toShape(OutlinedTextFieldTokens.INSTANCE.getContainerShape(), $composer2, 6);
                    $dirty2 &= -57345;
                } else {
                    shape3 = shape2;
                }
                if (i3 != 0) {
                    focusedBorderThickness2 = OutlinedTextFieldDefaults.INSTANCE.m1651getFocusedBorderThicknessD9Ej5fM();
                }
                if (i4 != 0) {
                    $dirty = $dirty2;
                    shape4 = shape3;
                    unfocusedBorderThickness2 = OutlinedTextFieldDefaults.INSTANCE.m1654getUnfocusedBorderThicknessD9Ej5fM();
                    focusedBorderThickness3 = focusedBorderThickness2;
                } else {
                    shape4 = shape3;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
                    $dirty = $dirty2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                shape4 = shape2;
                focusedBorderThickness3 = focusedBorderThickness2;
                unfocusedBorderThickness2 = f;
                $dirty = $dirty2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1998946250, $dirty, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedBorderContainerBox (TextFieldDefaults.kt:533)");
            }
            OutlinedTextFieldDefaults.INSTANCE.m1648ContainerBoxnbWgWpA(enabled, isError, interactionSource, colors, shape4, focusedBorderThickness3, unfocusedBorderThickness2, $composer2, 12582912 | ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Shape shape5 = shape4;
        final float f2 = focusedBorderThickness3;
        final float f3 = unfocusedBorderThickness2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults$OutlinedBorderContainerBox$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i5) {
                this.$tmp0_rcvr.m1853OutlinedBorderContainerBoxnbWgWpA(enabled, isError, interactionSource, colors, shape5, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
            }
        });
    }

    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1851textFieldWithLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        return textFieldDefaults.m1870textFieldWithLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.contentPaddingWithLabel`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.contentPaddingWithLabel(\n        start = start,\n        top = top,\n        end = end,\n        bottom = bottom,\n    )", imports = {}))
    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1870textFieldWithLabelPaddinga9UjIt4(float start, float end, float top, float bottom) {
        return m1855contentPaddingWithLabela9UjIt4(start, end, top, bottom);
    }

    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1852textFieldWithoutLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return textFieldDefaults.m1871textFieldWithoutLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.contentPaddingWithoutLabel`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.contentPaddingWithoutLabel(\n        start = start,\n        top = top,\n        end = end,\n        bottom = bottom,\n    )", imports = {}))
    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1871textFieldWithoutLabelPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return m1856contentPaddingWithoutLabela9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1849outlinedTextFieldPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return textFieldDefaults.m1866outlinedTextFieldPaddinga9UjIt4(f, f2, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `OutlinedTextFieldDefaults.contentPadding`", replaceWith = @ReplaceWith(expression = "OutlinedTextFieldDefaults.contentPadding(\n        start = start,\n        top = top,\n        end = end,\n        bottom = bottom,\n    )", imports = {"androidx.compose.material.OutlinedTextFieldDefaults"}))
    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1866outlinedTextFieldPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return OutlinedTextFieldDefaults.INSTANCE.m1650contentPaddinga9UjIt4(start, top, end, bottom);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.colors` with additional parameters to controlcontainer color based on state.", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.colors(\n        focusedTextColor = focusedTextColor,\n        unfocusedTextColor = unfocusedTextColor,\n        disabledTextColor = disabledTextColor,\n        errorTextColor = errorTextColor,\n        focusedContainerColor = containerColor,\n        unfocusedContainerColor = containerColor,\n        disabledContainerColor = containerColor,\n        errorContainerColor = errorContainerColor,\n        cursorColor = cursorColor,\n        errorCursorColor = errorCursorColor,\n        selectionColors = selectionColors,\n        focusedIndicatorColor = focusedIndicatorColor,\n        unfocusedIndicatorColor = unfocusedIndicatorColor,\n        disabledIndicatorColor = disabledIndicatorColor,\n        errorIndicatorColor = errorIndicatorColor,\n        focusedLeadingIconColor = focusedLeadingIconColor,\n        unfocusedLeadingIconColor = unfocusedLeadingIconColor,\n        disabledLeadingIconColor = disabledLeadingIconColor,\n        errorLeadingIconColor = errorLeadingIconColor,\n        focusedTrailingIconColor = focusedTrailingIconColor,\n        unfocusedTrailingIconColor = unfocusedTrailingIconColor,\n        disabledTrailingIconColor = disabledTrailingIconColor,\n        errorTrailingIconColor = errorTrailingIconColor,\n        focusedLabelColor = focusedLabelColor,\n        unfocusedLabelColor = unfocusedLabelColor,\n        disabledLabelColor = disabledLabelColor,\n        errorLabelColor = errorLabelColor,\n        focusedPlaceholderColor = focusedPlaceholderColor,\n        unfocusedPlaceholderColor = unfocusedPlaceholderColor,\n        disabledPlaceholderColor = disabledPlaceholderColor,\n        errorPlaceholderColor = errorPlaceholderColor,\n        focusedSupportingTextColor = focusedSupportingTextColor,\n        unfocusedSupportingTextColor = unfocusedSupportingTextColor,\n        disabledSupportingTextColor = disabledSupportingTextColor,\n        errorSupportingTextColor = errorSupportingTextColor,\n        focusedPrefixColor = focusedPrefixColor,\n        unfocusedPrefixColor = unfocusedPrefixColor,\n        disabledPrefixColor = disabledPrefixColor,\n        errorPrefixColor = errorPrefixColor,\n        focusedSuffixColor = focusedSuffixColor,\n        unfocusedSuffixColor = unfocusedSuffixColor,\n        disabledSuffixColor = disabledSuffixColor,\n        errorSuffixColor = errorSuffixColor,\n    )", imports = {}))
    /* JADX INFO: renamed from: textFieldColors-M37tBTI, reason: not valid java name */
    public final TextFieldColors m1868textFieldColorsM37tBTI(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long containerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int $changed4, int i, int i2) {
        long disabledTextColor2;
        SelectionColors selectionColors2;
        long disabledIndicatorColor2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long disabledLabelColor2;
        long disabledPlaceholderColor2;
        long disabledSupportingTextColor2;
        long disabledPrefixColor2;
        long disabledSuffixColor2;
        $composer.startReplaceableGroup(568209592);
        ComposerKt.sourceInformation($composer, "C(textFieldColors)P(29:c#ui.graphics.Color,39:c#ui.graphics.Color,9:c#ui.graphics.Color,20:c#ui.graphics.Color,0:c#ui.graphics.Color,11:c#ui.graphics.Color,1:c#ui.graphics.Color,12:c#ui.graphics.Color,31,22:c#ui.graphics.Color,32:c#ui.graphics.Color,2:c#ui.graphics.Color,13:c#ui.graphics.Color,24:c#ui.graphics.Color,34:c#ui.graphics.Color,4:c#ui.graphics.Color,15:c#ui.graphics.Color,30:c#ui.graphics.Color,40:c#ui.graphics.Color,10:c#ui.graphics.Color,21:c#ui.graphics.Color,23:c#ui.graphics.Color,33:c#ui.graphics.Color,3:c#ui.graphics.Color,14:c#ui.graphics.Color,25:c#ui.graphics.Color,35:c#ui.graphics.Color,5:c#ui.graphics.Color,16:c#ui.graphics.Color,28:c#ui.graphics.Color,38:c#ui.graphics.Color,8:c#ui.graphics.Color,19:c#ui.graphics.Color,26:c#ui.graphics.Color,36:c#ui.graphics.Color,6:c#ui.graphics.Color,17:c#ui.graphics.Color,27:c#ui.graphics.Color,37:c#ui.graphics.Color,7:c#ui.graphics.Color,18:c#ui.graphics.Color)671@35322L9,672@35402L9,673@35489L9,675@35640L9,676@35720L9,677@35805L9,678@35878L9,679@35966L9,680@36049L7,681@36145L9,682@36240L9,683@36342L9,685@36518L9,686@36614L9,687@36707L9,688@36807L9,690@36977L9,691@37075L9,692@37170L9,693@37272L9,695@37445L9,696@37529L9,697@37610L9,698@37698L9,700@37850L9,701@37946L9,702@38044L9,703@38138L9,705@38302L9,706@38400L9,707@38495L9,708@38597L9,710@38768L9,711@38854L9,712@38942L9,713@39029L9,715@39183L9,716@39269L9,717@39357L9,718@39444L9,720@39598L9,721@39634L2308:TextFieldDefaults.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusInputColor(), $composer, 6) : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputColor(), $composer, 6) : unfocusedTextColor;
        if ((i & 4) != 0) {
            long color = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledTextColor2 = Color.m2969copywmQWz5c(color, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        long errorTextColor2 = (i & 8) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorInputColor(), $composer, 6) : errorTextColor;
        long containerColor2 = (i & 16) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getContainerColor(), $composer, 6) : containerColor;
        long errorContainerColor2 = (i & 32) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getContainerColor(), $composer, 6) : errorContainerColor;
        long cursorColor2 = (i & 64) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getCaretColor(), $composer, 6) : cursorColor;
        long errorCursorColor2 = (i & 128) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorFocusCaretColor(), $composer, 6) : errorCursorColor;
        if ((i & 256) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) objConsume;
        } else {
            selectionColors2 = selectionColors;
        }
        long focusedIndicatorColor2 = (i & 512) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusActiveIndicatorColor(), $composer, 6) : focusedIndicatorColor;
        long unfocusedIndicatorColor2 = (i & 1024) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getActiveIndicatorColor(), $composer, 6) : unfocusedIndicatorColor;
        if ((i & 2048) != 0) {
            long color2 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledActiveIndicatorColor(), $composer, 6);
            disabledIndicatorColor2 = Color.m2969copywmQWz5c(color2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color2) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color2) : 0.0f);
        } else {
            disabledIndicatorColor2 = disabledIndicatorColor;
        }
        long errorIndicatorColor2 = (i & 4096) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorActiveIndicatorColor(), $composer, 6) : errorIndicatorColor;
        long focusedLeadingIconColor2 = (i & 8192) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusLeadingIconColor(), $composer, 6) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (i & 16384) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getLeadingIconColor(), $composer, 6) : unfocusedLeadingIconColor;
        if ((32768 & i) != 0) {
            long color3 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), $composer, 6);
            disabledLeadingIconColor2 = Color.m2969copywmQWz5c(color3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color3) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color3) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (65536 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorLeadingIconColor(), $composer, 6) : errorLeadingIconColor;
        long focusedTrailingIconColor2 = (131072 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusTrailingIconColor(), $composer, 6) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (262144 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getTrailingIconColor(), $composer, 6) : unfocusedTrailingIconColor;
        if ((524288 & i) != 0) {
            long color4 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), $composer, 6);
            disabledTrailingIconColor2 = Color.m2969copywmQWz5c(color4, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color4) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color4) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color4) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color4) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (1048576 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorTrailingIconColor(), $composer, 6) : errorTrailingIconColor;
        long focusedLabelColor2 = (2097152 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusLabelColor(), $composer, 6) : focusedLabelColor;
        long unfocusedLabelColor2 = (4194304 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getLabelColor(), $composer, 6) : unfocusedLabelColor;
        if ((8388608 & i) != 0) {
            long color5 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledLabelColor(), $composer, 6);
            disabledLabelColor2 = Color.m2969copywmQWz5c(color5, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color5) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color5) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color5) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color5) : 0.0f);
        } else {
            disabledLabelColor2 = disabledLabelColor;
        }
        long errorLabelColor2 = (16777216 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorLabelColor(), $composer, 6) : errorLabelColor;
        long focusedPlaceholderColor2 = (33554432 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (67108864 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : unfocusedPlaceholderColor;
        if ((134217728 & i) != 0) {
            long color6 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledPlaceholderColor2 = Color.m2969copywmQWz5c(color6, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color6) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color6) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color6) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color6) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        long errorPlaceholderColor2 = (268435456 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : errorPlaceholderColor;
        long focusedSupportingTextColor2 = (536870912 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusSupportingColor(), $composer, 6) : focusedSupportingTextColor;
        long unfocusedSupportingTextColor2 = (i & 1073741824) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getSupportingColor(), $composer, 6) : unfocusedSupportingTextColor;
        if ((i2 & 1) != 0) {
            long color7 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledSupportingColor(), $composer, 6);
            disabledSupportingTextColor2 = Color.m2969copywmQWz5c(color7, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color7) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color7) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color7) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color7) : 0.0f);
        } else {
            disabledSupportingTextColor2 = disabledSupportingTextColor;
        }
        long errorSupportingTextColor2 = (i2 & 2) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorSupportingColor(), $composer, 6) : errorSupportingTextColor;
        long focusedPrefixColor2 = (i2 & 4) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : focusedPrefixColor;
        long unfocusedPrefixColor2 = (i2 & 8) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : unfocusedPrefixColor;
        if ((i2 & 16) != 0) {
            long color8 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6);
            disabledPrefixColor2 = Color.m2969copywmQWz5c(color8, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color8) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color8) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color8) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color8) : 0.0f);
        } else {
            disabledPrefixColor2 = disabledPrefixColor;
        }
        long errorPrefixColor2 = (i2 & 32) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : errorPrefixColor;
        long focusedSuffixColor2 = (i2 & 64) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 128) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : unfocusedSuffixColor;
        if ((i2 & 256) != 0) {
            long color9 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6);
            disabledSuffixColor2 = Color.m2969copywmQWz5c(color9, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color9) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color9) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color9) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color9) : 0.0f);
        } else {
            disabledSuffixColor2 = disabledSuffixColor;
        }
        long errorSuffixColor2 = (i2 & 512) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(568209592, $changed, $changed1, "androidx.compose.material3.TextFieldDefaults.textFieldColors (TextFieldDefaults.kt:670)");
        }
        TextFieldColors textFieldColorsM1854colors0hiis_0 = m1854colors0hiis_0(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, containerColor2, containerColor2, containerColor2, errorContainerColor2, cursorColor2, errorCursorColor2, selectionColors2, focusedIndicatorColor2, unfocusedIndicatorColor2, disabledIndicatorColor2, errorIndicatorColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, errorPlaceholderColor2, focusedSupportingTextColor2, unfocusedSupportingTextColor2, disabledSupportingTextColor2, errorSupportingTextColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | ($changed & 57344) | (($changed << 3) & 458752) | (($changed << 6) & 3670016) | (($changed << 6) & 29360128) | (($changed << 6) & 234881024) | (($changed << 6) & 1879048192), (($changed >> 24) & 14) | (($changed >> 24) & 112) | (($changed1 << 6) & 896) | (($changed1 << 6) & 7168) | (($changed1 << 6) & 57344) | (($changed1 << 6) & 458752) | (($changed1 << 6) & 3670016) | (($changed1 << 6) & 29360128) | (($changed1 << 6) & 234881024) | (($changed1 << 6) & 1879048192), (($changed1 >> 24) & 14) | (($changed1 >> 24) & 112) | (($changed2 << 6) & 896) | (($changed2 << 6) & 7168) | (($changed2 << 6) & 57344) | (($changed2 << 6) & 458752) | (($changed2 << 6) & 3670016) | (($changed2 << 6) & 29360128) | (($changed2 << 6) & 234881024) | (($changed2 << 6) & 1879048192), (($changed2 >> 24) & 14) | (($changed2 >> 24) & 112) | (($changed3 << 6) & 896) | (($changed3 << 6) & 7168) | (57344 & ($changed3 << 6)) | (($changed3 << 6) & 458752) | (($changed3 << 6) & 3670016) | (($changed3 << 6) & 29360128) | (($changed3 << 6) & 234881024) | (($changed3 << 6) & 1879048192), (($changed3 >> 24) & 14) | (($changed3 >> 24) & 112) | (($changed4 << 6) & 896) | (($changed4 << 6) & 7168), 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return textFieldColorsM1854colors0hiis_0;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `OutlinedTextFieldDefaults.colors` with additional parameters tocontrol container color based on state.", replaceWith = @ReplaceWith(expression = "OutlinedTextFieldDefaults.colors(\n        focusedTextColor = focusedTextColor,\n        unfocusedTextColor = unfocusedTextColor,\n        disabledTextColor = disabledTextColor,\n        errorTextColor = errorTextColor,\n        focusedContainerColor = containerColor,\n        unfocusedContainerColor = containerColor,\n        disabledContainerColor = containerColor,\n        errorContainerColor = errorContainerColor,\n        cursorColor = cursorColor,\n        errorCursorColor = errorCursorColor,\n        selectionColors = selectionColors,\n        focusedBorderColor = focusedBorderColor,\n        unfocusedBorderColor = unfocusedBorderColor,\n        disabledBorderColor = disabledBorderColor,\n        errorBorderColor = errorBorderColor,\n        focusedLeadingIconColor = focusedLeadingIconColor,\n        unfocusedLeadingIconColor = unfocusedLeadingIconColor,\n        disabledLeadingIconColor = disabledLeadingIconColor,\n        errorLeadingIconColor = errorLeadingIconColor,\n        focusedTrailingIconColor = focusedTrailingIconColor,\n        unfocusedTrailingIconColor = unfocusedTrailingIconColor,\n        disabledTrailingIconColor = disabledTrailingIconColor,\n        errorTrailingIconColor = errorTrailingIconColor,\n        focusedLabelColor = focusedLabelColor,\n        unfocusedLabelColor = unfocusedLabelColor,\n        disabledLabelColor = disabledLabelColor,\n        errorLabelColor = errorLabelColor,\n        focusedPlaceholderColor = focusedPlaceholderColor,\n        unfocusedPlaceholderColor = unfocusedPlaceholderColor,\n        disabledPlaceholderColor = disabledPlaceholderColor,\n        errorPlaceholderColor = errorPlaceholderColor,\n        focusedSupportingTextColor = focusedSupportingTextColor,\n        unfocusedSupportingTextColor = unfocusedSupportingTextColor,\n        disabledSupportingTextColor = disabledSupportingTextColor,\n        errorSupportingTextColor = errorSupportingTextColor,\n        focusedPrefixColor = focusedPrefixColor,\n        unfocusedPrefixColor = unfocusedPrefixColor,\n        disabledPrefixColor = disabledPrefixColor,\n        errorPrefixColor = errorPrefixColor,\n        focusedSuffixColor = focusedSuffixColor,\n        unfocusedSuffixColor = unfocusedSuffixColor,\n        disabledSuffixColor = disabledSuffixColor,\n        errorSuffixColor = errorSuffixColor,\n    )", imports = {"androidx.compose.material.OutlinedTextFieldDefaults"}))
    /* JADX INFO: renamed from: outlinedTextFieldColors-M37tBTI, reason: not valid java name */
    public final TextFieldColors m1864outlinedTextFieldColorsM37tBTI(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long containerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedBorderColor, long unfocusedBorderColor, long disabledBorderColor, long errorBorderColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int $changed4, int i, int i2) {
        long disabledTextColor2;
        SelectionColors selectionColors2;
        long disabledBorderColor2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long disabledLabelColor2;
        long disabledPlaceholderColor2;
        long disabledSupportingTextColor2;
        long disabledPrefixColor2;
        long disabledSuffixColor2;
        $composer.startReplaceableGroup(618732090);
        ComposerKt.sourceInformation($composer, "C(outlinedTextFieldColors)P(29:c#ui.graphics.Color,39:c#ui.graphics.Color,9:c#ui.graphics.Color,20:c#ui.graphics.Color,0:c#ui.graphics.Color,12:c#ui.graphics.Color,1:c#ui.graphics.Color,13:c#ui.graphics.Color,31,22:c#ui.graphics.Color,32:c#ui.graphics.Color,2:c#ui.graphics.Color,11:c#ui.graphics.Color,24:c#ui.graphics.Color,34:c#ui.graphics.Color,4:c#ui.graphics.Color,15:c#ui.graphics.Color,30:c#ui.graphics.Color,40:c#ui.graphics.Color,10:c#ui.graphics.Color,21:c#ui.graphics.Color,23:c#ui.graphics.Color,33:c#ui.graphics.Color,3:c#ui.graphics.Color,14:c#ui.graphics.Color,25:c#ui.graphics.Color,35:c#ui.graphics.Color,5:c#ui.graphics.Color,16:c#ui.graphics.Color,28:c#ui.graphics.Color,38:c#ui.graphics.Color,8:c#ui.graphics.Color,19:c#ui.graphics.Color,26:c#ui.graphics.Color,36:c#ui.graphics.Color,6:c#ui.graphics.Color,17:c#ui.graphics.Color,27:c#ui.graphics.Color,37:c#ui.graphics.Color,7:c#ui.graphics.Color,18:c#ui.graphics.Color)821@45523L9,822@45605L9,823@45694L9,825@45849L9,828@46031L9,829@46121L9,830@46204L7,831@46291L9,832@46377L9,833@46470L9,835@46631L9,836@46729L9,837@46824L9,838@46926L9,840@47100L9,841@47200L9,842@47297L9,844@47414L9,845@47578L9,846@47664L9,847@47747L9,848@47837L9,850@47993L9,851@48091L9,852@48191L9,853@48287L9,855@48455L9,856@48555L9,857@48652L9,859@48769L9,860@48931L9,861@49019L9,862@49109L9,863@49198L9,865@49356L9,866@49444L9,867@49534L9,868@49623L9,870@49781L9,871@49843L2284:TextFieldDefaults.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusInputColor(), $composer, 6) : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputColor(), $composer, 6) : unfocusedTextColor;
        if ((i & 4) != 0) {
            long color = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledTextColor2 = Color.m2969copywmQWz5c(color, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        long errorTextColor2 = (i & 8) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorInputColor(), $composer, 6) : errorTextColor;
        long containerColor2 = (i & 16) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : containerColor;
        long errorContainerColor2 = (i & 32) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : errorContainerColor;
        long cursorColor2 = (i & 64) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getCaretColor(), $composer, 6) : cursorColor;
        long errorCursorColor2 = (i & 128) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorFocusCaretColor(), $composer, 6) : errorCursorColor;
        if ((i & 256) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) objConsume;
        } else {
            selectionColors2 = selectionColors;
        }
        long focusedBorderColor2 = (i & 512) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusOutlineColor(), $composer, 6) : focusedBorderColor;
        long unfocusedBorderColor2 = (i & 1024) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getOutlineColor(), $composer, 6) : unfocusedBorderColor;
        if ((i & 2048) != 0) {
            long color2 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledOutlineColor(), $composer, 6);
            disabledBorderColor2 = Color.m2969copywmQWz5c(color2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color2) : 0.12f, (14 & 2) != 0 ? Color.m2977getRedimpl(color2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color2) : 0.0f);
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        long errorBorderColor2 = (i & 4096) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorOutlineColor(), $composer, 6) : errorBorderColor;
        long focusedLeadingIconColor2 = (i & 8192) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusLeadingIconColor(), $composer, 6) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (i & 16384) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getLeadingIconColor(), $composer, 6) : unfocusedLeadingIconColor;
        if ((32768 & i) != 0) {
            long color3 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), $composer, 6);
            disabledLeadingIconColor2 = Color.m2969copywmQWz5c(color3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color3) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color3) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (65536 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorLeadingIconColor(), $composer, 6) : errorLeadingIconColor;
        long focusedTrailingIconColor2 = (131072 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusTrailingIconColor(), $composer, 6) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (262144 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getTrailingIconColor(), $composer, 6) : unfocusedTrailingIconColor;
        if ((524288 & i) != 0) {
            long color4 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), $composer, 6);
            disabledTrailingIconColor2 = Color.m2969copywmQWz5c(color4, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color4) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color4) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color4) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color4) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (1048576 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorTrailingIconColor(), $composer, 6) : errorTrailingIconColor;
        long focusedLabelColor2 = (2097152 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusLabelColor(), $composer, 6) : focusedLabelColor;
        long unfocusedLabelColor2 = (4194304 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getLabelColor(), $composer, 6) : unfocusedLabelColor;
        if ((8388608 & i) != 0) {
            long color5 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledLabelColor(), $composer, 6);
            disabledLabelColor2 = Color.m2969copywmQWz5c(color5, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color5) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color5) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color5) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color5) : 0.0f);
        } else {
            disabledLabelColor2 = disabledLabelColor;
        }
        long errorLabelColor2 = (16777216 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorLabelColor(), $composer, 6) : errorLabelColor;
        long focusedPlaceholderColor2 = (33554432 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (67108864 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : unfocusedPlaceholderColor;
        if ((134217728 & i) != 0) {
            long color6 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledPlaceholderColor2 = Color.m2969copywmQWz5c(color6, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color6) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color6) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color6) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color6) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        long errorPlaceholderColor2 = (268435456 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : errorPlaceholderColor;
        long focusedSupportingTextColor2 = (536870912 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusSupportingColor(), $composer, 6) : focusedSupportingTextColor;
        long unfocusedSupportingTextColor2 = (i & 1073741824) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getSupportingColor(), $composer, 6) : unfocusedSupportingTextColor;
        if ((i2 & 1) != 0) {
            long color7 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledSupportingColor(), $composer, 6);
            disabledSupportingTextColor2 = Color.m2969copywmQWz5c(color7, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color7) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color7) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color7) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color7) : 0.0f);
        } else {
            disabledSupportingTextColor2 = disabledSupportingTextColor;
        }
        long errorSupportingTextColor2 = (i2 & 2) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorSupportingColor(), $composer, 6) : errorSupportingTextColor;
        long focusedPrefixColor2 = (i2 & 4) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : focusedPrefixColor;
        long unfocusedPrefixColor2 = (i2 & 8) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : unfocusedPrefixColor;
        if ((i2 & 16) != 0) {
            long color8 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6);
            disabledPrefixColor2 = Color.m2969copywmQWz5c(color8, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color8) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color8) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color8) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color8) : 0.0f);
        } else {
            disabledPrefixColor2 = disabledPrefixColor;
        }
        long errorPrefixColor2 = (i2 & 32) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : errorPrefixColor;
        long focusedSuffixColor2 = (i2 & 64) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 128) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : unfocusedSuffixColor;
        if ((i2 & 256) != 0) {
            long color9 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6);
            disabledSuffixColor2 = Color.m2969copywmQWz5c(color9, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color9) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color9) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color9) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color9) : 0.0f);
        } else {
            disabledSuffixColor2 = disabledSuffixColor;
        }
        long errorSuffixColor2 = (i2 & 512) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(618732090, $changed, $changed1, "androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors (TextFieldDefaults.kt:820)");
        }
        TextFieldColors textFieldColorsM1649colors0hiis_0 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, containerColor2, containerColor2, containerColor2, errorContainerColor2, cursorColor2, errorCursorColor2, selectionColors2, focusedBorderColor2, unfocusedBorderColor2, disabledBorderColor2, errorBorderColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, errorPlaceholderColor2, focusedSupportingTextColor2, unfocusedSupportingTextColor2, disabledSupportingTextColor2, errorSupportingTextColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | ($changed & 57344) | (($changed << 3) & 458752) | (($changed << 6) & 3670016) | (($changed << 6) & 29360128) | (($changed << 6) & 234881024) | (($changed << 6) & 1879048192), (($changed >> 24) & 14) | (($changed >> 24) & 112) | (($changed1 << 6) & 896) | (($changed1 << 6) & 7168) | (($changed1 << 6) & 57344) | (($changed1 << 6) & 458752) | (($changed1 << 6) & 3670016) | (($changed1 << 6) & 29360128) | (($changed1 << 6) & 234881024) | (($changed1 << 6) & 1879048192), (($changed1 >> 24) & 14) | (($changed1 >> 24) & 112) | (($changed2 << 6) & 896) | (($changed2 << 6) & 7168) | (($changed2 << 6) & 57344) | (($changed2 << 6) & 458752) | (($changed2 << 6) & 3670016) | (($changed2 << 6) & 29360128) | (($changed2 << 6) & 234881024) | (($changed2 << 6) & 1879048192), (($changed2 >> 24) & 14) | (($changed2 >> 24) & 112) | (($changed3 << 6) & 896) | (($changed3 << 6) & 7168) | (57344 & ($changed3 << 6)) | (($changed3 << 6) & 458752) | (($changed3 << 6) & 3670016) | (($changed3 << 6) & 29360128) | (($changed3 << 6) & 234881024) | (($changed3 << 6) & 1879048192), (($changed3 >> 24) & 14) | 3072 | (($changed3 >> 24) & 112) | (($changed4 << 6) & 896), 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return textFieldColorsM1649colors0hiis_0;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0158  */
    /* JADX WARN: Code duplicated, block: B:102:0x015e  */
    /* JADX WARN: Code duplicated, block: B:104:0x0166  */
    /* JADX WARN: Code duplicated, block: B:105:0x0169  */
    /* JADX WARN: Code duplicated, block: B:107:0x016e  */
    /* JADX WARN: Code duplicated, block: B:110:0x0174  */
    /* JADX WARN: Code duplicated, block: B:111:0x0179  */
    /* JADX WARN: Code duplicated, block: B:113:0x017d  */
    /* JADX WARN: Code duplicated, block: B:115:0x0185  */
    /* JADX WARN: Code duplicated, block: B:116:0x0188  */
    /* JADX WARN: Code duplicated, block: B:118:0x018d  */
    /* JADX WARN: Code duplicated, block: B:121:0x0193  */
    /* JADX WARN: Code duplicated, block: B:122:0x0198  */
    /* JADX WARN: Code duplicated, block: B:124:0x019c  */
    /* JADX WARN: Code duplicated, block: B:126:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:127:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:129:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:132:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:133:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:135:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:137:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:138:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:140:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:143:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:144:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:146:0x01da  */
    /* JADX WARN: Code duplicated, block: B:149:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:151:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:154:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:161:0x0202  */
    /* JADX WARN: Code duplicated, block: B:164:0x0208  */
    /* JADX WARN: Code duplicated, block: B:172:0x0221  */
    /* JADX WARN: Code duplicated, block: B:175:0x0227  */
    /* JADX WARN: Code duplicated, block: B:183:0x023d  */
    /* JADX WARN: Code duplicated, block: B:186:0x0245  */
    /* JADX WARN: Code duplicated, block: B:187:0x024c  */
    /* JADX WARN: Code duplicated, block: B:189:0x0250  */
    /* JADX WARN: Code duplicated, block: B:191:0x0258  */
    /* JADX WARN: Code duplicated, block: B:192:0x025b  */
    /* JADX WARN: Code duplicated, block: B:194:0x0260  */
    /* JADX WARN: Code duplicated, block: B:197:0x0268  */
    /* JADX WARN: Code duplicated, block: B:198:0x026f  */
    /* JADX WARN: Code duplicated, block: B:200:0x0273  */
    /* JADX WARN: Code duplicated, block: B:202:0x027b  */
    /* JADX WARN: Code duplicated, block: B:203:0x027e  */
    /* JADX WARN: Code duplicated, block: B:205:0x0283  */
    /* JADX WARN: Code duplicated, block: B:214:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:216:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:229:0x0308 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:230:0x030a  */
    /* JADX WARN: Code duplicated, block: B:231:0x030c  */
    /* JADX WARN: Code duplicated, block: B:233:0x0310  */
    /* JADX WARN: Code duplicated, block: B:234:0x0312  */
    /* JADX WARN: Code duplicated, block: B:236:0x0316  */
    /* JADX WARN: Code duplicated, block: B:237:0x0318  */
    /* JADX WARN: Code duplicated, block: B:239:0x031c  */
    /* JADX WARN: Code duplicated, block: B:240:0x031e  */
    /* JADX WARN: Code duplicated, block: B:242:0x0322  */
    /* JADX WARN: Code duplicated, block: B:243:0x0324  */
    /* JADX WARN: Code duplicated, block: B:245:0x0328  */
    /* JADX WARN: Code duplicated, block: B:246:0x032a  */
    /* JADX WARN: Code duplicated, block: B:248:0x032e  */
    /* JADX WARN: Code duplicated, block: B:249:0x0330  */
    /* JADX WARN: Code duplicated, block: B:251:0x0334  */
    /* JADX WARN: Code duplicated, block: B:252:0x0336  */
    /* JADX WARN: Code duplicated, block: B:255:0x033c  */
    /* JADX WARN: Code duplicated, block: B:256:0x034a  */
    /* JADX WARN: Code duplicated, block: B:259:0x0354  */
    /* JADX WARN: Code duplicated, block: B:260:0x03cb  */
    /* JADX WARN: Code duplicated, block: B:263:0x03d1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:264:0x03d3  */
    /* JADX WARN: Code duplicated, block: B:265:0x03f2  */
    /* JADX WARN: Code duplicated, block: B:267:0x0417  */
    /* JADX WARN: Code duplicated, block: B:269:0x041b  */
    /* JADX WARN: Code duplicated, block: B:270:0x045b  */
    /* JADX WARN: Code duplicated, block: B:273:0x0484  */
    /* JADX WARN: Code duplicated, block: B:276:0x0510  */
    /* JADX WARN: Code duplicated, block: B:280:0x051a  */
    /* JADX WARN: Code duplicated, block: B:282:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:67:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:71:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:72:0x0100  */
    /* JADX WARN: Code duplicated, block: B:74:0x0105  */
    /* JADX WARN: Code duplicated, block: B:77:0x010d  */
    /* JADX WARN: Code duplicated, block: B:78:0x0114  */
    /* JADX WARN: Code duplicated, block: B:80:0x0118  */
    /* JADX WARN: Code duplicated, block: B:82:0x0120  */
    /* JADX WARN: Code duplicated, block: B:83:0x0123  */
    /* JADX WARN: Code duplicated, block: B:85:0x0128  */
    /* JADX WARN: Code duplicated, block: B:88:0x0130  */
    /* JADX WARN: Code duplicated, block: B:89:0x0137  */
    /* JADX WARN: Code duplicated, block: B:91:0x013b  */
    /* JADX WARN: Code duplicated, block: B:93:0x0143  */
    /* JADX WARN: Code duplicated, block: B:94:0x0146  */
    /* JADX WARN: Code duplicated, block: B:96:0x014b  */
    /* JADX WARN: Code duplicated, block: B:99:0x0151  */
    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.DecorationBox`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.DecorationBox(\n        value = value,\n        innerTextField = innerTextField,\n        enabled = enabled,\n        singleLine = singleLine,\n        visualTransformation = visualTransformation,\n        interactionSource = interactionSource,\n        isError = isError,\n        label = label,\n        placeholder = placeholder,\n        leadingIcon = leadingIcon,\n        trailingIcon = trailingIcon,\n        prefix = prefix,\n        suffix = suffix,\n        supportingText = supportingText,\n        shape = shape,\n        colors = colors,\n        contentPadding = contentPadding,\n        container = container,\n    )", imports = {}))
    public final void TextFieldDecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> innerTextField, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, Shape shape, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function9, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        boolean isError2;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Function2<? super Composer, ? super Integer, Unit> function16;
        Shape shape2;
        TextFieldColors colors2;
        PaddingValues contentPadding2;
        Shape shape3;
        Function2<? super Composer, ? super Integer, Unit> function2ComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function17;
        Function2<? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        Function2<? super Composer, ? super Integer, Unit> function21;
        boolean isError3;
        Function2<? super Composer, ? super Integer, Unit> function22;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        Function2<? super Composer, ? super Integer, Unit> function23;
        int $dirty1;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
        Intrinsics.checkNotNullParameter(visualTransformation, "visualTransformation");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Composer $composer3 = $composer.startRestartGroup(-1042273141);
        ComposerKt.sourceInformation($composer3, "C(TextFieldDecorationBox)P(16,4,3,12,17,5,6,7,9,8,15,10,13,14,11!1,2)958@54020L5,959@54061L8,969@54408L612:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer3.changedInstance(innerTextField) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer3.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(singleLine) ? 2048 : 1024;
        }
        int i21 = 8192;
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
                i2 = $composer3.changed(interactionSource) ? 131072 : 65536;
            }
            i3 = i & 64;
            if (i3 != 0) {
                $dirty |= 1572864;
            } else if (($changed & 3670016) != 0) {
                if ($composer3.changed(isError)) {
                    i4 = 1048576;
                } else {
                    i4 = 524288;
                }
                $dirty |= i4;
            }
            i5 = i & 128;
            if (i5 != 0) {
                $dirty |= 12582912;
            } else if (($changed & 29360128) != 0) {
                if ($composer3.changedInstance(function2)) {
                    i6 = 8388608;
                } else {
                    i6 = 4194304;
                }
                $dirty |= i6;
            }
            i7 = i & 256;
            if (i7 != 0) {
                $dirty |= 100663296;
            } else if (($changed & 234881024) != 0) {
                if ($composer3.changedInstance(function3)) {
                    i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i8 = 33554432;
                }
                $dirty |= i8;
            }
            i9 = i & 512;
            if (i9 != 0) {
                $dirty |= 805306368;
            } else if (($changed & 1879048192) != 0) {
                if ($composer3.changedInstance(function4)) {
                    i10 = 536870912;
                } else {
                    i10 = 268435456;
                }
                $dirty |= i10;
            }
            i11 = i & 1024;
            if (i11 != 0) {
                $dirty2 |= 6;
            } else if (($changed1 & 14) != 0) {
                if ($composer3.changedInstance(function5)) {
                    i12 = 4;
                } else {
                    i12 = 2;
                }
                $dirty2 |= i12;
            }
            i13 = i & 2048;
            if (i13 != 0) {
                $dirty2 |= 48;
            } else if (($changed1 & 112) != 0) {
                if ($composer3.changedInstance(function6)) {
                    i14 = 32;
                } else {
                    i14 = 16;
                }
                $dirty2 |= i14;
            }
            i15 = i & 4096;
            if (i15 != 0) {
                $dirty2 |= 384;
            } else if (($changed1 & 896) != 0) {
                if ($composer3.changedInstance(function7)) {
                    i16 = 256;
                } else {
                    i16 = 128;
                }
                $dirty2 |= i16;
            }
            i17 = i & 8192;
            if (i17 != 0) {
                $dirty2 |= 3072;
            } else if (($changed1 & 7168) != 0) {
                $dirty2 |= $composer3.changedInstance(function8) ? 2048 : 1024;
            }
            if (($changed1 & 57344) != 0) {
                if ((i & 16384) == 0 && $composer3.changed(shape)) {
                    i21 = 16384;
                }
                $dirty2 |= i21;
            }
            if (($changed1 & 458752) != 0) {
                $dirty2 |= ((i & 32768) == 0 || !$composer3.changed(colors)) ? 65536 : 131072;
            }
            if (($changed1 & 3670016) != 0) {
                $dirty2 |= ((i & 65536) == 0 || !$composer3.changed(contentPadding)) ? 524288 : 1048576;
            }
            i18 = i & 131072;
            if (i18 != 0) {
                $dirty2 |= 12582912;
            } else if (($changed1 & 29360128) != 0) {
                if ($composer3.changedInstance(function9)) {
                    i19 = 8388608;
                } else {
                    i19 = 4194304;
                }
                $dirty2 |= i19;
            }
            if ((i & 262144) != 0) {
                $dirty2 |= 100663296;
            } else if (($changed1 & 234881024) != 0) {
                if ($composer3.changed(this)) {
                    i20 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i20 = 33554432;
                }
                $dirty2 |= i20;
            }
            if (($dirty & 1533916891) != 306783378 && (191739611 & $dirty2) == 38347922 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                isError3 = isError;
                function19 = function2;
                function21 = function3;
                function17 = function4;
                function23 = function5;
                function18 = function6;
                function20 = function7;
                function22 = function8;
                shape3 = shape;
                colors3 = colors;
                contentPadding3 = contentPadding;
                function2ComposableLambda = function9;
                $composer2 = $composer3;
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        isError2 = false;
                    } else {
                        isError2 = isError;
                    }
                    if (i5 != 0) {
                        function10 = null;
                    } else {
                        function10 = function2;
                    }
                    if (i7 != 0) {
                        function11 = null;
                    } else {
                        function11 = function3;
                    }
                    if (i9 != 0) {
                        function12 = null;
                    } else {
                        function12 = function4;
                    }
                    if (i11 != 0) {
                        function13 = null;
                    } else {
                        function13 = function5;
                    }
                    if (i13 != 0) {
                        function14 = null;
                    } else {
                        function14 = function6;
                    }
                    if (i15 != 0) {
                        function15 = null;
                    } else {
                        function15 = function7;
                    }
                    if (i17 != 0) {
                        function16 = null;
                    } else {
                        function16 = function8;
                    }
                    if ((i & 16384) != 0) {
                        shape2 = INSTANCE.getShape($composer3, 6);
                        $dirty2 &= -57345;
                    } else {
                        shape2 = shape;
                    }
                    if ((32768 & i) != 0) {
                        colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 15) & 7168, Integer.MAX_VALUE, 4095);
                        $dirty2 &= -458753;
                    } else {
                        colors2 = colors;
                    }
                    if ((i & 65536) != 0) {
                        if (function10 == null) {
                            contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        } else {
                            contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        }
                        $dirty2 &= -3670017;
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i18 != 0) {
                        final boolean z = isError2;
                        final TextFieldColors textFieldColors = colors2;
                        final Shape shape4 = shape2;
                        final int i22 = $dirty;
                        final int i23 = $dirty2;
                        shape3 = shape2;
                        function17 = function12;
                        function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, 2023266550, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.1
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
                                ComposerKt.sourceInformation($composer4, "C967@54325L64:TextFieldDefaults.kt#uh7d8r");
                                if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(2023266550, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:966)");
                                }
                                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                                boolean z2 = enabled;
                                boolean z3 = z;
                                InteractionSource interactionSource2 = interactionSource;
                                TextFieldColors textFieldColors2 = textFieldColors;
                                Shape shape5 = shape4;
                                int i24 = i22;
                                int i25 = ((i24 >> 9) & 896) | ((i24 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i24 >> 15) & 112);
                                int i26 = i23;
                                textFieldDefaults.ContainerBox(z2, z3, interactionSource2, textFieldColors2, shape5, $composer4, i25 | ((i26 >> 6) & 7168) | (i26 & 57344), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        });
                        function18 = function14;
                        function19 = function10;
                        function20 = function15;
                        function21 = function11;
                        isError3 = isError2;
                        function22 = function16;
                        colors3 = colors2;
                        contentPadding3 = contentPadding2;
                        function23 = function13;
                        $dirty1 = $dirty2;
                    } else {
                        shape3 = shape2;
                        function2ComposableLambda = function9;
                        function17 = function12;
                        function18 = function14;
                        function19 = function10;
                        function20 = function15;
                        function21 = function11;
                        isError3 = isError2;
                        function22 = function16;
                        colors3 = colors2;
                        contentPadding3 = contentPadding2;
                        function23 = function13;
                        $dirty1 = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 16384) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((32768 & i) != 0) {
                        $dirty2 &= -458753;
                    }
                    if ((i & 65536) != 0) {
                        $dirty2 &= -3670017;
                    }
                    isError3 = isError;
                    function19 = function2;
                    function21 = function3;
                    function17 = function4;
                    function23 = function5;
                    function18 = function6;
                    function20 = function7;
                    function22 = function8;
                    shape3 = shape;
                    colors3 = colors;
                    contentPadding3 = contentPadding;
                    function2ComposableLambda = function9;
                    $dirty1 = $dirty2;
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1042273141, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:943)");
                }
                $composer2 = $composer3;
                DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, function19, function21, function17, function23, function18, function20, function22, shape3, colors3, contentPadding3, function2ComposableLambda, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | (1879048192 & $dirty), ($dirty1 & 14) | ($dirty1 & 112) | ($dirty1 & 896) | ($dirty1 & 7168) | ($dirty1 & 57344) | ($dirty1 & 458752) | ($dirty1 & 3670016) | ($dirty1 & 29360128) | ($dirty1 & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final boolean z2 = isError3;
            final Function2<? super Composer, ? super Integer, Unit> function24 = function19;
            final Function2<? super Composer, ? super Integer, Unit> function25 = function21;
            final Function2<? super Composer, ? super Integer, Unit> function26 = function17;
            final Function2<? super Composer, ? super Integer, Unit> function27 = function23;
            final Function2<? super Composer, ? super Integer, Unit> function28 = function18;
            final Function2<? super Composer, ? super Integer, Unit> function29 = function20;
            final Function2<? super Composer, ? super Integer, Unit> function30 = function22;
            final Shape shape5 = shape3;
            final TextFieldColors textFieldColors2 = colors3;
            final PaddingValues paddingValues = contentPadding3;
            final Function2<? super Composer, ? super Integer, Unit> function31 = function2ComposableLambda;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.2
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

                public final void invoke(Composer composer, int i24) {
                    TextFieldDefaults.this.TextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z2, function24, function25, function26, function27, function28, function29, function30, shape5, textFieldColors2, paddingValues, function31, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty |= i2;
        i3 = i & 64;
        if (i3 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) != 0) {
            if ($composer3.changed(isError)) {
                i4 = 1048576;
            } else {
                i4 = 524288;
            }
            $dirty |= i4;
        }
        i5 = i & 128;
        if (i5 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 29360128) != 0) {
            if ($composer3.changedInstance(function2)) {
                i6 = 8388608;
            } else {
                i6 = 4194304;
            }
            $dirty |= i6;
        }
        i7 = i & 256;
        if (i7 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) != 0) {
            if ($composer3.changedInstance(function3)) {
                i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i8 = 33554432;
            }
            $dirty |= i8;
        }
        i9 = i & 512;
        if (i9 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) != 0) {
            if ($composer3.changedInstance(function4)) {
                i10 = 536870912;
            } else {
                i10 = 268435456;
            }
            $dirty |= i10;
        }
        i11 = i & 1024;
        if (i11 != 0) {
            $dirty2 |= 6;
        } else if (($changed1 & 14) != 0) {
            if ($composer3.changedInstance(function5)) {
                i12 = 4;
            } else {
                i12 = 2;
            }
            $dirty2 |= i12;
        }
        i13 = i & 2048;
        if (i13 != 0) {
            $dirty2 |= 48;
        } else if (($changed1 & 112) != 0) {
            if ($composer3.changedInstance(function6)) {
                i14 = 32;
            } else {
                i14 = 16;
            }
            $dirty2 |= i14;
        }
        i15 = i & 4096;
        if (i15 != 0) {
            $dirty2 |= 384;
        } else if (($changed1 & 896) != 0) {
            if ($composer3.changedInstance(function7)) {
                i16 = 256;
            } else {
                i16 = 128;
            }
            $dirty2 |= i16;
        }
        i17 = i & 8192;
        if (i17 != 0) {
            $dirty2 |= 3072;
        } else if (($changed1 & 7168) != 0) {
            $dirty2 |= $composer3.changedInstance(function8) ? 2048 : 1024;
        }
        if (($changed1 & 57344) != 0) {
            if ((i & 16384) == 0) {
                i21 = 16384;
            }
            $dirty2 |= i21;
        }
        if (($changed1 & 458752) != 0) {
            $dirty2 |= ((i & 32768) == 0 || !$composer3.changed(colors)) ? 65536 : 131072;
        }
        if (($changed1 & 3670016) != 0) {
            $dirty2 |= ((i & 65536) == 0 || !$composer3.changed(contentPadding)) ? 524288 : 1048576;
        }
        i18 = i & 131072;
        if (i18 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed1 & 29360128) != 0) {
            if ($composer3.changedInstance(function9)) {
                i19 = 8388608;
            } else {
                i19 = 4194304;
            }
            $dirty2 |= i19;
        }
        if ((i & 262144) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed1 & 234881024) != 0) {
            if ($composer3.changed(this)) {
                i20 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i20 = 33554432;
            }
            $dirty2 |= i20;
        }
        if (($dirty & 1533916891) != 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i17 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape2 = shape;
                }
                if ((32768 & i) != 0) {
                    colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 15) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -458753;
                } else {
                    colors2 = colors;
                }
                if ((i & 65536) != 0) {
                    if (function10 == null) {
                        contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty2 &= -3670017;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i18 != 0) {
                    final boolean z3 = isError2;
                    final TextFieldColors textFieldColors3 = colors2;
                    final Shape shape6 = shape2;
                    final int i24 = $dirty;
                    final int i25 = $dirty2;
                    shape3 = shape2;
                    function17 = function12;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, 2023266550, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C967@54325L64:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2023266550, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:966)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z4 = enabled;
                            boolean z5 = z3;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors4 = textFieldColors3;
                            Shape shape7 = shape6;
                            int i26 = i24;
                            int i27 = ((i26 >> 9) & 896) | ((i26 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i26 >> 15) & 112);
                            int i28 = i25;
                            textFieldDefaults.ContainerBox(z4, z5, interactionSource2, textFieldColors4, shape7, $composer4, i27 | ((i28 >> 6) & 7168) | (i28 & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function18 = function14;
                    function19 = function10;
                    function20 = function15;
                    function21 = function11;
                    isError3 = isError2;
                    function22 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    function23 = function13;
                    $dirty1 = $dirty2;
                } else {
                    shape3 = shape2;
                    function2ComposableLambda = function9;
                    function17 = function12;
                    function18 = function14;
                    function19 = function10;
                    function20 = function15;
                    function21 = function11;
                    isError3 = isError2;
                    function22 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    function23 = function13;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i17 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape2 = shape;
                }
                if ((32768 & i) != 0) {
                    colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 15) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -458753;
                } else {
                    colors2 = colors;
                }
                if ((i & 65536) != 0) {
                    if (function10 == null) {
                        contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty2 &= -3670017;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i18 != 0) {
                    final boolean z4 = isError2;
                    final TextFieldColors textFieldColors4 = colors2;
                    final Shape shape7 = shape2;
                    final int i26 = $dirty;
                    final int i27 = $dirty2;
                    shape3 = shape2;
                    function17 = function12;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, 2023266550, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C967@54325L64:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2023266550, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:966)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z5 = enabled;
                            boolean z6 = z4;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors5 = textFieldColors4;
                            Shape shape8 = shape7;
                            int i28 = i26;
                            int i29 = ((i28 >> 9) & 896) | ((i28 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i28 >> 15) & 112);
                            int i210 = i27;
                            textFieldDefaults.ContainerBox(z5, z6, interactionSource2, textFieldColors5, shape8, $composer4, i29 | ((i210 >> 6) & 7168) | (i210 & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function18 = function14;
                    function19 = function10;
                    function20 = function15;
                    function21 = function11;
                    isError3 = isError2;
                    function22 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    function23 = function13;
                    $dirty1 = $dirty2;
                } else {
                    shape3 = shape2;
                    function2ComposableLambda = function9;
                    function17 = function12;
                    function18 = function14;
                    function19 = function10;
                    function20 = function15;
                    function21 = function11;
                    isError3 = isError2;
                    function22 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    function23 = function13;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1042273141, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:943)");
            }
            $composer2 = $composer3;
            DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, function19, function21, function17, function23, function18, function20, function22, shape3, colors3, contentPadding3, function2ComposableLambda, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | (1879048192 & $dirty), ($dirty1 & 14) | ($dirty1 & 112) | ($dirty1 & 896) | ($dirty1 & 7168) | ($dirty1 & 57344) | ($dirty1 & 458752) | ($dirty1 & 3670016) | ($dirty1 & 29360128) | ($dirty1 & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i17 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape2 = shape;
                }
                if ((32768 & i) != 0) {
                    colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 15) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -458753;
                } else {
                    colors2 = colors;
                }
                if ((i & 65536) != 0) {
                    if (function10 == null) {
                        contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty2 &= -3670017;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i18 != 0) {
                    final boolean z5 = isError2;
                    final TextFieldColors textFieldColors5 = colors2;
                    final Shape shape8 = shape2;
                    final int i28 = $dirty;
                    final int i29 = $dirty2;
                    shape3 = shape2;
                    function17 = function12;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, 2023266550, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C967@54325L64:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2023266550, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:966)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z6 = enabled;
                            boolean z7 = z5;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors6 = textFieldColors5;
                            Shape shape9 = shape8;
                            int i210 = i28;
                            int i211 = ((i210 >> 9) & 896) | ((i210 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i210 >> 15) & 112);
                            int i212 = i29;
                            textFieldDefaults.ContainerBox(z6, z7, interactionSource2, textFieldColors6, shape9, $composer4, i211 | ((i212 >> 6) & 7168) | (i212 & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function18 = function14;
                    function19 = function10;
                    function20 = function15;
                    function21 = function11;
                    isError3 = isError2;
                    function22 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    function23 = function13;
                    $dirty1 = $dirty2;
                } else {
                    shape3 = shape2;
                    function2ComposableLambda = function9;
                    function17 = function12;
                    function18 = function14;
                    function19 = function10;
                    function20 = function15;
                    function21 = function11;
                    isError3 = isError2;
                    function22 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    function23 = function13;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i17 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    shape2 = shape;
                }
                if ((32768 & i) != 0) {
                    colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 15) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -458753;
                } else {
                    colors2 = colors;
                }
                if ((i & 65536) != 0) {
                    if (function10 == null) {
                        contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty2 &= -3670017;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i18 != 0) {
                    final boolean z6 = isError2;
                    final TextFieldColors textFieldColors6 = colors2;
                    final Shape shape9 = shape2;
                    final int i210 = $dirty;
                    final int i211 = $dirty2;
                    shape3 = shape2;
                    function17 = function12;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, 2023266550, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C967@54325L64:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2023266550, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:966)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z7 = enabled;
                            boolean z8 = z6;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors7 = textFieldColors6;
                            Shape shape10 = shape9;
                            int i212 = i210;
                            int i213 = ((i212 >> 9) & 896) | ((i212 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i212 >> 15) & 112);
                            int i214 = i211;
                            textFieldDefaults.ContainerBox(z7, z8, interactionSource2, textFieldColors7, shape10, $composer4, i213 | ((i214 >> 6) & 7168) | (i214 & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function18 = function14;
                    function19 = function10;
                    function20 = function15;
                    function21 = function11;
                    isError3 = isError2;
                    function22 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    function23 = function13;
                    $dirty1 = $dirty2;
                } else {
                    shape3 = shape2;
                    function2ComposableLambda = function9;
                    function17 = function12;
                    function18 = function14;
                    function19 = function10;
                    function20 = function15;
                    function21 = function11;
                    isError3 = isError2;
                    function22 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    function23 = function13;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1042273141, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:943)");
            }
            $composer2 = $composer3;
            DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, function19, function21, function17, function23, function18, function20, function22, shape3, colors3, contentPadding3, function2ComposableLambda, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | (1879048192 & $dirty), ($dirty1 & 14) | ($dirty1 & 112) | ($dirty1 & 896) | ($dirty1 & 7168) | ($dirty1 & 57344) | ($dirty1 & 458752) | ($dirty1 & 3670016) | ($dirty1 & 29360128) | ($dirty1 & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final boolean z7 = isError3;
        final Function2<? super Composer, ? super Integer, Unit> function210 = function19;
        final Function2<? super Composer, ? super Integer, Unit> function211 = function21;
        final Function2<? super Composer, ? super Integer, Unit> function212 = function17;
        final Function2<? super Composer, ? super Integer, Unit> function213 = function23;
        final Function2<? super Composer, ? super Integer, Unit> function214 = function18;
        final Function2<? super Composer, ? super Integer, Unit> function215 = function20;
        final Function2<? super Composer, ? super Integer, Unit> function32 = function22;
        final Shape shape10 = shape3;
        final TextFieldColors textFieldColors7 = colors3;
        final PaddingValues paddingValues2 = contentPadding3;
        final Function2<? super Composer, ? super Integer, Unit> function33 = function2ComposableLambda;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.2
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

            public final void invoke(Composer composer, int i212) {
                TextFieldDefaults.this.TextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z7, function210, function211, function212, function213, function214, function215, function32, shape10, textFieldColors7, paddingValues2, function33, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:100:0x015a  */
    /* JADX WARN: Code duplicated, block: B:102:0x0160  */
    /* JADX WARN: Code duplicated, block: B:104:0x0168  */
    /* JADX WARN: Code duplicated, block: B:105:0x016b  */
    /* JADX WARN: Code duplicated, block: B:107:0x0170  */
    /* JADX WARN: Code duplicated, block: B:110:0x0176  */
    /* JADX WARN: Code duplicated, block: B:111:0x017b  */
    /* JADX WARN: Code duplicated, block: B:113:0x017f  */
    /* JADX WARN: Code duplicated, block: B:115:0x0187  */
    /* JADX WARN: Code duplicated, block: B:116:0x018a  */
    /* JADX WARN: Code duplicated, block: B:118:0x018f  */
    /* JADX WARN: Code duplicated, block: B:121:0x0195  */
    /* JADX WARN: Code duplicated, block: B:122:0x019a  */
    /* JADX WARN: Code duplicated, block: B:124:0x019e  */
    /* JADX WARN: Code duplicated, block: B:126:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:127:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:129:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:132:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:133:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:135:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:138:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:140:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:143:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:144:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:146:0x01da  */
    /* JADX WARN: Code duplicated, block: B:149:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:151:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:154:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:161:0x0202  */
    /* JADX WARN: Code duplicated, block: B:164:0x020b  */
    /* JADX WARN: Code duplicated, block: B:172:0x0221  */
    /* JADX WARN: Code duplicated, block: B:175:0x0227  */
    /* JADX WARN: Code duplicated, block: B:176:0x022e  */
    /* JADX WARN: Code duplicated, block: B:178:0x0232  */
    /* JADX WARN: Code duplicated, block: B:180:0x023a  */
    /* JADX WARN: Code duplicated, block: B:181:0x023d  */
    /* JADX WARN: Code duplicated, block: B:183:0x0242  */
    /* JADX WARN: Code duplicated, block: B:192:0x027f  */
    /* JADX WARN: Code duplicated, block: B:194:0x0286  */
    /* JADX WARN: Code duplicated, block: B:204:0x02d2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:205:0x02d4  */
    /* JADX WARN: Code duplicated, block: B:206:0x02d6  */
    /* JADX WARN: Code duplicated, block: B:208:0x02da  */
    /* JADX WARN: Code duplicated, block: B:209:0x02dc  */
    /* JADX WARN: Code duplicated, block: B:211:0x02e0  */
    /* JADX WARN: Code duplicated, block: B:212:0x02e2  */
    /* JADX WARN: Code duplicated, block: B:214:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:215:0x02e8  */
    /* JADX WARN: Code duplicated, block: B:217:0x02ec  */
    /* JADX WARN: Code duplicated, block: B:218:0x02ee  */
    /* JADX WARN: Code duplicated, block: B:220:0x02f2  */
    /* JADX WARN: Code duplicated, block: B:221:0x02f4  */
    /* JADX WARN: Code duplicated, block: B:223:0x02f8  */
    /* JADX WARN: Code duplicated, block: B:224:0x02fa  */
    /* JADX WARN: Code duplicated, block: B:226:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:227:0x0300  */
    /* JADX WARN: Code duplicated, block: B:230:0x0306  */
    /* JADX WARN: Code duplicated, block: B:231:0x0379  */
    /* JADX WARN: Code duplicated, block: B:234:0x037f  */
    /* JADX WARN: Code duplicated, block: B:235:0x03a5  */
    /* JADX WARN: Code duplicated, block: B:237:0x03a9  */
    /* JADX WARN: Code duplicated, block: B:238:0x03e5  */
    /* JADX WARN: Code duplicated, block: B:241:0x040c  */
    /* JADX WARN: Code duplicated, block: B:244:0x0494  */
    /* JADX WARN: Code duplicated, block: B:248:0x049e  */
    /* JADX WARN: Code duplicated, block: B:250:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:67:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:71:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:72:0x0102  */
    /* JADX WARN: Code duplicated, block: B:74:0x0107  */
    /* JADX WARN: Code duplicated, block: B:77:0x010d  */
    /* JADX WARN: Code duplicated, block: B:78:0x0114  */
    /* JADX WARN: Code duplicated, block: B:80:0x011a  */
    /* JADX WARN: Code duplicated, block: B:82:0x0122  */
    /* JADX WARN: Code duplicated, block: B:83:0x0125  */
    /* JADX WARN: Code duplicated, block: B:85:0x012a  */
    /* JADX WARN: Code duplicated, block: B:88:0x0130  */
    /* JADX WARN: Code duplicated, block: B:89:0x0137  */
    /* JADX WARN: Code duplicated, block: B:91:0x013d  */
    /* JADX WARN: Code duplicated, block: B:93:0x0145  */
    /* JADX WARN: Code duplicated, block: B:94:0x0148  */
    /* JADX WARN: Code duplicated, block: B:96:0x014d  */
    /* JADX WARN: Code duplicated, block: B:99:0x0153  */
    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `OutlinedTextFieldDefaults.DecorationBox`", replaceWith = @ReplaceWith(expression = "OutlinedTextFieldDefaults.DecorationBox(\n        value = value,\n        innerTextField = innerTextField,\n        enabled = enabled,\n        singleLine = singleLine,\n        visualTransformation = visualTransformation,\n        interactionSource = interactionSource,\n        isError = isError,\n        label = label,\n        placeholder = placeholder,\n        leadingIcon = leadingIcon,\n        trailingIcon = trailingIcon,\n        prefix = prefix,\n        suffix = suffix,\n        supportingText = supportingText,\n        colors = colors,\n        contentPadding = contentPadding,\n        container = container,\n    )", imports = {"androidx.compose.material.OutlinedTextFieldDefaults"}))
    public final void OutlinedTextFieldDecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> innerTextField, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function9, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        boolean isError2;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Function2<? super Composer, ? super Integer, Unit> function16;
        TextFieldColors colors2;
        PaddingValues contentPadding2;
        Function2<? super Composer, ? super Integer, Unit> function17;
        Function2<? super Composer, ? super Integer, Unit> function2ComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Function2<? super Composer, ? super Integer, Unit> function22;
        boolean isError3;
        Function2<? super Composer, ? super Integer, Unit> function23;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        int $dirty1;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
        Intrinsics.checkNotNullParameter(visualTransformation, "visualTransformation");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Composer $composer3 = $composer.startRestartGroup(-789275592);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextFieldDecorationBox)P(15,4,3,11,16,5,6,7,9,8,14,10,12,13!1,2)1031@56982L8,1036@57262L589:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer3.changedInstance(innerTextField) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer3.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(singleLine) ? 2048 : 1024;
        }
        int i19 = 8192;
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
                i2 = $composer3.changed(interactionSource) ? 131072 : 65536;
            }
            i3 = i & 64;
            if (i3 != 0) {
                $dirty |= 1572864;
            } else if (($changed & 3670016) != 0) {
                if ($composer3.changed(isError)) {
                    i4 = 1048576;
                } else {
                    i4 = 524288;
                }
                $dirty |= i4;
            }
            i5 = i & 128;
            if (i5 != 0) {
                $dirty |= 12582912;
            } else if (($changed & 29360128) != 0) {
                if ($composer3.changedInstance(function2)) {
                    i6 = 8388608;
                } else {
                    i6 = 4194304;
                }
                $dirty |= i6;
            }
            i7 = i & 256;
            if (i7 != 0) {
                $dirty |= 100663296;
            } else if (($changed & 234881024) != 0) {
                if ($composer3.changedInstance(function3)) {
                    i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i8 = 33554432;
                }
                $dirty |= i8;
            }
            i9 = i & 512;
            if (i9 != 0) {
                $dirty |= 805306368;
            } else if (($changed & 1879048192) != 0) {
                if ($composer3.changedInstance(function4)) {
                    i10 = 536870912;
                } else {
                    i10 = 268435456;
                }
                $dirty |= i10;
            }
            i11 = i & 1024;
            if (i11 != 0) {
                $dirty2 |= 6;
            } else if (($changed1 & 14) != 0) {
                if ($composer3.changedInstance(function5)) {
                    i12 = 4;
                } else {
                    i12 = 2;
                }
                $dirty2 |= i12;
            }
            i13 = i & 2048;
            if (i13 != 0) {
                $dirty2 |= 48;
            } else if (($changed1 & 112) != 0) {
                if ($composer3.changedInstance(function6)) {
                    i14 = 32;
                } else {
                    i14 = 16;
                }
                $dirty2 |= i14;
            }
            i15 = i & 4096;
            if (i15 != 0) {
                $dirty2 |= 384;
            } else if (($changed1 & 896) != 0) {
                $dirty2 |= $composer3.changedInstance(function7) ? 256 : 128;
            }
            i16 = i & 8192;
            if (i16 != 0) {
                $dirty2 |= 3072;
            } else if (($changed1 & 7168) != 0) {
                $dirty2 |= $composer3.changedInstance(function8) ? 2048 : 1024;
            }
            if (($changed1 & 57344) != 0) {
                if ((i & 16384) == 0 && $composer3.changed(colors)) {
                    i19 = 16384;
                }
                $dirty2 |= i19;
            }
            if (($changed1 & 458752) != 0) {
                $dirty2 |= ((i & 32768) == 0 || !$composer3.changed(contentPadding)) ? 65536 : 131072;
            }
            i17 = i & 65536;
            if (i17 != 0) {
                $dirty2 |= 1572864;
            } else if (($changed1 & 3670016) != 0) {
                if ($composer3.changedInstance(function9)) {
                    i18 = 1048576;
                } else {
                    i18 = 524288;
                }
                $dirty2 |= i18;
            }
            if (($dirty & 1533916891) != 306783378 && (2995931 & $dirty2) == 599186 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                isError3 = isError;
                function21 = function2;
                function18 = function3;
                function17 = function4;
                function19 = function5;
                function20 = function6;
                function22 = function7;
                function23 = function8;
                colors3 = colors;
                contentPadding3 = contentPadding;
                function2ComposableLambda = function9;
                $composer2 = $composer3;
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        isError2 = false;
                    } else {
                        isError2 = isError;
                    }
                    if (i5 != 0) {
                        function10 = null;
                    } else {
                        function10 = function2;
                    }
                    if (i7 != 0) {
                        function11 = null;
                    } else {
                        function11 = function3;
                    }
                    if (i9 != 0) {
                        function12 = null;
                    } else {
                        function12 = function4;
                    }
                    if (i11 != 0) {
                        function13 = null;
                    } else {
                        function13 = function5;
                    }
                    if (i13 != 0) {
                        function14 = null;
                    } else {
                        function14 = function6;
                    }
                    if (i15 != 0) {
                        function15 = null;
                    } else {
                        function15 = function7;
                    }
                    if (i16 != 0) {
                        function16 = null;
                    } else {
                        function16 = function8;
                    }
                    if ((i & 16384) != 0) {
                        colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                        $dirty2 &= -57345;
                    } else {
                        colors2 = colors;
                    }
                    if ((i & 32768) != 0) {
                        contentPadding2 = OutlinedTextFieldDefaults.m1647contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        $dirty2 &= -458753;
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i17 != 0) {
                        final boolean z = isError2;
                        final TextFieldColors textFieldColors = colors2;
                        final int i20 = $dirty;
                        final int i21 = $dirty2;
                        function17 = function12;
                        function18 = function11;
                        function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -1153197597, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.1
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
                                ComposerKt.sourceInformation($composer4, "C1034@57160L57:TextFieldDefaults.kt#uh7d8r");
                                if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1153197597, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1033)");
                                }
                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                boolean z2 = enabled;
                                boolean z3 = z;
                                InteractionSource interactionSource2 = interactionSource;
                                TextFieldColors textFieldColors2 = textFieldColors;
                                int i22 = i20;
                                outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z2, z3, interactionSource2, textFieldColors2, null, 0.0f, 0.0f, $composer4, ((i22 >> 9) & 896) | ((i22 >> 6) & 14) | 12582912 | ((i22 >> 15) & 112) | ((i21 >> 3) & 7168), 112);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        });
                        function19 = function13;
                        function20 = function14;
                        function21 = function10;
                        function22 = function15;
                        isError3 = isError2;
                        function23 = function16;
                        colors3 = colors2;
                        contentPadding3 = contentPadding2;
                        $dirty1 = $dirty2;
                    } else {
                        function17 = function12;
                        function2ComposableLambda = function9;
                        function18 = function11;
                        function19 = function13;
                        function20 = function14;
                        function21 = function10;
                        function22 = function15;
                        isError3 = isError2;
                        function23 = function16;
                        colors3 = colors2;
                        contentPadding3 = contentPadding2;
                        $dirty1 = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 16384) != 0) {
                        $dirty2 &= -57345;
                    }
                    if ((i & 32768) != 0) {
                        isError3 = isError;
                        function21 = function2;
                        function18 = function3;
                        function17 = function4;
                        function19 = function5;
                        function20 = function6;
                        function22 = function7;
                        function23 = function8;
                        colors3 = colors;
                        contentPadding3 = contentPadding;
                        function2ComposableLambda = function9;
                        $dirty1 = (-458753) & $dirty2;
                    } else {
                        isError3 = isError;
                        function21 = function2;
                        function18 = function3;
                        function17 = function4;
                        function19 = function5;
                        function20 = function6;
                        function22 = function7;
                        function23 = function8;
                        colors3 = colors;
                        contentPadding3 = contentPadding;
                        function2ComposableLambda = function9;
                        $dirty1 = $dirty2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-789275592, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:1016)");
                }
                $composer2 = $composer3;
                OutlinedTextFieldDefaults.INSTANCE.DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, function21, function18, function17, function19, function20, function22, function23, colors3, contentPadding3, function2ComposableLambda, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), 12582912 | ($dirty1 & 14) | ($dirty1 & 112) | ($dirty1 & 896) | ($dirty1 & 7168) | ($dirty1 & 57344) | ($dirty1 & 458752) | ($dirty1 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final boolean z2 = isError3;
            final Function2<? super Composer, ? super Integer, Unit> function24 = function21;
            final Function2<? super Composer, ? super Integer, Unit> function25 = function18;
            final Function2<? super Composer, ? super Integer, Unit> function26 = function17;
            final Function2<? super Composer, ? super Integer, Unit> function27 = function19;
            final Function2<? super Composer, ? super Integer, Unit> function28 = function20;
            final Function2<? super Composer, ? super Integer, Unit> function29 = function22;
            final Function2<? super Composer, ? super Integer, Unit> function30 = function23;
            final TextFieldColors textFieldColors2 = colors3;
            final PaddingValues paddingValues = contentPadding3;
            final Function2<? super Composer, ? super Integer, Unit> function31 = function2ComposableLambda;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.2
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

                public final void invoke(Composer composer, int i22) {
                    TextFieldDefaults.this.OutlinedTextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z2, function24, function25, function26, function27, function28, function29, function30, textFieldColors2, paddingValues, function31, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty |= i2;
        i3 = i & 64;
        if (i3 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) != 0) {
            if ($composer3.changed(isError)) {
                i4 = 1048576;
            } else {
                i4 = 524288;
            }
            $dirty |= i4;
        }
        i5 = i & 128;
        if (i5 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 29360128) != 0) {
            if ($composer3.changedInstance(function2)) {
                i6 = 8388608;
            } else {
                i6 = 4194304;
            }
            $dirty |= i6;
        }
        i7 = i & 256;
        if (i7 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) != 0) {
            if ($composer3.changedInstance(function3)) {
                i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i8 = 33554432;
            }
            $dirty |= i8;
        }
        i9 = i & 512;
        if (i9 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) != 0) {
            if ($composer3.changedInstance(function4)) {
                i10 = 536870912;
            } else {
                i10 = 268435456;
            }
            $dirty |= i10;
        }
        i11 = i & 1024;
        if (i11 != 0) {
            $dirty2 |= 6;
        } else if (($changed1 & 14) != 0) {
            if ($composer3.changedInstance(function5)) {
                i12 = 4;
            } else {
                i12 = 2;
            }
            $dirty2 |= i12;
        }
        i13 = i & 2048;
        if (i13 != 0) {
            $dirty2 |= 48;
        } else if (($changed1 & 112) != 0) {
            if ($composer3.changedInstance(function6)) {
                i14 = 32;
            } else {
                i14 = 16;
            }
            $dirty2 |= i14;
        }
        i15 = i & 4096;
        if (i15 != 0) {
            $dirty2 |= 384;
        } else if (($changed1 & 896) != 0) {
            $dirty2 |= $composer3.changedInstance(function7) ? 256 : 128;
        }
        i16 = i & 8192;
        if (i16 != 0) {
            $dirty2 |= 3072;
        } else if (($changed1 & 7168) != 0) {
            $dirty2 |= $composer3.changedInstance(function8) ? 2048 : 1024;
        }
        if (($changed1 & 57344) != 0) {
            if ((i & 16384) == 0) {
                i19 = 16384;
            }
            $dirty2 |= i19;
        }
        if (($changed1 & 458752) != 0) {
            $dirty2 |= ((i & 32768) == 0 || !$composer3.changed(contentPadding)) ? 65536 : 131072;
        }
        i17 = i & 65536;
        if (i17 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed1 & 3670016) != 0) {
            if ($composer3.changedInstance(function9)) {
                i18 = 1048576;
            } else {
                i18 = 524288;
            }
            $dirty2 |= i18;
        }
        if (($dirty & 1533916891) != 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i16 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -57345;
                } else {
                    colors2 = colors;
                }
                if ((i & 32768) != 0) {
                    contentPadding2 = OutlinedTextFieldDefaults.m1647contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -458753;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i17 != 0) {
                    final boolean z3 = isError2;
                    final TextFieldColors textFieldColors3 = colors2;
                    final int i22 = $dirty;
                    final int i23 = $dirty2;
                    function17 = function12;
                    function18 = function11;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -1153197597, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C1034@57160L57:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1153197597, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1033)");
                            }
                            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                            boolean z4 = enabled;
                            boolean z5 = z3;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors4 = textFieldColors3;
                            int i24 = i22;
                            outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z4, z5, interactionSource2, textFieldColors4, null, 0.0f, 0.0f, $composer4, ((i24 >> 9) & 896) | ((i24 >> 6) & 14) | 12582912 | ((i24 >> 15) & 112) | ((i23 >> 3) & 7168), 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function19 = function13;
                    function20 = function14;
                    function21 = function10;
                    function22 = function15;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                } else {
                    function17 = function12;
                    function2ComposableLambda = function9;
                    function18 = function11;
                    function19 = function13;
                    function20 = function14;
                    function21 = function10;
                    function22 = function15;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i16 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -57345;
                } else {
                    colors2 = colors;
                }
                if ((i & 32768) != 0) {
                    contentPadding2 = OutlinedTextFieldDefaults.m1647contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -458753;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i17 != 0) {
                    final boolean z4 = isError2;
                    final TextFieldColors textFieldColors4 = colors2;
                    final int i24 = $dirty;
                    final int i25 = $dirty2;
                    function17 = function12;
                    function18 = function11;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -1153197597, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C1034@57160L57:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1153197597, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1033)");
                            }
                            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                            boolean z5 = enabled;
                            boolean z6 = z4;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors5 = textFieldColors4;
                            int i26 = i24;
                            outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z5, z6, interactionSource2, textFieldColors5, null, 0.0f, 0.0f, $composer4, ((i26 >> 9) & 896) | ((i26 >> 6) & 14) | 12582912 | ((i26 >> 15) & 112) | ((i25 >> 3) & 7168), 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function19 = function13;
                    function20 = function14;
                    function21 = function10;
                    function22 = function15;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                } else {
                    function17 = function12;
                    function2ComposableLambda = function9;
                    function18 = function11;
                    function19 = function13;
                    function20 = function14;
                    function21 = function10;
                    function22 = function15;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-789275592, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:1016)");
            }
            $composer2 = $composer3;
            OutlinedTextFieldDefaults.INSTANCE.DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, function21, function18, function17, function19, function20, function22, function23, colors3, contentPadding3, function2ComposableLambda, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), 12582912 | ($dirty1 & 14) | ($dirty1 & 112) | ($dirty1 & 896) | ($dirty1 & 7168) | ($dirty1 & 57344) | ($dirty1 & 458752) | ($dirty1 & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i16 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -57345;
                } else {
                    colors2 = colors;
                }
                if ((i & 32768) != 0) {
                    contentPadding2 = OutlinedTextFieldDefaults.m1647contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -458753;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i17 != 0) {
                    final boolean z5 = isError2;
                    final TextFieldColors textFieldColors5 = colors2;
                    final int i26 = $dirty;
                    final int i27 = $dirty2;
                    function17 = function12;
                    function18 = function11;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -1153197597, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C1034@57160L57:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1153197597, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1033)");
                            }
                            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                            boolean z6 = enabled;
                            boolean z7 = z5;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors6 = textFieldColors5;
                            int i28 = i26;
                            outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z6, z7, interactionSource2, textFieldColors6, null, 0.0f, 0.0f, $composer4, ((i28 >> 9) & 896) | ((i28 >> 6) & 14) | 12582912 | ((i28 >> 15) & 112) | ((i27 >> 3) & 7168), 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function19 = function13;
                    function20 = function14;
                    function21 = function10;
                    function22 = function15;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                } else {
                    function17 = function12;
                    function2ComposableLambda = function9;
                    function18 = function11;
                    function19 = function13;
                    function20 = function14;
                    function21 = function10;
                    function22 = function15;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    function10 = null;
                } else {
                    function10 = function2;
                }
                if (i7 != 0) {
                    function11 = null;
                } else {
                    function11 = function3;
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function4;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function5;
                }
                if (i13 != 0) {
                    function14 = null;
                } else {
                    function14 = function6;
                }
                if (i15 != 0) {
                    function15 = null;
                } else {
                    function15 = function7;
                }
                if (i16 != 0) {
                    function16 = null;
                } else {
                    function16 = function8;
                }
                if ((i & 16384) != 0) {
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -57345;
                } else {
                    colors2 = colors;
                }
                if ((i & 32768) != 0) {
                    contentPadding2 = OutlinedTextFieldDefaults.m1647contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -458753;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i17 != 0) {
                    final boolean z6 = isError2;
                    final TextFieldColors textFieldColors6 = colors2;
                    final int i28 = $dirty;
                    final int i29 = $dirty2;
                    function17 = function12;
                    function18 = function11;
                    function2ComposableLambda = ComposableLambdaKt.composableLambda($composer3, -1153197597, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.1
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
                            ComposerKt.sourceInformation($composer4, "C1034@57160L57:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1153197597, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1033)");
                            }
                            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                            boolean z7 = enabled;
                            boolean z8 = z6;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors7 = textFieldColors6;
                            int i210 = i28;
                            outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z7, z8, interactionSource2, textFieldColors7, null, 0.0f, 0.0f, $composer4, ((i210 >> 9) & 896) | ((i210 >> 6) & 14) | 12582912 | ((i210 >> 15) & 112) | ((i29 >> 3) & 7168), 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    function19 = function13;
                    function20 = function14;
                    function21 = function10;
                    function22 = function15;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                } else {
                    function17 = function12;
                    function2ComposableLambda = function9;
                    function18 = function11;
                    function19 = function13;
                    function20 = function14;
                    function21 = function10;
                    function22 = function15;
                    isError3 = isError2;
                    function23 = function16;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-789275592, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:1016)");
            }
            $composer2 = $composer3;
            OutlinedTextFieldDefaults.INSTANCE.DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, function21, function18, function17, function19, function20, function22, function23, colors3, contentPadding3, function2ComposableLambda, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), 12582912 | ($dirty1 & 14) | ($dirty1 & 112) | ($dirty1 & 896) | ($dirty1 & 7168) | ($dirty1 & 57344) | ($dirty1 & 458752) | ($dirty1 & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final boolean z7 = isError3;
        final Function2<? super Composer, ? super Integer, Unit> function210 = function21;
        final Function2<? super Composer, ? super Integer, Unit> function211 = function18;
        final Function2<? super Composer, ? super Integer, Unit> function212 = function17;
        final Function2<? super Composer, ? super Integer, Unit> function213 = function19;
        final Function2<? super Composer, ? super Integer, Unit> function214 = function20;
        final Function2<? super Composer, ? super Integer, Unit> function215 = function22;
        final Function2<? super Composer, ? super Integer, Unit> function32 = function23;
        final TextFieldColors textFieldColors7 = colors3;
        final PaddingValues paddingValues2 = contentPadding3;
        final Function2<? super Composer, ? super Integer, Unit> function33 = function2ComposableLambda;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.2
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

            public final void invoke(Composer composer, int i210) {
                TextFieldDefaults.this.OutlinedTextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z7, function210, function211, function212, function213, function214, function215, function32, textFieldColors7, paddingValues2, function33, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: textFieldColors-eS1Emto, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m1869textFieldColorseS1Emto(long textColor, long disabledTextColor, long containerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long placeholderColor, long disabledPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int i, int i2) {
        long disabledTextColor2;
        SelectionColors selectionColors2;
        long disabledIndicatorColor2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long disabledLabelColor2;
        long disabledPlaceholderColor2;
        long disabledSupportingTextColor2;
        long disabledPrefixColor2;
        long disabledSuffixColor2;
        $composer.startReplaceableGroup(-595874869);
        ComposerKt.sourceInformation($composer, "C(textFieldColors)P(28:c#ui.graphics.Color,9:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,11:c#ui.graphics.Color,27,19:c#ui.graphics.Color,29:c#ui.graphics.Color,2:c#ui.graphics.Color,12:c#ui.graphics.Color,21:c#ui.graphics.Color,31:c#ui.graphics.Color,4:c#ui.graphics.Color,14:c#ui.graphics.Color,25:c#ui.graphics.Color,35:c#ui.graphics.Color,10:c#ui.graphics.Color,18:c#ui.graphics.Color,20:c#ui.graphics.Color,30:c#ui.graphics.Color,3:c#ui.graphics.Color,13:c#ui.graphics.Color,26:c#ui.graphics.Color,5:c#ui.graphics.Color,24:c#ui.graphics.Color,34:c#ui.graphics.Color,8:c#ui.graphics.Color,17:c#ui.graphics.Color,22:c#ui.graphics.Color,32:c#ui.graphics.Color,6:c#ui.graphics.Color,15:c#ui.graphics.Color,23:c#ui.graphics.Color,33:c#ui.graphics.Color,7:c#ui.graphics.Color,16:c#ui.graphics.Color)1060@58072L9,1061@58159L9,1063@58309L9,1064@58382L9,1065@58470L9,1066@58553L7,1067@58649L9,1068@58744L9,1069@58846L9,1071@59022L9,1072@59118L9,1073@59211L9,1074@59311L9,1076@59481L9,1077@59579L9,1078@59674L9,1079@59776L9,1081@59949L9,1082@60033L9,1083@60114L9,1084@60202L9,1086@60354L9,1087@60443L9,1088@60537L9,1090@60705L9,1091@60800L9,1092@60902L9,1094@61073L9,1095@61159L9,1096@61247L9,1097@61334L9,1099@61488L9,1100@61574L9,1101@61662L9,1102@61749L9,1104@61903L9,1105@61939L2261:TextFieldDefaults.kt#uh7d8r");
        long textColor2 = (i & 1) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputColor(), $composer, 6) : textColor;
        if ((i & 2) != 0) {
            long color = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledTextColor2 = Color.m2969copywmQWz5c(color, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        long containerColor2 = (i & 4) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getContainerColor(), $composer, 6) : containerColor;
        long cursorColor2 = (i & 8) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getCaretColor(), $composer, 6) : cursorColor;
        long errorCursorColor2 = (i & 16) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorFocusCaretColor(), $composer, 6) : errorCursorColor;
        if ((i & 32) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) objConsume;
        } else {
            selectionColors2 = selectionColors;
        }
        long focusedIndicatorColor2 = (i & 64) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusActiveIndicatorColor(), $composer, 6) : focusedIndicatorColor;
        long unfocusedIndicatorColor2 = (i & 128) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getActiveIndicatorColor(), $composer, 6) : unfocusedIndicatorColor;
        if ((i & 256) != 0) {
            long color2 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledActiveIndicatorColor(), $composer, 6);
            disabledIndicatorColor2 = Color.m2969copywmQWz5c(color2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color2) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color2) : 0.0f);
        } else {
            disabledIndicatorColor2 = disabledIndicatorColor;
        }
        long errorIndicatorColor2 = (i & 512) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorActiveIndicatorColor(), $composer, 6) : errorIndicatorColor;
        long focusedLeadingIconColor2 = (i & 1024) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusLeadingIconColor(), $composer, 6) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (i & 2048) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getLeadingIconColor(), $composer, 6) : unfocusedLeadingIconColor;
        if ((i & 4096) != 0) {
            long color3 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), $composer, 6);
            disabledLeadingIconColor2 = Color.m2969copywmQWz5c(color3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color3) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color3) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (i & 8192) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorLeadingIconColor(), $composer, 6) : errorLeadingIconColor;
        long focusedTrailingIconColor2 = (i & 16384) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusTrailingIconColor(), $composer, 6) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (32768 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getTrailingIconColor(), $composer, 6) : unfocusedTrailingIconColor;
        if ((65536 & i) != 0) {
            long color4 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), $composer, 6);
            disabledTrailingIconColor2 = Color.m2969copywmQWz5c(color4, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color4) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color4) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color4) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color4) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (131072 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorTrailingIconColor(), $composer, 6) : errorTrailingIconColor;
        long focusedLabelColor2 = (262144 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusLabelColor(), $composer, 6) : focusedLabelColor;
        long unfocusedLabelColor2 = (524288 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getLabelColor(), $composer, 6) : unfocusedLabelColor;
        if ((1048576 & i) != 0) {
            long color5 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledLabelColor(), $composer, 6);
            disabledLabelColor2 = Color.m2969copywmQWz5c(color5, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color5) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color5) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color5) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color5) : 0.0f);
        } else {
            disabledLabelColor2 = disabledLabelColor;
        }
        long errorLabelColor2 = (2097152 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorLabelColor(), $composer, 6) : errorLabelColor;
        long placeholderColor2 = (4194304 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : placeholderColor;
        if ((8388608 & i) != 0) {
            long color6 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledPlaceholderColor2 = Color.m2969copywmQWz5c(color6, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color6) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color6) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color6) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color6) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        long focusedSupportingTextColor2 = (16777216 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getFocusSupportingColor(), $composer, 6) : focusedSupportingTextColor;
        long unfocusedSupportingTextColor2 = (33554432 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getSupportingColor(), $composer, 6) : unfocusedSupportingTextColor;
        if ((67108864 & i) != 0) {
            long color7 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getDisabledSupportingColor(), $composer, 6);
            disabledSupportingTextColor2 = Color.m2969copywmQWz5c(color7, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color7) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color7) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color7) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color7) : 0.0f);
        } else {
            disabledSupportingTextColor2 = disabledSupportingTextColor;
        }
        long errorSupportingTextColor2 = (134217728 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getErrorSupportingColor(), $composer, 6) : errorSupportingTextColor;
        long focusedPrefixColor2 = (268435456 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : focusedPrefixColor;
        long unfocusedPrefixColor2 = (536870912 & i) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : unfocusedPrefixColor;
        if ((i & 1073741824) != 0) {
            long color8 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6);
            disabledPrefixColor2 = Color.m2969copywmQWz5c(color8, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color8) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color8) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color8) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color8) : 0.0f);
        } else {
            disabledPrefixColor2 = disabledPrefixColor;
        }
        long errorPrefixColor2 = (i2 & 1) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : errorPrefixColor;
        long focusedSuffixColor2 = (i2 & 2) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 4) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : unfocusedSuffixColor;
        if ((i2 & 8) != 0) {
            long color9 = ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6);
            disabledSuffixColor2 = Color.m2969copywmQWz5c(color9, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color9) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color9) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color9) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color9) : 0.0f);
        } else {
            disabledSuffixColor2 = disabledSuffixColor;
        }
        long errorSuffixColor2 = (i2 & 16) != 0 ? ColorSchemeKt.toColor(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-595874869, $changed, $changed1, "androidx.compose.material3.TextFieldDefaults.textFieldColors (TextFieldDefaults.kt:1059)");
        }
        TextFieldColors textFieldColorsM1854colors0hiis_0 = m1854colors0hiis_0(textColor2, textColor2, disabledTextColor2, textColor2, containerColor2, containerColor2, containerColor2, containerColor2, cursorColor2, errorCursorColor2, selectionColors2, focusedIndicatorColor2, unfocusedIndicatorColor2, disabledIndicatorColor2, errorIndicatorColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, placeholderColor2, placeholderColor2, disabledPlaceholderColor2, placeholderColor2, focusedSupportingTextColor2, unfocusedSupportingTextColor2, disabledSupportingTextColor2, errorSupportingTextColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2, $composer, ($changed & 14) | (($changed << 3) & 112) | (($changed << 3) & 896) | (($changed << 9) & 7168) | (($changed << 6) & 57344) | (($changed << 9) & 458752) | (($changed << 12) & 3670016) | (($changed << 15) & 29360128) | (($changed << 15) & 234881024) | (($changed << 15) & 1879048192), (($changed >> 15) & 14) | (($changed >> 15) & 112) | (($changed >> 15) & 896) | (($changed >> 15) & 7168) | (($changed >> 15) & 57344) | (($changed1 << 15) & 458752) | (($changed1 << 15) & 3670016) | (($changed1 << 15) & 29360128) | (($changed1 << 15) & 234881024) | (($changed1 << 15) & 1879048192), (($changed1 >> 15) & 14) | (($changed1 >> 15) & 112) | (($changed1 >> 15) & 896) | (($changed1 >> 15) & 7168) | (($changed1 >> 15) & 57344) | (($changed2 << 15) & 458752) | (($changed2 << 15) & 3670016) | (($changed2 << 15) & 29360128) | (($changed2 << 18) & 234881024) | (($changed2 << 18) & 1879048192), (($changed2 >> 6) & 14) | (($changed2 >> 9) & 112) | (($changed2 >> 9) & 896) | (($changed2 >> 9) & 7168) | (($changed2 >> 9) & 57344) | (($changed2 >> 9) & 458752) | (($changed2 >> 9) & 3670016) | (($changed3 << 21) & 29360128) | (($changed3 << 21) & 234881024) | (($changed3 << 21) & 1879048192), (($changed3 >> 9) & 14) | (($changed3 >> 9) & 112) | (($changed3 >> 9) & 896) | (($changed3 >> 9) & 7168), 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return textFieldColorsM1854colors0hiis_0;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: outlinedTextFieldColors-eS1Emto, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m1865outlinedTextFieldColorseS1Emto(long textColor, long disabledTextColor, long containerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedBorderColor, long unfocusedBorderColor, long disabledBorderColor, long errorBorderColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long placeholderColor, long disabledPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int i, int i2) {
        long disabledTextColor2;
        SelectionColors selectionColors2;
        long disabledBorderColor2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long disabledLabelColor2;
        long disabledPlaceholderColor2;
        long disabledSupportingTextColor2;
        long disabledPrefixColor2;
        long disabledSuffixColor2;
        $composer.startReplaceableGroup(1767818445);
        ComposerKt.sourceInformation($composer, "C(outlinedTextFieldColors)P(28:c#ui.graphics.Color,9:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,12:c#ui.graphics.Color,27,19:c#ui.graphics.Color,29:c#ui.graphics.Color,2:c#ui.graphics.Color,11:c#ui.graphics.Color,21:c#ui.graphics.Color,31:c#ui.graphics.Color,4:c#ui.graphics.Color,14:c#ui.graphics.Color,25:c#ui.graphics.Color,35:c#ui.graphics.Color,10:c#ui.graphics.Color,18:c#ui.graphics.Color,20:c#ui.graphics.Color,30:c#ui.graphics.Color,3:c#ui.graphics.Color,13:c#ui.graphics.Color,26:c#ui.graphics.Color,5:c#ui.graphics.Color,24:c#ui.graphics.Color,34:c#ui.graphics.Color,8:c#ui.graphics.Color,17:c#ui.graphics.Color,22:c#ui.graphics.Color,32:c#ui.graphics.Color,6:c#ui.graphics.Color,15:c#ui.graphics.Color,23:c#ui.graphics.Color,33:c#ui.graphics.Color,7:c#ui.graphics.Color,16:c#ui.graphics.Color)1155@64431L9,1156@64520L9,1159@64718L9,1160@64808L9,1161@64891L7,1162@64978L9,1163@65064L9,1164@65157L9,1166@65318L9,1167@65416L9,1168@65511L9,1169@65613L9,1171@65787L9,1172@65887L9,1173@65984L9,1175@66101L9,1176@66265L9,1177@66351L9,1178@66434L9,1179@66524L9,1181@66680L9,1182@66771L9,1183@66867L9,1185@67039L9,1186@67136L9,1188@67253L9,1189@67415L9,1190@67503L9,1191@67593L9,1192@67682L9,1194@67840L9,1195@67928L9,1196@68018L9,1197@68107L9,1199@68265L9,1200@68327L2237:TextFieldDefaults.kt#uh7d8r");
        long textColor2 = (i & 1) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputColor(), $composer, 6) : textColor;
        if ((i & 2) != 0) {
            long color = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledTextColor2 = Color.m2969copywmQWz5c(color, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        long containerColor2 = (i & 4) != 0 ? Color.INSTANCE.m3006getTransparent0d7_KjU() : containerColor;
        long cursorColor2 = (i & 8) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getCaretColor(), $composer, 6) : cursorColor;
        long errorCursorColor2 = (i & 16) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorFocusCaretColor(), $composer, 6) : errorCursorColor;
        if ((i & 32) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) objConsume;
        } else {
            selectionColors2 = selectionColors;
        }
        long focusedBorderColor2 = (i & 64) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusOutlineColor(), $composer, 6) : focusedBorderColor;
        long unfocusedBorderColor2 = (i & 128) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getOutlineColor(), $composer, 6) : unfocusedBorderColor;
        if ((i & 256) != 0) {
            long color2 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledOutlineColor(), $composer, 6);
            disabledBorderColor2 = Color.m2969copywmQWz5c(color2, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color2) : 0.12f, (14 & 2) != 0 ? Color.m2977getRedimpl(color2) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color2) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color2) : 0.0f);
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        long errorBorderColor2 = (i & 512) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorOutlineColor(), $composer, 6) : errorBorderColor;
        long focusedLeadingIconColor2 = (i & 1024) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusLeadingIconColor(), $composer, 6) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (i & 2048) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getLeadingIconColor(), $composer, 6) : unfocusedLeadingIconColor;
        if ((i & 4096) != 0) {
            long color3 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), $composer, 6);
            disabledLeadingIconColor2 = Color.m2969copywmQWz5c(color3, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color3) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color3) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color3) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color3) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (i & 8192) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorLeadingIconColor(), $composer, 6) : errorLeadingIconColor;
        long focusedTrailingIconColor2 = (i & 16384) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusTrailingIconColor(), $composer, 6) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (32768 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getTrailingIconColor(), $composer, 6) : unfocusedTrailingIconColor;
        if ((65536 & i) != 0) {
            long color4 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), $composer, 6);
            disabledTrailingIconColor2 = Color.m2969copywmQWz5c(color4, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color4) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color4) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color4) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color4) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (131072 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorTrailingIconColor(), $composer, 6) : errorTrailingIconColor;
        long focusedLabelColor2 = (262144 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusLabelColor(), $composer, 6) : focusedLabelColor;
        long unfocusedLabelColor2 = (524288 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getLabelColor(), $composer, 6) : unfocusedLabelColor;
        if ((1048576 & i) != 0) {
            long color5 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledLabelColor(), $composer, 6);
            disabledLabelColor2 = Color.m2969copywmQWz5c(color5, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color5) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color5) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color5) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color5) : 0.0f);
        } else {
            disabledLabelColor2 = disabledLabelColor;
        }
        long errorLabelColor2 = (2097152 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorLabelColor(), $composer, 6) : errorLabelColor;
        long placeholderColor2 = (4194304 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor(), $composer, 6) : placeholderColor;
        if ((8388608 & i) != 0) {
            long color6 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledPlaceholderColor2 = Color.m2969copywmQWz5c(color6, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color6) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color6) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color6) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color6) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        long focusedSupportingTextColor2 = (16777216 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getFocusSupportingColor(), $composer, 6) : focusedSupportingTextColor;
        long unfocusedSupportingTextColor2 = (33554432 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getSupportingColor(), $composer, 6) : unfocusedSupportingTextColor;
        if ((67108864 & i) != 0) {
            long color7 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getDisabledSupportingColor(), $composer, 6);
            disabledSupportingTextColor2 = Color.m2969copywmQWz5c(color7, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color7) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color7) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color7) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color7) : 0.0f);
        } else {
            disabledSupportingTextColor2 = disabledSupportingTextColor;
        }
        long errorSupportingTextColor2 = (134217728 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getErrorSupportingColor(), $composer, 6) : errorSupportingTextColor;
        long focusedPrefixColor2 = (268435456 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : focusedPrefixColor;
        long unfocusedPrefixColor2 = (536870912 & i) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : unfocusedPrefixColor;
        if ((i & 1073741824) != 0) {
            long color8 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6);
            disabledPrefixColor2 = Color.m2969copywmQWz5c(color8, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color8) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color8) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color8) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color8) : 0.0f);
        } else {
            disabledPrefixColor2 = disabledPrefixColor;
        }
        long errorPrefixColor2 = (i2 & 1) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : errorPrefixColor;
        long focusedSuffixColor2 = (i2 & 2) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 4) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : unfocusedSuffixColor;
        if ((i2 & 8) != 0) {
            long color9 = ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6);
            disabledSuffixColor2 = Color.m2969copywmQWz5c(color9, (14 & 1) != 0 ? Color.m2973getAlphaimpl(color9) : 0.38f, (14 & 2) != 0 ? Color.m2977getRedimpl(color9) : 0.0f, (14 & 4) != 0 ? Color.m2976getGreenimpl(color9) : 0.0f, (14 & 8) != 0 ? Color.m2974getBlueimpl(color9) : 0.0f);
        } else {
            disabledSuffixColor2 = disabledSuffixColor;
        }
        long errorSuffixColor2 = (i2 & 16) != 0 ? ColorSchemeKt.toColor(OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1767818445, $changed, $changed1, "androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors (TextFieldDefaults.kt:1154)");
        }
        TextFieldColors textFieldColorsM1649colors0hiis_0 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(textColor2, textColor2, disabledTextColor2, textColor2, containerColor2, containerColor2, containerColor2, containerColor2, cursorColor2, errorCursorColor2, selectionColors2, focusedBorderColor2, unfocusedBorderColor2, disabledBorderColor2, errorBorderColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, placeholderColor2, placeholderColor2, disabledPlaceholderColor2, placeholderColor2, focusedSupportingTextColor2, unfocusedSupportingTextColor2, disabledSupportingTextColor2, errorSupportingTextColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2, $composer, ($changed & 14) | (($changed << 3) & 112) | (($changed << 3) & 896) | (($changed << 9) & 7168) | (($changed << 6) & 57344) | (($changed << 9) & 458752) | (($changed << 12) & 3670016) | (($changed << 15) & 29360128) | (($changed << 15) & 234881024) | (($changed << 15) & 1879048192), (($changed >> 15) & 14) | (($changed >> 15) & 112) | (($changed >> 15) & 896) | (($changed >> 15) & 7168) | (($changed >> 15) & 57344) | (($changed1 << 15) & 458752) | (($changed1 << 15) & 3670016) | (($changed1 << 15) & 29360128) | (($changed1 << 15) & 234881024) | (($changed1 << 15) & 1879048192), (($changed1 >> 15) & 14) | (($changed1 >> 15) & 112) | (($changed1 >> 15) & 896) | (($changed1 >> 15) & 7168) | (($changed1 >> 15) & 57344) | (($changed2 << 15) & 458752) | (($changed2 << 15) & 3670016) | (($changed2 << 15) & 29360128) | (($changed2 << 18) & 234881024) | (($changed2 << 18) & 1879048192), (($changed2 >> 6) & 14) | (($changed2 >> 9) & 112) | (($changed2 >> 9) & 896) | (($changed2 >> 9) & 7168) | (($changed2 >> 9) & 57344) | (($changed2 >> 9) & 458752) | (($changed2 >> 9) & 3670016) | (($changed3 << 21) & 29360128) | (($changed3 << 21) & 234881024) | (($changed3 << 21) & 1879048192), (($changed3 >> 9) & 14) | 3072 | (($changed3 >> 9) & 112) | (($changed3 >> 9) & 896), 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return textFieldColorsM1649colors0hiis_0;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0158  */
    /* JADX WARN: Code duplicated, block: B:102:0x015e  */
    /* JADX WARN: Code duplicated, block: B:104:0x0166  */
    /* JADX WARN: Code duplicated, block: B:105:0x0169  */
    /* JADX WARN: Code duplicated, block: B:107:0x016e  */
    /* JADX WARN: Code duplicated, block: B:110:0x0174  */
    /* JADX WARN: Code duplicated, block: B:111:0x0179  */
    /* JADX WARN: Code duplicated, block: B:113:0x017d  */
    /* JADX WARN: Code duplicated, block: B:115:0x0185  */
    /* JADX WARN: Code duplicated, block: B:116:0x0188  */
    /* JADX WARN: Code duplicated, block: B:118:0x018d  */
    /* JADX WARN: Code duplicated, block: B:121:0x0193  */
    /* JADX WARN: Code duplicated, block: B:122:0x0198  */
    /* JADX WARN: Code duplicated, block: B:124:0x019c  */
    /* JADX WARN: Code duplicated, block: B:126:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:127:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:129:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:132:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:139:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:142:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:150:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:153:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:161:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:164:0x0203  */
    /* JADX WARN: Code duplicated, block: B:165:0x020a  */
    /* JADX WARN: Code duplicated, block: B:167:0x020e  */
    /* JADX WARN: Code duplicated, block: B:169:0x0216  */
    /* JADX WARN: Code duplicated, block: B:170:0x0219  */
    /* JADX WARN: Code duplicated, block: B:172:0x021e  */
    /* JADX WARN: Code duplicated, block: B:175:0x0224  */
    /* JADX WARN: Code duplicated, block: B:176:0x022b  */
    /* JADX WARN: Code duplicated, block: B:178:0x022f  */
    /* JADX WARN: Code duplicated, block: B:180:0x0237  */
    /* JADX WARN: Code duplicated, block: B:181:0x023a  */
    /* JADX WARN: Code duplicated, block: B:183:0x023f  */
    /* JADX WARN: Code duplicated, block: B:192:0x027a  */
    /* JADX WARN: Code duplicated, block: B:194:0x0281  */
    /* JADX WARN: Code duplicated, block: B:207:0x02b6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:208:0x02b8  */
    /* JADX WARN: Code duplicated, block: B:209:0x02ba  */
    /* JADX WARN: Code duplicated, block: B:211:0x02be  */
    /* JADX WARN: Code duplicated, block: B:212:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:214:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:215:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:217:0x02ca  */
    /* JADX WARN: Code duplicated, block: B:218:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:220:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:221:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:223:0x02d6  */
    /* JADX WARN: Code duplicated, block: B:224:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:227:0x02de  */
    /* JADX WARN: Code duplicated, block: B:228:0x02ea  */
    /* JADX WARN: Code duplicated, block: B:231:0x02f2  */
    /* JADX WARN: Code duplicated, block: B:232:0x0366  */
    /* JADX WARN: Code duplicated, block: B:235:0x036e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:236:0x0370  */
    /* JADX WARN: Code duplicated, block: B:237:0x038e  */
    /* JADX WARN: Code duplicated, block: B:239:0x03b2  */
    /* JADX WARN: Code duplicated, block: B:241:0x03b6  */
    /* JADX WARN: Code duplicated, block: B:242:0x03f2  */
    /* JADX WARN: Code duplicated, block: B:245:0x0417  */
    /* JADX WARN: Code duplicated, block: B:248:0x04a9  */
    /* JADX WARN: Code duplicated, block: B:252:0x04b3  */
    /* JADX WARN: Code duplicated, block: B:254:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:67:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:71:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:72:0x0100  */
    /* JADX WARN: Code duplicated, block: B:74:0x0105  */
    /* JADX WARN: Code duplicated, block: B:77:0x010d  */
    /* JADX WARN: Code duplicated, block: B:78:0x0114  */
    /* JADX WARN: Code duplicated, block: B:80:0x0118  */
    /* JADX WARN: Code duplicated, block: B:82:0x0120  */
    /* JADX WARN: Code duplicated, block: B:83:0x0123  */
    /* JADX WARN: Code duplicated, block: B:85:0x0128  */
    /* JADX WARN: Code duplicated, block: B:88:0x0130  */
    /* JADX WARN: Code duplicated, block: B:89:0x0137  */
    /* JADX WARN: Code duplicated, block: B:91:0x013b  */
    /* JADX WARN: Code duplicated, block: B:93:0x0143  */
    /* JADX WARN: Code duplicated, block: B:94:0x0146  */
    /* JADX WARN: Code duplicated, block: B:96:0x014b  */
    /* JADX WARN: Code duplicated, block: B:99:0x0151  */
    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use overload with prefix and suffix parameters")
    public final /* synthetic */ void TextFieldDecorationBox(final String value, final Function2 innerTextField, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2 label, Function2 placeholder, Function2 leadingIcon, Function2 trailingIcon, Function2 supportingText, Shape shape, TextFieldColors colors, PaddingValues contentPadding, Function2 container, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        boolean isError2;
        Function2 label2;
        Function2 placeholder2;
        Function2 leadingIcon2;
        Function2 trailingIcon2;
        Function2 supportingText2;
        Shape shape2;
        TextFieldColors colors2;
        Function2 trailingIcon3;
        PaddingValues contentPadding2;
        Shape shape3;
        PaddingValues contentPadding3;
        Function2 container2;
        Function2 leadingIcon3;
        Function2 supportingText3;
        Function2 label3;
        boolean isError3;
        Function2 placeholder3;
        TextFieldColors colors3;
        Function2 trailingIcon4;
        int $dirty1;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
        Intrinsics.checkNotNullParameter(visualTransformation, "visualTransformation");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Composer $composer3 = $composer.startRestartGroup(-1224712461);
        ComposerKt.sourceInformation($composer3, "C(TextFieldDecorationBox)P(14,4,3,11,15,5,6,7,9,8,13,12,10!1,2)1262@71315L5,1263@71356L8,1274@71711L684:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer3.changedInstance(innerTextField) ? 32 : 16;
        }
        int i18 = 128;
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer3.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(singleLine) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
                i2 = $composer3.changed(interactionSource) ? 131072 : 65536;
            }
            i3 = i & 64;
            if (i3 != 0) {
                $dirty |= 1572864;
            } else if (($changed & 3670016) != 0) {
                if ($composer3.changed(isError)) {
                    i4 = 1048576;
                } else {
                    i4 = 524288;
                }
                $dirty |= i4;
            }
            i5 = i & 128;
            if (i5 != 0) {
                $dirty |= 12582912;
            } else if (($changed & 29360128) != 0) {
                if ($composer3.changedInstance(label)) {
                    i6 = 8388608;
                } else {
                    i6 = 4194304;
                }
                $dirty |= i6;
            }
            i7 = i & 256;
            if (i7 != 0) {
                $dirty |= 100663296;
            } else if (($changed & 234881024) != 0) {
                if ($composer3.changedInstance(placeholder)) {
                    i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i8 = 33554432;
                }
                $dirty |= i8;
            }
            i9 = i & 512;
            if (i9 != 0) {
                $dirty |= 805306368;
            } else if (($changed & 1879048192) != 0) {
                if ($composer3.changedInstance(leadingIcon)) {
                    i10 = 536870912;
                } else {
                    i10 = 268435456;
                }
                $dirty |= i10;
            }
            i11 = i & 1024;
            if (i11 != 0) {
                $dirty2 |= 6;
            } else if (($changed1 & 14) != 0) {
                if ($composer3.changedInstance(trailingIcon)) {
                    i12 = 4;
                } else {
                    i12 = 2;
                }
                $dirty2 |= i12;
            }
            i13 = i & 2048;
            if (i13 != 0) {
                $dirty2 |= 48;
            } else if (($changed1 & 112) != 0) {
                if ($composer3.changedInstance(supportingText)) {
                    i14 = 32;
                } else {
                    i14 = 16;
                }
                $dirty2 |= i14;
            }
            if (($changed1 & 896) != 0) {
                if ((i & 4096) == 0 && $composer3.changed(shape)) {
                    i18 = 256;
                }
                $dirty2 |= i18;
            }
            if (($changed1 & 7168) != 0) {
                $dirty2 |= ((i & 8192) == 0 || !$composer3.changed(colors)) ? 1024 : 2048;
            }
            if (($changed1 & 57344) != 0) {
                $dirty2 |= ((i & 16384) == 0 || !$composer3.changed(contentPadding)) ? 8192 : 16384;
            }
            i15 = i & 32768;
            if (i15 != 0) {
                $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if (($changed1 & 458752) != 0) {
                if ($composer3.changedInstance(container)) {
                    i16 = 131072;
                } else {
                    i16 = 65536;
                }
                $dirty2 |= i16;
            }
            if ((i & 65536) != 0) {
                $dirty2 |= 1572864;
            } else if (($changed1 & 3670016) != 0) {
                if ($composer3.changed(this)) {
                    i17 = 1048576;
                } else {
                    i17 = 524288;
                }
                $dirty2 |= i17;
            }
            if (($dirty & 1533916891) != 306783378 && (2995931 & $dirty2) == 599186 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                isError3 = isError;
                label3 = label;
                placeholder3 = placeholder;
                leadingIcon3 = leadingIcon;
                trailingIcon4 = trailingIcon;
                supportingText3 = supportingText;
                shape3 = shape;
                colors3 = colors;
                contentPadding3 = contentPadding;
                container2 = container;
                $composer2 = $composer3;
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        isError2 = false;
                    } else {
                        isError2 = isError;
                    }
                    if (i5 != 0) {
                        label2 = null;
                    } else {
                        label2 = label;
                    }
                    if (i7 != 0) {
                        placeholder2 = null;
                    } else {
                        placeholder2 = placeholder;
                    }
                    if (i9 != 0) {
                        leadingIcon2 = null;
                    } else {
                        leadingIcon2 = leadingIcon;
                    }
                    if (i11 != 0) {
                        trailingIcon2 = null;
                    } else {
                        trailingIcon2 = trailingIcon;
                    }
                    if (i13 != 0) {
                        supportingText2 = null;
                    } else {
                        supportingText2 = supportingText;
                    }
                    if ((i & 4096) != 0) {
                        shape2 = INSTANCE.getShape($composer3, 6);
                        $dirty2 &= -897;
                    } else {
                        shape2 = shape;
                    }
                    if ((i & 8192) != 0) {
                        colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 9) & 7168, Integer.MAX_VALUE, 4095);
                        $dirty2 &= -7169;
                    } else {
                        colors2 = colors;
                    }
                    trailingIcon3 = trailingIcon2;
                    if ((i & 16384) != 0) {
                        if (label2 == null) {
                            contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        } else {
                            contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        }
                        $dirty2 &= -57345;
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i15 != 0) {
                        final boolean z = isError2;
                        final TextFieldColors textFieldColors = colors2;
                        final Shape shape4 = shape2;
                        final int i19 = $dirty;
                        final int i20 = $dirty2;
                        shape3 = shape2;
                        contentPadding3 = contentPadding2;
                        leadingIcon3 = leadingIcon2;
                        container2 = ComposableLambdaKt.composableLambda($composer3, -1171460386, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.3
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
                                ComposerKt.sourceInformation($composer4, "C1271@71620L64:TextFieldDefaults.kt#uh7d8r");
                                if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1171460386, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1270)");
                                }
                                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                                boolean z2 = enabled;
                                boolean z3 = z;
                                InteractionSource interactionSource2 = interactionSource;
                                TextFieldColors textFieldColors2 = textFieldColors;
                                Shape shape5 = shape4;
                                int i21 = i19;
                                int i22 = ((i21 >> 9) & 896) | ((i21 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i21 >> 15) & 112);
                                int i23 = i20;
                                textFieldDefaults.ContainerBox(z2, z3, interactionSource2, textFieldColors2, shape5, $composer4, i22 | (i23 & 7168) | ((i23 << 6) & 57344), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        });
                        supportingText3 = supportingText2;
                        label3 = label2;
                        isError3 = isError2;
                        placeholder3 = placeholder2;
                        colors3 = colors2;
                        trailingIcon4 = trailingIcon3;
                        $dirty1 = $dirty2;
                    } else {
                        shape3 = shape2;
                        contentPadding3 = contentPadding2;
                        container2 = container;
                        leadingIcon3 = leadingIcon2;
                        supportingText3 = supportingText2;
                        label3 = label2;
                        isError3 = isError2;
                        placeholder3 = placeholder2;
                        colors3 = colors2;
                        trailingIcon4 = trailingIcon3;
                        $dirty1 = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 4096) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 8192) != 0) {
                        $dirty2 &= -7169;
                    }
                    if ((i & 16384) != 0) {
                        $dirty2 &= -57345;
                    }
                    isError3 = isError;
                    label3 = label;
                    placeholder3 = placeholder;
                    leadingIcon3 = leadingIcon;
                    trailingIcon4 = trailingIcon;
                    supportingText3 = supportingText;
                    shape3 = shape;
                    colors3 = colors;
                    contentPadding3 = contentPadding;
                    container2 = container;
                    $dirty1 = $dirty2;
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1224712461, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:1249)");
                }
                $composer2 = $composer3;
                DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, label3, placeholder3, leadingIcon3, trailingIcon4, null, null, supportingText3, shape3, colors3, contentPadding3, container2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | (1879048192 & $dirty), ($dirty1 & 14) | 432 | (($dirty1 << 6) & 7168) | (($dirty1 << 6) & 57344) | (($dirty1 << 6) & 458752) | (($dirty1 << 6) & 3670016) | (($dirty1 << 6) & 29360128) | (($dirty1 << 6) & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final boolean z2 = isError3;
            final Function2 function2 = label3;
            final Function2 function3 = placeholder3;
            final Function2 function4 = leadingIcon3;
            final Function2 function5 = trailingIcon4;
            final Function2 function6 = supportingText3;
            final Shape shape5 = shape3;
            final TextFieldColors textFieldColors2 = colors3;
            final PaddingValues paddingValues = contentPadding3;
            final Function2 function7 = container2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.4
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

                public final void invoke(Composer composer, int i21) {
                    TextFieldDefaults.this.TextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z2, function2, function3, function4, function5, function6, shape5, textFieldColors2, paddingValues, function7, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty |= i2;
        i3 = i & 64;
        if (i3 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) != 0) {
            if ($composer3.changed(isError)) {
                i4 = 1048576;
            } else {
                i4 = 524288;
            }
            $dirty |= i4;
        }
        i5 = i & 128;
        if (i5 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 29360128) != 0) {
            if ($composer3.changedInstance(label)) {
                i6 = 8388608;
            } else {
                i6 = 4194304;
            }
            $dirty |= i6;
        }
        i7 = i & 256;
        if (i7 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) != 0) {
            if ($composer3.changedInstance(placeholder)) {
                i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i8 = 33554432;
            }
            $dirty |= i8;
        }
        i9 = i & 512;
        if (i9 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) != 0) {
            if ($composer3.changedInstance(leadingIcon)) {
                i10 = 536870912;
            } else {
                i10 = 268435456;
            }
            $dirty |= i10;
        }
        i11 = i & 1024;
        if (i11 != 0) {
            $dirty2 |= 6;
        } else if (($changed1 & 14) != 0) {
            if ($composer3.changedInstance(trailingIcon)) {
                i12 = 4;
            } else {
                i12 = 2;
            }
            $dirty2 |= i12;
        }
        i13 = i & 2048;
        if (i13 != 0) {
            $dirty2 |= 48;
        } else if (($changed1 & 112) != 0) {
            if ($composer3.changedInstance(supportingText)) {
                i14 = 32;
            } else {
                i14 = 16;
            }
            $dirty2 |= i14;
        }
        if (($changed1 & 896) != 0) {
            if ((i & 4096) == 0) {
                i18 = 256;
            }
            $dirty2 |= i18;
        }
        if (($changed1 & 7168) != 0) {
            $dirty2 |= ((i & 8192) == 0 || !$composer3.changed(colors)) ? 1024 : 2048;
        }
        if (($changed1 & 57344) != 0) {
            $dirty2 |= ((i & 16384) == 0 || !$composer3.changed(contentPadding)) ? 8192 : 16384;
        }
        i15 = i & 32768;
        if (i15 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & 458752) != 0) {
            if ($composer3.changedInstance(container)) {
                i16 = 131072;
            } else {
                i16 = 65536;
            }
            $dirty2 |= i16;
        }
        if ((i & 65536) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed1 & 3670016) != 0) {
            if ($composer3.changed(this)) {
                i17 = 1048576;
            } else {
                i17 = 524288;
            }
            $dirty2 |= i17;
        }
        if (($dirty & 1533916891) != 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    label2 = null;
                } else {
                    label2 = label;
                }
                if (i7 != 0) {
                    placeholder2 = null;
                } else {
                    placeholder2 = placeholder;
                }
                if (i9 != 0) {
                    leadingIcon2 = null;
                } else {
                    leadingIcon2 = leadingIcon;
                }
                if (i11 != 0) {
                    trailingIcon2 = null;
                } else {
                    trailingIcon2 = trailingIcon;
                }
                if (i13 != 0) {
                    supportingText2 = null;
                } else {
                    supportingText2 = supportingText;
                }
                if ((i & 4096) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -897;
                } else {
                    shape2 = shape;
                }
                if ((i & 8192) != 0) {
                    colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 9) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -7169;
                } else {
                    colors2 = colors;
                }
                trailingIcon3 = trailingIcon2;
                if ((i & 16384) != 0) {
                    if (label2 == null) {
                        contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty2 &= -57345;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i15 != 0) {
                    final boolean z3 = isError2;
                    final TextFieldColors textFieldColors3 = colors2;
                    final Shape shape6 = shape2;
                    final int i110 = $dirty;
                    final int i21 = $dirty2;
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    leadingIcon3 = leadingIcon2;
                    container2 = ComposableLambdaKt.composableLambda($composer3, -1171460386, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.3
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
                            ComposerKt.sourceInformation($composer4, "C1271@71620L64:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1171460386, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1270)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z4 = enabled;
                            boolean z5 = z3;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors4 = textFieldColors3;
                            Shape shape7 = shape6;
                            int i22 = i110;
                            int i23 = ((i22 >> 9) & 896) | ((i22 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i22 >> 15) & 112);
                            int i24 = i21;
                            textFieldDefaults.ContainerBox(z4, z5, interactionSource2, textFieldColors4, shape7, $composer4, i23 | (i24 & 7168) | ((i24 << 6) & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    supportingText3 = supportingText2;
                    label3 = label2;
                    isError3 = isError2;
                    placeholder3 = placeholder2;
                    colors3 = colors2;
                    trailingIcon4 = trailingIcon3;
                    $dirty1 = $dirty2;
                } else {
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    container2 = container;
                    leadingIcon3 = leadingIcon2;
                    supportingText3 = supportingText2;
                    label3 = label2;
                    isError3 = isError2;
                    placeholder3 = placeholder2;
                    colors3 = colors2;
                    trailingIcon4 = trailingIcon3;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    label2 = null;
                } else {
                    label2 = label;
                }
                if (i7 != 0) {
                    placeholder2 = null;
                } else {
                    placeholder2 = placeholder;
                }
                if (i9 != 0) {
                    leadingIcon2 = null;
                } else {
                    leadingIcon2 = leadingIcon;
                }
                if (i11 != 0) {
                    trailingIcon2 = null;
                } else {
                    trailingIcon2 = trailingIcon;
                }
                if (i13 != 0) {
                    supportingText2 = null;
                } else {
                    supportingText2 = supportingText;
                }
                if ((i & 4096) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -897;
                } else {
                    shape2 = shape;
                }
                if ((i & 8192) != 0) {
                    colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 9) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -7169;
                } else {
                    colors2 = colors;
                }
                trailingIcon3 = trailingIcon2;
                if ((i & 16384) != 0) {
                    if (label2 == null) {
                        contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty2 &= -57345;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i15 != 0) {
                    final boolean z4 = isError2;
                    final TextFieldColors textFieldColors4 = colors2;
                    final Shape shape7 = shape2;
                    final int i111 = $dirty;
                    final int i22 = $dirty2;
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    leadingIcon3 = leadingIcon2;
                    container2 = ComposableLambdaKt.composableLambda($composer3, -1171460386, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.3
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
                            ComposerKt.sourceInformation($composer4, "C1271@71620L64:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1171460386, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1270)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z5 = enabled;
                            boolean z6 = z4;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors5 = textFieldColors4;
                            Shape shape8 = shape7;
                            int i23 = i111;
                            int i24 = ((i23 >> 9) & 896) | ((i23 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i23 >> 15) & 112);
                            int i25 = i22;
                            textFieldDefaults.ContainerBox(z5, z6, interactionSource2, textFieldColors5, shape8, $composer4, i24 | (i25 & 7168) | ((i25 << 6) & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    supportingText3 = supportingText2;
                    label3 = label2;
                    isError3 = isError2;
                    placeholder3 = placeholder2;
                    colors3 = colors2;
                    trailingIcon4 = trailingIcon3;
                    $dirty1 = $dirty2;
                } else {
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    container2 = container;
                    leadingIcon3 = leadingIcon2;
                    supportingText3 = supportingText2;
                    label3 = label2;
                    isError3 = isError2;
                    placeholder3 = placeholder2;
                    colors3 = colors2;
                    trailingIcon4 = trailingIcon3;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1224712461, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:1249)");
            }
            $composer2 = $composer3;
            DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, label3, placeholder3, leadingIcon3, trailingIcon4, null, null, supportingText3, shape3, colors3, contentPadding3, container2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | (1879048192 & $dirty), ($dirty1 & 14) | 432 | (($dirty1 << 6) & 7168) | (($dirty1 << 6) & 57344) | (($dirty1 << 6) & 458752) | (($dirty1 << 6) & 3670016) | (($dirty1 << 6) & 29360128) | (($dirty1 << 6) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    label2 = null;
                } else {
                    label2 = label;
                }
                if (i7 != 0) {
                    placeholder2 = null;
                } else {
                    placeholder2 = placeholder;
                }
                if (i9 != 0) {
                    leadingIcon2 = null;
                } else {
                    leadingIcon2 = leadingIcon;
                }
                if (i11 != 0) {
                    trailingIcon2 = null;
                } else {
                    trailingIcon2 = trailingIcon;
                }
                if (i13 != 0) {
                    supportingText2 = null;
                } else {
                    supportingText2 = supportingText;
                }
                if ((i & 4096) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -897;
                } else {
                    shape2 = shape;
                }
                if ((i & 8192) != 0) {
                    colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 9) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -7169;
                } else {
                    colors2 = colors;
                }
                trailingIcon3 = trailingIcon2;
                if ((i & 16384) != 0) {
                    if (label2 == null) {
                        contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty2 &= -57345;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i15 != 0) {
                    final boolean z5 = isError2;
                    final TextFieldColors textFieldColors5 = colors2;
                    final Shape shape8 = shape2;
                    final int i112 = $dirty;
                    final int i23 = $dirty2;
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    leadingIcon3 = leadingIcon2;
                    container2 = ComposableLambdaKt.composableLambda($composer3, -1171460386, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.3
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
                            ComposerKt.sourceInformation($composer4, "C1271@71620L64:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1171460386, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1270)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z6 = enabled;
                            boolean z7 = z5;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors6 = textFieldColors5;
                            Shape shape9 = shape8;
                            int i24 = i112;
                            int i25 = ((i24 >> 9) & 896) | ((i24 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i24 >> 15) & 112);
                            int i26 = i23;
                            textFieldDefaults.ContainerBox(z6, z7, interactionSource2, textFieldColors6, shape9, $composer4, i25 | (i26 & 7168) | ((i26 << 6) & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    supportingText3 = supportingText2;
                    label3 = label2;
                    isError3 = isError2;
                    placeholder3 = placeholder2;
                    colors3 = colors2;
                    trailingIcon4 = trailingIcon3;
                    $dirty1 = $dirty2;
                } else {
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    container2 = container;
                    leadingIcon3 = leadingIcon2;
                    supportingText3 = supportingText2;
                    label3 = label2;
                    isError3 = isError2;
                    placeholder3 = placeholder2;
                    colors3 = colors2;
                    trailingIcon4 = trailingIcon3;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    label2 = null;
                } else {
                    label2 = label;
                }
                if (i7 != 0) {
                    placeholder2 = null;
                } else {
                    placeholder2 = placeholder;
                }
                if (i9 != 0) {
                    leadingIcon2 = null;
                } else {
                    leadingIcon2 = leadingIcon;
                }
                if (i11 != 0) {
                    trailingIcon2 = null;
                } else {
                    trailingIcon2 = trailingIcon;
                }
                if (i13 != 0) {
                    supportingText2 = null;
                } else {
                    supportingText2 = supportingText;
                }
                if ((i & 4096) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -897;
                } else {
                    shape2 = shape;
                }
                if ((i & 8192) != 0) {
                    colors2 = m1854colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, ($dirty2 >> 9) & 7168, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -7169;
                } else {
                    colors2 = colors;
                }
                trailingIcon3 = trailingIcon2;
                if ((i & 16384) != 0) {
                    if (label2 == null) {
                        contentPadding2 = m1845contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding2 = m1844contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty2 &= -57345;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i15 != 0) {
                    final boolean z6 = isError2;
                    final TextFieldColors textFieldColors6 = colors2;
                    final Shape shape9 = shape2;
                    final int i113 = $dirty;
                    final int i24 = $dirty2;
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    leadingIcon3 = leadingIcon2;
                    container2 = ComposableLambdaKt.composableLambda($composer3, -1171460386, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.3
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
                            ComposerKt.sourceInformation($composer4, "C1271@71620L64:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1171460386, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1270)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            boolean z7 = enabled;
                            boolean z8 = z6;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors7 = textFieldColors6;
                            Shape shape10 = shape9;
                            int i25 = i113;
                            int i26 = ((i25 >> 9) & 896) | ((i25 >> 6) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ((i25 >> 15) & 112);
                            int i27 = i24;
                            textFieldDefaults.ContainerBox(z7, z8, interactionSource2, textFieldColors7, shape10, $composer4, i26 | (i27 & 7168) | ((i27 << 6) & 57344), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    supportingText3 = supportingText2;
                    label3 = label2;
                    isError3 = isError2;
                    placeholder3 = placeholder2;
                    colors3 = colors2;
                    trailingIcon4 = trailingIcon3;
                    $dirty1 = $dirty2;
                } else {
                    shape3 = shape2;
                    contentPadding3 = contentPadding2;
                    container2 = container;
                    leadingIcon3 = leadingIcon2;
                    supportingText3 = supportingText2;
                    label3 = label2;
                    isError3 = isError2;
                    placeholder3 = placeholder2;
                    colors3 = colors2;
                    trailingIcon4 = trailingIcon3;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1224712461, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:1249)");
            }
            $composer2 = $composer3;
            DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, label3, placeholder3, leadingIcon3, trailingIcon4, null, null, supportingText3, shape3, colors3, contentPadding3, container2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | ($dirty & 29360128) | ($dirty & 234881024) | (1879048192 & $dirty), ($dirty1 & 14) | 432 | (($dirty1 << 6) & 7168) | (($dirty1 << 6) & 57344) | (($dirty1 << 6) & 458752) | (($dirty1 << 6) & 3670016) | (($dirty1 << 6) & 29360128) | (($dirty1 << 6) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final boolean z7 = isError3;
        final Function2<? super Composer, ? super Integer, Unit> function8 = label3;
        final Function2<? super Composer, ? super Integer, Unit> function9 = placeholder3;
        final Function2<? super Composer, ? super Integer, Unit> function10 = leadingIcon3;
        final Function2<? super Composer, ? super Integer, Unit> function11 = trailingIcon4;
        final Function2<? super Composer, ? super Integer, Unit> function12 = supportingText3;
        final Shape shape10 = shape3;
        final TextFieldColors textFieldColors7 = colors3;
        final PaddingValues paddingValues2 = contentPadding3;
        final Function2<? super Composer, ? super Integer, Unit> function13 = container2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox.4
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

            public final void invoke(Composer composer, int i25) {
                TextFieldDefaults.this.TextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z7, function8, function9, function10, function11, function12, shape10, textFieldColors7, paddingValues2, function13, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0158  */
    /* JADX WARN: Code duplicated, block: B:102:0x015e  */
    /* JADX WARN: Code duplicated, block: B:104:0x0166  */
    /* JADX WARN: Code duplicated, block: B:105:0x0169  */
    /* JADX WARN: Code duplicated, block: B:107:0x016e  */
    /* JADX WARN: Code duplicated, block: B:110:0x0174  */
    /* JADX WARN: Code duplicated, block: B:111:0x0179  */
    /* JADX WARN: Code duplicated, block: B:113:0x017d  */
    /* JADX WARN: Code duplicated, block: B:115:0x0185  */
    /* JADX WARN: Code duplicated, block: B:116:0x0188  */
    /* JADX WARN: Code duplicated, block: B:118:0x018d  */
    /* JADX WARN: Code duplicated, block: B:121:0x0193  */
    /* JADX WARN: Code duplicated, block: B:122:0x0198  */
    /* JADX WARN: Code duplicated, block: B:124:0x019c  */
    /* JADX WARN: Code duplicated, block: B:126:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:127:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:129:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:132:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:140:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:143:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:151:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:154:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:155:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:157:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:160:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:162:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:171:0x0235  */
    /* JADX WARN: Code duplicated, block: B:173:0x023c  */
    /* JADX WARN: Code duplicated, block: B:183:0x027c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:184:0x027e  */
    /* JADX WARN: Code duplicated, block: B:185:0x0280  */
    /* JADX WARN: Code duplicated, block: B:187:0x0284  */
    /* JADX WARN: Code duplicated, block: B:189:0x0288  */
    /* JADX WARN: Code duplicated, block: B:190:0x028a  */
    /* JADX WARN: Code duplicated, block: B:192:0x028e  */
    /* JADX WARN: Code duplicated, block: B:193:0x0290  */
    /* JADX WARN: Code duplicated, block: B:195:0x0294  */
    /* JADX WARN: Code duplicated, block: B:196:0x0296  */
    /* JADX WARN: Code duplicated, block: B:198:0x029a  */
    /* JADX WARN: Code duplicated, block: B:199:0x029c  */
    /* JADX WARN: Code duplicated, block: B:202:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:203:0x0312  */
    /* JADX WARN: Code duplicated, block: B:206:0x0318  */
    /* JADX WARN: Code duplicated, block: B:207:0x033b  */
    /* JADX WARN: Code duplicated, block: B:209:0x033f  */
    /* JADX WARN: Code duplicated, block: B:210:0x0375  */
    /* JADX WARN: Code duplicated, block: B:213:0x0396  */
    /* JADX WARN: Code duplicated, block: B:216:0x041d  */
    /* JADX WARN: Code duplicated, block: B:220:0x0427  */
    /* JADX WARN: Code duplicated, block: B:222:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:67:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:71:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:72:0x0100  */
    /* JADX WARN: Code duplicated, block: B:74:0x0105  */
    /* JADX WARN: Code duplicated, block: B:77:0x010b  */
    /* JADX WARN: Code duplicated, block: B:78:0x0112  */
    /* JADX WARN: Code duplicated, block: B:80:0x0118  */
    /* JADX WARN: Code duplicated, block: B:82:0x0120  */
    /* JADX WARN: Code duplicated, block: B:83:0x0123  */
    /* JADX WARN: Code duplicated, block: B:85:0x0128  */
    /* JADX WARN: Code duplicated, block: B:88:0x012e  */
    /* JADX WARN: Code duplicated, block: B:89:0x0135  */
    /* JADX WARN: Code duplicated, block: B:91:0x013b  */
    /* JADX WARN: Code duplicated, block: B:93:0x0143  */
    /* JADX WARN: Code duplicated, block: B:94:0x0146  */
    /* JADX WARN: Code duplicated, block: B:96:0x014b  */
    /* JADX WARN: Code duplicated, block: B:99:0x0151  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use overload with prefix and suffix parameters")
    public final /* synthetic */ void OutlinedTextFieldDecorationBox(final String value, final Function2 innerTextField, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2 label, Function2 placeholder, Function2 leadingIcon, Function2 trailingIcon, Function2 supportingText, TextFieldColors colors, PaddingValues contentPadding, Function2 container, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        Function2 label2;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        boolean isError2;
        Function2 placeholder2;
        Function2 leadingIcon2;
        Function2 trailingIcon2;
        Function2 supportingText2;
        TextFieldColors colors2;
        PaddingValues contentPadding2;
        Function2 placeholder3;
        Function2 container2;
        Function2 leadingIcon3;
        Function2 trailingIcon3;
        Function2 supportingText3;
        boolean isError3;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        Function2 label3;
        int $dirty1;
        Composer $composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
        Intrinsics.checkNotNullParameter(visualTransformation, "visualTransformation");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Composer $composer3 = $composer.startRestartGroup(508645792);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextFieldDecorationBox)P(13,4,3,10,14,5,6,7,9,8,12,11!1,2)1312@73179L8,1318@73467L656:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 14) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 112) == 0) {
            $dirty |= $composer3.changedInstance(innerTextField) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 896) == 0) {
            $dirty |= $composer3.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 7168) == 0) {
            $dirty |= $composer3.changed(singleLine) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 57344) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) == 0) {
            if (($changed & 458752) == 0) {
                i2 = $composer3.changed(interactionSource) ? 131072 : 65536;
            }
            i3 = i & 64;
            if (i3 != 0) {
                $dirty |= 1572864;
            } else if (($changed & 3670016) != 0) {
                if ($composer3.changed(isError)) {
                    i4 = 1048576;
                } else {
                    i4 = 524288;
                }
                $dirty |= i4;
            }
            i5 = i & 128;
            if (i5 != 0) {
                $dirty |= 12582912;
                label2 = label;
            } else if (($changed & 29360128) == 0) {
                label2 = label;
                if ($composer3.changedInstance(label2)) {
                    i6 = 8388608;
                } else {
                    i6 = 4194304;
                }
                $dirty |= i6;
            } else {
                label2 = label;
            }
            i7 = i & 256;
            if (i7 != 0) {
                $dirty |= 100663296;
            } else if (($changed & 234881024) != 0) {
                if ($composer3.changedInstance(placeholder)) {
                    i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i8 = 33554432;
                }
                $dirty |= i8;
            }
            i9 = i & 512;
            if (i9 != 0) {
                $dirty |= 805306368;
            } else if (($changed & 1879048192) != 0) {
                if ($composer3.changedInstance(leadingIcon)) {
                    i10 = 536870912;
                } else {
                    i10 = 268435456;
                }
                $dirty |= i10;
            }
            i11 = i & 1024;
            if (i11 != 0) {
                $dirty2 |= 6;
            } else if (($changed1 & 14) != 0) {
                if ($composer3.changedInstance(trailingIcon)) {
                    i12 = 4;
                } else {
                    i12 = 2;
                }
                $dirty2 |= i12;
            }
            i13 = i & 2048;
            if (i13 != 0) {
                $dirty2 |= 48;
            } else if (($changed1 & 112) != 0) {
                if ($composer3.changedInstance(supportingText)) {
                    i14 = 32;
                } else {
                    i14 = 16;
                }
                $dirty2 |= i14;
            }
            if (($changed1 & 896) != 0) {
                $dirty2 |= ((i & 4096) == 0 || !$composer3.changed(colors)) ? 128 : 256;
            }
            if (($changed1 & 7168) != 0) {
                $dirty2 |= ((i & 8192) == 0 || !$composer3.changed(contentPadding)) ? 1024 : 2048;
            }
            i15 = i & 16384;
            if (i15 != 0) {
                $dirty2 |= 24576;
            } else if (($changed1 & 57344) != 0) {
                $dirty2 |= $composer3.changedInstance(container) ? 16384 : 8192;
            }
            if (($dirty & 1533916891) != 306783378 && (46811 & $dirty2) == 9362 && $composer3.getSkipping()) {
                $composer3.skipToGroupEnd();
                isError3 = isError;
                placeholder3 = placeholder;
                leadingIcon3 = leadingIcon;
                trailingIcon3 = trailingIcon;
                supportingText3 = supportingText;
                colors3 = colors;
                contentPadding3 = contentPadding;
                container2 = container;
                $composer2 = $composer3;
                label3 = label2;
            } else {
                $composer3.startDefaults();
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        isError2 = false;
                    } else {
                        isError2 = isError;
                    }
                    if (i5 != 0) {
                        label2 = null;
                    }
                    if (i7 != 0) {
                        placeholder2 = null;
                    } else {
                        placeholder2 = placeholder;
                    }
                    if (i9 != 0) {
                        leadingIcon2 = null;
                    } else {
                        leadingIcon2 = leadingIcon;
                    }
                    if (i11 != 0) {
                        trailingIcon2 = null;
                    } else {
                        trailingIcon2 = trailingIcon;
                    }
                    if (i13 != 0) {
                        supportingText2 = null;
                    } else {
                        supportingText2 = supportingText;
                    }
                    if ((i & 4096) != 0) {
                        colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                        $dirty2 &= -897;
                    } else {
                        colors2 = colors;
                    }
                    if ((i & 8192) != 0) {
                        contentPadding2 = OutlinedTextFieldDefaults.m1647contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        $dirty2 &= -7169;
                    } else {
                        contentPadding2 = contentPadding;
                    }
                    if (i15 != 0) {
                        final boolean z = isError2;
                        final TextFieldColors textFieldColors = colors2;
                        final int i16 = $dirty;
                        final int i17 = $dirty2;
                        placeholder3 = placeholder2;
                        container2 = ComposableLambdaKt.composableLambda($composer3, 144282315, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.3
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
                                ComposerKt.sourceInformation($composer4, "C1315@73357L57:TextFieldDefaults.kt#uh7d8r");
                                if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(144282315, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1314)");
                                }
                                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                                boolean z2 = enabled;
                                boolean z3 = z;
                                InteractionSource interactionSource2 = interactionSource;
                                TextFieldColors textFieldColors2 = textFieldColors;
                                int i18 = i16;
                                outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z2, z3, interactionSource2, textFieldColors2, null, 0.0f, 0.0f, $composer4, ((i18 >> 9) & 896) | ((i18 >> 6) & 14) | 12582912 | ((i18 >> 15) & 112) | ((i17 << 3) & 7168), 112);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        });
                        leadingIcon3 = leadingIcon2;
                        trailingIcon3 = trailingIcon2;
                        supportingText3 = supportingText2;
                        isError3 = isError2;
                        colors3 = colors2;
                        contentPadding3 = contentPadding2;
                        label3 = label2;
                        $dirty1 = $dirty2;
                    } else {
                        placeholder3 = placeholder2;
                        container2 = container;
                        leadingIcon3 = leadingIcon2;
                        trailingIcon3 = trailingIcon2;
                        supportingText3 = supportingText2;
                        isError3 = isError2;
                        colors3 = colors2;
                        contentPadding3 = contentPadding2;
                        label3 = label2;
                        $dirty1 = $dirty2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 4096) != 0) {
                        $dirty2 &= -897;
                    }
                    if ((i & 8192) != 0) {
                        isError3 = isError;
                        placeholder3 = placeholder;
                        leadingIcon3 = leadingIcon;
                        trailingIcon3 = trailingIcon;
                        supportingText3 = supportingText;
                        colors3 = colors;
                        contentPadding3 = contentPadding;
                        container2 = container;
                        $dirty1 = $dirty2 & (-7169);
                        label3 = label2;
                    } else {
                        isError3 = isError;
                        placeholder3 = placeholder;
                        leadingIcon3 = leadingIcon;
                        trailingIcon3 = trailingIcon;
                        supportingText3 = supportingText;
                        colors3 = colors;
                        contentPadding3 = contentPadding;
                        container2 = container;
                        $dirty1 = $dirty2;
                        label3 = label2;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(508645792, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:1299)");
                }
                $composer2 = $composer3;
                OutlinedTextFieldDefaults.INSTANCE.DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, label3, placeholder3, leadingIcon3, trailingIcon3, null, null, supportingText3, colors3, contentPadding3, container2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), 12583344 | ($dirty1 & 14) | (($dirty1 << 6) & 7168) | (($dirty1 << 6) & 57344) | (($dirty1 << 6) & 458752) | (($dirty1 << 6) & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                return;
            }
            final boolean z2 = isError3;
            final Function2 function2 = label3;
            final Function2 function3 = placeholder3;
            final Function2 function4 = leadingIcon3;
            final Function2 function5 = trailingIcon3;
            final Function2 function6 = supportingText3;
            final TextFieldColors textFieldColors2 = colors3;
            final PaddingValues paddingValues = contentPadding3;
            final Function2 function7 = container2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.4
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

                public final void invoke(Composer composer, int i18) {
                    TextFieldDefaults.this.OutlinedTextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z2, function2, function3, function4, function5, function6, textFieldColors2, paddingValues, function7, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty |= i2;
        i3 = i & 64;
        if (i3 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 3670016) != 0) {
            if ($composer3.changed(isError)) {
                i4 = 1048576;
            } else {
                i4 = 524288;
            }
            $dirty |= i4;
        }
        i5 = i & 128;
        if (i5 != 0) {
            $dirty |= 12582912;
            label2 = label;
        } else if (($changed & 29360128) == 0) {
            label2 = label;
            if ($composer3.changedInstance(label2)) {
                i6 = 8388608;
            } else {
                i6 = 4194304;
            }
            $dirty |= i6;
        } else {
            label2 = label;
        }
        i7 = i & 256;
        if (i7 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 234881024) != 0) {
            if ($composer3.changedInstance(placeholder)) {
                i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i8 = 33554432;
            }
            $dirty |= i8;
        }
        i9 = i & 512;
        if (i9 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 1879048192) != 0) {
            if ($composer3.changedInstance(leadingIcon)) {
                i10 = 536870912;
            } else {
                i10 = 268435456;
            }
            $dirty |= i10;
        }
        i11 = i & 1024;
        if (i11 != 0) {
            $dirty2 |= 6;
        } else if (($changed1 & 14) != 0) {
            if ($composer3.changedInstance(trailingIcon)) {
                i12 = 4;
            } else {
                i12 = 2;
            }
            $dirty2 |= i12;
        }
        i13 = i & 2048;
        if (i13 != 0) {
            $dirty2 |= 48;
        } else if (($changed1 & 112) != 0) {
            if ($composer3.changedInstance(supportingText)) {
                i14 = 32;
            } else {
                i14 = 16;
            }
            $dirty2 |= i14;
        }
        if (($changed1 & 896) != 0) {
            $dirty2 |= ((i & 4096) == 0 || !$composer3.changed(colors)) ? 128 : 256;
        }
        if (($changed1 & 7168) != 0) {
            $dirty2 |= ((i & 8192) == 0 || !$composer3.changed(contentPadding)) ? 1024 : 2048;
        }
        i15 = i & 16384;
        if (i15 != 0) {
            $dirty2 |= 24576;
        } else if (($changed1 & 57344) != 0) {
            $dirty2 |= $composer3.changedInstance(container) ? 16384 : 8192;
        }
        if (($dirty & 1533916891) != 306783378) {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    label2 = null;
                }
                if (i7 != 0) {
                    placeholder2 = null;
                } else {
                    placeholder2 = placeholder;
                }
                if (i9 != 0) {
                    leadingIcon2 = null;
                } else {
                    leadingIcon2 = leadingIcon;
                }
                if (i11 != 0) {
                    trailingIcon2 = null;
                } else {
                    trailingIcon2 = trailingIcon;
                }
                if (i13 != 0) {
                    supportingText2 = null;
                } else {
                    supportingText2 = supportingText;
                }
                if ((i & 4096) != 0) {
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -897;
                } else {
                    colors2 = colors;
                }
                if ((i & 8192) != 0) {
                    contentPadding2 = OutlinedTextFieldDefaults.m1647contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -7169;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i15 != 0) {
                    final boolean z3 = isError2;
                    final TextFieldColors textFieldColors3 = colors2;
                    final int i18 = $dirty;
                    final int i19 = $dirty2;
                    placeholder3 = placeholder2;
                    container2 = ComposableLambdaKt.composableLambda($composer3, 144282315, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.3
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
                            ComposerKt.sourceInformation($composer4, "C1315@73357L57:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(144282315, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1314)");
                            }
                            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                            boolean z4 = enabled;
                            boolean z5 = z3;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors4 = textFieldColors3;
                            int i110 = i18;
                            outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z4, z5, interactionSource2, textFieldColors4, null, 0.0f, 0.0f, $composer4, ((i110 >> 9) & 896) | ((i110 >> 6) & 14) | 12582912 | ((i110 >> 15) & 112) | ((i19 << 3) & 7168), 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    leadingIcon3 = leadingIcon2;
                    trailingIcon3 = trailingIcon2;
                    supportingText3 = supportingText2;
                    isError3 = isError2;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    label3 = label2;
                    $dirty1 = $dirty2;
                } else {
                    placeholder3 = placeholder2;
                    container2 = container;
                    leadingIcon3 = leadingIcon2;
                    trailingIcon3 = trailingIcon2;
                    supportingText3 = supportingText2;
                    isError3 = isError2;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    label3 = label2;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    label2 = null;
                }
                if (i7 != 0) {
                    placeholder2 = null;
                } else {
                    placeholder2 = placeholder;
                }
                if (i9 != 0) {
                    leadingIcon2 = null;
                } else {
                    leadingIcon2 = leadingIcon;
                }
                if (i11 != 0) {
                    trailingIcon2 = null;
                } else {
                    trailingIcon2 = trailingIcon;
                }
                if (i13 != 0) {
                    supportingText2 = null;
                } else {
                    supportingText2 = supportingText;
                }
                if ((i & 4096) != 0) {
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -897;
                } else {
                    colors2 = colors;
                }
                if ((i & 8192) != 0) {
                    contentPadding2 = OutlinedTextFieldDefaults.m1647contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -7169;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i15 != 0) {
                    final boolean z4 = isError2;
                    final TextFieldColors textFieldColors4 = colors2;
                    final int i110 = $dirty;
                    final int i111 = $dirty2;
                    placeholder3 = placeholder2;
                    container2 = ComposableLambdaKt.composableLambda($composer3, 144282315, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.3
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
                            ComposerKt.sourceInformation($composer4, "C1315@73357L57:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(144282315, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1314)");
                            }
                            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                            boolean z5 = enabled;
                            boolean z6 = z4;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors5 = textFieldColors4;
                            int i112 = i110;
                            outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z5, z6, interactionSource2, textFieldColors5, null, 0.0f, 0.0f, $composer4, ((i112 >> 9) & 896) | ((i112 >> 6) & 14) | 12582912 | ((i112 >> 15) & 112) | ((i111 << 3) & 7168), 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    leadingIcon3 = leadingIcon2;
                    trailingIcon3 = trailingIcon2;
                    supportingText3 = supportingText2;
                    isError3 = isError2;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    label3 = label2;
                    $dirty1 = $dirty2;
                } else {
                    placeholder3 = placeholder2;
                    container2 = container;
                    leadingIcon3 = leadingIcon2;
                    trailingIcon3 = trailingIcon2;
                    supportingText3 = supportingText2;
                    isError3 = isError2;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    label3 = label2;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(508645792, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:1299)");
            }
            $composer2 = $composer3;
            OutlinedTextFieldDefaults.INSTANCE.DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, label3, placeholder3, leadingIcon3, trailingIcon3, null, null, supportingText3, colors3, contentPadding3, container2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), 12583344 | ($dirty1 & 14) | (($dirty1 << 6) & 7168) | (($dirty1 << 6) & 57344) | (($dirty1 << 6) & 458752) | (($dirty1 << 6) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.startDefaults();
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    label2 = null;
                }
                if (i7 != 0) {
                    placeholder2 = null;
                } else {
                    placeholder2 = placeholder;
                }
                if (i9 != 0) {
                    leadingIcon2 = null;
                } else {
                    leadingIcon2 = leadingIcon;
                }
                if (i11 != 0) {
                    trailingIcon2 = null;
                } else {
                    trailingIcon2 = trailingIcon;
                }
                if (i13 != 0) {
                    supportingText2 = null;
                } else {
                    supportingText2 = supportingText;
                }
                if ((i & 4096) != 0) {
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -897;
                } else {
                    colors2 = colors;
                }
                if ((i & 8192) != 0) {
                    contentPadding2 = OutlinedTextFieldDefaults.m1647contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -7169;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i15 != 0) {
                    final boolean z5 = isError2;
                    final TextFieldColors textFieldColors5 = colors2;
                    final int i112 = $dirty;
                    final int i113 = $dirty2;
                    placeholder3 = placeholder2;
                    container2 = ComposableLambdaKt.composableLambda($composer3, 144282315, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.3
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
                            ComposerKt.sourceInformation($composer4, "C1315@73357L57:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(144282315, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1314)");
                            }
                            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                            boolean z6 = enabled;
                            boolean z7 = z5;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors6 = textFieldColors5;
                            int i114 = i112;
                            outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z6, z7, interactionSource2, textFieldColors6, null, 0.0f, 0.0f, $composer4, ((i114 >> 9) & 896) | ((i114 >> 6) & 14) | 12582912 | ((i114 >> 15) & 112) | ((i113 << 3) & 7168), 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    leadingIcon3 = leadingIcon2;
                    trailingIcon3 = trailingIcon2;
                    supportingText3 = supportingText2;
                    isError3 = isError2;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    label3 = label2;
                    $dirty1 = $dirty2;
                } else {
                    placeholder3 = placeholder2;
                    container2 = container;
                    leadingIcon3 = leadingIcon2;
                    trailingIcon3 = trailingIcon2;
                    supportingText3 = supportingText2;
                    isError3 = isError2;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    label3 = label2;
                    $dirty1 = $dirty2;
                }
            } else {
                if (i3 != 0) {
                    isError2 = false;
                } else {
                    isError2 = isError;
                }
                if (i5 != 0) {
                    label2 = null;
                }
                if (i7 != 0) {
                    placeholder2 = null;
                } else {
                    placeholder2 = placeholder;
                }
                if (i9 != 0) {
                    leadingIcon2 = null;
                } else {
                    leadingIcon2 = leadingIcon;
                }
                if (i11 != 0) {
                    trailingIcon2 = null;
                } else {
                    trailingIcon2 = trailingIcon;
                }
                if (i13 != 0) {
                    supportingText2 = null;
                } else {
                    supportingText2 = supportingText;
                }
                if ((i & 4096) != 0) {
                    colors2 = OutlinedTextFieldDefaults.INSTANCE.m1649colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, 0, 0, 3072, Integer.MAX_VALUE, 4095);
                    $dirty2 &= -897;
                } else {
                    colors2 = colors;
                }
                if ((i & 8192) != 0) {
                    contentPadding2 = OutlinedTextFieldDefaults.m1647contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -7169;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i15 != 0) {
                    final boolean z6 = isError2;
                    final TextFieldColors textFieldColors6 = colors2;
                    final int i114 = $dirty;
                    final int i115 = $dirty2;
                    placeholder3 = placeholder2;
                    container2 = ComposableLambdaKt.composableLambda($composer3, 144282315, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.3
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
                            ComposerKt.sourceInformation($composer4, "C1315@73357L57:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 11) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(144282315, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:1314)");
                            }
                            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                            boolean z7 = enabled;
                            boolean z8 = z6;
                            InteractionSource interactionSource2 = interactionSource;
                            TextFieldColors textFieldColors7 = textFieldColors6;
                            int i116 = i114;
                            outlinedTextFieldDefaults.m1648ContainerBoxnbWgWpA(z7, z8, interactionSource2, textFieldColors7, null, 0.0f, 0.0f, $composer4, ((i116 >> 9) & 896) | ((i116 >> 6) & 14) | 12582912 | ((i116 >> 15) & 112) | ((i115 << 3) & 7168), 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    leadingIcon3 = leadingIcon2;
                    trailingIcon3 = trailingIcon2;
                    supportingText3 = supportingText2;
                    isError3 = isError2;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    label3 = label2;
                    $dirty1 = $dirty2;
                } else {
                    placeholder3 = placeholder2;
                    container2 = container;
                    leadingIcon3 = leadingIcon2;
                    trailingIcon3 = trailingIcon2;
                    supportingText3 = supportingText2;
                    isError3 = isError2;
                    colors3 = colors2;
                    contentPadding3 = contentPadding2;
                    label3 = label2;
                    $dirty1 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(508645792, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:1299)");
            }
            $composer2 = $composer3;
            OutlinedTextFieldDefaults.INSTANCE.DecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError3, label3, placeholder3, leadingIcon3, trailingIcon3, null, null, supportingText3, colors3, contentPadding3, container2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | ($dirty & 3670016) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), 12583344 | ($dirty1 & 14) | (($dirty1 << 6) & 7168) | (($dirty1 << 6) & 57344) | (($dirty1 << 6) & 458752) | (($dirty1 << 6) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final boolean z7 = isError3;
        final Function2<? super Composer, ? super Integer, Unit> function8 = label3;
        final Function2<? super Composer, ? super Integer, Unit> function9 = placeholder3;
        final Function2<? super Composer, ? super Integer, Unit> function10 = leadingIcon3;
        final Function2<? super Composer, ? super Integer, Unit> function11 = trailingIcon3;
        final Function2<? super Composer, ? super Integer, Unit> function12 = supportingText3;
        final TextFieldColors textFieldColors7 = colors3;
        final PaddingValues paddingValues2 = contentPadding3;
        final Function2<? super Composer, ? super Integer, Unit> function13 = container2;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox.4
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

            public final void invoke(Composer composer, int i116) {
                TextFieldDefaults.this.OutlinedTextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z7, function8, function9, function10, function11, function12, textFieldColors7, paddingValues2, function13, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
            }
        });
    }
}
