package androidx.compose.ui.platform;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.SpannableString;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.autofill.AutofillId;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import androidx.collection.SparseArrayCompat;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.R;
import androidx.compose.ui.TempListUtilsKt;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.accessibility.CollectionInfoKt;
import androidx.compose.ui.platform.coreshims.AutofillIdCompat;
import androidx.compose.ui.platform.coreshims.ContentCaptureSessionCompat;
import androidx.compose.ui.platform.coreshims.ViewCompatShims;
import androidx.compose.ui.platform.coreshims.ViewStructureCompat;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesAndroid;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.AndroidAccessibilitySpannableString_androidKt;
import androidx.compose.ui.text.platform.URLSpanCache;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import androidx.core.app.NotificationCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000â\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 \u008b\u00022\u00020\u0001:\u000e\u0088\u0002\u0089\u0002\u008a\u0002\u008b\u0002\u008c\u0002\u008d\u0002\u008e\u0002B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J-\u0010|\u001a\u00020\u001e2\u0006\u0010}\u001a\u00020\f2\u0006\u0010~\u001a\u00020\u007f2\u0007\u0010\u0080\u0001\u001a\u00020\u00062\n\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0082\u0001H\u0002J\u0013\u0010\u0083\u0001\u001a\u00020\u001eH\u0086@ø\u0001\u0000¢\u0006\u0003\u0010\u0084\u0001J\u001d\u0010\u0085\u0001\u001a\u00020\u001e2\u0007\u0010\u0086\u0001\u001a\u00020\f2\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010!H\u0002J\u0012\u0010\u0088\u0001\u001a\u00020\u001e2\u0007\u0010\u0086\u0001\u001a\u00020\fH\u0002J4\u0010\u0089\u0001\u001a\u00020\u000e2\u0007\u0010\u008a\u0001\u001a\u00020\u000e2\u0007\u0010\u008b\u0001\u001a\u00020\f2\b\u0010\u008c\u0001\u001a\u00030\u008d\u0001H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001JC\u0010\u0089\u0001\u001a\u00020\u000e2\r\u00102\u001a\t\u0012\u0004\u0012\u0002040\u0090\u00012\u0007\u0010\u008a\u0001\u001a\u00020\u000e2\u0007\u0010\u008b\u0001\u001a\u00020\f2\b\u0010\u008c\u0001\u001a\u00030\u008d\u0001H\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001J\t\u0010\u0093\u0001\u001a\u00020\u001eH\u0002J\u0011\u0010\u0094\u0001\u001a\u00020\u000e2\u0006\u0010}\u001a\u00020\fH\u0002J!\u0010\u0095\u0001\u001a\u00030\u0096\u00012\u0006\u0010}\u001a\u00020\f2\u0007\u0010\u0097\u0001\u001a\u00020\fH\u0001¢\u0006\u0003\b\u0098\u0001J\u0013\u0010\u0099\u0001\u001a\u0004\u0018\u00010\u007f2\u0006\u0010}\u001a\u00020\fH\u0002JD\u0010\u009a\u0001\u001a\u00030\u0096\u00012\u0006\u0010}\u001a\u00020\f2\t\u0010\u009b\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u009d\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0003\u0010\u009f\u0001J\u0011\u0010 \u0001\u001a\u00020\u000e2\b\u0010¡\u0001\u001a\u00030¢\u0001J\u0013\u0010£\u0001\u001a\u00020^2\b\u0010¤\u0001\u001a\u00030¥\u0001H\u0016J\u0013\u0010¦\u0001\u001a\u00020\f2\b\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\u0013\u0010©\u0001\u001a\u00020\f2\b\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\u0013\u0010ª\u0001\u001a\u00020\u000e2\b\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\u0015\u0010«\u0001\u001a\u0004\u0018\u00010\u00062\b\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\u0016\u0010¬\u0001\u001a\u0005\u0018\u00010\u00ad\u00012\b\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\u0017\u0010®\u0001\u001a\u0004\u0018\u00010\u00062\n\u0010§\u0001\u001a\u0005\u0018\u00010¨\u0001H\u0002J!\u0010¯\u0001\u001a\u0005\u0018\u00010°\u00012\n\u0010§\u0001\u001a\u0005\u0018\u00010¨\u00012\u0007\u0010±\u0001\u001a\u00020\fH\u0002J#\u0010²\u0001\u001a\u00020\f2\b\u0010³\u0001\u001a\u00030´\u00012\b\u0010µ\u0001\u001a\u00030´\u0001H\u0001¢\u0006\u0003\b¶\u0001J\u0011\u0010·\u0001\u001a\u00020\u000e2\u0006\u0010}\u001a\u00020\fH\u0002J\u0013\u0010¸\u0001\u001a\u00020\u000e2\b\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\u0013\u0010¹\u0001\u001a\u00020\u000e2\b\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\t\u0010º\u0001\u001a\u00020\u001eH\u0002J\u0012\u0010»\u0001\u001a\u00020\u001e2\u0007\u0010¼\u0001\u001a\u00020rH\u0002J\u0013\u0010½\u0001\u001a\u00020\u001e2\b\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\u0018\u0010¾\u0001\u001a\u00020\u001e2\u0007\u0010¼\u0001\u001a\u00020rH\u0000¢\u0006\u0003\b¿\u0001J\u000f\u0010À\u0001\u001a\u00020\u001eH\u0000¢\u0006\u0003\bÁ\u0001J&\u0010Â\u0001\u001a\u00020\u000e2\u0006\u0010}\u001a\u00020\f2\u0007\u0010Ã\u0001\u001a\u00020\f2\n\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0082\u0001H\u0002J$\u0010Ä\u0001\u001a\u00020\u001e2\u0006\u0010}\u001a\u00020\f2\u0007\u0010~\u001a\u00030Å\u00012\b\u0010Æ\u0001\u001a\u00030¨\u0001H\u0007J!\u0010Ç\u0001\u001a\u00020\u000e2\u0007\u0010È\u0001\u001a\u00020\f2\r\u0010É\u0001\u001a\b\u0012\u0004\u0012\u00020l0>H\u0002J\u0011\u0010Ê\u0001\u001a\u00020\u000e2\u0006\u0010}\u001a\u00020\fH\u0002J&\u0010Ë\u0001\u001a\u0016\u0012\u0005\u0012\u00030¨\u00010Ì\u0001j\n\u0012\u0005\u0012\u00030¨\u0001`Í\u00012\u0007\u0010Î\u0001\u001a\u00020\u000eH\u0002J\u0012\u0010Ï\u0001\u001a\u00020\f2\u0007\u0010È\u0001\u001a\u00020\fH\u0002J\u001c\u0010Ð\u0001\u001a\u00020\u001e2\b\u0010Ñ\u0001\u001a\u00030¨\u00012\u0007\u0010Ò\u0001\u001a\u00020dH\u0002J\"\u0010Ó\u0001\u001a\u00020\u001e2\b\u0010Ñ\u0001\u001a\u00030¨\u00012\u0007\u0010Ò\u0001\u001a\u00020dH\u0001¢\u0006\u0003\bÔ\u0001J\u001b\u0010Õ\u0001\u001a\u00020\u001e2\u0007\u0010È\u0001\u001a\u00020\f2\u0007\u0010Ö\u0001\u001a\u00020\u0006H\u0002J\u0013\u0010×\u0001\u001a\u00020\u000e2\b\u0010¡\u0001\u001a\u00030\u0096\u0001H\u0002J@\u0010Ø\u0001\u001a\u00020\u000e2\u0006\u0010}\u001a\u00020\f2\u0007\u0010\u0097\u0001\u001a\u00020\f2\u000b\b\u0002\u0010Ù\u0001\u001a\u0004\u0018\u00010\f2\u0011\b\u0002\u0010Ú\u0001\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010>H\u0002¢\u0006\u0003\u0010Û\u0001J&\u0010Ü\u0001\u001a\u00020\u001e2\u0007\u0010Ý\u0001\u001a\u00020\f2\u0007\u0010Ù\u0001\u001a\u00020\f2\t\u0010Þ\u0001\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010ß\u0001\u001a\u00020\u001e2\u0007\u0010Ý\u0001\u001a\u00020\fH\u0002J\u0012\u0010à\u0001\u001a\u00020\u001e2\u0007\u0010á\u0001\u001a\u00020lH\u0002J$\u0010â\u0001\u001a\u00020\u001e2\u0013\u0010ã\u0001\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020403H\u0001¢\u0006\u0003\bä\u0001J!\u0010å\u0001\u001a\u00020\u001e2\u0007\u0010¼\u0001\u001a\u00020r2\r\u0010æ\u0001\u001a\b\u0012\u0004\u0012\u00020\f0%H\u0002J.\u0010ç\u0001\u001a\u00020\u000e2\b\u0010§\u0001\u001a\u00030¨\u00012\u0007\u0010è\u0001\u001a\u00020\f2\u0007\u0010é\u0001\u001a\u00020\f2\u0007\u0010ê\u0001\u001a\u00020\u000eH\u0002J\u001c\u0010ë\u0001\u001a\u00020\u001e2\b\u0010§\u0001\u001a\u00030¨\u00012\u0007\u0010~\u001a\u00030Å\u0001H\u0002J\u001c\u0010ì\u0001\u001a\u00020\u001e2\b\u0010§\u0001\u001a\u00030¨\u00012\u0007\u0010~\u001a\u00030Å\u0001H\u0002J\u001c\u0010í\u0001\u001a\u00020\u001e2\b\u0010§\u0001\u001a\u00030¨\u00012\u0007\u0010~\u001a\u00030Å\u0001H\u0002J\u001c\u0010î\u0001\u001a\u00020\u001e2\b\u0010§\u0001\u001a\u00030¨\u00012\u0007\u0010~\u001a\u00030Å\u0001H\u0002J\t\u0010ï\u0001\u001a\u00020\u001eH\u0002JG\u0010ð\u0001\u001a\t\u0012\u0005\u0012\u00030¨\u00010;2\u0007\u0010Î\u0001\u001a\u00020\u000e2\u000e\u0010ñ\u0001\u001a\t\u0012\u0005\u0012\u00030¨\u00010;2\u001c\b\u0002\u0010ò\u0001\u001a\u0015\u0012\u0004\u0012\u00020\f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030¨\u00010;0cH\u0002J)\u0010ó\u0001\u001a\t\u0012\u0005\u0012\u00030¨\u00010;2\u0007\u0010Î\u0001\u001a\u00020\u000e2\u000e\u0010ô\u0001\u001a\t\u0012\u0005\u0012\u00030¨\u00010;H\u0002J\"\u0010õ\u0001\u001a\u0005\u0018\u00010ö\u00012\n\u0010÷\u0001\u001a\u0005\u0018\u00010¨\u00012\b\u0010ø\u0001\u001a\u00030ù\u0001H\u0002J.\u0010ú\u0001\u001a\u00020\u000e2\b\u0010§\u0001\u001a\u00030¨\u00012\u0007\u0010±\u0001\u001a\u00020\f2\u0007\u0010û\u0001\u001a\u00020\u000e2\u0007\u0010ü\u0001\u001a\u00020\u000eH\u0002J4\u0010ý\u0001\u001a\u0005\u0018\u0001Hþ\u0001\"\t\b\u0000\u0010þ\u0001*\u00020\u001b2\n\u0010\u009e\u0001\u001a\u0005\u0018\u0001Hþ\u00012\t\b\u0001\u0010ÿ\u0001\u001a\u00020\fH\u0002¢\u0006\u0003\u0010\u0080\u0002J\u0011\u0010\u0081\u0002\u001a\u00020\u001e2\u0006\u0010}\u001a\u00020\fH\u0002J\t\u0010\u0082\u0002\u001a\u00020\u001eH\u0002J\u0010\u0010\u0083\u0002\u001a\u0004\u0018\u00010-*\u00030¥\u0001H\u0002J\u0011\u0010\u0084\u0002\u001a\u0005\u0018\u00010\u0085\u0002*\u00030\u0086\u0002H\u0002J\u0010\u0010\u0087\u0002\u001a\u0004\u0018\u00010!*\u00030¨\u0001H\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0006X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u00020\u00148\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020!0 X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020\f0%X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010)\u001a\u00020\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0010\"\u0004\b+\u0010\u0012R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R(\u00102\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u000204038@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u000e\u00109\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010:\u001a&\u0012\f\u0012\n =*\u0004\u0018\u00010<0< =*\u0012\u0012\f\u0012\n =*\u0004\u0018\u00010<0<\u0018\u00010>0;X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010?\u001a\u00020@8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bA\u0010\u0016\u001a\u0004\bB\u0010CR\u000e\u0010D\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020FX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010G\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR6\u0010L\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0Mj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f`NX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR6\u0010S\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0Mj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f`NX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010P\"\u0004\bU\u0010RR\u001a\u0010V\u001a\u00020\u000e8@X\u0081\u0004¢\u0006\f\u0012\u0004\bW\u0010\u0016\u001a\u0004\bX\u0010\u0010R\u0014\u0010Y\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bY\u0010\u0010R\u0014\u0010Z\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010\u0010R\u0014\u0010[\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\u0010R \u0010\\\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\f030\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020^X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010_\u001a\b\u0012\u0004\u0012\u00020\f0%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010`\u001a\u0004\u0018\u00010aX\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020d0c8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\be\u0010\u0016\u001a\u0004\bf\u00106\"\u0004\bg\u00108R\u000e\u0010h\u001a\u00020dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010i\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010jR\u0014\u0010k\u001a\b\u0012\u0004\u0012\u00020l0;X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010o\u001a\u000e\u0012\u0004\u0012\u00020l\u0012\u0004\u0012\u00020\u001e0pX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010q\u001a\b\u0012\u0004\u0012\u00020r0%X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010s\u001a\u00020t8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bu\u0010\u0016\u001a\u0004\bv\u0010wR\u000e\u0010x\u001a\u00020yX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bz\u0010{\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u008f\u0002"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;", "Landroidx/core/view/AccessibilityDelegateCompat;", "view", "Landroidx/compose/ui/platform/AndroidComposeView;", "(Landroidx/compose/ui/platform/AndroidComposeView;)V", "EXTRA_DATA_TEST_TRAVERSALAFTER_VAL", "", "getEXTRA_DATA_TEST_TRAVERSALAFTER_VAL$ui_release", "()Ljava/lang/String;", "EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL", "getEXTRA_DATA_TEST_TRAVERSALBEFORE_VAL$ui_release", "accessibilityCursorPosition", "", "accessibilityForceEnabledForTesting", "", "getAccessibilityForceEnabledForTesting$ui_release", "()Z", "setAccessibilityForceEnabledForTesting$ui_release", "(Z)V", "accessibilityManager", "Landroid/view/accessibility/AccessibilityManager;", "getAccessibilityManager$ui_release$annotations", "()V", "getAccessibilityManager$ui_release", "()Landroid/view/accessibility/AccessibilityManager;", "actionIdToLabel", "Landroidx/collection/SparseArrayCompat;", "", "boundsUpdateChannel", "Lkotlinx/coroutines/channels/Channel;", "", "bufferedContentCaptureAppearedNodes", "Landroidx/collection/ArrayMap;", "Landroidx/compose/ui/platform/coreshims/ViewStructureCompat;", "getBufferedContentCaptureAppearedNodes$ui_release", "()Landroidx/collection/ArrayMap;", "bufferedContentCaptureDisappearedNodes", "Landroidx/collection/ArraySet;", "getBufferedContentCaptureDisappearedNodes$ui_release", "()Landroidx/collection/ArraySet;", "checkingForSemanticsChanges", "contentCaptureForceEnabledForTesting", "getContentCaptureForceEnabledForTesting$ui_release", "setContentCaptureForceEnabledForTesting$ui_release", "contentCaptureSession", "Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;", "getContentCaptureSession$ui_release", "()Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;", "setContentCaptureSession$ui_release", "(Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;)V", "currentSemanticsNodes", "", "Landroidx/compose/ui/platform/SemanticsNodeWithAdjustedBounds;", "getCurrentSemanticsNodes$ui_release", "()Ljava/util/Map;", "setCurrentSemanticsNodes$ui_release", "(Ljava/util/Map;)V", "currentSemanticsNodesInvalidated", "enabledServices", "", "Landroid/accessibilityservice/AccessibilityServiceInfo;", "kotlin.jvm.PlatformType", "", "enabledStateListener", "Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "getEnabledStateListener$ui_release$annotations", "getEnabledStateListener$ui_release", "()Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "focusedVirtualViewId", "handler", "Landroid/os/Handler;", "hoveredVirtualViewId", "getHoveredVirtualViewId$ui_release", "()I", "setHoveredVirtualViewId$ui_release", "(I)V", "idToAfterMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getIdToAfterMap$ui_release", "()Ljava/util/HashMap;", "setIdToAfterMap$ui_release", "(Ljava/util/HashMap;)V", "idToBeforeMap", "getIdToBeforeMap$ui_release", "setIdToBeforeMap$ui_release", "isEnabled", "isEnabled$ui_release$annotations", "isEnabled$ui_release", "isEnabledForAccessibility", "isEnabledForContentCapture", "isTouchExplorationEnabled", "labelToActionId", "nodeProvider", "Landroidx/core/view/accessibility/AccessibilityNodeProviderCompat;", "paneDisplayed", "pendingTextTraversedEvent", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent;", "previousSemanticsNodes", "", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy;", "getPreviousSemanticsNodes$ui_release$annotations", "getPreviousSemanticsNodes$ui_release", "setPreviousSemanticsNodes$ui_release", "previousSemanticsRoot", "previousTraversedNode", "Ljava/lang/Integer;", "scrollObservationScopes", "Landroidx/compose/ui/platform/ScrollObservationScope;", "semanticsChangeChecker", "Ljava/lang/Runnable;", "sendScrollEventIfNeededLambda", "Lkotlin/Function1;", "subtreeChangedLayoutNodes", "Landroidx/compose/ui/node/LayoutNode;", "touchExplorationStateListener", "Landroid/view/accessibility/AccessibilityManager$TouchExplorationStateChangeListener;", "getTouchExplorationStateListener$ui_release$annotations", "getTouchExplorationStateListener$ui_release", "()Landroid/view/accessibility/AccessibilityManager$TouchExplorationStateChangeListener;", "urlSpanCache", "Landroidx/compose/ui/text/platform/URLSpanCache;", "getView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "addExtraDataToAccessibilityNodeInfoHelper", "virtualViewId", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "extraDataKey", "arguments", "Landroid/os/Bundle;", "boundsUpdatesEventLoop", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bufferContentCaptureViewAppeared", "virtualId", "viewStructure", "bufferContentCaptureViewDisappeared", "canScroll", "vertical", "direction", "position", "Landroidx/compose/ui/geometry/Offset;", "canScroll-0AR0LA0$ui_release", "(ZIJ)Z", "", "canScroll-moWRBKg$ui_release", "(Ljava/util/Collection;ZIJ)Z", "checkForSemanticsChanges", "clearAccessibilityFocus", "createEvent", "Landroid/view/accessibility/AccessibilityEvent;", "eventType", "createEvent$ui_release", "createNodeInfo", "createTextSelectionChangedEvent", "fromIndex", "toIndex", "itemCount", "text", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/CharSequence;)Landroid/view/accessibility/AccessibilityEvent;", "dispatchHoverEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "getAccessibilityNodeProvider", "host", "Landroid/view/View;", "getAccessibilitySelectionEnd", "node", "Landroidx/compose/ui/semantics/SemanticsNode;", "getAccessibilitySelectionStart", "getInfoIsCheckable", "getInfoStateDescriptionOrNull", "getInfoText", "Landroid/text/SpannableString;", "getIterableTextForAccessibility", "getIteratorForGranularity", "Landroidx/compose/ui/platform/AccessibilityIterators$TextSegmentIterator;", "granularity", "hitTestSemanticsAt", "x", "", "y", "hitTestSemanticsAt$ui_release", "isAccessibilityFocused", "isAccessibilitySelectionExtendable", "isScreenReaderFocusable", "notifyContentCaptureChanges", "notifySubtreeAccessibilityStateChangedIfNeeded", "layoutNode", "notifySubtreeAppeared", "onLayoutChange", "onLayoutChange$ui_release", "onSemanticsChange", "onSemanticsChange$ui_release", "performActionHelper", "action", "populateAccessibilityNodeInfoProperties", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "semanticsNode", "registerScrollingId", "id", "oldScrollObservationScopes", "requestAccessibilityFocus", "semanticComparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "layoutIsRtl", "semanticsNodeIdToAccessibilityVirtualNodeId", "sendAccessibilitySemanticsStructureChangeEvents", "newNode", "oldNode", "sendContentCaptureSemanticsStructureChangeEvents", "sendContentCaptureSemanticsStructureChangeEvents$ui_release", "sendContentCaptureTextUpdateEvent", "newText", "sendEvent", "sendEventForVirtualView", "contentChangeType", "contentDescription", "(IILjava/lang/Integer;Ljava/util/List;)Z", "sendPaneChangeEvents", "semanticsNodeId", "title", "sendPendingTextTraversedAtGranularityEvent", "sendScrollEventIfNeeded", "scrollObservationScope", "sendSemanticsPropertyChangeEvents", "newSemanticsNodes", "sendSemanticsPropertyChangeEvents$ui_release", "sendSubtreeChangeAccessibilityEvents", "subtreeChangedSemanticsNodesIds", "setAccessibilitySelection", "start", "end", "traversalMode", "setContentInvalid", "setIsCheckable", "setStateDescription", "setText", "setTraversalValues", "sortByGeometryGroupings", "parentListToSort", "containerChildrenMapping", "subtreeSortedByGeometryGrouping", "listToSort", "toScreenCoords", "Landroid/graphics/RectF;", "textNode", "bounds", "Landroidx/compose/ui/geometry/Rect;", "traverseAtGranularity", "forward", "extendSelection", "trimToSize", "T", "size", "(Ljava/lang/CharSequence;I)Ljava/lang/CharSequence;", "updateHoveredVirtualView", "updateSemanticsNodesCopyAndPanes", "getContentCaptureSessionCompat", "getTextForTextField", "Landroidx/compose/ui/text/AnnotatedString;", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "toViewStructure", "Api24Impl", "Api28Impl", "Api29Impl", "Companion", "MyNodeProvider", "PendingTextTraversedEvent", "SemanticsNodeCopy", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidComposeViewAccessibilityDelegateCompat extends AccessibilityDelegateCompat {
    public static final int AccessibilityCursorPositionUndefined = -1;
    public static final int AccessibilitySliderStepsCount = 20;
    public static final String ClassName = "android.view.View";
    public static final String ExtraDataIdKey = "androidx.compose.ui.semantics.id";
    public static final String ExtraDataTestTagKey = "androidx.compose.ui.semantics.testTag";
    public static final int InvalidId = Integer.MIN_VALUE;
    public static final String LogTag = "AccessibilityDelegate";
    public static final int ParcelSafeTextLength = 100000;
    public static final long SendRecurringAccessibilityEventsIntervalMillis = 100;
    public static final String TextClassName = "android.widget.TextView";
    public static final String TextFieldClassName = "android.widget.EditText";
    public static final long TextTraversedEventTimeoutMillis = 1000;
    private final String EXTRA_DATA_TEST_TRAVERSALAFTER_VAL;
    private final String EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL;
    private int accessibilityCursorPosition;
    private boolean accessibilityForceEnabledForTesting;
    private final android.view.accessibility.AccessibilityManager accessibilityManager;
    private SparseArrayCompat<SparseArrayCompat<CharSequence>> actionIdToLabel;
    private final Channel<Unit> boundsUpdateChannel;
    private final ArrayMap<Integer, ViewStructureCompat> bufferedContentCaptureAppearedNodes;
    private final ArraySet<Integer> bufferedContentCaptureDisappearedNodes;
    private boolean checkingForSemanticsChanges;
    private boolean contentCaptureForceEnabledForTesting;
    private ContentCaptureSessionCompat contentCaptureSession;
    private Map<Integer, SemanticsNodeWithAdjustedBounds> currentSemanticsNodes;
    private boolean currentSemanticsNodesInvalidated;
    private List<AccessibilityServiceInfo> enabledServices;
    private final android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener enabledStateListener;
    private int focusedVirtualViewId;
    private final Handler handler;
    private int hoveredVirtualViewId;
    private HashMap<Integer, Integer> idToAfterMap;
    private HashMap<Integer, Integer> idToBeforeMap;
    private SparseArrayCompat<Map<CharSequence, Integer>> labelToActionId;
    private AccessibilityNodeProviderCompat nodeProvider;
    private ArraySet<Integer> paneDisplayed;
    private PendingTextTraversedEvent pendingTextTraversedEvent;
    private Map<Integer, SemanticsNodeCopy> previousSemanticsNodes;
    private SemanticsNodeCopy previousSemanticsRoot;
    private Integer previousTraversedNode;
    private final List<ScrollObservationScope> scrollObservationScopes;
    private final Runnable semanticsChangeChecker;
    private final Function1<ScrollObservationScope, Unit> sendScrollEventIfNeededLambda;
    private final ArraySet<LayoutNode> subtreeChangedLayoutNodes;
    private final android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateListener;
    private final URLSpanCache urlSpanCache;
    private final AndroidComposeView view;
    private static final int[] AccessibilityActionsResourceIds = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};

    /* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToggleableState.values().length];
            try {
                iArr[ToggleableState.On.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ToggleableState.Off.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ToggleableState.Indeterminate.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$boundsUpdatesEventLoop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat", f = "AndroidComposeViewAccessibilityDelegateCompat.android.kt", i = {0, 0, 1, 1}, l = {2177, 2210}, m = "boundsUpdatesEventLoop", n = {"this", "subtreeChangedSemanticsNodesIds", "this", "subtreeChangedSemanticsNodesIds"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C05211 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C05211(Continuation<? super C05211> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidComposeViewAccessibilityDelegateCompat.this.boundsUpdatesEventLoop(this);
        }
    }

    public static /* synthetic */ void getAccessibilityManager$ui_release$annotations() {
    }

    public static /* synthetic */ void getEnabledStateListener$ui_release$annotations() {
    }

    public static /* synthetic */ void getPreviousSemanticsNodes$ui_release$annotations() {
    }

    public static /* synthetic */ void getTouchExplorationStateListener$ui_release$annotations() {
    }

    public static /* synthetic */ void isEnabled$ui_release$annotations() {
    }

    public final AndroidComposeView getView() {
        return this.view;
    }

    public AndroidComposeViewAccessibilityDelegateCompat(AndroidComposeView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
        this.hoveredVirtualViewId = Integer.MIN_VALUE;
        Object systemService = view.getContext().getSystemService("accessibility");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        android.view.accessibility.AccessibilityManager accessibilityManager = (android.view.accessibility.AccessibilityManager) systemService;
        this.accessibilityManager = accessibilityManager;
        this.enabledStateListener = new android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda0
            @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
            public final void onAccessibilityStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat.enabledStateListener$lambda$0(this.f$0, z);
            }
        };
        this.touchExplorationStateListener = new android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1
            @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat.touchExplorationStateListener$lambda$1(this.f$0, z);
            }
        };
        this.enabledServices = accessibilityManager.getEnabledAccessibilityServiceList(-1);
        this.handler = new Handler(Looper.getMainLooper());
        this.nodeProvider = new AccessibilityNodeProviderCompat(new MyNodeProvider());
        this.focusedVirtualViewId = Integer.MIN_VALUE;
        this.actionIdToLabel = new SparseArrayCompat<>();
        this.labelToActionId = new SparseArrayCompat<>();
        this.accessibilityCursorPosition = -1;
        this.subtreeChangedLayoutNodes = new ArraySet<>();
        this.boundsUpdateChannel = ChannelKt.Channel$default(-1, null, null, 6, null);
        this.currentSemanticsNodesInvalidated = true;
        this.bufferedContentCaptureAppearedNodes = new ArrayMap<>();
        this.bufferedContentCaptureDisappearedNodes = new ArraySet<>();
        this.currentSemanticsNodes = MapsKt.emptyMap();
        this.paneDisplayed = new ArraySet<>();
        this.idToBeforeMap = new HashMap<>();
        this.idToAfterMap = new HashMap<>();
        this.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL";
        this.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL";
        this.urlSpanCache = new URLSpanCache();
        this.previousSemanticsNodes = new LinkedHashMap();
        this.previousSemanticsRoot = new SemanticsNodeCopy(view.getSemanticsOwner().getUnmergedRootSemanticsNode(), MapsKt.emptyMap());
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view2) {
                Intrinsics.checkNotNullParameter(view2, "view");
                AndroidComposeViewAccessibilityDelegateCompat.this.getAccessibilityManager().addAccessibilityStateChangeListener(AndroidComposeViewAccessibilityDelegateCompat.this.getEnabledStateListener());
                AndroidComposeViewAccessibilityDelegateCompat.this.getAccessibilityManager().addTouchExplorationStateChangeListener(AndroidComposeViewAccessibilityDelegateCompat.this.getTouchExplorationStateListener());
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                androidComposeViewAccessibilityDelegateCompat.setContentCaptureSession$ui_release(androidComposeViewAccessibilityDelegateCompat.getContentCaptureSessionCompat(view2));
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view2) {
                Intrinsics.checkNotNullParameter(view2, "view");
                AndroidComposeViewAccessibilityDelegateCompat.this.handler.removeCallbacks(AndroidComposeViewAccessibilityDelegateCompat.this.semanticsChangeChecker);
                AndroidComposeViewAccessibilityDelegateCompat.this.getAccessibilityManager().removeAccessibilityStateChangeListener(AndroidComposeViewAccessibilityDelegateCompat.this.getEnabledStateListener());
                AndroidComposeViewAccessibilityDelegateCompat.this.getAccessibilityManager().removeTouchExplorationStateChangeListener(AndroidComposeViewAccessibilityDelegateCompat.this.getTouchExplorationStateListener());
                AndroidComposeViewAccessibilityDelegateCompat.this.setContentCaptureSession$ui_release(null);
            }
        });
        this.semanticsChangeChecker = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeViewAccessibilityDelegateCompat.semanticsChangeChecker$lambda$45(this.f$0);
            }
        };
        this.scrollObservationScopes = new ArrayList();
        this.sendScrollEventIfNeededLambda = new Function1<ScrollObservationScope, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendScrollEventIfNeededLambda$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ScrollObservationScope scrollObservationScope) {
                invoke2(scrollObservationScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ScrollObservationScope it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.sendScrollEventIfNeeded(it);
            }
        };
    }

    /* JADX INFO: renamed from: getHoveredVirtualViewId$ui_release, reason: from getter */
    public final int getHoveredVirtualViewId() {
        return this.hoveredVirtualViewId;
    }

    public final void setHoveredVirtualViewId$ui_release(int i) {
        this.hoveredVirtualViewId = i;
    }

    /* JADX INFO: renamed from: getAccessibilityManager$ui_release, reason: from getter */
    public final android.view.accessibility.AccessibilityManager getAccessibilityManager() {
        return this.accessibilityManager;
    }

    /* JADX INFO: renamed from: getAccessibilityForceEnabledForTesting$ui_release, reason: from getter */
    public final boolean getAccessibilityForceEnabledForTesting() {
        return this.accessibilityForceEnabledForTesting;
    }

    public final void setAccessibilityForceEnabledForTesting$ui_release(boolean z) {
        this.accessibilityForceEnabledForTesting = z;
    }

    /* JADX INFO: renamed from: getEnabledStateListener$ui_release, reason: from getter */
    public final android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener getEnabledStateListener() {
        return this.enabledStateListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enabledStateListener$lambda$0(AndroidComposeViewAccessibilityDelegateCompat this$0, boolean enabled) {
        List<AccessibilityServiceInfo> listEmptyList;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (enabled) {
            listEmptyList = this$0.accessibilityManager.getEnabledAccessibilityServiceList(-1);
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        this$0.enabledServices = listEmptyList;
    }

    /* JADX INFO: renamed from: getTouchExplorationStateListener$ui_release, reason: from getter */
    public final android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener getTouchExplorationStateListener() {
        return this.touchExplorationStateListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void touchExplorationStateListener$lambda$1(AndroidComposeViewAccessibilityDelegateCompat this$0, boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.enabledServices = this$0.accessibilityManager.getEnabledAccessibilityServiceList(-1);
    }

    public final boolean isEnabled$ui_release() {
        return isEnabledForAccessibility() || getContentCaptureForceEnabledForTesting();
    }

    private final boolean isEnabledForAccessibility() {
        if (this.accessibilityForceEnabledForTesting) {
            return true;
        }
        if (this.accessibilityManager.isEnabled()) {
            List<AccessibilityServiceInfo> enabledServices = this.enabledServices;
            Intrinsics.checkNotNullExpressionValue(enabledServices, "enabledServices");
            if (!enabledServices.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: isEnabledForContentCapture, reason: from getter */
    private final boolean getContentCaptureForceEnabledForTesting() {
        return this.contentCaptureForceEnabledForTesting;
    }

    private final boolean isTouchExplorationEnabled() {
        return this.accessibilityForceEnabledForTesting || (this.accessibilityManager.isEnabled() && this.accessibilityManager.isTouchExplorationEnabled());
    }

    public final boolean getContentCaptureForceEnabledForTesting$ui_release() {
        return this.contentCaptureForceEnabledForTesting;
    }

    public final void setContentCaptureForceEnabledForTesting$ui_release(boolean z) {
        this.contentCaptureForceEnabledForTesting = z;
    }

    /* JADX INFO: renamed from: getContentCaptureSession$ui_release, reason: from getter */
    public final ContentCaptureSessionCompat getContentCaptureSession() {
        return this.contentCaptureSession;
    }

    public final void setContentCaptureSession$ui_release(ContentCaptureSessionCompat contentCaptureSessionCompat) {
        this.contentCaptureSession = contentCaptureSessionCompat;
    }

    public final ArrayMap<Integer, ViewStructureCompat> getBufferedContentCaptureAppearedNodes$ui_release() {
        return this.bufferedContentCaptureAppearedNodes;
    }

    public final ArraySet<Integer> getBufferedContentCaptureDisappearedNodes$ui_release() {
        return this.bufferedContentCaptureDisappearedNodes;
    }

    /* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent;", "", "node", "Landroidx/compose/ui/semantics/SemanticsNode;", "action", "", "granularity", "fromIndex", "toIndex", "traverseTime", "", "(Landroidx/compose/ui/semantics/SemanticsNode;IIIIJ)V", "getAction", "()I", "getFromIndex", "getGranularity", "getNode", "()Landroidx/compose/ui/semantics/SemanticsNode;", "getToIndex", "getTraverseTime", "()J", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class PendingTextTraversedEvent {
        private final int action;
        private final int fromIndex;
        private final int granularity;
        private final SemanticsNode node;
        private final int toIndex;
        private final long traverseTime;

        public PendingTextTraversedEvent(SemanticsNode node, int action, int granularity, int fromIndex, int toIndex, long traverseTime) {
            Intrinsics.checkNotNullParameter(node, "node");
            this.node = node;
            this.action = action;
            this.granularity = granularity;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            this.traverseTime = traverseTime;
        }

        public final SemanticsNode getNode() {
            return this.node;
        }

        public final int getAction() {
            return this.action;
        }

        public final int getGranularity() {
            return this.granularity;
        }

        public final int getFromIndex() {
            return this.fromIndex;
        }

        public final int getToIndex() {
            return this.toIndex;
        }

        public final long getTraverseTime() {
            return this.traverseTime;
        }
    }

    public final void setCurrentSemanticsNodes$ui_release(Map<Integer, SemanticsNodeWithAdjustedBounds> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.currentSemanticsNodes = map;
    }

    public final Map<Integer, SemanticsNodeWithAdjustedBounds> getCurrentSemanticsNodes$ui_release() {
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            this.currentSemanticsNodes = AndroidComposeViewAccessibilityDelegateCompat_androidKt.getAllUncoveredSemanticsNodesToMap(this.view.getSemanticsOwner());
            setTraversalValues();
        }
        return this.currentSemanticsNodes;
    }

    public final HashMap<Integer, Integer> getIdToBeforeMap$ui_release() {
        return this.idToBeforeMap;
    }

    public final void setIdToBeforeMap$ui_release(HashMap<Integer, Integer> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.idToBeforeMap = map;
    }

    public final HashMap<Integer, Integer> getIdToAfterMap$ui_release() {
        return this.idToAfterMap;
    }

    public final void setIdToAfterMap$ui_release(HashMap<Integer, Integer> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.idToAfterMap = map;
    }

    /* JADX INFO: renamed from: getEXTRA_DATA_TEST_TRAVERSALBEFORE_VAL$ui_release, reason: from getter */
    public final String getEXTRA_DATA_TEST_TRAVERSALBEFORE_VAL() {
        return this.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL;
    }

    /* JADX INFO: renamed from: getEXTRA_DATA_TEST_TRAVERSALAFTER_VAL$ui_release, reason: from getter */
    public final String getEXTRA_DATA_TEST_TRAVERSALAFTER_VAL() {
        return this.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL;
    }

    /* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0001\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u0006\u0010\u0013\u001a\u00020\u0014R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy;", "", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "currentSemanticsNodes", "", "", "Landroidx/compose/ui/platform/SemanticsNodeWithAdjustedBounds;", "(Landroidx/compose/ui/semantics/SemanticsNode;Ljava/util/Map;)V", "children", "", "getChildren", "()Ljava/util/Set;", "getSemanticsNode", "()Landroidx/compose/ui/semantics/SemanticsNode;", "unmergedConfig", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "getUnmergedConfig", "()Landroidx/compose/ui/semantics/SemanticsConfiguration;", "hasPaneTitle", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SemanticsNodeCopy {
        private final Set<Integer> children;
        private final SemanticsNode semanticsNode;
        private final SemanticsConfiguration unmergedConfig;

        public SemanticsNodeCopy(SemanticsNode semanticsNode, Map<Integer, SemanticsNodeWithAdjustedBounds> currentSemanticsNodes) {
            Intrinsics.checkNotNullParameter(semanticsNode, "semanticsNode");
            Intrinsics.checkNotNullParameter(currentSemanticsNodes, "currentSemanticsNodes");
            this.semanticsNode = semanticsNode;
            this.unmergedConfig = semanticsNode.getUnmergedConfig();
            this.children = new LinkedHashSet();
            List<SemanticsNode> replacedChildren$ui_release = semanticsNode.getReplacedChildren$ui_release();
            int size = replacedChildren$ui_release.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = replacedChildren$ui_release.get(index$iv);
                SemanticsNode child = (SemanticsNode) item$iv;
                if (currentSemanticsNodes.containsKey(Integer.valueOf(child.getId()))) {
                    this.children.add(Integer.valueOf(child.getId()));
                }
            }
        }

        public final SemanticsNode getSemanticsNode() {
            return this.semanticsNode;
        }

        public final SemanticsConfiguration getUnmergedConfig() {
            return this.unmergedConfig;
        }

        public final Set<Integer> getChildren() {
            return this.children;
        }

        public final boolean hasPaneTitle() {
            return this.unmergedConfig.contains(SemanticsProperties.INSTANCE.getPaneTitle());
        }
    }

    public final Map<Integer, SemanticsNodeCopy> getPreviousSemanticsNodes$ui_release() {
        return this.previousSemanticsNodes;
    }

    public final void setPreviousSemanticsNodes$ui_release(Map<Integer, SemanticsNodeCopy> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.previousSemanticsNodes = map;
    }

    /* JADX INFO: renamed from: canScroll-0AR0LA0$ui_release, reason: not valid java name */
    public final boolean m4526canScroll0AR0LA0$ui_release(boolean vertical, int direction, long position) {
        return m4527canScrollmoWRBKg$ui_release(getCurrentSemanticsNodes$ui_release().values(), vertical, direction, position);
    }

    /* JADX INFO: renamed from: canScroll-moWRBKg$ui_release, reason: not valid java name */
    public final boolean m4527canScrollmoWRBKg$ui_release(Collection<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes, boolean vertical, int direction, long position) {
        SemanticsPropertyKey<ScrollAxisRange> horizontalScrollAxisRange;
        boolean z;
        Intrinsics.checkNotNullParameter(currentSemanticsNodes, "currentSemanticsNodes");
        if (Offset.m2728equalsimpl0(position, Offset.INSTANCE.m2746getUnspecifiedF1C5BW0()) || !Offset.m2734isValidimpl(position)) {
            return false;
        }
        if (vertical) {
            horizontalScrollAxisRange = SemanticsProperties.INSTANCE.getVerticalScrollAxisRange();
        } else {
            if (vertical) {
                throw new NoWhenBranchMatchedException();
            }
            horizontalScrollAxisRange = SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange();
        }
        Collection<SemanticsNodeWithAdjustedBounds> $this$any$iv = currentSemanticsNodes;
        if ($this$any$iv.isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            SemanticsNodeWithAdjustedBounds node = (SemanticsNodeWithAdjustedBounds) element$iv;
            if (!RectHelper_androidKt.toComposeRect(node.getAdjustedBounds()).m2757containsk4lQ0M(position)) {
                z = false;
            } else {
                ScrollAxisRange scrollRange = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(node.getSemanticsNode().getConfig(), horizontalScrollAxisRange);
                if (scrollRange == null) {
                    z = false;
                } else {
                    int actualDirection = scrollRange.getReverseScrolling() ? -direction : direction;
                    if (direction == 0 && scrollRange.getReverseScrolling()) {
                        actualDirection = -1;
                    }
                    z = actualDirection < 0 ? scrollRange.getValue().invoke().floatValue() > 0.0f : scrollRange.getValue().invoke().floatValue() < scrollRange.getMaxValue().invoke().floatValue();
                }
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AccessibilityNodeInfo createNodeInfo(int virtualViewId) {
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycleRegistry;
        AndroidComposeView.ViewTreeOwners viewTreeOwners = this.view.getViewTreeOwners();
        if (((viewTreeOwners == null || (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) == null || (lifecycleRegistry = lifecycleOwner.getLifecycleRegistry()) == null) ? null : lifecycleRegistry.getState()) == Lifecycle.State.DESTROYED) {
            return null;
        }
        AccessibilityNodeInfoCompat info = AccessibilityNodeInfoCompat.obtain();
        Intrinsics.checkNotNullExpressionValue(info, "obtain()");
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes$ui_release().get(Integer.valueOf(virtualViewId));
        if (semanticsNodeWithAdjustedBounds == null) {
            return null;
        }
        SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode();
        if (virtualViewId == -1) {
            Object parentForAccessibility = ViewCompat.getParentForAccessibility(this.view);
            info.setParent(parentForAccessibility instanceof View ? (View) parentForAccessibility : null);
        } else if (semanticsNode.getParent() != null) {
            SemanticsNode parent = semanticsNode.getParent();
            Intrinsics.checkNotNull(parent);
            int parentId = parent.getId();
            if (parentId == this.view.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
                parentId = -1;
            }
            info.setParent(this.view, parentId);
        } else {
            throw new IllegalStateException("semanticsNode " + virtualViewId + " has null parent");
        }
        info.setSource(this.view, virtualViewId);
        Rect boundsInRoot = semanticsNodeWithAdjustedBounds.getAdjustedBounds();
        long topLeftInScreen = this.view.mo4187localToScreenMKHz9U(OffsetKt.Offset(boundsInRoot.left, boundsInRoot.top));
        long bottomRightInScreen = this.view.mo4187localToScreenMKHz9U(OffsetKt.Offset(boundsInRoot.right, boundsInRoot.bottom));
        info.setBoundsInScreen(new Rect((int) Math.floor(Offset.m2731getXimpl(topLeftInScreen)), (int) Math.floor(Offset.m2732getYimpl(topLeftInScreen)), (int) Math.ceil(Offset.m2731getXimpl(bottomRightInScreen)), (int) Math.ceil(Offset.m2732getYimpl(bottomRightInScreen))));
        populateAccessibilityNodeInfoProperties(virtualViewId, info, semanticsNode);
        return info.unwrap();
    }

    private final Comparator<SemanticsNode> semanticComparator(boolean layoutIsRtl) {
        final Comparator comparator = ComparisonsKt.compareBy(new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$comparator$1
            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(SemanticsNode it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Float.valueOf(it.getBoundsInWindow().getLeft());
            }
        }, new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$comparator$2
            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(SemanticsNode it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Float.valueOf(it.getBoundsInWindow().getTop());
            }
        }, new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$comparator$3
            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(SemanticsNode it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Float.valueOf(it.getBoundsInWindow().getBottom());
            }
        }, new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$comparator$4
            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(SemanticsNode it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Float.valueOf(it.getBoundsInWindow().getRight());
            }
        });
        if (layoutIsRtl) {
            comparator = ComparisonsKt.compareBy(new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.semanticComparator.1
                @Override // kotlin.jvm.functions.Function1
                public final Comparable<?> invoke(SemanticsNode it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Float.valueOf(it.getBoundsInWindow().getRight());
                }
            }, new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.semanticComparator.2
                @Override // kotlin.jvm.functions.Function1
                public final Comparable<?> invoke(SemanticsNode it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Float.valueOf(it.getBoundsInWindow().getTop());
                }
            }, new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.semanticComparator.3
                @Override // kotlin.jvm.functions.Function1
                public final Comparable<?> invoke(SemanticsNode it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Float.valueOf(it.getBoundsInWindow().getBottom());
                }
            }, new Function1<SemanticsNode, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.semanticComparator.4
                @Override // kotlin.jvm.functions.Function1
                public final Comparable<?> invoke(SemanticsNode it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Float.valueOf(it.getBoundsInWindow().getLeft());
                }
            });
        }
        final Comparator<LayoutNode> zComparator$ui_release = LayoutNode.INSTANCE.getZComparator$ui_release();
        final Comparator comparator2 = new Comparator() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int previousCompare = comparator.compare(t, t2);
                if (previousCompare != 0) {
                    return previousCompare;
                }
                SemanticsNode it = (SemanticsNode) t;
                SemanticsNode it2 = (SemanticsNode) t2;
                return zComparator$ui_release.compare(it.getLayoutNode(), it2.getLayoutNode());
            }
        };
        return new Comparator() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int previousCompare = comparator2.compare(t, t2);
                if (previousCompare != 0) {
                    return previousCompare;
                }
                SemanticsNode it = (SemanticsNode) t;
                Integer numValueOf = Integer.valueOf(it.getId());
                SemanticsNode it2 = (SemanticsNode) t2;
                return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(it2.getId()));
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ List sortByGeometryGroupings$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, boolean z, List list, Map map, int i, Object obj) {
        if ((i & 4) != 0) {
            map = new LinkedHashMap();
        }
        return androidComposeViewAccessibilityDelegateCompat.sortByGeometryGroupings(z, list, map);
    }

    private final List<SemanticsNode> sortByGeometryGroupings(boolean layoutIsRtl, List<SemanticsNode> parentListToSort, Map<Integer, List<SemanticsNode>> containerChildrenMapping) {
        List rowGroupings = new ArrayList();
        int entryIndex = 0;
        int lastIndex = CollectionsKt.getLastIndex(parentListToSort);
        if (0 <= lastIndex) {
            while (true) {
                SemanticsNode currEntry = parentListToSort.get(entryIndex);
                if (entryIndex == 0 || !sortByGeometryGroupings$placedEntryRowOverlaps(rowGroupings, currEntry)) {
                    androidx.compose.ui.geometry.Rect newRect = currEntry.getBoundsInWindow();
                    rowGroupings.add(new Pair(newRect, CollectionsKt.mutableListOf(currEntry)));
                }
                if (entryIndex == lastIndex) {
                    break;
                }
                entryIndex++;
            }
        }
        CollectionsKt.sortWith(rowGroupings, ComparisonsKt.compareBy(new Function1<Pair<? extends androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>>, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sortByGeometryGroupings.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Comparable<?> invoke2(Pair<androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Float.valueOf(it.getFirst().getTop());
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Comparable<?> invoke(Pair<? extends androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>> pair) {
                return invoke2((Pair<androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>>) pair);
            }
        }, new Function1<Pair<? extends androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>>, Comparable<?>>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sortByGeometryGroupings.2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Comparable<?> invoke2(Pair<androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Float.valueOf(it.getFirst().getBottom());
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Comparable<?> invoke(Pair<? extends androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>> pair) {
                return invoke2((Pair<androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>>) pair);
            }
        }));
        List returnList = new ArrayList();
        int size = rowGroupings.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = rowGroupings.get(index$iv);
            Pair row = (Pair) item$iv;
            CollectionsKt.sortWith((List) row.getSecond(), semanticComparator(layoutIsRtl));
            returnList.addAll((Collection) row.getSecond());
        }
        CollectionsKt.sortWith(returnList, new Comparator() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sortByGeometryGroupings$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                SemanticsNode it = (SemanticsNode) t;
                Float fValueOf = Float.valueOf(AndroidComposeViewAccessibilityDelegateCompat_androidKt.getGetTraversalIndex(it));
                SemanticsNode it2 = (SemanticsNode) t2;
                return ComparisonsKt.compareValues(fValueOf, Float.valueOf(AndroidComposeViewAccessibilityDelegateCompat_androidKt.getGetTraversalIndex(it2)));
            }
        });
        int i = 0;
        while (i <= CollectionsKt.getLastIndex(returnList)) {
            int currNodeId = ((SemanticsNode) returnList.get(i)).getId();
            List<SemanticsNode> list = containerChildrenMapping.get(Integer.valueOf(currNodeId));
            if (list != null) {
                returnList.remove(i);
                returnList.addAll(i, list);
            }
            List<SemanticsNode> list2 = containerChildrenMapping.get(Integer.valueOf(currNodeId));
            i += list2 != null ? list2.size() : 1;
        }
        return returnList;
    }

    private static final boolean sortByGeometryGroupings$placedEntryRowOverlaps(List<Pair<androidx.compose.ui.geometry.Rect, List<SemanticsNode>>> list, SemanticsNode node) {
        float entryTopCoord = node.getBoundsInWindow().getTop();
        float entryBottomCoord = node.getBoundsInWindow().getBottom();
        OpenEndRange<Float> openEndRangeRangeUntil = AndroidComposeViewAccessibilityDelegateCompat_androidKt.rangeUntil(entryTopCoord, entryBottomCoord);
        int currIndex = 0;
        int lastIndex = CollectionsKt.getLastIndex(list);
        if (0 > lastIndex) {
            return false;
        }
        while (true) {
            androidx.compose.ui.geometry.Rect currRect = list.get(currIndex).getFirst();
            if (!AndroidComposeViewAccessibilityDelegateCompat_androidKt.overlaps(AndroidComposeViewAccessibilityDelegateCompat_androidKt.rangeUntil(currRect.getTop(), currRect.getBottom()), openEndRangeRangeUntil)) {
                if (currIndex == lastIndex) {
                    return false;
                }
                currIndex++;
            } else {
                androidx.compose.ui.geometry.Rect newRect = currRect.intersect(new androidx.compose.ui.geometry.Rect(0.0f, entryTopCoord, Float.POSITIVE_INFINITY, entryBottomCoord));
                list.set(currIndex, new Pair<>(newRect, list.get(currIndex).getSecond()));
                list.get(currIndex).getSecond().add(node);
                return true;
            }
        }
    }

    private final List<SemanticsNode> subtreeSortedByGeometryGrouping(boolean layoutIsRtl, List<SemanticsNode> listToSort) {
        Map containerMapToChildren = new LinkedHashMap();
        List geometryList = new ArrayList();
        int size = listToSort.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = listToSort.get(index$iv);
            SemanticsNode node = (SemanticsNode) item$iv;
            subtreeSortedByGeometryGrouping$depthFirstSearch(this, geometryList, containerMapToChildren, layoutIsRtl, node);
        }
        return sortByGeometryGroupings(layoutIsRtl, geometryList, containerMapToChildren);
    }

    private static final void subtreeSortedByGeometryGrouping$depthFirstSearch(AndroidComposeViewAccessibilityDelegateCompat this$0, List<SemanticsNode> list, Map<Integer, List<SemanticsNode>> map, boolean $layoutIsRtl, SemanticsNode currNode) {
        if ((Intrinsics.areEqual((Object) AndroidComposeViewAccessibilityDelegateCompat_androidKt.isTraversalGroup(currNode), (Object) true) || this$0.isScreenReaderFocusable(currNode)) && this$0.getCurrentSemanticsNodes$ui_release().keySet().contains(Integer.valueOf(currNode.getId()))) {
            list.add(currNode);
        }
        if (Intrinsics.areEqual((Object) AndroidComposeViewAccessibilityDelegateCompat_androidKt.isTraversalGroup(currNode), (Object) true)) {
            map.put(Integer.valueOf(currNode.getId()), this$0.subtreeSortedByGeometryGrouping($layoutIsRtl, CollectionsKt.toMutableList((Collection) currNode.getChildren())));
            return;
        }
        List<SemanticsNode> children = currNode.getChildren();
        int size = children.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = children.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            subtreeSortedByGeometryGrouping$depthFirstSearch(this$0, list, map, $layoutIsRtl, child);
        }
    }

    private final void setTraversalValues() {
        this.idToBeforeMap.clear();
        this.idToAfterMap.clear();
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes$ui_release().get(-1);
        SemanticsNode hostSemanticsNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.getSemanticsNode() : null;
        Intrinsics.checkNotNull(hostSemanticsNode);
        boolean layoutIsRtl = AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(hostSemanticsNode);
        List<SemanticsNode> listSubtreeSortedByGeometryGrouping = subtreeSortedByGeometryGrouping(layoutIsRtl, CollectionsKt.mutableListOf(hostSemanticsNode));
        int i = 1;
        int lastIndex = CollectionsKt.getLastIndex(listSubtreeSortedByGeometryGrouping);
        if (1 > lastIndex) {
            return;
        }
        while (true) {
            int prevId = listSubtreeSortedByGeometryGrouping.get(i - 1).getId();
            int currId = listSubtreeSortedByGeometryGrouping.get(i).getId();
            this.idToBeforeMap.put(Integer.valueOf(prevId), Integer.valueOf(currId));
            this.idToAfterMap.put(Integer.valueOf(currId), Integer.valueOf(prevId));
            if (i == lastIndex) {
                return;
            } else {
                i++;
            }
        }
    }

    private final boolean isScreenReaderFocusable(SemanticsNode node) {
        boolean isSpeakingNode = (AndroidComposeViewAccessibilityDelegateCompat_androidKt.getInfoContentDescriptionOrNull(node) == null && getInfoText(node) == null && getInfoStateDescriptionOrNull(node) == null && !getInfoIsCheckable(node)) ? false : true;
        return node.getUnmergedConfig().getIsMergingSemanticsOfDescendants() || (node.isUnmergedLeafNode$ui_release() && isSpeakingNode);
    }

    public final void populateAccessibilityNodeInfoProperties(int virtualViewId, AccessibilityNodeInfoCompat info, SemanticsNode semanticsNode) {
        AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat;
        AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat2;
        Intrinsics.checkNotNullParameter(info, "info");
        Intrinsics.checkNotNullParameter(semanticsNode, "semanticsNode");
        info.setClassName(ClassName);
        Role role = (Role) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getRole());
        if (role != null) {
            role.getValue();
            if (semanticsNode.getIsFake() || semanticsNode.getReplacedChildren$ui_release().isEmpty()) {
                if (Role.m4606equalsimpl0(role.getValue(), Role.INSTANCE.m4616getTabo7Vup1c())) {
                    info.setRoleDescription(this.view.getContext().getResources().getString(R.string.tab));
                } else if (!Role.m4606equalsimpl0(role.getValue(), Role.INSTANCE.m4615getSwitcho7Vup1c())) {
                    String className = AndroidComposeViewAccessibilityDelegateCompat_androidKt.m4529toLegacyClassNameV4PA4sw(role.getValue());
                    if (!Role.m4606equalsimpl0(role.getValue(), Role.INSTANCE.m4613getImageo7Vup1c()) || semanticsNode.isUnmergedLeafNode$ui_release() || semanticsNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants()) {
                        info.setClassName(className);
                    }
                } else {
                    info.setRoleDescription(this.view.getContext().getResources().getString(R.string.switch_role));
                }
            }
            Unit unit = Unit.INSTANCE;
            Unit unit2 = Unit.INSTANCE;
        }
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.isTextField(semanticsNode)) {
            info.setClassName(TextFieldClassName);
        }
        if (semanticsNode.getConfig().contains(SemanticsProperties.INSTANCE.getText())) {
            info.setClassName(TextClassName);
        }
        info.setPackageName(this.view.getContext().getPackageName());
        info.setImportantForAccessibility(true);
        List<SemanticsNode> replacedChildren$ui_release = semanticsNode.getReplacedChildren$ui_release();
        int size = replacedChildren$ui_release.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = replacedChildren$ui_release.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            if (getCurrentSemanticsNodes$ui_release().containsKey(Integer.valueOf(child.getId()))) {
                AndroidViewHolder holder = this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(child.getLayoutNode());
                if (holder != null) {
                    info.addChild(holder);
                } else {
                    info.addChild(this.view, child.getId());
                }
            }
        }
        if (this.focusedVirtualViewId == virtualViewId) {
            info.setAccessibilityFocused(true);
            info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        } else {
            info.setAccessibilityFocused(false);
            info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_ACCESSIBILITY_FOCUS);
        }
        setText(semanticsNode, info);
        setContentInvalid(semanticsNode, info);
        setStateDescription(semanticsNode, info);
        setIsCheckable(semanticsNode, info);
        ToggleableState toggleState = (ToggleableState) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getToggleableState());
        if (toggleState != null) {
            if (toggleState == ToggleableState.On) {
                info.setChecked(true);
            } else if (toggleState == ToggleableState.Off) {
                info.setChecked(false);
            }
            Unit unit3 = Unit.INSTANCE;
            Unit unit4 = Unit.INSTANCE;
        }
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getSelected());
        if (bool != null) {
            boolean it = bool.booleanValue();
            if (role == null ? false : Role.m4606equalsimpl0(role.getValue(), Role.INSTANCE.m4616getTabo7Vup1c())) {
                info.setSelected(it);
            } else {
                info.setChecked(it);
            }
            Unit unit5 = Unit.INSTANCE;
            Unit unit6 = Unit.INSTANCE;
        }
        if (!semanticsNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants() || semanticsNode.getReplacedChildren$ui_release().isEmpty()) {
            info.setContentDescription(AndroidComposeViewAccessibilityDelegateCompat_androidKt.getInfoContentDescriptionOrNull(semanticsNode));
        }
        String testTag = (String) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getTestTag());
        if (testTag != null) {
            boolean testTagsAsResourceId = false;
            for (SemanticsNode current = semanticsNode; current != null; current = current.getParent()) {
                if (current.getUnmergedConfig().contains(SemanticsPropertiesAndroid.INSTANCE.getTestTagsAsResourceId())) {
                    testTagsAsResourceId = ((Boolean) current.getUnmergedConfig().get(SemanticsPropertiesAndroid.INSTANCE.getTestTagsAsResourceId())).booleanValue();
                    break;
                }
            }
            if (testTagsAsResourceId) {
                info.setViewIdResourceName(testTag);
            }
        }
        if (((Unit) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHeading())) != null) {
            info.setHeading(true);
            Unit it2 = Unit.INSTANCE;
            Unit unit7 = Unit.INSTANCE;
        }
        info.setPassword(AndroidComposeViewAccessibilityDelegateCompat_androidKt.isPassword(semanticsNode));
        info.setEditable(AndroidComposeViewAccessibilityDelegateCompat_androidKt.isTextField(semanticsNode));
        info.setEnabled(AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode));
        info.setFocusable(semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getFocused()));
        int i = 2;
        if (info.isFocusable()) {
            info.setFocused(((Boolean) semanticsNode.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getFocused())).booleanValue());
            if (info.isFocused()) {
                info.addAction(2);
            } else {
                info.addAction(1);
            }
        }
        info.setVisibleToUser(AndroidComposeViewAccessibilityDelegateCompat_androidKt.isVisible(semanticsNode));
        LiveRegionMode liveRegionMode = (LiveRegionMode) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getLiveRegion());
        if (liveRegionMode != null) {
            int it3 = liveRegionMode.getValue();
            if (LiveRegionMode.m4597equalsimpl0(it3, LiveRegionMode.INSTANCE.m4602getPolite0phEisY()) || !LiveRegionMode.m4597equalsimpl0(it3, LiveRegionMode.INSTANCE.m4601getAssertive0phEisY())) {
                i = 1;
            }
            info.setLiveRegion(i);
            Unit unit8 = Unit.INSTANCE;
            Unit unit9 = Unit.INSTANCE;
        }
        info.setClickable(false);
        AccessibilityAction it4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnClick());
        if (it4 != null) {
            boolean isSelected = Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getSelected()), (Object) true);
            info.setClickable(!isSelected);
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode) && !isSelected) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16, it4.getLabel()));
            }
            Unit unit10 = Unit.INSTANCE;
            Unit unit11 = Unit.INSTANCE;
        }
        info.setLongClickable(false);
        AccessibilityAction it5 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnLongClick());
        if (it5 != null) {
            info.setLongClickable(true);
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(32, it5.getLabel()));
            }
            Unit unit12 = Unit.INSTANCE;
            Unit unit13 = Unit.INSTANCE;
        }
        AccessibilityAction it6 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCopyText());
        if (it6 != null) {
            info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16384, it6.getLabel()));
            Unit unit14 = Unit.INSTANCE;
            Unit unit15 = Unit.INSTANCE;
        }
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
            AccessibilityAction it7 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetText());
            if (it7 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(2097152, it7.getLabel()));
                Unit unit16 = Unit.INSTANCE;
                Unit unit17 = Unit.INSTANCE;
            }
            AccessibilityAction it8 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPerformImeAction());
            if (it8 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionImeEnter, it8.getLabel()));
                Unit unit18 = Unit.INSTANCE;
                Unit unit19 = Unit.INSTANCE;
            }
            AccessibilityAction it9 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCutText());
            if (it9 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(65536, it9.getLabel()));
                Unit unit20 = Unit.INSTANCE;
                Unit unit21 = Unit.INSTANCE;
            }
            AccessibilityAction it10 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPasteText());
            if (it10 != null) {
                if (info.isFocused() && this.view.getClipboardManager().hasText()) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(32768, it10.getLabel()));
                }
                Unit unit22 = Unit.INSTANCE;
                Unit unit23 = Unit.INSTANCE;
            }
        }
        String text = getIterableTextForAccessibility(semanticsNode);
        String str = text;
        if (!(str == null || str.length() == 0)) {
            info.setTextSelection(getAccessibilitySelectionStart(semanticsNode), getAccessibilitySelectionEnd(semanticsNode));
            AccessibilityAction setSelectionAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetSelection());
            info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(131072, setSelectionAction != null ? setSelectionAction.getLabel() : null));
            info.addAction(256);
            info.addAction(512);
            info.setMovementGranularities(11);
            List contentDescription = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getContentDescription());
            List list = contentDescription;
            if ((list == null || list.isEmpty()) && semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult()) && !AndroidComposeViewAccessibilityDelegateCompat_androidKt.excludeLineAndPageGranularities(semanticsNode)) {
                info.setMovementGranularities(16 | info.getMovementGranularities() | 4);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            List extraDataKeys = new ArrayList();
            extraDataKeys.add(ExtraDataIdKey);
            CharSequence text2 = info.getText();
            if (!(text2 == null || text2.length() == 0) && semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult())) {
                extraDataKeys.add("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY");
            }
            if (semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTestTag())) {
                extraDataKeys.add(ExtraDataTestTagKey);
            }
            AccessibilityNodeInfoVerificationHelperMethods accessibilityNodeInfoVerificationHelperMethods = AccessibilityNodeInfoVerificationHelperMethods.INSTANCE;
            AccessibilityNodeInfo accessibilityNodeInfoUnwrap = info.unwrap();
            Intrinsics.checkNotNullExpressionValue(accessibilityNodeInfoUnwrap, "info.unwrap()");
            accessibilityNodeInfoVerificationHelperMethods.setAvailableExtraData(accessibilityNodeInfoUnwrap, extraDataKeys);
        }
        ProgressBarRangeInfo rangeInfo = (ProgressBarRangeInfo) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getProgressBarRangeInfo());
        if (rangeInfo != null) {
            if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetProgress())) {
                info.setClassName("android.widget.SeekBar");
            } else {
                info.setClassName("android.widget.ProgressBar");
            }
            if (rangeInfo != ProgressBarRangeInfo.INSTANCE.getIndeterminate()) {
                info.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(1, rangeInfo.getRange().getStart().floatValue(), rangeInfo.getRange().getEndInclusive().floatValue(), rangeInfo.getCurrent()));
            }
            if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetProgress()) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                if (rangeInfo.getCurrent() < RangesKt.coerceAtLeast(rangeInfo.getRange().getEndInclusive().floatValue(), rangeInfo.getRange().getStart().floatValue())) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                }
                if (rangeInfo.getCurrent() > RangesKt.coerceAtMost(rangeInfo.getRange().getStart().floatValue(), rangeInfo.getRange().getEndInclusive().floatValue())) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                }
            }
        }
        if (Build.VERSION.SDK_INT >= 24) {
            Api24Impl.addSetProgressAction(info, semanticsNode);
        }
        CollectionInfoKt.setCollectionInfo(semanticsNode, info);
        CollectionInfoKt.setCollectionItemInfo(semanticsNode, info);
        ScrollAxisRange xScrollState = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange());
        AccessibilityAction scrollAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getScrollBy());
        if (xScrollState != null && scrollAction != null) {
            if (!CollectionInfoKt.hasCollectionInfo(semanticsNode)) {
                info.setClassName("android.widget.HorizontalScrollView");
            }
            if (xScrollState.getMaxValue().invoke().floatValue() > 0.0f) {
                info.setScrollable(true);
            }
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                if (populateAccessibilityNodeInfoProperties$canScrollForward(xScrollState)) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                    if (!AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(semanticsNode)) {
                        accessibilityActionCompat2 = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT;
                    } else {
                        accessibilityActionCompat2 = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT;
                    }
                    info.addAction(accessibilityActionCompat2);
                }
                if (populateAccessibilityNodeInfoProperties$canScrollBackward(xScrollState)) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                    if (!AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(semanticsNode)) {
                        accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT;
                    } else {
                        accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT;
                    }
                    info.addAction(accessibilityActionCompat);
                }
            }
        }
        ScrollAxisRange yScrollState = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange());
        if (yScrollState != null && scrollAction != null) {
            if (!CollectionInfoKt.hasCollectionInfo(semanticsNode)) {
                info.setClassName("android.widget.ScrollView");
            }
            if (yScrollState.getMaxValue().invoke().floatValue() > 0.0f) {
                info.setScrollable(true);
            }
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                if (populateAccessibilityNodeInfoProperties$canScrollForward(yScrollState)) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN);
                }
                if (populateAccessibilityNodeInfoProperties$canScrollBackward(yScrollState)) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP);
                }
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.addPageActions(info, semanticsNode);
        }
        info.setPaneTitle((CharSequence) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getPaneTitle()));
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
            AccessibilityAction it11 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getExpand());
            if (it11 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(262144, it11.getLabel()));
                Unit unit24 = Unit.INSTANCE;
                Unit unit25 = Unit.INSTANCE;
            }
            AccessibilityAction it12 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCollapse());
            if (it12 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(524288, it12.getLabel()));
                Unit unit26 = Unit.INSTANCE;
                Unit unit27 = Unit.INSTANCE;
            }
            AccessibilityAction it13 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getDismiss());
            if (it13 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(1048576, it13.getLabel()));
                Unit unit28 = Unit.INSTANCE;
                Unit unit29 = Unit.INSTANCE;
            }
            if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getCustomActions())) {
                List customActions = (List) semanticsNode.getUnmergedConfig().get(SemanticsActions.INSTANCE.getCustomActions());
                int size2 = customActions.size();
                int[] iArr = AccessibilityActionsResourceIds;
                if (size2 >= iArr.length) {
                    throw new IllegalStateException("Can't have more than " + iArr.length + " custom actions for one widget");
                }
                SparseArrayCompat<CharSequence> sparseArrayCompat = new SparseArrayCompat<>();
                Map currentLabelToActionId = new LinkedHashMap();
                if (this.labelToActionId.containsKey(virtualViewId)) {
                    Map<CharSequence, Integer> map = this.labelToActionId.get(virtualViewId);
                    List<Integer> mutableList = ArraysKt.toMutableList(iArr);
                    List unassignedActions = new ArrayList();
                    List $this$fastForEach$iv = customActions;
                    int size3 = $this$fastForEach$iv.size();
                    int index$iv2 = 0;
                    while (index$iv2 < size3) {
                        int i2 = size3;
                        List $this$fastForEach$iv2 = $this$fastForEach$iv;
                        Object item$iv2 = $this$fastForEach$iv2.get(index$iv2);
                        CustomAccessibilityAction action = (CustomAccessibilityAction) item$iv2;
                        Intrinsics.checkNotNull(map);
                        ScrollAxisRange xScrollState2 = xScrollState;
                        if (map.containsKey(action.getLabel())) {
                            Integer actionId = map.get(action.getLabel());
                            Intrinsics.checkNotNull(actionId);
                            sparseArrayCompat.put(actionId.intValue(), action.getLabel());
                            currentLabelToActionId.put(action.getLabel(), actionId);
                            mutableList.remove(actionId);
                            info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(actionId.intValue(), action.getLabel()));
                        } else {
                            unassignedActions.add(action);
                        }
                        index$iv2++;
                        size3 = i2;
                        $this$fastForEach$iv = $this$fastForEach$iv2;
                        xScrollState = xScrollState2;
                        map = map;
                        scrollAction = scrollAction;
                    }
                    List $this$fastForEachIndexed$iv = unassignedActions;
                    int $i$f$fastForEachIndexed = 0;
                    int index$iv3 = 0;
                    int size4 = $this$fastForEachIndexed$iv.size();
                    while (index$iv3 < size4) {
                        Object item$iv3 = $this$fastForEachIndexed$iv.get(index$iv3);
                        CustomAccessibilityAction action2 = (CustomAccessibilityAction) item$iv3;
                        int index = index$iv3;
                        List $this$fastForEachIndexed$iv2 = $this$fastForEachIndexed$iv;
                        int actionId2 = mutableList.get(index).intValue();
                        sparseArrayCompat.put(actionId2, action2.getLabel());
                        currentLabelToActionId.put(action2.getLabel(), Integer.valueOf(actionId2));
                        info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(actionId2, action2.getLabel()));
                        index$iv3++;
                        unassignedActions = unassignedActions;
                        $this$fastForEachIndexed$iv = $this$fastForEachIndexed$iv2;
                        $i$f$fastForEachIndexed = $i$f$fastForEachIndexed;
                    }
                } else {
                    List $this$fastForEachIndexed$iv3 = customActions;
                    int $i$f$fastForEachIndexed2 = 0;
                    int index$iv4 = 0;
                    int size5 = $this$fastForEachIndexed$iv3.size();
                    while (index$iv4 < size5) {
                        Object item$iv4 = $this$fastForEachIndexed$iv3.get(index$iv4);
                        CustomAccessibilityAction action3 = (CustomAccessibilityAction) item$iv4;
                        int index2 = index$iv4;
                        List customActions2 = customActions;
                        int actionId3 = AccessibilityActionsResourceIds[index2];
                        sparseArrayCompat.put(actionId3, action3.getLabel());
                        currentLabelToActionId.put(action3.getLabel(), Integer.valueOf(actionId3));
                        info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(actionId3, action3.getLabel()));
                        index$iv4++;
                        $this$fastForEachIndexed$iv3 = $this$fastForEachIndexed$iv3;
                        customActions = customActions2;
                        $i$f$fastForEachIndexed2 = $i$f$fastForEachIndexed2;
                    }
                }
                this.actionIdToLabel.put(virtualViewId, sparseArrayCompat);
                this.labelToActionId.put(virtualViewId, currentLabelToActionId);
            }
        }
        info.setScreenReaderFocusable(isScreenReaderFocusable(semanticsNode));
        Integer beforeId = this.idToBeforeMap.get(Integer.valueOf(virtualViewId));
        if (beforeId != null) {
            beforeId.intValue();
            View beforeView = AndroidComposeViewAccessibilityDelegateCompat_androidKt.semanticsIdToView(this.view.getAndroidViewsHandler$ui_release(), beforeId.intValue());
            if (beforeView != null) {
                info.setTraversalBefore(beforeView);
            } else {
                info.setTraversalBefore(this.view, beforeId.intValue());
            }
            AccessibilityNodeInfo accessibilityNodeInfoUnwrap2 = info.unwrap();
            Intrinsics.checkNotNullExpressionValue(accessibilityNodeInfoUnwrap2, "info.unwrap()");
            addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, accessibilityNodeInfoUnwrap2, this.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL, null);
            Unit unit30 = Unit.INSTANCE;
            Unit unit31 = Unit.INSTANCE;
        }
        Integer afterId = this.idToAfterMap.get(Integer.valueOf(virtualViewId));
        if (afterId != null) {
            afterId.intValue();
            View afterView = AndroidComposeViewAccessibilityDelegateCompat_androidKt.semanticsIdToView(this.view.getAndroidViewsHandler$ui_release(), afterId.intValue());
            if (afterView != null) {
                info.setTraversalAfter(afterView);
            } else {
                info.setTraversalAfter(this.view, afterId.intValue());
            }
            AccessibilityNodeInfo accessibilityNodeInfoUnwrap3 = info.unwrap();
            Intrinsics.checkNotNullExpressionValue(accessibilityNodeInfoUnwrap3, "info.unwrap()");
            addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, accessibilityNodeInfoUnwrap3, this.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL, null);
            Unit unit32 = Unit.INSTANCE;
            Unit unit33 = Unit.INSTANCE;
        }
    }

    private static final boolean populateAccessibilityNodeInfoProperties$canScrollForward(ScrollAxisRange $this$populateAccessibilityNodeInfoProperties_u24canScrollForward) {
        return ($this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getValue().invoke().floatValue() < $this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getMaxValue().invoke().floatValue() && !$this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getReverseScrolling()) || ($this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getValue().invoke().floatValue() > 0.0f && $this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getReverseScrolling());
    }

    private static final boolean populateAccessibilityNodeInfoProperties$canScrollBackward(ScrollAxisRange $this$populateAccessibilityNodeInfoProperties_u24canScrollBackward) {
        return ($this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getValue().invoke().floatValue() > 0.0f && !$this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getReverseScrolling()) || ($this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getValue().invoke().floatValue() < $this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getMaxValue().invoke().floatValue() && $this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getReverseScrolling());
    }

    private final void setContentInvalid(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        if (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getError())) {
            info.setContentInvalid(true);
            info.setError((CharSequence) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getError()));
        }
    }

    private final String getInfoStateDescriptionOrNull(SemanticsNode node) {
        int iCoerceIn;
        Object string;
        Object stateDescription = SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getStateDescription());
        ToggleableState toggleState = (ToggleableState) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getToggleableState());
        Role role = (Role) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getRole());
        if (toggleState != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[toggleState.ordinal()]) {
                case 1:
                    if ((role == null ? false : Role.m4606equalsimpl0(role.getValue(), Role.INSTANCE.m4615getSwitcho7Vup1c())) && stateDescription == null) {
                        stateDescription = this.view.getContext().getResources().getString(R.string.on);
                    }
                    break;
                case 2:
                    if ((role == null ? false : Role.m4606equalsimpl0(role.getValue(), Role.INSTANCE.m4615getSwitcho7Vup1c())) && stateDescription == null) {
                        stateDescription = this.view.getContext().getResources().getString(R.string.off);
                    }
                    break;
                case 3:
                    if (stateDescription == null) {
                        stateDescription = this.view.getContext().getResources().getString(R.string.indeterminate);
                    }
                    break;
            }
        }
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getSelected());
        if (bool != null) {
            boolean it = bool.booleanValue();
            if (!(role == null ? false : Role.m4606equalsimpl0(role.getValue(), Role.INSTANCE.m4616getTabo7Vup1c())) && stateDescription == null) {
                if (it) {
                    string = this.view.getContext().getResources().getString(R.string.selected);
                } else {
                    string = this.view.getContext().getResources().getString(R.string.not_selected);
                }
                stateDescription = string;
            }
        }
        ProgressBarRangeInfo rangeInfo = (ProgressBarRangeInfo) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getProgressBarRangeInfo());
        if (rangeInfo != null) {
            if (rangeInfo != ProgressBarRangeInfo.INSTANCE.getIndeterminate()) {
                if (stateDescription == null) {
                    ClosedFloatingPointRange<Float> range = rangeInfo.getRange();
                    float progress = RangesKt.coerceIn(((range.getEndInclusive().floatValue() - range.getStart().floatValue()) > 0.0f ? 1 : ((range.getEndInclusive().floatValue() - range.getStart().floatValue()) == 0.0f ? 0 : -1)) == 0 ? 0.0f : (rangeInfo.getCurrent() - range.getStart().floatValue()) / (range.getEndInclusive().floatValue() - range.getStart().floatValue()), 0.0f, 1.0f);
                    if (progress == 0.0f) {
                        iCoerceIn = 0;
                    } else {
                        iCoerceIn = 100;
                        if (!(progress == 1.0f)) {
                            iCoerceIn = RangesKt.coerceIn(MathKt.roundToInt(100 * progress), 1, 99);
                        }
                    }
                    int percent = iCoerceIn;
                    stateDescription = this.view.getContext().getResources().getString(R.string.template_percent, Integer.valueOf(percent));
                }
            } else if (stateDescription == null) {
                stateDescription = this.view.getContext().getResources().getString(R.string.in_progress);
            }
        }
        return (String) stateDescription;
    }

    private final void setStateDescription(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        info.setStateDescription(getInfoStateDescriptionOrNull(node));
    }

    private final boolean getInfoIsCheckable(SemanticsNode node) {
        boolean isCheckable = false;
        ToggleableState toggleState = (ToggleableState) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getToggleableState());
        Role role = (Role) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getRole());
        if (toggleState != null) {
            isCheckable = true;
        }
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getSelected());
        if (bool == null) {
            return isCheckable;
        }
        bool.booleanValue();
        if (!(role == null ? false : Role.m4606equalsimpl0(role.getValue(), Role.INSTANCE.m4616getTabo7Vup1c()))) {
            return true;
        }
        return isCheckable;
    }

    private final void setIsCheckable(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        info.setCheckable(getInfoIsCheckable(node));
    }

    private final SpannableString getInfoText(SemanticsNode node) {
        AnnotatedString annotatedString;
        FontFamily.Resolver fontFamilyResolver = this.view.getFontFamilyResolver();
        AnnotatedString textForTextField = getTextForTextField(node.getUnmergedConfig());
        SpannableString accessibilitySpannableString = null;
        SpannableString editableTextToAssign = (SpannableString) trimToSize(textForTextField != null ? AndroidAccessibilitySpannableString_androidKt.toAccessibilitySpannableString(textForTextField, this.view.getDensity(), fontFamilyResolver, this.urlSpanCache) : null, ParcelSafeTextLength);
        List list = (List) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
        if (list != null && (annotatedString = (AnnotatedString) CollectionsKt.firstOrNull(list)) != null) {
            accessibilitySpannableString = AndroidAccessibilitySpannableString_androidKt.toAccessibilitySpannableString(annotatedString, this.view.getDensity(), fontFamilyResolver, this.urlSpanCache);
        }
        SpannableString textToAssign = (SpannableString) trimToSize(accessibilitySpannableString, ParcelSafeTextLength);
        return editableTextToAssign == null ? textToAssign : editableTextToAssign;
    }

    private final void setText(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        info.setText(getInfoText(node));
    }

    private final boolean isAccessibilityFocused(int virtualViewId) {
        return this.focusedVirtualViewId == virtualViewId;
    }

    private final boolean requestAccessibilityFocus(int virtualViewId) {
        if (!isTouchExplorationEnabled() || isAccessibilityFocused(virtualViewId)) {
            return false;
        }
        int i = this.focusedVirtualViewId;
        if (i != Integer.MIN_VALUE) {
            sendEventForVirtualView$default(this, i, 65536, null, null, 12, null);
        }
        this.focusedVirtualViewId = virtualViewId;
        this.view.invalidate();
        sendEventForVirtualView$default(this, virtualViewId, 32768, null, null, 12, null);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ boolean sendEventForVirtualView$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, int i, int i2, Integer num, List list, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            num = null;
        }
        if ((i3 & 8) != 0) {
            list = null;
        }
        return androidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView(i, i2, num, list);
    }

    private final boolean sendEventForVirtualView(int virtualViewId, int eventType, Integer contentChangeType, List<String> contentDescription) {
        if (virtualViewId == Integer.MIN_VALUE || !isEnabled$ui_release()) {
            return false;
        }
        AccessibilityEvent event = createEvent$ui_release(virtualViewId, eventType);
        if (contentChangeType != null) {
            event.setContentChangeTypes(contentChangeType.intValue());
        }
        if (contentDescription != null) {
            event.setContentDescription(TempListUtilsKt.fastJoinToString$default(contentDescription, ",", null, null, 0, null, null, 62, null));
        }
        return sendEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean sendEvent(AccessibilityEvent event) {
        if (!isEnabledForAccessibility()) {
            return false;
        }
        return this.view.getParent().requestSendAccessibilityEvent(this.view, event);
    }

    public final AccessibilityEvent createEvent$ui_release(int virtualViewId, int eventType) {
        AccessibilityEvent event = AccessibilityEvent.obtain(eventType);
        Intrinsics.checkNotNullExpressionValue(event, "obtain(eventType)");
        event.setEnabled(true);
        event.setClassName(ClassName);
        event.setPackageName(this.view.getContext().getPackageName());
        event.setSource(this.view, virtualViewId);
        SemanticsNodeWithAdjustedBounds it = getCurrentSemanticsNodes$ui_release().get(Integer.valueOf(virtualViewId));
        if (it != null) {
            event.setPassword(AndroidComposeViewAccessibilityDelegateCompat_androidKt.isPassword(it.getSemanticsNode()));
        }
        return event;
    }

    private final AccessibilityEvent createTextSelectionChangedEvent(int virtualViewId, Integer fromIndex, Integer toIndex, Integer itemCount, CharSequence text) {
        AccessibilityEvent $this$createTextSelectionChangedEvent_u24lambda_u2441 = createEvent$ui_release(virtualViewId, 8192);
        if (fromIndex != null) {
            int it = fromIndex.intValue();
            $this$createTextSelectionChangedEvent_u24lambda_u2441.setFromIndex(it);
        }
        if (toIndex != null) {
            int it2 = toIndex.intValue();
            $this$createTextSelectionChangedEvent_u24lambda_u2441.setToIndex(it2);
        }
        if (itemCount != null) {
            int it3 = itemCount.intValue();
            $this$createTextSelectionChangedEvent_u24lambda_u2441.setItemCount(it3);
        }
        if (text != null) {
            $this$createTextSelectionChangedEvent_u24lambda_u2441.getText().add(text);
        }
        return $this$createTextSelectionChangedEvent_u24lambda_u2441;
    }

    private final boolean clearAccessibilityFocus(int virtualViewId) {
        if (isAccessibilityFocused(virtualViewId)) {
            this.focusedVirtualViewId = Integer.MIN_VALUE;
            this.view.invalidate();
            sendEventForVirtualView$default(this, virtualViewId, 65536, null, null, 12, null);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean performActionHelper(int virtualViewId, int action, Bundle arguments) {
        SemanticsNode node;
        Function0 function0;
        Function0 function1;
        Function0 function2;
        Function0 function3;
        Function2 function4;
        Function0 function5;
        Function0 function6;
        Function0 function7;
        Function0 function8;
        Function0 function9;
        Function1 function10;
        Function2 function11;
        SemanticsConfiguration config;
        SemanticsConfiguration config2;
        AccessibilityAction accessibilityAction;
        Function1 function12;
        Function0 function13;
        Function0 function14;
        Function0 function15;
        Function0 function16;
        Function0 function17;
        CharSequence label;
        List customActions;
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes$ui_release().get(Integer.valueOf(virtualViewId));
        if (semanticsNodeWithAdjustedBounds != null && (node = semanticsNodeWithAdjustedBounds.getSemanticsNode()) != null) {
            switch (action) {
                case 64:
                    return requestAccessibilityFocus(virtualViewId);
                case 128:
                    return clearAccessibilityFocus(virtualViewId);
                case 256:
                case 512:
                    if (arguments == null) {
                        return false;
                    }
                    int granularity = arguments.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT);
                    boolean extendSelection = arguments.getBoolean(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN);
                    return traverseAtGranularity(node, granularity, action == 256, extendSelection);
                case 16384:
                    AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getCopyText());
                    if (accessibilityAction2 == null || (function0 = (Function0) accessibilityAction2.getAction()) == null) {
                        return false;
                    }
                    return ((Boolean) function0.invoke()).booleanValue();
                case 131072:
                    int start = arguments != null ? arguments.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_START_INT, -1) : -1;
                    int end = arguments != null ? arguments.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_END_INT, -1) : -1;
                    boolean success = setAccessibilitySelection(node, start, end, false);
                    if (success) {
                        sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(node.getId()), 0, null, null, 12, null);
                    }
                    return success;
                default:
                    if (!AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(node)) {
                        return false;
                    }
                    Object obj = null;
                    switch (action) {
                        case 1:
                            AccessibilityAction accessibilityAction3 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getRequestFocus());
                            if (accessibilityAction3 == null || (function1 = (Function0) accessibilityAction3.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function1.invoke()).booleanValue();
                        case 2:
                            if (!Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getFocused()), (Object) true)) {
                                return false;
                            }
                            FocusManager.CC.clearFocus$default(this.view.getFocusOwner(), false, 1, null);
                            return true;
                        case 16:
                            AccessibilityAction accessibilityAction4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnClick());
                            if (accessibilityAction4 != null && (function2 = (Function0) accessibilityAction4.getAction()) != null) {
                                obj = (Boolean) function2.invoke();
                            }
                            Boolean bool = obj;
                            sendEventForVirtualView$default(this, virtualViewId, 1, null, null, 12, null);
                            if (bool != 0) {
                                return bool.booleanValue();
                            }
                            return false;
                        case 32:
                            AccessibilityAction accessibilityAction5 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnLongClick());
                            if (accessibilityAction5 == null || (function3 = (Function0) accessibilityAction5.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function3.invoke()).booleanValue();
                        case 4096:
                        case 8192:
                        case android.R.id.accessibilityActionScrollUp:
                        case android.R.id.accessibilityActionScrollLeft:
                        case android.R.id.accessibilityActionScrollDown:
                        case android.R.id.accessibilityActionScrollRight:
                            boolean scrollForward = action == 4096;
                            boolean scrollBackward = action == 8192;
                            boolean scrollLeft = action == 16908345;
                            boolean scrollRight = action == 16908347;
                            boolean scrollUp = action == 16908344;
                            boolean scrollDown = action == 16908346;
                            boolean scrollHorizontal = scrollLeft || scrollRight || scrollForward || scrollBackward;
                            boolean scrollVertical = scrollUp || scrollDown || scrollForward || scrollBackward;
                            if (scrollForward || scrollBackward) {
                                ProgressBarRangeInfo rangeInfo = (ProgressBarRangeInfo) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getProgressBarRangeInfo());
                                AccessibilityAction setProgressAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetProgress());
                                if (rangeInfo != null && setProgressAction != null) {
                                    float max = RangesKt.coerceAtLeast(rangeInfo.getRange().getEndInclusive().floatValue(), rangeInfo.getRange().getStart().floatValue());
                                    float min = RangesKt.coerceAtMost(rangeInfo.getRange().getStart().floatValue(), rangeInfo.getRange().getEndInclusive().floatValue());
                                    float increment = rangeInfo.getSteps() > 0 ? (max - min) / (rangeInfo.getSteps() + 1) : (max - min) / 20;
                                    if (scrollBackward) {
                                        increment = -increment;
                                    }
                                    Function1 function18 = (Function1) setProgressAction.getAction();
                                    if (function18 != null) {
                                        return ((Boolean) function18.invoke(Float.valueOf(rangeInfo.getCurrent() + increment))).booleanValue();
                                    }
                                    return false;
                                }
                            }
                            long viewport = LayoutCoordinatesKt.boundsInParent(node.getLayoutInfo().getCoordinates()).m2764getSizeNHjbRc();
                            AccessibilityAction scrollAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getScrollBy());
                            if (scrollAction == null) {
                                return false;
                            }
                            ScrollAxisRange xScrollState = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange());
                            if (xScrollState != null && scrollHorizontal) {
                                float amountToScroll = Size.m2800getWidthimpl(viewport);
                                if (scrollLeft || scrollBackward) {
                                    amountToScroll = -amountToScroll;
                                }
                                if (xScrollState.getReverseScrolling()) {
                                    amountToScroll = -amountToScroll;
                                }
                                if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(node) && (scrollLeft || scrollRight)) {
                                    amountToScroll = -amountToScroll;
                                }
                                if (performActionHelper$canScroll(xScrollState, amountToScroll)) {
                                    Function2 function19 = (Function2) scrollAction.getAction();
                                    if (function19 != null) {
                                        return ((Boolean) function19.invoke(Float.valueOf(amountToScroll), Float.valueOf(0.0f))).booleanValue();
                                    }
                                    return false;
                                }
                            }
                            ScrollAxisRange yScrollState = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange());
                            if (yScrollState == null || !scrollVertical) {
                                return false;
                            }
                            float amountToScroll2 = Size.m2797getHeightimpl(viewport);
                            if (scrollUp || scrollBackward) {
                                amountToScroll2 = -amountToScroll2;
                            }
                            if (yScrollState.getReverseScrolling()) {
                                amountToScroll2 = -amountToScroll2;
                            }
                            if (performActionHelper$canScroll(yScrollState, amountToScroll2) && (function4 = (Function2) scrollAction.getAction()) != null) {
                                return ((Boolean) function4.invoke(Float.valueOf(0.0f), Float.valueOf(amountToScroll2))).booleanValue();
                            }
                            return false;
                        case 32768:
                            AccessibilityAction accessibilityAction6 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getPasteText());
                            if (accessibilityAction6 == null || (function5 = (Function0) accessibilityAction6.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function5.invoke()).booleanValue();
                        case 65536:
                            AccessibilityAction accessibilityAction7 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getCutText());
                            if (accessibilityAction7 == null || (function6 = (Function0) accessibilityAction7.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function6.invoke()).booleanValue();
                        case 262144:
                            AccessibilityAction accessibilityAction8 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getExpand());
                            if (accessibilityAction8 == null || (function7 = (Function0) accessibilityAction8.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function7.invoke()).booleanValue();
                        case 524288:
                            AccessibilityAction accessibilityAction9 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getCollapse());
                            if (accessibilityAction9 == null || (function8 = (Function0) accessibilityAction9.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function8.invoke()).booleanValue();
                        case 1048576:
                            AccessibilityAction accessibilityAction10 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getDismiss());
                            if (accessibilityAction10 == null || (function9 = (Function0) accessibilityAction10.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function9.invoke()).booleanValue();
                        case 2097152:
                            Object string = arguments != null ? arguments.getString(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE) : null;
                            AccessibilityAction accessibilityAction11 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetText());
                            if (accessibilityAction11 == null || (function10 = (Function1) accessibilityAction11.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function10.invoke(new AnnotatedString(string == null ? "" : string, null, null, 6, null))).booleanValue();
                        case android.R.id.accessibilityActionShowOnScreen:
                            SemanticsNode scrollableAncestor = node.getParent();
                            AccessibilityAction scrollAction2 = (scrollableAncestor == null || (config2 = scrollableAncestor.getConfig()) == null) ? null : (AccessibilityAction) SemanticsConfigurationKt.getOrNull(config2, SemanticsActions.INSTANCE.getScrollBy());
                            while (scrollableAncestor != null && scrollAction2 == null) {
                                scrollableAncestor = scrollableAncestor.getParent();
                                scrollAction2 = (scrollableAncestor == null || (config = scrollableAncestor.getConfig()) == null) ? null : (AccessibilityAction) SemanticsConfigurationKt.getOrNull(config, SemanticsActions.INSTANCE.getScrollBy());
                            }
                            if (scrollableAncestor == null) {
                                return false;
                            }
                            androidx.compose.ui.geometry.Rect viewportInParent = LayoutCoordinatesKt.boundsInParent(scrollableAncestor.getLayoutInfo().getCoordinates());
                            LayoutCoordinates parentLayoutCoordinates = scrollableAncestor.getLayoutInfo().getCoordinates().getParentLayoutCoordinates();
                            long parentInRoot = parentLayoutCoordinates != null ? LayoutCoordinatesKt.positionInRoot(parentLayoutCoordinates) : Offset.INSTANCE.m2747getZeroF1C5BW0();
                            androidx.compose.ui.geometry.Rect viewport2 = viewportInParent.m2768translatek4lQ0M(parentInRoot);
                            androidx.compose.ui.geometry.Rect target = RectKt.m2771Recttz77jQw(node.m4618getPositionInRootF1C5BW0(), IntSizeKt.m5444toSizeozmzZPI(node.m4620getSizeYbymL2g()));
                            ScrollAxisRange xScrollState2 = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(scrollableAncestor.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange());
                            ScrollAxisRange yScrollState2 = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(scrollableAncestor.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange());
                            float dx = performActionHelper$scrollDelta(target.getLeft() - viewport2.getLeft(), target.getRight() - viewport2.getRight());
                            if (xScrollState2 != null && xScrollState2.getReverseScrolling()) {
                                dx = -dx;
                            }
                            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(node)) {
                                dx = -dx;
                            }
                            float dy = performActionHelper$scrollDelta(target.getTop() - viewport2.getTop(), target.getBottom() - viewport2.getBottom());
                            if (yScrollState2 != null && yScrollState2.getReverseScrolling()) {
                                dy = -dy;
                            }
                            if (scrollAction2 == null || (function11 = (Function2) scrollAction2.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function11.invoke(Float.valueOf(dx), Float.valueOf(dy))).booleanValue();
                        case android.R.id.accessibilityActionSetProgress:
                            if (arguments == null || !arguments.containsKey(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE) || (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetProgress())) == null || (function12 = (Function1) accessibilityAction.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function12.invoke(Float.valueOf(arguments.getFloat(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE)))).booleanValue();
                        case android.R.id.accessibilityActionPageUp:
                            AccessibilityAction pageAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageUp());
                            if (pageAction == null || (function13 = (Function0) pageAction.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function13.invoke()).booleanValue();
                        case android.R.id.accessibilityActionPageDown:
                            AccessibilityAction pageAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageDown());
                            if (pageAction2 == null || (function14 = (Function0) pageAction2.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function14.invoke()).booleanValue();
                        case android.R.id.accessibilityActionPageLeft:
                            AccessibilityAction pageAction3 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageLeft());
                            if (pageAction3 == null || (function15 = (Function0) pageAction3.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function15.invoke()).booleanValue();
                        case android.R.id.accessibilityActionPageRight:
                            AccessibilityAction pageAction4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageRight());
                            if (pageAction4 == null || (function16 = (Function0) pageAction4.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function16.invoke()).booleanValue();
                        case android.R.id.accessibilityActionImeEnter:
                            AccessibilityAction accessibilityAction12 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getPerformImeAction());
                            if (accessibilityAction12 == null || (function17 = (Function0) accessibilityAction12.getAction()) == null) {
                                return false;
                            }
                            return ((Boolean) function17.invoke()).booleanValue();
                        default:
                            SparseArrayCompat<CharSequence> sparseArrayCompat = this.actionIdToLabel.get(virtualViewId);
                            if (sparseArrayCompat == null || (label = sparseArrayCompat.get(action)) == null || (customActions = (List) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsActions.INSTANCE.getCustomActions())) == null) {
                                return false;
                            }
                            int size = customActions.size();
                            for (int index$iv = 0; index$iv < size; index$iv++) {
                                Object item$iv = customActions.get(index$iv);
                                CustomAccessibilityAction customAction = (CustomAccessibilityAction) item$iv;
                                if (Intrinsics.areEqual(customAction.getLabel(), label)) {
                                    return customAction.getAction().invoke().booleanValue();
                                }
                            }
                            return false;
                    }
            }
        }
        return false;
    }

    private static final boolean performActionHelper$canScroll(ScrollAxisRange $this$performActionHelper_u24canScroll, float amount) {
        return (amount < 0.0f && $this$performActionHelper_u24canScroll.getValue().invoke().floatValue() > 0.0f) || (amount > 0.0f && $this$performActionHelper_u24canScroll.getValue().invoke().floatValue() < $this$performActionHelper_u24canScroll.getMaxValue().invoke().floatValue());
    }

    private static final float performActionHelper$scrollDelta(float a, float b) {
        if (Math.signum(a) == Math.signum(b)) {
            return Math.abs(a) < Math.abs(b) ? a : b;
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addExtraDataToAccessibilityNodeInfoHelper(int virtualViewId, AccessibilityNodeInfo info, String extraDataKey, Bundle arguments) {
        SemanticsNode node;
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes$ui_release().get(Integer.valueOf(virtualViewId));
        if (semanticsNodeWithAdjustedBounds == null || (node = semanticsNodeWithAdjustedBounds.getSemanticsNode()) == null) {
            return;
        }
        String text = getIterableTextForAccessibility(node);
        if (Intrinsics.areEqual(extraDataKey, this.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL)) {
            Integer it = this.idToBeforeMap.get(Integer.valueOf(virtualViewId));
            if (it != null) {
                info.getExtras().putInt(extraDataKey, it.intValue());
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(extraDataKey, this.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL)) {
            Integer it2 = this.idToAfterMap.get(Integer.valueOf(virtualViewId));
            if (it2 != null) {
                info.getExtras().putInt(extraDataKey, it2.intValue());
                return;
            }
            return;
        }
        if (node.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult()) && arguments != null && Intrinsics.areEqual(extraDataKey, "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY")) {
            int positionInfoStartIndex = arguments.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", -1);
            int positionInfoLength = arguments.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", -1);
            if (positionInfoLength > 0 && positionInfoStartIndex >= 0) {
                if (positionInfoStartIndex < (text != null ? text.length() : Integer.MAX_VALUE)) {
                    ArrayList arrayList = new ArrayList();
                    Function1 function1 = (Function1) ((AccessibilityAction) node.getUnmergedConfig().get(SemanticsActions.INSTANCE.getGetTextLayoutResult())).getAction();
                    Boolean getLayoutResult = function1 != null ? (Boolean) function1.invoke(arrayList) : null;
                    if (Intrinsics.areEqual((Object) getLayoutResult, (Object) true)) {
                        TextLayoutResult textLayoutResult = (TextLayoutResult) arrayList.get(0);
                        List boundingRects = new ArrayList();
                        for (int i = 0; i < positionInfoLength; i++) {
                            if (positionInfoStartIndex + i >= textLayoutResult.getLayoutInput().getText().length()) {
                                boundingRects.add(null);
                            } else {
                                androidx.compose.ui.geometry.Rect bounds = textLayoutResult.getBoundingBox(positionInfoStartIndex + i);
                                RectF boundsOnScreen = toScreenCoords(node, bounds);
                                boundingRects.add(boundsOnScreen);
                            }
                        }
                        List $this$toTypedArray$iv = boundingRects;
                        info.getExtras().putParcelableArray(extraDataKey, (Parcelable[]) $this$toTypedArray$iv.toArray(new RectF[0]));
                        return;
                    }
                    return;
                }
            }
            Log.e(LogTag, "Invalid arguments for accessibility character locations");
            return;
        }
        if (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTestTag()) && arguments != null && Intrinsics.areEqual(extraDataKey, ExtraDataTestTagKey)) {
            String testTag = (String) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getTestTag());
            if (testTag != null) {
                info.getExtras().putCharSequence(extraDataKey, testTag);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(extraDataKey, ExtraDataIdKey)) {
            info.getExtras().putInt(extraDataKey, node.getId());
        }
    }

    private final RectF toScreenCoords(SemanticsNode textNode, androidx.compose.ui.geometry.Rect bounds) {
        androidx.compose.ui.geometry.Rect visibleBounds;
        if (textNode == null) {
            return null;
        }
        androidx.compose.ui.geometry.Rect boundsInRoot = bounds.m2768translatek4lQ0M(textNode.m4618getPositionInRootF1C5BW0());
        androidx.compose.ui.geometry.Rect textNodeBoundsInRoot = textNode.getBoundsInRoot();
        if (boundsInRoot.overlaps(textNodeBoundsInRoot)) {
            visibleBounds = boundsInRoot.intersect(textNodeBoundsInRoot);
        } else {
            visibleBounds = null;
        }
        if (visibleBounds == null) {
            return null;
        }
        long topLeftInScreen = this.view.mo4187localToScreenMKHz9U(OffsetKt.Offset(visibleBounds.getLeft(), visibleBounds.getTop()));
        long bottomRightInScreen = this.view.mo4187localToScreenMKHz9U(OffsetKt.Offset(visibleBounds.getRight(), visibleBounds.getBottom()));
        return new RectF(Offset.m2731getXimpl(topLeftInScreen), Offset.m2732getYimpl(topLeftInScreen), Offset.m2731getXimpl(bottomRightInScreen), Offset.m2732getYimpl(bottomRightInScreen));
    }

    public final boolean dispatchHoverEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (!isTouchExplorationEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case 7:
            case 9:
                int virtualViewId = hitTestSemanticsAt$ui_release(event.getX(), event.getY());
                boolean handled = this.view.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(event);
                updateHoveredVirtualView(virtualViewId);
                if (virtualViewId == Integer.MIN_VALUE) {
                    return handled;
                }
                return true;
            case 8:
            default:
                return false;
            case 10:
                if (this.hoveredVirtualViewId != Integer.MIN_VALUE) {
                    updateHoveredVirtualView(Integer.MIN_VALUE);
                    return true;
                }
                return this.view.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(event);
        }
    }

    public final int hitTestSemanticsAt$ui_release(float x, float y) {
        NodeChain nodes;
        Owner.CC.measureAndLayout$default(this.view, false, 1, null);
        HitTestResult hitSemanticsEntities = new HitTestResult();
        LayoutNode.m4365hitTestSemanticsM_7yMNQ$ui_release$default(this.view.getRoot(), OffsetKt.Offset(x, y), hitSemanticsEntities, false, false, 12, null);
        Modifier.Node node = (Modifier.Node) CollectionsKt.lastOrNull((List) hitSemanticsEntities);
        LayoutNode layoutNode = node != null ? DelegatableNodeKt.requireLayoutNode(node) : null;
        if (!((layoutNode == null || (nodes = layoutNode.getNodes()) == null || !nodes.m4406hasH91voCI$ui_release(NodeKind.m4443constructorimpl(8))) ? false : true)) {
            return Integer.MIN_VALUE;
        }
        SemanticsNode semanticsNode = SemanticsNodeKt.SemanticsNode(layoutNode, false);
        if (!AndroidComposeViewAccessibilityDelegateCompat_androidKt.isVisible(semanticsNode)) {
            return Integer.MIN_VALUE;
        }
        AndroidViewHolder androidView = this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(layoutNode);
        if (androidView != null) {
            return Integer.MIN_VALUE;
        }
        int virtualViewId = semanticsNodeIdToAccessibilityVirtualNodeId(layoutNode.getSemanticsId());
        return virtualViewId;
    }

    private final void updateHoveredVirtualView(int virtualViewId) {
        if (this.hoveredVirtualViewId == virtualViewId) {
            return;
        }
        int previousVirtualViewId = this.hoveredVirtualViewId;
        this.hoveredVirtualViewId = virtualViewId;
        sendEventForVirtualView$default(this, virtualViewId, 128, null, null, 12, null);
        sendEventForVirtualView$default(this, previousVirtualViewId, 256, null, null, 12, null);
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
        Intrinsics.checkNotNullParameter(host, "host");
        return this.nodeProvider;
    }

    private final <T extends CharSequence> T trimToSize(T text, int size) {
        boolean z = true;
        if (!(size > 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int i = size;
        if (text != null && text.length() != 0) {
            z = false;
        }
        if (z || text.length() <= size) {
            return text;
        }
        if (Character.isHighSurrogate(text.charAt(size - 1)) && Character.isLowSurrogate(text.charAt(size))) {
            i = size - 1;
        }
        T t = (T) text.subSequence(0, i);
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.trimToSize");
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void semanticsChangeChecker$lambda$45(AndroidComposeViewAccessibilityDelegateCompat this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Owner.CC.measureAndLayout$default(this$0.view, false, 1, null);
        this$0.checkForSemanticsChanges();
        this$0.checkingForSemanticsChanges = false;
    }

    public final void onSemanticsChange$ui_release() {
        this.currentSemanticsNodesInvalidated = true;
        if (isEnabled$ui_release() && !this.checkingForSemanticsChanges) {
            this.checkingForSemanticsChanges = true;
            this.handler.post(this.semanticsChangeChecker);
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0075 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:23:0x0076  */
    /* JADX WARN: Code duplicated, block: B:26:0x0084 A[Catch: all -> 0x00e7, TryCatch #0 {all -> 0x00e7, blocks: (B:24:0x007c, B:26:0x0084, B:28:0x008d, B:29:0x0090, B:31:0x0096, B:33:0x00a0, B:34:0x00b2, B:36:0x00b9, B:37:0x00c2), top: B:49:0x007c }] */
    /* JADX WARN: Code duplicated, block: B:28:0x008d A[Catch: all -> 0x00e7, TryCatch #0 {all -> 0x00e7, blocks: (B:24:0x007c, B:26:0x0084, B:28:0x008d, B:29:0x0090, B:31:0x0096, B:33:0x00a0, B:34:0x00b2, B:36:0x00b9, B:37:0x00c2), top: B:49:0x007c }] */
    /* JADX WARN: Code duplicated, block: B:31:0x0096 A[Catch: all -> 0x00e7, TryCatch #0 {all -> 0x00e7, blocks: (B:24:0x007c, B:26:0x0084, B:28:0x008d, B:29:0x0090, B:31:0x0096, B:33:0x00a0, B:34:0x00b2, B:36:0x00b9, B:37:0x00c2), top: B:49:0x007c }] */
    /* JADX WARN: Code duplicated, block: B:33:0x00a0 A[Catch: all -> 0x00e7, LOOP:0: B:32:0x009d->B:33:0x00a0, LOOP_END, TryCatch #0 {all -> 0x00e7, blocks: (B:24:0x007c, B:26:0x0084, B:28:0x008d, B:29:0x0090, B:31:0x0096, B:33:0x00a0, B:34:0x00b2, B:36:0x00b9, B:37:0x00c2), top: B:49:0x007c }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00b9 A[Catch: all -> 0x00e7, TryCatch #0 {all -> 0x00e7, blocks: (B:24:0x007c, B:26:0x0084, B:28:0x008d, B:29:0x0090, B:31:0x0096, B:33:0x00a0, B:34:0x00b2, B:36:0x00b9, B:37:0x00c2), top: B:49:0x007c }] */
    /* JADX WARN: Code duplicated, block: B:39:0x00d8 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:40:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00d9 -> B:20:0x0067). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object boundsUpdatesEventLoop(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.boundsUpdatesEventLoop(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void onLayoutChange$ui_release(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.currentSemanticsNodesInvalidated = true;
        if (!isEnabled$ui_release()) {
            return;
        }
        notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
    }

    private final void notifySubtreeAccessibilityStateChangedIfNeeded(LayoutNode layoutNode) {
        if (this.subtreeChangedLayoutNodes.add(layoutNode)) {
            this.boundsUpdateChannel.mo7066trySendJP2dKIU(Unit.INSTANCE);
        }
    }

    private final void sendSubtreeChangeAccessibilityEvents(LayoutNode layoutNode, ArraySet<Integer> subtreeChangedSemanticsNodesIds) {
        SemanticsConfiguration config;
        LayoutNode it;
        if (layoutNode.isAttached() && !this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().containsKey(layoutNode)) {
            LayoutNode layoutNodeFindClosestParentNode = layoutNode.getNodes().m4406hasH91voCI$ui_release(NodeKind.m4443constructorimpl(8)) ? layoutNode : AndroidComposeViewAccessibilityDelegateCompat_androidKt.findClosestParentNode(layoutNode, new Function1<LayoutNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(LayoutNode it2) {
                    Intrinsics.checkNotNullParameter(it2, "it");
                    return Boolean.valueOf(it2.getNodes().m4406hasH91voCI$ui_release(NodeKind.m4443constructorimpl(8)));
                }
            });
            if (layoutNodeFindClosestParentNode == null || (config = layoutNodeFindClosestParentNode.getCollapsedSemantics$ui_release()) == null) {
                return;
            }
            if (!config.getIsMergingSemanticsOfDescendants() && (it = AndroidComposeViewAccessibilityDelegateCompat_androidKt.findClosestParentNode(layoutNodeFindClosestParentNode, new Function1<LayoutNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendSubtreeChangeAccessibilityEvents.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(LayoutNode it2) {
                    Intrinsics.checkNotNullParameter(it2, "it");
                    SemanticsConfiguration collapsedSemantics$ui_release = it2.getCollapsedSemantics$ui_release();
                    boolean z = false;
                    if (collapsedSemantics$ui_release != null && collapsedSemantics$ui_release.getIsMergingSemanticsOfDescendants()) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            })) != null) {
                layoutNodeFindClosestParentNode = it;
            }
            if (layoutNodeFindClosestParentNode != null) {
                int id = layoutNodeFindClosestParentNode.getSemanticsId();
                if (subtreeChangedSemanticsNodesIds.add(Integer.valueOf(id))) {
                    sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(id), 2048, 1, null, 8, null);
                }
            }
        }
    }

    private final void checkForSemanticsChanges() {
        sendAccessibilitySemanticsStructureChangeEvents(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), this.previousSemanticsRoot);
        sendContentCaptureSemanticsStructureChangeEvents$ui_release(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), this.previousSemanticsRoot);
        sendSemanticsPropertyChangeEvents$ui_release(getCurrentSemanticsNodes$ui_release());
        updateSemanticsNodesCopyAndPanes();
    }

    private final void updateSemanticsNodesCopyAndPanes() {
        SemanticsConfiguration unmergedConfig;
        ArraySet<? extends Integer> arraySet = new ArraySet<>();
        for (Integer id : this.paneDisplayed) {
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes$ui_release().get(id);
            String str = null;
            SemanticsNode currentNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.getSemanticsNode() : null;
            if (currentNode == null || !AndroidComposeViewAccessibilityDelegateCompat_androidKt.hasPaneTitle(currentNode)) {
                arraySet.add(id);
                Intrinsics.checkNotNullExpressionValue(id, "id");
                int iIntValue = id.intValue();
                SemanticsNodeCopy semanticsNodeCopy = this.previousSemanticsNodes.get(id);
                if (semanticsNodeCopy != null && (unmergedConfig = semanticsNodeCopy.getUnmergedConfig()) != null) {
                    str = (String) SemanticsConfigurationKt.getOrNull(unmergedConfig, SemanticsProperties.INSTANCE.getPaneTitle());
                }
                sendPaneChangeEvents(iIntValue, 32, str);
            }
        }
        this.paneDisplayed.removeAll(arraySet);
        this.previousSemanticsNodes.clear();
        for (Map.Entry<Integer, SemanticsNodeWithAdjustedBounds> entry : getCurrentSemanticsNodes$ui_release().entrySet()) {
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.hasPaneTitle(entry.getValue().getSemanticsNode()) && this.paneDisplayed.add(entry.getKey())) {
                sendPaneChangeEvents(entry.getKey().intValue(), 16, (String) entry.getValue().getSemanticsNode().getUnmergedConfig().get(SemanticsProperties.INSTANCE.getPaneTitle()));
            }
            this.previousSemanticsNodes.put(entry.getKey(), new SemanticsNodeCopy(entry.getValue().getSemanticsNode(), getCurrentSemanticsNodes$ui_release()));
        }
        this.previousSemanticsRoot = new SemanticsNodeCopy(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), getCurrentSemanticsNodes$ui_release());
    }

    public final void sendSemanticsPropertyChangeEvents$ui_release(Map<Integer, SemanticsNodeWithAdjustedBounds> newSemanticsNodes) {
        boolean newlyObservingScroll;
        int endCount;
        AccessibilityEvent $this$sendSemanticsPropertyChangeEvents_u24lambda_u2449;
        String text;
        Map<Integer, SemanticsNodeWithAdjustedBounds> newSemanticsNodes2 = newSemanticsNodes;
        Intrinsics.checkNotNullParameter(newSemanticsNodes2, "newSemanticsNodes");
        ArrayList oldScrollObservationScopes = new ArrayList(this.scrollObservationScopes);
        this.scrollObservationScopes.clear();
        Iterator<Integer> it = newSemanticsNodes.keySet().iterator();
        while (it.hasNext()) {
            int id = it.next().intValue();
            SemanticsNodeCopy oldNode = this.previousSemanticsNodes.get(Integer.valueOf(id));
            if (oldNode != null) {
                SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = newSemanticsNodes2.get(Integer.valueOf(id));
                SemanticsNode newNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.getSemanticsNode() : null;
                Intrinsics.checkNotNull(newNode);
                Iterator<Map.Entry<? extends SemanticsPropertyKey<?>, ? extends Object>> it2 = newNode.getUnmergedConfig().iterator();
                boolean propertyChanged = false;
                while (true) {
                    boolean propertyChanged2 = it2.hasNext();
                    if (!propertyChanged2) {
                        break;
                    }
                    Map.Entry<? extends SemanticsPropertyKey<?>, ? extends Object> next = it2.next();
                    if (Intrinsics.areEqual(next.getKey(), SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange()) || Intrinsics.areEqual(next.getKey(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange())) {
                        boolean newlyObservingScroll2 = registerScrollingId(id, oldScrollObservationScopes);
                        newlyObservingScroll = newlyObservingScroll2;
                    } else {
                        newlyObservingScroll = false;
                    }
                    if (newlyObservingScroll || !Intrinsics.areEqual(next.getValue(), SemanticsConfigurationKt.getOrNull(oldNode.getUnmergedConfig(), next.getKey()))) {
                        SemanticsPropertyKey<?> key = next.getKey();
                        if (Intrinsics.areEqual(key, SemanticsProperties.INSTANCE.getText())) {
                            List list = (List) SemanticsConfigurationKt.getOrNull(oldNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
                            AnnotatedString oldText = list != null ? (AnnotatedString) CollectionsKt.firstOrNull(list) : null;
                            List list2 = (List) SemanticsConfigurationKt.getOrNull(newNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
                            AnnotatedString newText = list2 != null ? (AnnotatedString) CollectionsKt.firstOrNull(list2) : null;
                            if (!Intrinsics.areEqual(oldText, newText)) {
                                sendContentCaptureTextUpdateEvent(newNode.getId(), String.valueOf(newText));
                            }
                        } else if (Intrinsics.areEqual(key, SemanticsProperties.INSTANCE.getPaneTitle())) {
                            Object value = next.getValue();
                            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.String");
                            String paneTitle = (String) value;
                            if (oldNode.hasPaneTitle()) {
                                sendPaneChangeEvents(id, 8, paneTitle);
                            }
                        } else {
                            if (Intrinsics.areEqual(key, SemanticsProperties.INSTANCE.getStateDescription()) ? true : Intrinsics.areEqual(key, SemanticsProperties.INSTANCE.getToggleableState())) {
                                sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(id), 2048, 64, null, 8, null);
                                sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(id), 2048, 0, null, 8, null);
                            } else if (Intrinsics.areEqual(key, SemanticsProperties.INSTANCE.getProgressBarRangeInfo())) {
                                sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(id), 2048, 64, null, 8, null);
                                sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(id), 2048, 0, null, 8, null);
                            } else if (Intrinsics.areEqual(key, SemanticsProperties.INSTANCE.getSelected())) {
                                Role role = (Role) SemanticsConfigurationKt.getOrNull(newNode.getConfig(), SemanticsProperties.INSTANCE.getRole());
                                if (!(role == null ? false : Role.m4606equalsimpl0(role.getValue(), Role.INSTANCE.m4616getTabo7Vup1c()))) {
                                    sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(id), 2048, 64, null, 8, null);
                                    sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(id), 2048, 0, null, 8, null);
                                } else if (Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(newNode.getConfig(), SemanticsProperties.INSTANCE.getSelected()), (Object) true)) {
                                    AccessibilityEvent event = createEvent$ui_release(semanticsNodeIdToAccessibilityVirtualNodeId(id), 4);
                                    SemanticsNode mergedNode = newNode.copyWithMergingEnabled$ui_release();
                                    List list3 = (List) SemanticsConfigurationKt.getOrNull(mergedNode.getConfig(), SemanticsProperties.INSTANCE.getContentDescription());
                                    String contentDescription = list3 != null ? TempListUtilsKt.fastJoinToString$default(list3, ",", null, null, 0, null, null, 62, null) : null;
                                    List list4 = (List) SemanticsConfigurationKt.getOrNull(mergedNode.getConfig(), SemanticsProperties.INSTANCE.getText());
                                    String text2 = list4 != null ? TempListUtilsKt.fastJoinToString$default(list4, ",", null, null, 0, null, null, 62, null) : null;
                                    if (contentDescription != null) {
                                        String it3 = contentDescription;
                                        event.setContentDescription(it3);
                                    }
                                    if (text2 != null) {
                                        String it4 = text2;
                                        event.getText().add(it4);
                                    }
                                    sendEvent(event);
                                } else {
                                    sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(id), 2048, 0, null, 8, null);
                                }
                            } else if (Intrinsics.areEqual(key, SemanticsProperties.INSTANCE.getContentDescription())) {
                                int iSemanticsNodeIdToAccessibilityVirtualNodeId = semanticsNodeIdToAccessibilityVirtualNodeId(id);
                                Object value2 = next.getValue();
                                Intrinsics.checkNotNull(value2, "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
                                sendEventForVirtualView(iSemanticsNodeIdToAccessibilityVirtualNodeId, 2048, 4, (List) value2);
                            } else {
                                String str = "";
                                if (Intrinsics.areEqual(key, SemanticsProperties.INSTANCE.getEditableText())) {
                                    if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.isTextField(newNode)) {
                                        AnnotatedString textForTextField = getTextForTextField(oldNode.getUnmergedConfig());
                                        CharSequence oldText2 = textForTextField != null ? textForTextField : "";
                                        AnnotatedString textForTextField2 = getTextForTextField(newNode.getUnmergedConfig());
                                        CharSequence newText2 = textForTextField2 != null ? textForTextField2 : "";
                                        CharSequence trimmedNewText = trimToSize(newText2, ParcelSafeTextLength);
                                        int startCount = 0;
                                        int endCount2 = 0;
                                        int oldTextLen = oldText2.length();
                                        int newTextLen = newText2.length();
                                        int minLength = RangesKt.coerceAtMost(oldTextLen, newTextLen);
                                        while (true) {
                                            if (startCount >= minLength) {
                                                endCount = endCount2;
                                                break;
                                            }
                                            int iCharAt = oldText2.charAt(startCount);
                                            endCount = endCount2;
                                            int endCount3 = newText2.charAt(startCount);
                                            if (iCharAt != endCount3) {
                                                break;
                                            }
                                            startCount++;
                                            endCount2 = endCount;
                                        }
                                        int endCount4 = endCount;
                                        while (endCount4 < minLength - startCount) {
                                            int minLength2 = minLength;
                                            if (oldText2.charAt((oldTextLen - 1) - endCount4) != newText2.charAt((newTextLen - 1) - endCount4)) {
                                                break;
                                            }
                                            endCount4++;
                                            minLength = minLength2;
                                        }
                                        int removedCount = (oldTextLen - endCount4) - startCount;
                                        int addedCount = (newTextLen - endCount4) - startCount;
                                        boolean becamePasswordNode = AndroidComposeViewAccessibilityDelegateCompat_androidKt.isTextField(oldNode.getSemanticsNode()) && !AndroidComposeViewAccessibilityDelegateCompat_androidKt.isPassword(oldNode.getSemanticsNode()) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.isPassword(newNode);
                                        boolean becameNotPasswordNode = AndroidComposeViewAccessibilityDelegateCompat_androidKt.isTextField(oldNode.getSemanticsNode()) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.isPassword(oldNode.getSemanticsNode()) && !AndroidComposeViewAccessibilityDelegateCompat_androidKt.isPassword(newNode);
                                        if (becamePasswordNode || becameNotPasswordNode) {
                                            $this$sendSemanticsPropertyChangeEvents_u24lambda_u2449 = createTextSelectionChangedEvent(semanticsNodeIdToAccessibilityVirtualNodeId(id), 0, 0, Integer.valueOf(newTextLen), trimmedNewText);
                                        } else {
                                            $this$sendSemanticsPropertyChangeEvents_u24lambda_u2449 = createEvent$ui_release(semanticsNodeIdToAccessibilityVirtualNodeId(id), 16);
                                            $this$sendSemanticsPropertyChangeEvents_u24lambda_u2449.setFromIndex(startCount);
                                            $this$sendSemanticsPropertyChangeEvents_u24lambda_u2449.setRemovedCount(removedCount);
                                            $this$sendSemanticsPropertyChangeEvents_u24lambda_u2449.setAddedCount(addedCount);
                                            $this$sendSemanticsPropertyChangeEvents_u24lambda_u2449.setBeforeText(oldText2);
                                            $this$sendSemanticsPropertyChangeEvents_u24lambda_u2449.getText().add(trimmedNewText);
                                        }
                                        AccessibilityEvent event2 = $this$sendSemanticsPropertyChangeEvents_u24lambda_u2449;
                                        event2.setClassName(TextFieldClassName);
                                        sendEvent(event2);
                                        if (becamePasswordNode || becameNotPasswordNode) {
                                            long textRange = ((TextRange) newNode.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getTextSelectionRange())).getPackedValue();
                                            event2.setFromIndex(TextRange.m4768getStartimpl(textRange));
                                            event2.setToIndex(TextRange.m4763getEndimpl(textRange));
                                            sendEvent(event2);
                                        }
                                    } else {
                                        sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(id), 2048, 2, null, 8, null);
                                    }
                                } else if (Intrinsics.areEqual(key, SemanticsProperties.INSTANCE.getTextSelectionRange())) {
                                    AnnotatedString textForTextField3 = getTextForTextField(newNode.getUnmergedConfig());
                                    if (textForTextField3 != null && (text = textForTextField3.getText()) != null) {
                                        str = text;
                                    }
                                    String newText3 = str;
                                    long textRange2 = ((TextRange) newNode.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getTextSelectionRange())).getPackedValue();
                                    sendEvent(createTextSelectionChangedEvent(semanticsNodeIdToAccessibilityVirtualNodeId(id), Integer.valueOf(TextRange.m4768getStartimpl(textRange2)), Integer.valueOf(TextRange.m4763getEndimpl(textRange2)), Integer.valueOf(newText3.length()), trimToSize(newText3, ParcelSafeTextLength)));
                                    sendPendingTextTraversedAtGranularityEvent(newNode.getId());
                                } else {
                                    if (Intrinsics.areEqual(key, SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange()) ? true : Intrinsics.areEqual(key, SemanticsProperties.INSTANCE.getVerticalScrollAxisRange())) {
                                        notifySubtreeAccessibilityStateChangedIfNeeded(newNode.getLayoutNode());
                                        ScrollObservationScope scope = AndroidComposeViewAccessibilityDelegateCompat_androidKt.findById(this.scrollObservationScopes, id);
                                        Intrinsics.checkNotNull(scope);
                                        scope.setHorizontalScrollAxisRange((ScrollAxisRange) SemanticsConfigurationKt.getOrNull(newNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange()));
                                        scope.setVerticalScrollAxisRange((ScrollAxisRange) SemanticsConfigurationKt.getOrNull(newNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange()));
                                        sendScrollEventIfNeeded(scope);
                                    } else if (Intrinsics.areEqual(key, SemanticsProperties.INSTANCE.getFocused())) {
                                        Object value3 = next.getValue();
                                        Intrinsics.checkNotNull(value3, "null cannot be cast to non-null type kotlin.Boolean");
                                        if (((Boolean) value3).booleanValue()) {
                                            sendEvent(createEvent$ui_release(semanticsNodeIdToAccessibilityVirtualNodeId(newNode.getId()), 8));
                                        }
                                        sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(newNode.getId()), 2048, 0, null, 8, null);
                                    } else if (Intrinsics.areEqual(key, SemanticsActions.INSTANCE.getCustomActions())) {
                                        List actions = (List) newNode.getUnmergedConfig().get(SemanticsActions.INSTANCE.getCustomActions());
                                        List oldActions = (List) SemanticsConfigurationKt.getOrNull(oldNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCustomActions());
                                        if (oldActions != null) {
                                            Set labels = new LinkedHashSet();
                                            List $this$fastForEach$iv = actions;
                                            int index$iv = 0;
                                            int size = $this$fastForEach$iv.size();
                                            while (index$iv < size) {
                                                Object item$iv = $this$fastForEach$iv.get(index$iv);
                                                CustomAccessibilityAction action = (CustomAccessibilityAction) item$iv;
                                                labels.add(action.getLabel());
                                                index$iv++;
                                                $this$fastForEach$iv = $this$fastForEach$iv;
                                            }
                                            Set oldLabels = new LinkedHashSet();
                                            int index$iv2 = 0;
                                            int size2 = oldActions.size();
                                            while (index$iv2 < size2) {
                                                Object item$iv2 = oldActions.get(index$iv2);
                                                CustomAccessibilityAction action2 = (CustomAccessibilityAction) item$iv2;
                                                oldLabels.add(action2.getLabel());
                                                index$iv2++;
                                                oldActions = oldActions;
                                            }
                                            propertyChanged = (labels.containsAll(oldLabels) && oldLabels.containsAll(labels)) ? false : true;
                                        } else if (!actions.isEmpty()) {
                                            propertyChanged = true;
                                        }
                                    } else if (next.getValue() instanceof AccessibilityAction) {
                                        Object value4 = next.getValue();
                                        Intrinsics.checkNotNull(value4, "null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>");
                                        propertyChanged = !AndroidComposeViewAccessibilityDelegateCompat_androidKt.accessibilityEquals((AccessibilityAction) value4, SemanticsConfigurationKt.getOrNull(oldNode.getUnmergedConfig(), next.getKey()));
                                    } else {
                                        propertyChanged = true;
                                    }
                                }
                            }
                        }
                    }
                }
                if (!propertyChanged) {
                    propertyChanged = AndroidComposeViewAccessibilityDelegateCompat_androidKt.propertiesDeleted(newNode, oldNode);
                }
                if (propertyChanged) {
                    sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(id), 2048, 0, null, 8, null);
                    newSemanticsNodes2 = newSemanticsNodes;
                } else {
                    newSemanticsNodes2 = newSemanticsNodes;
                }
            }
        }
    }

    private final void sendContentCaptureTextUpdateEvent(int id, String newText) {
        ContentCaptureSessionCompat session = this.contentCaptureSession;
        if (session == null || Build.VERSION.SDK_INT < 29) {
            return;
        }
        AutofillId autofillId = session.newAutofillId(id);
        if (autofillId == null) {
            throw new IllegalStateException("Invalid content capture ID".toString());
        }
        session.notifyViewTextChanged(autofillId, newText);
    }

    private final boolean registerScrollingId(int id, List<ScrollObservationScope> oldScrollObservationScopes) {
        ScrollObservationScope scrollObservationScope;
        boolean newlyObservingScroll = false;
        ScrollObservationScope oldScope = AndroidComposeViewAccessibilityDelegateCompat_androidKt.findById(oldScrollObservationScopes, id);
        if (oldScope != null) {
            scrollObservationScope = oldScope;
        } else {
            newlyObservingScroll = true;
            scrollObservationScope = new ScrollObservationScope(id, this.scrollObservationScopes, null, null, null, null);
        }
        ScrollObservationScope newScope = scrollObservationScope;
        this.scrollObservationScopes.add(newScope);
        return newlyObservingScroll;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendScrollEventIfNeeded(final ScrollObservationScope scrollObservationScope) {
        if (!scrollObservationScope.isValidOwnerScope()) {
            return;
        }
        this.view.getSnapshotObserver().observeReads$ui_release(scrollObservationScope, this.sendScrollEventIfNeededLambda, new Function0<Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendScrollEventIfNeeded.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code duplicated, block: B:21:0x0061  */
            /* JADX WARN: Code duplicated, block: B:23:0x008b  */
            /* JADX WARN: Code duplicated, block: B:25:0x00b1  */
            /* JADX WARN: Code duplicated, block: B:28:0x00db  */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                float deltaX;
                float deltaY;
                AccessibilityEvent event;
                ScrollAxisRange newXState = scrollObservationScope.getHorizontalScrollAxisRange();
                ScrollAxisRange newYState = scrollObservationScope.getVerticalScrollAxisRange();
                Float oldXValue = scrollObservationScope.getOldXValue();
                Float oldYValue = scrollObservationScope.getOldYValue();
                if (newXState != null && oldXValue != null) {
                    deltaX = newXState.getValue().invoke().floatValue() - oldXValue.floatValue();
                } else {
                    deltaX = 0.0f;
                }
                if (newYState != null && oldYValue != null) {
                    deltaY = newYState.getValue().invoke().floatValue() - oldYValue.floatValue();
                } else {
                    deltaY = 0.0f;
                }
                if (!(deltaX == 0.0f)) {
                    int virtualNodeId = this.semanticsNodeIdToAccessibilityVirtualNodeId(scrollObservationScope.getSemanticsNodeId());
                    AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(this, virtualNodeId, 2048, 1, null, 8, null);
                    event = this.createEvent$ui_release(virtualNodeId, 4096);
                    if (newXState != null) {
                        event.setScrollX((int) newXState.getValue().invoke().floatValue());
                        event.setMaxScrollX((int) newXState.getMaxValue().invoke().floatValue());
                    }
                    if (newYState != null) {
                        event.setScrollY((int) newYState.getValue().invoke().floatValue());
                        event.setMaxScrollY((int) newYState.getMaxValue().invoke().floatValue());
                    }
                    if (Build.VERSION.SDK_INT >= 28) {
                        Api28Impl.setScrollEventDelta(event, (int) deltaX, (int) deltaY);
                    }
                    this.sendEvent(event);
                } else {
                    if (!(deltaY == 0.0f)) {
                        int virtualNodeId2 = this.semanticsNodeIdToAccessibilityVirtualNodeId(scrollObservationScope.getSemanticsNodeId());
                        AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(this, virtualNodeId2, 2048, 1, null, 8, null);
                        event = this.createEvent$ui_release(virtualNodeId2, 4096);
                        if (newXState != null) {
                            event.setScrollX((int) newXState.getValue().invoke().floatValue());
                            event.setMaxScrollX((int) newXState.getMaxValue().invoke().floatValue());
                        }
                        if (newYState != null) {
                            event.setScrollY((int) newYState.getValue().invoke().floatValue());
                            event.setMaxScrollY((int) newYState.getMaxValue().invoke().floatValue());
                        }
                        if (Build.VERSION.SDK_INT >= 28) {
                            Api28Impl.setScrollEventDelta(event, (int) deltaX, (int) deltaY);
                        }
                        this.sendEvent(event);
                    }
                }
                if (newXState != null) {
                    scrollObservationScope.setOldXValue(newXState.getValue().invoke());
                }
                if (newYState != null) {
                    scrollObservationScope.setOldYValue(newYState.getValue().invoke());
                }
            }
        });
    }

    private final void sendPaneChangeEvents(int semanticsNodeId, int contentChangeType, String title) {
        AccessibilityEvent event = createEvent$ui_release(semanticsNodeIdToAccessibilityVirtualNodeId(semanticsNodeId), 32);
        event.setContentChangeTypes(contentChangeType);
        if (title != null) {
            event.getText().add(title);
        }
        sendEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ContentCaptureSessionCompat getContentCaptureSessionCompat(View $this$getContentCaptureSessionCompat) {
        ViewCompatShims.setImportantForContentCapture($this$getContentCaptureSessionCompat, 1);
        return ViewCompatShims.getContentCaptureSession($this$getContentCaptureSessionCompat);
    }

    private final ViewStructureCompat toViewStructure(SemanticsNode $this$toViewStructure) {
        AutofillIdCompat rootAutofillId;
        AutofillId parentAutofillId;
        String it;
        ContentCaptureSessionCompat session = this.contentCaptureSession;
        if (session == null || Build.VERSION.SDK_INT < 29 || (rootAutofillId = ViewCompatShims.getAutofillId(this.view)) == null) {
            return null;
        }
        SemanticsNode parentNode = $this$toViewStructure.getParent();
        if (parentNode != null) {
            parentAutofillId = session.newAutofillId(parentNode.getId());
            if (parentAutofillId == null) {
                return null;
            }
        } else {
            parentAutofillId = rootAutofillId.toAutofillId();
        }
        Intrinsics.checkNotNullExpressionValue(parentAutofillId, "if (parentNode != null) ….toAutofillId()\n        }");
        ViewStructureCompat structure = session.newVirtualViewStructure(parentAutofillId, $this$toViewStructure.getId());
        if (structure == null) {
            return null;
        }
        SemanticsConfiguration configuration = $this$toViewStructure.getUnmergedConfig();
        if (configuration.contains(SemanticsProperties.INSTANCE.getPassword())) {
            return null;
        }
        List it2 = (List) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getText());
        if (it2 != null) {
            structure.setClassName(TextClassName);
            structure.setText(TempListUtilsKt.fastJoinToString$default(it2, "\n", null, null, 0, null, null, 62, null));
        }
        AnnotatedString it3 = (AnnotatedString) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getEditableText());
        if (it3 != null) {
            structure.setClassName(TextFieldClassName);
            structure.setText(it3);
        }
        List it4 = (List) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getContentDescription());
        if (it4 != null) {
            structure.setContentDescription(TempListUtilsKt.fastJoinToString$default(it4, "\n", null, null, 0, null, null, 62, null));
        }
        Role role = (Role) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getRole());
        if (role != null && (it = AndroidComposeViewAccessibilityDelegateCompat_androidKt.m4529toLegacyClassNameV4PA4sw(role.getValue())) != null) {
            structure.setClassName(it);
        }
        androidx.compose.ui.geometry.Rect $this$toViewStructure_u24lambda_u2457 = $this$toViewStructure.getBoundsInWindow();
        structure.setDimens((int) $this$toViewStructure_u24lambda_u2457.getLeft(), (int) $this$toViewStructure_u24lambda_u2457.getTop(), 0, 0, (int) $this$toViewStructure_u24lambda_u2457.getWidth(), (int) $this$toViewStructure_u24lambda_u2457.getHeight());
        return structure;
    }

    private final void bufferContentCaptureViewAppeared(int virtualId, ViewStructureCompat viewStructure) {
        if (viewStructure == null) {
            return;
        }
        if (this.bufferedContentCaptureDisappearedNodes.contains(Integer.valueOf(virtualId))) {
            this.bufferedContentCaptureDisappearedNodes.remove(Integer.valueOf(virtualId));
        } else {
            this.bufferedContentCaptureAppearedNodes.put(Integer.valueOf(virtualId), viewStructure);
        }
    }

    private final void bufferContentCaptureViewDisappeared(int virtualId) {
        if (this.bufferedContentCaptureAppearedNodes.containsKey(Integer.valueOf(virtualId))) {
            this.bufferedContentCaptureAppearedNodes.remove(Integer.valueOf(virtualId));
        } else {
            this.bufferedContentCaptureDisappearedNodes.add(Integer.valueOf(virtualId));
        }
    }

    private final void notifyContentCaptureChanges() {
        ContentCaptureSessionCompat session = this.contentCaptureSession;
        if (session == null || Build.VERSION.SDK_INT < 29) {
            return;
        }
        if (!this.bufferedContentCaptureAppearedNodes.isEmpty()) {
            Collection<ViewStructureCompat> collectionValues = this.bufferedContentCaptureAppearedNodes.values();
            Intrinsics.checkNotNullExpressionValue(collectionValues, "bufferedContentCaptureAppearedNodes.values");
            List $this$fastMap$iv = CollectionsKt.toList(collectionValues);
            List target$iv = new ArrayList($this$fastMap$iv.size());
            int size = $this$fastMap$iv.size();
            for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
                ViewStructureCompat it = (ViewStructureCompat) item$iv$iv;
                target$iv.add(it.toViewStructure());
            }
            session.notifyViewsAppeared(target$iv);
            this.bufferedContentCaptureAppearedNodes.clear();
        }
        if (!this.bufferedContentCaptureDisappearedNodes.isEmpty()) {
            List $this$fastMap$iv2 = CollectionsKt.toList(this.bufferedContentCaptureDisappearedNodes);
            List target$iv2 = new ArrayList($this$fastMap$iv2.size());
            int size2 = $this$fastMap$iv2.size();
            for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
                Object item$iv$iv2 = $this$fastMap$iv2.get(index$iv$iv2);
                Integer it2 = (Integer) item$iv$iv2;
                target$iv2.add(Long.valueOf(it2.intValue()));
            }
            session.notifyViewsDisappeared(CollectionsKt.toLongArray(target$iv2));
            this.bufferedContentCaptureDisappearedNodes.clear();
        }
    }

    private final void notifySubtreeAppeared(SemanticsNode node) {
        bufferContentCaptureViewAppeared(node.getId(), toViewStructure(node));
        List<SemanticsNode> replacedChildren$ui_release = node.getReplacedChildren$ui_release();
        int size = replacedChildren$ui_release.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = replacedChildren$ui_release.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            notifySubtreeAppeared(child);
        }
    }

    private final void sendAccessibilitySemanticsStructureChangeEvents(SemanticsNode newNode, SemanticsNodeCopy oldNode) {
        Set newChildren = new LinkedHashSet();
        List<SemanticsNode> replacedChildren$ui_release = newNode.getReplacedChildren$ui_release();
        int size = replacedChildren$ui_release.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = replacedChildren$ui_release.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            if (getCurrentSemanticsNodes$ui_release().containsKey(Integer.valueOf(child.getId()))) {
                if (!oldNode.getChildren().contains(Integer.valueOf(child.getId()))) {
                    notifySubtreeAccessibilityStateChangedIfNeeded(newNode.getLayoutNode());
                    return;
                }
                newChildren.add(Integer.valueOf(child.getId()));
            }
        }
        Iterator<Integer> it = oldNode.getChildren().iterator();
        while (it.hasNext()) {
            if (!newChildren.contains(Integer.valueOf(it.next().intValue()))) {
                notifySubtreeAccessibilityStateChangedIfNeeded(newNode.getLayoutNode());
                return;
            }
        }
        List<SemanticsNode> replacedChildren$ui_release2 = newNode.getReplacedChildren$ui_release();
        int size2 = replacedChildren$ui_release2.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = replacedChildren$ui_release2.get(index$iv2);
            SemanticsNode child2 = (SemanticsNode) item$iv2;
            if (getCurrentSemanticsNodes$ui_release().containsKey(Integer.valueOf(child2.getId()))) {
                SemanticsNodeCopy semanticsNodeCopy = this.previousSemanticsNodes.get(Integer.valueOf(child2.getId()));
                Intrinsics.checkNotNull(semanticsNodeCopy);
                sendAccessibilitySemanticsStructureChangeEvents(child2, semanticsNodeCopy);
            }
        }
    }

    public final void sendContentCaptureSemanticsStructureChangeEvents$ui_release(SemanticsNode newNode, SemanticsNodeCopy oldNode) {
        Intrinsics.checkNotNullParameter(newNode, "newNode");
        Intrinsics.checkNotNullParameter(oldNode, "oldNode");
        List<SemanticsNode> replacedChildren$ui_release = newNode.getReplacedChildren$ui_release();
        int size = replacedChildren$ui_release.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = replacedChildren$ui_release.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            if (getCurrentSemanticsNodes$ui_release().containsKey(Integer.valueOf(child.getId())) && !oldNode.getChildren().contains(Integer.valueOf(child.getId()))) {
                notifySubtreeAppeared(child);
            }
        }
        for (Map.Entry<Integer, SemanticsNodeCopy> entry : this.previousSemanticsNodes.entrySet()) {
            if (!getCurrentSemanticsNodes$ui_release().containsKey(entry.getKey())) {
                bufferContentCaptureViewDisappeared(entry.getKey().intValue());
            }
        }
        List<SemanticsNode> replacedChildren$ui_release2 = newNode.getReplacedChildren$ui_release();
        int size2 = replacedChildren$ui_release2.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = replacedChildren$ui_release2.get(index$iv2);
            SemanticsNode child2 = (SemanticsNode) item$iv2;
            if (getCurrentSemanticsNodes$ui_release().containsKey(Integer.valueOf(child2.getId())) && this.previousSemanticsNodes.containsKey(Integer.valueOf(child2.getId()))) {
                SemanticsNodeCopy semanticsNodeCopy = this.previousSemanticsNodes.get(Integer.valueOf(child2.getId()));
                Intrinsics.checkNotNull(semanticsNodeCopy);
                sendContentCaptureSemanticsStructureChangeEvents$ui_release(child2, semanticsNodeCopy);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int semanticsNodeIdToAccessibilityVirtualNodeId(int id) {
        if (id == this.view.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
            return -1;
        }
        return id;
    }

    private final boolean traverseAtGranularity(SemanticsNode node, int granularity, boolean forward, boolean extendSelection) {
        AccessibilityIterators.TextSegmentIterator iterator;
        int selectionEnd;
        int selectionStart;
        int id = node.getId();
        Integer num = this.previousTraversedNode;
        if (num == null || id != num.intValue()) {
            this.accessibilityCursorPosition = -1;
            this.previousTraversedNode = Integer.valueOf(node.getId());
        }
        String text = getIterableTextForAccessibility(node);
        String str = text;
        if ((str == null || str.length() == 0) || (iterator = getIteratorForGranularity(node, granularity)) == null) {
            return false;
        }
        int current = getAccessibilitySelectionEnd(node);
        if (current == -1) {
            current = forward ? 0 : text.length();
        }
        int[] range = forward ? iterator.following(current) : iterator.preceding(current);
        if (range == null) {
            return false;
        }
        int segmentStart = range[0];
        int segmentEnd = range[1];
        if (extendSelection && isAccessibilitySelectionExtendable(node)) {
            int selectionStart2 = getAccessibilitySelectionStart(node);
            if (selectionStart2 == -1) {
                selectionStart2 = forward ? segmentStart : segmentEnd;
            }
            selectionEnd = forward ? segmentEnd : segmentStart;
            selectionStart = selectionStart2;
        } else {
            selectionEnd = forward ? segmentEnd : segmentStart;
            int selectionStart3 = selectionEnd;
            selectionStart = selectionStart3;
        }
        int action = forward ? 256 : 512;
        this.pendingTextTraversedEvent = new PendingTextTraversedEvent(node, action, granularity, segmentStart, segmentEnd, SystemClock.uptimeMillis());
        setAccessibilitySelection(node, selectionStart, selectionEnd, true);
        return true;
    }

    private final void sendPendingTextTraversedAtGranularityEvent(int semanticsNodeId) {
        PendingTextTraversedEvent it = this.pendingTextTraversedEvent;
        if (it != null) {
            if (semanticsNodeId != it.getNode().getId()) {
                return;
            }
            if (SystemClock.uptimeMillis() - it.getTraverseTime() <= 1000) {
                AccessibilityEvent event = createEvent$ui_release(semanticsNodeIdToAccessibilityVirtualNodeId(it.getNode().getId()), 131072);
                event.setFromIndex(it.getFromIndex());
                event.setToIndex(it.getToIndex());
                event.setAction(it.getAction());
                event.setMovementGranularity(it.getGranularity());
                event.getText().add(getIterableTextForAccessibility(it.getNode()));
                sendEvent(event);
            }
        }
        this.pendingTextTraversedEvent = null;
    }

    private final boolean setAccessibilitySelection(SemanticsNode node, int start, int end, boolean traversalMode) {
        String text;
        int i;
        if (node.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetSelection()) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(node)) {
            Function3 function3 = (Function3) ((AccessibilityAction) node.getUnmergedConfig().get(SemanticsActions.INSTANCE.getSetSelection())).getAction();
            if (function3 != null) {
                return ((Boolean) function3.invoke(Integer.valueOf(start), Integer.valueOf(end), Boolean.valueOf(traversalMode))).booleanValue();
            }
            return false;
        }
        if ((start == end && end == this.accessibilityCursorPosition) || (text = getIterableTextForAccessibility(node)) == null) {
            return false;
        }
        if (start >= 0 && start == end && end <= text.length()) {
            i = start;
        } else {
            i = -1;
        }
        this.accessibilityCursorPosition = i;
        boolean nonEmptyText = text.length() > 0;
        AccessibilityEvent event = createTextSelectionChangedEvent(semanticsNodeIdToAccessibilityVirtualNodeId(node.getId()), nonEmptyText ? Integer.valueOf(this.accessibilityCursorPosition) : null, nonEmptyText ? Integer.valueOf(this.accessibilityCursorPosition) : null, nonEmptyText ? Integer.valueOf(text.length()) : null, text);
        sendEvent(event);
        sendPendingTextTraversedAtGranularityEvent(node.getId());
        return true;
    }

    private final int getAccessibilitySelectionStart(SemanticsNode node) {
        if (!node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription()) && node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTextSelectionRange())) {
            return TextRange.m4768getStartimpl(((TextRange) node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getTextSelectionRange())).getPackedValue());
        }
        return this.accessibilityCursorPosition;
    }

    private final int getAccessibilitySelectionEnd(SemanticsNode node) {
        if (!node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription()) && node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTextSelectionRange())) {
            return TextRange.m4763getEndimpl(((TextRange) node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getTextSelectionRange())).getPackedValue());
        }
        return this.accessibilityCursorPosition;
    }

    private final boolean isAccessibilitySelectionExtendable(SemanticsNode node) {
        return !node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription()) && node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getEditableText());
    }

    private final AccessibilityIterators.TextSegmentIterator getIteratorForGranularity(SemanticsNode node, int granularity) {
        AccessibilityIterators.AbstractTextSegmentIterator iterator;
        if (node == null) {
            return null;
        }
        String text = getIterableTextForAccessibility(node);
        String str = text;
        if (str == null || str.length() == 0) {
            return null;
        }
        switch (granularity) {
            case 1:
                AccessibilityIterators.CharacterTextSegmentIterator.Companion companion = AccessibilityIterators.CharacterTextSegmentIterator.INSTANCE;
                Locale locale = this.view.getContext().getResources().getConfiguration().locale;
                Intrinsics.checkNotNullExpressionValue(locale, "view.context.resources.configuration.locale");
                iterator = companion.getInstance(locale);
                iterator.initialize(text);
                break;
            case 2:
                AccessibilityIterators.WordTextSegmentIterator.Companion companion2 = AccessibilityIterators.WordTextSegmentIterator.INSTANCE;
                Locale locale2 = this.view.getContext().getResources().getConfiguration().locale;
                Intrinsics.checkNotNullExpressionValue(locale2, "view.context.resources.configuration.locale");
                iterator = companion2.getInstance(locale2);
                iterator.initialize(text);
                break;
            case 4:
            case 16:
                if (!node.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult())) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                Function1 function1 = (Function1) ((AccessibilityAction) node.getUnmergedConfig().get(SemanticsActions.INSTANCE.getGetTextLayoutResult())).getAction();
                Boolean getLayoutResult = function1 != null ? (Boolean) function1.invoke(arrayList) : null;
                if (!Intrinsics.areEqual((Object) getLayoutResult, (Object) true)) {
                    return null;
                }
                TextLayoutResult textLayoutResult = (TextLayoutResult) arrayList.get(0);
                if (granularity == 4) {
                    AccessibilityIterators.AbstractTextSegmentIterator iterator2 = AccessibilityIterators.LineTextSegmentIterator.INSTANCE.getInstance();
                    ((AccessibilityIterators.LineTextSegmentIterator) iterator2).initialize(text, textLayoutResult);
                    iterator = iterator2;
                } else {
                    AccessibilityIterators.AbstractTextSegmentIterator iterator3 = AccessibilityIterators.PageTextSegmentIterator.INSTANCE.getInstance();
                    ((AccessibilityIterators.PageTextSegmentIterator) iterator3).initialize(text, textLayoutResult, node);
                    iterator = iterator3;
                }
                break;
                break;
            case 8:
                iterator = AccessibilityIterators.ParagraphTextSegmentIterator.INSTANCE.getInstance();
                iterator.initialize(text);
                break;
            default:
                return null;
        }
        return iterator;
    }

    private final String getIterableTextForAccessibility(SemanticsNode node) {
        AnnotatedString annotatedString;
        if (node == null) {
            return null;
        }
        if (!node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription())) {
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.isTextField(node)) {
                AnnotatedString textForTextField = getTextForTextField(node.getUnmergedConfig());
                if (textForTextField != null) {
                    return textForTextField.getText();
                }
                return null;
            }
            List list = (List) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
            if (list == null || (annotatedString = (AnnotatedString) CollectionsKt.firstOrNull(list)) == null) {
                return null;
            }
            return annotatedString.getText();
        }
        return TempListUtilsKt.fastJoinToString$default((List) node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getContentDescription()), ",", null, null, 0, null, null, 62, null);
    }

    private final AnnotatedString getTextForTextField(SemanticsConfiguration $this$getTextForTextField) {
        return (AnnotatedString) SemanticsConfigurationKt.getOrNull($this$getTextForTextField, SemanticsProperties.INSTANCE.getEditableText());
    }

    /* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$MyNodeProvider;", "Landroid/view/accessibility/AccessibilityNodeProvider;", "(Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;)V", "addExtraDataToAccessibilityNodeInfo", "", "virtualViewId", "", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "extraDataKey", "", "arguments", "Landroid/os/Bundle;", "createAccessibilityNodeInfo", "performAction", "", "action", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class MyNodeProvider extends AccessibilityNodeProvider {
        public MyNodeProvider() {
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) {
            return AndroidComposeViewAccessibilityDelegateCompat.this.createNodeInfo(virtualViewId);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int virtualViewId, int action, Bundle arguments) {
            return AndroidComposeViewAccessibilityDelegateCompat.this.performActionHelper(virtualViewId, action, arguments);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public void addExtraDataToAccessibilityNodeInfo(int virtualViewId, AccessibilityNodeInfo info, String extraDataKey, Bundle arguments) {
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(extraDataKey, "extraDataKey");
            AndroidComposeViewAccessibilityDelegateCompat.this.addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, info, extraDataKey, arguments);
        }
    }

    /* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$Api24Impl;", "", "()V", "addSetProgressAction", "", "info", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Api24Impl {
        public static final Api24Impl INSTANCE = new Api24Impl();

        private Api24Impl() {
        }

        @JvmStatic
        public static final void addSetProgressAction(AccessibilityNodeInfoCompat info, SemanticsNode semanticsNode) {
            AccessibilityAction it;
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(semanticsNode, "semanticsNode");
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode) && (it = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetProgress())) != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionSetProgress, it.getLabel()));
            }
        }
    }

    /* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$Api28Impl;", "", "()V", "setScrollEventDelta", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/accessibility/AccessibilityEvent;", "deltaX", "", "deltaY", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Api28Impl {
        public static final Api28Impl INSTANCE = new Api28Impl();

        private Api28Impl() {
        }

        @JvmStatic
        public static final void setScrollEventDelta(AccessibilityEvent event, int deltaX, int deltaY) {
            Intrinsics.checkNotNullParameter(event, "event");
            event.setScrollDeltaX(deltaX);
            event.setScrollDeltaY(deltaY);
        }
    }

    /* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$Api29Impl;", "", "()V", "addPageActions", "", "info", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Api29Impl {
        public static final Api29Impl INSTANCE = new Api29Impl();

        private Api29Impl() {
        }

        @JvmStatic
        public static final void addPageActions(AccessibilityNodeInfoCompat info, SemanticsNode semanticsNode) {
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(semanticsNode, "semanticsNode");
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                AccessibilityAction it = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageUp());
                if (it != null) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageUp, it.getLabel()));
                }
                AccessibilityAction it2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageDown());
                if (it2 != null) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageDown, it2.getLabel()));
                }
                AccessibilityAction it3 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageLeft());
                if (it3 != null) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageLeft, it3.getLabel()));
                }
                AccessibilityAction it4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageRight());
                if (it4 != null) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageRight, it4.getLabel()));
                }
            }
        }
    }
}
